/**
 * Copyright (C) 2008-2012 Electric Power Research Institute, Inc.
 * Copyright (C) 2009-2012 Richard Lincoln
 * All rights reserved.
 */
package com.ncond.dss.meter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;

abstract public class MeterElement extends CktElement {

	protected String elementName;
	protected CktElement meteredElement;
	protected int meteredTerminalIdx;
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

		elementName = "";
		meteredElement = null;
		meteredTerminalIdx = 0;
		sensorCurrent = null;
		sensorVoltage = null;
		phsAllocationFactor = null;
		calculatedCurrent = null;
		calculatedVoltage = null;
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	abstract public void takeSample();

	public void allocateSensorArrays() {
		if (meteredElement != null)
			calculatedCurrent = Util.resizeArray(calculatedCurrent, meteredElement.getYOrder());
		if (meteredElement != null)
			calculatedVoltage = Util.resizeArray(calculatedVoltage, meteredElement.getYOrder());
		sensorCurrent = Util.resizeArray(sensorCurrent, nPhases);
		sensorVoltage = Util.resizeArray(sensorVoltage, nPhases);
		phsAllocationFactor = Util.resizeArray(phsAllocationFactor, nPhases);
	}

	public void calcAllocationFactors() {
		int ioffset;
		int i;
		double mag;

		meteredElement.getCurrents(calculatedCurrent);

		// the phase allocation factor is the amount that the load must change to match the measured peak
		ioffset = meteredTerminalIdx * meteredElement.getNumConds();
		avgAllocFactor = 0.0;
		for (i = 0; i < nPhases; i++) {
			mag = calculatedCurrent[i + ioffset].abs();
			if (mag > 0.0) {
				phsAllocationFactor[i] = sensorCurrent[i] / mag;
			} else {
				phsAllocationFactor[i] = 1.0;  // no change
			}
			avgAllocFactor = avgAllocFactor + phsAllocationFactor[i];
		}
		avgAllocFactor = avgAllocFactor / nPhases;  // factor for 2- and 3-phase loads
	}

	public double getSensorCurrent(int idx) {
		return sensorCurrent[idx];
	}

	public double getSensorVoltage(int idx) {
		return sensorVoltage[idx];
	}

	public Complex getCalculatedCurrent(int idx) {
		return calculatedCurrent[idx];
	}

	public Complex getCalculatedVoltage(int idx) {
		return calculatedVoltage[idx];
	}

	public double[] getSensorCurrent() {
		return sensorCurrent;
	}

	public double[] getSensorVoltage() {
		return sensorVoltage;
	}

	public Complex[] getCalculatedCurrent() {
		return calculatedCurrent;
	}

	public Complex[] getCalculatedVoltage() {
		return calculatedVoltage;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public int getMeteredTerminalIdx() {
		return meteredTerminalIdx;
	}

	public void setMeteredTerminalIdx(int meteredTerminalIdx) {
		this.meteredTerminalIdx = meteredTerminalIdx;
	}

	public CktElement getMeteredElement() {
		return meteredElement;
	}

	public double[] getPhsAllocationFactor() {
		return phsAllocationFactor;
	}

	public void setMeteredElementChanged(boolean meteredElementChanged) {
		this.meteredElementChanged = meteredElementChanged;
	}

	public void setMeteredElement(CktElement meteredElement) {
		this.meteredElement = meteredElement;
	}

}
