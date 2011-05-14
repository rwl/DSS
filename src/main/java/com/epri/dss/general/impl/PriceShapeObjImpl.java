package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.PriceShape;
import com.epri.dss.general.PriceShapeObj;
import com.epri.dss.shared.impl.MathUtil;

public class PriceShapeObjImpl extends DSSObjectImpl implements PriceShapeObj {

	private int LastValueAccessed, NumPoints;  // Number of points in curve
	private int ArrayPropertyIndex;

	private boolean StdDevCalculated;
	private double Mean, StdDev;

	protected double Interval;  // =0.0 then random interval (hr)
	protected double[] Hours;   // Time values (hr) if Interval > 0.0  Else nil
	protected double[] PriceValues;  // Prices

	public PriceShapeObjImpl(DSSClass ParClass, String PriceShapeName) {
		super(ParClass);
		setName(PriceShapeName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.LastValueAccessed = 0;

		this.NumPoints = 0;
		this.Interval = 1.0;  // hr
		this.Hours = null;
		this.PriceValues = null;
		this.StdDevCalculated = false;  // calculate on demand

		this.ArrayPropertyIndex = -1;

		initPropertyValues(0);
	}

	/**
	 * Get prices at specified time, hr.
	 *
	 * This method returns the Price for the given hour.
	 * If no points exist in the curve, the result is 0.0
	 * If there are fewer points than requested, the curve is simply assumed to repeat
	 * Thus a daily load curve can suffice for a yearly load curve: You just get the
	 * same day over and over again.
	 * The value returned is the nearest to the interval requested. Thus if you request
	 * hour=12.25 and the interval is 1.0, you will get interval 12.
	 */
	public double getPrice(double Hr) {
		int Index, i;

		double Result = 0.0;    // default return value if no points in curve

		if (NumPoints > 0)  // Handle exceptional cases
			if (NumPoints == 1) {
				Result = PriceValues[0];
			} else {
				if (Interval > 0.0) {
					Index = (int) Math.round(Hr / Interval);
					if (Index > NumPoints) Index = Index % NumPoints;  // Wrap around using remainder
					if (Index == 0) Index = NumPoints;
					Result = PriceValues[Index];
				} else {
					// For random interval

					/* Start with previous value accessed under the assumption that most
					 * of the time, this FUNCTION will be called sequentially
					 */

					/* Normalize Hr to max hour in curve to get wraparound */
					if (Hr > Hours[NumPoints]) {
						Hr = Hr - (int) (Hr / Hours[NumPoints]) * Hours[NumPoints];
					}

					if (Hours[LastValueAccessed] > Hr) LastValueAccessed = 1;  // Start over from Beginning
					for (i = LastValueAccessed; i < NumPoints; i++) {  // TODO Check zero based indexing
						if (Math.abs(Hours[i] - Hr) < 0.00001) {  // If close to an actual point, just use it.
							Result = PriceValues[i];
							LastValueAccessed = i;
							return Result;
						} else if (Hours[i] > Hr) {  // Interpolate for Price
							LastValueAccessed = i - 1;
							Result = PriceValues[LastValueAccessed] +
									(Hr - Hours[LastValueAccessed]) / (Hours[i] - Hours[LastValueAccessed]) *
									(PriceValues[i] - PriceValues[LastValueAccessed]);
							return Result;
						}
					}

					// If we fall through the loop, just use last value
					LastValueAccessed = NumPoints - 1;
					Result            = PriceValues[NumPoints];
				}
			}

		return Result;
	}

	private void calcMeanandStdDev() {
		if (NumPoints > 0)
			if (Interval > 0.0) {
				MathUtil.RCDMeanandStdDev(PriceValues, NumPoints, Mean, StdDev);
			} else {
				MathUtil.curveMeanAndStdDev(PriceValues, Hours, NumPoints, Mean, StdDev);
			}

		setPropertyValue(4, String.format("%.8g", Mean));
		setPropertyValue(5, String.format("%.8g", StdDev));

		StdDevCalculated = true;
	}

	public double getMean() {
		if (!StdDevCalculated) calcMeanandStdDev();
		return Mean;
	}

	public double getStdDev() {
		if (!StdDevCalculated) calcMeanandStdDev();
		return StdDev;
	}

	/**
	 * Get prices by index.
	 */
	public double getPrice(int i) {
		if ((i < NumPoints) && (i >= 0)) {
			LastValueAccessed = i;
			return PriceValues[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get hour corresponding to point index.
	 */
	public double getHour(int i) {
		if (Interval == 0) {
			if ((i < NumPoints) && (i >= 0)) {
				LastValueAccessed = i;
				return Hours[i];
			} else {
				return 0.0;
			}
		} else {
			LastValueAccessed = i;
			return Hours[i] * Interval;
		}
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 2:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
			case 3:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
			default:
				F.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));
			}
		}
	}

