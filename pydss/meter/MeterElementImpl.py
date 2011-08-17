from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.meter.MeterElement import (MeterElement,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.DSSCktElement import (DSSCktElement,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class MeterElementImpl(DSSCktElement, MeterElement):
    elementName = None
    meteredElement = None
    meteredTerminal = None
    meteredElementChanged = None
    sensorCurrent = None
    sensorVoltage = None
    phsAllocationFactor = None
    calculatedCurrent = None
    calculatedVoltage = None
    avgAllocFactor = None

    def __init__(self, parClass):
        super(MeterElementImpl, self)(parClass)
        self.objType = DSSClassDefs.METER_ELEMENT
        self.elementName = ''
        self.meteredElement = None
        self.meteredTerminal = 1
        self.sensorCurrent = None
        self.sensorVoltage = None
        self.phsAllocationFactor = None
        self.calculatedCurrent = None
        self.calculatedVoltage = None

    def allocateSensorArrays(self):
        if self.meteredElement is not None:
            self.calculatedCurrent = Utilities.resizeArray(self.calculatedCurrent, self.meteredElement.getYorder())
        if self.meteredElement is not None:
            self.calculatedVoltage = Utilities.resizeArray(self.calculatedVoltage, self.meteredElement.getYorder())
        self.sensorCurrent = Utilities.resizeArray(self.sensorCurrent, self.nPhases)
        self.sensorVoltage = Utilities.resizeArray(self.sensorVoltage, self.nPhases)
        self.phsAllocationFactor = Utilities.resizeArray(self.phsAllocationFactor, self.nPhases)

    def calcAllocationFactors(self):
        self.meteredElement.getCurrents(self.calculatedCurrent)
        # the phase allocation factor is the amount that the load must change to match the measured peak
        iOffset = (self.meteredTerminal - 1) * self.meteredElement.getNConds()
        self.avgAllocFactor = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            mag = self.calculatedCurrent[i + iOffset].abs()
            if mag > 0.0:
                self.phsAllocationFactor[i] = self.sensorCurrent[i] / mag
            else:
                self.phsAllocationFactor[i] = 1.0
                # no change
            self.avgAllocFactor = self.avgAllocFactor + self.phsAllocationFactor[i]
        self.avgAllocFactor = self.avgAllocFactor / self.nPhases
        # factor for 2- and 3-phase loads

    def takeSample(self):
        """Sample control quantities and set action times in control queue."""
        DSSGlobals.doSimpleMsg('Programming error: Reached base MeterElement class for takeSample.' + DSSGlobals.CRLF + 'Device: ' + self.getName(), 723)

    def getElementName(self):
        return self.elementName

    def setElementName(self, name):
        self.elementName = name

    def getMeteredElement(self):
        return self.meteredElement

    def setMeteredElement(self, element):
        self.meteredElement = element

    def getMeteredTerminal(self):
        return self.meteredTerminal

    def setMeteredTerminal(self, terminal):
        self.meteredTerminal = terminal

    def isMeteredElementChanged(self):
        return self.meteredElementChanged

    def setMeteredElementChanged(self, changed):
        self.meteredElementChanged = changed

    def getSensorCurrent(self):
        return self.sensorCurrent

    def setSensorCurrent(self, current):
        self.sensorCurrent = current

    def getSensorVoltage(self):
        return self.sensorVoltage

    def setSensorVoltage(self, voltage):
        self.sensorVoltage = voltage

    def getPhsAllocationFactor(self):
        return self.phsAllocationFactor

    def setPhsAllocationFactor(self, factor):
        self.phsAllocationFactor = factor

    def getCalculatedCurrent(self):
        return self.calculatedCurrent

    def setCalculatedCurrent(self, current):
        self.calculatedCurrent = current

    def getCalculatedVoltage(self):
        return self.calculatedVoltage

    def setCalculatedVoltage(self, voltage):
        self.calculatedVoltage = voltage

    def getAvgAllocFactor(self):
        return self.avgAllocFactor

    def setAvgAllocFactor(self, factor):
        self.avgAllocFactor = factor
