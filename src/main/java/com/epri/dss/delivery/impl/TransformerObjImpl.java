package com.epri.dss.delivery.impl;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.DSSClass;
import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;
import com.epri.dss.shared.CMatrix;

public class TransformerObjImpl extends PDElementImpl implements TransformerObj {
	
	private int DeltaDirection;
	private double ppm_FloatFactor; //  parts per million winding float factor
	private double pctImag;
	
	protected int NumWindings;
	protected int MaxWindings;
	protected int[] TermRef;  // keeps track of terminal connections

	protected double XHL, XHT, XLT;  // per unit
	protected double Zbase;
	protected double[] XSC;     // per unit SC measurements
	protected double VABase;    // FOR impedances

	protected CMatrix ZB;
	protected CMatrix Y_1Volt;
	protected CMatrix Y_Term;
	protected CMatrix Y_1Volt_NL;   // No Load Y's
	protected CMatrix Y_Term_NL;

	protected double Y_Terminal_Freqmult;

	protected double NormMaxHKVA;
	protected double EmergMaxHKVA;
	protected double ThermalTimeConst;  // hr
	protected double n_thermal;
	protected double m_thermal;  // Exponents
	protected double FLrise;
	protected double HSrise;
	protected double pctLoadLoss;
	protected double pctNoLoadLoss;
	
	protected boolean XHLChanged;
	
	private int ActiveWinding;
	private boolean IsSubstation;
	private String SubstationName;
	private Winding[] Winding;
	private String XfmrBank;
	private String XfmrCode;

	public TransformerObjImpl(DSSClass ParClass, String TransfName) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}
	
	public double getPresentTap(int i) {
		return 0.0;
	}
	
	public void setPresentTap(int i, double Value) {
		
	}
	
	public double getMinTap(int i) {
		return 0.0;
	}
	
	public double getMaxTap(int i) {
		return 0.0;
	}
	
	public double getTapIncrement(int i) {
		return 0.0;
	}
	
	public double getBaseVoltage(int i) {
		return 0.0;
	}
	
	public double getBasekVLL(int i) {
		return 0.0;
	}
	
	/* CIM accessors */
	
	public int getNumWindings() {
		return NumWindings;
	}

	public int getNumTaps(int i) {
		return 0;
	}
	
	public double getWdgResistance(int i) {
		return 0.0;
	}
	
	public int getWdgConnection(int i) {
		return 0;
	}
	
	public double getWdgKVA(int i) {
		return 0.0;
	}
	
	public double getXsc(int i) {
		return 0.0;
	}
	
	public double getWdgRneutral(int i) {
		return 0.0;
	}
	
	public double getWdgXneutral(int i) {
		return 0.0;
	}
	
	public double getWdgYPPM(int i) {
		return 0.0;
	}
	
	private double calcY_Terminal(double FreqMult) {
		return 0.0;
	}
	
	private void buildYPrimComponent(CMatrix YPrim_Component,
			CMatrix Y_Terminal) {
		
	}
	
	private void addNeutralToY(double FreqMultiplier) {
		
	}
	
	private void fetchXfmrCode(String Code) {
		
	}
	
	protected void setTermRef() {
		
	}

	public int getActiveWinding() {
		return ActiveWinding;
	}

	public void setActiveWinding(int activeWinding) {
		ActiveWinding = activeWinding;
	}

	public boolean isSubstation() {
		return IsSubstation;
	}

	public void setSubstation(boolean isSubstation) {
		IsSubstation = isSubstation;
	}

	public String getSubstationName() {
		return SubstationName;
	}

	public void setSubstationName(String substationName) {
		SubstationName = substationName;
	}

	public Winding[] getWinding() {
		return Winding;
	}

	public void setWinding(Winding[] winding) {
		Winding = winding;
	}

	public String getXfmrBank() {
		return XfmrBank;
	}

	public void setXfmrBank(String xfmrBank) {
		XfmrBank = xfmrBank;
	}

	public String getXfmrCode() {
		return XfmrCode;
	}

	public void setXfmrCode(String xfmrCode) {
		XfmrCode = xfmrCode;
	}
	
	public void setNumWindings(int N) {
		
	}
	
	public double getPpm_FloatFactor() {
		return ppm_FloatFactor;
	}

	public double getPctImag() {
		return pctImag;
	}

	public double getXHL() {
		return XHL;
	}

	public double getXHT() {
		return XHT;
	}

	public double getXLT() {
		return XLT;
	}

	public double getBaseVA() {
		return VABase;
	}

	public double getNormMaxHKVA() {
		return NormMaxHKVA;
	}

	public double getEmergMaxHKVA() {
		return EmergMaxHKVA;
	}

	public double getThTau() {
		return ThermalTimeConst;
	}

	public double getThN() {
		return n_thermal;
	}

	public double getThM() {
		return m_thermal;
	}

	public double getFLrise() {
		return FLrise;
	}

	public double getHSrise() {
		return HSrise;
	}

	public double getPctLoadLoss() {
		return pctLoadLoss;
	}

	public double getPctNoLoadLoss() {
		return pctNoLoadLoss;
	}

	@Override
	public void getLosses(Complex TotalLosses, Complex LoadLosses,
			Complex NoLoadLosses) {
		
	}
	
	public int rotatePhases(int iPhs) {
		return 0;
	}
	
	@Override
	public String getPropertyValue(int Index) {
		return null;
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
	public void saveWrite(PrintStream F) {
		
	}
	
	public void getWindingVoltages(int iWind, Complex[] VBuffer) {
		
	}

}
