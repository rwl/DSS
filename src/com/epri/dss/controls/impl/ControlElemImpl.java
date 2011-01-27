package com.epri.dss.controls.impl;

import com.epri.dss.common.Bus;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.controls.ControlElem;

public class ControlElemImpl extends DSSCktElement implements ControlElem {

	public enum ControlAction {
	    NONE,
	    OPEN,
	    CLOSE,
	    CTRL_RESET, // can't use the same name as file reset function
	    LOCK,
	    UNLOCK,
	    TAPUP,
	    TAPDOWN}

	private CktElement ControlledElement;

	protected String ElementName;
	protected int ElementTerminal;
	/* If different than terminal */
	protected String ControlledBusName;
	protected Bus ControlledBus;
	protected String MonitorVariable;
	protected int MonitorVarIndex;
	protected double TimeDelay, DblTraceParameter;

	public ControlElemImpl(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public String getElementName() {
		return ElementName;
	}

	public void setElementName(String elementName) {
		ElementName = elementName;
	}

	public int getElementTerminal() {
		return ElementTerminal;
	}

	public void setElementTerminal(int elementTerminal) {
		ElementTerminal = elementTerminal;
	}

	public String getControlledBusName() {
		return ControlledBusName;
	}

	public void setControlledBusName(String controlledBusName) {
		ControlledBusName = controlledBusName;
	}

	public Bus getControlledBus() {
		return ControlledBus;
	}

	public void setControlledBus(Bus controlledBus) {
		ControlledBus = controlledBus;
	}

	public String getMonitorVariable() {
		return MonitorVariable;
	}

	public void setMonitorVariable(String monitorVariable) {
		MonitorVariable = monitorVariable;
	}

	public int getMonitorVarIndex() {
		return MonitorVarIndex;
	}

	public void setMonitorVarIndex(int monitorVarIndex) {
		MonitorVarIndex = monitorVarIndex;
	}

	public double getTimeDelay() {
		return TimeDelay;
	}

	public void setTimeDelay(double timeDelay) {
		TimeDelay = timeDelay;
	}

	public double getDblTraceParameter() {
		return DblTraceParameter;
	}

	public void setDblTraceParameter(double dblTraceParameter) {
		DblTraceParameter = dblTraceParameter;
	}

	public void setControlledElement(CktElement Value) {

	}

	public CktElement getControlledElement() {
		return null;
	}

	/* Sample control quantities and set action times in Control Queue */
	public void sample() {

	}

	/* Do the action that is pending from last sample */
	public void doPendingAction(int Code, int ProxyHdl) {

	}

	public void reset() {

	}

}
