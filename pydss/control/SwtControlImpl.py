from dss.control.SwtControl import (SwtControl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.SwtControlObjImpl import (SwtControlObjImpl,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)


class SwtControlImpl(ControlClassImpl, SwtControl):
    activeSwtControlObj = None

    def __init__(self):
        super(SwtControlImpl, self)()
        self.className = 'SwtControl'
        self.classType = self.classType + DSSClassDefs.SWT_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = SwtControl.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'SwitchedObj'
        self.propertyName[1] = 'SwitchedTerm'
        self.propertyName[2] = 'Action'
        self.propertyName[3] = 'Lock'
        self.propertyName[4] = 'Delay'
        self.propertyHelp[0] = 'Name of circuit element switch that the SwtControl operates. ' + 'Specify the full object class and name.'
        self.propertyHelp[1] = 'Terminal number of the controlled element switch. ' + '1 or 2, typically.  Default is 1.'
        self.propertyHelp[2] = '{Open | Close}  simulates manual operation of the controlled switch to open or close, after a time delay. ' + 'Note: automatic operation requires use of the COM interface with an external control algorithm.'
        self.propertyHelp[3] = 'Controlled switch is locked in its present open / close state. ' + 'Switch will not respond to either manual (Action) or automatic (COM interface) control until this Lock is removed.'
        self.propertyHelp[4] = 'Operating time delay (sec) of the switch. Defaults to 120.'
        self.activeProperty = SwtControl.NumPropsThisClass - 1
        super(SwtControlImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(SwtControlObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeSwtControlObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeSwtControlObj)
        result = 0
        asc = self.activeSwtControlObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                asc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + asc.getName() + '\"', 382)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    asc.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    asc.setElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    asc.interpretSwitchAction(param)
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    asc.setLocked(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    asc.setTimeDelay(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeSwtControlObj, paramPointer - SwtControl.NumPropsThisClass)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        asc.recalcElementData()
        return result

    def makeLike(self, swtControlName):
        result = 0
        # See if we can find this SwtControl name in the present collection
        otherSwtControl = self.find(swtControlName)
        if otherSwtControl is not None:
            asc = self.activeSwtControlObj
            asc.setNPhases(otherSwtControl.getNPhases())
            asc.setNConds(otherSwtControl.getNConds())
            # force reallocation of terminal stuff
            asc.setElementName(otherSwtControl.getElementName())
            asc.setElementTerminal(otherSwtControl.getElementTerminal())
            asc.setControlledElement(otherSwtControl.getControlledElement())
            # pointer to target circuit element
            asc.setTimeDelay(otherSwtControl.getTimeDelay())
            asc.setLocked(otherSwtControl.isLocked())
            asc.setPresentState(otherSwtControl.getPresentState())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < asc.getParentClass().getNumProperties()):
                    break
                asc.setPropertyValue(i, otherSwtControl.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in SwtControl makeLike: \"' + swtControlName + '\" not found.', 383)
        return result
