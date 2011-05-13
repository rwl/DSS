package com.epri.dss.general;

public interface CNDataObj extends CableDataObj {

	int getkStrand();

	double getDiaStrand();

	double getGmrStrand();

	double getRStrand();

	// FIXME: Private members in OpenDSS

	void setkStrand(int kStrand);

	void setDiaStrand(double diaStrand);

	void setGmrStrand(double gmrStrand);

	void setRStrand(double rStrand);

}
