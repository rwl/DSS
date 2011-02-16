package com.epri.dss.common.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.Solution;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;

public class Utilities {
	
	private static final int ZERONULL = 0;
	private static final String padString = "                                                  "; // 50 blanks
	private static final String padDotsString = " ................................................."; // 50 dots

	private Utilities() {
	}
	
	public static String expandFileName(String child) {
		try {
			return new File(System.getProperty("user.dir"), child).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Reallocates an array with a new size, and copies the contents
	 * of the old array to the new array.
	 * 
	 * @param oldArray the old array, to be reallocated.
	 * @param newSize the new array size.
	 * @return A new array with the same contents.
	 * 
	 * @see http://www.source-code.biz/snippets/java/3.htm
	 * @author Christian d'Heureuse <chdh@source-code.biz>
	 * @license LGPL
	 */
	public static Object resizeArray(Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class<?> elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0)
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		return newArray;
	}

	public static int compareTextShortest(String S1, String S2) {
		String TestStr;
		
		if (S1.length() < S2.length()) {
			TestStr = S2.substring(0, S1.length());  // TODO Check zero based indexing
			return TestStr.equals(S1) ? 1 : 0;
		} else {
			TestStr = S1.substring(0, S2.length());
			return TestStr.equals(S2) ? 1 : 0;
		}
	}

	/**
	 * Pad out a string with blanks to width characters.
	 */
	public static String pad(String S, int width) {
		return S.substring(0, S.length()) + padString.substring(0, width - S.length());
	}

	/**
	 * Pad out a string with dots to Width characters.
	 */
	public static String padDots(String S, int width) {
		return S.substring(0, S.length()) + padDotsString.substring(0, width - S.length());
	}

	/**
	 * Pad out a string with blanks to width characters or truncate to width chars.
	 */
	public static String padTrunc(String S, int width) {
		return pad(S, width).substring(0, width);
	}

	public static String fullName(CktElement pElem) {
		return encloseQuotes(pElem.getDSSClassName() + "." + pElem.getName());
	}

	/**
	 * Strips off everything up to a period.
	 */
	public static String stripExtension(String S) {
		int dotpos = S.indexOf('.');  // TODO Check zero based indexing
		if (dotpos == -1)
			dotpos = S.length();
		return S.substring(0, dotpos);
	}

	/**
	 * Returns everything past the first period.
	 */
	public static String stripClassName(String S) {
		return S.substring(S.indexOf('.'));
	}

	public static void fireOffEditor(String FileNm) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}
	
	public static void doShellCmd(String CmdString) {
		// FIXME Implement this method (assessing security concerns)
		throw new UnsupportedOperationException();
	}

	/**
	 * Put array values in parentheses separated by commas.
	 */
	public static String intArrayToString(int[] iarray, int count) {
		String Result = "(";
		for (int i = 0; i < count; i++) {
			Result = Result + String.valueOf(iarray[i]);
			if (i != count - 1)  // TODO Check zero based indexing
				Result = Result + ", ";
		}
		return Result + ')';
	}

	public static String encloseQuotes(String s) {
		return '"' + s + '"';
	}

