package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.CableDataObj;

public class CableDataObjImpl extends ConductorDataObjImpl implements CableDataObj {

	private double epsR;
	// next 3 use parent radiusUnits
	private double insLayer;
	private double diaIns;
	private double diaCable;

	public CableDataObjImpl(DSSClass parClass, String cableDataName) {
		super(parClass, cableDataName);

		setName(cableDataName.toLowerCase());
		objType = parClass.getDSSClassType();

		epsR     =  2.3;
		insLayer = -1.0;
		diaIns   = -1.0;
		diaCable = -1.0;
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			f.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				f.println(String.format("%.3g", epsR));
				break;
			case 1:
				f.println(String.format("%.6g", insLayer));
				break;
			case 2:
				f.println(String.format("%.6g", diaIns));
				break;
			case 3:
				f.println(String.format("%.6g", diaCable));
				break;
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 0, "2.3");  // TODO Check zero based indexing
		setPropertyValue(arrayOffset + 1, "-1");
		setPropertyValue(arrayOffset + 2, "-1");
		setPropertyValue(arrayOffset + 3, "-1");
		super.initPropertyValues(arrayOffset + 4);
	}

	public double getEpsR() {
		return epsR;
	}

	public double getInsLayer() {
		return insLayer;
	}

	public double getDiaIns() {
		return diaIns;
	}

	public double getDiaCable() {
		return diaCable;
	}

	// FIXME Private members in OpenDSS.

	public void setEpsR(double epsr) {
		epsR = epsr;
	}

	public void setInsLayer(double inslayer) {
		insLayer = inslayer;
	}

	public void setDiaIns(double diains) {
		diaIns = diains;
	}

	public void setDiaCable(double diacable) {
		diaCable = diacable;
	}

}
