package com.epri.dss.executive.impl;

import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSCircuit.ReductionStrategyType;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSForms;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.GeneratorObj;
import com.epri.dss.conversion.LoadObj;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.CapacitorObj;
import com.epri.dss.delivery.LineObj;
import com.epri.dss.delivery.ReactorObj;
import com.epri.dss.delivery.impl.CapacitorObjImpl;
import com.epri.dss.delivery.impl.ReactorObjImpl;
import com.epri.dss.executive.Executive;
import com.epri.dss.general.impl.DSSObjectImpl;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.meter.MonitorObj;
import com.epri.dss.meter.SensorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.plot.DSSPlot;
import com.epri.dss.plot.impl.DSSPlotImpl;
import com.epri.dss.shared.Dynamics;
import com.epri.dss.shared.impl.Complex;

public class ExecHelper {

	private ExecHelper() {
	}

	/**
	 * Looks for object definition:
	 * 
	 *   ParamName = 'object' if given
	 *   and the name of the object
	 *   
	 *   Object=Capacitor.C1
	 *   or just Capacitor.C1
	 *   
	 * If no dot, last class is assumed.
	 */
	public static void getObjClassAndName(String ObjClass, String ObjName) {
		Parser parser = Parser.getInstance();
		
		ObjClass = "";
		ObjName = "";
		String ParamName = parser.getNextParam().toLowerCase();
		String Param = parser.makeString();
		if (ParamName.length() > 0)  // If specified, must be object or an abbreviation.
			if (Utilities.compareTextShortest(ParamName, "object") != 0) {
				DSSGlobals.getInstance().doSimpleMsg("object=Class.Name expected as first parameter in command."+ DSSGlobals.CRLF + parser.getCmdString(), 240);
				return;
			}

		Utilities.parseObjectClassandName(Param, ObjClass, ObjName);  // see DSSGlobals
	}

	/**
	 * Process the New Command
	 * new type=xxxx name=xxxx  editstring
	 * 
	 * If the device being added already exists, the default behaviour is to
	 * treat the New command as an Edit command.  This may be overridden
	 * by setting the DuplicatesAllowed VARiable to true, in which case,
	 * the New command always results in a new device being added.
	 */
	public static int doNewCmd() {
		String ObjClass = "", ObjName = "";
		int Handle = 0;
		int Result = 0;
		
		getObjClassAndName(ObjClass, ObjName);  // TODO: Check ObjClass and ObjName get set.

		if (ObjClass.equals("solution")) {
			DSSGlobals.getInstance().doSimpleMsg("You cannot create new Solution objects through the command interface.", 241);
			return Result;
		}

		if (ObjClass.equals("circuit")) {
			DSSGlobals.getInstance().makeNewCircuit(ObjName);  // Make a new circuit
			Utilities.clearEventLog();  // Start the event log in the current directory
		} else {
			// Everything else must be a circuit element or DSS Object
			Handle = addObject(ObjClass, ObjName);
		}

		if (Handle == 0) Result = 1;
			
		return Result;
	}

	/**
	 * edit type=xxxx name=xxxx  editstring
	 */
	public static int doEditCmd() {
		String ObjType = "", ObjName = "";
		int Result = 0;

		getObjClassAndName(ObjType, ObjName);

		if (ObjType.equals("circuit")) {
			// Do nothing
		} else {
			// Everything else must be a circuit element
			Result = editObject(ObjType, ObjName);
		}
			
		return Result;
	}

	/**
	 * This routine should be recursive.
	 * So you can redirect input an arbitrary number of times.
	 * 
	 * If Compile, makes directory of the file the new home directory.
	 * If not Compile (is simple redirect), return to where we started.
	 */
	public static int doRedirect(boolean IsCompile) {
		File Fin;
		String ParamName, InputLine, CurrDir, SaveDir;
		DSSGlobals Globals = DSSGlobals.getInstance();
		int Result = 0;

		// Get next parm and try to interpret as a file name
		ParamName = Parser.getInstance().getNextParam();
		ExecCommandsImpl.setRedirFile(Utilities.expandFileName(Parser.getInstance().makeString()));	

		if (!ExecCommandsImpl.getRedirFile().equals("")) {
			SaveDir = System.getProperty("user.dir");

			try {
				Fin = new File(ExecCommandsImpl.getRedirFile());
				if (IsCompile) 
					Globals.setLastFileCompiled(ExecCommandsImpl.getRedirFile());
			} catch (Exception e) {
				// Couldn't find file  Try appending a '.dss' to the file name
				// If it doesn't already have an extension
				if (ExecCommandsImpl.getRedirFile().indexOf('.') == -1) {
					ExecCommandsImpl.setRedirFile(ExecCommandsImpl.getRedirFile() + ".dss");
					try {
						Fin = new File(ExecCommandsImpl.getRedirFile());
					} catch (Exception ex) {
						Globals.doSimpleMsg("Redirect File: \"" + ExecCommandsImpl.getRedirFile() + "\" Not Found.", 242);
						Globals.setSolutionAbort(true);
						return Result;
					}
				} else {
					Globals.doSimpleMsg("Redirect File: \""+ExecCommandsImpl.getRedirFile()+"\" Not Found.", 243);
					Globals.setSolutionAbort(true);
					return Result;  // Already had an extension, so just Bail
				}
			}
	
			// OK, we finally got one open, so we're going to continue
			try {
				try {
					// Change Directory to path specified by file in CASE that
					// loads in more files
					CurrDir = Utilities.extractFileDir(ExecCommandsImpl.getRedirFile());
	//				setCurrentDir(CurrDir);
					if (IsCompile)
						Globals.setDataPath(CurrDir);  // change DSSDataDirectory
	
					Globals.setRedirect_Abort(false);
					Globals.setIn_Redirect(true);
	
					FileInputStream fstream = new FileInputStream(Fin);
					DataInputStream in = new DataInputStream(in);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
						
					while (((InputLine = br.readLine()) != null) || Globals.isRedirect_Abort()) {
						if (!Globals.isSolutionAbort()) {
							ExecCommandsImpl.processCommand(InputLine);
						} else {
							Globals.setRedirect_Abort(true);  // Abort file if solution was aborted
						}
					}
	
					if (Globals.getActiveCircuit() != null)
						Globals.getActiveCircuit().setCurrentDirectory(CurrDir + "\"");
				} catch (Exception e) {
					Globals.doErrorMsg("DoRedirect"+DSSGlobals.CRLF+"Error Processing Input Stream in Compile/Redirect.",
								e.getMessage(),
								"Error in File: \"" + ExecCommandsImpl.getRedirFile() + "\" or Filename itself.", 244);
				}
			} finally {
				Fin.close();
				Globals.setIn_Redirect(false);
				if (IsCompile) {
					Globals.setDataPath(CurrDir); // change DSSDataDirectory
					Globals.setLastCommandWasCompile(true);
				} else {
	//				setCurrentDir(SaveDir);    // set back to where we were for redirect, but not compile
				}
			}
		} // else ignore altogether IF null filename

		return Result;
	}

	/**
	 * Select active object.
	 * select element=elementname terminal=terminalnumber
	 */
	public static int doSelectCmd() {
		String ObjClass = "", ObjName = "";
		String ParamName, Param;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 1;

		getObjClassAndName(ObjClass, ObjName);  // Parse Object class and name

		if ((ObjClass.length() == 0) && (ObjName.length() == 0))
			return Result;  // select active obj if any

		if (ObjClass.equals("circuit")) {
			setActiveCircuit(ObjName);
		} else {
			// Everything else must be a circuit element
			if (ObjClass.length() > 0)
				DSSClassDefs.setObjectClass(ObjClass);

			Globals.setActiveDSSClass(Globals.getDSSClassList().get(Globals.getLastClassReferenced()));
			if (Globals.getActiveDSSClass() != null) {
				if (!Globals.getActiveDSSClass().setActive(ObjName)) {
					// scroll through list of objects until a match
					Globals.doSimpleMsg("Error! Object \"" + ObjName + "\" not found."+ DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 245);
					Result = 0;
				} else {
					switch (Globals.getActiveDSSObject().getDSSObjType()) {
					case DSSClassDefs.DSS_OBJECT:
						// do nothing for general DSS object
					default:  // for circuit types, set ActiveCircuit Element, too
						Globals.getActiveCircuit().setActiveCktElement((DSSCktElement) Globals.getActiveDSSClass().getActiveObj());
						// Now check for active terminal designation
						ParamName = Parser.getInstance().getNextParam().toLowerCase();
						Param = Parser.getInstance().makeString();
						if (Param.length() > 0) {
							Globals.getActiveCircuit().getActiveCktElement().setActiveTerminalIdx(Parser.getInstance().makeInteger());
						} else {
							// TODO: Check zero indexing.
							Globals.getActiveCircuit().getActiveCktElement().setActiveTerminalIdx(1);  // default to 1
						}
						Globals.setActiveBus( Globals.getActiveCircuit().getActiveCktElement().getBus(Globals.getActiveCircuit().getActiveCktElement().getActiveTerminalIdx()) );
					}
				}
			} else {
				Globals.doSimpleMsg("Error! Active object type/class is not set.", 246);
				Result = 0;
			}
		}
		
		return Result;
	}

