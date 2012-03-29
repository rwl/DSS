/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.control;

import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.general.LoadShapeObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;

import static com.ncond.dss.common.Util.convertPFToPFRange2;
import static com.ncond.dss.common.Util.interpretStringListArray;
import static com.ncond.dss.common.Util.interpretDblArray;
import static com.ncond.dss.common.Util.interpretYesNo;
import static com.ncond.dss.common.Util.resizeArray;


public class StorageController extends ControlClass {

	public static final int ELEMENT        = 0;
	public static final int TERMINAL       = 1;
	public static final int KW_TARGET      = 2;
	public static final int KW_BAND        = 3;
	public static final int PF_TARGET      = 4;
	public static final int PF_BAND        = 5;
	public static final int ELEMENT_LIST   = 6;
	public static final int WEIGHTS        = 7;
	public static final int MODE_DISCHARGE = 8;
	public static final int MODE_CHARGE    = 9;
	public static final int TIME_DISCHARGE_TRIGGER = 10;
	public static final int TIME_CHARGE_TRIGGER    = 11;
	public static final int RATE_KW       = 12;
	public static final int RATE_KVAR     = 13;
	public static final int RATE_CHARGE   = 14;
	public static final int RESERVE       = 15;
	public static final int KWH_TOTAL     = 16;
	public static final int KW_TOTAL      = 17;
	public static final int KWH_ACTUAL    = 18;
	public static final int KW_ACTUAL     = 19;
	public static final int KW_NEED       = 20;
	public static final int PARTICIPATION = 21;
	public static final int YEARLY        = 22;
	public static final int DAILY         = 23;
	public static final int DUTY          = 24;
	public static final int EVENTLOG      = 25;
	public static final int VAR_DISPATCH  = 26;
	public static final int INHIBIT_TIME  = 27;
	public static final int T_UP_RAMP     = 28;
	public static final int T_FLAT        = 29;
	public static final int T_DN_RAMP     = 30;
	public static final int KW_THRESHOLD  = 31;

	public static final int NumPropsThisClass = 32;

	/* Other constants */
	public static final int RELEASE_INHIBIT = 999;

	public static StorageControllerObj activeStorageControllerObj;

	public StorageController() {
		super();

		className = "StorageController";
		classType = classType + DSSClassDefs.STORAGE_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandList(commands);
		commandList.setAbbrevAllowed(true);
	}

