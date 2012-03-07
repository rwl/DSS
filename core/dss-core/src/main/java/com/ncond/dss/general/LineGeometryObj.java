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

	public class LineGeometryProblem extends Exception {

		public LineGeometryProblem(String string) {
			// TODO Auto-generated constructor stub
		}

		private static final long serialVersionUID = -181990921259563478L;

	}

	private ConductorChoice phaseChoice;
	private int nConds;
	private int nPhases;
	private String[] condName;
	private ConductorDataObj[] wireData;
	private double[] X;
	private double[] Y;
	private int[] units;
	private int lastUnit;
	private boolean dataChanged;
	private boolean reduce;
	private int activeCond;
	private String spacingType;

	private LineConstants lineData;

	protected double normAmps;
	protected double emergAmps;

	public LineGeometryObj(DSSClass parClass, String lineGeometryName) {
		super(parClass);

		setName(lineGeometryName.toLowerCase());
		objType = parClass.getClassType();

		dataChanged = true;

		phaseChoice = ConductorChoice.UNKNOWN;
		condName = null;
		wireData = null;
		X = null;
		Y = null;
		units = null;
		lineData = null;
		spacingType = "";

		setNConds(3);  // allocates terminals
		nPhases = 3;
		setActiveCond(0);
		lastUnit  = LineUnits.UNITS_FT;
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
			setActiveCond(j);
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
		String result;

		switch (index) {
		case 2:
			result = String.format("%d", activeCond);
			break;
		case 3:
			result = condName[activeCond];
			break;
		case 12:
			result = condName[activeCond];
			break;
		case 13:
			result = condName[activeCond];
			break;
		case 4:
			result = String.format("%-g", X[activeCond]);
			break;
		case 5:
			result = String.format("%-g", Y[activeCond]);
			break;
		case 6:
			result = LineUnits.lineUnitsStr(units[activeCond]);
			break;
		case 11:
			result = "[";
			for (int i = 0; i < nConds; i++)
				result = result + condName[i] + " ";
			result = result + "]";
			break;
		case 14:
			result = "[";
			for (int i = 0; i < nConds; i++)
				result = result + condName[i] + " ";
			result = result + "]";
			break;
		case 15:
			result = "[";
			for (int i = 0; i < nConds; i++)
				result = result + condName[i] + " ";
			result = result + "]";
			break;
		default:
			// inherited parameters
			result = super.getPropertyValue(index);
			break;
		}

		return result;
	}

	public double getXCoord(int i) {
		return i < nConds ? X[i] : 0.0;
	}

	public double getYCoord(int i) {
		return i < nConds ? Y[i] : 0.0;
	}

	public String getConductorName(int i) {
		return i < nConds ? condName[i] : "";
	}

	public ConductorDataObj getConductorData(int i) {
		return i < nConds ? wireData[i] : null;
	}

	public int getNConds() {
		return reduce ? nPhases : nConds;
	}

	public double getRhoEarth() {
		return lineData.getRhoEarth();
	}

	public CMatrix getYcMatrix(double f, double length, int units) {
		CMatrix result = null;
		if (dataChanged) {
			try {
				updateLineGeometryData(f);
			} catch (LineGeometryProblem e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!DSS.solutionAbort)
			result = lineData.getYcMatrix(f, length, units);
		return result;
	}

	public CMatrix getZMatrix(double f, double length, int units) {
		CMatrix result = null;
		if (dataChanged) {
			try {
				updateLineGeometryData(f);
			} catch (LineGeometryProblem e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!DSS.solutionAbort)
			result = lineData.getZMatrix(f, length, units);
		return result;
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
	 * LineGeometry structure not conducive to standard means of saving.
	 */
	@Override
	public void saveWrite(PrintWriter f) {
		/* Write only properties that were explicitly set in the
		 * final order they were actually set.
		 */
		int iProp = getNextPropertySet(-1);  // works on activeDSSObject
		if (iProp >= 0)
			f.println();

		while (iProp >= 0) {
			switch (parentClass.getRevPropertyIdxMap(iProp)) {
			case 2:  // if cond=, spacing, or wires were ever used write out arrays ...
				for (int i = 0; i < nConds; i++)
					f.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, condName[i], X[i], Y[i], LineUnits.lineUnitsStr(units[i])));
				break;
			case 10:
				for (int i = 0; i < nConds; i++)
					f.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, condName[i], X[i], Y[i], LineUnits.lineUnitsStr(units[i])));
				break;
			case 11:
				for (int i = 0; i < nConds; i++)
					f.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, condName[i], X[i], Y[i], LineUnits.lineUnitsStr(units[i])));
				break;
			case 3:
				// do nothing
				break;
			case 4:
				// do nothing
				break;
			case 5:
				// do nothing
				break;
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
				if (reduce)
					f.println("~ Reduce=Yes");
				break;
			default:
				f.println(String.format("~ %s=%s",
						parentClass.getPropertyName(parentClass.getRevPropertyIdxMap(iProp)), Util.checkForBlanks(propertyValue[iProp])));
				break;
			}
			iProp = getNextPropertySet(iProp);
		}
	}

	public void setActiveCond(int value) {
		if (value > 0)
			if (value <= nConds) {
				setActiveCond(value);
				if (units[activeCond] == -1)
					units[activeCond] = lastUnit;  // makes this a sticky value so you don't have to repeat it
			}
	}

	// FIXME Private method in OpenDSS
	public void changeLineConstantsType(ConductorChoice newPhaseChoice) {
		LineConstants newLineData = null;
		boolean needNew = false;

		if (newPhaseChoice != phaseChoice)
			needNew = true;
		if (lineData == null) {
			needNew = true;
		} else if (nConds != lineData.getNumConds()) {
			needNew = true;
		}

		if (needNew)
			switch (newPhaseChoice) {
			case OVERHEAD:
				newLineData = new OHLineConstants(getNConds());
				break;
			case CONCENTRIC_NEUTRAL:
				newLineData = new CNLineConstants(getNConds());
				break;
			case TAPE_SHIELD:
				newLineData = new TSLineConstants(getNConds());
				break;
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
		if (lineData != null)
			lineData = null;
		changeLineConstantsType(phaseChoice);
		condName = new String[nConds];

		/* Allocations */
		wireData = new ConductorDataObj[nConds];
		X        = new double[nConds];
		Y        = new double[nConds];
		units    = new int[nConds];
		for (int i = 0; i < nConds; i++)
			units[i] = -1;  // default to ft
		lastUnit = LineUnits.UNITS_FT;
	}

	public ConductorChoice getPhaseChoice() {
		return phaseChoice;
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
	// FIXME Private method in OpenDSS
	public void updateLineGeometryData(double f) throws LineGeometryProblem {
		CNDataObj cnd;
		TSDataObj tsd;

		for (int i = 0; i < nConds; i++) {
			lineData.setX(i, units[i], X[i]);
			lineData.setY(i, units[i], Y[i]);
			lineData.setRadius(i, wireData[i].getRadiusUnits(), wireData[i].getRadius());
			lineData.setGMR(i, wireData[i].getGMRUnits(), wireData[i].getGMR60());
			lineData.setRdc(i, wireData[i].getResistanceUnits(), wireData[i].getRDC());
			lineData.setRac(i, wireData[i].getResistanceUnits(), wireData[i].getR60());  // Rac
			if (wireData[i] instanceof CNDataObj) {
				CNLineConstants cnlc = (CNLineConstants) lineData;
				cnd = (CNDataObj) wireData[i];
				cnlc.setEpsR(i, cnd.getEpsR());
				cnlc.setInsLayer(i, cnd.getRadiusUnits(), cnd.getInsLayer());
				cnlc.setDiaIns(i, cnd.getRadiusUnits(), cnd.getDiaIns());
				cnlc.setDiaCable(i, cnd.getRadiusUnits(), cnd.getDiaCable());
				cnlc.setKStrand(i, cnd.getkStrand());
				cnlc.setDiaStrand(i, cnd.getRadiusUnits(), cnd.getDiaStrand());
				cnlc.setGmrStrand(i, cnd.getGMRUnits(), cnd.getGmrStrand());
				cnlc.setRStrand(i, cnd.getResistanceUnits(), cnd.getRStrand());
			} else if (wireData[i] instanceof TSDataObj) {
				TSLineConstants tslc = (TSLineConstants) lineData;
				tsd = (TSDataObj) wireData[i];
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
		StringBuffer lineGeomErrMsg = new StringBuffer();
		if (lineData.conductorsInSameSpace(lineGeomErrMsg)) {
			DSS.solutionAbort = true;
			throw new LineGeometryProblem("Error in LineGeometry." + getName() + ": " + lineGeomErrMsg.toString());
		} else {
			lineData.calc(f);
			if (reduce)
				lineData.reduce();  // reduce out neutrals
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
		setNPhases(spc.getNPhases());
		spacingType = spc.getName();
		if (nConds > nPhases)
			reduce = true;

		newPhaseChoice = ConductorChoice.OVERHEAD;
		for (i = 0; i < getNConds(); i++) {
			if (wires[i] instanceof CNDataObj)
				newPhaseChoice = ConductorChoice.CONCENTRIC_NEUTRAL;
			if (wires[i] instanceof TSDataObj)
				newPhaseChoice = ConductorChoice.TAPE_SHIELD;
		}
		changeLineConstantsType(newPhaseChoice);

		for (i = 0; i < nConds; i++)
			condName[i] = wires[i].getName();
		for (i = 0; i < nConds; i++)
			wireData[i] = wires[i];
		for (i = 0; i < nConds; i++)
			X[i] = spc.getXCoord(i);
		for (i = 0; i < nConds; i++)
			Y[i] = spc.getYCoord(i);
		for (i = 0; i < nConds; i++)
			units[i] = spc.getUnits();
		dataChanged = true;
		normAmps    = wires[0].getNormAmps();
		emergAmps   = wires[0].getEmergAmps();

		try {
			updateLineGeometryData(DSS.activeCircuit.getSolution().getFrequency());
		} catch (LineGeometryProblem e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getUnits(int i) {
		return units[i];
	}

	public int getNPhases() {
		return nPhases;
	}

	public int getActiveCond() {
		return activeCond;
	}

	public int getNWires() {
		return nConds;
	}

	public double getNormAmps() {
		return normAmps;
	}

	public void setNormAmps(double amps) {
		normAmps = amps;
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public void setEmergAmps(double amps) {
		emergAmps = amps;
	}

	// FIXME Private members in OpenDSS.

	public String[] getCondName() {
		return condName;
	}

	public void setCondName(String[] name) {
		this.condName = name;
	}

	public ConductorDataObj[] getConductorData() {
		return wireData;
	}

	public void setConductorData(ConductorDataObj[] data) {
		wireData = data;
	}

	public double[] getX() {
		return X;
	}

	public void setX(double[] x) {
		X = x;
	}

	public double[] getY() {
		return Y;
	}

	public void setY(double[] y) {
		Y = y;
	}

	public int[] getUnits() {
		return units;
	}

	public void setUnits(int[] value) {
		units = value;
	}

	public int getLastUnit() {
		return lastUnit;
	}

	public void setLastUnit(int unit) {
		lastUnit = unit;
	}

	public boolean isDataChanged() {
		return dataChanged;
	}

	public void setDataChanged(boolean changed) {
		dataChanged = changed;
	}

	public boolean isReduce() {
		return reduce;
	}

	public void setReduce(boolean value) {
		reduce = value;
	}

	public String getSpacingType() {
		return spacingType;
	}

	public void setSpacingType(String type) {
		spacingType = type;
	}

	public LineConstants getLineData() {
		return lineData;
	}

	public void setLineData(LineConstants data) {
		lineData = data;
	}

	public void setPhaseChoice(ConductorChoice choice) {
		phaseChoice = choice;
	}

}
