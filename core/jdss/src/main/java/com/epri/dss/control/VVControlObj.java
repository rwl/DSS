package com.epri.dss.control;

import java.util.List;

import com.epri.dss.common.CktElement;
import com.epri.dss.general.XYCurveObj;
import com.epri.dss.shared.PointerList;

public interface VVControlObj extends ControlElem {

	double getVvc_Vmaxpu();

	void setVvc_Vmaxpu(double vvc_Vmaxpu);

	double getVvc_Vminpu();

	void setVvc_Vminpu(double vvc_Vminpu);

	double getKva_rating();

	void setKva_rating(double kva_rating);

	double getkW_rating();

	void setkW_rating(double kW_rating);

	double getKvar_fulloutput();

	void setKvar_fulloutput(double kvar_fulloutput);

	double getPf();

	void setPf(double pf);

	double getDelay();

	void setDelay(double delay);

	double getDelayoff();

	void setDelayoff(double delayoff);

	double getkW_ramp_rate();

	void setkW_ramp_rate(double kW_ramp_rate);

	double getKvar_ramp_rate();

	void setKvar_ramp_rate(double kvar_ramp_rate);

	double getkW_limit();

	void setkW_limit(double kW_limit);

	double getKvar_limit();

	void setKvar_limit(double kvar_limit);

	double getDeltaVTolerance();

	void setDeltaVTolerance(double deltaVTolerance);

	double getTotalWeight();

	void setTotalWeight(double totalWeight);

	double getQOldDeliver();

	void setQOldDeliver(double qOldDeliver);

	double getQdeliver();

	void setQdeliver(double qdeliver);

	double getQNew();

	void setQNew(double qNew);

	double getVavgPuPrior();

	void setVavgPuPrior(double vavgPuPrior);

	double getVavgPu();

	void setVavgPu(double vavgPu);

	double getPresentHour();

	void setPresentHour(double presentHour);

	int getControlActionHandle();

	void setControlActionHandle(int controlActionHandle);

	int getListSize();

	void setListSize(int listSize);

	List<String> getGeneratorNameList();

	void setGeneratorNameList(List<String> generatorNameList);

	PointerList getGenPointerList();

	void setGenPointerList(PointerList genPointerList);

	double[] getWeights();

	void setWeights(double[] weights);

	int getVvc_curve_size();

	void setVvc_curve_size(int vvc_curve_size);

	XYCurveObj getVvc_curve();

	void setVvc_curve(XYCurveObj vvc_curve);

	double getDeltaQ_factor();

	void setDeltaQ_factor(double deltaQ_factor);

	int getPendingChange();

	CktElement getMonitoredElement();

	void setMonitoredElement(CktElement monitoredElement);

	int getCondOffset();

	void setCondOffset(int condOffset);
}
