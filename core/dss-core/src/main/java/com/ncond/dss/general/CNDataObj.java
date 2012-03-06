package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;

public class CNDataObj extends CableDataObj {

	private int kStrand;
	private double diaStrand;
	private double gmrStrand;
	private double rStrand;

	public CNDataObj(DSSClass parClass, String CNDataName) {
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
