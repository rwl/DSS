/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.executive;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.general.PriceShapeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class ExecOptions {

	public final static int NumExecOptions = 82;

	public static final String[] execOption = defineOptions();
	public static final String[] optionHelp = defineHelp();

	public static CommandList optionList = new CommandList(execOption, true);

	private static String[] defineOptions() {
		String[] options = new String[NumExecOptions];

		options[0]  = "type";
		options[1]  = "element";
		options[2]  = "hour";
		options[3]  = "sec";
		options[4]  = "year";
		options[5]  = "frequency";
		options[6]  = "stepsize";
		options[7]  = "mode";
		options[8]  = "random";
		options[9] =  "number";
		options[10] = "time";
		options[11] = "class";
		options[12] = "object";
		options[13] = "circuit";
		options[14] = "editor";
		options[15] = "tolerance";
		options[16] = "maxiterations";
		options[17] = "h";
		options[18] = "Loadmodel";
		options[19] = "Loadmult";
		options[20] = "normvminpu";
		options[21] = "normvmaxpu";
		options[22] = "emergvminpu";
		options[23] = "emergvmaxpu";
		options[24] = "%mean";
		options[25] = "%stddev";
		options[26] = "LDCurve";  // load duration curve
		options[27] = "%growth";  // default growth rate
		options[28] = "Genkw";
		options[29] = "Genpf";
		options[30] = "CapkVAR";
		options[31] = "Addtype";
		options[32] = "Allowduplicates";
		options[33] = "Zonelock";
		options[34] = "UEweight";
		options[35] = "Lossweight";
		options[36] = "UEregs";
		options[37] = "Lossregs";
		options[38] = "Voltagebases";  // changes the default voltage base rules
		options[39] = "Algorithm";     // changes the default voltage base rules
		options[40] = "Trapezoidal";
		options[41] = "Autobuslist";   // array of bus names to include in auto add solutions
		options[42] = "Controlmode";
		options[43] = "Tracecontrol";
		options[44] = "Genmult";
		options[45] = "Defaultdaily";
		options[46] = "Defaultyearly";
		options[47] = "Allocationfactors";
		options[48] = "Cktmodel";
		options[49] = "Pricesignal";
		options[50] = "Pricecurve";
		options[51] = "Terminal";
		options[52] = "Basefrequency";
		options[53] = "Harmonics";
		options[54] = "Maxcontroliter";
		options[55] = "Bus";
		options[56] = "Datapath";
		options[57] = "KeepList";
		options[58] = "ReduceOption";
		options[59] = "DemandInterval";
		options[60] = "%Normal";
		options[61] = "DIVerbose";
		options[62] = "Casename";
		options[63] = "Markercode";
		options[64] = "Nodewidth";
		options[65] = "Log";
		options[66] = "Recorder";
		options[67] = "Overloadreport";
		options[68] = "Voltexceptionreport";
		options[69] = "Cfactors";
		options[70] = "Showexport";
		options[71] = "Numallociterations";
		options[72] = "DefaultBaseFrequency";
		options[73] = "Markswitches";
		options[74] = "Switchmarkercode";
		options[75] = "Daisysize";
		options[76] = "Marktransformers";
		options[77] = "TransMarkerCode";
		options[78] = "TransMarkerSize";
		options[79] = "LoadShapeClass";
		options[80] = "EarthModel";
		options[81] = "QueryLog";

		return options;
	}

	private static String[] defineHelp() {
		final String CRLF = DSS.CRLF;

		String[] help = new String[NumExecOptions];

		help[0]  = "Sets the active DSS class type. Same as Class=...";
		help[1]  = "Sets the active DSS element by name. You can use "+
			"the complete object spec (class.name) or just the "+
			"name.  if full name is specifed, class becomes the active "+
			"class, also.";
		help[2]  = "Sets the hour used for the start time of the solution.";
		help[3]  = "Sets the seconds from the hour for the start time of the solution.";
		help[4]  = "Sets the year to be used for the solution. "+
			"For certain solution types, this determines the growth multiplier.";
		help[5]  = "Sets the frequency for the solution of the active circuit.";
		help[6]  = "Sets the time step size for the active circuit. Default units are s. " +
			"May also be specified in minutes or hours by appending \"m\" or \"h\" to the value. For example:" + CRLF + CRLF +
			"   stepsize=.25h " + CRLF + "  stepsize=15m" + CRLF + "  stepsize=900s";
		help[7]  = "Set the solution mode. One of:"+
			CRLF+"  Snapshot,"+
			CRLF+"  Daily,"+
			CRLF+"  Direct,"+
			CRLF+"  Dutycycle,"+
			CRLF+"  Dynamic,"+
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
		help[8]  = "Randomization of Monte Carlo variables. One of [Uniform | Gaussian | Lognormal | None].";
		help[9] = "Number of solutions to perform for Monte Carlo or dutycycle solutions.";
		help[10] = "Specify the solution start time as an array. E.g:"+CRLF+
			"time=(hour, secs)";
		help[11] = "Synonym for \"type\".";
		help[12] = "Synonym for \"element\".";
		help[13] = "Set the active circuit by name.";
		help[14] = "Set the command string required to start up the editor preferred by the user. Does not require a circuit defined.";
		help[15] = "Sets the solution tolerance. Default is 0.0001.";
		help[16] = "Sets the maximum allowable iterations for power flow solutions. Default is 15.";
		help[17] = "Alternate name for time step size.";
		help[18] = "{Powerflow | Admittance} depending on the type of solution you wish to perform. "+
			"If admittance, a non-iterative, direct solution is done with all loads and generators modeled by their "+
			"equivalent admittance.";
		help[19] = "Global load multiplier for the circuit. Does not affect loads "+
			"designated to be \"fixed\".  All other base kW values are multiplied by this number. "+
			"Defaults to 1.0 when the circuit is created. As with other values, it always stays "+
			"at the last value to which it was set until changed again.";
		help[20] = "Minimum permissible per unit voltage for normal conditions. Default is 0.95.";
		help[21] = "Maximum permissible per unit voltage for normal conditions. Default is 1.05.";
		help[22] = "Minimum permissible per unit voltage for emergency (contingency) conditions. Default is 0.90.";
		help[23] = "Maximum permissible per unit voltage for emergency (contingency) conditions. Default is 1.08.";
		help[24] = "Percent mean to use for global load multiplier. Default is 65%.";
		help[25] = "Percent standard deviation to use for global load multiplier. Default is 9%.";
		help[26] = "Set load-duration curve. Global load multiplier is defined by this curve for LD1 and LD2 solution modes. Default is Nil.";
		help[27] = "Set default annual growth rate, percent, for loads with no growth curve specified. Default is 2.5.";
		help[28] = "Size of generator, kW, to automatically add to system. Default is 1000.0";
		help[29] = "Power factor of generator to assume for automatic addition. Default is 1.0.";
		help[30] = "Size of capacitor, kVAr, to automatically add to system.  Default is 600.0.";
		help[31] = "Type of device for AutoAdd Mode. {Generator | Capacitor} default is Generator.";
		help[32] = "Flag to indicate if it is OK to have devices of same name in the same class. {Yes/True | No/False} Default is No." +
			"If No, then a New command is treated as an Edit command. "+
			"If Yes, then a New command will always result in a device being added.";
		help[33] = "Recompute meter zones. {Yes/True | No/False}  Default is \"no\". if \"no\", then meter zones are recomputed each time there is a change in the circuit. "+
			"If \"yes\", then meter zones are not recomputed unless they have not yet been computed. "+
			"Meter zones are normally recomputed on \"solve\" command following a circuit change.";
		help[34] = "Weighting factor for UE/EEN in AutoAdd functions. Defaults to 1.0." + CRLF + CRLF +
			"AutoAdd mode minimizes"  + CRLF + CRLF +
			"(LossWeight * Losses + UEweight * UE). " + CRLF + CRLF +
			"If you wish to ignore UE, set to 0. " +
			"This applies only when there are EnergyMeter objects. " +
			"Otherwise, AutoAdd mode minimizes total system losses.";
		help[35] = "Weighting factor for losses in AutoAdd functions. Defaults to 1.0." + CRLF+CRLF+
			"AutoAdd mode minimizes"  + CRLF+CRLF+
			"(LossWeight * Losses + UEweight * UE). " + CRLF + CRLF +
			"If you wish to ignore losses, set to 0. "+
			"This applies only when there are EnergyMeter objects. " +
			"Otherwise, AutoAdd mode minimizes total system losses.";
		help[36] = "Which EnergyMeter register(s) to use for UE in AutoAdd mode. " +
			"May be one or more registers. If more than one, register values are summed together. " +
			"Array of integer values > 0.  Defaults to 11 (for Load EEN). " + CRLF+CRLF+
			"for a list of EnergyMeter register numbers, do the \"show meters\" command after defining a circuit.";
		help[37] = "Which EnergyMeter register(s) to use for losses in AutoAdd mode. " +
			"May be one or more registers. If more than one, register values are summed together. " +
			"Array of integer values > 0. Defaults to 13 (for zone kWh losses). " +  CRLF+CRLF+
			"for a list of EnergyMeter register numbers, do the \"show meters\" command after defining a circuit.";
		help[38] = "Define legal bus voltage bases for this circuit.  Enter an array "+
			"of the legal voltage bases, in phase-to-phase voltages, for example:" +CRLF+CRLF+
			"set voltagebases=\".208, .480, 12.47, 24.9, 34.5, 115.0, 230.0\" "+CRLF+CRLF+
			"When the \"calcVoltageBases\" command is issued, a snapshot solution is performed "+
			"with no load injections and the bus base voltage is set to the nearest legal voltage base. "+
			"The defaults are as shown in the example above.";
		help[39] = "Solution algorithm type. {Normal | Newton} Normal is a fixed point iteration " +
			"that is a little quicker than the Newton iteration.  Normal is adequate for most radial "+
			"distribution circuits.  Newton is more robust for circuits that are difficult to solve.";
		help[40] = "Specifies whether to use trapezoidal integration for accumulating energy meter registers. {Yes/True | No/False} Default is \"No\". " +
			"Applies to EnergyMeter and Generator objects.  Default method simply multiplies the " +
			"present value of the registers times the width of the interval. " +
			"Trapezoidal is more accurate when there are sharp changes in a load shape or unequal intervals. " +
			"Trapezoidal is automatically used for " +
			"some load-duration curve simulations where the interval size varies considerably. " +
			"Keep in mind that for Trapezoidal, you have to solve one more point than the number of intervals. " +
			"That is, to do a Daily simulation on a 24-hr load shape, you would set number=25 to force a solution " +
			"at the first point again to establish the last (24th) interval.";
		help[41] = "Array of bus names to include in AutoAdd searches. Or, you can specify a text file holding the names, one to a line, " +
			"by using the syntax (file=filename) instead of the actual array elements. "  +
			"Default is null, which results in the program " +
			"using either the buses in the EnergyMeter object zones or, if no EnergyMeters, all the buses, which can " +
			"make for lengthy solution times. " +CRLF+CRLF+
			"Examples:"+CRLF+CRLF+
			"set autobuslist=(bus1, bus2, bus3, ... )" +CRLF+
			"set autobuslist=(file=buslist.txt)";
		help[42] = "Control mode for the solution. {Off | Static | Event | Time} Default is \"static\". " +
			"Set to \"off\" to prevent controls from changing." + CRLF +
			"\"Static\": time does not advance.  Control actions are executed in order of shortest time to act " +
			"until all actions are cleared from the control queue. Use this mode for power flow solutions which may require several " +
			"regulator tap changes per solution." + CRLF+CRLF+
			"\"Event\": solution is event driven.  Only the control actions nearest in time " +
			"are executed and the time is advanced automatically to the time of the event. " + CRLF +CRLF+
			"\"Time\": solution is time driven. Control actions are executed when the time for the pending " +
			"action is reached or surpassed." + CRLF + CRLF +
			"Controls may reset and may choose not to act when it comes their time. " +CRLF+
			"Use \"time\" mode when modeling a control externally to the DSS and a solution mode such as " +
			"\"daily\" or \"dutycycle\" that advances time, or set the time (hour and sec) explicitly from the external program. ";
		help[43] = "Trace the actions taken in the control queue? {Yes/True | No/False} "  +
			"Creates a file named Trace_ControlQueue.csv in the default directory. " +
			"The names of all circuit elements taking an action are logged.";
		help[44] = "Global multiplier for the kW output of every generator in the circuit. Default is 1.0. " +
			"Applies to all but AutoAdd solution modes. " +
			"Ignored for generators designated as status=fixed.";
		help[45] = "Default daily load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.";
		help[46] = "Default yearly load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.";
		help[47] = "Sets the connected kVA allocation factors for all loads in the active circuit to the value given.";
		help[48] = "Designates whether circuit model is to interpreted as a normal multi-phase "+
			"model or a positive-sequence only model. {MultiPhase | Positive}  Default = MultiPhase.";
		help[49] = "Sets the present price signal ($/MWh) for the circuit. Default value is 25.";
		help[50] = "Sets the price shape to use to obtain for price signal. Default is none (null string). If none, " +
			"price signal either remains constant or is set by an external process using Set Price= option. " +
			"Curve is defined as a price shape in actual values (not normalized) and should be defined to correspond to " +
			"the type of analysis being performed (daily, yearly, load-duration, etc.).";
		help[51] = "Set the active terminal of the active circuit element. May also be done with \"select\" command.";
		help[52] = "Set the fundamental frequency for harmonic solution and the default base frequency for all impedance quantities. Default = 60. " +
			"Side effect: also changes the value of the solution frequency. Saved as default for next circuit.";
		help[53] = "Array of harmonics for which to perform a solution in Harmonics mode. {All | (list of harmonics) }  Default = All. " +
			"If All, then solution is performed for all harmonics defined in spectra currently being used. " +
			"Otherwise, specify a more limited list such as: " +CRLF+CRLF+
			" set harmonics=(1 5 7 11 13)";
		help[54] = "Max control iterations per solution. Default is 10.";
		help[55] = "Set active bus by name. Can also be done with \"select\" and \"setKVBase\" commands and the \"set terminal=\" option. " +
			"The bus connected to the active terminal becomes the active bus. See Zsc and Zsc012 commands.";
		help[56] = "Set the data path for files written or read by the DSS. Defaults to the startup path. May be null. Executes a \"cd\" to this path if non-null. Does not require a circuit defined.";
		help[57] = "Array of bus names to keep when performing circuit reductions. You can specify a text file holding the names, one to a line, " +
			"by using the syntax (file=filename) instead of the actual array elements. "  +
			"Command is cumulative (reset keeplist first). " +
			"Reduction algorithm may keep other buses automatically. " +CRLF+CRLF+
			"Examples:"+CRLF+CRLF+
			"  reset keeplist (sets all buses to false (no keep))" +CRLF+
			"  set keeplist=(bus1, bus2, bus3, ... )" +CRLF+
			"  set keeplist=(file=buslist.txt)";
		help[58] = "Strategy for reducing feeders. {Default or [null] | Stubs [Zmag=nnn] | MergeParallel | BreakLoops | Switches | TapEnds [maxangle=nnn] | Ends} " +
			"Default is to eliminate all dangling end buses and buses without load, caps, or taps. " +  CRLF +
			"\"Stubs [Zmag=0.02]\" merges short branches with impedance less than Zmag (default = 0.02 ohms) " + CRLF +
			"\"MergeParallel\" merges lines that have been found to be in parallel " +CRLF+
			"\"Breakloops\" disables one of the lines at the head of a loop. " +CRLF+
			"\"Tapends [maxangle=15]\" eliminates all buses except those at the feeder ends, at tap points and where the feeder turns by greater than maxangle degrees. " + CRLF+
			"\"Ends\" eliminates dangling ends only."+CRLF+
			"\"Switches\" merges switches with downline lines and eliminates dangling switches."+CRLF+
			"Marking buses with \"Keeplist\" will prevent their elimination.";
		help[59] = "Set for keeping demand interval data for daily, yearly, etc, simulations. {Yes/True | No/False} Default = no. "+
			"Side effect: Resets all meters!";
		help[60] = "Sets the normal rating of all lines to a specified percent of the emergency rating. Note: This action takes place immediately. "+
			"Only the in-memory value is changed for the duration of the run.";
		help[61] = "Separate demand interval (DI) file. {Yes/True | No/False} Default = False.  Set to Yes/True if you wish a separate demand interval (DI) file written " +
			"for each meter. Otherwise, only the totalizing meters are written.";
		help[62] = "Name of case for yearly simulations with demand interval data. "+
			"Becomes the name of the subdirectory under which all the year data are stored. "+
			"Default = circuit name "+CRLF+CRLF+
			"Side effect: Sets the prefix for output files";
		help[63] = "Number code for node marker on circuit plots. Number from 0 to 47. Default is 16 (open circle). 24 is solid circle. Try other values for other symbols. See also \"nodewidth\"";
		help[64] = "Width of node marker. Default = 1. See \"markerCode\"";
		help[65] = "Significant solution events are added to the Event Log, primarily for debugging. {Yes/True | No/False} Default = False.";
		help[66] = "Opens DSSRecorder.dss and enables recording of all commands. {Yes/True | No/False} Default = False. " +
			"Closed by either setting to No/False or exiting the program. " +
			"When closed by this command, the file name can be found in the \"result\". Does not require a circuit defined.";
		help[67] = "For yearly solution mode, sets overload reporting on/off. {Yes/True | No/False} Default = False. Demand interval must be set to true for this to have effect.";
		help[68] = "For yearly solution mode, sets voltage exception reporting on/off. {Yes/True | No/False} Default = False. Demand interval must be set to true for this to have effect.";
		help[69] = "Sets the CFactors for for all loads in the active circuit to the value given.";
		help[70] = "Automatically show the results of an \"export\" command after it is written. {Yes/True | No/False} Default = False.";
		help[71] = "Maximum number of iterations for load allocations for each time the \"allocateLoads\" or \"estimate\" command is given. Default is 2.";
		help[72] = "Set default base frequency. Side effect: Sets solution frequency and default circuit base frequency. This value is saved when the DSS closes down.";
		help[73] = "Mark lines that are switches or are isolated with a symbol. {Yes/True | No/False}  Default is No. See \"switchMarkerCode\".";
		help[74] = "Numeric marker code for lines with switches or are isolated from the circuit. Default is 4. See \"markSwitches\" option.";
		help[75] = "Relative size (a multiplier applied to default size) of daisy circles on daisy plot. Default is 1.0.";
		help[76] = "Mark transformer locations with a symbol. {Yes/True | No/False}  Default is No. See \"transMarkerCode\". " +
			"The coordinate of one of the buses for winding 1 or 2 must be defined for the symbol to show";
		help[77] = "Numeric marker code for transformers. Default is 35. See \"marksTransformers\" option.";
		help[78] = "Size of transformer marker. Default is 1.";
		help[79] = "Default load shape class to use for mode=time and mode=dynamic simulations. {Daily | Yearly | Duty | None*} Loads and generators, etc., will follow " +
			"this shape as time is advanced. Default value is None. That is, Load will not vary with time.";
		help[80] = "One of {Carson | FullCarson | Deri*}.  Default is Deri, which is" +
			"a fit to the full Carson that works well into high frequencies. " +
			"\"Carson\" is the simplified Carson method that is typically used for 50/60 Hz power flow programs. " +
			"Applies only to Line objects that use LineGeometry objects to compute impedances.";
		help[81] = "Clears the query log file and thereafter appends " +
			"the time-stamped Result string contents to the log file after a query command. {Yes/True | No/False} Default = False.";

		return help;
	}

	/**
	 * Set commands that do not require a circuit.
	 *
	 * This is for setting global options that do not require an active circuit.
	 */
	public static boolean doSetCmd_NoCircuit() {
		boolean success = true;

		Parser parser = Parser.getInstance();

		// Continue parsing command line
		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = optionList.getCommand(paramName);
			}

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for set command ", 130);
				break;
			case 14:
				DSS.defaultEditor = param;  // 'editor='
				break;
			case 56:
				DSS.setDataPath(param);  // set a legal data path
				break;
			case 66:
				Executive.getInstance().setRecorderOn(Util.interpretYesNo(param));
				break;
			case 72:
				DSS.defaultBaseFreq = parser.doubleValue();
				break;
			default:
				DSS.doSimpleMsg("You must create a circuit to execute this \"set\" command: " +
						parser.getCommand(), 301);
				success = false;  // indicate that we could not process all set command
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		return success;
	}

	/**
	 * Set DSS options.
	 *
	 * Solve command is re-routed here first to set options before solving.
	 */
	public static int doSetCmd(int solveOption) {
		LoadShapeObj testLoadShapeObj;
		int[] numRegs;

		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		int ret = 0;

		// continue parsing command line
		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = optionList.getCommand(paramName);
			}

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for set command ", 130);
				break;
			case 0:
			case 11:
				DSSClassDefs.setObjectClass(param);
				break;
			case 1:
			case 12:
				DSS.setObject(param);
				break;
			case 2:
				ckt.getSolution().setIntHour(parser.integerValue());
				break;
			case 3:
				ckt.getSolution().getDynaVars().t = parser.doubleValue();
				break;
			case 4:
				ckt.getSolution().setYear(parser.integerValue());
				ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1));
				break;
			case 5:
				ckt.getSolution().setFrequency(parser.doubleValue());
				break;
			case 6:
				ckt.getSolution().getDynaVars().h = Util.interpretTimeStepSize(param);
				break;
			case 17:
				ckt.getSolution().getDynaVars().h = Util.interpretTimeStepSize(param);
				break;
			case 7:
				ckt.getSolution().setMode( Util.interpretSolveMode(param) );  // see DSSGlobals
				break;
			case 8:
				ckt.getSolution().setRandomType( Util.interpretRandom(param) );
				break;
			case 9:
				ckt.getSolution().setNumberOfTimes(parser.integerValue());
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
				ckt.getSolution().setConvergenceTolerance(parser.doubleValue());
				break;
			case 16:
				ckt.getSolution().setMaxIterations(parser.integerValue());
				break;
			case 18:
				ckt.getSolution().setDefaultLoadModel(Util.interpretLoadModel(param)); // for reverting to last on specified
				ckt.getSolution().setLoadModel(ckt.getSolution().getDefaultLoadModel());
				break;
			case 19:
				ckt.setLoadMultiplier(parser.doubleValue());  // set using loadMultiplier property
				break;
			case 20:
				ckt.setNormalMinVolts(parser.doubleValue());
				break;
			case 21:
				ckt.setNormalMaxVolts(parser.doubleValue());
				break;
			case 22:
				ckt.setEmergMinVolts(parser.doubleValue());
				break;
			case 23:
				ckt.setEmergMaxVolts(parser.doubleValue());
				break;
			case 24:
				ckt.getDefaultDailyShapeObj().setMean(parser.doubleValue() / 100.0);
				break;
			case 25:
				ckt.getDefaultDailyShapeObj().setStdDev(parser.doubleValue() / 100.0);
				break;
			case 26:
				ckt.setLoadDurCurve(param);
				ckt.setLoadDurCurveObj((LoadShapeObj) DSS.loadShapeClass.find(param));
				if (ckt.getLoadDurCurveObj() == null)
					DSS.doSimpleMsg("Load-duration curve not found.", 131);
				break;
			case 27:
				ckt.setDefaultGrowthRate(1.0 + parser.doubleValue() / 100.0);
				ckt.setDefaultGrowthFactor( Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1) );
				break;
			case 28:
				ckt.getAutoAddObj().setGenKW(parser.doubleValue());
				break;
			case 29:
				ckt.getAutoAddObj().setGenPF(parser.doubleValue());
				break;
			case 30:
				ckt.getAutoAddObj().setCapKVAr(parser.doubleValue());
				break;
			case 31:
				ckt.getAutoAddObj().setAddType(Util.interpretAddType(param));
				break;
			case 32:
				ckt.setDuplicatesAllowed(Util.interpretYesNo(param));
				break;
			case 33:
				ckt.setZonesLocked(Util.interpretYesNo(param));
				break;
			case 34:
				ckt.setUEWeight(parser.doubleValue());
				break;
			case 35:
				ckt.setLossWeight(parser.doubleValue());
				break;
			case 36:
				numRegs = new int[1];
				ckt.setUERegs(Util.parseIntArray(ckt.getUERegs(), numRegs, param));
				ckt.setNumUERegs(numRegs[0]);
				break;
			case 37:
				numRegs = new int[1];
				ckt.setLossRegs(Util.parseIntArray(ckt.getLossRegs(), numRegs, param));
				ckt.setNumLossRegs(numRegs[0]);
				break;
			case 38:
				ExecHelper.doLegalVoltageBases();
				break;
			case 39:
				ckt.getSolution().setAlgorithm(Util.interpretSolveAlg(param));
				break;
			case 40:
				ckt.setTrapezoidalIntegration(Util.interpretYesNo(param));
				break;
			case 41:
				ExecHelper.doAutoAddBusList(param);
				break;
			case 42:
				ckt.getSolution().setControlMode(Util.interpretControlMode(param));
				ckt.getSolution().setDefaultControlMode(ckt.getSolution().getControlMode());  // always revert to last one specified in a script
				break;
			case 43:
				ckt.getControlQueue().setTrace(Util.interpretYesNo(param));
				break;
			case 44:
				ckt.setGenMultiplier(parser.doubleValue());
				break;
			case 45:
				testLoadShapeObj = (LoadShapeObj) DSS.loadShapeClass.find(param);
				if (testLoadShapeObj != null)
					ckt.setDefaultDailyShapeObj(testLoadShapeObj);
				break;
			case 46:
				testLoadShapeObj = (LoadShapeObj) DSS.loadShapeClass.find(param);
				if (testLoadShapeObj != null)
					ckt.setDefaultYearlyShapeObj(testLoadShapeObj);
				break;
			case 47:
				ExecHelper.doSetAllocationFactors(parser.doubleValue());
				break;
			case 48:
				ckt.setPositiveSequence(Util.interpretCktModel(param));
				break;
			case 49:
				ckt.setPriceSignal(parser.doubleValue());
				break;
			case 50:
				ckt.setPriceCurve(param);
				ckt.setPriceCurveObj((PriceShapeObj) DSS.loadShapeClass.find(param));
				if (ckt.getPriceCurveObj() == null)
					DSS.doSimpleMsg("PriceShape: \"" + param + "\" not found.", 132);
				break;
			case 51:
				if (ckt.getActiveCktElement() != null) {
					CktElement elem = ckt.getActiveCktElement();
					elem.setActiveTerminalIdx(parser.integerValue());
					DSS.setActiveBus(Util.stripExtension(elem.getBus(elem.getActiveTerminalIdx())));  // bus connected to terminal
				}
				break;
			case 52:
				ckt.setFundamental(parser.doubleValue());  // set base frequency for system (used henceforth)
				ckt.getSolution().setFrequency(parser.doubleValue());
				break;
			case 53:
				ExecHelper.doHarmonicsList(param);
				break;
			case 54:
				ckt.getSolution().setMaxControlIterations(parser.integerValue());
				break;
			case 55:
				ret = DSS.setActiveBus(param);  // see DSS globals
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
				ckt.setPctNormalFactor(parser.doubleValue());
				ExecHelper.doSetNormal(ckt.getPctNormalFactor());
				break;
			case 61:
				DSS.energyMeterClass.setDIVerbose(Util.interpretYesNo(param));
				break;
			case 62:
				ckt.setCaseName(parser.stringValue());
				break;
			case 63:
				ckt.setNodeMarkerCode(parser.integerValue());
				break;
			case 64:
				ckt.setNodeMarkerWidth(parser.integerValue());
				break;
			case 65:
				ckt.setLogEvents(Util.interpretYesNo(param));
				break;
			case 66:
				Executive.getInstance().setRecorderOn(Util.interpretYesNo(param));
				break;
			case 67:
				DSS.energyMeterClass.setDoOverloadReport(Util.interpretYesNo(param));
				break;
			case 68:
				DSS.energyMeterClass.setDoVoltageExceptionReport(Util.interpretYesNo(param));
				break;
			case 69:
				ExecHelper.doSetCFactors(parser.doubleValue());
				break;
			case 70:
				DSS.autoShowExport = Util.interpretYesNo(param);
				break;
			case 71:
				DSS.maxAllocationIterations = parser.integerValue();
				break;
			case 72:
				DSS.defaultBaseFreq = parser.doubleValue();
				ckt.setFundamental(parser.doubleValue());  // set base frequency for system (used henceforth)
				ckt.getSolution().setFrequency(parser.doubleValue());
				break;
			case 73:
				ckt.setMarkSwitches(Util.interpretYesNo(param));
				break;
			case 74:
				ckt.setSwitchMarkerCode(parser.integerValue());
				break;
			case 75:
				DSS.daisySize = parser.doubleValue();
				break;
			case 76:
				ckt.setMarkTransformers(Util.interpretYesNo(param));
				break;
			case 77:
				ckt.setTransMarkerCode(parser.integerValue());
				break;
			case 78:
				ckt.setTransMarkerSize(parser.integerValue());
				break;
			case 79:
				ckt.setActiveLoadShapeClass(Util.interpretLoadShapeClass(param));
				break;
			case 80:
				DSS.defaultEarthModel = Util.interpretEarthModel(param);
				break;
			case 81:
				DSS.logQueries = Util.interpretYesNo(param);
				if (DSS.logQueries) DSS.resetQueryLogFile();
			default:
				// ignore excess parameters
				break;
			}

			switch (paramPointer) {
			case 3:
				ckt.getSolution().updateDblHour();
				break;
			case 4:
				ckt.getSolution().updateDblHour();
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		if (solveOption == 1)
			ExecHelper.doSolveCmd();

		return ret;
	}

	/**
	 * Get DSS options requested and put in the global result string.
	 */
	public static int doGetCmd() {
		int paramPointer;
		Circuit ckt = DSS.activeCircuit;

		try {
			DSS.globalResult = "";  // initialize for appending

			// continue parsing command line
			String paramName = Parser.getInstance().getNextParam();
			String param = Parser.getInstance().stringValue();

			// there will be no named parameters in this command and the params
			// themselves will be the parameter name to return
			while (param.length() > 0) {
				paramPointer = optionList.getCommand(param);

				switch (paramPointer) {
				case -1:
					DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for Get command ", 133);
					break;
				case 0:
					DSS.appendGlobalResult(ckt.getActiveCktElement().getDSSClassName());
					break;
				case 11:
					DSS.appendGlobalResult(ckt.getActiveCktElement().getDSSClassName());
					break;
				case 1:
					DSS.appendGlobalResult(ckt.getActiveCktElement().getName());
					break;
				case 12:
					DSS.appendGlobalResult(ckt.getActiveCktElement().getName());
					break;
				case 2:
					DSS.appendGlobalResult(String.valueOf(ckt.getSolution().getIntHour()));
					break;
				case 3:
					DSS.appendGlobalResult(String.format("%g", ckt.getSolution().getDynaVars().t));
					break;
				case 4:
					DSS.appendGlobalResult(String.valueOf(ckt.getSolution().getYear()));
					break;
				case 5:
					DSS.appendGlobalResult(String.format("%g", ckt.getSolution().getFrequency()));
					break;
				case 6:
					DSS.appendGlobalResult(String.format("%g", ckt.getSolution().getDynaVars().h));
					break;
				case 17:
					DSS.appendGlobalResult(String.format("%g", ckt.getSolution().getDynaVars().h));
					break;
				case 7:
					DSS.appendGlobalResult(Util.getSolutionModeID());
					break;
				case 8:
					DSS.appendGlobalResult(Util.getRandomModeID());
					break;
				case 9:
					DSS.appendGlobalResult(String.valueOf(ckt.getSolution().getNumberOfTimes()));
					break;
				case 10:
					DSS.appendGlobalResult(String.format("[ %d, %g ]", String.valueOf(ckt.getSolution().getIntHour()), ckt.getSolution().getDynaVars().t));
					break;
				case 13:
					DSS.appendGlobalResult(ckt.getName());
					break;
				case 14:
					DSS.appendGlobalResult(DSS.defaultEditor);
					break;
				case 15:
					DSS.appendGlobalResult(String.format("%g", ckt.getSolution().getConvergenceTolerance()));
					break;
				case 16:
					DSS.appendGlobalResult(String.valueOf(ckt.getSolution().getMaxIterations()));
					break;
				case 18:
					DSS.appendGlobalResult(Util.getLoadModel());
					break;
				case 19:
					DSS.appendGlobalResult(String.format("%g", ckt.getLoadMultiplier()));
					break;
				case 20:
					DSS.appendGlobalResult(String.format("%g", ckt.getNormalMinVolts()));
					break;
				case 21:
					DSS.appendGlobalResult(String.format("%g", ckt.getNormalMaxVolts()));
					break;
				case 22:
					DSS.appendGlobalResult(String.format("%g", ckt.getEmergMinVolts()));
					break;
				case 23:
					DSS.appendGlobalResult(String.format("%g", ckt.getEmergMaxVolts()));
					break;
				case 24:
					DSS.appendGlobalResult(String.format("%g", ckt.getDefaultDailyShapeObj().getMean() * 100.0));
					break;
				case 25:
					DSS.appendGlobalResult(String.format("%g", ckt.getDefaultDailyShapeObj().getStdDev() * 100.0));
					break;
				case 26:
					DSS.appendGlobalResult(ckt.getLoadDurCurve());
					break;
				case 27:
					DSS.appendGlobalResult(String.format("%g", (ckt.getDefaultGrowthRate() - 1.0) * 100.0));
					break;
				case 28:
					DSS.appendGlobalResult(String.format("%g", ckt.getAutoAddObj().getGenKW()));
					break;
				case 29:
					DSS.appendGlobalResult(String.format("%g", ckt.getAutoAddObj().getGenPF()));
					break;
				case 30:
					DSS.appendGlobalResult(String.format("%g", ckt.getAutoAddObj().getCapKVAr()));
					break;
				case 31:
					switch (ckt.getAutoAddObj().getAddType()) {
					case GEN:
						DSS.appendGlobalResult("generator");
						break;
					case CAP:
						DSS.appendGlobalResult("capacitor");
						break;
					}
					break;
				case 32:
					if (ckt.isDuplicatesAllowed()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 33:
					if (ckt.isZonesLocked()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 34:
					DSS.appendGlobalResult(String.format("%g", ckt.getUEWeight()));
					break;
				case 35:
					DSS.appendGlobalResult(String.format("%g", ckt.getLossWeight()));
					break;
				case 36:
					DSS.appendGlobalResult(Util.intArrayToString(ckt.getUERegs(), ckt.getNumUERegs()));
					break;
				case 37:
					DSS.appendGlobalResult(Util.intArrayToString(ckt.getLossRegs(), ckt.getNumLossRegs()));
					break;
				case 38:
					DSS.globalResult = "(";
					for (double vBase : ckt.getLegalVoltageBases())
						DSS.globalResult += String.format("%g, ", vBase);
					DSS.globalResult += ")";
					break;
				case 39:
					switch (ckt.getSolution().getAlgorithm()) {
					case NORMAL:
						DSS.appendGlobalResult("normal");
						break;
					case NEWTON:
						DSS.appendGlobalResult("newton");
						break;
					}
					break;
				case 40:
					if (ckt.isTrapezoidalIntegration()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 41:
					for (int i = 0; i < ckt.getAutoAddBusList().listSize(); i++)
						DSS.appendGlobalResult(ckt.getAutoAddBusList().get(i));
					break;
				case 42:
					DSS.appendGlobalResult(Util.getControlModeID());
					break;
				case 43:
					if (ckt.getControlQueue().isTrace()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 44:
					DSS.appendGlobalResult(String.format("%g", ckt.getGenMultiplier()));
					break;
				case 45:
					DSS.appendGlobalResult(ckt.getDefaultDailyShapeObj().getName());
					break;
				case 46:
					DSS.appendGlobalResult(ckt.getDefaultYearlyShapeObj().getName());
					break;
				case 47:
					DSS.appendGlobalResult("Get function not applicable.");
					break;
				case 48:
					if (ckt.isPositiveSequence()) {
						DSS.appendGlobalResult("positive");
					} else {
						DSS.appendGlobalResult("multiphase");
					}
					break;
				case 49:
					DSS.appendGlobalResult(String.format("%g", ckt.getPriceSignal()));
					break;
				case 50:
					DSS.appendGlobalResult(ckt.getPriceCurve());
					break;
				case 51:
					DSS.appendGlobalResult(String.format("%d", ckt.getActiveCktElement().getActiveTerminalIdx()));
					break;
				case 52:
					DSS.appendGlobalResult(String.format("%g", ckt.getFundamental()));
					break;
				case 53:
					if (ckt.getSolution().isDoAllHarmonics()) {
						DSS.appendGlobalResult("ALL");
					} else {
						for (int i = 0; i < ckt.getSolution().getHarmonicListSize(); i++)
							DSS.appendGlobalResult(String.format("%g", ckt.getSolution().getHarmonicList()[i]));
					}
					break;
				case 54:
					DSS.appendGlobalResult(String.valueOf(ckt.getSolution().getMaxControlIterations()));
					break;
				case 55:
					DSS.appendGlobalResult(ckt.getBusList().get(ckt.getActiveBusIndex()));
					break;
				case 56:
					DSS.appendGlobalResult(DSS.dataDirectory);
					break;
				case 57:
					for (int i = 0; i < ckt.getNumBuses(); i++)
						if (ckt.getBus(i).isKeep())
							DSS.appendGlobalResult(ckt.getBusList().get(i));
					break;
				case 58:
					DSS.appendGlobalResult(ckt.getReductionStrategyString());
					break;
				case 59:
					if (DSS.energyMeterClass.isSaveDemandInterval()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 60:
					DSS.appendGlobalResult(String.format("%-.g", ckt.getPctNormalFactor()));
					break;
				case 61:
					if (DSS.energyMeterClass.isDI_Verbose()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 62:
					DSS.appendGlobalResult(ckt.getCaseName());
					break;
				case 63:
					DSS.appendGlobalResult(String.format("%d", ckt.getNodeMarkerCode()));
					break;
				case 64:
					DSS.appendGlobalResult(String.format("%d", ckt.getNodeMarkerWidth()));
					break;
				case 65:
					if (ckt.isLogEvents()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 66:
					if (Executive.getInstance().isRecorderOn()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 67:
					if (DSS.energyMeterClass.isDoOverloadReport()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 68:
					if (DSS.energyMeterClass.isDoVoltageExceptionReport()) {
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
					if (ckt.isMarkSwitches()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 74:
					DSS.appendGlobalResult(String.format("%d", ckt.getSwitchMarkerCode()));
					break;
				case 75:
					DSS.appendGlobalResult(String.format("%-.6g", DSS.daisySize));
					break;
				case 76:
					if (ckt.isMarkTransformers()) {
						DSS.appendGlobalResult("Yes");
					} else {
						DSS.appendGlobalResult("No");
					}
					break;
				case 77:
					DSS.appendGlobalResult(String.format("%d", ckt.getTransMarkerCode()));
					break;
				case 78:
					DSS.appendGlobalResult(String.format("%d", ckt.getTransMarkerSize()));
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
				param = Parser.getInstance().stringValue();
			}

		} catch (Exception e) {
			DSS.appendGlobalResult("***Error***");
		}

		return 0;
	}

}
