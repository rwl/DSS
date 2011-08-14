package com.epri.dss.executive.impl;

import org.apache.commons.lang.mutable.MutableInt;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.general.PriceShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.impl.CommandListImpl;

public class ExecOptions {

	public final static int NumExecOptions = 79;

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

		final String CRLF = DSSGlobals.CRLF;

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
		DSSGlobals globals = DSSGlobals.getInstance();

		// Continue parsing command line
		int paramPointer = 0;
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
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Set command ", 130);
				break;
			case 14:
				globals.setDefaultEditor(param);  // 'Editor='
				break;
			case 56:
				globals.setDataPath(param);  // set a legal data path
				break;
			case 66:
				DSSExecutive.getInstance().setRecorderOn(Utilities.interpretYesNo(param));
				break;
			case 72:
				globals.setDefaultBaseFreq(parser.makeDouble());
				break;
			default:
				globals.doSimpleMsg("You must create a new circuit object first: \"new circuit.mycktname\" to execute this Set command.", 301);
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
		MutableInt numRegs;

		Parser parser = Parser.getInstance();
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt;
		SolutionObj solution;

		int result = 0;
		// continue parsing command line
		int paramPointer = 0;
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
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Set command ", 130);
				break;
			case 0:
				DSSClassDefs.setObjectClass(param);
				break;
			case 11:
				DSSClassDefs.setObjectClass(param);
				break;
			case 1:
				globals.setObject(param);
				break;
			case 12:
				globals.setObject(param);
				break;
			case 2:
				globals.getActiveCircuit().getSolution().setIntHour(parser.makeInteger());
				break;
			case 3:
				globals.getActiveCircuit().getSolution().getDynaVars().t = parser.makeDouble();
				break;
			case 4:
				ckt = globals.getActiveCircuit();
				ckt.getSolution().setYear(parser.makeInteger());
				ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1));
				break;
			case 5:
				globals.getActiveCircuit().getSolution().setFrequency(parser.makeDouble());
				break;
			case 6:
				globals.getActiveCircuit().getSolution().getDynaVars().h = Utilities.interpretTimeStepSize(param);
				break;
			case 17:
				globals.getActiveCircuit().getSolution().getDynaVars().h = Utilities.interpretTimeStepSize(param);
				break;
			case 7:
				globals.getActiveCircuit().getSolution().setMode( Utilities.interpretSolveMode(param) );  // see DSSGlobals
				break;
			case 8:
				globals.getActiveCircuit().getSolution().setRandomType( Utilities.interpretRandom(param) );
				break;
			case 9:
				globals.getActiveCircuit().getSolution().setNumberOfTimes(parser.makeInteger());
				break;
			case 10:
				ExecHelper.setTime();
				break;
			case 13:
				ExecHelper.setActiveCircuit(param);
				break;
			case 14:
				globals.setDefaultEditor(param);  // 'Editor='
				break;
			case 15:
				globals.getActiveCircuit().getSolution().setConvergenceTolerance(parser.makeDouble());
				break;
			case 16:
				globals.getActiveCircuit().getSolution().setMaxIterations(parser.makeInteger());
				break;
			case 18:
				solution = globals.getActiveCircuit().getSolution();
				solution.setDefaultLoadModel(Utilities.interpretLoadModel(param)); // for reverting to last on specified
				solution.setLoadModel(solution.getDefaultLoadModel());
				break;
			case 19:
				globals.getActiveCircuit().setLoadMultiplier(parser.makeDouble());  // set using loadMultiplier property
				break;
			case 20:
				globals.getActiveCircuit().setNormalMinVolts(parser.makeDouble());
				break;
			case 21:
				globals.getActiveCircuit().setNormalMaxVolts(parser.makeDouble());
				break;
			case 22:
				globals.getActiveCircuit().setEmergMinVolts(parser.makeDouble());
				break;
			case 23:
				globals.getActiveCircuit().setEmergMaxVolts(parser.makeDouble());
				break;
			case 24:
				globals.getActiveCircuit().getDefaultDailyShapeObj().setMean(parser.makeDouble() / 100.0);
				break;
			case 25:
				globals.getActiveCircuit().getDefaultDailyShapeObj().setStdDev(parser.makeDouble() / 100.0);
				break;
			case 26:
				ckt = globals.getActiveCircuit();
				ckt.setLoadDurCurve(param);
				ckt.setLoadDurCurveObj((LoadShapeObj) globals.getLoadShapeClass().find(param));
				if (ckt.getLoadDurCurveObj() == null)
					globals.doSimpleMsg("Load-duration curve not found.", 131);
				break;
			case 27:
				ckt = globals.getActiveCircuit();
				ckt.setDefaultGrowthRate(1.0 + parser.makeDouble() / 100.0);
				ckt.setDefaultGrowthFactor( Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1) );
				break;
			case 28:
				globals.getActiveCircuit().getAutoAddObj().setGenKW(parser.makeDouble());
				break;
			case 29:
				globals.getActiveCircuit().getAutoAddObj().setGenPF(parser.makeDouble());
				break;
			case 30:
				globals.getActiveCircuit().getAutoAddObj().setCapKVAr(parser.makeDouble());
				break;
			case 31:
				globals.getActiveCircuit().getAutoAddObj().setAddType(Utilities.interpretAddType(param));
				break;
			case 32:
				globals.getActiveCircuit().setDuplicatesAllowed(Utilities.interpretYesNo(param));
				break;
			case 33:
				globals.getActiveCircuit().setZonesLocked(Utilities.interpretYesNo(param));
				break;
			case 34:
				globals.getActiveCircuit().setUEWeight(parser.makeDouble());
				break;
			case 35:
				globals.getActiveCircuit().setLossWeight(parser.makeDouble());
				break;
			case 36:
				numRegs = new MutableInt();
				globals.getActiveCircuit().setUERegs(
						Utilities.parseIntArray(globals.getActiveCircuit().getUERegs(), numRegs, param)
				);
				globals.getActiveCircuit().setNumUERegs(numRegs.intValue());
				break;
			case 37:
				numRegs = new MutableInt();
				globals.getActiveCircuit().setLossRegs(
						Utilities.parseIntArray(globals.getActiveCircuit().getLossRegs(), numRegs, param)
				);
				globals.getActiveCircuit().setNumLossRegs(numRegs.intValue());
				break;
			case 38:
				ExecHelper.doLegalVoltageBases();
				break;
			case 39:
				globals.getActiveCircuit().getSolution().setAlgorithm(Utilities.interpretSolveAlg(param));
				break;
			case 40:
				globals.getActiveCircuit().setTrapezoidalIntegration(Utilities.interpretYesNo(param));
				break;
			case 41:
				ExecHelper.doAutoAddBusList(param);
				break;
			case 42:
				solution = globals.getActiveCircuit().getSolution();
				solution.setControlMode(Utilities.interpretControlMode(param));
				solution.setDefaultControlMode(solution.getControlMode());  // always revert to last one specified in a script
				break;
			case 43:
				globals.getActiveCircuit().getControlQueue().setTrace(Utilities.interpretYesNo(param));
				break;
			case 44:
				globals.getActiveCircuit().setGenMultiplier(parser.makeDouble());
				break;
			case 45:
				testLoadShapeObj = (LoadShapeObj) globals.getLoadShapeClass().find(param);
				if (testLoadShapeObj != null)
					globals.getActiveCircuit().setDefaultDailyShapeObj(testLoadShapeObj);
				break;
			case 46:
				testLoadShapeObj = (LoadShapeObj) globals.getLoadShapeClass().find(param);
				if (testLoadShapeObj != null)
					globals.getActiveCircuit().setDefaultYearlyShapeObj(testLoadShapeObj);
				break;
			case 47:
				ExecHelper.doSetAllocationFactors(parser.makeDouble());
				break;
			case 48:
				globals.getActiveCircuit().setPositiveSequence(Utilities.interpretCktModel(param));
				break;
			case 49:
				globals.getActiveCircuit().setPriceSignal(parser.makeDouble());
				break;
			case 50:
				ckt = globals.getActiveCircuit();
				ckt.setPriceCurve(param);
				ckt.setPriceCurveObj((PriceShapeObj) globals.getLoadShapeClass().find(param));
				if (ckt.getPriceCurveObj() == null)
					globals.doSimpleMsg("PriceShape: \"" +param+ "\" not found.", 132);
				break;
			case 51:
				ckt = globals.getActiveCircuit();
				if (ckt.getActiveCktElement() != null) {
					CktElement cktElem = ckt.getActiveCktElement();
					cktElem.setActiveTerminalIdx(parser.makeInteger());
					globals.setActiveBus( Utilities.stripExtension(cktElem.getBus(cktElem.getActiveTerminalIdx())) );  // bus connected to terminal
				}
				break;
			case 52:
				globals.getActiveCircuit().setFundamental(parser.makeDouble());  // set base frequency for system (used henceforth)
				globals.getActiveCircuit().getSolution().setFrequency(parser.makeDouble());
				break;
			case 53:
				ExecHelper.doHarmonicsList(param);
				break;
			case 54:
				globals.getActiveCircuit().getSolution().setMaxControlIterations(parser.makeInteger());
				break;
			case 55:
				result = globals.setActiveBus(param);  // see DSSGlobals
				break;
			case 56:
				globals.setDataPath(param);  // set a legal data path
				break;
			case 57:
				ExecHelper.doKeeperBusList(param);
				break;
			case 58:
				ExecHelper.doSetReduceStrategy(param);
				break;
			case 59:
				globals.getEnergyMeterClass().setSaveDemandInterval(Utilities.interpretYesNo(param));
				break;
			case 60:
				globals.getActiveCircuit().setPctNormalFactor(parser.makeDouble());
				ExecHelper.doSetNormal(globals.getActiveCircuit().getPctNormalFactor());
				break;
			case 61:
				globals.getEnergyMeterClass().setDIVerbose(Utilities.interpretYesNo(param));
				break;
			case 62:
				globals.getActiveCircuit().setCaseName(parser.makeString());
				break;
			case 63:
				globals.getActiveCircuit().setNodeMarkerCode(parser.makeInteger());
				break;
			case 64:
				globals.getActiveCircuit().setNodeMarkerWidth(parser.makeInteger());
				break;
			case 65:
				globals.getActiveCircuit().setLogEvents(Utilities.interpretYesNo(param));
				break;
			case 66:
				DSSExecutive.getInstance().setRecorderOn(Utilities.interpretYesNo(param));
				break;
			case 67:
				globals.getEnergyMeterClass().setDo_OverloadReport(Utilities.interpretYesNo(param));
				break;
			case 68:
				globals.getEnergyMeterClass().setDo_VoltageExceptionReport(Utilities.interpretYesNo(param));
				break;
			case 69:
				ExecHelper.doSetCFactors(parser.makeDouble());
				break;
			case 70:
				globals.setAutoShowExport(Utilities.interpretYesNo(param));
				break;
			case 71:
				globals.setMaxAllocationIterations(parser.makeInteger());
				break;
			case 72:
				globals.setDefaultBaseFreq(parser.makeDouble());
				globals.getActiveCircuit().setFundamental(parser.makeDouble());  // set base frequency for system (used henceforth)
				globals.getActiveCircuit().getSolution().setFrequency(parser.makeDouble());
				break;
			case 73:
				globals.getActiveCircuit().setMarkSwitches(Utilities.interpretYesNo(param));
				break;
			case 74:
				globals.getActiveCircuit().setSwitchMarkerCode(parser.makeInteger());
				break;
			case 75:
				globals.setDaisySize(parser.makeDouble());
				break;
			case 76:
				globals.getActiveCircuit().setMarkTransformers(Utilities.interpretYesNo(param));
				break;
			case 77:
				globals.getActiveCircuit().setTransMarkerCode(parser.makeInteger());
				break;
			case 78:
				globals.getActiveCircuit().setTransMarkerSize(parser.makeInteger());
				break;
			case 79:
				globals.getActiveCircuit().setActiveLoadShapeClass(Utilities.interpretLoadShapeClass(param));
				break;
			case 80:
				globals.setDefaultEarthModel(Utilities.interpretEarthModel(param));
				break;
			default:
				// ignore excess parameters
				break;
			}

			switch (paramPointer) {
			case 3:
				globals.getActiveCircuit().getSolution().updateDblHour();
				break;
			case 4:
				globals.getActiveCircuit().getSolution().updateDblHour();
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
		DSSGlobals globals = DSSGlobals.getInstance();
		Circuit ckt;

		int result = 0;
		int paramPointer;
		try {
			globals.setGlobalResult("");  // initialize for appending

			// continue parsing command line
			String paramName = Parser.getInstance().getNextParam();
			String param = Parser.getInstance().makeString();
			// there will be no named parameters in this command and the params
			// themselves will be the parameter name to return
			while (param.length() > 0) {
				paramPointer = optionList.getCommand(param);

				switch (paramPointer) {
				case -1:
					globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Get command ", 133);
					break;
				case 0:
					globals.appendGlobalResult(globals.getActiveCircuit().getActiveCktElement().getDSSClassName());
					break;
				case 11:
					globals.appendGlobalResult(globals.getActiveCircuit().getActiveCktElement().getDSSClassName());
					break;
				case 1:
					globals.appendGlobalResult(globals.getActiveCircuit().getActiveCktElement().getName());
					break;
				case 12:
					globals.appendGlobalResult(globals.getActiveCircuit().getActiveCktElement().getName());
					break;
				case 2:
					globals.appendGlobalResult(String.valueOf(globals.getActiveCircuit().getSolution().getIntHour()));
					break;
				case 3:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getSolution().getDynaVars().t));
					break;
				case 4:
					globals.appendGlobalResult(String.valueOf(globals.getActiveCircuit().getSolution().getYear()));
					break;
				case 5:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getSolution().getFrequency()));
					break;
				case 6:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getSolution().getDynaVars().h));
					break;
				case 17:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getSolution().getDynaVars().h));
					break;
				case 7:
					globals.appendGlobalResult(Utilities.getSolutionModeID());
					break;
				case 8:
					globals.appendGlobalResult(Utilities.getRandomModeID());
					break;
				case 9:
					globals.appendGlobalResult(String.valueOf(globals.getActiveCircuit().getSolution().getNumberOfTimes()));
					break;
				case 10:
					globals.appendGlobalResult(String.format("[ %d, %-g ]", String.valueOf(globals.getActiveCircuit().getSolution().getIntHour()), globals.getActiveCircuit().getSolution().getDynaVars().t));
					break;
				case 13:
					globals.appendGlobalResult(globals.getActiveCircuit().getName());
					break;
				case 14:
					globals.appendGlobalResult(globals.getDefaultEditor());
					break;
				case 15:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getSolution().getConvergenceTolerance()));
					break;
				case 16:
					globals.appendGlobalResult(String.valueOf(globals.getActiveCircuit().getSolution().getMaxIterations()));
					break;
				case 18:
					globals.appendGlobalResult(Utilities.getLoadModel());
					break;
				case 19:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getLoadMultiplier()));
					break;
				case 20:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getNormalMinVolts()));
					break;
				case 21:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getNormalMaxVolts()));
					break;
				case 22:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getEmergMinVolts()));
					break;
				case 23:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getEmergMaxVolts()));
					break;
				case 24:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getDefaultDailyShapeObj().getMean() * 100.0));
					break;
				case 25:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getDefaultDailyShapeObj().getStdDev() * 100.0));
					break;
				case 26:
					globals.appendGlobalResult(globals.getActiveCircuit().getLoadDurCurve());
					break;
				case 27:
					globals.appendGlobalResult(String.format("%-g", (globals.getActiveCircuit().getDefaultGrowthRate() - 1.0) * 100.0));
					break;
				case 28:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getAutoAddObj().getGenKW()));
					break;
				case 29:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getAutoAddObj().getGenPF()));
					break;
				case 30:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getAutoAddObj().getCapKVAr()));
					break;
				case 31:
					switch (globals.getActiveCircuit().getAutoAddObj().getAddType()) {
					case DSSGlobals.GENADD:
						globals.appendGlobalResult("generator");
						break;
					case DSSGlobals.CAPADD:
						globals.appendGlobalResult("capacitor");
						break;
					}
					break;
				case 32:
					if (globals.getActiveCircuit().isDuplicatesAllowed()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 33:
					if (globals.getActiveCircuit().isZonesLocked()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 34:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getUEWeight()));
					break;
				case 35:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getLossWeight()));
					break;
				case 36:
					globals.appendGlobalResult(Utilities.intArrayToString(globals.getActiveCircuit().getUERegs(), globals.getActiveCircuit().getNumUERegs()));
					break;
				case 37:
					globals.appendGlobalResult(Utilities.intArrayToString(globals.getActiveCircuit().getLossRegs(), globals.getActiveCircuit().getNumLossRegs()));
					break;
				case 38:
					ckt = globals.getActiveCircuit();
					globals.setGlobalResult("(");
					for (double vBase : ckt.getLegalVoltageBases())
						globals.setGlobalResult(globals.getGlobalResult() + String.format("%-g, ", vBase));
					globals.setGlobalResult(globals.getGlobalResult() + ")");
					break;
				case 39:
					switch (globals.getActiveCircuit().getSolution().getAlgorithm()) {
					case Solution.NORMALSOLVE:
						globals.appendGlobalResult("normal");
						break;
					case Solution.NEWTONSOLVE:
						globals.appendGlobalResult("newton");
						break;
					}
					break;
				case 40:
					if (globals.getActiveCircuit().isTrapezoidalIntegration()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 41:
					for (int i = 0; i < globals.getActiveCircuit().getAutoAddBusList().listSize(); i++)
						globals.appendGlobalResult(globals.getActiveCircuit().getAutoAddBusList().get(i));
					break;
				case 42:
					globals.appendGlobalResult(Utilities.getControlModeID());
					break;
				case 43:
					if (globals.getActiveCircuit().getControlQueue().getTrace()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 44:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getGenMultiplier()));
					break;
				case 45:
					globals.appendGlobalResult(globals.getActiveCircuit().getDefaultDailyShapeObj().getName());
					break;
				case 46:
					globals.appendGlobalResult(globals.getActiveCircuit().getDefaultYearlyShapeObj().getName());
					break;
				case 47:
					globals.appendGlobalResult("Get function not applicable.");
					break;
				case 48:
					if (globals.getActiveCircuit().isPositiveSequence()) {
						globals.appendGlobalResult("positive");
					} else {
						globals.appendGlobalResult("multiphase");
					}
					break;
				case 49:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getPriceSignal()));
					break;
				case 50:
					globals.appendGlobalResult(globals.getActiveCircuit().getPriceCurve());
					break;
				case 51:
					globals.appendGlobalResult(String.format("%d", globals.getActiveCircuit().getActiveCktElement().getActiveTerminalIdx()));
					break;
				case 52:
					globals.appendGlobalResult(String.format("%-g", globals.getActiveCircuit().getFundamental()));
					break;
				case 53:
					SolutionObj sol = globals.getActiveCircuit().getSolution();
					if (sol.isDoAllHarmonics()) {
						globals.appendGlobalResult("ALL");
					} else {
						for (int i = 0; i < sol.getHarmonicListSize(); i++)
							globals.appendGlobalResult(String.format("%-g", sol.getHarmonicList()[i]));
					}
					break;
				case 54:
					globals.appendGlobalResult(String.valueOf(globals.getActiveCircuit().getSolution().getMaxControlIterations()));
					break;
				case 55:
					globals.appendGlobalResult(globals.getActiveCircuit().getBusList().get(globals.getActiveCircuit().getActiveBusIndex()));
					break;
				case 56:
					globals.appendGlobalResult(globals.getDSSDataDirectory());
					break;
				case 57:
					ckt = globals.getActiveCircuit();
					for (int i = 0; i < ckt.getNumBuses(); i++)
						if (ckt.getBuses()[i].isKeep())
							globals.appendGlobalResult(ckt.getBusList().get(i));
					break;
				case 58:
					globals.appendGlobalResult(globals.getActiveCircuit().getReductionStrategyString());
					break;
				case 59:
					if (globals.getEnergyMeterClass().isSaveDemandInterval()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 60:
					globals.appendGlobalResult(String.format("%-.g", globals.getActiveCircuit().getPctNormalFactor()));
					break;
				case 61:
					if (globals.getEnergyMeterClass().isDIVerbose()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 62:
					globals.appendGlobalResult(globals.getActiveCircuit().getCaseName());
					break;
				case 63:
					globals.appendGlobalResult(String.format("%d", globals.getActiveCircuit().getNodeMarkerCode()));
					break;
				case 64:
					globals.appendGlobalResult(String.format("%d", globals.getActiveCircuit().getNodeMarkerWidth()));
					break;
				case 65:
					if (globals.getActiveCircuit().isLogEvents()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 66:
					if (DSSExecutive.getInstance().isRecorderOn()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 67:
					if (globals.getEnergyMeterClass().isDo_OverloadReport()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 68:
					if (globals.getEnergyMeterClass().isDo_VoltageExceptionReport()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 69:
					globals.appendGlobalResult("Get function not applicable.");
					break;
				case 70:
					if (globals.isAutoShowExport()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 71:
					globals.appendGlobalResult(String.format("%d", globals.getMaxAllocationIterations())) ;
					break;
				case 72:
					globals.appendGlobalResult(String.format("%d", globals.getDefaultBaseFreq()));
					break;
				case 73:
					if (globals.getActiveCircuit().isMarkSwitches()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 74:
					globals.appendGlobalResult(String.format("%d", globals.getActiveCircuit().getSwitchMarkerCode()));
					break;
				case 75:
					globals.appendGlobalResult(String.format("%-.6g", globals.getDaisySize()));
					break;
				case 76:
					if (globals.getActiveCircuit().isMarkTransformers()) {
						globals.appendGlobalResult("Yes");
					} else {
						globals.appendGlobalResult("No");
					}
					break;
				case 77:
					globals.appendGlobalResult(String.format("%d", globals.getActiveCircuit().getTransMarkerCode()));
					break;
				case 78:
					globals.appendGlobalResult(String.format("%d", globals.getActiveCircuit().getTransMarkerSize()));
					break;
				case 79:
					globals.appendGlobalResult(Utilities.getActiveLoadShapeClass());
					break;
				case 80:
					globals.appendGlobalResult(Utilities.getEarthModel(globals.getDefaultEarthModel()));
					break;
				default:
					// ignore excess parameters
					break;
				}

				paramName = Parser.getInstance().getNextParam();
				param = Parser.getInstance().makeString();
			}

		} catch (Exception e) {
			globals.appendGlobalResult("***Error***");
		}

		return result;
	}

}
