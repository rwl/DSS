package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.CapacitorObj;

public class CapacitorObjImpl extends PDElementImpl implements CapacitorObj {
	
	private double[] C,
		XL,
		kvarrating,
		R,
		Harm;  // single C per phase (line rating) if Cmatrix not specified
	private int[] States;

	private double totalkvar,
		kvrating;
	private int NumSteps,
		LastStepInService;
	private double[] Cmatrix;  // If not nil then overrides C

	private boolean doHarmonicRecalc;

	private int SpecType;
	
	/* 0 or 1 for wye (default) or delta, respectively */
	protected int Connection;

	public CapacitorObjImpl(DSSClass ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public int getStates(int Idx) {
		return 0;
	}
	
	public void setStates(int Idx, int Value) {
		
	}
	
	private void processHarmonicSpec(String Param) {
		
	}
	
	private void processStatesSpec(String Param) {
		
	}
	
	private void makeYprimWork(DComplexMatrix2D YprimWork, int iStep) {
		
	}
	
	/* 1=kvar, 2=Cuf, 3=Cmatrix */
	public void setNumSteps(int Value) {
		
	}
	
	public int getNumSteps() {
		return NumSteps;
	}

	public int getConnection() {
		return Connection;
	}

	public void setConnection(int connection) {
		Connection = connection;
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	@Override
	public void makePosSequence() {
		
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
	
	public boolean addStep() {
		return false;
	}
	
	public boolean subtractStep() {
		return false;
	}
	
	public int availableSteps() {
		return 0;
	}

	public double getTotalkvar() {
		return totalkvar;
	}

	public double getKvrating() {
		return kvrating;
	}

}
