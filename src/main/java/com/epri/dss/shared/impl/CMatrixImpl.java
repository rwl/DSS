package com.epri.dss.shared.impl;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.CMatrix;

public class CMatrixImpl implements CMatrix {
	
	private int nOrder;
	private Complex[] values;
	
	protected int invertError;

	public CMatrixImpl(int n) {
	}

	public int getInvertError() {
		return invertError;
	}

	public void setInvertError(int invertError) {
		this.invertError = invertError;
	}
	
	public int getnOrder() {
		return nOrder;
	}
	
	public void invert() {
		
	}

	/* Zero out matrix */
	public void clear() {
		
	}
	
	public void addFrom(CMatrix otherMatrix) {
		
	}
	
	public void CopyFrom(CMatrix otherMatrix) {
		
	}
	
	public void setElement(int i, int j, Complex Value) {
		
	}
	
	public void setElemSym(int i, int j, Complex Value) {
		
	}
	
	public void addElement(int i, int j, Complex Value) {
		
	}
	
	public void addElemSym(int i, int j, Complex Value) {
		
	}
	
	public Complex getElement(int i, int j) {
		return null;
	}
	
	public int getErrorCode() {
		return 0;
	}
	
	public Complex sumBlock(int row1, int row2, int col1, int col2) {
		return null;
	}
	
	/* b = Ax */
	public void MVmult(Complex[] b, Complex[] x) {
		
	}
	
	/* b = Ax */
	public void MVmultAccum(Complex[] b, Complex[] x) {
		
	}
	
	public Complex[] asArray(int Order) {
		return null;
	}
	
	public void zeroRow(int iRow) {
		
	}
	
	public void zeroCol(int iCol) {
		
	}
	
	/* Average of Diagonal Elements */
	public Complex avgDiagonal() {
		return null;
	}
	
	public Complex avgOffDiagonal() {
		return null;
	}
	
	/* Multiply all elements by a constant */
	public void multByConst(double x) {
		
	}

	/* Perform Kron reduction on last row/col and return new matrix */
	public CMatrix Kron(int eliminationRow) {
		return null;
	}

}
