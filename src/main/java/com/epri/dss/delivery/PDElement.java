package com.epri.dss.delivery;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.meter.MeterElement;

public interface PDElement extends CktElement {

	Complex getExcessKVANorm (int idxTerm);

	Complex getExcessKVAEmerg(int idxTerm);

	double getNormAmps();

	void setNormAmps(double normAmps);

	double getEmergAmps();

	void setEmergAmps(double emergAmps);

	double getFaultRate();

	void setFaultRate(double faultRate);

	double getPctPerm();

	void setPctPerm(double pctPerm);

	double getHrsToRepair();

	void setHrsToRepair(double hrsToRepair);

	int getFromTerminal();

	void setFromTerminal(int fromTerminal);

	int getToTerminal();

	void setToTerminal(int toTerminal);

	boolean isShunt();

	void setShunt(boolean isShunt);

	int getNumCustomers();

	void setNumCustomers(int numCustomers);

	int getTotalCustomers();

	void setTotalCustomers(int totalCustomers);

	PDElement getParentPDElement();

	void setParentPDElement(PDElement parentPDElement);

	MeterElement getMeterObj();

	void setMeterObj(MeterElement meterObj);

	MeterElement getSensorObj();

	void setSensorObj(MeterElement sensorObj);

	double getOverloadUE();

	void setOverload_UE(double overload_UE);

	double getOverloadEEN();

	void setOverloadEEN(double overoad_EEN);

}
