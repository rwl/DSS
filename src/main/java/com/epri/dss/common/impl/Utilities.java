package com.epri.dss.common.impl;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;
import com.epri.dss.shared.impl.HashListImpl;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.FeederObj;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.control.ControlElem;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.delivery.FaultObj;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.delivery.ReactorObj;
import com.epri.dss.executive.impl.DSSExecutive;
import com.epri.dss.executive.impl.ExecCommands;
import com.epri.dss.executive.impl.ExecOptions;
import com.epri.dss.general.DSSObject;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.HashList;

public class Utilities {

	private static final String padString =
			"                                                  "; // 50 blanks
	private static final String padDotsString =
			" ................................................."; // 50 dots

	private Utilities() {
	}

	public static String expandFileName(String child) {
		if (child.length() == 0)
			return "";

		if (new File(child).isAbsolute())
			return child;

		try {
			return new File(DSSGlobals.getInstance().getCurrentDirectory(),
					child).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String extractFileDir(String path) {
		try {
			return new File(path).getParentFile().getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
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
			TestStr = S2.substring(0, S1.length());
			return TestStr.equalsIgnoreCase(S1) ? 0 : -1;
		} else {
			TestStr = S1.substring(0, S2.length());
			return TestStr.equalsIgnoreCase(S2) ? 0 : -1;
		}
	}

	/**
	 * Pad out a string with blanks to width characters.
	 */
	public static String pad(String S, int width) {
		return S.substring(0, S.length()) + padString.substring(0, width - S.length());
	}

	/**
	 * Pad out a string with dots to width characters.
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
			break;
		case 'p':
			Result = DSSGlobals.POWERFLOW;
			break;
		default:
			Result = DSSGlobals.ADMITTANCE;
			break;
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
	 * Accepts (case insensitive)
	 *   delta or LL    Result = 1
	 *   Y, wye, or LN  Result = 0
	 */
	public static int interpretConnection(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'y':
			return 0;  // wye
		case 'w':
			return 0;  // wye
		case 'd':
			return 1;  // delta or line-Line
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
		String SLC = s.toLowerCase().substring(0, 2);
		if (SLC.equalsIgnoreCase("ne")) {
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
	 * or a text file spec. ResultArray must be allocated to maxValues by
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
		int Result = MaxValues;  // default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (ParmName.equalsIgnoreCase("file")) {
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
							Result = i - 1;  // this will be different if less found;  TODO Check zero based indexing
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
			// parse list of values off input string

			// parse Values of array list
			for (int i = 0; i < MaxValues; i++) {
				ResultArray[i] = Globals.getAuxParser().makeDouble();  // fills array with zeros if we run out of numbers
				Globals.getAuxParser().getNextParam();
			}
		}
		return Result;
	}

	/**
	 * Get numeric values from an array specified either as a list on numbers
	 * or a text file spec. ResultArray must be allocated to maxValues by
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
		int Result = MaxValues;  // default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (ParmName.equalsIgnoreCase("file")) {
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
							Result = i - 1;  // this will be different if less found;  TODO Check zero based indexing
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

		} else {  // parse list of values off input string

			// parse values of array list
			for (int i = 0; i < MaxValues; i++) {
				ResultArray[i] = Globals.getAuxParser().makeInteger();  // fills array with zeros if we run out of numbers
				Globals.getAuxParser().getNextParam();
			}
		}
		return Result;
	}

	/**
	 * Return stepsize in seconds.
	 */
	public static double interpretTimeStepSize(String s) {
		char ch;
		String s2;
		double Result;

		DSSGlobals Globals = DSSGlobals.getInstance();

		/* Try to convert and see if we get an error */
		try {
			return Double.valueOf(s);  // only a number was specified, so must be seconds
		} catch (NumberFormatException e) {
			/* Error occurred so must have a units specifier */
			ch = s.charAt(s.length() - 1);  // get last character
			s2 = s.substring(0, s.length() - 2);
			try {
				Result = Double.valueOf(s2);
				switch (ch) {
				case 'h':
					Result = Result * 3600.0;
					break;
				case 'm':
					Result = Result * 60.0;
					break;
				case 's':
					// do nothing
					break;
				default:
					Result = Globals.getActiveCircuit().getSolution().getDynaVars().h;  // don't change it
					Globals.doSimpleMsg("Error in specification of stepSize: \"" + s +"\" Units can only be h, m, or s (single char only) ", 99934);
					break;
				}
			} catch (NumberFormatException ee) {
				Result = Globals.getActiveCircuit().getSolution().getDynaVars().h; // don't change it
				Globals.doSimpleMsg("Error in specification of stepSize: " + s, 99933);
				return Result;
			}
		}

		return Result;
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretAndAllocStrArray(String s, int Size, String[] ResultArray) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		// throw away any previous allocation
		ResultArray = new String[0];

		// now reallocate
		int MaxSize = 100;  // initialize
		Size = 0;
		ResultArray = new String[MaxSize];

		Globals.getAuxParser().setCmdString(s);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */
		if (ParmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				FileInputStream fileStream = new FileInputStream(Param);
				DataInputStream dataStream = new DataInputStream(fileStream);
				BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));

				while ((Param = reader.readLine()) != null) {
					if (Param != "") {  // ignore blank lines in file
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

		} else {  // parse list of values off input string

			// parse values of array list
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

		MaxSize = Size;  // get rid of excess allocation
		ResultArray = (String[]) resizeArray(ResultArray, MaxSize);
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretStringListArray(String s, List<String> ResultList) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		String NextParam;

		// throw away any previous allocation
		ResultList.clear();

		Globals.getAuxParser().setCmdString(s);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */

		if (ParmName.equalsIgnoreCase("file")) {
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
		} else {  // parse list of values off input string

			// parse values of array list
			while (Param != "") {
				ResultList.add(Param);
				ParmName = Globals.getAuxParser().getNextParam();
				Param = Globals.getAuxParser().makeString();
			}
		}
	}

	public static void parseObjectClassandName(String FullObjName, StringBuffer ClassName, StringBuffer ObjName) {
		// split off obj class and name
		int dotpos = FullObjName.indexOf('.');
		switch (dotpos) {
		case -1:
			ObjName.delete(0, ObjName.length());
			// assume it is all objname; class defaults
			ObjName.append( FullObjName.substring(0, FullObjName.length()) );
			ClassName.delete(0, ClassName.length());
			break;
		default:
			ClassName.delete(0, ClassName.length());
			ClassName.append( FullObjName.substring(0, dotpos) );
			ObjName.delete(0, ObjName.length());
			ObjName.append( FullObjName.substring(dotpos + 1, FullObjName.length()) );
			break;
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

	public static String getControlModeID() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt != null) {
			switch (ckt.getSolution().getControlMode()) {
			case DSSGlobals.CTRLSTATIC:
				return "STATIC";
			case DSSGlobals.EVENTDRIVEN:
				return "EVENT";
			case DSSGlobals.TIMEDRIVEN:
				return "TIME";
			case DSSGlobals.CONTROLSOFF:
				return "OFF";
			default:
				return "UNKNOWN";
			}
		} else {
			return "UNKNOWN";
		}
	}

	public static String getRandomModeID() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt != null) {
			switch (ckt.getSolution().getRandomType()) {
			case 0:
				return "None";
			case DSSGlobals.GAUSSIAN:
				return "Gaussian";
			case DSSGlobals.UNIFORM:
				return "Uniform";
			case DSSGlobals.LOGNORMAL:
				return "LogNormal";
			default:
				return "Unknown";
			}
		} else {
			return "Unknown";
		}
	}

	public static String getLoadModel() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		switch (ckt.getSolution().getLoadModel()) {
		case DSSGlobals.ADMITTANCE:
			return "Admittance";
		default:
			return "PowerFlow";
		}
	}

