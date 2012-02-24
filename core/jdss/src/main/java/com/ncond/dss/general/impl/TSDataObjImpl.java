package com.ncond.dss.general.impl;

import java.io.PrintStream;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.general.TSData;
import com.ncond.dss.general.TSDataObj;

public class TSDataObjImpl extends CableDataObjImpl implements TSDataObj {

	private double diaShield;
	private double tapeLayer;
	private double tapeLap;

	public TSDataObjImpl(DSSClass parClass, String TSDataName) {
		super(parClass, TSDataName);

		setName(TSDataName.toLowerCase());
		objType = parClass.getDSSClassType();
		diaShield = -1.0;
		tapeLayer = -1.0;
		tapeLap = 20.0;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			f.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				f.println(String.format("%.6g", diaShield));
				break;
			case 1:
				f.println(String.format("%.6g", tapeLayer));
				break;
			case 2:
				f.println(String.format("%.2g", tapeLap));
				break;
			}
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

	public double getTapeLayer() {
		return tapeLayer;
	}

	public double getTapeLap() {
		return tapeLap;
	}

	// FIXME Private members in OpenDSS

	public void setDiaShield(double shield) {
		diaShield = shield;
	}

	public void setTapeLayer(double layer) {
		tapeLayer = layer;
	}

	public void setTapeLap(double lap) {
		tapeLap = lap;
	}

}
