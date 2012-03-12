package com.ncond.dss.common.types;

public enum SolutionMode {
	SNAPSHOT,
	DAILYMODE,
	YEARLYMODE,  // 8760 hour
	MONTECARLO1,
	LOADDURATION1,
	PEAKDAY,
	DUTYCYCLE,
	DIRECT,
	MONTEFAULT,  // Monte Carlo fault study
	FAULTSTUDY,  // run through all buses and compute Voc and Zsc; then ask for fault current
	MONTECARLO2,
	MONTECARLO3,
	LOADDURATION2,
	AUTOADDFLAG,
	DYNAMICMODE,
	HARMONICMODE,
	GENERALTIME;
}
