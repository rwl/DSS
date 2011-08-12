package com.epri.dss.shared.impl;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.shared.CMatrix;

public class CMatrixImpl implements CMatrix {

	private int nOrder;
	private Complex[] values;

	protected int invertError;

	public CMatrixImpl(int n) {
		this.nOrder = n;
		this.invertError = 0;
		this.values = new Complex[n * n];
		for (int i = 0; i < n * n; i++)
			this.values[i] = Complex.ZERO;
	}

	/**
	 * Zero out matrix
	 */
	public void clear() {
		for (int i = 0; i < nOrder * nOrder; i++)
			values[i] = Complex.ZERO;
	}

	/**
	 * b = Ax
	 */
	public void MVMult(Complex[] b, Complex[] x) {
		Complex Sum;
		for (int i = 0; i < nOrder; i++) {
			Sum = Complex.ZERO;
			for (int j = 0; j < nOrder; j++)
				Sum = Sum.add( values[((j - 1) * nOrder + 1)].multiply(x[j]) );
			b[i] = Sum;
		}
	}

	/**
	 * b = Ax
	 *
	 * Same as MVMult except accumulates b.
	 */
	public void MVMultAccum(Complex[] b, Complex[] x) {
		Complex Sum;
		for (int i = 0; i < nOrder; i++) {
			Sum = Complex.ZERO;
			for (int j = 0; j < nOrder; j++)
				Sum = Sum.add( values[((j - 1) * nOrder + 1)].multiply(x[j]) );
			b[i] = b[i].add(Sum);
		}
	}

	private static int index(int i, int j, int L) {
		return (j - 1) * L + i;
	}
	public void invert() {
		int j, k, L, LL, m, i;
		int[] LT;
		double RMY;
		Complex T1;
		Complex[] A;

		L = nOrder;
		invertError = 0;

		A = values;  /* Assign pointer to something we can use */

		/* Allocate LT */
//        LT = null;
		LT = new int[L];
//		if (LT == null) {  // FIXME: Handle out of memory
//			invertError = 1;
//			return;
//		}

		/* Zero LT */
//		for (j = 0; j < L; j++)
//			LT[j] = 0;

		T1 = Complex.ZERO;
		k = 1; // TODO: Check zero indexing.

		/* M Loop */
		for (m = 0; m < L; m++) {
			for (LL = 0; LL < L; LL++) {
				if (LT[LL] != 0) {  // TODO: Check zero indexing.
					RMY = A[index(LL, LL, L)].abs() - T1.abs();  // Will this work??
					if (RMY > 0) {
						T1 = A[index(LL, LL, L)];
						k = LL;
					}
				}
			}
		}

		/* Error check. If RMY ends up zero, matrix is non-inversible */
		RMY = T1.abs();
		if (RMY == 0.0) {
			invertError = 2;
			return;
		}

		T1 = Complex.ZERO;
		LT[k] = 1; // TODO: Check zero indexing.
		for (i = 0; i < L; i++) {
			if (i != k) {
				for (j = 0; j < L; j++) {
					if (j != k) {
						A[index(i, j, L)] =
							A[index(i, j, L)].subtract( A[index(i, k, L)].multiply(A[index(k, j, L)]).divide(A[index(k, k, L)]) );
					}
				}
			}
		}

		// Invert and negate k, k element
		A[index(k, k, L)] = Complex.ONE.divide(A[index(k, k, L)]).negate();

		for (i = 0; i < L; i++) {
			if (i != k) {
				A[index(i, k, L)] = A[index(i, k, L)].multiply(A[index(k, k, L)]);
				A[index(k, i, L)] = A[index(k, i, L)].multiply(A[index(k, k, L)]);
			}
		}  // M loop

		for (j = 0; j < L; j++)
			for (k = 0; k < L; k++)
				A[index(j, k, L)] = A[index(j, k, L)].negate();

	}

	public int getInvertError() {
		return invertError;
	}

	public void setInvertError(int invertError) {
		this.invertError = invertError;
	}

	public int getNOrder() {
		return nOrder;
	}

	public void addFrom(CMatrix otherMatrix) {
		if (nOrder == otherMatrix.getNOrder()) {
			for (int i = 0; i < nOrder; i++) {
				for (int j = 0; j < nOrder; j++) {
					addElement(i, j, otherMatrix.getElement(i, j));
				}
			}
		}
	}

