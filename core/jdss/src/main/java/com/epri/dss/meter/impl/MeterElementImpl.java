package com.epri.dss.meter.impl;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.MeterElement;

public class MeterElementImpl extends DSSCktElement implements MeterElement {

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

	public MeterElementImpl(DSSClass parClass) {
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
			calculatedCurrent = Utilities.resizeArray(calculatedCurrent, meteredElement.getYorder());
		if (meteredElement != null)
			calculatedVoltage = Utilities.resizeArray(calculatedVoltage, meteredElement.getYorder());
		sensorCurrent = Utilities.resizeArray(sensorCurrent, nPhases);
		sensorVoltage = Utilities.resizeArray(sensorVoltage, nPhases);
		phsAllocationFactor = Utilities.resizeArray(phsAllocationFactor, nPhases);
	}

	public void calcAllocationFactors() {
		int iOffset;
		int i;
		double mag;

		meteredElement.getCurrents(calculatedCurrent);

		// the phase allocation factor is the amount that the load must change to match the measured peak
		iOffset = meteredTerminal * meteredElement.getNConds();
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
		DSSGlobals.doSimpleMsg("Programming error: Reached base MeterElement class for takeSample."+DSSGlobals.CRLF+"Device: "+getName(), 723);
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
