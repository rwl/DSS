package com.ncond.dss.general.impl;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.general.Spectrum;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.shared.impl.ComplexUtil;

public class SpectrumObjImpl extends DSSObjectImpl implements SpectrumObj {

	private double[] puMagArray;
	private double[] angleArray;
	private Complex[] multArray;

	protected int numHarm;
	protected double[] harmArray;

	public SpectrumObjImpl(DSSClass parClass, String spectrumName) {
		super(parClass);
		setName(spectrumName.toLowerCase());
		objType = parClass.getDSSClassType();

		numHarm    = 0;
		harmArray  = null;
		puMagArray = null;
		angleArray = null;
		multArray  = null;

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
					pw.printf("%-g, ", harmArray[j]);
				pw.println(")");
				break;
			case 2:
				pw.print("~ " + parentClass.getPropertyName(i) + "=(");
				for (j = 0; j < numHarm; j++)
					pw.printf("%-g, ", puMagArray[j] * 100.0);
				pw.println(")");
				break;
			case 3:
				pw.print("~ " + parentClass.getPropertyName(i) + "=(");
				for (j = 0; j < numHarm; j++)
					pw.printf("%-g, ", angleArray[j]);
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
				pw.printf("%-g, ", harmArray[i]);
				pw.printf("%-g, %-g, ", multArray[i].getReal(), multArray[i].getImaginary());
				pw.printf("%-g, %-g", multArray[i].abs(), ComplexUtil.degArg( multArray[i] ));
				pw.println();
			}
		}

		pw.close();
	}

	@Override
	public Complex getMult(double h) {
		/* Search list for harmonic (nearest 0.01 harmonic) and return multiplier */
		for (int i = 0; i < numHarm; i++)
			if (Math.abs(h - harmArray[i]) < 0.01)
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
			result = "(";
			break;
		case 2:
			result = "(";
			break;
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
				result = result + String.format("%-g, ", harmArray[i]);
			break;
		case 2:
			for (i = 0; i < numHarm; i++)
				result = result + String.format("%-g, ", puMagArray[i] * 100.0);
			break;
		case 3:
			for (i = 0; i < numHarm; i++)
				result = result + String.format("%-g, ", angleArray[i]);
			break;
		default:
			result = super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 1:
			result = ")";
			break;
		case 2:
			result = ")";
			break;
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
	// FIXME Private method in OpenDSS
	@Override
	public void setMultArray() {
		int i;
		double fundAngle;

		try {
			fundAngle = 0.0;
			for (i = 0; i < numHarm; i++) {
				if (Math.round(harmArray[i]) == 1) {
					fundAngle = angleArray[i];
					break;
				}
			}

			multArray = Util.resizeArray(multArray, numHarm);
			for (i = 0; i < numHarm; i++)
				multArray[i] = ComplexUtil.polarDeg2Complex(puMagArray[i], (angleArray[i] - harmArray[i] * fundAngle));
		} catch (Exception e) {
			DSS.doSimpleMsg("Exception while computing spectrum."+getName()+". Check definition. Aborting", 655);
			if (DSS.inRedirect)
				DSS.redirectAbort = true;
		}
	}

	@Override
	public int getNumHarm() {
		return numHarm;
	}

	@Override
	public void setNumHarm(int num) {
		numHarm = num;
	}

	@Override
	public double[] getHarmArray() {
		return harmArray;
	}

	@Override
	public void setHarmArray(double[] array) {
		harmArray = array;
	}

	// FIXME Private members in OpenDSS

	@Override
	public double[] getPUMagArray() {
		return puMagArray;
	}

	@Override
	public void setPUMagArray(double[] array) {
		this.puMagArray = array;
	}

	@Override
	public double[] getAngleArray() {
		return angleArray;
	}

	@Override
	public void setAngleArray(double[] array) {
		angleArray = array;
	}

	@Override
	public Complex[] getMultArray() {
		return multArray;
	}

	@Override
	public void setMultArray(Complex[] array) {
		multArray = array;
	}

}
