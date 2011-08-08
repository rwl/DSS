package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.CableDataObj;

public class CableDataObjImpl extends ConductorDataObjImpl implements CableDataObj {

	private double EpsR;
	// next 3 use parent RadiusUnits
	private double InsLayer;
	private double DiaIns;
	private double DiaCable;

	public CableDataObjImpl(DSSClass ParClass, String CableDataName) {
		super(ParClass, CableDataName);

		setName(CableDataName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.EpsR     =  2.3;
		this.InsLayer = -1.0;
		this.DiaIns   = -1.0;
		this.DiaCable = -1.0;
	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			F.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				F.println(String.format("%.3g", EpsR));
				break;
			case 1:
				F.println(String.format("%.6g", InsLayer));
				break;
			case 2:
				F.println(String.format("%.6g", DiaIns));
				break;
			case 3:
				F.println(String.format("%.6g", DiaCable));
				break;
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		setPropertyValue(ArrayOffset + 0, "2.3");
		setPropertyValue(ArrayOffset + 1, "-1");
		setPropertyValue(ArrayOffset + 2, "-1");
		setPropertyValue(ArrayOffset + 3, "-1");
		super.initPropertyValues(ArrayOffset + 4);
	}

	public double getEpsR() {
		return EpsR;
	}

	public double getInsLayer() {
		return InsLayer;
	}

	public double getDiaIns() {
		return DiaIns;
	}

	public double getDiaCable() {
		return DiaCable;
	}

	// FIXME Private members in OpenDSS.

	public void setEpsR(double epsR) {
		EpsR = epsR;
	}

	public void setInsLayer(double insLayer) {
		InsLayer = insLayer;
	}

	public void setDiaIns(double diaIns) {
		DiaIns = diaIns;
	}

	public void setDiaCable(double diaCable) {
		DiaCable = diaCable;
	}

}
