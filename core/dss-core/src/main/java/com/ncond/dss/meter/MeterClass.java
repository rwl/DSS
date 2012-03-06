package com.ncond.dss.meter;

import com.ncond.dss.common.CktElementClass;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.general.DSSObject;

abstract public class MeterClass extends CktElementClass {

	private int numMeterClassProps;

	public MeterClass() {
		super();
		numMeterClassProps = 0;
		classType = DSSClassDefs.METER_ELEMENT;
	}

	protected void countProperties() {
		numProperties = numProperties + numMeterClassProps;
		super.countProperties();
	}

	protected void defineProperties() {
		activeProperty = activeProperty + numMeterClassProps;
		super.defineProperties();
	}

	protected int classEdit(DSSObject activeMeterObj, int paramPointer) {
		if (paramPointer >= 0)
			super.classEdit(activeMeterObj, paramPointer - numMeterClassProps);

		return 0;
	}

	protected void classMakeLike(Object otherObj) {
//		new MeterElementImpl((DSSClass) otherObj);
	}

	public void resetAll() {
		DSS.doSimpleMsg("Programming Error: Base MeterClass.resetAll reached for class: "+getName(), 760);
	}

	/**
	 * Force all monitors to take a sample.
	 */
	public void sampleAll() {
		DSS.doSimpleMsg("Programming Error: Base MeterClass.sampleAll reached for class: "+getName(), 761);
	}

	/**
	 * Force all monitors to save their buffers to disk.
	 */
	public void saveAll() {
		DSS.doSimpleMsg("Programming Error: Base MeterClass.saveAll reached for Class: "+getName(), 762);
	}

	public int getNumMeterClassProps() {
		return numMeterClassProps;
	}

	public void setNumMeterClassProps(int numProps) {
		this.numMeterClassProps = numProps;
	}

}
