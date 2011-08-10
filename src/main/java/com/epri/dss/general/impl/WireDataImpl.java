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
		this.Class_Name = "WireData";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;

		setActiveElement(-1);

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		NumProperties = WireData.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

		ActiveProperty = WireData.NumPropsThisClass - 1;
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
		setActiveConductorDataObj((ConductorDataObj) ElementList.getActive());
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
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
				acd.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:  // TODO Check zero based indexing
				DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +'.'+ acd.getName() + "\"", 101);
				break;
			default:
				// Inherited parameters
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
		WireDataObj active = (WireDataObj) ElementList.getActive();
		return active.getName();
	}

	public void setCode(String Value) {

		setActiveConductorDataObj(null);
		WireDataObj pWireData;
		for (int i = 0; i < ElementList.size(); i++) {
			pWireData = (WireDataObj) ElementList.get(i);
			if (pWireData.getName().equals(Value)) {
				setActiveConductorDataObj(pWireData);
				return;
			}
		}

		DSSGlobals.getInstance().doSimpleMsg("WireData: \"" + Value + "\" not found.", 103);
	}

}
