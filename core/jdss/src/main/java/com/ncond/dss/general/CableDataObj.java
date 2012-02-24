package com.ncond.dss.general;

public interface CableDataObj extends ConductorDataObj {

	double getEpsR();

	double getInsLayer();

	double getDiaIns();

	double getDiaCable();

	// FIXME Private members in OpenDSS.

	void setEpsR(double epsR);

	void setInsLayer(double insLayer);

	void setDiaIns(double diaIns);

	void setDiaCable(double diaCable);

}
