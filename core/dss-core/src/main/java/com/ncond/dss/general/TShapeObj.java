package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.shared.MathUtil;

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
public class TShapeObj extends DSSObject {

	private int lastValueAccessed,
			numPoints;  // number of points in curve
	private int arrayPropertyIndex;

	private boolean stdDevCalculated;
	private double[] mean = new double[1];
	private double[] stdDev = new double[1];

	protected double interval;   // =0.0 then random interval (hr)
	protected double[] hours;    // time values (hr) if interval > 0.0 else nil
	protected double[] TValues;  // temperatures

	public TShapeObj(DSSClass parClass, String TShapeName) {
		super(parClass);
		setName(TShapeName.toLowerCase());
		objType = parClass.getClassType();

		lastValueAccessed = 0;

		numPoints = 0;
		interval  = 1.0;  // hr
		hours     = null;
		TValues   = null;
		stdDevCalculated = false;  // calculate on demand

		arrayPropertyIndex = -1;

		initPropertyValues(0);
	}

	/**
	 * Get temperatures at specified time, hr.
	 *
	 * This method returns the temperature for the given hour.
	 * If no points exist in the curve, the result is 0.0
	 * If there are fewer points than requested, the curve is simply assumed to repeat
	 * Thus a daily load curve can suffice for a yearly load curve: You just get the
	 * same day over and over again.
	 * The value returned is the nearest to the interval requested. Thus if you request
	 * hour=12.25 and the interval is 1.0, you will get interval 12.
	 */
	public double getTemperature(double hr) {
		int index, i;

		double result = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				result = TValues[0];
			} else {
				if (interval > 0.0) {
					index = (int) Math.round(hr / interval);
					if (index > numPoints)
						index = index % numPoints;  // wrap around using remainder
					if (index == 0)
						index = numPoints;
					result = TValues[index];
				} else {
					// for random interval

					/* Start with previous value accessed under the assumption that most
					 * of the time, this function will be called sequentially
					 */

					/* Normalize hr to max hour in curve to get wraparound */
					if (hr > hours[numPoints]) {
						hr = hr - (int) (hr / hours[numPoints - 1]) * hours[numPoints - 1];
					}

					if (hours[lastValueAccessed] > hr)
						lastValueAccessed = 1;  // start over from beginning
					for (i = lastValueAccessed; i < numPoints; i++) {
						if (Math.abs(hours[i] - hr) < 0.00001) {  // if close to an actual point, just use it
							result = TValues[i];
							lastValueAccessed = i;
							return result;
						} else if (hours[i] > hr) {  // interpolate for temperature
							lastValueAccessed = i - 1;
							result = TValues[lastValueAccessed] +
									(hr - hours[lastValueAccessed]) / (hours[i] - hours[lastValueAccessed]) *
									(TValues[i] - TValues[lastValueAccessed]);
							return result;
						}
					}

					// if we fall through the loop, just use last value
					lastValueAccessed = numPoints - 1;
					result            = TValues[numPoints - 1];
				}
			}

