/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

public enum StorageState {
	CHARGING (-1),
	IDLING (0),
	DISCHARGING (1),
	EXTERNAL (3);

	private int code;

	private StorageState(int code) {
		this.code = code;
	}

	public int code() {
		return this.code;
	}
}