	@Override
	protected void defineProperties() {
		numProperties = StorageController.NumPropsThisClass;
		countProperties();   // get inherited property count

		allocatePropertyArrays();

		// define property names
		propertyName[ELEMENT]                 = "Element";
		propertyName[TERMINAL]                = "Terminal";
		propertyName[KW_TARGET]               = "kWTarget";
		propertyName[KW_BAND]                 = "%kWBand";
		propertyName[PF_TARGET]               = "PFTarget";
		propertyName[PF_BAND]                 = "PFBand";
		propertyName[ELEMENT_LIST]            = "ElementList";
		propertyName[WEIGHTS]                 = "Weights";
		propertyName[MODE_DISCHARGE]          = "ModeDischarge";
		propertyName[MODE_CHARGE]             = "ModeCharge";
		propertyName[TIME_DISCHARGE_TRIGGER]  = "TimeDischargeTrigger";
		propertyName[TIME_CHARGE_TRIGGER]     = "TimeChargeTrigger";
		propertyName[RATE_KW]                 = "%RatekW";
		propertyName[RATE_KVAR]               = "%Ratekvar";
		propertyName[RATE_CHARGE]             = "%RateCharge";
		propertyName[RESERVE]                 = "%Reserve";
		propertyName[KWH_TOTAL]               = "kWhTotal";
		propertyName[KW_TOTAL]                = "kWTotal";
		propertyName[KWH_ACTUAL]              = "kWhActual";
		propertyName[KW_ACTUAL]               = "kWActual";
		propertyName[KW_NEED]                 = "kWneed";
		propertyName[PARTICIPATION]           = "%Participation";
		propertyName[YEARLY]                  = "Yearly";
		propertyName[DAILY]                   = "Daily";
		propertyName[DUTY]                    = "Duty";
		propertyName[EVENTLOG]                = "EventLog";
		propertyName[VAR_DISPATCH]            = "VarDispatch";
		propertyName[INHIBIT_TIME]            = "InhibitTime";
		propertyName[T_UP_RAMP]               = "Tup";
		propertyName[T_FLAT]                  = "TFlat";
		propertyName[T_DN_RAMP]               = "Tdn";
		propertyName[KW_THRESHOLD]            = "kWThreshold";


		propertyHelp[ELEMENT] =
				"Full object name of the circuit element, typically a line or transformer, "+
				"which the control is monitoring. There is no default; must be specified.";
		propertyHelp[TERMINAL] =
				"Number of the terminal of the circuit element to which the StorageController control is connected. "+
				"1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		propertyHelp[KW_TARGET] =
				"kW target for Discharging. The storage element fleet is dispatched to try to hold the power in band "+
				"at least until the storage is depleted.";
		propertyHelp[KW_BAND] =
				"Bandwidth (% of Target kW) of the dead band around the kW target value. Default is 2% (+/-1%)." +
				"No dispatch changes are attempted If the power in the monitored terminal stays within this band.";
		propertyHelp[PF_TARGET] =
				"Power Factor target for dispatching the reactive power. Default is 0.96. The reactive power of the storage element fleet is dispatched to try to hold the power factor in band. "+
				"It is assumed that the storage element inverter can produce kvar up to its kVA limit regardless of storage level.";
		propertyHelp[PF_BAND] =
				"Bandwidth of the Target power factor of the monitored element. of the dead band around the kvar target value. Default is 0.04 (+/- 0.02)." +
				"No dispatch changes of the kvar are attempted If the power factor of the monitored terminal stays within this band.";
		propertyHelp[ELEMENT_LIST] =
				"Array list of Storage elements to be controlled.  If not specified, all storage elements in the circuit not presently dispatched by another controller " +
				"are assumed dispatched by this controller.";
		propertyHelp[WEIGHTS] =
				"Array of proportional weights corresponding to each storage element in the ElementList. " +
				"The needed kW or kvar to get back to center band is dispatched to each storage element according to these weights. " +
				"Default is to set all weights to 1.0.";
		propertyHelp[MODE_DISCHARGE] =
				"{PeakShave* | Follow | Support | Loadshape | Time | Schedule} Mode of operation for the DISCHARGE FUNCTION of this controller. " +
				DSS.CRLF+DSS.CRLF+"In PeakShave mode (Default), the control attempts to discharge storage to keep power in the monitored element below the kWTarget. " +
				DSS.CRLF+DSS.CRLF+"In Follow mode, the control is triggered by time and resets the kWTarget value to the present monitored element power. " +
				"It then attempts to discharge storage to keep power in the monitored element below the new kWTarget. See TimeDischargeTrigger." +
				DSS.CRLF+DSS.CRLF+"In Support mode, the control operates oppositely of PeakShave mode: storage is discharged to keep kW power output up near the target. " +
				DSS.CRLF+DSS.CRLF+"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is discharged when the loadshape value is positive. " +
				DSS.CRLF+DSS.CRLF+"In Time mode, the storage discharge is turned on at the specified %RatekW and %Ratekvar at the specified discharge trigger time in fractional hours." +
				DSS.CRLF+DSS.CRLF+"In Schedule mode, the Tup, TFlat, and Tdn properties specify the up ramp duration, flat duration, and down ramp duration for the schedule. " +
				"The schedule start time is set by TimeDischargeTrigger and the rate of discharge for the flat part is determined by RatekW.";
		propertyHelp[MODE_CHARGE] =
				"{Loadshape | Time*} Mode of operation for the CHARGE FUNCTION of this controller. " +
				"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is charged when the loadshape value is negative. " +
				"In Time mode, the storage charging FUNCTION is triggered at the specified %RateCharge at the specified sharge trigger time in fractional hours.";
		propertyHelp[TIME_DISCHARGE_TRIGGER] =
				"Default time of day (hr) for initiating Discharging of the fleet. During Follow or Time mode discharging is triggered at a fixed time " +
				"each day at this hour. If Follow mode, storage will be discharged to attempt to hold the load at or below the power level at the time of triggering. " +
				"In Time mode, the discharge is based on the %RatekW property value. " +
				"Set this to a negative value to ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored). ";
		propertyHelp[TIME_CHARGE_TRIGGER] =
				"Default time of day (hr) for initiating charging in Time control mode. Set this to a negative value to ignore. Default is 2.0.  (0200)." +
				"When this value is >0 the storage fleet is set to charging at this time regardless of other control criteria to make sure storage is " +
				"topped off for the next discharge cycle.";
		propertyHelp[RATE_KW] =
				"Sets the kW discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time.";
		propertyHelp[RATE_KVAR] =
				"Sets the kvar discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time." ;
		propertyHelp[RATE_CHARGE] =
				"Sets the kW charging rate in % of rated capacity for each element of the fleet. Applies to TIME control mode and anytime charging mode is " +
				"entered due to a time trigger.";
		propertyHelp[RESERVE] =
				"Use this property to change the % reserve for each storage element under control of this controller. This might be used, for example, to " +
				"allow deeper discharges of storage or in case of emergency operation to use the remainder of the storage element.";
		propertyHelp[KWH_TOTAL] =
				"(Read only). Total rated kWh energy storage capacity of storage elements controlled by this controller.";
		propertyHelp[KW_TOTAL] =
				"(Read only). Total rated kW power capacity of storage elements controlled by this controller.";
		propertyHelp[KWH_ACTUAL] =
				"(Read only). Actual kWh output of all controlled storage elements. ";
		propertyHelp[KW_ACTUAL] =
				"(Read only). Actual kW output of all controlled storage elements. ";
		propertyHelp[KW_NEED] =
				"(Read only). KW needed to meet target.";
		propertyHelp[PARTICIPATION] =
				"Participation factor, %. Default = 100.";
		propertyHelp[YEARLY] =
				"Dispatch loadshape object, If any, for Yearly solution Mode.";
		propertyHelp[DAILY] =
				"Dispatch loadshape object, If any, for Daily solution mode.";
		propertyHelp[DUTY] =
				"Dispatch loadshape object, If any, for Dutycycle solution mode.";
		propertyHelp[EVENTLOG] =
				"{Yes/True | No/False} Default is No. Log control actions to Eventlog.";
		propertyHelp[VAR_DISPATCH] =
				"{Yes/True | No/False} Default is No. Flag to indicate whether or not to disatch vars as well as watts.";
		propertyHelp[INHIBIT_TIME] =
				"Hours (integer) to inhibit Discharging after going into Charge mode. Default is 5";

		propertyHelp[T_UP_RAMP]  = "Duration, hrs, of upramp part for SCHEDULE mode. Default is 0.25.";
		propertyHelp[T_FLAT]    = "Duration, hrs, of flat part for SCHEDULE mode. Default is 2.0.";
		propertyHelp[T_DN_RAMP]  = "Duration, hrs, of downramp part for SCHEDULE mode. Default is 0.25.";
		propertyHelp[KW_THRESHOLD] = "Threshold, kW, for Follow mode. kW has to be above this value for the Storage element " +
				"to be dispatched on. Defaults to 75% of the kWTarget value. Must reset this property after " +
				"setting kWTarget if you want a different value.";

		activeProperty = StorageController.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {
		DSS.activeCircuit.setActiveCktElement(new StorageControllerObj(this, objName));
		return addObjectToList(DSS.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeStorageControllerObj = (StorageControllerObj) elementList.getActive();
		DSS.activeCircuit.setActiveCktElement(activeStorageControllerObj);

		StorageControllerObj elem = activeStorageControllerObj;

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
						getClassName() +"."+ elem.getName() + "\"", 14407);
				break;
			case StorageController.ELEMENT:
				elem.setElementName(param.toLowerCase());
				break;
			case StorageController.TERMINAL:
				elem.setElementTerminalIdx(parser.integerValue() - 1);
				break;
			case StorageController.KW_TARGET:
				elem.setKWTarget(parser.doubleValue());
				break;
			case StorageController.KW_BAND:
				elem.setPctkWBand(parser.doubleValue());
				break;
			case StorageController.PF_TARGET:
				elem.setPFTarget( convertPFToPFRange2(parser.doubleValue()) );
				break;
			case StorageController.PF_BAND:
				elem.setPFBand(parser.doubleValue());
				break;
			case StorageController.ELEMENT_LIST:
				interpretStringListArray(param, elem.getStorageNameList());
				break;
			case StorageController.WEIGHTS:
				elem.setFleetSize(elem.getStorageNameList().size());
				if (elem.getFleetSize() > 0) {
					elem.setWeights( resizeArray(elem.getWeights(), elem.getFleetSize()) );
					interpretDblArray(param, elem.getFleetSize(), elem.getWeights());
				}
				break;
			case StorageController.MODE_DISCHARGE:
				elem.setDischargeMode(elem.interpretMode(MODE_DISCHARGE, param));
				break;
			case StorageController.MODE_CHARGE:
				elem.setChargeMode(elem.interpretMode(MODE_CHARGE, param));
				break;
			case StorageController.TIME_DISCHARGE_TRIGGER:
				elem.setDischargeTriggerTime(parser.doubleValue());
				break;
			case StorageController.TIME_CHARGE_TRIGGER:
				elem.setChargeTriggerTime(parser.doubleValue());
				break;
			case StorageController.RATE_KW:
				elem.setPctKWRate(parser.doubleValue());
				break;
			case StorageController.RATE_KVAR:
				elem.setPctKVArRate(parser.doubleValue());
				break;
			case StorageController.RATE_CHARGE:
				elem.setPctChargeRate(parser.doubleValue());
				break;
			case StorageController.RESERVE:
				elem.setPctFleetReserve(parser.doubleValue());
				break;
			case StorageController.KWH_TOTAL:
				// do nothing (read only)
				break;
			case StorageController.KW_TOTAL:
				// do nothing (read only)
				break;
			case StorageController.KWH_ACTUAL:
				// do nothing (read only)
				break;
			case StorageController.KW_ACTUAL:
				// do nothing (read only)
				break;
			case StorageController.KW_NEED:
				// do nothing (read only)
				break;
			case StorageController.PARTICIPATION:
				break;
			case StorageController.YEARLY:
				elem.setYearlyShape(param);
				break;
			case StorageController.DAILY:
				elem.setDailyShape(param);
				break;
			case StorageController.DUTY:
				elem.setDutyShape(param);
				break;
			case StorageController.EVENTLOG:
				elem.setShowEventLog(interpretYesNo(param));
				break;
			case StorageController.VAR_DISPATCH:
				elem.setDispatchVars(interpretYesNo(param));
				break;
			case StorageController.INHIBIT_TIME:
				elem.setInhibitHrs( Math.max(1, parser.integerValue()) );  // >= 1
				break;
			case StorageController.T_UP_RAMP:
				elem.setUpRampTime(parser.doubleValue());
				break;
			case StorageController.T_FLAT:
				elem.setFlatTime(parser.doubleValue());
				break;
			case StorageController.T_DN_RAMP:
				elem.setDnRampTime(parser.doubleValue());
				break;
			case StorageController.KW_THRESHOLD:
				elem.setKWThreshold(parser.doubleValue());
				break;
			default:
				// inherited parameters
				classEdit(activeStorageControllerObj, paramPointer - StorageController.NumPropsThisClass);
				break;
			}

			// side effects of setting properties above
			switch (paramPointer) {
			case KW_TARGET:
				elem.setHalfKWBand(elem.getPctkWBand() / 200.0 * elem.getKWTarget());
				break;
			case KW_BAND:
				elem.setHalfKWBand(elem.getPctkWBand() / 200.0 * elem.getKWTarget());
				elem.setKWThreshold( elem.getKWTarget() * 0.75 );
				break;
			case PF_BAND:
				elem.setHalfPFBand(elem.getPFBand() / 2.0);
				break;
			case MODE_DISCHARGE:
				if (elem.getDischargeMode() == StorageControlMode.FOLLOW)
					elem.setDischargeTriggerTime(12.0);  // noon
				break;
			case ELEMENT_LIST:
				// levelize the list
				elem.getFleetPointerList().clear();  // clear this for resetting on first sample
				elem.setFleetListChanged(true);
				elem.setElementListSpecified(true);
				elem.setFleetSize(elem.getStorageNameList().size());
				// realloc weights to be same size as possible number of storage elements
				elem.setWeights(resizeArray(elem.getWeights(), elem.getFleetSize()));
				for (int i = 0; i < elem.getFleetSize(); i++)
					elem.getWeights()[i] = 1.0;
				break;
			case YEARLY:
				elem.setYearlyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getYearlyShape()));
				if (elem.getYearlyShapeObj() == null)
					DSS.doSimpleMsg("Yearly loadshape \"" + elem.getYearlyShape() + "\" not found.", 14404);
				break;
			case DAILY:
				elem.setDailyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getDailyShape()));
				if (elem.getDailyShapeObj() == null)
					DSS.doSimpleMsg("Daily loadshape \"" + elem.getDailyShape() + "\" not found.", 14405);
				break;
			case DUTY:
				elem.setDutyShapeObj((LoadShapeObj) DSS.loadShapeClass.find(elem.getDutyShape()));
				if (elem.getDutyShapeObj() == null)
					DSS.doSimpleMsg("Dutycycle loadshape \"" + elem.getDutyShape() + "\" not found.", 14406);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.stringValue();
		}
		elem.recalcElementData();

		return 0;
	}

	@Override
	protected int makeLike(String name) {
		/* See if we can find this StorageController name in the present collection */
		StorageControllerObj other = (StorageControllerObj) find(name);

		if (other != null) {
			StorageControllerObj elem = activeStorageControllerObj;

			elem.setNumPhases(other.getNumPhases());
			elem.setNumConds(other.getNumConds());  // force reallocation of terminal stuff

			elem.setElementName(other.getElementName());
			elem.setControlledElement(other.getControlledElement());  // pointer to target circuit element
			elem.setMonitoredElement(other.getMonitoredElement());  // pointer to target circuit element
			elem.setElementTerminalIdx(other.getElementTerminalIdx());

			elem.setKWTarget(other.getKWTarget());
			elem.setKWThreshold(other.getKWThreshold());
			elem.setPctkWBand(other.getPctkWBand());
			elem.setPFTarget(other.getPFTarget());
			elem.PFBand = other.PFBand;
			elem.setHalfPFBand(other.getHalfPFBand());

			elem.getStorageNameList().clear();
			for (int i = 0; i < other.getStorageNameList().size(); i++)
				elem.getStorageNameList().add(other.getStorageNameList().get(i - 1));

			elem.setFleetSize(elem.getStorageNameList().size());
			if (elem.getFleetSize() > 0) {
				elem.setWeights( resizeArray(elem.getWeights(), elem.getFleetSize()) );
				for (int i = 0; i < elem.getFleetSize(); i++)
					elem.getWeights()[i] = other.getWeights()[i];
			}

			elem.setDischargeMode(other.getDischargeMode());
			elem.setChargeMode(other.getChargeMode());
			elem.setDischargeTriggerTime(other.getDischargeTriggerTime());
			elem.setChargeTriggerTime(other.getChargeTriggerTime());
			elem.setPctKWRate(other.getPctKWRate());
			elem.setPctKVArRate(other.getPctKVArRate());
			elem.setPctChargeRate(other.getPctChargeRate());
			elem.setPctFleetReserve(other.getPctFleetReserve());
			elem.setYearlyShape(other.getYearlyShape());
			elem.setDailyShape(other.getDailyShape());
			elem.setDutyShape(other.getDutyShape());
			elem.setDispatchVars(other.isDispatchVars());
			elem.setShowEventLog(other.isShowEventLog());
			elem.setInhibitHrs(other.getInhibitHrs());

			elem.setUpRampTime(other.getUpRampTime());
			elem.setFlatTime(other.getFlatTime());
			elem.setDnRampTime(other.getDnRampTime());

			// fill in private properties
			for (int i = 0; i < elem.getParentClass().getNumProperties(); i++)
				elem.setPropertyValue(i, other.getPropertyValue(i));
		} else {
			DSS.doSimpleMsg("Error in StorageController makeLike: \"" + name + "\" not found.", 370);
		}

		return 0;
	}

	@Override
	public int init(int handle) {
		DSS.doSimpleMsg("Need to implement StorageController.init", -1);
		return 0;
	}

}
