/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.executive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Solution;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.Util;
import com.ncond.dss.common.exceptions.SolverError;
import com.ncond.dss.common.types.Connection;
import com.ncond.dss.common.types.Distribution;
import com.ncond.dss.common.types.ReductionStrategy;
import com.ncond.dss.common.types.SolutionAlgs;
import com.ncond.dss.common.types.SolutionMode;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.Load;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.CapacitorObj;
import com.ncond.dss.delivery.Line;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.ReactorObj;
import com.ncond.dss.general.DSSObject;
import com.ncond.dss.general.LoadShape;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.NamedObject;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.meter.MonitorObj;
import com.ncond.dss.meter.SensorObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.plot.DSSGraphDeclarations;
import com.ncond.dss.plot.DSSPlot;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.MathUtil;
import com.ncond.dss.shared.PstCalc;

public class ExecHelper {

	private static CommandList saveCommands = new CommandList(new String[] {"class", "file", "dir", "keepdisabled"}, true);
	private static CommandList distributeCommands = new CommandList(new String[] {"kW", "how", "skip", "pf", "file", "MW"}, true);
	private static CommandList DI_PlotCommands = new CommandList(new String[] {"case", "year", "registers", "peak", "meter"});
	private static CommandList reconductorCommands = new CommandList(new String[] {"Line1", "Line2", "LineCode", "Geometry", "EditString"}, true);
	private static CommandList rephaseCommands = new CommandList(new String[] {"StartLine", "PhaseDesignation", "EditString", "ScriptFileName", "StopAtTransformers"}, true);
	private static CommandList addMarkerCommands = new CommandList(new String[] {"Bus", "code", "color", "size"}, true);
	private static CommandList setBusXYCommands = new CommandList(new String[] {"Bus", "x", "y"}, true);
	private static CommandList pstCalcCommands = new CommandList(new String[] {"Npts", "Voltages", "dt", "Frequency", "lamp"}, true);

	private ExecHelper() {}

	/**
	 * Looks for object definition:
	 *
	 *   paramName = 'object' if given
	 *   and the name of the object
	 *
	 *   object=capacitor.C1
	 *   or just capacitor.C1
	 *
	 * If no dot, last class is assumed.
	 */
	public static void getObjClassAndName(String[] objClass, String[] objName) {
		String paramName, param;
		Parser parser = Parser.getInstance();

		paramName = parser.getNextParam().toLowerCase();
		param = parser.makeString();
		if (paramName.length() > 0) {  // if specified, must be object or an abbreviation.
			if (Util.compareTextShortest(paramName, "object") != 0) {
				DSS.doSimpleMsg("object=class.name expected as first parameter in command." +
						DSS.CRLF + parser.getCmdBuffer(), 240);
				return;
			}
		}

		Util.parseObjectClassandName(param, objClass, objName);
	}

	/**
	 * Process the "new" command
	 *
	 *     new type=xxxx name=xxxx editstring
	 *
	 * If the device being added already exists, the default behaviour is to
	 * treat the "new" command as an "edit" command.  This may be overridden
	 * by setting the duplicatesAllowed variable to true, in which case,
	 * the "new" command always results in a new device being added.
	 */
	public static int doNewCmd() {
		String[] objClass = new String[1];
		String[] objName = new String[1];
		int handle = -1;
		int result = 0;

		getObjClassAndName(objClass, objName);

		if (objClass[0].equalsIgnoreCase("solution")) {
			DSS.doSimpleMsg("You cannot create new Solution objects through the command interface.", 241);
			return result;
		}

		if (objClass[0].equalsIgnoreCase("circuit")) {
			DSS.makeNewCircuit(objName[0]);
			Util.clearEventLog();  // start the event log in the current directory
		} else {
			// everything else must be a circuit element or DSS object
			handle = addObject(objClass[0], objName[0]);
		}

		if (handle == -1) result = 1;

		return result;
	}

	/**
	 * edit type=xxxx name=xxxx editstring
	 */
	public static int doEditCmd() {
		String[] objType = new String[0];
		String[] objName = new String[0];
		int success = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			// everything else must be a circuit element
			success = editObject(objType[0], objName[0]);
		}

