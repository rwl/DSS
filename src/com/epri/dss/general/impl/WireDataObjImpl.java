package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.WireDataObj;

public class WireDataObjImpl extends DSSObjectImpl implements WireDataObj {
	
	private double RDC;
	private double R60;
	private double GMR60;
	private double radius;
	private int GMRUnits;
	private int ResistanceUnits;
	private int RadiusUnits;
	
	protected double NormAmps;
	protected double EmergAmps;

	public WireDataObjImpl(DSSClass ParClass, String WireDataName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
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

	public int getGMRUnits() {
		return GMRUnits;
	}

	public int getResistanceUnits() {
		return ResistanceUnits;
	}

	public int getRadiusUnits() {
		return RadiusUnits;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}

}
