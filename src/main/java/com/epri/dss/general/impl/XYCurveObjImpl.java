package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.XYCurve;
import com.epri.dss.general.XYCurveObj;

public class XYCurveObjImpl extends DSSObjectImpl implements XYCurveObj {

	private int LastValueAccessed,
			NumPoints;  // number of points in curve
	private int ArrayPropertyIndex;
	private double X, Y;
	private double[] XValues, YValues;

	public XYCurveObjImpl(DSSClass ParClass, String XYCurveName) {
		super(ParClass);
		setName(XYCurveName.toLowerCase());
		this.objType = ParClass.getDSSClassType();

		this.LastValueAccessed = 0;

		this.NumPoints = 0;
		this.XValues = null;
		this.YValues = null;

		this.X = 0.0;
		this.Y = 0.0;

		ArrayPropertyIndex = -1;

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
		double Result = 0.0;  // default return value if no points in curve

		if (NumPoints > 0)  // handle exceptional cases
			if (NumPoints == 1) {
				return YValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, the values won't change much.
				 */

				if (XValues[LastValueAccessed] > X)
					LastValueAccessed = 1;  // start over from beginning

				// if off the curve for the first point, extrapolate from the first two points
				if ((LastValueAccessed == 0) && (XValues[0] > X)) {
					Result = interpolatePoints(0, 1, X, XValues, YValues);
					return Result;
				}

				// in the middle of the arrays
				for (int i = LastValueAccessed; i < NumPoints; i++) {
					if (Math.abs(XValues[i] - X) < 0.00001) {  // if close to an actual point, just use it
						Result = YValues[i];
						LastValueAccessed = i;
						return Result;
					} else if (XValues[i] > X) {
						// interpolate between two values
						LastValueAccessed = i - 1;  // TODO Check zero based indexing
						Result = interpolatePoints(i, LastValueAccessed, X, XValues, YValues);
						return Result;
					}
				}

				// if we fall through the loop, Extrapolate from last two points
				LastValueAccessed = NumPoints - 1;
				Result = interpolatePoints(NumPoints, LastValueAccessed,  X, XValues, YValues);
			}

		return Result;
	}

	/**
	 * Get Y value by index.
	 */
	public double getYValue(int i) {
		if ((i < NumPoints) && (i >= 0)) {
			LastValueAccessed = i;
			return YValues[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get X value corresponding to point index.
	 */
	public double getXValue(int i) {
		if ((i < NumPoints) && (i >= 0)) {
			LastValueAccessed = i;
			return XValues[i];
		} else {
			return 0.0;
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 2:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
				break;
			case 3:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
				break;
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}
	}

	@Override
	public String getPropertyValue(int Index) {
		String Result;

		switch (Index) {
		case 1:
			Result = "[";
			break;
		case 2:
			Result = "[";
			break;
		case 3:
			Result = "[";
			break;
		default:
			Result = "";
			break;
		}

		switch (Index) {
		case 1:
			if ((XValues != null) && (YValues != null)) {
				for (int i = 0; i < NumPoints; i++)
					Result = Result + String.format("%.8g, %.8g ", XValues[i], YValues[i]);
			} else {
				Result = "0, 0";
			}
			break;
		case 2:
			if (YValues != null) {
				for (int i = 0; i < NumPoints; i++)
					Result = Result + String.format("%-g, ", YValues[i]);
			} else {
				Result = "0";
			}
			break;
		case 3:
			if (XValues != null) {
				for (int i = 0; i < NumPoints; i++)
					Result = Result + String.format("%-g, ", XValues[i]);
			} else {
				Result = "0";
			}
			break;
		case 7:
			Result = String.format("%.8g", getXValue(Y));
			break;
		case 8:
			Result = String.format("%.8g", getYValue(X));
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		switch (Index) {
		case 1:
			Result = "[";
			break;
		case 2:
			Result = "[";
			break;
		case 3:
			Result = "[";
			break;
		}

		return Result;
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
		double Result = 0.0;  // default return value if no points in curve

		if (NumPoints > 0)  // handle exceptional cases
			if (NumPoints == 1) {
				Result = XValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially
				 */

				if (YValues[LastValueAccessed] > Y)
					LastValueAccessed = 0;  // start over from beginning

				// if off the curve for the first point, extrapolate from the first two points
				if ((LastValueAccessed == 0) && (YValues[0] > Y)) {
					Result = interpolatePoints(0, 1, Y, YValues, XValues);
					return Result;
				}

				for (int i = LastValueAccessed; i < NumPoints; i++) {  // TODO Check zero based indexing
					if (Math.abs(YValues[i] - Y) < 0.00001) {  // if close to an actual point, just use it.
						Result = XValues[i];
						LastValueAccessed = i;
						return Result;
					} else if (YValues[i] > Y) {
						// interpolate
						LastValueAccessed = i - 1;  // TODO Check zero based indexing
						Result = interpolatePoints(i, LastValueAccessed, Y, YValues, XValues);
						return Result;
					}
				}

				// if we fall through the loop, extrapolate from last two points
				LastValueAccessed = NumPoints - 1;
				Result = interpolatePoints(NumPoints, LastValueAccessed,  Y, YValues, XValues);
			}

		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(0, "0");  // number of points to expect
		setPropertyValue(1, "");
		setPropertyValue(2, "");
		setPropertyValue(3, "");
		setPropertyValue(4, "");
		setPropertyValue(5, "");
		setPropertyValue(6, "");

		super.initPropertyValues(XYCurve.NumPropsThisClass);
	}

	private double interpolatePoints(int i, int j, double X, double[] Xarray, double[] Yarray) {
		double Den = (Xarray[i] - Xarray[j]);
		if (Den != 0.0) {
			return Yarray[j] + (X - Xarray[j]) / Den * (Yarray[i] - Yarray[j]);
		} else {
			return Yarray[i];  // Y is undefined, return i-th value
		}
	}

	public int getNumPoints() {
		return NumPoints;
	}

	public void setNumPoints(int numPoints) {
		setPropertyValue(0, String.valueOf(numPoints));  // update property list variable

		// reset array property values to keep them in proper order in save

		if (ArrayPropertyIndex >= 0)
			setPropertyValue(ArrayPropertyIndex, getPropertyValue(ArrayPropertyIndex));

		NumPoints = numPoints;  // now assign the value
	}

	// FIXME Private members in OpenDSS

	public int getLastValueAccessed() {
		return LastValueAccessed;
	}

	public void setLastValueAccessed(int lastValueAccessed) {
		LastValueAccessed = lastValueAccessed;
	}

	public int getArrayPropertyIndex() {
		return ArrayPropertyIndex;
	}

	public void setArrayPropertyIndex(int arrayPropertyIndex) {
		ArrayPropertyIndex = arrayPropertyIndex;
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

	public void setXValues(double[] xValues) {
		XValues = xValues;
	}

	public double[] getYValues() {
		return YValues;
	}

	public void setYValues(double[] yValues) {
		YValues = yValues;
	}

}
