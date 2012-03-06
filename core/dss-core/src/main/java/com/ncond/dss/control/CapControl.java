package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;
import com.ncond.dss.control.CapControlObj.CapControlType;
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
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeCapControlObj = (CapControlObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeCapControlObj);

		int result = 0;

		CapControlObj acc = activeCapControlObj;

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
				acc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSS.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ acc.getName() + "\"", 352);
				break;
			case 0:
				acc.setElementName(param.toLowerCase());
				break;
			case 1:
				acc.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				acc.setCapacitorName("capacitor." + param);
				break;
			case 3:
				switch (param.toLowerCase().charAt(0)) {
				case 'c':
					acc.setControlType(CapControlType.CURRENT);
					break;
				case 'v':
					acc.setControlType(CapControlType.VOLTAGE);
					break;
				case 'k':
					acc.setControlType(CapControlType.KVAR);
					break;
				case 't':
					acc.setControlType(CapControlType.TIME);
					break;
				case 'p':
					acc.setControlType(CapControlType.PF);
					break;
				case 's':
					acc.setControlType(CapControlType.SRP);
					break;
				default:
					DSS.doSimpleMsg(String.format("Unrecognized CapControl type: \"%s\" (CapControl.%s)", param, acc.getName()), 352);
					break;
				}
				break;
			case 4:
				acc.setPTRatio(parser.makeDouble());
				break;
			case 5:
				acc.setCTRatio(parser.makeDouble());
				break;
			case 6:
				acc.setOnValue(parser.makeDouble());
				break;
			case 7:
				acc.setOffValue(parser.makeDouble());
				break;
			case 8:
				acc.setOnDelay(parser.makeDouble());
				break;
			case 9:
				acc.setVOverride(Util.interpretYesNo(param));
				break;
			case 10:
				acc.setVMax(parser.makeDouble());
				break;
			case 11:
				acc.setVMin(parser.makeDouble());
				break;
			case 12:
				acc.setOffDelay(parser.makeDouble());
				break;
			case 13:
				acc.setDeadTime(parser.makeDouble());
				break;
			case 14:
				if (Util.compareTextShortest(param, "avg") == 0) {
					acc.setCTPhase(CapControl.AVGPHASES);
				} else if (Util.compareTextShortest(param, "max") == 0) {
					acc.setCTPhase(CapControl.MAXPHASE);
				} else if (Util.compareTextShortest(param, "min") == 0) {
					acc.setCTPhase(CapControl.MINPHASE);
				} else {
					acc.setCTPhase( Math.max(1, parser.makeInteger()) );
				}
				break;
			case 15:
				if (Util.compareTextShortest(param, "avg") == 0) {
					acc.setPTPhase(CapControl.AVGPHASES);
				} else if (Util.compareTextShortest(param, "max") == 0) {
					acc.setPTPhase(CapControl.MAXPHASE);
				} else if (Util.compareTextShortest(param, "min") == 0) {
					acc.setPTPhase(CapControl.MINPHASE);
				} else {
					acc.setPTPhase( Math.max(1, parser.makeInteger()) );
				}
				break;
			case 16:
				acc.setVOverrideBusSpecified(true);
				acc.setVOverrideBusName(param);
			case 17:
				acc.setShowEventLog( Util.interpretYesNo(param) );
			default:
				// inherited parameters
				classEdit(activeCapControlObj, paramPointer - CapControl.NumPropsThisClass);
				break;
			}


			/* PF controller changes */
			if (acc.getControlType() == CapControlType.PF) {
				switch (paramPointer) {
				case 3:
					acc.setPFOnValue(0.95);  // defaults
					acc.setPFOffValue(1.05);
					break;
				case 6:
					if ((acc.getOnValue() >= -1.0) && (acc.getOnValue() <= 1.0)) {
						if (acc.getOnValue() < 0.0) {
							acc.setPFOnValue( 2.0 + acc.getOnValue());
						} else {
							acc.setPFOnValue(acc.getOnValue());
						}
					} else {
						DSS.doSimpleMsg("Invalid PF on value for CapControl."+acc.getName(), 353);
					}
					break;
				case 7:
					if ((acc.getOffValue() >= -1.0) && (acc.getOffValue() <= 1.0)) {
						if (acc.getOffValue() < 0.0) {
							acc.setPFOffValue(2.0 + acc.getOffValue());
						} else {
							acc.setPFOffValue(acc.getOffValue());
						}
					} else {
						DSS.doSimpleMsg("Invalid PF off value for CapControl."+acc.getName(), 35301);
					}
					break;
				case 14:
					if (acc.getCTPhase() > acc.getNumPhases()) {
						DSS.doSimpleMsg(String.format("Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ", acc.getCTPhase(), acc.getNumPhases()), 35302);
						acc.setCTPhase(1);
					}
					break;
				case 15:
					if (acc.getPTPhase() > acc.getNumPhases()) {
						DSS.doSimpleMsg(String.format("Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ", acc.getPTPhase(), acc.getNumPhases()), 35303);
						acc.setPTPhase(1);
					}
					break;
				}
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		acc.recalcElementData();

		return result;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement CapControl.init", -1);
		return 0;
	}

	@Override
	protected int makeLike(String capControlName) {
		int i, result = 0;

		/* See if we can find this CapControl name in the present collection */
		CapControlObj otherCapControl = (CapControlObj) find(capControlName);
		if (otherCapControl != null) {
			CapControlObj acc = activeCapControlObj;

			acc.setNumPhases(otherCapControl.getNumPhases());
			acc.setNumConds(otherCapControl.getNumConds());  // force reallocation of terminal stuff

			acc.setElementName(otherCapControl.getElementName());
			acc.setCapacitorName(otherCapControl.getCapacitorName());
			acc.setControlledElement(otherCapControl.getControlledElement());  // pointer to target circuit element
			acc.setMonitoredElement(otherCapControl.getMonitoredElement());    // pointer to target circuit element

			acc.setElementTerminal(otherCapControl.getElementTerminal());
			acc.setPTRatio(otherCapControl.getPTRatio());
			acc.setCTRatio(otherCapControl.getCTRatio());
			acc.setControlType(otherCapControl.getControlType());
			acc.setPresentState(otherCapControl.getPresentState());
			acc.setShouldSwitch(otherCapControl.isShouldSwitch());
			acc.setCondOffset(otherCapControl.getCondOffset());

			acc.setOnValue(otherCapControl.getOnValue());
			acc.setOffValue(otherCapControl.getOffValue());
			acc.setPFOnValue(otherCapControl.getPFOnValue());
			acc.setPFOffValue(otherCapControl.getPFOffValue());

			acc.setCTPhase(otherCapControl.getCTPhase());
			acc.setPTPhase(otherCapControl.getPTPhase());

			acc.setVOverride(otherCapControl.isVOverride());
			acc.setVOverrideBusSpecified(otherCapControl.isVOverrideBusSpecified());
			acc.setVOverrideBusName(otherCapControl.getVOverrideBusName());

		        acc.setShowEventLog(otherCapControl.isShowEventLog());

			for (i = 0; i < acc.getParentClass().getNumProperties(); i++)
				acc.setPropertyValue(i, otherCapControl.getPropertyValue(i));

		} else {
			DSS.doSimpleMsg("Error in CapControl makeLike: \"" + capControlName + "\" not found.", 360);
		}

		return result;
	}

}
