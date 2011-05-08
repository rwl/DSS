package com.epri.dss.general;

public interface TSLineConstants extends CableConstants {

	static final double RhoTS = 2.3718e-8;  // for copper tape shield

	double getDiaShield(int i);

	void setDiaShield(int i, double diaShield);

	double getTapeLayer(int i);

	void setTapeLayer(int i, double tapeLayer);

	double getTapeLap(int i);

	void setTapeLap(int i, double tapeLap);

}
