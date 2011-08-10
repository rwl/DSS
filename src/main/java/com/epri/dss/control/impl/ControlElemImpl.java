package com.epri.dss.control.impl;

import com.epri.dss.common.Bus;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.control.ControlElem;

public class ControlElemImpl extends DSSCktElement implements ControlElem {

	private CktElement ControlledElement;

	protected String ElementName;
	protected int ElementTerminal;
	/* If different than terminal */
	protected String ControlledBusName;
	protected Bus ControlledBus;
	protected String MonitorVariable;
	protected int MonitorVarIndex;
	protected double TimeDelay, DblTraceParameter;

	public ControlElemImpl(DSSClass ParClass) {
		super(ParClass);

		this.DSSObjType = DSSClassDefs.CTRL_ELEMENT;
		this.DblTraceParameter = 0.0;
		this.MonitorVariable = "";
		this.MonitorVarIndex = 0;
		setControlledElement(null);
	}

	/**
	 * Do the action that is pending from last sample.
	 */
	public void doPendingAction(int Code, int ProxyHdl) {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Reached base class for DoPendingAction."+DSSGlobals.CRLF+"Device: "+getDSSClassName()+"."+getName(), 460);
	}

	public void reset() {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Reached base class for reset."+DSSGlobals.CRLF+"Device: "+getDSSClassName()+"."+getName(), 461);
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	public void sample() {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Reached base class for sample."+DSSGlobals.CRLF+"Device: "+getDSSClassName()+"."+getName(), 462);
	}

	public void setControlledElement(CktElement Value) {
		try {
			// check for reassignment
			if (ControlledElement != null)
				ControlledElement.setHasControl(false);
		} finally {
			ControlledElement = Value;
			if (ControlledElement != null) {
				ControlledElement.setHasControl(true);
				ControlledElement.setControlElement(this);
			}
		}
	}

	public CktElement getControlledElement() {
		return ControlledElement;
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

}
