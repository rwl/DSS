package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.CNDataObj;

public class CNDataObjImpl extends CableDataObjImpl implements CNDataObj {

	private int kStrand;
	private double DiaStrand;
	private double GmrStrand;
	private double RStrand;

	public CNDataObjImpl(DSSClass ParClass, String CNDataName) {
		super(ParClass, CNDataName);
	}

	public int getkStrand() {
		return kStrand;
	}

	public double getDiaStrand() {
		return DiaStrand;
	}

	public double getGmrStrand() {
		return GmrStrand;
	}

	public double getRStrand() {
		return RStrand;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

}
