from __pyjamas__ import (ARGERROR,)
from dss.general.TShapeObj import (TShapeObj,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.general.TShape import (TShape,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)


class TShapeObjImpl(DSSObjectImpl, TShapeObj):
    lastValueAccessed = None
    numPoints = None
    # number of points in curve
    arrayPropertyIndex = None
    stdDevCalculated = None
    mean = MutableDouble()
    stdDev = MutableDouble()
    interval = None
    # =0.0 then random interval (hr)
    hours = None
    # time values (hr) if interval > 0.0 else nil
    TValues = None
    # temperatures

    def __init__(self, parClass, TShapeName):
        super(TShapeObjImpl, self)(parClass)
        self.setName(TShapeName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.lastValueAccessed = 0
        self.numPoints = 0
        self.interval = 1.0
        # hr
        self.hours = None
        self.TValues = None
        self.stdDevCalculated = False
        # calculate on demand
        self.arrayPropertyIndex = -1
        self.initPropertyValues(0)

    def getTemperature(self, *args):
        """Get temperatures at specified time, hr.

        This method returns the temperature for the given hour.
        If no points exist in the curve, the result is 0.0
        If there are fewer points than requested, the curve is simply assumed to repeat
        Thus a daily load curve can suffice for a yearly load curve: You just get the
        same day over and over again.
        The value returned is the nearest to the interval requested. Thus if you request
        hour=12.25 and the interval is 1.0, you will get interval 12.
        ---
        Get temperatures by index.
        """
        _0 = args
        _1 = len(args)
        if _1 == 1:
            if isinstance(_0[0], double):
                hr, = _0
                result = 0.0
                # default return value if no points in curve
                if self.numPoints > 0:
                    # handle exceptional cases
                    if self.numPoints == 1:
                        result = self.TValues[0]
                    elif self.interval > 0.0:
                        index = self.Math.round(hr / self.interval)
                        if index > self.numPoints:
                            index = index % self.numPoints
                            # wrap around using remainder
                        if index == 0:
                            index = self.numPoints
                        result = self.TValues[index]
                    else:
                        # for random interval
                        # Start with previous value accessed under the assumption that most
                        # of the time, this function will be called sequentially

                        # Normalize Hr to max hour in curve to get wraparound
                        if hr > self.hours[self.numPoints]:
                            hr = hr - ((hr / self.hours[self.numPoints]) * self.hours[self.numPoints])
                        if self.hours[self.lastValueAccessed] > hr:
                            self.lastValueAccessed = 1
                            # start over from beginning
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
                            if self.Math.abs(self.hours[i] - hr) < 1.0000000000000002e-05:
                                # if close to an actual point, just use it
                                result = self.TValues[i]
                                self.lastValueAccessed = i
                                return result
                            elif self.hours[i] > hr:
                                # interpolate for temperature
                                self.lastValueAccessed = i - 1
                                result = self.TValues[self.lastValueAccessed] + (((hr - self.hours[self.lastValueAccessed]) / (self.hours[i] - self.hours[self.lastValueAccessed])) * (self.TValues[i] - self.TValues[self.lastValueAccessed]))
                                return result
                        # if we fall through the loop, just use last value
                        self.lastValueAccessed = self.numPoints - 1
                        result = self.TValues[self.numPoints]
                return result
            else:
                i, = _0
                if i < self.numPoints and i >= 0:
                    self.lastValueAccessed = i
                    return self.TValues[i]
                else:
                    return 0.0
        else:
            raise ARGERROR(1, 1)

    def calcMeanandStdDev(self):
        if self.numPoints > 0:
            if self.interval > 0.0:
                MathUtil.RCDMeanandStdDev(self.TValues, self.numPoints, self.mean, self.stdDev)
            else:
                MathUtil.curveMeanAndStdDev(self.TValues, self.hours, self.numPoints, self.mean, self.stdDev)
        self.setPropertyValue(4, String.format.format('%.8g', self.mean.doubleValue()))
        self.setPropertyValue(5, String.format.format('%.8g', self.stdDev.doubleValue()))
        self.stdDevCalculated = True

    def getMean(self):
        if not self.stdDevCalculated:
            self.calcMeanandStdDev()
        return self.mean.doubleValue()

    def getStdDev(self):
        if not self.stdDevCalculated:
            self.calcMeanandStdDev()
        return self.stdDev.doubleValue()

    def getHour(self, i):
        """Get hour corresponding to point index."""
        if self.interval == 0:
            if i < self.numPoints and i >= 0:
                self.lastValueAccessed = i
                return self.hours[i]
            else:
                return 0.0
        else:
            self.lastValueAccessed = i
            return self.hours[i] * self.interval

    def dumpProperties(self, f, complete):
        super(TShapeObjImpl, self).dumpProperties(f, complete)
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
            if _0 == 2:
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
                result = String.format.format('%.8g', self.interval)
                break
            if (_3 is True) or (_2 == 2):
                _3 = True
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.numPoints):
                        break
                    result = result + String.format.format('%-g, ', self.TValues[i])
                break
            if (_3 is True) or (_2 == 3):
                _3 = True
                if self.hours is not None:
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < self.numPoints):
                            break
                        result = result + String.format.format('%-g, ', self.hours[i])
                break
            if (_3 is True) or (_2 == 4):
                _3 = True
                result = String.format.format('%.8g', self.mean.doubleValue())
                break
            if (_3 is True) or (_2 == 5):
                _3 = True
                result = String.format.format('%.8g', self.stdDev.doubleValue())
                break
            if (_3 is True) or (_2 == 9):
                _3 = True
                result = String.format.format('%.8g', self.interval * 3600.0)
                break
            if (_3 is True) or (_2 == 10):
                _3 = True
                result = String.format.format('%.8g', self.interval * 60.0)
                break
            if True:
                _3 = True
                result = super(TShapeObjImpl, self).getPropertyValue(index)
                break
            break
        _6 = index
        _7 = False
        while True:
            if _6 == 2:
                _7 = True
                result = result + ']'
                break
            if (_7 is True) or (_6 == 3):
                _7 = True
                result = result + ']'
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        # FIXME Private method in OpenDSS
        self.setPropertyValue(0, '0')
        # number of points to expect
        self.setPropertyValue(1, '1')
        # default = 1.0 hr;
        self.setPropertyValue(2, '')
        # vector of multiplier values
        self.setPropertyValue(3, '')
        # vextor of hour values
        self.setPropertyValue(4, '0')
        # set the mean (otherwise computed)
        self.setPropertyValue(5, '0')
        # set the std dev (otherwise computed)
        self.setPropertyValue(6, '')
        # switch input to a csvfile
        self.setPropertyValue(7, '')
        # switch input to a binary file of singles
        self.setPropertyValue(8, '')
        # switch input to a binary file of singles
        self.setPropertyValue(9, '3600')
        # seconds
        self.setPropertyValue(10, '60')
        # minutes
        self.setPropertyValue(11, '')
        # action option
        super(TShapeObjImpl, self).initPropertyValues(TShape.NumPropsThisClass)

    def saveToDblFile(self):
        # FIXME Private method in OpenDSS
        raise self.UnsupportedOperationException()

    def saveToSngFile(self):
        raise self.UnsupportedOperationException()

    def setMean(self, value):
        self.stdDevCalculated = True
        self.mean.setValue(value)

    def setNumPoints(self, num):
        self.setPropertyValue(0, String.valueOf.valueOf(num))
        # update property list variable
        # reset array property values to keep them in proper order in save
        if self.arrayPropertyIndex >= 0:
            self.setPropertyValue(self.arrayPropertyIndex, self.getPropertyValue(self.arrayPropertyIndex))
        self.numPoints = num

    def setStdDev(self, stddev):
        self.stdDevCalculated = True
        self.stdDev.setValue(stddev)

    def getNumPoints(self):
        return 0

    def getInterval(self):
        return self.interval

    def getHours(self):
        return self.hours

    def setHours(self, values):
        self.hours = values

    def getTValues(self):
        return self.TValues

    def setTValues(self, values):
        # FIXME Private members in OpenDSS
        self.TValues = values

    def getLastValueAccessed(self):
        return self.lastValueAccessed

    def setLastValueAccessed(self, lastValue):
        self.lastValueAccessed = lastValue

    def getArrayPropertyIndex(self):
        return self.arrayPropertyIndex

    def setArrayPropertyIndex(self, index):
        self.arrayPropertyIndex = index

    def isStdDevCalculated(self):
        return self.stdDevCalculated

    def setStdDevCalculated(self, calculated):
        self.stdDevCalculated = calculated

    def setInterval(self, value):
        self.interval = value
