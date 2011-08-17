from dss.common.Feeder import (Feeder,)
from dss.conversion.impl.PCClassImpl import (PCClassImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.common.impl.FeederObjImpl import (FeederObjImpl,)
from dss.parser.impl.Parser import (Parser,)


class FeederImpl(PCClassImpl, Feeder):
    NumPropsThisClass = 0
    activeFeederObj = None

    def __init__(self):
        super(FeederImpl, self)()
        self.className = 'Feeder'
        self.classType = DSSClassDefs.FEEDER_ELEMENT
        # + PC_ELEMENT;
        # add to PCElement list
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.NumPropsThisClass = 0
        self.numProperties = self.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        # Can't think of any properties we want the user to be able to set
        # Define Property names
        # PropertyName[0] = "bus1";
        # define Property help values
        # PropertyHelp[0] = "Name of bus to which source is connected."+DSSGlobals.CRLF+"bus1=busname"+DSSGlobals.CRLF+"bus1=busname.1.2.3";
        self.activeProperty = self.NumPropsThisClass - 1
        super(FeederImpl, self).defineProperties()
        # Add defs of inherited properties to bottom of list

    def newObject(self, objName):
        """Called from EnergyMeter."""
        # make a new feeder object
        # first see if this one already exists; if so, just reinitialize
        obj = self.find(objName)
        ckt = DSSGlobals.activeCircuit
        if obj is not None:
            ckt.setActiveCktElement(obj)
            result = 0
        else:
            ckt.setActiveCktElement(FeederObjImpl(self, objName))
            result = self.addObjectToList(DSSGlobals.activeDSSObject)
            ckt.addCktElement(result)
            # done here because feeder objects are instantiated from energy meters
        return result

    def edit(self):
        # continue parsing with contents of parser
        self.activeFeederObj = self.elementList.getActive()
        DSSGlobals.activeCircuit.setActiveCktElement(self.activeFeederObj)
        result = 0
        paramPointer = 0
        paramName = Parser.getInstance().getNextParam()
        param = Parser.getInstance().makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer > 0 and paramPointer <= self.numProperties:
                self.activeFeederObj.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + self.activeFeederObj.getName() + '\"', 630)
                    break
                if True:
                    _1 = True
                    self.classEdit(self.activeFeederObj, paramPointer - self.NumPropsThisClass)
                    break
                break
            paramName = Parser.getInstance().getNextParam()
            param = Parser.getInstance().makeString()
        self.activeFeederObj.recalcElementData()
        self.activeFeederObj.setYPrimInvalid(True)
        return result

    def makeLike(self, otherFeederName):
        result = 0
        # See if we can find this name in the present collection
        otherFeeder = self.find(otherFeederName)
        if otherFeeder is not None:
            if self.activeFeederObj.getNPhases() != otherFeeder.getNPhases():
                self.activeFeederObj.setNPhases(otherFeeder.getNPhases())
                self.activeFeederObj.setNConds(self.activeFeederObj.getNPhases())
                # forces reallocation of terminal stuff
                self.activeFeederObj.setYorder(self.activeFeederObj.getNConds() * self.activeFeederObj.getNTerms())
                self.activeFeederObj.setYPrimInvalid(True)
            # put properties to copy here
            self.classMakeLike(otherFeeder)
            # set spectrum, base frequency
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.activeFeederObj.getParentClass().getNumProperties()):
                    break
                self.activeFeederObj.setPropertyValue(i, otherFeeder.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in Feeder makeLike: \"' + otherFeederName + '\" not found.', 631)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement Feeder.init()', -1)
        return 0
