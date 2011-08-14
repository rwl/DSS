package com.epri.dss.general;

import java.io.PrintStream;

public interface GrowthShapeObj extends DSSObject {

	/* FIXME Private method in OpenDSS */
	void reCalcYearMult();

	String getPropertyValue(int index);

	void initPropertyValues(int arrayOffset);

	void dumpProperties(PrintStream f, boolean complete);

	/**
	 * Get multiplier for specified year.
	 */
	double getMult(int yr);

	int getNPts();

	void setNPts(int npts);

	int[] getYear();

	void setYear(int[] year);

	double[] getMultiplier();

	void setMultiplier(double[] multiplier);

	int getBaseYear();

	void setBaseYear(int baseYear);

}
