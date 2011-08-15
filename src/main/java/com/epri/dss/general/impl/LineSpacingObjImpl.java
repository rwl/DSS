package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LineSpacing;
import com.epri.dss.general.LineSpacingObj;
import com.epri.dss.shared.impl.LineUnits;

public class LineSpacingObjImpl extends DSSObjectImpl implements LineSpacingObj {

	private int nConds;
	private int nPhases;
	private double[] X;
	private double[] Y;
	private int units;
	private boolean dataChanged;

	public LineSpacingObjImpl(DSSClass parClass, String lineSpacingName) {
		super(parClass);

		setName(lineSpacingName.toLowerCase());
		objType = parClass.getDSSClassType();

		dataChanged = true;
		X           = null;
		Y           = null;
		units       = LineUnits.UNITS_FT;

		setNWires(3);  // allocates terminals
		nPhases = 3;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < 5; i++)  // TODO Check zero based indexing
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
	}

	private String arrayString(double[] pf, int n) {
		// FIXME Use StringBuffer
		String r = "[";
		if (n > 0)
			r = r + String.format("%-g", pf[0]);  // TODO Check zero based indexing
		for (int i = 1; i < n; i++)
			r = r + String.format(",%-g", pf[i]);
		return r + "]";
	}

	@Override
	public String getPropertyValue(int index) {
		switch (index) {
		case 3:
			return arrayString(X, nConds);
		case 4:
			return arrayString(Y, nConds);
		case 5:
			LineUnits.lineUnitsStr(units);
		default:
			// inherited parameters
			return super.getPropertyValue(index);
		}
	}

	public double getXCoord(int i) {
		return i <= nConds ? X[i] : 0.0;
	}

	public double getYCoord(int i) {
		return i <= nConds ? Y[i] : 0.0;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		propertyValue[0] = "3";
		propertyValue[1] = "3";
		propertyValue[2] = "0";
		propertyValue[3] = "32";
		propertyValue[4] = "ft";

		super.initPropertyValues(LineSpacing.NumPropsThisClass);
	}

	public void setNWires(int value) {
		nConds = value;
		X = new double[nConds];
		Y = new double[nConds];
		units = LineUnits.UNITS_FT;
	}

	public int getNWires() {
		return nConds;
	}

	public int getNPhases() {
		return nPhases;
	}

	public int getUnits() {
		return units;
	}

	// FIXME Private members in OpenDSS.

	public int getNConds() {
		return nConds;
	}

	public void setNConds(int num) {
		nConds = num;
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
		return dataChanged;
	}

	public void setDataChanged(boolean changed) {
		dataChanged = changed;
	}

	public void setNPhases(int num) {
		nPhases = num;
	}

	public void setUnits(int value) {
		units = value;
	}

}
