package com.epri.dss.general;

/**
 * Nominally, a time-current curve, but also used for volt-time curves.
 * 
 * Collections of time points. Return values can be interpolated either
 * Log-Log as traditional TCC or as over- or under-voltage definite time.
 *
 */
public interface TCC_CurveObj extends DSSObject {
	
	/* Return operating time for a particular time value */
	double getTCCTime(double C_Value);
	
	/* Return operating time for undervoltage relay */
	double getUVTime(double V_Value);
	
	/* Return operating time for overvoltage relay */
	double getOVTime(double V_Value);
	
	/* Get C_Value by index */
	double value(int i);
	
	/* Get time value (sec) corresponding to point index */
	double time(int i);

	int getNumPoints();

}
