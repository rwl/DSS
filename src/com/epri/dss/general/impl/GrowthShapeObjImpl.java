package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.GrowthShapeObj;

public class GrowthShapeObjImpl extends DSSObjectImpl implements GrowthShapeObj {

	/* Number of points in curve */
	private int Npts;
	/* Number of years presently allocated in look up table */
	private int NYears;
	private int BaseYear;

	/* Year values */
	private int[] Year;
	private double[] YearMult, Multiplier;

	public GrowthShapeObjImpl(DSSClass ParClass, String GrowthShapeName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private void reCalcYearMult() {

	}

	public String getPropertyValue(int Index) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	/* Get multiplier for Specified Year */
	public double getMult(int Yr) {
		return 0.0;
	}

}
