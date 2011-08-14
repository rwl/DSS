package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TCC_Curve;
import com.epri.dss.general.TCC_CurveObj;

public class TCC_CurveObjImpl extends DSSObjectImpl implements TCC_CurveObj {

	private int LastValueAccessed,
		Npts;  // Number of points in curve

	private double[] LogT, LogC,  // logarithms of t_values and c_values
		t_values,  // time values (hr) if interval > 0.0  else null
		c_values;

	public TCC_CurveObjImpl(DSSClass ParClass, String Name) {
		super(ParClass);

		setName(Name.toLowerCase());
		this.objType = ParClass.getDSSClassType();

		this.LastValueAccessed = 0;  // TODO Check zero based indexing
		this.Npts = 0;
		this.c_values = null;
		this.t_values = null;
		this.LogC     = null;
		this.LogT     = null;

		initPropertyValues(0);
	}

	/**
	 * This function returns the operation time for the value given.
	 * If the value is less than the first entry, return = -1 for no operation.
	 * Log-log interpolation is used.
	 */
	public double getTCCTime(double C_Value) {
		int i;
		double LogTest;

		double Result = -1.0;  // default return value

		/* If current is less than first point, just leave. */
		if (C_Value < c_values[0])
			return Result;

		if (Npts > 0)  // Handle exceptional cases
			if (Npts == 1) {
				Result = t_values[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially}
				 */
				if (c_values[LastValueAccessed] > C_Value)
					LastValueAccessed = 0;  // start over from beginning
				for (i = LastValueAccessed + 1; i < Npts; i++) {  // TODO Check zero based indexing
					if (c_values[i] == C_Value) {
						Result = t_values[i];
						LastValueAccessed = i;
						return Result;
					} else if (c_values[i] > C_Value) {  // log-log interpolation
						LastValueAccessed = i - 1;  // TODO Check zero based indexing
						if (C_Value > 0.0) {
							LogTest = Math.log(C_Value);
						} else {
							LogTest = Math.log(0.001);
						}
						Result = Math.exp( LogT[LastValueAccessed] +
								(LogTest - LogC[LastValueAccessed]) / (LogC[i] - LogC[LastValueAccessed]) *
								(LogT[i] - LogT[LastValueAccessed]) );
						return Result;
					}
				}

				// if we fall through the loop, just use last value
				LastValueAccessed = Npts - 1;  // TODO Check zero based indexing
				Result = t_values[Npts];
			}

		return Result;
	}

	/**
	 * Return operating time for over-voltage relay.
	 */
	public double getOVTime(double V_Value) {
		int i;
		double Result = -1.0;  // no op return

		if (V_Value > c_values[0]) {
			if (Npts == 1) {
				Result = t_values[0];
			} else {
				i = 0;  // TODO Check zero based indexing
				while (c_values[i] < V_Value) {
					i += 1;
					if (i > Npts)
						break;
				}
				Result = t_values[i - 1];
			}
		}

		return Result;
	}

	/**
	 * Return operating time for under-voltage relay.
	 */
	public double getUVTime(double V_Value) {
		int i;
		double Result = -1.0;  // no op return

		if (V_Value < c_values[Npts])  {
			if (Npts == 1) {
				Result = t_values[0];
			} else {
				i = Npts;
				while (c_values[i] > V_Value) {
					i -= 1;
					if (i == 0)
						break;
				}
				Result = t_values[i + 1];
			}
		}
		return Result;
	}

	/**
	 * Get C_Value by index.
	 */
	public double value(int i) {
		if ((i <= Npts) && (i > 0)) {
			LastValueAccessed = i;
			return c_values[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get time value (sec) corresponding to point index.
	 */
	public double time(int i) {
		if ((i <= Npts) && (i > 0)) {
			LastValueAccessed = i;
			return t_values[i];
		} else {
			return 0.0;
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < parentClass.getNumProperties(); i++)
			F.println("~ " + parentClass.getPropertyName()[i] + "=" + propertyValue[i]);
	}

	@Override
	public String getPropertyValue(int Index) {
		int i;
		String Result;

		switch (Index) {
		case 1:
			Result = "(";
			break;
		case 2:
			Result = "(";
			break;
		default:
			Result = "";
			break;
		}

		switch (Index) {
		case 2:
			for (i = 0; i < Npts; i++)
				Result = Result + String.format("%-g, ", c_values[i]);
			break;
		case 3:
			for (i = 0; i < Npts; i++)
				Result = Result + String.format("%-g, ", t_values[i]);
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		switch (Index) {
		case 1:
			Result = Result + ")";
			break;
		case 2:
			Result = Result + ")";
			break;
		}

		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		propertyValue[0] = "0";  // number of points to expect
		propertyValue[1] = "";   // vector of multiplier values
		propertyValue[2] = "";   // vector of sec values

		super.initPropertyValues(TCC_Curve.NumPropsThisClass);
	}

	public int getNumPoints() {
		return Npts;
	}

	public int getLastValueAccessed() {
		return LastValueAccessed;
	}

	public void setLastValueAccessed(int lastValueAccessed) {
		LastValueAccessed = lastValueAccessed;
	}

	public int getNPts() {
		return Npts;
	}

	public void setNPts(int npts) {
		Npts = npts;
	}

	public double[] getLogT() {
		return LogT;
	}

	public void setLogT(double[] logt) {
		LogT = logt;
	}

	public double[] getLogC() {
		return LogC;
	}

	public void setLogC(double[] logC) {
		LogC = logC;
	}

	public double[] getCValues() {
		return c_values;
	}

	public void setCValues(double[] c_values) {
		this.c_values = c_values;
	}

	public double[] getTValues() {
		return t_values;
	}

	public void setTValues(double[] t_values) {
		this.t_values = t_values;
	}

}
