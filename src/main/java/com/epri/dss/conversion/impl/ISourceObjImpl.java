package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.ISourceObj;

public class ISourceObjImpl extends PCElementImpl implements ISourceObj {
	
	private double Amps;

	private double Angle;

	private double PhaseShift;
	private int ScanType;
	
	protected double SrcFrequency;

	public ISourceObjImpl(DSSClassImpl ParClass, String SourceName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private Complex getBaseCurr() {
		return null;
	}

	public double getSrcFrequency() {
		return SrcFrequency;
	}

	public void setSrcFrequency(double srcFrequency) {
		SrcFrequency = srcFrequency;
	}
	
	@Override
	public void recalcElementData() {
		
	}
	
	@Override
	public void calcYPrim() {
		
	}
	
	/* Make a positive Sequence Model */
	@Override
	public void makePosSequence() {
		
	}
	
	@Override
	public int injCurrents() {
		return 0;
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

}
