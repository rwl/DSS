package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.VSource;
import com.epri.dss.conversion.VSourceObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.CommandListImpl;

public class VSourceImpl extends PCClassImpl implements VSource {

	private static VSourceObj ActiveVsourceObj;

	public VSourceImpl() {
		super();
		this.Class_Name   = "Vsource";
		this.DSSClassType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // Don"t want this in PC Element List

		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = VSource.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();


		// Define Property names
		PropertyName[0] = "bus1";
		PropertyName[1] = "basekv";
		PropertyName[2] = "pu";
		PropertyName[3] = "angle";
		PropertyName[4] = "frequency";
		PropertyName[5] = "phases";
		PropertyName[6] = "MVAsc3";
		PropertyName[7] = "MVAsc1";
		PropertyName[8] = "x1r1";
		PropertyName[9] = "x0r0";
		PropertyName[10] = "Isc3";
		PropertyName[11] = "Isc1";
		PropertyName[12] = "R1";
		PropertyName[13] = "X1";
		PropertyName[14] = "R0";
		PropertyName[15] = "X0";
		PropertyName[16] = "ScanType";
	    PropertyName[17] = "Sequence";
		PropertyName[18]  = "bus2";

		// define Property help values
		PropertyHelp[0] = "Name of bus to which the main terminal (1) is connected."+DSSGlobals.CRLF+"bus1=busname"+DSSGlobals.CRLF+"bus1=busname.1.2.3";
		PropertyHelp[1] = "Base Source kV, usually phase-phase (L-L) unless you are making a positive-sequence model or 1-phase model"+
				"in which case, it will be phase-neutral (L-N) kV.";
		PropertyHelp[2] = "Per unit of the base voltage that the source is actually operating at."+ DSSGlobals.CRLF +
				"\"pu=1.05\"";
		PropertyHelp[3] = "Phase angle in degrees of first phase: e.g.,Angle=10.3";
		PropertyHelp[4] = "Source frequency.  Defaults to system default base frequency.";
		PropertyHelp[5] = "Number of phases.  Defaults to 3.";
		PropertyHelp[6] = "MVA Short circuit, 3-phase fault. Default = 2000. " +
				"Z1 is determined by squaring the base kv and dividing by this value. "+
				"For single-phase source, this value is not used.";
		PropertyHelp[7] = "MVA Short Circuit, 1-phase fault. Default = 2100. " +
				"The \"single-phase impedance\", Zs, is determined by squaring the base kV and dividing by this value. "+
				"Then Z0 is determined by Z0 = 3Zs - 2Z1.  For 1-phase sources, Zs is used directly. " +
				"Use X0R0 to define X/R ratio for 1-phase source.";
		PropertyHelp[8] = "Positive-sequence  X/R ratio. Default = 4.";
		PropertyHelp[9] = "Zero-sequence X/R ratio.Default = 3.";
		PropertyHelp[10] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"3-phase short circuit current, amps.  Default is 10000.";
		PropertyHelp[11] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"single-phase short circuit current, amps.  Default is 10500.";
		PropertyHelp[12] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Positive-sequence resistance, ohms.  Default is 1.65.";
		PropertyHelp[13] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Positive-sequence reactance, ohms.  Default is 6.6.";
		PropertyHelp[14] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Zero-sequence resistance, ohms.  Default is 1.9.";
		PropertyHelp[15] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Zero-sequence reactance, ohms.  Default is 5.7.";
		PropertyHelp[16] = "{pos*| zero | none} Maintain specified sequence for harmonic solution. Default is positive sequence. "+
				"Otherwise, angle between phases rotates with harmonic.";
		PropertyHelp[17] = "{pos*| neg | zero} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. "+
                 "Default is positive sequence. ";
		PropertyHelp[18] = "Name of bus to which 2nd terminal is connected."+DSSGlobals.CRLF+"bus2=busname"+DSSGlobals.CRLF+"bus2=busname.1.2.3" +
				DSSGlobals.CRLF + DSSGlobals.CRLF +
				"Default is Bus1.0.0.0 (grounded wye connection)";

		ActiveProperty = VSource.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list

		// Override help string
		PropertyHelp[VSource.NumPropsThisClass - 1] = "Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new VSourceObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void vSourceSetBus1(String S) {
		String S2;
		int i, dotpos;

		// Special handling for Bus 1
		// Set Bus2 = Bus1.0.0.0

		VSourceObj avs = getActiveVsourceObj();

		avs.setBus(1, S);  // TODO Check zero based indexing

		// Default Bus2 to zero node of Bus1. (Grounded-Y connection)

		// Strip node designations from S
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos);
		} else {
			S2 = S.substring(0);  // copy up to Dot
		}
		for (i = 0; i < avs.getNPhases(); i++)
			S2 = S2 + ".0";  // append series of ".0"'s

		avs.setBus(2, S2);  // default setting for Bus2
	}

	@Override
	public int edit() {

		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// Continue parsing with contents of parser
		setActiveVsourceObj((VSourceObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveVsourceObj());

		int Result = 0;

		VSourceObj avs = getActiveVsourceObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param     = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				avs.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"VSource."+avs.getName()+"\"", 320);
			case 0:
				vSourceSetBus1(Param);   // special handling of Bus 1
			case 1:
				avs.setkVBase(parser.makeDouble());  // basekv
			case 2:
				avs.setPerUnit(parser.makeDouble());  // pu
			case 3:
				avs.setAngle(parser.makeDouble());  // Ang
			case 4:
				avs.setSrcFrequency(parser.makeDouble()); // freq
			case 5:
				avs.setNPhases(parser.makeInteger());  // num phases
						avs.setNConds(avs.getNPhases());  // Force reallocation of terminal info
			case 6:
				avs.setMVAsc3(parser.makeDouble());  // MVAsc3
			case 7:
				avs.setMVAsc1(parser.makeDouble());  // MVAsc1
			case 8:
				avs.setX1R1(parser.makeDouble());  // X1/R1
			case 9:
				avs.setX0R0(parser.makeDouble());  // X0/R0
			case 10:
				avs.setIsc3(parser.makeDouble());
			case 11:
				avs.setIsc1(parser.makeDouble());
			case 12:
				avs.setR1(parser.makeDouble());
			case 13:
				avs.setX1(parser.makeDouble());
			case 14:
				avs.setR0(parser.makeDouble());
			case 15:
				avs.setX0(parser.makeDouble());
			case 16:
				switch (Param.toUpperCase().charAt(0)) {
				case 'P':
					avs.setScanType(1);
				case 'Z':
					avs.setScanType(0);
				case 'N':
					avs.setScanType(-1);
				default:
					Globals.doSimpleMsg("Unknown Scan Type for \"" + Class_Name +"."+ avs.getName() + "\": "+Param, 321);
				}
			case 17:
				switch (Param.toUpperCase().charAt(0)) {
				case 'P':
					avs.setSequenceType(1);
				case 'Z':
					avs.setSequenceType(0);
				case 'N':
					avs.setSequenceType(-1);
				default:
					Globals.doSimpleMsg("Unknown Sequence Type for \"" + Class_Name +"."+ getName() + "\": "+Param, 321);
				}
			case 18:
				avs.setBus(2, Param);  // TODO Check zero based indexing
			default:
				classEdit(ActiveVsourceObj, ParamPointer - VSource.NumPropsThisClass);
			}

			// Set the Z spec type switch depending on which was specified.
			switch (ParamPointer) {
			case 6:
				avs.setZSpecType(1);
			case 7:
				avs.setZSpecType(1);
			case 10:
				avs.setZSpecType(2);
			case 11:
				avs.setZSpecType(2);
			case 12:
				avs.setZSpecType(3);
			case 13:
				avs.setZSpecType(3);
			case 14:
				avs.setZSpecType(3);
			case 15:
				avs.setZSpecType(3);
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		avs.recalcElementData();
		avs.setYprimInvalid(true);

		return Result;
	}

	@Override
	protected int makeLike(String OtherSource) {

		int Result = 0;

		/* See if we can find this line name in the present collection */
		VSourceObj OtherVSource = (VSourceObj) find(OtherSource);
		if (OtherVSource != null) {
			VSourceObj avs = getActiveVsourceObj();

			if (avs.getNPhases() != OtherVSource.getNPhases()) {
				avs.setNPhases(OtherVSource.getNPhases());
				avs.setNConds(avs.getNPhases());  // Forces reallocation of terminal stuff

				avs.setYorder(avs.getNConds() * avs.getNTerms());
				avs.setYprimInvalid(true);

				if (avs.getZ() != null) avs.setZ(null);
				if (avs.getZinv() != null) avs.setZinv(null);

				avs.setZ( new CMatrixImpl(avs.getNPhases()) );
				avs.setZinv( new CMatrixImpl(avs.getNPhases()) );
			}

			avs.getZ().copyFrom(OtherVSource.getZ());
			// avs.getZinv().copyFrom(OtherLine.getZinv());
			avs.setVMag(OtherVSource.getVMag());
			avs.setkVBase(OtherVSource.getkVBase());
			avs.setPerUnit(OtherVSource.getPerUnit());
			avs.setAngle(OtherVSource.getAngle());
			avs.setSrcFrequency(OtherVSource.getSrcFrequency());
			avs.setMVAsc3(OtherVSource.getMVAsc3());
			avs.setMVAsc1(OtherVSource.getMVAsc1());
			avs.setX1R1(OtherVSource.getX1R1());
			avs.setX0R0(OtherVSource.getX0R0());
			avs.setScanType(OtherVSource.getScanType());
			avs.setSequenceType(OtherVSource.getSequenceType());

			classMakeLike(OtherVSource);

			for (int i = 0; i < avs.getParentClass().getNumProperties(); i++)
				avs.setPropertyValue(i, OtherVSource.getPropertyValue(i));

			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Vsource makeLike: \"" + OtherSource + "\" Not Found.", 322);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Vsource.init", -1);
		return 0;
	}

	public static VSourceObj getActiveVsourceObj() {
		return ActiveVsourceObj;
	}

	public static void setActiveVsourceObj(VSourceObj activeVsourceObj) {
		ActiveVsourceObj = activeVsourceObj;
	}

}
