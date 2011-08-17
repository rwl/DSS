from dss.common.impl.Utilities import (Utilities,)
from dss.control.impl.RecloserObjImpl import (RecloserObjImpl,)
from dss.control.Recloser import (Recloser,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)


class RecloserImpl(ControlClassImpl, Recloser):
    activeRecloserObj = None
    TCC_CurveClass = DSSClassDefs.getDSSClass('TCC_Curve')

    def __init__(self):
        super(RecloserImpl, self)()
        self.className = 'Recloser'
        self.classType = self.classType + DSSClassDefs.RECLOSER_CONTROL
        self.defineProperties()
        Commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, Commands, 0, self.numProperties)
        self.commandList = CommandListImpl(Commands)
        self.commandList.setAbbrevAllowed(True)

    @classmethod
    def getTCC_Curve(cls, curveName):
        result = cls.TCC_CurveClass.find(curveName)
        if result is None:
            DSSGlobals.doSimpleMsg('TCC curve object: \"' + curveName + '\" not found.', 388)
        return result

    def defineProperties(self):
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'MonitoredObj'
        self.propertyName[1] = 'MonitoredTerm'
        self.propertyName[2] = 'SwitchedObj'
        self.propertyName[3] = 'SwitchedTerm'
        self.propertyName[4] = 'NumFast'
        self.propertyName[5] = 'PhaseFast'
        self.propertyName[6] = 'PhaseDelayed'
        self.propertyName[7] = 'GroundFast'
        self.propertyName[8] = 'GroundDelayed'
        self.propertyName[9] = 'PhaseTrip'
        self.propertyName[10] = 'GroundTrip'
        self.propertyName[11] = 'PhaseInst'
        self.propertyName[12] = 'GroundInst'
        self.propertyName[13] = 'Reset'
        self.propertyName[14] = 'Shots'
        self.propertyName[15] = 'RecloseIntervals'
        self.propertyName[16] = 'Delay'
        self.propertyName[17] = 'Action'
        self.propertyName[18] = 'TDPhFast'
        self.propertyName[19] = 'TDGrFast'
        self.propertyName[20] = 'TDPhDelayed'
        self.propertyName[21] = 'TDGrDelayed'
        self.propertyHelp[0] = 'Full object name of the circuit element, typically a line, transformer, load, or generator, ' + 'to which the Recloser's PT and/or CT are connected.' + ' This is the \"monitored\" element. ' + 'There is no default; must be specified.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the Recloser is connected. ' + '1 or 2, typically.  Default is 1.'
        self.propertyHelp[2] = 'Name of circuit element switch that the Recloser controls. ' + 'Specify the full object name.' + 'Defaults to the same as the Monitored element. ' + 'This is the \"controlled\" element.'
        self.propertyHelp[3] = 'Number of the terminal of the controlled element in which the switch is controlled by the Recloser. ' + '1 or 2, typically.  Default is 1.'
        self.propertyHelp[4] = 'Number of Fast (fuse saving) operations.  Default is 1. (See \"Shots\")'
        self.propertyHelp[5] = 'Name of the TCC Curve object that determines the Phase Fast trip.  Must have been previously defined as a TCC_Curve object.' + ' Default is \"A\". ' + 'Multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.'
        self.propertyHelp[6] = 'Name of the TCC Curve object that determines the Phase Delayed trip.  Must have been previously defined as a TCC_Curve object.' + ' Default is \"D\".' + 'Multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.'
        self.propertyHelp[7] = 'Name of the TCC Curve object that determines the Ground Fast trip.  Must have been previously defined as a TCC_Curve object.' + ' Default is none (ignored). ' + 'Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.'
        self.propertyHelp[8] = 'Name of the TCC Curve object that determines the Ground Delayed trip.  Must have been previously defined as a TCC_Curve object.' + ' Default is none (ignored).' + 'Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.'
        self.propertyHelp[9] = 'Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.'
        self.propertyHelp[10] = 'Multiplier or actual ground amps (3I0) for the ground TCC curve.  Defaults to 1.0.'
        self.propertyHelp[11] = 'Actual amps for instantaneous phase trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip. '
        self.propertyHelp[12] = 'Actual amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time.Default is 0.0, which signifies no inst trip.'
        self.propertyHelp[13] = 'Reset time in sec for Recloser.  Default is 15. '
        self.propertyHelp[14] = 'Total Number of fast and delayed shots to lockout.  Default is 4. This is one more than the number of reclose intervals.'
        self.propertyHelp[15] = 'Array of reclose intervals.  Default for Recloser is (0.5, 2.0, 2.0) seconds. ' + 'A locked out Recloser must be closed manually (action=close).'
        self.propertyHelp[16] = 'Fixed delay time (sec) added to Recloser trip time. Default is 0.0. Used to represent breaker time or any other delay.'
        self.propertyHelp[17] = '{Trip/Open | Close}  Action that overrides the Recloser control. Simulates manual control on recloser ' + '\"Trip\" or \"Open\" causes the controlled element to open and lock out. ' + '\"Close\" causes the controlled element to close and the Recloser to reset to its first operation.'
        self.propertyHelp[18] = 'Time dial for Phase Fast trip curve. Multiplier on time axis of specified curve. Default=1.0.'
        self.propertyHelp[19] = 'Time dial for Ground Fast trip curve. Multiplier on time axis of specified curve. Default=1.0.'
        self.propertyHelp[20] = 'Time dial for Phase Delayed trip curve. Multiplier on time axis of specified curve. Default=1.0.'
        self.propertyHelp[21] = 'Time dial for Ground Delayed trip curve. Multiplier on time axis of specified curve. Default=1.0.'
        self.activeProperty = Recloser.NumPropsThisClass - 1
        super(RecloserImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(RecloserObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeRecloserObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeRecloserObj)
        result = 0
        ar = self.activeRecloserObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                ar.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + ar.getName() + '\"', 390)
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
                    ar.setNumFast(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    ar.setPhaseFast(self.getTCC_Curve(param))
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    ar.setPhaseDelayed(self.getTCC_Curve(param))
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    ar.setGroundFast(self.getTCC_Curve(param))
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    ar.setGroundDelayed(self.getTCC_Curve(param))
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    ar.setPhaseTrip(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    ar.setGroundTrip(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    ar.setPhaseInst(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    ar.setGroundInst(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    ar.setResetTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    ar.setNumReclose(parser.makeInteger() - 1)
                    # one less than number of shots
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    ar.setNumReclose(parser.parseAsVector(4, ar.getRecloseIntervals()))
                    # max of 4 allowed
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    ar.setDelayTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    ar.interpretRecloserAction(param)
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    ar.setTDPhFast(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    ar.setTDGrFast(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    ar.setTDPhDelayed(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    ar.setTDGrDelayed(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeRecloserObj, paramPointer - Recloser.NumPropsThisClass)
                    # TODO Check name-static member conflict
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 1:
                    _3 = True
                    ar.setElementName(ar.getMonitoredElementName())
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    ar.setElementTerminal(ar.getMonitoredElementTerminal())
                    break
                break
            # Default the controlled element to the monitored element
            paramName = parser.getNextParam()
            param = parser.makeString()
        ar.recalcElementData()
        return result

    def makeLike(self, recloserName):
        result = 0
        # See if we can find this Recloser name in the present collection
        otherRecloser = self.find(recloserName)
        if otherRecloser is not None:
            ar = self.activeRecloserObj
            ar.setNPhases(otherRecloser.getNPhases())
            ar.setNConds(otherRecloser.getNConds())
            # force reallocation of terminal stuff
            ar.setElementName(otherRecloser.getElementName())
            ar.setElementTerminal(otherRecloser.getElementTerminal())
            ar.setControlledElement(otherRecloser.getControlledElement())
            # pointer to target circuit element
            ar.setMonitoredElement(otherRecloser.getMonitoredElement())
            # pointer to target circuit element
            ar.setMonitoredElementName(otherRecloser.getMonitoredElementName())
            # pointer to target circuit element
            ar.setMonitoredElementTerminal(otherRecloser.getMonitoredElementTerminal())
            # pointer to target circuit element
            ar.setPhaseDelayed(otherRecloser.getPhaseDelayed())
            ar.setGroundDelayed(otherRecloser.getGroundDelayed())
            ar.setPhaseFast(otherRecloser.getPhaseFast())
            ar.setGroundFast(otherRecloser.getGroundFast())
            ar.setPhaseTrip(otherRecloser.getPhaseTrip())
            ar.setGroundTrip(otherRecloser.getGroundTrip())
            ar.setPhaseInst(otherRecloser.getPhaseInst())
            ar.setGroundInst(otherRecloser.getGroundInst())
            ar.setResetTime(otherRecloser.getResetTime())
            ar.setNumReclose(otherRecloser.getNumReclose())
            ar.setNumFast(otherRecloser.getNumFast())
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
                ar.getRecloseIntervals()[i] = otherRecloser.getRecloseIntervals()[i]
            ar.setLockedOut(otherRecloser.isLockedOut())
            ar.setPresentState(otherRecloser.getPresentState())
            ar.setCondOffset(otherRecloser.getCondOffset())
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < ar.getParentClass().getNumProperties()):
                    break
                ar.setPropertyValue(i, otherRecloser.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in Recloser makeLike: \"' + recloserName + '\" not found.', 391)
        return result
