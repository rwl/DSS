package com.ncond.dss.common;

import com.ncond.dss.control.ControlElem;
import com.ncond.dss.control.impl.ControlAction;

public interface ControlQueue {

	void setTrace(boolean Value);

	boolean isTrace();

	/**
	 * Add a control action to the queue, sorted by lowest time first.
	 *
	 * @return handle to the action
	 */
	int push(int hour, double sec, int code, int proxyHdl, ControlElem owner);

	/**
	 * Add a control action to the queue, sorted by lowest time first.
	 *
	 * @return handle to the action
	 */
	int push(int hour, double sec, ControlAction code, int proxyHdl, ControlElem owner);

	void clear();

	void doAllActions();

	/**
	 * Do only actions with lowest time.
	 * Do only those actions with the same delay time as the first action return time.
	 */
	boolean doNearestActions(int[] hour, double[] sec);

	/** Do actions with time <= t */
	boolean doActions(int hour, double sec);

	boolean isEmpty();

	/** Delete queue item by handle */
	void delete(int hdl);

	void showQueue(String fileName);

}
