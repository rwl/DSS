package com.epri.dss.shared;

import com.epri.dss.shared.impl.Complex;

public interface CMatrix {

	int getInvertError();

	void setInvertError(int invertError);
	
	int getNOrder();
	
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
	
	/* b = Ax */
	void MVMult(Complex[] b, Complex[] x);
	
	/* b = Ax */
	void MVMultAccum(Complex[] b, Complex[] x);
	
	Complex[] asArray(int Order);
	
	void zeroRow(int iRow);
	
	void zeroCol(int iCol);
	
	/* Average of Diagonal Elements */
	Complex avgDiagonal();
	
	Complex avgOffDiagonal();
	
	/* Multiply all elements by a constant */
	void multByConst(double x);

	/* Perform Kron reduction on last row/col and return new matrix */
	CMatrix kron(int eliminationRow);

}
