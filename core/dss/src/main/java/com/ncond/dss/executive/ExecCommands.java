package com.ncond.dss.executive;

import java.io.File;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class ExecCommands {

	private static final String CRLF = DSS.CRLF;

	public static final int NumExecCommands = 98;

	public static final String[] execCommand = defineCommands();
	public static final String[] commandHelp = defineHelp();

	public static CommandList commandList = new CommandList(execCommand, true);

	/** Always has last command processed */
	public static String lastCmdLine;
	public static String redirFile;

	private static String[] defineCommands() {
		String[] commands = new String[NumExecCommands];

		commands[0]  = "New";
		commands[1]  = "Edit";
		commands[2]  = "More";
		commands[3]  = "M";
		commands[4]  = "~";
		commands[5]  = "Select";
		commands[6]  = "Save";
		commands[7]  = "Show";
		commands[8]  = "Solve";
		commands[9]  = "Enable";
		commands[10] = "Disable";
		commands[11] = "Plot";
		commands[12] = "Reset";
		commands[13] = "Compile";
		commands[14] = "Set";    // set DSS options
		commands[15] = "Dump";   // debug dump
		commands[16] = "Open";   // open a device terminal conductor
		commands[17] = "Close";  // close a device terminal conductor
		commands[18] = "//";     // comment
		commands[19] = "Redirect";
		commands[20] = "Help";
		commands[21] = "Quit";
		commands[22] = "?";      // property value inquiry
		commands[23] = "Next";
		commands[24] = "Panel";
		commands[25] = "Sample";
		commands[26] = "Clear";
		commands[27] = "About";
		commands[28] = "Calcvoltagebases";  // computes voltage bases
		commands[29] = "SetkVBase";  // set kV base at a bus
		commands[30] = "BuildY";  // forces rebuild of Y matrix right now
		commands[31] = "Get";  // returns values set with set command
		commands[32] = "Init";
		commands[33] = "Export";
		commands[34] = "Fileedit";
		commands[35] = "Voltages";
		commands[36] = "Currents";
		commands[37] = "Powers";
		commands[38] = "Seqvoltages";
		commands[39] = "Seqcurrents";
		commands[40] = "Seqpowers";
		commands[41] = "Losses";
		commands[42] = "Phaselosses";
		commands[43] = "Cktlosses";
		commands[44] = "Allocateloads";
		commands[45] = "Formedit";
		commands[46] = "Totals";  // total all energyMeters
		commands[47] = "Capacity";  // find upper kW limit of system for present year
		commands[48] = "Classes";  // list of intrinsic classes
		commands[49] = "Userclasses";  // list of user-defined classes
		commands[50] = "Zsc";
		commands[51] = "Zsc10";
		commands[52] = "ZscRefresh";
		commands[53] = "Ysc";
		commands[54] = "puvoltages";
		commands[55] = "VarValues";
		commands[56] = "Varnames";
		commands[57] = "Buscoords";
		commands[58] = "MakeBusList";
		commands[59] = "MakePosSeq";
		commands[60] = "Reduce";
		commands[61] = "Interpolate";
		commands[62] = "AlignFile";
		commands[63] = "TOP";
		commands[64] = "Rotate";
		commands[65] = "Vdiff";
		commands[66] = "Summary";
		commands[67] = "Distribute";
		commands[68] = "DI_plot";
		commands[69] = "Comparecases";
		commands[70] = "YearlyCurves";
		commands[71] = "CD";
		commands[72] = "Visualize";
		commands[73] = "CloseDI";
		commands[74] = "DOScmd";
		commands[75] = "Estimate";
		commands[76] = "Reconductor";
		commands[77] = "_InitSnap";
		commands[78] = "_SolveNoControl";
		commands[79] = "_SampleControls";
		commands[80] = "_DoControlActions";
		commands[81] = "_ShowControlQueue";
		commands[82] = "_SolveDirect";
		commands[83] = "_SolvePFlow";
		commands[84] = "AddMarker";

		commands[85] = "Guids";
		commands[86] = "SetLoadAndGenKV";
		commands[87] = "CvrtLoadshapes";
		commands[88] = "NodeDiff";
		commands[89] = "Rephase";
		commands[90] = "SetBusXY";
		commands[91] = "UpdateStorage";
		commands[92] = "Obfuscate";
		commands[93] = "LatLongCoords";
		commands[94] = "BatchEdit";
		commands[95] = "Pstcalc";
		commands[96] = "Variable";
		commands[97] = "ReprocessBuses";

		return commands;
	}

	private static String[] defineHelp() {
		String[] help = new String[NumExecCommands];

		help[0]  = "Create a new object within the DSS. Object becomes the "+
				"active object" + CRLF +
				"Example: New Line.line1 ...";
		help[1]  = "Edit an object. The object is selected and it then becomes the active object."+CRLF+CRLF+
				"Note that Edit is the default command.  You many change a property value simply by " +
				"giving the full property name and the new value, for example:"+CRLF+CRLF+
				"line.line1.r1=.04"+CRLF+
				"vsource.source.kvll=230";
		help[2]  = "Continuation of editing on the active object.";
		help[3]  = "Continuation of editing on the active object. An abbreviation for More";
		help[4]  = "Continuation of editing on the active object. An abbreviation."+CRLF+CRLF+
				"Example:"+CRLF+
				"New Line.Line1 Bus1=aaa  bus2=bbb"+CRLF+
				"~ R1=.058" +CRLF+
				"~ X1=.1121";
		help[5]  = "Selects an element and makes it the active element.  You can also specify the " +
				"active terminal (default = 1)."+CRLF+  CRLF+
				"Syntax:"+CRLF+
				"Select [element=]elementname  [terminal=]terminalnumber "+CRLF+CRLF+
				"Example:"+CRLF+
				"Select Line.Line1 "+CRLF+
				"~ R1=.1"+CRLF+"(continue editing)"+CRLF+CRLF+
				"Select Line.Line1 2 " +CRLF+
				"Voltages  (returns voltages at terminal 2 in Result)";
		help[6]  = "{Save [class=]{Meters | Circuit | Voltages | (classname)} [file=]filename [dir=]directory " + CRLF +
				" Default class = Meters, which saves the present values in both monitors and energy meters in the active circuit. " +
				"\"Save Circuit\" saves the present enabled circuit elements to the specified subdirectory in standard DSS form " +
				"with a Master.txt file and separate files for each class of data. If Dir= not specified a unique name based on the circuit name is created "+
				"automatically.  If Dir= is specified, any existing files are overwritten. " + CRLF +
				"\"Save Voltages\" saves the present solution in a simple CSV format in a file called DSS_SavedVoltages. "+
				"Used for VDIFF command."+CRLF+
				"Any class can be saved to a file.  If no filename specified, the classname is used.";
		help[7]  = "Writes selected results to a text file and brings "+
				"up the default text editor (see Set Editor=....) with the file for you to browse."+CRLF+  CRLF+
				"See separate help on Show command. "  +CRLF+  CRLF+
				"Default is \"show voltages LN Seq\".  ";
		help[8]  = "Perform the solution of the present solution mode. You can set any option "+
				"that you can set with the Set command (see Set). "+
				"The Solve command is virtually synonymous with the Set command except that " +
				"a solution is performed after the options are processed.";
		help[9]  = "Enables a circuit element or entire class.  Example:" +CRLF+
				"Enable load.loadxxx"+CRLF+
				"Enable generator.*  (enables all generators)";
		help[10] = "Disables a circuit element or entire class.  Example:" +CRLF+
				"Disable load.loadxxx"+CRLF+
				"Disable generator.*  (Disables all generators)"+CRLF+ CRLF+
				"The item remains defined, but is not included in the solution.";
		help[11] = "Plots circuits and results in a variety of manners.  See separate Plot command help.";
		help[12] = "{MOnitors | MEters | Faults | Controls | Eventlog | Keeplist |(no argument) } Resets all Monitors, Energymeters, etc. " +
				"If no argument specified, resets all options listed.";
		help[13] = "Reads the designated file name containing DSS commands " +
				"and processes them as if they were entered directly into the command line. "+
				"The file is said to be \"compiled.\" "  +
				"Similar to \"redirect\" except changes the default directory to the path of the specified file."+CRLF+CRLF+
				"Syntax:"+CRLF+
				"Compile filename";
		help[14] = "Used to set various DSS solution modes and options.  You may also set the options with the Solve command. "+
				"See \"Options\" for help.";
		help[15] = "Display the properties of either a specific DSS object or a complete dump "+
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
		help[16] = "Opens the specified terminal and conductor of the specified circuit element. " +
				"If the conductor is not specified, all phase conductors of the terminal are opened." +CRLF+CRLF+
				"Examples:"+CRLF+
				"Open line.line1 2 "+CRLF+
				"(opens all phases of terminal 2)"+CRLF+CRLF+
				"Open line.line1 2 3" +CRLF+
				"(opens the 3rd conductor of terminal 2)";
		help[17] = "Opposite of the Open command.";   // Close a device terminal conductor
		help[18] = "Comment.  Command line is ignored.";       // Comment
		help[19] = "Reads the designated file name containing DSS commands " +
				"and processes them as if they were entered directly into the command line. "+
				"Similar to \"Compile\", but leaves current directory where it was when Redirect command is invoked." +
				"Can temporarily change to subdirectories if nested Redirect commands require."+CRLF+CRLF+
				"ex:  redirect filename";
		help[20] = "Gives this display.";
		help[21] = "Shuts down DSS unless this is the DLL version.  Then it does nothing;  DLL parent is responsible for shutting down the DLL.";
		help[22] = "Inquiry for property value.  Result is put into GlobalReault and can be seen in the Result Window. "+
				"Specify the full property name."+CRLF+CRLF+
				"Example: ? Line.Line1.R1" + CRLF+CRLF+
				"Note you can set this property merely by saying:"+CRLF+
				"Line.line1.r1=.058";   // Property Value inquiry
		help[23] = "{Year | Hour | t}  Increments year, hour, or time as specified.  If \"t\" is " +
				"specified, then increments time by current step size.";
		help[24] = "Displays main control panel window.";
		help[25] = "Force all monitors and meters to take a sample now.";
		help[26] = "Clear all circuits currently in memory.";
		help[27] = "Display \"About Box\".  (Result string set to Version string.)";
		help[28] = "Calculates voltagebase for buses based on voltage bases defined "+
				"with Set voltagebases=... command.";
		help[29] = "Command to explicitly set the base voltage for a bus. " +
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
		help[30] = "Forces rebuild of Y matrix upon next Solve command regardless of need. " +
				"The usual reason for doing this would be to reset the matrix for another " +
				"load level when using LoadModel=PowerFlow (the default) when the system is difficult to " +
				"solve when the load is far from its base value.  Works by invalidating the Y primitive " +
				"matrices for all the Power Conversion elements.";
		help[31] = "Returns DSS property values set using the Set command. "  +
				"Result is returne in Result property of the Text interface. " +CRLF+CRLF+
				"VBA Example:" +CRLF+CRLF+
				"DSSText.Command = \"Get mode\"" +CRLF   +
				"Answer = DSSText.Result" +CRLF +CRLF +
				"Multiple properties may be requested on one get.  The results are appended "+
				"and the individual values separated by commas." +CRLF+CRLF+
				"See help on Set command for property names.";
		help[32] = "This command forces reinitialization of the solution for the next Solve command. " +
				"To minimize iterations, most solutions start with the previous solution unless there " +
				"has been a circuit change.  However, if the previous solution is bad, it may be necessary " +
				"to re-initialize.  In most cases, a re-initiallization results in a zero-load power flow " +
				"solution with only the series power delivery elements considered.";
		help[33] = "Export various solution values to CSV (or XML) files for import into other programs. " +
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
		help[34] = "Edit specified file in default text file editor (see Set Editor= option)."+CRLF+CRLF+
				"Fileedit EXP_METERS.CSV (brings up the meters export file)" + CRLF+CRLF+
				"\"FileEdit\" may be abbreviated to a unique character string.";
		help[35] = "Returns the voltages for the ACTIVE BUS in the Result string. " +
				"For setting the active Bus, use the Select command or the Set Bus= option. " +
				"Returned as magnitude and angle quantities, comma separated, one set per conductor of the terminal.";
		help[36] = "Returns the currents for each conductor of ALL terminals of the active circuit element in the Result string. "+
				"(See Select command.)" +
				"Returned as comma-separated magnitude and angle.";
		help[37] = "Returns the powers (complex) going into each conductors of ALL terminals of the active circuit element in the Result string. "+
				"(See Select command.)" +
				"Returned as comma-separated kW and kvar.";
		help[38] = "Returns the sequence voltages at all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values." +
				"Order of returned values: 0, 1, 2  (for each terminal).";
		help[39] = "Returns the sequence currents into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values." +
				"Order of returned values: 0, 1, 2  (for each terminal).";
		help[40] = "Returns the sequence powers into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated kw, kvar pairs." +
				"Order of returned values: 0, 1, 2  (for each terminal).";
		help[41] = "Returns the total losses for the active circuit element (see Select command) " +
				"in the Result string in kW, kvar.";
		help[42] = "Returns the losses for the active circuit element (see Select command) " +
				"for each PHASE in the Result string in comma-separated kW, kvar pairs.";
		help[43] = "Returns the total losses for the active circuit in the Result string in kW, kvar.";
		help[44] = "Estimates the allocation factors for loads that are defined using the XFKVA property. " +
				"Requires that energymeter objects be defined with the PEAKCURRENT property set. " +
				"Loads that are not in the zone of an energymeter cannot be allocated.";
		help[45] = "FormEdit [class.object].  Brings up form editor on active DSS object.";
		help[46] = "Totals all EnergyMeter objects in the circuit and reports register totals in the result string.";
		help[47] = "Find the maximum load the active circuit can serve in the PRESENT YEAR. Uses the EnergyMeter objects with the registers "+
				"set with the SET UEREGS= (..) command for the AutoAdd functions.  Syntax (defaults shown):"+CRLF+CRLF+
				"capacity [start=]0.9 [increment=]0.005" + CRLF + CRLF +
				"Returns the metered kW (load + losses - generation) and per unit load multiplier for the loading level at which something in the system reports an overload or undervoltage. "+
				"If no violations, then it returns the metered kW for peak load for the year (1.0 multiplier). "+
				"Aborts and returns 0 if no energymeters.";
		help[48] = "List of intrinsic DSS Classes. Returns comma-separated list in Result variable.";
		help[49] = "List of user-defined DSS Classes. Returns comma-separated list in Result variable.";
		help[50] = "Returns full Zsc matrix for the ACTIVE BUS in comma-separated complex number form.";
		help[51] = "Returns symmetrical component impedances, Z1, Z0 for the ACTIVE BUS in comma-separated R+jX form.";
		help[52] = "Refreshes Zsc matrix for the ACTIVE BUS.";
		help[53] = "Returns full Ysc matrix for the ACTIVE BUS in comma-separated complex number form G + jB.";
		help[54] = "Just like the Voltages command, except the voltages are in per unit if the kVbase at the bus is defined.";
		help[55] = "Returns variable values for active element if PC element. Otherwise, returns null.";
		help[56] = "Returns variable names for active element if PC element. Otherwise, returns null.";
		help[57] = "Define x,y coordinates for buses.  Execute after Solve or MakeBusList command is executed so that bus lists are defined." +
				"Reads coordinates from a CSV file with records of the form: busname, x, y."+CRLF+CRLF+
				"Example: BusCoords [file=]xxxx.csv";
		help[58] = "Updates the buslist, if needed, using the currently enabled circuit elements.  (This happens automatically for Solve command.) (See ReprocessBuses)";
		help[59] = "Attempts to convert present circuit model to a positive sequence equivalent. " +
				"It is recommended to Save the circuit after this and edit the saved version to correct possible misinterpretations.";
		help[60] = "{All | MeterName}  Default is \"All\".  Reduce the circuit according to reduction options. " +
				"See \"Set ReduceOptions\" and \"Set Keeplist\" options." +
				"Energymeter objects actually perform the reduction.  \"All\" causes all meters to reduce their zones.";
		help[61] = "{All | MeterName}  Default is \"All\". Interpolates coordinates for missing bus coordinates in meter zone";
		help[62] = "Alignfile [file=]filename.  Aligns DSS script files in columns for easier reading.";
		help[63] = "[class=]{Loadshape | TShape | Monitor  } [object=]{ALL (Loadshapes only) | objectname}. " +
				"Send specified object to TOP.  Loadshapes and TShapes must be hourly fixed interval. ";
		help[64] = "Usage: Rotate [angle=]nnn.  Rotate circuit plotting coordinates by specified angle (degrees). ";
		help[65] = "Displays the difference between the present solution and the last on saved using the SAVE VOLTAGES command.";
		help[66] = "Returns a power flow summary of the most recent solution in the global result string.";
		help[67] = "kw=nn how={Proportional | Uniform |Random | Skip} skip=nn PF=nn file=filename MW=nn" +CRLF +
				"Distributes generators on the system in the manner specified by \"how\"." + CRLF +
				"kW = total generation to be distributed (default=1000) "+ CRLF +
				"how= process name as indicated (default=proportional to load)" + CRLF +
				"skip = no. of buses to skip for \"How=Skip\" (default=1)" + CRLF +
				"PF = power factor for new generators (default=1.0)"+ CRLF +
				"file = name of file to save (default=distgenerators.txt)"+ CRLF +
				"MW = alternate way to specify kW (default = 1)";
		help[68] = "[case=]casename [year=]yr [registers=](reg1, reg2,...)  [peak=]y/n  [meter=]metername" +CRLF+
				"Plots demand interval (DI) results from yearly simulation cases.  "+
				"Plots selected registers from selected meter file (default = DI_Totals.CSV).  " +
				"Peak defaults to NO.  If YES, only daily peak of specified registers "+
				"is plotted. Example:"+CRLF+CRLF+
				" DI_Plot basecase year=5 registers=(9,11) no";
		help[69] = "[Case1=]casename [case2=]casename [register=](register number) [meter=]{Totals* | SystemMeter | metername}. "+CRLF+
				"Compares yearly simulations of two specified cases with respect to the quantity in the designated register "+
				"from the designated meter file. "+
				"Defaults: Register=9 meter=Totals.  Example:"+CRLF+CRLF+
				"Comparecases base pvgens 10";
		help[70] = "[cases=](case1, case2, ...) [registers=](reg1, reg2, ...)  [meter=]{Totals* | SystemMeter | metername}"+
				"Plots yearly curves for specified cases and registers. "+CRLF+
				"Default: meter=Totals. Example: "+CRLF+CRLF+
				"yearlycurves cases=(basecase, pvgens) registers=9";
		help[71] = "Change default directory to specified directory"+CRLF+CRLF+
				"CD dirname";
		help[72] = "[What=] one of {Currents* | Voltages | Powers} [element=]full_element_name  (class.name). " +
				"Shows the selected quantity for selected element on a multiphase line drawing in phasor values.";
		help[73] = "Close all DI files ... useful at end of yearly solution where DI files are left open. " +
				"(Reset and Set Year=nnn will also close the DI files)";
		help[74] = "Do a DOS command. Sends the command \"cmd ... \" to Windows. Execute the \"cmd /?\" command "+
				"in a DOS window to see the options. To do a DOS command and automatically exit, do " + CRLF+CRLF+
				"DOScmd /c ...command string ..."+CRLF+CRLF+
				"To keep the DOS window open, use /k switch.";
		help[75] = "Execute state estimator on present circuit given present sensor values.";
		help[76] = "Reconductor a line section. Must be in an EnergyMeter zone. " + CRLF +
				"Syntax: Reconductor Line1=... Line2=... [LineCode= | Geometry = ] NPhases=#" +CRLF+
	                        "Line1 and Line2 may be given in any order. All lines in the path between the two are redefined " +
	                        "with either the LineCode or Geometry (not both). You may also add an optional string the alter any other line properties. "+
	                        "The edit string should be enclosed in quotes or parens or brackets." +CRLF+
	                        "Nphases is an optional filter on the number of phases in line segments to change.";
		help[77] = "For step control of solution process: Intialize iteration counters, etc. that normally occurs at the " +
				"start of a snapshot solution process.";
		help[78] = "For step control of solution process: Solves the circuit in present state but does not check for control actions.";
		help[79] = "For step control of solution process: Sample the control elements, which push control action requests onto the control queue.";
		help[80] = "For step control of solution process: Pops control actions off the control queue according to the present control mode rules. " +
				"Dispatches contol actions to proper control element \"DoPendingAction\" handlers.";
		help[81] = "For step control of solution process: Show the present control queue contents.";
		help[82] = "For step control of solution process: Invoke direct solution function in DSS. Non-iterative solution of Y matrix and active sources only.";
		help[83] = "For step control of solution process: Invoke iterative power flow solution function of DSS directly.";
		help[84] = "Add a marker to the active plot. Example: "+CRLF+CRLF+"AddMarker Bus=busname code=nn color=$00FF0000 size=3";
		help[85] = "Read GUIDS for class names. Tab or comma-delimited file with full object name and GUID";
		help[86] = "Set load and generator object kv from bus voltage base and connection type.";
		help[87] = "Convert all Loadshapes presently loaded into either files of single or files of double. "+
				"Usually files of singles are adequate precision for loadshapes.  Syntax:"+CRLF+CRLF+
				"cvrtloadshapes type=sng  (this is the default)"+CRLF+
				"cvrtloadshapes type=dbl"+CRLF+CRLF+
				"A DSS script for loading the loadshapes from the created files is produced and displayed in the default editor. ";
		help[88] = "Global result is set to voltage difference, volts and degrees, (Node1 - Node2) between any two nodes. Syntax:" +CRLF+CRLF+
		"   NodeDiff Node1=MyBus.1 Node2=MyOtherBus.1";
		help[89] = "Generates a script to change the phase designation of all lines downstream from a start in line. Useful for such things as moving a single-phase " +
				"lateral from one phase to another and keep the phase designation consistent for reporting functions that need it to be " +
				"(not required for simply solving). "+CRLF+CRLF+
				"StartLine=... PhaseDesignation=\"...\"  EditString=\"...\" ScriptFileName=... StopAtTransformers=Y/N/T/F" +CRLF+CRLF+
				"Enclose the PhaseDesignation in quotes since it contains periods (dots)." + CRLF +
				"You may add and optional EditString to edit any other line properties."+CRLF+CRLF+
				"Rephase StartLine=Line.L100  PhaseDesignation=\".2\"  EditString=\"phases=1\" ScriptFile=Myphasechangefile.DSS  Stop=No";
		help[90] = "Bus=...  X=...  Y=... Set the X, Y coordinates for a single bus. Prerequisite: Bus must exist as a result of a Solve, CalcVoltageBases, or MakeBusList command.";
		help[91] = "Update Storage elements based on present solution and time interval. ";
		help[92] = "Change Bus and circuit element names to generic values to remove identifying names. Generally, " +
		"you will follow this command immediately by a \"Save Circuit Dir=MyDirName\" command.";
		help[93] = "Define x,y coordinates for buses using Latitude and Longitude values (decimal numbers).  Similar to BusCoords command. " +
				"Execute after Solve command or MakeBusList command is executed so that bus lists are defined." +
				"Reads coordinates from a CSV file with records of the form: busname, Latitude, Longitude."+CRLF+CRLF+
				"Example: LatLongCoords [file=]xxxx.csv" +CRLF+CRLF+
				"Note: Longitude is mapped to x coordinate and Latitude is mapped to y coordinate.";
		help[94] = "Batch edit objects in the same class. Example: BatchEdit Load..* duty=duty_shape" + CRLF +
				"In place of the object name, supply a PERL regular expression. .* matches all names." + CRLF +
				"The subsequent parameter string is applied to each object selected.";
		help[95] = "Pst calculation. PstCalc Npts=nnn Voltages=[array] dt=nnn freq=nn lamp=120 or 230." +CRLF+
				"Set Npts to a big enough value to hold the incoming voltage array. " +CRLF+
				"dt = time increment in seconds. default is 1"+CRLF+
				"freq = base frequency in Hz 50 or 60. Default is default base frequency" +CRLF+
				"Lamp= 120 for North America; 230 for Europe. Default is 120" + CRLF+CRLF+
				"PSTCalc Npts=1900 V=[file=MyCSVFile.CSV, Col=3, Header=y] dt=1 freq=60 lamp=120";
		help[96] = "[name=] MyVariableName  [Index=] IndexofMyVariable " +CRLF+CRLF+
				"Returns the value of the specified state variable of the active circuit element, if a PCelement. " +
				"Returns the value as a string in the Result window or the Text.Result interface if using the COM server. " +CRLF+CRLF+
				"You may specify the variable by name or by its index. You can determine the index using the VarNames command. " +
				"If any part of the request is invalid, the Result is null.";
		help[97] = "Forces reprocessing of bus definitions whether there has been a change or not. Use for rebuilding meter zone lists " +
				"when a line length changes, for example or some other event that would not normally trigger an update to the bus list.";

		return help;

	}

	public static void processCommand(String cmdLine) {
		int paramPointer;
		String paramName;
		String param;
		String[] objName = new String[1];
		String[] propName = new String[1];
		Parser parser = Parser.getInstance();

		try {
			DSS.cmdResult = 0;
			DSS.errorNumber = 0;  // reset error number
			DSS.globalResult = "";

			/* Load up the parser and process the first parameter only */
			lastCmdLine = cmdLine;
			parser.setCmdString(lastCmdLine);  // load up command parser
			DSS.lastCommandWasCompile = false;

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
				if (Executive.getInstance().isRecorderOn())
					Executive.getInstance().writeToRecorderFile(DSS.CRLF+"!*********"+cmdLine);
				DSS.cmdResult = ExecHelper.doRedirect(true);
				return;
			case 19:
				if (Executive.getInstance().isRecorderOn())
					Executive.getInstance().writeToRecorderFile(DSS.CRLF+"!*********"+cmdLine);
				DSS.cmdResult = ExecHelper.doRedirect(false);
				return;
			default:  // write everything direct to recorder, if on
				if (Executive.getInstance().isRecorderOn())
					Executive.getInstance().writeToRecorderFile(cmdLine);
				break;
			}

			// things that are ok to do before a circuit is defined
			switch (paramPointer) {
			case 0:
				DSS.cmdResult = ExecHelper.doNewCmd();  // new
				break;
			case 14:
				if (DSS.activeCircuit == null) {
					ExecOptions.getInstance().doSetCmd_NoCircuit();  // can only call this if no circuit active
					return;  // we exit with either a good outcome or bad
				}
				break;
			case 18:
				// do nothing - comment
				break;
			case 20:
				DSS.cmdResult = ExecHelper.doHelpCmd();
				break;
			case 21:
				DSS.forms.exitControlPanel();
				break;
			case 24:
				DSS.forms.showControlPanel();
				break;
			case 26:
				ExecHelper.doClearCmd();
				break;
			case 27:
				ExecHelper.doAboutBox();
				break;
			case 34:
				DSS.cmdResult = ExecHelper.doFileEditCmd();
				break;
			case 48:
				DSS.cmdResult = ExecHelper.doClassesCmd();
				break;
			case 49:
				DSS.cmdResult = ExecHelper.doUserClassesCmd();
				break;
			case 62:
				DSS.cmdResult = ExecHelper.doAlignFileCmd();
				break;
			case 68:
				DSS.cmdResult = ExecHelper.doDI_PlotCmd();
				break;
			case 69:
				DSS.cmdResult = ExecHelper.doCompareCasesCmd();
				break;
			case 70:
				DSS.cmdResult = ExecHelper.doYearlyCurvesCmd();
				break;
			case 71:
				paramName = parser.getNextParam();
				param = parser.makeString();
				if (new File(DSS.currentDirectory).exists()) {
					DSS.currentDirectory = param;
					DSS.cmdResult = 0;
					DSS.setDataPath(param);  // change DSS data directory
				} else {
					DSS.doSimpleMsg("Directory \""+param+"\" not found.", 282);
				}
				break;
			case 74:
				ExecHelper.doADOScmd();
				break;
			case 87:
				ExecHelper.doConvertLoadShapesCmd();
				break;
			default:
				if (DSS.activeCircuit == null)
					DSS.doSimpleMsg("You must create a new circuit object first: \"new circuit.mycktname\" to execute this command.", 301);
				break;
			}

			// now check to see if this is a command or a property reference

			if (paramPointer == -1) {
				/* If not a command or the command is unknown, then it could be a property of a circuit element */

				/* If a command or no text before the = sign, then error */
				if (paramName.length() == 0 || paramName.equalsIgnoreCase("command")) {
					DSS.doSimpleMsg("Unknown command: \"" + param + "\" "+ DSS.CRLF + parser.getCmdString(), 302);
					DSS.cmdResult = 1;
				} else {
					ExecHelper.parseObjName(paramName, objName, propName);
					if (objName[0].length() > 0)
						DSS.setObject(objName[0].toString());  // set active element
					if (DSS.activeDSSObject != null) {
						// rebuild command line and pass to editor
						// use quotes to ensure first parameter is interpreted ok after rebuild
						parser.setCmdString(propName[0].toString() + "=\"" + param + "\" " + parser.getRemainder());
						DSS.activeDSSClass.edit();
					}
				}
				return;
			}

			// process the rest of the commands
			switch (paramPointer) {
			case 1:
				DSS.cmdResult = ExecHelper.doEditCmd();  // edit
				break;
			case 2:
				DSS.cmdResult = ExecHelper.doMoreCmd(); // more
				break;
			case 3:
				DSS.cmdResult = ExecHelper.doMoreCmd(); // m
				break;
			case 4:
				DSS.cmdResult = ExecHelper.doMoreCmd(); // ~
				break;
			case 5:
				DSS.cmdResult = ExecHelper.doSelectCmd();
				break;
			case 6:
				DSS.cmdResult = ExecHelper.doSaveCmd(); // save
				break;
			case 7:
				DSS.cmdResult = ShowOptions.getInstance().doShowCmd(); // show
				break;
			case 8:
				DSS.cmdResult = ExecOptions.getInstance().doSetCmd(1); // solve
				break;
			case 9:
				DSS.cmdResult = ExecHelper.doEnableCmd();
				break;
			case 10:
				DSS.cmdResult = ExecHelper.doDisableCmd();
				break;
			case 11:
				DSS.cmdResult = PlotOptions.getInstance().doPlotCmd();
				break;
			case 12:
				DSS.cmdResult = ExecHelper.doResetCmd();
				break;
			case 14:
				DSS.cmdResult = ExecOptions.getInstance().doSetCmd(0);  // set with no solve
				break;
			case 15:
				DSS.cmdResult = ExecHelper.doPropertyDump();
				break;
			case 17:
				DSS.cmdResult = ExecHelper.doCloseCmd();
				break;
			case 22:
				DSS.cmdResult = ExecHelper.doQueryCmd();
				break;
			case 23:
				DSS.cmdResult = ExecHelper.doNextCmd();
				break;
//			case 24:
//				DSSForms.showControlPanel()
//				break;
			case 25:
				DSS.cmdResult = ExecHelper.doSampleCmd();
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
				DSS.cmdResult = ExecHelper.doSetVoltageBases();
				break;
			case 29:
				DSS.cmdResult = ExecHelper.doSetKVBase();
				break;
			case 30:
				// force rebuilding of Y
				DSS.activeCircuit.invalidateAllPCElements();
				break;
			case 31:
				DSS.cmdResult = ExecOptions.getInstance().doGetCmd();
				break;
			case 32:
				DSS.activeCircuit.getSolution().setSolutionInitialized(false);
				break;
			case 33:
				DSS.cmdResult = ExportOptions.getInstance().doExportCmd();
				break;
			case 34:
				DSS.cmdResult = ExecHelper.doFileEditCmd();
				break;
			case 35:
				DSS.cmdResult = ExecHelper.doVoltagesCmd(false);
				break;
			case 36:
				DSS.cmdResult = ExecHelper.doCurrentsCmd();
				break;
			case 37:
				DSS.cmdResult = ExecHelper.doPowersCmd();
				break;
			case 38:
				DSS.cmdResult = ExecHelper.doSeqVoltagesCmd();
				break;
			case 39:
				DSS.cmdResult = ExecHelper.doSeqCurrentsCmd();
				break;
			case 40:
				DSS.cmdResult = ExecHelper.doSeqPowersCmd();
				break;
			case 42:
				DSS.cmdResult = ExecHelper.doLossesCmd();
				break;
			case 43:
				DSS.cmdResult = ExecHelper.doCktLossesCmd();
				break;
			case 44:
				DSS.cmdResult = ExecHelper.doAllocateLoadsCmd();
				break;
			case 45:
				DSS.cmdResult = ExecHelper.doFormEditCmd();
				break;
			case 46:
				DSS.cmdResult = ExecHelper.doMeterTotals();
				break;
			case 47:
				DSS.cmdResult = ExecHelper.doCapacityCmd();
				break;
//			case 48:
//				Globals.setCmdResult(ExecHelper.doClassesCmd());
//				break;
//			case 49:
//				Globals.setCmdResult(ExecHelper.doUserClassesCmd();
//				break;
			case 50:
				DSS.cmdResult = ExecHelper.doZscCmd(true);
				break;
			case 51:
				DSS.cmdResult = ExecHelper.doZsc10Cmd();
				break;
			case 52:
				DSS.cmdResult = ExecHelper.doZscRefresh();
				break;
			case 53:
				DSS.cmdResult = ExecHelper.doZscCmd(false);
				break;
			case 54:
				DSS.cmdResult = ExecHelper.doVoltagesCmd(true);
				break;
			case 55:
				DSS.cmdResult = ExecHelper.doVarValuesCmd();
				break;
			case 56:
				DSS.cmdResult = ExecHelper.doVarNamesCmd();
				break;
			case 57:
				DSS.cmdResult = ExecHelper.doBusCoordsCmd(false);
				break;
			case 58:
				if (DSS.activeCircuit.isBusNameRedefined())
					DSS.activeCircuit.reProcessBusDefs();
				break;
			case 59:
				DSS.cmdResult = ExecHelper.doMakePosSeq();
				break;
			case 60:
				DSS.cmdResult = ExecHelper.doReduceCmd();
				break;
			case 61:
				DSS.cmdResult = ExecHelper.doInterpolateCmd();
				break;
			case 63:
				DSS.cmdResult = ExecHelper.doTOPCmd();
				break;
			case 64:
				DSS.cmdResult = ExecHelper.doRotateCmd();
				break;
			case 65:
				DSS.cmdResult = ExecHelper.doVDiffCmd();
				break;
			case 66:
				DSS.cmdResult = ExecHelper.doSummaryCmd();
				break;
			case 67:
				DSS.cmdResult = ExecHelper.doDistributeCmd();
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
				DSS.cmdResult = ExecHelper.doVisualizeCmd();
				break;
			case 73:
				DSS.cmdResult = ExecHelper.doCloseDICmd();
				break;
			case 75:
				DSS.cmdResult = ExecHelper.doEstimateCmd();
				break;
			case 76:
				DSS.cmdResult = ExecHelper.doReconductorCmd();
				break;
			/* Step solution commands */
			case 77:
				DSS.activeCircuit.getSolution().snapShotInit();
				break;
			case 78:
				DSS.activeCircuit.getSolution().solveCircuit();
				break;
			case 79:
				DSS.activeCircuit.getSolution().sampleControlDevices();
				break;
			case 80:
				DSS.activeCircuit.getSolution().doControlActions();
				break;
			case 81:
				DSS.activeCircuit.getControlQueue().showQueue(DSS.currentDirectory + DSS.circuitName_ + "ControlQueue.csv");
				break;
			case 82:
				DSS.activeCircuit.getSolution().solveDirect();
				break;
			case 83:
				DSS.activeCircuit.getSolution().doPFlowSolution();
				break;
			case 84:
				DSS.cmdResult = ExecHelper.doAddMarkerCmd();
				break;
			case 85:
				DSS.cmdResult = ExecHelper.doUUIDsCmd();
				break;
			case 86:
				DSS.cmdResult = ExecHelper.doSetLoadAndGenKVCmd();
				break;
			case 87:
				break;
			case 88:
				DSS.cmdResult = ExecHelper.doNodeDiffCmd();
				break;
			case 89:
				DSS.cmdResult = ExecHelper.doRephaseCmd();
				break;
			case 90:
				DSS.cmdResult = ExecHelper.doSetBusXYCmd();
				break;
			case 91:
				DSS.cmdResult = ExecHelper.doUpdateStorageCmd();
				break;
			case 92:
				Util.obfuscate();
				break;
			case 93:
				DSS.cmdResult = ExecHelper.doBusCoordsCmd(true);  // swaps X and Y
			case 94:
				DSS.cmdResult = ExecHelper.doBatchEditCmd();
			case 95:
				DSS.cmdResult = ExecHelper.doPstCalc();
			case 96:
				DSS.cmdResult = ExecHelper.doValVarCmd();
			case 97:
				DSS.activeCircuit.reProcessBusDefs();
			default:
				// ignore excess parameters
				break;
			}
		} catch (Exception e) {
//			DSS.doErrorMsg("ProcessCommand"+DSSGlobals.CRLF+"Exception raised while processing DSS command:"+ DSSGlobals.CRLF + Parser.getCmdString(),
//					e.getMessage(),
//					"Error in command string or circuit data.", 303);
			e.printStackTrace();
		}
	}


}
