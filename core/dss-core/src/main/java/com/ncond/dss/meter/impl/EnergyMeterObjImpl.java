package com.ncond.dss.meter.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.math.complex.Complex;

import com.ncond.dss.common.Bus;
import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.FeederObj;
import com.ncond.dss.common.SolutionObj;
import com.ncond.dss.common.impl.DSSCktElement;
import com.ncond.dss.common.impl.DSSClassDefs;
import com.ncond.dss.common.impl.DSSClassImpl;
import com.ncond.dss.common.impl.DSSGlobals;
import com.ncond.dss.common.impl.Utilities;
import com.ncond.dss.conversion.GeneratorObj;
import com.ncond.dss.conversion.LoadObj;
import com.ncond.dss.conversion.PCElement;
import com.ncond.dss.delivery.LineObj;
import com.ncond.dss.delivery.PDElement;
import com.ncond.dss.meter.EnergyMeter;
import com.ncond.dss.meter.EnergyMeterObj;
import com.ncond.dss.parser.impl.Parser;
import com.ncond.dss.shared.CktTree;
import com.ncond.dss.shared.CktTreeNode;
import com.ncond.dss.shared.impl.CktTreeImpl;
import com.ncond.dss.shared.impl.LineUnits;

public class EnergyMeterObjImpl extends MeterElementImpl implements EnergyMeterObj {

	private static double deltaHrs;

	private boolean firstSampleAfterReset;
	private boolean excessFlag;
	private boolean zoneIsRadial;
	private boolean voltageUEOnly;
	private boolean localOnly;
	private boolean hasFeeder;

	private boolean losses;
	private boolean lineLosses;
	private boolean xfmrLosses;
	private boolean seqLosses;
	private boolean threePhaseLosses;
	private boolean VBaseLosses;
	private boolean phaseVoltageReport;

	private FeederObj feederObj;   // not used at present
	private String[] definedZoneList;
	private int definedZoneListSize;

	/* Limits on the entire load in the zone for networks where UE cannot be
	 * determined by the individual branches.
	 */
	private double maxZoneKVANorm;
	private double maxZoneKVAEmerg;

	/* Voltage bases in the meter zone */
	private double[] VBaseTotalLosses;  // allocated array
	private double[] VBaseLineLosses;
	private double[] VBaseLoadLosses;
	private double[] VBaseNoLoadLosses;
	private double[] VBaseLoad;
	private double[] VBaseList;
	private int VBaseCount;
	private int maxVBaseCount;

	/* Arrays for phase voltage report */
	private double[] VPhaseMax;
	private double[] VPhaseMin;
	private double[] VPhaseAccum;
	private int[] VPhaseAccumCount;
	private FileWriter VPhaseFile;
	private boolean VPhaseReportFileIsOpen;

	/* Demand interval file variables */
	private FileWriter DI_File;
	private boolean thisMeterDI_FileIsOpen;


	protected String[] registerNames = new String[NumEMRegisters];

	protected CktTree branchList;  // all circuit elements in meter"s zone

	protected double[] registers   = new double[NumEMRegisters];
	protected double[] derivatives = new double[NumEMRegisters];
	protected double[] totalsMask  = new double[NumEMRegisters];

