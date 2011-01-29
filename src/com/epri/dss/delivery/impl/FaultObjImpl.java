package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.FaultObj;

public class FaultObjImpl extends PDElementImpl implements FaultObj {
	
	private double MinAmps;
	private boolean IsTemporary, Cleared, Is_ON;
	private double On_Time;
	private double RandomMult;
	
	/* single G per phase (line rating) if Gmatrix not specified */
	protected double G;
	/* If not null then overrides G */
	protected double[] Gmatrix;
	
	/* per unit stddev */
	protected double Stddev;
	protected int SpecType;

	public FaultObjImpl(DSSClass ParClass, String FaultName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private boolean faultStillGoing() {
		return false;
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	public void randomize() {
		
	}
	
	public void checkStatus(int ControlMode) {
		
	}
	
	public void reset() {
		
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

}
