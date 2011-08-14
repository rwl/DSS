package com.epri.dss.general.impl;

import java.io.PrintStream;

import org.apache.commons.lang.mutable.MutableDouble;

import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.MathUtil;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.LoadShape;
import com.epri.dss.general.LoadShapeObj;

public class LoadShapeObjImpl extends DSSObjectImpl implements LoadShapeObj {

	private int LastValueAccessed;
	/** Number of points in curve */
	private int NumPoints;
	private int ArrayPropertyIndex;

	/** =0.0 then random interval (hr) */
	protected double Interval;
	/** Time values (hr) if Interval > 0.0 */
	protected double[] Hours;
	protected double[] PMultipliers, QMultipliers;

	protected double MaxP, MaxQ;

	protected boolean UseActual;

	protected int iMaxP;

	protected boolean StdDevCalculated;
	protected MutableDouble Mean = new MutableDouble();
	protected MutableDouble StdDev = new MutableDouble();

	public LoadShapeObjImpl(DSSClass ParClass, String LoadShapeName) {
		super(ParClass);
		setName(LoadShapeName.toLowerCase());
		this.objType = ParClass.getDSSClassType();

		this.LastValueAccessed = 1;

		this.NumPoints    = 0;
		this.Interval     = 1.0;  // hr
		this.Hours        = null;
		this.PMultipliers = null;
		this.QMultipliers = null;
		this.MaxP         = 1.0;
		this.MaxQ         = 0.0;
		this.UseActual    = false;
		this.StdDevCalculated = false;  // calculate on demand

		this.ArrayPropertyIndex = 0;

		initPropertyValues(0);
	}

	/** Set imaginary part of Result when Qmultipliers not defined. */
	private double setResultIm(double realpart) {
		// if actual, assume zero, same as real otherwise
		return UseActual ? 0.0 : realpart;
	}

	/**
	 * Get multiplier at specified time.
	 *
	 * This function returns a multiplier for the given hour.
	 * If no points exist in the curve, the result is 1.0.
	 * If there are fewer points than requested, the curve is simply assumed to repeat
	 * Thus a daily load curve can suffice for a yearly load curve:  You just get the
	 * same day over and over again.
	 * The value returned is the nearest to the interval requested.  Thus if you request
	 * hour=12.25 and the interval is 1.0, you will get interval 12.
	 */
	public Complex getMult(double hr) {
		int Index;

		//Complex Result = new Complex(1.0, 1.0);  // default return value if no points in curve
		double[] Result = new double[] {1.0, 1.0};

		if (NumPoints > 0) {  // handle exceptional cases
			if (NumPoints == 1) {
				Result[0] = PMultipliers[0];  // TODO Check zero based indexing
				if (QMultipliers != null) {
					Result[1] = QMultipliers[0];  // TODO Check zero based indexing
				} else {
					Result[1] = setResultIm(Result[0]);
				}
			} else {
				if (Interval > 0.0) {
					Index = (int) Math.round(hr / Interval);
					if (Index > NumPoints)
						Index = Index % NumPoints;  // wrap around using remainder
					if (Index == 0)
						Index = NumPoints;
					Result[0] = PMultipliers[Index];
					if (QMultipliers != null) {
						Result[1] = QMultipliers[Index];
					} else {
						Result[1] = setResultIm(Result[0]);
					}
				} else {
					// for random interval

					/* Start with previous value accessed under the assumption that most
					 * of the time, this function will be called sequentially
					 */

					/* Normalize Hr to max hour in curve to get wraparound */
					if (hr > Hours[NumPoints])
						hr = hr - (hr / Hours[NumPoints]) * Hours[NumPoints];

					if (Hours[LastValueAccessed] > hr)
						LastValueAccessed = 1;  // Start over from beginning
					for (int i = LastValueAccessed + 1; i < NumPoints; i++) {  // TODO Check zero based indexing
						if (Math.abs(Hours[i] - hr) < 0.00001) {  // if close to an actual point, just use it.
							Result[0] = PMultipliers[i];
							if (QMultipliers != null) {
								Result[1] = QMultipliers[i];
							} else {
								Result[1] = setResultIm(Result[0]);
							}
							LastValueAccessed = i;
							return new Complex(Result[0], Result[1]);
						} else if (Hours[i] > hr) {  // interpolate for multiplier
							LastValueAccessed = i - 1;  // TODO Check zero based indexing
							Result[0] = PMultipliers[LastValueAccessed] +
								(hr - Hours[LastValueAccessed]) / (Hours[i] - Hours[LastValueAccessed]) *
									(PMultipliers[i] -PMultipliers[LastValueAccessed]);
							if (QMultipliers != null) {
								Result[1] = QMultipliers[LastValueAccessed] +
									(hr - Hours[LastValueAccessed]) / (Hours[i] - Hours[LastValueAccessed]) *
									(QMultipliers[i] -QMultipliers[LastValueAccessed]);
							} else {
								Result[1] = setResultIm(Result[0]);
							}
							return new Complex(Result[0], Result[1]);
						}
					}

					// if we fall through the loop, just use last value
					LastValueAccessed = NumPoints - 1;
					Result[0] = PMultipliers[NumPoints];
					if (QMultipliers != null) {
						Result[1] = QMultipliers[NumPoints];
					} else {
						Result[1] = setResultIm(Result[0]);
					}
				}
			}
		}

		return new Complex(Result[0], Result[1]);
	}

