package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.impl.ControlElemImpl;
import com.epri.dss.delivery.FuseObj;

public class FuseObjImpl extends ControlElemImpl implements FuseObj {
	
	private static final int FUSEMAXDIM = 6;
	
	private TCC_CurveObj FuseCurve;

	private double RatedCurrent;

	private double DelayTime;

	private String MonitoredElementName;
	private int MonitoredElementTerminal;
	private DSSCktElement MonitoredElement;

	/* handle to control queue actions */
	private int[] hAction = new int[FUSEMAXDIM];
	/* 0 = open 1 = close */
	private ControlAction[] PresentState = new ControlAction[FUSEMAXDIM];  
	private boolean[] ReadyToBlow = new boolean[FUSEMAXDIM];

	/* Offset for monitored terminal */
	private int CondOffset; 
	private DComplexMatrix1D cBuffer;

	public FuseObjImpl(DSSClassImpl ParClass, String FuseName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private void interpretFuseAction(String Action) {
		
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	/* Always Zero for a Fuse */
	@Override
	public void calcYPrim() {
		
	}
	
	/* Sample control quantities and set action times in Control Queue */
	@Override
	public void sample() {
		
	}
	
	/* Do the action that is pending from last sample */
	@Override
	public void doPendingAction(int Phs, int ProxyHdl) {
		
	}
	
	/* Reset to initial defined state */
	@Override
	public void reset() {
		
	}
	
	/* Get present value of terminal Curr */
	@Override
	public void getCurrents(DComplexMatrix1D Curr) {
		
	}
	
	/* Returns Injextion currents */
	@Override
	public void getInjCurrents(DComplexMatrix1D Curr) {
		
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
	}

}
