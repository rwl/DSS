package com.epri.dss.meter;

public interface SystemMeter {

	void takeSample();

	void reset();

	void save();

}
