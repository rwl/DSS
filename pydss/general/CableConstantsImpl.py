from dss.shared.impl.LineUnits import (LineUnits,)
from dss.general.impl.LineConstantsImpl import (LineConstantsImpl,)
from dss.shared.impl.MathUtil import (MathUtil,)
from dss.general.CableConstants import (CableConstants,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)


class CableConstantsImpl(LineConstantsImpl, CableConstants):
    epsR = None
    insLayer = None
    diaIns = None
    diaCable = None

    def __init__(self, numConductors):
        super(CableConstantsImpl, self)(numConductors)
        self.epsR = [None] * self.getNumConds()
        self.insLayer = [None] * self.getNumConds()
        self.diaIns = [None] * self.getNumConds()
        self.diaCable = [None] * self.getNumConds()

    def Kron(self, norder):
        """Don't reduce Y, it has zero neutral capacitance."""
        ZTemp = self.ZMatrix
        firstTime = True
        if self.frequency >= 0.0 and norder > 0 and norder < self.getNumConds():
            if self.ZReduced is not None:
                self.ZReduced = None
            if self.YcReduced is not None:
                self.YcReduced = None
            while ZTemp.order() > norder:
                self.ZReduced = ZTemp.kron(ZTemp.order())
                # Eliminate last row
                if not firstTime:
                    ZTemp = None
                    # Ztemp points to intermediate matrix
                ZTemp = self.ZReduced
                firstTime = False
            # now copy part of FYCmatrix to FYCreduced
            self.YcReduced = CMatrixImpl(norder)
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < norder):
                    break
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < norder):
                        break
                    self.YcReduced.setElement(i, j, self.YcMatrix.getElement(i, j))

    def conductorsInSameSpace(self, errorMessage):
        result = False
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.getNumConds()):
                break
            if self.Y[i] >= 0.0:
                result = True
                errorMessage.__add__(String.format.format('Cable %d height must be < 0. ', i))
                # FIXME Pass by reference
                return result
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < self.getNumConds()):
                break
            if i <= self.getNPhases():
                Ri = self.radius[i]
            else:
                Ri = 0.5 * self.diaCable[i]
            _2 = True
            j = i
            while True:
                if _2 is True:
                    _2 = False
                else:
                    j += 1
                if not (j < self.getNumConds()):
                    break
                # TODO Check zero based indexing
                if j <= self.getNPhases():
                    Rj = self.radius[j]
                else:
                    Rj = 0.5 * self.diaCable[j]
                Dij = self.Math.sqrt(MathUtil.sqr(self.X[i] - self.X[j]) + MathUtil.sqr(self.Y[i] - self.Y[j]))
                if Dij < Ri + Rj:
                    result = True
                    errorMessage.__add__(String.format.format('Cable conductors %d and %d occupy the same space.', i, j))
                    return result
        return result

    def getEpsR(self, i):
        return self.epsR[i]

    def setEpsR(self, i, epsr):
        self.epsR[i] = epsr

    def getInsLayer(self, i, units):
        return self.insLayer[i] * LineUnits.fromMeters(units)

    def setInsLayer(self, i, units, inslayer):
        if i >= 0 and i < self.getNumConds():
            self.insLayer[i] = inslayer * LineUnits.toMeters(units)

    def getDiaIns(self, i, units):
        return self.diaIns[i] * LineUnits.fromMeters(units)

    def setDiaIns(self, i, units, diains):
        if i >= 0 and i < self.getNumConds():
            self.diaIns[i] = diains * LineUnits.toMeters(units)

    def getDiaCable(self, i, units):
        return self.diaCable[i] * LineUnits.fromMeters(units)

    def setDiaCable(self, i, units, diacable):
        if i >= 0 and i < self.getNumConds():
            self.diaCable[i] = diacable * LineUnits.toMeters(units)
