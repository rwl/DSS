package com.epri.dss.conversion;

import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.CktElement;
import com.epri.dss.meter.MeterElement;
import com.epri.dss.general.SpectrumObj;

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

	void setVariable(int i, double Value);

	void zeroInjCurrent();

	void initPropertyValues(int ArrayOffset);

	/* Get present values of terminal */
	void getCurrents(Complex[] Curr);

	/* Get present values of terminal */
	void getInjCurrents(Complex[] Curr);

	void computeIterminal();

	int injCurrents();

	void calcYPrimContribution(Complex[] Curr);

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
