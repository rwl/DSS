package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.impl.LineGeometryObjImpl.LineGeometryProblem;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.LineUnits;

public class LineGeometryImpl extends DSSClassImpl implements LineGeometry {

	private static LineGeometryObj ActiveLineGeometryObj;

	public LineGeometryImpl() {
		super();
		Class_Name    = "LineGeometry";
		DSSClassType  = DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		String CRLF = DSSGlobals.CRLF;

		NumProperties = LineGeometry.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		PropertyName[0]  = "nconds";
		PropertyName[1]  = "nphases";
		PropertyName[2]  = "cond";
		PropertyName[3]  = "wire";
		PropertyName[4]  = "x";
		PropertyName[5]  = "h";
		PropertyName[6]  = "units";
		PropertyName[7]  = "normamps";
		PropertyName[8]  = "emergamps";
		PropertyName[9]  = "reduce";
		PropertyName[10] = "spacing";
		PropertyName[11] = "wires";
		PropertyName[12] = "cncable";
		PropertyName[13] = "tscable";
		PropertyName[14] = "cncables";
		PropertyName[15] = "tscables";

		PropertyHelp[0] = "Number of conductors in this geometry. Default is 3. Triggers memory allocations. Define first!";
		PropertyHelp[1] = "Number of phases. Default =3; All other conductors are considered neutrals and might be reduced out.";
		PropertyHelp[2] = "Set this = number of the conductor you wish to define. Default is 1.";
		PropertyHelp[3] = "Code from WireData. MUST BE PREVIOUSLY DEFINED. no default." + CRLF +
			"Specifies use of Overhead Line parameter calculation," + CRLF +
			"Unless Tape Shield cable previously assigned to phases, and this wire is a neutral.";
		PropertyHelp[4] = "x coordinate.";
		PropertyHelp[5] = "Height of conductor.";
		PropertyHelp[6] = "Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is \"ft\", but defaults to last unit defined";
		PropertyHelp[7] = "Normal ampacity, amperes for the line. Defaults to first conductor if not specified.";
		PropertyHelp[8] = "Emergency ampacity, amperes. Defaults to first conductor if not specified.";
		PropertyHelp[9] = "{Yes | No} Default = no. Reduce to Nphases (Kron Reduction). Reduce out neutrals.";
		PropertyHelp[10] = "Reference to a LineSpacing for use in a line constants calculation." + CRLF +
							"Alternative to x, h, and units. MUST BE PREVIOUSLY DEFINED." + CRLF +
							"Must match \"nconds\" as previously defined for this geometry." + CRLF +
							"Must be used in conjunction with the Wires property.";
		PropertyHelp[11] = "Array of WireData names for use in a line constants calculation." + CRLF +
							"Alternative to individual wire inputs. ALL MUST BE PREVIOUSLY DEFINED." + CRLF +
							"Must match \"nconds\" as previously defined for this geometry," + CRLF +
							"unless TSData or CNData were previously assigned to phases, and these wires are neutrals." + CRLF +
							"Must be used in conjunction with the Spacing property.";
		PropertyHelp[12] = "Code from CNData. MUST BE PREVIOUSLY DEFINED. no default." + CRLF +
				"Specifies use of Concentric Neutral cable parameter calculation.";
		PropertyHelp[13] = "Code from TSData. MUST BE PREVIOUSLY DEFINED. no default." + CRLF +
				"Specifies use of Tape Shield cable parameter calculation.";
		PropertyHelp[14] = "Array of CNData names for cable parameter calculation." + CRLF +
				"All must be previously defined, and match \"nphases\" for this geometry." + CRLF +
				"You can later define \"nconds-nphases\" wires for bare neutral conductors.";
		PropertyHelp[15] = "Array of TSData names for cable parameter calculation." + CRLF +
				"All must be previously defined, and match \"nphases\" for this geometry." + CRLF +
				"You can later define \"nconds-nphases\" wires for bare neutral conductors.";


		ActiveProperty = LineGeometry.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	/**
	 * Create a new object of this class and add to list.
	 */
	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new LineGeometryObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		LineGeometryObj alg = getActiveLineGeometryObj();

		int istart, istop, Result = 0;
		// continue parsing with contents of Parser
		setActiveLineGeometryObj((LineGeometryObj) ElementList.getActive());
		Globals.setActiveDSSObject(alg);

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();

		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);

				if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
					alg.setPropertyValue(ParamPointer, Param);

