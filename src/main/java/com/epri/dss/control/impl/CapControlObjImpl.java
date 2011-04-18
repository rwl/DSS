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
	
	// FIXME Private properties in OpenDSS

	public int getCTPhase() {
		return CTPhase;
	}

	public void setCTPhase(int cTPhase) {
		CTPhase = cTPhase;
	}

	public int getPTPhase() {
		return PTPhase;
	}

	public void setPTPhase(int pTPhase) {
		PTPhase = pTPhase;
	}

	public double getLastOpenTime() {
		return LastOpenTime;
	}

	public void setLastOpenTime(double lastOpenTime) {
		LastOpenTime = lastOpenTime;
	}

	public String getCapacitorName() {
		return CapacitorName;
	}

	public void setCapacitorName(String capacitorName) {
		CapacitorName = capacitorName;
	}

	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

	public CapacitorObj getControlledCapacitor() {
		return ControlledCapacitor;
	}

	public void setControlledCapacitor(CapacitorObj controlledCapacitor) {
		ControlledCapacitor = controlledCapacitor;
	}

	public boolean isShouldSwitch() {
		return ShouldSwitch;
	}

	public void setShouldSwitch(boolean shouldSwitch) {
		ShouldSwitch = shouldSwitch;
	}

	public boolean isArmed() {
		return Armed;
	}

	public void setArmed(boolean armed) {
		Armed = armed;
	}

	public ControlAction getPresentState() {
		return PresentState;
	}

	public void setPresentState(ControlAction presentState) {
		PresentState = presentState;
	}

	public ControlAction getInitialState() {
		return InitialState;
	}

	public void setInitialState(ControlAction initialState) {
		InitialState = initialState;
	}

	public int getControlActionHandle() {
		return ControlActionHandle;
	}

	public void setControlActionHandle(int controlActionHandle) {
		ControlActionHandle = controlActionHandle;
	}

	public int getCondOffset() {
		return CondOffset;
	}

	public void setCondOffset(int condOffset) {
		CondOffset = condOffset;
	}

	public Complex[] getcBuffer() {
		return cBuffer;
	}

	public void setcBuffer(Complex[] cBuffer) {
		this.cBuffer = cBuffer;
	}

	public void setON_Value(double oN_Value) {
		ON_Value = oN_Value;
	}

	public void setOFF_Value(double oFF_Value) {
		OFF_Value = oFF_Value;
	}

	public void setPFON_Value(double pFON_Value) {
		PFON_Value = pFON_Value;
	}

	public void setPFOFF_Value(double pFOFF_Value) {
		PFOFF_Value = pFOFF_Value;
	}

	public void setCTRatio(double cTRatio) {
		CTRatio = cTRatio;
	}

	public void setPTRatio(double pTRatio) {
		PTRatio = pTRatio;
	}

	public void setONDelay(double oNDelay) {
		ONDelay = oNDelay;
	}

	public void setOFFDelay(double oFFDelay) {
		OFFDelay = oFFDelay;
	}

	public void setDeadTime(double deadTime) {
		DeadTime = deadTime;
	}

	public void setVOverride(boolean vOverride) {
		VOverride = vOverride;
	}

	public void setVmax(double vmax) {
		Vmax = vmax;
	}

	public void setVmin(double vmin) {
		Vmin = vmin;
	}

}
