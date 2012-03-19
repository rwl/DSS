/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.delivery;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.control.ControlClass;
import com.ncond.dss.general.TCC_CurveObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Fuse extends ControlClass {

	public static final int FUSEMAXDIM = 6;

	public static final int NumPropsThisClass = 8;

	public static FuseObj activeFuseObj;

	private static DSSClass TCC_CurveClass;

	public Fuse() {
		super();

		className = "Fuse";
		classType = getClassType() + DSSClassDefs.FUSE_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);

		TCC_CurveClass = DSSClassDefs.getDSSClass("TCC_Curve");
	}

	/**
	 * General module function
	 */
	public static TCC_CurveObj getTccCurve(String curveName) {
		TCC_CurveObj curve = (TCC_CurveObj) TCC_CurveClass.find(curveName);

		if (curve == null)
			DSS.doSimpleMsg("TCC Curve object: \"" + curveName + "\" not found.", 401);

		return curve;
	}

	@Override
	protected void defineProperties() {
		numProperties = Fuse.NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0]  = "MonitoredObj";
		propertyName[1]  = "MonitoredTerm";
		propertyName[2]  = "SwitchedObj";
		propertyName[3]  = "SwitchedTerm";
		propertyName[4]  = "FuseCurve";
		propertyName[5] = "RatedCurrent";
		propertyName[6] = "Delay";
		propertyName[7] = "Action";

		propertyHelp[0] = "Full object name of the circuit element, typically a line, transformer, load, or generator, "+
				"to which the Fuse is connected." +
				" This is the \"monitored\" element. " +
				"There is no default; must be specified.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the Fuse is connected. "+
				"1 or 2, typically.  Default is 1.";
		propertyHelp[2] = "Name of circuit element switch that the Fuse controls. "+
				"Specify the full object name." +
				"Defaults to the same as the Monitored element. "+
				"This is the \"controlled\" element.";
		propertyHelp[3] = "Number of the terminal of the controlled element in which the switch is controlled by the Fuse. "+
				"1 or 2, typically.  Default is 1.  Assumes all phases of the element have a fuse of this type.";
		propertyHelp[4] = "Name of the TCC Curve object that determines the fuse blowing.  Must have been previously defined as a TCC_Curve object."+
				" Default is \"Tlink\". "+
				"Multiplying the current values in the curve by the \"RatedCurrent\" value gives the actual current.";
		propertyHelp[5] = "Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.";
		propertyHelp[6] = "Fixed delay time (sec) added to Fuse blowing time determined from the TCC curve. Default is 0.0. Used to represent fuse clearing time or any other delay." ;
		propertyHelp[7] = "{Trip/Open | Close}  Action that overrides the Fuse control. Simulates manual control on Fuse " +
				"\"Trip\" or \"Open\" causes the controlled element to open and lock out. " +
				"\"Close\" causes the controlled element to close and the Fuse to reset.";

		activeProperty  = Fuse.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new FuseObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeFuseObj = (FuseObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeFuseObj);

		FuseObj elem = activeFuseObj;

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
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() + "." + getClassName() + "\"", 402);
				break;
			case 0:
				elem.setMonitoredElementName(param.toLowerCase());
				break;
			case 1:
				elem.setMonitoredElementTerminalIdx(parser.makeInteger() - 1);
				break;
			case 2:
				elem.setElementName(param.toLowerCase());
				break;
			case 3:
				elem.setElementTerminalIdx(parser.makeInteger() - 1);
				break;
			case 4:
				elem.setFuseCurve(getTccCurve(param));
				break;
			case 5:
				elem.setRatedCurrent(parser.makeDouble());
				break;
			case 6:
				elem.setDelayTime(parser.makeDouble());
				break;
			case 7:
				elem.interpretFuseAction(param);
				break;
			default:
				// inherited parameters
				classEdit(activeFuseObj, paramPointer - Fuse.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			/* Default the controlled element to the monitored element */
			case 0:
				elem.setElementName(elem.getMonitoredElementName());
				break;
			case 1:
				elem.setElementTerminalIdx(elem.getMonitoredElementTerminalIdx());
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		elem.recalcElementData();

		return 0;
	}

	@Override
	protected int makeLike(String fuseName) {
		int success = 0;

		/* See if we can find this Fuse name in the present collection */
		FuseObj other = (FuseObj) find(fuseName);

		if (other != null) {
			FuseObj elem = activeFuseObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds()); // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setElementTerminalIdx(other.getElementTerminalIdx());
			elem.setControlledElement(other.getControlledElement());  // target circuit element

			elem.setMonitoredElement(other.getMonitoredElement());  // target circuit element
			elem.setMonitoredElementName(other.getMonitoredElementName());  // target circuit element
			elem.setMonitoredElementTerminalIdx(other.getMonitoredElementTerminalIdx());  // target circuit element

			elem.setFuseCurve(other.getFuseCurve());
			elem.setRatedCurrent(other.getRatedCurrent());

			// can't copy action handles
			elem.setPresentState(other.getPresentState());
			elem.setCondOffset(other.getCondOffset());

			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));

		} else {
			DSS.doSimpleMsg("Error in Fuse makeLike: \"" + fuseName + "\" not found.", 403);
		}

		return success;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Fuse.init", -1);
		return 0;
	}

}
