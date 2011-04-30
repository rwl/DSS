package com.epri.dss.executive.impl;

import com.epri.dss.common.AutoAdd;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.executive.Executive;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;

public class ExecOptions {

	private static final String CRLF = DSSGlobals.CRLF;

	public final static int NumExecOptions = 79;

	private String[] ExecOption;
	private String[] OptionHelp;

	private CommandList OptionList;

	// Private constructor prevents instantiation from other classes
	private ExecOptions() {
		defineOptions();
	}

	/**
	 * ExecOptionsHolder is loaded on the first execution of
	 * ExecOptions.getInstance() or the first access to
	 * ExecOptionsHolder.INSTANCE, not before.
	 */
	private static class ExecOptionsHolder {
		public static final ExecOptions INSTANCE = new ExecOptions();
	}

	public static ExecOptions getInstance() {
		return ExecOptionsHolder.INSTANCE;
	}

	private void defineOptions() {

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

	public String[] getExecOption() {
		return ExecOption;
	}

	public void setExecOption(String[] execOption) {
		ExecOption = execOption;
	}

	public String[] getOptionHelp() {
		return OptionHelp;
	}

	public void setOptionHelp(String[] optionHelp) {
		OptionHelp = optionHelp;
	}

	public String getExecOption(int i) {
		return ExecOption[i];
	}

	public String getOptionHelp(int i) {
		return OptionHelp[i];
	}

	public CommandList getOptionList() {
		return OptionList;
	}

	public void setOptionList(CommandList optionList) {
		OptionList = optionList;
	}

	/**
	 * Set Commands that do not require a circuit.
	 *
	 * This is for setting global options that do not require an active circuit.
	 */
	public boolean doSetCmd_NoCircuit() {
		boolean Result = true;

		Parser parser = Parser.getInstance();
		DSSGlobals Globals = DSSGlobals.getInstance();

		// Continue parsing command line
		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();

		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = OptionList.getCommand(ParamName);
			}

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Set Command ", 130);
			case 14:
				Globals.setDefaultEditor(Param);  // 'Editor='
			case 56:
				Globals.setDataPath(Param);  // Set a legal data path
			case 66:
				DSSExecutive.getDSSExecutive().setRecorderOn(Utilities.interpretYesNo(Param));
			case 72:
				Globals.setDefaultBaseFreq(parser.makeDouble());
			default:
				Globals.doSimpleMsg("You must create a new circuit object first: \"new circuit.mycktname\" to execute this Set command.", 301);
				Result = false;  // Indicate that we could not process all set command
				return Result;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		return Result;
	}

