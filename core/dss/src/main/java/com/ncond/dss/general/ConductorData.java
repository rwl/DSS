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

	public static final String LineUnitsHelp = "{mi|kft|km|m|Ft|in|cm|mm} default=none.";

	public static ConductorDataObj activeConductorDataObj;

//	private static ConductorChoice[] conductorChoiceArray = new ConductorChoice[100];

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
		ConductorDataObj elem;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		if (paramPointer >= 0) {
			elem = (ConductorDataObj) activeObj;

			switch (paramPointer) {
			case 0:
				elem.setRdc(parser.makeDouble());
				break;
			case 1:
				elem.setR60(parser.makeDouble());
				break;
			case 2:
				elem.setResistanceUnits( LineUnits.interpretUnitsCode(parser.makeString()) );
				break;
			case 3:
				elem.setGmr60(parser.makeDouble());
				break;
			case 4:
				elem.setGmrUnits( LineUnits.interpretUnitsCode(parser.makeString()) );
				break;
			case 5:
				elem.setRadius(parser.makeDouble());
				break;
			case 6:
				elem.setRadiusUnits( LineUnits.interpretUnitsCode(parser.makeString()) );
				break;
			case 7:
				elem.setNormAmps(parser.makeDouble());
				break;
			case 8:
				elem.setEmergAmps(parser.makeDouble());
				break;
			case 9:
				elem.setRadius(parser.makeDouble() / 2.0);
				break;
			default:
				super.classEdit(activeObj, paramPointer - numConductorClassProps);
				break;
			}
			/* Set defaults */
			switch (paramPointer) {
			case 0:
				if (elem.getR60() < 0.0)
					elem.setR60(1.02 * elem.getRdc());
				break;
			case 1:
				if (elem.getRdc() < 0.0)
					elem.setRdc(elem.getR60() / 1.02);
				break;
			case 3:
				if (elem.getRadius() < 0.0)
					elem.setRadius(elem.getGmr60() / 0.7788);
				break;
			case 4:
				if (elem.getRadiusUnits() == LineUnits.NONE)
					elem.setRadiusUnits(elem.getGmrUnits());
				break;
			case 5:
				if (elem.getGmr60() < 0.0)
					elem.setGmr60(0.7788 * elem.getRadius());
				break;
			case 6:
				if (elem.getGmrUnits() == LineUnits.NONE)
					elem.setGmrUnits(elem.getRadiusUnits());
				break;
			case 7:
				if (elem.getEmergAmps() < 0.0)
					elem.setEmergAmps(1.5 * elem.getNormAmps());
				break;
			case 8:
				if (elem.getNormAmps() < 0.0)
					elem.setNormAmps(elem.getEmergAmps() / 1.5);
				break;
			case 9:
				if (elem.getGmr60() < 0.0)
					elem.setGmr60(0.7788 * elem.getRadius());
				break;
			}

			/* Check for critical errors */
			switch (paramPointer) {
			case 3:
				if (elem.getRadius() == 0.0)
					DSS.doSimpleMsg("Error: Radius is specified as zero for ConductorData." + elem.getName(), 999);
				break;
			case 5:
				if (elem.getGmr60() == 0.0)
					DSS.doSimpleMsg("Error: GMR is specified as zero for ConductorData." + elem.getName(), 999);
				break;
			}
		}

		return 0;
	}

	protected void classMakeLike(DSSObject otherObj) {
		ConductorDataObj other = (ConductorDataObj) otherObj;
		ConductorDataObj elem = (ConductorDataObj) DSS.activeDSSObject;

		elem.setRdc(other.getRdc());
		elem.setR60(other.getR60());
		elem.setResistanceUnits(other.getResistanceUnits());
		elem.setGmr60(other.getGmr60());
		elem.setGmrUnits(other.getGmrUnits());
		elem.setRadius(other.getRadius());
		elem.setRadiusUnits(other.getRadiusUnits());
		elem.setNormAmps(other.getNormAmps());
		elem.setEmergAmps(other.getEmergAmps());
		//super.classMakeLike(OtherObj);
	}

}
