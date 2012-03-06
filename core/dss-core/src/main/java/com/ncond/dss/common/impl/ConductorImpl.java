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

	@Override
	public void setAmbient(double Value) {
		ambientTemp = Value;
	}

	@Override
	public void setTCCName(String Value) {
		TCCName = Value.toLowerCase();
	}

	@Override
	public String getTCCName() {
		return TCCName;
	}

	/**
	 * Computes whether conductor has burned down.
	 */
	@Override
	public void calcIsqt(double CurrentMag) {
		DSS.doSimpleMsg("Need to implement Conductor.calcIsqt", 770);
	}

	/**
	 * Restore the conductor and reset the i2t calcs.
	 */
	@Override
	public void resetIsqt() {
		DSS.doSimpleMsg("Need to implement Conductor.resetIsqt", 771);
	}

	@Override
	public boolean isClosed() {
		return closed;
	}

	@Override
	public void setClosed(boolean value) {
		closed = value;
	}

	@Override
	public boolean isFuseBlown() {
		return fuseBlown;
	}

	@Override
	public void setFuseBlown(boolean value) {
		fuseBlown = value;
	}

}
