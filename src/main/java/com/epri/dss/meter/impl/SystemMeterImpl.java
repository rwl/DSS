package com.epri.dss.meter.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.SystemMeter;

public class SystemMeterImpl implements SystemMeter {

	private static double[] RegisterArray = new double[EnergyMeter.NumEMRegisters];

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
		clear();
		this.This_Meter_DIFileIsOpen = false;
	}

	/**
	 * Only called if "SaveDemandInterval".
	 */
	protected void appendDemandIntervalFile() {
		String FileNm = "";
		FileWriter SystemDIStream;
		BufferedWriter SystemDIBuffer;

		DSSGlobals Globals = DSSGlobals.getInstance();

		if (This_Meter_DIFileIsOpen) return;

		try {
			FileNm = Globals.getEnergyMeterClass().getDI_Dir() + "/DI_SystemMeter.csv";
			SystemDIFile = new File(FileNm);
			/* File must exist */
			if (SystemDIFile.exists()) {
				SystemDIStream = new FileWriter(SystemDIFile, true);
			} else {
				SystemDIStream = new FileWriter(SystemDIFile, false);;
			}

			SystemDIBuffer = new BufferedWriter(SystemDIStream);

			This_Meter_DIFileIsOpen = true;

			// FIXME Close stream and buffer

		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening demand interval file \""+FileNm+" for appending."+DSSGlobals.CRLF+e.getMessage(), 540);
		}
	}

	private void clear() {
		kWh = 0.0;
		kvarh = 0.0;
		peakkW = 0.0;
		peakkVA = 0.0;
		Losseskwh = 0.0;
		Losseskvarh = 0.0;
		PeakLosseskW = 0.0;
		dkWh = 0.0;
		dkvarh = 0.0;
		dLosseskWh = 0.0;
		dlosseskvarh = 0.0;
		FirstSampleAfterReset = true;
	}

	protected void closeDemandIntervalFile() {
		if (This_Meter_DIFileIsOpen) {
			SystemDIFile.close();
			This_Meter_DIFileIsOpen = false;
		}
	}

	private void integrate(double Reg, double Value, double Deriv) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.isTrapezoidalIntegration()) {
			/* Trapezoidal rule integration */
			if (!FirstSampleAfterReset)
				Reg = Reg + 0.5 * ckt.getSolution().getIntervalHrs() * (Value + Deriv);
		} else {
			/* Plain Euler integration */
			Reg = Reg + ckt.getSolution().getIntervalHrs() * Value;
		}

		Deriv = Value;
	}

	protected void openDemandIntervalFile() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (This_Meter_DIFileIsOpen)
				SystemDIFile.close();

			SystemDIFile = new File(Globals.getEnergyMeterClass().getDI_Dir()+"/DI_SystemMeter.csv");
			FileWriter SystemDIStream = new FileWriter(SystemDIFile, false);
			BufferedWriter SystemDIBuffer = new BufferedWriter(SystemDIStream);  // FIXME Add stream and buffer members

			This_Meter_DIFileIsOpen = true;
			SystemDIBuffer.write("\"Hour\", ");
			writeRegisterNames(SystemDIFile);
			SystemDIBuffer.newLine();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening demand interval file \"DI_SystemMeter.csv\"  for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	public void reset() {
		clear();
	}

	public void save() {
		File F;
		String CSVName, Folder;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			CSVName = "SystemMeter.csv";
			/* If we are doing a simulation and saving interval data, create this in the
			 * same directory as the demand interval data.
			 */
			if (Globals.getEnergyMeterClass().isSaveDemandInterval()) {
				Folder = Globals.getEnergyMeterClass().getDI_Dir() + "/";
			} else {
				Folder = Globals.getDSSDataDirectory();
			}
			F = new File(Folder + CSVName);
			FileWriter FStream = new FileWriter(F, false);
			BufferedWriter FBuffer = new BufferedWriter(FStream);

			Globals.setGlobalResult(CSVName);

			FBuffer.write("Year, ");
			writeRegisterNames(F);
			FBuffer.newLine();

			FBuffer.write(Globals.getActiveCircuit().getSolution().getYear());
			writeRegisters(F);
			FBuffer.newLine();

			FBuffer.close();
			FStream.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening System Meter File \"" + DSSGlobals.CRLF + CSVName + "\": " + e.getMessage(), 542);
		}
	}

	/**
	 * Get total system energy out of the sources.
	 */
	public void takeSample() {

		cPower = Utilities.getTotalPowerFromSources().multiply(0.001);  // convert to kW

		integrate(kWh, cPower.getReal(), dkWh);
		integrate(kvarh, cPower.getImaginary(), dkvarh);

		peakkW  = Math.max(cPower.getReal(), peakkW);
		peakkVA = Math.max(cPower.abs(), peakkVA);

		/* Get total circuit losses */
		cLosses = DSSGlobals.getInstance().getActiveCircuit().getLosses();  // PD Elements except shunts
		cLosses = cLosses.multiply(0.001);  // convert to kW

		integrate(Losseskwh, cLosses.getReal(), dLosseskWh);
		integrate(Losseskvarh, cLosses.getImaginary(), dlosseskvarh);

		PeakLosseskW = Math.max(cLosses.getReal(), PeakLosseskW);

		FirstSampleAfterReset = false;
		if (This_Meter_DIFileIsOpen) writeDemandIntervalData();
	}

	protected void writeDemandIntervalData() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		SystemDIFile.write(String.format("%-.6g", sol.getDblHour()));
		SystemDIFile.write(String.format(", %-g", cPower.getReal()));
		SystemDIFile.write(String.format(", %-g", cPower.getImaginary()));
		SystemDIFile.write(String.format(", %-g", peakkW));
		SystemDIFile.write(String.format(", %-g", peakkVA));

		SystemDIFile.write(String.format(", %-g", cLosses.getReal()));
		SystemDIFile.write(String.format(", %-g", cLosses.getImaginary()));
		SystemDIFile.write(String.format(", %-g", PeakLosseskW));
		SystemDIFile.newLine();
	}

	private void writeRegisterNames(PrintStream F) {

		F.printf(", %-g", kWh);
		F.printf(", %-g", kvarh);
		F.printf(", %-g", peakkW);
		F.printf(", %-g", peakkVA);
		F.printf(", %-g", Losseskwh);
		F.printf(", %-g", Losseskvarh);
		F.printf(", %-g", PeakLosseskW);
	}

	private void writeRegisters(PrintStream F) {

	}

}