	private double MaxMult;

	private void doNormalize(double[] Multipliers) {
		int i;
		if (NumPoints > 0) {
			MaxMult = Math.abs(Multipliers[0]);  // TODO Check zero based indexing
			for (i = 1; i < NumPoints; i++)  // TODO Check zero based indexing
				MaxMult = Math.max(MaxMult, Math.abs(Multipliers[i]));
			if (MaxMult == 0.0)
				MaxMult = 1.0;  // avoid divide by zero
			for (i = 0; i < NumPoints; i++)
				Multipliers[i] = Multipliers[i] / MaxMult;
		}
	}

	/**
	 * Normalize the curve presently in memory.
	 */
	public void normalize() {
		doNormalize(PMultipliers);
		if (QMultipliers != null)
			doNormalize(QMultipliers);
		UseActual = false;  // not likely that you would want to use the actual if you normalized it
	}

	public void calcMeanAndStdDev() {
		if (NumPoints > 0)
			if (Interval > 0.0) {
				MathUtil.RCDMeanandStdDev(PMultipliers, NumPoints, Mean, StdDev);
			} else {
				MathUtil.curveMeanAndStdDev(PMultipliers, Hours, NumPoints, Mean, StdDev);
			}

		// TODO Check indenting
		propertyValue[4] = String.format("%.8g", Mean.doubleValue());  // TODO Check zero based indexing.
		propertyValue[5] = String.format("%.8g", StdDev.doubleValue());

		StdDevCalculated = true;
		/* No action is taken on Q multipliers */
	}

	public double getInterval() {
		if (Interval > 0.0) {
			return Interval;
		} else {
			if (LastValueAccessed > 1) {  // TODO Check zero based indexing
				return Hours[LastValueAccessed] - Hours[LastValueAccessed - 1];
			} else {
				return 0.0;
			}
		}
	}

	public double getMean() {
		if (!StdDevCalculated)
			calcMeanAndStdDev();
		return Mean.doubleValue();
	}

	public double getStdDev() {
		if (!StdDevCalculated)
			calcMeanAndStdDev();
		return StdDev.doubleValue();
	}

	/**
	 * Get multiplier by index.
	 */
	public double mult(int i) {
		if ((i <= NumPoints) && (i > 0)) {
			LastValueAccessed = i;
			return PMultipliers[i];
		} else {
			return 0.0;
		}
	}

	/**
	 * Get hour corresponding to point index.
	 */
	public double hour(int i) {
		if (Interval == 0) {
			if ((i <= NumPoints) & (i > 0)) {
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

	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 2:  // TODO Check zero based indexing
				F.println("~ " + parentClass.getPropertyName()[i] + "=(" + propertyValue[i] + ")");
				break;
			case 3:
				F.println("~ " + parentClass.getPropertyName()[i] + "=(" + propertyValue[i] + ")");
				break;
			default:
				F.println("~ " + parentClass.getPropertyName()[i] + "=" + propertyValue[i]);
				break;
			}
		}
	}

