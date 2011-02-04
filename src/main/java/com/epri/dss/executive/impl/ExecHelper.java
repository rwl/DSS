package com.epri.dss.executive.impl;

import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.parser.impl.Parser;

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

	public static int doSelectCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doMoreCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSaveCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSampleCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSolveCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doEnableCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doDisableCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doOpenCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doResetCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doNextCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doFormEditCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doClassesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doUserClassesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doHelpCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doClearCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doReduceCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doInterpolateCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCloseCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doResetMonitors() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doFileEditCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doQueryCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doResetMeters() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void doAboutBox() {
		// TODO Auto-generated method stub

	}

	public static int doSetVoltageBases() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSetkVBase() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void doLegalVoltageBases() {
		// TODO Auto-generated method stub

	}

	public static void doAutoAddBusList(String S) {
		// TODO Auto-generated method stub

	}

	public static void doKeeperBusList(String S) {
		// TODO Auto-generated method stub

	}

	public static void doSetReduceStrategy(String S) {
		// TODO Auto-generated method stub

	}

	public static void doSetAllocationFactors(double X) {
		// TODO Auto-generated method stub

	}

	public static void doSetCFactors(double X) {
		// TODO Auto-generated method stub

	}

	public static int doVoltagesCmd(boolean PerUnit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCurrentsCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doPowersCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSeqVoltagesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSeqCurrentsCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSeqPowersCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doLossesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doPhaseLossesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCktLossesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doAllocateLoadsCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doHarmonicsList(String S) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doMeterTotals() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doCapacityCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doZscCmd(boolean Zmatrix) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doZsc10Cmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doZscRefresh() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doBusCoordsCmd() {
		// TODO Auto-generated method stub
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

	public static int doVarValuesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doVarNamesCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doMakePosSeq() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doAlignFileCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doTOPCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doRotateCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doVDiffCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doSummaryCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doDistributeCmd() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doDI_PlotCmd() {
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

	public static void doSetNormal(double pctNormal) {
		// TODO Auto-generated method stub

	}

	public static void setTime() {
		// TODO Auto-generated method stub

	}

	public static void parseObjName(String fullname, String objname, String propname) {
		// TODO Auto-generated method stub

	}

	public static int addObject(String ObjType, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int editObject(String ObjType, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void setActiveCircuit(String cktname) {
		// TODO Auto-generated method stub

	}

	public static int setActiveCktElement() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int doPropertyDump() {
		// TODO Auto-generated method stub
		return 0;
	}

}
