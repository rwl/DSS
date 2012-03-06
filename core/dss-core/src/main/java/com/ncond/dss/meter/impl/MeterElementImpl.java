package com.ncond.dss.meter.impl;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.impl.CktElementImpl;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSS;
import com.ncond.dss.common.impl.Util;
import com.ncond.dss.meter.MeterElement;

abstract public class MeterElementImpl extends CktElementImpl implements MeterElement {

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

	@Override
	public void allocateSensorArrays() {
		if (meteredElement != null)
			calculatedCurrent = Util.resizeArray(calculatedCurrent, meteredElement.getYorder());
		if (meteredElement != null)
			calculatedVoltage = Util.resizeArray(calculatedVoltage, meteredElement.getYorder());
		sensorCurrent = Util.resizeArray(sensorCurrent, nPhases);
		sensorVoltage = Util.resizeArray(sensorVoltage, nPhases);
		phsAllocationFactor = Util.resizeArray(phsAllocationFactor, nPhases);
	}

	@Override
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
	@Override
	public void takeSample() {
		DSS.doSimpleMsg("Programming error: Reached base MeterElement class for takeSample."+DSS.CRLF+"Device: "+getName(), 723);
	}

	@Override
	public String getElementName() {
		return elementName;
	}

	@Override
	public void setElementName(String name) {
		elementName = name;
	}

	@Override
	public CktElement getMeteredElement() {
		return meteredElement;
	}

	@Override
	public void setMeteredElement(CktElement element) {
		meteredElement = element;
	}

	@Override
	public int getMeteredTerminal() {
		return meteredTerminal;
	}

	@Override
	public void setMeteredTerminal(int terminal) {
		meteredTerminal = terminal;
	}

	@Override
	public boolean isMeteredElementChanged() {
		return meteredElementChanged;
	}

	@Override
	public void setMeteredElementChanged(boolean changed) {
		meteredElementChanged = changed;
	}

	@Override
	public double[] getSensorCurrent() {
		return sensorCurrent;
	}

	@Override
	public void setSensorCurrent(double[] current) {
		sensorCurrent = current;
	}

	@Override
	public double[] getSensorVoltage() {
		return sensorVoltage;
	}

	@Override
	public void setSensorVoltage(double[] voltage) {
		sensorVoltage = voltage;
	}

	@Override
	public double[] getPhsAllocationFactor() {
		return phsAllocationFactor;
	}

	@Override
	public void setPhsAllocationFactor(double[] factor) {
		phsAllocationFactor = factor;
	}

	@Override
	public Complex[] getCalculatedCurrent() {
		return calculatedCurrent;
	}

	@Override
	public void setCalculatedCurrent(Complex[] current) {
		calculatedCurrent = current;
	}

	@Override
	public Complex[] getCalculatedVoltage() {
		return calculatedVoltage;
	}

	@Override
	public void setCalculatedVoltage(Complex[] voltage) {
		calculatedVoltage = voltage;
	}

	@Override
	public double getAvgAllocFactor() {
		return avgAllocFactor;
	}

	@Override
	public void setAvgAllocFactor(double factor) {
		avgAllocFactor = factor;
	}

}
