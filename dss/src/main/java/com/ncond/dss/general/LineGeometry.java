/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.LineUnits;

public class LineGeometry extends DSSClass {

	public static final int NumPropsThisClass = 16;

	public static LineGeometryObj activeLineGeometryObj;

	public LineGeometry() {
		super();
		className = "LineGeometry";
		classType = DSSClassDefs.DSS_OBJECT;
		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		final String CRLF = DSS.CRLF;

		numProperties = LineGeometry.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		propertyName[0]  = "nconds";
		propertyName[1]  = "nphases";
		propertyName[2]  = "cond";
		propertyName[3]  = "wire";
		propertyName[4]  = "x";
		propertyName[5]  = "h";
		propertyName[6]  = "units";
		propertyName[7]  = "normamps";
		propertyName[8]  = "emergamps";
		propertyName[9]  = "reduce";
		propertyName[10] = "spacing";
		propertyName[11] = "wires";
		propertyName[12] = "cncable";
		propertyName[13] = "tscable";
		propertyName[14] = "cncables";
		propertyName[15] = "tscables";

		propertyHelp[0] = "Number of conductors in this geometry. Default is 3. Triggers memory allocations. Define first!";
		propertyHelp[1] = "Number of phases. Default =3; All other conductors are considered neutrals and might be reduced out.";
		propertyHelp[2] = "Set this = number of the conductor you wish to define. Default is 1.";
		propertyHelp[3] = "Code from WireData. MUST BE PREVIOUSLY DEFINED. no default." + CRLF +
			"Specifies use of Overhead Line parameter calculation," + CRLF +
			"Unless Tape Shield cable previously assigned to phases, and this wire is a neutral.";
		propertyHelp[4] = "x coordinate.";
		propertyHelp[5] = "Height of conductor.";
		propertyHelp[6] = "Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is \"ft\", but defaults to last unit defined";
		propertyHelp[7] = "Normal ampacity, amperes for the line. Defaults to first conductor if not specified.";
		propertyHelp[8] = "Emergency ampacity, amperes. Defaults to first conductor if not specified.";
		propertyHelp[9] = "{Yes | No} Default = no. Reduce to Nphases (Kron Reduction). Reduce out neutrals.";
		propertyHelp[10] = "Reference to a LineSpacing for use in a line constants calculation." + CRLF +
			"Alternative to x, h, and units. MUST BE PREVIOUSLY DEFINED." + CRLF +
			"Must match \"nconds\" as previously defined for this geometry." + CRLF +
			"Must be used in conjunction with the Wires property.";
		propertyHelp[11] = "Array of WireData names for use in a line constants calculation." + CRLF +
			"Alternative to individual wire inputs. ALL MUST BE PREVIOUSLY DEFINED." + CRLF +
			"Must match \"nconds\" as previously defined for this geometry," + CRLF +
			"unless TSData or CNData were previously assigned to phases, and these wires are neutrals." + CRLF +
			"Must be used in conjunction with the Spacing property.";
		propertyHelp[12] = "Code from CNData. MUST BE PREVIOUSLY DEFINED. no default." + CRLF +
			"Specifies use of Concentric Neutral cable parameter calculation.";
		propertyHelp[13] = "Code from TSData. MUST BE PREVIOUSLY DEFINED. no default." + CRLF +
			"Specifies use of Tape Shield cable parameter calculation.";
		propertyHelp[14] = "Array of CNData names for cable parameter calculation." + CRLF +
			"All must be previously defined, and match \"nphases\" for this geometry." + CRLF +
			"You can later define \"nconds-nphases\" wires for bare neutral conductors.";
		propertyHelp[15] = "Array of TSData names for cable parameter calculation." + CRLF +
			"All must be previously defined, and match \"nphases\" for this geometry." + CRLF +
			"You can later define \"nconds-nphases\" wires for bare neutral conductors.";

		activeProperty = LineGeometry.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	/**
	 * Create a new object of this class and add to list.
	 */
	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new LineGeometryObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		int istart, istop;
		Parser parser = Parser.getInstance();

		LineGeometryObj elem = activeLineGeometryObj;

		// continue parsing with contents of Parser
		activeLineGeometryObj = (LineGeometryObj) elementList.getActive();
		DSS.activeDSSObject = elem;

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
						getClassName() + "." + elem.getName() + "\"", 10101);
				break;
			case 0:
				elem.setNConds(parser.makeInteger());  // use property value to force reallocations
				break;
			case 1:
				elem.setNPhases(parser.makeInteger());
				break;
			case 2:
				elem.setActiveCond(parser.makeInteger());
				break;
			case 3:
				elem.setCondName(elem.getActiveCond(), param);
				if (elem.getPhaseChoice() == ConductorChoice.UNKNOWN)
					elem.changeLineConstantsType(ConductorChoice.OVERHEAD);
				break;
			case 4:
				elem.setXcoord(elem.getActiveCond(), parser.makeDouble());
				break;
			case 5:
				elem.setYcoord(elem.getActiveCond(), parser.makeDouble());
				break;
			case 6:
				elem.setUnit(elem.getActiveCond(), LineUnits.interpretUnitsCode(param));
				elem.setLastUnit(elem.getUnit(elem.getActiveCond()));
				break;
			case 7:
				elem.setNormAmps(parser.makeDouble());
				break;
			case 8:
				elem.setEmergAmps(parser.makeDouble());
				break;
			case 9:
				elem.setReduce(Util.interpretYesNo(param));
				break;
			case 10:
				elem.setSpacingType(parser.makeString());
				if (DSS.lineSpacingClass.setActive(elem.getSpacingType())) {
					LineSpacing.activeLineSpacingObj = (LineSpacingObj) DSS.lineSpacingClass.getActiveObj();

					if (elem.getNConds() == LineSpacing.activeLineSpacingObj.getNWires()) {
						elem.setLastUnit(LineSpacing.activeLineSpacingObj.getUnits());
						for (int i = 0; i < elem.getNConds(); i++) {
							elem.setXcoord(i, LineSpacing.activeLineSpacingObj.getXCoord(i));
							elem.setYcoord(i, LineSpacing.activeLineSpacingObj.getYCoord(i));
							elem.setUnit(i, activeLineGeometryObj.getLastUnit());
						}
					} else {
						DSS.doSimpleMsg("LineSpacing object " + elem.getSpacingType() +
								" has the wrong number of wires.", 10103);
					}
				} else {
					DSS.doSimpleMsg("LineSpacing object " + elem.getSpacingType() +
							" has not been defined.", 10103);
				}
				break;
			case 12:
				elem.setCondName(elem.getActiveCond(), param);
				elem.changeLineConstantsType(ConductorChoice.CONCENTRIC_NEUTRAL);
				break;
			case 13:
				elem.setCondName(elem.getActiveCond(), param);
				elem.changeLineConstantsType(ConductorChoice.TAPE_SHIELD);
				break;
			case 11:
			case 14:
			case 15:
				istart = 0;
				istop = elem.getNConds() - 1;

