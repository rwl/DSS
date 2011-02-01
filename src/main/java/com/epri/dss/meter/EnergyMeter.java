package com.epri.dss.meter;

import java.io.File;

public interface EnergyMeter extends MeterClass {

	void setSaveDemandInterval(boolean Value);

	boolean isSaveDemandInterval();

	boolean isDIVerbose();

	double[] getDI_RegisterTotals();

	void setDI_RegisterTotals(double[] dI_RegisterTotals);

	String getDI_Dir();

	void setDI_Dir(String dI_Dir);

	File getDI_Totals();

	void setDI_Totals(File dI_Totals);

	File getMeterTotals();

	void setMeterTotals(File meterTotals);

	SystemMeter getSystemMeter();

	void setSystemMeter(SystemMeter systemMeter);

	boolean isDo_OverloadReport();

	void setDo_OverloadReport(boolean do_OverloadReport);

	boolean isDo_VoltageExceptionReport();

	void setDo_VoltageExceptionReport(boolean do_VoltageExceptionReport);

	boolean isOverLoadFileIsOpen();

	void setOverLoadFileIsOpen(boolean overLoadFileIsOpen);

	boolean isVoltageFileIsOpen();

	void setVoltageFileIsOpen(boolean voltageFileIsOpen);

	int edit();

	int init(int Handle);

	int newObject(String ObjName);

	void resetMeterZonesAll();

	void appendAllDIFiles();

	void openAllDIFiles();

	void closeAllDIFiles();

}
