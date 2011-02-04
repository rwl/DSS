package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.Feeder;
import com.epri.dss.common.Solution;
import com.epri.dss.conversion.Storage;
import com.epri.dss.executive.Executive;
import com.epri.dss.forms.DSSForms;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.GrowthShape;
import com.epri.dss.general.LineSpacing;
import com.epri.dss.general.LoadShape;
import com.epri.dss.general.Spectrum;
import com.epri.dss.general.TCC_Curve;
import com.epri.dss.general.WireData;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.Monitor;
import com.epri.dss.meter.Sensor;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.HashList;

public class DSSGlobals {

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
	static final Complex CALPHA = new Complex(-0.5, -0.866025);
	static final double SQRT2 = Math.sqrt(2.0);
	static final double SQRT3 = Math.sqrt(3.0);
	static final double InvSQRT3 = 1.0 / SQRT3;
	static final double InvSQRT3x1000 = InvSQRT3 * 1000.0;

	/** Variables */
	private boolean DLLFirstTime = true;
	private PrintStream DLLDebugFile;
	private String DSS_IniFileName = "OpenDSSPanel.ini";
	// Registry   (See Executive)
//	private IniRegSave DSS_Registry = IniRegSave("\\Software\\OpenDSS");

	private boolean IsDLL = false;
	private boolean NoFormsAllowed = false;

	private Circuit ActiveCircuit;
	private DSSClass ActiveDSSClass;
	private int LastClassReferenced;  // index of class of last thing edited
	private DSSObject ActiveDSSObject;
	private int NumCircuits = 0;
	private int MaxCircuits = 1;
	private int MaxBusLimit = 0; // Set in Validation
	private int MaxAllocationIterations = 2;
	private ArrayList<Circuit> Circuits;
	private ArrayList<DSSObject> DSSObjs;

	// Auxiliary parser for use by anybody for reparsing values
	private Parser AuxParser = Parser.getInstance();

	private boolean ErrorPending = false;
	private int CmdResult = 0;
	private int ErrorNumber = 0;
	private String LastErrorMessage = "";

	private String LastFileCompiled = "";
	private boolean LastCommandWasCompile = false;

	private boolean SolutionAbort = false;
	private boolean InShowResults = false;
	private boolean Redirect_Abort = false;
	private boolean In_Redirect = false;
	private boolean DIFilesAreOpen = false;
	private boolean AutoShowExport = false;
	private boolean SolutionWasAttempted = false;

	private String GlobalHelpString = "";
	private String GlobalPropertyValue = "";
	private String GlobalResult = "";
	private String VersionString = "Version " + getDSSVersion();

	private String DefaultEditor = "NotePad";     // normally, Notepad
	private String DSSFileName;// = GetDSSExeFile();     // Name of current exe or DLL
	private String DSSDirectory;// = new File(DSSFileName).getParent();     // where the current exe resides
	private String StartupDirectory = System.getProperty("user.dir") + "/";  // Where we started
	private String DSSDataDirectory;// = StartupDirectory;
	private String CircuitName_;     // Name of Circuit with a "_" appended

	private double DefaultBaseFreq = 60.0;
	private double DaisySize = 1.0;

	// Some commonly used classes   so we can find them easily
	private LoadShape LoadShapeClass;
	private GrowthShape GrowthShapeClass;
	private Spectrum SpectrumClass;
	private DSSClass SolutionClass;
	private EnergyMeter EnergyMeterClass;
	private Feeder FeederClass;
	private Monitor MonitorClass;
	private Sensor SensorClass;
	private TCC_Curve TCC_CurveClass;
	private WireData WireDataClass;
	private LineSpacing LineSpacingClass;
	private Storage StorageClass;

	private String[] EventStrings;
	private String[] SavedFileList;

	private ArrayList<DSSClass> DSSClassList;  // base class types
	private HashList ClassNames;

	// Private constructor prevents instantiation from other classes
	private DSSGlobals() {
	}

	/**
	 * DSSGlobalsHolder is loaded on the first execution of
	 * DSSGlobals.getInstance() or the first access to
	 * DSSGlobalsHolder.INSTANCE, not before.
	 */
	private static class DSSGlobalsHolder {
		public static final DSSGlobals INSTANCE = new DSSGlobals();
	}

	public static DSSGlobals getInstance() {
		return DSSGlobalsHolder.INSTANCE;
	}

