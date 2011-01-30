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

	public XfmrCodeObjImpl(DSSClass ParClass) {
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

}
