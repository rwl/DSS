package com.epri.dss.meter.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.epri.dss.common.Bus;
import com.epri.dss.common.Circuit;
import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.conversion.Generator;
import com.epri.dss.conversion.PCElement;
import com.epri.dss.delivery.PDElement;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.meter.SystemMeter;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CktTreeImpl;
import com.epri.dss.shared.impl.CommandListImpl;

public class EnergyMeterImpl extends MeterClassImpl implements EnergyMeter {

	// adjacency lists for PC and PD elements at each bus, built for faster searches
	public static List<PCElement>[] busAdjPC;  // also includes shunt PD elements
	public static List<PDElement>[] busAdjPD;

	public static EnergyMeterObj activeEnergyMeterObj;

	private Generator generatorClass;
	private boolean saveDemandInterval;
	private boolean DI_Verbose;
	private FileWriter overloadFile;
	private FileWriter voltageFile;

	protected double[] DI_RegisterTotals;
	protected String DI_Dir;
	protected FileWriter DI_Totals;
	protected FileWriter meterTotals;
	protected SystemMeter systemMeter;
	protected boolean doOverloadReport;
	protected boolean doVoltageExceptionReport;
	protected boolean overloadFileIsOpen;
	protected boolean voltageFileIsOpen;

	public EnergyMeterImpl() {
		super();
		className = "EnergyMeter";
		classType = classType + DSSClassDefs.ENERGY_METER;

		activeElement = -1;

		/* Initialise demand interval options to off */
		saveDemandInterval = false;
		DI_Verbose         = false;
		doOverloadReport  = false;  // saveDemandInterval must be true for this to have an effect
		overloadFileIsOpen = false;
		voltageFileIsOpen  = false;

		DI_Dir = "";

		defineProperties();

		String[] commands = new String[numProperties];
		System.arraycopy(propertyName, 0, commands, 0, numProperties);
		commandList = new CommandListImpl(commands);
		commandList.setAbbrevAllowed(true);

		generatorClass = (Generator) DSSGlobals.DSSClassList.get(DSSGlobals.classNames.find("generator"));

		systemMeter = new SystemMeterImpl();
	}