				switch (ParamPointer) {
				case -1:
					Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() + "." + alg.getName() + "\"", 10101);
					break;
				case 0:
					alg.setNconds(parser.makeInteger());  // Use property value to force reallocations
					break;
				case 1:
					alg.setNphases(parser.makeInteger());
					break;
				case 2:
					alg.setActiveCond(parser.makeInteger());
					break;
				case 3:
					alg.getCondName()[alg.getActiveCond()] = Param;
					if (alg.getPhaseChoice() == ConductorChoice.Unknown)
						alg.changeLineConstantsType(ConductorChoice.Overhead);
					break;
				case 4:
					alg.getX()[alg.getActiveCond()] = parser.makeDouble();
					break;
				case 5:
					alg.getY()[alg.getActiveCond()] = parser.makeDouble();
					break;
				case 6:
					alg.getUnits()[alg.getActiveCond()] = LineUnits.getUnitsCode(Param);
					alg.setLastUnit( alg.getUnits()[alg.getActiveCond()] );
					break;
				case 7:
					alg.setNormAmps(parser.makeDouble());
					break;
				case 8:
					alg.setEmergAmps(parser.makeDouble());
					break;
				case 9:
					alg.setReduce(Utilities.interpretYesNo(Param));
					break;
				case 10:
					alg.setSpacingType(parser.makeString());
					if (Globals.getLineSpacingClass().setActive(alg.getSpacingType())) {
						LineSpacingImpl.setActiveLineSpacingObj((LineSpacingObj) Globals.getLineSpacingClass().getActiveObj());
						if (alg.getNconds() == LineSpacingImpl.getActiveLineSpacingObj().getNWires()) {
							alg.setLastUnit(LineSpacingImpl.getActiveLineSpacingObj().getUnits());
							for (int i = 0; i < alg.getNconds(); i++) {
								alg.getX()[i] = LineSpacingImpl.getActiveLineSpacingObj().getXcoord(i);
								alg.getY()[i] = LineSpacingImpl.getActiveLineSpacingObj().getYcoord(i);
								alg.getUnits()[i] = getActiveLineGeometryObj().getLastUnit();
							}
						} else {
							Globals.doSimpleMsg("LineSpacing object " + alg.getSpacingType() + " has the wrong number of wires.", 10103);
						}
					} else {
						Globals.doSimpleMsg("LineSpacing object " + alg.getSpacingType() + " has not been defined.", 10103);
					}
					break;
				case 12:
					alg.getCondName()[alg.getActiveCond()] = Param;
					alg.changeLineConstantsType(ConductorChoice.ConcentricNeutral);
					break;
				case 13:
					alg.getCondName()[alg.getActiveCond()] = Param;
					alg.changeLineConstantsType(ConductorChoice.TapeShield);
					break;
				case 11:
					istart = 0;
					istop = alg.getNconds() - 1;  // TODO Check zero based indexing

					if (alg.getPhaseChoice() == ConductorChoice.Unknown) {
						alg.changeLineConstantsType(ConductorChoice.Overhead);
					} else {  // these are buried neutral wires
						istart = alg.getNphases();  // TODO Check zero based indexing
					}

					Globals.getAuxParser().setCmdString(parser.makeString());
					for (int i = istart; i < istop + 1; i++) {
						Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
						alg.getCondName()[i] = Globals.getAuxParser().makeString();

						Globals.getWireDataClass().setCode(alg.getCondName()[i]);

						if (ConductorDataImpl.getActiveConductorDataObj() != null) {
							alg.getConductorData()[i] = ConductorDataImpl.getActiveConductorDataObj();
							if (i == 0) {
								if (ConductorDataImpl.getActiveConductorDataObj().getNormAmps() > 0.0)
									alg.setNormAmps(ConductorDataImpl.getActiveConductorDataObj().getNormAmps());
								if (ConductorDataImpl.getActiveConductorDataObj().getEmergAmps() > 0.0)
									alg.setEmergAmps(ConductorDataImpl.getActiveConductorDataObj().getEmergAmps());
							}
						} else {
							Globals.doSimpleMsg("WireData Object \"" + alg.getCondName()[i] + "\" not defined. Must be previously defined.", 10103);
						}
					}
					break;
				case 14:
					istart = 0;
					istop = alg.getNconds() - 1;  // TODO Check zero based indexing

