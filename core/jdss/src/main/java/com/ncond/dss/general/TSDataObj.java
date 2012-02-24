package com.ncond.dss.general;

public interface TSDataObj extends CableDataObj {

	double getDiaShield();

	double getTapeLayer();

	double getTapeLap();

	// FIXME Private members in OpenDSS

	void setDiaShield(double diaShield);

	void setTapeLayer(double tapeLayer);

	void setTapeLap(double tapeLap);

}
