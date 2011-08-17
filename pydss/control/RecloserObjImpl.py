from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.control.RecloserObj import (RecloserObj,)
from dss.common.impl.Utilities import (Utilities,)
from dss.control.Recloser import (Recloser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.control.impl.ControlAction import (ControlAction,)
from dss.control.impl.RecloserImpl import (RecloserImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class RecloserObjImpl(ControlElemImpl, RecloserObj):
    phaseDelayed = None
    groundDelayed = None
    phaseFast = None
    groundFast = None
    phaseTrip = None
    groundTrip = None
    phaseInst = None
    groundInst = None
    recloseIntervals = None
    numFast = None
    numReclose = None
    resetTime = None
    delayTime = None
    TDGrDelayed = None
    TDPhDelayed = None
    TDGrFast = None
    TDPhFast = None
    monitoredElementName = None
    monitoredElementTerminal = None
    monitoredElement = None
    presentState = None
    operationCount = None
    lockedOut = None
    armedForClose = None
    armedForOpen = None
    groundTarget = None
    phaseTarget = None
    condOffset = None
    # offset for monitored terminal
    cBuffer = None

    def __init__(self, parClass, recloserName):
        super(RecloserObjImpl, self)(parClass)
        self.setName(recloserName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.elementName = ''
        self.setControlledElement(None)
        self.elementTerminal = 1
        self.monitoredElementName = ''
        self.monitoredElementTerminal = 1
        self.monitoredElement = None
        self.phaseFast = RecloserImpl.getTCC_Curve('a')
        self.phaseDelayed = RecloserImpl.getTCC_Curve('d')
        self.groundFast = None
        self.groundDelayed = None
        self.phaseTrip = 1.0
        self.groundTrip = 1.0
        self.phaseInst = 0.0
        self.groundInst = 0.0
        self.TDGrDelayed = 1.0
        self.TDPhDelayed = 1.0
        self.TDGrFast = 1.0
        self.TDPhFast = 1.0
        self.resetTime = 15.0
        self.numReclose = 3
        self.numFast = 1
        self.recloseIntervals = [None] * 4
        # fixed allocation of 4
        self.recloseIntervals[0] = 0.5
        self.recloseIntervals[1] = 2.0
        self.recloseIntervals[2] = 2.0
        self.presentState = ControlAction.CLOSE
        self.operationCount = 1
        self.lockedOut = False
        self.armedForOpen = False
        self.armedForClose = False
        self.groundTarget = False
        self.phaseTarget = False
        self.cBuffer = None
        self.objType = parClass.getDSSClassType()
        # CAP_CONTROL;
        self.initPropertyValues(0)
        # recalcElementData();

    def recalcElementData(self):
        ckt = DSSGlobals.activeCircuit
        devIndex = Utilities.getCktElementIndex(self.monitoredElementName)
        if devIndex >= 0:
            self.monitoredElement = ckt.getCktElements().get(devIndex)
            self.setNPhases(self.monitoredElement.getNPhases())
            # force number of phases to be same
            if self.monitoredElementTerminal > self.monitoredElement.getNTerms():
                DSSGlobals.doErrorMsg('Recloser: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Re-specify terminal no.', 392)
            else:
                # sets name of i-th terminal's connected bus in Recloser's bus list
                self.setBus(1, self.monitoredElement.getBus(self.monitoredElementTerminal))
                # allocate a buffer big enough to hold everything from the monitored element
                self.cBuffer = Utilities.resizeArray(self.cBuffer, self.monitoredElement.getYorder())
                self.condOffset = (self.monitoredElementTerminal - 1) * self.monitoredElement.getNConds()
                # for speedy sampling
        # Check for existence of controlled element
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # both CktElement and monitored element must already exist
            self.setControlledElement(ckt.getCktElements().get(devIndex))
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # make the 1st terminal active
            if self.getControlledElement().getConductorClosed(0):
                # check state of phases of active terminal
                self.presentState = ControlAction.CLOSE
                self.lockedOut = False
                self.operationCount = 1
                self.armedForOpen = False
            else:
                self.presentState = ControlAction.OPEN
                self.lockedOut = True
                self.operationCount = self.numReclose + 1
                self.armedForClose = False
        else:
            self.setControlledElement(None)
            # element not found
            DSSGlobals.doErrorMsg('Recloser: \"' + self.getName() + '\"', 'CktElement \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 393)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.monitoredElement is not None:
            self.setNPhases(self.monitoredElement.getNPhases())
            self.setNConds(self.nPhases)
            self.setBus(1, self.monitoredElement.getBus(self.elementTerminal))
            # allocate a buffer big enough to hold everything from the monitored element
            self.cBuffer = Utilities.resizeArray(self.cBuffer, self.monitoredElement.getYorder())
            self.condOffset = (self.elementTerminal - 1) * self.monitoredElement.getNConds()
            # for speedy sampling
        super(RecloserObjImpl, self).makePosSequence()

    def calcYPrim(self):
        # leave YPrims as null and they will be ignored
        pass

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

    def doPendingAction(self, code, proxyHdl):
        """Do the action that is pending from last sample."""
        # FIXME Private method in OpenDSS
        self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
        # set active terminal of CktElement to terminal 1
        if code == ControlAction.OPEN.code():
            _0 = self.presentState
            _1 = False
            while True:
                if _0 == self.CLOSE:
                    _1 = True
                    if self.armedForOpen:
                        # ignore if we became disarmed in meantime
                        self.getControlledElement().setConductorClosed(0, False)
                        # open all phases of active terminal
                        if self.operationCount > self.numReclose:
                            self.lockedOut = True
                            Utilities.appendToEventLog('Recloser.' + self.getName(), 'Opened, Locked Out')
                        elif self.operationCount > self.numFast:
                            Utilities.appendToEventLog('Recloser.' + self.getName(), 'Opened, Delayed')
                        else:
                            Utilities.appendToEventLog('Recloser.' + self.getName(), 'Opened, Fast')
                        if self.phaseTarget:
                            Utilities.appendToEventLog(' ', 'Phase Target')
                        if self.groundTarget:
                            Utilities.appendToEventLog(' ', 'Ground Target')
                        self.armedForOpen = False
                    break
                break
        elif code == ControlAction.CLOSE.code():
            _2 = self.presentState
            _3 = False
            while True:
                if _2 == self.OPEN:
                    _3 = True
                    if self.armedForClose and not self.lockedOut:
                        self.getControlledElement().setConductorClosed(0, True)
                        # close all phases of active terminal
                        self.operationCount += 1
                        Utilities.appendToEventLog('Recloser.' + self.getName(), 'Closed')
                        self.armedForClose = False
                    break
                break
        elif code == ControlAction.CTRL_RESET.code():
            _4 = self.presentState
            _5 = False
            while True:
                if _4 == self.CLOSE:
                    _5 = True
                    if not self.armedForOpen:
                        self.operationCount = 1
                        # don't reset if we just rearmed
                    break
                break

    def interpretRecloserAction(self, action):
        if self.getControlledElement() is not None:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal
            _0 = action.toLowerCase()[0]
            _1 = False
            while True:
                if _0 == 'o':
                    _1 = True
                    self.getControlledElement().setConductorClosed(0, False)
                    # open all phases of active terminal
                    self.lockedOut = True
                    self.operationCount = self.numReclose + 1
                    break
                if (_1 is True) or (_0 == 't'):
                    _1 = True
                    self.getControlledElement().setConductorClosed(0, False)
                    # open all phases of active terminal
                    self.lockedOut = True
                    self.operationCount = self.numReclose + 1
                    break
                if (_1 is True) or (_0 == 'c'):
                    _1 = True
                    self.getControlledElement().setConductorClosed(0, True)
                    # close all phases of active terminal
                    self.lockedOut = False
                    self.operationCount = 1
                    break
                break

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
        if self.getControlledElement().getConductorClosed(0):
            # check state of phases of active terminal
            self.presentState = ControlAction.CLOSE
        else:
            self.presentState = ControlAction.OPEN
        if self.operationCount > self.numFast:
            groundCurve = self.groundDelayed
            phaseCurve = self.phaseDelayed
            TDGround = self.TDGrDelayed
            TDPhase = self.TDPhDelayed
        else:
            groundCurve = self.groundFast
            phaseCurve = self.phaseFast
            TDGround = self.TDGrFast
            TDPhase = self.TDPhFast
        if self.presentState == ControlAction.CLOSE:
            tripTime = -1.0
            groundTime = -1.0
            phaseTime = -1.0
            # No trip
            # check largest current of all phases of monitored element
            self.monitoredElement.getCurrents(self.cBuffer)
            # Check ground trip, if any
            if groundCurve is not None:
                CSum = Complex.ZERO
                _0 = True
                i = 1 + self.condOffset
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.nPhases + self.condOffset):
                        break
                    CSum = CSum.add(self.cBuffer[i])
                CMag = CSum.abs()
                if (
                    self.groundInst > 0.0 and CMag >= self.groundInst and self.operationCount == 1
                ):
                    groundTime = 0.01 + self.delayTime
                    # inst trip on first operation
                else:
                    groundTime = TDGround * groundCurve.getTCCTime(CMag / self.groundTrip)
            if groundTime > 0.0:
                tripTime = groundTime
                self.groundTarget = True
            # if GroundTime > 0 then we have a ground trip
            # Check phase trip, if any
            if phaseCurve is not None:
                _1 = True
                i = 1 + self.condOffset
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.nPhases + self.condOffset):
                        break
                    CMag = self.cBuffer[i].abs()
                    if (
                        self.phaseInst > 0.0 and CMag >= self.phaseInst and self.operationCount == 1
                    ):
                        phaseTime = 0.01 + self.delayTime
                        # inst trip on first operation
                        break
                        # no sense checking other phases
                    else:
                        timeTest = TDPhase * phaseCurve.getTCCTime(CMag / self.phaseTrip)
                        if timeTest > 0.0:
                            if phaseTime < 0.0:
                                phaseTime = timeTest
                            else:
                                phaseTime = self.Math.min(phaseTime, timeTest)
            # if PhaseTime > 0 then we have a phase trip
            if phaseTime > 0.0:
                self.phaseTarget = True
                if tripTime > 0.0:
                    tripTime = self.Math.min(tripTime, phaseTime)
                else:
                    tripTime = phaseTime
            if tripTime > 0.0:
                if not self.armedForOpen:
                    ckt = DSSGlobals.activeCircuit
                    # then arm for an open operation
                    ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + self.delayTime, ControlAction.OPEN, 0, self)
                    if self.operationCount <= self.numReclose:
                        ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + self.delayTime + self.recloseIntervals[self.operationCount], ControlAction.CLOSE, 0, self)
                    self.armedForOpen = True
                    self.armedForClose = True
            elif self.armedForOpen:
                ckt = DSSGlobals.activeCircuit
                # if current dropped below pickup, disarm trip and set for reset.
                ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
                self.armedForOpen = False
                self.armedForClose = False
                self.groundTarget = False
                self.phaseTarget = False

    def dumpProperties(self, f, complete):
        super(RecloserObjImpl, self).dumpProperties(f, complete)
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

    def getPropertyValue(self, index):
        result = ''
        _0 = index
        _1 = False
        while True:
            if _0 == 15:
                _1 = True
                result = '('
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.numReclose):
                        break
                    result = result + String.format.format('%-g, ', self.recloseIntervals[i])
                result = result + ')'
                break
            if True:
                _1 = True
                result = super(RecloserObjImpl, self).getPropertyValue(index)
                break
            break
        return result

    def reset(self):
        """Reset to initial defined state."""
        self.presentState = ControlAction.CLOSE
        self.operationCount = 1
        self.lockedOut = False
        self.armedForOpen = False
        self.armedForClose = False
        self.groundTarget = False
        self.phaseTarget = False
        if self.getControlledElement() is not None:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal
            self.getControlledElement().setConductorClosed(0, True)
            # close all phases of active terminal

    def initPropertyValues(self, arrayOffset):
        # FIXME Private members in Open DSS
        self.propertyValue[0] = ''
        # "element";
        self.propertyValue[1] = '1'
        # "terminal";
        self.propertyValue[2] = ''
        self.propertyValue[3] = '1'
        # "terminal";
        self.propertyValue[4] = String.valueOf.valueOf(self.numFast)
        self.propertyValue[5] = ''
        self.propertyValue[6] = ''
        self.propertyValue[7] = ''
        self.propertyValue[8] = ''
        self.propertyValue[9] = '1.0'
        self.propertyValue[10] = '1.0'
        self.propertyValue[11] = '0'
        self.propertyValue[12] = '0'
        self.propertyValue[13] = '15'
        self.propertyValue[14] = '4'
        self.propertyValue[15] = '(0.5, 2.0, 2.0)'
        self.propertyValue[16] = '0.0'
        self.propertyValue[17] = ''
        self.propertyValue[18] = '1.0'
        self.propertyValue[19] = '1.0'
        self.propertyValue[20] = '1.0'
        self.propertyValue[21] = '1.0'
        super(RecloserObjImpl, self).initPropertyValues(Recloser.NumPropsThisClass)

    def getPhaseDelayed(self):
        return self.phaseDelayed

    def setPhaseDelayed(self, value):
        self.phaseDelayed = value

    def getGroundDelayed(self):
        return self.groundDelayed

    def setGroundDelayed(self, value):
        self.groundDelayed = value

    def getPhaseFast(self):
        return self.phaseFast

    def setPhaseFast(self, value):
        self.phaseFast = value

    def getGroundFast(self):
        return self.groundFast

    def setGroundFast(self, value):
        self.groundFast = value

    def getPhaseTrip(self):
        return self.phaseTrip

    def setPhaseTrip(self, value):
        self.phaseTrip = value

    def getGroundTrip(self):
        return self.groundTrip

    def setGroundTrip(self, value):
        self.groundTrip = value

    def getPhaseInst(self):
        return self.phaseInst

    def setPhaseInst(self, value):
        self.phaseInst = value

    def getGroundInst(self):
        return self.groundInst

    def setGroundInst(self, value):
        self.groundInst = value

    def getRecloseIntervals(self):
        return self.recloseIntervals

    def setRecloseIntervals(self, intervals):
        self.recloseIntervals = intervals

    def getNumFast(self):
        return self.numFast

    def setNumFast(self, num):
        self.numFast = num

    def getNumReclose(self):
        return self.numReclose

    def setNumReclose(self, num):
        self.numReclose = num

    def getResetTime(self):
        return self.resetTime

    def setResetTime(self, time):
        self.resetTime = time

    def getDelayTime(self):
        return self.delayTime

    def setDelayTime(self, time):
        self.delayTime = time

    def getTDGrDelayed(self):
        return self.TDGrDelayed

    def setTDGrDelayed(self, value):
        self.TDGrDelayed = value

    def getTDPhDelayed(self):
        return self.TDPhDelayed

    def setTDPhDelayed(self, value):
        self.TDPhDelayed = value

    def getTDGrFast(self):
        return self.TDGrFast

    def setTDGrFast(self, value):
        self.TDGrFast = value

    def getTDPhFast(self):
        return self.TDPhFast

    def setTDPhFast(self, value):
        self.TDPhFast = value

    def getMonitoredElementName(self):
        return self.monitoredElementName

    def setMonitoredElementName(self, name):
        self.monitoredElementName = name

    def getMonitoredElementTerminal(self):
        return self.monitoredElementTerminal

    def setMonitoredElementTerminal(self, terminal):
        self.monitoredElementTerminal = terminal

    def getMonitoredElement(self):
        return self.monitoredElement

    def setMonitoredElement(self, element):
        self.monitoredElement = element

    def getPresentState(self):
        return self.presentState

    def setPresentState(self, state):
        self.presentState = state

    def getOperationCount(self):
        return self.operationCount

    def setOperationCount(self, count):
        self.operationCount = count

    def isLockedOut(self):
        return self.lockedOut

    def setLockedOut(self, locked):
        self.lockedOut = locked

    def isArmedForClose(self):
        return self.armedForClose

    def setArmedForClose(self, armed):
        self.armedForClose = armed

    def isArmedForOpen(self):
        return self.armedForOpen

    def setArmedForOpen(self, armed):
        self.armedForOpen = armed

    def isGroundTarget(self):
        return self.groundTarget

    def setGroundTarget(self, target):
        self.groundTarget = target

    def isPhaseTarget(self):
        return self.phaseTarget

    def setPhaseTarget(self, target):
        self.phaseTarget = target

    def getCondOffset(self):
        return self.condOffset

    def setCondOffset(self, offset):
        self.condOffset = offset

    def getCBuffer(self):
        return self.cBuffer

    def setCBuffer(self, buffer):
        self.cBuffer = buffer