	/**
	 * Set DSS Options.
	 *
	 * Solve Command is re-routed here first to set options before solving.
	 */
	public int doSetCmd(int SolveOption) {
		LoadShapeObj TestLoadShapeObj;

		Parser parser = Parser.getInstance();
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt;
		SolutionObj solution;

		int Result = 0;
		// Continue parsing command line
		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();

		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = OptionList.getCommand(ParamName);
			}

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Set Command ", 130);
			case 0:
				DSSClassDefs.setObjectClass(Param);
			case 11:
				DSSClassDefs.setObjectClass(Param);
			case 1:
				Globals.setObject(Param);
			case 12:
				Globals.setObject(Param);
			case 2:
				Globals.getActiveCircuit().getSolution().setIntHour(parser.makeInteger());
			case 3:
				Globals.getActiveCircuit().getSolution().getDynaVars().t = parser.makeDouble();
			case 4:
				ckt = Globals.getActiveCircuit();
				ckt.getSolution().setYear(parser.makeInteger());
				ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1));
			case 5:
				Globals.getActiveCircuit().getSolution().setFrequency(parser.makeDouble());
			case 6:
				Globals.getActiveCircuit().getSolution().getDynaVars().h = Utilities.interpretTimeStepSize(Param);
			case 17:
				Globals.getActiveCircuit().getSolution().getDynaVars().h = Utilities.interpretTimeStepSize(Param);
			case 7:
				Globals.getActiveCircuit().getSolution().setMode( Utilities.interpretSolveMode(Param) );  // see DSSGlobals
			case 8:
				Globals.getActiveCircuit().getSolution().setRandomType( Utilities.interpretRandom(Param) );
			case 9:
				Globals.getActiveCircuit().getSolution().setNumberOfTimes(parser.makeInteger());
			case 10:
				ExecHelper.setTime();
			case 13:
				ExecHelper.setActiveCircuit(Param);
			case 14:
				Globals.setDefaultEditor(Param);  // 'Editor='
			case 15:
				Globals.getActiveCircuit().getSolution().setConvergenceTolerance(parser.makeDouble());
			case 16:
				Globals.getActiveCircuit().getSolution().setMaxIterations(parser.makeInteger());
			case 18:
				solution = Globals.getActiveCircuit().getSolution();
				solution.setDefaultLoadModel(Utilities.interpretLoadModel(Param)); // for reverting to last on specified
				solution.setLoadModel(solution.getDefaultLoadModel());
			case 19:
				Globals.getActiveCircuit().setLoadMultiplier(parser.makeDouble());  // Set using LoadMultiplier property
			case 20:
				Globals.getActiveCircuit().setNormalMinVolts(parser.makeDouble());
			case 21:
				Globals.getActiveCircuit().setNormalMaxVolts(parser.makeDouble());
			case 22:
				Globals.getActiveCircuit().setEmergMinVolts(parser.makeDouble());
			case 23:
				Globals.getActiveCircuit().setEmergMaxVolts(parser.makeDouble());
			case 24:
				Globals.getActiveCircuit().getDefaultDailyShapeObj().setMean(parser.makeDouble() / 100.0);
			case 25:
				Globals.getActiveCircuit().getDefaultDailyShapeObj().setStdDev(parser.makeDouble() / 100.0);
			case 26:
				ckt = Globals.getActiveCircuit();
				ckt.setLoadDurCurve(Param);
				ckt.setLoadDurCurveObj((LoadShapeObj) Globals.getLoadShapeClass().find(Param));
				if (ckt.getLoadDurCurveObj() == null)
					Globals.doSimpleMsg("Load-Duration Curve not found.", 131);
			case 27:
				ckt = Globals.getActiveCircuit();
				ckt.setDefaultGrowthRate(1.0 + parser.makeDouble() / 100.0);
				ckt.setDefaultGrowthFactor( Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1) );
			case 28:
				Globals.getActiveCircuit().getAutoAddObj().setGenkW(parser.makeDouble());
			case 29:
				Globals.getActiveCircuit().getAutoAddObj().setGenPF(parser.makeDouble());
			case 30:
				Globals.getActiveCircuit().getAutoAddObj().setCapkvar(parser.makeDouble());
			case 31:
				Globals.getActiveCircuit().getAutoAddObj().setAddType(Utilities.interpretAddType(Param));
			case 32:
				Globals.getActiveCircuit().setDuplicatesAllowed(Utilities.interpretYesNo(Param));
			case 33:
				Globals.getActiveCircuit().setZonesLocked(Utilities.interpretYesNo(Param));
			case 34:
				Globals.getActiveCircuit().setUEWeight(parser.makeDouble());
			case 35:
				Globals.getActiveCircuit().setLossWeight(parser.makeDouble());
			case 36:
				Utilities.parseIntArray(Globals.getActiveCircuit().getUERegs(), Globals.getActiveCircuit().getNumUERegs(), Param);
			case 37:
				Utilities.parseIntArray(Globals.getActiveCircuit().getLossRegs(), Globals.getActiveCircuit().getNumLossRegs(), Param);
			case 38:
				ExecHelper.doLegalVoltageBases();
			case 39:
				Globals.getActiveCircuit().getSolution().setAlgorithm(Utilities.interpretSolveAlg(Param));
			case 40:
				Globals.getActiveCircuit().setTrapezoidalIntegration(Utilities.interpretYesNo(Param));
			case 41:
				ExecHelper.doAutoAddBusList(Param);
			case 42:
				solution = Globals.getActiveCircuit().getSolution();
				solution.setControlMode(Utilities.interpretControlMode(Param));
				solution.setDefaultControlMode(solution.getControlMode());  // always revert to last one specified in a script
			case 43:
				Globals.getActiveCircuit().getControlQueue().setTrace(Utilities.interpretYesNo(Param));
			case 44:
				Globals.getActiveCircuit().setGenMultiplier(parser.makeDouble());
			case 45:
				TestLoadShapeObj = (LoadShapeObj) Globals.getLoadShapeClass().find(Param);
				if (TestLoadShapeObj != null)
					Globals.getActiveCircuit().setDefaultDailyShapeObj(TestLoadShapeObj);
			case 46:
				TestLoadShapeObj = (LoadShapeObj) Globals.getLoadShapeClass().find(Param);
				if (TestLoadShapeObj != null)
					Globals.getActiveCircuit().setDefaultYearlyShapeObj(TestLoadShapeObj);
			case 47:
				ExecHelper.doSetAllocationFactors(parser.makeDouble());
			case 48:
				Globals.getActiveCircuit().setPositiveSequence(Utilities.interpretCktModel(Param));
			case 49:
				Globals.getActiveCircuit().setPriceSignal(parser.makeDouble());
			case 50:
				ckt = Globals.getActiveCircuit();
				ckt.setPriceCurve(Param);
				ckt.setPriceCurveObj((LoadShapeObj) Globals.getLoadShapeClass().find(Param));
				if (ckt.getPriceCurveObj() == null)
					Globals.doSimpleMsg("Price Curve: \"" +Param+ "\" not found.", 132);
			case 51:
				ckt = Globals.getActiveCircuit();
				if (ckt.getActiveCktElement() != null) {
					CktElement cktElem = ckt.getActiveCktElement();
					cktElem.setActiveTerminalIdx(parser.makeInteger());
					Globals.setActiveBus( Utilities.stripExtension(cktElem.getBus(cktElem.getActiveTerminalIdx())) );   // bus connected to terminal
				}
			case 52:
				Globals.getActiveCircuit().setFundamental(parser.makeDouble());  // Set Base Frequency for system (used henceforth)
				Globals.getActiveCircuit().getSolution().setFrequency(parser.makeDouble());
			case 53:
				ExecHelper.doHarmonicsList(Param);
			case 54:
				Globals.getActiveCircuit().getSolution().setMaxControlIterations(parser.makeInteger());
			case 55:
				Result = Globals.setActiveBus(Param);  // See DSSGlobals
			case 56:
				Globals.setDataPath(Param);  // Set a legal data path
			case 57:
				ExecHelper.doKeeperBusList(Param);
			case 58:
				ExecHelper.doSetReduceStrategy(Param);
			case 59:
				Globals.getEnergyMeterClass().setSaveDemandInterval(Utilities.interpretYesNo(Param));
			case 60:
				Globals.getActiveCircuit().setPctNormalFactor(parser.makeDouble());
				ExecHelper.doSetNormal(Globals.getActiveCircuit().getPctNormalFactor());
			case 61:
				Globals.getEnergyMeterClass().setDI_Verbose(Utilities.interpretYesNo(Param));
			case 62:
				Globals.getActiveCircuit().setCaseName(parser.makeString());
			case 63:
				Globals.getActiveCircuit().setNodeMarkerCode(parser.makeInteger());
			case 64:
				Globals.getActiveCircuit().setNodeMarkerWidth(parser.makeInteger());
			case 65:
				Globals.getActiveCircuit().setLogEvents(Utilities.interpretYesNo(Param));
			case 66:
				DSSExecutive.getDSSExecutive().setRecorderOn(Utilities.interpretYesNo(Param));
			case 67:
				Globals.getEnergyMeterClass().setDo_OverloadReport(Utilities.interpretYesNo(Param));
			case 68:
				Globals.getEnergyMeterClass().setDo_VoltageExceptionReport(Utilities.interpretYesNo(Param));
			case 69:
				ExecHelper.doSetCFactors(parser.makeDouble());
			case 70:
				Globals.setAutoShowExport(Utilities.interpretYesNo(Param));
			case 71:
				Globals.setMaxAllocationIterations(parser.makeInteger());
			case 72:
				Globals.setDefaultBaseFreq(parser.makeDouble());
				Globals.getActiveCircuit().setFundamental(parser.makeDouble());  // Set Base Frequency for system (used henceforth)
				Globals.getActiveCircuit().getSolution().setFrequency(parser.makeDouble());
			case 73:
				Globals.getActiveCircuit().setMarkSwitches(Utilities.interpretYesNo(Param));
			case 74:
				Globals.getActiveCircuit().setSwitchMarkerCode(parser.makeInteger());
			case 75:
				Globals.setDaisySize(parser.makeDouble());
			case 76:
				Globals.getActiveCircuit().setMarkTransformers(Utilities.interpretYesNo(Param));
			case 77:
				Globals.getActiveCircuit().setTransMarkerCode(parser.makeInteger());
			case 78:
				Globals.getActiveCircuit().setTransMarkerSize(parser.makeInteger());
			case 79:
				Globals.getActiveCircuit().setActiveLoadShapeClass(Utilities.interpretLoadShapeClass(Param));
			case 80:
				Globals.setDefaultEarthModel(Utilities.interpretEarthModel(Param));
			default:
				// Ignore excess parameters
			}

			switch (ParamPointer) {
			case 3:
				Globals.getActiveCircuit().getSolution().updateDblHour();
			case 4:
				Globals.getActiveCircuit().getSolution().updateDblHour();
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		if (SolveOption == 1)
			ExecHelper.doSolveCmd();

		return 0;
	}

	/**
	 * Get DSS Options Reguaeste and put it in Global Result string
	 * may be retrieved by Result property of the DSSText interface.
	 */
	public int doGetCmd() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt;

		int Result = 0;
		int ParamPointer;
		try {
			Globals.setGlobalResult("");  // initialize for appending

			// Continue parsing command line
			String ParamName = Parser.getInstance().getNextParam();
			String Param = Parser.getInstance().makeString();
			// there will be no named paramters in this command and the params
			// themselves will be the parameter name to return
			while (Param.length() > 0) {
				ParamPointer = OptionList.getCommand(Param);

				switch (ParamPointer) {
				case -1:
					Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Get Command ", 133);
				case 0:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getActiveCktElement().getDSSClassName());
				case 11:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getActiveCktElement().getDSSClassName());
				case 1:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getActiveCktElement().getName());
				case 12:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getActiveCktElement().getName());
				case 2:
					Globals.appendGlobalResult(String.valueOf(Globals.getActiveCircuit().getSolution().getIntHour()));
				case 3:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getSolution().getDynaVars().t));
				case 4:
					Globals.appendGlobalResult(String.valueOf(Globals.getActiveCircuit().getSolution().getYear()));
				case 5:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getSolution().getFrequency()));
				case 6:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getSolution().getDynaVars().h));
				case 17:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getSolution().getDynaVars().h));
				case 7:
					Globals.appendGlobalResult(Utilities.getSolutionModeID());
				case 8:
					Globals.appendGlobalResult(Utilities.getRandomModeID());
				case 9:
					Globals.appendGlobalResult(String.valueOf(Globals.getActiveCircuit().getSolution().getNumberOfTimes()));
				case 10:
					Globals.appendGlobalResult(String.format("[ %d, %-g ]", String.valueOf(Globals.getActiveCircuit().getSolution().getIntHour()), Globals.getActiveCircuit().getSolution().getDynaVars().t));
				case 13:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getName());
				case 14:
					Globals.appendGlobalResult(Globals.getDefaultEditor());
				case 15:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getSolution().getConvergenceTolerance()));
				case 16:
					Globals.appendGlobalResult(String.valueOf(Globals.getActiveCircuit().getSolution().getMaxIterations()));
				case 18:
					Globals.appendGlobalResult(Utilities.getLoadModel());
				case 19:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getLoadMultiplier()));
				case 20:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getNormalMinVolts()));
				case 21:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getNormalMaxVolts()));
				case 22:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getEmergMinVolts()));
				case 23:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getEmergMaxVolts()));
				case 24:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getDefaultDailyShapeObj().getMean() * 100.0));
				case 25:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getDefaultDailyShapeObj().getStdDev() * 100.0));
				case 26:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getLoadDurCurve());
				case 27:
					Globals.appendGlobalResult(String.format("%-g", (Globals.getActiveCircuit().getDefaultGrowthRate() - 1.0) * 100.0));
				case 28:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getAutoAddObj().getGenkW()));
				case 29:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getAutoAddObj().getGenPF()));
				case 30:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getAutoAddObj().getCapkvar()));
				case 31:
					switch (Globals.getActiveCircuit().getAutoAddObj().getAddType()) {
					case DSSGlobals.GENADD:
						Globals.appendGlobalResult("generator");
					case DSSGlobals.CAPADD:
						Globals.appendGlobalResult("capacitor");
					}
				case 32:
					if (Globals.getActiveCircuit().isDuplicatesAllowed()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 33:
					if (Globals.getActiveCircuit().isZonesLocked()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 34:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getUEWeight()));
				case 35:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getLossWeight()));
				case 36:
					Globals.appendGlobalResult(Utilities.intArrayToString(Globals.getActiveCircuit().getUERegs(), Globals.getActiveCircuit().getNumUERegs()));
				case 37:
					Globals.appendGlobalResult(Utilities.intArrayToString(Globals.getActiveCircuit().getLossRegs(), Globals.getActiveCircuit().getNumLossRegs()));
				case 38:
					ckt = Globals.getActiveCircuit();
					Globals.setGlobalResult("(");
					for (double vBase : ckt.getLegalVoltageBases())
						Globals.setGlobalResult(Globals.getGlobalResult() + String.format("%-g, ", vBase));
					Globals.setGlobalResult(Globals.getGlobalResult() + ")");
				case 39:
					switch (Globals.getActiveCircuit().getSolution().getAlgorithm()) {
					case Solution.NORMALSOLVE:
						Globals.appendGlobalResult("normal");
					case Solution.NEWTONSOLVE:
						Globals.appendGlobalResult("newton");
					}
				case 40:
					if (Globals.getActiveCircuit().isTrapezoidalIntegration()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 41:
					for (int i = 0; i < Globals.getActiveCircuit().getAutoAddBusList().listSize(); i++)
						Globals.appendGlobalResult(Globals.getActiveCircuit().getAutoAddBusList().get(i));
				case 42:
					Globals.appendGlobalResult(Utilities.getControlModeID());
				case 43:
					if (Globals.getActiveCircuit().getControlQueue().getTrace()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 44:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getGenMultiplier()));
				case 45:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getDefaultDailyShapeObj().getName());
				case 46:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getDefaultYearlyShapeObj().getName());
				case 47:
					Globals.appendGlobalResult("Get function not applicable.");
				case 48:
					if (Globals.getActiveCircuit().isPositiveSequence()) {
						Globals.appendGlobalResult("positive");
					} else {
						Globals.appendGlobalResult("multiphase");
					}
				case 49:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getPriceSignal()));
				case 50:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getPriceCurve());
				case 51:
					Globals.appendGlobalResult(String.format("%d", Globals.getActiveCircuit().getActiveCktElement().getActiveTerminalIdx()));
				case 52:
					Globals.appendGlobalResult(String.format("%-g", Globals.getActiveCircuit().getFundamental()));
				case 53:
					SolutionObj sol = Globals.getActiveCircuit().getSolution();
					if (sol.isDoAllHarmonics()) {
						Globals.appendGlobalResult("ALL");
					} else {
						for (int i = 0; i < sol.getHarmonicListSize(); i++)
							Globals.appendGlobalResult(String.format("%-g", sol.getHarmonicList()[i]));
					}
				case 54:
					Globals.appendGlobalResult(String.valueOf(Globals.getActiveCircuit().getSolution().getMaxControlIterations()));
				case 55:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getBusList().get(Globals.getActiveCircuit().getActiveBusIndex()));
				case 56:
					Globals.appendGlobalResult(Globals.getDSSDataDirectory());
				case 57:
					ckt = Globals.getActiveCircuit();
					for (int i = 0; i < ckt.getNumBuses(); i++)
						if (ckt.getBuses()[i].isKeep())
							Globals.appendGlobalResult(ckt.getBusList().get(i));
				case 58:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getReductionStrategyString());
				case 59:
					if (Globals.getEnergyMeterClass().isSaveDemandInterval()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 60:
					Globals.appendGlobalResult(String.format("%-.g", Globals.getActiveCircuit().getPctNormalFactor()));
				case 61:
					if (Globals.getEnergyMeterClass().isDIVerbose()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 62:
					Globals.appendGlobalResult(Globals.getActiveCircuit().getCaseName());
				case 63:
					Globals.appendGlobalResult(String.format("%d", Globals.getActiveCircuit().getNodeMarkerCode()));
				case 64:
					Globals.appendGlobalResult(String.format("%d", Globals.getActiveCircuit().getNodeMarkerWidth()));
				case 65:
					if (Globals.getActiveCircuit().isLogEvents()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 66:
					if (DSSExecutive.getDSSExecutive().isRecorderOn()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 67:
					if (Globals.getEnergyMeterClass().isDo_OverloadReport()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 68:
					if (Globals.getEnergyMeterClass().isDo_VoltageExceptionReport()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 69:
					Globals.appendGlobalResult("Get function not applicable.");
				case 70:
					if (Globals.isAutoShowExport()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 71:
					Globals.appendGlobalResult(String.format("%d", Globals.getMaxAllocationIterations())) ;
				case 72:
					Globals.appendGlobalResult(String.format("%d", Globals.getDefaultBaseFreq()));
				case 73:
					if (Globals.getActiveCircuit().isMarkSwitches()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 74:
					Globals.appendGlobalResult(String.format("%d", Globals.getActiveCircuit().getSwitchMarkerCode()));
				case 75:
					Globals.appendGlobalResult(String.format("%-.6g", Globals.getDaisySize()));
				case 76:
					if (Globals.getActiveCircuit().isMarkTransformers()) {
						Globals.appendGlobalResult("Yes");
					} else {
						Globals.appendGlobalResult("No");
					}
				case 77:
					Globals.appendGlobalResult(String.format("%d", Globals.getActiveCircuit().getTransMarkerCode()));
				case 78:
					Globals.appendGlobalResult(String.format("%d", Globals.getActiveCircuit().getTransMarkerSize()));
				case 79:
					Globals.appendGlobalResult(Utilities.getActiveLoadShapeClass());
				case 80:
					Globals.appendGlobalResult(Utilities.getEarthModel(Globals.getDefaultEarthModel()));
				default:
					// Ignore excess parameters
				}

				ParamName = Parser.getInstance().getNextParam();
				Param = Parser.getInstance().makeString();
			}

		} catch (Exception e) {
			Globals.appendGlobalResult("***Error***");
		}

		return Result;
	}

}
