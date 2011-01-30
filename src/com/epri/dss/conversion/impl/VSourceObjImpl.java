package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.VSourceObj;

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
	
	protected DComplexMatrix2D Z;  // Base Frequency Series Z matrix
	protected DComplexMatrix2D Zinv;
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
	
	public DComplexMatrix2D getZ() {
		return Z;
	}

	public void setZ(DComplexMatrix2D z) {
		Z = z;
	}

	public DComplexMatrix2D getZinv() {
		return Zinv;
	}

	public void setZinv(DComplexMatrix2D zinv) {
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
