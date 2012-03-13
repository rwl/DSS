package com.ncond.dss.common;

import java.util.UUID;

import net.sourceforge.klusolve.CSparseSolve;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus.NodeBus;
import com.ncond.dss.common.exceptions.SolverProblem;
import com.ncond.dss.common.types.BuildOption;
import com.ncond.dss.common.types.YPrimType;


public class YMatrix extends CSparseSolve {

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
					if (addPrimitiveMatrix(sol.getY(), elem.getYOrder(), elem.getNodeRef(), 1, cArray, 1) < 1)
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

}
