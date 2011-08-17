from dss.delivery.impl.CapacitorObjImpl import (CapacitorObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.Capacitor import (Capacitor,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.PDClassImpl import (PDClassImpl,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)


class CapacitorImpl(PDClassImpl, Capacitor):
    activeCapacitorObj = None

    def __init__(self):
        super(CapacitorImpl, self)()
        self.className = 'Capacitor'
        self.classType = self.classType + DSSClassDefs.CAP_ELEMENT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = Capacitor.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'bus1'
        self.propertyName[1] = 'bus2'
        self.propertyName[2] = 'phases'
        self.propertyName[3] = 'kvar'
        self.propertyName[4] = 'kv'
        self.propertyName[5] = 'conn'
        self.propertyName[6] = 'cmatrix'
        self.propertyName[7] = 'cuf'
        self.propertyName[8] = 'R'
        self.propertyName[9] = 'XL'
        self.propertyName[10] = 'Harm'
        self.propertyName[11] = 'Numsteps'
        self.propertyName[12] = 'states'
        # define property help values
        self.propertyHelp[0] = 'Name of first bus. Examples:' + CRLF + 'bus1=busname' + CRLF + 'bus1=busname.1.2.3'
        self.propertyHelp[1] = 'Name of 2nd bus. Defaults to all phases connected ' + 'to first bus, node 0. (Shunt Wye Connection)' + CRLF + 'Not necessary to specify for delta (LL) connection'
        self.propertyHelp[2] = 'Number of phases.'
        self.propertyHelp[3] = 'Total kvar, if one step, or ARRAY of kvar ratings for each step.  Evenly divided among phases. See rules for NUMSTEPS.'
        self.propertyHelp[4] = 'For 2, 3-phase, kV phase-phase. Otherwise specify actual can rating.'
        self.propertyHelp[5] = '={wye | delta |LN |LL}  Default is wye, which is equivalent to LN'
        self.propertyHelp[6] = 'Nodal cap. matrix, lower triangle, microfarads, of the following form:' + CRLF + CRLF + 'cmatrix=\"c11 | -c21 c22 | -c31 -c32 c33\"' + CRLF + CRLF + 'All steps are assumed the same if this property is used.'
        self.propertyHelp[7] = 'ARRAY of Capacitance, each phase, for each step, microfarads.' + CRLF + 'See Rules for NumSteps.'
        self.propertyHelp[8] = 'ARRAY of series resistance in each phase (line), ohms. Default is 0.0'
        self.propertyHelp[9] = 'ARRAY of series inductive reactance(s) in each phase (line) for filter, ohms at base frequency. Use this OR \"h\" property to define filter. Default is 0.0.'
        self.propertyHelp[10] = 'ARRAY of harmonics to which each step is tuned. Zero is interpreted as meaning zero reactance (no filter). Default is zero.'
        self.propertyHelp[11] = 'Number of steps in this capacitor bank. Default = 1. Forces reallocation of the capacitance, reactor, and states array.  Rules: ' + 'If this property was previously =1, the value in the kvar property is divided equally among the steps. The kvar property ' + 'does not need to be reset if that is accurate.  If the Cuf or Cmatrix property was used previously, all steps are set to the value of the first step. ' + 'The states property is set to all steps on. All filter steps are set to the same harmonic. ' + 'If this property was previously >1, the arrays are reallocated, but no values are altered. You must SUBSEQUENTLY assign all array properties.'
        self.propertyHelp[12] = 'ARRAY of integers {1|0} states representing the state of each step (on|off). Defaults to 1 when reallocated (on). ' + 'Capcontrol will modify this array as it turns steps on or off.'
        self.activeProperty = Capacitor.NumPropsThisClass - 1
        super(CapacitorImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(CapacitorObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def doCmatrix(self):
        aco = self.activeCapacitorObj
        matBuffer = [None] * aco.getNPhases() * aco.getNPhases()
        orderFound = Parser.getInstance().parseAsSymMatrix(aco.getNPhases(), matBuffer)
        if orderFound > 0:
            # parse was successful
            # C
            aco.setCMatrix(Utilities.resizeArray(aco.getCMatrix(), aco.getNPhases() * aco.getNPhases()))
            _0 = True
            j = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    j += 1
                if not (j < aco.getNPhases() * aco.getNPhases()):
                    break
                aco.getCMatrix()[j] = 1e-06 * matBuffer[j]
        matBuffer = None

    def interpretConnection(self, s):
        """Accepts (case insensitive):
          delta or LL
          Y, wye, or LN
        """
        aco = self.activeCapacitorObj
        testS = s.toLowerCase()
        _0 = testS[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                aco.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                aco.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                aco.setConnection(1)
                # Delta or Line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = testS[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        aco.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        aco.setConnection(1)
                        break
                    break
                break
            break
        _4 = aco.getConnection()
        _5 = False
        while True:
            if _4 == 1:
                _5 = True
                aco.setNTerms(1)
                # force reallocation of terminals
                break
            if (_5 is True) or (_4 == 0):
                _5 = True
                if aco.getNTerms() != 2:
                    aco.setNTerms(2)
                break
            break

    def capSetBus1(self, s):
        # special handling for bus 1
        # set bus2 = bus1.0.0.0
        aco = self.activeCapacitorObj
        aco.setBus(0, s)
        # default bus2 to zero node of bus1. (grounded-Y connection)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos - 1]
        else:
            s2 = s[:len(s)]
            # copy up to dot
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < aco.getNPhases()):
                break
            s2 = s2 + '.0'
            # append series of ".0"'s
        aco.setBus(1, s2)
        # default setting for bus2
        aco.setShunt(True)

    def edit(self):
        result = 0
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeCapacitorObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeCapacitorObj)
        # use property to set this value
        aco = self.activeCapacitorObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                # TODO Check zero based indexing
                aco.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"Capacitor.' + aco.getName() + '\"', 450)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    self.capSetBus1(param)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    aco.setBus(1, param)
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    Utilities.interpretDblArray(param, aco.getNumSteps(), aco.getKVArRating())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    aco.setKVARating(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.interpretConnection(param)
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    self.doCmatrix()
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    Utilities.interpretDblArray(param, aco.getNumSteps(), aco.getC())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    Utilities.interpretDblArray(param, aco.getNumSteps(), aco.getR())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    Utilities.interpretDblArray(param, aco.getNumSteps(), aco.getXL())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    aco.processHarmonicSpec(param)
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    aco.setNumSteps(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    aco.processStatesSpec(param)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeCapacitorObj, paramPointer - Capacitor.NumPropsThisClass)
                    break
                break
            # some specials ...
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    aco.setPropertyValue(1, aco.getBus(0))
                    # this gets modified
                    aco.getPrpSequence()[1] = 0
                    # reset this for save function
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    if (
                        not Utilities.stripExtension(aco.getBus(0)).equalsIgnoreCase(Utilities.stripExtension(aco.getBus(1)))
                    ):
                        aco.setShunt(False)
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    if aco.getNPhases() != parser.makeInteger():
                        aco.setNPhases(parser.makeInteger())
                        aco.setNConds(aco.getNPhases())
                        # force reallocation of terminal info
                        aco.setYorder(aco.getNTerms() * aco.getNConds())
                    break
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    aco.setSpecType(1)
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    aco.setSpecType(3)
                    break
                if (_3 is True) or (_2 == 7):
                    _3 = True
                    aco.setSpecType(2)
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < aco.getNumSteps()):
                            break
                        aco.getC()[i] = aco.getC()[i] * 1e-06
                    break
                if (_3 is True) or (_2 == 9):
                    _3 = True
                    _5 = True
                    i = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < aco.getNumSteps()):
                            break
                        if aco.getXL()[i] != 0.0:
                            if aco.getR()[i] == 0.0:
                                aco.getR()[i] = self.Math.abs(aco.getXL()[i]) / 1000.0
                                # put in something so it doesn't fail
                    aco.setDoHarmonicRecalc(False)
                    # XL is specified
                    break
                break
            # YPrim invalidation on anything that changes impedance values
            _6 = paramPointer
            _7 = False
            while True:
                if _6 == 3:
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 4):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 5):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 6):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 7):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 8):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 12):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                if (_7 is True) or (_6 == 13):
                    _7 = True
                    aco.setYPrimInvalid(True)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        aco.recalcElementData()
        return result

    def makeLike(self, capacitorName):
        result = 0
        # See if we can find this capacitor name in the present collection
        otherCapacitor = self.find(capacitorName)
        if otherCapacitor is not None:
            aco = self.activeCapacitorObj
            if aco.getNPhases() != otherCapacitor.getNPhases():
                aco.setNPhases(otherCapacitor.getNPhases())
                aco.setNConds(aco.getNPhases())
                # force reallocation of terminals and conductors
                aco.setYorder(aco.getNConds() * aco.getNTerms())
                aco.setYPrimInvalid(True)
            aco.setNumSteps(otherCapacitor.getNumSteps())
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < aco.getNumSteps()):
                    break
                aco.getC()[i] = otherCapacitor.getC()[i]
                aco.getKVArRating()[i] = otherCapacitor.getKVArRating()[i]
                aco.getR()[i] = otherCapacitor.getR()[i]
                aco.getXL()[i] = otherCapacitor.getXL()[i]
                aco.getXL()[i] = otherCapacitor.getXL()[i]
                aco.getHarm()[i] = otherCapacitor.getHarm()[i]
                aco.getStates()[i] = otherCapacitor.getStates()[i]
            aco.setKVARating(otherCapacitor.getKVRating())
            aco.setConnection(otherCapacitor.getConnection())
            aco.setSpecType(otherCapacitor.getSpecType())
            if otherCapacitor.getCMatrix() is None:
                aco.setCMatrix([None] * 0)
            else:
                aco.setCMatrix(Utilities.resizeArray(aco.getCMatrix(), aco.getNPhases() * aco.getNPhases()))
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < aco.getNPhases() * aco.getNPhases()):
                        break
                    aco.getCMatrix()[i] = otherCapacitor.getCMatrix()[i]
            self.classMakeLike(otherCapacitor)
            # take care of inherited class properties
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < aco.getParentClass().getNumProperties()):
                    break
                aco.setPropertyValue(i, otherCapacitor.getPropertyValue(i))
                result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Capacitor.makeLike(): \"' + capacitorName + '\" not found.', 451)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Capacitor.init()', 452)
        return 0
