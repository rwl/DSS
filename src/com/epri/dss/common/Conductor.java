package com.epri.dss.common;

public interface Conductor {

	boolean isClosed();

	/* change this variable to indicate open or closed switch */
	void setClosed(boolean closed);

	boolean isFuseBlown();

	void setFuseBlown(boolean fuseBlown);

	void setAmbient(double Value);

	void setTCCname(String Value);

	String getTCCname();

	/* Computes whether conductor has burned down */
	void CalcIsqt(double CurrentMag);

	/* Restore the conductor and reset the i2t calcs */
	void ResetIsqt();

}
