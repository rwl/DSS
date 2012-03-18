/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;

@Getter @Setter
abstract public class ControlElem extends CktElement {

	public static final double USER_BASE_ACTION_CODE = 100;

	private CktElement controlledElement;

	protected String elementName;
	protected int elementTerminalIdx;
	/* If different than terminal */
	protected String controlledBusName;
	protected Bus controlledBus;
	protected String monitorVariable;
	protected int monitorVarIdx;
	protected double timeDelay, traceParameter;
	protected boolean showEventLog;

	public ControlElem(DSSClass parClass) {
		super(parClass);

		objType = DSSClassDefs.CTRL_ELEMENT;
		traceParameter = 0.0;
		monitorVariable = "";
		monitorVarIdx = 0;
		controlledElement = null;
		showEventLog = true;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	abstract public void doPendingAction(int code, int proxyHdl);

	abstract public void reset();

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	abstract public void sample();

	public void setControlledElement(CktElement value) {
		try {
			// check for reassignment
			if (controlledElement != null)
				controlledElement.setHasControl(false);
		} finally {
			controlledElement = value;
			if (controlledElement != null) {
				controlledElement.setHasControl(true);
				controlledElement.setControlElement(this);
			}
		}
	}

}
