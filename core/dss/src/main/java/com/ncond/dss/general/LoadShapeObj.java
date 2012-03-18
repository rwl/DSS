package com.ncond.dss.general;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;
import com.ncond.dss.shared.MathUtil;

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
@Getter @Setter
public class LoadShapeObj extends DSSObject {

	private int lastValueAccessed;

	/** Number of points in curve */
	private int numPoints;
	private int arrayPropertyIndex;

	/** =0.0 then random interval (hr) */
	protected double interval;
	/** Time values (hr) if Interval > 0.0 */
	protected double[] hours;
	protected double[] PMultipliers, QMultipliers;

	protected double maxP, maxQ, baseP, baseQ;

	protected boolean useActual;

	protected int iMaxP;

	protected boolean stdDevCalculated;
	protected double[] mean = new double[1];
	protected double[] stdDev = new double[1];

	public LoadShapeObj(DSSClass parClass, String loadShapeName) {
		super(parClass);

		setName(loadShapeName.toLowerCase());
		objType = parClass.getClassType();

		lastValueAccessed = 0;

		numPoints = 0;
		interval = 1.0;  // hr
		hours = null;
		PMultipliers = null;
		QMultipliers = null;
		maxP = 1.0;
		maxQ = 0.0;
		baseP = 0.0;
		baseQ = 0.0;
		useActual = false;
		stdDevCalculated = false;  // calculate on demand

		arrayPropertyIndex = 0;

		initPropertyValues(0);
	}

	/** Set imaginary part of result when QMultipliers not defined. */
	private double setResultIm(double realpart) {
		return useActual ? 0.0 : realpart;
	}

	/**
	 * Get multiplier at specified time.
	 *
	 * This function returns a multiplier for the given hour.
	 * If no points exist in the curve, the result is 1.0.
	 * If there are fewer points than requested, the curve is simply assumed to repeat
	 * Thus a daily load curve can suffice for a yearly load curve:  You just get the
	 * same day over and over again.
	 * The value returned is the nearest to the interval requested.  Thus if you request
	 * hour=12.25 and the interval is 1.0, you will get interval 12.
	 */
	public Complex getMult(double hr) {
		int index;

		double[] mult = new double[] {1.0, 1.0};  // default return value if no points in curve

		if (numPoints > 0) {  // handle exceptional cases
			if (numPoints == 1) {
				mult[0] = PMultipliers[0];
				if (QMultipliers != null) {
					mult[1] = QMultipliers[0];
				} else {
					mult[1] = setResultIm(mult[0]);
				}
			} else {
				if (interval > 0.0) {
					index = (int) Math.round(hr / interval);
					if (index > numPoints)
						index = index % numPoints;  // wrap around using remainder
					if (index == 0)
						index = numPoints;
					mult[0] = PMultipliers[index];
					if (QMultipliers != null) {
						mult[1] = QMultipliers[index];
					} else {
						mult[1] = setResultIm(mult[0]);
					}
				} else {
					// for random interval

					/* Start with previous value accessed under the assumption that most
					 * of the time, this function will be called sequentially */

					/* Normalize Hr to max hour in curve to get wraparound */
					if (hr > hours[numPoints])
						hr = hr - (hr / hours[numPoints]) * hours[numPoints];

					if (hours[lastValueAccessed] > hr)
						lastValueAccessed = 1;  // start over from beginning

					for (int i = lastValueAccessed + 1; i < numPoints; i++) {
						if (Math.abs(hours[i] - hr) < 0.00001) {  // if close to an actual point, just use it.
							mult[0] = PMultipliers[i];
							if (QMultipliers != null) {
								mult[1] = QMultipliers[i];
							} else {
								mult[1] = setResultIm(mult[0]);
							}
							lastValueAccessed = i;
							return new Complex(mult[0], mult[1]);
						} else if (hours[i] > hr) {  // interpolate for multiplier
							lastValueAccessed = i - 1;
							mult[0] = PMultipliers[lastValueAccessed] +
								(hr - hours[lastValueAccessed]) / (hours[i] - hours[lastValueAccessed]) *
								(PMultipliers[i] -PMultipliers[lastValueAccessed]);
							if (QMultipliers != null) {
								mult[1] = QMultipliers[lastValueAccessed] +
									(hr - hours[lastValueAccessed]) / (hours[i] - hours[lastValueAccessed]) *
									(QMultipliers[i] -QMultipliers[lastValueAccessed]);
							} else {
								mult[1] = setResultIm(mult[0]);
							}
							return new Complex(mult[0], mult[1]);
						}
					}

					// if we fall through the loop, just use last value
					lastValueAccessed = numPoints - 1;
					mult[0] = PMultipliers[numPoints];
					if (QMultipliers != null) {
						mult[1] = QMultipliers[numPoints];
					} else {
						mult[1] = setResultIm(mult[0]);
					}
				}
			}
		}

		return new Complex(mult[0], mult[1]);
	}

