package com.epri.dss.executive.impl;

import com.epri.dss.common.CktElement;
import com.epri.dss.common.impl.DSSForms;
import com.epri.dss.common.impl.DSSGlobals;
import com.epri.dss.common.impl.ShowResults;
import com.epri.dss.common.impl.Utilities;
import com.epri.dss.meter.MonitorObj;
import com.epri.dss.parser.impl.Parser;
import com.epri.dss.shared.CommandList;
import com.epri.dss.shared.impl.CommandListImpl;
import com.epri.dss.shared.impl.LineUnits;

public class ShowOptions {

	private String CRLF = DSSGlobals.CRLF;

	private static final int NumShowOptions = 30;

	private String[] ShowOption;
	private String[] ShowHelp;

	private CommandList ShowCommands;

	// Private constructor prevents instantiation from other classes
	private ShowOptions() {
		defineOptions();

		ShowCommands = new CommandListImpl(ShowOption);
		ShowCommands.setAbbrevAllowed(true);
	}

	/**
	 * ShowOptionsHolder is loaded on the first execution of
	 * ShowOptions.getInstance() or the first access to
	 * ShowOptionsHolder.INSTANCE, not before.
	 */
	private static class ShowOptionsHolder {
		public static final ShowOptions INSTANCE = new ShowOptions();
	}

	public static ShowOptions getInstance() {
		return ShowOptionsHolder.INSTANCE;
	}

