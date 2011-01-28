package com.epri.dss.controls;

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

	void setControlledElement(CktElement Value);

	CktElement getControlledElement();

	/* Sample control quantities and set action times in Control Queue */
	void sample();

	/* Do the action that is pending from last sample */
	void doPendingAction(int Code, int ProxyHdl);

	void reset();

}
