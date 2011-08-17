from __pyjamas__ import (ARGERROR,)
from dss.shared.impl.PointerListImpl import (PointerListImpl,)
from dss.shared.impl.PStackImpl import (PStackImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.shared.impl.CktTreeNodeImpl import (CktTreeNodeImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.CktTree import (CktTree,)
# from java.util.ArrayList import (ArrayList,)
# from java.util.List import (List,)


class CktTreeImpl(object, CktTree):

    class ZoneEndsList(object):
        endNodeList = None
        endBuses = None
        numEnds = None

        def __init__(self):
            self.endNodeList = PointerListImpl(10)
            self.numEnds = 0
            self.endBuses = None

        def add(self, node, endBusRef):
            self.numEnds += 1
            self.endNodeList.add(node)
            self.endBuses = Utilities.resizeArray(self.endBuses, self.numEnds)
            self.endBuses[self.numEnds - 1] = endBusRef
            # TODO Check zero based indexing

        def get(self, i, node):
            node = self.endNodeList.get(i)
            # FIXME Make generic
            return self.endBuses[i]

        def getNumEnds(self):
            return self.numEnds

        def setNumEnds(self, num):
            self.numEnds = num

    firstNode = None
    forwardStack = None
    presentBranch = None
    zoneEndsList = None

    def __init__(self):
        super(CktTreeImpl, self)()
        self.firstNode = None
        self.presentBranch = None
        self.zoneEndsList = self.ZoneEndsList()
        self.forwardStack = PStackImpl(20)

    def setNew(self, value):
        """Adds child and makes it present."""
        self.presentBranch = CktTreeNodeImpl(self.presentBranch, value)
        if self.firstNode is None:
            self.firstNode = self.presentBranch

    def addNewChild(self, value, busRef, terminalNo):
        if self.presentBranch is None:
            self.setNew(value)
        else:
            TempNode = CktTreeNodeImpl(self.presentBranch, value)
            TempNode.setFromBusReference(busRef)
            TempNode.setFromTerminal(terminalNo)
            self.presentBranch.addChild(TempNode)

    def setNewObject(self, value):
        """Adds a pointer to an object to be associated with the current node."""
        if self.presentBranch is not None:
            self.presentBranch.addObject(value)

    def pushAllChildren(self):
        if self.presentBranch is not None:
            # push all children of present node onto stack
            pChild = self.presentBranch.getFirstChild()
            while pChild is not None:
                self.forwardStack.push(pChild)
                # FIXME Implement generics
                pChild = self.presentBranch.getNextChild()
            self.presentBranch.setChildAdded(False)

    def goForward(self):
        """Move forward from present node."""
        # if we have added children to the present node since we opened it push them on
        if self.presentBranch is not None:
            if self.presentBranch.isChildAdded():
                self.pushAllChildren()
                # if the forward stack is empty push stuff on it to get started
        if len(self.forwardStack) == 0:
            self.pushAllChildren()
        self.presentBranch = self.forwardStack.pop()
        # FIXME Implement generics
        self.pushAllChildren()
        # push all children of latest
        if self.presentBranch is not None:
            return self.presentBranch.getCktObject()
        else:
            return None

    def goBackward(self):
        """Move backward from present node and reset forward stack."""
        self.presentBranch = self.presentBranch.getParent()
        self.forwardStack.clear()
        if self.presentBranch is not None:
            return self.presentBranch.getCktObject()
        else:
            return None

    def getParent(self):
        if self.presentBranch.getParent() is not None:
            return self.presentBranch.getParent().getCktObject()
        else:
            return None

    def getFirst(self):
        """Returns pointer to first cktobject.

        Go to beginning and reset forward stack.
        """
        self.presentBranch = self.firstNode
        self.forwardStack.clear()
        self.pushAllChildren()
        if self.presentBranch is not None:
            return self.presentBranch.getCktObject()
        else:
            return None

    def getFirstObject(self):
        if self.presentBranch is not None:
            return self.presentBranch.getFirstObject()
        else:
            return None

    def getNextObject(self):
        if self.presentBranch is not None:
            return self.presentBranch.getNextObject()
        else:
            return None

    def getActive(self):
        if self.presentBranch is not None:
            return self.presentBranch.getCktObject()
        else:
            return None

    def setActive(self, p):
        temp = self.getFirst()
        while temp is not None:
            if self.presentBranch.getCktObject() == p:
                break
            temp = self.goForward()
        self.forwardStack.clear()

    def startHere(self):
        """Start forward search at the present location (can also use active)."""
        self.forwardStack.clear()
        if self.presentBranch is not None:
            self.forwardStack.push(self.presentBranch)
            # FIXME Implement generics

    def getLevel(self):
        """Get lexical level of present node."""
        # Utility code for building a connected tree starting from a circuit element.
        if self.presentBranch is not None:
            return self.presentBranch.getLexicalLevel()
        else:
            return 0

    @classmethod
    def getSourcesConnectedToBus(cls, busNum, branchList, analyze):
        """Sources are excluded from the PC element list, so this is a brute-force search."""
        ckt = DSSGlobals.activeCircuit
        for psrc in ckt.getSources():
            # Sources are special PC elements
            if psrc.isEnabled():
                if analyze or (not psrc.isChecked()):
                    if psrc.getTerminals()[0].getBusRef() == busNum:
                        # ? Connected to this bus ?
                        if analyze:
                            psrc.setIsolated(False)
                            branchList.getPresentBranch().setDangling(False)
                        if not psrc.isChecked():
                            branchList.setNewObject(psrc)
                            psrc.setChecked(True)

    @classmethod
    def getPCElementsConnectedToBus(cls, adjLst, branchList, analyze):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(adjLst) - 1):
                break
            p = adjLst[i]
            if p.isEnabled():
                if analyze:
                    p.setIsolated(False)
                    branchList.getPresentBranch().setDangling(False)
                if not p.isChecked():
                    branchList.setNewObject(p)
                    p.setChecked(True)

    @classmethod
    def findAllChildBranches(cls, adjLst, busNum, branchList, analyze, activeBranch):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(adjLst) - 1):
                break
            p = adjLst[i]
            if p.isEnabled() and p != activeBranch:
                if analyze or (not p.isChecked()):
                    if not Utilities.isShuntElement(p) and Utilities.allTerminalsClosed(p):
                        _1 = True
                        j = 0
                        while True:
                            if _1 is True:
                                _1 = False
                            else:
                                j += 1
                            if not (j < p.getNTerms()):
                                break
                            if busNum == p.getTerminals()[j].getBusRef():
                                if analyze:
                                    p.setIsolated(False)
                                    branchList.getPresentBranch().setDangling(False)
                                    if p.isChecked() and branchList.getLevel() > 0:
                                        branchList.getPresentBranch().setLoopedHere(True)
                                        branchList.getPresentBranch().setLoopLineObj(p)
                                        if Utilities.isLineElement(p) and Utilities.isLineElement(activeBranch):
                                            if Utilities.checkParallel(activeBranch, p):
                                                branchList.getPresentBranch().setParallel(True)
                                if not p.isChecked():
                                    branchList.addNewChild(p, busNum, j)
                                    p.getTerminals()[j].setChecked(True)
                                    p.setChecked(True)
                                    break
                                    # for

    @classmethod
    def getShuntPDElementsConnectedToBus(cls, adjLst, branchList, analyze):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(adjLst) - 1):
                break
            p = adjLst[i]
            if p.isEnabled() and Utilities.isShuntElement(p):
                if analyze:
                    p.setIsolated(False)
                    branchList.getPresentBranch().setDangling(False)
                if not p.isChecked():
                    branchList.setNewObject(p)
                    p.setChecked(True)

    @classmethod
    def getIsolatedSubArea(cls, *args):
        """None
        ---
        Build a tree of connected elements beginning at StartElement.
        Analyze = true; will check for loops, isolated components, and parallel lines (takes longer)
        """
        _0 = args
        _1 = len(args)
        if _1 == 1:
            startElement, = _0
            return cls.getIsolatedSubArea(startElement, False)
        elif _1 == 2:
            startElement, analyze = _0
            pass # astStmt: [Stmt([]), 335]
            pass # astStmt: [Stmt([]), 336]
            pass # astStmt: [Stmt([]), 337]
            pass # astStmt: [Stmt([]), 338]
            pass # astStmt: [Stmt([]), 339]
            ckt = DSSGlobals.activeCircuit
            lstPD = ckt.getBusAdjacentPDLists()
            lstPC = ckt.getBusAdjacentPCLists()
            branchList = CktTreeImpl()
            testElement = startElement
            branchList.setNew(testElement)
            if analyze:
                testElement.setIsolated(False)
            testElement.setLastTerminalChecked(0)
            # we'll check things connected to both sides
            # check off this element so we don't use it again
            testElement.setChecked(True)
            # now start looking for other branches
            # finds any branch connected to the TestBranch and adds it to the list
            # goes until end of circuit, another energy meter, an open terminal, or disabled device
            testBranch = testElement
            while testBranch is not None:
                _0 = True
                iTerm = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        iTerm += 1
                    if not (iTerm < testBranch.getNTerms()):
                        break
                    if not testBranch.getTerminals()[iTerm].isChecked():
                        # now find all PC elements connected to the bus on this end of branch
                        # attach them as generic objects to cktTree node.
                        testBusNum = testBranch.getTerminals()[iTerm].getBusRef()
                        branchList.getPresentBranch().setToBusReference(testBusNum)
                        # add this as a "to" bus reference
                        if testBusNum > 0:
                            ckt.getBuses()[testBusNum].setBusChecked(True)
                            cls.getSourcesConnectedToBus(testBusNum, branchList, analyze)
                            cls.getPCElementsConnectedToBus(lstPC[testBusNum], branchList, analyze)
                            cls.getShuntPDElementsConnectedToBus(lstPD[testBusNum], branchList, analyze)
                            cls.findAllChildBranches(lstPD[testBusNum], testBusNum, branchList, analyze, testBranch)
                testBranch = branchList.goForward()
            return branchList
        else:
            raise ARGERROR(1, 2)

    @classmethod
    def buildActiveBusAdjacencyLists(cls, lstPD, lstPC):
        # CktElement pCktElement;
        ckt = DSSGlobals.activeCircuit
        nBus = ckt.getNumBuses()
        # Circuit.buses is effectively 1-based; bus 0 is ground   TODO Check zero based indexing
        lstPD = [None] * (nBus + 1)
        lstPC = [None] * (nBus + 1)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < nBus):
                break
            lstPD[i] = list()
            # default capacity should be enough
            lstPC[i] = list()
        for pCktElement in ckt.getPCElements():
            if pCktElement.isEnabled():
                i = pCktElement.getTerminals()[0].getBusRef()
                lstPC[i].add(pCktElement)
        for pCktElement in ckt.getPDElements():
            # Put only eligible PD elements in the list
            if pCktElement.isEnabled():
                if Utilities.isShuntElement(pCktElement):
                    i = pCktElement.getTerminals()[0].getBusRef()
                    lstPC[i].add(pCktElement)
                elif Utilities.allTerminalsClosed(pCktElement):
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < pCktElement.getNTerms()):
                            break
                        i = pCktElement.getTerminals()[j].getBusRef()
                        lstPD[i].add(pCktElement)

    @classmethod
    def freeAndNilBusAdjacencyLists(cls, lstPD, lstPC):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(lstPD)):
                break
            lstPD[i] = None
            lstPC[i] = None
        lstPD = None
        lstPC = None

    def getPresentBranch(self):
        return self.presentBranch

    def setPresentBranch(self, branch):
        self.presentBranch = branch

    def getZoneEndsList(self):
        return self.zoneEndsList

    def setZoneEndsList(self, list):
        self.zoneEndsList = list
