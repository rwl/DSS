from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.general.GrowthShapeObj import (GrowthShapeObj,)
from dss.general.GrowthShape import (GrowthShape,)


class GrowthShapeObjImpl(DSSObjectImpl, GrowthShapeObj):
    npts = None
    nYears = None
    baseYear = None
    year = None
    yearMult = None
    multiplier = None

    def __init__(self, parClass, growthShapeName):
        super(GrowthShapeObjImpl, self)(parClass)
        self.setName(growthShapeName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.npts = 0
        self.year = None
        self.multiplier = None
        self.nYears = 30
        self.yearMult = [None] * self.nYears
        self.initPropertyValues(0)

    def getMult(self, yr):
        """Get multiplier for specified year.

        This function returns the multiplier to use for a load in the given year.
        The first year specified in the curve is the base year.  The base value
        is the beginning of the first year.
        """
        # FIXME Private procedure in OpenDSS
        result = 1.0
        # default return value if no points in curve
        if self.npts > 0:
            # handle exceptional cases
            index = yr - self.baseYear
            if index > 0:
                # returns 1.0 for base year or any year previous  TODO Check zero based indexing
                if index > self.nYears:
                    # make some more space
                    self.nYears = index + 10
                    self.yearMult = Utilities.resizeArray(self.yearMult, self.nYears)
                    self.reCalcYearMult()
                result = self.yearMult[index]
        return result

    def reCalcYearMult(self):
        # fill up the yearMult array with total yearly multiplier from base year
        mult = self.multiplier[1]
        multInc = mult
        self.yearMult[0] = mult
        # TODO Check zero based indexing
        dataPtr = 1
        # TODO Check zero based indexing
        yr = self.baseYear
        _0 = True
        i = 1
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nYears):
                break
            # TODO Check zero based indexing
            yr += 1
            if dataPtr < self.npts:
                if self.year[dataPtr + 1] == yr:
                    dataPtr += 1
                    multInc = self.multiplier[dataPtr]
            mult = mult * multInc
            self.yearMult[i] = mult

    def dumpProperties(self, f, complete):
        super(GrowthShapeObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            _1 = i
            _2 = False
            while True:
                if _1 == 2:
                    _2 = True
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=(' + self.getPropertyValue(i) + ')'
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=(' + self.getPropertyValue(i) + ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
                    break
                break

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 2:
                _1 = True
                result = '('
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = '('
                break
            if True:
                _1 = True
                result = ''
                break
            break
        _2 = index
        _3 = False
        while True:
            if _2 == 2:
                _3 = True
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.npts):
                        break
                    result = result + String.format.format('%-d, ', self.year[i])
                break
            if (_3 is True) or (_2 == 3):
                _3 = True
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.npts):
                        break
                    result = result + String.format.format('%-g, ', self.multiplier[i])
                break
            if True:
                _3 = True
                result = super(GrowthShapeObjImpl, self).getPropertyValue(index)
                break
            break
        _6 = index
        _7 = False
        while True:
            if _6 == 2:
                _7 = True
                result = result + ')'
                break
            if (_7 is True) or (_6 == 3):
                _7 = True
                result = result + ')'
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        # FIXME Private members in OpenDSS
        self.propertyValue[0] = '0'
        # number of points to expect
        self.propertyValue[1] = ''
        # vector of year values
        self.propertyValue[2] = ''
        # vector of multiplier values corresponding to years
        self.propertyValue[3] = ''
        # switch input to a csvfile                (year, mult)
        self.propertyValue[4] = ''
        # switch input to a binary file of singles (year, mult)
        self.propertyValue[5] = ''
        # switch input to a binary file of doubles (year, mult)
        super(GrowthShapeObjImpl, self).initPropertyValues(GrowthShape.NumPropsThisClass)

    def getNPts(self):
        return self.npts

    def setNPts(self, n):
        self.npts = n

    def getYear(self):
        return self.year

    def setYear(self, values):
        self.year = values

    def getMultiplier(self):
        return self.multiplier

    def setMultiplier(self, mult):
        self.multiplier = mult

    def getBaseYear(self):
        return self.baseYear

    def setBaseYear(self, year):
        self.baseYear = year
