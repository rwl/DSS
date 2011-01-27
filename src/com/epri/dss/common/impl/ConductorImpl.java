package com.epri.dss.common.impl;

public class ConductorImpl {

	private String TCCName;
	private double AmbientTemp;
	/* Accumulated I2t */
	private double Accum_Isqt;

	/* change this variable to indicate open or closed switch */
	protected boolean Closed;
	protected boolean FuseBlown;

	public ConductorImpl() {
		this.Closed = true;
		this.FuseBlown = false;
		this.Accum_Isqt = 0.0;
		this.TCCName = "";
	}

	public boolean isClosed() {
		return Closed;
	}

	public void setClosed(boolean closed) {
		Closed = closed;
	}

	public boolean isFuseBlown() {
		return FuseBlown;
	}

	public void setFuseBlown(boolean fuseBlown) {
		FuseBlown = fuseBlown;
	}

	public void setAmbient(double Value) {

	}

	public void setTCCname(String Value) {

	}

	public String getTCCname() {
		return null;
	}

	/* Computes whether conductor has burned down */
	public void CalcIsqt(double CurrentMag) {

	}

	/* restore the conductor and reset the i2t calcs */
	public void ResetIsqt() {

	}

}
