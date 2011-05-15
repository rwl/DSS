package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.WireData;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.impl.LineUnits;

public class WireDataObjImpl extends ConductorDataObjImpl implements WireDataObj {

	public WireDataObjImpl(DSSClass ParClass, String WireDataName) {
		super(ParClass, WireDataName);
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
}
