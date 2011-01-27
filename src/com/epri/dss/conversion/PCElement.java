package com.epri.dss.conversion;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.CktElement;

public interface PCElement extends CktElement {

	public String getSpectrum();

	public void setSpectrum(String spectrum);

    /* Upline Sensor for this element */
	public SpectrumObj getSpectrumObj();

	public void setSpectrumObj(SpectrumObj spectrumObj);

    /* Upline EnergyMeter */
	public MeterElement getMeterObj();

	public void setMeterObj(MeterElement meterObj);

	public MeterElement getSensorObj();

	public void setSensorObj(MeterElement sensorObj);

	public DComplexMatrix1D getInjCurrent();

	public void setInjCurrent(DComplexMatrix1D injCurrent);

	public void setITerminalUpdated(boolean Value);

	public boolean getITerminalUpdated();

	public double getVariable(int i);

	public void setVariable(int i, double Value);

	public void zeroInjCurrent();

	public void initPropertyValues(int ArrayOffset);

	/* Get present values of terminal */
	public void getCurrents(DComplexMatrix1D Curr);

	/* Get present values of terminal */
	public void getInjCurrents(DComplexMatrix1D Curr);

	public void computeIterminal();

	public int injCurrents();

	public void calcYPrimContribution(DComplexMatrix1D Curr);

	public void dumpProperties(PrintStream F, boolean Complete);

	/** For Harmonics Mode */

	public void initHarmonics();

	/** For Dynamics Mode and Control Devices */

	public void initStateVars();

	public void integrateStates();

	public int numVariables();

	public void getAllVariables(double[] States);

	public String variableName(int i);

	public int lookupVariable(String s);

}
