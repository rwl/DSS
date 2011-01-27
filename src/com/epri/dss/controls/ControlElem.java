package com.epri.dss.controls;

import com.epri.dss.common.Bus;
import com.epri.dss.common.CktElement;

public interface ControlElem extends CktElement {

	public static final double USER_BASE_ACTION_CODE = 100;

	public String getElementName();

	public void setElementName(String elementName);

	public int getElementTerminal();

	public void setElementTerminal(int elementTerminal);

	public String getControlledBusName();

	public void setControlledBusName(String controlledBusName);

	public Bus getControlledBus();

	public void setControlledBus(Bus controlledBus);

	public String getMonitorVariable();

	public void setMonitorVariable(String monitorVariable);

	public int getMonitorVarIndex();

	public void setMonitorVarIndex(int monitorVarIndex);

	public double getTimeDelay();

	public void setTimeDelay(double timeDelay);

	public double getDblTraceParameter();

	public void setDblTraceParameter(double dblTraceParameter);

	public void setControlledElement(CktElement Value);

	public CktElement getControlledElement();

	/* Sample control quantities and set action times in Control Queue */
	public void sample();

	/* Do the action that is pending from last sample */
	public void doPendingAction(int Code, int ProxyHdl);

	public void reset();

}
