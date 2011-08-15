package com.epri.dss.delivery;

import com.epri.dss.shared.CMatrix;
import org.apache.commons.math.complex.Complex;

public interface TransformerObj extends PDElement {

	public double getPresentTap(int i);

	public void setPresentTap(int i, double value);

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

	public double getWdgRNeutral(int i);

	public double getWdgXNeutral(int i);

	public double getWdgYPPM(int i);

	public int getActiveWinding();

	public void setActiveWinding(int activeWinding);

	public String getSubstationName();

	public void setSubstationName(String substationName);

	public Winding[] getWinding();

	public void setWinding(Winding[] winding);

	public String getXfmrBank();

	public void setXfmrBank(String xfmrBank);

	public String getXfmrCode();

	public void setXfmrCode(String xfmrCode);

	public void setNumWindings(int N);

	public double getPPM_FloatFactor();

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

	public double getFLRise();

	public double getHSRise();

	public double getPctLoadLoss();

	public double getPctNoLoadLoss();

	public int rotatePhases(int iPhs);

	public void getWindingVoltages(int iWind, Complex[] VBuffer);

	// FIXME Private method in OpenDSS
	void fetchXfmrCode(String code);

	// FIXME Protected method in OpenDSS
	void setTermRef();


	// FIXME Private memebers in OpenDSS

	int getDeltaDirection();

	void setDeltaDirection(int deltaDirection);

	int getMaxWindings();

	void setMaxWindings(int maxWindings);

	int[] getTermRef();

	void setTermRef(int[] termRef);

	double getZBase();

	void setZBase(double zbase);

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

	void setY1VoltNL(CMatrix y1VoltNL);

	CMatrix getYTermNL();

	void setYTermNL(CMatrix yTermNL);

	double getYTerminalFreqMult();

	void setYTerminalFreqMult(double yTerminalFreqMult);

	double getThermalTimeConst();

	void setThermalTimeConst(double thermalTimeConst);

	double getNThermal();

	void setNThermal(double n_thermal);

	double getMThermal();

	void setMThermal(double m_thermal);

	boolean isXHLChanged();

	void setXHLChanged(boolean xHLChanged);

	boolean isSubstation();

	void setSubstation(boolean isSubstation);

	void setPPM_FloatFactor(double ppm_FloatFactor);

	void setPctImag(double pctImag);

	void setXHL(double xHL);

	void setXHT(double xHT);

	void setXLT(double xLT);

	void setNormMaxHKVA(double normMaxHKVA);

	void setEmergMaxHKVA(double emergMaxHKVA);

	void setFLRise(double flrise);

	void setHSRise(double hsrise);

	void setPctLoadLoss(double pctLoadLoss);

	void setPctNoLoadLoss(double pctNoLoadLoss);

}
