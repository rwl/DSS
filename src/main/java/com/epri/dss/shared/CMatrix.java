package com.epri.dss.shared;

import org.apache.commons.lang.mutable.MutableInt;
import org.apache.commons.math.complex.Complex;

public interface CMatrix {

	int order();

	void invert();

	/* Zero out matrix */
	void clear();

	void addFrom(CMatrix otherMatrix);

	void copyFrom(CMatrix otherMatrix);

	void setElement(int i, int j, Complex Value);

	void setElemSym(int i, int j, Complex Value);

	void addElement(int i, int j, Complex Value);

	void addElemSym(int i, int j, Complex Value);

	Complex getElement(int i, int j);

	int getErrorCode();

	Complex sumBlock(int row1, int row2, int col1, int col2);

	/** b = Ax */
	void vMult(Complex[] b, Complex[] x);

	/** b = Ax */
	void vMultAccum(Complex[] b, Complex[] x);

	Complex[] asArray(MutableInt Order);

	Complex[] asArray();

	void zeroRow(int iRow);

	void zeroCol(int iCol);

	/** Average of diagonal elements */
	Complex avgDiagonal();

	Complex avgOffDiagonal();

	/** Multiply all elements by a constant */
	void multByConst(double x);

	/** Perform Kron reduction on last row/col and return new matrix */
	CMatrix kron(int eliminationRow);

}
