from dss.common.impl.Utilities import (Utilities,)
from dss.control.CapControl import (CapControl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.control.impl.ControlClassImpl import (ControlClassImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.control.impl.CapControlObjImpl import (CapControlObjImpl,)
# from com.epri.dss.control.impl.CapControlObjImpl.CapControlType import (CapControlType,)


class CapControlImpl(ControlClassImpl, CapControl):
    activeCapControlObj = None

    def __init__(self):
        super(CapControlImpl, self)()
        self.className = 'CapControl'
        self.classType = self.classType + DSSClassDefs.CAP_CONTROL
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = CapControl.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'element'
        self.propertyName[1] = 'terminal'
        self.propertyName[2] = 'capacitor'
        self.propertyName[3] = 'type'
        self.propertyName[4] = 'PTratio'
        self.propertyName[5] = 'CTratio'
        self.propertyName[6] = 'ONsetting'
        self.propertyName[7] = 'OFFsetting'
        self.propertyName[8] = 'Delay'
        self.propertyName[9] = 'VoltOverride'
        self.propertyName[10] = 'Vmax'
        self.propertyName[11] = 'Vmin'
        self.propertyName[12] = 'DelayOFF'
        self.propertyName[13] = 'DeadTime'
        self.propertyName[14] = 'CTPhase'
        self.propertyName[15] = 'PTPhase'
        self.propertyHelp[0] = 'Full object name of the circuit element, typically a line or transformer, ' + 'to which the capacitor control's PT and/or CT are connected.' + 'There is no default; must be specified.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the CapControl is connected. ' + '1 or 2, typically.  Default is 1.'
        self.propertyHelp[2] = 'Name of Capacitor element which the CapControl controls. No Default; Must be specified.' + 'Do not specify the full object name; \"Capacitor\" is assumed for ' + 'the object class.  Example:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Capacitor=cap1'
        self.propertyHelp[3] = '{Current | voltage | kvar | PF | time } Control type.  Specify the ONsetting and OFFsetting ' + 'appropriately with the type of control. (See help for ONsetting)'
        self.propertyHelp[4] = 'Ratio of the PT that converts the monitored voltage to the control voltage. ' + 'Default is 60.  If the capacitor is Wye, the 1st phase line-to-neutral voltage is monitored.  Else, the line-to-line ' + 'voltage (1st - 2nd phase) is monitored.'
        self.propertyHelp[5] = 'Ratio of the CT from line amps to control ampere setting for current and kvar control types. '
        self.propertyHelp[6] = 'Value at which the control arms to switch the capacitor ON (or ratchet up a step).  ' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Type of Control:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Current: Line Amps / CTratio' + DSSGlobals.CRLF + 'Voltage: Line-Neutral (or Line-Line for delta) Volts / PTratio' + DSSGlobals.CRLF + 'kvar:    Total kvar, all phases (3-phase for pos seq model). This is directional. ' + DSSGlobals.CRLF + 'PF:      Power Factor, Total power in monitored terminal. Negative for Leading. ' + DSSGlobals.CRLF + 'Time:    Hrs from Midnight as a floating point number (decimal). 7:30am would be entered as 7.5.'
        self.propertyHelp[7] = 'Value at which the control arms to switch the capacitor OFF. (See help for ONsetting)' + 'For Time control, is OK to have Off time the next day ( < On time)'
        self.propertyHelp[8] = 'Time delay, in seconds, from when the control is armed before it sends out the switching ' + 'command to turn ON.  The control may reset before the action actually occurs. ' + 'This is used to determine which capacity control will act first. Default is 15.  You may specify any ' + 'floating point number to achieve a model of whatever condition is necessary.'
        self.propertyHelp[9] = '{Yes | No}  Default is No.  Switch to indicate whether VOLTAGE OVERRIDE is to be considered. ' + 'Vmax and Vmin must be set to reasonable values if this property is Yes.'
        self.propertyHelp[10] = 'Maximum voltage, in volts.  If the voltage across the capacitor divided by the PTRATIO is greater ' + 'than this voltage, the capacitor will switch OFF regardless of other control settings. ' + 'Default is 126 (goes with a PT ratio of 60 for 12.47 kV system).'
        self.propertyHelp[11] = 'Minimum voltage, in volts.  If the voltage across the capacitor divided by the PTRATIO is less ' + 'than this voltage, the capacitor will switch ON regardless of other control settings. ' + 'Default is 115 (goes with a PT ratio of 60 for 12.47 kV system).'
        self.propertyHelp[12] = 'Time delay, in seconds, for control to turn OFF when present state is ON. Default is 15.'
        self.propertyHelp[13] = 'Dead time after capacitor is turned OFF before it can be turned back ON. Default is 300 sec.'
        self.propertyHelp[14] = 'Number of the phase being monitored for CURRENT control or one of {AVG | MAX | MIN} for all phases. Default=1. ' + 'If delta or L-L connection, enter the first or the two phases being monitored [1-2, 2-3, 3-1]. ' + 'Must be less than the number of phases. Does not apply to kvar control which uses all phases by default.'
        self.propertyHelp[15] = 'Number of the phase being monitored for VOLTAGE control or one of {AVG | MAX | MIN} for all phases. Default=1. ' + 'If delta or L-L connection, enter the first or the two phases being monitored [1-2, 2-3, 3-1]. ' + 'Must be less than the number of phases. Does not apply to kvar control which uses all phases by default.'
        self.activeProperty = CapControl.NumPropsThisClass - 1
        super(CapControlImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, ObjName):
        DSSGlobals.activeCircuit.setActiveCktElement(CapControlObjImpl(self, ObjName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeCapControlObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeCapControlObj)
        result = 0
        acc = self.activeCapControlObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                acc.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + acc.getName() + '\"', 352)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    acc.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    acc.setElementTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    acc.setCapacitorName('capacitor.' + param)
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    _2 = param.toLowerCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'c':
                            _3 = True
                            acc.setControlType(CapControlType.CURRENT)
                            break
                        if (_3 is True) or (_2 == 'v'):
                            _3 = True
                            acc.setControlType(CapControlType.VOLTAGE)
                            break
                        if (_3 is True) or (_2 == 'k'):
                            _3 = True
                            acc.setControlType(CapControlType.KVAR)
                            break
                        if (_3 is True) or (_2 == 't'):
                            _3 = True
                            acc.setControlType(CapControlType.TIME)
                            break
                        if (_3 is True) or (_2 == 'p'):
                            _3 = True
                            acc.setControlType(CapControlType.PF)
                            break
                        if (_3 is True) or (_2 == 's'):
                            _3 = True
                            acc.setControlType(CapControlType.SRP)
                            break
                        if True:
                            _3 = True
                            DSSGlobals.doSimpleMsg(String.format.format('Unrecognized CapControl type: \"%s\" (CapControl.%s)', param, acc.getName()), 352)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    acc.setPTRatio(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    acc.setCTRatio(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    acc.setOnValue(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    acc.setOffValue(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    acc.setOnDelay(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    acc.setVOverride(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    acc.setVMax(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    acc.setVMin(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    acc.setOffDelay(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    acc.setDeadTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    if Utilities.compareTextShortest(param, 'avg') == 0:
                        acc.setCTPhase(CapControl.AVGPHASES)
                    elif Utilities.compareTextShortest(param, 'max') == 0:
                        acc.setCTPhase(CapControl.MAXPHASE)
                    elif Utilities.compareTextShortest(param, 'min') == 0:
                        acc.setCTPhase(CapControl.MINPHASE)
                    else:
                        acc.setCTPhase(self.Math.max(1, parser.makeInteger()))
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    if Utilities.compareTextShortest(param, 'avg') == 0:
                        acc.setPTPhase(CapControl.AVGPHASES)
                    elif Utilities.compareTextShortest(param, 'max') == 0:
                        acc.setPTPhase(CapControl.MAXPHASE)
                    elif Utilities.compareTextShortest(param, 'min') == 0:
                        acc.setPTPhase(CapControl.MINPHASE)
                    else:
                        acc.setPTPhase(self.Math.max(1, parser.makeInteger()))
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeCapControlObj, paramPointer - CapControl.NumPropsThisClass)
                    break
                break
            # PF controller changes
            if acc.getControlType() == CapControlType.PF:
                _4 = paramPointer
                _5 = False
                while True:
                    if _4 == 3:
                        _5 = True
                        acc.setPFOnValue(0.95)
                        # defaults
                        acc.setPFOffValue(1.05)
                        break
                    if (_5 is True) or (_4 == 6):
                        _5 = True
                        if acc.getOnValue() >= -1.0 and acc.getOnValue() <= 1.0:
                            if acc.getOnValue() < 0.0:
                                acc.setPFOnValue(2.0 + acc.getOnValue())
                            else:
                                acc.setPFOnValue(acc.getOnValue())
                        else:
                            DSSGlobals.doSimpleMsg('Invalid PF on value for CapControl.' + acc.getName(), 353)
                        break
                    if (_5 is True) or (_4 == 7):
                        _5 = True
                        if acc.getOffValue() >= -1.0 and acc.getOffValue() <= 1.0:
                            if acc.getOffValue() < 0.0:
                                acc.setPFOffValue(2.0 + acc.getOffValue())
                            else:
                                acc.setPFOffValue(acc.getOffValue())
                        else:
                            DSSGlobals.doSimpleMsg('Invalid PF off value for CapControl.' + acc.getName(), 35301)
                        break
                    if (_5 is True) or (_4 == 14):
                        _5 = True
                        if acc.getCTPhase() > acc.getNPhases():
                            DSSGlobals.doSimpleMsg(String.format.format('Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ', acc.getCTPhase(), acc.getNPhases()), 35302)
                            acc.setCTPhase(1)
                        break
                    if (_5 is True) or (_4 == 15):
                        _5 = True
                        if acc.getPTPhase() > acc.getNPhases():
                            DSSGlobals.doSimpleMsg(String.format.format('Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ', acc.getPTPhase(), acc.getNPhases()), 35303)
                            acc.setPTPhase(1)
                        break
                    break
            paramName = parser.getNextParam()
            param = parser.makeString()
        acc.recalcElementData()
        return result

    def makeLike(self, capControlName):
        result = 0
        # See if we can find this CapControl name in the present collection
        otherCapControl = self.find(capControlName)
        if otherCapControl is not None:
            acc = self.activeCapControlObj
            acc.setNPhases(otherCapControl.getNPhases())
            acc.setNConds(otherCapControl.getNConds())
            # force reallocation of terminal stuff
            acc.setElementName(otherCapControl.getElementName())
            acc.setCapacitorName(otherCapControl.getCapacitorName())
            acc.setControlledElement(otherCapControl.getControlledElement())
            # pointer to target circuit element
            acc.setMonitoredElement(otherCapControl.getMonitoredElement())
            # pointer to target circuit element
            acc.setElementTerminal(otherCapControl.getElementTerminal())
            acc.setPTRatio(otherCapControl.getPTRatio())
            acc.setCTRatio(otherCapControl.getCTRatio())
            acc.setControlType(otherCapControl.getControlType())
            acc.setPresentState(otherCapControl.getPresentState())
            acc.setShouldSwitch(otherCapControl.isShouldSwitch())
            acc.setCondOffset(otherCapControl.getCondOffset())
            acc.setOnValue(otherCapControl.getOnValue())
            acc.setOffValue(otherCapControl.getOffValue())
            acc.setPFOnValue(otherCapControl.getPFOnValue())
            acc.setPFOffValue(otherCapControl.getPFOffValue())
            acc.setCTPhase(otherCapControl.getCTPhase())
            acc.setPTPhase(otherCapControl.getPTPhase())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < acc.getParentClass().getNumProperties()):
                    break
                acc.setPropertyValue(i, otherCapControl.getPropertyValue(i))
        else:
            DSSGlobals.doSimpleMsg('Error in CapControl makeLike: \"' + capControlName + '\" not found.', 360)
        return result
