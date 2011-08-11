package com.epri.dss.general;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

/**
 * A general DSS object used by all circuits as a reference for obtaining
 * yearly, daily, and other load shapes.
 *
 * The values are set by the normal "new" and "edit" procedures for any DSS object.
 *
 * The values are retrieved by setting the Code property in the LoadShape
 * class. This sets the active LoadShape object to be the one referenced by the
 * Code property;
 *
 * Then the values of that code can be retrieved via the public variables.  Or
 * you can pick up the activeLoadShapeObj object and save the direct reference
 * to the object.
 *
 * LoadShapes default to fixed interval data.  If the interval is specified to
 * be 0.0, then both time and multiplier data are expected.  If the interval is
 * greater than 0.0, the user specifies only the multipliers.  The hour command
 * is ignored and the files are assumed to contain only the multiplier data.
 *
 * The user may place the data in CSV or binary files as well as passing
 * through the command interface. Obviously, for large amounts of data such as
 * 8760 load curves, the command interface is cumbersome.  CSV files are text
 * separated by commas, one interval to a line. There are two binary formats
 * permitted: 1) a file of Singles; 2) a file of Doubles.
 *
 * For fixed interval data, only the multiplier is expected.  Therefore, the
 * CSV format would contain only one number per line.  The two binary formats
 * are packed.
 *
 * For variable interval data, (hour, multiplier) pairs are expected in both
 * formats.
 *
 * The Mean and Std Deviation are automatically computed when a new series of
 * points is entered.
 *
 * The data may also be entered in unnormalized form.  The normalize=Yes
 * command will force normalization.  That is, the multipliers are scaled so
 * that the maximum value is 1.0.
 *
 */
public interface LoadShapeObj extends DSSObject {

	double getInterval();

	void setNumPoints(int Value);

	int getNumPoints();

	/**
	 * Get multiplier at specified time.
	 */
	Complex getMult(double hr);

	/**
	 * Get multiplier by index.
	 */
	double mult(int i);

	/**
	 * Get hour corresponding to point index.
	 */
	double hour(int i);

	/**
	 * Normalize the curve presently in memory.
	 */
	void normalize();

	void calcMeanAndStdDev();

	String getPropertyValue(int Index);

	void initPropertyValues(int ArrayOffset);

	void dumpProperties(PrintStream F, boolean Complete);

	double[] getHours();

	void setHours(double[] hours);

	double[] getPMultipliers();

	void setPMultipliers(double[] pMultipliers);

	double[] getQMultipliers();

	void setQMultipliers(double[] qMultipliers);

	double getMean();

	void setMean(double mean);

	double getStdDev();

	void setStdDev(double stdDev);

	void setInterval(double interval);

	double getMaxP();

	void setMaxP(double maxP);

	double getMaxQ();

	void setMaxQ(double maxQ);

	boolean isUseActual();

	void setUseActual(boolean useActual);

	// FIXME Private methods in OpenDSS

	void saveToDblFile();

	void saveToSngFile();

	void setMaxPandQ();


	// Private member in OpenDSS.
	void setArrayPropertyIndex(int i);


	// Protected member in OpenDSS.

	boolean isStdDevCalculated();

	void setStdDevCalculated(boolean stdDevCalculated);

}