	public void copyFrom(CMatrix otherMatrix) {
		if (nOrder == otherMatrix.getNOrder()) {
			for (int i = 0; i < nOrder; i++) {
				for (int j = 0; j < nOrder; j++) {
					setElement(i, j, otherMatrix.getElement(i, j));
				}
			}
		}
	}

	public void setElement(int i, int j, Complex Value) {
		values[((j - 1) * nOrder + i)] = Value;
	}

	public void setElemSym(int i, int j, Complex Value) {
		values[((j - 1) * nOrder + i)] = Value;
		if (i != j)
			values[((i - 1) * nOrder + j)] = Value;  // ensure symmetry
	}

	public void addElement(int i, int j, Complex Value) {
		values[((j - 1) * nOrder + i)] = values[((j - 1) * nOrder + i)].add(Value);
	}

	public void addElemSym(int i, int j, Complex Value) {
		values[((j - 1) * nOrder + i)] = values[((j - 1) * nOrder + i)].add(Value);
		if (i != j)
			values[((i - 1) * nOrder + j)] = values[((i - 1) * nOrder + j)].add(Value);  // ensure symmetry
	}

	public Complex getElement(int i, int j) {
		return values[((j - 1) * nOrder + i)];
	}

	public int getErrorCode() {
		return invertError;
	}

	/**
	 * Sum all elements in a given block of the matrix.
	 */
	public Complex sumBlock(int row1, int row2, int col1, int col2) {
		int rowstart;
		Complex Sum = Complex.ZERO;

		for (int j = col1; j < col2; j++) {
			rowstart = (j - 1) * nOrder;
			for (int i = rowstart + row1; i < rowstart + row2; i++) {
				Sum = Sum.add(values[i]);
			}
		}

		return Sum;
	}

	public Complex[] asArray(int order) {
		order = nOrder;
		return values;
	}

	public void zeroRow(int iRow) {
		int j = iRow;
		for (int i = 0; i < nOrder; i++) {
			values[j] = Complex.ZERO;
			j += nOrder;
		}
	}

	public void zeroCol(int iCol) {
		for (int i = (iCol - 1) * nOrder; i < iCol * nOrder; i++) {
			values[i] = Complex.ZERO;
		}
	}

	/**
	 * Average of diagonal elements
	 */
	public Complex avgDiagonal() {
		Complex Result = Complex.ZERO;
		for (int i = 0; i < nOrder; i++)
			Result = Result.add(values[((i - 1) * nOrder + i)]);

		if (nOrder > 0)
			Result = Result.divide(nOrder);

		return Result;
	}

	/**
	 * Average the upper triangle off diagonals.
	 */
	public Complex avgOffDiagonal() {
		Complex Result = Complex.ZERO;
		int nTimes = 0;
		for (int i = 0; i < nOrder; i++) {
			for (int j = i+1; j < nOrder; j++) {
				nTimes += 1;
				Result = Result.add(values[((j - 1) * nOrder + i)]);
			}
		}

		if (nTimes > 0)
			Result = Result.divide(nTimes);

		return Result;
	}

	/**
	 * Multiply all elements by a constant
	 */
	public void multByConst(double x) {
		for (int i = 0; i < nOrder * nOrder; i++)
			values[i] = values[i].multiply(x);
	}

	/**
	 * Perform Kron reduction on last row/col and return new matrix
	 */
	public CMatrix kron(int eliminationRow) {
		int ii, jj;
		CMatrix Result = null;   // Null result means it failed
		if ((nOrder > 1) && (eliminationRow <= nOrder) && (eliminationRow > 0)) {
			Result = new CMatrixImpl(nOrder - 1);
			int N = eliminationRow;
			Complex NNElement = getElement(N, N);

			ii = 0;
			for (int i = 0; i < nOrder; i++) {
				if (i != N) {
					ii += 1;
					jj = 0;
					for (int j = 0; j < nOrder; j++) {
						if (j != N) {
							jj += 1;
							Result.setElement(ii, jj,
									getElement(i, j).subtract( getElement(i, N).multiply(getElement(N, j)).divide(NNElement) )
							);
						}
					}
				}
			}
		}

		return Result;
	}

}
