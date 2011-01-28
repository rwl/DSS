package com.epri.dss.executive.impl;

import com.epri.dss.common.DSSGlobals;
import com.epri.dss.executive.ExecOptions;
import com.epri.dss.shared.CommandList;

public class ExecOptionsImpl implements ExecOptions {

	private static final String CRLF = DSSGlobals.CRLF;

	private final static int NumExecOptions = 79;

	private String[] ExecOption;
	private String[] OptionHelp;

	private CommandList OptionList;

	public ExecOptionsImpl() {

		this.ExecOption = new String[NumExecOptions];

        this.ExecOption[0]  = "type";
        this.ExecOption[1]  = "element";
        this.ExecOption[2]  = "hour";
        this.ExecOption[3]  = "sec";
        this.ExecOption[4]  = "year";
        this.ExecOption[5]  = "frequency";
        this.ExecOption[6]  = "stepsize";
        this.ExecOption[7]  = "mode";
        this.ExecOption[8]  = "random";
        this.ExecOption[9] =  "number";
        this.ExecOption[10] = "time";
        this.ExecOption[11] = "class";
        this.ExecOption[12] = "object";
        this.ExecOption[13] = "circuit";
        this.ExecOption[14] = "editor";
        this.ExecOption[15] = "tolerance";
        this.ExecOption[16] = "maxiterations";
        this.ExecOption[17] = "h";
        this.ExecOption[18] = "Loadmodel";
        this.ExecOption[19] = "Loadmult";
        this.ExecOption[20] = "normvminpu";
        this.ExecOption[21] = "normvmaxpu";
        this.ExecOption[22] = "emergvminpu";
        this.ExecOption[23] = "emergvmaxpu";
        this.ExecOption[24] = "%mean";
        this.ExecOption[25] = "%stddev";
        this.ExecOption[26] = "LDCurve";  // Load Duration Curve
        this.ExecOption[27] = "%growth";  // default growth rate
        this.ExecOption[28] = "Genkw";
        this.ExecOption[29] = "Genpf";
        this.ExecOption[30] = "CapkVAR";
        this.ExecOption[31] = "Addtype";
        this.ExecOption[32] = "Allowduplicates";
        this.ExecOption[33] = "Zonelock";
        this.ExecOption[34] = "UEweight";
        this.ExecOption[35] = "Lossweight";
        this.ExecOption[36] = "UEregs";
        this.ExecOption[37] = "Lossregs";
        this.ExecOption[38] = "Voltagebases";  //  changes the default voltage base rules
        this.ExecOption[39] = "Algorithm";  //  changes the default voltage base rules
        this.ExecOption[40] = "Trapezoidal";
        this.ExecOption[41] = "Autobuslist";  // array of bus names to include in auto add solutions
        this.ExecOption[42] = "Controlmode";
        this.ExecOption[43] = "Tracecontrol";
        this.ExecOption[44] = "Genmult";
        this.ExecOption[45] = "Defaultdaily";
        this.ExecOption[46] = "Defaultyearly";
        this.ExecOption[47] = "Allocationfactors";
        this.ExecOption[48] = "Cktmodel";
        this.ExecOption[49] = "Pricesignal";
        this.ExecOption[50] = "Pricecurve";
        this.ExecOption[51] = "Terminal";
        this.ExecOption[52] = "Basefrequency";
        this.ExecOption[53] = "Harmonics";
        this.ExecOption[54] = "Maxcontroliter";
        this.ExecOption[55] = "Bus";
        this.ExecOption[56] = "Datapath";
        this.ExecOption[57] = "KeepList";
        this.ExecOption[58] = "ReduceOption";
        this.ExecOption[59] = "DemandInterval";
        this.ExecOption[60] = "%Normal";
        this.ExecOption[61] = "DIVerbose";
        this.ExecOption[62] = "Casename";
        this.ExecOption[63] = "Markercode";
        this.ExecOption[64] = "Nodewidth";
        this.ExecOption[65] = "Log";
        this.ExecOption[66] = "Recorder";
        this.ExecOption[67] = "Overloadreport";
        this.ExecOption[68] = "Voltexceptionreport";
        this.ExecOption[69] = "Cfactors";
        this.ExecOption[70] = "Showexport";
        this.ExecOption[71] = "Numallociterations";
        this.ExecOption[72] = "DefaultBaseFrequency";
        this.ExecOption[73] = "Markswitches";
        this.ExecOption[74] = "Switchmarkercode";
        this.ExecOption[75] = "Daisysize";
        this.ExecOption[76] = "Marktransformers";
        this.ExecOption[77] = "TransMarkerCode";
        this.ExecOption[78] = "TransMarkerSize";


		this.OptionHelp = new String[NumExecOptions];

        this.OptionHelp[0]  = "Sets the active DSS class type.  Same as Class=...";
        this.OptionHelp[1]  = "Sets the active DSS element by name. You can use "+
                              "the complete object spec (class.name) or just the "+
                              "name.  if full name is specifed, class becomes the active "+
                              "class, also.";
        this.OptionHelp[2]  = "Sets the hour used for the start time of the solution.";
        this.OptionHelp[3]  = "Sets the seconds from the hour for the start time of the solution.";
        this.OptionHelp[4]  = "Sets the Year (integer number) to be used for the solution. "+
                              "for certain solution types, this determines the growth multiplier.";
        this.OptionHelp[5]  = "Sets the frequency for the solution of the active circuit.";
        this.OptionHelp[6]  = "Sets the time step size for the active circuit.  Default units are s. " +
                              "May also be specified in minutes or hours by appending \"m\" or \"h\" to the value. For example:" + CRLF + CRLF +
                              "   stepsize=.25h " + CRLF + "  stepsize=15m" + CRLF + "  stepsize=900s";
        this.OptionHelp[7]  = "Set the solution Mode: One of"+
                              CRLF+"  Snapshot,"+
                              CRLF+"  Daily,"+
                              CRLF+"  DIrect,"+
                              CRLF+"  DUtycycle,"+
                              CRLF+"  DYnamic,"+
                              CRLF+"  Harmonic,"+
                              CRLF+"  M1 (Monte Carlo 1),"+
                              CRLF+"  M2 (Monte Carlo 2),"+
                              CRLF+"  M3 (Monte Carlo 3),"+
                              CRLF+"  Faultstudy,"+
                              CRLF+"  Yearly (follow Yearly curve),"+
                              CRLF+"  MF (monte carlo fault study)"+
                              CRLF+"  Peakday,"+
                              CRLF+"  LD1 (load-duration 1)"+
                              CRLF+"  LD2 (load-duration 2)"+
                              CRLF+"  AutoAdd (see AddType)" +CRLF +CRLF+
                              "Side effect: setting the Mode propergy resets all monitors and energy meters. It also " +
                              "resets the time step, etc. to defaults for each mode.  After the initial reset, the user " +
                              "must explicitly reset the monitors and/or meters until another Set Mode= command.";
        this.OptionHelp[8]  = "One of [Uniform | Gaussian | Lognormal | None ] for Monte Carlo Variables.";
        this.OptionHelp[9] = "Number of solutions to perform for Monte Carlo or dutycycle solutions.";
        this.OptionHelp[10] = "Specify the solution start time as an array:"+CRLF+
                              "time=(hour, secs)";
        this.OptionHelp[11] = "Synonym for Type=. (See above)";
        this.OptionHelp[12] = "Synonym for Element=. (See above)";
        this.OptionHelp[13] = "Set the active circuit by name.";
        this.OptionHelp[14] = "Set the command string required to start up the editor preferred by the user. Does not require a circuit defined.";
        this.OptionHelp[15] = "Sets the solution tolerance.  Default is 0.0001.";
        this.OptionHelp[16] = "Sets the maximum allowable iterations for power flow solutions. Default is 15.";
        this.OptionHelp[17] = "Alternate name for time step size.";
        this.OptionHelp[18] = "{Powerflow | Admittance} depending on the type of solution you wish to perform. "+
                              "If admittance, a non-iterative, direct solution is done with all loads and generators modeled by their "+
                              "equivalent admittance.";
        this.OptionHelp[19] = "Global load multiplier for this circuit.  Does not affect loads "+
                              "designated to be \"fixed\".  All other base kW values are multiplied by this number. "+
                              "Defaults to 1.0 when the circuit is created. As with other values, it always stays "+
                              "at the last value to which it was set until changed again.";
        this.OptionHelp[20] = "Minimum permissible per unit voltage for normal conditions. Default is 0.95.";
        this.OptionHelp[21] = "Maximum permissible per unit voltage for normal conditions. Default is 1.05.";
        this.OptionHelp[22] = "Minimum permissible per unit voltage for emergency (contingency) conditions. Default is 0.90.";
        this.OptionHelp[23] = "Maximum permissible per unit voltage for emergency (contingency) conditions. Default is 1.08.";
        this.OptionHelp[24] = "Percent mean to use for global load multiplier. Default is 65%.";
        this.OptionHelp[25] = "Percent Standard deviation to use for global load multiplier. Default is 9%.";
        this.OptionHelp[26] = "Set Load-Duration Curve. Global load multiplier is defined by this curve for LD1 and LD2 solution modes. Default is Nil.";
        this.OptionHelp[27] = "Set default annual growth rate, percent, for loads with no growth curve specified. Default is 2.5.";
        this.OptionHelp[28] = "Size of generator, kW, to automatically add to system. Default is 1000.0";
        this.OptionHelp[29] = "Power factor of generator to assume for automatic addition. Default is 1.0.";
        this.OptionHelp[30] = "Size of capacitor, kVAR, to automatically add to system.  Default is 600.0.";
        this.OptionHelp[31] = "{Generator | Capacitor} Default is Generator. Type of device for AutoAdd Mode.";
        this.OptionHelp[32] = "{YES/TRUE | NO/FALSE}   Default is No. Flag to indicate if it is OK to have devices of same name in the same class. " +
                              "If No, then a New command is treated as an Edit command. "+
                              "If Yes, then a New command will always result in a device being added.";
        this.OptionHelp[33] = "{YES/TRUE | NO/FALSE}  Default is No. if No, then meter zones are recomputed each time there is a change in the circuit. "+
                              "If Yes, then meter zones are not recomputed unless they have not yet been computed. "+
                              "Meter zones are normally recomputed on Solve command following a circuit change.";
        this.OptionHelp[34] = "Weighting factor for UE/EEN in AutoAdd functions.  Defaults to 1.0." + CRLF + CRLF +
                              "Autoadd mode minimizes"  + CRLF + CRLF +
                              "(Lossweight * Losses + UEweight * UE). " + CRLF + CRLF +
                              "If you wish to ignore UE, set to 0. " +
                              "This applies only when there are EnergyMeter objects. " +
                              "Otherwise, AutoAdd mode minimizes total system losses.";
        this.OptionHelp[35] = "Weighting factor for Losses in AutoAdd functions.  Defaults to 1.0." + CRLF+CRLF+
                              "Autoadd mode minimizes"  + CRLF+CRLF+
                              "(Lossweight * Losses + UEweight * UE). " + CRLF + CRLF +
                              "If you wish to ignore Losses, set to 0. "+
                              "This applies only when there are EnergyMeter objects. " +
                              "Otherwise, AutoAdd mode minimizes total system losses.";
        this.OptionHelp[36] = "Which EnergyMeter register(s) to use for UE in AutoAdd Mode. " +
                              "May be one or more registers.  if more than one, register values are summed together. " +
                              "Array of integer values > 0.  Defaults to 11 (for Load EEN). " + CRLF+CRLF+
                              "for a list of EnergyMeter register numbers, do the \"Show Meters\" command after defining a circuit.";
        this.OptionHelp[37] = "Which EnergyMeter register(s) to use for Losses in AutoAdd Mode. " +
                              "May be one or more registers.  if more than one, register values are summed together. " +
                              "Array of integer values > 0.  Defaults to 13 (for Zone kWh Losses). " +  CRLF+CRLF+
                              "for a list of EnergyMeter register numbers, do the \"Show Meters\" command after defining a circuit.";
        this.OptionHelp[38] = "Define legal bus voltage bases for this circuit.  Enter an array "+
                              "of the legal voltage bases, in phase-to-phase voltages, for example:" +CRLF+CRLF+
                              "set voltagebases=\".208, .480, 12.47, 24.9, 34.5, 115.0, 230.0\" "+CRLF+CRLF+
                              "When the CalcVoltageBases command is issued, a snapshot solution is performed "+
                              "with no load injections and the bus base voltage is set to the nearest legal voltage base. "+
                              "The defaults are as shown in the example above.";
        this.OptionHelp[39] = "{Normal | Newton}  Solution algorithm type.  Normal is a fixed point iteration " +
                              "that is a little quicker than the Newton iteration.  Normal is adequate for most radial "+
                              "distribution circuits.  Newton is more robust for circuits that are difficult to solve.";
        this.OptionHelp[40] = "{YES/TRUE | NO/FALSE}  Default is \"No\". Specifies whether to use trapezoidal integration for accumulating energy meter registers. " +
                              "Applies to EnergyMeter and Generator objects.  Default method simply multiplies the " +
                              "present value of the registers times the width of the interval. " +
                              "Trapezoidal is more accurate when there are sharp changes in a load shape or unequal intervals. " +
                              "Trapezoidal is automatically used for " +
                              "some load-duration curve simulations where the interval size varies considerably. " +
                              "Keep in mind that for Trapezoidal, you have to solve one more point than the number of intervals. " +
                              "That is, to do a Daily simulation on a 24-hr load shape, you would set Number=25 to force a solution " +
                              "at the first point again to establish the last (24th) interval.";
        this.OptionHelp[41] = "Array of bus names to include in AutoAdd searches. Or, you can specify a text file holding the names, one to a line, " +
                              "by using the syntax (file=filename) instead of the actual array elements. "  +
                              "Default is null, which results in the program " +
                              "using either the buses in the EnergyMeter object zones or, if no EnergyMeters, all the buses, which can " +
                              "make for lengthy solution times. " +CRLF+CRLF+
                              "Examples:"+CRLF+CRLF+
                              "Set autobuslist=(bus1, bus2, bus3, ... )" +CRLF+
                              "Set autobuslist=(file=buslist.txt)";
        this.OptionHelp[42] = "{OFF | STATIC |EVENT | TIME}  Default is \"STATIC\".  Control mode for the solution. " +
                              "Set to OFF to prevent controls from changing." + CRLF +
                              "STATIC = Time does not advance.  Control actions are executed in order of shortest time to act " +
                              "until all actions are cleared from the control queue.  Use this mode for power flow solutions which may require several " +
                              "regulator tap changes per solution." + CRLF+CRLF+
                              "EVENT = solution is event driven.  Only the control actions nearest in time " +
                              "are executed and the time is advanced automatically to the time of the event. " + CRLF +CRLF+
                              "TIME = solution is time driven.  Control actions are executed when the time for the pending " +
                              "action is reached or surpassed." + CRLF + CRLF +
                              "Controls may reset and may choose not to act when it comes their time. " +CRLF+
                              "Use TIME mode when modeling a control externally to the DSS and a solution mode such as " +
                              "DAILY or DUTYCYCLE that advances time, or set the time (hour and sec) explicitly from the external program. ";
        this.OptionHelp[43] = "{YES/TRUE | NO/FALSE}  Set to YES to trace the actions taken in the control queue.  "  +
                              "Creates a file named TRACE_CONTROLQUEUE.CSV in the default directory. " +
                              "The names of all circuit elements taking an action are logged.";
        this.OptionHelp[44] = "Global multiplier for the kW output of every generator in the circuit. Default is 1.0. " +
                              "Applies to all but Autoadd solution modes. " +
                              "Ignored for generators designated as Status=Fixed.";
        this.OptionHelp[45] = "Default daily load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.";
        this.OptionHelp[46] = "Default yearly load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.";
        this.OptionHelp[47] = "Sets the connected kVA allocation factors for all loads in the active circuit to the value given.";
        this.OptionHelp[48] = "{Multiphase | Positive}  Default = Multiphase.  Designates whether circuit model is to interpreted as a normal multi-phase "+
                              "model or a positive-sequence only model";
        this.OptionHelp[49] = "Sets the price signal ($/MWh) for the circuit.  Initial value is 25.";
        this.OptionHelp[50] = "Sets the curve to use to obtain for price signal. Default is none (null string). If none, " +
                              "price signal either remains constant or is set by an external process. " +
                              "Curve is defined as a loadshape (not normalized) and should correspond to " +
                              "the type of analysis being performed (daily, yearly, load-duration, etc.).";
        this.OptionHelp[51] = "Set the active terminal of the active circuit element. May also be done with Select command.";
        this.OptionHelp[52] = "Default = 60. Set the fundamental frequency for harmonic solution and the default base frequency for all impedance quantities. " +
                              "Side effect: also changes the value of the solution frequency. Saved as default for next circuit.";
        this.OptionHelp[53] = "{ALL | (list of harmonics) }  Default = ALL. Array of harmonics for which to perform a solution in Harmonics mode. " +
                              "If ALL, then solution is performed for all harmonics defined in spectra currently being used. " +
                              "Otherwise, specify a more limited list such as: " +CRLF+CRLF+
                              "   Set Harmonics=(1 5 7 11 13)";
        this.OptionHelp[54] = "Max control iterations per solution.  Default is 10.";
        this.OptionHelp[55] = "Set Active Bus by name.  Can also be done with Select and SetkVBase commands and the \"Set Terminal=\"  option. " +
                              "The bus connected to the active terminal becomes the active bus. See Zsc and Zsc012 commands.";
        this.OptionHelp[56] = "Set the data path for files written or read by the DSS.  Defaults to the startup path. May be Null.  Executes a CHDIR to this path if non-null. Does not require a circuit defined.";
        this.OptionHelp[57] = "Array of bus names to keep when performing circuit reductions. You can specify a text file holding the names, one to a line, " +
                              "by using the syntax (file=filename) instead of the actual array elements. "  +
                              "Command is cumulative (reset keeplist first). " +
                              "Reduction algorithm may keep other buses automatically. " +CRLF+CRLF+
                              "Examples:"+CRLF+CRLF+
                              "Reset Keeplist (sets all buses to FALSE (no keep))" +CRLF+
                              "Set KeepList=(bus1, bus2, bus3, ... )" +CRLF+
                              "Set KeepList=(file=buslist.txt)";
        this.OptionHelp[58] = "{ Default or [null] | Stubs [Zmag=nnn] | MergeParallel | BreakLoops | Switches | TapEnds [maxangle=nnn] | Ends}  Strategy for reducing feeders. " +
                              "Default is to eliminate all dangling end buses and buses without load, caps, or taps. " +  CRLF +
                              "\"Stubs [Zmag=0.02]\" merges short branches with impedance less than Zmag (default = 0.02 ohms) " + CRLF +
                              "\"MergeParallel\" merges lines that have been found to be in parallel " +CRLF+
                              "\"Breakloops\" disables one of the lines at the head of a loop. " +CRLF+
                              "\"Tapends [maxangle=15]\" eliminates all buses except those at the feeder ends, at tap points and where the feeder turns by greater than maxangle degrees. " + CRLF+
                              "\"Ends\" eliminates dangling ends only."+CRLF+
                              "\"Switches\" merges switches with downline lines and eliminates dangling switches."+CRLF+
                              "Marking buses with \"Keeplist\" will prevent their elimination.";
        this.OptionHelp[59] = "{YES/TRUE | NO/FALSE} Default = no. Set for keeping demand interval data for daily, yearly, etc, simulations. "+
                              "Side Effect:  Resets all meters!!!";
        this.OptionHelp[60] = "Sets the Normal rating of all lines to a specified percent of the emergency rating.  Note: This action takes place immediately. "+
                              "Only the in-memory value is changed for the duration of the run.";
        this.OptionHelp[61] = "{YES/TRUE | NO/FALSE} Default = FALSE.  Set to Yes/True if you wish a separate demand interval (DI) file written " +
                              "for each meter.  Otherwise, only the totalizing meters are written.";
        this.OptionHelp[62] = "Name of case for yearly simulations with demand interval data. "+
                              "Becomes the name of the subdirectory under which all the year data are stored. "+
                              "Default = circuit name "+CRLF+CRLF+
                              "Side Effect: Sets the prefix for output files";
        this.OptionHelp[63] = "Number code for node marker on circuit plots. Number from 0 to 47. Default is 16 (open circle). 24 is solid circle. Try other values for other symbols. See also Nodewidth";
        this.OptionHelp[64] = "Width of node marker. Default=1. See MarkerCode";
        this.OptionHelp[65] = "{YES/TRUE | NO/FALSE} Default = FALSE.  Significant solution events are added to the Event Log, primarily for debugging.";
        this.OptionHelp[66] = "{YES/TRUE | NO/FALSE} Default = FALSE. Opens DSSRecorder.DSS in DSS install folder and enables recording of all commands that come through " +
                              "the text command interface. Closed by either setting to NO/FALSE or exiting the program. " +
                              "When closed by this command, the file name can be found in the Result. Does not require a circuit defined.";
        this.OptionHelp[67] = "{YES/TRUE | NO/FALSE} Default = FALSE. For yearly solution mode, sets overload reporting on/off. DemandInterval must be set to true for this to have effect.";
        this.OptionHelp[68] = "{YES/TRUE | NO/FALSE} Default = FALSE. For yearly solution mode, sets voltage exception reporting on/off. DemandInterval must be set to true for this to have effect.";
        this.OptionHelp[69] = "Sets the CFactors for for all loads in the active circuit to the value given.";
        this.OptionHelp[70] = "{YES/TRUE | NO/FALSE} Default = FALSE. If YES/TRUE will automatically show the results of an Export Command after it is written.";
        this.OptionHelp[71] = "Default is 2. Maximum number of iterations for load allocations for each time the AllocateLoads or Estimate command is given.";
        this.OptionHelp[72] = "Set Default Base Frequency, Hz. Side effect: Sets solution Frequency and default Circuit Base Frequency. This value is saved when the DSS closes down.";
        this.OptionHelp[73] = "{YES/TRUE | NO/FALSE}  Default is NO. Mark lines that are switches or are isolated with a symbol. See SwitchMarkerCode.";
        this.OptionHelp[74] = "Numeric marker code for lines with switches or are isolated from the circuit. Default is 4. See markswitches option.";
        this.OptionHelp[75] = "Default is 1.0. Relative size (a multiplier applied to default size) of daisy circles on daisy plot.";
        this.OptionHelp[76] = "{YES/TRUE | NO/FALSE}  Default is NO. Mark transformer locations with a symbol. See TransMarkerCode. " +
                              "The coordinate of one of the buses for winding 1 or 2 must be defined for the symbol to show";
        this.OptionHelp[77] = "Numeric marker code for transformers. Default is 35. See markstransformers option.";
        this.OptionHelp[78] = "Size of transformer marker. Default is 1.";
	}

	public int doGetCmd() {
		return 0;
	}

	public int doSetCmd(int SolveOption) {
		return 0;
	}

	/* Set Commands that do not require a circuit */
	public boolean doSetCmd_NoCircuit() {
		return false;
	}

}
