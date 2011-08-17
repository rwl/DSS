from dss.control.ControlElem import (ControlElem,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.DSSCktElement import (DSSCktElement,)


class ControlElemImpl(DSSCktElement, ControlElem):
    controlledElement = None
    elementName = None
    elementTerminal = None
    # If different than terminal
    controlledBusName = None
    controlledBus = None
    monitorVariable = None
    monitorVarIndex = None
    timeDelay = None
    dblTraceParameter = None

    def __init__(self, parClass):
        super(ControlElemImpl, self)(parClass)
        self.objType = DSSClassDefs.CTRL_ELEMENT
        self.dblTraceParameter = 0.0
        self.monitorVariable = ''
        self.monitorVarIndex = 0
        self.controlledElement = None

    def doPendingAction(self, code, proxyHdl):
        """Do the action that is pending from last sample."""
        DSSGlobals.doSimpleMsg('Programming Error: Reached base class for doPendingAction.' + DSSGlobals.CRLF + 'Device: ' + self.getDSSClassName() + '.' + self.getName(), 460)

    def reset(self):
        DSSGlobals.doSimpleMsg('Programming Error: Reached base class for reset.' + DSSGlobals.CRLF + 'Device: ' + self.getDSSClassName() + '.' + self.getName(), 461)

    def sample(self):
        """Sample control quantities and set action times in control queue."""
        DSSGlobals.doSimpleMsg('Programming Error: Reached base class for sample.' + DSSGlobals.CRLF + 'Device: ' + self.getDSSClassName() + '.' + self.getName(), 462)

    def setControlledElement(self, value):
        try:
            if self.controlledElement is not None:
                self.controlledElement.setHasControl(False)
        finally:
            self.controlledElement = value
            if self.controlledElement is not None:
                self.controlledElement.setHasControl(True)
                self.controlledElement.setControlElement(self)
        # check for reassignment

    def getControlledElement(self):
        return self.controlledElement

    def getElementName(self):
        return self.elementName

    def setElementName(self, name):
        self.elementName = name

    def getElementTerminal(self):
        return self.elementTerminal

    def setElementTerminal(self, terminal):
        self.elementTerminal = terminal

    def getControlledBusName(self):
        return self.controlledBusName

    def setControlledBusName(self, name):
        self.controlledBusName = name

    def getControlledBus(self):
        return self.controlledBus

    def setControlledBus(self, bus):
        self.controlledBus = bus

    def getMonitorVariable(self):
        return self.monitorVariable

    def setMonitorVariable(self, variable):
        self.monitorVariable = variable

    def getMonitorVarIndex(self):
        return self.monitorVarIndex

    def setMonitorVarIndex(self, index):
        self.monitorVarIndex = index

    def getTimeDelay(self):
        return self.timeDelay

    def setTimeDelay(self, delay):
        self.timeDelay = delay

    def getDblTraceParameter(self):
        return self.dblTraceParameter

    def setDblTraceParameter(self, parameter):
        self.dblTraceParameter = parameter
