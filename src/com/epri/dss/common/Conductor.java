package com.epri.dss.common;

public interface Conductor {

	public boolean isClosed();

	/* change this variable to indicate open or closed switch */
	public void setClosed(boolean closed);

	public boolean isFuseBlown();

	public void setFuseBlown(boolean fuseBlown);

	public void setAmbient(double Value);

	public void setTCCname(String Value);

	public String getTCCname();

	/* Computes whether conductor has burned down */
	public void CalcIsqt(double CurrentMag);

	/* Restore the conductor and reset the i2t calcs */
	public void ResetIsqt();
}
