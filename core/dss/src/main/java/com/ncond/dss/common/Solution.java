/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.common;

import com.ncond.dss.shared.CommandList;

/**
 * Collection of all solution objects.
 */
public class Solution extends DSSClass {

	public static SolutionObj activeSolutionObj;

	public static final int NumPropsThisClass = 1;

	public Solution() {
		super();
		className = "Solution";
		classType = DSSClassDefs.DSS_OBJECT + DSSClassDefs.HIDDEN_ELEMENT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = NumPropsThisClass;

		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "-------";

		// define property help values
		propertyHelp[0] = "Use Set command to set solution properties.";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		// make a new solution object and add it to solution class list
		activeSolutionObj = new SolutionObj(this, objName);

		// this one is different than the rest of the objects
		return addObjectToList(activeSolutionObj);
	}

	@Override
	public int edit() {
		activeSolutionObj = DSS.activeCircuit.getSolution();

		// This is all we do here now...
		activeSolutionObj.solve();
		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Solution.init()", -1);
		return 0;
	}

	@Override
	protected int makeLike(String objName) {
		DSS.doSimpleMsg("Need to implement Solution.makeLike()", -1);
		return 0;
	}

}
