package com.epri.dss.control.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.SwtControl;
import com.epri.dss.control.SwtControlObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class SwtControlImpl extends ControlClassImpl implements SwtControl {

	private static SwtControlObj ActiveSwtControlObj;

	public SwtControlImpl() {
		super();

		this.Class_Name = "SwtControl";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.SWT_CONTROL;

		defineProperties();

		String[] Commands = new String[this.NumProperties];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = SwtControl.NumPropsThisClass;
		countProperties();  // Get inherited property count

		allocatePropertyArrays();

		PropertyName[0] = "SwitchedObj";
		PropertyName[1] = "SwitchedTerm";
		PropertyName[2] = "Action";
		PropertyName[3] = "Lock";
		PropertyName[4] = "Delay";

		PropertyHelp[0] = "Name of circuit element switch that the SwtControl operates. "+
				"Specify the full object class and name.";
		PropertyHelp[1] = "Terminal number of the controlled element switch. " +
				"1 or 2, typically.  Default is 1.";
		PropertyHelp[2] = "{Open | Close}  simulates manual operation of the controlled switch to open or close, after a time delay. " +
				"Note: automatic operation requires use of the COM interface with an external control algorithm.";
		PropertyHelp[3] = "Controlled switch is locked in its present open / close state. " +
				"Switch will not respond to either manual (Action) or automatic (COM interface) control until this Lock is removed.";
		PropertyHelp[4] = "Operating time delay (sec) of the switch. Defaults to 120.";

		ActiveProperty = SwtControl.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new SwtControlObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveSwtControlObj((SwtControlObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveSwtControlObj());

		int Result = 0;

		SwtControlObj asc = getActiveSwtControlObj();

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
				asc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ asc.getName() + "\"", 382);
			case 0:
				asc.setElementName(Param.toLowerCase());
			case 1:
				asc.setElementTerminal(parser.makeInteger());
			case 2:
				asc.interpretSwitchAction(Param);
			case 3:
				asc.setLocked( Utilities.interpretYesNo(Param) );
			case 4:
				asc.setTimeDelay(parser.makeDouble());
			default:
				// Inherited parameters
				classEdit(ActiveSwtControlObj, ParamPointer - SwtControl.NumPropsThisClass);
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		asc.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String SwtControlName) {

		int Result = 0;
		/* See if we can find this SwtControl name in the present collection */
		SwtControlObj OtherSwtControl = (SwtControlObj) find(SwtControlName);
		if (OtherSwtControl != null) {
			SwtControlObj asc = getActiveSwtControlObj();

			asc.setNPhases(OtherSwtControl.getNPhases());
			asc.setNConds(OtherSwtControl.getNConds());  // Force reallocation of terminal stuff

			asc.setElementName(OtherSwtControl.getElementName());
			asc.setElementTerminal(OtherSwtControl.getElementTerminal());
			asc.setControlledElement(OtherSwtControl.getControlledElement());  // Pointer to target circuit element

			asc.setTimeDelay(OtherSwtControl.getTimeDelay());
			asc.setLocked(OtherSwtControl.isLocked());
			asc.setPresentState(OtherSwtControl.getPresentState());

			for (int i = 0; i < asc.getParentClass().getNumProperties(); i++)
				asc.setPropertyValue(i, OtherSwtControl.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in SwtControl makeLike: \"" + SwtControlName + "\" Not Found.", 383);
		}

		return Result;
	}

	public static void setActiveSwtControlObj(SwtControlObj activeSwtControlObj) {
		ActiveSwtControlObj = activeSwtControlObj;
	}

	public static SwtControlObj getActiveSwtControlObj() {
		return ActiveSwtControlObj;
	}

}
