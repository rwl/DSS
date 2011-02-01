package com.epri.dss.general;

import java.io.PrintStream;

public interface GrowthShapeObj extends DSSObject {

	String getPropertyValue(int Index);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	/* Get multiplier for Specified Year */
	double getMult(int Yr);

}
