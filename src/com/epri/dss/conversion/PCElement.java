package com.epri.dss.conversion;

import java.io.PrintStream;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;

import com.epri.dss.common.CktElement;

public interface PCElement extends CktElement {

	String getSpectrum();

	void setSpectrum(String spectrum);

    /* Upline Sensor for this element */
	SpectrumObj getSpectrumObj();

	void setSpectrumObj(SpectrumObj spectrumObj);

    /* Upline EnergyMeter */
	MeterElement getMeterObj();

	void setMeterObj(MeterElement meterObj);

	MeterElement getSensorObj();

	void setSensorObj(MeterElement sensorObj);

	DComplexMatrix1D getInjCurrent();

	void setInjCurrent(DComplexMatrix1D injCurrent);

	void setITerminalUpdated(boolean Value);

	boolean getITerminalUpdated();

	double getVariable(int i);

	void setVariable(int i, double Value);

	void zeroInjCurrent();

	void initPropertyValues(int ArrayOffset);

	/* Get present values of terminal */
	void getCurrents(DComplexMatrix1D Curr);

	/* Get present values of terminal */
	void getInjCurrents(DComplexMatrix1D Curr);

	void computeIterminal();

	int injCurrents();

	void calcYPrimContribution(DComplexMatrix1D Curr);

	void dumpProperties(PrintStream F, boolean Complete);

	/** For Harmonics Mode */

	void initHarmonics();

	/** For Dynamics Mode and Control Devices */

	void initStateVars();

	void integrateStates();

	int numVariables();

	void getAllVariables(double[] States);

	String variableName(int i);

	int lookupVariable(String s);

}
