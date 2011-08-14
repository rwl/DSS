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
		this.className = "TSData";
		this.classType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = TSData.NumPropsThisClass;
		countProperties();  // get inherited property count
		allocatePropertyArrays();

		propertyName[0] = "DiaShield";
		propertyName[1] = "TapeLayer";
		propertyName[2] = "TapeLap";

		propertyHelp[0] = "Diameter over tape shield; same units as radius; no default.";
		propertyHelp[1] = "Tape shield thickness; same units as radius; no default.";
		propertyHelp[2] = "Tape Lap in percent; default 20.0";

		activeProperty = TSData.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
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
		// continue parsing with contents of parser
		ConductorDataImpl.setActiveConductorDataObj((ConductorDataObj) elementList.getActive());
		Globals.setActiveDSSObject(ConductorDataImpl.getActiveConductorDataObj());

		TSDataObj tsd = (TSDataObj) ConductorDataImpl.getActiveConductorDataObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				tsd.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ tsd.getName() + "\"", 101);
				break;
			case 0:
				tsd.setDiaShield(parser.makeDouble());
				break;
			case 1:
				tsd.setTapeLayer(parser.makeDouble());
				break;
			case 2:
				tsd.setTapeLap(parser.makeDouble());
				break;
			default:
				// Inherited parameters
				classEdit(ConductorDataImpl.getActiveConductorDataObj(), ParamPointer - NumPropsThisClass);
				break;
			}

			/* Check for critical errors */
			switch (ParamPointer) {
			case 0:
				if (tsd.getDiaShield() <= 0.0)
					Globals.doSimpleMsg("Error: Diameter over shield must be positive for TapeShieldData " + tsd.getName(), 999);
				break;
			case 1:
				if (tsd.getTapeLayer() <= 0.0)
					Globals.doSimpleMsg("Error: Tape shield thickness must be positive for TapeShieldData " + tsd.getName(), 999);
				break;
			case 2:
				if ((tsd.getTapeLap() < 0.0) || (tsd.getTapeLap() > 100.0))
					Globals.doSimpleMsg("Error: Tap lap must range from 0 to 100 for TapeShieldData " + tsd.getName(), 999);
				break;
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
			DSSGlobals.getInstance().doSimpleMsg("Error in TapeShield makeLike: \"" + TSName + "\" not found.", 102);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement TSData.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TSDataObj) elementList.getActive()).getName();
	}

	public void setCode(String Value) {
		ConductorDataImpl.setActiveConductorDataObj(null);
		TSDataObj pTSDataObj = (TSDataObj) elementList.getFirst();
		while (pTSDataObj != null) {
			if (pTSDataObj.getName().equalsIgnoreCase(Value)) {
				ConductorDataImpl.setActiveConductorDataObj(pTSDataObj);
				return;
			}
			pTSDataObj = (TSDataObj) elementList.getNext();
		}
		DSSGlobals.getInstance().doSimpleMsg("TSData: \"" + Value + "\" not found.", 103);
	}

}
