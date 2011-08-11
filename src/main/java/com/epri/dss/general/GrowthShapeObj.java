package com.epri.dss.general;

import java.io.PrintStream;

public interface GrowthShapeObj extends DSSObject {

	/* FIXME Private method in OpenDSS */
	void reCalcYearMult();

	String getPropertyValue(int Index);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	/**
	 * Get multiplier for specified year.
	 */
	double getMult(int Yr);

	int getNpts();

	void setNpts(int npts);

	int[] getYear();

	void setYear(int[] year);

	double[] getMultiplier();

	void setMultiplier(double[] multiplier);

	int getBaseYear();

	void setBaseYear(int baseYear);

}
