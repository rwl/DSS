package com.epri.dss.shared.impl;

/**
 * Variables needed for dynamics and user-written models.
 */
public class DynamicsRec {

	/** time vars */
	public double h;  // time step size in sec for dynamics
	public double t;  // sec from top of hour
	public double tstart;
	public double tstop;

	/** 0 = new time step; 1 = same time step as last iteration */
	public int iterationFlag;

	/** PEAKSNAP, DAILYMODE, YEARLYMODE, MONTECARLO, etc. (see DSSGlobals) */
	public int solutionMode;

}
