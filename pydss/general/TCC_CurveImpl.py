from dss.general.impl.TCC_CurveObjImpl import (TCC_CurveObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.general.TCC_Curve import (TCC_Curve,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.common.impl.DSSClassImpl import (DSSClassImpl,)


class TCC_CurveImpl(DSSClassImpl, TCC_Curve):
    activeTCC_CurveObj = None

    def __init__(self):
        super(TCC_CurveImpl, self)()
        self.className = 'TCC_Curve'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = TCC_Curve.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'npts'
        # number of points to expect
        self.propertyName[1] = 'C_array'
        # vector of multiplier values
        self.propertyName[2] = 'T_array'
        # vextor of time values , Sec
        # define property help values
        self.propertyHelp[0] = 'Number of points to expect in time-current arrays.'
        # number of points to expect
        self.propertyHelp[1] = 'Array of current (or voltage) values corresponding to time values (see help on T_Array).'
        # vector of multiplier values
        self.propertyHelp[2] = 'Array of time values in sec. Typical array syntax: ' + CRLF + 't_array = (1, 2, 3, 4, ...)' + CRLF + CRLF + 'Can also substitute a file designation: ' + CRLF + 't_array =  (file=filename)' + CRLF + CRLF + 'The specified file has one value per line.'
        self.activeProperty = TCC_Curve.NumPropsThisClass - 1
        super(TCC_CurveImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = TCC_CurveObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def calcLogPoints(self, X, logX, n):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < n):
                break
            if X[i] > 0.0:
                logX[i] = self.Math.log(X[i])
            else:
                logX[i] = self.Math.log(0.001)

    def edit(self):
        result = 0
        # continue parsing with contents of parser
        self.activeTCC_CurveObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = self.activeTCC_CurveObj
        parser = Parser.getInstance()
        atc = self.activeTCC_CurveObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            # FIXME Check indenting of other edit methods at this point.
            if paramPointer > 0 and paramPointer <= self.numProperties:
                atc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + atc.getName() + '\"', 420)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    atc.setNPts(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    Utilities.interpretDblArray(param, atc.getNPts(), atc.getCValues())
                    # Parser.ParseAsVector(Npts, Multipliers);
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    Utilities.interpretDblArray(param, atc.getNPts(), atc.getTValues())
                    # Parser.ParseAsVector(Npts, Hours);
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeTCC_CurveObj, paramPointer - TCC_Curve.NumPropsThisClass)
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    atc.setCValues(Utilities.resizeArray(atc.getCValues(), atc.getNPts()))
                    atc.setLogC(Utilities.resizeArray(atc.getLogC(), atc.getNPts()))
                    atc.setTValues(Utilities.resizeArray(atc.getTValues(), atc.getNPts()))
                    atc.setLogT(Utilities.resizeArray(atc.getLogT(), atc.getNPts()))
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    self.calcLogPoints(atc.getCValues(), atc.getLogC(), atc.getNPts())
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    self.calcLogPoints(atc.getTValues(), atc.getLogT(), atc.getNPts())
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def makeLike(self, name):
        result = 0
        # See if we can find this line code in the present collection
        otherTCC_Curve = self.find(name)
        if otherTCC_Curve is not None:
            atc = self.activeTCC_CurveObj
            atc.setNPts(otherTCC_Curve.getNPts())
            atc.setCValues(Utilities.resizeArray(atc.getCValues(), atc.getNPts()))
            atc.setLogC(Utilities.resizeArray(atc.getLogC(), atc.getNPts()))
            atc.setTValues(Utilities.resizeArray(atc.getTValues(), atc.getNPts()))
            atc.setLogT(Utilities.resizeArray(atc.getLogT(), atc.getNPts()))
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < atc.getNPts()):
                    break
                atc.getCValues()[i] = otherTCC_Curve.getCValues()[i]
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < atc.getNPts()):
                    break
                atc.getTValues()[i] = otherTCC_Curve.getTValues()[i]
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < atc.getNPts()):
                    break
                atc.getLogC()[i] = otherTCC_Curve.getLogC()[i]
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < atc.getNPts()):
                    break
                atc.getLogT()[i] = otherTCC_Curve.getLogT()[i]
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < atc.getParentClass().getNumProperties()):
                    break
                atc.setPropertyValue(i, otherTCC_Curve.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in TCC_Curve.makeLike(): \"' + name + '\" not found.', 421)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement TCC_Curve.init()', -1)
        return 0

    def getCode(self):
        curve = self.elementList.getActive()
        return curve.getName()

    def setCode(self, value):
        self.activeTCC_CurveObj = None
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            curve = self.elementList.get(i)
            if curve.getName().equalsIgnoreCase(value):
                self.activeTCC_CurveObj = curve
                return
        DSSGlobals.doSimpleMsg('TCC_Curve: \"' + value + '\" not found.', 422)
