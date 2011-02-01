package com.epri.dss.general.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LoadShapeObj;

public class LoadShapeObjImpl extends DSSObjectImpl implements LoadShapeObj {

	private int LastValueAccessed;
	/* Number of points in curve */
	private int NumPoints;
	private int ArrayPropertyIndex;

	/* =0.0 then random interval (hr) */
	public double Interval;
	/* Time values (hr) if Interval > 0.0 */
	public double[] Hours;
	public double[] PMultipliers, QMultipliers;

	public double Mean;
	public double StdDev;

	public LoadShapeObjImpl(DSSClass ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public double getInterval() {
		return 0.0;
	}

	public void setNumPoints(int Value) {

	}

	public int getNumPoints() {
		return NumPoints;
	}

	private void saveToDblFile() {

	}

	private void saveToSngFile() {

	}

	/* Get multiplier at specified time */
	public Complex getMult(double hr) {
		return null;
	}

	/* get multiplier by index */
	public double mult(int i) {
		return 0.0;
	}

	/* get hour corresponding to point index */
	public double hour(int i) {
		return 0.0;
	}

	/* Normalize the curve presently in memory */
	public void normalize() {

	}

	public void calcMeanandStdDev() {

	}

	public String getPropertyValue(int Index) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

}