		return result;
	}

	private void calcMeanAndStdDev() {
		if (numPoints > 0)
			if (interval > 0.0) {
				MathUtil.meanandStdDev(TValues, numPoints, mean, stdDev);
			} else {
				MathUtil.curveMeanAndStdDev(TValues, hours, numPoints, mean, stdDev);
			}

		setPropertyValue(4, String.format("%.8g", mean[0]));
		setPropertyValue(5, String.format("%.8g", stdDev[0]));

		stdDevCalculated = true;
	}

	public double getMean() {
		if (!stdDevCalculated)
			calcMeanAndStdDev();
		return mean[0];
	}

	public double getStdDev() {
		if (!stdDevCalculated)
			calcMeanAndStdDev();
		return stdDev[0];
	}

	/**
	 * Get temperatures by index.
	 */
	public double getTemperature(int i) {
		if (i < numPoints && i >= 0) {
			lastValueAccessed = i;
			return TValues[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get hour corresponding to point index.
	 */
	public double getHour(int i) {
		if (interval == 0) {
			if (i < numPoints && i >= 0) {
				lastValueAccessed = i;
				return hours[i];
			} else {
				return 0.0;
			}
		} else {
			lastValueAccessed = i;
			return hours[i] * interval;
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 2:
				pw.println("~ " + getParentClass().getPropertyName(i) +
					"=(" + getPropertyValue(i) + ")");
				break;
			case 3:
				pw.println("~ " + getParentClass().getPropertyName(i) +
					"=(" + getPropertyValue(i) + ")");
				break;
			default:
				pw.println("~ " + getParentClass().getPropertyName(i) +
					"=" + getPropertyValue(i));
				break;
			}
		}
		pw.close();
	}

	@Override
	public String getPropertyValue(int index) {
		String result;
		switch (index) {
		case 2:
			result = "[";
			break;
		case 3:
			result = "[";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 1:
			result = String.format("%.8g", interval);
			break;
		case 2:
			for (int i = 0; i < numPoints; i++)
				result = result + String.format("%-g, " , TValues[i]);
			break;
		case 3:
			if (hours != null)
				for (int i = 0; i < numPoints; i++)
					result = result + String.format("%-g, ", hours[i]);
			break;
		case 4:
			result = String.format("%.8g", mean[0]);
			break;
		case 5:
			result = String.format("%.8g", stdDev[0]);
			break;
		case 9:
			result = String.format("%.8g", interval * 3600.0);
			break;
		case 10:
			result = String.format("%.8g", interval * 60.0);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 2:
			result = result + "]";
			break;
		case 3:
			result = result + "]";
			break;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "0");  // number of points to expect
		setPropertyValue(1, "1");  // default = 1.0 hr;
		setPropertyValue(2, "");   // vector of multiplier values
		setPropertyValue(3, "");   // vextor of hour values
		setPropertyValue(4, "0");  // set the mean (otherwise computed)
		setPropertyValue(5, "0");  // set the std dev (otherwise computed)
		setPropertyValue(6, "");   // switch input to a csvfile
		setPropertyValue(7, "");   // switch input to a binary file of singles
		setPropertyValue(8, "");   // switch input to a binary file of singles
		setPropertyValue(9, "3600");  // seconds
		setPropertyValue(10, "60");   // minutes
		setPropertyValue(11, "");  // action option

		super.initPropertyValues(TShape.NumPropsThisClass);
	}

	// FIXME Private method in OpenDSS
	public void saveToDblFile() {
		throw new UnsupportedOperationException();
	}

	// FIXME Private method in OpenDSS
	public void saveToSngFile() {
		throw new UnsupportedOperationException();
	}

	public void setMean(double value) {
		stdDevCalculated = true;
		mean[0] = value;
	}

	public void setNumPoints(int num) {
		setPropertyValue(0, String.valueOf(num));  // update property list variable

		// reset array property values to keep them in proper order in save

		if (arrayPropertyIndex >= 0)
			setPropertyValue(arrayPropertyIndex, getPropertyValue(arrayPropertyIndex));

		numPoints = num;
	}

	public void setStdDev(double stddev) {
		stdDevCalculated = true;
		stdDev[0] = stddev;
	}

	public int getNumPoints() {
		return 0;
	}

	public double getInterval() {
		return interval;
	}

	public double[] getHours() {
		return hours;
	}

	public void setHours(double[] values) {
		hours = values;
	}

	public double[] getTValues() {
		return TValues;
	}

	public void setTValues(double[] values) {
		TValues = values;
	}

	// FIXME Private members in OpenDSS

	public int getLastValueAccessed() {
		return lastValueAccessed;
	}

	public void setLastValueAccessed(int lastValue) {
		lastValueAccessed = lastValue;
	}

	public int getArrayPropertyIndex() {
		return arrayPropertyIndex;
	}

	public void setArrayPropertyIndex(int index) {
		arrayPropertyIndex = index;
	}

	public boolean isStdDevCalculated() {
		return stdDevCalculated;
	}

	public void setStdDevCalculated(boolean calculated) {
		stdDevCalculated = calculated;
	}

	public void setInterval(double value) {
		interval = value;
	}

}
