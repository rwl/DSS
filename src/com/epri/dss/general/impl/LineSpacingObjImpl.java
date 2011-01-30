package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LineSpacingObj;

public class LineSpacingObjImpl extends DSSObjectImpl implements LineSpacingObj {
	
	private int NConds;
	private int NPhases;
	private double[] X;
	private double[] Y;
	private int Units;
	private boolean DataChanged;

	public LineSpacingObjImpl(DSSClass ParClass, String LineSpacingName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public void setNWires(int Value) {
		
	}
	
	public int getNWires() {
		return NConds;
	}
	
	public double getXcoord(int i) {
		return 0.0;
	}
	
	public double getYcoord(int i) {
		return 0.0;
	}
	
	public int getNPhases() {
		return NPhases;
	}

	public int getUnits() {
		return Units;
	}

	@Override
	public String getPropertyValue(int Index) {
		return null;
	}
	
	@Override
	public void initPropertyValues(int ArrayOffset) {
		
	}
	
	@Override
	public void dumpProperties(PrintStream F, boolean Complete) {
		
	}

}
