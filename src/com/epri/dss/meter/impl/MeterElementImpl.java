package com.epri.dss.meter.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.meter.MeterElement;

public class MeterElementImpl extends DSSCktElement implements
		MeterElement {

	protected String ElementName;
	protected DSSCktElement MeteredElement;
	protected int MeteredTerminal;
	protected boolean MeteredElementChanged;

	protected double[] SensorCurrent;
	protected double[] SensorVoltage;
	protected double[] PhsAllocationFactor;
	protected DComplexMatrix1D CalculatedCurrent;
	protected DComplexMatrix1D CalculatedVoltage;
	protected double AvgAllocFactor;

	public MeterElementImpl(DSSClassImpl ParClass) {
		// TODO Auto-generated constructor stub
		super(ParClass);
	}

	public String getElementName() {
		return ElementName;
	}

	public void setElementName(String elementName) {
		ElementName = elementName;
	}

	public DSSCktElement getMeteredElement() {
		return MeteredElement;
	}

	public void setMeteredElement(DSSCktElement meteredElement) {
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

	public DComplexMatrix1D getCalculatedCurrent() {
		return CalculatedCurrent;
	}

	public void setCalculatedCurrent(DComplexMatrix1D calculatedCurrent) {
		CalculatedCurrent = calculatedCurrent;
	}

	public DComplexMatrix1D getCalculatedVoltage() {
		return CalculatedVoltage;
	}

	public void setCalculatedVoltage(DComplexMatrix1D calculatedVoltage) {
		CalculatedVoltage = calculatedVoltage;
	}

	public double getAvgAllocFactor() {
		return AvgAllocFactor;
	}

	public void setAvgAllocFactor(double avgAllocFactor) {
		AvgAllocFactor = avgAllocFactor;
	}

	/* Sample control quantities and set action times in Control Queue */
	public void takeSample() {

	}

	public void allocateSensorArrays() {

	}

	public void calcAllocationFactors() {

	}

}
