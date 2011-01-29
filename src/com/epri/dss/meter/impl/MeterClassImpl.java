package com.epri.dss.meter.impl;

import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.meter.MeterClass;

public class MeterClassImpl extends CktElementClassImpl implements MeterClass {

	private int numMeterClassProps;

	public MeterClassImpl() {
		// TODO Auto-generated constructor stub
	}

	public int getNumMeterClassProps() {
		return numMeterClassProps;
	}

	public void setNumMeterClassProps(int numMeterClassProps) {
		this.numMeterClassProps = numMeterClassProps;
	}

	protected int classEdit(Object ActiveMeterObj, int ParamPointer) {
		return 0;
	}

	protected void classMakeLike(Object OtherObj) {

	}

	protected void countProperties() {

	}

	protected void defineProperties() {

	}

	public void resetAll() {

	}

	/* Force all monitors to take a sample */
	public void sampleAll() {

	}

	/* Force all monitors to save their buffers to disk */
	public void saveAll() {

	}

}
