package net.sourceforge.klusolve;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.math.complex.Complex;

import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcsa;


abstract public class BaseSolve {

	protected static boolean SYMMETRIC_MATRIX = true;

	protected static Map<UUID, ISystem> sysMap = new HashMap<UUID, ISystem>();


	private static double[] asArray(Complex c) {
		return new double[] {c.getReal(), c.getImaginary()};
	}

	private static double[] asArray(Complex[] x) {
		return asArray(x, 0);
	}

	private static double[] asArray(Complex[] x, int x_offset) {
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
		fromArray(x, 0, z, 0);
	}

	private static void fromArray(double[] x, int x_offset, Complex[] z, int z_offset) {
		double re, im;

		for (int i = x_offset; i < x.length; i+=2) {
			re = x[(2 * x_offset) + i];
			im = x[(2 * x_offset) + i + 1];

			z[z_offset + (i / 2)] = new Complex(re, im);
		}
	}

	/**
	 *
	 * @param path
	 * @param iAction 0 to close, 1 to rewrite, 2 to append
	 */
	public static void setLogFile(String path, int iAction) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param uuid
	 * @return 1 for success, 0 for invalid handle
	 */
	public static int zeroSparseSet(UUID uuid) {
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			sys.zero();
			sys.setFactored(false);
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
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			if (sys.factorSystem() == 0) {
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
		DZcsa acxX, acxB;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			if (!sys.isFactored()) {
				sys.factorSystem();
			}
			if (sys.isFactored()) {
				acxX = new DZcsa(asArray(x, x_offset));
				acxB = new DZcsa(asArray(b, b_offset));

				sys.solveSystem(acxX, acxB);

				fromArray(acxB.x, 0, b, b_offset);
				return 1;
			} else {
				return 2;
			}
		}
		return 0;
	}

	/**
	 * @return 1 for success, 0 for invalid id
	 */
	public static int deleteSparseSet(UUID uuid) {
		ISystem sys = sysMap.remove(uuid);

		return (sys != null) ? 1 : 0;
	}

	/**
	 * Deprecated, use addPrimitiveMatrix instead.
	 */
	public static int addMatrixElement(UUID uuid, int i, int j, Complex val) {
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			sys.addElement(i, j, asArray(val), true);
			if (SYMMETRIC_MATRIX) {
				sys.addElement(j, i, asArray(val), true);
			}
			sys.setFactored(false);
			return 1;
		}

		return 0;
	}

	public static int getMatrixElement(UUID y, int i, int i2, Complex[] c) {
		double[] a;
		ISystem sys = sysMap.get(y);

		if (sys != null) {
			a = asArray(c[0]);
			sys.getElement(i, i2, a);
			c[0] = new Complex(a[0], a[1]);
			return 1;
		}
		return 0;
	}

	/**
	 *
	 * @param uuid
	 * @param res the matrix order (number of nodes)
	 * @return
	 */
	public static int getSize(UUID uuid, int[] res) {
		int order;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			order = sys.getSize();
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
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			nnz = sys.getNNZ();
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
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			nnz = sys.getSparseNNZ();
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
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			rcond = sys.getRCond();
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
	public static int getRGrowth(UUID uuid, double[] res) {
		double rgrowth;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			rgrowth = sys.getRGrowth();
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
	public static int getCondEst(UUID uuid, double[] res) {
		double cond_est;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			cond_est = sys.getCondEst();
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
	public static int getFlops(UUID uuid, double[] res) {
		double flops;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			flops = sys.getFlops();
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
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			col = sys.getSingularCol();
			res[0] = col;
			return 1;
		} else {
			return 0;
		}
	}

	public static int addPrimitiveMatrix(UUID uuid, int nOrder, int[] nodes,
			Complex[] mat) {
		return addPrimitiveMatrix(uuid, nOrder, nodes, 0, mat, 0);
	}

	/**
	 * @param uuid
	 * @param nOrder
	 * @param nodes
	 * @param mat
	 * @return 1 for success, 0 for invalid handle or a node number out of range
	 */
	public static int addPrimitiveMatrix(UUID uuid, int nOrder, int[] nodes,
			int node_offset, Complex[] mat, int m_offset) {
		int rc = 0;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			DZcsa a = new DZcsa(asArray(mat, m_offset));
			rc = sys.addPrimitiveMatrix(nOrder, nodes, node_offset, a);
		}
		return rc;
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
	public static int getCompressedMatrix(UUID uuid, int nCol, int nnz, int[] pCol,
			int[] rowIdx, Complex[] mat) {

		DZcsa a;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			a = new DZcsa(asArray(mat));
			if (sys.getCompressedMatrix(nCol, nnz, pCol, rowIdx, a) != 0) {
				fromArray(a.x, mat);
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
	public static int getTripletMatrix(UUID uuid, int nnz, int[] rows, int[] cols, Complex[] mat) {
		DZcsa a;
		ISystem sys = sysMap.get(uuid);

		if (sys != null) {
			a = new DZcsa(asArray(mat));
			if (sys.getTripletMatrix(nnz, rows, cols, a) != 0) {
				fromArray(a.x, mat);
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
	public static int findIslands(UUID uuid, int nOrder, int[] nodes) {

		ISystem sys = sysMap.get(uuid);

		if (sys != null && nOrder >= sys.getSize()) {
			return sys.findIslands(nodes);
		}
		return 0;
	}

}
