package com.epri.dss.general.impl;

import java.io.PrintStream;

import com.epri.dss.common.DSSClass;
import com.epri.dss.general.XfmrCodeObj;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;

public class XfmrCodeObjImpl extends DSSObjectImpl implements XfmrCodeObj {
	
	private int NPhases;
	private int ActiveWinding;
	private int NumWindings;
	private int MaxWindings;
	private double XHL, XHT, XLT;  // per unit
	private double[] XSC;     // per unit SC measurements
	private double VABase;    // FOR impedances
	private double NormMaxHKVA;
	private double EmergMaxHKVA;
	private double ThermalTimeConst;  // hr
	private double n_thermal;
	private double m_thermal;  // Exponents
	private double Lrise;
	private double HSrise;
	private double pctLoadLoss;
	private double pctNoLoadLoss;
	private double ppm_FloatFactor; //  parts per million winding float factor
	private double pctImag;
	private Winding[] Winding;

	public XfmrCodeObjImpl(DSSClass ParClass, String Name) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public void setNumWindings(int N) {
		
	}
	
	public void pullFromTransformer(TransformerObj obj) {
		
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
	
	// FIXME Private members in OpenDSS

	public int getNPhases() {
		return NPhases;
	}

	public void setNPhases(int nPhases) {
		NPhases = nPhases;
	}

	public int getActiveWinding() {
		return ActiveWinding;
	}

	public void setActiveWinding(int activeWinding) {
		ActiveWinding = activeWinding;
	}

	public int getMaxWindings() {
		return MaxWindings;
	}

	public void setMaxWindings(int maxWindings) {
		MaxWindings = maxWindings;
	}

	public double getXHL() {
		return XHL;
	}

	public void setXHL(double xHL) {
		XHL = xHL;
	}

	public double getXHT() {
		return XHT;
	}

	public void setXHT(double xHT) {
		XHT = xHT;
	}

	public double getXLT() {
		return XLT;
	}

	public void setXLT(double xLT) {
		XLT = xLT;
	}

	public double[] getXSC() {
		return XSC;
	}

	public void setXSC(double[] xSC) {
		XSC = xSC;
	}

	public double getVABase() {
		return VABase;
	}

	public void setVABase(double vABase) {
		VABase = vABase;
	}

	public double getNormMaxHKVA() {
		return NormMaxHKVA;
	}

	public void setNormMaxHKVA(double normMaxHKVA) {
		NormMaxHKVA = normMaxHKVA;
	}

	public double getEmergMaxHKVA() {
		return EmergMaxHKVA;
	}

	public void setEmergMaxHKVA(double emergMaxHKVA) {
		EmergMaxHKVA = emergMaxHKVA;
	}

	public double getThermalTimeConst() {
		return ThermalTimeConst;
	}

	public void setThermalTimeConst(double thermalTimeConst) {
		ThermalTimeConst = thermalTimeConst;
	}

	public double getN_thermal() {
		return n_thermal;
	}

	public void setN_thermal(double n_thermal) {
		this.n_thermal = n_thermal;
	}

	public double getM_thermal() {
		return m_thermal;
	}

	public void setM_thermal(double m_thermal) {
		this.m_thermal = m_thermal;
	}

	public double getLrise() {
		return Lrise;
	}

	public void setLrise(double lrise) {
		Lrise = lrise;
	}

	public double getHSrise() {
		return HSrise;
	}

	public void setHSrise(double hSrise) {
		HSrise = hSrise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public void setPctLoadLoss(double pctLoadLoss) {
		this.pctLoadLoss = pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	public void setPctNoLoadLoss(double pctNoLoadLoss) {
		this.pctNoLoadLoss = pctNoLoadLoss;
	}

	public double getPpm_FloatFactor() {
		return ppm_FloatFactor;
	}

	public void setPpm_FloatFactor(double ppm_FloatFactor) {
		this.ppm_FloatFactor = ppm_FloatFactor;
	}

	public double getPctImag() {
		return pctImag;
	}

	public void setPctImag(double pctImag) {
		this.pctImag = pctImag;
	}

	public Winding[] getWinding() {
		return Winding;
	}

	public void setWinding(Winding[] winding) {
		Winding = winding;
	}

	public int getNumWindings() {
		return NumWindings;
	}

}
