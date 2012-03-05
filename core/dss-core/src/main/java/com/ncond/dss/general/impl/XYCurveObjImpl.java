package com.ncond.dss.general.impl;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.general.XYCurve;
import com.ncond.dss.general.XYCurveObj;

public class XYCurveObjImpl extends DSSObjectImpl implements XYCurveObj {

	private int lastValueAccessed,
			numPoints;  // number of points in curve
	private int arrayPropertyIndex;
	private double X, Y;
	private double[] XValues, YValues;

	public XYCurveObjImpl(DSSClass parClass, String XYCurveName) {
		super(parClass);
		setName(XYCurveName.toLowerCase());
		objType = parClass.getDSSClassType();

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
		double result = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				return YValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, the values won't change much.
				 */

				if (XValues[lastValueAccessed] > X)
					lastValueAccessed = 1;  // start over from beginning

				// if off the curve for the first point, extrapolate from the first two points
				if (lastValueAccessed == 0 && XValues[0] > X) {
					result = interpolatePoints(0, 1, X, XValues, YValues);
					return result;
				}

				// in the middle of the arrays
				for (int i = lastValueAccessed; i < numPoints; i++) {
					if (Math.abs(XValues[i] - X) < 0.00001) {  // if close to an actual point, just use it
						result = YValues[i];
						lastValueAccessed = i;
						return result;
					} else if (XValues[i] > X) {
						// interpolate between two values
						lastValueAccessed = i - 1;
						result = interpolatePoints(i, lastValueAccessed, X, XValues, YValues);
						return result;
					}
				}

				// if we fall through the loop, extrapolate from last two points
				lastValueAccessed = numPoints - 2;
				result = interpolatePoints(numPoints, lastValueAccessed,  X, XValues, YValues);
			}

		return result;
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
	}

	@Override
	public String getPropertyValue(int index) {
		String result;

		switch (index) {
		case 1:
			result = "[";
			break;
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
			result = "[";
			break;
		case 2:
			result = "[";
			break;
		case 3:
			result = "[";
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
		double result = 0.0;  // default return value if no points in curve

		if (numPoints > 0)  // handle exceptional cases
			if (numPoints == 1) {
				result = XValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially
				 */

				if (YValues[lastValueAccessed] > Y)
					lastValueAccessed = 0;  // start over from beginning

				// if off the curve for the first point, extrapolate from the first two points
				if (lastValueAccessed == 0 && YValues[0] > Y) {
					result = interpolatePoints(0, 1, Y, YValues, XValues);
					return result;
				}

				for (int i = lastValueAccessed; i < numPoints; i++) {
					if (Math.abs(YValues[i] - Y) < 0.00001) {  // if close to an actual point, just use it.
						result = XValues[i];
						lastValueAccessed = i;
						return result;
					} else if (YValues[i] > Y) {
						// interpolate
						lastValueAccessed = i - 1;
						result = interpolatePoints(i, lastValueAccessed, Y, YValues, XValues);
						return result;
					}
				}

				// if we fall through the loop, extrapolate from last two points
				lastValueAccessed = numPoints - 2;
				result = interpolatePoints(numPoints, lastValueAccessed,  Y, YValues, XValues);
			}

		return result;
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

	public int getNumPoints() {
		return numPoints;
	}

	public void setNumPoints(int num) {
		setPropertyValue(0, String.valueOf(num));  // update property list variable

		// reset array property values to keep them in proper order in save

		if (arrayPropertyIndex >= 0)
			setPropertyValue(arrayPropertyIndex, getPropertyValue(arrayPropertyIndex));

		numPoints = num;  // now assign the value
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

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public double[] getXValues() {
		return XValues;
	}

	public void setXValues(double[] xvalues) {
		XValues = xvalues;
	}

	public double[] getYValues() {
		return YValues;
	}

	public void setYValues(double[] yvalues) {
		YValues = yvalues;
	}

}
