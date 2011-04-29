package com.epri.dss.shared.impl;

/**
 * Variables needed for dynamics and user-written models.
 */
public class DynamicsRec {

	/* time vars */
	public double h;     // Time step size in sec for dynamics
	public double t;     // sec from top of hour
	public double tstart;
	public double tstop;

	/* 0 = New Time Step; 1 = Same Time Step as last iteration */
	public int IterationFlag;
	/* PEAKSNAP, DAILYMODE, YEARLYMODE, MONTECARLO, etc. (see DSSGlobals) */
	public int SolutionMode;

}
