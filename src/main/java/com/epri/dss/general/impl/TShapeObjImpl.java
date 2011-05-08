package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TShapeObj;

public class TShapeObjImpl extends DSSObjectImpl implements TShapeObj {

	private int LastValueAccessed,
			NumPoints;  // Number of points in curve
	private int ArrayPropertyIndex;

	private boolean StdDevCalculated;
	private double Mean, StdDev;

	protected double Interval;   // =0.0 then random interval (hr)
	protected double Hours;      // Time values (hr) if Interval > 0.0  Else nil
	protected double[] TValues;  // Temperatures

	public TShapeObjImpl(DSSClass ParClass, String TShapeName) {
		super(ParClass);
	}

	private void saveToDblFile() {

	}

	private void saveToSngFile() {

	}

	private void calcMeanandStdDev() {

	}

	public int getNumPoints() {
		return NumPoints;
	}

	public void setNumPoints(int numPoints) {
		NumPoints = numPoints;
	}

	public double getMean() {
		return Mean;
	}

	public void setMean(double mean) {
		Mean = mean;
	}

	public double getStdDev() {
		return StdDev;
	}

	public void setStdDev(double stdDev) {
		StdDev = stdDev;
	}

	public double getInterval() {
		return Interval;
	}

	public void setInterval(double interval) {
		Interval = interval;
	}

	public double getHours() {
		return Hours;
	}

	public void setHours(double hours) {
		Hours = hours;
	}

	public double[] getTValues() {
		return TValues;
	}

	public void setTValues(double[] tValues) {
		TValues = tValues;
	}

	/**
	 * Get temperatures at specified time, hr.
	 */
	public double getTemperature(double hr) {
		return 0;
	}

	/**
	 * Get temperatures by index.
	 */
	public double getTemperature(int i) {
		return 0;
	}

	/**
	 * Get hour corresponding to point index.
	 */
	public double getHour(int i) {
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
