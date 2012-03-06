package com.ncond.dss.common;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.control.VVControl;
import com.ncond.dss.conversion.PVSystem;
import com.ncond.dss.conversion.Storage;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.CNData;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.GrowthShape;
import com.ncond.dss.general.LineSpacing;
import com.ncond.dss.general.LoadShape;
import com.ncond.dss.general.PriceShape;
import com.ncond.dss.general.Spectrum;
import com.ncond.dss.general.TCC_Curve;
import com.ncond.dss.general.TSData;
import com.ncond.dss.general.TShape;
import com.ncond.dss.general.WireData;
import com.ncond.dss.general.XYCurve;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.Monitor;
import com.ncond.dss.meter.Sensor;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.HashList;

public class DSS {

	public static final String CRLF = System.getProperty("line.separator");
	public static final double PI = Math.PI;
	public static final double TWO_PI = 2.0 * PI;
	public static final double RADIANS_TO_DEGREES = 180.0 / PI;
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
	public static DSSForms forms = CommandLineDSSForms.getInstance();

	/* Variables */
	public static boolean debugFirstTime = true;
//	public static File debugFile;
//	public static String DSSIniFileName = "OpenDSSPanel.ini";
//	public static String programName = "OpenDSS";
	// Registry   (See Executive)
//	public static IniRegSave DSS_Registry = IniRegSave("\\Software\\OpenDSS");

//	public static boolean isDLL = false;
	public static boolean noFormsAllowed = false;

	public static Circuit activeCircuit;
	public static DSSClass activeDSSClass;
	public static int lastClassReferenced;  // index of class of last thing edited
	public static DSSObject activeDSSObject;
	public static int numCircuits = 0;
	public static int maxCircuits = 1;
	public static int maxBusLimit = 0;  // set in validation
	public static int maxAllocationIterations = 2;
	public static List<Circuit> circuits;
	public static List<DSSObject> DSSObjs;

	// auxiliary parser for use by anybody for reparsing values
	public static Parser auxParser = Parser.getInstance();

	public static boolean errorPending = false;
	public static int cmdResult = 0;
	public static int errorNumber = 0;
	public static String lastErrorMessage = "";

	public static int defaultEarthModel = DERI;
	public static int activeEarthModel = defaultEarthModel;

	public static String lastFileCompiled = "";
	public static boolean lastCommandWasCompile = false;

	public static boolean solutionAbort = false;
	public static boolean inShowResults = false;
	public static boolean redirectAbort = false;
	public static boolean inRedirect = false;
	public static boolean DIFilesAreOpen = false;
	public static boolean autoShowExport = false;
	public static boolean solutionWasAttempted = false;

	public static String globalHelpString = "";
	public static String globalPropertyValue = "";
	public static String globalResult = "";
	public static String lastResultFile;
	public static String versionString = "Version " + getDSSVersion();

	public static boolean logQueries = false;
	public static boolean queryFirstTime;
	public static String queryLogFileName = "";
	public static File queryLogFile;

	public static String defaultEditor = "vi";
//	public static String DSSFileName;// = getDSSExeFile();  // name of current exe or DLL
//	public static String DSSDirectory;// = new File(DSSFileName).getParent();  // where the current exe resides
//	public static String startupDirectory = System.getProperty("user.dir") + "/";  // starting point
	public static String dataDirectory = System.getProperty("user.dir") + "/";
	public static String circuitName_;  // name of circuit with a "_" appended
	public static String currentDirectory = dataDirectory;  // current working directory

	public static double defaultBaseFreq = 60.0;
	public static double daisySize = 1.0;

	// commonly used classes
	public static LoadShape loadShapeClass;
	public static TShape TShapeClass;
	public static PriceShape priceShapeClass;
	public static XYCurve XYCurveClass;
	public static GrowthShape growthShapeClass;
	public static Spectrum spectrumClass;
	public static DSSClass solutionClass;
	public static EnergyMeter energyMeterClass;
	//public static Feeder feederClass;
	public static Monitor monitorClass;
	public static Sensor sensorClass;
	public static TCC_Curve TCC_CurveClass;
	public static WireData wireDataClass;
	public static CNData CNDataClass;
	public static TSData TSDataClass;
	public static LineSpacing lineSpacingClass;
	public static Storage storageClass;
	public static PVSystem PVSystemClass;
	public static VVControl VVControlClass;

	public static List<String> eventStrings;
	public static List<String> savedFileList;

	public static List<DSSClass> DSSClassList;  // base class types
	public static HashList classNames;

