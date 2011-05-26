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

	// Adjacency lists for PC and PD elements at each bus, built for faster searches
	public static List<PCElement>[] BusAdjPC; // Also includes shunt PD elements
	public static List<PDElement>[] BusAdjPD;

	private static EnergyMeterObj ActiveEnergyMeterObj;


	private Generator GeneratorClass;
	private boolean SaveDemandInterval;
	private boolean DI_Verbose;
	private FileWriter OverLoadFile;
	private FileWriter VoltageFile;

	protected double[] DI_RegisterTotals;
	protected String DI_Dir;
	protected FileWriter DI_Totals;
	protected FileWriter MeterTotals;
	protected SystemMeter SystemMeter;
	protected boolean Do_OverloadReport;
	protected boolean Do_VoltageExceptionReport;
	protected boolean OverLoadFileIsOpen;
	protected boolean VoltageFileIsOpen;

	public EnergyMeterImpl() {
		super();
		this.Class_Name = "EnergyMeter";
		this.DSSClassType = this.DSSClassType + DSSClassDefs.ENERGY_METER;

		this.ActiveElement = -1;

		/* Initialice demand interval options to off */
		this.SaveDemandInterval = false;
		this.DI_Verbose         = false;
		this.Do_OverloadReport   = false;  // FSaveDemandInterval must be true for this to have an effect
		this.OverLoadFileIsOpen  = false;
		this.VoltageFileIsOpen   = false;

		this.DI_Dir = "";

		defineProperties();

		String[] Commands = new String[0];
		System.arraycopy(this.PropertyName, 0, Commands, 0, this.NumProperties);
		this.CommandList = new CommandListImpl(Commands);
		this.CommandList.setAbbrevAllowed(true);

		DSSGlobals Globals = DSSGlobals.getInstance();
		this.GeneratorClass = (Generator) Globals.getDSSClassList().get(Globals.getClassNames().find("generator"));

		this.SystemMeter = new SystemMeterImpl();
	}

	protected void defineProperties() {

		NumProperties = EnergyMeter.NumPropsThisClass;
		countProperties();   // Get inherited property count
		allocatePropertyArrays();


		// Define property names
		PropertyName[0] = "element";
		PropertyName[1] = "terminal";
		PropertyName[2] = "action";
		PropertyName[3] = "option";
		PropertyName[4] = "kVAnormal";
		PropertyName[5] = "kVAemerg";
		PropertyName[6] = "peakcurrent";
		PropertyName[7] = "Zonelist";
		PropertyName[8] = "LocalOnly";
		PropertyName[9] = "Mask";
		PropertyName[10] = "Losses";
		PropertyName[11] = "LineLosses";
		PropertyName[12] = "XfmrLosses";
		PropertyName[13] = "SeqLosses";
		PropertyName[14] = "3phaseLosses";
		PropertyName[15] = "VbaseLosses"; // segregate losses by voltage base
		PropertyName[16] = "PhaseVoltageReport"; // Compute Avg phase voltages in zone

		/*PropertyName[10] = "Feeder";  **** removed - not used*/

		PropertyHelp[0] = "Name (Full Object name) of element to which the monitor is connected.";
		PropertyHelp[1] = "Number of the terminal of the circuit element to which the monitor is connected. "+
				"1 or 2, typically.";
		PropertyHelp[2] = "{Clear (reset) | Save | Take | Zonedump | Allocate | Reduce} " + DSSGlobals.CRLF + DSSGlobals.CRLF +
				"(A)llocate = Allocate loads on the meter zone to match PeakCurrent." + DSSGlobals.CRLF +
				"(C)lear = reset all registers to zero" + DSSGlobals.CRLF +
				"(R)educe = reduces zone by merging lines (see Set Keeplist & ReduceOption)" + DSSGlobals.CRLF +
				"(S)ave = saves the current register values to a file." + DSSGlobals.CRLF +
				"   File name is \"MTR_metername.csv\"." +DSSGlobals.CRLF +
				"(T)ake = Takes a sample at present solution" + DSSGlobals.CRLF +
				"(Z)onedump = Dump names of elements in meter zone to a file" + DSSGlobals.CRLF +
				"   File name is \"Zone_metername.csv\".";
		PropertyHelp[3] = "Enter a string ARRAY of any combination of the following. Options processed left-to-right:" + DSSGlobals.CRLF + DSSGlobals.CRLF +
				"(E)xcess : (default) UE/EEN is estimate of energy over capacity " + DSSGlobals.CRLF +
				"(T)otal : UE/EEN is total energy after capacity exceeded"+ DSSGlobals.CRLF +
				"(R)adial : (default) Treats zone as a radial circuit"+ DSSGlobals.CRLF +
				"(M)esh : Treats zone as meshed network (not radial)." +DSSGlobals.CRLF+
				"(C)ombined : (default) Load UE/EEN computed from combination of overload and undervoltage."+ DSSGlobals.CRLF +
				"(V)oltage : Load UE/EEN computed based on voltage only."+DSSGlobals.CRLF+DSSGlobals.CRLF+
				"Example: option=(E, R)";
		PropertyHelp[4] = "Upper limit on kVA load in the zone, Normal configuration. Default is 0.0 (ignored). " +
				"Overrides limits on individual lines for overload EEN. " +
				"With \"LocalOnly=Yes\" option, uses only load in metered branch.";
		PropertyHelp[5] = "Upper limit on kVA load in the zone, Emergency configuration. Default is 0.0 (ignored). " +
				"Overrides limits on individual lines for overload UE. " +
				"With \"LocalOnly=Yes\" option, uses only load in metered branch.";
		PropertyHelp[6] = "ARRAY of current magnitudes representing the peak currents measured at this location " +
				"for the load allocation function.  Default is (400, 400, 400). Enter one current for each phase";
		PropertyHelp[7] = "ARRAY of full element names for this meter's zone.  Default is for meter to find it's own zone. " +
				"If specified, DSS uses this list instead.  Can access the names in a single-column text file.  Examples: " + DSSGlobals.CRLF + DSSGlobals.CRLF+
				"zonelist=[line.L1, transformer.T1, Line.L3] " + DSSGlobals.CRLF +
				"zonelist=(file=branchlist.txt)";
		PropertyHelp[8] = "{Yes | No}  Default is NO.  If Yes, meter considers only the monitored element " +
				"for EEN and UE calcs.  Uses whole zone for losses.";
		PropertyHelp[9] = "Mask for adding registers whenever all meters are totalized.  Array of floating point numbers " +
				"representing the multiplier to be used for summing each register from this meter. " +
				"Default = (1, 1, 1, 1, ... ).  You only have to enter as many as are changed (positional). " +
				"Useful when two meters monitor same energy, etc.";
		PropertyHelp[10]= "{Yes | No}  Default is YES. Compute Zone losses. If NO, then no losses at all are computed.";
		PropertyHelp[11]= "{Yes | No}  Default is YES. Compute Line losses. If NO, then none of the losses are computed.";
		PropertyHelp[12]= "{Yes | No}  Default is YES. Compute Transformer losses. If NO, transformers are ignored in loss calculations.";
		PropertyHelp[13]= "{Yes | No}  Default is YES. Compute Sequence losses in lines and segregate by line mode losses and zero mode losses.";
		PropertyHelp[14]= "{Yes | No}  Default is YES. Compute Line losses and segregate by 3-phase and other (1- and 2-phase) line losses. ";
		PropertyHelp[15]= "{Yes | No}  Default is YES. Compute losses and segregate by voltage base. If NO, then voltage-based tabulation is not reported.";
		PropertyHelp[16]= "{Yes | No}  Default is NO.  Report min, max, and average phase voltages for the zone and tabulate by voltage base. " +
				"Demand Intervals must be turned on (Set Demand=true) and voltage bases must be defined for this property to take effect. "+
				"Result is in a separate report file.";

		/*PropertyHelp[10] = "{Yes/True | No/False}  Default is NO. If set to Yes, a Feeder object is created corresponding to " +
				"the energymeter.  Feeder is enabled if Radial=Yes; diabled if Radial=No.  Feeder is " +
				"synched automatically with the meter zone.  Do not create feeders for zones in meshed transmission systems.";*/

		ActiveProperty = EnergyMeter.NumPropsThisClass;
		super.defineProperties();  // Add defs of inherited properties to bottom of list
	}

	public int newObject(String ObjName) {
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getActiveCircuit().setActiveCktElement(new EnergyMeterObjImpl(this, ObjName));
		return addObjectToList(Globals.getActiveDSSObject());
	}

	public int edit() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Parser parser = Parser.getInstance();

		// continue parsing with contents of parser
		setActiveEnergyMeterObj((EnergyMeterObj) ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement(getActiveEnergyMeterObj());

		int Result = 0;

		boolean DoRecalc = false;

		EnergyMeterObj aem = getActiveEnergyMeterObj();

		aem.setMeteredElementChanged(false);

		int ParamPointer = 0;
		String ParamName = parser.getNextParam();
		String Param     = parser.makeString();
		while (Param.length() > 0) {
			if (ParamName.length() == 0) {
				ParamPointer += 1;
			} else {
				ParamPointer = CommandList.getCommand(ParamName);
			}

			if ((ParamPointer > 0) && (ParamPointer <= NumProperties))
				aem.setPropertyValue(ParamPointer, Param);

			switch (ParamPointer) {
			case -1:
				Globals.doSimpleMsg("Unknown parameter \"" + ParamName + "\" for Object \"" + Class_Name +"."+ aem.getName() + "\"", 520);
			case 0:
				aem.setElementName(Param.toLowerCase());
			case 1:
				aem.setMeteredTerminal(parser.makeInteger());
			case 2:  /* Actions */
				switch (Param.toLowerCase().charAt(0)) {
				case 'a':
					aem.allocateLoad();
				case 'c':
					aem.resetRegisters();
				case 'r':
					aem.reduceZone();
				case 's':
					aem.saveRegisters();
				case 't':
					aem.takeSample();
				case 'z':
					aem.zoneDump();
				}
			case 3:
				processOptions(Param);
			case 4:
				aem.setMaxZonekVA_Norm(parser.makeDouble());
			case 5:
				aem.setMaxZonekVA_Emerg(parser.makeDouble());
			case 6:
				parser.parseAsVector(aem.getNPhases(), aem.getSensorCurrent());   // Inits to zero
			case 7:
				Utilities.interpretAndAllocStrArray(Param, aem.getDefinedZoneListSize(), aem.getDefinedZoneList());
			case 8:
				aem.setLocalOnly(Utilities.interpretYesNo(Param));
			case 9:
				interpretRegisterMaskArray(aem.getTotalsMask());
			case 10:
				aem.setLosses(Utilities.interpretYesNo(Param));
			case 11:
				aem.setLineLosses(Utilities.interpretYesNo(Param));
			case 12:
				aem.setXfmrLosses(Utilities.interpretYesNo(Param));
			case 13:
				aem.setSeqLosses(Utilities.interpretYesNo(Param));
			case 14:
				aem.setThreePhaseLosses(Utilities.interpretYesNo(Param));
			case 15:
				aem.setVBaseLosses(Utilities.interpretYesNo(Param));
			case 16:
				aem.setPhaseVoltageReport(Utilities.interpretYesNo(Param));
			/* case 10: aem.setHasFeeder(Utilities.interpretYesNo(Param));*/
			default:
				classEdit(ActiveEnergyMeterObj, ParamPointer - EnergyMeter.NumPropsThisClass);
			}

			switch (ParamPointer) {
			case 0:
				aem.setMeteredElementChanged(true);
				DoRecalc = true;
			case 1:
				aem.setMeteredElementChanged(true);
				DoRecalc = true;
			case 10:
				if (aem.isHasFeeder()) {
					DoRecalc = true;
				} else {
					aem.removeFeederObj();
				}
			}

			ParamName = parser.getNextParam();
			Param = parser.makeString();
		}

		if (DoRecalc)
			aem.recalcElementData();  // When some basic data have changed

		return Result;
	}

	protected int makeLike(String EnergyMeterName) {
		int Result = 0;
		/* See IF we can find this EnergyMeter name in the present collection */
		EnergyMeterObj OtherEnergyMeter = (EnergyMeterObj) find(EnergyMeterName);
		if (OtherEnergyMeter != null) {

			EnergyMeterObj aem = getActiveEnergyMeterObj();

			aem.setNPhases(OtherEnergyMeter.getNPhases());
			aem.setNConds(OtherEnergyMeter.getNConds());  // Force reallocation of terminal stuff

			aem.setElementName(OtherEnergyMeter.getElementName());
			aem.setMeteredElement(OtherEnergyMeter.getMeteredElement());  // Pointer to target circuit element
			aem.setMeteredTerminal(OtherEnergyMeter.getMeteredTerminal());
			aem.setExcessFlag(OtherEnergyMeter.isExcessFlag());

			aem.setMaxZonekVA_Norm(OtherEnergyMeter.getMaxZonekVA_Norm());
			aem.setMaxZonekVA_Emerg(OtherEnergyMeter.getMaxZonekVA_Emerg());

			aem.setDefinedZoneListSize(OtherEnergyMeter.getDefinedZoneListSize());
			aem.setDefinedZoneList(new String[aem.getDefinedZoneListSize()]);
			// Copy strings over (actually incr ref count on string)
			for (int i = 0; i < aem.getDefinedZoneListSize(); i++)
				aem.getDefinedZoneList()[i] = OtherEnergyMeter.getDefinedZoneList()[i];

			aem.setLocalOnly(OtherEnergyMeter.isLocalOnly());
			aem.setVoltageUEOnly(OtherEnergyMeter.isVoltageUEOnly());

			/* Boolean Flags */
			aem.setLosses(OtherEnergyMeter.isLosses());
			aem.setLineLosses(OtherEnergyMeter.isLineLosses());
			aem.setXfmrLosses(OtherEnergyMeter.isXfmrLosses());
			aem.setSeqLosses(OtherEnergyMeter.isSeqLosses());
			aem.setThreePhaseLosses(OtherEnergyMeter.isThreePhaseLosses());
			aem.setVBaseLosses(OtherEnergyMeter.isVBaseLosses());
			aem.setPhaseVoltageReport(OtherEnergyMeter.isPhaseVoltageReport());

			for (int i = 0; i < aem.getParentClass().getNumProperties(); i++)
				aem.setPropertyValue(i, OtherEnergyMeter.getPropertyValue(i));
		} else {
			DSSGlobals.getInstance().doSimpleMsg("Error in EnergyMeter makeLike: \"" + EnergyMeterName + "\" Not Found.", 521);
		}

		return Result;
	}

	public int init(int Handle) {
		DSSGlobals.getInstance().doSimpleMsg("Need to implement EnergyMeter.init", -1);
		return 0;
	}

	public void resetMeterZonesAll() {
		int i;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		if (ckt.getEnergyMeters().size() == 0)
			return;

		// Initialize the checked flag for all circuit elements.
		for (CktElement pCktElement : ckt.getCktElements()) {
			pCktElement.setChecked(false);
			pCktElement.setIsIsolated(true);
			for (i = 0; i < pCktElement.getNTerms(); i++)
				pCktElement.getTerminals()[i].setChecked(false);
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

		// Set up the bus adjacency lists for faster searches to build meter zone lists.
		CktTreeImpl.buildActiveBusAdjacencyLists(BusAdjPD, BusAdjPC);

		/* Set HasMeter flag for all cktElements */
		setHasMeterFlag();
		DSSGlobals.getInstance().getSensorClass().setHasSensorFlag();  // Set all sensor branch flags, too.

		// Initialise the checked flag for all buses
		for (i = 0; i < ckt.getNumBuses(); i++)
			ckt.getBuses()[i].setBusChecked(false);

		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			if (pMeter.isEnabled())
				pMeter.makeMeterZoneLists();

		CktTreeImpl.freeAndNilBusAdjacencyLists(BusAdjPD, BusAdjPC);
	}

	/**
	 * Reset all meters in active circuit to zero.
	 */
	@Override
	public void resetAll() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		if (Globals.isDIFilesAreOpen())
			closeAllDIFiles();

		if (SaveDemandInterval) {

			/* Make directories to save data */
			File dir = new File(ckt.getCaseName());
			if (!dir.exists()) {
				try {
					dir.mkdir();
				} catch (Exception e) {
					Globals.doSimpleMsg("Error making directory: \""+ckt.getCaseName()+"\". " + e.getMessage(), 522);
				}
			}
			DI_Dir = ckt.getCaseName() + "/DI_yr_" + String.valueOf(ckt.getSolution().getYear()).trim();
			dir = new File(DI_Dir);
			if (!dir.exists()) {
				try {
					dir.mkdir();
				} catch (Exception e) {
					Globals.doSimpleMsg("Error making Demand Interval Directory: \""+DI_Dir+"\". " + e.getMessage(), 523);
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

		SystemMeter.reset();

		// Reset generator objects, too
		GeneratorClass.resetRegistersAll();
		Globals.getStorageClass().resetRegistersAll();
	}

	/**
	 * Force all meters in active circuit to sample.
	 */
	@Override
	public void sampleAll() {
		DSSGlobals Globals = DSSGlobals.getInstance();
		Circuit ckt = Globals.getActiveCircuit();

		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			if (pMeter.isEnabled())
				pMeter.takeSample();

		SystemMeter.takeSample();

		if (SaveDemandInterval) {  /* Write totals demand interval file */
			PrintWriter DI_TotalsPrinter = new PrintWriter(DI_Totals);

			DI_TotalsPrinter.printf("%-.6g ", ckt.getSolution().getDblHour());
			for (int i = 0; i < NumEMRegisters; i++)
				DI_TotalsPrinter.printf(", %-.6g", DI_RegisterTotals[i]);
			DI_TotalsPrinter.println();
			DI_TotalsPrinter.close();

			clearDI_Totals();
			if (OverLoadFileIsOpen) writeOverloadReport();
			if (VoltageFileIsOpen) writeVoltageReport();
		}

		// Sample generator and storage objects, too
		GeneratorClass.sampleAll();
		Globals.getStorageClass().sampleAll();  // samples energymeter part of storage elements (not update)
	}

	/**
	 * Force all EnergyMeters in the circuit to take a sample.
	 */
	@Override
	public void saveAll() {
		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		for (EnergyMeterObj pMeter : ckt.getEnergyMeters())
			if (pMeter.isEnabled())
				pMeter.saveRegisters();

		SystemMeter.save();
	}

	/**
	 * Set the HasMeter flag for all cktElement;
	 */
	protected void setHasMeterFlag() {
		int i;
		EnergyMeterObj ThisMeter;
		DSSCktElement CktElem;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		/* Initialize all to false */
		for (i = 0; i < ckt.getPDElements().size(); i++) {
			CktElem = (DSSCktElement) ckt.getPDElements().get(i);
			CktElem.setHasEnergyMeter(false);
		}

		for (i = 0; i < ckt.getEnergyMeters().size(); i++) {
			ThisMeter = ckt.getEnergyMeters().get(i);
			if (ThisMeter.getMeteredElement() != null)
				ThisMeter.getMeteredElement().setHasEnergyMeter(true);
		}
	}

	private void processOptions(String Opts) {
		String S2 = " ";
		DSSGlobals Globals = DSSGlobals.getInstance();

		Globals.getAuxParser().setCmdString(Opts);  // Load up aux parser

		EnergyMeterObj aem = getActiveEnergyMeterObj();

		/* Loop until no more options found */
		while (S2.length() > 0) {
			Globals.getAuxParser().getNextParam();  // ignore any parameter name not expecting any
			S2 = Globals.getAuxParser().makeString().toLowerCase();
			if (S2.length() > 0)
				switch (S2.charAt(0)) {
				case 'e':
					aem.setExcessFlag(true);
				case 't':
					aem.setExcessFlag(false);
				case 'r':
					aem.setZoneIsRadial(true);
				case 'm':
					aem.setZoneIsRadial(false);
				case 'c':
					aem.setVoltageUEOnly(false);
				case 'v':
					aem.setVoltageUEOnly(true);
				}
		}
	}

	public void closeAllDIFiles() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (SaveDemandInterval) {
			/* While closing DI files, write all meter registers to one file */
			try {
				createMeterTotals();
			} catch (Exception e) {
				Globals.doSimpleMsg("Error on rewrite of totals file: "+e.getMessage(), 536);
			}

			/* Close all the DI file for each meter */
			for (EnergyMeterObj pMeter : Globals.getActiveCircuit().getEnergyMeters())
				if (pMeter.isEnabled())
					pMeter.closeDemandIntervalFile();

			writeTotalsFile();  // Sum all EnergyMeter registers to "Totals.csv"
			SystemMeter.closeDemandIntervalFile();
			SystemMeter.save();
			try {
				MeterTotals.close();
				DI_Totals.close();
				Globals.setDIFilesAreOpen(false);
				if (OverLoadFileIsOpen) {
					OverLoadFile.close();
					OverLoadFileIsOpen = false;
				}
				if (VoltageFileIsOpen) {
					VoltageFile.close();
					VoltageFileIsOpen = false;
				}
			} catch (IOException e) {
				Globals.doSimpleMsg("Error closing file: "+e.getMessage(), 537);
			}
		}
	}

	public void appendAllDIFiles() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (SaveDemandInterval) {

			clearDI_Totals();  // clears accumulator arrays

			for (EnergyMeterObj pMeter : Globals.getActiveCircuit().getEnergyMeters())
				if (pMeter.isEnabled())
					pMeter.appendDemandIntervalFile();

			SystemMeter.appendDemandIntervalFile();

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
				Globals.doSimpleMsg("Error opening demand interval file \""+getName()+".csv" + " for appending."+DSSGlobals.CRLF+e.getMessage(), 538);
			}

			Globals.setDIFilesAreOpen(true);
		}
	}

	public void setSaveDemandInterval(boolean Value) {
		SaveDemandInterval = Value;
		resetAll();
	}

	/**
	 * Scans the active circuit for overloaded PD elements and writes each to a file.
	 * This is called only if in Demand Interval (DI) mode and the file is open.
	 */
	private void writeOverloadReport() {
		double Cmax;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		PrintWriter OverLoadPrinter = new PrintWriter(OverLoadFile);

		/* Check PDElements only */
		for (PDElement PDElem : ckt.getPDElements()) {
			if (PDElem.isEnabled() && !PDElem.isShunt()) {  // Ignore shunts

				if ((PDElem.getNormAmps() > 0.0) || (PDElem.getEmergAmps() > 0.0)) {
					PDElem.computeIterminal();
					Cmax = PDElem.maxTerminalOneIMag();  // For now, check only terminal 1 for overloads
					if ((Cmax > PDElem.getNormAmps()) || (Cmax > PDElem.getEmergAmps())) {
						OverLoadPrinter.printf("%-.6g,", ckt.getSolution().getDblHour());
						OverLoadPrinter.printf(" %s, %-.4g, %-.4g,", Utilities.fullName(PDElem), PDElem.getNormAmps(), PDElem.getEmergAmps());
						if (PDElem.getNormAmps() > 0.0) {
							OverLoadPrinter.printf(" %-.7g,", Cmax / PDElem.getNormAmps() * 100.0);
						} else {
							OverLoadPrinter.print(" 0.0,");
						}
						if (PDElem.getEmergAmps() > 0.0) {
							OverLoadPrinter.printf(" %-.7g,", Cmax / PDElem.getEmergAmps() * 100.0);
						} else {
							OverLoadPrinter.print(" 0.0,");
						}
						/* Find bus of first terminal */
						OverLoadPrinter.printf(" %-.3g ", ckt.getBuses()[ckt.getMapNodeToBus()[ PDElem.getNodeRef()[0] ].BusRef].getkVBase());

						OverLoadPrinter.println();
					}
				}
			}
		}

		OverLoadPrinter.close();
	}

	private void clearDI_Totals() {
		for (int i = 0; i < EnergyMeter.NumEMRegisters; i++)
			DI_RegisterTotals[i] = 0.0;
	}

	private void createDI_Totals() {
		EnergyMeterObj pMeter;

		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			DI_Totals = new FileWriter(DI_Dir + "/DI_Totals.csv");
//			FileWriter DI_TotalsStream = new FileWriter(DI_Totals, false);
			BufferedWriter DI_TotalsBuffer = new BufferedWriter(DI_Totals);

			DI_TotalsBuffer.write("Time");
			for (int i = 0; i < Globals.getActiveCircuit().getEnergyMeters().size(); i++) {
				pMeter = Globals.getActiveCircuit().getEnergyMeters().get(i);
				DI_TotalsBuffer.write(", \"" + pMeter.getRegisterNames()[i] + "\"");
			}
			DI_TotalsBuffer.newLine();
			DI_TotalsBuffer.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error creating: \""+DI_Dir+"/DI_Totals.csv\": "+e.getMessage(), 539);
		}
	}

	private void createMeterTotals() {
		int i;
		EnergyMeterObj mtr;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		try {
			File MeterTotalsFile = new File(DI_Dir + "/EnergyMeterTotals.csv");
			MeterTotals = new FileWriter(MeterTotalsFile);
			BufferedWriter MeterTotalsBuffer = new BufferedWriter(MeterTotals);

			MeterTotalsBuffer.write("Name");
			mtr = ckt.getEnergyMeters().get(0);
			if (mtr != null)
				for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
					MeterTotalsBuffer.write(", \"" + mtr.getRegisterNames()[i] + "\"");
			MeterTotalsBuffer.newLine();

			MeterTotalsBuffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDIVerbose(boolean Value) {
		DI_Verbose = Value;
		resetAll();
	}

	private void writeTotalsFile() {
		EnergyMeterObj mtr;
		double[] RegSum = new double[EnergyMeter.NumEMRegisters];
		int i, j;
		File F;

		DSSGlobals Globals = DSSGlobals.getInstance();

		/* Sum up all registers of all meters and write to Totals.csv */
		for (i = 0; i < EnergyMeter.NumEMRegisters; i++) RegSum[i] = 0.0;

		for (i = 0; i < Globals.getActiveCircuit().getEnergyMeters().size(); i++) {
			mtr = Globals.getActiveCircuit().getEnergyMeters().get(i);
			if (mtr.isEnabled())
				for (j = 0; j < EnergyMeter.NumEMRegisters; j++)
					RegSum[j] = RegSum[j] + mtr.getRegisters()[j] * mtr.getTotalsMask()[j];
		}

		try {  // Write the file
			F = new File(DI_Dir + "/Totals.csv");

			FileWriter FStream = new FileWriter(F, false);
			BufferedWriter FBuffer = new BufferedWriter(FStream);

			FBuffer.write("Year");
			mtr = Globals.getActiveCircuit().getEnergyMeters().get(0);
			if (mtr != null)
				for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
					FBuffer.write(", \"" + mtr.getRegisterNames()[i] + "\"");
			FBuffer.newLine();

			FBuffer.write(Globals.getActiveCircuit().getSolution().getYear());
			for (i = 0; i < EnergyMeter.NumEMRegisters; i++)
				FBuffer.write(String.format(", %-g ", RegSum[i]));
			FBuffer.newLine();

			FBuffer.close();
			FStream.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening demand interval file Totals.csv."+DSSGlobals.CRLF+e.getMessage(), 543);
		}
	}

	private void writeVoltageReport() {
		int i, j;
		double Vmagpu;
		int UnderCount;
		int OverCount;
		double OverVmax;
		double UnderVmin;
		Bus bus;

		/* For any bus with a defined voltage base, test for > Vmax or < Vmin */

		OverCount  = 0;
		UnderCount = 0;

		Circuit ckt = DSSGlobals.getInstance().getActiveCircuit();

		OverVmax   = ckt.getNormalMinVolts();
		UnderVmin  = ckt.getNormalMaxVolts();
		for (i = 0; i < ckt.getNumBuses(); i++) {
			bus = ckt.getBuses()[i];
			if (bus.getkVBase() > 0.0) {
				for (j = 0; j < bus.getNumNodesThisBus(); j++) {
					Vmagpu = ckt.getSolution().getNodeV()[ bus.getRef(j) ].abs() / bus.getkVBase() * 0.001;
					if (Vmagpu > 0.1) {  // ignore neutral buses
						UnderVmin = Math.min(UnderVmin, Vmagpu);
						OverVmax  = Math.max(OverVmax,  Vmagpu);
						if (Vmagpu < ckt.getNormalMinVolts()) {
							UnderCount += 1;
							break;  /* next i */
						} else if (Vmagpu > ckt.getNormalMaxVolts()) {
							OverCount += 1;
							break;
						}
					}
				}
			}
		}  /* for i */

		PrintWriter VoltagePrinter = new PrintWriter(VoltageFile);
		VoltagePrinter.printf("%-.6g,", ckt.getSolution().getDblHour());
		VoltagePrinter.printf(" %d, %-.6g, %d, %-.6g", UnderCount, UnderVmin, OverCount, OverVmax);
		VoltagePrinter.println();
		VoltagePrinter.close();
	}

	private void interpretRegisterMaskArray(double[] Mask) {
		int n = Parser.getInstance().parseAsVector(NumEMRegisters, Mask);
		for (int i = n; i < EnergyMeter.NumEMRegisters; i++)  // TODO Check zero based indexing
			Mask[i] = 1.0;  // Set the rest to 1
	}

	/**
	 * Similar to "append", by creates the files.
	 */
	public void openAllDIFiles() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		if (SaveDemandInterval) {

			clearDI_Totals();  // clears accumulator arrays

			for (EnergyMeterObj pMeter : Globals.getActiveCircuit().getEnergyMeters())
				if (pMeter.isEnabled())
					pMeter.openDemandIntervalFile();

			SystemMeter.openDemandIntervalFile();

			/* Optional Exception Reporting */
			if (Do_OverloadReport) openOverloadReportFile();
			if (Do_VoltageExceptionReport) openVoltageReportFile();

			/* Open DI_Totals */
			try {
				createDI_Totals();  // TODO Add throws exception
			} catch (Exception e) {
				Globals.doSimpleMsg("Error opening demand interval file \""+getName()+".csv" + " for appending."+DSSGlobals.CRLF+e.getMessage(), 538);
			}

			Globals.setDIFilesAreOpen(true);
		}
	}

	private void openOverloadReportFile() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (OverLoadFileIsOpen) OverLoadFile.close();

			OverLoadFile = new FileWriter(Globals.getEnergyMeterClass().getDI_Dir() + "/DI_Overloads.csv");
			PrintWriter OverLoadPrinter = new PrintWriter(OverLoadFile);

			OverLoadFileIsOpen = true;
			OverLoadPrinter.print("\"Hour\", \"Element\", \"Normal Amps\", \"Emerg Amps\", \"% Normal\", \"% Emerg\", \"kVBase\"");
			OverLoadPrinter.println();
			OverLoadPrinter.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening demand interval file \""+Globals.getEnergyMeterClass().getDI_Dir()+"/DI_Overloads.csv\"  for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	private void openVoltageReportFile() {
		DSSGlobals Globals = DSSGlobals.getInstance();

		try {
			if (VoltageFileIsOpen) VoltageFile.close();

			VoltageFile = new FileWriter(Globals.getEnergyMeterClass().getDI_Dir()+"/DI_VoltExceptions.csv");

			VoltageFileIsOpen = true;
			PrintWriter VoltagePrinter = new PrintWriter(VoltageFile);
			VoltagePrinter.println("\"Hour\", \"Undervoltages\", \"Min Voltage\", \"Overvoltage\", \"Max Voltage\"");
			VoltagePrinter.close();
		} catch (Exception e) {
			Globals.doSimpleMsg("Error opening demand interval file \""+Globals.getEnergyMeterClass().getDI_Dir()+"/DI_VoltExceptions.csv\" for writing."+DSSGlobals.CRLF+e.getMessage(), 541);
		}
	}

	public boolean isSaveDemandInterval() {
		return SaveDemandInterval;
	}

	public boolean isDIVerbose() {
		return DI_Verbose;
	}

	public double[] getDI_RegisterTotals() {
		return DI_RegisterTotals;
	}

	public void setDI_RegisterTotals(double[] dI_RegisterTotals) {
		DI_RegisterTotals = dI_RegisterTotals;
	}

	public String getDI_Dir() {
		return DI_Dir;
	}

	public void setDI_Dir(String dI_Dir) {
		DI_Dir = dI_Dir;
	}

	public FileWriter getDI_Totals() {
		return DI_Totals;
	}

	public void setDI_Totals(FileWriter dI_Totals) {
		DI_Totals = dI_Totals;
	}

	public FileWriter getMeterTotals() {
		return MeterTotals;
	}

	public void setMeterTotals(FileWriter meterTotals) {
		MeterTotals = meterTotals;
	}

	public SystemMeter getSystemMeter() {
		return SystemMeter;
	}

	public void setSystemMeter(SystemMeter systemMeter) {
		SystemMeter = systemMeter;
	}

	public boolean isDo_OverloadReport() {
		return Do_OverloadReport;
	}

	public void setDo_OverloadReport(boolean do_OverloadReport) {
		Do_OverloadReport = do_OverloadReport;
	}

	public boolean isDo_VoltageExceptionReport() {
		return Do_VoltageExceptionReport;
	}

	public void setDo_VoltageExceptionReport(boolean do_VoltageExceptionReport) {
		Do_VoltageExceptionReport = do_VoltageExceptionReport;
	}

	public boolean isOverLoadFileIsOpen() {
		return OverLoadFileIsOpen;
	}

	public void setOverLoadFileIsOpen(boolean overLoadFileIsOpen) {
		OverLoadFileIsOpen = overLoadFileIsOpen;
	}

	public boolean isVoltageFileIsOpen() {
		return VoltageFileIsOpen;
	}

	public void setVoltageFileIsOpen(boolean voltageFileIsOpen) {
		VoltageFileIsOpen = voltageFileIsOpen;
	}

	public static void setActiveEnergyMeterObj(EnergyMeterObj activeEnergyMeterObj) {
		ActiveEnergyMeterObj = activeEnergyMeterObj;
	}

	public static EnergyMeterObj getActiveEnergyMeterObj() {
		return ActiveEnergyMeterObj;
	}

}
