from __pyjamas__ import (ARGERROR,)
from dss.executive.impl.ExportOptions import (ExportOptions,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.executive.impl.ShowOptions import (ShowOptions,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.executive.impl.DSSExecutive import (DSSExecutive,)
from dss.executive.impl.ExecOptions import (ExecOptions,)
from dss.executive.impl.PlotOptions import (PlotOptions,)
from dss.executive.impl.ExecHelper import (ExecHelper,)


class ExecCommands(object):
    NumExecCommands = 93
    execCommand = [None] * NumExecCommands
    commandHelp = [None] * NumExecCommands
    commandList = None
    # Always has last command processed
    lastCmdLine = None
    redirFile = None

    def __init__(self):
        """Private constructor prevents instantiation from other classes."""
        self.defineCommands()
        self.commandList = CommandListImpl(self.execCommand)
        self.commandList.setAbbrevAllowed(True)

    class ExecCommandsHolder(object):
        """ExecCommandsHolder is loaded on the first execution of
        ExecCommands.getInstance() or the first access to
        ExecCommandsHolder.INSTANCE, not before.
        """
        INSTANCE = ExecCommands()

    @classmethod
    def getInstance(cls):
        return cls.ExecCommandsHolder.INSTANCE

    def defineCommands(self):
        CRLF = DSSGlobals.CRLF
        self.execCommand = [None] * self.NumExecCommands
        self.execCommand[0] = 'New'
        self.execCommand[1] = 'Edit'
        self.execCommand[2] = 'More'
        self.execCommand[3] = 'M'
        self.execCommand[4] = '~'
        self.execCommand[5] = 'Select'
        self.execCommand[6] = 'Save'
        self.execCommand[7] = 'Show'
        self.execCommand[8] = 'Solve'
        self.execCommand[9] = 'Enable'
        self.execCommand[10] = 'Disable'
        self.execCommand[11] = 'Plot'
        self.execCommand[12] = 'Reset'
        self.execCommand[13] = 'Compile'
        self.execCommand[14] = 'Set'
        # set DSS options
        self.execCommand[15] = 'Dump'
        # debug dump
        self.execCommand[16] = 'Open'
        # open a device terminal conductor
        self.execCommand[17] = 'Close'
        # close a device terminal conductor
        self.execCommand[18] = '//'
        # comment
        self.execCommand[19] = 'Redirect'
        self.execCommand[20] = 'Help'
        self.execCommand[21] = 'Quit'
        self.execCommand[22] = '?'
        # property value inquiry
        self.execCommand[23] = 'Next'
        self.execCommand[24] = 'Panel'
        self.execCommand[25] = 'Sample'
        self.execCommand[26] = 'Clear'
        self.execCommand[27] = 'About'
        self.execCommand[28] = 'Calcvoltagebases'
        # computes voltage bases
        self.execCommand[29] = 'SetkVBase'
        # set kV base at a bus
        self.execCommand[30] = 'BuildY'
        # forces rebuild of Y matrix right now
        self.execCommand[31] = 'Get'
        # returns values set with set command
        self.execCommand[32] = 'Init'
        self.execCommand[33] = 'Export'
        self.execCommand[34] = 'Fileedit'
        self.execCommand[35] = 'Voltages'
        self.execCommand[36] = 'Currents'
        self.execCommand[37] = 'Powers'
        self.execCommand[38] = 'Seqvoltages'
        self.execCommand[39] = 'Seqcurrents'
        self.execCommand[40] = 'Seqpowers'
        self.execCommand[41] = 'Losses'
        self.execCommand[42] = 'Phaselosses'
        self.execCommand[43] = 'Cktlosses'
        self.execCommand[44] = 'Allocateloads'
        self.execCommand[45] = 'Formedit'
        self.execCommand[46] = 'Totals'
        # total all energyMeters
        self.execCommand[47] = 'Capacity'
        # find upper kW limit of system for present year
        self.execCommand[48] = 'Classes'
        # list of intrinsic classes
        self.execCommand[49] = 'Userclasses'
        # list of user-defined classes
        self.execCommand[50] = 'Zsc'
        self.execCommand[51] = 'Zsc10'
        self.execCommand[52] = 'ZscRefresh'
        self.execCommand[53] = 'Ysc'
        self.execCommand[54] = 'puvoltages'
        self.execCommand[55] = 'VarValues'
        self.execCommand[56] = 'Varnames'
        self.execCommand[57] = 'Buscoords'
        self.execCommand[58] = 'MakeBusList'
        self.execCommand[59] = 'MakePosSeq'
        self.execCommand[60] = 'Reduce'
        self.execCommand[61] = 'Interpolate'
        self.execCommand[62] = 'AlignFile'
        self.execCommand[63] = 'TOP'
        self.execCommand[64] = 'Rotate'
        self.execCommand[65] = 'Vdiff'
        self.execCommand[66] = 'Summary'
        self.execCommand[67] = 'Distribute'
        self.execCommand[68] = 'DI_plot'
        self.execCommand[69] = 'Comparecases'
        self.execCommand[70] = 'YearlyCurves'
        self.execCommand[71] = 'CD'
        self.execCommand[72] = 'Visualize'
        self.execCommand[73] = 'CloseDI'
        self.execCommand[74] = 'DOScmd'
        self.execCommand[75] = 'Estimate'
        self.execCommand[76] = 'Reconductor'
        self.execCommand[77] = '_InitSnap'
        self.execCommand[78] = '_SolveNoControl'
        self.execCommand[79] = '_SampleControls'
        self.execCommand[80] = '_DoControlActions'
        self.execCommand[81] = '_ShowControlQueue'
        self.execCommand[82] = '_SolveDirect'
        self.execCommand[83] = '_SolvePFlow'
        self.execCommand[84] = 'AddMarker'
        self.execCommand[85] = 'Guids'
        self.execCommand[86] = 'SetLoadAndGenKV'
        self.execCommand[87] = 'CvrtLoadshapes'
        self.execCommand[88] = 'NodeDiff'
        self.execCommand[89] = 'Rephase'
        self.execCommand[90] = 'SetBusXY'
        self.execCommand[91] = 'UpdateStorage'
        self.execCommand[92] = 'Obfuscate'
        self.commandHelp = [None] * self.NumExecCommands
        self.commandHelp[0] = 'Create a new object within the DSS. Object becomes the ' + 'active object' + CRLF + 'Example: New Line.line1 ...'
        self.commandHelp[1] = 'Edit an object. The object is selected and it then becomes the active object.' + CRLF + CRLF + 'Note that Edit is the default command.  You many change a property value simply by ' + 'giving the full property name and the new value, for example:' + CRLF + CRLF + 'line.line1.r1=.04' + CRLF + 'vsource.source.kvll=230'
        self.commandHelp[2] = 'Continuation of editing on the active object.'
        self.commandHelp[3] = 'Continuation of editing on the active object. An abbreviation for More'
        self.commandHelp[4] = 'Continuation of editing on the active object. An abbreviation.' + CRLF + CRLF + 'Example:' + CRLF + 'New Line.Line1 Bus1=aaa  bus2=bbb' + CRLF + '~ R1=.058' + CRLF + '~ X1=.1121'
        self.commandHelp[5] = 'Selects an element and makes it the active element.  You can also specify the ' + 'active terminal (default = 1).' + CRLF + CRLF + 'Syntax:' + CRLF + 'Select [element=]elementname  [terminal=]terminalnumber ' + CRLF + CRLF + 'Example:' + CRLF + 'Select Line.Line1 ' + CRLF + '~ R1=.1' + CRLF + '(continue editing)' + CRLF + CRLF + 'Select Line.Line1 2 ' + CRLF + 'Voltages  (returns voltages at terminal 2 in Result)'
        self.commandHelp[6] = '{Save [class=]{Meters | Circuit | Voltages | (classname)} [file=]filename [dir=]directory ' + CRLF + ' Default class = Meters, which saves the present values in both monitors and energy meters in the active circuit. ' + '\"Save Circuit\" saves the present enabled circuit elements to the specified subdirectory in standard DSS form ' + 'with a Master.txt file and separate files for each class of data. If Dir= not specified a unique name based on the circuit name is created ' + 'automatically.  If Dir= is specified, any existing files are overwritten. ' + CRLF + '\"Save Voltages\" saves the present solution in a simple CSV format in a file called DSS_SavedVoltages. ' + 'Used for VDIFF command.' + CRLF + 'Any class can be saved to a file.  If no filename specified, the classname is used.'
        self.commandHelp[7] = 'Writes selected results to a text file and brings ' + 'up the default text editor (see Set Editor=....) with the file for you to browse.' + CRLF + CRLF + 'See separate help on Show command. ' + CRLF + CRLF + 'Default is \"show voltages LN Seq\".  '
        self.commandHelp[8] = 'Perform the solution of the present solution mode. You can set any option ' + 'that you can set with the Set command (see Set). ' + 'The Solve command is virtually synonymous with the Set command except that ' + 'a solution is performed after the options are processed.'
        self.commandHelp[9] = 'Enables a circuit element or entire class.  Example:' + CRLF + 'Enable load.loadxxx' + CRLF + 'Enable generator.*  (enables all generators)'
        self.commandHelp[10] = 'Disables a circuit element or entire class.  Example:' + CRLF + 'Disable load.loadxxx' + CRLF + 'Disable generator.*  (Disables all generators)' + CRLF + CRLF + 'The item remains defined, but is not included in the solution.'
        self.commandHelp[11] = 'Plots circuits and results in a variety of manners.  See separate Plot command help.'
        self.commandHelp[12] = '{MOnitors | MEters | Faults | Controls | Eventlog | Keeplist |(no argument) } Resets all Monitors, Energymeters, etc. ' + 'If no argument specified, resets all options listed.'
        self.commandHelp[13] = 'Reads the designated file name containing DSS commands ' + 'and processes them as if they were entered directly into the command line. ' + 'The file is said to be \"compiled.\" ' + 'Similar to \"redirect\" except changes the default directory to the path of the specified file.' + CRLF + CRLF + 'Syntax:' + CRLF + 'Compile filename'
        self.commandHelp[14] = 'Used to set various DSS solution modes and options.  You may also set the options with the Solve command. ' + 'See \"Options\" for help.'
        self.commandHelp[15] = 'Display the properties of either a specific DSS object or a complete dump ' + 'on all variables in the problem (Warning! Could be very large!).' + ' Brings up the default text editor with the text file written by this command.' + CRLF + ' Syntax: dump [class.obj] [debug]' + CRLF + ' Examples:' + CRLF + CRLF + ' Dump line.line1 ' + CRLF + ' Dump solution  (dumps all solution vars) ' + CRLF + ' Dump commands  (dumps all commands to a text file) ' + CRLF + ' Dump transformer.*  (dumps all transformers)' + CRLF + ' Dump ALLOCationfactors  (load allocation factors)' + CRLF + ' Dump Buslist    (bus name hash list)' + CRLF + ' Dump Devicelist    (Device name hash list)' + CRLF + ' Dump      (dumps all objects in circuit) '
        # " Dump debug";   // Debug dump
        self.commandHelp[16] = 'Opens the specified terminal and conductor of the specified circuit element. ' + 'If the conductor is not specified, all phase conductors of the terminal are opened.' + CRLF + CRLF + 'Examples:' + CRLF + 'Open line.line1 2 ' + CRLF + '(opens all phases of terminal 2)' + CRLF + CRLF + 'Open line.line1 2 3' + CRLF + '(opens the 3rd conductor of terminal 2)'
        self.commandHelp[17] = 'Opposite of the Open command.'
        # Close a device terminal conductor
        self.commandHelp[18] = 'Comment.  Command line is ignored.'
        # Comment
        self.commandHelp[19] = 'Reads the designated file name containing DSS commands ' + 'and processes them as if they were entered directly into the command line. ' + 'Similar to \"Compile\", but leaves current directory where it was when Redirect command is invoked.' + 'Can temporarily change to subdirectories if nested Redirect commands require.' + CRLF + CRLF + 'ex:  redirect filename'
        self.commandHelp[20] = 'Gives this display.'
        self.commandHelp[21] = 'Shuts down DSS unless this is the DLL version.  Then it does nothing;  DLL parent is responsible for shutting down the DLL.'
        self.commandHelp[22] = 'Inquiry for property value.  Result is put into GlobalReault and can be seen in the Result Window. ' + 'Specify the full property name.' + CRLF + CRLF + 'Example: ? Line.Line1.R1' + CRLF + CRLF + 'Note you can set this property merely by saying:' + CRLF + 'Line.line1.r1=.058'
        # Property Value inquiry
        self.commandHelp[23] = '{Year | Hour | t}  Increments year, hour, or time as specified.  If \"t\" is ' + 'specified, then increments time by current step size.'
        self.commandHelp[24] = 'Displays main control panel window.'
        self.commandHelp[25] = 'Force all monitors and meters to take a sample now.'
        self.commandHelp[26] = 'Clear all circuits currently in memory.'
        self.commandHelp[27] = 'Display \"About Box\".  (Result string set to Version string.)'
        self.commandHelp[28] = 'Calculates voltagebase for buses based on voltage bases defined ' + 'with Set voltagebases=... command.'
        self.commandHelp[29] = 'Command to explicitly set the base voltage for a bus. ' + 'Bus must be previously defined. Parameters in order are:' + CRLF + 'Bus = {bus name}' + CRLF + 'kVLL = (line-to-line base kV)' + CRLF + 'kVLN = (line-to-neutral base kV)' + CRLF + CRLF + 'kV base is normally given in line-to-line kV (phase-phase). ' + 'However, it may also be specified by line-to-neutral kV.' + CRLF + 'The following exampes are equivalent:' + CRLF + CRLF + 'setkvbase Bus=B9654 kVLL=13.2' + CRLF + 'setkvbase B9654 13.2' + CRLF + 'setkvbase B9654 kvln=7.62'
        self.commandHelp[30] = 'Forces rebuild of Y matrix upon next Solve command regardless of need. ' + 'The usual reason for doing this would be to reset the matrix for another ' + 'load level when using LoadModel=PowerFlow (the default) when the system is difficult to ' + 'solve when the load is far from its base value.  Works by invalidating the Y primitive ' + 'matrices for all the Power Conversion elements.'
        self.commandHelp[31] = 'Returns DSS property values set using the Set command. ' + 'Result is returne in Result property of the Text interface. ' + CRLF + CRLF + 'VBA Example:' + CRLF + CRLF + 'DSSText.Command = \"Get mode\"' + CRLF + 'Answer = DSSText.Result' + CRLF + CRLF + 'Multiple properties may be requested on one get.  The results are appended ' + 'and the individual values separated by commas.' + CRLF + CRLF + 'See help on Set command for property names.'
        self.commandHelp[32] = 'This command forces reinitialization of the solution for the next Solve command. ' + 'To minimize iterations, most solutions start with the previous solution unless there ' + 'has been a circuit change.  However, if the previous solution is bad, it may be necessary ' + 'to re-initialize.  In most cases, a re-initiallization results in a zero-load power flow ' + 'solution with only the series power delivery elements considered.'
        self.commandHelp[33] = 'Export various solution values to CSV (or XML) files for import into other programs. ' + 'Creates a new file except for Energymeter and Generator objects, for which ' + 'the results for each device of this class are APPENDED to the CSV File. You may export to ' + 'a specific file by specifying the file name as the LAST parameter on the line. For example:' + CRLF + CRLF + '  Export Voltage Myvoltagefile.CSV' + CRLF + CRLF + 'Otherwise, the default file names shown in the Export help are used. ' + 'For Energymeter and Generator, specifying the switch \"/multiple\" (or /m) for the file name will cause ' + 'a separate file to be written for each meter or generator. ' + 'The default is for a single file containing all elements.' + CRLF + CRLF + 'May be abreviated Export V, Export C, etc.  Default is \"V\" for voltages.' + ' If Set ShowExport=Yes, the output file will be automatically displayed in the default editor. ' + 'Otherwise, you must open the file separately. The name appears in the Result window.'
        self.commandHelp[34] = 'Edit specified file in default text file editor (see Set Editor= option).' + CRLF + CRLF + 'Fileedit EXP_METERS.CSV (brings up the meters export file)' + CRLF + CRLF + '\"FileEdit\" may be abbreviated to a unique character string.'
        self.commandHelp[35] = 'Returns the voltages for the ACTIVE BUS in the Result string. ' + 'For setting the active Bus, use the Select command or the Set Bus= option. ' + 'Returned as magnitude and angle quantities, comma separated, one set per conductor of the terminal.'
        self.commandHelp[36] = 'Returns the currents for each conductor of ALL terminals of the active circuit element in the Result string. ' + '(See Select command.)' + 'Returned as comma-separated magnitude and angle.'
        self.commandHelp[37] = 'Returns the powers (complex) going into each conductors of ALL terminals of the active circuit element in the Result string. ' + '(See Select command.)' + 'Returned as comma-separated kW and kvar.'
        self.commandHelp[38] = 'Returns the sequence voltages at all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values.' + 'Order of returned values: 0, 1, 2  (for each terminal).'
        self.commandHelp[39] = 'Returns the sequence currents into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated magnitude only values.' + 'Order of returned values: 0, 1, 2  (for each terminal).'
        self.commandHelp[40] = 'Returns the sequence powers into all terminals of the active circuit element (see Select command) in Result string.  Returned as comma-separated kw, kvar pairs.' + 'Order of returned values: 0, 1, 2  (for each terminal).'
        self.commandHelp[41] = 'Returns the total losses for the active circuit element (see Select command) ' + 'in the Result string in kW, kvar.'
        self.commandHelp[42] = 'Returns the losses for the active circuit element (see Select command) ' + 'for each PHASE in the Result string in comma-separated kW, kvar pairs.'
        self.commandHelp[43] = 'Returns the total losses for the active circuit in the Result string in kW, kvar.'
        self.commandHelp[44] = 'Estimates the allocation factors for loads that are defined using the XFKVA property. ' + 'Requires that energymeter objects be defined with the PEAKCURRENT property set. ' + 'Loads that are not in the zone of an energymeter cannot be allocated.'
        self.commandHelp[45] = 'FormEdit [class.object].  Brings up form editor on active DSS object.'
        self.commandHelp[46] = 'Totals all EnergyMeter objects in the circuit and reports register totals in the result string.'
        self.commandHelp[47] = 'Find the maximum load the active circuit can serve in the PRESENT YEAR. Uses the EnergyMeter objects with the registers ' + 'set with the SET UEREGS= (..) command for the AutoAdd functions.  Syntax (defaults shown):' + CRLF + CRLF + 'capacity [start=]0.9 [increment=]0.005' + CRLF + CRLF + 'Returns the metered kW (load + losses - generation) and per unit load multiplier for the loading level at which something in the system reports an overload or undervoltage. ' + 'If no violations, then it returns the metered kW for peak load for the year (1.0 multiplier). ' + 'Aborts and returns 0 if no energymeters.'
        self.commandHelp[48] = 'List of intrinsic DSS Classes. Returns comma-separated list in Result variable.'
        self.commandHelp[49] = 'List of user-defined DSS Classes. Returns comma-separated list in Result variable.'
        self.commandHelp[50] = 'Returns full Zsc matrix for the ACTIVE BUS in comma-separated complex number form.'
        self.commandHelp[51] = 'Returns symmetrical component impedances, Z1, Z0 for the ACTIVE BUS in comma-separated R+jX form.'
        self.commandHelp[52] = 'Refreshes Zsc matrix for the ACTIVE BUS.'
        self.commandHelp[53] = 'Returns full Ysc matrix for the ACTIVE BUS in comma-separated complex number form G + jB.'
        self.commandHelp[54] = 'Just like the Voltages command, except the voltages are in per unit if the kVbase at the bus is defined.'
        self.commandHelp[55] = 'Returns variable values for active element if PC element. Otherwise, returns null.'
        self.commandHelp[56] = 'Returns variable names for active element if PC element. Otherwise, returns null.'
        self.commandHelp[57] = 'Define x,y coordinates for buses.  Execute after Solve command performed so that bus lists are defined.' + 'Reads coordinates from a CSV file with records of the form: busname, x, y.' + CRLF + CRLF + 'Example: BusCoords [file=]xxxx.csv'
        self.commandHelp[58] = 'Updates the buslist using the currently enabled circuit elements.  (This happens automatically for Solve command.)'
        self.commandHelp[59] = 'Attempts to convert present circuit model to a positive sequence equivalent. ' + 'It is recommended to Save the circuit after this and edit the saved version to correct possible misinterpretations.'
        self.commandHelp[60] = '{All | MeterName}  Default is \"All\".  Reduce the circuit according to reduction options. ' + 'See \"Set ReduceOptions\" and \"Set Keeplist\" options.' + 'Energymeter objects actually perform the reduction.  \"All\" causes all meters to reduce their zones.'
        self.commandHelp[61] = '{All | MeterName}  Default is \"All\". Interpolates coordinates for missing bus coordinates in meter zone'
        self.commandHelp[62] = 'Alignfile [file=]filename.  Aligns DSS script files in columns for easier reading.'
        self.commandHelp[63] = '[class=]{Loadshape | TShape | Monitor  } [object=]{ALL (Loadshapes only) | objectname}. ' + 'Send specified object to TOP.  Loadshapes and TShapes must be hourly fixed interval. '
        self.commandHelp[64] = 'Usage: Rotate [angle=]nnn.  Rotate circuit plotting coordinates by specified angle (degrees). '
        self.commandHelp[65] = 'Displays the difference between the present solution and the last on saved using the SAVE VOLTAGES command.'
        self.commandHelp[66] = 'Returns a power flow summary of the most recent solution in the global result string.'
        self.commandHelp[67] = 'kw=nn how={Proportional | Uniform |Random | Skip} skip=nn PF=nn file=filename MW=nn' + CRLF + 'Distributes generators on the system in the manner specified by \"how\".' + CRLF + 'kW = total generation to be distributed (default=1000) ' + CRLF + 'how= process name as indicated (default=proportional to load)' + CRLF + 'skip = no. of buses to skip for \"How=Skip\" (default=1)' + CRLF + 'PF = power factor for new generators (default=1.0)' + CRLF + 'file = name of file to save (default=distgenerators.txt)' + CRLF + 'MW = alternate way to specify kW (default = 1)'
        self.commandHelp[68] = '[case=]casename [year=]yr [registers=](reg1, reg2,...)  [peak=]y/n  [meter=]metername' + CRLF + 'Plots demand interval (DI) results from yearly simulation cases.  ' + 'Plots selected registers from selected meter file (default = DI_Totals.CSV).  ' + 'Peak defaults to NO.  If YES, only daily peak of specified registers ' + 'is plotted. Example:' + CRLF + CRLF + ' DI_Plot basecase year=5 registers=(9,11) no'
        self.commandHelp[69] = '[Case1=]casename [case2=]casename [register=](register number) [meter=]{Totals* | SystemMeter | metername}. ' + CRLF + 'Compares yearly simulations of two specified cases with respect to the quantity in the designated register ' + 'from the designated meter file. ' + 'Defaults: Register=9 meter=Totals.  Example:' + CRLF + CRLF + 'Comparecases base pvgens 10'
        self.commandHelp[70] = '[cases=](case1, case2, ...) [registers=](reg1, reg2, ...)  [meter=]{Totals* | SystemMeter | metername}' + 'Plots yearly curves for specified cases and registers. ' + CRLF + 'Default: meter=Totals. Example: ' + CRLF + CRLF + 'yearlycurves cases=(basecase, pvgens) registers=9'
        self.commandHelp[71] = 'Change default directory to specified directory' + CRLF + CRLF + 'CD dirname'
        self.commandHelp[72] = '[What=] one of {Currents* | Voltages | Powers} [element=]full_element_name  (class.name). ' + 'Shows the selected quantity for selected element on a multiphase line drawing in phasor values.'
        self.commandHelp[73] = 'Close all DI files ... useful at end of yearly solution where DI files are left open. ' + '(Reset and Set Year=nnn will also close the DI files)'
        self.commandHelp[74] = 'Do a DOS command. Sends the command \"cmd ... \" to Windows. Execute the \"cmd /?\" command ' + 'in a DOS window to see the options. To do a DOS command and automatically exit, do ' + CRLF + CRLF + 'DOScmd /c ...command string ...' + CRLF + CRLF + 'To keep the DOS window open, use /k switch.'
        self.commandHelp[75] = 'Execute state estimator on present circuit given present sensor values.'
        self.commandHelp[76] = 'Reconductor a line section. Must be in an EnergyMeter zone. ' + CRLF + 'Syntax: Reconductor Line1=... Line2=... [LineCode= | Geometry = ] ' + CRLF + 'Line1 and Line2 may be given in any order. All lines in the path between the two are redefined ' + 'with either the LineCode or Geometry.'
        self.commandHelp[77] = 'For step control of solution process: Intialize iteration counters, etc. that normally occurs at the ' + 'start of a snapshot solution process.'
        self.commandHelp[78] = 'For step control of solution process: Solves the circuit in present state but does not check for control actions.'
        self.commandHelp[79] = 'For step control of solution process: Sample the control elements, which push control action requests onto the control queue.'
        self.commandHelp[80] = 'For step control of solution process: Pops control actions off the control queue according to the present control mode rules. ' + 'Dispatches contol actions to proper control element \"DoPendingAction\" handlers.'
        self.commandHelp[81] = 'For step control of solution process: Show the present control queue contents.'
        self.commandHelp[82] = 'For step control of solution process: Invoke direct solution function in DSS. Non-iterative solution of Y matrix and active sources only.'
        self.commandHelp[83] = 'For step control of solution process: Invoke iterative power flow solution function of DSS directly.'
        self.commandHelp[84] = 'Add a marker to the active plot. Example: ' + CRLF + CRLF + 'AddMarker Bus=busname code=nn color=$00FF0000 size=3'
        self.commandHelp[85] = 'Read GUIDS for class names. Tab or comma-delimited file with full object name and GUID'
        self.commandHelp[86] = 'Set load and generator object kv from bus voltage base and connection type.'
        self.commandHelp[87] = 'Convert all Loadshapes presently loaded into either files of single or files of double. ' + 'Usually files of singles are adequate precision for loadshapes.  Syntax:' + CRLF + CRLF + 'cvrtloadshapes type=sng  (this is the default)' + CRLF + 'cvrtloadshapes type=dbl' + CRLF + CRLF + 'A DSS script for loading the loadshapes from the created files is produced and displayed in the default editor. '
        self.commandHelp[88] = 'Global result is set to voltage difference, volts and degrees, (Node1 - Node2) between any two nodes. Syntax:' + CRLF + CRLF + '   NodeDiff Node1=MyBus.1 Node2=MyOtherBus.1'
        self.commandHelp[89] = 'Generates a script to change the phase designation of all lines downstream from a start in line. Useful for such things as moving a single-phase ' + 'lateral from one phase to another and keep the phase designation consistent for reporting functions that need it to be ' + '(not required for simply solving). ' + CRLF + CRLF + 'StartLine=... PhaseDesignation=\"...\"  EditString=\"...\" ScriptFileName=... StopAtTransformers=Y/N/T/F' + CRLF + CRLF + 'Enclose the PhaseDesignation in quotes since it contains periods (dots).' + CRLF + 'You may add and optional EditString to edit any other line properties.' + CRLF + CRLF + 'Rephase StartLine=Line.L100  PhaseDesignation=\".2\"  EditString=\"phases=1\" ScriptFile=Myphasechangefile.DSS  Stop=No'
        self.commandHelp[90] = 'Bus=...  X=...  Y=... Set the X, Y coordinates for a single bus. Prerequisite: Bus must exist as a result of a Solve, CalcVoltageBases, or MakeBusList command.'
        self.commandHelp[91] = 'Update Storage elements based on present solution and time interval. '
        self.commandHelp[92] = 'Change Bus and circuit element names to generic values to remove identifying names. Generally, ' + 'you will follow this command immediately by a \"Save Circuit Dir=MyDirName\" command.'

    def getLastCmdLine(self):
        return self.lastCmdLine

    def setLastCmdLine(self, line):
        self.lastCmdLine = line

    def getRedirFile(self):
        return self.redirFile

    def setRedirFile(self, file):
        self.redirFile = file

    def getExecCommand(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.execCommand
        elif _1 == 1:
            i, = _0
            return self.execCommand[i]
        else:
            raise ARGERROR(0, 1)

    def setExecCommand(self, command):
        self.execCommand = command

    def getCommandList(self):
        return self.commandList

    def setCommandList(self, list):
        self.commandList = list

    def getCommandHelp(self, i):
        return self.commandHelp[i]

    def processCommand(self, cmdLine):
        objName = str()
        propName = str()
        parser = Parser.getInstance()
        # Globals.doErrorMsg("ProcessCommand"+DSSGlobals.CRLF+"Exception raised while processing DSS command:"+ DSSGlobals.CRLF + Parser.getCmdString(),
        # e.getMessage(),
        # "Error in command string or circuit data.", 303);
        try:
            DSSGlobals.cmdResult = 0
            DSSGlobals.errorNumber = 0
            # reset error number
            DSSGlobals.globalResult = ''
            # Load up the parser and process the first parameter only
            self.lastCmdLine = cmdLine
            parser.setCmdString(self.lastCmdLine)
            # load up command parser
            DSSGlobals.lastCommandWasCompile = False
            paramPointer = 0
            paramName = parser.getNextParam()
            param = parser.makeString()
            if len(param) == 0:
                return
                # skip blank line
                # check for command verb or property value
                # commands do not have equal signs so paramName must be zero
            if len(paramName) == 0:
                paramPointer = self.commandList.getCommand(param)
                # check first for "compile" or "redirect" and return
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 13:
                    _1 = True
                    if DSSExecutive.getInstance().isRecorderOn():
                        DSSExecutive.getInstance().writeToRecorderFile(DSSGlobals.CRLF + '!*********' + cmdLine)
                    DSSGlobals.cmdResult = ExecHelper.doRedirect(True)
                    return
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    if DSSExecutive.getInstance().isRecorderOn():
                        DSSExecutive.getInstance().writeToRecorderFile(DSSGlobals.CRLF + '!*********' + cmdLine)
                    DSSGlobals.cmdResult = ExecHelper.doRedirect(False)
                    return
                if True:
                    _1 = True
                    if DSSExecutive.getInstance().isRecorderOn():
                        DSSExecutive.getInstance().writeToRecorderFile(cmdLine)
                    break
                break
            # things that are ok to do before a circuit is defined
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doNewCmd()
                    # new
                    break
                if (_3 is True) or (_2 == 14):
                    _3 = True
                    if DSSGlobals.activeCircuit is None:
                        ExecOptions.getInstance().doSetCmd_NoCircuit()
                        # can only call this if no circuit active
                        return
                        # we exit with either a good outcome or bad
                    break
                if (_3 is True) or (_2 == 18):
                    _3 = True
                    break
                if (_3 is True) or (_2 == 20):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doHelpCmd()
                    break
                if (_3 is True) or (_2 == 21):
                    _3 = True
                    if not DSSGlobals.isDLL:
                        DSSGlobals.DSSForms.exitControlPanel()
                    break
                if (_3 is True) or (_2 == 24):
                    _3 = True
                    DSSGlobals.DSSForms.showControlPanel()
                    break
                if (_3 is True) or (_2 == 26):
                    _3 = True
                    ExecHelper.doClearCmd()
                    break
                if (_3 is True) or (_2 == 27):
                    _3 = True
                    ExecHelper.doAboutBox()
                    break
                if (_3 is True) or (_2 == 34):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doFileEditCmd()
                    break
                if (_3 is True) or (_2 == 48):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doClassesCmd()
                    break
                if (_3 is True) or (_2 == 49):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doUserClassesCmd()
                    break
                if (_3 is True) or (_2 == 62):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doAlignFileCmd()
                    break
                if (_3 is True) or (_2 == 68):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doDI_PlotCmd()
                    break
                if (_3 is True) or (_2 == 69):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doCompareCasesCmd()
                    break
                if (_3 is True) or (_2 == 70):
                    _3 = True
                    DSSGlobals.cmdResult = ExecHelper.doYearlyCurvesCmd()
                    break
                if (_3 is True) or (_2 == 71):
                    _3 = True
                    paramName = parser.getNextParam()
                    param = parser.makeString()
                    if self.File(DSSGlobals.currentDirectory).exists():
                        DSSGlobals.currentDirectory = param
                        DSSGlobals.cmdResult = 0
                        DSSGlobals.setDataPath(param)
                        # change DSS data directory
                    else:
                        DSSGlobals.doSimpleMsg('Directory \"' + param + '\" not found.', 282)
                    break
                if (_3 is True) or (_2 == 74):
                    _3 = True
                    ExecHelper.doADOScmd()
                    break
                if (_3 is True) or (_2 == 87):
                    _3 = True
                    ExecHelper.doCvrtLoadshapesCmd()
                    break
                if True:
                    _3 = True
                    if DSSGlobals.activeCircuit is None:
                        DSSGlobals.doSimpleMsg('You must create a new circuit object first: \"new circuit.mycktname\" to execute this command.', 301)
                    break
                break
            # now check to see if this is a command or a property reference
            if paramPointer == -1:
                # If not a command or the command is unknown, then it could be a property of a circuit element
                # If a command or no text before the = sign, then error
                if (len(paramName) == 0) or paramName.equalsIgnoreCase('command'):
                    DSSGlobals.doSimpleMsg('Unknown Command: \"' + param + '\" ' + DSSGlobals.CRLF + parser.getCmdString(), 302)
                    DSSGlobals.cmdResult = 1
                else:
                    ExecHelper.parseObjName(paramName, objName, propName)
                    if len(objName) > 0:
                        DSSGlobals.setObject(str(objName))
                        # set active element
                    if DSSGlobals.activeDSSObject is not None:
                        # rebuild command line and pass to editor
                        # use quotes to ensure first parameter is interpreted ok after rebuild
                        parser.setCmdString(str(propName) + '=\"' + param + '\" ' + parser.getRemainder())
                        DSSGlobals.activeDSSClass.edit()
                return
            # process the rest of the commands
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 1:
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doEditCmd()
                    # edit
                    break
                if (_5 is True) or (_4 == 2):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doMoreCmd()
                    # more
                    break
                if (_5 is True) or (_4 == 3):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doMoreCmd()
                    # m
                    break
                if (_5 is True) or (_4 == 4):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doMoreCmd()
                    # ~
                    break
                if (_5 is True) or (_4 == 5):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSelectCmd()
                    break
                if (_5 is True) or (_4 == 6):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSaveCmd()
                    # save
                    break
                if (_5 is True) or (_4 == 7):
                    _5 = True
                    DSSGlobals.cmdResult = ShowOptions.getInstance().doShowCmd()
                    # show
                    break
                if (_5 is True) or (_4 == 8):
                    _5 = True
                    DSSGlobals.cmdResult = ExecOptions.getInstance().doSetCmd(1)
                    # solve
                    break
                if (_5 is True) or (_4 == 9):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doEnableCmd()
                    break
                if (_5 is True) or (_4 == 10):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doDisableCmd()
                    break
                if (_5 is True) or (_4 == 11):
                    _5 = True
                    DSSGlobals.cmdResult = PlotOptions.getInstance().doPlotCmd()
                    break
                if (_5 is True) or (_4 == 12):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doResetCmd()
                    break
                if (_5 is True) or (_4 == 14):
                    _5 = True
                    DSSGlobals.cmdResult = ExecOptions.getInstance().doSetCmd(0)
                    # set with no solve
                    break
                if (_5 is True) or (_4 == 15):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doPropertyDump()
                    break
                if (_5 is True) or (_4 == 17):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doCloseCmd()
                    break
                if (_5 is True) or (_4 == 22):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doQueryCmd()
                    break
                if (_5 is True) or (_4 == 23):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doNextCmd()
                    break
                    # case 24:
                    # DSSForms.showControlPanel()
                    # break;
                if (_5 is True) or (_4 == 25):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSampleCmd()
                    break
                    # case 26:
                    # clearAllCircuits();
                    # disposeDSSClasses();
                    # createDSSClasses();
                    # break;
                    # case 27:
                    # doAboutBox();
                    # break;
                if (_5 is True) or (_4 == 28):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSetVoltageBases()
                    break
                if (_5 is True) or (_4 == 29):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSetkVBase()
                    break
                if (_5 is True) or (_4 == 30):
                    _5 = True
                    DSSGlobals.activeCircuit.invalidateAllPCElements()
                    break
                if (_5 is True) or (_4 == 31):
                    _5 = True
                    DSSGlobals.cmdResult = ExecOptions.getInstance().doGetCmd()
                    break
                if (_5 is True) or (_4 == 32):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().setSolutionInitialized(False)
                    break
                if (_5 is True) or (_4 == 33):
                    _5 = True
                    DSSGlobals.cmdResult = ExportOptions.getInstance().doExportCmd()
                    break
                if (_5 is True) or (_4 == 34):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doFileEditCmd()
                    break
                if (_5 is True) or (_4 == 35):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doVoltagesCmd(False)
                    break
                if (_5 is True) or (_4 == 36):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doCurrentsCmd()
                    break
                if (_5 is True) or (_4 == 37):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doPowersCmd()
                    break
                if (_5 is True) or (_4 == 38):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSeqVoltagesCmd()
                    break
                if (_5 is True) or (_4 == 39):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSeqCurrentsCmd()
                    break
                if (_5 is True) or (_4 == 40):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSeqPowersCmd()
                    break
                if (_5 is True) or (_4 == 42):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doLossesCmd()
                    break
                if (_5 is True) or (_4 == 43):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doCktLossesCmd()
                    break
                if (_5 is True) or (_4 == 44):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doAllocateLoadsCmd()
                    break
                if (_5 is True) or (_4 == 45):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doFormEditCmd()
                    break
                if (_5 is True) or (_4 == 46):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doMeterTotals()
                    break
                if (_5 is True) or (_4 == 47):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doCapacityCmd()
                    break
                    # case 48:
                    # Globals.setCmdResult(ExecHelper.doClassesCmd());
                    # break;
                    # case 49:
                    # Globals.setCmdResult(ExecHelper.doUserClassesCmd();
                    # break;
                if (_5 is True) or (_4 == 50):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doZscCmd(True)
                    break
                if (_5 is True) or (_4 == 51):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doZsc10Cmd()
                    break
                if (_5 is True) or (_4 == 52):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doZscRefresh()
                    break
                if (_5 is True) or (_4 == 53):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doZscCmd(False)
                    break
                if (_5 is True) or (_4 == 54):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doVoltagesCmd(True)
                    break
                if (_5 is True) or (_4 == 55):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doVarValuesCmd()
                    break
                if (_5 is True) or (_4 == 56):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doVarNamesCmd()
                    break
                if (_5 is True) or (_4 == 57):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doBusCoordsCmd()
                    break
                if (_5 is True) or (_4 == 58):
                    _5 = True
                    if DSSGlobals.activeCircuit.isBusNameRedefined():
                        DSSGlobals.activeCircuit.reProcessBusDefs()
                    break
                if (_5 is True) or (_4 == 59):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doMakePosSeq()
                    break
                if (_5 is True) or (_4 == 60):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doReduceCmd()
                    break
                if (_5 is True) or (_4 == 61):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doInterpolateCmd()
                    break
                if (_5 is True) or (_4 == 63):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doTOPCmd()
                    break
                if (_5 is True) or (_4 == 64):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doRotateCmd()
                    break
                if (_5 is True) or (_4 == 65):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doVDiffCmd()
                    break
                if (_5 is True) or (_4 == 66):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSummaryCmd()
                    break
                if (_5 is True) or (_4 == 67):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doDistributeCmd()
                    break
                if (_5 is True) or (_4 == 68):
                    _5 = True
                    break
                if (_5 is True) or (_4 == 69):
                    _5 = True
                    break
                if (_5 is True) or (_4 == 70):
                    _5 = True
                    break
                if (_5 is True) or (_4 == 71):
                    _5 = True
                    break
                if (_5 is True) or (_4 == 72):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doVisualizeCmd()
                    break
                if (_5 is True) or (_4 == 73):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doCloseDICmd()
                    break
                if (_5 is True) or (_4 == 75):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doEstimateCmd()
                    break
                if (_5 is True) or (_4 == 76):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doReconductorCmd()
                    break
                    # Step solution commands
                if (_5 is True) or (_4 == 77):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().snapShotInit()
                    break
                if (_5 is True) or (_4 == 78):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().solveCircuit()
                    break
                if (_5 is True) or (_4 == 79):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().sampleControlDevices()
                    break
                if (_5 is True) or (_4 == 80):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().doControlActions()
                    break
                if (_5 is True) or (_4 == 81):
                    _5 = True
                    DSSGlobals.activeCircuit.getControlQueue().showQueue(DSSGlobals.DSSDirectory + DSSGlobals.circuitName_ + 'ControlQueue.csv')
                    break
                if (_5 is True) or (_4 == 82):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().solveDirect()
                    break
                if (_5 is True) or (_4 == 83):
                    _5 = True
                    DSSGlobals.activeCircuit.getSolution().doPFlowSolution()
                    break
                if (_5 is True) or (_4 == 84):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doAddMarkerCmd()
                    break
                if (_5 is True) or (_4 == 85):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doUUIDsCmd()
                    break
                if (_5 is True) or (_4 == 86):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSetLoadAndGenKVCmd()
                    break
                if (_5 is True) or (_4 == 87):
                    _5 = True
                    break
                if (_5 is True) or (_4 == 88):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doNodeDiffCmd()
                    break
                if (_5 is True) or (_4 == 89):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doRephaseCmd()
                    break
                if (_5 is True) or (_4 == 90):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doSetBusXYCmd()
                    break
                if (_5 is True) or (_4 == 91):
                    _5 = True
                    DSSGlobals.cmdResult = ExecHelper.doUpdateStorageCmd()
                    break
                if (_5 is True) or (_4 == 92):
                    _5 = True
                    Utilities.obfuscate()
                    break
                if True:
                    _5 = True
                    break
                break
        except Exception, e:
            e.printStackTrace()
