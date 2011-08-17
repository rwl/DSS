from dss.shared.impl.PointerListImpl import (PointerListImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.CktTreeNode import (CktTreeNode,)


class CktTreeNodeImpl(object, CktTreeNode):
    childBranches = None
    # TODO Replace with List and Iterator
    numToBuses = None
    toBusPtr = None
    toBusList = None
    childAdded = None
    lexicalLevel = None
    parentBranch = None
    shuntObjects = None
    # generic objects attached to the tree at this node
    cktObject = None
    # pointer to the circuit object referenced
    fromBusReference = None
    voltBaseIndex = None
    fromTerminal = None
    isLoopedHere = None
    isParallel = None
    isDangling = None
    loopLineObj = None

    def __init__(self, parent, selfObj):
        super(CktTreeNodeImpl, self)()
        self.cktObject = selfObj
        self.parentBranch = parent
        if self.parentBranch is not None:
            self.lexicalLevel = self.parentBranch.getLexicalLevel() + 1
        else:
            self.lexicalLevel = 0
        self.childBranches = PointerListImpl(2)
        self.shuntObjects = PointerListImpl(1)
        self.fromBusReference = 0
        self.voltBaseIndex = 0
        # index to voltage base list used by EnergyMeter and maybe others
        self.numToBuses = 0
        self.toBusList = None
        self.toBusPtr = 0
        self.childAdded = False
        # TEMc - initialize some topology variables, 10/2009
        self.isDangling = True
        self.isLoopedHere = False
        self.isParallel = False
        self.loopLineObj = None

    def addChild(self, value):
        self.childBranches.add(value)
        self.childAdded = True

    def addObject(self, value):
        self.shuntObjects.add(value)

    def getFirstChild(self):
        return self.childBranches.getFirst()

    def getNextChild(self):
        return self.childBranches.getNext()

    def getParent(self):
        return self.parentBranch

    def getNumChildren(self):
        """Number of children at present node."""
        return len(self.childBranches)

    def getNumObjects(self):
        """Number of objects at present node."""
        return len(self.shuntObjects)

    def getToBusReference(self):
        """Sequentially access the toBus list if more than one with each invocation of the property."""
        if self.numToBuses == 1:
            return self.toBusList[0]
            # always return the first
        else:
            self.toBusPtr += 1
            if self.toBusPtr >= self.numToBuses:
                # TODO Check zero based indexing
                self.toBusPtr = 0
                # ready for next sequence of access
                return -1
            else:
                return self.toBusList[self.toBusPtr]

    def setToBusReference(self, value):
        self.numToBuses += 1
        self.toBusList = Utilities.resizeArray(self.toBusList, self.numToBuses)
        self.toBusList[self.numToBuses] = value

    def resetToBusList(self):
        self.toBusPtr = 0
        # TODO Check zero based indexing

    def getFirstObject(self):
        return self.shuntObjects.getFirst()
        # TODO Make generic

    def getNextObject(self):
        return self.shuntObjects.getNext()

    def getCktObject(self):
        return self.cktObject

    def setCktObject(self, ckt):
        self.cktObject = ckt

    def getFromBusReference(self):
        return self.fromBusReference

    def setFromBusReference(self, reference):
        self.fromBusReference = reference

    def getVoltBaseIndex(self):
        return self.voltBaseIndex

    def setVoltBaseIndex(self, index):
        self.voltBaseIndex = index

    def getFromTerminal(self):
        return self.fromTerminal

    def setFromTerminal(self, terminal):
        self.fromTerminal = terminal

    def isLoopedHere(self):
        return self.isLoopedHere

    def setLoopedHere(self, value):
        self.isLoopedHere = value

    def isParallel(self):
        return self.isParallel

    def setParallel(self, value):
        self.isParallel = value

    def isDangling(self):
        return self.isDangling

    def setDangling(self, value):
        self.isDangling = value

    def getLoopLineObj(self):
        return self.loopLineObj

    def setLoopLineObj(self, lineObj):
        # FIXME Protected members in OpenDSS
        self.loopLineObj = lineObj

    def isChildAdded(self):
        return self.childAdded

    def setChildAdded(self, added):
        self.childAdded = added

    def getLexicalLevel(self):
        return self.lexicalLevel

    def setLexicalLevel(self, level):
        self.lexicalLevel = level
