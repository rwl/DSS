package com.ncond.dss.conversion;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.CommandList;

public class VSource extends PCClass {

	public static final int NumPropsThisClass = 19;

	public static VSourceObj activeVSourceObj;

	public VSource() {
		super();
		className = "VSource";
		classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // don't want this in PC element list

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
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
		propertyHelp[0] = "Name of bus to which the main terminal (1) is connected."+DSS.CRLF+"bus1=busname"+DSS.CRLF+"bus1=busname.1.2.3";
		propertyHelp[1] = "Base Source kV, usually phase-phase (L-L) unless you are making a positive-sequence model or 1-phase model"+
				"in which case, it will be phase-neutral (L-N) kV.";
		propertyHelp[2] = "Per unit of the base voltage that the source is actually operating at."+ DSS.CRLF +
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
		propertyHelp[10] = "Alternate method of defining the source impedance. " + DSS.CRLF +
				"3-phase short circuit current, amps.  Default is 10000.";
		propertyHelp[11] = "Alternate method of defining the source impedance. " + DSS.CRLF +
				"single-phase short circuit current, amps.  Default is 10500.";
		propertyHelp[12] = "Alternate method of defining the source impedance. " + DSS.CRLF +
				"Positive-sequence resistance, ohms.  Default is 1.65.";
		propertyHelp[13] = "Alternate method of defining the source impedance. " + DSS.CRLF +
				"Positive-sequence reactance, ohms.  Default is 6.6.";
		propertyHelp[14] = "Alternate method of defining the source impedance. " + DSS.CRLF +
				"Zero-sequence resistance, ohms.  Default is 1.9.";
		propertyHelp[15] = "Alternate method of defining the source impedance. " + DSS.CRLF +
				"Zero-sequence reactance, ohms.  Default is 5.7.";
		propertyHelp[16] = "{pos*| zero | none} Maintain specified sequence for harmonic solution. Default is positive sequence. "+
				"Otherwise, angle between phases rotates with harmonic.";
		propertyHelp[17] = "{pos*| neg | zero} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. "+
                 "Default is positive sequence. ";
		propertyHelp[18] = "Name of bus to which 2nd terminal is connected."+DSS.CRLF+"bus2=busname"+DSS.CRLF+"bus2=busname.1.2.3" +
				DSS.CRLF + DSS.CRLF +
				"Default is Bus1.0.0.0 (grounded wye connection)";

		activeProperty = VSource.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override help string
		propertyHelp[VSource.NumPropsThisClass] = "Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.";
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new VSourceObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	private void vSourceSetBus1(String s) {
		String s2;
		int i, dotpos;

		// special handling for bus 1
		// set bus2=bus1.0.0.0

		VSourceObj avs = activeVSourceObj;

		avs.setBus(0, s);

		// default bus2 to zero node of bus1. (Grounded-Y connection)

		// strip node designations from s
		dotpos = s.indexOf('.');
		if (dotpos >= 0) {
			s2 = s.substring(0, dotpos);
		} else {
			s2 = s.substring(0);  // copy up to dot
		}
		for (i = 0; i < avs.getNumPhases(); i++)
			s2 = s2 + ".0";  // append series of ".0"'s

		avs.setBus(1, s2);  // default setting for bus2
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeVSourceObj = (VSourceObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeVSourceObj);

		VSourceObj elem = activeVSourceObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"VSource." +
						elem.getName() + "\"", 320);
				break;
			case 0:
				vSourceSetBus1(param);  // special handling of bus 1
				break;
			case 1:
				elem.setKVBase(parser.makeDouble());  // baseKV
				break;
			case 2:
				elem.setPerUnit(parser.makeDouble());  // pu
				break;
			case 3:
				elem.setAngle(parser.makeDouble());  // ang
				break;
			case 4:
				elem.setSrcFrequency(parser.makeDouble());  // freq
				break;
			case 5:
				elem.setNumPhases(parser.makeInteger());  // num phases
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
				break;
			case 6:
				elem.setMVAsc3(parser.makeDouble());  // MVAsc3
				break;
			case 7:
				elem.setMVAsc1(parser.makeDouble());  // MVAsc1
				break;
			case 8:
				elem.setX1R1(parser.makeDouble());  // X1/R1
				break;
			case 9:
				elem.setX0R0(parser.makeDouble());  // X0/R0
				break;
			case 10:
				elem.setIsc3(parser.makeDouble());
				break;
			case 11:
				elem.setIsc1(parser.makeDouble());
				break;
			case 12:
				elem.setR1(parser.makeDouble());
				break;
			case 13:
				elem.setX1(parser.makeDouble());
				break;
			case 14:
				elem.setR0(parser.makeDouble());
				break;
			case 15:
				elem.setX0(parser.makeDouble());
				break;
			case 16:
				switch (param.toUpperCase().charAt(0)) {
				case 'P':
					elem.setScanType(SequenceType.POS);
					break;
				case 'Z':
					elem.setScanType(SequenceType.ZERO);
					break;
				case 'N':
					elem.setScanType(SequenceType.NONE);
					break;
				default:
					DSS.doSimpleMsg("Unknown scan type for \"" + getClassName() +"."+ elem.getName() + "\": "+param, 321);
					break;
				}
				break;
			case 17:
				switch (param.toUpperCase().charAt(0)) {
				case 'P':
					elem.setSequenceType(SequenceType.POS);
					break;
				case 'Z':
					elem.setSequenceType(SequenceType.ZERO);
					break;
				case 'N':
					elem.setSequenceType(SequenceType.NONE);
					break;
				default:
					DSS.doSimpleMsg("Unknown sequence type for \"" + getClassName() +"."+ getClassName() + "\": "+param, 321);
					break;
				}
				break;
			case 18:
				elem.setBus(1, param);
				break;
			default:
				classEdit(activeVSourceObj, paramPointer - VSource.NumPropsThisClass);
				break;
			}

