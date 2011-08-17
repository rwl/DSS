from dss.common.impl.Utilities import (Utilities,)
from dss.general.impl.NamedObjectImpl import (NamedObjectImpl,)
from dss.general.DSSObject import (DSSObject,)
# from java.io.PrintWriter import (PrintWriter,)


class DSSObjectImpl(NamedObjectImpl, DSSObject):
    propSeqCount = None
    propertyValue = None
    prpSequence = None
    objType = None
    parentClass = None
    classIndex = None
    hasBeenSaved = None
    flag = None

    def clearPropSeqArray(self):
        self.propSeqCount = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            self.prpSequence[i] = 0

    def __init__(self, parClass):
        super(DSSObjectImpl, self)(parClass.getName())
        self.objType = 0
        self.propSeqCount = 0
        self.parentClass = parClass
        self.propertyValue = [None] * self.parentClass.getNumProperties()
        # init'd to zero when allocated
        self.prpSequence = [None] * self.parentClass.getNumProperties()
        self.hasBeenSaved = False

    def finalize(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            self.propertyValue[i] = ''
        self.propertyValue = [None] * 0
        self.prpSequence = [None] * 0
        super(DSSObjectImpl, self).finalize()

    def dumpProperties(self, f, complete):
        print 
        print 'new ' + self.getDSSClassName() + '.' + self.getName()

    def edit(self):
        """Allow calls to edit from object itself."""
        self.parentClass.setActiveElement(self.classIndex)
        return self.parentClass.edit()

    def getPropertyValue(self, index):
        """Use DSSClass.propertyIndex() to get index by name."""
        # default behavior for all DSS objects
        return self.propertyValue[index]

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[arrayOffset] = ''
        # clear propertySequence array after initialization
        self.clearPropSeqArray()

    def saveWrite(self, f):
        # Write only properties that were explicitly set in the
        # final order they were actually set.

        iProp = self.getNextPropertySet(0)
        # works on activeDSSObject
        while iProp > 0:
            pc = self.parentClass
            f.print_(' ' + pc.getPropertyName()[pc.getRevPropertyIdxMap()[iProp]])
            f.print_('=' + Utilities.checkForBlanks(self.propertyValue[iProp]))
            iProp = self.getNextPropertySet(iProp)

    def getNextPropertySet(self, idx):
        """Find next larger property sequence number
        return 0 if none found
        """
        smallest = 9999999
        # some big number
        result = 0
        if idx > 0:
            idx = self.prpSequence[idx]
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            if self.prpSequence[i] > idx:
                if self.prpSequence[i] < smallest:
                    smallest = self.prpSequence[i]
                    result = i
        return result

    def setName(self, value):
        # if renamed, then let someone know so hash list can be updated
        if len(self.getLocalName()) > 0:
            self.parentClass.setElementNamesOutOfSynch(True)
        self.setLocalName(value)

    def setPropertyValue(self, index, value):
        self.propertyValue[index] = value
        # keep track of the order in which this property was
        # accessed for save command
        self.propSeqCount += 1
        self.prpSequence[index] = self.propSeqCount

    def getName(self):
        return self.getLocalName()

    def getDSSObjType(self):
        return self.objType

    def setDSSObjType(self, type):
        self.objType = type

    def getParentClass(self):
        return self.parentClass

    def setParentClass(self, parent):
        self.parentClass = parent

    def getClassIndex(self):
        return self.classIndex

    def setClassIndex(self, index):
        self.classIndex = index

    def isHasBeenSaved(self):
        return self.hasBeenSaved

    def setHasBeenSaved(self, saved):
        self.hasBeenSaved = saved

    def isFlag(self):
        return self.flag

    def setFlag(self, value):
        # FIXME Protected member in OpenDSS
        self.flag = value

    def getPrpSequence(self):
        return self.prpSequence
