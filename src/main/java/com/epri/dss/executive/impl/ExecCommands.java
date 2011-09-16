package com.epri.dss.executive.impl;

import java.io.File;

import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.impl.CommandListImpl;

public class ExecCommands {

	public static final int NumExecCommands = 95;

	private String[] execCommand = new String[NumExecCommands];
	private String[] commandHelp = new String[NumExecCommands];

	private CommandList commandList;

	/* Always has last command processed */
	private String lastCmdLine;
	private String redirFile;

	/**
	 * Private constructor prevents instantiation from other classes.
	 */
	private ExecCommands() {
		defineCommands();

		commandList = new CommandListImpl(execCommand);
		commandList.setAbbrevAllowed(true);
	}

	/**
	 * ExecCommandsHolder is loaded on the first execution of
	 * ExecCommands.getInstance() or the first access to
	 * ExecCommandsHolder.INSTANCE, not before.
	 */
	private static class ExecCommandsHolder {
		public static final ExecCommands INSTANCE = new ExecCommands();
	}

	public static ExecCommands getInstance() {
		return ExecCommandsHolder.INSTANCE;
	}

	private void defineCommands() {
		final String CRLF = DSSGlobals.CRLF;

		execCommand = new String[NumExecCommands];

		execCommand[0]  = "New";
		execCommand[1]  = "Edit";
		execCommand[2]  = "More";
		execCommand[3]  = "M";
		execCommand[4]  = "~";
		execCommand[5]  = "Select";
		execCommand[6]  = "Save";
		execCommand[7]  = "Show";
		execCommand[8]  = "Solve";
		execCommand[9]  = "Enable";
		execCommand[10] = "Disable";
		execCommand[11] = "Plot";
		execCommand[12] = "Reset";
		execCommand[13] = "Compile";
		execCommand[14] = "Set";    // set DSS options
		execCommand[15] = "Dump";   // debug dump
		execCommand[16] = "Open";   // open a device terminal conductor
		execCommand[17] = "Close";  // close a device terminal conductor
		execCommand[18] = "//";     // comment
		execCommand[19] = "Redirect";
		execCommand[20] = "Help";
		execCommand[21] = "Quit";
		execCommand[22] = "?";      // property value inquiry
		execCommand[23] = "Next";
		execCommand[24] = "Panel";
		execCommand[25] = "Sample";
		execCommand[26] = "Clear";
		execCommand[27] = "About";
		execCommand[28] = "Calcvoltagebases";  // computes voltage bases
		execCommand[29] = "SetkVBase";  // set kV base at a bus
		execCommand[30] = "BuildY";  // forces rebuild of Y matrix right now
		execCommand[31] = "Get";  // returns values set with set command
		execCommand[32] = "Init";
		execCommand[33] = "Export";
		execCommand[34] = "Fileedit";
		execCommand[35] = "Voltages";
		execCommand[36] = "Currents";
		execCommand[37] = "Powers";
		execCommand[38] = "Seqvoltages";
		execCommand[39] = "Seqcurrents";
		execCommand[40] = "Seqpowers";
		execCommand[41] = "Losses";
		execCommand[42] = "Phaselosses";
		execCommand[43] = "Cktlosses";
		execCommand[44] = "Allocateloads";
		execCommand[45] = "Formedit";
		execCommand[46] = "Totals";  // total all energyMeters
		execCommand[47] = "Capacity";  // find upper kW limit of system for present year
		execCommand[48] = "Classes";  // list of intrinsic classes
		execCommand[49] = "Userclasses";  // list of user-defined classes
		execCommand[50] = "Zsc";
		execCommand[51] = "Zsc10";
		execCommand[52] = "ZscRefresh";
		execCommand[53] = "Ysc";
		execCommand[54] = "puvoltages";
		execCommand[55] = "VarValues";
		execCommand[56] = "Varnames";
		execCommand[57] = "Buscoords";
		execCommand[58] = "MakeBusList";
		execCommand[59] = "MakePosSeq";
		execCommand[60] = "Reduce";
		execCommand[61] = "Interpolate";
		execCommand[62] = "AlignFile";
		execCommand[63] = "TOP";
		execCommand[64] = "Rotate";
		execCommand[65] = "Vdiff";
		execCommand[66] = "Summary";
		execCommand[67] = "Distribute";
		execCommand[68] = "DI_plot";
		execCommand[69] = "Comparecases";
		execCommand[70] = "YearlyCurves";
		execCommand[71] = "CD";
		execCommand[72] = "Visualize";
		execCommand[73] = "CloseDI";
		execCommand[74] = "DOScmd";
		execCommand[75] = "Estimate";
		execCommand[76] = "Reconductor";
		execCommand[77] = "_InitSnap";
		execCommand[78] = "_SolveNoControl";
		execCommand[79] = "_SampleControls";
		execCommand[80] = "_DoControlActions";
		execCommand[81] = "_ShowControlQueue";
		execCommand[82] = "_SolveDirect";
		execCommand[83] = "_SolvePFlow";
		execCommand[84] = "AddMarker";

		execCommand[85] = "Guids";
		execCommand[86] = "SetLoadAndGenKV";
		execCommand[87] = "CvrtLoadshapes";
		execCommand[88] = "NodeDiff";
		execCommand[89] = "Rephase";
		execCommand[90] = "SetBusXY";
		execCommand[91] = "UpdateStorage";
		execCommand[92] = "Obfuscate";
		execCommand[93] = "LatLongCoords";
		execCommand[94] = "BatchEdit";


		commandHelp = new String[NumExecCommands];

		commandHelp[0]  = "Create a new object within the DSS. Object becomes the "+
				"active object" + CRLF +
				"Example: New Line.line1 ...";
		commandHelp[1]  = "Edit an object. The object is selected and it then becomes the active object."+CRLF+CRLF+
				"Note that Edit is the default command.  You many change a property value simply by " +
				"giving the full property name and the new value, for example:"+CRLF+CRLF+
				"line.line1.r1=.04"+CRLF+
				"vsource.source.kvll=230";
		commandHelp[2]  = "Continuation of editing on the active object.";
		commandHelp[3]  = "Continuation of editing on the active object. An abbreviation for More";
		commandHelp[4]  = "Continuation of editing on the active object. An abbreviation."+CRLF+CRLF+
				"Example:"+CRLF+
				"New Line.Line1 Bus1=aaa  bus2=bbb"+CRLF+
				"~ R1=.058" +CRLF+
				"~ X1=.1121";
		commandHelp[5]  = "Selects an element and makes it the active element.  You can also specify the " +
				"active terminal (default = 1)."+CRLF+  CRLF+
				"Syntax:"+CRLF+
				"Select [element=]elementname  [terminal=]terminalnumber "+CRLF+CRLF+
				"Example:"+CRLF+
				"Select Line.Line1 "+CRLF+
				"~ R1=.1"+CRLF+"(continue editing)"+CRLF+CRLF+
				"Select Line.Line1 2 " +CRLF+
				"Voltages  (returns voltages at terminal 2 in Result)";
		commandHelp[6]  = "{Save [class=]{Meters | Circuit | Voltages | (classname)} [file=]filename [dir=]directory " + CRLF +
				" Default class = Meters, which saves the present values in both monitors and energy meters in the active circuit. " +
				"\"Save Circuit\" saves the present enabled circuit elements to the specified subdirectory in standard DSS form " +
				"with a Master.txt file and separate files for each class of data. If Dir= not specified a unique name based on the circuit name is created "+
				"automatically.  If Dir= is specified, any existing files are overwritten. " + CRLF +
				"\"Save Voltages\" saves the present solution in a simple CSV format in a file called DSS_SavedVoltages. "+
				"Used for VDIFF command."+CRLF+
				"Any class can be saved to a file.  If no filename specified, the classname is used.";
		commandHelp[7]  = "Writes selected results to a text file and brings "+
				"up the default text editor (see Set Editor=....) with the file for you to browse."+CRLF+  CRLF+
				"See separate help on Show command. "  +CRLF+  CRLF+
				"Default is \"show voltages LN Seq\".  ";
		commandHelp[8]  = "Perform the solution of the present solution mode. You can set any option "+
				"that you can set with the Set command (see Set). "+
				"The Solve command is virtually synonymous with the Set command except that " +
				"a solution is performed after the options are processed.";
		commandHelp[9]  = "Enables a circuit element or entire class.  Example:" +CRLF+
				"Enable load.loadxxx"+CRLF+
				"Enable generator.*  (enables all generators)";
		commandHelp[10] = "Disables a circuit element or entire class.  Example:" +CRLF+
				"Disable load.loadxxx"+CRLF+
				"Disable generator.*  (Disables all generators)"+CRLF+ CRLF+
				"The item remains defined, but is not included in the solution.";
		commandHelp[11] = "Plots circuits and results in a variety of manners.  See separate Plot command help.";
		commandHelp[12] = "{MOnitors | MEters | Faults | Controls | Eventlog | Keeplist |(no argument) } Resets all Monitors, Energymeters, etc. " +
				"If no argument specified, resets all options listed.";
		commandHelp[13] = "Reads the designated file name containing DSS commands " +
				"and processes them as if they were entered directly into the command line. "+
				"The file is said to be \"compiled.\" "  +
				"Similar to \"redirect\" except changes the default directory to the path of the specified file."+CRLF+CRLF+
				"Syntax:"+CRLF+
				"Compile filename";
		commandHelp[14] = "Used to set various DSS solution modes and options.  You may also set the options with the Solve command. "+
				"See \"Options\" for help.";
		commandHelp[15] = "Display the properties of either a specific DSS object or a complete dump "+
				"on all variables in the problem (Warning! Could be very large!)."+
				" Brings up the default text editor with the text file written by this command."+ CRLF+
				" Syntax: dump [class.obj] [debug]" + CRLF +
				" Examples:"+CRLF+CRLF+
				" Dump line.line1 "+CRLF+
				" Dump solution  (dumps all solution vars) "+CRLF+
				" Dump commands  (dumps all commands to a text file) "+CRLF+
				" Dump transformer.*  (dumps all transformers)"+CRLF+
				" Dump ALLOCationfactors  (load allocation factors)"+CRLF+
				" Dump Buslist    (bus name hash list)" + CRLF +
				" Dump Devicelist    (Device name hash list)" + CRLF +
				" Dump      (dumps all objects in circuit) ";
				//" Dump debug";   // Debug dump
		commandHelp[16] = "Opens the specified terminal and conductor of the specified circuit element. " +
				"If the conductor is not specified, all phase conductors of the terminal are opened." +CRLF+CRLF+
				"Examples:"+CRLF+
				"Open line.line1 2 "+CRLF+
				"(opens all phases of terminal 2)"+CRLF+CRLF+
				"Open line.line1 2 3" +CRLF+
				"(opens the 3rd conductor of terminal 2)";
		commandHelp[17] = "Opposite of the Open command.";   // Close a device terminal conductor
		commandHelp[18] = "Comment.  Command line is ignored.";       // Comment
		commandHelp[19] = "Reads the designated file name containing DSS commands " +
				"and processes them as if they were entered directly into the command line. "+
				"Similar to \"Compile\", but leaves current directory where it was when Redirect command is invoked." +
				"Can temporarily change to subdirectories if nested Redirect commands require."+CRLF+CRLF+
				"ex:  redirect filename";
		commandHelp[20] = "Gives this display.";
		commandHelp[21] = "Shuts down DSS unless this is the DLL version.  Then it does nothing;  DLL parent is responsible for shutting down the DLL.";
		commandHelp[22] = "Inquiry for property value.  Result is put into GlobalReault and can be seen in the Result Window. "+
				"Specify the full property name."+CRLF+CRLF+
				"Example: ? Line.Line1.R1" + CRLF+CRLF+
				"Note you can set this property merely by saying:"+CRLF+
				"Line.line1.r1=.058";   // Property Value inquiry
		commandHelp[23] = "{Year | Hour | t}  Increments year, hour, or time as specified.  If \"t\" is " +
				"specified, then increments time by current step size.";
		commandHelp[24] = "Displays main control panel window.";
		commandHelp[25] = "Force all monitors and meters to take a sample now.";
		commandHelp[26] = "Clear all circuits currently in memory.";
		commandHelp[27] = "Display \"About Box\".  (Result string set to Version string.)";
		commandHelp[28] = "Calculates voltagebase for buses based on voltage bases defined "+
				"with Set voltagebases=... command.";
		commandHelp[29] = "Command to explicitly set the base voltage for a bus. " +
				"Bus must be previously defined. Parameters in order are:"+CRLF+
				"Bus = {bus name}"   +CRLF+
				"kVLL = (line-to-line base kV)"      +CRLF+
				"kVLN = (line-to-neutral base kV)"   + CRLF+CRLF+
				"kV base is normally given in line-to-line kV (phase-phase). " +
				"However, it may also be specified by line-to-neutral kV."+CRLF+
				"The following exampes are equivalent:"+CRLF+CRLF+
				"setkvbase Bus=B9654 kVLL=13.2"   +CRLF+
				"setkvbase B9654 13.2"   +CRLF+
				"setkvbase B9654 kvln=7.62";
		commandHelp[30] = "Forces rebuild of Y matrix upon next Solve command regardless of need. " +
				"The usual reason for doing this would be to reset the matrix for another " +
				"load level when using LoadModel=PowerFlow (the default) when the system is difficult to " +
				"solve when the load is far from its base value.  Works by invalidating the Y primitive " +
				"matrices for all the Power Conversion elements.";
		commandHelp[31] = "Returns DSS property values set using the Set command. "  +
				"Result is returne in Result property of the Text interface. " +CRLF+CRLF+
				"VBA Example:" +CRLF+CRLF+
				"DSSText.Command = \"Get mode\"" +CRLF   +
				"Answer = DSSText.Result" +CRLF +CRLF +
				"Multiple properties may be requested on one get.  The results are appended "+
				"and the individual values separated by commas." +CRLF+CRLF+
				"See help on Set command for property names.";
		commandHelp[32] = "This command forces reinitialization of the solution for the next Solve command. " +
				"To minimize iterations, most solutions start with the previous solution unless there " +
				"has been a circuit change.  However, if the previous solution is bad, it may be necessary " +
				"to re-initialize.  In most cases, a re-initiallization results in a zero-load power flow " +
				"solution with only the series power delivery elements considered.";
		commandHelp[33] = "Export various solution values to CSV (or XML) files for import into other programs. " +
				"Creates a new file except for Energymeter and Generator objects, for which " +
				"the results for each device of this class are APPENDED to the CSV File. You may export to "+
				"a specific file by specifying the file name as the LAST parameter on the line. For example:"+ CRLF+CRLF+
				"  Export Voltage Myvoltagefile.CSV" +CRLF+CRLF+
				"Otherwise, the default file names shown in the Export help are used. " +
				"For Energymeter and Generator, specifying the switch \"/multiple\" (or /m) for the file name will cause " +
				"a separate file to be written for each meter or generator. " +
				"The default is for a single file containing all elements." +  CRLF + CRLF+
				"May be abreviated Export V, Export C, etc.  Default is \"V\" for voltages."+
				" If Set ShowExport=Yes, the output file will be automatically displayed in the default editor. "+
				"Otherwise, you must open the file separately. The name appears in the Result window.";
		commandHelp[34] = "Edit specified file in default text file editor (see Set Editor= option)."+CRLF+CRLF+
				"Fileedit EXP_METERS.CSV (brings up the meters export file)" + CRLF+CRLF+
				"\"FileEdit\" may be abbreviated to a unique character string.";
		commandHelp[35] = "Returns the voltages for the ACTIVE BUS in the Result string. " +
				"For setting the active Bus, use the Select command or the Set Bus= option. " +
				"Returned as magnitude and angle quantities, comma separated, one set per conductor of the terminal.";
		commandHelp[36] = "Returns the currents for each conductor of ALL terminals of the active circuit element in the Result string. "+
				"(See Select command.)" +
				"Returned as comma-separated magnitude and angle.";
		commandHelp[37] = "Returns the powers (complex) going into each conductors of ALL terminals of the active circuit element in the Result string. "+
				"(See Select command.)" +
				"Returned as comma-separated kW and kvar.";
		commandHelp[38] = "Returns the sequence voltages at all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values." +
				"Order of returned values: 0, 1, 2  (for each terminal).";
		commandHelp[39] = "Returns the sequence currents into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values." +
				"Order of returned values: 0, 1, 2  (for each terminal).";
		commandHelp[40] = "Returns the sequence powers into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated kw, kvar pairs." +
				"Order of returned values: 0, 1, 2  (for each terminal).";
		commandHelp[41] = "Returns the total losses for the active circuit element (see Select command) " +
				"in the Result string in kW, kvar.";
		commandHelp[42] = "Returns the losses for the active circuit element (see Select command) " +
				"for each PHASE in the Result string in comma-separated kW, kvar pairs.";
		commandHelp[43] = "Returns the total losses for the active circuit in the Result string in kW, kvar.";
		commandHelp[44] = "Estimates the allocation factors for loads that are defined using the XFKVA property. " +
				"Requires that energymeter objects be defined with the PEAKCURRENT property set. " +
				"Loads that are not in the zone of an energymeter cannot be allocated.";
		commandHelp[45] = "FormEdit [class.object].  Brings up form editor on active DSS object.";
		commandHelp[46] = "Totals all EnergyMeter objects in the circuit and reports register totals in the result string.";
		commandHelp[47] = "Find the maximum load the active circuit can serve in the PRESENT YEAR. Uses the EnergyMeter objects with the registers "+
				"set with the SET UEREGS= (..) command for the AutoAdd functions.  Syntax (defaults shown):"+CRLF+CRLF+
				"capacity [start=]0.9 [increment=]0.005" + CRLF + CRLF +
				"Returns the metered kW (load + losses - generation) and per unit load multiplier for the loading level at which something in the system reports an overload or undervoltage. "+
				"If no violations, then it returns the metered kW for peak load for the year (1.0 multiplier). "+
				"Aborts and returns 0 if no energymeters.";
		commandHelp[48] = "List of intrinsic DSS Classes. Returns comma-separated list in Result variable.";
		commandHelp[49] = "List of user-defined DSS Classes. Returns comma-separated list in Result variable.";
		commandHelp[50] = "Returns full Zsc matrix for the ACTIVE BUS in comma-separated complex number form.";
		commandHelp[51] = "Returns symmetrical component impedances, Z1, Z0 for the ACTIVE BUS in comma-separated R+jX form.";
		commandHelp[52] = "Refreshes Zsc matrix for the ACTIVE BUS.";
		commandHelp[53] = "Returns full Ysc matrix for the ACTIVE BUS in comma-separated complex number form G + jB.";
		commandHelp[54] = "Just like the Voltages command, except the voltages are in per unit if the kVbase at the bus is defined.";
		commandHelp[55] = "Returns variable values for active element if PC element. Otherwise, returns null.";
		commandHelp[56] = "Returns variable names for active element if PC element. Otherwise, returns null.";
		commandHelp[57] = "Define x,y coordinates for buses.  Execute after Solve or MakeBusList command is executed so that bus lists are defined." +
				"Reads coordinates from a CSV file with records of the form: busname, x, y."+CRLF+CRLF+
				"Example: BusCoords [file=]xxxx.csv";
		commandHelp[58] = "Updates the buslist using the currently enabled circuit elements.  (This happens automatically for Solve command.)";
		commandHelp[59] = "Attempts to convert present circuit model to a positive sequence equivalent. " +
				"It is recommended to Save the circuit after this and edit the saved version to correct possible misinterpretations.";
		commandHelp[60] = "{All | MeterName}  Default is \"All\".  Reduce the circuit according to reduction options. " +
				"See \"Set ReduceOptions\" and \"Set Keeplist\" options." +
				"Energymeter objects actually perform the reduction.  \"All\" causes all meters to reduce their zones.";
		commandHelp[61] = "{All | MeterName}  Default is \"All\". Interpolates coordinates for missing bus coordinates in meter zone";
		commandHelp[62] = "Alignfile [file=]filename.  Aligns DSS script files in columns for easier reading.";
		commandHelp[63] = "[class=]{Loadshape | TShape | Monitor  } [object=]{ALL (Loadshapes only) | objectname}. " +
				"Send specified object to TOP.  Loadshapes and TShapes must be hourly fixed interval. ";
		commandHelp[64] = "Usage: Rotate [angle=]nnn.  Rotate circuit plotting coordinates by specified angle (degrees). ";
		commandHelp[65] = "Displays the difference between the present solution and the last on saved using the SAVE VOLTAGES command.";
		commandHelp[66] = "Returns a power flow summary of the most recent solution in the global result string.";
		commandHelp[67] = "kw=nn how={Proportional | Uniform |Random | Skip} skip=nn PF=nn file=filename MW=nn" +CRLF +
				"Distributes generators on the system in the manner specified by \"how\"." + CRLF +
				"kW = total generation to be distributed (default=1000) "+ CRLF +
				"how= process name as indicated (default=proportional to load)" + CRLF +
				"skip = no. of buses to skip for \"How=Skip\" (default=1)" + CRLF +
				"PF = power factor for new generators (default=1.0)"+ CRLF +
				"file = name of file to save (default=distgenerators.txt)"+ CRLF +
				"MW = alternate way to specify kW (default = 1)";
		commandHelp[68] = "[case=]casename [year=]yr [registers=](reg1, reg2,...)  [peak=]y/n  [meter=]metername" +CRLF+
				"Plots demand interval (DI) results from yearly simulation cases.  "+
				"Plots selected registers from selected meter file (default = DI_Totals.CSV).  " +
				"Peak defaults to NO.  If YES, only daily peak of specified registers "+
				"is plotted. Example:"+CRLF+CRLF+
				" DI_Plot basecase year=5 registers=(9,11) no";
		commandHelp[69] = "[Case1=]casename [case2=]casename [register=](register number) [meter=]{Totals* | SystemMeter | metername}. "+CRLF+
				"Compares yearly simulations of two specified cases with respect to the quantity in the designated register "+
				"from the designated meter file. "+
				"Defaults: Register=9 meter=Totals.  Example:"+CRLF+CRLF+
				"Comparecases base pvgens 10";
		commandHelp[70] = "[cases=](case1, case2, ...) [registers=](reg1, reg2, ...)  [meter=]{Totals* | SystemMeter | metername}"+
				"Plots yearly curves for specified cases and registers. "+CRLF+
				"Default: meter=Totals. Example: "+CRLF+CRLF+
				"yearlycurves cases=(basecase, pvgens) registers=9";
		commandHelp[71] = "Change default directory to specified directory"+CRLF+CRLF+
				"CD dirname";
		commandHelp[72] = "[What=] one of {Currents* | Voltages | Powers} [element=]full_element_name  (class.name). " +
				"Shows the selected quantity for selected element on a multiphase line drawing in phasor values.";
		commandHelp[73] = "Close all DI files ... useful at end of yearly solution where DI files are left open. " +
				"(Reset and Set Year=nnn will also close the DI files)";
		commandHelp[74] = "Do a DOS command. Sends the command \"cmd ... \" to Windows. Execute the \"cmd /?\" command "+
				"in a DOS window to see the options. To do a DOS command and automatically exit, do " + CRLF+CRLF+
				"DOScmd /c ...command string ..."+CRLF+CRLF+
				"To keep the DOS window open, use /k switch.";
		commandHelp[75] = "Execute state estimator on present circuit given present sensor values.";
		commandHelp[76] = "Reconductor a line section. Must be in an EnergyMeter zone. " + CRLF +
				"Syntax: Reconductor Line1=... Line2=... [LineCode= | Geometry = ] " +CRLF+
				"Line1 and Line2 may be given in any order. All lines in the path between the two are redefined " +
				"with either the LineCode or Geometry.";
		commandHelp[77] = "For step control of solution process: Intialize iteration counters, etc. that normally occurs at the " +
				"start of a snapshot solution process.";
		commandHelp[78] = "For step control of solution process: Solves the circuit in present state but does not check for control actions.";
		commandHelp[79] = "For step control of solution process: Sample the control elements, which push control action requests onto the control queue.";
		commandHelp[80] = "For step control of solution process: Pops control actions off the control queue according to the present control mode rules. " +
				"Dispatches contol actions to proper control element \"DoPendingAction\" handlers.";
		commandHelp[81] = "For step control of solution process: Show the present control queue contents.";
		commandHelp[82] = "For step control of solution process: Invoke direct solution function in DSS. Non-iterative solution of Y matrix and active sources only.";
		commandHelp[83] = "For step control of solution process: Invoke iterative power flow solution function of DSS directly.";
		commandHelp[84] = "Add a marker to the active plot. Example: "+CRLF+CRLF+"AddMarker Bus=busname code=nn color=$00FF0000 size=3";
		commandHelp[85] = "Read GUIDS for class names. Tab or comma-delimited file with full object name and GUID";
		commandHelp[86] = "Set load and generator object kv from bus voltage base and connection type.";
		commandHelp[87] = "Convert all Loadshapes presently loaded into either files of single or files of double. "+
				"Usually files of singles are adequate precision for loadshapes.  Syntax:"+CRLF+CRLF+
				"cvrtloadshapes type=sng  (this is the default)"+CRLF+
				"cvrtloadshapes type=dbl"+CRLF+CRLF+
				"A DSS script for loading the loadshapes from the created files is produced and displayed in the default editor. ";
		commandHelp[88] = "Global result is set to voltage difference, volts and degrees, (Node1 - Node2) between any two nodes. Syntax:" +CRLF+CRLF+
		"   NodeDiff Node1=MyBus.1 Node2=MyOtherBus.1";
		commandHelp[89] = "Generates a script to change the phase designation of all lines downstream from a start in line. Useful for such things as moving a single-phase " +
		"lateral from one phase to another and keep the phase designation consistent for reporting functions that need it to be " +
		"(not required for simply solving). "+CRLF+CRLF+
		"StartLine=... PhaseDesignation=\"...\"  EditString=\"...\" ScriptFileName=... StopAtTransformers=Y/N/T/F" +CRLF+CRLF+
		"Enclose the PhaseDesignation in quotes since it contains periods (dots)." + CRLF +
		"You may add and optional EditString to edit any other line properties."+CRLF+CRLF+
		"Rephase StartLine=Line.L100  PhaseDesignation=\".2\"  EditString=\"phases=1\" ScriptFile=Myphasechangefile.DSS  Stop=No";
		commandHelp[90] = "Bus=...  X=...  Y=... Set the X, Y coordinates for a single bus. Prerequisite: Bus must exist as a result of a Solve, CalcVoltageBases, or MakeBusList command.";
		commandHelp[91] = "Update Storage elements based on present solution and time interval. ";
		commandHelp[92] = "Change Bus and circuit element names to generic values to remove identifying names. Generally, " +
		"you will follow this command immediately by a \"Save Circuit Dir=MyDirName\" command.";
		commandHelp[93] = "Define x,y coordinates for buses using Latitude and Longitude values (decimal numbers).  Similar to BusCoords command. " +
				"Execute after Solve command or MakeBusList command is executed so that bus lists are defined." +
				"Reads coordinates from a CSV file with records of the form: busname, Latitude, Longitude."+CRLF+CRLF+
				"Example: LatLongCoords [file=]xxxx.csv" +CRLF+CRLF+
				"Note: Longitude is mapped to x coordinate and Latitude is mapped to y coordinate.";
		commandHelp[94] = "Batch edit objects in the same class. Example: BatchEdit Load..* duty=duty_shape" + CRLF +
				"In place of the object name, supply a PERL regular expression. .* matches all names." + CRLF +
				"The subsequent parameter string is applied to each object selected.";
	}

