package com.epri.dss.general.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.SpectrumObj;

public class SpectrumObjImpl extends DSSObjectImpl implements SpectrumObj {

	private double[] puMagArray;
	private double[] AngleArray;
	private DComplexMatrix1D MultArray;

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

	public double[] getMult(double h) {
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
