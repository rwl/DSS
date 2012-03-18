/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSSClass;

@Getter @Setter
public class CNDataObj extends CableDataObj {

	private int kStrand;
	private double diaStrand;
	private double gmrStrand;
	private double rStrand;

	public CNDataObj(DSSClass parClass, String CNDataName) {
		super(parClass, CNDataName);
		setName(CNDataName.toLowerCase());
		objType = parClass.getClassType();

		kStrand = 2;
		diaStrand = -1.0;
		gmrStrand = -1.0;
		rStrand = -1.0;

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

}
