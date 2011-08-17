from __pyjamas__ import (ARGERROR,)
from dss.common.CktElement import (CktElement,)
from dss.general.impl.DSSObjectImpl import (DSSObjectImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.common.impl.PowerTerminal import (PowerTerminal,)
# from java.io.PrintStream import (PrintStream,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class DSSCktElement(DSSObjectImpl, CktElement):
    busNames = None
    enabled = None
    enabledProperty = None
    activeTerminalIdx = None
    YPrimInvalid = None
    handle = None
    nTerms = None
    # No. conductors per terminal
    nConds = None
    nPhases = None
    complexBuffer = None
    ITerminalSolutionCount = None
    busIndex = None
    YPrimSeries = None
    YPrimShunt = None
    YPrim = None
    # order will be nTerms * nCond
    # Frequency at which YPrim has been computed
    YPrimFreq = None
    # Total nodeRef array for element
    nodeRef = None
    YOrder = None
    # Flag used in tree searches
    lastTerminalChecked = None
    checked = None
    hasEnergyMeter = None
    hasSensorObj = None
    isIsolated = None
    hasControl = None
    isPartOfFeeder = None
    controlElement = None
    ITerminal = None
    VTerminal = None
    baseFrequency = None
    terminals = None
    activeTerminal = None

    def __init__(self, parClass):
        super(DSSCktElement, self)(parClass)
        self.nodeRef = None
        self.YPrimSeries = None
        self.YPrimShunt = None
        self.YPrim = None
        self.terminals = None
        self.busNames = None
        self.VTerminal = None
        self.ITerminal = None
        # present value of terminal current
        self.complexBuffer = None
        self.handle = -1
        self.busIndex = 0
        self.nTerms = 0
        self.nConds = 0
        self.nPhases = 0
        self.objType = 0
        self.YOrder = 0
        self.YPrimInvalid = True
        self.enabled = True
        self.hasEnergyMeter = False
        self.hasSensorObj = False
        self.isPartOfFeeder = False
        self.isIsolated = False
        self.controlElement = None
        # init to no control on this element
        self.hasControl = False
        self.activeTerminalIdx = 1
        self.lastTerminalChecked = 0
        # Indicates which solution ITemp is computed for
        self.ITerminalSolutionCount = -1
        self.baseFrequency = DSSGlobals.activeCircuit.getFundamental()

    def setYPrimInvalid(self, value):
        self.YPrimInvalid = value
        if value:
            # if this device is in the circuit, then we have to rebuild Y on a change in Yprim
            if self.enabled:
                DSSGlobals.activeCircuit.getSolution().setSystemYChanged(True)

    def isYprimInvalid(self):
        return self.YPrimInvalid

    def setActiveTerminalIdx(self, value):
        if value > 0 and value <= self.nTerms:
            self.activeTerminalIdx = value
            self.activeTerminal = self.terminals[value]

    def getActiveTerminalIdx(self):
        return self.activeTerminalIdx

    def setHandle(self, value):
        self.handle = value

    def getConductorClosed(self, index):
        """Returns the state of selected conductor.
        If index=-1 return true if all phases closed, else false.
        """
        if index == 0:
            result = True
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                if not self.terminals[self.activeTerminalIdx].getConductors()[i].isClosed():
                    result = False
                    break
        elif index > 0 and index <= self.nConds:
            result = self.terminals[self.activeTerminalIdx].getConductors()[index].isClosed()
        else:
            result = False
        return result

    def setConductorClosed(self, index, value):
        if index == 0:
            # do all conductors
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                self.terminals[self.activeTerminalIdx].getConductors()[i].setClosed(value)
            DSSGlobals.activeCircuit.getSolution().setSystemYChanged(True)
            # force Y matrix rebuild
            self.YPrimInvalid = True
        elif index > 0 and index <= self.nConds:
            self.terminals[self.activeTerminalIdx].getConductors()[index].setClosed(value)
            DSSGlobals.activeCircuit.getSolution().setSystemYChanged(True)
            self.YPrimInvalid = True

    def setNConds(self, value):
        # check for an almost certain programming error
        if value <= 0:
            DSSGlobals.doSimpleMsg(String.format.format('Invalid number of terminals (%d) for \"%s.%s\"', value, self.parentClass.getName(), self.getName()), 749)
            return
        if value != self.nConds:
            DSSGlobals.activeCircuit.setBusNameRedefined(True)
        self.nConds = value
        self.setNTerms(self.nTerms)
        # realloc terminals; need more efficient way to do this

    def getNConds(self):
        return self.nConds

    def setNPhases(self, value):
        if value > 0:
            self.nPhases = value

    def getNPhases(self):
        return self.nPhases

    def setNTerms(self, value):
        # check for an almost certain programming error
        if value <= 0:
            DSSGlobals.doSimpleMsg(String.format.format('Invalid number of terminals (%d) for \"%s.%s\"', value, self.parentClass.getName(), self.getName()), 749)
            return
        # if value is same as present value, no reallocation necessary;
        # if either nTerms or nConds has changed then reallocate
        if (value != self.nTerms) or (value * self.nConds != self.YOrder):
            # Sanity Check
            if self.nConds > 101:
                DSSGlobals.doSimpleMsg(String.format.format('Warning: Number of conductors is very large (%d) for circuit element: \"%s.%s.' + 'Possible error in specifying the number of phases for element.', self.nConds, self.parentClass.getName(), self.getName()), 750)
            # ReAllocate bus names
            # because they are strings, we have to do it differently
            if value < self.nTerms:
                self.busNames = Utilities.resizeArray(self.busNames, value)
                # keeps old values; truncates storage
            elif self.busNames is None:
                # first allocation
                self.busNames = [None] * value
                # fill with zeros or strings will crash
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < value):
                        break
                    self.busNames[i] = self.getName() + '_' + String.valueOf.valueOf(i)
                    # make up a bus name to stick in
                    # this is so devices like transformers which may be defined on multiple commands
                    # will have something in the busNames array
            else:
                newBusNames = [None] * value
                # make some new space
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.nTerms):
                        break
                    newBusNames[i] = self.busNames[i]
                    # copy old into new
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.nTerms):
                        break
                    self.busNames[i] = ''
                    # decrement usage counts by setting to empty string
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < self.nTerms + 1):
                        break
                    newBusNames[i] = self.getName() + '_' + String.valueOf.valueOf(i)
                    # make up a bus name to stick in
                self.busNames = newBusNames
            # Reallocate terminals if nConds or nTerms changed
            if self.terminals is not None:
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.nTerms):
                        break
                    self.terminals[i] = None
                    # clean up old storage
            self.terminals = Utilities.resizeArray(self.terminals, value)
            self.nTerms = value
            # set new number of terminals
            self.YOrder = self.nTerms * self.nConds
            self.VTerminal = Utilities.resizeArray(self.VTerminal, self.YOrder)
            self.ITerminal = Utilities.resizeArray(self.ITerminal, self.YOrder)
            self.complexBuffer = Utilities.resizeArray(self.complexBuffer, self.YOrder)
            # used by both PD and PC elements
            _5 = True
            i = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    i += 1
                if not (i < value):
                    break
                self.terminals[i] = PowerTerminal(self.nConds)

    def getNTerms(self):
        return self.nTerms

    def setEnabled(self, value):
        if value != self.enabled:
            # don't change unless this represents a change
            self.enabled = value
            # force rebuilding of Y matrix and bus lists
            DSSGlobals.activeCircuit.setBusNameRedefined(True)

    def isEnabled(self):
        return self.enabled

    def getYPrim(self, YMatrix, opt):
        # FIXME Pass by reference
        _0 = opt
        _1 = False
        while True:
            if _0 == DSSGlobals.ALL_YPRIM:
                _1 = True
                YMatrix = self.YPrim
                break
            if (_1 is True) or (_0 == DSSGlobals.SERIES):
                _1 = True
                YMatrix = self.YPrimSeries
                break
            if (_1 is True) or (_0 == DSSGlobals.SHUNT):
                _1 = True
                YMatrix = self.YPrimShunt
                break
            break
        return 0

    def getYPrimValues(self, opt):
        """Returns the storage arrays for fast access."""
        result = None
        _0 = opt
        _1 = False
        while True:
            if _0 == DSSGlobals.ALL_YPRIM:
                _1 = True
                if self.YPrim is not None:
                    result = self.YPrim.asArray()
                break
            if (_1 is True) or (_0 == DSSGlobals.SERIES):
                _1 = True
                if self.YPrimSeries is not None:
                    result = self.YPrimSeries.asArray()
                break
            if (_1 is True) or (_0 == DSSGlobals.SHUNT):
                _1 = True
                if self.YPrimShunt is not None:
                    result = self.YPrimShunt.asArray()
                break
            break
        return result

    def getCurrents(self, curr):
        """Get present value of terminal current for reports."""
        DSSGlobals.doErrorMsg('Something is wrong. Got to base CktElement getCurrents for object:' + DSSGlobals.CRLF + self.getDSSClassName() + '.' + self.getName(), 'N/A', 'Should not be able to get here. Probable programming error.', 751)

    def getInjCurrents(self, curr):
        """Returns injection currents"""
        DSSGlobals.doErrorMsg('Something is Wrong. Got to base CktElement GetInjCurrents for object:' + DSSGlobals.CRLF + self.getDSSClassName() + '.' + self.getName(), '****', 'Should not be able to get here. Probable programming error.', 752)

    def getLosses(self, *args):
        """None
        ---
        Get total losses in circuit element, all phases, all terminals.
        Returns complex losses (watts, vars).
        """
        _0 = args
        _1 = len(args)
        if _1 == 0:
            cLoss = Complex.ZERO
            pass # astStmt: [Stmt([]), 502]
            if self.enabled:
                self.computeITerminal()
                # sum complex power going into all conductors of all terminals
                sol = DSSGlobals.activeCircuit.getSolution()
                _0 = True
                k = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        k += 1
                    if not (k < self.YOrder):
                        break
                    n = self.nodeRef[k]
                    if n > 0:
                        if DSSGlobals.activeCircuit.isPositiveSequence():
                            cLoss = cLoss.add(sol.getNodeV()[n].multiply(self.ITerminal[k].conjugate()).multiply(3.0))
                        else:
                            cLoss = cLoss.add(sol.getNodeV()[n].multiply(self.ITerminal[k].conjugate()))
            return cLoss
        elif _1 == 3:
            totalLosses, loadLosses, NoLoadLosses = _0
            # watts, vars
            totLosses = self.getLosses()
            totalLosses[0] = totLosses.getReal()
            totalLosses[1] = totLosses.getImaginary()
            loadLosses[0] = totLosses.getReal()
            loadLosses[1] = totLosses.getImaginary()
            NoLoadLosses[0] = 0
            NoLoadLosses[1] = 0
        else:
            raise ARGERROR(0, 3)

    def injCurrents(self):
        """Applies to PC Elements Puts straight into solution array"""
        DSSGlobals.doErrorMsg('Improper call to injCurrents for element: ' + self.getName() + '.', '****', 'Called CktElement class base function instead of actual.', 753)
        return 0

    def setNodeRef(self, *args):
        """Set NodeRef array for fast solution with intrinsics.

        Also allocates VTemp & ITemp.
        """
        _0 = args
        _1 = len(args)
        if _1 == 1:
            ref, = _0
            self.nodeRef = ref
        elif _1 == 2:
            iTerm, nodeRefArray = _0
            # allocate nodeRef and move new values into it.
            pass # astStmt: [Stmt([]), 360]
            size = self.YOrder
            size2 = self.nConds
            # size for one terminal
            self.nodeRef = Utilities.resizeArray(self.nodeRef, size)
            # doesn't do anything if already properly allocated
            System.arraycopy(nodeRefArray[0], 0, self.nodeRef[((iTerm - 1) * self.nConds) + 1], 0, size2)
            System.arraycopy(nodeRefArray[0], 0, self.terminals[iTerm].termNodeRef[0], 0, size2)
            # copy in terminal as well
            # allocate temp array used to hold voltages and currents for calcs
            self.VTerminal = Utilities.resizeArray(self.VTerminal, self.YOrder)
            self.ITerminal = Utilities.resizeArray(self.ITerminal, self.YOrder)
            self.complexBuffer = Utilities.resizeArray(self.complexBuffer, self.YOrder)
            iTerm, nodeRefArray = _0
            self.setNodeRef(iTerm, [nodeRefArray])
        else:
            raise ARGERROR(1, 2)

    def getFirstBus(self):
        if self.nTerms > 0:
            self.busIndex = 0
            # TODO Check zero based indexing
            return self.busNames[self.busIndex]
        else:
            return ''

    def getNextBus(self):
        result = ''
        if self.nTerms > 0:
            self.busIndex += 1
            if self.busIndex <= self.nTerms:
                result = self.busNames[self.busIndex]
            else:
                self.busIndex = self.nTerms
        return result

    def getBus(self, i):
        """Get bus name by index."""
        if i <= self.nTerms:
            return self.busNames[i]
        else:
            return ''

    def setBus(self, i, s):
        """Set bus name by index."""
        if i <= self.nTerms:
            self.busNames[i] = s.toLowerCase()
            # set global flag to signal circuit to rebuild bus defs
            DSSGlobals.activeCircuit.setBusNameRedefined(True)
        else:
            DSSGlobals.doSimpleMsg(String.format.format('Attempt to set bus name for non-existent circuit element terminal(%d): \"%s\"', i, s), 7541)

    def setFreq(self, value):
        """Set freq and recompute YPrim."""
        if value > 0.0:
            self.YPrimFreq = value

    def recalcElementData(self):
        DSSGlobals.doSimpleMsg('Virtual proc recalcElementData in base CktElement class called for device = \"' + self.getName() + '\"', 754)

    def calcYPrim(self):
        if self.YPrimSeries is not None:
            self.doYprimCalcs(self.YPrimSeries)
        if self.YPrimShunt is not None:
            self.doYprimCalcs(self.YPrimShunt)
        if self.YPrim is not None:
            self.doYprimCalcs(self.YPrim)

    def computeITerminal(self):
        """Computes ITerminal for this device."""
        # to save time, only recompute if a different solution than last time it was computed
        if (
            self.ITerminalSolutionCount != DSSGlobals.activeCircuit.getSolution().getSolutionCount()
        ):
            self.getCurrents(self.ITerminal)
            self.ITerminalSolutionCount = DSSGlobals.activeCircuit.getSolution().getSolutionCount()

    def maxTerminalOneIMag(self):
        """Max of ITerminal 1 phase currents."""
        result = 0.0
        if self.enabled:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                result = self.Math.max(result, self.Math.pow(self.ITerminal[i].getReal(), 2) + self.Math.pow(self.ITerminal[i].getImaginary(), 2))
        return self.Math.sqrt(result)
        # just do the sqrt once and save a little time

    def getPower(self, idxTerm):
        """Get total complex power in active terminal."""
        cPower = Complex.ZERO
        self.activeTerminalIdx = idxTerm
        if self.enabled:
            self.computeITerminal()
            # sum complex power going into phase conductors of active terminal
        sol = DSSGlobals.activeCircuit.getSolution()
        k = (idxTerm - 1) * self.nConds
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nConds):
                break
            # 11-7-08 changed from nPhases - was not accounting for all conductors
            n = self.activeTerminal.getTermNodeRef()[i]
            # don't bother for grounded node
            if n > 0:
                cPower = cPower.add(sol.getNodeV()[n].multiply(self.ITerminal[k + i].conjugate()))
        # If this is a positive sequence circuit, then we need to multiply by 3 to get the 3-phase power
        if DSSGlobals.activeCircuit.isPositiveSequence():
            cPower = cPower.multiply(3.0)
        return cPower

    def getPhasePower(self, powerBuffer):
        """Get the power in each phase (complex losses) of active terminal
        neutral conductors are ignored by this routine.
        """
        if self.enabled:
            self.computeITerminal()
            sol = DSSGlobals.activeCircuit.getSolution()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                n = self.nodeRef[i]
                # increment through terminals
                if n > 0:
                    if DSSGlobals.activeCircuit.isPositiveSequence():
                        powerBuffer[i] = sol.getNodeV()[n].multiply(self.ITerminal[i].conjugate()).multiply(3.0)
                    else:
                        powerBuffer[i] = sol.getNodeV()[n].multiply(self.ITerminal[i].conjugate())
        else:
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                powerBuffer[i] = Complex.ZERO

    def getPhaseLosses(self, numPhases, LossBuffer):
        """Get the losses in each phase (complex losses);  Power difference coming out
        each phase. Note: This can be misleading if the nodeV voltage is greatly unbalanced.

        Neutral conductors are ignored by this routine.
        """
        numPhases = self.nPhases
        if self.enabled:
            self.computeITerminal()
            sol = DSSGlobals.activeCircuit.getSolution()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < numPhases):
                    break
                cLoss = Complex.ZERO
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < self.nTerms):
                        break
                    k = ((j - 1) * self.nConds) + i
                    n = self.nodeRef[k]
                    # increment through terminals
                    if n > 0:
                        if DSSGlobals.activeCircuit.isPositiveSequence():
                            cLoss = cLoss.add(sol.getNodeV()[n].multiply(self.ITerminal[k].conjugate()).multiply(3.0))
                        else:
                            cLoss = cLoss.add(sol.getNodeV()[n].multiply(self.ITerminal[k].conjugate()))
                LossBuffer[i] = cLoss
        else:
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < numPhases):
                    break
                LossBuffer[i] = Complex.ZERO

    def dumpProperties(self, f, complete):
        # FIXME Implement this method
        raise self.UnsupportedOperationException()

    def doYprimCalcs(self, YMatrix):
        k = 0
        rowEliminated = None
        elementOpen = False
        # Now account for open conductors
        # For any conductor that is open, zero out row and column
        k = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nTerms):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.nConds):
                    break
                if not self.terminals[i].getConductors()[j].isClosed():
                    if not elementOpen:
                        rowEliminated = [None] * self.YOrder
                        elementOpen = True
                    # first do Kron reduction
                    elimRow = j + k
                    Ynn = YMatrix.getElement(elimRow, elimRow)
                    if Ynn.abs() == 0.0:
                        Ynn = Complex(DSSGlobals.EPSILON, Ynn.getImaginary())
                    rowEliminated[elimRow] = 1
                    # TODO Check zero based indexing.
                    _2 = True
                    ii = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            ii += 1
                        if not (ii < self.YOrder):
                            break
                        if rowEliminated[ii] == 0:
                            Yin = YMatrix.getElement(ii, elimRow)
                            _3 = True
                            jj = 0
                            while True:
                                if _3 is True:
                                    _3 = False
                                else:
                                    jj += 1
                                if not (jj < self.YOrder):
                                    break
                                if rowEliminated[jj] == 0:
                                    Yij = YMatrix.getElement(ii, jj)
                                    Ynj = YMatrix.getElement(elimRow, jj)
                                    YMatrix.setElemSym(ii, jj, Yij.subtract(Yin.multiply(Ynj).divide(Ynn)))
                    # now zero out row and column
                    YMatrix.zeroRow(elimRow)
                    YMatrix.zeroCol(elimRow)
                    YMatrix.setElement(elimRow, elimRow, Complex(DSSGlobals.EPSILON, 0.0))
                    # in case node gets isolated
            k = k + self.nConds
        if elementOpen:
            rowEliminated = [None] * 0

    def sumCurrents(self):
        """Sum terminal currents into system currents array.

        Primarily for Newton iteration.
        """
        if self.enabled:
            self.computeITerminal()
            sol = DSSGlobals.activeCircuit.getSolution()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                sol.getCurrents()[self.nodeRef[i]] = sol.getCurrents()[self.nodeRef[i]].add(self.ITerminal[i])
                # NodeRef=0 is OK

    def getTermVoltages(self, iTerm, VBuffer):
        """Bus voltages at indicated terminal.

        Fills VBuffer array which must be adequately allocated by calling routine.
        """
        try:
            ncond = self.nConds
            # Return zero if terminal number improperly specified
            if (iTerm < 0) or (iTerm > self.nTerms):
                # TODO Check zero based indexing
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < ncond):
                        break
                    VBuffer[i] = Complex.ZERO
                return
            sol = DSSGlobals.activeCircuit.getSolution()
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < ncond):
                    break
                VBuffer[i] = sol.getNodeV()[self.terminals[iTerm].getTermNodeRef()[i]]
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error filling voltage buffer in getTermVoltages for circuit element:' + self.getDSSClassName() + '.' + self.getName() + DSSGlobals.CRLF + 'Probable cause: Invalid definition of element.' + DSSGlobals.CRLF + 'System error message: ' + e.getMessage(), 755)

    def initPropertyValues(self, arrayOffset):
        # TODO Check zero based indexing
        self.propertyValue[arrayOffset + 1] = String.format.format('%g', self.baseFrequency)
        # base freq
        self.propertyValue[arrayOffset + 2] = 'true'
        # enabled
        self.enabledProperty = arrayOffset + 2
        # keep track of this
        super(DSSCktElement, self).initPropertyValues(arrayOffset + 2)

    def getPropertyValue(self, index):
        if index == self.enabledProperty:
            if self.enabled:
                result = 'true'
            else:
                result = 'false'
        else:
            result = super(DSSCktElement, self).getPropertyValue(index)
        return result

    def getSeqLosses(self, posSeqLosses, negSeqLosses, zeroModeLosses):
        """For the base class, just return complex zero.

        Derived classes have to supply appropriate function.
        """
        posSeqLosses[0] = 0
        posSeqLosses[1] = 0
        negSeqLosses[0] = 0
        negSeqLosses[1] = 0
        zeroModeLosses[0] = 0
        zeroModeLosses[1] = 0

    def isGroundBus(self, s):
        result = True
        i = s.find('.1')
        if i >= 0:
            result = False
        i = s.find('.2')
        if i >= 0:
            result = False
        i = s.find('.3')
        if i >= 0:
            result = False
        i = s.find('.')
        if i == -1:
            result = False
        return result

    def makePosSequence(self):
        """Make a positive sequence model."""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nTerms):
                break
            grnd = self.isGroundBus(self.busNames[i])
            self.busNames[i] = Utilities.stripExtension(self.busNames[i])
            if grnd:
                self.busNames[i] = self.busNames[i] + '.0'

    def computeVTerminal(self):
        """Put terminal voltages in an array."""
        sol = DSSGlobals.activeCircuit.getSolution()
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            self.VTerminal[i] = sol.getNodeV()[self.nodeRef[i]]

    def zeroITerminal(self):
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            self.ITerminal[i] = Complex.ZERO

    def getNodeRef(self):
        return self.nodeRef

    def getYorder(self):
        return self.YOrder

    def setYorder(self, order):
        self.YOrder = order

    def getLastTerminalChecked(self):
        return self.lastTerminalChecked

    def setLastTerminalChecked(self, checked):
        self.lastTerminalChecked = checked

    def isChecked(self):
        return self.checked

    def setChecked(self, value):
        self.checked = value

    def hasEnergyMeter(self):
        return self.hasEnergyMeter

    def setHasEnergyMeter(self, hasMeter):
        self.hasEnergyMeter = hasMeter

    def hasSensorObj(self):
        return self.hasSensorObj

    def setHasSensorObj(self, value):
        self.hasSensorObj = value

    def isIsolated(self):
        return self.isIsolated

    def setIsolated(self, value):
        self.isIsolated = value

    def hasControl(self):
        return self.hasControl

    def setHasControl(self, value):
        self.hasControl = value

    def isPartofFeeder(self):
        return self.isPartOfFeeder

    def setPartofFeeder(self, isPart):
        self.isPartOfFeeder = isPart

    def getControlElement(self):
        return self.controlElement

    def setControlElement(self, element):
        self.controlElement = element

    def getITerminal(self):
        return self.ITerminal

    def setITerminal(self, terminal):
        self.ITerminal = terminal

    def getVTerminal(self):
        return self.VTerminal

    def setVTerminal(self, terminal):
        self.VTerminal = terminal

    def getBaseFrequency(self):
        return self.baseFrequency

    def setBaseFrequency(self, frequency):
        self.baseFrequency = frequency

    def getTerminals(self):
        return self.terminals

    def setTerminals(self, value):
        self.terminals = value

    def setActiveTerminal(self, terminal):
        self.activeTerminal = terminal

    def getActiveTerminal(self):
        return self.activeTerminal

    def setYPrimFreq(self, Value):
        self.setFreq(Value)

    def getYPrimFreq(self):
        return self.YPrimFreq

    def getHandle(self):
        return 0
