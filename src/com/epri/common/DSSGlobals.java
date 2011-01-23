package com.epri.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

import com.epri.dss.general.DSSObject;

public class DSSGlobals {

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

	/** Variables */
	public static boolean DLLFirstTime = true;
	public static PrintStream DLLDebugFile;
	public static String DSS_IniFileName = "OpenDSSPanel.ini";
	// Registry   (See Executive)
	public static IniRegSave DSS_Registry = IniRegSave("\\Software\\OpenDSS");

	public static boolean IsDLL = false;
	public static boolean NoFormsAllowed = false;

	public static DSSCircuit ActiveCircuit;
	public static DSSClass ActiveDSSClass;
	public static int LastClassReferenced;  // index of class of last thing edited
	public static DSSObject ActiveDSSObject;
	public static int NumCircuits = 0;
	public static int MaxCircuits = 1;
	public static int MaxBusLimit = 0; // Set in Validation
	public static int MaxAllocationIterations = 2;
	public static PointerList Circuits;
	public static PointerList DSSObjs;

    // Auxiliary parser for use by anybody for reparsing values
	public static Parser AuxParser = new Parser();

	public static boolean ErrorPending = false;
	public static int CmdResult = 0;
	public static int ErrorNumber = 0;
	public static String LastErrorMessage = "";

	public static String LastFileCompiled = "";
	public static boolean LastCommandWasCompile = false;

	// 120-degree shift constant
	public static double[] CALPHA = new double[] {-0.5, -0.866025};
	public static double SQRT2 = Math.sqrt(2.0);
	public static double SQRT3 = Math.sqrt(3.0);
	public static double InvSQRT3 = 1.0 / SQRT3;
	public static double InvSQRT3x1000 = InvSQRT3 * 1000.0;
	public static boolean SolutionAbort = false;
	public static boolean InShowResults = false;
	public static boolean Redirect_Abort = false;
	public static boolean In_Redirect = false;
	public static boolean DIFilesAreOpen = false;
	public static boolean AutoShowExport = false;
	public static boolean SolutionWasAttempted = false;

	public static String GlobalHelpString = "";
	public static String GlobalPropertyValue = "";
	public static String GlobalResult = "";
	public static String VersionString = "Version " + GetDSSVersion();

	public static String DefaultEditor = "NotePad";     // normally, Notepad
//	public static String DSSFileName = GetDSSExeFile();     // Name of current exe or DLL
//	public static String DSSDirectory = new File(DSSFileName).getParent();     // where the current exe resides
//	public static String StartupDirectory = GetCurrentDir() + "\\";     // Where we started
//	public static String DSSDataDirectory = StartupDirectory;
	public static String CircuitName_;     // Name of Circuit with a "_" appended

	public static double DefaultBaseFreq = 60.0;
	public static double DaisySize = 1.0;

	// Some commonly used classes   so we can find them easily
	public static LoadShape LoadShapeClass;
	public static GrowthShape GrowthShapeClass;
	public static Spectrum SpectrumClass;
	public static DSSClass SolutionClass;
	public static EnergyMeter EnergyMeterClass;
	public static Feeder FeederClass;
	public static DSSMonitor MonitorClass;
	public static Sensor SensorClass;
	public static TCC_Curve TCC_CurveClass;
	public static WireData WireDataClass;
	public static LineSpacing LineSpacingClass;
	public static Storage StorageClass;


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

	public static void DoErrorMsg(String S, String Emsg, String ProbCause, int ErrNum) {
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

	public static void DoSimpleMsg(String S, int ErrNum) {
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

	public static void ClearAllCircuits() {
		ActiveCircuit = Circuits.First();
		while (ActiveCircuit != null) {
			ActiveCircuit.Free();
			ActiveCircuit = Circuits.Next();
		}
		Circuits.Free();
		Circuits = new PointerList(2);   // Make a new list of circuits
		NumCircuits = 0;
	}

	/* Set object active by name */
	public static void SetObject(String Param) {
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

	public static int SetActiveBus(String BusName) {
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
	public static void SetDataPath(String PathName) {
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


	public static void MakeNewCircuit(String Name) {
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
	public static void AppendGlobalResult(String S) {
		if (GlobalResult.length() == 0) {
			GlobalResult = S;
		} else {
			GlobalResult = GlobalResult + ", " + S;
		}
	}

	/* Separate by CRLF */
	public static void AppendGlobalResultCRLF(String S) {
		if (GlobalResult.length() > 0) {
			GlobalResult = GlobalResult + CRLF + S;
		} else {
			GlobalResult = S;
		}
	}

	public static void WriteDLLDebugFile(String S) {
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

	public static void ReadDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public static void WriteDSS_Registry() {
		throw new UnsupportedOperationException();
	}

	public static boolean IsDSSDLL(String Fname) {
		throw new UnsupportedOperationException();
	}

	public static String GetDSSVersion() {
		// TODO: Implement GetDSSVersion()
		return "Unknown.";
	}

}