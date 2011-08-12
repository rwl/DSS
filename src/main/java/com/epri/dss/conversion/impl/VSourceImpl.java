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
		this.className = "Vsource";
		this.DSSClassType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // don't want this in PC element list

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = VSource.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "basekv";
		propertyName[2] = "pu";
		propertyName[3] = "angle";
		propertyName[4] = "frequency";
		propertyName[5] = "phases";
		propertyName[6] = "MVAsc3";
		propertyName[7] = "MVAsc1";
		propertyName[8] = "x1r1";
		propertyName[9] = "x0r0";
		propertyName[10] = "Isc3";
		propertyName[11] = "Isc1";
		propertyName[12] = "R1";
		propertyName[13] = "X1";
		propertyName[14] = "R0";
		propertyName[15] = "X0";
		propertyName[16] = "ScanType";
	    propertyName[17] = "Sequence";
		propertyName[18]  = "bus2";

		// define property help values
		propertyHelp[0] = "Name of bus to which the main terminal (1) is connected."+DSSGlobals.CRLF+"bus1=busname"+DSSGlobals.CRLF+"bus1=busname.1.2.3";
		propertyHelp[1] = "Base Source kV, usually phase-phase (L-L) unless you are making a positive-sequence model or 1-phase model"+
				"in which case, it will be phase-neutral (L-N) kV.";
		propertyHelp[2] = "Per unit of the base voltage that the source is actually operating at."+ DSSGlobals.CRLF +
				"\"pu=1.05\"";
		propertyHelp[3] = "Phase angle in degrees of first phase: e.g.,Angle=10.3";
		propertyHelp[4] = "Source frequency.  Defaults to system default base frequency.";
		propertyHelp[5] = "Number of phases.  Defaults to 3.";
		propertyHelp[6] = "MVA Short circuit, 3-phase fault. Default = 2000. " +
				"Z1 is determined by squaring the base kv and dividing by this value. "+
				"For single-phase source, this value is not used.";
		propertyHelp[7] = "MVA Short Circuit, 1-phase fault. Default = 2100. " +
				"The \"single-phase impedance\", Zs, is determined by squaring the base kV and dividing by this value. "+
				"Then Z0 is determined by Z0 = 3Zs - 2Z1.  For 1-phase sources, Zs is used directly. " +
				"Use X0R0 to define X/R ratio for 1-phase source.";
		propertyHelp[8] = "Positive-sequence  X/R ratio. Default = 4.";
		propertyHelp[9] = "Zero-sequence X/R ratio.Default = 3.";
		propertyHelp[10] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"3-phase short circuit current, amps.  Default is 10000.";
		propertyHelp[11] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"single-phase short circuit current, amps.  Default is 10500.";
		propertyHelp[12] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Positive-sequence resistance, ohms.  Default is 1.65.";
		propertyHelp[13] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Positive-sequence reactance, ohms.  Default is 6.6.";
		propertyHelp[14] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Zero-sequence resistance, ohms.  Default is 1.9.";
		propertyHelp[15] = "Alternate method of defining the source impedance. " + DSSGlobals.CRLF +
				"Zero-sequence reactance, ohms.  Default is 5.7.";
		propertyHelp[16] = "{pos*| zero | none} Maintain specified sequence for harmonic solution. Default is positive sequence. "+
				"Otherwise, angle between phases rotates with harmonic.";
		propertyHelp[17] = "{pos*| neg | zero} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. "+
                 "Default is positive sequence. ";
		propertyHelp[18] = "Name of bus to which 2nd terminal is connected."+DSSGlobals.CRLF+"bus2=busname"+DSSGlobals.CRLF+"bus2=busname.1.2.3" +
				DSSGlobals.CRLF + DSSGlobals.CRLF +
				"Default is Bus1.0.0.0 (grounded wye connection)";

		activeProperty = VSource.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override help string
		propertyHelp[VSource.NumPropsThisClass - 1] = "Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.";
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

		// special handling for bus 1
		// set bus2=bus1.0.0.0

		VSourceObj avs = getActiveVsourceObj();

		avs.setBus(1, S);  // TODO Check zero based indexing

		// default bus2 to zero node of bus1. (Grounded-Y connection)

		// strip node designations from s
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos);
		} else {
			S2 = S.substring(0);  // copy up to dot
		}
		for (i = 0; i < avs.getNPhases(); i++)
			S2 = S2 + ".0";  // append series of ".0"'s

		avs.setBus(2, S2);  // default setting for bus2
	}

	@Override
	public int edit() {

		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveVsourceObj((VSourceObj) elementList.getActive());
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
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				avs.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"VSource."+avs.getName()+"\"", 320);
				break;
			case 0:
				vSourceSetBus1(Param);  // special handling of bus 1
				break;
			case 1:
				avs.setkVBase(parser.makeDouble());  // baseKV
				break;
			case 2:
				avs.setPerUnit(parser.makeDouble());  // pu
				break;
			case 3:
				avs.setAngle(parser.makeDouble());  // ang
				break;
			case 4:
				avs.setSrcFrequency(parser.makeDouble());  // freq
				break;
			case 5:
				avs.setNPhases(parser.makeInteger());  // num phases
				avs.setNConds(avs.getNPhases());  // force reallocation of terminal info
				break;
			case 6:
				avs.setMVAsc3(parser.makeDouble());  // MVAsc3
				break;
			case 7:
				avs.setMVAsc1(parser.makeDouble());  // MVAsc1
				break;
			case 8:
				avs.setX1R1(parser.makeDouble());  // X1/R1
				break;
			case 9:
				avs.setX0R0(parser.makeDouble());  // X0/R0
				break;
			case 10:
				avs.setIsc3(parser.makeDouble());
				break;
			case 11:
				avs.setIsc1(parser.makeDouble());
				break;
			case 12:
				avs.setR1(parser.makeDouble());
				break;
			case 13:
				avs.setX1(parser.makeDouble());
				break;
			case 14:
				avs.setR0(parser.makeDouble());
				break;
			case 15:
				avs.setX0(parser.makeDouble());
				break;
			case 16:
				switch (Param.toUpperCase().charAt(0)) {
				case 'P':
					avs.setScanType(1);
					break;
				case 'Z':
					avs.setScanType(0);
					break;
				case 'N':
					avs.setScanType(-1);
					break;
				default:
					Globals.doSimpleMsg("Unknown scan type for \"" + getName() +"."+ avs.getName() + "\": "+Param, 321);
					break;
				}
				break;
			case 17:
				switch (Param.toUpperCase().charAt(0)) {
				case 'P':
					avs.setSequenceType(1);
					break;
				case 'Z':
					avs.setSequenceType(0);
					break;
				case 'N':
					avs.setSequenceType(-1);
					break;
				default:
					Globals.doSimpleMsg("Unknown sequence type for \"" + getName() +"."+ getName() + "\": "+Param, 321);
					break;
				}
				break;
			case 18:
				avs.setBus(2, Param);  // TODO Check zero based indexing
				break;
			default:
				classEdit(ActiveVsourceObj, ParamPointer - VSource.NumPropsThisClass);
				break;
			}

			// set the Z spec type switch depending on which was specified
			switch (ParamPointer) {
			case 6:
				avs.setZSpecType(1);
				break;
			case 7:
				avs.setZSpecType(1);
				break;
			case 10:
				avs.setZSpecType(2);
				break;
			case 11:
				avs.setZSpecType(2);
				break;
			case 12:
				avs.setZSpecType(3);
				break;
			case 13:
				avs.setZSpecType(3);
				break;
			case 14:
				avs.setZSpecType(3);
				break;
			case 15:
				avs.setZSpecType(3);
				break;
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		avs.recalcElementData();
		avs.setYPrimInvalid(true);

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
				avs.setNConds(avs.getNPhases());  // forces reallocation of terminal stuff

				avs.setYorder(avs.getNConds() * avs.getNTerms());
				avs.setYPrimInvalid(true);

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
			DSSGlobals.getInstance().doSimpleMsg("Error in VSource makeLike: \"" + OtherSource + "\" not found.", 322);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement VSource.init", -1);
		return 0;
	}

	public static VSourceObj getActiveVsourceObj() {
		return ActiveVsourceObj;
	}

	public static void setActiveVsourceObj(VSourceObj activeVsourceObj) {
		ActiveVsourceObj = activeVsourceObj;
	}

}
