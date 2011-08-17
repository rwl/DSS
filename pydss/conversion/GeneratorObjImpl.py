from dss.conversion.Generator import (Generator,)
from dss.shared.Dynamics import (Dynamics,)
from dss.common.impl.Utilities import (Utilities,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.conversion.impl.GenUserModelImpl import (GenUserModelImpl,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.conversion.GeneratorObj import (GeneratorObj,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class GeneratorObjImpl(PCElementImpl, GeneratorObj):
    NumGenRegisters = 6
    NumGenVariables = 6
    CDOUBLEONE = Complex(1.0, 1.0)
    cBuffer = [None] * 24
    # temp buffer for calcs
    Yeq = None
    # at nominal
    Yeq95 = None
    # at 95%
    Yeq105 = None
    # at 105%
    currentLimit = None
    debugTrace = None
    deltaQMax = None
    dispatchMode = None
    dispatchValue = None
    dQdV = None
    dQdVSaved = None
    forcedON = None
    firstSampleAfterReset = None
    fixed = None
    # if fixed, always at base value
    generatorSolutionCount = None
    genFundamental = None
    genOn = None
    genSwitchOpen = None
    kVANotSet = None
    lastGrowthFactor = None
    lastYear = None
    openGeneratorSolutionCount = None
    PVFactor = None
    randomMult = None
    regHours = None
    regKVArh = None
    regKWh = None
    regMaxKVA = None
    regMaxKW = None
    regPrice = None
    shapeFactor = None
    thetaHarm = None
    traceFile = None
    userModel = None
    shaftModel = None
    VAvg = None
    VRemembered = None
    varRemembered = None
    varBase = None
    varMax = None
    varMin = None
    VBase = None
    VBase105 = None
    VBase95 = None
    VMaxPU = None
    VMinPU = None
    VThev = None
    VThevHarm = None
    VThevMag = None
    YPrimOpenCond = None
    YQFixed = None
    shapeIsActual = None
    connection = None
    dailyDispShape = None
    dailyDispShapeObj = None
    dutyShape = None
    dutyShapeObj = None
    genClass = None
    genModel = None
    genVars = None
    kVArBase = None
    kVArMax = None
    kVArMin = None
    kWBase = None
    PFNominal = None
    Vpu = None
    VTarget = None
    yearlyShape = None
    yearlyShapeObj = None
    registers = [None] * NumGenRegisters
    derivatives = [None] * NumGenRegisters

    def __init__(self, parClass, generatorName):
        super(GeneratorObjImpl, self)(parClass)
        self.setName(generatorName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # + GEN_ELEMENT;  // in both PC element and gen element list
        self.setNPhases(3)
        self.nConds = 4
        # defaults to wye
        self.YOrder = 0
        # to trigger an initial allocation
        self.setNTerms(1)
        # forces allocations
        self.kWBase = 1000.0
        self.kVArBase = 60.0
        self.kVArMax = self.kVArBase * 2.0
        self.kVArMin = -self.kVArMax
        self.PFNominal = 0.88
        # Rneut     = 0.0;
        # Xneut     = 0.0;
        self.yearlyShape = ''
        self.yearlyShapeObj = None
        # if YearlyShapeObj = null then the load alway stays nominal * global multipliers
        self.dailyDispShape = ''
        self.dailyDispShapeObj = None
        # if DaillyShapeObj = null then the load alway stays nominal * global multipliers
        self.dutyShape = ''
        self.dutyShapeObj = None
        # if DutyShapeObj = null then the load alway stays nominal * global multipliers
        self.connection = 0
        # Wye (star)
        self.genModel = 1
        # Typical fixed kW negative load
        self.genClass = 1
        self.lastYear = 0
        self.lastGrowthFactor = 1.0
        self.dQdVSaved = 0.0
        # initialize this here; allows generators to be turned off and on
        self.generatorSolutionCount = -1
        # for keep track of the present solution in injcurrent calcs
        self.openGeneratorSolutionCount = -1
        self.YPrimOpenCond = None
        self.genVars.kVGeneratorBase = 12.47
        self.Vpu = 1.0
        self.VTarget = (1000.0 * self.Vpu * self.genVars.kVGeneratorBase) / DSSGlobals.SQRT3
        # Line-to-Neutral target
        self.VBase = 7200.0
        self.VMinPU = 0.9
        self.VMaxPU = 1.1
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.YOrder = self.nTerms * self.nConds
        self.randomMult = 1.0
        self.fixed = False
        # Machine rating stuff
        self.genVars.kVARating = self.kWBase * 1.2
        self.kVANotSet = True
        # flag for default value for kVA
        # GenVars.Vd = 7200.0;
        self.genVars.puXd = 1.0
        self.genVars.puXdp = 0.28
        self.genVars.puXdpp = 0.2
        self.genVars.Xd = (self.genVars.puXd * self.Math.pow(self.genVars.kVGeneratorBase, 2) * 1000.0) / self.genVars.kVARating
        self.genVars.Xdp = (self.genVars.puXdp * self.Math.pow(self.genVars.kVGeneratorBase, 2) * 1000.0) / self.genVars.kVARating
        self.genVars.Xdpp = (self.genVars.puXdpp * self.Math.pow(self.genVars.kVGeneratorBase, 2) * 1000.0) / self.genVars.kVARating
        self.genVars.HMass = 1.0
        # W-sec/VA rating
        self.genVars.theta = 0.0
        self.genVars.w0 = DSSGlobals.TWO_PI * self.getBaseFrequency()
        self.genVars.speed = 0.0
        self.genVars.dSpeed = 0.0
        self.genVars.D = 1.0
        self.userModel = GenUserModelImpl(self.genVars)
        self.shaftModel = GenUserModelImpl(self.genVars)
        self.dispatchValue = 0.0
        # follow curves
        self.regKWh = 1
        self.regKVArh = 2
        self.regMaxKW = 3
        self.regMaxKVA = 4
        self.regHours = 5
        self.regPrice = 6
        self.PVFactor = 0.1
        self.debugTrace = False
        self.forcedON = False
        self.genSwitchOpen = False
        self.shapeIsActual = False
        self.spectrum = 'defaultgen'
        # override base class
        self.initPropertyValues(0)
        self.recalcElementData()

    def randomize(self, opt):
        """0 = reset to 1.0; 1 = Gaussian around mean and std Dev; 2 = uniform"""
        _0 = opt
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.randomMult = 1.0
                break
            if (_1 is True) or (_0 == DSSGlobals.GAUSSIAN):
                _1 = True
                self.randomMult = MathUtil.gauss(self.yearlyShapeObj.getMean(), self.yearlyShapeObj.getStdDev())
                break
            if (_1 is True) or (_0 == DSSGlobals.UNIFORM):
                _1 = True
                self.randomMult = self.Math.random()
                # number between 0 and 1.0
                break
            if (_1 is True) or (_0 == DSSGlobals.LOGNORMAL):
                _1 = True
                self.randomMult = MathUtil.quasiLognormal(self.yearlyShapeObj.getMean())
                break
            break

    def calcDailyMult(self, hr):
        if self.dailyDispShapeObj is not None:
            self.shapeFactor = self.dailyDispShapeObj.getMult(hr)
            self.shapeIsActual = self.dailyDispShapeObj.isUseActual()
        else:
            self.shapeFactor = self.CDOUBLEONE
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
            self.shapeFactor = self.CDOUBLEONE
            # defaults to no variation

    def setNominalGeneration(self):
        genOnSaved = self.genOn
        self.shapeFactor = self.CDOUBLEONE
        # check to make sure the generation is on
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if (not sol.isDynamicModel()) or (not sol.isHarmonicModel()):
            # leave generator in whatever state it was prior to entering dynamic mode
            self.genOn = True
            # init to on then check if it should be off
            if not self.forcedON:
                _0 = self.dispatchMode
                _1 = False
                while True:
                    if _0 == Generator.LOADMODE:
                        _1 = True
                        if (
                            self.dispatchValue > 0.0 and ckt.getGeneratorDispatchReference() < self.dispatchValue
                        ):
                            self.genOn = False
                        break
                    if (_1 is True) or (_0 == Generator.PRICEMODE):
                        _1 = True
                        if self.dispatchValue > 0.0 and ckt.getPriceSignal() < self.dispatchValue:
                            self.genOn = False
                        break
                    break
        if not self.genOn:
            # if generator is off enter as tiny resistive load (.0001 pu) so we don't get divide by zero in matrix
            self.genVars.PNominalPerPhase = (-0.1 * self.kWBase) / self.nPhases
            # Pnominalperphase   = 0.0;
            self.genVars.QNominalPerPhase = 0.0
        else:
            # generator is on, compute it's nominal watts and vars
            if self.isFixed():
                factor = 1.0
                # for fixed generators, set constant
            else:
                _2 = sol.getMode()
                _3 = False
                while True:
                    if _2 == Dynamics.SNAPSHOT:
                        _3 = True
                        factor = ckt.getGenMultiplier() * 1.0
                        break
                    if (_3 is True) or (_2 == Dynamics.DAILYMODE):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDailyMult(sol.getDblHour())
                        # daily dispatch curve
                        break
                    if (_3 is True) or (_2 == Dynamics.YEARLYMODE):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcYearlyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.DUTYCYCLE):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDutyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.GENERALTIME):
                        _3 = True
                        factor = ckt.getGenMultiplier()
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
                                self.shapeFactor = Complex(1.0, 1.0)
                                # default to 1 + j1 if not known
                                break
                            break
                        break
                    if (_3 is True) or (_2 == Dynamics.MONTECARLO1):
                        _3 = True
                        factor = ckt.getGenMultiplier() * 1.0
                        break
                    if (_3 is True) or (_2 == Dynamics.MONTEFAULT):
                        _3 = True
                        factor = ckt.getGenMultiplier() * 1.0
                        break
                    if (_3 is True) or (_2 == Dynamics.FAULTSTUDY):
                        _3 = True
                        factor = ckt.getGenMultiplier() * 1.0
                        break
                    if (_3 is True) or (_2 == Dynamics.DYNAMICMODE):
                        _3 = True
                        factor = ckt.getGenMultiplier() * 1.0
                        break
                    if (_3 is True) or (_2 == Dynamics.MONTECARLO2):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDailyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.MONTECARLO3):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDailyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.LOADDURATION1):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDailyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.LOADDURATION2):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDailyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.PEAKDAY):
                        _3 = True
                        factor = ckt.getGenMultiplier()
                        self.calcDailyMult(sol.getDblHour())
                        break
                    if (_3 is True) or (_2 == Dynamics.AUTOADDFLAG):
                        _3 = True
                        factor = 1.0
                        break
                    if True:
                        _3 = True
                        factor = 1.0
                        break
                    break
            if (not sol.isDynamicModel()) or sol.isHarmonicModel():
                if self.shapeIsActual:
                    self.genVars.PNominalPerPhase = (1000.0 * self.shapeFactor.getReal()) / self.nPhases
                else:
                    self.genVars.PNominalPerPhase = (1000.0 * self.kWBase * factor * self.shapeFactor.getReal()) / self.nPhases
                if self.genModel == 3:
                    # Just make sure present value is reasonable.
                    if self.genVars.QNominalPerPhase > self.varMax:
                        self.genVars.QNominalPerPhase = self.varMax
                    elif self.genVars.QNominalPerPhase < self.varMin:
                        self.genVars.QNominalPerPhase = self.varMin
                elif self.shapeIsActual:
                    self.genVars.QNominalPerPhase = (1000.0 * self.shapeFactor.getImaginary()) / self.nPhases
                else:
                    self.genVars.QNominalPerPhase = (1000.0 * self.kVArBase * factor * self.shapeFactor.getImaginary()) / self.nPhases
                # for other generator models
        # else genON
        if (not sol.isDynamicModel()) or sol.isHarmonicModel():
            _6 = self.genModel
            _7 = False
            while True:
                if _6 == 6:
                    _7 = True
                    self.Yeq = ComplexUtil.invert(Complex(0.0, -self.genVars.Xd))
                    # gets negated in calcYPrim
                    break
                if True:
                    _7 = True
                    self.Yeq = ComplexUtil.divide(Complex(self.genVars.PNominalPerPhase, -self.genVars.QNominalPerPhase), self.Math.pow(self.VBase, 2))
                    # VBase must be L-N for 3-phase
                    if self.VMinPU != 0.0:
                        self.Yeq95 = ComplexUtil.divide(self.Yeq, self.Math.pow(self.VMinPU, 2))
                        # at 95% voltage
                    else:
                        self.Yeq95 = self.Yeq
                        # always a constant Z model
                    if self.VMaxPU != 0.0:
                        self.Yeq105 = ComplexUtil.divide(self.Yeq, self.Math.pow(self.VMaxPU, 2))
                        # at 105% voltage
                    else:
                        self.Yeq105 = self.Yeq
                    break
                break
            # When we leave here, all the Yeq's are in L-N values
            if self.genModel == 7:
                self.currentLimit = ComplexUtil.divide(Complex(self.genVars.PNominalPerPhase, self.genVars.QNominalPerPhase), self.VBase95)
        # if generator state changes, force re-calc of Y matrix
        if self.genOn != genOnSaved:
            self.setYPrimInvalid(True)

    def recalcElementData(self):
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.varBase = (1000.0 * self.kVArBase) / self.nPhases
        self.varMin = (1000.0 * self.kVArMin) / self.nPhases
        self.varMax = (1000.0 * self.kVArMax) / self.nPhases
        # Populate data structures used for interchange with user-written models.
        self.genVars.Xd = (self.genVars.puXd * 1000.0 * self.Math.pow(self.genVars.kVGeneratorBase, 2)) / self.genVars.kVARating
        self.genVars.Xdp = (self.genVars.puXdp * 1000.0 * self.Math.pow(self.genVars.kVGeneratorBase, 2)) / self.genVars.kVARating
        self.genVars.Xdpp = (self.genVars.puXdpp * 1000.0 * self.Math.pow(self.genVars.kVGeneratorBase, 2)) / self.genVars.kVARating
        self.genVars.conn = self.connection
        self.genVars.numPhases = self.nPhases
        self.genVars.numConductors = self.nConds
        self.setNominalGeneration()
        # Now check for errors. If any of these came out nil and the string was not nil, give warning.
        if self.yearlyShape.equalsIgnoreCase('none'):
            self.yearlyShape = ''
        if self.dailyDispShape.equalsIgnoreCase('none'):
            self.dailyDispShape = ''
        if self.dutyShape.equalsIgnoreCase('none'):
            self.dutyShape = ''
        if self.yearlyShapeObj is None:
            if len(self.yearlyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Yearly load shape: \"' + self.yearlyShape + '\" not found.', 563)
        if self.dailyDispShapeObj is None:
            if len(self.dailyDispShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Daily load shape: \"' + self.dailyDispShape + '\" not found.', 564)
        if self.dutyShapeObj is None:
            if len(self.dutyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Duty load shape: \"' + self.dutyShape + '\" not found.', 565)
        self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
        if self.getSpectrumObj() is None:
            DSSGlobals.doSimpleMsg('Error: Spectrum \"' + self.getSpectrum() + '\" not found.', 566)
            # if (Rneut < 0.0) {  // flag for open neutral
            # 			YNeut = new Complex(0.0, 0.0);
            # 		} else if ((Rneut == 0.0) && (Xneut = 0.0)) {  // solidly grounded
            # 			YNeut = new Complex(1.0e6, 0.0);  // 1 microohm resistor
            # 		} else {
            # 			YNeut = new Complex(Rneut, XNeut).invert();
            # 		}

        self.YQFixed = -self.varBase / self.Math.pow(self.VBase, 2)
        # 10-17-02  Fixed negative sign
        self.VTarget = (self.Vpu * 1000.0 * self.genVars.kVGeneratorBase) / DSSGlobals.SQRT3
        # initialize to zero - defaults to PQ generator
        # solution object will reset after circuit modifications
        self.dQdV = self.dQdVSaved
        # for Model = 3
        self.deltaQMax = (self.varMax - self.varMin) * 0.1
        # limit to 10% of range
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))
        # Update any user-written models
        if self.userModel.exists():
            self.userModel.updateModel()
        if self.shaftModel.exists():
            self.shaftModel.updateModel()

    def calcYPrimMatrix(self, Ymatrix):
        ckt = DSSGlobals.activeCircuit
        self.YPrimFreq = ckt.getSolution().getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        sol = ckt.getSolution()
        if sol.isDynamicModel() or sol.isHarmonicModel():
            if self.genOn:
                Y = self.Yeq
                # L-N value computed in initialization routines
            else:
                Y = Complex(DSSGlobals.EPSILON, 0.0)
            if self.connection == 1:
                Y = ComplexUtil.divide(Y, 3.0)
                # convert to delta impedance
            Y = Complex(Y.getReal(), Y.getImaginary() / freqMultiplier)
            Yij = Y.negate()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                _1 = self.connection
                _2 = False
                while True:
                    if _1 == 0:
                        _2 = True
                        Ymatrix.setElement(i, i, Y)
                        Ymatrix.addElement(self.nConds, self.nConds, Y)
                        Ymatrix.setElemSym(i, self.nConds, Yij)
                        break
                    if (_2 is True) or (_1 == 1):
                        _2 = True
                        Ymatrix.setElement(i, i, Y)
                        Ymatrix.addElement(i, i, Y)
                        # put it in again
                        _3 = True
                        j = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                j += 1
                            if not (j < i - 1):
                                break
                            # TODO Check zero based indexing
                            Ymatrix.setElemSym(i, j, Yij)
                        break
                    break
            # **** Removed neutral / neutral may float
            # 
            # 			if (Connection == 0) {  // take care of neutral issues
            # 				Ymatrix.addElement(nConds, nConds, YNeut);  // add in user specified neutral Z, if any
            # 				// bump up neutral-ground in case neutral ends up floating
            # 				Ymatrix.setElement(nConds, nConds, Ymatrix.getElement(nConds, nConds).multiply(1.000001));
            # 			}

        else:
            # Regular power flow generator model
            # Yeq is always expected as the equivalent line-neutral admittance
            Y = self.Yeq.negate()
            # negate for generation; Yeq is L-N quantity
            # ****** Need to modify the base admittance for real harmonics calcs
            Y = Complex(Y.getReal(), Y.getImaginary() / freqMultiplier)
            _4 = self.connection
            _5 = False
            while True:
                if _4 == 0:
                    _5 = True
                    Yij = Y.negate()
                    _6 = True
                    i = 0
                    while True:
                        if _6 is True:
                            _6 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        Ymatrix.setElement(i, i, Y)
                        Ymatrix.addElement(self.nConds, self.nConds, Y)
                        Ymatrix.setElemSym(i, self.nConds, Yij)
                    break
                if (_5 is True) or (_4 == 1):
                    _5 = True
                    Y = ComplexUtil.divide(Y, 3.0)
                    # convert to delta impedance
                    Yij = Y.negate()
                    _7 = True
                    i = 0
                    while True:
                        if _7 is True:
                            _7 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        j = i + 1
                        if j > self.nConds:
                            j = 0
                            # wrap around for closed connections
                        Ymatrix.addElement(i, i, Y)
                        Ymatrix.addElement(j, j, Y)
                        Ymatrix.addElemSym(i, j, Yij)
                    break
                break

    def calcYPrim(self):
        # build only shunt Yprim
        # build a dummy Yprim series so that calcV does not fail
        if self.isYprimInvalid():
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
            self.YPrimShunt.clear()
            self.YPrimSeries.clear()
            self.YPrim.clear()
        if (
            DSSGlobals.activeCircuit.getSolution().getLoadModel() == DSSGlobals.POWERFLOW
        ):
            # 12-7-99 we'll start with Yeq in system matrix
            self.setNominalGeneration()
            self.calcYPrimMatrix(self.YPrimShunt)
        else:
            # admittance model wanted
            self.setNominalGeneration()
            self.calcYPrimMatrix(self.YPrimShunt)
        # set YPrim_Series based on diagonals of YPrim_shunt so that calcVoltages doesn't fail
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
        super(GeneratorObjImpl, self).calcYPrim()

    def stickCurrInTerminalArray(self, termArray, curr, i):
        """Add the current into the proper location according to connection.

        Reverse of similar routine in load (complex negates are switched).
        """
        _0 = self.connection
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                termArray[i] = termArray[i].add(curr)
                termArray[self.nConds] = termArray[self.nConds].add(curr.negate())
                # neutral
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                termArray[i] = termArray[i].add(curr)
                j = i + 1
                if j > self.nConds:
                    j = 0
                termArray[j] = termArray[j].add(curr.negate())
                break
            break

    def writeTraceRecord(self, s):
        # FIXME handle exception
        try:
            if not DSSGlobals.inShowResults:
                fw = self.FileWriter(self.traceFile, True)
                bw = self.BufferedWriter(fw)
                bw.write(String.format.format('%-.g, %d, %-.g, ', DSSGlobals.activeCircuit.getSolution().getDynaVars().t, DSSGlobals.activeCircuit.getSolution().getIteration(), DSSGlobals.activeCircuit.getLoadMultiplier()) + Utilities.getSolutionModeID() + ', ' + Utilities.getLoadModel() + ', ' + self.genModel + ', ' + self.dQdV + ', ' + ((self.VAvg * 0.001732) / self.genVars.kVGeneratorBase) + ', ' + (self.VTarget - self.VAvg) + ', ' + ((self.genVars.QNominalPerPhase * 3.0) / 1000000.0) + ', ' + ((self.genVars.PNominalPerPhase * 3.0) / 1000000.0) + ', ' + s + ', ')
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    bw.write(self.getInjCurrent()[i].abs() + ', ')
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    bw.write(self.getITerminal()[i].abs() + ', ')
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nPhases):
                        break
                    bw.write(self.getVTerminal()[i].abs() + ', ')
                bw.write(self.VThevMag + ', ' + ((self.genVars.theta * 180.0) / self.Math.PI))
                bw.newLine()
                bw.close()
                fw.close()
        except Exception, e:
            pass # astStmt: [Stmt([]), None]

    def doConstantPQGen(self):
        """Compute total terminal current for Constant PQ."""
        curr = None
        # Complex[] V012, I012 = new Complex[2];
        # Complex[] Iabc = new Complex[3];
        # treat this just like the Load moVdel
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.zeroITerminal()
        # Tried this but couldn't get it to work
        # 		switch (nPhases) {
        # 		case 3:  // Use Symmetrical Components
        # 			phase2SymComp(Vterminal, V012);   // vTerminal is L-N voltages here
        # 			// Phase2SymComp(InjCurrent, @I012);   // vTerminal is L-G voltages here
        # 			V = V012[0];  // positive sequence L-N voltage
        # 			Vmag = V012[0].abs();
        # 
        # 			if (VMag <= VBase95) {
        # 				Curr = Yeq95.multiply(V).negate();  // below 95% (Vminpu) use an impedance model
        # 			} else {
        # 				if (Vmag > VBase105)
        # 					Curr = Yeq105.multiply(V).negate();  // above 105% (Vmaxpu) use an impedance model
        # 			}
        # 			if ((Vmag <= VBase95) || (Vmag > VBase105)) {
        # 				Curr = CurrentLimit.divide(V.divide(-Vmag)).conjugate();
        # 			} else {
        # 				Curr = new Complex(-GenVars.Pnominalperphase, -GenVars.Qnominalperphase).divide(V).conjugate();  // current into pos seq model
        # 			}
        # 
        # 			I012[0] = Curr;  // pos sequence current into the terminal
        # 
        # 			if (Connection == 1) {
        # 				I012[0] = Complex.ZERO;
        # 			} else {
        # 				I012[0] = V012[0].divide(new Complex(0.0, xdpp));
        # 			}
        # 			I012[1] = V012[1].divide(new Complex(0.0, xdpp));
        # 
        # 			// negative and zero sequence contributions
        # 			symComp2Phase(Iabc, I012);    // Iabc now desired terminal current
        # 			if (DebugTrace) {
        # 				FileWriter TraceStream = new FileWriter(TraceFile, true);
        # 				BufferedWriter TraceBuffer = new BufferedWriter(TraceStream);
        # 				TraceBuffer.write(String.format("V1=%-.5g, /_%-.5g, ", V.abs(), V.degArg()));
        # 				TraceBuffer.write(String.format("I1=%-.5g, /_%-.5g, ", Curr.abs(), Curr.degArg()));
        # 				TraceBuffer.write("Iabc=");
        # 				for (int i = 0; i < 3; i++)
        # 					TraceBuffer.write(String.format("%-.5g, /_%-.5g, ", Iabc[i].abs(), Iabc[i].degArg()));
        # 				TraceBuffer.newLine();
        # 				TraceBuffer.close();
        # 			}
        # 
        # 			for (i = 0; i < 3; i++) {
        # 				ITerminal[i] = Iabc[i];  // put into terminal array directly because we have computed line current above
        # 				Caccum(getInjCurrent()[i], Iabc[i].negate()));  // subtract in
        # 				if (Connection == 0) {
        # 					Caccum(getIterminal()[nConds], Iabc[i].negate());  // neutral
        # 					Caccum(getInjCurrent()[nConds], Iabc[i]);  // neutral
        # 				}
        # 			}
        # 			IterminalUpdated = true;  // so that we con't have to recompute for a report
        # 			break;
        # 		}

        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
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
            _1 = self.connection
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    if VMag <= self.VBase95:
                        curr = self.Yeq95.multiply(V)
                        # below 95% use an impedance model
                    elif VMag > self.VBase105:
                        curr = self.Yeq105.multiply(V)
                        # above 105% use an impedance model
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, self.genVars.QNominalPerPhase).divide(V).conjugate()
                        # between 95% -105%, constant PQ
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    VMag = VMag / DSSGlobals.SQRT3
                    # L-N magnitude
                    if VMag <= self.VBase95:
                        curr = ComplexUtil.divide(self.Yeq95, 3.0).multiply(V)
                        # below 95% use an impedance model
                    elif VMag > self.VBase105:
                        curr = ComplexUtil.divide(self.Yeq105, 3.0).multiply(V)
                        # above 105% use an impedance model
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, self.genVars.QNominalPerPhase).divide(V).conjugate()
                        # between 95% -105%, constant PQ
                    break
                break
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doConstantZGen(self):
        # assume Yeq is kept up to date
        self.calcYPrimContribution(self.getInjCurrent())
        # init InjCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        self.zeroITerminal()
        if self.connection == 0:
            Yeq2 = self.Yeq
        else:
            Yeq2 = ComplexUtil.divide(self.Yeq, 3.0)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            curr = Yeq2.multiply(self.VTerminal[i])
            # Yeq is always line to neutral
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doPVTypeGen(self):
        """Compute total terminal current for Constant P, |V|."""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the generator
        self.zeroITerminal()
        # guess at a new var output value
        self.VAvg = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.VAvg = self.VAvg + self.VTerminal[i].abs()
        if self.connection == 1:
            self.VAvg = self.VAvg / (DSSGlobals.SQRT3 * self.nPhases)
        else:
            self.VAvg = self.VAvg / self.nPhases
        # 12-9-99 added empirical 0.7 factor to improve iteration
        # 12-17-99 changed to 0.1 because first guess was consistently too high
        dQ = self.PVFactor * self.dQdV * (self.VTarget - self.VAvg)
        # vTarget is L-N
        if self.Math.abs(dQ) > self.deltaQMax:
            if dQ < 0.0:
                dQ = -self.deltaQMax
            else:
                dQ = self.deltaQMax
        self.genVars.QNominalPerPhase = self.genVars.QNominalPerPhase + dQ
        # Test limits
        if self.genVars.QNominalPerPhase > self.varMax:
            self.genVars.QNominalPerPhase = self.varMax
        elif self.genVars.QNominalPerPhase < self.varMin:
            self.genVars.QNominalPerPhase = self.varMin
        # compute injection currents using W and var values
        # do not use constant Z models outside normal range
        # presumably the var source will take care of the voltage problems
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            curr = Complex(self.genVars.PNominalPerPhase, self.genVars.QNominalPerPhase).divide(self.VTerminal[i]).conjugate()
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doFixedQGen(self):
        """Compute total terminal current for Fixed Q.
        Constant P, Fixed Q - Q is always kvarBase.
        """
        curr = None
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
            _1 = self.connection
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    if VMag <= self.VBase95:
                        curr = Complex(self.Yeq95.getReal(), self.YQFixed).multiply(V)
                        # below 95% use an impedance model
                    elif VMag > self.VBase105:
                        curr = Complex(self.Yeq105.getReal(), self.YQFixed).multiply(V)
                        # above 105% use an impedance model
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, self.varBase).divide(V).conjugate()
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    VMag = VMag / DSSGlobals.SQRT3
                    # convert to L-N for test
                    if VMag <= self.VBase95:
                        curr = Complex(self.Yeq95.getReal() / 3.0, self.YQFixed / 3.0).multiply(V)
                        # below 95% use an impedance model
                    elif VMag > self.VBase105:
                        curr = Complex(self.Yeq105.getReal() / 3.0, self.YQFixed / 3.0).multiply(V)
                        # above 105% use an impedance model
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, self.varBase).divide(V).conjugate()
                    break
                break
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doFixedQZGen(self):
        """Compute total terminal current for constant P, fixed Q.
        Q is always a fixed Z derived from kvarBase
        """
        curr = None
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
            _1 = self.connection
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    if VMag <= self.VBase95:
                        curr = Complex(self.Yeq95.getReal(), self.YQFixed).multiply(V)
                        # below 95% use an impedance model
                    elif VMag > self.VBase105:
                        curr = Complex(self.Yeq105.getReal(), self.YQFixed).multiply(V)
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, 0.0).divide(V).conjugate()
                        # P component of current
                        curr = curr.add(Complex(0.0, self.YQFixed).multiply(V))
                        # add in Q component of current
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    VMag = VMag / DSSGlobals.SQRT3
                    # convert to L-N for test
                    if VMag <= self.VBase95:
                        curr = Complex(self.Yeq95.getReal() / 3.0, self.YQFixed / 3.0).multiply(V)
                        # below 95% use an impedance model
                    elif VMag > self.VBase105:
                        curr = Complex(self.Yeq105.getReal() / 3.0, self.YQFixed / 3.0).multiply(V)
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, 0.0).divide(V).conjugate()
                        # P component of current
                        curr = curr.add(Complex(0.0, self.YQFixed / 3.0).multiply(V))
                        # add in Q component of current
                    break
                break
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doUserModel(self):
        """Compute total terminal current from User-written model."""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        if self.userModel.exists():
            # check automatically selects the usermodel if true
            # appendToEventLog("Wnominal=", String.format("%-.5g", Pnominalperphase));
            self.userModel.calc(self.VTerminal, self.ITerminal)
            self.setITerminalUpdated(True)
            # SolutionObj sol = DSSGlobals.activeCircuit.getSolution();
            # negate currents from user model for power flow generator model
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nConds):
                    break
                self.getInjCurrent()[i] = self.getInjCurrent()[i].add(self.getITerminal()[i].negate())
        else:
            DSSGlobals.doSimpleMsg('Generator.' + self.getName() + ' model designated to use user-written model, but user-written model is not defined.', 567)

    def doCurrentLimitedPQ(self):
        """Compute total terminal current for constant PQ, but limit to max current below VminPU."""
        curr = None
        # treat this just like the load model
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
            _1 = self.connection
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    if (VMag <= self.VBase95) or (VMag > self.VBase105):
                        # limit the current magnitude when voltage drops outside normal range
                        curr = self.currentLimit.divide(ComplexUtil.divide(V, VMag)).conjugate()
                        # current limit expression
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, self.genVars.QNominalPerPhase).divide(V).conjugate()
                        # above vMinPU, constant PQ
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    VMagLN = VMag / DSSGlobals.SQRT3
                    if (VMagLN <= self.VBase95) or (VMagLN > self.VBase105):
                        # limit the current magnitude when voltage drops outside normal range
                        curr = self.currentLimit.divide(ComplexUtil.divide(V, VMag)).conjugate()
                        # Current limit expression
                    else:
                        curr = Complex(self.genVars.PNominalPerPhase, self.genVars.QNominalPerPhase).divide(V).conjugate()
                        # above vMinPU, constant PQ
                    break
                break
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doDynamicMode(self):
        """Compute total current and add into injTemp."""
        V012 = [None] * 2
        I012 = [None] * 2
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        # Inj = -Itotal (in) - Yprim * Vtemp
        if self.genModel == 6 and self.userModel.exists():
            # auto selects model
            # We have total currents in Itemp
            self.userModel.calc(self.VTerminal, self.ITerminal)
            # returns terminal currents in iTerminal
        else:
            # No user model, use default Thevinen equivalent
            _0 = self.nPhases
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.calcVThevDyn()
                    # update for latest phase angle
                    self.getITerminal()[0] = self.getVTerminal()[0].subtract(self.VThev).subtract(self.getVTerminal()[1]).divide(Complex(0.0, self.genVars.Xdp))
                    self.getITerminal()[1] = self.getITerminal()[0].negate()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    MathUtil.phase2SymComp(self.VTerminal, V012)
                    # positive sequence contribution to iTerminal
                    self.calcVThevDyn()
                    # update for latest phase angle
                    # positive sequence contribution to iTerminal
                    I012[0] = V012[0].subtract(self.VThev).divide(Complex(0.0, self.genVars.Xdp))
                    I012[1] = V012[1].divide(Complex(0.0, self.genVars.Xdpp))
                    if self.connection == 1:
                        I012[0] = Complex.ZERO
                        # TODO Check zero based indexing
                    else:
                        I012[0] = V012[0].divide(Complex(0.0, self.genVars.Xdpp))
                    MathUtil.symComp2Phase(self.getITerminal(), I012)
                    # convert back to phase components
                    # Neutral current
                    if self.connection == 0:
                        self.getITerminal()[self.nConds] = I012[0].multiply(3.0).negate()
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg(String.format.format('Dynamics mode is implemented only for 1- or 3-phase generators. Generator.' + self.getName() + ' has %d phases.', self.nPhases), 5671)
                    DSSGlobals.solutionAbort = True
                    break
                break
        self.setITerminalUpdated(True)
        # Add it into inj current array
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            self.getInjCurrent()[i] = self.getInjCurrent()[i].add(self.getITerminal()[i].negate())
            # Take Care of any shaft model calcs
        if self.genModel == 6 and self.shaftModel.exists():
            # auto selects model
            # compute mech power to shaft
            self.shaftModel.calc(self.getVTerminal(), self.getITerminal())
            # returns pshaft at least

    def doHarmonicMode(self):
        """Compute injection current only when in harmonics mode.

        Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built.
        Vd is the fundamental frequency voltage behind Xd" for phase 1.
        """
        self.computeVTerminal()
        sol = DSSGlobals.activeCircuit.getSolution()
        genHarmonic = sol.getFrequency() / self.genFundamental
        e = self.getSpectrumObj().getMult(genHarmonic).multiply(self.VThevHarm)
        # get base harmonic magnitude
        e = Utilities.rotatePhasorRad(e, genHarmonic, self.thetaHarm)
        # time shift by fundamental frequency phase shift
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.cBuffer[i] = e
            if i < self.nPhases:
                e = Utilities.rotatePhasorDeg(e, genHarmonic, -120.0)
                # assume 3-phase generator
        # Handle wye connection
        if self.connection == 0:
            self.cBuffer[self.nConds] = self.getVTerminal()[self.nConds]
            # assume no neutral injection voltage
            # Inj currents = Yprim (E)
        self.YPrim.vMult(self.getInjCurrent(), self.cBuffer)

    def calcVTerminalPhase(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        # Establish phase voltages and stick in Vterminal
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
                    self.getVTerminal()[i] = sol.vDiff(self.nodeRef[i], self.nodeRef[self.nConds])
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
                    self.getVTerminal()[i] = sol.vDiff(self.nodeRef[i], self.nodeRef[j])
                break
            break
        self.generatorSolutionCount = sol.getSolutionCount()

    def calcVterminal(self):
        """Put terminal voltages in an array."""
        self.computeVTerminal()
        self.generatorSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount()

    def calcGenModelContribution(self):
        """Calculates generator current and adds it properly into the injcurrent array
        routines may also compute ITerminal (ITerminalUpdated flag).
        """
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        self.setITerminalUpdated(False)
        if sol.isDynamicModel():
            self.doDynamicMode()
        elif sol.isHarmonicModel() and sol.getFrequency() != ckt.getFundamental():
            self.doHarmonicMode()
        else:
            # compute currents and put into injTemp array
            _0 = self.genModel
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.doConstantPQGen()
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    self.doConstantZGen()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    self.doPVTypeGen()
                    # constant P, |V|
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    self.doFixedQGen()
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    self.doFixedQZGen()
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.doUserModel()
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    self.doCurrentLimitedPQ()
                    break
                if True:
                    _1 = True
                    self.doConstantPQGen()
                    # for now, until we implement the other models.
                    break
                break
        # When this is done, ITerminal is up to date

    def calcInjCurrentArray(self):
        """Difference between currents in YPrim and total current."""
        # now get injection currents.
        if self.genSwitchOpen:
            self.zeroInjCurrent()
        else:
            self.calcGenModelContribution()
        # We're not going to mess with this logic here -- too complicated: Use an open line in series
        # 		to look at open phase conditions.
        # 
        # 		} else {
        # 			SolutionObj sol = DSSGlobals.activeCircuit.getSolution();
        # 
        # 			// some terminals not closed  use admittance model for injection
        # 			if (OpenGeneratorSolutionCount != sol.getSolutionCount()) {
        # 
        # 				// rebuild the YPrimOpenCond if a new solution because values may have changed
        # 
        # 				// only reallocate when necessary
        # 				if (YPrimOpenCond == null) {
        # 					YPrimOpenCond = new CMatrixImpl(Yorder);
        # 				} else {
        # 					YPrimOpenCond.clear();
        # 				}
        # 				if (YPrimOpenCond.getNOrder() != Yorder) {
        # 					YPrimOpenCond = null;
        # 					YPrimOpenCond = new CMatrixImpl(Yorder);
        # 				}
        # 				calcYPrimMatrix(YPrimOpenCond);
        # 
        # 				// now account for the open conductors //
        # 				// for any conductor that is open, zero out row and column
        # 				int k = 0;
        # 				for (int i = 0; i < nTerms; i++) {
        # 					for (int j = 0; j < nConds; j++) {
        # 						if (!Terminals[i].getConductors()[j].isClosed()) {
        # 							YPrimOpenCond.zeroRow(j + k);
        # 							YPrimOpenCond.zeroCol(j + k);
        # 							YPrimOpenCond.setElement(j + k, j + k, new Complex(1.0e-12, 0.0));  // in case node gets isolated
        # 						}
        # 					}
        # 					k = k + nConds;
        # 				}
        # 
        # 				OpenGeneratorSolutionCount = sol.getSolutionCount();
        # 
        # 			}
        # 
        # 			for (int i = 0; i < Yorder; i++) {
        # 				Ref = NodeRef[i];
        # 				if (Ref == -1) {
        # 					Vterminal[i] = Complex.ZERO;
        # 				} else {
        # 					Vterminal[i] = V[Ref];
        # 				}
        # 			}
        # 			YPrimOpenCond.MVmult(InjTemp, Vterminal);
        # 			for (int i = 0; i < Yorder; i++)
        # 				InjTemp[i] = InjTemp[i].neagte();
        # 		}

    def getTerminalCurrents(self, curr):
        """Compute total currents."""
        sol = DSSGlobals.activeCircuit.getSolution()
        if self.ITerminalSolutionCount != sol.getSolutionCount():
            # recalc the contribution
            if not self.genSwitchOpen:
                self.calcGenModelContribution()
                # adds totals in iTerminal as a side effect
        super(GeneratorObjImpl, self).getTerminalCurrents(curr)
        if self.debugTrace:
            self.writeTraceRecord('TotalCurrent')

    def injCurrents(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        if sol.loadsNeedUpdating():
            self.setNominalGeneration()
            # set the nominal kW, etc for the type of solution being done
        self.calcInjCurrentArray()
        # difference between currents in YPrim and total terminal current
        if self.debugTrace:
            self.writeTraceRecord('Injection')
            # add into system injection current array
        return super(GeneratorObjImpl, self).injCurrents()

    def getInjCurrents(self, curr):
        """Gives the currents for the last solution performed.

        Do not call setNominalLoad, as that may change the load values.
        """
        self.calcInjCurrentArray()
        # difference between currents in YPrim and total current
        # copy into buffer array
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
                curr[i] = self.getInjCurrent()[i]
        except Exception, e:
            DSSGlobals.doErrorMsg('Generator Object: \"' + self.getName() + '\" in getInjCurrents method.', e.getMessage(), 'Current buffer not big enough.', 568)

    def resetRegisters(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NumGenRegisters):
                break
            self.registers[i] = 0.0
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.NumGenRegisters):
                break
            self.derivatives[i] = 0.0
        self.firstSampleAfterReset = True
        # initialize for trapezoidal integration

    def integrate(self, reg, deriv, interval):
        if DSSGlobals.activeCircuit.isTrapezoidalIntegration():
            # Trapezoidal Rule Integration
            if not self.firstSampleAfterReset:
                self.registers[reg] = self.registers[reg] + (0.5 * interval * (deriv + self.derivatives[reg]))
        else:
            # Plain Euler integration
            self.registers[reg] = self.registers[reg] + (interval * deriv)
        self.derivatives[reg] = deriv

    def takeSample(self):
        """Update energy from metered zone."""
        # compute energy in generator branch
        if self.isEnabled():
            if self.genOn:
                S = Complex(self.getPresentKW(), self.getPresentKVAr())
                SMag = S.abs()
                hourValue = 1.0
            else:
                S = Complex.ZERO
                SMag = 0.0
                hourValue = 0.0
            ckt = DSSGlobals.activeCircuit
            if self.genOn or ckt.isTrapezoidalIntegration():
                # Make sure we always integrate for Trapezoidal case.
                # Don't need to for gen off and normal integration.

                if ckt.isPositiveSequence():
                    S = S.multiply(3.0)
                    SMag = 3.0 * SMag
                self.integrate(self.regKWh, S.getReal(), ckt.getSolution().getIntervalHrs())
                # accumulate the power
                self.integrate(self.regKVArh, S.getImaginary(), ckt.getSolution().getIntervalHrs())
                self.setDragHandRegister(self.regMaxKW, self.Math.abs(S.getReal()))
                self.setDragHandRegister(self.regMaxKVA, SMag)
                self.integrate(self.regHours, hourValue, ckt.getSolution().getIntervalHrs())
                # accumulate hours in operation
                self.integrate(self.regPrice, S.getReal() * ckt.getPriceSignal(), ckt.getSolution().getIntervalHrs())
                # accumulate hours in operation
                self.firstSampleAfterReset = False

    def getPresentKW(self):
        return self.genVars.PNominalPerPhase * 0.001 * self.nPhases

    def getPresentKV(self):
        return self.genVars.kVGeneratorBase

    def getPresentKVAr(self):
        return self.genVars.QNominalPerPhase * 0.001 * self.nPhases

    def initDQDVCalc(self):
        """Procedures for setting the dQdV used by the solution object."""
        self.dQdV = 0.0
        self.genVars.QNominalPerPhase = 0.5 * (self.varMax + self.varMin)
        # avg of the limits

    def bumpUpQ(self):
        """Bump up vars by 10% of range for next calc."""
        self.genVars.QNominalPerPhase = self.genVars.QNominalPerPhase + (0.1 * (self.varMax - self.varMin))

    def rememberQV(self):
        self.varRemembered = self.genVars.QNominalPerPhase
        self.calcVterminal()
        self.VAvg = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.VAvg = self.VAvg + self.VTerminal[i].abs()
        self.VAvg = self.VAvg / self.nPhases
        self.VRemembered = self.VAvg

    def calcDQDV(self):
        self.calcVterminal()
        self.VAvg = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nPhases):
                break
            self.VAvg = self.VAvg + self.VTerminal[i].abs()
        self.VAvg = self.VAvg / self.nPhases
        VDiff = self.VAvg - self.VRemembered
        if VDiff != 0.0:
            self.dQdV = (self.genVars.QNominalPerPhase - self.varRemembered) / VDiff
        else:
            self.dQdV = 0.0
            # something strange has occured
        # this will force a de facto P, Q model
        self.dQdVSaved = self.dQdV
        # save for next time; allows generator to be enabled/disabled during simulation

    def resetStartPoint(self):
        self.genVars.QNominalPerPhase = (1000.0 * self.kVArBase) / self.nPhases

    def dumpProperties(self, F, Complete):
        # Support for harmonics mode.
        super(GeneratorObjImpl, self).dumpProperties(F, Complete)
        print '!dQdV=' + self.dQdV
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getParentClass().getNumProperties()):
                break
            idx = self.getParentClass().getPropertyIdxMap()[i]
            _1 = idx
            _2 = False
            while True:
                if _1 == 33:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=(' + self.propertyValue[idx] + ')'
                    break
                if (_2 is True) or (_1 == 35):
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=(' + self.propertyValue[idx] + ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.propertyValue[idx]
                    break
                break
        print 

    def initHarmonics(self):
        Va = None
        sol = DSSGlobals.activeCircuit.getSolution()
        self.setYPrimInvalid(True)
        # force rebuild of YPrims
        self.genFundamental = sol.getFrequency()
        # whatever the frequency is when we enter here.
        self.Yeq = ComplexUtil.invert(Complex(0.0, self.genVars.Xdpp))
        # used for current calcs; always L-N
        # Compute reference Thevinen voltage from phase 1 current
        if self.genOn:
            self.computeITerminal()
            # get present value of current
            _0 = self.connection
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    Va = sol.getNodeV()[self.nodeRef[0]].subtract(sol.getNodeV()[self.nodeRef[self.nConds]])
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    Va = sol.getNodeV()[self.nodeRef[0]]
                    break
                break
            e = Va.subtract(self.ITerminal[0].multiply(Complex(0.0, self.genVars.Xdpp)))
            self.VThevHarm = e.abs()
            # establish base mag and angle
            self.thetaHarm = e.getArgument()
        else:
            self.VThevHarm = 0.0
            self.thetaHarm = 0.0

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '3'
        # phases;
        self.propertyValue[1] = self.getBus(0)
        # bus1
        self.propertyValue[2] = '12.47'
        self.propertyValue[3] = '100'
        self.propertyValue[4] = '.80'
        self.propertyValue[5] = '1'
        self.propertyValue[6] = ''
        self.propertyValue[7] = ''
        self.propertyValue[8] = ''
        self.propertyValue[9] = 'Default'
        self.propertyValue[10] = '0.0'
        self.propertyValue[11] = 'wye'
        self.propertyValue[12] = '60'
        self.propertyValue[13] = '0'
        # "rneut"; // if entered -, assume open
        self.propertyValue[14] = '0'
        # "xneut";
        self.propertyValue[15] = 'variable'
        # "status"  fixed or variable
        self.propertyValue[16] = '1'
        # "class"
        self.propertyValue[17] = '1.0'
        self.propertyValue[18] = Utilities.strReal(self.kVArMax, 3)
        self.propertyValue[19] = Utilities.strReal(self.kVArMin, 3)
        self.propertyValue[20] = '0.1'
        self.propertyValue[21] = 'no'
        self.propertyValue[22] = '0.90'
        self.propertyValue[23] = '1.10'
        self.propertyValue[24] = 'No'
        self.propertyValue[25] = String.format.format('%-g', self.genVars.kVARating)
        self.propertyValue[26] = String.format.format('%-g', self.genVars.kVARating * 0.001)
        self.propertyValue[27] = String.format.format('%-g', self.genVars.puXd)
        self.propertyValue[28] = String.format.format('%-g', self.genVars.puXdp)
        self.propertyValue[29] = String.format.format('%-g', self.genVars.puXdpp)
        self.propertyValue[30] = String.format.format('%-g', self.genVars.HMass)
        self.propertyValue[31] = String.format.format('%-g', self.genVars.Dpu)
        self.propertyValue[32] = ''
        self.propertyValue[33] = ''
        self.propertyValue[34] = ''
        self.propertyValue[35] = ''
        super(GeneratorObjImpl, self).initPropertyValues(Generator.NumPropsThisClass)

    def initStateVars(self):
        """Support for dynamics mode."""
        # Complex VNeut;
        Edp = None
        V012 = [None] * 2
        # TODO Check zero based indexing
        I012 = [None] * 2
        Vabc = [None] * 3
        self.setYPrimInvalid(True)
        # force rebuild of YPrims
        self.Yeq = ComplexUtil.invert(Complex(0.0, self.genVars.Xdp))
        # Compute nominal positive sequence voltage behind transient reactance
        if self.genOn:
            sol = DSSGlobals.activeCircuit.getSolution()
            self.computeITerminal()
            _0 = self.nPhases
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    Edp = sol.getNodeV()[self.nodeRef[0]].subtract(sol.getNodeV()[self.nodeRef[1]]).subtract(self.getITerminal()[0].multiply(Complex(0.0, self.genVars.Xdp)))
                    self.VThevMag = Edp.abs()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    MathUtil.phase2SymComp(self.getITerminal(), I012)
                    # voltage behind Xdp  (transient reactance), volts
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        Vabc[i] = sol.getNodeV()[self.nodeRef[i]]
                        # wye voltage
                    MathUtil.phase2SymComp(Vabc, V012)
                    Edp = V012[0].subtract(I012[0].multiply(Complex(0.0, self.genVars.Xdp)))
                    # pos sequence
                    self.VThevMag = Edp.abs()
                    break
                if True:
                    _1 = True
                    DSSGlobals.doSimpleMsg(String.format.format('Dynamics mode is implemented only for 1- or 3-phase generators. Generator.' + self.getName() + ' has %d phases.', self.nPhases), 5672)
                    DSSGlobals.solutionAbort = True
                    break
                break
            # shaft variables
            # theta is angle on Vthev[1] relative to system reference
            # Theta  = Vthev[0].getArgument();  // assume source at 0
            self.genVars.theta = Edp.getArgument()
            self.genVars.dTheta = 0.0
            self.genVars.w0 = DSSGlobals.TWO_PI * sol.getFrequency()
            # recalc mMass and D in case the frequency has changed
            self.genVars.MMass = (2.0 * self.genVars.HMass * self.genVars.kVARating * 1000.0) / self.genVars.w0
            # M = W-sec
            self.genVars.D = (self.genVars.Dpu * self.genVars.kVARating * 1000.0) / self.genVars.w0
            self.genVars.PShaft = -self.getPower(0).getReal()
            # initialize pShaft to present power output
            self.genVars.speed = 0.0
            # relative to synch speed
            self.genVars.dSpeed = 0.0
            # init user-written models
            # int nCond; Complex[] V, I; double X, Pshaft, Theta, Speed, dt, time;
            if self.genModel == 6:
                if self.userModel.exists():
                    self.userModel.init(self.VTerminal, self.ITerminal)
                if self.shaftModel.exists():
                    self.shaftModel.init(self.VTerminal, self.ITerminal)
        else:
            self.VThev = Complex.ZERO
            self.genVars.theta = 0.0
            self.genVars.dTheta = 0.0
            self.genVars.w0 = 0
            self.genVars.speed = 0.0
            self.genVars.dSpeed = 0.0

    def integrateStates(self):
        # compute derivatives and then integrate
        self.computeITerminal()
        # check for user-written exciter model
        # function(Complex[] V, Complex[] I, double Pshaft, double Theta, double Speed, double dt, double time)
        sol = DSSGlobals.activeCircuit.getSolution()
        if sol.getDynaVars().iterationFlag == 0:
            # first iteration of new time step
            self.genVars.thetaHistory = self.genVars.theta + (0.5 * sol.getDynaVars().h * self.genVars.dTheta)
            self.genVars.speedHistory = self.genVars.speed + (0.5 * sol.getDynaVars().h * self.genVars.dSpeed)
        # compute shaft dynamics
        tracePower = MathUtil.terminalPowerIn(self.VTerminal, self.ITerminal, self.nPhases)
        self.genVars.dSpeed = ((self.genVars.PShaft + tracePower.getReal()) - (self.genVars.D * self.genVars.speed)) / self.genVars.MMass
        # GenVars.dSpeed = (GenVars.Torque + terminalPowerIn(Vtemp, Itemp, nPhases).getReal() / GenVars.Speed) / (GenVars.Mmass);
        self.genVars.dTheta = self.genVars.speed
        # trapezoidal method
        self.genVars.speed = self.genVars.speedHistory + (0.5 * sol.getDynaVars().h * self.genVars.dSpeed)
        self.genVars.theta = self.genVars.thetaHistory + (0.5 * sol.getDynaVars().h * self.genVars.dTheta)
        # write dynamics trace record
        if self.debugTrace:
            # TODO: handle exception
            try:
                TraceStream = self.FileWriter(self.traceFile, True)
                TraceBuffer = self.BufferedWriter(TraceStream)
                TraceBuffer.write(String.format.format('t=%-.5g ', sol.getDynaVars().t))
                TraceBuffer.write(String.format.format(' Flag=%d ', sol.getDynaVars().iterationFlag))
                TraceBuffer.write(String.format.format(' Speed=%-.5g ', self.genVars.speed))
                TraceBuffer.write(String.format.format(' dSpeed=%-.5g ', self.genVars.dSpeed))
                TraceBuffer.write(String.format.format(' Pshaft=%-.5g ', self.genVars.PShaft))
                TraceBuffer.write(String.format.format(' P=%-.5g Q= %-.5g', tracePower.getReal(), tracePower.getImaginary()))
                TraceBuffer.write(String.format.format(' M=%-.5g ', self.genVars.MMass))
                TraceBuffer.newLine()
                TraceBuffer.close()
                TraceStream.close()
            except IOException, e:
                pass # astStmt: [Stmt([]), None]
        if self.genModel == 6:
            if self.userModel.exists():
                self.userModel.integrate()
            if self.shaftModel.exists():
                self.shaftModel.integrate()

    def getVariable(self, i):
        """Return variables one at a time."""
        n = 0
        result = -9999.99
        # error return value
        if i < 0:
            return result
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                result = (self.genVars.w0 + self.genVars.speed) / DSSGlobals.TWO_PI
                # frequency, Hz
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                result = self.genVars.theta * DSSGlobals.RADIANS_TO_DEGREES
                # report in deg
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                result = self.VThev.abs() / self.VBase
                # report in pu
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = self.genVars.PShaft
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                result = self.genVars.dSpeed * DSSGlobals.RADIANS_TO_DEGREES
                # report in deg
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                result = self.genVars.dTheta
                break
            if True:
                _1 = True
                if self.userModel.exists():
                    n = self.userModel.numVars()
                    k = i - self.NumGenVariables
                    if k <= n:
                        return self.userModel.getVariable(k)
                # If we get here, must be in the shaft model if anywhere
                if self.shaftModel.exists():
                    k = i - (self.NumGenVariables + n)
                    if k > 0:
                        return self.shaftModel.getVariable(k)
                break
            break
        return result

    def setVariable(self, i, value):
        n = 0
        if i < 0:
            return
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.genVars.speed = (value - self.genVars.w0) * DSSGlobals.TWO_PI
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                self.genVars.theta = value / DSSGlobals.RADIANS_TO_DEGREES
                # deg to rad
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.genVars.PShaft = value
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                self.genVars.dSpeed = value / DSSGlobals.RADIANS_TO_DEGREES
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                self.genVars.dTheta = value
                break
            if True:
                _1 = True
                if self.userModel.exists():
                    n = self.userModel.numVars()
                    k = i - self.NumGenVariables
                    if k <= n:
                        self.userModel.setVariable(k, value)
                        return
                # if we get here, must be in the shaft model
                if self.shaftModel.exists():
                    k = i - (self.NumGenVariables + n)
                    if k > 0:
                        self.shaftModel.setVariable(k, value)
                break
            break

    def getAllVariables(self, states):
        n = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.NumGenVariables):
                break
            states[i] = self.getVariable(i)
        if self.userModel.exists():
            n = self.userModel.numVars()
            self.userModel.getAllVars(states[self.NumGenVariables + 1])
        if self.shaftModel.exists():
            self.shaftModel.getAllVars(states[self.NumGenVariables + 1 + n])

    def numVariables(self):
        result = self.NumGenVariables
        if self.userModel.exists():
            result = result + self.userModel.numVars()
        if self.shaftModel.exists():
            result = result + self.shaftModel.numVars()
        return result

    def variableName(self, i):
        buffSize = 255
        # char[] Buff = new char[BuffSize];
        result = ''
        n = 0
        if i < 0:
            return result
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                result = 'Frequency'
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                result = 'Theta (Deg)'
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                result = 'Vd'
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = 'PShaft'
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                result = 'dSpeed (Deg/sec)'
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                result = 'dTheta (Deg)'
                break
            if True:
                _1 = True
                if self.userModel.exists():
                    pName = 0
                    n = self.userModel.numVars()
                    i2 = i - self.NumGenVariables
                    if i2 <= n:
                        self.userModel.getVarName(i2, pName, buffSize)
                        return String.valueOf.valueOf(pName)
                if self.shaftModel.exists():
                    pName = 0
                    i2 = i - self.NumGenVariables - n
                    if i2 >= 0:
                        self.userModel.getVarName(i2, pName, buffSize)
                    result = String.valueOf.valueOf(pName)
                break
            break
        return result

    def getPropertyValue(self, index):
        result = ''
        _0 = index
        _1 = False
        while True:
            if _0 == 2:
                _1 = True
                result = String.format.format('%.6g', self.genVars.kVGeneratorBase)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = String.format.format('%.6g', self.kWBase)
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                result = String.format.format('%.6g', self.PFNominal)
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                result = self.yearlyShape
                break
            if (_1 is True) or (_0 == 7):
                _1 = True
                result = self.dailyDispShape
                break
            if (_1 is True) or (_0 == 8):
                _1 = True
                result = self.dutyShape
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                result = String.format.format('%.6g', self.kVArBase)
                break
            if (_1 is True) or (_0 == 18):
                _1 = True
                result = String.format.format('%.6g', self.kVArMax)
                break
            if (_1 is True) or (_0 == 19):
                _1 = True
                result = String.format.format('%.6g', self.kVArMin)
                break
            if (_1 is True) or (_0 == 25):
                _1 = True
                result = String.format.format('%.6g', self.genVars.kVARating)
                break
            if (_1 is True) or (_0 == 26):
                _1 = True
                result = String.format.format('%.6g', self.genVars.kVARating * 0.001)
                break
            if (_1 is True) or (_0 == 33):
                _1 = True
                result = '(' + super(GeneratorObjImpl, self).getPropertyValue(index) + ')'
                break
            if (_1 is True) or (_0 == 35):
                _1 = True
                result = '(' + super(GeneratorObjImpl, self).getPropertyValue(index) + ')'
                break
            if True:
                _1 = True
                result = super(GeneratorObjImpl, self).getPropertyValue(index)
                break
            break
        return result

    def makePosSequence(self):
        """Make a positive sequence model"""
        s = 'Phases=1 conn=wye'
        # make sure voltage is line-neutral
        if (self.nPhases > 1) or (self.connection != 0):
            V = self.genVars.kVGeneratorBase / DSSGlobals.SQRT3
        else:
            V = self.genVars.kVGeneratorBase
        s = s + String.format.format(' kV=%-.5g', V)
        # divide the load by no. phases
        if self.nPhases > 1:
            s = s + String.format.format(' kW=%-.5g  PF=%-.5g', self.kWBase / self.nPhases, self.PFNominal)
            if (self.prpSequence[18] != 0) or (self.prpSequence[19] != 0):
                s = s + String.format.format(' maxkvar=%-.5g  minkvar=%-.5g', self.kVArMax / self.nPhases, self.kVArMin / self.nPhases)
            if self.prpSequence[25] > 0:
                s = s + String.format.format(' kva=%-.5g  ', self.genVars.kVARating / self.nPhases)
            if self.prpSequence[26] > 0:
                s = s + String.format.format(' MVA=%-.5g  ', self.genVars.kVARating / 1000.0 / self.nPhases)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(GeneratorObjImpl, self).makePosSequence()

    def setConductorClosed(self, index, value):
        super(GeneratorObjImpl, self).setConductorClosed(index, value)
        # just turn generator on or off
        if value:
            self.genSwitchOpen = False
        else:
            self.genSwitchOpen = True

    def setPowerFactor(self, value):
        self.PFNominal = value
        self.syncUpPowerQuantities()

    def setPresentKV(self, value):
        self.genVars.kVGeneratorBase = value
        _0 = self.nPhases
        _1 = False
        while True:
            if _0 == 2:
                _1 = True
                self.VBase = self.genVars.kVGeneratorBase * DSSGlobals.InvSQRT3x1000
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.VBase = self.genVars.kVGeneratorBase * DSSGlobals.InvSQRT3x1000
                break
            if True:
                _1 = True
                self.VBase = self.genVars.kVGeneratorBase * 1000.0
                break
            break

    def setPresentKVAr(self, value):
        self.kVArBase = value
        self.genVars.QNominalPerPhase = (1000.0 * self.kVArBase) / self.nPhases
        # init to something reasonable
        kVA_Gen = self.Math.sqrt(self.Math.pow(self.kWBase, 2) + self.Math.pow(self.kVArBase, 2))
        if kVA_Gen != 0.0:
            self.setPowerFactor(self.kWBase / kVA_Gen)
        else:
            self.setPowerFactor(1.0)
        if self.kWBase * self.kVArBase < 0.0:
            self.setPowerFactor(-self.PFNominal)
        self.kVArMax = 2.0 * self.kVArBase
        self.kVArMin = -self.kVArMax

    def setPresentKW(self, value):
        # FIXME Private method in OpenDSS
        self.kWBase = value
        self.syncUpPowerQuantities()

    def syncUpPowerQuantities(self):
        # keep kvar nominal up to date with kW and PF
        if self.PFNominal != 0.0:
            self.kVArBase = self.kWBase * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
            self.genVars.QNominalPerPhase = (1000.0 * self.kVArBase) / self.nPhases
            self.kVArMax = 2.0 * self.kVArBase
            self.kVArMin = -self.kVArMax
            if self.PFNominal < 0.0:
                self.kVArBase = -self.kVArBase
            if self.kVANotSet:
                self.genVars.kVARating = self.kWBase * 1.2

    def setDragHandRegister(self, reg, value):
        # FIXME Private method in OpenDSS
        if value > self.registers[reg]:
            self.registers[reg] = value

    def setKwKVAr(self, PkW, QkVar):
        self.setKWBase(PkW)
        self.setPresentKVAr(QkVar)

    def calcVThevDyn(self):
        """3-phase Voltage behind transient reactance."""
        if self.genSwitchOpen:
            self.VThevMag = 0.0
        self.VThev = ComplexUtil.pclx(self.VThevMag, self.genVars.theta)

    def isForcedOn(self):
        return self.forcedON

    def setForcedOn(self, forced):
        self.forcedON = forced

    def getPowerFactor(self):
        return self.PFNominal

    def getConnection(self):
        return self.connection

    def setConnection(self, conn):
        self.connection = conn

    def getDailyDispShape(self):
        return self.dailyDispShape

    def setDailyDispShape(self, shape):
        self.dailyDispShape = shape

    def getDailyDispShapeObj(self):
        return self.dailyDispShapeObj

    def setDailyDispShapeObj(self, shapeObj):
        self.dailyDispShapeObj = shapeObj

    def getDutyShape(self):
        return self.dutyShape

    def setDutyShape(self, shape):
        self.dutyShape = shape

    def getDutyShapeObj(self):
        return self.dutyShapeObj

    def setDutyShapeObj(self, shapeObj):
        self.dutyShapeObj = shapeObj

    def getGenClass(self):
        return self.genClass

    def setGenClass(self, cls):
        self.genClass = cls

    def getGenModel(self):
        return self.genModel

    def setGenModel(self, model):
        self.genModel = model

    def getGenVars(self):
        return self.genVars

    def setGenVars(self, vars):
        self.genVars = vars

    def getKVArBase(self):
        return self.kVArBase

    def setKVArBase(self, base):
        self.kVArBase = base

    def getKVArMax(self):
        return self.kVArMax

    def setKVArMax(self, max):
        self.kVArMax = max

    def getKVArMin(self):
        return self.kVArMin

    def setKVArMin(self, min):
        self.kVArMin = min

    def getKWBase(self):
        return self.kWBase

    def setKWBase(self, base):
        self.kWBase = base

    def getVpu(self):
        return self.Vpu

    def setVpu(self, vpu):
        self.Vpu = vpu

    def getVTarget(self):
        return self.VTarget

    def setVTarget(self, target):
        self.VTarget = target

    def getYearlyShape(self):
        return self.yearlyShape

    def setYearlyShape(self, shape):
        self.yearlyShape = shape

    def getYearlyShapeObj(self):
        return self.yearlyShapeObj

    def setYearlyShapeObj(self, shapeObj):
        self.yearlyShapeObj = shapeObj

    def getRegisters(self):
        return self.registers

    def setRegisters(self, values):
        self.registers = values

    def getDerivatives(self):
        return self.derivatives

    def setDerivatives(self, values):
        # FIXME Private members in OpenDSS
        self.derivatives = values

    def getYeq(self):
        return self.Yeq

    def setYeq(self, yeq):
        self.Yeq = yeq

    def getYeq95(self):
        return self.Yeq95

    def setYeq95(self, yeq95):
        self.Yeq95 = yeq95

    def getYeq105(self):
        return self.Yeq105

    def setYeq105(self, yeq105):
        self.Yeq105 = yeq105

    def getCurrentLimit(self):
        return self.currentLimit

    def setCurrentLimit(self, limit):
        self.currentLimit = limit

    def isDebugTrace(self):
        return self.debugTrace

    def setDebugTrace(self, debug):
        self.debugTrace = debug

    def getDeltaQMax(self):
        return self.deltaQMax

    def setDeltaQMax(self, delta):
        self.deltaQMax = delta

    def getDispatchMode(self):
        return self.dispatchMode

    def setDispatchMode(self, mode):
        self.dispatchMode = mode

    def getDispatchValue(self):
        return self.dispatchValue

    def setDispatchValue(self, value):
        self.dispatchValue = value

    def getDQDV(self):
        return self.dQdV

    def setDQDV(self, value):
        self.dQdV = value

    def getDQDVSaved(self):
        return self.dQdVSaved

    def setDQDVSaved(self, value):
        self.dQdVSaved = value

    def isFirstSampleAfterReset(self):
        return self.firstSampleAfterReset

    def setFirstSampleAfterReset(self, firstSample):
        self.firstSampleAfterReset = firstSample

    def isFixed(self):
        return self.fixed

    def setFixed(self, value):
        self.fixed = value

    def getGeneratorSolutionCount(self):
        return self.generatorSolutionCount

    def setGeneratorSolutionCount(self, count):
        self.generatorSolutionCount = count

    def getGenFundamental(self):
        return self.genFundamental

    def setGenFundamental(self, fundamental):
        self.genFundamental = fundamental

    def isGenOn(self):
        return self.genOn

    def setGenOn(self, on):
        self.genOn = on

    def isGenSwitchOpen(self):
        return self.genSwitchOpen

    def setGenSwitchOpen(self, open):
        self.genSwitchOpen = open

    def iskVANotSet(self):
        return self.kVANotSet

    def setkVANotSet(self, value):
        self.kVANotSet = value

    def getLastGrowthFactor(self):
        return self.lastGrowthFactor

    def setLastGrowthFactor(self, factor):
        self.lastGrowthFactor = factor

    def getLastYear(self):
        return self.lastYear

    def setLastYear(self, year):
        self.lastYear = year

    def getOpenGeneratorSolutionCount(self):
        return self.openGeneratorSolutionCount

    def setOpenGeneratorSolutionCount(self, count):
        self.openGeneratorSolutionCount = count

    def getPVFactor(self):
        return self.PVFactor

    def setPVFactor(self, factor):
        self.PVFactor = factor

    def getRandomMult(self):
        return self.randomMult

    def setRandomMult(self, mult):
        self.randomMult = mult

    def getRegHours(self):
        return self.regHours

    def setRegHours(self, hours):
        self.regHours = hours

    def getRegKVArh(self):
        return self.regKVArh

    def setRegKVArh(self, kvarh):
        self.regKVArh = kvarh

    def getRegKWh(self):
        return self.regKWh

    def setRegKWh(self, value):
        self.regKWh = value

    def getRegMaxKVA(self):
        return self.regMaxKVA

    def setReg_MaxkVA(self, maxKVA):
        self.regMaxKVA = maxKVA

    def getReg_MaxkW(self):
        return self.regMaxKW

    def setRegMaxKW(self, maxKW):
        self.regMaxKW = maxKW

    def getRegPrice(self):
        return self.regPrice

    def setRegPrice(self, price):
        self.regPrice = price

    def getShapeFactor(self):
        return self.shapeFactor

    def setShapeFactor(self, factor):
        self.shapeFactor = factor

    def getThetaHarm(self):
        return self.thetaHarm

    def setThetaHarm(self, theta):
        self.thetaHarm = theta

    def getTraceFile(self):
        return self.traceFile

    def setTraceFile(self, file):
        self.traceFile = file

    def getV_Avg(self):
        return self.VAvg

    def setV_Avg(self, value):
        self.VAvg = value

    def getVRemembered(self):
        return self.VRemembered

    def setVRemembered(self, value):
        self.VRemembered = value

    def getVArRemembered(self):
        return self.varRemembered

    def setVArRemembered(self, value):
        self.varRemembered = value

    def getVArBase(self):
        return self.varBase

    def setVArBase(self, base):
        self.varBase = base

    def getVArMax(self):
        return self.varMax

    def setVArMax(self, max):
        self.varMax = max

    def getVArMin(self):
        return self.varMin

    def setVArMin(self, min):
        self.varMin = min

    def getVBase(self):
        return self.VBase

    def setVBase(self, value):
        self.VBase = value

    def getVBase105(self):
        return self.VBase105

    def setVBase105(self, value):
        self.VBase105 = value

    def getVBase95(self):
        return self.VBase95

    def setVBase95(self, value):
        self.VBase95 = value

    def getVMaxPU(self):
        return self.VMaxPU

    def setVMaxPU(self, value):
        self.VMaxPU = value

    def getVMinPU(self):
        return self.VMinPU

    def setVMinPU(self, value):
        self.VMinPU = value

    def getVThev(self):
        return self.VThev

    def setVThev(self, value):
        self.VThev = value

    def getVThevHarm(self):
        return self.VThevHarm

    def setVThevHarm(self, value):
        self.VThevHarm = value

    def getVThevMag(self):
        return self.VThevMag

    def setVThevMag(self, mag):
        self.VThevMag = mag

    def getYPrimOpenCond(self):
        return self.YPrimOpenCond

    def setYPrimOpenCond(self, value):
        self.YPrimOpenCond = value

    def getYQFixed(self):
        return self.YQFixed

    def setYQFixed(self, value):
        self.YQFixed = value

    def isShapeIsActual(self):
        return self.shapeIsActual

    def setShapeIsActual(self, model):
        self.shapeIsActual = model

    def getUserModel(self):
        return self.userModel

    def setUserModel(self, model):
        self.userModel = model

    def getShaftModel(self):
        return self.shaftModel

    def setShaftModel(self, model):
        self.shaftModel = model
