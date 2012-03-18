/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

public enum RelayControlType {
	CURRENT,  // default
	VOLTAGE,
	REVPOWER,
	NEGCURRENT,
	NEGVOLTAGE,
	GENERIC;  // use this for frequency, etc. generic over/under relay
}