	private void defineOptions() {

		this.ShowOption = new String[NumShowOptions];

		this.ShowOption[0]  = "autoadded";
		this.ShowOption[1]  = "buses";
		this.ShowOption[2]  = "currents";
		this.ShowOption[3]  = "convergence";
		this.ShowOption[4]  = "elements";
		this.ShowOption[5]  = "faults";
		this.ShowOption[6]  = "isolated";
		this.ShowOption[7]  = "generators";
		this.ShowOption[8]  = "meters";
		this.ShowOption[9]  = "monitor";
		this.ShowOption[10] = "panel";
		this.ShowOption[11] = "powers";
		this.ShowOption[12] = "voltages";
		this.ShowOption[13] = "zone";
		this.ShowOption[14] = "taps";
		this.ShowOption[15] = "overloads";
		this.ShowOption[16] = "unserved";
		this.ShowOption[17] = "eventlog";
		this.ShowOption[18] = "variables";
		this.ShowOption[19] = "ratings";
		this.ShowOption[20] = "loops";
		this.ShowOption[21] = "losses";
		this.ShowOption[22] = "busflow";
		this.ShowOption[23] = "lineconstants";
		this.ShowOption[24] = "yprim";
		this.ShowOption[25] = "y";
		this.ShowOption[26] = "controlqueue";
		this.ShowOption[27] = "topology";
		this.ShowOption[28] = "mismatch";
		this.ShowOption[29] = "kvbasemismatch";


		this.ShowHelp = new String[NumShowOptions];

		this.ShowHelp[ 0] = "Shows auto added capacitors or generators. See AutoAdd solution mode.";
		this.ShowHelp[ 1] = "Report showing all buses and nodes currently defined.";
		this.ShowHelp[ 2] = "Report showing currents from most recent solution. syntax: " + CRLF +  CRLF +
								"Show Currents  [[residual=]yes|no*] [Seq* | Elements]" + CRLF + CRLF +
							"If \"residual\" flag is yes, the sum of currents in all conductors is reported. " +
							"Default is to report Sequence currents; otherwise currents in all conductors are reported.";
		this.ShowHelp[ 3] = "Report on the convergence of each node voltage.";
		this.ShowHelp[ 4] = "Shows names of all elements in circuit or all elements of a specified class. Syntax: " +CRLF + CRLF +
							"Show ELements [Classname] " +CRLF + CRLF +
							"Useful for creating scripts that act on selected classes of elements. ";
		this.ShowHelp[ 5] = "After fault study solution, shows fault currents.";
		this.ShowHelp[ 6] = "Report showing buses and elements that are isolated from the main source.";
		this.ShowHelp[ 7] = "Report showing generator elements currently defined and the values of the energy meters " +CRLF +
							"associated with each generator.";
		this.ShowHelp[ 8] = "Shows the present values of the registers in the EnergyMeter elements.";
		this.ShowHelp[ 9] = "Shows the contents of a selected monitor. Syntax: " + CRLF + CRLF +
							" Show Monitor monitorname";
		this.ShowHelp[10] = "Shows control panel. (not necessary for standalone version)";
		this.ShowHelp[11] = "Report on powers flowing in circuit from most recent solution. "+CRLF+
							"Powers may be reported in kVA or MVA and in sequence quantities or in every " +
							"conductor of each element. Syntax:" +CRLF+CRLF+
							"Show Powers [MVA|kVA*] [Seq* | Elements]"+CRLF+CRLF+
							"Sequence powers in kVA is the default. Examples:"+CRLF+CRLF+
							"Show powers"+CRLF+
							"Show power kva element" +CRLF+
							"Show power mva elem";
		this.ShowHelp[12] = "Reports voltages from most recent solution. Voltages are reported with respect to "+CRLF+
							"system reference (Node 0) by default (LN option), but may also be reported Line-Line (LL option)."+CRLF+
							"The voltages are normally reported by bus/node, but may also be reported by circuit element. Syntax:"+CRLF+CRLF+
							"Show Voltages [LL |LN*]  [Seq* | Nodes | Elements]" +CRLF +CRLF +
							"Show Voltages" +CRLF+
							"Show Voltage LN Nodes"+CRLF+
							"Show Voltages LL Nodes" +CRLF+
							"Show Voltage LN Elem";
		this.ShowHelp[13] = "Shows the zone for a selected EnergyMeter element. Shows zone either in " +
							"a text file or in a graphical tree view." +CRLF + CRLF +
							"Show Zone  energymetername [Treeview]";
		this.ShowHelp[14] = "Shows the regulator/LTC taps from the most recent solution.";
		this.ShowHelp[15] = "Shows overloaded power delivery elements.";
		this.ShowHelp[16] = "Shows loads that are \"unserved\". That is, loads for which the voltage is too low, "+
							"or a branch on the source side is overloaded. If UEonly is specified, shows only those loads " +
							"in which the emergency rating has been exceeded. Syntax:" +CRLF + CRLF+
							"Show Unserved [UEonly] (unserved loads)";
		this.ShowHelp[17] = "Shows the present event log. (Regulator tap changes, capacitor switching, etc.)";
		this.ShowHelp[18] = "Shows internal state variables of devices (Power conversion elements) that report them.";
		this.ShowHelp[19] = "Shows ratings of power delivery elements.";
		this.ShowHelp[20] = "Shows closed loops detected by EnergyMeter elements that are possibly unwanted. Otherwise, loops are OK.";
		this.ShowHelp[21] = "Reports losses in each element and in the entire circuit.";
		this.ShowHelp[22] = "Creates a report showing power and current flows as well as voltages around a selected bus. Syntax:" +CRLF+CRLF+
							"Show BUSFlow busname [MVA|kVA*] [Seq* | Elements]" +CRLF+CRLF+
							"Show busflow busxxx kVA elem" +CRLF+
							"Show busflow busxxx MVA seq" +CRLF+CRLF+
							"NOTE: The Show menu will prompt you for these values.";
		this.ShowHelp[23] = "Creates two report files for the line constants (impedances) of every LINEGEOMETRY element currently defined. "+
							"One file shows the main report with the matrices. The other file contains corresponding LINECODE " +
							"definitions that you may use in subsequent simulations.  Syntax:" + CRLF + CRLF +
							"Show LIneConstants [frequency] [none|mi|km|kft|m|me|ft|in|cm] [rho]" + CRLF + CRLF +
							"Specify the frequency, length units and earth resistivity (meter-ohms). Examples:" + CRLF + CRLF +
							"Show Lineconstants 60 kft 100" + CRLF +
							"Show Linecon 50 km 1000";
		this.ShowHelp[24] = "Show the primitive admittance (y) matrix for the active element.";
		this.ShowHelp[25] = "Show the system Y matrix. Could be a large file!";
		this.ShowHelp[26] = "Shows the present contents of the control queue.";
		this.ShowHelp[27] = "Shows the topology as seen by the SwtControl elements.";
		this.ShowHelp[28] = "Shows the current mismatches at each node in amperes and percent of max currents at node.";
		this.ShowHelp[29] = "Creates a report of Load and Generator elements for which the base voltage does not match the Bus base voltage. " +
							"Scripts for correcting the voltage base are suggested.";

	}

