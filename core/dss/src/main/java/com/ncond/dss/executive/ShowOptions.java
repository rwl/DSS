package com.ncond.dss.executive;

import com.ncond.dss.common.Circuit;
import com.ncond.dss.common.CktElement;
import com.ncond.dss.common.DSS;
import com.ncond.dss.common.ShowResults;
import com.ncond.dss.common.Util;
import com.ncond.dss.meter.MonitorObj;
import com.ncond.dss.parser.Parser;
import com.ncond.dss.shared.CommandList;
import com.ncond.dss.shared.LineUnits;

public class ShowOptions {

	private static final String CRLF = DSS.CRLF;

	private static final int NumShowOptions = 32;

	public static final String[] showOption = defineOptions();
	public static final String[] showHelp = defineHelp();

	public static CommandList showCommands = new CommandList(showOption, true);

	private ShowOptions() {}

	private static String[] defineOptions() {

		String[] options = new String[NumShowOptions];

		options[0]  = "autoadded";
		options[1]  = "buses";
		options[2]  = "currents";
		options[3]  = "convergence";
		options[4]  = "elements";
		options[5]  = "faults";
		options[6]  = "isolated";
		options[7]  = "generators";
		options[8]  = "meters";
		options[9]  = "monitor";
		options[10] = "panel";
		options[11] = "powers";
		options[12] = "voltages";
		options[13] = "zone";
		options[14] = "taps";
		options[15] = "overloads";
		options[16] = "unserved";
		options[17] = "eventlog";
		options[18] = "variables";
		options[19] = "ratings";
		options[20] = "loops";
		options[21] = "losses";
		options[22] = "busflow";
		options[23] = "lineconstants";
		options[24] = "yprim";
		options[25] = "y";
		options[26] = "controlqueue";
		options[27] = "topology";
		options[28] = "mismatch";
		options[29] = "kvbasemismatch";
		options[30] = "deltaV";
		options[31] = "QueryLog";

		return options;
	}

