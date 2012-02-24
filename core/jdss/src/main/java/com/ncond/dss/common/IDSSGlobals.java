package com.ncond.dss.common;

import java.io.PrintStream;

import com.ncond.dss.conversion.Storage;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.GrowthShape;
import com.ncond.dss.general.LineSpacing;
import com.ncond.dss.general.LoadShape;
import com.ncond.dss.general.Spectrum;
import com.ncond.dss.general.TCC_Curve;
import com.ncond.dss.general.WireData;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.Monitor;
import com.ncond.dss.meter.Sensor;

public interface IDSSGlobals {

	boolean isDLLFirstTime();

	void setDLLFirstTime(boolean DLLFirstTime);

	PrintStream getDLLDebugFile();

	void setDLLDebugFile(PrintStream DLLDebugFile);

	String getDSSIniFileName();

	void setDSSIniFileName(String fileName);

//	IniRegSave getDSS_Registry();

//	void setDSS_Registry(IniRegSave dSS_Registry);

	boolean isDLL();

	void setDLL(boolean isDLL);

	boolean isNoFormsAllowed();

	void setNoFormsAllowed(boolean noFormsAllowed);

	Circuit getActiveCircuit();

	void setActiveCircuit(Circuit activeCircuit);

	DSSClass getActiveDSSClass();

	void setActiveDSSClass(DSSClass activeDSSClass);

	int getLastClassReferenced();

	void setLastClassReferenced(int lastClassReferenced);

	DSSObject getActiveDSSObject();

	void setActiveDSSObject(DSSObject activeDSSObject);

	int getNumCircuits();

	void setNumCircuits(int numCircuits);

	int getMaxCircuits();

	void setMaxCircuits(int maxCircuits);

	int getMaxBusLimit();

	void setMaxBusLimit(int maxBusLimit);

	int getMaxAllocationIterations();

	void setMaxAllocationIterations(int maxAllocationIterations);

//	PointerList getCircuits();
//
//	void setCircuits(PointerList circuits);
//
//	PointerList getDSSObjs();
//
//	void setDSSObjs(PointerList DSSObjs);
//
//	Parser getAuxParser();
//
//	void setAuxParser(Parser auxParser);

	boolean isErrorPending();

	void setErrorPending(boolean errorPending);

	int getCmdResult();

	void setCmdResult(int cmdResult);

	int getErrorNumber();

	void setErrorNumber(int errorNumber);

	String getLastErrorMessage();

	void setLastErrorMessage(String lastErrorMessage);

	String getLastFileCompiled();

	void setLastFileCompiled(String lastFileCompiled);

	boolean isLastCommandWasCompile();

	void setLastCommandWasCompile(boolean lastCommandWasCompile);

	boolean isSolutionAbort();

	void setSolutionAbort(boolean solutionAbort);

	boolean isInShowResults();

	void setInShowResults(boolean inShowResults);

	boolean isRedirectAbort();

	void setRedirectAbort(boolean redirectAbort);

	boolean isInRedirect();

	void setInRedirect(boolean inRedirect);

	boolean isDIFilesAreOpen();

	void setDIFilesAreOpen(boolean DIFilesAreOpen);

	boolean isAutoShowExport();

	void setAutoShowExport(boolean autoShowExport);

	boolean isSolutionWasAttempted();

	void setSolutionWasAttempted(boolean solutionWasAttempted);

	String getGlobalHelpString();

	void setGlobalHelpString(String globalHelpString);

	String getGlobalPropertyValue();

	void setGlobalPropertyValue(String globalPropertyValue);

	String getGlobalResult();

	void setGlobalResult(String globalResult);

	String getVersionString();

	void setVersionString(String versionString);

	String getDefaultEditor();

	void setDefaultEditor(String defaultEditor);

	String getCircuitName_();

	void setCircuitName_(String circuitName_);

	double getDefaultBaseFreq();

	void setDefaultBaseFreq(double defaultBaseFreq);

	double getDaisySize();

	void setDaisySize(double daisySize);

	LoadShape getLoadShapeClass();

	void setLoadShapeClass(LoadShape loadShapeClass);

	GrowthShape getGrowthShapeClass();

	void setGrowthShapeClass(GrowthShape growthShapeClass);

	Spectrum getSpectrumClass();

	void setSpectrumClass(Spectrum spectrumClass);

	DSSClass getSolutionClass();

	void setSolutionClass(DSSClass solutionClass);

	EnergyMeter getEnergyMeterClass();

	void setEnergyMeterClass(EnergyMeter energyMeterClass);

	Feeder getFeederClass();

	void setFeederClass(Feeder feederClass);

	Monitor getMonitorClass();

	void setMonitorClass(Monitor monitorClass);

	Sensor getSensorClass();

	void setSensorClass(Sensor sensorClass);

	TCC_Curve getTCC_CurveClass();

	void setTCC_CurveClass(TCC_Curve TCC_CurveClass);

	WireData getWireDataClass();

	void setWireDataClass(WireData wireDataClass);

	LineSpacing getLineSpacingClass();

	void setLineSpacingClass(LineSpacing lineSpacingClass);

	Storage getStorageClass();

	void setStorageClass(Storage storageClass);

	void doErrorMsg(String s, String emsg, String probCause, int errNum);

	void doSimpleMsg(String s, int errNum);

	void clearAllCircuits();

	/** Set object active by name */
	void setObject(String param);

	int setActiveBus(String busName);

	void makeNewCircuit(String name);

	/** Append a string to global result, separated by commas */
	void appendGlobalResult(String s);

	/** Separate by CRLF */
	void appendGlobalResultCRLF(String s);

	void WriteDLLDebugFile(String s);

	void readDSS_Registry();

	void writeDSS_Registry();

	boolean isDSSDLL(String fname);

	String getDSSVersion();

}
