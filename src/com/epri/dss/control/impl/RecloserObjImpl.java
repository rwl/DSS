package com.epri.dss.control.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

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

	private DComplexMatrix1D cBuffer;

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
