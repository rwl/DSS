package com.epri.dss.general.impl;

import com.epri.dss.general.CNLineConstants;

public class CNLineConstantsImpl extends CableConstantsImpl implements CNLineConstants {

	private int[] kStrand;
	private double[] DiaStrand;
	private double[] GmrStrand;
	private double[] RStrand;

	public CNLineConstantsImpl(int NumConductors) {
		super(NumConductors);
	}

	@Override
	public void calc(double f) {

	}

	public int[] getkStrand() {
		return kStrand;
	}

	public void setkStrand(int[] kStrand) {
		this.kStrand = kStrand;
	}

	public double[] getDiaStrand() {
		return DiaStrand;
	}

	public void setDiaStrand(double[] diaStrand) {
		DiaStrand = diaStrand;
	}

	public double[] getGmrStrand() {
		return GmrStrand;
	}

	public void setGmrStrand(double[] gmrStrand) {
		GmrStrand = gmrStrand;
	}

	public double[] getRStrand() {
		return RStrand;
	}

	public void setRStrand(double[] rStrand) {
		RStrand = rStrand;
	}

}