	protected void defineProperties() {

		numProperties = EnergyMeter.NumPropsThisClass;
		countProperties();   // get inherited property count
		allocatePropertyArrays();

		// define property names
		propertyName[0] = "element";
		propertyName[1] = "terminal";
		propertyName[2] = "action";
		propertyName[3] = "option";
		propertyName[4] = "kVAnormal";
		propertyName[5] = "kVAemerg";
		propertyName[6] = "peakcurrent";
		propertyName[7] = "Zonelist";
		propertyName[8] = "LocalOnly";
		propertyName[9] = "Mask";
		propertyName[10] = "Losses";
		propertyName[11] = "LineLosses";
		propertyName[12] = "XfmrLosses";
		propertyName[13] = "SeqLosses";
		propertyName[14] = "3phaseLosses";
		propertyName[15] = "VbaseLosses";  // segregate losses by voltage base
		propertyName[16] = "PhaseVoltageReport";  // compute avg phase voltages in zone

		/*PropertyName[10] = "Feeder";  **** removed - not used*/

		propertyHelp[0] = "Name (Full Object name) of element to which the monitor is connected.";
		propertyHelp[1] = "Number of the terminal of the circuit element to which the monitor is connected. "+
				"1 or 2, typically.";
		propertyHelp[2] = "{Clear (reset) | Save | Take | Zonedump | Allocate | Reduce} " + DSSGlobals.CRLF + DSSGlobals.CRLF +
				"(A)llocate = Allocate loads on the meter zone to match PeakCurrent." + DSSGlobals.CRLF +
				"(C)lear = reset all registers to zero" + DSSGlobals.CRLF +
				"(R)educe = reduces zone by merging lines (see Set Keeplist & ReduceOption)" + DSSGlobals.CRLF +
				"(S)ave = saves the current register values to a file." + DSSGlobals.CRLF +
				"   File name is \"MTR_metername.csv\"." +DSSGlobals.CRLF +
				"(T)ake = Takes a sample at present solution" + DSSGlobals.CRLF +
				"(Z)onedump = Dump names of elements in meter zone to a file" + DSSGlobals.CRLF +
				"   File name is \"Zone_metername.csv\".";
		propertyHelp[3] = "Enter a string ARRAY of any combination of the following. Options processed left-to-right:" + DSSGlobals.CRLF + DSSGlobals.CRLF +
				"(E)xcess : (default) UE/EEN is estimate of energy over capacity " + DSSGlobals.CRLF +
				"(T)otal : UE/EEN is total energy after capacity exceeded"+ DSSGlobals.CRLF +
				"(R)adial : (default) Treats zone as a radial circuit"+ DSSGlobals.CRLF +
				"(M)esh : Treats zone as meshed network (not radial)." +DSSGlobals.CRLF+
				"(C)ombined : (default) Load UE/EEN computed from combination of overload and undervoltage."+ DSSGlobals.CRLF +
				"(V)oltage : Load UE/EEN computed based on voltage only."+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Example: option=(E, R)";
		propertyHelp[4] = "Upper limit on kVA load in the zone, Normal configuration. Default is 0.0 (ignored). " +
				"Overrides limits on individual lines for overload EEN. " +
				"With \"LocalOnly=Yes\" option, uses only load in metered branch.";
		propertyHelp[5] = "Upper limit on kVA load in the zone, Emergency configuration. Default is 0.0 (ignored). " +
				"Overrides limits on individual lines for overload UE. " +
				"With \"LocalOnly=Yes\" option, uses only load in metered branch.";
		propertyHelp[6] = "ARRAY of current magnitudes representing the peak currents measured at this location " +
				"for the load allocation function.  Default is (400, 400, 400). Enter one current for each phase";
		propertyHelp[7] = "ARRAY of full element names for this meter's zone.  Default is for meter to find it's own zone. " +
				"If specified, DSS uses this list instead.  Can access the names in a single-column text file.  Examples: " + DSSGlobals.CRLF + DSSGlobals.CRLF+
				"zonelist=[line.L1, transformer.T1, Line.L3] " + DSSGlobals.CRLF +
				"zonelist=(file=branchlist.txt)";
		propertyHelp[8] = "{Yes | No}  Default is NO.  If Yes, meter considers only the monitored element " +
				"for EEN and UE calcs.  Uses whole zone for losses.";
		propertyHelp[9] = "Mask for adding registers whenever all meters are totalized.  Array of floating point numbers " +
				"representing the multiplier to be used for summing each register from this meter. " +
				"Default = (1, 1, 1, 1, ... ).  You only have to enter as many as are changed (positional). " +
				"Useful when two meters monitor same energy, etc.";
		propertyHelp[10]= "{Yes | No}  Default is YES. Compute Zone losses. If NO, then no losses at all are computed.";
		propertyHelp[11]= "{Yes | No}  Default is YES. Compute Line losses. If NO, then none of the losses are computed.";
		propertyHelp[12]= "{Yes | No}  Default is YES. Compute Transformer losses. If NO, transformers are ignored in loss calculations.";
		propertyHelp[13]= "{Yes | No}  Default is YES. Compute Sequence losses in lines and segregate by line mode losses and zero mode losses.";
		propertyHelp[14]= "{Yes | No}  Default is YES. Compute Line losses and segregate by 3-phase and other (1- and 2-phase) line losses. ";
		propertyHelp[15]= "{Yes | No}  Default is YES. Compute losses and segregate by voltage base. If NO, then voltage-based tabulation is not reported.";
		propertyHelp[16]= "{Yes | No}  Default is NO.  Report min, max, and average phase voltages for the zone and tabulate by voltage base. " +
				"Demand Intervals must be turned on (Set Demand=true) and voltage bases must be defined for this property to take effect. "+
				"Result is in a separate report file.";

		/*PropertyHelp[10] = "{Yes/True | No/False}  Default is NO. If set to Yes, a Feeder object is created corresponding to " +
				"the energymeter.  Feeder is enabled if Radial=Yes; diabled if Radial=No.  Feeder is " +
				"synched automatically with the meter zone.  Do not create feeders for zones in meshed transmission systems.";*/

		activeProperty = EnergyMeter.NumPropsThisClass - 1;
		super.defineProperties();  // add defs of inherited properties to bottom of list
	}

	public int newObject(String objName) {

		DSSGlobals.activeCircuit.setActiveCktElement(new EnergyMeterObjImpl(this, objName));
		return addObjectToList(DSSGlobals.activeDSSObject);
	}

