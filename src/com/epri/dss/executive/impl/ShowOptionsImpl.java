package com.epri.dss.executive.impl;

import com.epri.dss.common.DSSGlobals;
import com.epri.dss.executive.ShowOptions;
import com.epri.dss.shared.CommandList;

public class ShowOptionsImpl implements ShowOptions {

	private String CRLF = DSSGlobals.CRLF;

	private static final int NumShowOptions = 30;

	private String[] ShowOption;
	private String[] ShowHelp;

	private CommandList ShowCommands;

	public ShowOptionsImpl() {

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
		return 0;
	}

}
