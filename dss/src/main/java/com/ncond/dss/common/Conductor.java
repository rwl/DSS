/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

public class Conductor {

	private String TCCName;
	@SuppressWarnings("unused") private double ambientTemp;
	/** accumulated I2t */
	@SuppressWarnings("unused") private double accumIsqt;

	/** Change this variable to indicate open or closed switch. */
	protected boolean closed;
	protected boolean fuseBlown;

	public Conductor() {
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
		DSS.doSimpleMsg("Need to implement Conductor.calcIsqt", 770);
	}

	/**
	 * Restore the conductor and reset the i2t calcs.
	 */
	public void resetIsqt() {
		DSS.doSimpleMsg("Need to implement Conductor.resetIsqt", 771);
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
