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

		this.className = "SwtControl";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.SWT_CONTROL;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = SwtControl.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		propertyName[0] = "SwitchedObj";
		propertyName[1] = "SwitchedTerm";
		propertyName[2] = "Action";
		propertyName[3] = "Lock";
		propertyName[4] = "Delay";

		propertyHelp[0] = "Name of circuit element switch that the SwtControl operates. "+
				"Specify the full object class and name.";
		propertyHelp[1] = "Terminal number of the controlled element switch. " +
				"1 or 2, typically.  Default is 1.";
		propertyHelp[2] = "{Open | Close}  simulates manual operation of the controlled switch to open or close, after a time delay. " +
				"Note: automatic operation requires use of the COM interface with an external control algorithm.";
		propertyHelp[3] = "Controlled switch is locked in its present open / close state. " +
				"Switch will not respond to either manual (Action) or automatic (COM interface) control until this Lock is removed.";
		propertyHelp[4] = "Operating time delay (sec) of the switch. Defaults to 120.";

		activeProperty = SwtControl.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
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
		setActiveSwtControlObj((SwtControlObj) elementList.getActive());
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
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < numProperties))
				asc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ asc.getName() + "\"", 382);
				break;
			case 0:
				asc.setElementName(Param.toLowerCase());
				break;
			case 1:
				asc.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				asc.interpretSwitchAction(Param);
				break;
			case 3:
				asc.setLocked( Utilities.interpretYesNo(Param) );
				break;
			case 4:
				asc.setTimeDelay(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(ActiveSwtControlObj, ParamPointer - SwtControl.NumPropsThisClass);
				break;
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
			asc.setNConds(OtherSwtControl.getNConds());  // force reallocation of terminal stuff

			asc.setElementName(OtherSwtControl.getElementName());
			asc.setElementTerminal(OtherSwtControl.getElementTerminal());
			asc.setControlledElement(OtherSwtControl.getControlledElement());  // pointer to target circuit element

			asc.setTimeDelay(OtherSwtControl.getTimeDelay());
			asc.setLocked(OtherSwtControl.isLocked());
			asc.setPresentState(OtherSwtControl.getPresentState());

			for (int i = 0; i < asc.getParentClass().getNumProperties(); i++)
				asc.setPropertyValue(i, OtherSwtControl.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in SwtControl makeLike: \"" + SwtControlName + "\" not found.", 383);
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
