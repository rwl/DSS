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

	private static StorageControllerObj ActiveStorageControllerObj;

	public StorageControllerImpl() {
		super();

		this.className = "StorageController";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.STORAGE_CONTROL;

		defineProperties();

		String[] Commands = new String[this.numProperties];
		System.arraycopy(this.propertyName, 0, Commands, 0, this.numProperties);
		this.commandList = new CommandListImpl(Commands);
		this.commandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		numProperties = StorageController.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[StorageController.propELEMENT]                = "Element";
		propertyName[StorageController.propTERMINAL]               = "Terminal";
		propertyName[StorageController.propKWTARGET]               = "kWTarget";
		propertyName[StorageController.propKWBAND]                 = "%kWBand";
		propertyName[StorageController.propPFTARGET]               = "PFTarget";
		propertyName[StorageController.propPFBAND]                 = "PFBand";
		propertyName[StorageController.propELEMENTLIST]            = "ElementList";
		propertyName[StorageController.propWEIGHTS]                = "Weights";
		propertyName[StorageController.propMODEDISCHARGE]          = "ModeDischarge";
		propertyName[StorageController.propMODECHARGE]             = "ModeCharge";
		propertyName[StorageController.propTIMEDISCHARGETRIGGER]   = "TimeDischargeTrigger";
		propertyName[StorageController.propTIMECHARGETRIGGER]      = "TimeChargeTrigger";
		propertyName[StorageController.propRATEKW]                 = "%RatekW";
		propertyName[StorageController.propRATEKVAR]               = "%Ratekvar";
		propertyName[StorageController.propRATECHARGE]             = "%RateCharge";
		propertyName[StorageController.propRESERVE]                = "%Reserve";
		propertyName[StorageController.propKWHTOTAL]               = "kWhTotal";
		propertyName[StorageController.propKWTOTAL]                = "kWTotal";
		propertyName[StorageController.propKWHACTUAL]              = "kWhActual";
		propertyName[StorageController.propKWACTUAL]               = "kWActual";
		propertyName[StorageController.propKWNEED]                 = "kWneed";
		propertyName[StorageController.propPARTICIPATION]          = "%Participation";
		propertyName[StorageController.propYEARLY]                 = "Yearly";
		propertyName[StorageController.propDAILY]                  = "Daily";
		propertyName[StorageController.propDUTY]                   = "Duty";
		propertyName[StorageController.propEVENTLOG]               = "EventLog";
		propertyName[StorageController.propVARDISPATCH]            = "VarDispatch";
		propertyName[StorageController.propINHIBITTIME]            = "InhibitTime";
		propertyName[StorageController.propTUPRAMP]                = "Tup";
		propertyName[StorageController.propTFLAT]                  = "TFlat";
		propertyName[StorageController.propTDNRAMP]                = "Tdn";
		propertyName[StorageController.propKWTHRESHOLD]            = "kWThreshold";


		propertyHelp[StorageController.propELEMENT] =
				"Full object name of the circuit element, typically a line or transformer, "+
				"which the control is monitoring. There is no default; must be specified.";
		propertyHelp[StorageController.propTERMINAL] =
				"Number of the terminal of the circuit element to which the StorageController control is connected. "+
				"1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		propertyHelp[StorageController.propKWTARGET] =
				"kW target for Discharging. The storage element fleet is dispatched to try to hold the power in band "+
				"at least until the storage is depleted.";
		propertyHelp[StorageController.propKWBAND] =
				"Bandwidth (% of Target kW) of the dead band around the kW target value. Default is 2% (+/-1%)." +
				"No dispatch changes are attempted If the power in the monitored terminal stays within this band.";
		propertyHelp[StorageController.propPFTARGET] =
				"Power Factor target for dispatching the reactive power. Default is 0.96. The reactive power of the storage element fleet is dispatched to try to hold the power factor in band. "+
				"It is assumed that the storage element inverter can produce kvar up to its kVA limit regardless of storage level.";
		propertyHelp[StorageController.propPFBAND] =
				"Bandwidth of the Target power factor of the monitored element. of the dead band around the kvar target value. Default is 0.04 (+/- 0.02)." +
				"No dispatch changes of the kvar are attempted If the power factor of the monitored terminal stays within this band.";
		propertyHelp[StorageController.propELEMENTLIST] =
				"Array list of Storage elements to be controlled.  If not specified, all storage elements in the circuit not presently dispatched by another controller " +
				"are assumed dispatched by this controller.";
		propertyHelp[StorageController.propWEIGHTS] =
				"Array of proportional weights corresponding to each storage element in the ElementList. " +
				"The needed kW or kvar to get back to center band is dispatched to each storage element according to these weights. " +
				"Default is to set all weights to 1.0.";
		propertyHelp[StorageController.propMODEDISCHARGE] =
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
		propertyHelp[StorageController.propMODECHARGE] =
				"{Loadshape | Time*} Mode of operation for the CHARGE FUNCTION of this controller. " +
				"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is charged when the loadshape value is negative. " +
				"In Time mode, the storage charging FUNCTION is triggered at the specified %RateCharge at the specified sharge trigger time in fractional hours.";
		propertyHelp[StorageController.propTIMEDISCHARGETRIGGER] =
				"Default time of day (hr) for initiating Discharging of the fleet. During Follow or Time mode discharging is triggered at a fixed time " +
				"each day at this hour. If Follow mode, storage will be discharged to attempt to hold the load at or below the power level at the time of triggering. " +
				"In Time mode, the discharge is based on the %RatekW property value. " +
				"Set this to a negative value to ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored). ";
		propertyHelp[StorageController.propTIMECHARGETRIGGER] =
				"Default time of day (hr) for initiating charging in Time control mode. Set this to a negative value to ignore. Default is 2.0.  (0200)." +
				"When this value is >0 the storage fleet is set to charging at this time regardless of other control criteria to make sure storage is " +
				"topped off for the next discharge cycle.";
		propertyHelp[StorageController.propRATEKW] =
				"Sets the kW discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time.";
		propertyHelp[StorageController.propRATEKVAR] =
				"Sets the kvar discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time." ;
		propertyHelp[StorageController.propRATECHARGE] =
				"Sets the kW charging rate in % of rated capacity for each element of the fleet. Applies to TIME control mode and anytime charging mode is " +
				"entered due to a time trigger.";
		propertyHelp[StorageController.propRESERVE] =
				"Use this property to change the % reserve for each storage element under control of this controller. This might be used, for example, to " +
				"allow deeper discharges of storage or in case of emergency operation to use the remainder of the storage element.";
		propertyHelp[StorageController.propKWHTOTAL] =
				"(Read only). Total rated kWh energy storage capacity of storage elements controlled by this controller.";
		propertyHelp[StorageController.propKWTOTAL] =
				"(Read only). Total rated kW power capacity of storage elements controlled by this controller.";
		propertyHelp[StorageController.propKWHACTUAL] =
				"(Read only). Actual kWh output of all controlled storage elements. ";
		propertyHelp[StorageController.propKWACTUAL] =
				"(Read only). Actual kW output of all controlled storage elements. ";
		propertyHelp[StorageController.propKWNEED] =
				"(Read only). KW needed to meet target.";
		propertyHelp[StorageController.propPARTICIPATION] =
				"Participation factor, %. Default = 100.";
		propertyHelp[StorageController.propYEARLY] =
				"Dispatch loadshape object, If any, for Yearly solution Mode.";
		propertyHelp[StorageController.propDAILY] =
				"Dispatch loadshape object, If any, for Daily solution mode.";
		propertyHelp[StorageController.propDUTY] =
				"Dispatch loadshape object, If any, for Dutycycle solution mode.";
		propertyHelp[StorageController.propEVENTLOG] =
				"{Yes/True | No/False} Default is No. Log control actions to Eventlog.";
		propertyHelp[StorageController.propVARDISPATCH] =
				"{Yes/True | No/False} Default is No. Flag to indicate whether or not to disatch vars as well as watts.";
		propertyHelp[StorageController.propINHIBITTIME] =
				"Hours (integer) to inhibit Discharging after going into Charge mode. Default is 5";

		propertyHelp[StorageController.propTUPRAMP]  = "Duration, hrs, of upramp part for SCHEDULE mode. Default is 0.25.";
		propertyHelp[StorageController.propTFLAT]    = "Duration, hrs, of flat part for SCHEDULE mode. Default is 2.0.";
		propertyHelp[StorageController.propTDNRAMP]  = "Duration, hrs, of downramp part for SCHEDULE mode. Default is 0.25.";
		propertyHelp[StorageController.propKWTHRESHOLD] = "Threshold, kW, for Follow mode. kW has to be above this value for the Storage element " +
				"to be dispatched on. Defaults to 75% of the kWTarget value. Must reset this property after " +
				"setting kWTarget if you want a different value.";

		activeProperty = StorageController.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	@Override
	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new StorageControllerObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	@Override
	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveStorageControllerObj((StorageControllerObj) elementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveStorageControllerObj());

		int Result = 0;

		StorageControllerObj asc = getActiveStorageControllerObj();

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
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + getName() +"."+ asc.getName() + "\"", 14407);
				break;
			case StorageController.propELEMENT:
				asc.setElementName(Param.toLowerCase());
				break;
			case StorageController.propTERMINAL:
				asc.setElementTerminal(parser.makeInteger());
				break;
			case StorageController.propKWTARGET:
				asc.setkWTarget(parser.makeDouble());
				break;
			case StorageController.propKWBAND:
				asc.setPctkWBand(parser.makeDouble());
				break;
			case StorageController.propPFTARGET:
				asc.setPFTarget( Utilities.convertPFToPFRange2(parser.makeDouble()) );
				break;
			case StorageController.propPFBAND:
				asc.setPFBand(parser.makeDouble());
				break;
			case StorageController.propELEMENTLIST:
				Utilities.interpretStringListArray(Param, asc.getStorageNameList());
				break;
			case StorageController.propWEIGHTS:
				asc.setFleetSize(asc.getStorageNameList().size());
				if (asc.getFleetSize() > 0) {
					asc.setWeights( (double[]) Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
					Utilities.interpretDblArray(Param, asc.getFleetSize(), asc.getWeights());
				}
				break;
			case StorageController.propMODEDISCHARGE:
				asc.setDischargeMode(asc.interpretMode(propMODEDISCHARGE, Param));
				break;
			case StorageController.propMODECHARGE:
				asc.setChargeMode(asc.interpretMode(propMODECHARGE, Param));
				break;
			case StorageController.propTIMEDISCHARGETRIGGER:
				asc.setDischargeTriggerTime(parser.makeDouble());
				break;
			case StorageController.propTIMECHARGETRIGGER:
				asc.setChargeTriggerTime(parser.makeDouble());
				break;
			case StorageController.propRATEKW:
				asc.setPctKWRate(parser.makeDouble());
				break;
			case StorageController.propRATEKVAR:
				asc.setPctkvarRate(parser.makeDouble());
				break;
			case StorageController.propRATECHARGE:
				asc.setPctChargeRate(parser.makeDouble());
				break;
			case StorageController.propRESERVE:
				asc.setPctFleetReserve(parser.makeDouble());
				break;
			case StorageController.propKWHTOTAL:
				// do nothing (read only)
				break;
			case StorageController.propKWTOTAL:
				// do nothing (read only)
				break;
			case StorageController.propKWHACTUAL:
				// do nothing (read only)
				break;
			case StorageController.propKWACTUAL:
				// do nothing (read only)
				break;
			case StorageController.propKWNEED:
				// do nothing (read only)
				break;
			case StorageController.propPARTICIPATION:
				break;
			case StorageController.propYEARLY:
				asc.setYearlyShape(Param);
				break;
			case StorageController.propDAILY:
				asc.setDailyShape(Param);
				break;
			case StorageController.propDUTY:
				asc.setDutyShape(Param);
				break;
			case StorageController.propEVENTLOG:
				asc.setShowEventLog(Utilities.interpretYesNo(Param));
				break;
			case StorageController.propVARDISPATCH:
				asc.setDispatchVars(Utilities.interpretYesNo(Param));
				break;
			case StorageController.propINHIBITTIME:
				asc.setInhibitHrs( Math.max(1, parser.makeInteger()) );  // >= 1
				break;
			case StorageController.propTUPRAMP:
				asc.setUpRamptime(parser.makeDouble());
				break;
			case StorageController.propTFLAT:
				asc.setFlatTime(parser.makeDouble());
				break;
			case StorageController.propTDNRAMP:
				asc.setDnrampTime(parser.makeDouble());
				break;
			case StorageController.propKWTHRESHOLD:
				asc.setkWThreshold(parser.makeDouble());
				break;
			default:
				// inherited parameters
				classEdit(ActiveStorageControllerObj, ParamPointer - StorageController.NumPropsThisClass);
				break;
			}

			// side effects of setting properties above
			switch (ParamPointer) {
			case propKWTARGET:
				asc.setHalfkWBand( asc.getPctkWBand() / 200.0 * asc.getkWTarget() );
				break;
			case propKWBAND:
				asc.setHalfkWBand( asc.getPctkWBand() / 200.0 * asc.getkWTarget() );
				asc.setkWThreshold( asc.getkWTarget() * 0.75 );
				break;
			case propPFBAND:
				asc.setHalfPFBand(asc.getPFBand() / 2.0);
				break;
			case propMODEDISCHARGE:
				if (asc.getDischargeMode() == StorageController.MODEFOLLOW)
					asc.setDischargeTriggerTime(12.0);  // noon
				break;
			case propELEMENTLIST:
				// levelize the list
				asc.getFleetPointerList().clear();  // clear this for resetting on first sample
				asc.setFleetListChanged(true);
				asc.setElementListSpecified(true);
				asc.setFleetSize(asc.getStorageNameList().size());
				// realloc weights to be same size as possible number of storage elements
				asc.setWeights( (double[]) Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
				for (int i = 0; i < asc.getFleetSize(); i++)
					asc.getWeights()[i] = 1.0;
				break;
			case propYEARLY:
				asc.setYearlyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(asc.getYearlyShape()) );
				if (asc.getYearlyShapeObj() == null)
					Globals.doSimpleMsg("Yearly loadshape \"" + asc.getYearlyShape() + "\" not found.", 14404);
				break;
			case propDAILY:
				asc.setDailyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(asc.getDailyShape()) );
				if (asc.getDailyShapeObj() == null)
					Globals.doSimpleMsg("Daily loadshape \"" + asc.getDailyShape() + "\" not found.", 14405);
				break;
			case propDUTY:
				asc.setDutyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(asc.getDutyShape()) );
				if (asc.getDutyShapeObj() == null)
					Globals.doSimpleMsg("Dutycycle loadshape \"" + asc.getDutyShape() + "\" not found.", 14406);
				break;
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		asc.recalcElementData();

		return Result;
	}

	@Override
	protected int makeLike(String StorageControllerName) {
		int Result = 0;
		/* See if we can find this StorageController name in the present collection */
		StorageControllerObj OtherStorageController = (StorageControllerObj) find(StorageControllerName);
		if (OtherStorageController != null) {
			StorageControllerObj asc = getActiveStorageControllerObj();

			asc.setNPhases(OtherStorageController.getNPhases());
			asc.setNConds(OtherStorageController.getNConds());  // force reallocation of terminal stuff

			asc.setElementName(OtherStorageController.getElementName());
			asc.setControlledElement(OtherStorageController.getControlledElement());  // pointer to target circuit element
			asc.setMonitoredElement(OtherStorageController.getMonitoredElement());  // pointer to target circuit element
			asc.setElementTerminal(OtherStorageController.getElementTerminal());

			asc.setkWTarget(OtherStorageController.getkWTarget());
			asc.setkWThreshold(OtherStorageController.getkWThreshold());
			asc.setPctkWBand(OtherStorageController.getPctkWBand());
			asc.setPFTarget(OtherStorageController.getPFTarget());
			asc.setPFBand(OtherStorageController.getPFBand());
			asc.setHalfPFBand(OtherStorageController.getHalfPFBand());

			asc.getStorageNameList().clear();
			for (int i = 0; i < OtherStorageController.getStorageNameList().size(); i++)
				asc.getStorageNameList().add(OtherStorageController.getStorageNameList().get(i - 1));

			asc.setFleetSize(asc.getStorageNameList().size());
			if (asc.getFleetSize() > 0) {
				asc.setWeights( (double[]) Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
				for (int i = 0; i < asc.getFleetSize(); i++)
					asc.getWeights()[i] = OtherStorageController.getWeights()[i];
			}

			asc.setDischargeMode(OtherStorageController.getDischargeMode());
			asc.setChargeMode(OtherStorageController.getChargeMode());
			asc.setDischargeTriggerTime(OtherStorageController.getDischargeTriggerTime());
			asc.setChargeTriggerTime(OtherStorageController.getChargeTriggerTime());
			asc.setPctKWRate(OtherStorageController.getPctKWRate());
			asc.setPctkvarRate(OtherStorageController.getPctkvarRate());
			asc.setPctChargeRate(OtherStorageController.getPctChargeRate());
			asc.setPctFleetReserve(OtherStorageController.getPctFleetReserve());
			asc.setYearlyShape(OtherStorageController.getYearlyShape());
			asc.setDailyShape(OtherStorageController.getDailyShape());
			asc.setDutyShape(OtherStorageController.getDutyShape());
			asc.setDispatchVars(OtherStorageController.isDispatchVars());
			asc.setShowEventLog(OtherStorageController.isShowEventLog());
			asc.setInhibitHrs(OtherStorageController.getInhibitHrs());

			asc.setUpRamptime(OtherStorageController.getUpRamptime());
			asc.setFlatTime(OtherStorageController.getFlatTime());
			asc.setDnrampTime(OtherStorageController.getDnrampTime());

			// fill in private properties
			for (int i = 0; i < asc.getParentClass().getNumProperties(); i++)
				asc.setPropertyValue(i, OtherStorageController.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in StorageController makeLike: \"" + StorageControllerName + "\" not found.", 370);
		}

		return Result;
	}

	public static void setActiveStorageControllerObj(StorageControllerObj activeStorageControllerObj) {
		ActiveStorageControllerObj = activeStorageControllerObj;
	}

	public static StorageControllerObj getActiveStorageControllerObj() {
		return ActiveStorageControllerObj;
	}

}
