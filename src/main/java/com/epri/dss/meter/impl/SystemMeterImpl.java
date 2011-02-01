package com.epri.dss.meter.impl;

import java.io.File;
import java.io.PrintStream;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.meter.SystemMeter;

public class SystemMeterImpl implements SystemMeter {

	private double kWh, dkWh,
		kvarh, dkvarh,
		peakkW,
		peakkVA,
		Losseskwh,  dLosseskWh,
		Losseskvarh, dlosseskvarh,
		PeakLosseskW;
	private boolean FirstSampleAfterReset, This_Meter_DIFileIsOpen;
	private File SystemDIFile;
	private Complex cPower, cLosses;

	public SystemMeterImpl() {
		// TODO Auto-generated constructor stub
	}

	private void clear() {

	}

	private void integrate(double Reg, double Value, double Deriv) {

	}

	private void writeRegisters(PrintStream F) {

	}

	private void writeRegisterNames(PrintStream F) {

	}

	protected void openDemandIntervalFile() {

	}

	protected void writeDemandIntervalData() {

	}

	protected void closeDemandIntervalFile() {

	}

	protected void appendDemandIntervalFile() {

	}

	public void takeSample() {

	}

	public void reset() {

	}

	public void save() {

	}

}