	public static int[] parseIntArray(int[] iarray, MutableInt count, String s) {
		@SuppressWarnings("unused")
		String ParamName;
		String Param = " ";

		DSSGlobals Globals = DSSGlobals.getInstance();

		// parse the line once to get the count of tokens on string, S
		Globals.getAuxParser().setCmdString(s);
		count.setValue(0);
		while (Param.length() > 0) {
			ParamName = Globals.getAuxParser().getNextParam();
			Param     = Globals.getAuxParser().makeString();
			if (Param.length() > 0)
				count.increment();
		}

		// reallocate iarray to new size
		iarray = (int[]) resizeArray(iarray, count.intValue());

		// Parse again for real
		Globals.getAuxParser().setCmdString(s);
		for (int i = 0; i < count.intValue(); i++) {
			ParamName = Globals.getAuxParser().getNextParam();
			iarray[i] = Globals.getAuxParser().makeInteger();
		}

		return iarray;
	}

	public static boolean isShuntElement(CktElement Elem) {
		switch (Elem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
		case DSSClassDefs.CAP_ELEMENT:
			CapacitorObj cElem = (CapacitorObj) Elem;
			return cElem.isShunt();
		case DSSClassDefs.REACTOR_ELEMENT:
			ReactorObj rElem = (ReactorObj) Elem;
			return rElem.isShunt();
		default:
			return false;
		}
	}

