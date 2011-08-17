from dss.control.Relay import (Relay,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.control.impl.RelayObjImpl import (RelayObjImpl,)
from dss.parser.impl.Parser import (Parser,)


class RelayImpl(ControlClassImpl, Relay):
    activeRelayObj = None
    TCC_CurveClass = None

    def __init__(self):
        super(RelayImpl, self)()
        self.className = 'Relay'
        self.classType = self.classType + DSSClassDefs.RELAY_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)
        self.TCC_CurveClass = DSSClassDefs.getDSSClass('TCC_Curve')

    def defineProperties(self):
        self.numProperties = Relay.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.addProperty('MonitoredObj', 0, 'Full object name of the circuit element, typically a line, transformer, load, or generator, ' + 'to which the relay's PT and/or CT are connected.' + ' This is the \"monitored\" element. ' + 'There is no default; must be specified.')
        self.addProperty('MonitoredTerm', 1, 'Number of the terminal of the circuit element to which the Relay is connected. ' + '1 or 2, typically.  Default is 1.')
        self.addProperty('SwitchedObj', 2, 'Name of circuit element switch that the Relay controls. ' + 'Specify the full object name.' + 'Defaults to the same as the Monitored element. ' + 'This is the \"controlled\" element.')
        self.addProperty('SwitchedTerm', 3, 'Number of the terminal of the controlled element in which the switch is controlled by the Relay. ' + '1 or 2, typically.  Default is 1.')
        self.addProperty('type', 4, 'One of a legal relay type:' + DSSGlobals.CRLF + 'Current' + DSSGlobals.CRLF + 'Voltage' + DSSGlobals.CRLF + 'Reversepower' + DSSGlobals.CRLF + '46 (neg seq current)' + DSSGlobals.CRLF + '47 (neg seq voltage)' + DSSGlobals.CRLF + 'Generic (generic over/under relay)' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Default is overcurrent relay (Current) ' + 'Specify the curve and pickup settings appropriate for each type. ' + 'Generic relays monitor PC Element Control variables and trip on out of over/under range in definite time.')
        self.addProperty('Phasecurve', 5, 'Name of the TCC Curve object that determines the phase trip.  ' + 'Must have been previously defined as a TCC_Curve object.' + ' Default is none (ignored). ' + 'For overcurrent relay, multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.')
        self.addProperty('Groundcurve', 6, 'Name of the TCC Curve object that determines the ground trip.  Must have been previously defined as a TCC_Curve object.' + ' Default is none (ignored).' + 'For overcurrent relay, multiplying the current values in the curve by the \"groundtrip\" valuw gives the actual current.')
        self.addProperty('PhaseTrip', 7, 'Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.')
        self.addProperty('GroundTrip', 8, 'Multiplier or actual ground amps (3I0) for the ground TCC curve.  Defaults to 1.0.')
        self.addProperty('TDPhase', 27, 'Time dial for Phase trip curve. Multiplier on time axis of specified curve. Default=1.0.')
        self.addProperty('TDGround', 28, 'Time dial for Ground trip curve. Multiplier on time axis of specified curve. Default=1.0.')
        self.addProperty('PhaseInst', 9, 'Actual  amps (Current relay) or kW (reverse power relay) for instantaneous phase trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip. ' + 'Use this value for specifying the Reverse Power threshold (kW) for reverse power relays.')
        self.addProperty('GroundInst', 10, 'Actual  amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time.Default is 0.0, which signifies no inst trip.')
        self.addProperty('Reset', 11, 'Reset time in sec for relay.  Default is 15. If ')
        self.addProperty('Shots', 12, 'Number of shots to lockout.  Default is 4. This is one more than the number of reclose intervals.')
        self.addProperty('RecloseIntervals', 13, 'Array of reclose intervals. If none, specify \"NONE\". Default for overcurrent relay is (0.5, 2.0, 2.0) seconds. ' + 'Default for a voltage relay is (5.0). In a voltage relay, this is  seconds after restoration of ' + 'voltage that the reclose occurs. ' + 'Reverse power relay is one shot to lockout, ' + 'so this is ignored.  A locked out relay must be closed manually (set action=close).')
        self.addProperty('Delay', 23, 'Trip time delay (sec) for definite time relays. Default is 0.0 for current and voltage relays.  If >0 then this value is used instead of curves. ' + ' Used exclusively by RevPower, 46 and 47 relays at this release. Defaults to 0.1 s for these relays.')
        self.addProperty('Overvoltcurve', 14, 'TCC Curve object to use for overvoltage relay.  Curve is assumed to be defined with per unit voltage values. ' + 'Voltage base should be defined for the relay. Default is none (ignored).')
        self.addProperty('Undervoltcurve', 15, 'TCC Curve object to use for undervoltage relay.  Curve is assumed to be defined with per unit voltage values. ' + 'Voltage base should be defined for the relay. Default is none (ignored).')
        self.addProperty('kvbase', 16, 'Voltage base (kV) for the relay. Specify line-line for 3 phase devices); line-neutral for 1-phase devices.  Relay assumes ' + 'the number of phases of the monitored element.  Default is 0.0, which results in assuming the voltage ' + 'values in the \"TCC\" curve are specified in actual line-to-neutral volts.')
        self.addProperty('47%Pickup', 24, 'Percent voltage pickup for 47 relay (Neg seq voltage). Default is 2. Specify also base voltage (kvbase) and delay time value.   ')
        self.addProperty('46BaseAmps', 22, 'Base current, Amps, for 46 relay (neg seq current).' + '  Used for establishing pickup and per unit I-squared-t.')
        self.addProperty('46%Pickup', 20, 'Percent pickup current for 46 relay (neg seq current).  Default is 20.0. ' + '  When current exceeds this value * BaseAmps, I-squared-t calc starts.')
        self.addProperty('46isqt', 21, 'Negative Sequence I-squared-t trip value for 46 relay (neg seq current).' + '  Default is 1 (trips in 1 sec for 1 per unit neg seq current).  Should be 1 to 99.')
        self.addProperty('Variable', 19, 'Name of variable in PC Elements being monitored.  Only applies to Generic relay.')
        self.addProperty('overtrip', 25, 'Trip setting (high value) for Generic relay variable.  Relay trips in definite time if value of variable exceeds this value.')
        self.addProperty('undertrip', 26, 'Trip setting (low value) for Generic relay variable.  Relay trips in definite time if value of variable is less than this value.')
        self.addProperty('Breakertime', 17, 'Fixed delay time (sec) added to relay time. Default is 0.0. Designed to represent breaker time or some other delay after a trip decision is made.' + 'Use Delay_Time property for setting a fixed trip time delay.' + 'Added to trip time of current and voltage relays. Could use in combination with inst trip value to obtain a definite time overcurrent relay.')
        self.addProperty('action', 18, '{Trip/Open | Close}  Action that overrides the relay control. Simulates manual control on breaker. ' + '\"Trip\" or \"Open\" causes the controlled element to open and lock out. ' + '\"Close\" causes the controlled element to close and the relay to reset to its first operation.')
        self.activeProperty = Relay.NumPropsThisClass - 1
        super(RelayImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(RelayObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def getTccCurve(self, curveName):
        result = self.TCC_CurveClass.find(curveName)
        if result is None:
            DSSGlobals.doSimpleMsg('TCC curve object: \"' + curveName + '\" not found.', 380)
        return result

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeRelayObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeRelayObj)
        result = 0
        ar = self.activeRelayObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                ar.setPropertyValue(self.propertyIdxMap[paramPointer], param)
            else:
                DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for relay \"' + ar.getName() + '\"', 381)
            if paramPointer >= 0:
                _0 = self.propertyIdxMap[paramPointer]
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + ar.getName() + '\"', 382)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        ar.setMonitoredElementName(param.toLowerCase())
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        ar.setMonitoredElementTerminal(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        ar.setElementName(param.toLowerCase())
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        ar.setElementTerminal(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        ar.interpretRelayType(param)
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        ar.setPhaseCurve(self.getTccCurve(param))
                        break
                    if (_1 is True) or (_0 == 6):
                        _1 = True
                        ar.setGroundCurve(self.getTccCurve(param))
                        break
                    if (_1 is True) or (_0 == 7):
                        _1 = True
                        ar.setPhaseTrip(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 8):
                        _1 = True
                        ar.setGroundTrip(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 9):
                        _1 = True
                        ar.setPhaseInst(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 10):
                        _1 = True
                        ar.setGroundInst(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 11):
                        _1 = True
                        ar.setResetTime(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 12):
                        _1 = True
                        ar.setNumReclose(parser.makeInteger() - 1)
                        # one less than number of shots
                        break
                    if (_1 is True) or (_0 == 13):
                        _1 = True
                        if param.equalsIgnoreCase('none'):
                            ar.setNumReclose(1)
                        else:
                            ar.setNumReclose(parser.parseAsVector(4, ar.getRecloseIntervals()))
                            # max of 4 allowed
                        break
                    if (_1 is True) or (_0 == 14):
                        _1 = True
                        ar.setOVCurve(self.getTccCurve(param))
                        break
                    if (_1 is True) or (_0 == 15):
                        _1 = True
                        ar.setUVCurve(self.getTccCurve(param))
                        break
                    if (_1 is True) or (_0 == 16):
                        _1 = True
                        ar.setKVBase(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 17):
                        _1 = True
                        ar.setBreakerTime(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 18):
                        _1 = True
                        ar.interpretRelayAction(param)
                        break
                    if (_1 is True) or (_0 == 19):
                        _1 = True
                        ar.setMonitorVariable(param.toLowerCase())
                        # for pc elements
                        break
                    if (_1 is True) or (_0 == 20):
                        _1 = True
                        ar.setPctPickup46(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 21):
                        _1 = True
                        ar.setIsqt46(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 22):
                        _1 = True
                        ar.setBaseAmps46(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 23):
                        _1 = True
                        ar.setDelayTime(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 24):
                        _1 = True
                        ar.setPctPickup47(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 25):
                        _1 = True
                        ar.setOverTrip(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 26):
                        _1 = True
                        ar.setUnderTrip(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 27):
                        _1 = True
                        ar.setTDPhase(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 28):
                        _1 = True
                        ar.setTDGround(parser.makeDouble())
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeRelayObj, paramPointer - Relay.NumPropsThisClass)
                        break
                    break
                # internal relay property commands
            if paramPointer >= 0:
                _2 = self.propertyIdxMap[paramPointer]
                _3 = False
                while True:
                    if _2 == 0:
                        _3 = True
                        ar.setElementName(ar.getMonitoredElementName())
                        break
                    if (_3 is True) or (_2 == 1):
                        _3 = True
                        ar.setElementTerminal(ar.getMonitoredElementTerminal())
                        break
                    if (_3 is True) or (_2 == 4):
                        _3 = True
                        _4 = param.toLowerCase()[0]
                        _5 = False
                        while True:
                            if _4 == 'c':
                                _5 = True
                                ar.setPropertyValue(13, '(0.5, 2.0, 2.0)')
                                break
                            if (_5 is True) or (_4 == 'v'):
                                _5 = True
                                ar.setPropertyValue(13, '(5.0)')
                                break
                            break
                        DSSGlobals.auxParser.setCmdString(ar.getPropertyValue(13))
                        paramName = DSSGlobals.auxParser.getNextParam()
                        ar.setNumReclose(DSSGlobals.auxParser.parseAsVector(4, ar.getRecloseIntervals()))
                        break
                    break
                # Default the controlled element to the monitored element
            paramName = parser.getNextParam()
            param = parser.makeString()
        ar.recalcElementData()
        return result

    def makeLike(self, relayName):
        result = 0
        # See if we can find this relay name in the present collection
        otherRelay = self.find(relayName)
        if otherRelay is not None:
            ar = self.activeRelayObj
            ar.setNPhases(otherRelay.getNPhases())
            ar.setNConds(otherRelay.getNConds())
            # force reallocation of terminal stuff
            ar.setElementName(otherRelay.getElementName())
            ar.setElementTerminal(otherRelay.getElementTerminal())
            ar.setControlledElement(otherRelay.getControlledElement())
            # target circuit element
            ar.setMonitoredElement(otherRelay.getMonitoredElement())
            # target circuit element
            ar.setMonitoredElementName(otherRelay.getMonitoredElementName())
            # target circuit element
            ar.setMonitoredElementTerminal(otherRelay.getMonitoredElementTerminal())
            # target circuit element
            ar.setPhaseCurve(otherRelay.getPhaseCurve())
            ar.setGroundCurve(otherRelay.getGroundCurve())
            ar.setOVCurve(otherRelay.getOVCurve())
            ar.setUVCurve(otherRelay.getUVCurve())
            ar.setPhaseTrip(otherRelay.getPhaseTrip())
            ar.setGroundTrip(otherRelay.getGroundTrip())
            ar.setTDPhase(otherRelay.getTDPhase())
            ar.setTDGround(otherRelay.getTDGround())
            ar.setPhaseInst(otherRelay.getPhaseInst())
            ar.setGroundInst(otherRelay.getGroundInst())
            ar.setResetTime(otherRelay.getResetTime())
            ar.setNumReclose(otherRelay.getNumReclose())
            ar.setDelayTime(otherRelay.getDelayTime())
            ar.setBreakerTime(otherRelay.getBreakerTime())
            ar.setRecloseIntervals(Utilities.resizeArray(ar.getRecloseIntervals(), 4))
            # always make a max of 4
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ar.getNumReclose()):
                    break
                ar.getRecloseIntervals()[i] = otherRelay.getRecloseIntervals()[i]
            ar.setKVBase(otherRelay.getKVBase())
            ar.setLockedOut(otherRelay.isLockedOut())
            ar.setControlType(otherRelay.getControlType())
            ar.setPresentState(otherRelay.getPresentState())
            ar.setCondOffset(otherRelay.getCondOffset())
            # 46 relay neg seq current
            ar.setPickupAmps46(otherRelay.getPickupAmps46())
            ar.setPctPickup46(otherRelay.getPctPickup46())
            ar.setBaseAmps46(otherRelay.getBaseAmps46())
            ar.setIsqt46(otherRelay.getIsqt46())
            # 47 relay
            ar.setPickupVolts47(otherRelay.getPickupVolts47())
            ar.setPctPickup47(otherRelay.getPctPickup47())
            # Generic relay
            ar.setMonitorVariable(otherRelay.getMonitorVariable())
            ar.setOverTrip(otherRelay.getOverTrip())
            ar.setUnderTrip(otherRelay.getUnderTrip())
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < ar.getParentClass().getNumProperties()):
                    break
                ar.setPropertyValue(i, otherRelay.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in Relay makeLike: \"' + relayName + '\" not found.', 383)
        return result
