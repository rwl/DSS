from dss.common.impl.Utilities import (Utilities,)
from dss.control.GenDispatcher import (GenDispatcher,)
from dss.control.impl.GenDispatcherObjImpl import (GenDispatcherObjImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)


class GenDispatcherImpl(ControlClassImpl, GenDispatcher):
    activeGenDispatcherObj = None

    def __init__(self):
        super(GenDispatcherImpl, self)()
        self.className = 'GenDispatcher'
        self.classType = self.classType + DSSClassDefs.GEN_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = GenDispatcher.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'Element'
        self.propertyName[1] = 'Terminal'
        self.propertyName[2] = 'kWLimit'
        self.propertyName[3] = 'kWBand'
        self.propertyName[4] = 'kvarlimit'
        self.propertyName[5] = 'GenList'
        self.propertyName[6] = 'Weights'
        self.propertyHelp[0] = 'Full object name of the circuit element, typically a line or transformer, ' + 'which the control is monitoring. There is no default; must be specified.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the GenDispatcher control is connected. ' + '1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.'
        self.propertyHelp[2] = 'kW Limit for the monitored element. The generators are dispatched to hold the power in band.'
        self.propertyHelp[3] = 'Bandwidth (kW) of the dead band around the target limit.' + 'No dispatch changes are attempted if the power in the monitored terminal stays within this band.'
        self.propertyHelp[4] = 'Max kvar to be delivered through the element.  Uses same dead band as kW.'
        self.propertyHelp[5] = 'Array list of generators to be dispatched.  If not specified, all generators in the circuit are assumed dispatchable.'
        self.propertyHelp[6] = 'Array of proportional weights corresponding to each generator in the GenList.' + ' The needed kW to get back to center band is dispatched to each generator according to these weights. ' + 'Default is to set all weights to 1.0.'
        self.activeProperty = GenDispatcher.NumPropsThisClass - 1
        super(GenDispatcherImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, ObjName):
        DSSGlobals.activeCircuit.setActiveCktElement(GenDispatcherObjImpl(self, ObjName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeGenDispatcherObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeGenDispatcherObj)
        result = 0
        agd = self.activeGenDispatcherObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                agd.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + agd.getName() + '\"', 364)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    agd.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    agd.setElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    agd.setKWLimit(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    agd.setKWBand(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    agd.setKVArLimit(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    Utilities.interpretStringListArray(param, agd.getGeneratorNameList())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    agd.setListSize(len(agd.getGeneratorNameList()))
                    if agd.getListSize() > 0:
                        agd.setWeights(Utilities.resizeArray(agd.getWeights(), agd.getListSize()))
                        Utilities.interpretDblArray(param, agd.getListSize(), agd.getWeights())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeGenDispatcherObj, paramPointer - GenDispatcher.NumPropsThisClass)
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 3:
                    _3 = True
                    agd.setHalfKWBand(agd.getKWBand() / 2.0)
                    break
                if (_3 is True) or (_2 == 5):
                    _3 = True
                    agd.getGenPointerList().clear()
                    # clear this for resetting on first sample
                    agd.setListSize(len(agd.getGeneratorNameList()))
                    agd.setWeights(Utilities.resizeArray(agd.getWeights(), agd.getListSize()))
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < agd.getListSize()):
                            break
                        agd.getWeights()[i] = 1.0
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        agd.recalcElementData()
        return result

    def makeLike(self, genDispatcherName):
        result = 0
        # See if we can find this GenDispatcher name in the present collection
        otherGenDispatcher = self.find(genDispatcherName)
        if otherGenDispatcher is not None:
            agd = self.activeGenDispatcherObj
            agd.setNPhases(otherGenDispatcher.getNPhases())
            agd.setNConds(otherGenDispatcher.getNConds())
            # force reallocation of terminal stuff
            agd.setElementName(otherGenDispatcher.getElementName())
            agd.setControlledElement(otherGenDispatcher.getControlledElement())
            # pointer to target circuit element
            agd.setMonitoredElement(otherGenDispatcher.getMonitoredElement())
            # pointer to target circuit element
            agd.setElementTerminal(otherGenDispatcher.getElementTerminal())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < agd.getParentClass().getNumProperties()):
                    break
                agd.setPropertyValue(i, otherGenDispatcher.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in GenDispatcher makeLike: \"' + genDispatcherName + '\" not found.', 370)
        return result
