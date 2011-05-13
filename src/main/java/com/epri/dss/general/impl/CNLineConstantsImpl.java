package com.epri.dss.general.impl;

import com.epri.dss.general.CNLineConstants;
import com.epri.dss.general.LineConstants;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

public class CNLineConstantsImpl extends CableConstantsImpl implements CNLineConstants {

	private int[] kStrand;
	private double[] DiaStrand;
	private double[] GmrStrand;
	private double[] RStrand;

	public CNLineConstantsImpl(int NumConductors) {
		super(NumConductors);

		this.kStrand = new int[getNumConds()];
		this.DiaStrand = new double[getNumConds()];
		this.GmrStrand = new double[getNumConds()];
		this.RStrand = new double[getNumConds()];
	}

	/**
	 * Compute base Z and YC matrices in ohms/m for this frequency and earth impedance.
	 */
	@Override
	public void calc(double f) {
		Complex Zi, Zspacing;
		boolean PowerFreq;
		Complex LFactor;
		int i, j;
		double Dij, Yfactor;
		int ReducedSize;
		int N, idxi, idxj;
		CMatrix Zmat, Ztemp;
		double ResCN, RadCN;
		double GmrCN;
		double Denom, RadIn;

		Frequency = f;  // this has side effects

		if (Zreduced != null) {
			ReducedSize = Zreduced.getNOrder();
			Zreduced = null;
		} else {
			ReducedSize = 0;
		}
		if (YCreduced != null) YCreduced = null;
		Zreduced = null;
		YCreduced = null;

		Zmatrix.clear();
		YCmatrix.clear();

		// add concentric neutrals to the end of conductor list; they are always reduced
		N = getNumConds() + getNumPhases();
		Zmat = new CMatrixImpl(N);

		/* For less than 1 kHz use GMR to better match published data */
		LFactor = new Complex(0.0, w * mu0 / LineConstants.TwoPI);
		if ((f < 1000.0) && (f > 40.0)) {
			PowerFreq = true;
		} else {
			PowerFreq= false;
		}

		// Self Impedances - CN cores and bare neutrals
		for (i = 0; i < getNumConds(); i++) {
			Zi = getZint(i);
			if (PowerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				Zspacing = LFactor.multiply( Math.log(1.0 / GMR[i]) );  // use GMR
			} else {
				Zspacing = LFactor.multiply( Math.log(1.0 / radius[i]) );
			}
			Zmat.setElement(i, i, Zi.add( Zspacing.add(getZe(i, i)) ));
		}

		// CN self impedances
		for (i = 0; i < getNumPhases(); i++) {
			ResCN = RStrand[i] / kStrand[i];
			RadCN = 0.5 * (DiaCable[i] - DiaStrand[i]);
			GmrCN = Math.pow(GmrStrand[i] * kStrand[i] * Math.pow(RadCN, kStrand[i] - 1.0),
					1.0 / kStrand[i]);
			Zspacing = LFactor.multiply(Math.log(1.0 / GmrCN));
			Zi = new Complex(ResCN, 0.0);
			idxi = i + getNumConds();
			Zmat.setElement(idxi, idxi, Zi.add( Zspacing.add(getZe(i, i)) ));
		}

		// Mutual Impedances - between CN cores and bare neutrals
		for (i = 0; i < getNumConds(); i++) {
			for (j = 0; j < i - 1; j++) {
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				Zmat.setElemSym(i, j, LFactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
		}

		// Mutual Impedances - CN to other CN, cores, and bare neutrals
		for (i = 0; i < getNumPhases(); i++) {
			idxi = i + getNumConds();
			for (j = 0; j < i - 1; j++) {  // CN to other CN
				idxj = j + getNumConds();
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				Zmat.setElemSym(idxi, idxj, LFactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
			for (j = 0; j < getNumConds(); j++) {  // CN to cores and bare neutrals
				idxj = j;
				RadCN = 0.5 * (DiaCable[i] - DiaStrand[i]);
				if (i == j) {  // CN to its own phase core
					Dij = RadCN;
				} else {  // CN to another phase or bare neutral
					Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
					Dij = Math.pow(Math.pow(Dij, kStrand[i]) - Math.pow(RadCN, kStrand[i]),
							1.0 / kStrand[i]);
				}
				Zmat.setElemSym(idxi, idxj, LFactor.multiply( Math.log(1.0 / Dij)).add(getZe(i, j)));
			}
		}

		// reduce out the CN
		while (Zmat.getNOrder() > getNumConds()) {
			Ztemp = Zmat.kron(Zmat.getNOrder());
			Zmat = null;
			Zmat = Ztemp;
		}
		Zmatrix.copyFrom(Zmat);
		Zmat = null;

		// for shielded cables, build the capacitance matrix directly
		for (i = 0; i < getNumPhases(); i++) {
			Yfactor = LineConstants.TwoPI * LineConstants.e0 * EpsR[i] * w;  // includes frequency so C==>Y
			RadCN = 0.5 * (DiaCable[i] - DiaStrand[i]);
			RadIn = radius[i];  // per Kersting, could make it the inside of insulating layer
			Denom = Math.log(RadCN / RadIn) - (1.0 / kStrand[i]) * Math.log(kStrand[i] * 0.5 * DiaStrand[i] / RadCN);
			YCmatrix.setElement(i, i, new Complex(0.0, Yfactor / Denom));
		}

		if (ReducedSize > 0)
			Kron(ReducedSize);  // Was reduced so reduce again to same size

		/* Else the Zmatrix is OK as last computed */
		RhoChanged = false;
	}

	public int getkStrand(int i) {
		return kStrand[i];
	}

	public double getDiaStrand(int i) {
		return DiaStrand[i];
	}

	public double getGmrStrand(int i) {
		return GmrStrand[i];
	}

	public double getRStrand(int i) {
		return RStrand[i];
	}

	public void setkStrand(int i, int kStrand) {
		if ((i >= 0) && (i <= getNumConds()))
			this.kStrand[i] = kStrand;
	}

	public void setDiaStrand(int i, int units, double diaStrand) {
		if ((i >= 0) && (i <= getNumConds()))
			DiaStrand[i] = diaStrand * LineUnits.toMeters(units);
	}

	public void setGmrStrand(int i, int units, double gmrStrand) {
		if ((i >= 0) && (i <= getNumConds()))
			GmrStrand[i] = gmrStrand * LineUnits.toMeters(units);
	}

	public void setRStrand(int i, int units, double rStrand) {
		if ((i >= 0) && (i <= getNumConds()))
			RStrand[i] = rStrand * LineUnits.toPerMeter(units);
	}

}
