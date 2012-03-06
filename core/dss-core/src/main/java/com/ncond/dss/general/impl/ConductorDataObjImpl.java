package com.ncond.dss.general.impl;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.general.ConductorDataObj;
import com.ncond.dss.shared.impl.LineUnits;

public class ConductorDataObjImpl extends DSSObjectImpl implements ConductorDataObj {

	private double Rdc;
	private double R60;
	private double gmr60;
	private double radius;
	private int gmrUnits;
	private int resistanceUnits;
	private int radiusUnits;

	protected double normAmps;
	protected double emergAmps;

	public ConductorDataObjImpl(DSSClass parClass, String conductorDataName) {
		super(parClass);

		setName(conductorDataName.toLowerCase());
		objType = parClass.getDSSClassType();

		Rdc             = -1.0;
		R60             = -1.0;
		gmr60           = -1.0;
		radius          = -1.0;
		gmrUnits        = 0;
		resistanceUnits = 0;
		radiusUnits     = 0;

		normAmps  = -1.0;
		emergAmps = -1.0;
	}

	@Override
	public void dumpProperties(OutputStream out, boolean complete) {
		super.dumpProperties(out, complete);

		PrintWriter pw = new PrintWriter(out);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			pw.print("~ " + getParentClass().getPropertyName(i) + "=");
			switch (i) {
			case 0:
				pw.printf("%.6g", getRDC());
				break;
			case 1:
				pw.printf("%.6g", getR60());
				break;
			case 2:
				pw.printf("%s", LineUnits.lineUnitsStr(getResistanceUnits()));
				break;
			case 3:
				pw.printf("%.6g", getGMR60());
				break;
			case 4:
				pw.printf("%s", LineUnits.lineUnitsStr(getGMRUnits()));
				break;
			case 5:
				pw.printf("%.6g", getRadius());
				break;
			case 6:
				pw.printf("%s", LineUnits.lineUnitsStr(getRadiusUnits()));
				break;
			case 7:
				pw.printf("%.6g", getNormAmps());
				break;
			case 8:
				pw.printf("%.6g", getEmergAmps());
				break;
			case 9:
				pw.printf("%.6g", getRadius() * 2.0);
				break;
			}
			pw.println();
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 1, "-1");
		setPropertyValue(arrayOffset + 2, "-1");
		setPropertyValue(arrayOffset + 3, "none");
		setPropertyValue(arrayOffset + 4, "-1");
		setPropertyValue(arrayOffset + 5, "none");
		setPropertyValue(arrayOffset + 6, "-1");
		setPropertyValue(arrayOffset + 7, "none");
		setPropertyValue(arrayOffset + 8, "-1");
		setPropertyValue(arrayOffset + 9, "-1");
		setPropertyValue(arrayOffset + 10, "-1");
		super.initPropertyValues(arrayOffset + 10);
	}

	@Override
	public double getNormAmps() {
		return normAmps;
	}

	@Override
	public void setNormAmps(double amps) {
		normAmps = amps;
	}

	@Override
	public double getEmergAmps() {
		return emergAmps;
	}

	@Override
	public void setEmergAmps(double amps) {
		emergAmps = amps;
	}

	@Override
	public double getRDC() {
		return Rdc;
	}

	@Override
	public double getR60() {
		return R60;
	}

	@Override
	public double getGMR60() {
		return gmr60;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public int getGMRUnits() {
		return gmrUnits;
	}

	@Override
	public int getResistanceUnits() {
		return resistanceUnits;
	}

	@Override
	public int getRadiusUnits() {
		return radiusUnits;
	}

	// FIXME Private members in OpenDSS.

	@Override
	public void setRDC(double rdc) {
		Rdc = rdc;
	}

	@Override
	public void setR60(double r60) {
		R60 = r60;
	}

	@Override
	public void setGMR60(double gmr) {
		gmr60 = gmr;
	}

	@Override
	public void setRadius(double value) {
		radius = value;
	}

	@Override
	public void setGMRUnits(int units) {
		gmrUnits = units;
	}

	@Override
	public void setResistanceUnits(int units) {
		resistanceUnits = units;
	}

	@Override
	public void setRadiusUnits(int units) {
		radiusUnits = units;
	}

}