					alg.changeLineConstantsType(ConductorChoice.ConcentricNeutral);
					istop = alg.getNphases() - 1;  // TODO Check zero based indexing

					Globals.getAuxParser().setCmdString(parser.makeString());
					for (int i = istart; i < istop + 1; i++) {
						Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
						alg.getCondName()[i] = Globals.getAuxParser().makeString();

						Globals.getCNDataClass().setCode(alg.getCondName()[i]);

						if (ConductorDataImpl.getActiveConductorDataObj() != null) {
							alg.getConductorData()[i] = ConductorDataImpl.getActiveConductorDataObj();
							if (i == 0) {
								if (ConductorDataImpl.getActiveConductorDataObj().getNormAmps() > 0.0)
									alg.setNormAmps(ConductorDataImpl.getActiveConductorDataObj().getNormAmps());
								if (ConductorDataImpl.getActiveConductorDataObj().getEmergAmps() > 0.0)
									alg.setEmergAmps(ConductorDataImpl.getActiveConductorDataObj().getEmergAmps());
							}
						} else {
							Globals.doSimpleMsg("CNData Object \"" + alg.getCondName()[i] + "\" not defined. Must be previously defined.", 10103);
						}
					}
					break;
				case 15:
					istart = 0;
					istop = alg.getNconds() - 1;  // TODO Check zero based indexing

					alg.changeLineConstantsType(ConductorChoice.TapeShield);
					istop = alg.getNphases();  // TODO Check zero based indexing

