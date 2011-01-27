package com.epri.dss.common;

import com.epri.dss.controls.impl.ControlElemImpl.ControlAction;
import com.epri.dss.controls.ControlElem;

public interface ControlQueue {

	public void setTrace(boolean Value);

	public boolean getTrace();

	public int push(int Hour, double Sec, int Code, int ProxyHdl,
			ControlElem Owner);

	public int push(int Hour, double Sec, ControlAction Code, int ProxyHdl,
			ControlElem Owner);

	public void clear();

	public void doAllActions();

	/* Do only actions with lowest time */
	public boolean doNearestActions(int Hour, double Sec);

	/* Do actions with time <= t */
	public boolean doActions(int Hour, double sec);

	public boolean isEmpty();

	/* Delete queue item by handle */
	public void delete(int Hdl);

	public void showQueue(String FileName);

}
