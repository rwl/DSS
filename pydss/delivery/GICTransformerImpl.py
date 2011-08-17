from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.impl.PDClassImpl import (PDClassImpl,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.delivery.impl.GICTransformerObjImpl import (GICTransformerObjImpl,)
from dss.delivery.GICTransformer import (GICTransformer,)
from dss.parser.impl.Parser import (Parser,)


class GICTransformerImpl(PDClassImpl, GICTransformer):
    activeGICTransformerObj = None

    def __init__(self):
        super(GICTransformerImpl, self)()
        self.className = 'GICTransformer'
        self.classType = DSSClassDefs.GIC_TRANSFORMER + DSSClassDefs.PD_ELEMENT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)
        # allow property list abbreviations

    def defineProperties(self):
        CRLF = DSSGlobals.CRLF
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # define property names
        self.propertyName[0] = 'BusH'
        self.propertyName[1] = 'BusNH'
        self.propertyName[2] = 'BusX'
        self.propertyName[3] = 'BusNX'
        self.propertyName[4] = 'phases'
        self.propertyName[5] = 'Type'
        self.propertyName[6] = 'R1'
        self.propertyName[7] = 'R2'
        # define property help values
        self.propertyHelp[0] = 'Name of High-side(H) bus. Examples:' + CRLF + 'BusH=busname' + CRLF + 'BusH=busname.1.2.3'
        self.propertyHelp[1] = 'Name of Neutral bus for H, or first, winding. Defaults to all phases connected ' + 'to H-side bus, node 0, if not specified and transformer type is either GSU or YY. ' + '(Shunt Wye Connection to ground reference)' + 'For Auto, this is automatically set to the X bus.'
        self.propertyHelp[2] = 'Name of Low-side(X) bus, if type=Auto or YY. '
        self.propertyHelp[3] = 'Name of Neutral bus for X, or Second, winding. Defaults to all phases connected ' + 'to X-side bus, node 0, if not specified. (Shunt Wye Connection to ground reference)'
        self.propertyHelp[4] = 'Number of Phases. Default is 3.'
        self.propertyHelp[5] = 'Type of transformer: {GSU* | Auto | YY}. Default is GSU.'
        self.propertyHelp[6] = 'Resistance, each phase, ohms for H winding, (Series winding, if Auto). Default is 0.0001. '
        self.propertyHelp[7] = 'Resistance, each phase, ohms for X winding, (Common winding, if Auto). Default is 0.0001. '
        self.activeProperty = self.NumPropsThisClass - 1
        super(GICTransformerImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeCircuit.setActiveCktElement(GICTransformerObjImpl(self, objName))
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def GICTransSetBusH(self, s):
        # set bus2 = busH1.0.0.0
        agt = self.activeGICTransformerObj
        agt.setBus(1, s)
        # default bus2 to zero node of bus1. (wye grounded connection)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos]
            # copy up to dot
        else:
            s2 = s[0:]
        s2 = s2 + '.0.0.0'
        # set default for up to 3 phases
        agt.setBus(2, s2)
        agt.setShunt(True)

    def GICTransSetBusX(self, s):
        # special handling for bus X
        # make sure we have enough terminals defined
        # set bus2 = bus1.0.0.0
        agt = self.activeGICTransformerObj
        if agt.getNTerms() != 4:
            # have to have 4 terminals to set this property
            agt.setNTerms(4)
            agt.setNConds(agt.getNPhases())
            # force reallocation of terminals and conductors
        agt.setBus(3, s)
        # default bus4 to zero node of bus3. (wye grounded connection)
        # strip node designations from s
        dotpos = s.find('.')
        if dotpos >= 0:
            s2 = s[:dotpos]
            # copy up to dot
        else:
            s2 = s[0:]
        s2 = s2 + '.0.0.0'
        # set default for up to 3 phases
        agt.setBus(4, s2)
        agt.setShunt(True)

    def edit(self):
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        self.activeGICTransformerObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeGICTransformerObj)
        # use property to set this value
        agt = self.activeGICTransformerObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                agt.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + self.getName() + '\"', 350)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    self.GICTransSetBusH(param)
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    agt.setBus(2, param)
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    self.GICTransSetBusX(param)
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    agt.setBus(4, param)
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    _2 = param.toUpperCase()[0]
                    _3 = False
                    while True:
                        if _2 == 'G':
                            _3 = True
                            agt.setSpecType(self.SPEC_GSU)
                            break
                        if (_3 is True) or (_2 == 'A'):
                            _3 = True
                            agt.setSpecType(self.SPEC_AUTO)
                            break
                        if (_3 is True) or (_2 == 'Y'):
                            _3 = True
                            agt.setSpecType(self.SPEC_YY)
                            break
                        break
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    agt.setG1(parser.makeDouble())
                    if agt.getG1() != 0.0:
                        agt.setG1(1.0 / agt.getG1())
                    else:
                        agt.setG1(10000.0)
                        # default to a low resistance
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    agt.setG2(parser.makeDouble())
                    if agt.getG2() != 0.0:
                        agt.setG2(1.0 / agt.getG2())
                    else:
                        agt.setG2(10000.0)
                        # default to a low resistance
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeGICTransformerObj, paramPointer - self.NumPropsThisClass)
                    break
                break
            # some specials ...
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 0:
                    _5 = True
                    agt.setPropertyValue(1, agt.getBus(1))
                    # bus2 gets modified if bus1 is set
                    break
                if (_5 is True) or (_4 == 2):
                    _5 = True
                    agt.setPropertyValue(3, agt.getBus(3))
                    # bus4 gets modified if bus3(X) is set
                    if agt.getSpecType() == self.SPEC_AUTO:
                        # automatically make up series-to-common connection
                        agt.setBus(2, agt.getBus(2))
                        agt.setPropertyValue(1, agt.getBus(1))
                    break
                if (_5 is True) or (_4 == 4):
                    _5 = True
                    if agt.getNPhases() != parser.makeInteger():
                        agt.setNPhases(parser.makeInteger())
                        agt.setNConds(agt.getNPhases())
                        # force reallocation of terminal info if different size
                        DSSGlobals.activeCircuit.setBusNameRedefined(True)
                        # set global flag to signal circuit to rebuild bus defs
                    break
                if (_5 is True) or (_4 == 5):
                    _5 = True
                    _6 = agt.getSpecType()
                    _7 = False
                    while True:
                        if _6 == self.SPEC_AUTO:
                            _7 = True
                            if agt.getNTerms() == 2:
                                agt.setNTerms(4)
                                agt.setNConds(agt.getNPhases())
                            agt.setBus(2, agt.getBus(2))
                            break
                        break
                    break
                break
            # YPrim invalidation on anything that changes impedance values or no. of terminals
            if paramPointer >= 2 and paramPointer <= 7:
                agt.setYPrimInvalid(True)
            paramName = parser.getNextParam()
            param = parser.makeString()
        agt.recalcElementData()
        return result

    def makeLike(self, GICTransName):
        result = 0
        # See if we can find this Fault name in the present collection
        otherGICTrans = self.find(GICTransName)
        if otherGICTrans is not None:
            agt = self.activeGICTransformerObj
            if agt.getNPhases() != otherGICTrans.getNPhases():
                agt.setNPhases(otherGICTrans.getNPhases())
                agt.setNTerms(otherGICTrans.getNTerms())
                agt.setNConds(agt.getNPhases())
                # force reallocation of terminals and conductors
                agt.setYorder(agt.getNConds() * agt.getNTerms())
                agt.setYPrimInvalid(True)
            agt.setBaseFrequency(otherGICTrans.getBaseFrequency())
            agt.setG1(otherGICTrans.getG1())
            agt.setG2(otherGICTrans.getG2())
            agt.setSpecType(otherGICTrans.getSpecType())
            self.classMakeLike(otherGICTrans)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < agt.getParentClass().getNumProperties()):
                    break
                agt.setPropertyValue(i, otherGICTrans.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in GICTransformer makeLike: \"' + GICTransName + '\" not found.', 351)
        return result

    def init(self, Handle):
        DSSGlobals.doSimpleMsg('Need to implement GICTransformer.init', -1)
        return 0
