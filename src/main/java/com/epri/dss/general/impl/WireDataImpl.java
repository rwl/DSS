package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.WireData;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.LineUnits;

public class WireDataImpl extends DSSClassImpl implements WireData {

	private static WireDataObj ActiveWireDataObj;

	public WireDataImpl() {
		super();
		this.Class_Name = "WireData";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = 0;

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		NumProperties = WireData.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

		PropertyName[0] = "Rdc";
		PropertyName[1] = "Rac";
		PropertyName[2] = "Runits";
		PropertyName[3] = "GMRac";
		PropertyName[4] = "GMRunits";
		PropertyName[5] = "radius";
		PropertyName[6] = "radunits";
		PropertyName[7] = "normamps";
		PropertyName[8] = "emergamps";
		PropertyName[9] = "diam";

		PropertyHelp[0] = "dc Resistance, ohms per unit length (see Runits). Defaults to Rac/1.02 if not specified.";
		PropertyHelp[1] = "Resistance at 60 Hz per unit length. Defaults to 1.02*Rdc if not specified.";
		PropertyHelp[2] = "Length units for resistance: ohms per {mi|kft|km|m|Ft|in|cm } Default=none.";
		PropertyHelp[3] = "GMR at 60 Hz. Defaults to .7788*radius if not specified.";
		PropertyHelp[4] = "Units for GMR: {mi|kft|km|m|Ft|in|cm } Default=none.";
		PropertyHelp[5] = "Outside radius of conductor. Defaults to GMR/0.7788 if not specified.";
		PropertyHelp[6] = "Units for outside radius: {mi|kft|km|m|Ft|in|cm } Default=none.";
		PropertyHelp[7] = "Normal ampacity, amperes. Defaults to Emergency amps/1.5 if not specified.";
		PropertyHelp[8] = "Emergency ampacity, amperes. Defaults to 1.5 * Normal Amps if not specified.";
		PropertyHelp[9] = "Diameter; Alternative method for entering radius.";

		ActiveProperty = WireData.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new WireDataObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		int Result = 0;
		// continue parsing with contents of parser
		setActiveWireDataObj((WireDataObj) ElementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveWireDataObj());

		Parser parser = Parser.getInstance();

		WireDataObj awo = getActiveWireDataObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
				awo.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case 0:  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +'.'+ awo.getName() + "\"", 101);
			case 1:
				awo.setRDC(parser.makeDouble());  // Use property value to force reallocations
			case 2:
				awo.setR60(parser.makeDouble());
			case 3:
				awo.setResistanceUnits(LineUnits.getUnitsCode(Param));
			case 4:
				awo.setGMR60(parser.makeDouble());
			case 5:
				awo.setGMRUnits(LineUnits.getUnitsCode(Param));
			case 6:
				awo.setRadius(parser.makeDouble());
			case 7:
				awo.setRadiusUnits(LineUnits.getUnitsCode(Param));
			case 8:
				awo.setNormAmps(parser.makeDouble());
			case 9:
				awo.setEmergAmps(parser.makeDouble());
			case 10:
				awo.setRadius(parser.makeDouble() / 2.0);
			default:
				// Inherited parameters
				classEdit(getActiveWireDataObj(), ParamPointer - WireData.NumPropsThisClass);
			}

			// Set defaults
			switch (ParamPointer) {
			case 1:
				if (awo.getR60() < 0.0)
					awo.setR60(1.02 * awo.getRDC());
			case 2:
				if (awo.getRDC() < 0.0)
					awo.setRDC(awo.getR60() / 1.02);
			case 4:
				if (awo.getRadius() < 0.0)
					awo.setRadius(awo.getGMR60() / 0.7788);
			case 5:
				if (awo.getRadiusUnits() == 0 )
					awo.setRadiusUnits(awo.getGMRUnits());
			case 6:
				if (awo.getGMR60() < 0.0)
					awo.setGMR60(0.7788 * awo.getRadius());
			case 7:
				if (awo.getGMRUnits() == 0)
					awo.setGMRUnits(awo.getRadiusUnits());
			case 8:
				if (awo.getEmergAmps() < 0.0)
					awo.setEmergAmps(1.5 * awo.getNormAmps());
			case 9:
				if (awo.getNormAmps() < 0.0)
					awo.setNormAmps(awo.getEmergAmps() / 1.5);
			case 10:
				if (awo.getGMR60() < 0.0)
					awo.setGMR60(0.7788 * awo.getRadius());
			}

			/* Check for critical errors */
			switch (ParamPointer) {
			case 4:
				if (awo.getRadius() == 0.0)
					DSSGlobals.getInstance().doSimpleMsg("Error: Radius is specified as zero for Wiredata." + awo.getName(), 999);
			case 6:
				if (awo.getGMR60() == 0.0)
					DSSGlobals.getInstance().doSimpleMsg("Error: GMR is specified as zero for Wiredata." + awo.getName(), 999);
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		return Result;
	}

	@Override
	protected int makeLike(String Name) {
		int Result = 0;
		/* See if we can find this line code in the present collection */
		WireDataObj OtherWireData = (WireDataObj) find(Name);
		if (OtherWireData != null) {

			WireDataObj awo = getActiveWireDataObj();

			awo.setRDC(OtherWireData.getRDC());
			awo.setR60(OtherWireData.getR60());
			awo.setResistanceUnits(OtherWireData.getResistanceUnits());
			awo.setGMR60(OtherWireData.getGMR60());
			awo.setGMRUnits(OtherWireData.getGMRUnits());
			awo.setRadius(OtherWireData.getRadius());
			awo.setRadiusUnits(OtherWireData.getRadiusUnits());
			awo.setNormAmps(OtherWireData.getNormAmps());
			awo.setEmergAmps(OtherWireData.getEmergAmps());

			for (int i = 0; i < awo.getParentClass().getNumProperties(); i++)
				awo.setPropertyValue(i, OtherWireData.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in WireData.makeLike: \"" + Name + "\" not found.", 102);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement WireData.init()", -1);
		return 0;
	}

	public String getCode() {
		WireDataObj active = (WireDataObj) ElementList.getActive();
		return active.getName();
	}

	public void setCode(String Value) {

		setActiveWireDataObj(null);
		WireDataObj pWireData;
		for (int i = 0; i < ElementList.size(); i++) {
			pWireData = (WireDataObj) ElementList.get(i);
			if (pWireData.getName().equals(Value)) {
				setActiveWireDataObj(pWireData);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("WireData: \"" + Value + "\" not found.", 103);
	}

	public static WireDataObj getActiveWireDataObj() {
		return ActiveWireDataObj;
	}

	public static void setActiveWireDataObj(WireDataObj activeWireDataObj) {
		ActiveWireDataObj = activeWireDataObj;
	}

}
