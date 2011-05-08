package com.epri.dss.general;

import com.epri.dss.shared.impl.Complex;

/**
 * Manages the geometry data and calculates the impedance matrices for an overhead line
 *
 * Usage: Create with Number of conductors you want
 *     Specify the number of phases. The first conductors you define with
 *     be the phases. Other conductors may be considered neutral.
 *
 *     Uses GMR for power frequency calcs so that answers match published
 *     data.
 *
 *     You only have to set R or GMR. The other will default. However, you should set
 *     both for better accuracy.
 *
 *     When you as for Zmatrix or YCmatrix you get the full matrix unless you have executed
 *     a Kron reduction or Reduce function. Reduce eleminates all non phases. If you
 *     want the full detailed model, DO NOT REDUCE!
 *
 */
public interface LineConstants {

	static final double e0 = 8.854e-12;  // dielectric constant  F/m
	static final double mu0 = 12.56637e-7; // hy/m
	static final double Twopi = 6.283185307;

	static Complex C1_j1 = new Complex(1, 1);
	static double b1 = 1.0 / (3.0 * Math.sqrt(2.0));;
	static double b2 = 1.0 / 16.0;
	static double b3 = b1 / 3.0 / 5.0;
	static double b4 = b2 / 4.0 / 6.0;
	static double d2 = b2 * Math.PI / 4.0;
	static double d4 = b4 * Math.PI / 4.0;
	static double c2 = 1.3659315;
	static double c4 = c2 + 1.0 / 4.0 + 1.0 / 6.0;

}
