from dss.conversion.impl.PVSystemObjImpl import (PVSystemObjImpl,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.conversion.PVSystem import (PVSystem,)
# from java.io.IOException import (IOException,)


class PVSystemImpl(PCClassImpl, PVSystem):
    activePVSystemObj = None
    registerNames = [None] * super(PVSystemImpl).NumPVSystemRegisters

    def __init__(self):
        super(PVSystemImpl, self)()
        self.className = 'PVSystem'
        self.classType = self.classType + DSSClassDefs.PVSYSTEM_ELEMENT
        # In both PCelement and PVSystem element list
        self.activeElement = -1
        # set register names
        self.registerNames[0] = 'kWh'
        self.registerNames[1] = 'kvarh'
        self.registerNames[2] = 'Max kW'
        self.registerNames[3] = 'Max kVA'
        self.registerNames[4] = 'Hours'
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = PVSystem.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # see DSSClass
        # define property names
        self.addProperty('phases', 0, 'Number of Phases, this PVSystem element.  Power is evenly divided among phases.')
        self.addProperty('bus1', 1, 'Bus to which the PVSystem element is connected.  May include specific node specification.')
        self.addProperty('kv', PVSystem.KV, 'Nominal rated (1.0 per unit) voltage, kV, for PVSystem element. For 2- and 3-phase PVSystem elements, specify phase-phase kV. ' + 'Otherwise, specify actual kV across each branch of the PVSystem element. ' + 'If 1-phase wye (star or LN), specify phase-neutral kV. ' + 'If 1-phase delta or phase-phase connected, specify phase-phase kV.')
        # line-neutral voltage//  base voltage
        self.addProperty('irradiance', PVSystem.IRRADIANCE, 'Get/set the present irradiance value in kW/sq-m. Used as base value for shape multipliers. ' + 'Generally entered as peak value for the time period of interest and the yearly, daily, and duty load shape ' + 'objects are defined as per unit multipliers (just like Loads/Generators).')
        self.addProperty('Pmpp', PVSystem.PMPP, 'Get/set the rated max power of the PV array for 1.0 kW/sq-m irradiance and a user-selected array temperature. ' + 'The P-TCurve should be defined relative to the selected array temperature.')
        self.addProperty('Temperature', PVSystem.TEMP, 'Get/set the present Temperature. Used as fixed value corresponding to PTCurve property. ' + 'A multiplier is obtained from the Pmpp-Temp curve and applied to the nominal Pmpp from the irradiance ' + 'to determine the net array output.')
        self.addProperty('pf', PVSystem.PF, 'Nominally, the power factor for the output power. Default is 1.0. ' + 'Setting this property will cause the inverter to operate in constant power factor mode.' + 'Enter negative when kW and kvar have opposite signs.' + CRLF + 'A positive power factor signifies that the PVSystem element produces vars ' + CRLF + 'as is typical for a generator.  ')
        self.addProperty('conn', PVSystem.CONNECTION, '={wye|LN|delta|LL}.  Default is wye.')
        self.addProperty('kvar', PVSystem.KVAR, 'Get/set the present kvar value.  Setting this property forces the inverter to operate in constant kvar mode.')
        self.addProperty('kVA', self.KVA, 'kVA rating of inverter. Used as the base for Dynamics mode and Harmonics mode values.')
        self.addProperty('%Cutin', PVSystem.CUT_IN, '% cut-in power -- % of kVA rating of inverter. ' + 'When the inverter is OFF, the power from the array must be greater than this for the inverter to turn on.')
        self.addProperty('%Cutout', PVSystem.CUT_OUT, '% cut-out power -- % of kVA rating of inverter. ' + 'When the inverter is ON, the inverter turns OFF when the power from the array drops below this valye.')
        self.addProperty('EffCurve', PVSystem.INV_EFF_CURVE, 'An XYCurve object, previously defined, that describes the PER UNIT efficiency vs PER UNIT of rated kVA for the inverter. ' + 'Inverter output power is discounted by the multiplier obtained from this curve.')
        self.addProperty('P-TCurve', PVSystem.P_T_CURVE, 'An XYCurve object, previously defined, that describes the PV array PER UNIT Pmpp vs Temperature curve. ' + 'Temperature units must agree with the Temperature property and the Temperature shapes used for simulations. ' + 'The Pmpp values are specified in per unit of the Pmpp value for 1 kW/sq-m irradiance. ' + 'The value for the temperature at which Pmpp is defined should be 1.0. ' + 'The net array power is determined by the irradiance * Pmpp * f(Temperature)')
        self.addProperty('%R', self.PCTR, 'Equivalent percent internal resistance, ohms. Default is 0. Placed in series with internal voltage source' + ' for harmonics and dynamics modes. Use a combination of %IdlekW and %EffCharge and %EffDischarge to account for ' + 'losses in power flow modes.')
        self.addProperty('%X', PVSystem.PCTX, 'Equivalent percent internal reactance, ohms. Default is 50%. Placed in series with internal voltage source' + ' for harmonics and dynamics modes. (Limits fault current to 2 pu.) ' + 'Use %Idlekvar and kvar properties to account for any reactive power during power flow solutions.')
        self.addProperty('model', PVSystem.MODEL, 'Integer code (default=1) for the model to use for power output variation with voltage. ' + 'Valid values are:' + CRLF + CRLF + '1:PVSystem element injects a CONSTANT kW at specified power factor.' + CRLF + '2:PVSystem element is modeled as a CONSTANT ADMITTANCE.' + CRLF + '3:Compute load injection from User-written Model.')
        self.addProperty('Vminpu', PVSystem.VMIN_PU, 'Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. ' + 'Below this value, the load model reverts to a constant impedance model.')
        self.addProperty('Vmaxpu', PVSystem.VMAX_PU, 'Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. ' + 'Above this value, the load model reverts to a constant impedance model.')
        self.addProperty('yearly', PVSystem.YEARLY, 'Dispatch shape to use for yearly simulations.  Must be previously defined ' + 'as a Loadshape object. If this is not specified, the Daily dispatch shape, If any, is repeated ' + 'during Yearly solution modes. In the default dispatch mode, ' + 'the PVSystem element uses this loadshape to trigger State changes.')
        self.addProperty('daily', self.DAILY, 'Dispatch shape to use for daily simulations.  Must be previously defined ' + 'as a Loadshape object of 24 hrs, typically.  In the default dispatch mode, ' + 'the PVSystem element uses this loadshape to trigger State changes.')
        # daily dispatch (hourly)
        self.addProperty('duty', PVSystem.DUTY, 'Load shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. ' + 'Must be previously defined as a Loadshape object. ' + 'Typically would have time intervals of 1-5 seconds. ' + 'Designate the number of points to solve using the Set Number=xxxx command. ' + 'If there are fewer points in the actual shape, the shape is assumed to repeat.')
        # as for wind generation
        self.addProperty('Tyearly', PVSystem.T_YEARLY, 'Temperature shape to use for yearly simulations.  Must be previously defined ' + 'as a TShape object. If this is not specified, the Daily dispatch shape, If any, is repeated ' + 'during Yearly solution modes. ' + 'The PVSystem element uses this TShape to determine the Pmpp from the Pmpp vs T curve. ' + 'Units must agree with the Pmpp vs T curve.')
        self.addProperty('Tdaily', PVSystem.T_DAILY, 'Temperature shape to use for daily simulations.  Must be previously defined ' + 'as a TShape object of 24 hrs, typically.  ' + 'The PVSystem element uses this TShape to determine the Pmpp from the Pmpp vs T curve. ' + 'Units must agree with the Pmpp vs T curve.')
        # daily dispatch (hourly)
        self.addProperty('Tduty', PVSystem.T_DUTY, 'Temperature shape to use for duty cycle dispatch simulations such as for solar ramp rate studies. ' + 'Must be previously defined as a TShape object. ' + 'Typically would have time intervals of 1-5 seconds. ' + 'Designate the number of points to solve using the Set Number=xxxx command. ' + 'If there are fewer points in the actual shape, the shape is assumed to repeat. ' + 'The PVSystem model uses this TShape to determine the Pmpp from the Pmpp vs T curve. ' + 'Units must agree with the Pmpp vs T curve.')
        # Cloud transient simulation
        self.addProperty('class', self.CLASS, 'An arbitrary integer number representing the class of PVSystem element so that PVSystem values may ' + 'be segregated by class.')
        # integer
        self.addProperty('UserModel', self.USER_MODEL, 'Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, ' + 'overriding the default model.  Set to \"none\" to negate previous setting.')
        self.addProperty('UserData', PVSystem.USER_DATA, 'String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.')
        self.addProperty('debugtrace', PVSystem.DEBUG_TRACE, '{Yes | No }  Default is no.  Turn this on to capture the progress of the PVSystem model ' + 'for each iteration.  Creates a separate file for each PVSystem element named \"PVSystem_name.csv\".')
        self.activeProperty = PVSystem.NumPropsThisClass - 1
        super(PVSystemImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override default help string
        self.propertyHelp[PVSystem.NumPropsThisClass] = 'Name of harmonic voltage or current spectrum for this PVSystem element. ' + 'Current injection is assumed for inverter. ' + 'Default value is \"default\", which is defined when the DSS starts.'

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(PVSystemObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def setNCondsForConnection(self):
        apv = self.activePVSystemObj
        _0 = apv.getConnection()
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                apv.setNConds(apv.getNPhases() + 1)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                _2 = apv.getNPhases()
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        apv.setNConds(apv.getNPhases() + 1)
                        # L-L
                        break
                    if (_3 is True) or (_2 == 2):
                        _3 = True
                        apv.setNConds(apv.getNPhases() + 1)
                        # open-delta
                        break
                    if True:
                        _3 = True
                        apv.setNConds(apv.getNPhases())
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
                pElem.updatePVSystem()

    def interpretConnection(self, s):
        """Accepts (case insensitive):
          delta or LL
          Y, wye, or LN
        """
        apv = self.activePVSystemObj
        _0 = s.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                apv.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                apv.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                apv.setConnection(1)
                # Delta or Line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = s.toLowerCase()[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        apv.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        apv.setConnection(1)
                        break
                    break
                break
            break
        self.setNCondsForConnection()
        # VBase is always L-N voltage unless 1-phase device or more than 3 phases
        _4 = apv.getNPhases()
        _5 = False
        while True:
            if _4 == 2:
                _5 = True
                apv.setVBase(apv.getKVPVSystemBase() * DSSGlobals.InvSQRT3x1000)
                # L-N volts
                break
            if (_5 is True) or (_4 == 3):
                _5 = True
                apv.setVBase(apv.getKVPVSystemBase() * DSSGlobals.InvSQRT3x1000)
                # L-N volts
                break
            if True:
                _5 = True
                apv.setVBase(apv.getKVPVSystemBase() * 1000.0)
                # just use what is supplied
                break
            break
        apv.setVBase95(apv.getVMinPU() * apv.getVBase())
        apv.setVBase105(apv.getVMaxPU() * apv.getVBase())
        apv.setYorder(apv.getNConds() * apv.getNTerms())
        apv.setYPrimInvalid(True)

    def edit(self):
        """Uses global parser."""
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activePVSystemObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activePVSystemObj)
        result = 0
        apv = self.activePVSystemObj
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
            if paramPointer >= 0 and paramPointer < self.numProperties:
                apv.setPropertyValue(self.propertyIdxMap[paramPointer], param)
                # update the string value of the property
            else:
                DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for PVSystem \"' + apv.getName() + '\"', 560)
            if paramPointer >= 0:
                iCase = self.propertyIdxMap[paramPointer]
                _0 = iCase
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + apv.getName() + '\"', 561)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        apv.setNPhases(parser.makeInteger())
                        # num phases
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        apv.setBus(1, param)
                        # TODO Check zero based indexing
                        break
                    if (_1 is True) or (_0 == self.KV):
                        _1 = True
                        apv.setPresentKV(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.IRRADIANCE):
                        _1 = True
                        apv.setIrradiance(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PF):
                        _1 = True
                        apv.setPFSpecified(True)
                        apv.setKVArSpecified(False)
                        apv.setPowerFactor(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.MODEL):
                        _1 = True
                        apv.setVoltageModel(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == self.YEARLY):
                        _1 = True
                        apv.setYearlyShape(param)
                        break
                    if (_1 is True) or (_0 == self.DAILY):
                        _1 = True
                        apv.setDailyShape(param)
                        break
                    if (_1 is True) or (_0 == self.DUTY):
                        _1 = True
                        apv.setDutyShape(param)
                        break
                    if (_1 is True) or (_0 == self.T_YEARLY):
                        _1 = True
                        apv.setYearlyTShape(param)
                        break
                    if (_1 is True) or (_0 == self.T_DAILY):
                        _1 = True
                        apv.setDailyTShape(param)
                        break
                    if (_1 is True) or (_0 == self.T_DUTY):
                        _1 = True
                        apv.setDutyTShape(param)
                        break
                    if (_1 is True) or (_0 == self.CONNECTION):
                        _1 = True
                        self.interpretConnection(param)
                        break
                    if (_1 is True) or (_0 == self.KVAR):
                        _1 = True
                        apv.setKVArSpecified(True)
                        apv.setPFSpecified(False)
                        apv.setPresentKVAr(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCTR):
                        _1 = True
                        apv.setPctR(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PCTX):
                        _1 = True
                        apv.setPctX(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.CLASS):
                        _1 = True
                        apv.setFClass(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == self.INV_EFF_CURVE):
                        _1 = True
                        apv.setInverterCurve(param)
                        break
                    if (_1 is True) or (_0 == self.TEMP):
                        _1 = True
                        apv.setTemperature(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.PMPP):
                        _1 = True
                        apv.setPmpp(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.P_T_CURVE):
                        _1 = True
                        apv.setPowerTempCurve(param)
                        break
                    if (_1 is True) or (_0 == self.CUT_IN):
                        _1 = True
                        apv.setPctCutIn(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.CUT_OUT):
                        _1 = True
                        apv.setPctCutOut(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.VMIN_PU):
                        _1 = True
                        apv.setVMinPU(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.VMAX_PU):
                        _1 = True
                        apv.setVMaxPU(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.KVA):
                        _1 = True
                        apv.setKVArating(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == self.USER_MODEL):
                        _1 = True
                        apv.getUserModel().setName(parser.makeString())
                        # connect to user written models
                        break
                    if (_1 is True) or (_0 == self.USER_DATA):
                        _1 = True
                        apv.getUserModel().edit(parser.makeString())
                        # send edit string to user model
                        break
                    if (_1 is True) or (_0 == self.DEBUG_TRACE):
                        _1 = True
                        apv.setDebugTrace(Utilities.interpretYesNo(param))
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activePVSystemObj, paramPointer - self.NumPropsThisClass)
                        break
                    break
                _2 = iCase
                _3 = False
                while True:
                    if _2 == 0:
                        _3 = True
                        self.setNCondsForConnection()
                        # force reallocation of terminal info
                        break
                        # set loadshape objects; returns nil if not valid
                    if (_3 is True) or (_2 == self.YEARLY):
                        _3 = True
                        apv.setYearlyShapeObj(DSSGlobals.loadShapeClass.find(apv.getYearlyShape()))
                        break
                    if (_3 is True) or (_2 == self.DAILY):
                        _3 = True
                        apv.setDailyShapeObj(DSSGlobals.loadShapeClass.find(apv.getDailyShape()))
                        break
                    if (_3 is True) or (_2 == self.DUTY):
                        _3 = True
                        apv.setDutyShapeObj(DSSGlobals.loadShapeClass.find(apv.getDutyShape()))
                        break
                    if (_3 is True) or (_2 == self.T_YEARLY):
                        _3 = True
                        apv.setYearlyTShapeObj(DSSGlobals.TShapeClass.find(apv.getYearlyTShape()))
                        break
                    if (_3 is True) or (_2 == self.T_DAILY):
                        _3 = True
                        apv.setDailyTShapeObj(DSSGlobals.TShapeClass.find(apv.getDailyTShape()))
                        break
                    if (_3 is True) or (_2 == self.T_DUTY):
                        _3 = True
                        apv.setDutyTShapeObj(DSSGlobals.TShapeClass.find(apv.getDutyTShape()))
                        break
                    if (_3 is True) or (_2 == self.INV_EFF_CURVE):
                        _3 = True
                        apv.setInverterCurveObj(DSSGlobals.XYCurveClass.find(apv.getInverterCurve()))
                        break
                    if (_3 is True) or (_2 == self.P_T_CURVE):
                        _3 = True
                        apv.setPowerTempCurveObj(DSSGlobals.XYCurveClass.find(apv.getPowerTempCurve()))
                        break
                    if (_3 is True) or (_2 == self.DEBUG_TRACE):
                        _3 = True
                        if apv.isDebugTrace():
                            # init trace file
                            # TODO Auto-generated catch block
                            try:
                                fw = self.FileWriter(DSSGlobals.DSSDataDirectory + 'STOR_' + apv.getName() + '.csv', False)
                                bw = self.BufferedWriter(fw)
                                bw.write('t, Iteration, LoadMultiplier, Mode, LoadModel, PVSystemModel,  Qnominalperphase, Pnominalperphase, CurrentType')
                                _4 = True
                                i = 0
                                while True:
                                    if _4 is True:
                                        _4 = False
                                    else:
                                        i += 1
                                    if not (i < apv.getNPhases()):
                                        break
                                    bw.write(', |Iinj' + String.valueOf.valueOf(i) + '|')
                                _5 = True
                                i = 0
                                while True:
                                    if _5 is True:
                                        _5 = False
                                    else:
                                        i += 1
                                    if not (i < apv.getNPhases()):
                                        break
                                    bw.write(', |Iterm' + String.valueOf.valueOf(i) + '|')
                                _6 = True
                                i = 0
                                while True:
                                    if _6 is True:
                                        _6 = False
                                    else:
                                        i += 1
                                    if not (i < apv.getNPhases()):
                                        break
                                    bw.write(', |Vterm' + String.valueOf.valueOf(i) + '|')
                                bw.write(',Vthev, Theta')
                                bw.newLine()
                                bw.close()
                                fw.close()
                            except IOException, e:
                                e.printStackTrace()
                        break
                    break
            paramName = parser.getNextParam()
            param = parser.makeString()
        apv.recalcElementData()
        apv.setYPrimInvalid(True)
        return result

    def makeLike(self, otherPVSystemObjName):
        result = 0
        # See if we can find this line name in the present collection
        otherPVsystemObj = self.find(otherPVSystemObjName)
        if otherPVsystemObj is not None:
            apv = self.activePVSystemObj
            if apv.getNPhases() != otherPVsystemObj.getNPhases():
                apv.setNPhases(otherPVsystemObj.getNPhases())
                apv.setNConds(apv.getNPhases())
                # forces reallocation of terminal stuff
                apv.setYorder(apv.getNConds() * apv.getNTerms())
                apv.setYPrimInvalid(True)
            apv.setKVPVSystemBase(otherPVsystemObj.getKVPVSystemBase())
            apv.setVBase(otherPVsystemObj.getVBase())
            apv.setVMinPU(otherPVsystemObj.getVMinPU())
            apv.setVMaxPU(otherPVsystemObj.getVMaxPU())
            apv.setVBase95(otherPVsystemObj.getVBase95())
            apv.setVBase105(otherPVsystemObj.getVBase105())
            apv.setKWOut(otherPVsystemObj.getKWOut())
            apv.setKVArOut(otherPVsystemObj.getKVArOut())
            apv.setPNominalPerPhase(otherPVsystemObj.getPNominalPerPhase())
            apv.setPowerFactor(otherPVsystemObj.getPowerFactor())
            apv.setQNominalPerPhase(otherPVsystemObj.getQNominalPerPhase())
            apv.setConnection(otherPVsystemObj.getConnection())
            apv.setYearlyShape(otherPVsystemObj.getYearlyShape())
            apv.setYearlyShapeObj(otherPVsystemObj.getYearlyShapeObj())
            apv.setDailyShape(otherPVsystemObj.getDailyShape())
            apv.setDailyShapeObj(otherPVsystemObj.getDailyShapeObj())
            apv.setDutyShape(otherPVsystemObj.getDutyShape())
            apv.setDutyShapeObj(otherPVsystemObj.getDutyShapeObj())
            apv.setYearlyTShape(otherPVsystemObj.getYearlyTShape())
            apv.setYearlyTShapeObj(otherPVsystemObj.getYearlyTShapeObj())
            apv.setDailyTShape(otherPVsystemObj.getDailyTShape())
            apv.setDailyTShapeObj(otherPVsystemObj.getDailyTShapeObj())
            apv.setDutyTShape(otherPVsystemObj.getDutyTShape())
            apv.setDutyTShapeObj(otherPVsystemObj.getDutyTShapeObj())
            apv.setInverterCurve(otherPVsystemObj.getInverterCurve())
            apv.setInverterCurveObj(otherPVsystemObj.getInverterCurveObj())
            apv.setPowerTempCurve(otherPVsystemObj.getPowerTempCurve())
            apv.setPowerTempCurveObj(otherPVsystemObj.getPowerTempCurveObj())
            apv.setFClass(otherPVsystemObj.getFClass())
            apv.setVoltageModel(otherPVsystemObj.getVoltageModel())
            apv.setTemperature(otherPVsystemObj.getTemperature())
            apv.setPmpp(otherPVsystemObj.getPmpp())
            apv.setPctCutIn(otherPVsystemObj.getPctCutIn())
            apv.setPctCutOut(otherPVsystemObj.getPctCutOut())
            apv.setIrradiance(otherPVsystemObj.getIrradiance())
            apv.setKVArating(otherPVsystemObj.getKVARating())
            apv.setPctR(otherPVsystemObj.getPctR())
            apv.setPctX(otherPVsystemObj.getPctX())
            apv.setRandomMult(otherPVsystemObj.getRandomMult())
            apv.getUserModel().setName(otherPVsystemObj.getUserModel().getName())
            # connect to user written models
            self.classMakeLike(otherPVsystemObj)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < apv.getParentClass().getNumProperties()):
                    break
                apv.setPropertyValue(i, otherPVsystemObj.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in PVSystem makeLike: \"' + otherPVSystemObjName + '\" not found.', 562)
        return result

    def init(self, handle):
        if handle == 0:
            # init all
            p = self.elementList.getFirst()
            while p is not None:
                p.randomize(0)
                p = self.elementList.getNext()
        else:
            self.setActiveElement(handle)
            p = self.getActiveObj()
            p.randomize(0)
        DSSGlobals.doSimpleMsg('Need to implement PVSystem.init', -1)
        return 0

    def resetRegistersAll(self):
        idx = self.getFirst()
        while idx > 0:
            self.getActiveObj().resetRegisters()
            idx = self.getNext()

    def sampleAll(self):
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

    def setRegisterNames(self, names):
        self.registerNames = names

    def getRegisterNames(self):
        return self.registerNames
