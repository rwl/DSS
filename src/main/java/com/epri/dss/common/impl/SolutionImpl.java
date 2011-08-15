package com.epri.dss.common.impl;

import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.shared.impl.CommandListImpl;

public class SolutionImpl extends DSSClassImpl implements Solution {

	public static SolutionObj activeSolutionObj;

//	private static File fDebug;

	public static final int NumPropsThisClass = 1;

	public SolutionImpl() {
		super();
		this.className = "Solution";
		this.classType = DSSClassDefs.DSS_OBJECT + DSSClassDefs.HIDDEN_ELEMENT;

		this.activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "-------";

		// define property help values
		propertyHelp[0] = "Use Set Command to set Solution properties.";

		activeProperty = NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	public int newObject(String objName) {
		// make a new solution object and add it to solution class list
		activeSolutionObj = new SolutionObjImpl(this, objName);
		// this one is different than the rest of the objects
		return addObjectToList(activeSolutionObj);
	}

	public int edit() {
		activeSolutionObj = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		// This is all we do here now...
		activeSolutionObj.solve();

		return 0;
	}

	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Solution.init()", -1);
		return 0;
	}

}
