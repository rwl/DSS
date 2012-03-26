/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;

/**
 * Nominally, a time-current curve, but also used for volt-time curves.
 *
 * Collections of time points. Return values can be interpolated either
 * Log-Log as traditional TCC or as over- or under-voltage definite time.
 *
 */
public class TCC_CurveObj extends DSSObject {

	private int lastValueAccessed, npts;

	private double[] logT, logC;  // logarithms of tValues and cValues
	private double[] tValues;  // time values (hr) if interval > 0.0 else null
	private double[] cValues;

	public TCC_CurveObj(DSSClass parClass, String name) {
		super(parClass);

		setName(name.toLowerCase());
		objType = parClass.getClassType();

		lastValueAccessed = 0;
		npts = 0;
		cValues = null;
		tValues = null;
		logC = null;
		logT = null;

		initPropertyValues(0);
	}

	/**
	 * This function returns the operation time for the value given.
	 * If the value is less than the first entry, return = -1 for no operation.
	 * Log-log interpolation is used.
	 */
	public double getTCCTime(double cValue) {
		int i;
		double logTest, time;

		time = -1.0;  // default return value

		/* If current is less than first point, just leave. */
		if (cValue < cValues[0]) return time;

		if (npts > 0) {  // handle exceptional cases
			if (npts == 1) {
				time = tValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially}
				 */
				if (cValues[lastValueAccessed] > cValue)
					lastValueAccessed = 0;  // start over from beginning

				for (i = lastValueAccessed + 1; i < npts; i++) {
					if (cValues[i] == cValue) {
						time = tValues[i];
						lastValueAccessed = i;
						return time;
					} else if (cValues[i] > cValue) {  // log-log interpolation
						lastValueAccessed = i - 1;
						logTest = cValue > 0.0 ? Math.log(cValue) : Math.log(0.001);
						time = Math.exp(logT[lastValueAccessed] +
							(logTest - logC[lastValueAccessed]) / (logC[i] - logC[lastValueAccessed]) *
							(logT[i] - logT[lastValueAccessed]));
						return time;
					}
				}

				// if we fall through the loop, just use last value
				lastValueAccessed = npts - 2;
				time = tValues[npts];
			}
		}

		return time;
	}

	/**
	 * Return operating time for over-voltage relay.
	 */
	public double getOVTime(double vValue) {
		int i;
		double time = -1.0;  // no op return

		if (vValue > cValues[0]) {
			if (npts == 1) {
				time = tValues[0];
			} else {
				i = 0;
				while (cValues[i] < vValue) {
					i += 1;
					if (i >= npts) break;
				}
				time = tValues[i - 1];
			}
		}

		return time;
	}

	/**
	 * Return operating time for under-voltage relay.
	 */
	public double getUVTime(double vValue) {
		int i;
		double time = -1.0;  // no op return

		if (vValue < cValues[npts])  {
			if (npts == 1) {
				time = tValues[0];
			} else {
				i = npts - 1;
				while (cValues[i] > vValue) {
					i -= 1;
					if (i < 0) break;
				}
				time = tValues[i + 1];
			}
		}

		return time;
	}

	/**
	 * Get C_Value by index.
	 */
	public double value(int i) {
		if (i < npts && i >= 0) {
			lastValueAccessed = i;
			return cValues[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get time value (sec) corresponding to point index.
	 */
	public double time(int i) {
		if (i <= npts && i > 0) {
			lastValueAccessed = i;
			return tValues[i];
		} else {
			return 0.0;
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < parentClass.getNumProperties(); i++)
			pw.println("~ " + parentClass.getPropertyName(i) + "=" + propertyValues[i]);

		pw.close();
	}

	@Override
	public String getPropertyValue(int index) {
		int i;
		String result;

		switch (index) {
		case 1:
		case 2:
			result = "(";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 1:
			for (i = 0; i < npts; i++)
				result += String.format("%g, ", cValues[i]);
			break;
		case 2:
			for (i = 0; i < npts; i++)
				result += String.format("%g, ", tValues[i]);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 1:
		case 2:
			result = result + ")";
			break;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "0");  // number of points to expect
		setPropertyValue(1, "");   // vector of multiplier values
		setPropertyValue(2, "");   // vector of sec values

		super.initPropertyValues(TCC_Curve.NumPropsThisClass - 1);
	}

	public int getNpts() {
		return npts;
	}

	public void setNpts(int npts) {
		this.npts = npts;
	}

	public double[] getLogT() {
		return logT;
	}

	public double[] getLogC() {
		return logC;
	}

	public double[] getTValues() {
		return tValues;
	}

	public double[] getCValues() {
		return cValues;
	}

	public void setLogT(double[] logT) {
		this.logT = logT;
	}

	public void setLogC(double[] logC) {
		this.logC = logC;
	}

	public void setTValues(double[] tValues) {
		this.tValues = tValues;
	}

	public void setCValues(double[] cValues) {
		this.cValues = cValues;
	}

}
