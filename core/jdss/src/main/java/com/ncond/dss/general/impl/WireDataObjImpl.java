package com.ncond.dss.general.impl;

import java.io.PrintStream;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.general.WireData;
import com.ncond.dss.general.WireDataObj;

public class WireDataObjImpl extends ConductorDataObjImpl implements WireDataObj {

	public WireDataObjImpl(DSSClass parClass, String wireDataName) {
		super(parClass, wireDataName);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		super.initPropertyValues(arrayOffset + WireData.NumPropsThisClass);
	}
}
