/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Defines objects common to all circuits in the DSS.
 * <!-- end-model-doc -->
 * @see electrickery.common.CommonFactory
 * @model kind="package"
 * @generated
 */
public interface CommonPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "common";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.electrickery.com/common";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "common";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommonPackage eINSTANCE = electrickery.common.impl.CommonPackageImpl.init();

	/**
	 * The meta object id for the '{@link electrickery.common.impl.CircuitImpl <em>Circuit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.CircuitImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getCircuit()
	 * @generated
	 */
	int CIRCUIT = 0;

	/**
	 * The feature id for the '<em><b>Executive</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__EXECUTIVE = 0;

	/**
	 * The feature id for the '<em><b>Solution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__SOLUTION = 1;

	/**
	 * The feature id for the '<em><b>Buses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__BUSES = 2;

	/**
	 * The feature id for the '<em><b>Map Node To Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__MAP_NODE_TO_BUS = 3;

	/**
	 * The feature id for the '<em><b>Bus List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__BUS_LIST = 4;

	/**
	 * The feature id for the '<em><b>Voltage Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__VOLTAGE_SOURCES = 5;

	/**
	 * The feature id for the '<em><b>Current Sources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__CURRENT_SOURCES = 6;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__GENERATORS = 7;

	/**
	 * The feature id for the '<em><b>Loads</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__LOADS = 8;

	/**
	 * The feature id for the '<em><b>Active Circuit Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__ACTIVE_CIRCUIT_ELEMENT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NAME = 10;

	/**
	 * The feature id for the '<em><b>Num Nodes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NUM_NODES = 11;

	/**
	 * The feature id for the '<em><b>Num Buses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NUM_BUSES = 12;

	/**
	 * The feature id for the '<em><b>Bus Name Redefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__BUS_NAME_REDEFINED = 13;

	/**
	 * The feature id for the '<em><b>Solved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__SOLVED = 14;

	/**
	 * The feature id for the '<em><b>Load Multiplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__LOAD_MULTIPLIER = 15;

	/**
	 * The feature id for the '<em><b>Default Growth Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__DEFAULT_GROWTH_FACTOR = 16;

	/**
	 * The feature id for the '<em><b>Default Growth Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__DEFAULT_GROWTH_RATE = 17;

	/**
	 * The feature id for the '<em><b>Generator Dispatch Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__GENERATOR_DISPATCH_REFERENCE = 18;

	/**
	 * The feature id for the '<em><b>Gen Multiplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__GEN_MULTIPLIER = 19;

	/**
	 * The feature id for the '<em><b>Default Hour Mult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__DEFAULT_HOUR_MULT = 20;

	/**
	 * The feature id for the '<em><b>Control bus Name Redefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__CONTROL_BUS_NAME_REDEFINED = 21;

	/**
	 * The feature id for the '<em><b>Price Signal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__PRICE_SIGNAL = 22;

	/**
	 * The feature id for the '<em><b>Emerg Min Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__EMERG_MIN_VOLTS = 23;

	/**
	 * The feature id for the '<em><b>Emerg Max Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__EMERG_MAX_VOLTS = 24;

	/**
	 * The feature id for the '<em><b>Normal Min Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NORMAL_MIN_VOLTS = 25;

	/**
	 * The feature id for the '<em><b>Normal Max Volts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NORMAL_MAX_VOLTS = 26;

	/**
	 * The feature id for the '<em><b>Log Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__LOG_EVENTS = 27;

	/**
	 * The feature id for the '<em><b>Meter Zones Computed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__METER_ZONES_COMPUTED = 28;

	/**
	 * The feature id for the '<em><b>Zones Locked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__ZONES_LOCKED = 29;

	/**
	 * The number of structural features of the '<em>Circuit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_FEATURE_COUNT = 30;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.YMatrixImpl <em>YMatrix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.YMatrixImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getYMatrix()
	 * @generated
	 */
	int YMATRIX = 1;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int YMATRIX__CIRCUIT = 0;

	/**
	 * The number of structural features of the '<em>YMatrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int YMATRIX_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.BusImpl <em>Bus</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.BusImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getBus()
	 * @generated
	 */
	int BUS = 2;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.CircuitElementImpl <em>Circuit Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.CircuitElementImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getCircuitElement()
	 * @generated
	 */
	int CIRCUIT_ELEMENT = 3;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.ConductorImpl <em>Conductor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.ConductorImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getConductor()
	 * @generated
	 */
	int CONDUCTOR = 7;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.SolutionImpl <em>Solution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.SolutionImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getSolution()
	 * @generated
	 */
	int SOLUTION = 4;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.TerminalImpl <em>Terminal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.TerminalImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getTerminal()
	 * @generated
	 */
	int TERMINAL = 6;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.NamedImpl <em>Named</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.NamedImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getNamed()
	 * @generated
	 */
	int NAMED = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__NAME = NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__CIRCUIT = NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>VBus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__VBUS = NAMED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bus Current</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__BUS_CURRENT = NAMED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Num Nodes This Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__NUM_NODES_THIS_BUS = NAMED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>KV Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__KV_BASE = NAMED_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__X = NAMED_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__Y = NAMED_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Coord Defined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__COORD_DEFINED = NAMED_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Keep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__KEEP = NAMED_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Bus Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__BUS_REF = NAMED_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__NODE_NUM = NAMED_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Bus</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS_FEATURE_COUNT = NAMED_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__ENABLED = 1;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__BASE_FREQ = 2;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YORDER = 3;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_INVALID = 4;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED = 5;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__TERMINALS = 6;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__ACTIVE_TERMINAL = 7;

	/**
	 * The feature id for the '<em><b>Active Terminal Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX = 8;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NTERMS = 9;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NCONDS = 10;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NPHASES = 11;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_SERIES = 12;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_SHUNT = 13;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM = 14;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_FREQ = 15;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NODE_REF = 16;

	/**
	 * The number of structural features of the '<em>Circuit Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT_FEATURE_COUNT = 17;

	/**
	 * The feature id for the '<em><b>Y</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__Y = 0;

	/**
	 * The feature id for the '<em><b>YSystem</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__YSYSTEM = 1;

	/**
	 * The feature id for the '<em><b>YSeries</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__YSERIES = 2;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__YEAR = 3;

	/**
	 * The feature id for the '<em><b>Preserve Node Voltages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__PRESERVE_NODE_VOLTAGES = 4;

	/**
	 * The feature id for the '<em><b>Frequency Changed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__FREQUENCY_CHANGED = 5;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__FREQUENCY = 6;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MODE = 7;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CIRCUIT = 8;

	/**
	 * The feature id for the '<em><b>Solution Initialised</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SOLUTION_INITIALISED = 9;

	/**
	 * The feature id for the '<em><b>Series YInvalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SERIES_YINVALID = 10;

	/**
	 * The feature id for the '<em><b>System YChanged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SYSTEM_YCHANGED = 11;

	/**
	 * The feature id for the '<em><b>Load Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__LOAD_MODEL = 12;

	/**
	 * The feature id for the '<em><b>Voltage Base Changed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__VOLTAGE_BASE_CHANGED = 13;

	/**
	 * The feature id for the '<em><b>Harmonic Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__HARMONIC_MODEL = 14;

	/**
	 * The feature id for the '<em><b>Dynamic Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__DYNAMIC_MODEL = 15;

	/**
	 * The feature id for the '<em><b>Use Auxillary Currents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__USE_AUXILLARY_CURRENTS = 16;

	/**
	 * The feature id for the '<em><b>Loads Need Updating</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__LOADS_NEED_UPDATING = 17;

	/**
	 * The feature id for the '<em><b>Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ITERATION = 18;

	/**
	 * The feature id for the '<em><b>Max Iterations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MAX_ITERATIONS = 19;

	/**
	 * The feature id for the '<em><b>Max Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MAX_ERROR = 20;

	/**
	 * The feature id for the '<em><b>Convergence Tolerance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONVERGENCE_TOLERANCE = 21;

	/**
	 * The feature id for the '<em><b>Converged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONVERGED = 22;

	/**
	 * The feature id for the '<em><b>Control Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONTROL_ITERATION = 23;

	/**
	 * The feature id for the '<em><b>Max Control Iterations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MAX_CONTROL_ITERATIONS = 24;

	/**
	 * The feature id for the '<em><b>Control Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONTROL_MODE = 25;

	/**
	 * The feature id for the '<em><b>Control Actions Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONTROL_ACTIONS_DONE = 26;

	/**
	 * The feature id for the '<em><b>Most Iterations Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MOST_ITERATIONS_DONE = 27;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ALGORITHM = 28;

	/**
	 * The feature id for the '<em><b>Last Solution Was Direct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__LAST_SOLUTION_WAS_DIRECT = 29;

	/**
	 * The feature id for the '<em><b>Solution Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SOLUTION_COUNT = 30;

	/**
	 * The feature id for the '<em><b>Node V</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__NODE_V = 31;

	/**
	 * The feature id for the '<em><b>Node VBase</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__NODE_VBASE = 32;

	/**
	 * The feature id for the '<em><b>Error Saved</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ERROR_SAVED = 33;

	/**
	 * The feature id for the '<em><b>VMag Saved</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__VMAG_SAVED = 34;

	/**
	 * The feature id for the '<em><b>Currents</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CURRENTS = 35;

	/**
	 * The feature id for the '<em><b>Algorithms</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ALGORITHMS = 36;

	/**
	 * The number of structural features of the '<em>Solution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION_FEATURE_COUNT = 37;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.SolutionAlgsImpl <em>Solution Algs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.SolutionAlgsImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getSolutionAlgs()
	 * @generated
	 */
	int SOLUTION_ALGS = 5;

	/**
	 * The number of structural features of the '<em>Solution Algs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION_ALGS_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Bus Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__BUS_REF = 0;

	/**
	 * The feature id for the '<em><b>Term Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__TERM_NODE_REF = 1;

	/**
	 * The feature id for the '<em><b>Conductors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__CONDUCTORS = 2;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__CHECKED = 3;

	/**
	 * The feature id for the '<em><b>NCond</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__NCOND = 4;

	/**
	 * The feature id for the '<em><b>Active Conductor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL__ACTIVE_CONDUCTOR = 5;

	/**
	 * The number of structural features of the '<em>Terminal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_FEATURE_COUNT = 6;

	/**
	 * The feature id for the '<em><b>Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDUCTOR__CLOSED = 0;

	/**
	 * The feature id for the '<em><b>Fuse Blown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDUCTOR__FUSE_BLOWN = 1;

	/**
	 * The feature id for the '<em><b>Accum ISq T</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDUCTOR__ACCUM_ISQ_T = 2;

	/**
	 * The feature id for the '<em><b>Tcc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDUCTOR__TCC_NAME = 3;

	/**
	 * The number of structural features of the '<em>Conductor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDUCTOR_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.GlobalsImpl <em>Globals</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.GlobalsImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getGlobals()
	 * @generated
	 */
	int GLOBALS = 9;

	/**
	 * The feature id for the '<em><b>Solution Abort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS__SOLUTION_ABORT = 0;

	/**
	 * The feature id for the '<em><b>Executives</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS__EXECUTIVES = 1;

	/**
	 * The feature id for the '<em><b>Solution Was Attempted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS__SOLUTION_WAS_ATTEMPTED = 2;

	/**
	 * The number of structural features of the '<em>Globals</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link electrickery.common.impl.ParserImpl <em>Parser</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.impl.ParserImpl
	 * @see electrickery.common.impl.CommonPackageImpl#getParser()
	 * @generated
	 */
	int PARSER = 10;

	/**
	 * The feature id for the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER__TOKEN = 0;

	/**
	 * The number of structural features of the '<em>Parser</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link electrickery.common.connectionType <em>connection Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.connectionType
	 * @see electrickery.common.impl.CommonPackageImpl#getconnectionType()
	 * @generated
	 */
	int CONNECTION_TYPE = 11;

	/**
	 * The meta object id for the '{@link electrickery.common.lengthUnit <em>length Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.lengthUnit
	 * @see electrickery.common.impl.CommonPackageImpl#getlengthUnit()
	 * @generated
	 */
	int LENGTH_UNIT = 12;

	/**
	 * The meta object id for the '{@link electrickery.common.tripAction <em>trip Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.tripAction
	 * @see electrickery.common.impl.CommonPackageImpl#gettripAction()
	 * @generated
	 */
	int TRIP_ACTION = 13;


	/**
	 * The meta object id for the '{@link electrickery.common.yBuildOption <em>yBuild Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.yBuildOption
	 * @see electrickery.common.impl.CommonPackageImpl#getyBuildOption()
	 * @generated
	 */
	int YBUILD_OPTION = 14;


	/**
	 * The meta object id for the '{@link electrickery.common.controlModeType <em>control Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.controlModeType
	 * @see electrickery.common.impl.CommonPackageImpl#getcontrolModeType()
	 * @generated
	 */
	int CONTROL_MODE_TYPE = 15;


	/**
	 * The meta object id for the '{@link electrickery.common.algorithmType <em>algorithm Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.common.algorithmType
	 * @see electrickery.common.impl.CommonPackageImpl#getalgorithmType()
	 * @generated
	 */
	int ALGORITHM_TYPE = 16;


	/**
	 * Returns the meta object for class '{@link electrickery.common.Circuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Circuit</em>'.
	 * @see electrickery.common.Circuit
	 * @generated
	 */
	EClass getCircuit();

	/**
	 * Returns the meta object for the container reference '{@link electrickery.common.Circuit#getExecutive <em>Executive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Executive</em>'.
	 * @see electrickery.common.Circuit#getExecutive()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Executive();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Circuit#getSolution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Solution</em>'.
	 * @see electrickery.common.Circuit#getSolution()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Solution();

	/**
	 * Returns the meta object for the containment reference list '{@link electrickery.common.Circuit#getBuses <em>Buses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Buses</em>'.
	 * @see electrickery.common.Circuit#getBuses()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Buses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getMapNodeToBus <em>Map Node To Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Map Node To Bus</em>'.
	 * @see electrickery.common.Circuit#getMapNodeToBus()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_MapNodeToBus();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.common.Circuit#getBusList <em>Bus List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bus List</em>'.
	 * @see electrickery.common.Circuit#getBusList()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_BusList();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#isBusNameRedefined <em>Bus Name Redefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Name Redefined</em>'.
	 * @see electrickery.common.Circuit#isBusNameRedefined()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_BusNameRedefined();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#isSolved <em>Solved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solved</em>'.
	 * @see electrickery.common.Circuit#isSolved()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_Solved();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getLoadMultiplier <em>Load Multiplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Multiplier</em>'.
	 * @see electrickery.common.Circuit#getLoadMultiplier()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_LoadMultiplier();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getDefaultGrowthFactor <em>Default Growth Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Growth Factor</em>'.
	 * @see electrickery.common.Circuit#getDefaultGrowthFactor()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_DefaultGrowthFactor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getDefaultGrowthRate <em>Default Growth Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Growth Rate</em>'.
	 * @see electrickery.common.Circuit#getDefaultGrowthRate()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_DefaultGrowthRate();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generator Dispatch Reference</em>'.
	 * @see electrickery.common.Circuit#getGeneratorDispatchReference()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_GeneratorDispatchReference();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getGenMultiplier <em>Gen Multiplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gen Multiplier</em>'.
	 * @see electrickery.common.Circuit#getGenMultiplier()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_GenMultiplier();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getDefaultHourMult <em>Default Hour Mult</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Hour Mult</em>'.
	 * @see electrickery.common.Circuit#getDefaultHourMult()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_DefaultHourMult();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#isControl_busNameRedefined <em>Control bus Name Redefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control bus Name Redefined</em>'.
	 * @see electrickery.common.Circuit#isControl_busNameRedefined()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_Control_busNameRedefined();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getPriceSignal <em>Price Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price Signal</em>'.
	 * @see electrickery.common.Circuit#getPriceSignal()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_PriceSignal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getEmergMinVolts <em>Emerg Min Volts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg Min Volts</em>'.
	 * @see electrickery.common.Circuit#getEmergMinVolts()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_EmergMinVolts();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getEmergMaxVolts <em>Emerg Max Volts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg Max Volts</em>'.
	 * @see electrickery.common.Circuit#getEmergMaxVolts()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_EmergMaxVolts();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getNormalMinVolts <em>Normal Min Volts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Normal Min Volts</em>'.
	 * @see electrickery.common.Circuit#getNormalMinVolts()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_NormalMinVolts();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getNormalMaxVolts <em>Normal Max Volts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Normal Max Volts</em>'.
	 * @see electrickery.common.Circuit#getNormalMaxVolts()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_NormalMaxVolts();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#isLogEvents <em>Log Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log Events</em>'.
	 * @see electrickery.common.Circuit#isLogEvents()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_LogEvents();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#isMeterZonesComputed <em>Meter Zones Computed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Meter Zones Computed</em>'.
	 * @see electrickery.common.Circuit#isMeterZonesComputed()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_MeterZonesComputed();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#isZonesLocked <em>Zones Locked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zones Locked</em>'.
	 * @see electrickery.common.Circuit#isZonesLocked()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_ZonesLocked();

	/**
	 * Returns the meta object for class '{@link electrickery.common.YMatrix <em>YMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>YMatrix</em>'.
	 * @see electrickery.common.YMatrix
	 * @generated
	 */
	EClass getYMatrix();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.YMatrix#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Circuit</em>'.
	 * @see electrickery.common.YMatrix#getCircuit()
	 * @see #getYMatrix()
	 * @generated
	 */
	EReference getYMatrix_Circuit();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getNumNodes <em>Num Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Nodes</em>'.
	 * @see electrickery.common.Circuit#getNumNodes()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_NumNodes();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getNumBuses <em>Num Buses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Buses</em>'.
	 * @see electrickery.common.Circuit#getNumBuses()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_NumBuses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Circuit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see electrickery.common.Circuit#getName()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link electrickery.common.Circuit#getVoltageSources <em>Voltage Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Voltage Sources</em>'.
	 * @see electrickery.common.Circuit#getVoltageSources()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_VoltageSources();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.common.Circuit#getCurrentSources <em>Current Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Current Sources</em>'.
	 * @see electrickery.common.Circuit#getCurrentSources()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_CurrentSources();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.common.Circuit#getGenerators <em>Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generators</em>'.
	 * @see electrickery.common.Circuit#getGenerators()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Generators();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.common.Circuit#getLoads <em>Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Loads</em>'.
	 * @see electrickery.common.Circuit#getLoads()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Loads();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Circuit#getActiveCircuitElement <em>Active Circuit Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Circuit Element</em>'.
	 * @see electrickery.common.Circuit#getActiveCircuitElement()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_ActiveCircuitElement();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Bus <em>Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bus</em>'.
	 * @see electrickery.common.Bus
	 * @generated
	 */
	EClass getBus();

	/**
	 * Returns the meta object for the container reference '{@link electrickery.common.Bus#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Circuit</em>'.
	 * @see electrickery.common.Bus#getCircuit()
	 * @see #getBus()
	 * @generated
	 */
	EReference getBus_Circuit();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Bus#getVBus <em>VBus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>VBus</em>'.
	 * @see electrickery.common.Bus#getVBus()
	 * @see #getBus()
	 * @generated
	 */
	EReference getBus_VBus();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Bus#getBusCurrent <em>Bus Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bus Current</em>'.
	 * @see electrickery.common.Bus#getBusCurrent()
	 * @see #getBus()
	 * @generated
	 */
	EReference getBus_BusCurrent();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#getNumNodesThisBus <em>Num Nodes This Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Nodes This Bus</em>'.
	 * @see electrickery.common.Bus#getNumNodesThisBus()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_NumNodesThisBus();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#getKVBase <em>KV Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Base</em>'.
	 * @see electrickery.common.Bus#getKVBase()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_KVBase();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see electrickery.common.Bus#getX()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_X();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see electrickery.common.Bus#getY()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_Y();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#isCoordDefined <em>Coord Defined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coord Defined</em>'.
	 * @see electrickery.common.Bus#isCoordDefined()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_CoordDefined();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#isKeep <em>Keep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Keep</em>'.
	 * @see electrickery.common.Bus#isKeep()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_Keep();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#getBusRef <em>Bus Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Ref</em>'.
	 * @see electrickery.common.Bus#getBusRef()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_BusRef();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Bus#getNodeNum <em>Node Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Num</em>'.
	 * @see electrickery.common.Bus#getNodeNum()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_NodeNum();

	/**
	 * Returns the meta object for class '{@link electrickery.common.CircuitElement <em>Circuit Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Circuit Element</em>'.
	 * @see electrickery.common.CircuitElement
	 * @generated
	 */
	EClass getCircuitElement();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see electrickery.common.CircuitElement#getName()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see electrickery.common.CircuitElement#isEnabled()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getBaseFreq <em>Base Freq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Freq</em>'.
	 * @see electrickery.common.CircuitElement#getBaseFreq()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_BaseFreq();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getYOrder <em>YOrder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YOrder</em>'.
	 * @see electrickery.common.CircuitElement#getYOrder()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YOrder();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#isYPrimInvalid <em>YPrim Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim Invalid</em>'.
	 * @see electrickery.common.CircuitElement#isYPrimInvalid()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrimInvalid();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getLastTerminalChecked <em>Last Terminal Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Terminal Checked</em>'.
	 * @see electrickery.common.CircuitElement#getLastTerminalChecked()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_LastTerminalChecked();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.common.CircuitElement#getTerminals <em>Terminals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Terminals</em>'.
	 * @see electrickery.common.CircuitElement#getTerminals()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_Terminals();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.CircuitElement#getActiveTerminal <em>Active Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Terminal</em>'.
	 * @see electrickery.common.CircuitElement#getActiveTerminal()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_ActiveTerminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getActiveTerminalIndex <em>Active Terminal Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Terminal Index</em>'.
	 * @see electrickery.common.CircuitElement#getActiveTerminalIndex()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_ActiveTerminalIndex();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getNTerms <em>NTerms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NTerms</em>'.
	 * @see electrickery.common.CircuitElement#getNTerms()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NTerms();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getNConds <em>NConds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NConds</em>'.
	 * @see electrickery.common.CircuitElement#getNConds()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NConds();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getNPhases <em>NPhases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPhases</em>'.
	 * @see electrickery.common.CircuitElement#getNPhases()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NPhases();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.CircuitElement#getYPrimSeries <em>YPrim Series</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>YPrim Series</em>'.
	 * @see electrickery.common.CircuitElement#getYPrimSeries()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_YPrimSeries();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.CircuitElement#getYPrimShunt <em>YPrim Shunt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>YPrim Shunt</em>'.
	 * @see electrickery.common.CircuitElement#getYPrimShunt()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_YPrimShunt();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.CircuitElement#getYPrim <em>YPrim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>YPrim</em>'.
	 * @see electrickery.common.CircuitElement#getYPrim()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_YPrim();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getYPrimFreq <em>YPrim Freq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim Freq</em>'.
	 * @see electrickery.common.CircuitElement#getYPrimFreq()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrimFreq();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.CircuitElement#getNodeRef <em>Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Ref</em>'.
	 * @see electrickery.common.CircuitElement#getNodeRef()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NodeRef();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Conductor <em>Conductor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conductor</em>'.
	 * @see electrickery.common.Conductor
	 * @generated
	 */
	EClass getConductor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Conductor#isClosed <em>Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Closed</em>'.
	 * @see electrickery.common.Conductor#isClosed()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_Closed();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Conductor#isFuseBlown <em>Fuse Blown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fuse Blown</em>'.
	 * @see electrickery.common.Conductor#isFuseBlown()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_FuseBlown();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Conductor#getAccumISqT <em>Accum ISq T</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accum ISq T</em>'.
	 * @see electrickery.common.Conductor#getAccumISqT()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_AccumISqT();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Conductor#getTccName <em>Tcc Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tcc Name</em>'.
	 * @see electrickery.common.Conductor#getTccName()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_TccName();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Solution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Solution</em>'.
	 * @see electrickery.common.Solution
	 * @generated
	 */
	EClass getSolution();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Y</em>'.
	 * @see electrickery.common.Solution#getY()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Y();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getYSystem <em>YSystem</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>YSystem</em>'.
	 * @see electrickery.common.Solution#getYSystem()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_YSystem();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getYSeries <em>YSeries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>YSeries</em>'.
	 * @see electrickery.common.Solution#getYSeries()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_YSeries();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see electrickery.common.Solution#getYear()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Year();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isPreserveNodeVoltages <em>Preserve Node Voltages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preserve Node Voltages</em>'.
	 * @see electrickery.common.Solution#isPreserveNodeVoltages()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_PreserveNodeVoltages();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isFrequencyChanged <em>Frequency Changed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency Changed</em>'.
	 * @see electrickery.common.Solution#isFrequencyChanged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_FrequencyChanged();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see electrickery.common.Solution#getFrequency()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see electrickery.common.Solution#getMode()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Mode();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Circuit</em>'.
	 * @see electrickery.common.Solution#getCircuit()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Circuit();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isSolutionInitialised <em>Solution Initialised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solution Initialised</em>'.
	 * @see electrickery.common.Solution#isSolutionInitialised()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SolutionInitialised();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isSeriesYInvalid <em>Series YInvalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Series YInvalid</em>'.
	 * @see electrickery.common.Solution#isSeriesYInvalid()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SeriesYInvalid();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isSystemYChanged <em>System YChanged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System YChanged</em>'.
	 * @see electrickery.common.Solution#isSystemYChanged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SystemYChanged();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getLoadModel <em>Load Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Model</em>'.
	 * @see electrickery.common.Solution#getLoadModel()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_LoadModel();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isVoltageBaseChanged <em>Voltage Base Changed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Voltage Base Changed</em>'.
	 * @see electrickery.common.Solution#isVoltageBaseChanged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_VoltageBaseChanged();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isHarmonicModel <em>Harmonic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Harmonic Model</em>'.
	 * @see electrickery.common.Solution#isHarmonicModel()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_HarmonicModel();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isDynamicModel <em>Dynamic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic Model</em>'.
	 * @see electrickery.common.Solution#isDynamicModel()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_DynamicModel();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isUseAuxillaryCurrents <em>Use Auxillary Currents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Auxillary Currents</em>'.
	 * @see electrickery.common.Solution#isUseAuxillaryCurrents()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_UseAuxillaryCurrents();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isLoadsNeedUpdating <em>Loads Need Updating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loads Need Updating</em>'.
	 * @see electrickery.common.Solution#isLoadsNeedUpdating()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_LoadsNeedUpdating();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getIteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Iteration</em>'.
	 * @see electrickery.common.Solution#getIteration()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Iteration();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getMaxIterations <em>Max Iterations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Iterations</em>'.
	 * @see electrickery.common.Solution#getMaxIterations()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MaxIterations();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getMaxError <em>Max Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Error</em>'.
	 * @see electrickery.common.Solution#getMaxError()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MaxError();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getConvergenceTolerance <em>Convergence Tolerance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Convergence Tolerance</em>'.
	 * @see electrickery.common.Solution#getConvergenceTolerance()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ConvergenceTolerance();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isConverged <em>Converged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Converged</em>'.
	 * @see electrickery.common.Solution#isConverged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Converged();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getControlIteration <em>Control Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Iteration</em>'.
	 * @see electrickery.common.Solution#getControlIteration()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ControlIteration();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getMaxControlIterations <em>Max Control Iterations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Control Iterations</em>'.
	 * @see electrickery.common.Solution#getMaxControlIterations()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MaxControlIterations();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getControlMode <em>Control Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Mode</em>'.
	 * @see electrickery.common.Solution#getControlMode()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ControlMode();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isControlActionsDone <em>Control Actions Done</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Actions Done</em>'.
	 * @see electrickery.common.Solution#isControlActionsDone()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ControlActionsDone();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getMostIterationsDone <em>Most Iterations Done</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Most Iterations Done</em>'.
	 * @see electrickery.common.Solution#getMostIterationsDone()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MostIterationsDone();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see electrickery.common.Solution#getAlgorithm()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Algorithm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#isLastSolutionWasDirect <em>Last Solution Was Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Solution Was Direct</em>'.
	 * @see electrickery.common.Solution#isLastSolutionWasDirect()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_LastSolutionWasDirect();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Solution#getSolutionCount <em>Solution Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solution Count</em>'.
	 * @see electrickery.common.Solution#getSolutionCount()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SolutionCount();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getNodeV <em>Node V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node V</em>'.
	 * @see electrickery.common.Solution#getNodeV()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_NodeV();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getNodeVBase <em>Node VBase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node VBase</em>'.
	 * @see electrickery.common.Solution#getNodeVBase()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_NodeVBase();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getErrorSaved <em>Error Saved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Error Saved</em>'.
	 * @see electrickery.common.Solution#getErrorSaved()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_ErrorSaved();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getVMagSaved <em>VMag Saved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>VMag Saved</em>'.
	 * @see electrickery.common.Solution#getVMagSaved()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_VMagSaved();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getCurrents <em>Currents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Currents</em>'.
	 * @see electrickery.common.Solution#getCurrents()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Currents();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Solution#getAlgorithms <em>Algorithms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Algorithms</em>'.
	 * @see electrickery.common.Solution#getAlgorithms()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Algorithms();

	/**
	 * Returns the meta object for class '{@link electrickery.common.SolutionAlgs <em>Solution Algs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Solution Algs</em>'.
	 * @see electrickery.common.SolutionAlgs
	 * @generated
	 */
	EClass getSolutionAlgs();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Terminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Terminal</em>'.
	 * @see electrickery.common.Terminal
	 * @generated
	 */
	EClass getTerminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Terminal#getBusRef <em>Bus Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Ref</em>'.
	 * @see electrickery.common.Terminal#getBusRef()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_BusRef();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.common.Terminal#getTermNodeRef <em>Term Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Term Node Ref</em>'.
	 * @see electrickery.common.Terminal#getTermNodeRef()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_TermNodeRef();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.common.Terminal#getConductors <em>Conductors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Conductors</em>'.
	 * @see electrickery.common.Terminal#getConductors()
	 * @see #getTerminal()
	 * @generated
	 */
	EReference getTerminal_Conductors();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Terminal#isChecked <em>Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Checked</em>'.
	 * @see electrickery.common.Terminal#isChecked()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_Checked();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Terminal#getNCond <em>NCond</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NCond</em>'.
	 * @see electrickery.common.Terminal#getNCond()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_NCond();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Terminal#getActiveConductor <em>Active Conductor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Conductor</em>'.
	 * @see electrickery.common.Terminal#getActiveConductor()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_ActiveConductor();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Named <em>Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named</em>'.
	 * @see electrickery.common.Named
	 * @generated
	 */
	EClass getNamed();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Named#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see electrickery.common.Named#getName()
	 * @see #getNamed()
	 * @generated
	 */
	EAttribute getNamed_Name();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Globals <em>Globals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Globals</em>'.
	 * @see electrickery.common.Globals
	 * @generated
	 */
	EClass getGlobals();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Globals#isSolutionAbort <em>Solution Abort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solution Abort</em>'.
	 * @see electrickery.common.Globals#isSolutionAbort()
	 * @see #getGlobals()
	 * @generated
	 */
	EAttribute getGlobals_SolutionAbort();

	/**
	 * Returns the meta object for the reference '{@link electrickery.common.Globals#getExecutives <em>Executives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executives</em>'.
	 * @see electrickery.common.Globals#getExecutives()
	 * @see #getGlobals()
	 * @generated
	 */
	EReference getGlobals_Executives();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Globals#isSolutionWasAttempted <em>Solution Was Attempted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solution Was Attempted</em>'.
	 * @see electrickery.common.Globals#isSolutionWasAttempted()
	 * @see #getGlobals()
	 * @generated
	 */
	EAttribute getGlobals_SolutionWasAttempted();

	/**
	 * Returns the meta object for class '{@link electrickery.common.Parser <em>Parser</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parser</em>'.
	 * @see electrickery.common.Parser
	 * @generated
	 */
	EClass getParser();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.common.Parser#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Token</em>'.
	 * @see electrickery.common.Parser#getToken()
	 * @see #getParser()
	 * @generated
	 */
	EAttribute getParser_Token();

	/**
	 * Returns the meta object for enum '{@link electrickery.common.connectionType <em>connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>connection Type</em>'.
	 * @see electrickery.common.connectionType
	 * @generated
	 */
	EEnum getconnectionType();

	/**
	 * Returns the meta object for enum '{@link electrickery.common.lengthUnit <em>length Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>length Unit</em>'.
	 * @see electrickery.common.lengthUnit
	 * @generated
	 */
	EEnum getlengthUnit();

	/**
	 * Returns the meta object for enum '{@link electrickery.common.tripAction <em>trip Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>trip Action</em>'.
	 * @see electrickery.common.tripAction
	 * @generated
	 */
	EEnum gettripAction();

	/**
	 * Returns the meta object for enum '{@link electrickery.common.yBuildOption <em>yBuild Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>yBuild Option</em>'.
	 * @see electrickery.common.yBuildOption
	 * @generated
	 */
	EEnum getyBuildOption();

	/**
	 * Returns the meta object for enum '{@link electrickery.common.controlModeType <em>control Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>control Mode Type</em>'.
	 * @see electrickery.common.controlModeType
	 * @generated
	 */
	EEnum getcontrolModeType();

	/**
	 * Returns the meta object for enum '{@link electrickery.common.algorithmType <em>algorithm Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>algorithm Type</em>'.
	 * @see electrickery.common.algorithmType
	 * @generated
	 */
	EEnum getalgorithmType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CommonFactory getCommonFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link electrickery.common.impl.CircuitImpl <em>Circuit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.CircuitImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getCircuit()
		 * @generated
		 */
		EClass CIRCUIT = eINSTANCE.getCircuit();

		/**
		 * The meta object literal for the '<em><b>Executive</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__EXECUTIVE = eINSTANCE.getCircuit_Executive();

		/**
		 * The meta object literal for the '<em><b>Solution</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__SOLUTION = eINSTANCE.getCircuit_Solution();

		/**
		 * The meta object literal for the '<em><b>Buses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__BUSES = eINSTANCE.getCircuit_Buses();

		/**
		 * The meta object literal for the '<em><b>Map Node To Bus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__MAP_NODE_TO_BUS = eINSTANCE.getCircuit_MapNodeToBus();

		/**
		 * The meta object literal for the '<em><b>Bus List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__BUS_LIST = eINSTANCE.getCircuit_BusList();

		/**
		 * The meta object literal for the '<em><b>Bus Name Redefined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__BUS_NAME_REDEFINED = eINSTANCE.getCircuit_BusNameRedefined();

		/**
		 * The meta object literal for the '<em><b>Solved</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__SOLVED = eINSTANCE.getCircuit_Solved();

		/**
		 * The meta object literal for the '<em><b>Load Multiplier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__LOAD_MULTIPLIER = eINSTANCE.getCircuit_LoadMultiplier();

		/**
		 * The meta object literal for the '<em><b>Default Growth Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__DEFAULT_GROWTH_FACTOR = eINSTANCE.getCircuit_DefaultGrowthFactor();

		/**
		 * The meta object literal for the '<em><b>Default Growth Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__DEFAULT_GROWTH_RATE = eINSTANCE.getCircuit_DefaultGrowthRate();

		/**
		 * The meta object literal for the '<em><b>Generator Dispatch Reference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__GENERATOR_DISPATCH_REFERENCE = eINSTANCE.getCircuit_GeneratorDispatchReference();

		/**
		 * The meta object literal for the '<em><b>Gen Multiplier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__GEN_MULTIPLIER = eINSTANCE.getCircuit_GenMultiplier();

		/**
		 * The meta object literal for the '<em><b>Default Hour Mult</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__DEFAULT_HOUR_MULT = eINSTANCE.getCircuit_DefaultHourMult();

		/**
		 * The meta object literal for the '<em><b>Control bus Name Redefined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__CONTROL_BUS_NAME_REDEFINED = eINSTANCE.getCircuit_Control_busNameRedefined();

		/**
		 * The meta object literal for the '<em><b>Price Signal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__PRICE_SIGNAL = eINSTANCE.getCircuit_PriceSignal();

		/**
		 * The meta object literal for the '<em><b>Emerg Min Volts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__EMERG_MIN_VOLTS = eINSTANCE.getCircuit_EmergMinVolts();

		/**
		 * The meta object literal for the '<em><b>Emerg Max Volts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__EMERG_MAX_VOLTS = eINSTANCE.getCircuit_EmergMaxVolts();

		/**
		 * The meta object literal for the '<em><b>Normal Min Volts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__NORMAL_MIN_VOLTS = eINSTANCE.getCircuit_NormalMinVolts();

		/**
		 * The meta object literal for the '<em><b>Normal Max Volts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__NORMAL_MAX_VOLTS = eINSTANCE.getCircuit_NormalMaxVolts();

		/**
		 * The meta object literal for the '<em><b>Log Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__LOG_EVENTS = eINSTANCE.getCircuit_LogEvents();

		/**
		 * The meta object literal for the '<em><b>Meter Zones Computed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__METER_ZONES_COMPUTED = eINSTANCE.getCircuit_MeterZonesComputed();

		/**
		 * The meta object literal for the '<em><b>Zones Locked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__ZONES_LOCKED = eINSTANCE.getCircuit_ZonesLocked();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.YMatrixImpl <em>YMatrix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.YMatrixImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getYMatrix()
		 * @generated
		 */
		EClass YMATRIX = eINSTANCE.getYMatrix();

		/**
		 * The meta object literal for the '<em><b>Circuit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference YMATRIX__CIRCUIT = eINSTANCE.getYMatrix_Circuit();

		/**
		 * The meta object literal for the '<em><b>Num Nodes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__NUM_NODES = eINSTANCE.getCircuit_NumNodes();

		/**
		 * The meta object literal for the '<em><b>Num Buses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__NUM_BUSES = eINSTANCE.getCircuit_NumBuses();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__NAME = eINSTANCE.getCircuit_Name();

		/**
		 * The meta object literal for the '<em><b>Voltage Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__VOLTAGE_SOURCES = eINSTANCE.getCircuit_VoltageSources();

		/**
		 * The meta object literal for the '<em><b>Current Sources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__CURRENT_SOURCES = eINSTANCE.getCircuit_CurrentSources();

		/**
		 * The meta object literal for the '<em><b>Generators</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__GENERATORS = eINSTANCE.getCircuit_Generators();

		/**
		 * The meta object literal for the '<em><b>Loads</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__LOADS = eINSTANCE.getCircuit_Loads();

		/**
		 * The meta object literal for the '<em><b>Active Circuit Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__ACTIVE_CIRCUIT_ELEMENT = eINSTANCE.getCircuit_ActiveCircuitElement();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.BusImpl <em>Bus</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.BusImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getBus()
		 * @generated
		 */
		EClass BUS = eINSTANCE.getBus();

		/**
		 * The meta object literal for the '<em><b>Circuit</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUS__CIRCUIT = eINSTANCE.getBus_Circuit();

		/**
		 * The meta object literal for the '<em><b>VBus</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUS__VBUS = eINSTANCE.getBus_VBus();

		/**
		 * The meta object literal for the '<em><b>Bus Current</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUS__BUS_CURRENT = eINSTANCE.getBus_BusCurrent();

		/**
		 * The meta object literal for the '<em><b>Num Nodes This Bus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__NUM_NODES_THIS_BUS = eINSTANCE.getBus_NumNodesThisBus();

		/**
		 * The meta object literal for the '<em><b>KV Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__KV_BASE = eINSTANCE.getBus_KVBase();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__X = eINSTANCE.getBus_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__Y = eINSTANCE.getBus_Y();

		/**
		 * The meta object literal for the '<em><b>Coord Defined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__COORD_DEFINED = eINSTANCE.getBus_CoordDefined();

		/**
		 * The meta object literal for the '<em><b>Keep</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__KEEP = eINSTANCE.getBus_Keep();

		/**
		 * The meta object literal for the '<em><b>Bus Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__BUS_REF = eINSTANCE.getBus_BusRef();

		/**
		 * The meta object literal for the '<em><b>Node Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__NODE_NUM = eINSTANCE.getBus_NodeNum();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.CircuitElementImpl <em>Circuit Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.CircuitElementImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getCircuitElement()
		 * @generated
		 */
		EClass CIRCUIT_ELEMENT = eINSTANCE.getCircuitElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__NAME = eINSTANCE.getCircuitElement_Name();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__ENABLED = eINSTANCE.getCircuitElement_Enabled();

		/**
		 * The meta object literal for the '<em><b>Base Freq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__BASE_FREQ = eINSTANCE.getCircuitElement_BaseFreq();

		/**
		 * The meta object literal for the '<em><b>YOrder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YORDER = eINSTANCE.getCircuitElement_YOrder();

		/**
		 * The meta object literal for the '<em><b>YPrim Invalid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YPRIM_INVALID = eINSTANCE.getCircuitElement_YPrimInvalid();

		/**
		 * The meta object literal for the '<em><b>Last Terminal Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED = eINSTANCE.getCircuitElement_LastTerminalChecked();

		/**
		 * The meta object literal for the '<em><b>Terminals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT_ELEMENT__TERMINALS = eINSTANCE.getCircuitElement_Terminals();

		/**
		 * The meta object literal for the '<em><b>Active Terminal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT_ELEMENT__ACTIVE_TERMINAL = eINSTANCE.getCircuitElement_ActiveTerminal();

		/**
		 * The meta object literal for the '<em><b>Active Terminal Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX = eINSTANCE.getCircuitElement_ActiveTerminalIndex();

		/**
		 * The meta object literal for the '<em><b>NTerms</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__NTERMS = eINSTANCE.getCircuitElement_NTerms();

		/**
		 * The meta object literal for the '<em><b>NConds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__NCONDS = eINSTANCE.getCircuitElement_NConds();

		/**
		 * The meta object literal for the '<em><b>NPhases</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__NPHASES = eINSTANCE.getCircuitElement_NPhases();

		/**
		 * The meta object literal for the '<em><b>YPrim Series</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT_ELEMENT__YPRIM_SERIES = eINSTANCE.getCircuitElement_YPrimSeries();

		/**
		 * The meta object literal for the '<em><b>YPrim Shunt</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT_ELEMENT__YPRIM_SHUNT = eINSTANCE.getCircuitElement_YPrimShunt();

		/**
		 * The meta object literal for the '<em><b>YPrim</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT_ELEMENT__YPRIM = eINSTANCE.getCircuitElement_YPrim();

		/**
		 * The meta object literal for the '<em><b>YPrim Freq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YPRIM_FREQ = eINSTANCE.getCircuitElement_YPrimFreq();

		/**
		 * The meta object literal for the '<em><b>Node Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__NODE_REF = eINSTANCE.getCircuitElement_NodeRef();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.ConductorImpl <em>Conductor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.ConductorImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getConductor()
		 * @generated
		 */
		EClass CONDUCTOR = eINSTANCE.getConductor();

		/**
		 * The meta object literal for the '<em><b>Closed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDUCTOR__CLOSED = eINSTANCE.getConductor_Closed();

		/**
		 * The meta object literal for the '<em><b>Fuse Blown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDUCTOR__FUSE_BLOWN = eINSTANCE.getConductor_FuseBlown();

		/**
		 * The meta object literal for the '<em><b>Accum ISq T</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDUCTOR__ACCUM_ISQ_T = eINSTANCE.getConductor_AccumISqT();

		/**
		 * The meta object literal for the '<em><b>Tcc Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDUCTOR__TCC_NAME = eINSTANCE.getConductor_TccName();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.SolutionImpl <em>Solution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.SolutionImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getSolution()
		 * @generated
		 */
		EClass SOLUTION = eINSTANCE.getSolution();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__Y = eINSTANCE.getSolution_Y();

		/**
		 * The meta object literal for the '<em><b>YSystem</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__YSYSTEM = eINSTANCE.getSolution_YSystem();

		/**
		 * The meta object literal for the '<em><b>YSeries</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__YSERIES = eINSTANCE.getSolution_YSeries();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__YEAR = eINSTANCE.getSolution_Year();

		/**
		 * The meta object literal for the '<em><b>Preserve Node Voltages</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__PRESERVE_NODE_VOLTAGES = eINSTANCE.getSolution_PreserveNodeVoltages();

		/**
		 * The meta object literal for the '<em><b>Frequency Changed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__FREQUENCY_CHANGED = eINSTANCE.getSolution_FrequencyChanged();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__FREQUENCY = eINSTANCE.getSolution_Frequency();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__MODE = eINSTANCE.getSolution_Mode();

		/**
		 * The meta object literal for the '<em><b>Circuit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__CIRCUIT = eINSTANCE.getSolution_Circuit();

		/**
		 * The meta object literal for the '<em><b>Solution Initialised</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__SOLUTION_INITIALISED = eINSTANCE.getSolution_SolutionInitialised();

		/**
		 * The meta object literal for the '<em><b>Series YInvalid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__SERIES_YINVALID = eINSTANCE.getSolution_SeriesYInvalid();

		/**
		 * The meta object literal for the '<em><b>System YChanged</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__SYSTEM_YCHANGED = eINSTANCE.getSolution_SystemYChanged();

		/**
		 * The meta object literal for the '<em><b>Load Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__LOAD_MODEL = eINSTANCE.getSolution_LoadModel();

		/**
		 * The meta object literal for the '<em><b>Voltage Base Changed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__VOLTAGE_BASE_CHANGED = eINSTANCE.getSolution_VoltageBaseChanged();

		/**
		 * The meta object literal for the '<em><b>Harmonic Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__HARMONIC_MODEL = eINSTANCE.getSolution_HarmonicModel();

		/**
		 * The meta object literal for the '<em><b>Dynamic Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__DYNAMIC_MODEL = eINSTANCE.getSolution_DynamicModel();

		/**
		 * The meta object literal for the '<em><b>Use Auxillary Currents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__USE_AUXILLARY_CURRENTS = eINSTANCE.getSolution_UseAuxillaryCurrents();

		/**
		 * The meta object literal for the '<em><b>Loads Need Updating</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__LOADS_NEED_UPDATING = eINSTANCE.getSolution_LoadsNeedUpdating();

		/**
		 * The meta object literal for the '<em><b>Iteration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__ITERATION = eINSTANCE.getSolution_Iteration();

		/**
		 * The meta object literal for the '<em><b>Max Iterations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__MAX_ITERATIONS = eINSTANCE.getSolution_MaxIterations();

		/**
		 * The meta object literal for the '<em><b>Max Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__MAX_ERROR = eINSTANCE.getSolution_MaxError();

		/**
		 * The meta object literal for the '<em><b>Convergence Tolerance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CONVERGENCE_TOLERANCE = eINSTANCE.getSolution_ConvergenceTolerance();

		/**
		 * The meta object literal for the '<em><b>Converged</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CONVERGED = eINSTANCE.getSolution_Converged();

		/**
		 * The meta object literal for the '<em><b>Control Iteration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CONTROL_ITERATION = eINSTANCE.getSolution_ControlIteration();

		/**
		 * The meta object literal for the '<em><b>Max Control Iterations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__MAX_CONTROL_ITERATIONS = eINSTANCE.getSolution_MaxControlIterations();

		/**
		 * The meta object literal for the '<em><b>Control Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CONTROL_MODE = eINSTANCE.getSolution_ControlMode();

		/**
		 * The meta object literal for the '<em><b>Control Actions Done</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CONTROL_ACTIONS_DONE = eINSTANCE.getSolution_ControlActionsDone();

		/**
		 * The meta object literal for the '<em><b>Most Iterations Done</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__MOST_ITERATIONS_DONE = eINSTANCE.getSolution_MostIterationsDone();

		/**
		 * The meta object literal for the '<em><b>Algorithm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__ALGORITHM = eINSTANCE.getSolution_Algorithm();

		/**
		 * The meta object literal for the '<em><b>Last Solution Was Direct</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__LAST_SOLUTION_WAS_DIRECT = eINSTANCE.getSolution_LastSolutionWasDirect();

		/**
		 * The meta object literal for the '<em><b>Solution Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__SOLUTION_COUNT = eINSTANCE.getSolution_SolutionCount();

		/**
		 * The meta object literal for the '<em><b>Node V</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__NODE_V = eINSTANCE.getSolution_NodeV();

		/**
		 * The meta object literal for the '<em><b>Node VBase</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__NODE_VBASE = eINSTANCE.getSolution_NodeVBase();

		/**
		 * The meta object literal for the '<em><b>Error Saved</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__ERROR_SAVED = eINSTANCE.getSolution_ErrorSaved();

		/**
		 * The meta object literal for the '<em><b>VMag Saved</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__VMAG_SAVED = eINSTANCE.getSolution_VMagSaved();

		/**
		 * The meta object literal for the '<em><b>Currents</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__CURRENTS = eINSTANCE.getSolution_Currents();

		/**
		 * The meta object literal for the '<em><b>Algorithms</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOLUTION__ALGORITHMS = eINSTANCE.getSolution_Algorithms();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.SolutionAlgsImpl <em>Solution Algs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.SolutionAlgsImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getSolutionAlgs()
		 * @generated
		 */
		EClass SOLUTION_ALGS = eINSTANCE.getSolutionAlgs();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.TerminalImpl <em>Terminal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.TerminalImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getTerminal()
		 * @generated
		 */
		EClass TERMINAL = eINSTANCE.getTerminal();

		/**
		 * The meta object literal for the '<em><b>Bus Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERMINAL__BUS_REF = eINSTANCE.getTerminal_BusRef();

		/**
		 * The meta object literal for the '<em><b>Term Node Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERMINAL__TERM_NODE_REF = eINSTANCE.getTerminal_TermNodeRef();

		/**
		 * The meta object literal for the '<em><b>Conductors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TERMINAL__CONDUCTORS = eINSTANCE.getTerminal_Conductors();

		/**
		 * The meta object literal for the '<em><b>Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERMINAL__CHECKED = eINSTANCE.getTerminal_Checked();

		/**
		 * The meta object literal for the '<em><b>NCond</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERMINAL__NCOND = eINSTANCE.getTerminal_NCond();

		/**
		 * The meta object literal for the '<em><b>Active Conductor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERMINAL__ACTIVE_CONDUCTOR = eINSTANCE.getTerminal_ActiveConductor();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.NamedImpl <em>Named</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.NamedImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getNamed()
		 * @generated
		 */
		EClass NAMED = eINSTANCE.getNamed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED__NAME = eINSTANCE.getNamed_Name();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.GlobalsImpl <em>Globals</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.GlobalsImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getGlobals()
		 * @generated
		 */
		EClass GLOBALS = eINSTANCE.getGlobals();

		/**
		 * The meta object literal for the '<em><b>Solution Abort</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBALS__SOLUTION_ABORT = eINSTANCE.getGlobals_SolutionAbort();

		/**
		 * The meta object literal for the '<em><b>Executives</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLOBALS__EXECUTIVES = eINSTANCE.getGlobals_Executives();

		/**
		 * The meta object literal for the '<em><b>Solution Was Attempted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBALS__SOLUTION_WAS_ATTEMPTED = eINSTANCE.getGlobals_SolutionWasAttempted();

		/**
		 * The meta object literal for the '{@link electrickery.common.impl.ParserImpl <em>Parser</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.impl.ParserImpl
		 * @see electrickery.common.impl.CommonPackageImpl#getParser()
		 * @generated
		 */
		EClass PARSER = eINSTANCE.getParser();

		/**
		 * The meta object literal for the '<em><b>Token</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARSER__TOKEN = eINSTANCE.getParser_Token();

		/**
		 * The meta object literal for the '{@link electrickery.common.connectionType <em>connection Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.connectionType
		 * @see electrickery.common.impl.CommonPackageImpl#getconnectionType()
		 * @generated
		 */
		EEnum CONNECTION_TYPE = eINSTANCE.getconnectionType();

		/**
		 * The meta object literal for the '{@link electrickery.common.lengthUnit <em>length Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.lengthUnit
		 * @see electrickery.common.impl.CommonPackageImpl#getlengthUnit()
		 * @generated
		 */
		EEnum LENGTH_UNIT = eINSTANCE.getlengthUnit();

		/**
		 * The meta object literal for the '{@link electrickery.common.tripAction <em>trip Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.tripAction
		 * @see electrickery.common.impl.CommonPackageImpl#gettripAction()
		 * @generated
		 */
		EEnum TRIP_ACTION = eINSTANCE.gettripAction();

		/**
		 * The meta object literal for the '{@link electrickery.common.yBuildOption <em>yBuild Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.yBuildOption
		 * @see electrickery.common.impl.CommonPackageImpl#getyBuildOption()
		 * @generated
		 */
		EEnum YBUILD_OPTION = eINSTANCE.getyBuildOption();

		/**
		 * The meta object literal for the '{@link electrickery.common.controlModeType <em>control Mode Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.controlModeType
		 * @see electrickery.common.impl.CommonPackageImpl#getcontrolModeType()
		 * @generated
		 */
		EEnum CONTROL_MODE_TYPE = eINSTANCE.getcontrolModeType();

		/**
		 * The meta object literal for the '{@link electrickery.common.algorithmType <em>algorithm Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.common.algorithmType
		 * @see electrickery.common.impl.CommonPackageImpl#getalgorithmType()
		 * @generated
		 */
		EEnum ALGORITHM_TYPE = eINSTANCE.getalgorithmType();

	}

} //CommonPackage
