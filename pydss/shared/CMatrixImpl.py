from __pyjamas__ import (ARGERROR,)
from dss.shared.CMatrix import (CMatrix,)
from dss.shared.impl.ComplexUtil import (ComplexUtil,)
# from org.apache.commons.lang.mutable.MutableInt import (MutableInt,)
# from org.apache.commons.math.complex.Complex import (Complex,)


class CMatrixImpl(object, CMatrix):
    nOrder = None
    values = None
    invertError = None

    def __init__(self, n):
        self.nOrder = n
        self.invertError = 0
        self.values = [None] * n * n
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < n * n):
                break
            self.values[i] = Complex.ZERO

    def order(self):
        return self.nOrder

    def setElement(self, i, j, value):
        self.values[(j * self.nOrder) + i] = value

    def setElemSym(self, i, j, value):
        self.values[(j * self.nOrder) + i] = value
        if i != j:
            self.values[(i * self.nOrder) + j] = value
            # ensure symmetry

    def addElement(self, i, j, value):
        self.values[(j * self.nOrder) + i] = self.values[(j * self.nOrder) + i].add(value)

    def addElemSym(self, i, j, value):
        self.values[(j * self.nOrder) + i] = self.values[(j * self.nOrder) + i].add(value)
        if i != j:
            self.values[(i * self.nOrder) + j] = self.values[(i * self.nOrder) + j].add(value)
            # ensure symmetry

    def getElement(self, i, j):
        return self.values[(j * self.nOrder) + i]

    def getErrorCode(self):
        return self.invertError

    def clear(self):
        """Zero out matrix"""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder * self.nOrder):
                break
            self.values[i] = Complex.ZERO

    def vMult(self, b, x):
        """b = Ax"""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder):
                break
            sum = Complex.ZERO
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.nOrder):
                    break
                sum = sum.add(self.values[((j - 1) * self.nOrder) + 1].multiply(x[j]))
            b[i] = sum

    def vMultAccum(self, b, x):
        """b = Ax

        Same as MVMult except accumulates b.
        """
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder):
                break
            sum = Complex.ZERO
            _1 = True
            j = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.nOrder):
                    break
                sum = sum.add(self.values[((j - 1) * self.nOrder) + 1].multiply(x[j]))
            b[i] = b[i].add(sum)

    def addFrom(self, otherMatrix):
        if self.nOrder == otherMatrix.order():
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nOrder):
                    break
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < self.nOrder):
                        break
                    self.addElement(i, j, otherMatrix.getElement(i, j))

    def copyFrom(self, otherMatrix):
        if self.nOrder == otherMatrix.order():
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nOrder):
                    break
                _1 = True
                j = 0
                while True:
                    if _1 is True:
                        _1 = False
                    else:
                        j += 1
                    if not (j < self.nOrder):
                        break
                    self.setElement(i, j, otherMatrix.getElement(i, j))

    def sumBlock(self, row1, row2, col1, col2):
        """Sum all elements in a given block of the matrix."""
        sum = Complex.ZERO
        _0 = True
        j = col1
        while True:
            if _0 is True:
                _0 = False
            else:
                j += 1
            if not (j < col2):
                break
            rowstart = j * self.nOrder
            _1 = True
            i = rowstart + row1
            while True:
                if _1 is True:
                    _1 = False
                else:
                    i += 1
                if not (i < rowstart + row2):
                    break
                sum = sum.add(self.values[i])
        return sum

    def asArray(self, *args):
        _0 = args
        _1 = len(args)
        if _1 == 0:
            return self.values
        elif _1 == 1:
            order, = _0
            order.setValue(self.nOrder)
            return self.values
        else:
            raise ARGERROR(0, 1)

    def zeroRow(self, iRow):
        j = iRow
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder):
                break
            self.values[j] = Complex.ZERO
            j += self.nOrder

    def zeroCol(self, iCol):
        _0 = True
        i = (iCol - 1) * self.nOrder
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < iCol * self.nOrder):
                break
            self.values[i] = Complex.ZERO

    def avgDiagonal(self):
        """Average of diagonal elements"""
        result = Complex.ZERO
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder):
                break
            result = result.add(self.values[((i - 1) * self.nOrder) + i])
        if self.nOrder > 0:
            result = ComplexUtil.divide(result, self.nOrder)
        return result

    def avgOffDiagonal(self):
        """Average the upper triangle off diagonals."""
        result = Complex.ZERO
        nTimes = 0
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder):
                break
            _1 = True
            j = i + 1
            while True:
                if _1 is True:
                    _1 = False
                else:
                    j += 1
                if not (j < self.nOrder):
                    break
                nTimes += 1
                result = result.add(self.values[((j - 1) * self.nOrder) + i])
        if nTimes > 0:
            result = ComplexUtil.divide(result, nTimes)
        return result

    def multByConst(self, x):
        """Multiply all elements by a constant"""
        _0 = True
        i = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                i += 1
            if not (i < self.nOrder * self.nOrder):
                break
            self.values[i] = self.values[i].multiply(x)

    @classmethod
    def index(cls, i, j, L):
        return ((j - 1) * L) + i

    def invert(self):
        L = self.nOrder
        self.invertError = 0
        A = self.values
        # Assign pointer to something we can use
        # Allocate LT
        # LT = null;
        LT = [None] * L
        # if (LT == null) {  // FIXME: Handle out of memory
        # invertError = 1;
        # return;
        # }
        # Zero LT
        # for (j = 0; j < L; j++)
        # LT[j] = 0;
        T1 = Complex.ZERO
        k = 1
        # TODO: Check zero indexing.
        # M Loop
        _0 = True
        m = 0
        while True:
            if _0 is True:
                _0 = False
            else:
                m += 1
            if not (m < L):
                break
            _1 = True
            LL = 0
            while True:
                if _1 is True:
                    _1 = False
                else:
                    LL += 1
                if not (LL < L):
                    break
                if LT[LL] != 0:
                    # TODO: Check zero indexing.
                    RMY = A[self.index(LL, LL, L)].abs() - T1.abs()
                    # Will this work??
                    if RMY > 0:
                        T1 = A[self.index(LL, LL, L)]
                        k = LL
        # Error check. If RMY ends up zero, matrix is non-inversible
        RMY = T1.abs()
        if RMY == 0.0:
            self.invertError = 2
            return
        T1 = Complex.ZERO
        LT[k] = 1
        # TODO: Check zero indexing.
        _2 = True
        i = 0
        while True:
            if _2 is True:
                _2 = False
            else:
                i += 1
            if not (i < L):
                break
            if i != k:
                _3 = True
                j = 0
                while True:
                    if _3 is True:
                        _3 = False
                    else:
                        j += 1
                    if not (j < L):
                        break
                    if j != k:
                        A[self.index(i, j, L)] = A[self.index(i, j, L)].subtract(A[self.index(i, k, L)].multiply(A[self.index(k, j, L)]).divide(A[self.index(k, k, L)]))
        # Invert and negate k, k element
        A[self.index(k, k, L)] = Complex.ONE.divide(A[self.index(k, k, L)]).negate()
        _4 = True
        i = 0
        while True:
            if _4 is True:
                _4 = False
            else:
                i += 1
            if not (i < L):
                break
            if i != k:
                A[self.index(i, k, L)] = A[self.index(i, k, L)].multiply(A[self.index(k, k, L)])
                A[self.index(k, i, L)] = A[self.index(k, i, L)].multiply(A[self.index(k, k, L)])
        # M loop
        _5 = True
        j = 0
        while True:
            if _5 is True:
                _5 = False
            else:
                j += 1
            if not (j < L):
                break
            _6 = True
            k = 0
            while True:
                if _6 is True:
                    _6 = False
                else:
                    k += 1
                if not (k < L):
                    break
                A[self.index(j, k, L)] = A[self.index(j, k, L)].negate()

    def kron(self, eliminationRow):
        """Perform Kron reduction on last row/col and return new matrix"""
        result = None
        # null result means it failed
        if self.nOrder > 1 and eliminationRow <= self.nOrder and eliminationRow > 0:
            result = CMatrixImpl(self.nOrder - 1)
            N = eliminationRow
            NNElement = self.getElement(N, N)
            ii = 0
            _0 = True
            i = 0
            while True:
                if _0 is True:
                    _0 = False
                else:
                    i += 1
                if not (i < self.nOrder):
                    break
                if i != N:
                    ii += 1
                    jj = 0
                    _1 = True
                    j = 0
                    while True:
                        if _1 is True:
                            _1 = False
                        else:
                            j += 1
                        if not (j < self.nOrder):
                            break
                        if j != N:
                            jj += 1
                            result.setElement(ii, jj, self.getElement(i, j).subtract(self.getElement(i, N).multiply(self.getElement(N, j)).divide(NNElement)))
        return result
