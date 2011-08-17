from dss.conversion.EquivalentObj import (EquivalentObj,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.conversion.Equivalent import (Equivalent,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from java.io.PrintStream import (PrintStream,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class EquivalentObjImpl(PCElementImpl, EquivalentObj):
    kVBase = None
    VMag = None
    perUnit = None
    angle = None
    equivFrequency = None
    R1 = None
    X1 = None
    R0 = None
    X0 = None
    needToDoRecalc = None
    # base frequency series Z matrix
    Z = None
    ZInv = None

    def __init__(self, parClass, sourceName):
        super(EquivalentObjImpl, self)(parClass)
        self.setName(sourceName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list
        self.setNPhases(3)
        self.nConds = 3
        self.setNTerms(1)
        self.Z = None
        self.ZInv = None
        # Basefrequency = 60.0;
        # set in base class
        self.R1 = None
        self.X1 = None
        self.R0 = None
        self.X0 = None
        self.reallocRX()
        self.R1[0] = 1.65
        self.X1[0] = 6.6
        self.R0[0] = 1.9
        self.X0[0] = 5.7
        self.kVBase = 115.0
        self.perUnit = 1.0
        self.equivFrequency = self.baseFrequency
        self.angle = 0.0
        self.spectrum = 'defaultvsource'
        self.initPropertyValues(0)
        self.YOrder = self.nTerms * self.nConds
        self.recalcElementData()

    def idx(self, a, b):
        return ((b - 1) * self.nTerms) + a

    def recalcElementData(self):
        # int i, j, ii, jj;
        if self.Z is not None:
            self.Z = None
        if self.ZInv is not None:
            self.ZInv = None
            # for a source, nPhases = nCond, for now
        self.Z = CMatrixImpl(self.nPhases * self.nTerms)
        self.ZInv = CMatrixImpl(self.nPhases * self.nTerms)
        # build Z matrix for all phases
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nTerms):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.nTerms):
                    break
                indx = self.idx(i, j)
                Zs = ComplexUtil.divide(Complex((2.0 * self.R1[indx]) + self.R0[indx], (2.0 * self.X1[indx]) + self.X0[indx]), 3.0)
                Zm = ComplexUtil.divide(Complex(self.R0[indx] - self.R1[indx], self.X0[indx] - self.X1[indx]), 3.0)
                iOffset = (i - 1) * self.nPhases
                jOffset = (j - 1) * self.nPhases
                _2 = True
                ii = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        ii += 1
                    if not (ii < self.nPhases):
                        break
                    _3 = True
                    jj = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            jj += 1
                        if not (jj < ii):
                            break
                        if ii == jj:
                            self.Z.setElement(ii + iOffset, jj + jOffset, Zs)
                        else:
                            self.Z.setElement(ii + iOffset, jj + jOffset, Zm)
                            self.Z.setElement(jj + iOffset, ii + jOffset, Zm)
                            # set other off-diagonal in this submatrix
            # voltage source properties
        _4 = self.nPhases
        _5 = False
        while True:
            if _4 == 1:
                _5 = True
                self.VMag = self.kVBase * self.perUnit * 1000.0
                break
            if True:
                _5 = True
                self.VMag = (self.kVBase * self.perUnit * 1000.0) / 2.0 / self.Math.sin(((180.0 / self.nPhases) * self.Math.PI) / 180.0)
                break
            break
        self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
        if self.getSpectrumObj() is None:
            DSSGlobals.doSimpleMsg('Spectrum object \"' + self.getSpectrum() + '\" for device equivalent.' + self.getName() + ' not found.', 802)
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))
        self.needToDoRecalc = False

    def calcYPrim(self):
        # build only YPrim series
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
        if self.needToDoRecalc:
            self.recalcElementData()
        self.YPrimFreq = DSSGlobals.activeCircuit.getSolution().getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        # Put in Series RL matrix adjusted for frequency
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.YOrder):
                    break
                value = self.Z.getElement(i, j)
                # Modify from base freq
                value = Complex(value.getReal(), value.getImaginary() * freqMultiplier)
                self.ZInv.setElement(i, j, value)
        self.ZInv.invert()
        # Invert in place
        if self.ZInv.getErrorCode() > 0:
            # If error, put in large series conductance
            DSSGlobals.doErrorMsg('EquivalentObj.calcYPrim', 'Matrix inversion error for equivalent \"' + self.getName() + '\"', 'Invalid impedance specified. Replaced with small resistance.', 803)
            self.ZInv.clear()
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                self.ZInv.setElement(i, i, Complex(1.0 / DSSGlobals.EPSILON, 0.0))
        self.YPrimSeries.copyFrom(self.ZInv)
        self.YPrim.copyFrom(self.YPrimSeries)
        # Now account for open conductors
        # For any conductor that is open, zero out row and column
        super(EquivalentObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def getVterminalForSource(self):
        # This formulation will theoretically handle voltage sources of any number
        # of phases assuming they are equally displaced in time.

        try:
            _0 = self.nPhases
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.VMag = self.kVBase * self.perUnit * 1000.0
                    break
                if True:
                    _1 = True
                    self.VMag = (self.kVBase * self.perUnit * 1000.0) / 2.0 / self.Math.sin(((180.0 / self.nPhases) * self.Math.PI) / 180.0)
                    break
                break
            sol = DSSGlobals.activeCircuit.getSolution()
            if sol.isHarmonicModel():
                equivHarm = sol.getFrequency() / self.equivFrequency
                VHarm = self.getSpectrumObj().getMult(equivHarm).multiply(self.VMag)
                # base voltage for this harmonic
                VHarm = Utilities.rotatePhasorDeg(VHarm, equivHarm, self.angle)
                # rotate for phase 1 shift
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.VTerminal[i] = VHarm
                    if i < self.nPhases:
                        VHarm = Utilities.rotatePhasorDeg(VHarm, equivHarm, -360.0 / self.nPhases)
            else:
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, (360.0 + self.angle) - (((i - 1) * 360.0) / self.nPhases))
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error computing voltages for Equivalent.' + self.getName() + '. Check specification. Aborting.', 804)
            if DSSGlobals.inRedirect:
                DSSGlobals.redirectAbort = True

    def injCurrents(self):
        self.getInjCurrents(self.getInjCurrent())
        return super(EquivalentObjImpl, self).injCurrents()
        # add into system array

    def getCurrents(self, curr):
        try:
            sol = DSSGlobals.activeCircuit.getSolution()
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
            DSSGlobals.doErrorMsg('GetCurrents for element: ' + self.getName() + '.', e.getMessage(), 'Inadequate storage allotted for circuit element.', 805)

    def getInjCurrents(self, curr):
        self.getVterminalForSource()
        self.YPrim.vMult(curr, self.VTerminal)
        # I = Y V
        self.setITerminalUpdated(False)

    def dumpProperties(self, f, complete):
        super(EquivalentObjImpl, self).dumpProperties(f, complete)
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
                    f.print_(c.getReal() + ' + j' + c.getImaginary())
                print 

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '1'
        self.propertyValue[1] = self.getBus(0)
        self.propertyValue[2] = '115'
        self.propertyValue[3] = '1'
        self.propertyValue[4] = '0'
        self.propertyValue[5] = '60'
        self.propertyValue[6] = '3'
        self.propertyValue[7] = '1.65'
        self.propertyValue[8] = '6.6'
        self.propertyValue[9] = '1.9'
        self.propertyValue[10] = '5.7'
        super(EquivalentObjImpl, self).initPropertyValues(Equivalent.NumPropsThisClass)

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return None
            if True:
                _1 = True
                return super(EquivalentObjImpl, self).getPropertyValue(index)
            break

    def makePosSequence(self):
        """Make a positive sequence model."""
        # Private method in OpenDSS
        s = 'Phases=1 '
        s = s + String.format.format('basekV=%-.5g ', self.kVBase / DSSGlobals.SQRT3)
        s = s + String.format.format('r1=%-.5g ', self.R1)
        s = s + String.format.format('x1=%-.5g ', self.X1)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(EquivalentObjImpl, self).makePosSequence()

    def doTerminalsDef(self, n):
        # Private method in OpenDSS
        result = self.nTerms
        if n != self.nTerms:
            if n > 0:
                self.reallocRX()
        return result

    def parseDblMatrix(self, mat):
        """Parse input string as an array."""
        Parser.getInstance().parseAsSymMatrix(self.nTerms, mat)

    def reallocRX(self):
        self.R1 = Utilities.resizeArray(self.R1, self.Math.pow(self.nTerms, 2))
        self.X1 = Utilities.resizeArray(self.X1, self.Math.pow(self.nTerms, 2))
        self.R0 = Utilities.resizeArray(self.R0, self.Math.pow(self.nTerms, 2))
        self.X0 = Utilities.resizeArray(self.X0, self.Math.pow(self.nTerms, 2))

    def getZ(self):
        return self.Z

    def setZ(self, z):
        self.Z = z

    def getZinv(self):
        return self.ZInv

    def setZInv(self, zinv):
        # FIXME Private members in OpenDSS
        self.ZInv = zinv

    def getKVBase(self):
        return self.kVBase

    def setKVBase(self, base):
        self.kVBase = base

    def getVMag(self):
        return self.VMag

    def setVMag(self, mag):
        self.VMag = mag

    def getPerUnit(self):
        return self.perUnit

    def setPerUnit(self, pu):
        self.perUnit = pu

    def getAngle(self):
        return self.angle

    def setAngle(self, ang):
        self.angle = ang

    def getEquivFrequency(self):
        return self.equivFrequency

    def setEquivFrequency(self, frequency):
        self.equivFrequency = frequency

    def getR1(self):
        return self.R1

    def setR1(self, r1):
        self.R1 = r1

    def getX1(self):
        return self.X1

    def setX1(self, x1):
        self.X1 = x1

    def getR0(self):
        return self.R0

    def setR0(self, r0):
        self.R0 = r0

    def getX0(self):
        return self.X0

    def setX0(self, x0):
        self.X0 = x0

    def isNeedToDoRecalc(self):
        return self.needToDoRecalc

    def setNeedToDoRecalc(self, value):
        self.needToDoRecalc = value
