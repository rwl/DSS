from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.ReactorObj import (ReactorObj,)
from dss.delivery.impl.PDElementImpl import (PDElementImpl,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.Reactor import (Reactor,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from java.io.PrintStream import (PrintStream,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class ReactorObjImpl(PDElementImpl, ReactorObj):
    R = None
    Rp = None
    Gp = None
    X = None
    kVArRating = None
    kVRating = None
    # If not null then overrides C
    RMatrix = None
    GMatrix = None
    XMatrix = None
    BMatrix = None
    connection = None
    # 0 or 1 for wye (default) or delta, respectively
    specType = None
    # 1=kVAr, 2=r+jx, 3=r and x matrices
    isParallel = None
    RpSpecified = None

    def __init__(self, parClass, reactorName):
        super(ReactorObjImpl, self)(parClass)
        self.setName(reactorName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(2)
        # force allocation of terminals and conductors
        self.setBus(2, self.getBus(0) + '.0.0.0')
        # default to grounded wye
        self.isShunt = True
        self.RMatrix = None
        self.XMatrix = None
        self.GMatrix = None
        self.BMatrix = None
        self.kVArRating = 100.0
        self.kVRating = 12.47
        self.X = (self.Math.pow(self.kVRating, 2) * 1000.0) / self.kVArRating
        self.R = 0.0
        self.Rp = 0.0
        # indicates it has not been set to a proper value
        self.isParallel = False
        self.RpSpecified = False
        self.connection = 0
        # 0 or 1 for wye (default) or delta, respectively
        self.specType = 1
        # 1=kVAr, 2=Cuf, 3=CMatrix
        self.normAmps = (self.kVArRating * DSSGlobals.SQRT3) / self.kVRating
        self.emergAmps = self.getNormAmps() * 1.35
        self.faultRate = 0.0005
        self.pctPerm = 100.0
        self.hrsToRepair = 3.0
        self.YOrder = self.nTerms * self.nConds
        self.recalcElementData()
        self.initPropertyValues(0)

    def recalcElementData(self):
        checkError = MutableInt()
        _0 = self.specType
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                kVArPerPhase = self.kVArRating / self.nPhases
                _2 = self.connection
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        phaseKV = self.kVRating
                        break
                    if True:
                        _3 = True
                        _4 = self.nPhases
                        _5 = False
                        while True:
                            if _4 == 2:
                                _5 = True
                                phaseKV = self.kVRating / DSSGlobals.SQRT3
                                # assume three phase system
                                break
                            if (_5 is True) or (_4 == 3):
                                _5 = True
                                phaseKV = self.kVRating / DSSGlobals.SQRT3
                                break
                            if True:
                                _5 = True
                                phaseKV = self.kVRating
                                break
                            break
                        break
                    break
                self.X = (self.Math.pow(phaseKV, 2) * 1000.0) / kVArPerPhase
                # Leave r as specified
                self.setNormAmps(kVArPerPhase / phaseKV)
                self.setEmergAmps(self.getNormAmps() * 1.35)
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                break
            break
        if self.RpSpecified and self.Rp != 0.0:
            self.Gp = 1.0 / self.Rp
        else:
            self.Gp = 0.0
            # default to 0,0 if Rp=0;
        if self.isParallel and self.specType == 3:
            self.GMatrix = Utilities.resizeArray(self.GMatrix, self.nPhases * self.nPhases)
            self.BMatrix = Utilities.resizeArray(self.BMatrix, self.nPhases * self.nPhases)
            # Copy rMatrix to gMatrix and invert
            _6 = True
            i = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    i += 1
                if not (i < self.nPhases * self.nPhases):
                    break
                self.GMatrix[i] = self.RMatrix[i]
            MathUtil.ETKInvert(self.RMatrix, self.nPhases, checkError)
            if checkError.intValue() > 0:
                # TODO Check zero based indexing
                DSSGlobals.doSimpleMsg('Error inverting R matrix for Reactor.' + self.getName() + ' - G is zeroed.', 232)
                _7 = True
                i = 0
                while True:
                    if _7 is True:
                        _7 = False
                    else:
                        i += 1
                    if not (i < self.nPhases * self.nPhases):
                        break
                    self.GMatrix[i] = 0.0
            # Copy xMatrix to bMatrix and invert
            _8 = True
            i = 0
            while True:
                if _8 is True:
                    _8 = False
                else:
                    i += 1
                if not (i < self.nPhases * self.nPhases):
                    break
                self.BMatrix[i] = -self.XMatrix[i]
                MathUtil.ETKInvert(self.BMatrix, self.nPhases, checkError)
                if checkError.intValue() > 0:
                    DSSGlobals.doSimpleMsg('Error inverting X matrix for Reactor.' + self.getName() + ' - B is zeroed.', 233)
                    _9 = True
                    i = 0
                    while True:
                        if _9 is True:
                            _9 = False
                        else:
                            i += 1
                        if not (i < self.nPhases * self.nPhases):
                            break
                        self.GMatrix[i] = 0.0

    def calcYPrim(self):
        # normally build only Yprim_Shunt, but if there are 2 terminals and
        # bus1 != bus2
        if self.isYprimInvalid():
            # reallocate YPrim if something has invalidated old allocation
            if self.YPrimShunt is not None:
                self.YPrimShunt = None
            self.YPrimShunt = CMatrixImpl(self.YOrder)
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            if self.YPrim is not None:
                self.YPrim = None
            self.YPrim = CMatrixImpl(self.YOrder)
        else:
            self.YPrimSeries.clear()
            # zero out YPrim
            self.YPrimShunt.clear()
            # zero out YPrim
            self.YPrim.clear()
        if self.isShunt():
            YPrimTemp = self.YPrimShunt
        else:
            YPrimTemp = self.YPrimSeries
        self.YPrimFreq = DSSGlobals.activeCircuit.getSolution().getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        # Now, put in Yprim matrix
        _0 = self.specType
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                value = ComplexUtil.invert(Complex(self.R, self.X * freqMultiplier))
                # add in rP value if specified
                if self.RpSpecified:
                    value = value.add(Complex(self.Gp, 0.0))
                _2 = self.connection
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        value2 = value.multiply(2.0)
                        value = value.negate()
                        _4 = True
                        i = 0
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            YPrimTemp.setElement(i, i, value2)
                            _5 = True
                            j = 0
                            while True:
                                if _5 is True:
                                    _5 = False
                                else:
                                    j += 1
                                if not (j < i - 1):
                                    break
                                # TODO Check zero based indexing
                                YPrimTemp.setElemSym(i, j, value)
                            # remainder of the matrix is all zero
                        break
                    if True:
                        _3 = True
                        _6 = True
                        i = 0
                        while True:
                            if _6 is True:
                                _6 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            YPrimTemp.setElement(i, i, value)
                            # elements are only on the diagonals
                            YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                            YPrimTemp.setElemSym(i, i + self.nPhases, value.negate())
                        break
                    break
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                value = ComplexUtil.invert(Complex(self.R, self.X * freqMultiplier))
                # add in rP value if specified
                if self.RpSpecified:
                    value = value.add(Complex(self.Gp, 0.0))
                _7 = self.connection
                _8 = False
                while True:
                    if _7 == 1:
                        _8 = True
                        value2 = value.multiply(2.0)
                        value = value.negate()
                        _9 = True
                        i = 0
                        while True:
                            if _9 is True:
                                _9 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            YPrimTemp.setElement(i, i, value2)
                            _10 = True
                            j = 0
                            while True:
                                if _10 is True:
                                    _10 = False
                                else:
                                    j += 1
                                if not (j < i - 1):
                                    break
                                # TODO Check zero based indexing
                                YPrimTemp.setElemSym(i, j, value)
                            # remainder of the matrix is all zero
                        break
                    if True:
                        _8 = True
                        _11 = True
                        i = 0
                        while True:
                            if _11 is True:
                                _11 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            YPrimTemp.setElement(i, i, value)
                            # elements are only on the diagonals
                            YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                            YPrimTemp.setElemSym(i, i + self.nPhases, value.negate())
                        break
                    break
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                if self.isParallel:
                    # build Z as a Y matrix
                    _12 = True
                    i = 0
                    while True:
                        if _12 is True:
                            _12 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        _13 = True
                        j = 0
                        while True:
                            if _13 is True:
                                _13 = False
                            else:
                                j += 1
                            if not (j < self.nPhases):
                                break
                            idx = ((j - 1) * self.nPhases) + i
                            # TODO Check zero based indexing
                            value = Complex(self.GMatrix[idx], self.BMatrix[idx] / freqMultiplier)
                            YPrimTemp.setElement(i, j, value)
                            YPrimTemp.setElement(i + self.nPhases, j + self.nPhases, value)
                            YPrimTemp.setElemSym(i, j + self.nPhases, value.negate())
                else:
                    # for series r and x
                    ZMatrix = CMatrixImpl(self.nPhases)
                    ZValues = ZMatrix.asArray()
                    # so we can populate array fast
                    self.nPhases = ZMatrix.order()
                    # Put in series r & l
                    _14 = True
                    i = 0
                    while True:
                        if _14 is True:
                            _14 = False
                        else:
                            i += 1
                        if not (i < self.nPhases * self.nPhases):
                            break
                        # correct the impedances for frequency
                        ZValues[i] = Complex(self.RMatrix[i], self.XMatrix[i] * freqMultiplier)
                    ZMatrix.invert()
                    # Invert in place - is now Y matrix
                    if ZMatrix.getErrorCode() > 0:
                        # If error, put in tiny series conductance
                        DSSGlobals.doErrorMsg('ReactorObj.calcYPrim()', 'Matrix inversion error for reactor \"' + self.getName() + '\"', 'Invalid impedance specified. Replaced with tiny conductance.', 234)
                        ZMatrix.clear()
                        _15 = True
                        i = 0
                        while True:
                            if _15 is True:
                                _15 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            ZMatrix.setElement(i, i, Complex(DSSGlobals.EPSILON, 0.0))
                        _16 = True
                        i = 0
                        while True:
                            if _16 is True:
                                _16 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            _17 = True
                            j = 0
                            while True:
                                if _17 is True:
                                    _17 = False
                                else:
                                    j += 1
                                if not (j < self.nPhases):
                                    break
                                value = ZMatrix.getElement(i, j)
                                YPrimTemp.setElement(i, j, value)
                                YPrimTemp.setElement(i + self.nPhases, j + self.nPhases, value)
                                YPrimTemp.setElemSym(i, j + self.nPhases, value.negate())
                        ZMatrix = None
                break
            break
        # set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
        if self.isShunt():
            if self.nPhases == 1 and not DSSGlobals.activeCircuit.isPositiveSequence():
                # assume a neutral or grounding reactor; leave diagonal in the circuit
                _18 = True
                i = 0
                while True:
                    if _18 is True:
                        _18 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    self.YPrimSeries.setElement(i, i, self.YPrimShunt.getElement(i, i))
            else:
                _19 = True
                i = 0
                while True:
                    if _19 is True:
                        _19 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    self.YPrimSeries.setElement(i, i, self.YPrimShunt.getElement(i, i).multiply(1e-10))
        self.YPrim.copyFrom(YPrimTemp)
        self.calcYPrim()
        self.setYPrimInvalid(False)

    def dumpProperties(self, f, complete):
        super(ReactorObjImpl, self).dumpProperties(f, complete)
        _0 = True
        k = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                k += 1
            if not (k < self.parentClass.getNumProperties()):
                break
            _1 = k
            _2 = False
            while True:
                if _1 == 6:
                    _2 = True
                    if self.RMatrix is not None:
                        f.print_(self.parentClass.getPropertyName()[k] + '= (')
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
                                if not (j < i):
                                    break
                                f.printf('%-.5g', self.RMatrix[((i - 1) * self.nPhases) + j] + ' ')
                            if i != self.nPhases:
                                f.print_('|')
                        print ')'
                    break
                if (_2 is True) or (_1 == 7):
                    _2 = True
                    if self.XMatrix is not None:
                        f.print_(self.parentClass.getPropertyName()[k] + '= (')
                        _5 = True
                        i = 0
                        while True:
                            if _5 is True:
                                _5 = False
                            else:
                                i += 1
                            if not (i < self.nPhases):
                                break
                            _6 = True
                            j = 0
                            while True:
                                if _6 is True:
                                    _6 = False
                                else:
                                    j += 1
                                if not (j < i):
                                    break
                                f.printf('%-.5g', self.XMatrix[((i - 1) * self.nPhases) + j] + ' ')
                            if i != self.nPhases:
                                f.print_('|')
                        print ')'
                    else:
                        print '~ ' + self.parentClass.getPropertyName()[k] + '=' + self.getPropertyValue(k)
                    break
                break

    def getLosses(self, totalLosses, loadLosses, noLoadLosses):
        # Only report no load losses if Rp defined and reactor is a shunt device;
        # else do default behavior.

        if self.RpSpecified and self.isShunt() and self.Rp != 0.0:
            cTotalLosses = self.getLosses()
            # side effect: computes iTerminal and vTerminal
            # Compute losses in Rp branch from voltages across shunt element -- node to ground
            cNoLoadLosses = Complex.ZERO
            sol = DSSGlobals.activeCircuit.getSolution()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                V = sol.getNodeV()[self.nodeRef[i]]
                cNoLoadLosses = cNoLoadLosses.add(Complex((self.Math.pow(V.getReal(), 2) + self.Math.pow(V.getImaginary(), 2)) / self.Rp, 0.0))
                # V^2/Rp
            if DSSGlobals.activeCircuit.isPositiveSequence():
                cNoLoadLosses = cNoLoadLosses.multiply(3.0)
            cLoadLosses = cTotalLosses.subtract(cNoLoadLosses)
            # subtract no load losses from total losses
            # handle pass by reference
            totalLosses[0] = cTotalLosses.getReal()
            totalLosses[1] = cTotalLosses.getImaginary()
            loadLosses[0] = cLoadLosses.getReal()
            loadLosses[1] = cLoadLosses.getImaginary()
            noLoadLosses[0] = cNoLoadLosses.getReal()
            noLoadLosses[1] = cNoLoadLosses.getImaginary()
        else:
            # do the default CktElement behaviors
            super(ReactorObjImpl, self).getLosses(totalLosses, loadLosses, noLoadLosses)

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[1] = self.getBus(0)
        self.propertyValue[2] = self.getBus(1)
        self.propertyValue[3] = '3'
        self.propertyValue[4] = '1200'
        self.propertyValue[5] = '12.47'
        self.propertyValue[6] = 'wye'
        self.propertyValue[7] = ''
        self.propertyValue[8] = ''
        self.propertyValue[9] = 'NO'
        # parallel
        self.propertyValue[10] = '0'
        # r series
        self.propertyValue[11] = String.format.format('%-.6g', self.X)
        # X
        self.propertyValue[12] = '0'
        # Rp
        super(ReactorObjImpl, self).initPropertyValues(Reactor.NumPropsThisClass)
        # override inherited properties
        self.propertyValue[Reactor.NumPropsThisClass + 1] = String.valueOf.valueOf(self.getNormAmps())
        # TODO Check zero based indexing
        self.propertyValue[Reactor.NumPropsThisClass + 2] = String.valueOf.valueOf(self.getEmergAmps())
        self.propertyValue[Reactor.NumPropsThisClass + 3] = String.valueOf.valueOf(self.getFaultRate())
        self.propertyValue[Reactor.NumPropsThisClass + 4] = String.valueOf.valueOf(self.getPctPerm())
        self.propertyValue[Reactor.NumPropsThisClass + 5] = String.valueOf.valueOf(self.getHrsToRepair())
        self.clearPropSeqArray()

    def makePosSequence(self):
        s = ''
        if self.nPhases > 1:
            _0 = self.specType
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    kVArPerPhase = self.kVArRating / self.nPhases
                    if (self.nPhases > 1) or (self.connection != 0):
                        phaseKV = self.kVRating / DSSGlobals.SQRT3
                    else:
                        phaseKV = self.kVRating
                    s = 'Phases=1 ' + String.format.format(' kV=%-.5g kvar=%-.5g', phaseKV, kVArPerPhase)
                    # Leave r as specified
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    s = 'Phases=1 '
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    s = 'Phases=1 '
                    # r1
                    Rs = 0.0
                    # avg self
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        Rs = Rs + self.RMatrix[((i - 1) * self.nPhases) + i]
                        # TODO Check zero based indexing
                    Rs = Rs / self.nPhases
                    Rm = 0.0
                    # avg mutual
                    _3 = True
                    i = 1
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        _4 = True
                        j = i
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                j += 1
                            if not (j < self.nPhases):
                                break
                            Rm = Rm + self.RMatrix[((i - 1) * self.nPhases) + j]
                    Rm = Rm / (self.nPhases * (self.nPhases - 1.0)) / 2.0
                    s = s + String.format.format(' R=%-.5g', Rs - Rm)
                    # x1
                    Rs = 0.0
                    # avg self
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        Rs = Rs + self.XMatrix[((i - 1) * self.nPhases) + i]
                    Rs = Rs / self.nPhases
                    Rm = 0.0
                    # avg mutual
                    _6 = True
                    i = 1
                    while True:
                        if _6 is True:
                            _6 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        _7 = True
                        j = i
                        while True:
                            if _7 is True:
                                _7 = False
                            else:
                                j += 1
                            if not (j < self.nPhases):
                                break
                            Rm = Rm + self.XMatrix[((i - 1) * self.nPhases) + j]
                    Rm = Rm / (self.nPhases * (self.nPhases - 1.0)) / 2.0
                    s = s + String.format.format(' X=%-.5g', Rs - Rm)
                    break
                break
            Parser.getInstance().setCmdString(s)
            self.edit()
        super(ReactorObjImpl, self).makePosSequence()

    def getR(self):
        return self.R

    def setR(self, r):
        self.R = r

    def getRp(self):
        return self.Rp

    def setRp(self, rp):
        self.Rp = rp

    def getGp(self):
        return self.Gp

    def setGp(self, gp):
        self.Gp = gp

    def getX(self):
        return self.X

    def setX(self, x):
        self.X = x

    def getKVArRating(self):
        return self.kVArRating

    def setKVArRating(self, kvarrating):
        self.kVArRating = kvarrating

    def getKVRating(self):
        return self.kVRating

    def setKVRating(self, kvrating):
        self.kVRating = kvrating

    def getRMatrix(self):
        return self.RMatrix

    def setRMatrix(self, rmatrix):
        self.RMatrix = rmatrix

    def getGMatrix(self):
        return self.GMatrix

    def setGMatrix(self, gmatrix):
        self.GMatrix = gmatrix

    def getXMatrix(self):
        return self.XMatrix

    def setXMatrix(self, xmatrix):
        self.XMatrix = xmatrix

    def getBMatrix(self):
        return self.BMatrix

    def setBMatrix(self, bmatrix):
        self.BMatrix = bmatrix

    def getConnection(self):
        return self.connection

    def setConnection(self, conn):
        self.connection = conn

    def getSpecType(self):
        return self.specType

    def setSpecType(self, type):
        self.specType = type

    def isParallel(self):
        return self.isParallel

    def setParallel(self, parallel):
        self.isParallel = parallel

    def isRpSpecified(self):
        return self.RpSpecified

    def setRpSpecified(self, specified):
        self.RpSpecified = specified
