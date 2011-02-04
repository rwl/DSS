package com.epri.dss.executive.impl;

import com.epri.dss.common.impl.DSSForms;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.executive.ExecCommands;
import com.epri.dss.executive.Executive;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;

public class ExecCommandsImpl implements ExecCommands {

	private static final String CRLF = DSSGlobals.CRLF;

	private static final int NumExecCommands = 88;

	private String[] ExecCommand;
	private String[] CommandHelp;

	private CommandList CommandList;

	/* Always has last command processed */
	private String LastCmdLine;
	private static String RedirFile;

	public ExecCommandsImpl() {

		this.ExecCommand = new String[NumExecCommands];

		this.ExecCommand[0]  = "New";
		this.ExecCommand[1]  = "Edit";
		this.ExecCommand[2]  = "More";
		this.ExecCommand[3]  = "M";
		this.ExecCommand[4]  = "~";
		this.ExecCommand[5]  = "Select";
		this.ExecCommand[6]  = "Save";
		this.ExecCommand[7]  = "Show";
		this.ExecCommand[8]  = "Solve";
		this.ExecCommand[9]  = "Enable";
		this.ExecCommand[10] = "Disable";
		this.ExecCommand[11] = "Plot";
		this.ExecCommand[12] = "Reset";
		this.ExecCommand[13] = "Compile";
		this.ExecCommand[14] = "Set";   // Set DSS Options
		this.ExecCommand[15] = "Dump";   // Debug dump
		this.ExecCommand[16] = "Open";   // Open a device terminal conductor
		this.ExecCommand[17] = "Close";   // Close a device terminal conductor
		this.ExecCommand[18] = "//";       // Comment
		this.ExecCommand[19] = "Redirect";
		this.ExecCommand[20] = "Help";
		this.ExecCommand[21] = "Quit";
		this.ExecCommand[22] = "?";   // Property Value inquiry
		this.ExecCommand[23] = "Next";
		this.ExecCommand[24] = "Panel";
		this.ExecCommand[25] = "Sample";
		this.ExecCommand[26] = "Clear";
		this.ExecCommand[27] = "About";
		this.ExecCommand[28] = "Calcvoltagebases";  //  Computes voltage bases
		this.ExecCommand[29] = "SetkVBase";  //  Set kV Base at a Bus
		this.ExecCommand[30] = "BuildY";  //  forces Rebuild of Y matrix right now
		this.ExecCommand[31] = "Get";  //  returns values set WITH Set command
		this.ExecCommand[32] = "Init";
		this.ExecCommand[33] = "Export";
		this.ExecCommand[34] = "Fileedit";
		this.ExecCommand[35] = "Voltages";
		this.ExecCommand[36] = "Currents";
		this.ExecCommand[37] = "Powers";
		this.ExecCommand[38] = "Seqvoltages";
		this.ExecCommand[39] = "Seqcurrents";
		this.ExecCommand[40] = "Seqpowers";
		this.ExecCommand[41] = "Losses";
		this.ExecCommand[42] = "Phaselosses";
		this.ExecCommand[43] = "Cktlosses";
		this.ExecCommand[44] = "Allocateloads";
		this.ExecCommand[45] = "Formedit";
		this.ExecCommand[46] = "Totals";  // Total all energymeters
		this.ExecCommand[47] = "Capacity";  // Find upper kW limit of system for present year
		this.ExecCommand[48] = "Classes";  // List of intrinsic classes
		this.ExecCommand[49] = "Userclasses";  // List of user-defined classes
		this.ExecCommand[50] = "Zsc";
		this.ExecCommand[51] = "Zsc10";
		this.ExecCommand[52] = "ZscRefresh";
		this.ExecCommand[53] = "Ysc";
		this.ExecCommand[54] = "puvoltages";
		this.ExecCommand[55] = "VarValues";
		this.ExecCommand[56] = "Varnames";
		this.ExecCommand[57] = "Buscoords";
		this.ExecCommand[58] = "MakeBusList";
		this.ExecCommand[59] = "MakePosSeq";
		this.ExecCommand[60] = "Reduce";
		this.ExecCommand[61] = "Interpolate";
		this.ExecCommand[62] = "AlignFile";
		this.ExecCommand[63] = "TOP";
		this.ExecCommand[64] = "Rotate";
		this.ExecCommand[65] = "Vdiff";
		this.ExecCommand[66] = "Summary";
		this.ExecCommand[67] = "Distribute";
		this.ExecCommand[68] = "DI_plot";
		this.ExecCommand[69] = "Comparecases";
		this.ExecCommand[70] = "YearlyCurves";
		this.ExecCommand[71] = "CD";
		this.ExecCommand[72] = "Visualize";
		this.ExecCommand[73] = "CloseDI";
		this.ExecCommand[74] = "DOScmd";
		this.ExecCommand[75] = "Estimate";
		this.ExecCommand[76] = "Reconductor";
		this.ExecCommand[77] = "_InitSnap";
		this.ExecCommand[78] = "_SolveNoControl";
		this.ExecCommand[79] = "_SampleControls";
		this.ExecCommand[80] = "_DoControlActions";
		this.ExecCommand[81] = "_ShowControlQueue";
		this.ExecCommand[82] = "_SolveDirect";
		this.ExecCommand[83] = "_SolvePFlow";
		this.ExecCommand[84] = "AddMarker";

		this.ExecCommand[85] = "Guids";
		this.ExecCommand[86] = "SetLoadAndGenKV";
		this.ExecCommand[87] = "CvrtLoadshapes";


		this.CommandHelp = new String[NumExecCommands];

		this.CommandHelp[0]  = "Create a new object within the DSS. Object becomes the "+
							"active object" + CRLF +
							"Example: New Line.line1 ...";
		this.CommandHelp[1]  = "Edit an object. The object is selected and it then becomes the active object."+CRLF+CRLF+
							"Note that Edit is the default command.  You many change a property value simply by " +
							"giving the full property name and the new value, for example:"+CRLF+CRLF+
							"line.line1.r1=.04"+CRLF+
							"vsource.source.kvll=230";
		this.CommandHelp[2]  = "Continuation of editing on the active object.";
		this.CommandHelp[3]  = "Continuation of editing on the active object. An abbreviation for More";
		this.CommandHelp[4]  = "Continuation of editing on the active object. An abbreviation."+CRLF+
							CRLF+
							"Example:"+CRLF+
							"New Line.Line1 Bus1=aaa  bus2=bbb"+CRLF+
							"~ R1=.058" +CRLF+
							"~ X1=.1121";
		this.CommandHelp[5]  = "Selects an element and makes it the active element.  You can also specify the " +
							"active terminal (default = 1)."+CRLF+  CRLF+
							"Syntax:"+CRLF+
							"Select [element=]elementname  [terminal=]terminalnumber "+CRLF+CRLF+
							"Example:"+CRLF+
							"Select Line.Line1 "+CRLF+
							"~ R1=.1"+CRLF+"(continue editing)"+CRLF+CRLF+
							"Select Line.Line1 2 " +CRLF+
							"Voltages  (returns voltages at terminal 2 in Result)";
		this.CommandHelp[6]  = "{Save [class=]{Meters | Circuit | Voltages | (classname)} [file=]filename [dir=]directory " + CRLF +
							" Default class = Meters, which saves the present values in both monitors and energy meters in the active circuit. " +
							"\"Save Circuit\" saves the present enabled circuit elements to the specified subdirectory in standard DSS form " +
							"with a Master.txt file and separate files for each class of data. If Dir= not specified a unique name based on the circuit name is created "+
							"automatically.  If Dir= is specified, any existing files are overwritten. " + CRLF +
							"\"Save Voltages\" saves the present solution in a simple CSV format in a file called DSS_SavedVoltages. "+
							"Used for VDIFF command."+CRLF+
							"Any class can be saved to a file.  If no filename specified, the classname is used.";
		this.CommandHelp[7]  = "Writes selected results to a text file and brings "+
							"up the default text editor (see Set Editor=....) with the file for you to browse."+CRLF+  CRLF+
							"See separate help on Show command. "  +CRLF+  CRLF+
							"Default is \"show voltages LN Seq\".  ";
		this.CommandHelp[8]  = "Perform the solution of the present solution mode. You can set any option "+
							"that you can set with the Set command (see Set). "+
							"The Solve command is virtually synonymous with the Set command except that " +
							"a solution is performed after the options are processed.";
		this.CommandHelp[9]  = "Enables a circuit element or entire class.  Example:" +CRLF+
							"Enable load.loadxxx"+CRLF+
							"Enable generator.*  (enables all generators)";
		this.CommandHelp[10] = "Disables a circuit element or entire class.  Example:" +CRLF+
							"Disable load.loadxxx"+CRLF+
							"Disable generator.*  (Disables all generators)"+CRLF+ CRLF+
							"The item remains defined, but is not included in the solution.";
		this.CommandHelp[11] = "Plots circuits and results in a variety of manners.  See separate Plot command help.";
		this.CommandHelp[12] = "{MOnitors | MEters | Faults | Controls | Eventlog | Keeplist |(no argument) } Resets all Monitors, Energymeters, etc. " +
							"If no argument specified, resets all options listed.";
		this.CommandHelp[13] = "Reads the designated file name containing DSS commands " +
							"and processes them as if they were entered directly into the command line. "+
							"The file is said to be \"compiled.\" "  +
							"Similar to \"redirect\" except changes the default directory to the path of the specified file."+CRLF+CRLF+
							"Syntax:"+CRLF+
							"Compile filename";
		this.CommandHelp[14] = "Used to set various DSS solution modes and options.  You may also set the options with the Solve command. "+
							"See \"Options\" for help.";
		this.CommandHelp[15] = "Display the properties of either a specific DSS object or a complete dump "+
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
		this.CommandHelp[16] = "Opens the specified terminal and conductor of the specified circuit element. " +
							"If the conductor is not specified, all phase conductors of the terminal are opened." +CRLF+CRLF+
							"Examples:"+CRLF+
							"Open line.line1 2 "+CRLF+
							"(opens all phases of terminal 2)"+CRLF+CRLF+
							"Open line.line1 2 3" +CRLF+
							"(opens the 3rd conductor of terminal 2)";
		this.CommandHelp[17] = "Opposite of the Open command.";   // Close a device terminal conductor
		this.CommandHelp[18] = "Comment.  Command line is ignored.";       // Comment
		this.CommandHelp[19] = "Reads the designated file name containing DSS commands " +
							"and processes them as if they were entered directly into the command line. "+
							"Similar to \"Compile\", but leaves current directory where it was when Redirect command is invoked." +
							"Can temporarily change to subdirectories if nested Redirect commands require."+CRLF+CRLF+
							"ex:  redirect filename";
		this.CommandHelp[20] = "Gives this display.";
		this.CommandHelp[21] = "Shuts down DSS unless this is the DLL version.  Then it does nothing;  DLL parent is responsible for shutting down the DLL.";
		this.CommandHelp[22] = "Inquiry for property value.  Result is put into GlobalReault and can be seen in the Result Window. "+
							"Specify the full property name."+CRLF+CRLF+
							"Example: ? Line.Line1.R1" + CRLF+CRLF+
							"Note you can set this property merely by saying:"+CRLF+
							"Line.line1.r1=.058";   // Property Value inquiry
		this.CommandHelp[23] = "{Year | Hour | t}  Increments year, hour, or time as specified.  If \"t\" is " +
							"specified, then increments time by current step size.";
		this.CommandHelp[24] = "Displays main control panel window.";
		this.CommandHelp[25] = "Force all monitors and meters to take a sample now.";
		this.CommandHelp[26] = "Clear all circuits currently in memory.";
		this.CommandHelp[27] = "Display \"About Box\".  (Result string set to Version string.)";
		this.CommandHelp[28] = "Calculates voltagebase for buses based on voltage bases defined "+
							"with Set voltagebases=... command.";
		this.CommandHelp[29] = "Command to explicitly set the base voltage for a bus. " +
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
		this.CommandHelp[30] = "Forces rebuild of Y matrix upon next Solve command regardless of need. " +
							"The usual reason for doing this would be to reset the matrix for another " +
							"load level when using LoadModel=PowerFlow (the default) when the system is difficult to " +
							"solve when the load is far from its base value.  Works by invalidating the Y primitive " +
							"matrices for all the Power Conversion elements.";
		this.CommandHelp[31] = "Returns DSS property values set using the Set command. "  +
							"Result is returne in Result property of the Text interface. " +CRLF+CRLF+
							"VBA Example:" +CRLF+CRLF+
							"DSSText.Command = \"Get mode\"" +CRLF   +
							"Answer = DSSText.Result" +CRLF +CRLF +
							"Multiple properties may be requested on one get.  The results are appended "+
							"and the individual values separated by commas." +CRLF+CRLF+
							"See help on Set command for property names.";
		this.CommandHelp[32] = "This command forces reinitialization of the solution for the next Solve command. " +
							"To minimize iterations, most solutions start with the previous solution unless there " +
							"has been a circuit change.  However, if the previous solution is bad, it may be necessary " +
							"to re-initialize.  In most cases, a re-initiallization results in a zero-load power flow " +
							"solution with only the series power delivery elements considered.";
		this.CommandHelp[33] = "Export various solution values to CSV (or XML) files for import into other programs. " +
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
		this.CommandHelp[34] = "Edit specified file in default text file editor (see Set Editor= option)."+CRLF+CRLF+
							"Fileedit EXP_METERS.CSV (brings up the meters export file)" + CRLF+CRLF+
							"\"FileEdit\" may be abbreviated to a unique character string.";
		this.CommandHelp[35] = "Returns the voltages for the ACTIVE BUS in the Result string. " +
							"For setting the active Bus, use the Select command or the Set Bus= option. " +
							"Returned as magnitude and angle quantities, comma separated, one set per conductor of the terminal.";
		this.CommandHelp[36] = "Returns the currents for each conductor of ALL terminals of the active circuit element in the Result string. "+
							"(See Select command.)" +
							"Returned as comma-separated magnitude and angle.";
		this.CommandHelp[37] = "Returns the powers (complex) going into each conductors of ALL terminals of the active circuit element in the Result string. "+
							"(See Select command.)" +
							"Returned as comma-separated kW and kvar.";
		this.CommandHelp[38] = "Returns the sequence voltages at all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values." +
							"Order of returned values: 0, 1, 2  (for each terminal).";
		this.CommandHelp[39] = "Returns the sequence currents into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values." +
							"Order of returned values: 0, 1, 2  (for each terminal).";
		this.CommandHelp[40] = "Returns the sequence powers into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated kw, kvar pairs." +
							"Order of returned values: 0, 1, 2  (for each terminal).";
		this.CommandHelp[41] = "Returns the total losses for the active circuit element (see Select command) " +
							"in the Result string in kW, kvar.";
		this.CommandHelp[42] = "Returns the losses for the active circuit element (see Select command) " +
							"for each PHASE in the Result string in comma-separated kW, kvar pairs.";
		this.CommandHelp[43] = "Returns the total losses for the active circuit in the Result string in kW, kvar.";
		this.CommandHelp[44] = "Estimates the allocation factors for loads that are defined using the XFKVA property. " +
							"Requires that energymeter objects be defined with the PEAKCURRENT property set. " +
							"Loads that are not in the zone of an energymeter cannot be allocated.";
		this.CommandHelp[45] = "FormEdit [class.object].  Brings up form editor on active DSS object.";
		this.CommandHelp[46] = "Totals all EnergyMeter objects in the circuit and reports register totals in the result string.";
		this.CommandHelp[47] = "Find the maximum load the active circuit can serve in the PRESENT YEAR. Uses the EnergyMeter objects with the registers "+
							"set with the SET UEREGS= (..) command for the AutoAdd functions.  Syntax (defaults shown):"+CRLF+CRLF+
							"capacity [start=]0.9 [increment=]0.005" + CRLF + CRLF +
							"Returns the metered kW (load + losses - generation) and per unit load multiplier for the loading level at which something in the system reports an overload or undervoltage. "+
							"If no violations, then it returns the metered kW for peak load for the year (1.0 multiplier). "+
							"Aborts and returns 0 if no energymeters.";
		this.CommandHelp[48] = "List of intrinsic DSS Classes. Returns comma-separated list in Result variable.";
		this.CommandHelp[49] = "List of user-defined DSS Classes. Returns comma-separated list in Result variable.";
		this.CommandHelp[50] = "Returns full Zsc matrix for the ACTIVE BUS in comma-separated complex number form.";
		this.CommandHelp[51] = "Returns symmetrical component impedances, Z1, Z0 for the ACTIVE BUS in comma-separated R+jX form.";
		this.CommandHelp[52] = "Refreshes Zsc matrix for the ACTIVE BUS.";
		this.CommandHelp[53] = "Returns full Ysc matrix for the ACTIVE BUS in comma-separated complex number form G + jB.";
		this.CommandHelp[54] = "Just like the Voltages command, except the voltages are in per unit if the kVbase at the bus is defined.";
		this.CommandHelp[55] = "Returns variable values for active element if PC element. Otherwise, returns null.";
		this.CommandHelp[56] = "Returns variable names for active element if PC element. Otherwise, returns null.";
		this.CommandHelp[57] = "Define x,y coordinates for buses.  Execute after Solve command performed so that bus lists are defined." +
							"Reads coordinates from a CSV file with records of the form: busname, x, y."+CRLF+CRLF+
							"Example: BusCoords [file=]xxxx.csv";
		this.CommandHelp[58] = "Updates the buslist using the currently enabled circuit elements.  (This happens automatically for Solve command.)";
		this.CommandHelp[59] = "Attempts to convert present circuit model to a positive sequence equivalent. " +
							"It is recommended to Save the circuit after this and edit the saved version to correct possible misinterpretations.";
		this.CommandHelp[60] = "{All | MeterName}  Default is \"All\".  Reduce the circuit according to reduction options. " +
							"See \"Set ReduceOptions\" and \"Set Keeplist\" options." +
							"Energymeter objects actually perform the reduction.  \"All\" causes all meters to reduce their zones.";
		this.CommandHelp[61] = "{All | MeterName}  Default is \"All\". Interpolates coordinates for missing bus coordinates in meter zone";
		this.CommandHelp[62] = "Alignfile [file=]filename.  Aligns DSS script files in columns for easier reading.";
		this.CommandHelp[63] = "[class=]{Loadshape | Monitor  } [object=]{ALL (Loadshapes only) | objectname}. " +
							"Send specified object to TOP.  Loadshapes must be hourly fixed interval. ";
		this.CommandHelp[64] = "Usage: Rotate [angle=]nnn.  Rotate circuit plotting coordinates by specified angle (degrees). ";
		this.CommandHelp[65] = "Displays the difference between the present solution and the last on saved using the SAVE VOLTAGES command.";
		this.CommandHelp[66] = "Returns a power flow summary of the most recent solution in the global result string.";
		this.CommandHelp[67] = "kw=nn how={Proportional | Uniform |Random | Skip} skip=nn PF=nn file=filename MW=nn" +CRLF +
							"Distributes generators on the system in the manner specified by \"how\"." + CRLF +
							"kW = total generation to be distributed (default=1000) "+ CRLF +
							"how= process name as indicated (default=proportional to load)" + CRLF +
							"skip = no. of buses to skip for \"How=Skip\" (default=1)" + CRLF +
							"PF = power factor for new generators (default=1.0)"+ CRLF +
							"file = name of file to save (default=distgenerators.txt)"+ CRLF +
							"MW = alternate way to specify kW (default = 1)";
		this.CommandHelp[68] = "[case=]casename [year=]yr [registers=](reg1, reg2,...)  [peak=]y/n  [meter=]metername" +CRLF+
							"Plots demand interval (DI) results from yearly simulation cases.  "+
							"Plots selected registers from selected meter file (default = DI_Totals.CSV).  " +
							"Peak defaults to NO.  If YES, only daily peak of specified registers "+
							"is plotted. Example:"+CRLF+CRLF+
							" DI_Plot basecase year=5 registers=(9,11) no";
		this.CommandHelp[69] = "[Case1=]casename [case2=]casename [register=](register number) [meter=]{Totals* | SystemMeter | metername}. "+CRLF+
							"Compares yearly simulations of two specified cases with respect to the quantity in the designated register "+
							"from the designated meter file. "+
							"Defaults: Register=9 meter=Totals.  Example:"+CRLF+CRLF+
							"Comparecases base pvgens 10";
		this.CommandHelp[70] = "[cases=](case1, case2, ...) [registers=](reg1, reg2, ...)  [meter=]{Totals* | SystemMeter | metername}"+
							"Plots yearly curves for specified cases and registers. "+CRLF+
							"Default: meter=Totals. Example: "+CRLF+CRLF+
							"yearlycurves cases=(basecase, pvgens) registers=9";
		this.CommandHelp[71] = "Change default directory to specified directory" + CRLF +CRLF +
							"CD dirname";
		this.CommandHelp[72] = "[What=] one of {Currents* | Voltages | Powers} [element=]full_element_name  (class.name). " +
							"Shows the selected quantity for selected element on a multiphase line drawing in phasor values.";
		this.CommandHelp[73] = "Close all DI files ... useful at end of yearly solution where DI files are left open. " +
							"(Reset and Set Year=nnn will also close the DI files)";
		this.CommandHelp[74] = "Do a DOS command. Sends the command \"cmd ... \" to Windows. Execute the \"cmd /?\" command "+
							"in a DOS window to see the options. To do a DOS command and automatically exit, do " + CRLF+CRLF+
							"DOScmd /c ...command string ..."+CRLF+CRLF+
							"To keep the DOS window open, use /k switch.";
		this.CommandHelp[75] = "Execute state estimator on present circuit given present sensor values.";
		this.CommandHelp[76] = "Reconductor a line section. Must be in an EnergyMeter zone. " + CRLF +
							"Syntax: Reconductor Line1=... Line2=... [LineCode= | Geometry = ] " +CRLF+
							"Line1 and Line2 may be given in any order. All lines in the path between the two are redefined " +
							"with either the LineCode or Geometry.";
		this.CommandHelp[77] = "For step control of solution process: Intialize iteration counters, etc. that normally occurs at the " +
							"start of a snapshot solution process.";
		this.CommandHelp[78] = "For step control of solution process: Solves the circuit in present state but does not check for control actions.";
		this.CommandHelp[79] = "For step control of solution process: Sample the control elements, which push control action requests onto the control queue.";
		this.CommandHelp[80] = "For step control of solution process: Pops control actions off the control queue according to the present control mode rules. " +
							"Dispatches contol actions to proper control element \"DoPendingAction\" handlers.";
		this.CommandHelp[81] = "For step control of solution process: Show the present control queue contents.";
		this.CommandHelp[82] = "For step control of solution process: Invoke direct solution function in DSS. Non-iterative solution of Y matrix and active sources only.";
		this.CommandHelp[83] = "For step control of solution process: Invoke iterative power flow solution function of DSS directly.";
		this.CommandHelp[84] = "Add a marker to the active plot. Example: "+CRLF+CRLF+"AddMarker Bus=busname code=nn color=$00FF0000 size=3";
		this.CommandHelp[85] = "Read GUIDS for class names. Tab or comma-delimited file with full object name and GUID";
		this.CommandHelp[86] = "Set load and generator object kv from bus voltage base and connection type.";
		this.CommandHelp[87] = "Convert all Loadshapes presently loaded into either files of single or files of double. "+
							"Usually files of singles are adequate precision for loadshapes.  Syntax:"+CRLF+CRLF+
							"cvrtloadshapes type=sng  (this is the default)"+CRLF+
							"cvrtloadshapes type=dbl"+CRLF+CRLF+
							"A DSS script for loading the loadshapes from the created files is produced and displayed in the default editor. ";

	}

