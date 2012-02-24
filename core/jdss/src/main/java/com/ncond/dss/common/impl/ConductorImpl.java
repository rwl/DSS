package com.ncond.dss.common.impl;

import com.ncond.dss.common.Conductor;

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
		closed = true;
		fuseBlown = false;
		accumIsqt = 0.0;
		TCCName = "";
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
		DSSGlobals.doSimpleMsg("Need to implement Conductor.calcIsqt", 770);
	}

	/**
	 * Restore the conductor and reset the i2t calcs.
	 */
	public void resetIsqt() {
		DSSGlobals.doSimpleMsg("Need to implement Conductor.resetIsqt", 771);
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
