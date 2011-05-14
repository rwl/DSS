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

	private static ConductorDataObj ActiveConductorDataObj;

	private static ConductorChoice[] ConductorChoiceArray = new ConductorChoice[100];

	private int NumConductorClassProps;

	public ConductorDataImpl() {
		super();
		this.NumConductorClassProps = 10;
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;
	}

	protected void countProperties() {
		NumProperties = NumProperties + getNumConductorClassProps();
		super.countProperties();
	}

	protected void defineProperties() {
		PropertyName[ActiveProperty + 0] = "Rdc";
		PropertyName[ActiveProperty + 1] = "Rac";
		PropertyName[ActiveProperty + 2] = "Runits";
		PropertyName[ActiveProperty + 3] = "GMRac";
		PropertyName[ActiveProperty + 4] = "GMRunits";
		PropertyName[ActiveProperty + 5] = "radius";
		PropertyName[ActiveProperty + 6] = "radunits";
		PropertyName[ActiveProperty + 7] = "normamps";
		PropertyName[ActiveProperty + 8] = "emergamps";
		PropertyName[ActiveProperty + 9] = "diam";

		PropertyHelp[ActiveProperty + 0] = "dc Resistance, ohms per unit length (see Runits). Defaults to Rac/1.02 if not specified.";
		PropertyHelp[ActiveProperty + 1] = "Resistance at 60 Hz per unit length. Defaults to 1.02*Rdc if not specified.";
		PropertyHelp[ActiveProperty + 2] = "Length units for resistance: ohms per " + LineUnitsHelp;
		PropertyHelp[ActiveProperty + 3] = "GMR at 60 Hz. Defaults to .7788*radius if not specified.";
		PropertyHelp[ActiveProperty + 4] = "Units for GMR: " + LineUnitsHelp;
		PropertyHelp[ActiveProperty + 5] = "Outside radius of conductor. Defaults to GMR/0.7788 if not specified.";
		PropertyHelp[ActiveProperty + 6] = "Units for outside radius: " + LineUnitsHelp;
		PropertyHelp[ActiveProperty + 7] = "Normal ampacity, amperes. Defaults to Emergency amps/1.5 if not specified.";
		PropertyHelp[ActiveProperty + 8] = "Emergency ampacity, amperes. Defaults to 1.5 * Normal Amps if not specified.";
		PropertyHelp[ActiveProperty + 9] = "Diameter; Alternative method for entering radius.";

		ActiveProperty = ActiveProperty + NumConductorClassProps;
		super.defineProperties();
	}

	protected int classEdit(DSSObject ActiveObj, int ParamPointer) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of parser
		if (ParamPointer >= 0) {
			ConductorDataObj cd = (ConductorDataObj) ActiveObj;
			switch (ParamPointer) {
			case 0:
				cd.setRDC(parser.makeDouble());
			case 1:
				cd.setR60(parser.makeDouble());
			case 2:
				cd.setResistanceUnits( LineUnits.getUnitsCode(parser.makeString()) );
			case 3:
				cd.setGMR60(parser.makeDouble());
			case 4:
				cd.setGMRUnits( LineUnits.getUnitsCode(parser.makeString()) );
			case 5:
				cd.setRadius(parser.makeDouble());
			case 6:
				cd.setRadiusUnits( LineUnits.getUnitsCode(parser.makeString()) );
			case 7:
				cd.setNormAmps(parser.makeDouble());
			case 8:
				cd.setEmergAmps(parser.makeDouble());
			case 9:
				cd.setRadius(parser.makeDouble() / 2.0);
			default:
				super.classEdit(ActiveObj, ParamPointer - NumConductorClassProps);
			}
			/* Set defaults */
			switch (ParamPointer) {
			case 0:
				if (cd.getR60() < 0.0)
					cd.setR60(1.02 * cd.getRDC());
			case 1:
				if (cd.getRDC() < 0.0)
					cd.setRDC(cd.getR60() / 1.02);
			case 3:
				if (cd.getRadius() < 0.0)
					cd.setRadius(cd.getGMR60() / 0.7788);
			case 4:
				if (cd.getRadiusUnits() == 0)
					cd.setRadiusUnits(cd.getGMRUnits());
			case 5:
				if (cd.getGMR60() < 0.0)
					cd.setGMR60(0.7788 * cd.getRadius());
			case 6:
				if (cd.getGMRUnits() == 0)
					cd.setGMRUnits(cd.getRadiusUnits());
			case 7:
				if (cd.getEmergAmps() < 0.0)
					cd.setEmergAmps(1.5 * cd.getNormAmps());
			case 8:
				if (cd.getNormAmps() < 0.0)
					cd.setNormAmps(cd.getEmergAmps() / 1.5);
			case 9:
				if (cd.getGMR60() < 0.0)
					cd.setGMR60(0.7788 * cd.getRadius());
			}
			/* Check for critical errors */
			switch (ParamPointer) {
			case 3:
				if (cd.getRadius() == 0.0)
					Globals.doSimpleMsg("Error: Radius is specified as zero for ConductorData." + cd.getName(), 999);
			case 5:
				if (cd.getGMR60() == 0.0)
					Globals.doSimpleMsg("Error: GMR is specified as zero for ConductorData." + cd.getName(), 999);
			}
		}

		return Result;
	}

	protected void classMakeLike(DSSObject OtherObj) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		ConductorDataObj OtherConductorData = (ConductorDataObj) OtherObj;
		ConductorDataObj cd = (ConductorDataObj) Globals.getActiveDSSObject();

		cd.setRDC(OtherConductorData.getRDC());
		cd.setR60(OtherConductorData.getR60());
		cd.setResistanceUnits(OtherConductorData.getResistanceUnits());
		cd.setGMR60(OtherConductorData.getGMR60());
		cd.setGMRUnits(OtherConductorData.getGMRUnits());
		cd.setRadius(OtherConductorData.getRadius());
		cd.setRadiusUnits(OtherConductorData.getRadiusUnits());
		cd.setNormAmps(OtherConductorData.getNormAmps());
		cd.setEmergAmps(OtherConductorData.getEmergAmps());
		//super.classMakeLike(OtherObj);
	}

	public void setNumConductorClassProps(int numConductorClassProps) {
		NumConductorClassProps = numConductorClassProps;
	}

	public int getNumConductorClassProps() {
		return NumConductorClassProps;
	}

	public static void setActiveConductorDataObj(ConductorDataObj activeConductorDataObj) {
		ActiveConductorDataObj = activeConductorDataObj;
	}

	public static ConductorDataObj getActiveConductorDataObj() {
		return ActiveConductorDataObj;
	}

}
