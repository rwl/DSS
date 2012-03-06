package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;

public class CableDataObj extends ConductorDataObj {

	private double epsR;
	// next 3 use parent radiusUnits
	private double insLayer;
	private double diaIns;
	private double diaCable;

	public CableDataObj(DSSClass parClass, String cableDataName) {
		super(parClass, cableDataName);

		setName(cableDataName.toLowerCase());
		objType = parClass.getDSSClassType();

		epsR     =  2.3;
		insLayer = -1.0;
		diaIns   = -1.0;
		diaCable = -1.0;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			pw.print("~ " + getParentClass().getPropertyName(i) + "=");
			switch (i) {
			case 0:
				pw.println(String.format("%.3g", epsR));
				break;
			case 1:
				pw.println(String.format("%.6g", insLayer));
				break;
			case 2:
				pw.println(String.format("%.6g", diaIns));
				break;
			case 3:
				pw.println(String.format("%.6g", diaCable));
				break;
			}
		}
		pw.close();
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 1, "2.3");
		setPropertyValue(arrayOffset + 2, "-1");
		setPropertyValue(arrayOffset + 3, "-1");
		setPropertyValue(arrayOffset + 4, "-1");
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
