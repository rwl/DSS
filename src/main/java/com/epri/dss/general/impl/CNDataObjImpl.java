package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.CNData;
import com.epri.dss.general.CNDataObj;

public class CNDataObjImpl extends CableDataObjImpl implements CNDataObj {

	private int kStrand;
	private double DiaStrand;
	private double GmrStrand;
	private double RStrand;

	public CNDataObjImpl(DSSClass ParClass, String CNDataName) {
		super(ParClass, CNDataName);
		setName(CNDataName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.kStrand   = 2;
		this.DiaStrand = -1.0;
		this.GmrStrand = -1.0;
		this.RStrand   = -1.0;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			F.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				F.println(String.format("%d", kStrand));
			case 1:
				F.println(String.format("%.6g", DiaStrand));
			case 2:
				F.println(String.format("%.6g", GmrStrand));
			case 3:
				F.println(String.format("%.6g", RStrand));
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		setPropertyValue(0, "2");
		setPropertyValue(1, "-1");
		setPropertyValue(2, "-1");
		setPropertyValue(3, "-1");
		super.initPropertyValues(ArrayOffset + CNData.NumPropsThisClass);
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

	// FIXME: Private members in OpenDSS

	public void setkStrand(int kStrand) {
		this.kStrand = kStrand;
	}

	public void setDiaStrand(double diaStrand) {
		DiaStrand = diaStrand;
	}

	public void setGmrStrand(double gmrStrand) {
		GmrStrand = gmrStrand;
	}

	public void setRStrand(double rStrand) {
		RStrand = rStrand;
	}

}
