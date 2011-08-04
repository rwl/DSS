package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.general.TSData;
import com.epri.dss.general.TSDataObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class TSDataImpl extends CableDataImpl implements TSData {

	public TSDataImpl() {
		super();
		this.Class_Name = "TSData";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = TSData.NumPropsThisClass;
		countProperties();  // Get inherited property count
		allocatePropertyArrays();

		PropertyName[0] = "DiaShield";
		PropertyName[1] = "TapeLayer";
		PropertyName[2] = "TapeLap";

		PropertyHelp[0] = "Diameter over tape shield; same units as radius; no default.";
		PropertyHelp[1] = "Tape shield thickness; same units as radius; no default.";
		PropertyHelp[2] = "Tape Lap in percent; default 20.0";

		ActiveProperty = TSData.NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new TSDataObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int Result = 0;
		// continue parsing with contents of Parser
		ConductorDataImpl.setActiveConductorDataObj((ConductorDataObj) ElementList.getActive());
		Globals.setActiveDSSObject(ConductorDataImpl.getActiveConductorDataObj());

		TSDataObj tsd = (TSDataObj) ConductorDataImpl.getActiveConductorDataObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				tsd.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ tsd.getName() + "\"", 101);
			case 0:
				tsd.setDiaShield(parser.makeDouble());
			case 1:
				tsd.setTapeLayer(parser.makeDouble());
			case 2:
				tsd.setTapeLap(parser.makeDouble());
			default:
				// Inherited parameters
				classEdit(ConductorDataImpl.getActiveConductorDataObj(), ParamPointer - NumPropsThisClass);
			}

			/* Check for critical errors */
			switch (ParamPointer) {
			case 0:
				if (tsd.getDiaShield() <= 0.0)
					Globals.doSimpleMsg("Error: Diameter over shield must be positive for TapeShieldData " + tsd.getName(), 999);
			case 1:
				if (tsd.getTapeLayer() <= 0.0)
					Globals.doSimpleMsg("Error: Tape shield thickness must be positive for TapeShieldData " + tsd.getName(), 999);
			case 2:
				if ((tsd.getTapeLap() < 0.0) || (tsd.getTapeLap() > 100.0))
					Globals.doSimpleMsg("Error: Tap lap must range from 0 to 100 for TapeShieldData " + tsd.getName(), 999);
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		return Result;
	}

	@Override
	protected int makeLike(String TSName) {
		int Result = 0;
		TSDataObj OtherData = (TSDataObj) find(TSName);
		if (OtherData != null) {
			TSDataObj tsd = (TSDataObj) ConductorDataImpl.getActiveConductorDataObj();

			tsd.setDiaShield(OtherData.getDiaShield());
			tsd.setTapeLayer(OtherData.getTapeLayer());
			tsd.setTapeLap(OtherData.getTapeLap());
			classMakeLike(OtherData);
			for (int i = 0; i < tsd.getParentClass().getNumProperties(); i++)
				tsd.setPropertyValue(i, OtherData.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in TapeShield makeLike: \"" + TSName + "\" Not Found.", 102);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TSData.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TSDataObj) ElementList.getActive()).getName();
	}

	public void setCode(String Value) {
		ConductorDataImpl.setActiveConductorDataObj(null);
		TSDataObj pTSDataObj = (TSDataObj) ElementList.getFirst();
		while (pTSDataObj != null) {
			if (pTSDataObj.getName().equals(Value)) {
				ConductorDataImpl.setActiveConductorDataObj(pTSDataObj);
				return;
			}
			pTSDataObj = (TSDataObj) ElementList.getNext();
		}
		DSSGlobals.getInstance().doSimpleMsg("TSData: \"" + Value + "\" not Found.", 103);
	}

}
