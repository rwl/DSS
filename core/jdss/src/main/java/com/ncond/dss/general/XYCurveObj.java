package com.ncond.dss.general;

/**
 * The XYcurve object is a general DSS object used by all circuit elements
 * as a reference for obtaining yearly, daily, and other Temperature shapes.
 *
 * The values are set by the normal New and Edit PROCEDUREs for any DSS object.
 *
 * The values may be retrieved by setting the Code Property in the XYCurve Class.
 * This sets the active XYCurve object to be the one referenced by the Code Property;
 *
 * Then the values of that code can be retrieved via the public variables.  Or you
 * can pick up the ActiveTXYcurveObj object and save the direct reference to the object.
 *
 * The user may place the curve data in CSV or binary files as well as passing through the
 * command interface. Obviously, for large amounts of data such as 8760 load curves, the
 * command interface is cumbersome.  CSV files are text separated by commas, or white space
 * one point to a line.
 *
 */
public interface XYCurveObj extends DSSObject {

	int getNumPoints();

	void setNumPoints(int numPoints);

	/**
	 * Get Y value at specified X value.
	 */
	double getYValue(double X);

	/**
	 * Get X value at specified Y value.
	 */
	public double getXValue(double Y);

	/**
	 * Get Y value by index.
	 */
	double getYValue(int i);

	/**
	 * Get X value corresponding to point index.
	 */
	double getXValue(int i);

	// FIXME Private members in OpenDSS

	int getLastValueAccessed();

	void setLastValueAccessed(int lastValueAccessed);

	int getArrayPropertyIndex();

	void setArrayPropertyIndex(int arrayPropertyIndex);

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double[] getXValues();

	void setXValues(double[] xValues);

	double[] getYValues();

	void setYValues(double[] yValues);

}
