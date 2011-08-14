package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LineSpacing;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.shared.impl.LineUnits;

public class LineSpacingObjImpl extends DSSObjectImpl implements LineSpacingObj {

	private int NConds;
	private int NPhases;
	private double[] X;
	private double[] Y;
	private int Units;
	private boolean DataChanged;

	public LineSpacingObjImpl(DSSClass ParClass, String LineSpacingName) {
		super(ParClass);

		setName(LineSpacingName.toLowerCase());
		this.objType = ParClass.getDSSClassType();

		this.DataChanged = true;
		this.X           = null;
		this.Y           = null;
		this.Units       = LineUnits.UNITS_FT;

		setNWires(3);  // allocates terminals
		this.NPhases = 3;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < 5; i++)  // TODO Check zero based indexing
			F.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
	}

	private String arrayString(double[] pF, int N) {
		String r = "[";
		if (N > 0)
			r = r + String.format("%-g", pF[0]);  // TODO Check zero based indexing
		for (int i = 1; i < N; i++)
			r = r + String.format(",%-g", pF[i]);
		return r + "]";
	}

	@Override
	public String getPropertyValue(int Index) {
		switch (Index) {
		case 3:
			return arrayString(X, NConds);
		case 4:
			return arrayString(Y, NConds);
		case 5:
			LineUnits.lineUnitsStr(Units);
		default:
			// inherited parameters
			return super.getPropertyValue(Index);
		}
	}

	public double getXCoord(int i) {
		return i <= NConds ? X[i] : 0.0;
	}

	public double getYCoord(int i) {
		return i <= NConds ? Y[i] : 0.0;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		propertyValue[0] = "3";
		propertyValue[1] = "3";
		propertyValue[2] = "0";
		propertyValue[3] = "32";
		propertyValue[4] = "ft";

		super.initPropertyValues(LineSpacing.NumPropsThisClass);
	}

	public void setNWires(int Value) {
		NConds = Value;
		X = new double[NConds];
		Y = new double[NConds];
		Units = LineUnits.UNITS_FT;
	}

	public int getNWires() {
		return NConds;
	}

	public int getNPhases() {
		return NPhases;
	}

	public int getUnits() {
		return Units;
	}

	// FIXME Private members in OpenDSS.

	public int getNConds() {
		return NConds;
	}

	public void setNConds(int nConds) {
		NConds = nConds;
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

	public boolean isDataChanged() {
		return DataChanged;
	}

	public void setDataChanged(boolean dataChanged) {
		DataChanged = dataChanged;
	}

	public void setNPhases(int nPhases) {
		NPhases = nPhases;
	}

	public void setUnits(int units) {
		Units = units;
	}

}
