package com.epri.dss.meter.impl;

import java.io.File;
import java.util.List;

import com.epri.dss.common.impl.DSSCktElement;
import com.epri.dss.common.impl.DSSClassDefs;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.control.impl.SwtControlObjImpl;
import com.epri.dss.conversion.Generator;
import com.epri.dss.meter.EnergyMeter;
import com.epri.dss.meter.EnergyMeterObj;
import com.epri.dss.meter.SystemMeter;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.impl.CommandListImpl;

public class EnergyMeterImpl extends MeterClassImpl implements EnergyMeter {

	private static double Delta_Hrs;
	// Adjacency lists for PC and PD elements at each bus, built for faster searches
	private static List<String>[] BusAdjPC; // Also includes shunt PD elements
	private static List<String>[] BusAdjPD;

	private static EnergyMeterObj ActiveEnergyMeterObj;


	private Generator GeneratorClass;
	private boolean SaveDemandInterval;
	private boolean DI_Verbose;
	private File OverLoadFile;
	private File VoltageFile;

	protected double[] DI_RegisterTotals;
	protected String DI_Dir;
	protected File DI_Totals;
	protected File MeterTotals;
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
		setActiveCapControlObj(ElementList.getActive());
		Globals.getActiveCircuit().setActiveCktElement((DSSCktElement) getActiveEnergyMeterObj());

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
				parser.parseAsVector(nPhases, aem.getSensorCurrent());   // Inits to zero
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

	public int init(int Handle) {
		return 0;
	}

	public void resetMeterZonesAll() {

	}

	/* Reset all meters in active circuit to zero */
	@Override
	public void resetAll() {

	}

	/* Force all meters in active circuit to sample */
	@Override
	public void sampleAll() {

	}

	@Override
	public void saveAll() {

	}

	public void appendAllDIFiles() {

	}

	public void openAllDIFiles() {

	}

	public void closeAllDIFiles() {

	}

	private void processOptions(String Opts) {

	}

	public void setSaveDemandInterval(boolean Value) {

	}

	public boolean isSaveDemandInterval() {
		return SaveDemandInterval;
	}

	private void createMeterTotals() {

	}

	private void createFDI_Totals() {

	}

	private void clearDI_Totals() {

	}

	private void writeTotalsFile() {

	}

	private void openOverloadReportFile() {

	}

	private void openVoltageReportFile() {

	}

	private void writeOverloadReport() {

	}

	private void writeVoltageReport() {

	}

	private void interpretRegisterMaskArray(double[] Mask) {

	}

	public void setDIVerbose(boolean Value) {

	}

	public boolean isDIVerbose() {
		return DI_Verbose;
	}

	protected int makeLike(String EnergyMeterName) {
		return 0;
	}

	protected void setHasMeterFlag() {

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

	public File getDI_Totals() {
		return DI_Totals;
	}

	public void setDI_Totals(File dI_Totals) {
		DI_Totals = dI_Totals;
	}

	public File getMeterTotals() {
		return MeterTotals;
	}

	public void setMeterTotals(File meterTotals) {
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
