package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class SwtControl extends ControlClass {

	public static final int NumPropsThisClass = 5;

	public static SwtControlObj activeSwtControlObj;

	public SwtControl() {
		super();

		className = "SwtControl";
		classType = classType + DSSClassDefs.SWT_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
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

	public int newObject(String objName) {

		DSS.activeCircuit.setActiveCktElement(new SwtControlObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeSwtControlObj = (SwtControlObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeSwtControlObj);

		int result = 0;

		SwtControlObj asc = activeSwtControlObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				asc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ asc.getName() + "\"", 382);
				break;
			case 0:
				asc.setElementName(param.toLowerCase());
				break;
			case 1:
				asc.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				asc.interpretSwitchAction(param);
				break;
			case 3:
				asc.setLocked( Util.interpretYesNo(param) );
				break;
			case 4:
				asc.setTimeDelay(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(activeSwtControlObj, paramPointer - SwtControl.NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		asc.recalcElementData();

		return result;
	}

	protected int makeLike(String swtControlName) {

		int result = 0;
		/* See if we can find this SwtControl name in the present collection */
		SwtControlObj otherSwtControl = (SwtControlObj) find(swtControlName);
		if (otherSwtControl != null) {
			SwtControlObj asc = activeSwtControlObj;

			asc.setNumPhases(otherSwtControl.getNumPhases());
			asc.setNumConds(otherSwtControl.getNumConds());  // force reallocation of terminal stuff

			asc.setElementName(otherSwtControl.getElementName());
			asc.setElementTerminal(otherSwtControl.getElementTerminal());
			asc.setControlledElement(otherSwtControl.getControlledElement());  // pointer to target circuit element

			asc.setTimeDelay(otherSwtControl.getTimeDelay());
			asc.setLocked(otherSwtControl.isLocked());
			asc.setPresentState(otherSwtControl.getPresentState());

			for (int i = 0; i < asc.getParentClass().getNumProperties(); i++)
				asc.setPropertyValue(i, otherSwtControl.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in SwtControl makeLike: \"" + swtControlName + "\" not found.", 383);
		}

		return result;
	}

	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement SwtControl.init", -1);
		return 0;
	}

}
