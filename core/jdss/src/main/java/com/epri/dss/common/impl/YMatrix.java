package com.epri.dss.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableLong;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSBus.NodeBus;
import com.epri.dss.shared.CMatrix;
import org.apache.commons.math.complex.Complex;

public class YMatrix {

	/* Options for building Y matrix */
	public static final int SERIESONLY = 1;
	public static final int WHOLEMATRIX = 2;

	private YMatrix() {
	}

	private static void reCalcAllYPrims() {
		Circuit ckt = DSSGlobals.activeCircuit;
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
		Circuit ckt = DSSGlobals.activeCircuit;
		if (ckt.isLogEvents())
			Utilities.logThisEvent("Recalc Invalid Yprims");
		for (CktElement pElem : ckt.getCktElements())
			if (pElem.isYprimInvalid())
				pElem.calcYPrim();
	}

	public static void resetSparseMatrix(MutableLong Y, int size) throws Esolv32Problem {
		if (Y.longValue() != 0) {
			if (deleteSparseSet(Y.longValue()) < 1)  // get rid of existing one before making a new one
				throw new Esolv32Problem("Error deleting system Y Matrix in resetSparseMatrix. Problem with sparse matrix solver.");
			Y.setValue(0);
		}

		// make a new sparse set
		Y.setValue(newSparseSet(size));
		if (Y.longValue() < 1)  // raise an exception  TODO Check zero based indexing
			throw new Esolv32Problem("Error creating system Y Matrix. Problem with sparse matrix solver.");
	}

	public static void initializeNodeVbase() {
		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		for (int i = 0; i < ckt.getNumNodes(); i++) {
			NodeBus nb = ckt.getMapNodeToBus()[i];
			sol.getNodeVBase()[i] = ckt.getBus(nb.busRef).getKVBase() * 1000.0;
			sol.setVoltageBaseChanged(false);
		}
	}

	/**
	 * Builds designated Y matrix for system and allocates solution arrays.
	 *
	 * @throws Esolv32Problem
	 */
	public static void buildYMatrix(int BuildOption, boolean AllocateVI) throws Esolv32Problem {
		int YMatrixSize;
		Complex[] CmatArray;

		CmatArray = null;
		// new function to log KLUSolve.DLL function calls
		//setLogFile("KLU_Log.txt", 1);

		Circuit ckt = DSSGlobals.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (sol.isPreserveNodeVoltages())
			sol.updateVBus();  // update voltage values stored with bus object

		// the following recounts the number of buses and resets meter zones and feeders
		// if radial but systemNodeMap not set then init for radial got skipped due to script sequence
		if (ckt.isBusNameRedefined())
			ckt.reProcessBusDefs();  // this changes the node references into the system Y matrix!

		YMatrixSize = ckt.getNumNodes();

		switch (BuildOption) {
		case WHOLEMATRIX:
			resetSparseMatrix(sol.getYSystem(), YMatrixSize);
			sol.setY(sol.getYSystem());
			break;
		case SERIESONLY:
			resetSparseMatrix(sol.getYSeries(), YMatrixSize);
			sol.setY(sol.getYSeries());
			break;
		}

		// tune up the Yprims if necessary
		if (sol.isFrequencyChanged()) {
			reCalcAllYPrims();
		} else {
			reCalcInvalidYPrims();
		}

		if (DSSGlobals.solutionAbort) {
			DSSGlobals.doSimpleMsg("Y matrix build aborted due to error in primitive Y calculations.", 11001);
			return;  // some problem occurred building Yprims
		}

		sol.setFrequencyChanged(false);

		if (ckt.isLogEvents())
			switch (BuildOption) {
			case WHOLEMATRIX:
				Utilities.logThisEvent("Building whole Y matrix");
				break;
			case SERIESONLY:
				Utilities.logThisEvent("Building series Y matrix");
				break;
			}

		// add in Yprims for all devices
		for (CktElement pElem : ckt.getCktElements()) {
			if (pElem.isEnabled()) {
				switch (BuildOption) {
				case WHOLEMATRIX:
					CmatArray = pElem.getYPrimValues(DSSGlobals.ALL_YPRIM);
					break;
				case SERIESONLY:
					CmatArray = pElem.getYPrimValues(DSSGlobals.SERIES);
					break;
				}
				// new function adding primitive Y matrix to KLU system Y matrix
				if (CmatArray != null)
					if (addPrimitiveMatrix(sol.getY(), pElem.getYorder(), pElem.getNodeRef()[0], CmatArray[0]) < 0)  // TODO Check zero based indexing
						throw new Esolv32Problem("Node index out of range adding to System Y Matrix");
			}  // if enabled
		}

		// allocate voltage and current vectors if requested
		if (AllocateVI) {
			if (ckt.isLogEvents())
				Utilities.logThisEvent("ReAllocating Solution Arrays");
			sol.setNodeV( Utilities.resizeArray(sol.getNodeV(), ckt.getNumNodes() + 1) );  // allocate system voltage array - allow for zero element
			sol.getNodeV()[0] = Complex.ZERO;  // TODO Check zero based indexing
			sol.setCurrents( Utilities.resizeArray(sol.getCurrents(), ckt.getNumNodes() + 1) );  // allocate system current array
			sol.setAuxCurrents( Utilities.resizeArray(sol.getAuxCurrents(), ckt.getNumNodes()) );  // allocate system current array
			if (sol.getVMagSaved() != null)
				sol.setVMagSaved(new double[0]);
			if (sol.getErrorSaved() != null)
				sol.setErrorSaved(new double[0]);
			if (sol.getNodeVBase() != null)
				sol.setNodeVBase(new double[0]);
			sol.setVMagSaved(new double[ckt.getNumNodes()]);   // zero fill
			sol.setErrorSaved(new double[ckt.getNumNodes()]);  // zero fill
			sol.setNodeVBase(new double[ckt.getNumNodes()]);   // zero fill
			initializeNodeVbase();
		}

		switch (BuildOption) {
		case WHOLEMATRIX:
			sol.setSeriesYInvalid(true);  // indicate that the series matrix may not match
			sol.setSystemYChanged(false);
			break;
		case SERIESONLY:
			sol.setSeriesYInvalid(false);  // systemYChange unchanged
			break;
		}

		// seleted RCD only done now on mode change
		//sol.setSolutionInitialized(false);  // require initialization of voltages if Y changed

		if (sol.isPreserveNodeVoltages())
			sol.restoreNodeVFromVbus();

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

		Circuit ckt = DSSGlobals.activeCircuit;

		String Result = "";

		Y = ckt.getSolution().getY();
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			getMatrixElement(Y, i, i, c);
			if (c.abs() == 0.0) {
				NodeBus nb = ckt.getMapNodeToBus()[i];
				Result += String.format("%sZero diagonal for bus %s, node %d", DSSGlobals.CRLF, ckt.getBusList().get(nb.busRef), nb.nodeNum);
			}
		}

