package com.epri.dss.common.impl;

import com.epri.dss.common.Solution;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.shared.impl.CommandListImpl;

public class SolutionImpl extends DSSClassImpl implements Solution {

	private static SolutionObj ActiveSolutionObj;

//	private static File FDebug;

	public static final int NumPropsThisClass = 1;

	public SolutionImpl() {
		super();
		this.Class_Name = "Solution";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT + DSSClassDefs.HIDDEN_ELEMENT;

		ActiveElement = 0;

		defineProperties();

		String[] Commands = new String[NumProperties];
		System.arraycopy(PropertyName, 0, Commands, 0, NumProperties);
		CommandList = new CommandListImpl(Commands);
		CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		NumProperties = NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		// Define Property names
		PropertyName[0] = "-------";

		// define Property help values
		PropertyHelp[0] = "Use Set Command to set Solution properties.";

		ActiveProperty = NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	public int newObject(String objName) {
		// Make a new Solution object and add it to Solution class list.
		ActiveSolutionObj = new SolutionObjImpl(this, objName);
		// this one is different than the rest of the objects.
		return addObjectToList(ActiveSolutionObj);
	}

	public int edit() {
		ActiveSolutionObj = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		// This is all we do here now...
		ActiveSolutionObj.solve();

		return 0;
	}

	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Solution.init()", -1);
		return 0;
	}

	public static SolutionObj getActiveSolutionObj() {
		return ActiveSolutionObj;
	}

	public static void setActiveSolutionObj(SolutionObj activeSolutionObj) {
		ActiveSolutionObj = activeSolutionObj;
	}

}
