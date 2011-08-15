package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.CNData;
import com.epri.dss.general.CNDataObj;

public class CNDataObjImpl extends CableDataObjImpl implements CNDataObj {

	private int kStrand;
	private double diaStrand;
	private double gmrStrand;
	private double rStrand;

	public CNDataObjImpl(DSSClass parClass, String CNDataName) {
		super(parClass, CNDataName);
		setName(CNDataName.toLowerCase());
		objType = parClass.getDSSClassType();

		kStrand   = 2;
		diaStrand = -1.0;
		gmrStrand = -1.0;
		rStrand   = -1.0;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			f.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				f.println(String.format("%d", kStrand));
				break;
			case 1:
				f.println(String.format("%.6g", diaStrand));
				break;
			case 2:
				f.println(String.format("%.6g", gmrStrand));
				break;
			case 3:
				f.println(String.format("%.6g", rStrand));
				break;
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "2");
		setPropertyValue(1, "-1");
		setPropertyValue(2, "-1");
		setPropertyValue(3, "-1");
		super.initPropertyValues(arrayOffset + CNData.NumPropsThisClass);
	}

	public int getkStrand() {
		return kStrand;
	}

	public double getDiaStrand() {
		return diaStrand;
	}

	public double getGmrStrand() {
		return gmrStrand;
	}

	public double getRStrand() {
		return rStrand;
	}

	// FIXME: Private members in OpenDSS

	public void setkStrand(int kstrand) {
		this.kStrand = kstrand;
	}

	public void setDiaStrand(double diastrand) {
		diaStrand = diastrand;
	}

	public void setGmrStrand(double gmrstrand) {
		gmrStrand = gmrstrand;
	}

	public void setRStrand(double rstrand) {
		rStrand = rstrand;
	}

}
