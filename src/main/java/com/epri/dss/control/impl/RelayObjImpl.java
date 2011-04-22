package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.RelayObj;
import com.epri.dss.general.TCC_CurveObj;

public class RelayObjImpl extends ControlElemImpl implements RelayObj {

	private int ControlType;

	/* OverCurrent Relay */
	private TCC_CurveObj PhaseCurve, GroundCurve;

	private double PhaseTrip, GroundTrip, PhaseInst, GroundInst;

	private double[] RecloseIntervals;
	private int NumReclose;

	private double ResetTime,
		Delay_Time,
		Breaker_time,
		TDPhase, TDGround;

	private String RelayTarget;

	/* Over/Under Voltage Relay */
	// Curves assumed in per unit of base voltage
	private TCC_CurveObj OVcurve, UVCurve;

	private double Vbase,   // line-neut volts base
		kVBase;

	/* 46 Relay  Neg Seq Current */
	private double PickupAmps46,
		PctPickup46,
		BaseAmps46,
		Isqt46;

	/* 47 Relay */
	private double PickupVolts47,
		PctPickup47;

	/* Generic Relay */
	private double OverTrip,
		UnderTrip;

	private String MonitoredElementName;
	private int MonitoredElementTerminal;
	private CktElement MonitoredElement;

	private ControlAction PresentState;

	private int OperationCount;

	private boolean LockedOut,
		ArmedForClose,
		ArmedForOpen,
		PhaseTarget, GroundTarget;

	private double NextTriptime;
	private int LastEventHandle;

	private int CondOffset;  // Offset for monitored terminal

	private Complex[] cBuffer;  // Complexarray buffer

