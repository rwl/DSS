from dss.general.WireData import (WireData,)
from dss.general.impl.ConductorDataImpl import (ConductorDataImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.impl.WireDataObjImpl import (WireDataObjImpl,)
from dss.parser.impl.Parser import (Parser,)


class WireDataImpl(ConductorDataImpl, WireData):

    def __init__(self):
        super(WireDataImpl, self)()
        self.className = 'WireData'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = WireData.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.activeProperty = WireData.NumPropsThisClass - 1
        super(WireDataImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = WireDataObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        result = 0
        # continue parsing with contents of parser
        self.activeConductorDataObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeConductorDataObj
        parser = Parser.getInstance()
        acd = self.activeConductorDataObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                acd.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + acd.getName() + '\"', 101)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeConductorDataObj, paramPointer - WireData.NumPropsThisClass)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def makeLike(self, name):
        result = 0
        # See if we can find this line code in the present collection
        otherWireData = self.find(name)
        if otherWireData is not None:
            awo = self.activeConductorDataObj
            self.classMakeLike(otherWireData)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < awo.getParentClass().getNumProperties()):
                    break
                awo.setPropertyValue(i, otherWireData.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in WireData.makeLike: \"' + name + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement WireData.init()', -1)
        return 0

    def getCode(self):
        active = self.elementList.getActive()
        return active.getName()

    def setCode(self, value):
        self.activeConductorDataObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            wireData = self.elementList.get(i)
            if wireData.getName().equalsIgnoreCase(value):
                self.activeConductorDataObj = wireData
                return
        DSSGlobals.doSimpleMsg('WireData: \"' + value + '\" not found.', 103)
