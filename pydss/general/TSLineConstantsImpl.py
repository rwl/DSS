from dss.shared.impl.LineUnits import (LineUnits,)
from dss.general.TSLineConstants import (TSLineConstants,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.general.impl.CableConstantsImpl import (CableConstantsImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class TSLineConstantsImpl(CableConstantsImpl, TSLineConstants):
    diaShield = None
    tapeLayer = None
    tapeLap = None

    def __init__(self, numConductors):
        super(TSLineConstantsImpl, self)(numConductors)
        self.diaShield = [None] * self.numConds
        self.tapeLayer = [None] * self.numConds
        self.tapeLap = [None] * self.numConds

    def getDiaShield(self, i, units):
        return self.diaShield[i] * LineUnits.fromMeters(units)

    def getTapeLayer(self, i, units):
        return self.tapeLayer[i] * LineUnits.fromMeters(units)

    def getTapeLap(self, i):
        return self.tapeLap[i]

    def setDiaShield(self, i, units, shield):
        if i >= 0 and i < self.numConds:
            self.diaShield[i] = shield * LineUnits.toMeters(units)

    def setTapeLayer(self, i, units, layer):
        if i >= 0 and i < self.numConds:
            self.tapeLayer[i] = layer * LineUnits.toMeters(units)

    def setTapeLap(self, i, lap):
        if i >= 0 and i < self.numConds:
            self.tapeLap[i] = lap

    def calc(self, f):
        """Compute base Z and YC matrices in ohms/m for this frequency and earth impedance."""
        self.setFrequency(f)
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
        # add concentric neutrals to the end of conductor list; they are always reduced
        n = self.numConds + self.numPhases
        ZMat = CMatrixImpl(n)
        # For less than 1 kHz use GMR to better match published data
        LFactor = Complex(0.0, (self.w * self.MU0) / self.TWO_PI)
        if f < 1000.0 and f > 40.0:
            powerFreq = True
        else:
            powerFreq = False
        # self impedances - TS cores and bare neutrals
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
            ZMat.setElement(i, i, Zi.add(ZSpacing.add(self.getZe(i, i))))
        # TS self impedances
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.numPhases):
                break
            resTS = (0.3183 * self.RHO_TS) / (self.diaShield[i] * self.tapeLayer[i] * self.Math.sqrt(50.0 / (100.0 - self.tapeLap[i])))
            gmrTS = 0.5 * (self.diaShield[i] - self.tapeLayer[i])
            # per Kersting, to center of TS
            ZSpacing = LFactor.multiply(self.Math.log(1.0 / gmrTS))
            Zi = Complex(resTS, 0.0)
            idxi = i + self.numConds
            ZMat.setElement(idxi, idxi, Zi.add(ZSpacing.add(self.getZe(i, i))))
        # mutual impedances - between TS cores and bare neutrals
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.numConds):
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
                # TODO Check zero based indexing
                Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                ZMat.setElemSym(i, j, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
        # mutual impedances - TS to other TS, cores, and bare neutrals
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.numPhases):
                break
            idxi = i + self.numConds
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < i):
                    break
                # TODO Check zero based indexing
                # TS to other TS
                idxj = j + self.numConds
                Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                ZMat.setElemSym(idxi, idxj, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
            _6 = True
            j = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    j += 1
                if not (j < self.numConds):
                    break
                # CN to cores and bare neutrals
                idxj = j
                gmrTS = 0.5 * (self.diaShield[i] - self.tapeLayer[i])
                # per Kersting, to center of TS
                if i == j:
                    # TS to its own phase core
                    Dij = gmrTS
                else:
                    # TS to another phase or bare neutral
                    Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                ZMat.setElemSym(idxi, idxj, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
        # reduce out the tape shields
        while ZMat.order() > self.numConds:
            ZTemp = ZMat.kron(ZMat.order())
            ZMat = None
            ZMat = ZTemp
        self.ZMatrix.copyFrom(ZMat)
        ZMat = None
        # for shielded cables, build the capacitance matrix directly
        # assumes the insulation may lie between semicon layers
        _7 = True
        i = 0
        while True:
            if _7 is True:
                _7 = False
            else:
                i += 1
            if not (i < self.numPhases):
                break
            YFactor = self.TWO_PI * self.E0 * self.epsR[i] * self.w
            # includes frequency so C==>Y
            radOut = 0.5 * self.diaIns[i]
            radIn = radOut - self.insLayer[i]
            denom = self.Math.log(radOut / radIn)
            self.YcMatrix.setElement(i, i, Complex(0.0, YFactor / denom))
        if reducedSize > 0:
            self.Kron(reducedSize)
            # was reduced so reduce again to same size
            # else the Zmatrix is OK as last computed
        self.rhoChanged = False
