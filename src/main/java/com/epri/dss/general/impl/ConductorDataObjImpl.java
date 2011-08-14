package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.shared.impl.LineUnits;

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
		this.objType = parClass.getDSSClassType();

		this.Rdc             = -1.0;
		this.R60             = -1.0;
		this.gmr60           = -1.0;
		this.radius          = -1.0;
		this.gmrUnits        = 0;
		this.resistanceUnits = 0;
		this.radiusUnits     = 0;

		this.normAmps  = -1.0;
		this.emergAmps = -1.0;
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < getParentClass().getNumProperties(); i++) {
			f.print("~ " + getParentClass().getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				f.println(String.format("%.6g", getRDC()));
				break;
			case 1:
				f.println(String.format("%.6g", getR60()));
				break;
			case 2:
				f.println(String.format("%s", LineUnits.lineUnitsStr(getResistanceUnits())));
				break;
			case 3:
				f.println(String.format("%.6g", getGMR60()));
				break;
			case 4:
				f.println(String.format("%s", LineUnits.lineUnitsStr(getGMRUnits())));
				break;
			case 5:
				f.println(String.format("%.6g", getRadius()));
				break;
			case 6:
				f.println(String.format("%s", LineUnits.lineUnitsStr(getRadiusUnits())));
				break;
			case 7:
				f.println(String.format("%.6g", getNormAmps()));
				break;
			case 8:
				f.println(String.format("%.6g", getEmergAmps()));
				break;
			case 9:
				f.println(String.format("%.6g", getRadius() * 2.0));
				break;
			}
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		setPropertyValue(arrayOffset + 0, "-1");
		setPropertyValue(arrayOffset + 1, "-1");
		setPropertyValue(arrayOffset + 2, "none");
		setPropertyValue(arrayOffset + 3, "-1");
		setPropertyValue(arrayOffset + 4, "none");
		setPropertyValue(arrayOffset + 5, "-1");
		setPropertyValue(arrayOffset + 6, "none");
		setPropertyValue(arrayOffset + 7, "-1");
		setPropertyValue(arrayOffset + 8, "-1");
		setPropertyValue(arrayOffset + 9, "-1");
		super.initPropertyValues(arrayOffset + 10);
	}

	public double getNormAmps() {
		return normAmps;
	}

	public void setNormAmps(double amps) {
		normAmps = amps;
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public void setEmergAmps(double amps) {
		emergAmps = amps;
	}

	public double getRDC() {
		return Rdc;
	}

	public double getR60() {
		return R60;
	}

	public double getGMR60() {
		return gmr60;
	}

	public double getRadius() {
		return radius;
	}

	public int getGMRUnits() {
		return gmrUnits;
	}

	public int getResistanceUnits() {
		return resistanceUnits;
	}

	public int getRadiusUnits() {
		return radiusUnits;
	}

	// FIXME Private members in OpenDSS.

	public void setRDC(double rdc) {
		Rdc = rdc;
	}

	public void setR60(double r60) {
		R60 = r60;
	}

	public void setGMR60(double gmr) {
		gmr60 = gmr;
	}

	public void setRadius(double value) {
		radius = value;
	}

	public void setGMRUnits(int units) {
		gmrUnits = units;
	}

	public void setResistanceUnits(int units) {
		resistanceUnits = units;
	}

	public void setRadiusUnits(int units) {
		radiusUnits = units;
	}

}
