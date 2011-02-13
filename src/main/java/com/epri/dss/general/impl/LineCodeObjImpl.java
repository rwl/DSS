package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.LineCodeObj;
import com.epri.dss.shared.CMatrix;

public class LineCodeObjImpl extends DSSObjectImpl implements LineCodeObj {
	
	private int NeutralConductor;
	
	protected int NPhases;

	protected boolean SymComponentsModel, ReduceByKron;

	protected CMatrix Z,  // Base Frequency Series Z matrix
		Zinv,
		YC;               // Shunt capacitance matrix at Base frequency.

	protected double BaseFrequency;

	protected double R1, X1, R0, X0, C1, C0;
	protected double NormAmps, EmergAmps, FaultRate, PctPerm, HrsToRepair;
	protected double Rg, Xg, rho;

	protected int Units;  // See LineUnits

	public LineCodeObjImpl(DSSClass ParClass, String LineCodeName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public void setNPhases(int Value) {
		
	}
	
	public int getNPhases() {
		return NPhases;
	}
	
	private void doKronReduction() {
		
	}
	
	private String getRMatrix() {
		return null;
	}
	
	private String getXMatrix() {
		return null;
	}
	
	private String getCMatrix() {
		return null;
	}
	
	public void calcMatricesFromZ1Z0() {
		
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

	public boolean isSymComponentsModel() {
		return SymComponentsModel;
	}

	public void setSymComponentsModel(boolean symComponentsModel) {
		SymComponentsModel = symComponentsModel;
	}

	public boolean isReduceByKron() {
		return ReduceByKron;
	}

	public void setReduceByKron(boolean reduceByKron) {
		ReduceByKron = reduceByKron;
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

	public CMatrix getYC() {
		return YC;
	}

	public void setYC(CMatrix yC) {
		YC = yC;
	}

	public double getBaseFrequency() {
		return BaseFrequency;
	}

	public void setBaseFrequency(double baseFrequency) {
		BaseFrequency = baseFrequency;
	}

	public double getR1() {
		return R1;
	}

	public void setR1(double r1) {
		R1 = r1;
	}

	public double getX1() {
		return X1;
	}

	public void setX1(double x1) {
		X1 = x1;
	}

	public double getR0() {
		return R0;
	}

	public void setR0(double r0) {
		R0 = r0;
	}

	public double getX0() {
		return X0;
	}

	public void setX0(double x0) {
		X0 = x0;
	}

	public double getC1() {
		return C1;
	}

	public void setC1(double c1) {
		C1 = c1;
	}

	public double getC0() {
		return C0;
	}

	public void setC0(double c0) {
		C0 = c0;
	}

	public double getNormAmps() {
		return NormAmps;
	}

	public void setNormAmps(double normAmps) {
		NormAmps = normAmps;
	}

	public double getEmergAmps() {
		return EmergAmps;
	}

	public void setEmergAmps(double emergAmps) {
		EmergAmps = emergAmps;
	}

	public double getFaultRate() {
		return FaultRate;
	}

	public void setFaultRate(double faultRate) {
		FaultRate = faultRate;
	}

	public double getPctPerm() {
		return PctPerm;
	}

	public void setPctPerm(double pctPerm) {
		PctPerm = pctPerm;
	}

	public double getHrsToRepair() {
		return HrsToRepair;
	}

	public void setHrsToRepair(double hrsToRepair) {
		HrsToRepair = hrsToRepair;
	}

	public double getRg() {
		return Rg;
	}

	public void setRg(double rg) {
		Rg = rg;
	}

	public double getXg() {
		return Xg;
	}

	public void setXg(double xg) {
		Xg = xg;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public int getUnits() {
		return Units;
	}

	public void setUnits(int units) {
		Units = units;
	}
	
}
