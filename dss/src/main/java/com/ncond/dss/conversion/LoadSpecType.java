/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.conversion;

/**
 * Legal ways to define base load.
 */
public enum LoadSpecType {
	KW_PF,
	KW_KVAR,
	KVA_PF,
	XFKVA_ALLOCATIONFACTOR_PF,
	KWH_KWHDAYS24_CFACTOR_PF;
}
