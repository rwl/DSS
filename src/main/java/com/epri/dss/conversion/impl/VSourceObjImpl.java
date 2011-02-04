package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.VSourceObj;
import com.epri.dss.shared.CMatrix;

public class VSourceObjImpl extends PCElementImpl implements VSourceObj {
	
	private double MVAsc3;
	private double MVAsc1;
	private double Isc3;
	private double Isc1;
	private int ZSpecType;
	private double R1, X1;
	private double R0, X0;
	private double X1R1;
	private double X0R0;

	private int ScanType;
	
	protected CMatrix Z;  // Base Frequency Series Z matrix
	protected CMatrix Zinv;
	protected double VMag;

	protected double kVBase;
	protected double PerUnit;
	protected double Angle;
	protected double SrcFrequency;

	public VSourceObjImpl(DSSClassImpl ParClass, String SourceName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	private void getVTerminalForSource() {
		
	}
	
	public CMatrix getZ() {
		return Z;
	}

	public void setZ(CMatrix z) {
		Z = z;
	}

	public CMatrix getZinv() {
		return Zinv;
	}

	public void setZinv(CMatrix zinv) {
		Zinv = zinv;
	}

	public double getVMag() {
		return VMag;
	}

	public void setVMag(double vMag) {
		VMag = vMag;
	}

	public double getkVBase() {
		return kVBase;
	}

	public void setkVBase(double kVBase) {
		this.kVBase = kVBase;
	}

	public double getPerUnit() {
		return PerUnit;
	}

	public void setPerUnit(double perUnit) {
		PerUnit = perUnit;
	}

	public double getAngle() {
		return Angle;
	}

	public void setAngle(double angle) {
		Angle = angle;
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
