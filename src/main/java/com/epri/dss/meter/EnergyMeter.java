package com.epri.dss.meter;

import java.io.FileWriter;

public interface EnergyMeter extends MeterClass {

	static final int NumPropsThisClass = 17;

	static final int NUM_EM_VBASE = 7;
	static final int NUM_EM_REGISTERS = 32 + 5 * NUM_EM_VBASE;   // Total Number of energy meter registers

	static final int REG_KWH                    = 0;
	static final int REG_KVARH                  = 1;
	static final int REG_MAX_KW                 = 2;
	static final int REG_MAX_KVA                = 3;
	static final int REG_ZONE_KWH               = 4;
	static final int REG_ZONE_KVARH             = 5;
	static final int REG_ZONE_MAX_KW            = 6;
	static final int REG_ZONE_MAX_KVA           = 7;
	static final int REG_OVERLOAD_KWH_NORM      = 8;  // max overload
	static final int REG_OVERLOAD_KWH_EMERG     = 9;
	static final int REG_LOAD_EEN               = 10;
	static final int REG_LOAD_UE                = 11;  // energy served below normal voltage
	static final int REG_ZONE_LOSSES_KWH        = 12;
	static final int REG_ZONE_LOSSES_KVARH      = 13;
	static final int REG_LOSSES_MAX_KW          = 14;
	static final int REG_LOSSES_MAX_KVAR        = 15;
	static final int REG_LOAD_LOSSES_KWH        = 16;
	static final int REG_LOAD_LOSSES_KVARH      = 17;
	static final int REG_NO_LOAD_LOSSES_KWH     = 18;
	static final int REG_NO_LOAD_LOSSES_KVARH   = 19;
	static final int REG_MAX_LOAD_LOSSES        = 20;
	static final int REG_MAX_NO_LOAD_LOSSES     = 21;
	static final int REG_LINE_LOSSES_KWH        = 22;
	static final int REG_TRANSFORMER_LOSSES_KWH = 23;
	static final int REG_LINE_MODE_LINE_LOSS    = 24;  // for 3-phase feeder lines
	static final int REG_ZERO_MODE_LINE_LOSS    = 25;
	static final int REG_3_PHASE_LINE_LOSS      = 26;
	static final int REG_1_PHASE_LINE_LOSS      = 27;
	static final int REG_GEN_KWH                = 28;
	static final int REG_GEN_KVARH              = 29;
	static final int REG_GEN_MAX_KW             = 30;
	static final int REG_GEN_MAX_KVA            = 31;
	static final int REG_VBASE_START            = 32;  // anchor for the voltage base loss registers

	void setSaveDemandInterval(boolean value);

	void setDIVerbose(boolean value);

	boolean isSaveDemandInterval();

	boolean isDIVerbose();

	double[] getDI_RegisterTotals();

	void setDI_RegisterTotals(double[] DI_RegisterTotals);

	String getDI_Dir();

	void setDI_Dir(String DI_Dir);

	FileWriter getDI_Totals();

	void setDI_Totals(FileWriter DI_Totals);

	FileWriter getMeterTotals();

	void setMeterTotals(FileWriter meterTotals);

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

	int init(int handle);

	int newObject(String objName);

	void resetMeterZonesAll();

	void appendAllDIFiles();

	void openAllDIFiles();

	void closeAllDIFiles();

}
