package com.ncond.dss.general;

/**
 * The TShape object is a general DSS object used by all circuits
 * as a reference for obtaining yearly, daily, and other Temperature shapes.
 *
 * The values are set by the normal New and Edit PROCEDUREs for any DSS object.
 *
 * The values may be retrieved by setting the Code Property in the Tshape Class.
 * This sets the active Tshape object to be the one referenced by the Code Property;
 *
 * Then the values of that code can be retrieved via the public variables.  Or you
 * can pick up the ActiveTShapeObj object and save the direct reference to the object.
 *
 * TShapes default to fixed interval data (like Loadshapes).  If the Interval is specified to be 0.0,
 * then both time and temperature data are expected.  If the Interval is  greater than 0.0,
 * the user specifies only the Temperatures.  The Hour command is ignored and the files are
 * assumed to contain only the temperature data.
 *
 * The Interval may also be specified in seconds (sinterval) or minutes (minterval).
 *
 * The user may place the data in CSV or binary files as well as passing through the
 * command interface. Obviously, for large amounts of data such as 8760 load curves, the
 * command interface is cumbersome.  CSV files are text separated by commas, one interval to a line.
 * There are two binary formats permitted: 1) a file of Singles; 2) a file of doubles.
 *
 * For fixed interval data, only the Temperature values are expected.  Therefore, the CSV format would
 * contain only one number per line.  The two binary formats are packed.
 *
 * For variable interval data, (hour, Temperature) pairs are expected in both formats.
 *
 * The Mean and Std Deviation are automatically computed upon demand when new series of points is entered.
 *
 */
public interface TShapeObj extends DSSObject {

	int getNumPoints();

	void setNumPoints(int numPoints);

	double getMean();

	void setMean(double mean);

	double getStdDev();

	void setStdDev(double stdDev);

	double getInterval();

	void setInterval(double interval);

	double[] getHours();

	void setHours(double[] hours);

	double[] getTValues();

	void setTValues(double[] tValues);

	/**
	 * Get temperatures at specified time, hr.
	 */
	double getTemperature(double hr);

	/**
	 * Get temperatures by index.
	 */
	double getTemperature(int i);

	/**
	 * Get hour corresponding to point index.
	 */
	double getHour(int i);

	// FIXME Private methods in OpenDSS

	void saveToDblFile();

	void saveToSngFile();

	// FIXME Private members in OpenDSS

	int getLastValueAccessed();

	void setLastValueAccessed(int lastValueAccessed);

	int getArrayPropertyIndex();

	void setArrayPropertyIndex(int arrayPropertyIndex);

	boolean isStdDevCalculated();

	void setStdDevCalculated(boolean stdDevCalculated);

}
