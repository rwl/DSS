package com.epri.dss.general;

import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;

public interface XfmrCodeObj extends DSSObject {
	
	void setNumWindings(int N);
	
	void pullFromTransformer(TransformerObj obj);
	
	// FIXME Private members in OpenDSS

	int getNPhases();

	void setNPhases(int nPhases);

	int getActiveWinding();

	void setActiveWinding(int activeWinding);

	int getMaxWindings();

	void setMaxWindings(int maxWindings);

	double getXHL();

	void setXHL(double xHL);

	double getXHT();

	void setXHT(double xHT);

	double getXLT();

	void setXLT(double xLT);

	double[] getXSC();

	void setXSC(double[] xSC);

	double getVABase();

	void setVABase(double vABase);

	double getNormMaxHKVA();

	void setNormMaxHKVA(double normMaxHKVA);

	double getEmergMaxHKVA();

	void setEmergMaxHKVA(double emergMaxHKVA);

	double getThermalTimeConst();

	void setThermalTimeConst(double thermalTimeConst);

	double getN_thermal();

	void setN_thermal(double n_thermal);

	double getM_thermal();

	void setM_thermal(double m_thermal);

	double getLrise();

	void setLrise(double lrise);

	double getHSrise();

	void setHSrise(double hSrise);

	double getPctLoadLoss();

	void setPctLoadLoss(double pctLoadLoss);

	double getPctNoLoadLoss();

	void setPctNoLoadLoss(double pctNoLoadLoss);

	double getPpm_FloatFactor();

	void setPpm_FloatFactor(double ppm_FloatFactor);

	double getPctImag();

	void setPctImag(double pctImag);

	Winding[] getWinding();

	void setWinding(Winding[] winding);

	int getNumWindings();

}
