/* ------------------------------------------------------------------------- */
/* Copyright (C) 2008, EnerNex Corporation. All rights reserved.             */
/* Copyright (C) 2012, Richard Lincoln. All rights reserved.                 */
/* Licensed under the GNU Lesser General Public License (LGPL) v 2.1         */
/* ------------------------------------------------------------------------- */

package net.sourceforge.klusolve;

import java.util.Stack;

import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcs;
import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcsa;
import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcsn;
import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcss;

import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_usolve.cs_usolve;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_util.cs_spalloc;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_complex.cs_czero;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_entry.cs_entry;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_ipvec.cs_ipvec;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_lsolve.cs_lsolve;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_lu.cs_lu;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_compress.cs_compress;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_dupl.cs_dupl;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_dropzeros.cs_dropzeros;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_sqr.cs_sqr;
import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_transpose.cs_transpose;
//import static edu.emory.mathcs.csparsej.tdcomplex.DZcs_print.cs_print;


public class CSparseSolver implements ISolver {

	protected DZcsa acx;

	/** admittance matrix blocks in compressed-column storage */
	protected DZcs Y22;

	/** admittance matrix blocks in triplet storage */
	protected DZcs T22;

	protected DZcss symbolic;
	protected DZcsn numeric;

	protected int nbus;  // number of nodes
	protected int nx;    // number of unknown voltages, hardwired to m_nBus
	protected int nnz;   // number of non-zero entries before factoring

	protected boolean factored;  // system has been factored

	protected int order;   // ordering method, 0:Natural, 1:Cholesky, 2:LU, 3:QR
	protected double tol;  // partial pivoting tolerance

	public CSparseSolver() {
		initDefaults();
	}

	public CSparseSolver(int nBus, int nV, int nI) {
		initDefaults();
		initialize(nBus, nV, nI);
	}

	protected void zero_indices() {
		nbus = nx = nnz = 0;
	}

	protected void null_pointers() {
		Y22 = null;
		T22 = null;
		numeric = null;
		symbolic = null;
		acx = null;
	}

	public void initDefaults() {
		nbus = 0;
		factored = false;
		acx = null;
		zero_indices();
		null_pointers();

		order = 2;
		tol = 1.0;//0.001;
	}

	public void clear() {
		zero_indices();
		null_pointers();
	}

	public int initialize(int nBus, int nV, int nI) {
		clear();

		nbus = nx = nBus;

		if (nx > 0) T22 = cs_spalloc (nx, nx, 2 * nx, true, true);

		acx = new DZcsa (nbus + 1);

		return 0;
	}

	public int initialize(int nBus) {
		return initialize(nBus, 0, 0);
	}

	public int factorSystem() {
		factored = false;

		int rc = factor();

		if (rc == 1) {
			factored = true;
			return 0;
		}
		return 1;
	}

	public int solveSystem(double[] x, double[] b) {
		acx.set(0, cs_czero());

		System.arraycopy(b, 0, acx.x, 1, 2 * nbus);

		solve(acx);

		System.arraycopy(acx.x, 1, x, 0, 2 * nbus);

		return 0;
	}

	/**
	 * @return 1 on success
	 */
	public int addPrimitiveMatrix(int order, int[] nodeRef, double[] mat) {
		int i, j, i1, idRow, idCol, idVal;
		double re, im;

		// check the node numbers
		for (i = 0; i < order; i++)
			if (nodeRef[i] > nbus) return 0;

		// add the matrix transposed
		for (i = 0; i < order; i++) {
			if (nodeRef[i] < 1) continue; // skip ground
			idVal = i;
			idRow = nodeRef[i] - 1;  // convert to zero-based
			for (j = 0; j < order; j++) {
				if (nodeRef[j] != 0) {  // skip ground
					idCol = nodeRef[j] - 1;  // convert to zero-based
					i1 = 2 * idVal;
					re = mat[i1];
					im = mat[i1 + 1];
					if (re != 0.0 || im != 0.0) {
						// put this value into the correct partition, transposed
						cs_entry (T22, idCol, idRow, new double[] {re, im});
					}
				}
				// always step through values, even if we don't use them
				idVal += order;
			}
		}
		return 1;
	}

	protected DZcs process_triplet(DZcs T) {
		DZcs C, A = null;

		if (T == null) return null;

		if (T.nz > 0) {
			//cs_print(T, false);
			C = cs_compress (T);
			cs_dupl (C);
			cs_dropzeros (C);
			A = cs_transpose (C, true);
			nnz += A.p [A.n];
			//cs_print(A, false);
		}

		return A;
	}

	protected void compress_partitions() {
		Y22 = process_triplet(T22);
	}

	public int factor() {
		// first convert the triplets to column-compressed form, and prep the columns
		if (T22 != null) {
			compress_partitions();
		} else {  // otherwise, compression and factoring has already been done
			return 1;
		}

		// factor Y22
		numeric = null;
		symbolic = null;

		if (Y22 != null) {
			symbolic = cs_sqr (order, Y22, false);  // ordering and symbolic analysis
			numeric = cs_lu (Y22, symbolic, tol) ;  // numeric LU factorization

			// TODO: check for singularity

			if (symbolic == null || numeric == null) return -1;
		}

		return 1;
	}

