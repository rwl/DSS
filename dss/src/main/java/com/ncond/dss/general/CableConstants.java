/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.LineUnits;
import com.ncond.dss.shared.MathUtil;

public class CableConstants extends LineConstants {

	protected double[] epsR;
	protected double[] insLayer;
	protected double[] diaIns;
	protected double[] diaCable;

	public CableConstants(int numConductors) {
		super(numConductors);
		epsR = new double[getNumConds()];
		insLayer = new double[getNumConds()];
		diaIns = new double[getNumConds()];
		diaCable = new double[getNumConds()];
	}

	/**
	 * Don't reduce Y, it has zero neutral capacitance.
	 */
	@Override
	public void kron(int norder) {
		CMatrix ZTemp;
		int i, j;

		ZTemp = Zmatrix;
		if (frequency >= 0.0 && norder > 0 && norder < getNumConds()) {
			while (ZTemp.order() > norder) {
				Zreduced = ZTemp.kron(ZTemp.order());  // eliminate last row
				ZTemp = Zreduced;
			}
			// now copy part of YcMatrix to YcReduced
			YcReduced = new CMatrix(norder);
			for (i = 0; i < norder; i++)
				for (j = 0; j < norder; j++)
					YcReduced.set(i, j, YcMatrix.get(i, j));
		}
	}

	@Override
	public boolean conductorsInSameSpace(String[] errorMessage) {
		int i, j;
		double dij;
		double Ri, Rj;

		boolean same = false;

		for (i = 0; i < getNumConds(); i++)
			if (Y[i] >= 0.0) {
				same = true;
				errorMessage[0] += String.format("Cable %d height must be < 0. ", i);
				return same;
			}

		for (i = 0; i < getNumConds(); i++) {
			Ri = (i < getNPhases()) ? radius[i] : 0.5 * diaCable[i];

			for (j = i + 1; j < getNumConds(); j++) {
				Rj = (j < getNPhases()) ? radius[j] : 0.5 * diaCable[j];

				dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				if (dij < (Ri + Rj)) {
					same = true;
					errorMessage[0] += String.format("Cable conductors %d and %d occupy the same space.", i, j);
					return same;
				}
			}
		}
		return same;
	}

	public double getEpsR(int i) {
		return epsR[i];
	}

	public void setEpsR(int i, double epsr) {
		epsR[i] = epsr;
	}

	public double getInsLayer(int i, LineUnits units) {
		return insLayer[i] * LineUnits.fromMeters(units);
	}

	public void setInsLayer(int i, LineUnits units, double inslayer) {
		if (i >= 0 && i < getNumConds())
			insLayer[i] = inslayer * LineUnits.toMeters(units);
	}

	public double getDiaIns(int i, LineUnits units) {
		return diaIns[i] * LineUnits.fromMeters(units);
	}

	public void setDiaIns(int i, LineUnits units, double diains) {
		if (i >= 0 && i < getNumConds())
			diaIns[i] = diains * LineUnits.toMeters(units);
	}

	public double getDiaCable(int i, LineUnits units) {
		return diaCable[i] * LineUnits.fromMeters(units);
	}

	public void setDiaCable(int i, LineUnits units, double diacable) {
		if (i >= 0 && i < getNumConds())
			diaCable[i] = diacable * LineUnits.toMeters(units);
	}

}
