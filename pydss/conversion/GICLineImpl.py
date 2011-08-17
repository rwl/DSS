from dss.conversion.impl.GICLineObjImpl import (GICLineObjImpl,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.conversion.GICLine import (GICLine,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)


class GICLineImpl(PCClassImpl, GICLine):
    activeGICLineObj = None

    def __init__(self):
        super(GICLineImpl, self)()
        self.className = 'GICLine'
        self.classType = DSSClassDefs.GIC_LINE + DSSClassDefs.PC_ELEMENT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'bus1'
        self.propertyName[1] = 'bus2'
        self.propertyName[2] = 'Volts'
        self.propertyName[3] = 'Angle'
        self.propertyName[4] = 'frequency'
        self.propertyName[5] = 'phases'
        self.propertyName[6] = 'R'
        self.propertyName[7] = 'X'
        self.propertyName[8] = 'C'
        self.propertyName[9] = 'ScanType'
        self.propertyName[10] = 'Sequence'
        # define property help values
        self.propertyHelp[0] = 'Name of bus to which the main terminal (1) is connected.' + CRLF + 'bus1=busname' + CRLF + 'bus1=busname.1.2.3'
        self.propertyHelp[1] = 'Name of bus to which 2nd terminal is connected.' + CRLF + 'bus2=busname' + CRLF + 'bus2=busname.1.2.3' + CRLF + CRLF + 'No Default; must be specified.'
        self.propertyHelp[2] = 'Voltage magnitude, in volts, of the GIC voltage induced across this line.'
        self.propertyHelp[3] = 'Phase angle in degrees of first phase. Default=0.0'
        self.propertyHelp[4] = 'Source frequency.  Defaults to 0.1 Hz.'
        self.propertyHelp[5] = 'Number of phases.  Defaults to 3.'
        self.propertyHelp[6] = 'Resistance of line, ohms of impedance in series with GIC voltage source. '
        self.propertyHelp[7] = 'Reactance at base frequency, ohms. Default = 0.0. This value is generally not important for GIC studies but may be used if desired.'
        self.propertyHelp[8] = 'Value of line blocking capacitance in microfarads. Default = 0.0, implying that there is no line blocking capacitor.'
        self.propertyHelp[9] = '{pos | zero* | none} Maintain specified sequence for harmonic solution. Default is ZERO sequence. ' + 'Otherwise, angle between phases rotates with harmonic.'
        self.propertyHelp[10] = '{pos | neg | zero*} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. ' + 'Default is ZERO sequence. '
        self.activeProperty = self.NumPropsThisClass - 1
        super(GICLineImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override help string
        self.propertyHelp[self.NumPropsThisClass] = 'Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.'

    def newObject(self, ObjName):
        DSSGlobals.activeCircuit.setActiveCktElement(GICLineObjImpl(self, ObjName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def GICLineSetBus1(self, s):
        # special handling for bus 1
        # set bus2=bus1.0.0.0
        agl = self.activeGICLineObj
        agl.setBus(1, s)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos - 1]
            # copy up to dot
        else:
            s2 = s[0:]
        agl.setBus(2, s2)
        # default setting for bus2 is same as bus1

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeGICLineObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeGICLineObj)
        result = 0
        agl = self.activeGICLineObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                agl.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"VSource.' + agl.getName() + '\"', 320)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    self.GICLineSetBus1(param)
                    # special handling of bus 1
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    agl.setBus(2, param)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    agl.setVolts(parser.makeDouble())
                    # basekv
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    agl.setAngle(parser.makeDouble())
                    # ang
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    agl.setSrcFrequency(parser.makeDouble())
                    # freq
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    agl.setNPhases(parser.makeInteger())
                    # num phases
                    agl.setNConds(agl.getNPhases())
                    # force reallocation of terminal info
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    agl.setR(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    agl.setX(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    agl.setC(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    _2 = param.toUpperCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'P':
                            _3 = True
                            agl.setScanType(1)
                            break
                        if (_3 is True) or (_2 == 'Z'):
                            _3 = True
                            agl.setScanType(0)
                            break
                        if (_3 is True) or (_2 == 'N'):
                            _3 = True
                            agl.setScanType(-1)
                            break
                        if True:
                            _3 = True
                            DSSGlobals.doSimpleMsg('Unknown scan type for \"' + self.getName() + '.' + agl.getName() + '\": ' + param, 321)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    _4 = param.toUpperCase()[0]
                    _5 = False
                    while True:
                        if _4 == 'P':
                            _5 = True
                            agl.setSequenceType(1)
                            break
                        if (_5 is True) or (_4 == 'Z'):
                            _5 = True
                            agl.setSequenceType(0)
                            break
                        if (_5 is True) or (_4 == 'N'):
                            _5 = True
                            agl.setSequenceType(-1)
                            break
                        if True:
                            _5 = True
                            DSSGlobals.doSimpleMsg('Unknown sequence type for \"' + self.getName() + '.' + agl.getName() + '\": ' + param, 321)
                            break
                        break
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeGICLineObj, paramPointer - self.NumPropsThisClass)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        agl.recalcElementData()
        agl.setYPrimInvalid(True)
        return result

    def makeLike(self, otherLine):
        result = 0
        # See if we can find this line name in the present collection
        otherGICLine = self.find(otherLine)
        if otherGICLine is not None:
            agl = self.activeGICLineObj
            if agl.getNPhases() != otherGICLine.getNPhases():
                agl.setNPhases(otherGICLine.getNPhases())
                agl.setNConds(agl.getNPhases())
                # forces reallocation of terminal stuff
                agl.setYorder(agl.getNConds() * agl.getNTerms())
                agl.setYPrimInvalid(True)
                if agl.getZ() is not None:
                    agl.setZ(None)
                if agl.getZInv() is not None:
                    agl.setZInv(None)
                agl.setZ(CMatrixImpl(agl.getNPhases()))
                agl.setZInv(CMatrixImpl(agl.getNPhases()))
            agl.getZ().copyFrom(otherGICLine.getZ())
            # Zinv.CopyFrom(OtherLine.Zinv);
            agl.setR(otherGICLine.getR())
            agl.setX(otherGICLine.getX())
            agl.setC(otherGICLine.getC())
            agl.setVolts(otherGICLine.getVolts())
            agl.setAngle(otherGICLine.getAngle())
            agl.setSrcFrequency(otherGICLine.getSrcFrequency())
            agl.setScanType(otherGICLine.getScanType())
            agl.setSequenceType(otherGICLine.getSequenceType())
            self.classMakeLike(otherGICLine)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < agl.getParentClass().getNumProperties()):
                    break
                agl.setPropertyValue(i, otherGICLine.getPropertyValue(i))
                result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in GICLine makeLike: \"' + otherLine + '\" not found.', 322)
        return result

    def init(self, Handle):
        DSSGlobals.doSimpleMsg('Need to implement GICLine.init', -1)
        return 0
