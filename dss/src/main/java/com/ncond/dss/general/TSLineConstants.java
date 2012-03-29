/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.shared.CMatrix;
import com.ncond.dss.shared.LineUnits;

import static com.ncond.dss.shared.MathUtil.sqr;

import static java.lang.Math.log;
import static java.lang.Math.sqrt;


public class TSLineConstants extends CableConstants {

	public static final double RHO_TS = 2.3718e-8;  // for copper tape shield

	private double[] diaShield;
	private double[] tapeLayer;
	private double[] tapeLap;

	public TSLineConstants(int numConductors) {
		super(numConductors);

		diaShield = new double[numConds];
		tapeLayer = new double[numConds];
		tapeLap   = new double[numConds];
	}

	public double getDiaShield(int i, LineUnits units) {
		return diaShield[i] * LineUnits.fromMeters(units);
	}

	public double getTapeLayer(int i, LineUnits units) {
		return tapeLayer[i] * LineUnits.fromMeters(units);
	}

	public double getTapeLap(int i) {
		return tapeLap[i];
	}

	public void setDiaShield(int i, LineUnits units, double shield) {
		if (i >= 0 && i < numConds)
			diaShield[i] = shield * LineUnits.toMeters(units);
	}

	public void setTapeLayer(int i, LineUnits units, double layer) {
		if (i >= 0 && i < numConds)
			tapeLayer[i] = layer * LineUnits.toMeters(units);
	}

	public void setTapeLap(int i, double lap) {
		if (i >= 0 && i < numConds)
			tapeLap[i] = lap;
	}

	/**
	 * Compute base Z and YC matrices in ohms/m for this frequency and earth impedance.
	 */
	@Override
	public void calc(double f) {
		Complex Zi, Zspacing;
		Boolean powerFreq;
		Complex Lfactor;
		int i, j;
		double dij, Yfactor;
		int reducedSize;
		int idxi, idxj;
		CMatrix Zmat, Ztemp;
		double resTS;//, radTS;
		double gmrTS;
		double denom, radIn, radOut;

		setFrequency(f);

		reducedSize = (Zreduced != null) ? Zreduced.order() : 0;

		Zreduced = null;
		YcReduced = null;

		Zmatrix.clear();
		YcMatrix.clear();

		// add concentric neutrals to the end of conductor list; they are always reduced
		Zmat = new CMatrix(numConds + numPhases);

		/* For less than 1 kHz use GMR to better match published data */
		Lfactor = new Complex(0.0, w * MU0 / TWO_PI);
		powerFreq = (f < 1000.0 && f > 40.0);

		// self impedances - TS cores and bare neutrals
		for (i = 0; i < numConds; i++) {
			Zi = getZint(i);
			if (powerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				Zspacing = Lfactor.multiply(log(1.0 / GMR[i]));  // use GMR
			} else {
				Zspacing = Lfactor.multiply(log(1.0 / radius[i]));
			}
			Zmat.set(i, i, Zi.add(Zspacing.add(getZe(i, i))));
		}

		// TS self impedances
		for (i = 0; i < numPhases; i++) {
			resTS = 0.3183 * RHO_TS / (diaShield[i] * tapeLayer[i] * sqrt(50.0 / (100.0 - tapeLap[i])));
			gmrTS = 0.5 * (diaShield[i] - tapeLayer[i]);  // per Kersting, to center of TS
			Zspacing = Lfactor.multiply(log(1.0 / gmrTS));
			Zi = new Complex(resTS, 0.0);
			idxi = i + numConds;
			Zmat.set(idxi, idxi, Zi.add(Zspacing.add(getZe(i, i))));
		}

		// mutual impedances - between TS cores and bare neutrals
		for (i = 0; i < numConds; i++) {
			for (j = 0; j < i; j++) {
				dij = sqrt(sqr(X[i] - X[j]) + sqr(Y[i] - Y[j]));
				Zmat.setSym(i, j, Lfactor.multiply( log(1.0 / dij) ).add(getZe(i, j)));
			}
		}

		// mutual impedances - TS to other TS, cores, and bare neutrals
		for (i = 0; i < numPhases; i++) {
			idxi = i + numConds;
			for (j = 0; j < i; j++) {
				// TS to other TS
				idxj = j + numConds;
				dij = sqrt(sqr(X[i] - X[j]) + sqr(Y[i] - Y[j]));
				Zmat.setSym(idxi, idxj, Lfactor.multiply( log(1.0 / dij) ).add(getZe(i, j)));
			}
			for (j = 0; j < numConds; j++) {
				// CN to cores and bare neutrals
				idxj = j;
				gmrTS = 0.5 * (diaShield[i] - tapeLayer[i]);  // per Kersting, to center of TS
				if (i == j) {  // TS to its own phase core
					dij = gmrTS;
				} else {  // TS to another phase or bare neutral
					dij = sqrt(sqr(X[i] - X[j]) + sqr(Y[i] - Y[j]));
				}
				Zmat.setSym(idxi, idxj, Lfactor.multiply( log(1.0 / dij) ).add(getZe(i, j)));
			}
		}

		// reduce out the tape shields
		while (Zmat.order() > numConds) {
			Ztemp = Zmat.kron(Zmat.order());
			Zmat = null;
			Zmat = Ztemp;
		}
		Zmatrix.copyFrom(Zmat);
		Zmat = null;

		// for shielded cables, build the capacitance matrix directly
		// assumes the insulation may lie between semicon layers
		for (i = 0; i < numPhases; i++) {
			Yfactor = TWO_PI * E0 * epsR[i] * w;  // includes frequency so C==>Y
			radOut = 0.5 * diaIns[i];
			radIn = radOut - insLayer[i];
			denom = log(radOut / radIn);
			YcMatrix.set(i, i, new Complex(0.0, Yfactor / denom));
		}

		if (reducedSize > 0)
			kron(reducedSize);  // was reduced so reduce again to same size

		/* else the Zmatrix is OK as last computed */
		rhoChanged = false;
	}

}
