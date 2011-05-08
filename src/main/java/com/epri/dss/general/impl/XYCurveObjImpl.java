package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.XYCurveObj;

public class XYCurveObjImpl extends DSSObjectImpl implements XYCurveObj {

	private int LastValueAccessed,
			NumPoints;  // Number of points in curve
	private int ArrayPropertyIndex;
	private double X, Y;
	private double[] XValues, YValues;

	public XYCurveObjImpl(DSSClass ParClass, String XYCurveName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private double interpolatePoints(int i, int j, double X, double[] Xarray, double[] Yarray) {
		return 0;
	}

	public int getNumPoints() {
		return NumPoints;
	}

	public void setNumPoints(int numPoints) {
		NumPoints = numPoints;
	}

	/**
	 * Get Y value at specified X value.
	 */
	public double getYValue(double X) {
		return 0;
	}

	/**
	 * Get X value at specified Y value.
	 */
	public double getXValue(double Y) {
		return 0;
	}

	/**
	 * Get Y value by index.
	 */
	public double getYValue(int i) {
		return 0;
	}

	/**
	 * Get X value corresponding to point index.
	 */
	public double getXValue(int i) {
		return 0;
	}

	@Override
	public String getPropertyValue(int Index) {
		return null;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

}
