/* ------------------------------------------------------------------------- */
/* Copyright (C) 2008, EnerNex Corporation. All rights reserved.             */
/* Copyright (C) 2012, Richard Lincoln.                                      */
/* Licensed under the GNU Lesser General Public License (LGPL) v 2.1         */
/* ------------------------------------------------------------------------- */

package net.sourceforge.klusolve;

import edu.emory.mathcs.csparsej.tdcomplex.DZcs_common.DZcsa;

public class MatrixComplex {

	DZcsa acx;
	int nRow, nCol;

	double[] get_acx(int i, int j) {
		return acx.get(i * nCol + j);
	}

}
