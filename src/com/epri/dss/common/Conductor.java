package com.epri.dss.common;

public interface Conductor {

	public void Set_Ambient(double Value);

	public void Set_TCCname(String Value);

	public String Get_TCCname();

	/* Computes whether conductor has burned down */
	public void CalcIsqt(double CurrentMag);

	/* restore the conductor and reset the i2t calcs */
	public void ResetIsqt();
}