	public String getPropertyValue(int Index) {
		String Result;

		switch (Index) {
		case 2:
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
		case 1:
			Result = String.format("%.8g", Interval);
			break;
		case 2:
			for (int i = 0; i < NumPoints; i++)
				Result = Result + String.format("%-g, ", PMultipliers[i]);
			break;
		case 3:
			if (Hours != null)
				for (int i = 0; i < NumPoints; i++)
					Result = Result + String.format("%-g, ", Hours[i]);
			break;
		case 4:
			Result = String.format("%.8g", Mean.doubleValue());
			break;
		case 5:
			Result = String.format("%.8g", StdDev.doubleValue());
			break;
		case 10:
			if (QMultipliers != null) {
				Result = "(";
				for (int i = 0; i < NumPoints; i++)
					Result = Result + String.format("%-g,", QMultipliers[i]);
				Result = Result + ")";
			}
			break;
		case 11:
			if (UseActual) {
				Result = "Yes";
			} else {
				Result = "No";
			}
			break;
		case 12:
			Result = String.format("%.8g", MaxP);
			break;
		case 13:
			Result = String.format("%.8g", MaxQ);
			break;
		case 14:
			Result = String.format("%.8g", Interval * 3600.0);
			break;
		case 15:
			Result = String.format("%.8g", Interval * 60.0);
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		switch (Index) {
		case 3:
			Result = Result + ")";
			break;
		case 4:
			Result = Result + ")";
			break;
		}

		return Result;
	}

	public void initPropertyValues(int ArrayOffset) {

		propertyValue[0] = "0";  // number of points to expect
		propertyValue[1] = "1";  // default = 1.0 hr;
		propertyValue[2] = "";   // vector of multiplier values
		propertyValue[3] = "";   // vector of hour values
		propertyValue[4] = "0";  // set the mean (otherwise computed)
		propertyValue[5] = "0";  // set the std dev (otherwise computed)
		propertyValue[6] = "";   // switch input to a csvfile
		propertyValue[7] = "";   // switch input to a binary file of singles
		propertyValue[8] = "";   // switch input to a binary file of singles
		propertyValue[9] = "";   // action option.
		propertyValue[10] = "";  // Qmult.
		propertyValue[11] = "No";
		propertyValue[12] = "0";
		propertyValue[13] = "0";
		propertyValue[14] = "3600";  // seconds
		propertyValue[15] = "60";    // minutes

		super.initPropertyValues(LoadShape.NumPropsThisClass);
	}

	// TODO Implement TOPExport method

	// FIXME Private method in OpenDSS
	public void saveToDblFile() {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	// FIXME Private method in OpenDSS
	public void saveToSngFile() {
		// FIXME Implement this method
		throw new UnsupportedOperationException();
	}

	// FIXME Private method in OpenDSS
	public void setMaxPandQ() {
		iMaxP = Utilities.iMaxAbsdblArrayValue(NumPoints, PMultipliers);

		if (iMaxP >= 0) {  // TODO Check zero based indexing
			MaxP = PMultipliers[iMaxP];
			if (QMultipliers != null) {
				MaxQ = QMultipliers[iMaxP];
			} else {
				MaxQ = 0.0;
			}
		}
	}

	public void setMean(double mean) {
		StdDevCalculated = true;
		Mean.setValue(mean);
	}

	public void setNumPoints(int Value) {

		propertyValue[0] = String.valueOf(Value);  // update property list variable

		// reset array property values to keep them in proper order in save

		if (ArrayPropertyIndex >= 0)  // TODO Check zero based indexing
			propertyValue[ArrayPropertyIndex] = propertyValue[ArrayPropertyIndex];
		if (QMultipliers != null)
			propertyValue[10] = propertyValue[10];  // TODO Check zero based indexing

		NumPoints = Value;  // now assign the value
	}

	public void setStdDev(double stdDev) {
		StdDevCalculated = true;
		StdDev.setValue(stdDev);
	}

	public int getNumPoints() {
		return NumPoints;
	}

	public double[] getHours() {
		return Hours;
	}

	public void setHours(double[] hours) {
		Hours = hours;
	}

	public double[] getPMultipliers() {
		return PMultipliers;
	}

	public void setPMultipliers(double[] pMultipliers) {
		PMultipliers = pMultipliers;
	}

	public double[] getQMultipliers() {
		return QMultipliers;
	}

	public void setQMultipliers(double[] qMultipliers) {
		QMultipliers = qMultipliers;
	}

	public void setInterval(double interval) {
		Interval = interval;
	}

	public double getMaxP() {
		return MaxP;
	}

	public void setMaxP(double maxP) {
		MaxP = maxP;
	}

	public double getMaxQ() {
		return MaxQ;
	}

	public void setMaxQ(double maxQ) {
		MaxQ = maxQ;
	}

	public boolean isUseActual() {
		return UseActual;
	}

	public void setUseActual(boolean useActual) {
		UseActual = useActual;
	}

	// Protected member in OpenDSS.

	public boolean isStdDevCalculated() {
		return StdDevCalculated;
	}

	public void setStdDevCalculated(boolean stdDevCalculated) {
		StdDevCalculated = stdDevCalculated;
	}

	// Private member in OpenDSS.
	public void setArrayPropertyIndex(int i) {
		ArrayPropertyIndex = i;
	}

}
