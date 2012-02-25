package com.ncond.dss.general.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.common.impl.Utilities;
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
	public void dumpProperties(PrintStream f, boolean complete) {
		int i, j;

		super.dumpProperties(f, complete);

		for (i = 0; i < parentClass.getNumProperties(); i++) {
			switch (i) {
			case 1:
				f.print("~ " + parentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < numHarm; j++)
					f.printf("%-g, ", harmArray[j]);
				f.println(")");
				break;
			case 2:
				f.print("~ " + parentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < numHarm; j++)
					f.printf("%-g, ", puMagArray[j] * 100.0);
				f.println(")");
				break;
			case 3:
				f.print("~ " + parentClass.getPropertyName()[i] + "=(");
				for (j = 0; j < numHarm; j++)
					f.printf("%-g, ", angleArray[j]);
				f.println(")");
				break;
			default:
				f.println("~ " + parentClass.getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}

		if (complete) {
			f.println("Multiplier Array:");
			f.println("Harmonic, Mult.re, Mult.im, Mag,  Angle");
			for (i = 0; i < numHarm; i++) {
				f.printf("%-g, ", harmArray[i]);
				f.printf("%-g, %-g, ", multArray[i].getReal(), multArray[i].getImaginary());
				f.printf("%-g, %-g", multArray[i].abs(), ComplexUtil.degArg( multArray[i] ));
				f.println();
			}
		}
	}

	public Complex getMult(double h) {
		/* Search list for harmonic (nearest 0.01 harmonic) and return multiplier */
		for (int i = 0; i < numHarm; i++)
			if (Math.abs(h - harmArray[i]) < 0.01)
				return multArray[i];

		/* None found, return zero */
		return Complex.ZERO;
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

			multArray = Utilities.resizeArray(multArray, numHarm);
			for (i = 0; i < numHarm; i++)
				multArray[i] = ComplexUtil.polarDeg2Complex(puMagArray[i], (angleArray[i] - harmArray[i] * fundAngle));
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Exception while computing spectrum."+getName()+". Check definition. Aborting", 655);
			if (DSSGlobals.inRedirect)
				DSSGlobals.redirectAbort = true;
		}
	}

	public int getNumHarm() {
		return numHarm;
	}

	public void setNumHarm(int num) {
		numHarm = num;
	}

	public double[] getHarmArray() {
		return harmArray;
	}

	public void setHarmArray(double[] array) {
		harmArray = array;
	}

	// FIXME Private members in OpenDSS

	public double[] getPUMagArray() {
		return puMagArray;
	}

	public void setPUMagArray(double[] array) {
		this.puMagArray = array;
	}

	public double[] getAngleArray() {
		return angleArray;
	}

	public void setAngleArray(double[] array) {
		angleArray = array;
	}

	public Complex[] getMultArray() {
		return multArray;
	}

	public void setMultArray(Complex[] array) {
		multArray = array;
	}

}
