from dss.general.TCC_CurveObj import (TCC_CurveObj,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.general.TCC_Curve import (TCC_Curve,)


class TCC_CurveObjImpl(DSSObjectImpl, TCC_CurveObj):
    lastValueAccessed = None
    npts = None
    # Number of points in curve
    logT = None
    logC = None
    tValues = None
    cValues = None
    # logarithms of t_values and c_values
    # time values (hr) if interval > 0.0  else null

    def __init__(self, parClass, name):
        super(TCC_CurveObjImpl, self)(parClass)
        self.setName(name.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.lastValueAccessed = 0
        # TODO Check zero based indexing
        self.npts = 0
        self.cValues = None
        self.tValues = None
        self.logC = None
        self.logT = None
        self.initPropertyValues(0)

    def getTCCTime(self, cValue):
        """This function returns the operation time for the value given.
        If the value is less than the first entry, return = -1 for no operation.
        Log-log interpolation is used.
        """
        result = -1.0
        # default return value
        # If current is less than first point, just leave.
        if cValue < self.cValues[0]:
            return result
        if self.npts > 0:
            # Handle exceptional cases
            if self.npts == 1:
                result = self.tValues[0]
            else:
                # Start with previous value accessed under the assumption that most
                # of the time, this function will be called sequentially}

                if self.cValues[self.lastValueAccessed] > cValue:
                    self.lastValueAccessed = 0
                    # start over from beginning
                _0 = True
                i = self.lastValueAccessed + 1
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.npts):
                        break
                    # TODO Check zero based indexing
                    if self.cValues[i] == cValue:
                        result = self.tValues[i]
                        self.lastValueAccessed = i
                        return result
                    elif self.cValues[i] > cValue:
                        # log-log interpolation
                        self.lastValueAccessed = i - 1
                        # TODO Check zero based indexing
                        if cValue > 0.0:
                            logTest = self.Math.log(cValue)
                        else:
                            logTest = self.Math.log(0.001)
                        result = self.Math.exp(self.logT[self.lastValueAccessed] + (((logTest - self.logC[self.lastValueAccessed]) / (self.logC[i] - self.logC[self.lastValueAccessed])) * (self.logT[i] - self.logT[self.lastValueAccessed])))
                        return result
                # if we fall through the loop, just use last value
                self.lastValueAccessed = self.npts - 1
                # TODO Check zero based indexing
                result = self.tValues[self.npts]
        return result

    def getOVTime(self, vValue):
        """Return operating time for over-voltage relay."""
        result = -1.0
        # no op return
        if vValue > self.cValues[0]:
            if self.npts == 1:
                result = self.tValues[0]
            else:
                i = 0
                # TODO Check zero based indexing
                while self.cValues[i] < vValue:
                    i += 1
                    if i > self.npts:
                        break
                result = self.tValues[i - 1]
        return result

    def getUVTime(self, vValue):
        """Return operating time for under-voltage relay."""
        result = -1.0
        # no op return
        if vValue < self.cValues[self.npts]:
            if self.npts == 1:
                result = self.tValues[0]
            else:
                i = self.npts
                while self.cValues[i] > vValue:
                    i -= 1
                    if i == 0:
                        break
                result = self.tValues[i + 1]
        return result

    def value(self, i):
        """Get C_Value by index."""
        if i <= self.npts and i > 0:
            self.lastValueAccessed = i
            return self.cValues[i]
        else:
            return 0.0

    def time(self, i):
        """Get time value (sec) corresponding to point index."""
        if i <= self.npts and i > 0:
            self.lastValueAccessed = i
            return self.tValues[i]
        else:
            return 0.0

    def dumpProperties(self, f, complete):
        super(TCC_CurveObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.propertyValue[i]

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                result = '('
                break
            if (_1 is True) or (_0 == 2):
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
                    result = result + String.format.format('%-g, ', self.cValues[i])
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
                    result = result + String.format.format('%-g, ', self.tValues[i])
                break
            if True:
                _3 = True
                result = super(TCC_CurveObjImpl, self).getPropertyValue(index)
                break
            break
        _6 = index
        _7 = False
        while True:
            if _6 == 1:
                _7 = True
                result = result + ')'
                break
            if (_7 is True) or (_6 == 2):
                _7 = True
                result = result + ')'
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '0'
        # number of points to expect
        self.propertyValue[1] = ''
        # vector of multiplier values
        self.propertyValue[2] = ''
        # vector of sec values
        super(TCC_CurveObjImpl, self).initPropertyValues(TCC_Curve.NumPropsThisClass)

    def getNumPoints(self):
        return self.npts

    def getLastValueAccessed(self):
        return self.lastValueAccessed

    def setLastValueAccessed(self, lastValue):
        self.lastValueAccessed = lastValue

    def getNPts(self):
        return self.npts

    def setNPts(self, n):
        self.npts = n

    def getLogT(self):
        return self.logT

    def setLogT(self, logt):
        self.logT = logt

    def getLogC(self):
        return self.logC

    def setLogC(self, logc):
        self.logC = logc

    def getCValues(self):
        return self.cValues

    def setCValues(self, values):
        self.cValues = values

    def getTValues(self):
        return self.tValues

    def setTValues(self, values):
        self.tValues = values
