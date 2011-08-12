package com.epri.dss.common.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.DSSForms;
import com.epri.dss.conversion.PVSystem;
import com.epri.dss.conversion.Storage;
import com.epri.dss.executive.impl.DSSExecutive;
import com.epri.dss.general.CNData;
import com.epri.dss.general.DSSObject;
import com.epri.dss.general.GrowthShape;
import com.epri.dss.general.LineSpacing;
import com.epri.dss.general.LoadShape;
import com.epri.dss.general.PriceShape;
import com.epri.dss.general.Spectrum;
import com.epri.dss.general.TCC_Curve;
import com.epri.dss.general.TSData;
import com.epri.dss.general.TShape;
import com.epri.dss.general.WireData;
import com.epri.dss.general.XYCurve;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.Monitor;
import com.epri.dss.meter.Sensor;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.HashList;

public class DSSGlobals {

	public static final String CRLF = System.getProperty("line.separator");
	public static final double PI = 3.14159265359;
	public static final double TWO_PI = 2.0 * PI;
	public static final double RADIANS_TO_DEGREES = 57.29577951;
	public static final double EPSILON = 1.0e-12;   // default tiny floating point
	public static final double EPSILON2 = 1.0e-3;   // default for real number mismatch testing

	/* Load model types for solution */
	public static final int POWERFLOW  = 1;
	public static final int ADMITTANCE = 2;

	/* For YPrim matrices */
	public static final int ALL_YPRIM = 0;
	public static final int SERIES = 1;
	public static final int SHUNT  = 2;

	/* Control modes */
	public static final int CONTROLSOFF = -1;
	public static final int EVENTDRIVEN =  1;
	public static final int TIMEDRIVEN  =  2;
	public static final int CTRLSTATIC  =  0;

	/* Randomization constants */
	public static final int GAUSSIAN  = 1;
	public static final int UNIFORM   = 2;
	public static final int LOGNORMAL = 3;

	/* AutoAdd constants */
	public static final int GENADD = 1;
	public static final int CAPADD = 2;

	/* Errors */
	public static final int SOLUTION_ABORT = 99;

	/* For general sequential time simulations */
	public static final int USEDAILY  = 0;
	public static final int USEYEARLY = 1;
	public static final int USEDUTY   = 2;
	public static final int USENONE   =-1;

	/* Earth model */
	public static final int SIMPLECARSON  = 1;
	public static final int FULLCARSON    = 2;
	public static final int DERI          = 3;

    /* Profile plot constants */
	public static final int PROFILE3PH    = 9999;  // some big number > likely no. of phases
	public static final int PROFILEALL    = 9998;
	public static final int PROFILEALLPRI = 9997;
	public static final int PROFILELLALL  = 9996;
	public static final int PROFILELLPRI  = 9995;
	public static final int PROFILELL     = 9994;

	/* 120-degree shift constant */
	public static final Complex CALPHA = new Complex(-0.5, -0.866025);
	public static final double SQRT2 = Math.sqrt(2.0);
	public static final double SQRT3 = Math.sqrt(3.0);
	public static final double InvSQRT3 = 1.0 / SQRT3;
	public static final double InvSQRT3x1000 = InvSQRT3 * 1000.0;

	/* DSS Forms */
	DSSForms forms = CommandLineDSSForms.getInstance();

	/* Variables */
	private boolean DLLFirstTime = true;
	private PrintStream DLLDebugFile;
	private String DSSIniFileName = "OpenDSSPanel.ini";
	// Registry   (See Executive)
//	private IniRegSave DSS_Registry = IniRegSave("\\Software\\OpenDSS");

	private boolean isDLL = false;
	private boolean noFormsAllowed = false;

