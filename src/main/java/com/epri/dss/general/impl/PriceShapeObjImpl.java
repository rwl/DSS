package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.PriceShapeObj;

public class PriceShapeObjImpl extends DSSObjectImpl implements PriceShapeObj {

	private int LastValueAccessed, NumPoints;  // Number of points in curve
	private int ArrayPropertyIndex;

	private boolean StdDevCalculated;
	private double Mean, StdDev;

	protected double Interval;  // =0.0 then random interval (hr)
	protected double[] Hours;   // Time values (hr) if Interval > 0.0  Else nil
	protected double[] PriceValues;  // Prices

	public PriceShapeObjImpl(DSSClass ParClass, String PriceShapeName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private void saveToDblFile() {

	}

	private void saveToSngFile() {

	}

	private void calcMeanandStdDev() {

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

	public int getNumPoints() {
		return 0;
	}

	public void setNumPoints(int numPoints) {
		NumPoints = numPoints;
	}

	public double getInterval() {
		return Interval;
	}

	public double[] getHours() {
		return Hours;
	}

	public void setHours(double[] hours) {
		Hours = hours;
	}

	public double[] getPriceValues() {
		return PriceValues;
	}

	public void setPriceValues(double[] priceValues) {
		PriceValues = priceValues;
	}

	/**
	 * Get prices at specified time, hr.
	 */
	public double getPrice(double hr) {
		return 0;
	}

	/**
	 * Get prices by index.
	 */
	public double getPrice(int i) {
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
