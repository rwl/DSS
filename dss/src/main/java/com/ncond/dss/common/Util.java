/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexUtils;

import com.ncond.dss.common.Bus.NodeBus;
import com.ncond.dss.common.Circuit.CktElementDef;
import com.ncond.dss.common.types.Algorithm;
import com.ncond.dss.common.types.AutoAddType;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.ControlMode;
import com.ncond.dss.common.types.Distribution;
import com.ncond.dss.common.types.EarthModel;
import com.ncond.dss.common.types.SolutionLoadModel;
import com.ncond.dss.common.types.Randomization;
import com.ncond.dss.common.types.SequentialTime;
import com.ncond.dss.common.types.SolutionMode;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.CapacitorObj;
import com.ncond.dss.delivery.FaultObj;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.delivery.ReactorObj;
import com.ncond.dss.delivery.Winding;
import com.ncond.dss.executive.ExecCommands;
import com.ncond.dss.executive.ExecOptions;
import com.ncond.dss.executive.Executive;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.HashList;

public class Util {

	private static final String PAD_STRING =
			"                                                  "; // 50 blanks
	private static final String PAD_DOTS_STRING =
			" ................................................."; // 50 dots

	private Util() {}

	public static String expandFileName(String child) {
		if (child.length() == 0) return "";

		if (new File(child).isAbsolute()) return child;

		try {
			return new File(DSS.currentDirectory, child).getCanonicalPath();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error encountered in Util.expandFileName(): " + e.getMessage(), -1);
		}
		return null;
	}

	public static String extractFileDir(String path) {
		return new File(path).getParentFile().getAbsolutePath();
	}

	/* Copy the contents of an array to an array of a new size. */

