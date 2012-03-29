/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;

import static com.ncond.dss.shared.ComplexUtil.degArg;
import static com.ncond.dss.shared.ComplexUtil.polarDeg2Complex;

import static com.ncond.dss.common.Util.resizeArray;

import static java.lang.Math.round;
import static java.lang.Math.abs;

import static java.lang.String.format;


/**
 * Harmonic spectrum specified as Harmonic, pct magnitude and angle.
 *
 * Spectrum is shifted by the fundamental angle and stored in MultArray
 * so that the fundamental is at zero degrees phase shift.
 */
public class SpectrumObj extends DSSObject {

	private double[] puMagArray;
	private double[] angleArray;
	private Complex[] multArray;

	protected int numHarm;
	protected double[] harmArray;

	public SpectrumObj(DSSClass parClass, String spectrumName) {
		super(parClass);
		setName(spectrumName.toLowerCase());
		objType = parClass.getClassType();

		numHarm = 0;
		harmArray = null;
		puMagArray = null;
		angleArray = null;
		multArray = null;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		int i, j;

		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 1:
				pw.print("~ " + parentClass.getPropertyName(i) + "=(");
				for (j = 0; j < numHarm; j++)
					pw.printf("%g, ", harmArray[j]);
				pw.println(")");
				break;
			case 2:
				pw.print("~ " + parentClass.getPropertyName(i) + "=(");
				for (j = 0; j < numHarm; j++)
					pw.printf("%g, ", puMagArray[j] * 100.0);
				pw.println(")");
				break;
			case 3:
				pw.print("~ " + parentClass.getPropertyName(i) + "=(");
				for (j = 0; j < numHarm; j++)
					pw.printf("%g, ", angleArray[j]);
				pw.println(")");
				break;
			default:
				pw.println("~ " + parentClass.getPropertyName(i) + "=" + getPropertyValue(i));
				break;
			}
		}

		if (complete) {
			pw.println("Multiplier Array:");
			pw.println("Harmonic, Mult.re, Mult.im, Mag,  Angle");
			for (i = 0; i < numHarm; i++) {
				pw.printf("%g, ", harmArray[i]);
				pw.printf("%g, %g, ", multArray[i].getReal(), multArray[i].getImaginary());
				pw.printf("%g, %g", multArray[i].abs(), degArg( multArray[i] ));
				pw.println();
			}
		}

		pw.close();
	}

	public Complex getMult(double h) {
		/* Search list for harmonic (nearest 0.01 harmonic) and return multiplier */
		for (int i = 0; i < numHarm; i++)
			if (abs(h - harmArray[i]) < 0.01)
				return multArray[i];

		/* None found, return zero */
		return Complex.ZERO;
	}

	@Override
	public String getPropertyValue(int index) {
		int i;
		String result;

		switch (index) {
		case 1:
		case 2:
		case 3:
			result = "(";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 0:
			result = String.valueOf(numHarm);
			break;
		case 1:
			for (i = 0; i < numHarm; i++)
				result = result + format("%g, ", harmArray[i]);
			break;
		case 2:
			for (i = 0; i < numHarm; i++)
				result = result + format("%g, ", puMagArray[i] * 100.0);
			break;
		case 3:
			for (i = 0; i < numHarm; i++)
				result = result + format("%g, ", angleArray[i]);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 1:
		case 2:
		case 3:
			result = ")";
			break;
		}

		return result;
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "0");
		setPropertyValue(1, "");
		setPropertyValue(2, "");
		setPropertyValue(3, "");
		setPropertyValue(4, "");

		super.initPropertyValues(Spectrum.NumPropsThisClass - 1);
	}

	/**
	 * Rotate all phase angles so that the fundamental is at zero.
	 */
	protected void setMultArray() {
		int i;
		double fundAngle;

		try {
			fundAngle = 0.0;
			for (i = 0; i < numHarm; i++) {
				if (round(harmArray[i]) == 1) {
					fundAngle = angleArray[i];
					break;
				}
			}

			multArray = resizeArray(multArray, numHarm);
			for (i = 0; i < numHarm; i++)
				multArray[i] = polarDeg2Complex(puMagArray[i], (angleArray[i] - harmArray[i] * fundAngle));
		} catch (Exception e) {
			DSS.doSimpleMsg("Exception while computing spectrum." + getName() + ". Check definition. Aborting", 655);
			if (DSS.inRedirect) DSS.redirectAbort = true;
		}
	}

	public double getHarmonic(int idx) {
		return harmArray[idx];
	}

	public double[] getPuMagArray() {
		return puMagArray;
	}

	public double[] getAngleArray() {
		return angleArray;
	}

	public int getNumHarm() {
		return numHarm;
	}

	public double[] getHarmArray() {
		return harmArray;
	}

	public void setPuMagArray(double[] puMagArray) {
		this.puMagArray = puMagArray;
	}

	public void setAngleArray(double[] angleArray) {
		this.angleArray = angleArray;
	}

	public void setHarmArray(double[] harmArray) {
		this.harmArray = harmArray;
	}

	public void setNumHarm(int numHarm) {
		this.numHarm = numHarm;
	}

}
