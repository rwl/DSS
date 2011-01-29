package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.PrintStream;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.DSSGlobals;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.GrowthShape;
import com.epri.dss.general.LoadShape;
import com.epri.dss.general.Spectrum;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.parser.Parser;
import com.epri.dss.parser.impl.ParserImpl;
import com.epri.dss.shared.PointerList;

public class DSSGlobalsImpl implements DSSGlobals {

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
	private PointerList Circuits;
	private PointerList DSSObjs;

    // Auxiliary parser for use by anybody for reparsing values
	private Parser AuxParser = new ParserImpl();

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
//	private String DSSFileName = GetDSSExeFile();     // Name of current exe or DLL
//	private String DSSDirectory = new File(DSSFileName).getParent();     // where the current exe resides
//	private String StartupDirectory = GetCurrentDir() + "\\";     // Where we started
//	private String DSSDataDirectory = StartupDirectory;
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
	private DSSMonitor MonitorClass;
	private Sensor SensorClass;
	private TCC_Curve TCC_CurveClass;
	private WireData WireDataClass;
	private LineSpacing LineSpacingClass;
	private Storage StorageClass;


	// Private constructor prevents instantiation from other classes
	private DSSGlobalsImpl() {
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

	public DSSObjectImpl getActiveDSSObject() {
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

	public PointerList getCircuits() {
		return Circuits;
	}

	public void setCircuits(PointerList circuits) {
		Circuits = circuits;
	}

	public PointerList getDSSObjs() {
		return DSSObjs;
	}

	public void setDSSObjs(PointerList dSSObjs) {
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

	public DSSMonitor getMonitorClass() {
		return MonitorClass;
	}

	public void setMonitorClass(DSSMonitor monitorClass) {
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

	/**
	 * DSSGlobalsHolder is loaded on the first execution of
	 * DSSGlobals.getInstance() or the first access to
	 * DSSGlobalsHolder.INSTANCE, not before.
	 */
	private class DSSGlobalsHolder {
		public static final DSSGlobals INSTANCE = new DSSGlobalsImpl();
	}

	public static DSSGlobals getInstance() {
		return DSSGlobalsHolder.INSTANCE;
	}

	public void doErrorMsg(String S, String Emsg, String ProbCause, int ErrNum) {
		String Msg = String.format("Error %d Reported From DSS Intrinsic Function: ", ErrNum)+ CRLF  + S
        + CRLF   + CRLF + "Error Description: " + CRLF + Emsg
        + CRLF   + CRLF + "Probable Cause: " + CRLF + ProbCause;

		if (!NoFormsAllowed) {

			if (In_Redirect) {
				int RetVal = DSSMessageDlg(Msg, false);
				if (RetVal == -1)
					Redirect_Abort = true;
			} else {
				DSSMessageDlg(Msg, true);
			}
		}

		LastErrorMessage = Msg;
		ErrorNumber = ErrNum;
		AppendGlobalResultCRLF(Msg);
	}

	public void doSimpleMsg(String S, int ErrNum) {
		if (!NoFormsAllowed) {
	       if (In_Redirect) {
	    	   int RetVal = DSSMessageDlg(String.format("(%d) %s%s", ErrNum, CRLF, S), false);
	    	   if (RetVal == -1)
	    		   Redirect_Abort = true;
	       } else {
	    	   DSSInfoMessageDlg(String.format("(%d) %s%s", ErrNum, CRLF, S));
	       }
		}

	    LastErrorMessage = S;
	    ErrorNumber = ErrNum;
	    AppendGlobalResultCRLF(S);
	}

	public void clearAllCircuits() {
		ActiveCircuit = Circuits.First();
		while (ActiveCircuit != null) {
			ActiveCircuit.Free();
			ActiveCircuit = Circuits.Next();
		}
		Circuits.Free();
		Circuits = new PointerListImpl(2);   // Make a new list of circuits
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
	    	SetObjectClass(ObjClass);


	    ActiveDSSClass = DSSClassList.Get(LastClassReferenced);
	    if (ActiveDSSClass != null) {
	        if (!ActiveDSSClass.SetActive(ObjName)) {
	        	// scroll through list of objects untill a match
	        	DoSimpleMsg("Error! Object \"" + ObjName + "\" not found." + CRLF + Parser.CmdString, 904);
	        } else {
		        switch (ActiveDSSObject.DSSObjType) {
				case DSS_OBJECT:
					// do nothing for general DSS object
				default:
					// for circuit types, set ActiveCircuit Element, too
					ActiveCircuit.ActiveCktElement = ActiveDSSClass.GetActiveObj();
				}
	        }
	    } else {
	        DoSimpleMsg("Error! Active object type/class is not set.", 905);
	    }
	}

	public int setActiveBus(String BusName) {
		// Now find the bus and set active
		int Result = 0;

		if (ActiveCircuit.BusList.ListSize == 0) System.exit(0);   // Buslist not yet built
		ActiveBusIndex = ActiveCircuit.BusList.Find(BusName);
		if (ActiveCircuit.ActiveBusIndex == 0) {
			Result = 1;
			AppendGlobalResult("SetActiveBus: Bus " + BusName + " Not Found.");
		}
	}

	/* Pathname may be null */
	public void setDataPath(String PathName) {
		File F = new File(PathName);

		if ((PathName.length() > 0) && !F.exists()) {

			// Try to create the directory
			if (F.mkdir()) {
				DoSimpleMsg("Cannot create " + PathName + " directory.", 907);
				System.exit(0);
			}

		}

	    DSSDataDirectory = PathName;

	    // Put a \ on the end if not supplied. Allow a null specification.
	    if (DSSDataDirectory.length() > 0) {
//	    	ChDir(DSSDataDirectory);   // Change to specified directory
	    	if (DSSDataDirectory.charAt(DSSDataDirectory.length()) != '\\') {
	    			DSSDataDirectory = DSSDataDirectory + "\\";
	    	}
	    }
	}


	public void makeNewCircuit(String Name) {
		if (NumCircuits <= MaxCircuits - 1) {
			ActiveCircuit = new DSSCircuit(Name);
	        ActiveDSSObject = ActiveSolutionObj;
	        /*Handle := */ Circuits.Add(ActiveCircuit);
	        NumCircuits += 1;
	        S = Parser.Remainder;    // Pass remainder of string on to vsource.
	        /* Create a default Circuit */
	        SolutionABort = false;
	        /* Voltage source named "source" connected to SourceBus */
	        DSSExecutive.Command = "New object=vsource.source Bus1=SourceBus " + S;  // Load up the parser as if it were read in
		} else {
	         DoErrorMsg("MakeNewCircuit",
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
			GlobalResult = GlobalResult + CRLF + S;
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
		FileWriter Writer = new FileWriter(DSSDataDirectory + "DSSDLLDebug.txt", Append);
		BufferedWriter DLLDebugFile = new BufferedWriter(Writer);
		DLLDebugFile.write(S + CRLF);
		DLLDebugFile.close();
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