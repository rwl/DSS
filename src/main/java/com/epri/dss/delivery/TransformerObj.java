package com.epri.dss.delivery;

import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.Complex;

public interface TransformerObj extends PDElement {
	
	public double getPresentTap(int i);
	
	public void setPresentTap(int i, double Value);
	
	public double getMinTap(int i);
	
	public double getMaxTap(int i);
	
	public double getTapIncrement(int i);
	
	public double getBaseVoltage(int i);
	
	public double getBasekVLL(int i);
	
	/* CIM accessors */
	
	public int getNumWindings();

	public int getNumTaps(int i);
	
	public double getWdgResistance(int i);
	
	public int getWdgConnection(int i);
	
	public double getWdgKVA(int i);
	
	public double getXsc(int i);
	
	public double getWdgRneutral(int i);
	
	public double getWdgXneutral(int i);
	
	public double getWdgYPPM(int i);

	public int getActiveWinding();

	public void setActiveWinding(int activeWinding);

	public boolean isSubstation();

	public void setSubstation(boolean isSubstation);

	public String getSubstationName();

	public void setSubstationName(String substationName);

	public Winding[] getWinding();

	public void setWinding(Winding[] winding);

	public String getXfmrBank();

	public void setXfmrBank(String xfmrBank);

	public String getXfmrCode();

	public void setXfmrCode(String xfmrCode);
	
	public void setNumWindings(int N);
	
	public double getPpm_FloatFactor();

	public double getPctImag();

	public double getXHL();

	public double getXHT();

	public double getXLT();

	public double getBaseVA();

	public double getNormMaxHKVA();

	public double getEmergMaxHKVA();

	public double getThTau();

	public double getThN();

	public double getThM();

	public double getFLrise();

	public double getHSrise();

	public double getPctLoadLoss();

	public double getPctNoLoadLoss();
	
	public int rotatePhases(int iPhs);
	
	public void getWindingVoltages(int iWind, Complex[] VBuffer);
	

	// FIXME Private memebers in OpenDSS

	int getDeltaDirection();

	void setDeltaDirection(int deltaDirection);

	int getMaxWindings();

	void setMaxWindings(int maxWindings);

	int[] getTermRef();

	void setTermRef(int[] termRef);

	double getZbase();

	void setZbase(double zbase);

	double[] getXSC();

	void setXSC(double[] xSC);

	double getVABase();

	void setVABase(double vABase);

	CMatrix getZB();

	void setZB(CMatrix zB);

	CMatrix getY_1Volt();

	void setY_1Volt(CMatrix y_1Volt);

	CMatrix getY_Term();

	void setY_Term(CMatrix y_Term);

	CMatrix getY_1Volt_NL();

	void setY_1Volt_NL(CMatrix y_1Volt_NL);

	CMatrix getY_Term_NL();

	void setY_Term_NL(CMatrix y_Term_NL);

	double getY_Terminal_Freqmult();

	void setY_Terminal_Freqmult(double y_Terminal_Freqmult);

	double getThermalTimeConst();

	void setThermalTimeConst(double thermalTimeConst);

	double getN_thermal();

	void setN_thermal(double n_thermal);

	double getM_thermal();

	void setM_thermal(double m_thermal);

	boolean isXHLChanged();

	void setXHLChanged(boolean xHLChanged);

	boolean isIsSubstation();

	void setIsSubstation(boolean isSubstation);

	void setPpm_FloatFactor(double ppm_FloatFactor);

	void setPctImag(double pctImag);

	void setXHL(double xHL);

	void setXHT(double xHT);

	void setXLT(double xLT);

	void setNormMaxHKVA(double normMaxHKVA);

	void setEmergMaxHKVA(double emergMaxHKVA);

	void setFLrise(double fLrise);

	void setHSrise(double hSrise);

	void setPctLoadLoss(double pctLoadLoss);

	void setPctNoLoadLoss(double pctNoLoadLoss);

}
