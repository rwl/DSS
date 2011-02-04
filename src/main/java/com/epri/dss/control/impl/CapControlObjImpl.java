package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.CapControlObj;
import com.epri.dss.delivery.CapacitorObj;

public class CapControlObjImpl extends ControlElemImpl implements CapControlObj {
	
	public enum CapControlType {
		CURRENTCONTROL,
		VOLTAGECONTROL,
		KVARCONTROL,
		TIMECONTROL,
		PFCONTROL,
		SRPCONTROL
	}
	
	private CapControlType ControlType;

	private int CTPhase, PTPhase;  // "ALL" is -1

	private double ON_Value,
		OFF_Value,
		PFON_Value,
		PFOFF_Value,
		CTRatio,
		PTRatio,
		ONDelay,
		OFFDelay,
		DeadTime,
		LastOpenTime;

	private boolean VOverride;
	private double Vmax, Vmin;

	private String CapacitorName;
	private CktElement MonitoredElement;
	private CapacitorObj ControlledCapacitor;
	private ControlAction PendingChange;
	private boolean ShouldSwitch;  // True: action is pending
	private boolean Armed;  // Control is armed for switching unless reset
	private ControlAction PresentState;
	private ControlAction InitialState;
	private int ControlActionHandle;
	private int CondOffset; // Offset for monitored terminal

	private Complex[] cBuffer;    // Complexarray buffer

	public CapControlObjImpl(DSSClassImpl ParClass, String CapControlName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public CapacitorObj getCapacitor() {
		return null;
	}
	
	private double normalizeToTOD(int h, double sec) {
		return 0.0;
	}
	
	public void setPendingChange(ControlAction Value) {
		
	}
	
	public ControlAction getPendingChange() {
		return PendingChange;
	}
	
	private void getControlVoltage(double ControlVoltage) {
		
	}
	
	private void getControlCurrent(double ControlCurrent) {
		
	}
	
	public CapControlType getControlType() {
		return ControlType;
	}

	public void setControlType(CapControlType controlType) {
		ControlType = controlType;
	}

	public double getON_Value() {
		return ON_Value;
	}

	public double getOFF_Value() {
		return OFF_Value;
	}

	public double getPFON_Value() {
		return PFON_Value;
	}

	public double getPFOFF_Value() {
		return PFOFF_Value;
	}

	public double getCTRatio() {
		return CTRatio;
	}

	public double getPTRatio() {
		return PTRatio;
	}

	public double getONDelay() {
		return ONDelay;
	}

	public double getOFFDelay() {
		return OFFDelay;
	}

	public double getDeadTime() {
		return DeadTime;
	}

	public boolean isVOverride() {
		return VOverride;
	}

	public double getVmax() {
		return Vmax;
	}

	public double getVmin() {
		return Vmin;
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
