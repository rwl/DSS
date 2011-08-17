from dss.common.impl.Utilities import (Utilities,)
from dss.conversion.GICLine import (GICLine,)
from dss.conversion.GICLineObj import (GICLineObj,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)
# from org.apache.commons.math.complex.ComplexUtils import (ComplexUtils,)


class GICLineObjImpl(PCElementImpl, GICLineObj):
    angle = None
    volts = None
    VMag = None
    # present voltage magnitude
    srcFrequency = None
    R = None
    X = None
    C = None
    scanType = None
    sequenceType = None
    Z = None
    # base frequency series Z matrix
    ZInv = None

    def __init__(self, parClass, lineName):
        super(GICLineObjImpl, self)(parClass)
        self.setName(lineName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list
        self.setNPhases(3)
        self.nConds = 3
        self.setNTerms(2)
        # now a 2-terminal device
        self.Z = None
        self.ZInv = None
        # Basefrequency = 60.0;
        # set in base class
        self.R = 1.0
        self.X = 0.0
        self.C = 0.0
        self.srcFrequency = 0.1
        # typical GIC study frequency
        self.angle = 0.0
        self.scanType = 0
        self.sequenceType = 0
        # default to zero sequence (same voltage induced in all phases)
        self.spectrum = ''
        # no default
        self.initPropertyValues(0)
        self.YOrder = self.nTerms * self.nConds
        self.recalcElementData()

    def recalcElementData(self):
        if self.Z is not None:
            self.Z = None
        if self.ZInv is not None:
            self.ZInv = None
            # for a source, nPhases = nCond, for now
        self.Z = CMatrixImpl(self.nPhases)
        self.ZInv = CMatrixImpl(self.nPhases)
        # Update property value array
        # Don't change a specified value; only computed ones
        Zs = Complex(self.R, self.X)
        Zm = Complex.ZERO
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.Z.setElement(i, i, Zs)
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < i - 1):
                    break
                self.Z.setElemSym(i, j, Zm)
        self.VMag = self.volts
        self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
        if self.getSpectrumObj() is None and len(self.getSpectrum()) > 0:
            DSSGlobals.doSimpleMsg('Spectrum object \"' + self.getSpectrum() + '\" for device GICLine.' + self.getName() + ' not found.', 324)
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))

    def calcYPrim(self):
        # build only YPrim_Series
        if self.isYprimInvalid():
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            if self.YPrim is not None:
                self.YPrim = None
            self.YPrim = CMatrixImpl(self.YOrder)
        else:
            self.YPrimSeries.clear()
            self.YPrim.clear()
        self.YPrimFreq = DSSGlobals.activeCircuit.getSolution().getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        # Put in series RL adjusted for frequency
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.nPhases):
                    break
                value = self.Z.getElement(i, j)
                # modify from base freq
                value = Complex(value.getReal(), value.getImaginary() * freqMultiplier)
                self.ZInv.setElement(i, j, value)
        if self.C > 0.0:
            # add 1/wC into diagonals of Zinv
            Xc = -1.0 / (DSSGlobals.TWO_PI * self.YPrimFreq * self.C * 1e-06)
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                self.ZInv.addElement(i, i, Complex(0.0, Xc))
        self.ZInv.invert()
        # Invert in place
        if self.ZInv.getErrorCode() > 0:
            # If error, put in large series conductance
            DSSGlobals.doErrorMsg('GICLineObj.calcYPrim', 'Matrix inversion error for GICLine \"' + self.getName() + '\"', 'Invalid impedance specified. Replaced with small resistance.', 325)
            self.ZInv.clear()
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                self.ZInv.setElement(i, i, Complex(1.0 / DSSGlobals.EPSILON, 0.0))
        # YPrim_Series.CopyFrom(Zinv);
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < self.nPhases):
                    break
                value = self.ZInv.getElement(i, j)
                self.YPrimSeries.setElement(i, j, value)
                self.YPrimSeries.setElement(i + self.nPhases, j + self.nPhases, value)
                self.YPrimSeries.setElemSym(i + self.nPhases, j, value.negate())
        self.YPrim.copyFrom(self.YPrimSeries)
        # Now account for open conductors
        # For any conductor that is open, zero out row and column
        super(GICLineObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def getVterminalForSource(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        # This formulation will theoretically handle voltage sources of
        # any number of phases assuming they are equally displaced in
        # 			 * time.

        try:
            self.VMag = self.volts
            if sol.isHarmonicModel() and self.getSpectrumObj() is not None:
                srcHarmonic = sol.getFrequency() / self.srcFrequency
                # base voltage for this harmonic
                VHarm = self.getSpectrumObj().getMult(srcHarmonic).multiply(self.VMag)
                # rotate for phase 1 shift
                VHarm = Utilities.rotatePhasorDeg(VHarm, srcHarmonic, self.angle)
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.VTerminal[i] = VHarm
                    self.VTerminal[i + self.nPhases] = Complex.ZERO
                    if i < self.nPhases:
                        _1 = self.scanType
                        _2 = False
                        while True:
                            if _1 == 1:
                                _2 = True
                                VHarm = Utilities.rotatePhasorDeg(VHarm, 1.0, -360.0 / self.nPhases)
                                break
                            if (_2 is True) or (_1 == 0):
                                _2 = True
                                break
                            if True:
                                _2 = True
                                VHarm = Utilities.rotatePhasorDeg(VHarm, srcHarmonic, -360.0 / self.nPhases)
                                break
                            break
            else:
                # non-harmonic modes or no spectrum
                if self.Math.abs(sol.getFrequency() - self.srcFrequency) > DSSGlobals.EPSILON2:
                    self.VMag = 0.0
                    # solution frequency and source frequency don't match
                    # Note: Re-uses VTerminal space
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    _4 = self.sequenceType
                    _5 = False
                    while True:
                        if _4 == -1:
                            _5 = True
                            self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, 360.0 + self.angle + (((i - 1) * 360.0) / self.nPhases))
                            # neg seq
                            break
                        if (_5 is True) or (_4 == 0):
                            _5 = True
                            self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, 360.0 + self.angle)
                            # all the same for zero sequence
                            break
                        if True:
                            _5 = True
                            self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, (360.0 + self.angle) - (((i - 1) * 360.0) / self.nPhases))
                            break
                        break
                    self.VTerminal[i + self.nPhases] = Complex.ZERO
                    # see comments in getInjCurrents
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error computing Voltages for GICLine.' + self.getName() + '. Check specification. Aborting.', 326)
            if DSSGlobals.inRedirect:
                DSSGlobals.redirectAbort = True

    def injCurrents(self):
        self.getInjCurrents(self.getInjCurrent())
        # This is source injection
        return super(GICLineObjImpl, self).injCurrents()
        # add into system array

    def getCurrents(self, curr):
        sol = DSSGlobals.activeCircuit.getSolution()
        try:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                self.VTerminal[i] = sol.getNodeV()[self.nodeRef[i]]
            self.YPrim.vMult(curr, self.VTerminal)
            # current from elements in system Y
            self.getInjCurrents(self.complexBuffer)
            # get present value of inj currents
            # add together with Yprim currents
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                curr[i] = curr[i].subtract(self.complexBuffer[i])
        except Exception, e:
            DSSGlobals.doErrorMsg('getCurrents for Element: ' + self.getName() + '.', e.getMessage(), 'Inadequate storage allotted for circuit element.', 327)

    def getInjCurrents(self, curr):
        # Source injection currents given by this formula:
        #    _     _           _         _
        #    |Iinj1|           |GICLine  |
        #    |     | = [Yprim] |         |
        #    |Iinj2|           | 0       |
        #    _     _           _         _

        self.getVterminalForSource()
        # gets voltage vector above
        self.YPrim.vMult(curr, self.VTerminal)
        self.setITerminalUpdated(False)

    def dumpProperties(self, f, complete):
        super(GICLineObjImpl, self).dumpProperties(f, complete)
        pc = self.getParentClass()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < pc.getNumProperties()):
                break
            print '~ ' + pc.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print 
            print 'baseFrequency=' + self.baseFrequency
            print 'vMag=' + self.VMag
            print 'zMatrix='
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                _2 = True
                j = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    c = self.Z.getElement(i, j)
                    f.printf('%.8g +j %.8g ', c.getReal(), c.getImaginary())
                print 

    def initPropertyValues(self, arrayOffset):
        # PropertyValue allocated in DSSObject constructor
        self.setPropertyValue(0, self.getBus(0))
        self.setPropertyValue(1, self.getBus(1))
        self.setPropertyValue(2, '0.0')
        self.setPropertyValue(3, '0')
        self.setPropertyValue(4, '0.1')
        self.setPropertyValue(5, '3')
        self.setPropertyValue(6, '1.0')
        self.setPropertyValue(7, '0')
        self.setPropertyValue(8, '0')
        self.setPropertyValue(9, 'zero')
        self.setPropertyValue(10, 'zero')
        super(GICLineObjImpl, self).initPropertyValues(GICLine.NumPropsThisClass)

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                return self.getBus(0)
            if (_1 is True) or (_0 == 2):
                _1 = True
                return self.getBus(1)
            if True:
                _1 = True
                return super(GICLineObjImpl, self).getPropertyValue(index)
            break

    def makePosSequence(self):
        """Make a positive sequence model."""
        s = 'phases=1 '
        s = s + String.format.format('voltage=%-.8g angle=%=.5g', self.volts, self.angle)
        s = s + String.format.format('r=%-.8g ', self.R)
        s = s + String.format.format('x=%-.8g ', self.X)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(GICLineObjImpl, self).makePosSequence()

    def getZ(self):
        return self.Z

    def setZ(self, z):
        self.Z = z

    def getZInv(self):
        return self.ZInv

    def setZInv(self, zinv):
        # FIXME: Private members in OpenDSS
        self.ZInv = zinv

    def getAngle(self):
        return self.angle

    def setAngle(self, value):
        self.angle = value

    def getVolts(self):
        return self.volts

    def setVolts(self, value):
        self.volts = value

    def getVMag(self):
        return self.VMag

    def setVMag(self, vmag):
        self.VMag = vmag

    def getSrcFrequency(self):
        return self.srcFrequency

    def setSrcFrequency(self, frequency):
        self.srcFrequency = frequency

    def getR(self):
        return self.R

    def setR(self, r):
        self.R = r

    def getX(self):
        return self.X

    def setX(self, x):
        self.X = x

    def getC(self):
        return self.C

    def setC(self, c):
        self.C = c

    def getScanType(self):
        return self.scanType

    def setScanType(self, type):
        self.scanType = type

    def getSequenceType(self):
        return self.sequenceType

    def setSequenceType(self, type):
        self.sequenceType = type
