package com.ncond.dss.control;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;

abstract public class ControlElem extends CktElement {

	public static final double USER_BASE_ACTION_CODE = 100;

	private CktElement controlledElement;

	protected String elementName;
	protected int elementTerminal;
	/* If different than terminal */
	protected String controlledBusName;
	protected Bus controlledBus;
	protected String monitorVariable;
	protected int monitorVarIndex;
	protected double timeDelay, dblTraceParameter;
	protected boolean showEventLog;

	public ControlElem(DSSClass parClass) {
		super(parClass);

		objType = DSSClassDefs.CTRL_ELEMENT;
		dblTraceParameter = 0.0;
		monitorVariable = "";
		monitorVarIndex = 0;
		controlledElement = null;
		showEventLog = true;
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	public void doPendingAction(int code, int proxyHdl) {
		DSS.doSimpleMsg("Programming error: Reached base class for doPendingAction."+DSS.CRLF+"Device: "+getDSSClassName()+"."+getName(), 460);
	}

	public void reset() {
		DSS.doSimpleMsg("Programming error: Reached base class for reset."+DSS.CRLF+"Device: "+getDSSClassName()+"."+getName(), 461);
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	public void sample() {
		DSS.doSimpleMsg("Programming error: Reached base class for sample."+DSS.CRLF+"Device: "+getDSSClassName()+"."+getName(), 462);
	}

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

	public CktElement getControlledElement() {
		return controlledElement;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String name) {
		elementName = name;
	}

	public int getElementTerminal() {
		return elementTerminal;
	}

	public void setElementTerminal(int terminal) {
		elementTerminal = terminal;
	}

	public String getControlledBusName() {
		return controlledBusName;
	}

	public void setControlledBusName(String name) {
		controlledBusName = name;
	}

	public Bus getControlledBus() {
		return controlledBus;
	}

	public void setControlledBus(Bus bus) {
		controlledBus = bus;
	}

	public String getMonitorVariable() {
		return monitorVariable;
	}

	public void setMonitorVariable(String variable) {
		monitorVariable = variable;
	}

	public int getMonitorVarIndex() {
		return monitorVarIndex;
	}

	public void setMonitorVarIndex(int index) {
		monitorVarIndex = index;
	}

	public double getTimeDelay() {
		return timeDelay;
	}

	public void setTimeDelay(double delay) {
		timeDelay = delay;
	}

	public double getDblTraceParameter() {
		return dblTraceParameter;
	}

	public void setDblTraceParameter(double parameter) {
		dblTraceParameter = parameter;
	}

	public boolean isShowEventLog() {
		return showEventLog;
	}

	public void setShowEventLog(boolean showEventLog) {
		this.showEventLog = showEventLog;
	}

}
