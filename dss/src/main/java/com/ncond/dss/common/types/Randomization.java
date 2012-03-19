/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common.types;

public enum Randomization {
	NONE,  // 1.0
	GAUSSIAN,  // Gaussian around mean and std dev
	UNIFORM,
	LOGNORMAL;
}
