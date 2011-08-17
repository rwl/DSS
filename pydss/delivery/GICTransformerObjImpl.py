from dss.delivery.impl.PDElementImpl import (PDElementImpl,)
from dss.delivery.GICTransformerObj import (GICTransformerObj,)
from dss.delivery.GICTransformer import (GICTransformer,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class GICTransformerObjImpl(PDElementImpl, GICTransformerObj):
    G1 = None
    G2 = None
    # single G per phase (line rating)
    specType = None

    def __init__(self, parClass, transformerName):
        super(GICTransformerObjImpl, self)(parClass)
        self.objType = parClass.getDSSClassType()
        self.setName(transformerName.toLowerCase())
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(2)
        # force allocation of terminals and conductors
        self.setBus(2, self.getBus(0) + '.0')
        # default to grounded
        self.isShunt = True
        self.G1 = 10000.0
        self.G2 = 10000.0
        self.specType = GICTransformer.SPEC_GSU
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
        # Now, put in Yprim matrix
        # If the fault is not on, the set zero conductance
        _0 = self.specType
        _1 = False
        while True:
            if _0 == GICTransformer.SPEC_GSU:
                _1 = True
                value = Complex(self.G1, 0.0)
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
            if (_1 is True) or (_0 == GICTransformer.SPEC_AUTO):
                _1 = True
                value = Complex(self.G1, 0.0)
                value2 = value.negate()
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    YPrimTemp.setElement(i, i, value)
                    # elements are only on the diagonals
                    YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                    YPrimTemp.setElemSym(i, i + self.nPhases, value2)
                # terminals 3 and 4
                value = Complex(self.G2, 0.0)
                value2 = value.negate()
                _4 = True
                i = (2 * self.nPhases) + 1
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < 3 * self.nPhases):
                        break
                    YPrimTemp.setElement(i, i, value)
                    # elements are only on the diagonals
                    YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                    YPrimTemp.setElemSym(i, i + self.nPhases, value2)
                break
            if (_1 is True) or (_0 == GICTransformer.SPEC_YY):
                _1 = True
                value = Complex(self.G1, 0.0)
                value2 = value.negate()
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    YPrimTemp.setElement(i, i, value)
                    # elements are only on the diagonals
                    YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                    YPrimTemp.setElemSym(i, i + self.nPhases, value2)
                # terminals 3 and 4
                value = Complex(self.G2, 0.0)
                value2 = value.negate()
                _6 = True
                i = (2 * self.nPhases) + 1
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < 3 * self.nPhases):
                        break
                    YPrimTemp.setElement(i, i, value)
                    # elements are only on the diagonals
                    YPrimTemp.setElement(i + self.nPhases, i + self.nPhases, value)
                    YPrimTemp.setElemSym(i, i + self.nPhases, value2)
                break
            break
        self.YPrim.copyFrom(YPrimTemp)
        super(GICTransformerObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def dumpProperties(self, f, complete):
        super(GICTransformerObjImpl, self).dumpProperties(f, complete)
        pc = self.getParentClass()
        print '~ ' + pc.getPropertyName()[0] + '=' + self.getFirstBus()
        print '~ ' + pc.getPropertyName()[1] + '=' + self.getNextBus()
        print '~ ' + pc.getPropertyName()[2] + '=' + self.getNextBus()
        print '~ ' + pc.getPropertyName()[3] + '=' + self.getNextBus()
        print '~ ' + pc.getPropertyName()[4] + '=' + self.nPhases
        _0 = self.specType
        _1 = False
        while True:
            if _0 == GICTransformer.SPEC_GSU:
                _1 = True
                print '~ ' + pc.getPropertyName()[6] + '= GSU'
                break
            if (_1 is True) or (_0 == GICTransformer.SPEC_AUTO):
                _1 = True
                print '~ ' + pc.getPropertyName()[6] + '= AUTO'
                break
            if (_1 is True) or (_0 == GICTransformer.SPEC_YY):
                _1 = True
                print '~ ' + pc.getPropertyName()[6] + '= YY'
                break
            break
        print '~ ' + pc.getPropertyName()[6] + '=' + (1.0 / self.G1)
        print '~ ' + pc.getPropertyName()[7] + '=' + (1.0 / self.G2)
        _2 = True
        i = GICTransformer.NumPropsThisClass
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < pc.getNumProperties()):
                break
            print '~ ' + pc.getPropertyName()[i] + '=' + self.getPropertyValue(i)

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, self.getBus(0))
        self.setPropertyValue(1, self.getBus(1))
        self.setPropertyValue(2, self.getBus(2))
        self.setPropertyValue(3, self.getBus(3))
        self.setPropertyValue(4, '3')
        self.setPropertyValue(5, 'GSU')
        self.setPropertyValue(6, '0.0001')
        self.setPropertyValue(7, '0.0001')
        super(GICTransformerObjImpl, self).initPropertyValues(GICTransformer.NumPropsThisClass - 1)
        # Override Inherited Properties
        self.setPropertyValue(GICTransformer.NumPropsThisClass + 0, '0')
        # normAmps
        self.setPropertyValue(GICTransformer.NumPropsThisClass + 1, '0')
        # emergAmps
        self.setPropertyValue(GICTransformer.NumPropsThisClass + 2, '0')
        # faultRate
        self.setPropertyValue(GICTransformer.NumPropsThisClass + 3, '0')
        # pctPerm
        self.setPropertyValue(GICTransformer.NumPropsThisClass + 4, '0')
        # hrsToRepair

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return self.getBus(0)
            if (_1 is True) or (_0 == 1):
                _1 = True
                return self.getBus(1)
            if (_1 is True) or (_0 == 2):
                _1 = True
                return self.getBus(2)
            if (_1 is True) or (_0 == 3):
                _1 = True
                return self.getBus(3)
            if (_1 is True) or (_0 == 4):
                _1 = True
                return String.format.format('%d', self.nPhases)
            if (_1 is True) or (_0 == 6):
                _1 = True
                return String.format.format('%.8g', 1.0 / self.G1)
            if (_1 is True) or (_0 == 7):
                _1 = True
                return String.format.format('%.8g', 1.0 / self.G2)
            if True:
                _1 = True
                return super(GICTransformerObjImpl, self).getPropertyValue(index)
            break

    def makePosSequence(self):
        # FIXME: Private members in OpenDSS
        if self.nPhases != 1:
            Parser.getInstance().setCmdString('Phases=1')
            self.edit()
        super(GICTransformerObjImpl, self).makePosSequence()

    def getG1(self):
        return self.G1

    def setG1(self, g1):
        self.G1 = g1

    def getG2(self):
        return self.G2

    def setG2(self, g2):
        self.G2 = g2

    def getSpecType(self):
        return self.specType

    def setSpecType(self, type):
        self.specType = type
