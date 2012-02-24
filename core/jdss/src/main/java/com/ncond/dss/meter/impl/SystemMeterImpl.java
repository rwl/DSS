package com.ncond.dss.meter.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.SystemMeter;

public class SystemMeterImpl implements SystemMeter {

	private static double[] registerArray = new double[EnergyMeter.NUM_EM_REGISTERS];

	private double[] kWh = new double[1];
	private double[] dkWh = new double[1];
	private double[] kVArh = new double[1];
	private double[] dkVArh = new double[1];
	private double[] losses_kWh = new double[1];
	private double[] dLosses_kWh = new double[1];
	private double[] losses_kVArh = new double[1];
	private double[] dlosses_kVArh = new double[1];

	private double peakKW, peakKVA, peakLossesKW;
	private boolean firstSampleAfterReset, thisMeterDIFileIsOpen;
	private FileWriter systemDIFile;
	private Complex cPower, cLosses;

	public SystemMeterImpl() {
		clear();
		thisMeterDIFileIsOpen = false;
	}

	/**
	 * Only called if "SaveDemandInterval".
	 */
	// FIXME Protected method in OpenDSS
	public void appendDemandIntervalFile() {
		String fileName = "";

		if (thisMeterDIFileIsOpen)
			return;

		try {
			fileName = DSSGlobals.energyMeterClass.getDI_Dir() + "/DI_SystemMeter.csv";
			/* File must exist */
			if (new File(fileName).exists()) {
				systemDIFile = new FileWriter(fileName, true);
			} else {
				systemDIFile = new FileWriter(fileName, false);;
			}

			thisMeterDIFileIsOpen = true;

		} catch (IOException e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file \""+fileName+" for appending."+DSSGlobals.CRLF+e.getMessage(), 540);
		}
	}

	private void clear() {
		kWh[0] = 0.0 ;
		kVArh[0] = 0.0;
		peakKW = 0.0;
		peakKVA = 0.0;
		losses_kWh[0] = 0.0;
		losses_kVArh[0] = 0.0;
		peakLossesKW = 0.0;
		dkWh[0] = 0.0;
		dkVArh[0] = 0.0;
		dLosses_kWh[0] = 0.0;
		dlosses_kVArh[0] = 0.0;
		firstSampleAfterReset = true;
	}

	// FIXME Protected method in OpenDSS
	public void closeDemandIntervalFile() {
		if (thisMeterDIFileIsOpen) {
			try {
				systemDIFile.close();
				thisMeterDIFileIsOpen = false;
			} catch (IOException e) {
				DSSGlobals.doSimpleMsg("Error closing demand interval file."+DSSGlobals.CRLF+e.getMessage(), 540);
			}
		}
	}

	private void integrate(double[] reg, double value, double[] deriv) {
		Circuit ckt = DSSGlobals.activeCircuit;

		if (ckt.isTrapezoidalIntegration()) {
			/* Trapezoidal rule integration */
			if (!firstSampleAfterReset)
				reg[0] += 0.5 * ckt.getSolution().getIntervalHrs() * (value + deriv[0]);
		} else {
			/* Plain Euler integration */
			reg[0] += ckt.getSolution().getIntervalHrs() * value;
		}

		deriv[0] = value;
	}

	// FIXME Protected method in OpenDSS
	public void openDemandIntervalFile() {
		PrintWriter systemDIPrinter;

		try {
			if (thisMeterDIFileIsOpen)
				systemDIFile.close();

			systemDIFile = new FileWriter(DSSGlobals.energyMeterClass.getDI_Dir()+"/DI_SystemMeter.csv");
			systemDIPrinter = new PrintWriter(systemDIFile);

			thisMeterDIFileIsOpen = true;
			systemDIPrinter.print("\"Hour\", ");
			writeRegisterNames(systemDIPrinter);
			systemDIPrinter.println();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file \"DI_SystemMeter.csv\"  for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	public void reset() {
		clear();
	}

	public void save() {
		String folder, csvName = "SystemMeter.csv";
		FileWriter fw;
		PrintWriter pw;

		try {
			/* If we are doing a simulation and saving interval data, create this in the
			 * same directory as the demand interval data.
			 */
			if (DSSGlobals.energyMeterClass.isSaveDemandInterval()) {
				folder = DSSGlobals.energyMeterClass.getDI_Dir() + "/";
			} else {
				folder = DSSGlobals.DSSDataDirectory;
			}
			fw = new FileWriter(folder + csvName, false);
			pw = new PrintWriter(fw);

			DSSGlobals.globalResult = csvName;

			pw.write("Year, ");
			writeRegisterNames(pw);
			pw.println();

			pw.print(DSSGlobals.activeCircuit.getSolution().getYear());
			writeRegisters(pw);
			pw.println();

			pw.close();
			fw.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening system meter file \"" + DSSGlobals.CRLF + csvName + "\": " + e.getMessage(), 542);
		}
	}

	/**
	 * Get total system energy out of the sources.
	 */
	public void takeSample() {
		cPower = Utilities.getTotalPowerFromSources().multiply(0.001);  // convert to kW

		integrate(kWh, cPower.getReal(), dkWh);
		integrate(kVArh, cPower.getImaginary(), dkVArh);

		peakKW  = Math.max(cPower.getReal(), peakKW);
		peakKVA = Math.max(cPower.abs(), peakKVA);

		/* Get total circuit losses */
		cLosses = DSSGlobals.activeCircuit.getLosses();  // PD elements except shunts
		cLosses = cLosses.multiply(0.001);  // convert to kW

		integrate(losses_kWh, cLosses.getReal(), dLosses_kWh);
		integrate(losses_kVArh, cLosses.getImaginary(), dlosses_kVArh);

		peakLossesKW = Math.max(cLosses.getReal(), peakLossesKW);

		firstSampleAfterReset = false;
		if (thisMeterDIFileIsOpen)
			writeDemandIntervalData();
	}

	protected void writeDemandIntervalData() {
		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

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

		f.printf(", %-g", kWh[0]);
		f.printf(", %-g", kVArh[0]);
		f.printf(", %-g", peakKW);
		f.printf(", %-g", peakKVA);
		f.printf(", %-g", losses_kWh[0]);
		f.printf(", %-g", losses_kVArh[0]);
		f.printf(", %-g", peakLossesKW);
	}

}
