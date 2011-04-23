package com.epri.dss.control;

import com.epri.dss.common.CktElement;
import com.epri.dss.general.LoadShapeObj;

/**
 * A StorageController is a control element that is connected to a terminal
 * of another circuit element and sends dispatch  signals to a fleet of energy
 * storage elements it controls.
 *
 * A StorageController is defined by a New command:
 *
 * New StorageController.Name=myname Element=devclass.name terminal=[ 1|2|...] Elementlist = (elem1  elem2 ...)
 *
 * or ... ElementList = [File=filename] where storage class elements are listed
 * one to a line. If omitted, all storage elements found in the active circuit
 * are included by default and controlled as a fleet.
 *
 */
public interface StorageControllerObj extends ControlElem {

	void setPFBand(double Value);

	double getPFBand();

	double getFleetkW();

	double getFleetkWh();

	double getFleetReserveKWh();

	// FIXME Private members in OpenDSS

	double getkWTarget();

	void setkWTarget(double kWTarget);

	double getPctkWBand();

	void setPctkWBand(double pctkWBand);

	double getHalfkWBand();

	void setHalfkWBand(double halfkWBand);

	double getPFTarget();

	void setPFTarget(double pFTarget);

	double getTotalWeight();

	void setTotalWeight(double totalWeight);

	double getHalfPFBand();

	void setHalfPFBand(double halfPFBand);

	double getkWNeeded();

	void setkWNeeded(double kWNeeded);

	int getFleetSize();

	void setFleetSize(int fleetSize);

	int getFleetState();

	void setFleetState(int fleetState);

	String[] getStorageNameList();

	void setStorageNameList(String[] storageNameList);

	Object[] getFleetPointerList();

	void setFleetPointerList(Object[] fleetPointerList);

	double[] getWeights();

	void setWeights(double[] weights);

	boolean isElementListSpecified();

	void setElementListSpecified(boolean elementListSpecified);

	int getDischargeMode();

	void setDischargeMode(int dischargeMode);

	int getChargeMode();

	void setChargeMode(int chargeMode);

	double getDischargeTriggerTime();

	void setDischargeTriggerTime(double dischargeTriggerTime);

	double getChargeTriggerTime();

	void setChargeTriggerTime(double chargeTriggerTime);

	double getPctKWRate();

	void setPctKWRate(double pctKWRate);

	double getPctkvarRate();

	void setPctkvarRate(double pctkvarRate);

	double getPctChargeRate();

	void setPctChargeRate(double pctChargeRate);

	double getPctFleetReserve();

	void setPctFleetReserve(double pctFleetReserve);

	boolean isFleetListChanged();

	void setFleetListChanged(boolean fleetListChanged);

	boolean isChargingAllowed();

	void setChargingAllowed(boolean chargingAllowed);

	boolean isShowEventLog();

	void setShowEventLog(boolean showEventLog);

	boolean isDispatchVars();

	void setDispatchVars(boolean dispatchVars);

	boolean isDischargeTriggeredByTime();

	void setDischargeTriggeredByTime(boolean dischargeTriggeredByTime);

	boolean isDischargeInhibited();

	void setDischargeInhibited(boolean dischargeInhibited);

	boolean isOutOfOomph();

	void setOutOfOomph(boolean outOfOomph);

	int getInhibitHrs();

	void setInhibitHrs(int inhibitHrs);

	double getTotalKWCapacity();

	void setTotalKWCapacity(double totalKWCapacity);

	double getTotalKWhCapacity();

	void setTotalKWhCapacity(double totalKWhCapacity);

	String getYearlyShape();

	void setYearlyShape(String yearlyShape);

	LoadShapeObj getYearlyShapeObj();

	void setYearlyShapeObj(LoadShapeObj yearlyShapeObj);

	String getDailyShape();

	void setDailyShape(String dailyShape);

	LoadShapeObj getDailyShapeObj();

	void setDailyShapeObj(LoadShapeObj dailyShapeObj);

	String getDutyShape();

	void setDutyShape(String dutyShape);

	LoadShapeObj getDutyShapeObj();

	void setDutyShapeObj(LoadShapeObj dutyShapeObj);

	double[] getLoadShapeMult();

	void setLoadShapeMult(double[] loadShapeMult);

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

}
