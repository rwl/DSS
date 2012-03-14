package com.ncond.dss.conversion;

/**
 * Load variation with voltage.
 */
public enum LoadModel {
	/** Constant kVA (P,Q always in same ratio) */
	PQ,
	/** Constant impedance */
	Z,
	/** Constant P, quadratic Q (mostly motor) */
	MOTOR,
	/** Linear P, Quadratic Q  (mixed motor/resistive) */
	CVR,
	/** Constant |I| */
	I,
	/** Constant P (variable); Q is fixed value (not variable) */
	FIXEDQ,
	/** Constant P (Variable); Q is fixed Z (not variable) */
	FIXEDQZ,
	/** 3 real power coefficients, 3 reactive, Vcutoff */
	ZIPV;
}
