package com.ncond.dss.general.impl;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.general.CNData;
import com.ncond.dss.general.CNDataObj;

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
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			pw.print("~ " + getParentClass().getPropertyName(i) + "=");
			switch (i) {
			case 0:
				pw.printf("%d", kStrand);
				break;
			case 1:
				pw.printf("%.6g", diaStrand);
				break;
			case 2:
				pw.printf("%.6g", gmrStrand);
				break;
			case 3:
				pw.printf("%.6g", rStrand);
				break;
			}
			pw.println();
		}
		pw.close();
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "2");
		setPropertyValue(1, "-1");
		setPropertyValue(2, "-1");
		setPropertyValue(3, "-1");
		super.initPropertyValues(arrayOffset + CNData.NumPropsThisClass);
	}

	@Override
	public int getkStrand() {
		return kStrand;
	}

	@Override
	public double getDiaStrand() {
		return diaStrand;
	}

	@Override
	public double getGmrStrand() {
		return gmrStrand;
	}

	@Override
	public double getRStrand() {
		return rStrand;
	}

	// FIXME: Private members in OpenDSS

	@Override
	public void setkStrand(int kstrand) {
		this.kStrand = kstrand;
	}

	@Override
	public void setDiaStrand(double diastrand) {
		diaStrand = diastrand;
	}

	@Override
	public void setGmrStrand(double gmrstrand) {
		gmrStrand = gmrstrand;
	}

	@Override
	public void setRStrand(double rstrand) {
		rStrand = rstrand;
	}

}
