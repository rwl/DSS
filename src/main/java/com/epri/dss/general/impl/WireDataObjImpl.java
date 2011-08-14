package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.WireData;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.shared.impl.LineUnits;

public class WireDataObjImpl extends ConductorDataObjImpl implements WireDataObj {

	public WireDataObjImpl(DSSClass parClass, String wireDataName) {
		super(parClass, wireDataName);
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		super.dumpProperties(f, complete);

		for (int i = 0; i < parentClass.getNumProperties(); i++) {
			f.print("~ " + parentClass.getPropertyName()[i] + "=");
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
