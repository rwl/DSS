from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.general.LoadShapeObj import (LoadShapeObj,)
from dss.general.LoadShape import (LoadShape,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LoadShapeObjImpl(DSSObjectImpl, LoadShapeObj):
    lastValueAccessed = None
    numPoints = None
    arrayPropertyIndex = None
    interval = None
    hours = None
    PMultipliers = None
    QMultipliers = None
    maxP = None
    maxQ = None
    useActual = None
    iMaxP = None
    stdDevCalculated = None
    mean = MutableDouble()
    stdDev = MutableDouble()

    def __init__(self, parClass, loadShapeName):
        super(LoadShapeObjImpl, self)(parClass)
        self.setName(loadShapeName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.lastValueAccessed = 1
        self.numPoints = 0
        self.interval = 1.0
        # hr
        self.hours = None
        self.PMultipliers = None
        self.QMultipliers = None
        self.maxP = 1.0
        self.maxQ = 0.0
        self.useActual = False
        self.stdDevCalculated = False
        # calculate on demand
        self.arrayPropertyIndex = 0
        self.initPropertyValues(0)

    def setResultIm(self, realpart):
        """Set imaginary part of Result when Qmultipliers not defined."""
        # if actual, assume zero, same as real otherwise
        return 0.0 if self.useActual else realpart

    def getMult(self, hr):
        """Get multiplier at specified time.

        This function returns a multiplier for the given hour.
        If no points exist in the curve, the result is 1.0.
        If there are fewer points than requested, the curve is simply assumed to repeat
        Thus a daily load curve can suffice for a yearly load curve:  You just get the
        same day over and over again.
        The value returned is the nearest to the interval requested.  Thus if you request
        hour=12.25 and the interval is 1.0, you will get interval 12.
        """
        # Complex Result = new Complex(1.0, 1.0);  // default return value if no points in curve
        result = [1.0, 1.0]
        if self.numPoints > 0:
            # handle exceptional cases
            if self.numPoints == 1:
                result[0] = self.PMultipliers[0]
                # TODO Check zero based indexing
                if self.QMultipliers is not None:
                    result[1] = self.QMultipliers[0]
                    # TODO Check zero based indexing
                else:
                    result[1] = self.setResultIm(result[0])
            elif self.interval > 0.0:
                index = self.Math.round(hr / self.interval)
                if index > self.numPoints:
                    index = index % self.numPoints
                    # wrap around using remainder
                if index == 0:
                    index = self.numPoints
                result[0] = self.PMultipliers[index]
                if self.QMultipliers is not None:
                    result[1] = self.QMultipliers[index]
                else:
                    result[1] = self.setResultIm(result[0])
            else:
                # for random interval
                # Start with previous value accessed under the assumption that most
                # of the time, this function will be called sequentially

                # Normalize Hr to max hour in curve to get wraparound
                if hr > self.hours[self.numPoints]:
                    hr = hr - ((hr / self.hours[self.numPoints]) * self.hours[self.numPoints])
                if self.hours[self.lastValueAccessed] > hr:
                    self.lastValueAccessed = 1
                    # Start over from beginning
                _0 = True
                i = self.lastValueAccessed + 1
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.numPoints):
                        break
                    # TODO Check zero based indexing
                    if self.Math.abs(self.hours[i] - hr) < 1.0000000000000002e-05:
                        # if close to an actual point, just use it.
                        result[0] = self.PMultipliers[i]
                        if self.QMultipliers is not None:
                            result[1] = self.QMultipliers[i]
                        else:
                            result[1] = self.setResultIm(result[0])
                        self.lastValueAccessed = i
                        return Complex(result[0], result[1])
                    elif self.hours[i] > hr:
                        # interpolate for multiplier
                        self.lastValueAccessed = i - 1
                        # TODO Check zero based indexing
                        result[0] = self.PMultipliers[self.lastValueAccessed] + (((hr - self.hours[self.lastValueAccessed]) / (self.hours[i] - self.hours[self.lastValueAccessed])) * (self.PMultipliers[i] - self.PMultipliers[self.lastValueAccessed]))
                        if self.QMultipliers is not None:
                            result[1] = self.QMultipliers[self.lastValueAccessed] + (((hr - self.hours[self.lastValueAccessed]) / (self.hours[i] - self.hours[self.lastValueAccessed])) * (self.QMultipliers[i] - self.QMultipliers[self.lastValueAccessed]))
                        else:
                            result[1] = self.setResultIm(result[0])
                        return Complex(result[0], result[1])
                # if we fall through the loop, just use last value
                self.lastValueAccessed = self.numPoints - 1
                result[0] = self.PMultipliers[self.numPoints]
                if self.QMultipliers is not None:
                    result[1] = self.QMultipliers[self.numPoints]
                else:
                    result[1] = self.setResultIm(result[0])
        return Complex(result[0], result[1])

    maxMult = None

    def doNormalize(self, multipliers):
        if self.numPoints > 0:
            self.maxMult = self.Math.abs(multipliers[0])
            # TODO Check zero based indexing
            _0 = True
            i = 1
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numPoints):
                    break
                # TODO Check zero based indexing
                self.maxMult = self.Math.max(self.maxMult, self.Math.abs(multipliers[i]))
            if self.maxMult == 0.0:
                self.maxMult = 1.0
                # avoid divide by zero
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.numPoints):
                    break
                multipliers[i] = multipliers[i] / self.maxMult

    def normalize(self):
        """Normalize the curve presently in memory."""
        self.doNormalize(self.PMultipliers)
        if self.QMultipliers is not None:
            self.doNormalize(self.QMultipliers)
        self.useActual = False
        # not likely that you would want to use the actual if you normalized it

    def calcMeanAndStdDev(self):
        if self.numPoints > 0:
            if self.interval > 0.0:
                MathUtil.RCDMeanandStdDev(self.PMultipliers, self.numPoints, self.mean, self.stdDev)
            else:
                MathUtil.curveMeanAndStdDev(self.PMultipliers, self.hours, self.numPoints, self.mean, self.stdDev)
            # TODO Check indenting
        self.propertyValue[4] = String.format.format('%.8g', self.mean.doubleValue())
        # TODO Check zero based indexing.
        self.propertyValue[5] = String.format.format('%.8g', self.stdDev.doubleValue())
        self.stdDevCalculated = True
        # No action is taken on Q multipliers

    def getInterval(self):
        if self.interval > 0.0:
            return self.interval
        elif self.lastValueAccessed > 1:
            # TODO Check zero based indexing
            return self.hours[self.lastValueAccessed] - self.hours[self.lastValueAccessed - 1]
        else:
            return 0.0

    def getMean(self):
        if not self.stdDevCalculated:
            self.calcMeanAndStdDev()
        return self.mean.doubleValue()

    def getStdDev(self):
        if not self.stdDevCalculated:
            self.calcMeanAndStdDev()
        return self.stdDev.doubleValue()

    def mult(self, i):
        """Get multiplier by index."""
        if i <= self.numPoints and i > 0:
            self.lastValueAccessed = i
            return self.PMultipliers[i]
        else:
            return 0.0

    def hour(self, i):
        """Get hour corresponding to point index."""
        if self.interval == 0:
            if (i <= self.numPoints) & (i > 0):
                self.lastValueAccessed = i
                return self.hours[i]
            else:
                return 0.0
        else:
            self.lastValueAccessed = i
            return self.hours[i] * self.interval

    def dumpProperties(self, f, complete):
        super(LoadShapeObjImpl, self).dumpProperties(f, complete)
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
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=(' + self.propertyValue[i] + ')'
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=(' + self.propertyValue[i] + ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.propertyValue[i]
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
                    result = result + String.format.format('%-g, ', self.PMultipliers[i])
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
            if (_3 is True) or (_2 == 10):
                _3 = True
                if self.QMultipliers is not None:
                    result = '('
                    _6 = True
                    i = 0
                    while True:
                        if _6 is True:
                            _6 = False
                        else:
                            i += 1
                        if not (i < self.numPoints):
                            break
                        result = result + String.format.format('%-g,', self.QMultipliers[i])
                    result = result + ')'
                break
            if (_3 is True) or (_2 == 11):
                _3 = True
                if self.useActual:
                    result = 'Yes'
                else:
                    result = 'No'
                break
            if (_3 is True) or (_2 == 12):
                _3 = True
                result = String.format.format('%.8g', self.maxP)
                break
            if (_3 is True) or (_2 == 13):
                _3 = True
                result = String.format.format('%.8g', self.maxQ)
                break
            if (_3 is True) or (_2 == 14):
                _3 = True
                result = String.format.format('%.8g', self.interval * 3600.0)
                break
            if (_3 is True) or (_2 == 15):
                _3 = True
                result = String.format.format('%.8g', self.interval * 60.0)
                break
            if True:
                _3 = True
                result = super(LoadShapeObjImpl, self).getPropertyValue(index)
                break
            break
        _7 = index
        _8 = False
        while True:
            if _7 == 3:
                _8 = True
                result = result + ')'
                break
            if (_8 is True) or (_7 == 4):
                _8 = True
                result = result + ')'
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        # TODO Implement TOPExport method
        # FIXME Private method in OpenDSS
        self.propertyValue[0] = '0'
        # number of points to expect
        self.propertyValue[1] = '1'
        # default = 1.0 hr;
        self.propertyValue[2] = ''
        # vector of multiplier values
        self.propertyValue[3] = ''
        # vector of hour values
        self.propertyValue[4] = '0'
        # set the mean (otherwise computed)
        self.propertyValue[5] = '0'
        # set the std dev (otherwise computed)
        self.propertyValue[6] = ''
        # switch input to a csvfile
        self.propertyValue[7] = ''
        # switch input to a binary file of singles
        self.propertyValue[8] = ''
        # switch input to a binary file of singles
        self.propertyValue[9] = ''
        # action option.
        self.propertyValue[10] = ''
        # Qmult.
        self.propertyValue[11] = 'No'
        self.propertyValue[12] = '0'
        self.propertyValue[13] = '0'
        self.propertyValue[14] = '3600'
        # seconds
        self.propertyValue[15] = '60'
        # minutes
        super(LoadShapeObjImpl, self).initPropertyValues(LoadShape.NumPropsThisClass)

    def saveToDblFile(self):
        # FIXME Implement this method
        # FIXME Private method in OpenDSS
        raise self.UnsupportedOperationException()

    def saveToSngFile(self):
        # FIXME Implement this method
        # FIXME Private method in OpenDSS
        raise self.UnsupportedOperationException()

    def setMaxPandQ(self):
        self.iMaxP = Utilities.iMaxAbsdblArrayValue(self.numPoints, self.PMultipliers)
        if self.iMaxP >= 0:
            # TODO Check zero based indexing
            self.maxP = self.PMultipliers[self.iMaxP]
            if self.QMultipliers is not None:
                self.maxQ = self.QMultipliers[self.iMaxP]
            else:
                self.maxQ = 0.0

    def setMean(self, value):
        self.stdDevCalculated = True
        self.mean.setValue(value)

    def setNumPoints(self, value):
        self.propertyValue[0] = String.valueOf.valueOf(value)
        # update property list variable
        # reset array property values to keep them in proper order in save
        if self.arrayPropertyIndex >= 0:
            # TODO Check zero based indexing
            self.propertyValue[self.arrayPropertyIndex] = self.propertyValue[self.arrayPropertyIndex]
        if self.QMultipliers is not None:
            self.propertyValue[10] = self.propertyValue[10]
            # TODO Check zero based indexing
        self.numPoints = value
        # now assign the value

    def setStdDev(self, stddev):
        self.stdDevCalculated = True
        self.stdDev.setValue(stddev)

    def getNumPoints(self):
        return self.numPoints

    def getHours(self):
        return self.hours

    def setHours(self, values):
        self.hours = values

    def getPMultipliers(self):
        return self.PMultipliers

    def setPMultipliers(self, values):
        self.PMultipliers = values

    def getQMultipliers(self):
        return self.QMultipliers

    def setQMultipliers(self, values):
        self.QMultipliers = values

    def setInterval(self, value):
        self.interval = value

    def getMaxP(self):
        return self.maxP

    def setMaxP(self, max):
        self.maxP = max

    def getMaxQ(self):
        return self.maxQ

    def setMaxQ(self, max):
        self.maxQ = max

    def isUseActual(self):
        return self.useActual

    def setUseActual(self, value):
        # Protected member in OpenDSS.
        self.useActual = value

    def isStdDevCalculated(self):
        return self.stdDevCalculated

    def setStdDevCalculated(self, calculated):
        # Private member in OpenDSS.
        self.stdDevCalculated = calculated

    def setArrayPropertyIndex(self, i):
        self.arrayPropertyIndex = i
