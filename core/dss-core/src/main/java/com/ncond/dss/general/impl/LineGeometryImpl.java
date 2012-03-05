package com.ncond.dss.general.impl;

import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSSClassImpl;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.general.LineGeometry;
import com.ncond.dss.general.LineGeometryObj;
import com.ncond.dss.general.LineSpacingObj;
import com.ncond.dss.general.impl.LineGeometryObjImpl.LineGeometryProblem;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.impl.CommandListImpl;
import com.ncond.dss.shared.impl.LineUnits;

public class LineGeometryImpl extends DSSClassImpl implements LineGeometry {

	public static LineGeometryObj activeLineGeometryObj;

	public LineGeometryImpl() {
		super();
		className = "LineGeometry";
		classType = DSSClassDefs.DSS_OBJECT;
		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

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
		DSS.activeDSSObject = new LineGeometryObjImpl(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		LineGeometryObj alg = activeLineGeometryObj;

		int istart, istop, result = 0;
		// continue parsing with contents of Parser
		activeLineGeometryObj = (LineGeometryObj) elementList.getActive();
		DSS.activeDSSObject = alg;

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
				alg.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Object \"" + getName() + "." + alg.getName() + "\"", 10101);
				break;
			case 0:
				alg.setNConds(parser.makeInteger());  // use property value to force reallocations
				break;
			case 1:
				alg.setNPhases(parser.makeInteger());
				break;
			case 2:
				alg.setActiveCond(parser.makeInteger());
				break;
			case 3:
				alg.getCondName()[alg.getActiveCond()] = param;
				if (alg.getPhaseChoice() == ConductorChoice.UNKNOWN)
					alg.changeLineConstantsType(ConductorChoice.OVERHEAD);
				break;
			case 4:
				alg.getX()[alg.getActiveCond()] = parser.makeDouble();
				break;
			case 5:
				alg.getY()[alg.getActiveCond()] = parser.makeDouble();
				break;
			case 6:
				alg.getUnits()[alg.getActiveCond()] = LineUnits.getUnitsCode(param);
				alg.setLastUnit( alg.getUnits()[alg.getActiveCond()] );
				break;
			case 7:
				alg.setNormAmps(parser.makeDouble());
				break;
			case 8:
				alg.setEmergAmps(parser.makeDouble());
				break;
			case 9:
				alg.setReduce(Util.interpretYesNo(param));
				break;
			case 10:
				alg.setSpacingType(parser.makeString());
				if (DSS.lineSpacingClass.setActive(alg.getSpacingType())) {
					LineSpacingImpl.activeLineSpacingObj = (LineSpacingObj) DSS.lineSpacingClass.getActiveObj();
					if (alg.getNConds() == LineSpacingImpl.activeLineSpacingObj.getNWires()) {
						alg.setLastUnit(LineSpacingImpl.activeLineSpacingObj.getUnits());
						for (int i = 0; i < alg.getNConds(); i++) {
							alg.getX()[i] = LineSpacingImpl.activeLineSpacingObj.getXCoord(i);
							alg.getY()[i] = LineSpacingImpl.activeLineSpacingObj.getYCoord(i);
							alg.getUnits()[i] = activeLineGeometryObj.getLastUnit();
						}
					} else {
						DSS.doSimpleMsg("LineSpacing object " + alg.getSpacingType() + " has the wrong number of wires.", 10103);
					}
				} else {
					DSS.doSimpleMsg("LineSpacing object " + alg.getSpacingType() + " has not been defined.", 10103);
				}
				break;
			case 12:
				alg.getCondName()[alg.getActiveCond()] = param;
				alg.changeLineConstantsType(ConductorChoice.CONCENTRIC_NEUTRAL);
				break;
			case 13:
				alg.getCondName()[alg.getActiveCond()] = param;
				alg.changeLineConstantsType(ConductorChoice.TAPE_SHIELD);
				break;
			case 11:
				istart = 0;
				istop = alg.getNConds() - 1;

				if (alg.getPhaseChoice() == ConductorChoice.UNKNOWN) {
					alg.changeLineConstantsType(ConductorChoice.OVERHEAD);
				} else {  // these are buried neutral wires
					istart = alg.getNPhases();
				}

				DSS.auxParser.setCmdString(parser.makeString());
				for (int i = istart; i <= istop; i++) {
					DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
					alg.getCondName()[i] = DSS.auxParser.makeString();

					DSS.wireDataClass.setCode(alg.getCondName()[i]);

					if (ConductorDataImpl.activeConductorDataObj != null) {
						alg.getConductorData()[i] = ConductorDataImpl.activeConductorDataObj;
						if (i == 0) {
							if (ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0)
								alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps());
							if (ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0)
								alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps());
						}
					} else {
						DSS.doSimpleMsg("WireData object \"" + alg.getCondName()[i] + "\" not defined. Must be previously defined.", 10103);
					}
				}
				break;
			case 14:
				istart = 0;
				istop = alg.getNConds() - 1;

				alg.changeLineConstantsType(ConductorChoice.CONCENTRIC_NEUTRAL);
				istop = alg.getNPhases() - 1;

				DSS.auxParser.setCmdString(parser.makeString());
				for (int i = istart; i <= istop; i++) {
					DSS.auxParser.getNextParam();  // ignore any parameter name not expecting any
					alg.getCondName()[i] = DSS.auxParser.makeString();

					DSS.CNDataClass.setCode(alg.getCondName()[i]);

					if (ConductorDataImpl.activeConductorDataObj != null) {
						alg.getConductorData()[i] = ConductorDataImpl.activeConductorDataObj;
						if (i == 0) {
							if (ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0)
								alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps());
							if (ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0)
								alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps());
						}
					} else {
						DSS.doSimpleMsg("CNData object \"" + alg.getCondName()[i] + "\" not defined. Must be previously defined.", 10103);
					}
				}
				break;
			case 15:
				istart = 0;
				istop = alg.getNConds() - 1;

				alg.changeLineConstantsType(ConductorChoice.TAPE_SHIELD);
				istop = alg.getNPhases() - 1;

				DSS.auxParser.setCmdString(parser.makeString());
				for (int i = istart; i < istop + 1; i++) {
					DSS.auxParser.getNextParam();  // ignore any parameter name  not expecting any
					alg.getCondName()[i] = DSS.auxParser.makeString();

					DSS.TSDataClass.setCode(alg.getCondName()[i]);

					if (ConductorDataImpl.activeConductorDataObj != null) {
						alg.getConductorData()[i] = ConductorDataImpl.activeConductorDataObj;
						if (i == 0) {
							if (ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0)
								alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps());
							if (ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0)
								alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps());
						}
					} else {
						DSS.doSimpleMsg("TSData object \"" + alg.getCondName()[i] + "\" not defined. Must be previously defined.", 10103);
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
				if (alg.getNPhases() > alg.getNConds())
					alg.setNPhases(alg.getNConds());
				break;
			case 2:
				if (alg.getActiveCond() < 0 || alg.getActiveCond() >= alg.getNConds())
					DSS.doSimpleMsg("Illegal cond= specification in line geometry:" + DSS.CRLF + parser.getCmdString(), 10102);
				break;
			case 3:
				DSS.wireDataClass.setCode(param);

				if (ConductorDataImpl.activeConductorDataObj != null) {
					alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.activeConductorDataObj;
					/* Default the current ratings for this geometry to the rating of the first conductor */
					if (alg.getActiveCond() == 0) {
						if (ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0)
							alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps());
						if (ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0)
							alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps());
					}
				} else {
					DSS.doSimpleMsg("WireData object \"" + param + "\" not defined. Must be previously defined.", 10103);
				}
				break;
			case 12:
				DSS.CNDataClass.setCode(param);

				if (ConductorDataImpl.activeConductorDataObj != null) {
					alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.activeConductorDataObj;
					/* Default the current ratings for this geometry to the rating of the first conductor */
					if (alg.getActiveCond() == 0) {
						if (ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0)
							alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps());
						if (ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0)
							alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps());
					}
				} else {
					DSS.doSimpleMsg("CNData object \"" + param + "\" not defined. Must be previously defined.", 10103);
				}
				break;
			case 13:
				DSS.TSDataClass.setCode(param);

				if (ConductorDataImpl.activeConductorDataObj != null) {
					alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.activeConductorDataObj;
					/* Default the current ratings for this geometry to the rating of the first conductor */
					if (alg.getActiveCond() == 0) {
						if (ConductorDataImpl.activeConductorDataObj.getNormAmps() > 0.0)
							alg.setNormAmps(ConductorDataImpl.activeConductorDataObj.getNormAmps());
						if (ConductorDataImpl.activeConductorDataObj.getEmergAmps() > 0.0)
							alg.setEmergAmps(ConductorDataImpl.activeConductorDataObj.getEmergAmps());
					}
				} else {
					DSS.doSimpleMsg("TSData object \"" + param + "\" not defined. Must be previously defined.", 10103);
				}
				break;
			}

			switch (paramPointer) {
			case 0:
				alg.setDataChanged(true);
				break;
			case 3:
				alg.setDataChanged(true);
				break;
			case 4:
				alg.setDataChanged(true);
				break;
			case 5:
				alg.setDataChanged(true);
				break;
			case 6:
				alg.setDataChanged(true);
				break;
			case 10:
				alg.setDataChanged(true);
				break;
			case 11:
				alg.setDataChanged(true);
				break;
			case 12:
				alg.setDataChanged(true);
				break;
			case 13:
				alg.setDataChanged(true);
				break;
			case 14:
				alg.setDataChanged(true);
				break;
			case 15:
				alg.setDataChanged(true);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}
		return result;
	}

	@Override
	protected int makeLike(String lineName) {

		int i, result = 0;
		/* See if we can find this line code in the present collection */
		LineGeometryObj otherLineGeometry = (LineGeometryObj) find(lineName);
		if (otherLineGeometry != null) {

			LineGeometryObj alg = activeLineGeometryObj;

			alg.setPhaseChoice(otherLineGeometry.getPhaseChoice());
			alg.setNConds(otherLineGeometry.getNWires());  // allocates
			alg.setNPhases(otherLineGeometry.getNPhases());
			alg.setSpacingType(otherLineGeometry.getSpacingType());
			for (i = 0; i < alg.getNConds(); i++)
				alg.getCondName()[i] = otherLineGeometry.getCondName()[i];
			for (i = 0; i < alg.getNConds(); i++)
				alg.getConductorData()[i] = otherLineGeometry.getConductorData()[i];
			for (i = 0; i < alg.getNConds(); i++)
				alg.getX()[i] = otherLineGeometry.getX()[i];
			for (i = 0; i < alg.getNConds(); i++)
				alg.getY()[i] = otherLineGeometry.getY()[i];
			for (i = 0; i < alg.getNConds(); i++)
				alg.getUnits()[i] = otherLineGeometry.getUnits()[i];
			alg.setDataChanged(true);
			alg.setNormAmps(otherLineGeometry.getNormAmps());
			alg.setEmergAmps(otherLineGeometry.getEmergAmps());

			try {
				alg.updateLineGeometryData(DSS.activeCircuit.getSolution().getFrequency());
			} catch (LineGeometryProblem e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (i = 0; i < alg.getParentClass().getNumProperties(); i++)
				alg.setPropertyValue(i, otherLineGeometry.getPropertyValue(i));
			result = 1;
		} else {
			DSS.doSimpleMsg("Error in LineGeometry makeLike: \"" + lineName + "\" not found.", 102);
		}

		return result;
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
		LineGeometryObj pLineGeo;
		activeLineGeometryObj = null;

		for (int i = 0; i < elementList.size(); i++) {
			pLineGeo = (LineGeometryObj) elementList.get(i);
			if (pLineGeo.getName().equalsIgnoreCase(value)) {
				activeLineGeometryObj = pLineGeo;
				return;
			}
		}

		DSS.doSimpleMsg("LineGeometry: \"" + value + "\" not found.", 103);
	}

}
