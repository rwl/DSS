from __pyjamas__ import (ARGERROR,)
from dss.delivery.impl.TransformerImpl import (TransformerImpl,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.Transformer import (Transformer,)
from dss.delivery.impl.PDElementImpl import (PDElementImpl,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.TransformerObj import (TransformerObj,)
from dss.delivery.impl.WindingImpl import (WindingImpl,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from java.io.PrintWriter import (PrintWriter,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class TransformerObjImpl(PDElementImpl, TransformerObj):
    deltaDirection = None
    ppmFloatFactor = None
    # parts per million winding float factor
    pctImag = None
    numWindings = None
    maxWindings = None
    termRef = None
    # keeps track of terminal connections
    XHL = None
    XHT = None
    XLT = None
    # per unit
    ZBase = None
    XSC = None
    # per unit SC measurements
    VABase = None
    # for impedances
    ZB = None
    Y_1Volt = None
    Y_Term = None
    Y_1Volt_NL = None
    # no load Y's
    Y_Term_NL = None
    Y_Terminal_FreqMult = None
    normMaxHKVA = None
    emergMaxHKVA = None
    thermalTimeConst = None
    # hr
    nThermal = None
    mThermal = None
    # exponents
    FLRise = None
    HSRise = None
    pctLoadLoss = None
    pctNoLoadLoss = None
    XHLChanged = None
    activeWinding = None
    isSubstation = None
    substationName = None
    winding = None
    XfmrBank = None
    XfmrCode = None

    def __init__(self, parClass, transfName):
        super(TransformerObjImpl, self)(parClass)
        self.setName(transfName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # DSSObjType + XFMR; // override PDElement (kept in both actually)
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = self.nPhases + 1
        self.setNumWindings(2)
        # must do this after setting number of phases
        self.activeWinding = 0
        # TODO Check zero based indexing
        self.setNTerms(self.numWindings)
        # force allocation of terminals and conductors
        self.XHL = 0.07
        self.XHT = 0.35
        self.XLT = 0.3
        self.XHLChanged = True
        # set flag to for calc of XSC array from XHL, etc.
        self.deltaDirection = 1
        self.substationName = ''
        self.XfmrBank = ''
        self.XfmrCode = ''
        self.VABase = self.winding[0].getKVA() * 1000.0
        self.thermalTimeConst = 2.0
        self.nThermal = 0.8
        self.mThermal = 0.8
        self.FLRise = 65.0
        self.HSRise = 15.0
        # hot spot rise
        self.normMaxHKVA = 1.1 * self.winding[1].getKVA()
        self.emergMaxHKVA = 1.5 * self.winding[1].getKVA()
        self.pctLoadLoss = 2.0 * self.winding[1].getRpu() * 100.0
        # assume two windings
        self.ppmFloatFactor = 1e-06
        # Compute antifloat added for each winding
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            self.winding[i].computeAntiFloatAdder(self.ppmFloatFactor, self.VABase / self.nPhases)
            # Default the no load properties to zero
        self.pctNoLoadLoss = 0.0
        self.pctImag = 0.0
        # BaseFrequency = 60.0; set in base class to circuit fundamental freq; do not reset here
        self.setFaultRate(0.007)
        self.isSubstation = False
        self.Y_Terminal_FreqMult = 0.0
        self.YOrder = self.nTerms * self.nConds
        self.initPropertyValues(0)
        self.recalcElementData()

    def setNumWindings(self, n):
        if n > 1:
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numWindings):
                    break
                self.winding[i] = None
                # free old winding objects
            oldWdgSize = ((self.numWindings - 1) * self.numWindings) / 2
            self.numWindings = n
            self.maxWindings = n
            self.setNConds(self.nPhases + 1)
            self.setNTerms(self.numWindings)
            self.winding = Utilities.resizeArray(self.winding, self.maxWindings)
            # reallocate collector array
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.maxWindings):
                    break
                self.winding[i] = WindingImpl()
                # array of short circuit measurements between pairs of windings
            self.XSC = Utilities.resizeArray(self.XSC, ((self.numWindings - 1) * self.numWindings) / 2)
            _2 = True
            i = oldWdgSize
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                    break
                # TODO Check zero based indexing
                self.XSC[i] = 0.3
            self.termRef = Utilities.resizeArray(self.termRef, 2 * self.numWindings * self.nPhases)
            # Reallocate impedance matrices
            self.ZB = None
            self.Y_1Volt = None
            self.Y_1Volt_NL = None
            self.Y_Term = None
            self.Y_Term_NL = None
            self.ZB = CMatrixImpl(self.numWindings - 1)
            self.Y_1Volt = CMatrixImpl(self.numWindings)
            self.Y_1Volt_NL = CMatrixImpl(self.numWindings)
            self.Y_Term = CMatrixImpl(2 * self.numWindings)
            self.Y_Term_NL = CMatrixImpl(2 * self.numWindings)
        else:
            DSSGlobals.doSimpleMsg('Invalid number of windings: ' + String.valueOf.valueOf(n) + ' for transformer ' + self.getName(), 111)

    def recalcElementData(self):
        # PrintWriter F;
        # determine delta direction
        # if high voltage is delta, delta leads wye
        # if high voltage is wye, delta lags wye
        if self.winding[0].getConnection() == self.winding[1].getConnection():
            self.deltaDirection = 1
        else:
            if self.winding[0].getKVLL() >= self.winding[1].getKVLL():
                iHVolt = 1
            else:
                iHVolt = 2
            _0 = self.winding[iHVolt].getConnection()
            _1 = False
            while True:
                if _0 == 0:
                    _1 = True
                    self.deltaDirection = 1
                    break
                if (_1 is True) or (_0 == 1):
                    _1 = True
                    self.deltaDirection = -1
                    break
                if True:
                    _1 = True
                    break
                break
        self.setTermRef()
        # re-establish termRef if num windings or connection changed
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            w = self.winding[i]
            if w.getNumTaps() > 0:
                w.setTapIncrement((w.getMaxTap() - w.getMinTap()) / w.getNumTaps())
            else:
                w.setTapIncrement(0.0)
        if self.XHLChanged:
            # should only happen for 2- and 3-winding transformers
            if self.numWindings <= 3:
                _3 = True
                i = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        i += 1
                    if not (i < (self.numWindings * (self.numWindings - 1)) / 2):
                        break
                    _4 = i
                    _5 = False
                    while True:
                        if _4 == 0:
                            _5 = True
                            self.XSC[0] = self.XHL
                            break
                        if (_5 is True) or (_4 == 1):
                            _5 = True
                            self.XSC[1] = self.XHT
                            break
                        if (_5 is True) or (_4 == 3):
                            _5 = True
                            self.XSC[2] = self.XLT
                            break
                        break
            self.XHLChanged = False
        # set winding voltage bases (in volts)
        _6 = True
        i = 0
        while True:
            if _6 is True:
                _6 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            w = self.winding[i]
            # get the actual turns voltage base for each winding
            _7 = w.getConnection()
            _8 = False
            while True:
                if _7 == 0:
                    _8 = True
                    # wye
                    _9 = self.nPhases
                    _10 = False
                    while True:
                        if _9 == 2:
                            _10 = True
                            w.setVBase(w.getKVLL() * DSSGlobals.InvSQRT3x1000)
                            # assume 3-phase for 2-phase designation
                            break
                        if (_10 is True) or (_9 == 3):
                            _10 = True
                            w.setVBase(w.getKVLL() * DSSGlobals.InvSQRT3x1000)
                            break
                        if True:
                            _10 = True
                            w.setVBase(w.getKVLL() * 1000.0)
                            break
                        break
                    break
                if (_8 is True) or (_7 == 1):
                    _8 = True
                    w.setVBase(w.getKVLL() * 1000.0)
                    # delta
                    break
                break
        # Base rating of winding 1
        self.VABase = self.winding[0].getKVA() * 1000.0
        _11 = True
        i = 0
        while True:
            if _11 is True:
                _11 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            self.winding[i].computeAntiFloatAdder(self.ppmFloatFactor, self.VABase / self.nPhases)
            # Normal and emergency terminal current rating for UE check
        VFactor = 1.0
        # ensure initialization
        _12 = self.winding[0].getConnection()
        _13 = False
        while True:
            if _12 == 0:
                _13 = True
                VFactor = self.winding[0].getVBase() * 0.001
                # wye
                break
            if (_13 is True) or (_12 == 1):
                _13 = True
                _14 = self.nPhases
                _15 = False
                while True:
                    if _14 == 1:
                        _15 = True
                        VFactor = self.winding[0].getVBase() * 0.001
                        break
                    if (_15 is True) or (_14 == 2):
                        _15 = True
                        VFactor = (self.winding[0].getVBase() * 0.001) / DSSGlobals.SQRT3
                        break
                    if (_15 is True) or (_14 == 3):
                        _15 = True
                        VFactor = (self.winding[0].getVBase() * 0.001) / DSSGlobals.SQRT3
                        break
                    if True:
                        _15 = True
                        VFactor = (self.winding[0].getVBase() * 0.001 * 0.5) / self.Math.sin(self.Math.PI / self.nPhases)
                        break
                    break
                break
            break
        # Divide per phase kVA by voltage to neutral
        self.setNormAmps(self.normMaxHKVA / self.nPhases / VFactor)
        self.setEmergAmps(self.emergMaxHKVA / self.nPhases / VFactor)
        self.calcY_Terminal(1.0)
        # calc Y_Terminal at base frequency

    def saveWrite(self, f):
        """Transformer structure not conducive to standard means of saving."""
        # FIXME Protected method in OpenDSS
        # Write only properties that were explicitly set in the
        # final order they were actually set.

        iProp = self.getNextPropertySet(0)
        # works on ActiveDSSObject   TODO Check zero based indexing
        while # Trap wdg= and write out array properties instead iProp > 0:
            _0 = self.parentClass.getRevPropertyIdxMap()[iProp]
            _1 = False
            while True:
                if _0 == 2:
                    _1 = True
                    _2 = True
                    i = 11
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < 16):
                            break
                        f.printf(' %s=%s', self.parentClass.getPropertyName()[i], self.getPropertyValue(i))
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < self.numWindings):
                            break
                        f.printf(' wdg=%d %sR=%.7g', i, '%', self.winding[i].getRpu() * 100.0)
                    break
                if (_1 is True) or (_0 == 3):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 4):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 5):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 6):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 7):
                    _1 = True
                    break
                if (_1 is True) or (_0 == 8):
                    _1 = True
                    break
                if True:
                    _1 = True
                    f.printf(' %s=%s', self.parentClass.getPropertyName()[self.parentClass.getRevPropertyIdxMap()[iProp]], Utilities.checkForBlanks(self.getPropertyValue(iProp)))
                    break
                break
            iProp = self.getNextPropertySet(iProp)

    def setTermRef(self, *args):
        """Sets an array which maps the two conductors of each winding to the phase
        and neutral conductors of the transformer according to the winding connection.
        """
        _0 = args
        _1 = len(args)
        if _1 == 0:
            pass # astStmt: [Stmt([]), 333]
            k = -1
            _0 = self.nPhases
            _1 = False
            while True:
                if _0 == 1:
                    _1 = True
                    _2 = True
                    j = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            j += 1
                        if not (j < self.numWindings):
                            break
                        k += 1
                        self.termRef[k] = ((j - 1) * self.nConds) + 1
                        k += 1
                        self.termRef[k] = j * self.nConds
                    break
                if True:
                    _1 = True
                    _3 = True
                    i = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        _4 = True
                        j = 0
                        while True:
                            if _4 is True:
                                _4 = False
                            else:
                                j += 1
                            if not (j < self.numWindings):
                                break
                            k += 1
                            _5 = self.winding[i].getConnection()
                            _6 = False
                            while True:
                                if _5 == 0:
                                    _6 = True
                                    self.termRef[k] = ((j - 1) * self.nConds) + i
                                    k += 1
                                    self.termRef[k] = j * self.nConds
                                    break
                                    # **** WILL THIS WORK for 2-PHASE OPEN DELTA ???? Need to check this sometime
                                if (_6 is True) or (_5 == 1):
                                    _6 = True
                                    self.termRef[k] = ((j - 1) * self.nConds) + i
                                    k += 1
                                    self.termRef[k] = ((j - 1) * self.nConds) + self.rotatePhases(i)
                                    # connect to next phase in sequence
                                    break
                                break
                    break
                break
        elif _1 == 1:
            ref, = _0
            self.termRef = ref
        else:
            raise ARGERROR(0, 1)

    def calcYPrim(self):
        if self.isYprimInvalid():
            # reallocate YPrim if something has invalidated old allocation
            if self.YPrimSeries is not None:
                self.YPrimSeries = None
            if self.YPrimShunt is not None:
                self.YPrimShunt = None
            if self.YPrim is not None:
                self.YPrim = None
            self.YPrimSeries = CMatrixImpl(self.YOrder)
            self.YPrimShunt = CMatrixImpl(self.YOrder)
            self.YPrim = CMatrixImpl(self.YOrder)
        else:
            # Same size as last time; just zero out to start over
            self.YPrimSeries.clear()
            # zero out YPrim
            self.YPrimShunt.clear()
            # zero out YPrim
            self.YPrim.clear()
        # Setfrequency multipliers for this calculation
        self.YPrimFreq = DSSGlobals.activeCircuit.getSolution().getFrequency()
        freqMultiplier = self.YPrimFreq / self.baseFrequency
        # Check for rebuilding Y_Terminal; only rebuild if freq is different than last time
        if freqMultiplier != self.Y_Terminal_FreqMult:
            self.calcY_Terminal(freqMultiplier)
        self.buildYPrimComponent(self.YPrimSeries, self.Y_Term)
        self.buildYPrimComponent(self.YPrimShunt, self.Y_Term_NL)
        self.addNeutralToY(freqMultiplier)
        # Combine the two YPrim components into YPrim
        self.YPrim.copyFrom(self.YPrimSeries)
        self.YPrim.addFrom(self.YPrimShunt)
        # Now account for open conductors
        # For any conductor that is open, zero out row and column
        super(TransformerObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def dumpProperties(self, f, complete):
        super(TransformerObjImpl, self).dumpProperties(f, complete)
        # Basic property dump
        print '~ ' + 'NumWindings=' + self.numWindings
        print '~ ' + 'phases=' + self.nPhases
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            W = self.winding[i]
            if i == 0:
                print '~ ' + 'wdg=' + i + ' bus=' + self.getFirstBus()
            else:
                print '~ ' + 'wdg=' + i + ' bus=' + self.getNextBus()
            _1 = W.getConnection()
            _2 = False
            while True:
                if _1 == 0:
                    _2 = True
                    print '~ conn=wye'
                    break
                if (_2 is True) or (_1 == 1):
                    _2 = True
                    print '~ conn=delta'
                    break
                break
            print '~ kv=' + W.getKVLL()
            print '~ kva=' + W.getKVA()
            print '~ tap=' + W.getPUTap()
            print '~ %r=' + (W.getRpu() * 100.0)
            print '~ rneut=' + W.getRNeut()
            print '~ xneut=' + W.getXNeut()
        print '~ ' + 'xhl=' + (self.XHL * 100.0)
        print '~ ' + 'xht=' + (self.XHT * 100.0)
        print '~ ' + 'xlt=' + (self.XLT * 100.0)
        f.print_('~ Xscmatrix= \"')
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                break
            f.print_((self.XSC[i] * 100.0) + ' ')
        print '\"'
        print '~ ' + 'NormMAxHkVA=' + self.normMaxHKVA
        print '~ ' + 'EmergMAxHkVA=' + self.emergMaxHKVA
        print '~ ' + 'thermal=' + self.thermalTimeConst
        print '~ ' + 'n=' + self.nThermal
        print '~ ' + 'm=' + self.mThermal
        print '~ ' + 'flrise=' + self.FLRise
        print '~ ' + 'hsrise=' + self.HSRise
        print '~ ' + '%loadloss=' + self.pctLoadLoss
        print '~ ' + '%noloadloss=' + self.pctNoLoadLoss
        _4 = True
        i = 27
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < Transformer.NumPropsThisClass):
                break
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        _5 = True
        i = Transformer.NumPropsThisClass
        while True:
            if _5 is True:
                _5 = False
            else:
                i += 1
            if not (i < self.parentClass.getNumProperties()):
                break
            print '~ ' + self.parentClass.getPropertyName()[i] + '=' + self.getPropertyValue(i)
        if complete:
            print 
            print 'ZB: (inverted)'
            _6 = True
            i = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    i += 1
                if not (i < self.numWindings - 1):
                    break
                _7 = True
                j = 0
                while True:
                    if _7 is True:
                        _7 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    f.print_(self.ZB.getElement(i, j).getReal() + ' ')
                print 
            _8 = True
            i = 0
            while True:
                if _8 is True:
                    _8 = False
                else:
                    i += 1
                if not (i < self.numWindings - 1):
                    break
                _9 = True
                j = 0
                while True:
                    if _9 is True:
                        _9 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    f.print_(self.ZB.getElement(i, j).getImaginary() + ' ')
                print 
            print 
            print 'Y_OneVolt'
            _10 = True
            i = 0
            while True:
                if _10 is True:
                    _10 = False
                else:
                    i += 1
                if not (i < self.numWindings):
                    break
                _11 = True
                j = 0
                while True:
                    if _11 is True:
                        _11 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    f.print_(self.Y_1Volt.getElement(i, j).getReal() + ' ')
                print 
            _12 = True
            i = 0
            while True:
                if _12 is True:
                    _12 = False
                else:
                    i += 1
                if not (i < self.numWindings):
                    break
                _13 = True
                j = 0
                while True:
                    if _13 is True:
                        _13 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    f.print_(self.Y_1Volt.getElement(i, j).getImaginary() + ' ')
                print 
            print 
            print 'Y_Terminal'
            _14 = True
            i = 0
            while True:
                if _14 is True:
                    _14 = False
                else:
                    i += 1
                if not (i < 2 * self.numWindings):
                    break
                _15 = True
                j = 0
                while True:
                    if _15 is True:
                        _15 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    f.print_(self.Y_Term.getElement(i, j).getReal() + ' ')
                print 
            _16 = True
            i = 0
            while True:
                if _16 is True:
                    _16 = False
                else:
                    i += 1
                if not (i < 2 * self.numWindings):
                    break
                _17 = True
                j = 0
                while True:
                    if _17 is True:
                        _17 = False
                    else:
                        j += 1
                    if not (j < i):
                        break
                    f.print_(self.Y_Term.getElement(i, j).getImaginary() + ' ')
                print 
            print 
            f.print_('TermRef= ')
            _18 = True
            i = 0
            while True:
                if _18 is True:
                    _18 = False
                else:
                    i += 1
                if not (i < 2 * self.numWindings * self.nPhases):
                    break
                f.print_(self.termRef[i] + ' ')
            print 

    def setPresentTap(self, i, value):
        if i >= 0 and i < self.numWindings:
            W = self.winding[i]
            # Range Checking
            tempVal = value
            if tempVal < W.getMinTap():
                tempVal = W.getMinTap()
            elif tempVal > W.getMaxTap():
                tempVal = W.getMaxTap()
            if tempVal != W.getPUTap():
                # Only if there's been a change
                W.setPUTap(tempVal)
                self.setYPrimInvalid(True)
                # this property triggers setting systemYChanged=true
                self.recalcElementData()

    def getWdgResistance(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getRpu()
        else:
            return 0.0

    def getWdgKVA(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getKVA()
        else:
            return 0.0

    def getWdgRNeutral(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getRNeut()
        else:
            return 0.0

    def getWdgXNeutral(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getXNeut()
        else:
            return 0.0

    def getWdgYPPM(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getY_PPM()
        else:
            return 0.0

    def getXsc(self, i):
        imax = ((self.numWindings - 1) * self.numWindings) / 2
        if i >= 0 and i < imax:
            return self.XSC[i]
        else:
            return 0.0

    def getWdgConnection(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getConnection()
        else:
            return 0

    def getMinTap(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getMinTap()
        else:
            return 0.0

    def getMaxTap(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getMaxTap()
        else:
            return 0.0

    def getNumTaps(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getNumTaps()
        else:
            return 0

    def getTapIncrement(self, i):
        if i >= 0 and i < self.numWindings:
            return self.winding[i].getTapIncrement()
        else:
            return 0.0

    def getWindingVoltages(self, iWind, VBuffer):
        """Voltages across indicated winding.
        Fill VBuffer array which must be adequately allocated by calling routine
        Order is number of phases.
        """
        # Return zero if winding number improperly specified
        try:
            if (iWind < 0) or (iWind >= self.numWindings):
                _0 = True
                i = 0
                while True:
                    if _0 is True:
                        _0 = False
                    else:
                        i += 1
                    if not (i < self.nConds):
                        break
                    VBuffer[i] = Complex.ZERO
                return
            # Load up VTemp - already allocated for all cktelements
            sol = DSSGlobals.activeCircuit.getSolution()
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                self.VTerminal[i] = sol.getNodeV()[self.nodeRef[i]]
            k = (iWind - 1) * self.nConds
            # Offset for winding   TODO Check zero based indexing
            neutTerm = self.nPhases + k + 1
            _2 = True
            i = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    i += 1
                if not (i < self.nPhases):
                    break
                _3 = self.winding[iWind].getConnection()
                _4 = False
                while True:
                    if _3 == 0:
                        _4 = True
                        VBuffer[i] = self.VTerminal[i + k].subtract(self.VTerminal[neutTerm])
                        break
                    if (_4 is True) or (_3 == 1):
                        _4 = True
                        ii = self.rotatePhases(i)
                        # get next phase in sequence
                        VBuffer[i] = self.VTerminal[i + k].subtract(self.VTerminal[ii + k])
                        break
                    break
        except Exception, e:
            DSSGlobals.doSimpleMsg('Error filling voltage buffer in getWindingVoltages for circuit element: Transformer.' + self.getName() + DSSGlobals.CRLF + 'Probable Cause: Invalid definition of element.' + DSSGlobals.CRLF + 'System error message: ' + e.getMessage(), 114)

    def getBaseVoltage(self, i):
        if (i < 0) or (i >= self.numWindings):
            return self.winding[0].getVBase()
        else:
            return self.winding[i].getVBase()

    def getLosses(self, totalLosses, loadLosses, noLoadLosses):
        # Calculates losses in watts, vars
        cTotalLosses = self.getLosses()
        # Side effect: computes ITerminal
        # Compute no load losses in YPrim_Shunt
        cTempIterminal = [None] * self.YOrder
        self.computeVTerminal()
        self.YPrimShunt.vMult(cTempIterminal, self.VTerminal)
        # No load losses are sum of all powers coming into YPrim_Shunt from each terminal
        cNoLoadLosses = Complex.ZERO
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.YOrder):
                break
            cNoLoadLosses = cNoLoadLosses.add(self.VTerminal[i].multiply(cTempIterminal[i].conjugate()))
        cLoadLosses = cTotalLosses.subtract(cNoLoadLosses)
        # Handle pass by reference
        totalLosses[0] = cTotalLosses.getReal()
        totalLosses[1] = cTotalLosses.getImaginary()
        loadLosses[0] = cLoadLosses.getReal()
        loadLosses[1] = cLoadLosses.getImaginary()
        noLoadLosses[0] = cNoLoadLosses.getReal()
        noLoadLosses[1] = cNoLoadLosses.getImaginary()
        cTempIterminal = None

    def getPropertyValue(self, index):
        """Gets the property for the active winding; set the active winding before calling."""
        if ((index >= 11 and index <= 15) or (index == 19)) or (index == 36):
            result = '['
        else:
            result = ''
        _0 = index
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                result = String.valueOf.valueOf(self.nPhases)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                result = String.valueOf.valueOf(self.numWindings)
                break
            if (_1 is True) or (_0 == 2):
                _1 = True
                result = String.valueOf.valueOf(self.activeWinding)
                # return active winding
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = self.getBus(self.activeWinding)
                # return bus spec for active winding
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                _2 = self.winding[self.activeWinding].getConnection()
                _3 = False
                while True:
                    if _2 == 0:
                        _3 = True
                        result = 'wye '
                        break
                    if (_3 is True) or (_2 == 1):
                        _3 = True
                        result = 'delta '
                        break
                    break
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getKVLL())
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getKVA())
                break
            if (_1 is True) or (_0 == 7):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getPUTap())
                break
            if (_1 is True) or (_0 == 8):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getRpu() * 100.0)
                # %R
                break
            if (_1 is True) or (_0 == 9):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getRNeut())
                break
            if (_1 is True) or (_0 == 10):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getXNeut())
                break
            if (_1 is True) or (_0 == 11):
                _1 = True
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + self.getBus(i) + ', '
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                _5 = True
                i = 0
                while True:
                    if _5 is True:
                        _5 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    _6 = self.winding[i].getConnection()
                    _7 = False
                    while True:
                        if _6 == 0:
                            _7 = True
                            result = result + 'wye, '
                            break
                        if (_7 is True) or (_6 == 1):
                            _7 = True
                            result = result + 'delta, '
                            break
                        break
                break
            if (_1 is True) or (_0 == 13):
                _1 = True
                _8 = True
                i = 0
                while True:
                    if _8 is True:
                        _8 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getKVLL())
                break
            if (_1 is True) or (_0 == 14):
                _1 = True
                _9 = True
                i = 0
                while True:
                    if _9 is True:
                        _9 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getKVA())
                break
            if (_1 is True) or (_0 == 15):
                _1 = True
                _10 = True
                i = 0
                while True:
                    if _10 is True:
                        _10 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getPUTap())
                    # interpretAllTaps(Param);
                break
            if (_1 is True) or (_0 == 16):
                _1 = True
                result = String.format.format('%.7g', self.XHL * 100.0)
                break
            if (_1 is True) or (_0 == 17):
                _1 = True
                result = String.format.format('%.7g', self.XHT * 100.0)
                break
            if (_1 is True) or (_0 == 18):
                _1 = True
                result = String.format.format('%.7g', self.XLT * 100.0)
                break
            if (_1 is True) or (_0 == 19):
                _1 = True
                _11 = True
                i = 0
                while True:
                    if _11 is True:
                        _11 = False
                    else:
                        i += 1
                    if not (i < ((self.numWindings - 1) * self.numWindings) / 2):
                        break
                    result = result + String.format.format('%-g, ', self.XSC[i] * 100.0)
                    # Parser.ParseAsVector(((NumWindings - 1)*NumWindings div 2), Xsc);
                break
            if (_1 is True) or (_0 == 25):
                _1 = True
                result = String.format.format('%.7g', self.pctLoadLoss)
                break
            if (_1 is True) or (_0 == 26):
                _1 = True
                result = String.format.format('%.7g', self.pctNoLoadLoss)
                break
            if (_1 is True) or (_0 == 30):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getMaxTap())
                break
            if (_1 is True) or (_0 == 31):
                _1 = True
                result = String.format.format('%.7g', self.winding[self.activeWinding].getMinTap())
                break
            if (_1 is True) or (_0 == 32):
                _1 = True
                result = String.format.format('%-d', self.winding[self.activeWinding].getNumTaps())
                break
            if (_1 is True) or (_0 == 34):
                _1 = True
                result = String.format.format('%.7g', self.pctImag)
                break
            if (_1 is True) or (_0 == 35):
                _1 = True
                result = String.format.format('%.7g', self.ppmFloatFactor / 1e-06)
                break
            if (_1 is True) or (_0 == 36):
                _1 = True
                _12 = True
                i = 0
                while True:
                    if _12 is True:
                        _12 = False
                    else:
                        i += 1
                    if not (i < self.numWindings):
                        break
                    result = result + String.format.format('%.7g, ', self.winding[i].getRpu() * 100.0)
                break
            if True:
                _1 = True
                result = super(TransformerObjImpl, self).getPropertyValue(index)
                break
            break
        # Overrides
        _13 = index = Transformer.NumPropsThisClass
        _14 = False
        while True:
            if _13 == 0:
                _14 = True
                result = String.format.format('%-.5g', self.getNormAmps())
                # normAmps
                break
            if (_14 is True) or (_13 == 1):
                _14 = True
                result = String.format.format('%-.5g', self.getEmergAmps())
                # emergAmps
                break
            break
        if ((index >= 11 and index <= 15) or (index == 19)) or (index == 36):
            result = ']'
        return result

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = '3'
        # "phases";
        self.propertyValue[1] = '2'
        # "windings";
        # winding definition
        self.propertyValue[2] = '1'
        # "wdg";
        self.propertyValue[3] = self.getBus(0)
        # "bus";
        self.propertyValue[4] = 'wye'
        # "conn";
        self.propertyValue[5] = '12.47'
        # if 2 or 3-phase:  phase-phase else actual winding
        self.propertyValue[6] = '1000'
        self.propertyValue[7] = '1.0'
        self.propertyValue[8] = '0.2'
        self.propertyValue[9] = '-1'
        self.propertyValue[10] = '0'
        # general data
        self.propertyValue[11] = ''
        self.propertyValue[12] = ''
        self.propertyValue[13] = ''
        # if 1-phase: actual winding rating; else phase-phase
        self.propertyValue[14] = ''
        # if 1-phase: actual winding rating; else phase-phase
        self.propertyValue[15] = ''
        self.propertyValue[16] = '7'
        self.propertyValue[17] = '35'
        self.propertyValue[18] = '30'
        self.propertyValue[19] = ''
        # x12 13 14... 23 24.. 34 ..
        self.propertyValue[20] = '2'
        self.propertyValue[21] = '.8'
        self.propertyValue[22] = '.8'
        self.propertyValue[23] = '65'
        self.propertyValue[24] = '15'
        self.propertyValue[25] = String.format.format('%.7g', self.pctLoadLoss)
        self.propertyValue[26] = String.format.format('%.7g', self.pctNoLoadLoss)
        # defaults to zero
        self.propertyValue[27] = ''
        self.propertyValue[28] = ''
        self.propertyValue[29] = 'n'
        # =y/n
        self.propertyValue[30] = '1.10'
        self.propertyValue[31] = '0.90'
        self.propertyValue[32] = '32'
        self.propertyValue[33] = ''
        self.propertyValue[34] = '0'
        self.propertyValue[35] = '1'
        self.propertyValue[36] = ''
        super(TransformerObjImpl, self).initPropertyValues(Transformer.NumPropsThisClass)
        # override some inherited properties
        self.propertyValue[Transformer.NumPropsThisClass + 1] = '400'
        # normAmps  // TODO Check zero based indexing
        self.propertyValue[Transformer.NumPropsThisClass + 2] = '600'
        # emergAmps
        self.propertyValue[Transformer.NumPropsThisClass + 3] = '0.007'
        # faultRate
        self.propertyValue[Transformer.NumPropsThisClass + 4] = '100'
        # pctPerm
        self.propertyValue[Transformer.NumPropsThisClass + 5] = '36'
        # hrsToRepair
        self.clearPropSeqArray()
        # so the overrides don't show up on save

    def rotatePhases(self, iPhs):
        """For Delta connections or Line-Line voltages."""
        result = iPhs + self.deltaDirection
        # TODO Check zero based indexing
        # make sure result is within limits
        if self.nPhases > 2:
            # assumes 2 phase delta is open delta
            if result > self.nPhases:
                result = 1
            if result < 1:
                result = self.nPhases
        elif result < 1:
            result = 3
            # for 2-phase delta, next phase will be 3rd phase
        return result

    def makePosSequence(self):
        """Converts default 3-phase transformer model into equivalent positive-sequence
        using scripting.
        """
        n = MutableInt()
        nodes = [None] * 5
        # integer buffer
        # First, determine if we can convert this one.
        if (self.nPhases == 1) or (self.nPhases == 2):
            # disable if any terminal not connected to phase one
            _0 = True
            iW = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    iW += 1
                if not (iW < self.numWindings):
                    break
                onPhase1 = False
                # Load up auxiliary parser
                DSSGlobals.auxParser.setCmdString(self.getBus(iW))
                DSSGlobals.auxParser.getNextParam()
                s = DSSGlobals.auxParser.parseAsBusName(n, nodes)
                # TODO Check N gets set
                if n.intValue() == 0:
                    onPhase1 = True
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < n.intValue()):
                        break
                    if nodes[i] == 1:
                        onPhase1 = True
                if not onPhase1:
                    self.setEnabled(False)
                    # we won't use this one
                    return
        # Construct transformer definition string
        s = 'Phases=1  Conns=('
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            s = s + 'Wye '
        s = s + ')  buses=('
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            s = s + self.getBus(i) + ' '
        s = s + ')  kVS=('
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            W = self.winding[i]
            if (self.nPhases > 1) or (W.getConnection() != 0):
                s = s + String.format.format(' %-.5g', W.getKVLL() / DSSGlobals.SQRT3)
            else:
                s = s + String.format.format(' %-.5g', W.getKVLL())
        s = s + ')  kVAs=('
        _5 = True
        i = 0
        while True:
            if _5 is True:
                _5 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            W = self.winding[i]
            s = s + String.format.format(' %-.5g', W.getKVA() / self.nPhases)
        s = s + ')'
        s = s + ' NormHkVA=' + String.format.format(' %-.5g %-.5g', self.normMaxHKVA / self.nPhases, self.emergMaxHKVA / self.nPhases)
        Parser.getInstance().setCmdString(s)
        self.edit()
        super(TransformerObjImpl, self).makePosSequence()

    def addNeutralToY(self, freqMultiplier):
        value = None
        # Account for neutral impedances
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            W = self.winding[i]
            if W.getConnection() == 0:
                # handle wye, but ignore delta (and open wye)
                if W.getRNeut() >= 0:
                    # <0 is flag for open neutral (Ignore)
                    if W.getRNeut() == 0 and W.getXNeut() == 0:
                        # solidly grounded
                        value = Complex(1000000, 0)
                else:
                    # 1 microohm resistor
                    value = ComplexUtil.invert(Complex(W.getRNeut(), W.getXNeut() * freqMultiplier))
                j = i * self.nConds
                self.YPrimSeries.addElement(j, j, value)
            else:
                # bump up neutral admittance a bit in case neutral is floating
                j = i * self.nConds
                if self.ppmFloatFactor != 0.0:
                    self.YPrimSeries.setElement(j, j, self.YPrimSeries.getElement(j, j).add(Complex(0.0, W.getY_PPM())))
                    # YPrim_Series.setElement(j, j, CmulReal_im(GetElement(j, j), ppm_FloatFactorPlusOne));

    def buildYPrimComponent(self, YPrimComponent, YTerminal):
        # Now, put in Yprim matrix
        # Have to add every element of Y_terminal into Yprim somewhere
        nw2 = 2 * self.numWindings
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < nw2):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < i):
                    break
                value = YTerminal.getElement(i, j)
                # this value goes in Yprim nPhases times
                _2 = True
                k = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        k += 1
                    if not (k < self.nPhases - 1):
                        break
                    YPrimComponent.addElemSym(self.termRef[i + (k * nw2)], self.termRef[j + (k * nw2)], value)

    def getBasekVLL(self, i):
        return self.winding[i].getKVLL()

    def calcY_Terminal(self, freqMult):
        # FIXME Private method in OpenDSS
        # construct ZBMatrix
        self.ZB.clear()
        self.ZBase = 1.0 / self.VABase / self.nPhases
        # base ohms on 1.0 volt basis
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numWindings - 1):
                break
            # convert pu to ohms on one volt base as we go...
            self.ZB.setElement(i, i, Complex(self.winding[0].getRpu() + self.winding[i + 1].getRNeut(), freqMult * self.XSC[i]).multiply(self.ZBase))
            # off diagonals
        k = self.numWindings
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numWindings - 1):
                break
            _2 = True
            j = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    j += 1
                if not (j < self.numWindings - 1):
                    break
                self.ZB.setElemSym(i, j, self.ZB.getElement(i, i).add(self.ZB.getElement(j, j)).subtract(Complex(self.winding[i + 1].getRpu() + self.winding[j + 1].getRpu(), freqMult * self.XSC[k]).multiply(self.ZBase)).multiply(0.5))
            k += 1
        # ******************************DEBUG******************************************************
        # if (false) {
        # AssignFile(F, CircuitName_ + "Transformer_"+Name+".TXT");
        # Rewrite(F);
        # Writeln(F,"ZB before inverting...");
        # DumpComplexMatrix(F, ZB);
        # }
        # *****************************************************************************************
        self.ZB.invert()
        # mhos on one volt base
        if self.ZB.getErrorCode() > 0:
            DSSGlobals.doErrorMsg('TransformerObj.calcYPrim', 'Matrix inversion error for transformer \"' + self.getName() + '\"', 'Invalid impedance specified. Replaced with tiny conductance to ground.', 117)
            self.ZB.clear()
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < self.ZB.order()):
                    break
                self.ZB.setElement(i, i, Complex(DSSGlobals.EPSILON, 0.0))
        # ******************************DEBUG******************************************************
        # if (false) {
        # F.println("ZB after inverting...");
        # dumpComplexMatrix(F, ZB);
        # }
        # *****************************************************************************************
        # Now construct Y_Oneturn = AT * ZB.Invert * A
        # -1 1 0 ...
        # 		A = -1 0 1 ..   order:  N-1 x N   N = NumWindings
        # 			...
        # 								-1 -1 ...
        # 		AT = Transpose of A =  1  0 ...    N X N-1
        # 								0  1 ..

        self.Y_1Volt.clear()
        self.Y_1Volt_NL.clear()
        # Allocate temp complex arrays
        cTempArray1 = [None] * self.numWindings * 2
        cTempArray2 = [None] * self.numWindings * 2
        a = [None] * self.numWindings * 2
        cMinusOne = Complex(-1.0, 0.0)
        At = CMatrixImpl(self.numWindings)
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.numWindings - 1):
                break
            At.setElement(i + 1, i, Complex.ONE)
        _5 = True
        i = 0
        while True:
            if _5 is True:
                _5 = False
            else:
                i += 1
            if not (i < self.numWindings - 1):
                break
            At.setElement(1, i, cMinusOne)
        cTempArray1[self.numWindings] = Complex.ZERO
        _6 = True
        i = 0
        while True:
            if _6 is True:
                _6 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            if i == 1:
                _7 = True
                k = 0
                while True:
                    if _7 is True:
                        _7 = False
                    else:
                        k += 1
                    if not (k < self.numWindings - 1):
                        break
                    a[k] = cMinusOne
            else:
                _8 = True
                k = 0
                while True:
                    if _8 is True:
                        _8 = False
                    else:
                        k += 1
                    if not (k < self.numWindings - 1):
                        break
                    if k == i - 1:
                        a[k] = Complex.ONE
                    else:
                        a[k] = Complex.ZERO
            self.ZB.vMult(cTempArray1, a)
            # Zb.invert * A
            At.vMult(cTempArray2, cTempArray1)
            # AT * Result
            _9 = True
            j = 0
            while True:
                if _9 is True:
                    _9 = False
                else:
                    j += 1
                if not (j < self.numWindings):
                    break
                self.Y_1Volt.setElement(j, i, cTempArray2[j])
        # Add magnetizing reactance to 2nd winding, assuming it is closest to the core
        # Add both resistive element representing core losses and a reactive element representing
        # magnetizing current.

        self.Y_1Volt_NL.addElement(1, 1, Complex(self.pctNoLoadLoss / 100.0 / self.ZBase, -self.pctImag / 100.0 / self.ZBase))
        # ******************************DEBUG******************************************************
        # if (false) {
        # F.println("Y_OneVolt ...");
        # dumpComplexMatrix(F, Y_OneVolt);
        # }
        # *****************************************************************************************
        # should have admittance of one phase of the transformer on a one-volt, wye-connected base
        # Now make into terminal admittance matrix and correct for actual voltage ratings
        # Y_Terminal = AT * Y_onevolt * A  where V_onevolt = A * V_terminal
        At = None
        self.Y_Term.clear()
        self.Y_Term_NL.clear()
        At = CMatrixImpl(self.numWindings * 2)
        _10 = True
        i = 0
        while True:
            if _10 is True:
                _10 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            At.setElement((2 * i) - 1, i, Complex(1.0 / (self.winding[i].getVBase() * self.winding[i].getPUTap()), 0.0))
        _11 = True
        i = 0
        while True:
            if _11 is True:
                _11 = False
            else:
                i += 1
            if not (i < self.numWindings):
                break
            At.setElement(2 * i, i, Complex(-1.0 / (self.winding[i].getVBase() * self.winding[i].getPUTap()), 0.0))
        _12 = True
        i = 0
        while True:
            if _12 is True:
                _12 = False
            else:
                i += 1
            if not (i < 2 * self.numWindings):
                break
            cTempArray1[i] = Complex.ZERO
        _13 = True
        i = 0
        while True:
            if _13 is True:
                _13 = False
            else:
                i += 1
            if not (i < 2 * self.numWindings):
                break
            _14 = True
            k = 0
            while True:
                if _14 is True:
                    _14 = False
                else:
                    k += 1
                if not (k < self.numWindings):
                    break
                if i == (2 * k) - 1:
                    a[k] = Complex(1.0 / (self.winding[k].getVBase() * self.winding[k].getPUTap()), 0.0)
                elif i == 2 * k:
                    a[k] = Complex(-1.0 / (self.winding[k].getVBase() * self.winding[k].getPUTap()), 0.0)
                else:
                    a[k] = Complex.ZERO
            # Main transformer part
            self.Y_1Volt.vMult(cTempArray1, a)
            At.vMult(cTempArray2, cTempArray1)
            # AT * Result
            _15 = True
            j = 0
            while True:
                if _15 is True:
                    _15 = False
                else:
                    j += 1
                if not (j < 2 * self.numWindings):
                    break
                self.Y_Term.setElement(j, i, cTempArray2[j])
                # No load part
            self.Y_1Volt_NL.vMult(cTempArray1, a)
            At.vMult(cTempArray2, cTempArray1)
            # AT * Result
            _16 = True
            j = 0
            while True:
                if _16 is True:
                    _16 = False
                else:
                    j += 1
                if not (j < 2 * self.numWindings):
                    break
                self.Y_Term_NL.setElement(j, i, cTempArray2[j])
        # ******************************DEBUG******************************************************
        # if (false) {
        # F.println("Y_Terminal before adding small element to diagonals ...");
        # dumpComplexMatrix(F, Y_Terminal);
        # }
        # *****************************************************************************************
        # Add a small admittance to the first conductor of each winding so that
        # the matrix will always invert even if the user neglects to define a voltage
        # reference on all sides

        if self.ppmFloatFactor != 0.0:
            _17 = True
            i = 0
            while True:
                if _17 is True:
                    _17 = False
                else:
                    i += 1
                if not (i < self.numWindings):
                    break
                j = (2 * i) - 1
                self.Y_Term.setElement(j, j, self.Y_Term.getElement(j, j).add(Complex(0.0, self.winding[i].getY_PPM())))
                # SetElement(j, j, CmulReal_im(GetElement(j, j) , ppm_FloatFactorPlusOne));
        # ******************************DEBUG******************************************************
        # if (false) {
        # F.println("Y_Terminal after adding small element to diagonals ...");
        # dumpComplexMatrix(F, Y_Terminal);
        # F.close();
        # }
        # *****************************************************************************************
        At = None
        a = None
        cTempArray1 = None
        cTempArray2 = None
        self.Y_Terminal_FreqMult = freqMult

    def fetchXfmrCode(self, code):
        if TransformerImpl.XfmrCodeClass is None:
            TransformerImpl.XfmrCodeClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('xfmrcode'))
        if TransformerImpl.XfmrCodeClass.setActive(code):
            obj = TransformerImpl.XfmrCodeClass.getActiveObj()
            self.XfmrCode = code.toLowerCase()
            # set sizes and copy parameters
            self.setNPhases(obj.getNPhases())
            self.setNumWindings(obj.getNumWindings())
            self.setNConds(self.nPhases + 1)
            # forces reallocation of terminals and conductors
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.numWindings):
                    break
                W = self.winding[i]
                W.setConnection(obj.getWinding()[i].getConnection())
                W.setKVLL(obj.getWinding()[i].getKVLL())
                W.setVBase(obj.getWinding()[i].getVBase())
                W.setKVA(obj.getWinding()[i].getKVA())
                W.setPUTap(obj.getWinding()[i].getPUTap())
                W.setRpu(obj.getWinding()[i].getRpu())
                W.setRNeut(obj.getWinding()[i].getRNeut())
                W.setXNeut(obj.getWinding()[i].getXNeut())
                W.setTapIncrement(obj.getWinding()[i].getTapIncrement())
                W.setMinTap(obj.getWinding()[i].getMinTap())
                W.setMaxTap(obj.getWinding()[i].getMaxTap())
                W.setNumTaps(obj.getWinding()[i].getNumTaps())
            self.setTermRef()
            self.XHL = obj.getXHL()
            self.XHT = obj.getXHT()
            self.XLT = obj.getXLT()
            _1 = True
            i = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < (self.numWindings * (self.numWindings - 1)) / 2):
                    break
                self.XSC[i] = obj.getXSC()[i]
            self.thermalTimeConst = obj.getThermalTimeConst()
            self.nThermal = obj.getNThermal()
            self.mThermal = obj.getMThermal()
            self.FLRise = obj.getLRise()
            self.HSRise = obj.getHSRise()
            self.pctLoadLoss = obj.getPctLoadLoss()
            self.pctNoLoadLoss = obj.getPctNoLoadLoss()
            self.setNormMaxHKVA(obj.getNormMaxHKVA())
            self.setEmergMaxHKVA(obj.getEmergMaxHKVA())
            self.YOrder = self.nConds * self.nTerms
            self.setYPrimInvalid(True)
            self.Y_Terminal_FreqMult = 0.0
            self.recalcElementData()
        else:
            DSSGlobals.doSimpleMsg('Xfmr Code:' + code + ' not found.', 180)

    def getPresentTap(self, i):
        # CIM accessors
        return 0.0

    def getNumWindings(self):
        return self.numWindings

    def getActiveWinding(self):
        return self.activeWinding

    def setActiveWinding(self, winding):
        self.activeWinding = winding

    def setSubstation(self, value):
        self.isSubstation = value

    def getSubstationName(self):
        return self.substationName

    def setSubstationName(self, name):
        self.substationName = name

    def getWinding(self):
        return self.winding

    def setWinding(self, values):
        self.winding = values

    def getXfmrBank(self):
        return self.XfmrBank

    def setXfmrBank(self, bank):
        self.XfmrBank = bank

    def getXfmrCode(self):
        return self.XfmrCode

    def setXfmrCode(self, code):
        self.XfmrCode = code

    def getPPM_FloatFactor(self):
        return self.ppmFloatFactor

    def getPctImag(self):
        return self.pctImag

    def getXHL(self):
        return self.XHL

    def getXHT(self):
        return self.XHT

    def getXLT(self):
        return self.XLT

    def getBaseVA(self):
        return self.VABase

    def getNormMaxHKVA(self):
        return self.normMaxHKVA

    def getEmergMaxHKVA(self):
        return self.emergMaxHKVA

    def getThTau(self):
        return self.thermalTimeConst

    def getThN(self):
        return self.nThermal

    def getThM(self):
        return self.mThermal

    def getFLRise(self):
        return self.FLRise

    def getHSRise(self):
        return self.HSRise

    def getPctLoadLoss(self):
        return self.pctLoadLoss

    def getPctNoLoadLoss(self):
        # FIXME Private memebers in OpenDSS
        return self.pctNoLoadLoss

    def getDeltaDirection(self):
        return self.deltaDirection

    def setDeltaDirection(self, direction):
        self.deltaDirection = direction

    def getMaxWindings(self):
        return self.maxWindings

    def setMaxWindings(self, max):
        self.maxWindings = max

    def getTermRef(self):
        return self.termRef

    def getZBase(self):
        return self.ZBase

    def setZBase(self, zbase):
        self.ZBase = zbase

    def getXSC(self):
        return self.XSC

    def setXSC(self, xsc):
        self.XSC = xsc

    def getVABase(self):
        return self.VABase

    def setVABase(self, base):
        self.VABase = base

    def getZB(self):
        return self.ZB

    def setZB(self, zb):
        self.ZB = zb

    def getY_1Volt(self):
        return self.Y_1Volt

    def setY_1Volt(self, value):
        self.Y_1Volt = value

    def getY_Term(self):
        return self.Y_Term

    def setY_Term(self, value):
        self.Y_Term = value

    def getY_1Volt_NL(self):
        return self.Y_1Volt_NL

    def setY1VoltNL(self, value):
        self.Y_1Volt_NL = value

    def getYTermNL(self):
        return self.Y_Term_NL

    def setYTermNL(self, value):
        self.Y_Term_NL = value

    def getYTerminalFreqMult(self):
        return self.Y_Terminal_FreqMult

    def setYTerminalFreqMult(self, mult):
        self.Y_Terminal_FreqMult = mult

    def getThermalTimeConst(self):
        return self.thermalTimeConst

    def setThermalTimeConst(self, timeConst):
        self.thermalTimeConst = timeConst

    def getNThermal(self):
        return self.nThermal

    def setNThermal(self, value):
        self.nThermal = value

    def getMThermal(self):
        return self.mThermal

    def setMThermal(self, value):
        self.mThermal = value

    def isXHLChanged(self):
        return self.XHLChanged

    def setXHLChanged(self, changed):
        self.XHLChanged = changed

    def isSubstation(self):
        return self.isSubstation

    def setPPM_FloatFactor(self, factor):
        self.ppmFloatFactor = factor

    def setPctImag(self, pct):
        self.pctImag = pct

    def setXHL(self, value):
        self.XHL = value

    def setXHT(self, value):
        self.XHT = value

    def setXLT(self, value):
        self.XLT = value

    def setNormMaxHKVA(self, max):
        self.normMaxHKVA = max

    def setEmergMaxHKVA(self, max):
        self.emergMaxHKVA = max

    def setFLRise(self, rise):
        self.FLRise = rise

    def setHSRise(self, rise):
        self.HSRise = rise

    def setPctLoadLoss(self, pct):
        self.pctLoadLoss = pct

    def setPctNoLoadLoss(self, pct):
        self.pctNoLoadLoss = pct
