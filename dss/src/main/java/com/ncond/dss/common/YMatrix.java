/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.sourceforge.klusolve.CSparseSolver;
import net.sourceforge.klusolve.ISolver;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus.NodeBus;
import com.ncond.dss.common.exceptions.SolverProblem;
import com.ncond.dss.common.types.BuildOption;
import com.ncond.dss.common.types.YPrimType;


public class YMatrix {

	private static Map<UUID, ISolver> solverMap = new HashMap<UUID, ISolver>();

	private static ISolver solver;

	private YMatrix() {}

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

	public static void resetSparseMatrix(UUID[] Y, int size) throws SolverProblem {
		if (Y[0] != null) {
			if (deleteSparseSet(Y[0]) < 1)  // get rid of existing one before making a new one
				throw new SolverProblem("Error deleting system Y matrix in resetSparseMatrix. Problem with sparse matrix solver.");
			Y[0] = null;
		}

		// make a new sparse set
		Y[0] = newSparseSet(size);

		if (Y[0] == null)
			throw new SolverProblem("Error creating system Y matrix. Problem with sparse matrix solver.");
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
		UUID[] Y = new UUID[1];
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
					if (addPrimitiveMatrix(sol.getY(), elem.getYOrder(), elem.getNodeRef(), cArray) < 1)
						throw new SolverProblem("Node index out of range adding to system Y matrix");
				}
			}
		}

		// allocate voltage and current vectors if requested
		if (allocateVI) {
			if (ckt.isLogEvents())
				Util.logThisEvent("Reallocating solution arrays");
			sol.setNodeV(Util.resizeArray(sol.getNodeV(), ckt.getNumNodes() + 1));  // allocate system voltage array - allow for zero element
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
		UUID Y;
		int[] sCol = new int[1];
		long nIslands, count, first;
		int[] cliques;
		NodeBus nb;
		Circuit ckt = DSS.activeCircuit;

		StringBuilder sb = new StringBuilder();

		Y = ckt.getSolution().getY();
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			getMatrixElement(Y, i, i, c);
			if (c[0].abs() == 0.0) {
				nb = ckt.getMapNodeToBus(i + 1);
				sb.append(String.format("%sZero diagonal for bus %s, node %d",
					DSS.CRLF, ckt.getBusList().get(nb.busRef), nb.nodeNum));
			}
		}

		// new diagnostics
		getSingularCol(Y, sCol);  // returns a 0-based node number
		if (sCol[0] >= 0) {
			nb = ckt.getMapNodeToBus(sCol[0] + 1);
			sb.append(String.format("%sMatrix singularity at bus %s, node %d",
				DSS.CRLF, ckt.getBusList().get(nb.busRef), sCol[0]));
		}

		cliques = new int[ckt.getNumNodes()];
		nIslands = findIslands(Y, ckt.getNumNodes(), cliques);
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


	/**
	 *
	 * @param path
	 * @param i 0 to close, 1 to rewrite, 2 to append
	 */
	public static void setLogFile(String path, int i) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the non-zero handle of a new sparse matrix, if successful
	 * must call deleteSparseSet on the valid handle when finished.
	 */
	public static UUID newSparseSet(int nBus) {
		try {
			UUID uuid = UUID.randomUUID();
			solver = new CSparseSolver();
			solver.initialize(nBus, 0, nBus);
			solverMap.put(uuid, solver);
			return uuid;
		} catch (Exception e) {
			DSS.doSimpleMsg("Error creating new sparse set: " + e.getMessage(), -1);
			return null;
		}
	}

	/**
	 * @param uuid
	 * @return 1 for success, 0 for invalid handle
	 */
	public static int zeroSparseSet(UUID uuid) {
		solver = solverMap.get(uuid);

		if (solver != null) {
			solver.zero();
			solver.setFactored(false);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Does no extra work if the factoring was done previously.
	 *
	 * @param uuid
	 * @return 1 for success, 2 for singular, 0 for invalid handle
	 */
	public static int factorSparseMatrix(UUID uuid) {
		solver = solverMap.get(uuid);

		if (solver != null) {
			if (solver.factorSystem() == 0) {
				return 1;  // success
			} else {
				return 2;  // singular
			}
		} else {
			return 0;
		}
	}

	/**
	 * factors matrix if needed.
	 *
	 * @param uuid
	 * @param x current injection input
	 * @param b node voltage output
	 * @return 1 for success, 2 for singular, 0 for invalid handle
	 */
	public static int solveSparseSet(UUID uuid, Complex[] x, Complex[] b) {
		return solveSparseSet(uuid, x, 0, b, 0);
	}

	public static int solveSparseSet(UUID uuid, Complex[] x, int x_offset,
			Complex[] b, int b_offset) {
		double[] acxX, acxB;
		solver = solverMap.get(uuid);

		if (solver != null) {
			if (!solver.isFactored())
				solver.factorSystem();

			if (solver.isFactored()) {
				acxX = toArray(x, x_offset);
				acxB = toArray(b, b_offset);

				solver.solveSystem(acxX, acxB);

				fromArray(acxB, 0, b, b_offset);
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

	/**
	 * @return 1 for success, 0 for invalid id
	 */
	public static int deleteSparseSet(UUID uuid) {
		solver = solverMap.remove(uuid);

		return (solver != null) ? 1 : 0;
	}

	public static int getMatrixElement(UUID y, int i, int i2, Complex[] c) {
		double[] a;
		solver = solverMap.get(y);

		if (solver != null) {
			a = toArray(c[0]);
			solver.getElement(i, i2, a);
			c[0] = new Complex(a[0], a[1]);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 *
	 * @param uuid
	 * @param res the matrix order (number of nodes)
	 * @return
	 */
	public static int getSize(UUID uuid, int[] res) {
		int order;
		solver = solverMap.get(uuid);

		if (solver != null) {
			order = solver.getSize();
			res[0] = order;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param id
	 * @param res number of non-zero entries in the original matrix
	 * @return
	 */
	public static int getNNZ(UUID uuid, int[] res) {
		int nnz;
		solver = solverMap.get(uuid);

		if (solver != null) {
			nnz = solver.getNNZ();
			res[0] = nnz;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param id
	 * @param res number of non-zero entries in factored matrix
	 * @return
	 */
	public static int getSparseNNZ(UUID uuid, int[] res) {
		int nnz;
		solver = solverMap.get(uuid);

		if (solver != null) {
			nnz = solver.getSparseNNZ();
			res[0] = nnz;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param uuid
	 * @param res quick estimate of the reciprocal of condition number
	 * @return
	 */
	public static long getRCond(UUID uuid, double[] res) {
		double rcond;
		solver = solverMap.get(uuid);

		if (solver != null) {
			rcond = solver.getRCond();
			res[0] = rcond;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param uuid
	 * @param res the pivot element growth factor
	 * @return
	 */
	public int getRGrowth(UUID uuid, double[] res) {
		double rgrowth;
		solver = solverMap.get(uuid);

		if (solver != null) {
			rgrowth = solver.getRGrowth();
			res[0] = rgrowth;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param uuid
	 * @param res a more accurate estimate of condition number
	 * @return
	 */
	public int getCondEst(UUID uuid, double[] res) {
		double cond_est;
		solver = solverMap.get(uuid);

		if (solver != null) {
			cond_est = solver.getCondEst();
			res[0] = cond_est;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * The following function results are not known prior to factoring.
	 *
	 * @param uuid
	 * @param res the number of floating point operations to factor
	 * @return
	 */
	public int getFlops(UUID uuid, double[] res) {
		double flops;
		solver = solverMap.get(uuid);

		if (solver != null) {
			flops = solver.getFlops();
			res[0] = flops;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param uuid
	 * @param res a column number corresponding to a singularity,
	 * or -1 if not singular  FIXME check zero based indexing
	 * @return
	 */
	public static int getSingularCol(UUID uuid, int[] res) {
		int col;
		solver = solverMap.get(uuid);

		if (solver != null) {
			col = solver.getSingularCol();
			res[0] = col;
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @param uuid
	 * @param nOrder
	 * @param nodes
	 * @param mat
	 * @return 1 for success, 0 for invalid handle or a node number out of range
	 */
	public static int addPrimitiveMatrix(UUID uuid, int nOrder,
			int[] nodes, Complex[] mat) {
		solver = solverMap.get(uuid);

		if (solver != null) {
			return solver.addPrimitiveMatrix(nOrder, nodes, toArray(mat));
		} else {
			return 0;
		}
	}

	/**
	 * Fill sparse matrix in compressed column form.
	 *
	 * @param uuid
	 * @param nCol
	 * @param nnz
	 * @param col must be of length nColP == nBus + 1
	 * @param rowIdx length nnz
	 * @param mat length nnz
	 * @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	 */
	public static int getCompressedMatrix(UUID uuid, int nCol, int nnz,
			int[] pCol, int[] rowIdx, Complex[] mat) {
		double[] a;
		solver = solverMap.get(uuid);

		if (solver != null) {
			a = toArray(mat);
			if (solver.getCompressedMatrix(nCol, nnz, pCol, rowIdx, a) != 0) {
				fromArray(a, mat);
				return 1;
			} else {
				return 2;  // probably a size mismatch
			}
		} else {
			return 0;
		}
	}

	/**
	 * Fill sparse matrix in triplet form.
	 *
	 * @param id
	 * @param nnz
	 * @param rows length nnz
	 * @param cols length nnz
	 * @param mat length nnz
	 * @return 1 for success, 0 for invalid handle, 2 for invalid array sizes
	 */
	public static int getTripletMatrix(UUID uuid, int nnz, int[] rows,
			int[] cols, Complex[] mat) {
		double[] a;
		solver = solverMap.get(uuid);

		if (solver != null) {
			a = toArray(mat);
			if (solver.getTripletMatrix(nnz, rows, cols, a) != 0) {
				fromArray(a, mat);
				return 1;
			} else {
				return 2;  // probably a size mismatch
			}
		} else {
			return 0;
		}
	}

	/**
	 * @param uuid
	 * @param nOrder
	 * @param nodes contains the island number for each node
	 * @return number of islands >= 1 by graph traversal
	 */
	public static long findIslands(UUID uuid, int nOrder, int[] nodes) {
		solver = solverMap.get(uuid);

		if (solver != null && nOrder >= solver.getSize()) {
			return solver.findIslands(nodes);
		} else {
			return 0;
		}
	}


	private static double[] toArray(Complex c) {
		return new double[] {c.getReal(), c.getImaginary()};
	}

	private static double[] toArray(Complex[] x) {
		double[] a = new double[x.length * 2];
		for (int i = 0; i < x.length; i++) {
			a[(2 * i)] = x[i].getReal();
			a[(2 * i) + 1] = x[i].getImaginary();
		}
		return a;
	}

	private static double[] toArray(Complex[] x, int x_offset) {
		Complex z;
		double[] a = new double[(2 * x.length) - (2 * x_offset)];

		for (int i = x_offset; i < x.length; i++) {
			z = x[i];
			a[(2 * i)] = z.getReal();
			a[(2 * i) + 1] = z.getImaginary();
		}
		return a;
	}

	private static void fromArray(double[] x, Complex[] z) {
		double re, im;

		for (int i = 0; i < z.length; i++) {
			re = x[(2 * i)];
			im = x[(2 * i) + 1];

			z[i] = new Complex(re, im);
		}
	}

	private static void fromArray(double[] x, int x_offset, Complex[] z, int z_offset) {
		double re, im;

		for (int i = x_offset; i < x.length; i+=2) {
			re = x[(2 * x_offset) + i];
			im = x[(2 * x_offset) + i + 1];

			z[z_offset + (i / 2)] = new Complex(re, im);
		}
	}

}
