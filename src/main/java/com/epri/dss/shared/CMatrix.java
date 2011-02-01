package com.epri.dss.shared;

import org.apache.commons.math.complex.Complex;

public interface CMatrix {

	int getInvertError();

	void setInvertError(int invertError);
	
	int getnOrder();
	
	void invert();

	/* Zero out matrix */
	void clear();
	
	void addFrom(CMatrix otherMatrix);
	
	void CopyFrom(CMatrix otherMatrix);
	
	void setElement(int i, int j, Complex Value);
	
	void setElemSym(int i, int j, Complex Value);
	
	void addElement(int i, int j, Complex Value);
	
	void addElemSym(int i, int j, Complex Value);
	
	Complex getElement(int i, int j);
	
	int getErrorCode();
	
	Complex sumBlock(int row1, int row2, int col1, int col2);
	
	/* b = Ax */
	void MVmult(Complex[] b, Complex[] x);
	
	/* b = Ax */
	void MVmultAccum(Complex[] b, Complex[] x);
	
	Complex[] asArray(int Order);
	
	void zeroRow(int iRow);
	
	void zeroCol(int iCol);
	
	/* Average of Diagonal Elements */
	Complex avgDiagonal();
	
	Complex avgOffDiagonal();
	
	/* Multiply all elements by a constant */
	void multByConst(double x);

	/* Perform Kron reduction on last row/col and return new matrix */
	CMatrix Kron(int eliminationRow);

}
