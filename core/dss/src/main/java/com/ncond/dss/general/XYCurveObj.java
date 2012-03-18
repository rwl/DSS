/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSSClass;

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
@Getter @Setter
public class XYCurveObj extends DSSObject {

	private int lastValueAccessed, numPoints;
	private int arrayPropertyIndex;
	private double X, Y;
	private double[] XValues, YValues;

	public XYCurveObj(DSSClass parClass, String XYCurveName) {
		super(parClass);

		setName(XYCurveName.toLowerCase());
		objType = parClass.getClassType();

		lastValueAccessed = 0;

		numPoints = 0;
		XValues = null;
		YValues = null;

		X = 0.0;
		Y = 0.0;

		arrayPropertyIndex = -1;

		initPropertyValues(0);
	}

	/**
	 * Get Y value at specified X value.
	 *
	 * This method returns the interpolated Y value for the given X.
	 * If no points exist in the curve, the result is  0.0
	 * If Xvalue is outside the range of defined X values,
	 * the curve is extrapolated from the ends.
	 */
	public double getYValue(double X) {
		double val = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				return YValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, the values won't change much. */

				if (XValues[lastValueAccessed] > X)
					lastValueAccessed = 0;  // start over from beginning

				// if off the curve for the first point, extrapolate from the first two points
				if (lastValueAccessed == 0 && XValues[0] > X) {
					val = interpolatePoints(0, 1, X, XValues, YValues);
					return val;
				}

				// in the middle of the arrays
				for (int i = lastValueAccessed; i < numPoints; i++) {
					if (Math.abs(XValues[i] - X) < 0.00001) {  // if close to an actual point, just use it
						val = YValues[i];
						lastValueAccessed = i;
						return val;
					} else if (XValues[i] > X) {
						// interpolate between two values
						lastValueAccessed = i - 1;
						val = interpolatePoints(i, lastValueAccessed, X, XValues, YValues);
						return val;
					}
				}

				// if we fall through the loop, extrapolate from last two points
				lastValueAccessed = numPoints - 2;
				val = interpolatePoints(numPoints, lastValueAccessed,  X, XValues, YValues);
			}

		return val;
	}

	/**
	 * Get Y value by index.
	 */
	public double getYValue(int i) {
		if (i < numPoints && i >= 0) {
			lastValueAccessed = i;
			return YValues[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get X value corresponding to point index.
	 */
	public double getXValue(int i) {
		if (i < numPoints && i >= 0) {
			lastValueAccessed = i;
			return XValues[i];
		} else {
			return 0.0;
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
	}

	@Override
	public String getPropertyValue(int index) {
		String result;

		switch (index) {
		case 1:
		case 2:
		case 3:
			result = "[";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 1:
			if ((XValues != null) && (YValues != null)) {
				for (int i = 0; i < numPoints; i++)
					result = result + String.format("%.8g, %.8g ", XValues[i], YValues[i]);
			} else {
				result = "0, 0";
			}
			break;
		case 2:
			if (YValues != null) {
				for (int i = 0; i < numPoints; i++)
					result = result + String.format("%-g, ", YValues[i]);
			} else {
				result = "0";
			}
			break;
		case 3:
			if (XValues != null) {
				for (int i = 0; i < numPoints; i++)
					result = result + String.format("%-g, ", XValues[i]);
			} else {
				result = "0";
			}
			break;
		case 7:
			result = String.format("%.8g", getXValue(Y));
			break;
		case 8:
			result = String.format("%.8g", getYValue(X));
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 1:
		case 2:
		case 3:
			result += "]";
			break;
		}

		return result;
	}

	/**
	 * Get X value at specified Y value.
	 *
	 * This method returns the interpolated X value for the given Y.
	 * If no points exist in the curve, the result is 0.0
	 * If Xvalue is outside the range of defined X values,
	 * the curve is extrapolated from the Ends.
	 */
	public double getXValue(double Y) {
		double val = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				val = XValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially
				 */

				if (YValues[lastValueAccessed] > Y)
					lastValueAccessed = 0;  // start over from beginning

				// if off the curve for the first point, extrapolate from the first two points
				if (lastValueAccessed == 0 && YValues[0] > Y) {
					val = interpolatePoints(0, 1, Y, YValues, XValues);
					return val;
				}

				for (int i = lastValueAccessed; i < numPoints; i++) {
					if (Math.abs(YValues[i] - Y) < 0.00001) {  // if close to an actual point, just use it.
						val = XValues[i];
						lastValueAccessed = i;
						return val;
					} else if (YValues[i] > Y) {
						// interpolate
						lastValueAccessed = i - 1;
						val = interpolatePoints(i, lastValueAccessed, Y, YValues, XValues);
						return val;
					}
				}

				// if we fall through the loop, extrapolate from last two points
				lastValueAccessed = numPoints - 2;
				val = interpolatePoints(numPoints, lastValueAccessed,  Y, YValues, XValues);
			}

		return val;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "0");  // number of points to expect
		setPropertyValue(1, "");
		setPropertyValue(2, "");
		setPropertyValue(3, "");
		setPropertyValue(4, "");
		setPropertyValue(5, "");
		setPropertyValue(6, "");

		super.initPropertyValues(XYCurve.NumPropsThisClass);
	}

	private double interpolatePoints(int i, int j, double X, double[] XArray, double[] YArray) {
		double den = (XArray[i] - XArray[j]);

		if (den != 0.0) {
			return YArray[j] + (X - XArray[j]) / den * (YArray[i] - YArray[j]);
		} else {
			return YArray[i];  // Y is undefined, return i-th value
		}
	}

	public void setNumPoints(int num) {
		setPropertyValue(0, String.valueOf(num));  // update property list variable

		// reset array property values to keep them in proper order in save

		if (arrayPropertyIndex >= 0)
			setPropertyValue(arrayPropertyIndex, getPropertyValue(arrayPropertyIndex));

		numPoints = num;  // now assign the value
	}

}