	private Circuit activeCircuit;
	private DSSClass activeDSSClass;
	private int lastClassReferenced;  // index of class of last thing edited
	private DSSObject activeDSSObject;
	private int numCircuits = 0;
	private int maxCircuits = 1;
	private int maxBusLimit = 0;  // set in validation
	private int maxAllocationIterations = 2;
	private ArrayList<Circuit> circuits;
	private ArrayList<DSSObject> DSSObjs;

	// auxiliary parser for use by anybody for reparsing values
	private Parser auxParser = Parser.getInstance();

	private boolean errorPending = false;
	private int cmdResult = 0;
	private int errorNumber = 0;
	private String lastErrorMessage = "";

	private int defaultEarthModel = DERI;
	private int activeEarthModel = defaultEarthModel;

	private String lastFileCompiled = "";
	private boolean lastCommandWasCompile = false;

	private boolean solutionAbort = false;
	private boolean inShowResults = false;
	private boolean redirectAbort = false;
	private boolean inRedirect = false;
	private boolean DIFilesAreOpen = false;
	private boolean autoShowExport = false;
	private boolean solutionWasAttempted = false;

	private String globalHelpString = "";
	private String globalPropertyValue = "";
	private String globalResult = "";
	private String versionString = "Version " + getDSSVersion();

	private String defaultEditor = "NotePad";
	private String DSSFileName;// = GetDSSExeFile();  // name of current exe or DLL
	private String DSSDirectory;// = new File(DSSFileName).getParent();  // where the current exe resides
	private String startupDirectory = System.getProperty("user.dir") + "/";  // starting point
	private String DSSDataDirectory = startupDirectory;
	private String circuitName_;  // name of circuit with a "_" appended
	private String currentDirectory = startupDirectory;  // current working directory

	private double defaultBaseFreq = 60.0;
	private double daisySize = 1.0;

	// commonly used classes
	private LoadShape loadShapeClass;
	private TShape TShapeClass;
	private PriceShape priceShapeClass;
	private XYCurve XYCurveClass;
	private GrowthShape growthShapeClass;
	private Spectrum spectrumClass;
	private DSSClass solutionClass;
	private EnergyMeter energyMeterClass;
	//private Feeder feederClass;
	private Monitor monitorClass;
	private Sensor sensorClass;
	private TCC_Curve TCC_CurveClass;
	private WireData wireDataClass;
	private CNData CNDataClass;
	private TSData TSDataClass;
	private LineSpacing lineSpacingClass;
	private Storage storageClass;
	private PVSystem PVSystemClass;

	private List<String> eventStrings;
	private List<String> savedFileList;

	private List<DSSClass> DSSClassList;  // base class types
	private HashList classNames;

	// private constructor prevents instantiation from other classes
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

	public void doErrorMsg(String s, String emsg, String probCause, int errNum) {
		String msg = String.format("Error %d reported from DSS function: ", errNum) + s
			+ CRLF + "Error description: " + CRLF + emsg
			+ CRLF + "Probable cause: " + CRLF + probCause;

		if (!noFormsAllowed) {
			if (inRedirect) {
				int retVal = forms.messageDlg(msg, false);
				if (retVal == -1)
					redirectAbort = true;
			} else {
				forms.messageDlg(msg, true);
			}
		}

		lastErrorMessage = msg;
		errorNumber = errNum;
		appendGlobalResult(msg);
	}

	public void doSimpleMsg(String s, int errNum) {
		if (!noFormsAllowed) {
			if (inRedirect) {
				int RetVal = forms.messageDlg(String.format("(%d) %s%s", errNum, CRLF, s), false);
				if (RetVal == -1)
					redirectAbort = true;
			} else {
				forms.infoMessageDlg(String.format("(%d) %s%s", errNum, CRLF, s));
			}
		}

		lastErrorMessage = s;
		errorNumber = errNum;
		appendGlobalResult(s);
	}

	public void clearAllCircuits() {
		activeCircuit = null;
		circuits = new ArrayList<Circuit>(2);  // make a new list of circuits
		numCircuits = 0;
	}

