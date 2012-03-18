/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import com.ncond.dss.conversion.PCClass;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Feeder extends PCClass {

	public static int NumPropsThisClass = 0;

	public static FeederObj activeFeederObj;

	public Feeder() {
		super();
		className = "Feeder";
		classType = DSSClassDefs.FEEDER_ELEMENT; /*+ PC_ELEMENT; */ // add to PCElement list

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		NumPropsThisClass = 0;

		numProperties = NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		// no properties we want the user to be able to set

		// define property names
		//propertyName[0] = "bus1";

		// define Property help values
		//propertyHelp[0] = "Name of bus to which source is connected." + DSS.CRLF +
		//"bus1=busname" + DSS.CRLF + "bus1=busname.1.2.3";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	/**
	 * Called from EnergyMeter.
	 */
	@Override
	public int newObject(String objName) {
		int result;

		// make a new feeder object
		// first see if this one already exists; if so, just reinitialize
		FeederObj obj = (FeederObj) find(objName);

		Circuit ckt = DSS.activeCircuit;

		if (obj != null) {
			ckt.setActiveCktElement((CktElement) obj);
			result = -1;
		} else {
			ckt.setActiveCktElement(new FeederObj(this, objName));
			result = addObjectToList(DSS.activeDSSObject);
			ckt.addCktElement(result);
			// done here because feeder objects are instantiated from energy meters
		}

		return result;
	}

	@Override
	public int edit() {
		// continue parsing with contents of parser
		activeFeederObj = (FeederObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement((CktElement) activeFeederObj);

		int paramPointer = -1;
		String paramName = Parser.getInstance().getNextParam();
		String param = Parser.getInstance().makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				activeFeederObj.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ activeFeederObj.getName() + "\"", 630);
				break;
			default:
				classEdit(activeFeederObj, paramPointer - NumPropsThisClass);
				break;
			}

			paramName = Parser.getInstance().getNextParam();
			param     = Parser.getInstance().makeString();
		}

		activeFeederObj.recalcElementData();
		activeFeederObj.setYPrimInvalid(true);

		return 0;
	}

	@Override
	protected int makeLike(String otherFeederName) {
		int success = 0;

		/* See if we can find this name in the present collection */
		FeederObj otherFeeder = (FeederObj) find(otherFeederName);
		if (otherFeeder != null) {
			if (activeFeederObj.getNumPhases() != otherFeeder.getNumPhases()) {
				activeFeederObj.setNumPhases(otherFeeder.getNumPhases());
				activeFeederObj.setNumConds(activeFeederObj.getNumPhases());  // forces reallocation of terminal arrays

				activeFeederObj.setYOrder(activeFeederObj.getNumConds() * activeFeederObj.getNumTerms());
				activeFeederObj.setYPrimInvalid(true);
			}

			// put properties to copy here

			classMakeLike(otherFeeder);  // set spectrum, base frequency

			for (int i = 0; i < activeFeederObj.getParentClass().getNumProperties(); i++) {
				activeFeederObj.setPropertyValue(i, otherFeeder.getPropertyValue(i));
			}
			success = 1;
		} else {
			DSS.doSimpleMsg("Error in Feeder makeLike: \"" + otherFeederName + "\" not found.", 631);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Feeder.init()", -1);
		return 0;
	}

}
