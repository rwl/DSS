from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.shared.Dynamics import (Dynamics,)
from dss.common.impl.YMatrix import (YMatrix,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.SolverError import (SolverError,)
from dss.common.impl.SolutionAlgs import (SolutionAlgs,)
from dss.common.impl.ControlProblem import (ControlProblem,)
from dss.common.SolutionObj import (SolutionObj,)
from dss.shared.impl.DynamicsRec import (DynamicsRec,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.Esolv32Problem import (Esolv32Problem,)
from dss.common.impl.SolutionImpl import (SolutionImpl,)
from dss.common.Solution import (Solution,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
# from com.epri.dss.common.impl.DSSBus.NodeBus import (NodeBus,)
# from java.io.PrintWriter import (PrintWriter,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class SolutionObjImpl(DSSObjectImpl, SolutionObj):
    # Array of delta V for Newton iteration
    dV = None
    frequency = None
    algorithm = None
    # NORMALSOLVE or NEWTONSOLVE
    auxCurrents = None
    # for injections like AutoAdd
    controlActionsDone = None
    controlIteration = None
    controlMode = None
    # EVENTDRIVEN, TIMEDRIVEN
    convergenceTolerance = None
    convergedFlag = None
    defaultControlMode = None
    # EVENTDRIVEN, TIMEDRIVEN
    defaultLoadModel = None
    # 1=POWERFLOW  2=ADMITTANCE
    doAllHarmonics = None
    dynamicsAllowed = None
    dynaVars = DynamicsRec()
    errorSaved = None
    firstIteration = None
    # Flag set to true if something has altered the frequency
    frequencyChanged = None
    year = None
    harmonic = None
    harmonicList = None
    harmonicListSize = None
    intHour = None
    dblHour = None
    # Main (system) Y matrix
    YSystem = None
    # Series Y matrix
    YSeries = None
    # Either Ysystem or Yseries
    Y = None
    intervalHrs = None
    # solution interval since last solution, hrs.
    isDynamicModel = None
    isHarmonicModel = None
    iteration = None
    loadModel = None
    # 1=POWERFLOW  2=ADMITTANCE
    lastSolutionWasDirect = None
    loadsNeedUpdating = None
    maxControlIterations = None
    maxError = None
    maxIterations = None
    mostIterationsDone = None
    nodeVBase = None
    numberOfTimes = None
    # number of times to solve
    preserveNodeVoltages = None
    randomType = None
    # 0 = NONE; 1 = GAUSSIAN; 2 = UNIFORM
    seriesYInvalid = None
    solutionCount = None
    # counter incremented for each solution
    solutionInitialized = None
    systemYChanged = None
    useAuxCurrents = None
    VMagSaved = None
    voltageBaseChanged = None
    # Main system voltage array
    nodeV = None
    # Main system currents array
    currents = None

    def __init__(self, parClass, solutionName):
        super(SolutionObjImpl, self)(parClass)
        self.setName(solutionName.toLowerCase())
        self.year = 0
        self.intHour = 0
        self.dynaVars.t = 0.0
        self.dblHour = 0.0
        self.dynaVars.tstart = 0.0
        self.dynaVars.tstop = 0.0
        # duration = 0.0;
        self.dynaVars.h = 0.001
        # default for dynasolve
        self.loadsNeedUpdating = True
        self.voltageBaseChanged = True
        # forces building of convergence check arrays
        self.maxIterations = 15
        self.maxControlIterations = 10
        self.convergenceTolerance = 0.0001
        self.convergedFlag = False
        self.isDynamicModel = False
        self.isHarmonicModel = False
        self.setFrequency(DSSGlobals.defaultBaseFreq)
        # Fundamental = 60.0; moved to circuit and used as default base frequency
        self.harmonic = 1.0
        self.frequencyChanged = True
        # force building of YPrim matrices
        self.doAllHarmonics = True
        self.firstIteration = True
        self.dynamicsAllowed = False
        self.systemYChanged = True
        self.seriesYInvalid = True
        # Define default harmonic list
        self.harmonicListSize = 5
        self.harmonicList = [None] * self.harmonicListSize
        self.harmonicList[0] = 1.0
        self.harmonicList[1] = 5.0
        self.harmonicList[2] = 7.0
        self.harmonicList[3] = 11.0
        self.harmonicList[4] = 13.0
        self.solutionInitialized = False
        self.loadModel = DSSGlobals.POWERFLOW
        self.defaultLoadModel = self.loadModel
        self.lastSolutionWasDirect = False
        self.YSeries = None
        self.YSystem = None
        self.Y = None
        self.nodeV = None
        self.dV = None
        self.currents = None
        self.auxCurrents = None
        self.VMagSaved = None
        self.errorSaved = None
        self.nodeVBase = None
        self.useAuxCurrents = False
        self.solutionCount = 0
        self.dynaVars.solutionMode = Dynamics.SNAPSHOT
        self.controlMode = DSSGlobals.CTRLSTATIC
        self.defaultControlMode = self.controlMode
        self.algorithm = Solution.NORMALSOLVE
        self.randomType = DSSGlobals.GAUSSIAN
        # default to Gaussian
        self.numberOfTimes = 100
        self.intervalHrs = 1.0
        self.initPropertyValues(0)

    def solve(self):
        """Main solution dispatch."""
        DSSGlobals.activeCircuit.setIsSolved(False)
        DSSGlobals.solutionWasAttempted = True
        DSSGlobals.DSSForms.initProgressForm()
        # initialize progress form;
        # Check of some special conditions that must be met before executing solutions
        if (
            DSSGlobals.activeCircuit.getEmergMinVolts() >= DSSGlobals.activeCircuit.getNormalMinVolts()
        ):
            DSSGlobals.doSimpleMsg('Error: Emergency Min Voltage Must Be Less Than Normal Min Voltage!' + DSSGlobals.CRLF + 'Solution Not Executed.', 480)
            return
        if DSSGlobals.solutionAbort:
            DSSGlobals.globalResult = 'Solution aborted.'
            DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT
            DSSGlobals.errorNumber = DSSGlobals.cmdResult
            return
        # Main solution algorithm dispatcher
        # TODO Handle solver error
        # TODO Handle control problem
        try:
            ckt = DSSGlobals.activeCircuit
            _0 = self.year
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    ckt.setDefaultGrowthFactor(1.0)
                    break
                if True:
                    _1 = True
                    ckt.setDefaultGrowthFactor(self.Math.pow(ckt.getDefaultGrowthRate(), self.year - 1))
                    break
                break
            # fire_InitControls();
            # CheckFaultStatus;  ???? needed here??
            _2 = self.dynaVars.solutionMode
            _3 = False
            while True:
                if _2 == Dynamics.SNAPSHOT:
                    _3 = True
                    self.solveSnap()
                    break
                if (_3 is True) or (_2 == Dynamics.YEARLYMODE):
                    _3 = True
                    SolutionAlgs.solveYearly()
                    break
                if (_3 is True) or (_2 == Dynamics.DAILYMODE):
                    _3 = True
                    SolutionAlgs.solveDaily()
                    break
                if (_3 is True) or (_2 == Dynamics.DUTYCYCLE):
                    _3 = True
                    SolutionAlgs.solveDuty()
                    break
                if (_3 is True) or (_2 == Dynamics.DYNAMICMODE):
                    _3 = True
                    SolutionAlgs.solveDynamic()
                    break
                if (_3 is True) or (_2 == Dynamics.MONTECARLO1):
                    _3 = True
                    SolutionAlgs.solveMonte1()
                    break
                if (_3 is True) or (_2 == Dynamics.MONTECARLO2):
                    _3 = True
                    SolutionAlgs.solveMonte2()
                    break
                if (_3 is True) or (_2 == Dynamics.MONTECARLO3):
                    _3 = True
                    SolutionAlgs.solveMonte3()
                    break
                if (_3 is True) or (_2 == Dynamics.PEAKDAY):
                    _3 = True
                    SolutionAlgs.solvePeakDay()
                    break
                if (_3 is True) or (_2 == Dynamics.LOADDURATION1):
                    _3 = True
                    SolutionAlgs.solveLD1()
                    break
                if (_3 is True) or (_2 == Dynamics.LOADDURATION2):
                    _3 = True
                    SolutionAlgs.solveLD2()
                    break
                if (_3 is True) or (_2 == Dynamics.DIRECT):
                    _3 = True
                    self.solveDirect()
                    break
                if (_3 is True) or (_2 == Dynamics.MONTEFAULT):
                    _3 = True
                    SolutionAlgs.solveMonteFault()
                    # Monte Carlo fault cases
                    break
                if (_3 is True) or (_2 == Dynamics.FAULTSTUDY):
                    _3 = True
                    SolutionAlgs.solveFaultStudy()
                    break
                if (_3 is True) or (_2 == Dynamics.AUTOADDFLAG):
                    _3 = True
                    ckt.getAutoAddObj().solve()
                    break
                if (_3 is True) or (_2 == Dynamics.HARMONICMODE):
                    _3 = True
                    SolutionAlgs.solveHarmonic()
                    break
                if (_3 is True) or (_2 == Dynamics.GENERALTIME):
                    _3 = True
                    SolutionAlgs.solveGeneralTime()
                    break
                if True:
                    _3 = True
                    DSSGlobals.doSimpleMsg('Unknown solution mode.', 481)
                    break
                break
        except Esolv32Problem, e:
            DSSGlobals.doSimpleMsg('Error encountered in Solve: ' + e.getMessage(), 482)
            DSSGlobals.solutionAbort = True
        except SolverError, e:
            pass # astStmt: [Stmt([]), None]
        except ControlProblem, e:
            pass # astStmt: [Stmt([]), None]

    def converged(self):
        ckt = DSSGlobals.activeCircuit
        # base convergence on voltage magnitude
        self.maxError = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumNodes()):
                break
            VMag = self.nodeV[i].abs()
            # If base specified, use it; otherwise go on present magnitude
            if self.nodeVBase[i] > 0.0:
                self.errorSaved[i] = self.Math.abs(VMag - self.VMagSaved[i]) / self.nodeVBase[i]
            elif VMag != 0.0:
                self.errorSaved[i] = self.Math.abs(1.0 - (self.VMagSaved[i] / VMag))
            self.VMagSaved[i] = VMag
            # for next go-'round
            self.maxError = self.Math.max(self.maxError, self.errorSaved[i])
            # update max error
        # $IFDEF debugtrace
        # FileWriter F = new FileWriter("DebugTrace.csv", true);
        # BufferedWriter FDebug = new BufferedWriter(F);
        # if (Iteration == 1) {
        # FDebug.write("Iter");
        # for (int i = 0; i < ckt.getNumNodes(); i++)
        # FDebug.write(", " + ckt.getBusList().get(ckt.getMapNodeToBus()[i].BusRef) + "." + ckt.getMapNodeToBus()[i].NodeNum);  // TODO Implement colon syntax
        # FDebug.newLine();
        # }
        # /* ***** */  // FIXME Format number widths
        # FDebug.write(Iteration);
        # for (int i = 0; i < ckt.getNumNodes(); i++)
        # FDebug.write(", " + VmagSaved[i]);
        # FDebug.newLine();
        # FDebug.write("Err");
        # for (int i = 0; i < ckt.getNumNodes(); i++)
        # FDebug.write(", " + String.format("%-.5g", ErrorSaved[i]));
        # FDebug.newLine();
        # FDebug.write("Curr");
        # for (int i = 0; i < ckt.getNumNodes(); i++)
        # FDebug.write(", " + Currents[i].abs());
        # FDebug.newLine();
        # //		/* *****
        # FDebug.close();
        # $ENDIF
        if self.maxError <= self.convergenceTolerance:
            result = True
        else:
            result = False
        self.convergedFlag = result
        return result

    def getSourceInjCurrents(self):
        """Add in the contributions of all source type elements to the global
        solution vector InjCurr.
        """
        ckt = DSSGlobals.activeCircuit
        for pElem in ckt.getSources():
            if pElem.isEnabled():
                pElem.injCurrents()
                # uses nodeRef to add current into injCurr Array;
        if self.isHarmonicModel:
            # pick up generators and loads, too
            for pElem in ckt.getGenerators():
                if pElem.isEnabled():
                    pElem.injCurrents()
                    # uses nodeRef to add current into injCurr array;
            for pElem in ckt.getLoads():
                if pElem.isEnabled():
                    pElem.injCurrents()
                    # uses aodeRef to add current into injCurr Array;

    def setGeneratorDispRef(self):
        """Set the global generator dispatch reference."""
        ckt = DSSGlobals.activeCircuit
        _0 = self.dynaVars.solutionMode
        _1 = False
        while True:
            if _0 == Dynamics.SNAPSHOT:
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor())
                break
            if (_1 is True) or (_0 == Dynamics.YEARLYMODE):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.DAILYMODE):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.DUTYCYCLE):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.GENERALTIME):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.DYNAMICMODE):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor())
                break
            if (_1 is True) or (_0 == Dynamics.HARMONICMODE):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor())
                break
            if (_1 is True) or (_0 == Dynamics.MONTECARLO1):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor())
                break
            if (_1 is True) or (_0 == Dynamics.MONTECARLO2):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.MONTECARLO3):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.PEAKDAY):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.LOADDURATION1):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.LOADDURATION2):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor() * ckt.getDefaultHourMult().getReal())
                break
            if (_1 is True) or (_0 == Dynamics.DIRECT):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getLoadMultiplier() * ckt.getDefaultGrowthFactor())
                break
            if (_1 is True) or (_0 == Dynamics.MONTEFAULT):
                _1 = True
                ckt.setGeneratorDispatchReference(1.0)
                # Monte Carlo fault cases solve at peak load only base case
                break
            if (_1 is True) or (_0 == Dynamics.FAULTSTUDY):
                _1 = True
                ckt.setGeneratorDispatchReference(1.0)
                break
            if (_1 is True) or (_0 == Dynamics.AUTOADDFLAG):
                _1 = True
                ckt.setGeneratorDispatchReference(ckt.getDefaultGrowthFactor())
                # peak load only
                break
            if True:
                _1 = True
                DSSGlobals.doSimpleMsg('Unknown solution mode.', 483)
                break
            break

    def setGeneratordQdV(self):
        didOne = False
        ckt = DSSGlobals.activeCircuit
        # save the generator dispatch level and set on high enough to turn all generators on
        genDispSave = ckt.getGeneratorDispatchReference()
        ckt.setGeneratorDispatchReference(1000.0)
        for pGen in ckt.getGenerators():
            if pGen.isEnabled():
                # for PV generator models only ...
                if pGen.getGenModel() == 3:
                    pGen.initDQDVCalc()
                    # solve at base var setting
                    self.iteration = 0
                    while not self.converged() and self.iteration < self.maxIterations:
                        self.iteration += 1
                        self.zeroInjCurr()
                        self.getSourceInjCurrents()
                        pGen.injCurrents()
                        # get generator currents with nominal vars
                        self.solveSystem(self.nodeV)
                    pGen.rememberQV()
                    # remember Q and V
                    pGen.bumpUpQ()
                    # solve after changing vars
                    self.iteration = 0
                    while not self.converged() and self.iteration < self.maxIterations:
                        self.iteration += 1
                        self.zeroInjCurr()
                        self.getSourceInjCurrents()
                        pGen.injCurrents()
                        # get generator currents with nominal vars
                        self.solveSystem(self.nodeV)
                    pGen.calcDQDV()
                    # bssed on remembered Q and V and present values of same
                    pGen.resetStartPoint()
                    didOne = True
        # restore generator dispatch reference
        ckt.setGeneratorDispatchReference(genDispSave)
        try:
            if (
                # reset initial solution
                didOne
            ):
                self.solveZeroLoadSnapShot()
        except Esolv32Problem, e:
            DSSGlobals.doSimpleMsg('From setGenerator dQdV, solveZeroLoadSnapShot: ' + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7071)
            raise SolverError('Aborting')

    def doNormalSolution(self):
        """Normal fixed-point solution.

          Vn+1 = [Y]-1 Injcurr

        Where Injcurr includes only PC elements (loads, generators, etc.)
        i.e., the shunt elements.

        InjCurr are the current injected into the node (need to reverse
        current direction for loads).

        @throws Esolv32Problem
        """
        self.iteration = 0
        ckt = DSSGlobals.activeCircuit
        # **** Main iteration loop ****
        while (
            (not # TODO Double-check reverse logicself.converged()) or (self.iteration <= 1) and self.iteration < self.maxIterations
        ):
            self.iteration += 1
            if ckt.isLogEvents():
                Utilities.logThisEvent('Solution Iteration ' + String.valueOf.valueOf(self.iteration))
                # Get injcurrents for all PC devices
            self.zeroInjCurr()
            self.getSourceInjCurrents()
            # sources
            self.getPCInjCurr()
            # get the injection currents from all the power conversion devices and feeders
            # the above call could change the primitive Y matrix, so have to check
            if self.systemYChanged:
                YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, False)
                # does not realloc V, I
            if self.useAuxCurrents:
                self.addInAuxCurrents(Solution.NORMALSOLVE)
                # // solve for voltages      /* Note: NodeV[0] = 0 + j0 always
            if ckt.isLogEvents():
                Utilities.logThisEvent('Solve sparse set doNormalSolution ...')
            self.solveSystem(self.nodeV)
            self.loadsNeedUpdating = False

    def doNewtonSolution(self):
        """Newton iteration.

          Vn+1 =  Vn - [Y]-1 Termcurr

        Where termCurr includes currents from all elements and we are
        attempting to get the  currents to sum to zero at all nodes.

        TermCurr is the sum of all currents going into the terminals of
        the elements.

        For PD Elements, termCurr = Yprim*V

        For Loads, termCurr = (Sload / V)*
        For Generators, termCurr = -(Sgen / V)

        @throws Esolv32Problem *
        """
        ckt = DSSGlobals.activeCircuit
        self.dV = Utilities.resizeArray(self.dV, ckt.getNumNodes() + 1)
        # make sure this is always big enough
        if self.controlIteration == 1:
            self.getPCInjCurr()
            # update the load multipliers for this solution
        self.iteration = 0
        while (
            (not # TODO Double-check reverse logicself.converged()) or (self.iteration <= 1) and self.iteration < self.maxIterations
        ):
            self.iteration += 1
            self.solutionCount += 1
            # sumAllCurrents uses ITerminal, so must force a recalc
            # get sum of currents at all nodes for all devices
            self.zeroInjCurr()
            self.sumAllCurrents()
            # call to current calc could change YPrim for some devices
            if self.systemYChanged:
                YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, False)
                # does not realloc V, I
            if self.useAuxCurrents:
                self.addInAuxCurrents(Solution.NEWTONSOLVE)
                # solve for change in voltages
            self.solveSystem(self.dV)
            self.loadsNeedUpdating = False
            # compute new guess at voltages
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumNodes()):
                    break
                # 0 node is always 0
                self.nodeV[i] = Complex(self.nodeV[i].getReal() - self.dV[i].getReal(), self.nodeV[i].getImaginary() - self.dV[i].getImaginary())

    def doPFlowSolution(self):
        self.solutionCount += 1
        # unique number for this solution
        if self.voltageBaseChanged:
            YMatrix.initializeNodeVbase()
            # for convergence test
        if not self.solutionInitialized:
            if DSSGlobals.activeCircuit.isLogEvents():
                Utilities.logThisEvent('Initializing Solution')
            # solveZeroLoadSnapShot();
            try:
                self.solveYDirect()
                # 8-14-06 this should give a better answer than zero load snapshot
            except Esolv32Problem, e:
                DSSGlobals.doSimpleMsg('From doPFlowSolution().solveYDirect(): ' + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7072)
                raise SolverError('Aborting')
            if DSSGlobals.solutionAbort:
                return
                # initialization can result in abort
            # The above resets the active sparse set to hY
            try:
                self.setGeneratordQdV()
                # set dQdV for model 3 generators
            except Esolv32Problem, e:
                DSSGlobals.doSimpleMsg('From doPFlowSolution.setGeneratordQdV(): ' + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7073)
                raise SolverError('Aborting')
            self.solutionInitialized = True
        _0 = self.algorithm
        _1 = False
        while True:
            if _0 == Solution.NORMALSOLVE:
                _1 = True
                self.doNormalSolution()
                break
            if (_1 is True) or (_0 == Solution.NEWTONSOLVE):
                _1 = True
                self.doNewtonSolution()
                break
            break
        DSSGlobals.activeCircuit.setIsSolved(self.convergedFlag)
        self.lastSolutionWasDirect = False

    def solveZeroLoadSnapShot(self):
        """Solve without load for initialization purposes.

        @throws Esolv32Problem
        """
        if self.systemYChanged or self.seriesYInvalid:
            YMatrix.buildYMatrix(YMatrix.SERIESONLY, True)
            # Side effect: allocates V
        self.solutionCount += 1
        # unique number for this solution
        self.zeroInjCurr()
        # Side effect: allocates InjCurr
        self.getSourceInjCurrents()
        # Vsource and Isource only
        # Make the series Y matrix the active matrix
        if self.YSeries is None:
            raise Esolv32Problem('Series Y matrix not built yet in solveZeroLoadSnapshot().')
        self.Y = self.YSeries
        if DSSGlobals.activeCircuit.isLogEvents():
            Utilities.logThisEvent('Solve Sparse Set ZeroLoadSnapshot ...')
        self.solveSystem(self.nodeV)
        # also sets voltages in radial part of the circuit if radial solution
        # Reset the main system Y as the solution matrix
        if self.YSystem is not None and not DSSGlobals.solutionAbort:
            self.Y = self.YSystem
        return 0

    def setVoltageBases(self):
        """Set voltage bases using voltage at first node (phase) of a bus.

        @throws SolverError
        """
        ckt = DSSGlobals.activeCircuit
        # don't allow the meter zones to auto-build in this load flow
        # solution, because the voltage bases are not available yet
        try:
            bZoneCalc = ckt.isMeterZonesComputed()
            bZoneLock = ckt.isZonesLocked()
            ckt.setMeterZonesComputed(True)
            ckt.setZonesLocked(True)
            self.solveZeroLoadSnapShot()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                bus = ckt.getBuses()[i]
                bus.setKVBase(Utilities.nearestBasekV(self.nodeV[bus.getRef(0)].abs() * 0.001732) / DSSGlobals.SQRT3)
                # l-n base kV  TODO Check zero based indexing
            YMatrix.initializeNodeVbase()
            # for convergence test
            ckt.setIsSolved(True)
            # now build the meter zones
            ckt.setMeterZonesComputed(bZoneCalc)
            ckt.setZonesLocked(bZoneLock)
            ckt.doResetMeterZones()
        except Esolv32Problem, e:
            DSSGlobals.doSimpleMsg('From setVoltageBases().solveZeroLoadSnapShot(): ' + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075)
            raise SolverError('Aborting')

    def snapShotInit(self):
        self.setGeneratorDispRef()
        self.controlIteration = 0
        self.controlActionsDone = False
        self.mostIterationsDone = 0
        self.loadsNeedUpdating = True
        # force the loads to update at least once

    def checkControls(self):
        """Snapshot checks with matrix rebuild.

        @throws ControlProblem
        @throws Esolv32Problem
        """
        if self.controlIteration < self.maxControlIterations:
            if self.convergedFlag:
                if DSSGlobals.activeCircuit.isLogEvents():
                    Utilities.logThisEvent('Control Iteration ' + String.valueOf.valueOf(self.controlIteration))
                self.sampleDoControlActions()
                self.checkFaultStatus()
            else:
                self.controlActionsDone = True
                # stop solution process if failure to converge
        if self.systemYChanged:
            YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, False)
            # rebuild Y matrix, but V stays same

    def solveSnap(self):
        """Solve for now once.

        @throws SolverError
        @throws ControlProblem
        @throws Esolv32Problem
        """
        result = 0
        totalIterations = 0
        self.snapShotInit()
        while (
            not self.controlActionsDone and self.controlIteration < self.maxControlIterations
        ):
            self.controlIteration += 1
            result = self.solveCircuit()
            # do circuit solution w/o checking controls
            # Now check controls
            # TODO $IFDEF DLL_ENGINE
            # fire_checkControls();
            # TODO $ENDIF
            self.checkControls()
            # For reporting max iterations per control iteration
            if self.iteration > self.mostIterationsDone:
                self.mostIterationsDone = self.iteration
            totalIterations = totalIterations + self.iteration
        if (
            not self.controlActionsDone and self.controlIteration >= self.maxControlIterations
        ):
            DSSGlobals.doSimpleMsg('Warning: max control iterations exceeded. ' + DSSGlobals.CRLF + 'Tip: Show eventLog to debug control settings.', 485)
            DSSGlobals.solutionAbort = True
            # this will stop this message in dynamic power flow modes
        if DSSGlobals.activeCircuit.isLogEvents():
            Utilities.logThisEvent('Solution done')
            # TODO $IFDEF DLL_ENGINE
            # fire_StepControls();
            # $ENDIF
        self.iteration = totalIterations
        # so that it reports a more interesting number
        return result

    def solveDirect(self):
        """Solve for now once, direct solution.

        @throws Esolv32Problem
        """
        self.loadsNeedUpdating = True
        # force possible update of loads and generators
        if self.systemYChanged:
            YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, True)
            # Side effect: allocates V
        self.solutionCount += 1
        # unique number for this solution
        self.zeroInjCurr()
        # Side fffect: allocates InjCurr
        self.getSourceInjCurrents()
        self.getMachineInjCurrents()
        # need this in dynamics mode to pick up injections
        if self.solveSystem(self.nodeV) == 1:
            # solve with zero injection current
            DSSGlobals.activeCircuit.setIsSolved(True)
            self.convergedFlag = True
        self.iteration = 1
        self.lastSolutionWasDirect = True
        return 0

    def solveCircuit(self):
        """SolveSnap sans control iteration.

        @throws SolverError
        """
        if self.loadModel == DSSGlobals.ADMITTANCE:
            try:
                self.solveDirect()
                # no sense horsing around when it's all admittance
            except Esolv32Problem, e:
                DSSGlobals.doSimpleMsg('From solveSnap().solveDirect(): ' + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7075)
                raise SolverError('Aborting')
        else:
            try:
                if self.systemYChanged:
                    YMatrix.buildYMatrix(YMatrix.WHOLEMATRIX, True)
                    # Side effect: allocates V
                self.doPFlowSolution()
            except Esolv32Problem, e:
                DSSGlobals.doSimpleMsg('From solveSnap().doPFlowSolution(): ' + DSSGlobals.CRLF + e.getMessage() + YMatrix.checkYMatrixforZeroes(), 7074)
                raise SolverError('Aborting')
        return 0

    def zeroInjCurr(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < DSSGlobals.activeCircuit.getNumNodes()):
                break
            self.currents[i] = Complex.ZERO

    def getPCInjCurr(self):
        """Get inj currents from all enabled PC devices."""
        ckt = DSSGlobals.activeCircuit
        for pElem in ckt.getPCElements():
            if pElem.isEnabled():
                pElem.injCurrents()
                # uses nodeRef to add current into injCurr array

    def dumpProperties(self, F, Complete):
        # TODO Translate this method
        pass

    def vDiff(self, i, j):
        """Difference between two node voltages."""
        return self.nodeV[i].subtract(self.nodeV[j])
        # V1-V2;

    def writeConvergenceReport(self, fileName):
        try:
            fw = self.FileWriter(fileName)
            f = PrintWriter(fw)
            print 
            print '-------------------'
            print 'Convergence Report:'
            print '-------------------'
            print '\"Bus.Node\", \"Error\", \"|V|\",\"Vbase\"'
            ckt = DSSGlobals.activeCircuit
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumNodes()):
                    break
                nb = ckt.getMapNodeToBus()[i]
                f.print_('\"' + Utilities.pad(ckt.getBusList().get(nb.busRef) + '.' + String.valueOf.valueOf(nb.nodeNum) + '\"', 18))
                f.printf(', %10.5s', self.errorSaved[i])
                f.printf(', %14s', self.VMagSaved[i])
                f.printf(', %14s', self.nodeVBase[i])
                # TODO Check text padding
                print 
            print 
            f.printf('Max Error = %10.5s', self.maxError)
            f.close()
        except IOException, e:
            e.printStackTrace()
        finally:
            Utilities.fireOffEditor(fileName)
        # TODO Auto-generated catch block

    def sumAllCurrents(self):
        ckt = DSSGlobals.activeCircuit
        for pElem in ckt.getCktElements():
            pElem.sumCurrents()
            # sum terminal currents into system currents array

    def doControlActions(self):
        xHour = MutableInt()
        xSec = MutableDouble()
        ckt = DSSGlobals.activeCircuit
        _0 = self.controlMode
        _1 = False
        while True:
            if _0 == DSSGlobals.CTRLSTATIC:
                _1 = True
                if ckt.getControlQueue().isEmpty():
                    self.controlActionsDone = True
                else:
                    ckt.getControlQueue().doNearestActions(xHour, xSec)
                    # ignore time advancement
                break
            if (_1 is True) or (_0 == DSSGlobals.EVENTDRIVEN):
                _1 = True
                mHour = MutableInt()
                mSec = MutableDouble()
                # advances time
                succ = ckt.getControlQueue().doNearestActions(mHour, mSec)
                self.intHour = mHour.intValue()
                self.dynaVars.t = mSec.doubleValue()
                if not succ:
                    self.controlActionsDone = True
                break
            if (_1 is True) or (_0 == DSSGlobals.TIMEDRIVEN):
                _1 = True
                if not ckt.getControlQueue().doActions(self.intHour, self.dynaVars.t):
                    self.controlActionsDone = True
                break
            break
        # execute the nearest set of control actions time-wise.

    def sampleControlDevices(self):
        controlDevice = None
        ckt = DSSGlobals.activeCircuit
        # sample all controls and set action times in control queue.
        try:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(ckt.getDSSControls())):
                    break
                controlDevice = ckt.getDSSControls().get(i)
                if controlDevice.isEnabled():
                    controlDevice.sample()
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error Sampling Control Device \"' + controlDevice.getName() + '\"' + DSSGlobals.CRLF + 'Error = ' + e.getMessage(), 484)
            raise ControlProblem('Solution aborted.')

    def sampleDoControlActions(self):
        """Sample and do.

        @throws ControlProblem
        """
        if self.controlMode == DSSGlobals.CONTROLSOFF:
            self.controlActionsDone = True
        else:
            self.sampleControlDevices()
            self.doControlActions()
            # This variable lets control devices know the bus list has changed
            DSSGlobals.activeCircuit.setControlBusNameRedefined(False)
            # reset until next change

    def setMode(self, Value):
        ckt = DSSGlobals.activeCircuit
        self.intHour = 0
        self.dynaVars.t = 0.0
        self.updateDblHour()
        ckt.setTrapezoidalIntegration(False)
        if not self.oKForDynamics(Value):
            return
        if not self.oKForHarmonics(Value):
            return
        self.dynaVars.solutionMode = Value
        self.controlMode = self.defaultControlMode
        # revert to default mode
        self.loadModel = self.defaultLoadModel
        self.isDynamicModel = False
        self.isHarmonicModel = False
        self.solutionInitialized = False
        # reinitialize solution when mode set (except dynamics)
        self.preserveNodeVoltages = False
        # don't do this unless we have to
        # reset defaults for solution modes
        _0 = self.dynaVars.solutionMode
        _1 = False
        while True:
            if _0 == Dynamics.PEAKDAY:
                _1 = True
                self.dynaVars.h = 3600.0
                self.numberOfTimes = 24
                break
            if (_1 is True) or (_0 == Dynamics.DAILYMODE):
                _1 = True
                self.dynaVars.h = 3600.0
                self.numberOfTimes = 24
                break
            if (_1 is True) or (_0 == Dynamics.SNAPSHOT):
                _1 = True
                self.intervalHrs = 1.0
                self.numberOfTimes = 1
                break
            if (_1 is True) or (_0 == Dynamics.YEARLYMODE):
                _1 = True
                self.intervalHrs = 1.0
                self.dynaVars.h = 3600.0
                self.numberOfTimes = 8760
                break
            if (_1 is True) or (_0 == Dynamics.DUTYCYCLE):
                _1 = True
                self.dynaVars.h = 1.0
                self.controlMode = DSSGlobals.TIMEDRIVEN
                break
            if (_1 is True) or (_0 == Dynamics.DYNAMICMODE):
                _1 = True
                self.dynaVars.h = 0.001
                self.controlMode = DSSGlobals.TIMEDRIVEN
                self.isDynamicModel = True
                self.preserveNodeVoltages = True
                # need to do this in case Y changes during this mode
                break
            if (_1 is True) or (_0 == Dynamics.GENERALTIME):
                _1 = True
                self.intervalHrs = 1.0
                self.dynaVars.h = 3600.0
                self.numberOfTimes = 1
                # just one time step per solve call expected
                break
            if (_1 is True) or (_0 == Dynamics.MONTECARLO1):
                _1 = True
                self.intervalHrs = 1.0
                break
            if (_1 is True) or (_0 == Dynamics.MONTECARLO2):
                _1 = True
                self.dynaVars.h = 3600.0
                break
            if (_1 is True) or (_0 == Dynamics.MONTECARLO3):
                _1 = True
                self.intervalHrs = 1.0
                break
            if (_1 is True) or (_0 == Dynamics.MONTEFAULT):
                _1 = True
                self.isDynamicModel = True
                break
            if (_1 is True) or (_0 == Dynamics.FAULTSTUDY):
                _1 = True
                self.isDynamicModel = True
                break
            if (_1 is True) or (_0 == Dynamics.LOADDURATION1):
                _1 = True
                self.dynaVars.h = 3600.0
                ckt.setTrapezoidalIntegration(True)
                break
            if (_1 is True) or (_0 == Dynamics.LOADDURATION2):
                _1 = True
                self.intHour = 1
                ckt.setTrapezoidalIntegration(True)
                break
            if (_1 is True) or (_0 == Dynamics.AUTOADDFLAG):
                _1 = True
                self.intervalHrs = 1.0
                ckt.getAutoAddObj().setModeChanged(True)
                break
            if (_1 is True) or (_0 == Dynamics.HARMONICMODE):
                _1 = True
                self.controlMode = DSSGlobals.CONTROLSOFF
                self.isHarmonicModel = True
                self.loadModel = DSSGlobals.ADMITTANCE
                self.preserveNodeVoltages = True
                # need to do this in case Y changes during this mode
                break
            break
        # Moved here 9-8-2007 so that mode is changed before reseting monitors, etc.
        # reset meters and monitors
        DSSGlobals.monitorClass.resetAll()
        DSSGlobals.energyMeterClass.resetAll()
        Utilities.doResetFaults()
        Utilities.doResetControls()

    def addInAuxCurrents(self, solveType):
        ckt = DSSGlobals.activeCircuit
        # for (int i = 0; i < ckt.getNumNodes(); i++)
        # Currents[i] = Currents[i].add(AuxCurrents[i]);
        # for now, only AutoAddObj uses this.
        if self.dynaVars.solutionMode == Dynamics.AUTOADDFLAG:
            ckt.getAutoAddObj().addCurrents(solveType)

    def zeroAuxCurrents(self):
        ckt = DSSGlobals.activeCircuit
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumNodes()):
                break
            self.auxCurrents[i] = Complex.ZERO

    def checkFaultStatus(self):
        ckt = DSSGlobals.activeCircuit
        for pFault in ckt.getFaults():
            pFault.checkStatus(self.controlMode)

    def getMachineInjCurrents(self):
        """This procedure is called for solveDirect and any other solution method
        that does not get the injection currents for PC elements normally. In
        dynamics mode, generators are voltage sources ...
        """
        # do machines in dynamics mode
        if self.isDynamicModel:
            ckt = DSSGlobals.activeCircuit
            for pElem in ckt.getGenerators():
                if pElem.isEnabled():
                    pElem.injCurrents()
                    # uses nodeRef to add current into injCurr array

    def oKForDynamics(self, value):
        result = True
        _0 = value
        _1 = False
        while True:
            if _0 == Dynamics.MONTEFAULT:
                _1 = True
                valueIsDynamic = True
                break
            if (_1 is True) or (_0 == Dynamics.DYNAMICMODE):
                _1 = True
                valueIsDynamic = True
                break
            if (_1 is True) or (_0 == Dynamics.FAULTSTUDY):
                _1 = True
                valueIsDynamic = True
                break
            if True:
                _1 = True
                valueIsDynamic = False
                break
            break
        # When we go in and out of dynamics mode, we have to do some special things
        if self.isDynamicModel and not valueIsDynamic:
            Utilities.invalidateAllMachines()
            # force recomp of YPrims when we leave dynamics mode
        if not self.isDynamicModel and valueIsDynamic:
            # see if conditions right for going into dynamics
            if DSSGlobals.activeCircuit.isSolved():
                Utilities.calcInitialMachineStates()
                # set state variables for machines (loads and generators)
            else:
                # Raise error message if not solved
                DSSGlobals.doSimpleMsg('Circuit must be solved in a non-dynamic mode before entering dynamics or fault study modes!' + DSSGlobals.CRLF + 'If you attempted to solve, then the solution has not yet converged.', 486)
                if DSSGlobals.inRedirect:
                    DSSGlobals.redirectAbort = True
                result = False
        return result

    def oKForHarmonics(self, value):
        """When we go in and out of harmonics mode, we have to do some special things."""
        result = True
        ckt = DSSGlobals.activeCircuit
        if self.isHarmonicModel and not (value == Dynamics.HARMONICMODE):
            Utilities.invalidateAllMachines()
            # force recomp of YPrims when we leave harmonics mode
            self.frequency = ckt.getFundamental()
            # resets everything to norm
        if not self.isHarmonicModel and value == Dynamics.HARMONICMODE:
            # see if conditions right for going into harmonics
            if ckt.isSolved() and self.frequency == ckt.getFundamental():
                if not Utilities.initializeForHarmonics():
                    # set state variables for machines (loads and generators) and sources
                    result = False
                    if DSSGlobals.inRedirect:
                        DSSGlobals.redirectAbort = True
            else:
                DSSGlobals.doSimpleMsg('Circuit must be solved in a fundamental frequency power flow or direct mode before entering harmonics mode!', 487)
                if DSSGlobals.inRedirect:
                    DSSGlobals.redirectAbort = True
                result = False
        return result

    def setFrequency(self, value):
        if self.frequency != value:
            self.frequencyChanged = True
            # force rebuild of all Y primitives
            self.systemYChanged = True
            # force rebuild of system Y
        self.frequency = value
        ckt = DSSGlobals.activeCircuit
        if ckt is not None:
            self.harmonic = self.frequency / ckt.getFundamental()
            # make sure harmonic stays in synch

    def incrementTime(self):
        self.dynaVars.t = self.dynaVars.t + self.dynaVars.h
        while self.dynaVars.t >= 3600.0:
            self.intHour += 1
            self.dynaVars.t = self.dynaVars.t - 3600.0
        self.updateDblHour()

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = ''
        # TODO Check zero based indexing
        super(SolutionObjImpl, self).initPropertyValues(SolutionImpl.NumPropsThisClass)

    def setYear(self, value):
        if DSSGlobals.DIFilesAreOpen:
            DSSGlobals.energyMeterClass.closeAllDIFiles()
        self.year = value
        self.intHour = 0
        # Change year, start over
        self.dynaVars.t = 0.0
        self.updateDblHour()
        DSSGlobals.energyMeterClass.resetAll()
        # force any previous year data to complete

    def saveVoltages(self):
        try:
            fd = self.FileWriter(DSSGlobals.circuitName_ + 'SavedVoltages.txt')
            f = PrintWriter(fd)
            ckt = DSSGlobals.activeCircuit
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ckt.getNumBuses()):
                    break
                busName = ckt.getBusList().get(i)
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < ckt.getBuses()[i].getNumNodesThisBus()):
                        break
                    volts = self.nodeV[ckt.getBuses()[i].getRef(j)]
                    print busName + ', ' + ckt.getBuses()[i].getNum(j) + String.format.format(', %-.7g, %-.7g', volts.abs(), ComplexUtil.degArg(volts))
            f.close()
            DSSGlobals.globalResult = DSSGlobals.circuitName_ + 'SavedVoltages.txt'
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error opening saved voltages file: ' + e.getMessage(), 488)
            return

    def solveSystem(self, V):
        """*************  MAIN SOLVER CALL *************

        @throws Esolv32Problem
        """
        iRes = 0
        dRes = 0
        # Note: NodeV[0] = 0 + j0 always. Therefore, pass the address of the element 1 of the array.
        # new function to log KLUSolve.DLL function calls
        try:
            YMatrix.setLogFile('KLU_Log.txt', 1)
            retCode = YMatrix.solveSparseSet(self.Y, V[1], self.currents[1])
            # solve for present injCurr
            # new information functions
            # YMatrix.getFlops(Y, dRes);
            # YMatrix.getRGrowth(Y, dRes);
            YMatrix.getRCond(self.Y, dRes)
            # YMatrix.getCondEst(Y, dRes); // this can be expensive
            # YMatrix.getSize(Y, iRes);
            YMatrix.getNNZ(self.Y, iRes)
            YMatrix.getSparseNNZ(self.Y, iRes)
            # YMatrix.getSingularCol(Y, iRes);
        except Exception, e:
            raise Esolv32Problem('Error solving system Y matrix. Sparse matrix solver reports numerical error: ' + e.getMessage())
        return retCode

    def updateDblHour(self):
        self.dblHour = self.intHour + (self.dynaVars.t / 3600.0)

    def updateVBus(self):
        """Updates voltages for each bus from NodeV."""
        ckt = DSSGlobals.activeCircuit
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumBuses()):
                break
            bus = ckt.getBuses()[i]
            if bus.getVBus() is not None:
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < bus.getNumNodesThisBus()):
                        break
                    bus.getVBus()[j] = self.nodeV[bus.getRef(j)]

    def restoreNodeVFromVbus(self):
        """Opposite of updateVBus()."""
        ckt = DSSGlobals.activeCircuit
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ckt.getNumBuses()):
                break
            bus = ckt.getBuses()[i]
            if bus.getVBus() is not None:
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < bus.getNumNodesThisBus()):
                        break
                    self.nodeV[bus.getRef(j)] = bus.getVBus()[j]

    def solveYDirect(self):
        """Similar to solveDirect(); used for initialization.
        Solves present Y matrix with no injection sources except voltage and current sources.

        @throws Esolv32Problem
        """
        self.zeroInjCurr()
        # side effect: allocates injCurr
        self.getSourceInjCurrents()
        self.getMachineInjCurrents()
        # need this in dynamics mode to pick up injections
        self.solveSystem(self.nodeV)
        # solve with zero injection current
        return 0

    def getFrequency(self):
        return self.frequency

    def getMode(self):
        return self.dynaVars.solutionMode

    def getYear(self):
        return self.year

    def getAlgorithm(self):
        return self.algorithm

    def setAlgorithm(self, alg):
        self.algorithm = alg

    def getAuxCurrents(self):
        return self.auxCurrents

    def setAuxCurrents(self, value):
        self.auxCurrents = value

    def isControlActionsDone(self):
        return self.controlActionsDone

    def setControlActionsDone(self, value):
        self.controlActionsDone = value

    def getControlIteration(self):
        return self.controlIteration

    def setControlIteration(self, iteration):
        self.controlIteration = iteration

    def getControlMode(self):
        return self.controlMode

    def setControlMode(self, mode):
        self.controlMode = mode

    def getConvergenceTolerance(self):
        return self.convergenceTolerance

    def setConvergenceTolerance(self, tolerance):
        self.convergenceTolerance = tolerance

    def isConvergedFlag(self):
        return self.convergedFlag

    def setConvergedFlag(self, flag):
        self.convergedFlag = flag

    def getDefaultControlMode(self):
        return self.defaultControlMode

    def setDefaultControlMode(self, controlMode):
        self.defaultControlMode = controlMode

    def getDefaultLoadModel(self):
        return self.defaultLoadModel

    def setDefaultLoadModel(self, loadModel):
        self.defaultLoadModel = loadModel

    def isDoAllHarmonics(self):
        return self.doAllHarmonics

    def setDoAllHarmonics(self, value):
        self.doAllHarmonics = value

    def isDynamicsAllowed(self):
        return self.dynamicsAllowed

    def setDynamicsAllowed(self, allowed):
        self.dynamicsAllowed = allowed

    def getDynaVars(self):
        return self.dynaVars

    def setDynaVars(self, vars):
        self.dynaVars = vars

    def getErrorSaved(self):
        return self.errorSaved

    def setErrorSaved(self, value):
        self.errorSaved = value

    def isFirstIteration(self):
        return self.firstIteration

    def setFirstIteration(self, iteration):
        self.firstIteration = iteration

    def isFrequencyChanged(self):
        return self.frequencyChanged

    def setFrequencyChanged(self, value):
        self.frequencyChanged = value

    def getHarmonic(self):
        return self.harmonic

    def setHarmonic(self, value):
        self.harmonic = value

    def getHarmonicList(self):
        return self.harmonicList

    def setHarmonicList(self, value):
        self.harmonicList = value

    def getHarmonicListSize(self):
        return self.harmonicListSize

    def setHarmonicListSize(self, size):
        self.harmonicListSize = size

    def getIntHour(self):
        return self.intHour

    def setIntHour(self, hour):
        self.intHour = hour

    def getDblHour(self):
        return self.dblHour

    def setDblHour(self, hour):
        self.dblHour = hour

    def getYSystem(self):
        return self.YSystem

    def setYSystem(self, value):
        self.YSystem = value

    def getYSeries(self):
        return self.YSeries

    def setYSeries(self, value):
        self.YSeries = value

    def getY(self):
        return self.Y

    def setY(self, y):
        self.Y = y

    def getIntervalHrs(self):
        return self.intervalHrs

    def setIntervalHrs(self, interval):
        self.intervalHrs = interval

    def isDynamicModel(self):
        return self.isDynamicModel

    def setDynamicModel(self, isDynamic):
        self.isDynamicModel = isDynamic

    def isHarmonicModel(self):
        return self.isHarmonicModel

    def setHarmonicModel(self, isHarmonic):
        self.isHarmonicModel = isHarmonic

    def getIteration(self):
        return self.iteration

    def setIteration(self, iter):
        self.iteration = iter

    def getLoadModel(self):
        return self.loadModel

    def setLoadModel(self, model):
        self.loadModel = model

    def lastSolutionWasDirect(self):
        return self.lastSolutionWasDirect

    def setLastSolutionWasDirect(self, value):
        self.lastSolutionWasDirect = value

    def loadsNeedUpdating(self):
        return self.loadsNeedUpdating

    def setLoadsNeedUpdating(self, value):
        self.loadsNeedUpdating = value

    def getMaxControlIterations(self):
        return self.maxControlIterations

    def setMaxControlIterations(self, iterations):
        self.maxControlIterations = iterations

    def getMaxError(self):
        return self.maxError

    def setMaxError(self, error):
        self.maxError = error

    def getMaxIterations(self):
        return self.maxIterations

    def setMaxIterations(self, iterations):
        self.maxIterations = iterations

    def getMostIterationsDone(self):
        return self.mostIterationsDone

    def setMostIterationsDone(self, value):
        self.mostIterationsDone = value

    def getNodeVBase(self):
        return self.nodeVBase

    def setNodeVBase(self, base):
        self.nodeVBase = base

    def getNumberOfTimes(self):
        return self.numberOfTimes

    def setNumberOfTimes(self, number):
        self.numberOfTimes = number

    def isPreserveNodeVoltages(self):
        return self.preserveNodeVoltages

    def setPreserveNodeVoltages(self, preserve):
        self.preserveNodeVoltages = preserve

    def getRandomType(self):
        return self.randomType

    def setRandomType(self, type):
        self.randomType = type

    def isSeriesYInvalid(self):
        return self.seriesYInvalid

    def setSeriesYInvalid(self, invalid):
        self.seriesYInvalid = invalid

    def getSolutionCount(self):
        return self.solutionCount

    def setSolutionCount(self, count):
        self.solutionCount = count

    def isSolutionInitialized(self):
        return self.solutionInitialized

    def setSolutionInitialized(self, value):
        self.solutionInitialized = value

    def isSystemYChanged(self):
        return self.systemYChanged

    def setSystemYChanged(self, value):
        self.systemYChanged = value

    def useAuxCurrents(self):
        return self.useAuxCurrents

    def setUseAuxCurrents(self, value):
        self.useAuxCurrents = value

    def getVMagSaved(self):
        return self.VMagSaved

    def setVMagSaved(self, value):
        self.VMagSaved = value

    def isVoltageBaseChanged(self):
        return self.voltageBaseChanged

    def setVoltageBaseChanged(self, value):
        self.voltageBaseChanged = value

    def getNodeV(self):
        return self.nodeV

    def setNodeV(self, value):
        self.nodeV = value

    def getCurrents(self):
        return self.currents

    def setCurrents(self, value):
        self.currents = value
