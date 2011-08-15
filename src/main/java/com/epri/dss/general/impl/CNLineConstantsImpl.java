package com.epri.dss.general.impl;

import com.epri.dss.general.CNLineConstants;
import com.epri.dss.general.LineConstants;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import org.apache.commons.math.complex.Complex;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

public class CNLineConstantsImpl extends CableConstantsImpl implements CNLineConstants {

	private int[] kStrand;
	private double[] diaStrand;
	private double[] gmrStrand;
	private double[] rStrand;

	public CNLineConstantsImpl(int numConductors) {
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
		Complex Zi, ZSpacing;
		boolean powerFreq;
		Complex LFactor;
		int i, j;
		double Dij, YFactor;
		int reducedSize;
		int n, idxi, idxj;
		CMatrix ZMat, ZTemp;
		double resCN, radCN;
		double gmrCN;
		double denom, radIn, radOut;

		setFrequency(f);  // this has side effects

		if (ZReduced != null) {
			reducedSize = ZReduced.getNOrder();
			ZReduced = null;
		} else {
			reducedSize = 0;
		}
		if (YcReduced != null) YcReduced = null;
		ZReduced = null;
		YcReduced = null;

		ZMatrix.clear();
		YcMatrix.clear();

		// add concentric neutrals to the end of conductor list; they are always reduced
		n = getNumConds() + getNPhases();
		ZMat = new CMatrixImpl(n);

		/* For less than 1 kHz use GMR to better match published data */
		LFactor = new Complex(0.0, w * MU0 / LineConstants.TWO_PI);
		if ((f < 1000.0) && (f > 40.0)) {
			powerFreq = true;
		} else {
			powerFreq= false;
		}

		// self impedances - CN cores and bare neutrals
		for (i = 0; i < getNumConds(); i++) {
			Zi = getZint(i);
			if (powerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				ZSpacing = LFactor.multiply( Math.log(1.0 / GMR[i]) );  // use GMR
			} else {
				ZSpacing = LFactor.multiply( Math.log(1.0 / radius[i]) );
			}
			ZMat.setElement(i, i, Zi.add( ZSpacing.add(getZe(i, i)) ));
		}

		// CN self impedances
		for (i = 0; i < getNPhases(); i++) {
			resCN = rStrand[i] / kStrand[i];
			radCN = 0.5 * (diaCable[i] - diaStrand[i]);
			gmrCN = Math.pow(gmrStrand[i] * kStrand[i] * Math.pow(radCN, kStrand[i] - 1.0),
					1.0 / kStrand[i]);
			ZSpacing = LFactor.multiply(Math.log(1.0 / gmrCN));
			Zi = new Complex(resCN, 0.0);
			idxi = i + getNumConds();
			ZMat.setElement(idxi, idxi, Zi.add( ZSpacing.add(getZe(i, i)) ));
		}

		// mutual impedances - between CN cores and bare neutrals
		for (i = 0; i < getNumConds(); i++) {
			for (j = 0; j < i - 1; j++) {
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				ZMat.setElemSym(i, j, LFactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
		}

		// mutual impedances - CN to other CN, cores, and bare neutrals
		for (i = 0; i < getNPhases(); i++) {
			idxi = i + getNumConds();
			for (j = 0; j < i - 1; j++) {  // CN to other CN
				idxj = j + getNumConds();
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				ZMat.setElemSym(idxi, idxj, LFactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
			for (j = 0; j < getNumConds(); j++) {  // CN to cores and bare neutrals
				idxj = j;
				radCN = 0.5 * (diaCable[i] - diaStrand[i]);
				if (i == j) {  // CN to its own phase core
					Dij = radCN;
				} else {  // CN to another phase or bare neutral
					Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
					Dij = Math.pow(Math.pow(Dij, kStrand[i]) - Math.pow(radCN, kStrand[i]),
							1.0 / kStrand[i]);
				}
				ZMat.setElemSym(idxi, idxj, LFactor.multiply( Math.log(1.0 / Dij)).add(getZe(i, j)));
			}
		}

		// reduce out the CN
		while (ZMat.getNOrder() > getNumConds()) {
			ZTemp = ZMat.kron(ZMat.getNOrder());
			ZMat = null;
			ZMat = ZTemp;
		}
		ZMatrix.copyFrom(ZMat);
		ZMat = null;

		// for shielded cables, build the capacitance matrix directly
		// assumes the insulation may lie between semicon layers
		for (i = 0; i < getNPhases(); i++) {
			YFactor = LineConstants.TWO_PI * LineConstants.E0 * epsR[i] * w;  // includes frequency so C==>Y
		    radOut = 0.5 * diaIns[i];
		    radIn = radOut - insLayer[i];
		    denom = Math.log(radOut / radIn);
			YcMatrix.setElement(i, i, new Complex(0.0, YFactor / denom));
		}

		if (reducedSize > 0)
			Kron(reducedSize);  // was reduced so reduce again to same size

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
		if ((i >= 0) && (i <= getNumConds()))
			this.kStrand[i] = kstrand;
	}

	public void setDiaStrand(int i, int units, double diastrand) {
		if ((i >= 0) && (i <= getNumConds()))
			diaStrand[i] = diastrand * LineUnits.toMeters(units);
	}

	public void setGmrStrand(int i, int units, double gmrstrand) {
		if ((i >= 0) && (i <= getNumConds()))
			gmrStrand[i] = gmrstrand * LineUnits.toMeters(units);
	}

	public void setRStrand(int i, int units, double rstrand) {
		if ((i >= 0) && (i <= getNumConds()))
			rStrand[i] = rstrand * LineUnits.toPerMeter(units);
	}

}
