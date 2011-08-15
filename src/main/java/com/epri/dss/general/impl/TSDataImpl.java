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
		className = "TSData";
		classType = DSSClassDefs.DSS_OBJECT;

		activeElement = -1;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
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
	public int newObject(String objName) {
		DSSGlobals.activeDSSObject = new TSDataObjImpl(this, objName);
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		ConductorDataImpl.activeConductorDataObj = (ConductorDataObj) elementList.getActive();
		DSSGlobals.activeDSSObject = ConductorDataImpl.activeConductorDataObj;

		TSDataObj tsd = (TSDataObj) ConductorDataImpl.activeConductorDataObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < numProperties))
				tsd.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ tsd.getName() + "\"", 101);
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
				classEdit(ConductorDataImpl.activeConductorDataObj, paramPointer - NumPropsThisClass);
				break;
			}

			/* Check for critical errors */
			switch (paramPointer) {
			case 0:
				if (tsd.getDiaShield() <= 0.0)
					DSSGlobals.doSimpleMsg("Error: Diameter over shield must be positive for TapeShieldData " + tsd.getName(), 999);
				break;
			case 1:
				if (tsd.getTapeLayer() <= 0.0)
					DSSGlobals.doSimpleMsg("Error: Tape shield thickness must be positive for TapeShieldData " + tsd.getName(), 999);
				break;
			case 2:
				if ((tsd.getTapeLap() < 0.0) || (tsd.getTapeLap() > 100.0))
					DSSGlobals.doSimpleMsg("Error: Tap lap must range from 0 to 100 for TapeShieldData " + tsd.getName(), 999);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		return result;
	}

	@Override
	protected int makeLike(String TSName) {
		int result = 0;
		TSDataObj otherData = (TSDataObj) find(TSName);
		if (otherData != null) {
			TSDataObj tsd = (TSDataObj) ConductorDataImpl.activeConductorDataObj;

			tsd.setDiaShield(otherData.getDiaShield());
			tsd.setTapeLayer(otherData.getTapeLayer());
			tsd.setTapeLap(otherData.getTapeLap());
			classMakeLike(otherData);
			for (int i = 0; i < tsd.getParentClass().getNumProperties(); i++)
				tsd.setPropertyValue(i, otherData.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.doSimpleMsg("Error in TapeShield makeLike: \"" + TSName + "\" not found.", 102);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.doSimpleMsg("Need to implement TSData.init", -1);
		return 0;
	}

	public String getCode() {
		return ((TSDataObj) elementList.getActive()).getName();
	}

	public void setCode(String value) {
		ConductorDataImpl.activeConductorDataObj = null;
		TSDataObj pTSDataObj = (TSDataObj) elementList.getFirst();
		while (pTSDataObj != null) {
			if (pTSDataObj.getName().equalsIgnoreCase(value)) {
				ConductorDataImpl.activeConductorDataObj = pTSDataObj;
				return;
			}
			pTSDataObj = (TSDataObj) elementList.getNext();
		}
		DSSGlobals.doSimpleMsg("TSData: \"" + value + "\" not found.", 103);
	}

}
