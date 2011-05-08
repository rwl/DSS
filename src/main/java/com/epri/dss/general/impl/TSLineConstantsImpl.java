package com.epri.dss.general.impl;

import com.epri.dss.general.TSLineConstants;

public class TSLineConstantsImpl extends CableConstantsImpl implements TSLineConstants {

	private double[] DiaShield;
	private double[] TapeLayer;
	private double[] TapeLap;

	public TSLineConstantsImpl(int NumConductors) {
		super(NumConductors);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void calc(double f) {

	}

	public double getDiaShield(int i) {
		return DiaShield[i];
	}

	public void setDiaShield(int i, double diaShield) {
		DiaShield[i] = diaShield;
	}

	public double getTapeLayer(int i) {
		return TapeLayer[i];
	}

	public void setTapeLayer(int i, double tapeLayer) {
		TapeLayer[i] = tapeLayer;
	}

	public double getTapeLap(int i) {
		return TapeLap[i];
	}

	public void setTapeLap(int i, double tapeLap) {
		TapeLap[i] = tapeLap;
	}

}
