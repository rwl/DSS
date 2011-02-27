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
		this.DSSObjType = ParClass.getDSSClassType();

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

		for (i = 0; i < ParentClass.getNumProperties(); i++) {
			switch (i) {
			case 1:  // TODO Check zero based indexing
				F.print("~ " + ParentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < NumHarm; j++) 
					F.printf("%-g, ", HarmArray[j]);
				F.println(")");
			case 2:
				F.print("~ " + ParentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < NumHarm; j++) 
					F.printf("%-g, ", puMagArray[j] * 100.0);
				F.println(")");
			case 3:
				F.print("~ " + ParentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < NumHarm; j++) 
					F.printf("%-g, ", AngleArray[j]);
				F.println(")");
			default:
				F.println("~ " + ParentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
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
		case 2:
			Result = "(";
		case 3:
			Result = "(";
		default:
			Result = "";
		}
		
		switch (Index) {
		case 0:
			Result = String.valueOf(NumHarm);
		case 1:
			for (i = 0; i < NumHarm; i++) 
				Result = Result + String.format("%-g, ", HarmArray[i]);
		case 2:
			for (i = 0; i < NumHarm; i++)
				Result = Result + String.format("%-g, ", puMagArray[i] * 100.0);
		case 3:
			for (i = 0; i < NumHarm; i++)
				Result = Result + String.format("%-g, ", AngleArray[i]);
		default:
			Result = super.getPropertyValue(Index);
		}

		switch (Index) {
		case 1:
			Result = ")";
		case 2:
			Result = ")";
		case 3:
			Result = ")";
		}
		
		return Result;
	}

	public void initPropertyValues(int ArrayOffset) {

		PropertyValue[0] = "0";
		PropertyValue[1] =  "";
		PropertyValue[2] =  "";
		PropertyValue[3] =  "";
		PropertyValue[4] =  "";

		super.initPropertyValues(Spectrum.NumPropsThisClass);
	}

	/**
	 * Rotate all phase angles so that the fundamental is at zero.
	 */
	private void setMultArray() {
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
			if (Globals.isIn_Redirect())
				Globals.setRedirect_Abort(true);
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

	public double[] getPuMagArray() {
		return puMagArray;
	}

	public void setPuMagArray(double[] puMagArray) {
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