	public static boolean[] resizeArray(boolean[] oldArray, int newSize) {
		boolean[] newArray = new boolean[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static int[] resizeArray(int[] oldArray, int newSize) {
		int[] newArray = new int[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static double[] resizeArray(double[] oldArray, int newSize) {
		double[] newArray = new double[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static String[] resizeArray(String[] oldArray, int newSize) {
		String[] newArray = new String[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Complex[] resizeArray(Complex[] oldArray, int newSize) {
		Complex[] newArray = new Complex[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static DSSObject[] resizeArray(DSSObject[] oldArray, int newSize) {
		DSSObject[] newArray = new DSSObject[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static CktElementDef[] resizeArray(CktElementDef[] oldArray, int newSize) {
		CktElementDef[] newArray = new CktElementDef[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Bus[] resizeArray(Bus[] oldArray, int newSize) {
		Bus[] newArray = new Bus[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static NodeBus[] resizeArray(NodeBus[] oldArray, int newSize) {
		NodeBus[] newArray = new NodeBus[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Terminal[] resizeArray(Terminal[] oldArray, int newSize) {
		Terminal[] newArray = new Terminal[newSize];
		if (oldArray == null) oldArray = newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Winding[] resizeArray(Winding[] oldArray, int newSize) {
		Winding[] newArray = new Winding[newSize];
		if (oldArray == null) return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0) System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static int compareTextShortest(String s1, String s2) {
		String testStr;

		if (s1.length() < s2.length()) {
			testStr = s2.substring(0, s1.length());
			return testStr.equalsIgnoreCase(s1) ? 0 : -1;
		} else {
			testStr = s1.substring(0, s2.length());
			return testStr.equalsIgnoreCase(s2) ? 0 : -1;
		}
	}

	/**
	 * Pad out a string with blanks to width characters.
	 */
	public static String pad(String s, int width) {
		return s + PAD_STRING.substring(0, width - s.length());
	}

	/**
	 * Pad out a string with dots to width characters.
	 */
	public static String padDots(String s, int width) {
		return s + PAD_DOTS_STRING.substring(0, width - s.length());
	}

	/**
	 * Pad out a string with blanks to width characters or truncate to width chars.
	 */
	public static String padTrunc(String s, int width) {
		return pad(s, width).substring(0, width);
	}

	public static String fullName(CktElement elem) {
		return encloseQuotes(elem.getDSSClassName() + "." + elem.getName().toUpperCase());
	}

	/**
	 * Strips off everything up to a period.
	 */
	public static String stripExtension(String s) {
		int dotpos = s.indexOf('.');
		if (dotpos == -1) dotpos = s.length();
		return s.substring(0, dotpos);
	}

	/**
	 * Returns everything past the first period.
	 */
	public static String stripClassName(String s) {
		return s.substring(s.indexOf('.') + 1);
	}

	public static void fireOffEditor(String fileName) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	public static void doShellCmd(String cmdString) {
		// FIXME Implement this method (assessing security concerns)
		throw new UnsupportedOperationException();
	}

	/**
	 * Put array values in parentheses separated by commas.
	 */
	public static String intArrayToString(int[] iarray, int count) {
		if (count > 0) {
			StringBuilder sb = new StringBuilder("[");
			for (int i = 0; i < count; i++) {
				sb.append(iarray[i]);
				if (i < count - 1) sb.append(", ");
			}
			sb.append("]");
			return sb.toString();
		} else {
			return "[null]";
		}
	}

	/**
	 * Put array values in brackets separated by commas.
	 */
	public static String dblArrayToString(double[] dblarray, int count) {
		if (count > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("[%.10g", dblarray[0]));
			for (int i = 1; i < count; i++) {
				sb.append(String.format(", %.10g", dblarray[i]));
			}
			sb.append("]");
			return sb.toString();
		} else {
			return "[null]";
		}
	}

	/**
	 * Put array values in brackets separated by commas.
	 */
	public static String cmplxArrayToString(Complex[] cpxarray, int count) {
		if (count > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("[%.10g +j %.10g",
				cpxarray[0].getReal(), cpxarray[0].getImaginary()));
			for (int i = 1; i < count; i++) {
				sb.append(String.format(", %.10g +j %.10g",
					cpxarray[i].getReal(), cpxarray[i].getImaginary()));
			}
			sb.append("]");
			return sb.toString();
		} else {
			return "[null]";
		}
	}

	public static String encloseQuotes(String s) {
		return '"' + s + '"';
	}

	/**
	 * Interpret solution mode. Could be: "nominal", "daily",  "yearly",
	 *   "montecarlo", "dutycycle",  "loadduration", "peakdays", etc.
	 */
	public static SolutionMode interpretSolveMode(String s) {
		String slc = s.toLowerCase();

		switch (slc.charAt(0)) {
		case 's':
			return SolutionMode.SNAPSHOT;
		case 'd':
			switch (slc.charAt(1)) {
			case 'u':
				return SolutionMode.DUTYCYCLE;
			case 'i':
				return SolutionMode.DIRECT;
			case 'y':
				return SolutionMode.DYNAMICMODE;
			default:
				return SolutionMode.DAILYMODE;
			}
		case 'f':
			return SolutionMode.FAULTSTUDY;
		case 'h':
			return SolutionMode.HARMONICMODE;
		case 'y':
			return SolutionMode.YEARLYMODE;
		case 'm':
			switch (slc.charAt(1)) {
			case '1':
				return SolutionMode.MONTECARLO1;
			case '2':
				return SolutionMode.MONTECARLO2;
			case '3':
				return SolutionMode.MONTECARLO3;
			case 'f':
				return SolutionMode.MONTEFAULT;
			default:
				return SolutionMode.MONTECARLO1;
			}
		case 'p':
			return SolutionMode.PEAKDAY;
		case 'a':
			return SolutionMode.AUTOADDFLAG;
		case 'l':
			switch (slc.charAt(1)) {
			case 'd':
				switch (slc.charAt(2)) {
				case '1':
					return SolutionMode.LOADDURATION1;
				case '2':
					return SolutionMode.LOADDURATION2;
				default:
					return SolutionMode.LOADDURATION1;
				}
			default:
				return SolutionMode.LOADDURATION1;
			}
		case 't':
			return SolutionMode.GENERALTIME;
		default:
			return SolutionMode.SNAPSHOT;
		}
	}

	/**
	 * Interpret solution control mode.
	 */
	public static ControlMode interpretControlMode(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'o':
			return ControlMode.CONTROLSOFF;
		case 'e':
			return ControlMode.EVENTDRIVEN;  // "event"
		case 't':
			return ControlMode.TIMEDRIVEN;   // "time"
		default:
			return ControlMode.CTRLSTATIC;
		}
	}

	public static SolutionLoadModel interpretLoadModel(String s) {
		SolutionLoadModel lm;
		Circuit ckt = DSS.activeCircuit;

		switch (s.toLowerCase().charAt(0)) {
		case 'a':
			lm = SolutionLoadModel.ADMITTANCE;
			break;
		case 'p':
			lm = SolutionLoadModel.POWERFLOW;
			break;
		default:
			lm = SolutionLoadModel.ADMITTANCE;
			break;
		}

		/* If this represents a change, invalidate all the PC Yprims */
		if (lm != ckt.getSolution().getLoadModel()) {
			ckt.invalidateAllPCElements();
		}

		return lm;
	}

	/**
	 * Interpret yes/no properties - can also be true/false.
	 */
	public static boolean interpretYesNo(String s) {
		switch (s.toLowerCase().charAt(0)) {
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
	public static Randomization interpretRandom(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'g':
			return Randomization.GAUSSIAN;
		case 'u':
			return Randomization.UNIFORM;
		case 'l':
			return Randomization.LOGNORMAL;
		default:
			return Randomization.NONE;  // no variation for any other entry
		}
	}

	/**
	 * Type of device to automatically add. Default is capacitor.
	 */
	public static AutoAddType interpretAddType(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'g':
			return AutoAddType.GEN;
		default:
			return AutoAddType.CAP;
		}
	}

	/**
	 * Accepts (case insensitive)
	 *   delta or LL    Result = 1
	 *   Y, wye, or LN  Result = 0
	 */
	public static Connection interpretConnection(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'y':
			return Connection.WYE;
		case 'w':
			return Connection.WYE;
		case 'd':
			return Connection.DELTA;
		case 'l':
			switch (s.toLowerCase().charAt(1)) {
			case 'n':
				return Connection.WYE;  // LN
			case 'l':
				return Connection.DELTA;  // LL
			}
		default:
			return Connection.WYE;
		}
	}

	public static Algorithm interpretSolveAlg(String s) {
		String slc = s.toLowerCase().substring(0, 2);
		if (slc.equalsIgnoreCase("ne")) {
			return Algorithm.NEWTON;
		} else {
			return Algorithm.NORMAL;
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
	public static void initDblArray(int numValues, double[] xArray, double value) {
		for (int i = 0; i < numValues; i++) xArray[i] = value;
	}

	/**
	 * Set all elements of a integer array.
	 */
	public static void initIntArray(int numValues, int[] xArray, int value) {
		for (int i = 0; i < numValues; i++) xArray[i] = value;
	}

	/**
	 * Get numeric values from an array specified either as a list on numbers
	 * or a text file spec. ResultArray must be allocated to maxValues by
	 * calling routine. File is assumed to have one value per line.
	 */
	public static int interpretDblArray(String s, int maxValues, double[] resultArray) {
		String csvFileName;
		int csvColumn;
		boolean csvHeader;
		String inputLine;
		int iskip;
		FileReader fr;
		BufferedReader br;

		Parser parser = DSS.auxParser;

		parser.setCmdBuffer(s);
		String parmName = parser.getNextParam();
		String param = parser.makeString();
		int result = maxValues;  // default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (parmName.equalsIgnoreCase("file")) {
			csvFileName = param.equals("%%result%%") ? DSS.lastResultFile : param;

			if (!new File(csvFileName).exists()) {
				DSS.doSimpleMsg(String.format("CSV file \"%s\" does not exist", csvFileName), 70401);
				return result;
			}

			csvColumn = 0;
			csvHeader = false;

			// look for other options  (may be in either order)
			parmName = parser.getNextParam();
			param = parser.makeString();
			while (param.length() > 0) {
				if (Util.compareTextShortest(parmName, "column") == 0)
					csvColumn = parser.makeInteger();
				if (Util.compareTextShortest(parmName, "header") == 0)
					csvHeader = Util.interpretYesNo(param);
				parmName = parser.getNextParam();
				param = parser.makeString();
			}

			// load the list from a file
			try {
				fr = new FileReader(csvFileName);
				br = new BufferedReader(fr);

				if (csvHeader) br.readLine();  // skip the header row

				for (int i = 0; i < maxValues; i++) {
					try {
						if ((inputLine = br.readLine()) != null) {
							parser.setCmdBuffer(inputLine);
							for (iskip = 0; iskip < csvColumn; iskip++) {
								parmName = parser.getNextParam();
								resultArray[i] = parser.makeDouble();
							}
						} else {
							result = i;  // this will be different if less found
							break;
						}
					} catch (Exception e) {
						DSS.doSimpleMsg(String.format("Error reading %d-th numeric array value from file: \"%s\" Error is:",
								i, param, e.getMessage()), 705);
						result = i;
						break;
					}
				}
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				DSS.doSimpleMsg("Error encountered reading numeric array: " + e.getMessage(), -1);
			} catch (IOException e) {
				DSS.doSimpleMsg("Error encountered reading numeric array: " + e.getMessage(), -1);
			}
		} else if ((parmName.length() > 0) && (compareTextShortest(parmName, "dblfile") == 0)) {
			// load the list from a file of doubles (no checking done on type of data)
			throw new UnsupportedOperationException();
		} else if ((parmName.length() > 0) && (compareTextShortest(parmName, "sngfile") == 0)) {
			// load the list from a file of singles (no checking done on type of data)
			throw new UnsupportedOperationException();
		} else {
			// parse list of values off input string
			for (int i = 0; i < maxValues; i++) {
				resultArray[i] = parser.makeDouble();  // fills array with zeros if we run out of numbers
				parser.getNextParam();
			}
		}
		return result;
	}

	/**
	 * Get numeric values from an array specified either as a list on numbers
	 * or a text file spec. ResultArray must be allocated to maxValues by
	 * calling routine. File is assumed to have one value per line.
	 */
	public static int interpretIntArray(String s, int maxValues, int[] resultArray) {
		FileReader fr;
		BufferedReader br;
		String parmName, param, inputLine;

		Parser parser = DSS.auxParser;

		parser.setCmdBuffer(s);
		parmName = parser.getNextParam();
		param = parser.makeString();
		int result = maxValues;  // default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				fr = new FileReader(param);
				br = new BufferedReader(fr);

				for (int i = 0; i < maxValues; i++) {
					try {
						if ((inputLine = br.readLine()) != null) {
							resultArray[i] = Integer.parseInt(inputLine);
						} else {
							result = i;  // this will be different if less found
							break;
						}
					} catch (Exception e) {
						DSS.doSimpleMsg(String.format("Error trying to read numeric array values from file: \"" +
								param + "\"  Error is: " + e.getMessage()), 706);
						result = i;
						break;
					}
				}
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				DSS.doSimpleMsg("Error encountered reading numeric array: " + e.getMessage(), -1);
			} catch (IOException e) {
				DSS.doSimpleMsg("Error encountered reading numeric array: " + e.getMessage(), -1);
			}

		} else {  // parse list of values off input string
			for (int i = 0; i < maxValues; i++) {
				resultArray[i] = parser.makeInteger();  // fills array with zeros if we run out of numbers
				parser.getNextParam();
			}
		}
		return result;
	}

	public static int interpretIntArray(String s, int maxValues, boolean[] resultArray) {
		int[] a = new int[resultArray.length];
		int r = interpretIntArray(s, maxValues, a);
		for (int i = 0; i < a.length; i++)
			resultArray[i] = (a[i] != 0);
		return r;
	}

	/**
	 * Return stepsize in seconds.
	 */
	public static double interpretTimeStepSize(String s) {
		char ch;
		String s2;
		double stepsize;
		SolutionObj sol = DSS.activeCircuit.getSolution();

		/* Try to convert and see if we get an error */
		try {
			return Double.valueOf(s);  // only a number was specified, so must be seconds
		} catch (NumberFormatException e) {
			/* Error occurred so must have a units specifier */
			ch = s.charAt(s.length() - 1);  // get last character
			s2 = s.substring(0, s.length() - 1);
			try {
				stepsize = Double.parseDouble(s2);
				switch (ch) {
				case 'h':
					stepsize = stepsize * 3600.0;
					break;
				case 'm':
					stepsize = stepsize * 60.0;
					break;
				case 's':
					break;  // do nothing
				default:
					stepsize = sol.getDynaVars().h;  // don't change it
					DSS.doSimpleMsg("Error in specification of stepSize: \"" +
						s +"\" units can only be h, m, or s (single char only) ", 99934);
					break;
				}
			} catch (NumberFormatException ee) {
				stepsize = sol.getDynaVars().h;  // don't change it
				DSS.doSimpleMsg("Error in specification of stepSize: " + s, 99933);
				return stepsize;
			}
		}

		return stepsize;
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretAndAllocStrArray(String s, int size, String[] resultArray) {
		FileReader fr;
		BufferedReader br;
		String parmName, param;
		Parser parser = DSS.auxParser;

		int maxSize = 100;  // initialize
		size = 0;
		resultArray = new String[maxSize];  // throw away any previous allocation

		parser.setCmdBuffer(s);
		parmName = parser.getNextParam();
		param = parser.makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */
		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				fr = new FileReader(param);
				br = new BufferedReader(fr);

				while ((param = br.readLine()) != null) {
					if (param != "") {  // ignore blank lines in file
						size += 1;
						if (size > maxSize) {
							maxSize += 100;
							resultArray = resizeArray(resultArray, maxSize);
						}
						resultArray[size] = param;
					}
				}
				fr.close();
				br.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read numeric array values from a file: " +
						e.getMessage(), 707);
			}

		} else {  // parse list of values off input string
			while (param != "") {
				size += 1;
				if (size > maxSize) {
					maxSize += 100;
					resultArray = resizeArray(resultArray, maxSize);
				}
				resultArray[size] = param;
				parmName = parser.getNextParam();
				param = parser.makeString();
			}
		}

		maxSize = size;  // get rid of excess allocation
		resultArray = resizeArray(resultArray, maxSize);
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretStringListArray(String s, List<String> resultList) {
		FileReader fr;
		BufferedReader br;
		String param, paramName, nextParam;
		Parser parser = DSS.auxParser;

		// throw away any previous allocation
		resultList.clear();

		parser.setCmdBuffer(s);
		paramName = parser.getNextParam();
		param = parser.makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */

		if (paramName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				fr = new FileReader(param);
				br = new BufferedReader(fr);

				while ((param = br.readLine()) != null) {
					parser.setCmdBuffer(param);
					paramName = parser.getNextParam();
					nextParam = parser.makeString();
					if (nextParam.length() > 0)  // ignore blank lines in file
						resultList.add(nextParam);
				}
				fr.close();
				br.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read numeric array values from a file: " +
						e.getMessage(), 708);
			}
		} else {  // parse list of values off input string
			while (param != "") {
				resultList.add(param);
				paramName = parser.getNextParam();
				param = parser.makeString();
			}
		}
	}

	/**
	 * Split off obj class and name.
	 */
	public static void parseObjectClassandName(String fullObjName, String[] className, String[] objName) {
		int dotpos = fullObjName.indexOf('.');
		switch (dotpos) {
		case -1:
			// assume it is all objname; class defaults
			objName[0] = fullObjName;
			className[0] = "";
			break;
		default:
			className[0] = fullObjName.substring(0, dotpos);
			objName[0] = fullObjName.substring(dotpos + 1, fullObjName.length());
			break;
		}
	}

	public static String getSolutionModeIDName(SolutionMode idx) {

		switch (idx) {
		case SNAPSHOT:
			return "Snap";
		case DAILYMODE:
			return "Daily";
		case YEARLYMODE:
			return "Yearly";
		case MONTECARLO1:
			return "M1";
		case MONTECARLO2:
			return "M2";
		case MONTECARLO3:
			return "M3";
		case LOADDURATION1:
			return "LD1";
		case LOADDURATION2:
			return "LD2";
		case PEAKDAY:
			return "PeakDay";
		case DUTYCYCLE:
			return "DutyCycle";
		case DIRECT:
			return "Direct";
		case DYNAMICMODE:
			return "Dynamic";
		case MONTEFAULT:
			return "MF";
		case FAULTSTUDY:
			return "FaultStudy";
		case AUTOADDFLAG:
			return "AutoAdd";
		case HARMONICMODE:
			return "Harmonic";
		case GENERALTIME:
			return "Time";
		default:
			return "Unknown";
		}
	}

	public static String getSolutionModeID() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			return getSolutionModeIDName(ckt.getSolution().getMode());
		} else {
			return "Unknown";
		}
	}

	public static String getControlModeID() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			switch (ckt.getSolution().getControlMode()) {
			case CTRLSTATIC:
				return "Static";
			case EVENTDRIVEN:
				return "Event";
			case TIMEDRIVEN:
				return "Time";
			case CONTROLSOFF:
				return "Off";
			default:
				return "Unknown";
			}
		} else {
			return "Unknown";
		}
	}

	public static String getRandomModeID() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			switch (ckt.getSolution().getRandomType()) {
			case NONE:
				return "None";
			case GAUSSIAN:
				return "Gaussian";
			case UNIFORM:
				return "Uniform";
			case LOGNORMAL:
				return "LogNormal";
			default:
				return "Unknown";
			}
		} else {
			return "Unknown";
		}
	}

	public static String getLoadModel() {
		Circuit ckt = DSS.activeCircuit;

		switch (ckt.getSolution().getLoadModel()) {
		case ADMITTANCE:
			return "Admittance";
		default:
			return "PowerFlow";
		}
	}

	public static int[] parseIntArray(int[] iarray, int[] count, String s) {
		String param = " ";
		Parser parser = DSS.auxParser;

		// parse the line once to get the count of tokens on string
		parser.setCmdBuffer(s);
		count[0] = 0;
		while (param.length() > 0) {
			parser.getNextParam();
			param = parser.makeString();
			if (param.length() > 0) count[0]++;
		}

		// reallocate iarray to new size
		iarray = resizeArray(iarray, count[0]);

		// parse again for real
		parser.setCmdBuffer(s);
		for (int i = 0; i < count[0]; i++) {
			parser.getNextParam();
			iarray[i] = parser.makeInteger();
		}

		return iarray;
	}

	public static boolean isShuntElement(CktElement elem) {
		switch (elem.getObjType() & DSSClassDefs.CLASSMASK) {
		case DSSClassDefs.CAP_ELEMENT:
			return ((CapacitorObj) elem).isShunt();
		case DSSClassDefs.REACTOR_ELEMENT:
			return ((ReactorObj) elem).isShunt();
		default:
			return false;
		}
	}

	public static boolean isLineElement(CktElement elem) {
		if ((elem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LINE_ELEMENT) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTransformerElement(CktElement elem) {
		if ((elem.getObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.XFMR_ELEMENT) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isStubLine(CktElement elem) {
		double Ztest;
		LineObj lineElement = (LineObj) elem;

		/* Get positive sequence or equivalent from matrix */
		if (lineElement.isSymComponentsModel()) {
			Ztest = new Complex(lineElement.getR1(), lineElement.getX1()).abs() * lineElement.getLen();
		} else {
			/* Get impedance from Z matrix */   /* Zs - Zm */
			if (lineElement.getNumPhases() > 1) {
				Ztest = lineElement.getZ().get(0, 0).subtract(lineElement.getZ().get(0, 1)).abs() * lineElement.getLen();
			} else {
				Ztest = lineElement.getZ().get(0, 0).abs() * lineElement.getLen();
			}
		}

		 return Ztest <= DSS.activeCircuit.getReductionZmag();
	}

	/**
	 * Given the full object name, return the index to the circuit element in the
	 * active circuit.  Use full name if given, else assume last class referenced.
	 */
	public static int getCktElementIndex(String fullObjName) {
		int devClassIndex, devIndex;
		Circuit ckt = DSS.activeCircuit;
		String[] devClassName = new String[1];
		String[] devName = new String[1];

		parseObjectClassandName(fullObjName, devClassName, devName);
		devClassIndex = DSS.classNames.find(devClassName[0]);
		if (devClassIndex == -1)
			devClassIndex = DSS.lastClassReferenced;

		// since there could be devices of the same name of different classes,
		// loop until we find one of the correct class
		devIndex = ckt.getDeviceList().find(devName[0]);
		while (devIndex >= 0) {
			if (ckt.getDeviceRef()[devIndex].cktElementClass == devClassIndex)
				return devIndex;  // found a match
			devIndex = ckt.getDeviceList().findNext();
		}
		return -1;
	}

	public static String strReal(double value, int numDecimals) {
		try {
			String fmt = String.format("%%.%df", numDecimals);
			return String.format(fmt, value);
		} catch (Exception e) {
			return "*****";
		}
	}

	public static void dumpAllocationFactors(String fileName) {
		FileWriter fw;
		PrintWriter pw;

		try {
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			for (LoadObj load : DSS.activeCircuit.getLoads()) {
				switch (load.getLoadSpecType()) {
				case XFKVA_ALLOCATIONFACTOR_PF:
					pw.println("Load." + load.getName() + ".allocationFactor=" +
						String.format("%-.5g", load.getKVAAllocationFactor()));
					break;
				case KWH_KWHDAYS24_CFACTOR_PF:
					pw.println("Load." + load.getName() + ".CFactor=" +
						String.format("%-.5g", load.getCFactor()));
					break;
				}
			}
			pw.close();
			fw.close();

			DSS.globalResult = fileName;
		} catch (IOException e) {
			DSS.doErrorMsg("Error opening " + fileName + " for writing.",
				e.getMessage(), " File protected or other file error.", 709);
		}
	}

	public static void dumpAllDSSCommands(String fileName) {
		FileWriter fw;
		PrintWriter pw;

		try {
			fileName = DSS.dataDirectory + "AllocationFactors.txt";
			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			// dump executive commands
			pw.println("[execCommands]");
			for (int i = 0; i < ExecCommands.NumExecCommands; i++)
				pw.println((i+1) +" + \"" + ExecCommands.execCommand[i] +
					"\" \"" + ExecCommands.commandHelp[i] + "\"");

			// dump executive options
			pw.println("[execOptions]");
			for (int i = 0; i < ExecOptions.NumExecOptions; i++)
				pw.println((i+1) + ", \"" + ExecOptions.execOption[i] +
					"\", \"" + ExecOptions.optionHelp[i] + "\"");

			// dump all present DSSClasses
			for (DSSClass cls : DSS.DSSClassList) {
				pw.println("[" + cls.getClassName() + "]");
				for (int i = 0; i < cls.getNumProperties(); i++) {
					pw.println((i+1) + ", \"" + cls.getPropertyName(i) +
						"\" \"" + cls.getPropertyHelp()[i] + "\"");
				}
			}

			pw.close();
		} catch (IOException e) {
			DSS.doErrorMsg("Error opening " + fileName + " for writing.", e.getMessage(),
					"Disk protected or other file error", 710);
		}
	}

	/**
	 * Find closest base voltage.
	 */
	public static double nearestBasekV(double kV) {
		int count;
		double diff;
		Circuit ckt = DSS.activeCircuit;

		count = 0;
		double testKV = ckt.getLegalVoltageBase(count);
		double result = testKV;
		double minDiff = 1.e50;  // big number

		while (testKV != 0.0) {
			diff = Math.abs(1.0 - kV / testKV);  // get per unit difference
			if (diff < minDiff) {
				minDiff = diff;
				result = testKV;
			}
			count += 1;
			testKV = ckt.getLegalVoltageBase(count);
		}
		return result;
	}

	public static boolean savePresentVoltages() {
		FileWriter fw;
		PrintWriter pw;

		try {
			fw = new FileWriter(DSS.dataDirectory + DSS.circuitName_ + "SavedVoltages.dbl");
			pw = new PrintWriter(fw);

			Circuit ckt = DSS.activeCircuit;
			SolutionObj sol = ckt.getSolution();

			pw.printf("%.d", ckt.getNumNodes());
			for (int i = 1; i <= ckt.getNumNodes(); i++) {
				pw.printf(" %.5f %.5f", sol.getNodeV(i).getReal(), sol.getNodeV(i).getImaginary());
			}

			pw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error saving present voltages: " + e.getMessage(), 712);
			return false;
		}
		return true;
	}

	public static boolean retrieveSavedVoltages() {
		FileReader fr;
		BufferedReader br;
		double dNumNodes;
		String inputLine, parts[];

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		try {
			fr = new FileReader(DSS.dataDirectory + DSS.circuitName_ + "SavedVoltages.dbl");
			br = new BufferedReader(fr);

			dNumNodes = Double.parseDouble(br.readLine());
			if (ckt.getNumNodes() == Math.round(dNumNodes)) {
				for (int i = 0; i < ckt.getNumNodes(); i++) {
					inputLine = br.readLine();
					parts = inputLine.split("\\s");
					sol.getNodeV()[i] = new Complex(
						Double.parseDouble(parts[0]),
						Double.parseDouble(parts[1])
					);
				}
			} else {
				DSS.doSimpleMsg("Saved results do not match present circuit. Aborting.", 714);
				return false;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error saving present voltages: " + e.getMessage(), 712);
			return false;
		}
		return true;
	}

	/**
	 * Intialize PC element base values for harmonics analysis.
	 */
	public static boolean initializeForHarmonics() {
		if (savePresentVoltages()) {  // save voltage vector to disk
			for (PCElement elem : DSS.activeCircuit.getPCElements())
				elem.initHarmonics();
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
		for (PCElement elem : DSS.activeCircuit.getPCElements()) {
			if (elem.isEnabled()) elem.initStateVars();
		}
	}

	/**
	 * For now, just does generators.
	 */
	public static void invalidateAllPCElements() {
		for (PCElement elem : DSS.activeCircuit.getPCElements())
			if (elem.isEnabled()) elem.setYPrimInvalid(true);
	}

	public static double presentTimeInSec() {
		SolutionObj sol = DSS.activeCircuit.getSolution();
		return sol.getDynaVars().t + sol.getIntHour() * 3600.0;
	}

	public static int doResetFaults() {
		for (FaultObj elem : DSS.activeCircuit.getFaults()) elem.reset();
		return 0;
	}

	public static int doResetControls() {
		for (ControlElem elem : DSS.activeCircuit.getControls()) {
			if (elem.isEnabled()) elem.reset();
		}
		return 0;
	}

	/**
	 *
	 * @param nodeRef one based node reference
	 * @return one based node number
	 */
	public static int getNodeNum(int nodeRef) {
		if (nodeRef == 0) {
			return 0;
		} else {
			return DSS.activeCircuit.getMapNodeToBus(nodeRef).nodeNum;
		}
	}

	/**
	 * Rotate a phasor by an angle and harmonic.
	 */
	public static Complex rotatePhasorDeg(Complex phasor, double h, double angleDeg) {
		return phasor.multiply(ComplexUtils.polar2Complex(1.0, Math.toRadians(h * angleDeg)));
	}

	public static Complex rotatePhasorRad(Complex phasor, double h, double angleRad) {
		return phasor.multiply(ComplexUtils.polar2Complex(1.0, h * angleRad));
	}

	private static double pfSign(Complex S) {
		return S.getReal() * S.getImaginary() < 0.0 ? -1.0 : 1.0;
	}

	/**
	 * Creates continous PF function from 1 to 2 where 1-2 range is leading (opposite sign).
	 */
	public static void convertComplexArrayToPowerandPF(Complex[] buffer, int n) {
		double mag, pf;

		/* Assume we get P + jQ */
		for (int i = 0; i < n; i++) {
			mag = buffer[i].abs();
			if (mag > 0.0) {
				pf = pfSign(buffer[i]) * Math.abs(buffer[i].getReal()) / mag;
				if (pf < 0.0) pf = 2.0 - Math.abs(pf);
			} else {
				pf = 1.0;  // for zero power
			}
			buffer[i] = new Complex(buffer[i].getReal(), pf);
		}
	}

	public static void convertComplexArrayToPolar(Complex[] buffer, int n) {
		for (int i = 0; i < n; i++) {
			buffer[i] = new Complex(
				buffer[i].abs(),
				ComplexUtil.degArg(buffer[i])
			);
		}
	}

	/**
	 * Assume p is a complex array and compute the residual of the number of
	 * phases specified.
	 */
	public static Complex residual(Object p, int nph) {
		Complex[] pc = (Complex[]) p;
		Complex resid = Complex.ZERO;
		for (int i = 0; i < nph; i++)
			resid = resid.add(pc[i]);
		return resid;
	}

	/**
	 * Assume p is a complex array and compute the residual of the number of
	 * phases specified and convert to polar.
	 */
	public static Complex residualPolar(Object p, int nph) {
		Complex x = residual(p, nph);
		return new Complex(x.abs(), ComplexUtil.degArg(x));
	}

	private static double sign(double x) {
		return x < 0.0 ? -1.0 : 1.0;
	}

	public static double powerFactor(Complex S) {
		if (S.getReal() != 0.0 && S.getImaginary() != 0.0) {
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
		try {
			DSS.eventStrings.clear();
		} catch (Exception e) {
			DSS.doSimpleMsg(String.format("Exception clearing event log: %s, eventStrings=%s",
					e.getMessage(), DSS.eventStrings.toString()), 7151);
		}
	}

	public static void logThisEvent(String eventName) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		DSS.eventStrings.add(String.format("Hour=%d, Sec=%-.8g, Iteration=%d, ControlIter=%d, Event=%s",
				sol.getIntHour(), sol.getDynaVars().t, sol.getIteration(), sol.getControlIteration(), eventName));
	}

	public static void appendToEventLog(String opDev, String action) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		String S = String.format("Hour=%d, Sec=%-.5g, ControlIter=%d, Element=%s, Action=%s",
				sol.getIntHour(), sol.getDynaVars().t, sol.getControlIteration(), opDev, action.toUpperCase());

		DSS.eventStrings.add(S);
	}

	public static void dumpComplexMatrix(OutputStream out, CMatrix a) {
		PrintWriter pw = new PrintWriter(out);
		if (a != null) {
			pw.println("!(G matrix)");
			for (int i = 0; i < a.order(); i++) {
				pw.print("! ");
				for (int j = 0; j < i; j++)
					pw.printf("%.8f ", a.get(i, j).getReal());
				pw.println();
			}
			pw.println("!(B Matrix) = ");
			for (int i = 0; i < a.order(); i++) {
				pw.print("! ");
				for (int j = 0; j < i; j++)
					pw.printf("%.8f ", a.get(i, j).getImaginary());
				pw.println();
			}
		}
	}

	/**
	 * Check all conductors of this element to see if it is closed.
	 * Make sure at least one phase on each terminal is closed.
	 */
	public static boolean allTerminalsClosed(CktElement thisElement) {
		boolean closed = false;
		for (int i = 0; i < thisElement.getNumTerms(); i++) {
			closed = false;
			thisElement.setActiveTerminalIdx(i);
			for (int j = 0; j < thisElement.getNumPhases(); j++)
				if (thisElement.isConductorClosed(j)) {
					closed = true;
					break;
				}
			if (!closed) return closed;  // didn't find a closed phase on this terminal
		}
		return closed;
	}

	/**
	 * Special function to write the VSource class and change the DSS command
	 * of the first source so that there is no problem with duplication when
	 * the circuit is subsequently created.
	 */
	public static boolean writeVSourceClassFile(DSSClass cls, boolean isCktElement) {
		PrintWriter pw;
		FileWriter fw;
		String clsName;
		CktElement elem;

		boolean success = true;
		if (cls.getElementCount() == 0) return success;

		try {
			clsName = cls.getClassName();
			fw = new FileWriter(clsName + ".dss");
			pw = new PrintWriter(fw);

			DSS.savedFileList.add(clsName + ".dss");
			cls.getFirst();  // sets activeDSSObject
			writeActiveDSSObject(pw, "edit");  // write first Vsource out as an edit
			while (cls.getNext() >= 0) {
				// skip cktElements that have been checked before and written
				// out by something else
				elem = (CktElement) DSS.activeDSSObject;
				if (elem.isHasBeenSaved()) continue;
				// skip disabled circuit elements; write all general DSS objects
				writeActiveDSSObject(pw, "new");  // sets hasBeenSaved=true
			}
			pw.close();
			cls.setSaved(true);
		} catch (IOException e) {
			DSS.doSimpleMsg("writeClassFile() error: " + e.getMessage(), 717);
			success = false;
		}

		return success;
	}

	public static boolean writeClassFile(DSSClass cls, String fileName, boolean isCktElement) {
		FileWriter fw;
		PrintWriter pw;
		String clsName;
		int nRecords;
		CktElement elem;

		boolean success = true;

		if (cls.getElementCount() == 0)
			return success;

		try {
			clsName = cls.getClassName();
			if (fileName.length() == 0)
				fileName = clsName + ".dss";  // default file name

			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			nRecords = 0;

			cls.getFirst();  // sets activeDSSObject

			while (cls.getNext() >= 0) {
				// skip cktElements that have been checked before and written
				// out by something else
				if (isCktElement) {
					elem = (CktElement) DSS.activeDSSObject;
					if (elem.isHasBeenSaved() || (!elem.isEnabled()))
						continue;
				}
				writeActiveDSSObject(pw, "new");  // sets hasBeenSaved = true
				nRecords += 1;  // count the actual records
			}
			pw.close();

			if (nRecords > 0) {
				DSS.savedFileList.add(fileName);
			} else {
				new File(fileName).delete();
			}

			cls.setSaved(true);
		} catch (IOException e) {
			DSS.doSimpleMsg("writeClassFile() error: " + e.getMessage(), 718);
			success = false;
		}
		return success;
	}

	/**
	 * Checks for blanks in the name and puts quotes around it.
	 */
	public static String checkForBlanks(String s) {
		String ss = s;
		if (s.indexOf(' ') >= 0) {
			if (s.charAt(0) != '(') {  // ignore if already quoted
				if (s.charAt(0) != '[') {  // ignore if already quoted
					if (s.charAt(0) != '{')  // ignore if already quoted
						ss = "\"" + s + "\"";
				}
			}
		}
		return ss;
	}

	public static void writeActiveDSSObject(PrintWriter pw, String newOrEdit) {
		DSSObject active = DSS.activeDSSObject;
		DSSClass parClass = active.getParentClass();

		pw.write(newOrEdit + " \"" + parClass.getClassName() + "." + active.getName() + "\"");

		active.saveWrite(pw);

		// handle disabled circuit elements
		if ((active.getObjType() & DSSClassDefs.CLASSMASK) != DSSClassDefs.DSS_OBJECT) {
			CktElement elem = (CktElement) active;
			if (!elem.isEnabled()) pw.write(" enabled=no");
		}
		pw.println();

		active.setHasBeenSaved(true);
	}

	public static void doResetKeepList() {
		Circuit ckt = DSS.activeCircuit;
		for (int i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBus(i).setKeep(false);
	}

	private static String extractComment(String s) {
		return s.substring(s.indexOf('!') + 1);
	}

	public static boolean rewriteAlignedFile(String fileName) {
		FileReader fr;
		BufferedReader br;
		FileWriter fw;
		PrintWriter pw;

		String saveDelims, line, field, alignedFile = "";
		int[] fieldLength;
		int arraySize, fieldLen = 0, fieldNum;
		boolean success = true;
		Parser parser = DSS.auxParser;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		} catch (IOException e) {
			DSS.doSimpleMsg("Error opening file " + fileName + ": " + e.getMessage(), 719);
			return false;
		}

		try {
			alignedFile = new File(fileName).getAbsolutePath() +
				"Aligned_" + new File(fileName).getName();
			fw = new FileWriter(alignedFile);
			pw = new PrintWriter(fw);
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening file "+ alignedFile + ": " + e.getMessage(), 720);
			try {
				fr.close();
				br.close();
			} catch (IOException e1) {
				DSS.doSimpleMsg("Error closing file " + fileName + ": " + e.getMessage(), 721);
			}
			return false;
		}

		saveDelims = parser.getDelimChars();
		parser.setDelimChars(",");
		arraySize = 10;
		fieldLength = new int[arraySize];

		try {
			/* Scan once to set field lengths */
			while ((line = br.readLine()) != null) {
				parser.setCmdBuffer(line);  // load the parser
				fieldNum = 0;
				while (fieldLen > 0) {
					parser.getNextParam();
					field = parser.makeString();
					fieldLen = field.length();
					if (field.indexOf(' ') >= 0)
						fieldLen = fieldLen + 2;
					if (fieldLen > 0) {
						fieldNum += 1;
						if (fieldNum > arraySize) {
							arraySize = fieldNum;
							fieldLength = resizeArray(fieldLength, arraySize);
							fieldLength[fieldNum] = fieldLen;
						}
					} else if (fieldLen > fieldLength[fieldNum]) {
						fieldLength[fieldNum] = fieldLen;
					}
				}
			}

			/* Now go back and re-read while writing the new file */
			br.reset();

			while ((line = br.readLine()) != null) {
				parser.setCmdBuffer(line);  // load the parser
				fieldNum = 0;
				while (fieldLen > 0) {
					parser.getNextParam();
					field = parser.makeString();
					if (field.indexOf(' ') >= 0)
						field = "\"" + field + "\"";  // add quotes if a space in field
					fieldLen = field.length();
					if (fieldLen > 0) {
						fieldNum += 1;
						pw.write(pad(field, fieldLength[fieldNum] + 1));
					}
				}
				if (line.indexOf('!') >= 0)
					pw.write(extractComment(line));
				pw.println();
			}
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing aligned file: " + e.getMessage(), 722);
		} finally {
			try {
				fr.close();
				br.close();
				fw.close();
				pw.close();
			} catch (IOException e) {
				DSS.doSimpleMsg("Error closing file: " + e.getMessage(), 723);
			}
			fieldLength = null;
			parser.setDelimChars(saveDelims);
		}
		DSS.globalResult = alignedFile;

		return success;
	}

	public static int doExecutiveCommand(String s) {
		Executive.getInstance().setCommand(s);
		return Executive.getInstance().getErrorResult();
	}

	/**
	 * Check to see if two lines are in parallel.
	 */
	public static boolean checkParallel(CktElement line1, CktElement line2) {

		if (line1.getTerminal(0).getBusRef() == line2.getTerminal(0).getBusRef())
			if (line1.getTerminal(1).getBusRef() == line2.getTerminal(1).getBusRef())
				return true;

		if (line1.getTerminal(1).getBusRef() == line2.getTerminal(0).getBusRef())
			if (line1.getTerminal(0).getBusRef() == line2.getTerminal(1).getBusRef())
				return true;

		return false;
	}

	public static double getMaxPUVoltage() {
		int i, j, ref;
		double max = -1.0;
		Bus bus;
		Circuit ckt = DSS.activeCircuit;

		for (i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			if (bus.getKVBase() > 0.0) {
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					ref = bus.getRef(j);
					if (ref > 0)
						max = Math.max(max, ckt.getSolution().getNodeV(ref).abs() / bus.getKVBase());
				}
			}
		}

		return max * 0.001;
	}

	public static double getMinPUVoltage(boolean ignoreNeutrals) {
		int ref;
		double VMagPU;

		double min = 1.0e50;  // start with big number
		boolean minFound = false;

		Circuit ckt = DSS.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			Bus bus = ckt.getBus(i);
			if (bus.getKVBase() > 0.0) {
				for (int j = 0; j < bus.getNumNodesThisBus(); j++) {
					ref = bus.getRef(j);
					if (ref > 0) {
						VMagPU = ckt.getSolution().getNodeV(ref).abs() / bus.getKVBase();
						if (ignoreNeutrals) {
							if (VMagPU > 100.0) {  // 0.1 pu
								min = Math.min(min, VMagPU);  // only check buses greater than 10%
								minFound = true;
							}
						} else {
							min = Math.min(min, VMagPU);
							minFound = true;
						}
					}
				}
			}
		}

		return minFound ? min * 0.001 : -1.0;
	}

	public static Complex getTotalPowerFromSources() {
		Circuit ckt = DSS.activeCircuit;

		Complex tot = Complex.ZERO;
		for (CktElement elem : ckt.getSources())
			tot = tot.add(elem.getPower(0).negate());

		return tot;
	}


	/**
	 * Distribute the generators uniformly amongst the feeder nodes that have loads.
	 */
	private static void writeUniformGenerators(PrintWriter pw, double kW, double pf) {
		int count;
		double kWEach;
		LoadObj load;
		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");
		count = loadClass.getElementList().size();

		kWEach = kW / Math.max(1.0, Math.round(count));
		if (ckt.isPositiveSequence())
			kWEach = kWEach / 3.0;

		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled()) {
				pw.printf("new generator.DG_%d  bus1=%s", i, load.getBus(0));
				pw.printf(" phases=%d kV=%g", load.getNumPhases(), load.getKVLoadBase());
				pw.printf(" kW=%g", kWEach);
				pw.printf(" pf=%-.3g", pf);
				pw.print(" model=1");
				pw.println();
			}
		}
	}

	/**
	 * Distribute generators randomly to loaded buses.
	 */
	private static void writeRandomGenerators(PrintWriter pw, double kW, double pf) {
		int count, loadCount;
		double kWEach;
		LoadObj load;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");

		count = loadClass.getElementList().size();

		/* Count enabled loads */
		loadCount = 0;
		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled()) loadCount += 1;
		}

		kWEach = kW / loadCount;  // median sized generator
		if (ckt.isPositiveSequence()) kWEach = kWEach / 3.0;

		/* Place random sizes on load buses so that total is approximately what was spec'd */
		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled()) {
				pw.printf("new generator.DG_%d  bus1=%s", i, load.getBus(0));
				pw.printf(" phases=%d kV=%g", load.getNumPhases(), load.getKVLoadBase());
				pw.printf(" kW=%g", kWEach * Math.random() * 2.0);
				pw.printf(" pf=%-.3g", pf);
				pw.print(" model=1");
				pw.println();
			}
		}
	}

	/**
	 * Distribute generators on every other load, skipping the number specified.
	 *
	 * Distribute the generator proportional to load.
	 */
	private static void writeEveryOtherGenerators(PrintWriter pw, double kW, double pf, int skip) {
		int count, skipCount;
		double kWEach, totalKW;
		LoadObj load;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");
		count = loadClass.getElementList().size();
		/* Add up the rated load in the enabled loads where gens will be placed */
		totalKW = 0.0;
		skipCount = skip;
		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled()) {
				/* Do not count skipped loads */
				if (skipCount == 0) {
					totalKW = totalKW + load.getKWBase();  // will be right value if pos seq, too
					skipCount = skip;  // start counter over again
				} else {
					skipCount -= 1;
				}
			}
		}

		if (ckt.isPositiveSequence()) {
			kWEach = kW / totalKW / 3.0;
		} else {
			kWEach = kW / totalKW;
		}

		skipCount = skip;
		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled()) {
				if (skipCount == 0) {
					pw.printf("new generator.DG_%d  bus1=%s", i, load.getBus(0));
					pw.printf(" phases=%d kV=%g", load.getNumPhases(), load.getKVLoadBase());
					pw.printf(" kW=%g ", kWEach * load.getKWBase());
					pw.printf(" pf=%-.3g", pf);
					pw.print(" model=1");
					pw.println();
					skipCount = skip;
				} else {
					skipCount -= 1;
				}
			}
		}
	}

	/**
	 * Distribute the generator proportional to load.
	 */
	private static void writeProportionalGenerators(PrintWriter pw, double kW, double pf) {
		int count;
		double kWEach, totalKW;
		LoadObj load;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");
		count = loadClass.getElementList().size();

		/* Add up the rated load in the enabled loads */
		totalKW = 0.0;
		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled())
				totalKW = totalKW + load.getKWBase();  // will be right value if pos seq, too
		}

		if (ckt.isPositiveSequence()) {
			kWEach = kW / totalKW / 3.0;
		} else {
			kWEach = kW / totalKW;
		}

		for (int i = 0; i < count; i++) {
			load = (LoadObj) loadClass.getElementList().get(i);
			if (load.isEnabled()) {
				pw.printf("new generator.DG_%d  bus1=%s", i, load.getBus(0));
				pw.printf(" phases=%d kV=%g", load.getNumPhases(), load.getKVLoadBase());
				pw.printf(" kW=%g", kWEach * load.getKWBase());
				pw.printf(" pf=%-.3g", pf);
				pw.print(" model=1");
				pw.println();
			}
		}
	}

	public static void makeDistributedGenerators(double kW, double pf,
			Distribution how, int skip, String fname) {
		FileWriter fw;
		PrintWriter pw;

		/* Write output file and then redirect command parser to it. */

		try {
			if (new File(fname).exists())
				DSS.doSimpleMsg("File \"" + fname + "\" is will be overwritten.", 721);

			fw = new FileWriter(fname);
			pw = new PrintWriter(fw);

			pw.println("! Created with distribute command:");
			pw.println(String.format("! distribute kW=%-.6g pf=%-.6g how=%s skip=%d file=%s", kW, pf, how, skip, fname));
			pw.println();
			//pw.println("set allowduplicates=yes");
			if (how == null) how = Distribution.PROPORTIONAL;

			switch (how) {
			case UNIFORM:
				writeUniformGenerators(pw, kW, pf);
				break;
			case RANDOM:
				writeRandomGenerators(pw, kW, pf);
				break;
			case SKIP:
				writeEveryOtherGenerators(pw, kW, pf, skip);
				break;
			default:
				writeProportionalGenerators(pw, kW, pf);
				break;
			}
			DSS.globalResult = fname;

			//pw.println("set allowduplicates=no");
			pw.close();
			fw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error opening \"" + fname + "\" for writing. Aborting.", 722);
			return;
		}
	}

	/**
	 * Let EnergyMeter objects control re-enabling of feeders.
	 *
	 * Feeder could have been dumped in meantime by setting Feeder=false in EnergyMeter.
	 */
	public static void enableFeeders() {
		for (EnergyMeterObj meter : DSS.activeCircuit.getEnergyMeters())
			meter.enableFeeder();
	}

	public static void disableFeeders() {
		for (FeederObj feeder : DSS.activeCircuit.getFeeders()) {
			feeder.setEnabled(false);
			feeder.setCktElementFeederFlags(false);
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

	public static String getDSSArray(int n, double[] dbls) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < n; i++)
			sb.append(String.format(" %-.5g", dbls[i]));
		sb.append(")");
		return sb.toString();
	}

	public static String getDSSArray(int n, int[] ints) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < n; i++)
			sb.append(String.format(" %-.d", ints[i]));
		sb.append(")");
		return sb.toString();
	}

	public static String getDSSArray(int n, boolean[] a) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < n; i++)
			sb.append(String.format(" %-.d", a[i] ? 1 : 0));
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Multiply only imaginary part by a real.
	 */
	public static Complex mulRealImag(Complex a, double mult) {
		return new Complex(a.getReal(), a.getImaginary() * mult);
	}

	/**
	 * Multiply a complex array times a double.
	 */
	public static void mulArray(Complex[] pc, double multiplier, int size) {
		for (int i = 0; i < size; i++)
			pc[i] = pc[i].multiply(multiplier);
	}

	public static int getMaxCktElementSize() {
		Circuit ckt = DSS.activeCircuit;
		int size = 0;
		for (int i = 0; i < ckt.getNumDevices(); i++)
			size = Math.max(size, ckt.getCktElements().get(i).getYOrder());
		return size;
	}

	/**
	 * To help avoid collisions of neutral numbers, this function returns a
	 * node number that is not being used, starting at the startNode value.
	 */
	public static int getUniqueNodeNumber(String busName, int startNode) {
		Circuit ckt = DSS.activeCircuit;

		int unique = startNode;
		int busIdx = ckt.getBusList().find(busName);
		if (busIdx >= 0) {
			while (ckt.getBus(busIdx).findIdx(unique) != -1)
				unique += 1;
		}
		ckt.getBus(busIdx).add(unique);  // add it to the list so next call will be unique
		return unique;
	}

	public static void showMessageBeep(String s) {
		Toolkit.getDefaultToolkit().beep();
		DSS.forms.infoMessageDlg(s);
	}

	public static boolean isPathBetween(PDElement fromLine, PDElement toLine) {
		PDElement elem = fromLine;
		while (elem != null) {
			if (elem.equals(toLine))
				return true;
			elem = elem.getParentPDElement();
		}
		return false;
	}

	/**
	 * Trace back up a tree and execute an edit command string.
	 */
	public static void traceAndEdit(PDElement fromLine, PDElement toLine, int nPhases, String editStr) {
		PDElement line = fromLine;
		while (line != null) {
			if ((line.getNumPhases() == nPhases) || (nPhases == 0)) {
				Parser.getInstance().setCmdBuffer(editStr);
				line.edit();  // uses parser
			}
			if (line.equals(toLine))
				break;
			line = line.getParentPDElement();
		}
	}

	/**
	 * Trace forward down a tree and generate a script file to change the phase.
	 */
	public static void goForwardAndRephase(PDElement fromLine, String phaseString,
			String editStr, String scriptFileName, boolean transStop) {

		PDElement elem;
		CktElement shunt;
		String s;
		FileWriter fw;
		PrintWriter pw = null;
		String fileName = null;
		int xfmrLevel;

		EnergyMeterObj meter = (EnergyMeterObj) fromLine.getMeterObj();

		/* Search for starting line in branchlist */
		elem = (PDElement) meter.getBranchList().getFirst();
		while (elem != null) {
			if (fromLine.equals(elem))
				break;
			elem = (PDElement) meter.getBranchList().goForward();
		}

		/* Error check */
		if (elem == null) {
			DSS.doSimpleMsg(fromLine.getParentClass().getClassName() + "." +
				fromLine.getName() + " not found in meter zone.", 723);
			return;
		}

		try {
			fileName = DSS.dataDirectory + DSS.circuitName_ + scriptFileName;
			DSS.globalResult = fileName;

			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			int i;
			meter.getBranchList().startHere();
			elem = (PDElement) meter.getBranchList().goForward();

			while (elem != null) {
				s = "edit " + elem.getParentClass().getClassName() + "." + elem.getName();

				/* Lines */
				if (isLineElement(elem)) {
					for (i = 0; i < elem.getNumTerms(); i++) {
						s = s + String.format(" bus%d=%s%s", i+1, stripExtension(elem.getBus(i)), phaseString);
						//Parser.getInstance().setCmdString(String.format("bus$d=%s%s", i+1, stripExtension(pPDelem.getBus(i)), phaseString));
						//elem.edit();
					}

					/* When we're done with that, we'll send the edit string */
					if (editStr.length() > 0) {
						s = s + "  " + editStr;
						//Parser.getInstance().setCmdString(editStr);
						//elem.edit();  // uses parser
					}

					pw.println(s);

					/* Now get all shunt objects connected to this branch */
					shunt = (CktElement) meter.getBranchList().getFirstObject();
					while (shunt != null) {
						/* 1st terminal only */
						i = 0;
						s = "edit " + shunt.getParentClass().getClassName() + "." + shunt.getName();
						s = s + String.format(" bus%d=%s%s", i+1, stripExtension(shunt.getBus(i)), phaseString);
						if (editStr.length() > 0)
							s = s + "  " + editStr;
						pw.println(s);
						//Parser.getInstance().setCmdString(String.format("bus$d=%s%s", i+1, stripExtension(pShuntObject.getBus(0)), phaseString));
						//shunt.edit();
						shunt = (CktElement) meter.getBranchList().getNextObject();
					}

					elem = (PDElement) meter.getBranchList().goForward();
				}

				/* Transformers */
				else if (isTransformerElement(elem)) {

					/* We'll stop at transformers and change only the primary winding.
					 * Then we'll cycle forward until the lexical level is less or we're done
					 */
					xfmrLevel = meter.getBranchList().getLevel();
					s = s + String.format(" wdg=1 bus=%s%s  %s", stripExtension(elem.getBus(0)), phaseString, editStr);
					if (!transStop)
						s = s + String.format(" wdg=2 bus=%s%s  %s", stripExtension(elem.getBus(1)), phaseString, editStr);
					pw.println(s);

					/* Be default go forward in the tree until we bounce back up to a line section above the transformer */
					if (transStop) {
						while ((elem != null) && (meter.getBranchList().getLevel() > xfmrLevel)) {
							elem = (PDElement) meter.getBranchList().goForward();
						}
					}
				} else {
					// then we get lines and loads beyond transformer
					elem = (PDElement) meter.getBranchList().goForward();
				}
			}
			pw.close();
			fw.close();
			fireOffEditor(fileName);
		} catch (IOException e) {
			DSS.doSimpleMsg("Error in goForwardAndRephase(): " + e.getMessage(), 722);
		}
	}

	/**
	 * Returns max value of an array of doubles.
	 */
	public static double maxDblArrayValue(int npts, double[] dbls) {
		if (npts == 0) return 0.0;

		double max = dbls[0];
		for (int i = 1; i < npts; i++)
			max = Math.max(max, dbls[i]);

		return max;
	}

	/**
	 * Returns index of max array value in abs value.
	 */
	public static int iMaxAbsdblArrayValue(int npts, double[] dbls) {
		if (npts == 0) return -1;

		int idx = 0;
		double maxValue = Math.abs(dbls[0]);
		for (int i = 1; i < npts; i++)
			if (Math.abs(dbls[i]) > maxValue) {
				maxValue = Math.abs(dbls[i]);
				idx = i;  // save index
			}
		return idx;
	}

	public static SequentialTime interpretLoadShapeClass(String s) {
		String ss = s.toLowerCase();

		switch (ss.charAt(0)) {
		case 'd':
			switch (ss.charAt(1)) {
			case 'a':
				return SequentialTime.DAILY;
			case 'u':
				return SequentialTime.DUTY;
			}
		case 'y':
			return SequentialTime.YEARLY;
		case 'n':
			return SequentialTime.NONE;
		}
		return SequentialTime.NONE;
	}

	public static EarthModel interpretEarthModel(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'c':
			return EarthModel.SIMPLECARSON;
		case 'f':
			return EarthModel.FULLCARSON;
		case 'd':
			return EarthModel.DERI;
		}
		return EarthModel.SIMPLECARSON;
	}

	public static String getActiveLoadShapeClass() {
		switch (DSS.activeCircuit.getActiveLoadShapeClass()) {
		case DAILY:
			return "Daily";
		case YEARLY:
			return "Yearly";
		case DUTY:
			return "Duty";
		case NONE:
			return "None";
		default:
			return "None";
		}
	}

	public static String getEarthModel(EarthModel em) {
		switch (em) {
		case SIMPLECARSON:
			return "Carson";
		case FULLCARSON:
			return "FullCarson";
		case DERI:
			return "Deri";
		default:
			return "Carson";
		}
	}

	public static int interpretColorName(String s) {
		try {
			if (compareTextShortest(s, "black") == 0) {
				return 0;
			} else if (compareTextShortest(s, "Maroon") == 0) {
				return 128;
			} else if (compareTextShortest(s, "Green") == 0) {
				return 32768;
			} else if (compareTextShortest(s, "Olive") == 0) {
				return 32896;
			} else if (compareTextShortest(s, "Navy") == 0) {
				return 8388608;
			} else if (compareTextShortest(s, "Purple") == 0) {
				return 8388736;
			} else if (compareTextShortest(s, "Teal") == 0) {
				return 8421376;
			} else if (compareTextShortest(s, "Gray") == 0) {
				return 8421504;
			} else if (compareTextShortest(s, "Silver") == 0) {
				return 12632256;
			} else if (compareTextShortest(s, "Red") == 0) {
				return 255;
			} else if (compareTextShortest(s, "Lime") == 0) {
				return 65280;
			} else if (compareTextShortest(s, "Yellow") == 0) {
				return 65535;
			} else if (compareTextShortest(s, "Blue") == 0) {
				return 16711680;
			} else if (compareTextShortest(s, "Fuchsia") == 0) {
				return 16711935;
			} else if (compareTextShortest(s, "Aqua") == 0) {
				return 16776960;
			} else if (compareTextShortest(s, "LtGray") == 0){
				return 12632256;
			} else if (compareTextShortest(s, "DkGray") == 0) {
				return 8421504;
			} else if (compareTextShortest(s, "White") == 0) {
				return 16777215;
			} else {
				return Integer.parseInt(s);
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Invalid color specification: " + s, 724);
		}
		return 16711680;  // default color
	}

	public static Color interpretColor(String s) {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	public static String makeNewCktElemName(final String oldName) {
		DSS.setObject(oldName);  // set object active
		DSSObject obj = DSS.activeDSSObject;

		return String.format("%s.%s%d",
				obj.getParentClass().getClassName(),
				obj.getParentClass().getClassName().substring(0, 3),
				obj.getClassIndex());
	}

	private static void renameCktElem(CktElement elem) {
		elem.setName(String.format("%s%d",
			elem.getParentClass().getClassName().substring(0, 3),
			elem.getClassIndex()));
		// make a new device list corresponding to the CktElements list
		DSS.activeCircuit.getDeviceList().add(elem.getName());
		elem.setChecked(true);
	}

	/**
	 * Rename buses and element names to generic names to remove identifiable
	 * names.
	 */
	public static void obfuscate() {
		int i, bref;
		int dotpos;
		int devListSize;
		HashList tempBusList;
		CktElement ctrlElem;
		String s, nodes;
		String oldBusName;
		String newBusName;
		int baseClass;
		int elemClass;
		List<String> controlUpDateStrings;
		List<CktElement> controlUpDatePtrs;

		Circuit ckt = DSS.activeCircuit;
		Parser parser = Parser.getInstance();

		/* Make sure buslist exists */

		if (DSS.activeCircuit == null) return;
		if (DSS.activeCircuit.getBusList().listSize() <= 0) return;

		tempBusList = new HashList(ckt.getBusList().listSize());

		/* Rename buses */
		for (i = 0; i < ckt.getBusList().listSize(); i++)
			tempBusList.add(String.format("B_%d", i));

		ckt.setBusList(null);
		ckt.setBusList(tempBusList);  // reassign

		/* Rename the bus names in each circuit element before renaming the
		 * elements */
		for (CktElement cktElem : ckt.getCktElements()) {
			baseClass = (cktElem.getObjType() & DSSClassDefs.BASECLASSMASK);
			if ((baseClass == DSSClassDefs.PC_ELEMENT) ||
					(baseClass == DSSClassDefs.PD_ELEMENT)) {
				s = "";
				for (i = 0; i < cktElem.getNumTerms(); i++) {
					oldBusName = cktElem.getBus(i);
					dotpos = oldBusName.indexOf('.');
					if (dotpos == -1) {
						nodes = "";
					} else {
						// preserve node designations if any
						nodes = oldBusName.substring(dotpos);
					}
					bref = cktElem.getTerminal(i).getBusRef();
					newBusName = String.format("B_%d%s", bref, nodes);
					// check for transformer because that will be an exception
					switch (cktElem.getObjType() & DSSClassDefs.CLASSMASK) {
					case DSSClassDefs.XFMR_ELEMENT:
						s = s + String.format("wdg=%d bus=%s ", i, newBusName);
						break;
					default:
						s = s + String.format("bus%d=%s ", i, newBusName);
						break;
					}
				}
				parser.setCmdBuffer(s);
				cktElem.edit();
			}
		}

		/* Rename the circuit elements to generic values */
		/* Have to catch the control elements and edit some of their
		 * parameters */

		/* First, make scripts to change the monitored element names in the
		 * controls to what they will be */
		controlUpDateStrings = new ArrayList<String>();
		controlUpDatePtrs = new ArrayList<CktElement>();

		for (CktElement cktElem : ckt.getCktElements()) {
			switch (cktElem.getObjType() & DSSClassDefs.CLASSMASK) {
			case DSSClassDefs.CAP_CONTROL:
				s = String.format("element=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0)));
				controlUpDateStrings.add(s + String.format("capacitor=%s ",
						makeNewCktElemName("capacitor." + cktElem.getPropertyValue(2)).substring(10, 99)));
				controlUpDatePtrs.add(cktElem);
				break;
			case DSSClassDefs.REG_CONTROL:
				// handled below
				break;
			case DSSClassDefs.RELAY_CONTROL:
				s = String.format("monitoredObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0)));
				controlUpDateStrings.add(s + String.format("switchedObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(2))));
				controlUpDatePtrs.add(cktElem);
				break;
			case DSSClassDefs.RECLOSER_CONTROL:
				s = String.format("monitoredObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0)));
				controlUpDateStrings.add(s + String.format("switchedObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(2))));
				controlUpDatePtrs.add(cktElem);
				break;
			case DSSClassDefs.FUSE_CONTROL:
				s = String.format("monitoredObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0)));
				controlUpDateStrings.add(s + String.format("switchedObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(2))));
				controlUpDatePtrs.add(cktElem);
				break;
			case DSSClassDefs.GEN_CONTROL:
				controlUpDateStrings.add(String.format("element=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0))));
				controlUpDatePtrs.add(cktElem);
				break;
			case DSSClassDefs.STORAGE_CONTROL:
				controlUpDateStrings.add (String.format("element=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0))));
				controlUpDatePtrs.add(cktElem);
				break;
			case DSSClassDefs.SWT_CONTROL:
				controlUpDateStrings.add (String.format("switchedObj=%s ",
						makeNewCktElemName(cktElem.getPropertyValue(0))));
				controlUpDatePtrs.add(cktElem);
				break;
			}
		}

		for (CktElement cktElem : ckt.getCktElements())
			cktElem.setChecked(false);  // initialize to not checked

		devListSize = ckt.getDeviceList().listSize();
		ckt.setDeviceList(null);
		ckt.setDeviceList(new HashList(devListSize));

		for (CktElement cktElem : ckt.getCktElements()) {
			if (!cktElem.isChecked()) {
				elemClass = (cktElem.getObjType() & DSSClassDefs.CLASSMASK);
				renameCktElem(cktElem);
				switch (elemClass) {
				case DSSClassDefs.XFMR_ELEMENT:
					if (cktElem.hasControl()) {
						ctrlElem = cktElem.getControlElement();
						if (ctrlElem != null) {
							parser.setCmdBuffer(String.format("transformer=%s",
									cktElem.getName()));
							ctrlElem.edit();
						}
					}
					break;
				default:
					break;
				}
			}
		}


		/* Run the control update scripts now that everything is renamed */
		for (i = 0; i < controlUpDatePtrs.size() - 1; i++) {
			CktElement cktElem = controlUpDatePtrs.get(i);
			parser.setCmdBuffer( controlUpDateStrings.get(i) );
			cktElem.edit();
		}

		controlUpDateStrings = null;
		controlUpDatePtrs = null;
	}

}
