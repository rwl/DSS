from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.general.LineCode import (LineCode,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.general.LineCodeObj import (LineCodeObj,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LineCodeObjImpl(DSSObjectImpl, LineCodeObj):
    NeutralConductor = None
    NPhases = None
    SymComponentsModel = None
    ReduceByKron = None
    Z = None
    Zinv = None
    Yc = None
    # base frequency series Z matrix
    # shunt capacitance matrix at base frequency
    BaseFrequency = None
    R1 = None
    X1 = None
    R0 = None
    X0 = None
    C1 = None
    C0 = None
    NormAmps = None
    EmergAmps = None
    FaultRate = None
    PctPerm = None
    HrsToRepair = None
    Rg = None
    Xg = None
    rho = None
    Units = None
    # see LineUnits

    def __init__(self, ParClass, LineCodeName):
        super(LineCodeObjImpl, self)(ParClass)
        self.setName(LineCodeName.toLowerCase())
        self.objType = ParClass.getDSSClassType()
        self.setNPhases(3)
        # directly set conds and phases
        self.NeutralConductor = self.NPhases - 1
        # initialize to last conductor  TODO Check zero indexing
        self.R1 = 0.058
        # ohms per 1000 ft
        self.X1 = 0.1206
        self.R0 = 0.1784
        self.X0 = 0.4047
        self.C1 = 3.3999999999999995e-09
        # nf per 1000ft
        self.C0 = 1.5999999999999999e-09
        self.Z = None
        self.Zinv = None
        self.Yc = None
        self.BaseFrequency = DSSGlobals.activeCircuit.getFundamental()
        self.Units = LineUnits.UNITS_NONE
        # default to none  (no conversion)
        self.NormAmps = 400.0
        self.EmergAmps = 600.0
        self.PctPerm = 20.0
        self.FaultRate = 0.1
        self.Rg = 0.01805
        # ohms per 1000'
        self.Xg = 0.155081
        self.rho = 100.0
        self.SymComponentsModel = True
        self.ReduceByKron = False
        self.calcMatricesFromZ1Z0()
        # put some reasonable values in
        self.initPropertyValues(0)

    def getRMatrix(self):
        Result = '['
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.NPhases):
                    break
                Result = Result + String.format.format('%12.8f ', self.Z.getElement(i, j).getReal())
            if i < self.NPhases:
                # TODO Check zero based indexing
                Result = Result + '|'
        return Result + ']'

    def getXMatrix(self):
        Result = '['
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.NPhases):
                    break
                Result = Result + String.format.format('%12.8f ', self.Z.getElement(i, j).getImaginary())
            if i < self.NPhases:
                # TODO Check zero based indexing
                Result = Result + '|'
        return Result + ']'

    def getCMatrix(self):
        Result = '['
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.NPhases):
                    break
                Result = Result + String.format.format('%12.8f ', (self.Yc.getElement(i, j).getImaginary() / DSSGlobals.TWO_PI / self.BaseFrequency) * 1000000000.0)
            if i < self.NPhases:
                # TODO Check zero based indexing
                Result = Result + '|'
        return Result + ']'

    def setNPhases(self, Value):
        """Set the number of phases and reallocate phase-sensitive arrays.
        Need to preserve values in Z matrices.
        """
        if Value > 0:
            if self.NPhases != Value:
                # if size is no different, we don't need to do anything
                self.NPhases = Value
                self.NeutralConductor = self.NPhases
                # init to last conductor
                # put some reasonable values in these matrices
                self.calcMatricesFromZ1Z0()
                # reallocs matrices

    def getNPhases(self):
        return self.NPhases

    def calcMatricesFromZ1Z0(self):
        if self.Z is not None:
            self.Z = None
        if self.Zinv is not None:
            self.Zinv = None
        if self.Yc is not None:
            self.Yc = None
            # for a line, nPhases = nCond, for now
        self.Z = CMatrixImpl(self.NPhases)
        self.Zinv = CMatrixImpl(self.NPhases)
        self.Yc = CMatrixImpl(self.NPhases)
        OneThird = 1.0 / 3.0
        # do this to get more precision in next few statements
        Ztemp = Complex(self.R1, self.X1).multiply(2.0)
        Zs = Ztemp.add(Complex(self.R0, self.X0)).multiply(OneThird)
        Zm = Complex(self.R0, self.X0).subtract(Complex(self.R1, self.X1)).multiply(OneThird)
        Yc1 = DSSGlobals.TWO_PI * self.BaseFrequency * self.C1
        Yc0 = DSSGlobals.TWO_PI * self.BaseFrequency * self.C0
        Ys = Complex(0.0, Yc1).multiply(2.0).add(Complex(0.0, Yc0)).multiply(OneThird)
        Ym = Complex(0.0, Yc0).subtract(Complex(0.0, Yc1)).multiply(OneThird)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            self.Z.setElement(i, i, Zs)
            self.Yc.setElement(i, i, Ys)
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
                self.Yc.setElemSym(i, j, Ym)
        self.Zinv.copyFrom(self.Z)
        self.Zinv.invert()

    def dumpProperties(self, F, Complete):
        super(LineCodeObjImpl, self).dumpProperties(F, Complete)
        print '~ ' + self.parentClass.getPropertyName()[1] + '=' + self.NPhases
        print '~ ' + self.parentClass.getPropertyName()[2] + '=' + self.R1
        print '~ ' + self.parentClass.getPropertyName()[3] + '=' + self.X1
        print '~ ' + self.parentClass.getPropertyName()[4] + '=' + self.R0
        print '~ ' + self.parentClass.getPropertyName()[5] + '=' + self.X0
        print '~ ' + self.parentClass.getPropertyName()[6] + '=' + (self.C1 * 1000000000.0)
        print '~ ' + self.parentClass.getPropertyName()[7] + '=' + (self.C0 * 1000000000.0)
        print '~ ' + self.parentClass.getPropertyName()[8] + '=' + self.propertyValue[8]
        F.print_('~ ' + self.parentClass.getPropertyName()[9] + '=\"')
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.NPhases):
                    break
                F.print_(self.Z.getElement(i, j).getReal() + ' ')
            F.print_('|')
        print '\"'
        F.print_('~ ' + self.parentClass.getPropertyName()[10] + '=\"')
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            _3 = True
            j = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    j += 1
                if not (j < self.NPhases):
                    break
                F.print_(self.Z.getElement(i, j).getImaginary() + ' ')
            F.print_('|')
        print '\"'
        F.print_('~ ' + self.parentClass.getPropertyName()[11] + '=\"')
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.NPhases):
                break
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < self.NPhases):
                    break
                F.print_(((self.Yc.getElement(i, j).getImaginary() / DSSGlobals.TWO_PI / self.BaseFrequency) * 1000000000.0) + ' ')
            F.print_('|')
        print '\"'
        _6 = True
        i = 12
        while True:
            if _6 is True:
                _6 = False
            else:
                i += 1
            if not (i < 21):
                break
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.propertyValue[i]
        print String.format.format('~ %s=%d', self.parentClass.getPropertyName()[22], self.NeutralConductor)

    def getPropertyValue(self, Index):
        _0 = Index
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                return String.format.format('%d', self.NPhases)
            if (_1 is True) or (_0 == 2):
                _1 = True
                return String.format.format('%.5g', self.R1) if self.SymComponentsModel else '----'
            if (_1 is True) or (_0 == 3):
                _1 = True
                return String.format.format('%.5g', self.X1) if self.SymComponentsModel else '----'
            if (_1 is True) or (_0 == 4):
                _1 = True
                return String.format.format('%.5g', self.R0) if self.SymComponentsModel else '----'
            if (_1 is True) or (_0 == 5):
                _1 = True
                return String.format.format('%.5g', self.X0) if self.SymComponentsModel else '----'
            if (_1 is True) or (_0 == 6):
                _1 = True
                return String.format.format('%.5g', self.C1 * 1000000000.0) if self.SymComponentsModel else '----'
            if (_1 is True) or (_0 == 7):
                _1 = True
                return String.format.format('%.5g', self.C0 * 1000000000.0) if self.SymComponentsModel else '----'
            if (_1 is True) or (_0 == 8):
                _1 = True
                return LineUnits.lineUnitsStr(self.Units)
            if (_1 is True) or (_0 == 9):
                _1 = True
                return self.getRMatrix()
            if (_1 is True) or (_0 == 10):
                _1 = True
                return self.getXMatrix()
            if (_1 is True) or (_0 == 11):
                _1 = True
                return self.getCMatrix()
            if (_1 is True) or (_0 == 12):
                _1 = True
                return String.format.format('%.g', DSSGlobals.defaultBaseFreq)
                # "baseFreq";
            if (_1 is True) or (_0 == 18):
                _1 = True
                return 'Y' if self.ReduceByKron else 'N'
            if (_1 is True) or (_0 == 19):
                _1 = True
                return String.format.format('%.5g', self.Rg)
            if (_1 is True) or (_0 == 20):
                _1 = True
                return String.format.format('%.5g', self.Xg)
            if (_1 is True) or (_0 == 21):
                _1 = True
                return String.format.format('%.5g', self.rho)
            if (_1 is True) or (_0 == 22):
                _1 = True
                return String.valueOf.valueOf(self.NeutralConductor)
            if True:
                _1 = True
                return super(LineCodeObjImpl, self).getPropertyValue(Index)
            break

    def initPropertyValues(self, ArrayOffset):
        # FIXME Private method in OpenDSS
        self.propertyValue[0] = '3'
        # "nphases";
        self.propertyValue[1] = '.058'
        # "r1";
        self.propertyValue[2] = '.1206'
        # "x1";
        self.propertyValue[3] = '0.1784'
        # "r0";
        self.propertyValue[4] = '0.4047'
        # "x0";
        self.propertyValue[5] = '3.4'
        # "c1";
        self.propertyValue[6] = '1.6'
        # "c0";
        self.propertyValue[7] = 'none'
        # "units";
        self.propertyValue[8] = ''
        # "rmatrix";
        self.propertyValue[9] = ''
        # "xmatrix";
        self.propertyValue[10] = ''
        # "cmatrix";
        self.propertyValue[11] = String.format.format('%6.1f', DSSGlobals.defaultBaseFreq)
        # "baseFreq";
        self.propertyValue[12] = '400'
        # "normamps";
        self.propertyValue[13] = '600'
        # "emergamps";
        self.propertyValue[14] = '0.1'
        # "faultrate";
        self.propertyValue[15] = '20'
        # "pctperm";
        self.propertyValue[16] = '3'
        # "Hrs to repair";
        self.propertyValue[17] = 'N'
        # "Kron";
        self.propertyValue[18] = '.01805'
        # "Rg";
        self.propertyValue[19] = '.155081'
        # "Xg";
        self.propertyValue[20] = '100'
        # "rho";
        self.propertyValue[21] = '3'
        # "Neutral";
        super(LineCodeObjImpl, self).initPropertyValues(LineCode.NumPropsThisClass)

    def doKronReduction(self):
        if self.NeutralConductor == 0:
            # TODO Check zero based indexing
            return
            # Do Nothing
        NewZ = None
        NewYc = None
        if self.NPhases > 1:
            # Reallocate into smaller space if Kron was successful
            try:
                NewZ = self.Z.kron(self.NeutralConductor)
                # perform Kron reductions into temp space
                # Have to invert the Y matrix to eliminate properly
                self.Yc.invert()
                # Vn = 0 not In
                NewYc = self.Yc.kron(self.NeutralConductor)
            except Exception, e:
                DSSGlobals.doSimpleMsg(String.format.format('Kron reduction failed: LineCode.%s. Attempting to eliminate neutral conductor %d.', self.getName(), self.NeutralConductor), 103)
            if NewZ is not None and NewYc is not None:
                NewYc.invert()
                # back to Y
                self.NPhases = NewZ.order()
                # get rid of Z and Yc and replace
                self.Z = None
                self.Yc = None
                self.Z = NewZ
                self.Yc = NewYc
                self.NeutralConductor = 0
                # TODO Check zero based indexing
                self.ReduceByKron = False
                # Change property values to reflect Kron reduction for save circuit function
                self.propertyValue[0] = String.format.format('%d', self.NPhases)
                self.propertyValue[8] = self.getRMatrix()
                self.propertyValue[9] = self.getXMatrix()
                self.propertyValue[10] = self.getCMatrix()
            else:
                DSSGlobals.doSimpleMsg(String.format.format('Kron reduction failed: LineCode.%s. Attempting to eliminate neutral conductor %d.', self.getName(), self.NeutralConductor), 103)
        else:
            DSSGlobals.doSimpleMsg('Cannot perform Kron Reduction on a 1-phase LineCode: LineCode.' + self.getName(), 103)

    def isSymComponentsModel(self):
        return self.SymComponentsModel

    def setSymComponentsModel(self, symComponentsModel):
        self.SymComponentsModel = symComponentsModel

    def isReduceByKron(self):
        return self.ReduceByKron

    def setReduceByKron(self, reduceByKron):
        self.ReduceByKron = reduceByKron

    def getZ(self):
        return self.Z

    def setZ(self, z):
        self.Z = z

    def getZinv(self):
        return self.Zinv

    def setZinv(self, zinv):
        self.Zinv = zinv

    def getYC(self):
        return self.Yc

    def setYc(self, Yc):
        self.Yc = Yc

    def getBaseFrequency(self):
        return self.BaseFrequency

    def setBaseFrequency(self, baseFrequency):
        self.BaseFrequency = baseFrequency

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

    def getC1(self):
        return self.C1

    def setC1(self, c1):
        self.C1 = c1

    def getC0(self):
        return self.C0

    def setC0(self, c0):
        self.C0 = c0

    def getNormAmps(self):
        return self.NormAmps

    def setNormAmps(self, normAmps):
        self.NormAmps = normAmps

    def getEmergAmps(self):
        return self.EmergAmps

    def setEmergAmps(self, emergAmps):
        self.EmergAmps = emergAmps

    def getFaultRate(self):
        return self.FaultRate

    def setFaultRate(self, faultRate):
        self.FaultRate = faultRate

    def getPctPerm(self):
        return self.PctPerm

    def setPctPerm(self, pctPerm):
        self.PctPerm = pctPerm

    def getHrsToRepair(self):
        return self.HrsToRepair

    def setHrsToRepair(self, hrsToRepair):
        self.HrsToRepair = hrsToRepair

    def getRg(self):
        return self.Rg

    def setRg(self, rg):
        self.Rg = rg

    def getXg(self):
        return self.Xg

    def setXg(self, xg):
        self.Xg = xg

    def getRho(self):
        return self.rho

    def setRho(self, rho):
        self.rho = rho

    def getUnits(self):
        return self.Units

    def setUnits(self, units):
        # FIXME Private members in OpenDSS
        self.Units = units

    def getNeutralConductor(self):
        return self.NeutralConductor

    def setNeutralConductor(self, neutralConductor):
        self.NeutralConductor = neutralConductor
