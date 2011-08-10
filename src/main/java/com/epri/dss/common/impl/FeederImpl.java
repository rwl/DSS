package com.epri.dss.common.impl;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.Feeder;
import com.epri.dss.common.FeederObj;
import com.epri.dss.conversion.impl.PCClassImpl;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class FeederImpl extends PCClassImpl implements Feeder {

	private static int NumPropsThisClass = 0;

	private static FeederObj ActiveFeederObj;

	public FeederImpl() {
		super();
		this.Class_Name = "Feeder";
		this.DSSClassType = DSSClassDefs.FEEDER_ELEMENT; /*+ PC_ELEMENT; */ // add to PCElement list

		setActiveElement(-1);

		defineProperties();

		String[] Commands = new String[NumProperties];
		System.arraycopy(PropertyName, 0, Commands, 0, NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {
		NumPropsThisClass = 0;

		NumProperties = NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

// Can't think of any properties we want the user to be able to set

		// Define Property names
//		PropertyName[0] = "bus1";

		// define Property help values
//		PropertyHelp[0] = "Name of bus to which source is connected."+DSSGlobals.CRLF+"bus1=busname"+DSSGlobals.CRLF+"bus1=busname.1.2.3";


		ActiveProperty = NumPropsThisClass - 1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	/**
	 * Called from EnergyMeter.
	 */
	@Override
	public int newObject(String ObjName) {
		int Result;

		// Make a new Feeder object
		// First see if this one already exists. If so, just reinitialize
		FeederObj Obj = (FeederObj) find(ObjName);

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (Obj != null) {
			ckt.setActiveCktElement((DSSCktElement) Obj);
			Result = 0;
		} else {
			ckt.setActiveCktElement(new FeederObjImpl(this, ObjName));
			Result = addObjectToList(DSSGlobals.getInstance().getActiveDSSObject());
			ckt.addCktElement(Result);
			// done here because feeder objects are instantiated from energy meters
		}

		return Result;
	}

	@Override
	public int edit() {
		// continue parsing with contents of Parser
		ActiveFeederObj = (FeederObj) ElementList.getActive();
		DSSGlobals.getInstance().getActiveCircuit().setActiveCktElement((DSSCktElement) ActiveFeederObj);

		int Result = 0;

		int ParamPointer = 0;
		String ParamName = Parser.getInstance().getNextParam();
		String Param = Parser.getInstance().makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
				ActiveFeederObj.setPropertyValue(ParamPointer, Param);

				switch (ParamPointer) {
				case 0:
					DSSGlobals.getInstance().doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ ActiveFeederObj.getName() + "\"", 630);
					break;
				default:
					classEdit(ActiveFeederObj, ParamPointer - NumPropsThisClass);
					break;
				}

				ParamName = Parser.getInstance().getNextParam();
				Param     = Parser.getInstance().makeString();
		}

		ActiveFeederObj.recalcElementData();
		ActiveFeederObj.setYprimInvalid(true);

		return Result;
	}

	@Override
	protected int makeLike(String OtherFeederName) {
		int Result = 0;

		/* See if we can find this name in the present collection */
		FeederObj OtherFeeder = (FeederObj) find(OtherFeederName);
		if (OtherFeeder != null) {
			if (ActiveFeederObj.getNPhases() != OtherFeeder.getNPhases()) {
				ActiveFeederObj.setNPhases(OtherFeeder.getNPhases());
				ActiveFeederObj.setNConds(ActiveFeederObj.getNPhases());  // Forces reallocation of terminal stuff

				ActiveFeederObj.setYorder(ActiveFeederObj.getNConds() * ActiveFeederObj.getNTerms());
				ActiveFeederObj.setYprimInvalid(true);
			}

			// Put properties to copy here

			classMakeLike(OtherFeeder);  // set spectrum, base frequency

			for (int i = 0; i < ActiveFeederObj.getParentClass().getNumProperties(); i++) {
				ActiveFeederObj.setPropertyValue(i, OtherFeeder.getPropertyValue(i));
			}
			Result = 1;
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in Feeder MakeLike: \"" + OtherFeederName + "\" Not Found.", 631);
		}

		return Result;
	}

	@Override
	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement Feeder.init()", -1);
		return 0;
	}

	public static int getNumPropsThisClass() {
		return NumPropsThisClass;
	}

	public static void setNumPropsThisClass(int numPropsThisClass) {
		NumPropsThisClass = numPropsThisClass;
	}

	public static FeederObj getActiveFeederObj() {
		return ActiveFeederObj;
	}

	public static void setActiveFeederObj(FeederObj activeFeederObj) {
		ActiveFeederObj = activeFeederObj;
	}

}
