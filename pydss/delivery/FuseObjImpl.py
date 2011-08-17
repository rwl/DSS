from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.Fuse import (Fuse,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.control.impl.ControlAction import (ControlAction,)
from dss.delivery.FuseObj import (FuseObj,)
from dss.delivery.impl.FuseImpl import (FuseImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class FuseObjImpl(ControlElemImpl, FuseObj):
    fuseCurve = None
    ratedCurrent = None
    delayTime = None
    monitoredElementName = None
    monitoredElementTerminal = None
    monitoredElement = None
    # Handle to control queue actions
    hAction = [None] * Fuse.FUSEMAXDIM
    # 0 = open 1 = close
    presentState = [None] * Fuse.FUSEMAXDIM
    readyToBlow = [None] * Fuse.FUSEMAXDIM
    # Offset for monitored terminal
    condOffset = None
    cBuffer = None

    def __init__(self, parClass, fuseName):
        super(FuseObjImpl, self)(parClass)
        self.setName(fuseName.toLowerCase())
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
        self.fuseCurve = FuseImpl.getTccCurve('tlink')
        self.ratedCurrent = 1.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getNPhases())):
                break
            self.presentState[i] = ControlAction.CLOSE
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getNPhases())):
                break
            self.readyToBlow[i] = False
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getNPhases())):
                break
            self.hAction[i] = 0
        self.cBuffer = None
        # complex buffer
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
            if self.getNPhases() > Fuse.FUSEMAXDIM:
                DSSGlobals.doSimpleMsg('Warning: Fuse ' + self.getName() + ': Number of phases > max fuse dimension.', 404)
            if self.monitoredElementTerminal > self.monitoredElement.getNTerms():
                DSSGlobals.doErrorMsg('Fuse: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Re-specify terminal no.', 404)
            else:
                # sets name of i-th terminal's connected bus in fuse's bus list
                self.setBus(1, self.monitoredElement.getBus(self.monitoredElementTerminal))
                # TODO Check zero based indexing
                # allocate a buffer big enough to hold everything from the monitored element
                self.cBuffer = Utilities.resizeArray(self.cBuffer, self.monitoredElement.getYorder())
                self.condOffset = (self.monitoredElementTerminal - 1) * self.monitoredElement.getNConds()
                # for speedy sampling
        # Check for existence of controlled element
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # both CktElement and monitored element must already exist
            self.setControlledBus(DSSGlobals.activeCircuit.getCktElements().get(devIndex))
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # make the 1st terminal active
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if (
                    not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getControlledElement().getNPhases()))
                ):
                    break
                if self.getControlledElement().getConductorClosed(i):
                    # check state of i-th phase of active terminal
                    self.presentState[i] = ControlAction.CLOSE
                else:
                    self.presentState[i] = ControlAction.OPEN
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.getControlledElement().getNPhases()):
                    break
                self.hAction[i] = 0
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if (
                    not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getControlledElement().getNPhases()))
                ):
                    break
                self.readyToBlow[i] = False
        else:
            self.setControlledElement(None)
            # element not found
            DSSGlobals.doErrorMsg('Fuse: \"' + self.getName() + '\"', 'CktElement Element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 405)

    def calcYPrim(self):
        """Always zero for a fuse."""
        # leave YPrims as null and they will be ignored
        pass

    def getCurrents(self, curr):
        """Get present value of terminal current."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNConds()):
                break
            curr[i] = Complex.ZERO

    def getInjCurrents(self, curr):
        """Returns injection currents."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNConds()):
                break
            curr[i] = Complex.ZERO

    def doPendingAction(self, phs, proxyHdl):
        """Do the action that is pending from last sample.

        Theoretically, there shouldn't be anything on the queue unless we have to do something.

        Only legal action is to open one phase.
        """
        if phs <= Fuse.FUSEMAXDIM:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal of CktElement to terminal 1
            _0 = self.presentState[phs]
            _1 = False
            while True:
                if _0 == self.CLOSE:
                    _1 = True
                    if self.readyToBlow[phs]:
                        # ignore if we became disarmed in meantime
                        self.getControlledElement().setConductorClosed(phs, False)
                        # open all phases of active terminal
                        Utilities.appendToEventLog('Fuse.' + self.getName(), 'Phase ' + String.valueOf.valueOf(phs) + ' Blown')
                        self.hAction[phs] = 0
                    break
                if True:
                    _1 = True
                    break
                break

    def interpretFuseAction(self, action):
        if self.getControlElement() is not None:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal
            _0 = action.toLowerCase()[0]
            _1 = False
            while True:
                if _0 == 'o':
                    _1 = True
                    self.getControlledElement().setConductorClosed(0, False)
                    # open all phases of active terminal   TODO Check zero based indexing
                    break
                if (_1 is True) or (_0 == 't'):
                    _1 = True
                    self.getControlledElement().setConductorClosed(0, False)
                    break
                if (_1 is True) or (_0 == 'c'):
                    _1 = True
                    self.getControlledElement().setConductorClosed(0, True)
                    # close all phases of active terminal
                    break
                break

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
        self.monitoredElement.getCurrents(self.cBuffer)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if (
                not (i < self.Math.min(Fuse.FUSEMAXDIM, self.monitoredElement.getNPhases()))
            ):
                break
            if self.getControlledElement().getConductorClosed(i):
                # check state of phases of active terminal
                self.presentState[i] = ControlAction.CLOSE
            else:
                self.presentState[i] = ControlAction.OPEN
            if self.presentState[i] == ControlAction.CLOSE:
                tripTime = -1.0
                # Check phase trip, if any
                if self.fuseCurve is not None:
                    CMag = self.cBuffer[i].abs()
                    tripTime = self.fuseCurve.getTCCTime(CMag / self.ratedCurrent)
                if tripTime > 0.0:
                    if not self.readyToBlow[i]:
                        ckt = DSSGlobals.activeCircuit
                        # then arm for an open operation
                        self.hAction[i] = ckt.getControlQueue().push(ckt.getSolution().getIntHour(), ckt.getSolution().getDynaVars().t + tripTime + self.delayTime, i, 0, self)
                        self.readyToBlow[i] = True
                elif self.readyToBlow[i]:
                    # current has dropped below pickup and it hasn't blown yet
                    DSSGlobals.activeCircuit.getControlQueue().delete(self.hAction[i])
                    # delete the fuse blow action
                    self.readyToBlow[i] = False

    def dumpProperties(self, f, complete):
        super(FuseObjImpl, self).dumpProperties(f, complete)
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
        return super(FuseObjImpl, self).getPropertyValue(index)

    def reset(self):
        """Reset to initial defined state."""
        if self.getControlledElement() is not None:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if (
                    not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getControlledElement().getNPhases()))
                ):
                    break
                self.presentState[i] = ControlAction.CLOSE
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if (
                    not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getControlledElement().getNPhases()))
                ):
                    break
                self.readyToBlow[i] = False
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if (
                    not (i < self.Math.min(Fuse.FUSEMAXDIM, self.getControlledElement().getNPhases()))
                ):
                    break
                self.hAction[i] = 0
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal
            self.getControlledElement().setConductorClosed(0, True)
            # close all phases of active terminal

    def initPropertyValues(self, arrayOffset):
        # FIXME: Private members in OpenDSS
        self.propertyValue[1] = ''
        # "element";
        self.propertyValue[2] = '1'
        # "terminal";
        self.propertyValue[3] = ''
        self.propertyValue[4] = '1'
        # "terminal";
        self.propertyValue[5] = 'Tlink'
        self.propertyValue[6] = '1.0'
        self.propertyValue[7] = '0'
        self.propertyValue[8] = ''
        super(FuseObjImpl, self).initPropertyValues(Fuse.NumPropsThisClass)

    def getFuseCurve(self):
        return self.fuseCurve

    def setFuseCurve(self, curve):
        self.fuseCurve = curve

    def getRatedCurrent(self):
        return self.ratedCurrent

    def setRatedCurrent(self, current):
        self.ratedCurrent = current

    def getDelayTime(self):
        return self.delayTime

    def setDelayTime(self, time):
        self.delayTime = time

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

    def getHAction(self):
        return self.hAction

    def setHAction(self, action):
        self.hAction = action

    def getPresentState(self):
        return self.presentState

    def setPresentState(self, state):
        self.presentState = state

    def getReadyToBlow(self):
        return self.readyToBlow

    def setReadyToBlow(self, ready):
        self.readyToBlow = ready

    def getCondOffset(self):
        return self.condOffset

    def setCondOffset(self, offset):
        self.condOffset = offset

    def getCBuffer(self):
        return self.cBuffer

    def setCBuffer(self, buffer):
        self.cBuffer = buffer