	/**
	 * more editstring  (assumes active circuit element)
	 */
	public static int doMoreCmd() {
		if (DSSGlobals.getInstance().getActiveDSSClass() != null) {
			return DSSGlobals.getInstance().getActiveDSSClass().edit();
		} else {
			return 0;
		}
	}

	public static int doSaveCmd() {
		// TODO: Implement this method
		throw new UnsupportedOperationException();
	}

	public static int doClearCmd() {
		Executive.DSSExecutive.clear();
		return 0;
	}

	public static int doHelpCmd() {
		DSSForms.showHelpForm();
		return 0;
	}

	/**
	 * Force all monitors and meters in active circuit to take a sample.
	 */
	public static int doSampleCmd() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		
		for (MonitorObj mon : ckt.getMonitors()) 
			mon.takeSample();
			
		for (EnergyMeterObj meter : ckt.getEnergyMeters()) 
			meter.takeSample();
		
		for (GeneratorObj gen : ckt.getGenerators()) 
			gen.takeSample();
		
		return 0;
	}

	public static int doSolveCmd() {
		// Just invoke solution obj's editor to pick up parsing
		// and execute rest of command
		Solution.ActiveSolutionObj = DSSGlobals.getInstance().getActiveCircuit().getSolution();  
		return DSSGlobals.getInstance().getSolutionClass().edit();
	}

	/**
	 * Parses the object off the line and sets it active as a CktElement.
	 */
	public static int setActiveCktElement() {
		String ObjType = "", ObjName = "";
		DSSGlobals Globals = DSSGlobals.getInstance();
		int Result = 0;

		getObjClassAndName(ObjType, ObjName);

		if (ObjType.equals("circuit")) {
			// Do nothing
		} else {
			if (ObjType.equals(Globals.getActiveDSSClass().getName())) {
				Globals.setLastClassReferenced(Globals.getClassNames().find(ObjType));
				
				switch (Globals.getLastClassReferenced()) {
				case 0:
					Globals.doSimpleMsg("Object Type \"" + ObjType + "\" not found."+ DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 253);
					Result = 0;
					return Result;
				default:
					// intrinsic and user Defined models
					Globals.setActiveDSSClass(Globals.getDSSClassList().get(Globals.getLastClassReferenced()));
					if (Globals.getActiveDSSClass().setActive(ObjName)) {
						// scroll through list of objects until a match
						switch (Globals.getActiveDSSObject().getDSSObjType()) {
						case DSSClassDefs.DSS_OBJECT:
							Globals.doSimpleMsg("Error in SetActiveCktElement: Object not a circuit Element."+ DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 254);
						default:
							Globals.getActiveCircuit().setActiveCktElement((DSSCktElement) Globals.getActiveDSSClass().getActiveObj());
							Result = 1;
						}
					}
				}
			}
		}
		
		return Result;
	}

	public static int doEnableCmd() {
		String ObjType = "", ObjName = "";
		DSSClass ClassPtr;
		CktElement CktElem;

		//Result = setActiveCktElement();
		//if (Result > 0) DSSGlobals.getInstance().getActiveCircuit().getActiveCktElement().setEnabled(true);

		int Result = 0;

		getObjClassAndName(ObjType, ObjName);

		if (ObjType.equals("circuit")) {
			// Do nothing
		} else {
			if (ObjType.length() > 0) {
				// only applies to CktElementClass objects
				ClassPtr = DSSClassDefs.getDSSClass(ObjType);
				if (ClassPtr != null) {
					
					if (ClassPtr.getDSSClassType() && DSSClassDefs.BASECLASSMASK) > 0) {
						// Everything else must be a circuit element
						if (ObjName.equals("*")) {
							// Enable all elements of this class
							for (int i = 0; i < ClassPtr.getElementCount(); i++) {
								CktElem = (CktElement) ClassPtr.getElementList().get(i);
								CktElem.setEnabled(true);
							}
						} else {
							// just load up the parser and call the edit routine for the object in question
							Parser.getInstance().setCmdString("Enabled=true");  // Will only work for CktElements
							Result = editObject(ObjType, ObjName);
						}
					}
					
				}
			}
		}
		
		return Result;
	}

	public static int doDisableCmd() {
		String ObjType = "", ObjName = "";
		DSSClass ClassPtr;
		CktElement CktElem;

		int Result = 0;

		getObjClassAndName(ObjType, ObjName);

		if (ObjType.equals("circuit")) {
			// Do nothing
		} else {
			if (ObjType.length() > 0) {
				// only applies to CktElementClass objects
				ClassPtr = DSSClassDefs.getDSSClass(ObjType);
				if (ClassPtr != null) {

					if (ClassPtr.getDSSClassType() && DSSClassDefs.BASECLASSMASK) {
						// Everything else must be a circuit element
						if (ObjName.equals("*")) {
							// Disable all elements of this class
							for (int i = 0; i < ClassPtr.getElementCount(); i++) {
								CktElem = (CktElement) ClassPtr.getElementList().get(i);
								CktElem.setEnabled(false);
							}
						}
					} else {
						// just load up the parser and call the edit routine for the object in question
						Parser.getInstance().setCmdString("Enabled=false");  // Will only work for CktElements
						Result = editObject(ObjType, ObjName);
					}
					
				}
			}
		}
		

		//Result = setActiveCktElement();
		//if (Result > 0) getActiveCircuit().getActiveCktElement().setEnabled(false);
		
		return Result;
	}

	public static int doPropertyDump() {
		// TODO Implement this method.
		throw new UnsupportedOperationException();
	}

	/** For interpreting time specified as an array "hour, sec". */
	public static void setTime() {
		double[] TimeArray = new double[2];
		Parser.getInstance().parseAsVector(2, TimeArray);
		
		SolutionObj Solution = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		
		Solution.setIntHour((int) TimeArray[0]);
		Solution.getDynaVars().t = TimeArray[1];
		Solution.updateDblHour();
	}

	public static void setActiveCircuit(String cktname) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		for (Circuit ckt : Globals.getCircuits()) 
			if (ckt.getName().equals(cktname)) {
				Globals.setActiveCircuit(ckt);
				return;
			}

		Globals.doSimpleMsg("Error! No circuit named \"" + cktname + "\" found." + DSSGlobals.CRLF +
				"Active circuit not changed.", 258);
	}

	public static void doLegalVoltageBases() {
		double[] Dummy = new double[100];  // Big Buffer
		int Num = Parser.getInstance().parseAsVector(100, Dummy);
		/* Parsing zero-fills the array */

		/* LegalVoltageBases is a zero-terminated array, so we have to allocate
		 * one more than the number of actual values}
		 */
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		ckt.setLegalVoltageBases(new double[Num + 1]);
		for (int i = 0; i < Num + 1; i++) 
			ckt.getLegalVoltageBases()[i] = Dummy[i];
	}

	/**
	 * Opens a terminal and conductor of a ckt Element.
	 * 
	 * Syntax:  "Open class.name term=xx cond=xx"
	 * If cond is omitted, all conductors are opened.
	 */
	public static int doOpenCmd() {
		int Terminal;
		int Conductor;
		String ParamName;
		Parser parser = Parser.getInstance();

		int retval = setActiveCktElement();
		if (retval > 0) {
			ParamName = parser.getNextParam();
			Terminal  = parser.makeInteger();
			ParamName = parser.getNextParam();
			Conductor = parser.makeInteger();
			
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			ckt.getActiveCktElement().setActiveTerminalIdx(Terminal);
			ckt.getActiveCktElement().setConductorClosed(Conductor, false);
			DSSGlobals.getInstance().setActiveBus(
					Utilities.stripExtension(ckt.getActiveCktElement().getBus(
							ckt.getActiveCktElement().getActiveTerminalIdx()) ) );
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Open Command: Circuit Element Not Found." +DSSGlobals.CRLF+parser.getCmdString(), 259);
		}
	
		return 0;
	}

	/**
	 * Closes a terminal and conductor of a ckt Element.
	 * 
	 * Syntax:  "Close class.name term=xx cond=xx"
	 * If cond is omitted, all conductors are opened.
	 */
	public static int doCloseCmd() {
		int Terminal;
		int Conductor;
		String ParamName;
		Parser parser = Parser.getInstance();

		int retval = setActiveCktElement();
		if (retval > 0) {
			ParamName = parser.getNextParam();                 
			Terminal = parser.makeInteger();
			ParamName = parser.getNextParam();
			Conductor = parser.makeInteger();
			
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

			ckt.getActiveCktElement().setActiveTerminalIdx(Terminal);
			ckt.getActiveCktElement().setConductorClosed(Conductor, true);
			DSSGlobals.getInstance().setActiveBus(
					Utilities.stripExtension(ckt.getActiveCktElement().getBus(
							ckt.getActiveCktElement().getActiveTerminalIdx()) ) );
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Close Command: Circuit Element Not Found."+DSSGlobals.CRLF+parser.getCmdString(), 260);
		}
		return 0;
	}

	public static int doResetCmd() {
		Parser parser = Parser.getInstance();
		int Result = 0;

		// Get next parm and try to interpret as a file name
		String ParamName = parser.getNextParam();
		String Param = parser.makeString().toUpperCase();
		if (Param.length() == 0) {
			doResetMonitors();
			doResetMeters();
			Utilities.doResetFaults();
			Utilities.doResetControls();
			Utilities.clearEventLog();
			Utilities.doResetKeepList();
		} else {
			switch (Param.charAt(0)) {
			case 'M':
				switch (Param.charAt(1)) {
				case 'O':
					// Monitor
					doResetMonitors();
				case 'E':
					// Meter
					doResetMeters();
				}
			case 'F':
				// Faults
				Utilities.doResetFaults();
			case 'C':
				// Controls
				Utilities.doResetControls();
			case 'E':
				// EventLog
				Utilities.clearEventLog();
			case 'K':
				Utilities.doResetKeepList();
			default:
				DSSGlobals.getInstance().doSimpleMsg("Unknown argument to Reset Command: \""+Param+"\"", 261);
			}
		}
			
		return 0;
	}
	
	private static void markCapAndReactorBuses() {
		DSSClass cls;
		CapacitorObj capElement;
		ReactorObj reacElement;
		int ObjRef;
		DSSGlobals Globals = DSSGlobals.getInstance();

		/* Mark all buses as keepers if there are capacitors or reactors on them */
		cls = DSSClassDefs.getDSSClass("capacitor");
		if (cls != null) {
			ObjRef = cls.getFirst();
			while (ObjRef > 0) {
				capElement = new CapacitorObjImpl(Globals.getActiveDSSObject());
				if (capElement.isShunt()) {
					if (capElement.isEnabled()) {
						Globals.getActiveCircuit().getBuses()[capElement.getTerminals()[0].getBusRef()].setKeep(true);
					}
				}
				ObjRef = cls.getNext();
			}
		}

		/* Now Get the Reactors */
		cls = DSSClassDefs.getDSSClass("reactor");
		if (cls != null) {
			ObjRef = cls.getFirst();
			while (ObjRef > 0) {
				reacElement = new ReactorObjImpl(Globals.getActiveDSSObject());
				if (reacElement.isShunt()) {
					try {
						if (reacElement.isEnabled())
							Globals.getActiveCircuit().getBuses()[reacElement.getTerminals()[0].getBusRef()].setKeep(true);
					} catch (Exception e) {
						Globals.doSimpleMsg(String.format("%s %s Reactor=%s Bus No.=%d ", e.getMessage(), DSSGlobals.CRLF, reacElement.getName(), reacElement.getNodeRef()[0]), 9999);
					}
				}
				ObjRef = cls.getNext();
			}
		}
	}

	public static int doReduceCmd() {
		EnergyMeter MeterClass;
		int DevClassIndex;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		// Get next parm and try to interpret as a file name
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString().toUpperCase();

		/* Mark Capacitor and Reactor buses as Keep so we don't lose them */
		markCapAndReactorBuses();

		if (Param.length() == 0) Param = "A";
		switch (Param.charAt(0)) {
		case 'A':
			for (EnergyMeterObj MeterObj : Globals.getActiveCircuit().getEnergyMeters()) 
				MeterObj.reduceZone();
		default:
			/* Reduce a specific meter */
			DevClassIndex = Globals.getClassNames().find("energymeter");
			if (DevClassIndex > 0) {  // TODO Check zero indexing
				MeterClass = (EnergyMeter) Globals.getDSSClassList().get(DevClassIndex);
				if (MeterClass.setActive(Param)) {   // Try to set it active
					EnergyMeterObj MeterObj = (EnergyMeterObj) MeterClass.getActiveObj();
					MeterObj.reduceZone();
				} else {
					Globals.doSimpleMsg("EnergyMeter \""+Param+"\" not found.", 262);
				}
			}
		}
		return 0;
	}

	public static int doResetMonitors() {
		for (MonitorObj Mon : DSSGlobals.getInstance().getActiveCircuit().getMonitors()) 
			Mon.resetIt();
		return 0;
	}
	
	public static int doFileEditCmd() {

		// Get next parm and try to interpret as a file name
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();

		if (new File(Param).exists()) {
			Utilities.fireOffEditor(Param);
		} else {
			DSSGlobals.getInstance().setGlobalResult("File \""+Param+"\" does not exist.");
		}
	
		return 1;
	}

	/**
	 * Parse strings such as
	 *     1. Classname.Objectname,Property  (full name)
	 *     2. Objectname.Property            (classname omitted)
	 *     3. Property                       (classname and objectname omitted
	 */
	public static void parseObjName(String fullName, String objName, String propName) {
		int DotPos1 = fullName.indexOf(".");
		switch (DotPos1) {
		case -1:
			objName = "";
			propName = fullName;
		default:
			propName = fullName.substring(DotPos1 + 1, (fullName.length() - DotPos1));  // TODO Check indexing.
			int DotPos2 = propName.indexOf(".");
			switch (DotPos2) {
			case -1:
				objName = fullName.substring(0, DotPos1 - 1);
			default:
				objName  = fullName.substring(0, DotPos1 + DotPos2 - 1);
				propName = propName.substring(DotPos2 + 1, propName.length() - DotPos2);
			}
		}
	}

	/**
	 * ? Command
	 * Syntax:  ? Line.Line1.R1 
	 */
	public static int doQueryCmd() {
		String ObjName = "", PropName = "";
		int Result = 0;
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();

		parseObjName(Param, ObjName, PropName);

		if (ObjName.equals("solution")) {  // special for solution
			Globals.setActiveDSSClass(Globals.getSolutionClass());
			Globals.setActiveDSSObject((DSSObjectImpl) Globals.getActiveCircuit().getSolution());
		} else {
			// Set Object Active
			Parser.getInstance().setCmdString("\"" + ObjName + "\"");
			doSelectCmd();
		}

		// Put property value in global VARiable
		int PropIndex = Globals.getActiveDSSClass().propertyIndex(PropName);
		if (PropIndex > 0) {
			Globals.setGlobalPropertyValue(Globals.getActiveDSSObject().getPropertyValue(PropIndex));
		} else {
			Globals.setGlobalPropertyValue("Property Unknown");
		}

		Globals.setGlobalResult(Globals.getGlobalPropertyValue());
		//MessageDlg(Param + ' = ' + GlobalPropertyValue,  mtCustom, [mbOK], 0);

		return Result;
	}

	public static int doResetMeters() {
		DSSGlobals.getInstance().getEnergyMeterClass().resetAll();
		return 0;
	}

	public static int doNextCmd() {
		// Get next parm and try to interpret as a file name
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();
		
		SolutionObj solution = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		switch (Param.toUpperCase().charAt(0)) {
		case 'Y':
			solution.setYear(solution.getYear() + 1);  // Year
		case 'H':
			solution.setIntHour(solution.getIntHour() + 1);  // Hour
		case 'T':
			solution.incrementTime();  // Time
		}	 
		
		return 0;
	}

	public static int doSetVoltageBases() {
		DSSGlobals.getInstance().getActiveCircuit().getSolution().setVoltageBases();
		return 0;
	}

	public static void doAboutBox() {
		if (DSSGlobals.getInstance().isNoFormsAllowed()) return;
		DSSForms.showAboutBox();
	}

	public static int addObject(String ObjType, String name) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();
		
		int Result = 0;

		// Search for class if not already active.
		// If nothing specified, LastClassReferenced remains.
		if (ObjType.equals(Globals.getActiveDSSClass().getName()))
			Globals.setLastClassReferenced(Globals.getClassNames().find(ObjType));
			
		switch (Globals.getLastClassReferenced()) {
		case 0:  // TODO Check zero indexing
			Globals.doSimpleMsg("New Command: Object Type \"" + ObjType + "\" not found." + DSSGlobals.CRLF + parser.getCmdString(), 263);
			Result = 0;
			return Result;
		default:
			// intrinsic and user Defined models
			// Make a new circuit element
			Globals.setActiveDSSClass(Globals.getDSSClassList().get(Globals.getLastClassReferenced()));

			// Name must be supplied
			if (name.length() == 0) {
				Globals.doSimpleMsg("Object Name Missing"+ DSSGlobals.CRLF + parser.getCmdString(), 264);
				return Result;
			}

			// now let's make a new object or set an existing one active, whatever the case
			switch (Globals.getActiveDSSClass().getDSSClassType()) {
			case DSSClassDefs.DSS_OBJECT:
				// These can be added WITHout having an active circuit
				// Duplicates not allowed in general DSS objects;
				if  (!Globals.getActiveDSSClass().setActive(name)) {
					Result = Globals.getActiveDSSClass().newObject(name);
					// Stick in object list to keep track of it.
					Globals.getDSSObjs().add(Globals.getActiveDSSObject());  
				}
			default:
				// These are circuit elements
				if (Globals.getActiveCircuit() == null) {
					Globals.doSimpleMsg("You Must Create a circuit first: \"new circuit.yourcktname\"", 265);
					return Result;
				}

				// If Object already exists. Treat as an Edit if duplicates not allowed
				if (Globals.getActiveCircuit().isDuplicatesAllowed()) {
					Result = Globals.getActiveDSSClass().newObject(name); // Returns index into this class
					Globals.getActiveCircuit().addCktElement(Result);   // Adds active object to active circuit
				} else {
					// Check to see if we can set it active first
					if (!Globals.getActiveDSSClass().setActive(name)) {
						Result = Globals.getActiveDSSClass().newObject(name);   // Returns index into this class
						Globals.getActiveCircuit().addCktElement(Result);   // Adds active object to active circuit
					} else {
						Globals.doSimpleMsg("Warning: Duplicate new element definition: \""+ Globals.getActiveDSSClass().getName()+"."+name+"\""+
									DSSGlobals.CRLF+ "Element being redefined.", 266);
					}
				}
			}

			// ActiveDSSObject now points to the object just added
			// If a circuit element, ActiveCktElement in ActiveCircuit is also set
			if (Result > 0) Globals.getActiveDSSObject().setClassIndex(Result);

			Globals.getActiveDSSClass().edit();    // Process remaining instructions on the command line
		}
		
		return Result;
	}

	public static int editObject(String ObjType, String name) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		Globals.setLastClassReferenced(Globals.getClassNames().find(ObjType));
		
		switch (Globals.getLastClassReferenced()) {
		case 0:  // TODO Check zero indexing
			Globals.doSimpleMsg("Edit Command: Object Type \"" + ObjType + "\" not found."+ DSSGlobals.CRLF + Parser.getInstance().getCmdString(), 267);
			Result = 0;
			return Result;
		default:
			// intrinsic and user Defined models
			// Edit the DSS object
			Globals.setActiveDSSClass(Globals.getDSSClassList().get(Globals.getLastClassReferenced()));
			if (Globals.getActiveDSSClass().setActive(name)) 	
				Result = Globals.getActiveDSSClass().edit();   // Edit the active object
		}
	
		return Result;
	}

	public static int doSetkVBase() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		int Result = 0;
		
		// Parse off next two items on line
		String ParamName = Parser.getInstance().getNextParam();
		String BusName = Parser.getInstance().makeString();

		ParamName = Parser.getInstance().getNextParam();
		double kVValue = Parser.getInstance().makeDouble();

		// Now find the bus and set the value
		Circuit ckt = Globals.getActiveCircuit();
		
		ckt.setActiveBusIndex(ckt.getBusList().find(BusName));

		if (ckt.getActiveBusIndex() > 0) {
			if (ParamName.equals("kvln")) {
				ckt.getBuses()[ckt.getActiveBusIndex()].setkVBase(kVValue);
			} else {
				ckt.getBuses()[ckt.getActiveBusIndex()].setkVBase(kVValue / DSSGlobals.SQRT3);
			}
			Result = 0;
			ckt.getSolution().setVoltageBaseChanged(true);
			// Solution.SolutionInitialized := FALSE;  // Force reinitialization
		} else {
			Result = 1;
			Globals.appendGlobalResult("Bus " + BusName + " Not Found.");
		}
		
		return Result;
	}

	/**
	 * Syntax can be either a list of bus names or a file specification:
	 *     File= ...
	 */
	public static void doAutoAddBusList(String S) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		String S2;

		Globals.getActiveCircuit().getAutoAddBusList().clear();

		// Load up auxiliary parser to reparse the array list or file name
		Globals.getAuxParser().setCmdString(S);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();

		/* Syntax can be either a list of bus names or a file specification:  File= ... */

		if (ParmName.equals("file")) {
			// load the list from a file

			try {
				File F = new File(Param);
				FileInputStream fstream = new FileInputStream(F);
				DataInputStream in = new DataInputStream(in);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
				while ((S2 = br.readLine()) != null) {
					Globals.getAuxParser().setCmdString(S2);
					ParmName = Globals.getAuxParser().getNextParam();
					Param = Globals.getAuxParser().makeString();
					if (Param.length() > 0) 
						Globals.getActiveCircuit().getAutoAddBusList().add(Param);
				}
				F.close();
			} catch (Exception e) {
				Globals.doSimpleMsg("Error trying to read bus list file. Error is: "+e.getMessage(), 268);
			}
		} else {
			// Parse bus names off of array list
			while (Param.length() > 0) {  // TODO Check zero indexing
				Globals.getActiveCircuit().getAutoAddBusList().add(Param);
				Globals.getAuxParser().getNextParam();
				Param = Globals.getAuxParser().makeString();
			}
		}
	}

	/**
	 * Set Keep flag on buses found in list so they aren't eliminated by
	 * some reduction algorithm.  This command is cumulative. To clear flag,
	 * use Reset Keeplist.
	 * 
	 * Syntax can be either a list of bus names or a file specification:  File= ...
	 */
	public static void doKeeperBusList(String S) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		String S2;
		int iBus;
		Circuit ckt;
		
		// Load up auxiliary parser to reparse the array list or file name
		Globals.getAuxParser().setCmdString(S);
		String ParmName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString();

		if (ParmName.equals("file")) {
			// load the list from a file

			try {
				File F = new File(Param);
				FileInputStream fstream = new FileInputStream(F);
				DataInputStream in = new DataInputStream(in);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
				while ((S2 = br.readLine()) != null) {
					Globals.getAuxParser().setCmdString(S2);
					ParmName = Globals.getAuxParser().getNextParam();
					Param = Globals.getAuxParser().makeString();
					if (Param.length() > 0) {
						ckt = Globals.getActiveCircuit();
						iBus = ckt.getBusList().find(Param);
						if (iBus > 0) ckt.getBuses()[iBus].setKeep(true);
					}
				}
				F.close();
			} catch (Exception e) {
				Globals.doSimpleMsg("Error trying to read bus list file "+Param+". Error is: "+e.getMessage(), 269);
			}
		} else {
			// Parse bus names off of array list
			while (Param.length() > 0) {
				ckt = Globals.getActiveCircuit();

				iBus = ckt.getBusList().find(Param);
				if (iBus > 0) ckt.getBuses()[iBus].setKeep(true);

				Globals.getAuxParser().getNextParam();
				Param = Globals.getAuxParser().makeString();
			}
		}
	}

	public static int doCktLossesCmd() {
		Complex LossValue;
		DSSGlobals Globals = DSSGlobals.getInstance();
		int Result = 0;
		
		if (Globals.getActiveCircuit() != null) {
			Globals.setGlobalResult("");
			LossValue = Globals.getActiveCircuit().getLosses();
			Globals.setGlobalResult(String.format("%10.5g, %10.5g", LossValue.getReal() * 0.001,  LossValue.getImaginary() * 0.001));
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}

		return Result;
	}

	public static int doCurrentsCmd() {
		Complex[] cBuffer;
		int nValues;
		int Result = 0;
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (Globals.getActiveCircuit() != null) {
			CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();
			
			nValues = cktElem.getNConds() * cktElem.getNTerms();
			Globals.setGlobalResult("");
			cBuffer = new Complex[nValues];
			cktElem.getCurrents(cBuffer);
			for (int i = 0; i < nValues; i++) 
				Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, %6.1f,", cBuffer[i].abs(), cBuffer[i].degArg()) );
			cBuffer = null;
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}

		return Result;
	}

	public static int doLossesCmd() {
		Complex LossValue;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();
			if (ckt.getActiveCktElement() != null) {
				Globals.setGlobalResult("");
				LossValue = ckt.getActiveCktElement().getLosses();
				Globals.setGlobalResult(String.format("%10.5g, %10.5g", LossValue.getReal() * 0.001, LossValue.getImaginary() * 0.001));
			}
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}
			
		return Result;
	}

	/**
	 * Returns Phase losses in kW, kVar.
	 */
	public static int doPhaseLossesCmd() {
		Complex[] cBuffer;
		DSSGlobals Globals = DSSGlobals.getInstance();
		int nValues;

		int Result = 0;

		if (Globals.getActiveCircuit() != null) {
			CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();

			nValues = cktElem.getNPhases();
			cBuffer = new Complex[nValues];
			Globals.setGlobalResult("");
			cktElem.getPhaseLosses(nValues, cBuffer);
			for (int i = 0; i < nValues; i++)
				Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, %10.5g,", cBuffer[i].getReal() * 0.001, cBuffer[i].getImaginary() * 0.001));
			cBuffer = null;
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}
		
		return Result;
	}

	public static int doPowersCmd() {
		Complex[] cBuffer;
		DSSGlobals Globals = DSSGlobals.getInstance();
		int nValues;

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();
			
			nValues = cktElem.getNConds() * cktElem.getNTerms();
			Globals.setGlobalResult("");
			cBuffer = new Complex[nValues];
			cktElem.getPhasePower(cBuffer);
			for (int i = 0; i < nValues; i++)
				Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, %10.5g,", cBuffer[i].getReal() * 0.001, cBuffer[i].getImaginary() * 0.001));
			cBuffer = null;
		} else {
			Globals.setGlobalResult("No Active Circuit");
		}
		
		return Result;
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
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();
			if (ckt.getActiveCktElement() != null) {
				CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();

				Globals.setGlobalResult("");
				if (cktElem.getNPhases() < 3) {
					for (i = 0; i < 3 * cktElem.getNTerms(); i++) 
						Globals.setGlobalResult( Globals.getGlobalResult() + " -1.0," );  // Signify n/A
				} else {
					nValues = cktElem.getNConds() * cktElem.getNTerms();
					cBuffer = new Complex[nValues];
					cktElem.getCurrents(cBuffer);
					for (int j = 0; j < cktElem.getNTerms(); j++) {
						k = (j - 1) * cktElem.getNConds();
						for (i = 0; i < 3; i++) 
							Iph[i] = cBuffer[k + i];
						MathUtil.phase2SymComp(Iph, I012);
						for (i = 0; i < 3; i++) 
							Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, ", I012[i].abs()) );
					}
					cBuffer = null;
				}
			}
		} else {
			Globals.setGlobalResult("No Active Circuit");
		}

		return Result;
	}

	/**
	 * All seq Powers of active 3-phase ciruit element.
	 * Returns kW + j kVAr
	 */
	public static int doSeqPowersCmd() {
		int nValues, i, j, k;
		Complex S;
		Complex[] Vph = new Complex[3];
		Complex[] V012 = new Complex[3];
		Complex[] Iph = new Complex[3];
		Complex[] I012 = new Complex[3];
		Complex[] cBuffer;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();

			if (ckt.getActiveCktElement() != null) {
				CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();
				
				Globals.setGlobalResult("");
				if (cktElem.getNPhases() < 3) {
					for (i = 0; i < 2 * 3 * cktElem.getNTerms() - 1; i++) 
						Globals.setGlobalResult( Globals.getGlobalResult() + "-1.0, ");  // Signify N/A
				} else {
					nValues = cktElem.getNConds() * cktElem.getNTerms();
					cBuffer = new Complex[nValues];
					cktElem.getCurrents(cBuffer);
					for (j = 0; j < cktElem.getNTerms(); j++) {
						k = (j - 1) * cktElem.getNConds();
						for (i = 0; i < 3; i++) 
							Vph[i] = ckt.getSolution().getNodeV()[cktElem.getTerminals()[j].getTermNodeRef()[i]];
						for (i = 0; i < 3; i++) 
							Iph[i] = cBuffer[k + i];
						MathUtil.phase2SymComp(Iph, I012);
						MathUtil.phase2SymComp(Vph, V012);
						for (i = 0; i < 3; i++) 
							S = V012[i].multiply( I012[i].conjugate() );
						Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, %10.5g,", S.getReal() * 0.003, S.getImaginary() * 0.003)); // 3-phase kW conversion
					}
				}
				cBuffer = null;
			}
		} else {
			Globals.setGlobalResult("No Active Circuit");
		}
		
		return Result;
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
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		nValues = -1;  // unassigned, for exception message
		n = -1;        // unassigned, for exception message
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();

			if (ckt.getActiveCktElement() != null) {
				CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();
			
				if (cktElem.isEnabled()) {
					try {
						nValues = cktElem.getNPhases();
						Globals.setGlobalResult("");
						if (nValues < 3) {
							for (i = 0; i < 3 * cktElem.getNTerms(); i++) 
								Globals.setGlobalResult( Globals.getGlobalResult() + "-1.0, ");  // Signify N/A
						} else {
							for (j = 0; j < cktElem.getNTerms(); j++) {

								k = (j - 1) * cktElem.getNConds();
								for (i = 0; i < 3; i++) 
									Vph[i] = ckt.getSolution().getNodeV()[cktElem.getNodeRef()[i + k]];

								MathUtil.phase2SymComp(Vph, V012);  // Compute Symmetrical components

								for (i = 0; i < 3; i++) 
									Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, ", V012[i].abs()));
								
							}
						}
					} catch (Exception e) {
						S = e.getMessage() + DSSGlobals.CRLF +
							"Element=" + cktElem.getName() + DSSGlobals.CRLF +
							"Nvalues=" + String.valueOf(nValues) + DSSGlobals.CRLF +
							"Nterms=" + String.valueOf(cktElem.getNTerms()) + DSSGlobals.CRLF +
							"NConds =" + String.valueOf(cktElem.getNConds()) + DSSGlobals.CRLF +
							"noderef=" + String.valueOf(n) ;
						Globals.doSimpleMsg(S, 270);	
					}
				}
			} else {
				Globals.setGlobalResult("Element Disabled");  // Disabled
			}
		} else {
			Globals.setGlobalResult("No Active Circuit");
		}

		return Result;
	}

	/** Bus Voltages at active terminal. */
	public static int doVoltagesCmd(boolean perUnit) {
		Complex Volts;
		Bus ActiveBus;
		double Vmag;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();
			
			if (ckt.getActiveBusIndex() != 0) { // TODO Check indexing.
				ActiveBus = ckt.getBuses()[ckt.getActiveBusIndex()];
				Globals.setGlobalResult("");
				for (int i = 0; i < ActiveBus.getNumNodesThisBus(); i++) {
					Volts = ckt.getSolution().getNodeV()[ActiveBus.getRef(i)];
					Vmag = Volts.abs();
					if (perUnit && (ActiveBus.getkVBase() > 0.0)) {
						Vmag = Vmag * 0.001 / ActiveBus.getkVBase();
						Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, %6.1f, ", Vmag, Volts.degArg()));
					} else {
						Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%10.5g, %6.1f, ", Vmag, Volts.degArg()));
					}
				}
			} else {
				Globals.setGlobalResult("No Active Bus.");
			}
			
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}

		return Result;
	}

	/** Bus Short Circuit matrix. */
	public static int doZscCmd(boolean Zmatrix) {
		Bus ActiveBus;
		Complex Z;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();
			
			if (ckt.getActiveBusIndex() != 0) {  // FIXME: Bus indexing.
				ActiveBus = ckt.getBuses()[ckt.getActiveBusIndex()];
				Globals.setGlobalResult("");
				if (ActiveBus.getZsc() == null)
					return Result;
				
				for (int i = 0; i < ActiveBus.getNumNodesThisBus(); i++) {
					for (int j = 0; j < ActiveBus.getNumNodesThisBus(); j++) {
						if (Zmatrix) {
							Z = ActiveBus.getZsc().getElement(i, j);
						} else {
							Z = ActiveBus.getYsc().getElement(i, j);
						}
						Globals.setGlobalResult( Globals.getGlobalResult() + String.format("%-.5g, %-.5g,   ", Z.getReal(), Z.getImaginary()) );
					}
				}
			} else {
				Globals.setGlobalResult("No Active Bus.");
			}
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}
		
		return Result;
	}

	/** Bus Short Circuit matrix. */
	public static int doZsc10Cmd() {
		Bus ActiveBus;
		Complex Z;
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		if (Globals.getActiveCircuit() != null) {
			Circuit ckt = Globals.getActiveCircuit();
			
			if (ckt.getActiveBusIndex() != 0) {  // FIXME: Bus indexing.
				ActiveBus = ckt.getBuses()[ckt.getActiveBusIndex()];
				Globals.setGlobalResult("");
				if (ActiveBus.getZsc() == null) {
					
					Z = ActiveBus.getZsc1();
					Globals.setGlobalResult( Globals.getGlobalResult() + String.format("Z1, %-.5g, %-.5g, ", Z.getReal(), Z.getImaginary()) + DSSGlobals.CRLF);
				
					Z = ActiveBus.getZsc0();
					Globals.setGlobalResult( Globals.getGlobalResult() + String.format("Z0, %-.5g, %-.5g, ", Z.getReal(), Z.getImaginary()));
				}
			} else {
				Globals.setGlobalResult("No Active Bus.");
			}
		} else {
			Globals.setGlobalResult("No Active Circuit.");
		}
			
		return Result;
	}

	/**
	 * Requires an EnergyMeter Object at the head of the feeder.
	 * Adjusts loads defined by connected kVA or kWh billing.
	 */
	public static int doAllocateLoadsCmd() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int Result = 0;
		Circuit ckt = Globals.getActiveCircuit();
		
		ckt.setLoadMultiplier(1.0);   // Property .. has side effects
		ckt.getSolution().setMode(Dynamics.SNAPSHOT);
		ckt.getSolution().solve();  // Make guess based on present allocationfactors

		/* Allocation loop -- make MaxAllocationIterations iterations */
		for (int i = 0; i < Globals.getMaxAllocationIterations(); i++) {
			/* Do EnergyMeters */
			for (EnergyMeterObj meter : ckt.getEnergyMeters()) 
				meter.calcAllocationFactors();

			/* Now do other Sensors */
			for (SensorObj sensor : ckt.getSensors()) 
				sensor.calcAllocationFactors();

			/* Now let the EnergyMeters run down the circuit setting the loads */
			for (EnergyMeterObj meter : ckt.getEnergyMeters()) 
				meter.allocateLoad();
			
			ckt.getSolution().solve();  // Update the solution
		}
		
		return Result;
	}

	public static void doSetAllocationFactors(double x) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		if (x < 0.0) {
			Globals.doSimpleMsg("Allocation Factor must be greater than zero.", 271);
		} else {
			for (LoadObj load : Globals.getActiveCircuit().getLoads()) 
				load.setkVAAllocationFactor(x);
		}
	}

	public static void doSetCFactors(double x) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		if (x <= 0.0) {
			Globals.doSimpleMsg("CFactor must be greater than zero.", 271);
		} else {
			for (LoadObj load : Globals.getActiveCircuit().getLoads()) 
				load.setCFactor(x);
		}
	}

	public static int doHarmonicsList(String S) {
		int Result = 0;

		SolutionObj solution = DSSGlobals.getInstance().getActiveCircuit().getSolution();
		
		if (S.equals("ALL")) {
			solution.setDoAllHarmonics(true);
		} else {
			solution.setDoAllHarmonics(false);
		
			double[] Dummy = new double[100]; // Big Buffer
			int Num = Parser.getInstance().parseAsVector(100, Dummy);
			/* Parsing zero-fills the array */

			solution.setHarmonicListSize(Num);
			Utilities.resizeArray(solution.getHarmonicList(), solution.getHarmonicListSize());
			for (int i = 0; i < solution.getHarmonicListSize(); i++) 
				solution.getHarmonicList()[i] = Dummy[i];
			Dummy = null;
		}
	
		return Result;
	}

	public static int doFormEditCmd() {
		if (DSSGlobals.getInstance().isNoFormsAllowed()) 
			return 0;
		
		doSelectCmd();  // Select ActiveObject
		
		if (DSSGlobals.getInstance().getActiveDSSObject() != null) {
			DSSForms.showPropEditForm();
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Element Not Found.", 272);
		}
	
		return 1;
	}

	public static int doMeterTotals() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		if (Globals.getActiveCircuit() != null) {
			Globals.getActiveCircuit().totalizeMeters();
			// Now export to global result
			for (int i = 0; i < EnergyMeterObj.NumEMRegisters; i++) 
				Globals.appendGlobalResult( String.format("%-.6g", Globals.getActiveCircuit().getRegisterTotals()[i]) );
		}
		
		return 0;
	}

	public static int doCapacityCmd() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		int ParamPointer = 0;
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();
		
		while (Param.length() > 0) {
			
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				switch (ParamName.charAt(0)) {
				case 's':
					ParamPointer = 1;
				case 'i':
					ParamPointer = 2;
				default:
					ParamPointer = 0;
				}
			}
			
			switch (ParamPointer) {
			case 0:
				Globals.doSimpleMsg("Unknown parameter \""+ParamName+"\" for Capacity Command", 273);
			case 1:
				Globals.getActiveCircuit().setCapacityStart(Parser.getInstance().makeDouble());
			case 2:
				Globals.getActiveCircuit().setCapacityIncrement(Parser.getInstance().makeDouble());
			}

			ParamName = Parser.getInstance().getNextParam();
			Param = Parser.getInstance().makeString();
		}
		
		Circuit ckt = Globals.getActiveCircuit();
		if (ckt.computeCapacity()) {  // Totalizes EnergyMeters at End

			Globals.setGlobalResult(String.format("%-.6g", (ckt.getRegisterTotals()[3] + ckt.getRegisterTotals()[19]) ) );  // Peak KW in Meters
			Globals.appendGlobalResult( String.format("%-.6g", ckt.getLoadMultiplier()) );
		}

		return 0;
	}

	public static int doClassesCmd() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		for (int i = 0; i < DSSClassDefs.getNumIntrinsicClasses(); i++) 
			Globals.appendGlobalResult( DSSClassImpl(Globals.getDSSClassList().get(i)).getName() );
		
		return 0;
	}

	public static int doUserClassesCmd() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		if (DSSClassDefs.getNumUserClasses() == 0) {
			Globals.appendGlobalResult("No User Classes Defined.");
		} else {
			for (int i = DSSClassDefs.getNumIntrinsicClasses() + 1; i < Globals.getDSSClassList().size(); i++) 
				Globals.appendGlobalResult( DSSClassImpl(Globals.getDSSClassList().get(i)).getName() );
		}     
	return 0;
	}

	public static int doZscRefresh() {
		int Result = 1;

		try {
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
			SolutionObj solution = ckt.getSolution();
			for (int i = 0; i < ckt.getNumNodes(); i++) 
				solution.getCurrents()[i] = Complex.ZERO;  // Clear Currents array
			
			if ((ckt.getActiveBusIndex() > 0) && (ckt.getActiveBusIndex() <= ckt.getNumBuses())) {  // TODO Check zero indexing
				if (ckt.getBuses()[ckt.getActiveBusIndex()].getZsc() == null)
					ckt.getBuses()[ckt.getActiveBusIndex()].allocateBusQuantities();
				SolutionAlgs.computeYsc(ckt.getActiveBusIndex());      // Compute YSC for active Bus
				Result = 0;
			}
			
		} catch (Exception e) {
			DSSGlobals.getInstance().doSimpleMsg("ZscRefresh Error: " + e.getMessage() + DSSGlobals.CRLF , 274);
		}
			
		return Result;
	}

	public static int doVarValuesCmd() {
		if (DSSGlobals.getInstance().getActiveCircuit() != null) {
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
			/* Check if PCElement */
			switch (ckt.getActiveCktElement().getDSSObjType()) {
			case DSSClassDefs.PC_ELEMENT:
				PCElement cktElem = (PCElement) ckt.getActiveCktElement();
				for (int i = 0; i < cktElem.numVariables(); i++) 
					DSSGlobals.getInstance().appendGlobalResult(String.format("%-.6g", cktElem.getVariable(i)));
			default:
				DSSGlobals.getInstance().appendGlobalResult("Null");
			}
		}
		return 0;
	}

	public static int doVarNamesCmd() {
		if (DSSGlobals.getInstance().getActiveCircuit() != null) {
			Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
			/* Check if PCElement */
			switch (ckt.getActiveCktElement().getDSSObjType()) {
			case DSSClassDefs.PC_ELEMENT:
				PCElement cktElem = (PCElement) ckt.getActiveCktElement();
				for (int i = 0; i < cktElem.numVariables(); i++) 
					DSSGlobals.getInstance().appendGlobalResult(cktElem.variableName(i));
			default:
				DSSGlobals.getInstance().appendGlobalResult("Null");
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
	 */
	public static int doBusCoordsCmd() {
		String BusName, S;
		int iB, Result = 0;
		File F;

		/* Get next parameter on command line */
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();

		try {

			try {
				F = new File(Param);
				FileInputStream fstream = new FileInputStream(F);
				DataInputStream in = new DataInputStream(in);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));

				while ((S = br.readLine()) != null) {
					Parser parser = DSSGlobals.getInstance().getAuxParser();  // User Auxparser to parse line

					parser.setCmdString(S);
					parser.getNextParam();
					BusName = parser.makeString();
					iB = DSSGlobals.getInstance().getActiveCircuit().getBusList().find(BusName);
					if (iB > 0) {
						Bus bus = DSSGlobals.getInstance().getActiveCircuit().getBuses()[iB];
						parser.getNextParam();
						bus.setX(parser.makeDouble());
						parser.getNextParam();
						bus.setY(parser.makeDouble());
						bus.setCoordDefined(true);
					}
				}  // Just ignore a bus that's not in the circuit
			} catch (Exception e) {
				DSSGlobals.getInstance().doSimpleMsg("Bus Coordinate file: \"" + Param + "\" not found.", 275);
			}
		} finally {
			F.close();
		}
	
		return Result;
	}

	public static int doMakePosSeq() {
		
		DSSGlobals.getInstance().getActiveCircuit().setPositiveSequence(true);

		for (CktElement cktElem : DSSGlobals.getInstance().getActiveCircuit().getCktElements()) 
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
	
	public static void doSetReduceStrategy(String S) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		Globals.getActiveCircuit().setReductionStrategyString(S);
		Globals.getAuxParser().setCmdString(S);
		String ParamName = Globals.getAuxParser().getNextParam();
		String Param = Globals.getAuxParser().makeString().toUpperCase();
		ParamName = Globals.getAuxParser().getNextParam();
		String Param2 = Globals.getAuxParser().makeString();

		Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsDefault);
		if (Param.length() == 0)
			return;  // No option given

		switch (Param.charAt(0)) {
		case 'B':
			Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsBreakLoop);
		case 'D':
			Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsDefault);
		case 'E':
			Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsDangling);
		case 'M':
			Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsMergeParallel);
		case 'T':
			Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsTapEnds);
			Globals.getActiveCircuit().setReductionMaxAngle(15.0);  // default
			if (Param2.length() > 0)
				Globals.getActiveCircuit().setReductionMaxAngle(Globals.getAuxParser().makeDouble());
		case 'S':  // Stubs
			if (Utilities.compareTextShortest(Param, "SWITCH") == 0) {
				Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsSwitches);
			} else {
				Globals.getActiveCircuit().setReductionZmag(0.02);
				Globals.getActiveCircuit().setReductionStrategy(ReductionStrategyType.rsStubs);
				if (Param2.length() > 0) 
					Globals.getActiveCircuit().setReductionZmag(Globals.getAuxParser().makeDouble());
			}
		default:
			Globals.doSimpleMsg("Unknown Reduction Strategy: \"" + S + "\".", 276);
		}
	}

	/**
	 * Interpolate bus coordinates in meter zones.
	 */
	public static int doInterpolateCmd() {
		EnergyMeter MeterClass;
		String ParamName, Param;
		int DevClassIndex;
		CktElement CktElem;
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		int Result = 0;

		ParamName = Parser.getInstance().getNextParam();
		Param = Parser.getInstance().makeString().toUpperCase();

		// initialize the Checked Flag FOR all circuit Elements
		Circuit ckt = Globals.getActiveCircuit();

		for (CktElement cktElem : ckt.getCktElements()) 
			cktElem.setChecked(false);

		if (Param.length() == 0) Param = "A";
		switch (Param.charAt(0)) {
		case 'A':
			for (EnergyMeterObj MetObj : ckt.getEnergyMeters()) 
				MetObj.interpolateCoordinates();
		default:
			/* Interpolate a specific meter */
			DevClassIndex = Globals.getClassNames().find("energymeter");
			if (DevClassIndex > 0) {
				MeterClass = (EnergyMeter) Globals.getDSSClassList().get(DevClassIndex);
				if (MeterClass.setActive(Param)) {  // Try to set it active
					EnergyMeterObj MetObj = (EnergyMeterObj) MeterClass.getActiveObj();
					MetObj.interpolateCoordinates();
				} else {	
					Globals.doSimpleMsg("EnergyMeter \""+Param+"\" not found.", 277);
				}
			}
		}
		
		return Result;
	}

	/**
	 * Rewrites designated file, aligning the fields into columns.
	 */
	public static int doAlignFileCmd() {
		int Result = 0;
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();

		if (new File(Param).exists()) {
			if (!Utilities.rewriteAlignedFile(Param)) 
				Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("File \""+Param+"\" does not exist.", 278);
			Result = 1;
		}

		if (Result == 0)
			Utilities.fireOffEditor(DSSGlobals.getInstance().getGlobalResult());
	
		return Result;
	}

	/**
	 * Sends Monitors, Loadshapes, GrowthShapes, or TCC Curves to TOP
	 * as an STO file.
	 */
	public static int doTOPCmd() {
		int Result = 0;
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString().toUpperCase();

		ParamName = Parser.getInstance().getNextParam();
		String ObjName = Parser.getInstance().makeString().toUpperCase();

		if (ObjName.length() == 0) ObjName = "ALL";

		switch (Param.charAt(0)) {
		case 'L':
			DSSGlobals.getInstance().getLoadShapeClass().tOPExport(ObjName);
//		case 'G':
//			DSSGlobals.getInstance().getGrowthShapeClass().tOPExportAll();
//		case 'T':
//			DSSGlobals.getInstance().getTCC_CurveClass().tOPExportAll();
		default:
			DSSGlobals.getInstance().getMonitorClass().tOPExport(ObjName);
		}
		return Result;
	}

	public static void doSetNormal(double pctNormal) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt != null) {
			pctNormal = pctNormal * 0.01;  // FIXME local copy only
			for (LineObj line : ckt.getLines()) 
				line.setNormAmps(pctNormal * line.getEmergAmps());
		}
	}

	/**
	 * Rotate about the center of the coordinates.
	 */
	public static int doRotateCmd() {
		double Angle, xmin, xmax, ymin, ymax, xc, yc;
		String ParamName;
		Bus bus;
		Complex a, vector;
		
		int Result = 0;
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt != null) {

			ParamName = Parser.getInstance().getNextParam();
			Angle = Parser.getInstance().makeDouble() * DSSGlobals.PI / 180.0;   // Deg to rad

			a = new Complex(Math.cos(Angle), Math.sin(Angle));
			xmin =  1.0e50;
			xmax = -1.0e50;
			ymin =  1.0e50;
			ymax = -1.0e50;
			for (int i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBuses()[i].isCoordDefined()) {
					bus = ckt.getBuses()[i];
					xmax = Math.max(xmax, bus.getX());
					xmin = Math.min(xmin, bus.getX());
					ymax = Math.max(ymax, bus.getY());
					ymin = Math.min(ymin, bus.getY());
				}
			}

			xc = (xmax + xmin) / 2.0;
			yc = (ymax + ymin) / 2.0;

			for (int i = 0; i < ckt.getNumBuses(); i++) {
				if (ckt.getBuses()[i].isCoordDefined()) {
					bus = ckt.getBuses()[i];
					vector = new Complex(bus.getX() - xc, bus.getY() - yc);
					vector = vector.multiply(a);
					bus.setX(xc + vector.getReal());
					bus.setY(yc + vector.getImaginary());
				}
			}
		}
		return Result;
	}

	public static int doVDiffCmd() {
		// TODO Implement this method.
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns summary in global result string.
	 */
	public static int doSummaryCmd() {
		String S;
		Complex cLosses, cPower;
		String CRLF = DSSGlobals.CRLF;
		
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		
		int Result = 0;
		S = "";
		if (ckt.isSolved()) {
			S = S + "Status = SOLVED" + DSSGlobals.CRLF;
		} else {
			S = S + "Status = NOT Solved" + CRLF;
		}
		S = S + "Solution Mode = " + Utilities.getSolutionModeID() + CRLF;
		S = S + "Number = " + String.valueOf(ckt.getSolution().getNumberOfTimes()) + CRLF;
		S = S + "Load Mult = " + String.format("%5.3f", ckt.getLoadMultiplier()) + CRLF;
		S = S + "Devices = " + String.format("%d", ckt.getNumDevices()) + CRLF;
		S = S + "Buses = " + String.format("%d", ckt.getNumBuses()) + CRLF;
		S = S + "Nodes = " + String.format("%d", ckt.getNumNodes()) + CRLF;
		S = S + "Control Mode =" + Utilities.getControlModeID() + CRLF;
		S = S + "Total Iterations = " + String.valueOf(ckt.getSolution().getIteration()) + CRLF;
		S = S + "Control Iterations = " + String.valueOf(ckt.getSolution().getControlIteration()) + CRLF;
		S = S + "Max Sol Iter = " + String.valueOf(ckt.getSolution().getMostIterationsDone()) + CRLF;
		S = S + " " + CRLF;
		S = S + " - Circuit Summary -" + CRLF;
		S = S + " " + CRLF;
		if (ckt != null) {
			S = S + String.format("Year = %d ", ckt.getSolution().getYear()) + CRLF;
			S = S + String.format("Hour = %d ", ckt.getSolution().getIntHour()) + CRLF;
			S = S + "Max pu. voltage = " + String.format("%-.5g ", Utilities.getMaxPUVoltage()) + CRLF;
			S = S + "Min pu. voltage = " + String.format("%-.5g ", Utilities.getMinPUVoltage(true)) + CRLF;
			cPower = Utilities.getTotalPowerFromSources().multiply(0.000001);  // MVA
			S = S + String.format("Total Active Power:   %-.6g MW", cPower.getReal()) + CRLF;
			S = S + String.format("Total Reactive Power: %-.6g Mvar", cPower.getImaginary()) + CRLF;
			cLosses = ckt.getLosses().multiply(0.000001);
			if (cPower.getReal() != 0.0) {
				S = S + String.format("Total Active Losses:   %-.6g MW, (%-.4g %%)", cLosses.getReal(), (cLosses.getReal() / cPower.getReal() * 100.0)) + CRLF;
			} else {
				S = S + "Total Active Losses:   ****** MW, (**** %%)" + CRLF;
			}
			S = S + String.format("Total Reactive Losses: %-.6g Mvar", cLosses.getImaginary()) + CRLF;
			S = S + String.format("Frequency = %-g Hz", ckt.getSolution().getFrequency()) + CRLF;
			S = S + "Mode = " + Utilities.getSolutionModeID() + CRLF;
			S = S + "Control Mode = " + Utilities.getControlModeID() + CRLF;
			S = S + "Load Model = " + Utilities.getLoadModel() + CRLF;
		}

		DSSGlobals.getInstance().setGlobalResult(S);
		
		return Result;
	}

	public static int doDistributeCmd() {
		Parser parser = Parser.getInstance();
		int Result = 0;
		int ParamPointer = 0;
		/* Defaults */
		double kW = 1000.0;
		String How = "Proportional";
		int Skip = 1;
		double PF = 1.0;
		String FilName = "DistGenerators.dss";

		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = DistributeCommands.getCommand(ParamName);
			}

			switch (ParamPointer) {
			case 1:
				kW = parser.makeDouble();
			case 2:
				How = parser.makeString();
			case 3:
				Skip = parser.makeInteger();
			case 4:
				PF = parser.makeDouble();
			case 5:
				FilName = parser.makeString();
			case 6:
				kW = parser.makeDouble() * 1000.0;
			default:
				// ignore unnamed and extra parms
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		Utilities.makeDistributedGenerators(kW, PF, How, Skip, FilName); 
	
		return Result;
	}

	public static int doDI_PlotCmd() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		
		if (Globals.isDIFilesAreOpen())
			Globals.getEnergyMeterClass().closeAllDIFiles();

		if (DSSPlot.DSSPlotObj == null)
			DSSPlot.DSSPlotObj = new DSSPlotImpl();
		
		/* Defaults */
		int NumRegs = 1;
		double[] dRegisters = new double[EnergyMeterObj.NumEMRegisters];
		int[] iRegisters = new int[NumRegs];
		iRegisters[0] = 9;
		boolean PeakDay = false;
		int CaseYear = 1;
		String CaseName = "";
		String MeterName = "DI_Totals";

		Parser parser = Parser.getInstance();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = DI_PlotCommands.getCommand(ParamName);
			}
			
			switch (ParamPointer) {
			case 1:
				CaseName = Param;
			case 2:
				CaseYear = parser.makeInteger();
			case 3:
				NumRegs = parser.parseAsVector(EnergyMeterObj.NumEMRegisters, dRegisters);
				iRegisters = new int[NumRegs];
				for (int i = 0; i < NumRegs; i++) 
					iRegisters[i - 1] = (int) dRegisters[i];
			case 4:
				PeakDay = Utilities.interpretYesNo(Param);
			case 5:
				MeterName = parser.makeString();
			default:
				// ignore unnamed and extra parms
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		DSSPlot.DSSPlotObj.doDI_Plot(CaseName, CaseYear, iRegisters, PeakDay, MeterName);

		iRegisters = null;
		
		return 0;
	}

	public static int doGuidsCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSetLoadAndGenKVCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCompareCasesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doYearlyCurvesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doVisualizeCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCloseDICmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doADOScmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doEstimateCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doReconductorCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doAddMarkerCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCvrtLoadshapesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static int doNodeDiffCmd() {
		return 0;
	}
	
	public static int doRephaseCmd() {
		return 0;
	}
	
	public static int doSetBusXYCmd() {
		return 0;
	}

}