	public void solve(DZcsa Vbus) {
		int n;
		DZcsa b, x;

		if (nx < 1) return;  // nothing to do

		// load current injections into rhs
		b = new DZcsa(2 * nx);
		System.arraycopy(Vbus.x, 1, b.x, 0, 2 * nx);

		n = Y22.n;
		x = new DZcsa(n);

		// solve and copy voltages into the output vector
		// relying on Y22.n == nx from T22 creation
		cs_ipvec(numeric.pinv, b, x, n) ;	/* x = b(p) */
		cs_lsolve(numeric.L, x) ;		/* x = L\x */
		cs_usolve(numeric.U, x) ;		/* x = U\x */
		cs_ipvec(symbolic.q, x, b, n) ;		/* b(q) = x */

		System.arraycopy(b, 0, Vbus, 1, n);
	}

	public int getSize() {
		return nbus;
	}

	public int getNNZ() {
		return nnz;
	}

	public double getRCond() {
		throw new UnsupportedOperationException();
	}

	public double getRGrowth() {
		throw new UnsupportedOperationException();
	}

	public double getCondEst() {
		throw new UnsupportedOperationException();
	}

	public double getFlops() {
		throw new UnsupportedOperationException();
	}

	public int findDisconnectedSubnetwork() {
		throw new UnsupportedOperationException();
	}

	public int getSparseNNZ() {
		throw new UnsupportedOperationException();
	}

	public int getSingularCol() {
		throw new UnsupportedOperationException();
	}

	/* stack-based DFS from Sedgewick */

	protected Stack<Integer> stack;

	protected void mark_dfs (int j, int cnt, int[] Ap, int[] Ai, int[] clique) {
		int i, k;

		stack.add(j);
		while (!stack.empty()) {
			j = stack.pop();
			clique[j] = cnt;
			for (k = Ap [j]; k < Ap [j + 1]; k++) {
				i = Ai [k];
				if (clique[i] == 0) {
					stack.add (i);
					clique[i] = -1;  // to only push once
				}
			}
		}
	}

	public int findIslands(int[] idClique) {
		factor();

		int[] clique = new int[nbus];
		int[] Ap = Y22.p;
		int[] Ai = Y22.i;
		int j;

		// DFS down the columns
		int cnt = 0;
		for (j = 0; j < nbus; j++) clique[j] = cnt; // use to mark the nodes with clique #
		stack = new Stack<Integer>();
		for (j = 0; j < nbus; j++) {
			if (clique[j] == 0) {  // have not visited this column yet
				++cnt;
				mark_dfs (j, cnt, Ap, Ai, clique);
			}
		}

		for (j = 0; j < nbus; j++) idClique[j] = clique[j];

		return cnt;
	}

	public void zero() {
		initialize(nbus, 0, nbus);
	}

	public void addElement(int iRow, int iCol, double[] cpxVal, boolean bSum) {  // bSum is ignored
		double re, im;

		if (iRow > nbus || iCol > nbus) return;
		if (iRow <= 0 || iCol <= 0) return;

		--iRow;
		--iCol;

		re = cpxVal[0];
		im = cpxVal[1];

		if (re == 0.0 && im == 0.0)
			return;

		// stuff this value into the correct partition, transposed
		cs_entry (T22, iCol, iRow, cpxVal);
	}

	public void getElement(int iRow, int iCol, double[] cpxVal) {
		double[] Ax;
		int[] Ap, Ai;
		int i, p;

		cpxVal[0] = 0; cpxVal[1] = 0;

		if (iRow > nbus || iCol > nbus) return;
		if (--iRow < 0) return;
		if (--iCol < 0) return;

		if (T22 != null) {  // have to search the triplet storage, which is not sorted
			Ax = T22.x;
			Ap = T22.p;
			Ai = T22.i;
			for (i = 0; i < T22.nz; i++) {
				if (Ap [i] == iCol && Ai [i] == iRow) {
					cpxVal[0] += Ax [2 * i];
					cpxVal[1] += Ax [2 * i + 1];
				}
			}
		} else if (Y22 != null) {  // faster, duplicates already summed and elements are sorted
			Ax = Y22.x;
			Ap = Y22.p;
			Ai = Y22.i;
			for (p = Ap [iCol]; p < Ap [iCol + 1]; ++p) {
				if (Ai [p] == iRow) {
					cpxVal[0] = Ax [2 * p];
					cpxVal[1] = Ax [2 * p + 1];
					return;
				}
			}
		}
	}

	public int getCompressedMatrix(int nColP, int nnz, int[] pCol,
			int[] rowIdx, double[] mat) {
		int rc = 0;

		if (T22 != null) factor();

		if (Y22 != null && nnz >= this.nnz && nColP > nbus) {
			rc = this.nnz;
			if (rc > 0) {
				System.arraycopy(Y22.x, 0, mat, 0, this.nnz);
				System.arraycopy(Y22.p, 0, pCol, 0, (nbus + 1));
				System.arraycopy(Y22.i, 0, rowIdx, 0, this.nnz);
			}
		}
		return rc;
	}

	public int getTripletMatrix(int nnz, int[] rows, int[] cols, double[] mat) {
		int j, p, rc = 0;
		int[] Ap, Ai;

		if (T22 != null) factor();

		if (Y22 != null && nnz >= this.nnz) {
			rc = this.nnz;
			if (rc > 0) {
				System.arraycopy(Y22.x, 0, mat, 0, this.nnz);
				Ap = Y22.p;
				Ai = Y22.i;
				for (j = 0; j < nbus; j++) {
					for (p = Ap [j]; p < Ap [j + 1]; p++) {
						rows[p] = Ai [p];
						cols[p] = j;
					}
				}
			}
		}
		return rc;
	}

	public boolean isFactored() {
		return factored;
	}

	public void setFactored(boolean factored) {
		this.factored = factored;
	}

}
