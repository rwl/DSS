package com.epri.dss.general;

import com.epri.dss.delivery.TransformerObj;
import com.epri.dss.delivery.Winding;

public interface XfmrCodeObj extends DSSObject {

	void setNumWindings(int n);

	void pullFromTransformer(TransformerObj obj);

	// FIXME Private members in OpenDSS

	int getNPhases();

	void setNPhases(int nPhases);

	int getActiveWinding();

	void setActiveWinding(int activeWinding);

	int getMaxWindings();

	void setMaxWindings(int maxWindings);

	double getXHL();

	void setXHL(double XHL);

	double getXHT();

	void setXHT(double XHT);

	double getXLT();

	void setXLT(double XLT);

	double[] getXSC();

	void setXSC(double[] XSC);

	double getVABase();

	void setVABase(double base);

	double getNormMaxHKVA();

	void setNormMaxHKVA(double normMaxHKVA);

	double getEmergMaxHKVA();

	void setEmergMaxHKVA(double emergMaxHKVA);

	double getThermalTimeConst();

	void setThermalTimeConst(double thermalTimeConst);

	double getNThermal();

	void setNThermal(double nthermal);

	double getMThermal();

	void setMThermal(double mthermal);

	double getLRise();

	void setLRise(double lrise);

	double getHSRise();

	void setHSRise(double hsrise);

	double getPctLoadLoss();

	void setPctLoadLoss(double pctLoadLoss);

	double getPctNoLoadLoss();

	void setPctNoLoadLoss(double pctNoLoadLoss);

	double getPpmFloatFactor();

	void setPpmFloatFactor(double ppmFloatFactor);

	double getPctImag();

	void setPctImag(double pctImag);

	Winding[] getWinding();

	void setWinding(Winding[] winding);

	int getNumWindings();

}