	public static boolean isLineElement(CktElement Elem) {
		if ((Elem.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LINE_ELEMENT) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTransformerElement(CktElement Elem) {
		if ((Elem.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.XFMR_ELEMENT) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isStubLine(CktElement Elem) {
		double Ztest;
		LineObj LineElement = (LineObj) Elem;

		/* Get positive sequence or equivalent from matrix */
		if (LineElement.isSymComponentsModel()) {
			Ztest = (new Complex(LineElement.getR1(), LineElement.getX1())).abs() * LineElement.getLen();
		} else {
			/* Get impedance from Z matrix */   /* Zs - Zm */
			if (LineElement.getNPhases() > 1) {  // TODO Check zero based indexing
				Ztest = LineElement.getZ().getElement(0, 0).subtract(LineElement.getZ().getElement(0, 1)).abs() * LineElement.getLen();
			} else {
				Ztest = LineElement.getZ().getElement(0, 0).abs() * LineElement.getLen();
			}
		}

		if (Ztest <= DSSGlobals.getInstance().getActiveCircuit().getReductionZmag()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Given the full object name, return the index to the circuit element in the
	 * active circuit.  Use full name if given, else assume last class referenced.
	 */
	public static int getCktElementIndex(String fullObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int DevClassIndex, DevIndex;
		StringBuffer DevClassName = new StringBuffer();
		StringBuffer DevName = new StringBuffer();

		int Result = 0;  // default return value
		parseObjectClassandName(fullObjName, DevClassName, DevName);
		DevClassIndex = Globals.getClassNames().find(DevClassName.toString());
		if (DevClassIndex == -1)
			DevClassIndex = Globals.getLastClassReferenced();

		// Since there could be devices of the same name of different classes,
		// loop until we find one of the correct class
		Circuit ckt = Globals.getActiveCircuit();
		DevIndex = ckt.getDeviceList().find(DevName.toString());
		while (DevIndex > -1) {
			if ((ckt.getDeviceRef()[DevIndex]).cktElementClass == DevClassIndex)  // we got a match
				return DevIndex;
			DevIndex = ckt.getDeviceList().findNext();
		}

		return Result;
	}

	public static String strReal(double Value, int NumDecimals) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			String fmt = String.format("%%.%df", NumDecimals);
			pw.printf(fmt, Value);
			return sw.toString();
		} catch (Exception e) {
			return "*****";
		}
	}

	public static void dumpAllocationFactors(String FileName) {
		PrintWriter F;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			FileName = Globals.getDSSDataDirectory() + "AllocationFactors.txt";
			FileWriter FW = new FileWriter(FileName);
			F = new PrintWriter(FW);
		} catch (IOException e) {
			Globals.doErrorMsg("Error opening "+FileName+" for writing.", e.getMessage(), " File protected or other file error.", 709);
			return;
		}

		for (LoadObj pLoad : Globals.getActiveCircuit().getLoads()) {
			switch (pLoad.getLoadSpecType()) {
			case 3:
				F.println("Load."+pLoad.getName()+".AllocationFactor=" + String.format("%-.5g", pLoad.getkVAAllocationFactor()));
				break;
			case 4:
				F.println("Load."+pLoad.getName()+".CFactor=" + String.format("%-.5g", pLoad.getCFactor()));
				break;
			}
		}

		F.close();
	}

	public static void dumpAllDSSCommands(String FileName) {
		PrintWriter F;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			FileName = Globals.getDSSDataDirectory() + "AllocationFactors.txt";
			FileWriter FW = new FileWriter(FileName);
			F = new PrintWriter(FW);
		} catch (IOException e) {
			Globals.doErrorMsg("Error opening "+FileName+" for writing.", e.getMessage(), "Disk protected or other file error", 710);
			return;
		}

		// dump Executive commands
		F.println("[execcommands]");
		for (int i = 0; i < ExecCommands.NumExecCommands; i++) {
			F.println(i +" + \"" + ExecCommands.getInstance().getExecCommand(i) + "\" \"" + ExecCommands.getInstance().getCommandHelp(i) + "\"");
		}

		// Dump Executive Options
		F.println("[execoptions]");
		for (int i = 0; i < ExecOptions.NumExecOptions; i++) {
			F.println(i + ", \"" + ExecOptions.getInstance().getExecOption(i) + "\", \"" + ExecOptions.getInstance().getOptionHelp(i) + "\"");
		}

		// Dump all present DSSClasses
		for (DSSClass pClass : Globals.getDSSClassList()) {
			F.println("[" + pClass.getName() + "]");
			for (int i = 0; i < pClass.getNumProperties(); i++)
				F.println(i + ", \"" + pClass.getPropertyName()[i] + "\" \"" + pClass.getPropertyHelp()[i] + "\"");
		}

		F.close();
	}

	/**
	 * Find closest base voltage.
	 */
	public static double nearestBasekV(double kV) {
		double Diff;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		int Count = 1;
		double TestkV = ckt.getLegalVoltageBases()[0];
		double Result = TestkV;
		double MinDiff = 1.e50;  // big number

		while (TestkV != 0.0) {
			Diff = Math.abs(1.0 - kV / TestkV);  // get per unit difference
			if (Diff < MinDiff) {
				MinDiff = Diff;
				Result = TestkV;
			}

			Count += 1;
			TestkV = ckt.getLegalVoltageBases()[Count];
		}
		return Result;
	}

	public static boolean savePresentVoltages() {
		PrintWriter F;
		double dNumNodes;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			FileWriter FW = new FileWriter(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "SavedVoltages.dbl");
			F = new PrintWriter(FW);
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening/creating file to save voltages: " + e.getMessage(), 711);
			return false;
		}

		try {
			Circuit ckt = Globals.getActiveCircuit();
			SolutionObj sol = ckt.getSolution();

			dNumNodes = ckt.getNumNodes();
			F.printf("%.d",  dNumNodes);
			for (int i = 0; i < ckt.getNumNodes(); i++) {
				F.printf(" %.5f %.5f", sol.getNodeV()[i].getReal(), sol.getNodeV()[i].getImaginary());
			}

			F.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error writing file to save voltages: " + e.getMessage(), 712);
			return false;
		}

		return true;
	}

	public static boolean retrieveSavedVoltages() {
		// FIXME Implement this method and savePresentVoltages() using MatrixMarket format.
		throw new UnsupportedOperationException();
	}

	/**
	 * Intialize load and generator base values for harmonics analysis.
	 */
	public static boolean initializeForHarmonics() {
		if (savePresentVoltages()) {  // Zap voltage vector to disk
			for (PCElement pcElem : DSSGlobals.getInstance().getActiveCircuit().getPCElements())
				pcElem.initHarmonics();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Does all PC elements.
	 *
	 * If state variables not defined for a PC class, does nothing.
	 */
	public static void calcInitialMachineStates() {
		for (PCElement pcElem : DSSGlobals.getInstance().getActiveCircuit().getPCElements()) {
			if (pcElem.isEnabled())
				pcElem.initStateVars();
		}
	}

	/**
	 * For now, just does generators.
	 */
	public static void invalidateAllMachines() {
		for (GeneratorObj pGen : DSSGlobals.getInstance().getActiveCircuit().getGenerators())
			pGen.setYprimInvalid(true);
	}

	public static double presentTimeInSec() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		return sol.getDynaVars().t + sol.getIntHour() * 3600.0;
	}

	public static int doResetFaults() {
		for (FaultObj fElem : DSSGlobals.getInstance().getActiveCircuit().getFaults())
			fElem.reset();
		return 0;
	}

	public static int doResetControls() {
		for (ControlElem cElem : DSSGlobals.getInstance().getActiveCircuit().getDSSControls()) {
			if (cElem.isEnabled())
				cElem.reset();
		}
		return 0;
	}

	public static int getNodeNum(int NodeRef) {
		if (NodeRef == 0) {  // TODO Check zero based indexing
			return 0;
		} else {
			return DSSGlobals.getInstance().getActiveCircuit().getMapNodeToBus()[NodeRef].nodeNum;
		}
	}

	/**
	 * Rotate a phasor by an angle and harmonic.
	 */
	public static Complex rotatePhasorDeg(Complex Phasor, double h, double AngleDeg) {
		return Phasor.multiply( ComplexUtil.polar2Complex(1.0, Math.toRadians(h * AngleDeg)) );
	}

	public static Complex rotatePhasorRad(Complex Phasor, double h, double AngleRad) {
		return Phasor.multiply( ComplexUtil.polar2Complex(1.0, h * AngleRad) );
	}

	private static double pFSign(Complex S) {
		return S.getReal() * S.getImaginary() < 0.0 ? -1.0 : 1.0;
	}

	/**
	 * Creates continous PF function from 1 to 2 where 1-2 range is leading (opposite sign).
	 */
	public static void convertComplexArrayToPowerandPF(Complex[] Buffer, int N) {
		double Mag, PF;

		/* Assume we get P + jQ */
		for (int i = 0; i < N; i++) {
			Mag = Buffer[i].abs();
			if (Mag > 0.0) {
				PF = pFSign(Buffer[i]) * Math.abs(Buffer[i].getReal()) / Mag;
				if (PF < 0.0)
					PF = 2.0 - Math.abs(PF);
			} else {
				PF = 1.0;  // for zero power
			}
			Buffer[i] = new Complex(Buffer[i].getReal(), PF);
		}
	}

	public static void convertComplexArrayToPolar(Complex[] Buffer, int N) {
		for (int i = 0; i < N; i++)
			Buffer[i] = new Complex(Buffer[i].abs(), Buffer[i].degArg());
	}

	/**
	 * Assume p is a complex array and compute the residual of the number of
	 * phases specified.
	 */
	public static Complex residual(Object p, int Nph) {
		Complex[] pc = (Complex[]) p;
		Complex Result = Complex.ZERO;
		for (int i = 0; i < Nph; i++)
			Result = Result.add(pc[i]);
		return Result;
	}

	/**
	 * Assume p is a complex array and compute the residual of the number of
	 * phases specified and convert to polar.
	 */
	public static Complex residualPolar(Object p, int Nph) {
		Complex x = residual(p, Nph);
		return new Complex(x.abs(), x.degArg());
	}

	private static double sign(double x) {
		return x < 0.0 ? -1.0 : 1.0;
	}

	public static double powerFactor(Complex S) {
		if ((S.getReal() != 0.0) && (S.getImaginary() != 0.0)) {
			return sign(S.getReal() * S.getImaginary()) * Math.abs(S.getReal()) / S.abs();
		} else {
			return 1.0;
		}
	}

	/**
	 * Convert PF from +/- 1 to 0..2 where 1..2 is leading.
	 */
	public static double convertPFToPFRange2(double value) {
		if (value < 0.0) {
			return 2.0 + value;
		} else {
			return value;
		}
	}

	public static double convertPFRange2ToPF(double value) {
		if (value > 1.0) {
			return value - 2.0;
		} else {
			return value;
		}
	}

	public static void clearEventLog() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		try {
			Globals.getEventStrings().clear();
		} catch (Exception e) {
			Globals.doSimpleMsg(String.format("Exception clearing event log: %s, @EventStrings=%p", e.getMessage(), Globals.getEventStrings()), 7151);
		}
	}

	public static void logThisEvent(String EventName) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		DSSGlobals.getInstance().getEventStrings().add(String.format("Hour=%d, Sec=%-.8g, Iteration=%d, ControlIter=%d, Event=%s",
				sol.getIntHour(), sol.getDynaVars().t, sol.getIteration(), sol.getControlIteration(), EventName));
	}

	public static void appendToEventLog(String OpDev, String action) {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		String S = String.format("Hour=%d, Sec=%-.5g, ControlIter=%d, Element=%s, Action=%s",
				sol.getIntHour(), sol.getDynaVars().t, sol.getControlIteration(), OpDev, action.toUpperCase());

		DSSGlobals.getInstance().getEventStrings().add(S);
	}

