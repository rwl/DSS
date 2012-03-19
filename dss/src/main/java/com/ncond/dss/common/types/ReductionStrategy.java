/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common.types;

public enum ReductionStrategy {
	DEFAULT,
	STUBS,
	TAP_ENDS,
	MERGE_PARALLEL,
	BREAK_LOOP,
	DANGLING,
	SWITCHES
}