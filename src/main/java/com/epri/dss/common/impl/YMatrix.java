package com.epri.dss.common.impl;

import java.util.ArrayList;
import java.util.List;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSBus.NodeBus;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

public class YMatrix {

	/* Options for building Y matrix */
	public static final int SERIESONLY = 1;
	public static final int WHOLEMATRIX = 2;

	private YMatrix() {
	}

	private static void reCalcAllYPrims() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt.isLogEvents())
			Utilities.logThisEvent("Recalc All Yprims");
		for (CktElement pElem : ckt.getCktElements())
			pElem.calcYPrim();
	}

	/**
	 * Recalc YPrims only for those circuit elements that have had changes
	 * since last solution.
	 */
	private static void reCalcInvalidYPrims() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		if (ckt.isLogEvents())
			Utilities.logThisEvent("Recalc Invalid Yprims");
		for (CktElement pElem : ckt.getCktElements())
			if (pElem.isYprimInvalid())
				pElem.calcYPrim();
	}

	public static void resetSparseMatrix(long Y, int size) throws Esolv32Problem {
		if (Y != 0) {
			if (deleteSparseSet(Y) < 1)  // Get rid of existing one beFore making a new one
				throw new Esolv32Problem("Error Deleting System Y Matrix in ResetSparseMatrix. Problem with sparse matrix solver.");
			Y = 0;
		}

		// Make a new sparse set
		Y = newSparseSet(size);
		if (Y < 1)  // Raise and exception  TODO Check zero based indexing
			throw new Esolv32Problem("Error Creating System Y Matrix. Problem WITH Sparse matrix solver.");
	}

	public static void initializeNodeVbase() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		for (int i = 0; i < ckt.getNumNodes(); i++) {
			NodeBus nb = ckt.getMapNodeToBus()[i];
			sol.getNodeVbase()[i] = ckt.getBuses()[nb.BusRef].getkVBase() * 1000.0;
			sol.setVoltageBaseChanged(false);
		}
	}

	/**
	 * Builds designated Y matrix for system and allocates solution arrays.
	 * @throws Esolv32Problem
	 */
	public static void buildYMatrix(int BuildOption, boolean AllocateVI) throws Esolv32Problem {
		int YMatrixSize;
		Complex[] CmatArray;

		CmatArray = null;
		// new function to log KLUSolve.DLL function calls
		//setLogFile("KLU_Log.txt", 1);

		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();
		SolutionObj sol = ckt.getSolution();

		if (sol.isPreserveNodeVoltages())
			sol.updateVBus();  // Update voltage values stored with Bus object

		// the following re counts the number of buses and resets meter zones and feeders
		// If radial but systemNodeMap not set then init for radial got skipped due to script sequence
		if (ckt.isBusNameRedefined())
			ckt.reProcessBusDefs();  // This changes the node references into the system Y matrix!!

		YMatrixSize = ckt.getNumNodes();

		switch (BuildOption) {
		case WHOLEMATRIX:
			resetSparseMatrix(sol.getYsystem(), YMatrixSize);
			sol.setY(sol.getYsystem());
		case SERIESONLY:
			resetSparseMatrix(sol.getYseries(), YMatrixSize);
			sol.setY(sol.getYseries());
		}

		// tune up the Yprims if necessary
		if (sol.isFrequencyChanged()) {
			reCalcAllYPrims();
		} else {
			reCalcInvalidYPrims();
		}

		if (Globals.isSolutionAbort()) {
			Globals.doSimpleMsg("Y matrix build aborted due to error in primitive Y calculations.", 11001);
			return;  // Some problem occured building Yprims
		}

		sol.setFrequencyChanged(false);

		if (ckt.isLogEvents())
			switch (BuildOption) {
			case WHOLEMATRIX:
				Utilities.logThisEvent("Building Whole Y Matrix");
			case SERIESONLY:
				Utilities.logThisEvent("Building Series Y Matrix");
			}

		// Add in Yprims for all devices
		for (CktElement pElem : ckt.getCktElements()) {
			if (pElem.isEnabled()) {
				switch (BuildOption) {
				case WHOLEMATRIX:
					CmatArray = pElem.getYPrimValues(DSSGlobals.ALL_YPRIM);
				case SERIESONLY:
					CmatArray = pElem.getYPrimValues(DSSGlobals.SERIES);
				}
				// new function adding primitive Y matrix to KLU system Y matrix
				if (CmatArray != null)
					if (addPrimitiveMatrix(sol.getY(), pElem.getYorder(), pElem.getNodeRef()[0], CmatArray[0]) < 0)  // TODO Check zero based indexing
						throw new Esolv32Problem("Node index out of range adding to System Y Matrix");
			}  // If Enabled
		}

		// Allocate voltage and current vectors if requested
		if (AllocateVI) {
			if (ckt.isLogEvents())
				Utilities.logThisEvent("ReAllocating Solution Arrays");
			sol.setNodeV( (Complex[]) Utilities.resizeArray(sol.getNodeV(), ckt.getNumNodes() + 1) );  // Allocate System Voltage array - allow for zero element
			sol.getNodeV()[0] = Complex.ZERO;  // TODO Check zero based indexing
			sol.setCurrents( (Complex[]) Utilities.resizeArray(sol.getCurrents(), ckt.getNumNodes() + 1) );  // Allocate System current array
			sol.setAuxCurrents( (Complex[]) Utilities.resizeArray(sol.getAuxCurrents(), ckt.getNumNodes()) );  // Allocate System current array
			if (sol.getVmagSaved() != null)
				sol.setVmagSaved(new double[0]);
			if (sol.getErrorSaved() != null)
				sol.setErrorSaved(new double[0]);
			if (sol.getNodeVbase() != null)
				sol.setNodeVbase(new double[0]);
			sol.setVmagSaved(new double[ckt.getNumNodes()]);   // zero fill
			sol.setErrorSaved(new double[ckt.getNumNodes()]);  // zero fill
			sol.setNodeVbase(new double[ckt.getNumNodes()]);   // zero fill
			initializeNodeVbase();
		}

		switch (BuildOption) {
		case WHOLEMATRIX:
			sol.setSeriesYInvalid(true);  // Indicate that the Series matrix may not match
			sol.setSystemYChanged(false);
		case SERIESONLY:
			sol.setSeriesYInvalid(false);  // SystemYChange unchanged
		}

		// Deleted RCD only done now on mode change
		//sol.setSolutionInitialized(false);  //Require initialization of voltages if Y changed

		if (sol.isPreserveNodeVoltages())
			sol.restoreNodeVfromVbus();

	}

	private static int addPrimitiveMatrix(CMatrix y, int yorder, int nodes, Complex mat) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void resetSparseMatrix(CMatrix ysystem, int yMatrixSize) {
		// TODO Auto-generated method stub

	}

	/**
	 * Leave the call to getMatrixElement, but add more diagnostics.
	 */
	public static String checkYMatrixforZeroes() {
		Complex c = null;
		CMatrix Y;
		int sCol = 0;
		long nIslands, iCount, iFirst;
		List<Long> Cliques;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		String Result = "";

		Y = ckt.getSolution().getY();
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			getMatrixElement(Y, i, i, c);
			if (c.abs() == 0.0) {
				NodeBus nb = ckt.getMapNodeToBus()[i];
				Result += String.format("%sZero diagonal for bus %s, node %d", DSSGlobals.CRLF, ckt.getBusList().get(nb.BusRef), nb.NodeNum);
			}
		}

		// new diagnostics
		getSingularCol(Y, sCol);  // returns a 0-based node number  TODO Check zero based indexing
		if (sCol >= 0) {
			NodeBus nb = ckt.getMapNodeToBus()[sCol];
			Result += String.format("%sMatrix singularity at bus %s, node %d", DSSGlobals.CRLF, ckt.getBusList().get(nb.BusRef), sCol);
		}

		Cliques = new ArrayList<Long>(ckt.getNumNodes());  // TODO Check translation
		nIslands = findIslands(Y, ckt.getNumNodes(), Cliques.get(0));
		if (nIslands > 1) {
			Result += String.format("%sFound %d electrical islands:", DSSGlobals.CRLF, nIslands);
			for (int i = 0; i < nIslands; i++) {
				iCount = 0;
				iFirst = 0;
				for (int p = 0; p < ckt.getNumNodes(); p++) {
					if (Cliques.get(p) == i) {
						iCount += 1;
						if (iFirst == 0)
							iFirst = p + 1;
					}
				}
				NodeBus nb = ckt.getMapNodeToBus()[(int) iFirst];
				Result += String.format("%s  #%d has %d nodes, including bus %s (node %d)", DSSGlobals.CRLF, i, iCount, ckt.getBusList().get(nb.BusRef), iFirst);
			}
		}

		return Result;
	}

	// in general, KLU arrays are 0-based
	// function calls return 0 to indicate failure, 1 for success

	private static long findIslands(CMatrix y, int numNodes, Long pNodes) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void getSingularCol(CMatrix y, long sCol) {
		// TODO Auto-generated method stub

	}

	private static void getMatrixElement(CMatrix y, int i, int i2, Complex c) {
		// TODO Auto-generated method stub

	}

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

	public static int solveSparseSet(CMatrix ysystem, Complex complex, Complex complex2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void setLogFile(String string, int action) {
		// TODO Auto-generated method stub

	}

	public static void getRCond(CMatrix y, double dRes) {
		// TODO Auto-generated method stub

	}

	public static void getNNZ(CMatrix y, long iRes) {
		// TODO Auto-generated method stub

	}

	public static void getSparseNNZ(CMatrix y, long iRes) {
		// TODO Auto-generated method stub

	}

}
