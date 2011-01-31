package com.epri.dss.control.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

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

	private DComplexMatrix1D cBuffer;  // Complexarray buffer

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
	public void getInjCurrents(DComplexMatrix1D Curr) {
		
	}
	
	@Override
	public void getCurrents(DComplexMatrix1D Curr) {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override 
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}

}