	public static String getRedirFile() {
		return RedirFile;
	}

	public static void setRedirFile(String redirFile) {
		RedirFile = redirFile;
	}

	public void processCommand(String CmdLine) {
		int ParamPointer;
		String ParamName;
		String Param;
		String ObjName = "", PropName = "";
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser Parser = com.epri.dss.parser.impl.Parser.getInstance();

		try {
			Globals.setCmdResult(0);
			Globals.setErrorNumber(0);  // Reset Error number
			Globals.setGlobalResult("");

			/* Load up the parser and process the first parameter only */
			LastCmdLine = CmdLine;
			Parser.setCmdString(LastCmdLine);  // Load up command parser
			Globals.setLastCommandWasCompile(false);
			
			ParamPointer = 0;
			ParamName = Parser.getNextParam();
			Param = Parser.makeString();
			if (Param.length() == 0) return;  // Skip blank line

			// Check for Command verb or Property Value
			// Commands do not have equal signs so ParamName must be zero
			if (ParamName.length() == 0)
				ParamPointer = CommandList.getCommand(Param);

			// Check first for Compile or Redirect and get outta here
			switch (ParamPointer) {
			case 14:
				if (Executive.DSSExecutive.isRecorderOn())
					Executive.DSSExecutive.writeToRecorderFile(CRLF+"!*********"+CmdLine);
				Globals.setCmdResult(ExecHelper.doRedirect(true));
				return;
			case 20:
				if (Executive.DSSExecutive.isRecorderOn())
					Executive.DSSExecutive.writeToRecorderFile(CRLF+"!*********"+CmdLine);
				Globals.setCmdResult(ExecHelper.doRedirect(false));
				return;
			default:  // Write everything direct to recorder, if ON
				if (Executive.DSSExecutive.isRecorderOn())
					Executive.DSSExecutive.writeToRecorderFile(CmdLine);
			}

			// Things that are OK to do before a circuit is defined
			switch (ParamPointer) {
			case 1:
				Globals.setCmdResult(ExecHelper.doNewCmd());  // new
			case 15:
				if (Globals.getActiveCircuit() == null) {
					ExecOptionsImpl.doSetCmd_NoCircuit();  // can only call this if no circuit active
					return;  // We exit with either a good outcome or bad
				}
			case 19:
				// Do Nothing - comment
			case 21:
				Globals.setCmdResult(ExecHelper.doHelpCmd());
			case 22:
				if (!Globals.isDLL()) 
					DSSForms.exitControlPanel();  // Quit in Stand alone version
			case 25:
				DSSForms.showControlPanel();
			case 27:
				ExecHelper.doClearCmd();
			case 28:
				ExecHelper.doAboutBox();
			case 35:
				Globals.setCmdResult(ExecHelper.doFileEditCmd());
			case 49:
				Globals.setCmdResult(ExecHelper.doClassesCmd());
			case 50:
				Globals.setCmdResult(ExecHelper.doUserClassesCmd());
			case 63:
				Globals.setCmdResult(ExecHelper.doAlignFileCmd());
			case 69:
				Globals.setCmdResult(ExecHelper.doDI_PlotCmd());
			case 70:
				Globals.setCmdResult(ExecHelper.doCompareCasesCmd());
			case 71:
				Globals.setCmdResult(ExecHelper.doYearlyCurvesCmd());
			case 72:
				ParamName = Parser.getNextParam();
				Param = Parser.makeString();
//				if (setCurrentDir(Param)) {  FIXME: Change cwd.
//					Globals.setCmdResult(0);
//					Globals.setDataPath(Param);  // Change DSSdataDirectory
//				} else {
//					Globals.doSimpleMsg("Directory \""+Param+"\" not found.", 282);
//				}
			case 75:
				ExecHelper.doADOScmd();
			case 88:
				ExecHelper.doCvrtLoadshapesCmd();
			default:
				if (Globals.getActiveCircuit() == null) 
					Globals.doSimpleMsg("You must create a new circuit object first: \"new circuit.mycktname\" to execute this command.", 301);
			}

			// Now check to see if this is a command or a property reference

			if (ParamPointer == 0) {
				/* If not a command or the command is unknown, then it could be a property of a circuit element */

				/* If a command or no text before the = sign, then error */
				if ((ParamName.length() == 0) || ParamName.equals("command")) {
					Globals.doSimpleMsg("Unknown Command: \"" + Param + "\" "+ CRLF + Parser.getCmdString(), 302);
					Globals.setCmdResult(1);
				} else {
					ExecHelper.parseObjName(ParamName, ObjName, PropName);  // TODO: Check ObjName and PropName get set.
					if (ObjName.length() > 0)
						Globals.setObject(ObjName);  // Set active element
					if (Globals.getActiveDSSObject() != null) {
						// rebuild command line and pass to editor
						// use quotes to ensure first parameter is interpreted OK after rebuild
						Parser.setCmdString(PropName + "=\"" + Param + "\" " + Parser.getRemainder());
						Globals.getActiveDSSClass().edit();
					}
				}
				return;  // Done - don't need to do anything else
			}

			// Process the rest of the commands
			switch (ParamPointer) {
			case 2:
				Globals.setCmdResult(ExecHelper.doEditCmd());  // edit
			case 3:
				Globals.setCmdResult(ExecHelper.doMoreCmd()); // more
			case 4:
				Globals.setCmdResult(ExecHelper.doMoreCmd()); // m
			case 5:
				Globals.setCmdResult(ExecHelper.doMoreCmd()); // ~
			case 6:
				Globals.setCmdResult(ExecHelper.doSelectCmd());
			case 7:
				Globals.setCmdResult(ExecHelper.doSaveCmd()); // save
			case 8:
				Globals.setCmdResult(ShowOptionsImpl.doShowCmd()); // show
			case 9:
				Globals.setCmdResult(ExecOptionsImpl.doSetCmd(1)); // solve
			case 10:
				Globals.setCmdResult(ExecHelper.doEnableCmd());
			case 11:
				Globals.setCmdResult(ExecHelper.doDisableCmd());
			case 12:
				Globals.setCmdResult(PlotOptionsImpl.doPlotCmd());
			case 13:
				Globals.setCmdResult(ExecHelper.doResetCmd());
			case 15:
				Globals.setCmdResult(ExecOptionsImpl.doSetCmd(0));  // set with no solve
			case 16:
				Globals.setCmdResult(ExecHelper.doPropertyDump());
			case 18:
				Globals.setCmdResult(ExecHelper.doCloseCmd());
				
			case 23:
				Globals.setCmdResult(ExecHelper.doQueryCmd());
			case 24:
				Globals.setCmdResult(ExecHelper.doNextCmd());
//			case 25:
//				DSSForms.showControlPanel()
			case 26:
				Globals.setCmdResult(ExecHelper.doSampleCmd());
//			case 27:
//				clearAllCircuits();
//				disposeDSSClasses();
//				createDSSClasses();
//			case 28:
//				doAboutBox();
			case 29:
				Globals.setCmdResult(ExecHelper.doSetVoltageBases());
			case 30:
				Globals.setCmdResult(ExecHelper.doSetkVBase());
			case 31:
				// Force rebuilding of Y
				Globals.getActiveCircuit().invalidateAllPCElements();
			case 32:
				Globals.setCmdResult(ExecOptionsImpl.doGetCmd());
			case 33:
				Globals.getActiveCircuit().getSolution().setSolutionInitialized(false);
			case 34:
				Globals.setCmdResult(ExportOptionsImpl.doExportCmd());
//			case 35:
//				Globals.setCmdResult(ExecHelper.doFileEditCmd());
			case 36:
				Globals.setCmdResult(ExecHelper.doVoltagesCmd(false));
			case 37:
				Globals.setCmdResult(ExecHelper.doCurrentsCmd());
			case 38:
				Globals.setCmdResult(ExecHelper.doPowersCmd());
			case 39:
				Globals.setCmdResult(ExecHelper.doSeqVoltagesCmd());
			case 40:
				Globals.setCmdResult(ExecHelper.doSeqCurrentsCmd());
			case 41:
				Globals.setCmdResult(ExecHelper.doSeqPowersCmd());
			case 43:
				Globals.setCmdResult(ExecHelper.doLossesCmd());
			case 44:
				Globals.setCmdResult(ExecHelper.doCktLossesCmd());
			case 45:
				Globals.setCmdResult(ExecHelper.doAllocateLoadsCmd());
			case 46:
				Globals.setCmdResult(ExecHelper.doFormEditCmd());
			case 47:
				Globals.setCmdResult(ExecHelper.doMeterTotals());
			case 48:
				Globals.setCmdResult(ExecHelper.doCapacityCmd());
//			case 49:
//				Globals.setCmdResult(ExecHelper.doClassesCmd());
//			case 50:
//				Globals.setCmdResult(ExecHelper.doUserClassesCmd();
			case 51:
				Globals.setCmdResult(ExecHelper.doZscCmd(true));
			case 52:
				Globals.setCmdResult(ExecHelper.doZsc10Cmd());
			case 53:
				Globals.setCmdResult(ExecHelper.doZscRefresh());
			case 54:
				Globals.setCmdResult(ExecHelper.doZscCmd(false));
			case 55:
				Globals.setCmdResult(ExecHelper.doVoltagesCmd(true));
			case 56:
				Globals.setCmdResult(ExecHelper.doVarValuesCmd());
			case 57:
				Globals.setCmdResult(ExecHelper.doVarNamesCmd());
			case 58:
				Globals.setCmdResult(ExecHelper.doBusCoordsCmd());
			case 59:
				if (Globals.getActiveCircuit().isBusNameRedefined())
					Globals.getActiveCircuit().reProcessBusDefs();
			case 60:
				Globals.setCmdResult(ExecHelper.doMakePosSeq());
			case 61:
				Globals.setCmdResult(ExecHelper.doReduceCmd());
			case 62:
				Globals.setCmdResult(ExecHelper.doInterpolateCmd());
			case 64:
				Globals.setCmdResult(ExecHelper.doTOPCmd());
			case 65:
				Globals.setCmdResult(ExecHelper.doRotateCmd());
			case 66:
				Globals.setCmdResult(ExecHelper.doVDiffCmd());
			case 67:
				Globals.setCmdResult(ExecHelper.doSummaryCmd());
			case 68:
				Globals.setCmdResult(ExecHelper.doDistributeCmd());
			case 69:
			case 70:
			case 71:
			case 72:
			case 73:
				Globals.setCmdResult(ExecHelper.doVisualizeCmd());
			case 74:
				Globals.setCmdResult(ExecHelper.doCloseDICmd());
			case 76:
				Globals.setCmdResult(ExecHelper.doEstimateCmd());
			case 77:
				Globals.setCmdResult(ExecHelper.doReconductorCmd());
			/* Step solution commands */
			case 78:
				Globals.getActiveCircuit().getSolution().snapShotInit();
			case 79:
				Globals.getActiveCircuit().getSolution().solveCircuit();
			case 80:
				Globals.getActiveCircuit().getSolution().sampleControlDevices();
			case 81:
				Globals.getActiveCircuit().getSolution().doControlActions();
			case 82:
				Globals.getActiveCircuit().getControlQueue().showQueue(Globals.getDSSDirectory() + Globals.getCircuitName_() + "ControlQueue.csv");
			case 83:
				Globals.getActiveCircuit().getSolution().solveDirect();
			case 84:
				Globals.getActiveCircuit().getSolution().doPFLOWsolution();
			case 85:
				Globals.setCmdResult(ExecHelper.doAddMarkerCmd());
			case 86:
				Globals.setCmdResult(ExecHelper.doGuidsCmd());
			case 87:
				Globals.setCmdResult(ExecHelper.doSetLoadAndGenKVCmd());
			case 88:
			case 89:
				Globals.setCmdResult(ExecHelper.doNodeDiffCmd());
			case 90:
				Globals.setCmdResult(ExecHelper.doRephaseCmd());
			case 91:
				Globals.setCmdResult(ExecHelper.doSetBusXYCmd());
			default:
				// Ignore excess parameters
			}
		} catch (Exception e) {
			Globals.doErrorMsg(("ProcessCommand"+CRLF+"Exception Raised WHILE Processing DSS Command:"+ CRLF + Parser.getCmdString()),
					e.getMessage(),
					"Error in command string or circuit data.", 303);
		}
	}

}