	/**
	 * Set object active by name.
	 */
	public void setObject(String param) {
		String objName, objClass = null;

		// Split off obj class and name
		int dotpos = param.indexOf(".");
		switch (dotpos) {
		case 0:
			// assume it is all name; class defaults
			objName = param;
			break;
		default:
			objClass = param.substring(0, dotpos - 1);
			objName = param.substring(dotpos + 1, param.length());
			break;
		}

		if (objClass.length() > 0)
			DSSClassDefs.setObjectClass(objClass);

		activeDSSClass = DSSClassList.get(lastClassReferenced);
		if (activeDSSClass != null) {
			if (!activeDSSClass.setActive(objName)) {
				// scroll through list of objects until a match
				doSimpleMsg("Error: Object \"" + objName + "\" not found." + CRLF + Parser.getInstance().getCmdString(), 904);
			} else {
				switch (activeDSSObject.getDSSObjType()) {
				case DSSClassDefs.DSS_OBJECT:
					// do nothing for general DSS object
					break;
				default:
					// for circuit types, set active circuit element too
					activeCircuit.setActiveCktElement((DSSCktElement) activeDSSClass.getActiveObj());
					break;
				}
			}
		} else {
			doSimpleMsg("Error: Active object type/class is not set.", 905);
		}
	}

	/** Finds the bus and sets it active. */
	public int setActiveBus(String busName) {
		int result = 0;

		if (activeCircuit.getBusList().listSize() == 0)
			return result;   // bus list not yet built

		activeCircuit.setActiveBusIndex(activeCircuit.getBusList().find(busName));

		if (activeCircuit.getActiveBusIndex() == 0) {
			result = 1;
			appendGlobalResult("SetActiveBus: Bus " + busName + " not found.");
		}

		return result;
	}

	/** Pathname may be null */
	public void setDataPath(String pathName) {
		File f = new File(pathName);

		if ((pathName.length() > 0) && !f.exists()) {

			// try to create the directory
			if (f.mkdir()) {
				doSimpleMsg("Cannot create " + pathName + " directory.", 907);
				System.exit(0);
			}

		}

		DSSDataDirectory = pathName;

		// Put a \ on the end if not supplied. Allow a null specification.
		if (DSSDataDirectory.length() > 0) {
	    	setCurrentDirectory(DSSDataDirectory);   // change to specified directory
//			if (DSSDataDirectory.charAt(DSSDataDirectory.length()) != '\\') {
//					DSSDataDirectory = DSSDataDirectory + "\\";
//			}
		}
	}


	public void makeNewCircuit(String name) {
		if (numCircuits < maxCircuits) {
			activeCircuit = new DSSCircuit(name);
			activeDSSObject = SolutionImpl.getActiveSolutionObj();
			/*Handle = */ circuits.add(activeCircuit);
			numCircuits += 1;
			// pass remainder of string on to VSource
			String s = Parser.getInstance().getRemainder();

			/* create a default circuit */
			solutionAbort = false;
			/* Voltage source named "source" connected to "SourceBus" */
			// load up the parser as if it were read in
			DSSExecutive.getInstance().setCommand("new object=vsource.source bus1=SourceBus " + s);
		} else {
			doErrorMsg("makeNewCircuit", "Cannot create new circuit.",
					"Max. circuits exceeded." + CRLF +
					"(Max no. of circuits=" + String.valueOf(maxCircuits) + ")", 906);
		}
	}

	/**
	 * Append a string to global result, separated by commas.
	 */
	public void appendGlobalResult(String s) {
		if (globalResult.length() == 0) {
			globalResult = s;
		} else {
			globalResult = globalResult + ", " + s;
		}
	}

	/**
	 * Separate by CRLF.
	 */
	public void appendGlobalResultCRLF(String s) {
		if (globalResult.length() > 0) {
			globalResult += CRLF + s;
		} else {
			globalResult = s;
		}
	}

