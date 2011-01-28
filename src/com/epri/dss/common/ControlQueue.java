package com.epri.dss.common;

import com.epri.dss.controls.impl.ControlElemImpl.ControlAction;
import com.epri.dss.controls.ControlElem;

public interface ControlQueue {

	void setTrace(boolean Value);

	boolean getTrace();

	int push(int Hour, double Sec, int Code, int ProxyHdl, ControlElem Owner);

	int push(int Hour, double Sec, ControlAction Code, int ProxyHdl,
			ControlElem Owner);

	void clear();

	void doAllActions();

	/* Do only actions with lowest time */
	boolean doNearestActions(int Hour, double Sec);

	/* Do actions with time <= t */
	boolean doActions(int Hour, double sec);

	boolean isEmpty();

	/* Delete queue item by handle */
	void delete(int Hdl);

	void showQueue(String FileName);

}
