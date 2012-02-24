package com.ncond.dss.general;

/**
 * Nominally, a time-current curve, but also used for volt-time curves.
 *
 * Collections of time points. Return values can be interpolated either
 * Log-Log as traditional TCC or as over- or under-voltage definite time.
 *
 */
public interface TCC_CurveObj extends DSSObject {

	/**
	 * Return operating time for a particular time value.
	 */
	double getTCCTime(double value);

	/**
	 * Return operating time for undervoltage relay.
	 */
	double getUVTime(double value);

	/**
	 * Return operating time for overvoltage relay.
	 */
	double getOVTime(double value);

	/**
	 * Get C_Value by index.
	 */
	double value(int i);

	/**
	 * Get time value (sec) corresponding to point index.
	 */
	double time(int i);

	int getNumPoints();

	// FIXME Private members in OpenDSS

	int getLastValueAccessed();

	void setLastValueAccessed(int lastValueAccessed);

	int getNPts();

	void setNPts(int npts);

	double[] getLogT();

	void setLogT(double[] logt);

	double[] getLogC();

	void setLogC(double[] logC);

	double[] getCValues();

	void setCValues(double[] values);

	double[] getTValues();

	void setTValues(double[] values);

}
