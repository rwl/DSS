package com.epri.dss.control.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.control.GenDispatcherObj;

public class GenDispatcherObjImpl extends ControlElemImpl implements GenDispatcherObj {
	
	private double kWLimit,
		kWBand,
		HalfkWBand,
		FkvarLimit,
		TotalWeight;
	private int ListSize;
	private String[] GeneratorNameList;
	private Object[] GenPointerList;
	private double[] Weights;

	private CktElement MonitoredElement;

	public GenDispatcherObjImpl(DSSClassImpl ParClass, String GenDispatcherName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
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
	
	public boolean makeGenList() {
		return false;
	}

}
