package com.ncond.dss.meter;

import com.ncond.dss.common.CktElementClass;

public interface MeterClass extends CktElementClass {

	int getNumMeterClassProps();

	void setNumMeterClassProps(int numMeterClassProps);

	void resetAll();

	/** Force all monitors to take a sample */
	void sampleAll();

	/** Force all monitors to save their buffers to disk */
	void saveAll();

}