	public int edit() {
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		activeEnergyMeterObj = (EnergyMeterObj) elementList.getActive();
		DSSGlobals.activeCircuit.setActiveCktElement(activeEnergyMeterObj);

		int result = 0;

		boolean doRecalc = false;

		EnergyMeterObj aem = activeEnergyMeterObj;

		aem.setMeteredElementChanged(false);

		int paramPointer = -1;
		String paramName = parser.getNextParam();
		String param = parser.makeString();
		while (param.length() > 0) {
			if (paramName.length() == 0) {
				paramPointer += 1;
			} else {
				paramPointer = commandList.getCommand(paramName);
			}

			if (paramPointer >= 0 && paramPointer < numProperties)
				aem.setPropertyValue(paramPointer, param);

			switch (paramPointer) {
			case -1:
				DSSGlobals.doSimpleMsg("Unknown parameter \"" + paramName + "\" for object \"" + getName() +"."+ aem.getName() + "\"", 520);
				break;
			case 0:
				aem.setElementName(param.toLowerCase());
				break;
			case 1:
				aem.setMeteredTerminal(parser.makeInteger());
				break;
			case 2:  /* Actions */
				switch (param.toLowerCase().charAt(0)) {
				case 'a':
					aem.allocateLoad();
					break;
				case 'c':
					aem.resetRegisters();
					break;
				case 'r':
					aem.reduceZone();
					break;
				case 's':
					aem.saveRegisters();
					break;
				case 't':
					aem.takeSample();
					break;
				case 'z':
					aem.zoneDump();
					break;
				}
				break;
			case 3:
				processOptions(param);
				break;
			case 4:
				aem.setMaxZoneKVANorm(parser.makeDouble());
				break;
			case 5:
				aem.setMaxZoneKVAEmerg(parser.makeDouble());
				break;
			case 6:
				parser.parseAsVector(aem.getNPhases(), aem.getSensorCurrent());  // inits to zero
				break;
			case 7:
				Utilities.interpretAndAllocStrArray(param, aem.getDefinedZoneListSize(), aem.getDefinedZoneList());
				break;
			case 8:
				aem.setLocalOnly(Utilities.interpretYesNo(param));
				break;
			case 9:
				interpretRegisterMaskArray(aem.getTotalsMask());
				break;
			case 10:
				aem.setLosses(Utilities.interpretYesNo(param));
				break;
			case 11:
				aem.setLineLosses(Utilities.interpretYesNo(param));
				break;
			case 12:
				aem.setXfmrLosses(Utilities.interpretYesNo(param));
				break;
			case 13:
				aem.setSeqLosses(Utilities.interpretYesNo(param));
				break;
			case 14:
				aem.set3PhaseLosses(Utilities.interpretYesNo(param));
				break;
			case 15:
				aem.setVBaseLosses(Utilities.interpretYesNo(param));
				break;
			case 16:
				aem.setPhaseVoltageReport(Utilities.interpretYesNo(param));
				break;
			/* case 10: aem.setHasFeeder(Utilities.interpretYesNo(Param)); break;*/
			default:
				classEdit(activeEnergyMeterObj, paramPointer - EnergyMeter.NumPropsThisClass);
				break;
			}

			switch (paramPointer) {
			case 0:
				aem.setMeteredElementChanged(true);
				doRecalc = true;
				break;
			case 1:
				aem.setMeteredElementChanged(true);
				doRecalc = true;
				break;
			case 10:
				if (aem.hasFeeder()) {
					doRecalc = true;
				} else {
					aem.removeFeederObj();
				}
				break;
			}

			paramName = parser.getNextParam();
			param = parser.makeString();
		}

		if (doRecalc)
			aem.recalcElementData();  // when some basic data have changed

		return result;
	}

