package com.ncond.dss.meter.impl;

import com.ncond.dss.common.impl.CktElementClassImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.meter.MeterClass;

abstract public class MeterClassImpl extends CktElementClassImpl implements MeterClass {

	private int numMeterClassProps;

	public MeterClassImpl() {
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

	@Override
	public void resetAll() {
		DSS.doSimpleMsg("Programming Error: Base MeterClass.resetAll reached for class: "+getName(), 760);
	}

	/**
	 * Force all monitors to take a sample.
	 */
	@Override
	public void sampleAll() {
		DSS.doSimpleMsg("Programming Error: Base MeterClass.sampleAll reached for class: "+getName(), 761);
	}

	/**
	 * Force all monitors to save their buffers to disk.
	 */
	@Override
	public void saveAll() {
		DSS.doSimpleMsg("Programming Error: Base MeterClass.saveAll reached for Class: "+getName(), 762);
	}

	@Override
	public int getNumMeterClassProps() {
		return numMeterClassProps;
	}

	@Override
	public void setNumMeterClassProps(int numProps) {
		this.numMeterClassProps = numProps;
	}

}
