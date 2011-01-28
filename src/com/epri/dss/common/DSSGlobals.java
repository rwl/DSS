package com.epri.dss.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.PointerList;

public interface DSSGlobals {

	static final String CRLF = System.getProperty("line.separator");
	static final double PI = 3.14159265359;
	static final double TwoPi = 2.0 * PI;
	static final double RadiansToDegrees = 57.29577951;
	static final double EPSILON = 1.0e-12;   // Default tiny floating point
	static final double EPSILON2 = 1.0e-3;   // Default for Real number mismatch testing

	// Load model types for solution
	static final int POWERFLOW  = 1;
	static final int ADMITTANCE = 2;

	// For YPrim matrices
	static final int ALL_YPRIM = 0;
	static final int SERIES = 1;
	static final int SHUNT  = 2;

    /* Control Modes */
	static final int CONTROLSOFF = -1;
	static final int EVENTDRIVEN =  1;
	static final int TIMEDRIVEN  =  2;
	static final int STATIC      =  0;

    /* Randomization Constants */
	static final int GAUSSIAN  = 1;
	static final int UNIFORM   = 2;
	static final int LOGNORMAL = 3;

    /* Autoadd Constants */
	static final int GENADD = 1;
	static final int CAPADD = 2;

    /* ERRORS */
	static final int SOLUTION_ABORT = 99;

	/* 120-degree shift constant */
	static final double[] CALPHA = new double[] {-0.5, -0.866025};
	static final double SQRT2 = Math.sqrt(2.0);
	static final double SQRT3 = Math.sqrt(3.0);
	static final double InvSQRT3 = 1.0 / SQRT3;
	static final double InvSQRT3x1000 = InvSQRT3 * 1000.0;

	boolean isDLLFirstTime();

	void setDLLFirstTime(boolean dLLFirstTime);

	PrintStream getDLLDebugFile();

	void setDLLDebugFile(PrintStream dLLDebugFile);

	String getDSS_IniFileName();

	void setDSS_IniFileName(String dSS_IniFileName);

	IniRegSave getDSS_Registry();

	void setDSS_Registry(IniRegSave dSS_Registry);

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

	PointerList getCircuits();

	void setCircuits(PointerList circuits);

	PointerList getDSSObjs();

	void setDSSObjs(PointerList dSSObjs);

	Parser getAuxParser();

	void setAuxParser(Parser auxParser);

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

	boolean isRedirect_Abort();

	void setRedirect_Abort(boolean redirect_Abort);

	boolean isIn_Redirect();

	void setIn_Redirect(boolean in_Redirect);

	boolean isDIFilesAreOpen();

	void setDIFilesAreOpen(boolean dIFilesAreOpen);

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

	DSSMonitor getMonitorClass();

	void setMonitorClass(DSSMonitor monitorClass);

	Sensor getSensorClass();

	void setSensorClass(Sensor sensorClass);

	TCC_Curve getTCC_CurveClass();

	void setTCC_CurveClass(TCC_Curve tCC_CurveClass);

	WireData getWireDataClass();

	void setWireDataClass(WireData wireDataClass);

	LineSpacing getLineSpacingClass();

	void setLineSpacingClass(LineSpacing lineSpacingClass);

	Storage getStorageClass();

	void setStorageClass(Storage storageClass);

	void doErrorMsg(String S, String Emsg, String ProbCause, int ErrNum);

	void doSimpleMsg(String S, int ErrNum);

	void clearAllCircuits();

	/* Set object active by name */
	void setObject(String Param);

	int setActiveBus(String BusName);

	void makeNewCircuit(String Name);

	/* Append a string to Global result, separated by commas */
	void appendGlobalResult(String S);

	/* Separate by CRLF */
	void appendGlobalResultCRLF(String S);

	void WriteDLLDebugFile(String S);

	void readDSS_Registry();

	void writeDSS_Registry();

	boolean isDSSDLL(String Fname);

	String getDSSVersion();
}
