package com.ncond.dss.general;

public interface TSLineConstants extends CableConstants {

	static final double RHO_TS = 2.3718e-8;  // for copper tape shield

	double getDiaShield(int i, int units);

	void setDiaShield(int i, int units, double diaShield);

	double getTapeLayer(int i, int units);

	void setTapeLayer(int i, int units, double tapeLayer);

	double getTapeLap(int i);

	void setTapeLap(int i, double tapeLap);

}
