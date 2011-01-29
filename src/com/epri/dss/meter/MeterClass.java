package com.epri.dss.meter;

import com.epri.dss.common.CktElementClass;

public interface MeterClass extends CktElementClass {

	int getNumMeterClassProps();

	void setNumMeterClassProps(int numMeterClassProps);

	void resetAll();

	/* Force all monitors to take a sample */
	void sampleAll();

	/* Force all monitors to save their buffers to disk */
	void saveAll();

}
