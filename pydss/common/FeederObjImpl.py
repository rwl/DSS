from dss.common.impl.FeederImpl import (FeederImpl,)
from dss.common.FeederObj import (FeederObj,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.conversion.impl.PCElementImpl import (PCElementImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from java.io.PrintStream import (PrintStream,)
# from java.util.ArrayList import (ArrayList,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class FeederObjImpl(PCElementImpl, FeederObj):
    sequenceList = None
    shuntList = None
    rootElement = None
    fromTerminalOffset = None
    isSynched = None

    def __init__(self, ParClass, MeterName):
        super(FeederObjImpl, self)(ParClass)
        self.setName(MeterName.toLowerCase())
        self.objType = ParClass.getDSSClassType()
        # this will be a current source (PCElement)
        self.sequenceList = list(50)
        self.shuntList = list(50)
        self.isSynched = False
        # bus names and nPhases, etc are set up from EnergyMeter
        self.recalcElementData()
        self.initPropertyValues(0)

    def initializeFeeder(self, BranchList):
        self.sequenceList.clear()
        # get rid of any previous definitions
        self.shuntList.clear()
        self.isSynched = False
        # set up feeder terminals and busRef to match the from node of the first branch
        if BranchList is not None:
            self.rootElement = BranchList.getFirst()
            self.setNPhases(self.rootElement.getNPhases())
            # take care of allocating terminal stuff
            self.setNConds(self.rootElement.getNConds())
            self.setNTerms(1)
            self.YOrder = self.nTerms * self.nConds
            self.terminals[0].setBusRef(BranchList.getPresentBranch().getFromBusReference())
            # TODO Check zero based indexing
            self.setBus(0, self.rootElement.getBus(BranchList.getPresentBranch().getFromTerminal()))
            # set bus name same as first element
            self.fromTerminalOffset = (BranchList.getPresentBranch().getFromTerminal() - 1) * self.nConds
            self.setNodeRef(0, self.rootElement.getNodeRef()[1 + self.fromTerminalOffset])
            # TODO Check zero based indexing
            # build the sequence list and shunt list
            pElement = self.rootElement
            while pElement is not None:
                self.sequenceList.add(pElement)
                # mark all the to buses for this branch as radial buses
                BranchList.getPresentBranch().resetToBusList()
                # reset pointer to first to bus
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < pElement.getNTerms() - 1):
                        break
                    bref = BranchList.getPresentBranch().getToBusReference()
                    # each call pops off a new one
                    if bref > 0:
                        DSSGlobals.activeCircuit.getBuses()[bref].setRadialBus(True)
                pShunt = BranchList.getPresentBranch().getFirstObject()
                while pShunt is not None:
                    self.shuntList.add(pShunt)
                    pShunt = BranchList.getPresentBranch().getNextObject()
                pElement = BranchList.goForward()
            self.isSynched = True
            self.setCktElementFeederFlags(True)

    def recalcElementData(self):
        pass

    def calcYPrim(self):
        # for now, YPrim is null
        # build only YPrim_Series
        if self.isYprimInvalid():
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            if self.YPrim is not None:
                self.YPrim = None
            self.YPrim = CMatrixImpl(self.YOrder)
        else:
            self.YPrimSeries.clear()
            self.YPrim.clear()
        # Yprim = 0 for ideal current source; just leave it zeroed
        # Now account for open conductors
        # For any conductor that is open, zero out row and column
        super(FeederObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def getCurrents(self, curr):
        """Total currents into a feeder which are equal to the currents into the first element.
        Return the currents in the from terminal of the first element in the sequence list.
        """
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            curr[i] = Complex.ZERO
            # no contribution if not radial solution

    def getInjCurrents(self, curr):
        """Fill Up an array of injection currents.

        Only thing this is used for is for getCurrents(). Ignore for Feeder.
        """
        pass

    def dumpProperties(self, f, complete):
        super(FeederObjImpl, self).dumpProperties(f, complete)
        # Do not dump any properties for a Feeder unless debug.
        if complete:
            # Dump sequence lists, etc here...
            print 
            print 

    def initPropertyValues(self, arrayOffset):
        super(FeederObjImpl, self).initPropertyValues(FeederImpl.NumPropsThisClass)

    def makePosSequence(self):
        """Make a positive sequence model."""
        # do nothing
        pass

    def setCktElementFeederFlags(self, value):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < len(self.shuntList)):
                break
            self.shuntList[i].setPartofFeeder(value)
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < len(self.sequenceList)):
                break
            self.sequenceList[i].setPartofFeeder(value)

    def isSynched(self):
        return self.isSynched

    def setSynched(self, synched):
        self.isSynched = synched
