package com.epri.dss.control;

import com.epri.dss.control.impl.ControlElemImpl.ControlAction;

public interface SwtControlObj extends ControlElem {

	ControlAction getPresentState();

	boolean isLocked();
	
}
