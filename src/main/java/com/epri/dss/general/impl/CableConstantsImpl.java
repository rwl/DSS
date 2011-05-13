package com.epri.dss.general.impl;

import com.epri.dss.general.CableConstants;
import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;
import com.epri.dss.shared.impl.LineUnits;
import com.epri.dss.shared.impl.MathUtil;

public class CableConstantsImpl extends LineConstantsImpl implements CableConstants {

	protected double[] EpsR;
	protected double[] InsLayer;
	protected double[] DiaIns;
	protected double[] DiaCable;

	public CableConstantsImpl(int NumConductors) {
		super(NumConductors);
		this.EpsR = new double[getNumConds()];
		this.InsLayer = new double[getNumConds()];
		this.DiaIns = new double[getNumConds()];
		this.DiaCable = new double[getNumConds()];
	}

	/**
	 * Don't reduce Y, it has zero neutral capacitance.
	 */
	@Override
	public void Kron(int Norder) {
		CMatrix Ztemp;
		boolean FirstTime;
		int i, j;

		Ztemp = Zmatrix;
		FirstTime = true;
		if ((Frequency >= 0.0) && (Norder > 0) && (Norder < getNumConds())) {
			if (Zreduced != null) Zreduced = null;
			if (YCreduced != null) YCreduced = null;
			while (Ztemp.getNOrder() > Norder) {
				Zreduced = Ztemp.kron(Ztemp.getNOrder());  // Eliminate last row
				if (!FirstTime) Ztemp = null;  // Ztemp points to intermediate matrix
				Ztemp = Zreduced;
				FirstTime = false;
			}
			// now copy part of FYCmatrix to FYCreduced
			YCreduced = new CMatrixImpl(Norder);
			for (i = 0; i < Norder; i++)
				for (j = 0; j < Norder; j++)
					YCreduced.setElement(i, j, YCmatrix.getElement(i, j));
		}
	}

	@Override
	public boolean conductorsInSameSpace(StringBuffer ErrorMessage) {
		int i, j;
		double Dij;
		double Ri, Rj;

		boolean Result = false;

		for (i = 0; i < getNumConds(); i++) {
			if (Y[i] >= 0.0) {
				Result = true;
				ErrorMessage.append(String.format("Cable %d height must be < 0. ", i));  // FIXME Pass by reference
				return Result;
			}
		}

		for (i = 0; i < getNumConds(); i++) {
			if (i <= getNumPhases()) {
				Ri = radius[i];
			} else {
				Ri = 0.5 * DiaCable[i];
			}
			for (j = i; j < getNumConds(); j++) {  // TODO Check zero based indexing
				if (j <= getNumPhases()) {
					Rj = radius[j];
				} else {
					Rj = 0.5 * DiaCable[j];
				}
				Dij = Math.sqrt(MathUtil.sqr(X[i] - X[j]) + MathUtil.sqr(Y[i] - Y[j]));
				if (Dij < (Ri + Rj)) {
					Result = true;
					ErrorMessage.append(String.format("Cable conductors %d and %d occupy the same space.", i, j));
					return Result;
				}
			}
		}
		return Result;
	}

	public double getEpsR(int i) {
		return EpsR[i];
	}

	public void setEpsR(int i, double epsR) {
		EpsR[i] = epsR;
	}

	public double getInsLayer(int i, int units) {
		return InsLayer[i] * LineUnits.fromMeters(units);
	}

	public void setInsLayer(int i, int units, double insLayer) {
		if ((i >= 0) && (i < getNumConds()))
			InsLayer[i] = insLayer * LineUnits.toMeters(units);
	}

	public double getDiaIns(int i, int units) {
		return DiaIns[i] * LineUnits.fromMeters(units);
	}

	public void setDiaIns(int i, int units, double diaIns) {
		if ((i >= 0) && (i < getNumConds()))
			DiaIns[i] = diaIns * LineUnits.toMeters(units);
	}

	public double getDiaCable(int i, int units) {
		return DiaCable[i] * LineUnits.fromMeters(units);
	}

	public void setDiaCable(int i, int units, double diaCable) {
		if ((i >= 0) && (i < getNumConds()))
			DiaCable[i] = diaCable * LineUnits.toMeters(units);
	}

}
