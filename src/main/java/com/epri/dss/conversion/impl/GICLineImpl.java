package com.epri.dss.conversion.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.conversion.GICLine;
import com.epri.dss.conversion.GICLineObj;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.CommandListImpl;
import com.ibm.icu.util.GlobalizationPreferences;

public class GICLineImpl extends PCClassImpl implements GICLine {

	private static GICLineObj ActiveGICLineObj;

	public GICLineImpl() {
		super();
		this.className = "GICLine";
		this.DSSClassType = DSSClassDefs.GIC_LINE + DSSClassDefs.PC_ELEMENT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		numProperties = NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "bus2";
		propertyName[2] = "Volts";
		propertyName[3] = "Angle";
		propertyName[4] = "frequency";
		propertyName[5] = "phases";
		propertyName[6] = "R";
		propertyName[7] = "X";
		propertyName[8] = "C";
		propertyName[9] = "ScanType";
		propertyName[10] = "Sequence";

		// define property help values
		propertyHelp[0] = "Name of bus to which the main terminal (1) is connected."+ CRLF +
		                   "bus1=busname"+ CRLF +
		                   "bus1=busname.1.2.3";
		propertyHelp[1] = "Name of bus to which 2nd terminal is connected."+ CRLF +
		                   "bus2=busname"+ CRLF +
		                   "bus2=busname.1.2.3" + CRLF + CRLF +
		                   "No Default; must be specified.";

		propertyHelp[2] = "Voltage magnitude, in volts, of the GIC voltage induced across this line.";
		propertyHelp[3] = "Phase angle in degrees of first phase. Default=0.0";
		propertyHelp[4] = "Source frequency.  Defaults to 0.1 Hz.";
		propertyHelp[5] = "Number of phases.  Defaults to 3.";
		propertyHelp[6] = "Resistance of line, ohms of impedance in series with GIC voltage source. ";
		propertyHelp[7] = "Reactance at base frequency, ohms. Default = 0.0. This value is generally not important for GIC studies but may be used if desired.";
		propertyHelp[8] = "Value of line blocking capacitance in microfarads. Default = 0.0, implying that there is no line blocking capacitor.";
		propertyHelp[9] = "{pos | zero* | none} Maintain specified sequence for harmonic solution. Default is ZERO sequence. "+
		                    "Otherwise, angle between phases rotates with harmonic.";
		propertyHelp[10] = "{pos | neg | zero*} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. "+
		                    "Default is ZERO sequence. ";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override help string
		propertyHelp[NumPropsThisClass] = "Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new GICLineObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	private void GICLineSetBus1(final String S) {
		String S2;
		int dotpos;

		// special handling for bus 1
		// set bus2=bus1.0.0.0

		GICLineObj agl = getActiveGICLineObj();

		agl.setBus(1, S);

		// strip node designations from s
		dotpos = S.indexOf('.');
		if (dotpos >= 0) {
			S2 = S.substring(0, dotpos-1);  // copy up to dot
		} else {
			S2 = S.substring(0);
		}

		agl.setBus(2, S2);  // default setting for bus2 is same as bus1
	}

	@Override
	public int edit() {
		int ParamPointer;
		String ParamName, Param;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveGICLineObj((GICLineObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveGICLineObj());

		int Result = 0;
		GICLineObj agl = getActiveGICLineObj();

		ParamPointer = 0;
		ParamName = parser.getNextParam();
		Param     = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				agl.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"VSource."+agl.getName()+"\"", 320);
				break;
			case 0:
				GICLineSetBus1(Param);   // special handling of bus 1
				break;
			case 1:
				agl.setBus(2, Param);
				break;
			case 2:
				agl.setVolts(parser.makeDouble());  // basekv
				break;
			case 3:
				agl.setAngle(parser.makeDouble());  // ang
				break;
			case 4:
				agl.setSrcFrequency(parser.makeDouble());  // freq
				break;
			case 5:
				agl.setNPhases(parser.makeInteger());  // num phases
				agl.setNConds(agl.getNPhases());  // force reallocation of terminal info
				break;
			case 6:
				agl.setR(parser.makeDouble());
				break;
			case 7:
				agl.setX(parser.makeDouble());
				break;
			case 8:
				agl.setC(parser.makeDouble());
				break;
			case 9:
				switch (Param.toUpperCase().charAt(0)) {
				case 'P':
					agl.setScanType(1);
					break;
				case 'Z':
					agl.setScanType(0);
					break;
				case 'N':
					agl.setScanType(-1);
					break;
				default:
					Globals.doSimpleMsg("Unknown scan type for \"" + getName() +"."+ agl.getName() + "\": "+Param, 321);
					break;
				}
				break;
			case 10:
				switch (Param.toUpperCase().charAt(0)) {
				case 'P':
					agl.setSequenceType(1);
					break;
				case 'Z':
					agl.setSequenceType(0);
					break;
				case 'N':
					agl.setSequenceType(-1);
					break;
				default:
					Globals.doSimpleMsg("Unknown sequence type for \"" + getName() +"."+ agl.getName() + "\": "+Param, 321);
					break;
				}
				break;
			default:
				classEdit(getActiveGICLineObj(), ParamPointer - NumPropsThisClass);
				break;
			}

			ParamName = parser.getNextParam();
			Param     = parser.makeString();
		}

		agl.recalcElementData();
		agl.setYPrimInvalid(true);

		return Result;
	}

	@Override
	protected int makeLike(String OtherLine) {
		GICLineObj OtherGICLine;
		int i;

		int Result = 0;

		/* See if we can find this line name in the present collection */
		OtherGICLine = (GICLineObj) find(OtherLine);
		if (OtherGICLine != null) {
			GICLineObj agl = getActiveGICLineObj();

			if (agl.getNPhases() != OtherGICLine.getNPhases()) {

				agl.setNPhases(OtherGICLine.getNPhases());
				agl.setNConds(agl.getNPhases());  // forces reallocation of terminal stuff

				agl.setYorder(agl.getNConds() * agl.getNTerms());
				agl.setYPrimInvalid(true);

				if (agl.getZ() != null) agl.setZ(null);
				if (agl.getZInv() != null) agl.setZInv(null);

				agl.setZ( new CMatrixImpl(agl.getNPhases()) );
				agl.setZInv( new CMatrixImpl(agl.getNPhases()) );
			}

			agl.getZ().copyFrom(OtherGICLine.getZ());
			// Zinv.CopyFrom(OtherLine.Zinv);
			agl.setR(OtherGICLine.getR());
			agl.setX(OtherGICLine.getX());
			agl.setC(OtherGICLine.getC());
			agl.setVolts(OtherGICLine.getVolts());
			agl.setAngle(OtherGICLine.getAngle());

			agl.setSrcFrequency(OtherGICLine.getSrcFrequency());
			agl.setScanType(OtherGICLine.getScanType());
			agl.setSequenceType(OtherGICLine.getSequenceType());

			classMakeLike(OtherGICLine);

			for (i = 0; i < agl.getParentClass().getNumProperties(); i++) {
				agl.setPropertyValue(i, OtherGICLine.getPropertyValue(i));
				Result = 1;
			}
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in GICLine makeLike: \"" + OtherLine + "\" not found.", 322);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement GICLine.init", -1);
		return 0;
	}

	public static GICLineObj getActiveGICLineObj() {
		return ActiveGICLineObj;
	}

	public static void setActiveGICLineObj(GICLineObj activeGICLineObj) {
		ActiveGICLineObj = activeGICLineObj;
	}

}
