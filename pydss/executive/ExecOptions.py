from __pyjamas__ import (ARGERROR,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.executive.impl.DSSExecutive import (DSSExecutive,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.Solution import (Solution,)
from dss.executive.impl.ExecHelper import (ExecHelper,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)


class ExecOptions(object):
    NumExecOptions = 79
    execOption = None
    optionHelp = None
    optionList = None
    # private constructor prevents instantiation from other classes

    def __init__(self):
        self.defineOptions()
        self.optionList = CommandListImpl(self.execOption)
        self.optionList.setAbbrevAllowed(True)

    class ExecOptionsHolder(object):
        """ExecOptionsHolder is loaded on the first execution of
        ExecOptions.getInstance() or the first access to
        ExecOptionsHolder.INSTANCE, not before.
        """
        INSTANCE = ExecOptions()

    @classmethod
    def getInstance(cls):
        return cls.ExecOptionsHolder.INSTANCE

    def defineOptions(self):
        self.execOption = [None] * self.NumExecOptions
        self.execOption[0] = 'type'
        self.execOption[1] = 'element'
        self.execOption[2] = 'hour'
        self.execOption[3] = 'sec'
        self.execOption[4] = 'year'
        self.execOption[5] = 'frequency'
        self.execOption[6] = 'stepsize'
        self.execOption[7] = 'mode'
        self.execOption[8] = 'random'
        self.execOption[9] = 'number'
        self.execOption[10] = 'time'
        self.execOption[11] = 'class'
        self.execOption[12] = 'object'
        self.execOption[13] = 'circuit'
        self.execOption[14] = 'editor'
        self.execOption[15] = 'tolerance'
        self.execOption[16] = 'maxiterations'
        self.execOption[17] = 'h'
        self.execOption[18] = 'Loadmodel'
        self.execOption[19] = 'Loadmult'
        self.execOption[20] = 'normvminpu'
        self.execOption[21] = 'normvmaxpu'
        self.execOption[22] = 'emergvminpu'
        self.execOption[23] = 'emergvmaxpu'
        self.execOption[24] = '%mean'
        self.execOption[25] = '%stddev'
        self.execOption[26] = 'LDCurve'
        # load duration curve
        self.execOption[27] = '%growth'
        # default growth rate
        self.execOption[28] = 'Genkw'
        self.execOption[29] = 'Genpf'
        self.execOption[30] = 'CapkVAR'
        self.execOption[31] = 'Addtype'
        self.execOption[32] = 'Allowduplicates'
        self.execOption[33] = 'Zonelock'
        self.execOption[34] = 'UEweight'
        self.execOption[35] = 'Lossweight'
        self.execOption[36] = 'UEregs'
        self.execOption[37] = 'Lossregs'
        self.execOption[38] = 'Voltagebases'
        # changes the default voltage base rules
        self.execOption[39] = 'Algorithm'
        # changes the default voltage base rules
        self.execOption[40] = 'Trapezoidal'
        self.execOption[41] = 'Autobuslist'
        # array of bus names to include in auto add solutions
        self.execOption[42] = 'Controlmode'
        self.execOption[43] = 'Tracecontrol'
        self.execOption[44] = 'Genmult'
        self.execOption[45] = 'Defaultdaily'
        self.execOption[46] = 'Defaultyearly'
        self.execOption[47] = 'Allocationfactors'
        self.execOption[48] = 'Cktmodel'
        self.execOption[49] = 'Pricesignal'
        self.execOption[50] = 'Pricecurve'
        self.execOption[51] = 'Terminal'
        self.execOption[52] = 'Basefrequency'
        self.execOption[53] = 'Harmonics'
        self.execOption[54] = 'Maxcontroliter'
        self.execOption[55] = 'Bus'
        self.execOption[56] = 'Datapath'
        self.execOption[57] = 'KeepList'
        self.execOption[58] = 'ReduceOption'
        self.execOption[59] = 'DemandInterval'
        self.execOption[60] = '%Normal'
        self.execOption[61] = 'DIVerbose'
        self.execOption[62] = 'Casename'
        self.execOption[63] = 'Markercode'
        self.execOption[64] = 'Nodewidth'
        self.execOption[65] = 'Log'
        self.execOption[66] = 'Recorder'
        self.execOption[67] = 'Overloadreport'
        self.execOption[68] = 'Voltexceptionreport'
        self.execOption[69] = 'Cfactors'
        self.execOption[70] = 'Showexport'
        self.execOption[71] = 'Numallociterations'
        self.execOption[72] = 'DefaultBaseFrequency'
        self.execOption[73] = 'Markswitches'
        self.execOption[74] = 'Switchmarkercode'
        self.execOption[75] = 'Daisysize'
        self.execOption[76] = 'Marktransformers'
        self.execOption[77] = 'TransMarkerCode'
        self.execOption[78] = 'TransMarkerSize'
        CRLF = DSSGlobals.CRLF
        self.optionHelp = [None] * self.NumExecOptions
        self.optionHelp[0] = 'Sets the active DSS class type.  Same as Class=...'
        self.optionHelp[1] = 'Sets the active DSS element by name. You can use ' + 'the complete object spec (class.name) or just the ' + 'name.  if full name is specifed, class becomes the active ' + 'class, also.'
        self.optionHelp[2] = 'Sets the hour used for the start time of the solution.'
        self.optionHelp[3] = 'Sets the seconds from the hour for the start time of the solution.'
        self.optionHelp[4] = 'Sets the Year (integer number) to be used for the solution. ' + 'for certain solution types, this determines the growth multiplier.'
        self.optionHelp[5] = 'Sets the frequency for the solution of the active circuit.'
        self.optionHelp[6] = 'Sets the time step size for the active circuit.  Default units are s. ' + 'May also be specified in minutes or hours by appending \"m\" or \"h\" to the value. For example:' + CRLF + CRLF + '   stepsize=.25h ' + CRLF + '  stepsize=15m' + CRLF + '  stepsize=900s'
        self.optionHelp[7] = 'Set the solution Mode: One of' + CRLF + '  Snapshot,' + CRLF + '  Daily,' + CRLF + '  DIrect,' + CRLF + '  DUtycycle,' + CRLF + '  DYnamic,' + CRLF + '  Harmonic,' + CRLF + '  M1 (Monte Carlo 1),' + CRLF + '  M2 (Monte Carlo 2),' + CRLF + '  M3 (Monte Carlo 3),' + CRLF + '  Faultstudy,' + CRLF + '  Yearly (follow Yearly curve),' + CRLF + '  MF (monte carlo fault study)' + CRLF + '  Peakday,' + CRLF + '  LD1 (load-duration 1)' + CRLF + '  LD2 (load-duration 2)' + CRLF + '  AutoAdd (see AddType)' + CRLF + CRLF + 'Side effect: setting the Mode propergy resets all monitors and energy meters. It also ' + 'resets the time step, etc. to defaults for each mode.  After the initial reset, the user ' + 'must explicitly reset the monitors and/or meters until another Set Mode= command.'
        self.optionHelp[8] = 'One of [Uniform | Gaussian | Lognormal | None ] for Monte Carlo Variables.'
        self.optionHelp[9] = 'Number of solutions to perform for Monte Carlo or dutycycle solutions.'
        self.optionHelp[10] = 'Specify the solution start time as an array:' + CRLF + 'time=(hour, secs)'
        self.optionHelp[11] = 'Synonym for Type=. (See above)'
        self.optionHelp[12] = 'Synonym for Element=. (See above)'
        self.optionHelp[13] = 'Set the active circuit by name.'
        self.optionHelp[14] = 'Set the command string required to start up the editor preferred by the user. Does not require a circuit defined.'
        self.optionHelp[15] = 'Sets the solution tolerance.  Default is 0.0001.'
        self.optionHelp[16] = 'Sets the maximum allowable iterations for power flow solutions. Default is 15.'
        self.optionHelp[17] = 'Alternate name for time step size.'
        self.optionHelp[18] = '{Powerflow | Admittance} depending on the type of solution you wish to perform. ' + 'If admittance, a non-iterative, direct solution is done with all loads and generators modeled by their ' + 'equivalent admittance.'
        self.optionHelp[19] = 'Global load multiplier for this circuit.  Does not affect loads ' + 'designated to be \"fixed\".  All other base kW values are multiplied by this number. ' + 'Defaults to 1.0 when the circuit is created. As with other values, it always stays ' + 'at the last value to which it was set until changed again.'
        self.optionHelp[20] = 'Minimum permissible per unit voltage for normal conditions. Default is 0.95.'
        self.optionHelp[21] = 'Maximum permissible per unit voltage for normal conditions. Default is 1.05.'
        self.optionHelp[22] = 'Minimum permissible per unit voltage for emergency (contingency) conditions. Default is 0.90.'
        self.optionHelp[23] = 'Maximum permissible per unit voltage for emergency (contingency) conditions. Default is 1.08.'
        self.optionHelp[24] = 'Percent mean to use for global load multiplier. Default is 65%.'
        self.optionHelp[25] = 'Percent Standard deviation to use for global load multiplier. Default is 9%.'
        self.optionHelp[26] = 'Set Load-Duration Curve. Global load multiplier is defined by this curve for LD1 and LD2 solution modes. Default is Nil.'
        self.optionHelp[27] = 'Set default annual growth rate, percent, for loads with no growth curve specified. Default is 2.5.'
        self.optionHelp[28] = 'Size of generator, kW, to automatically add to system. Default is 1000.0'
        self.optionHelp[29] = 'Power factor of generator to assume for automatic addition. Default is 1.0.'
        self.optionHelp[30] = 'Size of capacitor, kVAR, to automatically add to system.  Default is 600.0.'
        self.optionHelp[31] = '{Generator | Capacitor} Default is Generator. Type of device for AutoAdd Mode.'
        self.optionHelp[32] = '{YES/TRUE | NO/FALSE}   Default is No. Flag to indicate if it is OK to have devices of same name in the same class. ' + 'If No, then a New command is treated as an Edit command. ' + 'If Yes, then a New command will always result in a device being added.'
        self.optionHelp[33] = '{YES/TRUE | NO/FALSE}  Default is No. if No, then meter zones are recomputed each time there is a change in the circuit. ' + 'If Yes, then meter zones are not recomputed unless they have not yet been computed. ' + 'Meter zones are normally recomputed on Solve command following a circuit change.'
        self.optionHelp[34] = 'Weighting factor for UE/EEN in AutoAdd functions.  Defaults to 1.0.' + CRLF + CRLF + 'Autoadd mode minimizes' + CRLF + CRLF + '(Lossweight * Losses + UEweight * UE). ' + CRLF + CRLF + 'If you wish to ignore UE, set to 0. ' + 'This applies only when there are EnergyMeter objects. ' + 'Otherwise, AutoAdd mode minimizes total system losses.'
        self.optionHelp[35] = 'Weighting factor for Losses in AutoAdd functions.  Defaults to 1.0.' + CRLF + CRLF + 'Autoadd mode minimizes' + CRLF + CRLF + '(Lossweight * Losses + UEweight * UE). ' + CRLF + CRLF + 'If you wish to ignore Losses, set to 0. ' + 'This applies only when there are EnergyMeter objects. ' + 'Otherwise, AutoAdd mode minimizes total system losses.'
        self.optionHelp[36] = 'Which EnergyMeter register(s) to use for UE in AutoAdd Mode. ' + 'May be one or more registers.  if more than one, register values are summed together. ' + 'Array of integer values > 0.  Defaults to 11 (for Load EEN). ' + CRLF + CRLF + 'for a list of EnergyMeter register numbers, do the \"Show Meters\" command after defining a circuit.'
        self.optionHelp[37] = 'Which EnergyMeter register(s) to use for Losses in AutoAdd Mode. ' + 'May be one or more registers.  if more than one, register values are summed together. ' + 'Array of integer values > 0.  Defaults to 13 (for Zone kWh Losses). ' + CRLF + CRLF + 'for a list of EnergyMeter register numbers, do the \"Show Meters\" command after defining a circuit.'
        self.optionHelp[38] = 'Define legal bus voltage bases for this circuit.  Enter an array ' + 'of the legal voltage bases, in phase-to-phase voltages, for example:' + CRLF + CRLF + 'set voltagebases=\".208, .480, 12.47, 24.9, 34.5, 115.0, 230.0\" ' + CRLF + CRLF + 'When the CalcVoltageBases command is issued, a snapshot solution is performed ' + 'with no load injections and the bus base voltage is set to the nearest legal voltage base. ' + 'The defaults are as shown in the example above.'
        self.optionHelp[39] = '{Normal | Newton}  Solution algorithm type.  Normal is a fixed point iteration ' + 'that is a little quicker than the Newton iteration.  Normal is adequate for most radial ' + 'distribution circuits.  Newton is more robust for circuits that are difficult to solve.'
        self.optionHelp[40] = '{YES/TRUE | NO/FALSE}  Default is \"No\". Specifies whether to use trapezoidal integration for accumulating energy meter registers. ' + 'Applies to EnergyMeter and Generator objects.  Default method simply multiplies the ' + 'present value of the registers times the width of the interval. ' + 'Trapezoidal is more accurate when there are sharp changes in a load shape or unequal intervals. ' + 'Trapezoidal is automatically used for ' + 'some load-duration curve simulations where the interval size varies considerably. ' + 'Keep in mind that for Trapezoidal, you have to solve one more point than the number of intervals. ' + 'That is, to do a Daily simulation on a 24-hr load shape, you would set Number=25 to force a solution ' + 'at the first point again to establish the last (24th) interval.'
        self.optionHelp[41] = 'Array of bus names to include in AutoAdd searches. Or, you can specify a text file holding the names, one to a line, ' + 'by using the syntax (file=filename) instead of the actual array elements. ' + 'Default is null, which results in the program ' + 'using either the buses in the EnergyMeter object zones or, if no EnergyMeters, all the buses, which can ' + 'make for lengthy solution times. ' + CRLF + CRLF + 'Examples:' + CRLF + CRLF + 'Set autobuslist=(bus1, bus2, bus3, ... )' + CRLF + 'Set autobuslist=(file=buslist.txt)'
        self.optionHelp[42] = '{OFF | STATIC |EVENT | TIME}  Default is \"STATIC\".  Control mode for the solution. ' + 'Set to OFF to prevent controls from changing.' + CRLF + 'STATIC = Time does not advance.  Control actions are executed in order of shortest time to act ' + 'until all actions are cleared from the control queue.  Use this mode for power flow solutions which may require several ' + 'regulator tap changes per solution.' + CRLF + CRLF + 'EVENT = solution is event driven.  Only the control actions nearest in time ' + 'are executed and the time is advanced automatically to the time of the event. ' + CRLF + CRLF + 'TIME = solution is time driven.  Control actions are executed when the time for the pending ' + 'action is reached or surpassed.' + CRLF + CRLF + 'Controls may reset and may choose not to act when it comes their time. ' + CRLF + 'Use TIME mode when modeling a control externally to the DSS and a solution mode such as ' + 'DAILY or DUTYCYCLE that advances time, or set the time (hour and sec) explicitly from the external program. '
        self.optionHelp[43] = '{YES/TRUE | NO/FALSE}  Set to YES to trace the actions taken in the control queue.  ' + 'Creates a file named TRACE_CONTROLQUEUE.CSV in the default directory. ' + 'The names of all circuit elements taking an action are logged.'
        self.optionHelp[44] = 'Global multiplier for the kW output of every generator in the circuit. Default is 1.0. ' + 'Applies to all but Autoadd solution modes. ' + 'Ignored for generators designated as Status=Fixed.'
        self.optionHelp[45] = 'Default daily load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.'
        self.optionHelp[46] = 'Default yearly load shape name. Default value is \"default\", which is a 24-hour curve defined when the DSS is started.'
        self.optionHelp[47] = 'Sets the connected kVA allocation factors for all loads in the active circuit to the value given.'
        self.optionHelp[48] = '{Multiphase | Positive}  Default = Multiphase.  Designates whether circuit model is to interpreted as a normal multi-phase ' + 'model or a positive-sequence only model'
        self.optionHelp[49] = 'Sets the present price signal ($/MWh) for the circuit.  Default value is 25.'
        self.optionHelp[50] = 'Sets the PRICESHAPE object to use to obtain for price signal. Default is none (null string). If none, ' + 'price signal either remains constant or is set by an external process using Set Price= option. ' + 'Curve is defined as a PRICESHAPE in actual values (not normalized) and should be defined to correspond to ' + 'the type of analysis being performed (daily, yearly, load-duration, etc.).'
        self.optionHelp[51] = 'Set the active terminal of the active circuit element. May also be done with Select command.'
        self.optionHelp[52] = 'Default = 60. Set the fundamental frequency for harmonic solution and the default base frequency for all impedance quantities. ' + 'Side effect: also changes the value of the solution frequency. Saved as default for next circuit.'
        self.optionHelp[53] = '{ALL | (list of harmonics) }  Default = ALL. Array of harmonics for which to perform a solution in Harmonics mode. ' + 'If ALL, then solution is performed for all harmonics defined in spectra currently being used. ' + 'Otherwise, specify a more limited list such as: ' + CRLF + CRLF + '   Set Harmonics=(1 5 7 11 13)'
        self.optionHelp[54] = 'Max control iterations per solution.  Default is 10.'
        self.optionHelp[55] = 'Set Active Bus by name.  Can also be done with Select and SetkVBase commands and the \"Set Terminal=\"  option. ' + 'The bus connected to the active terminal becomes the active bus. See Zsc and Zsc012 commands.'
        self.optionHelp[56] = 'Set the data path for files written or read by the DSS.  Defaults to the startup path. May be Null.  Executes a CHDIR to this path if non-null. Does not require a circuit defined.'
        self.optionHelp[57] = 'Array of bus names to keep when performing circuit reductions. You can specify a text file holding the names, one to a line, ' + 'by using the syntax (file=filename) instead of the actual array elements. ' + 'Command is cumulative (reset keeplist first). ' + 'Reduction algorithm may keep other buses automatically. ' + CRLF + CRLF + 'Examples:' + CRLF + CRLF + 'Reset Keeplist (sets all buses to FALSE (no keep))' + CRLF + 'Set KeepList=(bus1, bus2, bus3, ... )' + CRLF + 'Set KeepList=(file=buslist.txt)'
        self.optionHelp[58] = '{ Default or [null] | Stubs [Zmag=nnn] | MergeParallel | BreakLoops | Switches | TapEnds [maxangle=nnn] | Ends}  Strategy for reducing feeders. ' + 'Default is to eliminate all dangling end buses and buses without load, caps, or taps. ' + CRLF + '\"Stubs [Zmag=0.02]\" merges short branches with impedance less than Zmag (default = 0.02 ohms) ' + CRLF + '\"MergeParallel\" merges lines that have been found to be in parallel ' + CRLF + '\"Breakloops\" disables one of the lines at the head of a loop. ' + CRLF + '\"Tapends [maxangle=15]\" eliminates all buses except those at the feeder ends, at tap points and where the feeder turns by greater than maxangle degrees. ' + CRLF + '\"Ends\" eliminates dangling ends only.' + CRLF + '\"Switches\" merges switches with downline lines and eliminates dangling switches.' + CRLF + 'Marking buses with \"Keeplist\" will prevent their elimination.'
        self.optionHelp[59] = '{YES/TRUE | NO/FALSE} Default = no. Set for keeping demand interval data for daily, yearly, etc, simulations. ' + 'Side Effect:  Resets all meters!!!'
        self.optionHelp[60] = 'Sets the Normal rating of all lines to a specified percent of the emergency rating.  Note: This action takes place immediately. ' + 'Only the in-memory value is changed for the duration of the run.'
        self.optionHelp[61] = '{YES/TRUE | NO/FALSE} Default = FALSE.  Set to Yes/True if you wish a separate demand interval (DI) file written ' + 'for each meter.  Otherwise, only the totalizing meters are written.'
        self.optionHelp[62] = 'Name of case for yearly simulations with demand interval data. ' + 'Becomes the name of the subdirectory under which all the year data are stored. ' + 'Default = circuit name ' + CRLF + CRLF + 'Side Effect: Sets the prefix for output files'
        self.optionHelp[63] = 'Number code for node marker on circuit plots. Number from 0 to 47. Default is 16 (open circle). 24 is solid circle. Try other values for other symbols. See also Nodewidth'
        self.optionHelp[64] = 'Width of node marker. Default=1. See MarkerCode'
        self.optionHelp[65] = '{YES/TRUE | NO/FALSE} Default = FALSE.  Significant solution events are added to the Event Log, primarily for debugging.'
        self.optionHelp[66] = '{YES/TRUE | NO/FALSE} Default = FALSE. Opens DSSRecorder.DSS in DSS install folder and enables recording of all commands that come through ' + 'the text command interface. Closed by either setting to NO/FALSE or exiting the program. ' + 'When closed by this command, the file name can be found in the Result. Does not require a circuit defined.'
        self.optionHelp[67] = '{YES/TRUE | NO/FALSE} Default = FALSE. For yearly solution mode, sets overload reporting on/off. DemandInterval must be set to true for this to have effect.'
        self.optionHelp[68] = '{YES/TRUE | NO/FALSE} Default = FALSE. For yearly solution mode, sets voltage exception reporting on/off. DemandInterval must be set to true for this to have effect.'
        self.optionHelp[69] = 'Sets the CFactors for for all loads in the active circuit to the value given.'
        self.optionHelp[70] = '{YES/TRUE | NO/FALSE} Default = FALSE. If YES/TRUE will automatically show the results of an Export Command after it is written.'
        self.optionHelp[71] = 'Default is 2. Maximum number of iterations for load allocations for each time the AllocateLoads or Estimate command is given.'
        self.optionHelp[72] = 'Set Default Base Frequency, Hz. Side effect: Sets solution Frequency and default Circuit Base Frequency. This value is saved when the DSS closes down.'
        self.optionHelp[73] = '{YES/TRUE | NO/FALSE}  Default is NO. Mark lines that are switches or are isolated with a symbol. See SwitchMarkerCode.'
        self.optionHelp[74] = 'Numeric marker code for lines with switches or are isolated from the circuit. Default is 4. See markswitches option.'
        self.optionHelp[75] = 'Default is 1.0. Relative size (a multiplier applied to default size) of daisy circles on daisy plot.'
        self.optionHelp[76] = '{YES/TRUE | NO/FALSE}  Default is NO. Mark transformer locations with a symbol. See TransMarkerCode. ' + 'The coordinate of one of the buses for winding 1 or 2 must be defined for the symbol to show'
        self.optionHelp[77] = 'Numeric marker code for transformers. Default is 35. See markstransformers option.'
        self.optionHelp[78] = 'Size of transformer marker. Default is 1.'

    def getExecOption(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.execOption
        elif _1 == 1:
            i, = _0
            return self.execOption[i]
        else:
            raise ARGERROR(0, 1)

    def setExecOption(self, values):
        self.execOption = values

    def getOptionHelp(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.optionHelp
        elif _1 == 1:
            i, = _0
            return self.optionHelp[i]
        else:
            raise ARGERROR(0, 1)

    def setOptionHelp(self, values):
        self.optionHelp = values

    def getOptionList(self):
        return self.optionList

    def setOptionList(self, list):
        self.optionList = list

    def doSetCmd_NoCircuit(self):
        """Set commands that do not require a circuit.

        This is for setting global options that do not require an active circuit.
        """
        result = True
        parser = Parser.getInstance()
        # Continue parsing command line
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.optionList.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Set command ', 130)
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    DSSGlobals.defaultEditor = param
                    # 'Editor='
                    break
                if (_1 is True) or (_0 == 56):
                    _1 = True
                    DSSGlobals.setDataPath(param)
                    # set a legal data path
                    break
                if (_1 is True) or (_0 == 66):
                    _1 = True
                    DSSExecutive.getInstance().setRecorderOn(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 72):
                    _1 = True
                    DSSGlobals.defaultBaseFreq = parser.makeDouble()
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg('You must create a new circuit object first: \"new circuit.mycktname\" to execute this Set command.', 301)
                    result = False
                    # indicate that we could not process all set command
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def doSetCmd(self, solveOption):
        """Set DSS options.

        Solve command is re-routed here first to set options before solving.
        """
        parser = Parser.getInstance()
        result = 0
        # continue parsing command line
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.optionList.getCommand(paramName)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Set command ', 130)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    DSSClassDefs.setObjectClass(param)
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    DSSClassDefs.setObjectClass(param)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    DSSGlobals.setObject(param)
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    DSSGlobals.setObject(param)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setIntHour(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().getDynaVars().t = parser.makeDouble()
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    ckt = DSSGlobals.activeCircuit
                    ckt.getSolution().setYear(parser.makeInteger())
                    ckt.setDefaultGrowthFactor(self.Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1))
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setFrequency(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().getDynaVars().h = Utilities.interpretTimeStepSize(param)
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().getDynaVars().h = Utilities.interpretTimeStepSize(param)
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setMode(Utilities.interpretSolveMode(param))
                    # see DSSGlobals
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setRandomType(Utilities.interpretRandom(param))
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setNumberOfTimes(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    ExecHelper.setTime()
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    ExecHelper.setActiveCircuit(param)
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    DSSGlobals.defaultEditor = param
                    # 'Editor='
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setConvergenceTolerance(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setMaxIterations(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    solution = DSSGlobals.activeCircuit.getSolution()
                    solution.setDefaultLoadModel(Utilities.interpretLoadModel(param))
                    # for reverting to last on specified
                    solution.setLoadModel(solution.getDefaultLoadModel())
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    DSSGlobals.activeCircuit.setLoadMultiplier(parser.makeDouble())
                    # set using loadMultiplier property
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    DSSGlobals.activeCircuit.setNormalMinVolts(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    DSSGlobals.activeCircuit.setNormalMaxVolts(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 22):
                    _1 = True
                    DSSGlobals.activeCircuit.setEmergMinVolts(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 23):
                    _1 = True
                    DSSGlobals.activeCircuit.setEmergMaxVolts(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 24):
                    _1 = True
                    DSSGlobals.activeCircuit.getDefaultDailyShapeObj().setMean(parser.makeDouble() / 100.0)
                    break
                if (_1 is True) or (_0 == 25):
                    _1 = True
                    DSSGlobals.activeCircuit.getDefaultDailyShapeObj().setStdDev(parser.makeDouble() / 100.0)
                    break
                if (_1 is True) or (_0 == 26):
                    _1 = True
                    ckt = DSSGlobals.activeCircuit
                    ckt.setLoadDurCurve(param)
                    ckt.setLoadDurCurveObj(DSSGlobals.loadShapeClass.find(param))
                    if ckt.getLoadDurCurveObj() is None:
                        DSSGlobals.doSimpleMsg('Load-duration curve not found.', 131)
                    break
                if (_1 is True) or (_0 == 27):
                    _1 = True
                    ckt = DSSGlobals.activeCircuit
                    ckt.setDefaultGrowthRate(1.0 + (parser.makeDouble() / 100.0))
                    ckt.setDefaultGrowthFactor(self.Math.pow(ckt.getDefaultGrowthRate(), ckt.getSolution().getYear() - 1))
                    break
                if (_1 is True) or (_0 == 28):
                    _1 = True
                    DSSGlobals.activeCircuit.getAutoAddObj().setGenKW(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 29):
                    _1 = True
                    DSSGlobals.activeCircuit.getAutoAddObj().setGenPF(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 30):
                    _1 = True
                    DSSGlobals.activeCircuit.getAutoAddObj().setCapKVAr(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 31):
                    _1 = True
                    DSSGlobals.activeCircuit.getAutoAddObj().setAddType(Utilities.interpretAddType(param))
                    break
                if (_1 is True) or (_0 == 32):
                    _1 = True
                    DSSGlobals.activeCircuit.setDuplicatesAllowed(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 33):
                    _1 = True
                    DSSGlobals.activeCircuit.setZonesLocked(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 34):
                    _1 = True
                    DSSGlobals.activeCircuit.setUEWeight(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 35):
                    _1 = True
                    DSSGlobals.activeCircuit.setLossWeight(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 36):
                    _1 = True
                    numRegs = MutableInt()
                    DSSGlobals.activeCircuit.setUERegs(Utilities.parseIntArray(DSSGlobals.activeCircuit.getUERegs(), numRegs, param))
                    DSSGlobals.activeCircuit.setNumUERegs(numRegs.intValue())
                    break
                if (_1 is True) or (_0 == 37):
                    _1 = True
                    numRegs = MutableInt()
                    DSSGlobals.activeCircuit.setLossRegs(Utilities.parseIntArray(DSSGlobals.activeCircuit.getLossRegs(), numRegs, param))
                    DSSGlobals.activeCircuit.setNumLossRegs(numRegs.intValue())
                    break
                if (_1 is True) or (_0 == 38):
                    _1 = True
                    ExecHelper.doLegalVoltageBases()
                    break
                if (_1 is True) or (_0 == 39):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setAlgorithm(Utilities.interpretSolveAlg(param))
                    break
                if (_1 is True) or (_0 == 40):
                    _1 = True
                    DSSGlobals.activeCircuit.setTrapezoidalIntegration(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 41):
                    _1 = True
                    ExecHelper.doAutoAddBusList(param)
                    break
                if (_1 is True) or (_0 == 42):
                    _1 = True
                    solution = DSSGlobals.activeCircuit.getSolution()
                    solution.setControlMode(Utilities.interpretControlMode(param))
                    solution.setDefaultControlMode(solution.getControlMode())
                    # always revert to last one specified in a script
                    break
                if (_1 is True) or (_0 == 43):
                    _1 = True
                    DSSGlobals.activeCircuit.getControlQueue().setTrace(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 44):
                    _1 = True
                    DSSGlobals.activeCircuit.setGenMultiplier(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 45):
                    _1 = True
                    testLoadShapeObj = DSSGlobals.loadShapeClass.find(param)
                    if testLoadShapeObj is not None:
                        DSSGlobals.activeCircuit.setDefaultDailyShapeObj(testLoadShapeObj)
                    break
                if (_1 is True) or (_0 == 46):
                    _1 = True
                    testLoadShapeObj = DSSGlobals.loadShapeClass.find(param)
                    if testLoadShapeObj is not None:
                        DSSGlobals.activeCircuit.setDefaultYearlyShapeObj(testLoadShapeObj)
                    break
                if (_1 is True) or (_0 == 47):
                    _1 = True
                    ExecHelper.doSetAllocationFactors(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 48):
                    _1 = True
                    DSSGlobals.activeCircuit.setPositiveSequence(Utilities.interpretCktModel(param))
                    break
                if (_1 is True) or (_0 == 49):
                    _1 = True
                    DSSGlobals.activeCircuit.setPriceSignal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 50):
                    _1 = True
                    ckt = DSSGlobals.activeCircuit
                    ckt.setPriceCurve(param)
                    ckt.setPriceCurveObj(DSSGlobals.loadShapeClass.find(param))
                    if ckt.getPriceCurveObj() is None:
                        DSSGlobals.doSimpleMsg('PriceShape: \"' + param + '\" not found.', 132)
                    break
                if (_1 is True) or (_0 == 51):
                    _1 = True
                    ckt = DSSGlobals.activeCircuit
                    if ckt.getActiveCktElement() is not None:
                        cktElem = ckt.getActiveCktElement()
                        cktElem.setActiveTerminalIdx(parser.makeInteger())
                        DSSGlobals.setActiveBus(Utilities.stripExtension(cktElem.getBus(cktElem.getActiveTerminalIdx())))
                        # bus connected to terminal
                    break
                if (_1 is True) or (_0 == 52):
                    _1 = True
                    DSSGlobals.activeCircuit.setFundamental(parser.makeDouble())
                    # set base frequency for system (used henceforth)
                    DSSGlobals.activeCircuit.getSolution().setFrequency(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 53):
                    _1 = True
                    ExecHelper.doHarmonicsList(param)
                    break
                if (_1 is True) or (_0 == 54):
                    _1 = True
                    DSSGlobals.activeCircuit.getSolution().setMaxControlIterations(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 55):
                    _1 = True
                    result = DSSGlobals.setActiveBus(param)
                    # see DSSGlobals
                    break
                if (_1 is True) or (_0 == 56):
                    _1 = True
                    DSSGlobals.setDataPath(param)
                    # set a legal data path
                    break
                if (_1 is True) or (_0 == 57):
                    _1 = True
                    ExecHelper.doKeeperBusList(param)
                    break
                if (_1 is True) or (_0 == 58):
                    _1 = True
                    ExecHelper.doSetReduceStrategy(param)
                    break
                if (_1 is True) or (_0 == 59):
                    _1 = True
                    DSSGlobals.energyMeterClass.setSaveDemandInterval(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 60):
                    _1 = True
                    DSSGlobals.activeCircuit.setPctNormalFactor(parser.makeDouble())
                    ExecHelper.doSetNormal(DSSGlobals.activeCircuit.getPctNormalFactor())
                    break
                if (_1 is True) or (_0 == 61):
                    _1 = True
                    DSSGlobals.energyMeterClass.setDIVerbose(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 62):
                    _1 = True
                    DSSGlobals.activeCircuit.setCaseName(parser.makeString())
                    break
                if (_1 is True) or (_0 == 63):
                    _1 = True
                    DSSGlobals.activeCircuit.setNodeMarkerCode(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 64):
                    _1 = True
                    DSSGlobals.activeCircuit.setNodeMarkerWidth(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 65):
                    _1 = True
                    DSSGlobals.activeCircuit.setLogEvents(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 66):
                    _1 = True
                    DSSExecutive.getInstance().setRecorderOn(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 67):
                    _1 = True
                    DSSGlobals.energyMeterClass.setDoOverloadReport(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 68):
                    _1 = True
                    DSSGlobals.energyMeterClass.setDoVoltageExceptionReport(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 69):
                    _1 = True
                    ExecHelper.doSetCFactors(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 70):
                    _1 = True
                    DSSGlobals.autoShowExport = Utilities.interpretYesNo(param)
                    break
                if (_1 is True) or (_0 == 71):
                    _1 = True
                    DSSGlobals.maxAllocationIterations = parser.makeInteger()
                    break
                if (_1 is True) or (_0 == 72):
                    _1 = True
                    DSSGlobals.defaultBaseFreq = parser.makeDouble()
                    DSSGlobals.activeCircuit.setFundamental(parser.makeDouble())
                    # set base frequency for system (used henceforth)
                    DSSGlobals.activeCircuit.getSolution().setFrequency(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 73):
                    _1 = True
                    DSSGlobals.activeCircuit.setMarkSwitches(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 74):
                    _1 = True
                    DSSGlobals.activeCircuit.setSwitchMarkerCode(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 75):
                    _1 = True
                    DSSGlobals.daisySize = parser.makeDouble()
                    break
                if (_1 is True) or (_0 == 76):
                    _1 = True
                    DSSGlobals.activeCircuit.setMarkTransformers(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 77):
                    _1 = True
                    DSSGlobals.activeCircuit.setTransMarkerCode(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 78):
                    _1 = True
                    DSSGlobals.activeCircuit.setTransMarkerSize(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 79):
                    _1 = True
                    DSSGlobals.activeCircuit.setActiveLoadShapeClass(Utilities.interpretLoadShapeClass(param))
                    break
                if (_1 is True) or (_0 == 80):
                    _1 = True
                    DSSGlobals.defaultEarthModel = Utilities.interpretEarthModel(param)
                    break
                if True:
                    _1 = True
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 3:
                    _3 = True
                    DSSGlobals.activeCircuit.getSolution().updateDblHour()
                    break
                if (_3 is True) or (_2 == 4):
                    _3 = True
                    DSSGlobals.activeCircuit.getSolution().updateDblHour()
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        if solveOption == 1:
            ExecHelper.doSolveCmd()
        return result

    def doGetCmd(self):
        """Get DSS options reguaeste and put it in global result string
        may be retrieved by result property of the DSSText interface.
        """
        result = 0
        try:
            DSSGlobals.globalResult = ''
            # initialize for appending
            # continue parsing command line
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
            # there will be no named parameters in this command and the params
            # themselves will be the parameter name to return
            while len(param) > 0:
                paramPointer = self.optionList.getCommand(param)
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Get command ', 133)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getActiveCktElement().getDSSClassName())
                        break
                    if (_1 is True) or (_0 == 11):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getActiveCktElement().getDSSClassName())
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getActiveCktElement().getName())
                        break
                    if (_1 is True) or (_0 == 12):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getActiveCktElement().getName())
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.valueOf.valueOf(DSSGlobals.activeCircuit.getSolution().getIntHour()))
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getSolution().getDynaVars().t))
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.valueOf.valueOf(DSSGlobals.activeCircuit.getSolution().getYear()))
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getSolution().getFrequency()))
                        break
                    if (_1 is True) or (_0 == 6):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getSolution().getDynaVars().h))
                        break
                    if (_1 is True) or (_0 == 17):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getSolution().getDynaVars().h))
                        break
                    if (_1 is True) or (_0 == 7):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.getSolutionModeID())
                        break
                    if (_1 is True) or (_0 == 8):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.getRandomModeID())
                        break
                    if (_1 is True) or (_0 == 9):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.valueOf.valueOf(DSSGlobals.activeCircuit.getSolution().getNumberOfTimes()))
                        break
                    if (_1 is True) or (_0 == 10):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('[ %d, %-g ]', String.valueOf.valueOf(DSSGlobals.activeCircuit.getSolution().getIntHour()), DSSGlobals.activeCircuit.getSolution().getDynaVars().t))
                        break
                    if (_1 is True) or (_0 == 13):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getName())
                        break
                    if (_1 is True) or (_0 == 14):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.defaultEditor)
                        break
                    if (_1 is True) or (_0 == 15):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getSolution().getConvergenceTolerance()))
                        break
                    if (_1 is True) or (_0 == 16):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.valueOf.valueOf(DSSGlobals.activeCircuit.getSolution().getMaxIterations()))
                        break
                    if (_1 is True) or (_0 == 18):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.getLoadModel())
                        break
                    if (_1 is True) or (_0 == 19):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getLoadMultiplier()))
                        break
                    if (_1 is True) or (_0 == 20):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getNormalMinVolts()))
                        break
                    if (_1 is True) or (_0 == 21):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getNormalMaxVolts()))
                        break
                    if (_1 is True) or (_0 == 22):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getEmergMinVolts()))
                        break
                    if (_1 is True) or (_0 == 23):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getEmergMaxVolts()))
                        break
                    if (_1 is True) or (_0 == 24):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getDefaultDailyShapeObj().getMean() * 100.0))
                        break
                    if (_1 is True) or (_0 == 25):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getDefaultDailyShapeObj().getStdDev() * 100.0))
                        break
                    if (_1 is True) or (_0 == 26):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getLoadDurCurve())
                        break
                    if (_1 is True) or (_0 == 27):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', (DSSGlobals.activeCircuit.getDefaultGrowthRate() - 1.0) * 100.0))
                        break
                    if (_1 is True) or (_0 == 28):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getAutoAddObj().getGenKW()))
                        break
                    if (_1 is True) or (_0 == 29):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getAutoAddObj().getGenPF()))
                        break
                    if (_1 is True) or (_0 == 30):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getAutoAddObj().getCapKVAr()))
                        break
                    if (_1 is True) or (_0 == 31):
                        _1 = True
                        _2 = DSSGlobals.activeCircuit.getAutoAddObj().getAddType()
                        _3 = False
                        while True:
                            if _2 == DSSGlobals.GENADD:
                                _3 = True
                                DSSGlobals.appendGlobalResult('generator')
                                break
                            if (_3 is True) or (_2 == DSSGlobals.CAPADD):
                                _3 = True
                                DSSGlobals.appendGlobalResult('capacitor')
                                break
                            break
                        break
                    if (_1 is True) or (_0 == 32):
                        _1 = True
                        if DSSGlobals.activeCircuit.isDuplicatesAllowed():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 33):
                        _1 = True
                        if DSSGlobals.activeCircuit.isZonesLocked():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 34):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getUEWeight()))
                        break
                    if (_1 is True) or (_0 == 35):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getLossWeight()))
                        break
                    if (_1 is True) or (_0 == 36):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.intArrayToString(DSSGlobals.activeCircuit.getUERegs(), DSSGlobals.activeCircuit.getNumUERegs()))
                        break
                    if (_1 is True) or (_0 == 37):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.intArrayToString(DSSGlobals.activeCircuit.getLossRegs(), DSSGlobals.activeCircuit.getNumLossRegs()))
                        break
                    if (_1 is True) or (_0 == 38):
                        _1 = True
                        ckt = DSSGlobals.activeCircuit
                        DSSGlobals.globalResult = '('
                        for vBase in ckt.getLegalVoltageBases():
                            DSSGlobals.globalResult = DSSGlobals.globalResult + String.format.format('%-g, ', vBase)
                        DSSGlobals.globalResult = DSSGlobals.globalResult + ')'
                        break
                    if (_1 is True) or (_0 == 39):
                        _1 = True
                        _4 = DSSGlobals.activeCircuit.getSolution().getAlgorithm()
                        _5 = False
                        while True:
                            if _4 == Solution.NORMALSOLVE:
                                _5 = True
                                DSSGlobals.appendGlobalResult('normal')
                                break
                            if (_5 is True) or (_4 == Solution.NEWTONSOLVE):
                                _5 = True
                                DSSGlobals.appendGlobalResult('newton')
                                break
                            break
                        break
                    if (_1 is True) or (_0 == 40):
                        _1 = True
                        if DSSGlobals.activeCircuit.isTrapezoidalIntegration():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 41):
                        _1 = True
                        _6 = True
                        i = 0
                        while True:
                            if _6 is True:
                                _6 = False
                            else:
                                i += 1
                            if not (i < DSSGlobals.activeCircuit.getAutoAddBusList().listSize()):
                                break
                            DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getAutoAddBusList().get(i))
                        break
                    if (_1 is True) or (_0 == 42):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.getControlModeID())
                        break
                    if (_1 is True) or (_0 == 43):
                        _1 = True
                        if DSSGlobals.activeCircuit.getControlQueue().getTrace():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 44):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getGenMultiplier()))
                        break
                    if (_1 is True) or (_0 == 45):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getDefaultDailyShapeObj().getName())
                        break
                    if (_1 is True) or (_0 == 46):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getDefaultYearlyShapeObj().getName())
                        break
                    if (_1 is True) or (_0 == 47):
                        _1 = True
                        DSSGlobals.appendGlobalResult('Get function not applicable.')
                        break
                    if (_1 is True) or (_0 == 48):
                        _1 = True
                        if DSSGlobals.activeCircuit.isPositiveSequence():
                            DSSGlobals.appendGlobalResult('positive')
                        else:
                            DSSGlobals.appendGlobalResult('multiphase')
                        break
                    if (_1 is True) or (_0 == 49):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getPriceSignal()))
                        break
                    if (_1 is True) or (_0 == 50):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getPriceCurve())
                        break
                    if (_1 is True) or (_0 == 51):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.activeCircuit.getActiveCktElement().getActiveTerminalIdx()))
                        break
                    if (_1 is True) or (_0 == 52):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-g', DSSGlobals.activeCircuit.getFundamental()))
                        break
                    if (_1 is True) or (_0 == 53):
                        _1 = True
                        sol = DSSGlobals.activeCircuit.getSolution()
                        if sol.isDoAllHarmonics():
                            DSSGlobals.appendGlobalResult('ALL')
                        else:
                            _7 = True
                            i = 0
                            while True:
                                if _7 is True:
                                    _7 = False
                                else:
                                    i += 1
                                if not (i < sol.getHarmonicListSize()):
                                    break
                                DSSGlobals.appendGlobalResult(String.format.format('%-g', sol.getHarmonicList()[i]))
                        break
                    if (_1 is True) or (_0 == 54):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.valueOf.valueOf(DSSGlobals.activeCircuit.getSolution().getMaxControlIterations()))
                        break
                    if (_1 is True) or (_0 == 55):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getBusList().get(DSSGlobals.activeCircuit.getActiveBusIndex()))
                        break
                    if (_1 is True) or (_0 == 56):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.DSSDataDirectory)
                        break
                    if (_1 is True) or (_0 == 57):
                        _1 = True
                        ckt = DSSGlobals.activeCircuit
                        _8 = True
                        i = 0
                        while True:
                            if _8 is True:
                                _8 = False
                            else:
                                i += 1
                            if not (i < ckt.getNumBuses()):
                                break
                            if ckt.getBuses()[i].isKeep():
                                DSSGlobals.appendGlobalResult(ckt.getBusList().get(i))
                        break
                    if (_1 is True) or (_0 == 58):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getReductionStrategyString())
                        break
                    if (_1 is True) or (_0 == 59):
                        _1 = True
                        if DSSGlobals.energyMeterClass.isSaveDemandInterval():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 60):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-.g', DSSGlobals.activeCircuit.getPctNormalFactor()))
                        break
                    if (_1 is True) or (_0 == 61):
                        _1 = True
                        if DSSGlobals.energyMeterClass.isDIVerbose():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 62):
                        _1 = True
                        DSSGlobals.appendGlobalResult(DSSGlobals.activeCircuit.getCaseName())
                        break
                    if (_1 is True) or (_0 == 63):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.activeCircuit.getNodeMarkerCode()))
                        break
                    if (_1 is True) or (_0 == 64):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.activeCircuit.getNodeMarkerWidth()))
                        break
                    if (_1 is True) or (_0 == 65):
                        _1 = True
                        if DSSGlobals.activeCircuit.isLogEvents():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 66):
                        _1 = True
                        if DSSExecutive.getInstance().isRecorderOn():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 67):
                        _1 = True
                        if DSSGlobals.energyMeterClass.isDo_OverloadReport():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 68):
                        _1 = True
                        if DSSGlobals.energyMeterClass.isDo_VoltageExceptionReport():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 69):
                        _1 = True
                        DSSGlobals.appendGlobalResult('Get function not applicable.')
                        break
                    if (_1 is True) or (_0 == 70):
                        _1 = True
                        if DSSGlobals.autoShowExport:
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 71):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.maxAllocationIterations))
                        break
                    if (_1 is True) or (_0 == 72):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.defaultBaseFreq))
                        break
                    if (_1 is True) or (_0 == 73):
                        _1 = True
                        if DSSGlobals.activeCircuit.isMarkSwitches():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 74):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.activeCircuit.getSwitchMarkerCode()))
                        break
                    if (_1 is True) or (_0 == 75):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%-.6g', DSSGlobals.daisySize))
                        break
                    if (_1 is True) or (_0 == 76):
                        _1 = True
                        if DSSGlobals.activeCircuit.isMarkTransformers():
                            DSSGlobals.appendGlobalResult('Yes')
                        else:
                            DSSGlobals.appendGlobalResult('No')
                        break
                    if (_1 is True) or (_0 == 77):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.activeCircuit.getTransMarkerCode()))
                        break
                    if (_1 is True) or (_0 == 78):
                        _1 = True
                        DSSGlobals.appendGlobalResult(String.format.format('%d', DSSGlobals.activeCircuit.getTransMarkerSize()))
                        break
                    if (_1 is True) or (_0 == 79):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.getActiveLoadShapeClass())
                        break
                    if (_1 is True) or (_0 == 80):
                        _1 = True
                        DSSGlobals.appendGlobalResult(Utilities.getEarthModel(DSSGlobals.defaultEarthModel))
                        break
                    if True:
                        _1 = True
                        break
                    break
                paramName = Parser.getInstance().getNextParam()
                param = Parser.getInstance().makeString()
        except Exception, e:
            DSSGlobals.appendGlobalResult('***Error***')
        return result
