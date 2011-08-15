package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.ConductorData;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.DSSObject;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.LineUnits;

public class ConductorDataImpl extends DSSClassImpl implements ConductorData {

	public static ConductorDataObj activeConductorDataObj;

	private static ConductorChoice[] conductorChoiceArray = new ConductorChoice[100];

	private int numConductorClassProps;

	public ConductorDataImpl() {
		super();
		numConductorClassProps = 10;
		classType = DSSClassDefs.DSS_OBJECT;
	}

	protected void countProperties() {
		numProperties = numProperties + getNumConductorClassProps();
		super.countProperties();
	}

	protected void defineProperties() {
		propertyName[activeProperty + 1] = "Rdc";
		propertyName[activeProperty + 2] = "Rac";
		propertyName[activeProperty + 3] = "Runits";
		propertyName[activeProperty + 4] = "GMRac";
		propertyName[activeProperty + 5] = "GMRunits";
		propertyName[activeProperty + 6] = "radius";
		propertyName[activeProperty + 7] = "radunits";
		propertyName[activeProperty + 8] = "normamps";
		propertyName[activeProperty + 9] = "emergamps";
		propertyName[activeProperty + 10] = "diam";

		propertyHelp[activeProperty + 1] = "dc Resistance, ohms per unit length (see Runits). Defaults to Rac/1.02 if not specified.";
		propertyHelp[activeProperty + 2] = "Resistance at 60 Hz per unit length. Defaults to 1.02*Rdc if not specified.";
		propertyHelp[activeProperty + 3] = "Length units for resistance: ohms per " + LineUnitsHelp;
		propertyHelp[activeProperty + 4] = "GMR at 60 Hz. Defaults to .7788*radius if not specified.";
		propertyHelp[activeProperty + 5] = "Units for GMR: " + LineUnitsHelp;
		propertyHelp[activeProperty + 6] = "Outside radius of conductor. Defaults to GMR/0.7788 if not specified.";
		propertyHelp[activeProperty + 7] = "Units for outside radius: " + LineUnitsHelp;
		propertyHelp[activeProperty + 8] = "Normal ampacity, amperes. Defaults to Emergency amps/1.5 if not specified.";
		propertyHelp[activeProperty + 9] = "Emergency ampacity, amperes. Defaults to 1.5 * Normal Amps if not specified.";
		propertyHelp[activeProperty + 10] = "Diameter; Alternative method for entering radius.";

		activeProperty = activeProperty + numConductorClassProps;
		super.defineProperties();
	}

	protected int classEdit(DSSObject activeObj, int paramPointer) {
		DSSGlobals globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			ConductorDataObj cd = (ConductorDataObj) activeObj;
			switch (paramPointer) {
			case 0:
				cd.setRDC(parser.makeDouble());
				break;
			case 1:
				cd.setR60(parser.makeDouble());
				break;
			case 2:
				cd.setResistanceUnits( LineUnits.getUnitsCode(parser.makeString()) );
				break;
			case 3:
				cd.setGMR60(parser.makeDouble());
				break;
			case 4:
				cd.setGMRUnits( LineUnits.getUnitsCode(parser.makeString()) );
				break;
			case 5:
				cd.setRadius(parser.makeDouble());
				break;
			case 6:
				cd.setRadiusUnits( LineUnits.getUnitsCode(parser.makeString()) );
				break;
			case 7:
				cd.setNormAmps(parser.makeDouble());
				break;
			case 8:
				cd.setEmergAmps(parser.makeDouble());
				break;
			case 9:
				cd.setRadius(parser.makeDouble() / 2.0);
				break;
			default:
				super.classEdit(activeObj, paramPointer - numConductorClassProps);
				break;
			}
			/* Set defaults */
			switch (paramPointer) {
			case 0:
				if (cd.getR60() < 0.0)
					cd.setR60(1.02 * cd.getRDC());
				break;
			case 1:
				if (cd.getRDC() < 0.0)
					cd.setRDC(cd.getR60() / 1.02);
				break;
			case 3:
				if (cd.getRadius() < 0.0)
					cd.setRadius(cd.getGMR60() / 0.7788);
				break;
			case 4:
				if (cd.getRadiusUnits() == 0)
					cd.setRadiusUnits(cd.getGMRUnits());
				break;
			case 5:
				if (cd.getGMR60() < 0.0)
					cd.setGMR60(0.7788 * cd.getRadius());
				break;
			case 6:
				if (cd.getGMRUnits() == 0)
					cd.setGMRUnits(cd.getRadiusUnits());
				break;
			case 7:
				if (cd.getEmergAmps() < 0.0)
					cd.setEmergAmps(1.5 * cd.getNormAmps());
				break;
			case 8:
				if (cd.getNormAmps() < 0.0)
					cd.setNormAmps(cd.getEmergAmps() / 1.5);
				break;
			case 9:
				if (cd.getGMR60() < 0.0)
					cd.setGMR60(0.7788 * cd.getRadius());
				break;
			}
			/* Check for critical errors */
			switch (paramPointer) {
			case 3:
				if (cd.getRadius() == 0.0)
					globals.doSimpleMsg("Error: Radius is specified as zero for ConductorData." + cd.getName(), 999);
				break;
			case 5:
				if (cd.getGMR60() == 0.0)
					globals.doSimpleMsg("Error: GMR is specified as zero for ConductorData." + cd.getName(), 999);
				break;
			}
		}

		return result;
	}

	protected void classMakeLike(DSSObject otherObj) {
		DSSGlobals globals = DSSGlobals.getInstance();

		ConductorDataObj otherConductorData = (ConductorDataObj) otherObj;
		ConductorDataObj cd = (ConductorDataObj) globals.getActiveDSSObject();

		cd.setRDC(otherConductorData.getRDC());
		cd.setR60(otherConductorData.getR60());
		cd.setResistanceUnits(otherConductorData.getResistanceUnits());
		cd.setGMR60(otherConductorData.getGMR60());
		cd.setGMRUnits(otherConductorData.getGMRUnits());
		cd.setRadius(otherConductorData.getRadius());
		cd.setRadiusUnits(otherConductorData.getRadiusUnits());
		cd.setNormAmps(otherConductorData.getNormAmps());
		cd.setEmergAmps(otherConductorData.getEmergAmps());
		//super.classMakeLike(OtherObj);
	}

	public void setNumConductorClassProps(int num) {
		numConductorClassProps = num;
	}

	public int getNumConductorClassProps() {
		return numConductorClassProps;
	}

}
