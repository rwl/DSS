package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.CableDataObj;

public class CableDataObjImpl extends ConductorDataObjImpl implements CableDataObj {

	private double EpsR;
	// next 3 use parent RadiusUnits
	private double InsLayer;
	private double DiaIns;
	private double DiaCable;

	public CableDataObjImpl(DSSClass ParClass, String CableDataName) {
		super(ParClass, CableDataName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	public double getEpsR() {
		return EpsR;
	}

	public double getInsLayer() {
		return InsLayer;
	}

	public double getDiaIns() {
		return DiaIns;
	}

	public double getDiaCable() {
		return DiaCable;
	}

}
