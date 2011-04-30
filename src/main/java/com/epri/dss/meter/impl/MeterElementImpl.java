package com.epri.dss.meter.impl;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.DSSClass;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.MeterElement;

public class MeterElementImpl extends DSSCktElement implements MeterElement {

	protected String ElementName;
	protected CktElement MeteredElement;
	protected int MeteredTerminal;
	protected boolean MeteredElementChanged;

	protected double[] SensorCurrent;
	protected double[] SensorVoltage;
	protected double[] PhsAllocationFactor;
	protected Complex[] CalculatedCurrent;
	protected Complex[] CalculatedVoltage;
	protected double AvgAllocFactor;

	public MeterElementImpl(DSSClass ParClass) {
		super(ParClass);
		this.DSSObjType = DSSClassDefs.METER_ELEMENT;

		this.ElementName         = "";
		this.MeteredElement      = null;
		this.MeteredTerminal     = 1;
		this.SensorCurrent       = null;
		this.SensorVoltage       = null;
		this.PhsAllocationFactor = null;
		this.CalculatedCurrent   = null;
		this.CalculatedVoltage   = null;
	}

	public void allocateSensorArrays() {
		if (MeteredElement != null)
			CalculatedCurrent = (Complex[]) Utilities.resizeArray(CalculatedCurrent, MeteredElement.getYorder());
		if (MeteredElement != null)
			CalculatedVoltage = (Complex[]) Utilities.resizeArray(CalculatedVoltage, MeteredElement.getYorder());
		SensorCurrent = (double[]) Utilities.resizeArray(SensorCurrent, nPhases);
		SensorVoltage = (double[]) Utilities.resizeArray(SensorVoltage, nPhases);
		PhsAllocationFactor = (double[]) Utilities.resizeArray(PhsAllocationFactor, nPhases);
	}

	public void calcAllocationFactors() {
		int iOffset;
		int i;
		double Mag;

		MeteredElement.getCurrents(CalculatedCurrent);

		// The Phase Allocation Factor is the amount that the load must change to match the measured peak
		iOffset = (MeteredTerminal - 1) * MeteredElement.getNConds();
		AvgAllocFactor = 0.0;
		for (i = 0; i < nPhases; i++) {
			Mag = CalculatedCurrent[i + iOffset].abs();
			if (Mag > 0.0) {
				PhsAllocationFactor[i] = SensorCurrent[i] / Mag;
			} else {
				PhsAllocationFactor[i] = 1.0;  // No change
			}
			AvgAllocFactor = AvgAllocFactor + PhsAllocationFactor[i];
		}
		AvgAllocFactor = AvgAllocFactor / nPhases;  // Factor for 2- and 3-phase loads
	}

	/**
	 * Sample control quantities and set action times in control queue.
	 */
	public void takeSample() {
		DSSGlobals.getInstance().doSimpleMsg("Programming Error: Reached base MeterElement class for takeSample."+DSSGlobals.CRLF+"Device: "+getName(), 723);
	}

	public String getElementName() {
		return ElementName;
	}

	public void setElementName(String elementName) {
		ElementName = elementName;
	}

	public CktElement getMeteredElement() {
		return MeteredElement;
	}

	public void setMeteredElement(CktElement meteredElement) {
		MeteredElement = meteredElement;
	}

	public int getMeteredTerminal() {
		return MeteredTerminal;
	}

	public void setMeteredTerminal(int meteredTerminal) {
		MeteredTerminal = meteredTerminal;
	}

	public boolean isMeteredElementChanged() {
		return MeteredElementChanged;
	}

	public void setMeteredElementChanged(boolean meteredElementChanged) {
		MeteredElementChanged = meteredElementChanged;
	}

	public double[] getSensorCurrent() {
		return SensorCurrent;
	}

	public void setSensorCurrent(double[] sensorCurrent) {
		SensorCurrent = sensorCurrent;
	}

	public double[] getSensorVoltage() {
		return SensorVoltage;
	}

	public void setSensorVoltage(double[] sensorVoltage) {
		SensorVoltage = sensorVoltage;
	}

	public double[] getPhsAllocationFactor() {
		return PhsAllocationFactor;
	}

	public void setPhsAllocationFactor(double[] phsAllocationFactor) {
		PhsAllocationFactor = phsAllocationFactor;
	}

	public Complex[] getCalculatedCurrent() {
		return CalculatedCurrent;
	}

	public void setCalculatedCurrent(Complex[] calculatedCurrent) {
		CalculatedCurrent = calculatedCurrent;
	}

	public Complex[] getCalculatedVoltage() {
		return CalculatedVoltage;
	}

	public void setCalculatedVoltage(Complex[] calculatedVoltage) {
		CalculatedVoltage = calculatedVoltage;
	}

	public double getAvgAllocFactor() {
		return AvgAllocFactor;
	}

	public void setAvgAllocFactor(double avgAllocFactor) {
		AvgAllocFactor = avgAllocFactor;
	}

}