	public void WriteDLLDebugFile(String s) {
		boolean append;
		if (DLLFirstTime) {
			append = false;
			DLLFirstTime = false;
		} else {
			append = true;
		}
		FileWriter writer;
		try {
			writer = new FileWriter(DSSDataDirectory + "DSSDLLDebug.txt", append);
			BufferedWriter debugFile = new BufferedWriter(writer);
			debugFile.write(s + CRLF);
			debugFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	public boolean isDLLFirstTime() {
		return DLLFirstTime;
	}

	public void setDLLFirstTime(boolean firstTime) {
		DLLFirstTime = firstTime;
	}

	public PrintStream getDLLDebugFile() {
		return DLLDebugFile;
	}

	public void setDLLDebugFile(PrintStream debugFile) {
		DLLDebugFile = debugFile;
	}

	public String getDSS_IniFileName() {
		return DSSIniFileName;
	}

	public void setDSSIniFileName(String iniFileName) {
		DSSIniFileName = iniFileName;
	}

//	public IniRegSave getDSS_Registry() {
//		return DSS_Registry;
//	}

//	public void setDSS_Registry(IniRegSave dSS_Registry) {
//		DSS_Registry = dSS_Registry;
//	}

	public boolean isDLL() {
		return isDLL;
	}

	public void setDLL(boolean value) {
		isDLL = value;
	}

	public boolean isNoFormsAllowed() {
		return noFormsAllowed;
	}

	public void setNoFormsAllowed(boolean value) {
		noFormsAllowed = value;
	}

	public Circuit getActiveCircuit() {
		return activeCircuit;
	}

	public void setActiveCircuit(Circuit value) {
		activeCircuit = value;
	}

	public DSSClass getActiveDSSClass() {
		return activeDSSClass;
	}

	public void setActiveDSSClass(DSSClass value) {
		activeDSSClass = value;
	}

	public int getLastClassReferenced() {
		return lastClassReferenced;
	}

	public void setLastClassReferenced(int lastClass) {
		lastClassReferenced = lastClass;
	}

	public DSSObject getActiveDSSObject() {
		return activeDSSObject;
	}

	public void setActiveDSSObject(DSSObject value) {
		activeDSSObject = value;
	}

	public int getNumCircuits() {
		return numCircuits;
	}

	public void setNumCircuits(int value) {
		numCircuits = value;
	}

	public int getMaxCircuits() {
		return maxCircuits;
	}

	public void setMaxCircuits(int value) {
		maxCircuits = value;
	}

	public int getMaxBusLimit() {
		return maxBusLimit;
	}

	public void setMaxBusLimit(int busLimit) {
		maxBusLimit = busLimit;
	}

	public int getMaxAllocationIterations() {
		return maxAllocationIterations;
	}

	public void setMaxAllocationIterations(int maxIter) {
		maxAllocationIterations = maxIter;
	}

	public ArrayList<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(ArrayList<Circuit> ckts) {
		circuits = ckts;
	}

	public ArrayList<DSSObject> getDSSObjs() {
		return DSSObjs;
	}

	public void setDSSObjs(ArrayList<DSSObject> value) {
		DSSObjs = value;
	}

	public Parser getAuxParser() {
		return auxParser;
	}

	public void setAuxParser(Parser parser) {
		auxParser = parser;
	}

	public boolean isErrorPending() {
		return errorPending;
	}

	public void setErrorPending(boolean value) {
		errorPending = value;
	}

	public int getCmdResult() {
		return cmdResult;
	}

	public void setCmdResult(int result) {
		cmdResult = result;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int number) {
		errorNumber = number;
	}

	public String getLastErrorMessage() {
		return lastErrorMessage;
	}

	public void setLastErrorMessage(String errorMessage) {
		lastErrorMessage = errorMessage;
	}

	public int getDefaultEarthModel() {
		return defaultEarthModel;
	}

	public void setDefaultEarthModel(int earthModel) {
		defaultEarthModel = earthModel;
	}

	public int getActiveEarthModel() {
		return activeEarthModel;
	}

	public void setActiveEarthModel(int earthModel) {
		activeEarthModel = earthModel;
	}

	public String getLastFileCompiled() {
		return lastFileCompiled;
	}

	public void setLastFileCompiled(String lastFile) {
		lastFileCompiled = lastFile;
	}

	public boolean isLastCommandWasCompile() {
		return lastCommandWasCompile;
	}

	public void setLastCommandWasCompile(boolean value) {
		lastCommandWasCompile = value;
	}

	public boolean isSolutionAbort() {
		return solutionAbort;
	}

	public void setSolutionAbort(boolean abort) {
		solutionAbort = abort;
	}

	public boolean isInShowResults() {
		return inShowResults;
	}

	public void setInShowResults(boolean value) {
		inShowResults = value;
	}

	public boolean isRedirectAbort() {
		return redirectAbort;
	}

	public void setRedirectAbort(boolean abort) {
		redirectAbort = abort;
	}

	public boolean isInRedirect() {
		return inRedirect;
	}

	public void setInRedirect(boolean value) {
		inRedirect = value;
	}

	public boolean isDIFilesAreOpen() {
		return DIFilesAreOpen;
	}

	public void setDIFilesAreOpen(boolean value) {
		DIFilesAreOpen = value;
	}

	public boolean isAutoShowExport() {
		return autoShowExport;
	}

	public void setAutoShowExport(boolean value) {
		autoShowExport = value;
	}

	public boolean isSolutionWasAttempted() {
		return solutionWasAttempted;
	}

	public void setSolutionWasAttempted(boolean value) {
		solutionWasAttempted = value;
	}

	public String getGlobalHelpString() {
		return globalHelpString;
	}

	public void setGlobalHelpString(String help) {
		globalHelpString = help;
	}

	public String getGlobalPropertyValue() {
		return globalPropertyValue;
	}

	public void setGlobalPropertyValue(String value) {
		globalPropertyValue = value;
	}

	public String getGlobalResult() {
		return globalResult;
	}

	public void setGlobalResult(String result) {
		globalResult = result;
	}

	public String getVersionString() {
		return versionString;
	}

	public void setVersionString(String version) {
		versionString = version;
	}

	public String getDefaultEditor() {
		return defaultEditor;
	}

	public void setDefaultEditor(String editor) {
		defaultEditor = editor;
	}

	public String getDSSFileName() {
		return DSSFileName;
	}

	public void setDSSFileName(String fileName) {
		DSSFileName = fileName;
	}

	public String getDSSDirectory() {
		return DSSDirectory;
	}

	public void setDSSDirectory(String dir) {
		DSSDirectory = dir;
	}

	public String getStartupDirectory() {
		return startupDirectory;
	}

	public void setStartupDirectory(String dir) {
		startupDirectory = dir;
	}

	public String getDSSDataDirectory() {
		return DSSDataDirectory;
	}

	public void setDSSDataDirectory(String dir) {
		DSSDataDirectory = dir;
	}

	public String getCircuitName_() {
		return circuitName_;
	}

	public void setCircuitName_(String name) {
		circuitName_ = name;
	}

	public double getDefaultBaseFreq() {
		return defaultBaseFreq;
	}

	public void setDefaultBaseFreq(double freq) {
		defaultBaseFreq = freq;
	}

	public double getDaisySize() {
		return daisySize;
	}

	public void setDaisySize(double size) {
		daisySize = size;
	}

	public LoadShape getLoadShapeClass() {
		return loadShapeClass;
	}

	public void setLoadShapeClass(LoadShape cls) {
		loadShapeClass = cls;
	}

	public GrowthShape getGrowthShapeClass() {
		return growthShapeClass;
	}

	public void setGrowthShapeClass(GrowthShape cls) {
		growthShapeClass = cls;
	}

	public Spectrum getSpectrumClass() {
		return spectrumClass;
	}

	public void setSpectrumClass(Spectrum cls) {
		spectrumClass = cls;
	}

	public DSSClass getSolutionClass() {
		return solutionClass;
	}

	public void setSolutionClass(DSSClass cls) {
		solutionClass = cls;
	}

	public EnergyMeter getEnergyMeterClass() {
		return energyMeterClass;
	}

	public void setEnergyMeterClass(EnergyMeter cls) {
		energyMeterClass = cls;
	}

//	public Feeder getFeederClass() {
//		return FeederClass;
//	}
//
//	public void setFeederClass(Feeder feederClass) {
//		FeederClass = feederClass;
//	}

	public Monitor getMonitorClass() {
		return monitorClass;
	}

	public void setMonitorClass(Monitor cls) {
		monitorClass = cls;
	}

	public Sensor getSensorClass() {
		return sensorClass;
	}

	public void setSensorClass(Sensor cls) {
		sensorClass = cls;
	}

	public TCC_Curve getTCC_CurveClass() {
		return TCC_CurveClass;
	}

	public void setTCC_CurveClass(TCC_Curve cls) {
		TCC_CurveClass = cls;
	}

	public WireData getWireDataClass() {
		return wireDataClass;
	}

	public void setWireDataClass(WireData cls) {
		wireDataClass = cls;
	}

	public LineSpacing getLineSpacingClass() {
		return lineSpacingClass;
	}

	public void setLineSpacingClass(LineSpacing cls) {
		lineSpacingClass = cls;
	}

	public Storage getStorageClass() {
		return storageClass;
	}

	public void setStorageClass(Storage cls) {
		storageClass = cls;
	}

	public TShape getTShapeClass() {
		return TShapeClass;
	}

	public void setTShapeClass(TShape cls) {
		TShapeClass = cls;
	}

	public PriceShape getPriceShapeClass() {
		return priceShapeClass;
	}

	public void setPriceShapeClass(PriceShape cls) {
		priceShapeClass = cls;
	}

	public XYCurve getXYCurveClass() {
		return XYCurveClass;
	}

	public void setXYCurveClass(XYCurve cls) {
		XYCurveClass = cls;
	}

	public CNData getCNDataClass() {
		return CNDataClass;
	}

	public void setCNDataClass(CNData cls) {
		CNDataClass = cls;
	}

	public TSData getTSDataClass() {
		return TSDataClass;
	}

	public void setTSDataClass(TSData cls) {
		TSDataClass = cls;
	}

	public PVSystem getPVSystemClass() {
		return PVSystemClass;
	}

	public void setPVSystemClass(PVSystem cls) {
		PVSystemClass = cls;
	}

	public List<String> getEventStrings() {
		return eventStrings;
	}

	public void setEventStrings(List<String> strings) {
		eventStrings = strings;
	}

	public List<String> getSavedFileList() {
		return savedFileList;
	}

	public void setSavedFileList(List<String> list) {
		savedFileList = list;
	}

	public List<DSSClass> getDSSClassList() {
		return DSSClassList;
	}

	public void setDSSClassList(List<DSSClass> list) {
		DSSClassList = list;
	}

	public HashList getClassNames() {
		return classNames;
	}

	public void setClassNames(HashList names) {
		classNames = names;
	}

	public void readDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public void writeDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public boolean isDSSDLL(String fname) {
		throw new UnsupportedOperationException();
	}

	public String getDSSVersion() {
		// TODO: Implement GetDSSVersion()
		return "Unknown.";
	}

	public DSSForms getDSSForms() {
		return forms;
	}

	public void setDSSForms(DSSForms value) {
		forms = value;
	}

	public String getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(String directory) {
		currentDirectory = directory;
	}

}