package com.epri.dss.common.impl;

import com.epri.dss.shared.impl.Complex;

public class YMatrix {
	
	/* Options for building Y matrix */
	public static int SERIESONLY = 1;
	public static int WHOLEMATRIX = 2;

	private YMatrix() {
	}
	
	public static void buildYMatrix(int BuildOption, boolean AllocateVI) {
		
	}
	
	public static void resetSparseMatrix(long Y, int size) {
		
	}
	
	public static void initializeNodeVbase() {
		
	}

	public static String checkYMatrixforZeroes() {
		return null;
	}
	
	// in general, KLU arrays are 0-based
	// function calls return 0 to indicate failure, 1 for success

	// returns the non-zero handle of a new sparse matrix, if successful
	// must call DeleteSparseSet on the valid handle when finished
	public static long newSparseSet(long nBus) {
		return 0;
	}

	// return 1 for success, 0 for invalid handle
	public static long deleteSparseSet(long id) {
		return 0;
	}

	// return 1 for success, 2 for singular, 0 for invalid handle
	// factors matrix if needed
	public static long solveSparseSet(long id, Complex[] x, Complex[] b) {
		return 0;
	}

	// return 1 for success, 0 for invalid handle
	public static long zeroSparseSet(long id) {
		return 0;
	}

	// return 1 for success, 2 for singular, 0 for invalid handle
	// FactorSparseMatrix does no extra work if the factoring was done previously
	public static long factorSparseMatrix(long id) {
		return 0;
	}

	// These "Get" functions for matrix information all return 1 for success, 0 for invalid handle
	// Res is the matrix order (number of nodes)
	public static long getSize(long id, long Res) {
		return 0;
	}

	// the following function results are not known prior to factoring
	// Res is the number of floating point operations to factor
	public static long getFlops(long id, double Res) {
		return 0;
	}
	
	// Res is number of non-zero entries in the original matrix
	public static long getNNZ(long id, long Res) {
		return 0;
	}
	
	// Res is the number of non-zero entries in factored matrix
	public static long getSparseNNZ(long id, long Res) {
		return 0;
	}
	
	// Res is a column number corresponding to a singularity, or 0 if not singular
	public static long getSingularCol(long id, long Res) {
		return 0;
	}
	
	// Res is the pivot element growth factor
	public static long getRGrowth(long id, double Res) {
		return 0;
	}
	
	// Res is aquick estimate of the reciprocal of condition number
	public static long getRCond(long id, double Res) {
		return 0;
	}
	
	// Res is a more accurate estimate of condition number
	public static long getCondEst(long id, double Res) {
		return 0;
	}

	// return 1 for success, 0 for invalid handle or a node number out of range
	public static long addPrimitiveMatrix(long id, long nOrder, long Nodes, Complex Mat) {
		return 0;
	}

	// Action = 0 (close), 1 (rewrite) or 2 (append)
	public static long setLogFile(char Path, long Action) {
		return 0;
	}

	// fill sparse matrix in compressed column form
	// return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	// pColP must be of length nColP == nBus + 1
	// pRowIdx and pMat of length nNZ, which
//	    must be at least the value returned by GetNNZ
	public static long getCompressedMatrix(long id, long nColP, long nNZ, long pColP, long pRowIdx, Complex Mat) {
		return 0;
	}

	// fill sparse matrix in triplet form
	// return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	// pRows, pCols, and Mat must all be of length nNZ
	public static long getTripletMatrix(long id, long nNZ, long pRows, long pCols, Complex Mat) {
		return 0;
	}

	// returns number of islands >= 1 by graph traversal
	// pNodes contains the island number for each node
	public static long findIslands(long id, long nOrder, long pNodes) {
		return 0;
	}

	// AddMatrixElement is deprecated, use AddPrimitiveMatrix instead
	public static long addMatrixElement(long id, long i, long j, Complex Value) {
		return 0;
	}

	// GetMatrixElement is deprecated, use GetCompressedMatrix or GetTripletMatrix
	public static long getMatrixElement(long id, long i, long j, Complex Value) {
		return 0;
	}

}
