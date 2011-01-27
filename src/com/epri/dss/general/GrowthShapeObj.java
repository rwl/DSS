package com.epri.dss.general;

import java.io.PrintStream;

public interface GrowthShapeObj extends DSSObject {

	public String getPropertyValue(int Index);

	public void initPropertyValues(int ArrayOffset);

	public void dumpProperties(PrintStream F, boolean Complete);

	/* Get multiplier for Specified Year */
	public double getMult(int Yr);

}
