package com.epri.dss.shared.impl;

public abstract class MathUtil {

	public static double gauss(double Mean, double StdDev) {
		return 0.0;
	}

	public static double quasiLognormal(double mean) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void phase2SymComp(Complex complex, Complex[] i012) {
		// TODO Auto-generated method stub

	}

	public static void phase2SymComp(Complex[] cBuffer, Complex[] v012) {
		// TODO Auto-generated method stub

	}

	public static void symComp2Phase(Complex[] iterminal, Complex[] i012) {
		// TODO Auto-generated method stub

	}

	public static Complex terminalPowerIn(Complex[] vterminal, Complex[] iterminal, int nPhases) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Complex parallelZ(Complex complex, Complex complex2) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void ETKInvert(double[] rmatrix, int nPhases, int checkError) {
		// TODO Auto-generated method stub

	}

	public static void RCDMeanandStdDev(double[] pMultipliers, int numPoints, double mean, double stdDev) {
		// TODO Auto-generated method stub

	}

	public static void curveMeanAndStdDev(double[] pMultipliers, double[] hours, int numPoints, double mean, double stdDev) {
		// TODO Auto-generated method stub

	}

	public static Complex Bessel_I0(Complex alpha) {
		// TODO Auto-generated method stub
		return null;
	}

}
