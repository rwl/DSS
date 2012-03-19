/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.meter;

import com.ncond.dss.common.CktElementClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.general.DSSObject;

abstract public class MeterClass extends CktElementClass {

	private int numMeterClassProps;

	public MeterClass() {
		super();
		numMeterClassProps = 0;
		classType = DSSClassDefs.METER_ELEMENT;
	}

	@Override
	protected void countProperties() {
		numProperties = numProperties + numMeterClassProps;
		super.countProperties();
	}

	@Override
	protected void defineProperties() {
		activeProperty = activeProperty + numMeterClassProps;
		super.defineProperties();
	}

	@Override
	protected int classEdit(DSSObject activeMeterObj, int paramPointer) {
		if (paramPointer >= 0)
			super.classEdit(activeMeterObj, paramPointer - numMeterClassProps);
		return 0;
	}

	protected void classMakeLike(Object otherObj) {
//		new MeterElementImpl((DSSClass) otherObj);
	}

	abstract public void resetAll();

	/**
	 * Force all monitors to take a sample.
	 */
	abstract public void sampleAll();

	/**
	 * Force all monitors to save their buffers to disk.
	 */
	abstract public void saveAll();

	public int getNumMeterClassProps() {
		return numMeterClassProps;
	}

	public void setNumMeterClassProps(int numProps) {
		this.numMeterClassProps = numProps;
	}

}
