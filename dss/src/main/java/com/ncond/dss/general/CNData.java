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

public class CNData extends CableData {

	public static final int NumPropsThisClass = 4;

	public CNData() {
		super();
		className = "CNData";
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
		numProperties = CNData.NumPropsThisClass;
		countProperties();   // get inherited property count

		allocatePropertyArrays();

		propertyName[0] = "k";
		propertyName[1] = "DiaStrand";
		propertyName[2] = "GmrStrand";
		propertyName[3] = "Rstrand";

		propertyHelp[0] = "Number of concentric neutral strands; default is 2";
		propertyHelp[1] = "Diameter of a concentric neutral strand; same units as core conductor radius; no default.";
		propertyHelp[2] = "Geometric mean radius of a concentric neutral strand; same units as core conductor GMR; defaults to 0.7788 * CN strand radius.";
		propertyHelp[3] = "AC resistance of a concentric neutral strand; same units as core conductor resistance; no default.";

		activeProperty = CNData.NumPropsThisClass - 1;

		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeDSSObject = new CNDataObj(this, objName);
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

		CNDataObj elem = (CNDataObj) ConductorData.activeConductorDataObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

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
						getClassName() +"."+ getClassName() + "\"", 101);
				break;
			case 0:
				elem.setKStrand(parser.integerValue());
				break;
			case 1:
				elem.setDiaStrand(parser.doubleValue());
				break;
			case 2:
				elem.setGmrStrand(parser.doubleValue());
				break;
			case 3:
				elem.setRStrand(parser.doubleValue());
				break;
			default:
				// inherited parameters
				classEdit(ConductorData.activeConductorDataObj, paramPointer - CNData.NumPropsThisClass);
				break;
			}

			/* Set defaults */
			switch (paramPointer) {
			case 1:
				if (elem.getGmrStrand() <= 0.0)
					elem.setGmrStrand(0.7788 * 0.5 * elem.getDiaStrand());
				break;
			}

			/* Check for critical errors */
			switch (paramPointer) {
			case 0:
				if (elem.getKStrand() < 2)
					DSS.doSimpleMsg("Must have at least 2 concentric neutral strands for CNData " + elem.getName(), 999);
				break;
			case 1:
				if (elem.getDiaStrand() <= 0.0)
					DSS.doSimpleMsg("Neutral strand diameter must be positive for CNData " + elem.getName(), 999);
				break;
			case 2:
				if (elem.getGmrStrand() <= 0.0)
					DSS.doSimpleMsg("Neutral strand GMR must be positive for CNData " + elem.getName(), 999);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		CNDataObj elem = (CNDataObj) ConductorData.activeConductorDataObj;

		int success = 0;
		CNDataObj other = (CNDataObj) find(name);

		if (other != null) {
			elem.setKStrand(other.getKStrand());
			elem.setDiaStrand(other.getDiaStrand());
			elem.setGmrStrand(other.getGmrStrand());
			elem.setRStrand(other.getRStrand());
			classMakeLike(other);
			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in concentric neutral makeLike: \"" + name + "\" not found.", 102);
		}
		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement CNData.init().", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		return ((CNDataObj) elementList.getActive()).getName();
	}

	/**
	 * Sets the active CNData.
	 */
	public void setCode(String value) {
		ConductorData.activeConductorDataObj = null;

		CNDataObj pCNDataObj = (CNDataObj) elementList.getFirst();
		while (pCNDataObj != null) {
			if (pCNDataObj.getName().equalsIgnoreCase(value)) {
				ConductorData.activeConductorDataObj = pCNDataObj;
				return;
			}
			pCNDataObj = (CNDataObj) elementList.getNext();
		}

		DSS.doSimpleMsg("CNData: \"" + value + "\" not found.", 103);
	}

}
