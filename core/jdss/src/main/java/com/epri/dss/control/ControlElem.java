package com.epri.dss.control;

import com.epri.dss.common.Bus;
import com.epri.dss.common.CktElement;

public interface ControlElem extends CktElement {

	static final double USER_BASE_ACTION_CODE = 100;

	String getElementName();

	void setElementName(String elementName);

	int getElementTerminal();

	void setElementTerminal(int elementTerminal);

	String getControlledBusName();

	void setControlledBusName(String controlledBusName);

	Bus getControlledBus();

	void setControlledBus(Bus controlledBus);

	String getMonitorVariable();

	void setMonitorVariable(String monitorVariable);

	int getMonitorVarIndex();

	void setMonitorVarIndex(int monitorVarIndex);

	double getTimeDelay();

	void setTimeDelay(double timeDelay);

	double getDblTraceParameter();

	void setDblTraceParameter(double dblTraceParameter);

	void setControlledElement(CktElement value);

	CktElement getControlledElement();

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	void sample();

	/**
	 * Do the action that is pending from last sample.
	 */
	void doPendingAction(int code, int proxyHdl);

	void reset();

	boolean isShowEventLog();

	void setShowEventLog(boolean showEventLog);

}
