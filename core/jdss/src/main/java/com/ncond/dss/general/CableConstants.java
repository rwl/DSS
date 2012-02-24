package com.ncond.dss.general;

public interface CableConstants extends LineConstants {

	double getEpsR(int i);

	void setEpsR(int i, double epsR);

	double getInsLayer(int i, int units);

	void setInsLayer(int i, int units, double insLayer);

	double getDiaIns(int i, int units);

	void setDiaIns(int i, int units, double diaIns);

	double getDiaCable(int i, int units);

	void setDiaCable(int i, int units, double diaCable);

}