	public EnergyMeterObjImpl(DSSClassImpl parClass, String energyMeterName) {
		super(parClass);

		int i;
		Circuit ckt = DSSGlobals.activeCircuit;

		setName(energyMeterName.toLowerCase());
		objType = parClass.getDSSClassType();  // ENERGY_METER;

		setNPhases(3);  // directly set conds and phases
		nConds = 3;
		setNTerms(1);  // this forces allocation of terminals and conductors in base class
		excessFlag     = true;  // default to excess energy for UE
		elementName    = "Vsource." + ckt.getCktElements().get(0).getName();  // default to first circuit element (source)
		meteredElement = null;
		branchList     = null;  // initialize to null, set later when inited

		thisMeterDI_FileIsOpen = false;
		VPhaseReportFileIsOpen  = false;

		initPropertyValues(0);

		// max zone kW limits ignored unless the user provides a rating
		maxZoneKVANorm     = 0.0;
		maxZoneKVAEmerg    = 0.0;

		zoneIsRadial        = true;
		hasFeeder           = false;
		feederObj           = null;  // initialise to not assigned
		definedZoneList     = null;
		definedZoneListSize = 0;
		losses             = true;  /* Loss reporting switches */
		lineLosses         = true;
		xfmrLosses         = true;
		seqLosses          = true;
		threePhaseLosses   = true;
		VBaseLosses        = true;
		phaseVoltageReport = false;
		VBaseList          = null;
		VBaseTotalLosses   = null;
		VBaseLineLosses    = null;
		VBaseLoadLosses    = null;
		VBaseNoLoadLosses  = null;
		VBaseLoad          = null;
		VBaseCount         = 0;
		maxVBaseCount      = (EnergyMeter.NUM_EM_REGISTERS - EnergyMeter.REG_VBASE_START) / 5;
		VBaseList          = new double[maxVBaseCount];
		VBaseTotalLosses   = new double[maxVBaseCount];
		VBaseLineLosses    = new double[maxVBaseCount];
		VBaseLoadLosses    = new double[maxVBaseCount];
		VBaseNoLoadLosses  = new double[maxVBaseCount];
		VBaseLoad          = new double[maxVBaseCount];

		// arrays for phase voltage report
		VPhaseMax   = new double[maxVBaseCount * 3];
		VPhaseMin   = new double[maxVBaseCount * 3];
		VPhaseAccum = new double[maxVBaseCount * 3];
		VPhaseAccumCount = new int[maxVBaseCount * 3];

		localOnly     = false;
		voltageUEOnly = false;

		// set register names  that correspond to the register quantities
		registerNames[0]  = "kWh";
		registerNames[1]  = "kvarh";
		registerNames[2]  = "Max kW";
		registerNames[3]  = "Max kVA";
		registerNames[4]  = "Zone kWh";
		registerNames[5]  = "Zone kvarh";
		registerNames[6]  = "Zone Max kW";
		registerNames[7]  = "Zone Max kVA";
		registerNames[8]  = "Overload kWh Normal";
		registerNames[9]  = "Overload kWh Emerg";
		registerNames[10] = "Load EEN";
		registerNames[11] = "Load UE";
		registerNames[12] = "Zone Losses kWh";
		registerNames[13] = "Zone Losses kvarh";
		registerNames[14] = "Zone Max kW Losses";
		registerNames[15] = "Zone Max kvar Losses";
		registerNames[16] = "Load Losses kWh";
		registerNames[17] = "Load Losses kvarh";
		registerNames[18] = "No Load Losses kWh";
		registerNames[19] = "No Load Losses kvarh";
		registerNames[20] = "Max kW Load Losses";
		registerNames[21] = "Max kW No Load Losses";
		registerNames[22] = "Line Losses";
		registerNames[23] = "Transformer Losses";

		registerNames[24] = "Line Mode Line Losses";
		registerNames[25] = "Zero Mode Line Losses";

		registerNames[26] = "3-phase Line Losses";
		registerNames[27] = "1- and 2-phase Line Losses";

		registerNames[28] = "Gen kWh";
		registerNames[29] = "Gen kvarh";
		registerNames[30] = "Gen Max kW";
		registerNames[31] = "Gen Max kVA";

		/* Registers for capturing losses by base voltage, names assigned later */
		for (i = EnergyMeter.REG_VBASE_START + 1; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			registerNames[i] = "";

		resetRegisters();
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			totalsMask[i] = 1.0;

		allocateSensorArrays();

		for (i = 0; i < nPhases; i++)
			sensorCurrent[i] = 400.0;

		//recalcElementData();
	}

	private static int jiIndex(int i, int j) {
		return j * 3 + i;
	}

	@Override
	public void recalcElementData() {

		int devIndex = Utilities.getCktElementIndex(elementName);

		if (devIndex >= 0) {  // monitored element must already exist
			meteredElement = (DSSCktElement) DSSGlobals.activeCircuit.getCktElements().get(devIndex);
			/* MeteredElement must be a PDElement */
			if (!(meteredElement instanceof PDElement)) {
				meteredElement = null;  // element not found
				DSSGlobals.doErrorMsg("EnergyMeter: \"" + getName() + "\"", "Circuit element \""+ elementName + "\" is not a Power Delivery (PD) element.",
					" Element must be a PD element.", 525);
				return;
			}

			if (meteredTerminal >= meteredElement.getNTerms()) {
				DSSGlobals.doErrorMsg("EnergyMeter: \"" + getName() + "\"",
					"Terminal no. \"" + String.valueOf(meteredTerminal)+"\" does not exist.",
					"Respecify terminal no.", 524);
			} else {

				if (meteredElementChanged) {
					// sets name of i-th terminal's connected bus in monitor's bus list
					// this value will be used to set the nodeRef array (see takeSample)
					setBus(0, meteredElement.getBus(meteredTerminal));
					setNPhases( meteredElement.getNPhases() );
					nConds  = meteredElement.getNConds();
					allocateSensorArrays();

					// if we come through here, throw branch list away
					branchList = null;
				}

				if (hasFeeder) makeFeederObj();  // OK to call multiple times
			}
		} else {
			meteredElement = null;  // element not found
			DSSGlobals.doErrorMsg("EnergyMeter: \"" + getName() + "\"", "Circuit element \""+ elementName + "\" not found.",
					" Element must be defined previously.", 525);
		}
	}

	/**
	 * Make a positive sequence model.
	 */
	@Override
	public void makePosSequence() {

		if (meteredElement != null) {
			setBus(0, meteredElement.getBus(meteredTerminal));
			setNPhases( meteredElement.getNPhases() );
			setNConds( meteredElement.getNConds() );
			allocateSensorArrays();
			branchList = null;
		}

		if (hasFeeder)
			makeFeederObj();

		super.makePosSequence();
	}

	private String makeVPhaseReportFileName() {
		return DSSGlobals.energyMeterClass.getDI_Dir() + "/" + getName() + "_PhaseVoltageReport.csv";
	}

	public void resetRegisters() {
		int i;

		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			registers[i] = 0.0;
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			derivatives[i] = 0.0;

		/* Initialise drag hand registers to some big negative number */
		registers[EnergyMeter.REG_MAX_KW]             = -1.0e50;
		registers[EnergyMeter.REG_MAX_KVA]            = -1.0e50;
		registers[EnergyMeter.REG_ZONE_MAX_KW]        = -1.0e50;
		registers[EnergyMeter.REG_ZONE_MAX_KVA]       = -1.0e50;
		registers[EnergyMeter.REG_MAX_LOAD_LOSSES]    = -1.0e50;
		registers[EnergyMeter.REG_MAX_NO_LOAD_LOSSES] = -1.0e50;
		registers[EnergyMeter.REG_LOSSES_MAX_KW]      = -1.0e50;
		registers[EnergyMeter.REG_LOSSES_MAX_KVAR]    = -1.0e50;

		registers[EnergyMeter.REG_GEN_MAX_KW]         = -1.0e50;
		registers[EnergyMeter.REG_GEN_MAX_KVA]        = -1.0e50;

		firstSampleAfterReset = true;  // initialise for trapezoidal integration
		// Removed .. open in solution loop See Solve Yearly if (EnergyMeterClass.SaveDemandInterval) openDemandIntervalFile();
	}

	@Override
	public void calcYPrim() {
		// YPrim is all zeros; just leave as nil so it is ignored
	}

	public void saveRegisters() {
		File f;
		FileWriter fw;
		BufferedWriter bw;

		String csvName = "MTR_" + getName() + ".csv";

		try {
			f = new File(DSSGlobals.DSSDataDirectory + csvName);
			fw = new FileWriter(f, false);
			bw = new BufferedWriter(fw);

			DSSGlobals.globalResult = csvName;

			bw.write("Year, " + DSSGlobals.activeCircuit.getSolution().getYear() + ",");
			bw.newLine();

			for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++) {
				bw.write("\"" + registerNames[i] + "\"," + registers[i]);
				bw.newLine();
			}

			bw.close();
			fw.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening Meter File \"" + DSSGlobals.CRLF + csvName + "\": " + e.getMessage(), 526);
			return;
		}
	}

	private void integrate(int reg, double deriv, double interval) {
		Circuit ckt = DSSGlobals.activeCircuit;

		if (ckt.isTrapezoidalIntegration()) {
		/* Trapezoidal rule integration */
		if (!firstSampleAfterReset)
			registers[reg] = registers[reg] + 0.5 * interval * (deriv + derivatives[reg]);
		} else {
			/* Plain Euler integration */
			registers[reg] = registers[reg] + interval * deriv;
		}

		/* Set the derivatives so that the proper value shows up in demand interval files
		 * and prepare for next time step in Trapezoidal integration. */
		derivatives[reg] = deriv;
	}

	/**
	 * Update registers from metered zone.
	 * Assumes one time period has taken place since last sample.
	 */
	@Override
	public void takeSample() {
		int i, j;

		/* pass by reference */
		double[] mS_TotalLosses = new double[2],
			mS_LoadLosses = new double[2],
			mS_NoLoadLosses = new double[2],
			mS_PosSeqLosses = new double[2],
			mS_ZeroSeqLosses = new double[2],
			mS_NegSeqLosses = new double[2];
		Complex S_Local,
			S_TotalLosses,
			S_LoadLosses,
			S_NoLoadLosses,
			totalLoadLosses,
			totalNoLoadLosses,
			totalLineLosses,
			totalTransformerLosses,
			totalLineModeLosses,  // lines only for now
			totalZeroModeLosses,
			total3PhaseLosses,
			total1PhaseLosses,
			totalLosses;

		PDElement cktElem, parenElem;
		PCElement PCElem;
		LoadObj load;
		GeneratorObj gen;

		double maxExcessKWNorm,
			maxExcessKWEmerg,
			EEN = 0,
			UE = 0,
			zoneKW,
			totalZoneKW,
			totalZoneKVAr,
			totalLoad_EEN,
			totalLoad_UE,
			totalGenKW,
			totalGenKVAr,
			loadKVA,
			genKVA,
			S_LocalKVA,
			loadKW;
		Complex S_PosSeqLosses;
		Complex S_ZeroSeqLosses;
		Complex S_NegSeqLosses;

		double puV;


		// compute energy in branch to which meter is connected

		//MeteredElement.setActiveTerminalIdx(MeteredTerminal);  // needed for excess kVA calcs
		S_Local    = meteredElement.getPower(meteredTerminal).multiply(0.001);
		S_LocalKVA = S_Local.abs();
		deltaHrs   = DSSGlobals.activeCircuit.getSolution().getIntervalHrs();
		integrate(EnergyMeter.REG_KWH,   S_Local.getReal(), deltaHrs);  // accumulate the power
		integrate(EnergyMeter.REG_KVARH, S_Local.getImaginary(), deltaHrs);
		setDragHandRegister(EnergyMeter.REG_MAX_KW,  S_Local.getReal());   // 3-10-04 removed abs()
		setDragHandRegister(EnergyMeter.REG_MAX_KVA, S_LocalKVA);

		// compute maximum overload energy in all branches in zone
		// and mark all load downline from an overloaded branch as unserved
		// if localonly, check only metered element

		totalLosses         = Complex.ZERO;  // initialize loss accumulators
		totalLoadLosses     = Complex.ZERO;
		totalNoLoadLosses   = Complex.ZERO;
		totalLineLosses     = Complex.ZERO;
		totalLineModeLosses = Complex.ZERO;
		totalZeroModeLosses = Complex.ZERO;
		total3PhaseLosses   = Complex.ZERO;
		total1PhaseLosses   = Complex.ZERO;
		totalTransformerLosses   = Complex.ZERO;

		// init all voltage base loss accumulators
		for (i = 0; i < maxVBaseCount; i++) {
			VBaseTotalLosses[i]  = 0.0;
			VBaseLineLosses[i]   = 0.0;
			VBaseLoadLosses[i]   = 0.0;
			VBaseNoLoadLosses[i] = 0.0;
			VBaseLoad[i]         = 0.0;
		}

		// phase voltage arrays
		if (phaseVoltageReport) {
			for (i = 0; i < maxVBaseCount; i++) {
				if (VBaseList[i] > 0.0) {
					for (j = 0; j < 3; j++) {
						VPhaseMax[jiIndex(j, i)] = 0.0;
						VPhaseMin[jiIndex(j, i)] = 9999.0;
						VPhaseAccum[jiIndex(j, i)] = 0.0;
						VPhaseAccumCount[jiIndex(j, i)] = 0;  // keep track of counts for average
					}
				}
			}
		}

		cktElem = (PDElement) branchList.getFirst();
		maxExcessKWNorm = 0.0;
		maxExcessKWEmerg = 0.0;

		/* ------------------------------------------------------------------ */
		/* ------------------------ Local Zone Only ------------------------- */
		/* ------------------------------------------------------------------ */
		if (localOnly) {
			cktElem = (PDElement) meteredElement;
			maxExcessKWNorm  = Math.abs(cktElem.getExcessKVANorm(meteredTerminal).getReal());
			maxExcessKWEmerg = Math.abs(cktElem.getExcessKVAEmerg(meteredTerminal).getReal());
		} else {
			/* -------------------------------------------------------------- */
			/* --------Cyle Through Entire Zone Setting EEN/UE -------------- */
			/* -------------------------------------------------------------- */
			while (cktElem != null) {
				// loop thru all ckt elements on zone

				cktElem.setActiveTerminalIdx( branchList.getPresentBranch().getFromTerminal() );
				// invoking this property sets the Overload_UE flag in the PD element
				EEN = Math.abs(cktElem.getExcessKVANorm(cktElem.getActiveTerminalIdx()).getReal());
				UE  = Math.abs(cktElem.getExcessKVAEmerg(cktElem.getActiveTerminalIdx()).getReal());
			}

			/* For radial circuits just keep the maximum overload; for mesh, add them up */
			if (zoneIsRadial) {
				if (UE  > maxExcessKWEmerg)
					maxExcessKWEmerg = UE;
				if (EEN > maxExcessKWNorm)
					maxExcessKWNorm  = EEN;
			} else {
				maxExcessKWEmerg = maxExcessKWEmerg + UE;
				maxExcessKWNorm  = maxExcessKWNorm  + EEN;
			}

			// even if this branch is not overloaded, if the parent element is overloaded
			// mark load on this branch as unserved also
			// use the larger of the two factors
			parenElem = (PDElement) branchList.getParent();
			if (parenElem != null) {
				cktElem.setOverloadEEN( Math.max(cktElem.getOverloadEEN(), parenElem.getOverloadEEN()) );
				cktElem.setOverload_UE( Math.max(cktElem.getOverloadUE(), parenElem.getOverloadUE()) );
			}

			// mark loads (not generators) by the degree of overload if the meter's zone is to be considered radial
			// this overrides and supercedes the load's own determination of unserved based on voltage
			// if voltage only is to be used for Load UE/EEN, don't mark (set to 0.0 and load will calc UE based on voltage)
			PCElem = (PCElement) branchList.getFirstObject();
			while (PCElem != null) {
				if ((PCElem.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT) {
					load = (LoadObj) PCElem;
					if (cktElem.getOverloadEEN() > 0.0 && zoneIsRadial && !voltageUEOnly) {
						load.setEEN_Factor(cktElem.getOverloadEEN());
					} else {
						load.setEEN_Factor(0.0);
					}

					if (cktElem.getOverloadUE() > 0.0 && zoneIsRadial && !voltageUEOnly) {
						load.setUE_Factor(cktElem.getOverloadUE());
					} else {
						load.setUE_Factor(0.0);
					}
				}
				PCElem = (PCElement) branchList.getNextObject();
			}

			cktElem = (PDElement) branchList.goForward();
		}

		// get the losses, and unserved bus energies
		totalZoneKW   = 0.0;
		totalZoneKVAr = 0.0;
		totalLoad_EEN = 0.0;
		totalLoad_UE  = 0.0;
		totalGenKW    = 0.0;
		totalGenKVAr  = 0.0;

		/* ------------------------------------------------------------------ */
		/* ----       Cycle Through Zone Accumulating Load and Losses    ---- */
		/* ------------------------------------------------------------------ */
		cktElem = (PDElement) branchList.getFirst();
		while (cktElem != null) {
			PCElem = (PCElement) branchList.getFirstObject();
			while (PCElem != null) {
				switch (PCElem.getDSSObjType() & DSSClassDefs.CLASSMASK) {
				case DSSClassDefs.LOAD_ELEMENT:
					if (!localOnly) {  // don't check for load EEN/UE if Local only
						load = (LoadObj) PCElem;
						loadKW = accumulateLoad(load, totalZoneKW, totalZoneKVAr, totalLoad_EEN, totalLoad_UE);
						if (VBaseLosses) {
							int vbi = branchList.getPresentBranch().getVoltBaseIndex();
							if (vbi > 0)
								VBaseLoad[vbi] = VBaseLoad[vbi] + loadKW;
						}
					}
					break;
				case DSSClassDefs.GEN_ELEMENT:
					gen = (GeneratorObj) PCElem;
					accumulateGen(gen, totalGenKW, totalGenKVAr);  // FIXME pass by reference
					break;
				}
				PCElem = (PCElement) branchList.getNextObject();
			}

			if (losses) {  // compute and report losses

				/* Get losses from the present circuit element */
				cktElem.getLosses(mS_TotalLosses, mS_LoadLosses, mS_NoLoadLosses);  // returns watts, vars
				S_TotalLosses = new Complex(mS_TotalLosses[0], mS_TotalLosses[1]);
				S_LoadLosses = new Complex(mS_LoadLosses[0], mS_LoadLosses[1]);
				S_NoLoadLosses = new Complex(mS_NoLoadLosses[0], mS_NoLoadLosses[1]);

				/* Convert to kW */
				S_TotalLosses = S_TotalLosses.multiply(0.001);
				S_LoadLosses = S_LoadLosses.multiply(0.001);
				S_NoLoadLosses = S_NoLoadLosses.multiply(0.001);
				/* Update accumulators */
				totalLosses = totalLosses.add(S_TotalLosses);  // accumulate total losses in meter zone
				totalLoadLosses = totalLoadLosses.add(S_LoadLosses);  // accumulate total load losses in meter zone
				totalNoLoadLosses = totalNoLoadLosses.add(S_NoLoadLosses);  // accumulate total no load losses in meter zone

				/* Line and transformer elements */
				if (Utilities.isLineElement(cktElem) && lineLosses) {
					totalLineLosses = totalLineLosses.add(S_TotalLosses);  // accumulate total losses in meter zone
					if (seqLosses) {
						cktElem.getSeqLosses(mS_PosSeqLosses, mS_NegSeqLosses, mS_ZeroSeqLosses);
						S_PosSeqLosses = new Complex(mS_PosSeqLosses[0], mS_PosSeqLosses[1]);
						S_NegSeqLosses = new Complex(mS_NegSeqLosses[0], mS_NegSeqLosses[1]);
						S_ZeroSeqLosses = new Complex(mS_ZeroSeqLosses[0], mS_ZeroSeqLosses[1]);

						S_PosSeqLosses = S_PosSeqLosses.add(S_NegSeqLosses);  // add line modes together
						S_PosSeqLosses = S_PosSeqLosses.multiply(0.001);  // convert to kW
						S_ZeroSeqLosses = S_ZeroSeqLosses.multiply(0.001);
						totalLineModeLosses = totalLineModeLosses.add(S_PosSeqLosses);
						totalZeroModeLosses = totalZeroModeLosses.add(S_ZeroSeqLosses);
					}
					/* Separate line losses into 3- and "1-phase" losses */
					if (threePhaseLosses) {
						if (cktElem.getNPhases() == 3) {
							total3PhaseLosses = total3PhaseLosses.add(S_TotalLosses);
						} else {
							total1PhaseLosses = total1PhaseLosses.add(S_TotalLosses);
						}
					}
				} else if (Utilities.isTransformerElement(cktElem) && xfmrLosses) {
					totalTransformerLosses = totalTransformerLosses.add(S_TotalLosses);  // accumulate total losses in meter zone
				}

				if (VBaseLosses) {
					int vbi = branchList.getPresentBranch().getVoltBaseIndex();
					if (vbi >= 0) {
						VBaseTotalLosses[vbi] = VBaseTotalLosses[vbi]  + S_TotalLosses.getReal();
						if (Utilities.isLineElement(cktElem)) {
							VBaseLineLosses[vbi] = VBaseLineLosses[vbi] + S_TotalLosses.getReal();
						} else if (Utilities.isTransformerElement(cktElem)) {
							VBaseLoadLosses[vbi]   = VBaseLoadLosses[vbi]   + S_LoadLosses.getReal();
							VBaseNoLoadLosses[vbi] = VBaseNoLoadLosses[vbi] + S_NoLoadLosses.getReal();
						}
					}
				}

				// compute min, max, and average pu voltages for 1st 3 phases (nodes designated 1, 2, or 3)
				if (phaseVoltageReport) {
					int vbi = branchList.getPresentBranch().getVoltBaseIndex();
					int fbr = branchList.getPresentBranch().getFromBusReference();
					if (vbi >= 0) {
						Circuit ckt = DSSGlobals.activeCircuit;
						if (ckt.getBus(fbr).getKVBase() > 0.0) {
							for (i = 0; i < ckt.getBus(fbr).getNumNodesThisBus(); i++) {
								j = ckt.getBus(fbr).getNum(i);
								if (j >= 0 && j < 3) {
									puV = ckt.getSolution().getNodeV( ckt.getBus(fbr).getRef(i) ).abs() / ckt.getBus(fbr).getKVBase();
									VPhaseMax[jiIndex(j, vbi)] = Math.max(VPhaseMax[jiIndex(j, vbi)], puV);
									VPhaseMin[jiIndex(j, vbi)] = Math.min(VPhaseMin[jiIndex(j, vbi)], puV);
									VPhaseAccum[jiIndex(j, vbi)] = VPhaseAccum[jiIndex(j, vbi)] + puV;
									VPhaseAccumCount[jiIndex(j, vbi)] += 1;  // keep track of counts for average
								}
							}
						}
					}
				}
			}  // if (losses)

			cktElem = (PDElement) branchList.goForward();
		}

		/* NOTE: integrate proc automatically sets derivatives array */
		integrate(EnergyMeter.REG_LOAD_EEN, totalLoad_EEN, deltaHrs);
		integrate(EnergyMeter.REG_LOAD_UE,  totalLoad_UE,  deltaHrs);

		/* Accumulate losses in appropriate registers */
		integrate(EnergyMeter.REG_ZONE_LOSSES_KWH,      totalLosses.getReal(),          deltaHrs);
		integrate(EnergyMeter.REG_ZONE_LOSSES_KVARH,    totalLosses.getImaginary(),     deltaHrs);
		integrate(EnergyMeter.REG_LOAD_LOSSES_KWH,      totalLoadLosses.getReal(),      deltaHrs);
		integrate(EnergyMeter.REG_LOAD_LOSSES_KVARH,    totalLoadLosses.getImaginary(), deltaHrs);
		integrate(EnergyMeter.REG_NO_LOAD_LOSSES_KWH,   totalNoLoadLosses.getReal(),    deltaHrs);
		integrate(EnergyMeter.REG_NO_LOAD_LOSSES_KVARH, totalNoLoadLosses.getImaginary(), deltaHrs);
		integrate(EnergyMeter.REG_LINE_LOSSES_KWH,      totalLineLosses.getReal(),      deltaHrs);
		integrate(EnergyMeter.REG_LINE_MODE_LINE_LOSS,  totalLineModeLosses.getReal(),  deltaHrs);
		integrate(EnergyMeter.REG_ZERO_MODE_LINE_LOSS,  totalZeroModeLosses.getReal(),  deltaHrs);
		integrate(EnergyMeter.REG_3_PHASE_LINE_LOSS,    total3PhaseLosses.getReal(),    deltaHrs);
		integrate(EnergyMeter.REG_1_PHASE_LINE_LOSS,    total1PhaseLosses.getReal(),    deltaHrs);
		integrate(EnergyMeter.REG_TRANSFORMER_LOSSES_KWH,  totalTransformerLosses.getReal(),  deltaHrs);

		for (i = 0; i < maxVBaseCount; i++) {
			integrate(EnergyMeter.REG_VBASE_START + i, VBaseTotalLosses[i],  deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 1 * maxVBaseCount + i, VBaseLineLosses[i],   deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 2 * maxVBaseCount + i, VBaseLoadLosses[i],   deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 3 * maxVBaseCount + i, VBaseNoLoadLosses[i], deltaHrs);
			integrate(EnergyMeter.REG_VBASE_START + 4 * maxVBaseCount + i, VBaseLoad[i],         deltaHrs);
		}


		/* ------------------------------------------------------------------ */
		/* ---------------   Total Zone Load and Generation ----------------- */
		/* ------------------------------------------------------------------ */

		integrate(EnergyMeter.REG_ZONE_KWH,   totalZoneKW,   deltaHrs);
		integrate(EnergyMeter.REG_ZONE_KVARH, totalZoneKVAr, deltaHrs);
		integrate(EnergyMeter.REG_GEN_KWH,    totalGenKW,    deltaHrs);
		integrate(EnergyMeter.REG_GEN_KVARH,  totalGenKVAr,  deltaHrs);
		genKVA  = Math.sqrt(Math.pow(totalGenKVAr, 2)  + Math.pow(totalGenKW, 2));
		loadKVA = Math.sqrt(Math.pow(totalZoneKVAr, 2) + Math.pow(totalZoneKW, 2));

		/* ------------------------------------------------------------------ */
		/* ---------------   Set Drag Hand Registers  ----------------------- */
		/* ------------------------------------------------------------------ */

		setDragHandRegister(EnergyMeter.REG_LOSSES_MAX_KW,      Math.abs(totalLosses.getReal()));
		setDragHandRegister(EnergyMeter.REG_LOSSES_MAX_KVAR,    Math.abs(totalLosses.getImaginary()));
		setDragHandRegister(EnergyMeter.REG_MAX_LOAD_LOSSES,    Math.abs(totalLoadLosses.getReal()));
		setDragHandRegister(EnergyMeter.REG_MAX_NO_LOAD_LOSSES, Math.abs(totalNoLoadLosses.getReal()));
		setDragHandRegister(EnergyMeter.REG_ZONE_MAX_KW,        totalZoneKW);  // Removed abs()  3-10-04
		setDragHandRegister(EnergyMeter.REG_ZONE_MAX_KVA,       loadKVA);
		/* Max total generator registers */
		setDragHandRegister(EnergyMeter.REG_GEN_MAX_KW, totalGenKW);  // removed abs()  3-10-04
		setDragHandRegister(EnergyMeter.REG_GEN_MAX_KVA, genKVA);

		/* ------------------------------------------------------------------ */
		/* ---------------------   Overload Energy  ------------------------- */
		/* ------------------------------------------------------------------ */
		/* Overload energy for the entire zone */
		if (localOnly) {
			zoneKW = S_Local.getReal();
		} else {
			zoneKW = totalZoneKW;
		}

		/* Either the max excess kW of any PD element or the excess over zone limits */

		/* regs 9 and 10 */
		/* Fixed these formulas 2-7-07 per discussions with Daniel Brooks */
		if (maxZoneKVANorm > 0.0) {
			if (S_LocalKVA == 0.0)
				S_LocalKVA = maxZoneKVANorm;
			integrate(EnergyMeter.REG_OVERLOAD_KWH_NORM, Math.max(0.0, (zoneKW * (1.0 - maxZoneKVANorm / S_LocalKVA))), deltaHrs);
		} else {
			integrate(EnergyMeter.REG_OVERLOAD_KWH_NORM, maxExcessKWNorm, deltaHrs);
		}

		if (maxZoneKVAEmerg > 0.0) {
			if (S_LocalKVA == 0.0)
				S_LocalKVA = maxZoneKVAEmerg;
			integrate(EnergyMeter.REG_OVERLOAD_KWH_EMERG, Math.max(0.0, (zoneKW * (1.0 - maxZoneKVAEmerg / S_LocalKVA))), deltaHrs);
		} else {
			integrate(EnergyMeter.REG_OVERLOAD_KWH_EMERG, maxExcessKWEmerg,  deltaHrs);
		}

		firstSampleAfterReset = false;
		if (DSSGlobals.energyMeterClass.isSaveDemandInterval())
			writeDemandIntervalData();
	}

	private void totalUpDownstreamCustomers() {
		int i, accumulator;
		CktTreeNode presentNode = null;
		PDElement cktElem;

		if (branchList == null) {
			DSSGlobals.doSimpleMsg("Meter zone lists need to be built. Solve or makeBusList first.", 529);
			return;
		}

		/* Init totals and checked flag */
		cktElem = (PDElement) branchList.getFirst();
		while (cktElem != null) {
			cktElem.setChecked(false);
			cktElem.setTotalCustomers(0);
			cktElem = (PDElement) branchList.goForward();
		}

		/* This algorithm could be made more efficient with a sequence list */

		for (i = 0; i < branchList.getZoneEndsList().getNumEnds(); i++) {
			/*busref = */branchList.getZoneEndsList().get(i, presentNode);
			if (presentNode != null) {
				cktElem = (PDElement) presentNode.getCktObject();
				if (!cktElem.isChecked()) {  // don't do a zone end element more than once
					cktElem.setChecked(true);
					accumulator = cktElem.getNumCustomers();
					while (true) {  /* Trace back to the source */
						cktElem.setTotalCustomers( cktElem.getTotalCustomers() + accumulator );
						presentNode = presentNode.getParent();
						if (presentNode == null)
							break;
						cktElem = (PDElement) presentNode.getCktObject();
						if (!cktElem.isChecked()) {  // avoid double counting
							accumulator = accumulator + cktElem.getNumCustomers();
							cktElem.setChecked(true);
						}
					}
				}
			}
		}
	}

	/**
	 * This gets fired off whenever the buslists are rebuilt.
	 * Must be updated whenever there is a change in the circuit.
	 */
	public void makeMeterZoneLists() {
		Circuit ckt = DSSGlobals.activeCircuit;

		int testBusNum, zoneListCounter;
		int j, iTerm, iPC, iPD;
		CktElement activeBranch;
		PDElement testElement;
		PCElement pc;
		LoadObj load;
		boolean isFeederEnd;
		List<PCElement> adjLstPC;
		List<PDElement> adjLstPD;

		zoneListCounter = 0;
		VBaseCount = 0;  /* Build the voltage base list over in case a base added or deleted */
		for (j = 0; j < maxVBaseCount; j++)
			VBaseList[j] = 0.0;

		branchList = new CktTreeImpl();  /* Instantiates ZoneEndsList, too */

		// get started
		if (meteredElement != null) {
			branchList.setNew(meteredElement);
		} else {
			DSSGlobals.doSimpleMsg("Metered Element for EnergyMeter "+getName()+" not defined.", 527);
			return;
		}

		/* Initialize sensorObj property of the first branch to this MeterElement object.
		 * Before starting, all sensorObj definitions are cleared in PC elements and PD elements. The
		 * sensorObj property is passed down to the Load objects for loadAllocation and state estimation.
		 */
		if (meteredElement instanceof PDElement) {
			((PDElement) meteredElement).setSensorObj(this);
		} else if (meteredElement instanceof PCElement) {
			((PCElement) meteredElement).setSensorObj(this);
		}

		if (meteredElement instanceof PDElement) {
			((PDElement) meteredElement).setMeterObj(this);
		} else if (meteredElement instanceof PCElement) {
			((PCElement) meteredElement).setMeterObj(this);
		}

		meteredElement.getTerminal(meteredTerminal).setChecked(true);
		CktTreeNode pb = branchList.getPresentBranch();
		// this bus is the head of the feeder; do not mark as radial bus
		pb.setFromBusReference( meteredElement.getTerminal(meteredTerminal).getBusRef() );
		DSSGlobals.activeCircuit.getBus(pb.getFromBusReference()).setDistFromMeter(0.0);
		pb.setVoltBaseIndex( addToVoltBaseList(pb.getFromBusReference()) );
		pb.setFromTerminal(meteredTerminal);
		if (meteredElement instanceof PDElement)
			((PDElement) meteredElement).setFromTerminal(meteredTerminal);

		// check off this element so we don't use it again
		meteredElement.setChecked(true);
		meteredElement.setIsolated(false);

		// now start looking for other branches
		// finds any branch connected to the testBranch and adds it to the list
		// goes until end of circuit, another energy meter, an open terminal, or disabled device
		activeBranch = meteredElement;
		while (activeBranch != null) {
			pb = branchList.getPresentBranch();
			pb.setLoopedHere(false);
			pb.setParallel(false);
			pb.setDangling(true);  // unless we find something connected to it
			pb.setVoltBaseIndex( addToVoltBaseList(pb.getFromBusReference()) );

			((PDElement) activeBranch).setNumCustomers(0);  // init counter

			for (iTerm = 0; iTerm < activeBranch.getNTerms(); iTerm++) {
				if (!activeBranch.getTerminal(iTerm).isChecked()) {
					// now find all loads and generators connected to the bus on this end of branch
					// attach them as generic objects to cktTree node
					testBusNum = activeBranch.getTerminal(iTerm).getBusRef();
					branchList.getPresentBranch().setToBusReference(testBusNum);  // add this as a "to" bus reference
					if (Utilities.isLineElement(activeBranch)) {  // convert to consistent units (km)
						ckt.getBus(testBusNum).setDistFromMeter( ckt.getBus( branchList.getPresentBranch().getFromBusReference() ).getDistFromMeter()
								+ ((LineObj) activeBranch).getLen() * LineUnits.convertLineUnits( ((LineObj) activeBranch).getLengthUnits(), LineUnits.UNITS_KM) );
					} else {
						ckt.getBus(testBusNum).setDistFromMeter( ckt.getBus(branchList.getPresentBranch().getFromBusReference()).getDistFromMeter() );
					}

					adjLstPC = EnergyMeterImpl.busAdjPC[testBusNum];
					for (iPC = 0; iPC < adjLstPC.size(); iPC++) {
						pc = (PCElement) adjLstPC.get(iPC);
						if (pc.isChecked())
							continue;  // skip ones we already checked
						branchList.getPresentBranch().setDangling(false);  // something is connected here
						// is this a load or a generator or a capacitor or reactor?
						if (((pc.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT)
								|| ((pc.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.GEN_ELEMENT)
								|| ((pc.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.CAP_ELEMENT)
								|| ((pc.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.REACTOR_ELEMENT)) {

							branchList.setNewObject(pc);
							pc.setChecked(true);  // so we don't pick this element up again
							pc.setIsolated(false);
							pc.setActiveTerminalIdx(0);
							/* Totalize number of customers if load type */
							if (pc instanceof LoadObj) {
								load = (LoadObj) pc;
								((PDElement) activeBranch).setNumCustomers( ((PDElement) activeBranch).getNumCustomers() + load.getNumCustomers() );
							}
							/* If object does not have a sensor attached, it acquires the sensor of its parent branch */
							if (!pc.hasSensorObj())
								pc.setSensorObj( ((PDElement) activeBranch).getSensorObj() );
							pc.setMeterObj(this);
						}
					}

					// now find all branches connected to this bus that we havent found already
					// do not include in this zone if branch has open terminals or has another meter

					if (definedZoneListSize == 0) {  // search tree for connected branches (default)
						isFeederEnd = true;
						adjLstPD = EnergyMeterImpl.busAdjPD[testBusNum];
						for (iPD = 0; iPD < adjLstPD.size(); iPD++) {
							testElement = (PDElement) adjLstPD.get(iPD);  // only enabled objects are in this list
							// see resetMeterZonesAll()
							if (!(testElement == activeBranch)) {  // skip self
								if (!testElement.hasEnergyMeter()) {  // stop at other meters so zones don't interfere
									for (j = 0; j < testElement.getNTerms(); j++) {  // check each terminal
										if (testBusNum == testElement.getTerminal(j).getBusRef()) {
											branchList.getPresentBranch().setDangling(false);  // we found something it was connected to
											/* Check for loops and parallel branches and mark them */
											if (testElement.isChecked()) {  /* This branch is on some meter's list already */
												branchList.getPresentBranch().setLoopedHere(true);  /* It's a loop */
												branchList.getPresentBranch().setLoopLineObj(testElement);
												if (Utilities.isLineElement(activeBranch) && Utilities.isLineElement(testElement))
													if (Utilities.checkParallel(activeBranch, testElement))
														branchList.getPresentBranch().setParallel(true);  /* It's paralleled with another line */
											} else {  // push testElement onto stack and set properties
												isFeederEnd = false;  // for interpolation
												branchList.addNewChild(testElement, testBusNum, j);  // add new child to the branch list
												testElement.getTerminal(j).setChecked(true);
												testElement.setFromTerminal(j);
												testElement.setChecked(true);
												testElement.setIsolated(false);
												/* Branch inherits sensor of upline branch if it doesn't have its own */
												if (!hasSensorObj)
													testElement.setSensorObj( ((PDElement) activeBranch).getSensorObj() );
												testElement.setMeterObj(this);   // set meterobj to this meter
												testElement.setParentPDElement( (PDElement) activeBranch );  // record the parent so we can easily back up for reconductoring, etc.
												break;
											}
										}  /* if testBusNum */
									}  /* for terminals */
								}
							}
						}  /* for iPD */
						if (isFeederEnd)
							branchList.getZoneEndsList().add(branchList.getPresentBranch(), testBusNum);
						/* This is an end of the feeder and testBusNum is the end bus */
					} else {  // zone is manually specified; just add next element in list as a child
						zoneListCounter += 1;
						while (zoneListCounter < definedZoneListSize) {
							if (ckt.setElementActive(definedZoneList[zoneListCounter]) == 0) {
								zoneListCounter += 1;  // not found, let's search for another
							} else {
								testElement = (PDElement) ckt.getActiveCktElement();
								if (!testElement.isEnabled()) {
									zoneListCounter += 1;  // lets ignore disabled devices
								} else {
									if ((testElement.getDSSObjType() & DSSClassDefs.BASECLASSMASK) != DSSClassDefs.PD_ELEMENT) {
										zoneListCounter += 1;  // lets ignore non-PD elements
									} else {
										branchList.addNewChild(testElement, 0, 0);  // add it as a child to the previous element
									}
									break;  // can't do reductions if manually spec'd
								}
							}
						}  // while
					}
				}
			}  /* for iTerm */
			activeBranch = (DSSCktElement) branchList.goForward();  // sets present node
		}

		totalUpDownstreamCustomers();

		if (hasFeeder)
			feederObj.initializeFeeder(branchList);  // synchronise the feeder definition

		assignVoltBaseRegisterNames();
	}

	@Override
	public void getCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	@Override
	public void getInjCurrents(Complex[] curr) {
		for (int i = 0; i < nConds; i++)
			curr[i] = Complex.ZERO;
	}

	public void zoneDump() {
		File f;
		FileWriter fw;
		BufferedWriter bw;
		PDElement pd;
		CktElement loadElem;

		Circuit ckt = DSSGlobals.activeCircuit;

		String csvName = "Zone_" + getName() + ".csv";

		try {
			f = new File(DSSGlobals.DSSDataDirectory, csvName);
			fw = new FileWriter(f, false);
			bw = new BufferedWriter(fw);

			DSSGlobals.globalResult = csvName;

			bw.write("Level, Branch, Bus1, Bus2, Distance");
			bw.newLine();

			if (branchList != null) {
				pd = (PDElement) branchList.getFirst();
				while (pd != null) {
					bw.write(String.format("%d, %s.%s, %s, %s, %10.4f",
							branchList.getLevel(), pd.getParentClass().getName(), pd.getName(),
							pd.getFirstBus(), pd.getNextBus(),
							/*BusList.get(BranchList.getPresentBranch().getToBusReference()),*/
							ckt.getBus(branchList.getPresentBranch().getToBusReference()).getDistFromMeter()));
					bw.newLine();
					loadElem = (CktElement) branchList.getFirstObject();
					while (loadElem != null) {
						bw.write("-1, " + String.format("%s.%s, %s", loadElem.getParentClass().getName(), loadElem.getName(), loadElem.getFirstBus()/*ckt.getBusList().get(BranchList.getPresentBranch().getToBusReference())*/));
						bw.newLine();
						loadElem = (CktElement) branchList.getNextObject();
					}
					pd = (PDElement) branchList.goForward();
				}
			}

			bw.close();
			fw.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening file \"" + csvName + "\": " + e.getMessage(), 528);
		}
	}

	@Override
	public void dumpProperties(PrintStream f, boolean complete) {
		int i;
		PDElement pd;
		CktElement load;

		super.dumpProperties(f, complete);

		for (i = 0; i < getParentClass().getNumProperties(); i++) {
			switch (i) {
			case 3:  // option
				f.print("~ " + getParentClass().getPropertyName()[i] + "=(");
				if (excessFlag) {
					f.print("E,");
				} else {
					f.print("T,");
				}
				if (zoneIsRadial) {
					f.print(" R,");
				} else {
					f.print(" M,");
				}
				if (voltageUEOnly) {
					f.print(" V");
				} else {
					f.print(" C");
				}
				f.println(")");
				break;
			case 6:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=(" + getPropertyValue(i) + ")");
				break;
			default:
				f.println("~ " + getParentClass().getPropertyName()[i] + "=" + getPropertyValue(i));
				break;
			}
		}

		if (complete) {
			f.println("Registers");
			for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
				f.println("\"" + registerNames[i] + "\" = " + registers[i]);
			f.println();

			f.println("Branch List:");
			if (branchList != null) {
				pd = (PDElement) branchList.getFirst();
				while (pd != null) {
					f.println("Circuit Element = " + pd.getName());
					load = (CktElement) branchList.getFirstObject();
					while (load != null) {
						f.println("   Shunt Element = " + load.getParentClass().getName() + "." + load.getName());
						load = (CktElement) branchList.getNextObject();
					}
					pd = (PDElement) branchList.goForward();
				}
			}
		}
	}

	/**
	 * Add to VoltBase list if not already there and return index.
	 */
	private int addToVoltBaseList(int busRef) {
		Bus bus = DSSGlobals.activeCircuit.getBus(busRef);

		for (int i = 0; i < VBaseCount; i++) {
			if (Math.abs(1.0 - bus.getKVBase() / VBaseList[i]) < 0.01)  // < 1% difference
				return i;
		}

		if (bus.getKVBase() > 0.0 && VBaseCount < maxVBaseCount) {
			VBaseCount += 1;  // TODO Check incrementation
			VBaseList[VBaseCount] = bus.getKVBase();
			return VBaseCount;
		} else {
			return 0;
		}
	}

	public void allocateLoad() {
		int connectedPhase;
		PDElement cktElem;
		LoadObj loadElem;

		/* Now go through the meter's zone and adjust the loads.
		 *
		 * While the AllocationFactor property is adjusted for all loads, it will only
		 * have an effect on loads defined with either the XFKVA property or the
		 * kWh property.
		 *
		 * Loads have a sensorObj property that points to its upstream sensor that has the adjustments for
		 * the allocation factors.  This is established in the makeMeterZoneLists proc in this unit.
		 *
		 * Sensors consist of EnergyMeters, which drive the load allocation process and Sensor objects that
		 * are simply voltage and current measuring points.  A Sensor may be attached to a line or transformer
		 * or it may be connected directly to a load.
		 */

		cktElem = (PDElement) branchList.getFirst();
		while (cktElem != null) {
			loadElem = (LoadObj) branchList.getFirstObject();
			while (loadElem != null) {
				if ((loadElem.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT) {  // only for loads not other shunts
					switch (loadElem.getNPhases()) {
					/* For single phase loads, allocate based on phase factor, else average factor */
					case 1:
						connectedPhase = DSSGlobals.activeCircuit.getMapNodeToBus()[nodeRef[0]].nodeNum;  // one-based
						if (connectedPhase > 0 && connectedPhase < 4)  // restrict to phases 1..3
							loadElem.setAllocationFactor( loadElem.getAllocationFactor() * loadElem.getSensorObj().getPhsAllocationFactor()[connectedPhase] );
						break;
					default:
						loadElem.setAllocationFactor( loadElem.getAllocationFactor() * avgAllocFactor);
						break;
					}
				}
				loadElem = (LoadObj) branchList.getNextObject();  /* Next load at this bus */
			}
			cktElem = (PDElement) branchList.goForward();  /* Go on down the tree */
		}
	}

	@Override
	public void initPropertyValues(int arrayOffset) {
		String s;

		setPropertyValue(0, "");          // "element";
		setPropertyValue(1, "1");         // "terminal";
		setPropertyValue(2, "clear");     // "action";
		setPropertyValue(3, "(E, R, C)"); // "option";
		setPropertyValue(4, "0.0");       // "kWnormal";
		setPropertyValue(5, "0.0");       // "kwEmerg";
		setPropertyValue(6, "(400, 400, 400)"); // "peakCurrent";
		setPropertyValue(7, "");  // zoneList
		setPropertyValue(8, "No");
		/* Define mask as 1 for all registers */
		s = "[";
		for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			s = s + "1 ";
		setPropertyValue(9, s + "]");
		setPropertyValue(10, "Yes");
		setPropertyValue(11, "Yes");
		setPropertyValue(12, "Yes");
		setPropertyValue(13, "Yes");
		setPropertyValue(14, "Yes");  // segregate losses by voltage base
		setPropertyValue(15, "Yes");
		setPropertyValue(16, "No");

		super.initPropertyValues(EnergyMeter.NumPropsThisClass);
	}

	private void accumulateGen(GeneratorObj gen, double totalZonekW, double totalZoneKVAr) {  // FIXME Pass by reference
		//pGen.setActiveTerminalIdx(1);
		Complex S = gen.getPower(0).multiply(0.001).negate();
		totalZonekW   = totalZonekW   + S.getReal();
		totalZoneKVAr = totalZoneKVAr + S.getImaginary();
	}

	private double accumulateLoad(LoadObj load, double totalZoneKW, double totalZoneKVAr, double totalLoad_EEN, double totalLoad_UE) {  // FIXME Pass be reference

		Complex SLoad;
		double kWLoad, result;
		double loadEEN, loadUE;

		//pLoad.setActiveTerminalIdx(1);
		SLoad = load.getPower(0).multiply(0.001);  // get power in terminal 1
		kWLoad = SLoad.getReal();
		result = kWLoad;

		/* Accumulate load in zone */
		totalZoneKW = totalZoneKW + kWLoad;
		totalZoneKVAr = totalZoneKVAr + SLoad.getImaginary();

		/* Always integrate even if the value is 0.0
		 * otherwise the integrate function is not correct.
		 */
		/* Invoking the exceedsNormal and unserved properties causes the factors to be computed */
		if (excessFlag) {  // return excess load as EEN/UE
			if (load.getExceedsNormal()) {
				loadEEN = kWLoad * load.getEEN_Factor();
			} else {
				loadEEN = 0.0;
			}
			if (load.getUnserved()) {
				loadUE  = kWLoad * load.getUE_Factor();
			} else {
				loadUE = 0.0;
			}
		} else {  // return total load as EEN/UE
			if (load.getExceedsNormal()) {
				loadEEN = kWLoad;
			} else {
				loadEEN = 0.0;
			}
			if (load.getUnserved()) {
				loadUE = kWLoad;
			} else {
				loadUE = 0.0;
			}
		}

		totalLoad_EEN = totalLoad_EEN + loadEEN;
		totalLoad_UE  = totalLoad_UE  + loadUE;

		return result;
	}

	/**
	 * Reduce zone by eliminating buses and merging lines.
	 */
	public void reduceZone() {
		// make sure zone list is built
		if (branchList == null) makeMeterZoneLists();

		switch (DSSGlobals.activeCircuit.getReductionStrategy()) {
		case STUBS:          ReduceAlgs.doReduceStubs(branchList); break;
		case TAP_ENDS:       ReduceAlgs.doReduceTapEnds (branchList); break;
		case MERGE_PARALLEL: ReduceAlgs.doMergeParallelLines(branchList); break;
		case DANGLING:       ReduceAlgs.doReduceDangling(branchList); break;
		case BREAK_LOOP:     ReduceAlgs.doBreakLoops(branchList); break;
		case SWITCHES:       ReduceAlgs.doReduceSwitches(branchList); break;
		default:             ReduceAlgs.doReduceDefault(branchList); break;
		}

		// resynchronize with feeders
		if (hasFeeder)
			feederObj.initializeFeeder(branchList);
	}

	/**
	 * Start at the ends of the zone and work toward the start
	 * interpolating between known coordinates.
	 */
	public void interpolateCoordinates() {
		int i, busRef, firstCoordRef, secondCoordRef, lineCount;
		CktTreeNode startNode, presentNode = null;
		CktElement cktElem;

		Circuit ckt = DSSGlobals.activeCircuit;

		if (branchList == null) {
			DSSGlobals.doSimpleMsg("Meter zone lists need to be built. Solve or makeBusList first.", 529);
			return;
		}

		for (i = 0; i < branchList.getZoneEndsList().getNumEnds(); i++) {
			busRef = branchList.getZoneEndsList().get(i, presentNode);

			firstCoordRef = busRef;
			secondCoordRef = firstCoordRef;  /* so compiler won't issue warning */
			/* Find a bus with a coordinate */
			if (!ckt.getBus(busRef).isCoordDefined()) {
				while (!ckt.getBus( presentNode.getFromBusReference() ).isCoordDefined()) {
					presentNode = presentNode.getParent();
					if (presentNode == null)
						break;
				}
				if (presentNode != null)
					firstCoordRef = presentNode.getFromBusReference();
			}

			while (presentNode != null) {
				/* Back up until we find another coord defined */
				lineCount = 0;  /* number of line segments in this segment */
				startNode = presentNode;
				cktElem = (CktElement) presentNode.getCktObject();
				if (firstCoordRef != presentNode.getFromBusReference()) {
					/* Handle special case for end branch */
					if (ckt.getBus( presentNode.getFromBusReference() ).isCoordDefined()) {
						firstCoordRef = presentNode.getFromBusReference();
					} else {
						lineCount += 1;
					}
				}

				while (!ckt.getBus( secondCoordRef ).isCoordDefined() && !cktElem.isChecked()) {
					cktElem.setChecked(true);
					presentNode = presentNode.getParent();
					if (presentNode == null)
						break;
					cktElem = (CktElement) presentNode.getCktObject();
					secondCoordRef = presentNode.getFromBusReference();
					lineCount += 1;
				}

				if (presentNode != null && lineCount > 1) {
					if (ckt.getBus(secondCoordRef).isCoordDefined()) {
						calcBusCoordinates(startNode,  firstCoordRef, secondCoordRef, lineCount);
					} else {
						break;  /* While - went as far as we could go this way */
					}
				}

				firstCoordRef = secondCoordRef;
			}
		}  /* for */
	}

	private void calcBusCoordinates(CktTreeNode startBranch, int firstCoordRef, int secondCoordRef, int lineCount) {
		double X, Y, XInc, YInc;

		if (lineCount == 1)
			return;

		Circuit ckt = DSSGlobals.activeCircuit;

		XInc = (ckt.getBus(firstCoordRef).getX() - ckt.getBus(secondCoordRef).getX()) / lineCount;
		YInc = (ckt.getBus(firstCoordRef).getY() - ckt.getBus(secondCoordRef).getY()) / lineCount;

		X = ckt.getBus(firstCoordRef).getX();
		Y = ckt.getBus(firstCoordRef).getY();

		/*if (((X < 10.0) && (y < 10.0)) || ((ckt.getBuses()[SecondCoordRef].getX() < 10.0) && (ckt.getBuses()[SecondCoordRef].getY() < 10.0)))
			X = Y;*/  // stopping point

		/* Either start with the "to" end of startNode or the "from" end; */
		if (firstCoordRef != startBranch.getFromBusReference()) {
			// start with "to" end
			X = X - XInc;
			Y = Y - YInc;
			ckt.getBus(startBranch.getFromBusReference()).setX(X);
			ckt.getBus(startBranch.getFromBusReference()).setY(Y);
			ckt.getBus(startBranch.getFromBusReference()).setCoordDefined(true);
			lineCount -= 1;
		}

		while (lineCount > 1) {
			X = X - XInc;
			Y = Y - YInc;
			startBranch = startBranch.getParent();  // back up the tree
			ckt.getBus(startBranch.getFromBusReference()).setX(X);
			ckt.getBus(startBranch.getFromBusReference()).setY(Y);
			ckt.getBus(startBranch.getFromBusReference()).setCoordDefined(true);
			lineCount -= 1;
		}
	}

	@Override
	public String getPropertyValue(int index) {
		String result;

		switch (index) {
		case 3:
			result = "(";
			break;
		case 6:
			result = "(";
			break;
		default:
			result = "";
			break;
		}

		switch (index) {
		case 3:  // option
			if (excessFlag) {
				result = result + "E,";
			} else {
				result = result + "T,";
			}
			if (zoneIsRadial) {
				result = result + " R,";
			} else {
				result = result + " M,";
			}
			if (voltageUEOnly) {
				result = result + " V";
			} else {
				result = result + " C";
			}
			break;
		default:
			result = result + super.getPropertyValue(index);
			break;
		}

		switch (index) {
		case 3:
			result = result + ")";
			break;
		case 6:
			result = result + ")";
			break;
		}

		return result;
	}

	public void saveZone(String dirname) {
		CktElement cktElem, shuntElement;
		LoadObj loadElement;
		File fbranches, fshunts, floads, fgens, fcaps;
		FileWriter fwbranches, fwshunts, fwloads, fwgens, fwcaps;
		PrintWriter pwbranches, pwshunts, pwloads, pwgens, pwcaps;
		int nbranches, nshunts, nloads, ngens, ncaps;

		Circuit ckt = DSSGlobals.activeCircuit;

		/* We are in the directory indicated by dirname */

		/* Run down the zone and write each element into a file */

		if (branchList != null) {
			/* Open some files: */

			try {
				fbranches = new File("Branches.dss");  // both lines and transformers
				fwbranches = new FileWriter(fbranches, false);
				pwbranches = new PrintWriter(fwbranches);

				nbranches = 0;
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error creating Branches.dss for EnergyMeter: " + getName()+". " + e.getMessage(), 530);
				return;
			}

			try {
				fshunts = new File("Shunts.dss");
				fwshunts = new FileWriter(fshunts, false);
				pwshunts = new PrintWriter(fwshunts);

				nshunts = 0;
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error creating Shunts.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 531);
				return;
			}

			try {
				floads = new File("Loads.dss");
				fwloads = new FileWriter(floads, false);
				pwloads = new PrintWriter(fwloads);

				nloads = 0;
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error creating Loads.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 532);
				return;
			}

			try {
				fgens = new File("Generators.dss");
				fwgens = new FileWriter(fgens, false);
				pwgens = new PrintWriter(fwgens);

				ngens = 0;
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error creating Generators.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 533);
				return;
			}

			try {
				fcaps = new File("Capacitors.dss");
				fwcaps = new FileWriter(fcaps, false);
				pwcaps = new PrintWriter(fwcaps);
				ncaps = 0;
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error creating Capacitors.dss for EnergyMeter: " + getName() + ". " + e.getMessage(), 534);
				return;
			}


			cktElem = (CktElement) branchList.getFirst();
			while (cktElem != null) {
				if (cktElem.isEnabled()) {
					ckt.setActiveCktElement(cktElem);
					nbranches += 1;
					Utilities.writeActiveDSSObject(pwbranches, "New");  // sets hasBeenSaved(true)
					if (ckt.getActiveCktElement().hasControl()) {
						ckt.setActiveCktElement( ckt.getActiveCktElement().getControlElement() );
						Utilities.writeActiveDSSObject(pwbranches, "New");  // regulator control ... also, relays, switch controls
					}

					shuntElement = (CktElement) branchList.getFirstObject();
					while (shuntElement != null) {
						ckt.setActiveCktElement(shuntElement);
						if ((shuntElement.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.LOAD_ELEMENT) {
							loadElement = (LoadObj) shuntElement;
							if (loadElement.getHasBeenAllocated()) {
								/* Manually set the allocation factor so it shows up */
								Parser.getInstance().setCmdString( "allocationfactor=" + String.format("%-.4g", loadElement.getAllocationFactor()) );
								loadElement.edit();
							}
							ckt.setActiveCktElement(shuntElement);  // reset in case edit mangles it
							nloads += 1;
							Utilities.writeActiveDSSObject(pwloads, "New");
						} else if ((shuntElement.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.GEN_ELEMENT) {
							ngens += 1;
							Utilities.writeActiveDSSObject(pwgens, "New");
							if (ckt.getActiveCktElement().hasControl()) {
								ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement());
								Utilities.writeActiveDSSObject(pwgens, "New");
							}
						} else if ((shuntElement.getDSSObjType() & DSSClassDefs.CLASSMASK) == DSSClassDefs.CAP_ELEMENT) {
							ncaps += 1;
							Utilities.writeActiveDSSObject(pwcaps, "New");
							if (ckt.getActiveCktElement().hasControl()) {
								ckt.setActiveCktElement(ckt.getActiveCktElement().getControlElement());
								Utilities.writeActiveDSSObject(pwcaps, "New");
							}
						} else {
							nshunts += 1;
							Utilities.writeActiveDSSObject(pwshunts, "New");
						}
						shuntElement = (CktElement) branchList.getNextObject();
					}
				}  /* if enabled */
				cktElem = (CktElement) branchList.goForward();
			}

			pwbranches.close();
			pwshunts.close();
			pwloads.close();
			pwgens.close();
			pwcaps.close();

			try {
				fwbranches.close();
				fwshunts.close();
				fwloads.close();
				fwgens.close();
				fwcaps.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/* If any records were written to the file, record their relative names */
			if (nbranches > 0) {
				DSSGlobals.savedFileList.add(dirname + "/Branches.dss");
			} else {
				fbranches.delete();
			}
			if (nshunts > 0) {
				DSSGlobals.savedFileList.add(dirname + "/Shunts.dss");
			} else {
				fshunts.delete();
			}
			if (nloads > 0) {
				DSSGlobals.savedFileList.add(dirname + "/Loads.dss");
			} else {
				floads.delete();
			}
			if (ngens > 0) {
				DSSGlobals.savedFileList.add(dirname + "/Generators.dss");
			} else {
				fgens.delete();
			}
			if (ncaps > 0) {
				DSSGlobals.savedFileList.add(dirname + "/Capacitors.dss");
			} else {
				fcaps.delete();
			}
		}
	}

	private void setDragHandRegister(int reg, double value) {
		if (value > registers[reg]) {
			registers[reg]   = value;
			derivatives[reg] = value;  // use this for demand interval data
		}
	}

	// FIXME Protected method in OpenDSS
	public void closeDemandIntervalFile() {
		try {
			if (thisMeterDI_FileIsOpen) {
				DI_File.close();
				thisMeterDI_FileIsOpen = false;
				if (VPhaseReportFileIsOpen) VPhaseFile.close();
				VPhaseReportFileIsOpen = false;
			}
		} catch (IOException e) {
			DSSGlobals.doSimpleMsg("Error closing demand interval file for meter \""+getName()+"\"", 534);
		}

		/* Write registers to totals file */
		PrintWriter meterTotalsPrinter = new PrintWriter(DSSGlobals.energyMeterClass.getMeterTotals());
		meterTotalsPrinter.print("\"" + getName() + "\"");
		for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			meterTotalsPrinter.printf(", %-g", registers[i]);
		meterTotalsPrinter.println();
		meterTotalsPrinter.close();
	}

	// FIXME Protected method in OpenDSS
	public void openDemandIntervalFile() {
		int i, j;
		double VBase;

		try {
			if (thisMeterDI_FileIsOpen)
				closeDemandIntervalFile();

			if (DSSGlobals.energyMeterClass.isDIVerbose()) {

				DI_File = new FileWriter(makeDIFileName());
				PrintWriter DI_Printer = new PrintWriter(DI_File);

				thisMeterDI_FileIsOpen = true;
				DI_Printer.print("\"Hour\"");
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					DI_Printer.print(", \"" + registerNames[i] + "\"");
				DI_Printer.println();
				DI_Printer.close();

				/* Phase voltage report, if requested */
				if (phaseVoltageReport) {
					VPhaseFile = new FileWriter(makeVPhaseReportFileName());
					PrintWriter VPhase_Printer = new PrintWriter(VPhaseFile);

					VPhaseReportFileIsOpen = true;
					VPhase_Printer.write("\"Hour\"");
					for (i = 0; i < maxVBaseCount; i++) {
						VBase = VBaseList[i] * DSSGlobals.SQRT3;
						if (VBase > 0.0) {
							for (j = 0; j < 3; j++) {
								VPhase_Printer.printf(", %.3gkV_Phs_%d_Max", VBase, j);
								VPhase_Printer.printf(", %.3gkV_Phs_%d_Min", VBase, j);
								VPhase_Printer.printf(", %.3gkV_Phs_%d_Avg", VBase, j);
							}
						}
					}
					VPhase_Printer.println();
					VPhase_Printer.close();
				}

			}
		} catch (IOException e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file \"" + getName() + ".csv" +" for writing."+DSSGlobals.CRLF+e.getMessage(), 535);
		}
	}

	protected void writeDemandIntervalData() {
		int i, j;

		SolutionObj sol = DSSGlobals.activeCircuit.getSolution();

		if ((DSSGlobals.energyMeterClass.isDIVerbose()) && thisMeterDI_FileIsOpen) {
			PrintWriter DI_Printer = new PrintWriter(DI_File);
			DI_Printer.printf("%-.6g", sol.getDblHour());
			for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
				DI_Printer.printf(", %-.6g", derivatives[i]);
			DI_Printer.println();
			DI_Printer.close();
		}

		/* Add to class demand interval registers */
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			DSSGlobals.energyMeterClass.getDI_RegisterTotals()[i] += derivatives[i] * totalsMask[i];

		/* Phase voltage report, if requested */
		if (VPhaseReportFileIsOpen) {
			PrintWriter VPhase_Printer = new PrintWriter(VPhaseFile);
			VPhase_Printer.printf("%-.6g", sol.getDblHour());
			for (i = 0; i < maxVBaseCount; i++) {
				if (VBaseList[i] > 0.0) {
					for (j = 0; j < 3; j++) {
						VPhase_Printer.printf(", %-.6g", 0.001 * VPhaseMax[jiIndex(j, i)]);
						VPhase_Printer.printf(", %-.6g", 0.001 * VPhaseMin[jiIndex(j, i)]);
						VPhase_Printer.printf(", %-.6g", 0.001 * myCountAvg(VPhaseAccum[jiIndex(j, i)], VPhaseAccumCount[jiIndex(j, i)]));
					}
				}
			}
			VPhase_Printer.println();
		}
	}
	private double myCountAvg(double value, int count) {
		if (count == 0) {
			return 0.0;
		} else {
			return value / count;
		}
	}

	/**
	 * Only called if "SaveDemandInterval".
	 */
	// FIXME Protected method in OpenDSS
	public void appendDemandIntervalFile() {
		String fileName;

		if (thisMeterDI_FileIsOpen)
			return;

		try {
			if (DSSGlobals.energyMeterClass.isDIVerbose()) {
				fileName = makeDIFileName();  // creates directory if it doesn't exist
				/* File must exist */
				if (new File(fileName).exists()) {
					DI_File = new FileWriter(fileName, true);
				} else {
					DI_File = new FileWriter(fileName, false);
				}

				thisMeterDI_FileIsOpen = true;
			}
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file \""+getName()+".csv" + " for appending."+DSSGlobals.CRLF+e.getMessage(), 537);
		}
	}

	private void assignVoltBaseRegisterNames() {
		int i, ireg;
		double VBase;

		ireg = 0;
		for (i = 0; i < maxVBaseCount; i++) {
			if (VBaseList[i] > 0.0) {
				VBase = VBaseList[i] * DSSGlobals.SQRT3;
				registerNames[i + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Losses", VBase);
				registerNames[i + 1 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Line Loss", VBase);
				registerNames[i + 2 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Load Loss", VBase);
				registerNames[i + 3 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV No Load Loss", VBase);
				registerNames[i + 4 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("%.3g kV Load Energy", VBase);
			} else {
				registerNames[i + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 1 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 2 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 3 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
				registerNames[i + 4 * maxVBaseCount + EnergyMeter.REG_VBASE_START] = String.format("Aux%d", ireg);
				ireg += 1;
			}
		}
		for (i = EnergyMeter.REG_VBASE_START + 5; i < EnergyMeter.NUM_EM_REGISTERS; i++) {  // TODO Check zero based indexing
			registerNames[i] = String.format("Aux%d", ireg);
			ireg += 1;
		}
	}

	private void makeFeederObj() {

		if (meteredElement != null) {
//			Globals.getFeederClass().newObject(getName());  // newObject creates only if not existent else inits and desynchs
//			setFeederObj( (FeederObj) DSSGlobals.activeCircuit.getActiveCktElement() );
//			getFeederObj().setBus(1, MeteredElement.getBus(MeteredTerminal));  // TODO Check zero based indexing
//			getFeederObj().setNPhases(MeteredElement.getNPhases());
//			getFeederObj().setNConds(MeteredElement.getNConds());
			//getFeederObj().setEnabled(DSSGlobals.activeCircuit.isRadialSolution());
		} else {
			DSSGlobals.doSimpleMsg("Error: Attempted to make Feeder object without instantiating meteredElement in EnergyMeter."+getName(), 544);
		}
	}

	// FIXME Private method in OpenDSS
	public void removeFeederObj() {
		if (feederObj != null) {
			feederObj.setEnabled(false);
			feederObj.setCktElementFeederFlags(false);
		}
	}

	/**
	 * HasFeeder has to be true before FeederObj will be re-enabled.
	 */
	public void enableFeeder() {
		if (hasFeeder) {
			if (feederObj == null) {
				makeFeederObj();
			} else {
				feederObj.setEnabled(true);
			}
			feederObj.setCktElementFeederFlags(true);
		}
	}

	private String makeDIFileName() {
		return DSSGlobals.energyMeterClass.getDI_Dir() + "/" + getName() + ".csv";
	}

	public double getRegister(int idx) {
		return registers[idx];
	}

	public double getTotalsMask(int idx) {
		return totalsMask[idx];
	}

	public String[] getRegisterNames() {
		return registerNames;
	}

	public void setRegisterNames(String[] names) {
		registerNames = names;
	}

	public CktTree getBranchList() {
		return branchList;
	}

	public void setBranchList(CktTree list) {
		branchList = list;
	}

	public double[] getRegisters() {
		return registers;
	}

	public void setRegisters(double[] values) {
		registers = values;
	}

	public double[] getDerivatives() {
		return derivatives;
	}

	public void setDerivatives(double[] deriv) {
		derivatives = deriv;
	}

	public double[] getTotalsMask() {
		return totalsMask;
	}

	public void setTotalsMask(double[] totals) {
		totalsMask = totals;
	}


	// FIXME Private members in OpenDSS

	public boolean isFirstSampleAfterReset() {
		return firstSampleAfterReset;
	}

	public void setFirstSampleAfterReset(boolean firstSample) {
		firstSampleAfterReset = firstSample;
	}

	public boolean isExcessFlag() {
		return excessFlag;
	}

	public void setExcessFlag(boolean excess) {
		excessFlag = excess;
	}

	public boolean zoneIsRadial() {
		return zoneIsRadial;
	}

	public void setZoneIsRadial(boolean isRadial) {
		zoneIsRadial = isRadial;
	}

	public boolean isVoltageUEOnly() {
		return voltageUEOnly;
	}

	public void setVoltageUEOnly(boolean value) {
		voltageUEOnly = value;
	}

	public boolean isLocalOnly() {
		return localOnly;
	}

	public void setLocalOnly(boolean local) {
		localOnly = local;
	}

	public boolean hasFeeder() {
		return hasFeeder;
	}

	public void setHasFeeder(boolean value) {
		hasFeeder = value;
	}

	public boolean isLosses() {
		return losses;
	}

	public void setLosses(boolean value) {
		losses = value;
	}

	public boolean isLineLosses() {
		return lineLosses;
	}

	public void setLineLosses(boolean losses) {
		lineLosses = losses;
	}

	public boolean isXfmrLosses() {
		return xfmrLosses;
	}

	public void setXfmrLosses(boolean losses) {
		xfmrLosses = losses;
	}

	public boolean isSeqLosses() {
		return seqLosses;
	}

	public void setSeqLosses(boolean losses) {
		seqLosses = losses;
	}

	public boolean is3PhaseLosses() {
		return threePhaseLosses;
	}

	public void set3PhaseLosses(boolean losses) {
		threePhaseLosses = losses;
	}

	public boolean isVBaseLosses() {
		return VBaseLosses;
	}

	public void setVBaseLosses(boolean losses) {
		VBaseLosses = losses;
	}

	public boolean isPhaseVoltageReport() {
		return phaseVoltageReport;
	}

	public void setPhaseVoltageReport(boolean report) {
		phaseVoltageReport = report;
	}

	public FeederObj getFeederObj() {
		return feederObj;
	}

	public void setFeederObj(FeederObj feeder) {
		feederObj = feeder;
	}

	public String[] getDefinedZoneList() {
		return definedZoneList;
	}

	public void setDefinedZoneList(String[] list) {
		definedZoneList = list;
	}

	public int getDefinedZoneListSize() {
		return definedZoneListSize;
	}

	public void setDefinedZoneListSize(int size) {
		definedZoneListSize = size;
	}

	public double getMaxZoneKVANorm() {
		return maxZoneKVANorm;
	}

	public void setMaxZoneKVANorm(double max) {
		maxZoneKVANorm = max;
	}

	public double getMaxZoneKVAEmerg() {
		return maxZoneKVAEmerg;
	}

	public void setMaxZoneKVAEmerg(double max) {
		maxZoneKVAEmerg = max;
	}

	public double[] getVBaseTotalLosses() {
		return VBaseTotalLosses;
	}

	public void setVBaseTotalLosses(double[] losses) {
		VBaseTotalLosses = losses;
	}

	public double[] getVBaseLineLosses() {
		return VBaseLineLosses;
	}

	public void setVBaseLineLosses(double[] losses) {
		VBaseLineLosses = losses;
	}

	public double[] getVBaseLoadLosses() {
		return VBaseLoadLosses;
	}

	public void setVBaseLoadLosses(double[] losses) {
		VBaseLoadLosses = losses;
	}

	public double[] getVBaseNoLoadLosses() {
		return VBaseNoLoadLosses;
	}

	public void setVBaseNoLoadLosses(double[] losses) {
		VBaseNoLoadLosses = losses;
	}

	public double[] getVBaseLoad() {
		return VBaseLoad;
	}

	public void setVBaseLoad(double[] load) {
		VBaseLoad = load;
	}

	public double[] getVBaseList() {
		return VBaseList;
	}

	public void setVBaseList(double[] list) {
		VBaseList = list;
	}

	public int getVBaseCount() {
		return VBaseCount;
	}

	public void setVBaseCount(int count) {
		VBaseCount = count;
	}

	public int getMaxVBaseCount() {
		return maxVBaseCount;
	}

	public void setMaxVBaseCount(int count) {
		maxVBaseCount = count;
	}

	public double[] getVPhaseMax() {
		return VPhaseMax;
	}

	public void setVPhaseMax(double[] max) {
		VPhaseMax = max;
	}

	public double[] getVPhaseMin() {
		return VPhaseMin;
	}

	public void setVPhaseMin(double[] min) {
		VPhaseMin = min;
	}

	public double[] getVPhaseAccum() {
		return VPhaseAccum;
	}

	public void setVPhaseAccum(double[] accum) {
		VPhaseAccum = accum;
	}

	public int[] getVPhaseAccumCount() {
		return VPhaseAccumCount;
	}

	public void setVPhaseAccumCount(int[] count) {
		VPhaseAccumCount = count;
	}

	public FileWriter getVPhaseFile() {
		return VPhaseFile;
	}

	public void setVPhaseFile(FileWriter file) {
		VPhaseFile = file;
	}

	public boolean isVPhaseReportFileOpen() {
		return VPhaseReportFileIsOpen;
	}

	public void setVPhaseReportFileOpen(boolean isOpen) {
		VPhaseReportFileIsOpen = isOpen;
	}

	public FileWriter getDIFile() {
		return DI_File;
	}

	public void setDIFile(FileWriter file) {
		DI_File = file;
	}

	public boolean isThisMeterDIFileOpen() {
		return thisMeterDI_FileIsOpen;
	}

	public void setThisMeterDIFileOpen(boolean isOpen) {
		thisMeterDI_FileIsOpen = isOpen;
	}

}
