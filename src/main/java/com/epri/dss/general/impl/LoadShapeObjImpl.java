package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LoadShapeObj;

public class LoadShapeObjImpl extends DSSObjectImpl implements LoadShapeObj {

	private int LastValueAccessed;
	/* Number of points in curve */
	private int NumPoints;
	private int ArrayPropertyIndex;

	/* =0.0 then random interval (hr) */
	protected double Interval;
	/* Time values (hr) if Interval > 0.0 */
	protected double[] Hours;
	protected double[] PMultipliers, QMultipliers;
	
	protected double MaxP, MaxQ;

	protected boolean UseActual;

	protected double Mean;
	protected double StdDev;

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

	public double[] getHours() {
		return Hours;
	}

	public void setHours(double[] hours) {
		Hours = hours;
	}

	public double[] getPMultipliers() {
		return PMultipliers;
	}

	public void setPMultipliers(double[] pMultipliers) {
		PMultipliers = pMultipliers;
	}

	public double[] getQMultipliers() {
		return QMultipliers;
	}

	public void setQMultipliers(double[] qMultipliers) {
		QMultipliers = qMultipliers;
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

	public void setInterval(double interval) {
		Interval = interval;
	}

	public double getMaxP() {
		return MaxP;
	}

	public void setMaxP(double maxP) {
		MaxP = maxP;
	}

	public double getMaxQ() {
		return MaxQ;
	}

	public void setMaxQ(double maxQ) {
		MaxQ = maxQ;
	}

	public boolean isUseActual() {
		return UseActual;
	}

	public void setUseActual(boolean useActual) {
		UseActual = useActual;
	}

}
