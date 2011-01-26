package com.epri.dss.common;

public interface DSSGlobals {

	public static final String CRLF = System.getProperty("line.separator");
	public static final double PI = 3.14159265359;
	public static final double TwoPi = 2.0 * PI;
	public static final double RadiansToDegrees = 57.29577951;
	public static final double EPSILON = 1.0e-12;   // Default tiny floating point
	public static final double EPSILON2 = 1.0e-3;   // Default for Real number mismatch testing

	// Load model types for solution
	public static final int POWERFLOW  = 1;
	public static final int ADMITTANCE = 2;

	// For YPrim matrices
	public static final int ALL_YPRIM = 0;
	public static final int SERIES = 1;
	public static final int SHUNT  = 2;

    /* Control Modes */
	public static final int CONTROLSOFF = -1;
	public static final int EVENTDRIVEN =  1;
	public static final int TIMEDRIVEN  =  2;
	public static final int STATIC      =  0;

    /* Randomization Constants */
	public static final int GAUSSIAN  = 1;
	public static final int UNIFORM   = 2;
	public static final int LOGNORMAL = 3;

    /* Autoadd Constants */
	public static final int GENADD = 1;
	public static final int CAPADD = 2;

    /* ERRORS */
	public static final int SOLUTION_ABORT = 99;
}