	public boolean isDLLFirstTime() {
		return DLLFirstTime;
	}

	public void setDLLFirstTime(boolean dLLFirstTime) {
		DLLFirstTime = dLLFirstTime;
	}

	public PrintStream getDLLDebugFile() {
		return DLLDebugFile;
	}

	public void setDLLDebugFile(PrintStream dLLDebugFile) {
		DLLDebugFile = dLLDebugFile;
	}

	public String getDSS_IniFileName() {
		return DSS_IniFileName;
	}

	public void setDSS_IniFileName(String dSS_IniFileName) {
		DSS_IniFileName = dSS_IniFileName;
	}

//	public IniRegSave getDSS_Registry() {
//		return DSS_Registry;
//	}

//	public void setDSS_Registry(IniRegSave dSS_Registry) {
//		DSS_Registry = dSS_Registry;
//	}

	public boolean isDLL() {
		return IsDLL;
	}

	public void setDLL(boolean isDLL) {
		IsDLL = isDLL;
	}

	public boolean isNoFormsAllowed() {
		return NoFormsAllowed;
	}

	public void setNoFormsAllowed(boolean noFormsAllowed) {
		NoFormsAllowed = noFormsAllowed;
	}

	public Circuit getActiveCircuit() {
		return ActiveCircuit;
	}

	public void setActiveCircuit(Circuit activeCircuit) {
		ActiveCircuit = activeCircuit;
	}

	public DSSClass getActiveDSSClass() {
		return ActiveDSSClass;
	}

	public void setActiveDSSClass(DSSClass activeDSSClass) {
		ActiveDSSClass = activeDSSClass;
	}

	public int getLastClassReferenced() {
		return LastClassReferenced;
	}

	public void setLastClassReferenced(int lastClassReferenced) {
		LastClassReferenced = lastClassReferenced;
	}

	public DSSObject getActiveDSSObject() {
		return ActiveDSSObject;
	}

	public void setActiveDSSObject(DSSObjectImpl activeDSSObject) {
		ActiveDSSObject = activeDSSObject;
	}

	public int getNumCircuits() {
		return NumCircuits;
	}

	public void setNumCircuits(int numCircuits) {
		NumCircuits = numCircuits;
	}

	public int getMaxCircuits() {
		return MaxCircuits;
	}

	public void setMaxCircuits(int maxCircuits) {
		MaxCircuits = maxCircuits;
	}

	public int getMaxBusLimit() {
		return MaxBusLimit;
	}

	public void setMaxBusLimit(int maxBusLimit) {
		MaxBusLimit = maxBusLimit;
	}

	public int getMaxAllocationIterations() {
		return MaxAllocationIterations;
	}

	public void setMaxAllocationIterations(int maxAllocationIterations) {
		MaxAllocationIterations = maxAllocationIterations;
	}

	public ArrayList<Circuit> getCircuits() {
		return Circuits;
	}

	public void setCircuits(ArrayList<Circuit> circuits) {
		Circuits = circuits;
	}

	public ArrayList<DSSObject> getDSSObjs() {
		return DSSObjs;
	}

	public void setDSSObjs(ArrayList<DSSObject> dSSObjs) {
		DSSObjs = dSSObjs;
	}

	public Parser getAuxParser() {
		return AuxParser;
	}

	public void setAuxParser(Parser auxParser) {
		AuxParser = auxParser;
	}

	public boolean isErrorPending() {
		return ErrorPending;
	}

	public void setErrorPending(boolean errorPending) {
		ErrorPending = errorPending;
	}

	public int getCmdResult() {
		return CmdResult;
	}

	public void setCmdResult(int cmdResult) {
		CmdResult = cmdResult;
	}

	public int getErrorNumber() {
		return ErrorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		ErrorNumber = errorNumber;
	}

	public String getLastErrorMessage() {
		return LastErrorMessage;
	}

	public void setLastErrorMessage(String lastErrorMessage) {
		LastErrorMessage = lastErrorMessage;
	}

	public String getLastFileCompiled() {
		return LastFileCompiled;
	}

	public void setLastFileCompiled(String lastFileCompiled) {
		LastFileCompiled = lastFileCompiled;
	}

	public boolean isLastCommandWasCompile() {
		return LastCommandWasCompile;
	}