	protected int makeLike(String energyMeterName) {
		EnergyMeterObj aem;
		int result = 0;

		/* See if we can find this EnergyMeter name in the present collection */
		EnergyMeterObj otherEnergyMeter = (EnergyMeterObj) find(energyMeterName);
		if (otherEnergyMeter != null) {

			aem = activeEnergyMeterObj;

			aem.setNPhases(otherEnergyMeter.getNPhases());
			aem.setNConds(otherEnergyMeter.getNConds());  // force reallocation of terminal stuff

			aem.setElementName(otherEnergyMeter.getElementName());
			aem.setMeteredElement(otherEnergyMeter.getMeteredElement());  // pointer to target circuit element
			aem.setMeteredTerminal(otherEnergyMeter.getMeteredTerminal());
			aem.setExcessFlag(otherEnergyMeter.isExcessFlag());

			aem.setMaxZoneKVANorm(otherEnergyMeter.getMaxZoneKVANorm());
			aem.setMaxZoneKVAEmerg(otherEnergyMeter.getMaxZoneKVAEmerg());

			aem.setDefinedZoneListSize(otherEnergyMeter.getDefinedZoneListSize());
			aem.setDefinedZoneList(new String[aem.getDefinedZoneListSize()]);
			// copy strings over (actually incr ref count on string)
			for (int i = 0; i < aem.getDefinedZoneListSize(); i++)
				aem.getDefinedZoneList()[i] = otherEnergyMeter.getDefinedZoneList()[i];

			aem.setLocalOnly(otherEnergyMeter.isLocalOnly());
			aem.setVoltageUEOnly(otherEnergyMeter.isVoltageUEOnly());

			/* Boolean flags */
			aem.setLosses(otherEnergyMeter.isLosses());
			aem.setLineLosses(otherEnergyMeter.isLineLosses());
			aem.setXfmrLosses(otherEnergyMeter.isXfmrLosses());
			aem.setSeqLosses(otherEnergyMeter.isSeqLosses());
			aem.set3PhaseLosses(otherEnergyMeter.is3PhaseLosses());
			aem.setVBaseLosses(otherEnergyMeter.isVBaseLosses());
			aem.setPhaseVoltageReport(otherEnergyMeter.isPhaseVoltageReport());

			for (int i = 0; i < aem.getParentClass().getNumProperties(); i++)
				aem.setPropertyValue(i, otherEnergyMeter.getPropertyValue(i));
		} else {
			DSSGlobals.doSimpleMsg("Error in EnergyMeter makeLike: \"" + energyMeterName + "\" not found.", 521);
		}

		return result;
	}

	public int init(int handle) {
		DSSGlobals.doSimpleMsg("Need to implement EnergyMeter.init", -1);
		return 0;
	}

