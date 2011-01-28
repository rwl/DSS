package com.epri.dss.shared;

public interface Dynamics {

	static final int NumSolutionModes = 16;

    /* Solution modes */
	static final int SNAPSHOT = 0;
	static final int DAILYMODE = 1;
	static final int YEARLYMODE = 2;  // 8760 hour
	static final int MONTECARLO1 = 3;
	static final int LOADDURATION1 = 4;
	static final int PEAKDAY = 5;
	static final int DUTYCYCLE = 6;
	static final int DIRECT = 7;
	static final int MONTEFAULT = 8;  // Monte Carlo Fault Study
	static final int FAULTSTUDY = 9;  // Run through all buses and compute Voc and Zsc; Then ask for fault current.
	static final int MONTECARLO2 = 10;
	static final int MONTECARLO3 = 11;
	static final int LOADDURATION2 = 12;
	static final int AUTOADDFLAG = 13;
	static final int DYNAMICMODE = 14;
	static final int HARMONICMODE = 15;

}
