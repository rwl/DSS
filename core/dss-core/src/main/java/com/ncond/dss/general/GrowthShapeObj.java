package com.ncond.dss.general;

import java.io.OutputStream;

public interface GrowthShapeObj extends DSSObject {

	/* FIXME Private method in OpenDSS */
	void reCalcYearMult();

	@Override
	String getPropertyValue(int index);

	@Override
	void initPropertyValues(int arrayOffset);

	@Override
	void dumpProperties(OutputStream out, boolean complete);

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
