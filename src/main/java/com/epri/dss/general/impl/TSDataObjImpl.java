package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.TSData;
import com.epri.dss.general.TSDataObj;

public class TSDataObjImpl extends CableDataObjImpl implements TSDataObj {

	private double DiaShield;
	private double TapeLayer;
	private double TapeLap;

	public TSDataObjImpl(DSSClass ParClass, String TSDataName) {
		super(ParClass, TSDataName);

		setName(TSDataName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();
		this.DiaShield = -1.0;
		this.TapeLayer = -1.0;
		this.TapeLap = 20.0;

		initPropertyValues(0);
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			F.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				F.println(String.format("%.6g", DiaShield));
			case 1:
				F.println(String.format("%.6g", TapeLayer));
			case 2:
				F.println(String.format("%.2g", TapeLap));
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		setPropertyValue(0, "-1");
		setPropertyValue(1, "-1");
		setPropertyValue(2, "20.0");
		super.initPropertyValues(ArrayOffset + TSData.NumPropsThisClass);
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

	// FIXME Private members in OpenDSS

	public void setDiaShield(double diaShield) {
		DiaShield = diaShield;
	}

	public void setTapeLayer(double tapeLayer) {
		TapeLayer = tapeLayer;
	}

	public void setTapeLap(double tapeLap) {
		TapeLap = tapeLap;
	}

}
