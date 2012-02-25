package com.ncond.dss.control.impl;

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