		// new diagnostics
		getSingularCol(Y, sCol);  // returns a 0-based node number  TODO Check zero based indexing
		if (sCol >= 0) {
			NodeBus nb = ckt.getMapNodeToBus()[sCol];
			Result += String.format("%sMatrix singularity at bus %s, node %d", DSSGlobals.CRLF, ckt.getBusList().get(nb.busRef), sCol);
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
				Result += String.format("%s  #%d has %d nodes, including bus %s (node %d)", DSSGlobals.CRLF, i, iCount, ckt.getBusList().get(nb.busRef), iFirst);
			}
		}

		return Result;
	}

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

	/**
	 * Returns the non-zero handle of a new sparse matrix, if successful
	 * must call deleteSparseSet on the valid handle when finished.
	 */
	public static long newSparseSet(long nBus) {
		return 0;
	}

	/** return 1 for success, 0 for invalid handle */
	public static long deleteSparseSet(long id) {
		return 0;
	}

	/**
	 * Return 1 for success, 2 for singular, 0 for invalid handle
	 * factors matrix if needed.
	 */
	public static long solveSparseSet(long id, Complex[] x, Complex[] b) {
		return 0;
	}

	/**
	 * @param id
	 * @return 1 for success, 0 for invalid handle
	 */
	public static long zeroSparseSet(long id) {
		return 0;
	}

	/**
	 * Does no extra work if the factoring was done previously.
	 *
	 * @param id
	 * @return 1 for success, 2 for singular, 0 for invalid handle
	 */
	public static long factorSparseMatrix(long id) {
		return 0;
	}

	/* These "get" functions for matrix information all return 1 for success, 0 for invalid handle */

	/**
	 * Res is the matrix order (number of nodes)
	 */
	public static long getSize(long id, long Res) {
		return 0;
	}

	/**
	 * The following function results are not known prior to factoring.
	 *
	 * @param id
	 * @param Res the number of floating point operations to factor
	 * @return
	 */
	public static long getFlops(long id, double Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param Res number of non-zero entries in the original matrix
	 * @return
	 */
	public static long getNNZ(long id, long Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param Res number of non-zero entries in factored matrix
	 * @return
	 */
	public static long getSparseNNZ(long id, long Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param Res a column number corresponding to a singularity, or 0 if not singular
	 * @return
	 */
	public static long getSingularCol(long id, long Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param Res the pivot element growth factor
	 * @return
	 */
	public static long getRGrowth(long id, double Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param Res quick estimate of the reciprocal of condition number
	 * @return
	 */
	public static long getRCond(long id, double Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param Res a more accurate estimate of condition number
	 * @return
	 */
	public static long getCondEst(long id, double Res) {
		return 0;
	}

	/**
	 * @param id
	 * @param nOrder
	 * @param Nodes
	 * @param Mat
	 * @return 1 for success, 0 for invalid handle or a node number out of range
	 */
	public static long addPrimitiveMatrix(long id, long nOrder, long Nodes, Complex Mat) {
		return 0;
	}

	/**
	 * @param Path
	 * @param Action 0 (close), 1 (rewrite) or 2 (append)
	 * @return
	 */
	public static long setLogFile(char Path, long Action) {
		return 0;
	}

	/**
	 * Fill sparse matrix in compressed column form.
	 *
	 * @param id
	 * @param nColP
	 * @param nNZ
	 * @param pColP must be of length nColP == nBus + 1
	 * @param pRowIdx length nnz
	 * @param Mat length nnz
	 * @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	 */
	public static long getCompressedMatrix(long id, long nColP, long nNZ, long pColP, long pRowIdx, Complex Mat) {
		return 0;
	}

	/**
	 * Fill sparse matrix in triplet form.
	 *
	 * @param id
	 * @param nNZ
	 * @param pRows length nnz
	 * @param pCols length nnz
	 * @param Mat length nnz
	 * @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	 */
	public static long getTripletMatrix(long id, long nNZ, long pRows, long pCols, Complex Mat) {
		return 0;
	}

	/**
	 * @param id
	 * @param nOrder
	 * @param pNodes contains the island number for each node
	 * @return number of islands >= 1 by graph traversal
	 */
	public static long findIslands(long id, long nOrder, long pNodes) {
		return 0;
	}

	/**
	 * Deprecated, use addPrimitiveMatrix instead.
	 */
	public static long addMatrixElement(long id, long i, long j, Complex Value) {
		return 0;
	}

	/**
	 * Deprecated, use getCompressedMatrix or getTripletMatrix.
	 */
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
