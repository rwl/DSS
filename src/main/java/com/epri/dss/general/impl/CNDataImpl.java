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
		this.Class_Name = "CNData";
		this.DSSClassType = DSSClassDefs.DSS_OBJECT;
		this.ActiveElement = -1;

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		NumProperties = CNData.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

		PropertyName[0] = "k";
		PropertyName[1] = "DiaStrand";
		PropertyName[2] = "GmrStrand";
		PropertyName[3] = "Rstrand";

		PropertyHelp[0] = "Number of concentric neutral strands; default is 2";
		PropertyHelp[1] = "Diameter of a concentric neutral strand; same units as core conductor radius; no default.";
		PropertyHelp[2] = "Geometric mean radius of a concentric neutral strand; same units as core conductor GMR; defaults to 0.7788 * CN strand radius.";
		PropertyHelp[3] = "AC resistance of a concentric neutral strand; same units as core conductor resistance; no default.";

		ActiveProperty = CNData.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Globals.setActiveDSSObject(new CNDataObjImpl(this, ObjName));
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
		ConductorDataImpl.setActiveConductorDataObj((ConductorDataObj) ElementList.getActive());
		Globals.setActiveDSSObject(ConductorDataImpl.getActiveConductorDataObj());

		CNDataObj acd = (CNDataObj) ConductorDataImpl.getActiveConductorDataObj();

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
				acd.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ getName() + "\"", 101);
			case 0:
				acd.setkStrand(parser.makeInteger());
			case 1:
				acd.setDiaStrand(parser.makeDouble());
			case 2:
				acd.setGmrStrand(parser.makeDouble());
			case 3:
				acd.setRStrand(parser.makeDouble());
			default:
				// Inherited parameters
				classEdit(ConductorDataImpl.getActiveConductorDataObj(), ParamPointer - CNData.NumPropsThisClass);
			}

			/* Set defaults */
			switch (ParamPointer) {
			case 1:
				if (acd.getGmrStrand() <= 0.0)
					acd.setGmrStrand(0.7788 * 0.5 * acd.getDiaStrand());
			}

			/* Check for critical errors */
			switch (ParamPointer) {
			case 0:
				if (acd.getkStrand() < 2)
					Globals.doSimpleMsg("Error: Must have at least 2 concentric neutral strands for CNData " + acd.getName(), 999);
			case 1:
				if (acd.getDiaStrand() <= 0.0)
					Globals.doSimpleMsg("Error: Neutral strand diameter must be positive for CNData " + acd.getName(), 999);
			case 2:
				if (acd.getGmrStrand() <= 0.0)
					Globals.doSimpleMsg("Error: Neutral strand GMR must be positive for CNData " + acd.getName(), 999);
			}
			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}
		return Result;
	}

	@Override
	protected int makeLike(String CNName) {
		CNDataObj acd = (CNDataObj) ConductorDataImpl.getActiveConductorDataObj();

		int Result = 0;
		CNDataObj OtherData = (CNDataObj) find(CNName);
		if (OtherData != null) {
			acd.setkStrand(OtherData.getkStrand());
			acd.setDiaStrand(OtherData.getDiaStrand());
			acd.setGmrStrand(OtherData.getGmrStrand());
			acd.setRStrand(OtherData.getRStrand());
			classMakeLike(OtherData);
			for (int i = 0; i < acd.getParentClass().getNumProperties(); i++)
				acd.setPropertyValue(i, OtherData.getPropertyValue(i));
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Concentric Neutral MakeLike: \"" + CNName + "\" Not Found.", 102);
		}
		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement CNData.init().", -1);
		return 0;
	}

	/**
	 * Returns active line code string.
	 */
	public String getCode() {
		return ((CNDataObj) ElementList.getActive()).getName();
	}

	/**
	 * Sets the active CNData.
	 */
	public void setCode(String Value) {
		ConductorDataImpl.setActiveConductorDataObj(null);
		CNDataObj pCNDataObj = (CNDataObj) ElementList.getFirst();
		while (pCNDataObj != null) {
			if (pCNDataObj.getName() == Value) {
				ConductorDataImpl.setActiveConductorDataObj(pCNDataObj);
				return;
			}
			pCNDataObj = (CNDataObj) ElementList.getNext();
		}
		DSSGlobals.getInstance().doSimpleMsg("CNData: \"" + Value + "\" not Found.", 103);
	}

}
