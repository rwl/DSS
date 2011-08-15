package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.WireData;
import com.epri.dss.general.WireDataObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class WireDataImpl extends ConductorDataImpl implements WireData {

	public WireDataImpl() {
		super();
		this.className = "WireData";
		this.classType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		numProperties = WireData.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		activeProperty = WireData.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();

		globals.setActiveDSSObject(new WireDataObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		int result = 0;
		// continue parsing with contents of parser
		activeConductorDataObj = (ConductorDataObj) elementList.getActive();
		DSSGlobals.getInstance().setActiveDSSObject(activeConductorDataObj);

		Parser parser = Parser.getInstance();

		ConductorDataObj acd = activeConductorDataObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer > 0) && (paramPointer <= numProperties))
				acd.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +'.'+ acd.getName() + "\"", 101);
				break;
			default:
				// inherited parameters
				classEdit(activeConductorDataObj, paramPointer - WireData.NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		return result;
	}

	@Override
	protected int makeLike(String name) {
		int result = 0;
		/* See if we can find this line code in the present collection */
		WireDataObj otherWireData = (WireDataObj) find(name);
		if (otherWireData != null) {

			ConductorDataObj awo = activeConductorDataObj;

			classMakeLike(otherWireData);

			for (int i = 0; i < awo.getParentClass().getNumProperties(); i++)
				awo.setPropertyValue(i, otherWireData.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in WireData.makeLike: \"" + name + "\" not found.", 102);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement WireData.init()", -1);
		return 0;
	}

	public String getCode() {
		WireDataObj active = (WireDataObj) elementList.getActive();
		return active.getName();
	}

	public void setCode(String value) {

		activeConductorDataObj = null;
		WireDataObj wireData;
		for (int i = 0; i < elementList.size(); i++) {
			wireData = (WireDataObj) elementList.get(i);
			if (wireData.getName().equalsIgnoreCase(value)) {
				activeConductorDataObj = wireData;
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("WireData: \"" + value + "\" not found.", 103);
	}

}
