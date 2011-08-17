from dss.shared.Dynamics import (Dynamics,)
from dss.conversion.Storage import (Storage,)
from dss.common.impl.Utilities import (Utilities,)
from dss.conversion.StorageObj import (StorageObj,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.conversion.impl.StoreUserModelImpl import (StoreUserModelImpl,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class StorageObjImpl(PCElementImpl, StorageObj):
    CDOUBLEONE = Complex(1.0, 1.0)
    cBuffer = [None] * 24
    Yeq = None
    # at nominal
    Yeq95 = None
    # at 95%
    Yeq105 = None
    # at 105%
    YeqIdling = None
    # in shunt representing idle impedance
    debugTrace = None
    state = None
    stateChanged = None
    firstSampleAfterReset = None
    storageSolutionCount = None
    storageFundamental = None
    storageObjSwitchOpen = None
    kVANotSet = None
    kVARating = None
    kVStorageBase = None
    kVArOut = None
    kWOut = None
    kVArRequested = None
    pctIdleKW = None
    pctIdleKVAr = None
    pctChargeEff = None
    pctDischargeEff = None
    chargeEff = None
    dischargeEff = None
    dischargeTrigger = None
    chargeTrigger = None
    chargeTime = None
    pctR = None
    pctX = None
    openStorageSolutionCount = None
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
    thetaHarm = None
    traceFile = None
    userModel = None
    kVArBase = None
    # base vars per phase
    VBase = None
    # base volts suitable for computing currents
    VBase105 = None
    VBase95 = None
    VMaxPU = None
    VMinPU = None
    VThevhH = None
    YPrimOpenCond = None
    RThev = None
    XThev = None
    connection = None
    dailyShape = None
    dailyShapeObj = None
    dutyShape = None
    dutyShapeObj = None
    storageClass = None
    voltageModel = None
    PFNominal = None
    yearlyShape = None
    yearlyShapeObj = None
    kWRating = None
    kWhRating = None
    kWhStored = None
    kWhReserve = None
    pctKWout = None
    pctKVArOut = None
    pctKWIn = None
    pctReserve = None
    dispatchMode = None
    registers = [None] * Storage.NumStorageRegisters
    derivatives = [None] * Storage.NumStorageRegisters

    def __init__(self, parClass, storageName):
        super(StorageObjImpl, self)(parClass)
        self.setName(storageName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # + STORAGE_ELEMENT;  // in both PCElement and StorageElement list
        self.setNPhases(3)
        self.nConds = 4
        # defaults to wye
        self.YOrder = 0
        # to trigger an initial allocation
        self.setNTerms(1)
        # forces allocations
        self.yearlyShape = ''
        self.yearlyShapeObj = None
        # if yearlyShapeObj = null then the load always stays nominal * global multipliers
        self.dailyShape = ''
        self.dailyShapeObj = None
        # if dailyShapeObj = null then the load always stays nominal * global multipliers
        self.dutyShape = ''
        self.dutyShapeObj = None
        # if dutyShapeObj = null then the load always stays nominal * global multipliers
        self.connection = 0
        # Wye (star)
        self.voltageModel = 1
        # Typical fixed kW negative load
        self.storageClass = 1
        self.storageSolutionCount = -1
        # for keep track of the present solution in injCurrent calcs
        self.openStorageSolutionCount = -1
        self.YPrimOpenCond = None
        self.kVStorageBase = 12.47
        self.VBase = 7200.0
        self.VMinPU = 0.9
        self.VMaxPU = 1.1
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.YOrder = self.nTerms * self.nConds
        self.randomMult = 1.0
        # Output rating stuff
        self.kWOut = 25.0
        self.kVArOut = 0.0
        self.PFNominal = 1.0
        self.kWRating = 25.0
        self.kVARating = self.kWRating * 1.0
        self.state = Storage.IDLING
        # idling and fully charged
        self.stateChanged = True
        # force building of YPrim
        self.kWhRating = 50
        self.kWhStored = self.kWhRating
        self.pctReserve = 20.0
        # per cent of kWhRating
        self.kWhReserve = (self.kWhRating * self.pctReserve) / 100.0
        self.pctR = 0.0
        self.pctX = 50.0
        self.pctIdleKW = 1.0
        self.pctIdleKVAr = 0.0
        self.dischargeTrigger = 0.0
        self.chargeTrigger = 0.0
        self.pctChargeEff = 90.0
        self.pctDischargeEff = 90.0
        self.pctKWout = 100.0
        self.pctKVArOut = 100.0
        self.pctKWIn = 100.0
        self.chargeTime = 2.0
        # 2 AM
        self.kVANotSet = True
        # flag to set the default value for kVA
        self.userModel = StoreUserModelImpl()
        self.regKWh = 1
        self.regKVArh = 2
        self.regMaxKW = 3
        self.regMaxKVA = 4
        self.regHours = 5
        self.regPrice = 6
        self.debugTrace = False
        self.storageObjSwitchOpen = False
        self.spectrum = ''
        # override base class
        self.spectrumObj = None
        self.initPropertyValues(0)
        self.recalcElementData()

    def decodeState(self):
        _0 = self.state
        _1 = False
        while True:
            if _0 == Storage.CHARGING:
                _1 = True
                return 'CHARGING'
            if (_1 is True) or (_0 == Storage.DISCHARGING):
                _1 = True
                return 'DISCHARGING'
            if True:
                _1 = True
                return 'IDLING'
            break

    def initPropertyValues(self, arrayOffset):
        """Define default values for the properties."""
        self.propertyValue[0] = '3'
        # "phases";
        self.propertyValue[1] = self.getBus(0)
        # "bus1";
        self.propertyValue[Storage.KV] = String.format.format('%-g', self.kVStorageBase)
        self.propertyValue[Storage.KW] = String.format.format('%-g', self.kWOut)
        self.propertyValue[Storage.PF] = String.format.format('%-g', self.PFNominal)
        self.propertyValue[Storage.MODEL] = '1'
        self.propertyValue[Storage.YEARLY] = ''
        self.propertyValue[Storage.DAILY] = ''
        self.propertyValue[Storage.DUTY] = ''
        self.propertyValue[Storage.DISP_MODE] = 'Default'
        self.propertyValue[Storage.IDLE_KVAR] = '0'
        self.propertyValue[Storage.CONNECTION] = 'wye'
        self.propertyValue[Storage.KVAR] = String.format.format('%-g', self.getPresentKVAr())
        self.propertyValue[Storage.PCTR] = String.format.format('%-g', self.pctR)
        self.propertyValue[Storage.PCTX] = String.format.format('%-g', self.pctX)
        self.propertyValue[Storage.IDLE_KW] = '1'
        # percent
        self.propertyValue[Storage.CLASS] = '1'
        # "class"
        self.propertyValue[Storage.DISP_OUT_TRIG] = '0'
        # 0 - no trigger level
        self.propertyValue[Storage.DISP_IN_TRIG] = '0'
        self.propertyValue[Storage.CHARGE_EFF] = '90'
        self.propertyValue[Storage.DISCHARGE_EFF] = '90'
        self.propertyValue[Storage.PCT_KW_OUT] = '100'
        self.propertyValue[Storage.PCT_KW_IN] = '100'
        self.propertyValue[Storage.VMIN_PU] = '0.90'
        self.propertyValue[Storage.VMAX_PU] = '1.10'
        self.propertyValue[Storage.STATE] = 'IDLING'
        self.propertyValue[Storage.KVA] = String.format.format('%-g', self.kVARating)
        self.propertyValue[Storage.KW_RATED] = String.format.format('%-g', self.kWRating)
        self.propertyValue[Storage.KWH_RATED] = String.format.format('%-g', self.kWhRating)
        self.propertyValue[Storage.KWH_STORED] = String.format.format('%-g', self.kWhStored)
        self.propertyValue[Storage.PCT_STORED] = String.format.format('%-g', (self.kWhStored / self.kWhRating) * 100.0)
        self.propertyValue[Storage.PCT_RESERVE] = String.format.format('%-g', self.pctReserve)
        self.propertyValue[Storage.CHARGE_TIME] = String.format.format('%-g', self.chargeTime)
        self.propertyValue[Storage.USER_MODEL] = ''
        # UserModel
        self.propertyValue[Storage.USER_DATA] = ''
        # UserData
        self.propertyValue[Storage.DEBUG_TRACE] = 'NO'
        super(StorageObjImpl, self).initPropertyValues(Storage.NumPropsThisClass)

    def returnDispMode(self, imode):
        _0 = imode
        _1 = False
        while True:
            if _0 == Storage.EXTERNAL_MODE:
                _1 = True
                return 'External'
            if (_1 is True) or (_0 == Storage.FOLLOW):
                _1 = True
                return 'Follow'
            if (_1 is True) or (_0 == Storage.LOAD_MODE):
                _1 = True
                return 'Loadshape'
            if (_1 is True) or (_0 == Storage.PRICE_MODE):
                _1 = True
                return 'Price'
            if True:
                _1 = True
                return 'default'
            break

    def getPropertyValue(self, index):
        _0 = index
        _1 = False
        while True:
            if _0 == Storage.KV:
                _1 = True
                return String.format.format('%.6g', self.kVStorageBase)
            if (_1 is True) or (_0 == Storage.KW):
                _1 = True
                return String.format.format('%.6g', self.kWOut)
            if (_1 is True) or (_0 == Storage.PF):
                _1 = True
                return String.format.format('%.6g', self.PFNominal)
            if (_1 is True) or (_0 == Storage.MODEL):
                _1 = True
                return String.format.format('%d', self.voltageModel)
            if (_1 is True) or (_0 == Storage.YEARLY):
                _1 = True
                return self.yearlyShape
            if (_1 is True) or (_0 == Storage.DAILY):
                _1 = True
                return self.dailyShape
            if (_1 is True) or (_0 == Storage.DUTY):
                _1 = True
                return self.dutyShape
            if (_1 is True) or (_0 == Storage.DISP_MODE):
                _1 = True
                return self.returnDispMode(self.dispatchMode)
            if (_1 is True) or (_0 == Storage.IDLE_KVAR):
                _1 = True
                return String.format.format('%.6g', self.pctIdleKVAr)
                # case Storage.propCONNECTION:;
            if (_1 is True) or (_0 == Storage.KVAR):
                _1 = True
                return String.format.format('%.6g', self.kVArOut)
            if (_1 is True) or (_0 == Storage.PCTR):
                _1 = True
                return String.format.format('%.6g', self.pctR)
            if (_1 is True) or (_0 == Storage.PCTX):
                _1 = True
                return String.format.format('%.6g', self.pctX)
            if (_1 is True) or (_0 == Storage.IDLE_KW):
                _1 = True
                return String.format.format('%.6g', self.pctIdleKW)
                # case Storage.propCLASS      = 17;
            if (_1 is True) or (_0 == Storage.DISP_OUT_TRIG):
                _1 = True
                return String.format.format('%.6g', self.dischargeTrigger)
            if (_1 is True) or (_0 == Storage.DISP_IN_TRIG):
                _1 = True
                return String.format.format('%.6g', self.chargeTrigger)
            if (_1 is True) or (_0 == Storage.CHARGE_EFF):
                _1 = True
                return String.format.format('%.6g', self.pctChargeEff)
            if (_1 is True) or (_0 == Storage.DISCHARGE_EFF):
                _1 = True
                return String.format.format('%.6g', self.pctDischargeEff)
            if (_1 is True) or (_0 == Storage.PCT_KW_OUT):
                _1 = True
                return String.format.format('%.6g', self.pctKWout)
            if (_1 is True) or (_0 == Storage.VMIN_PU):
                _1 = True
                return String.format.format('%.6g', self.VMinPU)
            if (_1 is True) or (_0 == Storage.VMAX_PU):
                _1 = True
                return String.format.format('%.6g', self.VMaxPU)
            if (_1 is True) or (_0 == Storage.STATE):
                _1 = True
                return self.decodeState()
            if (_1 is True) or (_0 == Storage.KVA):
                _1 = True
                return String.format.format('%.6g', self.kVARating)
            if (_1 is True) or (_0 == Storage.KW_RATED):
                _1 = True
                return String.format.format('%.6g', self.kWRating)
            if (_1 is True) or (_0 == Storage.KWH_RATED):
                _1 = True
                return String.format.format('%.6g', self.kWhRating)
            if (_1 is True) or (_0 == Storage.KWH_STORED):
                _1 = True
                return String.format.format('%.6g', self.kWhStored)
            if (_1 is True) or (_0 == Storage.PCT_RESERVE):
                _1 = True
                return String.format.format('%.6g', self.pctReserve)
            if (_1 is True) or (_0 == Storage.USER_MODEL):
                _1 = True
                return self.userModel.getName()
            if (_1 is True) or (_0 == Storage.USER_DATA):
                _1 = True
                return '(' + super(StorageObjImpl, self).getPropertyValue(index) + ')'
                # case Storage.propDEBUGTRACE = 33;
            if (_1 is True) or (_0 == Storage.PCT_KW_IN):
                _1 = True
                return String.format.format('%.6g', self.pctKWIn)
            if (_1 is True) or (_0 == Storage.PCT_STORED):
                _1 = True
                return String.format.format('%.6g', (self.kWhStored / self.kWhRating) * 100.0)
            if (_1 is True) or (_0 == Storage.CHARGE_TIME):
                _1 = True
                return String.format.format('%.6g', self.chargeTime)
            if True:
                _1 = True
                return super(StorageObjImpl, self).getPropertyValue(index)
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
        self.checkStateTriggerLevel(self.shapeFactor.getReal())
        # last recourse

    def CalcDutyMult(self, hr):
        if self.dutyShapeObj is not None:
            self.shapeFactor = self.dutyShapeObj.getMult(hr)
            self.checkStateTriggerLevel(self.shapeFactor.getReal())
        else:
            self.calcDailyMult(hr)
            # default to daily mult if no duty curve specified

    def calcYearlyMult(self, hr):
        if self.yearlyShapeObj is not None:
            self.shapeFactor = self.yearlyShapeObj.getMult(hr)
            self.checkStateTriggerLevel(self.shapeFactor.getReal())
        else:
            self.calcDailyMult(hr)
            # defaults to daily curve

    def setKWAndKVArOut(self):
        _0 = self.state
        _1 = False
        while True:
            if _0 == Storage.CHARGING:
                _1 = True
                if self.kWhStored < self.kWhRating:
                    _2 = self.dispatchMode
                    _3 = False
                    while True:
                        if _2 == Storage.FOLLOW:
                            _3 = True
                            self.kWOut = self.kWRating * self.shapeFactor.getReal()
                            self.kVArOut = self.kVArBase * self.shapeFactor.getImaginary()
                            # ???
                        if True:
                            _3 = True
                            self.kWOut = (-self.kWRating * self.pctKWIn) / 100.0
                            if self.PFNominal == 1.0:
                                self.kVArOut = 0.0
                            else:
                                self.syncUpPowerQuantities()
                                # computes kvar_out from PF
                        break
                else:
                    self.setState(Storage.IDLING)
                    # all charged up
                break
            if (_1 is True) or (_0 == Storage.DISCHARGING):
                _1 = True
                if self.kWhStored > self.kWhReserve:
                    _4 = self.dispatchMode
                    _5 = False
                    while True:
                        if _4 == Storage.FOLLOW:
                            _5 = True
                            self.kWOut = self.kWRating * self.shapeFactor.getReal()
                            self.kVArOut = self.kVArBase * self.shapeFactor.getImaginary()
                        if True:
                            _5 = True
                            self.kWOut = (self.kWRating * self.pctKWout) / 100.0
                            if self.PFNominal == 1.0:
                                self.kVArOut = 0.0
                            else:
                                self.syncUpPowerQuantities()
                                # computes kvar_out from PF
                        break
                else:
                    self.setState(Storage.IDLING)
                    # not enough storage to discharge
                break
            break

    def setNominalStorageOuput(self):
        ckt = DSSGlobals.activeCircuit
        sol = ckt.getSolution()
        self.shapeFactor = self.CDOUBLEONE
        # init here; changed by curve routine
        # check to make sure the storage element is on
        if not (sol.isDynamicModel() or sol.isHarmonicModel()):
            # leave storage element in whatever state it was prior to entering dynamic mode
            # check dispatch to see what state the storage element should be in
            _0 = self.dispatchMode
            _1 = False
            while True:
                if _0 == Storage.EXTERNAL_MODE:
                    _1 = True
                    break
                if (_1 is True) or (_0 == Storage.LOAD_MODE):
                    _1 = True
                    self.checkStateTriggerLevel(ckt.getGeneratorDispatchReference())
                    break
                if (_1 is True) or (_0 == Storage.PRICE_MODE):
                    _1 = True
                    self.checkStateTriggerLevel(ckt.getPriceSignal())
                    break
                if True:
                    _1 = True
                    _2 = sol.getMode()
                    _3 = False
                    while True:
                        if _2 == Dynamics.SNAPSHOT:
                            _3 = True
                            break
                        if (_3 is True) or (_2 == Dynamics.DAILYMODE):
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            # daily dispatch curve
                            break
                        if (_3 is True) or (_2 == Dynamics.YEARLYMODE):
                            _3 = True
                            self.calcYearlyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.MONTECARLO1):
                            _3 = True
                            break
                        if (_3 is True) or (_2 == Dynamics.MONTEFAULT):
                            _3 = True
                            break
                        if (_3 is True) or (_2 == Dynamics.FAULTSTUDY):
                            _3 = True
                            break
                        if (_3 is True) or (_2 == Dynamics.DYNAMICMODE):
                            _3 = True
                            break
                            # assume daily curve, if any, for the following
                        if (_3 is True) or (_2 == Dynamics.MONTECARLO2):
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.MONTECARLO3):
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.LOADDURATION1):
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.LOADDURATION2):
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.PEAKDAY):
                            _3 = True
                            self.calcDailyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.DUTYCYCLE):
                            _3 = True
                            self.CalcDutyMult(sol.getDblHour())
                            break
                        if (_3 is True) or (_2 == Dynamics.AUTOADDFLAG):
                            _3 = True
                            break
                        break
                    break
                break
            self.setKWAndKVArOut()
            # based on state and amount of energy left in storage
            if self.state == Storage.IDLING:
                # YeqIdle will be in the Yprim matrix so set this to zero
                self.PNominalPerPhase = 0.0
                # -0.1 * kWRating / nPhases;  // watts
                if self.dispatchMode == Storage.EXTERNAL_MODE:
                    # check for requested kVAr
                    self.QNominalPerPhase = (self.kVArRequested / self.nPhases) * 1000.0
                else:
                    self.QNominalPerPhase = 0.0
                self.Yeq = ComplexUtil.divide(Complex(self.PNominalPerPhase, -self.QNominalPerPhase), self.Math.pow(self.VBase, 2))
                # VBase must be L-N for 3-phase
                self.Yeq95 = self.Yeq
                self.Yeq105 = self.Yeq
            else:
                self.PNominalPerPhase = (1000.0 * self.kWOut) / self.nPhases
                self.QNominalPerPhase = (1000.0 * self.kVArOut) / self.nPhases
                _4 = self.voltageModel
                _5 = False
                while True:
                    if _4 == 3:
                        _5 = True
                        break
                    if True:
                        _5 = True
                        self.Yeq = ComplexUtil.divide(Complex(self.PNominalPerPhase, -self.QNominalPerPhase), self.Math.pow(self.VBase, 2))
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
                # Fix this when user model gets connected in
            # When we leave here, all the Yeq's are in L-N values
        # if storage element state changes, force re-calc of Y matrix
        if self.stateChanged:
            self.setYPrimInvalid(True)
            self.stateChanged = False
            # reset the flag

    def recalcElementData(self):
        self.VBase95 = self.VMinPU * self.VBase
        self.VBase105 = self.VMaxPU * self.VBase
        self.kVArBase = (1000.0 * self.kVArOut) / self.nPhases
        # remember this for follow mode
        # values in ohms for Thevenin equivalents
        self.RThev = ((self.pctR * 0.01 * self.Math.pow(self.getPresentKV(), 2)) / self.kVARating) * 1000.0
        self.XThev = ((self.pctX * 0.01 * self.Math.pow(self.getPresentKV(), 2)) / self.kVARating) * 1000.0
        # efficiencies
        self.chargeEff = self.pctChargeEff * 0.01
        self.dischargeEff = self.pctDischargeEff * 0.01
        self.YeqIdling = Complex(self.pctIdleKW, self.pctIdleKVAr).multiply((self.kWRating * 10.0) / self.Math.pow(self.VBase, 2) / self.nPhases)
        # 10.0 = 1000/100 = kW->W/pct
        self.setNominalStorageOuput()
        # Now check for errors. If any of these came out nil and the string was not nil, give warning
        if self.yearlyShapeObj is None:
            if len(self.yearlyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Yearly load shape: \"' + self.yearlyShape + '\" not found.', 563)
        if self.dailyShapeObj is None:
            if len(self.dailyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Daily load shape: \"' + self.dailyShape + '\" not found.', 564)
        if self.dutyShapeObj is None:
            if len(self.dutyShape) > 0:
                DSSGlobals.doSimpleMsg('Warning: Duty load shape: \"' + self.dutyShape + '\" not found.', 565)
        if len(self.getSpectrum()) > 0:
            self.setSpectrumObj(DSSGlobals.spectrumClass.find(self.getSpectrum()))
            if self.getSpectrumObj() is None:
                DSSGlobals.doSimpleMsg('Error: Spectrum \"' + self.getSpectrum() + '\" not found.', 566)
        else:
            self.setSpectrumObj(None)
        # initialize to zero - defaults to PQ storage element
        # solution object will reset after circuit modifications
        self.setInjCurrent(Utilities.resizeArray(self.getInjCurrent(), self.YOrder))
        # Update any user-written models
        if self.userModel.exists():
            self.userModel.updateModel()

    def calcYPrimMatrix(self, YMatrix):
        sol = DSSGlobals.activeCircuit.getSolution()
        self.YPrimFreq = sol.getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        if sol.isHarmonicModel():
            # Yeq is computed from %R and %X -- inverse of Rthev + j Xthev
            # sol.isIsDynamicModel() ||
            _0 = self.state
            _1 = False
            while True:
                if _0 == Storage.IDLING:
                    _1 = True
                    Y = self.YeqIdling
                    break
                if (_1 is True) or (_0 == Storage.DISCHARGING):
                    _1 = True
                    Y = self.Yeq.negate().add(self.YeqIdling)
                    break
                if True:
                    _1 = True
                    Y = self.Yeq
                    # L-N value computed in initialization routines
                    break
                break
            if self.connection == 1:
                Y = ComplexUtil.divide(Y, 3.0)
                # convert to delta impedance
            Y = Complex(Y.getReal(), Y.getImaginary() / freqMultiplier)
            Yij = Y.negate()
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                _3 = self.connection
                _4 = False
                while True:
                    if _3 == 0:
                        _4 = True
                        YMatrix.setElement(i, i, Y)
                        YMatrix.addElement(self.nConds, self.nConds, Y)
                        YMatrix.setElemSym(i, self.nConds, Yij)
                        break
                    if (_4 is True) or (_3 == 1):
                        _4 = True
                        YMatrix.setElement(i, i, Y)
                        YMatrix.addElement(i, i, Y)
                        # put it in again
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
                            YMatrix.setElemSym(i, j, Yij)
                        break
                    break
        else:
            # regular power flow storage element model
            # Yeq is always expected as the equivalent line-neutral admittance
            _6 = self.state
            _7 = False
            while True:
                if _6 == Storage.IDLING:
                    _7 = True
                    Y = self.YeqIdling
                    break
                if True:
                    _7 = True
                    Y = self.Yeq.negate().add(self.YeqIdling)
                    # negate for generation; Yeq is L-N quantity
                    break
                break
            # ****** need to modify the base admittance for real harmonics calcs
            Y = Complex(Y.getReal(), Y.getImaginary() / freqMultiplier)
            _8 = self.connection
            _9 = False
            while True:
                if _8 == 0:
                    _9 = True
                    Yij = Y.negate()
                    _10 = True
                    i = 0
                    while True:
                        if _10 is True:
                            _10 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        YMatrix.setElement(i, i, Y)
                        YMatrix.addElement(self.nConds, self.nConds, Y)
                        YMatrix.setElemSym(i, self.nConds, Yij)
                    break
                if (_9 is True) or (_8 == 1):
                    _9 = True
                    Y = ComplexUtil.divide(Y, 3.0)
                    # convert to delta impedance
                    Yij = Y.negate()
                    _11 = True
                    i = 0
                    while True:
                        if _11 is True:
                            _11 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        j = i + 1
                        if j >= self.nConds:
                            j = 0
                            # wrap around for closed connections
                        YMatrix.addElement(i, i, Y)
                        YMatrix.addElement(j, j, Y)
                        YMatrix.addElemSym(i, j, Yij)
                    break
                break

    def normalizeToTOD(self, h, sec):
        """Normalize time to a floating point number representing time of day if Hour > 24
        time should be 0 to 24.
        """
        if h > 23:
            hourOfDay = h - ((h / 24) * 24)
        else:
            hourOfDay = h
        Result = hourOfDay + (sec / 3600.0)
        if Result > 24.0:
            Result = Result - 24.0
            # wrap around
        return Result

    def checkStateTriggerLevel(self, level):
        """This is where we set the state of the storage element."""
        self.stateChanged = False
        oldState = self.state
        if self.dispatchMode == Storage.FOLLOW:
            # set charge and discharge modes based on sign of load shape
            if level > 0.0 and self.kWhStored > self.kWhReserve:
                self.setState(Storage.DISCHARGING)
            elif level < 0.0 and self.kWhStored < self.kWhRating:
                self.setState(Storage.CHARGING)
            else:
                self.setState(Storage.IDLING)
        else:
            # all other dispatch modes, just compare to trigger value
            if self.chargeTrigger == 0.0 and self.dischargeTrigger == 0.0:
                return
                # first see If we want to turn off charging or discharging state
            _0 = self.getState()
            _1 = False
            while True:
                if _0 == Storage.CHARGING:
                    _1 = True
                    if self.chargeTrigger != 0.0:
                        if (self.chargeTrigger < level) or (self.kWhStored >= self.kWhRating):
                            self.setState(Storage.IDLING)
                    break
                if (_1 is True) or (_0 == Storage.DISCHARGING):
                    _1 = True
                    if self.dischargeTrigger != 0.0:
                        if (self.dischargeTrigger > level) or (self.kWhStored <= self.kWhReserve):
                            self.setState(Storage.IDLING)
                    break
                break
            # now check to see if we want to turn on the opposite state
            _2 = self.getState()
            _3 = False
            while True:
                if _2 == Storage.IDLING:
                    _3 = True
                    if (
                        self.dischargeTrigger != 0.0 and self.dischargeTrigger < level and self.kWhStored > self.kWhReserve
                    ):
                        self.setState(Storage.DISCHARGING)
                    elif (
                        self.chargeTrigger != 0.0 and self.chargeTrigger > level and self.kWhStored < self.kWhRating
                    ):
                        self.setState(Storage.CHARGING)
                    # check to see if it is time to turn the charge cycle on If it is not already on
                    if not (self.getState() == Storage.CHARGING):
                        if self.chargeTime > 0.0:
                            sol = DSSGlobals.activeCircuit.getSolution()
                            if (
                                self.Math.abs(self.normalizeToTOD(sol.getIntHour(), sol.getDynaVars().t) - self.chargeTime) < sol.getDynaVars().h / 3600.0
                            ):
                                self.setState(Storage.CHARGING)
                    break
                break
        if oldState != self.state:
            self.stateChanged = True

    def calcYPrim(self):
        # build only shunt Yprim
        # build a dummy Yprim_Series so that calcV does not fail
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
        self.setNominalStorageOuput()
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
        super(StorageObjImpl, self).calcYPrim()

    def putCurrInTerminalArray(self, termArray, curr, i):
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
                termArray[j] = termArray[j].add(curr.negate())
                break
            break

    def writeTraceRecord(self, s):
        ckt = DSSGlobals.activeCircuit
        try:
            if not DSSGlobals.inShowResults:
                fw = self.FileWriter(self.traceFile, True)
                bw = self.BufferedWriter(fw)
                bw.write(String.format.format('%-.g, %d, %-.g, ', ckt.getSolution().getDynaVars().t, ckt.getSolution().getIteration(), ckt.getLoadMultiplier()) + Utilities.getSolutionModeID() + ', ' + Utilities.getLoadModel() + ', ' + self.voltageModel + ', ' + ((self.QNominalPerPhase * 3.0) / 1000000.0) + ', ' + ((self.PNominalPerPhase * 3.0) / 1000000.0) + ', ' + s + ', ')
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
                    # TraceBuffer.write(VThevMag + ", " + StoreVARs.Theta * 180.0 / Math.PI);
                bw.newLine()
                bw.close()
                fw.close()
        except Exception, e:
            pass # astStmt: [Stmt([]), None]

    def doConstantPQStorageObj(self):
        """Compute total terminal current for Constant PQ."""
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
            self.putCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.putCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doConstantZStorageObj(self):
        """Constant Z model."""
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
            if not (i < self.nPhases):
                break
            curr = Yeq2.multiply(self.VTerminal[i])
            # Yeq is always line to neutral
            self.putCurrInTerminalArray(self.getITerminal(), curr.negate(), i)
            # put into terminal array taking into account connection
            self.setITerminalUpdated(True)
            self.putCurrInTerminalArray(self.getInjCurrent(), curr, i)
            # put into terminal array taking into account connection

    def doUserModel(self):
        """Compute total terminal current from user-written model."""
        self.calcYPrimContribution(self.getInjCurrent())
        # init injCurrent array
        if self.userModel.exists():
            # check automatically selects the user model if true
            self.userModel.calc(self.VTerminal, self.ITerminal)
            self.setITerminalUpdated(True)
            # SolutionObj sol = DSSGlobals.activeCircuit.getSolution();
            # negate currents from user model for power flow storage element model
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
            DSSGlobals.doSimpleMsg('Storage.' + self.getName() + ' model designated to use user-written model, but user-written model is not defined.', 567)

    def doDynamicMode(self):
        """Compute total current and add into injTemp.

        For now, just assume the storage element is constant power
        for the duration of the dynamic simulation.
        """
        self.doConstantPQStorageObj()

    def doHarmonicMode(self):
        """Compute injection current only when in harmonics mode.

        Assumes spectrum is a voltage source behind subtransient reactance and YPrim has been built
        Vd is the fundamental frequency voltage behind Xd" for phase 1.
        """
        self.computeVTerminal()
        sol = DSSGlobals.activeCircuit.getSolution()
        storageHarmonic = sol.getFrequency() / self.storageFundamental
        if self.getSpectrumObj() is not None:
            e = self.getSpectrumObj().getMult(storageHarmonic).multiply(self.VThevhH)
            # get base harmonic magnitude
        else:
            e = Complex.ZERO
        e = Utilities.rotatePhasorRad(e, storageHarmonic, self.thetaHarm)
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
                e = Utilities.rotatePhasorDeg(e, storageHarmonic, -120.0)
                # assume 3-phase Storage element
        # Handle wye connection
        if self.connection == 0:
            self.cBuffer[self.nConds] = self.VTerminal[self.nConds]
            # assume no neutral injection voltage
            # Inj currents = Yprim (E)
        self.YPrim.vMult(self.getInjCurrent(), self.cBuffer)

    def calcVTerminalPhase(self):
        # /**
        # * Put terminal voltages in an array.
        # //
        # private void calcVterminal() {
        # computeVterminal();
        # StorageSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount();
        # }
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
                    if j >= self.nConds:
                        j = 0
                    self.VTerminal[i] = sol.vDiff(self.nodeRef[i], self.nodeRef[j])
                break
            break
        self.storageSolutionCount = sol.getSolutionCount()

    def calcStorageModelContribution(self):
        """Calculates storage element current and adds it properly into the injCurrent array
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
            _0 = self.voltageModel
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.doConstantPQStorageObj()
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    self.doConstantZStorageObj()
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    self.doUserModel()
                    break
                if True:
                    _1 = True
                    self.doConstantPQStorageObj()
                    # for now, until we implement the other models.
                    break
                break
        # When this is done, ITerminal is up to date

    def calcInjCurrentArray(self):
        """Difference between currents in YPrim and total current."""
        # now get injection currents
        if self.storageObjSwitchOpen:
            self.zeroInjCurrent()
        else:
            self.calcStorageModelContribution()

    def getTerminalCurrents(self, Curr):
        """Compute total currents."""
        sol = DSSGlobals.activeCircuit.getSolution()
        if self.ITerminalSolutionCount != sol.getSolutionCount():
            # recalc the contribution
            if not self.storageObjSwitchOpen:
                self.calcStorageModelContribution()
                # adds totals in ITerminal as a side effect
            super(StorageObjImpl, self).getTerminalCurrents(Curr)
        if self.debugTrace:
            self.writeTraceRecord('TotalCurrent')

    def injCurrents(self):
        sol = DSSGlobals.activeCircuit.getSolution()
        if sol.loadsNeedUpdating():
            self.setNominalStorageOuput()
            # set the nominal kW, etc for the type of solution being done
        self.calcInjCurrentArray()
        # difference between currents in YPrim and total terminal current
        if self.debugTrace:
            self.writeTraceRecord('Injection')
            # add into system injection current array
        return super(StorageObjImpl, self).injCurrents()

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
            DSSGlobals.doErrorMsg('Storage object: \"' + self.getName() + '\" in getInjCurrents method.', e.getMessage(), 'Current buffer not big enough.', 568)

    def resetRegisters(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < Storage.NumStorageRegisters):
                break
            self.registers[i] = 0.0
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < Storage.NumStorageRegisters):
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
        """Update energy from metered zone."""
        # FIXME Private method in OpenDSS
        ckt = DSSGlobals.activeCircuit
        # compute energy in storage element branch
        if self.isEnabled():
            # only tabulate discharge hours
            if self.state == Storage.DISCHARGING:
                S = Complex(self.getPresentKW(), self.getPresentKVAr())
                SMag = S.abs()
                hourValue = 1.0
            else:
                S = Complex.ZERO
                SMag = 0.0
                hourValue = 0.0
            if (self.state == Storage.DISCHARGING) or ckt.isTrapezoidalIntegration():
                # Make sure we always integrate for Trapezoidal case.
                # Don't need to for gen off and normal integration.

                sol = ckt.getSolution()
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

    def updateStorage(self):
        """Update storage elements based on present kW and intervalHrs variable."""
        sol = DSSGlobals.activeCircuit.getSolution()
        _0 = self.state
        _1 = False
        while True:
            if _0 == Storage.DISCHARGING:
                _1 = True
                self.kWhStored = self.kWhStored - ((self.getPresentKW() * sol.getIntervalHrs()) / self.dischargeEff)
                if self.kWhStored < self.kWhReserve:
                    self.kWhStored = self.kWhReserve
                    self.setState(Storage.IDLING)
                    # it's empty turn it off
                    self.stateChanged = True
                break
            if (_1 is True) or (_0 == Storage.CHARGING):
                _1 = True
                self.kWhStored = self.kWhStored - (self.getPresentKW() * sol.getIntervalHrs() * self.chargeEff)
                if self.kWhStored > self.kWhRating:
                    self.kWhStored = self.kWhRating
                    self.setState(Storage.IDLING)
                    # it's full turn it off
                    self.stateChanged = True
                break
            break

    def getPresentKW(self):
        return self.kWOut
        # PNominalPerPhase * 0.001 * nPhases;

    def getKWChargeLosses(self):
        _0 = self.getState()
        _1 = False
        while True:
            if _0 == Storage.CHARGING:
                _1 = True
                return self.Math.abs((self.getPower(0).getReal() * (100.0 - self.pctChargeEff)) / 100000.0)
                # kW
            if (_1 is True) or (_0 == Storage.IDLING):
                _1 = True
                return self.getKWIdlingLosses()
            if (_1 is True) or (_0 == Storage.DISCHARGING):
                _1 = True
                return self.Math.abs((self.getPower(0).getReal() * (100.0 - self.pctDischargeEff)) / 100000.0)
                # kW
            break
        return 0

    def getKWIdlingLosses(self):
        return (self.pctIdleKW * self.kWRating) / 100.0

    def getPresentKV(self):
        return self.kVStorageBase

    def getPresentKVAr(self):
        return self.kVArOut
        # QNominalPerPhase * 0.001 * nPhases;

    def dumpProperties(self, f, complete):
        super(StorageObjImpl, self).dumpProperties(f, complete)
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
                if _1 == Storage.USER_DATA:
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
        """This method makes a Thevenin equivalent behis the reactance spec'd in %R and %X"""
        Va = None
        sol = DSSGlobals.activeCircuit.getSolution()
        self.setYPrimInvalid(True)
        # force rebuild of YPrims
        self.storageFundamental = sol.getFrequency()
        # whatever the frequency is when we enter here
        self.Yeq = ComplexUtil.invert(Complex(self.RThev, self.XThev))
        # used for current calcs; always L-N
        # Compute reference Thevinen voltage from phase 1 current
        if self.state == Storage.DISCHARGING:
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
            self.VThevhH = e.abs()
            # establish base mag and angle
            self.thetaHarm = e.getArgument()
        else:
            self.VThevhH = 0.0
            self.thetaHarm = 0.0

    def initStateVars(self):
        """For going into dynamics mode."""
        self.setYPrimInvalid(True)
        # force rebuild of YPrims

    def integrateStates(self):
        """Dynamics mode integration routine."""
        # FIXME Private method in OpenDSS
        pass

    def interpretState(self, s):
        _0 = s.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'c':
                _1 = True
                return Storage.CHARGING
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                return Storage.DISCHARGING
            if True:
                _1 = True
                return Storage.IDLING
            break

    def getVariable(self, i):
        """Return variables one at a time."""
        if i < 0:
            return -9999.99
            # for now, report kWh stored and mode
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return self.kWhStored
            if (_1 is True) or (_0 == 1):
                _1 = True
                return self.state
            if (_1 is True) or (_0 == 2):
                _1 = True
                return self.pctKWout
            if (_1 is True) or (_0 == 3):
                _1 = True
                return self.pctKWIn
            if True:
                _1 = True
                if self.userModel.exists():
                    n = self.userModel.numVars()
                    k = i - Storage.NumStorageVariables
                    if k <= n:
                        return self.userModel.getVariable(k)
                break
            break
        return -9999.99

    def setVariable(self, i, value):
        if i < 0:
            return
            # no variables to set
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                self.kWhStored = value
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                self.setState(value)
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                self.setPctKWOut(value)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.pctKWIn = value
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                break
            if True:
                _1 = True
                if self.userModel.exists():
                    n = self.userModel.numVars()
                    k = i - Storage.NumStorageVariables
                    if k <= n:
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
            if not (i < Storage.NumStorageVariables):
                break
            states[i] = self.getVariable(i)
        if self.userModel.exists():
            self.userModel.getAllVars(states[Storage.NumStorageVariables])

    def numVariables(self):
        result = Storage.NumStorageVariables
        if self.userModel.exists():
            result = result + self.userModel.numVars()
        return result

    def variableName(self, i):
        buffSize = 255
        # char[] Buff = new char[BuffSize];
        if i < 0:
            return None
        _0 = i
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                return 'kWh Stored'
            if (_1 is True) or (_0 == 1):
                _1 = True
                return 'Storage State Flag'
            if (_1 is True) or (_0 == 2):
                _1 = True
                return 'kW Discharging'
            if (_1 is True) or (_0 == 3):
                _1 = True
                return 'kW Charging'
            if (_1 is True) or (_0 == 4):
                _1 = True
                return 'kW Losses'
            if (_1 is True) or (_0 == 5):
                _1 = True
                return 'kW Idling Losses'
            if True:
                _1 = True
                if self.userModel.exists():
                    pName = 0
                    n = self.userModel.numVars()
                    i2 = i - Storage.NumStorageVariables
                    if i2 <= n:
                        self.userModel.getVarName(i2, pName, buffSize)
                        return String.valueOf.valueOf(pName)
                break
            break
        return None

    def makePosSequence(self):
        """Make a positive sequence model."""
        s = 'phases=1 conn=wye'
        # make sure voltage is line-neutral
        if (self.nPhases > 1) or (self.connection != 0):
            V = self.kVStorageBase / DSSGlobals.SQRT3
        else:
            V = self.kVStorageBase
        s = s + String.format.format(' kV=%-.5g', V)
        if self.nPhases > 1:
            s = s + String.format.format(' kWrating=%-.5g  PF=%-.5g', self.kWRating / self.nPhases, self.PFNominal)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(StorageObjImpl, self).makePosSequence()
        # write out other properties

    def setConductorClosed(self, index, value):
        super(StorageObjImpl, self).setConductorClosed(index, value)
        # just turn storage element on or off
        if value:
            self.storageObjSwitchOpen = False
        else:
            self.storageObjSwitchOpen = True

    def setPctKVArOut(self, value):
        self.pctKVArOut = value
        # force recompute of target PF and requested kVAr
        self.setPresentKVAr((self.kWRating * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0) * self.pctKVArOut) / 100.0)

    def setPctKWOut(self, value):
        self.pctKWout = value
        self.kWOut = (self.pctKWout * self.kWRating) / 100.0

    def setPowerFactor(self, value):
        self.PFNominal = value
        self.syncUpPowerQuantities()

    def setPresentKV(self, value):
        self.kVStorageBase = value
        _0 = self.nPhases
        _1 = False
        while True:
            if _0 == 2:
                _1 = True
                self.VBase = self.kVStorageBase * DSSGlobals.InvSQRT3x1000
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                self.VBase = self.kVStorageBase * DSSGlobals.InvSQRT3x1000
                break
            if True:
                _1 = True
                self.VBase = self.kVStorageBase * 1000.0
                break
            break

    def setPresentKVAr(self, value):
        """Set the kvar to requested value within rating of inverter"""
        self.kVArOut = value
        self.kVArRequested = value
        # Requested kVA output
        kVA_Gen = self.Math.sqrt(self.Math.pow(self.kWOut, 2) + self.Math.pow(self.kVArOut, 2))
        if kVA_Gen > self.kVARating:
            kVA_Gen = self.kVARating
            # limit kVA to rated value
        if kVA_Gen != 0.0:
            self.setPowerFactor(self.kWOut / kVA_Gen)
        else:
            self.setPowerFactor(1.0)
        if self.kWOut * self.kVArOut < 0.0:
            self.setPowerFactor(-self.PFNominal)

    def setPresentKW(self, value):
        self.setPctKWOut((value / self.kWhRating) * 100.0)
        self.kWOut = value
        # syncUpPowerQuantities();

    def setState(self, value):
        # FIXME Private method in OpenDSS
        self.state = value

    def syncUpPowerQuantities(self):
        if self.kVANotSet:
            self.kVARating = self.kWRating
        self.kVArOut = 0.0
        # keep kVAr nominal up to date with kW and PF
        if self.PFNominal != 0.0:
            self.kVArOut = self.kWOut * self.Math.sqrt((1.0 / self.Math.pow(self.PFNominal, 2)) - 1.0)
            if self.PFNominal < 0.0:
                self.kVArOut = -self.kVArOut

    def setDragHandRegister(self, reg, value):
        if value > self.registers[reg]:
            self.registers[reg] = value

    def getPowerFactor(self):
        return self.PFNominal

    def getState(self):
        return self.state

    def getPctKVArOut(self):
        return self.pctKVArOut

    def getPctKWOut(self):
        return self.pctKWout

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

    def getStorageClass(self):
        return self.storageClass

    def setStorageClass(self, cls):
        self.storageClass = cls

    def getVoltageModel(self):
        return self.voltageModel

    def setVoltageModel(self, model):
        self.voltageModel = model

    def getYearlyShape(self):
        return self.yearlyShape

    def setYearlyShape(self, shape):
        self.yearlyShape = shape

    def getYearlyShapeObj(self):
        return self.yearlyShapeObj

    def setYearlyShapeObj(self, shapeObj):
        self.yearlyShapeObj = shapeObj

    def getKWRating(self):
        return self.kWRating

    def setKWRating(self, kw):
        self.kWRating = kw

    def getKWhRating(self):
        return self.kWhRating

    def setKWhRating(self, kwh):
        self.kWhRating = kwh

    def getKWhStored(self):
        return self.kWhStored

    def setKWhStored(self, kwh):
        self.kWhStored = kwh

    def getKWhReserve(self):
        return self.kWhReserve

    def setKWhReserve(self, kwh):
        self.kWhReserve = kwh

    def getPctKWin(self):
        return self.pctKWIn

    def setPctKWin(self, pct):
        self.pctKWIn = pct

    def getPctReserve(self):
        return self.pctReserve

    def setPctReserve(self, pct):
        self.pctReserve = pct

    def getDispatchMode(self):
        return self.dispatchMode

    def setDispatchMode(self, mode):
        self.dispatchMode = mode

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

    def getYeqIdling(self):
        return self.YeqIdling

    def setYeqIdling(self, value):
        self.YeqIdling = value

    def isDebugTrace(self):
        return self.debugTrace

    def setDebugTrace(self, debug):
        self.debugTrace = debug

    def isStateChanged(self):
        return self.stateChanged

    def setStateChanged(self, value):
        self.stateChanged = value

    def isFirstSampleAfterReset(self):
        return self.firstSampleAfterReset

    def setFirstSampleAfterReset(self, value):
        self.firstSampleAfterReset = value

    def getStorageSolutionCount(self):
        return self.storageSolutionCount

    def setStorageSolutionCount(self, count):
        self.storageSolutionCount = count

    def getStorageFundamental(self):
        return self.storageFundamental

    def setStorageFundamental(self, fundamental):
        self.storageFundamental = fundamental

    def isStorageObjSwitchOpen(self):
        return self.storageObjSwitchOpen

    def setStorageObjSwitchOpen(self, value):
        self.storageObjSwitchOpen = value

    def isKVANotSet(self):
        return self.kVANotSet

    def setKVANotSet(self, value):
        self.kVANotSet = value

    def getKVArating(self):
        return self.kVARating

    def setKVA_Rating(self, rating):
        self.kVARating = rating

    def getKVStorageBase(self):
        return self.kVStorageBase

    def setKVStorageBase(self, base):
        self.kVStorageBase = base

    def getKVArOut(self):
        return self.kVArOut

    def setKVArOut(self, kvar):
        self.kVArOut = kvar

    def getKWOut(self):
        return self.kWOut

    def setKWOut(self, kW):
        self.kWOut = kW

    def getKVArRequested(self):
        return self.kVArRequested

    def setKVArRequested(self, kvar):
        self.kVArRequested = kvar

    def getPctIdleKW(self):
        return self.pctIdleKW

    def setPctIdleKW(self, pct):
        self.pctIdleKW = pct

    def getPctIdleKVAr(self):
        return self.pctIdleKVAr

    def setPctIdleKVAr(self, pct):
        self.pctIdleKVAr = pct

    def getPctChargeEff(self):
        return self.pctChargeEff

    def setPctChargeEff(self, pct):
        self.pctChargeEff = pct

    def getPctDischargeEff(self):
        return self.pctDischargeEff

    def setPctDischargeEff(self, pct):
        self.pctDischargeEff = pct

    def getChargeEff(self):
        return self.chargeEff

    def setChargeEff(self, eff):
        self.chargeEff = eff

    def getDischargeEff(self):
        return self.dischargeEff

    def setDischargeEff(self, eff):
        self.dischargeEff = eff

    def getDischargeTrigger(self):
        return self.dischargeTrigger

    def setDischargeTrigger(self, trigger):
        self.dischargeTrigger = trigger

    def getChargeTrigger(self):
        return self.chargeTrigger

    def setChargeTrigger(self, trigger):
        self.chargeTrigger = trigger

    def getChargeTime(self):
        return self.chargeTime

    def setChargeTime(self, time):
        self.chargeTime = time

    def getPctR(self):
        return self.pctR

    def setPctR(self, pct):
        self.pctR = pct

    def getPctX(self):
        return self.pctX

    def setPctX(self, pct):
        self.pctX = pct

    def getOpenStorageSolutionCount(self):
        return self.openStorageSolutionCount

    def setOpenStorageSolutionCount(self, count):
        self.openStorageSolutionCount = count

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

    def getThetaHarm(self):
        return self.thetaHarm

    def setThetaHarm(self, theta):
        self.thetaHarm = theta

    def getTraceFile(self):
        return self.traceFile

    def setTraceFile(self, file):
        self.traceFile = file

    def getKVArBase(self):
        return self.kVArBase

    def setKVArBase(self, kvarBase):
        self.kVArBase = kvarBase

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

    def getVThevhH(self):
        return self.VThevhH

    def setVThevhH(self, value):
        self.VThevhH = value

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

    def getUserModel(self):
        return self.userModel

    def setUserModel(self, model):
        self.userModel = model
