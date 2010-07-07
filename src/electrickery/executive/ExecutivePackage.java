/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive;

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
 * @see electrickery.executive.ExecutiveFactory
 * @model kind="package"
 * @generated
 */
public interface ExecutivePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "executive";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.openpowersystem.com/dss/executive";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "exe";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExecutivePackage eINSTANCE = electrickery.executive.impl.ExecutivePackageImpl.init();

	/**
	 * The meta object id for the '{@link electrickery.executive.impl.ExecOptionsImpl <em>Exec Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.impl.ExecOptionsImpl
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecOptions()
	 * @generated
	 */
	int EXEC_OPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__HOUR = 2;

	/**
	 * The feature id for the '<em><b>Sec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__SEC = 3;

	/**
	 * The feature id for the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__YEAR = 4;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__FREQUENCY = 5;

	/**
	 * The feature id for the '<em><b>Step Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__STEP_SIZE = 6;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__MODE = 7;

	/**
	 * The feature id for the '<em><b>Random</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__RANDOM = 8;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__NUMBER = 9;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__TIME = 10;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__CIRCUIT = 11;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__EDITOR = 12;

	/**
	 * The feature id for the '<em><b>Tolerance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__TOLERANCE = 13;

	/**
	 * The feature id for the '<em><b>Max Iter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__MAX_ITER = 14;

	/**
	 * The feature id for the '<em><b>Load Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__LOAD_MODEL = 15;

	/**
	 * The feature id for the '<em><b>Load Mult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__LOAD_MULT = 16;

	/**
	 * The feature id for the '<em><b>Norm VMin PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__NORM_VMIN_PU = 17;

	/**
	 * The feature id for the '<em><b>Norm VMax PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__NORM_VMAX_PU = 18;

	/**
	 * The feature id for the '<em><b>Emerg VMin PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__EMERG_VMIN_PU = 19;

	/**
	 * The feature id for the '<em><b>Emerg VMax PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__EMERG_VMAX_PU = 20;

	/**
	 * The feature id for the '<em><b>Pct Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__PCT_MEAN = 21;

	/**
	 * The feature id for the '<em><b>Pct Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__PCT_STD_DEV = 22;

	/**
	 * The feature id for the '<em><b>LD Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__LD_CURVE = 23;

	/**
	 * The feature id for the '<em><b>Pct Growth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__PCT_GROWTH = 24;

	/**
	 * The feature id for the '<em><b>Gen KW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__GEN_KW = 25;

	/**
	 * The feature id for the '<em><b>Gen PF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__GEN_PF = 26;

	/**
	 * The feature id for the '<em><b>Cap KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__CAP_KV_AR = 27;

	/**
	 * The feature id for the '<em><b>Add Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__ADD_TYPE = 28;

	/**
	 * The feature id for the '<em><b>Allow Duplicates</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__ALLOW_DUPLICATES = 29;

	/**
	 * The feature id for the '<em><b>Zone Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__ZONE_LOCK = 30;

	/**
	 * The feature id for the '<em><b>UE Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__UE_WEIGHT = 31;

	/**
	 * The feature id for the '<em><b>Loss Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__LOSS_WEIGHT = 32;

	/**
	 * The feature id for the '<em><b>UE Regs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__UE_REGS = 33;

	/**
	 * The feature id for the '<em><b>Loss Regs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__LOSS_REGS = 34;

	/**
	 * The feature id for the '<em><b>Voltage Bases</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__VOLTAGE_BASES = 35;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__ALGORITHM = 36;

	/**
	 * The feature id for the '<em><b>Trapezoidal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__TRAPEZOIDAL = 37;

	/**
	 * The feature id for the '<em><b>Auto Bus List</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__AUTO_BUS_LIST = 38;

	/**
	 * The feature id for the '<em><b>Control Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__CONTROL_MODE = 39;

	/**
	 * The feature id for the '<em><b>Trace Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__TRACE_MODE = 40;

	/**
	 * The feature id for the '<em><b>Gen Mult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__GEN_MULT = 41;

	/**
	 * The feature id for the '<em><b>Default Daily</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__DEFAULT_DAILY = 42;

	/**
	 * The feature id for the '<em><b>Default Yearly</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__DEFAULT_YEARLY = 43;

	/**
	 * The feature id for the '<em><b>Allocation Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__ALLOCATION_FACTOR = 44;

	/**
	 * The feature id for the '<em><b>Ckt Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__CKT_MODEL = 45;

	/**
	 * The feature id for the '<em><b>Price Signal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__PRICE_SIGNAL = 46;

	/**
	 * The feature id for the '<em><b>Price Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__PRICE_CURVE = 47;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__TERMINAL = 48;

	/**
	 * The feature id for the '<em><b>Base Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__BASE_FREQUENCY = 49;

	/**
	 * The feature id for the '<em><b>Harmonics</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__HARMONICS = 50;

	/**
	 * The feature id for the '<em><b>Max Controller</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__MAX_CONTROLLER = 51;

	/**
	 * The feature id for the '<em><b>Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__BUS = 52;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__DATA_PATH = 53;

	/**
	 * The feature id for the '<em><b>Keep List</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__KEEP_LIST = 54;

	/**
	 * The feature id for the '<em><b>Reduce Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__REDUCE_OPTION = 55;

	/**
	 * The feature id for the '<em><b>Demand Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__DEMAND_INTERVAL = 56;

	/**
	 * The feature id for the '<em><b>Pct Normal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__PCT_NORMAL = 57;

	/**
	 * The feature id for the '<em><b>DI Verbose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__DI_VERBOSE = 58;

	/**
	 * The feature id for the '<em><b>Case Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__CASE_NAME = 59;

	/**
	 * The feature id for the '<em><b>Marker Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__MARKER_CODE = 60;

	/**
	 * The feature id for the '<em><b>Node Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__NODE_WIDTH = 61;

	/**
	 * The feature id for the '<em><b>Log</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__LOG = 62;

	/**
	 * The feature id for the '<em><b>Recorder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__RECORDER = 63;

	/**
	 * The feature id for the '<em><b>Overload Report</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__OVERLOAD_REPORT = 64;

	/**
	 * The feature id for the '<em><b>Voltage Exception Report</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT = 65;

	/**
	 * The number of structural features of the '<em>Exec Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_OPTIONS_FEATURE_COUNT = 66;

	/**
	 * The meta object id for the '{@link electrickery.executive.impl.ExecCommandsImpl <em>Exec Commands</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.impl.ExecCommandsImpl
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecCommands()
	 * @generated
	 */
	int EXEC_COMMANDS = 1;

	/**
	 * The number of structural features of the '<em>Exec Commands</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXEC_COMMANDS_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link electrickery.executive.impl.ExecutiveImpl <em>Executive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.impl.ExecutiveImpl
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecutive()
	 * @generated
	 */
	int EXECUTIVE = 2;

	/**
	 * The feature id for the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__COMMAND = 0;

	/**
	 * The feature id for the '<em><b>Exec Commands</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__EXEC_COMMANDS = 1;

	/**
	 * The feature id for the '<em><b>Exec Options</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__EXEC_OPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Active Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__ACTIVE_CIRCUIT = 3;

	/**
	 * The feature id for the '<em><b>Circuits</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__CIRCUITS = 4;

	/**
	 * The feature id for the '<em><b>Max Circuits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__MAX_CIRCUITS = 5;

	/**
	 * The number of structural features of the '<em>Executive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link electrickery.executive.solutionMode <em>solution Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.solutionMode
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getsolutionMode()
	 * @generated
	 */
	int SOLUTION_MODE = 3;

	/**
	 * The meta object id for the '{@link electrickery.executive.randomType <em>random Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.randomType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getrandomType()
	 * @generated
	 */
	int RANDOM_TYPE = 4;

	/**
	 * The meta object id for the '{@link electrickery.executive.loadModelType <em>load Model Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.loadModelType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getloadModelType()
	 * @generated
	 */
	int LOAD_MODEL_TYPE = 5;

	/**
	 * The meta object id for the '{@link electrickery.executive.autoAddType <em>auto Add Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.autoAddType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getautoAddType()
	 * @generated
	 */
	int AUTO_ADD_TYPE = 6;

	/**
	 * The meta object id for the '{@link electrickery.executive.algorithmType <em>algorithm Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.algorithmType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getalgorithmType()
	 * @generated
	 */
	int ALGORITHM_TYPE = 7;

	/**
	 * The meta object id for the '{@link electrickery.executive.controlModeType <em>control Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.controlModeType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getcontrolModeType()
	 * @generated
	 */
	int CONTROL_MODE_TYPE = 8;

	/**
	 * The meta object id for the '{@link electrickery.executive.circuitModelType <em>circuit Model Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.circuitModelType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getcircuitModelType()
	 * @generated
	 */
	int CIRCUIT_MODEL_TYPE = 9;

	/**
	 * The meta object id for the '{@link electrickery.executive.reductionStrategy <em>reduction Strategy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.reductionStrategy
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getreductionStrategy()
	 * @generated
	 */
	int REDUCTION_STRATEGY = 10;

	/**
	 * The meta object id for the '{@link electrickery.executive.resetType <em>reset Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.resetType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getresetType()
	 * @generated
	 */
	int RESET_TYPE = 11;

	/**
	 * The meta object id for the '{@link electrickery.executive.nextType <em>next Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.nextType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getnextType()
	 * @generated
	 */
	int NEXT_TYPE = 12;

	/**
	 * The meta object id for the '{@link electrickery.executive.exportType <em>export Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.exportType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getexportType()
	 * @generated
	 */
	int EXPORT_TYPE = 13;

	/**
	 * The meta object id for the '{@link electrickery.executive.reduceType <em>reduce Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.reduceType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getreduceType()
	 * @generated
	 */
	int REDUCE_TYPE = 14;

	/**
	 * The meta object id for the '{@link electrickery.executive.distributionType <em>distribution Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.distributionType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getdistributionType()
	 * @generated
	 */
	int DISTRIBUTION_TYPE = 15;


	/**
	 * Returns the meta object for class '{@link electrickery.executive.ExecOptions <em>Exec Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exec Options</em>'.
	 * @see electrickery.executive.ExecOptions
	 * @generated
	 */
	EClass getExecOptions();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see electrickery.executive.ExecOptions#getType()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Type();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see electrickery.executive.ExecOptions#getElement()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Element();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hour</em>'.
	 * @see electrickery.executive.ExecOptions#getHour()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Hour();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getSec <em>Sec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sec</em>'.
	 * @see electrickery.executive.ExecOptions#getSec()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Sec();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getYear <em>Year</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Year</em>'.
	 * @see electrickery.executive.ExecOptions#getYear()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Year();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see electrickery.executive.ExecOptions#getFrequency()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getStepSize <em>Step Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Step Size</em>'.
	 * @see electrickery.executive.ExecOptions#getStepSize()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_StepSize();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see electrickery.executive.ExecOptions#getMode()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Mode();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getRandom <em>Random</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Random</em>'.
	 * @see electrickery.executive.ExecOptions#getRandom()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Random();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see electrickery.executive.ExecOptions#getNumber()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Number();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.executive.ExecOptions#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Time</em>'.
	 * @see electrickery.executive.ExecOptions#getTime()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Time();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Circuit</em>'.
	 * @see electrickery.executive.ExecOptions#getCircuit()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Circuit();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Editor</em>'.
	 * @see electrickery.executive.ExecOptions#getEditor()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Editor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getTolerance <em>Tolerance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tolerance</em>'.
	 * @see electrickery.executive.ExecOptions#getTolerance()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Tolerance();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getMaxIter <em>Max Iter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Iter</em>'.
	 * @see electrickery.executive.ExecOptions#getMaxIter()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_MaxIter();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getLoadModel <em>Load Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Model</em>'.
	 * @see electrickery.executive.ExecOptions#getLoadModel()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_LoadModel();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getLoadMult <em>Load Mult</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Mult</em>'.
	 * @see electrickery.executive.ExecOptions#getLoadMult()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_LoadMult();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getNormVMinPU <em>Norm VMin PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm VMin PU</em>'.
	 * @see electrickery.executive.ExecOptions#getNormVMinPU()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_NormVMinPU();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getNormVMaxPU <em>Norm VMax PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm VMax PU</em>'.
	 * @see electrickery.executive.ExecOptions#getNormVMaxPU()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_NormVMaxPU();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getEmergVMinPU <em>Emerg VMin PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg VMin PU</em>'.
	 * @see electrickery.executive.ExecOptions#getEmergVMinPU()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_EmergVMinPU();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getEmergVMaxPU <em>Emerg VMax PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg VMax PU</em>'.
	 * @see electrickery.executive.ExecOptions#getEmergVMaxPU()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_EmergVMaxPU();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getPctMean <em>Pct Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Mean</em>'.
	 * @see electrickery.executive.ExecOptions#getPctMean()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_PctMean();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getPctStdDev <em>Pct Std Dev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Std Dev</em>'.
	 * @see electrickery.executive.ExecOptions#getPctStdDev()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_PctStdDev();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.ExecOptions#getLDCurve <em>LD Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LD Curve</em>'.
	 * @see electrickery.executive.ExecOptions#getLDCurve()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_LDCurve();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getPctGrowth <em>Pct Growth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Growth</em>'.
	 * @see electrickery.executive.ExecOptions#getPctGrowth()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_PctGrowth();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getGenKW <em>Gen KW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gen KW</em>'.
	 * @see electrickery.executive.ExecOptions#getGenKW()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_GenKW();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getGenPF <em>Gen PF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gen PF</em>'.
	 * @see electrickery.executive.ExecOptions#getGenPF()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_GenPF();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getCapKVAr <em>Cap KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cap KV Ar</em>'.
	 * @see electrickery.executive.ExecOptions#getCapKVAr()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_CapKVAr();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getAddType <em>Add Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Add Type</em>'.
	 * @see electrickery.executive.ExecOptions#getAddType()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_AddType();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isAllowDuplicates <em>Allow Duplicates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Duplicates</em>'.
	 * @see electrickery.executive.ExecOptions#isAllowDuplicates()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_AllowDuplicates();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isZoneLock <em>Zone Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zone Lock</em>'.
	 * @see electrickery.executive.ExecOptions#isZoneLock()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_ZoneLock();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getUEWeight <em>UE Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UE Weight</em>'.
	 * @see electrickery.executive.ExecOptions#getUEWeight()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_UEWeight();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getLossWeight <em>Loss Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loss Weight</em>'.
	 * @see electrickery.executive.ExecOptions#getLossWeight()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_LossWeight();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getUERegs <em>UE Regs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UE Regs</em>'.
	 * @see electrickery.executive.ExecOptions#getUERegs()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_UERegs();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getLossRegs <em>Loss Regs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loss Regs</em>'.
	 * @see electrickery.executive.ExecOptions#getLossRegs()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_LossRegs();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.executive.ExecOptions#getVoltageBases <em>Voltage Bases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Voltage Bases</em>'.
	 * @see electrickery.executive.ExecOptions#getVoltageBases()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_VoltageBases();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see electrickery.executive.ExecOptions#getAlgorithm()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Algorithm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isTrapezoidal <em>Trapezoidal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trapezoidal</em>'.
	 * @see electrickery.executive.ExecOptions#isTrapezoidal()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Trapezoidal();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.executive.ExecOptions#getAutoBusList <em>Auto Bus List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Auto Bus List</em>'.
	 * @see electrickery.executive.ExecOptions#getAutoBusList()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_AutoBusList();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getControlMode <em>Control Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Mode</em>'.
	 * @see electrickery.executive.ExecOptions#getControlMode()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_ControlMode();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isTraceMode <em>Trace Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Mode</em>'.
	 * @see electrickery.executive.ExecOptions#isTraceMode()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_TraceMode();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getGenMult <em>Gen Mult</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gen Mult</em>'.
	 * @see electrickery.executive.ExecOptions#getGenMult()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_GenMult();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.ExecOptions#getDefaultDaily <em>Default Daily</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Daily</em>'.
	 * @see electrickery.executive.ExecOptions#getDefaultDaily()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_DefaultDaily();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.ExecOptions#getDefaultYearly <em>Default Yearly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Yearly</em>'.
	 * @see electrickery.executive.ExecOptions#getDefaultYearly()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_DefaultYearly();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getAllocationFactor <em>Allocation Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation Factor</em>'.
	 * @see electrickery.executive.ExecOptions#getAllocationFactor()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_AllocationFactor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getCktModel <em>Ckt Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ckt Model</em>'.
	 * @see electrickery.executive.ExecOptions#getCktModel()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_CktModel();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getPriceSignal <em>Price Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Price Signal</em>'.
	 * @see electrickery.executive.ExecOptions#getPriceSignal()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_PriceSignal();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.ExecOptions#getPriceCurve <em>Price Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Price Curve</em>'.
	 * @see electrickery.executive.ExecOptions#getPriceCurve()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_PriceCurve();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.ExecOptions#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Terminal</em>'.
	 * @see electrickery.executive.ExecOptions#getTerminal()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_Terminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getBaseFrequency <em>Base Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Frequency</em>'.
	 * @see electrickery.executive.ExecOptions#getBaseFrequency()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_BaseFrequency();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.executive.ExecOptions#getHarmonics <em>Harmonics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Harmonics</em>'.
	 * @see electrickery.executive.ExecOptions#getHarmonics()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Harmonics();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getMaxController <em>Max Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Controller</em>'.
	 * @see electrickery.executive.ExecOptions#getMaxController()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_MaxController();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.ExecOptions#getBus <em>Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bus</em>'.
	 * @see electrickery.executive.ExecOptions#getBus()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_Bus();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Path</em>'.
	 * @see electrickery.executive.ExecOptions#getDataPath()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_DataPath();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.executive.ExecOptions#getKeepList <em>Keep List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Keep List</em>'.
	 * @see electrickery.executive.ExecOptions#getKeepList()
	 * @see #getExecOptions()
	 * @generated
	 */
	EReference getExecOptions_KeepList();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getReduceOption <em>Reduce Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reduce Option</em>'.
	 * @see electrickery.executive.ExecOptions#getReduceOption()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_ReduceOption();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isDemandInterval <em>Demand Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Demand Interval</em>'.
	 * @see electrickery.executive.ExecOptions#isDemandInterval()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_DemandInterval();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getPctNormal <em>Pct Normal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Normal</em>'.
	 * @see electrickery.executive.ExecOptions#getPctNormal()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_PctNormal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isDIVerbose <em>DI Verbose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>DI Verbose</em>'.
	 * @see electrickery.executive.ExecOptions#isDIVerbose()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_DIVerbose();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getCaseName <em>Case Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Case Name</em>'.
	 * @see electrickery.executive.ExecOptions#getCaseName()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_CaseName();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getMarkerCode <em>Marker Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Marker Code</em>'.
	 * @see electrickery.executive.ExecOptions#getMarkerCode()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_MarkerCode();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#getNodeWidth <em>Node Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Width</em>'.
	 * @see electrickery.executive.ExecOptions#getNodeWidth()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_NodeWidth();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isLog <em>Log</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log</em>'.
	 * @see electrickery.executive.ExecOptions#isLog()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Log();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isRecorder <em>Recorder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recorder</em>'.
	 * @see electrickery.executive.ExecOptions#isRecorder()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_Recorder();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isOverloadReport <em>Overload Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overload Report</em>'.
	 * @see electrickery.executive.ExecOptions#isOverloadReport()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_OverloadReport();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.ExecOptions#isVoltageExceptionReport <em>Voltage Exception Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Voltage Exception Report</em>'.
	 * @see electrickery.executive.ExecOptions#isVoltageExceptionReport()
	 * @see #getExecOptions()
	 * @generated
	 */
	EAttribute getExecOptions_VoltageExceptionReport();

	/**
	 * Returns the meta object for class '{@link electrickery.executive.ExecCommands <em>Exec Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exec Commands</em>'.
	 * @see electrickery.executive.ExecCommands
	 * @generated
	 */
	EClass getExecCommands();

	/**
	 * Returns the meta object for class '{@link electrickery.executive.Executive <em>Executive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Executive</em>'.
	 * @see electrickery.executive.Executive
	 * @generated
	 */
	EClass getExecutive();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.Executive#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command</em>'.
	 * @see electrickery.executive.Executive#getCommand()
	 * @see #getExecutive()
	 * @generated
	 */
	EAttribute getExecutive_Command();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.Executive#getExecCommands <em>Exec Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Exec Commands</em>'.
	 * @see electrickery.executive.Executive#getExecCommands()
	 * @see #getExecutive()
	 * @generated
	 */
	EReference getExecutive_ExecCommands();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.Executive#getExecOptions <em>Exec Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Exec Options</em>'.
	 * @see electrickery.executive.Executive#getExecOptions()
	 * @see #getExecutive()
	 * @generated
	 */
	EReference getExecutive_ExecOptions();

	/**
	 * Returns the meta object for the reference '{@link electrickery.executive.Executive#getActiveCircuit <em>Active Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Circuit</em>'.
	 * @see electrickery.executive.Executive#getActiveCircuit()
	 * @see #getExecutive()
	 * @generated
	 */
	EReference getExecutive_ActiveCircuit();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.executive.Executive#getCircuits <em>Circuits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Circuits</em>'.
	 * @see electrickery.executive.Executive#getCircuits()
	 * @see #getExecutive()
	 * @generated
	 */
	EReference getExecutive_Circuits();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.executive.Executive#getMaxCircuits <em>Max Circuits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Circuits</em>'.
	 * @see electrickery.executive.Executive#getMaxCircuits()
	 * @see #getExecutive()
	 * @generated
	 */
	EAttribute getExecutive_MaxCircuits();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.solutionMode <em>solution Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>solution Mode</em>'.
	 * @see electrickery.executive.solutionMode
	 * @generated
	 */
	EEnum getsolutionMode();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.randomType <em>random Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>random Type</em>'.
	 * @see electrickery.executive.randomType
	 * @generated
	 */
	EEnum getrandomType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.loadModelType <em>load Model Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>load Model Type</em>'.
	 * @see electrickery.executive.loadModelType
	 * @generated
	 */
	EEnum getloadModelType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.autoAddType <em>auto Add Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>auto Add Type</em>'.
	 * @see electrickery.executive.autoAddType
	 * @generated
	 */
	EEnum getautoAddType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.algorithmType <em>algorithm Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>algorithm Type</em>'.
	 * @see electrickery.executive.algorithmType
	 * @generated
	 */
	EEnum getalgorithmType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.controlModeType <em>control Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>control Mode Type</em>'.
	 * @see electrickery.executive.controlModeType
	 * @generated
	 */
	EEnum getcontrolModeType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.circuitModelType <em>circuit Model Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>circuit Model Type</em>'.
	 * @see electrickery.executive.circuitModelType
	 * @generated
	 */
	EEnum getcircuitModelType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.reductionStrategy <em>reduction Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>reduction Strategy</em>'.
	 * @see electrickery.executive.reductionStrategy
	 * @generated
	 */
	EEnum getreductionStrategy();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.resetType <em>reset Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>reset Type</em>'.
	 * @see electrickery.executive.resetType
	 * @generated
	 */
	EEnum getresetType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.nextType <em>next Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>next Type</em>'.
	 * @see electrickery.executive.nextType
	 * @generated
	 */
	EEnum getnextType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.exportType <em>export Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>export Type</em>'.
	 * @see electrickery.executive.exportType
	 * @generated
	 */
	EEnum getexportType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.reduceType <em>reduce Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>reduce Type</em>'.
	 * @see electrickery.executive.reduceType
	 * @generated
	 */
	EEnum getreduceType();

	/**
	 * Returns the meta object for enum '{@link electrickery.executive.distributionType <em>distribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>distribution Type</em>'.
	 * @see electrickery.executive.distributionType
	 * @generated
	 */
	EEnum getdistributionType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExecutiveFactory getExecutiveFactory();

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
		 * The meta object literal for the '{@link electrickery.executive.impl.ExecOptionsImpl <em>Exec Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.impl.ExecOptionsImpl
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecOptions()
		 * @generated
		 */
		EClass EXEC_OPTIONS = eINSTANCE.getExecOptions();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__TYPE = eINSTANCE.getExecOptions_Type();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__ELEMENT = eINSTANCE.getExecOptions_Element();

		/**
		 * The meta object literal for the '<em><b>Hour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__HOUR = eINSTANCE.getExecOptions_Hour();

		/**
		 * The meta object literal for the '<em><b>Sec</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__SEC = eINSTANCE.getExecOptions_Sec();

		/**
		 * The meta object literal for the '<em><b>Year</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__YEAR = eINSTANCE.getExecOptions_Year();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__FREQUENCY = eINSTANCE.getExecOptions_Frequency();

		/**
		 * The meta object literal for the '<em><b>Step Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__STEP_SIZE = eINSTANCE.getExecOptions_StepSize();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__MODE = eINSTANCE.getExecOptions_Mode();

		/**
		 * The meta object literal for the '<em><b>Random</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__RANDOM = eINSTANCE.getExecOptions_Random();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__NUMBER = eINSTANCE.getExecOptions_Number();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__TIME = eINSTANCE.getExecOptions_Time();

		/**
		 * The meta object literal for the '<em><b>Circuit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__CIRCUIT = eINSTANCE.getExecOptions_Circuit();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__EDITOR = eINSTANCE.getExecOptions_Editor();

		/**
		 * The meta object literal for the '<em><b>Tolerance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__TOLERANCE = eINSTANCE.getExecOptions_Tolerance();

		/**
		 * The meta object literal for the '<em><b>Max Iter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__MAX_ITER = eINSTANCE.getExecOptions_MaxIter();

		/**
		 * The meta object literal for the '<em><b>Load Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__LOAD_MODEL = eINSTANCE.getExecOptions_LoadModel();

		/**
		 * The meta object literal for the '<em><b>Load Mult</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__LOAD_MULT = eINSTANCE.getExecOptions_LoadMult();

		/**
		 * The meta object literal for the '<em><b>Norm VMin PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__NORM_VMIN_PU = eINSTANCE.getExecOptions_NormVMinPU();

		/**
		 * The meta object literal for the '<em><b>Norm VMax PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__NORM_VMAX_PU = eINSTANCE.getExecOptions_NormVMaxPU();

		/**
		 * The meta object literal for the '<em><b>Emerg VMin PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__EMERG_VMIN_PU = eINSTANCE.getExecOptions_EmergVMinPU();

		/**
		 * The meta object literal for the '<em><b>Emerg VMax PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__EMERG_VMAX_PU = eINSTANCE.getExecOptions_EmergVMaxPU();

		/**
		 * The meta object literal for the '<em><b>Pct Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__PCT_MEAN = eINSTANCE.getExecOptions_PctMean();

		/**
		 * The meta object literal for the '<em><b>Pct Std Dev</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__PCT_STD_DEV = eINSTANCE.getExecOptions_PctStdDev();

		/**
		 * The meta object literal for the '<em><b>LD Curve</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__LD_CURVE = eINSTANCE.getExecOptions_LDCurve();

		/**
		 * The meta object literal for the '<em><b>Pct Growth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__PCT_GROWTH = eINSTANCE.getExecOptions_PctGrowth();

		/**
		 * The meta object literal for the '<em><b>Gen KW</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__GEN_KW = eINSTANCE.getExecOptions_GenKW();

		/**
		 * The meta object literal for the '<em><b>Gen PF</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__GEN_PF = eINSTANCE.getExecOptions_GenPF();

		/**
		 * The meta object literal for the '<em><b>Cap KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__CAP_KV_AR = eINSTANCE.getExecOptions_CapKVAr();

		/**
		 * The meta object literal for the '<em><b>Add Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__ADD_TYPE = eINSTANCE.getExecOptions_AddType();

		/**
		 * The meta object literal for the '<em><b>Allow Duplicates</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__ALLOW_DUPLICATES = eINSTANCE.getExecOptions_AllowDuplicates();

		/**
		 * The meta object literal for the '<em><b>Zone Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__ZONE_LOCK = eINSTANCE.getExecOptions_ZoneLock();

		/**
		 * The meta object literal for the '<em><b>UE Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__UE_WEIGHT = eINSTANCE.getExecOptions_UEWeight();

		/**
		 * The meta object literal for the '<em><b>Loss Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__LOSS_WEIGHT = eINSTANCE.getExecOptions_LossWeight();

		/**
		 * The meta object literal for the '<em><b>UE Regs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__UE_REGS = eINSTANCE.getExecOptions_UERegs();

		/**
		 * The meta object literal for the '<em><b>Loss Regs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__LOSS_REGS = eINSTANCE.getExecOptions_LossRegs();

		/**
		 * The meta object literal for the '<em><b>Voltage Bases</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__VOLTAGE_BASES = eINSTANCE.getExecOptions_VoltageBases();

		/**
		 * The meta object literal for the '<em><b>Algorithm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__ALGORITHM = eINSTANCE.getExecOptions_Algorithm();

		/**
		 * The meta object literal for the '<em><b>Trapezoidal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__TRAPEZOIDAL = eINSTANCE.getExecOptions_Trapezoidal();

		/**
		 * The meta object literal for the '<em><b>Auto Bus List</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__AUTO_BUS_LIST = eINSTANCE.getExecOptions_AutoBusList();

		/**
		 * The meta object literal for the '<em><b>Control Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__CONTROL_MODE = eINSTANCE.getExecOptions_ControlMode();

		/**
		 * The meta object literal for the '<em><b>Trace Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__TRACE_MODE = eINSTANCE.getExecOptions_TraceMode();

		/**
		 * The meta object literal for the '<em><b>Gen Mult</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__GEN_MULT = eINSTANCE.getExecOptions_GenMult();

		/**
		 * The meta object literal for the '<em><b>Default Daily</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__DEFAULT_DAILY = eINSTANCE.getExecOptions_DefaultDaily();

		/**
		 * The meta object literal for the '<em><b>Default Yearly</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__DEFAULT_YEARLY = eINSTANCE.getExecOptions_DefaultYearly();

		/**
		 * The meta object literal for the '<em><b>Allocation Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__ALLOCATION_FACTOR = eINSTANCE.getExecOptions_AllocationFactor();

		/**
		 * The meta object literal for the '<em><b>Ckt Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__CKT_MODEL = eINSTANCE.getExecOptions_CktModel();

		/**
		 * The meta object literal for the '<em><b>Price Signal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__PRICE_SIGNAL = eINSTANCE.getExecOptions_PriceSignal();

		/**
		 * The meta object literal for the '<em><b>Price Curve</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__PRICE_CURVE = eINSTANCE.getExecOptions_PriceCurve();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__TERMINAL = eINSTANCE.getExecOptions_Terminal();

		/**
		 * The meta object literal for the '<em><b>Base Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__BASE_FREQUENCY = eINSTANCE.getExecOptions_BaseFrequency();

		/**
		 * The meta object literal for the '<em><b>Harmonics</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__HARMONICS = eINSTANCE.getExecOptions_Harmonics();

		/**
		 * The meta object literal for the '<em><b>Max Controller</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__MAX_CONTROLLER = eINSTANCE.getExecOptions_MaxController();

		/**
		 * The meta object literal for the '<em><b>Bus</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__BUS = eINSTANCE.getExecOptions_Bus();

		/**
		 * The meta object literal for the '<em><b>Data Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__DATA_PATH = eINSTANCE.getExecOptions_DataPath();

		/**
		 * The meta object literal for the '<em><b>Keep List</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXEC_OPTIONS__KEEP_LIST = eINSTANCE.getExecOptions_KeepList();

		/**
		 * The meta object literal for the '<em><b>Reduce Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__REDUCE_OPTION = eINSTANCE.getExecOptions_ReduceOption();

		/**
		 * The meta object literal for the '<em><b>Demand Interval</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__DEMAND_INTERVAL = eINSTANCE.getExecOptions_DemandInterval();

		/**
		 * The meta object literal for the '<em><b>Pct Normal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__PCT_NORMAL = eINSTANCE.getExecOptions_PctNormal();

		/**
		 * The meta object literal for the '<em><b>DI Verbose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__DI_VERBOSE = eINSTANCE.getExecOptions_DIVerbose();

		/**
		 * The meta object literal for the '<em><b>Case Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__CASE_NAME = eINSTANCE.getExecOptions_CaseName();

		/**
		 * The meta object literal for the '<em><b>Marker Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__MARKER_CODE = eINSTANCE.getExecOptions_MarkerCode();

		/**
		 * The meta object literal for the '<em><b>Node Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__NODE_WIDTH = eINSTANCE.getExecOptions_NodeWidth();

		/**
		 * The meta object literal for the '<em><b>Log</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__LOG = eINSTANCE.getExecOptions_Log();

		/**
		 * The meta object literal for the '<em><b>Recorder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__RECORDER = eINSTANCE.getExecOptions_Recorder();

		/**
		 * The meta object literal for the '<em><b>Overload Report</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__OVERLOAD_REPORT = eINSTANCE.getExecOptions_OverloadReport();

		/**
		 * The meta object literal for the '<em><b>Voltage Exception Report</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT = eINSTANCE.getExecOptions_VoltageExceptionReport();

		/**
		 * The meta object literal for the '{@link electrickery.executive.impl.ExecCommandsImpl <em>Exec Commands</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.impl.ExecCommandsImpl
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecCommands()
		 * @generated
		 */
		EClass EXEC_COMMANDS = eINSTANCE.getExecCommands();

		/**
		 * The meta object literal for the '{@link electrickery.executive.impl.ExecutiveImpl <em>Executive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.impl.ExecutiveImpl
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecutive()
		 * @generated
		 */
		EClass EXECUTIVE = eINSTANCE.getExecutive();

		/**
		 * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTIVE__COMMAND = eINSTANCE.getExecutive_Command();

		/**
		 * The meta object literal for the '<em><b>Exec Commands</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTIVE__EXEC_COMMANDS = eINSTANCE.getExecutive_ExecCommands();

		/**
		 * The meta object literal for the '<em><b>Exec Options</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTIVE__EXEC_OPTIONS = eINSTANCE.getExecutive_ExecOptions();

		/**
		 * The meta object literal for the '<em><b>Active Circuit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTIVE__ACTIVE_CIRCUIT = eINSTANCE.getExecutive_ActiveCircuit();

		/**
		 * The meta object literal for the '<em><b>Circuits</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTIVE__CIRCUITS = eINSTANCE.getExecutive_Circuits();

		/**
		 * The meta object literal for the '<em><b>Max Circuits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTIVE__MAX_CIRCUITS = eINSTANCE.getExecutive_MaxCircuits();

		/**
		 * The meta object literal for the '{@link electrickery.executive.solutionMode <em>solution Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.solutionMode
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getsolutionMode()
		 * @generated
		 */
		EEnum SOLUTION_MODE = eINSTANCE.getsolutionMode();

		/**
		 * The meta object literal for the '{@link electrickery.executive.randomType <em>random Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.randomType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getrandomType()
		 * @generated
		 */
		EEnum RANDOM_TYPE = eINSTANCE.getrandomType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.loadModelType <em>load Model Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.loadModelType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getloadModelType()
		 * @generated
		 */
		EEnum LOAD_MODEL_TYPE = eINSTANCE.getloadModelType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.autoAddType <em>auto Add Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.autoAddType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getautoAddType()
		 * @generated
		 */
		EEnum AUTO_ADD_TYPE = eINSTANCE.getautoAddType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.algorithmType <em>algorithm Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.algorithmType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getalgorithmType()
		 * @generated
		 */
		EEnum ALGORITHM_TYPE = eINSTANCE.getalgorithmType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.controlModeType <em>control Mode Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.controlModeType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getcontrolModeType()
		 * @generated
		 */
		EEnum CONTROL_MODE_TYPE = eINSTANCE.getcontrolModeType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.circuitModelType <em>circuit Model Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.circuitModelType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getcircuitModelType()
		 * @generated
		 */
		EEnum CIRCUIT_MODEL_TYPE = eINSTANCE.getcircuitModelType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.reductionStrategy <em>reduction Strategy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.reductionStrategy
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getreductionStrategy()
		 * @generated
		 */
		EEnum REDUCTION_STRATEGY = eINSTANCE.getreductionStrategy();

		/**
		 * The meta object literal for the '{@link electrickery.executive.resetType <em>reset Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.resetType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getresetType()
		 * @generated
		 */
		EEnum RESET_TYPE = eINSTANCE.getresetType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.nextType <em>next Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.nextType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getnextType()
		 * @generated
		 */
		EEnum NEXT_TYPE = eINSTANCE.getnextType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.exportType <em>export Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.exportType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getexportType()
		 * @generated
		 */
		EEnum EXPORT_TYPE = eINSTANCE.getexportType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.reduceType <em>reduce Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.reduceType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getreduceType()
		 * @generated
		 */
		EEnum REDUCE_TYPE = eINSTANCE.getreduceType();

		/**
		 * The meta object literal for the '{@link electrickery.executive.distributionType <em>distribution Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.executive.distributionType
		 * @see electrickery.executive.impl.ExecutivePackageImpl#getdistributionType()
		 * @generated
		 */
		EEnum DISTRIBUTION_TYPE = eINSTANCE.getdistributionType();

	}

} //ExecutivePackage
