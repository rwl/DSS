package com.ncond.dss.common;

public interface Conductor {

	boolean isClosed();

	/**
	 * Change this variable to indicate open or closed switch.
	 */
	void setClosed(boolean closed);

	boolean isFuseBlown();

	void setFuseBlown(boolean fuseBlown);

	void setAmbient(double value);

	void setTCCName(String value);

	String getTCCName();

	/**
	 * Computes whether conductor has burned down.
	 */
	void calcIsqt(double currentMag);

	/**
	 * Restore the conductor and reset the i2t calcs.
	 */
	void resetIsqt();

}
