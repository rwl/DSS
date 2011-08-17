from dss.conversion.impl.VSourceObjImpl import (VSourceObjImpl,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.conversion.VSource import (VSource,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)


class VSourceImpl(PCClassImpl, VSource):
    activeVSourceObj = None

    def __init__(self):
        super(VSourceImpl, self)()
        self.className = 'Vsource'
        self.classType = DSSClassDefs.SOURCE + DSSClassDefs.NON_PCPD_ELEM
        # don't want this in PC element list
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = VSource.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'bus1'
        self.propertyName[1] = 'basekv'
        self.propertyName[2] = 'pu'
        self.propertyName[3] = 'angle'
        self.propertyName[4] = 'frequency'
        self.propertyName[5] = 'phases'
        self.propertyName[6] = 'MVAsc3'
        self.propertyName[7] = 'MVAsc1'
        self.propertyName[8] = 'x1r1'
        self.propertyName[9] = 'x0r0'
        self.propertyName[10] = 'Isc3'
        self.propertyName[11] = 'Isc1'
        self.propertyName[12] = 'R1'
        self.propertyName[13] = 'X1'
        self.propertyName[14] = 'R0'
        self.propertyName[15] = 'X0'
        self.propertyName[16] = 'ScanType'
        self.propertyName[17] = 'Sequence'
        self.propertyName[18] = 'bus2'
        # define property help values
        self.propertyHelp[0] = 'Name of bus to which the main terminal (1) is connected.' + DSSGlobals.CRLF + 'bus1=busname' + DSSGlobals.CRLF + 'bus1=busname.1.2.3'
        self.propertyHelp[1] = 'Base Source kV, usually phase-phase (L-L) unless you are making a positive-sequence model or 1-phase model' + 'in which case, it will be phase-neutral (L-N) kV.'
        self.propertyHelp[2] = 'Per unit of the base voltage that the source is actually operating at.' + DSSGlobals.CRLF + '\"pu=1.05\"'
        self.propertyHelp[3] = 'Phase angle in degrees of first phase: e.g.,Angle=10.3'
        self.propertyHelp[4] = 'Source frequency.  Defaults to system default base frequency.'
        self.propertyHelp[5] = 'Number of phases.  Defaults to 3.'
        self.propertyHelp[6] = 'MVA Short circuit, 3-phase fault. Default = 2000. ' + 'Z1 is determined by squaring the base kv and dividing by this value. ' + 'For single-phase source, this value is not used.'
        self.propertyHelp[7] = 'MVA Short Circuit, 1-phase fault. Default = 2100. ' + 'The \"single-phase impedance\", Zs, is determined by squaring the base kV and dividing by this value. ' + 'Then Z0 is determined by Z0 = 3Zs - 2Z1.  For 1-phase sources, Zs is used directly. ' + 'Use X0R0 to define X/R ratio for 1-phase source.'
        self.propertyHelp[8] = 'Positive-sequence  X/R ratio. Default = 4.'
        self.propertyHelp[9] = 'Zero-sequence X/R ratio.Default = 3.'
        self.propertyHelp[10] = 'Alternate method of defining the source impedance. ' + DSSGlobals.CRLF + '3-phase short circuit current, amps.  Default is 10000.'
        self.propertyHelp[11] = 'Alternate method of defining the source impedance. ' + DSSGlobals.CRLF + 'single-phase short circuit current, amps.  Default is 10500.'
        self.propertyHelp[12] = 'Alternate method of defining the source impedance. ' + DSSGlobals.CRLF + 'Positive-sequence resistance, ohms.  Default is 1.65.'
        self.propertyHelp[13] = 'Alternate method of defining the source impedance. ' + DSSGlobals.CRLF + 'Positive-sequence reactance, ohms.  Default is 6.6.'
        self.propertyHelp[14] = 'Alternate method of defining the source impedance. ' + DSSGlobals.CRLF + 'Zero-sequence resistance, ohms.  Default is 1.9.'
        self.propertyHelp[15] = 'Alternate method of defining the source impedance. ' + DSSGlobals.CRLF + 'Zero-sequence reactance, ohms.  Default is 5.7.'
        self.propertyHelp[16] = '{pos*| zero | none} Maintain specified sequence for harmonic solution. Default is positive sequence. ' + 'Otherwise, angle between phases rotates with harmonic.'
        self.propertyHelp[17] = '{pos*| neg | zero} Set the phase angles for the specified symmetrical component sequence for non-harmonic solution modes. ' + 'Default is positive sequence. '
        self.propertyHelp[18] = 'Name of bus to which 2nd terminal is connected.' + DSSGlobals.CRLF + 'bus2=busname' + DSSGlobals.CRLF + 'bus2=busname.1.2.3' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'Default is Bus1.0.0.0 (grounded wye connection)'
        self.activeProperty = VSource.NumPropsThisClass - 1
        super(VSourceImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list
        # override help string
        self.propertyHelp[VSource.NumPropsThisClass - 1] = 'Name of harmonic spectrum for this source.  Default is \"defaultvsource\", which is defined when the DSS starts.'

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(VSourceObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def vSourceSetBus1(self, s):
        # special handling for bus 1
        # set bus2=bus1.0.0.0
        avs = self.activeVSourceObj
        avs.setBus(1, s)
        # TODO Check zero based indexing
        # default bus2 to zero node of bus1. (Grounded-Y connection)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos]
        else:
            s2 = s[0:]
            # copy up to dot
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < avs.getNPhases()):
                break
            s2 = s2 + '.0'
            # append series of ".0"'s
        avs.setBus(2, s2)
        # default setting for bus2

    def edit(self):
        parser = Parser.getInstance()
        # continue parsing with contents of parser
        self.activeVSourceObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeVSourceObj)
        result = 0
        avs = self.activeVSourceObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                avs.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"VSource.' + avs.getName() + '\"', 320)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    self.vSourceSetBus1(param)
                    # special handling of bus 1
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    avs.setKVBase(parser.makeDouble())
                    # baseKV
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    avs.setPerUnit(parser.makeDouble())
                    # pu
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    avs.setAngle(parser.makeDouble())
                    # ang
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    avs.setSrcFrequency(parser.makeDouble())
                    # freq
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    avs.setNPhases(parser.makeInteger())
                    # num phases
                    avs.setNConds(avs.getNPhases())
                    # force reallocation of terminal info
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    avs.setMVAsc3(parser.makeDouble())
                    # MVAsc3
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    avs.setMVAsc1(parser.makeDouble())
                    # MVAsc1
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    avs.setX1R1(parser.makeDouble())
                    # X1/R1
                    break
                if (_1 is True) or (_0 == 9):
                    _1 = True
                    avs.setX0R0(parser.makeDouble())
                    # X0/R0
                    break
                if (_1 is True) or (_0 == 10):
                    _1 = True
                    avs.setIsc3(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 11):
                    _1 = True
                    avs.setIsc1(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 12):
                    _1 = True
                    avs.setR1(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 13):
                    _1 = True
                    avs.setX1(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 14):
                    _1 = True
                    avs.setR0(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 15):
                    _1 = True
                    avs.setX0(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 16):
                    _1 = True
                    _2 = param.toUpperCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'P':
                            _3 = True
                            avs.setScanType(1)
                            break
                        if (_3 is True) or (_2 == 'Z'):
                            _3 = True
                            avs.setScanType(0)
                            break
                        if (_3 is True) or (_2 == 'N'):
                            _3 = True
                            avs.setScanType(-1)
                            break
                        if True:
                            _3 = True
                            DSSGlobals.doSimpleMsg('Unknown scan type for \"' + self.getName() + '.' + avs.getName() + '\": ' + param, 321)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 17):
                    _1 = True
                    _4 = param.toUpperCase()[0]
                    _5 = False
                    while True:
                        if _4 == 'P':
                            _5 = True
                            avs.setSequenceType(1)
                            break
                        if (_5 is True) or (_4 == 'Z'):
                            _5 = True
                            avs.setSequenceType(0)
                            break
                        if (_5 is True) or (_4 == 'N'):
                            _5 = True
                            avs.setSequenceType(-1)
                            break
                        if True:
                            _5 = True
                            DSSGlobals.doSimpleMsg('Unknown sequence type for \"' + self.getName() + '.' + self.getName() + '\": ' + param, 321)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 18):
                    _1 = True
                    avs.setBus(2, param)
                    # TODO Check zero based indexing
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeVSourceObj, paramPointer - VSource.NumPropsThisClass)
                    break
                break
            # set the Z spec type switch depending on which was specified
            _6 = paramPointer
            _7 = False
            while True:
                if _6 == 6:
                    _7 = True
                    avs.setZSpecType(1)
                    break
                if (_7 is True) or (_6 == 7):
                    _7 = True
                    avs.setZSpecType(1)
                    break
                if (_7 is True) or (_6 == 10):
                    _7 = True
                    avs.setZSpecType(2)
                    break
                if (_7 is True) or (_6 == 11):
                    _7 = True
                    avs.setZSpecType(2)
                    break
                if (_7 is True) or (_6 == 12):
                    _7 = True
                    avs.setZSpecType(3)
                    break
                if (_7 is True) or (_6 == 13):
                    _7 = True
                    avs.setZSpecType(3)
                    break
                if (_7 is True) or (_6 == 14):
                    _7 = True
                    avs.setZSpecType(3)
                    break
                if (_7 is True) or (_6 == 15):
                    _7 = True
                    avs.setZSpecType(3)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        avs.recalcElementData()
        avs.setYPrimInvalid(True)
        return result

    def makeLike(self, otherSource):
        result = 0
        # See if we can find this line name in the present collection
        otherVSource = self.find(otherSource)
        if otherVSource is not None:
            avs = self.activeVSourceObj
            if avs.getNPhases() != otherVSource.getNPhases():
                avs.setNPhases(otherVSource.getNPhases())
                avs.setNConds(avs.getNPhases())
                # forces reallocation of terminal stuff
                avs.setYorder(avs.getNConds() * avs.getNTerms())
                avs.setYPrimInvalid(True)
                if avs.getZ() is not None:
                    avs.setZ(None)
                if avs.getZinv() is not None:
                    avs.setZinv(None)
                avs.setZ(CMatrixImpl(avs.getNPhases()))
                avs.setZinv(CMatrixImpl(avs.getNPhases()))
            avs.getZ().copyFrom(otherVSource.getZ())
            # avs.getZinv().copyFrom(OtherLine.getZinv());
            avs.setVMag(otherVSource.getVMag())
            avs.setKVBase(otherVSource.getKVBase())
            avs.setPerUnit(otherVSource.getPerUnit())
            avs.setAngle(otherVSource.getAngle())
            avs.setSrcFrequency(otherVSource.getSrcFrequency())
            avs.setMVAsc3(otherVSource.getMVAsc3())
            avs.setMVAsc1(otherVSource.getMVAsc1())
            avs.setX1R1(otherVSource.getX1R1())
            avs.setX0R0(otherVSource.getX0R0())
            avs.setScanType(otherVSource.getScanType())
            avs.setSequenceType(otherVSource.getSequenceType())
            self.classMakeLike(otherVSource)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < avs.getParentClass().getNumProperties()):
                    break
                avs.setPropertyValue(i, otherVSource.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in VSource makeLike: \"' + otherSource + '\" not found.', 322)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement VSource.init', -1)
        return 0
