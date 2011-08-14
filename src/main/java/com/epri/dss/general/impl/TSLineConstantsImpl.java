package com.epri.dss.general.impl;

import com.epri.dss.general.TSLineConstants;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

public class TSLineConstantsImpl extends CableConstantsImpl implements TSLineConstants {

	private double[] DiaShield;
	private double[] TapeLayer;
	private double[] TapeLap;

	public TSLineConstantsImpl(int NumConductors) {
		super(NumConductors);

		this.DiaShield = new double[numConds];
		this.TapeLayer = new double[numConds];
		this.TapeLap   = new double[numConds];
	}

	public double getDiaShield(int i, int units) {
		return DiaShield[i] * LineUnits.fromMeters(units);
	}

	public double getTapeLayer(int i, int units) {
		return TapeLayer[i] * LineUnits.fromMeters(units);
	}

	public double getTapeLap(int i) {
		return TapeLap[i];
	}

	public void setDiaShield(int i, int units, double diaShield) {
		if ((i >= 0) && (i < numConds))
			DiaShield[i] = diaShield * LineUnits.toMeters(units);
	}

	public void setTapeLayer(int i, int units, double tapeLayer) {
		if ((i >= 0) && (i < numConds))
			TapeLayer[i] = tapeLayer * LineUnits.toMeters(units);
	}

	public void setTapeLap(int i, double tapeLap) {
		if ((i >= 0) && (i < numConds))
			TapeLap[i] = tapeLap;
	}

	/**
	 * Compute base Z and YC matrices in ohms/m for this frequency and earth impedance.
	 */
	@Override
	public void calc(double f) {
		Complex Zi, Zspacing;
		Boolean PowerFreq;
		Complex Lfactor;
		int i, j;
		double Dij, Yfactor;
		int ReducedSize;
		int N, idxi, idxj;
		CMatrix Zmat, Ztemp;
		double ResTS, RadTS;
		double GmrTS;
		double Denom, RadIn, RadOut;

		setFrequency(f);

		if (ZReduced != null) {
			ReducedSize = ZReduced.getNOrder();
			ZReduced = null;
		} else {
			ReducedSize = 0;
		}

		if (YcReduced != null) YcReduced = null;
		ZReduced = null;
		YcReduced = null;

		ZMatrix.clear();
		YcMatrix.clear();

		// add concentric neutrals to the end of conductor list; they are always reduced
		N = numConds + numPhases;
		Zmat = new CMatrixImpl(N);

		/* For less than 1 kHz use GMR to better match published data */
		Lfactor = new Complex(0.0, w * MU0 / TWO_PI);
		if ((f < 1000.0) && (f > 40.0)) {
			PowerFreq = true;
		} else {
			PowerFreq = false;
		}

		// self impedances - TS cores and bare neutrals
		for (i = 0; i < numConds; i++) {
			Zi = getZint(i);
			if (PowerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				Zspacing = Lfactor.multiply( Math.log(1.0 / GMR[i]) );  // use GMR
			} else {
				Zspacing = Lfactor.multiply( Math.log(1.0 / radius[i]) );
			}
			Zmat.setElement(i, i, Zi.add( Zspacing.add(getZe(i, i)) ));
		}

		// TS self impedances
		for (i = 0; i < numPhases; i++) {
			ResTS = 0.3183 * RHO_TS / (DiaShield[i] * TapeLayer[i] * Math.sqrt(50.0 / (100.0 - TapeLap[i])));
			GmrTS = 0.5 * (DiaShield[i] - TapeLayer[i]);  // per Kersting, to center of TS
			Zspacing = Lfactor.multiply( Math.log(1.0 / GmrTS) );
			Zi = new Complex(ResTS, 0.0);
			idxi = i + numConds;
			Zmat.setElement(idxi, idxi, Zi.add( Zspacing.add(getZe(i, i)) ));
		}

		// mutual impedances - between TS cores and bare neutrals
		for (i = 0; i < numConds; i++) {
			for (j = 0; j < i; j++) {  // TODO Check zero based indexing
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				Zmat.setElemSym(i, j, Lfactor.multiply( Math.log(1.0 / Dij)).add(getZe(i, j)));
			}
		}

		// mutual impedances - TS to other TS, cores, and bare neutrals
		for (i = 0; i < numPhases; i++) {
			idxi = i + numConds;
			for (j = 0; j < i; j++) {  // TODO Check zero based indexing
				// TS to other TS
				idxj = j + numConds;
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				Zmat.setElemSym(idxi, idxj, Lfactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
			for (j = 0; j < numConds; j++) {
				// CN to cores and bare neutrals
				idxj = j;
				GmrTS = 0.5 * (DiaShield[i] - TapeLayer[i]);  // per Kersting, to center of TS
				if (i == j) {  // TS to its own phase core
					Dij = GmrTS;
				} else {  // TS to another phase or bare neutral
					Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				}
				Zmat.setElemSym(idxi, idxj, Lfactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
		}

		// reduce out the tape shields
		while (Zmat.getNOrder() > numConds) {
			Ztemp = Zmat.kron(Zmat.getNOrder());
			Zmat = null;
			Zmat = Ztemp;
		}
		ZMatrix.copyFrom(Zmat);
		Zmat = null;

		// for shielded cables, build the capacitance matrix directly
		// assumes the insulation may lie between semicon layers
		for (i = 0; i < numPhases; i++) {
			Yfactor = TWO_PI * E0 * epsR[i] * w;  // includes frequency so C==>Y
		    RadOut = 0.5 * diaIns[i];
		    RadIn  = RadOut - insLayer[i];
		    Denom  = Math.log(RadOut / RadIn);
			YcMatrix.setElement(i, i, new Complex(0.0, Yfactor / Denom));
		}

		if (ReducedSize > 0)
			Kron(ReducedSize);  // was reduced so reduce again to same size

		/* else the Zmatrix is OK as last computed */
		rhoChanged = false;
	}

}
