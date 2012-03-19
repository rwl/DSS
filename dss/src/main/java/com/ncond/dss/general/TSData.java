/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.general;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class TSData extends CableData {

	public static final int NumPropsThisClass = 3;

	public TSData() {
		super();
		className = "TSData";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = TSData.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		propertyName[0] = "DiaShield";
		propertyName[1] = "TapeLayer";
		propertyName[2] = "TapeLap";

		propertyHelp[0] = "Diameter over tape shield; same units as radius; no default.";
		propertyHelp[1] = "Tape shield thickness; same units as radius; no default.";
		propertyHelp[2] = "Tape Lap in percent; default 20.0";

		activeProperty = TSData.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new TSDataObj(this, objName);
		return addObjectToList(DSS.activeDSSObject);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		ConductorData.activeConductorDataObj = (ConductorDataObj) elementList.getActive();
		DSS.activeDSSObject = ConductorData.activeConductorDataObj;

		TSDataObj elem = (TSDataObj) ConductorData.activeConductorDataObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 101);
				break;
			case 0:
				elem.setDiaShield(parser.makeDouble());
				break;
			case 1:
				elem.setTapeLayer(parser.makeDouble());
				break;
			case 2:
				elem.setTapeLap(parser.makeDouble());
				break;
			default:
				// Inherited parameters
				classEdit(ConductorData.activeConductorDataObj, paramPointer - NumPropsThisClass);
				break;
			}

			/* Check for critical errors */
			switch (paramPointer) {
			case 0:
				if (elem.getDiaShield() <= 0.0)
					DSS.doSimpleMsg("Diameter over shield must be positive for TapeShieldData " + elem.getName(), 999);
				break;
			case 1:
				if (elem.getTapeLayer() <= 0.0)
					DSS.doSimpleMsg("Tape shield thickness must be positive for TapeShieldData " + elem.getName(), 999);
				break;
			case 2:
				if ((elem.getTapeLap() < 0.0) || (elem.getTapeLap() > 100.0))
					DSS.doSimpleMsg("Tap lap must range from 0 to 100 for TapeShieldData " + elem.getName(), 999);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		return 0;
	}

	@Override
	protected int makeLike(String TSName) {
		int success = 0;
		TSDataObj other = (TSDataObj) find(TSName);
		if (other != null) {
			TSDataObj elem = (TSDataObj) ConductorData.activeConductorDataObj;

			elem.setDiaShield(other.getDiaShield());
			elem.setTapeLayer(other.getTapeLayer());
			elem.setTapeLap(other.getTapeLap());
			classMakeLike(other);
			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in TapeShield makeLike: \"" + TSName + "\" not found.", 102);
		}
		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement TSData.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TSDataObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		ConductorData.activeConductorDataObj = null;
		TSDataObj elem = (TSDataObj) elementList.getFirst();
		while (elem != null) {
			if (elem.getName().equalsIgnoreCase(value)) {
				ConductorData.activeConductorDataObj = elem;
				return;
			}
			elem = (TSDataObj) elementList.getNext();
		}
		DSS.doSimpleMsg("TSData: \"" + value + "\" not found", 103);
	}

}
