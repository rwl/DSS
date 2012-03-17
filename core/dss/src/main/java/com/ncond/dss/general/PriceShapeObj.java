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
 * The PriceShape object is a general DSS object used by all circuits
 * as a reference for obtaining yearly, daily, and other Price shapes.
 *
 * The values are set by the normal New and Edit procedures for any DSS object.
 *
 * The values may be retrieved by setting the Code Property in the PriceShape Class.
 * This sets the active PriceShape object to be the one referenced by the Code Property;
 *
 * Then the values of that code can be retrieved via the public variables.  Or you
 * can pick up the ActivePriceShapeObj object and save the direct reference to the object.
 *
 * PriceShapes default to fixed interval data (like Loadshapes).  If the Interval is specified to be 0.0,
 * then both time and price data are expected.  If the Interval is  greater than 0.0,
 * the user specifies only the prices.  The Hour command is ignored and the files are
 * assumed to contain only the price data.
 *
 * The Interval may also be specified in seconds (sinterval) or minutes (minterval).
 *
 * The user may place the data in CSV or binary files as well as passing through the
 * command interface. Obviously, for large amounts of data such as 8760 load curves, the
 * command interface is cumbersome.  CSV files are text separated by commas, one interval to a line.
 * There are two binary formats permitted: 1) a file of Singles; 2) a file of doubles.
 *
 * For fixed interval data, only the price values are expected.  Therefore, the CSV format would
 * contain only one number per line.  The two binary formats are packed.
 *
 * For variable interval data, (hour, price) pairs are expected in both formats.
 *
 * The Mean and Std Deviation are automatically computed upon demand when new series of points is entered.
 *
 *
 */
@Getter @Setter
public class PriceShapeObj extends DSSObject {

	private int lastValueAccessed, numPoints;  // number of points in curve
	private int arrayPropertyIndex;

	private boolean stdDevCalculated;
	private double[] mean = new double[1];
	private double[] stdDev = new double[1];

	protected double interval;  // =0.0 then random interval (hr)
	protected double[] hours;   // time values (hr) if interval > 0.0 else nil
	protected double[] priceValues;  // prices

	public PriceShapeObj(DSSClass parClass, String priceShapeName) {
		super(parClass);
		setName(priceShapeName.toLowerCase());
		objType = parClass.getClassType();

		lastValueAccessed = 0;

		numPoints   = 0;
		interval    = 1.0;  // hr
		hours       = null;
		priceValues = null;
		stdDevCalculated = false;  // calculate on demand

		arrayPropertyIndex = -1;

		initPropertyValues(0);
	}

	/**
	 * Get prices at specified time, hr.
	 *
	 * This method returns the Price for the given hour.
	 * If no points exist in the curve, the result is 0.0
	 * If there are fewer points than requested, the curve is simply assumed to repeat
	 * Thus a daily load curve can suffice for a yearly load curve: You just get the
	 * same day over and over again.
	 * The value returned is the nearest to the interval requested. Thus if you request
	 * hour=12.25 and the interval is 1.0, you will get interval 12.
	 */
	public double getPrice(double hr) {
		int index, i;

		double result = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				result = priceValues[0];
			} else {
				if (interval > 0.0) {
					index = (int) Math.round(hr / interval);
					if (index >= numPoints)
						index = index % numPoints;  // wrap around using remainder
					if (index == -1)
						index = numPoints;
					result = priceValues[index];
				} else {
					// for random interval

					/* Start with previous value accessed under the assumption that most
					 * of the time, this function will be called sequentially.
					 */

					/* Normalize Hr to max hour in curve to get wraparound */
					if (hr > hours[numPoints])
						hr = hr - (int) (hr / hours[numPoints]) * hours[numPoints];

					if (hours[lastValueAccessed] > hr)
						lastValueAccessed = 0;  // start over from beginning
					for (i = lastValueAccessed + 1; i < numPoints; i++) {
						if (Math.abs(hours[i] - hr) < 0.00001) {  // if close to an actual point, just use it.
							result = priceValues[i];
							lastValueAccessed = i;
							return result;
						} else if (hours[i] > hr) {  // interpolate for price
							lastValueAccessed = i - 1;
							result = priceValues[lastValueAccessed] +
									(hr - hours[lastValueAccessed]) / (hours[i] - hours[lastValueAccessed]) *
									(priceValues[i] - priceValues[lastValueAccessed]);
							return result;
						}
					}

					// if we fall through the loop, just use last value
					lastValueAccessed = numPoints - 1;
					result            = priceValues[numPoints];
				}
			}

		return result;
	}

	private void calcMeanandStdDev() {
		if (numPoints > 0) {
			if (interval > 0.0) {
				MathUtil.meanandStdDev(priceValues, numPoints, mean, stdDev);
			} else {
				MathUtil.curveMeanAndStdDev(priceValues, hours, numPoints, mean, stdDev);
			}
		}

		setPropertyValue(4, String.format("%.8g", mean[0]));
		setPropertyValue(5, String.format("%.8g", stdDev[0]));

		stdDevCalculated = true;
	}

	public double getMean() {
		if (!stdDevCalculated)
			calcMeanandStdDev();
		return mean[0];
	}

	public double getStdDev() {
		if (!stdDevCalculated)
			calcMeanandStdDev();
		return stdDev[0];
	}

	/**
	 * Get prices by index.
	 */
	public double getPrice(int i) {
		if (i < numPoints && i >= 0) {
			lastValueAccessed = i;
			return priceValues[i];
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
				result = result + String.format("%-g, " , priceValues[i]);
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
		setPropertyValue(11, "");     // action option

		super.initPropertyValues(PriceShape.NumPropsThisClass);
	}

	public void saveToDblFile() {
		saveToDblFile();
	}

	public void saveToSngFile() {
		int i;
		String fileName;
		PrintWriter pw;

		if (priceValues != null) {
			try {
				fileName = String.format("%s.dbl", getName());
				pw = new PrintWriter(fileName);

				for (i = 0; i < numPoints; i++)
					pw.println(priceValues[i]);

				DSS.globalResult = "price=[dblfile=" + fileName + ']';

				pw.close();
			} catch (IOException e) {
				DSS.doSimpleMsg("Error writing price shape values.", -1);
			}
		} else {
			DSS.doSimpleMsg("PriceShape." + getName() + " price values not defined.", 622);
		}
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
