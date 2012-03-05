package com.ncond.dss.executive.impl;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.Solution;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.PriceShapeObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.impl.CommandListImpl;

public class ExecOptions {

	public final static int NumExecOptions = 82;

	private String[] execOption;
	private String[] optionHelp;

	private CommandList optionList;

	// private constructor prevents instantiation from other classes
	private ExecOptions() {
		defineOptions();

		optionList = new CommandListImpl(execOption);
		optionList.setAbbrevAllowed(true);
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

		execOption = new String[NumExecOptions];

		execOption[0]  = "type";
		execOption[1]  = "element";
		execOption[2]  = "hour";
		execOption[3]  = "sec";
		execOption[4]  = "year";
		execOption[5]  = "frequency";
		execOption[6]  = "stepsize";
		execOption[7]  = "mode";
		execOption[8]  = "random";
		execOption[9] =  "number";
		execOption[10] = "time";
		execOption[11] = "class";
		execOption[12] = "object";
		execOption[13] = "circuit";
		execOption[14] = "editor";
		execOption[15] = "tolerance";
		execOption[16] = "maxiterations";
		execOption[17] = "h";
		execOption[18] = "Loadmodel";
		execOption[19] = "Loadmult";
		execOption[20] = "normvminpu";
		execOption[21] = "normvmaxpu";
		execOption[22] = "emergvminpu";
		execOption[23] = "emergvmaxpu";
		execOption[24] = "%mean";
		execOption[25] = "%stddev";
		execOption[26] = "LDCurve";  // load duration curve
		execOption[27] = "%growth";  // default growth rate
		execOption[28] = "Genkw";
		execOption[29] = "Genpf";
		execOption[30] = "CapkVAR";
		execOption[31] = "Addtype";
		execOption[32] = "Allowduplicates";
		execOption[33] = "Zonelock";
		execOption[34] = "UEweight";
		execOption[35] = "Lossweight";
		execOption[36] = "UEregs";
		execOption[37] = "Lossregs";
		execOption[38] = "Voltagebases";  // changes the default voltage base rules
		execOption[39] = "Algorithm";     // changes the default voltage base rules
		execOption[40] = "Trapezoidal";
		execOption[41] = "Autobuslist";   // array of bus names to include in auto add solutions
		execOption[42] = "Controlmode";
		execOption[43] = "Tracecontrol";
		execOption[44] = "Genmult";
		execOption[45] = "Defaultdaily";
		execOption[46] = "Defaultyearly";
		execOption[47] = "Allocationfactors";
		execOption[48] = "Cktmodel";
		execOption[49] = "Pricesignal";
		execOption[50] = "Pricecurve";
		execOption[51] = "Terminal";
		execOption[52] = "Basefrequency";
		execOption[53] = "Harmonics";
		execOption[54] = "Maxcontroliter";
		execOption[55] = "Bus";
		execOption[56] = "Datapath";
		execOption[57] = "KeepList";
		execOption[58] = "ReduceOption";
		execOption[59] = "DemandInterval";
		execOption[60] = "%Normal";
		execOption[61] = "DIVerbose";
		execOption[62] = "Casename";
		execOption[63] = "Markercode";
		execOption[64] = "Nodewidth";
		execOption[65] = "Log";
		execOption[66] = "Recorder";
		execOption[67] = "Overloadreport";
		execOption[68] = "Voltexceptionreport";
		execOption[69] = "Cfactors";
		execOption[70] = "Showexport";
		execOption[71] = "Numallociterations";
		execOption[72] = "DefaultBaseFrequency";
		execOption[73] = "Markswitches";
		execOption[74] = "Switchmarkercode";
		execOption[75] = "Daisysize";
		execOption[76] = "Marktransformers";
		execOption[77] = "TransMarkerCode";
		execOption[78] = "TransMarkerSize";
		execOption[79] = "LoadShapeClass";
		execOption[80] = "EarthModel";
		execOption[81] = "QueryLog";

		final String CRLF = DSS.CRLF;

		optionHelp = new String[NumExecOptions];

		optionHelp[0]  = "Sets the active DSS class type.  Same as Class=...";
		optionHelp[1]  = "Sets the active DSS element by name. You can use "+
				"the complete object spec (class.name) or just the "+
				"name.  if full name is specifed, class becomes the active "+
				"class, also.";
		optionHelp[2]  = "Sets the hour used for the start time of the solution.";
		optionHelp[3]  = "Sets the seconds from the hour for the start time of the solution.";
		optionHelp[4]  = "Sets the Year (integer number) to be used for the solution. "+
				"for certain solution types, this determines the growth multiplier.";
		optionHelp[5]  = "Sets the frequency for the solution of the active circuit.";
		optionHelp[6]  = "Sets the time step size for the active circuit.  Default units are s. " +
						"May also be specified in minutes or hours by appending \"m\" or \"h\" to the value. For example:" + CRLF + CRLF +
						"   stepsize=.25h " + CRLF + "  stepsize=15m" + CRLF + "  stepsize=900s";
		optionHelp[7]  = "Set the solution Mode: One of"+
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
		optionHelp[8]  = "One of [Uniform | Gaussian | Lognormal | None ] for Monte Carlo Variables.";
		optionHelp[9] = "Number of solutions to perform for Monte Carlo or dutycycle solutions.";
		optionHelp[10] = "Specify the solution start time as an array:"+CRLF+
				"time=(hour, secs)";
		optionHelp[11] = "Synonym for Type=. (See above)";
		optionHelp[12] = "Synonym for Element=. (See above)";
		optionHelp[13] = "Set the active circuit by name.";
		optionHelp[14] = "Set the command string required to start up the editor preferred by the user. Does not require a circuit defined.";
		optionHelp[15] = "Sets the solution tolerance.  Default is 0.0001.";
		optionHelp[16] = "Sets the maximum allowable iterations for power flow solutions. Default is 15.";
		optionHelp[17] = "Alternate name for time step size.";
		optionHelp[18] = "{Powerflow | Admittance} depending on the type of solution you wish to perform. "+
				"If admittance, a non-iterative, direct solution is done with all loads and generators modeled by their "+
				"equivalent admittance.";
		optionHelp[19] = "Global load multiplier for this circuit.  Does not affect loads "+
				"designated to be \"fixed\".  All other base kW values are multiplied by this number. "+
				"Defaults to 1.0 when the circuit is created. As with other values, it always stays "+
				"at the last value to which it was set until changed again.";
		optionHelp[20] = "Minimum permissible per unit voltage for normal conditions. Default is 0.95.";
		optionHelp[21] = "Maximum permissible per unit voltage for normal conditions. Default is 1.05.";
		optionHelp[22] = "Minimum permissible per unit voltage for emergency (contingency) conditions. Default is 0.90.";
		optionHelp[23] = "Maximum permissible per unit voltage for emergency (contingency) conditions. Default is 1.08.";
		optionHelp[24] = "Percent mean to use for global load multiplier. Default is 65%.";
		optionHelp[25] = "Percent Standard deviation to use for global load multiplier. Default is 9%.";
		optionHelp[26] = "Set Load-Duration Curve. Global load multiplier is defined by this curve for LD1 and LD2 solution modes. Default is Nil.";
		optionHelp[27] = "Set default annual growth rate, percent, for loads with no growth curve specified. Default is 2.5.";
		optionHelp[28] = "Size of generator, kW, to automatically add to system. Default is 1000.0";
		optionHelp[29] = "Power factor of generator to assume for automatic addition. Default is 1.0.";
		optionHelp[30] = "Size of capacitor, kVAR, to automatically add to system.  Default is 600.0.";
		optionHelp[31] = "{Generator | Capacitor} Default is Generator. Type of device for AutoAdd Mode.";
		optionHelp[32] = "{YES/TRUE | NO/FALSE}   Default is No. Flag to indicate if it is OK to have devices of same name in the same class. " +
				"If No, then a New command is treated as an Edit command. "+
				"If Yes, then a New command will always result in a device being added.";
		optionHelp[33] = "{YES/TRUE | NO/FALSE}  Default is No. if No, then meter zones are recomputed each time there is a change in the circuit. "+
				"If Yes, then meter zones are not recomputed unless they have not yet been computed. "+
				"Meter zones are normally recomputed on Solve command following a circuit change.";
		optionHelp[34] = "Weighting factor for UE/EEN in AutoAdd functions.  Defaults to 1.0." + CRLF + CRLF +
				"Autoadd mode minimizes"  + CRLF + CRLF +
				"(Lossweight * Losses + UEweight * UE). " + CRLF + CRLF +
				"If you wish to ignore UE, set to 0. " +
				"This applies only when there are EnergyMeter objects. " +
				"Otherwise, AutoAdd mode minimizes total system losses.";
		optionHelp[35] = "Weighting factor for Losses in AutoAdd functions.  Defaults to 1.0." + CRLF+CRLF+
				"Autoadd mode minimizes"  + CRLF+CRLF+
				"(Lossweight * Losses + UEweight * UE). " + CRLF + CRLF +
				"If you wish to ignore Losses, set to 0. "+
				"This applies only when there are EnergyMeter objects. " +
				"Otherwise, AutoAdd mode minimizes total system losses.";
		optionHelp[36] = "Which EnergyMeter register(s) to use for UE in AutoAdd Mode. " +
				"May be one or more registers.  if more than one, register values are summed together. " +
				"Array of integer values > 0.  Defaults to 11 (for Load EEN). " + CRLF+CRLF+
				"for a list of EnergyMeter register numbers, do the \"Show Meters\" command after defining a circuit.";
		optionHelp[37] = "Which EnergyMeter register(s) to use for Losses in AutoAdd Mode. " +
				"May be one or more registers.  if more than one, register values are summed together. " +
				"Array of integer values > 0.  Defaults to 13 (for Zone kWh Losses). " +  CRLF+CRLF+
				"for a list of EnergyMeter register numbers, do the \"Show Meters\" command after defining a circuit.";
		optionHelp[38] = "Define legal bus voltage bases for this circuit.  Enter an array "+
				"of the legal voltage bases, in phase-to-phase voltages, for example:" +CRLF+CRLF+
				"set voltagebases=\".208, .480, 12.47, 24.9, 34.5, 115.0, 230.0\" "+CRLF+CRLF+
				"When the CalcVoltageBases command is issued, a snapshot solution is performed "+
				"with no load injections and the bus base voltage is set to the nearest legal voltage base. "+
				"The defaults are as shown in the example above.";
		optionHelp[39] = "{Normal | Newton}  Solution algorithm type.  Normal is a fixed point iteration " +
				"that is a little quicker than the Newton iteration.  Normal is adequate for most radial "+
				"distribution circuits.  Newton is more robust for circuits that are difficult to solve.";
		optionHelp[40] = "{YES/TRUE | NO/FALSE}  Default is \"No\". Specifies whether to use trapezoidal integration for accumulating energy meter registers. " +
				"Applies to EnergyMeter and Generator objects.  Default method simply multiplies the " +
				"present value of the registers times the width of the interval. " +
				"Trapezoidal is more accurate when there are sharp changes in a load shape or unequal intervals. " +
				"Trapezoidal is automatically used for " +
				"some load-duration curve simulations where the interval size varies considerably. " +
				"Keep in mind that for Trapezoidal, you have to solve one more point than the number of intervals. " +
				"That is, to do a Daily simulation on a 24-hr load shape, you would set Number=25 to force a solution " +
				"at the first point again to establish the last (24th) interval.";
		optionHelp[41] = "Array of bus names to include in AutoAdd searches. Or, you can specify a text file holding the names, one to a line, " +
				"by using the syntax (file=filename) instead of the actual array elements. "  +
				"Default is null, which results in the program " +
				"using either the buses in the EnergyMeter object zones or, if no EnergyMeters, all the buses, which can " +
				"make for lengthy solution times. " +CRLF+CRLF+
				"Examples:"+CRLF+CRLF+
				"Set autobuslist=(bus1, bus2, bus3, ... )" +CRLF+
				"Set autobuslist=(file=buslist.txt)";
		optionHelp[42] = "{OFF | STATIC |EVENT | TIME}  Default is \"STATIC\".  Control mode for the solution. " +
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
		optionHelp[43] = "{YES/TRUE | NO/FALSE}  Set to YES to trace the actions taken in the control queue.  "  +
				"Creates a file named TRACE_CONTROLQUEUE.CSV in the default directory. " +
				"The names of all circuit elements taking an action are logged.";
		optionHelp[44] = "Global multiplier for the kW output of every generator in the circuit. Default is 1.0. " +
				"Applies to all but Autoadd solution modes. " +
				"Ignored for generators designated as Status=Fixed.";
		optionHelp[45] = "Default daily load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.";
		optionHelp[46] = "Default yearly load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.";
		optionHelp[47] = "Sets the connected kVA allocation factors for all loads in the active circuit to the value given.";
		optionHelp[48] = "{Multiphase | Positive}  Default = Multiphase.  Designates whether circuit model is to interpreted as a normal multi-phase "+
				"model or a positive-sequence only model";
		optionHelp[49] = "Sets the present price signal ($/MWh) for the circuit.  Default value is 25.";
		optionHelp[50] = "Sets the PRICESHAPE object to use to obtain for price signal. Default is none (null string). If none, " +
				"price signal either remains constant or is set by an external process using Set Price= option. " +
				"Curve is defined as a PRICESHAPE in actual values (not normalized) and should be defined to correspond to " +
				"the type of analysis being performed (daily, yearly, load-duration, etc.).";
		optionHelp[51] = "Set the active terminal of the active circuit element. May also be done with Select command.";
		optionHelp[52] = "Default = 60. Set the fundamental frequency for harmonic solution and the default base frequency for all impedance quantities. " +
				"Side effect: also changes the value of the solution frequency. Saved as default for next circuit.";
		optionHelp[53] = "{ALL | (list of harmonics) }  Default = ALL. Array of harmonics for which to perform a solution in Harmonics mode. " +
				"If ALL, then solution is performed for all harmonics defined in spectra currently being used. " +
				"Otherwise, specify a more limited list such as: " +CRLF+CRLF+
				"   Set Harmonics=(1 5 7 11 13)";
		optionHelp[54] = "Max control iterations per solution.  Default is 10.";
		optionHelp[55] = "Set Active Bus by name.  Can also be done with Select and SetkVBase commands and the \"Set Terminal=\"  option. " +
				"The bus connected to the active terminal becomes the active bus. See Zsc and Zsc012 commands.";
		optionHelp[56] = "Set the data path for files written or read by the DSS.  Defaults to the startup path. May be Null.  Executes a CHDIR to this path if non-null. Does not require a circuit defined.";
		optionHelp[57] = "Array of bus names to keep when performing circuit reductions. You can specify a text file holding the names, one to a line, " +
				"by using the syntax (file=filename) instead of the actual array elements. "  +
				"Command is cumulative (reset keeplist first). " +
				"Reduction algorithm may keep other buses automatically. " +CRLF+CRLF+
				"Examples:"+CRLF+CRLF+
				"Reset Keeplist (sets all buses to FALSE (no keep))" +CRLF+
				"Set KeepList=(bus1, bus2, bus3, ... )" +CRLF+
				"Set KeepList=(file=buslist.txt)";
		optionHelp[58] = "{ Default or [null] | Stubs [Zmag=nnn] | MergeParallel | BreakLoops | Switches | TapEnds [maxangle=nnn] | Ends}  Strategy for reducing feeders. " +
				"Default is to eliminate all dangling end buses and buses without load, caps, or taps. " +  CRLF +
				"\"Stubs [Zmag=0.02]\" merges short branches with impedance less than Zmag (default = 0.02 ohms) " + CRLF +
				"\"MergeParallel\" merges lines that have been found to be in parallel " +CRLF+
				"\"Breakloops\" disables one of the lines at the head of a loop. " +CRLF+
				"\"Tapends [maxangle=15]\" eliminates all buses except those at the feeder ends, at tap points and where the feeder turns by greater than maxangle degrees. " + CRLF+
				"\"Ends\" eliminates dangling ends only."+CRLF+
				"\"Switches\" merges switches with downline lines and eliminates dangling switches."+CRLF+
				"Marking buses with \"Keeplist\" will prevent their elimination.";
		optionHelp[59] = "{YES/TRUE | NO/FALSE} Default = no. Set for keeping demand interval data for daily, yearly, etc, simulations. "+
				"Side Effect:  Resets all meters!!!";
		optionHelp[60] = "Sets the Normal rating of all lines to a specified percent of the emergency rating.  Note: This action takes place immediately. "+
				"Only the in-memory value is changed for the duration of the run.";
		optionHelp[61] = "{YES/TRUE | NO/FALSE} Default = FALSE.  Set to Yes/True if you wish a separate demand interval (DI) file written " +
				"for each meter.  Otherwise, only the totalizing meters are written.";
		optionHelp[62] = "Name of case for yearly simulations with demand interval data. "+
				"Becomes the name of the subdirectory under which all the year data are stored. "+
				"Default = circuit name "+CRLF+CRLF+
				"Side Effect: Sets the prefix for output files";
		optionHelp[63] = "Number code for node marker on circuit plots. Number from 0 to 47. Default is 16 (open circle). 24 is solid circle. Try other values for other symbols. See also Nodewidth";
		optionHelp[64] = "Width of node marker. Default=1. See MarkerCode";
		optionHelp[65] = "{YES/TRUE | NO/FALSE} Default = FALSE.  Significant solution events are added to the Event Log, primarily for debugging.";
		optionHelp[66] = "{YES/TRUE | NO/FALSE} Default = FALSE. Opens DSSRecorder.DSS in DSS install folder and enables recording of all commands that come through " +
				"the text command interface. Closed by either setting to NO/FALSE or exiting the program. " +
				"When closed by this command, the file name can be found in the Result. Does not require a circuit defined.";
		optionHelp[67] = "{YES/TRUE | NO/FALSE} Default = FALSE. For yearly solution mode, sets overload reporting on/off. DemandInterval must be set to true for this to have effect.";
		optionHelp[68] = "{YES/TRUE | NO/FALSE} Default = FALSE. For yearly solution mode, sets voltage exception reporting on/off. DemandInterval must be set to true for this to have effect.";
		optionHelp[69] = "Sets the CFactors for for all loads in the active circuit to the value given.";
		optionHelp[70] = "{YES/TRUE | NO/FALSE} Default = FALSE. If YES/TRUE will automatically show the results of an Export Command after it is written.";
		optionHelp[71] = "Default is 2. Maximum number of iterations for load allocations for each time the AllocateLoads or Estimate command is given.";
		optionHelp[72] = "Set Default Base Frequency, Hz. Side effect: Sets solution Frequency and default Circuit Base Frequency. This value is saved when the DSS closes down.";
		optionHelp[73] = "{YES/TRUE | NO/FALSE}  Default is NO. Mark lines that are switches or are isolated with a symbol. See SwitchMarkerCode.";
		optionHelp[74] = "Numeric marker code for lines with switches or are isolated from the circuit. Default is 4. See markswitches option.";
		optionHelp[75] = "Default is 1.0. Relative size (a multiplier applied to default size) of daisy circles on daisy plot.";
		optionHelp[76] = "{YES/TRUE | NO/FALSE}  Default is NO. Mark transformer locations with a symbol. See TransMarkerCode. " +
				"The coordinate of one of the buses for winding 1 or 2 must be defined for the symbol to show";
		optionHelp[77] = "Numeric marker code for transformers. Default is 35. See markstransformers option.";
		optionHelp[78] = "Size of transformer marker. Default is 1.";
		optionHelp[79] = "={Daily | Yearly | Duty | None*} Default loadshape class to use for mode=time and mode=dynamic simulations. Loads and generators, etc., will follow " +
				"this shape as time is advanced. Default value is None. That is, Load will not vary with time.";
		optionHelp[80] = "One of {Carson | FullCarson | Deri*}.  Default is Deri, which is" +
				"a  fit to the Full Carson that works well into high frequencies. " +
				"\"Carson\" is the simplified Carson method that is typically used for 50/60 Hz power flow programs. " +
				"Applies only to Line objects that use LineGeometry objects to compute impedances.";
		optionHelp[81] = "{YES/TRUE | NO/FALSE} Default = FALSE. When set to TRUE/YES, clears the query log file and thereafter appends " +
				"the time-stamped Result string contents to the log file after a query command, ?. ";

	}

