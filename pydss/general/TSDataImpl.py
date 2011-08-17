from dss.general.impl.TSDataObjImpl import (TSDataObjImpl,)
from dss.general.impl.ConductorDataImpl import (ConductorDataImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.impl.CableDataImpl import (CableDataImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.general.TSData import (TSData,)


class TSDataImpl(CableDataImpl, TSData):

    def __init__(self):
        super(TSDataImpl, self)()
        self.className = 'TSData'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = TSData.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'DiaShield'
        self.propertyName[1] = 'TapeLayer'
        self.propertyName[2] = 'TapeLap'
        self.propertyHelp[0] = 'Diameter over tape shield; same units as radius; no default.'
        self.propertyHelp[1] = 'Tape shield thickness; same units as radius; no default.'
        self.propertyHelp[2] = 'Tape Lap in percent; default 20.0'
        self.activeProperty = TSData.NumPropsThisClass - 1
        super(TSDataImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = TSDataObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        """Uses global parser."""
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        ConductorDataImpl.activeConductorDataObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = ConductorDataImpl.activeConductorDataObj
        tsd = ConductorDataImpl.activeConductorDataObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                tsd.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + tsd.getName() + '\"', 101)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    tsd.setDiaShield(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    tsd.setTapeLayer(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    tsd.setTapeLap(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(ConductorDataImpl.activeConductorDataObj, paramPointer - self.NumPropsThisClass)
                    break
                break
            # Check for critical errors
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    if tsd.getDiaShield() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Diameter over shield must be positive for TapeShieldData ' + tsd.getName(), 999)
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    if tsd.getTapeLayer() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Tape shield thickness must be positive for TapeShieldData ' + tsd.getName(), 999)
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    if (tsd.getTapeLap() < 0.0) or (tsd.getTapeLap() > 100.0):
                        DSSGlobals.doSimpleMsg('Error: Tap lap must range from 0 to 100 for TapeShieldData ' + tsd.getName(), 999)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def makeLike(self, TSName):
        result = 0
        otherData = self.find(TSName)
        if otherData is not None:
            tsd = ConductorDataImpl.activeConductorDataObj
            tsd.setDiaShield(otherData.getDiaShield())
            tsd.setTapeLayer(otherData.getTapeLayer())
            tsd.setTapeLap(otherData.getTapeLap())
            self.classMakeLike(otherData)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < tsd.getParentClass().getNumProperties()):
                    break
                tsd.setPropertyValue(i, otherData.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in TapeShield makeLike: \"' + TSName + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement TSData.init', -1)
        return 0

    def getCode(self):
        return self.elementList.getActive().getName()

    def setCode(self, value):
        ConductorDataImpl.activeConductorDataObj = None
        pTSDataObj = self.elementList.getFirst()
        while pTSDataObj is not None:
            if pTSDataObj.getName().equalsIgnoreCase(value):
                ConductorDataImpl.activeConductorDataObj = pTSDataObj
                return
            pTSDataObj = self.elementList.getNext()
        DSSGlobals.doSimpleMsg('TSData: \"' + value + '\" not found.', 103)