	private static String[] defineHelp() {

		String[] help = new String[NumShowOptions];

		help[ 0] = "Shows auto added capacitors or generators. See AutoAdd solution mode.";
		help[ 1] = "Report showing all buses and nodes currently defined.";
		help[ 2] = "Report showing currents from most recent solution. syntax: " + CRLF +  CRLF +
				"Show Currents  [[residual=]yes|no*] [Seq* | Elements]" + CRLF + CRLF +
				"If \"residual\" flag is yes, the sum of currents in all conductors is reported. " +
				"Default is to report Sequence currents; otherwise currents in all conductors are reported.";
		help[ 3] = "Report on the convergence of each node voltage.";
		help[ 4] = "Shows names of all elements in circuit or all elements of a specified class. Syntax: " +CRLF + CRLF +
				"Show Elements [Classname] " +CRLF + CRLF +
				"Useful for creating scripts that act on selected classes of elements. ";
		help[ 5] = "After fault study solution, shows fault currents.";
		help[ 6] = "Report showing buses and elements that are isolated from the main source.";
		help[ 7] = "Report showing generator elements currently defined and the values of the energy meters " +CRLF +
				"associated with each generator.";
		help[ 8] = "Shows the present values of the registers in the EnergyMeter elements.";
		help[ 9] = "Shows the contents of a selected monitor. Syntax: " + CRLF + CRLF +
				" Show Monitor monitorname";
		help[10] = "Shows control panel. (not necessary for standalone version)";
		help[11] = "Report on powers flowing in circuit from most recent solution. "+CRLF+
				"Powers may be reported in kVA or MVA and in sequence quantities or in every " +
				"conductor of each element. Syntax:" +CRLF+CRLF+
				"Show Powers [MVA|kVA*] [Seq* | Elements]"+CRLF+CRLF+
				"Sequence powers in kVA is the default. Examples:"+CRLF+CRLF+
				"Show powers"+CRLF+
				"Show power kva element" +CRLF+
				"Show power mva elem";
		help[12] = "Reports voltages from most recent solution. Voltages are reported with respect to "+CRLF+
				"system reference (Node 0) by default (LN option), but may also be reported Line-Line (LL option)."+CRLF+
				"The voltages are normally reported by bus/node, but may also be reported by circuit element. Syntax:"+CRLF+CRLF+
				"Show Voltages [LL |LN*]  [Seq* | Nodes | Elements]" +CRLF +CRLF +
				"Show Voltages" +CRLF+
				"Show Voltage LN Nodes"+CRLF+
				"Show Voltages LL Nodes" +CRLF+
				"Show Voltage LN Elem";
		help[13] = "Shows the zone for a selected EnergyMeter element. Shows zone either in " +
				"a text file or in a graphical tree view." +CRLF + CRLF +
				"Show Zone  energymetername [Treeview]";
		help[14] = "Shows the regulator/LTC taps from the most recent solution.";
		help[15] = "Shows overloaded power delivery elements.";
		help[16] = "Shows loads that are \"unserved\". That is, loads for which the voltage is too low, "+
				"or a branch on the source side is overloaded. If UEonly is specified, shows only those loads " +
				"in which the emergency rating has been exceeded. Syntax:" +CRLF + CRLF+
				"Show Unserved [UEonly] (unserved loads)";
		help[17] = "Shows the present event log. (Regulator tap changes, capacitor switching, etc.)";
		help[18] = "Shows internal state variables of devices (Power conversion elements) that report them.";
		help[19] = "Shows ratings of power delivery elements.";
		help[20] = "Shows closed loops detected by EnergyMeter elements that are possibly unwanted. Otherwise, loops are OK.";
		help[21] = "Reports losses in each element and in the entire circuit.";
		help[22] = "Creates a report showing power and current flows as well as voltages around a selected bus. Syntax:" +CRLF+CRLF+
				"Show busflow busname [MVA|kVA*] [Seq* | Elements]" +CRLF+CRLF+
				"Show busflow busxxx kVA elem" +CRLF+
				"Show busflow busxxx MVA seq" +CRLF+CRLF+
				"NOTE: The Show menu will prompt you for these values.";
		help[23] = "Creates two report files for the line constants (impedances) of every LINEGEOMETRY element currently defined. "+
				"One file shows the main report with the matrices. The other file contains corresponding LINECODE " +
				"definitions that you may use in subsequent simulations.  Syntax:" + CRLF + CRLF +
				"Show LIneConstants [frequency] [none|mi|km|kft|m|me|ft|in|cm] [rho]" + CRLF + CRLF +
				"Specify the frequency, length units and earth resistivity (meter-ohms). Examples:" + CRLF + CRLF +
				"Show Lineconstants 60 kft 100" + CRLF +
				"Show Linecon 50 km 1000";
		help[24] = "Show the primitive admittance (y) matrix for the active element.";
		help[25] = "Show the system Y matrix. Could be a large file!";
		help[26] = "Shows the present contents of the control queue.";
		help[27] = "Shows the topology as seen by the SwtControl elements.";
		help[28] = "Shows the current mismatches at each node in amperes and percent of max currents at node.";
		help[29] = "Creates a report of Load and Generator elements for which the base voltage does not match the Bus base voltage. " +
				"Scripts for correcting the voltage base are suggested.";
		help[30] = "Show voltages ACROSS each 2-terminal element, phase-by-phase. ";
		help[31] = "Show Query Log file. ";

		return help;
	}

