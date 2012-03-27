/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.LineUnits;

/**
 * The LineGeometry object is a general DSS object used by all circuits
 * as a reference for obtaining line impedances.
 *
 * The values are set by the normal "new" and "edit" procedures for any DSS object.
 *
 * The values are retrieved by setting the code property in the LineGeometry
 * class. This sets the active LineGeometry object to be the one referenced by
 * the code property;
 *
 * Then the values of that code can be retrieved via the public variables.
 *
 */
public class LineGeometryObj extends DSSObject {

	private ConductorChoice phaseChoice;
	protected int nConds;
	protected int nPhases;
	private String[] condNames;
	private ConductorDataObj[] conductorData;
	private double[] X;
	private double[] Y;
	private LineUnits[] units;
	private LineUnits lastUnit;
	private boolean dataChanged;
	private boolean reduce;
	private int activeCondIdx;
	protected String spacingType;

	private LineConstants lineData;

	protected double normAmps;
	protected double emergAmps;

	public LineGeometryObj(DSSClass parClass, String lineGeometryName) {
		super(parClass);

		setName(lineGeometryName.toLowerCase());
		objType = parClass.getClassType();

		dataChanged = true;

		phaseChoice = ConductorChoice.UNKNOWN;
		condNames = null;
		conductorData = null;
		X = null;
		Y = null;
		units = null;
		lineData = null;
		spacingType = "";

		setNConds(3);  // allocates terminals
		nPhases = 3;
		setActiveCondIdx(0);

		lastUnit  = LineUnits.FT;
		normAmps  = 0.0;
		emergAmps = 0.0;

		reduce = false;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < 2; i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		for (int j = 0; j < nConds; j++) {
			setActiveCondIdx(j);
			pw.println("~ " + parentClass.getPropertyName(2) + "=" + getPropertyValue(2));
			pw.println("~ " + parentClass.getPropertyName(3) + "=" + getPropertyValue(3));
			pw.println("~ " + parentClass.getPropertyName(4) + "=" + getPropertyValue(4));
			pw.println("~ " + parentClass.getPropertyName(5) + "=" + getPropertyValue(5));
			pw.println("~ " + parentClass.getPropertyName(6) + "=" + getPropertyValue(6));
		}

		for (int i = 7; i < parentClass.getNumProperties(); i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));

