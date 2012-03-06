package com.ncond.dss.conversion;

import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.CktElement;
import com.ncond.dss.general.SpectrumObj;
import com.ncond.dss.meter.MeterElement;

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

	Complex[] getInjCurrent();

	void setInjCurrent(Complex[] injCurrent);

	void setITerminalUpdated(boolean Value);

	boolean getITerminalUpdated();

	double getVariable(int i);

	void setVariable(int i, double value);

	void zeroInjCurrent();

	@Override
	void initPropertyValues(int arrayOffset);

	/** Get present values of terminal */
	@Override
	void getCurrents(Complex[] curr);

	/** Get present values of terminal */
	@Override
	void getInjCurrents(Complex[] curr);

	@Override
	void computeITerminal();

	@Override
	int injCurrents();

	void calcYPrimContribution(Complex[] curr);

	void dumpProperties(PrintStream f, boolean complete);

	/* For harmonics mode */

	void initHarmonics();

	/* For dynamics mode and control devices */

	void initStateVars();

	void integrateStates();

	int numVariables();

	void getAllVariables(double[] states);

	String variableName(int i);

	int lookupVariable(String s);

}
