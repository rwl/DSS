from dss.conversion.impl.LoadObjImpl import (LoadObjImpl,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.conversion.Load import (Load,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class LoadImpl(PCClassImpl, Load):
    activeLoadObj = None

    def __init__(self):
        super(LoadImpl, self)()
        self.className = 'Load'
        self.classType = self.classType + DSSClassDefs.LOAD_ELEMENT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        """Add properties of this class to propName."""
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'phases'
        self.propertyName[1] = 'bus1'
        self.propertyName[2] = 'kV'
        self.propertyName[3] = 'kW'
        self.propertyName[4] = 'pf'
        self.propertyName[5] = 'model'
        self.propertyName[6] = 'yearly'
        self.propertyName[7] = 'daily'
        self.propertyName[8] = 'duty'
        self.propertyName[9] = 'growth'
        self.propertyName[10] = 'conn'
        self.propertyName[11] = 'kvar'
        self.propertyName[12] = 'Rneut'
        # if entered -, assume open
        self.propertyName[13] = 'Xneut'
        self.propertyName[14] = 'status'
        # fixed or variable
        self.propertyName[15] = 'class'
        # integer
        self.propertyName[16] = 'Vminpu'
        # min pu voltage for which model applies
        self.propertyName[17] = 'Vmaxpu'
        # max pu voltage for which model applies
        self.propertyName[18] = 'Vminnorm'
        # min pu voltage normal load
        self.propertyName[19] = 'Vminemerg'
        # min pu voltage emergency rating
        self.propertyName[20] = 'xfkVA'
        # service transformer rated kVA
        self.propertyName[21] = 'allocationfactor'
        # allocation factor for xfkVA
        self.propertyName[22] = 'kVA'
        # specify load in kVA and PF
        self.propertyName[23] = '%mean'
        # per cent default mean
        self.propertyName[24] = '%stddev'
        # per cent default standard deviation
        self.propertyName[25] = 'CVRwatts'
        # percent watts reduction per 1% reduction in voltage from nominal
        self.propertyName[26] = 'CVRvars'
        # percent vars reduction per 1% reduction in voltage from nominal
        self.propertyName[27] = 'kwh'
        # kwh billing
        self.propertyName[28] = 'kwhdays'
        # kwh billing period (24-hr days)
        self.propertyName[29] = 'Cfactor'
        # multiplier from kWh avg to peak kW
        self.propertyName[30] = 'CVRcurve'
        # name of curve to use for yearly CVR simulations
        self.propertyName[31] = 'NumCust'
        # number of customers, this load
        self.propertyName[32] = 'ZIPV'
        # array of 7 coefficients
        # define property help values
        self.propertyHelp[0] = 'Number of Phases, this load.  Load is evenly divided among phases.'
        self.propertyHelp[1] = 'Bus to which the load is connected.  May include specific node specification.'
        self.propertyHelp[2] = 'Nominal rated (1.0 per unit) voltage, kV, for load. For 2- and 3-phase loads, specify phase-phase kV. ' + 'Otherwise, specify actual kV across each branch of the load. ' + 'If wye (star), specify phase-neutral kV. ' + 'If delta or phase-phase connected, specify phase-phase kV.'
        # line-neutral voltage
        self.propertyHelp[3] = 'Total base kW for the load.  Normally, you would enter the maximum kW for the load for the first year ' + 'and allow it to be adjusted by the load shapes, growth shapes, and global load multiplier.' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Legal ways to define base load:' + DSSGlobals.CRLF + 'kW, PF' + DSSGlobals.CRLF + 'kW, kvar' + DSSGlobals.CRLF + 'kVA, PF' + DSSGlobals.CRLF + 'XFKVA * Allocationfactor, PF' + DSSGlobals.CRLF + 'kWh/(kWhdays*24) * Cfactor, PF'
        self.propertyHelp[4] = 'Load power factor.  Enter negative for leading powerfactor (when kW and kvar have opposite signs.)'
        self.propertyHelp[5] = 'Integer code for the model to use for load variation with voltage. ' + 'Valid values are:' + DSSGlobals.CRLF + DSSGlobals.CRLF + '1:Standard constant P+jQ load. (Default)' + DSSGlobals.CRLF + '2:Constant impedance load. ' + DSSGlobals.CRLF + '3:Const P, Quadratic Q (like a motor).' + DSSGlobals.CRLF + '4:Nominal Linear P, Quadratic Q (feeder mix). Use this with CVRfactor.' + DSSGlobals.CRLF + '5:Constant Current Magnitude' + DSSGlobals.CRLF + '6:Const P, Fixed Q' + DSSGlobals.CRLF + '7:Const P, Fixed Impedance Q' + DSSGlobals.CRLF + '8:ZIPV (7 values)' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'For Types 6 and 7, only the P is modified by load multipliers.'
        self.propertyHelp[6] = 'Load shape to use for yearly simulations.  Must be previously defined ' + 'as a Loadshape object. Defaults to Daily load shape ' + ' when Daily is defined.  The daily load shape is repeated in this case. ' + 'Set Status=Fixed to ignore Loadshape designation. ' + 'Set to NONE to reset to no loadahape. ' + 'The default is no variation.'
        self.propertyHelp[7] = 'Load shape to use for daily simulations.  Must be previously defined ' + 'as a Loadshape object of 24 hrs, typically. ' + 'Set Status=Fixed to ignore Loadshape designation. ' + 'Set to NONE to reset to no loadahape. ' + 'Default is no variation (constant) if not defined. ' + 'Side effect: Sets Yearly load shape if not already defined.'
        self.propertyHelp[8] = 'Load shape to use for duty cycle simulations.  Must be previously defined ' + 'as a Loadshape object.  Typically would have time intervals less than 1 hr. ' + 'Designate the number of points to solve using the Set Number=xxxx command. ' + 'If there are fewer points in the actual shape, the shape is assumed to repeat.' + 'Set to NONE to reset to no loadahape. ' + 'Set Status=Fixed to ignore Loadshape designation. ' + ' Defaults to Daily curve If not specified.'
        self.propertyHelp[9] = 'Characteristic  to use for growth factors by years.  Must be previously defined ' + 'as a Growthshape object. Defaults to circuit default growth factor (see Set Growth command).'
        self.propertyHelp[10] = '={wye or LN | delta or LL}.  Default is wye.'
        self.propertyHelp[11] = 'Specify the base kvar for specifying load as kW & kvar.  Assumes kW has been already defined.  Alternative to specifying the power factor.  Side effect: ' + ' the power factor and kVA is altered to agree.'
        self.propertyHelp[12] = 'Default is -1. Neutral resistance of wye (star)-connected load in actual ohms. ' + 'If entered as a negative value, the neutral can be open, or floating, or it can be connected to ' + 'node 0 (ground), which is the usual default. ' + 'If >=0 be sure to explicitly specify the node connection for the neutral, or last, conductor. ' + 'Otherwise, the neutral impedance will be shorted to ground.'
        self.propertyHelp[13] = 'Neutral reactance of wye(star)-connected load in actual ohms.  May be + or -.'
        self.propertyHelp[14] = '={Variable | Fixed | Exempt}.  Default is variable. If Fixed, no load multipliers apply;  however, growth ' + 'multipliers do apply.  All multipliers apply to Variable loads.  Exempt loads are not ' + 'modified by the global load multiplier, such as in load duration curves, etc.  Daily multipliers ' + 'do apply, so setting this property to Exempt is a good way to represent industrial load that stays the same' + ' day-after-day for the period study.'
        # fixed or variable
        self.propertyHelp[15] = 'An arbitrary integer number representing the class of load so that load values may ' + 'be segregated by load value. Default is 1; not used internally.'
        self.propertyHelp[16] = 'Default = 0.95.  Minimum per unit voltage for which the MODEL is assumed to apply. ' + 'Below this value, the load model reverts to a constant impedance model.'
        self.propertyHelp[17] = 'Default = 1.05.  Maximum per unit voltage for which the MODEL is assumed to apply. ' + 'Above this value, the load model reverts to a constant impedance model.'
        self.propertyHelp[18] = 'Minimum per unit voltage for load EEN evaluations, Normal limit.  Default = 0, which defaults to system \"vminnorm\" ' + 'property (see Set Command under Executive).  If this property is specified, it ALWAYS ' + 'overrides the system specification. This allows you to have different criteria for different loads. ' + 'Set to zero to revert to the default system value.'
        self.propertyHelp[19] = 'Minimum per unit voltage for load UE evaluations, Emergency limit.  Default = 0, which defaults to system \"vminemerg\" ' + 'property (see Set Command under Executive).  If this property is specified, it ALWAYS ' + 'overrides the system specification. This allows you to have different criteria for different loads. ' + 'Set to zero to revert to the default system value.'
        self.propertyHelp[20] = 'Default = 0.0.  Rated kVA of service transformer for allocating loads based on connected kVA ' + 'at a bus. Side effect:  kW, PF, and kvar are modified. See help on kVA.'
        self.propertyHelp[21] = 'Default = 0.5.  Allocation factor for allocating loads based on connected kVA ' + 'at a bus. Side effect:  kW, PF, and kvar are modified by multiplying this factor times the XFKVA (if > 0).'
        self.propertyHelp[22] = 'Specify base Load in kVA (and power factor)' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Legal ways to define base load:' + DSSGlobals.CRLF + 'kW, PF' + DSSGlobals.CRLF + 'kW, kvar' + DSSGlobals.CRLF + 'kVA, PF' + DSSGlobals.CRLF + 'XFKVA * Allocationfactor, PF' + DSSGlobals.CRLF + 'kWh/(kWhdays*24) * Cfactor, PF'
        self.propertyHelp[23] = 'Percent mean value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 50.'
        self.propertyHelp[24] = 'Percent Std deviation value for load to use for monte carlo studies if no loadshape is assigned to this load. Default is 10.'
        self.propertyHelp[25] = 'Percent reduction in active power (watts) per 1% reduction in voltage from 100% rated. Default=1. ' + DSSGlobals.CRLF + ' Typical values range from 0.4 to 0.8. Applies to Model=4 only.' + DSSGlobals.CRLF + ' Intended to represent conservation voltage reduction or voltage optimization measures.'
        self.propertyHelp[26] = 'Percent reduction in reactive power (vars) per 1% reduction in voltage from 100% rated. Default=2. ' + DSSGlobals.CRLF + ' Typical values range from 2 to 3. Applies to Model=4 only.' + DSSGlobals.CRLF + ' Intended to represent conservation voltage reduction or voltage optimization measures.'
        self.propertyHelp[27] = 'kWh billed for this period. Default is 0. See help on kVA and Cfactor and kWhDays.'
        self.propertyHelp[28] = 'Length of kWh billing period in days (24 hr days). Default is 30. Average demand is computed using this value.'
        # kwh billing period (24-hr days)
        self.propertyHelp[29] = 'Factor relating average kW to peak kW. Default is 4.0. See kWh and kWhdays. See kVA.'
        # multiplier from kWh avg to peak kW
        self.propertyHelp[30] = 'Default is NONE. Curve describing both watt and var factors as a function of time. ' + 'Refers to a LoadShape object with both Mult and Qmult defined. ' + 'Define a Loadshape to agree with yearly or daily curve according to the type of analysis being done. ' + 'If NONE, the CVRwatts and CVRvars factors are used and assumed constant.'
        self.propertyHelp[31] = 'Number of customers, this load. Default is 1.'
        self.propertyHelp[32] = 'Array of 7 coefficients:' + DSSGlobals.CRLF + ' First 3 are ZIP weighting factors for real power (should sum to 1)' + DSSGlobals.CRLF + ' Next 3 are ZIP weighting factors for reactive power (should sum to 1)' + DSSGlobals.CRLF + ' Last 1 is cut-off voltage in p.u. of base kV; load is 0 below this cut-off' + DSSGlobals.CRLF + ' No defaults; all coefficients must be specified if using model=8.'
        self.activeProperty = self.NumPropsThisClass - 1
        super(LoadImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        self.propertyHelp[self.NumPropsThisClass - 1] = 'Name of harmonic current spectrum for this load.  Default is \"defaultload\", which is defined when the DSS starts.'

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(LoadObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def setNcondsForConnection(self):
        al = self.activeLoadObj
        _0 = al.getConnection()
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                al.setNConds(al.getNPhases() + 1)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                _2 = al.getNPhases()
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        al.setNConds(al.getNPhases() + 1)
                        # L-L
                        break
                    if (_3 is True) or (_2 == 2):
                        _3 = True
                        al.setNConds(al.getNPhases() + 1)
                        # open-delta
                        break
                    if True:
                        _3 = True
                        al.setNConds(al.getNPhases())
                        break
                    break
                break
            break

    def interpretConnection(self, s):
        """Accepts (checks only min number of chars required):
        		delta or LL           (Case insensitive)
        		Y, wye, or LN
        """
        al = self.activeLoadObj
        testS = s.toLowerCase()
        _0 = testS[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                al.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                al.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                al.setConnection(1)
                # Delta or Line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = testS[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        al.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        al.setConnection(1)
                        break
                    break
                break
            break
        self.setNcondsForConnection()
        _4 = al.getConnection()
        _5 = False
        while True:
            if _4 == 1:
                _5 = True
                al.setVBase(al.getKVLoadBase() * 1000.0)
            if True:
                _5 = True
                _6 = al.getNPhases()
                _7 = False
                while True:
                    if _6 == 2:
                        _7 = True
                        al.setVBase(al.getKVLoadBase() * DSSGlobals.InvSQRT3x1000)
                        break
                    if (_7 is True) or (_6 == 3):
                        _7 = True
                        al.setVBase(al.getKVLoadBase() * DSSGlobals.InvSQRT3x1000)
                        break
                    if True:
                        _7 = True
                        al.setVBase(al.getKVLoadBase() * 1000.0)
                        break
                    break
                break
            break
        al.setVBase95(al.getVMinPU() * al.getVBase())
        al.setVBase105(al.getVMaxPU() * al.getVBase())
        al.setYorder(al.getNConds() * al.getNTerms())
        al.setYPrimInvalid(True)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeLoadObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeLoadObj)
        result = 0
        al = self.activeLoadObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                al.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + al.getName() + '\"', 580)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    al.setNPhases(parser.makeInteger())
                    # num phases
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    al.setBus(1, param)
                    # TODO: Check zero based indexing
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    al.setKVLoadBase(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    al.setKWBase(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    al.setPFNominal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    al.setLoadModel(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    al.setYearlyShape(param)
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    al.setDailyShape(param)
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    al.setDutyShape(param)
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    al.setGrowthShape(param)
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    self.interpretConnection(param)
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    al.setKVArBase(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    al.setRNeut(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    al.setXNeut(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    _2 = param.toLowerCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'f':
                            _3 = True
                            al.setFixed(True)
                            al.setExemptFromLDCurve(False)
                            break
                        if (_3 is True) or (_2 == 'e'):
                            _3 = True
                            al.setFixed(False)
                            al.setExemptFromLDCurve(True)
                            break
                        if True:
                            _3 = True
                            al.setFixed(False)
                            al.setExemptFromLDCurve(False)
                            break
                        break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    al.setLoadClass(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    al.setVMinPU(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    al.setVMaxPU(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    al.setVMinNormal(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    al.setVMinEmerg(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    al.setConnectedKVA(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    al.setKVAAllocationFactor(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 22):
                    _1 = True
                    al.setKVABase(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 23):
                    _1 = True
                    al.setPUMean(parser.makeDouble() / 100.0)
                    break
                if (_1 is True) or (_0 == 24):
                    _1 = True
                    al.setPUStdDev(parser.makeDouble() / 100.0)
                    break
                if (_1 is True) or (_0 == 25):
                    _1 = True
                    al.setCVRWattFactor(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 26):
                    _1 = True
                    al.setCVRVArFactor(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 27):
                    _1 = True
                    al.setKWh(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 28):
                    _1 = True
                    al.setKWhDays(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 29):
                    _1 = True
                    al.setCFactor(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 30):
                    _1 = True
                    al.setCVRShape(param)
                    break
                if (_1 is True) or (_0 == 31):
                    _1 = True
                    al.setNumCustomers(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 32):
                    _1 = True
                    al.setZIPVSize(7)
                    parser.parseAsVector(7, al.getZIPV())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeLoadObj, paramPointer - Load.NumPropsThisClass)
                    break
                break
            # side effects
            # keep kvar nominal up to date with kW and PF
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 0:
                    _5 = True
                    self.setNcondsForConnection()
                    # force reallocation of terminal info
                    al.updateVoltageBases()
                    break
                if (_5 is True) or (_4 == 2):
                    _5 = True
                    al.updateVoltageBases()
                    break
                if (_5 is True) or (_4 == 3):
                    _5 = True
                    al.setLoadSpecType(0)
                    break
                if (_5 is True) or (_4 == 4):
                    _5 = True
                    al.setPFChanged(True)
                    break
                if (_5 is True) or (_4 == 6):
                    _5 = True
                    al.setYearlyShapeObj(DSSGlobals.loadShapeClass.find(al.getYearlyShape()))
                    if al.getYearlyShapeObj() is not None:
                        if al.getYearlyShapeObj().isUseActual():
                            al.setKW_KVAr(al.getYearlyShapeObj().getMaxP(), al.getYearlyShapeObj().getMaxQ())
                    break
                if (_5 is True) or (_4 == 7):
                    _5 = True
                    al.setDailyShapeObj(DSSGlobals.loadShapeClass.find(al.getDailyShape()))
                    if al.getDailyShapeObj() is not None:
                        if al.getDailyShapeObj().isUseActual():
                            al.setKW_KVAr(al.getDailyShapeObj().getMaxP(), al.getDailyShapeObj().getMaxQ())
                            # If yearly load shape is not yet defined, make it the same as daily
                    if al.getYearlyShapeObj() is None:
                        al.setYearlyShapeObj(al.getDailyShapeObj())
                    break
                if (_5 is True) or (_4 == 8):
                    _5 = True
                    al.setDutyShapeObj(DSSGlobals.loadShapeClass.find(al.getDutyShape()))
                    if al.getDutyShapeObj() is not None:
                        if al.getDutyShapeObj().isUseActual():
                            al.setKW_KVAr(al.getDutyShapeObj().getMaxP(), al.getDutyShapeObj().getMaxQ())
                    break
                if (_5 is True) or (_4 == 9):
                    _5 = True
                    al.setGrowthShapeObj(DSSGlobals.growthShapeClass.find(al.getGrowthShape()))
                    break
                if (_5 is True) or (_4 == 11):
                    _5 = True
                    al.setLoadSpecType(1)
                    # kW, kvar
                    break
                if (_5 is True) or (_4 == 22):
                    _5 = True
                    al.setLoadSpecType(2)
                    # kVA, PF
                    break
                if (_5 is True) or (_4 == 30):
                    _5 = True
                    al.setCVRShapeObj(DSSGlobals.loadShapeClass.find(al.getCVRShape()))
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        al.recalcElementData()
        al.setYPrimInvalid(True)
        return result

    def makeLike(self, otherLoadName):
        result = 0
        # See if we can find this line name in the present collection
        otherLoad = self.find(otherLoadName)
        if otherLoad is not None:
            al = self.activeLoadObj
            if al.getNPhases() != otherLoad.getNPhases():
                al.setNPhases(otherLoad.getNPhases())
                al.setNConds(al.getNPhases())
                # forces reallocation of terminal stuff
                al.setYorder(al.getNConds() * al.getNTerms())
                al.setYPrimInvalid(True)
            al.setKVLoadBase(otherLoad.getKVLoadBase())
            al.setVBase(otherLoad.getVBase())
            al.setVMinPU(otherLoad.getVMinPU())
            al.setVMaxPU(otherLoad.getVMaxPU())
            al.setVBase95(otherLoad.getVBase95())
            al.setVBase105(otherLoad.getVBase105())
            al.setKWBase(otherLoad.getKWBase())
            al.setKVABase(otherLoad.getKVABase())
            al.setKVArBase(otherLoad.getKVArBase())
            al.setLoadSpecType(otherLoad.getLoadSpecType())
            al.setWNominal(otherLoad.getWNominal())
            al.setPFNominal(otherLoad.getPFNominal())
            al.setVArNominal(otherLoad.getVArNominal())
            al.setConnection(otherLoad.getConnection())
            al.setRNeut(otherLoad.getRNeut())
            al.setXNeut(otherLoad.getXNeut())
            al.setYearlyShape(otherLoad.getYearlyShape())
            al.setYearlyShapeObj(otherLoad.getYearlyShapeObj())
            al.setCVRShape(otherLoad.getCVRShape())
            al.setCVRShapeObj(otherLoad.getCVRShapeObj())
            al.setDailyShape(otherLoad.getDailyShape())
            al.setDailyShapeObj(otherLoad.getDailyShapeObj())
            al.setDutyShape(otherLoad.getDutyShape())
            al.setDutyShapeObj(otherLoad.getDutyShapeObj())
            al.setGrowthShape(otherLoad.getGrowthShape())
            al.setGrowthShapeObj(otherLoad.getGrowthShapeObj())
            # al.setSpectrum(OtherLoad.getSpectrum();  in base class now
            # al.setSpectrumObj(OtherLoad.getSpectrumObj());
            al.setLoadClass(otherLoad.getLoadClass())
            al.setNumCustomers(otherLoad.getNumCustomers())
            al.setLoadModel(otherLoad.getLoadModel())
            al.setFixed(otherLoad.isFixed())
            al.setExemptFromLDCurve(otherLoad.isExemptFromLDCurve())
            al.setKVAAllocationFactor(otherLoad.getKVAAllocationFactor())
            al.setConnectedKVA(otherLoad.getConnectedKVA())
            al.setCVRWattFactor(otherLoad.getCVRWattFactor())
            al.setCVRVArFactor(otherLoad.getCVRVArFactor())
            al.setShapeIsActual(otherLoad.shapeIsActual())
            al.setZIPVSize(otherLoad.getNZIPV())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < al.getNZIPV()):
                    break
                al.getZIPV()[i] = otherLoad.getZIPV()[i]
            self.classMakeLike(otherLoad)
            # take care of inherited class properties
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < al.getParentClass().getNumProperties()):
                    break
                al.setPropertyValue(i, otherLoad.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Load makeLike: \"' + otherLoadName + '\" not found.', 581)
        return result

    def init(self, handle):
        if handle == 0:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(self.elementList)):
                    break
                pLoad = self.elementList.get(i)
                pLoad.randomize(0)
        else:
            self.setActiveElement(handle)
            pLoad = self.getActiveObj()
            pLoad.randomize(0)
        DSSGlobals.doSimpleMsg('Need to finish implementation Load.init', -1)
        return 0
