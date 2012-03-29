/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

import static com.ncond.dss.common.Util.interpretYesNo;


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

	@Override
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
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new SwtControlObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeSwtControlObj = (SwtControlObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeSwtControlObj);

		SwtControlObj elem = activeSwtControlObj;

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.stringValue();

		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 382);
				break;
			case 0:
				elem.setElementName(param.toLowerCase());
				break;
			case 1:
				elem.setElementTerminalIdx(parser.integerValue() - 1);
				break;
			case 2:
				elem.interpretSwitchAction(param);
				break;
			case 3:
				elem.setLocked( interpretYesNo(param) );
				break;
			case 4:
				elem.setTimeDelay(parser.doubleValue());
				break;
			default:
				// inherited parameters
				classEdit(activeSwtControlObj, paramPointer - NumPropsThisClass);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}

		elem.recalcElementData();

		return 0;
	}

	@Override
	protected int makeLike(String swtControlName) {
		/* See if we can find this SwtControl name in the present collection */
		SwtControlObj other = (SwtControlObj) find(swtControlName);

		if (other != null) {
			SwtControlObj elem = activeSwtControlObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setElementTerminalIdx(other.getElementTerminalIdx());
			elem.setControlledElement(other.getControlledElement());  // pointer to target circuit element

			elem.setTimeDelay(other.getTimeDelay());
			elem.setLocked(other.isLocked());
			elem.setPresentState(other.getPresentState());

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in SwtControl makeLike: \"" + swtControlName + "\" not found.", 383);
		}

		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement SwtControl.init", -1);
		return 0;
	}

}
