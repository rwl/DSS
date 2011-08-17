from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.parser.impl.Parser import (Parser,)


class ReduceAlgs(object):
    """Reduction algorithms

    Primarily called from EnergyMeter.
    """

    def __init__(self):
        pass

    @classmethod
    def doMergeParallelLines(cls, branchList):
        """Merge all lines in this zone that are marked in parallel."""
        if branchList is not None:
            branchList.getFirst()
            lineElement = branchList.goForward()
            # always keep the first element
            while lineElement is not None:
                if branchList.getPresentBranch().isParallel():
                    # There will always be two lines in parallel. The first operation will disable the second
                    if lineElement.isEnabled():
                        lineElement.mergeWith(branchList.getPresentBranch().getLoopLineObj(), False)
                        # guaranteed to be a line
                lineElement = branchList.goForward()

    @classmethod
    def doBreakLoops(cls, branchList):
        if branchList is not None:
            branchList.getFirst()
            lineElement = branchList.goForward()
            # always keep the first element
            while lineElement is not None:
                if branchList.getPresentBranch().isLoopedHere():
                    # There will always be two lines in the loop. The first operation will disable the second
                    if lineElement.isEnabled():
                        branchList.getPresentBranch().getLoopLineObj().setEnabled(False)
                        # disable the other
                lineElement = branchList.goForward()

    @classmethod
    def doReduceTapEnds(cls, branchList):
        pass

    @classmethod
    def doReduceDangling(cls, branchList):
        if branchList is not None:
            # Let's throw away all dangling end branches
            branchList.getFirst()
            lineElem1 = branchList.goForward()
            # always keep the first element
            while lineElem1 is not None:
                if Utilities.isLineElement(lineElem1):
                    pb = branchList.getPresentBranch()
                    # If it is at the end of a section and has no load,cap, reactor,
                    # or coordinate, just throw it away.

                    if pb.isDangling():
                        toBusRef = pb.getToBusReference()
                        # only access this property once
                        if toBusRef > 0:
                            bus = DSSGlobals.activeCircuit.getBuses()[toBusRef]
                            if not bus.isKeep():
                                lineElem1.setEnabled(False)
                lineElem1 = branchList.goForward()

    @classmethod
    def doReduceStubs(cls, branchList):
        """Eliminate short stubs and merge with lines on either side."""
        ckt = DSSGlobals.activeCircuit
        if branchList is not None:
            # eliminate really short lines
            # First, flag all elements that need to be merged
            lineElement1 = branchList.getFirst()
            lineElement1 = branchList.goForward()
            # always keep the first element
            while lineElement1 is not None:
                if Utilities.isLineElement(lineElement1):
                    if Utilities.isStubLine(lineElement1):
                        lineElement1.setFlag(True)
                        # Too small: Mark for merge with something
                    else:
                        lineElement1.setFlag(False)
                lineElement1 = branchList.goForward()
            lineElement1 = branchList.getFirst()
            lineElement1 = branchList.goForward()
            # always keep the first element
            while lineElement1 is not None:
                if lineElement1.isFlag():
                    # merge this element out
                    pb = branchList.getPresentBranch()
                    if pb.getNumChildren() == 0 and pb.getNumObjects() == 0:
                        lineElement1.setEnabled(False)
                        # just discard it
                    elif (pb.getNumChildren() == 0) or (pb.getNumChildren() > 1):
                        # Merge with parent and move loads on parent to to node
                        parentNode = pb.getParent()
                        if parentNode is not None:
                            if parentNode.getNumChildren() == 1:
                                # only works for in-line
                                if not ckt.getBuses()[parentNode.getToBusReference()].isKeep():
                                    # Let's consider merging
                                    lineElement2 = parentNode.getCktObject()
                                    if lineElement2.isEnabled():
                                        # check to make sure it hasn't been merged out
                                        if Utilities.isLineElement(lineElement2):
                                            if lineElement2.mergeWith(lineElement1, True):
                                                # Move any loads to toBus Reference of downline branch
                                                if parentNode.getNumObjects() > 0:
                                                    # Redefine bus connection for PC elements hanging on the bus that is eliminated
                                                    loadElement = parentNode.getFirstObject()
                                                    while loadElement is not None:
                                                        Parser.getInstance().setCmdString('bus1=\"' + ckt.getBusList().get(pb.getToBusReference()) + '\"')
                                                        loadElement.edit()
                                                        loadElement = parentNode.getNextObject()
                        # if parentNode
                    elif not ckt.getBuses()[pb.getToBusReference()].isKeep():
                        # Let's consider merging
                        lineElement2 = pb.getFirstChild().getCktObject()
                        if Utilities.isLineElement(lineElement2):
                            if lineElement2.mergeWith(lineElement1, True):
                                if pb.getFirstChild().getNumObjects() > 0:
                                    # Redefine bus connection to upline bus
                                    loadElement = pb.getFirstChild().getFirstObject()
                                    while loadElement is not None:
                                        Parser.getInstance().setCmdString('bus1=\"' + ckt.getBusList().get(pb.getFromBusReference()) + '\"')
                                        loadElement.edit()
                                        loadElement = pb.getFirstChild().getNextObject()
                    # Merge with child
                lineElement1 = branchList.goForward()

    @classmethod
    def doReduceSwitches(cls, branchList):
        """Merge switches in with lines or delete if dangling."""
        if branchList is not None:
            lineElement1 = branchList.getFirst()
            lineElement1 = branchList.goForward()
            # always keep the first element
            while lineElement1 is not None:
                if lineElement1.isEnabled():
                    # maybe we threw it away already
                    if Utilities.isLineElement(lineElement1):
                        if lineElement1.isSwitch():
                            pb = branchList.getPresentBranch()
                            # See if eligble for merging
                            _0 = pb.getNumChildren()
                            _1 = False
                            while True:
                                if _0 == 0:
                                    _1 = True
                                    if pb.getNumObjects() == 0:
                                        lineElement1.setEnabled(False)
                                    break
                                if (_1 is True) or (_0 == 1):
                                    _1 = True
                                    if pb.getNumObjects() == 0:
                                        if not DSSGlobals.activeCircuit.getBuses()[pb.getToBusReference()].isKeep():
                                            # Let's consider merging
                                            lineElement2 = pb.getFirstChild().getCktObject()
                                            if Utilities.isLineElement(lineElement2):
                                                if not lineElement2.isSwitch():
                                                    lineElement2.mergeWith(lineElement1, True)
                                                    # Series Merge
                                    break
                                break
                lineElement1 = branchList.goForward()

    @classmethod
    def doReduceDefault(cls, branchList):
        if branchList is not None:
            # Now merge remaining lines
            lineElement1 = branchList.getFirst()
            lineElement1 = branchList.goForward()
            # always keep the first element
            while lineElement1 is not None:
                if Utilities.isLineElement(lineElement1):
                    if not lineElement1.isSwitch():
                        if lineElement1.isEnabled():
                            # maybe we threw it away already
                            pb = branchList.getPresentBranch()
                            # see if eligble for merging
                            if pb.getNumChildren() == 1:
                                if pb.getNumObjects() == 0:
                                    if not DSSGlobals.activeCircuit.getBuses()[pb.getToBusReference()].isKeep():
                                        # Let's consider merging
                                        lineElement2 = pb.getFirstChild().getCktObject()
                                        if Utilities.isLineElement(lineElement2):
                                            if not lineElement2.isSwitch():
                                                lineElement2.mergeWith(lineElement1, True)
                                                # Series merge
                lineElement1 = branchList.goForward()