	public static void doErrorMsg(String s, String emsg, String probCause, int errNum) {
		String msg = String.format("Error %d reported from DSS function: ", errNum) +
			s + CRLF + "Error description: " + CRLF + emsg + CRLF +
			"Probable cause: " + CRLF + probCause;

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

	public static void doSimpleMsg(String s, int errNum) {
		if (!noFormsAllowed) {
			if (inRedirect) {
				int rc = forms.messageDlg(String.format("(%d) %s%s", errNum, CRLF, s), false);
				if (rc == -1) redirectAbort = true;
			} else {
				forms.infoMessageDlg(String.format("(%d) %s%s", errNum, CRLF, s));
			}
		}

		lastErrorMessage = s;
		errorNumber = errNum;
		appendGlobalResult(s);
	}

	public static void clearAllCircuits() {
		activeCircuit = null;
		circuits = new ArrayList<Circuit>(2);  // make a new list of circuits

		// revert on key global flags to original states
		numCircuits = 0;
		logQueries = false;
		maxAllocationIterations = 2;
	}

	/**
	 * Set object active by name.
	 */
	public static void setObject(String param) {
		String objName, objClass = null;

		// Split off obj class and name
		int dotpos = param.indexOf(".");
		switch (dotpos) {
		case -1:
			// assume it is all name; class defaults
			objName = param;
			break;
		default:
			objClass = param.substring(0, dotpos);
			objName = param.substring(dotpos + 1, param.length());
			break;
		}

		if (objClass.length() > 0)
			DSSClassDefs.setObjectClass(objClass);

		activeDSSClass = DSSClassList.get(lastClassReferenced);
		if (activeDSSClass != null) {
			if (!activeDSSClass.setActive(objName)) {
				// scroll through list of objects until a match
				doSimpleMsg("Error: Object \"" + objName + "\" not found." + CRLF +
						Parser.getInstance().getCmdString(), 904);
			} else {
				switch (activeDSSObject.getDSSObjType()) {
				case DSSClassDefs.DSS_OBJECT:
					// do nothing for general DSS object
					break;
				default:
					// for circuit types, set active circuit element too
					activeCircuit.setActiveCktElement((CktElement) activeDSSClass.getActiveObj());
					break;
				}
			}
		} else {
			doSimpleMsg("Error: Active object type/class is not set.", 905);
		}
	}

	/**
	 * Finds the bus and sets it active.
	 */
	public static int setActiveBus(String busName) {
		int result = 0;

		if (activeCircuit.getBusList().listSize() == 0)
			return result;   // bus list not yet built

		activeCircuit.setActiveBusIndex(activeCircuit.getBusList().find(busName));

		if (activeCircuit.getActiveBusIndex() == -1) {
			result = 1;
			appendGlobalResult("setActiveBus: Bus " + busName + " not found.");
		}

		return result;
	}

	/**
	 * Pathname may be null
	 */
	public static void setDataPath(String pathName) {
		File f = new File(pathName);

		if ((pathName.length() > 0) && !f.exists()) {

			// try to create the directory
			if (f.mkdir()) {
				doSimpleMsg("Cannot create " + pathName + " directory.", 907);
				System.exit(0);
			}
		}

		dataDirectory = pathName;

		// Put a / on the end if not supplied. Allow a null specification.
		if (dataDirectory.length() > 0) {
			currentDirectory = dataDirectory;   // change to specified directory
			if (dataDirectory.charAt(dataDirectory.length()) != '/')
				dataDirectory += "/";
		}
	}


	public static void makeNewCircuit(String name) {
		String s;

		if (numCircuits < maxCircuits) {
			activeCircuit = new Circuit(name);
			activeDSSObject = Solution.activeSolutionObj;
			/*Handle = */ circuits.add(activeCircuit);
			numCircuits += 1;
			// pass remainder of string on to VSource
			s = Parser.getInstance().getRemainder();

			/* create a default circuit */
			solutionAbort = false;
			/* Voltage source named "source" connected to "SourceBus" */
			// load up the parser as if it were read in
			Executive.getInstance().setCommand("new object=vsource.source bus1=SourceBus " + s);
		} else {
			doErrorMsg("makeNewCircuit", "Cannot create new circuit.",
				"Max. circuits exceeded." + CRLF +
				"(Max no. of circuits=" + maxCircuits + ")", 906);
		}
	}

	/**
	 * Append a string to global result, separated by commas.
	 */
	public static void appendGlobalResult(String s) {
		globalResult += (globalResult.length() == 0) ? s : ", " + s;
	}

	/**
	 * Separate by CRLF.
	 */
	public static void appendGlobalResultCRLF(String s) {
		globalResult += (globalResult.length() > 0) ? CRLF + s : s;
	}

	public static void writeDebugFile(String s) {
		boolean append;
		if (debugFirstTime) {
			append = false;
			debugFirstTime = false;
		} else {
			append = true;
		}

		try {
			FileWriter fw = new FileWriter(dataDirectory + "DSSDebug.txt", append);
			PrintWriter pw = new PrintWriter(fw);

			pw.println(s);

			pw.close();
			fw.close();
		} catch (IOException e) {
			doSimpleMsg("Error writing debug file: " + e.getMessage(), 906);
		}
	}

	public static void resetQueryLogFile() {
		queryFirstTime = true;
	}

	/**
	 * Log file is written after a query command if logQueries is true.
	 */
	public static void writeQueryLogFile(String prop, String s) {
		FileWriter fw;
		PrintWriter pw;

		try {
			queryLogFileName = dataDirectory + "QueryLog.csv";
			queryLogFile = new File(queryLogFileName);

			if (queryFirstTime) {
				fw = new FileWriter(queryLogFile, false);
				pw = new PrintWriter(fw);
				pw.println("Time(h), Property, Result");
				queryFirstTime = false;
			} else {
				fw = new FileWriter(queryLogFile, true);
				pw = new PrintWriter(fw);
			}

			pw.printf("%.10g, %s, %s", activeCircuit.getSolution().getDblHour(), prop, s);
			pw.close();
			fw.close();
		} catch (IOException e) {
			doSimpleMsg("Error writing query log file: " + e.getMessage(), 908);
		}
	}

	public static String getDSSVersion() {
		// TODO: Implement getDSSVersion()
		return "Unknown.";
	}

	public static void readDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public static void writeDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public static boolean isDSSDLL(String fname) {
		throw new UnsupportedOperationException();
	}

}