package com.epri.dss.meter;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.CktElement;

public interface MeterElement extends CktElement {

	public String getElementName();

	public void setElementName(String elementName);

	public CktElement getMeteredElement();

	public void setMeteredElement(CktElement meteredElement);

	public int getMeteredTerminal();

	public void setMeteredTerminal(int meteredTerminal);

	public boolean isMeteredElementChanged();

	public void setMeteredElementChanged(boolean meteredElementChanged);

	public double[] getSensorCurrent();

	public void setSensorCurrent(double[] sensorCurrent);

	public double[] getSensorVoltage();

	void setSensorVoltage(double[] sensorVoltage);

	double[] getPhsAllocationFactor();

	void setPhsAllocationFactor(double[] phsAllocationFactor);

	Complex[] getCalculatedCurrent();

	void setCalculatedCurrent(Complex[] calculatedCurrent);

	Complex[] getCalculatedVoltage();

	void setCalculatedVoltage(Complex[] calculatedVoltage);

	double getAvgAllocFactor();

	void setAvgAllocFactor(double avgAllocFactor);

	/** Sample control quantities and set action times in control queue */
	void takeSample();

	void allocateSensorArrays();

	void calcAllocationFactors();

}
