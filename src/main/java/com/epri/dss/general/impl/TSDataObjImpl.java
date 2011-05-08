package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TSDataObj;

public class TSDataObjImpl extends CableDataObjImpl implements TSDataObj {

	private double DiaShield;
	private double TapeLayer;
	private double TapeLap;

	public TSDataObjImpl(DSSClass ParClass, String TSDataName) {
		super(ParClass, TSDataName);
	}

	public double getDiaShield() {
		return DiaShield;
	}

	public double getTapeLayer() {
		return TapeLayer;
	}

	public double getTapeLap() {
		return TapeLap;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

}
