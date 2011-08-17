from dss.shared.impl.LineUnits import (LineUnits,)
from dss.general.LineSpacing import (LineSpacing,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.general.impl.LineSpacingObjImpl import (LineSpacingObjImpl,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)


class LineSpacingImpl(DSSClassImpl, LineSpacing):
    activeLineSpacingObj = None

    class SpcParmChoice(object):
        X = 'X'
        H = 'H'
        _values = [X, H]

        @classmethod
        def values(cls):
            return cls._enum_values[:]

    def __init__(self):
        super(LineSpacingImpl, self)()
        self.className = 'LineSpacing'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
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
        self.propertyName[0] = 'nconds'
        self.propertyName[1] = 'nphases'
        self.propertyName[2] = 'x'
        self.propertyName[3] = 'h'
        self.propertyName[4] = 'units'
        self.propertyHelp[0] = 'Number of wires in this geometry. Default is 3. Triggers memory allocations. Define first!'
        self.propertyHelp[1] = 'Number of retained phase conductors. If less than the number of wires, list the retained phase coordinates first.'
        self.propertyHelp[2] = 'Array of wire X coordinates.'
        self.propertyHelp[3] = 'Array of wire Heights.'
        self.propertyHelp[4] = 'Units for x and h: {mi|kft|km|m|Ft|in|cm } Initial default is \"ft\", but defaults to last unit defined'
        self.activeProperty = LineSpacing.NumPropsThisClass - 1
        super(LineSpacingImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = LineSpacingObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def interpretArray(self, s, which):
        DSSGlobals.auxParser.setCmdString(s)
        als = self.activeLineSpacingObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < als.getNWires()):
                break
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name not expecting any
            Str = DSSGlobals.auxParser.makeString()
            if len(Str) > 0:
                _1 = which
                _2 = False
                while True:
                    if _1 == self.X:
                        _2 = True
                        als.getX()[i] = DSSGlobals.auxParser.makeDouble()
                        break
                    if (_2 is True) or (_1 == self.H):
                        _2 = True
                        als.getY()[i] = DSSGlobals.auxParser.makeDouble()
                        break
                    break

    def edit(self):
        result = 0
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeLineSpacingObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeLineSpacingObj
        als = self.activeLineSpacingObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
                if paramPointer > 0 and paramPointer <= self.numProperties:
                    als.setPropertyValue(paramPointer, param)
                _0 = paramPointer
                _1 = False
                while True:
                    if _0 == 0:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + als.getName() + '\"', 10101)
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        als.setNWires(parser.makeInteger())
                        # use property value to force reallocations
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        als.setNPhases(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        self.interpretArray(param, self.SpcParmChoice.X)
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        self.interpretArray(param, self.SpcParmChoice.H)
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        als.setUnits(LineUnits.getUnitsCode(param))
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeLineSpacingObj, paramPointer - LineSpacing.NumPropsThisClass)
                        break
                    break
                _2 = paramPointer
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        als.setDataChanged(True)
                        break
                    if (_3 is True) or (_2 == 2):
                        _3 = True
                        als.setDataChanged(True)
                        break
                    if (_3 is True) or (_2 == 3):
                        _3 = True
                        als.setDataChanged(True)
                        break
                    if (_3 is True) or (_2 == 4):
                        _3 = True
                        als.setDataChanged(True)
                        break
                    if (_3 is True) or (_2 == 5):
                        _3 = True
                        als.setDataChanged(True)
                        break
                    break
                paramName = parser.getNextParam()
                param = parser.makeString()
        return result

    def makeLike(self, lineName):
        result = 0
        # See if we can find this line code in the present collection
        otherLineSpacing = self.find(lineName)
        if otherLineSpacing is not None:
            als = self.activeLineSpacingObj
            als.setNWires(otherLineSpacing.getNWires())
            # allocates
            als.setNPhases(otherLineSpacing.getNPhases())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < als.getNConds()):
                    break
                als.getX()[i] = otherLineSpacing.getX()[i]
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < als.getNConds()):
                    break
                als.getY()[i] = otherLineSpacing.getY()[i]
            als.setUnits(otherLineSpacing.getUnits())
            als.setDataChanged(True)
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < als.getParentClass().getNumProperties()):
                    break
                als.setPropertyValue(i, otherLineSpacing.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in LineSpacing makeLike: \"' + lineName + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement LineSpacing.init()', -1)
        return 0

    def getCode(self):
        """Returns active line code string."""
        active = self.elementList.getActive()
        return active.getName()

    def setCode(self, value):
        """Sets the active LineSpacing."""
        self.activeLineSpacingObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            pSpacing = self.elementList.get(i)
            if pSpacing.getName().equalsIgnoreCase(value):
                self.activeLineSpacingObj = pSpacing
                return
        DSSGlobals.doSimpleMsg('LineSpacing: \"' + value + '\" not found.', 103)
