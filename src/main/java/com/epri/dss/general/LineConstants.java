package com.epri.dss.general;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.shared.ComplexMatrix;
import org.apache.commons.math.complex.Complex;

/**
 * Manages the geometry data and calculates the impedance matrices for an overhead line
 *
 * Usage: Create with number of conductors you want
 *     Specify the number of phases. The first conductors you define with
 *     be the phases. Other conductors may be considered neutral.
 *
 *     Uses GMR for power frequency calcs so that answers match published
 *     data.
 *
 *     You only have to set R or GMR. The other will default. However, you should set
 *     both for better accuracy.
 *
 *     When you ask for Zmatrix or YCmatrix you get the full matrix unless you have executed
 *     a Kron reduction or Reduce function. Reduce eleminates all non phases. If you
 *     want the full detailed model, DO NOT REDUCE!
 *
 */
public interface LineConstants {

	static final double E0 = 8.854e-12;  // dielectric constant  F/m
	static final double MU0 = 12.56637e-7; // hy/m
	static final double TWO_PI = DSSGlobals.TWO_PI;

	static final Complex C1_j1 = new Complex(1, 1);
	static final double B1 = 1.0 / (3.0 * Math.sqrt(2.0));;
	static final double B2 = 1.0 / 16.0;
	static final double B3 = B1 / 3.0 / 5.0;
	static final double B4 = B2 / 4.0 / 6.0;
	static final double D2 = B2 * Math.PI / 4.0;
	static final double D4 = B4 * Math.PI / 4.0;
	static final double C2 = 1.3659315;
	static final double C4 = C2 + 1.0 / 4.0 + 1.0 / 6.0;

	double getGMR(int i, int units);

	void setGMR(int i, int units, double value);

	double getRadius(int i, int units);

	void setRadius(int i, int units, double value);

	double getRdc(int i, int units);

	void setRdc(int i, int units, double value);

	double getRac(int i, int units);

	void setRac(int i, int units, double value);

	double getX(int i, int units);

	void setX(int i, int units, double value);

	double getY(int i, int units);

	void setY(int i, int units, double value);

	Complex getZe(int i, int j);

	Complex getZint(int i);

	/**
	 * These two properties will auto recalc the impedance matrices if frequency is different.
	 * Converts to desired units when executed; Returns pointer to working version.
	 */
	ComplexMatrix getZMatrix(double f, double length, int units);

	ComplexMatrix getYcMatrix(double f, double length, int units);

	int getNPhases();

	void setNPhases(int numPhases);

	double getRhoEarth();

	void setRhoEarth(double rhoEarth);

	int getNumConds();

	boolean conductorsInSameSpace(StringBuffer errorMessage);

	/**
	 * Force a calc of impedances.
	 */
	void calc(double f);

	/**
	 * Performs a Kron reduction leaving first nOrder rows
	 */
	void Kron(int nOrder);

	/**
	 * Kron reduce to numPhases only
	 */
	void reduce();

}
