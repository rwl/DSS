package com.epri.dss.general.impl;

import com.epri.dss.general.LineConstants;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

/**
 * This class returns a matrix ordered by phases first then remaining conductors.
 * Assumes phases are defined first.
 *
 */
public class LineConstantsImpl implements LineConstants {

	protected int NumConds;
	protected int NumPhases;
	protected double[] X;
	protected double[] Y;
	protected double[] Rdc;  // ohms/m
	protected double[] Rac;  // ohms/m
	protected double[] GMR;  // m
	protected double[] radius;

	protected CMatrix Zmatrix;   // in ohms/m
	protected CMatrix YCmatrix;  // siemens/m   --- jwC

	protected CMatrix Zreduced;   // These two do not exist until Kron Reduction
	protected CMatrix YCreduced;  // is executed

	protected double Frequency;  // Frequency for which impedances are computed
	protected double w;  // 2piF
	protected double rhoEarth;  // ohm-m
	protected Complex me; // factor for earth impedance
	protected boolean RhoChanged;

	public LineConstantsImpl(int NumConductors) {

	}

	public double getGMR(int i, int units) {
		return 0;
	}

	public void setGMR(int i, int units, double value) {

	}

	public double getRadius(int i, int units) {
		return 0;
	}

	public void setRadius(int i, int units, double value) {

	}

	public double getRdc(int i, int units) {
		return 0;
	}

	public void setRdc(int i, int units, double value) {

	}

	public double getRac(int i, int units) {
		return 0;
	}

	public void setRac(int i, int units, double value) {

	}

	public double getX(int i, int units) {
		return 0;
	}

	public void setX(int i, int units, double value) {

	}

	public double getY(int i, int units) {
		return 0;
	}

	public void setY(int i, int units, double value) {

	}

	protected double getFrequency() {
		return Frequency;
	}

	protected void setFrequency(double frequency) {
		Frequency = frequency;
	}

	public Complex getZe(int i, int j) {
		return null;
	}

	public Complex getZint(int i) {
		return null;
	}

	/**
	 * These two properties will auto recalc the impedance matrices if frequency is different.
	 * Converts to desired units when executed; Returns pointer to working version.
	 */
	public CMatrix getZmatrix(double f, double Lngth, int Units) {
		return Zmatrix;
	}

	public CMatrix getYCmatrix() {
		return YCmatrix;
	}

	protected int getNumPhases() {
		return 0;
	}

	public void setNumPhases(int numPhases) {
		NumPhases = numPhases;
	}

	public double getRhoEarth() {
		return 0;
	}

	public void setRhoEarth(double rhoEarth) {
		this.rhoEarth = rhoEarth;
	}

	public int getNumConds() {
		return NumConds;
	}

	public boolean conductorsInSameSpace(StringBuffer ErrorMessage) {
		return false;
	}

	// force a calc of impedances
	public void calc(double f) {

	}

	// Performs a Kron reduction leaving first Norder rows
	public void Kron(int Norder) {

	}

	/**
	 * Kron reduce to Numphases only
	 */
	public void reduce() {

	}

}