	public void setLastCommandWasCompile(boolean lastCommandWasCompile) {
		LastCommandWasCompile = lastCommandWasCompile;
	}

	public boolean isSolutionAbort() {
		return SolutionAbort;
	}

	public void setSolutionAbort(boolean solutionAbort) {
		SolutionAbort = solutionAbort;
	}

	public boolean isInShowResults() {
		return InShowResults;
	}

	public void setInShowResults(boolean inShowResults) {
		InShowResults = inShowResults;
	}

	public boolean isRedirect_Abort() {
		return Redirect_Abort;
	}

	public void setRedirect_Abort(boolean redirect_Abort) {
		Redirect_Abort = redirect_Abort;
	}

	public boolean isIn_Redirect() {
		return In_Redirect;
	}

	public void setIn_Redirect(boolean in_Redirect) {
		In_Redirect = in_Redirect;
	}

	public boolean isDIFilesAreOpen() {
		return DIFilesAreOpen;
	}

	public void setDIFilesAreOpen(boolean dIFilesAreOpen) {
		DIFilesAreOpen = dIFilesAreOpen;
	}

	public boolean isAutoShowExport() {
		return AutoShowExport;
	}

	public void setAutoShowExport(boolean autoShowExport) {
		AutoShowExport = autoShowExport;
	}

	public boolean isSolutionWasAttempted() {
		return SolutionWasAttempted;
	}

	public void setSolutionWasAttempted(boolean solutionWasAttempted) {
		SolutionWasAttempted = solutionWasAttempted;
	}

	public String getGlobalHelpString() {
		return GlobalHelpString;
	}

	public void setGlobalHelpString(String globalHelpString) {
		GlobalHelpString = globalHelpString;
	}

	public String getGlobalPropertyValue() {
		return GlobalPropertyValue;
	}

	public void setGlobalPropertyValue(String globalPropertyValue) {
		GlobalPropertyValue = globalPropertyValue;
	}

	public String getGlobalResult() {
		return GlobalResult;
	}

	public void setGlobalResult(String globalResult) {
		GlobalResult = globalResult;
	}

	public String getVersionString() {
		return VersionString;
	}

	public void setVersionString(String versionString) {
		VersionString = versionString;
	}

	public String getDefaultEditor() {
		return DefaultEditor;
	}

	public void setDefaultEditor(String defaultEditor) {
		DefaultEditor = defaultEditor;
	}

	public String getDSSFileName() {
		return DSSFileName;
	}

	public void setDSSFileName(String dSSFileName) {
		DSSFileName = dSSFileName;
	}

	public String getDSSDirectory() {
		return DSSDirectory;
	}

	public void setDSSDirectory(String dSSDirectory) {
		DSSDirectory = dSSDirectory;
	}

	public String getStartupDirectory() {
		return StartupDirectory;
	}

	public void setStartupDirectory(String startupDirectory) {
		StartupDirectory = startupDirectory;
	}

	public String getDSSDataDirectory() {
		return DSSDataDirectory;
	}

	public void setDSSDataDirectory(String dSSDataDirectory) {
		DSSDataDirectory = dSSDataDirectory;
	}

	public String getCircuitName_() {
		return CircuitName_;
	}

	public void setCircuitName_(String circuitName_) {
		CircuitName_ = circuitName_;
	}

	public double getDefaultBaseFreq() {
		return DefaultBaseFreq;
	}

	public void setDefaultBaseFreq(double defaultBaseFreq) {
		DefaultBaseFreq = defaultBaseFreq;
	}

	public double getDaisySize() {
		return DaisySize;
	}

	public void setDaisySize(double daisySize) {
		DaisySize = daisySize;
	}

	public LoadShape getLoadShapeClass() {
		return LoadShapeClass;
	}

	public void setLoadShapeClass(LoadShape loadShapeClass) {
		LoadShapeClass = loadShapeClass;
	}

	public GrowthShape getGrowthShapeClass() {
		return GrowthShapeClass;
	}

	public void setGrowthShapeClass(GrowthShape growthShapeClass) {
		GrowthShapeClass = growthShapeClass;
	}

	public Spectrum getSpectrumClass() {
		return SpectrumClass;
	}

	public void setSpectrumClass(Spectrum spectrumClass) {
		SpectrumClass = spectrumClass;
	}

	public DSSClass getSolutionClass() {
		return SolutionClass;
	}

