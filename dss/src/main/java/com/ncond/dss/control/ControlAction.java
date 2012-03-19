/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

public enum ControlAction {

	NONE       (1),
	OPEN       (2),
	CLOSE      (3),
	CTRL_RESET (4),
	LOCK       (5),
	UNLOCK     (6),
	TAPUP      (7),
	TAPDOWN    (8);

	private final int code;

	ControlAction(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}

}
