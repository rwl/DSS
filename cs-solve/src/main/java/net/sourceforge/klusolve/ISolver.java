package net.sourceforge.klusolve;

import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcsa;

public interface ISolver {

	void initDefaults();

	void clear();

	int factorSystem();

	/**
	 * @param acxB current injections on input
	 * @param acxX node voltages on output,
	 * no provision for voltage sources
         */
	int solveSystem(double[] acxX, double[] acxB);

	/**
	 * This resets and reinitializes the sparse matrix, nI == nBus.
	 */
	int initialize(int nBus, int nV, int nI);

	int initialize(int nBus);

	/**
	 * @return the matrix order (number of nodes)
	 */
	int getSize();

	/* Metrics */

	/**
	 * @return the number of non-zero entries in factored matrix
	 */
	int getSparseNNZ();

	/**
	 * @return the number of non-zero entries in the original matrix
	 */
	int getNNZ();

	/**
	 * @return a quick estimate of the reciprocal of condition number
	 */
	double getRCond();

	/**
	 * @return the pivot element growth factor
	 */
	double getRGrowth();

	/**
	 * @return a more accurate estimate of condition number
	 */
	double getCondEst();

	/**
	 * @return the number of floating point operations to factor
	 */
	double getFlops();

	/**
	 * @return a column number corresponding to a singularity, or -1 if not singular
	 */
	int getSingularCol();

//	void addMatrix(int[] aidBus, MatrixComplex pcxm, int bSum);

	/**
	 * returns 1 for success, -1 for a singular matrix
	 * returns 0 for another error, most likely the matrix is too large for int
	 *
	 * @return
	 */
	int factor();

	/**
	 * input: acxVbus[0] is ground voltage
	 * acxVbus[1..nBus] are current injections
	 * output: acxVbus[1..nBus] are solved voltages
	 *
	 * @param acxVbus
	 */
	void solve(DZcsa acxVbus);

	/**
	 * Returns the number of connected components (cliques) in the whole system graph
	 * (i.e., considers Y11, Y12, and Y21 in addition to Y22)
	 * store the island number (1-based) for each node in idClique
	 *
	 * The KLU factorization might have some information about cliques in Y22 only,
	 * but we want to consider the whole system, so this function
	 * performs a new DFS on the compressed non-zero pattern
	 *
	 * This function could behave differently than before,
	 * since the compression process removes numerical zero elements
	 *
	 * @param idClique
	 * @return
	 */
	int findIslands(int[] idClique);

	/**
	 * returns the row > 0 if a zero appears on the diagonal
	 * calls Factor if necessary
	 * note: the EMTP terminology is "floating subnetwork"
	 *
	 * @return
	 */
	int findDisconnectedSubnetwork();

	/**
	 * Maintains allocations, zeros matrix values
	 */
	void zero();

	void addElement(int iRow, int iCol, double[] cpxVal, boolean bSum);

	/**
	 * Return the sum of elements at FIXME: 1-based [iRow, iCol]
	 *
	 * @param iRow
	 * @param iCol
	 * @param cpxVal
	 */
	void getElement(int iRow, int iCol, double[] cpxVal);

	/**
	 * @return 1 on success
	 */
	int addPrimitiveMatrix(int nOrder, int[] pNodes, double[] pMat);

	/**
	 * Return in compressed triplet form.
	 *
	 * @param nColP
	 * @param nNZ
	 * @param pColP
	 * @param pRowIdx
	 * @param pMat
	 * @return 1 for success, 0 for a size mismatch
	 */
	int getCompressedMatrix(int nColP, int nNZ, int[] pColP, int[] pRowIdx, double[] pMat);

	int getTripletMatrix(int nNZ, int[] pRows, int[] pCols, double[] pMat);

	boolean isFactored();

	void setFactored(boolean factored);

}