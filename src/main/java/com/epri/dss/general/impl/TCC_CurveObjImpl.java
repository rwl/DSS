package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TCC_Curve;
import com.epri.dss.general.TCC_CurveObj;

public class TCC_CurveObjImpl extends DSSObjectImpl implements TCC_CurveObj {
	
	private int LastValueAccessed,
		Npts;  // Number of points in curve

	private double[] LogT, LogC,  // Logarithms of t_values and c_values
		t_values,  // Time values (hr) if Interval > 0.0  Else null
		c_values;

	public TCC_CurveObjImpl(DSSClass ParClass, String Name) {
		super(ParClass);
		
		setName(Name.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

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
	 * If the value is less than the first entry, return = -1 for No operation.
	 * Log-Log  interpolation is used.
	 */
	public double getTCCTime(double C_Value) {
		int i;
		double LogTest;

		double Result = -1.0;  // default return value

		/* If current is less than first point, just leave. */
		if (C_Value < c_values[0])
			return Result;

		if (Npts > 0)  // Handle Exceptional cases
			if (Npts == 1) {
				Result = t_values[0];
			} else {
				/* Start with previous value accessed under the assumption that most
				 * of the time, this function will be called sequentially}
				 */
				if (c_values[LastValueAccessed] > C_Value)
					LastValueAccessed = 0;  // Start over from beginning
				for (i = LastValueAccessed + 1; i < Npts; i++) {  // TODO Check zero based indexing
					if (c_values[i] == C_Value) {
						Result = t_values[i];  // direct hit!
						LastValueAccessed = i;
						return Result;
					} else if (c_values[i] > C_Value) {  // Log-Log interpolation
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

				// If we fall through the loop, just use last value
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
		double Result = -1.0;  // No op return

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
		double Result = -1.0;  // No op return

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

		for (int i = 0; i < ParentClass.getNumProperties(); i++) 
			F.println("~ " + ParentClass.getPropertyName()[i] + "=" + PropertyValue[i]);
	}
	
	@Override
	public String getPropertyValue(int Index) {
		int i;
		String Result;

		switch (Index) {
		case 1:
			Result = "(";
		case 2:
			Result = "(";
		default:
			Result = "";
		}

		switch (Index) {
		case 2:
			for (i = 0; i < Npts; i++) 
				Result = Result + String.format("%-g, ", c_values[i]);
		case 3:
			for (i = 0; i < Npts; i++) 
				Result = Result + String.format("%-g, ", t_values[i]);
		default:
			Result = super.getPropertyValue(Index);
		}

		switch (Index) {
		case 1:
			Result = Result + ")";
		case 2:
			Result = Result + ")";
		}

		return Result;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		PropertyValue[0] = "0";     // Number of points to expect
		PropertyValue[1] = "";      // vector of multiplier values
		PropertyValue[2] = "";      // vector of sec values

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

	public int getNpts() {
		return Npts;
	}

	public void setNpts(int npts) {
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

	public double[] getC_values() {
		return c_values;
	}

	public void setC_values(double[] c_values) {
		this.c_values = c_values;
	}

	public double[] getT_values() {
		return t_values;
	}

	public void setT_values(double[] t_values) {
		this.t_values = t_values;
	}

}