	public RelayObjImpl(DSSClassImpl ParClass, String RelayName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private void interpretRelayAction(String Action) {

	}

	private void interpretRelayType(String S) {

	}

	private void overcurrentLogic() {

	}

	private void voltageLogic() {

	}

	private void revPowerLogic() {

	}

	private void negSeq46Logic() {

	}

	private void negSeq47Logic() {

	}

	private void genericLogic() {

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

	public int getControlType() {
		return ControlType;
	}

	public void setControlType(int controlType) {
		ControlType = controlType;
	}

	public TCC_CurveObj getPhaseCurve() {
		return PhaseCurve;
	}

	public void setPhaseCurve(TCC_CurveObj phaseCurve) {
		PhaseCurve = phaseCurve;
	}

	public TCC_CurveObj getGroundCurve() {
		return GroundCurve;
	}

	public void setGroundCurve(TCC_CurveObj groundCurve) {
		GroundCurve = groundCurve;
	}

	public double getPhaseTrip() {
		return PhaseTrip;
	}

	public void setPhaseTrip(double phaseTrip) {
		PhaseTrip = phaseTrip;
	}

	public double getGroundTrip() {
		return GroundTrip;
	}

	public void setGroundTrip(double groundTrip) {
		GroundTrip = groundTrip;
	}

	public double getPhaseInst() {
		return PhaseInst;
	}

	public void setPhaseInst(double phaseInst) {
		PhaseInst = phaseInst;
	}

	public double getGroundInst() {
		return GroundInst;
	}

	public void setGroundInst(double groundInst) {
		GroundInst = groundInst;
	}

	public double[] getRecloseIntervals() {
		return RecloseIntervals;
	}

	public void setRecloseIntervals(double[] recloseIntervals) {
		RecloseIntervals = recloseIntervals;
	}

	public int getNumReclose() {
		return NumReclose;
	}

	public void setNumReclose(int numReclose) {
		NumReclose = numReclose;
	}

	public double getResetTime() {
		return ResetTime;
	}

	public void setResetTime(double resetTime) {
		ResetTime = resetTime;
	}

	public double getDelay_Time() {
		return Delay_Time;
	}

	public void setDelay_Time(double delay_Time) {
		Delay_Time = delay_Time;
	}

	public double getBreaker_time() {
		return Breaker_time;
	}

	public void setBreaker_time(double breaker_time) {
		Breaker_time = breaker_time;
	}

	public double getTDPhase() {
		return TDPhase;
	}

	public void setTDPhase(double tDPhase) {
		TDPhase = tDPhase;
	}

	public double getTDGround() {
		return TDGround;
	}

	public void setTDGround(double tDGround) {
		TDGround = tDGround;
	}

	public String getRelayTarget() {
		return RelayTarget;
	}

	public void setRelayTarget(String relayTarget) {
		RelayTarget = relayTarget;
	}

	public TCC_CurveObj getOVcurve() {
		return OVcurve;
	}

	public void setOVcurve(TCC_CurveObj oVcurve) {
		OVcurve = oVcurve;
	}

	public TCC_CurveObj getUVCurve() {
		return UVCurve;
	}

	public void setUVCurve(TCC_CurveObj uVCurve) {
		UVCurve = uVCurve;
	}

	public double getVbase() {
		return Vbase;
	}

	public void setVbase(double vbase) {
		Vbase = vbase;
	}

	public double getkVBase() {
		return kVBase;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public double getPickupAmps46() {
		return PickupAmps46;
	}

	public void setPickupAmps46(double pickupAmps46) {
		PickupAmps46 = pickupAmps46;
	}

	public double getPctPickup46() {
		return PctPickup46;
	}

	public void setPctPickup46(double pctPickup46) {
		PctPickup46 = pctPickup46;
	}

	public double getBaseAmps46() {
		return BaseAmps46;
	}

	public void setBaseAmps46(double baseAmps46) {
		BaseAmps46 = baseAmps46;
	}

	public double getIsqt46() {
		return Isqt46;
	}

	public void setIsqt46(double isqt46) {
		Isqt46 = isqt46;
	}

	public double getPickupVolts47() {
		return PickupVolts47;
	}

	public void setPickupVolts47(double pickupVolts47) {
		PickupVolts47 = pickupVolts47;
	}

	public double getPctPickup47() {
		return PctPickup47;
	}

	public void setPctPickup47(double pctPickup47) {
		PctPickup47 = pctPickup47;
	}

	public double getOverTrip() {
		return OverTrip;
	}

	public void setOverTrip(double overTrip) {
		OverTrip = overTrip;
	}

	public double getUnderTrip() {
		return UnderTrip;
	}

	public void setUnderTrip(double underTrip) {
		UnderTrip = underTrip;
	}

	public String getMonitoredElementName() {
		return MonitoredElementName;
	}

	public void setMonitoredElementName(String monitoredElementName) {
		MonitoredElementName = monitoredElementName;
	}

	public int getMonitoredElementTerminal() {
		return MonitoredElementTerminal;
	}

	public void setMonitoredElementTerminal(int monitoredElementTerminal) {
		MonitoredElementTerminal = monitoredElementTerminal;
	}

	public CktElement getMonitoredElement() {
		return MonitoredElement;
	}

	public void setMonitoredElement(CktElement monitoredElement) {
		MonitoredElement = monitoredElement;
	}

	public ControlAction getPresentState() {
		return PresentState;
	}

	public void setPresentState(ControlAction presentState) {
		PresentState = presentState;
	}

	public int getOperationCount() {
		return OperationCount;
	}

	public void setOperationCount(int operationCount) {
		OperationCount = operationCount;
	}

	public boolean isLockedOut() {
		return LockedOut;
	}

	public void setLockedOut(boolean lockedOut) {
		LockedOut = lockedOut;
	}

	public boolean isArmedForClose() {
		return ArmedForClose;
	}

	public void setArmedForClose(boolean armedForClose) {
		ArmedForClose = armedForClose;
	}

	public boolean isArmedForOpen() {
		return ArmedForOpen;
	}

	public void setArmedForOpen(boolean armedForOpen) {
		ArmedForOpen = armedForOpen;
	}

	public boolean isPhaseTarget() {
		return PhaseTarget;
	}

	public void setPhaseTarget(boolean phaseTarget) {
		PhaseTarget = phaseTarget;
	}

	public boolean isGroundTarget() {
		return GroundTarget;
	}

	public void setGroundTarget(boolean groundTarget) {
		GroundTarget = groundTarget;
	}

	public double getNextTriptime() {
		return NextTriptime;
	}

	public void setNextTriptime(double nextTriptime) {
		NextTriptime = nextTriptime;
	}

	public int getLastEventHandle() {
		return LastEventHandle;
	}

	public void setLastEventHandle(int lastEventHandle) {
		LastEventHandle = lastEventHandle;
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

}
