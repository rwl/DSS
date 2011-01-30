package com.epri.dss.meter;

public interface MonitorObj extends MeterElement {

	String getCSVFileName();

	int getMode();

	void setMode(int mode);

//	MemoryStream getMonitorStream();
//
//	void setMonitorStream(MemoryStream monitorStream);

	int getSampleCount();

	void setSampleCount(int sampleCount);
	
	void resetIt();
	
	/* Saves present buffer to file */
	void save();
	
	void openMonitorStream();
	
	void clearMonitorStream();
	
	void closeMonitorStream();
	
	void translateToCSV(boolean Show);

}