					Globals.getAuxParser().setCmdString(parser.makeString());
					for (int i = istart; i < istop + 1; i++) {
						Globals.getAuxParser().getNextParam();  // ignore any parameter name  not expecting any
						alg.getCondName()[i] = Globals.getAuxParser().makeString();

						Globals.getTSDataClass().setCode(alg.getCondName()[i]);

						if (ConductorDataImpl.getActiveConductorDataObj() != null) {
							alg.getConductorData()[i] = ConductorDataImpl.getActiveConductorDataObj();
							if (i == 0) {
								if (ConductorDataImpl.getActiveConductorDataObj().getNormAmps() > 0.0)
									alg.setNormAmps(ConductorDataImpl.getActiveConductorDataObj().getNormAmps());
								if (ConductorDataImpl.getActiveConductorDataObj().getEmergAmps() > 0.0)
									alg.setEmergAmps(ConductorDataImpl.getActiveConductorDataObj().getEmergAmps());
							}
						} else {
							Globals.doSimpleMsg("TSData Object \"" + alg.getCondName()[i] + "\" not defined. Must be previously defined.", 10103);
						}
					}
					break;
				default:
					// Inherited parameters
					classEdit(getActiveLineGeometryObj(), ParamPointer - LineGeometry.NumPropsThisClass);
					break;
				}

				/* Set defaults */
				switch (ParamPointer) {
				case 1:
					if (alg.getNphases() > alg.getNconds())
						alg.setNphases(alg.getNconds());
					break;
				case 2:
					if ((alg.getActiveCond() < 1) || (alg.getActiveCond() > alg.getNconds()))
						Globals.doSimpleMsg("Illegal cond= specification in Line Geometry:" + DSSGlobals.CRLF + parser.getCmdString(), 10102);
					break;
				case 3:
					Globals.getWireDataClass().setCode(Param);

					if (ConductorDataImpl.getActiveConductorDataObj() != null) {
						alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.getActiveConductorDataObj();
						/* Default the current ratings for this geometry to the rating of the first conductor */
						if (alg.getActiveCond() == 1) {  // TODO Check zero based indexing
							if (ConductorDataImpl.getActiveConductorDataObj().getNormAmps() > 0.0)
								alg.setNormAmps(ConductorDataImpl.getActiveConductorDataObj().getNormAmps());
							if (ConductorDataImpl.getActiveConductorDataObj().getEmergAmps() > 0.0)
								alg.setEmergAmps(ConductorDataImpl.getActiveConductorDataObj().getEmergAmps());
						}
					} else {
						Globals.doSimpleMsg("WireData Object \"" + Param + "\" not defined. Must be previously defined.", 10103);
					}
					break;
				case 12:
					Globals.getCNDataClass().setCode(Param);

					if (ConductorDataImpl.getActiveConductorDataObj() != null) {
						alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.getActiveConductorDataObj();
						/* Default the current ratings for this geometry to the rating of the first conductor */
						if (alg.getActiveCond() == 1) {  // TODO Check zero based indexing
							if (ConductorDataImpl.getActiveConductorDataObj().getNormAmps() > 0.0)
								alg.setNormAmps(ConductorDataImpl.getActiveConductorDataObj().getNormAmps());
							if (ConductorDataImpl.getActiveConductorDataObj().getEmergAmps() > 0.0)
								alg.setEmergAmps(ConductorDataImpl.getActiveConductorDataObj().getEmergAmps());
						}
					} else {
						Globals.doSimpleMsg("CNData Object \"" + Param + "\" not defined. Must be previously defined.", 10103);
					}
					break;
				case 13:
					Globals.getTSDataClass().setCode(Param);

					if (ConductorDataImpl.getActiveConductorDataObj() != null) {
						alg.getConductorData()[alg.getActiveCond()] = ConductorDataImpl.getActiveConductorDataObj();
						/* Default the current ratings for this geometry to the rating of the first conductor */
						if (alg.getActiveCond() == 1) {  // TODO Check zero based indexing
							if (ConductorDataImpl.getActiveConductorDataObj().getNormAmps() > 0.0)
								alg.setNormAmps(ConductorDataImpl.getActiveConductorDataObj().getNormAmps());
							if (ConductorDataImpl.getActiveConductorDataObj().getEmergAmps() > 0.0)
								alg.setEmergAmps(ConductorDataImpl.getActiveConductorDataObj().getEmergAmps());
						}
					} else {
						Globals.doSimpleMsg("TSData Object \"" + Param + "\" not defined. Must be previously defined.", 10103);
					}
					break;
				}

				switch (ParamPointer) {
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

				ParamName = parser.getNextParam();
				Param = parser.makeString();
			}
		}
		return Result;
	}

	@Override
	protected int makeLike(String LineName) {

		int i, Result = 0;
		/* See if we can find this line code in the present collection */
		LineGeometryObj OtherLineGeometry = (LineGeometryObj) find(LineName);
		if (OtherLineGeometry != null) {

			LineGeometryObj alg = getActiveLineGeometryObj();

			alg.setPhaseChoice(OtherLineGeometry.getPhaseChoice());
			alg.setNconds(OtherLineGeometry.getNWires());   // allocates
			alg.setNphases(OtherLineGeometry.getNphases());
			alg.setSpacingType(OtherLineGeometry.getSpacingType());
			for (i = 0; i < alg.getNconds(); i++)
				alg.getCondName()[i] = OtherLineGeometry.getCondName()[i];
			for (i = 0; i < alg.getNconds(); i++)
				alg.getConductorData()[i] = OtherLineGeometry.getConductorData()[i];
			for (i = 0; i < alg.getNconds(); i++)
				alg.getX()[i] = OtherLineGeometry.getX()[i];
			for (i = 0; i < alg.getNconds(); i++)
				alg.getY()[i] = OtherLineGeometry.getY()[i];
			for (i = 0; i < alg.getNconds(); i++)
				alg.getUnits()[i] = OtherLineGeometry.getUnits()[i];
			alg.setDataChanged(true);
			alg.setNormAmps(OtherLineGeometry.getNormAmps());
			alg.setEmergAmps(OtherLineGeometry.getEmergAmps());

			try {
				alg.updateLineGeometryData(DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency());
			} catch (LineGeometryProblem e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (i = 0; i < alg.getParentClass().getNumProperties(); i++)
				alg.setPropertyValue(i, OtherLineGeometry.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in LineGeometry MakeLike: \"" + LineName + "\" Not Found.", 102);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement LineGeometry.init()", -1);
		return 0;
	}

	public String getCode() {
		LineGeometryObj active = (LineGeometryObj) ElementList.getActive();
		return active.getName();
	}

	public void setCode(String Value) {
		setActiveLineGeometryObj(null);

		for (int i = 0; i < ElementList.size(); i++) {
			LineGeometryObj pLineGeo = (LineGeometryObj) ElementList.get(i);
			if (pLineGeo.getName().equalsIgnoreCase(Value)) {
				setActiveLineGeometryObj(pLineGeo);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("LineGeometry: \"" + Value + "\" not Found.", 103);
	}

	public static LineGeometryObj getActiveLineGeometryObj() {
		return ActiveLineGeometryObj;
	}

	public static void setActiveLineGeometryObj(LineGeometryObj activeLineGeometryObj) {
		ActiveLineGeometryObj = activeLineGeometryObj;
	}

}