		pw.close();
	}

	@Override
	public String getPropertyValue(int index) {
		String val;

		switch (index) {
		case 2:
			val = String.format("%d", activeCondIdx);
			break;
		case 3:
		case 12:
		case 13:
			val = condNames[activeCondIdx];
			break;
		case 4:
			val = String.format("%g", X[activeCondIdx]);
			break;
		case 5:
			val = String.format("%g", Y[activeCondIdx]);
			break;
		case 6:
			val = LineUnits.lineUnitsStr(units[activeCondIdx]);
			break;
		case 11:
		case 14:
		case 15:
			StringBuilder sb = new StringBuilder("[");
			for (int i = 0; i < nConds; i++) {
				sb.append(condNames[i]);
				sb.append(" ");
			}
			sb.append("]");
			val = sb.toString();
			break;
		default:
			// inherited parameters
			val = super.getPropertyValue(index);
			break;
		}

		return val;
	}

	public double getXCoord(int i) {
		return i < nConds ? X[i] : 0.0;
	}

	public double getYCoord(int i) {
		return i < nConds ? Y[i] : 0.0;
	}

	public void setXcoord(int idx, double val) {
		X[idx] = val;
	}

	public void setYcoord(int idx, double val) {
		Y[idx] = val;
	}

	public String getConductorName(int i) {
		return i < nConds ? condNames[i] : "";
	}

	public void setConductorData(int idx, ConductorDataObj name) {
		conductorData[idx] = name;
	}

	public ConductorDataObj getConductorData(int i) {
		return i < nConds ? conductorData[i] : null;
	}

	public ConductorDataObj[] getConductorData() {
		return conductorData;
	}

	public int getNConds() {
		return reduce ? nPhases : nConds;
	}

	public double getRhoEarth() {
		return lineData.getRhoEarth();
	}

	public CMatrix getYcMatrix(double f, double length, LineUnits units) {
		CMatrix Yc = null;

		if (dataChanged) {
			try {
				updateLineGeometryData(f);
			} catch (LineGeometryProblem e) {
				DSS.doSimpleMsg("Error updating line geometry data.", -1);
			}
		}

		if (!DSS.solutionAbort)
			Yc = lineData.getYcMatrix(f, length, units);

		return Yc;
	}

	public CMatrix getZMatrix(double f, double length, LineUnits units) {
		CMatrix Z = null;

		if (dataChanged) {
			try {
				updateLineGeometryData(f);
			} catch (LineGeometryProblem e) {
				DSS.doSimpleMsg("Error updating line geometry data.", -1);
			}
		}

		if (!DSS.solutionAbort)
			Z = lineData.getZMatrix(f, length, units);

		return Z;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "3");
		setPropertyValue(1, "3");
		setPropertyValue(2, "1");
		setPropertyValue(3, "");
		setPropertyValue(4, "0");
		setPropertyValue(5, "32");
		setPropertyValue(6, "ft");
		setPropertyValue(7, "0");
		setPropertyValue(8, "0");

		super.initPropertyValues(LineGeometry.NumPropsThisClass - 1);
	}

	/**
	 * Overrides standard saveWrite.
	 *
	 * LineGeometry structure not conducive to standard means of saving.
	 */
	@Override
	public void saveWrite(PrintWriter f) {
		int i, iProp;

		/* Write only properties that were explicitly set in the
		 * final order they were actually set. */

		iProp = getNextPropertySet(-1);  // works on activeDSSObject

		if (iProp >= 0) f.println();

		while (iProp >= 0) {
			switch (parentClass.getRevPropertyIdxMap(iProp)) {
			case 2:
			case 10:
			case 11:  // if cond=, spacing, or wires were ever used write out arrays ...
				for (i = 0; i < nConds; i++) {
					f.printf("~ cond=%d wire=%s X=%.7g h=%.7g units=%s",
						i, condNames[i], X[i], Y[i], LineUnits.lineUnitsStr(units[i]));
					f.println();
				}
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				// do nothing
				break;
			case 7:
				f.println(String.format("~ normamps=%.4g", normAmps));
				break;
			case 8:
				f.println(String.format("~ emergamps=%.4g", emergAmps));
				break;
			case 9:
				if (reduce) f.println("~ reduce=Yes");
				break;
			default:
				f.printf("~ %s=%s",
					parentClass.getPropertyName(parentClass.getRevPropertyIdxMap(iProp)),
					Util.checkForBlanks(propertyValues[iProp]));
				f.println();
				break;
			}
			iProp = getNextPropertySet(iProp);
		}
	}

	public void setActiveCondIdx(int value) {
		if (value >= 0 && value < nConds) {
			activeCondIdx = value;
			if (units[activeCondIdx] == null) {
				// makes this a sticky value so you don't have to repeat it
				units[activeCondIdx] = lastUnit;
			}
		}
	}

	protected void changeLineConstantsType(ConductorChoice newPhaseChoice) {
		LineConstants newLineData = null;
		boolean needNew;

		needNew = newPhaseChoice != phaseChoice;

		if (lineData == null) {
			needNew = true;
		} else if (nConds != lineData.getNumConds()) {
			needNew = true;
		}

		if (needNew) {
			switch (newPhaseChoice) {
			case OVERHEAD:
				newLineData = new OHLineConstants(nConds);
				break;
			case CONCENTRIC_NEUTRAL:
				newLineData = new CNLineConstants(nConds);
				break;
			case TAPE_SHIELD:
				newLineData = new TSLineConstants(nConds);
				break;
			}
		}

		if (newLineData != null) {
			if (lineData != null) {
				newLineData.setNPhases(lineData.getNPhases());
				newLineData.setRhoEarth(lineData.getRhoEarth());
			} else {
				lineData = null;
				lineData = newLineData;
			}
		}
		phaseChoice = newPhaseChoice;
	}

	public void setNConds(int value) {
		nConds = value;
		lineData = null;
		changeLineConstantsType(phaseChoice);
		condNames = new String[nConds];

		/* Allocations */
		conductorData = new ConductorDataObj[nConds];
		X = new double[nConds];
		Y = new double[nConds];
		units = new LineUnits[nConds];
		for (int i = 0; i < nConds; i++)
			units[i] = LineUnits.FT;  // default to ft
		lastUnit = LineUnits.FT;
	}

	public void setNPhases(int value) {
		nPhases = value;
		lineData.setNPhases(value);
	}

	public void setRhoEarth(double value) {
		lineData.setRhoEarth(value);
	}

	/**
	 * Call this before using the line data.
	 * @throws LineGeometryProblem
	 */
	protected void updateLineGeometryData(double f) throws LineGeometryProblem {
		CNDataObj cnd;
		TSDataObj tsd;
		CNLineConstants cnlc;
		TSLineConstants tslc;

		for (int i = 0; i < nConds; i++) {
			lineData.setX(i, units[i], X[i]);
			lineData.setY(i, units[i], Y[i]);
			lineData.setRadius(i, conductorData[i].getRadiusUnits(), conductorData[i].getRadius());
			lineData.setGMR(i, conductorData[i].getGmrUnits(), conductorData[i].getGmr60());
			lineData.setRdc(i, conductorData[i].getResistanceUnits(), conductorData[i].getRdc());
			lineData.setRac(i, conductorData[i].getResistanceUnits(), conductorData[i].getR60());

			if (conductorData[i] instanceof CNDataObj) {
				cnlc = (CNLineConstants) lineData;
				cnd = (CNDataObj) conductorData[i];
				cnlc.setEpsR(i, cnd.getEpsR());
				cnlc.setInsLayer(i, cnd.getRadiusUnits(), cnd.getInsLayer());
				cnlc.setDiaIns(i, cnd.getRadiusUnits(), cnd.getDiaIns());
				cnlc.setDiaCable(i, cnd.getRadiusUnits(), cnd.getDiaCable());
				cnlc.setKStrand(i, cnd.getKStrand());
				cnlc.setDiaStrand(i, cnd.getRadiusUnits(), cnd.getDiaStrand());
				cnlc.setGmrStrand(i, cnd.getGmrUnits(), cnd.getGmrStrand());
				cnlc.setRStrand(i, cnd.getResistanceUnits(), cnd.getRStrand());
			} else if (conductorData[i] instanceof TSDataObj) {
				tslc = (TSLineConstants) lineData;
				tsd = (TSDataObj) conductorData[i];
				tslc.setEpsR(i, tsd.getEpsR());
				tslc.setInsLayer(i, tsd.getRadiusUnits(), tsd.getInsLayer());
				tslc.setDiaIns(i, tsd.getRadiusUnits(), tsd.getDiaIns());
				tslc.setDiaCable(i, tsd.getRadiusUnits(), tsd.getDiaCable());
				tslc.setDiaShield(i, tsd.getRadiusUnits(), tsd.getDiaShield());
				tslc.setTapeLayer(i, tsd.getRadiusUnits(), tsd.getTapeLayer());
				tslc.setTapeLap(i, tsd.getTapeLap());
			}
		}

		lineData.setNPhases(nPhases);
		dataChanged = false;

		/* Before we calc, check for bad conductor definitions */
		String[] lineGeomErrMsg = new String[] {""};
		if (lineData.conductorsInSameSpace(lineGeomErrMsg)) {
			DSS.solutionAbort = true;
			throw new LineGeometryProblem("Error in LineGeometry." + getName() + ": " + lineGeomErrMsg[0]);
		} else {
			lineData.calc(f);
			if (reduce) lineData.reduce();  // reduce out neutrals
		}
	}

	/**
	 * Called from a line object that has its own spacing and wires input
	 * automatically sets reduce=y if the spacing has more wires than phases.
	 */
	public void loadSpacingAndWires(LineSpacingObj spc, ConductorDataObj[] wires) {
		int i;
		ConductorChoice newPhaseChoice;

		nConds = spc.getNWires();  // allocates
		nPhases = spc.getNPhases();
		spacingType = spc.getName();
		if (nConds > nPhases) reduce = true;

		newPhaseChoice = ConductorChoice.OVERHEAD;
		for (i = 0; i < nConds; i++) {
			if (wires[i] instanceof CNDataObj) {
				newPhaseChoice = ConductorChoice.CONCENTRIC_NEUTRAL;
			}
			if (wires[i] instanceof TSDataObj) {
				newPhaseChoice = ConductorChoice.TAPE_SHIELD;
			}
		}
		changeLineConstantsType(newPhaseChoice);

		for (i = 0; i < nConds; i++)
			condNames[i] = wires[i].getName();
		for (i = 0; i < nConds; i++)
			conductorData[i] = wires[i];
		for (i = 0; i < nConds; i++)
			X[i] = spc.getXCoord(i);
		for (i = 0; i < nConds; i++)
			Y[i] = spc.getYCoord(i);
		for (i = 0; i < nConds; i++)
			units[i] = spc.getUnits();
		dataChanged = true;
		normAmps = wires[0].getNormAmps();
		emergAmps = wires[0].getEmergAmps();

		try {
			updateLineGeometryData(DSS.activeCircuit.getSolution().getFrequency());
		} catch (LineGeometryProblem e) {
			DSS.doSimpleMsg("Error updating line geometry data.", -1);
		}
	}

	public LineUnits getUnit(int i) {
		return units[i];
	}

	public void setUnit(int idx, LineUnits val) {
		units[idx] = val;
	}

	public int getNWires() {
		return nConds;
	}

	public String getCondName(int idx) {
		return condNames[idx];
	}

	public void setCondName(int idx, String val) {
		condNames[idx] = val;
	}

	public LineUnits getLastUnit() {
		return lastUnit;
	}

	public void setLastUnit(LineUnits lastUnit) {
		this.lastUnit = lastUnit;
	}

	public String getSpacingType() {
		return spacingType;
	}

	public void setSpacingType(String spacingType) {
		this.spacingType = spacingType;
	}

	public double getNormAmps() {
		return normAmps;
	}

	public void setNormAmps(double normAmps) {
		this.normAmps = normAmps;
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		this.emergAmps = emergAmps;
	}

	public ConductorChoice getPhaseChoice() {
		return phaseChoice;
	}

	public int getNPhases() {
		return nPhases;
	}

	public int getActiveCondIdx() {
		return activeCondIdx;
	}

	public void setDataChanged(boolean dataChanged) {
		this.dataChanged = dataChanged;
	}

	public void setReduce(boolean reduce) {
		this.reduce = reduce;
	}

	public void setPhaseChoice(ConductorChoice phaseChoice) {
		this.phaseChoice = phaseChoice;
	}

}
