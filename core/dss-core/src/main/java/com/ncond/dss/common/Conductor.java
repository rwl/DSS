package com.ncond.dss.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Conductor {

	private String TCCName;
	private double ambientTemp;
	/** accumulated I2t */
	private double accumIsqt;

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

}