	public static int doShowCmd() {
		String fileName = "";
		MonitorObj mon;
		int MVAOpt;
		boolean LLOpt;
		boolean showResid;
		int showOptionCode;
		String busName;
		double freq;
		LineUnits units;
		double rhoLine;

		Parser parser = Parser.getInstance();
		Circuit ckt = DSS.activeCircuit;

		parser.getNextParam();
		String param = parser.makeString().toLowerCase();
		int paramPointer = showCommands.getCommand(param);

		if (paramPointer == -1) paramPointer = 12;  // voltages

		/* Check commands requiring a solution and abort if no solution or circuit */
		switch (paramPointer) {
		case 3:
		case 5:
		case 7:
		case 8:
		case 9:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 28:
		case 29:
		case 30:
			if (ckt == null) {
				DSS.doSimpleMsg("No circuit created.", 24701);
				return 0;
			}
			if ((ckt.getSolution() == null) || (ckt.getSolution().getNodeV() == null)) {
				DSS.doSimpleMsg("The circuit must be solved before you can do this.", 24702);
				return 0;
			}
		}

		DSS.inShowResults = true;

		switch (paramPointer) {
		case 0:  // autoadded
			Util.fireOffEditor(DSS.dataDirectory + DSS.circuitName_ + "AutoAddedGenerators.txt");
			Util.fireOffEditor(DSS.dataDirectory + DSS.circuitName_ + "AutoAddedCapacitors.txt");
			break;
		case 1:
			ShowResults.showBuses(DSS.dataDirectory + DSS.circuitName_ + "Buses.txt");
			break;
		case 2:
			showOptionCode = 0;
			showResid = false;
			parser.getNextParam();  // look for residual
			param = parser.makeString().toUpperCase();
			// logic handles show curr y|n|t elements or show curr elements
			if (param.length() > 0) {
				switch (param.charAt(0)) {
				case 'Y':
					showResid = true;
					break;
				case 'T':
					showResid = true;
					break;
				case 'N':
					showResid = false;
					break;
				case 'E':
					showOptionCode = 1;
					break;
				}
				parser.getNextParam();  // look for another param
				param = parser.makeString().toUpperCase();
				if (param.length() > 0) {
					switch (param.charAt(0)) {
					case 'E':
						showOptionCode = 1;
						break;
					}
				}
				switch (showOptionCode) {
				case 0:
					fileName = "Curr_Seq";
					break;
				case 1:
					fileName = "Curr_Elem";
					break;
				}
				ShowResults.showCurrents(DSS.dataDirectory + DSS.circuitName_ + fileName + ".txt", showResid, showOptionCode);
			}
			break;
		case 3:
			ckt.getSolution().writeConvergenceReport(DSS.dataDirectory + DSS.circuitName_ + "Convergence.txt");
			break;
		case 4:
			parser.getNextParam();  // look for another param
			param = parser.makeString().toLowerCase();
			ShowResults.showElements(DSS.dataDirectory + DSS.circuitName_ + "Elements.txt", param);
			break;
		case 5:
			ShowResults.showFaultStudy(DSS.dataDirectory + DSS.circuitName_ + "FaultStudy.txt");
			break;
		case 6:
			ShowResults.showIsolated(DSS.dataDirectory + DSS.circuitName_ + "Isolated.txt");
			break;
		case 7:
			ShowResults.showGenMeters(DSS.dataDirectory + DSS.circuitName_ + "GenMeterOut.txt");
			break;
		case 8:
			ShowResults.showMeters(DSS.dataDirectory + DSS.circuitName_ + "EMout.txt");
			break;
		case 9:  // show monitor
			parser.getNextParam();
			param = parser.makeString();
			if (param.length() > 0) {
				mon = (MonitorObj) DSS.monitorClass.find(param);
				if (mon != null) {
					mon.translateToCSV(true);
				} else {
					DSS.doSimpleMsg("Monitor \""+param+"\" not found."+ DSS.CRLF + parser.getCmdString(), 248);
				}
			} else {
				DSS.doSimpleMsg("Monitor name not specified." + DSS.CRLF + parser.getCmdString(), 249);
			}
			break;
		case 10:
			DSS.forms.showControlPanel();
			break;
		case 11:
			showOptionCode = 0;
			MVAOpt = 0;
			fileName = "Power";
			parser.getNextParam();
			param = parser.makeString().toLowerCase();
			if (param.length() > 0) {
				switch (param.charAt(0)) {
				case 'm':
					MVAOpt = 1;
					break;
				case 'e':
					showOptionCode = 1;
					break;
				}
			}
			parser.getNextParam();
			param = parser.makeString().toLowerCase();
			if (param.length() > 0)
				if (param.charAt(0) == 'e')
					showOptionCode = 1;
			if (showOptionCode == 1) {
				fileName = fileName + "_elem";
			} else {
				fileName = fileName + "_seq";
			}
			if (MVAOpt == 1) {
				fileName = fileName + "_MVA";
			} else {
				fileName = fileName + "_kVA";
			}

			ShowResults.showPowers(DSS.dataDirectory + DSS.circuitName_ + fileName + ".txt", MVAOpt, showOptionCode);
			break;
		case 12:
			LLOpt = false;  // line-line voltage option
			showOptionCode = 0;
			/* Check for LL or LN option */
			parser.getNextParam();
			param = parser.makeString();

			fileName = "VLN";
			if (param.length() > 0) {
				if (param.equalsIgnoreCase("LL")) {
					LLOpt = true;
					fileName = "VLL";
				}
			}
			/* Check for seq | nodes | elements */
			parser.getNextParam();
			param = parser.makeString().toUpperCase();
			if (param.length() > 0) {
				switch (param.charAt(0)) {
				case 'N':
					showOptionCode = 1;
					fileName = fileName + "_node";
					break;
				case 'E':
					showOptionCode = 2;
					fileName = fileName + "_elem";
					break;
				default:
					fileName = fileName + "_seq";
					break;
				}
			}
			ShowResults.showVoltages(DSS.dataDirectory + DSS.circuitName_ + fileName + ".txt", LLOpt, showOptionCode);
			break;
		case 13:
			ShowResults.showMeterZone(DSS.dataDirectory + DSS.circuitName_ + "ZoneOut.txt");
			break;
		case 14:
			ShowResults.showRegulatorTaps(DSS.dataDirectory + DSS.circuitName_ + "RegTaps.txt");
			break;
		case 15:
			ShowResults.showOverloads(DSS.dataDirectory + DSS.circuitName_ + "Overload.txt");
			break;
		case 16:
			parser.getNextParam();
			param = parser.makeString();
			if (param.length() > 0) {
				ShowResults.showUnserved(DSS.dataDirectory + DSS.circuitName_ + "Unserved.txt", true);
			} else {
				ShowResults.showUnserved(DSS.dataDirectory + DSS.circuitName_ + "Unserved.txt", false);
			}
			break;
		case 17:
			DSS.forms.showMessageForm(DSS.eventStrings);
			break;
		case 18:
			ShowResults.showVariables(DSS.dataDirectory + DSS.circuitName_ + "Variables.txt");
			break;
		case 19:
			ShowResults.showRatings(DSS.dataDirectory + DSS.circuitName_ + "RatingsOut.txt");
			break;
		case 20:
			ShowResults.showLoops(DSS.dataDirectory + DSS.circuitName_ + "Loops.txt");
			break;
		case 21:
			ShowResults.showLosses(DSS.dataDirectory + DSS.circuitName_ + "Losses.txt");
			break;
		case 22:  // show bus power report
			showOptionCode = 0;
			MVAOpt = 0;
			parser.getNextParam();  // get bus name
			busName = parser.makeString();
			if (busName.length() > 0) {
				fileName = busName;
			} else {
				fileName = "BusPower";
			}
			parser.getNextParam();
			param = parser.makeString().toLowerCase();
			if (param.length() > 0) {
				switch (param.charAt(0)) {
				case 'm':
					MVAOpt = 1;
					break;
				case 'e':
					showOptionCode = 1;
					break;
				}
			}
			parser.getNextParam();
			param = parser.makeString().toLowerCase();
			if (param.length() > 0)
				if (param.charAt(0) == 'e')
					showOptionCode = 1;
			if (showOptionCode == 1) {
				fileName = fileName + "_elem";
			} else {
				fileName = fileName + "_seq";
			}
			if (MVAOpt == 1) {
				fileName = fileName + "_MVA";
			} else {
				fileName = fileName + "_kVA";
			}

			ShowResults.showBusPowers(DSS.dataDirectory + DSS.circuitName_ + fileName + ".txt", busName, MVAOpt, showOptionCode);
			break;
		case 23:  // showLineConstants  show lineConstants 60 mi
			freq = DSS.defaultBaseFreq;  // default
			units = LineUnits.KFT; // 'kft'; // default
			rhoLine = 100.0;
			parser.getNextParam();
			if (parser.makeString().length() > 0)
				freq = parser.makeDouble();
			parser.getNextParam();
			if (parser.makeString().length() > 0)
				units = LineUnits.interpretUnitsCode(parser.makeString());
			parser.getNextParam();
			if (parser.makeString().length() > 0)
				rhoLine = parser.makeDouble();
			ShowResults.showLineConstants(DSS.dataDirectory + DSS.circuitName_ + "LineConstants.txt", freq, units, rhoLine);
			break;
		case 24:
			if (ckt != null) {  // Yprim
				CktElement cktElem = ckt.getActiveCktElement();
				ShowResults.showYPrim(DSS.dataDirectory + cktElem.getParentClass().getClassName() + '_' + cktElem.getName() + "_Yprim.txt");
			}
			break;
		case 25:  // Y
			ShowResults.showY(DSS.dataDirectory + DSS.circuitName_ + "SystemY.txt");
			break;
		case 26:
			if (ckt != null)
				ckt.getControlQueue().showQueue(DSS.dataDirectory + DSS.circuitName_  + "ControlQueue.csv");
			break;
		case 27:
			ShowResults.showTopology(DSS.dataDirectory + DSS.circuitName_);
			break;
		case 28:
			ShowResults.showNodeCurrentSum(DSS.dataDirectory + DSS.circuitName_ + "NodeMismatch.txt");
			break;
		case 29:
			ShowResults.showKVBaseMismatch(DSS.dataDirectory + DSS.circuitName_ + "kVBaseMismatch.txt");
			break;
		case 30:
			ShowResults.showDeltaV(DSS.dataDirectory + DSS.circuitName_ + "DeltaV.txt");
			break;
		case 31:
			Util.fireOffEditor(DSS.queryLogFileName);
			break;
		}

		DSS.inShowResults = false;
		return 0;
	}

}
