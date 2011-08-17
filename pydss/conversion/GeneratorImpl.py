from dss.conversion.Generator import (Generator,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.conversion.impl.GeneratorObjImpl import (GeneratorObjImpl,)
from dss.parser.impl.Parser import (Parser,)
# from java.io.BufferedWriter import (BufferedWriter,)
# from java.io.File import (File,)
# from java.io.FileWriter import (FileWriter,)


class GeneratorImpl(PCClassImpl, Generator):
    activeGeneratorObj = None
    registerNames = [None] * Generator.NumGenRegisters

    def __init__(self):
        super(GeneratorImpl, self)()
        self.className = 'Generator'
        self.classType = self.classType + DSSClassDefs.GEN_ELEMENT
        # in both PCElement and GenElement list
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
        self.numProperties = Generator.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # see DSSClass
        # define property names
        self.addProperty('phases', 0, 'Number of Phases, this Generator.  Power is evenly divided among phases.')
        self.addProperty('bus1', 1, 'Bus to which the Generator is connected.  May include specific node specification.')
        self.addProperty('kv', 2, 'Nominal rated (1.0 per unit) voltage, kV, for Generator. For 2- and 3-phase Generators, specify phase-phase kV. ' + 'Otherwise, specify actual kV across each branch of the Generator. ' + 'If wye (star), specify phase-neutral kV. ' + 'If delta or phase-phase connected, specify phase-phase kV.')
        # line-neutral voltage//  base voltage
        self.addProperty('kW', 3, 'Total base kW for the Generator.  A positive value denotes power coming OUT of the element, ' + DSSGlobals.CRLF + 'which is the opposite of a load. This value is modified depending on the dispatch mode. ' + 'Unaffected by the global load multiplier and growth curves. ' + 'If you want there to be more generation, you must add more generators or change this value.')
        self.addProperty('pf', 4, 'Generator power factor. Default is 0.80. Enter negative for leading powerfactor ' + '(when kW and kvar have opposite signs.)' + DSSGlobals.CRLF + 'A positive power factor for a generator signifies that the generator produces vars ' + DSSGlobals.CRLF + 'as is typical for a synchronous generator.  Induction machines would be ' + DSSGlobals.CRLF + 'specified with a negative power factor.')
        self.addProperty('kvar', 12, 'Specify the base kvar.  Alternative to specifying the power factor.  Side effect: ' + ' the power factor value is altered to agree based on present value of kW.')
        self.addProperty('model', 5, 'Integer code for the model to use for generation variation with voltage. ' + 'Valid values are:' + DSSGlobals.CRLF + DSSGlobals.CRLF + '1:Generator injects a constant kW at specified power factor.' + DSSGlobals.CRLF + '2:Generator is modeled as a constant admittance.' + DSSGlobals.CRLF + '3:Const kW, constant kV.  Somewhat like a conventional transmission power flow P-V generator.' + DSSGlobals.CRLF + '4:Const kW, Fixed Q (Q never varies)' + DSSGlobals.CRLF + '5:Const kW, Fixed Q(as a constant reactance)' + DSSGlobals.CRLF + '6:Compute load injection from User-written Model.(see usage of Xd, Xdp)' + DSSGlobals.CRLF + '7:Constant kW, kvar, but current limited below Vminpu')
        self.addProperty('Vminpu', 22, 'Default = 0.90.  Minimum per unit voltage for which the Model is assumed to apply. ' + 'Below this value, the load model reverts to a constant impedance model.')
        self.addProperty('Vmaxpu', 23, 'Default = 1.10.  Maximum per unit voltage for which the Model is assumed to apply. ' + 'Above this value, the load model reverts to a constant impedance model.')
        self.addProperty('yearly', 6, 'Dispatch shape to use for yearly simulations.  Must be previously defined ' + 'as a Loadshape object. If this is not specified, a constant value is assumed (no variation). ' + 'If the generator is assumed to be ON continuously, specify Status=FIXED, or ' + 'designate a curve that is 1.0 per unit at all times. ' + 'Set to NONE to reset to no loadahape. ' + 'Nominally for 8760 simulations.  If there are fewer points in the designated shape than ' + 'the number of points in the solution, the curve is repeated.')
        self.addProperty('daily', 7, 'Dispatch shape to use for daily simulations.  Must be previously defined ' + 'as a Loadshape object of 24 hrs, typically.  If generator is assumed to be ' + 'ON continuously, specify Status=FIXED, or designate a Loadshape object' + 'that is 1.0 perunit for all hours. ' + 'Set to NONE to reset to no loadahape. ')
        # daily dispatch (hourly)
        self.addProperty('duty', 8, 'Load shape to use for duty cycle dispatch simulations such as for wind generation. ' + 'Must be previously defined as a Loadshape object. ' + 'Typically would have time intervals less than 1 hr -- perhaps, in seconds. ' + 'Set Status=Fixed to ignore Loadshape designation. ' + 'Set to NONE to reset to no loadahape. ' + 'Designate the number of points to solve using the Set Number=xxxx command. ' + 'If there are fewer points in the actual shape, the shape is assumed to repeat.')
        # as for wind generation
        self.addProperty('dispmode', 9, '{Default* | Loadlevel | Price } Default = Default. Dispatch mode. ' + 'In default mode, gen is either always on or follows dispatch curve as specified. ' + 'Otherwise, the gen comes on when either the global default load level (Loadshape \"default\") or the price level ' + 'exceeds the dispatch value.')
        # = 0 | >0
        self.addProperty('dispvalue', 10, 'Dispatch value. ' + DSSGlobals.CRLF + 'If = 0.0 (default) then Generator follow dispatch curves, if any. ' + DSSGlobals.CRLF + 'If > 0  then Generator is ON only when either the price signal (in Price dispatch mode) ' + 'exceeds this value or the active circuit load multiplier * \"default\" loadshape value * the default yearly growth factor ' + 'exceeds this value.  Then the generator follows dispatch curves (duty, daily, or yearly), if any (see also Status).')
        # = 0 | >0
        self.addProperty('conn', 11, '={wye|LN|delta|LL}.  Default is wye.')
        self.addProperty('Rneut', 13, 'Removed due to causing confusion - Add neutral impedance externally.')
        self.addProperty('Xneut', 14, 'Removed due to causing confusion - Add neutral impedance externally.')
        self.addProperty('status', 15, '={Fixed | Variable*}.  If Fixed, then dispatch multipliers do not apply. ' + 'The generator is alway at full power when it is ON. ' + ' Default is Variable  (follows curves).')
        # fixed or variable
        self.addProperty('class', 16, 'An arbitrary integer number representing the class of Generator so that Generator values may ' + 'be segregated by class.')
        # integer
        self.addProperty('Vpu', 17, 'Per Unit voltage set point for Model = 3  (typical power flow model).  Default is 1.0. ')
        # per unit set point voltage for power flow model
        self.addProperty('maxkvar', 18, 'Maximum kvar limit for Model = 3.  Defaults to twice the specified load kvar.  ' + 'Always reset this if you change PF or kvar properties.')
        self.addProperty('minkvar', 19, 'Minimum kvar limit for Model = 3. Enter a negative number if generator can absorb vars.' + ' Defaults to negative of Maxkvar.  Always reset this if you change PF or kvar properties.')
        self.addProperty('pvfactor', 20, 'Deceleration factor for P-V generator model (Model=3).  Default is 0.1. ' + 'If the circuit converges easily, you may want to use a higher number such as 1.0. ' + 'Use a lower number if solution diverges. Use Debugtrace=yes to create a file that will ' + 'trace the convergence of a generator model.')
        self.addProperty('forceon', 24, '{Yes | No}  Forces generator ON despite requirements of other dispatch modes. ' + 'Stays ON until this property is set to NO, or an internal algorithm cancels the forced ON state.')
        self.addProperty('kVA', 25, 'kVA rating of electrical machine. Defaults to 1.2* kW if not specified. Applied to machine or inverter definition for Dynamics mode solutions. ')
        self.addProperty('MVA', 26, 'MVA rating of electrical machine.  Alternative to using kVA=.')
        self.addProperty('Xd', 27, 'Per unit synchronous reactance of machine. Presently used only for Thevinen impedance for power flow calcs of user models (model=6). ' + 'Typically use a value 0.4 to 1.0. Default is 1.0')
        self.addProperty('Xdp', 28, 'Per unit transient reactance of the machine.  Used for Dynamics mode and Fault studies.  Default is 0.27.' + 'For user models, this value is used for the Thevinen/Norton impedance for Dynamics Mode.')
        self.addProperty('Xdpp', 29, 'Per unit subtransient reactance of the machine.  Used for Harmonics. Default is 0.20.')
        self.addProperty('H', 30, 'Per unit mass constant of the machine.  MW-sec/MVA.  Default is 1.0.')
        self.addProperty('D', 31, 'Damping constant.  Usual range is 0 to 4. Default is 1.0.  Adjust to get damping')
        self.addProperty('UserModel', 32, 'Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, ' + 'overriding the default model.  Set to \"none\" to negate previous setting.')
        self.addProperty('UserData', 33, 'String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.')
        self.addProperty('ShaftModel', 34, 'Name of user-written DLL containing a Shaft model, which models the prime mover and determines the power on the shaft for Dynamics studies. ' + 'Models additional mass elements other than the single-mass model in the DSS default model. Set to \"none\" to negate previous setting.')
        self.addProperty('ShaftData', 35, 'String (in quotes or parentheses) that gets passed to user-written shaft dynamic model for defining the data for that model.')
        self.addProperty('debugtrace', 21, '{Yes | No }  Default is no.  Turn this on to capture the progress of the generator model ' + 'for each iteration.  Creates a separate file for each generator named \"GEN_name.CSV\".')
        self.activeProperty = self.NumPropsThisClass - 1
        super(GeneratorImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override default help string
        self.propertyHelp[Generator.NumPropsThisClass] = 'Name of harmonic voltage or current spectrum for this generator. ' + 'Voltage behind Xd' for machine - default. Current injection for inverter. ' + 'Default value is \"default\", which is defined when the DSS starts.'

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(GeneratorObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def setNcondsForConnection(self):
        ag = self.activeGeneratorObj
        _0 = ag.getConnection()
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                ag.setNConds(ag.getNPhases() + 1)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                _2 = ag.getNPhases()
                _3 = False
                while True:
                    if _2 == 1:
                        _3 = True
                        ag.setNConds(ag.getNPhases() + 1)
                        # L-L
                        break
                    if (_3 is True) or (_2 == 2):
                        _3 = True
                        ag.setNConds(ag.getNPhases() + 1)
                        # open-delta
                        break
                    if True:
                        _3 = True
                        ag.setNConds(ag.getNPhases())
                        break
                    break
                break
            break

    def interpretConnection(self, s):
        """Accepts (case insensitive):
        		delta or LL
        		Y, wye, or LN
        """
        ag = self.activeGeneratorObj
        testS = s.toLowerCase()
        _0 = testS[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                ag.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                ag.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                ag.setConnection(1)
                # Delta or line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = testS[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        ag.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        ag.setConnection(1)
                        break
                    break
                break
            break
        self.setNcondsForConnection()
        # VBase is always L-N voltage unless 1-phase device or more than 3 phases
        _4 = ag.getNPhases()
        _5 = False
        while True:
            if _4 == 2:
                _5 = True
                ag.setVBase(ag.getGenVars().kVGeneratorBase * DSSGlobals.InvSQRT3x1000)
                # L-N Volts
                break
            if (_5 is True) or (_4 == 3):
                _5 = True
                ag.setVBase(ag.getGenVars().kVGeneratorBase * DSSGlobals.InvSQRT3x1000)
                # L-N Volts
                break
            if True:
                _5 = True
                ag.setVBase(ag.getGenVars().kVGeneratorBase * 1000.0)
                # just use what is supplied
                break
            break
        ag.setVBase95(ag.getVMinPU() * ag.getVBase())
        ag.setVBase105(ag.getVMaxPU() * ag.getVBase())
        ag.setYorder(ag.getNConds() * ag.getNTerms())
        ag.setYPrimInvalid(True)

    @classmethod
    def interpretDispMode(cls, s):
        _0 = s.toLowerCase()[0]
        _1 = False
        while True:
            if _0 == 'l':
                _1 = True
                return Generator.LOADMODE
            if (_1 is True) or (_0 == 'p'):
                _1 = True
                return Generator.PRICEMODE
            if True:
                _1 = True
                return Generator.DEFAULT
            break

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeGeneratorObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeGeneratorObj)
        result = 0
        ag = self.activeGeneratorObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                ag.setPropertyValue(self.propertyIdxMap[paramPointer], param)
            else:
                DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for generator \"' + ag.getName() + '\"', 560)
            if paramPointer >= 0:
                _0 = self.propertyIdxMap[paramPointer]
                _1 = False
                while True:
                    if _0 == -1:
                        _1 = True
                        DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + ag.getName() + '\"', 561)
                        break
                    if (_1 is True) or (_0 == 0):
                        _1 = True
                        ag.setNPhases(parser.makeInteger())
                        # num phases
                        break
                    if (_1 is True) or (_0 == 1):
                        _1 = True
                        ag.setBus(1, param)
                        # TODO Check zero based indexing
                        break
                    if (_1 is True) or (_0 == 2):
                        _1 = True
                        ag.setPresentKV(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 3):
                        _1 = True
                        ag.setKWBase(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 4):
                        _1 = True
                        ag.setPowerFactor(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 5):
                        _1 = True
                        ag.setGenModel(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 6):
                        _1 = True
                        ag.setYearlyShape(param)
                        break
                    if (_1 is True) or (_0 == 7):
                        _1 = True
                        ag.setDailyDispShape(param)
                        break
                    if (_1 is True) or (_0 == 8):
                        _1 = True
                        ag.setDutyShape(param)
                        break
                    if (_1 is True) or (_0 == 9):
                        _1 = True
                        ag.setDispatchMode(self.interpretDispMode(param))
                        break
                    if (_1 is True) or (_0 == 10):
                        _1 = True
                        ag.setDispatchValue(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 11):
                        _1 = True
                        self.interpretConnection(param)
                        break
                    if (_1 is True) or (_0 == 12):
                        _1 = True
                        ag.setPresentKVAr(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 13):
                        _1 = True
                        DSSGlobals.doSimpleMsg('Rneut property has been deleted. Use external impedance.', 5611)
                        break
                    if (_1 is True) or (_0 == 14):
                        _1 = True
                        DSSGlobals.doSimpleMsg('Xneut property has been deleted. Use external impedance.', 5612)
                        break
                    if (_1 is True) or (_0 == 15):
                        _1 = True
                        if param.toLowerCase()[0] == 'f':
                            ag.setFixed(True)
                        else:
                            ag.setFixed(False)
                        break
                    if (_1 is True) or (_0 == 16):
                        _1 = True
                        ag.setGenClass(parser.makeInteger())
                        break
                    if (_1 is True) or (_0 == 17):
                        _1 = True
                        ag.setVpu(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 18):
                        _1 = True
                        ag.setKVArMax(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 19):
                        _1 = True
                        ag.setKVArMin(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 20):
                        _1 = True
                        ag.setPVFactor(parser.makeDouble())
                        # declaration factor
                        break
                    if (_1 is True) or (_0 == 21):
                        _1 = True
                        ag.setDebugTrace(Utilities.interpretYesNo(param))
                        break
                    if (_1 is True) or (_0 == 22):
                        _1 = True
                        ag.setVMinPU(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 23):
                        _1 = True
                        ag.setVMaxPU(parser.makeDouble())
                        break
                    if (_1 is True) or (_0 == 24):
                        _1 = True
                        ag.setForcedOn(Utilities.interpretYesNo(param))
                        break
                    if (_1 is True) or (_0 == 25):
                        _1 = True
                        ag.getGenVars().kVARating = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 26):
                        _1 = True
                        ag.getGenVars().kVARating = parser.makeDouble() * 1000.0
                        # "MVA";
                        break
                    if (_1 is True) or (_0 == 27):
                        _1 = True
                        ag.getGenVars().puXd = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 28):
                        _1 = True
                        ag.getGenVars().puXdp = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 29):
                        _1 = True
                        ag.getGenVars().puXdpp = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 30):
                        _1 = True
                        ag.getGenVars().HMass = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 31):
                        _1 = True
                        ag.getGenVars().Dpu = parser.makeDouble()
                        break
                    if (_1 is True) or (_0 == 32):
                        _1 = True
                        ag.getUserModel().setName(parser.makeString())
                        # connect to user written models
                        break
                    if (_1 is True) or (_0 == 33):
                        _1 = True
                        ag.getUserModel().edit(parser.makeString())
                        # send edit string to user model
                        break
                    if (_1 is True) or (_0 == 34):
                        _1 = True
                        ag.getShaftModel().setName(parser.makeString())
                        break
                    if (_1 is True) or (_0 == 35):
                        _1 = True
                        ag.getShaftModel().edit(parser.makeString())
                        break
                    if True:
                        _1 = True
                        self.classEdit(self.activeGeneratorObj, paramPointer - Generator.NumPropsThisClass)
                        break
                    break
            if paramPointer >= 0:
                _2 = self.propertyIdxMap[paramPointer]
                _3 = False
                while True:
                    if _2 == 0:
                        _3 = True
                        self.setNcondsForConnection()
                        # force reallocation of terminal info
                        break
                    if (_3 is True) or (_2 == 3):
                        _3 = True
                        ag.syncUpPowerQuantities()
                        break
                    if (_3 is True) or (_2 == 4):
                        _3 = True
                        ag.syncUpPowerQuantities()
                        break
                    if (_3 is True) or (_2 == 6):
                        _3 = True
                        ag.setYearlyShapeObj(DSSGlobals.loadShapeClass.find(ag.getYearlyShape()))
                        if ag.getYearlyShape() is not None:
                            if ag.getYearlyShapeObj().isUseActual():
                                ag.setKwKVAr(ag.getYearlyShapeObj().getMaxP(), ag.getYearlyShapeObj().getMaxQ())
                        break
                    if (_3 is True) or (_2 == 7):
                        _3 = True
                        ag.setDailyDispShapeObj(DSSGlobals.loadShapeClass.find(ag.getDailyDispShape()))
                        if ag.getDailyDispShapeObj() is not None:
                            if ag.getDailyDispShapeObj().isUseActual():
                                ag.setKwKVAr(ag.getDailyDispShapeObj().getMaxP(), ag.getDailyDispShapeObj().getMaxQ())
                        break
                    if (_3 is True) or (_2 == 8):
                        _3 = True
                        ag.setDutyShapeObj(DSSGlobals.loadShapeClass.find(ag.getDutyShape()))
                        if ag.getDutyShapeObj() is not None:
                            if ag.getDutyShapeObj().isUseActual():
                                ag.setKwKVAr(ag.getDutyShapeObj().getMaxP(), ag.getDutyShapeObj().getMaxQ())
                        break
                    if (_3 is True) or (_2 == 21):
                        _3 = True
                        if ag.isDebugTrace():
                            # TODO: handle exception
                            try:
                                TraceFile = File(DSSGlobals.DSSDataDirectory + 'GEN_' + ag.getName() + '.csv')
                                TraceStream = FileWriter(TraceFile, False)
                                TraceBuffer = BufferedWriter(TraceStream)
                                TraceBuffer.write('t, Iteration, LoadMultiplier, Mode, LoadModel, GenModel, dQdV, Avg_Vpu, Vdiff, MQnominalperphase, MPnominalperphase, CurrentType')
                                _4 = True
                                i = 0
                                while True:
                                    if _4 is True:
                                        _4 = False
                                    else:
                                        i += 1
                                    if not (i < ag.getNPhases()):
                                        break
                                    TraceBuffer.write(', |Iinj' + String.valueOf.valueOf(i) + '|')
                                _5 = True
                                i = 0
                                while True:
                                    if _5 is True:
                                        _5 = False
                                    else:
                                        i += 1
                                    if not (i < ag.getNPhases()):
                                        break
                                    TraceBuffer.write(', |Iterm' + String.valueOf.valueOf(i) + '|')
                                _6 = True
                                i = 0
                                while True:
                                    if _6 is True:
                                        _6 = False
                                    else:
                                        i += 1
                                    if not (i < ag.getNPhases()):
                                        break
                                    TraceBuffer.write(', |Vterm' + String.valueOf.valueOf(i) + '|')
                                TraceBuffer.write(',Vthev, Theta')
                                TraceBuffer.newLine()
                                TraceBuffer.close()
                                TraceStream.close()
                            except Exception, e:
                                pass # astStmt: [Stmt([]), None]
                        break
                    if (_3 is True) or (_2 == 25):
                        _3 = True
                        ag.setkVANotSet(False)
                        break
                    if (_3 is True) or (_2 == 26):
                        _3 = True
                        ag.setkVANotSet(False)
                        break
                    break
            paramName = parser.getNextParam()
            param = parser.makeString()
        ag.recalcElementData()
        ag.setYPrimInvalid(True)
        return result

    def makeLike(self, otherGeneratorName):
        result = 0
        # See if we can find this line name in the present collection
        otherGenerator = self.find(otherGeneratorName)
        if otherGenerator is not None:
            ag = self.activeGeneratorObj
            if ag.getNPhases() != otherGenerator.getNPhases():
                ag.setNPhases(otherGenerator.getNPhases())
                ag.setNConds(ag.getNPhases())
                # forces reallocation of terminal stuff
                ag.setYorder(ag.getNConds() * ag.getNTerms())
                ag.setYPrimInvalid(True)
            ag.getGenVars().kVGeneratorBase = otherGenerator.getGenVars().kVGeneratorBase
            ag.setVBase(otherGenerator.getVBase())
            ag.setVMinPU(otherGenerator.getVMinPU())
            ag.setVMaxPU(otherGenerator.getVMaxPU())
            ag.setVBase95(otherGenerator.getVBase95())
            ag.setVBase105(otherGenerator.getVBase105())
            ag.setKWBase(otherGenerator.getKWBase())
            ag.setKVArBase(otherGenerator.getKVArBase())
            ag.getGenVars().PNominalPerPhase = otherGenerator.getGenVars().PNominalPerPhase
            ag.setPowerFactor(otherGenerator.getPowerFactor())
            ag.getGenVars().QNominalPerPhase = otherGenerator.getGenVars().QNominalPerPhase
            ag.setVArMin(otherGenerator.getVArMin())
            ag.setVArMax(otherGenerator.getVArMax())
            ag.setConnection(otherGenerator.getConnection())
            # ag.setRneut(OtherGenerator.getRneut());
            # ag.setXneut(OtherGenerator.getXneut());
            ag.setYearlyShape(otherGenerator.getYearlyShape())
            ag.setYearlyShapeObj(otherGenerator.getYearlyShapeObj())
            ag.setDailyDispShape(otherGenerator.getDailyDispShape())
            ag.setDailyDispShapeObj(otherGenerator.getDailyDispShapeObj())
            ag.setDutyShape(otherGenerator.getDutyShape())
            ag.setDutyShapeObj(otherGenerator.getDutyShapeObj())
            ag.setDispatchMode(otherGenerator.getDispatchMode())
            ag.setDispatchValue(otherGenerator.getDispatchValue())
            ag.setGenClass(otherGenerator.getGenClass())
            ag.setGenModel(otherGenerator.getGenModel())
            ag.setFixed(otherGenerator.isFixed())
            ag.setVTarget(otherGenerator.getVTarget())
            ag.setVpu(otherGenerator.getVpu())
            ag.setKVArMax(otherGenerator.getKVArMax())
            ag.setKVArMin(otherGenerator.getKVArMin())
            ag.setForcedOn(otherGenerator.isForcedOn())
            ag.setkVANotSet(otherGenerator.iskVANotSet())
            ag.getGenVars().kVARating = otherGenerator.getGenVars().kVARating
            ag.getGenVars().puXd = otherGenerator.getGenVars().puXd
            ag.getGenVars().puXdp = otherGenerator.getGenVars().puXdp
            ag.getGenVars().puXdpp = otherGenerator.getGenVars().puXdpp
            ag.getGenVars().HMass = otherGenerator.getGenVars().HMass
            ag.getGenVars().theta = otherGenerator.getGenVars().theta
            ag.getGenVars().speed = otherGenerator.getGenVars().speed
            ag.getGenVars().w0 = otherGenerator.getGenVars().w0
            ag.getGenVars().dSpeed = otherGenerator.getGenVars().dSpeed
            ag.getGenVars().D = otherGenerator.getGenVars().D
            ag.getGenVars().Dpu = otherGenerator.getGenVars().Dpu
            ag.getUserModel().setName(otherGenerator.getUserModel().getName())
            # connect to user written models
            ag.getShaftModel().setName(otherGenerator.getShaftModel().getName())
            self.classMakeLike(otherGenerator)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ag.getParentClass().getNumProperties()):
                    break
                ag.setPropertyValue(i, otherGenerator.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Load makeLike: \"' + otherGeneratorName + '\" not found.', 562)
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
                p = self.elementList.get(i)
                p.randomize(0)
        else:
            self.setActiveElement(handle)
            p = self.getActiveObj()
            p.randomize(0)
        DSSGlobals.doSimpleMsg('Need to implement Generator.init()', -1)
        return 0

    def resetRegistersAll(self):
        """Force all EnergyMeters in the circuit to reset."""
        for pGen in DSSGlobals.activeCircuit.getGenerators():
            pGen.resetRegisters()

    def sampleAll(self):
        """Force all EnergyMeters in the circuit to take a sample."""
        for pGen in DSSGlobals.activeCircuit.getGenerators():
            pGen.takeSample()

    def getRegisterNames(self):
        return self.registerNames

    def setRegisterNames(self, names):
        self.registerNames = names
