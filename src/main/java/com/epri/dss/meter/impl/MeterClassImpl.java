package com.epri.dss.meter.impl;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.CktElementClassImpl;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.meter.MeterClass;

public class MeterClassImpl extends CktElementClassImpl implements MeterClass {

	private int NumMeterClassProps;

	public MeterClassImpl() {
		super();
		this.NumMeterClassProps = 0;
		this.DSSClassType = DSSClassDefs.METER_ELEMENT;
	}

	protected void countProperties() {
		NumProperties = NumProperties + NumMeterClassProps;
		super.countProperties();
	}

	protected void defineProperties() {
		ActiveProperty = ActiveProperty + NumMeterClassProps;
		super.defineProperties();
	}

	protected int classEdit(Object ActiveMeterObj, int ParamPointer) {

		if (ParamPointer > 0)
			super.classEdit(ActiveMeterObj, ParamPointer - NumMeterClassProps);

		return 0;
	}

	protected void classMakeLike(Object OtherObj) {
		new MeterElementImpl((DSSClass) OtherObj);
	}

	public void resetAll() {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Base MeterClass.resetAll reached for class: "+getName(), 760);
	}

	/**
	 * Force all monitors to take a sample.
	 */
	public void sampleAll() {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Base MeterClass.sampleAll reached for class: "+getName(), 761);
	}

	/**
	 * Force all monitors to save their buffers to disk.
	 */
	public void saveAll() {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Base MeterClass.saveAll reached for Class: "+getName(), 762);
	}

	public int getNumMeterClassProps() {
		return NumMeterClassProps;
	}

	public void setNumMeterClassProps(int numMeterClassProps) {
		this.NumMeterClassProps = numMeterClassProps;
	}

}
