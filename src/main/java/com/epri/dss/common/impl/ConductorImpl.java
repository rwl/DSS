package com.epri.dss.common.impl;

import com.epri.dss.common.Conductor;

public class ConductorImpl implements Conductor {

	private String TCCName;
	private double AmbientTemp;
	/** accumulated I2t */
	private double Accum_Isqt;

	/** Change this variable to indicate open or closed switch. */
	protected boolean Closed;
	protected boolean FuseBlown;

	public ConductorImpl() {
		super();
		this.Closed = true;
		this.FuseBlown = false;
		this.Accum_Isqt = 0.0;
		this.TCCName = "";
	}

	public void setAmbient(double Value) {
		AmbientTemp = Value;
	}

	public void setTCCname(String Value) {
		TCCName = Value.toLowerCase();
	}

	public String getTCCname() {
		return TCCName;
	}

	/**
	 * Computes whether conductor has burned down.
	 */
	public void calcIsqt(double CurrentMag) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Conductor.calcIsqt", 770);
	}

	/**
	 * Restore the conductor and reset the i2t calcs.
	 */
	public void ResetIsqt() {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Conductor.resetIsqt", 771);
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

}