	private double maxMult;

	private void doNormalize(double[] multipliers) {
		int i;
		if (numPoints > 0) {
			if (maxMult <= 0.0) {
				maxMult = Math.abs(multipliers[0]);
				for (i = 1; i < numPoints; i++)
					maxMult = Math.max(maxMult, Math.abs(multipliers[i]));
			}

			if (maxMult == 0.0) maxMult = 1.0;  // avoid divide by zero

			for (i = 0; i < numPoints; i++)
				multipliers[i] = multipliers[i] / maxMult;
		}
	}

	/**
	 * Normalize the curve presently in memory.
	 */
	public void normalize() {
		maxMult = baseP;
		doNormalize(PMultipliers);
		if (QMultipliers != null) {
			maxMult = baseQ;
			doNormalize(QMultipliers);
		}
		useActual = false;  // not likely that you would want to use the actual if you normalized it
	}

	public void calcMeanAndStdDev() {
		if (numPoints > 0) {
			if (interval > 0.0) {
				MathUtil.meanAndStdDev(PMultipliers, numPoints, mean, stdDev);
			} else {
				MathUtil.curveMeanAndStdDev(PMultipliers, hours, numPoints, mean, stdDev);
			}
		}

		setPropertyValue(4, String.format("%.8g", mean[0]));
		setPropertyValue(5, String.format("%.8g", stdDev[0]));

		stdDevCalculated = true;
		/* No action is taken on Q multipliers */
	}

