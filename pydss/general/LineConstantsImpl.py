from dss.general.LineConstants import (LineConstants,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.common.impl.DSSGlobals import (DSSGlobals,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class LineConstantsImpl(object, LineConstants):
    """This class returns a matrix ordered by phases first then remaining conductors.
    Assumes phases are defined first.
    """
    numConds = None
    numPhases = None
    X = None
    Y = None
    Rdc = None
    # ohms/m
    Rac = None
    # ohms/m
    GMR = None
    # m
    radius = None
    ZMatrix = None
    # in ohms/m
    YcMatrix = None
    # siemens/m   --- jwC
    ZReduced = None
    # these two do not exist until Kron reduction
    YcReduced = None
    # is executed
    frequency = None
    # frequency for which impedances are computed
    w = None
    # 2piF
    rhoEarth = None
    # ohm-m
    me = None
    # factor for earth impedance
    rhoChanged = None

    def __init__(self, numConductors):
        self.numConds = numConductors
        self.setNPhases(self.numConds)
        self.X = [None] * self.numConds
        self.Y = [None] * self.numConds
        self.GMR = [None] * self.numConds
        self.radius = [None] * self.numConds
        self.Rdc = [None] * self.numConds
        self.Rac = [None] * self.numConds
        # Initialize to not set
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            self.GMR[i] = -1.0
            self.radius[i] = -1.0
            self.Rdc[i] = -1.0
        self.ZMatrix = CMatrixImpl(self.numConds)
        self.YcMatrix = CMatrixImpl(self.numConds)
        self.setFrequency(-1.0)
        # not computed
        self.setRhoEarth(100.0)
        # default value
        self.rhoChanged = True
        self.ZReduced = None
        self.YcReduced = None

    def calc(self, f):
        """Force a calc of impedances.

        Compute base Z and Yc matrices in ohms/m for this frequency and earth impedance.
        """
        # rhoEarth = rho;
        self.setFrequency(f)
        # this has side effects
        if self.ZReduced is not None:
            reducedSize = self.ZReduced.order()
            self.ZReduced = None
        else:
            reducedSize = 0
        if self.YcReduced is not None:
            self.YcReduced = None
        self.ZReduced = None
        self.YcReduced = None
        self.ZMatrix.clear()
        self.YcMatrix.clear()
        # For less than 1 kHz use GMR to better match published data
        LFactor = Complex(0.0, (self.w * self.MU0) / self.TWO_PI)
        if f < 1000.0 and f > 40.0:
            powerFreq = True
        else:
            powerFreq = False
        # Self impedances
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            Zi = self.getZint(i)
            if powerFreq:
                # for less than 1 kHz, use published GMR
                Zi = Complex(Zi.getReal(), 0.0)
                ZSpacing = LFactor.multiply(self.Math.log(1.0 / self.GMR[i]))
                # use GMR
            else:
                ZSpacing = LFactor.multiply(self.Math.log(1.0 / self.radius[i]))
            self.ZMatrix.setElement(i, i, Zi.add(ZSpacing.add(self.getZe(i, i))))
        # Mutual impedances
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            _2 = True
            j = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    j += 1
                if not (j < i - 1):
                    break
                # TODO Check zero based indexing
                Dij = self.Math.sqrt(self.Math.pow(self.X[i] - self.X[j], 2) + self.Math.pow(self.Y[i] - self.Y[j], 2))
                self.ZMatrix.setElemSym(i, j, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
        # Capacitance matrix
        PFactor = -1.0 / self.TWO_PI / self.E0 / self.w
        # include frequency
        # Construct P matrix and then invert
        _3 = True
        i = 0
        while True:
            if _3 is True:
                _3 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            self.YcMatrix.setElement(i, i, Complex(0.0, PFactor * self.Math.log((2.0 * self.Y[i]) / self.radius[i])))
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < i - 1):
                    break
                # TODO Check zero based indexing
                Dij = self.Math.sqrt(self.Math.pow(self.X[i] - self.X[j], 2) + self.Math.pow(self.Y[i] - self.Y[j], 2))
                Dijp = self.Math.sqrt(self.Math.pow(self.X[i] - self.X[j], 2) + self.Math.pow(self.Y[i] + self.Y[j], 2))
                # distance to image j
                self.YcMatrix.setElemSym(i, j, Complex(0.0, PFactor * self.Math.log(Dijp / Dij)))
        self.YcMatrix.invert()
        # now should be nodal C matrix
        if reducedSize > 0:
            self.Kron(reducedSize)
            # was reduced so reduce again to same size
            # else the Zmatrix is OK as last computed
        self.rhoChanged = False

    def conductorsInSameSpace(self, errorMessage):
        # Check all conductors to make sure none occupy the same space or are defined at 0,0
        result = False
        # Check for 0 Y coordinate
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            if self.Y[i] <= 0.0:
                result = True
                errorMessage.__add__(String.format.format('Conductor %d height must be  > 0. ', i))
                return result
        # Check for overlapping conductors
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numConds):
                break
            _2 = True
            j = i + 1
            while True:
                if _2 is True:
                    _2 = False
                else:
                    j += 1
                if not (j < self.numConds):
                    break
                # TODO Check zero based indexing
                Dij = self.Math.sqrt(self.Math.pow(self.X[i] - self.X[j], 2) + self.Math.pow(self.Y[i] - self.Y[j], 2))
                if Dij < self.radius[i] + self.radius[j]:
                    result = True
                    errorMessage.__add__(String.format.format('Conductors %d and %d occupy the same space.', i, j))
                    return result
        return result

    def getGMR(self, i, units):
        return self.GMR[i] * LineUnits.fromMeters(units)

    def getRac(self, i, units):
        return self.Rac[i] * LineUnits.fromPerMeter(units)

    def getRadius(self, i, units):
        return self.radius[i] * LineUnits.fromMeters(units)

    def getRdc(self, i, units):
        return self.Rdc[i] * LineUnits.fromPerMeter(units)

    def getX(self, i, units):
        return self.X[i] * LineUnits.fromMeters(units)

    def getY(self, i, units):
        return self.Y[i] * LineUnits.fromMeters(units)

    def getYcMatrix(self, f, length, units):
        """Will auto recalc the impedance matrices if frequency is different
        Converts to desired units when executed.

        Makes a new YCmatrix and correct for lengths and units as it copies.
        Uses the reduced Zmatrix by default if it exists.
        """
        if self.YcReduced is not None:
            Yc = self.YcReduced
        else:
            Yc = self.YcMatrix
        newSize = Yc.order()
        result = CMatrixImpl(newSize)
        result.copyFrom(Yc)
        YcValues = result.asArray()
        newSize = result.order()
        unitLengthConversion = LineUnits.fromPerMeter(units) * length
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < newSize * newSize):
                break
            YcValues[i] = YcValues[i].multiply(unitLengthConversion)
            # a=a*b
        return result

    def getZe(self, i, j):
        """Earth return impedance at present frequency for ij element."""
        result = None
        Yi = self.Math.abs(self.Y[i])
        Yj = self.Math.abs(self.Y[j])
        _0 = DSSGlobals.activeEarthModel
        _1 = False
        while True:
            if _0 == DSSGlobals.SIMPLECARSON:
                _1 = True
                result = Complex((self.w * self.MU0) / 8.0, ((self.w * self.MU0) / self.TWO_PI) * self.Math.log(658.5 * self.Math.sqrt(self.rhoEarth / self.frequency)))
                break
            if (_1 is True) or (_0 == DSSGlobals.FULLCARSON):
                _1 = True
                if i == j:
                    thetaij = 0.0
                    Dij = 2.0 * Yi
                else:
                    Dij = self.Math.sqrt(self.Math.pow(Yi + Yj + self.Math.pow(self.X[i] - self.X[j], 2), 2))
                    thetaij = self.Math.acos((Yi + Yj) / Dij)
                mij = 0.0028099 * Dij * self.Math.sqrt(self.frequency / self.rhoEarth)
                re = (((self.Math.PI / 8.0) - (self.B1 * mij * self.Math.cos(thetaij))) + (self.B2 * self.Math.pow(mij, 2) * ((self.Math.log(self.Math.exp(self.C2) / mij) * self.Math.cos(2.0 * thetaij)) + (thetaij * self.Math.sin(2.0 * thetaij)))) + (self.B3 * mij * mij * mij * self.Math.cos(3.0 * thetaij))) - (self.D4 * mij * mij * mij * mij * self.Math.cos(4.0 * thetaij))
                term1 = 0.5 * self.Math.log(1.85138 / mij)
                term2 = self.B1 * mij * self.Math.cos(thetaij)
                term3 = -self.D2 * self.Math.pow(mij, 2) * self.Math.cos(2.0 * thetaij)
                term4 = self.B3 * mij * mij * mij * self.Math.cos(3.0 * thetaij)
                term5 = -self.B4 * mij * mij * mij * mij * ((self.Math.log(self.Math.exp(self.C4) / mij) * self.Math.cos(4.0 * thetaij)) + (thetaij * self.Math.sin(4.0 * thetaij)))
                im = term1 + term2 + term3 + term4 + term5
                result = Complex(re, im)
                result = Complex(result.getReal(), result.getImaginary() + (0.5 * self.Math.log(Dij)))
                # correction term to work with DSS structure
                result = result.multiply((self.w * self.MU0) / self.Math.PI)
                break
            if (_1 is True) or (_0 == DSSGlobals.DERI):
                _1 = True
                if i != j:
                    hterm = Complex(Yi + Yj, 0.0).add(ComplexUtil.invert(self.me).multiply(2.0))
                    xterm = Complex(self.X[i] - self.X[j], 0.0)
                    lnArg = hterm.multiply(hterm).add(xterm.multiply(xterm)).sqrt()
                    result = Complex(0.0, (self.w * self.MU0) / self.TWO_PI).multiply(lnArg.log())
                else:
                    hterm = Complex(Yi, 0.0).add(ComplexUtil.invert(self.me))
                    result = Complex(0.0, (self.w * self.MU0) / self.TWO_PI).multiply(hterm.multiply(2.0).log())
                break
            break
        return result

    def getZint(self, i):
        """Internal impedance of i-th conductor for present frequency."""
        result = None
        _0 = DSSGlobals.activeEarthModel
        _1 = False
        while True:
            if _0 == DSSGlobals.SIMPLECARSON:
                _1 = True
                result = Complex(self.Rac[i], (self.w * self.MU0) / (8 * self.Math.PI))
                break
            if (_1 is True) or (_0 == DSSGlobals.FULLCARSON):
                _1 = True
                result = Complex(self.Rac[i], (self.w * self.MU0) / (8 * self.Math.PI))
                break
            if (_1 is True) or (_0 == DSSGlobals.DERI):
                _1 = True
                alpha = self.C1_j1.multiply(self.Math.sqrt((self.frequency * self.MU0) / self.Rdc[i]))
                if alpha.abs() > 35.0:
                    I0I1 = Complex.ONE
                else:
                    I0I1 = MathUtil.Bessel_I0(alpha).divide(MathUtil.Bessel_I0(alpha))
                result = self.C1_j1.multiply(I0I1).multiply(self.Math.sqrt(self.Rdc[i] * self.frequency * self.MU0) / 2.0)
                break
            break
        return result

    def getZMatrix(self, f, length, units):
        """Will auto recalc the impedance matrices if frequency is different
        converts to desired units when executed.

        Makes a new Zmatrix and correct for lengths and units as it copies.
        Uses the reduced Zmatrix by default if it exists.
        """
        if (f != self.frequency) or self.rhoChanged:
            self.calc(f)
            # only recalcs if f changed or rho earth changed
        if self.ZReduced is not None:
            Z = self.ZReduced
        else:
            Z = self.ZMatrix
        newSize = Z.order()
        result = CMatrixImpl(newSize)
        result.copyFrom(Z)
        # gets ohms/meter
        ZValues = result.asArray()
        # ptr to the values in the new copy
        newSize = result.order()
        # Convert the values by units and length
        unitLengthConversion = LineUnits.fromPerMeter(units) * length
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < newSize * newSize):
                break
            ZValues[i] = ZValues[i].multiply(unitLengthConversion)
            # a=a*b
        return None

    def Kron(self, nOrder):
        """Performs a Kron reduction leaving first nOrder rows."""
        ZTemp = self.ZMatrix
        YcTemp = self.YcMatrix
        firstTime = True
        if self.frequency >= 0.0 and nOrder > 0 and nOrder < self.numConds:
            if self.ZReduced is not None:
                self.ZReduced = None
            if self.YcReduced is not None:
                self.YcReduced = None
                # Reduce computed matrix one row/col at a time until it is norder
            while ZTemp.order() > nOrder:
                self.ZReduced = ZTemp.kron(ZTemp.order())
                # eliminate last row
                self.YcReduced = YcTemp.kron(ZTemp.order())
                if not firstTime:
                    ZTemp = None
                    # Ztemp points to intermediate matrix
                    YcTemp = None
                ZTemp = self.ZReduced
                YcTemp = self.YcReduced
                firstTime = False
            # Left with reduced matrix

    def reduce(self):
        """Kron reduce to num phases only."""
        self.Kron(self.numPhases)

    def setFrequency(self, value):
        self.setFrequency(value)
        self.w = self.TWO_PI * self.frequency
        self.me = Complex(0.0, (self.w * self.MU0) / self.rhoEarth).sqrt()

    def setRhoEarth(self, value):
        if value != self.rhoEarth:
            self.rhoChanged = True
        self.rhoEarth = value
        if self.frequency >= 0.0:
            self.me = Complex(0.0, (self.w * self.MU0) / self.rhoEarth).sqrt()

    def setGMR(self, i, units, value):
        if i > 0 and i <= self.numConds:
            # TODO Check zero based indexing
            self.GMR[i] = value * LineUnits.toMeters(units)
            if self.radius[i] < 0.0:
                self.radius[i] = self.GMR[i] / 0.7788
                # equivalent round conductor

    def setNPhases(self, value):
        self.numPhases = value

    def setRac(self, i, units, value):
        if i > 0 and i <= self.numConds:
            # TODO Check zero based indexing
            self.Rac[i] = value * LineUnits.toPerMeter(units)

    def setRadius(self, i, units, value):
        if i > 0 and i <= self.numConds:
            # TODO Check zero based indexing
            self.radius[i] = value * LineUnits.toMeters(units)
            if self.GMR[i] < 0.0:
                self.GMR[i] = self.radius[i] * 0.7788
                # default to round conductor

    def setRdc(self, i, units, value):
        if i > 0 and i <= self.numConds:
            self.Rdc[i] = value * LineUnits.toPerMeter(units)

    def setX(self, i, units, value):
        if i > 0 and i <= self.numConds:
            self.X[i] = value * LineUnits.toMeters(units)

    def setY(self, i, units, value):
        if i > 0 and i <= self.numConds:
            self.Y[i] = value * LineUnits.toMeters(units)

    def getRhoEarth(self):
        return self.rhoEarth

    def getNumConds(self):
        return self.numConds

    def getFrequency(self):
        return self.frequency

    def getNPhases(self):
        return self.numPhases
