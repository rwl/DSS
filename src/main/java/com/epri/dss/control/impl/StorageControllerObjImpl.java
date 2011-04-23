package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.StorageControllerObj;
import com.epri.dss.general.LoadShapeObj;

public class StorageControllerObjImpl extends ControlElemImpl implements StorageControllerObj {

	private double kWTarget,
		pctkWBand,
		HalfkWBand,
		PFTarget,    // Range on this is 0..2 where 1..2 is leading
		TotalWeight;
	private double HalfPFBand;
	private double PFBand;
	private double kWNeeded;
	private int FleetSize;
	private int FleetState;

	private String[] StorageNameList;
	private Object[] FleetPointerList;
	private double[] Weights;

	private boolean ElementListSpecified;

	private int DischargeMode;
	private int ChargeMode;
	private double DischargeTriggerTime;
	private double ChargeTriggerTime;
	private double pctKWRate;
	private double pctkvarRate;
	private double pctChargeRate;
	private double pctFleetReserve;
	private boolean FleetListChanged;
	private boolean ChargingAllowed;
	private boolean ShowEventLog;
	private boolean DispatchVars;
	private boolean DischargeTriggeredByTime;
	private boolean DischargeInhibited;
	private boolean OutOfOomph;
	private int InhibitHrs;

	private double TotalKWCapacity;
	private double TotalKWhCapacity;

	private String YearlyShape;  // ='fixed' means no variation  on all the time
	private LoadShapeObj YearlyShapeObj;  // Shape for this Storage element
	private String DailyShape;  // Daily (24 HR) Storage element shape
	private LoadShapeObj DailyShapeObj;  // Daily Storage element Shape for this load
	private String DutyShape;  // Duty cycle load shape for changes typically less than one hour
	private LoadShapeObj DutyShapeObj;  // Shape for this Storage element

	private double[] LoadShapeMult;

	private CktElement MonitoredElement;

	public StorageControllerObjImpl(DSSClassImpl ParClass, String StorageControllerName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}


	// private void setPctReserve()
	private void setAllFleetValues() {

	}

	private void setFleetkWRate() {

	}

	private void setFleetkvarRate() {

	}

	private void setFleetChargeRate() {

	}

	private void setFleetToCharge() {

	}

	private void setFleetToDisCharge() {

	}

	private void setFleetToIdle() {

	}

	private void setFleetToExternal() {

	}

	private int interpretMode(int Opt, String S) {
		return 0;
	}

	private String getModeString(int Opt, int Mode) {
		return null;
	}

	private String getkWTotal(double Sum) {
		return null;
	}

	private String getkWhTotal(double Sum) {
		return null;
	}

	private String getkWhActual() {
		return null;
	}

	private String getkWActual() {
		return null;
	}

	private void calcYearlyMult(double Hr) {

	}

	private void calcDailyMult(double Hr) {

	}

	private void calcDutyMult(double Hr) {

	}

	private String returnElementsList() {
		return null;
	}

	private String returnWeightsList() {
		return null;
	}

	private boolean makeFleetList() {
		return false;
	}

	private void doLoadFollowMode() {

	}

	private void doLoadShapeMode() {

	}

	private void doTimeMode(int Opt) {

	}

	private double normalizeToTOD(int h, double sec) {
		return 0.0;
	}

	public void setPFBand(double Value) {

	}

	public double getPFBand() {
		return PFBand;
	}

	public double getFleetkW() {
		return 0.0;
	}

	public double getFleetkWh() {
		return 0.0;
	}

	public double getFleetReserveKWh() {
		return 0.0;
	}

	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {

	}

	@Override
	public void recalcElementData() {

	}

	@Override
	public void calcYPrim() {

	}

	/* Sample control quantities and set action times in Control Queue */
	@Override
	public void sample() {

	}

	/* Do the action that is pending from last sample */
	@Override
	public void doPendingAction(int Code, int ProxyHdl) {

	}

	/* Reset to initial defined state */
	@Override
	public void reset() {

	}

	@Override
	public void getInjCurrents(Complex[] Curr) {

	}

	@Override
	public void getCurrents(Complex[] Curr) {

	}

	@Override
	public void initPropertyValues(int ArrayOffset) {

	}

	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	// FIXME Private members in OpenDSS

	public double getkWTarget() {
		return kWTarget;
	}


	public void setkWTarget(double kWTarget) {
		this.kWTarget = kWTarget;
	}


	public double getPctkWBand() {
		return pctkWBand;
	}


	public void setPctkWBand(double pctkWBand) {
		this.pctkWBand = pctkWBand;
	}


	public double getHalfkWBand() {
		return HalfkWBand;
	}


	public void setHalfkWBand(double halfkWBand) {
		HalfkWBand = halfkWBand;
	}


	public double getPFTarget() {
		return PFTarget;
	}


	public void setPFTarget(double pFTarget) {
		PFTarget = pFTarget;
	}


	public double getTotalWeight() {
		return TotalWeight;
	}


	public void setTotalWeight(double totalWeight) {
		TotalWeight = totalWeight;
	}


	public double getHalfPFBand() {
		return HalfPFBand;
	}


	public void setHalfPFBand(double halfPFBand) {
		HalfPFBand = halfPFBand;
	}


	public double getkWNeeded() {
		return kWNeeded;
	}


	public void setkWNeeded(double kWNeeded) {
		this.kWNeeded = kWNeeded;
	}


	public int getFleetSize() {
		return FleetSize;
	}


	public void setFleetSize(int fleetSize) {
		FleetSize = fleetSize;
	}


	public int getFleetState() {
		return FleetState;
	}


