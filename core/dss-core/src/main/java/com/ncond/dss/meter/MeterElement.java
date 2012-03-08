package com.ncond.dss.meter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.DSSClass;
import com.ncond.dss.common.DSSClassDefs;
import com.ncond.dss.common.Util;

@Data
@EqualsAndHashCode(callSuper=true)
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
			calculatedCurrent = Util.resizeArray(calculatedCurrent, meteredElement.getYOrder());
		if (meteredElement != null)
			calculatedVoltage = Util.resizeArray(calculatedVoltage, meteredElement.getYOrder());
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

}
