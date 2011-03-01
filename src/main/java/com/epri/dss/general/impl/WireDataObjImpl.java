package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.WireData;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.impl.LineUnits;

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
		setName(WireDataName.toLowerCase());
		this.DSSObjType = ParClass.getDSSClassType();

		this.RDC              = -1.0;
		this.R60              = -1.0;
		this.GMR60            = -1.0;
		this.radius           = -1.0;
		this.GMRUnits         = 0;
		this.ResistanceUnits  = 0;
		this.RadiusUnits      = 0;

		this.NormAmps    = -1.0;
		this.EmergAmps   =-1.0;

		initPropertyValues(0);
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		super.dumpProperties(F, Complete);

		for (int i = 0; i < ParentClass.getNumProperties(); i++) {
			F.print("~ " + ParentClass.getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				F.println(String.format("%.6g", getRDC()));
			case 1:
				F.println(String.format("%.6g", getR60()));
			case 2:
				F.println(String.format("%s", LineUnits.lineUnitsStr(getResistanceUnits())));
			case 3:
				F.println(String.format("%.6g", getGMR60()));
			case 4:
				F.println(String.format("%s", LineUnits.lineUnitsStr(getGMRUnits())));
			case 5:
				F.println(String.format("%.6g", getRadius()));
			case 6:
				F.println(String.format("%s", LineUnits.lineUnitsStr(getRadiusUnits())));
			case 7:
				F.println(String.format("%.6g", getNormAmps()));
			case 8:
				F.println(String.format("%.6g", getEmergAmps()));
			case 9:
				F.println(String.format("%.6g", getRadius() * 2.0));
			}
		}
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		PropertyValue[1] = "-1";
		PropertyValue[2] =  "-1";
		PropertyValue[3] =  "none";
		PropertyValue[4] =  "-1";
		PropertyValue[5] =  "none";
		PropertyValue[6] =  "-1";
		PropertyValue[7] =  "none";
		PropertyValue[8] =  "-1";
		PropertyValue[9] =  "-1";
		PropertyValue[10] =  "-1";

		super.initPropertyValues(WireData.NumPropsThisClass);		
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
	
	// FIXME Private members in OpenDSS

	public void setRDC(double rDC) {
		RDC = rDC;
	}

	public void setR60(double r60) {
		R60 = r60;
	}

	public void setGMR60(double gMR60) {
		GMR60 = gMR60;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setGMRUnits(int gMRUnits) {
		GMRUnits = gMRUnits;
	}

	public void setResistanceUnits(int resistanceUnits) {
		ResistanceUnits = resistanceUnits;
	}

	public void setRadiusUnits(int radiusUnits) {
		RadiusUnits = radiusUnits;
	}

}