	public void setFleetState(int fleetState) {
		FleetState = fleetState;
	}


	public String[] getStorageNameList() {
		return StorageNameList;
	}


	public void setStorageNameList(String[] storageNameList) {
		StorageNameList = storageNameList;
	}


	public Object[] getFleetPointerList() {
		return FleetPointerList;
	}


	public void setFleetPointerList(Object[] fleetPointerList) {
		FleetPointerList = fleetPointerList;
	}


	public double[] getWeights() {
		return Weights;
	}


	public void setWeights(double[] weights) {
		Weights = weights;
	}


	public boolean isElementListSpecified() {
		return ElementListSpecified;
	}


	public void setElementListSpecified(boolean elementListSpecified) {
		ElementListSpecified = elementListSpecified;
	}


	public int getDischargeMode() {
		return DischargeMode;
	}


	public void setDischargeMode(int dischargeMode) {
		DischargeMode = dischargeMode;
	}


	public int getChargeMode() {
		return ChargeMode;
	}


	public void setChargeMode(int chargeMode) {
		ChargeMode = chargeMode;
	}


	public double getDischargeTriggerTime() {
		return DischargeTriggerTime;
	}


	public void setDischargeTriggerTime(double dischargeTriggerTime) {
		DischargeTriggerTime = dischargeTriggerTime;
	}


	public double getChargeTriggerTime() {
		return ChargeTriggerTime;
	}


	public void setChargeTriggerTime(double chargeTriggerTime) {
		ChargeTriggerTime = chargeTriggerTime;
	}


	public double getPctKWRate() {
		return pctKWRate;
	}


	public void setPctKWRate(double pctKWRate) {
		this.pctKWRate = pctKWRate;
	}


	public double getPctkvarRate() {
		return pctkvarRate;
	}


	public void setPctkvarRate(double pctkvarRate) {
		this.pctkvarRate = pctkvarRate;
	}


	public double getPctChargeRate() {
		return pctChargeRate;
	}


	public void setPctChargeRate(double pctChargeRate) {
		this.pctChargeRate = pctChargeRate;
	}


	public double getPctFleetReserve() {
		return pctFleetReserve;
	}


	public void setPctFleetReserve(double pctFleetReserve) {
		this.pctFleetReserve = pctFleetReserve;
	}


	public boolean isFleetListChanged() {
		return FleetListChanged;
	}


	public void setFleetListChanged(boolean fleetListChanged) {
		FleetListChanged = fleetListChanged;
	}


	public boolean isChargingAllowed() {
		return ChargingAllowed;
	}


	public void setChargingAllowed(boolean chargingAllowed) {
		ChargingAllowed = chargingAllowed;
	}


	public boolean isShowEventLog() {
		return ShowEventLog;
	}


	public void setShowEventLog(boolean showEventLog) {
		ShowEventLog = showEventLog;
	}


	public boolean isDispatchVars() {
		return DispatchVars;
	}


	public void setDispatchVars(boolean dispatchVars) {
		DispatchVars = dispatchVars;
	}


	public boolean isDischargeTriggeredByTime() {
		return DischargeTriggeredByTime;
	}


	public void setDischargeTriggeredByTime(boolean dischargeTriggeredByTime) {
		DischargeTriggeredByTime = dischargeTriggeredByTime;
	}


	public boolean isDischargeInhibited() {
		return DischargeInhibited;
	}


	public void setDischargeInhibited(boolean dischargeInhibited) {
		DischargeInhibited = dischargeInhibited;
	}


	public boolean isOutOfOomph() {
		return OutOfOomph;
	}


	public void setOutOfOomph(boolean outOfOomph) {
		OutOfOomph = outOfOomph;
	}


	public int getInhibitHrs() {
		return InhibitHrs;
	}


	public void setInhibitHrs(int inhibitHrs) {
		InhibitHrs = inhibitHrs;
	}


	public double getTotalKWCapacity() {
		return TotalKWCapacity;
	}


	public void setTotalKWCapacity(double totalKWCapacity) {
		TotalKWCapacity = totalKWCapacity;
	}


	public double getTotalKWhCapacity() {
		return TotalKWhCapacity;
	}


	public void setTotalKWhCapacity(double totalKWhCapacity) {
		TotalKWhCapacity = totalKWhCapacity;
	}


	public String getYearlyShape() {
		return YearlyShape;
	}


	public void setYearlyShape(String yearlyShape) {
		YearlyShape = yearlyShape;
	}


	public LoadShapeObj getYearlyShapeObj() {
		return YearlyShapeObj;
	}


	public void setYearlyShapeObj(LoadShapeObj yearlyShapeObj) {
		YearlyShapeObj = yearlyShapeObj;
	}


	public String getDailyShape() {
		return DailyShape;
	}


	public void setDailyShape(String dailyShape) {
		DailyShape = dailyShape;
	}


	public LoadShapeObj getDailyShapeObj() {
		return DailyShapeObj;
	}


	public void setDailyShapeObj(LoadShapeObj dailyShapeObj) {
		DailyShapeObj = dailyShapeObj;
	}


	public String getDutyShape() {
		return DutyShape;
	}


	public void setDutyShape(String dutyShape) {
		DutyShape = dutyShape;
	}


	public LoadShapeObj getDutyShapeObj() {
		return DutyShapeObj;
	}


	public void setDutyShapeObj(LoadShapeObj dutyShapeObj) {
		DutyShapeObj = dutyShapeObj;
	}


	public double[] getLoadShapeMult() {
		return LoadShapeMult;
	}


	public void setLoadShapeMult(double[] loadShapeMult) {
		LoadShapeMult = loadShapeMult;
	}


	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}


	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

}
