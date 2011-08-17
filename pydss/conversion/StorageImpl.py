from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.conversion.Storage import (Storage,)
from dss.common.impl.Utilities import (Utilities,)
from dss.conversion.impl.StorageObjImpl import (StorageObjImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class StorageImpl(PCClassImpl, Storage):
    activeStorageObj = None
    cBuffer = [None] * 24
    registerNames = [None] * super(StorageImpl).NumStorageRegisters

    def __init__(self):
        super(StorageImpl, self)()
        self.className = 'Storage'
        self.classType = self.classType + DSSClassDefs.STORAGE_ELEMENT
        # in both PCElement and Storage element list
        self.activeElement = -1
        # set register names
        self.registerNames[0] = 'kWh'
        self.registerNames[1] = 'kvarh'
        self.registerNames[2] = 'Max kW'
        self.registerNames[3] = 'Max kVA'
        self.registerNames[4] = 'Hours'
        self.registerNames[5] = '$'
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # see DSSClass
        # define property names
        # Using the addProperty function, you can list the properties here in the order you want
        # them to appear when properties are accessed sequentially without tags. Syntax:
        # 
        #   addProperty(<name of property>, <index in the edit case statement>, <help text>);

        self.addProperty('phases', 0, 'Number of Phases, this Storage element.  Power is evenly divided among phases.')
        self.addProperty('bus1', 1, 'Bus to which the Storage element is connected.  May include specific node specification.')
        self.addProperty('kv', self.KV, 'Nominal rated (1.0 per unit) voltage, kV, for Storage element. For 2- and 3-phase Storage elements, specify phase-phase kV. ' + 'Otherwise, specify actual kV across each branch of the Storage element. ' + 'If wye (star), specify phase-neutral kV. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'If delta or phase-phase connected, specify phase-phase kV.')
        # line-neutral voltage//  base voltage
        self.addProperty('kW', self.KW, 'Get/set the present kW value.  A positive value denotes power coming OUT of the element, ' + DSSGlobals.CRLF + 'which is the opposite of a Load element. A negative value indicates the Storage element is in Charging state. ' + 'This value is modified internally depending on the dispatch mode. ')
        self.addProperty('pf', self.PF, 'Nominally, the power factor for discharging (acting as a generator). Default is 1.0. ' + 'Setting this property will also set the kvar property.' + 'Enter negative for leading powerfactor ' + '(when kW and kvar have opposite signs.)' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'A positive power factor for a generator signifies that the Storage element produces vars ' + 'as is typical for a generator.  ')
        self.addProperty('conn', self.CONNECTION, '={wye|LN|delta|LL}.  Default is wye.')
        self.addProperty('kvar', self.KVAR, 'Get/set the present kW value.  Alternative to specifying the power factor.  Side effect: ' + ' the power factor value is altered to agree based on present value of kW.')
        self.addProperty('kVA', self.KVA, 'kVA rating of power output. Defaults to rated kW. Used as the base for Dynamics mode and Harmonics mode values.')
        self.addProperty('kWrated', self.KW_RATED, 'kW rating of power output. Base for Loadshapes when DispMode=Follow. Side effect: Set KVA property.')
        self.addProperty('kWhrated', self.KWH_RATED, 'Rated storage capacity in kWh. Default is 50.')
        self.addProperty('kWhstored', self.KWH_STORED, 'Present amount of energy stored, kWh. Default is same as kWh rated.')
        self.addProperty('%stored', self.PCT_STORED, 'Present amount of energy stored, % of rated kWh. Default is 100%.')
        self.addProperty('%reserve', self.PCT_RESERVE, 'Percent of rated kWh storage capacity to be held in reserve for normal operation. Default = 20. ' + DSSGlobals.CRLF + 'This is treated as the minimum energy discharge level unless there is an emergency. For emergency operation ' + 'set this property lower. Cannot be less than zero.')
        self.addProperty('State', self.STATE, '{IDLING | CHARGING | DISCHARGING}  Get/Set present operational state. In DISCHARGING mode, the Storage element ' + 'acts as a generator and the kW property is positive. The element continues discharging at the scheduled output power level ' + 'until the storage reaches the reserve value. Then the state reverts to IDLING. ' + 'In the CHARGING state, the Storage element behaves like a Load and the kW property is negative. ' + 'The element continues to charge until the max storage kWh is reached and Then switches to IDLING state. ' + 'In IDLING state, the kW property shows zero. However, the resistive and reactive loss elements remain in the circuit ' + 'and the power flow report will show power being consumed.')
        self.addProperty('%Discharge', self.PCT_KW_OUT, 'Discharge rate (output power) in Percent of rated kW. Default = 100.')
        self.addProperty('%Charge', self.PCT_KW_IN, 'Charging rate (input power) in Percent of rated kW. Default = 100.')
        self.addProperty('%EffCharge', self.CHARGE_EFF, 'Percent efficiency for CHARGING the storage element. Default = 90.')
        self.addProperty('%EffDischarge', self.DISCHARGE_EFF, 'Percent efficiency for DISCHARGING the storage element. Default = 90.' + 'Idling losses are handled by %IdlingkW property and are in addition to the charging and discharging efficiency losses ' + 'in the power conversion process inside the unit.')
        self.addProperty('%IdlingkW', self.IDLE_KW, 'Percent of rated kW consumed while idling. Default = 1.')
        self.addProperty('%Idlingkvar', self.IDLE_KVAR, 'Percent of rated kW consumed as reactive power (kvar) while idling. Default = 0.')
        self.addProperty('%R', self.PCTR, 'Equivalent percent internal resistance, ohms. Default is 0. Placed in series with internal voltage source' + ' for harmonics and dynamics modes. Use a combination of %IdlekW and %EffCharge and %EffDischarge to account for ' + 'losses in power flow modes.')
        self.addProperty('%X', self.PCTX, 'Equivalent percent internal reactance, ohms. Default is 50%. Placed in series with internal voltage source' + ' for harmonics and dynamics modes. (Limits fault current to 2 pu.) ' + 'Use %Idlekvar and kvar properties to account for any reactive power during power flow solutions.')
        self.addProperty('model', self.MODEL, 'Integer code (default=1) for the model to use for powet output variation with voltage. ' + 'Valid values are:' + DSSGlobals.CRLF + DSSGlobals.CRLF + '1:Storage element injects a CONSTANT kW at specified power factor.' + DSSGlobals.CRLF + '2:Storage element is modeled as a CONSTANT ADMITTANCE.' + DSSGlobals.CRLF + '3:Compute load injection from User-written Model.')
        self.addProperty('Vminpu', self.VMIN_PU, 'Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. ' + 'Below this value, the load model reverts to a constant impedance model.')
        self.addProperty('Vmaxpu', self.VMAX_PU, 'Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. ' + 'Above this value, the load model reverts to a constant impedance model.')
        self.addProperty('yearly', self.YEARLY, 'Dispatch shape to use for yearly simulations.  Must be previously defined ' + 'as a Loadshape object. If this is not specified, the Daily dispatch shape, if any, is repeated ' + 'during Yearly solution modes. In the default dispatch mode, ' + 'the Storage element uses this loadshape to trigger State changes.')
        self.addProperty('daily', self.DAILY, 'Dispatch shape to use for daily simulations.  Must be previously defined ' + 'as a Loadshape object of 24 hrs, typically.  In the default dispatch mode, ' + 'the Storage element uses this loadshape to trigger State changes.')
        # daily dispatch (hourly)
        self.addProperty('duty', self.DUTY, 'Load shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. ' + 'Must be previously defined as a Loadshape object. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Typically would have time intervals of 1-5 seconds. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Designate the number of points to solve using the Set Number=xxxx command. ' + 'If there are fewer points in the actual shape, the shape is assumed to repeat.')
        # as for wind generation
        self.addProperty('DispMode', self.DISP_MODE, '{DEFAULT | FOLLOW | EXTERNAL | LOADLEVEL | PRICE } Default = \"DEFAULT\". Dispatch mode. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'In DEFAULT mode, Storage element state is triggered to discharge or charge at the specified rate by the ' + 'loadshape curve corresponding to the solution mode. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'In FOLLOW mode the kW and kvar output of the STORAGE element follows the active loadshape multipliers ' + 'until storage is either exhausted or full. ' + 'The element discharges for positive values and charges for negative values.  The loadshapes are based on the kW and kvar ' + 'values in the most recent definition of kW and PF or kW and kvar properties. ' + DSSGlobals.CRLF + DSSGlobals.CRLF)
        self.addProperty('DischargeTrigger', self.DISP_OUT_TRIG, 'Dispatch trigger value for discharging the storage. ' + DSSGlobals.CRLF + 'If = 0.0 the Storage element state is changed by the State command or by a StorageController object. ' + DSSGlobals.CRLF + 'If <> 0  the Storage element state is set to DISCHARGING when this trigger level is EXCEEDED by either the specified ' + 'Loadshape curve value or the price signal or global Loadlevel value, depending on dispatch mode. See State property.')
        self.addProperty('Chargetrigger', self.DISP_IN_TRIG, 'Dispatch trigger value for charging the storage. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'If = 0.0 the Storage element state is changed by the State command or StorageController object.  ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'If <> 0  the Storage element state is set to CHARGING when this trigger level is GREATER than either the specified ' + 'Loadshape curve value or the price signal or global Loadlevel value, depending on dispatch mode. See State property.')
        self.addProperty('TimeChargeTrig', self.CHARGE_TIME, 'Time of day in fractional hours (0230 = 2.5) at which storage element will automatically go into charge state. ' + 'Default is 2.0.  Enter a negative time value to disable this feature.')
        self.addProperty('class', self.CLASS, 'An arbitrary integer number representing the class of Storage element so that Storage values may ' + 'be segregated by class.')
        # integer
        self.addProperty('UserModel', self.USER_MODEL, 'Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, ' + 'overriding the default model.  Set to \"none\" to negate previous setting.')
        self.addProperty('UserData', self.USER_DATA, 'String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.')
        self.addProperty('debugtrace', self.DEBUG_TRACE, '{Yes | No }  Default is no.  Turn this on to capture the progress of the Storage model ' + 'for each iteration.  Creates a separate file for each Storage element named \"STORAGE_name.CSV\".')
        self.activeProperty = self.NumPropsThisClass - 1
        super(StorageImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override default help string
        self.propertyHelp[self.NumPropsThisClass] = 'Name of harmonic voltage or current spectrum for this Storage element. ' + 'Current injection is assumed for inverter. ' + 'Default value is \"default\", which is defined when the DSS starts.'

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(StorageObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def setNcondsForConnection(self):
        as_ = self.activeStorageObj
        _0 = as_.getConnection()
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                as_.setNConds(as_.getNPhases() + 1)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                _2 = as_.getNPhases()
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        as_.setNConds(as_.getNPhases() + 1)
                        # L-L
                        break
                    if (_3 is True) or (_2 == 2):
                        _3 = True
                        as_.setNConds(as_.getNPhases() + 1)
                        # open-delta
                        break
                    if True:
                        _3 = True
                        as_.setNConds(as_.getNPhases())
                        break
                    break
                break
            break

    def updateAll(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            pElem = self.elementList.get(i)
            if pElem.isEnabled():
                pElem.updateStorage()

    def interpretConnection(self, s):
        """Accepts (case insensitive):
          delta or LL
          Y, wye, or LN
        """
        as_ = self.activeStorageObj
        testS = s.toLowerCase()
        _0 = testS[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                as_.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                as_.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                as_.setConnection(1)
                # Delta or Line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = testS[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        as_.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        as_.setConnection(1)
                        break
                    break
                break
            break
        self.setNcondsForConnection()
        # VBase is always L-N voltage unless 1-phase device or more than 3 phases
        _4 = as_.getNPhases()
        _5 = False
        while True:
            if _4 == 2:
                _5 = True
                as_.setVBase(as_.getKVStorageBase() * DSSGlobals.InvSQRT3x1000)
                # L-N Volts
                break
            if (_5 is True) or (_4 == 3):
                _5 = True
                as_.setVBase(as_.getKVStorageBase() * DSSGlobals.InvSQRT3x1000)
                break
            if True:
                _5 = True
                as_.setVBase(as_.getKVStorageBase() * 1000.0)
                # just use what is supplied
                break
            break
        as_.setVBase95(as_.getVMinPU() * as_.getVBase())
        as_.setVBase105(as_.getVMaxPU() * as_.getVBase())
        as_.setYorder(as_.getNConds() * as_.getNTerms())
        as_.setYPrimInvalid(True)

    def interpretDispMode(self, s):
        _0 = s.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'e':
                _1 = True
                return self.EXTERNAL_MODE
            if (_1 is True) or (_0 == 'f'):
                _1 = True
                return self.FOLLOW
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                return self.LOAD_MODE
            if (_1 is True) or (_0 == 'p'):
                _1 = True
                return self.PRICE_MODE
            if True:
                _1 = True
                return self.DEFAULT
            break

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeStorageObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeStorageObj)
        result = 0
        as_ = self.activeStorageObj
        paramPointer = 0
        paramName = parser.getNextParam()
        # parse next property off the command line
        param = parser.makeString()
        # put the string value of the property value in local memory for faster access
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
                # if it is not a named property, assume the next property
            else:
                paramPointer = self.commandList.getCommand(paramName)
                # look up the name in the list for this class
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                as_.setPropertyValue(self.propertyIdxMap[paramPointer], param)
                # update the string value of the property
            else:
                DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for Storage \"' + as_.getName() + '\"', 560)
            if paramPointer > 0:
                iCase = self.propertyIdxMap[paramPointer]
                _0 = iCase
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + as_.getName() + '\"', 561)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        as_.setNPhases(parser.makeInteger())
                        # num phases
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        as_.setBus(1, param)
                        # TODO Check zero based indexing
                        break
                    if (_1 is True) or (_0 == self.KV):
                        _1 = True
                        as_.setPresentKV(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.KW):
                        _1 = True
                        as_.setKWOut(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PF):
                        _1 = True
                        as_.setPowerFactor(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.MODEL):
                        _1 = True
                        as_.setVoltageModel(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == self.YEARLY):
                        _1 = True
                        as_.setYearlyShape(param)
                        break
                    if (_1 is True) or (_0 == self.DAILY):
                        _1 = True
                        as_.setDailyShape(param)
                        break
                    if (_1 is True) or (_0 == self.DUTY):
                        _1 = True
                        as_.setDutyShape(param)
                        break
                    if (_1 is True) or (_0 == self.DISP_MODE):
                        _1 = True
                        as_.setDispatchMode(self.interpretDispMode(param))
                        break
                    if (_1 is True) or (_0 == self.IDLE_KVAR):
                        _1 = True
                        as_.setPctIdleKVAr(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.CONNECTION):
                        _1 = True
                        self.interpretConnection(param)
                        break
                    if (_1 is True) or (_0 == self.KVAR):
                        _1 = True
                        as_.setPresentKVAr(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCTR):
                        _1 = True
                        as_.setPctR(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCTX):
                        _1 = True
                        as_.setPctX(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.IDLE_KW):
                        _1 = True
                        as_.setPctIdleKW(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.CLASS):
                        _1 = True
                        as_.setStorageClass(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == self.DISP_OUT_TRIG):
                        _1 = True
                        as_.setDischargeTrigger(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.DISP_IN_TRIG):
                        _1 = True
                        as_.setChargeTrigger(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.CHARGE_EFF):
                        _1 = True
                        as_.setPctChargeEff(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.DISCHARGE_EFF):
                        _1 = True
                        as_.setPctDischargeEff(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCT_KW_OUT):
                        _1 = True
                        as_.setPctKWOut(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.VMIN_PU):
                        _1 = True
                        as_.setVMinPU(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.VMAX_PU):
                        _1 = True
                        as_.setVMaxPU(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.STATE):
                        _1 = True
                        as_.setState(as_.interpretState(param))
                        break
                    if (_1 is True) or (_0 == self.KVA):
                        _1 = True
                        as_.setKVA_Rating(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.KW_RATED):
                        _1 = True
                        as_.setKWRating(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.KWH_RATED):
                        _1 = True
                        as_.setKWhRating(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.KWH_STORED):
                        _1 = True
                        as_.setKWhStored(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCT_RESERVE):
                        _1 = True
                        as_.setPctReserve(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.USER_MODEL):
                        _1 = True
                        as_.getUserModel().setName(parser.makeString())
                        # connect to user written models
                        break
                    if (_1 is True) or (_0 == self.USER_DATA):
                        _1 = True
                        as_.getUserModel().edit(parser.makeString())
                        # send edit string to user model
                        break
                    if (_1 is True) or (_0 == self.DEBUG_TRACE):
                        _1 = True
                        as_.setDebugTrace(Utilities.interpretYesNo(param))
                        break
                    if (_1 is True) or (_0 == self.PCT_KW_IN):
                        _1 = True
                        as_.setPctKWin(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCT_STORED):
                        _1 = True
                        as_.setKWhStored(parser.makeDouble() * 0.01 * as_.getKWhRating())
                        break
                    if (_1 is True) or (_0 == self.CHARGE_TIME):
                        _1 = True
                        as_.setChargeTime(parser.makeDouble())
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeStorageObj, paramPointer - self.NumPropsThisClass)
                        break
                    break
                _2 = iCase
                _3 = False
                while True:
                    if _2 == 0:
                        _3 = True
                        self.setNcondsForConnection()
                        # force reallocation of terminal info
                        break
                    if (_3 is True) or (_2 == self.KW):
                        _3 = True
                        as_.syncUpPowerQuantities()
                        # keep kVAr nominal up to date with kW and PF
                        break
                    if (_3 is True) or (_2 == self.PF):
                        _3 = True
                        as_.syncUpPowerQuantities()
                        break
                        # Set load shape objects; returns nil if not valid
                    if (_3 is True) or (_2 == self.YEARLY):
                        _3 = True
                        as_.setYearlyShapeObj(DSSGlobals.loadShapeClass.find(as_.getYearlyShape()))
                        break
                    if (_3 is True) or (_2 == self.DAILY):
                        _3 = True
                        as_.setDailyShapeObj(DSSGlobals.loadShapeClass.find(as_.getDailyShape()))
                        break
                    if (_3 is True) or (_2 == self.DUTY):
                        _3 = True
                        as_.setDutyShapeObj(DSSGlobals.loadShapeClass.find(as_.getDutyShape()))
                        break
                    if (_3 is True) or (_2 == self.KW_RATED):
                        _3 = True
                        as_.setKVA_Rating(as_.getKWRating())
                        break
                    if (_3 is True) or (_2 == self.KWH_RATED):
                        _3 = True
                        as_.setKWhStored(as_.getKWhRating())
                        # Assume fully charged
                        as_.setKWhReserve(as_.getKWhRating() * as_.getPctReserve() * 0.01)
                        break
                    if (_3 is True) or (_2 == self.PCT_RESERVE):
                        _3 = True
                        as_.setKWhReserve(as_.getKWhRating() * as_.getPctReserve() * 0.01)
                        break
                    if (_3 is True) or (_2 == self.DEBUG_TRACE):
                        _3 = True
                        if as_.isDebugTrace():
                            # init trace file
                            # TODO: handle exception
                            try:
                                TraceFile = self.File(DSSGlobals.DSSDataDirectory + 'STOR_' + as_.getName() + '.csv')
                                TraceStream = self.FileWriter(TraceFile, False)
                                TraceBuffer = self.BufferedWriter(TraceStream)
                                TraceBuffer.write('t, Iteration, LoadMultiplier, Mode, LoadModel, StorageModel,  Qnominalperphase, Pnominalperphase, CurrentType')
                                _4 = True
                                i = 0
                                while True:
                                    if _4 is True:
                                        _4 = False
                                    else:
                                        i += 1
                                    if not (i < as_.getNPhases()):
                                        break
                                    TraceBuffer.write(', |Iinj' + String.valueOf.valueOf(i) + '|')
                                _5 = True
                                i = 0
                                while True:
                                    if _5 is True:
                                        _5 = False
                                    else:
                                        i += 1
                                    if not (i < as_.getNPhases()):
                                        break
                                    TraceBuffer.write(', |Iterm' + String.valueOf.valueOf(i) + '|')
                                _6 = True
                                i = 0
                                while True:
                                    if _6 is True:
                                        _6 = False
                                    else:
                                        i += 1
                                    if not (i < as_.getNPhases()):
                                        break
                                    TraceBuffer.write(', |Vterm' + String.valueOf.valueOf(i) + '|')
                                TraceBuffer.write(',Vthev, Theta')
                                TraceBuffer.newLine()
                                TraceBuffer.close()
                                TraceStream.close()
                            except Exception, e:
                                pass # astStmt: [Stmt([]), None]
                        break
                    if (_3 is True) or (_2 == self.KVA):
                        _3 = True
                        as_.setKVANotSet(False)
                        break
                    break
            paramName = parser.getNextParam()
            param = parser.makeString()
        as_.recalcElementData()
        as_.setYPrimInvalid(True)
        return result

    def makeLike(self, otherStorageObjName):
        """Copy over essential properties from other object."""
        result = 0
        # See if we can find this line name in the present collection
        otherStorageObj = self.find(otherStorageObjName)
        if otherStorageObj is not None:
            as_ = self.activeStorageObj
            if as_.getNPhases() != otherStorageObj.getNPhases():
                as_.setNPhases(otherStorageObj.getNPhases())
                as_.setNConds(as_.getNPhases())
                # forces reallocation of terminal stuff
                as_.setYorder(as_.getNConds() * as_.getNTerms())
                as_.setYPrimInvalid(True)
            as_.setKVStorageBase(otherStorageObj.getKVStorageBase())
            as_.setVBase(otherStorageObj.getVBase())
            as_.setVMinPU(otherStorageObj.getVMinPU())
            as_.setVMaxPU(otherStorageObj.getVMaxPU())
            as_.setVBase95(otherStorageObj.getVBase95())
            as_.setVBase105(otherStorageObj.getVBase105())
            as_.setKWOut(otherStorageObj.getKWOut())
            as_.setKVArOut(otherStorageObj.getKVArOut())
            as_.setPNominalPerPhase(otherStorageObj.getPNominalPerPhase())
            as_.setPowerFactor(otherStorageObj.getPowerFactor())
            as_.setQNominalPerPhase(otherStorageObj.getQNominalPerPhase())
            as_.setConnection(otherStorageObj.getConnection())
            as_.setYearlyShape(otherStorageObj.getYearlyShape())
            as_.setYearlyShapeObj(otherStorageObj.getYearlyShapeObj())
            as_.setDailyShape(otherStorageObj.getDailyShape())
            as_.setDailyShapeObj(otherStorageObj.getDailyShapeObj())
            as_.setDutyShape(otherStorageObj.getDutyShape())
            as_.setDutyShapeObj(otherStorageObj.getDutyShapeObj())
            as_.setDispatchMode(otherStorageObj.getDispatchMode())
            as_.setStorageClass(otherStorageObj.getStorageClass())
            as_.setVoltageModel(otherStorageObj.getVoltageModel())
            as_.setState(otherStorageObj.getState())
            as_.setStateChanged(otherStorageObj.isStateChanged())
            as_.setKVANotSet(otherStorageObj.isKVANotSet())
            as_.setKVA_Rating(otherStorageObj.getKVArating())
            as_.setKWRating(otherStorageObj.getKWRating())
            as_.setKWhRating(otherStorageObj.getKWhRating())
            as_.setKWhStored(otherStorageObj.getKWhStored())
            as_.setKWhReserve(otherStorageObj.getKWhReserve())
            as_.setPctReserve(otherStorageObj.getPctReserve())
            as_.setDischargeTrigger(otherStorageObj.getDischargeTrigger())
            as_.setChargeTrigger(otherStorageObj.getChargeTrigger())
            as_.setPctChargeEff(otherStorageObj.getPctChargeEff())
            as_.setPctDischargeEff(otherStorageObj.getPctDischargeEff())
            as_.setPctKWOut(otherStorageObj.getPctKWOut())
            as_.setPctKWin(otherStorageObj.getPctKWin())
            as_.setPctIdleKW(otherStorageObj.getPctIdleKW())
            as_.setPctIdleKVAr(otherStorageObj.getPctIdleKVAr())
            as_.setChargeTime(otherStorageObj.getChargeTime())
            as_.setPctR(otherStorageObj.getPctR())
            as_.setPctX(otherStorageObj.getPctX())
            as_.setRandomMult(otherStorageObj.getRandomMult())
            as_.getUserModel().setName(otherStorageObj.getUserModel().getName())
            # connect to user written models
            self.classMakeLike(otherStorageObj)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < as_.getParentClass().getNumProperties()):
                    break
                as_.setPropertyValue(i, otherStorageObj.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Storage makeLike: \"' + otherStorageObjName + '\" not found.', 562)
        return result

    def init(self, handle):
        if handle == 0:
            # init all
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(self.elementList)):
                    break
                pElem = self.elementList.get(i)
                pElem.randomize(0)
        else:
            self.setActiveElement(handle)
            pElem = self.getActiveObj()
            pElem.randomize(0)
        DSSGlobals.doSimpleMsg('Need to implement Storage.init', -1)
        return 0

    def resetRegistersAll(self):
        """Force all EnergyMeters in the circuit to reset."""
        idx = self.getFirst()
        while idx >= 0:
            pElem = self.getActiveObj()
            pElem.resetRegisters()
            idx = self.getNext()

    def sampleAll(self):
        """Force all Storage elements in the circuit to take a sample."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            pElem = self.elementList.get(i)
            if pElem.isEnabled():
                pElem.takeSample()

    def getRegisterNames(self):
        return self.registerNames

    def setRegisterNames(self, names):
        self.registerNames = names
