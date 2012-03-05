package com.ncond.dss.common.impl;

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

import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexUtils;


import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.FeederObj;
import com.ncond.dss.common.Solution;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Terminal;
import com.ncond.dss.common.impl.DSSBus.NodeBus;
import com.ncond.dss.common.impl.DSSCircuit.CktElementDef;
import com.ncond.dss.control.ControlElem;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.CapacitorObj;
import com.ncond.dss.delivery.FaultObj;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.delivery.ReactorObj;
import com.ncond.dss.delivery.Winding;
import com.ncond.dss.executive.impl.DSSExecutive;
import com.ncond.dss.executive.impl.ExecCommands;
import com.ncond.dss.executive.impl.ExecOptions;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.Dynamics;
import com.ncond.dss.shared.HashList;
import com.ncond.dss.shared.impl.ComplexUtil;
import com.ncond.dss.shared.impl.HashListImpl;

public class Util {

	private static final String PAD_STRING =
			"                                                  "; // 50 blanks
	private static final String PAD_DOTS_STRING =
			" ................................................."; // 50 dots

	private Util() {

	}

	public static String expandFileName(String child) {
		if (child.length() == 0)
			return "";

		if (new File(child).isAbsolute())
			return child;

		try {
			return new File(DSS.currentDirectory, child).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String extractFileDir(String path) {
		return new File(path).getParentFile().getAbsolutePath();
	}

	/* Copy the contents of an array to an array of a new size. */

	public static int[] resizeArray(int[] oldArray, int newSize) {
		int[] newArray = new int[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static double[] resizeArray(double[] oldArray, int newSize) {
		double[] newArray = new double[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static String[] resizeArray(String[] oldArray, int newSize) {
		String[] newArray = new String[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Complex[] resizeArray(Complex[] oldArray, int newSize) {
		Complex[] newArray = new Complex[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static DSSObject[] resizeArray(DSSObject[] oldArray, int newSize) {
		DSSObject[] newArray = new DSSObject[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static CktElementDef[] resizeArray(CktElementDef[] oldArray, int newSize) {
		CktElementDef[] newArray = new CktElementDef[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Bus[] resizeArray(Bus[] oldArray, int newSize) {
		Bus[] newArray = new Bus[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static NodeBus[] resizeArray(NodeBus[] oldArray, int newSize) {
		NodeBus[] newArray = new NodeBus[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Terminal[] resizeArray(Terminal[] oldArray, int newSize) {
		PowerTerminal[] newArray = new PowerTerminal[newSize];
		if (oldArray == null)
			oldArray = newArray;

		int oldSize = oldArray.length;

		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}

	public static Winding[] resizeArray(Winding[] oldArray, int newSize) {
		Winding[] newArray = new Winding[newSize];
		if (oldArray == null)
			return newArray;
		int oldSize = oldArray.length;
		int length = Math.min(oldSize, newSize);
		if (length > 0)
			System.arraycopy(oldArray, 0, newArray, 0, length);
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
		return s.substring(0, s.length()) + PAD_STRING.substring(0, width - s.length());
	}

	/**
	 * Pad out a string with dots to width characters.
	 */
	public static String padDots(String s, int width) {
		return s.substring(0, s.length()) + PAD_DOTS_STRING.substring(0, width - s.length());
	}

	/**
	 * Pad out a string with blanks to width characters or truncate to width chars.
	 */
	public static String padTrunc(String s, int width) {
		return pad(s, width).substring(0, width);
	}

	public static String fullName(CktElement pElem) {
		return encloseQuotes(pElem.getDSSClassName() + "." + pElem.getName().toUpperCase());
	}

	/**
	 * Strips off everything up to a period.
	 */
	public static String stripExtension(String s) {
		int dotpos = s.indexOf('.');
		if (dotpos == -1)
			dotpos = s.length();
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

		String result = "[NULL]";  // FIXME: use StringBuilder
		if (count > 0) {
			result = "[";
			for (int i = 0; i < count; i++) {
				result = result + String.valueOf(iarray[i]);
				if (i != count - 1)
					result = result + ", ";
			}
			result = result + "]";
		}
		return result;
	}

	/**
	 * Put array values in brackets separated by commas.
	 */
	public static String dblArrayToString(double[] dblarray, int count) {
		String result = "[NULL]";
		if (count > 0) {
			result = String.format("[%.10g", dblarray[0]);
			for (int i = 1; i < count; i++) {
				result = result + String.format(", %.10g", dblarray[i]);
			}
			result = result + "]";
		}
		return result;
	}

	/**
	 * Put array values in brackets separated by commas.
	 */
	public static String cmplxArrayToString(Complex[] cpxarray, int count) {
		String result = "[NULL]";
		if (count > 0) {
			result = String.format("[%.10g +j %.10g",  cpxarray[0].getReal(), cpxarray[0].getImaginary());
			for (int i = 1; i < count; i++) {
				result = result + String.format(", %.10g +j %.10g", cpxarray[i].getReal(), cpxarray[i].getImaginary());
			}
			result = result + "]";
		}
		return result;
	}

	public static String encloseQuotes(String s) {
		return '"' + s + '"';
	}

	/**
	 * Interpret solution mode. Could be: "nominal", "daily",  "yearly",
	 *   "montecarlo", "dutycycle",  "loadduration", "peakdays", etc.
	 */
	public static int interpretSolveMode(String s) {
		String slc;

		slc = s.toLowerCase();

		switch (slc.charAt(0)) {
		case 's':
			return Dynamics.SNAPSHOT;
		case 'd':
			switch (slc.charAt(1)) {
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
			switch (slc.charAt(1)) {
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
			switch (slc.charAt(1)) {
			case 'd':
				switch (slc.charAt(2)) {
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
		String slc = s.toLowerCase();

		switch (slc.charAt(0)) {
		case 'o':
			return DSS.CONTROLSOFF;
		case 'e':
			return DSS.EVENTDRIVEN;  // "event"
		case 't':
			return DSS.TIMEDRIVEN;   // "time"
		default:
			return DSS.CTRLSTATIC;
		}
	}

	public static int interpretLoadModel(String s) {
		int result;
		String s2 = s.toLowerCase();

		switch (s2.charAt(0)) {
		case 'a':
			result = DSS.ADMITTANCE;
			break;
		case 'p':
			result = DSS.POWERFLOW;
			break;
		default:
			result = DSS.ADMITTANCE;
			break;
		}

		/* If this represents a change, invalidate all the PC Yprims */
		Circuit ckt = DSS.activeCircuit;
		if (result != ckt.getSolution().getLoadModel())
			ckt.invalidateAllPCElements();

		return result;
	}

	/**
	 * Interpret yes/no properties - can also be true/false.
	 */
	public static boolean interpretYesNo(String s) {
		String s2 = s.toLowerCase();
		switch (s2.charAt(0)) {
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
		String slc = s.toLowerCase();

		switch (slc.charAt(0)) {
		case 'g':
			return DSS.GAUSSIAN;
		case 'u':
			return DSS.UNIFORM;
		case 'l':
			return DSS.LOGNORMAL;
		default:
			return 0;  // no variation for any other entry
		}
	}

	/**
	 * Type of device to automatically add. Default is capacitor.
	 */
	public static int interpretAddType(String s) {
		String slc = s.toLowerCase();
		switch (slc.charAt(0)) {
		case 'g':
			return DSS.GENADD;
		default:
			return DSS.CAPADD;
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
		String slc = s.toLowerCase().substring(0, 2);
		if (slc.equalsIgnoreCase("ne")) {
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
	public static void initDblArray(int numValues, double[] xArray, double value) {
		for (int i = 0; i < numValues; i++)
			xArray[i] = value;
	}

	/**
	 * Set all elements of a integer array.
	 */
	public static void initIntArray(int numValues, int[] xArray, int value) {
		for (int i = 0; i < numValues; i++)
			xArray[i] = value;
	}

	/**
	 * Get numeric values from an array specified either as a list on numbers
	 * or a text file spec. ResultArray must be allocated to maxValues by
	 * calling routine. File is assumed to have one value per line.
	 */
	public static int interpretDblArray(String s, int maxValues, double[] resultArray) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		String csvFileName;
		int csvColumn;
		boolean csvHeader;
		String inputLine;
		int iskip;

		DSS.auxParser.setCmdString(s);
		String parmName = DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString();
		int result = maxValues;  // default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (parmName.equalsIgnoreCase("file")) {
			/* Default values */
			if (param.equals("%%result%%")) {
				csvFileName = DSS.lastResultFile;
			} else {
				csvFileName = param;
			}

			if (!new File(csvFileName).exists()) {
				DSS.doSimpleMsg(String.format("CSV file \"%s\" does not exist", csvFileName), 70401);
				return result;
			}

			// default options
			csvColumn = 0;
			csvHeader = false;

			// look for other options  (may be in either order)
			parmName = DSS.auxParser.getNextParam();
			param = DSS.auxParser.makeString();
			while (param.length() > 0) {
				if (Util.compareTextShortest(parmName, "column") == 0)
					csvColumn = DSS.auxParser.makeInteger();
				if (Util.compareTextShortest(parmName, "header") == 0)
					csvHeader = Util.interpretYesNo(param);
				parmName = DSS.auxParser.getNextParam();
				param = DSS.auxParser.makeString();
			}

			// load the list from a file
			try {
				// FIXME Use BufferedReader not BufferedInputStream
				fis = new FileInputStream(csvFileName);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);

				if (csvHeader) dis.readLine();  // skip the header row

				for (int i = 0; i < maxValues; i++) {
					try {
						if (dis.available() != 0) {
							inputLine = dis.readLine();
							DSS.auxParser.setCmdString(inputLine);
							for (iskip = 0; iskip < csvColumn; iskip++) {
								parmName = DSS.auxParser.getNextParam();
								resultArray[i] = DSS.auxParser.makeDouble();
							}
						} else {
							result = i - 1;  // this will be different if less found;  TODO Check zero based indexing
							break;
						}
					} catch (Exception e) {
						DSS.doSimpleMsg(String.format("Error reading %d-th numeric array value from file: \"%s\" Error is:", i, param, e.getMessage()), 705);
						result = i - 1;
						break;
					}
				}
				fis.close();
				bis.close();
				dis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ((parmName.length() > 0) && (compareTextShortest(parmName, "dblfile") == 0)) {
			// load the list from a file of doubles (no checking done on type of data)
			throw new UnsupportedOperationException();
			// TODO Implement this section
		} else if ((parmName.length() > 0) && (compareTextShortest(parmName, "sngfile") == 0)) {
			// load the list from a file of singles (no checking done on type of data)
			throw new UnsupportedOperationException();
			// TODO Implement this section
		} else {
			// parse list of values off input string

			// parse Values of array list
			for (int i = 0; i < maxValues; i++) {
				resultArray[i] = DSS.auxParser.makeDouble();  // fills array with zeros if we run out of numbers
				DSS.auxParser.getNextParam();
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
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		DSS.auxParser.setCmdString(s);
		String parmName = DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString();
		int result = maxValues;  // default return value

		/* Syntax can be either a list of numeric values or a file specification: File= ... */

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				// FIXME Use BufferedReader not BufferedInputStream
				fis = new FileInputStream(param);
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);

				for (int i = 0; i < maxValues; i++) {
					try {
						if (dis.available() != 0) {
							resultArray[i] = dis.readInt();
						} else {
							result = i - 1;  // this will be different if less found;  TODO Check zero based indexing
							break;
						}
					} catch (Exception e) {
						DSS.doSimpleMsg(String.format("Error trying to read numeric array values from file: \""+param +"\"  Error is: "+e.getMessage()), 706);
						result = i - 1;
						break;
					}
				}
				fis.close();
				bis.close();
				dis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {  // parse list of values off input string

			// parse values of array list
			for (int i = 0; i < maxValues; i++) {
				resultArray[i] = DSS.auxParser.makeInteger();  // fills array with zeros if we run out of numbers
				DSS.auxParser.getNextParam();
			}
		}
		return result;
	}

	/**
	 * Return stepsize in seconds.
	 */
	public static double interpretTimeStepSize(String s) {
		char ch;
		String s2;
		double result;

		/* Try to convert and see if we get an error */
		try {
			return Double.valueOf(s);  // only a number was specified, so must be seconds
		} catch (NumberFormatException e) {
			/* Error occurred so must have a units specifier */
			ch = s.charAt(s.length() - 1);  // get last character
			s2 = s.substring(0, s.length() - 2);
			try {
				result = Double.valueOf(s2);
				switch (ch) {
				case 'h':
					result = result * 3600.0;
					break;
				case 'm':
					result = result * 60.0;
					break;
				case 's':
					// do nothing
					break;
				default:
					result = DSS.activeCircuit.getSolution().getDynaVars().h;  // don't change it
					DSS.doSimpleMsg("Error in specification of stepSize: \"" + s +"\" Units can only be h, m, or s (single char only) ", 99934);
					break;
				}
			} catch (NumberFormatException ee) {
				result = DSS.activeCircuit.getSolution().getDynaVars().h; // don't change it
				DSS.doSimpleMsg("Error in specification of stepSize: " + s, 99933);
				return result;
			}
		}

		return result;
	}

	/**
	 * Get string values from an array specified either as a list on strings or
	 * a text file spec. ResultArray is allocated as needed. File is assumed to
	 * have one value per line.
	 */
	public static void interpretAndAllocStrArray(String s, int size, String[] resultArray) {

		// throw away any previous allocation
		resultArray = new String[0];

		// now reallocate
		int maxSize = 100;  // initialize
		size = 0;
		resultArray = new String[maxSize];

		DSS.auxParser.setCmdString(s);
		String parmName = DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */
		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				FileInputStream fileStream = new FileInputStream(param);
				DataInputStream dataStream = new DataInputStream(fileStream);
				BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));

				while ((param = reader.readLine()) != null) {
					if (param != "") {  // ignore blank lines in file
						size += 1;
						if (size > maxSize) {
							maxSize += 100;
							resultArray = resizeArray(resultArray, maxSize);
						}
						resultArray[size] = param;
					}
				}
				fileStream.close();
				dataStream.close();
				reader.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read numeric array values from a file. Error is: "+e.getMessage(), 707);
			}

		} else {  // parse list of values off input string

			// parse values of array list
			while (param != "") {
				size += 1;
				if (size > maxSize) {
					maxSize += 100;
					resultArray = resizeArray(resultArray, maxSize);
				}
				resultArray[size] = param;
				parmName = DSS.auxParser.getNextParam();
				param = DSS.auxParser.makeString();
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
		String nextParam;

		// throw away any previous allocation
		resultList.clear();

		DSS.auxParser.setCmdString(s);
		String parmName = DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString();

		/* Syntax can be either a list of string values or a file specification:  File= ... */

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				FileInputStream fileStream = new FileInputStream(param);
				DataInputStream dataStream = new DataInputStream(fileStream);
				BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));

				while ((param = reader.readLine()) != null) {
					DSS.auxParser.setCmdString(param);
					parmName = DSS.auxParser.getNextParam();
					nextParam = DSS.auxParser.makeString();
					if (nextParam.length() > 0)  // Ignore Blank Lines in File
						resultList.add(nextParam);
				}
				fileStream.close();
				dataStream.close();
				reader.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read numeric array values from a file. Error is: "+e.getMessage(), 708);
			}
		} else {  // parse list of values off input string

			// parse values of array list
			while (param != "") {
				resultList.add(param);
				parmName = DSS.auxParser.getNextParam();
				param = DSS.auxParser.makeString();
			}
		}
	}

	public static void parseObjectClassandName(String fullObjName, StringBuffer className, StringBuffer objName) {
		// split off obj class and name
		int dotpos = fullObjName.indexOf('.');
		switch (dotpos) {
		case -1:
			objName.delete(0, objName.length());
			// assume it is all objname; class defaults
			objName.append( fullObjName.substring(0, fullObjName.length()) );
			className.delete(0, className.length());
			break;
		default:
			className.delete(0, className.length());
			className.append( fullObjName.substring(0, dotpos) );
			objName.delete(0, objName.length());
			objName.append( fullObjName.substring(dotpos + 1, fullObjName.length()) );
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
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			return getSolutionModeIDName(ckt.getSolution().getMode());
		} else {
			return "UNKNOWN";
		}
	}

	public static String getControlModeID() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			switch (ckt.getSolution().getControlMode()) {
			case DSS.CTRLSTATIC:
				return "STATIC";
			case DSS.EVENTDRIVEN:
				return "EVENT";
			case DSS.TIMEDRIVEN:
				return "TIME";
			case DSS.CONTROLSOFF:
				return "OFF";
			default:
				return "UNKNOWN";
			}
		} else {
			return "UNKNOWN";
		}
	}

	public static String getRandomModeID() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			switch (ckt.getSolution().getRandomType()) {
			case 0:
				return "None";
			case DSS.GAUSSIAN:
				return "Gaussian";
			case DSS.UNIFORM:
				return "Uniform";
			case DSS.LOGNORMAL:
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
		case DSS.ADMITTANCE:
			return "Admittance";
		default:
			return "PowerFlow";
		}
	}

	public static int[] parseIntArray(int[] iarray, int[] count, String s) {
		String param = " ";

		// parse the line once to get the count of tokens on string, S
		DSS.auxParser.setCmdString(s);
		count[0] = 0;
		while (param.length() > 0) {
			DSS.auxParser.getNextParam();
			param     = DSS.auxParser.makeString();
			if (param.length() > 0)
				count[0]++;
		}

		// reallocate iarray to new size
		iarray = resizeArray(iarray, count[0]);

		// Parse again for real
		DSS.auxParser.setCmdString(s);
		for (int i = 0; i < count[0]; i++) {
			DSS.auxParser.getNextParam();
			iarray[i] = DSS.auxParser.makeInteger();
		}

		return iarray;
	}

	public static boolean isShuntElement(CktElement elem) {
		switch (elem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
		case DSSClassDefs.CAP_ELEMENT:
			return ((CapacitorObj) elem).isShunt();
		case DSSClassDefs.REACTOR_ELEMENT:
			return ((ReactorObj) elem).isShunt();
		default:
			return false;
		}
	}

	public static boolean isLineElement(CktElement elem) {
		if ((elem.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LINE_ELEMENT) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTransformerElement(CktElement elem) {
		if ((elem.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.XFMR_ELEMENT) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isStubLine(CktElement elem) {
		double ZTest;
		LineObj lineElement = (LineObj) elem;

		/* Get positive sequence or equivalent from matrix */
		if (lineElement.isSymComponentsModel()) {
			ZTest = new Complex(lineElement.getR1(), lineElement.getX1()).abs() * lineElement.getLen();
		} else {
			/* Get impedance from Z matrix */   /* Zs - Zm */
			if (lineElement.getNumPhases() > 1) {
				ZTest = lineElement.getZ().get(0, 0).subtract(lineElement.getZ().get(0, 1)).abs() * lineElement.getLen();
			} else {
				ZTest = lineElement.getZ().get(0, 0).abs() * lineElement.getLen();
			}
		}

		if (ZTest <= DSS.activeCircuit.getReductionZmag()) {
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

		int devClassIndex, devIndex;
		StringBuffer devClassName = new StringBuffer();
		StringBuffer devName = new StringBuffer();

		int result = -1;  // default return value
		parseObjectClassandName(fullObjName, devClassName, devName);
		devClassIndex = DSS.classNames.find(devClassName.toString());
		if (devClassIndex == -1)
			devClassIndex = DSS.lastClassReferenced;

		// since there could be devices of the same name of different classes,
		// loop until we find one of the correct class
		Circuit ckt = DSS.activeCircuit;
		devIndex = ckt.getDeviceList().find(devName.toString());
		while (devIndex > -1) {
			if (ckt.getDeviceRef()[devIndex].cktElementClass == devClassIndex)  // we got a match
				return devIndex;
			devIndex = ckt.getDeviceList().findNext();
		}

		return result;
	}

	public static String strReal(double value, int numDecimals) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			String fmt = String.format("%%.%df", numDecimals);
			pw.printf(fmt, value);
			return sw.toString();
		} catch (Exception e) {
			return "*****";
		}
	}

	public static void dumpAllocationFactors(String fileName) {
		PrintWriter pw;

		try {
			FileWriter fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			DSS.doErrorMsg("Error opening "+fileName+" for writing.", e.getMessage(), " File protected or other file error.", 709);
			return;
		}

		for (LoadObj pLoad : DSS.activeCircuit.getLoads()) {
			switch (pLoad.getLoadSpecType()) {
			case 3:
				pw.println("Load."+pLoad.getName()+".AllocationFactor=" + String.format("%-.5g", pLoad.getKVAAllocationFactor()));
				break;
			case 4:
				pw.println("Load."+pLoad.getName()+".CFactor=" + String.format("%-.5g", pLoad.getCFactor()));
				break;
			}
		}

		pw.close();

		DSS.globalResult = fileName;
	}

	public static void dumpAllDSSCommands(String fileName) {
		PrintWriter pw;

		try {
			fileName = DSS.DSSDataDirectory + "AllocationFactors.txt";
			FileWriter fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			DSS.doErrorMsg("Error opening "+fileName+" for writing.", e.getMessage(), "Disk protected or other file error", 710);
			return;
		}

		// dump executive commands
		pw.println("[execcommands]");
		for (int i = 0; i < ExecCommands.NumExecCommands; i++)
			pw.println(i +" + \"" + ExecCommands.getInstance().getExecCommand(i) + "\" \"" + ExecCommands.getInstance().getCommandHelp(i) + "\"");

		// dump executive options
		pw.println("[execoptions]");
		for (int i = 0; i < ExecOptions.NumExecOptions; i++)
			pw.println(i + ", \"" + ExecOptions.getInstance().getExecOption(i) + "\", \"" + ExecOptions.getInstance().getOptionHelp(i) + "\"");

		// dump all present DSSClasses
		for (DSSClass pClass : DSS.DSSClassList) {
			pw.println("[" + pClass.getName() + "]");
			for (int i = 0; i < pClass.getNumProperties(); i++)
				pw.println(i + ", \"" + pClass.getPropertyName(i) + "\" \"" + pClass.getPropertyHelp()[i] + "\"");
		}

		pw.close();
	}

	/**
	 * Find closest base voltage.
	 */
	public static double nearestBasekV(double kV) {
		double diff;

		Circuit ckt = DSS.activeCircuit;

		int count = 0;
		double testKV = ckt.getLegalVoltageBases()[count];
		double result = testKV;
		double minDiff = 1.e50;  // big number

		while (testKV != 0.0) {
			diff = Math.abs(1.0 - kV / testKV);  // get per unit difference
			if (diff < minDiff) {
				minDiff = diff;
				result = testKV;
			}

			count += 1;
			testKV = ckt.getLegalVoltageBases()[count];
		}
		return result;
	}

	public static boolean savePresentVoltages() {
		PrintWriter pw;
		double dNumNodes;

		try {
			FileWriter fw = new FileWriter(DSS.DSSDataDirectory + DSS.circuitName_ + "SavedVoltages.dbl");
			pw = new PrintWriter(fw);
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening/creating file to save voltages: " + e.getMessage(), 711);
			return false;
		}

		try {
			Circuit ckt = DSS.activeCircuit;
			SolutionObj sol = ckt.getSolution();

			dNumNodes = ckt.getNumNodes();
			pw.printf("%.d",  dNumNodes);
			for (int i = 0; i < ckt.getNumNodes(); i++) {
				pw.printf(" %.5f %.5f", sol.getNodeV(i).getReal(), sol.getNodeV(i).getImaginary());
			}

			pw.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Error writing file to save voltages: " + e.getMessage(), 712);
			return false;
		}

		return true;
	}

	public static boolean retrieveSavedVoltages() {
		// FIXME Implement this method and savePresentVoltages() using MatrixMarket format.
		throw new UnsupportedOperationException();
	}

	/**
	 * Intialize PCElement base values for harmonics analysis.
	 */
	public static boolean initializeForHarmonics() {
		if (savePresentVoltages()) {  // zap voltage vector to disk
			for (PCElement pcElem : DSS.activeCircuit.getPCElements())
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
		for (PCElement pcElem : DSS.activeCircuit.getPCElements()) {
			if (pcElem.isEnabled())
				pcElem.initStateVars();
		}
	}

	/**
	 * For now, just does generators.
	 */
	public static void invalidateAllPCElements() {
		for (PCElement pElem : DSS.activeCircuit.getPCElements())
			if (pElem.isEnabled())
				pElem.setYPrimInvalid(true);
	}

	public static double presentTimeInSec() {
		SolutionObj sol = DSS.activeCircuit.getSolution();
		return sol.getDynaVars().t + sol.getIntHour() * 3600.0;
	}

	public static int doResetFaults() {
		for (FaultObj fElem : DSS.activeCircuit.getFaults())
			fElem.reset();
		return 0;
	}

	public static int doResetControls() {
		for (ControlElem cElem : DSS.activeCircuit.getDSSControls()) {
			if (cElem.isEnabled())
				cElem.reset();
		}
		return 0;
	}

	public static int getNodeNum(int nodeRef) {
		if (nodeRef == -1) {
			return 0;
		} else {
			return DSS.activeCircuit.getMapNodeToBus()[nodeRef].nodeNum;
		}
	}

	/**
	 * Rotate a phasor by an angle and harmonic.
	 */
	public static Complex rotatePhasorDeg(Complex phasor, double h, double angleDeg) {
		return phasor.multiply( ComplexUtils.polar2Complex(1.0, Math.toRadians(h * angleDeg)) );
	}

	public static Complex rotatePhasorRad(Complex phasor, double h, double angleRad) {
		return phasor.multiply( ComplexUtils.polar2Complex(1.0, h * angleRad) );
	}

	private static double pfSign(Complex S) {
		return S.getReal() * S.getImaginary() < 0.0 ? -1.0 : 1.0;
	}

	/**
	 * Creates continous PF function from 1 to 2 where 1-2 range is leading (opposite sign).
	 */
	public static void convertComplexArrayToPowerandPF(Complex[] buffer, int n) {
		double mag, PF;

		/* Assume we get P + jQ */
		for (int i = 0; i < n; i++) {
			mag = buffer[i].abs();
			if (mag > 0.0) {
				PF = pfSign(buffer[i]) * Math.abs(buffer[i].getReal()) / mag;
				if (PF < 0.0)
					PF = 2.0 - Math.abs(PF);
			} else {
				PF = 1.0;  // for zero power
			}
			buffer[i] = new Complex(buffer[i].getReal(), PF);
		}
	}

	public static void convertComplexArrayToPolar(Complex[] buffer, int n) {
		for (int i = 0; i < n; i++)
			buffer[i] = new Complex(buffer[i].abs(), ComplexUtil.degArg(buffer[i]));
	}

	/**
	 * Assume p is a complex array and compute the residual of the number of
	 * phases specified.
	 */
	public static Complex residual(Object p, int nph) {
		Complex[] pc = (Complex[]) p;
		Complex result = Complex.ZERO;
		for (int i = 0; i < nph; i++)
			result = result.add(pc[i]);
		return result;
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
			DSS.doSimpleMsg(String.format("Exception clearing event log: %s, @EventStrings=%s", e.getMessage(), DSS.eventStrings.toString()), 7151);
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

	public static void dumpComplexMatrix(PrintStream f, CMatrix aMatrix) {
		// TODO Convert to use MatrixMarket format
		try {
			if (aMatrix != null) {
				f.println("!(G matrix)");
				for (int i = 0; i < aMatrix.order(); i++) {
					f.print("! ");
					for (int j = 0; j < i; j++)
						f.printf("%.8f ", aMatrix.get(i, j).getReal());
					f.println();
				}
				f.println("!(B Matrix) = ");
				for (int i = 0; i < aMatrix.order(); i++) {
					f.print("! ");
					for (int j = 0; j < i; j++)
						f.printf("%.8f ", aMatrix.get(i, j).getImaginary());
					f.println();
				}
			}
		} catch (Exception e) {
			DSS.doSimpleMsg("Error in dump complex matrix: "+e.getMessage()+"  Write aborted.", 716);
		}
	}

	/**
	 * Check all conductors of this element to see if it is closed.
	 * Make sure at least one phase on each terminal is closed.
	 */
	public static boolean allTerminalsClosed(CktElement thisElement) {
		boolean result = false;
		for (int i = 0; i < thisElement.getNumTerms(); i++) {
			result = false;
			thisElement.setActiveTerminalIdx(i);
			for (int j = 0; j < thisElement.getNumPhases(); j++)
				if (thisElement.isConductorClosed(j)) {
					result = true;
					break;
				}
			if (!result)
				return result;  // didn't find a closed phase on this terminal
		}
		return result;
	}

	/**
	 * Special Function to write the VSource class and change the DSS command
	 * of the first Source so that there is no problem with duplication when
	 * the circuit is subsequently created.
	 */
	public static boolean writeVSourceClassFile(DSSClass cls, boolean isCktElement) {
		PrintWriter pw;
		String clsName;
		CktElement elem;

		boolean result = true;
		if (cls.getElementCount() == 0)
			return result;

		try {
			clsName = cls.getName();
			FileWriter fw = new FileWriter(clsName + ".dss");
			pw = new PrintWriter(fw);

			DSS.savedFileList.add(clsName + ".dss");
			cls.getFirst();  // sets activeDSSObject
			writeActiveDSSObject(pw, "Edit");  // write first Vsource out as an edit
			while (cls.getNext() >= 0) {
				// skip cktElements that have been checked before and written out by
				// something else
				elem = (CktElement) DSS.activeDSSObject;
				if (elem.isHasBeenSaved())
					continue;
				// skip disabled circuit elements; write all general DSS objects
				writeActiveDSSObject(pw, "New");  // sets hasBeenSaved = true
			}
			pw.close();
			cls.setSaved(true);
		} catch (Exception e) {
			DSS.doSimpleMsg("WriteClassFile error: "+e.getMessage(), 717);
			result = false;
		}

		return result;
	}

	public static boolean writeClassFile(DSSClass cls, String fileName, boolean isCktElement) {
		FileWriter fw;
		PrintWriter pw;
		String clsName;
		int nRecords;
		CktElement elem;

		boolean result = true;

		if (cls.getElementCount() == 0)
			return result;

		try {
			clsName = cls.getName();
			if (fileName.length() == 0)
				fileName = clsName + ".dss";  // default file name

			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			nRecords = 0;

			cls.getFirst();  // sets activeDSSObject

			while (cls.getNext() >= 0) {
				// skip cktElements that have been checked before and written out by
				// something else
				if (isCktElement) {
					elem = (CktElement) DSS.activeDSSObject;
					if (elem.isHasBeenSaved() || (!elem.isEnabled()))
						continue;
				}

				writeActiveDSSObject(pw, "New");  // sets hasBeenSaved = true
				nRecords += 1;  // count the actual records

			}
			pw.close();

			if (nRecords > 0) {
				DSS.savedFileList.add(fileName);
			} else {
				new File(fileName).delete();
			}

			cls.setSaved(true);
		} catch (Exception e) {
			DSS.doSimpleMsg("WriteClassFile error: "+e.getMessage(), 718);
			result = false;
		}
		return result;
	}

	/**
	 * Checks for blanks in the name and puts quotes around it.
	 */
	public static String checkForBlanks(String s) {
		String result = s;
		if (s.indexOf(' ') >= 0)
			if (s.charAt(0) != '(')  // ignore if already quoted
				if (s.charAt(0) != '[')  // ignore if already quoted
					if (s.charAt(0) != '{')  // ignore if already quoted
						result = "\""+s+"\"";
		return result;
	}

	public static void writeActiveDSSObject(PrintWriter pw, String newOrEdit) {

		DSSClass parClass = DSS.activeDSSObject.getParentClass();
		pw.write(newOrEdit + " \"" + parClass.getName() + "." + DSS.activeDSSObject.getName() + "\"");

		DSS.activeDSSObject.saveWrite(pw);

		// handle disabled circuit elements; modified to allow applets to save disabled elements 12-28-06
		if ((DSS.activeDSSObject.getDSSObjType() & DSSClassDefs.CLASSMASK) != DSSClassDefs.DSS_OBJECT) {
			CktElement elem = (CktElement) DSS.activeDSSObject;
			if (!elem.isEnabled())
				pw.write(" ENABLED=NO");
		}
		pw.println();  // terminate line

		DSS.activeDSSObject.setHasBeenSaved(true);
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

		FileInputStream fis;
		DataInputStream dis;
		BufferedReader br;

		FileWriter fw;
		PrintWriter pw;

		String saveDelims, line, field, alignedFile = "";
		int[] fieldLength;
		int arraySize, fieldLen = 0, fieldNum;

		boolean result = true;

		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening file: "+fileName+", "+e.getMessage(), 719);
			return false;
		}

		try {
			alignedFile = new File(fileName).getAbsolutePath() + "Aligned_" + new File(fileName).getName();
			fw = new FileWriter(alignedFile);
			pw = new PrintWriter(fw);
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening file: "+ alignedFile +", "+e.getMessage(), 720);

			try {
				fis.close();
				dis.close();
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}

			return false;
		}

		saveDelims = DSS.auxParser.getDelimChars();
		DSS.auxParser.setDelimChars(",");
		arraySize = 10;
		fieldLength = new int[arraySize];

		try {
			/* Scan once to set field lengths */
			while ((line = br.readLine()) != null) {
				DSS.auxParser.setCmdString(line);  // load the parser
				fieldNum = 0;
				while (fieldLen > 0) {
					DSS.auxParser.getNextParam();
					field = DSS.auxParser.makeString();
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
				DSS.auxParser.setCmdString(line);  // load the parser
				fieldNum = 0;
				while (fieldLen > 0) {
					DSS.auxParser.getNextParam();
					field = DSS.auxParser.makeString();
					if (field.indexOf(' ') >= 0)
						field = "\"" + field + "\"";  // add quotes if a space in field
					fieldLen = field.length();
					if (fieldLen > 0) {
						fieldNum += 1;
						pw.write( pad(field, fieldLength[fieldNum] + 1) );
					}
				}

				if (line.indexOf('!') >= 0)
					pw.write(extractComment(line));

				pw.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				fis.close();
				dis.close();
				br.close();

				fw.close();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			fieldLength = null;
			DSS.auxParser.setDelimChars(saveDelims);
		}

		DSS.globalResult = alignedFile;

		return result;
	}

	public static int doExecutiveCommand(String s) {
		DSSExecutive.getInstance().setCommand(s);
		return DSSExecutive.getInstance().getErrorResult();
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
		int nRef;
		double result = -1.0;

		Circuit ckt = DSS.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			if (ckt.getBus(i).getKVBase() > 0.0)
				for (int j = 0; j < ckt.getBus(i).getNumNodesThisBus(); j++) {
					nRef = ckt.getBus(i).getRef(j);
					if (nRef >= 0)
						result = Math.max(result, ckt.getSolution().getNodeV(nRef).abs() / ckt.getBus(i).getKVBase());
				}
		}

		return result * 0.001;
	}

	public static double getMinPUVoltage(boolean ignoreNeutrals) {
		int nRef;
		double VMagPU;

		double result    = 1.0e50;  // start with big number
		boolean minFound = false;

		Circuit ckt = DSS.activeCircuit;

		for (int i = 0; i < ckt.getNumBuses(); i++) {
			Bus bus = ckt.getBus(i);
			if (bus.getKVBase() > 0.0)
				for (int j = 0; j < bus.getNumNodesThisBus(); j++) {
					nRef = bus.getRef(j);
					if (nRef >= 0) {
						VMagPU = ckt.getSolution().getNodeV(nRef).abs() / bus.getKVBase();
						if (ignoreNeutrals) {
							if (VMagPU > 100.0) {  // 0.1 pu
								result = Math.min(result, VMagPU);  // only check buses greater than 10%
								minFound = true;
							}
						} else {
							result = Math.min(result, VMagPU);
							minFound = true;
						}
					}
				}
		}

		result = result * 0.001;

		if (!minFound)
			result = -1.0;

		return result;
	}

	public static Complex getTotalPowerFromSources() {
		Circuit ckt = DSS.activeCircuit;

		Complex result = Complex.ZERO;

		for (CktElement pElem : ckt.getSources())
			result = result.add( pElem.getPower(0).negate() );

		return result;
	}


	/**
	 * Distribute the generators uniformly amongst the feeder nodes that have loads.
	 */
	private static void writeUniformGenerators(PrintWriter pw, double kW, double PF) {
		LoadObj pLoad;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");
		int count = loadClass.getElementList().size();

		double kWEach = kW / Math.max(1.0, Math.round(count));
		if (ckt.isPositiveSequence())
			kWEach = kWEach / 3.0;

		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled()) {
				pw.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
				pw.printf(" phases=%d kV=%-g", pLoad.getNumPhases(), pLoad.getKVLoadBase());
				pw.printf(" kW=%-g", kWEach);
				pw.printf(" PF=%-.3g", PF);
				pw.print(" model=1");
				pw.println();
			}
		}
	}

	/**
	 * Distribute generators randomly to loaded buses.
	 */
	private static void writeRandomGenerators(PrintWriter pw, double kW, double PF) {
		LoadObj pLoad;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");

		int count = loadClass.getElementList().size();

		/* Count enabled loads */
		int loadCount = 0;
		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				loadCount += 1;
		}

		double kWEach = kW / loadCount;  // median sized generator
		if (ckt.isPositiveSequence())
			kWEach = kWEach / 3.0;

		/* Place random sizes on load buses so that total is approximately what was spec'd */
		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled()) {
				pw.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
				pw.printf(" phases=%d kV=%-g", pLoad.getNumPhases(), pLoad.getKVLoadBase());
				pw.printf(" kW=%-g", kWEach * Math.random() * 2.0);
				pw.printf(" PF=%-.3g", PF);
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
	private static void writeEveryOtherGenerators(PrintWriter pw, double kW, double PF, int skip) {
		double kWEach;
		LoadObj pLoad;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");
		int count = loadClass.getElementList().size();
		/* Add up the rated load in the enabled loads where gens will be placed */
		double totalKW = 0.0;
		int skipCount = skip;
		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				/* Do not count skipped loads */
				if (skipCount == 0) {
					totalKW = totalKW + pLoad.getKWBase();  // will be right value if pos seq, too
					skipCount = skip;  // start counter over again
				} else {
					skipCount -= 1;
				}
		}

		if (ckt.isPositiveSequence()) {
			kWEach = kW / totalKW / 3.0;
		} else {
			kWEach = kW / totalKW;
		}

		skipCount = skip;
		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				if (skipCount == 0) {
					pw.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
					pw.printf(" phases=%d kV=%-g", pLoad.getNumPhases(), pLoad.getKVLoadBase());
					pw.printf(" kW=%-g ", kWEach * pLoad.getKWBase());
					pw.printf(" PF=%-.3g", PF);
					pw.print(" model=1");
					pw.println();
					skipCount = skip;
				} else {
					skipCount -= 1;
				}
		}
	}

	/**
	 * Distribute the generator proportional to load.
	 */
	private static void writeProportionalGenerators(PrintWriter pw, double kW, double PF) {
		double kWEach;
		LoadObj pLoad;

		Circuit ckt = DSS.activeCircuit;

		DSSClass loadClass = DSSClassDefs.getDSSClass("load");
		int count = loadClass.getElementList().size();

		/* Add up the rated load in the enabled loads */
		double totalKW = 0.0;
		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled())
				totalKW = totalKW + pLoad.getKWBase();  // will be right value if pos seq, too
		}

		if (ckt.isPositiveSequence()) {
			kWEach = kW / totalKW / 3.0;
		} else {
			kWEach = kW / totalKW;
		}

		for (int i = 0; i < count; i++) {
			pLoad = (LoadObj) loadClass.getElementList().get(i);
			if (pLoad.isEnabled()) {
				pw.printf("new generator.DG_%d  bus1=%s", i, pLoad.getBus(0));
				pw.printf(" phases=%d kV=%-g", pLoad.getNumPhases(), pLoad.getKVLoadBase());
				pw.printf(" kW=%-g", kWEach * pLoad.getKWBase());
				pw.printf(" PF=%-.3g", PF);
				pw.print(" model=1");
				pw.println();
			}
		}
	}

	public static void makeDistributedGenerators(double kW, double PF,
			String how, int skip, String fname) {

		FileWriter fw;
		PrintWriter pw;

		/* Write outputfile and then redirect command parser to it. */

		try {
			if (new File(fname).exists())
				DSS.doSimpleMsg("File \""+fname+"\" is about to be overwritten. Rename it now before continuing if you wish to keep it.", 721);
			fw = new FileWriter(fname);
			pw = new PrintWriter(fw);
		} catch (Exception e) {
			DSS.doSimpleMsg("Error opening \"" + fname + "\" for writing. Aborting.", 722);
			return;
		}

		try {
			pw.println("! Created with distribute command:");
			pw.println(String.format("! Distribute kW=%-.6g PF=%-.6g How=%s Skip=%d  file=%s", kW, PF, how, skip, fname));
			pw.println();
			//pw.println("set allowduplicates=yes");
			if (how.length() == 0)
				how = "P";
			switch (how.toUpperCase().charAt(0)) {
			case 'U':
				writeUniformGenerators(pw, kW, PF);
				break;
			case 'R':
				writeRandomGenerators(pw, kW, PF);
				break;
			case 'S':
				writeEveryOtherGenerators(pw, kW, PF, skip);
				break;
			default:
				writeProportionalGenerators(pw, kW, PF);
				break;
			}
			DSS.globalResult = fname;
		} finally {
			pw.println("set allowduplicates=no");
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.close();
		}
	}

	/**
	 * Let EnergyMeter objects control re-enabling of feeders.
	 *
	 * Feeder could have been dumped in meantime by setting Feeder=false in EnergyMeter.
	 */
	public static void enableFeeders() {
		Circuit ckt = DSS.activeCircuit;
		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			pMeter.enableFeeder();
	}

	public static void disableFeeders() {
		Circuit ckt = DSS.activeCircuit;
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

	public static String getDSSArray(int n, double[] dbls) {
		String result = "(";
		for (int i = 0; i < n; i++)
			result = result + String.format(" %-.5g", dbls[i]);
		return result + ")";
	}

	public static String getDSSArray(int n, int[] ints) {
		String result = "(";
		for (int i = 0; i < n; i++)
			result = result + String.format(" %-.d", ints[i]);
		return result + ")";
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

		int result = 0;
		for (int i = 0; i < ckt.getNumDevices(); i++)
			result = Math.max(result, ckt.getCktElements().get(i).getYorder());
		return result;
	}

	/**
	 * To help avoid collisions of neutral numbers, this function returns a
	 * node number that is not being used, starting at the startNode value.
	 */
	public static int getUniqueNodeNumber(String sBusName, int startNode) {
		Circuit ckt = DSS.activeCircuit;

		int result = startNode;
		int iBusIdx = ckt.getBusList().find(sBusName);
		if (iBusIdx >= 0)
			while (ckt.getBus(iBusIdx).findIdx(result) != -1)
				result += 1;
		ckt.getBus(iBusIdx).add(result);  // add it to the list so next call will be unique
		return result;
	}


	public static void showMessageBeep(String s) {
		Toolkit.getDefaultToolkit().beep();
		DSS.forms.infoMessageDlg(s);
	}

	public static boolean isPathBetween(PDElement fromLine, PDElement toLine) {
		PDElement PDElem = fromLine;
		while (PDElem != null) {
			if (PDElem.equals(toLine))
				return true;
			PDElem = PDElem.getParentPDElement();
		}
		return false;
	}

	/**
	 * Trace back up a tree and execute an edit command string.
	 */
	public static void traceAndEdit(PDElement fromLine, PDElement toLine, int nPhases, String editStr) {
		PDElement pLine = fromLine;
		while (pLine != null) {
			if ((pLine.getNumPhases() == nPhases) || (nPhases == 0)) {
				Parser.getInstance().setCmdString(editStr);
				pLine.edit();  // uses parser
			}
			if (pLine.equals(toLine))
				break;
			pLine = pLine.getParentPDElement();
		}
	}

	/**
	 * Trace forward down a tree and generate a script file to change the phase.
	 */
	public static void goForwardAndRephase(PDElement fromLine, String phaseString,
			String editStr, String scriptFileName, boolean transStop) {

		PDElement pPDelem;
		CktElement pShuntObject;
		String s;
		FileWriter fw;
		PrintWriter pw = null;
		String fileName = null;
		int xfmrLevel;

		EnergyMeterObj pMeter = (EnergyMeterObj) fromLine.getMeterObj();

		/* Search for starting line in branchlist */
		pPDelem = (PDElement) pMeter.getBranchList().getFirst();
		while (pPDelem != null) {
			if (fromLine.equals(pPDelem))
				break;
			pPDelem = (PDElement) pMeter.getBranchList().goForward();
		}

		/* Error check */
		if (pPDelem == null) {
			DSS.doSimpleMsg(fromLine.getParentClass().getName() + "." + fromLine.getName() + " not found in meter zone.", 723);
			return;
		}

		try {
			fileName = DSS.DSSDataDirectory + DSS.circuitName_ + scriptFileName;
			DSS.globalResult = fileName;

			fw = new FileWriter(fileName);
			pw = new PrintWriter(fw);

			int i;
			pMeter.getBranchList().startHere();
			pPDelem = (PDElement) pMeter.getBranchList().goForward();

			while (pPDelem != null) {
				s = "edit " + pPDelem.getParentClass().getName() + "." + pPDelem.getName();

				/* ----------------LINES------------------------------------- */

				if (isLineElement(pPDelem)) {

					for (i = 0; i < pPDelem.getNumTerms(); i++) {
						s = s + String.format(" Bus%d=%s%s", i, stripExtension(pPDelem.getBus(i)), phaseString);
						//Parser.getInstance().setCmdString(String.format("Bus$d=%s%s", i, StripExtension(pPDelem.getBus(i)), PhaseString));
						//pPDelem.edit();
					}

					/* When we're done with that, we'll send the edit string */
					if (editStr.length() > 0) {
						s = s + "  " + editStr;
						//Parser.getInstance().setCmdString(editStr);
						//pPDelem.edit();   // uses parser
					}

					pw.println(s);

					/* Now get all shunt objects connected to this branch */
					pShuntObject = (CktElement) pMeter.getBranchList().getFirstObject();
					while (pShuntObject != null) {
						/* 1st terminal only */
						i = 0;
						s = "edit " + pShuntObject.getParentClass().getName() + "." + pShuntObject.getName();
						s = s + String.format(" Bus%d=%s%s", i, stripExtension(pShuntObject.getBus(i)), phaseString);
						if (editStr.length() > 0)
							s = s + "  " + editStr;
						pw.println(s);
						//Parser.getInstance().setCmdString(String.format("Bus$d=%s%s", i, stripExtension(pShuntObject.getBus(0)), PhaseString));
						//pShuntObject.edit();
						pShuntObject = (CktElement) pMeter.getBranchList().getNextObject();
					}

					pPDelem = (PDElement) pMeter.getBranchList().goForward();
				} // isLine

				/* ----------------TRANSFORMERS------------------------------ */

				else if (isTransformerElement(pPDelem)) {

					/* We'll stop at transformers and change only the primary winding.
					 * Then we'll cycle forward until the lexical level is less or we're done
					 */
					xfmrLevel = pMeter.getBranchList().getLevel();
					s = s + String.format(" wdg=1 Bus=%s%s  %s", stripExtension(pPDelem.getBus(0)), phaseString, editStr);
					if (!transStop)
						s = s + String.format(" wdg=2 Bus=%s%s  %s", stripExtension(pPDelem.getBus(1)), phaseString, editStr);
					pw.println(s);

					/* Be default go forward in the tree until we bounce back up to a line section above the transformer */
					if (transStop) {
						while ((pPDelem != null) && (pMeter.getBranchList().getLevel() > xfmrLevel)) {
							pPDelem = (PDElement) pMeter.getBranchList().goForward();
						}
					}
				} else {
					// then we get lines and loads beyond transformer
					pPDelem = (PDElement) pMeter.getBranchList().goForward();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
			fireOffEditor(fileName);
		}
	}

	/**
	 * Returns max value of an array of doubles.
	 */
	public static double maxDblArrayValue(int npts, double[] dbls) {
		if (npts == 0)
			return 0.0;

		double result = dbls[0];
		for (int i = 1; i < npts; i++)
			result = Math.max(result, dbls[i]);

		return result;
	}

	/**
	 * Returns index of max array value in abs value.
	 */
	public static int iMaxAbsdblArrayValue(int npts, double[] dbls) {
		if (npts == 0)
			return -1;

		int result = 0;
		double maxValue = Math.abs(dbls[0]);
		for (int i = 1; i < npts; i++)
			if (Math.abs(dbls[i]) > maxValue) {
				maxValue = Math.abs(dbls[i]);
				result = i;  // save index
			}
		return result;
	}

	public static int interpretLoadShapeClass(String s) {
		String ss = s.toLowerCase();
		int result = DSS.USENONE;

		switch (ss.charAt(0)) {
		case 'd':
			switch (ss.charAt(1)) {
			case 'a':
				result = DSS.USEDAILY;
				break;
			case 'u':
				result = DSS.USEDUTY;
				break;
			}
		case 'y':
			result = DSS.USEYEARLY;
			break;
		case 'n':
			result = DSS.USENONE;
			break;
		}
		return result;
	}

	public static int interpretEarthModel(String s) {
		String ss = s.toLowerCase();
		int result = DSS.SIMPLECARSON;
		switch (ss.charAt(0)) {
		case 'c':
			result = DSS.SIMPLECARSON;
			break;
		case 'f':
			result = DSS.FULLCARSON;
			break;
		case 'd':
			result = DSS.DERI;
			break;
		}
		return result;
	}

	public static String getActiveLoadShapeClass() {
		switch (DSS.activeCircuit.getActiveLoadShapeClass()) {
		case DSS.USEDAILY:
			return "Daily";
		case DSS.USEYEARLY:
			return "Yearly";
		case DSS.USEDUTY:
			return "Duty";
		case DSS.USENONE:
			return "None";
		default:
			return "None";
		}
	}

	public static String getEarthModel(int n) {
		switch (n) {
		case DSS.SIMPLECARSON:
			return "Carson";
		case DSS.FULLCARSON:
			return "FullCarson";
		case DSS.DERI:
			return "Deri";
		default:
			return "Carson";
		}
	}

	public static int interpretColorName(String s) {

		int result = 16711680;  // default color
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
			DSS.doSimpleMsg("Invalid Color Specification: \"" + s + "\".", 724);
		}
		return result;
	}

	public static Color interpretColor(String s) {
		throw new UnsupportedOperationException();
	}

	public static String makeNewCktElemName(final String oldName) {
		DSS.setObject(oldName);  // set object active
		DSSObject obj = DSS.activeDSSObject;
		return String.format("%s.%s%d", obj.getParentClass().getName(),
				obj.getParentClass().getName().substring(0, 3),
				obj.getClassIndex());
	}

	private static void renameCktElem(CktElement pElem) {
		pElem.setName( String.format("%s%d",
				pElem.getParentClass().getName().substring(0, 3),
				pElem.getClassIndex()) );
		// make a new device list corresponding to the CktElements list
		DSS.activeCircuit.getDeviceList().add(pElem.getName());
		pElem.setChecked(true);
	}

	/**
	 * Rename Buses and element names to generic names to remove identifiable
	 * names.
	 */
	public static void obfuscate() {
		int i, bref;
		int dotpos;
		int devListSize;
		HashList tempBusList;
		CktElement pCtrlElem;
		String s, nodes;
		String oldBusName;
		String newBusName;
		int baseClass;
		int elemClass;
		List<String> controlUpDateStrings;
		List<CktElement> controlUpDatePtrs;

		Parser parser = Parser.getInstance();

		/* Make sure buslist exists */

		if (DSS.activeCircuit == null)
			return;
		if (DSS.activeCircuit.getBusList().listSize() <= 0)
			return;

		Circuit ckt = DSS.activeCircuit;

		tempBusList = new HashListImpl(ckt.getBusList().listSize());

		/* Rename Buses */
		for (i = 0; i < ckt.getBusList().listSize(); i++)
			tempBusList.add(String.format("B_%d", i));

		ckt.setBusList(null);
		ckt.setBusList(tempBusList);  // reassign

		/* Rename the bus names in each circuit element before renaming the
		 * elements */
		for (CktElement pCktElem : ckt.getCktElements()) {
			baseClass = (pCktElem.getDSSObjType() & DSSClassDefs.BASECLASSMASK);
			if ((baseClass == DSSClassDefs.PC_ELEMENT) ||
					(baseClass == DSSClassDefs.PD_ELEMENT)) {
				s = "";
				for (i = 0; i < pCktElem.getNumTerms(); i++) {
					oldBusName = pCktElem.getBus(i);
					dotpos     = oldBusName.indexOf('.');
					if (dotpos == -1) {
						nodes = "";
					} else {
						// preserve node designations if any
						nodes = oldBusName.substring(dotpos);
					}
					bref  = pCktElem.getTerminal(i).getBusRef();
					newBusName = String.format("B_%d%s", bref, nodes);
					// check for transformer because that will be an exception
					switch (pCktElem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
					case DSSClassDefs.XFMR_ELEMENT:
						s = s + String.format("Wdg=%d Bus=%s ", i, newBusName);
						break;
					default:
						s = s + String.format("Bus%d=%s ", i, newBusName);
						break;
					}
				}
				parser.setCmdString(s);
				pCktElem.edit();
			}
		}

		/* Rename the circuit elements to generic values */
		/* Have to catch the control elements and edit some of their
		 * parameters */

		/* First, make scripts to change the monitored element names in the
		 * controls to what they will be */
		controlUpDateStrings = new ArrayList<String>();
		controlUpDatePtrs    = new ArrayList<CktElement>();

		for (CktElement pCktElem : ckt.getCktElements()) {
			switch (pCktElem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
			case DSSClassDefs.CAP_CONTROL:
				s = String.format("Element=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0)));
				controlUpDateStrings.add (s + String.format("Capacitor=%s ",
						makeNewCktElemName("capacitor." + pCktElem.getPropertyValue(2)).substring(10, 99)));
				controlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.REG_CONTROL:
				// handled below
				break;
			case DSSClassDefs.RELAY_CONTROL:
				s = String.format("MonitoredObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0)));
				controlUpDateStrings.add ( s + String.format("SwitchedObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(2))));
				controlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.RECLOSER_CONTROL:
				s = String.format("MonitoredObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0)));
				controlUpDateStrings.add ( s + String.format("SwitchedObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(2))));
				controlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.FUSE_CONTROL:
				s = String.format("MonitoredObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0)));
				controlUpDateStrings.add ( s + String.format("SwitchedObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(2))));
				controlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.GEN_CONTROL:
				controlUpDateStrings.add (String.format("Element=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0))));
				controlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.STORAGE_CONTROL:
				controlUpDateStrings.add (String.format("Element=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0))));
				controlUpDatePtrs.add(pCktElem);
				break;
			case DSSClassDefs.SWT_CONTROL:
				controlUpDateStrings.add (String.format("SwitchedObj=%s ",
						makeNewCktElemName(pCktElem.getPropertyValue(0))));
				controlUpDatePtrs.add(pCktElem);
				break;
			}
		}

		for (CktElement pCktElem : ckt.getCktElements())
			pCktElem.setChecked(false);  // initialize to not checked

		devListSize = ckt.getDeviceList().listSize();
		ckt.setDeviceList(null);
		ckt.setDeviceList( new HashListImpl(devListSize) );

		for (CktElement pCktElem : ckt.getCktElements()) {
			if (!pCktElem.isChecked()) {
				elemClass = (pCktElem.getDSSObjType() & DSSClassDefs.CLASSMASK);
				renameCktElem(pCktElem);
				switch (elemClass) {
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
		for (i = 0; i < controlUpDatePtrs.size() - 1; i++) {
			pCktElem         = controlUpDatePtrs.get(i);
			parser.setCmdString( controlUpDateStrings.get(i) );
			pCktElem.edit();
		}

		controlUpDateStrings = null;
		controlUpDatePtrs = null;
	}

}
