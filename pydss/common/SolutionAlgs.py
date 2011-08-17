from dss.common.impl.YMatrix import (YMatrix,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.FaultImpl import (FaultImpl,)
from dss.common.impl.Esolv32Problem import (Esolv32Problem,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class SolutionAlgs(object):
    """Solution algorithms."""
    progressCount = None

    def __init__(self):
        pass

    @classmethod
    def show10PctProgress(cls, i, n):
        if DSSGlobals.noFormsAllowed:
            return
        if (i * 10) / n > cls.progressCount:
            cls.progressCount += 1
            DSSGlobals.DSSForms.showPctProgress(cls.progressCount * 10)

    @classmethod
    def solveYearly(cls):
        """Solve following yearly cycle.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        DSSGlobals.DSSForms.progressCaption('Solving Year ' + String.valueOf.valueOf(sol.getYear()))
        cls.progressCount = 0
        DSSGlobals.DSSForms.showPctProgress(cls.progressCount)
        try:
            sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
            # needed for energy meters and storage elements
            if not DSSGlobals.DIFilesAreOpen:
                DSSGlobals.energyMeterClass.openAllDIFiles()
                # open demand interval files, if desired, creates DI_Totals
            twoPct = cls.Math.max(sol.getNumberOfTimes() / 50, 1)
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.incrementTime()
                    ckt.setDefaultHourMult(ckt.getDefaultYearlyShapeObj().getMult(sol.getDblHour()))
                    if ckt.getPriceCurveObj() is not None:
                        ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()))
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.energyMeterClass.sampleAll()
                    # make all energy meters take a sample
                    DSSGlobals.storageClass.updateAll()
                    if N % twoPct == 0:
                        DSSGlobals.DSSForms.showPctProgress((N * 100) / sol.getNumberOfTimes())
        finally:
            DSSGlobals.DSSForms.progressHide()
            DSSGlobals.monitorClass.saveAll()
            # DSSGlobals.energyMeterClass.closeAllDIFiles();  // save demand interval files, see DIFilesAreOpen logic
        return 0

    @classmethod
    def solveDaily(cls):
        """Solve following daily cycle.

        Stepsize defaults to 1 hr and number of times = 24.
        Load is modified by yearly growth, time of day, and global load multiplier.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        # t = 0.0;
        # Globals.getMonitorClass().resetAll();
        # DSSGlobals.energyMeterClass.resetAll();
        try:
            sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
            # needed for energy meters
            ckt.setDefaultDailyShapeObj(DSSGlobals.loadShapeClass.find('default'))
            if not DSSGlobals.DIFilesAreOpen:
                DSSGlobals.energyMeterClass.openAllDIFiles()
                # append demand interval files, if desired
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.incrementTime()
                    ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                    if ckt.getPriceCurveObj() is not None:
                        ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()))
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.energyMeterClass.sampleAll()
                    # make all energy meters take a sample
                    DSSGlobals.storageClass.updateAll()
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.energyMeterClass.closeAllDIFiles()
            # save demand interval files
        return 0

    @classmethod
    def solvePeakDay(cls):
        """Solve following daily cycle at peak load.

        Takes the given load kW and assumes it represents the peak value.
        Load is modified by daily load curve and growth factor for the year.
        'h' defaults to 3600 (1 hr) but can be reset to anything.
        Differs from Daily mode in that the global load multiplier is ignored.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        sol.getDynaVars().t = 0.0
        DSSGlobals.monitorClass.resetAll()
        DSSGlobals.energyMeterClass.resetAll()
        try:
            sol.setIntHour(0)
            sol.setDblHour(0.0)
            sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
            # needed for energy meters and storage devices
            ckt.setDefaultDailyShapeObj(DSSGlobals.loadShapeClass.find('default'))
            if not DSSGlobals.DIFilesAreOpen:
                DSSGlobals.energyMeterClass.openAllDIFiles()
                # open demand interval files, if desired
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.incrementTime()
                    ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                    if ckt.getPriceCurveObj() is not None:
                        ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()))
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.energyMeterClass.sampleAll()
                    # make all energy meters take a sample
                    DSSGlobals.storageClass.updateAll()
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.energyMeterClass.closeAllDIFiles()
            # save demand interval files
        return 0

    @classmethod
    def solveDuty(cls):
        """Solve following duty cycle.
        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        DSSGlobals.DSSForms.progressCaption('Duty Cycle Solution')
        cls.progressCount = 0
        DSSGlobals.DSSForms.showPctProgress(0)
        # t = 0.0;
        # Globals.getMonitorClass().resetAll();
        TwoPct = cls.Math.max(1, sol.getNumberOfTimes() / 50)
        try:
            sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
            # needed for energy meters and storage devices
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.incrementTime()
                    ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                    # assume price signal stays constant for dutycycle calcs
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.storageClass.updateAll()
                    if N % TwoPct == 0:
                        DSSGlobals.DSSForms.showPctProgress((N * 100) / sol.getNumberOfTimes())
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.DSSForms.progressHide()
        return 0

    @classmethod
    def solveGeneralTime(cls):
        """For rolling your own solution modes.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
        # needed for energy meters and storage devices
        _0 = True
        N = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                N += 1
            if not (N < sol.getNumberOfTimes()):
                break
            if not DSSGlobals.solutionAbort:
                # Compute basic multiplier from default load shape to use in generator dispatch, if any
                ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                sol.solveSnap()
                DSSGlobals.monitorClass.sampleAll()
                # make all monitors take a sample
                DSSGlobals.storageClass.updateAll()
                sol.incrementTime()
        return 0

    @classmethod
    def integratePCStates(cls):
        """Integrate states in all PC Elements.  At present, only PC Elements
        can have dynamic states.
        """
        ckt = DSSGlobals.activeCircuit
        for pcElem in ckt.getPCElements():
            pcElem.integrateStates()

    @classmethod
    def solveDynamic(cls):
        """Solve dynamics.
        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        try:
            sol.setSolutionInitialized(True)
            # if we're in dynamics mode, no need to re-initialize.
            sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
            # needed for energy meters and storage devices
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.incrementTime()
                    ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                    # assume price signal stays constant for dynamic calcs
                    # Predictor
                    sol.getDynaVars().iterationFlag = 0
                    cls.integratePCStates()
                    sol.solveSnap()
                    # Corrector
                    sol.getDynaVars().iterationFlag = 1
                    cls.integratePCStates()
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.storageClass.updateAll()
        finally:
            DSSGlobals.monitorClass.saveAll()
        return 0

    @classmethod
    def solveMonte1(cls):
        """Solve Monte Carlo solution.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        try:
            ckt.setLoadMultiplier(1.0)
            # always set with prop in case matrix must be rebuilt
            sol.setIntervalHrs(1.0)
            # needed for energy meters and storage devices
            sol.setIntHour(0)
            sol.setDblHour(0.0)
            # use hour to denote case number
            sol.getDynaVars().t = 0.0
            # Globals.getMonitorClass().resetAll();
            # DSSGlobals.energyMeterClass.resetAll();
            DSSGlobals.DSSForms.progressCaption('Monte Carlo Mode 1, ' + String.valueOf.valueOf(sol.getNumberOfTimes()) + ' Random Loads.')
            cls.progressCount = 0
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.setIntHour(sol.getIntHour() + 1)
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.energyMeterClass.sampleAll()
                    # make all meters take a sample
                    cls.show10PctProgress(N, sol.getNumberOfTimes())
                else:
                    DSSGlobals.errorNumber = DSSGlobals.SOLUTION_ABORT
                    DSSGlobals.cmdResult = DSSGlobals.errorNumber
                    DSSGlobals.globalResult = 'Solution Aborted'
                    break
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.DSSForms.progressHide()
        return 0

    @classmethod
    def solveMonte2(cls):
        """Solve Monte Carlo solution.
        Do a daily load solution for several random days.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        try:
            sol.getDynaVars().t = 0.0
            sol.setIntHour(0)
            sol.setDblHour(0.0)
            # Globals.getMonitorClass().resetAll();
            # DSSGlobals.energyMeterClass.resetAll();
            sol.setIntervalHrs(sol.getDynaVars().h / 3600.0)
            # needed for energy meters and storage devices
            nDaily = cls.Math.round(24.0 / sol.getIntervalHrs())
            if not DSSGlobals.DIFilesAreOpen:
                DSSGlobals.energyMeterClass.openAllDIFiles()
                # open demand interval files, if desired
            DSSGlobals.DSSForms.progressCaption('Monte Carlo Mode 2, ' + String.valueOf.valueOf(sol.getNumberOfTimes()) + ' Days.')
            cls.progressCount = 0
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    # number of days
                    # always set loadMultiplier with prop in case matrix must be rebuilt
                    _1 = sol.getRandomType()
                    _2 = False
                    while True:
                        if _1 == DSSGlobals.UNIFORM:
                            _2 = True
                            ckt.setLoadMultiplier(cls.Math.random())
                            # number between 0 and 1
                            break
                        if (_2 is True) or (_1 == DSSGlobals.GAUSSIAN):
                            _2 = True
                            ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(), ckt.getDefaultDailyShapeObj().getStdDev()))
                            break
                        break
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < nDaily):
                            break
                        sol.incrementTime()
                        ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                        sol.solveSnap()
                        DSSGlobals.monitorClass.sampleAll()
                        # make all monitors take a sample
                        DSSGlobals.energyMeterClass.sampleAll()
                        # make all meters take a sample
                        DSSGlobals.storageClass.updateAll()
                    cls.show10PctProgress(N, sol.getNumberOfTimes())
                else:
                    DSSGlobals.errorNumber = DSSGlobals.SOLUTION_ABORT
                    DSSGlobals.cmdResult = DSSGlobals.errorNumber
                    DSSGlobals.globalResult = 'Solution Aborted.'
                    break
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.energyMeterClass.closeAllDIFiles()
            # save demand interval files
            DSSGlobals.DSSForms.progressHide()
        return 0

    @classmethod
    def solveMonte3(cls):
        """Solve Monte Carlo solution.

        Hold time fixed and just vary the global load multiplier.
        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        # time must be set beFore entering this routine
        try:
            sol.setIntervalHrs(1.0)
            # just get per unit energy and multiply result as necessary
            if not DSSGlobals.DIFilesAreOpen:
                DSSGlobals.energyMeterClass.openAllDIFiles()
                # Open Demand Interval files, if desired
            DSSGlobals.DSSForms.progressCaption('Monte Carlo Mode 3, ' + String.valueOf.valueOf(sol.getNumberOfTimes()) + ' Different Load Levels.')
            cls.progressCount = 0
            ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
            if ckt.getPriceCurveObj() is not None:
                ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(sol.getDblHour()))
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    # always set loadMultiplier with prop in case matrix must be rebuilt
                    _1 = sol.getRandomType()
                    _2 = False
                    while True:
                        if _1 == DSSGlobals.UNIFORM:
                            _2 = True
                            ckt.setLoadMultiplier(cls.Math.random())
                            # number between 0 and 1
                            break
                        if (_2 is True) or (_1 == DSSGlobals.GAUSSIAN):
                            _2 = True
                            ckt.setLoadMultiplier(MathUtil.gauss(ckt.getDefaultDailyShapeObj().getMean(), ckt.getDefaultDailyShapeObj().getStdDev()))
                            break
                        if (_2 is True) or (_1 == DSSGlobals.LOGNORMAL):
                            _2 = True
                            ckt.setLoadMultiplier(MathUtil.quasiLognormal(ckt.getDefaultDailyShapeObj().getMean()))
                            break
                        break
                    sol.solveSnap()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    DSSGlobals.energyMeterClass.sampleAll()
                    # make all meters take a sample
                    cls.show10PctProgress(N, sol.getNumberOfTimes())
                else:
                    DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT
                    DSSGlobals.errorNumber = DSSGlobals.cmdResult
                    DSSGlobals.globalResult = 'Solution Aborted'
                    break
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.energyMeterClass.closeAllDIFiles()
            # save demand interval files
            DSSGlobals.DSSForms.progressHide()
        # Globals.getMonitorClass().resetAll();
        # DSSGlobals.energyMeterClass.resetAll();
        return 0

    @classmethod
    def solveLD1(cls):
        """Solve Load-Duration Curve, 1.

        Do a Daily Simulation based on a load duration curve.
        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        try:
            if ckt.getLoadDurCurveObj() is None:
                DSSGlobals.doSimpleMsg('Load Duration Curve Not Defined (Set LDCurve=... command). Cannot perform solution.', 470)
                return 0
            # time must be set before entering this routine
            # Globals.getMonitorClass().resetAll();
            # DSSGlobals.energyMeterClass.resetAll();
            nDaily = cls.Math.round((24.0 / sol.getDynaVars().h) * 3600.0)
            if not DSSGlobals.DIFilesAreOpen:
                DSSGlobals.energyMeterClass.openAllDIFiles()
                # open demand interval files, if desired
            DSSGlobals.DSSForms.progressCaption('Load-Duration Mode 1 Solution.')
            # (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));
            sol.setIntHour(0)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < nDaily):
                    break
                # set the time
                sol.incrementTime()
                ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
                if not DSSGlobals.solutionAbort:
                    _1 = True
                    N = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            N += 1
                        if not (N < ckt.getLoadDurCurveObj().getNumPoints()):
                            break
                        # always set loadMultiplier with prop in case matrix must be rebuilt
                        ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N))
                        # adjust meter interval to interval on value of present load-duration curve
                        sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval())
                        # price curve must correspond to load-duration curve
                        if ckt.getPriceCurveObj() is not None:
                            ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(N))
                        sol.solveSnap()
                        DSSGlobals.monitorClass.sampleAll()
                        # make all monitors take a sample
                        DSSGlobals.energyMeterClass.sampleAll()
                        # make all meters take a sample
                        DSSGlobals.storageClass.updateAll()
                    DSSGlobals.DSSForms.showPctProgress((i * 100) / nDaily)
                else:
                    DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT
                    DSSGlobals.errorNumber = DSSGlobals.cmdResult
                    DSSGlobals.globalResult = 'Solution Aborted'
                    break
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.energyMeterClass.closeAllDIFiles()
            # save demand interval files
            DSSGlobals.DSSForms.progressHide()
        return 0

    @classmethod
    def solveLD2(cls):
        """Solve Load-Duration Curve, 2.
        Hold time fixed and just vary the global load multiplier according to
        the global Load-Duration Curve.

        @throws ControlProblem
        @throws SolverError
        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if ckt.getLoadDurCurveObj() is None:
            DSSGlobals.doSimpleMsg('Load duration curve not defined (set ldcurve=... command). Can not perform solution.', 471)
            return 0
        # time must be set before entering this routine
        # Globals.getMonitorClass().resetAll();
        # DSSGlobals.energyMeterClass.resetAll();
        ckt.setDefaultHourMult(ckt.getDefaultDailyShapeObj().getMult(sol.getDblHour()))
        if not DSSGlobals.DIFilesAreOpen:
            DSSGlobals.energyMeterClass.openAllDIFiles()
            # open demand interval files, if desired
            # (set in solve method) ckt.setDefaultGrowthFactor(Math.pow(ckt.getDefaultGrowthRate(), (sol.getYear() - 1)));
        try:
            if DSSGlobals.solutionAbort:
                DSSGlobals.cmdResult = DSSGlobals.SOLUTION_ABORT
                DSSGlobals.errorNumber = DSSGlobals.cmdResult
                DSSGlobals.globalResult = 'Solution aborted.'
                return 0
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < ckt.getLoadDurCurveObj().getNumPoints()):
                    break
                # adjust meter interval to interval on value of present load-duration curve
                # always set loadMultiplier with prop in case matrix must be rebuilt
                ckt.setLoadMultiplier(ckt.getLoadDurCurveObj().mult(N))
                sol.setIntervalHrs(ckt.getLoadDurCurveObj().getInterval())
                # price curve must correspond to load-duration curve
                if ckt.getPriceCurveObj() is not None:
                    ckt.setPriceSignal(ckt.getPriceCurveObj().getPrice(N))
                sol.solveSnap()
                DSSGlobals.monitorClass.sampleAll()
                # make all monitors take a sample
                DSSGlobals.energyMeterClass.sampleAll()
                # make all meters take a sample
                DSSGlobals.storageClass.updateAll()
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.energyMeterClass.closeAllDIFiles()
            # save demand interval files
        return 0

    @classmethod
    def pickAFault(cls):
        """Enable one of the faults in the circuit. Disable the rest."""
        ckt = DSSGlobals.activeCircuit
        NumFaults = len(ckt.getFaults())
        whichOne = (cls.Math.random() * NumFaults) + 1
        # TODO Check zero based indexing
        if whichOne > NumFaults:
            whichOne = NumFaults
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < NumFaults):
                break
            faultObj = ckt.getFaults().get(i)
            if i == whichOne:
                FaultImpl.activeFaultObj = faultObj
                # in fault unit
                faultObj.setEnabled(True)
            else:
                faultObj.setEnabled(False)

    @classmethod
    def solveMonteFault(cls):
        """Solve Monte Carlo Fault Study.

        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        try:
            sol.setLoadModel(DSSGlobals.ADMITTANCE)
            # all direct solution
            ckt.setLoadMultiplier(1.0)
            # always set loadMultiplier with prop in case matrix must be rebuilt
            sol.setIntHour(0)
            sol.setDblHour(0.0)
            # use hour to denote case number
            sol.getDynaVars().t = 0.0
            # Globals.getMonitorClass().resetAll();
            DSSGlobals.DSSForms.progressCaption('Monte Carlo Fault Study: ' + String.valueOf.valueOf(sol.getNumberOfTimes()) + ' different faults.')
            cls.progressCount = 0
            sol.setGeneratorDispRef()
            _0 = True
            N = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    N += 1
                if not (N < sol.getNumberOfTimes()):
                    break
                if not DSSGlobals.solutionAbort:
                    sol.setIntHour(sol.getIntHour() + 1)
                    cls.pickAFault()
                    # randomly enable one of the faults
                    FaultImpl.activeFaultObj.randomize()
                    # randomize the fault resistance
                    sol.solveDirect()
                    DSSGlobals.monitorClass.sampleAll()
                    # make all monitors take a sample
                    cls.show10PctProgress(N, sol.getNumberOfTimes())
        finally:
            DSSGlobals.monitorClass.saveAll()
            DSSGlobals.DSSForms.progressHide()
        return 0

    @classmethod
    def allocateAllSCParms(cls):
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
            ckt.getBuses()[i].allocateBusQuantities()

    @classmethod
    def computeIsc(cls):
        """Compute Isc at all buses for current values of Voc and Ysc."""
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
            bus.getYsc().vMult(bus.getBusCurrent(), bus.getVBus())

    @classmethod
    def computeYsc(cls, iB):
        """Compute YSC for I-th bus.
        Assume InjCurr is zeroed.

        @throws Esolv32Problem
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        bus = ckt.getBuses()[iB]
        bus.getZsc().clear()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < bus.getNumNodesThisBus()):
                break
            ref1 = bus.getRef(i)
            if ref1 > 0:
                # TODO Check zero based indexing
                sol.getCurrents()[ref1] = Complex.ONE
                # SparseSet expects 1st element of voltage array, not 0-th element
                if (
                    YMatrix.solveSparseSet(sol.getYSystem(), sol.getNodeV()[1], sol.getCurrents()[1]) < 1
                ):
                    raise Esolv32Problem('Error solving system Y matrix in computeYsc. Problem with sparse matrix solver.')
                    # Extract voltage vector = column of Zsc
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < bus.getNumNodesThisBus()):
                        break
                    bus.getZsc().setElement(j, i, sol.getNodeV()[bus.getRef(j)])
                sol.getCurrents()[ref1] = Complex.ZERO
        bus.getYsc().copyFrom(bus.getZsc())
        bus.getYsc().invert()
        # Save as admittance

    @classmethod
    def computeAllYsc(cls):
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < ckt.getNumNodes()):
                break
            sol.getCurrents()[j] = Complex.ZERO
        cls.progressCount = 0
        _1 = True
        iB = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                iB += 1
            if not (iB < ckt.getNumBuses()):
                break
            # TODO Auto-generated catch block
            try:
                cls.computeYsc(iB)
                # compute YSC for iB-th Bus
            except Esolv32Problem, e:
                pass # astStmt: [Stmt([]), None]
            if (iB * 10) / ckt.getNumBuses() > cls.progressCount:
                cls.progressCount += 1
                DSSGlobals.DSSForms.showPctProgress(30 + (cls.progressCount * 5))

    @classmethod
    def disableAllFaults(cls):
        ckt = DSSGlobals.activeCircuit
        for fault in ckt.getFaults():
            FaultImpl.activeFaultObj = fault
            FaultImpl.activeFaultObj.setEnabled(False)

    @classmethod
    def solveFaultStudy(cls):
        """Full fault study

        @throws Esolv32Problem
        """
        sol = DSSGlobals.activeCircuit.getSolution()
        DSSGlobals.DSSForms.showPctProgress(0)
        DSSGlobals.DSSForms.progressCaption('Computing Open-Circuit Voltages')
        sol.setLoadModel(DSSGlobals.ADMITTANCE)
        cls.disableAllFaults()
        sol.solveDirect()
        # this gets the open circuit voltages and bus lists corrected
        cls.allocateAllSCParms()
        # reallocate bus quantities
        sol.updateVBus()
        # put present solution Voc's in bus quantities
        DSSGlobals.DSSForms.progressCaption('Computing Ysc Matrices for each bus')
        DSSGlobals.DSSForms.showPctProgress(30)
        cls.computeAllYsc()
        DSSGlobals.DSSForms.progressCaption('Computing short-circuit currents.')
        DSSGlobals.DSSForms.showPctProgress(80)
        cls.computeIsc()
        DSSGlobals.DSSForms.showPctProgress(100)
        DSSGlobals.DSSForms.progressCaption('Done.')
        DSSGlobals.DSSForms.progressHide()
        # now should have all we need to make a short circuit report
        return 0

    @classmethod
    def addFrequency(cls, freqList, numFreq, maxFreq, f):
        """Add unique frequency, F to list in ascending order, reallocating if necessary."""
        # See if F is in list
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < numFreq):
                break
            # Allow a little tolerance (0.1 hz) for the frequency for round off error
            if cls.Math.abs(f - freqList[i]) < 0.1:
                return
                # already in list, nothing to do
        # OK, it's not in list, so let's add it
        numFreq += 1
        if numFreq > maxFreq:
            maxFreq += 20
            freqList = Utilities.resizeArray(freqList, maxFreq)
        # let's add it in ascending order
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < numFreq - 1):
                break
            if f < freqList[i]:
                # push down array and insert it
                _2 = True
                j = numFreq - 1
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        j -= 1
                    if not (j >= i):
                        break
                    # TODO Check count down logic
                    freqList[j + 1] = freqList[j]
                freqList[i] = f
                return
        # If we fall through, tack it on to the end
        freqList[numFreq] = f

    @classmethod
    def getSourceFrequency(cls, pc):
        if pc.getDSSClassName().equalsIgnoreCase('vsource'):
            pVsrc = pc
            return pVsrc.getSrcFrequency()
        else:
            pIsrc = pc
            return pIsrc.getSrcFrequency()

    @classmethod
    def collectAllFrequencies(cls, freqList, numFreq):
        # Make a list of all frequencies in use
        # Accumulate all unique frequencies
        maxFreq = 20
        # Initial List size
        numFreq = 0
        freqList = Utilities.resizeArray(freqList, maxFreq)
        ckt = DSSGlobals.activeCircuit
        # Check sources -- each could have a different base frequency
        for p in ckt.getSources():
            if p.isEnabled():
                if DSSGlobals.spectrumClass.find(p.getSpectrum()) is not None:
                    pSpectrum = DSSGlobals.spectrumClass.getActiveObj()
                    f = cls.getSourceFrequency(p)
                    _0 = True
                    j = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            j += 1
                        if not (j < pSpectrum.getNumHarm()):
                            break
                        cls.addFrequency(freqList, numFreq, maxFreq, pSpectrum.getHarmArray()[j] * f)
        # Mark spectra being used
        # Check loads and generators - these are assumed to be at fundamental frequency
        spectrumInUse = [None] * DSSGlobals.spectrumClass.getElementCount()
        # allocate and zero
        for p in ckt.getPCElements():
            if p.isEnabled():
                if DSSGlobals.spectrumClass.find(p.getSpectrum()) is not None:
                    spectrumInUse[DSSGlobals.spectrumClass.getActiveElement()] = 1
        # Add marked spectra to list
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < DSSGlobals.spectrumClass.getElementCount()):
                break
            if spectrumInUse[i] == 1:
                DSSGlobals.spectrumClass.setActiveElement(i)
                pSpectrum = DSSGlobals.spectrumClass.getActiveObj()
                _2 = True
                j = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        j += 1
                    if not (j < pSpectrum.getNumHarm()):
                        break
                    cls.addFrequency(freqList, numFreq, maxFreq, pSpectrum.getHarmArray()[j] * ckt.getFundamental())
        spectrumInUse = None

    @classmethod
    def solveHarmonic(cls):
        frequencyList = [None] * 0
        nFreq = 0
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        DSSGlobals.DSSForms.showPctProgress(0)
        DSSGlobals.DSSForms.progressCaption('Performing Harmonic Solution')
        try:
            if sol.getFrequency() != ckt.getFundamental():
                # Last solution was something other than fundamental
                sol.setFrequency(ckt.getFundamental())
                if not Utilities.retrieveSavedVoltages():
                    return 0
                    # Get saved fundamental frequency solution
            DSSGlobals.monitorClass.sampleAll()
            # store the fundamental frequency in the monitors
            # Get the list of harmonic frequencies to solve at
            if sol.isDoAllHarmonics():
                cls.collectAllFrequencies(frequencyList, nFreq)
                # allocates frequencyList  TODO Check allocation
            else:
                frequencyList = Utilities.resizeArray(frequencyList, sol.getHarmonicListSize())
                nFreq = sol.getHarmonicListSize()
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < nFreq):
                        break
                    frequencyList[i] = ckt.getFundamental() * sol.getHarmonicList()[i]
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < nFreq):
                    break
                sol.setFrequency(frequencyList[i])
                if cls.Math.abs(sol.getHarmonic() - 1.0) > DSSGlobals.EPSILON:
                    # Skip fundamental
                    DSSGlobals.DSSForms.progressCaption('Solving at frequency = ' + String.format.format('%-g', sol.getFrequency()))
                    DSSGlobals.DSSForms.showPctProgress(cls.Math.round((100.0 * i) / nFreq))
                    sol.solveDirect()
                    DSSGlobals.monitorClass.sampleAll()
                    # storage devices are assumed to stay the same since there is no time variation in this mode
            DSSGlobals.DSSForms.showPctProgress(100)
            DSSGlobals.DSSForms.progressCaption('Done.')
        finally:
            DSSGlobals.DSSForms.progressHide()
            DSSGlobals.monitorClass.saveAll()
            frequencyList = None
        # now should have all we need to make a short circuit report
        return 0
