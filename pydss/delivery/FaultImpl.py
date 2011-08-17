from dss.delivery.Fault import (Fault,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.PDClassImpl import (PDClassImpl,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.delivery.impl.FaultObjImpl import (FaultObjImpl,)


class FaultImpl(PDClassImpl, Fault):
    activeFaultObj = None

    def __init__(self):
        super(FaultImpl, self)()
        self.className = 'Fault'
        self.classType = DSSClassDefs.FAULTOBJECT + DSSClassDefs.NON_PCPD_ELEM
        # only in fault object class
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = Fault.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'bus1'
        self.propertyName[1] = 'bus2'
        self.propertyName[2] = 'phases'
        self.propertyName[3] = 'r'
        self.propertyName[4] = '%stddev'
        self.propertyName[5] = 'Gmatrix'
        self.propertyName[6] = 'ONtime'
        self.propertyName[7] = 'temporary'
        self.propertyName[8] = 'MinAmps'
        # define property help values
        self.propertyHelp[0] = 'Name of first bus. Examples:' + DSSGlobals.CRLF + 'bus1=busname' + DSSGlobals.CRLF + 'bus1=busname.1.2.3'
        self.propertyHelp[1] = 'Name of 2nd bus. Defaults to all phases connected ' + 'to first bus, node 0, if not specified. (Shunt Wye Connection to ground reference)'
        self.propertyHelp[2] = 'Number of Phases. Default is 1.'
        self.propertyHelp[3] = 'Resistance, each phase, ohms. Default is 0.0001. Assumed to be Mean value if gaussian random mode.' + 'Max value if uniform mode.  A Fault is actually a series resistance ' + 'that defaults to a wye connection to ground on the second terminal.  You ' + 'may reconnect the 2nd terminal to achieve whatever connection.  Use ' + 'the Gmatrix property to specify an arbitrary conductance matrix.'
        self.propertyHelp[4] = 'Percent standard deviation in resistance to assume for Monte Carlo fault (MF) solution mode for GAUSSIAN distribution. Default is 0 (no variation from mean).'
        self.propertyHelp[5] = 'Use this to specify a nodal conductance (G) matrix to represent some arbitrary resistance network. ' + 'Specify in lower triangle form as usual for DSS matrices.'
        self.propertyHelp[6] = 'Time (sec) at which the fault is established for time varying simulations. Default is 0.0 ' + '(on at the beginning of the simulation)'
        self.propertyHelp[7] = '{Yes | No} Default is No.  Designate whether the fault is temporary.  For Time-varying simulations, ' + 'the fault will be removed if the current through the fault drops below the MINAMPS criteria.'
        self.propertyHelp[8] = 'Minimum amps that can sustain a temporary fault. Default is 5.'
        self.activeProperty = Fault.NumPropsThisClass - 1
        super(FaultImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(FaultObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def doGmatrix(self):
        af = self.activeFaultObj
        matBuffer = [None] * af.getNPhases() * af.getNPhases()
        orderFound = Parser.getInstance().parseAsSymMatrix(af.getNPhases(), matBuffer)
        if orderFound > 0:
            # parse was successful  TODO Check zero based indexing
            # X
            af.setGMatrix(Utilities.resizeArray(af.getGMatrix(), af.getNPhases() * af.getNPhases()))
            _0 = True
            j = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    j += 1
                if not (j < af.getNPhases() * af.getNPhases()):
                    break
                af.getGMatrix()[j] = matBuffer[j]
        matBuffer = None

    def fltSetBus1(self, s):
        # special handling for bus 1
        # set bus2 = bus1.0.0.0
        af = self.activeFaultObj
        af.setBus(1, s)
        # TODO Check zero based indexing
        # default bus2 to zero node of bus1. (wye grounded connection)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos]
            # copy up to dot
        else:
            s2 = s[len(s):]
        s2 = s2 + '.0.0.0'
        # set default for up to 3 phases
        af.setBus(2, s2)
        # TODO Check zero based indexing
        af.setShunt(True)

    def edit(self):
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activeFaultObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeFaultObj)
        # use property to set this value
        af = self.activeFaultObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer <= self.numProperties:
                af.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + af.getName() + '\"', 350)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    self.fltSetBus1(param)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    af.setBus(2, param)
                    # TODO Check zero based indexing
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    af.setG(parser.makeDouble())
                    if af.getG() != 0.0:
                        af.setG(1.0 / af.getG())
                    else:
                        af.setG(10000.0)
                        # default to a low resistance
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    af.setStdDev(parser.makeDouble() * 0.01)
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    self.doGmatrix()
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    af.setOnTime(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    af.setTemporary(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    af.setMinAmps(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeFaultObj, paramPointer - Fault.NumPropsThisClass)
                    break
                break
            # some specials ...
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    af.setPropertyValue(1, af.getBus(1))
                    # bus2 gets modified if bus1 is   TODO Check zero based indexing
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    if (
                        not Utilities.stripExtension(af.getBus(0)).equalsIgnoreCase(Utilities.stripExtension(af.getBus(1)))
                    ):
                        af.setShunt(False)
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    if af.getNPhases() != parser.makeInteger():
                        af.setNPhases(parser.makeInteger())
                        af.setNConds(af.getNPhases())
                        # force reallocation of terminal info
                        DSSGlobals.activeCircuit.setBusNameRedefined(True)
                        # set global flag to signal circuit to rebuild bus defs
                    break
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    af.setSpecType(1)
                    break
                if (_3 is True) or (_2 == 5):
                    _3 = True
                    af.setSpecType(2)
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    if af.getOnTime() > 0.0:
                        af.setOn(False)
                        # assume fault will be on later
                    break
                break
            # YPrim invalidation on anything that changes impedance values
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 3:
                    _5 = True
                    af.setYPrimInvalid(True)
                    break
                if (_5 is True) or (_4 == 4):
                    _5 = True
                    af.setYPrimInvalid(True)
                    break
                if (_5 is True) or (_4 == 6):
                    _5 = True
                    af.setYPrimInvalid(True)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        af.recalcElementData()
        return result

    def makeLike(self, faultName):
        result = 0
        # See if we can find this fault name in the present collection
        otherFault = self.find(faultName)
        if otherFault is not None:
            af = self.activeFaultObj
            if af.getNPhases() != otherFault.getNPhases():
                af.setNPhases(otherFault.getNPhases())
                af.setNConds(af.getNPhases())
                # force reallocation of terminals and conductors
                af.setYorder(af.getNConds() * af.getNTerms())
                af.setYPrimInvalid(True)
            af.setBaseFrequency(otherFault.getBaseFrequency())
            af.setG(otherFault.getG())
            af.setSpecType(otherFault.getSpecType())
            af.setMinAmps(otherFault.getMinAmps())
            af.setTemporary(otherFault.isTemporary())
            af.setCleared(otherFault.isCleared())
            af.setOn(otherFault.isOn())
            af.setOnTime(otherFault.getOnTime())
            if otherFault.getGMatrix() is None:
                af.setGMatrix(None)
            else:
                af.setGMatrix(Utilities.resizeArray(af.getGMatrix(), af.getNPhases() * af.getNPhases()))
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < af.getNPhases() * af.getNPhases()):
                        break
                    af.getGMatrix()[i] = otherFault.getGMatrix()[i]
            self.classMakeLike(otherFault)
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < af.getParentClass().getNumProperties()):
                    break
                af.setPropertyValue(i, otherFault.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Fault.makeLike(): \"' + faultName + '\" not found.', 351)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Fault.init()', -1)
        return 0
