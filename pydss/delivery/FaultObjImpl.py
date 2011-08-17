from dss.delivery.Fault import (Fault,)
from dss.shared.Dynamics import (Dynamics,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.impl.PDElementImpl import (PDElementImpl,)
from dss.delivery.FaultObj import (FaultObj,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class FaultObjImpl(PDElementImpl, FaultObj):
    minAmps = None
    isTemporary = None
    cleared = None
    isOn = None
    onTime = None
    randomMult = None
    # Single G per phase (line rating) if GMatrix not specified
    G = None
    # If not null then overrides G
    GMatrix = None
    # Per unit std dev
    stdDev = None
    specType = None

    def __init__(self, parClass, faultName):
        super(FaultObjImpl, self)(parClass)
        self.objType = parClass.getDSSClassType()
        # FAULTOBJECT + NON_PCPD_ELEM;  // only in fault object class
        self.setName(faultName.toLowerCase())
        # default to SLG fault
        self.setNPhases(1)
        # directly set conds and phases
        self.nConds = 1
        self.setNTerms(2)
        # force allocation of terminals and conductors
        self.setBus(2, self.getBus(0) + '.0')
        # default to grounded   TODO Check zero based indexing
        self.setShunt(True)
        self.GMatrix = None
        self.G = 10000.0
        self.specType = 1
        # G 2=Gmatrix
        self.minAmps = 5.0
        self.isTemporary = False
        self.cleared = False
        self.isOn = True
        self.onTime = 0.0
        # always enabled at the start of a solution
        self.randomMult = 1
        self.normAmps = 0.0
        self.emergAmps = 0.0
        self.faultRate = 0.0
        self.pctPerm = 100.0
        self.hrsToRepair = 0.0
        self.initPropertyValues(0)
        self.YOrder = self.nTerms * self.nConds
        self.recalcElementData()

    def recalcElementData(self):
        # nothing to do
        pass

    def randomize(self):
        """Called from solveMontefault procedure."""
        sol = DSSGlobals.activeCircuit.getSolution()
        _0 = sol.getRandomType()
        _1 = False
        while True:
            if _0 == DSSGlobals.GAUSSIAN:
                _1 = True
                self.randomMult = MathUtil.gauss(1.0, self.stdDev)
                break
            if (_1 is True) or (_0 == DSSGlobals.UNIFORM):
                _1 = True
                self.randomMult = self.Math.random()
                break
            if (_1 is True) or (_0 == DSSGlobals.LOGNORMAL):
                _1 = True
                self.randomMult = MathUtil.quasiLognormal(1.0)
                break
            if True:
                _1 = True
                self.randomMult = 1.0
                break
            break
        # give the multiplier some skew to approximate more uniform/Gaussian current distributions
        # RandomMult = cube(RandomMult);   removed 12/7/04
        self.setYPrimInvalid(True)
        # force rebuilding of matrix

    def calcYPrim(self):
        if self.isYprimInvalid():
            # reallocate YPrim if something has invalidated old allocation
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            if self.YPrimShunt is not None:
                self.YPrimShunt = None
            self.YPrimShunt = CMatrixImpl(self.YOrder)
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
        # make sure randomMult is 1.0 if not solution mode MonteFault
        if DSSGlobals.activeCircuit.getSolution().getMode() != Dynamics.MONTEFAULT:
            self.randomMult = 1.0
        if self.randomMult == 0.0:
            self.randomMult = 1e-06
            # Now, put in Yprim matrix
            # If the fault is not on, the set zero conductance
        _0 = self.specType
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                if self.isOn:
                    value = Complex(self.G / self.randomMult, 0.0)
                else:
                    value = Complex.ZERO
                value2 = value.negate()
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    YPrimTemp.setElement(i, i, value)
                    # elements are only on the diagonals
                    YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                    YPrimTemp.setElemSym(i, i + self.nPhases, value2)
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    ioffset = (i - 1) * self.nPhases
                    _4 = True
                    j = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            j += 1
                        if not (j < self.nPhases):
                            break
                        if self.isOn:
                            value = Complex(self.GMatrix[ioffset + j] / self.randomMult, 0.0)
                        else:
                            value = Complex.ZERO
                        YPrimTemp.setElement(i, j, value)
                        YPrimTemp.setElement(i + self.nPhases, j + self.nPhases, value)
                        value = value.negate()
                        YPrimTemp.setElemSym(i, j + self.nPhases, value)
                break
            break
        self.YPrim.copyFrom(YPrimTemp)
        super(FaultObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def dumpProperties(self, f, complete):
        super(FaultObjImpl, self).dumpProperties(f, complete)
        pc = self.getParentClass()
        print '~ ' + pc.getPropertyName()[0] + '=' + self.getFirstBus()
        print '~ ' + pc.getPropertyName()[1] + '=' + self.getNextBus()
        print '~ ' + pc.getPropertyName()[2] + '=' + self.nPhases
        print '~ ' + pc.getPropertyName()[3] + '=' + (1.0 / self.G)
        print '~ ' + pc.getPropertyName()[4] + '=' + (self.stdDev * 100.0)
        if self.GMatrix is not None:
            f.print_('~ ' + pc.getPropertyName()[5] + '= (')
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
                    if not (j < i):
                        break
                    f.print_(self.GMatrix[((i - 1) * self.nPhases) + j] + ' ')
                if i != self.nPhases:
                    f.print_('|')
            print ')'
        print '~ ' + pc.getPropertyName()[6] + '=' + self.onTime
        if self.isTemporary:
            print '~ ' + pc.getPropertyName()[7] + '= Yes'
        else:
            print '~ ' + pc.getPropertyName()[7] + '= No'
        print '~ ' + pc.getPropertyName()[8] + '=' + self.minAmps
        _2 = True
        i = Fault.NumPropsThisClass
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < pc.getNumProperties()):
                break
            print '~ ' + pc.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print '// SpecType=' + self.specType

    def checkStatus(self, controlMode):
        _0 = controlMode
        _1 = False
        while True:
            if _0 == DSSGlobals.CTRLSTATIC:
                _1 = True
                break
            if (_1 is True) or (_0 == DSSGlobals.EVENTDRIVEN):
                _1 = True
                if not self.isOn:
                    # Turn it on unless it has been previously cleared
                    if Utilities.presentTimeInSec() > self.onTime and not self.cleared:
                        self.isOn = True
                        self.setYPrimInvalid(True)
                        Utilities.appendToEventLog('Fault.' + self.getName(), '**APPLIED**')
                elif self.isTemporary:
                    if not self.faultStillGoing():
                        self.isOn = False
                        self.cleared = True
                        self.setYPrimInvalid(True)
                        Utilities.appendToEventLog('Fault.' + self.getName(), '**CLEARED**')
                break
            if (_1 is True) or (_0 == DSSGlobals.TIMEDRIVEN):
                _1 = True
                if not self.isOn:
                    # Turn it on unless it has been previously cleared
                    if Utilities.presentTimeInSec() > self.onTime and not self.cleared:
                        self.isOn = True
                        self.setYPrimInvalid(True)
                        Utilities.appendToEventLog('Fault.' + self.getName(), '**APPLIED**')
                elif self.isTemporary:
                    if not self.faultStillGoing():
                        self.isOn = False
                        self.cleared = True
                        self.setYPrimInvalid(True)
                        Utilities.appendToEventLog('Fault.' + self.getName(), '**CLEARED**')
                break
            break

    def faultStillGoing(self):
        self.computeITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            if self.ITerminal[i].abs() > self.minAmps:
                return True
        return False

    def reset(self):
        self.setCleared(False)

    def initPropertyValues(self, ArrayOffset):
        self.propertyValue[0] = self.getBus(0)
        self.propertyValue[1] = self.getBus(1)
        self.propertyValue[2] = '1'
        self.propertyValue[3] = '0.0001'
        self.propertyValue[4] = '0'
        self.propertyValue[5] = ''
        self.propertyValue[6] = '0.0'
        self.propertyValue[7] = 'no'
        self.propertyValue[8] = '5.0'
        super(FaultObjImpl, self).initPropertyValues(Fault.NumPropsThisClass)
        # override inherited properties
        self.propertyValue[Fault.NumPropsThisClass + 1] = '0'
        # normAmps   TODO Check zero based indexing
        self.propertyValue[Fault.NumPropsThisClass + 2] = '0'
        # emergAmps
        self.propertyValue[Fault.NumPropsThisClass + 3] = '0'
        # faultRate
        self.propertyValue[Fault.NumPropsThisClass + 4] = '0'
        # pctPerm
        self.propertyValue[Fault.NumPropsThisClass + 5] = '0'
        # hrsToRepair

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 5:
                _1 = True
                result = '('
                if self.GMatrix is not None:
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
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
                            result = result + String.format.format('%-g', self.GMatrix[((i - 1) * self.nPhases) + j]) + ' '
                        if i < self.nPhases:
                            result = result + '|'
                result = result + ')'
                break
            if True:
                _1 = True
                result = super(FaultObjImpl, self).getPropertyValue(index)
                break
            break
        return result

    def makePosSequence(self):
        # FIXME Private members in OpenDSS
        if self.nPhases != 1:
            Parser.getInstance().setCmdString('Phases=1')
            self.edit()
        super(FaultObjImpl, self).makePosSequence()

    def getMinAmps(self):
        return self.minAmps

    def setMinAmps(self, min):
        self.minAmps = min

    def isTemporary(self):
        return self.isTemporary

    def setTemporary(self, temp):
        self.isTemporary = temp

    def isCleared(self):
        return self.cleared

    def setCleared(self, value):
        self.cleared = value

    def isOn(self):
        return self.isOn

    def setOn(self, on):
        self.isOn = on

    def getOnTime(self):
        return self.onTime

    def setOnTime(self, value):
        self.onTime = value

    def getRandomMult(self):
        return self.randomMult

    def setRandomMult(self, mult):
        self.randomMult = mult

    def getG(self):
        return self.G

    def setG(self, g):
        self.G = g

    def getGMatrix(self):
        return self.GMatrix

    def setGMatrix(self, value):
        self.GMatrix = value

    def getStdDev(self):
        return self.stdDev

    def setStdDev(self, value):
        self.stdDev = value

    def getSpecType(self):
        return self.specType

    def setSpecType(self, type):
        self.specType = type
