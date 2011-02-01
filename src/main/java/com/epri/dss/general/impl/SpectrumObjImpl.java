package com.epri.dss.general.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.SpectrumObj;

public class SpectrumObjImpl extends DSSObjectImpl implements SpectrumObj {

	private double[] puMagArray;
	private double[] AngleArray;
	private Complex[] MultArray;

	protected int NumHarm;
	protected double[] HarmArray;

	public SpectrumObjImpl(DSSClass ParClass, String SpectrumName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
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

	private void setMultArray() {

	}

	public Complex getMult(double h) {
		return null;
	}

	public String getPropertyValue(int Index) {
		return null;
	}

	public void initPropertyValues(int ArrayOffset) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

}
