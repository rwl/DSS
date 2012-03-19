/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.general.TCC_CurveObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class Recloser extends ControlClass {

	public final static int NumPropsThisClass = 22;

	public static RecloserObj activeRecloserObj;

	private static DSSClass TCC_CurveClass = DSSClassDefs.getDSSClass("TCC_Curve");

	public Recloser() {
		super();

		className = "Recloser";
		classType = classType + DSSClassDefs.RECLOSER_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	public static TCC_CurveObj getTCC_Curve(String curveName) {
		TCC_CurveObj curve = (TCC_CurveObj) TCC_CurveClass.find(curveName);

		if (curve == null)
			DSS.doSimpleMsg("TCC curve object: \"" + curveName + "\" not found.", 388);

		return curve;
	}

	@Override
	protected void defineProperties() {
		numProperties = NumPropsThisClass;
		countProperties();  // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[0]  = "MonitoredObj";
		propertyName[1]  = "MonitoredTerm";
		propertyName[2]  = "SwitchedObj";
		propertyName[3]  = "SwitchedTerm";
		propertyName[4]  = "NumFast";
		propertyName[5]  = "PhaseFast";
		propertyName[6]  = "PhaseDelayed";
		propertyName[7]  = "GroundFast";
		propertyName[8]  = "GroundDelayed";
		propertyName[9]  = "PhaseTrip";
		propertyName[10] = "GroundTrip";
		propertyName[11] = "PhaseInst";
		propertyName[12] = "GroundInst";
		propertyName[13] = "Reset";
		propertyName[14] = "Shots";
		propertyName[15] = "RecloseIntervals";
		propertyName[16] = "Delay";
		propertyName[17] = "Action";
		propertyName[18] = "TDPhFast";
		propertyName[19] = "TDGrFast";
		propertyName[20] = "TDPhDelayed";
		propertyName[21] = "TDGrDelayed";

		propertyHelp[0] = "Full object name of the circuit element, typically a line, transformer, load, or generator, "+
				"to which the Recloser's PT and/or CT are connected." +
				" This is the \"monitored\" element. " +
				"There is no default; must be specified.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the Recloser is connected. "+
				"1 or 2, typically.  Default is 1.";
		propertyHelp[2] = "Name of circuit element switch that the Recloser controls. "+
				"Specify the full object name." +
				"Defaults to the same as the Monitored element. "+
				"This is the \"controlled\" element.";
		propertyHelp[3] = "Number of the terminal of the controlled element in which the switch is controlled by the Recloser. "+
				"1 or 2, typically.  Default is 1.";
		propertyHelp[4] = "Number of Fast (fuse saving) operations.  Default is 1. (See \"Shots\")";
		propertyHelp[5] = "Name of the TCC Curve object that determines the Phase Fast trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is \"A\". "+
				"Multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.";
		propertyHelp[6] = "Name of the TCC Curve object that determines the Phase Delayed trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is \"D\"."+
				"Multiplying the current values in the curve by the \"phasetrip\" value gives the actual current.";
		propertyHelp[7] = "Name of the TCC Curve object that determines the Ground Fast trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is none (ignored). "+
				"Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.";
		propertyHelp[8] = "Name of the TCC Curve object that determines the Ground Delayed trip.  Must have been previously defined as a TCC_Curve object."+
				" Default is none (ignored)."+
				"Multiplying the current values in the curve by the \"groundtrip\" value gives the actual current.";
		propertyHelp[9] = "Multiplier or actual phase amps for the phase TCC curve.  Defaults to 1.0.";
		propertyHelp[10] = "Multiplier or actual ground amps (3I0) for the ground TCC curve.  Defaults to 1.0.";
		propertyHelp[11] = "Actual amps for instantaneous phase trip which is assumed to happen in 0.01 sec + Delay Time. Default is 0.0, which signifies no inst trip. ";
		propertyHelp[12] = "Actual amps for instantaneous ground trip which is assumed to happen in 0.01 sec + Delay Time.Default is 0.0, which signifies no inst trip.";
		propertyHelp[13] = "Reset time in sec for Recloser.  Default is 15. ";
		propertyHelp[14] = "Total Number of fast and delayed shots to lockout.  Default is 4. This is one more than the number of reclose intervals.";
		propertyHelp[15] = "Array of reclose intervals.  Default for Recloser is (0.5, 2.0, 2.0) seconds. " +
				"A locked out Recloser must be closed manually (action=close).";
		propertyHelp[16] = "Fixed delay time (sec) added to Recloser trip time. Default is 0.0. Used to represent breaker time or any other delay." ;
		propertyHelp[17] = "{Trip/Open | Close}  Action that overrides the Recloser control. Simulates manual control on recloser " +
				"\"Trip\" or \"Open\" causes the controlled element to open and lock out. " +
				"\"Close\" causes the controlled element to close and the Recloser to reset to its first operation.";
		propertyHelp[18] = "Time dial for Phase Fast trip curve. Multiplier on time axis of specified curve. Default=1.0.";
		propertyHelp[19] = "Time dial for Ground Fast trip curve. Multiplier on time axis of specified curve. Default=1.0.";
		propertyHelp[20] = "Time dial for Phase Delayed trip curve. Multiplier on time axis of specified curve. Default=1.0.";
		propertyHelp[21] = "Time dial for Ground Delayed trip curve. Multiplier on time axis of specified curve. Default=1.0.";

		activeProperty = Recloser.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new RecloserObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeRecloserObj = (RecloserObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeRecloserObj);

		RecloserObj elem = activeRecloserObj;

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
						getClassName() +"."+ elem.getName() +"\"", 390);
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
				elem.setNumFast(parser.makeInteger());
				break;
			case 5:
				elem.setPhaseFast(getTCC_Curve(param));
				break;
			case 6:
				elem.setPhaseDelayed(getTCC_Curve(param));
				break;
			case 7:
				elem.setGroundFast(getTCC_Curve(param));
				break;
			case 8:
				elem.setGroundDelayed(getTCC_Curve(param));
				break;
			case 9:
				elem.setPhaseTrip(parser.makeDouble());
				break;
			case 10:
				elem.setGroundTrip(parser.makeDouble());
				break;
			case 11:
				elem.setPhaseInst(parser.makeDouble());
				break;
			case 12:
				elem.setGroundInst(parser.makeDouble());
				break;
			case 13:
				elem.setResetTime(parser.makeDouble());
				break;
			case 14:
				elem.setNumReclose(parser.makeInteger() - 1);  // one less than number of shots
				break;
			case 15:
				elem.setNumReclose(parser.parseAsVector(4, elem.getRecloseIntervals()));  // max of 4 allowed
				break;
			case 16:
				elem.setDelayTime(parser.makeDouble());
				break;
			case 17:
				elem.interpretRecloserAction(param);
				break;
			case 18:
				elem.setTDPhFast(parser.makeDouble());
				break;
			case 19:
				elem.setTDGrFast(parser.makeDouble());
				break;
			case 20:
				elem.setTDPhDelayed(parser.makeDouble());
				break;
			case 21:
				elem.setTDGrDelayed(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(activeRecloserObj, paramPointer - NumPropsThisClass);
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
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement Recloser.init", -1);
		return 0;
	}

	@Override
	protected int makeLike(String recloserName) {
		int i;

		/* See if we can find this Recloser name in the present collection */
		RecloserObj other = (RecloserObj) find(recloserName);
		if (other != null) {
			RecloserObj elem = activeRecloserObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setElementTerminalIdx(other.getElementTerminalIdx());
			elem.setControlledElement(other.getControlledElement());  // pointer to target circuit element

			elem.setMonitoredElement(other.getMonitoredElement());  // pointer to target circuit element
			elem.setMonitoredElementName(other.getMonitoredElementName());  // pointer to target circuit element
			elem.setMonitoredElementTerminalIdx(other.getMonitoredElementTerminalIdx());  // pointer to target circuit element

			elem.setPhaseDelayed(other.getPhaseDelayed());
			elem.setGroundDelayed(other.getGroundDelayed());
			elem.setPhaseFast(other.getPhaseFast());
			elem.setGroundFast(other.getGroundFast());
			elem.setPhaseTrip(other.getPhaseTrip());
			elem.setGroundTrip(other.getGroundTrip());
			elem.setPhaseInst(other.getPhaseInst());
			elem.setGroundInst(other.getGroundInst());
			elem.setResetTime(other.getResetTime());
			elem.setNumReclose(other.getNumReclose());
			elem.setNumFast(other.getNumFast());

			elem.setRecloseIntervals(Util.resizeArray(elem.getRecloseIntervals(), 4));  // always make a max of 4
			for (i = 0; i < elem.getNumReclose(); i++)
				elem.getRecloseIntervals()[i] = other.getRecloseIntervals()[i];

			elem.setLockedOut(other.isLockedOut());

			elem.setPresentState(other.getPresentState());
			elem.setCondOffset(other.getCondOffset());

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in Recloser makeLike: \"" + recloserName + "\" not found.", 391);
		}

		return 0;
	}

}