				elem.changeLineConstantsType(ConductorChoice.TAPE_SHIELD);
				istop = elem.getNPhases() - 1;

				DSS.auxParser.setCmdBuffer(parser.makeString());
				for (int i = istart; i < istop; i++) {
					DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
					elem.setCondName(i, DSS.auxParser.makeString());

					DSS.TSDataClass.setCode(elem.getCondName(i));

					if (ConductorData.activeConductorDataObj != null) {
						elem.getConductorData()[i] = ConductorData.activeConductorDataObj;
						if (i == 0) {
							if (ConductorData.activeConductorDataObj.getNormAmps() > 0.0)
								elem.setNormAmps(ConductorData.activeConductorDataObj.getNormAmps());
							if (ConductorData.activeConductorDataObj.getEmergAmps() > 0.0)
								elem.setEmergAmps(ConductorData.activeConductorDataObj.getEmergAmps());
						}
					} else {
						DSS.doSimpleMsg("TSData object \"" + elem.getCondName(i) +
							"\" not defined. Must be previously defined.", 10103);
					}
				}
				break;
			default:
				// Inherited parameters
				classEdit(activeLineGeometryObj, paramPointer - LineGeometry.NumPropsThisClass);
				break;
			}

			/* Set defaults */
			switch (paramPointer) {
			case 1:
				if (elem.getNPhases() > elem.getNConds())
					elem.setNPhases(elem.getNConds());
				break;
			case 2:
				if (elem.getActiveCond() < 0 || elem.getActiveCond() >= elem.getNConds())
					DSS.doSimpleMsg("Illegal cond= specification in line geometry:" +
							DSS.CRLF + parser.getCmdBuffer(), 10102);
				break;
			case 3:
			case 12:
			case 13:
				DSS.TSDataClass.setCode(param);

				if (ConductorData.activeConductorDataObj != null) {
					elem.setConductorData(elem.getActiveCond(), ConductorData.activeConductorDataObj);

					/* Default the current ratings for this geometry to the rating of the first conductor */
					if (elem.getActiveCond() == 0) {
						if (ConductorData.activeConductorDataObj.getNormAmps() > 0.0)
							elem.setNormAmps(ConductorData.activeConductorDataObj.getNormAmps());
						if (ConductorData.activeConductorDataObj.getEmergAmps() > 0.0)
							elem.setEmergAmps(ConductorData.activeConductorDataObj.getEmergAmps());
					}
				} else {
					DSS.doSimpleMsg("TSData object \"" + param + "\" not defined. Must be previously defined.", 10103);
				}
				break;
			}

			switch (paramPointer) {
			case 0:
			case 3:
			case 4:
			case 5:
			case 6:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
				elem.setDataChanged(true);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}
		return 0;
	}

	@Override
	protected int makeLike(String lineName) {
		int i, success = 0;

		/* See if we can find this line code in the present collection */
		LineGeometryObj other = (LineGeometryObj) find(lineName);

		if (other != null) {
			LineGeometryObj elem = activeLineGeometryObj;

			elem.setPhaseChoice(other.getPhaseChoice());
			elem.setNConds(other.getNWires());  // allocates
			elem.setNPhases(other.getNPhases());
			elem.setSpacingType(other.getSpacingType());
			for (i = 0; i < elem.getNConds(); i++)
				elem.setCondName(i, other.getCondName(i));
			for (i = 0; i < elem.getNConds(); i++)
				elem.setConductorData(i, other.getConductorData(i));
			for (i = 0; i < elem.getNConds(); i++)
				elem.setXcoord(i, other.getXCoord(i));
			for (i = 0; i < elem.getNConds(); i++)
				elem.setYcoord(i, other.getYCoord(i));
			for (i = 0; i < elem.getNConds(); i++)
				elem.setUnit(i, other.getUnit(i));
			elem.setDataChanged(true);
			elem.setNormAmps(other.getNormAmps());
			elem.setEmergAmps(other.getEmergAmps());

			try {
				elem.updateLineGeometryData(DSS.activeCircuit.getSolution().getFrequency());
			} catch (LineGeometryProblem e) {
				DSS.doSimpleMsg("Error updating line geometry data.", -1);
			}

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in LineGeometry makeLike: \"" +
					lineName + "\" not found.", 102);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement LineGeometry.init()", -1);
		return 0;
	}

	public String getCode() {
		LineGeometryObj active = (LineGeometryObj) elementList.getActive();
		return active.getName();
	}

	public void setCode(String value) {
		LineGeometryObj elem;
		activeLineGeometryObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			elem = (LineGeometryObj) elementList.get(i);
			if (elem.getName().equalsIgnoreCase(value)) {
				activeLineGeometryObj = elem;
				return;
			}
		}

		DSS.doSimpleMsg("LineGeometry: \"" + value + "\" not found.", 103);
	}

}
