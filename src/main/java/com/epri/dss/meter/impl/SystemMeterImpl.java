package com.epri.dss.meter.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.mutable.MutableDouble;

import com.epri.dss.shared.impl.Complex;

import com.epri.dss.common.Circuit;
import com.epri.dss.common.SolutionObj;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.SystemMeter;

public class SystemMeterImpl implements SystemMeter {

	private static double[] registerArray = new double[EnergyMeter.NUM_EM_REGISTERS];

	private MutableDouble kWh = new MutableDouble();
	private MutableDouble dkWh = new MutableDouble();
	private MutableDouble kvarh = new MutableDouble();
	private MutableDouble dkvarh = new MutableDouble();
	private MutableDouble Losseskwh = new MutableDouble();
	private MutableDouble dLosseskWh = new MutableDouble();
	private MutableDouble Losseskvarh = new MutableDouble();
	private MutableDouble dlosseskvarh = new MutableDouble();

	private double peakKW, peakKVA, peakLossesKW;
	private boolean firstSampleAfterReset, thisMeterDIFileIsOpen;
	private FileWriter systemDIFile;
	private Complex cPower, cLosses;

	public SystemMeterImpl() {
		clear();
		this.thisMeterDIFileIsOpen = false;
	}

	/**
	 * Only called if "SaveDemandInterval".
	 */
	// FIXME Protected method in OpenDSS
	public void appendDemandIntervalFile() {
		String fileName = "";

		DSSGlobals globals = DSSGlobals.getInstance();

		if (thisMeterDIFileIsOpen) return;

		try {
			fileName = globals.getEnergyMeterClass().getDI_Dir() + "/DI_SystemMeter.csv";
			/* File must exist */
			if (new File(fileName).exists()) {
				systemDIFile = new FileWriter(fileName, true);
			} else {
				systemDIFile = new FileWriter(fileName, false);;
			}

			thisMeterDIFileIsOpen = true;

		} catch (IOException e) {
			globals.doSimpleMsg("Error opening demand interval file \""+fileName+" for appending."+DSSGlobals.CRLF+e.getMessage(), 540);
		}
	}

	private void clear() {
		kWh.setValue(0.0);
		kvarh.setValue(0.0);
		peakKW = 0.0;
		peakKVA = 0.0;
		Losseskwh.setValue(0.0);
		Losseskvarh.setValue(0.0);
		peakLossesKW = 0.0;
		dkWh.setValue(0.0);
		dkvarh.setValue(0.0);
		dLosseskWh.setValue(0.0);
		dlosseskvarh.setValue(0.0);
		firstSampleAfterReset = true;
	}

	// FIXME Protected method in OpenDSS
	public void closeDemandIntervalFile() {
		if (thisMeterDIFileIsOpen) {
			try {
				systemDIFile.close();
				thisMeterDIFileIsOpen = false;
			} catch (IOException e) {
				DSSGlobals.getInstance().doSimpleMsg("Error closing demand interval file."+DSSGlobals.CRLF+e.getMessage(), 540);
			}
		}
	}

	private void integrate(MutableDouble reg, double value, MutableDouble deriv) {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.isTrapezoidalIntegration()) {
			/* Trapezoidal rule integration */
			if (!firstSampleAfterReset)
				reg.add(0.5 * ckt.getSolution().getIntervalHrs() * (value + deriv.doubleValue()));
		} else {
			/* Plain Euler integration */
			reg.add(ckt.getSolution().getIntervalHrs() * value);
		}

		deriv.setValue(value);
	}

	// FIXME Protected method in OpenDSS
	public void openDemandIntervalFile() {
		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			if (thisMeterDIFileIsOpen)
				systemDIFile.close();

			systemDIFile = new FileWriter(globals.getEnergyMeterClass().getDI_Dir()+"/DI_SystemMeter.csv");
			PrintWriter systemDIPrinter = new PrintWriter(systemDIFile);

			thisMeterDIFileIsOpen = true;
			systemDIPrinter.print("\"Hour\", ");
			writeRegisterNames(systemDIPrinter);
			systemDIPrinter.println();
		} catch (Exception e) {
			globals.doSimpleMsg("Error opening demand interval file \"DI_SystemMeter.csv\"  for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	public void reset() {
		clear();
	}

	public void save() {
		String csvName = "SystemMeter.csv", Folder;

		DSSGlobals globals = DSSGlobals.getInstance();

		try {
			/* If we are doing a simulation and saving interval data, create this in the
			 * same directory as the demand interval data.
			 */
			if (globals.getEnergyMeterClass().isSaveDemandInterval()) {
				Folder = globals.getEnergyMeterClass().getDI_Dir() + "/";
			} else {
				Folder = globals.getDSSDataDirectory();
			}
			FileWriter fw = new FileWriter(Folder + csvName, false);
			PrintWriter pw = new PrintWriter(fw);

			globals.setGlobalResult(csvName);

			pw.write("Year, ");
			writeRegisterNames(pw);
			pw.println();

			pw.print(globals.getActiveCircuit().getSolution().getYear());
			writeRegisters(pw);
			pw.println();

			pw.close();
			fw.close();
		} catch (Exception e) {
			globals.doSimpleMsg("Error opening system meter file \"" + DSSGlobals.CRLF + csvName + "\": " + e.getMessage(), 542);
		}
	}

	/**
	 * Get total system energy out of the sources.
	 */
	public void takeSample() {
		cPower = Utilities.getTotalPowerFromSources().multiply(0.001);  // convert to kW

		integrate(kWh, cPower.getReal(), dkWh);
		integrate(kvarh, cPower.getImaginary(), dkvarh);

		peakKW  = Math.max(cPower.getReal(), peakKW);
		peakKVA = Math.max(cPower.abs(), peakKVA);

		/* Get total circuit losses */
		cLosses = DSSGlobals.getInstance().getActiveCircuit().getLosses();  // PD elements except shunts
		cLosses = cLosses.multiply(0.001);  // convert to kW

		integrate(Losseskwh, cLosses.getReal(), dLosseskWh);
		integrate(Losseskvarh, cLosses.getImaginary(), dlosseskvarh);

		peakLossesKW = Math.max(cLosses.getReal(), peakLossesKW);

		firstSampleAfterReset = false;
		if (thisMeterDIFileIsOpen) writeDemandIntervalData();
	}

	protected void writeDemandIntervalData() {
		SolutionObj sol = DSSGlobals.getInstance().getActiveCircuit().getSolution();

		PrintWriter pw = new PrintWriter(systemDIFile);

		pw.printf("%-.6g", sol.getDblHour());
		pw.printf(", %-g", cPower.getReal());
		pw.printf(", %-g", cPower.getImaginary());
		pw.printf(", %-g", peakKW);
		pw.printf(", %-g", peakKVA);

		pw.printf(", %-g", cLosses.getReal());
		pw.printf(", %-g", cLosses.getImaginary());
		pw.printf(", %-g", peakLossesKW);
		pw.println();

		pw.close();
	}

	private void writeRegisterNames(PrintWriter f) {
		f.print("kWh, kvarh, \"Peak kW\", \"peak kVA\", \"Losses kWh\", \"Losses kvarh\", \"Peak Losses kW\"");
	}

	private void writeRegisters(PrintWriter f) {

		f.printf(", %-g", kWh.doubleValue());
		f.printf(", %-g", kvarh.doubleValue());
		f.printf(", %-g", peakKW);
		f.printf(", %-g", peakKVA);
		f.printf(", %-g", Losseskwh.doubleValue());
		f.printf(", %-g", Losseskvarh.doubleValue());
		f.printf(", %-g", peakLossesKW);
	}

}
