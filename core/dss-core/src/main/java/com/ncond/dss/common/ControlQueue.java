package com.ncond.dss.common;

import com.ncond.dss.control.ControlElem;
import com.ncond.dss.control.impl.ControlAction;

public interface ControlQueue {

	void setTrace(boolean Value);

	boolean getTrace();

	int push(int hour, double sec, int code, int proxyHdl, ControlElem owner);

	int push(int hour, double sec, ControlAction code, int proxyHdl, ControlElem owner);

	void clear();

	void doAllActions();

	/** Do only actions with lowest time */
	boolean doNearestActions(int[] hour, double[] sec);

	/** Do actions with time <= t */
	boolean doActions(int hour, double sec);

	boolean isEmpty();

	/** Delete queue item by handle */
	void delete(int hdl);

	void showQueue(String fileName);

}
