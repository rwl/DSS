from __pyjamas__ import (ARGERROR,)
from dss.general.SpectrumObj import (SpectrumObj,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.Spectrum import (Spectrum,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class SpectrumObjImpl(DSSObjectImpl, SpectrumObj):
    puMagArray = None
    angleArray = None
    multArray = None
    numHarm = None
    harmArray = None

    def __init__(self, parClass, spectrumName):
        super(SpectrumObjImpl, self)(parClass)
        self.setName(spectrumName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.numHarm = 0
        self.harmArray = None
        self.puMagArray = None
        self.angleArray = None
        self.multArray = None
        self.initPropertyValues(0)

    def dumpProperties(self, f, complete):
        super(SpectrumObjImpl, self).dumpProperties(f, complete)
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
                if _1 == 1:
                    _2 = True
                    f.print_('~ ' + self.parentClass.getPropertyName()[i] + '=(')
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < self.numHarm):
                            break
                        f.printf('%-g, ', self.harmArray[j])
                    print ')'
                    break
                if (_2 is True) or (_1 == 2):
                    _2 = True
                    f.print_('~ ' + self.parentClass.getPropertyName()[i] + '=(')
                    _4 = True
                    j = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            j += 1
                        if not (j < self.numHarm):
                            break
                        f.printf('%-g, ', self.puMagArray[j] * 100.0)
                    print ')'
                    break
                if (_2 is True) or (_1 == 3):
                    _2 = True
                    f.print_('~ ' + self.parentClass.getPropertyName()[i] + '=(')
                    _5 = True
                    j = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            j += 1
                        if not (j < self.numHarm):
                            break
                        f.printf('%-g, ', self.angleArray[j])
                    print ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
                    break
                break
        if complete:
            print 'Multiplier Array:'
            print 'Harmonic, Mult.re, Mult.im, Mag,  Angle'
            _6 = True
            i = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    i += 1
                if not (i < self.numHarm):
                    break
                f.printf('%-g, ', self.harmArray[i])
                f.printf('%-g, %-g, ', self.multArray[i].getReal(), self.multArray[i].getImaginary())
                f.printf('%-g, %-g', self.multArray[i].abs(), ComplexUtil.degArg(self.multArray[i]))
                print 

    def getMult(self, h):
        # Search list for harmonic (nearest 0.01 harmonic) and return multiplier
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numHarm):
                break
            if self.Math.abs(h - self.harmArray[i]) < 0.01:
                return self.multArray[i]
                # None found, return zero
        return Complex.ZERO

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
            if _2 == 0:
                _3 = True
                result = String.valueOf.valueOf(self.numHarm)
                break
            if (_3 is True) or (_2 == 1):
                _3 = True
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.numHarm):
                        break
                    result = result + String.format.format('%-g, ', self.harmArray[i])
                break
            if (_3 is True) or (_2 == 2):
                _3 = True
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.numHarm):
                        break
                    result = result + String.format.format('%-g, ', self.puMagArray[i] * 100.0)
                break
            if (_3 is True) or (_2 == 3):
                _3 = True
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < self.numHarm):
                        break
                    result = result + String.format.format('%-g, ', self.angleArray[i])
                break
            if True:
                _3 = True
                result = super(SpectrumObjImpl, self).getPropertyValue(index)
                break
            break
        _7 = index
        _8 = False
        while True:
            if _7 == 1:
                _8 = True
                result = ')'
                break
            if (_8 is True) or (_7 == 2):
                _8 = True
                result = ')'
                break
            if (_8 is True) or (_7 == 3):
                _8 = True
                result = ')'
                break
            break
        return result

    def initPropertyValues(self, arrayOffset):
        # FIXME Private method in OpenDSS
        self.propertyValue[0] = '0'
        self.propertyValue[1] = ''
        self.propertyValue[2] = ''
        self.propertyValue[3] = ''
        self.propertyValue[4] = ''
        super(SpectrumObjImpl, self).initPropertyValues(Spectrum.NumPropsThisClass)

    def setMultArray(self, *args):
        """Rotate all phase angles so that the fundamental is at zero."""
        _0 = args
        _1 = len(args)
        if _1 == 0:
            pass # astStmt: [Stmt([]), 162]
            pass # astStmt: [Stmt([]), 163]
            try:
                fundAngle = 0.0
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.numHarm):
                        break
                    if self.Math.round(self.harmArray[i]) == 1:
                        fundAngle = self.angleArray[i]
                        break
                self.multArray = Utilities.resizeArray(self.multArray, self.numHarm)
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.numHarm):
                        break
                    self.multArray[i] = ComplexUtil.polarDeg2Complex(self.puMagArray[i], self.angleArray[i] - (self.harmArray[i] * fundAngle))
            except Exception, e:
                DSSGlobals.doSimpleMsg('Exception while computing spectrum.' + self.getName() + '. Check Definition. Aborting', 655)
                if DSSGlobals.inRedirect:
                    DSSGlobals.redirectAbort = True
        elif _1 == 1:
            array, = _0
            self.multArray = array
        else:
            raise ARGERROR(0, 1)

    def getNumHarm(self):
        return self.numHarm

    def setNumHarm(self, num):
        self.numHarm = num

    def getHarmArray(self):
        return self.harmArray

    def setHarmArray(self, array):
        # FIXME Private members in OpenDSS
        self.harmArray = array

    def getPUMagArray(self):
        return self.puMagArray

    def setPUMagArray(self, array):
        self.puMagArray = array

    def getAngleArray(self):
        return self.angleArray

    def setAngleArray(self, array):
        self.angleArray = array

    def getMultArray(self):
        return self.multArray
