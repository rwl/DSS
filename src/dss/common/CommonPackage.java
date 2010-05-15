/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common;

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
 * @see dss.common.CommonFactory
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
	String eNS_URI = "http://www.openpowersystem.com/dss/common";

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
	CommonPackage eINSTANCE = dss.common.impl.CommonPackageImpl.init();

	/**
	 * The meta object id for the '{@link dss.common.impl.CircuitImpl <em>Circuit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.CircuitImpl
	 * @see dss.common.impl.CommonPackageImpl#getCircuit()
	 * @generated
	 */
	int CIRCUIT = 0;

	/**
	 * The feature id for the '<em><b>Solution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__SOLUTION = 0;

	/**
	 * The feature id for the '<em><b>Control Queue</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__CONTROL_QUEUE = 1;

	/**
	 * The feature id for the '<em><b>Bus List</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__BUS_LIST = 2;

	/**
	 * The feature id for the '<em><b>Faults</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__FAULTS = 3;

	/**
	 * The feature id for the '<em><b>Voltage Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__VOLTAGE_SOURCES = 4;

	/**
	 * The feature id for the '<em><b>Current Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__CURRENT_SOURCES = 5;

	/**
	 * The feature id for the '<em><b>Sensors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__SENSORS = 6;

	/**
	 * The feature id for the '<em><b>Monitors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__MONITORS = 7;

	/**
	 * The feature id for the '<em><b>Energy Meters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__ENERGY_METERS = 8;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__GENERATORS = 9;

	/**
	 * The feature id for the '<em><b>Transformers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__TRANSFORMERS = 10;

	/**
	 * The feature id for the '<em><b>Cap Controls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__CAP_CONTROLS = 11;

	/**
	 * The feature id for the '<em><b>Reg Controls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__REG_CONTROLS = 12;

	/**
	 * The feature id for the '<em><b>Lines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__LINES = 13;

	/**
	 * The feature id for the '<em><b>Loads</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__LOADS = 14;

	/**
	 * The feature id for the '<em><b>Shunt Capacitors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__SHUNT_CAPACITORS = 15;

	/**
	 * The feature id for the '<em><b>Feeder</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__FEEDER = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NAME = 17;

	/**
	 * The feature id for the '<em><b>Num Nodes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__NUM_NODES = 18;

	/**
	 * The feature id for the '<em><b>Generator Dispatch Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__GENERATOR_DISPATCH_REFERENCE = 19;

	/**
	 * The feature id for the '<em><b>Gen Multiplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__GEN_MULTIPLIER = 20;

	/**
	 * The feature id for the '<em><b>Solved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__SOLVED = 21;

	/**
	 * The feature id for the '<em><b>Bus Name Redefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__BUS_NAME_REDEFINED = 22;

	/**
	 * The feature id for the '<em><b>Control bus Name Redefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__CONTROL_BUS_NAME_REDEFINED = 23;

	/**
	 * The feature id for the '<em><b>Load Multiplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__LOAD_MULTIPLIER = 24;

	/**
	 * The feature id for the '<em><b>Default Growth Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__DEFAULT_GROWTH_FACTOR = 25;

	/**
	 * The feature id for the '<em><b>Default Hour Mult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__DEFAULT_HOUR_MULT = 26;

	/**
	 * The feature id for the '<em><b>Price Signal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT__PRICE_SIGNAL = 27;

	/**
	 * The number of structural features of the '<em>Circuit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_FEATURE_COUNT = 28;

	/**
	 * The meta object id for the '{@link dss.common.impl.BusImpl <em>Bus</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.BusImpl
	 * @see dss.common.impl.CommonPackageImpl#getBus()
	 * @generated
	 */
	int BUS = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__NAME = 0;

	/**
	 * The feature id for the '<em><b>VBus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__VBUS = 1;

	/**
	 * The feature id for the '<em><b>Bus Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__BUS_CURRENT = 2;

	/**
	 * The feature id for the '<em><b>ZSC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__ZSC = 3;

	/**
	 * The feature id for the '<em><b>YSC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__YSC = 4;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__X = 5;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__Y = 6;

	/**
	 * The feature id for the '<em><b>KV Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__KV_BASE = 7;

	/**
	 * The feature id for the '<em><b>Coords Defined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__COORDS_DEFINED = 8;

	/**
	 * The feature id for the '<em><b>Bus Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__BUS_CHECKED = 9;

	/**
	 * The feature id for the '<em><b>Keep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__KEEP = 10;

	/**
	 * The feature id for the '<em><b>Radial Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS__RADIAL_BUS = 11;

	/**
	 * The number of structural features of the '<em>Bus</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUS_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link dss.common.impl.CircuitElementImpl <em>Circuit Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.CircuitElementImpl
	 * @see dss.common.impl.CommonPackageImpl#getCircuitElement()
	 * @generated
	 */
	int CIRCUIT_ELEMENT = 2;

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
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NODE_REF = 3;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YORDER = 4;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_INVALID = 5;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED = 6;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__CHECKED = 7;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__HAS_METER = 8;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__ISOLATED = 9;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__HAS_CONTROL = 10;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__PART_OF_FEEDER = 11;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__CONTROL_ELEMENT = 12;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__TERMINALS = 13;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__ACTIVE_TERMINAL = 14;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NTERMS = 15;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NCONDS = 16;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__NPHASES = 17;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__BUS_INDEX = 18;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_SERIES = 19;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_SHUNT = 20;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM = 21;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT__YPRIM_FREQ = 22;

	/**
	 * The number of structural features of the '<em>Circuit Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CIRCUIT_ELEMENT_FEATURE_COUNT = 23;

	/**
	 * The meta object id for the '{@link dss.common.impl.CollectionImpl <em>Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.CollectionImpl
	 * @see dss.common.impl.CommonPackageImpl#getCollection()
	 * @generated
	 */
	int COLLECTION = 3;

	/**
	 * The feature id for the '<em><b>NProperties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION__NPROPERTIES = 0;

	/**
	 * The feature id for the '<em><b>Property Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION__PROPERTY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Property Help</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION__PROPERTY_HELP = 2;

	/**
	 * The feature id for the '<em><b>Property Idx Map</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION__PROPERTY_IDX_MAP = 3;

	/**
	 * The feature id for the '<em><b>Element List</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION__ELEMENT_LIST = 4;

	/**
	 * The feature id for the '<em><b>Saved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION__SAVED = 5;

	/**
	 * The number of structural features of the '<em>Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link dss.common.impl.ConductorImpl <em>Conductor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.ConductorImpl
	 * @see dss.common.impl.CommonPackageImpl#getConductor()
	 * @generated
	 */
	int CONDUCTOR = 4;

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
	 * The meta object id for the '{@link dss.common.impl.FeederImpl <em>Feeder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.FeederImpl
	 * @see dss.common.impl.CommonPackageImpl#getFeeder()
	 * @generated
	 */
	int FEEDER = 5;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEEDER__SPECTRUM = 0;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEEDER__BASE_FREQ = 1;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEEDER__ENABLED = 2;

	/**
	 * The number of structural features of the '<em>Feeder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEEDER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link dss.common.impl.SolutionImpl <em>Solution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.SolutionImpl
	 * @see dss.common.impl.CommonPackageImpl#getSolution()
	 * @generated
	 */
	int SOLUTION = 6;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__YEAR = 0;

	/**
	 * The feature id for the '<em><b>Preserve Node Voltages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__PRESERVE_NODE_VOLTAGES = 1;

	/**
	 * The feature id for the '<em><b>Frequency Changed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__FREQUENCY_CHANGED = 2;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__FREQUENCY = 3;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MODE = 4;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CIRCUIT = 5;

	/**
	 * The feature id for the '<em><b>Solution Abort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SOLUTION_ABORT = 6;

	/**
	 * The feature id for the '<em><b>Solution Initialised</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SOLUTION_INITIALISED = 7;

	/**
	 * The feature id for the '<em><b>Series YInvalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SERIES_YINVALID = 8;

	/**
	 * The feature id for the '<em><b>System YChanged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__SYSTEM_YCHANGED = 9;

	/**
	 * The feature id for the '<em><b>Load Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__LOAD_MODEL = 10;

	/**
	 * The feature id for the '<em><b>Voltage Base Changed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__VOLTAGE_BASE_CHANGED = 11;

	/**
	 * The feature id for the '<em><b>Harmonic Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__HARMONIC_MODEL = 12;

	/**
	 * The feature id for the '<em><b>Dynamic Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__DYNAMIC_MODEL = 13;

	/**
	 * The feature id for the '<em><b>Use Auxillary Currents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__USE_AUXILLARY_CURRENTS = 14;

	/**
	 * The feature id for the '<em><b>Loads Need Updating</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__LOADS_NEED_UPDATING = 15;

	/**
	 * The feature id for the '<em><b>Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ITERATION = 16;

	/**
	 * The feature id for the '<em><b>Max Iterations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MAX_ITERATIONS = 17;

	/**
	 * The feature id for the '<em><b>Max Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MAX_ERROR = 18;

	/**
	 * The feature id for the '<em><b>Convergence Tolerance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONVERGENCE_TOLERANCE = 19;

	/**
	 * The feature id for the '<em><b>Converged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONVERGED = 20;

	/**
	 * The feature id for the '<em><b>Control Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONTROL_ITERATION = 21;

	/**
	 * The feature id for the '<em><b>Max Control Iterations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MAX_CONTROL_ITERATIONS = 22;

	/**
	 * The feature id for the '<em><b>Control Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONTROL_MODE = 23;

	/**
	 * The feature id for the '<em><b>Control Actions Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CONTROL_ACTIONS_DONE = 24;

	/**
	 * The feature id for the '<em><b>Most Iterations Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__MOST_ITERATIONS_DONE = 25;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__ALGORITHM = 26;

	/**
	 * The feature id for the '<em><b>Last Solution Was Direct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__LAST_SOLUTION_WAS_DIRECT = 27;

	/**
	 * The feature id for the '<em><b>Node V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__NODE_V = 28;

	/**
	 * The feature id for the '<em><b>Currents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION__CURRENTS = 29;

	/**
	 * The number of structural features of the '<em>Solution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOLUTION_FEATURE_COUNT = 30;

	/**
	 * The meta object id for the '{@link dss.common.impl.TerminalImpl <em>Terminal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.TerminalImpl
	 * @see dss.common.impl.CommonPackageImpl#getTerminal()
	 * @generated
	 */
	int TERMINAL = 7;

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
	 * The meta object id for the '{@link dss.common.impl.ControlQueueImpl <em>Control Queue</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.impl.ControlQueueImpl
	 * @see dss.common.impl.CommonPackageImpl#getControlQueue()
	 * @generated
	 */
	int CONTROL_QUEUE = 8;

	/**
	 * The feature id for the '<em><b>Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_QUEUE__EMPTY = 0;

	/**
	 * The number of structural features of the '<em>Control Queue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_QUEUE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link dss.common.connectionType <em>connection Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.connectionType
	 * @see dss.common.impl.CommonPackageImpl#getconnectionType()
	 * @generated
	 */
	int CONNECTION_TYPE = 9;

	/**
	 * The meta object id for the '{@link dss.common.lengthUnit <em>length Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.lengthUnit
	 * @see dss.common.impl.CommonPackageImpl#getlengthUnit()
	 * @generated
	 */
	int LENGTH_UNIT = 10;

	/**
	 * The meta object id for the '{@link dss.common.tripAction <em>trip Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.tripAction
	 * @see dss.common.impl.CommonPackageImpl#gettripAction()
	 * @generated
	 */
	int TRIP_ACTION = 11;


	/**
	 * The meta object id for the '{@link dss.common.yBuildOption <em>yBuild Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.yBuildOption
	 * @see dss.common.impl.CommonPackageImpl#getyBuildOption()
	 * @generated
	 */
	int YBUILD_OPTION = 12;


	/**
	 * The meta object id for the '{@link dss.common.controlModeType <em>control Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.controlModeType
	 * @see dss.common.impl.CommonPackageImpl#getcontrolModeType()
	 * @generated
	 */
	int CONTROL_MODE_TYPE = 13;


	/**
	 * The meta object id for the '{@link dss.common.algorithmType <em>algorithm Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.common.algorithmType
	 * @see dss.common.impl.CommonPackageImpl#getalgorithmType()
	 * @generated
	 */
	int ALGORITHM_TYPE = 14;


	/**
	 * Returns the meta object for class '{@link dss.common.Circuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Circuit</em>'.
	 * @see dss.common.Circuit
	 * @generated
	 */
	EClass getCircuit();

	/**
	 * Returns the meta object for the reference '{@link dss.common.Circuit#getSolution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Solution</em>'.
	 * @see dss.common.Circuit#getSolution()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Solution();

	/**
	 * Returns the meta object for the reference '{@link dss.common.Circuit#getControlQueue <em>Control Queue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Control Queue</em>'.
	 * @see dss.common.Circuit#getControlQueue()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_ControlQueue();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getBusList <em>Bus List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bus List</em>'.
	 * @see dss.common.Circuit#getBusList()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_BusList();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getFaults <em>Faults</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Faults</em>'.
	 * @see dss.common.Circuit#getFaults()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Faults();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#isBusNameRedefined <em>Bus Name Redefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Name Redefined</em>'.
	 * @see dss.common.Circuit#isBusNameRedefined()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_BusNameRedefined();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getNumNodes <em>Num Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Nodes</em>'.
	 * @see dss.common.Circuit#getNumNodes()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_NumNodes();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see dss.common.Circuit#getName()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getVoltageSources <em>Voltage Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Voltage Sources</em>'.
	 * @see dss.common.Circuit#getVoltageSources()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_VoltageSources();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getCurrentSources <em>Current Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Current Sources</em>'.
	 * @see dss.common.Circuit#getCurrentSources()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_CurrentSources();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getSensors <em>Sensors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sensors</em>'.
	 * @see dss.common.Circuit#getSensors()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Sensors();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getMonitors <em>Monitors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Monitors</em>'.
	 * @see dss.common.Circuit#getMonitors()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Monitors();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getEnergyMeters <em>Energy Meters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Energy Meters</em>'.
	 * @see dss.common.Circuit#getEnergyMeters()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_EnergyMeters();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getGenerators <em>Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Generators</em>'.
	 * @see dss.common.Circuit#getGenerators()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Generators();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getTransformers <em>Transformers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transformers</em>'.
	 * @see dss.common.Circuit#getTransformers()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Transformers();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getCapControls <em>Cap Controls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cap Controls</em>'.
	 * @see dss.common.Circuit#getCapControls()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_CapControls();

	/**
	 * Returns the meta object for the containment reference list '{@link dss.common.Circuit#getRegControls <em>Reg Controls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reg Controls</em>'.
	 * @see dss.common.Circuit#getRegControls()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_RegControls();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.Circuit#getLines <em>Lines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Lines</em>'.
	 * @see dss.common.Circuit#getLines()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Lines();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.Circuit#getLoads <em>Loads</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Loads</em>'.
	 * @see dss.common.Circuit#getLoads()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Loads();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.Circuit#getShuntCapacitors <em>Shunt Capacitors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Shunt Capacitors</em>'.
	 * @see dss.common.Circuit#getShuntCapacitors()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_ShuntCapacitors();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.Circuit#getFeeder <em>Feeder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Feeder</em>'.
	 * @see dss.common.Circuit#getFeeder()
	 * @see #getCircuit()
	 * @generated
	 */
	EReference getCircuit_Feeder();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generator Dispatch Reference</em>'.
	 * @see dss.common.Circuit#getGeneratorDispatchReference()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_GeneratorDispatchReference();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getGenMultiplier <em>Gen Multiplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gen Multiplier</em>'.
	 * @see dss.common.Circuit#getGenMultiplier()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_GenMultiplier();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#isSolved <em>Solved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solved</em>'.
	 * @see dss.common.Circuit#isSolved()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_Solved();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#isControl_busNameRedefined <em>Control bus Name Redefined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control bus Name Redefined</em>'.
	 * @see dss.common.Circuit#isControl_busNameRedefined()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_Control_busNameRedefined();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getLoadMultiplier <em>Load Multiplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Multiplier</em>'.
	 * @see dss.common.Circuit#getLoadMultiplier()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_LoadMultiplier();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getDefaultGrowthFactor <em>Default Growth Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Growth Factor</em>'.
	 * @see dss.common.Circuit#getDefaultGrowthFactor()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_DefaultGrowthFactor();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getDefaultHourMult <em>Default Hour Mult</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Hour Mult</em>'.
	 * @see dss.common.Circuit#getDefaultHourMult()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_DefaultHourMult();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Circuit#getPriceSignal <em>Price Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price Signal</em>'.
	 * @see dss.common.Circuit#getPriceSignal()
	 * @see #getCircuit()
	 * @generated
	 */
	EAttribute getCircuit_PriceSignal();

	/**
	 * Returns the meta object for class '{@link dss.common.Bus <em>Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bus</em>'.
	 * @see dss.common.Bus
	 * @generated
	 */
	EClass getBus();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see dss.common.Bus#getName()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_Name();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getVBus <em>VBus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VBus</em>'.
	 * @see dss.common.Bus#getVBus()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_VBus();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getBusCurrent <em>Bus Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Current</em>'.
	 * @see dss.common.Bus#getBusCurrent()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_BusCurrent();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getZSC <em>ZSC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ZSC</em>'.
	 * @see dss.common.Bus#getZSC()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_ZSC();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getYSC <em>YSC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YSC</em>'.
	 * @see dss.common.Bus#getYSC()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_YSC();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see dss.common.Bus#getX()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_X();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see dss.common.Bus#getY()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_Y();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#getKVBase <em>KV Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Base</em>'.
	 * @see dss.common.Bus#getKVBase()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_KVBase();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#isCoordsDefined <em>Coords Defined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coords Defined</em>'.
	 * @see dss.common.Bus#isCoordsDefined()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_CoordsDefined();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#isBusChecked <em>Bus Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Checked</em>'.
	 * @see dss.common.Bus#isBusChecked()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_BusChecked();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#isKeep <em>Keep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Keep</em>'.
	 * @see dss.common.Bus#isKeep()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_Keep();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Bus#isRadialBus <em>Radial Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Radial Bus</em>'.
	 * @see dss.common.Bus#isRadialBus()
	 * @see #getBus()
	 * @generated
	 */
	EAttribute getBus_RadialBus();

	/**
	 * Returns the meta object for class '{@link dss.common.CircuitElement <em>Circuit Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Circuit Element</em>'.
	 * @see dss.common.CircuitElement
	 * @generated
	 */
	EClass getCircuitElement();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see dss.common.CircuitElement#getName()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see dss.common.CircuitElement#isEnabled()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getBaseFreq <em>Base Freq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Freq</em>'.
	 * @see dss.common.CircuitElement#getBaseFreq()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_BaseFreq();

	/**
	 * Returns the meta object for the attribute list '{@link dss.common.CircuitElement#getNodeRef <em>Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Node Ref</em>'.
	 * @see dss.common.CircuitElement#getNodeRef()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NodeRef();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getYOrder <em>YOrder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YOrder</em>'.
	 * @see dss.common.CircuitElement#getYOrder()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YOrder();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isYPrimInvalid <em>YPrim Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim Invalid</em>'.
	 * @see dss.common.CircuitElement#isYPrimInvalid()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrimInvalid();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getLastTerminalChecked <em>Last Terminal Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Terminal Checked</em>'.
	 * @see dss.common.CircuitElement#getLastTerminalChecked()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_LastTerminalChecked();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isChecked <em>Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Checked</em>'.
	 * @see dss.common.CircuitElement#isChecked()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_Checked();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isHasMeter <em>Has Meter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Meter</em>'.
	 * @see dss.common.CircuitElement#isHasMeter()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_HasMeter();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isIsolated <em>Isolated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Isolated</em>'.
	 * @see dss.common.CircuitElement#isIsolated()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_Isolated();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isHasControl <em>Has Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Control</em>'.
	 * @see dss.common.CircuitElement#isHasControl()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_HasControl();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#isPartOfFeeder <em>Part Of Feeder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Part Of Feeder</em>'.
	 * @see dss.common.CircuitElement#isPartOfFeeder()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_PartOfFeeder();

	/**
	 * Returns the meta object for the reference '{@link dss.common.CircuitElement#getControlElement <em>Control Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Control Element</em>'.
	 * @see dss.common.CircuitElement#getControlElement()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_ControlElement();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.CircuitElement#getTerminals <em>Terminals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Terminals</em>'.
	 * @see dss.common.CircuitElement#getTerminals()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_Terminals();

	/**
	 * Returns the meta object for the reference '{@link dss.common.CircuitElement#getActiveTerminal <em>Active Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Terminal</em>'.
	 * @see dss.common.CircuitElement#getActiveTerminal()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EReference getCircuitElement_ActiveTerminal();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getNTerms <em>NTerms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NTerms</em>'.
	 * @see dss.common.CircuitElement#getNTerms()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NTerms();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getNConds <em>NConds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NConds</em>'.
	 * @see dss.common.CircuitElement#getNConds()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NConds();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getNPhases <em>NPhases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NPhases</em>'.
	 * @see dss.common.CircuitElement#getNPhases()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_NPhases();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getBusIndex <em>Bus Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Index</em>'.
	 * @see dss.common.CircuitElement#getBusIndex()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_BusIndex();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getYPrimSeries <em>YPrim Series</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim Series</em>'.
	 * @see dss.common.CircuitElement#getYPrimSeries()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrimSeries();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getYPrimShunt <em>YPrim Shunt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim Shunt</em>'.
	 * @see dss.common.CircuitElement#getYPrimShunt()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrimShunt();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getYPrim <em>YPrim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim</em>'.
	 * @see dss.common.CircuitElement#getYPrim()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrim();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.CircuitElement#getYPrimFreq <em>YPrim Freq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPrim Freq</em>'.
	 * @see dss.common.CircuitElement#getYPrimFreq()
	 * @see #getCircuitElement()
	 * @generated
	 */
	EAttribute getCircuitElement_YPrimFreq();

	/**
	 * Returns the meta object for class '{@link dss.common.Collection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection</em>'.
	 * @see dss.common.Collection
	 * @generated
	 */
	EClass getCollection();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Collection#getNProperties <em>NProperties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NProperties</em>'.
	 * @see dss.common.Collection#getNProperties()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_NProperties();

	/**
	 * Returns the meta object for the attribute list '{@link dss.common.Collection#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Property Name</em>'.
	 * @see dss.common.Collection#getPropertyName()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_PropertyName();

	/**
	 * Returns the meta object for the attribute list '{@link dss.common.Collection#getPropertyHelp <em>Property Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Property Help</em>'.
	 * @see dss.common.Collection#getPropertyHelp()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_PropertyHelp();

	/**
	 * Returns the meta object for the attribute list '{@link dss.common.Collection#getPropertyIdxMap <em>Property Idx Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Property Idx Map</em>'.
	 * @see dss.common.Collection#getPropertyIdxMap()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_PropertyIdxMap();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.Collection#getElementList <em>Element List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Element List</em>'.
	 * @see dss.common.Collection#getElementList()
	 * @see #getCollection()
	 * @generated
	 */
	EReference getCollection_ElementList();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Collection#isSaved <em>Saved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Saved</em>'.
	 * @see dss.common.Collection#isSaved()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_Saved();

	/**
	 * Returns the meta object for class '{@link dss.common.Conductor <em>Conductor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conductor</em>'.
	 * @see dss.common.Conductor
	 * @generated
	 */
	EClass getConductor();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Conductor#isClosed <em>Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Closed</em>'.
	 * @see dss.common.Conductor#isClosed()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_Closed();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Conductor#isFuseBlown <em>Fuse Blown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fuse Blown</em>'.
	 * @see dss.common.Conductor#isFuseBlown()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_FuseBlown();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Conductor#getAccumISqT <em>Accum ISq T</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accum ISq T</em>'.
	 * @see dss.common.Conductor#getAccumISqT()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_AccumISqT();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Conductor#getTccName <em>Tcc Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tcc Name</em>'.
	 * @see dss.common.Conductor#getTccName()
	 * @see #getConductor()
	 * @generated
	 */
	EAttribute getConductor_TccName();

	/**
	 * Returns the meta object for class '{@link dss.common.Feeder <em>Feeder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feeder</em>'.
	 * @see dss.common.Feeder
	 * @generated
	 */
	EClass getFeeder();

	/**
	 * Returns the meta object for the reference '{@link dss.common.Feeder#getSpectrum <em>Spectrum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Spectrum</em>'.
	 * @see dss.common.Feeder#getSpectrum()
	 * @see #getFeeder()
	 * @generated
	 */
	EReference getFeeder_Spectrum();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Feeder#getBaseFreq <em>Base Freq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Freq</em>'.
	 * @see dss.common.Feeder#getBaseFreq()
	 * @see #getFeeder()
	 * @generated
	 */
	EAttribute getFeeder_BaseFreq();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Feeder#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see dss.common.Feeder#isEnabled()
	 * @see #getFeeder()
	 * @generated
	 */
	EAttribute getFeeder_Enabled();

	/**
	 * Returns the meta object for class '{@link dss.common.Solution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Solution</em>'.
	 * @see dss.common.Solution
	 * @generated
	 */
	EClass getSolution();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see dss.common.Solution#getYear()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Year();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isPreserveNodeVoltages <em>Preserve Node Voltages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preserve Node Voltages</em>'.
	 * @see dss.common.Solution#isPreserveNodeVoltages()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_PreserveNodeVoltages();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isFrequencyChanged <em>Frequency Changed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency Changed</em>'.
	 * @see dss.common.Solution#isFrequencyChanged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_FrequencyChanged();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see dss.common.Solution#getFrequency()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see dss.common.Solution#getMode()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Mode();

	/**
	 * Returns the meta object for the reference '{@link dss.common.Solution#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Circuit</em>'.
	 * @see dss.common.Solution#getCircuit()
	 * @see #getSolution()
	 * @generated
	 */
	EReference getSolution_Circuit();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isSolutionAbort <em>Solution Abort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solution Abort</em>'.
	 * @see dss.common.Solution#isSolutionAbort()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SolutionAbort();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isSolutionInitialised <em>Solution Initialised</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Solution Initialised</em>'.
	 * @see dss.common.Solution#isSolutionInitialised()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SolutionInitialised();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isSeriesYInvalid <em>Series YInvalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Series YInvalid</em>'.
	 * @see dss.common.Solution#isSeriesYInvalid()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SeriesYInvalid();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isSystemYChanged <em>System YChanged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System YChanged</em>'.
	 * @see dss.common.Solution#isSystemYChanged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_SystemYChanged();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getLoadModel <em>Load Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Model</em>'.
	 * @see dss.common.Solution#getLoadModel()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_LoadModel();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isVoltageBaseChanged <em>Voltage Base Changed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Voltage Base Changed</em>'.
	 * @see dss.common.Solution#isVoltageBaseChanged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_VoltageBaseChanged();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isHarmonicModel <em>Harmonic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Harmonic Model</em>'.
	 * @see dss.common.Solution#isHarmonicModel()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_HarmonicModel();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isDynamicModel <em>Dynamic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic Model</em>'.
	 * @see dss.common.Solution#isDynamicModel()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_DynamicModel();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isUseAuxillaryCurrents <em>Use Auxillary Currents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Auxillary Currents</em>'.
	 * @see dss.common.Solution#isUseAuxillaryCurrents()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_UseAuxillaryCurrents();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isLoadsNeedUpdating <em>Loads Need Updating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loads Need Updating</em>'.
	 * @see dss.common.Solution#isLoadsNeedUpdating()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_LoadsNeedUpdating();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getIteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Iteration</em>'.
	 * @see dss.common.Solution#getIteration()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Iteration();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getMaxIterations <em>Max Iterations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Iterations</em>'.
	 * @see dss.common.Solution#getMaxIterations()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MaxIterations();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getMaxError <em>Max Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Error</em>'.
	 * @see dss.common.Solution#getMaxError()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MaxError();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getConvergenceTolerance <em>Convergence Tolerance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Convergence Tolerance</em>'.
	 * @see dss.common.Solution#getConvergenceTolerance()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ConvergenceTolerance();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isConverged <em>Converged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Converged</em>'.
	 * @see dss.common.Solution#isConverged()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Converged();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getControlIteration <em>Control Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Iteration</em>'.
	 * @see dss.common.Solution#getControlIteration()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ControlIteration();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getMaxControlIterations <em>Max Control Iterations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Control Iterations</em>'.
	 * @see dss.common.Solution#getMaxControlIterations()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MaxControlIterations();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getControlMode <em>Control Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Mode</em>'.
	 * @see dss.common.Solution#getControlMode()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ControlMode();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isControlActionsDone <em>Control Actions Done</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Actions Done</em>'.
	 * @see dss.common.Solution#isControlActionsDone()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_ControlActionsDone();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getMostIterationsDone <em>Most Iterations Done</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Most Iterations Done</em>'.
	 * @see dss.common.Solution#getMostIterationsDone()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_MostIterationsDone();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see dss.common.Solution#getAlgorithm()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Algorithm();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#isLastSolutionWasDirect <em>Last Solution Was Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Solution Was Direct</em>'.
	 * @see dss.common.Solution#isLastSolutionWasDirect()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_LastSolutionWasDirect();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getNodeV <em>Node V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node V</em>'.
	 * @see dss.common.Solution#getNodeV()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_NodeV();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Solution#getCurrents <em>Currents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Currents</em>'.
	 * @see dss.common.Solution#getCurrents()
	 * @see #getSolution()
	 * @generated
	 */
	EAttribute getSolution_Currents();

	/**
	 * Returns the meta object for class '{@link dss.common.Terminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Terminal</em>'.
	 * @see dss.common.Terminal
	 * @generated
	 */
	EClass getTerminal();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Terminal#getBusRef <em>Bus Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Ref</em>'.
	 * @see dss.common.Terminal#getBusRef()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_BusRef();

	/**
	 * Returns the meta object for the attribute list '{@link dss.common.Terminal#getTermNodeRef <em>Term Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Term Node Ref</em>'.
	 * @see dss.common.Terminal#getTermNodeRef()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_TermNodeRef();

	/**
	 * Returns the meta object for the reference list '{@link dss.common.Terminal#getConductors <em>Conductors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Conductors</em>'.
	 * @see dss.common.Terminal#getConductors()
	 * @see #getTerminal()
	 * @generated
	 */
	EReference getTerminal_Conductors();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Terminal#isChecked <em>Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Checked</em>'.
	 * @see dss.common.Terminal#isChecked()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_Checked();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Terminal#getNCond <em>NCond</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NCond</em>'.
	 * @see dss.common.Terminal#getNCond()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_NCond();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.Terminal#getActiveConductor <em>Active Conductor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Conductor</em>'.
	 * @see dss.common.Terminal#getActiveConductor()
	 * @see #getTerminal()
	 * @generated
	 */
	EAttribute getTerminal_ActiveConductor();

	/**
	 * Returns the meta object for class '{@link dss.common.ControlQueue <em>Control Queue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Queue</em>'.
	 * @see dss.common.ControlQueue
	 * @generated
	 */
	EClass getControlQueue();

	/**
	 * Returns the meta object for the attribute '{@link dss.common.ControlQueue#isEmpty <em>Empty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Empty</em>'.
	 * @see dss.common.ControlQueue#isEmpty()
	 * @see #getControlQueue()
	 * @generated
	 */
	EAttribute getControlQueue_Empty();

	/**
	 * Returns the meta object for enum '{@link dss.common.connectionType <em>connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>connection Type</em>'.
	 * @see dss.common.connectionType
	 * @generated
	 */
	EEnum getconnectionType();

	/**
	 * Returns the meta object for enum '{@link dss.common.lengthUnit <em>length Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>length Unit</em>'.
	 * @see dss.common.lengthUnit
	 * @generated
	 */
	EEnum getlengthUnit();

	/**
	 * Returns the meta object for enum '{@link dss.common.tripAction <em>trip Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>trip Action</em>'.
	 * @see dss.common.tripAction
	 * @generated
	 */
	EEnum gettripAction();

	/**
	 * Returns the meta object for enum '{@link dss.common.yBuildOption <em>yBuild Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>yBuild Option</em>'.
	 * @see dss.common.yBuildOption
	 * @generated
	 */
	EEnum getyBuildOption();

	/**
	 * Returns the meta object for enum '{@link dss.common.controlModeType <em>control Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>control Mode Type</em>'.
	 * @see dss.common.controlModeType
	 * @generated
	 */
	EEnum getcontrolModeType();

	/**
	 * Returns the meta object for enum '{@link dss.common.algorithmType <em>algorithm Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>algorithm Type</em>'.
	 * @see dss.common.algorithmType
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
		 * The meta object literal for the '{@link dss.common.impl.CircuitImpl <em>Circuit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.CircuitImpl
		 * @see dss.common.impl.CommonPackageImpl#getCircuit()
		 * @generated
		 */
		EClass CIRCUIT = eINSTANCE.getCircuit();

		/**
		 * The meta object literal for the '<em><b>Solution</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__SOLUTION = eINSTANCE.getCircuit_Solution();

		/**
		 * The meta object literal for the '<em><b>Control Queue</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__CONTROL_QUEUE = eINSTANCE.getCircuit_ControlQueue();

		/**
		 * The meta object literal for the '<em><b>Bus List</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__BUS_LIST = eINSTANCE.getCircuit_BusList();

		/**
		 * The meta object literal for the '<em><b>Faults</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__FAULTS = eINSTANCE.getCircuit_Faults();

		/**
		 * The meta object literal for the '<em><b>Bus Name Redefined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__BUS_NAME_REDEFINED = eINSTANCE.getCircuit_BusNameRedefined();

		/**
		 * The meta object literal for the '<em><b>Num Nodes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__NUM_NODES = eINSTANCE.getCircuit_NumNodes();

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
		 * The meta object literal for the '<em><b>Current Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__CURRENT_SOURCES = eINSTANCE.getCircuit_CurrentSources();

		/**
		 * The meta object literal for the '<em><b>Sensors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__SENSORS = eINSTANCE.getCircuit_Sensors();

		/**
		 * The meta object literal for the '<em><b>Monitors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__MONITORS = eINSTANCE.getCircuit_Monitors();

		/**
		 * The meta object literal for the '<em><b>Energy Meters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__ENERGY_METERS = eINSTANCE.getCircuit_EnergyMeters();

		/**
		 * The meta object literal for the '<em><b>Generators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__GENERATORS = eINSTANCE.getCircuit_Generators();

		/**
		 * The meta object literal for the '<em><b>Transformers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__TRANSFORMERS = eINSTANCE.getCircuit_Transformers();

		/**
		 * The meta object literal for the '<em><b>Cap Controls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__CAP_CONTROLS = eINSTANCE.getCircuit_CapControls();

		/**
		 * The meta object literal for the '<em><b>Reg Controls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__REG_CONTROLS = eINSTANCE.getCircuit_RegControls();

		/**
		 * The meta object literal for the '<em><b>Lines</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__LINES = eINSTANCE.getCircuit_Lines();

		/**
		 * The meta object literal for the '<em><b>Loads</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__LOADS = eINSTANCE.getCircuit_Loads();

		/**
		 * The meta object literal for the '<em><b>Shunt Capacitors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__SHUNT_CAPACITORS = eINSTANCE.getCircuit_ShuntCapacitors();

		/**
		 * The meta object literal for the '<em><b>Feeder</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT__FEEDER = eINSTANCE.getCircuit_Feeder();

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
		 * The meta object literal for the '<em><b>Solved</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__SOLVED = eINSTANCE.getCircuit_Solved();

		/**
		 * The meta object literal for the '<em><b>Control bus Name Redefined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__CONTROL_BUS_NAME_REDEFINED = eINSTANCE.getCircuit_Control_busNameRedefined();

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
		 * The meta object literal for the '<em><b>Default Hour Mult</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__DEFAULT_HOUR_MULT = eINSTANCE.getCircuit_DefaultHourMult();

		/**
		 * The meta object literal for the '<em><b>Price Signal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT__PRICE_SIGNAL = eINSTANCE.getCircuit_PriceSignal();

		/**
		 * The meta object literal for the '{@link dss.common.impl.BusImpl <em>Bus</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.BusImpl
		 * @see dss.common.impl.CommonPackageImpl#getBus()
		 * @generated
		 */
		EClass BUS = eINSTANCE.getBus();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__NAME = eINSTANCE.getBus_Name();

		/**
		 * The meta object literal for the '<em><b>VBus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__VBUS = eINSTANCE.getBus_VBus();

		/**
		 * The meta object literal for the '<em><b>Bus Current</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__BUS_CURRENT = eINSTANCE.getBus_BusCurrent();

		/**
		 * The meta object literal for the '<em><b>ZSC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__ZSC = eINSTANCE.getBus_ZSC();

		/**
		 * The meta object literal for the '<em><b>YSC</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__YSC = eINSTANCE.getBus_YSC();

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
		 * The meta object literal for the '<em><b>KV Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__KV_BASE = eINSTANCE.getBus_KVBase();

		/**
		 * The meta object literal for the '<em><b>Coords Defined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__COORDS_DEFINED = eINSTANCE.getBus_CoordsDefined();

		/**
		 * The meta object literal for the '<em><b>Bus Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__BUS_CHECKED = eINSTANCE.getBus_BusChecked();

		/**
		 * The meta object literal for the '<em><b>Keep</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__KEEP = eINSTANCE.getBus_Keep();

		/**
		 * The meta object literal for the '<em><b>Radial Bus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUS__RADIAL_BUS = eINSTANCE.getBus_RadialBus();

		/**
		 * The meta object literal for the '{@link dss.common.impl.CircuitElementImpl <em>Circuit Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.CircuitElementImpl
		 * @see dss.common.impl.CommonPackageImpl#getCircuitElement()
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
		 * The meta object literal for the '<em><b>Node Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__NODE_REF = eINSTANCE.getCircuitElement_NodeRef();

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
		 * The meta object literal for the '<em><b>Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__CHECKED = eINSTANCE.getCircuitElement_Checked();

		/**
		 * The meta object literal for the '<em><b>Has Meter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__HAS_METER = eINSTANCE.getCircuitElement_HasMeter();

		/**
		 * The meta object literal for the '<em><b>Isolated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__ISOLATED = eINSTANCE.getCircuitElement_Isolated();

		/**
		 * The meta object literal for the '<em><b>Has Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__HAS_CONTROL = eINSTANCE.getCircuitElement_HasControl();

		/**
		 * The meta object literal for the '<em><b>Part Of Feeder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__PART_OF_FEEDER = eINSTANCE.getCircuitElement_PartOfFeeder();

		/**
		 * The meta object literal for the '<em><b>Control Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CIRCUIT_ELEMENT__CONTROL_ELEMENT = eINSTANCE.getCircuitElement_ControlElement();

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
		 * The meta object literal for the '<em><b>Bus Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__BUS_INDEX = eINSTANCE.getCircuitElement_BusIndex();

		/**
		 * The meta object literal for the '<em><b>YPrim Series</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YPRIM_SERIES = eINSTANCE.getCircuitElement_YPrimSeries();

		/**
		 * The meta object literal for the '<em><b>YPrim Shunt</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YPRIM_SHUNT = eINSTANCE.getCircuitElement_YPrimShunt();

		/**
		 * The meta object literal for the '<em><b>YPrim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YPRIM = eINSTANCE.getCircuitElement_YPrim();

		/**
		 * The meta object literal for the '<em><b>YPrim Freq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CIRCUIT_ELEMENT__YPRIM_FREQ = eINSTANCE.getCircuitElement_YPrimFreq();

		/**
		 * The meta object literal for the '{@link dss.common.impl.CollectionImpl <em>Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.CollectionImpl
		 * @see dss.common.impl.CommonPackageImpl#getCollection()
		 * @generated
		 */
		EClass COLLECTION = eINSTANCE.getCollection();

		/**
		 * The meta object literal for the '<em><b>NProperties</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__NPROPERTIES = eINSTANCE.getCollection_NProperties();

		/**
		 * The meta object literal for the '<em><b>Property Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__PROPERTY_NAME = eINSTANCE.getCollection_PropertyName();

		/**
		 * The meta object literal for the '<em><b>Property Help</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__PROPERTY_HELP = eINSTANCE.getCollection_PropertyHelp();

		/**
		 * The meta object literal for the '<em><b>Property Idx Map</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__PROPERTY_IDX_MAP = eINSTANCE.getCollection_PropertyIdxMap();

		/**
		 * The meta object literal for the '<em><b>Element List</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION__ELEMENT_LIST = eINSTANCE.getCollection_ElementList();

		/**
		 * The meta object literal for the '<em><b>Saved</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__SAVED = eINSTANCE.getCollection_Saved();

		/**
		 * The meta object literal for the '{@link dss.common.impl.ConductorImpl <em>Conductor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.ConductorImpl
		 * @see dss.common.impl.CommonPackageImpl#getConductor()
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
		 * The meta object literal for the '{@link dss.common.impl.FeederImpl <em>Feeder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.FeederImpl
		 * @see dss.common.impl.CommonPackageImpl#getFeeder()
		 * @generated
		 */
		EClass FEEDER = eINSTANCE.getFeeder();

		/**
		 * The meta object literal for the '<em><b>Spectrum</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEEDER__SPECTRUM = eINSTANCE.getFeeder_Spectrum();

		/**
		 * The meta object literal for the '<em><b>Base Freq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEEDER__BASE_FREQ = eINSTANCE.getFeeder_BaseFreq();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEEDER__ENABLED = eINSTANCE.getFeeder_Enabled();

		/**
		 * The meta object literal for the '{@link dss.common.impl.SolutionImpl <em>Solution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.SolutionImpl
		 * @see dss.common.impl.CommonPackageImpl#getSolution()
		 * @generated
		 */
		EClass SOLUTION = eINSTANCE.getSolution();

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
		 * The meta object literal for the '<em><b>Solution Abort</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__SOLUTION_ABORT = eINSTANCE.getSolution_SolutionAbort();

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
		 * The meta object literal for the '<em><b>Node V</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__NODE_V = eINSTANCE.getSolution_NodeV();

		/**
		 * The meta object literal for the '<em><b>Currents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOLUTION__CURRENTS = eINSTANCE.getSolution_Currents();

		/**
		 * The meta object literal for the '{@link dss.common.impl.TerminalImpl <em>Terminal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.TerminalImpl
		 * @see dss.common.impl.CommonPackageImpl#getTerminal()
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
		 * The meta object literal for the '{@link dss.common.impl.ControlQueueImpl <em>Control Queue</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.impl.ControlQueueImpl
		 * @see dss.common.impl.CommonPackageImpl#getControlQueue()
		 * @generated
		 */
		EClass CONTROL_QUEUE = eINSTANCE.getControlQueue();

		/**
		 * The meta object literal for the '<em><b>Empty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_QUEUE__EMPTY = eINSTANCE.getControlQueue_Empty();

		/**
		 * The meta object literal for the '{@link dss.common.connectionType <em>connection Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.connectionType
		 * @see dss.common.impl.CommonPackageImpl#getconnectionType()
		 * @generated
		 */
		EEnum CONNECTION_TYPE = eINSTANCE.getconnectionType();

		/**
		 * The meta object literal for the '{@link dss.common.lengthUnit <em>length Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.lengthUnit
		 * @see dss.common.impl.CommonPackageImpl#getlengthUnit()
		 * @generated
		 */
		EEnum LENGTH_UNIT = eINSTANCE.getlengthUnit();

		/**
		 * The meta object literal for the '{@link dss.common.tripAction <em>trip Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.tripAction
		 * @see dss.common.impl.CommonPackageImpl#gettripAction()
		 * @generated
		 */
		EEnum TRIP_ACTION = eINSTANCE.gettripAction();

		/**
		 * The meta object literal for the '{@link dss.common.yBuildOption <em>yBuild Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.yBuildOption
		 * @see dss.common.impl.CommonPackageImpl#getyBuildOption()
		 * @generated
		 */
		EEnum YBUILD_OPTION = eINSTANCE.getyBuildOption();

		/**
		 * The meta object literal for the '{@link dss.common.controlModeType <em>control Mode Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.controlModeType
		 * @see dss.common.impl.CommonPackageImpl#getcontrolModeType()
		 * @generated
		 */
		EEnum CONTROL_MODE_TYPE = eINSTANCE.getcontrolModeType();

		/**
		 * The meta object literal for the '{@link dss.common.algorithmType <em>algorithm Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.common.algorithmType
		 * @see dss.common.impl.CommonPackageImpl#getalgorithmType()
		 * @generated
		 */
		EEnum ALGORITHM_TYPE = eINSTANCE.getalgorithmType();

	}

} //CommonPackage
