/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.LineUnits;
import com.ncond.dss.shared.MathUtil;

public class CNLineConstants extends CableConstants {

	private int[] kStrand;
	private double[] diaStrand;
	private double[] gmrStrand;
	private double[] rStrand;

	public CNLineConstants(int numConductors) {
		super(numConductors);

		kStrand = new int[getNumConds()];
		diaStrand = new double[getNumConds()];
		gmrStrand = new double[getNumConds()];
		rStrand = new double[getNumConds()];
	}

	/**
	 * Compute base Z and YC matrices in ohms/m for this frequency and earth impedance.
	 */
	@Override
	public void calc(double f) {
		Complex Zi, Zspacing;
		boolean powerFreq;
		Complex Lfactor;
		int i, j;
		double dij, Yfactor;
		int reducedSize;
		int n, idxi, idxj;
		CMatrix Zmat, Ztemp;
		double resCN, radCN;
		double gmrCN;
		double denom, radIn, radOut;

		setFrequency(f);  // this has side effects

		reducedSize = (Zreduced != null) ? Zreduced.order() : 0;

		Zreduced = null;
		YcReduced = null;

		Zmatrix.clear();
		YcMatrix.clear();

		// add concentric neutrals to the end of conductor list; they are always reduced
		n = getNumConds() + getNPhases();
		Zmat = new CMatrix(n);

		/* For less than 1 kHz use GMR to better match published data */
		Lfactor = new Complex(0.0, w * MU0 / LineConstants.TWO_PI);
		powerFreq = (f < 1000.0 && f > 40.0);

		// self impedances - CN cores and bare neutrals
		for (i = 0; i < getNumConds(); i++) {
			Zi = getZint(i);
			if (powerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				Zspacing = Lfactor.multiply(Math.log(1.0 / GMR[i]));  // use GMR
			} else {
				Zspacing = Lfactor.multiply(Math.log(1.0 / radius[i]));
			}
			Zmat.set(i, i, Zi.add(Zspacing.add(getZe(i, i))));
		}

		// CN self impedances
		for (i = 0; i < getNPhases(); i++) {
			resCN = rStrand[i] / kStrand[i];
			radCN = 0.5 * (diaCable[i] - diaStrand[i]);
			gmrCN = Math.pow(gmrStrand[i] * kStrand[i] * Math.pow(radCN, kStrand[i] - 1.0),
					1.0 / kStrand[i]);
			Zspacing = Lfactor.multiply(Math.log(1.0 / gmrCN));
			Zi = new Complex(resCN, 0.0);
			idxi = i + getNumConds();
			Zmat.set(idxi, idxi, Zi.add(Zspacing.add(getZe(i, i))));
		}

		// mutual impedances - between CN cores and bare neutrals
		for (i = 0; i < getNumConds(); i++) {
			for (j = 0; j < i - 1; j++) {
				dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				Zmat.setSym(i, j, Lfactor.multiply(Math.log(1.0 / dij)).add(getZe(i, j)));
			}
		}

		// mutual impedances - CN to other CN, cores, and bare neutrals
		for (i = 0; i < getNPhases(); i++) {
			idxi = i + getNumConds();
			for (j = 0; j < i - 1; j++) {  // CN to other CN
				idxj = j + getNumConds();
				dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				Zmat.setSym(idxi, idxj, Lfactor.multiply(Math.log(1.0 / dij)).add(getZe(i, j)));
			}
			for (j = 0; j < getNumConds(); j++) {  // CN to cores and bare neutrals
				idxj = j;
				radCN = 0.5 * (diaCable[i] - diaStrand[i]);
				if (i == j) {  // CN to its own phase core
					dij = radCN;
				} else {  // CN to another phase or bare neutral
					dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
					dij = Math.pow(Math.pow(dij, kStrand[i]) - Math.pow(radCN, kStrand[i]), 1.0 / kStrand[i]);
				}
				Zmat.setSym(idxi, idxj, Lfactor.multiply(Math.log(1.0 / dij)).add(getZe(i, j)));
			}
		}

		// reduce out the CN
		while (Zmat.order() > getNumConds()) {
			Ztemp = Zmat.kron(Zmat.order());
			Zmat = Ztemp;
		}
		Zmatrix.copyFrom(Zmat);
		Zmat = null;

		// for shielded cables, build the capacitance matrix directly
		// assumes the insulation may lie between semicon layers
		for (i = 0; i < getNPhases(); i++) {
			Yfactor = LineConstants.TWO_PI * LineConstants.E0 * epsR[i] * w;  // includes frequency so C==>Y
			radOut = 0.5 * diaIns[i];
			radIn = radOut - insLayer[i];
			denom = Math.log(radOut / radIn);
			YcMatrix.set(i, i, new Complex(0.0, Yfactor / denom));
		}

		if (reducedSize > 0)
			kron(reducedSize);  // was reduced so reduce again to same size

		/* else the Zmatrix is OK as last computed */
		rhoChanged = false;
	}

	public int getKStrand(int i) {
		return kStrand[i];
	}

	public double getDiaStrand(int i) {
		return diaStrand[i];
	}

	public double getGmrStrand(int i) {
		return gmrStrand[i];
	}

	public double getRStrand(int i) {
		return rStrand[i];
	}

	public void setKStrand(int i, int kstrand) {
		if (i >= 0 && i <= getNumConds())
			this.kStrand[i] = kstrand;
	}

	public void setDiaStrand(int i, LineUnits units, double diastrand) {
		if (i >= 0 && i <= getNumConds())
			diaStrand[i] = diastrand * LineUnits.toMeters(units);
	}

	public void setGmrStrand(int i, LineUnits units, double gmrstrand) {
		if (i >= 0 && i <= getNumConds())
			gmrStrand[i] = gmrstrand * LineUnits.toMeters(units);
	}

	public void setRStrand(int i, LineUnits units, double rstrand) {
		if (i >= 0 && i <= getNumConds())
			rStrand[i] = rstrand * LineUnits.toPerMeter(units);
	}

}
