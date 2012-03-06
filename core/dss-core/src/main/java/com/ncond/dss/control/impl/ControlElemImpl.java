package com.ncond.dss.control.impl;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.CktElementImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.control.ControlElem;

abstract public class ControlElemImpl extends CktElementImpl implements ControlElem {

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

	public ControlElemImpl(DSSClass parClass) {
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
	@Override
	public void doPendingAction(int code, int proxyHdl) {
		DSS.doSimpleMsg("Programming error: Reached base class for doPendingAction."+DSS.CRLF+"Device: "+getDSSClassName()+"."+getName(), 460);
	}

	@Override
	public void reset() {
		DSS.doSimpleMsg("Programming error: Reached base class for reset."+DSS.CRLF+"Device: "+getDSSClassName()+"."+getName(), 461);
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	@Override
	public void sample() {
		DSS.doSimpleMsg("Programming error: Reached base class for sample."+DSS.CRLF+"Device: "+getDSSClassName()+"."+getName(), 462);
	}

	@Override
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

	@Override
	public CktElement getControlledElement() {
		return controlledElement;
	}

	@Override
	public String getElementName() {
		return elementName;
	}

	@Override
	public void setElementName(String name) {
		elementName = name;
	}

	@Override
	public int getElementTerminal() {
		return elementTerminal;
	}

	@Override
	public void setElementTerminal(int terminal) {
		elementTerminal = terminal;
	}

	@Override
	public String getControlledBusName() {
		return controlledBusName;
	}

	@Override
	public void setControlledBusName(String name) {
		controlledBusName = name;
	}

	@Override
	public Bus getControlledBus() {
		return controlledBus;
	}

	@Override
	public void setControlledBus(Bus bus) {
		controlledBus = bus;
	}

	@Override
	public String getMonitorVariable() {
		return monitorVariable;
	}

	@Override
	public void setMonitorVariable(String variable) {
		monitorVariable = variable;
	}

	@Override
	public int getMonitorVarIndex() {
		return monitorVarIndex;
	}

	@Override
	public void setMonitorVarIndex(int index) {
		monitorVarIndex = index;
	}

	@Override
	public double getTimeDelay() {
		return timeDelay;
	}

	@Override
	public void setTimeDelay(double delay) {
		timeDelay = delay;
	}

	@Override
	public double getDblTraceParameter() {
		return dblTraceParameter;
	}

	@Override
	public void setDblTraceParameter(double parameter) {
		dblTraceParameter = parameter;
	}

	@Override
	public boolean isShowEventLog() {
		return showEventLog;
	}

	@Override
	public void setShowEventLog(boolean showEventLog) {
		this.showEventLog = showEventLog;
	}

}
