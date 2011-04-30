package com.epri.dss.meter;

public interface SystemMeter {

	void takeSample();

	void reset();

	void save();

	// FIXME Protected method in OpenDSS
	void closeDemandIntervalFile();

	// FIXME Protected method in OpenDSS
	void appendDemandIntervalFile();

	// FIXME Protected method in OpenDSS
	void openDemandIntervalFile();

}
