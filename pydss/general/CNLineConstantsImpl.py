from dss.general.CNLineConstants import (CNLineConstants,)
from dss.general.LineConstants import (LineConstants,)
from dss.shared.impl.LineUnits import (LineUnits,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.general.impl.CableConstantsImpl import (CableConstantsImpl,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class CNLineConstantsImpl(CableConstantsImpl, CNLineConstants):
    kStrand = None
    diaStrand = None
    gmrStrand = None
    rStrand = None

    def __init__(self, numConductors):
        super(CNLineConstantsImpl, self)(numConductors)
        self.kStrand = [None] * self.getNumConds()
        self.diaStrand = [None] * self.getNumConds()
        self.gmrStrand = [None] * self.getNumConds()
        self.rStrand = [None] * self.getNumConds()

    def calc(self, f):
        """Compute base Z and YC matrices in ohms/m for this frequency and earth impedance."""
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
        # add concentric neutrals to the end of conductor list; they are always reduced
        n = self.getNumConds() + self.getNPhases()
        ZMat = CMatrixImpl(n)
        # For less than 1 kHz use GMR to better match published data
        LFactor = Complex(0.0, (self.w * self.MU0) / LineConstants.TWO_PI)
        if f < 1000.0 and f > 40.0:
            powerFreq = True
        else:
            powerFreq = False
        # self impedances - CN cores and bare neutrals
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNumConds()):
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
        # CN self impedances
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            resCN = self.rStrand[i] / self.kStrand[i]
            radCN = 0.5 * (self.diaCable[i] - self.diaStrand[i])
            gmrCN = self.Math.pow(self.gmrStrand[i] * self.kStrand[i] * self.Math.pow(radCN, self.kStrand[i] - 1.0), 1.0 / self.kStrand[i])
            ZSpacing = LFactor.multiply(self.Math.log(1.0 / gmrCN))
            Zi = Complex(resCN, 0.0)
            idxi = i + self.getNumConds()
            ZMat.setElement(idxi, idxi, Zi.add(ZSpacing.add(self.getZe(i, i))))
        # mutual impedances - between CN cores and bare neutrals
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < self.getNumConds()):
                break
            _3 = True
            j = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    j += 1
                if not (j < i - 1):
                    break
                Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                ZMat.setElemSym(i, j, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
        # mutual impedances - CN to other CN, cores, and bare neutrals
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < self.getNPhases()):
                break
            idxi = i + self.getNumConds()
            _5 = True
            j = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    j += 1
                if not (j < i - 1):
                    break
                # CN to other CN
                idxj = j + self.getNumConds()
                Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                ZMat.setElemSym(idxi, idxj, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
            _6 = True
            j = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    j += 1
                if not (j < self.getNumConds()):
                    break
                # CN to cores and bare neutrals
                idxj = j
                radCN = 0.5 * (self.diaCable[i] - self.diaStrand[i])
                if i == j:
                    # CN to its own phase core
                    Dij = radCN
                else:
                    # CN to another phase or bare neutral
                    Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                    Dij = self.Math.pow(self.Math.pow(Dij, self.kStrand[i]) - self.Math.pow(radCN, self.kStrand[i]), 1.0 / self.kStrand[i])
                ZMat.setElemSym(idxi, idxj, LFactor.multiply(self.Math.log(1.0 / Dij)).add(self.getZe(i, j)))
        # reduce out the CN
        while ZMat.order() > self.getNumConds():
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
            if not (i < self.getNPhases()):
                break
            YFactor = LineConstants.TWO_PI * LineConstants.E0 * self.epsR[i] * self.w
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

    def getKStrand(self, i):
        return self.kStrand[i]

    def getDiaStrand(self, i):
        return self.diaStrand[i]

    def getGmrStrand(self, i):
        return self.gmrStrand[i]

    def getRStrand(self, i):
        return self.rStrand[i]

    def setKStrand(self, i, kstrand):
        if i >= 0 and i <= self.getNumConds():
            self.kStrand[i] = kstrand

    def setDiaStrand(self, i, units, diastrand):
        if i >= 0 and i <= self.getNumConds():
            self.diaStrand[i] = diastrand * LineUnits.toMeters(units)

    def setGmrStrand(self, i, units, gmrstrand):
        if i >= 0 and i <= self.getNumConds():
            self.gmrStrand[i] = gmrstrand * LineUnits.toMeters(units)

    def setRStrand(self, i, units, rstrand):
        if i >= 0 and i <= self.getNumConds():
            self.rStrand[i] = rstrand * LineUnits.toPerMeter(units)
