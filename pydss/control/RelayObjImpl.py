from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.control.Relay import (Relay,)
from dss.common.impl.Utilities import (Utilities,)
from dss.control.RelayObj import (RelayObj,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlAction import (ControlAction,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class RelayObjImpl(ControlElemImpl, RelayObj):
    controlType = None
    # Over current relay
    phaseCurve = None
    groundCurve = None
    phaseTrip = None
    groundTrip = None
    phaseInst = None
    groundInst = None
    recloseIntervals = None
    numReclose = None
    resetTime = None
    delayTime = None
    breakerTime = None
    TDPhase = None
    TDGround = None
    relayTarget = None
    # Over/under voltage relay
    # Curves assumed in per unit of base voltage
    OVCurve = None
    UVCurve = None
    VBase = None
    kVBase = None
    # line-neut volts base
    # 46 relay neg seq current
    pickupAmps46 = None
    pctPickup46 = None
    baseAmps46 = None
    isqt46 = None
    # 47 relay
    pickupVolts47 = None
    pctPickup47 = None
    # Generic relay
    overTrip = None
    underTrip = None
    monitoredElementName = None
    monitoredElementTerminal = None
    monitoredElement = None
    presentState = None
    operationCount = None
    lockedOut = None
    armedForClose = None
    armedForOpen = None
    phaseTarget = None
    groundTarget = None
    nextTripTime = None
    lastEventHandle = None
    condOffset = None
    # Offset for monitored terminal
    cBuffer = None

    def __init__(self, parClass, relayName):
        super(RelayObjImpl, self)(parClass)
        self.setName(relayName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.elementName = ''
        self.setControlledElement(None)
        self.elementTerminal = 0
        # TODO Check zero based indexing
        self.monitoredElementName = ''
        self.monitoredElementTerminal = 0
        self.monitoredElement = None
        self.relayTarget = ''
        self.phaseCurve = None
        self.groundCurve = None
        self.OVCurve = None
        self.UVCurve = None
        self.phaseTrip = 1.0
        self.groundTrip = 1.0
        self.TDPhase = 1.0
        self.TDGround = 1.0
        self.phaseInst = 0.0
        self.groundInst = 0.0
        self.resetTime = 15.0
        self.numReclose = 3
        self.recloseIntervals = None
        self.recloseIntervals = Utilities.resizeArray(self.recloseIntervals, 4)
        # fixed allocation of 4
        self.recloseIntervals[0] = 0.5
        self.recloseIntervals[1] = 2.0
        self.recloseIntervals[2] = 2.0
        self.presentState = ControlAction.CLOSE
        self.isqt46 = 1.0
        self.baseAmps46 = 100.0
        self.pctPickup46 = 20.0
        self.pickupAmps46 = self.baseAmps46 * self.pctPickup46 * 0.01
        self.pctPickup47 = 2.0
        self.overTrip = 1.2
        self.underTrip = 0.8
        self.operationCount = 1
        self.lockedOut = False
        self.armedForOpen = False
        self.armedForClose = False
        self.phaseTarget = False
        self.groundTarget = False
        self.nextTripTime = -1.0
        # not set to trip
        self.cBuffer = None
        self.objType = parClass.getDSSClassType()
        # CAP_CONTROL;
        self.initPropertyValues(0)
        # recalcElementData();

    def recalcElementData(self):
        devIndex = Utilities.getCktElementIndex(self.monitoredElementName)
        if devIndex >= 0:
            self.monitoredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            self.setNPhases(self.monitoredElement.getNPhases())
            # force number of phases to be same
            if self.monitoredElementTerminal > self.monitoredElement.getNTerms():
                DSSGlobals.doErrorMsg('Relay: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Re-specify terminal no.', 384)
            else:
                # sets name of i-th terminal's connected bus in Relay's bus list
                self.setBus(1, self.monitoredElement.getBus(self.monitoredElementTerminal))
                # allocate a buffer big enough to hold everything from the monitored element
                self.cBuffer = Utilities.resizeArray(self.cBuffer, self.monitoredElement.getYorder())
                self.condOffset = (self.monitoredElementTerminal - 1) * self.monitoredElement.getNConds()
                # for speedy sampling
                _0 = self.controlType
                _1 = False
                while True:
                    if _0 == Relay.GENERIC:
                        _1 = True
                        if (
                            self.monitoredElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK != DSSClassDefs.PC_ELEMENT
                        ):
                            DSSGlobals.doSimpleMsg('Relay ' + self.getName() + ': Monitored element for generic relay is not a PC element.', 385)
                        else:
                            pElem = self.monitoredElement
                            self.monitorVarIndex = pElem.lookupVariable(self.monitorVariable)
                            if self.monitorVarIndex < 0:
                                DSSGlobals.doSimpleMsg('Relay ' + self.getName() + ': Monitor variable \"' + self.monitorVariable + '\" does not exist.', 386)
                        break
                    break
        # Check for existence of controlled element
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # both CktElement and monitored element must already exist
            self.setControlledElement(DSSGlobals.activeCircuit.getCktElements().get(devIndex))
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # make the 1 st terminal active
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
            DSSGlobals.doErrorMsg('Relay: \"' + self.getName() + '\"', 'CktElement element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 387)
        # Misc stuff
        self.pickupAmps46 = self.baseAmps46 * self.pctPickup46 * 0.01
        _2 = self.nPhases
        _3 = False
        while True:
            if _2 == 1:
                _3 = True
                self.VBase = self.kVBase * 1000.0
                break
            if True:
                _3 = True
                self.VBase = (self.kVBase / DSSGlobals.SQRT3) * 1000.0
                break
            break
        self.pickupVolts47 = self.VBase * self.pctPickup47 * 0.01

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
        _0 = self.nPhases
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                self.VBase = self.kVBase * 1000.0
                break
            if True:
                _1 = True
                self.VBase = (self.kVBase / DSSGlobals.SQRT3) * 1000.0
                break
            break
        self.pickupVolts47 = self.VBase * self.pctPickup47 * 0.01
        super(RelayObjImpl, self).makePosSequence()

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
                            Utilities.appendToEventLog('Relay.' + self.getName(), 'Opened on ' + self.relayTarget + ' & Locked Out ')
                    else:
                        Utilities.appendToEventLog('Relay.' + self.getName(), 'Opened')
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
                        Utilities.appendToEventLog('Relay.' + self.getName(), 'Closed')
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

    def interpretRelayAction(self, action):
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
            _0 = self.controlType
            _1 = False
            while True:
                if _0 == Relay.CURRENT:
                    _1 = True
                    self.overcurrentLogic()
                    # Current
                    break
                if (_1 is True) or (_0 == Relay.VOLTAGE):
                    _1 = True
                    self.voltageLogic()
                    # Reclosing voltage relay - definite time
                    break
                if (_1 is True) or (_0 == Relay.REVPOWER):
                    _1 = True
                    self.revPowerLogic()
                    # One shot to lockout
                    break
                if (_1 is True) or (_0 == Relay.NEGCURRENT):
                    _1 = True
                    self.negSeq46Logic()
                    # One shot to lockout
                    break
                if (_1 is True) or (_0 == Relay.NEGVOLTAGE):
                    _1 = True
                    self.negSeq47Logic()
                    # One shot to lockout
                    break
                if (_1 is True) or (_0 == Relay.GENERIC):
                    _1 = True
                    self.genericLogic()
                    # One shot to lockout
                    break
                break

    def dumpProperties(self, f, complete):
        super(RelayObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(self.getParentClass().getPropertyIdxMap()[i])
        if complete:
            print 

    def getPropertyValue(self, index):
        result = ''
        _0 = self.getParentClass().getPropertyIdxMap()[index]
        _1 = False
        while True:
            if _0 == 13:
                _1 = True
                result = '('
                if self.numReclose == 0:
                    result = result + 'NONE'
                else:
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
                result = super(RelayObjImpl, self).getPropertyValue(index)
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
        self.phaseTarget = False
        self.groundTarget = False
        self.nextTripTime = -1.0
        # not set to trip
        if self.getControlledElement() is not None:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal
            self.getControlledElement().setConductorClosed(0, True)
            # close all phases of active terminal

    def initPropertyValues(self, arrayOffset):
        # FIXME Private method in OpenDSS
        self.propertyValue[0] = ''
        # "element";
        self.propertyValue[1] = '1'
        # "terminal";
        self.propertyValue[2] = ''
        self.propertyValue[3] = '1'
        # "terminal";
        self.propertyValue[4] = 'current'
        self.propertyValue[5] = ''
        self.propertyValue[6] = ''
        self.propertyValue[7] = '1.0'
        self.propertyValue[8] = '1.0'
        self.propertyValue[9] = '0.0'
        self.propertyValue[10] = '0.0'
        self.propertyValue[11] = '15'
        self.propertyValue[12] = '4'
        self.propertyValue[13] = '(0.5, 2.0, 2.0)'
        self.propertyValue[14] = ''
        self.propertyValue[15] = ''
        self.propertyValue[16] = '0.0'
        self.propertyValue[17] = '0.0'
        self.propertyValue[18] = ''
        self.propertyValue[19] = ''
        self.propertyValue[20] = '20'
        self.propertyValue[21] = '1'
        self.propertyValue[22] = '100'
        self.propertyValue[23] = '0'
        self.propertyValue[24] = '2'
        self.propertyValue[25] = '1.2'
        self.propertyValue[26] = '0.8'
        self.propertyValue[27] = '1.0'
        self.propertyValue[28] = '1.0'
        super(RelayObjImpl, self).initPropertyValues(Relay.NumPropsThisClass)

    def interpretRelayType(self, s):
        _0 = s.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'c':
                _1 = True
                self.controlType = Relay.CURRENT
                break
            if (_1 is True) or (_0 == 'v'):
                _1 = True
                self.controlType = Relay.VOLTAGE
                break
            if (_1 is True) or (_0 == 'r'):
                _1 = True
                self.controlType = Relay.REVPOWER
                break
            if (_1 is True) or (_0 == '4'):
                _1 = True
                _2 = s[1]
                _3 = False
                while True:
                    if _2 == '6':
                        _3 = True
                        self.controlType = Relay.NEGCURRENT
                        break
                    if (_3 is True) or (_2 == '7'):
                        _3 = True
                        self.controlType = Relay.NEGVOLTAGE
                        break
                    break
            if (_1 is True) or (_0 == '8'):
                _1 = True
                self.controlType = Relay.GENERIC
                break
            if True:
                _1 = True
                self.controlType = Relay.CURRENT
                break
            break
        # Set definite time defaults
        _4 = s.toLowerCase()[0]
        _5 = False
        while True:
            if _4 == 'c':
                _5 = True
                self.delayTime = 0.0
                break
            if (_5 is True) or (_4 == 'v'):
                _5 = True
                self.delayTime = 0.0
                break
            if (_5 is True) or (_4 == 'r'):
                _5 = True
                self.delayTime = 0.1
                break
            if (_5 is True) or (_4 == '4'):
                _5 = True
                self.delayTime = 0.1
                break
            if (_5 is True) or (_4 == '8'):
                _5 = True
                self.delayTime = 0.1
                break
            if True:
                _5 = True
                self.delayTime = 0.0
                break
            break
        self.propertyValue[23] = String.format.format('%-.g', self.delayTime)

    def genericLogic(self):
        """Generic relays only work on PC elements with control terminals."""
        pElem = self.monitoredElement
        varValue = pElem.getVariable(self.monitorVarIndex)
        # Check for trip
        if (varValue > self.overTrip) or (varValue < self.underTrip):
            if not self.armedForOpen:
                # push the trip operation and arm to trip
                ckt = DSSGlobals.activeCircuit
                self.relayTarget = pElem.variableName(self.monitorVarIndex)
                self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.delayTime + self.breakerTime, ControlAction.OPEN, 0, self)
                self.operationCount = self.numReclose + 1
                # force a lockout
                self.armedForOpen = True
        elif self.armedForOpen:
            # we became unarmed, so reset and disarm
            ckt = DSSGlobals.activeCircuit
            self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
            self.armedForOpen = False
        # Within bounds
        # Less than pickup value: reset if armed

    def negSeq46Logic(self):
        """Negative sequence current relay. Patterned after Basler relay."""
        I012 = [None] * 3
        self.monitoredElement.setActiveTerminalIdx(self.monitoredElementTerminal)
        self.monitoredElement.getCurrents(self.cBuffer)
        iOffset = (self.monitoredElementTerminal - 1) * self.monitoredElement.getNConds()
        # offset for active terminal
        MathUtil.phase2SymComp(self.cBuffer[iOffset + 1], I012)
        negSeqCurrentMag = I012[2].abs()
        if negSeqCurrentMag >= self.pickupAmps46:
            if not self.armedForOpen:
                # push the trip operation and arm to trip
                ckt = DSSGlobals.activeCircuit
                self.relayTarget = '-Seq Curr'
                # Simple estimate of trip time assuming current will be constant
                if self.delayTime > 0.0:
                    tripTime = self.delayTime
                else:
                    tripTime = self.isqt46 / self.Math.pow(negSeqCurrentMag / self.baseAmps46, 2)
                    # sec
                self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + self.breakerTime, ControlAction.OPEN, 0, self)
                self.operationCount = self.numReclose + 1
                # force a lockout
                self.armedForOpen = True
        elif self.armedForOpen:
            # we became unarmed, so reset and disarm
            ckt = DSSGlobals.activeCircuit
            self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
            self.armedForOpen = False
        # Less than pickup value: reset if armed

    def overcurrentLogic(self):
        if self.presentState == ControlAction.CLOSE:
            tripTime = -1.0
            groundTime = -1.0
            phaseTime = -1.0
            # No trip
            # check largest current of all phases of monitored element
            self.monitoredElement.getCurrents(self.cBuffer)
            # Check ground trip, if any
            if (
                (self.groundCurve is not None) or (self.delayTime > 0.0) and self.groundTrip > 0.0
            ):
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
                    # TODO Check zero based indexing
                    CSum = CSum.add(self.cBuffer[i])
                CMag = CSum.abs()
                if (
                    self.groundInst > 0.0 and CMag >= self.groundInst and self.operationCount == 1
                ):
                    groundTime = 0.01 + self.breakerTime
                    # inst trip on first operation
                elif self.delayTime > 0.0:
                    # definite time ground relay
                    if CMag >= self.groundTrip:
                        groundTime = self.delayTime
                    else:
                        groundTime = -1.0
                else:
                    groundTime = self.TDGround * self.groundCurve.getTCCTime(CMag / self.groundTrip)
            if groundTime > 0.0:
                tripTime = groundTime
                self.groundTarget = True
            # if groundTime > 0 then we have a ground trip
            # Check phase trip, if any
            if (
                (self.phaseCurve is not None) or (self.delayTime > 0.0) and self.phaseTrip > 0.0
            ):
                _1 = True
                i = 1 + self.condOffset
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.nPhases + self.condOffset):
                        break
                    # TODO Check zero based indexing
                    CMag = self.cBuffer[i].abs()
                    if (
                        self.phaseInst > 0.0 and CMag >= self.phaseInst and self.operationCount == 1
                    ):
                        phaseTime = 0.01 + self.breakerTime
                        # inst trip on first operation
                        break
                        # if inst, no sense checking other phases
                    else:
                        if self.delayTime > 0.0:
                            # definite time phase relay
                            if CMag >= self.phaseTrip:
                                timeTest = self.delayTime
                            else:
                                timeTest = -1.0
                        else:
                            timeTest = self.TDPhase * self.phaseCurve.getTCCTime(CMag / self.phaseTrip)
                        if timeTest > 0.0:
                            if phaseTime < 0.0:
                                phaseTime = timeTest
                            else:
                                phaseTime = self.Math.min(phaseTime, timeTest)
            # if phaseTime > 0 then we have a phase trip
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
                    self.relayTarget = ''
                    if phaseTime > 0.0:
                        self.relayTarget = self.relayTarget + 'Ph'
                    if groundTime > 0.0:
                        self.relayTarget = self.relayTarget + ' Gnd'
                    self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + self.breakerTime, ControlAction.OPEN, 0, self)
                    if self.operationCount <= self.numReclose:
                        self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + self.breakerTime + self.recloseIntervals[self.operationCount], ControlAction.CLOSE, 0, self)
                    self.armedForOpen = True
                    self.armedForClose = True
            elif self.armedForOpen:
                ckt = DSSGlobals.activeCircuit
                # if current dropped below pickup, disarm trip and set for reset
                self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
                self.armedForOpen = False
                self.armedForClose = False
                self.phaseTarget = False
                self.groundTarget = False

    def revPowerLogic(self):
        # MonitoredElement.ActiveTerminalIdx = MonitoredElementTerminal;
        S = self.monitoredElement.getPower(self.monitoredElementTerminal)
        if S.getReal() < 0.0:
            if self.Math.abs(S.getReal()) > self.phaseInst * 1000.0:
                if not self.armedForOpen:
                    # push the trip operation and arm to trip
                    ckt = DSSGlobals.activeCircuit
                    self.relayTarget = 'Rev P'
                    self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.delayTime + self.breakerTime, ControlAction.OPEN, 0, self)
                    self.operationCount = self.numReclose + 1
                    # force a lockout
                    self.armedForOpen = True
            elif self.armedForOpen:
                # we became unarmed, so reset and disarm
                ckt = DSSGlobals.activeCircuit
                self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
                self.armedForOpen = False

    def voltageLogic(self):
        VMag = 0
        if not self.lockedOut:
            # *** Fix so that fastest trip time applies ***
            self.monitoredElement.getTermVoltages(self.monitoredElementTerminal, self.cBuffer)
            VMin = 1e+50
            VMax = 0.0
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.monitoredElement.getNPhases()):
                    break
                VMag = self.cBuffer[i].abs()
            if VMag > VMax:
                VMax = VMag
            if VMag < VMin:
                VMin = VMag
                # Convert to per unit
            VMax = VMax / self.VBase
            VMin = VMin / self.VBase
            if self.presentState == ControlAction.CLOSE:
                tripTime = -1.0
                OVTime = -1.0
                UVTime = -1.0
                # Check over voltage trip, if any
                if self.OVCurve is not None:
                    OVTime = self.OVCurve.getOVTime(VMax)
                if OVTime > 0.0:
                    tripTime = OVTime
                    # if OVTime > 0 then we have a OV trip
                    # Check UV trip, if any
                if self.UVCurve is not None:
                    UVTime = self.UVCurve.getUVTime(VMin)
                    # If UVTime > 0 then we have a UV trip
                if UVTime > 0.0:
                    if tripTime > 0.0:
                        tripTime = self.Math.min(tripTime, UVTime)
                        # min of UV or OV time
                    else:
                        tripTime = UVTime
                if tripTime > 0.0:
                    ckt = DSSGlobals.activeCircuit
                    if (
                        self.armedForOpen and ckt.getSolution().getDynaVars().t + tripTime + self.breakerTime < self.nextTripTime
                    ):
                        ckt.getControlQueue().delete(self.lastEventHandle)
                        # delete last event from queue
                        self.armedForOpen = False
                        # force it to go through next if
                    if not self.armedForOpen:
                        # then arm for an open operation
                        if tripTime == UVTime:
                            if tripTime == OVTime:
                                self.relayTarget = 'UV + OV'
                            else:
                                self.relayTarget = 'UV'
                        else:
                            self.relayTarget = 'OV'
                        self.nextTripTime = ckt.getSolution().getDynaVars().t + tripTime + self.breakerTime
                        self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), self.nextTripTime, ControlAction.OPEN, 0, self)
                        self.armedForOpen = True
                elif self.armedForOpen:
                    ckt = DSSGlobals.activeCircuit
                    # if voltage dropped below pickup, disarm trip and set for reset
                    ckt.getControlQueue().delete(self.lastEventHandle)
                    # delete last event from queue
                    self.nextTripTime = -1.0
                    self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
                    self.armedForOpen = False
            elif self.operationCount <= self.numReclose:
                if not self.armedForClose:
                    if VMax > 0.9:
                        ckt = DSSGlobals.activeCircuit
                        # OK if voltage > 90%
                        self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.recloseIntervals[self.operationCount], ControlAction.CLOSE, 0, self)
                        self.armedForClose = True
                elif VMax < 0.9:
                    self.armedForClose = False
                # Armed, but check to see if voltage dropped before it reclosed and cancel action
            # Present state is open, check for voltage and then set reclose interval

    def negSeq47Logic(self):
        """Neg seq voltage relay."""
        # FIXME Private members in OpenDSS
        V012 = [None] * 3
        self.monitoredElement.getTermVoltages(self.monitoredElementTerminal, self.cBuffer)
        MathUtil.phase2SymComp(self.cBuffer, V012)
        # phase to symmetrical components
        negSeqVoltageMag = V012[2].abs()
        if negSeqVoltageMag >= self.pickupVolts47:
            if not self.armedForOpen:
                # push the trip operation and arm to trip
                ckt = DSSGlobals.activeCircuit
                self.relayTarget = '-Seq V'
                self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.delayTime + self.breakerTime, ControlAction.OPEN, 0, self)
                self.operationCount = self.numReclose + 1
                # force a lockout
                self.armedForOpen = True
        elif self.armedForOpen:
            # we became unarmed, so reset and disarm
            ckt = DSSGlobals.activeCircuit
            self.lastEventHandle = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + self.resetTime, ControlAction.CTRL_RESET, 0, self)
            self.armedForOpen = False
        # Less than pickup value: reset if armed

    def getControlType(self):
        return self.controlType

    def setControlType(self, type):
        self.controlType = type

    def getPhaseCurve(self):
        return self.phaseCurve

    def setPhaseCurve(self, curve):
        self.phaseCurve = curve

    def getGroundCurve(self):
        return self.groundCurve

    def setGroundCurve(self, curve):
        self.groundCurve = curve

    def getPhaseTrip(self):
        return self.phaseTrip

    def setPhaseTrip(self, trip):
        self.phaseTrip = trip

    def getGroundTrip(self):
        return self.groundTrip

    def setGroundTrip(self, trip):
        self.groundTrip = trip

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

    def getBreakerTime(self):
        return self.breakerTime

    def setBreakerTime(self, time):
        self.breakerTime = time

    def getTDPhase(self):
        return self.TDPhase

    def setTDPhase(self, phase):
        self.TDPhase = phase

    def getTDGround(self):
        return self.TDGround

    def setTDGround(self, ground):
        self.TDGround = ground

    def getRelayTarget(self):
        return self.relayTarget

    def setRelayTarget(self, target):
        self.relayTarget = target

    def getOVCurve(self):
        return self.OVCurve

    def setOVCurve(self, curve):
        self.OVCurve = curve

    def getUVCurve(self):
        return self.UVCurve

    def setUVCurve(self, curve):
        self.UVCurve = curve

    def getVBase(self):
        return self.VBase

    def setVBase(self, base):
        self.VBase = base

    def getKVBase(self):
        return self.kVBase

    def setKVBase(self, base):
        self.kVBase = base

    def getPickupAmps46(self):
        return self.pickupAmps46

    def setPickupAmps46(self, value):
        self.pickupAmps46 = value

    def getPctPickup46(self):
        return self.pctPickup46

    def setPctPickup46(self, value):
        self.pctPickup46 = value

    def getBaseAmps46(self):
        return self.baseAmps46

    def setBaseAmps46(self, value):
        self.baseAmps46 = value

    def getIsqt46(self):
        return self.isqt46

    def setIsqt46(self, value):
        self.isqt46 = value

    def getPickupVolts47(self):
        return self.pickupVolts47

    def setPickupVolts47(self, value):
        self.pickupVolts47 = value

    def getPctPickup47(self):
        return self.pctPickup47

    def setPctPickup47(self, value):
        self.pctPickup47 = value

    def getOverTrip(self):
        return self.overTrip

    def setOverTrip(self, trip):
        self.overTrip = trip

    def getUnderTrip(self):
        return self.underTrip

    def setUnderTrip(self, trip):
        self.underTrip = trip

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

    def setLockedOut(self, value):
        self.lockedOut = value

    def isArmedForClose(self):
        return self.armedForClose

    def setArmedForClose(self, armed):
        self.armedForClose = armed

    def isArmedForOpen(self):
        return self.armedForOpen

    def setArmedForOpen(self, armed):
        self.armedForOpen = armed

    def isPhaseTarget(self):
        return self.phaseTarget

    def setPhaseTarget(self, target):
        self.phaseTarget = target

    def isGroundTarget(self):
        return self.groundTarget

    def setGroundTarget(self, target):
        self.groundTarget = target

    def getNextTripTime(self):
        return self.nextTripTime

    def setNextTripTime(self, time):
        self.nextTripTime = time

    def getLastEventHandle(self):
        return self.lastEventHandle

    def setLastEventHandle(self, handle):
        self.lastEventHandle = handle

    def getCondOffset(self):
        return self.condOffset

    def setCondOffset(self, offset):
        self.condOffset = offset

    def getCBuffer(self):
        return self.cBuffer

    def setCBuffer(self, buffer):
        self.cBuffer = buffer
