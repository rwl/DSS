package com.epri.dss.control.impl;

import com.epri.dss.common.impl.DSSCktElement;
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

		this.Class_Name = "StorageController";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.STORAGE_CONTROL;

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);
	}

	protected void defineProperties() {

		NumProperties = StorageController.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();

		// Define property names
		PropertyName[StorageController.propELEMENT]                = "Element";
		PropertyName[StorageController.propTERMINAL]               = "Terminal";
		PropertyName[StorageController.propKWTARGET]               = "kWTarget";
		PropertyName[StorageController.propKWBAND]                 = "%kWBand";
		PropertyName[StorageController.propPFTARGET]               = "PFTarget";
		PropertyName[StorageController.propPFBAND]                 = "PFBand";
		PropertyName[StorageController.propELEMENTLIST]            = "ElementList";
		PropertyName[StorageController.propWEIGHTS]                = "Weights";
		PropertyName[StorageController.propMODEDISCHARGE]          = "ModeDischarge";
		PropertyName[StorageController.propMODECHARGE]             = "ModeCharge";
		PropertyName[StorageController.propTIMEDISCHARGETRIGGER]   = "TimeDischargeTrigger";
		PropertyName[StorageController.propTIMECHARGETRIGGER]      = "TimeChargeTrigger";
		PropertyName[StorageController.propRATEKW]                 = "%RatekW";
		PropertyName[StorageController.propRATEKVAR]               = "%Ratekvar";
		PropertyName[StorageController.propRATECHARGE]             = "%RateCharge";
		PropertyName[StorageController.propRESERVE]                = "%Reserve";
		PropertyName[StorageController.propKWHTOTAL]               = "kWhTotal";
		PropertyName[StorageController.propKWTOTAL]                = "kWTotal";
		PropertyName[StorageController.propKWHACTUAL]              = "kWhActual";
		PropertyName[StorageController.propKWACTUAL]               = "kWActual";
		PropertyName[StorageController.propKWNEED]                 = "kWneed";
		PropertyName[StorageController.propPARTICIPATION]          = "%Participation";
		PropertyName[StorageController.propYEARLY]                 = "Yearly";
		PropertyName[StorageController.propDAILY]                  = "Daily";
		PropertyName[StorageController.propDUTY]                   = "Duty";
		PropertyName[StorageController.propEVENTLOG]               = "EventLog";
		PropertyName[StorageController.propVARDISPATCH]            = "VarDispatch";
		PropertyName[StorageController.propINHIBITTIME]            = "InhibitTime";


		PropertyHelp[StorageController.propELEMENT] =
				"Full object name of the circuit element, typically a line or transformer, "+
				"which the control is monitoring. There is no default; must be specified.";
		PropertyHelp[StorageController.propTERMINAL] =
				"Number of the terminal of the circuit element to which the StorageController control is connected. "+
				"1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.";
		PropertyHelp[StorageController.propKWTARGET] =
				"kW target for Discharging. The storage element fleet is dispatched to try to hold the power in band "+
				"at least until the storage is depleted.";
		PropertyHelp[StorageController.propKWBAND] =
				"Bandwidth (% of Target kW) of the dead band around the kW target value. Default is 2% (+/-1%)." +
				"No dispatch changes are attempted If the power in the monitored terminal stays within this band.";
		PropertyHelp[StorageController.propPFTARGET] =
				"Power Factor target for dispatching the reactive power. Default is 0.96. The reactive power of the storage element fleet is dispatched to try to hold the power factor in band. "+
				"It is assumed that the storage element inverter can produce kvar up to its kVA limit regardless of storage level.";
		PropertyHelp[StorageController.propPFBAND] =
				"Bandwidth of the Target power factor of the monitored element. of the dead band around the kvar target value. Default is 0.04 (+/- 0.02)." +
				"No dispatch changes of the kvar are attempted If the power factor of the monitored terminal stays within this band.";
		PropertyHelp[StorageController.propELEMENTLIST] =
				"Array list of Storage elements to be controlled.  If not specified, all storage elements in the circuit not presently dispatched by another controller " +
				"are assumed dispatched by this controller.";
		PropertyHelp[StorageController.propWEIGHTS] =
				"Array of proportional weights corresponding to each storage element in the ElementList. " +
				"The needed kW or kvar to get back to center band is dispatched to each storage element according to these weights. " +
				"Default is to set all weights to 1.0.";
		PropertyHelp[StorageController.propMODEDISCHARGE] =
				"{PeakShave* | Follow | Support | Loadshape | Time} Mode of operation for the DISCHARGE FUNCTION of this controller. " +
				"In PeakShave mode (Default), the control attempts to discharge storage to keep power in the monitored element below the kWTarget. " +
				"In Follow mode, the control is triggered by time and resets the kWTarget value to the present monitored element power. " +
				"It then attempts to discharge storage to keep power in the monitored element below the new kWTarget. See TimeDischargeTrigger." +
				"In Support mode, the control operates oppositely of PeakShave mode: storage is discharged to keep kW power output up near the target. " +
				"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is discharged when the loadshape value is positive. " +
				"In Time mode, the storage discharge is turned on at the specified %RatekW and %Ratekvar at the specified discharge trigger time in fractional hours.";
		PropertyHelp[StorageController.propMODECHARGE] =
				"{Loadshape | Time*} Mode of operation for the CHARGE FUNCTION of this controller. " +
				"In Loadshape mode, both charging and discharging precisely follows the per unit loadshape. " +
				"Storage is charged when the loadshape value is negative. " +
				"In Time mode, the storage charging FUNCTION is triggered at the specified %RateCharge at the specified sharge trigger time in fractional hours.";
		PropertyHelp[StorageController.propTIMEDISCHARGETRIGGER] =
				"Default time of day (hr) for initiating Discharging of the fleet. During Follow or Time mode discharging is triggered at a fixed time " +
				"each day at this hour. If Follow mode, storage will be discharged to attempt to hold the load at or below the power level at the time of triggering. " +
				"In Time mode, the discharge is based on the %RatekW property value. " +
				"Set this to a negative value to ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored). ";
		PropertyHelp[StorageController.propTIMECHARGETRIGGER] =
				"Default time of day (hr) for initiating charging in Time control mode. Set this to a negative value to ignore. Default is 2.0.  (0200)." +
				"When this value is >0 the storage fleet is set to charging at this time regardless of other control criteria to make sure storage is " +
				"topped off for the next discharge cycle.";
		PropertyHelp[StorageController.propRATEKW] =
				"Sets the kW discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time.";
		PropertyHelp[StorageController.propRATEKVAR] =
				"Sets the kvar discharge rate in % of rated capacity for each element of the fleet. Applies to TIME control mode or anytime discharging is triggered " +
				"by time." ;
		PropertyHelp[StorageController.propRATECHARGE] =
				"Sets the kW charging rate in % of rated capacity for each element of the fleet. Applies to TIME control mode and anytime charging mode is " +
				"entered due to a time trigger.";
		PropertyHelp[StorageController.propRESERVE] =
				"Use this property to change the % reserve for each storage element under control of this controller. This might be used, for example, to " +
				"allow deeper discharges of storage or in case of emergency operation to use the remainder of the storage element.";
		PropertyHelp[StorageController.propKWHTOTAL] =
				"(Read only). Total rated kWh energy storage capacity of storage elements controlled by this controller.";
		PropertyHelp[StorageController.propKWTOTAL] =
				"(Read only). Total rated kW power capacity of storage elements controlled by this controller.";
		PropertyHelp[StorageController.propKWHACTUAL] =
				"(Read only). Actual kWh output of all controlled storage elements. ";
		PropertyHelp[StorageController.propKWACTUAL] =
				"(Read only). Actual kW output of all controlled storage elements. ";
		PropertyHelp[StorageController.propKWNEED] =
				"(Read only). KW needed to meet target.";
		PropertyHelp[StorageController.propPARTICIPATION] =
				"Participation factor, %. Default = 100.";
		PropertyHelp[StorageController.propYEARLY] =
				"Dispatch loadshape object, If any, for Yearly solution Mode.";
		PropertyHelp[StorageController.propDAILY] =
				"Dispatch loadshape object, If any, for Daily solution mode.";
		PropertyHelp[StorageController.propDUTY] =
				"Dispatch loadshape object, If any, for Dutycycle solution mode.";
		PropertyHelp[StorageController.propEVENTLOG] =
				"{Yes/True | No/False} Default is No. Log control actions to Eventlog.";
		PropertyHelp[StorageController.propVARDISPATCH] =
				"{Yes/True | No/False} Default is No. Flag to indicate whether or not to disatch vars as well as watts.";
		PropertyHelp[StorageController.propINHIBITTIME] =
				"Hours (integer) to inhibit Discharging after going into Charge mode. Default is 5";

		ActiveProperty = StorageController.NumPropsThisClass -1;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
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
		setActiveCapControlObj(ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement((DSSCktElement) getActiveStorageControllerObj());

		int Result = 0;

		StorageControllerObj asc = getActiveStorageControllerObj();

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer >= 0) && (ParamPointer < NumProperties))
				asc.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ asc.getName() + "\"", 14407);
			case StorageController.propELEMENT:
				asc.setElementName(Param.toLowerCase());
			case StorageController.propTERMINAL:
				asc.setElementTerminal(parser.makeInteger());
			case StorageController.propKWTARGET:
				asc.setkWTarget(parser.makeDouble());
			case StorageController.propKWBAND:
				asc.setPctkWBand(parser.makeDouble());
			case StorageController.propPFTARGET:
				asc.setPFTarget( Utilities.convertPFToPFRange2(parser.makeDouble()) );
			case StorageController.propPFBAND:
				asc.setPFBand(parser.makeDouble());
			case StorageController.propELEMENTLIST:
				Utilities.interpretStringListArray(Param, asc.getStorageNameList());
			case StorageController.propWEIGHTS:
				asc.setFleetSize(asc.getStorageNameList().length;
				if (asc.getFleetSize() > 0) {
					asc.setWeights( (double[]) Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
					Utilities.interpretDblArray(Param, asc.getFleetSize(), asc.getWeights());
				}
			case StorageController.propMODEDISCHARGE:
				asc.setDischargeMode(asc.interpretMode(propMODEDISCHARGE, Param));
			case StorageController.propMODECHARGE:
				asc.setChargeMode(asc.interpretMode(propMODECHARGE, Param));
			case StorageController.propTIMEDISCHARGETRIGGER:
				asc.setDischargeTriggerTime(parser.makeDouble());
			case StorageController.propTIMECHARGETRIGGER:
				asc.setChargeTriggerTime(parser.makeDouble());
			case StorageController.propRATEKW:
				asc.setPctKWRate(parser.makeDouble());
			case StorageController.propRATEKVAR:
				asc.setPctkvarRate(parser.makeDouble());
			case StorageController.propRATECHARGE:
				asc.setPctChargeRate(parser.makeDouble());
			case StorageController.propRESERVE:
				asc.setPctFleetReserve(parser.makeDouble());
			case StorageController.propKWHTOTAL:
				// Do nothing (read only)
			case StorageController.propKWTOTAL:
				// Do nothing (Read only)
			case StorageController.propKWHACTUAL:
				// Do nothing (Read only)
			case StorageController.propKWACTUAL:
				// Do nothing (Read only)
			case StorageController.propKWNEED:
				// Do nothing (Read only)
			case StorageController.propPARTICIPATION:

			case StorageController.propYEARLY:
				asc.setYearlyShape(Param);
			case StorageController.propDAILY:
				asc.setDailyShape(Param);
			case StorageController.propDUTY:
				asc.setDutyShape(Param);
			case StorageController.propEVENTLOG:
				asc.setShowEventLog(Utilities.interpretYesNo(Param));
			case StorageController.propVARDISPATCH:
				asc.setDispatchVars(Utilities.interpretYesNo(Param));
			case StorageController.propINHIBITTIME:
				asc.setInhibitHrs( Math.max(1, parser.makeInteger()) );  // >=1
			default:
				// Inherited parameters
				classEdit(ActiveStorageControllerObj, ParamPointer - StorageController.NumPropsThisClass);
			}

			// Side effects of setting properties above
			switch (ParamPointer) {
			case propKWTARGET:
				asc.setHalfkWBand( asc.getPctkWBand() / 200.0 * asc.getkWTarget() );
			case propKWBAND:
				asc.setHalfkWBand( asc.getPctkWBand() / 200.0 * asc.getkWTarget() );
			case propPFBAND:
				asc.setHalfPFBand(asc.getPFBand() / 2.0);
			case propMODEDISCHARGE:
				if (asc.getDischargeMode() == StorageController.MODEFOLLOW)
					asc.setDischargeTriggerTime(12.0);  // Noon

			case propELEMENTLIST:
				// levelize the list
				asc.getFleetPointerList().clear();  // clear this for resetting on first sample
				asc.setFleetListChanged(true);
				asc.setElementListSpecified(true);
				asc.setFleetSize(asc.getStorageNameList().length);
				// Realloc weights to be same size as possible number of storage elements
				asc.setWeights( (double[]) Utilities.resizeArray(asc.getWeights(), asc.getFleetSize()) );
				for (int i = 0; i < asc.getFleetSize(); i++)
					asc.getWeights()[i] = 1.0;
			case propYEARLY:
				asc.setYearlyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(asc.getYearlyShape()) );
				if (asc.getYearlyShapeObj() == null)
					Globals.doSimpleMsg("Yearly loadshape \"" + asc.getYearlyShape() + "\" not found.", 14404);
			case propDAILY:
				asc.setDailyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(asc.getDailyShape()) );
				if (asc.getDailyShapeObj() == null)
					Globals.doSimpleMsg("Daily loadshape \"" + asc.getDailyShape() + "\" not found.", 14405);
			case propDUTY:
				asc.setDutyShapeObj( (LoadShapeObj) Globals.getLoadShapeClass().find(asc.getDutyShape()) );
				if (asc.getDutyShapeObj() == null)
					Globals.doSimpleMsg("Dutycycle loadshape \"" + asc.getDutyShape() + "\" not found.", 14406);
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
		StorageControllerObj OtherStorageController = find(StorageControllerName);
		if (OtherStorageController != null) {
			StorageControllerObj asc = getActiveStorageControllerObj();

			asc.setNPhases(OtherStorageController.getNPhases());
			asc.setNConds(OtherStorageController.getNConds());  // Force reallocation of terminal stuff

			asc.setElementName(OtherStorageController.getElementName());
			asc.setControlledElement(OtherStorageController.getControlledElement());  // Pointer to target circuit element
			asc.setMonitoredElement(OtherStorageController.getMonitoredElement());  // Pointer to target circuit element
			asc.setElementTerminal(OtherStorageController.getElementTerminal());

			asc.setkWTarget(OtherStorageController.getkWTarget());
			asc.setPctkWBand(OtherStorageController.getPctkWBand());
			asc.setPFTarget(OtherStorageController.getPFTarget());
			asc.setPFBand(OtherStorageController.getPFBand());
			asc.setHalfPFBand(OtherStorageController.getHalfPFBand());

			asc.getStorageNameList().clear();
			for (int i = 0; i < OtherStorageController.getStorageNameList().length; i++)
				asc.getStorageNameList().add(OtherStorageController.getStorageNameList().get(i - 1));

			asc.setFleetSize(asc.getStorageNameList().length);
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

			// Fill in private properties
			for (int i = 0; i < asc.getParentClass().getNumProperties(); i++)
				asc.setPropertyValue(i, OtherStorageController.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in StorageController makeLike: \"" + StorageControllerName + "\" Not Found.", 370);
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