	public void setSolutionClass(DSSClass solutionClass) {
		SolutionClass = solutionClass;
	}

	public EnergyMeter getEnergyMeterClass() {
		return EnergyMeterClass;
	}

	public void setEnergyMeterClass(EnergyMeter energyMeterClass) {
		EnergyMeterClass = energyMeterClass;
	}

	public Feeder getFeederClass() {
		return FeederClass;
	}

	public void setFeederClass(Feeder feederClass) {
		FeederClass = feederClass;
	}

	public Monitor getMonitorClass() {
		return MonitorClass;
	}

	public void setMonitorClass(Monitor monitorClass) {
		MonitorClass = monitorClass;
	}

	public Sensor getSensorClass() {
		return SensorClass;
	}

	public void setSensorClass(Sensor sensorClass) {
		SensorClass = sensorClass;
	}

	public TCC_Curve getTCC_CurveClass() {
		return TCC_CurveClass;
	}

	public void setTCC_CurveClass(TCC_Curve tCC_CurveClass) {
		TCC_CurveClass = tCC_CurveClass;
	}

	public WireData getWireDataClass() {
		return WireDataClass;
	}

	public void setWireDataClass(WireData wireDataClass) {
		WireDataClass = wireDataClass;
	}

	public LineSpacing getLineSpacingClass() {
		return LineSpacingClass;
	}

	public void setLineSpacingClass(LineSpacing lineSpacingClass) {
		LineSpacingClass = lineSpacingClass;
	}

	public Storage getStorageClass() {
		return StorageClass;
	}

	public void setStorageClass(Storage storageClass) {
		StorageClass = storageClass;
	}

	public String[] getEventStrings() {
		return EventStrings;
	}

	public void setEventStrings(String[] eventStrings) {
		EventStrings = eventStrings;
	}

	public String[] getSavedFileList() {
		return SavedFileList;
	}

	public void setSavedFileList(String[] savedFileList) {
		SavedFileList = savedFileList;
	}

	public ArrayList<DSSClass> getDSSClassList() {
		return DSSClassList;
	}

	public void setDSSClassList(ArrayList<DSSClass> dSSClassList) {
		DSSClassList = dSSClassList;
	}

	public HashList getClassNames() {
		return ClassNames;
	}

	public void setClassNames(HashList classNames) {
		ClassNames = classNames;
	}

	public void doErrorMsg(String S, String Emsg, String ProbCause, int ErrNum) {
		String Msg = String.format("Error %d Reported From DSS Intrinsic Function: ", ErrNum)+ CRLF  + S
		+ CRLF   + CRLF + "Error Description: " + CRLF + Emsg
		+ CRLF   + CRLF + "Probable Cause: " + CRLF + ProbCause;

		if (!NoFormsAllowed) {
			if (In_Redirect) {
				int RetVal = DSSForms.messageDlg(Msg, false);
				if (RetVal == -1)
					Redirect_Abort = true;
			} else {
				DSSForms.messageDlg(Msg, true);
			}
		}

		LastErrorMessage = Msg;
		ErrorNumber = ErrNum;
		appendGlobalResult(Msg);
	}

	public void doSimpleMsg(String S, int ErrNum) {
		if (!NoFormsAllowed) {
			if (In_Redirect) {
				int RetVal = DSSForms.messageDlg(String.format("(%d) %s%s", ErrNum, CRLF, S), false);
				if (RetVal == -1)
					Redirect_Abort = true;
			} else {
				DSSForms.infoMessageDlg(String.format("(%d) %s%s", ErrNum, CRLF, S));
			}
		}

		LastErrorMessage = S;
		ErrorNumber = ErrNum;
		appendGlobalResult(S);
	}

	public void clearAllCircuits() {		
		ActiveCircuit = null;
		Circuits = new ArrayList<Circuit>(2);   // Make a new list of circuits
		NumCircuits = 0;
	}

