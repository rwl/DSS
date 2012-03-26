/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.Util;

public class GrowthShapeObj extends DSSObject {

	/** Number of points in curve */
	private int npts;

	/** Number of years presently allocated in look up table */
	private int nYears;
	private int baseYear;

	/** Year values */
	private int[] year;
	private double[] yearMult, multiplier;

	public GrowthShapeObj(DSSClass parClass, String growthShapeName) {
		super(parClass);

		setName(growthShapeName.toLowerCase());
		objType = parClass.getClassType();

		npts = 0;
		year = null;
		multiplier = null;
		nYears = 30;
		yearMult = new double[nYears];

		initPropertyValues(0);
	}

	/**
	 * Get multiplier for specified year.
	 *
	 * This function returns the multiplier to use for a load in the given year.
	 * The first year specified in the curve is the base year. The base value
	 * is the beginning of the first year.
	 */
	public double getMult(int yr) {
		int index;

		double mult = 1.0;  // default return value if no points in curve

		if (npts > 0) {  // handle exceptional cases
			index = yr - baseYear;
			if (index >= 0) {  // returns 1.0 for base year or any year previous
				if (index >= nYears) {  // make some more space
					nYears = index + 10;
					yearMult = Util.resizeArray(yearMult, nYears);
					reCalcYearMult();
				}
				mult = yearMult[index];
			}
		}

		return mult;
	}

	protected void reCalcYearMult() {
		// fill up the yearMult array with total yearly multiplier from base year
		double mult = multiplier[0];
		double multInc = mult;
		yearMult[0] = mult;
		int dataPtr = 0;
		int yr = baseYear;

		for (int i = 1; i < nYears; i++) {
			yr += 1;
			if (dataPtr < npts - 1) {
				if (year[dataPtr + 1] == yr) {
					dataPtr += 1;
					multInc = multiplier[dataPtr];
				}
			}
			mult = mult * multInc;
			yearMult[i] = mult;
		}
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 1:
			case 2:
				pw.println("~ " + parentClass.getPropertyName(i) +
					"=(" + getPropertyValue(i) + ")");
				break;
			default:
				pw.println("~ " + parentClass.getPropertyName(i) +
					"=" + getPropertyValue(i));
				break;
			}
		}
		pw.close();
	}

	@Override
	public String getPropertyValue(int index) {
		int i;
		String val;

		switch (index) {
		case 1:
		case 2:
			val = "(";
			break;
		default:
			val = "";
			break;
		}

		switch (index) {
		case 1:
			for (i = 0; i < npts; i++)
				val += String.format("%-d, ", year[i]);
			break;
		case 2:
			for (i = 0; i < npts; i++)
				val += String.format("%g, ", multiplier[i]);
			break;
		default:
			val = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 1:
		case 2:
			val = val + ")";
			break;
		}

		return val;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {

		setPropertyValue(0, "0");  // number of points to expect
		setPropertyValue(1, "");   // vector of year values
		setPropertyValue(2, "");   // vector of multiplier values corresponding to years
		setPropertyValue(3, "");   // switch input to a csvfile                (year, mult)
		setPropertyValue(4, "");   // switch input to a binary file of singles (year, mult)
		setPropertyValue(5, "");   // switch input to a binary file of doubles (year, mult)

		super.initPropertyValues(GrowthShape.NumPropsThisClass - 1);
	}

	public int getNpts() {
		return npts;
	}

	public void setNpts(int npts) {
		this.npts = npts;
	}

	public int[] getYear() {
		return year;
	}

	public double[] getMultiplier() {
		return multiplier;
	}

	public void setBaseYear(int baseYear) {
		this.baseYear = baseYear;
	}

	public void setYear(int[] year) {
		this.year = year;
	}

	public void setMultiplier(double[] multiplier) {
		this.multiplier = multiplier;
	}

}
