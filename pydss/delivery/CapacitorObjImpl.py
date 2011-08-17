from __pyjamas__ import (ARGERROR,)
from dss.delivery.CapacitorObj import (CapacitorObj,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.impl.PDElementImpl import (PDElementImpl,)
from dss.delivery.Capacitor import (Capacitor,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.CMatrix import (CMatrix,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class CapacitorObjImpl(PDElementImpl, CapacitorObj):
    C = None
    XL = None
    kVArRating = None
    R = None
    harm = None
    # single C per phase (line rating) if Cmatrix not specified
    states = None
    totalKVAr = None
    kVRating = None
    numSteps = None
    lastStepInService = None
    CMatrix = None
    # if not nil then overrides C
    doHarmonicRecalc = None
    specType = None
    # 0 or 1 for wye (default) or delta, respectively
    connection = None

    def __init__(self, parClass, capacitorName):
        super(CapacitorObjImpl, self)(parClass)
        self.setName(capacitorName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(2)
        # force allocation of terminals and conductors
        self.setBus(1, self.getBus(0) + '.0.0.0')
        # default to grounded wye
        self.isShunt = True
        # defaults to shunt capacitor
        CMatrix = None
        # Initialize these pointers to nil so reallocmem will work reliably.
        self.C = None
        self.XL = None
        self.kVArRating = None
        self.R = None
        self.harm = None
        self.states = None
        self.setNumSteps(1)
        # initial allocation for the arrays, too
        self.lastStepInService = self.numSteps
        Utilities.initDblArray(self.numSteps, self.R, 0.0)
        Utilities.initDblArray(self.numSteps, self.XL, 0.0)
        Utilities.initDblArray(self.numSteps, self.harm, 0.0)
        Utilities.initDblArray(self.numSteps, self.kVArRating, 1200.0)
        self.states[0] = 1
        self.kVRating = 12.47
        Utilities.initDblArray(self.numSteps, self.C, 1.0 / (DSSGlobals.TWO_PI * self.baseFrequency * self.Math.pow(self.kVRating, 2) * 1000.0) / self.kVArRating[0])
        self.connection = 0
        # 0 or 1 for wye (default) or delta, respectively
        self.specType = 1
        # 1=kvar, 2=Cuf, 3=Cmatrix
        self.normAmps = ((self.kVArRating[0] * DSSGlobals.SQRT3) / self.kVRating) * 1.35
        # 135%
        self.emergAmps = (self.getNormAmps() * 1.8) / 1.35
        # 180%
        self.faultRate = 0.0005
        self.pctPerm = 100.0
        self.hrsToRepair = 3.0
        self.YOrder = self.nTerms * self.nConds
        self.doHarmonicRecalc = False
        self.recalcElementData()
        self.initPropertyValues(0)

    def recalcElementData(self):
        self.totalKVAr = 0.0
        phaseKV = 1.0
        w = DSSGlobals.TWO_PI * self.baseFrequency
        _0 = self.specType
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
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
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < self.numSteps):
                        break
                    self.C[i] = 1.0 / (w * self.Math.pow(phaseKV, 2) * 1000.0) / self.kVArRating[0] / self.nPhases
                _7 = True
                i = 0
                while True:
                    if _7 is True:
                        _7 = False
                    else:
                        i += 1
                    if not (i < self.numSteps):
                        break
                    self.totalKVAr = self.totalKVAr + self.kVArRating[i]
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                _8 = self.connection
                _9 = False
                while True:
                    if _8 == 1:
                        _9 = True
                        phaseKV = self.kVRating
                        break
                    if True:
                        _9 = True
                        _10 = self.nPhases
                        _11 = False
                        while True:
                            if _10 == 2:
                                _11 = True
                                phaseKV = self.kVRating / DSSGlobals.SQRT3
                                # assume three phase system
                                break
                            if (_11 is True) or (_10 == 3):
                                _11 = True
                                phaseKV = self.kVRating / DSSGlobals.SQRT3
                                break
                            if True:
                                _11 = True
                                phaseKV = self.kVRating
                                break
                            break
                        break
                    break
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < self.numSteps):
                        break
                    self.totalKVAr = self.totalKVAr + ((w * self.C[i] * self.Math.pow(phaseKV, 2)) / 1000.0)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                break
            break
        if self.doHarmonicRecalc:
            # if harmonic specified, compute filter reactance
            _13 = True
            i = 0
            while True:
                if _13 is True:
                    _13 = False
                else:
                    i += 1
                if not (i < self.numSteps):
                    break
                if self.harm[i] != 0.0:
                    self.XL[i] = 1.0 / (w * self.C[i]) / self.Math.pow(self.harm[i], 2)
                else:
                    self.XL[i] = 0.0
                    # assume 0 harmonic means no filter
                if self.R[i] == 0.0:
                    self.R[i] = self.XL[i] / 1000.0
        kVArPerPhase = self.totalKVAr / self.nPhases
        self.setNormAmps((kVArPerPhase / phaseKV) * 1.35)
        self.setEmergAmps((self.getNormAmps() * 1.8) / 1.35)

    def calcYPrim(self):
        # normally build only Yprim_Shunt, but if there are 2 terminals and bus1 != bus2
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
        YPrimWork = CMatrixImpl(self.YOrder)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numSteps):
                break
            if self.states[i] == 1:
                self.makeYprimWork(YPrimWork, i)
                YPrimTemp.addFrom(YPrimWork)
        YPrimWork = None
        # set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
        if self.isShunt():
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                self.YPrimSeries.setElement(i, i, self.YPrimShunt.getElement(i, i).multiply(1e-10))
        self.YPrim.copyFrom(YPrimTemp)
        super(CapacitorObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def dumpProperties(self, f, complete):
        super(CapacitorObjImpl, self).dumpProperties(f, complete)
        print '~ ' + self.parentClass.getPropertyName()[0] + '=' + self.getFirstBus()
        print '~ ' + self.parentClass.getPropertyName()[1] + '=' + self.getNextBus()
        print '~ ' + self.parentClass.getPropertyName()[2] + '=' + self.getNPhases()
        print '~ ' + self.parentClass.getPropertyName()[3] + '=' + self.getPropertyValue(3)
        print '~ ' + self.parentClass.getPropertyName()[4] + '=' + self.getKVRating()
        _0 = self.getConnection()
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                print '~ ' + self.parentClass.getPropertyName()[5] + '=wye'
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                print '~ ' + self.parentClass.getPropertyName()[6] + '=delta'
                break
            break
        if self.getCMatrix() is not None:
            f.print_(self.parentClass.getPropertyName()[6] + '= (')
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.getNPhases()):
                    break
                _3 = True
                j = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    # TODO: Check zero based indexing
                    f.print_((self.getCMatrix()[((i - 1) * self.getNPhases()) + j] * 1000000.0) + ' ')
                if i != self.getNPhases():
                    f.print_('|')
            print ')'
        print '~ ' + self.parentClass.getPropertyName()[7] + '=' + self.getPropertyValue(7)
        print '~ ' + self.parentClass.getPropertyName()[8] + '=' + self.getPropertyValue(8)
        print '~ ' + self.parentClass.getPropertyName()[9] + '=' + self.getPropertyValue(9)
        print '~ ' + self.parentClass.getPropertyName()[10] + '=' + self.getPropertyValue(10)
        print '~ ' + self.parentClass.getPropertyName()[11] + '=' + self.getNumSteps()
        print '~ ' + self.parentClass.getPropertyName()[12] + '=' + self.getPropertyValue(12)
        _4 = True
        i = Capacitor.NumPropsThisClass + 1
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            # TODO: Check zero based indexing
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print 'SpecType=' + self.getSpecType()

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = self.getBus(0)
        # TODO: Check zero based indexing
        self.propertyValue[1] = self.getBus(1)
        # TODO: Check zero based indexing
        self.propertyValue[2] = '3'
        self.propertyValue[3] = '1200'
        self.propertyValue[4] = '12.47'
        self.propertyValue[5] = 'wye'
        self.propertyValue[6] = ''
        self.propertyValue[7] = ''
        self.propertyValue[8] = '0'
        self.propertyValue[9] = '0'
        self.propertyValue[10] = '0'
        self.propertyValue[11] = '1'
        self.propertyValue[12] = '1'
        # states
        super(CapacitorObjImpl, self).initPropertyValues(Capacitor.NumPropsThisClass)
        # override inherited properties
        self.propertyValue[Capacitor.NumPropsThisClass + 1] = Utilities.strReal(self.getNormAmps(), 0)
        # TODO: Check zero based indexing
        self.propertyValue[Capacitor.NumPropsThisClass + 2] = Utilities.strReal(self.getEmergAmps(), 0)
        self.propertyValue[Capacitor.NumPropsThisClass + 3] = Utilities.strReal(self.getFaultRate(), 0)
        self.propertyValue[Capacitor.NumPropsThisClass + 4] = Utilities.strReal(self.getPctPerm(), 0)
        self.propertyValue[Capacitor.NumPropsThisClass + 5] = Utilities.strReal(self.getHrsToRepair(), 0)
        self.clearPropSeqArray()

    def makePosSequence(self):
        s = None
        if self.getNPhases() > 1:
            _0 = self.getSpecType()
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    if (self.getNPhases() > 1) or (self.getConnection() != 0):
                        phaseKV = self.getKVRating() / DSSGlobals.SQRT3
                    else:
                        phaseKV = self.getKVRating()
                    s = 'Phases=1 ' + String.format.format(' kV=%-.5g kvar=(', phaseKV)
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.getNumSteps()):
                            break
                        kVArPerPhase = self.getKVArRating()[i] / self.getNPhases()
                        s = s + String.format.format(' %-.5g', kVArPerPhase)
                    s = s + ')'
                    break
                    # Leave R as specified
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    s = 'Phases=1 '
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    s = 'Phases=1 '
                    # r1
                    Cs = 0.0
                    # avg self
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < self.getNPhases()):
                            break
                        Cs = Cs + self.getCMatrix()[((i - 1) * self.getNPhases()) + i]
                        # TODO: Check zero based indexing
                    Cs = Cs / self.getNPhases()
                    Cm = 0.0
                    # avg mutual
                    _4 = True
                    i = 1
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < self.getNPhases()):
                            break
                        _5 = True
                        j = i
                        while True:
                            if _5 is True:
                                _5 = False
                            else:
                                j += 1
                            if not (j < self.getNPhases()):
                                break
                            Cm = Cm + self.getCMatrix()[((i - 1) * self.getNPhases()) + j]
                    Cm = Cm / (self.getNPhases() * (self.getNPhases() - 1.0)) / 2.0
                    s = s + String.format.format(' Cuf=%-.5g', Cs - Cm)
                    break
                break
            Parser.getInstance().setCmdString(s)
            self.edit()
        super(CapacitorObjImpl, self).makePosSequence()

    def getStates(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.states
        elif _1 == 1:
            idx, = _0
            return self.getStates()[idx]
        else:
            raise ARGERROR(0, 1)

    def setStates(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 1:
            values, = _0
            self.states = values
        elif _1 == 2:
            idx, value = _0
            if self.getStates()[idx] != value:
                self.getStates()[idx] = value
                self.setYPrimInvalid(True)
        else:
            raise ARGERROR(1, 2)

    def setNumSteps(self, value):
        """Special case for changing from 1 to more. Automatically make a new bank.

        1=kvar, 2=Cuf, 3=Cmatrix
        """
        # FIXME Private member in OpenDSS
        # Reallocate all arrays associated with steps
        if self.getNumSteps() != value and value > 0:
            RStep = 0.0
            XLStep = 0.0
            if self.getNumSteps() == 1:
                # Save total values to be divided up
                self.setTotalKVAr(self.getKVArRating()[0])
                RStep = self.getR()[0] * value
                XLStep = self.getXL()[0] * value
            # reallocate arrays (must be initialized to nil for first call)
            self.setC(Utilities.resizeArray(self.getC(), value))
            self.setXL(Utilities.resizeArray(self.getXL(), value))
            self.setKVArRating(Utilities.resizeArray(self.getKVArRating(), value))
            self.setR(Utilities.resizeArray(self.getR(), value))
            self.setHarm(Utilities.resizeArray(self.getHarm(), value))
            self.setStates(Utilities.resizeArray(self.getStates(), value))
            # special case for numSteps=1
            if self.getNumSteps() == 1:
                _0 = self.getSpecType()
                _1 = False
                while True:
                    if _0 == 1:
                        _1 = True
                        stepSize = self.getTotalKVAr() / value
                        _2 = True
                        i = 0
                        while True:
                            if _2 is True:
                                _2 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getKVArRating()[i] = stepSize
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        _3 = True
                        i = 1
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getC()[i] = self.getC()[0]
                            # make same as first step
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        break
                    break
                _4 = self.getSpecType()
                _5 = False
                while True:
                    if _4 == 1:
                        _5 = True
                        _6 = True
                        i = 0
                        while True:
                            if _6 is True:
                                _6 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getR()[i] = RStep
                        _7 = True
                        i = 0
                        while True:
                            if _7 is True:
                                _7 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getXL()[i] = XLStep
                        break
                    if (_5 is True) or (_4 == 2):
                        _5 = True
                        _8 = True
                        i = 1
                        while True:
                            if _8 is True:
                                _8 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getR()[i] = self.getR()[0]
                        _9 = True
                        i = 1
                        while True:
                            if _9 is True:
                                _9 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getXL()[i] = self.getXL()[0]
                        break
                    if (_5 is True) or (_4 == 3):
                        _5 = True
                        _10 = True
                        i = 1
                        while True:
                            if _10 is True:
                                _10 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getR()[i] = self.getR()[0]
                        _11 = True
                        i = 1
                        while True:
                            if _11 is True:
                                _11 = False
                            else:
                                i += 1
                            if not (i < value):
                                break
                            self.getXL()[i] = self.getXL()[0]
                        break
                    break
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < value):
                        break
                    self.getStates()[i] = 1
                    # turn them all ON
                self.setLastStepInService(value)
                _13 = True
                i = 1
                while True:
                    if _13 is True:
                        _13 = False
                    else:
                        i += 1
                    if not (i < value):
                        break
                    self.getHarm()[i] = self.getHarm()[0]
                    # tune them all the same as first
        self.setNumSteps(value)

    def processHarmonicSpec(self, param):
        # FIXME Private member in OpenDSS
        Utilities.interpretDblArray(param, self.getNumSteps(), self.getHarm())
        self.setDoHarmonicRecalc(True)

    def processStatesSpec(self, param):
        Utilities.interpretIntArray(param, self.getNumSteps(), self.getStates())
        self.lastStepInService = 0
        _0 = True
        i = self.getNumSteps()
        while True:
            if _0 is True:
                _0 = False
            else:
                i -= 1
            if not (i < 0):
                break
            # TODO Check zero based indexing
            if self.getStates()[i] == 1:
                self.lastStepInService = i
                break

    def makeYprimWork(self, YPrimWork, iStep):
        """Call this routine only if step is energized."""
        Zl = None
        self.setYPrimFreq(DSSGlobals.activeCircuit.getSolution().getFrequency())
        freqMultiple = self.getYPrimFreq() / self.getBaseFrequency()
        w = DSSGlobals.TWO_PI * self.getYPrimFreq()
        if self.getR()[iStep] + self.Math.abs(self.getXL()[iStep]) > 0.0:
            hasZl = True
        else:
            hasZl = False
        if hasZl:
            Zl = Complex(self.getR()[iStep], self.getXL()[iStep] * freqMultiple)
            # Now, put C into in Yprim matrix
        _0 = self.getSpecType()
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                value = Complex(0.0, self.getC()[iStep] * w)
                _2 = self.getConnection()
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
                            if not (i < self.getNPhases()):
                                break
                            YPrimWork.setElement(i, i, value2)
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
                                YPrimWork.setElemSym(i, j, value)
                            # remainder of the matrix is all zero
                        break
                    if True:
                        _3 = True
                        if hasZl:
                            value = ComplexUtil.invert(Zl.add(ComplexUtil.invert(value)))
                            # add in ZL
                        value2 = value.negate()
                        _6 = True
                        i = 0
                        while True:
                            if _6 is True:
                                _6 = False
                            else:
                                i += 1
                            if not (i < self.getNPhases()):
                                break
                            YPrimWork.setElement(i, i, value)
                            # elements are only on the diagonals
                            YPrimWork.setElement(i + self.getNPhases(), i + self.getNPhases(), value)
                            YPrimWork.setElemSym(i, i + self.getNPhases(), value2)
                        break
                    break
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                value = Complex(0.0, self.getC()[iStep] * w)
                _7 = self.getConnection()
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
                            if not (i < self.getNPhases()):
                                break
                            YPrimWork.setElement(i, i, value2)
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
                                YPrimWork.setElemSym(i, j, value)
                            # remainder of the matrix is all zero
                        break
                    if True:
                        _8 = True
                        if hasZl:
                            value = ComplexUtil.invert(Zl.add(ComplexUtil.invert(value)))
                            # add in ZL
                        value2 = value.negate()
                        _11 = True
                        i = 0
                        while True:
                            if _11 is True:
                                _11 = False
                            else:
                                i += 1
                            if not (i < self.getNPhases()):
                                break
                            YPrimWork.setElement(i, i, value)
                            # elements are only on the diagonals
                            YPrimWork.setElement(i + self.getNPhases(), i + self.getNPhases(), value)
                            YPrimWork.setElemSym(i, i + self.getNPhases(), value2)
                        break
                    break
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < self.getNPhases()):
                        break
                    ioffset = (i - 1) * self.getNPhases()
                    # TODO Check zero based indexing
                    _13 = True
                    j = 0
                    while True:
                        if _13 is True:
                            _13 = False
                        else:
                            j += 1
                        if not (j < self.getNPhases()):
                            break
                        value = Complex(0.0, self.getCMatrix()[ioffset + j] * w)
                        YPrimWork.setElement(i, j, value)
                        YPrimWork.setElement(i + self.getNPhases(), j + self.getNPhases(), value)
                        value = value.negate()
                        YPrimWork.setElemSym(i, j + self.getNPhases(), value)
                break
            break
        # Add line reactance for filter reactor, if any
        if hasZl:
            _14 = self.getSpecType()
            _15 = False
            while True:
                if _14 == 1:
                    _15 = True
                    _16 = self.getConnection()
                    _17 = False
                    while True:
                        if _16 == 1:
                            _17 = True
                            _18 = True
                            i = 0
                            while True:
                                if _18 is True:
                                    _18 = False
                                else:
                                    i += 1
                                if not (i < self.getNPhases()):
                                    break
                                YPrimWork.setElement(i, i, YPrimWork.getElement(i, i).multiply(1.000001))
                            YPrimWork.invert()
                            _19 = True
                            i = 0
                            while True:
                                if _19 is True:
                                    _19 = False
                                else:
                                    i += 1
                                if not (i < self.getNPhases()):
                                    break
                                value = Zl.add(YPrimWork.getElement(i, i))
                                YPrimWork.setElement(i, i, value)
                            YPrimWork.invert()
                            break
                        if True:
                            _17 = True
                            break
                        break
                    break
                if (_15 is True) or (_14 == 2):
                    _15 = True
                    _20 = self.getConnection()
                    _21 = False
                    while True:
                        if _20 == 1:
                            _21 = True
                            _22 = True
                            i = 0
                            while True:
                                if _22 is True:
                                    _22 = False
                                else:
                                    i += 1
                                if not (i < self.getNPhases()):
                                    break
                                YPrimWork.setElement(i, i, YPrimWork.getElement(i, i).multiply(1.000001))
                            YPrimWork.invert()
                            _23 = True
                            i = 0
                            while True:
                                if _23 is True:
                                    _23 = False
                                else:
                                    i += 1
                                if not (i < self.getNPhases()):
                                    break
                                value = Zl.add(YPrimWork.getElement(i, i))
                                YPrimWork.setElement(i, i, value)
                            YPrimWork.invert()
                            break
                        if True:
                            _21 = True
                            break
                        break
                    break
                if (_15 is True) or (_14 == 3):
                    _15 = True
                    YPrimWork.invert()
                    _24 = True
                    i = 0
                    while True:
                        if _24 is True:
                            _24 = False
                        else:
                            i += 1
                        if not (i < self.getNPhases()):
                            break
                        value = Zl.add(YPrimWork.getElement(i, i))
                        YPrimWork.setElement(i, i, value)
                    YPrimWork.invert()
                    break
                break

    def getPropertyValue(self, index):
        result = ''
        _0 = index
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                result = self.getBus(0)
                # TODO: Check zero based indexing
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                result = self.getBus(1)
                # TODO: Check zero based indexing
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = Utilities.getDSSArray_Real(self.getNumSteps(), self.getKVArRating())
                break
            if (_1 is True) or (_0 == 7):
                _1 = True
                temp = [None] * self.getNumSteps()
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.getNumSteps()):
                        break
                    temp[i] = self.getC()[i] * 1000000.0
                    # to microfarads
                result = Utilities.getDSSArray_Real(self.getNumSteps(), temp)
                temp = None
                # throw away temp storage
                break
            if (_1 is True) or (_0 == 8):
                _1 = True
                result = Utilities.getDSSArray_Real(self.getNumSteps(), self.getR())
                break
            if (_1 is True) or (_0 == 10):
                _1 = True
                result = Utilities.getDSSArray_Real(self.getNumSteps(), self.getXL())
                break
            if (_1 is True) or (_0 == 11):
                _1 = True
                result = Utilities.getDSSArray_Real(self.getNumSteps(), self.getHarm())
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                result = Utilities.getDSSArray_Integer(self.getNumSteps(), self.getStates())
                break
            if True:
                _1 = True
                result = super(CapacitorObjImpl, self).getPropertyValue(index)
                break
            break
        # special cases
        return result

    def addStep(self):
        # start with last step in service and see if we can add more; if not return false.
        if self.lastStepInService == self.getNumCustomers():
            return False
        else:
            self.lastStepInService += 1
            self.getStates()[self.lastStepInService] = 1
            # TODO Check zero based indexing
            return True

    def subtractStep(self):
        if self.lastStepInService == 0:
            # TODO Check zero based indexing
            return False
        else:
            self.getStates()[self.lastStepInService] = 0
            # TODO Check zero based indexing
            self.lastStepInService -= 1
            if self.lastStepInService == 0:
                return False
            else:
                return True
                # signify bank open

    def availableSteps(self):
        return self.getNumSteps() - self.lastStepInService

    def getNumSteps(self):
        return self.numSteps

    def getConnection(self):
        return self.connection

    def setConnection(self, conn):
        self.connection = conn

    def getTotalKVAr(self):
        return self.totalKVAr

    def getKVRating(self):
        # FIXME Private members in OpenDSS
        return self.kVRating

    def getC(self):
        return self.C

    def setC(self, value):
        self.C = value

    def getXL(self):
        return self.XL

    def setXL(self, xl):
        self.XL = xl

    def getKVArRating(self):
        return self.kVArRating

    def setKVArRating(self, rating):
        self.kVArRating = rating

    def getR(self):
        return self.R

    def setR(self, r):
        self.R = r

    def getHarm(self):
        return self.harm

    def setHarm(self, values):
        self.harm = values

    def getLastStepInService(self):
        return self.lastStepInService

    def setLastStepInService(self, lastStep):
        self.lastStepInService = lastStep

    def getCMatrix(self):
        return CMatrix

    def setCMatrix(self, cmatrix):
        CMatrix = cmatrix

    def isDoHarmonicRecalc(self):
        return self.doHarmonicRecalc

    def setDoHarmonicRecalc(self, value):
        self.doHarmonicRecalc = value

    def getSpecType(self):
        return self.specType

    def setSpecType(self, type):
        self.specType = type

    def setTotalKVAr(self, total):
        self.totalKVAr = total

    def setKVARating(self, rating):
        self.kVRating = rating