	public int doShowCmd() {
		String Filname = "";
		MonitorObj pMon;

		Parser parser = Parser.getInstance();
		DSSGlobals Globals = DSSGlobals.getInstance();

		int MVAopt;
		boolean LLopt;
		boolean ShowResid;
		int ShowOptionCode;
		String BusName;
		double Freq;
		int Units;
		double Rho_line;

		parser.getNextParam();
		String Param = parser.makeString().toLowerCase();
		int ParamPointer = ShowCommands.getCommand(Param);

		if (ParamPointer == 0)
			ParamPointer = 13;  // voltages

		Globals.setInShowResults(true);

		switch (ParamPointer) {
		case 0:  // Autoadded
			Utilities.fireOffEditor(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "AutoAddedGenerators.txt");
			Utilities.fireOffEditor(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "AutoAddedCapacitors.txt");
		case 1:
			ShowResults.showBuses(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Buses.txt");
		case 2:
			ShowOptionCode = 0;
			ShowResid = false;
			parser.getNextParam();  // Look for residual
			Param = parser.makeString().toUpperCase();
			// logic handles show curr y|n|T elements or show curr elements
			if (Param.length() > 0) {
				switch (Param.charAt(0)) {
				case 'Y':
					ShowResid = true;
				case 'T':
					ShowResid = true;
				case 'N':
					ShowResid = false;
				case 'E':
					ShowOptionCode = 1;
				}
				parser.getNextParam();  // Look for another param
				Param = parser.makeString().toUpperCase();
				if (Param.length() > 0) {
					switch (Param.charAt(0)) {
					case 'E':
						ShowOptionCode = 1;
					}
				}
				switch (ShowOptionCode) {
				case 0:
					Filname = "Curr_Seq";
				case 1:
					Filname = "Curr_Elem";
				}
				ShowResults.showCurrents(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + Filname + ".txt", ShowResid, ShowOptionCode);
			}
		case 3:
			Globals.getActiveCircuit().getSolution().writeConvergenceReport(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Convergence.txt");
		case 4:
			parser.getNextParam();  // Look for another param
			Param = parser.makeString().toLowerCase();
			ShowResults.showElements(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Elements.txt", Param);
		case 5:
			ShowResults.showFaultStudy(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "FaultStudy.txt");
		case 6:
			ShowResults.showIsolated(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Isolated.txt");
		case 7:
			ShowResults.showGenMeters(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "GenMeterOut.txt");
		case 8:
			ShowResults.showMeters(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "EMout.txt");
		case 9:  // Show Monitor
			parser.getNextParam();
			Param = parser.makeString();
			if (Param.length() > 0) {
				pMon = (MonitorObj) Globals.getMonitorClass().find(Param);
				if (pMon != null) {
					pMon.translateToCSV(true);
				} else {
					Globals.doSimpleMsg("Monitor \""+Param+"\" not found."+ DSSGlobals.CRLF + parser.getCmdString(), 248);
				}
			} else {
				Globals.doSimpleMsg("Monitor Name Not Specified." + DSSGlobals.CRLF + parser.getCmdString(), 249);
			}
		case 10:
			DSSForms.showControlPanel();
		case 11:
			ShowOptionCode = 0;
			MVAopt = 0;
			Filname = "Power";
			parser.getNextParam();
			Param = parser.makeString().toLowerCase();
			if (Param.length() > 0) {
				switch (Param.charAt(0)) {
				case 'm':
					MVAopt = 1;
				case 'e':
					ShowOptionCode = 1;
				}
			}
			parser.getNextParam();
			Param = parser.makeString().toLowerCase();
			if (Param.length() > 0)
				if (Param.charAt(0) == 'e')
					ShowOptionCode = 1;
			if (ShowOptionCode == 1) {
				Filname = Filname + "_elem";
			} else {
				Filname = Filname + "_seq";
			}
			if (MVAopt == 1) {
				Filname = Filname + "_MVA";
			} else {
				Filname = Filname + "_kVA";
			}

			ShowResults.showPowers(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + Filname + ".txt", MVAopt, ShowOptionCode);
		case 12:
			LLopt = false;  // Line-Line voltage option
			ShowOptionCode = 0;
			/* Check for LL or LN option */
			parser.getNextParam();
			Param = parser.makeString();

			Filname = "VLN";
			if (Param.length() > 0) {
				if (Param.equals("LL")) {
					LLopt = true;
					Filname = "VLL";
				}
			}
			/* Check for Seq | nodes | elements */
			parser.getNextParam();
			Param = parser.makeString().toUpperCase();
			if (Param.length() > 0) {
				switch (Param.charAt(0)) {
				case 'N':
					ShowOptionCode = 1;
					Filname = Filname + "_Node";
				case 'E':
					ShowOptionCode = 2;
					Filname = Filname + "_elem";
				default:
					Filname = Filname + "_seq";
				}
			}
			ShowResults.showVoltages(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + Filname + ".txt", LLopt, ShowOptionCode);
		case 13:
			ShowResults.showMeterZone(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "ZoneOut.txt");
		case 14:
			ShowResults.showRegulatorTaps(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "RegTaps.txt");
		case 15:
			ShowResults.showOverloads(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Overload.txt");
		case 16:
			parser.getNextParam();
			Param = parser.makeString();
			if (Param.length() > 0) {
				ShowResults.showUnserved(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Unserved.txt", true);
			} else {
				ShowResults.showUnserved(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Unserved.txt", false);
			}
		case 17:
			DSSForms.showMessageForm(Globals.getEventStrings());
		case 18:
			ShowResults.showVariables(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Variables.txt");
		case 19:
			ShowResults.showRatings(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "RatingsOut.txt");
		case 20:
			ShowResults.showLoops(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Loops.txt");
		case 21:
			ShowResults.showLosses(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "Losses.txt");
		case 22:  // Show Bus Power Report
			ShowOptionCode = 0;
			MVAopt = 0;
			parser.getNextParam(); // Get busname
			BusName = parser.makeString();
			if (BusName.length() > 0) {
				Filname = BusName;
			} else {
				Filname = "BusPower";
			}
			parser.getNextParam();
			Param = parser.makeString().toLowerCase();
			if (Param.length() > 0) {
				switch (Param.charAt(0)) {
				case 'm':
					MVAopt = 1;
				case 'e':
					ShowOptionCode = 1;
				}
			}
			parser.getNextParam();
			Param = parser.makeString().toLowerCase();
			if (Param.length() > 0)
				if (Param.charAt(0) == 'e')
					ShowOptionCode = 1;
			if (ShowOptionCode == 1) {
				Filname = Filname + "_elem";
			} else {
				Filname = Filname + "_seq";
			}
			if (MVAopt == 1) {
				Filname = Filname + "_MVA";
			} else {
				Filname = Filname + "_kVA";
			}

			ShowResults.showBusPowers(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + Filname + ".txt", BusName, MVAopt, ShowOptionCode);
		case 23:  // ShowLineConstants  Show Lineconstants 60 mi
			Freq = Globals.getDefaultBaseFreq();  // Default
			Units = LineUnits.UNITS_KFT; // 'kft'; // default
			Rho_line = 100.0;
			parser.getNextParam();
			if (parser.makeString().length() > 0)
				Freq = parser.makeDouble();
			parser.getNextParam();
			if (parser.makeString().length() > 0)
				Units = LineUnits.getUnitsCode(parser.makeString());
			parser.getNextParam();
			if (parser.makeString().length() > 0)
				Rho_line = parser.makeDouble();
			ShowResults.showLineConstants(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "LineConstants.txt", Freq, Units, Rho_line);
		case 24:
			if (Globals.getActiveCircuit() != null) {  // Yprim
				CktElement cktElem = Globals.getActiveCircuit().getActiveCktElement();
				ShowResults.showYPrim(Globals.getDSSDataDirectory() + cktElem.getParentClass().getName() + '_' + cktElem.getName() + "_Yprim.txt");
			}
		case 25:  // Y
			ShowResults.showY(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "SystemY.txt");
		case 26:
			if (Globals.getActiveCircuit() != null)
				Globals.getActiveCircuit().getControlQueue().showQueue(Globals.getDSSDataDirectory() + Globals.getCircuitName_()  + "ControlQueue.csv");
		case 27:
			ShowResults.showTopology(Globals.getDSSDataDirectory() + Globals.getCircuitName_());
		case 28:
			ShowResults.showNodeCurrentSum(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "NodeMismatch.txt");
		case 29:
			ShowResults.showkVBaseMismatch(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "kVBaseMismatch.txt");
		case 30:
			ShowResults.showDeltaV(Globals.getDSSDataDirectory() + Globals.getCircuitName_() + "DeltaV.txt");
		}

		Globals.setInShowResults(false);
		return 0;
	}

}
