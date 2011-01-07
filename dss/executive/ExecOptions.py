# Copyright (C) 2010 Richard Lincoln
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, USA

class ExecOptions(object):
    """Defines options for an executive.
    """

    def __init__(self, executive, type='', element='', hour=0, sec=0L, year=0,
            frequency=0.0, stepSize=0.0, mode="Snapshot", random="Uniform",
            number=0, time=0, circuit='', editor='', tolerance=0.0, maxIter=0,
            loadModel="Powerflow", loadMult=0.0, normVMinPU=0.0,
            normVMaxPU=0.0, emergVMinPU=0.0, emergVMaxPU=0.0, pctMean=0.0,
            pctStdDev=0.0, pctGrowth=0.0, genKW=0.0, genPF=0.0, capKVAr=0.0,
            addType="Generator", allowDuplicates=False, zoneLock=False,
            UEWeight=0.0, lossWeight=0.0, UERegs=0, lossRegs=0,
            voltageBases=0.0, algorithm="Normal", trapezoidal=False,
            controlMode="Static", traceMode=False, genMult=0.0,
            allocationFactor=0.0, cktModel="Multiphase", priceSignal=0.0,
            baseFrequency=0.0, harmonics=0, maxController=0, dataPath='',
            reduceOption="Stubs", demandInterval=False, pctNormal=0.0,
            DIVerbose=False, caseName='', markerCode='', nodeWidth=0.0,
            log=False, recorder=False, overloadReport=False,
            voltageExceptionReport=False, LDCurve=None, autoBusList=None,
            defaultDaily=None, defaultYearly=None, priceCurve=None,
            terminal=None, bus=None, keepList=None):
        """Initialises a new 'ExecOptions' instance.
        """
        self.executive = executive

        #: Active DSS class type.
        self.type = type

        #: Active DSS element name. You can use the complete object spec (class
        #  name) or just the name.  If full name is specifed, class becomes the
        #  active class, also.
        self.element = element

        #: Start hour of the solution.
        self.hour = hour

        #: Seconds from the hour for the start time of the solution.
        self.sec = sec

        #: Year to be used for the solution.  For certain solution types, this
        #  determines the growth multiplier.
        self.year = year

        #: Frequency for the solution of the active circuit.
        self.frequency = frequency

        #: Time step in sec for the active circuit.  Nominally for dynamics
        #  solution.
        self.stepSize = stepSize

        #: Setting the Mode property resets all monitors and energy meters. It
        #  also resets the time step, etc. to defaults for each mode.  After
        #  the initial reset, the user must explicitly reset the monitors
        #  and/or meters until another Set Mode= command. Values are:
        #  "Snapshot", "Daily", "Direct", "Dutycycle", "Dynamic", "Harmonic",
        #  "MonteCarlo1", "MonteCarlo2", "MonteCarlo3", "FaultStudy", "Yearly",
        #  "MonteFault", "Peakday", "LoadDuration1", "LoadDuration2", "AutoAdd"
        self.mode = mode

        #: For Monte Carlo variables. Values are: "Uniform", "Gaussian",
        #  "Lognormal", "None"
        self.random = random

        #: Number of solutions to perform for Monte Carlo or dutycycle
        #  solutions.
        self.number = number

        #: Specify the solution start time as an array: {hour, secs}
        self.time = time

        #: Active circuit name.
        self.circuit = circuit

        #: Command string required to start up the editor preferred by the
        #  user.
        self.editor = editor

        #: Solution tolerance.
        self.tolerance = tolerance

        #: Maximum allowable iterations for power flow solutions.
        self.maxIter = maxIter

        #: If admittance, a non-iterative, direct solution is done with all
        #  loads and generators modeled by their equivalent admittance. Values
        #  are: "Powerflow", "Admittance"
        self.loadModel = loadModel

        #: Global load multiplier for this circuit.  Does not affect loads
        #  designated to be 'fixed'.  All other base kW values are multiplied
        #  by this number. Defaults to 1.0 when the circuit is created. As with
        #  other values, it always stays at the last value to which it was set
        #  until changed again.
        self.loadMult = loadMult

        #: Minimum permissible per unit voltage for normal conditions.
        self.normVMinPU = normVMinPU

        #: Maximum permissible per unit voltage for normal conditions.
        self.normVMaxPU = normVMaxPU

        #: Minimum permissible per unit voltage for emergency (contingency)
        #  conditions.
        self.emergVMinPU = emergVMinPU

        #: Maximum permissible per unit voltage for emergency (contingency)
        #  conditions.
        self.emergVMaxPU = emergVMaxPU

        #: Percent mean to use for global load multiplier.
        self.pctMean = pctMean

        #: Percent Standard deviation to use for global load multiplier.
        self.pctStdDev = pctStdDev

        #: Default annual growth rate, percent, for loads with no growth curve
        #  specified.
        self.pctGrowth = pctGrowth

        #: Size of generator, kW, to automatically add to system.
        self.genKW = genKW

        #: Power factor of generator to assume for automatic addition.
        self.genPF = genPF

        #: Size of capacitor, kVAR, to automatically add to system.
        self.capKVAr = capKVAr

        #: Type of device for AutoAdd Mode. Values are: "Generator",
        #  "Capacitor"
        self.addType = addType

        #: Flag to indicate if it is OK to have devices of same name in the
        #  same class. If No, then a New command is treated as an Edit command.
        #  If Yes, then a New command will always result in a device being
        #  added.
        self.allowDuplicates = allowDuplicates

        #: If No, then meter zones are recomputed each time there is a change
        #  in the circuit. If Yes, then meter zones are not recomputed unless
        #  they have not yet been computed. Meter zones are normally recomputed
        #  on Solve command following a circuit change.
        self.zoneLock = zoneLock

        #: Weighting factor for UE/EEN in AutoAdd functions.  Autoadd mode
        #  minimizes (Lossweight * Losses + UEweight * UE).  If you wish to
        #  ignore UE, set to 0.  This applies only when there are EnergyMeter
        #  objects. Otherwise, AutoAdd mode minimizes total system losses.
        self.UEWeight = UEWeight

        #: Weighting factor for Losses in AutoAdd functions. Autoadd mode
        #  minimizes (Lossweight * Losses + UEweight * UE).  If you wish to
        #  ignore Losses, set to 0. This applies only when there are
        #  EnergyMeter objects. Otherwise, AutoAdd mode minimizes total system
        #  losses.
        self.lossWeight = lossWeight

        #: Which EnergyMeter register(s) to use for UE in AutoAdd Mode. May be
        #  one or more registers.  If more than one, register values are summed
        #  together. Array of integer values > 0.  Defaults to 11 (for Load
        #  EEN).  For a list of EnergyMeter register numbers, do the 'Show
        #  Meters' command after defining a circuit.
        self.UERegs = UERegs

        #: Which EnergyMeter register(s) to use for Losses in AutoAdd Mode. May
        #  be one or more registers.  If more than one, register values are
        #  summed together.  Array of integer values > 0.  Defaults to 13 (for
        #  Zone kWh Losses).  For a list of EnergyMeter register numbers, do
        #  the 'Show Meters' command after defining a circuit.
        self.lossRegs = lossRegs

        #: Legal bus voltage bases for this circuit.  Enter an array of the
        #  legal voltage bases, in phase-to-phase voltages, for example:
        #  {.208, .480, 12.47, 24.9, 34.5, 115.0, 230.0}  When the
        #  CalcVoltageBases command is issued, a snapshot solution is performed
        #  with no load injections and the bus base voltage is set to the
        #  nearest legal voltage base. The defaults are as shown in the example
        #  above.
        self.voltageBases = voltageBases

        #: Solution algorithm type.  Normal is a fixed point iteration that is
        #  a little quicker than the Newton iteration.  Normal is adequate for
        #  most radial distribution circuits.  Newton is more robust for
        #  circuits that are difficult to solve. Values are: "Normal", "Newton"
        self.algorithm = algorithm

        #: Specifies whether to use trapezoidal integration for accumulating
        #  energy meter registers. Applies to EnergyMeter and Generator
        #  objects.  Default method simply multiplies the present value of the
        #  registers times the width of the interval. Trapezoidal is more
        #  accurate when there are sharp changes in a load shape or unequal
        #  intervals. Trapezoidal is automatically used for some load-duration
        #  curve simulations where the interval size varies considerably. Keep
        #  in mind that for Trapezoidal, you have to solve one more point than
        #  the number of intervals. That is, to do a Daily simulation on a
        #  24-hr load shape, you would set Number=25 to force a solution at the
        #  first point again to establish the last (24th) interval.
        self.trapezoidal = trapezoidal

        #: Control mode for the solution. Values are: "Static", "Event", "Time"
        self.controlMode = controlMode

        #: Set to YES to trace the actions taken in the control queue.  Creates
        #  a file named TRACE_CONTROLQUEUE.CSV in the default directory. The
        #  names of all circuit elements taking an action are logged.
        self.traceMode = traceMode

        #: Global multiplier for the kW output of every generator in the
        #  circuit.  Applies to all but Autoadd solution modes. Ignored for
        #  generators designated as Status=Fixed.
        self.genMult = genMult

        #: Allocation factor to which all loads in the active circuit are set.
        self.allocationFactor = allocationFactor

        #: Designates whether circuit model is to interpreted as a normal
        #  multi-phase model or a positive-sequence only model. Values are:
        #  "Multiphase", "Positive"
        self.cktModel = cktModel

        #: Price signal ($/MWh) for the circuit.
        self.priceSignal = priceSignal

        #: Set the fundamental frequency for harmonic solution and the default
        #  base frequency for all impedance quantities. Side effect: also
        #  changes the value of the solution frequency.
        self.baseFrequency = baseFrequency

        #: Array of harmonics for which to perform a solution in Harmonics
        #  mode. If ALL, then solution is performed for all harmonics defined
        #  in spectra currently being used.  Otherwise, specify a more limited
        #  list such as:  {1, 5, 7, 11, 13}
        self.harmonics = harmonics

        #: Max control iterations per solution.
        self.maxController = maxController

        #: Set the data path for files written or read by the DSS.  Defaults to
        #  the startup path. May be Null.  Executes a CHDIR to this path if
        #  non-null.  Does not require a circuit defined.
        self.dataPath = dataPath

        #: Strategy for reducing feeders. Values are: "Stubs", "MergeParallel",
        #  "Breakloops", "Tapends", "Ends", "Switches"
        self.reduceOption = reduceOption

        #: Set for keeping demand interval data for daily, yearly, etc,
        #  simulations.  Side Effect:  Resets all meters!!!
        self.demandInterval = demandInterval

        #: Normal rating of all lines to a specified percent of the emergency
        #  rating.  Note: This action takes place immediately. Only the in
        #  memory value is changed for the duration of the run.
        self.pctNormal = pctNormal

        #: Set to Yes/True if you wish a separate demand interval (DI) file
        #  written for each meter.  Otherwise, only the totalizing meters are
        #  written.
        self.DIVerbose = DIVerbose

        #: Name of case for yearly simulations with demand interval data.
        #  Becomes the name of the subdirectory under which all the year data
        #  are stored. Default = circuit name.  Side Effect: Sets the prefix
        #  for output files
        self.caseName = caseName

        #: Number code for node marker on circuit plots (SDL MarkAt options).
        self.markerCode = markerCode

        #: Width of node marker.
        self.nodeWidth = nodeWidth

        #: Significant solution events are added to the Event Log, primarily
        #  for debugging.
        self.log = log

        #: Opens DSSRecorder.DSS in DSS install folder and enables recording of
        #  all commands that come through the text command interface.  Closed
        #  by either setting to NO/FALSE or exiting the program.  When closed
        #  by this command, the file name can be found in the Result.  Does not
        #  require a circuit defined.
        self.recorder = recorder

        #: For yearly solution mode, sets overload reporting on/off.
        #  DemandInterval must be set to true for this to have effect.
        self.overloadReport = overloadReport

        #: For yearly solution mode, sets voltage exception reporting on/off.
        #  DemandInterval must be set to true for this to have effect.
        self.voltageExceptionReport = voltageExceptionReport

        #: Global load multiplier is defined by this curve for LD1 and LD2
        #  solution modes.
        self.LDCurve = LDCurve

        self.autoBusList = [] if autoBusList is None else autoBusList

        #: Default daily load shape.
        self.defaultDaily = defaultDaily

        #: Default yearly load shape.
        self.defaultYearly = defaultYearly

        #: The curve to use to obtain for price signal. Default is none (null
        #  string).  If none, price signal either remains constant or is set by
        #  an external process.  Curve is defined as a loadshape (not
        #  normalized) and should correspond to the type of analysis being
        #  performed (daily, yearly, load-duration, etc.).
        self.priceCurve = priceCurve

        #: Active terminal of the active circuit element.
        self.terminal = terminal

        #: Active bus.
        self.bus = bus

        self.keepList = [] if keepList is None else keepList
