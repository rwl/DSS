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
		objType = parClass.getDSSClassType();

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
	 * The first year specified in the curve is the base year.  The base value
	 * is the beginning of the first year.
	 */
	public double getMult(int yr) {
		int index;

		double result = 1.0;  // default return value if no points in curve

		if (npts > 0) {  // handle exceptional cases
			index = yr - baseYear;
			if (index >= 0) {  // returns 1.0 for base year or any year previous
				if (index >= nYears) {  // make some more space
					nYears = index + 10;
					yearMult = Util.resizeArray(yearMult, nYears);
					reCalcYearMult();
				}
				result = yearMult[index];
			}
		}

		return result;
	}

	/* FIXME Private procedure in OpenDSS */
	public void reCalcYearMult() {
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

	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 1:
				pw.println("~ " + parentClass.getPropertyName(i) +
					"=(" + getPropertyValue(i) + ")");
				break;
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
		case 1:
			for (i = 0; i < npts; i++)
				result = result + String.format("%-d, ", year[i]);
			break;
		case 2:
			for (i = 0; i < npts; i++)
				result = result + String.format("%-g, ", multiplier[i]);
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

	public void initPropertyValues(int arrayOffset) {

		propertyValue[0] = "0";  // number of points to expect
		propertyValue[1] = "";   // vector of year values
		propertyValue[2] = "";   // vector of multiplier values corresponding to years
		propertyValue[3] = "";   // switch input to a csvfile                (year, mult)
		propertyValue[4] = "";   // switch input to a binary file of singles (year, mult)
		propertyValue[5] = "";   // switch input to a binary file of doubles (year, mult)

		super.initPropertyValues(GrowthShape.NumPropsThisClass - 1);
	}


	/* FIXME Private members in OpenDSS */
	public int getNPts() {
		return npts;
	}

	public void setNPts(int n) {
		npts = n;
	}

	public int[] getYear() {
		return year;
	}

	public void setYear(int[] values) {
		year = values;
	}

	public double[] getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double[] mult) {
		multiplier = mult;
	}

	public int getBaseYear() {
		return baseYear;
	}

	public void setBaseYear(int year) {
		baseYear = year;
	}

}
