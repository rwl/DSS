package com.epri.dss.general;

public interface CNLineConstants extends CableConstants {

	int[] getkStrand();

	void setkStrand(int[] kStrand);

	double[] getDiaStrand();

	void setDiaStrand(double[] diaStrand);

	double[] getGmrStrand();

	void setGmrStrand(double[] gmrStrand);

	double[] getRStrand();

	void setRStrand(double[] rStrand);

}
