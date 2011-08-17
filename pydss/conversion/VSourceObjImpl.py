from dss.common.impl.Utilities import (Utilities,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.VSourceObj import (VSourceObj,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.conversion.VSource import (VSource,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class VSourceObjImpl(PCElementImpl, VSourceObj):
    MVAsc3 = None
    MVAsc1 = None
    Isc3 = None
    Isc1 = None
    ZSpecType = None
    R1 = None
    X1 = None
    R0 = None
    X0 = None
    X1R1 = None
    X0R0 = None
    scanType = None
    sequenceType = None
    Z = None
    # base frequency series Z matrix
    ZInv = None
    VMag = None
    kVBase = None
    perUnit = None
    angle = None
    srcFrequency = None

    def __init__(self, parClass, sourceName):
        super(VSourceObjImpl, self)(parClass)
        self.setName(sourceName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # SOURCE + NON_PCPD_ELEM;  // don't want this in PC element list
        self.setNPhases(3)
        self.nConds = 3
        self.setNTerms(2)
        # now a 2-terminal device
        self.Z = None
        self.ZInv = None
        # Basefrequency = 60.0;  // set in base class
        self.MVAsc3 = 2000.0
        self.MVAsc1 = 2100.0
        self.ZSpecType = 1
        # default to MVAsc
        self.R1 = 1.65
        self.X1 = 6.6
        self.R0 = 1.9
        self.X0 = 5.7
        self.Isc3 = 10000.0
        self.Isc1 = 10540.0
        self.kVBase = 115.0
        self.X1R1 = 4.0
        self.X0R0 = 3.0
        self.perUnit = 1.0
        self.srcFrequency = self.baseFrequency
        self.angle = 0.0
        self.scanType = 1
        self.sequenceType = 1
        self.spectrum = 'defaultvsource'
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
        if self.nPhases == 1:
            factor = 1.0
        else:
            factor = DSSGlobals.SQRT3
        Rs = 0.0
        Rm = 0.0
        Xs = 0.1
        Xm = 0.0
        # Calculate the short circuit impedance and make all other spec types agree
        _0 = self.ZSpecType
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                self.X1 = self.Math.pow(self.kVBase, 2) / self.MVAsc3 / self.Math.sqrt(1.0 + (1.0 / self.Math.pow(self.X1R1, 2)))
                Xs = self.Math.pow(self.kVBase, 2) / self.MVAsc1 / self.Math.sqrt(1.0 + (1.0 / self.Math.pow(self.X0R0, 2)))
                # approx
                self.R1 = self.X1 / self.X1R1
                Xm = Xs - self.X1
                self.X0 = Xs + (2.0 * Xm)
                self.R0 = self.X0 / self.X0R0
                self.Isc3 = (self.MVAsc3 * 1000.0) / (DSSGlobals.SQRT3 * self.kVBase)
                self.Isc1 = (self.MVAsc1 * 1000.0) / (factor * self.kVBase)
                if self.nPhases == 1:
                    Rs = Xs / self.X0R0
                else:
                    Rs = ((2.0 * self.R1) + self.R0) / 3.0
                Rm = (self.R0 - self.R1) / 3.0
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                self.MVAsc3 = (DSSGlobals.SQRT3 * self.kVBase * self.Isc3) / 1000.0
                self.MVAsc1 = (factor * self.kVBase * self.Isc1) / 1000.0
                self.X1 = self.Math.pow(self.kVBase, 2) / self.MVAsc3 / self.Math.sqrt(1.0 + (1.0 / self.Math.pow(self.X1R1, 2)))
                Xs = self.Math.pow(self.kVBase, 2) / self.MVAsc1 / self.Math.sqrt(1.0 + (1.0 / self.Math.pow(self.X0R0, 2)))
                # approx
                self.R1 = self.X1 / self.X1R1
                Xm = Xs - self.X1
                self.X0 = Xs + (2.0 * Xm)
                self.R0 = self.X0 / self.X0R0
                if self.nPhases == 1:
                    Rs = Xs / self.X0R0
                else:
                    Rs = ((2.0 * self.R1) + self.R0) / 3.0
                Rm = (self.R0 - self.R1) / 3.0
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.Isc3 = (self.kVBase * 1000.0) / DSSGlobals.SQRT3 / Complex(self.R1, self.X1).abs()
                if self.nPhases == 1:
                    # force Z0 to be Z1 so Zs is same as Z1
                    self.R0 = self.R1
                    self.X0 = self.X1
                Rs = ((2.0 * self.R1) + self.R0) / 3.0
                Xs = ((2.0 * self.X1) + self.X0) / 3.0
                self.Isc1 = (self.kVBase * 1000.0) / factor / Complex(Rs, Xs).abs()
                self.MVAsc3 = (DSSGlobals.SQRT3 * self.kVBase * self.Isc3) / 1000.0
                self.MVAsc1 = (factor * self.kVBase * self.Isc1) / 1000.0
                Xm = Xs - self.X1
                Rs = ((2.0 * self.R1) + self.R0) / 3.0
                Rm = (self.R0 - self.R1) / 3.0
                break
            break
        # Update property value array
        # Don't change a specified value; only computed ones
        Zs = Complex(Rs, Xs)
        Zm = Complex(Rm, Xm)
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.Z.setElement(i, i, Zs)
            _3 = True
            j = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    j += 1
                if not (j < i - 1):
                    break
                self.Z.setElemSym(i, j, Zm)
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
            DSSGlobals.doSimpleMsg('Spectrum object \"' + self.getSpectrum() + '\" for device VSource.' + self.getName() + ' not found.', 324)
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
                value = Complex(value.getReal(), value.getImaginary() * freqMultiplier)
                # Modify from base freq
                self.ZInv.setElement(i, j, value)
        self.ZInv.invert()
        # Invert in place
        if self.ZInv.getErrorCode() > 0:
            # If error, put in large series conductance
            DSSGlobals.doErrorMsg('VsourceObj.calcYPrim', 'Matrix inversion error for VSource \"' + self.getName() + '\"', 'Invalid impedance specified. Replaced with small resistance.', 325)
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
        # YPrim_Series.copyFrom(Zinv);
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            _4 = True
            j = 0
            while True:
                if _4 is True:
                    _4 = False
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
        super(VSourceObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def getVTerminalForSource(self):
        # This formulation will theoretically handle voltage sources of any
        # number of phases assuming they are equally displaced in time.

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
                srcHarmonic = sol.getFrequency() / self.srcFrequency
                VHarm = self.getSpectrumObj().getMult(srcHarmonic).multiply(self.VMag)
                # base voltage for this harmonic
                VHarm = Utilities.rotatePhasorDeg(VHarm, srcHarmonic, self.angle)
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
                    self.VTerminal[i + self.nPhases] = Complex.ZERO
                    if i < self.nPhases:
                        _3 = self.scanType
                        _4 = False
                        while True:
                            if _3 == 1:
                                _4 = True
                                VHarm = Utilities.rotatePhasorDeg(VHarm, 1.0, -360.0 / self.nPhases)
                                # maintain pos seq
                                break
                            if (_4 is True) or (_3 == 0):
                                _4 = True
                                break
                            if True:
                                _4 = True
                                VHarm = Utilities.rotatePhasorDeg(VHarm, srcHarmonic, -360.0 / self.nPhases)
                                # normal rotation
                                break
                            break
            else:
                if self.Math.abs(sol.getFrequency() - self.srcFrequency) > DSSGlobals.EPSILON2:
                    self.VMag = 0.0
                    # solution frequency and source frequency don't match
                    # NOTE: RE-uses VTerminal space
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    _6 = self.sequenceType
                    _7 = False
                    while True:
                        if _6 == -1:
                            _7 = True
                            self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, 360.0 + self.angle + (((i - 1) * 360.0) / self.nPhases))
                            # neg seq
                            break
                        if (_7 is True) or (_6 == 0):
                            _7 = True
                            self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, 360.0 + self.angle)
                            # all the same for zero sequence
                            break
                        if True:
                            _7 = True
                            self.VTerminal[i] = ComplexUtil.polarDeg2Complex(self.VMag, (360.0 + self.angle) - (((i - 1) * 360.0) / self.nPhases))
                            break
                        break
                    self.VTerminal[i + self.nPhases] = Complex.ZERO
                    # see comments in getInjCurrents
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error computing voltages for VSource.' + self.getName() + '. Check specification. Aborting.', 326)
            if DSSGlobals.inRedirect:
                DSSGlobals.redirectAbort = True

    def injCurrents(self):
        self.getInjCurrents(self.getInjCurrent())
        # This is source injection
        return super(VSourceObjImpl, self).injCurrents()
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
            DSSGlobals.doErrorMsg('getCurrents for element: ' + self.getName() + '.', e.getMessage(), 'Inadequate storage allotted for circuit element.', 327)

    def getInjCurrents(self, curr):
        """Source injection currents given by this formula:
        		_     _           _         _
        		|Iinj1|           |Vsource  |
        		|     | = [Yprim] |         |
        		|Iinj2|           | 0       |
        		_     _           _         _
        """
        self.getVTerminalForSource()
        # gets voltage vector above
        self.YPrim.vMult(curr, self.VTerminal)
        self.setITerminalUpdated(False)

    def dumpProperties(self, f, complete):
        super(VSourceObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.propertyValue[i]
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
        self.propertyValue[1] = self.getBus(0)
        self.propertyValue[2] = '115'
        self.propertyValue[3] = '1'
        self.propertyValue[4] = '0'
        self.propertyValue[5] = String.format.format('%d', self.Math.round(DSSGlobals.defaultBaseFreq))
        self.propertyValue[6] = '3'
        self.propertyValue[7] = '2000'
        self.propertyValue[8] = '2100'
        self.propertyValue[9] = '4'
        self.propertyValue[10] = '3'
        self.propertyValue[11] = '10000'
        self.propertyValue[12] = '10500'
        self.propertyValue[13] = '1.65'
        self.propertyValue[14] = '6.6'
        self.propertyValue[15] = '1.9'
        self.propertyValue[16] = '5.7'
        self.propertyValue[17] = 'Pos'
        self.propertyValue[18] = 'Pos'
        self.propertyValue[19] = self.getBus(0)
        super(VSourceObjImpl, self).initPropertyValues(VSource.NumPropsThisClass)

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return self.getBus(0)
            if (_1 is True) or (_0 == 6):
                _1 = True
                return String.format.format('%-.5g', self.MVAsc3)
            if (_1 is True) or (_0 == 7):
                _1 = True
                return String.format.format('%-.5g', self.MVAsc1)
            if (_1 is True) or (_0 == 10):
                _1 = True
                return String.format.format('%-.5g', self.Isc3)
            if (_1 is True) or (_0 == 11):
                _1 = True
                return String.format.format('%-.5g', self.Isc1)
            if (_1 is True) or (_0 == 12):
                _1 = True
                return String.format.format('%-.5g', self.R1)
            if (_1 is True) or (_0 == 13):
                _1 = True
                return String.format.format('%-.5g', self.X1)
            if (_1 is True) or (_0 == 14):
                _1 = True
                return String.format.format('%-.5g', self.R0)
            if (_1 is True) or (_0 == 15):
                _1 = True
                return String.format.format('%-.5g', self.X0)
            if (_1 is True) or (_0 == 18):
                _1 = True
                return self.getBus(1)
            if True:
                _1 = True
                return super(VSourceObjImpl, self).getPropertyValue(index)
            break

    def makePosSequence(self):
        """Make a positive sequence model."""
        s = 'phases=1 '
        s = s + String.format.format('basekV=%-.5g ', self.kVBase / DSSGlobals.SQRT3)
        s = s + String.format.format('r1=%-.5g ', self.R1)
        s = s + String.format.format('x1=%-.5g ', self.X1)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(VSourceObjImpl, self).makePosSequence()

    def getZ(self):
        return self.Z

    def setZ(self, z):
        self.Z = z

    def getZinv(self):
        return self.ZInv

    def setZinv(self, zinv):
        self.ZInv = zinv

    def getVMag(self):
        return self.VMag

    def setVMag(self, mag):
        self.VMag = mag

    def getKVBase(self):
        return self.kVBase

    def setKVBase(self, base):
        self.kVBase = base

    def getPerUnit(self):
        return self.perUnit

    def setPerUnit(self, pu):
        self.perUnit = pu

    def getAngle(self):
        return self.angle

    def setAngle(self, ang):
        self.angle = ang

    def getSrcFrequency(self):
        return self.srcFrequency

    def setSrcFrequency(self, frequency):
        # FIXME Private members in OpenDSS
        self.srcFrequency = frequency

    def getMVAsc3(self):
        return self.MVAsc3

    def setMVAsc3(self, mvasc3):
        self.MVAsc3 = mvasc3

    def getMVAsc1(self):
        return self.MVAsc1

    def setMVAsc1(self, mvasc1):
        self.MVAsc1 = mvasc1

    def getIsc3(self):
        return self.Isc3

    def setIsc3(self, isc3):
        self.Isc3 = isc3

    def getIsc1(self):
        return self.Isc1

    def setIsc1(self, isc1):
        self.Isc1 = isc1

    def getZSpecType(self):
        return self.ZSpecType

    def setZSpecType(self, specType):
        self.ZSpecType = specType

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

    def getX1R1(self):
        return self.X1R1

    def setX1R1(self, x1r1):
        self.X1R1 = x1r1

    def getX0R0(self):
        return self.X0R0

    def setX0R0(self, x0r0):
        self.X0R0 = x0r0

    def getScanType(self):
        return self.scanType

    def setScanType(self, type):
        self.scanType = type

    def getSequenceType(self):
        return self.sequenceType

    def setSequenceType(self, type):
        self.sequenceType = type
