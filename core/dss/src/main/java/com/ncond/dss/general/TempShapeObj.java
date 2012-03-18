package com.ncond.dss.general;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSS;
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
@Getter @Setter
public class TempShapeObj extends DSSObject {

	private int lastValueAccessed, numPoints;
	private int arrayPropertyIndex;

	private boolean stdDevCalculated;
	private double[] mean = new double[1];
	private double[] stdDev = new double[1];

	protected double interval;   // =0.0 then random interval (hr)
	protected double[] hours;    // time values (hr) if interval > 0.0 else nil
	protected double[] tempValues;  // temperatures

	public TempShapeObj(DSSClass parClass, String TShapeName) {
		super(parClass);

		setName(TShapeName.toLowerCase());
		objType = parClass.getClassType();

		lastValueAccessed = 0;

		numPoints = 0;
		interval = 1.0;  // hr
		hours = null;
		tempValues = null;
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

		double t = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				t = tempValues[0];
			} else {
				if (interval > 0.0) {
					index = (int) Math.round(hr / interval) - 1;
					if (index >= numPoints)
						index = index % numPoints;  // wrap around using remainder
					if (index == -1)
						index = numPoints;
					t = tempValues[index];
				} else {
					// for random interval

					/* Start with previous value accessed under the assumption that most
					 * of the time, this function will be called sequentially
					 */

					/* Normalize hr to max hour in curve to get wraparound */
					if (hr > hours[numPoints]) {
						hr -= (int) (hr / hours[numPoints - 1]) * hours[numPoints - 1];
					}

					if (hours[lastValueAccessed] > hr)
						lastValueAccessed = 0;  // start over from beginning

					for (i = lastValueAccessed + 1; i < numPoints; i++) {
						if (Math.abs(hours[i] - hr) < 0.00001) {  // if close to an actual point, just use it
							t = tempValues[i];
							lastValueAccessed = i;
							return t;
						} else if (hours[i] > hr) {  // interpolate for temperature
							lastValueAccessed = i - 1;
							t = tempValues[lastValueAccessed] +
									(hr - hours[lastValueAccessed]) / (hours[i] - hours[lastValueAccessed]) *
									(tempValues[i] - tempValues[lastValueAccessed]);
							return t;
						}
					}

					// if we fall through the loop, just use last value
					lastValueAccessed = numPoints - 2;
					t = tempValues[numPoints - 1];
				}
			}

		return t;
	}

	private void calcMeanAndStdDev() {
		if (numPoints > 0) {
			if (interval > 0.0) {
				MathUtil.meanAndStdDev(tempValues, numPoints, mean, stdDev);
			} else {
				MathUtil.curveMeanAndStdDev(tempValues, hours, numPoints, mean, stdDev);
			}
		}

		setPropertyValue(4, String.format("%.8g", mean[0]));
		setPropertyValue(5, String.format("%.8g", stdDev[0]));

		stdDevCalculated = true;
	}

	public double getMean() {
		if (!stdDevCalculated) calcMeanAndStdDev();
		return mean[0];
	}

	public double getStdDev() {
		if (!stdDevCalculated) calcMeanAndStdDev();
		return stdDev[0];
	}

	/**
	 * Get temperatures by index.
	 */
	public double getTemperature(int i) {
		if (i < numPoints && i >= 0) {
			lastValueAccessed = i;
			return tempValues[i];
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
		String val;
		switch (index) {
		case 2:
		case 3:
			val = "[";
			break;
		default:
			val = "";
			break;
		}

		switch (index) {
		case 1:
			val = String.format("%.8g", interval);
			break;
		case 2:
			for (int i = 0; i < numPoints; i++)
				val = val + String.format("%-g, " , tempValues[i]);
			break;
		case 3:
			if (hours != null)
				for (int i = 0; i < numPoints; i++)
					val = val + String.format("%-g, ", hours[i]);
			break;
		case 4:
			val = String.format("%.8g", mean[0]);
			break;
		case 5:
			val = String.format("%.8g", stdDev[0]);
			break;
		case 9:
			val = String.format("%.8g", interval * 3600.0);
			break;
		case 10:
			val = String.format("%.8g", interval * 60.0);
			break;
		default:
			val = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 2:
		case 3:
			val = val + "]";
			break;
		}

		return val;
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

		super.initPropertyValues(TempShape.NumPropsThisClass);
	}

	public void saveToDblFile() {
		int i;
		String fileName;
		PrintWriter pw;

		if (tempValues != null) {
			try {
				fileName = String.format("%s.dbl", getName());
				pw = new PrintWriter(fileName);

				for (i = 0; i < numPoints; i++)
					pw.println(tempValues[i]);

				DSS.globalResult = "temp=[dblfile=" + fileName + ']';

				pw.close();
			} catch (IOException e) {
				DSS.doSimpleMsg("Error writing temperature shape values.", -1);
			}
		} else {
			DSS.doSimpleMsg("TShape." + getName() + " temperatures not defined.", 622);
		}
	}

	public void saveToSngFile() {
		saveToDblFile();
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

}
