package com.epri.dss.common.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.FeederObj;
import com.epri.dss.conversion.impl.PCElementImpl;
import com.epri.dss.shared.CktTree;

public class FeederObjImpl extends PCElementImpl implements FeederObj {
	
	private Object[] SequenceList;
	private Object[] ShuntList;

	private CktElement RootElement;
	private int FromTerminalOffset;
	
	protected boolean IsSynched;

	public FeederObjImpl(DSSClassImpl ParClass, String MeterName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSynched() {
		return IsSynched;
	}

	public void setIsSynched(boolean isSynched) {
		IsSynched = isSynched;
	}

	public void initializeFeeder(CktTree BranchList) {
		
	}
	
	public void setCktElementFeederFlags(boolean Value) {
		
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
