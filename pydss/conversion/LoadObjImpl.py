from dss.conversion.LoadObj import (LoadObj,)
from dss.shared.Dynamics import (Dynamics,)
from dss.common.impl.Utilities import (Utilities,)
from dss.conversion.impl.LoadImpl import (LoadImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.Load import (Load,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LoadObjImpl(PCElementImpl, LoadObj):
    CDOUBLEONE = Complex(1.0, 1.0)
    PFChanged = None
    allocationFactor = None
    kVAAllocationFactor = None
    connectedkVA = None
    kWh = None
    kWhDays = None
    CFactor = None
    avgKW = None
    harmAng = None
    harmMag = None
    lastGrowthFactor = None
    lastYear = None
    loadFundamental = None
    loadSolutionCount = None
    openLoadSolutionCount = None
    randomMult = None
    shapeFactor = None
    varBase = None
    varNominal = None
    VBase = None
    VBase105 = None
    VBase95 = None
    WNominal = None
    Yeq = None
    Yeq105 = None
    Yeq95 = None
    Yneut = None
    YPrimOpenCond = None
    YQFixed = None
    ZIPV = None
    nZIPV = None
    # formerly private, now read-only properties
    puMean = None
    puStdDev = None
    CVRwattFactor = None
    CVRvarFactor = None
    VMaxPU = None
    VMinEmerg = None
    # overrides system settings if != 0.0
    VMinNormal = None
    VMinPU = None
    exemptFromLDCurve = None
    fixed = None
    # if fixed, always at base value
    shapeIsActual = None
    connection = None
    dailyShape = None
    dailyShapeObj = None
    dutyShape = None
    dutyShapeObj = None
    EEN_Factor = None
    growthShape = None
    growthShapeObj = None
    hasBeenAllocated = None
    kWBase = None
    kVABase = None
    kVArBase = None
    kVLoadBase = None
    loadClass = None
    numCustomers = None
    loadSpecType = None
    PFNominal = None
    RNeut = None
    UE_Factor = None
    XNeut = None
    yearlyShape = None
    yearlyShapeObj = None
    CVRshape = None
    CVRShapeObj = None
    loadModel = None

    def __init__(self, parClass, sourceName):
        # FIXME Private method in OpenDSS
        super(LoadObjImpl, self)(parClass)
        self.setName(sourceName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        self.setNPhases(3)
        self.nConds = 4
        # defaults to wye so it has a 4th conductor
        self.YOrder = 0
        # to trigger an initial allocation
        self.setNTerms(1)
        # forces allocations
        self.kWBase = 10.0
        self.kVArBase = 5.0
        self.PFNominal = 0.88
        self.kVABase = self.kWBase / self.PFNominal
        self.loadSpecType = 0
        self.RNeut = -1.0
        # signify neutral is open
        self.XNeut = 0.0
        self.yearlyShape = ''
        self.yearlyShapeObj = None
        # if yearlyShapeObj = null then the load alway stays nominal * global multipliers
        self.dailyShape = ''
        self.dailyShapeObj = None
        # if dailyShapeObj = null then the load alway stays nominal * global multipliers
        self.dutyShape = ''
        self.dutyShapeObj = None
        # if dutyShapeObj = null then the load alway stays nominal * global multipliers
        self.growthShape = ''
        self.growthShapeObj = None
        # if growthShapeObj = null then the load alway stays nominal * global multipliers
        self.CVRshape = ''
        self.CVRShapeObj = None
        self.connection = 0
        # wye (star)
        self.loadModel = 1
        # changed from 2 RCD (easiest to solve)
        self.loadClass = 1
        self.numCustomers = 1
        self.lastYear = 0
        self.CVRwattFactor = 1.0
        self.CVRvarFactor = 2.0
        self.lastGrowthFactor = 1.0
        self.kVAAllocationFactor = 0.5
        self.allocationFactor = self.kVAAllocationFactor
        self.hasBeenAllocated = False
        self.PFChanged = False
        self.shapeIsActual = False
        self.loadSolutionCount = -1
        # for keeping track of the present solution in injcurrent calcs
        self.openLoadSolutionCount = -1
        self.YPrimOpenCond = None
        self.connectedkVA = 0.0
        # loadSpecType=3
        self.kWh = 0.0
        # loadSpecType=4
        self.CFactor = 4.0
        self.kWhDays = 30.0
        self.VMinNormal = 0.0
        # indicates for program to use circuit quantities
        self.VMinEmerg = 0.0
        self.kVLoadBase = 12.47
        self.VBase = 7200.0
        self.VMinPU = 0.95
        self.VMaxPU = 1.05
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.YOrder = self.nTerms * self.nConds
        self.randomMult = 1.0
        self.fixed = False
        self.exemptFromLDCurve = False
        self.puMean = 0.5
        self.puStdDev = 0.1
        self.UE_Factor = 0.0
        self.EEN_Factor = 0.0
        self.spectrum = 'defaultload'
        # override base class definition
        self.harmMag = None
        self.harmAng = None
        self.ZIPV = None
        self.setZIPVSize(0)
        self.initPropertyValues(0)
        self.recalcElementData()

    def setZIPVSize(self, n):
        self.nZIPV = n
        self.ZIPV = Utilities.resizeArray(self.ZIPV, self.nZIPV)

    def randomize(self, opt):
        """0 = reset to 1.0
        1 = Gaussian around mean and std dev
        2 = uniform
        """
        _0 = opt
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.randomMult = 1.0
                break
            if (_1 is True) or (_0 == DSSGlobals.GAUSSIAN):
                _1 = True
                if self.yearlyShapeObj is not None:
                    self.randomMult = MathUtil.gauss(self.yearlyShapeObj.getMean(), self.yearlyShapeObj.getStdDev())
                else:
                    self.randomMult = MathUtil.gauss(self.puMean, self.puStdDev)
                break
            if (_1 is True) or (_0 == DSSGlobals.UNIFORM):
                _1 = True
                self.randomMult = self.Math.random()
                # number between 0 and 1.0
                break
            if (_1 is True) or (_0 == DSSGlobals.LOGNORMAL):
                _1 = True
                if self.yearlyShapeObj is not None:
                    self.randomMult = MathUtil.quasiLognormal(self.yearlyShapeObj.getMean())
                else:
                    self.randomMult = MathUtil.quasiLognormal(self.puMean)
                break
            break

    def calcDailyMult(self, hr):
        if self.dailyShapeObj is not None:
            self.shapeFactor = self.dailyShapeObj.getMult(hr)
            self.shapeIsActual = self.dailyShapeObj.isUseActual()
        else:
            self.shapeFactor = Complex(1.0, 1.0)
            # default to no daily variation

    def calcDutyMult(self, hr):
        if self.dutyShapeObj is not None:
            self.shapeFactor = self.dutyShapeObj.getMult(hr)
            self.shapeIsActual = self.dutyShapeObj.isUseActual()
        else:
            self.calcDailyMult(hr)
            # default to daily mult if no duty curve specified

    def calcYearlyMult(self, hr):
        # Yearly curve is assumed to be hourly only
        if self.yearlyShapeObj is not None:
            self.shapeFactor = self.yearlyShapeObj.getMult(hr)
            self.shapeIsActual = self.yearlyShapeObj.isUseActual()
        else:
            self.shapeFactor = Complex(1.0, 1.0)
            # defaults to no variation

    def calcCVRMult(self, hr):
        # CVR curve is assumed to be used in a yearly simulation
        if self.CVRShapeObj is not None:
            CVRFactor = self.CVRShapeObj.getMult(hr)
            # Complex
            self.CVRwattFactor = CVRFactor.getReal()
            self.CVRvarFactor = CVRFactor.getImaginary()
        else:
            # CVRWattFactor, etc. remain unchanged
            pass

    def growthFactor(self, year):
        if year == 0:
            self.lastGrowthFactor = 1.0
            # default all to 1 in year 0 ; use base values
        elif self.growthShapeObj is None:
            self.lastGrowthFactor = DSSGlobals.activeCircuit.getDefaultGrowthFactor()
        elif year != self.lastYear:
            # search growth curve
            self.lastGrowthFactor = self.growthShapeObj.getMult(year)
        return self.lastGrowthFactor
        # for now

    def setKW_KVAr(self, PkW, QkVAr):
        self.kWBase = PkW
        self.kVArBase = QkVAr
        self.loadSpecType = 1

    def setNominalLoad(self):
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        self.shapeFactor = self.CDOUBLEONE
        self.shapeIsActual = False
        if self.fixed:
            factor = self.growthFactor(sol.getYear())
            # for fixed loads, consider only growth factor
        else:
            _0 = sol.getMode()
            _1 = False
            while True:
                if _0 == Dynamics.SNAPSHOT:
                    _1 = True
                    if self.exemptFromLDCurve:
                        factor = self.growthFactor(sol.getYear())
                    else:
                        factor = ckt.getLoadMultiplier() * self.growthFactor(sol.getYear())
                    break
                if (_1 is True) or (_0 == Dynamics.HARMONICMODE):
                    _1 = True
                    if self.exemptFromLDCurve:
                        factor = self.growthFactor(sol.getYear())
                    else:
                        factor = ckt.getLoadMultiplier() * self.growthFactor(sol.getYear())
                    break
                if (_1 is True) or (_0 == Dynamics.DAILYMODE):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    self.calcDailyMult(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.YEARLYMODE):
                    _1 = True
                    factor = ckt.getLoadMultiplier() * self.growthFactor(sol.getYear())
                    self.calcYearlyMult(sol.getDblHour())
                    if self.loadModel == 4:
                        self.calcCVRMult(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.DUTYCYCLE):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    self.calcDutyMult(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.GENERALTIME):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                        # this mode allows use of one class of load shape
                    _2 = ckt.getActiveLoadShapeClass()
                    _3 = False
                    while True:
                        if _2 == DSSGlobals.USEDAILY:
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == DSSGlobals.USEYEARLY):
                            _3 = True
                            self.calcYearlyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == DSSGlobals.USEDUTY):
                            _3 = True
                            self.calcDutyMult(sol.getDblHour())
                            break
                        if True:
                            _3 = True
                            self.shapeFactor = Complex.ONE
                            # default to 1 + j1 if not known
                            break
                        break
                    break
                if (_1 is True) or (_0 == Dynamics.DYNAMICMODE):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                        # this mode allows use of one class of load shape
                    _4 = ckt.getActiveLoadShapeClass()
                    _5 = False
                    while True:
                        if _4 == DSSGlobals.USEDAILY:
                            _5 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_5 is True) or (_4 == DSSGlobals.USEYEARLY):
                            _5 = True
                            self.calcYearlyMult(sol.getDblHour())
                            break
                        if (_5 is True) or (_4 == DSSGlobals.USEDUTY):
                            _5 = True
                            self.calcDutyMult(sol.getDblHour())
                            break
                        if True:
                            _5 = True
                            self.shapeFactor = Complex.ONE
                            # default to 1 + j1 if not known
                            break
                        break
                    break
                if (_1 is True) or (_0 == Dynamics.MONTECARLO1):
                    _1 = True
                    self.randomize(sol.getRandomType())
                    factor = self.randomMult * self.growthFactor(sol.getYear())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    break
                if (_1 is True) or (_0 == Dynamics.MONTECARLO2):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    self.calcDailyMult(sol.getDblHour())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    break
                if (_1 is True) or (_0 == Dynamics.MONTECARLO3):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    self.calcDailyMult(sol.getDblHour())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    break
                if (_1 is True) or (_0 == Dynamics.LOADDURATION1):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    self.calcDailyMult(sol.getDblHour())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    break
                if (_1 is True) or (_0 == Dynamics.LOADDURATION2):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    self.calcDailyMult(sol.getDblHour())
                    if not self.exemptFromLDCurve:
                        factor = factor * ckt.getLoadMultiplier()
                    break
                if (_1 is True) or (_0 == Dynamics.PEAKDAY):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    self.calcDailyMult(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.AUTOADDFLAG):
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    # loadMult = 1.0 by default
                    break
                if True:
                    _1 = True
                    factor = self.growthFactor(sol.getYear())
                    # defaults to Base kW * growth
                    break
                break
        if self.shapeIsActual:
            self.WNominal = (1000.0 * self.shapeFactor.getReal()) / self.nPhases
            self.varNominal = (1000.0 * self.shapeFactor.getImaginary()) / self.nPhases
        else:
            self.WNominal = (1000.0 * self.kWBase * factor * self.shapeFactor.getReal()) / self.nPhases
            self.varNominal = (1000.0 * self.kVArBase * factor * self.shapeFactor.getImaginary()) / self.nPhases
        self.Yeq = ComplexUtil.divide(Complex(self.WNominal, -self.varNominal), self.Math.pow(self.VBase, 2))
        if self.VMinPU != 0.0:
            self.Yeq95 = ComplexUtil.divide(self.Yeq, self.Math.pow(self.VMinPU, 2))
            # at 95% voltage
        else:
            self.Yeq95 = Complex.ZERO
        if self.VMaxPU != 0.0:
            self.Yeq105 = ComplexUtil.divide(self.Yeq, self.Math.pow(self.VMaxPU, 2))
            # at 105% voltage
        else:
            self.Yeq105 = self.Yeq

    def recalcElementData(self):
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        # Set kW and kVAr from root values of kVA and PF
        _0 = self.loadSpecType
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
                if self.PFNominal < 0.0:
                    self.kVArBase = -self.kVArBase
                self.kVABase = self.Math.sqrt(self.Math.pow(self.kWBase, 2) + self.Math.pow(self.kVArBase, 2))
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                self.kVABase = self.Math.sqrt(self.Math.pow(self.kWBase, 2) + self.Math.pow(self.kVArBase, 2))
                if self.kVABase > 0.0:
                    self.PFNominal = self.kWBase / self.kVABase
                    # If kW and kvar are different signs, PF is negative
                    if self.kVArBase != 0.0:
                        self.PFNominal = self.PFNominal * self.Math.signum(self.kWBase * self.kVArBase)
                else:
                    # leave it as it is
                    pass
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                self.kWBase = self.kVABase * self.Math.abs(self.PFNominal)
                self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
                if self.PFNominal < 0.0:
                    self.kVArBase = -self.kVArBase
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                if self.PFChanged:
                    # recompute kVAr base
                    self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
                    if self.PFNominal < 0.0:
                        self.kVArBase = -self.kVArBase
                    self.kVABase = self.Math.sqrt(self.Math.pow(self.kWBase, 2) + self.Math.pow(self.kVArBase, 2))
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                if self.PFChanged:
                    # recompute kVAr base
                    self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
                    if self.PFNominal < 0.0:
                        self.kVArBase = -self.kVArBase
                    self.kVABase = self.Math.sqrt(self.Math.pow(self.kWBase, 2) + self.Math.pow(self.kVArBase, 2))
                break
            break
        self.setNominalLoad()
        # Now check for errors. If any of these came out nil and the string was not nil, give warning.
        if self.yearlyShape.equalsIgnoreCase('none'):
            self.yearlyShape = ''
        if self.dailyShape.equalsIgnoreCase('none'):
            self.dailyShape = ''
        if self.dutyShape.equalsIgnoreCase('none'):
            self.dutyShape = ''
        if self.yearlyShapeObj is None:
            if len(self.yearlyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Yearly load shape: \"' + self.yearlyShape + '\" not found.', 583)
        if self.dailyShapeObj is None:
            if len(self.dailyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Daily load shape: \"' + self.dailyShape + '\" not found.', 584)
        if self.dutyShapeObj is None:
            if len(self.dutyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Duty load shape: \"' + self.dutyShape + '\" not found.', 585)
        if self.growthShapeObj is None:
            if len(self.growthShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Yearly growth shape: \"' + self.growthShape + '\" not found.', 586)
        if self.CVRShapeObj is None:
            if len(self.CVRshape) > 0:
                DSSGlobals.doSimpleMsg('Warning: CVR shape shape: \"' + self.CVRshape + '\" not found.', 586)
        self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
        if self.getSpectrumObj() is None:
            DSSGlobals.doSimpleMsg('Error: Spectrum \"' + self.getSpectrum() + '\" not found.', 587)
        if self.RNeut < 0.0:
            # flag for open neutral
            self.Yneut = Complex(0.0, 0.0)
        elif self.RNeut == 0.0 and self.XNeut == 0.0:
            # solidly grounded
            self.Yneut = Complex(1000000.0, 0.0)
            # 1 microohm resistor
        else:
            self.Yneut = ComplexUtil.invert(Complex(self.RNeut, self.XNeut))
        self.varBase = (1000.0 * self.kVArBase) / self.nPhases
        self.YQFixed = -self.varBase / self.Math.pow(self.VBase, 2)
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))
        self.setPFChanged(False)

    def calcYPrimMatrix(self, YMatrix):
        self.YPrimFreq = DSSGlobals.activeCircuit.getSolution().getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        Y = self.Yeq
        Y = Complex(Y.getReal(), Y.getImaginary() / freqMultiplier)
        # Correct reactive part for frequency
        Yij = Y.negate()
        _0 = self.connection
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    YMatrix.setElement(i, i, Y)
                    YMatrix.addElement(self.nConds, self.nConds, Y)
                    YMatrix.setElemSym(i, self.nConds, Yij)
                YMatrix.addElement(self.nConds, self.nConds, self.Yneut)
                # neutral
                # If neutral is floating, make sure there is some small
                # connection to ground  by increasing the last diagonal slightly.

                if self.RNeut < 0.0:
                    YMatrix.setElement(self.nConds, self.nConds, YMatrix.getElement(self.nConds, self.nConds).multiply(1.000001))
                break
            if (_1 is True) or (_0 == 1):
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
                    j = i + 1
                    if j > self.nConds:
                        j = 0
                        # wrap around for closed connections
                    YMatrix.addElement(i, i, Y)
                    YMatrix.addElement(j, j, Y)
                    YMatrix.addElemSym(i, j, Yij)
                    # get both off-diagonal elements
                break
            break

    def calcYPrim(self):
        """If doing an analysis that requires the load to be modeled as an impedance
        then put all in.
        """
        # build only YPrim shunt for a load then copy to YPrim
        # build a dummy Yprim series so that calcV does not fail
        if self.isYprimInvalid():
            if self.YPrimShunt is not None:
                self.YPrimShunt = None
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            if self.YPrim is not None:
                self.YPrim = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            self.YPrimShunt = CMatrixImpl(self.YOrder)
            self.YPrim = CMatrixImpl(self.YOrder)
        else:
            self.YPrimShunt.clear()
            self.YPrimSeries.clear()
            self.YPrim.clear()
        if (
            DSSGlobals.activeCircuit.getSolution().getLoadModel() == DSSGlobals.POWERFLOW
        ):
            self.setNominalLoad()
            # same as admittance model
            self.calcYPrimMatrix(self.YPrimShunt)
        else:
            # admittance model wanted
            self.setNominalLoad()
            self.calcYPrimMatrix(self.YPrimShunt)
        # set YPrim_Series based on diagonals of YPrim_Shunt so that calcVoltages doesn't fail
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            self.YPrimSeries.setElement(i, i, self.YPrimShunt.getElement(i, i).multiply(1e-10))
        self.YPrim.copyFrom(self.YPrimShunt)
        # account for open conductors
        super(LoadObjImpl, self).calcYPrim()

    def stickCurrInTerminalArray(self, termArray, curr, i):
        """Put the current into the proper location according to connection."""
        _0 = self.connection
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                termArray[i] = termArray[i].add(curr.negate())
                termArray[self.nConds] = termArray[self.nConds].add(curr)
                # neutral
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                termArray[i] = termArray[i].add(curr.negate())
                j = i + 1
                if j > self.nConds:
                    j = 0
                    # rotate the phases
                termArray[j] = termArray[j].add(curr)
                break
            break

    def updateVoltageBases(self):
        al = LoadImpl.activeLoadObj
        _0 = self.connection
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                al.setVBase(self.kVLoadBase * 1000.0)
                break
            if True:
                _1 = True
                _2 = self.nPhases
                _3 = False
                while True:
                    if _2 == 2:
                        _3 = True
                        al.setVBase(self.kVLoadBase * DSSGlobals.InvSQRT3x1000)
                        break
                    if (_3 is True) or (_2 == 3):
                        _3 = True
                        al.setVBase(self.kVLoadBase * DSSGlobals.InvSQRT3x1000)
                        break
                    if True:
                        _3 = True
                        al.setVBase(self.kVLoadBase * 1000.0)
                        # 1-phase or unknown
                        break
                    break
                break
            break

    def doConstantPQLoad(self):
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            V = self.VTerminal[i]
            VMag = V.abs()
            if VMag <= self.VBase95:
                curr = self.Yeq95.multiply(V)
                # below 95% use an impedance model
            elif VMag > self.VBase105:
                curr = self.Yeq105.multiply(V)
                # above 105% use an impedance model
            else:
                curr = Complex(self.WNominal, self.varNominal).divide(V).conjugate()
                # above 95%, constant PQ
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doConstantZLoad(self):
        # assume Yeq is kept up to date
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent Array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            curr = self.Yeq.multiply(self.VTerminal[i])
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doMotorTypeLoad(self):
        """Constant P, quadratic Q"""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            V = self.VTerminal[i]
            VMag = V.abs()
            if VMag <= self.VBase95:
                curr = self.Yeq95.multiply(V)
                # below 95% use an impedance model
            elif VMag > self.VBase105:
                curr = self.Yeq105.multiply(V)
                # above 105% use an impedance model
            else:
                curr = Complex(self.WNominal, 0.0).divide(V).conjugate()
                # above 95%, constant P
                curr = curr.add(Complex(0.0, self.Yeq.getImaginary()).multiply(V))
                # add in Q component of current
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doConstantILoad(self):
        """Constant current load."""
        # computes the current assuming the voltage mag is VBase
        # just uses the phase angle off the voltage
        # Injection = [s/v]* = [ (P+jQ)/(Vbase * V/|V|)]*
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            V = self.VTerminal[i]
            curr = Complex(self.WNominal, self.varNominal).divide(ComplexUtil.divide(V, V.abs()).multiply(self.VBase)).conjugate()
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doZIPVModel(self):
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            V = self.VTerminal[i]
            VMag = V.abs()
            if VMag <= self.VBase95:
                curr = self.Yeq95.multiply(V)
            elif VMag > self.VBase105:
                curr = self.Yeq105.multiply(V)
            else:
                currZ = Complex(self.Yeq.getReal() * self.ZIPV[0], self.Yeq.getImaginary() * self.ZIPV[3]).multiply(self.VTerminal[i])
                currI = Complex(self.WNominal * self.ZIPV[1], self.varNominal * self.ZIPV[4]).divide(ComplexUtil.divide(V, V.abs()).multiply(self.VBase)).conjugate()
                currP = Complex(self.WNominal * self.ZIPV[2], self.varNominal * self.ZIPV[5]).divide(V).conjugate()
                curr = currZ.add(currI.add(currP))
            # low-voltage drop-out
            if self.ZIPV[6] > 0.0:
                vx = 500.0 * ((VMag / self.VBase) - self.ZIPV[6])
                evx = self.Math.exp(2 * vx)
                yv = 0.5 * (1 + ((evx - 1) / (evx + 1)))
                curr = curr.multiply(yv)
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doCVRModel(self):
        """Linear P, quadratic Q."""
        # var current
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        try:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                V = self.VTerminal[i]
                VMag = V.abs()
                VRatio = VMag / self.VBase
                # VBase is l-n for wye and l-l for delta
                # linear factor adjustment does not converge for some reason while power adjust does easily
                # WattFactor = (1.0 + FCVRwattFactor*(Vmag/VBase - 1.0));
                if self.CVRwattFactor != 1.0:
                    wattFactor = self.Math.pow(VRatio, self.CVRwattFactor)
                else:
                    wattFactor = VRatio
                    # old value (in error): 1.0;
                if wattFactor > 0.0:
                    curr = Complex(self.WNominal * wattFactor, 0.0).divide(V).conjugate()
                else:
                    curr = Complex.ZERO
                    # P component of current
                # Compute Q component of current
                if self.CVRvarFactor == 2.0:
                    # Check for easy, quick ones first
                    CVar = Complex(0.0, self.Yeq.getImaginary()).multiply(V)
                    # 2 is same as constant impedance
                elif self.CVRvarFactor == 3.0:
                    varFactor = self.Math.pow(VRatio, 3)
                    # writeDLLDebugFile(String.format("%s, V=%.6g +j %.6g", getName(), V.getReal(), V.getImaginary()));
                    CVar = Complex(0.0, self.varNominal * varFactor).divide(V).conjugate()
                else:
                    # Other VAr factor code here if not squared or cubed
                    varFactor = self.Math.pow(VRatio, self.CVRvarFactor)
                    CVar = Complex(0.0, self.varNominal * varFactor).divide(V).conjugate()
                curr = curr.add(CVar)
                # add in Q component of current
                # writeDLLDebugFile(String.format("%s, %d, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g, %-.5g ", getName(), i, Vmag, VRatio, WNominal, WattFactor, varNominal, VarFactor, Curr.abs(), V.multiply(Curr.conjugate()).getReal()));
                self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
                # put into terminal array taking into account connection
                self.setITerminalUpdated(True)
                self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
                # put into terminal array taking into account connection
        except Exception, e:
            DSSGlobals.doSimpleMsg(String.format.format('Error in Load.%s: %s ', self.getName(), e.getMessage()), 5871)

    def doFixedQ(self):
        """Constant P, Fixed Q. Q is always kvarBase."""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            V = self.VTerminal[i]
            VMag = V.abs()
            if VMag <= self.VBase95:
                curr = Complex(self.Yeq95.getReal(), self.YQFixed).multiply(V)
                # below 95% use an impedance model
            elif VMag > self.VBase105:
                curr = Complex(self.Yeq105.getReal(), self.YQFixed).multiply(V)
                # above 105% use an impedance model
            else:
                curr = Complex(self.WNominal, self.varBase).divide(V).conjugate()
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doFixedQZ(self):
        """Constant P, Fixed Q. Q is always a fixed Z derived from kvarBase."""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            V = self.VTerminal[i]
            VMag = V.abs()
            if VMag <= self.VBase95:
                curr = Complex(self.Yeq95.getReal(), self.YQFixed).multiply(V)
                # below 95% use an impedance model
            elif VMag > self.VBase105:
                curr = Complex(self.Yeq105.getReal(), self.YQFixed).multiply(V)
            else:
                curr = Complex(self.WNominal, 0.0).divide(V).conjugate()
                # P component of current
                curr = curr.add(Complex(0.0, self.YQFixed).multiply(V))
                # add in Q component of current
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doHarmonicMode(self):
        """Compute injection current only when in harmonics mode.
        Assumes spectrum is an ideal current source based on the fundamental current and spectrum.
        """
        # Don't calc VTerminal here because it could be undefined
        self.zeroInjCurrent()
        self.zeroITerminal()
        sol = DSSGlobals.activeCircuit.getSolution()
        loadHarmonic = sol.getFrequency() / self.loadFundamental
        # loadFundamental = frequency of solution when harmonic mode entered
        mult = self.getSpectrumObj().getMult(loadHarmonic)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            curr = mult.multiply(self.harmMag[i])
            # get base harmonic magnitude
            curr = Utilities.rotatePhasorDeg(curr, loadHarmonic, self.harmAng[i])
            # time shift by fundamental
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)

    def allTerminalsClosed(self):
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
                if not (j < self.nConds):
                    break
                if not self.terminals[i].getConductors()[j].isClosed():
                    return False
        return True

    def calcVTerminalPhase(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        # Establish phase voltages and stick in VTemp
        _0 = self.connection
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    self.VTerminal[i] = sol.vDiff(self.nodeRef[i], self.nodeRef[self.nConds])
                break
            if (_1 is True) or (_0 == 1):
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
                    j = i + 1
                    # TODO Check zero based indexing
                    if j > self.nConds:
                        j = 0
                    self.VTerminal[i] = sol.vDiff(self.nodeRef[i], self.nodeRef[j])
                break
            break
        self.loadSolutionCount = sol.getSolutionCount()

    def calcLoadModelContribution(self):
        """Calculates total load current and adds it properly into the injCurrent array."""
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        self.setITerminalUpdated(False)
        if sol.isDynamicModel():
            # doDynamicMode();  // TODO Implement dynamic mode
            pass
        elif sol.isHarmonicModel() and sol.getFrequency() != ckt.getFundamental():
            self.doHarmonicMode()
        else:
            # compute total load currents and add into injCurrent array
            _0 = self.loadModel
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.doConstantPQLoad()
                    # normal load-flow type load
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    self.doConstantZLoad()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    self.doMotorTypeLoad()
                    # constant P, quadratic Q;
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    self.doCVRModel()
                    # mixed motor/resistive load with CVR factors
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    self.doConstantILoad()
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.doFixedQ()
                    # fixed Q
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    self.doFixedQZ()
                    # fixed, constant Z Q
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    self.doZIPVModel()
                    break
                if True:
                    _1 = True
                    self.doConstantZLoad()
                    # for now, until we implement the other models
                    break
                break

    def calcInjCurrentArray(self):
        """Fill injCurrent array with the current values to use for injections."""
        ckt = DSSGlobals.activeCircuit
        # if a terminal is open, then standard load models don't apply, so check it out first
        if self.allTerminalsClosed():
            # now get injection currents
            self.calcLoadModelContribution()
        else:
            # FIXME: THIS MAY NOT WORK !!! WATCH FOR BAD RESULTS
            # some terminals not closed use admittance model for injection
            if self.openLoadSolutionCount != ckt.getSolution().getSolutionCount():
                # rebuild the YPrimOpenCond if a new solution because values may have changed
                # only reallocate when necessary
                if self.YPrimOpenCond is None:
                    self.YPrimOpenCond = CMatrixImpl(self.YOrder)
                else:
                    self.YPrimOpenCond.clear()
                if self.YPrimOpenCond.order() != self.YOrder:
                    self.YPrimOpenCond = None
                    self.YPrimOpenCond = CMatrixImpl(self.YOrder)
                self.calcYPrimMatrix(self.YPrimOpenCond)
                # Now account for the open conductors
                # For any conductor that is open, zero out row and column
                k = 0
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
                        if not (j < self.nConds):
                            break
                        if not self.terminals[i].getConductors()[j].isClosed():
                            self.YPrimOpenCond.zeroRow(j + k)
                            self.YPrimOpenCond.zeroCol(j + k)
                            self.YPrimOpenCond.setElement(j + k, j + k, Complex(1e-12, 0.0))
                            # in case node gets isolated
                    k = k + self.nConds
                self.openLoadSolutionCount = ckt.getSolution().getSolutionCount()
            self.computeVTerminal()
            self.YPrimOpenCond.vMult(self.complexBuffer, self.VTerminal)
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                self.complexBuffer[i] = self.complexBuffer[i].negate()

    def getTerminalCurrents(self, Curr):
        """Always return total terminal currents in the curr array"""
        sol = DSSGlobals.activeCircuit.getSolution()
        if self.ITerminalSolutionCount != sol.getSolutionCount():
            # recalc the contribution
            self.calcLoadModelContribution()
            # adds totals in ITerminal as a side effect
        super(LoadObjImpl, self).getTerminalCurrents(Curr)

    def injCurrents(self):
        """Get the injection currents and add them directly into the currents array."""
        sol = DSSGlobals.activeCircuit.getSolution()
        result = 0
        if self.isEnabled():
            if sol.loadsNeedUpdating():
                self.setNominalLoad()
                # set the nominal kW, etc. for the type of solution being done
            self.calcInjCurrentArray()
            result = super(LoadObjImpl, self).injCurrents()
            # add into global currents array
        return result

    def getInjCurrents(self, curr):
        """Gets the injection currents for the last solution performed.
        Do not call setNominalLoad, as that may change the load values.
        """
        try:
            if self.isEnabled():
                self.calcInjCurrentArray()
                # copy into buffer array
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.YOrder):
                        break
                    curr[i] = self.getInjCurrent()[i]
            else:
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < len(curr)):
                        break
                    curr[i] = Complex.ZERO
        except Exception, e:
            DSSGlobals.doErrorMsg('Load object: \"' + self.getName() + '\" in getInjCurrents function.', e.getMessage(), 'Current buffer may not big enough.', 588)

    def getUnserved(self):
        """Line overload takes precedence.
        Assumes that low voltage is due to overloaded line.
        If voltage is below emergency minimum, it is counted as unserved.
        """
        ckt = DSSGlobals.activeCircuit
        if self.UE_Factor > 0.0:
            return True
            # else check voltages
        if self.loadSolutionCount != ckt.getSolution().getSolutionCount():
            self.calcVTerminalPhase()
            # get the lowest of the phase voltages
        Vpu = self.VBase
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            VMag = self.VTerminal[i].abs()
            if VMag < Vpu:
                Vpu = VMag
        Vpu = Vpu / self.VBase
        if self.VMinNormal != 0.0:
            normMinCriteria = self.VMinNormal
        else:
            normMinCriteria = ckt.getNormalMinVolts()
        if self.VMinEmerg != 0.0:
            emergMinCriteria = self.VMinEmerg
        else:
            emergMinCriteria = ckt.getEmergMinVolts()
        if Vpu < emergMinCriteria:
            # UE_Factor = 1.0;
            # 9-19-00 RCD  let UE_Factor start small and grow linearly at same slope
            # as EEN_Factor
            self.UE_Factor = (emergMinCriteria - Vpu) / (normMinCriteria - emergMinCriteria)
            return True
        return False

    def getExceedsNormal(self):
        """Line overload takes precedence.
        Assumes that low voltage is due to overloaded line.
        If voltage is below normal minimum, it is counted as unserved in proportion
        to the difference between the normal and emergency voltage limits.
        """
        ckt = DSSGlobals.activeCircuit
        if self.EEN_Factor > 0.0:
            return True
            # check line overload
        if self.loadSolutionCount != ckt.getSolution().getSolutionCount():
            self.calcVTerminalPhase()
            # get the lowest of the phase voltages
        Vpu = self.VBase
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            VMag = self.VTerminal[i].abs()
            if VMag < Vpu:
                Vpu = VMag
        Vpu = Vpu / self.VBase
        if self.VMinNormal != 0.0:
            normMinCriteria = self.VMinNormal
        else:
            normMinCriteria = ckt.getNormalMinVolts()
        if self.VMinEmerg != 0.0:
            emergMinCriteria = self.VMinEmerg
        else:
            emergMinCriteria = ckt.getEmergMinVolts()
        if Vpu < normMinCriteria:
            self.EEN_Factor = (normMinCriteria - Vpu) / (normMinCriteria - emergMinCriteria)
            # 9-19-00 RCD  Let EEN factor grow linearly at same slope
            # IF EEN_Factor > 1.0 THEN EEN_Factor = 1.0;
            return True
        return False

    def dumpProperties(self, f, complete):
        super(LoadObjImpl, self).dumpProperties(f, complete)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            _1 = i
            _2 = False
            while True:
                if _1 == 3:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.kWBase
                    break
                if (_2 is True) or (_1 == 4):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.PFNominal
                    break
                if (_2 is True) or (_1 == 11):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.kVArBase
                    break
                if (_2 is True) or (_1 == 21):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.kVAAllocationFactor
                    break
                if (_2 is True) or (_1 == 22):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.kVABase
                    break
                if (_2 is True) or (_1 == 32):
                    _2 = True
                    f.print_('~ ' + self.getParentClass().getPropertyName()[i] + '=')
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < self.nZIPV):
                            break
                        f.print_(self.ZIPV[j] + ' ')
                    print '\"'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(i)
                    break
                break

    def setKVAAllocationFactor(self, value):
        """Allocate load from connected kVA or kWh billing."""
        self.setKVAAllocationFactor(value)
        self.allocationFactor = value
        self.loadSpecType = 3
        self.computeAllocatedLoad()
        self.hasBeenAllocated = True

    def setAllocationFactor(self, value):
        """AllocationFactor adjusts either connected kVA allocation factor or kWh CFactor.

        This procedure is used by the EnergyMeter allocateLoad function to adjust load allocation factors.
        """
        self.allocationFactor = value
        _0 = self.loadSpecType
        _1 = False
        while True:
            if _0 == 3:
                _1 = True
                self.setKVAAllocationFactor(value)
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                self.setCFactor(value)
                break
            break
        self.computeAllocatedLoad()
        # update kWBase
        self.hasBeenAllocated = True

    def setCFactor(self, value):
        self.CFactor = value
        self.allocationFactor = value
        self.loadSpecType = 4
        self.computeAllocatedLoad()
        self.hasBeenAllocated = True

    def setConnectedKVA(self, value):
        self.connectedkVA = value
        self.loadSpecType = 3
        self.allocationFactor = self.kVAAllocationFactor
        self.computeAllocatedLoad()

    def setKWh(self, value):
        self.kWh = value
        self.loadSpecType = 4
        self.allocationFactor = self.CFactor
        self.computeAllocatedLoad()

    def setKWhDays(self, value):
        self.kWhDays = value
        self.loadSpecType = 4
        self.computeAllocatedLoad()

    def computeAllocatedLoad(self):
        """Fixed loads defined by kW, kVAr or kW, PF are ignored."""
        _0 = self.loadSpecType
        _1 = False
        while True:
            if _0 == 3:
                _1 = True
                if self.connectedkVA > 0.0:
                    self.kWBase = self.connectedkVA * self.kVAAllocationFactor * self.Math.abs(self.PFNominal)
                    self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
                    if self.PFNominal < 0.0:
                        self.kVArBase = -self.kVArBase
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                self.avgKW = self.kWh / (self.kWhDays * 24)
                self.kWBase = self.avgKW * self.CFactor
                self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
                if self.PFNominal < 0.0:
                    self.kVArBase = -self.kVArBase
                break
            break

    def initHarmonics(self):
        """Get the present terminal currents and store for harmonics base reference."""
        # Make sure there's enough memory
        self.harmMag = Utilities.resizeArray(self.harmMag, self.nPhases)
        self.harmAng = Utilities.resizeArray(self.harmAng, self.nPhases)
        currents = [None] * self.YOrder
        # to hold currents
        self.loadFundamental = DSSGlobals.activeCircuit.getSolution().getFrequency()
        self.getCurrents(currents)
        # Store the currents at fundamental frequency.
        # The spectrum is applied to these.

        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.harmMag[i] = currents[i].abs()
            self.harmAng[i] = ComplexUtil.degArg(currents[i])
        currents = None

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '3'
        # "phases";
        self.propertyValue[1] = self.getBus(0)
        # "bus1";
        self.propertyValue[2] = '12.47'
        self.propertyValue[3] = '10'
        self.propertyValue[4] = '.88'
        self.propertyValue[5] = '1'
        self.propertyValue[6] = ''
        self.propertyValue[7] = ''
        self.propertyValue[8] = ''
        self.propertyValue[9] = ''
        self.propertyValue[10] = 'wye'
        self.propertyValue[11] = '5'
        self.propertyValue[12] = '-1'
        # "rneut"; // if entered -, assume open or user defined
        self.propertyValue[13] = '0'
        # "xneut";
        self.propertyValue[14] = 'variable'
        # "status";  // fixed or variable
        self.propertyValue[15] = '1'
        # class
        self.propertyValue[16] = '0.95'
        self.propertyValue[17] = '1.05'
        self.propertyValue[18] = '0.0'
        self.propertyValue[19] = '0.0'
        self.propertyValue[20] = '0.0'
        self.propertyValue[21] = '0.5'
        # allocation factor
        self.propertyValue[22] = '11.3636'
        self.propertyValue[23] = '50'
        self.propertyValue[24] = '10'
        self.propertyValue[25] = '1'
        # CVR watt factor
        self.propertyValue[26] = '2'
        # CVR var factor
        self.propertyValue[27] = '0'
        # kwh bulling
        self.propertyValue[28] = '30'
        # kwhdays
        self.propertyValue[29] = '4'
        # Cfactor
        self.propertyValue[30] = ''
        # CVRCurve
        self.propertyValue[31] = '1'
        # NumCust
        self.propertyValue[32] = ''
        # ZIPV coefficient array
        super(LoadObjImpl, self).initPropertyValues(Load.NumPropsThisClass)

    def makePosSequence(self):
        """Make a positive sequence model."""
        s = 'phases=1 conn=wye'
        # make sure voltage is line-neutral
        if (self.nPhases > 1) or (self.connection != 0):
            V = self.kVLoadBase / DSSGlobals.SQRT3
        else:
            V = self.kVLoadBase
        s = s + String.format.format(' kV=%-.5g', V)
        # divide the load by no. phases
        if self.nPhases > 1:
            s = s + String.format.format(' kW=%-.5g  kvar=%-.5g', self.kWBase / self.nPhases, self.kVArBase / self.nPhases)
            if self.connectedkVA > 0.0:
                s = s + String.format.format(' xfkVA=%-.5g  ', self.connectedkVA / self.nPhases)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(LoadObjImpl, self).makePosSequence()

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == 1:
                _1 = True
                return self.getBus(0)
            if (_1 is True) or (_0 == 2):
                _1 = True
                return String.format.format('%-g', self.kVLoadBase)
            if (_1 is True) or (_0 == 3):
                _1 = True
                return String.format.format('%-g', self.kWBase)
            if (_1 is True) or (_0 == 4):
                _1 = True
                return String.format.format('%-.3g', self.PFNominal)
            if (_1 is True) or (_0 == 6):
                _1 = True
                return self.yearlyShape
            if (_1 is True) or (_0 == 7):
                _1 = True
                return self.dailyShape
            if (_1 is True) or (_0 == 8):
                _1 = True
                return self.dutyShape
            if (_1 is True) or (_0 == 11):
                _1 = True
                return String.format.format('%-.3g', self.kVArBase)
            if (_1 is True) or (_0 == 21):
                _1 = True
                return String.format.format('%-.3g', self.kVAAllocationFactor)
            if (_1 is True) or (_0 == 22):
                _1 = True
                return String.format.format('%-g', self.kVABase)
            if (_1 is True) or (_0 == 29):
                _1 = True
                return String.format.format('%-.3g', self.CFactor)
            if (_1 is True) or (_0 == 32):
                _1 = True
                result = ''
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nZIPV):
                        break
                    result = result + String.format.format(' %-g', self.ZIPV[i])
                return result
            if True:
                _1 = True
                return super(LoadObjImpl, self).getPropertyValue(index)
            break

    def getKVAAllocationFactor(self):
        return self.kVAAllocationFactor

    def getConnectedKVA(self):
        return self.connectedkVA

    def getCFactor(self):
        return self.CFactor

    def getKWh(self):
        return self.kWh

    def getKWhDays(self):
        return self.kWhDays

    def getAllocationFactor(self):
        return self.allocationFactor

    def getConnection(self):
        return self.connection

    def setConnection(self, conn):
        self.connection = conn

    def getDailyShape(self):
        return self.dailyShape

    def setDailyShape(self, shape):
        self.dailyShape = shape

    def getDailyShapeObj(self):
        return self.dailyShapeObj

    def setDailyShapeObj(self, shapeObj):
        self.dailyShapeObj = shapeObj

    def getDutyShape(self):
        return self.dutyShape

    def setDutyShape(self, shape):
        self.dutyShape = shape

    def getDutyShapeObj(self):
        return self.dutyShapeObj

    def setDutyShapeObj(self, shapeObj):
        self.dutyShapeObj = shapeObj

    def getEEN_Factor(self):
        return self.EEN_Factor

    def setEEN_Factor(self, factor):
        self.EEN_Factor = factor

    def getGrowthShape(self):
        return self.growthShape

    def setGrowthShape(self, shape):
        self.growthShape = shape

    def getGrowthShapeObj(self):
        return self.growthShapeObj

    def setGrowthShapeObj(self, shapeObj):
        self.growthShapeObj = shapeObj

    def getHasBeenAllocated(self):
        return self.hasBeenAllocated

    def setHasBeenAllocated(self, allocated):
        self.hasBeenAllocated = allocated

    def getKWBase(self):
        return self.kWBase

    def setKWBase(self, base):
        self.kWBase = base

    def getKVABase(self):
        return self.kVABase

    def setKVABase(self, base):
        self.kVABase = base

    def getKVArBase(self):
        return self.kVArBase

    def setKVArBase(self, base):
        self.kVArBase = base

    def getKVLoadBase(self):
        return self.kVLoadBase

    def setKVLoadBase(self, base):
        self.kVLoadBase = base

    def getLoadClass(self):
        return self.loadClass

    def setLoadClass(self, cls):
        self.loadClass = cls

    def getNumCustomers(self):
        return self.numCustomers

    def setNumCustomers(self, num):
        self.numCustomers = num

    def getLoadSpecType(self):
        return self.loadSpecType

    def setLoadSpecType(self, specType):
        self.loadSpecType = specType

    def getPFNominal(self):
        return self.PFNominal

    def setPFNominal(self, value):
        self.PFNominal = value

    def getRNeut(self):
        return self.RNeut

    def setRNeut(self, rneut):
        self.RNeut = rneut

    def getUE_Factor(self):
        return self.UE_Factor

    def setUE_Factor(self, factor):
        self.UE_Factor = factor

    def getXNeut(self):
        return self.XNeut

    def setXNeut(self, xneut):
        self.XNeut = xneut

    def getYearlyShape(self):
        return self.yearlyShape

    def setYearlyShape(self, shape):
        self.yearlyShape = shape

    def getYearlyShapeObj(self):
        return self.yearlyShapeObj

    def setYearlyShapeObj(self, shapeObj):
        self.yearlyShapeObj = shapeObj

    def getCVRShape(self):
        return self.CVRshape

    def setCVRShape(self, shape):
        self.CVRshape = shape

    def getCVRShapeObj(self):
        return self.CVRShapeObj

    def setCVRShapeObj(self, shapeObj):
        self.CVRShapeObj = shapeObj

    def getLoadModel(self):
        return self.loadModel

    def setLoadModel(self, model):
        self.loadModel = model

    def getPUMean(self):
        return self.puMean

    def getPUStdDev(self):
        return self.puStdDev

    def getCVRWattFactor(self):
        return self.CVRwattFactor

    def getCVRVArFactor(self):
        return self.CVRvarFactor

    def getVMaxPU(self):
        return self.VMaxPU

    def getVMinEmerg(self):
        return self.VMinEmerg

    def getVMinNormal(self):
        return self.VMinNormal

    def getVMinPU(self):
        return self.VMinPU

    def isExemptFromLDCurve(self):
        return self.exemptFromLDCurve

    def isFixed(self):
        # FIXME Private members in OpenDSS.
        return self.fixed

    def isPFChanged(self):
        return self.PFChanged

    def setPFChanged(self, changed):
        self.PFChanged = changed

    def getAvgKW(self):
        return self.avgKW

    def setAvgKW(self, avg):
        self.avgKW = avg

    def getHarmAng(self):
        return self.harmAng

    def setHarmAng(self, ang):
        self.harmAng = ang

    def getHarmMag(self):
        return self.harmMag

    def setHarmMag(self, mag):
        self.harmMag = mag

    def getLastGrowthFactor(self):
        return self.lastGrowthFactor

    def setLastGrowthFactor(self, factor):
        self.lastGrowthFactor = factor

    def getLastYear(self):
        return self.lastYear

    def setLastYear(self, year):
        self.lastYear = year

    def getLoadFundamental(self):
        return self.loadFundamental

    def setLoadFundamental(self, fundamental):
        self.loadFundamental = fundamental

    def getLoadSolutionCount(self):
        return self.loadSolutionCount

    def setLoadSolutionCount(self, count):
        self.loadSolutionCount = count

    def getOpenLoadSolutionCount(self):
        return self.openLoadSolutionCount

    def setOpenLoadSolutionCount(self, count):
        self.openLoadSolutionCount = count

    def getRandomMult(self):
        return self.randomMult

    def setRandomMult(self, mult):
        self.randomMult = mult

    def getShapeFactor(self):
        return self.shapeFactor

    def setShapeFactor(self, factor):
        self.shapeFactor = factor

    def getVArBase(self):
        return self.varBase

    def setVArBase(self, base):
        self.varBase = base

    def getVArNominal(self):
        return self.varNominal

    def setVArNominal(self, value):
        self.varNominal = value

    def getVBase(self):
        return self.VBase

    def setVBase(self, vBase):
        self.VBase = vBase

    def getVBase105(self):
        return self.VBase105

    def setVBase105(self, vBase105):
        self.VBase105 = vBase105

    def getVBase95(self):
        return self.VBase95

    def setVBase95(self, vBase95):
        self.VBase95 = vBase95

    def getWNominal(self):
        return self.WNominal

    def setWNominal(self, value):
        self.WNominal = value

    def getYeq(self):
        return self.Yeq

    def setYeq(self, yeq):
        self.Yeq = yeq

    def getYeq105(self):
        return self.Yeq105

    def setYeq105(self, yeq105):
        self.Yeq105 = yeq105

    def getYeq95(self):
        return self.Yeq95

    def setYeq95(self, yeq95):
        self.Yeq95 = yeq95

    def getYneut(self):
        return self.Yneut

    def setYneut(self, value):
        self.Yneut = value

    def getYPrimOpenCond(self):
        return self.YPrimOpenCond

    def setYPrimOpenCond(self, value):
        self.YPrimOpenCond = value

    def getYQFixed(self):
        return self.YQFixed

    def setYQFixed(self, value):
        self.YQFixed = value

    def setPUMean(self, value):
        self.puMean = value

    def setPUStdDev(self, value):
        self.puStdDev = value

    def setCVRWattFactor(self, factor):
        self.CVRwattFactor = factor

    def setCVRVArFactor(self, factor):
        self.CVRvarFactor = factor

    def setVMaxPU(self, vmaxpu):
        self.VMaxPU = vmaxpu

    def setVMinEmerg(self, vminEmerg):
        self.VMinEmerg = vminEmerg

    def setVMinNormal(self, vminNormal):
        self.VMinNormal = vminNormal

    def setVMinPU(self, vminpu):
        self.VMinPU = vminpu

    def setExemptFromLDCurve(self, exempt):
        self.exemptFromLDCurve = exempt

    def setFixed(self, value):
        self.fixed = value

    def shapeIsActual(self):
        return self.shapeIsActual

    def setShapeIsActual(self, isActual):
        self.shapeIsActual = isActual

    def getZIPV(self):
        return self.ZIPV

    def setZIPV(self, zipv):
        self.ZIPV = zipv

    def setNZIPV(self, nzipv):
        self.nZIPV = nzipv

    def getNZIPV(self):
        return self.nZIPV
