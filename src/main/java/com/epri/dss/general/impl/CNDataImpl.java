package com.epri.dss.general.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.general.CNData;
import com.epri.dss.general.CNDataObj;
import com.epri.dss.general.ConductorDataObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class CNDataImpl extends CableDataImpl implements CNData {

	public CNDataImpl() {
		super();
		this.className = "CNData";
		this.classType = DSSClassDefs.DSS_OBJECT;

		this.activeElement = -1;

		defineProperties();

		String[] commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		numProperties = CNData.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		propertyName[0] = "k";
		propertyName[1] = "DiaStrand";
		propertyName[2] = "GmrStrand";
		propertyName[3] = "Rstrand";

		propertyHelp[0] = "Number of concentric neutral strands; default is 2";
		propertyHelp[1] = "Diameter of a concentric neutral strand; same units as core conductor radius; no default.";
		propertyHelp[2] = "Geometric mean radius of a concentric neutral strand; same units as core conductor GMR; defaults to 0.7788 * CN strand radius.";
		propertyHelp[3] = "AC resistance of a concentric neutral strand; same units as core conductor resistance; no default.";

		activeProperty = CNData.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSSGlobals globals = DSSGlobals.getInstance();
		globals.setActiveDSSObject(new CNDataObjImpl(this, objName));
		return addObjectToList(globals.getActiveDSSObject());
	}

	/**
	 * Uses global parser.
	 */
	@Override
	public int edit() {
		DSSGlobals globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		int result = 0;
		// continue parsing with contents of parser
		ConductorDataImpl.setActiveConductorDataObj((ConductorDataObj) elementList.getActive());
		globals.setActiveDSSObject(ConductorDataImpl.getActiveConductorDataObj());

		CNDataObj acd = (CNDataObj) ConductorDataImpl.getActiveConductorDataObj();

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
				acd.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				globals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ getName() + "\"", 101);
				break;
			case 0:
				acd.setkStrand(parser.makeInteger());
				break;
			case 1:
				acd.setDiaStrand(parser.makeDouble());
				break;
			case 2:
				acd.setGmrStrand(parser.makeDouble());
				break;
			case 3:
				acd.setRStrand(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(ConductorDataImpl.getActiveConductorDataObj(), paramPointer - CNData.NumPropsThisClass);
				break;
			}

			/* Set defaults */
			switch (paramPointer) {
			case 1:
				if (acd.getGmrStrand() <= 0.0)
					acd.setGmrStrand(0.7788 * 0.5 * acd.getDiaStrand());
				break;
			}

			/* Check for critical errors */
			switch (paramPointer) {
			case 0:
				if (acd.getkStrand() < 2)
					globals.doSimpleMsg("Error: Must have at least 2 concentric neutral strands for CNData " + acd.getName(), 999);
				break;
			case 1:
				if (acd.getDiaStrand() <= 0.0)
					globals.doSimpleMsg("Error: Neutral strand diameter must be positive for CNData " + acd.getName(), 999);
				break;
			case 2:
				if (acd.getGmrStrand() <= 0.0)
					globals.doSimpleMsg("Error: Neutral strand GMR must be positive for CNData " + acd.getName(), 999);
				break;
			}
			paramName = parser.getNextParam();
			param = parser.makeString();
		}
		return result;
	}

	@Override
	protected int makeLike(String CNName) {
		CNDataObj acd = (CNDataObj) ConductorDataImpl.getActiveConductorDataObj();

		int result = 0;
		CNDataObj otherData = (CNDataObj) find(CNName);
		if (otherData != null) {
			acd.setkStrand(otherData.getkStrand());
			acd.setDiaStrand(otherData.getDiaStrand());
			acd.setGmrStrand(otherData.getGmrStrand());
			acd.setRStrand(otherData.getRStrand());
			classMakeLike(otherData);
			for (int i = 0; i < acd.getParentClass().getNumProperties(); i++)
				acd.setPropertyValue(i, otherData.getPropertyValue(i));
			result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in concentric neutral makeLike: \"" + CNName + "\" not found.", 102);
		}
		return result;
	}

	@Override
	public int init(int handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement CNData.init().", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		return ((CNDataObj) elementList.getActive()).getName();
	}

	/**
	 * Sets the active CNData.
	 */
	public void setCode(String value) {
		ConductorDataImpl.setActiveConductorDataObj(null);
		CNDataObj pCNDataObj = (CNDataObj) elementList.getFirst();
		while (pCNDataObj != null) {
			if (pCNDataObj.getName().equalsIgnoreCase(value)) {
				ConductorDataImpl.setActiveConductorDataObj(pCNDataObj);
				return;
			}
			pCNDataObj = (CNDataObj) elementList.getNext();
		}
		DSSGlobals.getInstance().doSimpleMsg("CNData: \"" + value + "\" not found.", 103);
	}

}
