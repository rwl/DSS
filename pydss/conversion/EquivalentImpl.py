from dss.conversion.impl.EquivalentObjImpl import (EquivalentObjImpl,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.conversion.Equivalent import (Equivalent,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)


class EquivalentImpl(PCClassImpl, Equivalent):
    activeEquivalentObj = None

    def __init__(self):
        super(EquivalentImpl, self)()
        self.className = 'Equivalent'
        self.classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM
        # don"t want this in PC element list
        self.activeElement = -1
        self.defineProperties()
        Commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, Commands, 0, self.numProperties)
        self.commandList = CommandListImpl(Commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = Equivalent.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'terminals'
        self.propertyName[1] = 'buses'
        self.propertyName[2] = 'basekv'
        self.propertyName[3] = 'pu'
        self.propertyName[4] = 'angle'
        self.propertyName[5] = 'frequency'
        self.propertyName[6] = 'phases'
        self.propertyName[7] = 'R1'
        self.propertyName[8] = 'X1'
        self.propertyName[9] = 'R0'
        self.propertyName[10] = 'X0'
        # define property help values
        self.propertyHelp[0] = 'Number of terminals.  Default =1. Set this BEFORE defining matrices.'
        self.propertyHelp[1] = 'Array of Bus Names to which equivalent source is connected.' + DSSGlobals.CRLF + 'buses=(b1 b2 b3)'
        self.propertyHelp[2] = 'Base Source kV, usually L-L unless you are making a positive-sequence model' + 'in which case, it will be L-N.'
        self.propertyHelp[3] = 'Per unit of the base voltage that the source is actually operating at.' + DSSGlobals.CRLF + '\"pu=1.05\"'
        self.propertyHelp[4] = 'Phase angle in degrees of first phase: e.g.,Angle=10.3'
        self.propertyHelp[5] = 'Source frequency.  Defaults to  60 Hz.'
        self.propertyHelp[6] = 'Number of phases.  Defaults to 3.'
        self.propertyHelp[7] = 'Positive-sequence resistance matrix, lower triangle.'
        self.propertyHelp[8] = 'Positive-sequence reactance matrix, lower triangle.'
        self.propertyHelp[9] = 'Zero-sequence resistance matrix, lower triangle.'
        self.propertyHelp[10] = 'Zero-sequence reactance matrix, lower triangle.'
        self.activeProperty = Equivalent.NumPropsThisClass
        super(EquivalentImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override help string
        self.propertyHelp[Equivalent.NumPropsThisClass + 1] = 'Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.'

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(EquivalentObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeEquivalentObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeEquivalentObj)
        result = 0
        ae = self.activeEquivalentObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                ae.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"Equivalent.' + ae.getName() + '\"', 800)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    ae.setNTerms(ae.doTerminalsDef(parser.makeInteger()))
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    self.interpretAllBuses(param)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    ae.setKVBase(parser.makeDouble())
                    # basekv
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    ae.setPerUnit(parser.makeDouble())
                    # pu
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    ae.setAngle(parser.makeDouble())
                    # ang
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    ae.setEquivFrequency(parser.makeDouble())
                    # freq
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    ae.setNPhases(parser.makeInteger())
                    # num phases
                    ae.setNConds(ae.getNPhases())
                    # force reallocation of terminal info
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    ae.parseDblMatrix(ae.getR1())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    ae.parseDblMatrix(ae.getX1())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    ae.parseDblMatrix(ae.getR0())
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    ae.parseDblMatrix(ae.getX0())
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeEquivalentObj, paramPointer - Equivalent.NumPropsThisClass)
                    break
                break
            if (paramPointer == 0) or (paramPointer >= 7 and paramPointer <= 10):
                ae.setNeedToDoRecalc(True)
            paramName = parser.getNextParam()
            param = parser.makeString()
        # recalcElementData();
        ae.setYPrimInvalid(True)
        return result

    def makeLike(self, OtherSource):
        result = 0
        # See if we can find this line name in the present collection
        otherEquivalent = self.find(OtherSource)
        if otherEquivalent is not None:
            ae = self.activeEquivalentObj
            if (
                (ae.getNPhases() != otherEquivalent.getNPhases()) or (ae.getNTerms() != otherEquivalent.getNTerms())
            ):
                ae.setNTerms(ae.doTerminalsDef(otherEquivalent.getNTerms()))
                ae.setNPhases(otherEquivalent.getNPhases())
                ae.setNConds(ae.getNPhases())
                # forces reallocation of terminal stuff
                ae.setYorder(ae.getNConds() * ae.getNTerms())
                ae.setYPrimInvalid(True)
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < ae.getNTerms()):
                        break
                    ae.getR1()[i] = otherEquivalent.getR1()[i]
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < ae.getNTerms()):
                        break
                    ae.getR0()[i] = otherEquivalent.getR0()[i]
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < ae.getNTerms()):
                        break
                    ae.getX1()[i] = otherEquivalent.getX1()[i]
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < ae.getNTerms()):
                        break
                    ae.getX0()[i] = otherEquivalent.getX0()[i]
                if ae.getZ() is not None:
                    ae.setZ(None)
                if ae.getZinv() is not None:
                    ae.setZInv(None)
                ae.setZ(CMatrixImpl(ae.getNPhases()))
                ae.setZInv(CMatrixImpl(ae.getNPhases()))
            ae.getZ().copyFrom(otherEquivalent.getZ())
            # ae.getZinv().copyFrom(OtherLine.getZinv());
            ae.setVMag(otherEquivalent.getVMag())
            ae.setKVBase(otherEquivalent.getKVBase())
            ae.setPerUnit(otherEquivalent.getPerUnit())
            ae.setAngle(otherEquivalent.getAngle())
            ae.setEquivFrequency(otherEquivalent.getEquivFrequency())
            self.classMakeLike(otherEquivalent)
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < ae.getParentClass().getNumProperties()):
                    break
                ae.setPropertyValue(i, otherEquivalent.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Equivalent makeLike: \"' + OtherSource + '\" not found.', 801)
        return result

    def init(self, Handle):
        DSSGlobals.doSimpleMsg('Need to implement Equivalent.init', -1)
        return 0

    def interpretAllBuses(self, s):
        """Routine expecting all winding connections expressed in one array of strings."""
        DSSGlobals.auxParser.setCmdString(s)
        # load up parser
        # Loop for no more than the expected number of windings; ignore omitted values
        ae = self.activeEquivalentObj
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < ae.getNTerms()):
                break
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name  not expecting any
            busName = DSSGlobals.auxParser.makeString()
            if len(busName) > 0:
                ae.setBus(i, busName)
                # TODO Check zero based indexing