	public static void dumpComplexMatrix(PrintStream F, CMatrix aMatrix) {
		// TODO Convert to use MatrixMarket format
		try {
			if (aMatrix != null) {
				F.println("!(G matrix)");
				for (int i = 0; i < aMatrix.getNOrder(); i++) {
					F.print("! ");
					for (int j = 0; j < i; j++)
						F.printf("%.8f ", aMatrix.getElement(i, j).getReal());
					F.println();
				}
				F.println("!(B Matrix) = ");
				for (int i = 0; i < aMatrix.getNOrder(); i++) {
					F.print("! ");
					for (int j = 0; j < i; j++)
						F.printf("%.8f ", aMatrix.getElement(i, j).getImaginary());
					F.println();
				}
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Error in Dump Complex Matrix: "+e.getMessage()+"  Write aborted.", 716);
		}
	}

	/**
	 * Check all conductors of this element to see if it is closed.
	 * Make sure at least one phase on each terminal is closed.
	 */
	public static boolean allTerminalsClosed(CktElement thisElement) {
		boolean Result = false;
		for (int i = 0; i < thisElement.getNTerms(); i++) {
			Result = false;
			thisElement.setActiveTerminalIdx(i);  // TODO Check zero based indexing
			for (int j = 0; j < thisElement.getNPhases(); j++)
				if (thisElement.getConductorClosed(j)) {
					Result = true;
					break;
				}
			if (!Result)
				return Result;  // didn't find a closed phase on this terminal
		}
		return Result;
	}

	/**
	 * Special Function to write the Vsource class and change the DSS command
	 * of the first Source so that there is no problem with duplication when
	 * the circuit is subsequently created.
	 */
	public static boolean writeVsourceClassFile(DSSClass DSS_Class, boolean isCktElement) {
		PrintWriter F;
		String ClassName;
		CktElement elem;

		DSSGlobals Globals = DSSGlobals.getInstance();

		boolean Result = true;
		if (DSS_Class.getElementCount() == 0)
			return Result;

		try {
			ClassName = DSS_Class.getName();
			FileWriter FW = new FileWriter(ClassName + ".dss");
			F = new PrintWriter(FW);

			Globals.getSavedFileList().add(ClassName + ".dss");
			DSS_Class.getFirst();  // Sets ActiveDSSObject
			writeActiveDSSObject(F, "Edit");  // write first Vsource out as an edit
			while (DSS_Class.getNext() >= 0) {  // TODO Check zero based indexing
				// skip cktElements that have been checked before and written out by
				// something else
				elem = (CktElement) Globals.getActiveDSSObject();
				if (elem.isHasBeenSaved())
					continue;
				// skip disabled circuit elements; write all general DSS objects
				writeActiveDSSObject(F, "New");  // sets hasBeenSaved = true
			}
			F.close();
			DSS_Class.setSaved(true);
		} catch (Exception e) {
			Globals.doSimpleMsg("WriteClassFile error: "+e.getMessage(), 717);
			Result = false;
		}

		return Result;
	}

	public static boolean writeClassFile(DSSClass DSS_Class, String FileName, boolean isCktElement) {
		PrintWriter F;
		String ClassName;
		int nRecords;
		CktElement elem;

		DSSGlobals Globals = DSSGlobals.getInstance();

		boolean Result = true;

		if (DSS_Class.getElementCount() == 0)
			return Result;

		try {
			ClassName = DSS_Class.getName();
			if (FileName.length() == 0)
				FileName = ClassName + ".dss";  // default file name

			FileWriter FW = new FileWriter(FileName);
			F = new PrintWriter(FW);

			nRecords = 0;

			DSS_Class.getFirst();  // sets activeDSSObject

			while (DSS_Class.getNext() >= 0) {  // TODO Check zero based indexing
				// skip cktElements that have been checked before and written out by
				// something else
				if (isCktElement) {
					elem = (CktElement) Globals.getActiveDSSObject();
					if (elem.isHasBeenSaved() || (!elem.isEnabled()))
						continue;
				}

				writeActiveDSSObject(F, "New");  // sets hasBeenSaved = true
				nRecords += 1;  // count the actual records

			}
			F.close();

			if (nRecords > 0) {
				Globals.getSavedFileList().add(FileName);
			} else {
				new File(FileName).delete();
			}

			DSS_Class.setSaved(true);
		} catch (Exception e) {
			Globals.doSimpleMsg("WriteClassFile error: "+e.getMessage(), 718);
			Result = false;
		}
		return Result;
	}

	/**
	 * Checks for blanks in the name and puts quotes around it.
	 */
	public static String checkForBlanks(String S) {
		String Result = S;
		if (S.indexOf(' ') >= 0)
			if (S.charAt(0) != '(')  // ignore if already quoted
				if (S.charAt(0) != '[')  // ignore if already quoted
					if (S.charAt(0) != '{')  // ignore if already quoted
						Result = "\""+S+"\"";
		return Result;
	}

	public static void writeActiveDSSObject(PrintWriter F, String NewOrEdit) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		DSSClass ParClass = Globals.getActiveDSSObject().getParentClass();
		F.write(NewOrEdit + " \"" + ParClass.getName() + "." + Globals.getActiveDSSObject().getName() + "\"");

		Globals.getActiveDSSObject().saveWrite(F);

		// Handle disabled circuit elements; modified to allow applets to save disabled elements 12-28-06
		if ((Globals.getActiveDSSObject().getDSSObjType() & DSSClassDefs.CLASSMASK) != DSSClassDefs.DSS_OBJECT) {
			CktElement elem = (CktElement) Globals.getActiveDSSObject();
			if (!elem.isEnabled())
				F.write(" ENABLED=NO");
		}
		F.println();  // terminate line

		Globals.getActiveDSSObject().setHasBeenSaved(true);
	}

	public static void doResetKeepList() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (int i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBuses()[i].setKeep(false);
	}

	private static String extractComment(String s) {
		return s.substring(s.indexOf('!'));
	}

