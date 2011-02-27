package com.epri.dss.general;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.shared.CMatrix;

/**
 * Manages the geometry data and calculates the impedance matrices for an
 * overhead line.
 * 
 * Usage: Create with Number of conductors you want
 *        Specify the number of phases. The first conductors you define with
 *        be the phases. Other conductors may be considered neutral.
 *     
 *        Uses GMR for power frequency calcs so that answers match published
 *        data.
 *     
 *        You only have to set R or GMR. The other will default. However, you
 *        should set both for better accuracy.
 *     
 *        When you as for Zmatrix or YCmatrix you get the full matrix unless
 *        you have executed a Kron reduction or Reduce function. Reduce
 *        eleminates all non phases. If you want the full detailed model,
 *        DO NOT REDUCE!
 *
 */
public interface OHLineConstants {
	
	static final double e0 = 8.854e-12;     // dielectric constant  F/m
	static final double mu0 = 12.56637e-7;  // hy/m
	static final double TwoPi = 6.283185307;
	
	double getGMR(int i, int units);
	
	double getRadius(int i, int units);
	
	double getRdc(int i, int units);
	
	double getRac(int i, int units);
	
	double getX(int i, int units);
	
	double getY(int i, int units);
	
	/*
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 */
	CMatrix getYCmatrix(double f, double Lngth, int Units);
	
	/* Earth return impedance at present frequency for ij element */
	Complex getZe(int i, int j);
	
	/* Internal impedance of i-th conductor for present frequency */
	Complex getZint(int i);

	/*
	 * Will auto recalc the impedance matrices if frequency is different
	 * Converts to desired units when executed.
	 */
	CMatrix getZmatrix(double f, double Lngth, int Units);
	
	void setGMR(int i, int units, double Value);
	
	void setRadius(int i, int units, double Value);
	
	void setRdc(int i, int units, double Value);
	
	void setRac(int i, int units, double Value);
	
	void setX(int i, int units, double Value);
	
	void setY(int i, int units, double Value);
	
	void setRhoEarth(double Value);
	
	double getRhoEarth();
	
	void setNPhases(int Value);
	
	int getNPhases();
	
	boolean conductorsInSameSpace(String ErrorMessage);
	
	/* Force a calc of impedances */
	void calc(double f);
	
	/* Performs a Kron reduction leaving first Norder rows */
	void Kron(int nOrder);
	
	/* Kron reduce to NumPhases only */
	void reduce();

}
