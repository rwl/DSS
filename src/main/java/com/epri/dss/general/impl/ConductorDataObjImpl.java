package com.epri.dss.general.impl;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.ConductorDataObj;

public class ConductorDataObjImpl extends DSSObjectImpl implements ConductorDataObj {

	private double RDC;
	private double R60;
	private double GMR60;
	private double radius;
	private double GMRUnits;
	private double ResistanceUnits;
	private double RadiusUnits;

	protected double NormAmps;
	protected double EmergAmps;

	public ConductorDataObjImpl(DSSClass ParClass, String ConductorDataName) {
		super(ParClass);
	}

	public double getNormAmps() {
		return NormAmps;
	}

	public void setNormAmps(double normAmps) {
		NormAmps = normAmps;
	}

	public double getEmergAmps() {
		return EmergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		EmergAmps = emergAmps;
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	public double getRDC() {
		return RDC;
	}

	public double getR60() {
		return R60;
	}

	public double getGMR60() {
		return GMR60;
	}

	public double getRadius() {
		return radius;
	}

	public double getGMRUnits() {
		return GMRUnits;
	}

	public double getResistanceUnits() {
		return ResistanceUnits;
	}

	public double getRadiusUnits() {
		return RadiusUnits;
	}

}
