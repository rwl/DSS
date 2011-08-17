from __pyjamas__ import (ARGERROR,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
from dss.shared.impl.CMatrixImpl import (CMatrixImpl,)
# from org.apache.commons.lang.mutable.MutableDouble import (MutableDouble,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class MathUtil(object):
    # Symmetrical component conversion matrices
    As2p = getAs2p(3)
    Ap2s = getAp2s(3)
    # private static final CMatrix ClarkeF = getClarkeF(3);
    # private static final CMatrix ClarkeR = getClarkeR(3);
    # private static final double SIN2PI3 = Math.sin(2.0 * Math.PI / 3.0);

    @classmethod
    def getAMatrix(cls, order):
        Amat = CMatrixImpl(order)
        a = Complex(-0.5, 0.866025403)
        aa = Complex(-0.5, -0.866025403)
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < 3):
                break
            Amat.setElemSym(0, i, Complex.ONE)
        Amat.setElement(1, 1, aa)
        Amat.setElement(2, 2, aa)
        Amat.setElemSym(1, 2, a)
        return Amat

    @classmethod
    def getAs2p(cls, order):
        return cls.getAMatrix(order)

    @classmethod
    def getAp2s(cls, order):
        # /**
        # * Forward Clarke.
        # //
        # private static CMatrix getClarkeF(int order) {
        # CMatrix ClarkeF = new CMatrixImpl(order);
        # ClarkeF.setElement(0, 0, new Complex(1.0, 0.0) );
        # ClarkeF.setElement(0, 1, new Complex(-0.5,0.0) );
        # ClarkeF.setElement(0, 2, new Complex(-0.5,0.0) );
        # ClarkeF.setElement(1, 1, new Complex(SIN2PI3, 0.0) );
        # ClarkeF.setElement(1, 2, new Complex(-SIN2PI3,0.0) );
        # ClarkeF.setElement(2, 0, new Complex(0.5, 0.0) );
        # ClarkeF.setElement(2, 1, new Complex(0.5, 0.0) );
        # ClarkeF.setElement(2, 2, new Complex(0.5, 0.0) );
        # ClarkeF.multByConst(2.0 / 3.0);  // multiply all elements by a const 2/3
        # return ClarkeF;
        # }
        # /**
        # * Reverse Clarke.
        # //
        # private static CMatrix getClarkeR(int order) {
        # CMatrix ClarkeR = new CMatrixImpl(order);
        # ClarkeR.setElement(0, 0, new Complex(1.0, 0.0) );
        # ClarkeR.setElement(1, 0, new Complex(-0.5,0.0) );
        # ClarkeR.setElement(2, 0, new Complex(-0.5,0.0) );
        # ClarkeR.setElement(1, 1, new Complex(SIN2PI3, 0.0) );
        # ClarkeR.setElement(2, 1, new Complex(-SIN2PI3,0.0) );
        # ClarkeR.setElement(0, 2, new Complex(1.0, 0.0) );
        # ClarkeR.setElement(1, 2, new Complex(1.0, 0.0) );
        # ClarkeR.setElement(2, 3, new Complex(1.0, 0.0) );
        # return null;
        # }
        Ap2s = cls.getAMatrix(order)
        Ap2s.invert()
        return Ap2s

    L = None

    @classmethod
    def index(cls, i, j):
        return ((j - 1) * cls.L) + i

    @classmethod
    def ETKInvert(cls, A, norder, error):
        """Matrix= reference to matrix of doulbes
          Norder = order of matrix  (assumed square)
          Error 	= 0 if no error;
                 = 1 if not enough heap to alloc temp array
                 = 2 if matrix can't be inverted

        This routine will invert a non-symmetric matrix.  Index is assumed to
        follow the FORTRAN standard, not the Pascal standard.  That is the data
        are ordered by first subscript first, then second subscript.  This routine
        computes its own indexing, leaving nothing to the whims of a cantankerous compiler.

        It assumes that the matrix is dimensioned to exactly the number of elements
        needed.  Apologies to Fortran users who are accustomed to over dimensioning
        stuff.
        """
        # TODO Check zero based indexing
        cls.L = norder
        error.setValue(0)
        # Allocate LT
        LT = [None] * cls.L
        if len(LT) == 0:
            error.setValue(1)
            return
        # Zero LT
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < cls.L):
                break
            LT[j] = 0
        T1 = 0.0
        # M Loop
        # initialize a safe value of k
        k = 1
        _1 = True
        M = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                M += 1
            if not (M < cls.L):
                break
            _2 = True
            LL = 0
            while True:
                if _2 is True:
                    _2 = False
                else:
                    LL += 1
                if not (LL < cls.L):
                    break
                if LT[LL] != 1:
                    RMY = cls.Math.abs(A[cls.index(LL, LL)]) - cls.Math.abs(T1)
                    if RMY > 0.0:
                        T1 = A[cls.index(LL, LL)]
                        k = LL
            # Error Check. If RMY ends up zero, matrix is non-inversible
            RMY = cls.Math.abs(T1)
            if RMY == 0.0:
                error.setValue(2)
                return
            T1 = 0.0
            LT[k] = 1
            _3 = True
            i = 0
            while True:
                if _3 is True:
                    _3 = False
                else:
                    i += 1
                if not (i < cls.L):
                    break
                if i != k:
                    _4 = True
                    j = 0
                    while True:
                        if _4 is True:
                            _4 = False
                        else:
                            j += 1
                        if not (j < cls.L):
                            break
                        if j != k:
                            A[cls.index(i, j)] = A[cls.index(i, j)] - ((A[cls.index(i, k)] * A[cls.index(k, j)]) / A[cls.index(k, k)])
            A[cls.index(k, k)] = -1.0 / A[cls.index(k, k)]
            _5 = True
            i = 0
            while True:
                if _5 is True:
                    _5 = False
                else:
                    i += 1
                if not (i < cls.L):
                    break
                if i != k:
                    A[cls.index(i, k)] = A[cls.index(i, k)] * A[cls.index(k, k)]
                    A[cls.index(k, i)] = A[cls.index(k, i)] * A[cls.index(k, k)]
        _6 = True
        j = 0
        while True:
            if _6 is True:
                _6 = False
            else:
                j += 1
            if not (j < cls.L):
                break
            _7 = True
            k = 0
            while True:
                if _7 is True:
                    _7 = False
                else:
                    k += 1
                if not (k < cls.L):
                    break
                A[cls.index(j, k)] = -A[cls.index(j, k)]
        LT = None

    @classmethod
    def phase2SymComp(cls, *args):
        _0 = args
        _1 = len(args)
        if _1 == 2:
            Vph, V012 = _0
            cls.Ap2s.vMult(V012, Vph)
            Vph, V012 = _0
            cls.phase2SymComp([Vph], V012)
        else:
            raise ARGERROR(2, 2)

    @classmethod
    def symComp2Phase(cls, Vph, V012):
        cls.As2p.vMult(Vph, V012)

    @classmethod
    def terminalPowerIn(cls, V, I, nPhases):
        """Computes total complex power given terminal voltages and currents."""
        result = Complex.ZERO
        _0 = True
        j = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < nPhases):
                break
            result = result.add(V[j].multiply(I[j].conjugate()))
        return result

    @classmethod
    def calckPowers(cls, *args):
        """Compute complex power in kW and kvar in each phase."""
        _0 = args
        _1 = len(args)
        if _1 == 4:
            kWkVAr, V, I, n = _0
            _0 = True
            j = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    j += 1
                if not (j < n):
                    break
                kWkVAr[j] = V[j].multiply(I[j].conjugate()).multiply(0.001)
            kWkVAr, V, I, n = _0
            cls.calckPowers(kWkVAr, V, [I], n)
        else:
            raise ARGERROR(4, 4)

    @classmethod
    def gauss(cls, mean, stdDev):
        """Returns a normally distributed random variable."""
        A = 0.0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < 12):
                break
            A = A + cls.Math.random()
        return ((A - 6.0) * stdDev) + mean

    @classmethod
    def quasiLognormal(cls, mean):
        """Generates a quasi-lognormal distribution with approx 50% of values
        from 0 to Mean and the remainder from Mean to infinity.
        """
        return cls.Math.exp(cls.gauss(0.0, 1.0)) * mean

    @classmethod
    def sum(cls, data, count):
        sum = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < count):
                break
            sum += data[i]
        return sum

    @classmethod
    def RCDMeanandStdDev(cls, pData, nData, mean, stdDev):
        data = [None] * 100
        data = pData
        # make a double pointer
        if nData == 1:
            mean.setValue(data[0])
            stdDev.setValue(data[0])
            return
        mean.setValue(cls.sum(data, nData) / nData)
        S = 0
        # sum differences from the mean, for greater accuracy
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < nData):
                break
            S = S + cls.Math.pow(mean.doubleValue() - data[i], 2)
        stdDev.setValue(cls.Math.sqrt(S / (nData - 1)))

    @classmethod
    def curveMeanAndStdDev(cls, pY, pX, N, mean, stdDev):
        if N == 1:
            mean.setValue(pY[0])
            stdDev.setValue(pY[0])
            return
        s = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < N - 1):
                break
            s += 0.5 * (pY[i] + pY[i + 1]) * (pX[i + 1] - pX[i])
        mean.setValue(s / (pX[N] - pX[0]))
        # TODO Check zero based indexing
        s = 0
        # sum differences from the mean, for greater accuracy
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < N - 1):
                break
            dy1 = pY[i] - mean.doubleValue()
            dy2 = pY[i + 1] - mean.doubleValue()
            s += 0.5 * ((dy1 * dy1) + (dy2 * dy2)) * (pX[i + 1] - pX[i])
        stdDev.setValue(cls.Math.sqrt(s / (pX[N] - pX[0])))

    @classmethod
    def parallelZ(cls, Z1, Z2):
        """Parallel two complex impedances."""
        denom = Z1.add(Z2)
        if (
            (cls.Math.abs(denom.getReal()) > 0.0) or (cls.Math.abs(denom.getImaginary()) > 0.0)
        ):
            return Z1.multiply(Z2).divide(denom)
        else:
            # Error
            return Complex.ZERO

    @classmethod
    def Bessel_I0(cls, a):
        """z = I0(a)"""
        maxTerm = 1000
        epsilonSqr = 0.9999999999999999e-20
        sizeSqr = 1
        result = Complex.ONE
        # term 0
        zSQR25 = a.multiply(a).multiply(0.25)
        term = zSQR25
        result = result.add(zSQR25)
        # term 1
        i = 0
        while i < maxTerm and sizeSqr > epsilonSqr:
            term = zSQR25.multiply(term)
            i += 1
            term = ComplexUtil.divide(term, cls.Math.pow(i, 2))
            result = result.add(term)
            # sum = sum + term
            sizeSqr = cls.Math.pow(term.getReal(), 2) + cls.Math.pow(term.getImaginary(), 2)
        return result

    @classmethod
    def pctNemaUnbalance(cls, Vph):
        """@return Nema unbalance"""
        VMag = [None] * 3
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < 3):
                break
            VMag[i] = Vph[i].abs()
        VAvg = 0.0
        _1 = True
        i = 0
        while True:
            if _1 is True:
                _1 = False
            else:
                i += 1
            if not (i < 3):
                break
            VAvg = VAvg + VMag[i]
        VAvg = VAvg / 3.0
        maxDiff = 0.0
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < 3):
                break
            maxDiff = cls.Math.max(maxDiff, cls.Math.abs(VMag[i] - VAvg))
        if VAvg != 0.0:
            return (maxDiff / VAvg) * 100.0
            # pct difference
        else:
            return 0

    @classmethod
    def getXR(cls, a):
        if a.getReal() != 0.0:
            result = a.getImaginary() / a.getReal()
            if cls.Math.abs(result) > 9999.0:
                result = 9999.0
        else:
            result = 9999.0
        return result

    @classmethod
    def sqr(cls, a):
        return cls.Math.pow(a, 2)
