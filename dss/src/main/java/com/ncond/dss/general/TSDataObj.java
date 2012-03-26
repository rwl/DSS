/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;

public class TSDataObj extends CableDataObj {

	private double diaShield;
	private double tapeLayer;
	private double tapeLap;

	public TSDataObj(DSSClass parClass, String TSDataName) {
		super(parClass, TSDataName);

		setName(TSDataName.toLowerCase());
		objType = parClass.getClassType();
		diaShield = -1.0;
		tapeLayer = -1.0;
		tapeLap = 20.0;

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
				pw.printf("%.6g", diaShield);
				break;
			case 1:
				pw.printf("%.6g", tapeLayer);
				break;
			case 2:
				pw.printf("%.2g", tapeLap);
				break;
			}
			pw.println();
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(0, "-1");
		setPropertyValue(1, "-1");
		setPropertyValue(2, "20.0");
		super.initPropertyValues(arrayOffset + TSData.NumPropsThisClass);
	}

	public double getDiaShield() {
		return diaShield;
	}

	public void setDiaShield(double diaShield) {
		this.diaShield = diaShield;
	}

	public double getTapeLayer() {
		return tapeLayer;
	}

	public void setTapeLayer(double tapeLayer) {
		this.tapeLayer = tapeLayer;
	}

	public double getTapeLap() {
		return tapeLap;
	}

	public void setTapeLap(double tapeLap) {
		this.tapeLap = tapeLap;
	}

}
