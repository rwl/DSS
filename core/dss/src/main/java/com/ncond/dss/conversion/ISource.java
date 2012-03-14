package com.ncond.dss.conversion;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class ISource extends PCClass {

	public static final int NumPropsThisClass = 7;

	public static ISourceObj activeISourceObj;

	public ISource() {
		super();
		className = "ISource";
		classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM;  // don"t want this in PC element list

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = ISource.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0] = "bus1";
		propertyName[1] = "amps";
		propertyName[2] = "angle";
		propertyName[3] = "frequency";
		propertyName[4] = "phases";
		propertyName[5] = "scantype";
		propertyName[6] = "sequence";

		// define property help values
		propertyHelp[0] = "Name of bus to which source is connected."+DSS.CRLF+"bus1=busname"+DSS.CRLF+"bus1=busname.1.2.3";
		propertyHelp[1] = "Magnitude of current source, each phase, in Amps.";
		propertyHelp[2] = "Phase angle in degrees of first phase: e.g.,Angle=10.3."+DSS.CRLF+
				"Phase shift between phases is assumed 120 degrees when "+
				"number of phases <= 3";
		propertyHelp[3] = "Source frequency.  Defaults to  circuit fundamental frequency.";
		propertyHelp[4] = "Number of phases.  Defaults to 3. For 3 or less, phase shift is 120 degrees.";
		propertyHelp[5] = "{pos*| zero | none} Maintain specified sequence for harmonic solution. Default is positive sequence. "+
				"Otherwise, angle between phases rotates with harmonic.";
		propertyHelp[6] = "{pos*| neg | zero} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. "+
				"Default is positive sequence.";


		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list

		// override help string
		propertyHelp[NumPropsThisClass] = "Harmonic spectrum assumed for this source.  Default is \"default\".";
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new ISourceObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeISourceObj = (ISourceObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeISourceObj);

		ISourceObj elem  = activeISourceObj;

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
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 330);
				break;
			case 0:
				elem.setBus(0, param);
				break;
			case 1:
				elem.setAmps(parser.makeDouble());
				break;
			case 2:
				elem.setAngle(parser.makeDouble());  // ang
				break;
			case 3:
				elem.setSrcFrequency(parser.makeDouble()); // freq
				break;
			case 4:
				elem.setNumPhases(parser.makeInteger()); // num phases
				switch (elem.getNumPhases()) {
				case 1:
					elem.setPhaseShift(0.0);
					break;
				case 2:
					elem.setPhaseShift(120.0);
					break;
				case 3:
					elem.setPhaseShift(120.0);
					break;
				default:  // higher order systems
					elem.setPhaseShift(360.0 / elem.getNumPhases());
					break;
				}
				elem.setNumConds(elem.getNumPhases());  // force reallocation of terminal info
				break;
			case 5:
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
					DSS.doSimpleMsg("Unknown scan type for \"" + getClassName() + "." +
							elem.getName() + "\": "+param, 331);
					break;
				}
				break;
			case 6:
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
					DSS.doSimpleMsg("Unknown sequence type for \"" + getClassName() + "." +
							elem.getName() + "\": "+param, 331);
					break;
				}
				break;
			default:
				classEdit(activeISourceObj, paramPointer - ISource.NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param     = parser.makeString();
		}

		elem.recalcElementData();
		elem.setYPrimInvalid(true);

		return 0;
	}

	@Override
	protected int makeLike(String otherSource) {
		int success = 0;

		/* See if we can find this line name in the present collection */
		ISourceObj other = (ISourceObj) find(otherSource);

		if (other != null) {
			ISourceObj elem = activeISourceObj;

			if (elem.getNumPhases() != other.getNumPhases()) {
				elem.setNumPhases(other.getNumPhases());
				elem.setNumConds(elem.getNumPhases());  // forces reallocation of terminal stuff

				elem.setYOrder(elem.getNumConds() * elem.getNumTerms());
				elem.setYPrimInvalid(true);
			}

			elem.setAmps(other.getAmps());
			elem.setAngle(other.getAngle());
			elem.setSrcFrequency(other.getSrcFrequency());
			elem.setScanType(other.getScanType());
			elem.setSequenceType(other.getSequenceType());

			classMakeLike(other); // set spectrum, base frequency

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

			success = 1;
		} else {
			DSS.doSimpleMsg("Error in ISource makeLike: \"" + otherSource + "\" not found", 332);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement ISource.init", -1);
		return 0;
	}

}
