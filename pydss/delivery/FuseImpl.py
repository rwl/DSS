from dss.delivery.Fuse import (Fuse,)
from dss.delivery.impl.FuseObjImpl import (FuseObjImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)


class FuseImpl(ControlClassImpl, Fuse):
    activeFuseObj = None
    TCC_CurveClass = None

    def __init__(self):
        super(FuseImpl, self)()
        self.className = 'Fuse'
        self.classType = self.getDSSClassType() + DSSClassDefs.FUSE_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)
        self.TCC_CurveClass = DSSClassDefs.getDSSClass('TCC_Curve')

    @classmethod
    def getTccCurve(cls, curveName):
        """General module function"""
        result = cls.TCC_CurveClass.find(curveName)
        if result is None:
            DSSGlobals.doSimpleMsg('TCC Curve object: \"' + curveName + '\" not found.', 401)
        return result

    def defineProperties(self):
        self.numProperties = Fuse.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'MonitoredObj'
        self.propertyName[1] = 'MonitoredTerm'
        self.propertyName[2] = 'SwitchedObj'
        self.propertyName[3] = 'SwitchedTerm'
        self.propertyName[4] = 'FuseCurve'
        self.propertyName[5] = 'RatedCurrent'
        self.propertyName[6] = 'Delay'
        self.propertyName[7] = 'Action'
        self.propertyHelp[0] = 'Full object name of the circuit element, typically a line, transformer, load, or generator, ' + 'to which the Fuse is connected.' + ' This is the \"monitored\" element. ' + 'There is no default; must be specified.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the Fuse is connected. ' + '1 or 2, typically.  Default is 1.'
        self.propertyHelp[2] = 'Name of circuit element switch that the Fuse controls. ' + 'Specify the full object name.' + 'Defaults to the same as the Monitored element. ' + 'This is the \"controlled\" element.'
        self.propertyHelp[3] = 'Number of the terminal of the controlled element in which the switch is controlled by the Fuse. ' + '1 or 2, typically.  Default is 1.  Assumes all phases of the element have a fuse of this type.'
        self.propertyHelp[4] = 'Name of the TCC Curve object that determines the fuse blowing.  Must have been previously defined as a TCC_Curve object.' + ' Default is \"Tlink\". ' + 'Multiplying the current values in the curve by the \"RatedCurrent\" value gives the actual current.'
        self.propertyHelp[5] = 'Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.'
        self.propertyHelp[6] = 'Fixed delay time (sec) added to Fuse blowing time determined from the TCC curve. Default is 0.0. Used to represent fuse clearing time or any other delay.'
        self.propertyHelp[7] = '{Trip/Open | Close}  Action that overrides the Fuse control. Simulates manual control on Fuse ' + '\"Trip\" or \"Open\" causes the controlled element to open and lock out. ' + '\"Close\" causes the controlled element to close and the Fuse to reset.'
        self.activeProperty = Fuse.NumPropsThisClass - 1
        super(FuseImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(FuseObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeFuseObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeFuseObj)
        result = 0
        af = self.activeFuseObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                # TODO Check zero based indexing
                af.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + self.getName() + '\"', 402)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    af.setMonitoredElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    af.setMonitoredElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    af.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    af.setElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    af.setFuseCurve(self.getTccCurve(param))
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    af.setRatedCurrent(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    af.setDelayTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    af.interpretFuseAction(param)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeFuseObj, paramPointer - Fuse.NumPropsThisClass)
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    af.setElementName(af.getMonitoredElementName())
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    af.setElementTerminal(af.getMonitoredElementTerminal())
                    break
                break
            # Default the controlled element to the monitored element
            paramName = parser.getNextParam()
            param = parser.makeString()
        af.recalcElementData()
        return result

    def makeLike(self, fuseName):
        result = 0
        # See if we can find this Fuse name in the present collection
        otherFuse = self.find(fuseName)
        if otherFuse is not None:
            af = self.activeFuseObj
            af.setNPhases(otherFuse.getNPhases())
            af.setNConds(otherFuse.getNConds())
            # force reallocation of terminal stuff
            af.setElementName(otherFuse.getElementName())
            af.setElementTerminal(otherFuse.getElementTerminal())
            af.setControlledElement(otherFuse.getControlledElement())
            # target circuit element
            af.setMonitoredElement(otherFuse.getMonitoredElement())
            # target circuit element
            af.setMonitoredElementName(otherFuse.getMonitoredElementName())
            # target circuit element
            af.setMonitoredElementTerminal(otherFuse.getMonitoredElementTerminal())
            # target circuit element
            af.setFuseCurve(otherFuse.getFuseCurve())
            af.setRatedCurrent(otherFuse.getRatedCurrent())
            # can't copy action handles
            af.setPresentState(otherFuse.getPresentState())
            af.setCondOffset(otherFuse.getCondOffset())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < af.getParentClass().getNumProperties()):
                    break
                af.setPropertyValue(i, otherFuse.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in Fuse makeLike: \"' + fuseName + '\" not found.', 403)
        return result