	public double getInterval() {
		if (interval > 0.0) {
			return interval;
		} else {
			if (lastValueAccessed > 0) {
				return hours[lastValueAccessed] - hours[lastValueAccessed - 1];
			} else {
				return 0.0;
			}
		}
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
	 * Get multiplier by index.
	 */
	public double mult(int i) {
		if (i < numPoints && i >= 0) {
			lastValueAccessed = i;
			return PMultipliers[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get hour corresponding to point index.
	 */
	public double hour(int i) {
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

		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 2:
				pw.println("~ " + parentClass.getPropertyName(i) +
					"=(" + propertyValues[i] + ")");
				break;
			case 3:
				pw.println("~ " + parentClass.getPropertyName(i) +
					"=(" + propertyValues[i] + ")");
				break;
			default:
				pw.println("~ " + parentClass.getPropertyName(i) +
					"=" + propertyValues[i]);
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
			val = "(";
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
				val = val + String.format("%-g, ", PMultipliers[i]);
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
		case 10:
			if (QMultipliers != null) {
				val = "(";
				for (int i = 0; i < numPoints; i++)
					val = val + String.format("%-g,", QMultipliers[i]);
				val = val + ")";
			}
			break;
		case 11:
			val = useActual ? "Yes" : "No";
			break;
		case 12:
			val = String.format("%.8g", maxP);
			break;
		case 13:
			val = String.format("%.8g", maxQ);
			break;
		case 14:
			val = String.format("%.8g", interval * 3600.0);
			break;
		case 15:
			val = String.format("%.8g", interval * 60.0);
			break;
		case 17:
			val = String.format("%.8g", baseP);
			break;
		case 18:
			val = String.format("%.8g", baseQ);
			break;
		default:
			val = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 2:
		case 3:
			val += ")";
			break;
		}

		return val;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "0");  // number of points to expect
		setPropertyValue(1, "1");  // default = 1.0 hr;
		setPropertyValue(2, "");   // vector of multiplier values
		setPropertyValue(3, "");   // vector of hour values
		setPropertyValue(4, "0");  // set the mean (otherwise computed)
		setPropertyValue(5, "0");  // set the std dev (otherwise computed)
		setPropertyValue(6, "");   // switch input to a csvfile
		setPropertyValue(7, "");   // switch input to a binary file of singles
		setPropertyValue(8, "");   // switch input to a binary file of singles
		setPropertyValue(9, "");   // action option.
		setPropertyValue(10, "");  // Qmult.
		setPropertyValue(11, "No");
		setPropertyValue(12, "0");
		setPropertyValue(13, "0");
		setPropertyValue(14, "3600");  // seconds
		setPropertyValue(15, "60");    // minutes

		super.initPropertyValues(LoadShape.NumPropsThisClass - 1);
	}

	public void saveToDblFile() {
		int i;
		String fileName;
		PrintWriter pw;

		if (PMultipliers != null) {
			try {
				fileName = String.format("%s_P.dbl", getName());
				pw = new PrintWriter(fileName);

				for (i = 0; i < numPoints; i++)
					pw.println(PMultipliers[i]);


				DSS.globalResult = "mult=[dblfile=" + fileName + ']';

				pw.close();

				fileName = String.format("%s_Q.dbl", getName());
				pw = new PrintWriter(fileName);

				for (i = 0; i < numPoints; i++)
					pw.println(QMultipliers[i]);

				pw.close();

				DSS.appendGlobalResult(" Qmult=[dblfile=" + fileName + ']');
			} catch (IOException e) {
				DSS.doSimpleMsg("Error writing load shape multipliers.", -1);
			}
		} else {
			DSS.doSimpleMsg("Loadshape." + getName() + " P multipliers not defined.", 622);
		}
	}

	public void saveToSngFile() {
		saveToDblFile();
	}

	protected void setMaxPandQ() {
		iMaxP = Util.iMaxAbsdblArrayValue(numPoints, PMultipliers);

		if (iMaxP >= 0) {
			maxP = PMultipliers[iMaxP];
			maxQ = QMultipliers != null ? QMultipliers[iMaxP] : 0.0;
		}
	}

	public void setMean(double value) {
		stdDevCalculated = true;
		mean[0] = value;
	}

	public void setNumPoints(int value) {
		setPropertyValue(0, String.valueOf(value));  // update property list variable

		// reset array property values to keep them in proper order in save

		if (arrayPropertyIndex >= 0)
			propertyValues[arrayPropertyIndex] = propertyValues[arrayPropertyIndex];

		if (QMultipliers != null)
			propertyValues[10] = propertyValues[10];

		numPoints = value;  // now assign the value
	}

	public void setStdDev(double stddev) {
		stdDevCalculated = true;
		stdDev[0] = stddev;
	}

	public double getPMultiplier(int idx) {
		return PMultipliers[idx];
	}

	public void setPMultiplier(int idx, double val) {
		PMultipliers[idx] = val;
	}

	public double getQMultiplier(int idx) {
		return QMultipliers[idx];
	}

	public void setQMultiplier(int idx, double val) {
		QMultipliers[idx] = val;
	}

	public double getHour(int idx) {
		return hours[idx];
	}

	public void setHour(int idx, double val) {
		hours[idx] = val;
	}

}
