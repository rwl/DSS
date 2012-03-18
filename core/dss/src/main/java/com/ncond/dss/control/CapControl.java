/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

public class CapControl extends ControlClass {

	public static CapControlObj activeCapControlObj;

	public static final int AVGPHASES = -1;
	public static final int MAXPHASE  = -2;
	public static final int MINPHASE  = -3;

	public static final int SRPINHIBITRELEASE = 222; // just some unused number

	public static final int NumPropsThisClass = 18;

	public CapControl() {
		super();

		className = "CapControl";
		classType = classType + DSSClassDefs.CAP_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = CapControl.NumPropsThisClass;

		countProperties();   // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "element";
		propertyName[1] = "terminal";
		propertyName[2] = "capacitor";
		propertyName[3] = "type";
		propertyName[4] = "PTratio";
		propertyName[5] = "CTratio";
		propertyName[6] = "ONsetting";
		propertyName[7] = "OFFsetting";
		propertyName[8] = "Delay";
		propertyName[9] = "VoltOverride";
		propertyName[10] = "Vmax";
		propertyName[11] = "Vmin";
		propertyName[12] = "DelayOFF";
		propertyName[13] = "DeadTime";
		propertyName[14] = "CTPhase";
		propertyName[15] = "PTPhase";
		propertyName[16] = "VBus";
		propertyName[17] = "EventLog";

		propertyHelp[0] = "Full object name of the circuit element, typically a line or transformer, "+
				"to which the capacitor control's PT and/or CT are connected." +
				"There is no default; must be specified.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the CapControl is connected. "+
				"1 or 2, typically.  Default is 1.";
		propertyHelp[2] = "Name of Capacitor element which the CapControl controls. No Default; Must be specified."+
				"Do not specify the full object name; \"Capacitor\" is assumed for "  +
				"the object class.  Example:"+DSS.CRLF+DSS.CRLF+
				"Capacitor=cap1";
		propertyHelp[3] = "{Current | voltage | kvar | PF | time } Control type.  Specify the ONsetting and OFFsetting " +
				"appropriately with the type of control. (See help for ONsetting)";
		propertyHelp[4] = "Ratio of the PT that converts the monitored voltage to the control voltage. "+
				"Default is 60.  If the capacitor is Wye, the 1st phase line-to-neutral voltage is monitored.  Else, the line-to-line " +
				"voltage (1st - 2nd phase) is monitored.";
		propertyHelp[5] = "Ratio of the CT from line amps to control ampere setting for current and kvar control types. ";
		propertyHelp[6] = "Value at which the control arms to switch the capacitor ON (or ratchet up a step).  " + DSS.CRLF+DSS.CRLF +
				"Type of Control:"+DSS.CRLF+DSS.CRLF+
				"Current: Line Amps / CTratio"+DSS.CRLF+
				"Voltage: Line-Neutral (or Line-Line for delta) Volts / PTratio" +DSS.CRLF+
				"kvar:    Total kvar, all phases (3-phase for pos seq model). This is directional. " + DSS.CRLF +
				"PF:      Power Factor, Total power in monitored terminal. Negative for Leading. " + DSS.CRLF +
				"Time:    Hrs from Midnight as a floating point number (decimal). 7:30am would be entered as 7.5.";
		propertyHelp[7] = "Value at which the control arms to switch the capacitor OFF. (See help for ONsetting)" +
				"For Time control, is OK to have Off time the next day ( < On time)";
		propertyHelp[8] = "Time delay, in seconds, from when the control is armed before it sends out the switching " +
				"command to turn ON.  The control may reset before the action actually occurs. " +
				"This is used to determine which capacity control will act first. Default is 15.  You may specify any "+
				"floating point number to achieve a model of whatever condition is necessary.";
		propertyHelp[9] = "{Yes | No}  Default is No.  Switch to indicate whether VOLTAGE OVERRIDE is to be considered. " +
				"Vmax and Vmin must be set to reasonable values if this property is Yes.";
		propertyHelp[10] = "Maximum voltage, in volts.  If the voltage across the capacitor divided by the PTRATIO is greater " +
				"than this voltage, the capacitor will switch OFF regardless of other control settings. " +
				"Default is 126 (goes with a PT ratio of 60 for 12.47 kV system).";
		propertyHelp[11] = "Minimum voltage, in volts.  If the voltage across the capacitor divided by the PTRATIO is less " +
				"than this voltage, the capacitor will switch ON regardless of other control settings. "+
				"Default is 115 (goes with a PT ratio of 60 for 12.47 kV system).";
		propertyHelp[12] = "Time delay, in seconds, for control to turn OFF when present state is ON. Default is 15.";
		propertyHelp[13] = "Dead time after capacitor is turned OFF before it can be turned back ON. Default is 300 sec.";
		propertyHelp[14] = "Number of the phase being monitored for CURRENT control or one of {AVG | MAX | MIN} for all phases. Default=1. " +
				"If delta or L-L connection, enter the first or the two phases being monitored [1-2, 2-3, 3-1]. " +
				"Must be less than the number of phases. Does not apply to kvar control which uses all phases by default.";
		propertyHelp[15] = "Number of the phase being monitored for VOLTAGE control or one of {AVG | MAX | MIN} for all phases. Default=1. " +
				"If delta or L-L connection, enter the first or the two phases being monitored [1-2, 2-3, 3-1]. " +
				"Must be less than the number of phases. Does not apply to kvar control which uses all phases by default.";
		propertyHelp[16] = "Name of bus to use for voltage override function. Default is bus at monitored terminal. " +
				"Sometimes it is useful to monitor a bus in another location to emulate various DMS control algorithms.";
		propertyHelp[17] = "{Yes/True* | No/False} Default is YES for CapControl. Log control actions to Eventlog.";

		activeProperty = CapControl.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSS.activeCircuit.setActiveCktElement(new CapControlObj(this, ObjName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		CapControlObj elem;
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		elem = activeCapControlObj = (CapControlObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeCapControlObj);

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < numProperties))
				elem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" +
						getClassName() +"."+ elem.getName() + "\"", 352);
				break;
			case 0:
				elem.setElementName(param.toLowerCase());
				break;
			case 1:
				elem.setElementTerminalIdx(parser.makeInteger());
				break;
			case 2:
				elem.setCapacitorName("capacitor." + param);
				break;
			case 3:
				switch (param.toLowerCase().charAt(0)) {
				case 'c':
					elem.setControlType(CapControlType.CURRENT);
					break;
				case 'v':
					elem.setControlType(CapControlType.VOLTAGE);
					break;
				case 'k':
					elem.setControlType(CapControlType.KVAR);
					break;
				case 't':
					elem.setControlType(CapControlType.TIME);
					break;
				case 'p':
					elem.setControlType(CapControlType.PF);
					break;
				case 's':
					elem.setControlType(CapControlType.SRP);
					break;
				default:
					DSS.doSimpleMsg(String.format("Unrecognized CapControl type: \"%s\" (CapControl.%s)",
							param, elem.getName()), 352);
					break;
				}
				break;
			case 4:
				elem.setPTRatio(parser.makeDouble());
				break;
			case 5:
				elem.setCTRatio(parser.makeDouble());
				break;
			case 6:
				elem.setOnValue(parser.makeDouble());
				break;
			case 7:
				elem.setOffValue(parser.makeDouble());
				break;
			case 8:
				elem.setOnDelay(parser.makeDouble());
				break;
			case 9:
				elem.setVOverride(Util.interpretYesNo(param));
				break;
			case 10:
				elem.setVMax(parser.makeDouble());
				break;
			case 11:
				elem.setVMin(parser.makeDouble());
				break;
			case 12:
				elem.setOffDelay(parser.makeDouble());
				break;
			case 13:
				elem.setDeadTime(parser.makeDouble());
				break;
			case 14:
				if (Util.compareTextShortest(param, "avg") == 0) {
					elem.setCTPhaseIdx(CapControl.AVGPHASES);
				} else if (Util.compareTextShortest(param, "max") == 0) {
					elem.setCTPhaseIdx(CapControl.MAXPHASE);
				} else if (Util.compareTextShortest(param, "min") == 0) {
					elem.setCTPhaseIdx(CapControl.MINPHASE);
				} else {
					elem.setCTPhaseIdx(Math.max(0, parser.makeInteger() - 1));
				}
				break;
			case 15:
				if (Util.compareTextShortest(param, "avg") == 0) {
					elem.setPTPhaseIdx(CapControl.AVGPHASES);
				} else if (Util.compareTextShortest(param, "max") == 0) {
					elem.setPTPhaseIdx(CapControl.MAXPHASE);
				} else if (Util.compareTextShortest(param, "min") == 0) {
					elem.setPTPhaseIdx(CapControl.MINPHASE);
				} else {
					elem.setPTPhaseIdx(Math.max(0, parser.makeInteger() - 1));
				}
				break;
			case 16:
				elem.setVOverrideBusSpecified(true);
				elem.setVOverrideBusName(param);
			case 17:
				elem.setShowEventLog(Util.interpretYesNo(param));
			default:
				// inherited parameters
				classEdit(elem, paramPointer - CapControl.NumPropsThisClass);
				break;
			}

			/* PF controller changes */
			if (elem.getControlType() == CapControlType.PF) {
				switch (paramPointer) {
				case 3:
					elem.setPFOnValue(0.95);  // defaults
					elem.setPFOffValue(1.05);
					break;
				case 6:
					if ((elem.getOnValue() >= -1.0) && (elem.getOnValue() <= 1.0)) {
						if (elem.getOnValue() < 0.0) {
							elem.setPFOnValue(2.0 + elem.getOnValue());
						} else {
							elem.setPFOnValue(elem.getOnValue());
						}
					} else {
						DSS.doSimpleMsg("Invalid PF on value for CapControl." + elem.getName(), 353);
					}
					break;
				case 7:
					if ((elem.getOffValue() >= -1.0) && (elem.getOffValue() <= 1.0)) {
						if (elem.getOffValue() < 0.0) {
							elem.setPFOffValue(2.0 + elem.getOffValue());
						} else {
							elem.setPFOffValue(elem.getOffValue());
						}
					} else {
						DSS.doSimpleMsg("Invalid PF off value for CapControl." + elem.getName(), 35301);
					}
					break;
				case 14:
					if (elem.getCTPhaseIdx() >= elem.getNumPhases()) {
						DSS.doSimpleMsg(String.format("Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ",
							elem.getCTPhaseIdx() + 1, elem.getNumPhases()), 35302);
						elem.setCTPhaseIdx(0);
					}
					break;
				case 15:
					if (elem.getPTPhaseIdx() >= elem.getNumPhases()) {
						DSS.doSimpleMsg(String.format("Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ",
								elem.getPTPhaseIdx() + 1, elem.getNumPhases()), 35303);
						elem.setPTPhaseIdx(0);
					}
					break;
				}
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		elem.recalcElementData();

		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement CapControl.init", -1);
		return 0;
	}

	@Override
	protected int makeLike(String capControlName) {
		int i;

		/* See if we can find this CapControl name in the present collection */
		CapControlObj other = (CapControlObj) find(capControlName);
		if (other != null) {
			CapControlObj elem = activeCapControlObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setCapacitorName(other.getCapacitorName());
			elem.setControlledElement(other.getControlledElement());  // target circuit element
			elem.setMonitoredElement(other.getMonitoredElement());    // target circuit element

			elem.setElementTerminalIdx(other.getElementTerminalIdx());
			elem.setPTRatio(other.getPTRatio());
			elem.setCTRatio(other.getCTRatio());
			elem.setControlType(other.getControlType());
			elem.setPresentState(other.getPresentState());
			elem.setShouldSwitch(other.isShouldSwitch());
			elem.setCondOffset(other.getCondOffset());

			elem.setOnValue(other.getOnValue());
			elem.setOffValue(other.getOffValue());
			elem.setPFOnValue(other.getPFOnValue());
			elem.setPFOffValue(other.getPFOffValue());

			elem.setCTPhaseIdx(other.getCTPhaseIdx());
			elem.setPTPhaseIdx(other.getPTPhaseIdx());

			elem.setVOverride(other.isVOverride());
			elem.setVOverrideBusSpecified(other.isVOverrideBusSpecified());
			elem.setVOverrideBusName(other.getVOverrideBusName());

		        elem.setShowEventLog(other.isShowEventLog());

			for (i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in CapControl makeLike: \"" + capControlName + "\" not found.", 360);
		}

		return 0;
	}

}
