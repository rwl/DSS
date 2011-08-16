package com.epri.dss.control.impl;

import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.StorageController;
import com.epri.dss.control.StorageControllerObj;
import com.epri.dss.general.LoadShapeObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class StorageControllerImpl extends ControlClassImpl implements StorageController {

	public static StorageControllerObj activeStorageControllerObj;

	public StorageControllerImpl() {
		super();

		className = "StorageController";
		classType = classType + DSSClassDefs.STORAGE_CONTROL;

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = StorageController.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[StorageController.ELEMENT]                = "Element";
		propertyName[StorageController.TERMINAL]               = "Terminal";
		propertyName[StorageController.KW_TARGET]               = "kWTarget";
		propertyName[StorageController.KW_BAND]                 = "%kWBand";
		propertyName[StorageController.PF_TARGET]               = "PFTarget";
		propertyName[StorageController.PF_BAND]                 = "PFBand";
		propertyName[StorageController.ELEMENT_LIST]            = "ElementList";
		propertyName[StorageController.WEIGHTS]                = "Weights";
		propertyName[StorageController.MODE_DISCHARGE]          = "ModeDischarge";
		propertyName[StorageController.MODE_CHARGE]             = "ModeCharge";
		propertyName[StorageController.TIME_DISCHARGE_TRIGGER]   = "TimeDischargeTrigger";
		propertyName[StorageController.TIME_CHARGE_TRIGGER]      = "TimeChargeTrigger";
		propertyName[StorageController.RATE_KW]                 = "%RatekW";
		propertyName[StorageController.RATE_KVAR]               = "%Ratekvar";
		propertyName[StorageController.RATE_CHARGE]             = "%RateCharge";
		propertyName[StorageController.RESERVE]                = "%Reserve";
		propertyName[StorageController.KWH_TOTAL]               = "kWhTotal";
		propertyName[StorageController.KW_TOTAL]                = "kWTotal";
		propertyName[StorageController.KWH_ACTUAL]              = "kWhActual";
		propertyName[StorageController.KW_ACTUAL]               = "kWActual";
		propertyName[StorageController.KW_NEED]                 = "kWneed";
		propertyName[StorageController.PARTICIPATION]          = "%Participation";
		propertyName[StorageController.YEARLY]                 = "Yearly";
		propertyName[StorageController.DAILY]                  = "Daily";
		propertyName[StorageController.DUTY]                   = "Duty";
		propertyName[StorageController.EVENTLOG]               = "EventLog";
		propertyName[StorageController.VAR_DISPATCH]            = "VarDispatch";
		propertyName[StorageController.INHIBIT_TIME]            = "InhibitTime";
		propertyName[StorageController.T_UP_RAMP]                = "Tup";
		propertyName[StorageController.T_FLAT]                  = "TFlat";
		propertyName[StorageController.T_DN_RAMP]                = "Tdn";
		propertyName[StorageController.KW_THRESHOLD]            = "kWThreshold";


		propertyHelp[StorageController.ELEMENT] =
				"Full object name of the circuit element, typically a line or transformer, "+
				"which the control is monitoring. There is no default; must be specified.";
		propertyHelp[StorageController.TERMINAL] =
				"Number of the terminal of the circuit element to which the StorageController control is connected. "+
				"1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		propertyHelp[StorageController.KW_TARGET] =
				"kW target for Discharging. The storage element fleet is dispatched to try to hold the power in band "+
				"at least until the storage is depleted.";
		propertyHelp[StorageController.KW_BAND] =
				"Bandwidth (% of Target kW) of the dead band around the kW target value. Default is 2% (+/-1%)." +
				"No dispatch changes are attempted If the power in the monitored terminal stays within this band.";
		propertyHelp[StorageController.PF_TARGET] =
				"Power Factor target for dispatching the reactive power. Default is 0.96. The reactive power of the storage element fleet is dispatched to try to hold the power factor in band. "+
				"It is assumed that the storage element inverter can produce kvar up to its kVA limit regardless of storage level.";
		propertyHelp[StorageController.PF_BAND] =
				"Bandwidth of the Target power factor of the monitored element. of the dead band around the kvar target value. Default is 0.04 (+/- 0.02)." +
				"No dispatch changes of the kvar are attempted If the power factor of the monitored terminal stays within this band.";
		propertyHelp[StorageController.ELEMENT_LIST] =
				"Array list of Storage elements to be controlled.  If not specified, all storage elements in the circuit not presently dispatched by another controller " +
				"are assumed dispatched by this controller.";
		propertyHelp[StorageController.WEIGHTS] =
				"Array of proportional weights corresponding to each storage element in the ElementList. " +
				"The needed kW or kvar to get back to center band is dispatched to each storage element according to these weights. " +
				"Default is to set all weights to 1.0.";
		propertyHelp[StorageController.MODE_DISCHARGE] =
				"{PeakShave* | Follow | Support | Loadshape | Time | Schedule} Mode of operation for the DISCHARGE FUNCTION of this controller. " +
				"In PeakShave mode (Default), the control attempts to discharge storage to keep power in the monitored element below the kWTarget. " +
				"In Follow mode, the control is triggered by time and resets the kWTarget value to the present monitored element power. " +
				"It then attempts to discharge storage to keep power in the monitored element below the new kWTarget. See TimeDischargeTrigger." +
				"In Support mode, the control operates oppositely of PeakShave mode: storage is discharged to keep kW power output up near the target. " +
				"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is discharged when the loadshape value is positive. " +
				"In Time mode, the storage discharge is turned on at the specified %RatekW and %Ratekvar at the specified discharge trigger time in fractional hours." +
				"In Schedule mode, the Tup, TFlat, and Tdn properties specify the up ramp duration, flat duration, and down ramp duration for the schedule. " +
				"The schedule start time is set by TimeDischargeTrigger and the rate of discharge for the flat part is determined by RatekW.";
		propertyHelp[StorageController.MODE_CHARGE] =
				"{Loadshape | Time*} Mode of operation for the CHARGE FUNCTION of this controller. " +
				"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is charged when the loadshape value is negative. " +
				"In Time mode, the storage charging FUNCTION is triggered at the specified %RateCharge at the specified sharge trigger time in fractional hours.";
		propertyHelp[StorageController.TIME_DISCHARGE_TRIGGER] =
				"Default time of day (hr) for initiating Discharging of the fleet. During Follow or Time mode discharging is triggered at a fixed time " +
				"each day at this hour. If Follow mode, storage will be discharged to attempt to hold the load at or below the power level at the time of triggering. " +
				"In Time mode, the discharge is based on the %RatekW property value. " +
				"Set this to a negative value to ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored). ";
		propertyHelp[StorageController.TIME_CHARGE_TRIGGER] =
				"Default time of day (hr) for initiating charging in Time control mode. Set this to a negative value to ignore. Default is 2.0.  (0200)." +
				"When this value is >0 the storage fleet is set to charging at this time regardless of other control criteria to make sure storage is " +
				"topped off for the next discharge cycle.";
		propertyHelp[StorageController.RATE_KW] =
				"Sets the kW discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time.";
		propertyHelp[StorageController.RATE_KVAR] =
				"Sets the kvar discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time." ;
		propertyHelp[StorageController.RATE_CHARGE] =
				"Sets the kW charging rate in % of rated capacity for each element of the fleet. Applies to TIME control mode and anytime charging mode is " +
				"entered due to a time trigger.";
		propertyHelp[StorageController.RESERVE] =
				"Use this property to change the % reserve for each storage element under control of this controller. This might be used, for example, to " +
				"allow deeper discharges of storage or in case of emergency operation to use the remainder of the storage element.";
		propertyHelp[StorageController.KWH_TOTAL] =
				"(Read only). Total rated kWh energy storage capacity of storage elements controlled by this controller.";
		propertyHelp[StorageController.KW_TOTAL] =
				"(Read only). Total rated kW power capacity of storage elements controlled by this controller.";
		propertyHelp[StorageController.KWH_ACTUAL] =
				"(Read only). Actual kWh output of all controlled storage elements. ";
		propertyHelp[StorageController.KW_ACTUAL] =
				"(Read only). Actual kW output of all controlled storage elements. ";
		propertyHelp[StorageController.KW_NEED] =
				"(Read only). KW needed to meet target.";
		propertyHelp[StorageController.PARTICIPATION] =
				"Participation factor, %. Default = 100.";
		propertyHelp[StorageController.YEARLY] =
				"Dispatch loadshape object, If any, for Yearly solution Mode.";
		propertyHelp[StorageController.DAILY] =
				"Dispatch loadshape object, If any, for Daily solution mode.";
		propertyHelp[StorageController.DUTY] =
				"Dispatch loadshape object, If any, for Dutycycle solution mode.";
		propertyHelp[StorageController.EVENTLOG] =
				"{Yes/True | No/False} Default is No. Log control actions to Eventlog.";
		propertyHelp[StorageController.VAR_DISPATCH] =
				"{Yes/True | No/False} Default is No. Flag to indicate whether or not to disatch vars as well as watts.";
		propertyHelp[StorageController.INHIBIT_TIME] =
				"Hours (integer) to inhibit Discharging after going into Charge mode. Default is 5";

		propertyHelp[StorageController.T_UP_RAMP]  = "Duration, hrs, of upramp part for SCHEDULE mode. Default is 0.25.";
		propertyHelp[StorageController.T_FLAT]    = "Duration, hrs, of flat part for SCHEDULE mode. Default is 2.0.";
		propertyHelp[StorageController.T_DN_RAMP]  = "Duration, hrs, of downramp part for SCHEDULE mode. Default is 0.25.";
		propertyHelp[StorageController.KW_THRESHOLD] = "Threshold, kW, for Follow mode. kW has to be above this value for the Storage element " +
				"to be dispatched on. Defaults to 75% of the kWTarget value. Must reset this property after " +
				"setting kWTarget if you want a different value.";

		activeProperty = StorageController.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String objName) {

		DSSGlobals.activeCircuit.setActiveCktElement(new StorageControllerObjImpl(this, objName));
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	@Override
	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeStorageControllerObj = (StorageControllerObj) elementList.getActive();
		DSSGlobals.activeCircuit.setActiveCktElement(activeStorageControllerObj);

		int result = 0;

		StorageControllerObj asc = activeStorageControllerObj;

		int paramPointer = 0;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if ((paramPointer >= 0) && (paramPointer < numProperties))
				asc.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ asc.getName() + "\"", 14407);
				break;
			case StorageController.ELEMENT:
				asc.setElementName(param.toLowerCase());
				break;
			case StorageController.TERMINAL:
				asc.setElementTerminal(parser.makeInteger());
				break;
			case StorageController.KW_TARGET:
				asc.setKWTarget(parser.makeDouble());
				break;
			case StorageController.KW_BAND:
				asc.setPctKWBand(parser.makeDouble());
				break;
			case StorageController.PF_TARGET:
				asc.setPFTarget( Utilities.convertPFToPFRange2(parser.makeDouble()) );
				break;
			case StorageController.PF_BAND:
				asc.setPFBand(parser.makeDouble());
				break;
			case StorageController.ELEMENT_LIST:
				Utilities.interpretStringListArray(param, asc.getStorageNameList());
				break;
			case StorageController.WEIGHTS:
				asc.setFleetSize(asc.getStorageNameList().size());
				if (asc.getFleetSize() > 0) {
					asc.setWeights( Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
					Utilities.interpretDblArray(param, asc.getFleetSize(), asc.getWeights());
				}
				break;
			case StorageController.MODE_DISCHARGE:
				asc.setDischargeMode(asc.interpretMode(MODE_DISCHARGE, param));
				break;
			case StorageController.MODE_CHARGE:
				asc.setChargeMode(asc.interpretMode(MODE_CHARGE, param));
				break;
			case StorageController.TIME_DISCHARGE_TRIGGER:
				asc.setDischargeTriggerTime(parser.makeDouble());
				break;
			case StorageController.TIME_CHARGE_TRIGGER:
				asc.setChargeTriggerTime(parser.makeDouble());
				break;
			case StorageController.RATE_KW:
				asc.setPctKWRate(parser.makeDouble());
				break;
			case StorageController.RATE_KVAR:
				asc.setPctKVArRate(parser.makeDouble());
				break;
			case StorageController.RATE_CHARGE:
				asc.setPctChargeRate(parser.makeDouble());
				break;
			case StorageController.RESERVE:
				asc.setPctFleetReserve(parser.makeDouble());
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
				asc.setYearlyShape(param);
				break;
			case StorageController.DAILY:
				asc.setDailyShape(param);
				break;
			case StorageController.DUTY:
				asc.setDutyShape(param);
				break;
			case StorageController.EVENTLOG:
				asc.setShowEventLog(Utilities.interpretYesNo(param));
				break;
			case StorageController.VAR_DISPATCH:
				asc.setDispatchVars(Utilities.interpretYesNo(param));
				break;
			case StorageController.INHIBIT_TIME:
				asc.setInhibitHrs( Math.max(1, parser.makeInteger()) );  // >= 1
				break;
			case StorageController.T_UP_RAMP:
				asc.setUpRampTime(parser.makeDouble());
				break;
			case StorageController.T_FLAT:
				asc.setFlatTime(parser.makeDouble());
				break;
			case StorageController.T_DN_RAMP:
				asc.setDnRampTime(parser.makeDouble());
				break;
			case StorageController.KW_THRESHOLD:
				asc.setKWThreshold(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(activeStorageControllerObj, paramPointer - StorageController.NumPropsThisClass);
				break;
			}

			// side effects of setting properties above
			switch (paramPointer) {
			case KW_TARGET:
				asc.setHalfKWBand( asc.getPctKWBand() / 200.0 * asc.getKWTarget() );
				break;
			case KW_BAND:
				asc.setHalfKWBand( asc.getPctKWBand() / 200.0 * asc.getKWTarget() );
				asc.setKWThreshold( asc.getKWTarget() * 0.75 );
				break;
			case PF_BAND:
				asc.setHalfPFBand(asc.getPFBand() / 2.0);
				break;
			case MODE_DISCHARGE:
				if (asc.getDischargeMode() == StorageController.FOLLOW)
					asc.setDischargeTriggerTime(12.0);  // noon
				break;
			case ELEMENT_LIST:
				// levelize the list
				asc.getFleetPointerList().clear();  // clear this for resetting on first sample
				asc.setFleetListChanged(true);
				asc.setElementListSpecified(true);
				asc.setFleetSize(asc.getStorageNameList().size());
				// realloc weights to be same size as possible number of storage elements
				asc.setWeights( Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
				for (int i = 0; i < asc.getFleetSize(); i++)
					asc.getWeights()[i] = 1.0;
				break;
			case YEARLY:
				asc.setYearlyShapeObj( (LoadShapeObj) DSSGlobals.loadShapeClass.find(asc.getYearlyShape()) );
				if (asc.getYearlyShapeObj() == null)
					DSSGlobals.doSimpleMsg("Yearly loadshape \"" + asc.getYearlyShape() + "\" not found.", 14404);
				break;
			case DAILY:
				asc.setDailyShapeObj( (LoadShapeObj) DSSGlobals.loadShapeClass.find(asc.getDailyShape()) );
				if (asc.getDailyShapeObj() == null)
					DSSGlobals.doSimpleMsg("Daily loadshape \"" + asc.getDailyShape() + "\" not found.", 14405);
				break;
			case DUTY:
				asc.setDutyShapeObj( (LoadShapeObj) DSSGlobals.loadShapeClass.find(asc.getDutyShape()) );
				if (asc.getDutyShapeObj() == null)
					DSSGlobals.doSimpleMsg("Dutycycle loadshape \"" + asc.getDutyShape() + "\" not found.", 14406);
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		asc.recalcElementData();

		return result;
	}

	@Override
	protected int makeLike(String storageControllerName) {
		int result = 0;
		/* See if we can find this StorageController name in the present collection */
		StorageControllerObj otherStorageController = (StorageControllerObj) find(storageControllerName);
		if (otherStorageController != null) {
			StorageControllerObj asc = activeStorageControllerObj;

			asc.setNPhases(otherStorageController.getNPhases());
			asc.setNConds(otherStorageController.getNConds());  // force reallocation of terminal stuff

			asc.setElementName(otherStorageController.getElementName());
			asc.setControlledElement(otherStorageController.getControlledElement());  // pointer to target circuit element
			asc.setMonitoredElement(otherStorageController.getMonitoredElement());  // pointer to target circuit element
			asc.setElementTerminal(otherStorageController.getElementTerminal());

			asc.setKWTarget(otherStorageController.getKWTarget());
			asc.setKWThreshold(otherStorageController.getKWThreshold());
			asc.setPctKWBand(otherStorageController.getPctKWBand());
			asc.setPFTarget(otherStorageController.getPFTarget());
			asc.setPFBand(otherStorageController.getPFBand());
			asc.setHalfPFBand(otherStorageController.getHalfPFBand());

			asc.getStorageNameList().clear();
			for (int i = 0; i < otherStorageController.getStorageNameList().size(); i++)
				asc.getStorageNameList().add(otherStorageController.getStorageNameList().get(i - 1));

			asc.setFleetSize(asc.getStorageNameList().size());
			if (asc.getFleetSize() > 0) {
				asc.setWeights( Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
				for (int i = 0; i < asc.getFleetSize(); i++)
					asc.getWeights()[i] = otherStorageController.getWeights()[i];
			}

			asc.setDischargeMode(otherStorageController.getDischargeMode());
			asc.setChargeMode(otherStorageController.getChargeMode());
			asc.setDischargeTriggerTime(otherStorageController.getDischargeTriggerTime());
			asc.setChargeTriggerTime(otherStorageController.getChargeTriggerTime());
			asc.setPctKWRate(otherStorageController.getPctKWRate());
			asc.setPctKVArRate(otherStorageController.getPctKVArRate());
			asc.setPctChargeRate(otherStorageController.getPctChargeRate());
			asc.setPctFleetReserve(otherStorageController.getPctFleetReserve());
			asc.setYearlyShape(otherStorageController.getYearlyShape());
			asc.setDailyShape(otherStorageController.getDailyShape());
			asc.setDutyShape(otherStorageController.getDutyShape());
			asc.setDispatchVars(otherStorageController.isDispatchVars());
			asc.setShowEventLog(otherStorageController.isShowEventLog());
			asc.setInhibitHrs(otherStorageController.getInhibitHrs());

			asc.setUpRampTime(otherStorageController.getUpRampTime());
			asc.setFlatTime(otherStorageController.getFlatTime());
			asc.setDnRampTime(otherStorageController.getDnRampTime());

			// fill in private properties
			for (int i = 0; i < asc.getParentClass().getNumProperties(); i++)
				asc.setPropertyValue(i, otherStorageController.getPropertyValue(i));
		} else {
			DSSGlobals.doSimpleMsg("Error in StorageController makeLike: \"" + storageControllerName + "\" not found.", 370);
		}

		return result;
	}

}
