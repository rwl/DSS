package com.ncond.dss.meter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;

abstract public class MeterElement extends CktElement {

	protected String elementName;
	protected CktElement meteredElement;
	protected int meteredTerminal;
	protected boolean meteredElementChanged;

	protected double[] sensorCurrent;
	protected double[] sensorVoltage;
	protected double[] phsAllocationFactor;
	protected Complex[] calculatedCurrent;
	protected Complex[] calculatedVoltage;
	protected double avgAllocFactor;

	public MeterElement(DSSClass parClass) {
		super(parClass);
		objType = DSSClassDefs.METER_ELEMENT;

		elementName         = "";
		meteredElement      = null;
		meteredTerminal     = 0;
		sensorCurrent       = null;
		sensorVoltage       = null;
		phsAllocationFactor = null;
		calculatedCurrent   = null;
		calculatedVoltage   = null;
	}

	public void allocateSensorArrays() {
		if (meteredElement != null)
			calculatedCurrent = Util.resizeArray(calculatedCurrent, meteredElement.getYorder());
		if (meteredElement != null)
			calculatedVoltage = Util.resizeArray(calculatedVoltage, meteredElement.getYorder());
		sensorCurrent = Util.resizeArray(sensorCurrent, nPhases);
		sensorVoltage = Util.resizeArray(sensorVoltage, nPhases);
		phsAllocationFactor = Util.resizeArray(phsAllocationFactor, nPhases);
	}

	public void calcAllocationFactors() {
		int iOffset;
		int i;
		double mag;

		meteredElement.getCurrents(calculatedCurrent);

		// the phase allocation factor is the amount that the load must change to match the measured peak
		iOffset = meteredTerminal * meteredElement.getNumConds();
		avgAllocFactor = 0.0;
		for (i = 0; i < nPhases; i++) {
			mag = calculatedCurrent[i + iOffset].abs();
			if (mag > 0.0) {
				phsAllocationFactor[i] = sensorCurrent[i] / mag;
			} else {
				phsAllocationFactor[i] = 1.0;  // no change
			}
			avgAllocFactor = avgAllocFactor + phsAllocationFactor[i];
		}
		avgAllocFactor = avgAllocFactor / nPhases;  // factor for 2- and 3-phase loads
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	public void takeSample() {
		DSS.doSimpleMsg("Programming error: Reached base MeterElement class for takeSample."+DSS.CRLF+"Device: "+getName(), 723);
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String name) {
		elementName = name;
	}

	public CktElement getMeteredElement() {
		return meteredElement;
	}

	public void setMeteredElement(CktElement element) {
		meteredElement = element;
	}

	public int getMeteredTerminal() {
		return meteredTerminal;
	}

	public void setMeteredTerminal(int terminal) {
		meteredTerminal = terminal;
	}

	public boolean isMeteredElementChanged() {
		return meteredElementChanged;
	}

	public void setMeteredElementChanged(boolean changed) {
		meteredElementChanged = changed;
	}

	public double[] getSensorCurrent() {
		return sensorCurrent;
	}

	public void setSensorCurrent(double[] current) {
		sensorCurrent = current;
	}

	public double[] getSensorVoltage() {
		return sensorVoltage;
	}

	public void setSensorVoltage(double[] voltage) {
		sensorVoltage = voltage;
	}

	public double[] getPhsAllocationFactor() {
		return phsAllocationFactor;
	}

	public void setPhsAllocationFactor(double[] factor) {
		phsAllocationFactor = factor;
	}

	public Complex[] getCalculatedCurrent() {
		return calculatedCurrent;
	}

	public void setCalculatedCurrent(Complex[] current) {
		calculatedCurrent = current;
	}

	public Complex[] getCalculatedVoltage() {
		return calculatedVoltage;
	}

	public void setCalculatedVoltage(Complex[] voltage) {
		calculatedVoltage = voltage;
	}

	public double getAvgAllocFactor() {
		return avgAllocFactor;
	}

	public void setAvgAllocFactor(double factor) {
		avgAllocFactor = factor;
	}

}
