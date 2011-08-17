from dss.shared.Dynamics import (Dynamics,)
from dss.common.impl.Utilities import (Utilities,)
from dss.conversion.PVSystemObj import (PVSystemObj,)
from dss.parser.impl.Parser import (Parser,)
from dss.conversion.PVSystem import (PVSystem,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.conversion.impl.PVSystemUserModelImpl import (PVSystemUserModelImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class PVSystemObjImpl(PCElementImpl, PVSystemObj):
    CDOUBLEONE = Complex(1.0, 1.0)
    cBuffer = [None] * 24
    Yeq = None
    # at nominal
    Yeq95 = None
    # at VMin
    Yeq105 = None
    # at VMax
    debugTrace = None
    PVSystemSolutionCount = None
    PVSystemFundamental = None
    # Thevinen equivalent voltage mag and angle reference for harmonic model
    PVSystemObjSwitchOpen = None
    firstSampleAfterReset = None
    PFSpecified = None
    kVArSpecified = None
    kVARating = None
    kVPVSystemBase = None
    kVArOut = None
    kWOut = None
    panelKW = None
    # computed
    irradiance = None
    kVArRequested = None
    temperature = None
    Pmpp = None
    effFactor = None
    tempFactor = None
    inverterON = None
    pctCutIn = None
    pctCutOut = None
    cutInKW = None
    cutOutKW = None
    pctR = None
    pctX = None
    openPVSystemSolutionCount = None
    PNominalPerPhase = None
    QNominalPerPhase = None
    randomMult = None
    regHours = None
    regKVArh = None
    regKWh = None
    regMaxKVA = None
    regMaxKW = None
    regPrice = None
    shapeFactor = None
    TShapeValue = None
    thetaHarm = None
    # Thevinen equivalent voltage mag and angle reference for harmonic model
    traceFile = None
    userModel = None
    # User-written models
    varBase = None
    # base vars per phase
    VBase = None
    # base volts suitable for computing currents
    VBase105 = None
    VBase95 = None
    VMaxPU = None
    VMinPU = None
    VThevHarm = None
    # Thevinen equivalent voltage mag and angle reference for harmonic model
    YPrimOpenCond = None
    RThev = None
    XThev = None
    # public members
    connection = None
    # 0 = line-neutral; 1 = delta
    dailyShape = None
    # daily (24 HR) PVSystem element irradiance shape
    dailyShapeObj = None
    # daily PVSystem element irradiance shape for this load
    dutyShape = None
    # duty cycle irradiance shape for changes typically less than one hour
    dutyShapeObj = None
    # irradiance shape for this PVSystem element
    yearlyShape = None
    yearlyShapeObj = None
    # yearly irradiance shape for this PVSystem element
    dailyTShape = None
    dailyTShapeObj = None
    dutyTShape = None
    dutyTShapeObj = None
    yearlyTShape = None
    yearlyTShapeObj = None
    inverterCurve = None
    inverterCurveObj = None
    powerTempCurve = None
    powerTempCurveObj = None
    FClass = None
    voltageModel = None
    # variation with voltage
    PFNominal = None
    registers = [None] * PVSystem.NumPVSystemRegisters
    derivatives = [None] * PVSystem.NumPVSystemRegisters

    def __init__(self, parClass, PVSystemName):
        super(PVSystemObjImpl, self)(parClass)
        self.setName(PVSystemName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # + PVSystem_ELEMENT;  // in both PCElement and PVSystemElement list
        self.setNPhases(3)
        self.nConds = 4
        # defaults to wye
        self.YOrder = 0
        # to trigger an initial allocation
        self.setNTerms(1)
        # forces allocations
        self.yearlyShape = ''
        self.yearlyShapeObj = None
        # if yearlyShapeObj = null then the irradiance always stays nominal
        self.dailyShape = ''
        self.dailyShapeObj = None
        # if dailyShapeObj = null then the irradiance always stays nominal
        self.dutyShape = ''
        self.dutyShapeObj = None
        # if dutyShapeObj = null then the irradiance always stays nominal
        self.yearlyTShape = ''
        self.yearlyTShapeObj = None
        # if yearlyShapeObj = null then the temperature always stays nominal
        self.dailyTShape = ''
        self.dailyTShapeObj = None
        # if dailyShapeObj = null then the temperature always stays nominal
        self.dutyTShape = ''
        self.dutyTShapeObj = None
        # if dutyShapeObj = null then the temperature always stays nominal
        self.inverterCurveObj = None
        self.powerTempCurveObj = None
        self.inverterCurve = ''
        self.powerTempCurve = ''
        self.connection = 0
        # wye (star, L-N)
        self.voltageModel = 1
        # typical fixed kW negative load
        self.FClass = 1
        self.PVSystemSolutionCount = -1
        # for keep track of the present solution in injCurrent calcs
        self.openPVSystemSolutionCount = -1
        self.YPrimOpenCond = None
        self.kVPVSystemBase = 12.47
        self.VBase = 7200.0
        self.VMinPU = 0.9
        self.VMaxPU = 1.1
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.YOrder = self.nTerms * self.nConds
        self.randomMult = 1.0
        self.PFSpecified = True
        self.kVArSpecified = False
        self.inverterON = True
        # start with inverterOn
        self.temperature = 25.0
        self.irradiance = 1.0
        # kW / sq-m
        self.pctCutIn = 20.0
        self.pctCutOut = 20.0
        # Output rating stuff
        self.kWOut = 500.0
        self.kVArOut = 0.0
        self.PFNominal = 1.0
        self.kVARating = 500.0
        self.Pmpp = 500.0
        self.pctR = 0.0
        self.pctX = 50.0
        self.userModel = PVSystemUserModelImpl()
        self.regKWh = 0
        self.regKVArh = 1
        self.regMaxKW = 2
        self.regMaxKVA = 3
        self.regHours = 4
        self.regPrice = 5
        self.debugTrace = False
        self.PVSystemObjSwitchOpen = False
        self.spectrum = ''
        # override base class
        self.spectrumObj = None
        self.initPropertyValues(0)
        self.recalcElementData()

    def initPropertyValues(self, arrayOffset):
        self.setPropertyValue(0, '3')
        # "phases";
        self.setPropertyValue(1, self.getBus(0))
        # "bus1";
        self.setPropertyValue(PVSystem.KV, String.format.format('%-g', self.kVPVSystemBase))
        self.setPropertyValue(PVSystem.IRRADIANCE, String.format.format('%-g', self.irradiance))
        self.setPropertyValue(PVSystem.PF, String.format.format('%-g', self.PFNominal))
        self.setPropertyValue(PVSystem.MODEL, '1')
        self.setPropertyValue(PVSystem.YEARLY, '')
        self.setPropertyValue(PVSystem.DAILY, '')
        self.setPropertyValue(PVSystem.DUTY, '')
        self.setPropertyValue(PVSystem.T_YEARLY, '')
        self.setPropertyValue(PVSystem.T_DAILY, '')
        self.setPropertyValue(PVSystem.T_DUTY, '')
        self.setPropertyValue(PVSystem.CONNECTION, 'wye')
        self.setPropertyValue(PVSystem.KVAR, String.format.format('%-g', self.getPresentKVAr()))
        self.setPropertyValue(PVSystem.PCTR, String.format.format('%-g', self.pctR))
        self.setPropertyValue(PVSystem.PCTX, String.format.format('%-g', self.pctX))
        self.setPropertyValue(PVSystem.CLASS, '1')
        # "class"
        self.setPropertyValue(PVSystem.INV_EFF_CURVE, '')
        self.setPropertyValue(PVSystem.TEMP, String.format.format('%-g', self.temperature))
        self.setPropertyValue(PVSystem.PMPP, String.format.format('%-g', self.Pmpp))
        self.setPropertyValue(PVSystem.P_T_CURVE, '')
        self.setPropertyValue(PVSystem.CUT_IN, '20')
        self.setPropertyValue(PVSystem.CUT_OUT, '20')
        self.setPropertyValue(PVSystem.VMIN_PU, '0.90')
        self.setPropertyValue(PVSystem.VMAX_PU, '1.10')
        self.setPropertyValue(PVSystem.KVA, String.format.format('%-g', self.kVARating))
        self.setPropertyValue(PVSystem.USER_MODEL, '')
        # UserModel
        self.setPropertyValue(PVSystem.USER_DATA, '')
        # UserData
        self.setPropertyValue(PVSystem.DEBUG_TRACE, 'NO')
        super(PVSystemObjImpl, self).initPropertyValues(PVSystem.NumPropsThisClass)

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == PVSystem.KV:
                _1 = True
                return String.format.format('%.6g', self.kVPVSystemBase)
            if (_1 is True) or (_0 == PVSystem.IRRADIANCE):
                _1 = True
                return String.format.format('%.6g', self.irradiance)
            if (_1 is True) or (_0 == PVSystem.PF):
                _1 = True
                return String.format.format('%.6g', self.PFNominal)
            if (_1 is True) or (_0 == PVSystem.MODEL):
                _1 = True
                return String.format.format('%d', self.voltageModel)
            if (_1 is True) or (_0 == PVSystem.YEARLY):
                _1 = True
                return self.yearlyShape
            if (_1 is True) or (_0 == PVSystem.DAILY):
                _1 = True
                return self.dailyShape
            if (_1 is True) or (_0 == PVSystem.DUTY):
                _1 = True
                return self.dutyShape
            if (_1 is True) or (_0 == PVSystem.T_YEARLY):
                _1 = True
                return self.yearlyTShape
            if (_1 is True) or (_0 == PVSystem.T_DAILY):
                _1 = True
                return self.dailyTShape
            if (_1 is True) or (_0 == PVSystem.T_DUTY):
                _1 = True
                return self.dutyTShape
                # case PVSystem.propCONNECTION :;
            if (_1 is True) or (_0 == PVSystem.KVAR):
                _1 = True
                return String.format.format('%.6g', self.kVArOut)
            if (_1 is True) or (_0 == PVSystem.PCTR):
                _1 = True
                return String.format.format('%.6g', self.pctR)
            if (_1 is True) or (_0 == PVSystem.PCTX):
                _1 = True
                return String.format.format('%.6g', self.pctX)
                # case PVSystem.propCLASS      = 17;
            if (_1 is True) or (_0 == PVSystem.INV_EFF_CURVE):
                _1 = True
                return self.inverterCurve
            if (_1 is True) or (_0 == PVSystem.TEMP):
                _1 = True
                return String.format.format('%.6g', self.temperature)
            if (_1 is True) or (_0 == PVSystem.PMPP):
                _1 = True
                return String.format.format('%.6g', self.Pmpp)
            if (_1 is True) or (_0 == PVSystem.P_T_CURVE):
                _1 = True
                return self.powerTempCurve
            if (_1 is True) or (_0 == PVSystem.CUT_IN):
                _1 = True
                return String.format.format('%.6g', self.pctCutIn)
            if (_1 is True) or (_0 == PVSystem.CUT_OUT):
                _1 = True
                return String.format.format('%.6g', self.pctCutOut)
            if (_1 is True) or (_0 == PVSystem.VMIN_PU):
                _1 = True
                return String.format.format('%.6g', self.VMinPU)
            if (_1 is True) or (_0 == PVSystem.VMAX_PU):
                _1 = True
                return String.format.format('%.6g', self.VMaxPU)
            if (_1 is True) or (_0 == PVSystem.KVA):
                _1 = True
                return String.format.format('%.6g', self.kVARating)
            if (_1 is True) or (_0 == PVSystem.USER_MODEL):
                _1 = True
                return self.userModel.getName()
            if (_1 is True) or (_0 == PVSystem.USER_DATA):
                _1 = True
                return '(' + super(PVSystemObjImpl, self).getPropertyValue(index) + ')'
                # case PVSystem.propDEBUGTRACE = 33;
            if True:
                _1 = True
                return super(PVSystemObjImpl, self).getPropertyValue(index)
            break

    def randomize(self, opt):
        """0 = reset to 1.0; 1 = Gaussian around mean and std dev; 2 = uniform"""
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
        if self.dailyShapeObj is not None:
            self.shapeFactor = self.dailyShapeObj.getMult(hr)
        else:
            self.shapeFactor = self.CDOUBLEONE
            # default to no variation

    def calcDailyTemperature(self, hr):
        if self.dailyTShapeObj is not None:
            self.TShapeValue = self.dailyTShapeObj.getTemperature(hr)
        else:
            self.TShapeValue = 1.0
            # default to no variation

    def calcDutyMult(self, hr):
        if self.dutyShapeObj is not None:
            self.shapeFactor = self.dutyShapeObj.getMult(hr)
        else:
            self.calcDailyMult(hr)
            # default to daily mult if no duty curve specified

    def calcDutyTemperature(self, hr):
        if self.dutyTShapeObj is not None:
            self.TShapeValue = self.dutyTShapeObj.getTemperature(hr)
        else:
            self.calcDailyTemperature(hr)
            # default to daily mult if no duty curve specified

    def calcYearlyMult(self, hr):
        if self.yearlyShapeObj is not None:
            self.shapeFactor = self.yearlyShapeObj.getMult(hr)
        else:
            self.calcDailyMult(hr)
            # defaults to daily curve

    def calcYearlyTemperature(self, hr):
        if self.yearlyTShapeObj is not None:
            self.TShapeValue = self.yearlyTShapeObj.getTemperature(hr)
        else:
            self.calcDailyTemperature(hr)
            # defaults to daily curve

    def setNominalPVSystemOuput(self):
        self.shapeFactor = self.CDOUBLEONE
        # init here; changed by curve routine
        self.TShapeValue = 25.0
        # init here; changed by curve routine
        # check to make sure the PVSystem element is on
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        if not (sol.isDynamicModel() or sol.isHarmonicModel()):
            # leave PVSystem element in whatever state it was prior to entering dynamic mode
            # check dispatch to see what state the PVSystem element should be in
            _0 = sol.getMode()
            _1 = False
            while True:
                if _0 == Dynamics.SNAPSHOT:
                    _1 = True
                    break
                if (_1 is True) or (_0 == Dynamics.DAILYMODE):
                    _1 = True
                    self.calcDailyMult(sol.getDblHour())
                    self.calcDailyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.YEARLYMODE):
                    _1 = True
                    self.calcYearlyMult(sol.getDblHour())
                    self.calcYearlyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.MONTECARLO1):
                    _1 = True
                    break
                if (_1 is True) or (_0 == Dynamics.MONTEFAULT):
                    _1 = True
                    break
                if (_1 is True) or (_0 == Dynamics.FAULTSTUDY):
                    _1 = True
                    break
                if (_1 is True) or (_0 == Dynamics.DYNAMICMODE):
                    _1 = True
                    break
                    # assume daily curve, if any, for the following
                if (_1 is True) or (_0 == Dynamics.MONTECARLO2):
                    _1 = True
                    self.calcDailyMult(sol.getDblHour())
                    self.calcDailyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.MONTECARLO3):
                    _1 = True
                    self.calcDailyMult(sol.getDblHour())
                    self.calcDailyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.LOADDURATION1):
                    _1 = True
                    self.calcDailyMult(sol.getDblHour())
                    self.calcDailyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.LOADDURATION2):
                    _1 = True
                    self.calcDailyMult(sol.getDblHour())
                    self.calcDailyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.PEAKDAY):
                    _1 = True
                    self.calcDailyMult(sol.getDblHour())
                    self.calcDailyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.DUTYCYCLE):
                    _1 = True
                    self.calcDutyMult(sol.getDblHour())
                    self.calcDutyTemperature(sol.getDblHour())
                    break
                if (_1 is True) or (_0 == Dynamics.AUTOADDFLAG):
                    _1 = True
                    break
                break
            self.computeKwKVAr()
            self.PNominalPerPhase = (1000.0 * self.kWOut) / self.getNPhases()
            self.QNominalPerPhase = (1000.0 * self.kVArOut) / self.getNPhases()
            _2 = self.voltageModel
            _3 = False
            while True:
                if _2 == 3:
                    _3 = True
                    break
                if True:
                    _3 = True
                    self.Yeq = ComplexUtil.divide(Complex(self.PNominalPerPhase, -self.QNominalPerPhase), MathUtil.sqr(self.VBase))
                    # VBase must be L-N for 3-phase
                    if self.VMinPU != 0.0:
                        self.Yeq95 = ComplexUtil.divide(self.Yeq, MathUtil.sqr(self.VMinPU))
                        # at 95% voltage
                    else:
                        self.Yeq95 = self.Yeq
                        # Always a constant Z model
                    if self.VMaxPU != 0.0:
                        self.Yeq105 = ComplexUtil.divide(self.Yeq, MathUtil.sqr(self.VMaxPU))
                        # at 105% voltage
                    else:
                        self.Yeq105 = self.Yeq
                    break
                break
            # When we leave here, all the Yeq's are in L-N values

    def recalcElementData(self):
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.varBase = (1000.0 * self.kVArOut) / self.nPhases
        # values in ohms for Thevenin equivalents
        self.RThev = ((self.pctR * 0.01 * MathUtil.sqr(self.getPresentKV())) / self.kVARating) * 1000.0
        self.XThev = ((self.pctX * 0.01 * MathUtil.sqr(self.getPresentKV())) / self.kVARating) * 1000.0
        self.cutInKW = (self.pctCutIn * self.kVARating) / 100.0
        self.cutOutKW = (self.pctCutOut * self.kVARating) / 100.0
        self.setNominalPVSystemOuput()
        # Now check for errors. If any of these came out nil and the string was not nil, give warning.
        if self.yearlyShapeObj is None:
            if len(self.yearlyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Yearly load shape: \"' + self.yearlyShape + '\" not found.', 563)
        if self.dailyShapeObj is None:
            if len(self.dailyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Daily load shape: \"' + self.dailyShape + '\" not found.', 564)
        if self.dutyShapeObj is None:
            if len(self.dutyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Duty load shape: \"' + self.dutyShape + '\" not found.', 565)
        if self.yearlyTShapeObj is None:
            if len(self.yearlyTShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Yearly temperature shape: \"' + self.yearlyTShape + '\" not found.', 5631)
        if self.dailyTShapeObj is None:
            if len(self.dailyTShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Daily temperature shape: \"' + self.dailyTShape + '\" not found.', 5641)
        if self.dutyTShapeObj is None:
            if len(self.dutyTShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Duty temperature shape: \"' + self.dutyTShape + '\" not found.', 5651)
        if len(self.getSpectrum()) > 0:
            self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
            if self.getSpectrumObj() is None:
                DSSGlobals.doSimpleMsg('Error: Spectrum \"' + self.getSpectrum() + '\" not found.', 566)
        else:
            self.setSpectrumObj(None)
        # initialize to zero - defaults to PQ PVSystem element
        # solution object will reset after circuit modifications
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))
        # Update any user-written models
        if self.userModel.exists():
            self.userModel.updateModel()

    def calcYPrimMatrix(self, YMatrix):
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        self.YPrimFreq = sol.getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        if sol.isHarmonicModel():
            # Yeq is computed from %R and %X -- inverse of Rthev + j Xthev
            # sol.isDynamicModel() ||
            Y = self.Yeq
            # L-N value computed in initialization routines
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
                        YMatrix.setElement(i, i, Y)
                        YMatrix.addElement(self.nConds, self.nConds, Y)
                        YMatrix.setElemSym(i, self.nConds, Yij)
                        break
                    if (_2 is True) or (_1 == 1):
                        _2 = True
                        YMatrix.setElement(i, i, Y)
                        YMatrix.addElement(i, i, Y)
                        # put it in again
                        _3 = True
                        j = 0
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                j += 1
                            if not (j < i):
                                break
                            # TODO Check zero based indexing
                            YMatrix.setElemSym(i, j, Yij)
                        break
                    break
        else:
            # regular power flow PVSystem element model
            # Yeq is always expected as the equivalent line-neutral admittance
            Y = self.Yeq.negate()
            # negate for generation; YEQ is L-N quantity
            # ****** need to modify the base admittance for real harmonics calcs
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
                        YMatrix.setElement(i, i, Y)
                        YMatrix.addElement(self.nConds, self.nConds, Y)
                        YMatrix.setElemSym(i, self.nConds, Yij)
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
                        # TODO Check zero based indexing
                        if j >= self.nConds:
                            j = 0
                            # wrap around for closed connections
                        YMatrix.addElement(i, i, Y)
                        YMatrix.addElement(j, j, Y)
                        YMatrix.addElemSym(i, j, Yij)
                    break
                break

    def computeInverterPower(self):
        effFactor = 1.0
        kW_Out = 0.0
        # determine state of the inverter
        if self.inverterON:
            if self.panelKW < self.cutOutKW:
                self.inverterON = False
        elif self.panelKW >= self.cutInKW:
            self.inverterON = True
        # set inverter output; defaults to 100% of the panelkW if no efficiency curve spec'd
        if self.inverterON:
            if self.inverterCurveObj is not None:
                effFactor = self.inverterCurveObj.getYValue(self.panelKW / self.kVARating)
                # pu eff vs pu power
            kW_Out = self.panelKW * effFactor
        else:
            kW_Out = 0.0
        # kVAr value
        if self.PFSpecified:
            if self.PFNominal == 1.0:
                self.kVArOut = 0.0
            else:
                self.kVArOut = self.kWOut * self.Math.sqrt((1.0 / MathUtil.sqr(self.PFNominal)) - 1.0) * self.Math.signum(self.PFNominal)
                # if PF is negative, make sure kVAr has opposite sign of kW
                # kW will always be positive
        else:
            # kVAr is specified
            self.kVArOut = self.kVArRequested
        # limit kVAr so that kVA of inverter is not exceeded
        kVA_Gen = self.Math.sqrt(MathUtil.sqr(self.kWOut) + MathUtil.sqr(self.kVArOut))
        if kVA_Gen > self.kVARating:
            if self.kWOut > self.kVARating:
                self.kWOut = self.kVARating
                self.kVArOut = 0.0
            else:
                self.kVArOut = self.Math.sqrt(MathUtil.sqr(self.kVARating) - MathUtil.sqr(kW_Out)) * self.Math.signum(self.kVArOut)

    def computeKwKVAr(self):
        self.computePanelPower()
        # apply irradiance
        self.computeInverterPower()
        # apply inverter eff after checking for cutin/cutout

    def computePanelPower(self):
        self.tempFactor = 1.0
        if self.powerTempCurveObj is not None:
            self.tempFactor = self.powerTempCurveObj.getYValue(self.TShapeValue)
            # pu Pmpp vs T (actual)
        self.panelKW = self.irradiance * self.shapeFactor.getReal() * self.Pmpp * self.tempFactor

    def calcYPrim(self):
        # build only shunt Yprim
        # build a dummy Yprim Series so that calcV does not fail
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
        self.setNominalPVSystemOuput()
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
        super(PVSystemObjImpl, self).calcYPrim()

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
                if j >= self.nConds:
                    j = 0
                    # wrap
                termArray[j] = termArray[j].add(curr.negate())
                break
            break

    def writeTraceRecord(self, s):
        # FIXME handle exception
        try:
            if not DSSGlobals.inShowResults:
                fw = self.FileWriter(self.traceFile, True)
                bw = self.BufferedWriter(fw)
                bw.write(String.format.format('%-.g, %d, %-.g, ', DSSGlobals.activeCircuit.getSolution().getDynaVars().t, DSSGlobals.activeCircuit.getSolution().getIteration(), DSSGlobals.activeCircuit.getLoadMultiplier()) + Utilities.getSolutionModeID() + ', ' + Utilities.getLoadModel() + ', ' + self.voltageModel + ', ' + ((self.QNominalPerPhase * 3.0) / 1000000.0) + ', ' + ((self.PNominalPerPhase * 3.0) / 1000000.0) + ', ' + s + ', ')
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
                bw.newLine()
                bw.close()
                fw.close()
        except Exception, e:
            pass # astStmt: [Stmt([]), None]

    def doConstantPQPVsystemObj(self):
        """Compute total terminal current for constant PQ."""
        curr = None
        # treat this just like the load model
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        self.zeroITerminal()
        self.calcVTerminalPhase()
        # get actual voltage across each phase of the load
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
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
                        curr = Complex(self.PNominalPerPhase, self.QNominalPerPhase).divide(V).conjugate()
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
                        curr = Complex(self.PNominalPerPhase, self.QNominalPerPhase).divide(V).conjugate()
                        # between 95% -105%, constant PQ
                    break
                break
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doConstantZPVsystemObj(self):
        """Constant Z model"""
        # assume Yeq is kept up to date
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
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
            if not (i < self.getNPhases()):
                break
            curr = Yeq2.multiply(self.VTerminal[i])
            # Yeq is always line to neutral
            self.stickCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.stickCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doUserModel(self):
        """Compute total terminal current from User-written model"""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        if self.userModel.exists():
            # check automatically selects the user model if true
            self.userModel.calc(self.VTerminal, self.ITerminal)
            self.setITerminalUpdated(True)
            # negate currents from user model for power flow PVSystem element model
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nConds):
                    break
                self.getInjCurrent()[i] = self.getInjCurrent()[i].add(self.ITerminal[i].negate())
        else:
            DSSGlobals.doSimpleMsg('PVSystem.' + self.getName() + ' model designated to use user-written model, but user-written model is not defined.', 567)

    def doDynamicMode(self):
        """Compute total current and add into injTemp

        For now, just assume the PVSystem element is constant power
        for the duration of the dynamic simulation.
        """
        self.doConstantPQPVsystemObj()

    def doHarmonicMode(self):
        """Compute injection current only when in harmonics mode.

        Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built
        Vd is the fundamental frequency voltage behind Xd' for phase 1
        """
        self.computeVTerminal()
        sol = DSSGlobals.activeCircuit.getSolution()
        PVSystemHarmonic = sol.getFrequency() / self.PVSystemFundamental
        if self.getSpectrumObj() is not None:
            e = self.getSpectrumObj().getMult(PVSystemHarmonic).multiply(self.VThevHarm)
            # get base harmonic magnitude
        else:
            e = Complex.ZERO
        e = Utilities.rotatePhasorRad(e, PVSystemHarmonic, self.thetaHarm)
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
                # TODO Check zero based indexing
                e = Utilities.rotatePhasorDeg(e, PVSystemHarmonic, -120.0)
                # assume 3-phase PVSystem element
        # Handle wye connection
        if self.connection == 0:
            self.cBuffer[self.nConds] = self.VTerminal[self.nConds]
            # assume no neutral injection voltage
            # Inj currents = Yprim (E)
        self.YPrim.vMult(self.getInjCurrent(), self.cBuffer)

    def calcVTerminalPhase(self):
        # private void calcVterminal()
        sol = DSSGlobals.activeCircuit.getSolution()
        # Establish phase voltages and stick in VTerminal
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
                    if j >= self.nConds:
                        j = 0
                    self.VTerminal[i] = sol.vDiff(self.nodeRef[i], self.nodeRef[j])
                break
            break
        self.PVSystemSolutionCount = sol.getSolutionCount()

    def calcPVSystemModelContribution(self):
        """This is where the power gets computed.

        Calculates PVSystem element current and adds it properly into the injcurrent array
        routines may also compute ITerminal (ITerminalUpdated flag)
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
            _0 = self.voltageModel
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.doConstantPQPVsystemObj()
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    self.doConstantZPVsystemObj()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    self.doUserModel()
                    break
                if True:
                    _1 = True
                    self.doConstantPQPVsystemObj()
                    # for now, until we implement the other models.
                    break
                break
        # When this is done, ITerminal is up to date

    def calcInjCurrentArray(self):
        """Difference between currents in YPrim and total current."""
        # now get injection currents
        if self.PVSystemObjSwitchOpen:
            self.zeroInjCurrent()
        else:
            self.calcPVSystemModelContribution()

    def getTerminalCurrents(self, curr):
        """Compute total currents."""
        sol = DSSGlobals.activeCircuit.getSolution()
        if self.ITerminalSolutionCount != sol.getSolutionCount():
            # recalc the contribution
            if not self.PVSystemObjSwitchOpen:
                self.calcPVSystemModelContribution()
                # adds totals in ITerminal as a side effect
        super(PVSystemObjImpl, self).getTerminalCurrents(curr)
        if self.debugTrace:
            self.writeTraceRecord('TotalCurrent')

    def injCurrents(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        if sol.loadsNeedUpdating():
            self.setNominalPVSystemOuput()
            # set the nominal kW, etc for the type of solution being done
        self.calcInjCurrentArray()
        # difference between currents in YPrim and total terminal current
        if self.debugTrace:
            self.writeTraceRecord('Injection')
            # add into system injection current array
        return super(PVSystemObjImpl, self).injCurrents()

    def getInjCurrents(self, curr):
        """Gives the currents for the last solution performed.

        Do not call setNominal, as that may change the load values.
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
            DSSGlobals.doErrorMsg('PVSystem object: \"' + self.getName() + '\" in getInjCurrents method.', e.getMessage(), 'Current buffer not big enough.', 568)

    def resetRegisters(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < PVSystem.NumPVSystemRegisters):
                break
            self.registers[i] = 0.0
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < PVSystem.NumPVSystemRegisters):
                break
            self.derivatives[i] = 0.0
        self.firstSampleAfterReset = True
        # initialize for trapezoidal integration

    def integrate(self, reg, deriv, interval):
        ckt = DSSGlobals.activeCircuit
        if ckt.isTrapezoidalIntegration():
            # Trapezoidal rule integration
            if not self.firstSampleAfterReset:
                self.registers[reg] = self.registers[reg] + (0.5 * interval * (deriv + self.derivatives[reg]))
        else:
            # Plain Euler integration
            self.registers[reg] = self.registers[reg] + (interval * deriv)
        self.derivatives[reg] = deriv

    def takeSample(self):
        """Update energy from metered zone"""
        # private void setKWandKvarOut()
        # FIXME Private method in OpenDSS
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        # compute energy in PVSystem element branch
        if self.isEnabled():
            S = Complex(self.getPresentKW(), self.getPresentKVAr())
            SMag = S.abs()
            hourValue = 1.0
            if ckt.isTrapezoidalIntegration():
                # Make sure we always integrate for Trapezoidal case
                # Don't need to for gen off and normal integration.

                if ckt.isPositiveSequence():
                    S = S.multiply(3.0)
                    SMag = 3.0 * SMag
                self.integrate(self.regKWh, S.getReal(), sol.getIntervalHrs())
                # accumulate the power
                self.integrate(self.regKVArh, S.getImaginary(), sol.getIntervalHrs())
                self.setDragHandRegister(self.regMaxKW, self.Math.abs(S.getReal()))
                self.setDragHandRegister(self.regMaxKVA, SMag)
                self.integrate(self.regHours, hourValue, sol.getIntervalHrs())
                # accumulate hours in operation
                self.integrate(self.regPrice, S.getReal() * ckt.getPriceSignal(), sol.getIntervalHrs())
                # accumulate hours in operation
                self.firstSampleAfterReset = False

    def updatePVSystem(self):
        """Update PVSystem elements based on present kW and IntervalHrs variable."""
        # do nothing
        pass

    def getPresentKW(self):
        return self.PNominalPerPhase * 0.001 * self.nPhases

    def getPresentIrradiance(self):
        return self.irradiance * self.shapeFactor.getReal()

    def getPresentKV(self):
        return self.kVPVSystemBase

    def getPresentKVAr(self):
        return self.QNominalPerPhase * 0.001 * self.nPhases

    def dumpProperties(self, f, complete):
        super(PVSystemObjImpl, self).dumpProperties(f, complete)
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
                if _1 == PVSystem.USER_DATA:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=(' + self.getPropertyValue(idx) + ')'
                    break
                if True:
                    _2 = True
                    print '~ ' + self.getParentClass().getPropertyName()[i] + '=' + self.getPropertyValue(idx)
                    break
                break
        print 

    def initHarmonics(self):
        """This routine makes a Thevenin equivalent behis the reactance spec'd in %R and %X."""
        Va = None
        # FIXME Implement connection enum
        sol = DSSGlobals.activeCircuit.getSolution()
        self.setYPrimInvalid(True)
        # force rebuild of YPrims
        self.PVSystemFundamental = sol.getFrequency()
        # whatever the frequency is when we enter here
        self.Yeq = ComplexUtil.invert(Complex(self.RThev, self.XThev))
        # used for current calcs; always L-N
        # Compute reference Thevinen voltage from phase 1 current
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
        e = Va.subtract(self.ITerminal[0].multiply(Complex(self.RThev, self.XThev)))
        self.VThevHarm = e.abs()
        # establish base mag and angle
        self.thetaHarm = e.getArgument()

    def initStateVars(self):
        """For going into dynamics mode"""
        self.setYPrimInvalid(True)
        # force rebuild of YPrims

    def integrateStates(self):
        """Dynamics mode integration routine"""
        pass

    def getVariable(self, i):
        """Return variables one at a time"""
        if i < 0:
            return -9999.0
            # error return value; no state vars  FIXME throw exception
            # for now, report kWh stored and mode
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return self.getPresentIrradiance()
            if (_1 is True) or (_0 == 1):
                _1 = True
                return self.getPanelKW()
            if (_1 is True) or (_0 == 2):
                _1 = True
                return self.getTempFactor()
            if (_1 is True) or (_0 == 3):
                _1 = True
                return self.getEffFactor()
            if True:
                _1 = True
                if self.userModel.exists():
                    n = self.userModel.numVars()
                    k = i - PVSystem.NumPVSystemVariables
                    # TODO Check zero based indexing
                    if k < n:
                        return self.userModel.getVariable(k)
                break
            break
        return -9999.0

    def setVariable(self, i, value):
        if i < 0:
            return
            # no variables to set
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.setIrradiance(value)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                break
            if True:
                _1 = True
                if self.userModel.exists():
                    n = self.userModel.numVars()
                    k = i - PVSystem.NumPVSystemVariables
                    if k < n:
                        # TODO Check zero based indexing
                        self.userModel.setVariable(k, value)
                        return
                break
            break

    def getAllVariables(self, states):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < PVSystem.NumPVSystemVariables):
                break
            states[i] = self.getVariable(i)
        if self.userModel.exists():
            self.userModel.getAllVars(states[PVSystem.NumPVSystemVariables + 1])

    def numVariables(self):
        Result = PVSystem.NumPVSystemVariables
        if self.userModel.exists():
            Result = Result + self.userModel.numVars()
        return Result

    def variableName(self, i):
        buffSize = 255
        # char[] Buff = new char[BuffSize];
        result = ''
        if i < 0:
            return result
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return 'Irradiance'
            if (_1 is True) or (_0 == 1):
                _1 = True
                return 'PanelkW'
            if (_1 is True) or (_0 == 2):
                _1 = True
                return 'P_TFactor'
            if (_1 is True) or (_0 == 3):
                _1 = True
                return 'Efficiency'
            if True:
                _1 = True
                if self.userModel.exists():
                    pName = 0
                    n = self.userModel.numVars()
                    i2 = i - PVSystem.NumPVSystemVariables
                    if i2 < n:
                        self.userModel.getVarName(i2, pName, buffSize)
                        return String.valueOf.valueOf(pName)
                break
            break
        return result

    def makePosSequence(self):
        """Make a positive sequence model"""
        s = 'Phases=1 conn=wye'
        # make sure voltage is line-neutral
        if (self.nPhases > 1) or (self.connection != 0):
            V = self.kVPVSystemBase / DSSGlobals.SQRT3
        else:
            V = self.kVPVSystemBase
        s = s + String.format.format(' kV=%-.5g', V)
        if self.nPhases > 1:
            s = s + String.format.format(' kva=%-.5g  PF=%-.5g', self.kVARating / self.nPhases, self.PFNominal)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(PVSystemObjImpl, self).makePosSequence()
        # write out other properties

    def setConductorClosed(self, index, value):
        # just turn PVSystem element on or off
        if value:
            self.PVSystemObjSwitchOpen = False
        else:
            self.PVSystemObjSwitchOpen = True

    def setPowerFactor(self, value):
        self.PFNominal = value
        self.PFSpecified = True

    def setPresentIrradiance(self, value):
        self.irradiance = value

    def setPresentKV(self, value):
        self.kVPVSystemBase = value
        _0 = self.nPhases
        _1 = False
        while True:
            if _0 == 2:
                _1 = True
                self.VBase = self.kVPVSystemBase * DSSGlobals.InvSQRT3x1000
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.VBase = self.kVPVSystemBase * DSSGlobals.InvSQRT3x1000
                break
            if True:
                _1 = True
                self.VBase = self.kVPVSystemBase * 1000.0
                break
            break

    def setPresentKVAr(self, value):
        self.kVArRequested = value

    def setDragHandRegister(self, reg, value):
        if value > self.registers[reg]:
            self.registers[reg] = value

    def getPowerFactor(self):
        return self.PFNominal

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

    def getYearlyShape(self):
        return self.yearlyShape

    def setYearlyShape(self, shape):
        self.yearlyShape = shape

    def getYearlyShapeObj(self):
        return self.yearlyShapeObj

    def setYearlyShapeObj(self, shapeObj):
        self.yearlyShapeObj = shapeObj

    def getDailyTShape(self):
        return self.dailyTShape

    def setDailyTShape(self, shape):
        self.dailyTShape = shape

    def getDailyTShapeObj(self):
        return self.dailyTShapeObj

    def setDailyTShapeObj(self, shapeObj):
        self.dailyTShapeObj = shapeObj

    def getDutyTShape(self):
        return self.dutyTShape

    def setDutyTShape(self, shape):
        self.dutyTShape = shape

    def getDutyTShapeObj(self):
        return self.dutyTShapeObj

    def setDutyTShapeObj(self, shapeObj):
        self.dutyTShapeObj = shapeObj

    def getYearlyTShape(self):
        return self.yearlyTShape

    def setYearlyTShape(self, shape):
        self.yearlyTShape = shape

    def getYearlyTShapeObj(self):
        return self.yearlyTShapeObj

    def setYearlyTShapeObj(self, shapeObj):
        self.yearlyTShapeObj = shapeObj

    def getInverterCurve(self):
        return self.inverterCurve

    def setInverterCurve(self, curve):
        self.inverterCurve = curve

    def getInverterCurveObj(self):
        return self.inverterCurveObj

    def setInverterCurveObj(self, curveObj):
        self.inverterCurveObj = curveObj

    def getPowerTempCurve(self):
        return self.powerTempCurve

    def setPowerTempCurve(self, tempCurve):
        self.powerTempCurve = tempCurve

    def getPowerTempCurveObj(self):
        return self.powerTempCurveObj

    def setPowerTempCurveObj(self, curveObj):
        self.powerTempCurveObj = curveObj

    def getFClass(self):
        return self.FClass

    def setFClass(self, cls):
        self.FClass = cls

    def getVoltageModel(self):
        return self.voltageModel

    def setVoltageModel(self, model):
        self.voltageModel = model

    def getRegisters(self):
        return self.registers

    def setRegisters(self, values):
        self.registers = values

    def getDerivatives(self):
        return self.derivatives

    def setDerivatives(self, values):
        # FIXME Private members in OpenDSS.
        self.derivatives = values

    def getYeq(self):
        return self.Yeq

    def setYeq(self, value):
        self.Yeq = value

    def getYeq95(self):
        return self.Yeq95

    def setYeq95(self, value):
        self.Yeq95 = value

    def getYeq105(self):
        return self.Yeq105

    def setYeq105(self, value):
        self.Yeq105 = value

    def isDebugTrace(self):
        return self.debugTrace

    def setDebugTrace(self, debug):
        self.debugTrace = debug

    def getPVSystemSolutionCount(self):
        return self.PVSystemSolutionCount

    def setPVSystemSolutionCount(self, count):
        self.PVSystemSolutionCount = count

    def getPVSystemFundamental(self):
        return self.PVSystemFundamental

    def setPVSystemFundamental(self, fundamental):
        self.PVSystemFundamental = fundamental

    def isPVSystemObjSwitchOpen(self):
        return self.PVSystemObjSwitchOpen

    def setPVSystemObjSwitchOpen(self, value):
        self.PVSystemObjSwitchOpen = value

    def isFirstSampleAfterReset(self):
        return self.firstSampleAfterReset

    def setFirstSampleAfterReset(self, value):
        self.firstSampleAfterReset = value

    def isPFSpecified(self):
        return self.PFSpecified

    def setPFSpecified(self, specified):
        self.PFSpecified = specified

    def isKVArSpecified(self):
        return self.kVArSpecified

    def setKVArSpecified(self, specified):
        self.kVArSpecified = specified

    def getKVARating(self):
        return self.kVARating

    def setKVArating(self, rating):
        self.kVARating = rating

    def getKVPVSystemBase(self):
        return self.kVPVSystemBase

    def setKVPVSystemBase(self, base):
        self.kVPVSystemBase = base

    def getKVArOut(self):
        return self.kVArOut

    def setKVArOut(self, kvar):
        self.kVArOut = kvar

    def getKWOut(self):
        return self.kWOut

    def setKWOut(self, kW):
        self.kWOut = kW

    def getPanelKW(self):
        return self.panelKW

    def setPanelKW(self, kW):
        self.panelKW = kW

    def getIrradiance(self):
        return self.irradiance

    def setIrradiance(self, value):
        self.irradiance = value

    def getKVArRequested(self):
        return self.kVArRequested

    def setKVArRequested(self, requested):
        self.kVArRequested = requested

    def getTemperature(self):
        return self.temperature

    def setTemperature(self, temp):
        self.temperature = temp

    def getPmpp(self):
        return self.Pmpp

    def setPmpp(self, value):
        self.Pmpp = value

    def getEffFactor(self):
        return self.effFactor

    def setEffFactor(self, factor):
        self.effFactor = factor

    def getTempFactor(self):
        return self.tempFactor

    def setTempFactor(self, factor):
        self.tempFactor = factor

    def isInverterOn(self):
        return self.inverterON

    def setInverterOn(self, on):
        self.inverterON = on

    def getPctCutIn(self):
        return self.pctCutIn

    def setPctCutIn(self, pct):
        self.pctCutIn = pct

    def getPctCutOut(self):
        return self.pctCutOut

    def setPctCutOut(self, pct):
        self.pctCutOut = pct

    def getCutInKW(self):
        return self.cutInKW

    def setCutInKW(self, value):
        self.cutInKW = value

    def getCutOutKW(self):
        return self.cutOutKW

    def setCutOutKW(self, value):
        self.cutOutKW = value

    def getPctR(self):
        return self.pctR

    def setPctR(self, pct):
        self.pctR = pct

    def getPctX(self):
        return self.pctX

    def setPctX(self, pct):
        self.pctX = pct

    def getOpenPVSystemSolutionCount(self):
        return self.openPVSystemSolutionCount

    def setOpenPVSystemSolutionCount(self, count):
        self.openPVSystemSolutionCount = count

    def getPNominalPerPhase(self):
        return self.PNominalPerPhase

    def setPNominalPerPhase(self, value):
        self.PNominalPerPhase = value

    def getQNominalPerPhase(self):
        return self.QNominalPerPhase

    def setQNominalPerPhase(self, value):
        self.QNominalPerPhase = value

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

    def setRegKWh(self, kwh):
        self.regKWh = kwh

    def getRegMaxKVA(self):
        return self.regMaxKVA

    def setRegMaxKVA(self, maxkva):
        self.regMaxKVA = maxkva

    def getRegMaxKW(self):
        return self.regMaxKW

    def setRegMaxKW(self, maxkw):
        self.regMaxKW = maxkw

    def getRegPrice(self):
        return self.regPrice

    def setRegPrice(self, price):
        self.regPrice = price

    def getShapeFactor(self):
        return self.shapeFactor

    def setShapeFactor(self, factor):
        self.shapeFactor = factor

    def getTShapeValue(self):
        return self.TShapeValue

    def setTShapeValue(self, value):
        self.TShapeValue = value

    def getThetaHarm(self):
        return self.thetaHarm

    def setThetaHarm(self, thetaharm):
        self.thetaHarm = thetaharm

    def getTraceFile(self):
        return self.traceFile

    def setTraceFile(self, tracefile):
        self.traceFile = tracefile

    def getUserModel(self):
        return self.userModel

    def setUserModel(self, model):
        self.userModel = model

    def getVArBase(self):
        return self.varBase

    def setVArBase(self, base):
        self.varBase = base

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

    def getVMaxPU(self):
        return self.VMaxPU

    def setVMaxPU(self, vmaxpu):
        self.VMaxPU = vmaxpu

    def getVMinPU(self):
        return self.VMinPU

    def setVMinPU(self, vminpu):
        self.VMinPU = vminpu

    def getVThevHarm(self):
        return self.VThevHarm

    def setVThevHarm(self, vthevharm):
        self.VThevHarm = vthevharm

    def getYPrimOpenCond(self):
        return self.YPrimOpenCond

    def setYPrimOpenCond(self, value):
        self.YPrimOpenCond = value

    def getRThev(self):
        return self.RThev

    def setRThev(self, rthev):
        self.RThev = rthev

    def getXThev(self):
        return self.XThev

    def setXThev(self, xthev):
        self.XThev = xthev
