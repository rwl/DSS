package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;
import com.epri.dss.shared.impl.ComplexUtil;

import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.general.Spectrum;
import com.epri.dss.general.SpectrumObj;

public class SpectrumObjImpl extends DSSObjectImpl implements SpectrumObj {

	private double[] puMagArray;
	private double[] AngleArray;
	private Complex[] MultArray;

	protected int NumHarm;
	protected double[] HarmArray;

	public SpectrumObjImpl(DSSClass ParClass, String SpectrumName) {
		super(ParClass);
		setName(SpectrumName.toLowerCase());
		this.objType = ParClass.getDSSClassType();

		this.NumHarm    = 0;
		this.HarmArray  = null;
		this.puMagArray = null;
		this.AngleArray = null;
		this.MultArray  = null;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		int i, j;

		super.dumpProperties(F, Complete);

		for (i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 1:  // TODO Check zero based indexing
				F.print("~ " + parentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < NumHarm; j++)
					F.printf("%-g, ", HarmArray[j]);
				F.println(")");
				break;
			case 2:
				F.print("~ " + parentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < NumHarm; j++)
					F.printf("%-g, ", puMagArray[j] * 100.0);
				F.println(")");
				break;
			case 3:
				F.print("~ " + parentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < NumHarm; j++)
					F.printf("%-g, ", AngleArray[j]);
				F.println(")");
				break;
			default:
				F.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}

		if (Complete) {
			F.println("Multiplier Array:");
			F.println("Harmonic, Mult.re, Mult.im, Mag,  Angle");
			for (i = 0; i < NumHarm; i++) {
				F.printf("%-g, ", HarmArray[i]);
				F.printf("%-g, %-g, ", MultArray[i].getReal(), MultArray[i].getImaginary());
				F.printf("%-g, %-g", MultArray[i].abs(), MultArray[i].degArg());
				F.println();
			}
		}
	}

	public Complex getMult(double h) {
		/* Search list for harmonic (nearest 0.01 harmonic) and return multiplier */
		for (int i = 0; i < NumHarm; i++)
			if (Math.abs(h - HarmArray[i]) < 0.01)
				return MultArray[i];

		/* None found, return zero */
		return Complex.ZERO;
	}

	public String getPropertyValue(int Index) {
		int i;
		String Result;

		switch (Index) {
		case 1:  // TODO Check zero based indexing
			Result = "(";
			break;
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
		case 0:
			Result = String.valueOf(NumHarm);
			break;
		case 1:
			for (i = 0; i < NumHarm; i++)
				Result = Result + String.format("%-g, ", HarmArray[i]);
			break;
		case 2:
			for (i = 0; i < NumHarm; i++)
				Result = Result + String.format("%-g, ", puMagArray[i] * 100.0);
			break;
		case 3:
			for (i = 0; i < NumHarm; i++)
				Result = Result + String.format("%-g, ", AngleArray[i]);
			break;
		default:
			Result = super.getPropertyValue(Index);
			break;
		}

		switch (Index) {
		case 1:
			Result = ")";
			break;
		case 2:
			Result = ")";
			break;
		case 3:
			Result = ")";
			break;
		}

		return Result;
	}

	public void initPropertyValues(int ArrayOffset) {

		propertyValue[0] = "0";
		propertyValue[1] =  "";
		propertyValue[2] =  "";
		propertyValue[3] =  "";
		propertyValue[4] =  "";

		super.initPropertyValues(Spectrum.NumPropsThisClass);
	}

	/**
	 * Rotate all phase angles so that the fundamental is at zero.
	 */
	// FIXME Private method in OpenDSS
	public void setMultArray() {
		int i;
		double FundAngle;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			FundAngle = 0.0;
			for (i = 0; i < NumHarm; i++) {
				if (Math.round(HarmArray[i]) == 1) {
					FundAngle = AngleArray[i];
					break;
				}
			}

			MultArray = (Complex[]) Utilities.resizeArray(MultArray, NumHarm);
			for (i = 0; i < NumHarm; i++)
				MultArray[i] = ComplexUtil.polarDeg2Complex(puMagArray[i], (AngleArray[i] - HarmArray[i] * FundAngle));
		} catch (Exception e) {
			Globals.doSimpleMsg("Exception while computing spectrum."+getName()+". Check Definition. Aborting", 655);
			if (Globals.isInRedirect())
				Globals.setRedirectAbort(true);
		}
	}

	public int getNumHarm() {
		return NumHarm;
	}

	public void setNumHarm(int numHarm) {
		NumHarm = numHarm;
	}

	public double[] getHarmArray() {
		return HarmArray;
	}

	public void setHarmArray(double[] harmArray) {
		HarmArray = harmArray;
	}

	// FIXME Private members in OpenDSS

	public double[] getPUMagArray() {
		return puMagArray;
	}

	public void setPUMagArray(double[] puMagArray) {
		this.puMagArray = puMagArray;
	}

	public double[] getAngleArray() {
		return AngleArray;
	}

	public void setAngleArray(double[] angleArray) {
		AngleArray = angleArray;
	}

	public Complex[] getMultArray() {
		return MultArray;
	}

	public void setMultArray(Complex[] multArray) {
		MultArray = multArray;
	}

}