	public void processCommand(String cmdLine) {
		int paramPointer;
		String paramName;
		String param;
		StringBuffer objName = new StringBuffer();
		StringBuffer propName = new StringBuffer();
		Parser parser = Parser.getInstance();

		try {
			DSSGlobals.cmdResult = 0;
			DSSGlobals.errorNumber = 0;  // reset error number
			DSSGlobals.globalResult = "";

			/* Load up the parser and process the first parameter only */
			lastCmdLine = cmdLine;
			parser.setCmdString(lastCmdLine);  // load up command parser
			DSSGlobals.lastCommandWasCompile = false;

			paramPointer = -1;
			paramName = parser.getNextParam();
			param = parser.makeString();
			if (param.length() == 0)
				return;  // skip blank line

			// check for command verb or property value
			// commands do not have equal signs so paramName must be zero
			if (paramName.length() == 0)
				paramPointer = commandList.getCommand(param);

			// check first for "compile" or "redirect" and return
			switch (paramPointer) {
			case 13:
				if (DSSExecutive.getInstance().isRecorderOn())
					DSSExecutive.getInstance().writeToRecorderFile(DSSGlobals.CRLF+"!*********"+cmdLine);
				DSSGlobals.cmdResult = ExecHelper.doRedirect(true);
				return;
			case 19:
				if (DSSExecutive.getInstance().isRecorderOn())
					DSSExecutive.getInstance().writeToRecorderFile(DSSGlobals.CRLF+"!*********"+cmdLine);
				DSSGlobals.cmdResult = ExecHelper.doRedirect(false);
				return;
			default:  // write everything direct to recorder, if on
				if (DSSExecutive.getInstance().isRecorderOn())
					DSSExecutive.getInstance().writeToRecorderFile(cmdLine);
				break;
			}

			// things that are ok to do before a circuit is defined
			switch (paramPointer) {
			case 0:
				DSSGlobals.cmdResult = ExecHelper.doNewCmd();  // new
				break;
			case 14:
				if (DSSGlobals.activeCircuit == null) {
					ExecOptions.getInstance().doSetCmd_NoCircuit();  // can only call this if no circuit active
					return;  // we exit with either a good outcome or bad
				}
				break;
			case 18:
				// do nothing - comment
				break;
			case 20:
				DSSGlobals.cmdResult = ExecHelper.doHelpCmd();
				break;
			case 21:
				if (!DSSGlobals.isDLL)
					DSSGlobals.DSSForms.exitControlPanel();
				break;
			case 24:
				DSSGlobals.DSSForms.showControlPanel();
				break;
			case 26:
				ExecHelper.doClearCmd();
				break;
			case 27:
				ExecHelper.doAboutBox();
				break;
			case 34:
				DSSGlobals.cmdResult = ExecHelper.doFileEditCmd();
				break;
			case 48:
				DSSGlobals.cmdResult = ExecHelper.doClassesCmd();
				break;
			case 49:
				DSSGlobals.cmdResult = ExecHelper.doUserClassesCmd();
				break;
			case 62:
				DSSGlobals.cmdResult = ExecHelper.doAlignFileCmd();
				break;
			case 68:
				DSSGlobals.cmdResult = ExecHelper.doDI_PlotCmd();
				break;
			case 69:
				DSSGlobals.cmdResult = ExecHelper.doCompareCasesCmd();
				break;
			case 70:
				DSSGlobals.cmdResult = ExecHelper.doYearlyCurvesCmd();
				break;
			case 71:
				paramName = parser.getNextParam();
				param = parser.makeString();
				if (new File(DSSGlobals.currentDirectory).exists()) {
					DSSGlobals.currentDirectory = param;
					DSSGlobals.cmdResult = 0;
					DSSGlobals.setDataPath(param);  // change DSS data directory
				} else {
					DSSGlobals.doSimpleMsg("Directory \""+param+"\" not found.", 282);
				}
				break;
			case 74:
				ExecHelper.doADOScmd();
				break;
			case 87:
				ExecHelper.doCvrtLoadshapesCmd();
				break;
			default:
				if (DSSGlobals.activeCircuit == null)
					DSSGlobals.doSimpleMsg("You must create a new circuit object first: \"new circuit.mycktname\" to execute this command.", 301);
				break;
			}

			// now check to see if this is a command or a property reference

			if (paramPointer == -1) {
				/* If not a command or the command is unknown, then it could be a property of a circuit element */

				/* If a command or no text before the = sign, then error */
				if (paramName.length() == 0 || paramName.equalsIgnoreCase("command")) {
					DSSGlobals.doSimpleMsg("Unknown command: \"" + param + "\" "+ DSSGlobals.CRLF + parser.getCmdString(), 302);
					DSSGlobals.cmdResult = 1;
				} else {
					ExecHelper.parseObjName(paramName, objName, propName);
					if (objName.length() > 0)
						DSSGlobals.setObject(objName.toString());  // set active element
					if (DSSGlobals.activeDSSObject != null) {
						// rebuild command line and pass to editor
						// use quotes to ensure first parameter is interpreted ok after rebuild
						parser.setCmdString(propName.toString() + "=\"" + param + "\" " + parser.getRemainder());
						DSSGlobals.activeDSSClass.edit();
					}
				}
				return;
			}

			// process the rest of the commands
			switch (paramPointer) {
			case 1:
				DSSGlobals.cmdResult = ExecHelper.doEditCmd();  // edit
				break;
			case 2:
				DSSGlobals.cmdResult = ExecHelper.doMoreCmd(); // more
				break;
			case 3:
				DSSGlobals.cmdResult = ExecHelper.doMoreCmd(); // m
				break;
			case 4:
				DSSGlobals.cmdResult = ExecHelper.doMoreCmd(); // ~
				break;
			case 5:
				DSSGlobals.cmdResult = ExecHelper.doSelectCmd();
				break;
			case 6:
				DSSGlobals.cmdResult = ExecHelper.doSaveCmd(); // save
				break;
			case 7:
				DSSGlobals.cmdResult = ShowOptions.getInstance().doShowCmd(); // show
				break;
			case 8:
				DSSGlobals.cmdResult = ExecOptions.getInstance().doSetCmd(1); // solve
				break;
			case 9:
				DSSGlobals.cmdResult = ExecHelper.doEnableCmd();
				break;
			case 10:
				DSSGlobals.cmdResult = ExecHelper.doDisableCmd();
				break;
			case 11:
				DSSGlobals.cmdResult = PlotOptions.getInstance().doPlotCmd();
				break;
			case 12:
				DSSGlobals.cmdResult = ExecHelper.doResetCmd();
				break;
			case 14:
				DSSGlobals.cmdResult = ExecOptions.getInstance().doSetCmd(0);  // set with no solve
				break;
			case 15:
				DSSGlobals.cmdResult = ExecHelper.doPropertyDump();
				break;
			case 17:
				DSSGlobals.cmdResult = ExecHelper.doCloseCmd();
				break;
			case 22:
				DSSGlobals.cmdResult = ExecHelper.doQueryCmd();
				break;
			case 23:
				DSSGlobals.cmdResult = ExecHelper.doNextCmd();
				break;
//			case 24:
//				DSSForms.showControlPanel()
//				break;
			case 25:
				DSSGlobals.cmdResult = ExecHelper.doSampleCmd();
				break;
//			case 26:
//				clearAllCircuits();
//				disposeDSSClasses();
//				createDSSClasses();
//				break;
//			case 27:
//				doAboutBox();
//				break;
			case 28:
				DSSGlobals.cmdResult = ExecHelper.doSetVoltageBases();
				break;
			case 29:
				DSSGlobals.cmdResult = ExecHelper.doSetKVBase();
				break;
			case 30:
				// force rebuilding of Y
				DSSGlobals.activeCircuit.invalidateAllPCElements();
				break;
			case 31:
				DSSGlobals.cmdResult = ExecOptions.getInstance().doGetCmd();
				break;
			case 32:
				DSSGlobals.activeCircuit.getSolution().setSolutionInitialized(false);
				break;
			case 33:
				DSSGlobals.cmdResult = ExportOptions.getInstance().doExportCmd();
				break;
			case 34:
				DSSGlobals.cmdResult = ExecHelper.doFileEditCmd();
				break;
			case 35:
				DSSGlobals.cmdResult = ExecHelper.doVoltagesCmd(false);
				break;
			case 36:
				DSSGlobals.cmdResult = ExecHelper.doCurrentsCmd();
				break;
			case 37:
				DSSGlobals.cmdResult = ExecHelper.doPowersCmd();
				break;
			case 38:
				DSSGlobals.cmdResult = ExecHelper.doSeqVoltagesCmd();
				break;
			case 39:
				DSSGlobals.cmdResult = ExecHelper.doSeqCurrentsCmd();
				break;
			case 40:
				DSSGlobals.cmdResult = ExecHelper.doSeqPowersCmd();
				break;
			case 42:
				DSSGlobals.cmdResult = ExecHelper.doLossesCmd();
				break;
			case 43:
				DSSGlobals.cmdResult = ExecHelper.doCktLossesCmd();
				break;
			case 44:
				DSSGlobals.cmdResult = ExecHelper.doAllocateLoadsCmd();
				break;
			case 45:
				DSSGlobals.cmdResult = ExecHelper.doFormEditCmd();
				break;
			case 46:
				DSSGlobals.cmdResult = ExecHelper.doMeterTotals();
				break;
			case 47:
				DSSGlobals.cmdResult = ExecHelper.doCapacityCmd();
				break;
//			case 48:
//				Globals.setCmdResult(ExecHelper.doClassesCmd());
//				break;
//			case 49:
//				Globals.setCmdResult(ExecHelper.doUserClassesCmd();
//				break;
			case 50:
				DSSGlobals.cmdResult = ExecHelper.doZscCmd(true);
				break;
			case 51:
				DSSGlobals.cmdResult = ExecHelper.doZsc10Cmd();
				break;
			case 52:
				DSSGlobals.cmdResult = ExecHelper.doZscRefresh();
				break;
			case 53:
				DSSGlobals.cmdResult = ExecHelper.doZscCmd(false);
				break;
			case 54:
				DSSGlobals.cmdResult = ExecHelper.doVoltagesCmd(true);
				break;
			case 55:
				DSSGlobals.cmdResult = ExecHelper.doVarValuesCmd();
				break;
			case 56:
				DSSGlobals.cmdResult = ExecHelper.doVarNamesCmd();
				break;
			case 57:
				DSSGlobals.cmdResult = ExecHelper.doBusCoordsCmd(false);
				break;
			case 58:
				if (DSSGlobals.activeCircuit.isBusNameRedefined())
					DSSGlobals.activeCircuit.reProcessBusDefs();
				break;
			case 59:
				DSSGlobals.cmdResult = ExecHelper.doMakePosSeq();
				break;
			case 60:
				DSSGlobals.cmdResult = ExecHelper.doReduceCmd();
				break;
			case 61:
				DSSGlobals.cmdResult = ExecHelper.doInterpolateCmd();
				break;
			case 63:
				DSSGlobals.cmdResult = ExecHelper.doTOPCmd();
				break;
			case 64:
				DSSGlobals.cmdResult = ExecHelper.doRotateCmd();
				break;
			case 65:
				DSSGlobals.cmdResult = ExecHelper.doVDiffCmd();
				break;
			case 66:
				DSSGlobals.cmdResult = ExecHelper.doSummaryCmd();
				break;
			case 67:
				DSSGlobals.cmdResult = ExecHelper.doDistributeCmd();
				break;
			case 68:
				break;
			case 69:
				break;
			case 70:
				break;
			case 71:
				break;
			case 72:
				DSSGlobals.cmdResult = ExecHelper.doVisualizeCmd();
				break;
			case 73:
				DSSGlobals.cmdResult = ExecHelper.doCloseDICmd();
				break;
			case 75:
				DSSGlobals.cmdResult = ExecHelper.doEstimateCmd();
				break;
			case 76:
				DSSGlobals.cmdResult = ExecHelper.doReconductorCmd();
				break;
			/* Step solution commands */
			case 77:
				DSSGlobals.activeCircuit.getSolution().snapShotInit();
				break;
			case 78:
				DSSGlobals.activeCircuit.getSolution().solveCircuit();
				break;
			case 79:
				DSSGlobals.activeCircuit.getSolution().sampleControlDevices();
				break;
			case 80:
				DSSGlobals.activeCircuit.getSolution().doControlActions();
				break;
			case 81:
				DSSGlobals.activeCircuit.getControlQueue().showQueue(DSSGlobals.DSSDirectory + DSSGlobals.circuitName_ + "ControlQueue.csv");
				break;
			case 82:
				DSSGlobals.activeCircuit.getSolution().solveDirect();
				break;
			case 83:
				DSSGlobals.activeCircuit.getSolution().doPFlowSolution();
				break;
			case 84:
				DSSGlobals.cmdResult = ExecHelper.doAddMarkerCmd();
				break;
			case 85:
				DSSGlobals.cmdResult = ExecHelper.doUUIDsCmd();
				break;
			case 86:
				DSSGlobals.cmdResult = ExecHelper.doSetLoadAndGenKVCmd();
				break;
			case 87:
				break;
			case 88:
				DSSGlobals.cmdResult = ExecHelper.doNodeDiffCmd();
				break;
			case 89:
				DSSGlobals.cmdResult = ExecHelper.doRephaseCmd();
				break;
			case 90:
				DSSGlobals.cmdResult = ExecHelper.doSetBusXYCmd();
				break;
			case 91:
				DSSGlobals.cmdResult = ExecHelper.doUpdateStorageCmd();
				break;
			case 92:
				Utilities.obfuscate();
				break;
			case 93:
				DSSGlobals.cmdResult = ExecHelper.doBusCoordsCmd(true);  // swaps X and Y
			case 94:
				DSSGlobals.cmdResult = ExecHelper.doBatchEditCmd();
			default:
				// ignore excess parameters
				break;
			}
		} catch (Exception e) {
//			Globals.doErrorMsg("ProcessCommand"+DSSGlobals.CRLF+"Exception raised while processing DSS command:"+ DSSGlobals.CRLF + Parser.getCmdString(),
//					e.getMessage(),
//					"Error in command string or circuit data.", 303);
			e.printStackTrace();
		}
	}

	public String getLastCmdLine() {
		return lastCmdLine;
	}

	public void setLastCmdLine(String line) {
		lastCmdLine = line;
	}

	public String getRedirFile() {
		return redirFile;
	}

	public void setRedirFile(String file) {
		redirFile = file;
	}

	public String[] getExecCommand() {
		return execCommand;
	}

	public void setExecCommand(String[] command) {
		execCommand = command;
	}

	public CommandList getCommandList() {
		return commandList;
	}

	public void setCommandList(CommandList list) {
		commandList = list;
	}

	public String getExecCommand(int i) {
		return execCommand[i];
	}

	public String getCommandHelp(int i) {
		return commandHelp[i];
	}

}
