package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TCC_Curve;
import com.epri.dss.general.TCC_CurveObj;

public class TCC_CurveObjImpl extends DSSObjectImpl implements TCC_CurveObj {

	private int lastValueAccessed,
		npts;  // Number of points in curve

	private double[] logT, logC,  // logarithms of t_values and c_values
		tValues,  // time values (hr) if interval > 0.0  else null
		cValues;

	public TCC_CurveObjImpl(DSSClass parClass, String name) {
		super(parClass);

		setName(name.toLowerCase());
		objType = parClass.getDSSClassType();

		lastValueAccessed = 0;  // TODO Check zero based indexing
		npts = 0;
		cValues = null;
		tValues = null;
		logC     = null;
		logT     = null;

		initPropertyValues(0);
	}

	/**
	 * This function returns the operation time for the value given.
	 * If the value is less than the first entry, return = -1 for no operation.
	 * Log-log interpolation is used.
	 */
	public double getTCCTime(double cValue) {
		int i;
		double logTest;

		double result = -1.0;  // default return value

		/* If current is less than first point, just leave. */
		if (cValue < cValues[0])
			return result;

		if (npts > 0)  // Handle exceptional cases
			if (npts == 1) {
				result = tValues[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially}
				 */
				if (cValues[lastValueAccessed] > cValue)
					lastValueAccessed = 0;  // start over from beginning
				for (i = lastValueAccessed + 1; i < npts; i++) {  // TODO Check zero based indexing
					if (cValues[i] == cValue) {
						result = tValues[i];
						lastValueAccessed = i;
						return result;
					} else if (cValues[i] > cValue) {  // log-log interpolation
						lastValueAccessed = i - 1;  // TODO Check zero based indexing
						if (cValue > 0.0) {
							logTest = Math.log(cValue);
						} else {
							logTest = Math.log(0.001);
						}
						result = Math.exp( logT[lastValueAccessed] +
								(logTest - logC[lastValueAccessed]) / (logC[i] - logC[lastValueAccessed]) *
								(logT[i] - logT[lastValueAccessed]) );
						return result;
					}
				}

				// if we fall through the loop, just use last value
				lastValueAccessed = npts - 1;  // TODO Check zero based indexing
				result = tValues[npts];
			}

		return result;
	}

	/**
	 * Return operating time for over-voltage relay.
	 */
	public double getOVTime(double vValue) {
		int i;
		double result = -1.0;  // no op return

		if (vValue > cValues[0]) {
			if (npts == 1) {
				result = tValues[0];
			} else {
				i = 0;  // TODO Check zero based indexing
				while (cValues[i] < vValue) {
					i += 1;
					if (i > npts)
						break;
				}
				result = tValues[i - 1];
			}
		}

		return result;
	}

	/**
	 * Return operating time for under-voltage relay.
	 */
	public double getUVTime(double vValue) {
		int i;
		double result = -1.0;  // no op return

		if (vValue < cValues[npts])  {
			if (npts == 1) {
				result = tValues[0];
			} else {
				i = npts;
				while (cValues[i] > vValue) {
					i -= 1;
					if (i == 0)
						break;
				}
				result = tValues[i + 1];
			}
		}
		return result;
	}

	/**
	 * Get C_Value by index.
	 */
	public double value(int i) {
		if ((i <= npts) && (i > 0)) {
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
		if ((i <= npts) && (i > 0)) {
			lastValueAccessed = i;
			return tValues[i];
		} else {
			return 0.0;
		}
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < parentClass.getNumProperties(); i++)
			f.println("~ " + parentClass.getPropertyName()[i] + "=" + propertyValue[i]);
	}

	@Override
	public String getPropertyValue(int index) {
		int i;
		String result;

		switch (index) {
		case 1:
			result = "(";
			break;
		case 2:
			result = "(";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 2:
			for (i = 0; i < npts; i++)
				result = result + String.format("%-g, ", cValues[i]);
			break;
		case 3:
			for (i = 0; i < npts; i++)
				result = result + String.format("%-g, ", tValues[i]);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 1:
			result = result + ")";
			break;
		case 2:
			result = result + ")";
			break;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		propertyValue[0] = "0";  // number of points to expect
		propertyValue[1] = "";   // vector of multiplier values
		propertyValue[2] = "";   // vector of sec values

		super.initPropertyValues(TCC_Curve.NumPropsThisClass);
	}

	public int getNumPoints() {
		return npts;
	}

	public int getLastValueAccessed() {
		return lastValueAccessed;
	}

	public void setLastValueAccessed(int lastValue) {
		lastValueAccessed = lastValue;
	}

	public int getNPts() {
		return npts;
	}

	public void setNPts(int n) {
		npts = n;
	}

	public double[] getLogT() {
		return logT;
	}

	public void setLogT(double[] logt) {
		logT = logt;
	}

	public double[] getLogC() {
		return logC;
	}

	public void setLogC(double[] logc) {
		logC = logc;
	}

	public double[] getCValues() {
		return cValues;
	}

	public void setCValues(double[] values) {
		cValues = values;
	}

	public double[] getTValues() {
		return tValues;
	}

	public void setTValues(double[] values) {
		tValues = values;
	}

}