	/**
	 * Interpret solution mode. Could be: "nominal", "daily",  "yearly",
	 *   "montecarlo", "dutycycle",  "loadduration", "peakdays", etc.
	 */
	public static int interpretSolveMode(String s) {
		String SLC;

		SLC = s.toLowerCase();

		switch (SLC.charAt(0)) {
		case 's':
			return Dynamics.SNAPSHOT;
		case 'd':
			switch (SLC.charAt(1)) {
			case 'u':
				return Dynamics.DUTYCYCLE;
			case 'i':
				return Dynamics.DIRECT;
			case 'y':
				return Dynamics.DYNAMICMODE;
			default:
				return Dynamics.DAILYMODE;
			}
		case 'f':
			return Dynamics.FAULTSTUDY;
		case 'h':
			return Dynamics.HARMONICMODE;
		case 'y':
			return Dynamics.YEARLYMODE;
		case 'm':
			switch (SLC.charAt(1)) {
			case '1':
				return Dynamics.MONTECARLO1;
			case '2':
				return Dynamics.MONTECARLO2;
			case '3':
				return Dynamics.MONTECARLO3;
			case 'f':
				return Dynamics.MONTEFAULT;
			default:
				return Dynamics.MONTECARLO1;
			}
		case 'p': 
			return Dynamics.PEAKDAY;
		case 'a':
			return Dynamics.AUTOADDFLAG;
		case 'l':
			switch (SLC.charAt(1)) {
			case 'd':
				switch (SLC.charAt(2)) {
				case '1':
					return Dynamics.LOADDURATION1;
				case '2':
					return Dynamics.LOADDURATION2;
				default:
					return Dynamics.LOADDURATION1;
				}
			default:
				return Dynamics.LOADDURATION1;
			}
		case 't':
			return Dynamics.GENERALTIME;
		default:
			return Dynamics.SNAPSHOT;
		}
	}

	/**
	 * Interpret solution control mode.
	 */
	public static int interpretControlMode(String s) {
		String SLC = s.toLowerCase();

		switch (SLC.charAt(0)) {
		case 'o':
			return DSSGlobals.CONTROLSOFF;
		case 'e':
			return DSSGlobals.EVENTDRIVEN;  // "event"
		case 't':
			return DSSGlobals.TIMEDRIVEN;   // "time"
		default:
			return DSSGlobals.CTRLSTATIC;
		}
	}

	public static int interpretLoadModel(String s) {
		int Result;
		String S2 = s.toLowerCase();
		
		switch (S2.charAt(0)) {
		case 'a':
			Result = DSSGlobals.ADMITTANCE;
		case 'p':
			Result = DSSGlobals.POWERFLOW;
		default:
			Result = DSSGlobals.ADMITTANCE;
		}
		
		/* If this represents a change, invalidate all the PC Yprims */
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (Result != ckt.getSolution().getLoadModel())
			ckt.invalidateAllPCElements();
		
		return Result;
	}

	/** 
	 * Interpret yes/no properties - can also be true/false.
	 */
	public static boolean interpretYesNo(String s) {
		String S2 = s.toLowerCase();
		switch (S2.charAt(0)) {
		case 'y': 
			return true;
		case 't': 
			return true;
		case 'n':
			return false;
		case 'f':
			return false;
		default:
			return false;
		}
	}

	/**
	 * Interpret the type of random variation in the load.
	 * none|gaussian|uniform|lognormal
	 */
	public static int interpretRandom(String s) {
		String SLC = s.toLowerCase();

		switch (SLC.charAt(0)) {
		case 'g':
			return DSSGlobals.GAUSSIAN;
		case 'u':
			return DSSGlobals.UNIFORM;
		case 'l':
			return DSSGlobals.LOGNORMAL;
		default:
		return 0;  // no variation for any other entry
		}
	}

	/**
	 * Type of device to automatically add. Default is capacitor.
	 */
	public static int interpretAddType(String s) {
		String SLC = s.toLowerCase();
		switch (SLC.charAt(0)) {
		case 'g':
			return DSSGlobals.GENADD;
		default:
			return DSSGlobals.CAPADD;
		}
	}

