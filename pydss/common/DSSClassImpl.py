from dss.shared.impl.PointerListImpl import (PointerListImpl,)
from dss.common.DSSClass import (DSSClass,)
from dss.shared.impl.HashListImpl import (HashListImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.parser.impl.Parser import (Parser,)


class DSSClassImpl(object, DSSClass):
    """Base class for all DSS collection classes.
    Keeps track of objects of each class, dispatches edits, etc
    """
    DSSClasses = None
    className = None
    activeElement = None
    commandList = None
    activeProperty = None
    elementNameList = None
    numProperties = None
    propertyName = None
    propertyHelp = None
    propertyIdxMap = None
    revPropertyIdxMap = None
    classType = None
    elementList = None
    elementNamesOutOfSynch = None
    saved = None

    def __init__(self):
        super(DSSClassImpl, self)()
        # init size and increment
        self.elementList = PointerListImpl(20)
        self.propertyName = None
        self.propertyHelp = None
        self.propertyIdxMap = None
        self.revPropertyIdxMap = None
        self.activeElement = -1
        self.activeProperty = -1
        self.elementNameList = HashListImpl(100)
        self.elementNamesOutOfSynch = False

    def finalize(self):
        # get rid of space occupied by strings
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numProperties):
                break
            self.propertyName[i] = ''
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numProperties):
                break
            self.propertyHelp[i] = ''
        self.propertyName = [None] * 0
        self.propertyHelp = [None] * 0
        self.propertyIdxMap = [None] * 0
        self.revPropertyIdxMap = [None] * 0
        self.elementList = None
        self.elementNameList = None
        self.commandList = None
        super(DSSClassImpl, self).finalize()

    def newObject(self, ObjName):
        DSSGlobals.doErrorMsg('Reached base class of DSSClass for device \"' + ObjName + '\"', 'N/A', 'Should be overridden.', 780)
        return 0

    def setActiveElement(self, Value):
        if Value > 0 and Value <= len(self.elementList):
            self.activeElement = Value
            DSSGlobals.activeDSSObject = self.elementList.get(self.activeElement)
            if isinstance(DSSGlobals.activeDSSObject, DSSCktElement):
                DSSGlobals.activeCircuit.setActiveCktElement(DSSGlobals.activeDSSObject)

    def getActiveElement(self):
        return self.activeElement

    def edit(self):
        """Uses global parser."""
        DSSGlobals.doSimpleMsg('DSSClass.edit() called. Should be overriden.', 781)
        return 0

    def init(self, Handle):
        DSSGlobals.doSimpleMsg('DSSClass.init() called. Should be overriden.', 782)
        return 0

    def addObjectToList(self, Obj):
        """Used by newObject()."""
        # put it in this collection's element list.
        self.elementList.add(Obj)
        self.elementNameList.add(Obj.getName())
        if len(self.elementList) > 2 * self.elementNameList.getInitialAllocation():
            self.reallocateElementNameList()
        self.setActiveElement(len(self.elementList) - 1)
        return self.activeElement
        # return index of object in list

    def setActive(self, ObjName):
        result = False
        if self.elementNamesOutOfSynch:
            self.resynchElementNameList()
        idx = self.elementNameList.find(ObjName)
        if idx > 0:
            self.setActiveElement(idx)
            DSSGlobals.activeDSSObject = self.elementList.get(idx)
            result = True
        return result

    def find(self, ObjName):
        """Find an obj of this class by name."""
        result = None
        if self.elementNamesOutOfSynch:
            self.resynchElementNameList()
        idx = self.elementNameList.find(ObjName)
        if idx > 0:
            self.setActiveElement(idx)
            result = self.elementList.get(idx)
        return result

    def getActiveObj(self):
        """Get address of active obj of this class."""
        if self.activeElement > 0:
            return self.elementList.get(self.activeElement)
        else:
            return None

    def getFirstPropertyName(self):
        self.activeProperty = 0
        return self.getNextPropertyName()

    def getNextPropertyName(self):
        if self.activeProperty <= self.numProperties:
            result = self.propertyName[self.activeProperty]
        else:
            result = ''
        self.activeProperty += 1
        return result

    def propertyIndex(self, prop):
        """Find property value by string."""
        result = 0
        # default result if not found
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numProperties):
                break
            if prop.equalsIgnoreCase(self.propertyName[i]):
                result = self.propertyIdxMap[i]
                break
        return result

    def countProperties(self):
        """Add no. of intrinsic properties."""
        self.numProperties = self.numProperties + 1

    def defineProperties(self):
        """Add properties of this class to propName."""
        self.activeProperty += 1
        self.propertyName[self.activeProperty] = 'like'
        self.propertyHelp[self.activeProperty] = 'Make like another object, e.g.:' + DSSGlobals.CRLF + DSSGlobals.CRLF + 'new capacitor.C2 like=c1  ...'

    def classEdit(self, activeObj, paramPointer):
        # continue parsing with contents of parser
        if paramPointer > 0:
            # DSSObject obj = (DSSObject) ActiveObj;
            _0 = paramPointer
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    self.makeLike(Parser.getInstance().makeString())
                    # like command
                    break
                break
        return 0

    def makeLike(self, objName):
        DSSGlobals.doSimpleMsg('DSSClass.makeLike() called. Should be overriden.', 784)
        return 0

    def getFirst(self):
        result = 0
        if len(self.elementList) == 0:
            result = 0
        else:
            self.setActiveElement(1)
            DSSGlobals.activeDSSObject = self.elementList.get(0)
            if isinstance(DSSGlobals.activeDSSObject, DSSCktElement):
                DSSGlobals.activeCircuit.setActiveCktElement(DSSGlobals.activeDSSObject)
                result = self.activeElement
        return result

    def getNext(self):
        result = -1
        if self.activeElement > len(self.elementList):
            result = 0
        else:
            DSSGlobals.activeDSSObject = self.elementList.getNext()
            if isinstance(DSSGlobals.activeDSSObject, DSSCktElement):
                DSSGlobals.activeCircuit.setActiveCktElement(DSSGlobals.activeDSSObject)
                result = self.activeElement
        self.activeElement += 1
        return result

    def addProperty(self, propName, cmdMapIndex, helpString):
        """Helper routine for building property strings.

        Using the addProperty function, you can list the properties here in the order you want
        them to appear when properties are accessed sequentially without tags. Syntax:

        addProperty(<name of property>, <index in the edit case statement>, <help text>);
        """
        self.activeProperty += 1
        self.propertyName[self.activeProperty] = propName
        self.propertyHelp[self.activeProperty] = helpString
        # map to internal object property index
        self.propertyIdxMap[self.activeProperty] = cmdMapIndex
        self.revPropertyIdxMap[cmdMapIndex] = self.activeProperty

    def allocatePropertyArrays(self):
        self.propertyName = [None] * self.numProperties
        self.propertyHelp = [None] * self.numProperties
        self.propertyIdxMap = [None] * self.numProperties
        self.revPropertyIdxMap = [None] * self.numProperties
        self.activeProperty = -1
        # initialize for addProperty
        # initialize propertyIdxMap to take care of legacy items
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numProperties):
                break
            self.propertyIdxMap[i] = i
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numProperties):
                break
            self.revPropertyIdxMap[i] = i

    def reallocateElementNameList(self):
        # Reallocate the device name list to improve the performance of searches
        self.elementNameList = None
        # throw away the old one
        self.elementNameList = HashListImpl(2 * len(self.elementList))
        # make a new one
        # do this using the names of the elements rather than the old list because it might be
        # messed up if an element gets renamed
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.elementList)):
                break
            self.elementNameList.add(self.elementList.get(i).getName())

    def resynchElementNameList(self):
        self.reallocateElementNameList()
        self.elementNamesOutOfSynch = False

    def getElementCount(self):
        return len(self.elementList)

    def getNumProperties(self):
        return self.numProperties

    def setNumProperties(self, num):
        self.numProperties = num

    def getPropertyName(self):
        return self.propertyName

    def setPropertyName(self, name):
        self.propertyName = name

    def getPropertyHelp(self):
        return self.propertyHelp

    def setPropertyHelp(self, help):
        self.propertyHelp = help

    def getPropertyIdxMap(self):
        return self.propertyIdxMap

    def setPropertyIdxMap(self, map):
        self.propertyIdxMap = map

    def getRevPropertyIdxMap(self):
        return self.revPropertyIdxMap

    def setRevPropertyIdxMap(self, map):
        self.revPropertyIdxMap = map

    def getDSSClassType(self):
        return self.classType

    def setDSSClassType(self, type):
        self.classType = type

    def getElementList(self):
        return self.elementList

    def setElementList(self, list):
        self.elementList = list

    def isElementNamesOutOfSynch(self):
        return self.elementNamesOutOfSynch

    def setElementNamesOutOfSynch(self, value):
        self.elementNamesOutOfSynch = value

    def isSaved(self):
        return self.saved

    def setSaved(self, value):
        self.saved = value

    def getName(self):
        return self.className

    @classmethod
    def setDSSClasses(cls, classes):
        cls.DSSClasses = classes

    @classmethod
    def getDSSClasses(cls):
        return cls.DSSClasses
