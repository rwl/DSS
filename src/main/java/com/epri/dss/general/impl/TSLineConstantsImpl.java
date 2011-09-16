package com.epri.dss.general.impl;

import com.epri.dss.general.TSLineConstants;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import org.apache.commons.math.complex.Complex;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

public class TSLineConstantsImpl extends CableConstantsImpl implements TSLineConstants {

	private double[] diaShield;
	private double[] tapeLayer;
	private double[] tapeLap;

	public TSLineConstantsImpl(int numConductors) {
		super(numConductors);

		diaShield = new double[numConds];
		tapeLayer = new double[numConds];
		tapeLap   = new double[numConds];
	}

	public double getDiaShield(int i, int units) {
		return diaShield[i] * LineUnits.fromMeters(units);
	}

	public double getTapeLayer(int i, int units) {
		return tapeLayer[i] * LineUnits.fromMeters(units);
	}

	public double getTapeLap(int i) {
		return tapeLap[i];
	}

	public void setDiaShield(int i, int units, double shield) {
		if ((i >= 0) && (i < numConds))
			diaShield[i] = shield * LineUnits.toMeters(units);
	}

	public void setTapeLayer(int i, int units, double layer) {
		if ((i >= 0) && (i < numConds))
			tapeLayer[i] = layer * LineUnits.toMeters(units);
	}

	public void setTapeLap(int i, double lap) {
		if ((i >= 0) && (i < numConds))
			tapeLap[i] = lap;
	}

	/**
	 * Compute base Z and YC matrices in ohms/m for this frequency and earth impedance.
	 */
	@Override
	public void calc(double f) {
		Complex Zi, ZSpacing;
		Boolean powerFreq;
		Complex LFactor;
		int i, j;
		double Dij, YFactor;
		int reducedSize;
		int n, idxi, idxj;
		CMatrix ZMat, ZTemp;
		double resTS, radTS;
		double gmrTS;
		double denom, radIn, radOut;

		setFrequency(f);

		if (ZReduced != null) {
			reducedSize = ZReduced.order();
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
		n = numConds + numPhases;
		ZMat = new CMatrixImpl(n);

		/* For less than 1 kHz use GMR to better match published data */
		LFactor = new Complex(0.0, w * MU0 / TWO_PI);
		if ((f < 1000.0) && (f > 40.0)) {
			powerFreq = true;
		} else {
			powerFreq = false;
		}

		// self impedances - TS cores and bare neutrals
		for (i = 0; i < numConds; i++) {
			Zi = getZint(i);
			if (powerFreq) {  // for less than 1 kHz, use published GMR
				Zi = new Complex(Zi.getReal(), 0.0);
				ZSpacing = LFactor.multiply( Math.log(1.0 / GMR[i]) );  // use GMR
			} else {
				ZSpacing = LFactor.multiply( Math.log(1.0 / radius[i]) );
			}
			ZMat.set(i, i, Zi.add( ZSpacing.add(getZe(i, i)) ));
		}

		// TS self impedances
		for (i = 0; i < numPhases; i++) {
			resTS = 0.3183 * RHO_TS / (diaShield[i] * tapeLayer[i] * Math.sqrt(50.0 / (100.0 - tapeLap[i])));
			gmrTS = 0.5 * (diaShield[i] - tapeLayer[i]);  // per Kersting, to center of TS
			ZSpacing = LFactor.multiply( Math.log(1.0 / gmrTS) );
			Zi = new Complex(resTS, 0.0);
			idxi = i + numConds;
			ZMat.set(idxi, idxi, Zi.add( ZSpacing.add(getZe(i, i)) ));
		}

		// mutual impedances - between TS cores and bare neutrals
		for (i = 0; i < numConds; i++) {
			for (j = 0; j < i; j++) {  // TODO Check zero based indexing
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				ZMat.setSym(i, j, LFactor.multiply( Math.log(1.0 / Dij)).add(getZe(i, j)));
			}
		}

		// mutual impedances - TS to other TS, cores, and bare neutrals
		for (i = 0; i < numPhases; i++) {
			idxi = i + numConds;
			for (j = 0; j < i; j++) {  // TODO Check zero based indexing
				// TS to other TS
				idxj = j + numConds;
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				ZMat.setSym(idxi, idxj, LFactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
			for (j = 0; j < numConds; j++) {
				// CN to cores and bare neutrals
				idxj = j;
				gmrTS = 0.5 * (diaShield[i] - tapeLayer[i]);  // per Kersting, to center of TS
				if (i == j) {  // TS to its own phase core
					Dij = gmrTS;
				} else {  // TS to another phase or bare neutral
					Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				}
				ZMat.setSym(idxi, idxj, LFactor.multiply( Math.log(1.0 / Dij) ).add(getZe(i, j)));
			}
		}

		// reduce out the tape shields
		while (ZMat.order() > numConds) {
			ZTemp = ZMat.kron(ZMat.order());
			ZMat = null;
			ZMat = ZTemp;
		}
		ZMatrix.copyFrom(ZMat);
		ZMat = null;

		// for shielded cables, build the capacitance matrix directly
		// assumes the insulation may lie between semicon layers
		for (i = 0; i < numPhases; i++) {
			YFactor = TWO_PI * E0 * epsR[i] * w;  // includes frequency so C==>Y
		    radOut = 0.5 * diaIns[i];
		    radIn  = radOut - insLayer[i];
		    denom  = Math.log(radOut / radIn);
			YcMatrix.set(i, i, new Complex(0.0, YFactor / denom));
		}

		if (reducedSize > 0)
			kron(reducedSize);  // was reduced so reduce again to same size

		/* else the Zmatrix is OK as last computed */
		rhoChanged = false;
	}

}
