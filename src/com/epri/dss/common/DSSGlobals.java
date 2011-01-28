package com.epri.dss.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.PointerList;

public interface DSSGlobals {

	public static final String CRLF = System.getProperty("line.separator");
	public static final double PI = 3.14159265359;
	public static final double TwoPi = 2.0 * PI;
	public static final double RadiansToDegrees = 57.29577951;
	public static final double EPSILON = 1.0e-12;   // Default tiny floating point
	public static final double EPSILON2 = 1.0e-3;   // Default for Real number mismatch testing

	// Load model types for solution
	public static final int POWERFLOW  = 1;
	public static final int ADMITTANCE = 2;

	// For YPrim matrices
	public static final int ALL_YPRIM = 0;
	public static final int SERIES = 1;
	public static final int SHUNT  = 2;

    /* Control Modes */
	public static final int CONTROLSOFF = -1;
	public static final int EVENTDRIVEN =  1;
	public static final int TIMEDRIVEN  =  2;
	public static final int STATIC      =  0;

    /* Randomization Constants */
	public static final int GAUSSIAN  = 1;
	public static final int UNIFORM   = 2;
	public static final int LOGNORMAL = 3;

    /* Autoadd Constants */
	public static final int GENADD = 1;
	public static final int CAPADD = 2;

    /* ERRORS */
	public static final int SOLUTION_ABORT = 99;

	/* 120-degree shift constant */
	public static final double[] CALPHA = new double[] {-0.5, -0.866025};
	public static final double SQRT2 = Math.sqrt(2.0);
	public static final double SQRT3 = Math.sqrt(3.0);
	public static final double InvSQRT3 = 1.0 / SQRT3;
	public static final double InvSQRT3x1000 = InvSQRT3 * 1000.0;

	public boolean isDLLFirstTime();

	public void setDLLFirstTime(boolean dLLFirstTime);

	public PrintStream getDLLDebugFile();

	public void setDLLDebugFile(PrintStream dLLDebugFile);

	public String getDSS_IniFileName();

	public void setDSS_IniFileName(String dSS_IniFileName);

	public IniRegSave getDSS_Registry();

	public void setDSS_Registry(IniRegSave dSS_Registry);

	public boolean isDLL();

	public void setDLL(boolean isDLL);

	public boolean isNoFormsAllowed();

	public void setNoFormsAllowed(boolean noFormsAllowed);

	public Circuit getActiveCircuit();

	public void setActiveCircuit(Circuit activeCircuit);

	public DSSClass getActiveDSSClass();

	public void setActiveDSSClass(DSSClass activeDSSClass);

	public int getLastClassReferenced();

	public void setLastClassReferenced(int lastClassReferenced);

	public DSSObject getActiveDSSObject();

	public void setActiveDSSObject(DSSObject activeDSSObject);

	public int getNumCircuits();

	public void setNumCircuits(int numCircuits);

	public int getMaxCircuits();

	public void setMaxCircuits(int maxCircuits);

	public int getMaxBusLimit();

	public void setMaxBusLimit(int maxBusLimit);

	public int getMaxAllocationIterations();

	public void setMaxAllocationIterations(int maxAllocationIterations);

	public PointerList getCircuits();

	public void setCircuits(PointerList circuits);

	public PointerList getDSSObjs();

	public void setDSSObjs(PointerList dSSObjs);

	public Parser getAuxParser();

	public void setAuxParser(Parser auxParser);

	public boolean isErrorPending();

	public void setErrorPending(boolean errorPending);

	public int getCmdResult();

	public void setCmdResult(int cmdResult);

	public int getErrorNumber();

	public void setErrorNumber(int errorNumber);

	public String getLastErrorMessage();

	public void setLastErrorMessage(String lastErrorMessage);

	public String getLastFileCompiled();

	public void setLastFileCompiled(String lastFileCompiled);

	public boolean isLastCommandWasCompile();

	public void setLastCommandWasCompile(boolean lastCommandWasCompile);

	public boolean isSolutionAbort();

	public void setSolutionAbort(boolean solutionAbort);

	public boolean isInShowResults();

	public void setInShowResults(boolean inShowResults);

	public boolean isRedirect_Abort();

	public void setRedirect_Abort(boolean redirect_Abort);

	public boolean isIn_Redirect();

	public void setIn_Redirect(boolean in_Redirect);

	public boolean isDIFilesAreOpen();

	public void setDIFilesAreOpen(boolean dIFilesAreOpen);

	public boolean isAutoShowExport();

	public void setAutoShowExport(boolean autoShowExport);

	public boolean isSolutionWasAttempted();

	public void setSolutionWasAttempted(boolean solutionWasAttempted);

	public String getGlobalHelpString();

	public void setGlobalHelpString(String globalHelpString);

	public String getGlobalPropertyValue();

	public void setGlobalPropertyValue(String globalPropertyValue);

	public String getGlobalResult();

	public void setGlobalResult(String globalResult);

	public String getVersionString();

	public void setVersionString(String versionString);

	public String getDefaultEditor();

	public void setDefaultEditor(String defaultEditor);

	public String getCircuitName_();

	public void setCircuitName_(String circuitName_);

	public double getDefaultBaseFreq();

	public void setDefaultBaseFreq(double defaultBaseFreq);

	public double getDaisySize();

	public void setDaisySize(double daisySize);

	public LoadShape getLoadShapeClass();

	public void setLoadShapeClass(LoadShape loadShapeClass);

	public GrowthShape getGrowthShapeClass();

	public void setGrowthShapeClass(GrowthShape growthShapeClass);

	public Spectrum getSpectrumClass();

	public void setSpectrumClass(Spectrum spectrumClass);

	public DSSClass getSolutionClass();

	public void setSolutionClass(DSSClass solutionClass);

	public EnergyMeter getEnergyMeterClass();

	public void setEnergyMeterClass(EnergyMeter energyMeterClass);

	public Feeder getFeederClass();

	public void setFeederClass(Feeder feederClass);

	public DSSMonitor getMonitorClass();

	public void setMonitorClass(DSSMonitor monitorClass);

	public Sensor getSensorClass();

	public void setSensorClass(Sensor sensorClass);

	public TCC_Curve getTCC_CurveClass();

	public void setTCC_CurveClass(TCC_Curve tCC_CurveClass);

	public WireData getWireDataClass();

	public void setWireDataClass(WireData wireDataClass);

	public LineSpacing getLineSpacingClass();

	public void setLineSpacingClass(LineSpacing lineSpacingClass);

	public Storage getStorageClass();

	public void setStorageClass(Storage storageClass);

	public void doErrorMsg(String S, String Emsg, String ProbCause, int ErrNum);

	public void doSimpleMsg(String S, int ErrNum);

	public void clearAllCircuits();

	/* Set object active by name */
	public void setObject(String Param);

	public int setActiveBus(String BusName);

	public void makeNewCircuit(String Name);

	/* Append a string to Global result, separated by commas */
	public void appendGlobalResult(String S);

	/* Separate by CRLF */
	public void appendGlobalResultCRLF(String S);

	public void WriteDLLDebugFile(String S);

	public void readDSS_Registry();

	public void writeDSS_Registry();

	public boolean isDSSDLL(String Fname);

	public String getDSSVersion();
}
