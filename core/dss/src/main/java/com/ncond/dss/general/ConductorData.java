package com.ncond.dss.general;

import lombok.Getter;
import lombok.Setter;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.LineUnits;

@Getter @Setter
abstract public class ConductorData extends DSSClass {

	public static final String LineUnitsHelp = "{mi|kft|km|m|Ft|in|cm|mm} Default=none.";

	public static ConductorDataObj activeConductorDataObj;

	private static ConductorChoice[] conductorChoiceArray = new ConductorChoice[100];

	private int numConductorClassProps;

	public ConductorData() {
		super();
		numConductorClassProps = 10;
		classType = DSSClassDefs.DSS_OBJECT;
	}

	@Override
	protected void countProperties() {
		numProperties = numProperties + getNumConductorClassProps();
		super.countProperties();
	}

	@Override
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

	@Override
	protected int classEdit(DSSObject activeObj, int paramPointer) {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			ConductorDataObj cd = (ConductorDataObj) activeObj;
			switch (paramPointer) {
			case 0:
				cd.setRdc(parser.makeDouble());
				break;
			case 1:
				cd.setR60(parser.makeDouble());
				break;
			case 2:
				cd.setResistanceUnits( LineUnits.interpretUnitsCode(parser.makeString()) );
				break;
			case 3:
				cd.setGmr60(parser.makeDouble());
				break;
			case 4:
				cd.setGmrUnits( LineUnits.interpretUnitsCode(parser.makeString()) );
				break;
			case 5:
				cd.setRadius(parser.makeDouble());
				break;
			case 6:
				cd.setRadiusUnits( LineUnits.interpretUnitsCode(parser.makeString()) );
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
					cd.setR60(1.02 * cd.getRdc());
				break;
			case 1:
				if (cd.getRdc() < 0.0)
					cd.setRdc(cd.getR60() / 1.02);
				break;
			case 3:
				if (cd.getRadius() < 0.0)
					cd.setRadius(cd.getGmr60() / 0.7788);
				break;
			case 4:
				if (cd.getRadiusUnits() == LineUnits.NONE)
					cd.setRadiusUnits(cd.getGmrUnits());
				break;
			case 5:
				if (cd.getGmr60() < 0.0)
					cd.setGmr60(0.7788 * cd.getRadius());
				break;
			case 6:
				if (cd.getGmrUnits() == LineUnits.NONE)
					cd.setGmrUnits(cd.getRadiusUnits());
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
				if (cd.getGmr60() < 0.0)
					cd.setGmr60(0.7788 * cd.getRadius());
				break;
			}
			/* Check for critical errors */
			switch (paramPointer) {
			case 3:
				if (cd.getRadius() == 0.0)
					DSS.doSimpleMsg("Error: Radius is specified as zero for ConductorData." + cd.getName(), 999);
				break;
			case 5:
				if (cd.getGmr60() == 0.0)
					DSS.doSimpleMsg("Error: GMR is specified as zero for ConductorData." + cd.getName(), 999);
				break;
			}
		}

		return result;
	}

	protected void classMakeLike(DSSObject otherObj) {

		ConductorDataObj otherConductorData = (ConductorDataObj) otherObj;
		ConductorDataObj cd = (ConductorDataObj) DSS.activeDSSObject;

		cd.setRdc(otherConductorData.getRdc());
		cd.setR60(otherConductorData.getR60());
		cd.setResistanceUnits(otherConductorData.getResistanceUnits());
		cd.setGmr60(otherConductorData.getGmr60());
		cd.setGmrUnits(otherConductorData.getGmrUnits());
		cd.setRadius(otherConductorData.getRadius());
		cd.setRadiusUnits(otherConductorData.getRadiusUnits());
		cd.setNormAmps(otherConductorData.getNormAmps());
		cd.setEmergAmps(otherConductorData.getEmergAmps());
		//super.classMakeLike(OtherObj);
	}

}
