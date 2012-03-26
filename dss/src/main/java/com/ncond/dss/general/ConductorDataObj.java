/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.ncond.dss.common.DSSClass;
import com.ncond.dss.shared.LineUnits;

/**
 * The ConductorData object is a general DSS object used by all circuits
 * as a reference for obtaining line impedances.
 *
 * The values are set by the normal "new" and "edit" procedures for any DSS object.
 *
 * The values are retrieved by setting the code property in the ConductorData class.
 * This sets the active ConductorData object to be the one referenced by the code property;
 *
 * Then the values of that code can be retrieved via the public variables.
 *
 */
public class ConductorDataObj extends DSSObject {

	private double Rdc;
	private double R60;
	private double gmr60;
	private double radius;
	private LineUnits gmrUnits;
	private LineUnits resistanceUnits;
	private LineUnits radiusUnits;

	protected double normAmps;
	protected double emergAmps;

	public ConductorDataObj(DSSClass parClass, String conductorDataName) {
		super(parClass);

		setName(conductorDataName.toLowerCase());
		objType = parClass.getClassType();

		Rdc = -1.0;
		R60 = -1.0;
		gmr60 = -1.0;
		radius = -1.0;
		gmrUnits = LineUnits.NONE;
		resistanceUnits = LineUnits.NONE;
		radiusUnits = LineUnits.NONE;

		normAmps = -1.0;
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
				pw.printf("%.6g", getRdc());
				break;
			case 1:
				pw.printf("%.6g", getR60());
				break;
			case 2:
				pw.printf("%s", LineUnits.lineUnitsStr(getResistanceUnits()));
				break;
			case 3:
				pw.printf("%.6g", getGmr60());
				break;
			case 4:
				pw.printf("%s", LineUnits.lineUnitsStr(getGmrUnits()));
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

	public double getR60() {
		return R60;
	}

	public double getGmr60() {
		return gmr60;
	}

	public double getRadius() {
		return radius;
	}

	public LineUnits getGmrUnits() {
		return gmrUnits;
	}

	public double getNormAmps() {
		return normAmps;
	}

	public double getEmergAmps() {
		return emergAmps;
	}

	public double getRdc() {
		return Rdc;
	}

	public void setRdc(double rdc) {
		Rdc = rdc;
	}

	public LineUnits getResistanceUnits() {
		return resistanceUnits;
	}

	public void setResistanceUnits(LineUnits resistanceUnits) {
		this.resistanceUnits = resistanceUnits;
	}

	public LineUnits getRadiusUnits() {
		return radiusUnits;
	}

	public void setRadiusUnits(LineUnits radiusUnits) {
		this.radiusUnits = radiusUnits;
	}

	public void setR60(double r60) {
		R60 = r60;
	}

	public void setGmr60(double gmr60) {
		this.gmr60 = gmr60;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setGmrUnits(LineUnits gmrUnits) {
		this.gmrUnits = gmrUnits;
	}

	public void setNormAmps(double normAmps) {
		this.normAmps = normAmps;
	}

	public void setEmergAmps(double emergAmps) {
		this.emergAmps = emergAmps;
	}

}
