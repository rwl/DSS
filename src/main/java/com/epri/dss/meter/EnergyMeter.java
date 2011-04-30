package com.epri.dss.meter;

import java.io.File;
import java.io.FileWriter;

public interface EnergyMeter extends MeterClass {

	static final int NumPropsThisClass = 17;

	static final int NumEMVbase = 7;
	static final int NumEMRegisters = 32 + 5 * NumEMVbase;   // Total Number of energy meter registers

	static final int Reg_kWh               = 0;
	static final int Reg_kvarh             = 1;
	static final int Reg_MaxkW             = 2;
	static final int Reg_MaxkVA            = 3;
	static final int Reg_ZonekWh           = 4;
	static final int Reg_Zonekvarh         = 5;
	static final int Reg_ZoneMaxkW         = 6;
	static final int Reg_ZoneMaxkVA        = 7;
	static final int Reg_OverloadkWhNorm   = 8;    // Max overload
	static final int Reg_OverloadkWhEmerg  = 9;
	static final int Reg_LoadEEN           = 10;
	static final int Reg_LoadUE            = 11;  // Energy served below normal voltage
	static final int Reg_ZoneLosseskWh     = 12;
	static final int Reg_ZoneLosseskvarh   = 13;
	static final int Reg_LossesMaxkW       = 14;
	static final int Reg_LossesMaxkvar     = 15;
	static final int Reg_LoadLosseskWh     = 16;
	static final int Reg_LoadLosseskvarh   = 17;
	static final int Reg_NoLoadLosseskWh   = 18;
	static final int Reg_NoLoadLosseskvarh = 19;
	static final int Reg_MaxLoadLosses     = 20;
	static final int Reg_MaxNoLoadLosses   = 21;
	static final int Reg_LineLosseskWh     = 22;
	static final int Reg_TransformerLosseskWh = 23;
	static final int Reg_LineModeLineLoss  = 24;    // for 3-phase feeder lines
	static final int Reg_ZeroModeLineLoss  = 25;
	static final int Reg_3_phaseLineLoss   = 26;
	static final int Reg_1_phaseLineLoss   = 27;
	static final int Reg_GenkWh            = 28;
	static final int Reg_Genkvarh          = 29;
	static final int Reg_GenMaxkW          = 30;
	static final int Reg_GenMaxkVA         = 31;
	static final int Reg_VBaseStart        = 32;  // anchor for the voltage base loss registers

	void setSaveDemandInterval(boolean Value);

	void setDIVerbose(boolean Value);

	boolean isSaveDemandInterval();

	boolean isDIVerbose();

	double[] getDI_RegisterTotals();

	void setDI_RegisterTotals(double[] dI_RegisterTotals);

	String getDI_Dir();

	void setDI_Dir(String dI_Dir);

	FileWriter getDI_Totals();

	void setDI_Totals(FileWriter dI_Totals);

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

	int init(int Handle);

	int newObject(String ObjName);

	void resetMeterZonesAll();

	void appendAllDIFiles();

	void openAllDIFiles();

	void closeAllDIFiles();

}
