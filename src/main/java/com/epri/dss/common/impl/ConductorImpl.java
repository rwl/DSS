package com.epri.dss.common.impl;

import com.epri.dss.common.Conductor;

public class ConductorImpl implements Conductor {

	private String TCCName;
	private double ambientTemp;
	/** accumulated I2t */
	private double accumIsqt;

	/** Change this variable to indicate open or closed switch. */
	protected boolean closed;
	protected boolean fuseBlown;

	public ConductorImpl() {
		super();
		this.closed = true;
		this.fuseBlown = false;
		this.accumIsqt = 0.0;
		this.TCCName = "";
	}

	public void setAmbient(double Value) {
		ambientTemp = Value;
	}

	public void setTCCName(String Value) {
		TCCName = Value.toLowerCase();
	}

	public String getTCCName() {
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
	public void resetIsqt() {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Conductor.resetIsqt", 771);
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean value) {
		closed = value;
	}

	public boolean isFuseBlown() {
		return fuseBlown;
	}

	public void setFuseBlown(boolean value) {
		fuseBlown = value;
	}

}
