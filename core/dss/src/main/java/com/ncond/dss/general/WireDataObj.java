/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;

import com.ncond.dss.common.DSSClass;

/**
 * Used for overhead line impedances.
 */
public class WireDataObj extends ConductorDataObj {

	public WireDataObj(DSSClass parClass, String wireDataName) {
		super(parClass, wireDataName);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		super.initPropertyValues(arrayOffset + WireData.NumPropsThisClass);
	}

}
