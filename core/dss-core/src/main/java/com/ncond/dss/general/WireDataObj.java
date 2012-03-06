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