			// set the Z spec type switch depending on which was specified
			switch (paramPointer) {
			case 6:
				elem.setZspecType(ZSpecType.MVASC);
				break;
			case 7:
				elem.setZspecType(ZSpecType.MVASC);
				break;
			case 10:
				elem.setZspecType(ZSpecType.ISC);
				break;
			case 11:
				elem.setZspecType(ZSpecType.ISC);
				break;
			case 12:
				elem.setZspecType(ZSpecType.Z1Z0);
				break;
			case 13:
				elem.setZspecType(ZSpecType.Z1Z0);
				break;
			case 14:
				elem.setZspecType(ZSpecType.Z1Z0);
				break;
			case 15:
				elem.setZspecType(ZSpecType.Z1Z0);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		elem.recalcElementData();
		elem.setYPrimInvalid(true);

		return 0;
	}

	@Override
	protected int makeLike(String otherSource) {
		int success = 0;

		/* See if we can find this line name in the present collection */
		VSourceObj other = (VSourceObj) find(otherSource);
		if (other != null) {
			VSourceObj elem = activeVSourceObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // forces reallocation of terminal stuff

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);

				elem.setZ(new CMatrix(elem.getNumPhases()));
				elem.setZinv(new CMatrix(elem.getNumPhases()));
			}

			elem.getZ().copyFrom(other.getZ());
			// elem.getZinv().copyFrom(other.getZinv());
			elem.setVmag(other.getVmag());
			elem.setKVBase(other.getKVBase());
			elem.setPerUnit(other.getPerUnit());
			elem.setAngle(other.getAngle());
			elem.setSrcFrequency(other.getSrcFrequency());
			elem.setMVAsc3(other.getMVAsc3());
			elem.setMVAsc1(other.getMVAsc1());
			elem.setX1R1(other.getX1R1());
			elem.setX0R0(other.getX0R0());
			elem.setScanType(other.getScanType());
			elem.setSequenceType(other.getSequenceType());

			classMakeLike(other);

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in VSource makeLike: \"" + otherSource + "\" not found.", 322);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement VSource.init", -1);
		return 0;
	}

}
