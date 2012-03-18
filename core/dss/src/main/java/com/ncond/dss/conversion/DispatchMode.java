/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

public enum DispatchMode {
	DEFAULT,
	LOAD,
	PRICE,

	// storage only
	EXTERNAL,
	FOLLOW;
}