	public void resetMeterZonesAll() {
		int i;

		Circuit ckt = DSSGlobals.activeCircuit;

		if (ckt.getEnergyMeters().size() == 0)
			return;

		// initialize the checked flag for all circuit elements.
		for (CktElement pCktElement : ckt.getCktElements()) {
			pCktElement.setChecked(false);
			pCktElement.setIsolated(true);
			for (i = 0; i < pCktElement.getNTerms(); i++)
				pCktElement.getTerminal(i).setChecked(false);
		}

		/* Clear some things that will be set by the meter zone */
		for (PDElement PDElem : ckt.getPDElements()) {
			PDElem.setMeterObj(null);
			PDElem.setSensorObj(null);
			PDElem.setParentPDElement(null);
		}

		for (PCElement PCElem : ckt.getPCElements()) {
			PCElem.setMeterObj(null);
			PCElem.setSensorObj(null);
		}

		// set up the bus adjacency lists for faster searches to build meter zone lists
		CktTreeImpl.buildActiveBusAdjacencyLists(busAdjPD, busAdjPC);

		/* Set hasMeter flag for all cktElements */
		setHasMeterFlag();
		DSSGlobals.sensorClass.setHasSensorFlag();  // set all sensor branch flags, too.

		// initialise the checked flag for all buses
		for (i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBus(i).setBusChecked(false);

		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			if (pMeter.isEnabled())
				pMeter.makeMeterZoneLists();

		CktTreeImpl.freeAndNilBusAdjacencyLists(busAdjPD, busAdjPC);
	}

	/**
	 * Reset all meters in active circuit to zero.
	 */
	@Override
	public void resetAll() {
		Circuit ckt = DSSGlobals.activeCircuit;

		if (DSSGlobals.DIFilesAreOpen)
			closeAllDIFiles();

		if (saveDemandInterval) {

			/* Make directories to save data */
			File dir = new File(ckt.getCaseName());
			if (!dir.exists()) {
				try {
					dir.mkdir();
				} catch (Exception e) {
					DSSGlobals.doSimpleMsg("Error making directory: \""+ckt.getCaseName()+"\". " + e.getMessage(), 522);
				}
			}
			DI_Dir = ckt.getCaseName() + "/DI_yr_" + String.valueOf(ckt.getSolution().getYear()).trim();
			dir = new File(DI_Dir);
			if (!dir.exists()) {
				try {
					dir.mkdir();
				} catch (Exception e) {
					DSSGlobals.doSimpleMsg("Error making demand interval directory: \""+DI_Dir+"\". " + e.getMessage(), 523);
				}
			}

			createDI_Totals();
			try {
				DI_Totals.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}

		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			pMeter.resetRegisters();

		systemMeter.reset();

		// reset generator objects, too
		generatorClass.resetRegistersAll();
		DSSGlobals.storageClass.resetRegistersAll();
	}

	/**
	 * Force all meters in active circuit to sample.
	 */
	@Override
	public void sampleAll() {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (EnergyMeterObj meter : ckt.getEnergyMeters())
			if (meter.isEnabled())
				meter.takeSample();

		systemMeter.takeSample();

		if (saveDemandInterval) {  /* Write totals demand interval file */
			PrintWriter DI_TotalsPrinter = new PrintWriter(DI_Totals);

			DI_TotalsPrinter.printf("%-.6g ", ckt.getSolution().getDblHour());
			for (int i = 0; i < NUM_EM_REGISTERS; i++)
				DI_TotalsPrinter.printf(", %-.6g", DI_RegisterTotals[i]);
			DI_TotalsPrinter.println();
			DI_TotalsPrinter.close();

			clearDI_Totals();
			if (overloadFileIsOpen) writeOverloadReport();
			if (voltageFileIsOpen) writeVoltageReport();
		}

		// sample generator and storage objects, too
		generatorClass.sampleAll();
		DSSGlobals.storageClass.sampleAll();  // samples energymeter part of storage elements (not update)
	}

	/**
	 * Force all EnergyMeters in the circuit to take a sample.
	 */
	@Override
	public void saveAll() {
		Circuit ckt = DSSGlobals.activeCircuit;

		for (EnergyMeterObj meter : ckt.getEnergyMeters())
			if (meter.isEnabled())
				meter.saveRegisters();

		systemMeter.save();
	}

	/**
	 * Set the hasMeter flag for all cktElement;
	 */
	protected void setHasMeterFlag() {
		int i;
		EnergyMeterObj thisMeter;
		DSSCktElement cktElem;

		Circuit ckt = DSSGlobals.activeCircuit;

		/* Initialize all to false */
		for (i = 0; i < ckt.getPDElements().size(); i++) {
			cktElem = (DSSCktElement) ckt.getPDElements().get(i);
			cktElem.setHasEnergyMeter(false);
		}

		for (i = 0; i < ckt.getEnergyMeters().size(); i++) {
			thisMeter = ckt.getEnergyMeters().get(i);
			if (thisMeter.getMeteredElement() != null)
				thisMeter.getMeteredElement().setHasEnergyMeter(true);
		}
	}

	private void processOptions(String opts) {
		String s2 = " ";

		DSSGlobals.auxParser.setCmdString(opts);  // load up aux parser

		EnergyMeterObj aem = activeEnergyMeterObj;

		/* Loop until no more options found */
		while (s2.length() > 0) {
			DSSGlobals.auxParser.getNextParam();  // ignore any parameter name not expecting any
			s2 = DSSGlobals.auxParser.makeString().toLowerCase();
			if (s2.length() > 0)
				switch (s2.charAt(0)) {
				case 'e':
					aem.setExcessFlag(true);
					break;
				case 't':
					aem.setExcessFlag(false);
					break;
				case 'r':
					aem.setZoneIsRadial(true);
					break;
				case 'm':
					aem.setZoneIsRadial(false);
					break;
				case 'c':
					aem.setVoltageUEOnly(false);
					break;
				case 'v':
					aem.setVoltageUEOnly(true);
					break;
				}
		}
	}

	public void closeAllDIFiles() {

		if (saveDemandInterval) {
			/* While closing DI files, write all meter registers to one file */
			try {
				createMeterTotals();
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error on rewrite of totals file: "+e.getMessage(), 536);
			}

			/* Close all the DI file for each meter */
			for (EnergyMeterObj meter : DSSGlobals.activeCircuit.getEnergyMeters())
				if (meter.isEnabled())
					meter.closeDemandIntervalFile();

			writeTotalsFile();  // sum all EnergyMeter registers to "Totals.csv"
			systemMeter.closeDemandIntervalFile();
			systemMeter.save();
			try {
				meterTotals.close();
				DI_Totals.close();
				DSSGlobals.DIFilesAreOpen = false;
				if (overloadFileIsOpen) {
					overloadFile.close();
					overloadFileIsOpen = false;
				}
				if (voltageFileIsOpen) {
					voltageFile.close();
					voltageFileIsOpen = false;
				}
			} catch (IOException e) {
				DSSGlobals.doSimpleMsg("Error closing file: "+e.getMessage(), 537);
			}
		}
	}

	public void appendAllDIFiles() {

		if (saveDemandInterval) {

			clearDI_Totals();  // clears accumulator arrays

			for (EnergyMeterObj meter : DSSGlobals.activeCircuit.getEnergyMeters())
				if (meter.isEnabled())
					meter.appendDemandIntervalFile();

			systemMeter.appendDemandIntervalFile();

			/* Open DI_Totals */
			try {
				File DI_TotalsFile = new File(DI_Dir + "/DI_Totals.csv");
				/* File Must Exist */
				if (DI_TotalsFile.exists()) {
					DI_Totals = new FileWriter(DI_TotalsFile, true);
				} else {
					createDI_Totals();
				}
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error opening demand interval file \""+getName()+".csv" + " for appending."+DSSGlobals.CRLF+e.getMessage(), 538);
			}

			DSSGlobals.DIFilesAreOpen = true;
		}
	}

	public void setSaveDemandInterval(boolean value) {
		saveDemandInterval = value;
		resetAll();
	}

	/**
	 * Scans the active circuit for overloaded PD elements and writes each to a file.
	 * This is called only if in Demand Interval (DI) mode and the file is open.
	 */
	private void writeOverloadReport() {
		double CMax;

		Circuit ckt = DSSGlobals.activeCircuit;

		PrintWriter pw = new PrintWriter(overloadFile);

		/* Check PDElements only */
		for (PDElement PDElem : ckt.getPDElements()) {
			if (PDElem.isEnabled() && !PDElem.isShunt()) {  // ignore shunts

				if (PDElem.getNormAmps() > 0.0 || PDElem.getEmergAmps() > 0.0) {
					PDElem.computeITerminal();
					CMax = PDElem.maxTerminalOneIMag();  // for now, check only terminal 1 for overloads
					if (CMax > PDElem.getNormAmps() || CMax > PDElem.getEmergAmps()) {
						pw.printf("%-.6g,", ckt.getSolution().getDblHour());
						pw.printf(" %s, %-.4g, %-.4g,", Utilities.fullName(PDElem), PDElem.getNormAmps(), PDElem.getEmergAmps());
						if (PDElem.getNormAmps() > 0.0) {
							pw.printf(" %-.7g,", CMax / PDElem.getNormAmps() * 100.0);
						} else {
							pw.print(" 0.0,");
						}
						if (PDElem.getEmergAmps() > 0.0) {
							pw.printf(" %-.7g,", CMax / PDElem.getEmergAmps() * 100.0);
						} else {
							pw.print(" 0.0,");
						}
						/* Find bus of first terminal */
						pw.printf(" %-.3g ", ckt.getBus(ckt.getMapNodeToBus()[ PDElem.getNodeRef()[0] ].busRef).getKVBase());

						pw.println();
					}
				}
			}
		}

		pw.close();
	}

	private void clearDI_Totals() {
		for (int i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			DI_RegisterTotals[i] = 0.0;
	}

	private void createDI_Totals() {
		EnergyMeterObj meter;

		try {
			DI_Totals = new FileWriter(DI_Dir + "/DI_Totals.csv");
//			FileWriter DI_TotalsStream = new FileWriter(DI_Totals, false);
			BufferedWriter DI_TotalsBuffer = new BufferedWriter(DI_Totals);

			DI_TotalsBuffer.write("Time");
			for (int i = 0; i < DSSGlobals.activeCircuit.getEnergyMeters().size(); i++) {
				meter = DSSGlobals.activeCircuit.getEnergyMeters().get(i);
				DI_TotalsBuffer.write(", \"" + meter.getRegisterNames()[i] + "\"");
			}
			DI_TotalsBuffer.newLine();
			DI_TotalsBuffer.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error creating: \""+DI_Dir+"/DI_Totals.csv\": "+e.getMessage(), 539);
		}
	}

	private void createMeterTotals() {
		int i;
		EnergyMeterObj mtr;

		Circuit ckt = DSSGlobals.activeCircuit;

		try {
			File meterTotalsFile = new File(DI_Dir + "/EnergyMeterTotals.csv");
			meterTotals = new FileWriter(meterTotalsFile);
			BufferedWriter meterTotalsBuffer = new BufferedWriter(meterTotals);

			meterTotalsBuffer.write("Name");
			mtr = ckt.getEnergyMeters().get(0);
			if (mtr != null)
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					meterTotalsBuffer.write(", \"" + mtr.getRegisterNames()[i] + "\"");
			meterTotalsBuffer.newLine();

			meterTotalsBuffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDIVerbose(boolean value) {
		DI_Verbose = value;
		resetAll();
	}

	private void writeTotalsFile() {
		EnergyMeterObj mtr;
		double[] regSum = new double[EnergyMeter.NUM_EM_REGISTERS];
		int i, j;
		File f;

		/* Sum up all registers of all meters and write to Totals.csv */
		for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++) regSum[i] = 0.0;

		for (i = 0; i < DSSGlobals.activeCircuit.getEnergyMeters().size(); i++) {
			mtr = DSSGlobals.activeCircuit.getEnergyMeters().get(i);
			if (mtr.isEnabled())
				for (j = 0; j < EnergyMeter.NUM_EM_REGISTERS; j++)
					regSum[j] = regSum[j] + mtr.getRegisters()[j] * mtr.getTotalsMask()[j];
		}

		try {  // Write the file
			f = new File(DI_Dir + "/Totals.csv");

			FileWriter fw = new FileWriter(f, false);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("Year");
			mtr = DSSGlobals.activeCircuit.getEnergyMeters().get(0);
			if (mtr != null)
				for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
					bw.write(", \"" + mtr.getRegisterNames()[i] + "\"");
			bw.newLine();

			bw.write(DSSGlobals.activeCircuit.getSolution().getYear());
			for (i = 0; i < EnergyMeter.NUM_EM_REGISTERS; i++)
				bw.write(String.format(", %-g ", regSum[i]));
			bw.newLine();

			bw.close();
			fw.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file Totals.csv."+DSSGlobals.CRLF+e.getMessage(), 543);
		}
	}

	private void writeVoltageReport() {
		int i, j;
		double VMagPU;
		int underCount;
		int overCount;
		double overVMax;
		double underVMin;
		Bus bus;

		/* For any bus with a defined voltage base, test for > Vmax or < Vmin */

		overCount  = 0;
		underCount = 0;

		Circuit ckt = DSSGlobals.activeCircuit;

		overVMax   = ckt.getNormalMinVolts();
		underVMin  = ckt.getNormalMaxVolts();
		for (i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBus(i);
			if (bus.getKVBase() > 0.0) {
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					VMagPU = ckt.getSolution().getNodeV()[ bus.getRef(j) ].abs() / bus.getKVBase() * 0.001;
					if (VMagPU > 0.1) {  // ignore neutral buses
						underVMin = Math.min(underVMin, VMagPU);
						overVMax  = Math.max(overVMax,  VMagPU);
						if (VMagPU < ckt.getNormalMinVolts()) {
							underCount += 1;
							break;  /* next i */
						} else if (VMagPU > ckt.getNormalMaxVolts()) {
							overCount += 1;
							break;
						}
					}
				}
			}
		}  /* for i */

		PrintWriter VoltagePrinter = new PrintWriter(voltageFile);
		VoltagePrinter.printf("%-.6g,", ckt.getSolution().getDblHour());
		VoltagePrinter.printf(" %d, %-.6g, %d, %-.6g", underCount, underVMin, overCount, overVMax);
		VoltagePrinter.println();
		VoltagePrinter.close();
	}

	private void interpretRegisterMaskArray(double[] mask) {
		int n = Parser.getInstance().parseAsVector(NUM_EM_REGISTERS, mask);
		for (int i = n - 1; i < EnergyMeter.NUM_EM_REGISTERS; i++)
			mask[i] = 1.0;  // set the rest to 1
	}

	/**
	 * Similar to "append", by creates the files.
	 */
	public void openAllDIFiles() {

		if (saveDemandInterval) {

			clearDI_Totals();  // clears accumulator arrays

			for (EnergyMeterObj meter : DSSGlobals.activeCircuit.getEnergyMeters())
				if (meter.isEnabled())
					meter.openDemandIntervalFile();

			systemMeter.openDemandIntervalFile();

			/* Optional exception reporting */
			if (doOverloadReport)
				openOverloadReportFile();
			if (doVoltageExceptionReport)
				openVoltageReportFile();

			/* Open DI_Totals */
			try {
				createDI_Totals();  // TODO add throws exception
			} catch (Exception e) {
				DSSGlobals.doSimpleMsg("Error opening demand interval file \""+getName()+".csv" + " for appending."+DSSGlobals.CRLF+e.getMessage(), 538);
			}

			DSSGlobals.DIFilesAreOpen = true;
		}
	}

	private void openOverloadReportFile() {

		try {
			if (overloadFileIsOpen)
				overloadFile.close();

			overloadFile = new FileWriter(DSSGlobals.energyMeterClass.getDI_Dir() + "/DI_Overloads.csv");
			PrintWriter pw = new PrintWriter(overloadFile);

			overloadFileIsOpen = true;
			pw.print("\"Hour\", \"Element\", \"Normal Amps\", \"Emerg Amps\", \"% Normal\", \"% Emerg\", \"kVBase\"");
			pw.println();
			pw.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file \""+DSSGlobals.energyMeterClass.getDI_Dir()+"/DI_Overloads.csv\"  for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	private void openVoltageReportFile() {

		try {
			if (voltageFileIsOpen)
				voltageFile.close();

			voltageFile = new FileWriter(DSSGlobals.energyMeterClass.getDI_Dir()+"/DI_VoltExceptions.csv");

			voltageFileIsOpen = true;
			PrintWriter pw = new PrintWriter(voltageFile);
			pw.println("\"Hour\", \"Undervoltages\", \"Min Voltage\", \"Overvoltage\", \"Max Voltage\"");
			pw.close();
		} catch (Exception e) {
			DSSGlobals.doSimpleMsg("Error opening demand interval file \""+DSSGlobals.energyMeterClass.getDI_Dir()+"/DI_VoltExceptions.csv\" for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	public boolean isSaveDemandInterval() {
		return saveDemandInterval;
	}

	public boolean isDIVerbose() {
		return DI_Verbose;
	}

	public double[] getDI_RegisterTotals() {
		return DI_RegisterTotals;
	}

	public void setDI_RegisterTotals(double[] totals) {
		DI_RegisterTotals = totals;
	}

	public String getDI_Dir() {
		return DI_Dir;
	}

	public void setDI_Dir(String dir) {
		DI_Dir = dir;
	}

	public FileWriter getDI_Totals() {
		return DI_Totals;
	}

	public void setDI_Totals(FileWriter totals) {
		DI_Totals = totals;
	}

	public FileWriter getMeterTotals() {
		return meterTotals;
	}

	public void setMeterTotals(FileWriter totals) {
		meterTotals = totals;
	}

	public SystemMeter getSystemMeter() {
		return systemMeter;
	}

	public void setSystemMeter(SystemMeter meter) {
		systemMeter = meter;
	}

	public boolean isDo_OverloadReport() {
		return doOverloadReport;
	}

	public void setDoOverloadReport(boolean doReport) {
		doOverloadReport = doReport;
	}

	public boolean isDo_VoltageExceptionReport() {
		return doVoltageExceptionReport;
	}

	public void setDoVoltageExceptionReport(boolean doReport) {
		doVoltageExceptionReport = doReport;
	}

	public boolean isOverLoadFileIsOpen() {
		return overloadFileIsOpen;
	}

	public void setOverLoadFileIsOpen(boolean fileIsOpen) {
		overloadFileIsOpen = fileIsOpen;
	}

	public boolean isVoltageFileIsOpen() {
		return voltageFileIsOpen;
	}

	public void setVoltageFileIsOpen(boolean fileIsOpen) {
		voltageFileIsOpen = fileIsOpen;
	}

}
