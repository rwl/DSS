package com.epri.dss.delivery;

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

}
