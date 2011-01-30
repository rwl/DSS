package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TCC_CurveObj;

public class TCC_CurveObjImpl extends DSSObjectImpl implements TCC_CurveObj {
	
	private int LastValueAccessed,
		Npts;  // Number of points in curve

	private double[] Logt, LogC,  // Logarithms of t_values and c_values
		t_values,  // Time values (hr) if Interval > 0.0  Else null
		c_values;

	public TCC_CurveObjImpl(DSSClass ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	/* Return operating time for a particular time value */
	public double getTCCTime(double C_Value) {
		return 0.0;
	}
	
	/* Return operating time for undervoltage relay */
	public double getUVTime(double V_Value) {
		return 0.0;
	}
	
	/* Return operating time for overvoltage relay */
	public double getOVTime(double V_Value) {
		return 0.0;
	}
	
	/* Get C_Value by index */
	public double value(int i) {
		return 0.0;
	}
	
	/* Get time value (sec) corresponding to point index */
	public double time(int i) {
		return 0.0;
	}

	public int getNumPoints() {
		return Npts;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}

}
