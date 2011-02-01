package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.ReactorObj;

public class ReactorObjImpl extends PDElementImpl implements ReactorObj {
	
	private double R, Rp, Gp, X, kvarrating, kvrating;
	/* If not null then overrides C */
	private double[] Rmatrix, Gmatrix, XMatrix, Bmatrix;  

	private int Connection;  // 0 or 1 for wye (default) or delta, respectively
	private int SpecType;   // 1=kvar, 2=R+jX, 3=R and X matrices

	private boolean IsParallel;
	private boolean RpSpecified;

	public ReactorObjImpl(DSSClass ParClass, String ReactorName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses) {
		
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

}
