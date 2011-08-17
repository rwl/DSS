from dss.meter.SensorObj import (SensorObj,)
from dss.common.impl.Utilities import (Utilities,)
from dss.meter.Sensor import (Sensor,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.meter.impl.MeterElementImpl import (MeterElementImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class SensorObjImpl(MeterElementImpl, SensorObj):
    validSensor = None
    sensorKW = None
    sensorKVAr = None
    KVBase = None
    # value specified
    VBase = None
    # in volts
    conn = None
    VSpecified = None
    ISpecified = None
    PSpecified = None
    QSpecified = None
    clearSpecified = None
    deltaDirection = None
    pctError = None
    weight = None

    def __init__(self, parClass, sensorName):
        super(SensorObjImpl, self)(parClass)
        self.setName(sensorName.toLowerCase())
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(1)
        # this forces allocation of terminals and conductors in base class
        self.sensorKW = None
        self.sensorKVAr = None
        self.KVBase = 12.47
        # default 3-phase voltage
        self.weight = 1.0
        self.pctError = 1.0
        self.setConn(0)
        # wye
        self.clearSensor()
        self.objType = parClass.getDSSClassType()
        # SENSOR_ELEMENT;
        self.initPropertyValues(0)
        # recalcElementData();

    def recalcElementData(self):
        self.validSensor = False
        devIndex = Utilities.getCktElementIndex(self.elementName)
        if devIndex >= 0:
            # sensored element must already exist
            self.meteredElement = DSSGlobals.activeCircuit.getCktElements().get(devIndex)
            if self.meteredTerminal > self.meteredElement.getNTerms():
                # TODO Check zero based indexing
                DSSGlobals.doErrorMsg('Sensor: \"' + self.getName() + '\"', 'Terminal no. \"' + '\" does not exist.', 'Respecify terminal no.', 665)
            else:
                self.setNPhases(self.meteredElement.getNPhases())
                self.setNConds(self.meteredElement.getNConds())
                # sets name of i-th terminal's connected bus in Sensor's bus list
                # this value will be used to set the nodeRef array (see takeSample)
                self.setBus(1, self.meteredElement.getBus(self.meteredTerminal))
                self.clearSensor()
                self.validSensor = True
                self.allocateSensorObjArrays()
                self.zeroSensorArrays()
                self.recalcVbase()
        else:
            self.meteredElement = None
            # element not found
            DSSGlobals.doErrorMsg('Sensor: \"' + self.getName() + '\"', 'Circuit Element \"' + self.elementName + '\" not found.', ' Element must be defined previously.', 666)

    def makePosSequence(self):
        """Make a positive sequence model."""
        if self.meteredElement is not None:
            self.setBus(1, self.meteredElement.getBus(self.meteredTerminal))
            self.setNPhases(self.meteredElement.getNPhases())
            self.setNConds(self.meteredElement.getNConds())
            self.clearSensor()
            self.validSensor = True
            self.allocateSensorObjArrays()
            self.zeroSensorArrays()
            self.recalcVbase()
        super(SensorObjImpl, self).makePosSequence()

    def recalcVbase(self):
        _0 = self.conn
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                if self.nPhases == 1:
                    self.VBase = self.KVBase * 1000.0
                else:
                    self.VBase = (self.KVBase * 1000.0) / DSSGlobals.SQRT3
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                self.VBase = self.KVBase * 1000.0
                break
            break

    def calcYPrim(self):
        # leave YPrims as nil and they will be ignored
        pass

    def resetIt(self):
        self.clearSensor()

    def rotatePhases(self, j):
        """For delta connections or line-line voltages."""
        result = j + self.deltaDirection
        # make sure result is within limits
        if self.nPhases > 2:
            # assumes 2 phase delta is open delta
            if result > self.nPhases:
                result = 1
            if result < 1:
                result = self.nPhases
        elif result < 1:
            result = 3
            # for 2-phase delta, next phase will be 3rd phase
        return result

    def takeSample(self):
        if not (self.validSensor and self.isEnabled()):
            return
        self.meteredElement.getCurrents(self.calculatedCurrent)
        self.computeVTerminal()
        _0 = self.conn
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.calculatedVoltage[i] = self.VTerminal[i].subtract(self.VTerminal[self.rotatePhases(i)])
                break
            if True:
                _1 = True
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.calculatedVoltage[i] = self.VTerminal[i]
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

    def getWLSCurrentError(self):
        """Return the WLS Error for currents.
        Get square error and weight it.
        """
        result = 0.0
        # Convert P and Q specification to currents
        if self.PSpecified:
            # compute currents assuming vbase
            if self.QSpecified:
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    kVA = Complex(self.sensorKW[i], self.sensorKVAr[i]).abs()
                    self.sensorCurrent[i] = (kVA * 1000.0) / self.VBase
            else:
                # no Q just use P
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.sensorCurrent[i] = (self.sensorKW[i] * 1000.0) / self.VBase
            self.ISpecified = True
            # overrides current specification
        if self.ISpecified:
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                result = (result + self.Math.pow(self.calculatedCurrent[i].getReal(), 2) + self.Math.pow(self.calculatedCurrent[i].getImaginary(), 2)) - self.Math.pow(self.sensorCurrent[i], 2)
        result = result * self.weight
        return result

    def getWLSVoltageError(self):
        """Get square error and weight it."""
        result = 0.0
        if self.VSpecified:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                result = (result + self.Math.pow(self.calculatedVoltage[i].getReal(), 2) + self.Math.pow(self.calculatedVoltage[i].getImaginary(), 2)) - self.Math.pow(self.sensorVoltage[i], 2)
        result = result * self.weight
        return result

    def dumpProperties(self, f, complete):
        # FIXME Private method in OpenDSS
        super(SensorObjImpl, self).dumpProperties(f, complete)
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

    def clearSensor(self):
        self.VSpecified = False
        self.ISpecified = False
        self.PSpecified = False
        self.QSpecified = False
        self.clearSpecified = False

    def allocateSensorObjArrays(self):
        self.sensorKW = Utilities.resizeArray(self.sensorKW, self.nPhases)
        self.sensorKVAr = Utilities.resizeArray(self.sensorKVAr, self.nPhases)
        self.allocateSensorArrays()

    def zeroSensorArrays(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.sensorCurrent[i] = 0.0
            self.sensorVoltage[i] = 0.0
            self.sensorKW[i] = 0.0
            self.sensorKVAr[i] = 0.0

    def initPropertyValues(self, arrayOffset):
        # FIXME Private method in OpenDSS
        self.setPropertyValue(0, '')
        # 'element';
        self.setPropertyValue(1, '1')
        # 'terminal';
        self.setPropertyValue(2, '12.47')
        # 'kVBase';
        self.setPropertyValue(3, 'No')
        # must be set to yes to clear before setting quantities
        self.setPropertyValue(4, '[7.2, 7.2, 7.2]')
        self.setPropertyValue(5, '[0.0, 0.0, 0.0]')
        # currents
        self.setPropertyValue(6, '[0.0, 0.0, 0.0]')
        # P kW
        self.setPropertyValue(7, '[0.0, 0.0, 0.0]')
        # Q kvar
        self.setPropertyValue(8, 'wye')
        self.setPropertyValue(9, '1')
        self.setPropertyValue(10, '1')
        # %Error
        self.setPropertyValue(11, '1')
        # %Error
        self.setPropertyValue(12, '')
        # Action
        super(SensorObjImpl, self).initPropertyValues(Sensor.NumPropsThisClass)

    def limitToPlusMinusOne(self, i):
        if i >= 0:
            return 1
        else:
            return -1

    def save(self):
        """Saves present buffer to file."""
        pass

    def setConn(self, value):
        """Connection code."""
        self.conn = value
        self.recalcVbase()

    def setAction(self, value):
        pass

    def getConn(self):
        return self.conn

    def getSensorKW(self):
        return self.sensorKW

    def getSensorKVAr(self):
        return self.sensorKVAr

    def getKVBase(self):
        return self.KVBase

    def getDeltaDirection(self):
        return self.deltaDirection

    def getPctError(self):
        return self.pctError

    def setPctError(self, pct):
        self.pctError = pct

    def getWeight(self):
        return self.weight

    def setWeight(self, w):
        # FIXME Private members in OpenDSS
        self.weight = w

    def isValidSensor(self):
        return self.validSensor

    def setValidSensor(self, valid):
        self.validSensor = valid

    def getVBase(self):
        return self.VBase

    def setVBase(self, vbase):
        self.VBase = vbase

    def isVSpecified(self):
        return self.VSpecified

    def setVSpecified(self, specified):
        self.VSpecified = specified

    def isISpecified(self):
        return self.ISpecified

    def setISpecified(self, specified):
        self.ISpecified = specified

    def isPSpecified(self):
        return self.PSpecified

    def setPSpecified(self, specified):
        self.PSpecified = specified

    def isQSpecified(self):
        return self.QSpecified

    def setQSpecified(self, specified):
        self.QSpecified = specified

    def isClearSpecified(self):
        return self.clearSpecified

    def setClearSpecified(self, clear):
        self.clearSpecified = clear

    def setSensorKW(self, kw):
        self.sensorKW = kw

    def setSensorKVAr(self, kvar):
        self.sensorKVAr = kvar

    def setKVBase(self, base):
        self.KVBase = base

    def setDeltaDirection(self, direction):
        self.deltaDirection = direction
