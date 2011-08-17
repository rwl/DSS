from dss.common.impl.Utilities import (Utilities,)
from dss.control.RegControl import (RegControl,)
from dss.control.impl.GenDispatcherObjImpl import (GenDispatcherObjImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)


class RegControlImpl(ControlClassImpl, RegControl):
    activeRegControlObj = None

    def __init__(self):
        super(RegControlImpl, self)()
        self.className = 'RegControl'
        self.classType = self.classType + DSSClassDefs.REG_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = RegControl.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'transformer'
        self.propertyName[1] = 'winding'
        self.propertyName[2] = 'vreg'
        self.propertyName[3] = 'band'
        self.propertyName[4] = 'ptratio'
        self.propertyName[5] = 'CTprim'
        self.propertyName[6] = 'R'
        self.propertyName[7] = 'X'
        self.propertyName[8] = 'bus'
        self.propertyName[9] = 'delay'
        self.propertyName[10] = 'reversible'
        self.propertyName[11] = 'revvreg'
        self.propertyName[12] = 'revband'
        self.propertyName[13] = 'revR'
        self.propertyName[14] = 'revX'
        self.propertyName[15] = 'tapdelay'
        self.propertyName[16] = 'debugtrace'
        self.propertyName[17] = 'maxtapchange'
        self.propertyName[18] = 'inversetime'
        self.propertyName[19] = 'tapwinding'
        self.propertyName[20] = 'vlimit'
        self.propertyName[21] = 'PTphase'
        self.propertyName[22] = 'revThreshold'
        self.propertyName[23] = 'revDelay'
        self.propertyName[24] = 'revNeutral'
        self.propertyHelp[0] = 'Name of Transformer element to which the RegControl is connected. ' + 'Do not specify the full object name; \"Transformer\" is assumed for ' + 'the object class.  Example:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Transformer=Xfmr1'
        self.propertyHelp[1] = 'Number of the winding of the transformer element that the RegControl is monitoring. ' + '1 or 2, typically.  Side Effect: Sets TAPWINDING property to the same winding.'
        self.propertyHelp[2] = 'Voltage regulator setting, in VOLTS, for the winding being controlled.  Multiplying this ' + 'value times the ptratio should yield the voltage across the WINDING of the controlled transformer.' + ' Default is 120.0'
        self.propertyHelp[3] = 'Bandwidth in VOLTS for the controlled bus (see help for ptratio property).  Default is 3.0'
        self.propertyHelp[4] = 'Ratio of the PT that converts the controlled winding voltage to the regulator voltage. ' + 'Default is 60.  If the winding is Wye, the line-to-neutral voltage is used.  Else, the line-to-line ' + 'voltage is used.'
        self.propertyHelp[5] = 'Rating, in Amperes, of the primary CT rating for converting the line amps to control amps.' + 'The typical default secondary ampere rating is 0.2 Amps (check with manufacturer specs).'
        self.propertyHelp[6] = 'R setting on the line drop compensator in the regulator, expressed in VOLTS.'
        self.propertyHelp[7] = 'X setting on the line drop compensator in the regulator, expressed in VOLTS.'
        self.propertyHelp[8] = 'Name of a bus (busname.nodename) in the system to use as the controlled bus instead of the bus to which the ' + 'transformer winding is connected or the R and X line drop compensator settings.  Do not specify this ' + 'value if you wish to use the line drop compensator settings.  Default is null string. Assumes the base voltage for this ' + 'bus is the same as the transformer winding base specified above. ' + 'Note: This bus (1-phase) WILL BE CREATED by the regulator control upon SOLVE if not defined by some other device. ' + 'You can specify the node of the bus you wish to sample (defaults to 1). ' + 'If specified, the RegControl is redefined as a 1-phase device since only one voltage is used.'
        self.propertyHelp[9] = 'Time delay, in seconds, from when the voltage goes out of band to when the tap changing begins. ' + 'This is used to determine which regulator control will act first. Default is 15.  You may specify any ' + 'floating point number to achieve a model of whatever condition is necessary.'
        self.propertyHelp[10] = '{Yes | No*} Indicates whether or not the regulator can be switched to regulate in the reverse direction. Default is No.' + 'Typically applies only to line regulators and not to LTC on a substation transformer.'
        self.propertyHelp[11] = 'Voltage setting in volts for operation in the reverse direction.'
        self.propertyHelp[12] = 'Bandwidth for operating in the reverse direction.'
        self.propertyHelp[13] = 'R line drop compensator setting for reverse direction.'
        self.propertyHelp[14] = 'X line drop compensator setting for reverse direction.'
        self.propertyHelp[15] = 'Delay in sec between tap changes. Default is 2. This is how long it takes between changes ' + 'after the first change.'
        self.propertyHelp[16] = '{Yes | No*}  Default is no.  Turn this on to capture the progress of the regulator model ' + 'for each control iteration.  Creates a separate file for each RegControl named \"REG_name.CSV\".'
        self.propertyHelp[17] = 'Maximum allowable tap change per control iteration in STATIC control mode.  Default is 16. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Set this to 1 to better approximate actual control action. ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Set this to 0 to fix the tap in the current position.'
        self.propertyHelp[18] = '{Yes | No*} Default is no.  The time delay is adjusted inversely proportional to the amount the voltage is outside the band down to 10%.'
        self.propertyHelp[19] = 'Winding containing the actual taps, if different than the WINDING property. Defaults to the same winding as specified by the WINDING property.'
        self.propertyHelp[20] = 'Voltage Limit for bus to which regulated winding is connected (e.g. first customer). Default is 0.0. ' + 'Set to a value greater then zero to activate this function.'
        self.propertyHelp[21] = 'For multi-phase transformers, the number of the phase being monitored or one of { MAX | MIN} for all phases. Default=1. ' + 'Must be less than or equal to the number of phases. Ignored for regulated bus.'
        self.propertyHelp[22] = 'kW reverse power threshold for reversing the direction of the regulator. Default is 100.0 kw.'
        self.propertyHelp[23] = 'Time Delay in seconds (s) for executing the reversing action once the threshold for reversing has been exceeded. Default is 60 s.'
        self.propertyHelp[24] = '{Yes | No*} Default is no. Set this to Yes if you want the regulator to go to neutral in the reverse direction.'
        self.activeProperty = RegControl.NumPropsThisClass - 1
        super(RegControlImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(GenDispatcherObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeRegControlObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeRegControlObj)
        result = 0
        arc = self.activeRegControlObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                arc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + arc.getName() + '\"', 120)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    arc.setElementName('Transformer.' + param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    arc.setElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    arc.setVReg(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    arc.setBandwidth(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    arc.setPTRatio(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    arc.setCTRating(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    arc.setR(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    arc.setX(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    arc.setRegulatedBus(param)
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    arc.setTimeDelay(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    arc.setReversible(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    arc.setRevVReg(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    arc.setRevBandwidth(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    arc.setRevR(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    arc.setRevX(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    arc.setTapDelay(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    arc.setDebugTrace(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    arc.setTapLimitPerChange(self.Math.max(0, parser.makeInteger()))
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    arc.setInverseTime(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 19):
                    _1 = True
                    arc.setTapWinding(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 20):
                    _1 = True
                    arc.setVLimit(parser.makeDouble())
                    if arc.getVLimit() > 0.0:
                        arc.setVLimitActive(True)
                    else:
                        arc.setVLimitActive(False)
                    break
                if (_1 is True) or (_0 == 21):
                    _1 = True
                    if Utilities.compareTextShortest(param, 'max') == 0:
                        arc.setPTPhase(self.MAXPHASE)
                    elif Utilities.compareTextShortest(param, 'min') == 0:
                        arc.setPTPhase(self.MINPHASE)
                    else:
                        arc.setPTPhase(self.Math.max(1, parser.makeInteger()))
                    break
                if (_1 is True) or (_0 == 22):
                    _1 = True
                    arc.setKWRevPowerThreshold(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 23):
                    _1 = True
                    arc.setRevDelay(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 24):
                    _1 = True
                    arc.setReverseNeutral(Utilities.interpretYesNo(param))
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeRegControlObj, paramPointer - RegControl.NumPropsThisClass)
                    break
                break
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 1:
                    _3 = True
                    arc.setTapWinding(arc.getElementTerminal())
                    # resets if property re-assigned
                    arc.setPropertyValue(19, param)
                    break
                if (_3 is True) or (_2 == 16):
                    _3 = True
                    if arc.isDebugTrace():
                        # TODO: handle exception
                        try:
                            TraceFile = self.File(DSSGlobals.DSSDataDirectory + 'REG_' + arc.getName() + '.csv')
                            TraceStream = self.FileWriter(TraceFile, False)
                            TraceBuffer = self.BufferedWriter(TraceStream)
                            TraceBuffer.write('Hour, Sec, ControlIteration, Iterations, LoadMultiplier, Present Tap, Pending Change, Actual Change, Increment, Min Tap, Max Tap')
                            TraceBuffer.newLine()
                            TraceBuffer.close()
                            TraceStream.close()
                        except Exception, e:
                            pass # astStmt: [Stmt([]), None]
                    break
                if (_3 is True) or (_2 == 23):
                    _3 = True
                    arc.setRevPowerThreshold(arc.getKWRevPowerThreshold() * 1000.0)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        arc.recalcElementData()
        return result

    def makeLike(self, regControlName):
        result = 0
        # See if we can find this RegControl name in the present collection
        otherRegControl = self.find(regControlName)
        if otherRegControl is not None:
            arc = self.activeRegControlObj
            arc.setNPhases(otherRegControl.getNPhases())
            arc.setNConds(otherRegControl.getNConds())
            # force reallocation of terminal stuff
            arc.setElementName(otherRegControl.getElementName())
            arc.setControlledElement(otherRegControl.getControlledElement())
            # pointer to target circuit element
            arc.setElementTerminal(otherRegControl.getElementTerminal())
            arc.setVReg(otherRegControl.getVReg())
            arc.setBandwidth(otherRegControl.getBandwidth())
            arc.setPTRatio(otherRegControl.getPTRatio())
            arc.setCTRating(otherRegControl.getCTRating())
            arc.setR(otherRegControl.getR())
            arc.setX(otherRegControl.getX())
            arc.setRegulatedBus(otherRegControl.getRegulatedBus())
            arc.setTimeDelay(otherRegControl.getTimeDelay())
            arc.setReversible(otherRegControl.isReversible())
            arc.setRevVReg(otherRegControl.getRevVReg())
            arc.setRevBandwidth(otherRegControl.getRevBandwidth())
            arc.setRevR(otherRegControl.getRevR())
            arc.setRevX(otherRegControl.getRevX())
            arc.setTapDelay(otherRegControl.getTapDelay())
            arc.setTapWinding(otherRegControl.getTapWinding())
            arc.setInverseTime(otherRegControl.isInverseTime())
            arc.setTapLimitPerChange(otherRegControl.getTapLimitPerChange())
            arc.setTapLimitPerChange(otherRegControl.getTapLimitPerChange())
            arc.setKWRevPowerThreshold(otherRegControl.getKWRevPowerThreshold())
            arc.setRevPowerThreshold(otherRegControl.getRevPowerThreshold())
            arc.setRevDelay(otherRegControl.getRevDelay())
            arc.setReverseNeutral(otherRegControl.isReverseNeutral())
            # arc.setDebugTrace(OtherRegControl.isDebugTrace();  always default to no
            arc.setPTPhase(otherRegControl.getPTPhase())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < arc.getParentClass().getNumProperties()):
                    break
                arc.setPropertyValue(i, otherRegControl.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in RegControl makeLike: \"' + regControlName + '\" not found.', 121)
        return result
