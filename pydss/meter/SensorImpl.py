from dss.meter.impl.SensorObjImpl import (SensorObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.meter.Sensor import (Sensor,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.meter.impl.MeterClassImpl import (MeterClassImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class SensorImpl(MeterClassImpl, Sensor):
    activeSensorObj = None

    def __init__(self):
        super(SensorImpl, self)()
        self.className = 'Sensor'
        self.classType = self.classType + DSSClassDefs.SENSOR_ELEMENT
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'element'
        self.propertyName[1] = 'terminal'
        self.propertyName[2] = 'kvbase'
        self.propertyName[3] = 'clear'
        self.propertyName[4] = 'kVs'
        self.propertyName[5] = 'currents'
        self.propertyName[6] = 'kWs'
        self.propertyName[7] = 'kvars'
        self.propertyName[8] = 'conn'
        # sensor connection
        self.propertyName[9] = 'Deltadirection'
        # +/- 1
        self.propertyName[10] = '%Error'
        # %Error of sensor
        self.propertyName[11] = 'Weight'
        # for WLS calc
        self.propertyName[12] = 'action'
        self.propertyHelp[0] = 'Name (Full Object name) of element to which the Sensor is connected.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the Sensor is connected. ' + '1 or 2, typically. Default is 1.'
        self.propertyHelp[2] = 'Voltage base for the sensor, in kV. If connected to a 2- or 3-phase terminal, ' + DSSGlobals.CRLF + 'specify L-L voltage. For 1-phase devices specify L-N or actual 1-phase voltage. ' + 'Like many other DSS devices, default is 12.47kV.'
        self.propertyHelp[3] = '{ Yes | No }. Clear=Yes clears sensor values. Should be issued before putting in a new set of measurements.'
        self.propertyHelp[4] = 'Array of Voltages (kV) measured by the voltage sensor. For Delta-connected ' + 'sensors, Line-Line voltages are expected. For Wye, Line-Neutral are expected.'
        self.propertyHelp[5] = 'Array of Currents (amps) measured by the current sensor. Specify this or power quantities; not both.'
        self.propertyHelp[6] = 'Array of Active power (kW) measurements at the sensor. Is converted into Currents along with q=[...]' + DSSGlobals.CRLF + 'Will override any i=[...] specification.'
        self.propertyHelp[7] = 'Array of Reactive power (kvar) measurements at the sensor. Is converted into Currents along with p=[...]'
        self.propertyHelp[8] = 'Voltage sensor Connection: { wye | delta | LN | LL }.  Default is wye. Applies to voltage measurement only. ' + DSSGlobals.CRLF + 'Currents are always assumed to be line currents.' + DSSGlobals.CRLF + 'If wye or LN, voltage is assumed measured line-neutral; otherwise, line-line.'
        self.propertyHelp[9] = '{1 or -1}  Default is 1:  1-2, 2-3, 3-1.  For reverse rotation, enter -1. Any positive or negative entry will suffice.'
        self.propertyHelp[10] = 'Assumed percent error in the measurement. Default is 1.'
        self.propertyHelp[11] = 'Weighting factor: Default is 1.'
        self.propertyHelp[12] = 'NOT IMPLEMENTED.Action options: ' + DSSGlobals.CRLF + 'SQERROR: Show square error of the present value of the monitored terminal  ' + DSSGlobals.CRLF + 'quantity vs the sensor value. Actual values - convert to per unit in calling program.  ' + DSSGlobals.CRLF + 'Value reported in result window/result variable.'
        self.activeProperty = Sensor.NumPropsThisClass - 1
        super(SensorImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(SensorObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeSensorObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeSensorObj)
        result = 0
        doRecalcElementData = False
        as_ = self.activeSensorObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                as_.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + as_.getName() + '\"', 661)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    as_.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    as_.setMeteredTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    as_.setKVBase(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    as_.setClearSpecified(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    parser.parseAsVector(as_.getNPhases(), as_.getSensorVoltage())
                    # inits to zero
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    parser.parseAsVector(as_.getNPhases(), as_.getSensorCurrent())
                    # inits to zero
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    parser.parseAsVector(as_.getNPhases(), as_.getSensorKW())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    parser.parseAsVector(as_.getNPhases(), as_.getSensorKVAr())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    as_.setConn(Utilities.interpretConnection(param))
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    as_.setDeltaDirection(as_.limitToPlusMinusOne(parser.makeInteger()))
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    as_.setPctError(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    as_.setWeight(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    as_.setAction(param)
                    # put sq error in global result
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeSensorObj, paramPointer - Sensor.NumPropsThisClass)
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    doRecalcElementData = True
                    as_.setMeteredElementChanged(True)
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    doRecalcElementData = True
                    as_.setMeteredElementChanged(True)
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    doRecalcElementData = True
                    break
                    # Do not recalc element data for setting of sensor quantities
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    if as_.isClearSpecified():
                        as_.clearSensor()
                    break
                if (_3 is True) or (_2 == 4):
                    _3 = True
                    as_.setVSpecified(True)
                    break
                if (_3 is True) or (_2 == 5):
                    _3 = True
                    as_.setISpecified(True)
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    as_.setPSpecified(True)
                    break
                if (_3 is True) or (_2 == 7):
                    _3 = True
                    as_.setQSpecified(True)
                    break
                if (_3 is True) or (_2 == 8):
                    _3 = True
                    doRecalcElementData = True
                    break
                if (_3 is True) or (_2 == 9):
                    _3 = True
                    doRecalcElementData = True
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        if doRecalcElementData:
            as_.recalcElementData()
        return result

    def resetAll(self):
        """Force all sensors in the circuit to reset."""
        for sensor in DSSGlobals.activeCircuit.getSensors():
            sensor.resetIt()

    def sampleAll(self):
        """Force all sensors to take a sample."""
        for sensor in DSSGlobals.activeCircuit.getSensors():
            sensor.takeSample()

    def saveAll(self):
        """Force all sensors to save their buffers to disk."""
        # for (SensorObj pSensor : DSSGlobals.activeCircuit.getSensors())
        # pSensor.save();
        pass

    def setHasSensorFlag(self):
        """Set the hasSensorObj flag for all ckt element."""
        ckt = DSSGlobals.activeCircuit
        # Initialize all to false
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(ckt.getPDElements())):
                break
            cktElem = ckt.getPDElements().get(i)
            cktElem.setHasSensorObj(False)
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < len(ckt.getPCElements())):
                break
            cktElem = ckt.getPCElements().get(i)
            cktElem.setHasSensorObj(False)
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < len(ckt.getSensors())):
                break
            thisSensor = ckt.getSensors().get(i)
            if thisSensor.getMeteredElement() is not None:
                thisSensor.getMeteredElement().setHasSensorObj(True)
                if isinstance(thisSensor.getMeteredElement(), PCElement):
                    thisSensor.getMeteredElement().setSensorObj(thisSensor)
                else:
                    thisSensor.getMeteredElement().setSensorObj(thisSensor)

    def makeLike(self, sensorName):
        result = 0
        # See if we can find this sensor name in the present collection
        otherSensor = self.find(sensorName)
        if otherSensor is not None:
            as_ = self.activeSensorObj
            as_.setNPhases(otherSensor.getNPhases())
            as_.setNConds(otherSensor.getNConds())
            # force reallocation of terminal stuff
            as_.setElementName(otherSensor.getElementName())
            as_.setMeteredElement(otherSensor.getMeteredElement())
            # target circuit element
            as_.setMeteredTerminal(otherSensor.getMeteredTerminal())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < as_.getParentClass().getNumProperties()):
                    break
                as_.setPropertyValue(i, otherSensor.getPropertyValue(i))
            as_.setBaseFrequency(otherSensor.getBaseFrequency())
        else:
            DSSGlobals.doSimpleMsg('Error in Sensor makeLike: \"' + sensorName + '\" not found.', 662)
        return result

    def init(self, handle):
        Result = 0
        if handle >= 0:
            sensor = self.elementList.get(handle)
            sensor.resetIt()
        else:
            # Do 'em all
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(self.elementList)):
                    break
                sensor = self.elementList.get(i)
                sensor.resetIt()
        return Result
