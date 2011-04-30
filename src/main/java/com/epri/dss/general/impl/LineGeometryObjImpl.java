package com.epri.dss.general.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.LineGeometry;
import com.epri.dss.general.LineGeometryObj;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.general.OHLineConstants;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.LineUnits;

public class LineGeometryObjImpl extends DSSObjectImpl implements LineGeometryObj {

	public class LineGeometryProblem extends Exception {

		private static final long serialVersionUID = -181990921259563478L;

	}

	private int NConds;
	private int NPhases;
	private String[] condType;
	private WireDataObj[] WireData;
	private double[] X;
	private double[] Y;
	private int[] Units;
	private int LastUnit;
	private boolean DataChanged;
	private boolean Reduce;
	private int ActiveCond;
	private String SpacingType;

	private OHLineConstants LineData;

	protected double NormAmps;
	protected double EmergAmps;

	public LineGeometryObjImpl(DSSClass ParClass, String LineGeometryName) {
		super(ParClass);

		setName(LineGeometryName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.DataChanged = true;

		this.condType = null;
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
		case 3:
			Result = String.format("%d", ActiveCond);
		case 4:
			Result = condType[ActiveCond];
		case 5:
			Result = String.format("%-g", X[ActiveCond]);
		case 6:
			Result = String.format("%-g", Y[ActiveCond]);
		case 7:
			Result = LineUnits.lineUnitsStr(Units[ActiveCond]);
		case 12:
			Result = "[";
			for (int i = 0; i < NConds; i++)
				Result = Result + condType[i] + " ";
			Result = Result + "]";
		default:
			// Inherited parameters
			Result = super.getPropertyValue(Index);
		}

		return Result;
	}

	public double getXcoord(int i) {
		return i <= NConds ? X[i] : 0.0;
	}

	public double getYcoord(int i) {
		return i <= NConds ? Y[i] : 0.0;
	}

	public String getWireName(int i) {
		return i <= NConds ? condType[i] : "";
	}

	public WireDataObj getWireData (int i) {
		return i <= NConds ? WireData[i] : null;
	}

	public int getNconds() {
		return Reduce ? NPhases : NConds;
	}

	public double getRhoEarth() {
		return LineData.getRhoEarth();
	}

	public CMatrix getYCmatrix(double f, double Length, int Units) {
		CMatrix Result = null;
		if (DataChanged)
			updateLineGeometryData(f);
		if (!DSSGlobals.getInstance().isSolutionAbort())
			Result = LineData.getYCmatrix(f, Length, Units);
		return Result;
	}

	public CMatrix getZmatrix(double f, double Length, int Units) {
		CMatrix Result = null;
		if (DataChanged)
			updateLineGeometryData(f);
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
							i, condType[i], X[i], Y[i], LineUnits.lineUnitsStr(Units[i])));
			case 10:
				for (int i = 0; i < NConds; i++)
					F.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, condType[i], X[i], Y[i], LineUnits.lineUnitsStr(Units[i])));
			case 11:
				for (int i = 0; i < NConds; i++)
					F.println(String.format("~ Cond=%d wire=%s X=%.7g h=%.7g units=%s",
							i, condType[i], X[i], Y[i], LineUnits.lineUnitsStr(Units[i])));
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

	public void setNconds(int Value) {
		NConds = Value;
		if (LineData != null)
			LineData = null;
		LineData = new OHLineConstantsImpl(NConds);  // set number phases=number conductors
		condType = new String[NConds];

		/* Allocations */
		WireData = new WireDataObj[NConds];
		X        = new double[NConds];
		Y        = new double[NConds];
		Units    = new int[NConds];
		for (int i = 0; i < NConds; i++)
			Units[i] = -1;  // default to ft
		LastUnit = LineUnits.UNITS_FT;
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
	 */
	// FIXME Private method in OpenDSS
	public void updateLineGeometryData(double f) {
		for (int i = 0; i < NConds; i++) {
			LineData.setX(i, Units[i], X[i]);
			LineData.setY(i, Units[i], Y[i]);
			LineData.setRadius(i, WireData[i].getRadiusUnits(), WireData[i].getRadius());
			LineData.setGMR(i, WireData[i].getGMRUnits(), WireData[i].getGMR60());
			LineData.setRdc(i, WireData[i].getResistanceUnits(), WireData[i].getRDC());
			LineData.setRac(i, WireData[i].getResistanceUnits(), WireData[i].getR60());  // Rac
		}

		LineData.setNPhases(NPhases);
		DataChanged = false;

		/* Before we calc, check for bad conductor definitions */
		/*String LineGeomErrMsg = "";
		if (LineData.conductorsInSameSpace(LineGeomErrMsg)) {
			throw new LineGeometryProblem("Error in LineGeometry." + getName() + ": " + LineGeomErrMsg);
			DSSGlobals.getInstance().setSolutionAbort(true);
		} else {
			LineData.calc(f);
			if (Reduce)
				LineData.reduce(); // reduce out neutrals
		}*/
	}

	/**
	 * Called from a Line object that has its own spacing and wires input
	 * automatically sets reduce=y if the spacing has more wires than phases.
	 */
	public void LoadSpacingAndWires(LineSpacingObj Spc, WireDataObj[] Wires) {
		int i;

		NConds = Spc.getNWires();  // allocates
		NPhases = Spc.getNPhases();
		SpacingType = Spc.getName();
		if (NConds > NPhases)
			Reduce = true;

		for (i = 0; i < NConds; i++)
			condType[i] = Wires[i].getName();
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

		updateLineGeometryData(DSSGlobals.getInstance().getActiveCircuit().getSolution().getFrequency());
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

	public String[] getCondType() {
		return condType;
	}

	public void setCondType(String[] condType) {
		this.condType = condType;
	}

	public WireDataObj[] getWireData() {
		return WireData;
	}

	public void setWireData(WireDataObj[] wireData) {
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

	public OHLineConstants getLineData() {
		return LineData;
	}

	public void setLineData(OHLineConstants lineData) {
		LineData = lineData;
	}

}
