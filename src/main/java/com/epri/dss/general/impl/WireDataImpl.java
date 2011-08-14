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

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
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
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.setActiveDSSObject(new WireDataObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		int Result = 0;
		// continue parsing with contents of parser
		setActiveConductorDataObj((ConductorDataObj) elementList.getActive());
		DSSGlobals.getInstance().setActiveDSSObject(getActiveConductorDataObj());

		Parser parser = Parser.getInstance();

		ConductorDataObj acd = getActiveConductorDataObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= numProperties))
				acd.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +'.'+ acd.getName() + "\"", 101);
				break;
			default:
				// inherited parameters
				classEdit(getActiveConductorDataObj(), ParamPointer - WireData.NumPropsThisClass);
				break;
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

			ConductorDataObj awo = getActiveConductorDataObj();

			classMakeLike(OtherWireData);

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
		WireDataObj active = (WireDataObj) elementList.getActive();
		return active.getName();
	}

	public void setCode(String Value) {

		setActiveConductorDataObj(null);
		WireDataObj pWireData;
		for (int i = 0; i < elementList.size(); i++) {
			pWireData = (WireDataObj) elementList.get(i);
			if (pWireData.getName().equalsIgnoreCase(Value)) {
				setActiveConductorDataObj(pWireData);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("WireData: \"" + Value + "\" not found.", 103);
	}

}
