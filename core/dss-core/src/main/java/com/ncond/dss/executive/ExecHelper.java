package com.ncond.dss.executive;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.Circuit.ReductionStrategyType;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Solution;
import com.ncond.dss.common.SolutionAlgs;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.SolverError;
import com.ncond.dss.common.Util;
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
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.meter.MonitorObj;
import com.ncond.dss.meter.SensorObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.plot.DSSGraphDeclarations;
import com.ncond.dss.plot.DSSPlot;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.ComplexUtil;
import com.ncond.dss.shared.Dynamics;
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

	private ExecHelper() {

	}

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
		if (paramName.length() > 0)  // if specified, must be object or an abbreviation.
			if (Util.compareTextShortest(paramName, "object") != 0) {
				DSS.doSimpleMsg("object=class.name expected as first parameter in command."+ DSS.CRLF + parser.getCmdString(), 240);
				return;
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
		int result = 0;  // TODO Check return value

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

		if (handle == -1)
			result = 1;

		return result;
	}

	/**
	 * edit type=xxxx name=xxxx editstring
	 */
	public static int doEditCmd() {
		String[] objType = new String[0];
		String[] objName = new String[0];
		int result = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			// everything else must be a circuit element
			result = editObject(objType[0], objName[0]);
		}

		return result;
	}

	/**
	 * batchedit type=xxxx name=pattern editstring
	 */
	public static int doBatchEditCmd() {
		String[] objType = new String[1];
		String[] pattern = new String[1];
		Pattern regEx1;
		Matcher matcher;
		DSSObject pObj;
		int params;

		Parser parser = Parser.getInstance();

		int result = 0;
		getObjClassAndName(objType, pattern);
		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			DSS.lastClassReferenced = DSS.classNames.find( objType[0] );

			switch (DSS.lastClassReferenced) {
			case -1:
				DSS.doSimpleMsg("BatchEdit command: Object type \"" + objType[0] + "\" not found."+ DSS.CRLF + parser.getCmdString(), 267);
				return result;
			default:
				params = parser.getPosition();
				DSS.activeDSSClass = DSS.DSSClassList.get( DSS.lastClassReferenced );
				regEx1 = Pattern.compile( pattern[0], Pattern.CASE_INSENSITIVE );
				DSS.activeDSSClass.getFirst();
				pObj = (DSSObject) DSS.activeDSSClass.getActiveObj();
				while (pObj != null) {
					matcher = regEx1.matcher( pObj.getName() );
					if (matcher.find()) {
						parser.setPosition(params);
						DSS.activeDSSClass.edit();
					}
					DSS.activeDSSClass.getNext();
					pObj = (DSSObject) DSS.activeDSSClass.getActiveObj();
				}
			}
		}
		return result;
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
		int result = 0;

		// get next parm and try to interpret as a file name
		Parser.getInstance().getNextParam();
		ExecCommands.getInstance().setRedirFile(
				Util.expandFileName(Parser.getInstance().makeString()));

		if (!ExecCommands.getInstance().getRedirFile().equals("")) {
			saveDir = DSS.currentDirectory;

			try {
				fr = new FileReader(ExecCommands.getInstance().getRedirFile());
				if (isCompile)
					DSS.lastFileCompiled = ExecCommands.getInstance().getRedirFile();
			} catch (FileNotFoundException e) {
				// couldn't find file; try appending '.dss' to the file name
				// if it doesn't already have an extension
				if (ExecCommands.getInstance().getRedirFile().indexOf('.') == -1) {
					ExecCommands.getInstance().setRedirFile(ExecCommands.getInstance().getRedirFile() + ".dss");
					try {
						fr = new FileReader(ExecCommands.getInstance().getRedirFile());
					} catch (FileNotFoundException ex) {
						DSS.doSimpleMsg("Redirect file: \"" + ExecCommands.getInstance().getRedirFile() + "\" not found.", 242);
						DSS.solutionAbort = true;
						return result;
					}
				} else {
					DSS.doSimpleMsg("Redirect file: \""+ExecCommands.getInstance().getRedirFile()+"\" not found.", 243);
					DSS.solutionAbort = true;
					return result;  // already had an extension
				}
			}

			// OK, we finally got one open, so we're going to continue
			try {
				// change directory to path specified by file in case that loads in more files
				currDir = Util.extractFileDir(ExecCommands.getInstance().getRedirFile());
				DSS.currentDirectory = currDir;
				if (isCompile)
					DSS.setDataPath(currDir);

				DSS.redirectAbort = false;
				DSS.inRedirect = true;

				br = new BufferedReader(fr);

				while (((inputLine = br.readLine()) != null) || DSS.redirectAbort) {
					if (!DSS.solutionAbort) {
						ExecCommands.getInstance().processCommand(inputLine);
					} else {
						DSS.redirectAbort = true;  // abort file if solution was aborted
					}
				}

				if (DSS.activeCircuit != null)
					DSS.activeCircuit.setCurrentDirectory(currDir + "\"");

				br.close();
				fr.close();
			} catch (IOException e) {
				DSS.doErrorMsg("DoRedirect"+DSS.CRLF+"Error processing input stream in Compile/Redirect.",
						e.getMessage(),
						"Error in file: \"" + ExecCommands.getInstance().getRedirFile() + "\" or filename.", 244);
			} finally {
				DSS.inRedirect = false;
				if (isCompile) {
					DSS.setDataPath(currDir);  // change DSSDataDirectory
					DSS.lastCommandWasCompile = true;
				} else {
					DSS.currentDirectory = saveDir;  // set back to where we were for redirect, but not compile
				}
			}
		} // else ignore altogether if null filename

		return result;
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

		int result = 1;

		getObjClassAndName(objClass, objName);  // parse object class and name

		if (objClass[0].length() == 0 && objName[0].length() == 0)
			return result;  // select active obj if any

		if (objClass[0].equalsIgnoreCase("circuit")) {
			setActiveCircuit(objName[0]);
		} else {
			// everything else must be a circuit element
			if (objClass[0].length() > 0)
				DSSClassDefs.setObjectClass(objClass[0]);

			DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
			if (DSS.activeDSSClass != null) {
				if (!DSS.activeDSSClass.setActive( objName[0] )) {
					// scroll through list of objects until a match
					DSS.doSimpleMsg("Error: Object \"" + objName[0] + "\" not found."+ DSS.CRLF + Parser.getInstance().getCmdString(), 245);
					result = 0;
				} else {
					switch (DSS.activeDSSObject.getObjType()) {
					case DSSClassDefs.DSS_OBJECT:
						// do nothing for general DSS object
						break;
					default:  // for circuit types set activeCircuit element too
						DSS.activeCircuit.setActiveCktElement((CktElement) DSS.activeDSSClass.getActiveObj());
						// now check for active terminal designation
						Parser.getInstance().getNextParam().toLowerCase();
						param = Parser.getInstance().makeString();
						if (param.length() > 0) {
							DSS.activeCircuit.getActiveCktElement().setActiveTerminalIdx(Parser.getInstance().makeInteger());
						} else {
							DSS.activeCircuit.getActiveCktElement().setActiveTerminalIdx(0);
						}
						DSS.setActiveBus( DSS.activeCircuit.getActiveCktElement().getBus(DSS.activeCircuit.getActiveCktElement().getActiveTerminalIdx()) );
						break;
					}
				}
			} else {
				DSS.doSimpleMsg("Error: Active object type/class is not set.", 246);
				result = 0;
			}
		}

		return result;
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

	public static int doSaveCmd() {
		// TODO: Implement this method
		throw new UnsupportedOperationException();
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
		int result = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			if (!objType[0].equalsIgnoreCase( DSS.activeDSSClass.getClassName() )) {
				DSS.lastClassReferenced =  DSS.classNames.find(objType[0]);

				switch (DSS.lastClassReferenced) {
				case 0:
					DSS.doSimpleMsg("Object type \"" + objType[0] + "\" not found."+ DSS.CRLF + Parser.getInstance().getCmdString(), 253);
					result = 0;
					return result;
				default:
					// intrinsic and user defined models
					DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
					if (DSS.activeDSSClass.setActive( objName[0] )) {
						// scroll through list of objects until a match
						switch (DSS.activeDSSObject.getObjType()) {
						case DSSClassDefs.DSS_OBJECT:
							DSS.doSimpleMsg("Error in setActiveCktElement: Object not a circuit element."+ DSS.CRLF + Parser.getInstance().getCmdString(), 254);
							break;
						default:
							DSS.activeCircuit.setActiveCktElement((CktElement) DSS.activeDSSClass.getActiveObj());
							result = 1;
						}
					}
					break;
				}
			}
		}

		return result;
	}

	public static int doEnableCmd() {
		String[] objType = new String[1];
		String[] objName = new String[1];
		DSSClass classPtr;
		CktElement cktElem;

		//result = setActiveCktElement();
		//if (result >= 0) DSSGlobals.activeCircuit.getActiveCktElement().setEnabled(true);

		int result = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			if (objType[0].length() > 0) {
				// only applies to CktElementClass objects
				classPtr = DSSClassDefs.getDSSClass(objType[0]);
				if (classPtr != null) {

					if ((classPtr.getClassType() & DSSClassDefs.BASECLASSMASK) > 0) {
						// everything else must be a circuit element
						if (objName[0].equals("*")) {
							// enable all elements of this class
							for (int i = 0; i < classPtr.getElementCount(); i++) {
								cktElem = (CktElement) classPtr.getElementList().get(i);
								cktElem.setEnabled(true);
							}
						} else {
							// just load up the parser and call the edit routine for the object in question
							Parser.getInstance().setCmdString("enabled=true");  // will only work for CktElements
							result = editObject(objType[0], objName[0]);
						}
					}

				}
			}
		}

		return result;
	}

	public static int doDisableCmd() {
		String[] objType = new String[1];
		String[] objName = new String[1];
		DSSClass classPtr;
		CktElement cktElem;

		int result = 0;

		getObjClassAndName(objType, objName);

		if (objType[0].equalsIgnoreCase("circuit")) {
			// do nothing
		} else {
			if (objType[0].length() > 0) {
				// only applies to CktElementClass objects
				classPtr = DSSClassDefs.getDSSClass(objType[0]);
				if (classPtr != null) {

					if ((classPtr.getClassType() & DSSClassDefs.BASECLASSMASK) > 0) {
						// everything else must be a circuit element
						if (objName[0].equals("*")) {
							// disable all elements of this class
							for (int i = 0; i < classPtr.getElementCount(); i++) {
								cktElem = (CktElement) classPtr.getElementList().get(i);
								cktElem.setEnabled(false);
							}
						}
					} else {
						// just load up the parser and call the edit routine for the object in question
						Parser.getInstance().setCmdString("enabled=false");  // will only work for CktElements
						result = editObject(objType[0], objName[0]);
					}

				}
			}
		}

		//Result = setActiveCktElement();
		//if (Result > 0) getActiveCircuit().getActiveCktElement().setEnabled(false);

		return result;
	}

	public static int doPropertyDump() {
		// TODO Implement this method.
		throw new UnsupportedOperationException();
	}

	/** For interpreting time specified as an array "hour, sec". */
	public static void setTime() {
		double[] timeArray = new double[2];
		Parser.getInstance().parseAsVector(2, timeArray);

		SolutionObj solution = DSS.activeCircuit.getSolution();

		solution.setIntHour((int) timeArray[0]);
		solution.getDynaVars().t = timeArray[1];
		solution.updateDblHour();
	}

	public static void setActiveCircuit(String cktName) {

		for (Circuit ckt : DSS.circuits)
			if (ckt.getName().equalsIgnoreCase(cktName)) {
				DSS.activeCircuit = ckt;
				return;
			}

		DSS.doSimpleMsg("Error: No circuit named \"" + cktName + "\" found." + DSS.CRLF +
				"Active circuit not changed.", 258);
	}

	public static void doLegalVoltageBases() {
		double[] dummy = new double[100];  // big buffer
		int num = Parser.getInstance().parseAsVector(100, dummy);
		/* Parsing zero-fills the array */

		/* LegalVoltageBases is a zero-terminated array, so we have to allocate
		 * one more than the number of actual values}
		 */
		Circuit ckt = DSS.activeCircuit;
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

		int retval = setActiveCktElement();
		if (retval > 0) {
			parser.getNextParam();
			terminal  = parser.makeInteger();
			parser.getNextParam();
			conductor = parser.makeInteger();

			Circuit ckt = DSS.activeCircuit;

			ckt.getActiveCktElement().setActiveTerminalIdx(terminal);
			ckt.getActiveCktElement().setConductorClosed(conductor, false);
			DSS.setActiveBus(
					Util.stripExtension(ckt.getActiveCktElement().getBus(
							ckt.getActiveCktElement().getActiveTerminalIdx()) ) );
		} else {
			DSS.doSimpleMsg("Error in Open command: Circuit element not found." +DSS.CRLF+parser.getCmdString(), 259);
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

		int retval = setActiveCktElement();
		if (retval > 0) {
			parser.getNextParam();
			terminal = parser.makeInteger();
			parser.getNextParam();
			conductor = parser.makeInteger();

			Circuit ckt = DSS.activeCircuit;

			ckt.getActiveCktElement().setActiveTerminalIdx(terminal);
			ckt.getActiveCktElement().setConductorClosed(conductor, true);
			DSS.setActiveBus( Util.stripExtension(ckt.getActiveCktElement().getBus(
					ckt.getActiveCktElement().getActiveTerminalIdx()) ) );
		} else {
			DSS.doSimpleMsg("Error in Close command circuit element not found."+DSS.CRLF+parser.getCmdString(), 260);
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
				case 'O':
					// monitor
					doResetMonitors();
					break;
				case 'E':
					// meter
					doResetMeters();
					break;
				}
				break;
			case 'F':
				// faults
				Util.doResetFaults();
				break;
			case 'C':
				// controls
				Util.doResetControls();
				break;
			case 'E':
				// eventLog
				Util.clearEventLog();
				break;
			case 'K':
				Util.doResetKeepList();
				break;
			default:
				DSS.doSimpleMsg("Unknown argument to Reset command: \""+param+"\"", 261);
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
						DSS.activeCircuit.getBus(capElement.getTerminal(0).getBusRef()).setKeep(true);
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
							DSS.activeCircuit.getBus( reacElement.getTerminal(0).getBusRef() ).setKeep(true);
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

		if (param.length() == 0)
			param = "A";
		switch (param.charAt(0)) {
		case 'A':
			for (EnergyMeterObj MeterObj : DSS.activeCircuit.getEnergyMeters())
				MeterObj.reduceZone();
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
					DSS.doSimpleMsg("EnergyMeter \""+param+"\" not found.", 262);
				}
			}
			break;
		}
		return 0;
	}

	public static int doResetMonitors() {
		for (MonitorObj mon : DSS.activeCircuit.getMonitors())
			mon.resetIt();
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
			DSS.globalResult = "File \""+param+"\" does not exist.";
		}

		return 1;
	}

	/**
	 * Parse strings such as
	 *     1. Classname.Objectname,Property  (full name)
	 *     2. Objectname.Property            (classname omitted)
	 *     3. Property                       (classname and objectname omitted
	 */
	public static void parseObjName(String fullName, StringBuffer objName, StringBuffer propName) {
		int dotpos1, dotpos2;
		String tmpName;

		dotpos1 = fullName.indexOf(".");
		switch (dotpos1) {
		case -1:
			objName.delete(0, objName.length());
			propName.setLength(fullName.length());
			propName.replace(0, fullName.length(), fullName);
			break;
		default:
			tmpName = fullName.substring(dotpos1 + 1, fullName.length());
			propName.setLength(tmpName.length());
			propName.replace(0, tmpName.length(), tmpName);
			dotpos2 = propName.indexOf(".");
			switch (dotpos2) {
			case -1:
				tmpName = fullName.substring(0, dotpos1);
				objName.setLength(tmpName.length());
				objName.replace(0, tmpName.length(), tmpName);
				break;
			default:
				tmpName = fullName.substring(0, dotpos1 + dotpos2);
				objName.setLength(tmpName.length());
				objName.replace(0, tmpName.length(), tmpName);

				tmpName = propName.substring(dotpos2 + 1, propName.length());
				propName.setLength(tmpName.length());
				propName.replace(0, tmpName.length(), tmpName);
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
		StringBuffer objName = new StringBuffer();
		StringBuffer propName = new StringBuffer();
		int result = 0;

		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString();

		parseObjName(param, objName, propName);

		if (objName.toString().equalsIgnoreCase("solution")) {  // special for solution
			DSS.activeDSSClass = DSS.solutionClass;
			DSS.activeDSSObject = (DSSObject) DSS.activeCircuit.getSolution();
		} else {
			// set object active
			Parser.getInstance().setCmdString("\"" + objName.toString() + "\"");
			doSelectCmd();
		}

		// put property value in global variable
		int propIndex = DSS.activeDSSClass.propertyIndex(propName.toString());
		if (propIndex >= 0) {
			DSS.globalPropertyValue = DSS.activeDSSObject.getPropertyValue(propIndex);
		} else {
			DSS.globalPropertyValue = "Property unknown";
		}

		DSS.globalResult = DSS.globalPropertyValue;

		if (DSS.logQueries)
			DSS.writeQueryLogFile(param, DSS.globalResult);  // write time-stamped query

		return result;
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
		if (DSS.noFormsAllowed)
			return;
		DSS.forms.showAboutBox();
	}

	public static int addObject(String objType, String name) {
		Parser parser = Parser.getInstance();

		int handle = -1;

		// search for class if not already active
		// if nothing specified, lastClassReferenced remains
		if (!objType.equalsIgnoreCase( DSS.activeDSSClass.getClassName() ))
			DSS.lastClassReferenced = DSS.classNames.find(objType);

		switch (DSS.lastClassReferenced) {
		case -1:
			DSS.doSimpleMsg("New command: Object type \"" + objType + "\" not found." + DSS.CRLF + parser.getCmdString(), 263);
			handle = -1;
			return handle;
		default:
			// intrinsic and user defined models
			// make a new circuit element
			DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);

			// name must be supplied
			if (name.length() == 0) {
				DSS.doSimpleMsg("Object name missing"+ DSS.CRLF + parser.getCmdString(), 264);
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
						DSS.doSimpleMsg("Warning: Duplicate new element definition: \""+ DSS.activeDSSClass.getClassName()+"."+name+"\""+
								DSS.CRLF+ "Element being redefined.", 266);
					}
				}
				break;
			}

			// activeDSSObject now points to the object just added
			// if a circuit element, activeCktElement in activeCircuit is also set
			if (handle >= 0)
				DSS.activeDSSObject.setClassIndex(handle);

			// process remaining instructions on the command line
			DSS.activeDSSClass.edit();

			break;
		}

		return handle;
	}

	public static int editObject(String objType, String name) {
		int result = 0;
		DSS.lastClassReferenced = DSS.classNames.find(objType);

		switch (DSS.lastClassReferenced) {
		case -1:
			DSS.doSimpleMsg("Edit command: Object type \"" + objType + "\" not found."+ DSS.CRLF + Parser.getInstance().getCmdString(), 267);
			result = 0;
			return result;
		default:
			// intrinsic and user defined models
			// edit the DSS object
			DSS.activeDSSClass = DSS.DSSClassList.get(DSS.lastClassReferenced);
			if (DSS.activeDSSClass.setActive(name))
				result = DSS.activeDSSClass.edit();  // edit the active object
			break;
		}

		return result;
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
				ckt.getBus( ckt.getActiveBusIndex() ).setKVBase(kVValue);
			} else {
				ckt.getBus( ckt.getActiveBusIndex() ).setKVBase(kVValue / DSS.SQRT3);
			}
			result = 0;
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

		DSS.activeCircuit.getAutoAddBusList().clear();

		// Load up auxiliary parser to reparse the array list or file name
		DSS.auxParser.setCmdString(s);
		String parmName = DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString();

		/* Syntax can be either a list of bus names or a file specification: file= ... */

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				File F = new File(param);
				FileInputStream fis = new FileInputStream(F);
				DataInputStream dis = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));

				while ((s2 = br.readLine()) != null) {
					DSS.auxParser.setCmdString(s2);
					parmName = DSS.auxParser.getNextParam();
					param = DSS.auxParser.makeString();
					if (param.length() > 0)
						DSS.activeCircuit.getAutoAddBusList().add(param);
				}
				br.close();
				dis.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read bus list file. Error is: "+e.getMessage(), 268);
			}
		} else {
			// parse bus names off of array list
			while (param.length() > 0) {  // TODO Check zero indexing
				DSS.activeCircuit.getAutoAddBusList().add(param);
				DSS.auxParser.getNextParam();
				param = DSS.auxParser.makeString();
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
		Circuit ckt;

		// load up auxiliary parser to reparse the array list or file name
		DSS.auxParser.setCmdString(s);
		String parmName = DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString();

		if (parmName.equalsIgnoreCase("file")) {
			// load the list from a file
			try {
				File f = new File(param);
				FileInputStream fis = new FileInputStream(f);
				DataInputStream dis = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));

				while ((s2 = br.readLine()) != null) {
					DSS.auxParser.setCmdString(s2);
					parmName = DSS.auxParser.getNextParam();
					param = DSS.auxParser.makeString();
					if (param.length() > 0) {
						ckt = DSS.activeCircuit;
						iBus = ckt.getBusList().find(param);
						if (iBus > 0) ckt.getBus(iBus).setKeep(true);
					}
				}
				br.close();
				dis.close();
			} catch (Exception e) {
				DSS.doSimpleMsg("Error trying to read bus list file "+param+". Error is: "+e.getMessage(), 269);
			}
		} else {
			// parse bus names off of array list
			while (param.length() > 0) {
				ckt = DSS.activeCircuit;

				iBus = ckt.getBusList().find(param);
				if (iBus >= 0)
					ckt.getBus(iBus).setKeep(true);

				DSS.auxParser.getNextParam();
				param = DSS.auxParser.makeString();
			}
		}
	}

	public static int doCktLossesCmd() {
		Complex lossValue;
		int result = 0;

		if (DSS.activeCircuit != null) {
			DSS.globalResult = "";
			lossValue = DSS.activeCircuit.getLosses();
			DSS.globalResult = String.format("%10.5g, %10.5g", lossValue.getReal() * 0.001,  lossValue.getImaginary() * 0.001);
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	public static int doCurrentsCmd() {
		Complex[] cBuffer;
		CktElement cktElem;
		int nValues;
		int result = 0;

		if (DSS.activeCircuit != null) {
			cktElem = DSS.activeCircuit.getActiveCktElement();

			nValues = cktElem.getNumConds() * cktElem.getNumTerms();
			DSS.globalResult = "";
			cBuffer = new Complex[nValues];
			cktElem.getCurrents(cBuffer);
			for (int i = 0; i < nValues; i++)
				DSS.globalResult = DSS.globalResult + String.format("%10.5g, %6.1f,", cBuffer[i].abs(), ComplexUtil.degArg( cBuffer[i] ));
			cBuffer = null;
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	public static int doLossesCmd() {
		Complex lossValue;
		Circuit ckt;

		int result = 0;
		if (DSS.activeCircuit != null) {
			ckt = DSS.activeCircuit;
			if (ckt.getActiveCktElement() != null) {
				DSS.globalResult = "";
				lossValue = ckt.getActiveCktElement().getLosses();
				DSS.globalResult = String.format("%10.5g, %10.5g", lossValue.getReal() * 0.001, lossValue.getImaginary() * 0.001);
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	/**
	 * Returns phase losses in kW, kVAr.
	 */
	public static int doPhaseLossesCmd() {
		Complex[] cBuffer;
		CktElement cktElem;
		int[] nValues = new int[1];

		int result = 0;

		if (DSS.activeCircuit != null) {
			cktElem = DSS.activeCircuit.getActiveCktElement();

			nValues[0] = cktElem.getNumPhases();
			cBuffer = new Complex[nValues[0]];
			DSS.globalResult = "";
			cktElem.getPhaseLosses(nValues, cBuffer);
			for (int i = 0; i < nValues[0]; i++)
				DSS.globalResult += String.format("%10.5g, %10.5g,", cBuffer[i].getReal() * 0.001, cBuffer[i].getImaginary() * 0.001);
			cBuffer = null;
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	public static int doPowersCmd() {
		Complex[] cBuffer;
		CktElement cktElem;
		int nValues;

		int result = 0;
		if (DSS.activeCircuit != null) {
			cktElem = DSS.activeCircuit.getActiveCktElement();

			nValues = cktElem.getNumConds() * cktElem.getNumTerms();
			DSS.globalResult = "";
			cBuffer = new Complex[nValues];
			cktElem.getPhasePower(cBuffer);
			for (int i = 0; i < nValues; i++)
				DSS.globalResult =  DSS.globalResult + String.format("%10.5g, %10.5g,", cBuffer[i].getReal() * 0.001, cBuffer[i].getImaginary() * 0.001);
			cBuffer = null;
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
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
		Circuit ckt;
		CktElement cktElem;

		int result = 0;
		if (DSS.activeCircuit != null) {
			ckt = DSS.activeCircuit;
			if (ckt.getActiveCktElement() != null) {
				cktElem = DSS.activeCircuit.getActiveCktElement();

				DSS.globalResult = "";
				if (cktElem.getNumPhases() < 3) {
					for (i = 0; i < 3 * cktElem.getNumTerms(); i++)
						DSS.globalResult =  DSS.globalResult + " -1.0,";  // signify n/a
				} else {
					nValues = cktElem.getNumConds() * cktElem.getNumTerms();
					cBuffer = new Complex[nValues];
					cktElem.getCurrents(cBuffer);
					for (int j = 0; j < cktElem.getNumTerms(); j++) {
						k = j * cktElem.getNumConds();
						for (i = 0; i < 3; i++)
							Iph[i] = cBuffer[k + i];
						MathUtil.phase2SymComp(Iph, I012);
						for (i = 0; i < 3; i++)
							DSS.globalResult = DSS.globalResult + String.format("%10.5g, ", I012[i].abs());
					}
					cBuffer = null;
				}
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
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
		Circuit ckt;
		CktElement cktElem;

		int result = 0;
		if (DSS.activeCircuit != null) {
			ckt = DSS.activeCircuit;

			if (ckt.getActiveCktElement() != null) {
				cktElem = DSS.activeCircuit.getActiveCktElement();

				DSS.globalResult = "";
				if (cktElem.getNumPhases() < 3) {
					for (i = 0; i < 2 * 3 * cktElem.getNumTerms() - 1; i++)
						DSS.globalResult = DSS.globalResult + "-1.0, ";  // signify n/a
				} else {
					nValues = cktElem.getNumConds() * cktElem.getNumTerms();
					cBuffer = new Complex[nValues];
					cktElem.getCurrents(cBuffer);
					for (j = 0; j < cktElem.getNumTerms(); j++) {
						k = j * cktElem.getNumConds();
						for (i = 0; i < 3; i++)
							Vph[i] = ckt.getSolution().getNodeV( cktElem.getTerminal(j).getTermNodeRef(i) );
						for (i = 0; i < 3; i++)
							Iph[i] = cBuffer[k + i];
						MathUtil.phase2SymComp(Iph, I012);
						MathUtil.phase2SymComp(Vph, V012);
						for (i = 0; i < 3; i++)
							S = V012[i].multiply( I012[i].conjugate() );
						DSS.globalResult = DSS.globalResult + String.format("%10.5g, %10.5g,", S.getReal() * 0.003, S.getImaginary() * 0.003); // 3-phase kW conversion
					}
				}
				cBuffer = null;
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
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
		String S;
		Circuit ckt;
		CktElement cktElem;

		int result = 0;
		nValues = -1;  // unassigned, for exception message
		n = -1;        // unassigned, for exception message
		if (DSS.activeCircuit != null) {
			ckt = DSS.activeCircuit;

			if (ckt.getActiveCktElement() != null) {
				cktElem = DSS.activeCircuit.getActiveCktElement();

				if (cktElem.isEnabled()) {
					try {
						nValues = cktElem.getNumPhases();
						DSS.globalResult = "";
						if (nValues < 3) {
							for (i = 0; i < 3 * cktElem.getNumTerms(); i++)
								DSS.globalResult =  DSS.globalResult + "-1.0, ";  // signify n/a
						} else {
							for (j = 0; j < cktElem.getNumTerms(); j++) {

								k = j * cktElem.getNumConds();
								for (i = 0; i < 3; i++)
									Vph[i] = ckt.getSolution().getNodeV( cktElem.getNodeRef(i + k) );

								MathUtil.phase2SymComp(Vph, V012);  // compute symmetrical components

								for (i = 0; i < 3; i++)
									DSS.globalResult =  DSS.globalResult + String.format("%10.5g, ", V012[i].abs());
							}
						}
					} catch (Exception e) {
						S = e.getMessage() + DSS.CRLF +
							"element=" + cktElem.getName() + DSS.CRLF +
							"nValues=" + String.valueOf(nValues) + DSS.CRLF +
							"nTerms=" + String.valueOf(cktElem.getNumTerms()) + DSS.CRLF +
							"nConds =" + String.valueOf(cktElem.getNumConds()) + DSS.CRLF +
							"nodeRef=" + String.valueOf(n) ;
						DSS.doSimpleMsg(S, 270);
					}
				}
			} else {
				DSS.globalResult = "Element disabled";
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	/** Bus Voltages at active terminal. */
	public static int doVoltagesCmd(boolean perUnit) {
		Complex volts;
		Bus activeBus;
		double VMag;

		int result = 0;
		if (DSS.activeCircuit != null) {
			Circuit ckt = DSS.activeCircuit;

			if (ckt.getActiveBusIndex() >= 0) {
				activeBus = ckt.getBus( ckt.getActiveBusIndex() );
				DSS.globalResult = "";
				for (int i = 0; i < activeBus.getNumNodesThisBus(); i++) {
					volts = ckt.getSolution().getNodeV( activeBus.getRef(i) );
					VMag = volts.abs();
					if (perUnit && (activeBus.getKVBase() > 0.0)) {
						VMag = VMag * 0.001 / activeBus.getKVBase();
						DSS.globalResult =  DSS.globalResult + String.format("%10.5g, %6.1f, ", VMag, ComplexUtil.degArg( volts ));
					} else {
						DSS.globalResult =  DSS.globalResult + String.format("%10.5g, %6.1f, ", VMag, ComplexUtil.degArg( volts ));
					}
				}
			} else {
				DSS.globalResult = "No active bus";
			}

		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	/** Bus short circuit matrix. */
	public static int doZscCmd(boolean ZMatrix) {
		Bus activeBus;
		Complex Z;

		int result = 0;
		if (DSS.activeCircuit != null) {
			Circuit ckt = DSS.activeCircuit;

			if (ckt.getActiveBusIndex() >= 0) {
				activeBus = ckt.getBus( ckt.getActiveBusIndex() );
				DSS.globalResult = "";
				if (activeBus.getZsc() == null)
					return result;

				for (int i = 0; i < activeBus.getNumNodesThisBus(); i++) {
					for (int j = 0; j < activeBus.getNumNodesThisBus(); j++) {
						if (ZMatrix) {
							Z = activeBus.getZsc().get(i, j);
						} else {
							Z = activeBus.getYsc().get(i, j);
						}
						DSS.globalResult = DSS.globalResult + String.format("%-.5g, %-.5g,   ", Z.getReal(), Z.getImaginary());
					}
				}
			} else {
				DSS.globalResult = "No active bus";
			}
		} else {
			DSS.globalResult = "No active circuit";
		}

		return result;
	}

	/** Bus Short Circuit matrix. */
	public static int doZsc10Cmd() {
		Bus activeBus;
		Complex Z;

		int result = 0;
		if (DSS.activeCircuit != null) {
			Circuit ckt = DSS.activeCircuit;

			if (ckt.getActiveBusIndex() >= 0) {
				activeBus = ckt.getBus( ckt.getActiveBusIndex() );
				DSS.globalResult = "";
				if (activeBus.getZsc() == null) {

					Z = activeBus.getZsc1();
					DSS.globalResult = DSS.globalResult + String.format("Z1, %-.5g, %-.5g, ", Z.getReal(), Z.getImaginary()) + DSS.CRLF;

					Z = activeBus.getZsc0();
					DSS.globalResult = DSS.globalResult + String.format("Z0, %-.5g, %-.5g, ", Z.getReal(), Z.getImaginary());
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
		int result = 0;
		Circuit ckt = DSS.activeCircuit;

		ckt.setLoadMultiplier(1.0);  // setter has side effects
		if (ckt.getSolution().getMode() != Dynamics.SNAPSHOT)
			ckt.getSolution().setMode( Dynamics.SNAPSHOT );  // resets meters, etc. if not in snapshot mode
		ckt.getSolution().solve();  /* Make guess based on present allocationfactors */

		/* Allocation loop -- make maxAllocationIterations iterations */
		for (int i = 0; i < DSS.maxAllocationIterations; i++) {
			/* Do energy meters */
			for (EnergyMeterObj meter : ckt.getEnergyMeters())
				meter.calcAllocationFactors();

			/* Now do other sensors */
			for (SensorObj sensor : ckt.getSensors())
				sensor.calcAllocationFactors();

			/* Now let the energy meters run down the circuit setting the loads */
			for (EnergyMeterObj meter : ckt.getEnergyMeters())
				meter.allocateLoad();

			ckt.getSolution().solve();  // update the solution
		}

		return result;
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
		int result = 0;

		SolutionObj solution = DSS.activeCircuit.getSolution();

		if (s.equalsIgnoreCase("ALL")) {
			solution.setDoAllHarmonics(true);
		} else {
			solution.setDoAllHarmonics(false);

			double[] tmp = new double[100];  // big buffer
			int num = Parser.getInstance().parseAsVector(100, tmp);
			/* Parsing zero-fills the array */

			solution.setHarmonicListSize(num);
			solution.setHarmonicList( Util.resizeArray(solution.getHarmonicList(), solution.getHarmonicListSize()) );
			for (int i = 0; i < solution.getHarmonicListSize(); i++)
				solution.getHarmonicList()[i] = tmp[i];
			tmp = null;
		}

		return result;
	}

	public static int doFormEditCmd() {
		if (DSS.noFormsAllowed)
			return 0;

		doSelectCmd();  // select active object

		if (DSS.activeDSSObject != null) {
			DSS.forms.showPropEditForm();
		} else {
			DSS.doSimpleMsg("Element not found.", 272);
		}

		return 1;
	}

	public static int doMeterTotals() {

		if (DSS.activeCircuit != null) {
			DSS.activeCircuit.totalizeMeters();
			// Now export to global result
			for (int i = 0; i < EnergyMeterObj.NumEMRegisters; i++)
				DSS.appendGlobalResult( String.format("%-.6g", DSS.activeCircuit.getRegisterTotals()[i]) );
		}

		return 0;
	}

	public static int doCapacityCmd() {

		int paramPointer = -1;
		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

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
				DSS.doSimpleMsg("Unknown parameter \""+paramName+"\" for capacity command", 273);
				break;
			case 0:
				DSS.activeCircuit.setCapacityStart(Parser.getInstance().makeDouble());
				break;
			case 1:
				DSS.activeCircuit.setCapacityIncrement(Parser.getInstance().makeDouble());
				break;
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		Circuit ckt = DSS.activeCircuit;
		if (ckt.computeCapacity()) {  // totalizes energy meters at end

			DSS.globalResult = String.format("%-.6g", (ckt.getRegisterTotals()[3] + ckt.getRegisterTotals()[19]));  // peak kW in meters
			DSS.appendGlobalResult( String.format("%-.6g", ckt.getLoadMultiplier()) );
		}

		return 0;
	}

	public static int doClassesCmd() {

		for (int i = 0; i < DSSClassDefs.getNumIntrinsicClasses(); i++)
			DSS.appendGlobalResult( ((DSSClass) DSS.DSSClassList.get(i)).getClassName() );

		return 0;
	}

	public static int doUserClassesCmd() {

		if (DSSClassDefs.getNumUserClasses() == 0) {
			DSS.appendGlobalResult("No user classes defined.");
		} else {
			for (int i = DSSClassDefs.getNumIntrinsicClasses(); i < DSS.DSSClassList.size(); i++)
				DSS.appendGlobalResult( ((DSSClass) DSS.DSSClassList.get(i)).getClassName() );
		}
		return 0;
	}

	public static int doZscRefresh() {
		Circuit ckt;
		SolutionObj solution;
		int result = 1;

		try {
			ckt = DSS.activeCircuit;
			solution = ckt.getSolution();
			for (int i = 0; i < ckt.getNumNodes(); i++)
				solution.setCurrent(i, Complex.ZERO);  // clear currents array

			if (ckt.getActiveBusIndex() >= 0 && ckt.getActiveBusIndex() < ckt.getNumBuses()) {
				if (ckt.getBus( ckt.getActiveBusIndex() ).getZsc() == null)
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
		Circuit ckt;

		if (DSS.activeCircuit != null) {
			ckt = DSS.activeCircuit;
			/* Check if PCElement */
			switch (ckt.getActiveCktElement().getObjType()) {
			case DSSClassDefs.PC_ELEMENT:
				PCElement cktElem = (PCElement) ckt.getActiveCktElement();
				for (int i = 0; i < cktElem.numVariables(); i++)
					DSS.appendGlobalResult(String.format("%-.6g", cktElem.getVariable(i)));
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
		PCElement PCElem;
		Parser parser = Parser.getInstance();

		int result = 0;

		/* Check to make sure this is a PC Element. If not, return null string in global result */

		if ((DSS.activeCircuit.getActiveCktElement().getObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PC_ELEMENT) {

			DSS.globalResult = "";

		} else {

			PCElem = (PCElement) DSS.activeCircuit.getActiveCktElement();

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
				varIndex = PCElem.lookupVariable(param);  // look up property index
				break;
			case 1:
				varIndex = parser.makeInteger();
				break;
			default:
				break;
			}

			if ((varIndex >= 0) && (varIndex < PCElem.numVariables())) {
				DSS.globalResult = String.format("%.8g", PCElem.getVariable(varIndex));
			} else {
				DSS.globalResult = "";   // invalid var name or index
			}
		}

		return result;
	}

	public static int doVarNamesCmd() {
		if (DSS.activeCircuit != null) {
			Circuit ckt = DSS.activeCircuit;
			/* Check if PCElement */
			switch (ckt.getActiveCktElement().getObjType()) {
			case DSSClassDefs.PC_ELEMENT:
				PCElement cktElem = (PCElement) ckt.getActiveCktElement();
				for (int i = 0; i < cktElem.numVariables(); i++)
					DSS.appendGlobalResult(cktElem.variableName(i));
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
		int ib, result = 0;
		File f;
		Bus bus;

		/* Get next parameter on command line */
		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		try {
			f = new File(param);
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			while ((s = br.readLine()) != null) {
				Parser parser = DSS.auxParser;  // user aux parser to parse line

				parser.setCmdString(s);
				parser.getNextParam();
				busName = parser.makeString();
				ib = DSS.activeCircuit.getBusList().find(busName);
				if (ib >= 0) {
					bus = DSS.activeCircuit.getBus(ib);
					parser.getNextParam();
					if (swapXY) {
						bus.setY( parser.makeDouble() );
					} else {
						bus.setX( parser.makeDouble() );
					}
					parser.getNextParam();
					if (swapXY) {
						bus.setX( parser.makeDouble() );
					} else {
						bus.setY( parser.makeDouble() );
					}
					bus.setCoordDefined(true);
				}
			}  // ignore a bus that's not in the circuit

			br.close();
			dis.close();
		} catch (Exception e) {
			DSS.doSimpleMsg("Bus coordinate file: \"" + param + "\" not found.", 275);
		}

		return result;
	}

	public static int doMakePosSeq() {
		DSS.activeCircuit.setPositiveSequence(true);

		for (CktElement cktElem : DSS.activeCircuit.getCktElements())
			cktElem.makePosSequence();

		return 0;
	}

	private static int atLeast(int i, int j) {
		if (j < i) {
			return i;
		} else {
			return j;
		}
	}

	public static void doSetReduceStrategy(String s) {

		DSS.activeCircuit.setReductionStrategyString(s);
		DSS.auxParser.setCmdString(s);
		DSS.auxParser.getNextParam();
		String param = DSS.auxParser.makeString().toUpperCase();
		DSS.auxParser.getNextParam();
		String param2 = DSS.auxParser.makeString();

		DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.DEFAULT);
		if (param.length() == 0)
			return;  // no option given

		switch (param.charAt(0)) {
		case 'B':
			DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.BREAK_LOOP);
			break;
		case 'D':
			DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.DEFAULT);
			break;
		case 'E':
			DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.DANGLING);
			break;
		case 'M':
			DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.MERGE_PARALLEL);
			break;
		case 'T':
			DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.TAP_ENDS);
			DSS.activeCircuit.setReductionMaxAngle(15.0);  // default
			if (param2.length() > 0)
				DSS.activeCircuit.setReductionMaxAngle(DSS.auxParser.makeDouble());
			break;
		case 'S':  // stubs
			if (Util.compareTextShortest(param, "SWITCH") == 0) {
				DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.SWITCHES);
			} else {
				DSS.activeCircuit.setReductionZmag(0.02);
				DSS.activeCircuit.setReductionStrategy(ReductionStrategyType.STUBS);
				if (param2.length() > 0)
					DSS.activeCircuit.setReductionZmag(DSS.auxParser.makeDouble());
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

		int result = 0;

		Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString().toUpperCase();

		// initialize the checked flag for all circuit elements
		Circuit ckt = DSS.activeCircuit;

		for (CktElement cktElem : ckt.getCktElements())
			cktElem.setChecked(false);

		if (param.length() == 0)
			param = "A";

		switch (param.charAt(0)) {
		case 'A':
			for (EnergyMeterObj MetObj : ckt.getEnergyMeters())
				MetObj.interpolateCoordinates();
			break;
		default:
			/* Interpolate a specific meter */
			devClassIndex = DSS.classNames.find("energymeter");
			if (devClassIndex >= 0) {
				meterClass = (EnergyMeter) DSS.DSSClassList.get(devClassIndex);
				if (meterClass.setActive(param)) {  // try to set it active
					((EnergyMeterObj) meterClass.getActiveObj()).interpolateCoordinates();
				} else {
					DSS.doSimpleMsg("EnergyMeter \""+param+"\" not found.", 277);
				}
			}
			break;
		}

		return result;
	}

	/**
	 * Rewrites designated file, aligning the fields into columns.
	 */
	public static int doAlignFileCmd() {
		int result = 0;
		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		if (new File(param).exists()) {
			if (!Util.rewriteAlignedFile(param))
				result = 1;
		} else {
			DSS.doSimpleMsg("File \""+param+"\" does not exist.", 278);
			result = 1;
		}

		if (result == 0)
			Util.fireOffEditor(DSS.globalResult);

		return result;
	}

	/**
	 * Sends Monitors, Loadshapes, GrowthShapes, or TCC Curves to TOP
	 * as an STO file.
	 */
	public static int doTOPCmd() {
		int result = 0;
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
//		case 'G':
//			DSSGlobals.getInstance().getGrowthShapeClass().tOPExportAll();
//			break;
//		case 'T':
//			DSSGlobals.getInstance().getTCC_CurveClass().tOPExportAll();
//			break;
		default:
			DSS.monitorClass.TOPExport(objName);
			break;
		}
		return result;
	}

	public static void doSetNormal(double pctNormal) {
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {
			pctNormal = pctNormal * 0.01;
			for (LineObj line : ckt.getLines())
				line.setNormAmps(pctNormal * line.getEmergAmps());
		}
	}

	/**
	 * Rotate about the center of the coordinates.
	 */
	public static int doRotateCmd() {
		double angle, xmin, xmax, ymin, ymax, xc, yc;
		Bus bus;
		Complex a, vector;

		int result = 0;
		Circuit ckt = DSS.activeCircuit;
		if (ckt != null) {

			Parser.getInstance().getNextParam();
			angle = Parser.getInstance().makeDouble() * DSS.PI / 180.0;   // deg to rad

			a = new Complex(Math.cos(angle), Math.sin(angle));
			xmin =  1.0e50;
			xmax = -1.0e50;
			ymin =  1.0e50;
			ymax = -1.0e50;
			for (int i = 0; i < ckt.getNumBuses(); i++) {
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

			for (int i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBus(i).isCoordDefined()) {
					bus = ckt.getBus(i);
					vector = new Complex(bus.getX() - xc, bus.getY() - yc);
					vector = vector.multiply(a);
					bus.setX(xc + vector.getReal());
					bus.setY(yc + vector.getImaginary());
				}
			}
		}
		return result;
	}

	public static int doVDiffCmd() {
		// TODO Implement this method.
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns summary in global result string.
	 */
	public static int doSummaryCmd() {
		String s;
		Complex cLosses, cPower;
		final String CRLF = DSS.CRLF;

		Circuit ckt = DSS.activeCircuit;

		int result = 0;
		s = "";
		if (ckt.isSolved()) {
			s = s + "Status = SOLVED" + DSS.CRLF;
		} else {
			s = s + "Status = NOT Solved" + CRLF;
		}
		s = s + "Solution Mode = " + Util.getSolutionModeID() + CRLF;
		s = s + "Number = " + String.valueOf(ckt.getSolution().getNumberOfTimes()) + CRLF;
		s = s + "Load Mult = " + String.format("%5.3f", ckt.getLoadMultiplier()) + CRLF;
		s = s + "Devices = " + String.format("%d", ckt.getNumDevices()) + CRLF;
		s = s + "Buses = " + String.format("%d", ckt.getNumBuses()) + CRLF;
		s = s + "Nodes = " + String.format("%d", ckt.getNumNodes()) + CRLF;
		s = s + "Control Mode =" + Util.getControlModeID() + CRLF;
		s = s + "Total Iterations = " + String.valueOf(ckt.getSolution().getIteration()) + CRLF;
		s = s + "Control Iterations = " + String.valueOf(ckt.getSolution().getControlIteration()) + CRLF;
		s = s + "Max Sol Iter = " + String.valueOf(ckt.getSolution().getMostIterationsDone()) + CRLF;
		s = s + " " + CRLF;
		s = s + " - Circuit Summary -" + CRLF;
		s = s + " " + CRLF;
		if (ckt != null) {
			s = s + String.format("Year = %d ", ckt.getSolution().getYear()) + CRLF;
			s = s + String.format("Hour = %d ", ckt.getSolution().getIntHour()) + CRLF;
			s = s + "Max pu. voltage = " + String.format("%-.5g ", Util.getMaxPUVoltage()) + CRLF;
			s = s + "Min pu. voltage = " + String.format("%-.5g ", Util.getMinPUVoltage(true)) + CRLF;
			cPower = Util.getTotalPowerFromSources().multiply(0.000001);  // MVA
			s = s + String.format("Total Active Power:   %-.6g MW", cPower.getReal()) + CRLF;
			s = s + String.format("Total Reactive Power: %-.6g Mvar", cPower.getImaginary()) + CRLF;
			cLosses = ckt.getLosses().multiply(0.000001);
			if (cPower.getReal() != 0.0) {
				s = s + String.format("Total Active Losses:   %-.6g MW, (%-.4g %%)", cLosses.getReal(), (cLosses.getReal() / cPower.getReal() * 100.0)) + CRLF;
			} else {
				s = s + "Total Active Losses:   ****** MW, (**** %%)" + CRLF;
			}
			s = s + String.format("Total Reactive Losses: %-.6g Mvar", cLosses.getImaginary()) + CRLF;
			s = s + String.format("Frequency = %-g Hz", ckt.getSolution().getFrequency()) + CRLF;
			s = s + "Mode = " + Util.getSolutionModeID() + CRLF;
			s = s + "Control Mode = " + Util.getControlModeID() + CRLF;
			s = s + "Load Model = " + Util.getLoadModel() + CRLF;
		}

		DSS.globalResult = s;

		return result;
	}

	public static int doDistributeCmd() {
		Parser parser = Parser.getInstance();
		int result = 0;
		int paramPointer = -1;
		/* Defaults */
		double kW = 1000.0;
		String how = "Proportional";
		int skip = 1;
		double PF = 1.0;
		String fileName = "DistGenerators.dss";

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
				how = parser.makeString();
				break;
			case 2:
				skip = parser.makeInteger();
				break;
			case 3:
				PF = parser.makeDouble();
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

		Util.makeDistributedGenerators(kW, PF, how, skip, fileName);

		return result;
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
					iRegisters[i - 1] = (int) dRegisters[i];
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

		iRegisters = null;

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
				if (Util.compareTextShortest(paramName, "CASE1") == 0) {
					paramPointer = 0;
				} else if (Util.compareTextShortest(paramName, "CASE2") == 0) {
					paramPointer = 1;
				} else if (Util.compareTextShortest(paramName, "REGISTER") == 0) {
					paramPointer = 2;
				} else if (Util.compareTextShortest(paramName, "METER") == 0) {
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
		caseNames.clear();
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
				case 0:  // List of case names
					DSS.auxParser.setCmdString(param);
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
						iRegisters[i] = (int) dRegisters[i];  // TODO: check zero based indexing
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

		iRegisters = null;
		caseNames.clear();

		return 0;
	}

	public static int doVisualizeCmd() {
		int devIndex;
		boolean unknown;
		int quantity;
		DSSObject elem;

		int result = 0;

		// abort if no circuit or solution
		if (DSS.activeCircuit == null) {
			DSS.doSimpleMsg("No circuit created.", 24721);
			return result;
		}

		if ((DSS.activeCircuit.getSolution() == null) || (DSS.activeCircuit.getSolution().getNodeV() == null)) {
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
				if (Util.compareTextShortest(paramName, "WHAT") == 0) {
					paramPointer = 0;
				} else if (Util.compareTextShortest(paramName, "ELEMENT") == 0) {
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

		/*--------------------------------------------------------------*/

		devIndex = Util.getCktElementIndex(elemName);
		if (devIndex >= 0) {  //  element must already exist
			elem = DSS.activeCircuit.getCktElements().get(devIndex);
			if (elem instanceof CktElement) {
				DSSPlot.getDSSPlotObj().doVisualizationPlot((CktElement) elem, quantity);
			} else {
				DSS.doSimpleMsg(elem.getName() + " must be a circuit element type!", 282);   // wrong type
			}
		} else {
		DSS.doSimpleMsg("Requested Circuit element: \"" + elemName + "\" not found.", 282);  // did not find it ..
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
		LineObj pLine1, pLine2;
		Line lineClass;
		int traceDirection;
		int nPhases;

		int result = 0;
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
			DSS.doSimpleMsg("Both Line1 and Line2 must be specified!", 28702);
			return result;
		}

		if (!lineCodeSpecified && !geometrySpecified) {
			DSS.doSimpleMsg("Either a new LineCode or a Geometry must be specified!", 28703);
			return result;
		}

		lineClass = (Line) DSS.DSSClassList.get(DSS.classNames.find("Line"));
		pLine1 = (LineObj) lineClass.find(line1);
		pLine2 = (LineObj) lineClass.find(line2);

		if (pLine1 == null || pLine2 == null) {
			if (pLine1 == null) {
				DSS.doSimpleMsg("Line."+line1+" not found.", 28704);
			} else if (pLine2 == null) {
				DSS.doSimpleMsg("Line."+line2+" not found.", 28704);
			}
			return result;
		}

		/* Now check to make sure they are in the same meter's zone */
		if (pLine1.getMeterObj() == null || pLine2.getMeterObj() == null) {
			DSS.doSimpleMsg("Error: Both Lines must be in the same EnergyMeter zone. One or both are not in any meter zone.", 28705);
			return result;
		}

		if (pLine1.getMeterObj() != pLine2.getMeterObj()) {
			DSS.doSimpleMsg("Error: Line1 is in EnergyMeter."+pLine1.getMeterObj().getName()+
					" zone while Line2 is in EnergyMeter."+pLine2.getMeterObj().getName()+ " zone. Both must be in the same Zone.", 28706);
			return result;
		}

		/* Since the lines can be given in either order, Have to check to see
		 * which direction they are specified and find the path between them.
		 */
		traceDirection = 0;
		if (Util.isPathBetween(pLine1, pLine2))
			traceDirection = 1;
		if (Util.isPathBetween(pLine2, pLine1))
			traceDirection = 2;

		if (lineCodeSpecified) {
			editString = "LineCode=" + lineCode;
		} else {
			editString = "Geometry=" + geometry;
		}

		// append myEditString onto the end of the edit string to change the linecode or geometry
		editString = String.format("%s  %s", editString, myEditString);

		switch (traceDirection) {
		case 1:
			Util.traceAndEdit(pLine1, pLine2, nPhases, editString);
			break;
		case 2:
			Util.traceAndEdit(pLine2, pLine1, nPhases, editString);
			break;
		default:
			DSS.doSimpleMsg("Traceback path not found between Line1 and Line2.", 28707);
			return result;
		}
		return result;
	}

	public static int doAddMarkerCmd() {
		String busName = "";
		int busIdx;
		Bus bus;
		Circuit ckt;
		Parser parser = Parser.getInstance();

		int result = 0;
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

		ckt = DSS.activeCircuit;

		busIdx = ckt.getBusList().find(busName);
		if (busIdx >= 0) {
			bus = ckt.getBus(busIdx);
			if (bus.isCoordDefined()) {
				DSSGraphDeclarations.addNewMarker(bus.getX(), bus.getY(), DSSPlot.getAddMarkerColor(), DSSPlot.getAddMarkerCode(), DSSPlot.getAddMarkerSize());
				DSSGraphDeclarations.showGraph();
			} else {
				DSS.doSimpleMsg("Bus coordinates not defined for bus " + busName, 28709);
			}
		} else {
			DSS.doSimpleMsg("Bus not found.", 28708);
		}

		return result;
	}

	public static int doSetLoadAndGenKVCmd() {
		Bus pBus;
		String sBus;
		int iBus;
		double kvln;

		Circuit ckt = DSS.activeCircuit;

		int result = 0;
		for (LoadObj pLoad : ckt.getLoads()) {
			Load.activeLoadObj = pLoad;  // for updateVoltageBases to work
			sBus = Util.stripExtension( pLoad.getBus(0) );
			iBus = ckt.getBusList().find(sBus);
			pBus = ckt.getBus(iBus);
			kvln = pBus.getKVBase();
			if (pLoad.getConnection() == 1 || pLoad.getNumPhases() == 3) {
				pLoad.setKVLoadBase(kvln * DSS.SQRT3);
			} else {
				pLoad.setKVLoadBase(kvln);
			}
			pLoad.updateVoltageBases();
			pLoad.recalcElementData();
		}

		for (GeneratorObj pGen : ckt.getGenerators()) {
			sBus = Util.stripExtension( pGen.getBus(0) );
			iBus = ckt.getBusList().find(sBus);
			pBus = ckt.getBus(iBus);
			kvln = pBus.getKVBase();
			if (pGen.getConnection() == 1 || pGen.getNumPhases() > 1) {
				pGen.setPresentKV(kvln * DSS.SQRT3);
			} else {
				pGen.setPresentKV(kvln);
			}
			pGen.recalcElementData();
		}

		return result;
	}

	public static int doUUIDsCmd() {
		// TODO Implement this method.
		throw new UnsupportedOperationException();
	}

	public static int doCvrtLoadshapesCmd() {
		LoadShapeObj pLoadShape;
		int iLoadshape;
		LoadShape loadShapeClass;
		String action;

		Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();

		if (param.length() == 0)
			param = "s";

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
		File f = new File(fileName);
		PrintWriter pw;
		try {
			pw = new PrintWriter(f);

			iLoadshape = loadShapeClass.getFirst();
			while (iLoadshape >= 0) {
				pLoadShape = (LoadShapeObj) loadShapeClass.getActiveObj();
				Parser.getInstance().setCmdString(action);
				pLoadShape.edit();
				pw.println(String.format("new loadShape.%s npts=%d interval=%.8g %s", pLoadShape.getName(), pLoadShape.getNumPoints(), pLoadShape.getInterval(), DSS.globalResult));
				iLoadshape = loadShapeClass.getNext();
			}

			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		int result = 0;
		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		sNode1 = param;
		if (paramName.indexOf('2') >= 0)
			sNode2 = param;

		paramName = Parser.getInstance().getNextParam();
		param = Parser.getInstance().makeString();
		sNode2 = param;
		if (paramName.indexOf('1') > 0)
			sNode1 = param;

		// get first node voltage
		DSS.auxParser.setToken(sNode1);
		nodeBuffer[0] = 1;
		sBusName = DSS.auxParser.parseAsBusName(numNodes, nodeBuffer);
		iBusIdx = DSS.activeCircuit.getBusList().find(sBusName);
		if (iBusIdx >= 0) {
			b1ref = DSS.activeCircuit.getBus(iBusIdx).find(nodeBuffer[0]);
		} else {
			DSS.doSimpleMsg(String.format("Bus %s not found.", sBusName), 28709);
			return result;
		}

		V1 = DSS.activeCircuit.getSolution().getNodeV(b1ref);

		// get 2nd node voltage
		DSS.auxParser.setToken(sNode2);
		nodeBuffer[0] = 1;
		sBusName = DSS.auxParser.parseAsBusName(numNodes, nodeBuffer);
		iBusIdx = DSS.activeCircuit.getBusList().find(sBusName);
		if (iBusIdx > 0) {
			b2ref = DSS.activeCircuit.getBus(iBusIdx).find(nodeBuffer[0]);
		} else {
			DSS.doSimpleMsg(String.format("Bus %s not found.", sBusName), 28710);
			return result;
		}

		V2 = DSS.activeCircuit.getSolution().getNodeV(b2ref);

		VNodeDiff = V1.subtract(V2);
		DSS.globalResult = String.format("%.7g, V,    %.7g, deg  ", VNodeDiff.abs(), ComplexUtil.degArg( VNodeDiff ));

		return result;
	}

	public static int doRephaseCmd() {
		String startLine = "";
		String newPhases = "";
		LineObj pStartLine;
		Line lineClass;

		int result = 0;
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
				DSS.doSimpleMsg("Error: Unknown parameter on command line: "+param, 28711);
				break;
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		lineClass = (Line) DSS.DSSClassList.get( DSS.classNames.find("Line") );
		pStartLine = (LineObj) lineClass.find( Util.stripClassName(startLine) );
		if (pStartLine == null) {
			DSS.doSimpleMsg("Starting line ("+startLine+") not found.", 28712);
			return result;
		}
		/* Check for some error conditions and abort if necessary */
		if (pStartLine.getMeterObj() == null) {
			DSS.doSimpleMsg("Starting line must be in an EnergyMeter zone.", 28713);
			return result;
		}

		if (!(pStartLine.getMeterObj() instanceof EnergyMeterObj)) {
			DSS.doSimpleMsg("Starting line must be in an EnergyMeter zone.", 28714);
			return result;
		}

		Util.goForwardAndRephase(pStartLine, newPhases, myEditString, scriptFileName, transfStop);

		return result;
	}

	public static int doSetBusXYCmd() {
		String busName = "";
		double XVal = 0.0;
		double YVal = 0.0;

		int result = 0;
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
				XVal = Parser.getInstance().makeDouble();
				break;
			case 2:
				YVal = Parser.getInstance().makeDouble();
				break;
			default:
				DSS.doSimpleMsg("Error: Unknown parameter on command line: "+param, 28721);
				break;
			}

			int iB = DSS.activeCircuit.getBusList().find(busName);
			if (iB >= 0) {
				DSS.activeCircuit.getBus(iB).setX(XVal);
				DSS.activeCircuit.getBus(iB).setY(YVal);
				DSS.activeCircuit.getBus(iB).setCoordDefined(true);
			} else {
				DSS.doSimpleMsg("Error: Bus \"" + busName + "\" not found.", 28722);
			}

			paramName = Parser.getInstance().getNextParam();
			param = Parser.getInstance().makeString();
		}

		return result;
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
		double[] VArray;
		int cyclesPerSample;
		int lamp;
		double[][] pstArray;
		int nPst;
		int i;
		String s;
		double freq;

		Parser parser = Parser.getInstance();

		int result = 0;
		VArray   = null;
		pstArray = new double[1][];
		npts     = 0;
		lamp     = 120;  // 120 or 230
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
				VArray = Util.resizeArray(VArray, npts);
				break;
			case 1:
				npts = Util.interpretDblArray(param, npts, VArray);
				break;
			case 3:
				cyclesPerSample = (int) Math.round(DSS.activeCircuit.getSolution().getFrequency() * parser.makeDouble());
				break;
			case 4:
				freq = parser.makeDouble();
				break;
			case 5:
				lamp = parser.makeInteger();
				break;
			default:
				DSS.doSimpleMsg("Error: Unknown Parameter on command line: " + param, 28722);
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (npts > 10) {
			nPst = PstCalc.pstRMS(pstArray, VArray, freq, cyclesPerSample, npts, lamp);
			// put resulting pst array in the result string
			s = "";
			for (i = 0; i < nPst; i++) {
				s = s + String.format("%.8g, ", pstArray[i]);
			}
			DSS.globalResult = s;
		} else {
			DSS.doSimpleMsg("Insuffient number of points for Pst Calculation.", 28723);
		}

		VArray = null;
		pstArray = null;

		return result;
	}

}
