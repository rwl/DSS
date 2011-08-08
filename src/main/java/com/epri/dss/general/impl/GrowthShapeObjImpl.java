package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.GrowthShape;
import com.epri.dss.general.GrowthShapeObj;

public class GrowthShapeObjImpl extends DSSObjectImpl implements GrowthShapeObj {

	/* Number of points in curve */
	private int Npts;
	/* Number of years presently allocated in look up table */
	private int NYears;
	private int BaseYear;

	/* Year values */
	private int[] Year;
	private double[] YearMult, Multiplier;

	public GrowthShapeObjImpl(DSSClass ParClass, String GrowthShapeName) {
		super(ParClass);

		setName(GrowthShapeName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.Npts = 0;
		this.Year = null;
		this.Multiplier = null;
		this.NYears = 30;
		this.YearMult = new double[this.NYears];

		initPropertyValues(0);
	}

	/**
	 * Get multiplier for specified year.
	 *
	 * This function returns the multiplier to use for a load in the given year.
	 * The first year specified in the curve is the base year.  The base value
	 * is the beginning of the first year.
	 */
	public double getMult(int Yr) {
		int Index;

		double Result = 1.0;  // default return value if no points in curve

		if (Npts > 0) {  // Handle Exceptional cases
			Index = Yr - BaseYear;
			if (Index > 0) {  // Returns 1.0 for base year or any year previous  TODO Check zero based indexing

				if (Index > NYears) {  // Make some more space
					NYears = Index + 10;
					YearMult = (double[]) Utilities.resizeArray(YearMult, NYears);
					reCalcYearMult();
				}

				Result = YearMult[Index];

			}

		}

		return Result;
	}

	/* FIXME Private procedure in OpenDSS */
	public void reCalcYearMult() {
		// Fill up the YearMult array with total yearly multiplier from base year
		double Mult = Multiplier[1];
		double MultInc = Mult;
		YearMult[0] = Mult;  // TODO Check zero based indexing
		int DataPtr = 1;  // TODO Check zero based indexing
		int Yr = BaseYear;
		for (int i = 1; i < NYears; i++) {  // TODO Check zero based indexing
			Yr += 1;
			if (DataPtr < Npts) {
				if (Year[DataPtr + 1] == Yr) {
					DataPtr += 1;
					MultInc = Multiplier[DataPtr];
				}
			}
			Mult = Mult * MultInc;
			YearMult[i] = Mult;
		}
	}

	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < ParentClass.getNumProperties(); i++) {
			switch (i) {
			case 2:  // TODO Check zero based indexing
				F.println("~ " + ParentClass.getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
				break;
			case 3:
				F.println("~ " + ParentClass.getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
				break;
			default:
				F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}
	}

	public String getPropertyValue(int Index) {
		int i;
		String Result;

		switch (Index) {
		case 2:  // TODO Check zero based indexing
			Result = "(";
			break;
		case 3:
			Result = "(";
			break;
		default:
			Result = "";
			break;
		}

		switch (Index) {
		case 2:
			for (i = 0; i < Npts; i++)
				Result = Result + String.format("%-d, ", Year[i]);
			break;
		case 3:
			for (i = 0; i < Npts; i++)
				Result = Result + String.format("%-g, ", Multiplier[i]);
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		switch (Index) {
		case 2:
			Result = Result + ")";
			break;
		case 3:
			Result = Result + ")";
			break;
		}

		return Result;
	}

	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "0";  // Number of points to expect
		PropertyValue[1] = "";   // vector of year values
		PropertyValue[2] = "";   // vector of multiplier values corresponding to years
		PropertyValue[3] = "";   // Switch input to a csvfile                (year, mult)
		PropertyValue[4] = "";   // switch input to a binary file of singles (year, mult)
		PropertyValue[5] = "";   // switch input to a binary file of doubles (year, mult)

		super.initPropertyValues(GrowthShape.NumPropsThisClass);
	}


	/* FIXME Private members in OpenDSS */
	public int getNpts() {
		return Npts;
	}

	public void setNpts(int npts) {
		Npts = npts;
	}

	public int[] getYear() {
		return Year;
	}

	public void setYear(int[] year) {
		Year = year;
	}

	public double[] getMultiplier() {
		return Multiplier;
	}

	public void setMultiplier(double[] multiplier) {
		Multiplier = multiplier;
	}

	public int getBaseYear() {
		return BaseYear;
	}

	public void setBaseYear(int baseYear) {
		BaseYear = baseYear;
	}

}
