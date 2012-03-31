/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.util.logging.Logger;

import net.sourceforge.klusolve.CSparseSolver;
import net.sourceforge.klusolve.ISolver;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.common.Bus.NodeBus;
import com.ncond.dss.common.exceptions.SolverProblem;
import com.ncond.dss.common.types.BuildOption;
import com.ncond.dss.common.types.YPrimType;


public class YMatrix {

	private Logger log = Logger.getLogger(YMatrix.class.getName());

	private ISolver solver;

	public YMatrix(int nBus) {
		log.info("Creating sparse set (nBus = " + nBus + ").");
		solver = new CSparseSolver(nBus, 0, nBus);
	}

	private static void reCalcAllYPrims() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt.isLogEvents())
			Util.logThisEvent("Recalc all Yprims");
		for (CktElement elem : ckt.getCktElements())
			elem.calcYPrim();
	}

	/**
	 * Recalc Yprims only for those circuit elements that have had changes
	 * since last solution.
	 */
	private static void reCalcInvalidYPrims() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt.isLogEvents())
			Util.logThisEvent("Recalc invalid Yprims");
		for (CktElement elem : ckt.getCktElements())
			if (elem.isYprimInvalid()) elem.calcYPrim();
	}

	public static void resetSparseMatrix(YMatrix[] Y, int size) {
		if (Y[0] != null) {
			Y[0].deleteSparseSet();  // get rid of existing one before making a new one
			Y[0] = null;
		}

		// make a new sparse set
		Y[0] = new YMatrix(size);
	}

	public static void initializeNodeVBase() {
		NodeBus nb;
		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		for (int i = 0; i < ckt.getNumNodes(); i++) {
			nb = ckt.getMapNodeToBus(i + 1);
			sol.getNodeVBase()[i] = ckt.getBus(nb.busRef - 1).getKVBase() * 1000.0;
			sol.setVoltageBaseChanged(false);
		}
	}

	/**
	 * Builds designated Y matrix for system and allocates solution arrays.
	 *
	 * @throws SolverProblem
	 */
	public static void buildYMatrix(BuildOption buildOption, boolean allocateVI) throws SolverProblem {
		YMatrix[] Y = new YMatrix[1];
		int YMatrixSize;
		Complex[] cArray = null;

		// log KLU solve function calls
		//setLogFile("KLU_Log.txt", 1);

		Circuit ckt = DSS.activeCircuit;
		SolutionObj sol = ckt.getSolution();

		if (sol.isPreserveNodeVoltages())
			sol.updateVBus();  // update voltage values stored with bus object

		// the following recounts the number of buses and resets meter zones and feeders
		// if radial but systemNodeMap not set then init for radial got skipped due to script sequence
		if (ckt.isBusNameRedefined())
			ckt.reProcessBusDefs();  // this changes the node references into the system Y matrix!

		YMatrixSize = ckt.getNumNodes();

		switch (buildOption) {
		case WHOLEMATRIX:
			Y[0] = sol.getYSystem();
			resetSparseMatrix(Y, YMatrixSize);
			sol.setYSystem(Y[0]);
			sol.setY(sol.getYSystem());
			break;
		case SERIESONLY:
			Y[0] = sol.getYSeries();
			resetSparseMatrix(Y, YMatrixSize);
			sol.setYSeries(Y[0]);
			sol.setY(sol.getYSeries());
			break;
		}

		// tune up the Yprims if necessary
		if (sol.isFrequencyChanged()) {
			reCalcAllYPrims();
		} else {
			reCalcInvalidYPrims();
		}

		if (DSS.solutionAbort) {
			DSS.doSimpleMsg("Y matrix build aborted due to error in primitive Y calculations.", 11001);
			return;  // some problem occurred building Yprims
		}

		sol.setFrequencyChanged(false);

		if (ckt.isLogEvents())
			switch (buildOption) {
			case WHOLEMATRIX:
				Util.logThisEvent("Building whole Y matrix");
				break;
			case SERIESONLY:
				Util.logThisEvent("Building series Y matrix");
				break;
			}

		// add in Yprims for all devices
		for (CktElement elem : ckt.getCktElements()) {
			if (elem.isEnabled()) {
				switch (buildOption) {
				case WHOLEMATRIX:
					cArray = elem.getYPrimValues(YPrimType.ALL_YPRIM);
					break;
				case SERIESONLY:
					cArray = elem.getYPrimValues(YPrimType.SERIES);
					break;
				}
				// new function adding primitive Y matrix to KLU system Y matrix
				if (cArray != null) {
					if (sol.getY().addPrimitiveMatrix(elem.getYOrder(), elem.getNodeRef(), cArray) < 1)
						throw new SolverProblem("Node index out of range adding to system Y matrix");
				}
			}
		}

		// allocate voltage and current vectors if requested
		if (allocateVI) {
			if (ckt.isLogEvents())
				Util.logThisEvent("Reallocating solution arrays");
			sol.setNodeV(Util.resizeArray(sol.getNodeV(), ckt.getNumNodes() + 1));  // allocate system voltage array - allow for zero element
			for (int i = 0; i < sol.getNodeV().length; i++) {
				if (sol.getNodeV()[i] == null)
					sol.getNodeV()[i] = Complex.ZERO;
			}
			sol.getNodeV()[0] = Complex.ZERO;
			sol.setCurrents(Util.resizeArray(sol.getCurrents(), ckt.getNumNodes() + 1));  // allocate system current array
			sol.setAuxCurrents(Util.resizeArray(sol.getAuxCurrents(), ckt.getNumNodes()));  // allocate system current array
			sol.setVMagSaved(new double[ckt.getNumNodes()]);
			sol.setErrorSaved(new double[ckt.getNumNodes()]);
			sol.setNodeVBase(new double[ckt.getNumNodes()]);
			initializeNodeVBase();
		}

		switch (buildOption) {
		case WHOLEMATRIX:
			sol.setSeriesYInvalid(true);  // indicate that the series matrix may not match
			sol.setSystemYChanged(false);
			break;
		case SERIESONLY:
			sol.setSeriesYInvalid(false);  // systemYChange unchanged
			break;
		}

		// only done now on mode change
		//sol.setSolutionInitialized(false);  // require initialization of voltages if Y changed

		if (sol.isPreserveNodeVoltages())
			sol.restoreNodeVFromVbus();
	}

	/**
	 * Leave the call to getMatrixElement, but add more diagnostics.
	 */
	public static String checkYMatrixforZeroes() {
		Complex[] c = new Complex[1];
		YMatrix Y;
		int sCol;
		long nIslands, count, first;
		int[] cliques;
		NodeBus nb;
		Circuit ckt = DSS.activeCircuit;

		StringBuilder sb = new StringBuilder();

		Y = ckt.getSolution().getY();
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			Y.getMatrixElement(i, i, c);
			if (c[0].abs() == 0.0) {
				nb = ckt.getMapNodeToBus(i + 1);
				sb.append(String.format("%sZero diagonal for bus %s, node %d",
					DSS.CRLF, ckt.getBusList().get(nb.busRef - 1), nb.nodeNum));
			}
		}

		// new diagnostics
		sCol = Y.getSingularCol();  // returns a 0-based node number
		if (sCol >= 0) {
			nb = ckt.getMapNodeToBus(sCol + 1);
			sb.append(String.format("%sMatrix singularity at bus %s, node %d",
				DSS.CRLF, ckt.getBusList().get(nb.busRef - 1), sCol));
		}

		cliques = new int[ckt.getNumNodes()];
		nIslands = Y.findIslands(ckt.getNumNodes(), cliques);
		if (nIslands > 1) {
			sb.append(String.format("%sFound %d electrical islands:", DSS.CRLF, nIslands));
			for (int i = 0; i < nIslands; i++) {
				count = 0;
				first = 0;
				for (int p = 0; p < ckt.getNumNodes(); p++) {
					if (cliques[p] == i) {
						count += 1;
						if (first == 0)
							first = p + 1;
					}
				}
				nb = ckt.getMapNodeToBus((int) first + 1);
				sb.append(String.format("%s  #%d has %d nodes, including bus %s (node %d)",
					DSS.CRLF, i+1, count, ckt.getBusList().get(nb.busRef - 1), first + 1));
			}
		}

		return sb.toString();
	}

	public void setSolver(ISolver solver) {
		assert solver != null;
		this.solver = solver;
	}

	/**
	 *
	 * @param path
	 * @param i 0 to close, 1 to rewrite, 2 to append
	 */
	public static void setLogFile(String path, int i) {
		throw new UnsupportedOperationException();
	}

	public void zeroSparseSet() {
		solver.zero();
		solver.setFactored(false);
	}

	/**
	 * Does no extra work if the factoring was done previously.
	 *
	 * @return 1 for success, 2 for singular
	 */
	public int factorSparseMatrix() {
		if (solver.factorSystem() == 0) {
			return 1;  // success
		} else {
			return 2;  // singular
		}
	}

	/**
	 * Factors matrix if needed.
	 *
	 * @param x current injection input
	 * @param b node voltage output
	 * @return 1 for success, 2 for singular
	 */
	public int solveSparseSet(Complex[] x, Complex[] b) {
		return solveSparseSet(x, 0, b, 0);
	}

	public int solveSparseSet(Complex[] x, int x_offset,
			Complex[] b, int b_offset) {
		int rc = 0;
		double[] acxX, acxB;

		if (!solver.isFactored())
			solver.factorSystem();

		if (solver.isFactored()) {
			acxX = Complex.toArray(x, x_offset);
			acxB = Complex.toArray(b, b_offset);

			solver.solveSystem(acxX, acxB);

			Complex.fromArray(acxB, 0, b, b_offset);
			rc = 1;
		} else {
			rc = 2;
		}

		log.info("Solved sparse set (" + rc + ")");

		return rc;
	}

	public void deleteSparseSet() {
		solver = null;
	}

	public void getMatrixElement(int i, int i2, Complex[] c) {
		double[] a = new double[2];
		solver.getElement(i, i2, a);
		c[0] = new Complex(a[0], a[1]);
	}

	/**
	 * @return the matrix order (number of nodes)
	 */
	public int getSize() {
		return solver.getSize();
	}

	/**
	 * @return number of non-zero entries in the original matrix
	 */
	public int getNNZ() {
		return solver.getNNZ();
	}

	/**
	 * @return number of non-zero entries in factored matrix
	 */
	public int getSparseNNZ() {
		return solver.getSparseNNZ();
	}

	/**
	 * @return quick estimate of the reciprocal of condition number
	 */
	public double getRCond() {
		return solver.getRCond();
	}

	/**
	 * @return the pivot element growth factor
	 */
	public double getRGrowth() {
		return solver.getRGrowth();
	}

	/**
	 * @return a more accurate estimate of condition number
	 */
	public double getCondEst() {
		return solver.getCondEst();
	}

	/**
	 * The following function results are not known prior to factoring.
	 *
	 * @return the number of floating point operations to factor
	 */
	public double getFlops() {
		return solver.getFlops();
	}

	/**
	 * @return a column number corresponding to a singularity,
	 * or -1 if not singular
	 */
	public int getSingularCol() {
		return solver.getSingularCol();
	}

	public int addPrimitiveMatrix(int nOrder, int[] nodes, Complex[] mat) {
		int i, j, idx;
		StringBuilder sb = new StringBuilder();

		sb.append("Adding primitive matrix (order = " + nOrder + "):\n");
		for (i = 0; i < nOrder; i++) {
			idx = i;
			for (j = 0; j < nOrder; j++) {
				sb.append(String.format("\tlocal [%d,%d] system [%d,%d] val(%2d) = %9.6f + j%9.6f\n",
					i, j, nodes[i], nodes[j], idx, mat[idx].real(), mat[idx].imag()));
				idx += nOrder;
			}
		}
		log.info(sb.toString());

		return solver.addPrimitiveMatrix(nOrder, nodes, Complex.toArray(mat));
	}

	/**
	 * Fill sparse matrix in compressed column form.
	 *
	 * @param nCol
	 * @param nnz
	 * @param col must be of length nColP == nBus + 1
	 * @param rowIdx length nnz
	 * @param mat length nnz
	 * @return 1 for success, 2 for invalid array sizes
	 */
	public int getCompressedMatrix(int nCol, int nnz, int[] pCol, int[] rowIdx, Complex[] mat) {
		double[] a = new double[2 * nnz];
		if (solver.getCompressedMatrix(nCol, nnz, pCol, rowIdx, a) != 0) {
			Complex.fromArray(a, mat);
			return 1;
		} else {
			return 2;  // probably a size mismatch
		}
	}

	/**
	 * Fill sparse matrix in triplet form.
	 *
	 * @param nnz
	 * @param rows length nnz
	 * @param cols length nnz
	 * @param mat length nnz
	 * @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	 */
	public int getTripletMatrix(int nnz, int[] rows, int[] cols, Complex[] mat) {
		double[] a = new double[2 * nnz];
		if (solver.getTripletMatrix(nnz, rows, cols, a) != 0) {
			Complex.fromArray(a, mat);
			return 1;
		} else {
			return 2;  // probably a size mismatch
		}
	}

	/**
	 * @param nOrder
	 * @param nodes contains the island number for each node
	 * @return number of islands >= 1 by graph traversal
	 */
	public long findIslands(int nOrder, int[] nodes) {
		if (solver != null && nOrder >= solver.getSize()) {
			return solver.findIslands(nodes);
		} else {
			return 0;
		}
	}

}
