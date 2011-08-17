from __pyjamas__ import (ARGERROR,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.general.XYCurveObj import (XYCurveObj,)
from dss.general.XYCurve import (XYCurve,)


class XYCurveObjImpl(DSSObjectImpl, XYCurveObj):
    lastValueAccessed = None
    numPoints = None
    # number of points in curve
    arrayPropertyIndex = None
    X = None
    Y = None
    XValues = None
    YValues = None

    def __init__(self, parClass, XYCurveName):
        super(XYCurveObjImpl, self)(parClass)
        self.setName(XYCurveName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.lastValueAccessed = 0
        self.numPoints = 0
        self.XValues = None
        self.YValues = None
        self.X = 0.0
        self.Y = 0.0
        self.arrayPropertyIndex = -1
        self.initPropertyValues(0)

    def getYValue(self, *args):
        """Get Y value at specified X value.

        This method returns the interpolated Y value for the given X.
        If no points exist in the curve, the result is  0.0
        If Xvalue is outside the range of defined X values,
        the curve is extrapolated from the ends.
        ---
        Get Y value by index.
        """
        _0 = args
        _1 = len(args)
        if _1 == 1:
            if isinstance(_0[0], double):
                X, = _0
                Result = 0.0
                # default return value if no points in curve
                if self.numPoints > 0:
                    # handle exceptional cases
                    if self.numPoints == 1:
                        return self.YValues[0]
                    else:
                        # Start with previous value accessed under the assumption that most
                        # of the time, the values won't change much.

                        if self.XValues[self.lastValueAccessed] > X:
                            self.lastValueAccessed = 1
                            # start over from beginning
                            # if off the curve for the first point, extrapolate from the first two points
                        if self.lastValueAccessed == 0 and self.XValues[0] > X:
                            Result = self.interpolatePoints(0, 1, X, self.XValues, self.YValues)
                            return Result
                        # in the middle of the arrays
                        _0 = True
                        i = self.lastValueAccessed
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < self.numPoints):
                                break
                            if self.Math.abs(self.XValues[i] - X) < 1.0000000000000002e-05:
                                # if close to an actual point, just use it
                                Result = self.YValues[i]
                                self.lastValueAccessed = i
                                return Result
                            elif self.XValues[i] > X:
                                # interpolate between two values
                                self.lastValueAccessed = i - 1
                                # TODO Check zero based indexing
                                Result = self.interpolatePoints(i, self.lastValueAccessed, X, self.XValues, self.YValues)
                                return Result
                        # if we fall through the loop, Extrapolate from last two points
                        self.lastValueAccessed = self.numPoints - 1
                        Result = self.interpolatePoints(self.numPoints, self.lastValueAccessed, X, self.XValues, self.YValues)
                return Result
            else:
                i, = _0
                if i < self.numPoints and i >= 0:
                    self.lastValueAccessed = i
                    return self.YValues[i]
                else:
                    return 0.0
        else:
            raise ARGERROR(1, 1)

    def getXValue(self, *args):
        """Get X value corresponding to point index.
        ---
        Get X value at specified Y value.

        This method returns the interpolated X value for the given Y.
        If no points exist in the curve, the result is 0.0
        If Xvalue is outside the range of defined X values,
        the curve is extrapolated from the Ends.
        """
        _0 = args
        _1 = len(args)
        if _1 == 1:
            if isinstance(_0[0], double):
                Y, = _0
                result = 0.0
                # default return value if no points in curve
                if self.numPoints > 0:
                    # handle exceptional cases
                    if self.numPoints == 1:
                        result = self.XValues[0]
                    else:
                        # Start with previous value accessed under the assumption that most
                        # of the time, this function will be called sequentially

                        if self.YValues[self.lastValueAccessed] > Y:
                            self.lastValueAccessed = 0
                            # start over from beginning
                            # if off the curve for the first point, extrapolate from the first two points
                        if self.lastValueAccessed == 0 and self.YValues[0] > Y:
                            result = self.interpolatePoints(0, 1, Y, self.YValues, self.XValues)
                            return result
                        _0 = True
                        i = self.lastValueAccessed
                        while True:
                            if _0 is True:
                                _0 = False
                            else:
                                i += 1
                            if not (i < self.numPoints):
                                break
                            # TODO Check zero based indexing
                            if self.Math.abs(self.YValues[i] - Y) < 1.0000000000000002e-05:
                                # if close to an actual point, just use it.
                                result = self.XValues[i]
                                self.lastValueAccessed = i
                                return result
                            elif self.YValues[i] > Y:
                                # interpolate
                                self.lastValueAccessed = i - 1
                                # TODO Check zero based indexing
                                result = self.interpolatePoints(i, self.lastValueAccessed, Y, self.YValues, self.XValues)
                                return result
                        # if we fall through the loop, extrapolate from last two points
                        self.lastValueAccessed = self.numPoints - 1
                        result = self.interpolatePoints(self.numPoints, self.lastValueAccessed, Y, self.YValues, self.XValues)
                return result
            else:
                i, = _0
                if i < self.numPoints and i >= 0:
                    self.lastValueAccessed = i
                    return self.XValues[i]
                else:
                    return 0.0
        else:
            raise ARGERROR(1, 1)

    def dumpProperties(self, f, complete):
        super(XYCurveObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            _1 = i
            _2 = False
            while True:
                if _1 == 2:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=(' + self.getPropertyValue(i) + ')'
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=(' + self.getPropertyValue(i) + ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(i)
                    break
                break

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                result = '['
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = '['
                break
            if True:
                _1 = True
                result = ''
                break
            break
        _2 = index
        _3 = False
        while True:
            if _2 == 1:
                _3 = True
                if self.XValues is not None and self.YValues is not None:
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < self.numPoints):
                            break
                        result = result + String.format.format('%.8g, %.8g ', self.XValues[i], self.YValues[i])
                else:
                    result = '0, 0'
                break
            if (_3 is True) or (_2 == 2):
                _3 = True
                if self.YValues is not None:
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < self.numPoints):
                            break
                        result = result + String.format.format('%-g, ', self.YValues[i])
                else:
                    result = '0'
                break
            if (_3 is True) or (_2 == 3):
                _3 = True
                if self.XValues is not None:
                    _6 = True
                    i = 0
                    while True:
                        if _6 is True:
                            _6 = False
                        else:
                            i += 1
                        if not (i < self.numPoints):
                            break
                        result = result + String.format.format('%-g, ', self.XValues[i])
                else:
                    result = '0'
                break
            if (_3 is True) or (_2 == 7):
                _3 = True
                result = String.format.format('%.8g', self.getXValue(self.Y))
                break
            if (_3 is True) or (_2 == 8):
                _3 = True
                result = String.format.format('%.8g', self.getYValue(self.X))
                break
            if True:
                _3 = True
                result = super(XYCurveObjImpl, self).getPropertyValue(index)
                break
            break
        _7 = index
        _8 = False
        while True:
            if _7 == 1:
                _8 = True
                result = '['
                break
            if (_8 is True) or (_7 == 2):
                _8 = True
                result = '['
                break
            if (_8 is True) or (_7 == 3):
                _8 = True
                result = '['
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '0')
        # number of points to expect
        self.setPropertyValue(1, '')
        self.setPropertyValue(2, '')
        self.setPropertyValue(3, '')
        self.setPropertyValue(4, '')
        self.setPropertyValue(5, '')
        self.setPropertyValue(6, '')
        super(XYCurveObjImpl, self).initPropertyValues(XYCurve.NumPropsThisClass)

    def interpolatePoints(self, i, j, X, XArray, YArray):
        den = XArray[i] - XArray[j]
        if den != 0.0:
            return YArray[j] + (((X - XArray[j]) / den) * (YArray[i] - YArray[j]))
        else:
            return YArray[i]
            # Y is undefined, return i-th value

    def getNumPoints(self):
        return self.numPoints

    def setNumPoints(self, num):
        # FIXME Private members in OpenDSS
        self.setPropertyValue(0, String.valueOf.valueOf(num))
        # update property list variable
        # reset array property values to keep them in proper order in save
        if self.arrayPropertyIndex >= 0:
            self.setPropertyValue(self.arrayPropertyIndex, self.getPropertyValue(self.arrayPropertyIndex))
        self.numPoints = num
        # now assign the value

    def getLastValueAccessed(self):
        return self.lastValueAccessed

    def setLastValueAccessed(self, lastValue):
        self.lastValueAccessed = lastValue

    def getArrayPropertyIndex(self):
        return self.arrayPropertyIndex

    def setArrayPropertyIndex(self, index):
        self.arrayPropertyIndex = index

    def getX(self):
        return self.X

    def setX(self, x):
        self.X = x

    def getY(self):
        return self.Y

    def setY(self, y):
        self.Y = y

    def getXValues(self):
        return self.XValues

    def setXValues(self, xvalues):
        self.XValues = xvalues

    def getYValues(self):
        return self.YValues

    def setYValues(self, yvalues):
        self.YValues = yvalues
