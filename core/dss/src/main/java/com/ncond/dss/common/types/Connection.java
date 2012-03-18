/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common.types;

public enum Connection {
	WYE,  // Y, wye, star or LN (neutral is explicit)
	DELTA;  // delta or LL (assume neutral is at zero)
}
