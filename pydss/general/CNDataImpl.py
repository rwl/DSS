from dss.general.impl.ConductorDataImpl import (ConductorDataImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.CommandListImpl import (CommandListImpl,)
from dss.common.impl.DSSClassDefs import (DSSClassDefs,)
from dss.general.impl.CableDataImpl import (CableDataImpl,)
from dss.general.impl.CNDataObjImpl import (CNDataObjImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.general.CNData import (CNData,)


class CNDataImpl(CableDataImpl, CNData):

    def __init__(self):
        super(CNDataImpl, self)()
        self.className = 'CNData'
        self.classType = DSSClassDefs.DSS_OBJECT
        self.activeElement = -1
        self.defineProperties()
        commands = [None] * self.numProperties
        System.arraycopy(self.propertyName, 0, commands, 0, self.numProperties)
        self.commandList = CommandListImpl(commands)
        self.commandList.setAbbrevAllowed(True)

    def defineProperties(self):
        self.numProperties = CNData.NumPropsThisClass
        self.countProperties()
        # get inherited property count
        self.allocatePropertyArrays()
        self.propertyName[0] = 'k'
        self.propertyName[1] = 'DiaStrand'
        self.propertyName[2] = 'GmrStrand'
        self.propertyName[3] = 'Rstrand'
        self.propertyHelp[0] = 'Number of concentric neutral strands; default is 2'
        self.propertyHelp[1] = 'Diameter of a concentric neutral strand; same units as core conductor radius; no default.'
        self.propertyHelp[2] = 'Geometric mean radius of a concentric neutral strand; same units as core conductor GMR; defaults to 0.7788 * CN strand radius.'
        self.propertyHelp[3] = 'AC resistance of a concentric neutral strand; same units as core conductor resistance; no default.'
        self.activeProperty = CNData.NumPropsThisClass - 1
        super(CNDataImpl, self).defineProperties()
        # add defs of inherited properties to bottom of list

    def newObject(self, objName):
        DSSGlobals.activeDSSObject = CNDataObjImpl(self, objName)
        return self.addObjectToList(DSSGlobals.activeDSSObject)

    def edit(self):
        """Uses global parser."""
        parser = Parser.getInstance()
        result = 0
        # continue parsing with contents of parser
        ConductorDataImpl.activeConductorDataObj = self.elementList.getActive()
        DSSGlobals.activeDSSObject = ConductorDataImpl.activeConductorDataObj
        acd = ConductorDataImpl.activeConductorDataObj
        paramPointer = 0
        paramName = parser.getNextParam()
        param = parser.makeString()
        while len(param) > 0:
            if len(paramName) == 0:
                paramPointer += 1
            else:
                paramPointer = self.commandList.getCommand(paramName)
            if paramPointer >= 0 and paramPointer < self.numProperties:
                acd.setPropertyValue(paramPointer, param)
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == -1:
                    _1 = True
                    DSSGlobals.doSimpleMsg('Unknown parameter \"' + paramName + '\" for object \"' + self.getName() + '.' + self.getName() + '\"', 101)
                    break
                if (_1 is True) or (_0 == 0):
                    _1 = True
                    acd.setkStrand(parser.makeInteger())
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    acd.setDiaStrand(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 2):
                    _1 = True
                    acd.setGmrStrand(parser.makeDouble())
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    acd.setRStrand(parser.makeDouble())
                    break
                if True:
                    _1 = True
                    self.classEdit(ConductorDataImpl.activeConductorDataObj, paramPointer - CNData.NumPropsThisClass)
                    break
                break
            # Set defaults
            _2 = paramPointer
            _3 = False
            while True:
                if _2 == 1:
                    _3 = True
                    if acd.getGmrStrand() <= 0.0:
                        acd.setGmrStrand(0.7788 * 0.5 * acd.getDiaStrand())
                    break
                break
            # Check for critical errors
            _4 = paramPointer
            _5 = False
            while True:
                if _4 == 0:
                    _5 = True
                    if acd.getkStrand() < 2:
                        DSSGlobals.doSimpleMsg('Error: Must have at least 2 concentric neutral strands for CNData ' + acd.getName(), 999)
                    break
                if (_5 is True) or (_4 == 1):
                    _5 = True
                    if acd.getDiaStrand() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Neutral strand diameter must be positive for CNData ' + acd.getName(), 999)
                    break
                if (_5 is True) or (_4 == 2):
                    _5 = True
                    if acd.getGmrStrand() <= 0.0:
                        DSSGlobals.doSimpleMsg('Error: Neutral strand GMR must be positive for CNData ' + acd.getName(), 999)
                    break
                break
            paramName = parser.getNextParam()
            param = parser.makeString()
        return result

    def makeLike(self, CNName):
        acd = ConductorDataImpl.activeConductorDataObj
        result = 0
        otherData = self.find(CNName)
        if otherData is not None:
            acd.setkStrand(otherData.getkStrand())
            acd.setDiaStrand(otherData.getDiaStrand())
            acd.setGmrStrand(otherData.getGmrStrand())
            acd.setRStrand(otherData.getRStrand())
            self.classMakeLike(otherData)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < acd.getParentClass().getNumProperties()):
                    break
                acd.setPropertyValue(i, otherData.getPropertyValue(i))
            result = 1
        else:
            DSSGlobals.doSimpleMsg('Error in concentric neutral makeLike: \"' + CNName + '\" not found.', 102)
        return result

    def init(self, handle):
        DSSGlobals.doSimpleMsg('Need to implement CNData.init().', -1)
        return 0

    def getCode(self):
        """Returns active line code string."""
        return self.elementList.getActive().getName()

    def setCode(self, value):
        """Sets the active CNData."""
        ConductorDataImpl.activeConductorDataObj = None
        pCNDataObj = self.elementList.getFirst()
        while pCNDataObj is not None:
            if pCNDataObj.getName().equalsIgnoreCase(value):
                ConductorDataImpl.activeConductorDataObj = pCNDataObj
                return
            pCNDataObj = self.elementList.getNext()
        DSSGlobals.doSimpleMsg('CNData: \"' + value + '\" not found.', 103)
