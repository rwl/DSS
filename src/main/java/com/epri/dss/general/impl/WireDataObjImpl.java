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

		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			F.print("~ " + parentClass.getPropertyName()[i] + "=");
			switch (i) {
			case 0:
				F.println(String.format("%.6g", getRDC()));
				break;
			case 1:
				F.println(String.format("%.6g", getR60()));
				break;
			case 2:
				F.println(String.format("%s", LineUnits.lineUnitsStr(getResistanceUnits())));
				break;
			case 3:
				F.println(String.format("%.6g", getGMR60()));
				break;
			case 4:
				F.println(String.format("%s", LineUnits.lineUnitsStr(getGMRUnits())));
				break;
			case 5:
				F.println(String.format("%.6g", getRadius()));
				break;
			case 6:
				F.println(String.format("%s", LineUnits.lineUnitsStr(getRadiusUnits())));
				break;
			case 7:
				F.println(String.format("%.6g", getNormAmps()));
				break;
			case 8:
				F.println(String.format("%.6g", getEmergAmps()));
				break;
			case 9:
				F.println(String.format("%.6g", getRadius() * 2.0));
				break;
			}
		}
	}

	@Override
	public void initPropertyValues(int ArrayOffset) {
		propertyValue[1] = "-1";
		propertyValue[2] =  "-1";
		propertyValue[3] =  "none";
		propertyValue[4] =  "-1";
		propertyValue[5] =  "none";
		propertyValue[6] =  "-1";
		propertyValue[7] =  "none";
		propertyValue[8] =  "-1";
		propertyValue[9] =  "-1";
		propertyValue[10] =  "-1";

		super.initPropertyValues(WireData.NumPropsThisClass);
	}
}
