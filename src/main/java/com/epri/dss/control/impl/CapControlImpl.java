package com.epri.dss.control.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.CapControl;
import com.epri.dss.control.CapControlObj;
import com.epri.dss.control.impl.CapControlObjImpl.CapControlType;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class CapControlImpl extends ControlClassImpl implements CapControl {

	private static CapControlObj ActiveCapControlObj;

	public CapControlImpl() {
		super();

		this.className = "CapControl";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.CAP_CONTROL;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

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

		propertyHelp[0] = "Full object name of the circuit element, typically a line or transformer, "+
				"to which the capacitor control's PT and/or CT are connected." +
				"There is no default; must be specified.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the CapControl is connected. "+
				"1 or 2, typically.  Default is 1.";
		propertyHelp[2] = "Name of Capacitor element which the CapControl controls. No Default; Must be specified."+
				"Do not specify the full object name; \"Capacitor\" is assumed for "  +
				"the object class.  Example:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Capacitor=cap1";
		propertyHelp[3] = "{Current | voltage | kvar | PF | time } Control type.  Specify the ONsetting and OFFsetting " +
				"appropriately with the type of control. (See help for ONsetting)";
		propertyHelp[4] = "Ratio of the PT that converts the monitored voltage to the control voltage. "+
				"Default is 60.  If the capacitor is Wye, the 1st phase line-to-neutral voltage is monitored.  Else, the line-to-line " +
				"voltage (1st - 2nd phase) is monitored.";
		propertyHelp[5] = "Ratio of the CT from line amps to control ampere setting for current and kvar control types. ";
		propertyHelp[6] = "Value at which the control arms to switch the capacitor ON (or ratchet up a step).  " + DSSGlobals.CRLF+DSSGlobals.CRLF +
				"Type of Control:"+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Current: Line Amps / CTratio"+DSSGlobals.CRLF+
				"Voltage: Line-Neutral (or Line-Line for delta) Volts / PTratio" +DSSGlobals.CRLF+
				"kvar:    Total kvar, all phases (3-phase for pos seq model). This is directional. " + DSSGlobals.CRLF +
				"PF:      Power Factor, Total power in monitored terminal. Negative for Leading. " + DSSGlobals.CRLF +
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

		activeProperty = CapControl.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new CapControlObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveCapControlObj((CapControlObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveCapControlObj());

		int Result = 0;

		CapControlObj acc = getActiveCapControlObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = commandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer <= numProperties))
				acc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for object \"" + getName() +"."+ acc.getName() + "\"", 352);
				break;
			case 0:
				acc.setElementName(Param.toLowerCase());
				break;
			case 1:
				acc.setElementTerminal(parser.makeInteger());
				break;
			case 2:
				acc.setCapacitorName("capacitor." + Param);
				break;
			case 3:
				switch (Param.toLowerCase().charAt(0)) {
				case 'c':
					acc.setControlType(CapControlType.CURRENTCONTROL);
					break;
				case 'v':
					acc.setControlType(CapControlType.VOLTAGECONTROL);
					break;
				case 'k':
					acc.setControlType(CapControlType.KVARCONTROL);
					break;
				case 't':
					acc.setControlType(CapControlType.TIMECONTROL);
					break;
				case 'p':
					acc.setControlType(CapControlType.PFCONTROL);
					break;
				case 's':
					acc.setControlType(CapControlType.SRPCONTROL);
					break;
				default:
					Globals.doSimpleMsg(String.format("Unrecognized CapControl type: \"%s\" (CapControl.%s)", Param, acc.getName()), 352);
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
				acc.setON_Value(parser.makeDouble());
				break;
			case 7:
				acc.setOFF_Value(parser.makeDouble());
				break;
			case 8:
				acc.setONDelay(parser.makeDouble());
				break;
			case 9:
				acc.setVOverride(Utilities.interpretYesNo(Param));
				break;
			case 10:
				acc.setVmax(parser.makeDouble());
				break;
			case 11:
				acc.setVmin(parser.makeDouble());
				break;
			case 12:
				acc.setOFFDelay(parser.makeDouble());
				break;
			case 13:
				acc.setDeadTime(parser.makeDouble());
				break;
			case 14:
				if (Utilities.compareTextShortest(Param, "avg") == 0) {
					acc.setCTPhase(CapControl.AVGPHASES);
				} else if (Utilities.compareTextShortest(Param, "max") == 0) {
					acc.setCTPhase(CapControl.MAXPHASE);
				} else if (Utilities.compareTextShortest(Param, "min") == 0) {
					acc.setCTPhase(CapControl.MINPHASE);
				} else {
					acc.setCTPhase( Math.max(1, parser.makeInteger()) );
				}
				break;
			case 15:
				if (Utilities.compareTextShortest(Param, "avg") == 0) {
					acc.setPTPhase(CapControl.AVGPHASES);
				} else if (Utilities.compareTextShortest(Param, "max") == 0) {
					acc.setPTPhase(CapControl.MAXPHASE);
				} else if (Utilities.compareTextShortest(Param, "min") == 0) {
					acc.setPTPhase(CapControl.MINPHASE);
				} else {
					acc.setPTPhase( Math.max(1, parser.makeInteger()) );
				}
				break;
			default:
				// inherited parameters
				classEdit(getActiveCapControlObj(), ParamPointer - CapControl.NumPropsThisClass);
				break;
			}


			/* PF controller changes */
			if (acc.getControlType() == CapControlType.PFCONTROL) {
				switch (ParamPointer) {
				case 3:
					acc.setPFON_Value(0.95);  // defaults
					acc.setPFOFF_Value(1.05);
					break;
				case 6:
					if ((acc.getON_Value() >= -1.0) && (acc.getON_Value() <= 1.0)) {
						if (acc.getON_Value() < 0.0) {
							acc.setPFON_Value( 2.0 + acc.getON_Value());
						} else {
							acc.setPFON_Value(acc.getON_Value());
						}
					} else {
						Globals.doSimpleMsg("Invalid PF on value for CapControl."+acc.getName(), 353);
					}
					break;
				case 7:
					if ((acc.getOFF_Value() >= -1.0) && (acc.getOFF_Value() <= 1.0)) {
						if (acc.getOFF_Value() < 0.0) {
							acc.setPFOFF_Value(2.0 + acc.getOFF_Value());
						} else {
							acc.setPFOFF_Value(acc.getOFF_Value());
						}
					} else {
						Globals.doSimpleMsg("Invalid PF off value for CapControl."+acc.getName(), 35301);
					}
					break;
				case 14:
					if (acc.getCTPhase() > acc.getNPhases()) {
						Globals.doSimpleMsg(String.format("Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ", acc.getCTPhase(), acc.getNPhases()), 35302);
						acc.setCTPhase(1);
					}
					break;
				case 15:
					if (acc.getPTPhase() > acc.getNPhases()) {
						Globals.doSimpleMsg(String.format("Error: Monitored phase(%d) must be less than or equal to number of phases(%d). ", acc.getPTPhase(), acc.getNPhases()), 35303);
						acc.setPTPhase(1);
					}
					break;
				}
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		acc.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String CapControlName) {
		int i, Result = 0;

		/* See if we can find this CapControl name in the present collection */
		CapControlObj OtherCapControl = (CapControlObj) find(CapControlName);
		if (OtherCapControl != null) {
			CapControlObj acc = getActiveCapControlObj();

			acc.setNPhases(OtherCapControl.getNPhases());
			acc.setNConds(OtherCapControl.getNConds());  // force reallocation of terminal stuff

			acc.setElementName(OtherCapControl.getElementName());
			acc.setCapacitorName(OtherCapControl.getCapacitorName());
			acc.setControlledElement(OtherCapControl.getControlledElement());  // pointer to target circuit element
			acc.setMonitoredElement(OtherCapControl.getMonitoredElement());    // pointer to target circuit element

			acc.setElementTerminal(OtherCapControl.getElementTerminal());
			acc.setPTRatio(OtherCapControl.getPTRatio());
			acc.setCTRatio(OtherCapControl.getCTRatio());
			acc.setControlType(OtherCapControl.getControlType());
			acc.setPresentState(OtherCapControl.getPresentState());
			acc.setShouldSwitch(OtherCapControl.isShouldSwitch());
			acc.setCondOffset(OtherCapControl.getCondOffset());

			acc.setON_Value(OtherCapControl.getON_Value());
			acc.setOFF_Value(OtherCapControl.getOFF_Value());
			acc.setPFON_Value(OtherCapControl.getPFON_Value());
			acc.setPFOFF_Value(OtherCapControl.getPFOFF_Value());

			acc.setCTPhase(OtherCapControl.getCTPhase());
			acc.setPTPhase(OtherCapControl.getPTPhase());

			for (i = 0; i < acc.getParentClass().getNumProperties(); i++)
				acc.setPropertyValue(i, OtherCapControl.getPropertyValue(i));

		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in CapControl makeLike: \"" + CapControlName + "\" not found.", 360);
		}

		return Result;
	}

	public static CapControlObj getActiveCapControlObj() {
		return ActiveCapControlObj;
	}

	public static void setActiveCapControlObj(CapControlObj activeCapControlObj) {
		ActiveCapControlObj = activeCapControlObj;
	}

}