	public static boolean rewriteAlignedFile(String FileName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		FileInputStream fileInput;
		DataInputStream dataInput;
		BufferedReader inputReader;

		FileWriter FWout;
		PrintWriter Fout;

		String SaveDelims, Line, Field, AlignedFile = "";
		int[] FieldLength;
		int ArraySize, FieldLen = 0, FieldNum;


		boolean Result = true;

		try {
			fileInput = new FileInputStream(FileName);
			dataInput = new DataInputStream(fileInput);
			inputReader = new BufferedReader(new InputStreamReader(dataInput));
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening file: "+FileName+", "+e.getMessage(), 719);
			return false;
		}

		try {
			AlignedFile = new File(FileName).getAbsolutePath() + "Aligned_" + new File(FileName).getName();
			FWout = new FileWriter(AlignedFile);
			Fout = new PrintWriter(FWout);
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening file: "+ AlignedFile +", "+e.getMessage(), 720);

			try {
				fileInput.close();
				dataInput.close();
				inputReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}

			return false;
		}

		SaveDelims = Globals.getAuxParser().getDelimChars();
		Globals.getAuxParser().setDelimChars(",");
		ArraySize   = 10;
		FieldLength = new int[ArraySize];

		try {
			/* Scan once to set field lengths */
			while ((Line = inputReader.readLine()) != null) {
				Globals.getAuxParser().setCmdString(Line);  // load the parser
				FieldNum = 0;
				while (FieldLen > 0) {
					Globals.getAuxParser().getNextParam();
					Field = Globals.getAuxParser().makeString();
					FieldLen = Field.length();
					if (Field.indexOf(' ') >= 0)  // TODO Check zero based indexing
						FieldLen = FieldLen + 2;
					if (FieldLen > 0) {
						FieldNum += 1;
						if (FieldNum > ArraySize) {
							ArraySize = FieldNum;
							FieldLength = (int[]) resizeArray(FieldLength, ArraySize);
							FieldLength[FieldNum] = FieldLen;
						}
					} else if (FieldLen > FieldLength[FieldNum]) {
						FieldLength[FieldNum] = FieldLen;
					}
				}
			}

			/* Now go back and re-read while writing the new file */
			inputReader.reset();

			while ((Line = inputReader.readLine()) != null) {
				Globals.getAuxParser().setCmdString(Line);  // load the parser
				FieldNum = 0;
				while (FieldLen > 0) {
					Globals.getAuxParser().getNextParam();
					Field = Globals.getAuxParser().makeString();
					if (Field.indexOf(' ') >= 0)  // TODO Check zero based indexing
						Field = "\"" + Field + "\"";  // add quotes if a space in field
					FieldLen = Field.length();
					if (FieldLen > 0) {
						FieldNum += 1;
						Fout.write( pad(Field, FieldLength[FieldNum] + 1) );  // TODO Check zero based indexing
					}
				}

				if (Line.indexOf('!') > 0)
					Fout.write(extractComment(Line));

				Fout.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				fileInput.close();
				dataInput.close();
				inputReader.close();

				FWout.close();
				Fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FieldLength = null;
			Globals.getAuxParser().setDelimChars(SaveDelims);
		}

		Globals.setGlobalResult(AlignedFile);

		return Result;
	}

	public static int doExecutiveCommand(String s) {
		DSSExecutive.getInstance().setCommand(s);
		return DSSExecutive.getInstance().getErrorResult();
	}

	/**
	 * Check to see if two lines are in parallel.
	 */
	public static boolean checkParallel(CktElement Line1, CktElement Line2) {

		if (Line1.getTerminals()[0].BusRef == Line2.getTerminals()[0].BusRef)  // TODO Check zero based indexing
			if (Line1.getTerminals()[1].BusRef == Line2.getTerminals()[1].BusRef)
				return true;

		if (Line1.getTerminals()[1].BusRef == Line2.getTerminals()[0].BusRef)
			if (Line1.getTerminals()[0].BusRef == Line2.getTerminals()[1].BusRef)
				return true;

		return false;
	}

	public static double getMaxPUVoltage() {
		int nRef;
		double Result = -1.0;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			if (ckt.getBuses()[i].getKVBase() > 0.0) {
				for (int j = 0; j < ckt.getBuses()[i].getNumNodesThisBus(); j++) {
					nRef = ckt.getBuses()[i].getRef(j);
					if (nRef > 0)
						Result = Math.max(Result, ckt.getSolution().getNodeV()[nRef].abs() / ckt.getBuses()[i].getKVBase());
				}
			}
		}

		return Result * 0.001;
	}

	public static double getMinPUVoltage(boolean ignoreNeutrals) {
		int nRef;
		double VMagPU;

		double Result    = 1.0e50;  // start with big number
		boolean MinFound = false;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			Bus bus = ckt.getBuses()[i];
			if (bus.getKVBase() > 0.0)
				for (int j = 0; j < bus.getNumNodesThisBus(); j++) {
					nRef = bus.getRef(j);
					if (nRef > 0) {
						VMagPU = ckt.getSolution().getNodeV()[nRef].abs() / bus.getKVBase();
						if (ignoreNeutrals) {
							if (VMagPU > 100.0) {  // 0.1 pu
								Result   = Math.min(Result, VMagPU);  // only check buses greater than 10%
								MinFound = true;
							}
						} else {
							Result   = Math.min(Result, VMagPU);
							MinFound = true;
						}
					}
				}
		}

		Result = Result * 0.001;

		if (!MinFound)
			Result = -1.0;

		return Result;
	}

	public static Complex getTotalPowerFromSources() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		Complex Result = Complex.ZERO;

		for (CktElement pElem : ckt.getSources())
			Result = Result.add(pElem.getPower(0).negate());

		return Result;
	}


	/**
	 * Distribute the generators uniformly amongst the feeder nodes that have loads.
	 */
	private static void writeUniformGenerators(PrintWriter F, double kW, double PF) {
		LoadObj pLoad;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		DSSClass LoadClass = DSSClassDefs.getDSSClass("load");
		int Count = LoadClass.getElementList().size();

		double kWEach = kW / Math.max(1.0, Math.round(Count));
		if (ckt.isPositiveSequence())
			kWEach = kWEach / 3.0;

		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled()) {
				F.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
				F.printf(" phases=%d kV=%-g", pLoad.getNPhases(), pLoad.getkVLoadBase());
				F.printf(" kW=%-g", kWEach);
				F.printf(" PF=%-.3g", PF);
				F.print(" model=1");
				F.println();
			}
		}
	}

	/**
	 * Distribute generators randomly to loaded buses.
	 */
	private static void writeRandomGenerators(PrintWriter F, double kW, double PF) {
		LoadObj pLoad;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		DSSClass LoadClass = DSSClassDefs.getDSSClass("load");

		int Count = LoadClass.getElementList().size();

		/* Count enabled loads */
		int LoadCount = 0;
		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				LoadCount += 1;
		}

		double kWEach = kW / LoadCount;  // median sized generator
		if (ckt.isPositiveSequence())
			kWEach = kWEach / 3.0;

