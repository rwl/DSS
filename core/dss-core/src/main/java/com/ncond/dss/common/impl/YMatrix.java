package com.ncond.dss.common.impl;

import java.util.UUID;

import org.apache.commons.math.complex.Complex;

import net.sourceforge.klusolve.CSparseSolve;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.BusImpl.NodeBus;


public class YMatrix extends CSparseSolve {

	/* Options for building Y matrix */
	public static final int SERIESONLY = 1;
	public static final int WHOLEMATRIX = 2;

	private YMatrix() {
	}

	private static void reCalcAllYPrims() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt.isLogEvents())
			Util.logThisEvent("Recalc All Yprims");
		for (CktElement pElem : ckt.getCktElements())
			pElem.calcYPrim();
	}

	/**
	 * Recalc YPrims only for those circuit elements that have had changes
	 * since last solution.
	 */
	private static void reCalcInvalidYPrims() {
		Circuit ckt = DSS.activeCircuit;
		if (ckt.isLogEvents())
			Util.logThisEvent("Recalc Invalid Yprims");
		for (CktElement pElem : ckt.getCktElements())
			if (pElem.isYprimInvalid())
				pElem.calcYPrim();
	}

	public static void resetSparseMatrix(UUID[] Y, int size) throws SolveProblem {
		if (Y[0] != null) {
			if (deleteSparseSet(Y[0]) < 1)  // get rid of existing one before making a new one
				throw new SolveProblem("Error deleting system Y Matrix in resetSparseMatrix. Problem with sparse matrix solver.");
			Y[0] = null;
		}

		// make a new sparse set
		Y[0] = newSparseSet(size);
		if (Y[0] == null)  // raise an exception  TODO Check zero based indexing
			throw new SolveProblem("Error creating system Y Matrix. Problem with sparse matrix solver.");
	}

	public static void initializeNodeVbase() {
		Circuit ckt = DSS.activeCircuit;
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
	 * @throws SolveProblem
	 */
	public static void buildYMatrix(int BuildOption, boolean AllocateVI) throws SolveProblem {
		UUID[] pY = new UUID[1];
		int YMatrixSize;
		Complex[] CmatArray;

		CmatArray = null;
		// new function to log KLUSolve.DLL function calls
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

		switch (BuildOption) {
		case WHOLEMATRIX:
			pY[0] = sol.getYSystem();
			resetSparseMatrix(pY, YMatrixSize);
			sol.setYSystem(pY[0]);
			sol.setY(sol.getYSystem());
			break;
		case SERIESONLY:
			pY[0] = sol.getYSeries();
			resetSparseMatrix(pY, YMatrixSize);
			sol.setYSeries(pY[0]);
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
			switch (BuildOption) {
			case WHOLEMATRIX:
				Util.logThisEvent("Building whole Y matrix");
				break;
			case SERIESONLY:
				Util.logThisEvent("Building series Y matrix");
				break;
			}

		// add in Yprims for all devices
		for (CktElement pElem : ckt.getCktElements()) {
			if (pElem.isEnabled()) {
				switch (BuildOption) {
				case WHOLEMATRIX:
					CmatArray = pElem.getYPrimValues(DSS.ALL_YPRIM);
					break;
				case SERIESONLY:
					CmatArray = pElem.getYPrimValues(DSS.SERIES);
					break;
				}
				// new function adding primitive Y matrix to KLU system Y matrix
				if (CmatArray != null)
					if (addPrimitiveMatrix(sol.getY(), pElem.getYorder(), pElem.getNodeRef(), 1, CmatArray, 1) < 0)  // TODO Check zero based indexing
						throw new SolveProblem("Node index out of range adding to System Y Matrix");
			}  // if enabled
		}

		// allocate voltage and current vectors if requested
		if (AllocateVI) {
			if (ckt.isLogEvents())
				Util.logThisEvent("ReAllocating Solution Arrays");
			sol.setNodeV( Util.resizeArray(sol.getNodeV(), ckt.getNumNodes() + 1) );  // allocate system voltage array - allow for zero element
			sol.getNodeV()[0] = Complex.ZERO;  // TODO Check zero based indexing
			sol.setCurrents( Util.resizeArray(sol.getCurrents(), ckt.getNumNodes() + 1) );  // allocate system current array
			sol.setAuxCurrents( Util.resizeArray(sol.getAuxCurrents(), ckt.getNumNodes()) );  // allocate system current array
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

	/**
	 * Leave the call to getMatrixElement, but add more diagnostics.
	 */
	public static String checkYMatrixforZeroes() {
		Complex[] c = new Complex[1];
		UUID Y;
		int[] sCol = new int[1];
		long nIslands, iCount, iFirst;
		int[] cliques;

		Circuit ckt = DSS.activeCircuit;

		String Result = "";

		Y = ckt.getSolution().getY();
		for (int i = 0; i < ckt.getNumNodes(); i++) {
			getMatrixElement(Y, i, i, c);
			if (c[0].abs() == 0.0) {
				NodeBus nb = ckt.getMapNodeToBus()[i];
				Result += String.format("%sZero diagonal for bus %s, node %d", DSS.CRLF, ckt.getBusList().get(nb.busRef), nb.nodeNum);
			}
		}

		// new diagnostics
		getSingularCol(Y, sCol);  // returns a 0-based node number  TODO Check zero based indexing
		if (sCol[0] >= 0) {
			NodeBus nb = ckt.getMapNodeToBus()[sCol[0]];
			Result += String.format("%sMatrix singularity at bus %s, node %d", DSS.CRLF, ckt.getBusList().get(nb.busRef), sCol);
		}

		cliques = new int[ckt.getNumNodes()];
		nIslands = findIslands(Y, ckt.getNumNodes(), cliques);
		if (nIslands > 1) {
			Result += String.format("%sFound %d electrical islands:", DSS.CRLF, nIslands);
			for (int i = 0; i < nIslands; i++) {
				iCount = 0;
				iFirst = 0;
				for (int p = 0; p < ckt.getNumNodes(); p++) {
					if (cliques[p] == i) {
						iCount += 1;
						if (iFirst == 0)
							iFirst = p + 1;
					}
				}
				NodeBus nb = ckt.getMapNodeToBus()[(int) iFirst];
				Result += String.format("%s  #%d has %d nodes, including bus %s (node %d)", DSS.CRLF, i, iCount, ckt.getBusList().get(nb.busRef), iFirst);
			}
		}

		return Result;
	}

}
