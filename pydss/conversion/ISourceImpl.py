from dss.conversion.impl.ISourceObjImpl import (ISourceObjImpl,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.conversion.ISource import (ISource,)


class ISourceImpl(PCClassImpl, ISource):
    activeISourceObj = None

    def __init__(self):
        super(ISourceImpl, self)()
        self.className = 'Isource'
        self.classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM
        # don"t want this in PC element list
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = ISource.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'bus1'
        self.propertyName[1] = 'amps'
        self.propertyName[2] = 'angle'
        self.propertyName[3] = 'frequency'
        self.propertyName[4] = 'phases'
        self.propertyName[5] = 'scantype'
        self.propertyName[6] = 'sequence'
        # define property help values
        self.propertyHelp[0] = 'Name of bus to which source is connected.' + DSSGlobals.CRLF + 'bus1=busname' + DSSGlobals.CRLF + 'bus1=busname.1.2.3'
        self.propertyHelp[1] = 'Magnitude of current source, each phase, in Amps.'
        self.propertyHelp[2] = 'Phase angle in degrees of first phase: e.g.,Angle=10.3.' + DSSGlobals.CRLF + 'Phase shift between phases is assumed 120 degrees when ' + 'number of phases <= 3'
        self.propertyHelp[3] = 'Source frequency.  Defaults to  circuit fundamental frequency.'
        self.propertyHelp[4] = 'Number of phases.  Defaults to 3. For 3 or less, phase shift is 120 degrees.'
        self.propertyHelp[5] = '{pos*| zero | none} Maintain specified sequence for harmonic solution. Default is positive sequence. ' + 'Otherwise, angle between phases rotates with harmonic.'
        self.propertyHelp[6] = '{pos*| neg | zero} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. ' + 'Default is positive sequence.'
        self.activeProperty = ISource.NumPropsThisClass - 1
        # TODO Check zero based indexing
        super(ISourceImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override help string
        self.propertyHelp[ISource.NumPropsThisClass - 1] = 'Harmonic spectrum assumed for this source.  Default is \"default\".'
        # TODO Check zero based indexing

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(ISourceObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeISourceObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeISourceObj)
        result = 0
        ais = self.activeISourceObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                ais.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + ais.getName() + '\"', 330)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    ais.setBus(1, param)
                    # TODO Check zero based indexing
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    ais.setAmps(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    ais.setAngle(parser.makeDouble())
                    # ang
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    ais.setSrcFrequency(parser.makeDouble())
                    # freq
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    ais.setNPhases(parser.makeInteger())
                    # num phases
                    _2 = ais.getNPhases()
                    _3 = False
                    while True:
                        if _2 == 1:
                            _3 = True
                            ais.setPhaseShift(0.0)
                            break
                        if (_3 is True) or (_2 == 2):
                            _3 = True
                            ais.setPhaseShift(120.0)
                            break
                        if (_3 is True) or (_2 == 3):
                            _3 = True
                            ais.setPhaseShift(120.0)
                            break
                        if True:
                            _3 = True
                            ais.setPhaseShift(360.0 / ais.getNPhases())
                            break
                        break
                    ais.setNConds(ais.getNPhases())
                    # force reallocation of terminal info
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    _4 = param.toUpperCase()[0]
                    _5 = False
                    while True:
                        if _4 == 'P':
                            _5 = True
                            ais.setScanType(1)
                            break
                        if (_5 is True) or (_4 == 'Z'):
                            _5 = True
                            ais.setScanType(0)
                            break
                        if (_5 is True) or (_4 == 'N'):
                            _5 = True
                            ais.setScanType(-1)
                            break
                        if True:
                            _5 = True
                            DSSGlobals.doSimpleMsg('Unknown scan type for \"' + self.getName() + '.' + ais.getName() + '\": ' + param, 331)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    _6 = param.toUpperCase()[0]
                    _7 = False
                    while True:
                        if _6 == 'P':
                            _7 = True
                            ais.setSequenceType(1)
                            break
                        if (_7 is True) or (_6 == 'Z'):
                            _7 = True
                            ais.setSequenceType(0)
                            break
                        if (_7 is True) or (_6 == 'N'):
                            _7 = True
                            ais.setSequenceType(-1)
                            break
                        if True:
                            _7 = True
                            DSSGlobals.doSimpleMsg('Unknown sequence type for \"' + self.getName() + '.' + ais.getName() + '\": ' + param, 331)
                            break
                        break
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeISourceObj, paramPointer - ISource.NumPropsThisClass)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        ais.recalcElementData()
        ais.setYPrimInvalid(True)
        return result

    def makeLike(self, otherSource):
        result = 0
        # See if we can find this line name in the present collection
        otherISource = self.find(otherSource)
        if otherISource is not None:
            ais = self.activeISourceObj
            if ais.getNPhases() != otherISource.getNPhases():
                ais.setNPhases(otherISource.getNPhases())
                ais.setNConds(ais.getNPhases())
                # forces reallocation of terminal stuff
                ais.setYorder(ais.getNConds() * ais.getNTerms())
                ais.setYPrimInvalid(True)
            ais.setAmps(otherISource.getAmps())
            ais.setAngle(otherISource.getAngle())
            ais.setSrcFrequency(otherISource.getSrcFrequency())
            ais.setScanType(otherISource.getScanType())
            ais.setSequenceType(otherISource.getSequenceType())
            self.classMakeLike(otherISource)
            # set spectrum, base frequency
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < ais.getParentClass().getNumProperties()):
                    break
                ais.setPropertyValue(i, otherISource.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in ISource makeLike: \"' + otherSource + '\" not found.', 332)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement ISource.init', -1)
        return 0
