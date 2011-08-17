from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.general.LineSpacingObj import (LineSpacingObj,)
from dss.general.LineSpacing import (LineSpacing,)
# from java.io.PrintStream import (PrintStream,)


class LineSpacingObjImpl(DSSObjectImpl, LineSpacingObj):
    nConds = None
    nPhases = None
    X = None
    Y = None
    units = None
    dataChanged = None

    def __init__(self, parClass, lineSpacingName):
        super(LineSpacingObjImpl, self)(parClass)
        self.setName(lineSpacingName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.dataChanged = True
        self.X = None
        self.Y = None
        self.units = LineUnits.UNITS_FT
        self.setNWires(3)
        # allocates terminals
        self.nPhases = 3
        self.initPropertyValues(0)

    def dumpProperties(self, f, complete):
        super(LineSpacingObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < 5):
                break
            # TODO Check zero based indexing
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)

    def arrayString(self, pf, n):
        # FIXME Use StringBuffer
        r = '['
        if n > 0:
            r = r + String.format.format('%-g', pf[0])
            # TODO Check zero based indexing
        _0 = True
        i = 1
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < n):
                break
            r = r + String.format.format(',%-g', pf[i])
        return r + ']'

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 3:
                _1 = True
                return self.arrayString(self.X, self.nConds)
            if (_1 is True) or (_0 == 4):
                _1 = True
                return self.arrayString(self.Y, self.nConds)
            if (_1 is True) or (_0 == 5):
                _1 = True
                LineUnits.lineUnitsStr(self.units)
            if True:
                _1 = True
                return super(LineSpacingObjImpl, self).getPropertyValue(index)
            break

    def getXCoord(self, i):
        return self.X[i] if i <= self.nConds else 0.0

    def getYCoord(self, i):
        return self.Y[i] if i <= self.nConds else 0.0

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '3'
        self.propertyValue[1] = '3'
        self.propertyValue[2] = '0'
        self.propertyValue[3] = '32'
        self.propertyValue[4] = 'ft'
        super(LineSpacingObjImpl, self).initPropertyValues(LineSpacing.NumPropsThisClass)

    def setNWires(self, value):
        self.nConds = value
        self.X = [None] * self.nConds
        self.Y = [None] * self.nConds
        self.units = LineUnits.UNITS_FT

    def getNWires(self):
        return self.nConds

    def getNPhases(self):
        return self.nPhases

    def getUnits(self):
        # FIXME Private members in OpenDSS.
        return self.units

    def getNConds(self):
        return self.nConds

    def setNConds(self, num):
        self.nConds = num

    def getX(self):
        return self.X

    def setX(self, x):
        self.X = x

    def getY(self):
        return self.Y

    def setY(self, y):
        self.Y = y

    def isDataChanged(self):
        return self.dataChanged

    def setDataChanged(self, changed):
        self.dataChanged = changed

    def setNPhases(self, num):
        self.nPhases = num

    def setUnits(self, value):
        self.units = value