		return success;
	}

	/**
	 * batchedit type=xxxx name=pattern editstring
	 */
	public static int doBatchEditCmd() {
		String[] objType = new String[1];
		String[] pattern = new String[1];
		Pattern regEx1;
		Matcher matcher;
		DSSObject obj;
		int params;
		int success = 0;

		Parser parser = Parser.getInstance();

		getObjClassAndName(objType, pattern);
		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			DSS.lastClassReferenced = DSS.classNames.find(objType[0]);

			switch (DSS.lastClassReferenced) {
			case -1:
				DSS.doSimpleMsg("BatchEdit command: Object type \"" + objType[0] +
					"\" not found."+ DSS.CRLF + parser.getCmdBuffer(), 267);
				return success;
			default:
				params = parser.getPosition();
				DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
				regEx1 = Pattern.compile(pattern[0], Pattern.CASE_INSENSITIVE);
				DSS.activeDSSClass.getFirst();
				obj = DSS.activeDSSClass.getActiveObj();
				while (obj != null) {
					matcher = regEx1.matcher(obj.getName());
					if (matcher.find()) {
						parser.setPosition(params);
						DSS.activeDSSClass.edit();
					}
					DSS.activeDSSClass.getNext();
					obj = DSS.activeDSSClass.getActiveObj();
				}
			}
		}
		return success;
	}

	/**
	 * This routine should be recursive.
	 * So you can redirect input an arbitrary number of times.
	 *
	 * If isCompile, makes directory of the file the new home directory.
	 * If not isCompile (is simple redirect), return to where we started.
	 */
	public static int doRedirect(boolean isCompile) {
		FileReader fr;
		BufferedReader br;
		String inputLine, currDir = "", saveDir;
		int success = 0;

		// get next parm and try to interpret as a file name
		Parser.getInstance().getNextParam();
		ExecCommands.redirFile = Util.expandFileName(Parser.getInstance().makeString());

		if (!ExecCommands.redirFile.equals("")) {
			saveDir = DSS.currentDirectory;

			try {
				fr = new FileReader(ExecCommands.redirFile);
				if (isCompile) DSS.lastFileCompiled = ExecCommands.redirFile;
			} catch (FileNotFoundException e) {
				// couldn't find file; try appending '.dss' to the file name
				// if it doesn't already have an extension
				if (ExecCommands.redirFile.indexOf('.') == -1) {
					ExecCommands.redirFile = ExecCommands.redirFile + ".dss";
					try {
						fr = new FileReader(ExecCommands.redirFile);
					} catch (FileNotFoundException ex) {
						DSS.doSimpleMsg("Redirect file: \"" + ExecCommands.redirFile + "\" not found.", 242);
						DSS.solutionAbort = true;
						return success;
					}
				} else {
					DSS.doSimpleMsg("Redirect file: \"" + ExecCommands.redirFile + "\" not found.", 243);
					DSS.solutionAbort = true;
					return success;  // already had an extension
				}
			}

			try {
				// change directory to path specified by file in case that loads in more files
				currDir = Util.extractFileDir(ExecCommands.redirFile);
				DSS.currentDirectory = currDir;
				if (isCompile) DSS.setDataPath(currDir);

				DSS.redirectAbort = false;
				DSS.inRedirect = true;

				br = new BufferedReader(fr);

				while ((inputLine = br.readLine()) != null || DSS.redirectAbort) {
					if (!DSS.solutionAbort) {
						ExecCommands.processCommand(inputLine);
					} else {
						DSS.redirectAbort = true;  // abort file if solution was aborted
					}
				}

				if (DSS.activeCircuit != null)
					DSS.activeCircuit.setCurrentDirectory(currDir + DSS.SEPARATOR);

				br.close();
				fr.close();
			} catch (IOException e) {
				DSS.doErrorMsg("DoRedirect" + DSS.CRLF + "Error processing input stream in Compile/Redirect.",
						e.getMessage(),
						"Error in file: \"" + ExecCommands.redirFile + "\" or filename.", 244);
			} finally {
				DSS.inRedirect = false;
				if (isCompile) {
					DSS.setDataPath(currDir);  // change DSSDataDirectory
					DSS.lastCommandWasCompile = true;
				} else {
					// set back to where we were for redirect, but not compile
					DSS.currentDirectory = saveDir;
				}
			}
		}  // else ignore altogether if null filename

		return success;
	}

	/**
	 * Select active object.
	 *
	 *   select element=elementname terminal=terminalnumber
	 */
	public static int doSelectCmd() {
		String[] objClass = new String[1];
		String[] objName = new String[1];
		String param;
		int success = 1;
		Circuit ckt = DSS.activeCircuit;

		getObjClassAndName(objClass, objName);  // parse object class and name

		if (objClass[0].length() == 0 && objName[0].length() == 0)
			return success;  // select active obj if any

		if (objClass[0].equalsIgnoreCase("circuit")) {
			setActiveCircuit(objName[0]);
		} else {
			// everything else must be a circuit element
			if (objClass[0].length() > 0)
				DSSClassDefs.setObjectClass(objClass[0]);

			DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);

			if (DSS.activeDSSClass != null) {
				if (!DSS.activeDSSClass.setActive(objName[0])) {
					// scroll through list of objects until a match
					DSS.doSimpleMsg("Object \"" + objName[0] + "\" not found." +
							DSS.CRLF + Parser.getInstance().getCmdBuffer(), 245);
					success = 0;
				} else {
					switch (DSS.activeDSSObject.getObjType()) {
					case DSSClassDefs.DSS_OBJECT:
						// do nothing for general DSS object
						break;
					default:  // for circuit types set activeCircuit element too
						ckt.setActiveCktElement((CktElement) DSS.activeDSSClass.getActiveObj());
						// now check for active terminal designation
						Parser.getInstance().getNextParam().toLowerCase();
						param = Parser.getInstance().makeString();
						if (param.length() > 0) {
							ckt.getActiveCktElement().setActiveTerminalIdx(Parser.getInstance().makeInteger() - 1);
						} else {
							ckt.getActiveCktElement().setActiveTerminalIdx(0);
						}
						DSS.setActiveBus(ckt.getActiveCktElement().getBus(ckt.getActiveCktElement().getActiveTerminalIdx()));
						break;
					}
				}
			} else {
				DSS.doSimpleMsg("Active object type/class is not set.", 246);
				success = 0;
			}
		}

		return success;
	}

	/**
	 * More edit string (assumes active circuit element).
	 */
	public static int doMoreCmd() {
		if (DSS.activeDSSClass != null) {
			return DSS.activeDSSClass.edit();
		} else {
			return 0;
		}
	}

	/**
	 * Save current values in both monitors and meters.
	 */
	public static int doSaveCmd() {
		int paramPointer;
		String paramName, param;
		String objClass;
		String saveDir;
		String saveFile;
		DSSClass cls;

		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		int result = 0;
		objClass = "";
		saveDir = "";
		saveFile = "";

		paramPointer = -1;
		paramName = parser.getNextParam();
		param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = saveCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				objClass = parser.makeString();
				break;
			case 1:
				saveFile = parser.makeString();   // file name for saving a class
				break;
			case 2:
				saveDir = parser.makeString();
				break;
			default:
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		DSS.inShowResults = true;

		if (objClass.length() == 0 || Util.compareTextShortest(objClass, "meters") == 0) {
			// save monitors and meters
			for (MonitorObj mon : ckt.getMonitors()) {
				mon.save();
			}
			for (EnergyMeterObj mtr : ckt.getEnergyMeters()) {
				mtr.saveRegisters();
			}
			return result;
		}

		if (Util.compareTextShortest(objClass, "circuit") == 0) {
			if (!ckt.save(saveDir)) result = 1;
			return result;
		}
		if (Util.compareTextShortest(objClass, "voltages") == 0) {
			ckt.getSolution().saveVoltages();
			return result;
		}

		/* Assume that we have a class name for a DSS Class */
		cls = DSSClassDefs.getDSSClass(objClass);
		if (cls != null) {
			if (saveFile.length() == 0) saveFile = objClass;
			if (saveDir.length() > 0) {
				if (! new File(saveDir).exists()) {
					try {
						new File(saveDir).mkdir();
					} catch (SecurityException e) {
						DSS.doSimpleMsg("Error making directory: \""+saveDir+"\". " + e.getMessage(), 247);
					}
				}
				saveFile = saveDir + DSS.SEPARATOR + saveFile;
			}
			Util.writeClassFile(cls, saveFile, false);  // just write the class with no checks
		}

		DSS.lastResultFile = saveFile;
		DSS.globalResult = saveFile;

		return result;
	}

	public static int doClearCmd() {
		Executive.getInstance().clear();
		return 0;
	}

	public static int doHelpCmd() {
		DSS.forms.showHelpForm();
		return 0;
	}

	/**
	 * Force all monitors and meters in active circuit to take a sample.
	 */
	public static int doSampleCmd() {
		DSS.monitorClass.sampleAll();
		DSS.energyMeterClass.sampleAll();  // gets generators too
		return 0;
	}

	public static int doSolveCmd() {
		// just invoke solution obj's editor to pick up parsing
		// and execute rest of command
		Solution.activeSolutionObj = DSS.activeCircuit.getSolution();
		return DSS.solutionClass.edit();
	}

	/**
	 * Parses the object off the line and sets it active as a CktElement.
	 */
	public static int setActiveCktElement() {
		String[] objType = new String[1];
		String[] objName = new String[1];
		int success = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			if (!objType[0].equalsIgnoreCase(DSS.activeDSSClass.getClassName())) {
				DSS.lastClassReferenced = DSS.classNames.find(objType[0]);

				switch (DSS.lastClassReferenced) {
				case -1:
					DSS.doSimpleMsg("Object type \"" + objType[0] + "\" not found." +
						DSS.CRLF + Parser.getInstance().getCmdBuffer(), 253);
					return success;
				default:
					// intrinsic and user defined models
					DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
					if (DSS.activeDSSClass.setActive(objName[0])) {
						// scroll through list of objects until a match
						switch (DSS.activeDSSObject.getObjType()) {
						case DSSClassDefs.DSS_OBJECT:
							DSS.doSimpleMsg("Error in setActiveCktElement: Object not a circuit element." +
									DSS.CRLF + Parser.getInstance().getCmdBuffer(), 254);
							break;
						default:
							DSS.activeCircuit.setActiveCktElement((CktElement) DSS.activeDSSClass.getActiveObj());
							success = 1;
						}
					}
					break;
				}
			}
		}

		return success;
	}

	public static int doEnableCmd() {
		String[] objType = new String[1];
		String[] objName = new String[1];
		DSSClass cls;
		CktElement elem;
		int i, success = 0;

		//result = setActiveCktElement();
		//if (result >= 0) DSSGlobals.activeCircuit.getActiveCktElement().setEnabled(true);

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			if (objType[0].length() > 0) {
				// only applies to CktElementClass objects
				cls = DSSClassDefs.getDSSClass(objType[0]);
				if (cls != null) {
					if ((cls.getClassType() & DSSClassDefs.BASECLASSMASK) > 0) {
						// everything else must be a circuit element
						if (objName[0].equals("*")) {
							// enable all elements of this class
							for (i = 0; i < cls.getElementCount(); i++) {
								elem = (CktElement) cls.getElementList().get(i);
								elem.setEnabled(true);
							}
						} else {
							// just load up the parser and call the edit routine for the object in question
							Parser.getInstance().setCmdBuffer("enabled=true");  // will only work for CktElements
							success = editObject(objType[0], objName[0]);
						}
					}

				}
			}
		}

		return success;
	}

	public static int doDisableCmd() {
		String[] objType = new String[1];
		String[] objName = new String[1];
		DSSClass cls;
		CktElement elem;
		int i, success = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			if (objType[0].length() > 0) {
				// only applies to CktElementClass objects
				cls = DSSClassDefs.getDSSClass(objType[0]);
				if (cls != null) {
					if ((cls.getClassType() & DSSClassDefs.BASECLASSMASK) > 0) {
						// everything else must be a circuit element
						if (objName[0].equals("*")) {
							// disable all elements of this class
							for (i = 0; i < cls.getElementCount(); i++) {
								elem = (CktElement) cls.getElementList().get(i);
								elem.setEnabled(false);
							}
						}
					} else {
						// just load up the parser and call the edit routine for the object in question
						Parser.getInstance().setCmdBuffer("enabled=false");  // will only work for CktElements
						success = editObject(objType[0], objName[0]);
					}

				}
			}
		}

		//result = setActiveCktElement();
		//if (result > 0) getActiveCircuit().getActiveCktElement().setEnabled(false);

		return success;
	}

	public static int doPropertyDump() {
		FileOutputStream fos;
		boolean singleObject, debugDump, isSolution;
		int i, result = 0;
		String fileName = "";
		@SuppressWarnings("unused") String paramName;
		String param, param2;
		String[] objClass = new String[1];
		String[] objName = new String[1];

		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		singleObject = false;
		isSolution = false;
		debugDump = false;
		objClass[0] = " ";  // make sure these have at least one character
		objName[0] = " ";

		// continue parsing command line - check for object name
		paramName = parser.getNextParam();
		param = parser.makeString();
		if (param.length() > 0) {
			if (param.equalsIgnoreCase("commands")) {
				if (!DSS.noFormsAllowed) {
					Util.dumpAllDSSCommands(fileName);
					Util.fireOffEditor(fileName);
					return result;
				}
			}

			/* dump bus names hash list */
			if (param.equalsIgnoreCase("buslist")) {
				if (!DSS.noFormsAllowed) {
					fileName = DSS.dataDirectory + "BusHashList.txt";
					ckt.getBusList().dumpToFile(fileName);
					Util.fireOffEditor(fileName);
					return result;
				}
			}

			/* dump device names hash list */
			if (param.equalsIgnoreCase("devicelist")) {
				if (!DSS.noFormsAllowed) {
					fileName = DSS.dataDirectory + "DeviceHashList.txt";
					ckt.getDeviceList().dumpToFile(fileName);
					Util.fireOffEditor(fileName);
					return result;
				}
			}

			if (param.toLowerCase().startsWith("alloc")) {
				fileName = DSS.dataDirectory + "AllocationFactors.txt";
				Util.dumpAllocationFactors(fileName);
				Util.fireOffEditor(fileName);
				return result;
			}

			if (param.equalsIgnoreCase("debug")) {
				debugDump = true;
			} else {
				if (param.equalsIgnoreCase("solution")) {
					// assume active circuit solution if not qualified
					DSS.activeDSSClass = DSS.solutionClass;
					DSS.activeDSSObject = ckt.getSolution();
					isSolution = true;
				} else {
					singleObject = true;
					// check to see if we want a debug dump on this object
					paramName = parser.getNextParam();
					param2 = parser.makeString();
					if (param2.equalsIgnoreCase("debug")) debugDump = true;
					// set active element to be value in param
					parser.setCmdBuffer("\"" + param + "\"");  // put param back into parser
					getObjClassAndName(objClass, objName);
					//if (doSelectCmd == 0) return result;
					if (DSSClassDefs.setObjectClass(objClass[0])) {
						DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
						if (DSS.activeDSSClass == null) return result;
					} else {
						return result;
					}
				}
			}
		}

		try {
			fos = new FileOutputStream(DSS.dataDirectory + DSS.circuitName_ + "PropertyDump.txt");
		} catch (IOException e) {
			DSS.doErrorMsg("Error opening " + DSS.dataDirectory + " PropertyDump.txt for writing in " +
					DSS.currentDirectory,
					e.getMessage(), "Disk protected or other file error", 255);
			return result;
		}

		try {
			if (singleObject) {
				/* if objName == "*" then we dump all objects of this class */
				switch (objName[0].charAt(0)) {
				case '*':
					for (i = 0; i < DSS.activeDSSClass.getElementCount(); i++) {
						DSS.activeDSSClass.setActiveElement(i);
						DSS.activeDSSObject.dumpProperties(fos, debugDump);
					}
					break;
				default:
					if (!DSS.activeDSSClass.setActive(objName[0])) {
						DSS.doSimpleMsg("Object \"" + objName[0] + "\" not found.", 256);
						return result;
					} else {
						DSS.activeDSSObject.dumpProperties(fos, debugDump);  // Dump only properties of active circuit element
					}
					break;
				}
			} else if (isSolution) {
				DSS.activeDSSObject.dumpProperties(fos, debugDump);
			} else {
				// dump general circuit stuff
				if (debugDump) ckt.debugDump(fos);
				// dump circuit objects
				try {
					for (CktElement elem : ckt.getCktElements()) {
						elem.dumpProperties(fos, debugDump);
					}

					for (DSSObject obj : DSS.DSSObjs) {
						obj.dumpProperties(fos, debugDump);
					}
				} catch (Exception e) {
					DSS.doErrorMsg("Problem writing file.",
							e.getMessage(),
							"File may be read only, in use, or disk full.", 257);
				}
			}

			ckt.getSolution().dumpProperties(fos, debugDump);

			fos.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing property dump.", -1);
		}

		Util.fireOffEditor(DSS.dataDirectory + DSS.circuitName_ + "PropertyDump.txt");

		return result;
	}

	/** For interpreting time specified as an array "hour, sec". */
	public static void setTime() {
		double[] timeArray = new double[2];
		Parser.getInstance().parseAsVector(2, timeArray);

		SolutionObj solution = DSS.activeCircuit.getSolution();

		solution.setIntHour((int) Math.round(timeArray[0]));
		solution.getDynaVars().t = timeArray[1];
		solution.updateDblHour();
	}

	public static void setActiveCircuit(String cktName) {
		for (Circuit ckt : DSS.circuits) {
			if (ckt.getName().equalsIgnoreCase(cktName)) {
				DSS.activeCircuit = ckt;
				return;
			}
		}

		DSS.doSimpleMsg("Error: No circuit named \"" + cktName + "\" found." +
				DSS.CRLF + "Active circuit not changed.", 258);
	}

	public static void doLegalVoltageBases() {
		Circuit ckt = DSS.activeCircuit;
		double[] dummy = new double[100];  // big buffer

		int num = Parser.getInstance().parseAsVector(100, dummy);
		/* Parsing zero-fills the array */

		/* LegalVoltageBases is a zero-terminated array, so we have to allocate
		 * one more than the number of actual values */
		ckt.setLegalVoltageBases(new double[num + 1]);
		for (int i = 0; i < num + 1; i++)
			ckt.getLegalVoltageBases()[i] = dummy[i];
	}

	/**
	 * Opens a terminal and conductor of a ckt element.
	 *
	 * Syntax: "open class.name term=xx cond=xx"
	 * If cond is omitted, all conductors are opened.
	 */
	public static int doOpenCmd() {
		int terminal;
		int conductor;
		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		int retval = setActiveCktElement();
		if (retval > 0) {
			parser.getNextParam();
			terminal = parser.makeInteger();
			parser.getNextParam();
			conductor = parser.makeInteger();

			ckt.getActiveCktElement().setActiveTerminalIdx(terminal);
			ckt.getActiveCktElement().setConductorClosed(conductor, false);
			DSS.setActiveBus(Util.stripExtension(ckt.getActiveCktElement().getBus(ckt.getActiveCktElement().getActiveTerminalIdx())));
		} else {
			DSS.doSimpleMsg("Error in Open command: Circuit element not found." +
					DSS.CRLF + parser.getCmdBuffer(), 259);
		}

		return 0;
	}

	/**
	 * Closes a terminal and conductor of a ckt element.
	 *
	 * Syntax: "close class.name term=xx cond=xx"
	 * If cond is omitted, all conductors are opened.
	 */
	public static int doCloseCmd() {
		int terminal;
		int conductor;
		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		int retval = setActiveCktElement();
		if (retval > 0) {
			parser.getNextParam();
			terminal = parser.makeInteger();
			parser.getNextParam();
			conductor = parser.makeInteger();

			ckt.getActiveCktElement().setActiveTerminalIdx(terminal);
			ckt.getActiveCktElement().setConductorClosed(conductor, true);
			DSS.setActiveBus(Util.stripExtension(ckt.getActiveCktElement().getBus(ckt.getActiveCktElement().getActiveTerminalIdx())));
		} else {
			DSS.doSimpleMsg("Error in Close command circuit element not found." +
					DSS.CRLF + parser.getCmdBuffer(), 260);
		}
		return 0;
	}

	public static int doResetCmd() {
		String param;
		Parser parser = Parser.getInstance();

		// get next parm and try to interpret as a file name
		parser.getNextParam();
		param = parser.makeString().toUpperCase();

		if (param.length() == 0) {
			doResetMonitors();
			doResetMeters();
			Util.doResetFaults();
			Util.doResetControls();
			Util.clearEventLog();
			Util.doResetKeepList();
		} else {
			switch (param.charAt(0)) {
			case 'M':
				switch (param.charAt(1)) {
				case 'O':  // monitor
					doResetMonitors();
					break;
				case 'E':  // meter
					doResetMeters();
					break;
				}
				break;
			case 'F':  // faults
				Util.doResetFaults();
				break;
			case 'C':  // controls
				Util.doResetControls();
				break;
			case 'E':  // eventLog
				Util.clearEventLog();
				break;
			case 'K':
				Util.doResetKeepList();
				break;
			default:
				DSS.doSimpleMsg("Unknown argument to reset command: \"" + param + "\"", 261);
				break;
			}
		}

		return 0;
	}

	private static void markCapAndReactorBuses() {
		DSSClass cls;
		CapacitorObj capElement;
		ReactorObj reacElement;
		int objRef;

		/* Mark all buses as keepers if there are capacitors or reactors on them */
		cls = DSSClassDefs.getDSSClass("capacitor");
		if (cls != null) {
			objRef = cls.getFirst();
			while (objRef >= 0) {
				capElement = (CapacitorObj) DSS.activeDSSObject;
				if (capElement.isShunt()) {
					if (capElement.isEnabled())
						DSS.activeCircuit.getBus(capElement.getTerminal(0).getBusRef() - 1).setKeep(true);
				}
				objRef = cls.getNext();
			}
		}

		/* Now get the reactors */
		cls = DSSClassDefs.getDSSClass("reactor");
		if (cls != null) {
			objRef = cls.getFirst();
			while (objRef >= 0) {
				reacElement = (ReactorObj) DSS.activeDSSObject;
				if (reacElement.isShunt()) {
					try {
						if (reacElement.isEnabled())
							DSS.activeCircuit.getBus(reacElement.getTerminal(0).getBusRef() - 1).setKeep(true);
					} catch (Exception e) {
						DSS.doSimpleMsg(String.format("%s %s reactor=%s Bus No.=%d ", e.getMessage(), DSS.CRLF,
							reacElement.getName(), reacElement.getNodeRef(0)), 9999);
					}
				}
				objRef = cls.getNext();
			}
		}
	}

	public static int doReduceCmd() {
		String param;
		EnergyMeter meterClass;
		int devClassIndex;

		// get next parm and try to interpret as a file name
		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString().toUpperCase();

		/* Mark capacitor and reactor buses as keep so we don't lose them */
		markCapAndReactorBuses();

		if (param.length() == 0) param = "A";

		switch (param.charAt(0)) {
		case 'A':
			for (EnergyMeterObj mtr : DSS.activeCircuit.getEnergyMeters()) {
				mtr.reduceZone();
			}
			break;
		default:
			/* Reduce a specific meter */
			devClassIndex = DSS.classNames.find("energymeter");
			if (devClassIndex >= 0) {
				meterClass = (EnergyMeter) DSS.DSSClassList.get(devClassIndex);
				if (meterClass.setActive(param)) {   // try to set it active
					EnergyMeterObj MeterObj = (EnergyMeterObj) meterClass.getActiveObj();
					MeterObj.reduceZone();
				} else {
					DSS.doSimpleMsg("EnergyMeter \"" + param + "\" not found.", 262);
				}
			}
			break;
		}
		return 0;
	}

	public static int doResetMonitors() {
		for (MonitorObj mon : DSS.activeCircuit.getMonitors()) {
			mon.resetIt();
		}
		return 0;
	}

	public static int doFileEditCmd() {
		String param;

		// get next parm and try to interpret as a file name
		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString();

		if (new File(param).exists()) {
			Util.fireOffEditor(param);
		} else {
			DSS.globalResult = "File \"" + param + "\" does not exist.";
		}

		return 1;
	}

	/**
	 * Parse strings such as
	 *     1. classname.objectname,property  (full name)
	 *     2. objectname.property            (classname omitted)
	 *     3. property                       (classname and objectname omitted
	 */
	public static void parseObjName(String fullName, String[] objName, String[] propName) {
		int dotpos1, dotpos2;

		dotpos1 = fullName.indexOf(".");
		switch (dotpos1) {
		case -1:
			objName[0] = "";
			propName[0] = fullName;
			break;
		default:
			propName[0] = fullName.substring(dotpos1 + 1, fullName.length());
			dotpos2 = propName[0].indexOf(".");
			switch (dotpos2) {
			case -1:
				objName[0] = fullName.substring(0, dotpos1);
				break;
			default:
				objName[0] = fullName.substring(0, dotpos1 + dotpos2);
				propName[0] = propName[0].substring(dotpos2 + 1, propName[0].length());
				break;
			}
			break;
		}
	}

	/**
	 * ? Command
	 * Syntax: "? Line.Line1.R1"
	 */
	public static int doQueryCmd() {
		String param;
		String[] objName = new String[1];
		String[] propName = new String[1];

		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString();

		parseObjName(param, objName, propName);

		if (objName.toString().equalsIgnoreCase("solution")) {  // special for solution
			DSS.activeDSSClass = DSS.solutionClass;
			DSS.activeDSSObject = DSS.activeCircuit.getSolution();
		} else {
			Parser.getInstance().setCmdBuffer("\"" + objName.toString() + "\"");
			doSelectCmd();  // set object active
		}

		// put property value in global variable
		int propIndex = DSS.activeDSSClass.propertyIndex(propName.toString());
		if (propIndex >= 0) {
			DSS.globalPropertyValue = DSS.activeDSSObject.getPropertyValue(propIndex);
		} else {
			DSS.globalPropertyValue = "property unknown";
		}

		DSS.globalResult = DSS.globalPropertyValue;

		if (DSS.logQueries)
			DSS.writeQueryLogFile(param, DSS.globalResult);  // write time-stamped query

		return 0;
	}

	public static int doResetMeters() {
		DSS.energyMeterClass.resetAll();
		return 0;
	}

	public static int doNextCmd() {
		String param;
		SolutionObj solution;

		// get next parm and try to interpret as a file name
		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString();

		solution = DSS.activeCircuit.getSolution();

		switch (param.toUpperCase().charAt(0)) {
		case 'Y':
			solution.setYear(solution.getYear() + 1);  // year
			break;
		case 'H':
			solution.setIntHour(solution.getIntHour() + 1);  // hour
			break;
		case 'T':
			solution.incrementTime();  // time
			break;
		}

		return 0;
	}

	public static int doSetVoltageBases() throws SolverError {
		DSS.activeCircuit.getSolution().setVoltageBases();
		return 0;
	}

	public static void doAboutBox() {
		if (DSS.noFormsAllowed) return;
		DSS.forms.showAboutBox();
	}

	public static int addObject(String objType, String name) {
		Parser parser = Parser.getInstance();

		int handle = -1;

		// search for class if not already active
		// if nothing specified, lastClassReferenced remains
		if (!objType.equalsIgnoreCase(DSS.activeDSSClass.getClassName())) {
			DSS.lastClassReferenced = DSS.classNames.find(objType);
		}

		switch (DSS.lastClassReferenced) {
		case -1:
			DSS.doSimpleMsg("Object type \"" + objType + "\" not found." +
					DSS.CRLF + parser.getCmdBuffer(), 263);
			return handle;
		default:
			// intrinsic and user defined models
			// make a new circuit element
			DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);

			// name must be supplied
			if (name.length() == 0) {
				DSS.doSimpleMsg("Object name missing" + DSS.CRLF + parser.getCmdBuffer(), 264);
				return handle;
			}

			// now let's make a new object or set an existing one active, whatever the case
			switch (DSS.activeDSSClass.getClassType()) {
			case DSSClassDefs.DSS_OBJECT:
				// these can be added without having an active circuit
				// duplicates not allowed in general DSS objects
				if  (!DSS.activeDSSClass.setActive(name)) {
					handle = DSS.activeDSSClass.newObject(name);

					// stick in object list to keep track of it
					DSS.DSSObjs.add(DSS.activeDSSObject);
				}
				break;
			default:
				// these are circuit elements
				if (DSS.activeCircuit == null) {
					DSS.doSimpleMsg("You must create a circuit first: \"new circuit.cktname\"", 265);
					return handle;
				}

				// if object already exists, treat as an "edit" if duplicates not allowed
				if (DSS.activeCircuit.isDuplicatesAllowed()) {
					handle = DSS.activeDSSClass.newObject(name);  // returns index into this class
					DSS.activeCircuit.addCktElement(handle);  // adds active object to active circuit
				} else {
					// check to see if we can set it active first
					if (!DSS.activeDSSClass.setActive(name)) {
						handle = DSS.activeDSSClass.newObject(name);  // returns index into this class
						DSS.activeCircuit.addCktElement(handle);  // adds active object to active circuit
					} else {
						DSS.doSimpleMsg("Duplicate new element definition: \"" +
							DSS.activeDSSClass.getClassName() + "." + name + "\"" +
							DSS.CRLF + "Element being redefined.", 266);
					}
				}
				break;
			}

			// activeDSSObject now points to the object just added
			// if a circuit element, activeCktElement in activeCircuit is also set
			if (handle >= 0) DSS.activeDSSObject.setClassIndex(handle);

			// process remaining instructions on the command line
			DSS.activeDSSClass.edit();

			break;
		}

		return handle;
	}

	public static int editObject(String objType, String name) {
		int success = 0;
		DSS.lastClassReferenced = DSS.classNames.find(objType);

		switch (DSS.lastClassReferenced) {
		case -1:
			DSS.doSimpleMsg("Object type \"" + objType + "\" not found." +
				DSS.CRLF + Parser.getInstance().getCmdBuffer(), 267);
			success = 0;
			return success;
		default:
			// intrinsic and user defined models
			// edit the DSS object
			DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
			if (DSS.activeDSSClass.setActive(name))
				success = DSS.activeDSSClass.edit();  // edit the active object
			break;
		}

		return success;
	}

	public static int doSetKVBase() {
		int result = 0;

		// parse off next two items on line
		String paramName = Parser.getInstance().getNextParam();
		String busName = Parser.getInstance().makeString();

		paramName = Parser.getInstance().getNextParam();
		double kVValue = Parser.getInstance().makeDouble();

		// now find the bus and set the value
		Circuit ckt = DSS.activeCircuit;

		ckt.setActiveBusIndex(ckt.getBusList().find(busName));

		if (ckt.getActiveBusIndex() >= 0) {
			if (paramName.equalsIgnoreCase("kvln")) {
				ckt.getBus(ckt.getActiveBusIndex()).setKVBase(kVValue);
			} else {
				ckt.getBus(ckt.getActiveBusIndex()).setKVBase(kVValue / DSS.SQRT3);
			}
			ckt.getSolution().setVoltageBaseChanged(true);
			// solution.setSolutionInitialized(false);  // force reinitialization
		} else {
			result = 1;
			DSS.appendGlobalResult("Bus " + busName + " not found.");
		}

		return result;
	}

	/**
	 * Syntax can be either a list of bus names or a file specification:
	 *     file= ...
	 */
	public static void doAutoAddBusList(String s) {
		String s2;
		FileReader fr;
		BufferedReader br;
		Circuit ckt = DSS.activeCircuit;
		Parser parser = DSS.auxParser;

		ckt.getAutoAddBusList().clear();

		// Load up auxiliary parser to reparse the array list or file name
		parser.setCmdBuffer(s);
		String parmName = parser.getNextParam();
		String param = parser.makeString();

		/* Syntax can be either a list of bus names or a file specification: file= ... */

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				fr = new FileReader(param);
				br = new BufferedReader(fr);

				while ((s2 = br.readLine()) != null) {
					parser.setCmdBuffer(s2);
					parmName = parser.getNextParam();
					param = parser.makeString();
					if (param.length() > 0)
						ckt.getAutoAddBusList().add(param);
				}
				br.close();
				fr.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read bus list file. Error is: " + e.getMessage(), 268);
			}
		} else {
			// parse bus names off of array list
			while (param.length() > 0) {
				ckt.getAutoAddBusList().add(param);
				parser.getNextParam();
				param = parser.makeString();
			}
		}
	}

	/**
	 * Set keep flag on buses found in list so they aren't eliminated by
	 * some reduction algorithm.  This command is cumulative. To clear flag,
	 * use Reset keepList.
	 *
	 * Syntax can be either a list of bus names or a file specification: file= ...
	 */
	public static void doKeeperBusList(String s) {
		String s2;
		int iBus;
		FileReader fr;
		BufferedReader br;
		Circuit ckt = DSS.activeCircuit;
		Parser parser = DSS.auxParser;

		// load up auxiliary parser to reparse the array list or file name
		parser.setCmdBuffer(s);
		String parmName = parser.getNextParam();
		String param = parser.makeString();

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				fr = new FileReader(param);
				br = new BufferedReader(fr);

				while ((s2 = br.readLine()) != null) {
					parser.setCmdBuffer(s2);
					parmName = parser.getNextParam();
					param = parser.makeString();
					if (param.length() > 0) {
						iBus = ckt.getBusList().find(param);
						if (iBus >= 0) ckt.getBus(iBus).setKeep(true);
					}
				}
				br.close();
				fr.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read bus list file " + param +
						". Error is: " + e.getMessage(), 269);
			}
		} else {
			// parse bus names off of array list
			while (param.length() > 0) {
				iBus = ckt.getBusList().find(param);
				if (iBus >= 0) ckt.getBus(iBus).setKeep(true);

				parser.getNextParam();
				param = parser.makeString();
			}
		}
	}

	public static int doCktLossesCmd() {
		Complex lossValue;
		int result = 0;

		if (DSS.activeCircuit != null) {
			DSS.globalResult = "";
			lossValue = DSS.activeCircuit.getLosses();
			DSS.globalResult = String.format("%10.5g, %10.5g",
					lossValue.getReal() * 0.001,
					lossValue.getImaginary() * 0.001);
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	public static int doCurrentsCmd() {
		Complex[] cBuffer;
		CktElement elem;
		int nValues;

		if (DSS.activeCircuit != null) {
			elem = DSS.activeCircuit.getActiveCktElement();
			nValues = elem.getNumConds() * elem.getNumTerms();
			DSS.globalResult = "";
			cBuffer = new Complex[nValues];
			elem.getCurrents(cBuffer);
			for (int i = 0; i < nValues; i++) {
				DSS.globalResult += String.format("%10.5g, %6.1f,",
					cBuffer[i].abs(), ComplexUtil.degArg(cBuffer[i]));
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	public static int doLossesCmd() {
		Complex lossValue;
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			if (ckt.getActiveCktElement() != null) {
				DSS.globalResult = "";
				lossValue = ckt.getActiveCktElement().getLosses();
				DSS.globalResult = String.format("%10.5g, %10.5g",
						lossValue.getReal() * 0.001,
						lossValue.getImaginary() * 0.001);
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	/**
	 * Returns phase losses in kW, kVAr.
	 */
	public static int doPhaseLossesCmd() {
		Complex[] cBuffer;
		CktElement elem;
		int[] nValues = new int[1];
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			elem = ckt.getActiveCktElement();

			nValues[0] = elem.getNumPhases();
			cBuffer = new Complex[nValues[0]];
			DSS.globalResult = "";
			elem.getPhaseLosses(nValues, cBuffer);
			for (int i = 0; i < nValues[0]; i++) {
				DSS.globalResult += String.format("%10.5g, %10.5g,",
						cBuffer[i].getReal() * 0.001,
						cBuffer[i].getImaginary() * 0.001);
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	public static int doPowersCmd() {
		Complex[] cBuffer;
		CktElement elem;
		int nValues;
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			elem = ckt.getActiveCktElement();

			nValues = elem.getNumConds() * elem.getNumTerms();
			DSS.globalResult = "";
			cBuffer = new Complex[nValues];
			elem.getPhasePower(cBuffer);
			for (int i = 0; i < nValues; i++) {
				DSS.globalResult += String.format("%10.5g, %10.5g,",
						cBuffer[i].getReal() * 0.001,
						cBuffer[i].getImaginary() * 0.001);
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	/**
	 * All sequence currents of active circuit element.
	 * Returns magnitude only.
	 */
	public static int doSeqCurrentsCmd() {
		int nValues, i, k;
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex[] cBuffer;
		Circuit ckt = DSS.activeCircuit;
		CktElement elem;

		if (ckt != null) {
			if (ckt.getActiveCktElement() != null) {
				elem = ckt.getActiveCktElement();

				DSS.globalResult = "";
				if (elem.getNumPhases() < 3) {
					for (i = 0; i < 3 * elem.getNumTerms(); i++) {
						DSS.globalResult = DSS.globalResult + " -1.0,";  // signify n/a
					}
				} else {
					nValues = elem.getNumConds() * elem.getNumTerms();
					cBuffer = new Complex[nValues];
					elem.getCurrents(cBuffer);
					for (int j = 0; j < elem.getNumTerms(); j++) {
						k = j * elem.getNumConds();
						for (i = 0; i < 3; i++)
							Iph[i] = cBuffer[k + i];
						MathUtil.phase2SymComp(Iph, I012);
						for (i = 0; i < 3; i++)
							DSS.globalResult += String.format("%10.5g, ", I012[i].abs());
					}
				}
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	/**
	 * All seq powers of active 3-phase ciruit element.
	 * Returns kW + j kVAr
	 */
	public static int doSeqPowersCmd() {
		int nValues, i, j, k;
		Complex S = null;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex[] cBuffer;
		Circuit ckt = DSS.activeCircuit;
		CktElement elem;

		if (ckt != null) {
			if (ckt.getActiveCktElement() != null) {
				elem = ckt.getActiveCktElement();

				DSS.globalResult = "";
				if (elem.getNumPhases() < 3) {
					for (i = 0; i < 2 * 3 * elem.getNumTerms() - 1; i++)
						DSS.globalResult = DSS.globalResult + "-1.0, ";  // signify n/a
				} else {
					nValues = elem.getNumConds() * elem.getNumTerms();
					cBuffer = new Complex[nValues];
					elem.getCurrents(cBuffer);
					for (j = 0; j < elem.getNumTerms(); j++) {
						k = j * elem.getNumConds();
						for (i = 0; i < 3; i++)
							Vph[i] = ckt.getSolution().getNodeV(elem.getTerminal(j).getTermNodeRef(i));
						for (i = 0; i < 3; i++)
							Iph[i] = cBuffer[k + i];
						MathUtil.phase2SymComp(Iph, I012);
						MathUtil.phase2SymComp(Vph, V012);
						for (i = 0; i < 3; i++)
							S = V012[i].multiply(I012[i].conjugate());
						DSS.globalResult += String.format("%10.5g, %10.5g,",
							S.getReal() * 0.003, S.getImaginary() * 0.003);  // 3-phase kW conversion
					}
				}
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	/**
	 * All voltages of active ciruit element.
	 * Magnitude only.
	 * @return a set of seq voltages (3) for each terminal.
	 */
	public static int doSeqVoltagesCmd() {
		int nValues, i, j, k, n;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		String s;
		Circuit ckt = DSS.activeCircuit;
		CktElement elem;

		nValues = -1;  // unassigned, for exception message
		n = -1;        // unassigned, for exception message
		if (ckt != null) {
			if (ckt.getActiveCktElement() != null) {
				elem = ckt.getActiveCktElement();

				if (elem.isEnabled()) {
					try {
						nValues = elem.getNumPhases();
						DSS.globalResult = "";
						if (nValues < 3) {
							for (i = 0; i < 3 * elem.getNumTerms(); i++)
								DSS.globalResult += "-1.0, ";  // signify n/a
						} else {
							for (j = 0; j < elem.getNumTerms(); j++) {

								k = j * elem.getNumConds();
								for (i = 0; i < 3; i++)
									Vph[i] = ckt.getSolution().getNodeV(elem.getNodeRef(i + k));

								MathUtil.phase2SymComp(Vph, V012);  // compute symmetrical components

								for (i = 0; i < 3; i++)
									DSS.globalResult += String.format("%10.5g, ", V012[i].abs());
							}
						}
					} catch (Exception e) {
						s = e.getMessage() + DSS.CRLF +
							"element=" + elem.getName() + DSS.CRLF +
							"nValues=" + nValues + DSS.CRLF +
							"nTerms=" + elem.getNumTerms() + DSS.CRLF +
							"nConds =" + elem.getNumConds() + DSS.CRLF +
							"nodeRef=" + n;
						DSS.doSimpleMsg(s, 270);
					}
				}
			} else {
				DSS.globalResult = "Element disabled";
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	/**
	 * Bus voltages at active terminal.
	 */
	public static int doVoltagesCmd(boolean perUnit) {
		Complex volts;
		Bus activeBus;
		double Vmag;
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			if (ckt.getActiveBusIndex() >= 0) {
				activeBus = ckt.getBus(ckt.getActiveBusIndex());
				DSS.globalResult = "";
				for (int i = 0; i < activeBus.getNumNodesThisBus(); i++) {
					volts = ckt.getSolution().getNodeV(activeBus.getRef(i));
					Vmag = volts.abs();
					if (perUnit && (activeBus.getKVBase() > 0.0)) {
						Vmag = Vmag * 0.001 / activeBus.getKVBase();
						DSS.globalResult += String.format("%10.5g, %6.1f, ",
								Vmag, ComplexUtil.degArg( volts ));
					} else {
						DSS.globalResult += String.format("%10.5g, %6.1f, ",
								Vmag, ComplexUtil.degArg( volts ));
					}
				}
			} else {
				DSS.globalResult = "No active bus";
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return 0;
	}

	/**
	 * Bus short circuit matrix.
	 */
	public static int doZscCmd(boolean ZMatrix) {
		Bus activeBus;
		Complex Z;
		Circuit ckt = DSS.activeCircuit;

		int ret = 0;
		if (ckt != null) {
			if (ckt.getActiveBusIndex() >= 0) {
				activeBus = ckt.getBus( ckt.getActiveBusIndex() );
				DSS.globalResult = "";
				if (activeBus.getZsc() == null) return ret;

				for (int i = 0; i < activeBus.getNumNodesThisBus(); i++) {
					for (int j = 0; j < activeBus.getNumNodesThisBus(); j++) {
						if (ZMatrix) {
							Z = activeBus.getZsc().get(i, j);
						} else {
							Z = activeBus.getYsc().get(i, j);
						}
						DSS.globalResult += String.format("%-.5g, %-.5g,   ",
								Z.getReal(), Z.getImaginary());
					}
				}
			} else {
				DSS.globalResult = "No active bus";
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return ret;
	}

	/**
	 * Bus short circuit matrix.
	 */
	public static int doZsc10Cmd() {
		Bus activeBus;
		Complex Z;
		Circuit ckt = DSS.activeCircuit;

		int result = 0;
		if (ckt != null) {
			if (ckt.getActiveBusIndex() >= 0) {
				activeBus = ckt.getBus(ckt.getActiveBusIndex());
				DSS.globalResult = "";
				if (activeBus.getZsc() == null) {
					Z = activeBus.getZsc1();
					DSS.globalResult += String.format("Z1, %-.5g, %-.5g, ",
							Z.getReal(), Z.getImaginary()) + DSS.CRLF;

					Z = activeBus.getZsc0();
					DSS.globalResult += String.format("Z0, %-.5g, %-.5g, ",
							Z.getReal(), Z.getImaginary());
				}
			} else {
				DSS.globalResult = "No active bus";
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	/**
	 * Requires an EnergyMeter object at the head of the feeder.
	 * Adjusts loads defined by connected kVA or kWh billing.
	 */
	public static int doAllocateLoadsCmd() {
		Circuit ckt = DSS.activeCircuit;

		ckt.setLoadMultiplier(1.0);  // setter has side effects
		if (ckt.getSolution().getMode() != SolutionMode.SNAPSHOT) {
			// resets meters, etc. if not in snapshot mode
			ckt.getSolution().setMode(SolutionMode.SNAPSHOT);
		}

		/* Make guess based on present allocationfactors */
		ckt.getSolution().solve();

		/* Allocation loop -- make maxAllocationIterations iterations */
		for (int i = 0; i < DSS.maxAllocationIterations; i++) {
			for (EnergyMeterObj meter : ckt.getEnergyMeters())
				meter.calcAllocationFactors();

			for (SensorObj sensor : ckt.getSensors())
				sensor.calcAllocationFactors();

			// let the energy meters run down the circuit setting the loads
			for (EnergyMeterObj meter : ckt.getEnergyMeters())
				meter.allocateLoad();

			ckt.getSolution().solve();  // update the solution
		}

		return 0;
	}

	public static void doSetAllocationFactors(double x) {
		if (x < 0.0) {
			DSS.doSimpleMsg("Allocation factor must be greater than zero.", 271);
		} else {
			for (LoadObj load : DSS.activeCircuit.getLoads())
				load.setKVAAllocationFactor(x);
		}
	}

	public static void doSetCFactors(double x) {
		if (x <= 0.0) {
			DSS.doSimpleMsg("CFactor must be greater than zero.", 271);
		} else {
			for (LoadObj load : DSS.activeCircuit.getLoads())
				load.setCFactor(x);
		}
	}

	public static int doHarmonicsList(String s) {
		SolutionObj sol = DSS.activeCircuit.getSolution();

		if (s.equalsIgnoreCase("ALL")) {
			sol.setDoAllHarmonics(true);
		} else {
			sol.setDoAllHarmonics(false);

			double[] tmp = new double[100];  // big buffer
			int num = Parser.getInstance().parseAsVector(100, tmp);
			/* Parsing zero-fills the array */

			sol.setHarmonicListSize(num);
			sol.setHarmonicList(Util.resizeArray(sol.getHarmonicList(), sol.getHarmonicListSize()));
			for (int i = 0; i < sol.getHarmonicListSize(); i++)
				sol.getHarmonicList()[i] = tmp[i];
		}

		return 0;
	}

	public static int doFormEditCmd() {
		if (DSS.noFormsAllowed) return 0;

		doSelectCmd();  // select active object

		if (DSS.activeDSSObject != null) {
			DSS.forms.showPropEditForm();
		} else {
			DSS.doSimpleMsg("Element not found.", 272);
		}

		return 1;
	}

	public static int doMeterTotals() {
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			ckt.totalizeMeters();
			// now export to global result
			for (int i = 0; i < EnergyMeterObj.NumEMRegisters; i++)
				DSS.appendGlobalResult(String.format("%-.6g", DSS.activeCircuit.getRegisterTotals()[i]));
		}

		return 0;
	}

	public static int doCapacityCmd() {
		Circuit ckt = DSS.activeCircuit;
		Parser parser = Parser.getInstance();

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				switch (paramName.charAt(0)) {
				case 's':
					paramPointer = 0;
					break;
				case 'i':
					paramPointer = 1;
					break;
				default:
					paramPointer = -1;
					break;
				}
			}

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for capacity command", 273);
				break;
			case 0:
				ckt.setCapacityStart(parser.makeDouble());
				break;
			case 1:
				ckt.setCapacityIncrement(parser.makeDouble());
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (ckt.computeCapacity()) {  // totalizes energy meters at end
			DSS.globalResult = String.format("%-.6g",
				ckt.getRegisterTotals()[3] + ckt.getRegisterTotals()[19]);  // peak kW in meters

			DSS.appendGlobalResult(String.format("%-.6g", ckt.getLoadMultiplier()));
		}

		return 0;
	}

	public static int doClassesCmd() {
		for (int i = 0; i < DSSClassDefs.getNumIntrinsicClasses(); i++)
			DSS.appendGlobalResult(DSS.DSSClassList.get(i).getClassName());
		return 0;
	}

	public static int doUserClassesCmd() {
		if (DSSClassDefs.getNumUserClasses() == 0) {
			DSS.appendGlobalResult("No user classes defined.");
		} else {
			for (int i = DSSClassDefs.getNumIntrinsicClasses(); i < DSS.DSSClassList.size(); i++)
				DSS.appendGlobalResult(DSS.DSSClassList.get(i).getClassName());
		}
		return 0;
	}

	public static int doZscRefresh() {
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();
		int result = 1;

		try {
			for (int i = 0; i < ckt.getNumNodes(); i++)
				sol.setCurrent(i, Complex.ZERO);  // clear currents array

			if (ckt.getActiveBusIndex() >= 0 && ckt.getActiveBusIndex() < ckt.getNumBuses()) {
				if (ckt.getBus(ckt.getActiveBusIndex()).getZsc() == null)
					ckt.getBus(ckt.getActiveBusIndex()).allocateBusQuantities();
				SolutionAlgs.computeYsc(ckt.getActiveBusIndex());  // compute Ysc for active bus
				result = 0;
			}

		} catch (Exception e) {
			DSS.doSimpleMsg("ZscRefresh error: " + e.getMessage() + DSS.CRLF , 274);
		}

		return result;
	}

	public static int doVarValuesCmd() {
		PCElement elem;
		Circuit ckt = DSS.activeCircuit;

		if (DSS.activeCircuit != null) {
			/* Check if PCElement */
			switch (ckt.getActiveCktElement().getObjType()) {
			case DSSClassDefs.PC_ELEMENT:
				elem = (PCElement) ckt.getActiveCktElement();
				for (int i = 0; i < elem.numVariables(); i++)
					DSS.appendGlobalResult(String.format("%-.6g", elem.getVariable(i)));
				break;
			default:
				DSS.appendGlobalResult("null");
				break;
			}
		}
		return 0;
	}

	/**
	 * Get value of specified variable by name of index,
	 */
	public static int doValVarCmd() {
		String paramName, param;
		int varIndex;
		int propIndex;
		PCElement elem;
		Circuit ckt = DSS.activeCircuit;
		Parser parser = Parser.getInstance();

		/* Check to make sure this is a PC Element. If not, return null string in global result */

		if ((ckt.getActiveCktElement().getObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PC_ELEMENT) {
			DSS.globalResult = "";
		} else {
			elem = (PCElement) ckt.getActiveCktElement();

			/* Get next parameter on command line */

			paramName = parser.getNextParam().toUpperCase();
			param = parser.makeString();

			propIndex = 0;
			if (paramName.length() > 0) {
				switch (paramName.charAt(0)) {
				case 'N':
					propIndex = 0;
					break;
				case 'I':
					propIndex = 1;
					break;
				default:
					break;
				}
			}

			varIndex = 0;

			switch (propIndex) {
			case 0:
				varIndex = elem.lookupVariable(param);  // look up property index
				break;
			case 1:
				varIndex = parser.makeInteger() - 1;
				break;
			default:
				break;
			}

			if ((varIndex >= 0) && (varIndex < elem.numVariables())) {
				DSS.globalResult = String.format("%.8g", elem.getVariable(varIndex));
			} else {
				DSS.globalResult = "";   // invalid var name or index
			}
		}

		return 0;
	}

	public static int doVarNamesCmd() {
		PCElement elem;
		Circuit ckt = DSS.activeCircuit;

		if (DSS.activeCircuit != null) {
			/* Check if PCElement */
			switch (ckt.getActiveCktElement().getObjType()) {
			case DSSClassDefs.PC_ELEMENT:
				elem = (PCElement) ckt.getActiveCktElement();
				for (int i = 0; i < elem.numVariables(); i++)
					DSS.appendGlobalResult(elem.variableName(i));
				break;
			default:
				DSS.appendGlobalResult("null");
				break;
			}
		}

		return 0;
	}

	/**
	 * Format of file should be
	 *
	 *   BusName, x, y
	 *
	 * (x, y are real values)
	 *
	 * If swapXY is true, x and y values are swapped
	 */
	public static int doBusCoordsCmd(boolean swapXY) {
		String busName, s;
		int ib;
		FileReader fr;
		BufferedReader br;
		Bus bus;
		Circuit ckt = DSS.activeCircuit;

		/* Get next parameter on command line */
		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		try {
			fr = new FileReader(param);
			br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				Parser parser = DSS.auxParser;  // user aux parser to parse line

				parser.setCmdBuffer(s);
				parser.getNextParam();
				busName = parser.makeString();
				ib = ckt.getBusList().find(busName);
				if (ib >= 0) {
					bus = ckt.getBus(ib);
					parser.getNextParam();
					if (swapXY) {
						bus.setY(parser.makeDouble());
					} else {
						bus.setX(parser.makeDouble());
					}
					parser.getNextParam();
					if (swapXY) {
						bus.setX(parser.makeDouble());
					} else {
						bus.setY(parser.makeDouble());
					}
					bus.setCoordDefined(true);
				}
			}  // ignore a bus that's not in the circuit

			br.close();
			fr.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Bus coordinate file: \"" + param + "\" not found.", 275);
		}

		return 0;
	}

	public static int doMakePosSeq() {
		Circuit ckt = DSS.activeCircuit;

		ckt.setPositiveSequence(true);

		for (CktElement elem : ckt.getCktElements())
			elem.makePosSequence();

		return 0;
	}

	public static void doSetReduceStrategy(String s) {
		Circuit ckt = DSS.activeCircuit;
		Parser parser = DSS.auxParser;

		ckt.setReductionStrategyString(s);

		parser.setCmdBuffer(s);
		parser.getNextParam();
		String param = parser.makeString().toUpperCase();
		parser.getNextParam();
		String param2 = parser.makeString();

		ckt.setReductionStrategy(ReductionStrategy.DEFAULT);

		if (param.length() == 0) return;  // no option given

		switch (param.charAt(0)) {
		case 'B':
			ckt.setReductionStrategy(ReductionStrategy.BREAK_LOOP);
			break;
		case 'D':
			ckt.setReductionStrategy(ReductionStrategy.DEFAULT);
			break;
		case 'E':
			ckt.setReductionStrategy(ReductionStrategy.DANGLING);
			break;
		case 'M':
			ckt.setReductionStrategy(ReductionStrategy.MERGE_PARALLEL);
			break;
		case 'T':
			ckt.setReductionStrategy(ReductionStrategy.TAP_ENDS);
			ckt.setReductionMaxAngle(15.0);  // default
			if (param2.length() > 0)
				ckt.setReductionMaxAngle(parser.makeDouble());
			break;
		case 'S':  // stubs
			if (Util.compareTextShortest(param, "switch") == 0) {
				ckt.setReductionStrategy(ReductionStrategy.SWITCHES);
			} else {
				ckt.setReductionZmag(0.02);
				ckt.setReductionStrategy(ReductionStrategy.STUBS);
				if (param2.length() > 0) {
					ckt.setReductionZmag(parser.makeDouble());
				}
			}
			break;
		default:
			DSS.doSimpleMsg("Unknown reduction strategy: \"" + s + "\".", 276);
			break;
		}
	}

	/**
	 * Interpolate bus coordinates in meter zones.
	 */
	public static int doInterpolateCmd() {
		EnergyMeter meterClass;
		String param;
		int devClassIndex;
		Circuit ckt = DSS.activeCircuit;

		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString().toUpperCase();

		// initialize the checked flag for all circuit elements

		for (CktElement elem : ckt.getCktElements())
			elem.setChecked(false);

		if (param.length() == 0)
			param = "A";

		switch (param.charAt(0)) {
		case 'A':
			for (EnergyMeterObj mtr : ckt.getEnergyMeters())
				mtr.interpolateCoordinates();
			break;
		default:
			/* Interpolate a specific meter */
			devClassIndex = DSS.classNames.find("energymeter");
			if (devClassIndex >= 0) {
				meterClass = (EnergyMeter) DSS.DSSClassList.get(devClassIndex);
				if (meterClass.setActive(param)) {  // try to set it active
					((EnergyMeterObj) meterClass.getActiveObj()).interpolateCoordinates();
				} else {
					DSS.doSimpleMsg("EnergyMeter \"" + param + "\" not found.", 277);
				}
			}
			break;
		}

		return 0;
	}

	/**
	 * Rewrites designated file, aligning the fields into columns.
	 */
	public static int doAlignFileCmd() {
		int ret = 0;

		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		if (new File(param).exists()) {
			if (!Util.rewriteAlignedFile(param)) ret = 1;
		} else {
			DSS.doSimpleMsg("File \"" + param + "\" does not exist.", 278);
			ret = 1;
		}

		if (ret == 0) Util.fireOffEditor(DSS.globalResult);

		return ret;
	}

	/**
	 * Sends monitors, load shapes, growth shapes, or TCC curves to TOP
	 * as an STO file.
	 */
	public static int doTOPCmd() {
		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString().toUpperCase();

		Parser.getInstance().getNextParam();
		String objName = Parser.getInstance().makeString().toUpperCase();

		if (objName.length() == 0)
			objName = "ALL";

		switch (param.charAt(0)) {
		case 'L':
			DSS.loadShapeClass.TOPExport(objName);
			break;
		case 'T':
			DSS.TShapeClass.TOPExport(objName);
			break;
		default:
			DSS.monitorClass.TOPExport(objName);
			break;
		}
		return 0;
	}

	public static void doSetNormal(double pctNormal) {
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			pctNormal = pctNormal * 0.01;
			for (LineObj line : ckt.getLines()) {
				line.setNormAmps(pctNormal * line.getEmergAmps());
			}
		}
	}

	/**
	 * Rotate about the center of the coordinates.
	 */
	public static int doRotateCmd() {
		int i;
		double angle, xmin, xmax, ymin, ymax, xc, yc;
		Bus bus;
		Complex a, vector;
		Circuit ckt = DSS.activeCircuit;

		if (ckt != null) {
			Parser.getInstance().getNextParam();
			angle = Parser.getInstance().makeDouble() * DSS.PI / 180.0;   // deg to rad

			a = new Complex(Math.cos(angle), Math.sin(angle));
			xmin =  1.0e50;
			xmax = -1.0e50;
			ymin =  1.0e50;
			ymax = -1.0e50;
			for (i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBus(i).isCoordDefined()) {
					bus = ckt.getBus(i);
					xmax = Math.max(xmax, bus.getX());
					xmin = Math.min(xmin, bus.getX());
					ymax = Math.max(ymax, bus.getY());
					ymin = Math.min(ymin, bus.getY());
				}
			}

			xc = (xmax + xmin) / 2.0;
			yc = (ymax + ymin) / 2.0;

			for (i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBus(i).isCoordDefined()) {
					bus = ckt.getBus(i);
					vector = new Complex(bus.getX() - xc, bus.getY() - yc);
					vector = vector.multiply(a);
					bus.setX(xc + vector.getReal());
					bus.setY(yc + vector.getImaginary());
				}
			}
		}
		return 0;
	}

	public static int doVDiffCmd() {
		String busName, line;
	        int i, node, busIndex;
	        double Vmag, diff;
		Circuit ckt = DSS.activeCircuit;
	        Parser parser = DSS.auxParser;

	        int ret = 0;

	        if (new File(DSS.circuitName_ + "SavedVoltages.txt").exists()) {
	        	try {
	        		FileReader fr = new FileReader(DSS.circuitName_ + "SavedVoltages.txt");
	        		BufferedReader br = new BufferedReader(fr);

	        		PrintWriter pw = new PrintWriter(DSS.circuitName_ + "Vdiff.txt");

	        		while ((line = br.readLine()) != null) {
	        			parser.setCmdBuffer(line);
	        			parser.getNextParam();
	        			busName = parser.makeString();
	        			if (busName.length() > 0) {
	        				busIndex = ckt.getBusList().find(busName);
	        				if (busIndex >= 0) {
	        					parser.getNextParam();
	        					node = parser.makeInteger();
	        					Bus bus = ckt.getBus(busIndex);
	        					for (i = 0; i < bus.getNumNodesThisBus(); i++) {
								if (bus.getNum(i) == node) {
									parser.getNextParam();
									Vmag = parser.makeDouble();
									diff = ckt.getSolution().getNodeV(bus.getRef(i)).abs() - Vmag;
									if (Vmag != 0.0) {
										pw.println(busName + "." + node + ", " + (diff / Vmag * 100.0) + ", %");
									} else {
										pw.println(busName + "." + node + ", " + String.format("%-.5g", diff) + ", Volts");
									}
								}
							}
	        				}
	        			}
	        		}

	        		pw.close();
	        		br.close();
	        		fr.close();

	        		Util.fireOffEditor(DSS.circuitName_ + "Vdiff.txt");
	        	} catch (Exception e) {
	        		DSS.doSimpleMsg("Error opening saved voltages or Vdiff file: " + e.getMessage(), 280);
	     	           	return ret;
			}
	        } else {
	        	DSS.doSimpleMsg("No Saved Voltages.", 281);
	        }

	        return ret;
	}

	/**
	 * Returns summary in global result string.
	 */
	public static int doSummaryCmd() {
		StringBuilder s;
		Complex cLosses, cPower;
		String CRLF = DSS.CRLF;
		Circuit ckt = DSS.activeCircuit;

		s = new StringBuilder();
		if (ckt.isSolved()) {
			s.append("Status = Solved" + CRLF);
		} else {
			s.append("Status = Not solved" + CRLF);
		}
		s.append("Solution Mode = " + Util.getSolutionModeID() + CRLF);
		s.append("Number = " + String.valueOf(ckt.getSolution().getNumberOfTimes()) + CRLF);
		s.append("Load Mult = " + String.format("%5.3f", ckt.getLoadMultiplier()) + CRLF);
		s.append("Devices = " + String.format("%d", ckt.getNumDevices()) + CRLF);
		s.append("Buses = " + String.format("%d", ckt.getNumBuses()) + CRLF);
		s.append("Nodes = " + String.format("%d", ckt.getNumNodes()) + CRLF);
		s.append("Control Mode =" + Util.getControlModeID() + CRLF);
		s.append("Total Iterations = " + String.valueOf(ckt.getSolution().getIteration()) + CRLF);
		s.append("Control Iterations = " + String.valueOf(ckt.getSolution().getControlIteration()) + CRLF);
		s.append("Max Sol Iter = " + String.valueOf(ckt.getSolution().getMostIterationsDone()) + CRLF);
		s.append(" " + CRLF);
		s.append(" - Circuit Summary -" + CRLF);
		s.append(" " + CRLF);
		if (ckt != null) {
			s.append(String.format("Year = %d ", ckt.getSolution().getYear()) + CRLF);
			s.append(String.format("Hour = %d ", ckt.getSolution().getIntHour()) + CRLF);
			s.append("Max pu. voltage = " + String.format("%-.5g ", Util.getMaxPUVoltage()) + CRLF);
			s.append("Min pu. voltage = " + String.format("%-.5g ", Util.getMinPUVoltage(true)) + CRLF);
			cPower = Util.getTotalPowerFromSources().multiply(0.000001);  // MVA
			s.append(String.format("Total Active Power:   %-.6g MW", cPower.getReal()) + CRLF);
			s.append(String.format("Total Reactive Power: %-.6g Mvar", cPower.getImaginary()) + CRLF);
			cLosses = ckt.getLosses().multiply(0.000001);
			if (cPower.getReal() != 0.0) {
				s.append(String.format("Total Active Losses:   %-.6g MW, (%-.4g %%)",
					cLosses.getReal(), (cLosses.getReal() / cPower.getReal() * 100.0)) + CRLF);
			} else {
				s.append("Total Active Losses:   ****** MW, (**** %%)" + CRLF);
			}
			s.append(String.format("Total Reactive Losses: %-.6g Mvar", cLosses.getImaginary()) + CRLF);
			s.append(String.format("Frequency = %g Hz", ckt.getSolution().getFrequency()) + CRLF);
			s.append("Mode = " + Util.getSolutionModeID() + CRLF);
			s.append("Control Mode = " + Util.getControlModeID() + CRLF);
			s.append("Load Model = " + Util.getLoadModel() + CRLF);
		}

		DSS.globalResult = s.toString();

		return 0;
	}

	private static Distribution interpretDistribution(String s) {
		switch (s.toLowerCase().charAt(0)) {
		case 'u':
			return Distribution.UNIFORM;
		case 'r':
			return Distribution.RANDOM;
		case 's':
			return Distribution.SKIP;
		case 'p':
			return Distribution.PROPORTIONAL;
		default:
			return null;
		}
	}

	public static int doDistributeCmd() {
		Parser parser = Parser.getInstance();

		/* Defaults */
		double kW = 1000.0;
		Distribution how = Distribution.PROPORTIONAL;
		int skip = 1;
		double pf = 1.0;
		String fileName = "DistGenerators.dss";

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = distributeCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				kW = parser.makeDouble();
				break;
			case 1:
				how = interpretDistribution(parser.makeString());
				break;
			case 2:
				skip = parser.makeInteger();
				break;
			case 3:
				pf = parser.makeDouble();
				break;
			case 4:
				fileName = parser.makeString();
				break;
			case 5:
				kW = parser.makeDouble() * 1000.0;
				break;
			default:
				// ignore unnamed and extra parms
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		Util.makeDistributedGenerators(kW, pf, how, skip, fileName);

		return 0;
	}

	public static int doDI_PlotCmd() {
		if (DSS.DIFilesAreOpen)
			DSS.energyMeterClass.closeAllDIFiles();

		if (DSSPlot.getDSSPlotObj() == null)
			DSSPlot.setDSSPlotObj(new DSSPlot());

		/* Defaults */
		int numRegs = 1;
		double[] dRegisters = new double[EnergyMeterObj.NumEMRegisters];
		int[] iRegisters = new int[numRegs];
		iRegisters[0] = 9;
		boolean peakDay = false;
		int caseYear = 1;
		String caseName = "";
		String meterName = "DI_Totals";

		Parser parser = Parser.getInstance();

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = DI_PlotCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				caseName = param;
				break;
			case 1:
				caseYear = parser.makeInteger();
				break;
			case 2:
				numRegs = parser.parseAsVector(EnergyMeterObj.NumEMRegisters, dRegisters);
				iRegisters = new int[numRegs];
				for (int i = 0; i < numRegs; i++)
					iRegisters[i] = (int) Math.round(dRegisters[i]);
				break;
			case 3:
				peakDay = Util.interpretYesNo(param);
				break;
			case 4:
				meterName = parser.makeString();
				break;
			default:
				// ignore unnamed and extra parms
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		DSSPlot.getDSSPlotObj().doDI_Plot(caseName, caseYear, iRegisters, peakDay, meterName);

		return 0;
	}

	public static int doCompareCasesCmd() {
		boolean unknown;

		if (DSS.DIFilesAreOpen)
			DSS.energyMeterClass.closeAllDIFiles();

		if (DSSPlot.getDSSPlotObj() == null)
			DSSPlot.setDSSPlotObj(new DSSPlot());

		String caseName1 = "base";
		String caseName2 = "";
		int reg = 9;  // overload EEN
		String whichFile = "Totals";

		int paramPointer = -1;
		String paramName = Parser.getInstance().getNextParam().toUpperCase();
		String param = Parser.getInstance().makeString();

		while (param.length() > 0) {
			unknown = false;
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				if (Util.compareTextShortest(paramName, "case1") == 0) {
					paramPointer = 0;
				} else if (Util.compareTextShortest(paramName, "case2") == 0) {
					paramPointer = 1;
				} else if (Util.compareTextShortest(paramName, "register") == 0) {
					paramPointer = 2;
				} else if (Util.compareTextShortest(paramName, "meter") == 0) {
					paramPointer = 3;
				} else {
					unknown = true;
				}
			}

			if (!unknown) {
				switch (paramPointer) {
				case 0:
					caseName1 = param;
					break;
				case 1:
					caseName2 = param;
					break;
				case 2:
					reg = Parser.getInstance().makeInteger();
					break;
				case 3:
					whichFile = param;
					break;
				default:
					// ignore unnamed and extra params
					break;
				}
			}
			paramName = Parser.getInstance().getNextParam().toUpperCase();
			param = Parser.getInstance().makeString();
		}

		DSSPlot.getDSSPlotObj().doCompareCases(caseName1, caseName2, whichFile, reg);

		return 0;
	}

	public static int doYearlyCurvesCmd() {
		boolean unknown;
		ArrayList<String> caseNames;
		double[] dRegisters = new double[EnergyMeterObj.NumEMRegisters];
		int[] iRegisters;
		String whichFile;

		if (DSS.DIFilesAreOpen)
			DSS.energyMeterClass.closeAllDIFiles();

		if (DSSPlot.getDSSPlotObj() == null)
			DSSPlot.setDSSPlotObj(new DSSPlot());

		int nRegs = 1;
		iRegisters = new int[nRegs];
		caseNames = new ArrayList<String>();
		whichFile = "Totals";

		int paramPointer = -1;
		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		while (param.length() > 0) {
			unknown = false;

			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				switch (paramName.toUpperCase().charAt(0)) {
				case 'C':
					paramPointer = 0;
					break;
				case 'R':
					paramPointer = 1;
					break;
				case 'M':
					paramPointer = 2;  // meter=
					break;
				default:
					unknown = true;
					break;
				}
			}

			if (!unknown) {
				switch (paramPointer) {
				case 0:   // list of case names
					DSS.auxParser.setCmdBuffer(param);
					DSS.auxParser.getNextParam();
					param = DSS.auxParser.makeString();
					while (param.length() > 0) {
						caseNames.add(param);
						DSS.auxParser.getNextParam();
						param = DSS.auxParser.makeString();
					}
					break;
				case 1:
					nRegs = Parser.getInstance().parseAsVector(EnergyMeterObj.NumEMRegisters, dRegisters);
					iRegisters = new int[nRegs];
					for (int i = 0; i < nRegs; i++)
						iRegisters[i] = (int) Math.round(dRegisters[i]);
					break;
				case 2:
					whichFile = param;
					break;
				default:
					// ignore unnamed and extra params
					break;
				}
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		DSSPlot.getDSSPlotObj().doYearlyCurvePlot(caseNames, whichFile, iRegisters);

		return 0;
	}

	public static int doVisualizeCmd() {
		int devIndex;
		boolean unknown;
		int quantity;
		DSSObject elem;
		Circuit ckt = DSS.activeCircuit;

		int result = 0;

		// abort if no circuit or solution
		if (ckt == null) {
			DSS.doSimpleMsg("No circuit created.", 24721);
			return result;
		}

		if ((ckt.getSolution() == null) || (ckt.getSolution().getNodeV() == null)) {
			DSS.doSimpleMsg("The circuit must be solved before you can do this.", 24722);
			return result;
		}

		quantity = DSSPlot.vizCURRENT;
		String elemName = "";

		/* Parse rest of command line */
		int paramPointer = -1;
		String paramName = Parser.getInstance().getNextParam().toUpperCase();
		String param = Parser.getInstance().makeString();

		while (param.length() > 0) {
			unknown = false;

			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				if (Util.compareTextShortest(paramName, "what") == 0) {
					paramPointer = 0;
				} else if (Util.compareTextShortest(paramName, "element") == 0) {
					paramPointer = 1;
				} else {
					unknown = true;
				}
			}

			if (!unknown) {
				switch (paramPointer) {
				case 0:
					switch (param.toLowerCase().charAt(0)) {
					case 'c':
						quantity = DSSPlot.vizCURRENT;
						break;
					case 'v':
						quantity = DSSPlot.vizVOLTAGE;
						break;
					case 'p':
						quantity = DSSPlot.vizPOWER;
						break;
					}
				case 1:
					elemName = param;
					break;
				default:
					// ignore unnamed and extra params
					break;
				}
			}

			paramName = Parser.getInstance().getNextParam().toUpperCase();
			param = Parser.getInstance().makeString();
		}

		devIndex = Util.getCktElementIndex(elemName);
		if (devIndex >= 0) {  //  element must already exist
			elem = ckt.getCktElements().get(devIndex);
			if (elem instanceof CktElement) {
				DSSPlot.getDSSPlotObj().doVisualizationPlot((CktElement) elem, quantity);
			} else {
				DSS.doSimpleMsg(elem.getName() + " must be a circuit element type!", 282);
			}
		} else {
			DSS.doSimpleMsg("Requested circuit element: \"" + elemName + "\" not found.", 282);
		}

		return result;
	}

	public static int doCloseDICmd() {
		DSS.energyMeterClass.closeAllDIFiles();
		return 0;
	}

	public static int doADOScmd() {
		Util.doShellCmd(Parser.getInstance().getRemainder());
		return 0;
	}

	/**
	 * Load current estimation is driven by energy meters at head of feeders.
	 */
	public static int doEstimateCmd() {
		doAllocateLoadsCmd();

		/* Let's look to see how well we did */
		if (!DSS.autoShowExport)
			Executive.getInstance().setCommand("set showexport=yes");

		Executive.getInstance().setCommand("export estimation");

		return 0;
	}

	public static int doReconductorCmd() {
		String lineCode = "", geometry = "", editString;
		LineObj lineElem1, lineElem2;
		Line lineClass;
		int traceDirection;
		int nPhases;

		int ret = 0;
		int paramPointer = -1;
		boolean lineCodeSpecified = false;
		boolean geometrySpecified = false;
		String line1 = "";
		String line2 = "";
		String myEditString = "";
		nPhases = 0;  // no filtering by number of phases

		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = reconductorCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				line1 = param;
				break;
			case 1:
				line2 = param;
				break;
			case 2:
				lineCode = param;
				lineCodeSpecified = true;
				geometrySpecified = false;
				break;
			case 3:
				geometry = param;
				lineCodeSpecified = false;
				geometrySpecified = true;
				break;
			case 4:
				myEditString = param;
				break;
			case 5:
				nPhases = Parser.getInstance().makeInteger();
			default:
				DSS.doSimpleMsg("Error: Unknown parameter on command line: "+param, 28701);
				break;
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		/* Check for errors */

		/* If user specified full line name, get rid of "line." */
		line1 = Util.stripClassName(line1);
		line2 = Util.stripClassName(line2);

		if (line1.length() == 0 || line2.length() == 0) {
			DSS.doSimpleMsg("Both line 1 and line 2 must be specified!", 28702);
			return ret;
		}

		if (!lineCodeSpecified && !geometrySpecified) {
			DSS.doSimpleMsg("Either a new line code or a geometry must be specified!", 28703);
			return ret;
		}

		lineClass = (Line) DSS.DSSClassList.get(DSS.classNames.find("Line"));
		lineElem1 = (LineObj) lineClass.find(line1);
		lineElem2 = (LineObj) lineClass.find(line2);

		if (lineElem1 == null || lineElem2 == null) {
			if (lineElem1 == null) {
				DSS.doSimpleMsg("Line." + line1 + " not found.", 28704);
			} else if (lineElem2 == null) {
				DSS.doSimpleMsg("Line." + line2 + " not found.", 28704);
			}
			return ret;
		}

		/* Now check to make sure they are in the same meter's zone */
		if (lineElem1.getMeterObj() == null || lineElem2.getMeterObj() == null) {
			DSS.doSimpleMsg("Error: Both Lines must be in the same EnergyMeter zone. " +
					"One or both are not in any meter zone.", 28705);
			return ret;
		}

		if (lineElem1.getMeterObj() != lineElem2.getMeterObj()) {
			DSS.doSimpleMsg("Error: Line1 is in EnergyMeter." + lineElem1.getMeterObj().getName() +
					" zone while Line2 is in EnergyMeter." + lineElem2.getMeterObj().getName() +
					" zone. Both must be in the same Zone.", 28706);
			return ret;
		}

		/* Since the lines can be given in either order, Have to check to see
		 * which direction they are specified and find the path between them.
		 */
		traceDirection = 0;

		if (Util.isPathBetween(lineElem1, lineElem2))
			traceDirection = 1;

		if (Util.isPathBetween(lineElem2, lineElem1))
			traceDirection = 2;

		if (lineCodeSpecified) {
			editString = "lineCode=" + lineCode;
		} else {
			editString = "geometry=" + geometry;
		}

		// append myEditString onto the end of the edit string to change the linecode or geometry
		editString = String.format("%s %s", editString, myEditString);

		switch (traceDirection) {
		case 1:
			Util.traceAndEdit(lineElem1, lineElem2, nPhases, editString);
			break;
		case 2:
			Util.traceAndEdit(lineElem2, lineElem1, nPhases, editString);
			break;
		default:
			DSS.doSimpleMsg("Traceback path not found between line 1 and line 2.", 28707);
			return ret;
		}
		return ret;
	}

	public static int doAddMarkerCmd() {
		String busName = "";
		int busIdx;
		Bus bus;
		Circuit ckt = DSS.activeCircuit;
		Parser parser = Parser.getInstance();

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = addMarkerCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				busName = param;
				break;
			case 1:
				DSSPlot.setAddMarkerCode(parser.makeInteger());
				break;
			case 2:
				DSSPlot.setAddMarkerColor(parser.makeInteger());
				break;
			case 3:
				DSSPlot.setAddMarkerSize(parser.makeInteger());
				break;
			default:
				// ignore unnamed and extra params
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		busIdx = ckt.getBusList().find(busName);
		if (busIdx >= 0) {
			bus = ckt.getBus(busIdx);
			if (bus.isCoordDefined()) {
				DSSGraphDeclarations.addNewMarker(bus.getX(), bus.getY(),
						DSSPlot.getAddMarkerColor(),
						DSSPlot.getAddMarkerCode(),
						DSSPlot.getAddMarkerSize());
				DSSGraphDeclarations.showGraph();
			} else {
				DSS.doSimpleMsg("Bus coordinates not defined for bus " + busName, 28709);
			}
		} else {
			DSS.doSimpleMsg("Bus not found.", 28708);
		}

		return 0;
	}

	public static int doSetLoadAndGenKVCmd() {
		Bus bus;
		String sBus;
		int iBus;
		double kvln;

		Circuit ckt = DSS.activeCircuit;

		for (LoadObj load : ckt.getLoads()) {
			Load.activeLoadObj = load;  // for updateVoltageBases to work
			sBus = Util.stripExtension(load.getBus(0));
			iBus = ckt.getBusList().find(sBus);
			bus = ckt.getBus(iBus);
			kvln = bus.getKVBase();
			if (load.getConnection() == Connection.DELTA || load.getNumPhases() == 3) {
				load.setKVLoadBase(kvln * DSS.SQRT3);
			} else {
				load.setKVLoadBase(kvln);
			}
			load.updateVoltageBases();
			load.recalcElementData();
		}

		for (GeneratorObj gen : ckt.getGenerators()) {
			sBus = Util.stripExtension(gen.getBus(0));
			iBus = ckt.getBusList().find(sBus);
			bus = ckt.getBus(iBus);
			kvln = bus.getKVBase();
			if (gen.getConnection() == Connection.DELTA || gen.getNumPhases() > 1) {
				gen.setPresentKV(kvln * DSS.SQRT3);
			} else {
				gen.setPresentKV(kvln);
			}
			gen.recalcElementData();
		}

		return 0;
	}

	public static int doUUIDsCmd() {
		FileReader fr;
		BufferedReader br;
		@SuppressWarnings("unused")
		String paramName, param, s, nameVal, uuidVal;
		String[] devClass = new String[1];
		String[] devName = new String[1];
		NamedObject named;
		Parser parser = Parser.getInstance();

		paramName = parser.getNextParam();
		param = parser.makeString();

		try {
			fr = new FileReader(param);
			br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				named = null;
				DSS.auxParser.setCmdBuffer(s);
				DSS.auxParser.getNextParam();
				nameVal = DSS.auxParser.makeString();
				DSS.auxParser.getNextParam();
				uuidVal = DSS.auxParser.makeString();
				// format the UUID properly
				if (uuidVal.indexOf('{') < 0)
					uuidVal = '{' + uuidVal + '}';
				// find this object
				Util.parseObjectClassandName(nameVal, devClass, devName);
				if (devClass[0].equalsIgnoreCase("circuit")) {
					named = DSS.activeCircuit;
				} else {
					DSS.lastClassReferenced = DSS.classNames.find(devClass[0]);
					DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
					if (DSS.activeDSSClass != null) {
						DSS.activeDSSClass.setActive(devName[0]);
						named = DSS.activeDSSClass.getActiveObj();
					}
				}
				// re-assign its UUID
				if (named != null) named.setUUID(UUID.fromString(uuidVal));
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error reading UUIDs: " + e.getMessage(), -1);
		}

		return 0;
	}

	public static int doConvertLoadShapesCmd() {
		LoadShapeObj loadShape;
		int iLoadShape;
		LoadShape loadShapeClass;
		String action;
		PrintWriter pw;

		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		if (param.length() == 0) param = "s";

		/* Double file or Single file? */
		switch (param.toLowerCase().charAt(0)) {
		case 'd':
			action = "action=dblsave";
			break;
		default:
			action = "action=sngsave";  // default
			break;
		}

		loadShapeClass = (LoadShape) DSSClassDefs.getDSSClass("loadshape");

		String fileName = "ReloadLoadShapes.dss";

		try {
			pw = new PrintWriter(fileName);

			iLoadShape = loadShapeClass.getFirst();
			while (iLoadShape >= 0) {
				loadShape = (LoadShapeObj) loadShapeClass.getActiveObj();
				Parser.getInstance().setCmdBuffer(action);
				loadShape.edit();
				pw.printf("new loadShape.%s npts=%d interval=%.8g %s",
					loadShape.getName(), loadShape.getNumPoints(),
					loadShape.getInterval(), DSS.globalResult);
				pw.println();
				iLoadShape = loadShapeClass.getNext();
			}

			pw.close();
		} catch (IOException e) {
			DSS.doSimpleMsg("Error writing load shapes: " + e.getMessage(), -1);
		}

		Util.fireOffEditor(fileName);

		return 0;
	}

	public static int doNodeDiffCmd() {
		String sNode1, sNode2;
		String sBusName;
		Complex V1, V2, VNodeDiff;
		int iBusIdx;
		int b1ref;
		int b2ref;
		int[] numNodes = new int[1];
		int[] nodeBuffer = new int[50];

		Circuit ckt = DSS.activeCircuit;

		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		sNode1 = param;

		if (paramName.indexOf('2') >= 0) sNode2 = param;

		paramName = Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString();
		sNode2 = param;

		if (paramName.indexOf('1') > 0) sNode1 = param;

		// get first node voltage
		DSS.auxParser.setToken(sNode1);
		nodeBuffer[0] = 1;
		sBusName = DSS.auxParser.parseAsBusName(numNodes, nodeBuffer);
		iBusIdx = ckt.getBusList().find(sBusName);
		if (iBusIdx >= 0) {
			b1ref = ckt.getBus(iBusIdx).find(nodeBuffer[0]);
		} else {
			DSS.doSimpleMsg(String.format("Bus %s not found.", sBusName), 28709);
			return 0;
		}

		V1 = ckt.getSolution().getNodeV(b1ref);

		// get 2nd node voltage
		DSS.auxParser.setToken(sNode2);
		nodeBuffer[0] = 1;
		sBusName = DSS.auxParser.parseAsBusName(numNodes, nodeBuffer);
		iBusIdx = ckt.getBusList().find(sBusName);
		if (iBusIdx > 0) {
			b2ref = ckt.getBus(iBusIdx).find(nodeBuffer[0]);
		} else {
			DSS.doSimpleMsg(String.format("Bus %s not found.", sBusName), 28710);
			return 0;
		}

		V2 = ckt.getSolution().getNodeV(b2ref);

		VNodeDiff = V1.subtract(V2);
		DSS.globalResult = String.format("%.7g, V,    %.7g, deg  ",
			VNodeDiff.abs(), ComplexUtil.degArg(VNodeDiff));

		return 0;
	}

	public static int doRephaseCmd() {
		String startLine = "";
		String newPhases = "";
		LineObj pStartLine;
		Line lineClass;

		int paramPointer = -1;
		String myEditString = "";
		String scriptFileName = "RephaseEditScript.dss";
		boolean transfStop = true;  // stop at transformers

		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = rephaseCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				startLine = param;
				break;
			case 1:
				newPhases = param;
				break;
			case 2:
				myEditString = param;
				break;
			case 3:
				scriptFileName = param;
				break;
			case 4:
				transfStop = Util.interpretYesNo(param);
				break;
			default:
				DSS.doSimpleMsg("Unknown parameter on command line: "+param, 28711);
				break;
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		lineClass = (Line) DSS.DSSClassList.get(DSS.classNames.find("Line"));
		pStartLine = (LineObj) lineClass.find(Util.stripClassName(startLine));
		if (pStartLine == null) {
			DSS.doSimpleMsg("Starting line (" + startLine + ") not found.", 28712);
			return 0;
		}
		/* Check for some error conditions and abort if necessary */
		if (pStartLine.getMeterObj() == null) {
			DSS.doSimpleMsg("Starting line must be in an EnergyMeter zone.", 28713);
			return 0;
		}

		if (!(pStartLine.getMeterObj() instanceof EnergyMeterObj)) {
			DSS.doSimpleMsg("Starting line must be in an EnergyMeter zone.", 28714);
			return 0;
		}

		Util.goForwardAndRephase(pStartLine, newPhases, myEditString, scriptFileName, transfStop);

		return 0;
	}

	public static int doSetBusXYCmd() {
		String busName = "";
		double Xval = 0.0;
		double Yval = 0.0;
		Circuit ckt = DSS.activeCircuit;

		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		int paramPointer = -1;

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = setBusXYCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				busName = param;
				break;
			case 1:
				Xval = Parser.getInstance().makeDouble();
				break;
			case 2:
				Yval = Parser.getInstance().makeDouble();
				break;
			default:
				DSS.doSimpleMsg("Unknown parameter on command line: "+param, 28721);
				break;
			}

			int iB = ckt.getBusList().find(busName);
			if (iB >= 0) {
				ckt.getBus(iB).setX(Xval);
				ckt.getBus(iB).setY(Yval);
				ckt.getBus(iB).setCoordDefined(true);
			} else {
				DSS.doSimpleMsg("Bus \"" + busName + "\" not found.", 28722);
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		return 0;
	}

	public static int doUpdateStorageCmd() {
		DSS.storageClass.updateAll();
		return 0;
	}

	public static int doPstCalc() {
		String param;
		String paramName;
		int paramPointer;
		int npts;
		double[] Varray;
		int cyclesPerSample;
		int lamp;
		double[][] pstArray;
		int nPst;
		int i;
		String s;
		double freq;

		Parser parser = Parser.getInstance();

		Varray = null;
		pstArray = new double[1][];
		npts = 0;
		lamp = 120;  // 120 or 230
		cyclesPerSample = 60;
		freq = DSS.defaultBaseFreq;

		paramName      = parser.getNextParam();
		param          = parser.makeString();
		paramPointer   = -1;

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = pstCalcCommands.getCommand(paramName);
			}

			switch (paramPointer) {
			case 0:
				npts = parser.makeInteger();
				Varray = Util.resizeArray(Varray, npts);
				break;
			case 1:
				npts = Util.interpretDblArray(param, npts, Varray);
				break;
			case 3:
				double f = DSS.activeCircuit.getSolution().getFrequency();
				cyclesPerSample = (int) Math.round(f * parser.makeDouble());
				break;
			case 4:
				freq = parser.makeDouble();
				break;
			case 5:
				lamp = parser.makeInteger();
				break;
			default:
				DSS.doSimpleMsg("Unknown parameter on command line: " + param, 28722);
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (npts > 10) {
			nPst = PstCalc.pstRMS(pstArray, Varray, freq, cyclesPerSample, npts, lamp);
			// put resulting pst array in the result string
			s = "";
			for (i = 0; i < nPst; i++) {
				s = s + String.format("%.8g, ", pstArray[i]);
			}
			DSS.globalResult = s;
		} else {
			DSS.doSimpleMsg("Insuffient number of points for Pst calculation.", 28723);
		}

		return 0;
	}

}
