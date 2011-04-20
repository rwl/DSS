package com.epri.dss.control.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.RecloserObj;
import com.epri.dss.general.TCC_CurveObj;

public class RecloserObjImpl extends ControlElemImpl implements RecloserObj {

	private TCC_CurveObj PhaseDelayed,
		GroundDelayed,
		PhaseFast,
		GroundFast;

	private double PhaseTrip,
		GroundTrip,
		PhaseInst,
		GroundInst;

	private double[] RecloseIntervals;
	private int NumFast, NumReclose;
	private double ResetTime,
		DelayTime,
		TDGrDelayed,
		TDPhDelayed,
		TDGrFast,
		TDPhFast;

	private String MonitoredElementName;
	private int MonitoredElementTerminal;
	private CktElement MonitoredElement;

	private ControlAction PresentState;

	private int OperationCount;

	private boolean LockedOut,
		ArmedForClose, ArmedForOpen, GroundTarget, PhaseTarget;

	private int CondOffset; // Offset for monitored terminal

	private Complex[] cBuffer;

	public RecloserObjImpl(DSSClassImpl ParClass, String RecloserName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	private void interpretRecloserAction(String Action) {

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

	// FIXME Private members in Open DSS

	public TCC_CurveObj getPhaseDelayed() {
		return PhaseDelayed;
	}

	public void setPhaseDelayed(TCC_CurveObj phaseDelayed) {
		PhaseDelayed = phaseDelayed;
	}

	public TCC_CurveObj getGroundDelayed() {
		return GroundDelayed;
	}

	public void setGroundDelayed(TCC_CurveObj groundDelayed) {
		GroundDelayed = groundDelayed;
	}

	public TCC_CurveObj getPhaseFast() {
		return PhaseFast;
	}

	public void setPhaseFast(TCC_CurveObj phaseFast) {
		PhaseFast = phaseFast;
	}

	public TCC_CurveObj getGroundFast() {
		return GroundFast;
	}

	public void setGroundFast(TCC_CurveObj groundFast) {
		GroundFast = groundFast;
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

	public int getNumFast() {
		return NumFast;
	}

	public void setNumFast(int numFast) {
		NumFast = numFast;
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

	public double getDelayTime() {
		return DelayTime;
	}

	public void setDelayTime(double delayTime) {
		DelayTime = delayTime;
	}

	public double getTDGrDelayed() {
		return TDGrDelayed;
	}

	public void setTDGrDelayed(double tDGrDelayed) {
		TDGrDelayed = tDGrDelayed;
	}

	public double getTDPhDelayed() {
		return TDPhDelayed;
	}

	public void setTDPhDelayed(double tDPhDelayed) {
		TDPhDelayed = tDPhDelayed;
	}

	public double getTDGrFast() {
		return TDGrFast;
	}

	public void setTDGrFast(double tDGrFast) {
		TDGrFast = tDGrFast;
	}

	public double getTDPhFast() {
		return TDPhFast;
	}

	public void setTDPhFast(double tDPhFast) {
		TDPhFast = tDPhFast;
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

	public boolean isGroundTarget() {
		return GroundTarget;
	}

	public void setGroundTarget(boolean groundTarget) {
		GroundTarget = groundTarget;
	}

	public boolean isPhaseTarget() {
		return PhaseTarget;
	}

	public void setPhaseTarget(boolean phaseTarget) {
		PhaseTarget = phaseTarget;
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
