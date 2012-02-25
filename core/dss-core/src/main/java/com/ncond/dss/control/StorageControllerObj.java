package com.ncond.dss.control;

import java.util.List;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.general.LoadShapeObj;

import org.apache.commons.math.complex.Complex;

/**
 * A control element that is connected to a terminal
 * of another circuit element and sends dispatch  signals to a fleet of energy
 * storage elements it controls.
 *
 *   new storageController.name=myName element=devClass.name terminal=[ 1|2|...] elementList=(elem1  elem2 ...)
 *
 * or ... ElementList=[File=filename] where storage class elements are listed
 * one to a line. If omitted, all storage elements found in the active circuit
 * are included by default and controlled as a fleet.
 *
 */
public interface StorageControllerObj extends ControlElem {

	void setPFBand(double Value);

	double getPFBand();

	double getFleetKW();

	double getFleetkWh();

	double getFleetReserveKWh();

	// FIXME Private method in OpenDSS
	int interpretMode(int opt, String s);


	// FIXME Private members in OpenDSS

	double getKWTarget();

	void setKWTarget(double kWTarget);

	void setKWThreshold(double kWThreshold);

	double getKWThreshold();

	double getPctKWBand();

	void setPctKWBand(double pctkWBand);

	double getHalfKWBand();

	void setHalfKWBand(double halfkWBand);

	double getPFTarget();

	void setPFTarget(double pFTarget);

	double getTotalWeight();

	void setTotalWeight(double totalWeight);

	double getHalfPFBand();

	void setHalfPFBand(double halfPFBand);

	double getKWNeeded();

	void setKWNeeded(double kWNeeded);

	int getFleetSize();

	void setFleetSize(int fleetSize);

	int getFleetState();

	void setFleetState(int fleetState);

	List<String> getStorageNameList();

	void setStorageNameList(List<String> storageNameList);

	List<Object> getFleetPointerList();

	void setFleetPointerList(List<Object> fleetPointerList);

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

	double getPctKVArRate();

	void setPctKVArRate(double pctkVArRate);

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

	boolean isOutOfEnergy();

	void setOutOfEnergy(boolean outOfEnergy);

	int getInhibitHrs();

	void setInhibitHrs(int inhibitHrs);

	double getUpRampTime();

	void setUpRampTime(double upRampTime);

	double getFlatTime();

	void setFlatTime(double flatTime);

	double getDnRampTime();

	void setDnRampTime(double dnRampTime);

	double getUpPlusFlat();

	void setUpPlusFlat(double upPlusFlat);

	double getUpPlusFlatPlusDn();

	void setUpPlusFlatPlusDn(double upPlusFlatPlusDn);

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

	Complex getLoadShapeMult();

	void setLoadShapeMult(Complex loadShapeMult);

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

}
