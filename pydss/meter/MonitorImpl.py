from dss.meter.Monitor import (Monitor,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.meter.impl.MeterClassImpl import (MeterClassImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.meter.impl.MonitorObjImpl import (MonitorObjImpl,)


class MonitorImpl(MeterClassImpl, Monitor):
    activeMonitorObj = None

    def __init__(self):
        super(MonitorImpl, self)()
        self.className = 'Monitor'
        self.classType = self.classType + DSSClassDefs.MON_ELEMENT
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = Monitor.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'element'
        self.propertyName[1] = 'terminal'
        self.propertyName[2] = 'mode'
        self.propertyName[3] = 'action'
        # buffer=clear|save
        self.propertyName[4] = 'residual'
        # buffer=clear|save
        self.propertyName[5] = 'VIPolar'
        # V I in mag and angle rather then re and im
        self.propertyName[6] = 'PPolar'
        # power in power PF rather then power and vars
        self.propertyHelp[0] = 'Name (Full Object name) of element to which the monitor is connected.'
        self.propertyHelp[1] = 'Number of the terminal of the circuit element to which the monitor is connected. ' + '1 or 2, typically. For monitoring states, attach monitor to terminal 1.'
        self.propertyHelp[2] = 'Bitmask integer designating the values the monitor is to capture: ' + DSSGlobals.CRLF + '0 = Voltages and currents' + DSSGlobals.CRLF + '1 = Powers' + DSSGlobals.CRLF + '2 = Tap Position (Transformers only)' + DSSGlobals.CRLF + '3 = State Variables (PCElements only)' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Normally, these would be actual phasor quantities from solution.' + DSSGlobals.CRLF + 'Combine with adders below to achieve other results for terminal quantities:' + DSSGlobals.CRLF + '+16 = Sequence quantities' + DSSGlobals.CRLF + '+32 = Magnitude only' + DSSGlobals.CRLF + '+64 = Positive sequence only or avg of all phases' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Mix adder to obtain desired results. For example:' + DSSGlobals.CRLF + 'Mode=112 will save positive sequence voltage and current magnitudes only' + DSSGlobals.CRLF + 'Mode=48 will save all sequence voltages and currents, but magnitude only.'
        self.propertyHelp[3] = '{Clear | Save | Take}' + DSSGlobals.CRLF + '(C)lears or (S)aves current buffer.' + DSSGlobals.CRLF + '(T)ake action takes a sample.' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Note that monitors are automatically reset (cleared) when the Set Mode= command is issued. ' + 'Otherwise, the user must explicitly reset all monitors (reset monitors command) or individual ' + 'monitors with the Clear action.'
        self.propertyHelp[4] = '{Yes/True | No/False} Default = No.  Include Residual cbannel (sum of all phases) for voltage and current. ' + 'Does not apply to sequence quantity modes or power modes.'
        self.propertyHelp[5] = '{Yes/True | No/False} Default = YES. Report voltage and current in polar form (Mag/Angle). (default)  Otherwise, it will be real and imaginary.'
        self.propertyHelp[6] = '{Yes/True | No/False} Default = YES. Report power in Apparent power, S, in polar form (Mag/Angle).(default)  Otherwise, is P and Q'
        self.activeProperty = Monitor.NumPropsThisClass - 1
        super(MonitorImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(MonitorObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeMonitorObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeMonitorObj)
        result = 0
        am = self.activeMonitorObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                am.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + am.getName() + '\"', 661)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    am.setElementName(param.toLowerCase())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    am.setMeteredTerminal(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    am.setMode(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    _2 = param.toLowerCase()[0]
                    _3 = False
                    while True:
                        if _2 == 's':
                            _3 = True
                            am.save()
                            break
                        if (_3 is True) or (_2 == 'c'):
                            _3 = True
                            am.resetIt()
                            break
                        if (_3 is True) or (_2 == 'r'):
                            _3 = True
                            am.resetIt()
                            break
                        if (_3 is True) or (_2 == 't'):
                            _3 = True
                            am.takeSample()
                            break
                        break
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    am.setIncludeResidual(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    am.setVIPolar(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    am.setPPolar(Utilities.interpretYesNo(param))
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeMonitorObj, paramPointer - Monitor.NumPropsThisClass)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        am.recalcElementData()
        return result

    def resetAll(self):
        """Force all monitors in the circuit to reset."""
        for pMon in DSSGlobals.activeCircuit.getMonitors():
            if pMon.isEnabled():
                pMon.resetIt()

    def sampleAll(self):
        """Force all monitors to take a sample."""
        for pMon in DSSGlobals.activeCircuit.getMonitors():
            if pMon.isEnabled():
                pMon.takeSample()

    def saveAll(self):
        """Force all monitors to save their buffers to disk."""
        for pMon in DSSGlobals.activeCircuit.getMonitors():
            if pMon.isEnabled():
                pMon.save()

    def makeLike(self, MonitorName):
        result = 0
        # See if we can find this monitor name in the present collection
        otherMonitor = self.find(MonitorName)
        if otherMonitor is not None:
            am = self.activeMonitorObj
            am.setNPhases(otherMonitor.getNPhases())
            am.setNConds(otherMonitor.getNConds())
            # force reallocation of terminal stuff
            am.setBufferSize(otherMonitor.getBufferSize())
            am.setElementName(otherMonitor.getElementName())
            am.setMeteredElement(otherMonitor.getMeteredElement())
            # target circuit element
            am.setMeteredTerminal(otherMonitor.getMeteredTerminal())
            am.setMode(otherMonitor.getMode())
            am.setIncludeResidual(otherMonitor.isIncludeResidual())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < am.getParentClass().getNumProperties()):
                    break
                am.setPropertyValue(i, otherMonitor.getPropertyValue(i))
            am.setBaseFrequency(otherMonitor.getBaseFrequency())
        else:
            DSSGlobals.doSimpleMsg('Error in Monitor makeLike: \"' + MonitorName + '\" not found.', 662)
        return result

    def init(self, handle):
        result = 0
        if handle >= 0:
            mon = self.elementList.get(handle)
            mon.resetIt()
        else:
            # Do 'em all
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < len(self.elementList)):
                    break
                mon = self.elementList.get(i)
                mon.resetIt()
        return result

    def TOPExport(self, objName):
        # FIXME Implement or remove this
        raise self.UnsupportedOperationException()
