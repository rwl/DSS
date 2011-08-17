from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.control.CapControl import (CapControl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.control.CapControlObj import (CapControlObj,)
from dss.control.impl.ControlAction import (ControlAction,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class CapControlObjImpl(ControlElemImpl, CapControlObj):

    class CapControlType(object):
        CURRENT = 'CURRENT'
        VOLTAGE = 'VOLTAGE'
        KVAR = 'KVAR'
        TIME = 'TIME'
        PF = 'PF'
        SRP = 'SRP'
        _values = [CURRENT, VOLTAGE, KVAR, TIME, PF, SRP]

        @classmethod
        def values(cls):
            return cls._enum_values[:]

    controlType = None
    CTPhase = None
    PTPhase = None
    # "ALL" is -1
    onValue = None
    offValue = None
    PFOnValue = None
    PFOffValue = None
    CTRatio = None
    PTRatio = None
    OnDelay = None
    OffDelay = None
    DeadTime = None
    LastOpenTime = None
    VOverride = None
    VMax = None
    VMin = None
    capacitorName = None
    monitoredElement = None
    controlledCapacitor = None
    pendingChange = None
    shouldSwitch = None
    # true: action is pending
    armed = None
    # control is armed for switching unless reset
    presentState = None
    initialState = None
    controlActionHandle = None
    condOffset = None
    # offset for monitored terminal
    cBuffer = None

    def __init__(self, parClass, capControlName):
        super(CapControlObjImpl, self)(parClass)
        self.setName(capControlName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.CTPhase = 1
        self.PTPhase = 1
        self.PTRatio = 60.0
        self.CTRatio = 60.0
        self.controlType = self.CapControlType.CURRENT
        self.OnDelay = 15.0
        self.OffDelay = 15.0
        self.DeadTime = 300.0
        self.LastOpenTime = -self.DeadTime
        self.onValue = 300.0
        self.offValue = 200.0
        self.PFOnValue = 0.95
        self.PFOffValue = 1.05
        self.VOverride = False
        self.VMax = 126
        self.VMin = 115
        self.elementName = ''
        self.setControlledElement(None)
        self.elementTerminal = 1
        self.capacitorName = ''
        self.monitoredElement = None
        self.presentState = ControlAction.CLOSE
        self.shouldSwitch = False
        self.armed = False
        self.setPendingChange(ControlAction.NONE)
        self.controlActionHandle = 0
        self.cBuffer = None
        self.objType = parClass.getDSSClassType()
        # CAP_CONTROL;
        self.initPropertyValues(0)
        # recalcElementData();

    def recalcElementData(self):
        ckt = DSSGlobals.activeCircuit
        # Check for existence of capacitor
        devIndex = Utilities.getCktElementIndex(self.capacitorName)
        if devIndex >= 0:
            # both capacitor and monitored element must already exist
            self.setControlledElement(ckt.getCktElements().get(devIndex))
            self.controlledCapacitor = self.getCapacitor()
            self.setNPhases(self.getControlledElement().getNPhases())
            # force number of phases to be same
            self.setNConds(self.nPhases)
            self.getControlledElement().setActiveTerminalIdx(0)
            # make the 1st terminal active   TODO Check zero based indexing
            # Get control synched up with capacitor
            if (
                self.controlledCapacitor.availableSteps() == self.controlledCapacitor.getNumSteps()
            ):
                self.getControlledElement().setConductorClosed(0, False)
                # TODO Check zero based indexing
            else:
                self.getControlledElement().setConductorClosed(0, True)
                # TODO Check zero based indexing
            if self.getControlledElement().getConductorClosed(0):
                # check state of phases of active terminal
                self.presentState = ControlAction.CLOSE
            else:
                self.presentState = ControlAction.OPEN
        else:
            self.setControlledElement(None)
            # element not found
            DSSGlobals.doErrorMsg('CapControl: \"' + self.getName() + '\"', 'Capacitor Element \"' + self.capacitorName + '\" Not Found.', 'Element must be defined previously.', 361)
        self.initialState = self.presentState
        # Check for existence of monitored element
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            self.monitoredElement = ckt.getCktElements().get(devIndex)
            if self.elementTerminal > self.monitoredElement.getNTerms() - 1:
                # TODO Check zero based indexing
                DSSGlobals.doErrorMsg('CapControl: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Re-specify terminal no.', 362)
            else:
                # sets name of i-th terminal's connected bus in CapControl's buslist
                self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
                # TODO Check zero based indexing
                # allocate a buffer big enough to hold everything from the monitored element
                self.cBuffer = Utilities.resizeArray(self.cBuffer, self.monitoredElement.getYorder())
                self.condOffset = (self.elementTerminal - 1) * self.monitoredElement.getNConds()
                # for speedy sampling
        else:
            DSSGlobals.doSimpleMsg('Monitored Element in CapControl.' + self.getName() + ' does not exist:\"' + self.elementName + '\"', 363)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.getControlledElement() is not None:
            self.setEnabled(self.getControlledElement().isEnabled())
            self.setNPhases(self.getControlledElement().getNPhases())
            self.setNConds(self.nPhases)
        if self.monitoredElement is not None:
            self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
            # allocate a buffer big enough to hold everything from the monitored element
            self.cBuffer = Utilities.resizeArray(self.cBuffer, self.monitoredElement.getYorder())
            self.condOffset = (self.elementTerminal - 1) * self.monitoredElement.getNConds()
            # for speedy sampling
        super(CapControlObjImpl, self).makePosSequence()

    def calcYPrim(self):
        # leave YPrims as null and they will be ignored
        pass

    def getControlCurrent(self, controlCurrent):
        """Get current to control on based on type of control specified."""
        # FIXME: return double
        _0 = self.CTPhase
        _1 = False
        while True:
            if _0 == CapControl.AVGPHASES:
                _1 = True
                controlCurrent.setValue(0.0)
                # get avg of all phases
                _2 = True
                i = 1 + self.condOffset
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases + self.condOffset):
                        break
                    # TODO Check zero based indexing
                    controlCurrent.add(self.cBuffer[i].abs())
                controlCurrent.setValue(controlCurrent.doubleValue() / self.nPhases / self.CTRatio)
                break
            if (_1 is True) or (_0 == CapControl.MAXPHASE):
                _1 = True
                controlCurrent.setValue(0.0)
                # get max of all phases
                _3 = True
                i = 1 + self.condOffset
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nPhases + self.condOffset):
                        break
                    controlCurrent.setValue(self.Math.max(controlCurrent.doubleValue(), self.cBuffer[i].abs()))
                controlCurrent.setValue(controlCurrent.doubleValue() / self.CTRatio)
                break
            if (_1 is True) or (_0 == CapControl.MINPHASE):
                _1 = True
                controlCurrent.setValue(1e+50)
                # get min of all phases
                _4 = True
                i = 1 + self.condOffset
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.nPhases + self.condOffset):
                        break
                    controlCurrent.setValue(self.Math.min(controlCurrent.doubleValue(), self.cBuffer[i].abs()))
                controlCurrent.setValue(controlCurrent.doubleValue() / self.CTRatio)
                break
            if True:
                _1 = True
                controlCurrent.setValue(self.cBuffer[self.CTPhase].abs() / self.CTRatio)
                # monitored phase only
                break
            break

    def getCurrents(self, curr):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            curr[i] = Complex.ZERO

    def getInjCurrents(self, curr):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            curr[i] = Complex.ZERO

    def dumpProperties(self, f, complete):
        super(CapControlObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print 

    def doPendingAction(self, code, proxyHdl):
        """Do the action that is pending from last sample."""
        self.getControlledElement().setActiveTerminalIdx(0)
        # set active terminal of capacitor to terminal 1  TODO Check zero based indexing
        _0 = self.pendingChange
        _1 = False
        while True:
            if _0 == self.OPEN:
                _1 = True
                _2 = self.controlledCapacitor.getNumSteps()
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        if self.presentState == ControlAction.CLOSE:
                            self.getControlledElement().setConductorClosed(0, False)
                            # open all phases of active terminal
                            Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), '**Opened**')
                            self.presentState = ControlAction.OPEN
                            sol = DSSGlobals.activeCircuit.getSolution()
                            self.LastOpenTime = sol.getDynaVars().t + (3600.0 * sol.getIntHour())
                        break
                    if True:
                        _3 = True
                        if self.presentState == ControlAction.CLOSE:
                            # do this only if at least one step is closed
                            if not self.controlledCapacitor.subtractStep():
                                self.presentState = ControlAction.OPEN
                                self.getControlledElement().setConductorClosed(0, False)
                                # open all phases of active terminal
                                Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), '**Opened**')
                            else:
                                Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), '**Step Down**')
                        break
                    break
                break
            if (_1 is True) or (_0 == self.CLOSE):
                _1 = True
                if self.presentState == ControlAction.OPEN:
                    self.getControlledElement().setConductorClosed(0, True)
                    # close all phases of active terminal
                    Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), '**Closed**')
                    self.presentState = ControlAction.CLOSE
                    self.controlledCapacitor.addStep()
                elif self.controlledCapacitor.addStep():
                    Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), '**Step Up**')
                break
            if True:
                _1 = True
                break
            break
        self.shouldSwitch = False
        self.armed = False
        # reset control

    def nextDeltaPhase(self, iphs):
        result = iphs + 1
        if result >= self.nPhases:
            result = 0
        return result

    def getControlVoltage(self, controlVoltage):
        """Get Voltage used for voltage control based on specified options."""
        # FIXME: return double
        _0 = self.PTPhase
        _1 = False
        while True:
            if _0 == CapControl.AVGPHASES:
                _1 = True
                controlVoltage.setValue(0.0)
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.monitoredElement.getNPhases()):
                        break
                    controlVoltage.add(self.cBuffer[i].abs())
                controlVoltage.setValue(controlVoltage.doubleValue() / self.monitoredElement.getNPhases() / self.PTRatio)
                break
            if (_1 is True) or (_0 == CapControl.MAXPHASE):
                _1 = True
                controlVoltage.setValue(0.0)
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.monitoredElement.getNPhases()):
                        break
                    controlVoltage.setValue(self.Math.max(controlVoltage.doubleValue(), self.cBuffer[i].abs()))
                controlVoltage.setValue(controlVoltage.doubleValue() / self.PTRatio)
                break
            if (_1 is True) or (_0 == CapControl.MINPHASE):
                _1 = True
                controlVoltage.setValue(1e+50)
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.monitoredElement.getNPhases()):
                        break
                    controlVoltage.setValue(self.Math.min(controlVoltage.doubleValue(), self.cBuffer[i].abs()))
                controlVoltage.setValue(controlVoltage.doubleValue() / self.PTRatio)
                break
            if True:
                _1 = True
                pElem = self.getControlledElement()
                _5 = pElem.getConnection()
                _6 = False
                while True:
                    if _5 == 1:
                        _6 = True
                        controlVoltage.setValue(self.cBuffer[self.PTPhase].subtract(self.cBuffer[self.nextDeltaPhase(self.PTPhase)]).abs() / self.PTRatio)
                        # delta
                        break
                    if True:
                        _6 = True
                        controlVoltage.setValue(self.cBuffer[self.PTPhase].abs() / self.PTRatio)
                        # wye - default
                        break
                    break
                break
            break

    @classmethod
    def PF1to2(cls, S):
        # return PF in range of 1 to 2
        Sabs = S.abs()
        if Sabs != 0.0:
            result = cls.Math.abs(S.getReal()) / Sabs
        else:
            result = 1.0
            # default to unity
        if S.getImaginary() < 0.0:
            result = 2.0 - result
        return result

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        VTest = MutableDouble()
        currTest = MutableDouble()
        self.getControlledElement().setActiveTerminalIdx(0)
        if self.getControlledElement().getConductorClosed(0):
            # check state of phases of active terminal
            self.presentState = ControlAction.CLOSE
        else:
            self.presentState = ControlAction.OPEN
        self.shouldSwitch = False
        # first check voltage override
        if self.VOverride:
            if self.controlType != self.CapControlType.VOLTAGE:
                # don't bother for voltage control
                self.monitoredElement.getTermVoltages(self.elementTerminal, self.cBuffer)
                self.getControlVoltage(VTest)
                _0 = self.presentState
                _1 = False
                while True:
                    if _0 == self.OPEN:
                        _1 = True
                        if VTest.doubleValue() < self.VMin:
                            self.setPendingChange(ControlAction.CLOSE)
                            self.shouldSwitch = True
                        break
                    if (_1 is True) or (_0 == self.CLOSE):
                        _1 = True
                        if VTest.doubleValue() > self.VMax:
                            self.setPendingChange(ControlAction.OPEN)
                            self.shouldSwitch = True
                        break
                    break
        if not self.shouldSwitch:
            # else skip other control evaluations
            _2 = self.controlType
            _3 = False
            while True:
                if _2 == self.CURRENT:
                    _3 = True
                    self.monitoredElement.getCurrents(self.cBuffer)
                    self.getControlCurrent(currTest)
                    _4 = self.presentState
                    _5 = False
                    while True:
                        if _4 == self.OPEN:
                            _5 = True
                            if currTest.doubleValue() > self.onValue:
                                self.setPendingChange(ControlAction.CLOSE)
                                self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        if (_5 is True) or (_4 == self.CLOSE):
                            _5 = True
                            if currTest.doubleValue() < self.offValue:
                                self.setPendingChange(ControlAction.OPEN)
                                self.shouldSwitch = True
                            elif self.controlledCapacitor.availableSteps() > 0:
                                if currTest.doubleValue() > self.onValue:
                                    self.setPendingChange(ControlAction.CLOSE)
                                    self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        break
                    break
                if (_3 is True) or (_2 == self.VOLTAGE):
                    _3 = True
                    self.monitoredElement.getTermVoltages(self.elementTerminal, self.cBuffer)
                    self.getControlVoltage(VTest)
                    _6 = self.presentState
                    _7 = False
                    while True:
                        if _6 == self.OPEN:
                            _7 = True
                            if VTest.doubleValue() < self.onValue:
                                self.setPendingChange(ControlAction.CLOSE)
                                self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        if (_7 is True) or (_6 == self.CLOSE):
                            _7 = True
                            if VTest.doubleValue() > self.offValue:
                                self.setPendingChange(ControlAction.OPEN)
                                self.shouldSwitch = True
                            elif self.controlledCapacitor.availableSteps() > 0:
                                if VTest.doubleValue() < self.onValue:
                                    self.setPendingChange(ControlAction.CLOSE)
                                    self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        break
                    break
                if (_3 is True) or (_2 == self.KVAR):
                    _3 = True
                    S = self.monitoredElement.getPower(self.elementTerminal)
                    Q = S.getImaginary() * 0.001
                    # kvar
                    _8 = self.presentState
                    _9 = False
                    while True:
                        if _8 == self.OPEN:
                            _9 = True
                            if Q > self.onValue:
                                self.setPendingChange(ControlAction.CLOSE)
                                self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        if (_9 is True) or (_8 == self.CLOSE):
                            _9 = True
                            if Q < self.offValue:
                                self.setPendingChange(ControlAction.OPEN)
                                self.shouldSwitch = True
                            elif self.controlledCapacitor.availableSteps() > 0:
                                if Q > self.onValue:
                                    self.setPendingChange(ControlAction.CLOSE)
                                    # we can go some more
                                    self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        break
                    break
                if (_3 is True) or (_2 == self.SRP):
                    _3 = True
                    S = self.monitoredElement.getPower(self.elementTerminal)
                    Q = (S.getImaginary() * 0.001) + (0.20306 * S.getReal() * 0.001)
                    # kvar for -.98 PF
                    _10 = self.presentState
                    _11 = False
                    while True:
                        if _10 == self.OPEN:
                            _11 = True
                            if Q > self.onValue:
                                self.setPendingChange(ControlAction.CLOSE)
                                self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        if (_11 is True) or (_10 == self.CLOSE):
                            _11 = True
                            if Q < self.offValue:
                                self.setPendingChange(ControlAction.OPEN)
                                self.shouldSwitch = True
                            elif self.controlledCapacitor.availableSteps() > 0:
                                if Q > self.onValue:
                                    self.setPendingChange(ControlAction.CLOSE)
                                    # we can go some more
                                    self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        break
                    break
                if (_3 is True) or (_2 == self.TIME):
                    _3 = True
                    sol = DSSGlobals.activeCircuit.getSolution()
                    normalizedTime = self.normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t)
                    _12 = self.presentState
                    _13 = False
                    while True:
                        if _12 == self.OPEN:
                            _13 = True
                            if self.offValue > self.onValue:
                                if normalizedTime >= self.onValue and normalizedTime < self.offValue:
                                    self.setPendingChange(ControlAction.CLOSE)
                                    self.shouldSwitch = True
                                else:
                                    # reset
                                    self.setPendingChange(ControlAction.NONE)
                            elif normalizedTime >= self.onValue and normalizedTime < 24.0:
                                self.setPendingChange(ControlAction.CLOSE)
                                self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            # OFF time is next day
                            break
                        if (_13 is True) or (_12 == self.CLOSE):
                            _13 = True
                            if self.offValue > self.onValue:
                                if normalizedTime >= self.offValue:
                                    self.setPendingChange(ControlAction.OPEN)
                                    self.shouldSwitch = True
                                elif self.controlledCapacitor.availableSteps() > 0:
                                    if normalizedTime >= self.onValue and normalizedTime < self.offValue:
                                        self.setPendingChange(ControlAction.CLOSE)
                                        # we can go some more
                                        self.shouldSwitch = True
                                else:
                                    # reset
                                    self.setPendingChange(ControlAction.NONE)
                            elif normalizedTime >= self.offValue and normalizedTime < self.onValue:
                                self.setPendingChange(ControlAction.OPEN)
                                self.shouldSwitch = True
                            elif self.controlledCapacitor.availableSteps() > 0:
                                if normalizedTime >= self.onValue and normalizedTime < 24.0:
                                    self.setPendingChange(ControlAction.CLOSE)
                                    # we can go some more
                                    self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            # off time is next day
                            break
                        break
                if (_3 is True) or (_2 == PF):
                    _3 = True
                    S = self.monitoredElement.getPower(self.elementTerminal)
                    PF = self.PF1to2(S)
                    # TODO Check zero based indexing
                    # PF is in range of 0 .. 2; leading is 1..2
                    # When turning on make sure there is at least half the kvar of the bank
                    _14 = self.presentState
                    _15 = False
                    while True:
                        if _14 == self.OPEN:
                            _15 = True
                            if (
                                PF < self.PFOnValue and S.getImaginary() * 0.001 > self.controlledCapacitor.getTotalKVAr() * 0.5
                            ):
                                # make sure we don't go too far leading
                                self.setPendingChange(ControlAction.CLOSE)
                                self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        if (_15 is True) or (_14 == self.CLOSE):
                            _15 = True
                            if PF > self.PFOffValue:
                                self.setPendingChange(ControlAction.OPEN)
                                self.shouldSwitch = True
                            elif self.controlledCapacitor.availableSteps() > 0:
                                if (
                                    PF < self.PFOnValue and S.getImaginary() * 0.001 > (self.controlledCapacitor.getTotalKVAr() / self.controlledCapacitor.getNumSteps()) * 0.5
                                ):
                                    self.setPendingChange(ControlAction.CLOSE)
                                    # we can go some more
                                    self.shouldSwitch = True
                            else:
                                # reset
                                self.setPendingChange(ControlAction.NONE)
                            break
                        break
                    break
                break
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if self.shouldSwitch and not self.armed:
            if self.pendingChange == ControlAction.CLOSE:
                if (
                    (sol.getDynaVars().t + (sol.getIntHour() * 3600.0)) - self.LastOpenTime < self.DeadTime
                ):
                    # delay the close operation
                    # 2-6-09 Added ONDelay to DeadTime so that all caps do not close back in at same time
                    self.timeDelay = self.Math.max(self.OnDelay, (self.DeadTime + self.OnDelay) - (sol.getDynaVars().t + (sol.getIntHour() * 3600.0)) - self.LastOpenTime)
                else:
                    self.timeDelay = self.OnDelay
            else:
                self.timeDelay = self.OffDelay
            self.controlActionHandle = ckt.getControlQueue().push(sol.getIntHour(), sol.getDynaVars().t + self.timeDelay, self.pendingChange, 0, self)
            self.armed = True
            Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), String.format.format('**Armed**, Delay= %.5g sec', self.timeDelay))
        if self.armed and self.pendingChange == ControlAction.NONE:
            ckt.getControlQueue().delete(self.controlActionHandle)
            self.armed = False
            Utilities.appendToEventLog('Capacitor.' + self.getControlledElement().getName(), '**Reset**')

    def getCapacitor(self):
        return self.getControlledElement()

    def normalizeToTOD(self, h, sec):
        """Normalize time to a floating point number representing time of day if Hour > 24
        Resulting time should be 0:00+ to 24:00 inclusive.
        """
        if h > 24:
            hourOfDay = h - (((h - 1) / 24) * 24)
            # creates numbers 1-24
        else:
            hourOfDay = h
        result = hourOfDay + (sec / 3600.0)
        # if the TOD is at least slightly greater than 24:00 wrap around to 0:00
        if result - 24.0 > DSSGlobals.EPSILON:
            result = result - 24.0
            # Wrap around
        return result

    def reset(self):
        """Reset to initial defined state."""
        self.setPendingChange(ControlAction.NONE)
        self.getControlledElement().setActiveTerminalIdx(0)
        _0 = self.initialState
        _1 = False
        while True:
            if _0 == self.OPEN:
                _1 = True
                self.getControlledElement().setConductorClosed(0, False)
                # open all phases of active terminal
                break
            if (_1 is True) or (_0 == self.CLOSE):
                _1 = True
                self.getControlledElement().setConductorClosed(0, True)
                # close all phases of active terminal
                break
            break
        self.shouldSwitch = False
        self.LastOpenTime = -self.DeadTime
        self.presentState = self.initialState

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = ''
        # "element";
        self.propertyValue[1] = '1'
        # "terminal";
        self.propertyValue[2] = ''
        self.propertyValue[3] = 'current'
        self.propertyValue[4] = '60'
        self.propertyValue[5] = '60'
        self.propertyValue[6] = '300'
        self.propertyValue[7] = '200'
        self.propertyValue[8] = '15'
        self.propertyValue[9] = 'NO'
        self.propertyValue[10] = '126'
        self.propertyValue[11] = '115'
        self.propertyValue[12] = '15'
        self.propertyValue[13] = '300'
        self.propertyValue[14] = '1'
        self.propertyValue[15] = '1'
        super(CapControlObjImpl, self).initPropertyValues(CapControl.NumPropsThisClass)

    def setPendingChange(self, value):
        self.pendingChange = value
        self.dblTraceParameter = value.code()

    def getPendingChange(self):
        return self.pendingChange

    def getControlType(self):
        return self.controlType

    def setControlType(self, type):
        self.controlType = type

    def getOnValue(self):
        return self.onValue

    def getOffValue(self):
        return self.offValue

    def getPFOnValue(self):
        return self.PFOnValue

    def getPFOffValue(self):
        return self.PFOffValue

    def getCTRatio(self):
        return self.CTRatio

    def getPTRatio(self):
        return self.PTRatio

    def getOnDelay(self):
        return self.OnDelay

    def getOffDelay(self):
        return self.OffDelay

    def getDeadTime(self):
        return self.DeadTime

    def isVOverride(self):
        return self.VOverride

    def getVMax(self):
        return self.VMax

    def getVMin(self):
        # FIXME Private properties in OpenDSS
        return self.VMin

    def getCTPhase(self):
        return self.CTPhase

    def setCTPhase(self, phase):
        self.CTPhase = phase

    def getPTPhase(self):
        return self.PTPhase

    def setPTPhase(self, phase):
        self.PTPhase = phase

    def getLastOpenTime(self):
        return self.LastOpenTime

    def setLastOpenTime(self, time):
        self.LastOpenTime = time

    def getCapacitorName(self):
        return self.capacitorName

    def setCapacitorName(self, name):
        self.capacitorName = name

    def getMonitoredElement(self):
        return self.monitoredElement

    def setMonitoredElement(self, element):
        self.monitoredElement = element

    def getControlledCapacitor(self):
        return self.controlledCapacitor

    def setControlledCapacitor(self, capacitor):
        self.controlledCapacitor = capacitor

    def isShouldSwitch(self):
        return self.shouldSwitch

    def setShouldSwitch(self, value):
        self.shouldSwitch = value

    def isArmed(self):
        return self.armed

    def setArmed(self, value):
        self.armed = value

    def getPresentState(self):
        return self.presentState

    def setPresentState(self, state):
        self.presentState = state

    def getInitialState(self):
        return self.initialState

    def setInitialState(self, state):
        self.initialState = state

    def getControlActionHandle(self):
        return self.controlActionHandle

    def setControlActionHandle(self, handle):
        self.controlActionHandle = handle

    def getCondOffset(self):
        return self.condOffset

    def setCondOffset(self, offset):
        self.condOffset = offset

    def getCBuffer(self):
        return self.cBuffer

    def setCBuffer(self, buffer):
        self.cBuffer = buffer

    def setOnValue(self, value):
        self.onValue = value

    def setOffValue(self, value):
        self.offValue = value

    def setPFOnValue(self, value):
        self.PFOnValue = value

    def setPFOffValue(self, value):
        self.PFOffValue = value

    def setCTRatio(self, ratio):
        self.CTRatio = ratio

    def setPTRatio(self, ratio):
        self.PTRatio = ratio

    def setOnDelay(self, delay):
        self.OnDelay = delay

    def setOffDelay(self, delay):
        self.OffDelay = delay

    def setDeadTime(self, time):
        self.DeadTime = time

    def setVOverride(self, vOverride):
        self.VOverride = vOverride

    def setVMax(self, vmax):
        self.VMax = vmax

    def setVMin(self, vmin):
        self.VMin = vmin
