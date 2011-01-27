package com.epri.dss.conversion.impl;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassImpl;
import com.epri.dss.conversion.PCElement;

public class PCElementImpl extends DSSCktElement implements PCElement {

	private boolean IterminalUpdated;

	private String Spectrum;
	private SpectrumObj SpectrumObj;
    /* Upline EnergyMeter */
	private MeterElement MeterObj;
    /* Upline Sensor for this element */
	private MeterElement SensorObj;

	private DComplexMatrix1D InjCurrent;

	public PCElementImpl(DSSClassImpl ParClass) {
		super(ParClass);
		// TODO Auto-generated constructor stub
	}

	public String getSpectrum() {
		return Spectrum;
	}

	public void setSpectrum(String spectrum) {
		Spectrum = spectrum;
	}

    /* Upline Sensor for this element */
	public SpectrumObj getSpectrumObj() {
		return SpectrumObj;
	}

	public void setSpectrumObj(SpectrumObj spectrumObj) {
		SpectrumObj = spectrumObj;
	}

    /* Upline EnergyMeter */
	public MeterElement getMeterObj() {
		return MeterObj;
	}

	public void setMeterObj(MeterElement meterObj) {
		MeterObj = meterObj;
	}

	public MeterElement getSensorObj() {
		return SensorObj;
	}

	public void setSensorObj(MeterElement sensorObj) {
		SensorObj = sensorObj;
	}

	public DComplexMatrix1D getInjCurrent() {
		return InjCurrent;
	}

	public void setInjCurrent(DComplexMatrix1D injCurrent) {
		InjCurrent = injCurrent;
	}

	public void setITerminalUpdated(boolean Value) {

	}

	public boolean getITerminalUpdated() {
		return false;
	}

	protected void getTerminalCurrents(DComplexMatrix1D Curr) {

	}

	public double getVariable(int i) {
		return 0.0;
	}

	public void setVariable(int i, double Value) {

	}

	public void zeroInjCurrent() {

	}

	public void initPropertyValues(int ArrayOffset) {

	}

	/* Get present values of terminal */
	public void getCurrents(DComplexMatrix1D Curr) {

	}

	/* Get present values of terminal */
	public void getInjCurrents(DComplexMatrix1D Curr) {

	}

	public void computeIterminal() {

	}

	public int injCurrents() {
		return 0;
	}

	public void calcYPrimContribution(DComplexMatrix1D Curr) {

	}

	public void dumpProperties(PrintStream F, boolean Complete) {

	}

	/** For Harmonics Mode */
	public void initHarmonics() {

	}

	/** For Dynamics Mode and Control Devices */
	public void initStateVars() {

	}

	public void integrateStates() {

	}

	public int numVariables() {
		return 0;
	}

	public void getAllVariables(double[] States) {

	}

	public String variableName(int i) {
		return null;
	}

	public int lookupVariable(String s) {
		return 0;
	}

}