	public String[] getExecOption() {
		return execOption;
	}

	public void setExecOption(String[] values) {
		execOption = values;
	}

	public String[] getOptionHelp() {
		return optionHelp;
	}

	public void setOptionHelp(String[] values) {
		optionHelp = values;
	}

	public String getExecOption(int i) {
		return execOption[i];
	}

	public String getOptionHelp(int i) {
		return optionHelp[i];
	}

	public CommandList getOptionList() {
		return optionList;
	}

	public void setOptionList(CommandList list) {
		optionList = list;
	}

	/**
	 * Set commands that do not require a circuit.
	 *
	 * This is for setting global options that do not require an active circuit.
	 */
	public boolean doSetCmd_NoCircuit() {
		boolean result = true;

		Parser parser = Parser.getInstance();

		// Continue parsing command line
		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = optionList.getCommand(paramName);
			}

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Set command ", 130);
				break;
			case 14:
				DSS.defaultEditor = param;  // 'editor='
				break;
			case 56:
				DSS.setDataPath(param);  // set a legal data path
				break;
			case 66:
				DSSExecutive.getInstance().setRecorderOn(Util.interpretYesNo(param));
				break;
			case 72:
				DSS.defaultBaseFreq = parser.makeDouble();
				break;
			default:
				DSS.doSimpleMsg("You must create a new circuit object first: \"new circuit.mycktname\" to execute this Set command.", 301);
				result = false;  // indicate that we could not process all set command
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		return result;
	}

	/**
	 * Set DSS options.
	 *
	 * Solve command is re-routed here first to set options before solving.
	 */
	public int doSetCmd(int solveOption) {
		LoadShapeObj testLoadShapeObj;
		int[] numRegs;

		Parser parser = Parser.getInstance();
		Circuit ckt;
		SolutionObj solution;

		int result = 0;
		// continue parsing command line
		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = optionList.getCommand(paramName);
			}

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Set command ", 130);
				break;
			case 0:
				DSSClassDefs.setObjectClass(param);
				break;
			case 11:
				DSSClassDefs.setObjectClass(param);
				break;
			case 1:
				DSS.setObject(param);
				break;
			case 12:
				DSS.setObject(param);
				break;
			case 2:
				DSS.activeCircuit.getSolution().setIntHour(parser.makeInteger());
				break;
			case 3:
				DSS.activeCircuit.getSolution().getDynaVars().t = parser.makeDouble();
				break;
			case 4:
				ckt = DSS.activeCircuit;
				ckt.getSolution().setYear(parser.makeInteger());
				ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1));
				break;
			case 5:
				DSS.activeCircuit.getSolution().setFrequency(parser.makeDouble());
				break;
			case 6:
				DSS.activeCircuit.getSolution().getDynaVars().h = Util.interpretTimeStepSize(param);
				break;
			case 17:
				DSS.activeCircuit.getSolution().getDynaVars().h = Util.interpretTimeStepSize(param);
				break;
			case 7:
				DSS.activeCircuit.getSolution().setMode( Util.interpretSolveMode(param) );  // see DSSGlobals
				break;
			case 8:
				DSS.activeCircuit.getSolution().setRandomType( Util.interpretRandom(param) );
				break;
			case 9:
				DSS.activeCircuit.getSolution().setNumberOfTimes(parser.makeInteger());
				break;
			case 10:
				ExecHelper.setTime();
				break;
			case 13:
				ExecHelper.setActiveCircuit(param);
				break;
			case 14:
				DSS.defaultEditor = param;  // 'Editor='
				break;
			case 15:
				DSS.activeCircuit.getSolution().setConvergenceTolerance(parser.makeDouble());
				break;
			case 16:
				DSS.activeCircuit.getSolution().setMaxIterations(parser.makeInteger());
				break;
			case 18:
				solution = DSS.activeCircuit.getSolution();
				solution.setDefaultLoadModel(Util.interpretLoadModel(param)); // for reverting to last on specified
				solution.setLoadModel(solution.getDefaultLoadModel());
				break;
			case 19:
				DSS.activeCircuit.setLoadMultiplier(parser.makeDouble());  // set using loadMultiplier property
				break;
			case 20:
				DSS.activeCircuit.setNormalMinVolts(parser.makeDouble());
				break;
			case 21:
				DSS.activeCircuit.setNormalMaxVolts(parser.makeDouble());
				break;
			case 22:
				DSS.activeCircuit.setEmergMinVolts(parser.makeDouble());
				break;
			case 23:
				DSS.activeCircuit.setEmergMaxVolts(parser.makeDouble());
				break;
			case 24:
				DSS.activeCircuit.getDefaultDailyShapeObj().setMean(parser.makeDouble() / 100.0);
				break;
			case 25:
				DSS.activeCircuit.getDefaultDailyShapeObj().setStdDev(parser.makeDouble() / 100.0);
				break;
			case 26:
				ckt = DSS.activeCircuit;
				ckt.setLoadDurCurve(param);
				ckt.setLoadDurCurveObj((LoadShapeObj) DSS.loadShapeClass.find(param));
				if (ckt.getLoadDurCurveObj() == null)
					DSS.doSimpleMsg("Load-duration curve not found.", 131);
				break;
			case 27:
				ckt = DSS.activeCircuit;
				ckt.setDefaultGrowthRate(1.0 + parser.makeDouble() / 100.0);
				ckt.setDefaultGrowthFactor( Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1) );
				break;
			case 28:
				DSS.activeCircuit.getAutoAddObj().setGenKW(parser.makeDouble());
				break;
			case 29:
				DSS.activeCircuit.getAutoAddObj().setGenPF(parser.makeDouble());
				break;
			case 30:
				DSS.activeCircuit.getAutoAddObj().setCapKVAr(parser.makeDouble());
				break;
			case 31:
				DSS.activeCircuit.getAutoAddObj().setAddType(Util.interpretAddType(param));
				break;
			case 32:
				DSS.activeCircuit.setDuplicatesAllowed(Util.interpretYesNo(param));
				break;
			case 33:
				DSS.activeCircuit.setZonesLocked(Util.interpretYesNo(param));
				break;
			case 34:
				DSS.activeCircuit.setUEWeight(parser.makeDouble());
				break;
			case 35:
				DSS.activeCircuit.setLossWeight(parser.makeDouble());
				break;
			case 36:
				numRegs = new int[1];
				DSS.activeCircuit.setUERegs(
						Util.parseIntArray(DSS.activeCircuit.getUERegs(), numRegs, param)
				);
				DSS.activeCircuit.setNumUERegs(numRegs[0]);
				break;
			case 37:
				numRegs = new int[1];
				DSS.activeCircuit.setLossRegs(
						Util.parseIntArray(DSS.activeCircuit.getLossRegs(), numRegs, param)
				);
				DSS.activeCircuit.setNumLossRegs(numRegs[0]);
				break;
			case 38:
				ExecHelper.doLegalVoltageBases();
				break;
			case 39:
				DSS.activeCircuit.getSolution().setAlgorithm(Util.interpretSolveAlg(param));
				break;
			case 40:
				DSS.activeCircuit.setTrapezoidalIntegration(Util.interpretYesNo(param));
				break;
			case 41:
				ExecHelper.doAutoAddBusList(param);
				break;
			case 42:
				solution = DSS.activeCircuit.getSolution();
				solution.setControlMode(Util.interpretControlMode(param));
				solution.setDefaultControlMode(solution.getControlMode());  // always revert to last one specified in a script
				break;
			case 43:
				DSS.activeCircuit.getControlQueue().setTrace(Util.interpretYesNo(param));
				break;
			case 44:
				DSS.activeCircuit.setGenMultiplier(parser.makeDouble());
				break;
			case 45:
				testLoadShapeObj = (LoadShapeObj) DSS.loadShapeClass.find(param);
				if (testLoadShapeObj != null)
					DSS.activeCircuit.setDefaultDailyShapeObj(testLoadShapeObj);
				break;
			case 46:
				testLoadShapeObj = (LoadShapeObj) DSS.loadShapeClass.find(param);
				if (testLoadShapeObj != null)
					DSS.activeCircuit.setDefaultYearlyShapeObj(testLoadShapeObj);
				break;
			case 47:
				ExecHelper.doSetAllocationFactors(parser.makeDouble());
				break;
			case 48:
				DSS.activeCircuit.setPositiveSequence(Util.interpretCktModel(param));
				break;
			case 49:
				DSS.activeCircuit.setPriceSignal(parser.makeDouble());
				break;
			case 50:
				ckt = DSS.activeCircuit;
				ckt.setPriceCurve(param);
				ckt.setPriceCurveObj((PriceShapeObj) DSS.loadShapeClass.find(param));
				if (ckt.getPriceCurveObj() == null)
					DSS.doSimpleMsg("PriceShape: \"" +param+ "\" not found.", 132);
				break;
			case 51:
				ckt = DSS.activeCircuit;
				if (ckt.getActiveCktElement() != null) {
					CktElement cktElem = ckt.getActiveCktElement();
					cktElem.setActiveTerminalIdx(parser.makeInteger());
					DSS.setActiveBus( Util.stripExtension(cktElem.getBus(cktElem.getActiveTerminalIdx())) );  // bus connected to terminal
				}
				break;
			case 52:
				DSS.activeCircuit.setFundamental(parser.makeDouble());  // set base frequency for system (used henceforth)
				DSS.activeCircuit.getSolution().setFrequency(parser.makeDouble());
				break;
			case 53:
				ExecHelper.doHarmonicsList(param);
				break;
			case 54:
				DSS.activeCircuit.getSolution().setMaxControlIterations(parser.makeInteger());
				break;
			case 55:
				result = DSS.setActiveBus(param);  // see DSSGlobals
				break;
			case 56:
				DSS.setDataPath(param);  // set a legal data path
				break;
			case 57:
				ExecHelper.doKeeperBusList(param);
				break;
			case 58:
				ExecHelper.doSetReduceStrategy(param);
				break;
			case 59:
				DSS.energyMeterClass.setSaveDemandInterval(Util.interpretYesNo(param));
				break;
			case 60:
				DSS.activeCircuit.setPctNormalFactor(parser.makeDouble());
				ExecHelper.doSetNormal(DSS.activeCircuit.getPctNormalFactor());
				break;
			case 61:
				DSS.energyMeterClass.setDIVerbose(Util.interpretYesNo(param));
				break;
			case 62:
				DSS.activeCircuit.setCaseName(parser.makeString());
				break;
			case 63:
				DSS.activeCircuit.setNodeMarkerCode(parser.makeInteger());
				break;
			case 64:
				DSS.activeCircuit.setNodeMarkerWidth(parser.makeInteger());
				break;
			case 65:
				DSS.activeCircuit.setLogEvents(Util.interpretYesNo(param));
				break;
			case 66:
				DSSExecutive.getInstance().setRecorderOn(Util.interpretYesNo(param));
				break;
			case 67:
				DSS.energyMeterClass.setDoOverloadReport(Util.interpretYesNo(param));
				break;
			case 68:
				DSS.energyMeterClass.setDoVoltageExceptionReport(Util.interpretYesNo(param));
				break;
			case 69:
				ExecHelper.doSetCFactors(parser.makeDouble());
				break;
			case 70:
				DSS.autoShowExport = Util.interpretYesNo(param);
				break;
			case 71:
				DSS.maxAllocationIterations = parser.makeInteger();
				break;
			case 72:
				DSS.defaultBaseFreq = parser.makeDouble();
				DSS.activeCircuit.setFundamental(parser.makeDouble());  // set base frequency for system (used henceforth)
				DSS.activeCircuit.getSolution().setFrequency(parser.makeDouble());
				break;
			case 73:
				DSS.activeCircuit.setMarkSwitches(Util.interpretYesNo(param));
				break;
			case 74:
				DSS.activeCircuit.setSwitchMarkerCode(parser.makeInteger());
				break;
			case 75:
				DSS.daisySize = parser.makeDouble();
				break;
			case 76:
				DSS.activeCircuit.setMarkTransformers(Util.interpretYesNo(param));
				break;
			case 77:
				DSS.activeCircuit.setTransMarkerCode(parser.makeInteger());
				break;
			case 78:
				DSS.activeCircuit.setTransMarkerSize(parser.makeInteger());
				break;
			case 79:
				DSS.activeCircuit.setActiveLoadShapeClass(Util.interpretLoadShapeClass(param));
				break;
			case 80:
				DSS.defaultEarthModel = Util.interpretEarthModel(param);
				break;
			case 81:
				DSS.logQueries = Util.interpretYesNo(param);
				if (DSS.logQueries)
					DSS.resetQueryLogFile();
			default:
				// ignore excess parameters
				break;
			}

			switch (paramPointer) {
			case 3:
				DSS.activeCircuit.getSolution().updateDblHour();
				break;
			case 4:
				DSS.activeCircuit.getSolution().updateDblHour();
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (solveOption == 1)
			ExecHelper.doSolveCmd();

		return result;
	}

	/**
	 * Get DSS options reguaeste and put it in global result string
	 * may be retrieved by result property of the DSSText interface.
	 */
	public int doGetCmd() {
		int result = 0;
		int paramPointer;
		Circuit ckt;

		try {
			DSS.globalResult = "";  // initialize for appending

			// continue parsing command line
			String paramName = Parser.getInstance().getNextParam();
			String param = Parser.getInstance().makeString();
			// there will be no named parameters in this command and the params
			// themselves will be the parameter name to return
			while (param.length() > 0) {
				paramPointer = optionList.getCommand(param);

				switch (paramPointer) {
				case -1:
					DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Get command ", 133);
					break;
				case 0:
					DSS.appendGlobalResult(DSS.activeCircuit.getActiveCktElement().getDSSClassName());
					break;
				case 11:
					DSS.appendGlobalResult(DSS.activeCircuit.getActiveCktElement().getDSSClassName());
					break;
				case 1:
					DSS.appendGlobalResult(DSS.activeCircuit.getActiveCktElement().getName());
					break;
				case 12:
					DSS.appendGlobalResult(DSS.activeCircuit.getActiveCktElement().getName());
					break;
				case 2:
					DSS.appendGlobalResult(String.valueOf(DSS.activeCircuit.getSolution().getIntHour()));
					break;
				case 3:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getSolution().getDynaVars().t));
					break;
				case 4:
					DSS.appendGlobalResult(String.valueOf(DSS.activeCircuit.getSolution().getYear()));
					break;
				case 5:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getSolution().getFrequency()));
					break;
				case 6:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getSolution().getDynaVars().h));
					break;
				case 17:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getSolution().getDynaVars().h));
					break;
				case 7:
					DSS.appendGlobalResult(Util.getSolutionModeID());
					break;
				case 8:
					DSS.appendGlobalResult(Util.getRandomModeID());
					break;
				case 9:
					DSS.appendGlobalResult(String.valueOf(DSS.activeCircuit.getSolution().getNumberOfTimes()));
					break;
				case 10:
					DSS.appendGlobalResult(String.format("[ %d, %-g ]", String.valueOf(DSS.activeCircuit.getSolution().getIntHour()), DSS.activeCircuit.getSolution().getDynaVars().t));
					break;
				case 13:
					DSS.appendGlobalResult(DSS.activeCircuit.getName());
					break;
				case 14:
					DSS.appendGlobalResult(DSS.defaultEditor);
					break;
				case 15:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getSolution().getConvergenceTolerance()));
					break;
				case 16:
					DSS.appendGlobalResult(String.valueOf(DSS.activeCircuit.getSolution().getMaxIterations()));
					break;
				case 18:
					DSS.appendGlobalResult(Util.getLoadModel());
					break;
				case 19:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getLoadMultiplier()));
					break;
				case 20:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getNormalMinVolts()));
					break;
				case 21:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getNormalMaxVolts()));
					break;
				case 22:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getEmergMinVolts()));
					break;
				case 23:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getEmergMaxVolts()));
					break;
				case 24:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getDefaultDailyShapeObj().getMean() * 100.0));
					break;
				case 25:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getDefaultDailyShapeObj().getStdDev() * 100.0));
					break;
				case 26:
					DSS.appendGlobalResult(DSS.activeCircuit.getLoadDurCurve());
					break;
				case 27:
					DSS.appendGlobalResult(String.format("%-g", (DSS.activeCircuit.getDefaultGrowthRate() - 1.0) * 100.0));
					break;
				case 28:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getAutoAddObj().getGenKW()));
					break;
				case 29:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getAutoAddObj().getGenPF()));
					break;
				case 30:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getAutoAddObj().getCapKVAr()));
					break;
				case 31:
					switch (DSS.activeCircuit.getAutoAddObj().getAddType()) {
					case DSS.GENADD:
						DSS.appendGlobalResult("generator");
						break;
					case DSS.CAPADD:
						DSS.appendGlobalResult("capacitor");
						break;
					}
					break;
				case 32:
					if (DSS.activeCircuit.isDuplicatesAllowed()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 33:
					if (DSS.activeCircuit.isZonesLocked()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 34:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getUEWeight()));
					break;
				case 35:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getLossWeight()));
					break;
				case 36:
					DSS.appendGlobalResult(Util.intArrayToString(DSS.activeCircuit.getUERegs(), DSS.activeCircuit.getNumUERegs()));
					break;
				case 37:
					DSS.appendGlobalResult(Util.intArrayToString(DSS.activeCircuit.getLossRegs(), DSS.activeCircuit.getNumLossRegs()));
					break;
				case 38:
					ckt = DSS.activeCircuit;
					DSS.globalResult = "(";
					for (double vBase : ckt.getLegalVoltageBases())
						DSS.globalResult = DSS.globalResult + String.format("%-g, ", vBase);
					DSS.globalResult = DSS.globalResult + ")";
					break;
				case 39:
					switch (DSS.activeCircuit.getSolution().getAlgorithm()) {
					case Solution.NORMALSOLVE:
						DSS.appendGlobalResult("normal");
						break;
					case Solution.NEWTONSOLVE:
						DSS.appendGlobalResult("newton");
						break;
					}
					break;
				case 40:
					if (DSS.activeCircuit.isTrapezoidalIntegration()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 41:
					for (int i = 0; i < DSS.activeCircuit.getAutoAddBusList().listSize(); i++)
						DSS.appendGlobalResult(DSS.activeCircuit.getAutoAddBusList().get(i));
					break;
				case 42:
					DSS.appendGlobalResult(Util.getControlModeID());
					break;
				case 43:
					if (DSS.activeCircuit.getControlQueue().getTrace()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 44:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getGenMultiplier()));
					break;
				case 45:
					DSS.appendGlobalResult(DSS.activeCircuit.getDefaultDailyShapeObj().getName());
					break;
				case 46:
					DSS.appendGlobalResult(DSS.activeCircuit.getDefaultYearlyShapeObj().getName());
					break;
				case 47:
					DSS.appendGlobalResult("Get function not applicable.");
					break;
				case 48:
					if (DSS.activeCircuit.isPositiveSequence()) {
						DSS.appendGlobalResult("positive");
					} else {
						DSS.appendGlobalResult("multiphase");
					}
					break;
				case 49:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getPriceSignal()));
					break;
				case 50:
					DSS.appendGlobalResult(DSS.activeCircuit.getPriceCurve());
					break;
				case 51:
					DSS.appendGlobalResult(String.format("%d", DSS.activeCircuit.getActiveCktElement().getActiveTerminalIdx()));
					break;
				case 52:
					DSS.appendGlobalResult(String.format("%-g", DSS.activeCircuit.getFundamental()));
					break;
				case 53:
					SolutionObj sol = DSS.activeCircuit.getSolution();
					if (sol.isDoAllHarmonics()) {
						DSS.appendGlobalResult("ALL");
					} else {
						for (int i = 0; i < sol.getHarmonicListSize(); i++)
							DSS.appendGlobalResult(String.format("%-g", sol.getHarmonicList()[i]));
					}
					break;
				case 54:
					DSS.appendGlobalResult(String.valueOf(DSS.activeCircuit.getSolution().getMaxControlIterations()));
					break;
				case 55:
					DSS.appendGlobalResult(DSS.activeCircuit.getBusList().get(DSS.activeCircuit.getActiveBusIndex()));
					break;
				case 56:
					DSS.appendGlobalResult(DSS.DSSDataDirectory);
					break;
				case 57:
					ckt = DSS.activeCircuit;
					for (int i = 0; i < ckt.getNumBuses(); i++)
						if (ckt.getBus(i).isKeep())
							DSS.appendGlobalResult(ckt.getBusList().get(i));
					break;
				case 58:
					DSS.appendGlobalResult(DSS.activeCircuit.getReductionStrategyString());
					break;
				case 59:
					if (DSS.energyMeterClass.isSaveDemandInterval()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 60:
					DSS.appendGlobalResult(String.format("%-.g", DSS.activeCircuit.getPctNormalFactor()));
					break;
				case 61:
					if (DSS.energyMeterClass.isDIVerbose()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 62:
					DSS.appendGlobalResult(DSS.activeCircuit.getCaseName());
					break;
				case 63:
					DSS.appendGlobalResult(String.format("%d", DSS.activeCircuit.getNodeMarkerCode()));
					break;
				case 64:
					DSS.appendGlobalResult(String.format("%d", DSS.activeCircuit.getNodeMarkerWidth()));
					break;
				case 65:
					if (DSS.activeCircuit.isLogEvents()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 66:
					if (DSSExecutive.getInstance().isRecorderOn()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 67:
					if (DSS.energyMeterClass.isDo_OverloadReport()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 68:
					if (DSS.energyMeterClass.isDo_VoltageExceptionReport()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 69:
					DSS.appendGlobalResult("Get function not applicable.");
					break;
				case 70:
					if (DSS.autoShowExport) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 71:
					DSS.appendGlobalResult(String.format("%d", DSS.maxAllocationIterations)) ;
					break;
				case 72:
					DSS.appendGlobalResult(String.format("%d", DSS.defaultBaseFreq));
					break;
				case 73:
					if (DSS.activeCircuit.isMarkSwitches()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 74:
					DSS.appendGlobalResult(String.format("%d", DSS.activeCircuit.getSwitchMarkerCode()));
					break;
				case 75:
					DSS.appendGlobalResult(String.format("%-.6g", DSS.daisySize));
					break;
				case 76:
					if (DSS.activeCircuit.isMarkTransformers()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 77:
					DSS.appendGlobalResult(String.format("%d", DSS.activeCircuit.getTransMarkerCode()));
					break;
				case 78:
					DSS.appendGlobalResult(String.format("%d", DSS.activeCircuit.getTransMarkerSize()));
					break;
				case 79:
					DSS.appendGlobalResult(Util.getActiveLoadShapeClass());
					break;
				case 80:
					DSS.appendGlobalResult(Util.getEarthModel(DSS.defaultEarthModel));
					break;
				case 81:
					if (DSS.logQueries) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
				default:
					// ignore excess parameters
					break;
				}

				paramName = Parser.getInstance().getNextParam();
				param = Parser.getInstance().makeString();
			}

		} catch (Exception e) {
			DSS.appendGlobalResult("***Error***");
		}

		return result;
	}

}
