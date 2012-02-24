package com.ncond.dss.control;

import com.ncond.dss.control.impl.ControlAction;

public interface SwtControlObj extends ControlElem {

	ControlAction getPresentState();

	boolean isLocked();

	// FIXME Private method in OpenDSS
	void interpretSwitchAction(String Action);

	// FIXME Private members in OpenDSS

	void setPresentState(ControlAction presentState);

	void setLocked(boolean locked);

}
