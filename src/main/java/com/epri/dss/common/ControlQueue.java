package com.epri.dss.common;

import org.apache.commons.lang.mutable.MutableDouble;
import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.control.ControlElem;
import com.epri.dss.control.impl.ControlAction;

public interface ControlQueue {

	void setTrace(boolean Value);

	boolean getTrace();

	int push(int hour, double sec, int code, int proxyHdl, ControlElem owner);

	int push(int hour, double sec, ControlAction code, int proxyHdl, ControlElem owner);

	void clear();

	void doAllActions();

	/** Do only actions with lowest time */
	boolean doNearestActions(MutableInt hour, MutableDouble sec);

	/** Do actions with time <= t */
	boolean doActions(int hour, double sec);

	boolean isEmpty();

	/** Delete queue item by handle */
	void delete(int hdl);

	void showQueue(String fileName);

}
