package com.epri.dss.general.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.CNDataObj;
import com.epri.dss.general.CNLineConstants;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.LineConstants;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.OHLineConstants;
import com.epri.dss.general.TSDataObj;
import com.epri.dss.general.TSLineConstants;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.LineUnits;

public class LineGeometryObjImpl extends DSSObjectImpl implements LineGeometryObj {

	public class LineGeometryProblem extends Exception {

		public LineGeometryProblem(String string) {
			// TODO Auto-generated constructor stub
		}

		private static final long serialVersionUID = -181990921259563478L;

	}

	private ConductorChoice PhaseChoice;
	private int NConds;
	private int NPhases;
	private String[] CondName;
	private ConductorDataObj[] WireData;
	private double[] X;
	private double[] Y;
	private int[] Units;
	private int LastUnit;
	private boolean DataChanged;
	private boolean Reduce;
	private int ActiveCond;
	private String SpacingType;

	private LineConstants LineData;

	protected double NormAmps;
	protected double EmergAmps;

	public LineGeometryObjImpl(DSSClass ParClass, String LineGeometryName) {
		super(ParClass);

		setName(LineGeometryName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.DataChanged = true;

		this.PhaseChoice = ConductorChoice.Unknown;
		this.CondName = null;
		this.WireData = null;
		this.X = null;
		this.Y = null;
		this.Units = null;
		this.LineData = null;
		this.SpacingType = "";

		this.NConds     = 3;  // Allocates terminals
		this.NPhases    = 3;
		this.ActiveCond = 1;
		this.LastUnit   = LineUnits.UNITS_FT;
		this.NormAmps   = 0.0;
		this.EmergAmps  = 0.0;

		this.Reduce = false;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < 2; i++)  // TODO Check zero based indexing
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));

		for (int j = 0; j < NConds; j++) {
			ActiveCond = j;
			F.println("~ " + ParentClass.getPropertyName()[2] + "=" + getPropertyValue(2));
			F.println("~ " + ParentClass.getPropertyName()[3] + "=" + getPropertyValue(3));
			F.println("~ " + ParentClass.getPropertyName()[4] + "=" + getPropertyValue(4));
			F.println("~ " + ParentClass.getPropertyName()[5] + "=" + getPropertyValue(5));
			F.println("~ " + ParentClass.getPropertyName()[6] + "=" + getPropertyValue(6));
		}

		for (int i = 7; i < ParentClass.getNumProperties(); i++)
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
	}

	@Override
	public String getPropertyValue(int Index) {
		String Result;

		switch (Index) {
		case 2:
			Result = String.format("%d", ActiveCond);
		case 3:
			Result = CondName[ActiveCond];
		case 12:
			Result = CondName[ActiveCond];
		case 13:
			Result = CondName[ActiveCond];
		case 4:
			Result = String.format("%-g", X[ActiveCond]);
		case 5:
			Result = String.format("%-g", Y[ActiveCond]);
		case 6:
			Result = LineUnits.lineUnitsStr(Units[ActiveCond]);
		case 11:
			Result = "[";
			for (int i = 0; i < NConds; i++)
				Result = Result + CondName[i] + " ";
			Result = Result + "]";
		case 14:
			Result = "[";
			for (int i = 0; i < NConds; i++)
				Result = Result + CondName[i] + " ";
			Result = Result + "]";
		case 15:
			Result = "[";
			for (int i = 0; i < NConds; i++)
				Result = Result + CondName[i] + " ";
			Result = Result + "]";
		default:
			// Inherited parameters
			Result = super.getPropertyValue(Index);
		}

		return Result;
	}

	public double getXcoord(int i) {
		return i < NConds ? X[i] : 0.0;  // TODO Check zero based indexing
	}

	public double getYcoord(int i) {
		return i < NConds ? Y[i] : 0.0;
	}

	public String getConductorName(int i) {
		return i < NConds ? CondName[i] : "";
	}

	public ConductorDataObj getConductorData(int i) {
		return i < NConds ? WireData[i] : null;
	}

	public int getNconds() {
		return Reduce ? NPhases : NConds;
	}

	public double getRhoEarth() {
		return LineData.getRhoEarth();
	}

	public CMatrix getYCmatrix(double f, double Length, int Units) {
		CMatrix Result = null;
		if (DataChanged) {
			try {
				updateLineGeometryData(f);
			} catch (LineGeometryProblem e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!DSSGlobals.getInstance().isSolutionAbort())
			Result = LineData.getYCmatrix(f, Length, Units);
		return Result;
	}

	public CMatrix getZmatrix(double f, double Length, int Units) {
		CMatrix Result = null;
		if (DataChanged) {
			try {
				updateLineGeometryData(f);
			} catch (LineGeometryProblem e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!DSSGlobals.getInstance().isSolutionAbort())
			Result = LineData.getZmatrix(f, Length, Units);
		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "3";
		PropertyValue[1] = "3";
		PropertyValue[2] = "1";
		PropertyValue[3] = "";
		PropertyValue[4] = "0";
		PropertyValue[5] = "32";
		PropertyValue[6] = "ft";
		PropertyValue[7] = "0";
		PropertyValue[8] = "0";

		super.initPropertyValues(LineGeometry.NumPropsThisClass);
	}

	/**
	 * Overrides standard saveWrite.
	 * LineGeometry structure not conducive to standard means of saving.
	 */
	@Override
	public void saveWrite(PrintWriter F) {
		/* Write only properties that were explicitly set in the
		 * final order they were actually set
		 */
		int iProp = getNextPropertySet(0);  // Works on ActiveDSSObject
		if (iProp >= 0)  // TODO Check zero based indexing
			F.println();

		while (iProp >= 0) {
			switch (ParentClass.getRevPropertyIdxMap()[iProp]) {
			case 2:  // if cond=, spacing, or wires were ever used write out arrays ...
				for (int i = 0; i < NConds; i++)
					F.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, CondName[i], X[i], Y[i], LineUnits.lineUnitsStr(Units[i])));
			case 10:
				for (int i = 0; i < NConds; i++)
					F.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, CondName[i], X[i], Y[i], LineUnits.lineUnitsStr(Units[i])));
			case 11:
				for (int i = 0; i < NConds; i++)
					F.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, CondName[i], X[i], Y[i], LineUnits.lineUnitsStr(Units[i])));
			case 3:
				// do nothing
			case 4:
				// do nothing
			case 5:
				// do nothing
			case 6:
				// do nothing
			case 7:
				F.println(String.format("~ normamps=%.4g", NormAmps));
			case 8:
				F.println(String.format("~ emergamps=%.4g", EmergAmps));
			case 9:
				if (Reduce)
					F.println("~ Reduce=Yes");
			default:
				F.println(String.format("~ %s=%s",
						ParentClass.getPropertyName()[ParentClass.getRevPropertyIdxMap()[iProp]], Utilities.checkForBlanks(PropertyValue[iProp])));
			}
			iProp = getNextPropertySet(iProp);
		}
	}

	public void setActiveCond(int Value) {
		if (Value > 0)
			if (Value <= NConds) {
				ActiveCond = Value;
				if (Units[ActiveCond] == -1)
					Units[ActiveCond] = LastUnit;  // makes this a sticky value so you don't have to repeat it
			}
	}

	// FIXME Private method in OpenDSS
	public void changeLineConstantsType(ConductorChoice newPhaseChoice) {
		LineConstants newLineData = null;
		boolean needNew = false;

		if (newPhaseChoice != PhaseChoice)
			needNew = true;
		if (LineData == null) {
			needNew = true;
		} else if (NConds != LineData.getNumConds()) {
			needNew = true;
		}

		if (needNew)
			switch (newPhaseChoice) {
			case Overhead:
				newLineData = new OHLineConstantsImpl(getNconds());
			case ConcentricNeutral:
				newLineData = new CNLineConstantsImpl(getNconds());
			case TapeShield:
				newLineData = new TSLineConstantsImpl(getNconds());
			}

		if (newLineData != null) {
			if (LineData != null) {
				newLineData.setNPhases(LineData.getNPhases());
				newLineData.setRhoEarth(LineData.getRhoEarth());
			} else {
				LineData = null;
				LineData = newLineData;
			}
		}
		PhaseChoice = newPhaseChoice;
	}

	public void setNconds(int Value) {
		NConds = Value;
		if (LineData != null)
			LineData = null;
		changeLineConstantsType(PhaseChoice);
		CondName = new String[NConds];

		/* Allocations */
		WireData = new ConductorDataObj[NConds];
		X        = new double[NConds];
		Y        = new double[NConds];
		Units    = new int[NConds];
		for (int i = 0; i < NConds; i++)
			Units[i] = -1;  // default to ft
		LastUnit = LineUnits.UNITS_FT;
	}

	public ConductorChoice getPhaseChoice() {
		return PhaseChoice;
	}

	public void setNphases(int Value) {
		NPhases = Value;
		LineData.setNPhases(Value);
	}

	public void setRhoEarth(double Value) {
		LineData.setRhoEarth(Value);
	}

	/**
	 * Call this before using the line data.
	 * @throws LineGeometryProblem
	 */
	// FIXME Private method in OpenDSS
	public void updateLineGeometryData(double f) throws LineGeometryProblem {
		CNDataObj cnd;
		TSDataObj tsd;

		for (int i = 0; i < NConds; i++) {
			LineData.setX(i, Units[i], X[i]);
			LineData.setY(i, Units[i], Y[i]);
			LineData.setRadius(i, WireData[i].getRadiusUnits(), WireData[i].getRadius());
			LineData.setGMR(i, WireData[i].getGMRUnits(), WireData[i].getGMR60());
			LineData.setRdc(i, WireData[i].getResistanceUnits(), WireData[i].getRDC());
			LineData.setRac(i, WireData[i].getResistanceUnits(), WireData[i].getR60());  // Rac
			if (WireData[i] instanceof CNDataObj) {
				CNLineConstants cnlc = (CNLineConstants) LineData;
				cnd = (CNDataObj) WireData[i];
				cnlc.setEpsR(i, cnd.getEpsR());
				cnlc.setInsLayer(i, cnd.getRadiusUnits(), cnd.getInsLayer());
				cnlc.setDiaIns(i, cnd.getRadiusUnits(), cnd.getDiaIns());
				cnlc.setDiaCable(i, cnd.getRadiusUnits(), cnd.getDiaCable());
				cnlc.setkStrand(i, cnd.getkStrand());
				cnlc.setDiaStrand(i, cnd.getRadiusUnits(), cnd.getDiaStrand());
				cnlc.setGmrStrand(i, cnd.getGMRUnits(), cnd.getGmrStrand());
				cnlc.setRStrand(i, cnd.getResistanceUnits(), cnd.getRStrand());
			} else if (WireData[i] instanceof TSDataObj) {
				TSLineConstants tslc = (TSLineConstants) LineData;
				tsd = (TSDataObj) WireData[i];
				tslc.setEpsR(i, tsd.getEpsR());
				tslc.setInsLayer(i, tsd.getRadiusUnits(), tsd.getInsLayer());
				tslc.setDiaIns(i, tsd.getRadiusUnits(), tsd.getDiaIns());
				tslc.setDiaCable(i, tsd.getRadiusUnits(), tsd.getDiaCable());
				tslc.setDiaShield(i, tsd.getRadiusUnits(), tsd.getDiaShield());
				tslc.setTapeLayer(i, tsd.getRadiusUnits(), tsd.getTapeLayer());
				tslc.setTapeLap(i, tsd.getTapeLap());
			}
		}

		LineData.setNPhases(NPhases);
		DataChanged = false;

		/* Before we calc, check for bad conductor definitions */
		StringBuffer LineGeomErrMsg = new StringBuffer();
		if (LineData.conductorsInSameSpace(LineGeomErrMsg)) {
			DSSGlobals.getInstance().setSolutionAbort(true);
			throw new LineGeometryProblem("Error in LineGeometry." + getName() + ": " + LineGeomErrMsg.toString());
		} else {
			LineData.calc(f);
			if (Reduce)
				LineData.reduce(); // reduce out neutrals
		}
	}

	/**
	 * Called from a Line object that has its own spacing and wires input
	 * automatically sets reduce=y if the spacing has more wires than phases.
	 */
	public void loadSpacingAndWires(LineSpacingObj Spc, ConductorDataObj[] Wires) {
		int i;
		ConductorChoice newPhaseChoice;

		NConds = Spc.getNWires();  // allocates
		NPhases = Spc.getNPhases();
		SpacingType = Spc.getName();
		if (NConds > NPhases)
			Reduce = true;

		newPhaseChoice = ConductorChoice.Overhead;
		for (i = 0; i < getNconds(); i++) {
			if (Wires[i] instanceof CNDataObj)
				newPhaseChoice = ConductorChoice.ConcentricNeutral;
			if (Wires[i] instanceof TSDataObj)
				newPhaseChoice = ConductorChoice.TapeShield;
		}
		changeLineConstantsType(newPhaseChoice);

		for (i = 0; i < NConds; i++)
			CondName[i] = Wires[i].getName();
		for (i = 0; i < NConds; i++)
			WireData[i] = Wires[i];
		for (i = 0; i < NConds; i++)
			X[i] = Spc.getXcoord(i);
		for (i = 0; i < NConds; i++)
			Y[i] = Spc.getYcoord(i);
		for (i = 0; i < NConds; i++)
			Units[i] = Spc.getUnits();
		DataChanged = true;
		NormAmps    = Wires[0].getNormAmps();  // TODO Check zero based indexing
		EmergAmps   = Wires[0].getEmergAmps();

		try {
			updateLineGeometryData(DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency());
		} catch (LineGeometryProblem e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getUnits(int i) {
		return Units[i];
	}

	public int getNphases() {
		return NConds;
	}

	public int getActiveCond() {
		return ActiveCond;
	}

	public int getNWires() {
		return NConds;
	}

	public double getNormAmps() {
		return NormAmps;
	}

	public void setNormAmps(double normAmps) {
		NormAmps = normAmps;
	}

	public double getEmergAmps() {
		return EmergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		EmergAmps = emergAmps;
	}

	// FIXME Private members in OpenDSS.

	public String[] getCondName() {
		return CondName;
	}

	public void setCondName(String[] condName) {
		this.CondName = condName;
	}

	public ConductorDataObj[] getConductorData() {
		return WireData;
	}

	public void setConductorData(ConductorDataObj[] wireData) {
		WireData = wireData;
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
		return Units;
	}

	public void setUnits(int[] units) {
		Units = units;
	}

	public int getLastUnit() {
		return LastUnit;
	}

	public void setLastUnit(int lastUnit) {
		LastUnit = lastUnit;
	}

	public boolean isDataChanged() {
		return DataChanged;
	}

	public void setDataChanged(boolean dataChanged) {
		DataChanged = dataChanged;
	}

	public boolean isReduce() {
		return Reduce;
	}

	public void setReduce(boolean reduce) {
		Reduce = reduce;
	}

	public String getSpacingType() {
		return SpacingType;
	}

	public void setSpacingType(String spacingType) {
		SpacingType = spacingType;
	}

	public LineConstants getLineData() {
		return LineData;
	}

	public void setLineData(LineConstants lineData) {
		LineData = lineData;
	}

	public void setPhaseChoice(ConductorChoice phaseChoice) {
		PhaseChoice = phaseChoice;
	}

}
