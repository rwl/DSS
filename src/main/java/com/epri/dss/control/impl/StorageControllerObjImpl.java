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

}
