from dss.control.impl.ControlElemImpl import (ControlElemImpl,)
from dss.control.SwtControl import (SwtControl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.control.SwtControlObj import (SwtControlObj,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.control.impl.ControlAction import (ControlAction,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class SwtControlObjImpl(ControlElemImpl, SwtControlObj):
    presentState = None
    locked = None

    def __init__(self, parClass, swtControlName):
        super(SwtControlObjImpl, self)(parClass)
        self.setName(swtControlName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.elementName = ''
        self.setControlledElement(None)
        self.elementTerminal = 1
        self.presentState = ControlAction.CLOSE
        self.locked = False
        self.timeDelay = 120.0
        self.initPropertyValues(0)

    def recalcElementData(self):
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            self.setControlledElement(DSSGlobals.activeCircuit.getCktElements().get(devIndex))
            self.setNPhases(self.getControlledElement().getNPhases())
            self.setNConds(self.nPhases)
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            if not self.locked:
                _0 = self.presentState
                _1 = False
                while True:
                    if _0 == self.OPEN:
                        _1 = True
                        self.getControlledElement().setConductorClosed(0, False)
                        break
                    if (_1 is True) or (_0 == self.CLOSE):
                        _1 = True
                        self.getControlledElement().setConductorClosed(0, True)
                        break
                    break
            # attach controller bus to the switch bus - no space allocated for monitored variables
            self.setBus(1, self.getControlledElement().getBus(self.elementTerminal))
        else:
            self.setControlledElement(None)
            # element not found
            DSSGlobals.doErrorMsg('SwtControl: \"' + self.getName() + '\"', 'CktElement Element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 387)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.getControlledElement() is not None:
            self.setNPhases(self.getControlledElement().getNPhases())
            self.setNConds(self.nPhases)
            self.setBus(1, self.getControlledElement().getBus(self.elementTerminal))
        super(SwtControlObjImpl, self).makePosSequence()

    def calcYPrim(self):
        # leave YPrims as nil
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
        if not self.locked:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            if (
                code == ControlAction.OPEN.code() and self.presentState == ControlAction.CLOSE
            ):
                self.getControlledElement().setConductorClosed(0, False)
                # open all phases of active terminal
                Utilities.appendToEventLog('SwtControl.' + self.getName(), 'Opened')
            if (
                code == ControlAction.CLOSE.code() and self.presentState == ControlAction.OPEN
            ):
                self.getControlledElement().setConductorClosed(0, True)
                # close all phases of active terminal
                Utilities.appendToEventLog('SwtControl.' + self.getName(), 'Closed')

    def interpretSwitchAction(self, action):
        if not self.locked:
            _0 = action.toLowerCase()[0]
            _1 = False
            while True:
                if _0 == 'o':
                    _1 = True
                    self.presentState = ControlAction.OPEN
                    break
                if (_1 is True) or (_0 == 'c'):
                    _1 = True
                    self.presentState = ControlAction.CLOSE
                    break
                break
            if self.getControlledElement() is not None:
                self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
                _2 = self.presentState
                _3 = False
                while True:
                    if _2 == self.OPEN:
                        _3 = True
                        self.getControlledElement().setConductorClosed(0, False)
                        break
                    if (_3 is True) or (_2 == self.CLOSE):
                        _3 = True
                        self.getControlledElement().setConductorClosed(0, True)
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

    def dumpProperties(self, f, complete):
        super(SwtControlObjImpl, self).dumpProperties(f, complete)
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
        return super(SwtControlObjImpl, self).getPropertyValue(index)

    def reset(self):
        """Reset to initial defined state."""
        self.presentState = ControlAction.CLOSE
        self.locked = False
        if self.getControlledElement() is not None:
            self.getControlledElement().setActiveTerminalIdx(self.elementTerminal)
            # set active terminal
            self.getControlledElement().setConductorClosed(0, True)
            # close all phases of active terminal

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '')
        # 'element';
        self.setPropertyValue(1, '1')
        # 'terminal';
        self.setPropertyValue(2, 'c')
        self.setPropertyValue(3, 'n')
        self.setPropertyValue(4, '120.0')
        super(SwtControlObjImpl, self).initPropertyValues(SwtControl.NumPropsThisClass)

    def getPresentState(self):
        return self.presentState

    def isLocked(self):
        # FIXME Private members in OpenDSS
        return self.locked

    def setPresentState(self, state):
        self.presentState = state

    def setLocked(self, value):
        self.locked = value