//		randomize;

		/* Place random sizes on load buses so that total is approximately what was spec'd */
		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled()) {
				F.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
				F.printf(" phases=%d kV=%-g", pLoad.getNPhases(), pLoad.getkVLoadBase());
				F.printf(" kW=%-g", kWEach * Math.random() * 2.0);
				F.printf(" PF=%-.3g", PF);
				F.print(" model=1");
				F.println();
			}
		}
	}

	/**
	 * Distribute generators on every other load, skipping the number specified.
	 *
	 * Distribute the generator proportional to load.
	 */
	private static void writeEveryOtherGenerators(PrintWriter F, double kW, double PF, int Skip) {
		double kWEach;
		LoadObj pLoad;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		DSSClass LoadClass = DSSClassDefs.getDSSClass("load");
		int Count = LoadClass.getElementList().size();
		/* Add up the rated load in the enabled loads where gens will be placed */
		double TotalkW = 0.0;
		int SkipCount = Skip;
		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				/* Do not count skipped loads */
				if (SkipCount == 0) {
					TotalkW = TotalkW + pLoad.getkWBase();  // will be right value if pos seq, too
					SkipCount = Skip;  // start counter over again
				} else {
					SkipCount -= 1;
				}
		}

		if (ckt.isPositiveSequence()) {
			kWEach = kW / TotalkW / 3.0;
		} else {
			kWEach = kW / TotalkW;
		}

		SkipCount = Skip;
		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				if (SkipCount == 0) {
					F.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
					F.printf(" phases=%d kV=%-g", pLoad.getNPhases(), pLoad.getkVLoadBase());
					F.printf(" kW=%-g ", kWEach * pLoad.getkWBase());
					F.printf(" PF=%-.3g", PF);
					F.print(" model=1");
					F.println();
					SkipCount = Skip;
				} else {
					SkipCount -= 1;
				}
		}
	}

	/**
	 * Distribute the generator proportional to load.
	 */
	private static void writeProportionalGenerators(PrintWriter F, double kW, double PF) {
		double kWEach;
		LoadObj pLoad;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		DSSClass LoadClass = DSSClassDefs.getDSSClass("load");
		int Count = LoadClass.getElementList().size();

		/* Add up the rated load in the enabled loads */
		double TotalkW = 0.0;
		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				TotalkW = TotalkW + pLoad.getkWBase();  // will be right value if pos seq, too
		}

		if (ckt.isPositiveSequence()) {
			kWEach = kW / TotalkW / 3.0;
		} else {
			kWEach = kW / TotalkW;
		}

		for (int i = 0; i < Count; i++) {
			pLoad = (LoadObj) LoadClass.getElementList().get(i);
			if (pLoad.isEnabled()) {
				F.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
				F.printf(" phases=%d kV=%-g", pLoad.getNPhases(), pLoad.getkVLoadBase());
				F.printf(" kW=%-g", kWEach * pLoad.getkWBase());
				F.printf(" PF=%-.3g", PF);
				F.print(" model=1");
				F.println();
			}
		}
	}

	public static void makeDistributedGenerators(double kW, double PF,
			String How, int Skip, String Fname) {

		DSSGlobals Globals = DSSGlobals.getInstance();
		FileWriter FW;
		PrintWriter F;

		/* Write outputfile and then redirect command parser to it. */

		try {
			if (new File(Fname).exists())
				Globals.doSimpleMsg("File \""+Fname+"\" is about to be overwritten. Rename it now before continuing if you wish to keep it.", 721);
			FW = new FileWriter(Fname);
			F = new PrintWriter(FW);
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening \"" + Fname + "\" for writing. Aborting.", 722);
			return;
		}

		try {
			F.println("! Created with distribute command:");
			F.println(String.format("! Distribute kW=%-.6g PF=%-.6g How=%s Skip=%d  file=%s", kW, PF, How, Skip, Fname));
			F.println();
			//F.println("Set allowduplicates=yes");
			if (How.length() == 0)
				How = "P";
			switch (How.toUpperCase().charAt(0)) {
			case 'U':
				writeUniformGenerators(F, kW, PF);
				break;
			case 'R':
				writeRandomGenerators(F, kW, PF);
				break;
			case 'S':
				writeEveryOtherGenerators(F, kW, PF, Skip);
				break;
			default:
				writeProportionalGenerators(F, kW, PF);
				break;
			}
			Globals.setGlobalResult(Fname);
		} finally {
			F.println("set allowduplicates=no");
			try {
				FW.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			F.close();
		}
	}

	/**
	 * Let EnergyMeter objects control re-enabling of feeders.
	 *
	 * Feeder could have been dumped in meantime by setting Feeder=false in EnergyMeter.
	 */
	public static void enableFeeders() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			pMeter.enableFeeder();
	}

	public static void disableFeeders() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		for (FeederObj pFeeder : ckt.getFeeders()) {
			pFeeder.setEnabled(false);
			pFeeder.setCktElementFeederFlags(false);
		}
	}

	public static void initializeFeeders() {
		// do nothing for now
	}

	public static void forwardSweepAllFeeders() {
		// do nothing for now
	}

	public static void backwardSweepAllFeeders() {
		// do nothing for now
	}

	public static String getDSSArray_Real(int n, double[] dbls) {
		String Result = "(";
		for (int i = 0; i < n; i++)
			Result = Result + String.format(" %-.5g", dbls[i]);
		return Result + ")";
	}

	public static String getDSSArray_Integer(int n, int[] ints) {
		String Result = "(";
		for (int i = 0; i < n; i++)
			Result = Result + String.format(" %-.d", ints[i]);
		return Result + ")";
	}

	/**
	 * Multiply only imaginary part by a real.
	 */
	public static Complex CmulReal_im(Complex a, double Mult) {
		return new Complex(a.getReal(), a.getImaginary() * Mult);
	}

	/**
	 * Multiply a complex array times a double.
	 */
	public static void CmulArray(Complex[] pc, double Multiplier, int size) {
		for (int i = 0; i < size; i++)
			pc[i] = pc[i].multiply(Multiplier);
	}

	public static int getMaxCktElementSize() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		int Result = 0;
		for (int i = 0; i < ckt.getNumDevices(); i++)
			Result = Math.max(Result, ckt.getCktElements().get(i).getYorder());
		return Result;
	}

	/**
	 * To help avoid collisions of neutral numbers, this function returns a
	 * node number that is not being used, starting at the startNode value.
	 */
	public static int getUniqueNodeNumber(String sBusName, int StartNode) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		int Result = StartNode;
		int iBusidx = ckt.getBusList().find(sBusName);
		if (iBusidx >= 0)  // TODO Check zero based indexing
			while (ckt.getBuses()[iBusidx].findIdx(Result) != -1)  // TODO Check zero based indexing
				Result += 1;
		ckt.getBuses()[iBusidx].add(Result);  // add it to the list so next call will be unique
		return Result;
	}


	public static void showMessageBeep(String s) {
		Toolkit.getDefaultToolkit().beep();
		DSSGlobals.getInstance().getDSSForms().infoMessageDlg(s);
	}

	public static boolean isPathBetween(PDElement FromLine, PDElement ToLine) {
		PDElement PDElem = FromLine;
		while (PDElem != null) {
			if (PDElem.equals(ToLine))
				return true;
			PDElem = PDElem.getParentPDElement();
		}
		return false;
	}

	/**
	 * Trace back up a tree and execute an edit command string.
	 */
	public static void traceAndEdit(PDElement FromLine, PDElement ToLine, String EditStr) {
		PDElement pLine = FromLine;
		while (pLine != null) {
			Parser.getInstance().setCmdString(EditStr);
			pLine.edit();  // uses parser
			if (pLine.equals(ToLine))
				break;
			pLine = pLine.getParentPDElement();
		}
	}

	/**
	 * Trace forward down a tree and generate a script file to change the phase.
	 */
	public static void goForwardAndRephase(PDElement FromLine, String PhaseString,
			String EditStr, String ScriptFileName, boolean TransStop) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		PDElement pPDelem;
		CktElement pShuntObject;
		String S;
		FileWriter FWout;
		PrintWriter Fout = null;
		String FileName = null;
		int XfmrLevel;

		EnergyMeterObj pMeter = (EnergyMeterObj) FromLine.getMeterObj();

		/* Search for starting line in branchlist */
		pPDelem = (PDElement) pMeter.getBranchList().getFirst();
		while (pPDelem != null) {
			if (FromLine.equals(pPDelem))
				break;
			pPDelem = (PDElement) pMeter.getBranchList().GoForward();
		}

		/* Error check */
		if (pPDelem == null) {
		Globals.doSimpleMsg(FromLine.getParentClass().getName() + "." + FromLine.getName() + " not found in meter zone.", 723);
		return;
		}

		try {
			FileName = Globals.getDSSDataDirectory() + Globals.getCircuitName_() + ScriptFileName;
			Globals.setGlobalResult(FileName);

			FWout = new FileWriter(FileName);
			Fout = new PrintWriter(FWout);

			int i;
			pMeter.getBranchList().startHere();
			pPDelem = (PDElement) pMeter.getBranchList().GoForward();

			while (pPDelem != null) {
				S = "edit " + pPDelem.getParentClass().getName() + "." + pPDelem.getName();

				/* ----------------LINES------------------------------------- */

				if (isLineElement(pPDelem)) {

					for (i = 0; i < pPDelem.getNTerms(); i++) {
						S = S + String.format(" Bus%d=%s%s", i, stripExtension(pPDelem.getBus(i)), PhaseString);
						//Parser.getInstance().setCmdString(String.format("Bus$d=%s%s", i, StripExtension(pPDelem.getBus(i)), PhaseString));
						//pPDelem.edit();
					}

					/* When we're done with that, we'll send the edit string */
					if (EditStr.length() > 0) {
						S = S + "  " + EditStr;
						//Parser.getInstance().setCmdString(EditStr);
						//pPDelem.edit();   // Uses Parser
					}

					Fout.println(S);

					/* Now get all shunt objects connected to this branch */
					pShuntObject = (CktElement) pMeter.getBranchList().getFirstObject();
					while (pShuntObject != null) {
						/* 1st terminal only */
						i = 0;  // TODO Check zero based indexing
						S = "edit " + pShuntObject.getParentClass().getName() + "." + pShuntObject.getName();
						S = S + String.format(" Bus%d=%s%s", i, stripExtension(pShuntObject.getBus(i)), PhaseString);
						if (EditStr.length() > 0)
							S = S + "  " + EditStr;
						Fout.println(S);
						//Parser.getInstance().setCmdString(String.format("Bus$d=%s%s", i, stripExtension(pShuntObject.getBus(0)), PhaseString));
						//pShuntObject.edit();
						pShuntObject = (CktElement) pMeter.getBranchList().getNextObject();
					}

					pPDelem = (PDElement) pMeter.getBranchList().GoForward();
				} // isLine

				/* ----------------TRANSFORMERS------------------------------ */

				else if (isTransformerElement(pPDelem)) {

					/* We'll stop at transformers and change only the primary winding.
					 * Then we'll cycle forward until the lexical level is less or we're done
					 */
					XfmrLevel = pMeter.getBranchList().getLevel();
					S = S + String.format(" wdg=1 Bus=%s%s  %s", stripExtension(pPDelem.getBus(0)), PhaseString, EditStr);
					if (!TransStop)
						S = S + String.format(" wdg=2 Bus=%s%s  %s", stripExtension(pPDelem.getBus(1)), PhaseString, EditStr);
					Fout.println(S);

					/* Be default go forward in the tree until we bounce back up to a line section above the transformer */
					if (TransStop) {
						while ((pPDelem != null) && (pMeter.getBranchList().getLevel() > XfmrLevel)) {
							pPDelem = (PDElement) pMeter.getBranchList().GoForward();
						}
					}
				} else {
					// then we get lines and loads beyond transformer
					pPDelem = (PDElement) pMeter.getBranchList().GoForward();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Fout.close();
			fireOffEditor(FileName);
		}
	}

	/**
	 * Returns max value of an array of doubles.
	 */
	public static double maxDblArrayValue(int npts, double[] dbls) {
		if (npts == 0)
			return 0.0;

		double Result = dbls[0];
		for (int i = 1; i < npts; i++)
			Result = Math.max(Result, dbls[i]);

		return Result;
	}

	/**
	 * Returns index of max array value in abs value.
	 */
	public static int iMaxAbsdblArrayValue(int npts, double[] dbls) {
		if (npts == 0)
			return -1;  // TODO Check zero based indexing

		int Result = 0;  // TODO Check zero based indexing
		double MaxValue = Math.abs(dbls[0]);
		for (int i = 1; i < npts; i++)
			if (Math.abs(dbls[i]) > MaxValue) {
				MaxValue = Math.abs(dbls[i]);
				Result = i;  // save index
			}
		return Result;
	}

	public static int interpretLoadShapeClass(String s) {
		String ss = s.toLowerCase();
		int Result = DSSGlobals.USENONE;

		switch (ss.charAt(0)) {
		case 'd':
			switch (ss.charAt(1)) {
			case 'a':
				Result = DSSGlobals.USEDAILY;
				break;
			case 'u':
				Result = DSSGlobals.USEDUTY;
				break;
			}
		case 'y':
			Result = DSSGlobals.USEYEARLY;
			break;
		case 'n':
			Result = DSSGlobals.USENONE;
			break;
		}
		return Result;
	}

	public static int interpretEarthModel(String s) {
		String ss = s.toLowerCase();
		int Result = DSSGlobals.SIMPLECARSON;
		switch (ss.charAt(0)) {
		case 'c':
			Result = DSSGlobals.SIMPLECARSON;
			break;
		case 'f':
			Result = DSSGlobals.FULLCARSON;
			break;
		case 'd':
			Result = DSSGlobals.DERI;
			break;
		}
		return Result;
	}

	public static String getActiveLoadShapeClass() {
		switch (DSSGlobals.getInstance().getActiveCircuit().getActiveLoadShapeClass()) {
		case DSSGlobals.USEDAILY:
			return "Daily";
		case DSSGlobals.USEYEARLY:
			return "Yearly";
		case DSSGlobals.USEDUTY:
			return "Duty";
		case DSSGlobals.USENONE:
			return "None";
		default:
			return "None";
		}
	}

	public static String getEarthModel(int n) {
		switch (n) {
		case DSSGlobals.SIMPLECARSON:
			return "Carson";
		case DSSGlobals.FULLCARSON:
			return "FullCarson";
		case DSSGlobals.DERI:
			return "Deri";
		default:
			return "Carson";
		}
	}

	public static int interpretColorName(String S) {

		int Result = 16711680;  // default color
		try {
			if (compareTextShortest(S, "black") == 0) {
				return 0;
			} else if (compareTextShortest(S, "Maroon") == 0) {
				return 128;
			} else if (compareTextShortest(S, "Green") == 0) {
				return 32768;
			} else if (compareTextShortest(S, "Olive") == 0) {
				return 32896;
			} else if (compareTextShortest(S, "Navy") == 0) {
				return 8388608;
			} else if (compareTextShortest(S, "Purple") == 0) {
				return 8388736;
			} else if (compareTextShortest(S, "Teal") == 0) {
				return 8421376;
			} else if (compareTextShortest(S, "Gray") == 0) {
				return 8421504;
			} else if (compareTextShortest(S, "Silver") == 0) {
				return 12632256;
			} else if (compareTextShortest(S, "Red") == 0) {
				return 255;
			} else if (compareTextShortest(S, "Lime") == 0) {
				return 65280;
			} else if (compareTextShortest(S, "Yellow") == 0) {
				return 65535;
			} else if (compareTextShortest(S, "Blue") == 0) {
				return 16711680;
			} else if (compareTextShortest(S, "Fuchsia") == 0) {
				return 16711935;
			} else if (compareTextShortest(S, "Aqua") == 0) {
				return 16776960;
			} else if (compareTextShortest(S, "LtGray") == 0){
				return 12632256;
			} else if (compareTextShortest(S, "DkGray") == 0) {
				return 8421504;
			} else if (compareTextShortest(S, "White") == 0) {
				return 16777215;
			} else {
				return Integer.parseInt(S);
			}
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("Invalid Color Specification: \"" + S + "\".", 724);
		}
		return Result;
	}

	public static Color interpretColor(String S) {
		return null;
	}

	public static String MakeNewCktElemName(final String OldName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setObject(OldName);  // set object active
		DSSObject obj = Globals.getActiveDSSObject();
	    return String.format("%s.%s%d", obj.getParentClass().getName(),
	    		obj.getParentClass().getName().substring(0, 3),
	    		obj.getClassIndex());
	}

	private static void RenameCktElem(CktElement pElem) {
		pElem.setName( String.format("%s%d",
				pElem.getParentClass().getName().substring(0, 3),
				pElem.getClassIndex()) );
		// make a new device list corresponding to the CktElements list
		DSSGlobals.getInstance().getActiveCircuit().getDeviceList().add(pElem.getName());
		pElem.setChecked(true);
	}

	/**
	 * Rename Buses and element names to generic names to remove identifiable
	 * names.
	 */
	public static void Obfuscate() {
		int i, bref;
		int dotpos;
		int DevListSize;
		HashList TempBusList;
		CktElement pCtrlElem;
		String S, Nodes;
		String OldBusName;
		String NewBusName;
		int BaseClass;
		int ElemClass;
		List<String> ControlUpDateStrings;
		List<CktElement> ControlUpDatePtrs;

		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		/* Make sure buslist exists */

		if (Globals.getActiveCircuit() == null)
			return;
		if (Globals.getActiveCircuit().getBusList().listSize() <= 0)
			return;

		Circuit ckt = Globals.getActiveCircuit();

	    TempBusList = new HashListImpl(ckt.getBusList().listSize());

	    /* Rename Buses */
	    for (i = 0; i < ckt.getBusList().listSize(); i++)
			TempBusList.add(String.format("B_%d", i));

	    ckt.setBusList(null);
	    ckt.setBusList(TempBusList);  // Reassign

	    /* Rename the bus names in each circuit element before renaming the
	     * elements */
	    for (CktElement pCktElem : ckt.getCktElements()) {
	    	BaseClass = (pCktElem.getDSSObjType() & DSSClassDefs.BASECLASSMASK);
	    	if ((BaseClass == DSSClassDefs.PC_ELEMENT) ||
	    			(BaseClass == DSSClassDefs.PD_ELEMENT)) {
	    		S = "";
	    		for (i = 0; i < pCktElem.getNTerms(); i++) {
					OldBusName = pCktElem.getBus(i);
					dotpos     = OldBusName.indexOf('.');
					if (dotpos == -1) {
						Nodes = "";
					} else {
						// preserve node designations if any
						Nodes = OldBusName.substring(dotpos);
					}
					bref  = pCktElem.getTerminals()[i].BusRef;
					NewBusName = String.format("B_%d%s", bref, Nodes);
					// Check for Transformer because that will be an exception
					switch (pCktElem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
					case DSSClassDefs.XFMR_ELEMENT:
						S = S + String.format("Wdg=%d Bus=%s ", i, NewBusName);
						break;
					default:
						S = S + String.format("Bus%d=%s ", i, NewBusName);
						break;
					}
	    		}
				parser.setCmdString(S);
				pCktElem.edit();
	    	}
	    }

	    /* Rename the circuit elements to generic values */
	    /* Have to catch the control elements and edit some of their
	     * parameters */

	    /* First, make scripts to change the monitored element names in the
	     * controls to what they will be */
	    ControlUpDateStrings = new ArrayList<String>();
	    ControlUpDatePtrs    = new ArrayList<CktElement>();

	    for (CktElement pCktElem : ckt.getCktElements()) {
	    	switch (pCktElem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
			case DSSClassDefs.CAP_CONTROL:
				S = String.format("Element=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0)));
				ControlUpDateStrings.add (S + String.format("Capacitor=%s ",
						MakeNewCktElemName("capacitor." + pCktElem.getPropertyValue(2)).substring(10, 99)));
				ControlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.REG_CONTROL:
				// handled below
				break;
			case DSSClassDefs.RELAY_CONTROL:
				S = String.format("MonitoredObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0)));
				ControlUpDateStrings.add ( S + String.format("SwitchedObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(2))));
				ControlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.RECLOSER_CONTROL:
				S = String.format("MonitoredObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0)));
				ControlUpDateStrings.add ( S + String.format("SwitchedObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(2))));
				ControlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.FUSE_CONTROL:
				S = String.format("MonitoredObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0)));
				ControlUpDateStrings.add ( S + String.format("SwitchedObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(2))));
				ControlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.GEN_CONTROL:
				ControlUpDateStrings.add (String.format("Element=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0))));
				ControlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.STORAGE_CONTROL:
				ControlUpDateStrings.add (String.format("Element=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0))));
				ControlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.SWT_CONTROL:
				ControlUpDateStrings.add (String.format("SwitchedObj=%s ",
						MakeNewCktElemName(pCktElem.getPropertyValue(0))));
				ControlUpDatePtrs.add(pCktElem);
				break;
	    	}
	    }

	    for (CktElement pCktElem : ckt.getCktElements())
			pCktElem.setChecked(false);  // initialize to not checked

	    DevListSize = ckt.getDeviceList().listSize();
	    ckt.setDeviceList(null);
	    ckt.setDeviceList( new HashListImpl(DevListSize) );

	    for (CktElement pCktElem : ckt.getCktElements()) {
			if (!pCktElem.isChecked()) {
				ElemClass = (pCktElem.getDSSObjType() & DSSClassDefs.CLASSMASK);
	            RenameCktElem(pCktElem);
	            switch (ElemClass) {
				case DSSClassDefs.XFMR_ELEMENT:
					if (pCktElem.hasControl()) {
						pCtrlElem = pCktElem.getControlElement();
	                    if (pCtrlElem != null) {
	                    	parser.setCmdString(String.format("Transformer=%s",
	                    			pCktElem.getName()));
	                    	pCtrlElem.edit();
	                    }
					}
					break;
	            default:
	    			break;
	            }
			}
	    }


	    /* Run the control update scripts now that everything is renamed */
	    CktElement pCktElem;
	    for (i = 0; i < ControlUpDatePtrs.size() - 1; i++) {
			pCktElem         = ControlUpDatePtrs.get(i);
			parser.setCmdString( ControlUpDateStrings.get(i) );
			pCktElem.edit();
	    }

	    ControlUpDateStrings = null;
	    ControlUpDatePtrs = null;
	}

}
