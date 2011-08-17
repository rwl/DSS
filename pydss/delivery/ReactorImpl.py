from dss.delivery.impl.ReactorObjImpl import (ReactorObjImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.PDClassImpl import (PDClassImpl,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.delivery.Reactor import (Reactor,)
from dss.parser.impl.Parser import (Parser,)


class ReactorImpl(PDClassImpl, Reactor):
    activeReactorObj = None

    def __init__(self):
        super(ReactorImpl, self)()
        self.className = 'Reactor'
        self.classType = self.classType + DSSClassDefs.REACTOR_ELEMENT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = Reactor.NumPropsThisClass
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
        self.propertyName[6] = 'Rmatrix'
        self.propertyName[7] = 'Xmatrix'
        self.propertyName[8] = 'Parallel'
        self.propertyName[9] = 'R'
        self.propertyName[10] = 'X'
        self.propertyName[11] = 'Rp'
        # define property help values
        self.propertyHelp[0] = 'Name of first bus. Examples:' + DSSGlobals.CRLF + 'bus1=busname' + DSSGlobals.CRLF + 'bus1=busname.1.2.3'
        self.propertyHelp[1] = 'Name of 2nd bus. Defaults to all phases connected ' + 'to first bus, node 0. (Shunt Wye Connection)' + DSSGlobals.CRLF + 'Not necessary to specify for delta (LL) connection'
        self.propertyHelp[2] = 'Number of phases.'
        self.propertyHelp[3] = 'Total kvar, all phases.  Evenly divided among phases. Only determines X. Specify R separately'
        self.propertyHelp[4] = 'For 2, 3-phase, kV phase-phase. Otherwise specify actual coil rating.'
        self.propertyHelp[5] = '={wye | delta |LN |LL}  Default is wye, which is equivalent to LN. If Delta, then only one terminal.'
        self.propertyHelp[6] = 'Resistance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases. ' + 'Mutually exclusive to specifying parameters by kvar or X.'
        self.propertyHelp[7] = 'Reactance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases. ' + 'Mutually exclusive to specifying parameters by kvar or X.'
        self.propertyHelp[8] = '{Yes | No}  Default=No. Indicates whether Rmatrix and Xmatrix are to be considered in parallel. ' + 'Default is series. For other models, specify R and Rp.'
        self.propertyHelp[9] = 'Resistance (in series with reactance), each phase, ohms.'
        self.propertyHelp[10] = 'Reactance, each phase, ohms at base frequency.'
        self.propertyHelp[11] = 'Resistance in parallel with R and X (the entire branch). Assumed infinite if not specified.'
        self.activeProperty = Reactor.NumPropsThisClass - 1
        super(ReactorImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(ReactorObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def doMatrix(self, matrix):
        ar = self.activeReactorObj
        matBuffer = [None] * ar.getNPhases() * ar.getNPhases()
        orderFound = Parser.getInstance().parseAsSymMatrix(ar.getNPhases(), matBuffer)
        if orderFound > 0:
            # Parse was successful Else don't change Matrix
            # X
            matrix = Utilities.resizeArray(matrix, ar.getNPhases() * ar.getNPhases())
            _0 = True
            j = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    j += 1
                if not (j < ar.getNPhases() * ar.getNPhases()):
                    break
                matrix[j] = matBuffer[j]
            matBuffer = None

    def interpretConnection(self, s):
        """Accepts (case insensitive):
        		delta or LL
        		Y, wye, or LN
        """
        ar = self.activeReactorObj
        testS = s.toLowerCase()
        _0 = testS[0]
        _1 = False
        while True:
            if _0 == 'y':
                _1 = True
                ar.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'w'):
                _1 = True
                ar.setConnection(0)
                # Wye
                break
            if (_1 is True) or (_0 == 'd'):
                _1 = True
                ar.setConnection(1)
                # Delta or Line-Line
                break
            if (_1 is True) or (_0 == 'l'):
                _1 = True
                _2 = testS[1]
                _3 = False
                while True:
                    if _2 == 'n':
                        _3 = True
                        ar.setConnection(0)
                        break
                    if (_3 is True) or (_2 == 'l'):
                        _3 = True
                        ar.setConnection(1)
                        break
                    break
                break
            break
        _4 = ar.getConnection()
        _5 = False
        while True:
            if _4 == 1:
                _5 = True
                ar.setNTerms(1)
                # force reallocation of terminals
                break
            if (_5 is True) or (_4 == 0):
                _5 = True
                if ar.getNTerms() != 2:
                    ar.setNTerms(2)
                break
            break

    def reactorSetBus1(self, s):
        # special handling for bus 1
        # set bus2 = bus1.0.0.0
        ar = self.activeReactorObj
        ar.setBus(1, s)
        # default bus2 to zero node of bus1. (wye grounded connection)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos]
            # copy up to dot
        else:
            s2 = s[:len(s)]
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ar.getNPhases()):
                break
            s2 = s2 + '.0'
        ar.setBus(2, s2)
        ar.setShunt(True)

    def edit(self):
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activeReactorObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeReactorObj)
        ar = self.activeReactorObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                ar.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + ar.getName() + '\"', 230)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    self.reactorSetBus1(param)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    ar.setBus(2, param)
                    # TODO Check zero based indexing
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    ar.setKVArRating(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    ar.setKVRating(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    self.interpretConnection(param)
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    self.doMatrix(ar.getRMatrix())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    self.doMatrix(ar.getXMatrix())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    ar.setParallel(Utilities.interpretYesNo(param))
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    ar.setR(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    ar.setX(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    ar.setRp(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeReactorObj, paramPointer - Reactor.NumPropsThisClass)
                    break
                break
            # some specials ...
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 0:
                    _3 = True
                    ar.setPropertyValue(1, ar.getBus(1))
                    # this gets modified   TODO Check zero based indexing
                    ar.getPrpSequence()[1] = 0
                    # reset this for save function
                    break
                if (_3 is True) or (_2 == 1):
                    _3 = True
                    if (
                        not Utilities.stripExtension(ar.getBus(0)).equalsIgnoreCase(Utilities.stripExtension(ar.getBus(1)))
                    ):
                        ar.setShunt(False)
                    break
                if (_3 is True) or (_2 == 2):
                    _3 = True
                    if ar.getNPhases() != parser.makeInteger():
                        ar.setNPhases(parser.makeInteger())
                        ar.setNConds(ar.getNPhases())
                        # force reallocation of terminal info
                        ar.setYorder(ar.getNTerms() * ar.getNConds())
                    break
                if (_3 is True) or (_2 == 3):
                    _3 = True
                    ar.setSpecType(1)
                    # x specified by kVAr, kV
                    break
                if (_3 is True) or (_2 == 6):
                    _3 = True
                    ar.setSpecType(3)
                    break
                if (_3 is True) or (_2 == 7):
                    _3 = True
                    ar.setSpecType(3)
                    break
                if (_3 is True) or (_2 == 10):
                    _3 = True
                    ar.setSpecType(2)
                    # x specified directly
                    break
                if (_3 is True) or (_2 == 11):
                    _3 = True
                    ar.setRpSpecified(True)
                    break
                break
            # YPrim invalidation on anything that changes impedance values
            if paramPointer >= 2 and paramPointer <= 11:
                ar.setYPrimInvalid(True)
            paramName = parser.getNextParam()
            param = parser.makeString()
        ar.recalcElementData()
        return result

    def makeLike(self, reactorName):
        result = 0
        # See if we can find this reactor name in the present collection
        otherReactor = self.find(reactorName)
        if otherReactor is not None:
            ar = self.activeReactorObj
            if ar.getNPhases() != otherReactor.getNPhases():
                ar.setNPhases(otherReactor.getNPhases())
                ar.setNConds(ar.getNPhases())
                # force reallocation of terminals and conductors
                ar.setYorder(ar.getNConds() * ar.getNTerms())
                ar.setYPrimInvalid(True)
            ar.setR(otherReactor.getR())
            ar.setX(otherReactor.getX())
            ar.setRp(otherReactor.getRp())
            ar.setRpSpecified(otherReactor.isRpSpecified())
            ar.setParallel(otherReactor.isParallel())
            ar.setKVArRating(otherReactor.getKVArRating())
            ar.setKVRating(otherReactor.getKVRating())
            ar.setConnection(otherReactor.getConnection())
            ar.setSpecType(otherReactor.getSpecType())
            if otherReactor.getRMatrix() is None:
                ar.setRMatrix([None] * 0)
            else:
                ar.setRMatrix(Utilities.resizeArray(ar.getRMatrix(), ar.getNPhases() * ar.getNPhases()))
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < ar.getNPhases() * ar.getNPhases()):
                        break
                    ar.getRMatrix()[i] = otherReactor.getRMatrix()[i]
            if otherReactor.getXMatrix() is None:
                ar.setXMatrix([None] * 0)
            else:
                ar.setXMatrix(Utilities.resizeArray(ar.getXMatrix(), ar.getNPhases() * ar.getNPhases()))
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < ar.getNPhases() * ar.getNPhases()):
                        break
                    ar.getXMatrix()[i] = otherReactor.getXMatrix()[i]
            self.classMakeLike(otherReactor)
            # take care of inherited class properties
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < ar.getParentClass().getNumProperties()):
                    break
                ar.setPropertyValue(i, otherReactor.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Reactor makeLike: \"' + reactorName + '\" not found.', 231)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Reactor.init()', -1)
        return 0