	@Override
	public String getPropertyValue(int Index) {
		String Result;
		switch (Index) {
		case 2:
			Result = "[";
		case 3:
			Result = "[";
		default:
			Result = "";
		}

		switch (Index) {
		case 1:
			Result = String.format("%.8g", Interval);
		case 2:
			for (int i = 0; i < NumPoints; i++)
				Result = Result + String.format("%-g, " , PriceValues[i]);
		case 3:
			if (Hours != null)
				for (int i = 0; i < NumPoints; i++)
					Result = Result + String.format("%-g, ", Hours[i]);
		case 4:
			Result = String.format("%.8g", Mean);
		case 5:
			Result = String.format("%.8g", StdDev);
		case 9:
			Result = String.format("%.8g", Interval * 3600.0);
		case 10:
			Result = String.format("%.8g", Interval * 60.0);
		default:
			Result = super.getPropertyValue(Index);
		}

		switch (Index) {
		case 2:
			Result = Result + "]";
		case 3:
			Result = Result + "]";
		}

		return Result;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

		setPropertyValue(0, "0");  // Number of points to expect
		setPropertyValue(1, "1");  // default = 1.0 hr;
		setPropertyValue(2, "");   // vector of multiplier values
		setPropertyValue(3, "");   // vextor of hour values
		setPropertyValue(4, "0");  // set the mean (otherwise computed)
		setPropertyValue(5, "0");  // set the std dev (otherwise computed)
		setPropertyValue(6, "");   // Switch input to a csvfile
		setPropertyValue(7, "");   // switch input to a binary file of singles
		setPropertyValue(8, "");   // switch input to a binary file of singles
		setPropertyValue(9, "3600");  // seconds
		setPropertyValue(10, "60");   // minutes
		setPropertyValue(11, "");  // action option .

		super.initPropertyValues(PriceShape.NumPropsThisClass);
	}

	// FIXME Private method in OpenDSS
	public void saveToDblFile() {
		throw new UnsupportedOperationException();
	}

	// FIXME Private method in OpenDSS
	public void saveToSngFile() {
		throw new UnsupportedOperationException();
	}

	public void setMean(double mean) {
		StdDevCalculated = true;
		Mean = mean;
	}

	public void setNumPoints(int numPoints) {
		setPropertyValue(0, String.valueOf(numPoints));  // Update property list variable

		// Reset array property values to keep them in propoer order in save

		if (ArrayPropertyIndex >= 0)
			setPropertyValue(ArrayPropertyIndex, getPropertyValue(ArrayPropertyIndex));

		NumPoints = numPoints;
	}

	public void setStdDev(double stdDev) {
		StdDevCalculated = true;
		StdDev = stdDev;
	}

	public int getNumPoints() {
		return 0;
	}

	public double getInterval() {
		return Interval;
	}

	public double[] getHours() {
		return Hours;
	}

	public void setHours(double[] hours) {
		Hours = hours;
	}

	public double[] getPriceValues() {
		return PriceValues;
	}

	public void setPriceValues(double[] priceValues) {
		PriceValues = priceValues;
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

	public boolean isStdDevCalculated() {
		return StdDevCalculated;
	}

	public void setStdDevCalculated(boolean stdDevCalculated) {
		StdDevCalculated = stdDevCalculated;
	}

	public void setInterval(double interval) {
		Interval = interval;
	}

}
