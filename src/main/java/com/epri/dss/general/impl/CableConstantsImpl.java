package com.epri.dss.general.impl;

import com.epri.dss.general.CableConstants;

public class CableConstantsImpl extends LineConstantsImpl implements CableConstants {

	protected double[] EpsR;
	protected double[] InsLayer;
	protected double[] DiaIns;
	protected double[] DiaCable;

	public CableConstantsImpl(int NumConductors) {
		super(NumConductors);
	}

	@Override
	public boolean conductorsInSameSpace(String ErrorMessage) {
		return false;
	}

	/**
	 * Don't reduce Y, it has zero neutral capacitance.
	 */
	@Override
	public void Kron(int Norder) {

	}

	public double getEpsR(int i) {
		return 0;
	}

	public void setEpsR(int i, double epsR) {

	}

	public double getInsLayer(int i, int units) {
		return 0;
	}

	public void setInsLayer(int i, int units, double insLayer) {

	}

	public double getDiaIns(int i, int units) {
		return 0;
	}

	public void setDiaIns(int i, int units, double diaIns) {

	}

	public double getDiaCable(int i, int units) {
		return 0;
	}

	public void setDiaCable(int i, int units, double diaCable) {

	}

}
