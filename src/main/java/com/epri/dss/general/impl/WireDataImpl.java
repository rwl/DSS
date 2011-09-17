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
		className = "WireData";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
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

		DSSGlobals.activeDSSObject = new WireDataObjImpl(this, objName);
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	@Override
	public int edit() {
		int result = 0;
		// continue parsing with contents of parser
		activeConductorDataObj = (ConductorDataObj) elementList.getActive();
		DSSGlobals.activeDSSObject = activeConductorDataObj;

		Parser parser = Parser.getInstance();

		ConductorDataObj acd = activeConductorDataObj;

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
				acd.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +'.'+ acd.getName() + "\"", 101);
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
			DSSGlobals.doSimpleMsg("Error in WireData.makeLike: \"" + name + "\" not found.", 102);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.doSimpleMsg("Need to implement WireData.init()", -1);
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

		DSSGlobals.doSimpleMsg("WireData: \"" + value + "\" not found.", 103);
	}

}
