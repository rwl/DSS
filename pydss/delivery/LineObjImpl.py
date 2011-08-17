from dss.delivery.impl.LineImpl import (LineImpl,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.common.impl.Utilities import (Utilities,)
from dss.delivery.impl.PDElementImpl import (PDElementImpl,)
from dss.general.impl.ConductorDataImpl import (ConductorDataImpl,)
from dss.delivery.LineObj import (LineObj,)
from dss.general.impl.LineGeometryObjImpl import (LineGeometryObjImpl,)
from dss.general.impl.ConductorChoice import (ConductorChoice,)
from dss.parser.impl.Parser import (Parser,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.delivery.Line import (Line,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LineObjImpl(PDElementImpl, LineObj):
    # keep track of last frequency computed for geometry
    ZFrequency = None
    lineCodeUnits = None
    unitsConvert = None
    # conversion factor
    lineGeometryObj = None
    lineSpacingObj = None
    wireData = None
    phaseChoice = None
    rhoSpecified = None
    lineCodeSpecified = None
    earthModel = None
    capSpecified = None
    # to make sure user specifies C in some form
    ZInv = None
    # Base frequency series Z matrix per unit length
    Z = None
    Yc = None
    R1 = None
    X1 = None
    R0 = None
    X0 = None
    C1 = None
    C0 = None
    len = None
    lengthUnits = None
    Rg = None
    Xg = None
    KXg = None
    rho = None
    generalPlotQuantity = None
    # for general circuit plotting
    condCode = None
    geometryCode = None
    spacingCode = None
    geometrySpecified = None
    spacingSpecified = None
    symComponentsChanged = None
    symComponentsModel = None
    isSwitch = None

    def __init__(self, parClass, lineName):
        super(LineObjImpl, self)(parClass)
        self.setName(lineName.toLowerCase())
        self.objType = parClass.getDSSClassType()
        # DSSObjType + LINESECTION; // in both PD element list and line section lists
        self.setNPhases(3)
        # directly set conds and phases
        self.nConds = 3
        self.setNTerms(2)
        # force allocation of terminals and conductors
        self.isSwitch = False
        self.R1 = 0.058
        # ohms per 1000 ft
        self.X1 = 0.1206
        self.R0 = 0.1784
        self.X0 = 0.4047
        self.C1 = 3.3999999999999995e-09
        # nf per 1000ft
        self.C0 = 1.5999999999999999e-09
        self.len = 1.0
        # 1 kFt
        self.Z = None
        self.ZInv = None
        self.Yc = None
        self.condCode = ''
        self.Rg = 0.01805
        # ohms per 1000 ft
        self.Xg = 0.155081
        self.rho = 100.0
        self.KXg = self.Xg / self.Math.log(658.5 * self.Math.sqrt(self.rho / self.baseFrequency))
        self.rhoSpecified = False
        self.capSpecified = False
        # baseFrequency = 60.0;
        # set in base class
        self.normAmps = 400.0
        self.emergAmps = 600.0
        self.pctPerm = 20.0
        self.faultRate = 0.1
        self.hrsToRepair = 3.0
        self.symComponentsChanged = False
        self.symComponentsModel = True
        self.geometrySpecified = False
        self.geometryCode = ''
        self.lengthUnits = LineUnits.UNITS_NONE
        # assume everything matches
        self.unitsConvert = 1.0
        self.lineCodeUnits = LineUnits.UNITS_NONE
        self.lineCodeSpecified = False
        self.earthModel = DSSGlobals.defaultEarthModel
        self.spacingSpecified = False
        self.lineSpacingObj = None
        self.wireData = None
        self.phaseChoice = ConductorChoice.UNKNOWN
        self.spacingCode = ''
        self.ZFrequency = -1.0
        # indicate Z not computed.
        self.lineGeometryObj = None
        self.initPropertyValues(0)
        self.YOrder = self.nTerms * self.nConds
        self.recalcElementData()

    def fetchLineCode(self, code):
        if LineImpl.lineCodeClass is None:
            LineImpl.lineCodeClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('linecode'))
        if LineImpl.lineCodeClass.setActive(code):
            lc = LineImpl.lineCodeClass.getActiveObj()
            self.condCode = code.toLowerCase()
            # frequency compensation takes place in calcYPrim
            self.baseFrequency = lc.getBaseFrequency()
            # Copy impedances from line code, but do not recalc because symmetrical
            # component z's may not match what's in matrix

            if lc.isSymComponentsModel():
                self.R1 = lc.getR1()
                self.X1 = lc.getX1()
                self.R0 = lc.getR0()
                self.X0 = lc.getX0()
                self.C1 = lc.getC1()
                self.C0 = lc.getC0()
                self.symComponentsModel = True
            else:
                self.symComponentsModel = False
            # earth return impedances used to compensate for frequency
            self.Rg = lc.getRg()
            self.Xg = lc.getXg()
            self.rho = lc.getRho()
            self.KXg = self.Xg / self.Math.log(658.5 * self.Math.sqrt(self.rho / self.baseFrequency))
            self.lineCodeUnits = lc.getUnits()
            self.lineCodeSpecified = True
            self.unitsConvert = LineUnits.convertLineUnits(self.lineCodeUnits, self.lengthUnits)
            self.setNormAmps(lc.getNormAmps())
            self.setEmergAmps(lc.getEmergAmps())
            self.setFaultRate(lc.getFaultRate())
            self.setPctPerm(lc.getPctPerm())
            self.setHrsToRepair(lc.getHrsToRepair())
            self.updatePDProperties()
            if self.getNPhases() != lc.getNPhases():
                if self.Z is not None:
                    self.Z = None
                if self.ZInv is not None:
                    self.ZInv = None
                if self.Yc is not None:
                    self.Yc = None
                self.setNPhases(lc.getNPhases())
                # for a line, nPhases = nCond, for now
                self.Z = CMatrixImpl(self.nPhases)
                self.ZInv = CMatrixImpl(self.nPhases)
                self.Yc = CMatrixImpl(self.nPhases)
            if not self.symComponentsModel:
                # copy matrices
                self.Z.copyFrom(lc.getZ())
                # Zinv.copyFrom(lc.getZinv());
                # no need to copy Zinv
                self.Yc.copyFrom(lc.getYC())
            else:
                self.recalcElementData()
                # compute matrices
            self.setNConds(self.nPhases)
            # force reallocation of terminal info
            self.YOrder = self.nConds * self.nTerms
            # setYprimInvalid(true);  // set in edit; this is redundant
        else:
            DSSGlobals.doSimpleMsg('Line Code:' + code + ' not found.', 180)

    def recalcElementData(self):
        ckt = DSSGlobals.activeCircuit
        if self.Z is not None:
            self.Z = None
        if self.ZInv is not None:
            self.ZInv = None
        if self.Yc is not None:
            self.Yc = None
            # for a line, nPhases = nCond, for now
        self.Z = CMatrixImpl(self.getNPhases())
        self.ZInv = CMatrixImpl(self.getNPhases())
        self.Yc = CMatrixImpl(self.getNPhases())
        oneThird = 1.0 / 3.0
        # do this to get more precision in next few statements
        # Only time this is called is if symmetrical components are specified
        ZTemp = Complex(self.R1, self.X1).multiply(2.0)
        # Handle special case for 1-phase line and/or pos seq model
        if (self.getNPhases() == 1) or ckt.isPositiveSequence():
            # long-line equivalent pi, but only for cktModel=positive
            if ckt.isPositiveSequence() and self.C1 > 0:
                # nominal pi parameters per unit length
                Zs = Complex(self.R1, self.X1)
                Ys = Complex(0.0, DSSGlobals.TWO_PI * self.baseFrequency * self.C1)
                # apply the long-line correction to obtain Zm and Ym
                gammaL = Zs.multiply(Ys).sqrt()
                gammaL = gammaL.multiply(self.len)
                expP = Complex(self.Math.cos(gammaL.getImaginary()), self.Math.sin(gammaL.getImaginary())).multiply(self.Math.exp(gammaL.getReal()))
                exp2P = Complex(self.Math.cos(0.5 * gammaL.getImaginary()), self.Math.sin(0.5 * gammaL.getImaginary())).multiply(self.Math.exp(0.5 * gammaL.getReal()))
                expM = ComplexUtil.invert(expP)
                exp2M = ComplexUtil.invert(exp2P)
                sinhGL = expP.subtract(expM).multiply(0.5)
                tanh2GL = exp2P.subtract(exp2M).divide(exp2P.add(exp2M))
                Zm = Zs.multiply(self.len).multiply(sinhGL).divide(gammaL)
                Ym = Ys.multiply(self.len).multiply(tanh2GL).divide(gammaL.multiply(0.5))
                # rely on this function being called only once, unless r1, x1, or c1 changes
                self.R1 = Zm.getReal() / self.len
                self.X1 = Zm.getImaginary() / self.len
                self.C1 = Ym.getImaginary() / self.len / DSSGlobals.TWO_PI / self.baseFrequency
            # zero sequence the same as positive sequence
            self.R0 = self.R1
            self.X0 = self.X1
            self.C0 = self.C1
        Zs = ZTemp.add(Complex(self.R0, self.X0)).multiply(oneThird)
        Zm = Complex(self.R0, self.X0).subtract(Complex(self.R1, self.X1)).multiply(oneThird)
        Yc1 = DSSGlobals.TWO_PI * self.baseFrequency * self.C1
        Yc0 = DSSGlobals.TWO_PI * self.baseFrequency * self.C0
        Ys = Complex(0.0, Yc1).multiply(2.0).add(Complex(0.0, Yc0)).multiply(oneThird)
        Ym = Complex(0.0, Yc0).subtract(Complex(0.0, Yc1)).multiply(oneThird)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            self.Z.setElement(i, i, Zs)
            self.Yc.setElement(i, i, Ys)
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < i):
                    break
                # TODO Check zero based indexing
                self.Z.setElemSym(i, j, Zm)
                self.Yc.setElemSym(i, j, Ym)
        self.symComponentsChanged = False

    def calcYPrim(self):
        norder = MutableInt()
        freqMultiplier = 1.0
        lengthMultiplier = 1.0
        ckt = DSSGlobals.activeCircuit
        if self.symComponentsChanged:
            # Try to catch inadvertent user error when they forget to specify C1 and C0
            # Check to see if user has spec'd C1 and C0. If not, adjust default values for new length units
            if not self.capSpecified:
                self.C1 = self.C1 / LineUnits.convertLineUnits(LineUnits.UNITS_KFT, self.lengthUnits)
                # were defined in kft
                self.C0 = self.C0 / LineUnits.convertLineUnits(LineUnits.UNITS_KFT, self.lengthUnits)
                self.capSpecified = True
                # so we don't do it again
            self.recalcElementData()
        self.clearYPrim()
        # build series YPrim
        # CMatrix Y = YPrim_Series;
        # Build Z matrix
        if self.geometrySpecified:
            self.makeZFromGeometry(ckt.getSolution().getFrequency())
            # includes length in proper units
            if DSSGlobals.solutionAbort:
                return
        elif self.spacingSpecified:
            self.makeZFromSpacing(ckt.getSolution().getFrequency())
            if DSSGlobals.solutionAbort:
                return
        else:
            # Z is from line code or specified in line data
            lengthMultiplier = self.len / self.unitsConvert
            # convert to per unit length
            self.YPrimFreq = ckt.getSolution().getFrequency()
            freqMultiplier = self.YPrimFreq / self.baseFrequency
            # Put in series RL
            ZValues = self.Z.asArray(norder)
            ZInvValues = self.ZInv.asArray(norder)
            # Correct the impedances for length and frequency
            # Rg increases with frequency
            # Xg modified by ln of sqrt(1/f)
            if self.Xg != 0.0:
                XgMod = 0.5 * self.KXg * self.Math.log(freqMultiplier)
            else:
                XgMod = 0.0
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < norder.intValue() * norder.intValue()):
                    break
                ZInvValues[i] = Complex((ZValues[i].getReal() + (self.Rg * (freqMultiplier - 1.0))) * lengthMultiplier, (ZValues[i].getImaginary() - XgMod) * lengthMultiplier * freqMultiplier)
            self.ZInv.invert()
            # Invert in place
            if self.ZInv.getErrorCode() > 0:
                # If error, put in tiny series conductance
                # TEMc - shut this up for the CDPSM connectivity profile test, or whenever else it gets annoying
                DSSGlobals.doErrorMsg('LineObj.calcYPrim', 'Matrix inversion error for line \"' + self.getName() + '\"', 'Invalid impedance specified. Replaced with tiny conductance.', 183)
                self.ZInv.clear()
                _1 = True
                i = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        i += 1
                    if not (i < self.getNPhases()):
                        break
                    self.ZInv.setElement(i, i, Complex(DSSGlobals.EPSILON, 0.0))
            else:
                # Now, put in Yprim_Series matrix
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.getNPhases()):
                        break
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < self.getNPhases()):
                            break
                        value = self.ZInv.getElement(i, j)
                        self.YPrimSeries.setElement(i, j, value)
                        self.YPrimSeries.setElement(i + self.getNPhases(), j + self.getNPhases(), value)
                        value = value.negate()
                        self.YPrimSeries.setElemSym(i, j + self.getNPhases(), value)
            self.YPrim.copyFrom(self.YPrimSeries)
            # initialize YPrim for series impedances
            # 10/3/2006 moved this to after the copy to Yprim so it doesn't affect normal line model capacitance
            # 3-30-04  ----- Rev 2-4-09 to include both sides of line
            # increase diagonal elements of both sides of line so that we will avoid isolated bus problem
            # add equivalent of 10 kvar capacitive at 345 kV
            _4 = True
            i = 0
            while True:
                if _4 is True:
                    _4 = False
                else:
                    i += 1
                if not (i < self.YOrder):
                    break
                self.YPrimSeries.addElement(i, i, Line.CAP_EPSILON)
        # now build the shunt admittances and add into YPrim
        # Put half the shunt capacitive admittance at each end
        YValues = self.Yc.asArray(norder)
        if self.geometrySpecified or self.spacingSpecified:
            # Values are already compensated for length and frequency
            k = -1
            # TODO Check zero based indexing
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < self.getNPhases()):
                    break
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < self.getNPhases()):
                        break
                    k += 1
                    # assume matrix in col order (1,1  2,1  3,1 ...)
                    value = ComplexUtil.divide(YValues[k], 2.0)
                    # half at each end ...
                    self.YPrimShunt.addElement(i, j, value)
                    self.YPrimShunt.addElement(i + self.getNPhases(), j + self.getNPhases(), value)
        else:
            # Regular line model - values computed per unit length at base frequency
            k = -1
            # TODO Check zero based indexing
            _7 = True
            j = 0
            while True:
                if _7 is True:
                    _7 = False
                else:
                    j += 1
                if not (j < self.getNPhases()):
                    break
                _8 = True
                i = 0
                while True:
                    if _8 is True:
                        _8 = False
                    else:
                        i += 1
                    if not (i < self.getNPhases()):
                        break
                    k += 1
                    # assume matrix in col order (1,1  2,1  3,1 ...)
                    value = Complex(0.0, (YValues[k].getImaginary() * lengthMultiplier * freqMultiplier) / 2.0)
                    self.YPrimShunt.addElement(i, j, value)
                    self.YPrimShunt.addElement(i + self.getNPhases(), j + self.getNPhases(), value)
        # Now account for open conductors
        # For any conductor that is open, zero out row and column
        self.YPrim.addFrom(self.YPrimShunt)
        super(LineObjImpl, self).calcYPrim()
        self.setYPrimInvalid(False)

    def dumpProperties(self, f, complete):
        super(LineObjImpl, self).dumpProperties(f, complete)
        pc = self.getParentClass()
        print '~ ' + pc.getPropertyName()[0] + '=' + self.getFirstBus()
        print '~ ' + pc.getPropertyName()[1] + '=' + self.getNextBus()
        print '~ ' + pc.getPropertyName()[2] + '=' + self.getCondCode()
        print '~ ' + pc.getPropertyName()[3] + '=' + self.getLen()
        print '~ ' + pc.getPropertyName()[4] + '=' + self.getNPhases()
        print '~ ' + pc.getPropertyName()[5] + '=' + self.getR1()
        print '~ ' + pc.getPropertyName()[6] + '=' + self.getX1()
        print '~ ' + pc.getPropertyName()[7] + '=' + self.getR0()
        print '~ ' + pc.getPropertyName()[8] + '=' + self.getX0()
        print '~ ' + pc.getPropertyName()[9] + '=' + (self.getC1() * 1000000000.0)
        print '~ ' + pc.getPropertyName()[10] + '=' + (self.getC0() * 1000000000.0)
        f.print_('~ ' + pc.getPropertyName()[11] + '=' + '\"')
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.getNPhases()):
                    break
                f.print_(self.Z.getElement(i, j).getReal() + ' ')
            f.print_('|')
        print '\"'
        f.print_('~ ' + pc.getPropertyName()[12] + '=' + '\"')
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            _3 = True
            j = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    j += 1
                if not (j < self.getNPhases()):
                    break
                f.print_(self.Z.getElement(i, j).getImaginary() + ' ')
            f.print_('|')
        print '\"'
        f.print_('~ ' + pc.getPropertyName()[13] + '=' + '\"')
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < self.getNPhases()):
                    break
                f.print_(((self.Yc.getElement(i, j).getImaginary() / DSSGlobals.TWO_PI / self.baseFrequency) * 1000000000.0) + ' ')
            f.print_('|')
        print '\"'
        f.print_('~ ' + pc.getPropertyName()[13] + '=')
        if self.isSwitch:
            print 'true'
        else:
            print 'false'
        # Dump the rest by default
        _6 = True
        i = 14
        while True:
            if _6 is True:
                _6 = False
            else:
                i += 1
            if not (i < pc.getNumProperties()):
                break
            # TODO Check zero based indexing
            print '~ ' + pc.getPropertyName()[i] + '=' + self.getPropertyValue(i)

    def getLosses(self, totalLosses, loadLosses, noLoadLosses):
        """Placeholder for Line module no load loss procedure."""
        # for now, we'll just do the default behaviour until we implement shunt losses
        super(LineObjImpl, self).getLosses(totalLosses, loadLosses, noLoadLosses)

    def getPropertyValue(self, index):
        if index >= 11 and index <= 13:
            result = '['
        else:
            result = ''
        # Report impedance values in ohms per unit length of present length units
        _0 = index
        _1 = False
        while True:
            if _0 == 0:
                _1 = True
                result = self.getBus(0)
                break
            if (_1 is True) or (_0 == 1):
                _1 = True
                result = self.getBus(1)
                break
            if (_1 is True) or (_0 == 3):
                _1 = True
                result = String.format.format('%-.7g', self.len)
                break
            if (_1 is True) or (_0 == 4):
                _1 = True
                result = String.format.format('%d', self.nPhases)
                break
            if (_1 is True) or (_0 == 5):
                _1 = True
                if self.symComponentsModel:
                    result = String.format.format('%-.7g', self.R1 / self.unitsConvert)
                else:
                    result = '----'
                break
            if (_1 is True) or (_0 == 6):
                _1 = True
                if self.symComponentsModel:
                    result = String.format.format('%-.7g', self.X1 / self.unitsConvert)
                else:
                    result = '----'
                break
            if (_1 is True) or (_0 == 7):
                _1 = True
                if self.symComponentsModel:
                    result = String.format.format('%-.7g', self.R0 / self.unitsConvert)
                else:
                    result = '----'
                break
            if (_1 is True) or (_0 == 8):
                _1 = True
                if self.symComponentsModel:
                    result = String.format.format('%-.7g', self.X0 / self.unitsConvert)
                else:
                    result = '----'
                break
            if (_1 is True) or (_0 == 9):
                _1 = True
                if self.symComponentsModel:
                    result = String.format.format('%-.7g', (self.C1 * 1000000000.0) / self.unitsConvert)
                else:
                    result = '----'
                break
            if (_1 is True) or (_0 == 10):
                _1 = True
                if self.symComponentsModel:
                    result = String.format.format('%-.7g', (self.C0 * 1000000000.0) / self.unitsConvert)
                else:
                    result = '----'
                break
            if (_1 is True) or (_0 == 11):
                _1 = True
                _2 = True
                i = 0
                while True:
                    if _2 is True:
                        _2 = False
                    else:
                        i += 1
                    if not (i < self.getNConds()):
                        break
                    _3 = True
                    j = 0
                    while True:
                        if _3 is True:
                            _3 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        # report in per unit length in length units
                        if self.geometrySpecified or self.spacingSpecified:
                            result = result + String.format.format('%-.7g', self.Z.getElement(i, j).getReal() / self.len) + ' '
                        else:
                            result = result + String.format.format('%-.7g', self.Z.getElement(i, j).getReal() / self.unitsConvert) + ' '
                        if i < self.getNConds():
                            result = result + '|'
                break
            if (_1 is True) or (_0 == 12):
                _1 = True
                _4 = True
                i = 0
                while True:
                    if _4 is True:
                        _4 = False
                    else:
                        i += 1
                    if not (i < self.getNConds()):
                        break
                    _5 = True
                    j = 0
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        # X matrix
                        if self.geometrySpecified or self.spacingSpecified:
                            result = result + String.format.format('%-.7g', self.Z.getElement(i, j).getImaginary() / self.len) + ' '
                        else:
                            result = result + String.format.format('%-.7g', self.Z.getElement(i, j).getImaginary() / self.unitsConvert) + ' '
                    if i < self.nConds:
                        result = result + '|'
                break
            if (_1 is True) or (_0 == 13):
                _1 = True
                factor = DSSGlobals.TWO_PI * self.baseFrequency * 1e-09
                _6 = True
                i = 0
                while True:
                    if _6 is True:
                        _6 = False
                    else:
                        i += 1
                    if not (i < self.nConds):
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
                        if self.geometrySpecified or self.spacingSpecified:
                            result = result + String.format.format('%-.7g', self.Yc.getElement(i, j).getImaginary() / factor / self.len) + ' '
                        else:
                            result = result + String.format.format('%-.7g', self.Yc.getElement(i, j).getImaginary() / factor / self.unitsConvert) + ' '
                    if i < self.nConds:
                        result = result + '|'
                break
            if (_1 is True) or (_0 == 14):
                _1 = True
                if self.isSwitch:
                    result = 'True'
                else:
                    result = 'False'
                break
            if (_1 is True) or (_0 == 15):
                _1 = True
                result = String.format.format('%-g', self.Rg)
                break
            if (_1 is True) or (_0 == 16):
                _1 = True
                result = String.format.format('%-g', self.Xg)
                break
            if (_1 is True) or (_0 == 17):
                _1 = True
                result = String.format.format('%-g', self.rho)
                break
            if (_1 is True) or (_0 == 22):
                _1 = True
                result = Utilities.getEarthModel(self.earthModel)
                break
            if True:
                _1 = True
                result = super(LineObjImpl, self).getPropertyValue(index)
                break
            break
        if index >= 11 and index <= 13:
            result = result + '['
        return result

    def getSeqLosses(self, posSeqLosses, negSeqLosses, zeroSeqLosses):
        """Only consider 3-phase branches with pos seq >> neg seq
        Otherwise, we don't know whether it is a 3-phase line or just a line with 3 phases
        """
        Vph = [None] * 2
        V012 = [None] * 2
        I012 = [None] * 2
        posSeqLosses = Complex.ZERO
        negSeqLosses = Complex.ZERO
        zeroSeqLosses = Complex.ZERO
        # Method: sum seq powers going into each terminal
        if self.nPhases == 3:
            # 3-phase lines only
            self.computeITerminal()
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < 2):
                    break
                k = ((i - 1) * self.nPhases) + 1
                # TODO Check zero based indexing
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < 2):
                        break
                    Vph[j] = DSSGlobals.activeCircuit.getSolution().getNodeV()[self.nodeRef[k + j]]
                MathUtil.phase2SymComp(Vph, V012)
                MathUtil.phase2SymComp(self.ITerminal[k], I012)
                posSeqLosses = posSeqLosses.add(V012[1].multiply(I012[1].conjugate()))
                # TODO Check zero based indexing
                negSeqLosses = negSeqLosses.add(V012[2].multiply(I012[2].conjugate()))
                # accumulate both line modes
                zeroSeqLosses = zeroSeqLosses.add(V012[0].multiply(I012[0].conjugate()))
            posSeqLosses = posSeqLosses.multiply(3.0)
            negSeqLosses = negSeqLosses.multiply(3.0)
            zeroSeqLosses = zeroSeqLosses.multiply(3.0)

    def initPropertyValues(self, arrayOffset):
        self.propertyValue[0] = self.getBus(0)
        # TODO Check zero based indexing
        self.propertyValue[1] = self.getBus(1)
        self.propertyValue[2] = ''
        self.propertyValue[3] = '1.0'
        # "5.28"; Changed 2/17/00
        self.propertyValue[4] = '3'
        self.propertyValue[5] = '.058'
        self.propertyValue[6] = '.1206'
        self.propertyValue[7] = '.1784'
        self.propertyValue[8] = '.4047'
        self.propertyValue[9] = '3.4'
        self.propertyValue[10] = '1.6'
        self.propertyValue[11] = ''
        self.propertyValue[12] = ''
        self.propertyValue[13] = ''
        self.propertyValue[14] = 'false'
        self.propertyValue[15] = '0.01805'
        self.propertyValue[16] = '0.155081'
        self.propertyValue[17] = '100'
        self.propertyValue[18] = ''
        self.propertyValue[19] = 'NONE'
        self.propertyValue[20] = ''
        self.propertyValue[21] = ''
        self.propertyValue[22] = Utilities.getEarthModel(DSSGlobals.SIMPLECARSON)
        super(LineObjImpl, self).initPropertyValues(Line.NumPropsThisClass)
        # override inherited properties just in case
        self.propertyValue[Line.NumPropsThisClass + 1] = '400'
        # normAmps    // TODO Check zero based indexing
        self.propertyValue[Line.NumPropsThisClass + 2] = '600'
        # emergAmps
        self.propertyValue[Line.NumPropsThisClass + 3] = '0.1'
        # faultRate
        self.propertyValue[Line.NumPropsThisClass + 4] = '20'
        # pctPerm
        self.propertyValue[Line.NumPropsThisClass + 5] = '3'
        # hrsToRepair
        self.clearPropSeqArray()

    def makePosSequence(self):
        # set to single phase and make sure r1, x1, c1 set.
        # if already single phase, let alone
        if self.nPhases > 1:
            # kill certain propertyValue elements to get a cleaner looking save
            self.prpSequence[2] = 0
            # TODO Check zero based indexing
            _0 = True
            i = 5
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < 13):
                    break
                self.prpSequence[i] = 0
            if self.isSwitch:
                s = ' R1=1 X1=1 C1=1.1 Phases=1 Len=0.001'
            else:
                if self.symComponentsModel:
                    # keep the same Z1 and C1
                    Z1 = Complex(self.R1, self.X1)
                    C1New = self.C1 * 1000000000.0
                    # convert to nF
                else:
                    # matrix was input directly, or built from physical data
                    # average the diagonal and off-dialgonal elements
                    ZS = Complex.ZERO
                    _1 = True
                    i = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        ZS = ZS.add(self.Z.getElement(i, i))
                    ZS = ComplexUtil.divide(ZS, self.nPhases)
                    Zm = Complex.ZERO
                    _2 = True
                    i = 0
                    while True:
                        if _2 is True:
                            _2 = False
                        else:
                            i += 1
                        if not (i < self.nPhases - 1):
                            break
                        # TODO Check zero based indexing
                        _3 = True
                        j = i + 1
                        while True:
                            if _3 is True:
                                _3 = False
                            else:
                                j += 1
                            if not (j < self.nPhases):
                                break
                            Zm = Zm.add(self.Z.getElement(i, j))
                    Zm = ComplexUtil.divide(Zm, (self.nPhases * (self.nPhases - 1.0)) / 2.0)
                    Z1 = ZS.subtract(Zm)
                    # do same for capacitances
                    Cs = 0.0
                    _4 = True
                    i = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        Cs = Cs + self.Yc.getElement(i, i).getImaginary()
                    Cm = 0.0
                    _5 = True
                    i = 1
                    while True:
                        if _5 is True:
                            _5 = False
                        else:
                            i += 1
                        if not (i < self.nPhases):
                            break
                        # TODO Check zero based indexing
                        _6 = True
                        j = i + 1
                        while True:
                            if _6 is True:
                                _6 = False
                            else:
                                j += 1
                            if not (j < self.nPhases):
                                break
                            Cm = Cm + self.Yc.getElement(i, j).getImaginary()
                    C1New = ((Cs - Cm) / DSSGlobals.TWO_PI / self.baseFrequency / (self.nPhases * (self.nPhases - 1.0)) / 2.0) * 1000000000.0
                    # nanofarads
                s = String.format.format(' R1=%-.5g  %-.5g  C1=%-.5g Phases=1', Z1.getReal(), Z1.getImaginary(), C1New)
            # conductor current ratings
            s = s + String.format.format(' Normamps=%-.5g  %-.5g', self.getNormAmps(), self.getEmergAmps())
            Parser.getInstance().setCmdString(s)
            self.edit()
        super(LineObjImpl, self).makePosSequence()

    def mergeWith(self, otherLine, series):
        """Merge this line with another line and disble the other line."""
        order1 = MutableInt()
        order2 = MutableInt()
        s = ''
        result = False
        if otherLine is not None:
            if self.nPhases != otherLine.getNPhases():
                return result
                # can't merge
            lenUnitsSaved = self.lengthUnits
            self.setYPrimInvalid(True)
            # redefine property values to make it appear that line was defined this way originally using matrices
            if series:
                totalLen = self.len + (otherLine.getLen() * LineUnits.convertLineUnits(otherLine.getLengthUnits(), self.lengthUnits))
            else:
                totalLen = 1.0
            if series:
                # redefine the bus connections
                # find bus in common between the two lines
                common1 = 0
                common2 = 0
                i = 1
                while common1 == 0 and i <= 2:
                    testBusNum = DSSGlobals.activeCircuit.getMapNodeToBus()[self.nodeRef[1 + ((i - 1) * self.nConds)]].busRef
                    _0 = True
                    j = 0
                    while True:
                        if _0 is True:
                            _0 = False
                        else:
                            j += 1
                        if not (j < 2):
                            break
                        if (
                            DSSGlobals.activeCircuit.getMapNodeToBus()[otherLine.getNodeRef()[1 + ((j - 1) * otherLine.getNConds())]].busRef == testBusNum
                        ):
                            common1 = i
                            common2 = j
                            break
                    i += 1
                if common1 == 0:
                    return result
                    # there's been an error; didn't find anything in common
                    # redefine the bus connections
                _1 = common1
                _2 = False
                while True:
                    if _1 == 1:
                        _2 = True
                        _3 = common2
                        _4 = False
                        while True:
                            if _3 == 1:
                                _4 = True
                                s = 'Bus1=\"' + otherLine.getBus(1) + '\"'
                                break
                            if (_4 is True) or (_3 == 2):
                                _4 = True
                                s = 'Bus1=\"' + otherLine.getBus(0) + '\"'
                                break
                            break
                        break
                    if (_2 is True) or (_1 == 2):
                        _2 = True
                        _5 = common2
                        _6 = False
                        while True:
                            if _5 == 1:
                                _6 = True
                                s = 'Bus2=\"' + otherLine.getBus(1) + '\"'
                                break
                            if (_6 is True) or (_5 == 2):
                                _6 = True
                                s = 'Bus2=\"' + otherLine.getBus(0) + '\"'
                                break
                            break
                        break
                    break
                Parser.getInstance().setCmdString(s)
                self.edit()
            # Rename the line
            if series:
                newName = Utilities.stripExtension(self.getBus(0)) + '~' + Utilities.stripExtension(self.getBus(1))
            else:
                newName = Utilities.stripExtension(self.getBus(0)) + '||' + Utilities.stripExtension(self.getBus(1))
            # Update control element connections to this line
            self.updateControlElements('line.' + newName, 'line.' + self.getName())
            self.updateControlElements('line.' + newName, 'line.' + otherLine.getName())
            self.setName(newName)
            if series:
                self.isSwitch = False
                # not allowed on series merge.
                # Now do the impedances
            lenSelf = self.len / self.unitsConvert
            # in units of r x Data
            lenOther = otherLine.getLen() / otherLine.getUnitsConvert()
            if self.symComponentsModel:
                # ------------------ Sym Component Model ---------------------
                if series:
                    s = ' R1=' + String.format.format('%-g', ((self.R1 * lenSelf) + (otherLine.getR1() * lenOther)) / totalLen)
                    # ohms per unit length of this line length units
                    s = s + String.format.format(' %-g', ((self.X1 * lenSelf) + (otherLine.getX1() * lenOther)) / totalLen)
                    s = s + String.format.format(' %-g', ((self.R0 * lenSelf) + (otherLine.getR0() * lenOther)) / totalLen)
                    s = s + String.format.format(' %-g', ((self.X0 * lenSelf) + (otherLine.getX0() * lenOther)) / totalLen)
                    s = s + String.format.format(' %-g', (((self.C1 * lenSelf) + (otherLine.getC1() * lenOther)) / totalLen) * 1000000000.0)
                    s = s + String.format.format(' %-g', (((self.C0 * lenSelf) + (otherLine.getC0() * lenOther)) / totalLen) * 1000000000.0)
                elif self.isSwitch:
                    s = ''
                    # Leave as is if switch; just dummy z anyway
                elif otherLine.isSwitch():
                    s = ' switch=yes'
                    # This will take care of setting Z's
                else:
                    # ********* Will This work with length multiplier? did it ever work? *************************
                    newZ = MathUtil.parallelZ(Complex(self.R1 * self.len, self.X1 * self.len), Complex(otherLine.getR1() * otherLine.getLen(), otherLine.getX1() * otherLine.getLen()))
                    s = ' R1=' + String.format.format('%-g %-g ', newZ.getReal(), newZ.getImaginary())
                    newZ = MathUtil.parallelZ(Complex(self.R0 * self.len, self.X0 * self.len), Complex(otherLine.getR0() * otherLine.getLen(), otherLine.getX0() * otherLine.getLen()))
                    s = ' R0=' + String.format.format('%-g %-g ', newZ.getReal(), newZ.getImaginary())
                    s = s + String.format.format(' %-g', (((self.C1 * self.len) + (otherLine.getC1() * otherLine.getLen())) / totalLen) * 1000000000.0)
                    s = s + String.format.format(' %-g', (((self.C0 * self.len) + (otherLine.getC0() * otherLine.getLen())) / totalLen) * 1000000000.0)
                # Parallel
                Parser.getInstance().setCmdString(s)
                # this reset the length units
                self.edit()
            elif not series:
                totalLen = self.len / 2.0
                # we'll assume lines are equal for now
            else:
                # Matrices were defined
                # merge Z matrices
                values1 = self.Z.asArray(order1)
                values2 = otherLine.getZ().asArray(order2)
                if order1 != order2:
                    return result
                    # lines not same size for some reason
                    # Z <= (Z1 + Z2) / TotalLen to get equiv ohms per unit length
                _7 = True
                i = 0
                while True:
                    if _7 is True:
                        _7 = False
                    else:
                        i += 1
                    if not (i < order1.intValue() * order1.intValue()):
                        break
                    values1[i] = ComplexUtil.divide(values1[i].multiply(lenSelf).add(values2[i].multiply(lenOther)), totalLen)
                    # merge Yc matrices
                values1 = self.Yc.asArray(order1)
                values2 = otherLine.getYc().asArray(order2)
                if order1 != order2:
                    return result
                    # lines not same size for some reason
                _8 = True
                i = 0
                while True:
                    if _8 is True:
                        _8 = False
                    else:
                        i += 1
                    if not (i < order1.intValue() * order1.intValue()):
                        break
                    values1[i] = ComplexUtil.divide(values1[i].multiply(lenSelf).add(values2[i].multiply(lenOther)), totalLen)
                    # R matrix
                s = 'Rmatrix=['
                _9 = True
                i = 0
                while True:
                    if _9 is True:
                        _9 = False
                    else:
                        i += 1
                    if not (i < 3):
                        break
                    _10 = True
                    j = 0
                    while True:
                        if _10 is True:
                            _10 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        s = s + String.format.format(' %-g', self.Z.getElement(i, j).getReal())
                    s = s + ' | '
                s = s + '] Xmatrix=['
                # X matrix
                _11 = True
                i = 0
                while True:
                    if _11 is True:
                        _11 = False
                    else:
                        i += 1
                    if not (i < 3):
                        break
                    _12 = True
                    j = 0
                    while True:
                        if _12 is True:
                            _12 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        s = s + String.format.format(' %-g', self.Z.getElement(i, j).getImaginary())
                    s = s + ' | '
                s = s + ']'
                Parser.getInstance().setCmdString(s)
                self.edit()
                # C matrix
                wnano = (DSSGlobals.TWO_PI * self.baseFrequency) / 1000000000.0
                s = 'Cmatrix=['
                _13 = True
                i = 0
                while True:
                    if _13 is True:
                        _13 = False
                    else:
                        i += 1
                    if not (i < 3):
                        break
                    _14 = True
                    j = 0
                    while True:
                        if _14 is True:
                            _14 = False
                        else:
                            j += 1
                        if not (j < i):
                            break
                        s = s + String.format.format(' %-g', self.Yc.getElement(i, j).getImaginary() / wnano)
                        # convert from mhos to nanofs
                    s = s + ' | '
                s = s + '] '
                Parser.getInstance().setCmdString(s)
                self.edit()
            # ------------------- Matrix Model ---------------------------
            # matrix definition
            Parser.getInstance().setCmdString(String.format.format(' length=%-g units=%s', totalLen, LineUnits.lineUnitsStr(lenUnitsSaved)))
            self.edit()
            otherLine.setEnabled(False)
            # disable the other line
            result = True
        else:
            DSSGlobals.doSimpleMsg('Error in line merge: Attempt to merge with invalid (nil) line object found.', 184)
        return result

    def updateControlElements(self, newName, oldName):
        ckt = DSSGlobals.activeCircuit
        for pControlElem in ckt.getDSSControls():
            if oldName.equalsIgnoreCase(pControlElem.getElementName()):
                Parser.getInstance().setCmdString(' Element=' + newName)
                # change name of the property
                pControlElem.edit()

    def fetchLineSpacing(self, code):
        if DSSGlobals.lineSpacingClass.setActive(code):
            self.setLineSpacingObj(DSSGlobals.lineSpacingClass.getActiveObj())
            self.lineCodeSpecified = False
            self.killGeometrySpecified()
            self.spacingCode = code.toLowerCase()
            # need to establish Yorder before makeZFromSpacing
            self.setNPhases(self.lineSpacingObj.getNPhases())
            self.setNConds(self.nPhases)
            # force reallocation of terminal info
            self.YOrder = self.nConds * self.nTerms
            self.setYPrimInvalid(True)
            # force rebuild of Y matrix
        else:
            DSSGlobals.doSimpleMsg('Line spacing object ' + code + ' not found.', 181)

    def fetchWireList(self, code):
        if self.getLineSpacingObj() is None:
            DSSGlobals.doSimpleMsg('Must assign the LineSpacing before wires.', 181)
        if self.phaseChoice == ConductorChoice.UNKNOWN:
            # it's an overhead line
            self.lineCodeSpecified = False
            self.killGeometrySpecified()
            self.wireData = [None] * self.lineSpacingObj.getNWires()
            istart = 1
            self.phaseChoice = ConductorChoice.OVERHEAD
        else:
            # adding bare neutrals to an underground line - TODO what about repeat invocation?
            istart = self.lineSpacingObj.getNPhases() + 1
        DSSGlobals.auxParser.setCmdString(code)
        _0 = True
        i = istart
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.lineSpacingObj.getNWires()):
                break
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name  not expecting any
            DSSGlobals.wireDataClass.setCode(DSSGlobals.auxParser.makeString())
            if ConductorDataImpl.activeConductorDataObj is not None:
                self.wireData[i] = ConductorDataImpl.activeConductorDataObj
            else:
                DSSGlobals.doSimpleMsg('Wire ' + DSSGlobals.auxParser.makeString() + ' was not defined first.', 181)

    def fetchCNCableList(self, code):
        self.lineCodeSpecified = False
        self.killGeometrySpecified()
        if self.lineSpacingObj is None:
            DSSGlobals.doSimpleMsg('Must assign the LineSpacing before CN cables.', 181)
        self.phaseChoice = ConductorChoice.CONCENTRIC_NEUTRAL
        self.wireData = [None] * self.lineSpacingObj.getNWires()
        DSSGlobals.auxParser.setCmdString(code)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.lineSpacingObj.getNPhases()):
                break
            # fill extra neutrals later
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name  not expecting any
            DSSGlobals.CNDataClass.setCode(DSSGlobals.auxParser.makeString())
            if ConductorDataImpl.activeConductorDataObj is not None:
                self.wireData[i] = ConductorDataImpl.activeConductorDataObj
            else:
                DSSGlobals.doSimpleMsg('CN cable ' + DSSGlobals.auxParser.makeString() + ' was not defined first.', 181)

    def fetchTSCableList(self, code):
        self.lineCodeSpecified = False
        self.killGeometrySpecified()
        if self.lineSpacingObj is None:
            DSSGlobals.doSimpleMsg('Must assign the LineSpacing before TS cables.', 181)
        self.phaseChoice = ConductorChoice.TAPE_SHIELD
        self.wireData = [None] * self.lineSpacingObj.getNWires()
        DSSGlobals.auxParser.setCmdString(code)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.lineSpacingObj.getNPhases()):
                break
            # fill extra neutrals later
            DSSGlobals.auxParser.getNextParam()
            # ignore any parameter name  not expecting any
            DSSGlobals.TSDataClass.setCode(DSSGlobals.auxParser.makeString())
            if ConductorDataImpl.activeConductorDataObj is not None:
                self.wireData[i] = ConductorDataImpl.activeConductorDataObj
            else:
                DSSGlobals.doSimpleMsg('TS cable ' + DSSGlobals.auxParser.makeString() + ' was not defined first.', 181)

    def fetchGeometryCode(self, code):
        if LineImpl.lineGeometryClass is None:
            LineImpl.lineGeometryClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('LineGeometry'))
        if LineImpl.lineGeometryClass.setActive(code):
            self.lineCodeSpecified = False
            # cancel this flag
            self.spacingSpecified = False
            self.setLineGeometryObj(LineImpl.lineGeometryClass.getActiveObj())
            self.ZFrequency = -1.0
            # init to signify not computed
            self.geometryCode = code.toLowerCase()
            if self.rhoSpecified:
                self.lineGeometryObj.setRhoEarth(self.rho)
            self.setNormAmps(self.lineGeometryObj.getNormAmps())
            self.setEmergAmps(self.lineGeometryObj.getEmergAmps())
            self.updatePDProperties()
            self.setNPhases(self.lineGeometryObj.getNConds())
            self.setNConds(self.nPhases)
            # force reallocation of terminal info
            self.YOrder = self.nConds * self.nTerms
            self.setYPrimInvalid(True)
            # force rebuild of Y matrix
        else:
            DSSGlobals.doSimpleMsg('Line geometry object:' + code + ' not found.', 181)

    def makeZFromGeometry(self, f):
        """Make new Z, Zinv, Yc, etc"""
        if f == self.ZFrequency:
            return
            # already done for this frequency, no need to do anything
        if self.lineGeometryObj is not None:
            # This will make a new Z; throw away present allocations
            if self.Z is not None:
                self.Z = None
            if self.ZInv is not None:
                self.ZInv = None
            if self.Yc is not None:
                self.Yc = None
            DSSGlobals.activeEarthModel = self.getEarthModel()
            self.Z = self.getLineGeometryObj().getZMatrix(f, self.len, self.lengthUnits)
            self.Yc = self.getLineGeometryObj().getYcMatrix(f, self.len, self.lengthUnits)
            # init Zinv
            if self.Z is not None:
                self.ZInv = CMatrixImpl(self.Z.order())
                # either no. phases or no. conductors
                self.ZInv.copyFrom(self.Z)
            # Z and Yc are actual total impedance for the line
            self.ZFrequency = f

    def makeZFromSpacing(self, f):
        """Make new Z, Zinv, Yc, etc"""
        # FIXME Private method in OpenDSS
        if f == self.ZFrequency:
            return
            # already done for this frequency, no need to do anything
        if self.Z is not None:
            self.Z = None
        if self.ZInv is not None:
            self.ZInv = None
        if self.Yc is not None:
            self.Yc = None
            # make a temporary LineGeometry to calculate line constants
        if LineImpl.lineGeometryClass is None:
            LineImpl.lineGeometryClass = DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find('LineGeometry'))
        pGeo = LineGeometryObjImpl(LineImpl.lineGeometryClass, '==')
        pGeo.loadSpacingAndWires(self.getLineSpacingObj(), self.getWireData())
        # this sets OH, CN, or TS
        if self.rhoSpecified:
            pGeo.setRhoEarth(self.rho)
        self.setNormAmps(pGeo.getNormAmps())
        self.setEmergAmps(pGeo.getEmergAmps())
        self.updatePDProperties()
        DSSGlobals.activeEarthModel = self.earthModel
        self.Z = pGeo.getZMatrix(f, self.len, self.lengthUnits)
        self.Yc = pGeo.getYcMatrix(f, self.len, self.lengthUnits)
        if self.Z is not None:
            self.ZInv = CMatrixImpl(self.Z.order())
            # either no. phases or no. conductors
            self.ZInv.copyFrom(self.Z)
        pGeo = None
        self.ZFrequency = f

    def killGeometrySpecified(self):
        """Indicate no line geometry specification if this is called."""
        # FIXME Private method in OpenDSS
        if self.geometrySpecified:
            self.lineGeometryObj = None
            self.ZFrequency = -1.0
            self.geometrySpecified = False

    def killSpacingSpecified(self):
        if self.spacingSpecified:
            self.lineSpacingObj = None
            self.wireData = [None] * 0
            self.phaseChoice = ConductorChoice.UNKNOWN
            self.ZFrequency = -1.0
            self.spacingSpecified = False

    def clearYPrim(self):
        # line object needs both series and shunt YPrims built
        # FIXME Private method in OpenDSS
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
            self.YPrimSeries.clear()
            # zero out YPrim_Series
            self.YPrimShunt.clear()
            # zero out YPrim_Shunt
            self.YPrim.clear()
            # zero out YPrim

    def resetLengthUnits(self):
        """If specify the impedances always assume the length units match."""
        self.unitsConvert = 1.0
        self.lengthUnits = LineUnits.UNITS_NONE

    def numConductorData(self):
        if self.wireData is not None:
            return self.lineSpacingObj.getNWires()
        if self.lineGeometryObj is not None:
            return self.lineGeometryObj.getNWires()
        return 0

    def fetchConductorData(self, i):
        if self.wireData is not None:
            if i <= self.lineSpacingObj.getNWires():
                return self.wireData[i]
        elif self.lineGeometryObj is not None:
            if i <= self.lineGeometryObj.getNWires():
                return self.lineGeometryObj.getConductorData(i)
        return None

    def updatePDProperties(self):
        self.setPropertyValue(Line.NumPropsThisClass + 0, String.format.format('%-g', self.getNormAmps()))
        self.setPropertyValue(Line.NumPropsThisClass + 1, String.format.format('%-g', self.getEmergAmps()))
        self.setPropertyValue(Line.NumPropsThisClass + 2, String.format.format('%-g', self.getFaultRate()))
        self.setPropertyValue(Line.NumPropsThisClass + 3, String.format.format('%-g', self.getPctPerm()))
        self.setPropertyValue(Line.NumPropsThisClass + 4, String.format.format('%-g', self.getHrsToRepair()))

    def getZ(self):
        return self.Z

    def setZ(self, z):
        self.Z = z

    def getYc(self):
        return self.Yc

    def setYc(self, yc):
        self.Yc = yc

    def getR1(self):
        return self.R1

    def setR1(self, r1):
        self.R1 = r1

    def getX1(self):
        return self.X1

    def setX1(self, x1):
        self.X1 = x1

    def getR0(self):
        return self.R0

    def setR0(self, r0):
        self.R0 = r0

    def getX0(self):
        return self.X0

    def setX0(self, x0):
        self.X0 = x0

    def getC1(self):
        return self.C1

    def setC1(self, c1):
        self.C1 = c1

    def getC0(self):
        return self.C0

    def setC0(self, c0):
        self.C0 = c0

    def getLen(self):
        return self.len

    def setLen(self, value):
        self.len = value

    def getLengthUnits(self):
        return self.lengthUnits

    def setLengthUnits(self, units):
        self.lengthUnits = units

    def getRg(self):
        return self.Rg

    def setRg(self, rg):
        self.Rg = rg

    def getXg(self):
        return self.Xg

    def setXg(self, xg):
        self.Xg = xg

    def getKXg(self):
        return self.KXg

    def setKXg(self, kxg):
        self.KXg = kxg

    def getRho(self):
        return self.rho

    def setRho(self, rho):
        self.rho = rho

    def getGeneralPlotQuantity(self):
        return self.generalPlotQuantity

    def setGeneralPlotQuantity(self, quantity):
        self.generalPlotQuantity = quantity

    def getCondCode(self):
        return self.condCode

    def setCondCode(self, code):
        self.condCode = code

    def getGeometryCode(self):
        return self.geometryCode

    def setGeometryCode(self, code):
        self.geometryCode = code

    def getSpacingCode(self):
        return self.spacingCode

    def setSpacingCode(self, code):
        self.spacingCode = code

    def isGeometrySpecified(self):
        return self.geometrySpecified

    def setGeometrySpecified(self, specified):
        self.geometrySpecified = specified

    def isSpacingSpecified(self):
        return self.spacingSpecified

    def setSpacingSpecified(self, specified):
        self.spacingSpecified = specified

    def isSymComponentsChanged(self):
        return self.symComponentsChanged

    def setSymComponentsChanged(self, changed):
        self.symComponentsChanged = changed

    def isSymComponentsModel(self):
        return self.symComponentsModel

    def setSymComponentsModel(self, model):
        self.symComponentsModel = model

    def isSwitch(self):
        return self.isSwitch

    def setSwitch(self, value):
        self.isSwitch = value

    def isLineCodeSpecified(self):
        return self.lineCodeSpecified

    def getPhaseChoice(self):
        return self.phaseChoice

    def numConductorsAvailable(self):
        return self.numConductorData()

    def getConductorData(self, i):
        # FIXME Private members in OpenDSS
        return self.fetchConductorData(i)

    def getZFrequency(self):
        return self.ZFrequency

    def setZFrequency(self, frequency):
        self.ZFrequency = frequency

    def getLineCodeUnits(self):
        return self.lineCodeUnits

    def setLineCodeUnits(self, units):
        self.lineCodeUnits = units

    def getUnitsConvert(self):
        return self.unitsConvert

    def setUnitsConvert(self, units):
        self.unitsConvert = units

    def getLineGeometryObj(self):
        return self.lineGeometryObj

    def setLineGeometryObj(self, obj):
        self.lineGeometryObj = obj

    def getLineSpacingObj(self):
        return self.lineSpacingObj

    def setLineSpacingObj(self, obj):
        self.lineSpacingObj = obj

    def getWireData(self):
        return self.wireData

    def setWireData(self, data):
        self.wireData = data

    def getRhoSpecified(self):
        return self.rhoSpecified

    def setRhoSpecified(self, value):
        self.rhoSpecified = value

    def getEarthModel(self):
        return self.earthModel

    def setEarthModel(self, model):
        self.earthModel = model

    def setLineCodeSpecified(self, value):
        self.lineCodeSpecified = value

    def getZInv(self):
        return self.ZInv

    def setZInv(self, zinv):
        self.ZInv = zinv

    def isCapSpecified(self):
        return self.capSpecified

    def setCapSpecified(self, value):
        self.capSpecified = value
