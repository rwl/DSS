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
		setName(XYCurveName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.LastValueAccessed = 0;

		this.NumPoints   = 0;
		this.XValues     = null;
		this.YValues     = null;

		this.X = 0.0;
		this.Y = 0.0;

		ArrayPropertyIndex = -1;

		initPropertyValues(0);
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

	// FIXME Private members in OpenDSS

	public int getLastValueAccessed() {
		return LastValueAccessed;
	}

	public void setLastValueAccessed(int lastValueAccessed) {
		LastValueAccessed = lastValueAccessed;
	}

	public int getArrayPropertyIndex() {
		return ArrayPropertyIndex;
	}

	public void setArrayPropertyIndex(int arrayPropertyIndex) {
		ArrayPropertyIndex = arrayPropertyIndex;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double[] getXValues() {
		return XValues;
	}

	public void setXValues(double[] xValues) {
		XValues = xValues;
	}

	public double[] getYValues() {
		return YValues;
	}

	public void setYValues(double[] yValues) {
		YValues = yValues;
	}

}