	/**
	 * Accepts (Case insensitive)
	 *   delta or LL    Result = 1       
	 *   Y, wye, or LN  Result = 0
	 */
	public static int interpretConnection(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'y':
			return 0;  // Wye
		case 'w':
			return 0;  // Wye
		case 'd':
			return 1;  // Delta or line-Line
		case 'l':
			switch (s.toLowerCase().charAt(1)) {
			case 'n':
				return 0;
			case 'l':
				return 1;
			}
		default:
			return 0;
		}
	}

	public static int interpretSolveAlg(String s) {
		String SLC = s.toLowerCase().substring(0, 1);  // TODO Check zero based indexing
		if (SLC.equals("ne")) {
			return Solution.NEWTONSOLVE;
		} else {
			return Solution.NORMALSOLVE;
		}
	}

	/**
	 * Returns true if positive sequence.
	 */
	public static boolean interpretCktModel(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'p':
			return true;
		default:
			return false;
		}
	}

	/**
	 * Set all elements of a double array.
	 */
	public static void initDblArray(int NumValues, double[] Xarray, double Value) {
		for (int i = 0; i < NumValues; i++)
			Xarray[i] = Value;
	}

	/**
	 * Set all elements of a integer array.
	 */
	public static void initIntArray(int NumValues, int[] Xarray, int Value) {
		for (int i = 0; i < NumValues; i++)
			Xarray[i] = Value;
	}

	/**
	 * Get numeric values from an array specified either as a list on numbers
	 * or a text file spec. ResultArray must be allocated to MaxValues by
	 * calling routine. File is assumed to have one value per line.
	 */
	public static int interpretDblArray(String s, int MaxValues, double[] ResultArray) {
		FileInputStream fileStream = null;
		BufferedInputStream bufferedStream = null;
		DataInputStream dataStream = null;
		
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(s);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();
		int Result = MaxValues; // Default return value;

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (ParmName.equals("file")) {
			// load the list from a file
			try {
				// FIXME Use BufferedReader not BufferedInputStream
				fileStream = new FileInputStream(Param);
				bufferedStream = new BufferedInputStream(fileStream);
				dataStream = new DataInputStream(bufferedStream);

				for (int i = 0; i < MaxValues; i++) {
					try {
						if (dataStream.available() != 0) {
							ResultArray[i] = dataStream.readDouble();
						} else {
							Result = i - 1;  // This will be different if less found;  TODO Check zero based indexing
							break;
						}
					} catch (Exception e) {
						Globals.doSimpleMsg(String.format("Error reading %d-th numeric array value from file: \"%s\" Error is:", i, Param, e.getMessage()), 705);
						Result = i - 1;
						break;
					}
				}
				fileStream.close();
				bufferedStream.close();
				dataStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ((ParmName.length() > 0) && (compareTextShortest(ParmName, "dblfile") == 0)) {
			// load the list from a file of doubles (no checking done on type of data)
			throw new UnsupportedOperationException();
			// TODO Implement this section
		} else if ((ParmName.length() > 0) && (compareTextShortest(ParmName, "sngfile") == 0)) {
			// load the list from a file of singles (no checking done on type of data)
			throw new UnsupportedOperationException();
			// TODO Implement this section
		} else {
			// Parse list of values off input string
		
			// Parse Values of array list
			for (int i = 0; i < MaxValues; i++) {
				ResultArray[i] = Globals.getAuxParser().makeDouble();  // Fills array with zeros if we run out of numbers
				Globals.getAuxParser().getNextParam();
			}
		}
		return Result;
	}

	/**
	 * Get numeric values from an array specified either as a list on numbers
	 * or a text file spec. ResultArray must be allocated to MaxValues by
	 * calling routine. File is assumed to have one value per line.
	 */
	public static int interpretIntArray(String s, int MaxValues, int[] ResultArray) {
		FileInputStream fileStream = null;
		BufferedInputStream bufferedStream = null;
		DataInputStream dataStream = null;
		
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		Globals.getAuxParser().setCmdString(s);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();
		int Result = MaxValues;  // Default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (ParmName.equals("file")) {
			// load the list from a file
			try {
				// FIXME Use BufferedReader not BufferedInputStream 
				fileStream = new FileInputStream(Param);
				bufferedStream = new BufferedInputStream(fileStream);
				dataStream = new DataInputStream(bufferedStream);

				for (int i = 0; i < MaxValues; i++) {
					try {
						if (dataStream.available() != 0) {
							ResultArray[i] = dataStream.readInt();
						} else {
							Result = i - 1;  // This will be different if less found;  TODO Check zero based indexing
							break;
						}
					} catch (Exception e) {
						Globals.doSimpleMsg(String.format("Error trying to read numeric array values from file: \""+Param +"\"  Error is: "+e.getMessage()), 706);
						Result = i - 1;
						break;
					}
				}
				fileStream.close();
				bufferedStream.close();
				dataStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {  // Parse list of values off input string

			// Parse Values of array list
			for (int i = 0; i < MaxValues; i++) {
				ResultArray[i] = Globals.getAuxParser().makeInteger();  // Fills array with zeros if we run out of numbers
				Globals.getAuxParser().getNextParam();
			}
		}
		return Result;
	}

	/**
	 * Return stepsize in seconds.
	 */
	public static double interpretTimeStepSize(String s) {
		int Code;
		char ch;
		String s2;
		double Result;
		
		DSSGlobals Globals = DSSGlobals.getInstance();

		/* Try to convert and see if we get an error */
		try {
			return Double.valueOf(s);  // Only a number was specified, so must be seconds
		} catch (NumberFormatException e) {
			/* Error occurred so must have a units specifier */
			ch = s.charAt(s.length() - 1);  // get last character
			s2 = s.substring(0, s.length() - 2);
			try {
				Result = Double.valueOf(s2);
				switch (ch) {
				case 'h':
					Result = Result * 3600.0;
				case 'm':
					Result = Result * 60.0;
				case 's':
					// do nothing
				default:
					Result = Globals.getActiveCircuit().getSolution().getDynaVars().h;  // Don't change it
					Globals.doSimpleMsg("Error in specification of StepSize: \"" + s +"\" Units can only be h, m, or s (single char only) ", 99934);
				}
			} catch (NumberFormatException ee) {
				Result = Globals.getActiveCircuit().getSolution().getDynaVars().h; // Don't change it
				Globals.doSimpleMsg("Error in specification of StepSize: " + s, 99933);
				return Result;
			}
		}
			
		return Result;
	}

	public static void initStringToNull(String S) {
		//Move(ZeroNull, S, 4);
		S = null;
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretAndAllocStrArray(String s, int Size, String[] ResultArray) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		// Throw away any previous allocation
		ResultArray = new String[0];

		// Now Reallocate
		int MaxSize = 100;  // initialize
		Size = 0;
		ResultArray = new String[MaxSize];

		Globals.getAuxParser().setCmdString(s);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */
		if (ParmName.equals("file")) {
			// load the list from a file
			try {
				FileInputStream fileStream = new FileInputStream(Param);
				DataInputStream dataStream = new DataInputStream(fileStream);
				BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
				
				while ((Param = reader.readLine()) != null) {
					if (Param != "") {  // Ignore blank lines in file
						Size += 1;
						if (Size > MaxSize) {
							MaxSize += 100;
							ResultArray = (String[]) resizeArray(ResultArray, MaxSize);
						}
						ResultArray[Size] = Param;
					}
				}
				fileStream.close();
				dataStream.close();
				reader.close();
			} catch (Exception e) {
				Globals.doSimpleMsg("Error trying to read numeric array values from a file. Error is: "+e.getMessage(), 707);
			}
			
		} else {  // Parse list of values off input string

			// Parse Values of array list
			while (Param != "") {
				Size += 1;
				if (Size > MaxSize) {
					MaxSize += 100;
					ResultArray = (String[]) resizeArray(ResultArray, MaxSize);
				}
				ResultArray[Size] = Param;
				ParmName = Globals.getAuxParser().getNextParam();
				Param = Globals.getAuxParser().makeString();
			}
		}

		MaxSize = Size;  // Get rid of excess allocation
		ResultArray = (String[]) resizeArray(ResultArray, MaxSize);
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretStringListArray(String s, ArrayList<String> ResultList) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		String NextParam;
		
		// Throw away any previous allocation
		ResultList.clear();

		Globals.getAuxParser().setCmdString(S);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */

		if (ParmName.equals("file")) {
			// load the list from a file
			try {
				FileInputStream fileStream = new FileInputStream(Param);
				DataInputStream dataStream = new DataInputStream(fileStream);
				BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
				
				while ((Param = reader.readLine()) != null) {
					Globals.getAuxParser().setCmdString(Param);
					ParmName = Globals.getAuxParser().getNextParam();
					NextParam = Globals.getAuxParser().makeString();
					if (NextParam.length() > 0)  // Ignore Blank Lines in File
						ResultList.add(NextParam);
				}
				fileStream.close();
				dataStream.close();
				reader.close();
			} catch (Exception e) {
				Globals.doSimpleMsg("Error trying to read numeric array values from a file. Error is: "+e.getMessage(), 708);
			}
		} else {  // Parse list of values off input string

			// Parse Values of array list
			while (Param != "") {
				ResultList.add(Param);
				ParmName = Globals.getAuxParser().getNextParam();
				Param = Globals.getAuxParser().makeString();
			}
		}
	}
	
	public static void parseObjectClassandName(String FullObjName, String ClassName, String ObjName) {
		// Split off obj class and name
		int dotpos = FullObjName.indexOf('.');  // TODO Check zero based indexing
		switch (dotpos) {
		case -1:
			ObjName = FullObjName.substring(0, FullObjName.length());  // assume it is all objname; class defaults
			ClassName = "";
		default:
			ClassName = FullObjName.substring(0, dotpos);
			ObjName   = FullObjName.substring(dotpos + 1, FullObjName.length());
		}
	}

	public static String getSolutionModeIDName(int idx) {

		switch (idx) {
		case Dynamics.SNAPSHOT:
			return "Snap";
		case Dynamics.DAILYMODE:
			return "Daily";
		case Dynamics.YEARLYMODE:
			return "Yearly";
		case Dynamics.MONTECARLO1:
			return "M1";
		case Dynamics.MONTECARLO2:
			return "M2";
		case Dynamics.MONTECARLO3:
			return "M3";
		case Dynamics.LOADDURATION1:
			return "LD1";
		case Dynamics.LOADDURATION2:
			return "LD2";
		case Dynamics.PEAKDAY:
			return "Peakday";
		case Dynamics.DUTYCYCLE:
			return "DUtycycle";
		case Dynamics.DIRECT:
			return "DIrect";
		case Dynamics.DYNAMICMODE:
			return "DYnamic";
		case Dynamics.MONTEFAULT:
			return "MF";
		case Dynamics.FAULTSTUDY:
			return "Faultstudy";
		case Dynamics.AUTOADDFLAG:
			return "Autoadd";
		case Dynamics.HARMONICMODE:
			return "Harmonic";
		case Dynamics.GENERALTIME:
			return "Time";
		default:
			return "UNKNOWN";
		}
	}

	public static String getSolutionModeID() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt != null) {
			return getSolutionModeIDName(ckt.getSolution().getMode());
		} else {
			return "UNKNOWN";
		}
	}
	
	

	public static void showMessageBeep(String s) {

	}

	public static void parseIntArray(int[] iarray, int count, String s) {

	}

	public static int interpretLoadShapeClass(String s) {
		return 0;
	}

	public static int interpretEarthModel(String s) {
		return 0;
	}

	public static int interpretColorName(String s) {
		return 0;
	}

	public static String getControlModeID() {
		return null;
	}

	public static String getRandomModeID() {
		return null;
	}

	public static String getLoadModel() {
		return null;
	}

	public static String getActiveLoadShapeClass() {
		return null;
	}

	public static String getDSSArray_Real(int n, double[] dbls) {
		return null;
	}

	public static String getDSSArray_Integer(int n, int[] ints) {
		return null;
	}

	public static String getEarthModel(int n) {
		return null;
	}

	public static int doExecutiveCommand(String s) {
		return 0;
	}

	public static int getCktElementIndex(String fullObjName) {
		return 0;
	}

	public static boolean isShuntElement(CktElement Elem) {
		return false;
	}

	public static boolean isLineElement(CktElement Elem) {
		return false;
	}

	public static boolean isTransformerElement(CktElement Elem) {
		return false;
	}

	public static boolean isStubLine(CktElement Elem) {
		return false;
	}

	public static boolean checkParallel(CktElement Line1, CktElement Line2) {
		return false;
	}

	public static boolean allTerminalsClosed(CktElement thisElement) {
		return false;
	}

	public static String strReal(double Value, int NumDecimals) {
		return null;
	}

	public static void dumpAllDSSCommands(String Filename) {

	}

	public static void dumpAllocationFactors(String Filename) {

	}

	public static void dumpComplexMatrix(PrintStream F,
			CMatrix AMatrix) {

	}

	public static double nearestBasekV(double kV) {
		return 0;
	}

	public static double presentTimeInSec() {
		return 0;
	}

	public static int doResetFaults() {
		return 0;
	}

	public static int doResetControls() {
		return 0;
	}

	public static void doResetKeepList() {

	}

	public static int getNodeNum(int NodeRef) {
		return 0;
	}

	public static Complex CmulReal_im(Complex a, double Mult) {
		return null;
	}

	public static double maxDblArrayValue(int npts, double[] dbls) {
		return 0;
	}

	public static int iMaxAbsdblArrayValue(int npts, double[] dbls) {
		return 0;
	}

	public static boolean writeClassFile(DSSClass DSS_Class, String FileName,
			boolean isCktElement) {
		return false;
	}

	public static boolean writeVsourceClassFile(DSSClass DSS_Class,
			boolean isCktElement) {
		return false;
	}

	public static void writeActiveDSSObject(PrintStream F, String NewOrEdit) {

	}

	public static String checkForBlanks(String S) {
		return null;
	}

	public static boolean rewriteAlignedFile(String FileName) {
		return false;
	}

	public static void clearEventLog() {

	}

	public static void appendToEventLog(String opdev, String action) {

	}

	public static void logThisEvent(String EventName) {

	}

	public static void rotatePhasorDeg(Complex Phasor, double h,
			double AngleDeg) {

	}

	public static void rotatePhasorRad(Complex Phasor, double h,
			double AngleRad) {

	}

	public static void convertComplexArrayToPolar(Complex[] Buffer,
			int N) {

	}

	public static void convertComplexArrayToPowerandPF(Complex[] Buffer,
			int N) {

	}

	public static Complex residual(Object p, int Nph) {
		return null;
	}

	public static Complex residualPolar(Object p, int Nph) {
		return null;
	}

	public static double powerFactor(Complex S) {
		return 0;
	}

	public static double convertPFToPFRange2(double value) {
		return 0;
	}

	public static double convertPFRange2ToPF(double value) {
		return 0;
	}

	public static void CmulArray(Complex[] pc, double Multiplier,
			int size) {

	}

	public static void calcInitialMachineStates() {

	}

	public static void invalidateAllMachines() {

	}

	public static boolean initializeForHarmonics() {
		return false;
	}

	public static boolean savePresentVoltages() {
		return false;
	}

	public static boolean retrieveSavedVoltages() {
		return false;
	}

	public static double getMaxPUVoltage() {
		return 0;
	}

	public static double getMinPUVoltage(boolean ignoreNeutrals) {
		return 0;
	}

	public static Complex getTotalPowerFromSources() {
		return null;
	}

	public static int getMaxCktElementSize() {
		return 0;
	}

	public static int getUniqueNodeNumber(String sBusName, int StartNode) {
		return 0;
	}

	public static boolean isPathBetween(PDElement FromLine, PDElement ToLine) {
		return false;
	}

	public static void traceAndEdit(PDElement FromLine, PDElement ToLine,
			String EditStr) {

	}

	public static void goForwardAndRephase(PDElement FromLine,
			String PhaseString, String EditStr, String ScriptFileName,
			boolean TransStop) {

	}

	public static void makeDistributedGenerators(double kW, double PF,
			String How, int Skip, String Fname) {

	}

	public static void enableFeeders() {

	}

	public static void disableFeeders() {

	}

	public static void initializeFeeders() {

	}

	public static void forwardSweepAllFeeders() {

	}

	public static void backwardSweepAllFeeders() {

	}

}
