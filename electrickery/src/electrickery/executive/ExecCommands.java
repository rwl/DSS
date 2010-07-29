/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.executive;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exec Commands</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines commands for the executive.
 * <!-- end-model-doc -->
 *
 *
 * @see electrickery.executive.ExecutivePackage#getExecCommands()
 * @model
 * @generated
 */
public interface ExecCommands extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Create a new object within the DSS. Object becomes the active
	 * object.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void create();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Edit an object. The object is selected and it then becomes the
	 * active object.
	 * 
	 * Note that Edit is the default command.  You many change a property
	 * value simply by giving the full property name and the new value.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void edit();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Continuation of editing on the active object.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void more();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Selects an element and makes it the active element.  You can also
	 * specify the active terminal (default = 1).
	 * 
	 * Syntax:
	 *     Select [element=]elementname  [terminal=]terminalnumber
	 * 
	 * Example:
	 *     Select Line.Line1
	 *     ~ R1=.1'+CRLF+'(continue editing)
	 *     Select Line.Line1 2
	 *     Voltages  (returns voltages at terminal 2 in Result)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void select(String elementName, int terminalNumber);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default class = Meters, which saves the present values in both
	 * monitors and energy meters in the active circuit.
	 * "Save Circuit" saves the present enabled circuit elements to the
	 * specified subdirectory in standard DSS form with a Master.txt file and
	 * separate files for each class of data. If Dir= not specified a unique
	 * name based on the circuit name is created automatically.  If dir= is
	 * specified, any existing files are overwritten.
	 * "Save Voltages" saves the present solution in a simple CSV format in a
	 * file called DSS_SavedVoltages.
	 * Used for VDIFF command.
	 * Any class can be saved to a file.  If no filename specified, the
	 * classname is used.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void save(EClass cls, String dir);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Writes selected results to a text file and brings up the editor
	 * (see Set Editor=....) with the file for you to browse.
	 * 
	 * Valid Options (*=default):
	 *     Show Buses
	 *     Show Currents  [[residual=]yes|no*] [Seq* | Elements]
	 *     Show COnvergence  (convergence report)
	 *     Show ELements [Classname] (shows names of all elements in circuit
	 *     or all elements of a class)
	 *     Show Faults (after Fault Study)
	 *     Show Generators
	 *     Show Losses
	 *     Show Meters
	 *     Show Monitor Monitorname
	 *     Show Panel (control panel)
	 *     Show Powers [MVA|kVA*] [Seq* | Elements]
	 *     Show Voltages [LL |LN*]  [Seq* | Nodes | Elements]
	 *     Show Zone  EnergyMeterName [Treeview]
	 *     Show AutoAdded  (see AutoAdd solution mode)
	 *     Show Taps  (regulated transformers)
	 *     Show Overloads (overloaded PD elements)
	 *     Show Unserved [UEonly] (unserved loads)
	 *     Show EVentlog
	 *     Show VAriables
	 *     Show Isolated
	 *     Show Ratings
	 *     Show Loops
	 *     Show Yprim  (shows Yprim for active ckt element)
	 *     Show Y      (shows system Y)
	 *     Show BusFlow busname [MVA|kVA*] [Seq* | Elements]
	 *     Show LineConstants [frequency] [none|mi|km|kft|m|me|ft|in|cm]
	 * 
	 * Default is "show voltages LN Seq".
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void show(String option);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Perform the solution of the present solution mode. You can set any
	 * option that you can set with the Set command (see Set). The Solve
	 * command is virtually synonymous with the Set command except that
	 * a solution is performed after the options are processed.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void solve();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Enables a circuit element or entire class.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void enable();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Disables a circuit element or entire class. The item remains
	 * defined, but is not included in the solution.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void disable();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Plots results in a variety of manners.
	 * 
	 * Implemented options (in order):
	 * 
	 *     Type = {Circuit | Monitor | Daisy | Zones | AutoAdd | General}
	 * 
	 *     Quantity = {Voltage | Current | Power | Losses | Capacity |
	 *     (Value Index for General, AutoAdd, or Circuit[w/ file]) }
	 * 
	 *     Max = {0 | value corresponding to max scale or line thickness}
	 * 
	 *     Dots = {Y | N}
	 * 
	 *     Labels = {Y | N}
	 * 
	 *     Object = [metername for Zone plot | Monitor name | File Name for
	 *     General bus data or Circuit branch data]
	 * 
	 *     ShowLoops = {Y | N} (default=N)
	 * 
	 *     R3 = pu value for tri-color plot max range [.85] (Color C3)
	 * 
	 *     R2 = pu value for tri-color plot mid range [.50] (Color C2)
	 * 
	 *     C1, C2, C3 = {RGB color number}
	 * 
	 *     Channels=(array of channel numbers for monitor plot)
	 * 
	 *     Bases=(array of base values for each channel for monitor plot).
	 *     Default is 1.0 for each.  Set Base= after defining channels.
	 * 
	 *     Subs={Y | N} (default=N) (show substations)
	 * 
	 *     Thickness=max thickness allowed for lines in circuit plots
	 *     (default=7)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void plot();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resets all Monitors, Energymeters, etc.
	 * 
	 * If no argument specified, resets all options listed.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void reset(resetType type);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reads the designated file name containing DSS commands and
	 * processes them as if they were entered directly into the command line.
	 * The file is said to be "compiled."
	 * 
	 * Similar to "redirect" except changes the default directory to the path
	 * of the specified file.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void compile(String fileName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Used to set various DSS solution modes and options.  You may also
	 * set the options with the Solve command.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void setValue();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Display the properties of either a specific DSS object or a
	 * complete dump on all variables in the problem (Warning! Could be very
	 * large!).
	 * 
	 * Brings up the default text editor with the text file written by this
	 * command.
	 * 
	 * Syntax: dump [class.obj] [debug]
	 * 
	 * Examples:
	 *     Dump line.line1
	 *     Dump solution  (dumps all solution vars)
	 *     Dump commands  (dumps all commands to a text file)
	 *     Dump transformer.*  (dumps all transformers)
	 *     Dump ALLOCationfactors  (load allocation factors)
	 *     Dump (dumps all objects in circuit)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void dump(EClass cls, EObject obj, boolean debug);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opens the specified terminal and conductor of the specified circuit
	 * element. If the conductor is not specified, all phase conductors of the
	 * terminal are opened.
	 * 
	 * Examples:
	 *     Open line.line1 2 (opens all phases of terminal 2)
	 *     Open line.line1 2 3 (opens the 3rd conductor of terminal 2)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void open(EClass element, EClass terminal, int conductor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opposite of the Open command.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void close(EClass element, EClass terminal, int conductor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reads the designated file name containing DSS commands and
	 * processes them as if they were entered directly into the command line.
	 * Similar to "Compile", but leaves current directory where it was when
	 * Redirect command is invoked. Can temporarily change to subdirectories
	 * if nested Redirect commands require.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void redirect(String fileName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Handles display of help.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void help();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Handles closing the application.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void quit();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Inquiry for property value.  Result is put into GlobalResult and
	 * can be seen in the Result Window.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void what(EClass element, EStructuralFeature property);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Increments year, hour, or time as specified.  If
	 * "t" is specified, then increments time by current step size.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void next(nextType type);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void panel();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Force all monitors and meters to take a sample now.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void sample();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Clear all circuits currently in memory.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void clear();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void about();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calculates voltagebase for buses based on voltage bases defined
	 * with Set voltagebases=... command.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void calcVoltageBases();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Command to explicitly set the base voltage for a bus.
	 * 
	 * Bus must be previously defined. Parameters in order are:
	 * 
	 *     Bus = {bus name}
	 *     kVLL = (line-to-line base kV)
	 *     kVLN = (line-to-neutral base kV)
	 * 
	 * kV base is normally given in line-to-line kV (phase-phase). However,
	 * it may also be specified by line-to-neutral kV.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void setKvBase(EClass bus, float kVLL, float kVLN);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Forces rebuild of Y matrix upon next Solve command regardless of
	 * need. The usual reason for doing this would be to reset the matrix for
	 * another load level when using LoadModel=PowerFlow (the default) when
	 * the system is difficult to solve when the load is far from its base
	 * value.  Works by invalidating the Y primitive matrices for all the
	 * Power Conversion elements.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void buildY();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns DSS property values set using the Set command. Result is
	 * return in Result property of the Text interface.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	void getValue();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This command forces reinitialization of the solution for the next
	 * Solve command. To minimize iterations, most solutions start with the
	 * previous solution unless there has been a circuit change.  However, if
	 * the previous solution is bad, it may be necessary to re-initialize. In
	 * most cases, a re-initiallization results in a zero-load power flow
	 * solution with only the series power delivery elements considered.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void initialise();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Export various solution values to CSV files for import into other
	 * programs.
	 * 
	 * Creates a new CSV file except for Energymeter and Generator objects,
	 * for which the results for each device of this class are APPENDED to the
	 * CSV File. You may export to a specific file by specifying the file name
	 * as the LAST parameter on the line. Otherwise, the default file names
	 * shown below are used. For Energymeter and Generator, specifying the
	 * switch "/multiple" (or /m) for the file name will cause a separate file
	 * to be written for each meter or generator. The default is for a single
	 * file containing all elements.
	 * 
	 * Syntax for Implemented Exports:
	 * 
	 *     Export Voltages  [Filename]   (EXP_VOLTAGES.CSV)
	 *     Export SeqVoltages [Filename] (EXP_SEQVOLTAGES.CSV)
	 *     Export Currents [Filename]    (EXP_CURRENTS.CSV)
	 *     Export Overloads [Filename]    EXP_OVERLOADS.CSV)
	 *     Export Unserved  [UEonly] [Filename]   EXP_UNSERVED.CSV)
	 *     Export SeqCurrents [Filename] (EXP_SEQCURRENTS.CSV)
	 *     Export Powers [MVA] [Filename](EXP_POWERS.CSV)
	 *     Export Faultstudy [Filename]  (EXP_FAULTS.CSV)
	 *     Export Generators [Filename | /m ]  (EXP_GENMETERS.CSV)
	 *     Export Loads [Filename]       (EXP_LOADS.CSV)
	 *     Export Meters [Filename |/m ] (EXP_METERS.CSV)
	 *     Export Monitors monitorname   (file name is assigned)
	 *     Export Yprims  [Filename]     (EXP_Yprims.CSV) (all YPrim matrices)
	 *     Export Y  [Filename]          (EXP_Y.CSV)   (system Y matrix)
	 * 
	 * May be abreviated Export V, Export C, etc.  Default is "V".
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void export(exportType type, String fileName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Edit specified file in default text file editor.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void fileEdit(String fileName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the voltages for the ACTIVE BUS in the Result string.
	 * For setting the active Bus, use the Select command or the
	 * set_bus= option.
	 * 
	 * Returned as magnitude and angle quantities, comma separated, one set
	 * per conductor of the terminal.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String voltages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the currents for each conductor of ALL terminals of the
	 * active circuit element in the Result string/ (See select command.)
	 * Returned as comma-separated magnitude and angle.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String currents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the powers (complex) going into each conductors of ALL
	 * terminals of the active circuit element in the Result string.
	 * (See select command.)
	 * 
	 * Returned as comma-separated kW and kvar.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String powers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the sequence voltages at all terminals of the active
	 * circuit element (see Select command) in Result string.  Returned as
	 * comma-separated magnitude only values.
	 * 
	 * Order of returned values: 0, 1, 2  (for each terminal).
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String seqVoltages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the sequence currents into all terminals of the active
	 * circuit element (see Select command) in Result string.  Returned as
	 * comma-separated magnitude only values.
	 * 
	 * Order of returned values: 0, 1, 2  (for each terminal).
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String seqCurrents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the sequence powers into all terminals of the active
	 * circuit element (see Select command) in Result string.  Returned as
	 * comma-separated kw, kvar pairs.
	 * 
	 * Order of returned values: 0, 1, 2  (for each terminal).
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String seqPower();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the total losses for the active circuit element in the
	 * Result string in kW, kvar.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String losses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the losses for the active circuit element for each PHASE in
	 * the Result string in comma-separated kW, kvar pairs.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String phaseLosses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the total losses for the active circuit in the Result
	 * string in kW, kvar.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String cktLosses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Estimates the allocation factors for loads that are defined using
	 * the XFKVA property. Requires that energymeter objects be defined with
	 * the PEAKCURRENT property set. Loads that are not in the zone of an
	 * energymeter cannot be allocated.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String allocateLoads();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Brings up form editor on active DSS object.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void formEdit(EObject obj);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Totals all EnergyMeter objects in the circuit and reports register
	 * totals in the result string.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String totals();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Find the maximum load the active circuit can serve in the PRESENT
	 * YEAR. Uses the EnergyMeter objects with the registers set with the
	 * SET UEREGS= (..) command for the AutoAdd functions.
	 * 
	 * Syntax (defaults shown):
	 *     capacity [start=]0.9 [increment=]0.005
	 * 
	 * Returns the metered kW (load + losses - generation) and per unit load
	 * multiplier for the loading level at which something in the system
	 * reports an overload or undervoltage. If no violations, then it returns
	 * the metered kW for peak load for the year (1.0 multiplier). Aborts and
	 * returns 0 if no energymeters.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	double capacity();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * List of intrinsic DSS Classes. Returns comma-separated list in
	 * Result variable.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String classes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * List of user-defined DSS Classes. Returns comma-separated list in
	 * Result variable.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	String userClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns full Zsc matrix for the ACTIVE BUS in comma-separated
	 * complex number form.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void Zsc();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns symmetrical component impedances, Z1, Z0 for the ACTIVE BUS
	 * in comma-separated R+jX form.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void Zsc10();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Refreshes Zsc matrix for the ACTIVE BUS.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void ZscRefresh();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns full Ysc matrix for the ACTIVE BUS in comma-separated
	 * complex number form G + jB.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void Ysc();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Just like the Voltages command, except the voltages are in per unit
	 * if the kVbase at the bus is defined.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void puVoltages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns variable values for active element if PC element.
	 * Otherwise, returns null.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	EList<Double> varValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns variable names for active element if PC element. Otherwise,
	 * returns null.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	EList<String> varNames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Define x,y coordinates for buses.  Execute after Solve command
	 * performed so that bus lists are defined. Reads coordinates from a CSV
	 * file with records of the form: busname, x, y.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void busCoords(String fileName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Updates the buslist using the currently enabled circuit elements.
	 * (This happens automatically for Solve command.)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void makeBusList();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Attempts to convert present circuit model to a positive sequence
	 * equivalent. It is recommended to Save the circuit after this and edit
	 * the saved version to correct possible misinterpretations.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void makePosSequence();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reduce the circuit according
	 * to reduction options. See "Set ReduceOptions" and "Set Keeplist"
	 * options.
	 * 
	 * Energymeter objects actually perform the reduction.  "All" causes all
	 * meters to reduce their zones.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void reduce(reduceType type);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Interpolates coordinates for missing bus coordinates in meter zone.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void interpolate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Aligns DSS script files in columns for easier reading.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void alignFile(String fileName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Send specified object to TOP.  Loadshapes must be hourly fixed
	 * interval.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void top();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rotate circuit plotting coordinates by specified angle.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void rotate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Displays the difference between the present solution and the last
	 * on saved using the SAVE VOLTAGES command.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void vDiff();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Displays a power flow summary of the most recent solution.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void summary();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Distributes generators on the system in the manner specified by "how".
	 * 
	 *     kW = total generation to be distributed (default=1000)
	 *     how= process name as indicated (default=proportional to load)
	 *     skip = no. of buses to skip for "How=Skip" (default=1)
	 *     PF = power factor for new generators (default=1.0)
	 *     file = name of file to save (default=distgenerators.txt)
	 *     MW = alternate way to specify kW (default = 1)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void distribute(distributionType how);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Plots demand interval (DI) results from yearly simulation cases.
	 * Plots selected registers from selected meter file (default =
	 * DI_Totals.CSV).
	 * Peak defaults to NO.  If YES, only daily peak of specified registers
	 * is plotted.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void diPlot();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Compares yearly simulations of two specified cases with respect to the
	 * quantity in the designated register from the designated meter file.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void compareCases();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Plots yearly curves for specified cases and registers.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void yearlyCurves();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Shows the currents for selected element on a drawing in
	 * polar coordinates.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void visualise();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Close all DI files ... useful at end of yearly solution where DI
	 * files are left open.
	 * 
	 * (Reset and Set Year=nnn will also close the DI files)
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void closeDI();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Execute state estimator on present circuit given present sensor
	 * values.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void estimate();

} // ExecCommands
