package com.epri.dss.shared;

public interface Dynamics {

	public static final int NumSolutionModes = 16;

    /* Solution modes */
	public static final int SNAPSHOT = 0;
	public static final int DAILYMODE = 1;
	public static final int YEARLYMODE = 2;  // 8760 hour
	public static final int MONTECARLO1 = 3;
	public static final int LOADDURATION1 = 4;
	public static final int PEAKDAY = 5;
	public static final int DUTYCYCLE = 6;
	public static final int DIRECT = 7;
	public static final int MONTEFAULT = 8;  // Monte Carlo Fault Study
	public static final int FAULTSTUDY = 9;  // Run through all buses and compute Voc and Zsc; Then ask for fault current.
	public static final int MONTECARLO2 = 10;
	public static final int MONTECARLO3 = 11;
	public static final int LOADDURATION2 = 12;
	public static final int AUTOADDFLAG = 13;
	public static final int DYNAMICMODE = 14;
	public static final int HARMONICMODE = 15;

}