	/* Set object active by name */
	public void setObject(String Param) {
		String ObjName, ObjClass;

		// Split off Obj class and name
		int dotpos = Param.indexOf(".");
		switch (dotpos) {
		case 0:
			// assume it is all name; class defaults
			ObjName = Param;
		default:
			ObjClass = Param.substring(0, dotpos - 1);
			ObjName = Param.substring(dotpos + 1, Param.length());
		}

		if (ObjClass.length() > 0)
			DSSClassDefs.setObjectClass(ObjClass);

		ActiveDSSClass = DSSClassList.get(LastClassReferenced);
		if (ActiveDSSClass != null) {
			if (!ActiveDSSClass.setActive(ObjName)) {
				// scroll through list of objects untill a match
				doSimpleMsg("Error! Object \"" + ObjName + "\" not found." + CRLF + Parser.getInstance().getCmdString(), 904);
			} else {
				switch (ActiveDSSObject.getDSSObjType()) {
				case DSSClassDefs.DSS_OBJECT:
					// do nothing for general DSS object
				default:
					// for circuit types, set ActiveCircuit Element, too
					ActiveCircuit.setActiveCktElement((DSSCktElement) ActiveDSSClass.getActiveObj());
				}
			}
		} else {
			doSimpleMsg("Error! Active object type/class is not set.", 905);
		}
	}

	/** Finds the bus and sets it active. */
	public int setActiveBus(String BusName) {
		int Result = 0;

		if (ActiveCircuit.getBusList().listSize() == 0)
			return Result;   // BusList not yet built
		
		ActiveCircuit.setActiveBusIndex(ActiveCircuit.getBusList().find(BusName));
		
		if (ActiveCircuit.getActiveBusIndex() == 0) {
			Result = 1;
			appendGlobalResult("SetActiveBus: Bus " + BusName + " Not Found.");
		}
		
		return Result;
	}

	/** Pathname may be null */
	public void setDataPath(String PathName) {
		File F = new File(PathName);

		if ((PathName.length() > 0) && !F.exists()) {

			// Try to create the directory
			if (F.mkdir()) {
				doSimpleMsg("Cannot create " + PathName + " directory.", 907);
				System.exit(0);
			}

		}

		DSSDataDirectory = PathName;

		// Put a \ on the end if not supplied. Allow a null specification.
		if (DSSDataDirectory.length() > 0) {
			// FIXME: change directory
//	    	cd(DSSDataDirectory);   // Change to specified directory
			if (DSSDataDirectory.charAt(DSSDataDirectory.length()) != '\\') {
					DSSDataDirectory = DSSDataDirectory + "\\";
			}
		}
	}


	public void makeNewCircuit(String Name) {
		if (NumCircuits <= MaxCircuits - 1) {
			ActiveCircuit = new DSSCircuit(Name);
			ActiveDSSObject = Solution.ActiveSolutionObj;
			/*Handle = */ Circuits.add(ActiveCircuit);
			NumCircuits += 1;
			// Pass remainder of string on to vsource.
			String S = Parser.getInstance().getRemainder();    
			
			/* Create a default Circuit */
			SolutionAbort = false;
			/* Voltage source named "source" connected to SourceBus */
			// Load up the parser as if it were read in
			Executive.DSSExecutive.setCommand("New object=vsource.source Bus1=SourceBus " + S);  
		} else {
			doErrorMsg("MakeNewCircuit",
					"Cannot create new circuit.",
					"Max. Circuits Exceeded." + CRLF +
					"(Max no. of circuits=" + String.valueOf(MaxCircuits) + ")", 906);
		}
	}

	/* Append a string to Global result, separated by commas */
	public void appendGlobalResult(String S) {
		if (GlobalResult.length() == 0) {
			GlobalResult = S;
		} else {
			GlobalResult = GlobalResult + ", " + S;
		}
	}

	/* Separate by CRLF */
	public void appendGlobalResultCRLF(String S) {
		if (GlobalResult.length() > 0) {
			GlobalResult += CRLF + S;
		} else {
			GlobalResult = S;
		}
	}

	public void WriteDLLDebugFile(String S) {
		boolean Append;
		if (DLLFirstTime) {
			Append = false;
			DLLFirstTime = false;
		} else {
			Append = true;
		}
		FileWriter Writer;
		try {
			Writer = new FileWriter(DSSDataDirectory + "DSSDLLDebug.txt", Append);
			BufferedWriter DLLDebugFile = new BufferedWriter(Writer);
			DLLDebugFile.write(S + CRLF);
			DLLDebugFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public void readDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public void writeDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public boolean isDSSDLL(String Fname) {
		throw new UnsupportedOperationException();
	}

	public String getDSSVersion() {
		// TODO: Implement GetDSSVersion()
		return "Unknown.";
	}

}