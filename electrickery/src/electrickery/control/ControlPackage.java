/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control;

import electrickery.common.CommonPackage;

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
 * @see electrickery.control.ControlFactory
 * @model kind="package"
 * @generated
 */
public interface ControlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "control";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.electrickery.com/control";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cont";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ControlPackage eINSTANCE = electrickery.control.impl.ControlPackageImpl.init();

	/**
	 * The meta object id for the '{@link electrickery.control.impl.ControlElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.impl.ControlElementImpl
	 * @see electrickery.control.impl.ControlPackageImpl#getControlElement()
	 * @generated
	 */
	int CONTROL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__NAME = CommonPackage.CIRCUIT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__ENABLED = CommonPackage.CIRCUIT_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__BASE_FREQ = CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__NODE_REF = CommonPackage.CIRCUIT_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__YORDER = CommonPackage.CIRCUIT_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__YPRIM_INVALID = CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__LAST_TERMINAL_CHECKED = CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__CHECKED = CommonPackage.CIRCUIT_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__HAS_METER = CommonPackage.CIRCUIT_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__ISOLATED = CommonPackage.CIRCUIT_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__HAS_CONTROL = CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__PART_OF_FEEDER = CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__CONTROL_ELEMENT = CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__TERMINALS = CommonPackage.CIRCUIT_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__ACTIVE_TERMINAL = CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__NTERMS = CommonPackage.CIRCUIT_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__NCONDS = CommonPackage.CIRCUIT_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__NPHASES = CommonPackage.CIRCUIT_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__BUS_INDEX = CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__YPRIM_SERIES = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__YPRIM_SHUNT = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__YPRIM = CommonPackage.CIRCUIT_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__YPRIM_FREQ = CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__LIKE = CommonPackage.CIRCUIT_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__ELEMENT_NAME = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Element Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__ELEMENT_TERMINAL = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__CONTROLLED_BUS_NAME = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__CONTROLLED_BUS = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__MONITORED_VARIABLE = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Monitored Var Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__MONITORED_VAR_INDEX = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__TIME_DELAY = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT__DBL_TRACE_PARAM = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_ELEMENT_FEATURE_COUNT = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link electrickery.control.impl.CapacitorControlImpl <em>Capacitor Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.impl.CapacitorControlImpl
	 * @see electrickery.control.impl.ControlPackageImpl#getCapacitorControl()
	 * @generated
	 */
	int CAPACITOR_CONTROL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__NAME = CONTROL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ENABLED = CONTROL_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__BASE_FREQ = CONTROL_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__NODE_REF = CONTROL_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__YORDER = CONTROL_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__YPRIM_INVALID = CONTROL_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__LAST_TERMINAL_CHECKED = CONTROL_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__CHECKED = CONTROL_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__HAS_METER = CONTROL_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ISOLATED = CONTROL_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__HAS_CONTROL = CONTROL_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__PART_OF_FEEDER = CONTROL_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__CONTROL_ELEMENT = CONTROL_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__TERMINALS = CONTROL_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ACTIVE_TERMINAL = CONTROL_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__NTERMS = CONTROL_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__NCONDS = CONTROL_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__NPHASES = CONTROL_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__BUS_INDEX = CONTROL_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__YPRIM_SERIES = CONTROL_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__YPRIM_SHUNT = CONTROL_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__YPRIM = CONTROL_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__YPRIM_FREQ = CONTROL_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__LIKE = CONTROL_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ELEMENT_NAME = CONTROL_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Element Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ELEMENT_TERMINAL = CONTROL_ELEMENT__ELEMENT_TERMINAL;

	/**
	 * The feature id for the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__CONTROLLED_BUS_NAME = CONTROL_ELEMENT__CONTROLLED_BUS_NAME;

	/**
	 * The feature id for the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__CONTROLLED_BUS = CONTROL_ELEMENT__CONTROLLED_BUS;

	/**
	 * The feature id for the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__MONITORED_VARIABLE = CONTROL_ELEMENT__MONITORED_VARIABLE;

	/**
	 * The feature id for the '<em><b>Monitored Var Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__MONITORED_VAR_INDEX = CONTROL_ELEMENT__MONITORED_VAR_INDEX;

	/**
	 * The feature id for the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__TIME_DELAY = CONTROL_ELEMENT__TIME_DELAY;

	/**
	 * The feature id for the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__DBL_TRACE_PARAM = CONTROL_ELEMENT__DBL_TRACE_PARAM;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ELEMENT = CONTROL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__TERMINAL = CONTROL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Capacitor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__CAPACITOR = CONTROL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__TYPE = CONTROL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>PT Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__PT_RATIO = CONTROL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>CT Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__CT_RATIO = CONTROL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>On Setting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__ON_SETTING = CONTROL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Off Setting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__OFF_SETTING = CONTROL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__DELAY = CONTROL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Volt Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__VOLT_OVERRIDE = CONTROL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>VMax</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__VMAX = CONTROL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>VMin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__VMIN = CONTROL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Delay Off</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__DELAY_OFF = CONTROL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Dead Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL__DEAD_TIME = CONTROL_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Capacitor Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_CONTROL_FEATURE_COUNT = CONTROL_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link electrickery.control.impl.GeneratorDispatcherImpl <em>Generator Dispatcher</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.impl.GeneratorDispatcherImpl
	 * @see electrickery.control.impl.ControlPackageImpl#getGeneratorDispatcher()
	 * @generated
	 */
	int GENERATOR_DISPATCHER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__NAME = CONTROL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__ENABLED = CONTROL_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__BASE_FREQ = CONTROL_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__NODE_REF = CONTROL_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__YORDER = CONTROL_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__YPRIM_INVALID = CONTROL_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__LAST_TERMINAL_CHECKED = CONTROL_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__CHECKED = CONTROL_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__HAS_METER = CONTROL_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__ISOLATED = CONTROL_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__HAS_CONTROL = CONTROL_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__PART_OF_FEEDER = CONTROL_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__CONTROL_ELEMENT = CONTROL_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__TERMINALS = CONTROL_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__ACTIVE_TERMINAL = CONTROL_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__NTERMS = CONTROL_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__NCONDS = CONTROL_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__NPHASES = CONTROL_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__BUS_INDEX = CONTROL_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__YPRIM_SERIES = CONTROL_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__YPRIM_SHUNT = CONTROL_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__YPRIM = CONTROL_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__YPRIM_FREQ = CONTROL_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__LIKE = CONTROL_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__ELEMENT_NAME = CONTROL_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Element Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__ELEMENT_TERMINAL = CONTROL_ELEMENT__ELEMENT_TERMINAL;

	/**
	 * The feature id for the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__CONTROLLED_BUS_NAME = CONTROL_ELEMENT__CONTROLLED_BUS_NAME;

	/**
	 * The feature id for the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__CONTROLLED_BUS = CONTROL_ELEMENT__CONTROLLED_BUS;

	/**
	 * The feature id for the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__MONITORED_VARIABLE = CONTROL_ELEMENT__MONITORED_VARIABLE;

	/**
	 * The feature id for the '<em><b>Monitored Var Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__MONITORED_VAR_INDEX = CONTROL_ELEMENT__MONITORED_VAR_INDEX;

	/**
	 * The feature id for the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__TIME_DELAY = CONTROL_ELEMENT__TIME_DELAY;

	/**
	 * The feature id for the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__DBL_TRACE_PARAM = CONTROL_ELEMENT__DBL_TRACE_PARAM;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__ELEMENT = CONTROL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__TERMINAL = CONTROL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>KW Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__KW_LIMIT = CONTROL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>KW Band</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__KW_BAND = CONTROL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>KVar Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__KVAR_LIMIT = CONTROL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Gen List</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__GEN_LIST = CONTROL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Weights</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER__WEIGHTS = CONTROL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Generator Dispatcher</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_DISPATCHER_FEATURE_COUNT = CONTROL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link electrickery.control.impl.RecloserImpl <em>Recloser</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.impl.RecloserImpl
	 * @see electrickery.control.impl.ControlPackageImpl#getRecloser()
	 * @generated
	 */
	int RECLOSER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__NAME = CONTROL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__ENABLED = CONTROL_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__BASE_FREQ = CONTROL_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__NODE_REF = CONTROL_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__YORDER = CONTROL_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__YPRIM_INVALID = CONTROL_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__LAST_TERMINAL_CHECKED = CONTROL_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__CHECKED = CONTROL_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__HAS_METER = CONTROL_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__ISOLATED = CONTROL_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__HAS_CONTROL = CONTROL_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__PART_OF_FEEDER = CONTROL_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__CONTROL_ELEMENT = CONTROL_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__TERMINALS = CONTROL_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__ACTIVE_TERMINAL = CONTROL_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__NTERMS = CONTROL_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__NCONDS = CONTROL_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__NPHASES = CONTROL_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__BUS_INDEX = CONTROL_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__YPRIM_SERIES = CONTROL_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__YPRIM_SHUNT = CONTROL_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__YPRIM = CONTROL_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__YPRIM_FREQ = CONTROL_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__LIKE = CONTROL_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__ELEMENT_NAME = CONTROL_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Element Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__ELEMENT_TERMINAL = CONTROL_ELEMENT__ELEMENT_TERMINAL;

	/**
	 * The feature id for the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__CONTROLLED_BUS_NAME = CONTROL_ELEMENT__CONTROLLED_BUS_NAME;

	/**
	 * The feature id for the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__CONTROLLED_BUS = CONTROL_ELEMENT__CONTROLLED_BUS;

	/**
	 * The feature id for the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__MONITORED_VARIABLE = CONTROL_ELEMENT__MONITORED_VARIABLE;

	/**
	 * The feature id for the '<em><b>Monitored Var Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__MONITORED_VAR_INDEX = CONTROL_ELEMENT__MONITORED_VAR_INDEX;

	/**
	 * The feature id for the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__TIME_DELAY = CONTROL_ELEMENT__TIME_DELAY;

	/**
	 * The feature id for the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__DBL_TRACE_PARAM = CONTROL_ELEMENT__DBL_TRACE_PARAM;

	/**
	 * The feature id for the '<em><b>Monitored Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__MONITORED_OBJ = CONTROL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Monitored Term</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__MONITORED_TERM = CONTROL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Switched Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__SWITCHED_OBJ = CONTROL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Switched Term</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__SWITCHED_TERM = CONTROL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>NFast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__NFAST = CONTROL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Phase Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__PHASE_FAST = CONTROL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Phase Delayed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__PHASE_DELAYED = CONTROL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Ground Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__GROUND_FAST = CONTROL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Ground Delayed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__GROUND_DELAYED = CONTROL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Phase Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__PHASE_TRIP = CONTROL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Ground Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__GROUND_TRIP = CONTROL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Phase Inst</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__PHASE_INST = CONTROL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Ground Inst</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__GROUND_INST = CONTROL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Reset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__RESET = CONTROL_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Shots</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__SHOTS = CONTROL_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Reclose Intervals</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__RECLOSE_INTERVALS = CONTROL_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__DELAY = CONTROL_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__ACTION = CONTROL_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>TD Ph Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__TD_PH_FAST = CONTROL_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>TD Gr Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__TD_GR_FAST = CONTROL_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>TD Ph Delayed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__TD_PH_DELAYED = CONTROL_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>TD Gr Delayed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER__TD_GR_DELAYED = CONTROL_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The number of structural features of the '<em>Recloser</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECLOSER_FEATURE_COUNT = CONTROL_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The meta object id for the '{@link electrickery.control.impl.RegulatorControlImpl <em>Regulator Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.impl.RegulatorControlImpl
	 * @see electrickery.control.impl.ControlPackageImpl#getRegulatorControl()
	 * @generated
	 */
	int REGULATOR_CONTROL = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__NAME = CONTROL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__ENABLED = CONTROL_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__BASE_FREQ = CONTROL_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__NODE_REF = CONTROL_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__YORDER = CONTROL_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__YPRIM_INVALID = CONTROL_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__LAST_TERMINAL_CHECKED = CONTROL_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__CHECKED = CONTROL_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__HAS_METER = CONTROL_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__ISOLATED = CONTROL_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__HAS_CONTROL = CONTROL_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__PART_OF_FEEDER = CONTROL_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__CONTROL_ELEMENT = CONTROL_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__TERMINALS = CONTROL_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__ACTIVE_TERMINAL = CONTROL_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__NTERMS = CONTROL_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__NCONDS = CONTROL_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__NPHASES = CONTROL_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__BUS_INDEX = CONTROL_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__YPRIM_SERIES = CONTROL_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__YPRIM_SHUNT = CONTROL_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__YPRIM = CONTROL_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__YPRIM_FREQ = CONTROL_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__LIKE = CONTROL_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__ELEMENT_NAME = CONTROL_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Element Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__ELEMENT_TERMINAL = CONTROL_ELEMENT__ELEMENT_TERMINAL;

	/**
	 * The feature id for the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__CONTROLLED_BUS_NAME = CONTROL_ELEMENT__CONTROLLED_BUS_NAME;

	/**
	 * The feature id for the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__CONTROLLED_BUS = CONTROL_ELEMENT__CONTROLLED_BUS;

	/**
	 * The feature id for the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__MONITORED_VARIABLE = CONTROL_ELEMENT__MONITORED_VARIABLE;

	/**
	 * The feature id for the '<em><b>Monitored Var Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__MONITORED_VAR_INDEX = CONTROL_ELEMENT__MONITORED_VAR_INDEX;

	/**
	 * The feature id for the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__TIME_DELAY = CONTROL_ELEMENT__TIME_DELAY;

	/**
	 * The feature id for the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__DBL_TRACE_PARAM = CONTROL_ELEMENT__DBL_TRACE_PARAM;

	/**
	 * The feature id for the '<em><b>Transformer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__TRANSFORMER = CONTROL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Winding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__WINDING = CONTROL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>VReg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__VREG = CONTROL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Band</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__BAND = CONTROL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>PT Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__PT_RATIO = CONTROL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>CT Prim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__CT_PRIM = CONTROL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__R = CONTROL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__X = CONTROL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__BUS = CONTROL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__DELAY = CONTROL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Reversible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__REVERSIBLE = CONTROL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Rev VReg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__REV_VREG = CONTROL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Rev Band</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__REV_BAND = CONTROL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Rev R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__REV_R = CONTROL_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Rev X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__REV_X = CONTROL_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Tap Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__TAP_DELAY = CONTROL_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Debug Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__DEBUG_TRACE = CONTROL_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Max Tap Change</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__MAX_TAP_CHANGE = CONTROL_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Inverse Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__INVERSE_TIME = CONTROL_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Tap Winding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__TAP_WINDING = CONTROL_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>VLimit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL__VLIMIT = CONTROL_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The number of structural features of the '<em>Regulator Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGULATOR_CONTROL_FEATURE_COUNT = CONTROL_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The meta object id for the '{@link electrickery.control.impl.RelayImpl <em>Relay</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.impl.RelayImpl
	 * @see electrickery.control.impl.ControlPackageImpl#getRelay()
	 * @generated
	 */
	int RELAY = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__NAME = CONTROL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ENABLED = CONTROL_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__BASE_FREQ = CONTROL_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__NODE_REF = CONTROL_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__YORDER = CONTROL_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__YPRIM_INVALID = CONTROL_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__LAST_TERMINAL_CHECKED = CONTROL_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__CHECKED = CONTROL_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__HAS_METER = CONTROL_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ISOLATED = CONTROL_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__HAS_CONTROL = CONTROL_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PART_OF_FEEDER = CONTROL_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__CONTROL_ELEMENT = CONTROL_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__TERMINALS = CONTROL_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ACTIVE_TERMINAL = CONTROL_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__NTERMS = CONTROL_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__NCONDS = CONTROL_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__NPHASES = CONTROL_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__BUS_INDEX = CONTROL_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__YPRIM_SERIES = CONTROL_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__YPRIM_SHUNT = CONTROL_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__YPRIM = CONTROL_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__YPRIM_FREQ = CONTROL_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__LIKE = CONTROL_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ELEMENT_NAME = CONTROL_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Element Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ELEMENT_TERMINAL = CONTROL_ELEMENT__ELEMENT_TERMINAL;

	/**
	 * The feature id for the '<em><b>Controlled Bus Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__CONTROLLED_BUS_NAME = CONTROL_ELEMENT__CONTROLLED_BUS_NAME;

	/**
	 * The feature id for the '<em><b>Controlled Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__CONTROLLED_BUS = CONTROL_ELEMENT__CONTROLLED_BUS;

	/**
	 * The feature id for the '<em><b>Monitored Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__MONITORED_VARIABLE = CONTROL_ELEMENT__MONITORED_VARIABLE;

	/**
	 * The feature id for the '<em><b>Monitored Var Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__MONITORED_VAR_INDEX = CONTROL_ELEMENT__MONITORED_VAR_INDEX;

	/**
	 * The feature id for the '<em><b>Time Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__TIME_DELAY = CONTROL_ELEMENT__TIME_DELAY;

	/**
	 * The feature id for the '<em><b>Dbl Trace Param</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__DBL_TRACE_PARAM = CONTROL_ELEMENT__DBL_TRACE_PARAM;

	/**
	 * The feature id for the '<em><b>Monitored Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__MONITORED_OBJ = CONTROL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Monitored Term</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__MONITORED_TERM = CONTROL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Switched Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__SWITCHED_OBJ = CONTROL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Switched Term</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__SWITCHED_TERM = CONTROL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__TYPE = CONTROL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Phase Curve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PHASE_CURVE = CONTROL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Ground Curve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__GROUND_CURVE = CONTROL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Phase Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PHASE_TRIP = CONTROL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Ground Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__GROUND_TRIP = CONTROL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>TD Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__TD_PHASE = CONTROL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>TD Ground</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__TD_GROUND = CONTROL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Phase Inst</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PHASE_INST = CONTROL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Ground Inst</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__GROUND_INST = CONTROL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Reset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__RESET = CONTROL_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Shots</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__SHOTS = CONTROL_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Reclose Intervals</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__RECLOSE_INTERVALS = CONTROL_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__DELAY = CONTROL_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Overvolt Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__OVERVOLT_CURVE = CONTROL_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Undervolt Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__UNDERVOLT_CURVE = CONTROL_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>KV Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__KV_BASE = CONTROL_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Pct Pickup47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PCT_PICKUP47 = CONTROL_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Pct Amps46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PCT_AMPS46 = CONTROL_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Pct Pickup46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__PCT_PICKUP46 = CONTROL_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>ISQT46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ISQT46 = CONTROL_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__VARIABLE = CONTROL_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Overtrip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__OVERTRIP = CONTROL_ELEMENT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Undertrip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__UNDERTRIP = CONTROL_ELEMENT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Breaker Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__BREAKER_TIME = CONTROL_ELEMENT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY__ACTION = CONTROL_ELEMENT_FEATURE_COUNT + 28;

	/**
	 * The number of structural features of the '<em>Relay</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELAY_FEATURE_COUNT = CONTROL_ELEMENT_FEATURE_COUNT + 29;

	/**
	 * The meta object id for the '{@link electrickery.control.controlType <em>control Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.controlType
	 * @see electrickery.control.impl.ControlPackageImpl#getcontrolType()
	 * @generated
	 */
	int CONTROL_TYPE = 6;

	/**
	 * The meta object id for the '{@link electrickery.control.relayType <em>relay Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.control.relayType
	 * @see electrickery.control.impl.ControlPackageImpl#getrelayType()
	 * @generated
	 */
	int RELAY_TYPE = 7;


	/**
	 * Returns the meta object for class '{@link electrickery.control.ControlElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see electrickery.control.ControlElement
	 * @generated
	 */
	EClass getControlElement();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getElementName <em>Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Name</em>'.
	 * @see electrickery.control.ControlElement#getElementName()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_ElementName();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getElementTerminal <em>Element Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Terminal</em>'.
	 * @see electrickery.control.ControlElement#getElementTerminal()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_ElementTerminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getControlledBusName <em>Controlled Bus Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Controlled Bus Name</em>'.
	 * @see electrickery.control.ControlElement#getControlledBusName()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_ControlledBusName();

	/**
	 * Returns the meta object for the reference '{@link electrickery.control.ControlElement#getControlledBus <em>Controlled Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Controlled Bus</em>'.
	 * @see electrickery.control.ControlElement#getControlledBus()
	 * @see #getControlElement()
	 * @generated
	 */
	EReference getControlElement_ControlledBus();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getMonitoredVariable <em>Monitored Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Variable</em>'.
	 * @see electrickery.control.ControlElement#getMonitoredVariable()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_MonitoredVariable();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getMonitoredVarIndex <em>Monitored Var Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Var Index</em>'.
	 * @see electrickery.control.ControlElement#getMonitoredVarIndex()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_MonitoredVarIndex();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getTimeDelay <em>Time Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Delay</em>'.
	 * @see electrickery.control.ControlElement#getTimeDelay()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_TimeDelay();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.ControlElement#getDblTraceParam <em>Dbl Trace Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dbl Trace Param</em>'.
	 * @see electrickery.control.ControlElement#getDblTraceParam()
	 * @see #getControlElement()
	 * @generated
	 */
	EAttribute getControlElement_DblTraceParam();

	/**
	 * Returns the meta object for class '{@link electrickery.control.CapacitorControl <em>Capacitor Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capacitor Control</em>'.
	 * @see electrickery.control.CapacitorControl
	 * @generated
	 */
	EClass getCapacitorControl();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see electrickery.control.CapacitorControl#getElement()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_Element();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminal</em>'.
	 * @see electrickery.control.CapacitorControl#getTerminal()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_Terminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getCapacitor <em>Capacitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacitor</em>'.
	 * @see electrickery.control.CapacitorControl#getCapacitor()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_Capacitor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see electrickery.control.CapacitorControl#getType()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_Type();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getPTRatio <em>PT Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PT Ratio</em>'.
	 * @see electrickery.control.CapacitorControl#getPTRatio()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_PTRatio();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getCTRatio <em>CT Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CT Ratio</em>'.
	 * @see electrickery.control.CapacitorControl#getCTRatio()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_CTRatio();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getOnSetting <em>On Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Setting</em>'.
	 * @see electrickery.control.CapacitorControl#getOnSetting()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_OnSetting();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getOffSetting <em>Off Setting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Off Setting</em>'.
	 * @see electrickery.control.CapacitorControl#getOffSetting()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_OffSetting();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see electrickery.control.CapacitorControl#getDelay()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_Delay();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#isVoltOverride <em>Volt Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Volt Override</em>'.
	 * @see electrickery.control.CapacitorControl#isVoltOverride()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_VoltOverride();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getVMax <em>VMax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMax</em>'.
	 * @see electrickery.control.CapacitorControl#getVMax()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_VMax();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getVMin <em>VMin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMin</em>'.
	 * @see electrickery.control.CapacitorControl#getVMin()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_VMin();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getDelayOff <em>Delay Off</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay Off</em>'.
	 * @see electrickery.control.CapacitorControl#getDelayOff()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_DelayOff();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.CapacitorControl#getDeadTime <em>Dead Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dead Time</em>'.
	 * @see electrickery.control.CapacitorControl#getDeadTime()
	 * @see #getCapacitorControl()
	 * @generated
	 */
	EAttribute getCapacitorControl_DeadTime();

	/**
	 * Returns the meta object for class '{@link electrickery.control.GeneratorDispatcher <em>Generator Dispatcher</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator Dispatcher</em>'.
	 * @see electrickery.control.GeneratorDispatcher
	 * @generated
	 */
	EClass getGeneratorDispatcher();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.GeneratorDispatcher#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getElement()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EAttribute getGeneratorDispatcher_Element();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.GeneratorDispatcher#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminal</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getTerminal()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EAttribute getGeneratorDispatcher_Terminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.GeneratorDispatcher#getKWLimit <em>KW Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KW Limit</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getKWLimit()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EAttribute getGeneratorDispatcher_KWLimit();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.GeneratorDispatcher#getKWBand <em>KW Band</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KW Band</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getKWBand()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EAttribute getGeneratorDispatcher_KWBand();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.GeneratorDispatcher#getKVarLimit <em>KVar Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KVar Limit</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getKVarLimit()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EAttribute getGeneratorDispatcher_KVarLimit();

	/**
	 * Returns the meta object for the reference list '{@link electrickery.control.GeneratorDispatcher#getGenList <em>Gen List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen List</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getGenList()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EReference getGeneratorDispatcher_GenList();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.control.GeneratorDispatcher#getWeights <em>Weights</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Weights</em>'.
	 * @see electrickery.control.GeneratorDispatcher#getWeights()
	 * @see #getGeneratorDispatcher()
	 * @generated
	 */
	EAttribute getGeneratorDispatcher_Weights();

	/**
	 * Returns the meta object for class '{@link electrickery.control.Recloser <em>Recloser</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Recloser</em>'.
	 * @see electrickery.control.Recloser
	 * @generated
	 */
	EClass getRecloser();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getMonitoredObj <em>Monitored Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Obj</em>'.
	 * @see electrickery.control.Recloser#getMonitoredObj()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_MonitoredObj();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getMonitoredTerm <em>Monitored Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Term</em>'.
	 * @see electrickery.control.Recloser#getMonitoredTerm()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_MonitoredTerm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getSwitchedObj <em>Switched Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switched Obj</em>'.
	 * @see electrickery.control.Recloser#getSwitchedObj()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_SwitchedObj();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getSwitchedTerm <em>Switched Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switched Term</em>'.
	 * @see electrickery.control.Recloser#getSwitchedTerm()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_SwitchedTerm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getNFast <em>NFast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NFast</em>'.
	 * @see electrickery.control.Recloser#getNFast()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_NFast();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getPhaseFast <em>Phase Fast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Fast</em>'.
	 * @see electrickery.control.Recloser#getPhaseFast()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_PhaseFast();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getPhaseDelayed <em>Phase Delayed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Delayed</em>'.
	 * @see electrickery.control.Recloser#getPhaseDelayed()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_PhaseDelayed();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getGroundFast <em>Ground Fast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Fast</em>'.
	 * @see electrickery.control.Recloser#getGroundFast()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_GroundFast();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getGroundDelayed <em>Ground Delayed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Delayed</em>'.
	 * @see electrickery.control.Recloser#getGroundDelayed()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_GroundDelayed();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getPhaseTrip <em>Phase Trip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Trip</em>'.
	 * @see electrickery.control.Recloser#getPhaseTrip()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_PhaseTrip();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getGroundTrip <em>Ground Trip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Trip</em>'.
	 * @see electrickery.control.Recloser#getGroundTrip()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_GroundTrip();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getPhaseInst <em>Phase Inst</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Inst</em>'.
	 * @see electrickery.control.Recloser#getPhaseInst()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_PhaseInst();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getGroundInst <em>Ground Inst</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Inst</em>'.
	 * @see electrickery.control.Recloser#getGroundInst()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_GroundInst();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getReset <em>Reset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reset</em>'.
	 * @see electrickery.control.Recloser#getReset()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_Reset();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getShots <em>Shots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shots</em>'.
	 * @see electrickery.control.Recloser#getShots()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_Shots();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.control.Recloser#getRecloseIntervals <em>Reclose Intervals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Reclose Intervals</em>'.
	 * @see electrickery.control.Recloser#getRecloseIntervals()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_RecloseIntervals();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see electrickery.control.Recloser#getDelay()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_Delay();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see electrickery.control.Recloser#getAction()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_Action();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getTDPhFast <em>TD Ph Fast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>TD Ph Fast</em>'.
	 * @see electrickery.control.Recloser#getTDPhFast()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_TDPhFast();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getTDGrFast <em>TD Gr Fast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>TD Gr Fast</em>'.
	 * @see electrickery.control.Recloser#getTDGrFast()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_TDGrFast();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getTDPhDelayed <em>TD Ph Delayed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>TD Ph Delayed</em>'.
	 * @see electrickery.control.Recloser#getTDPhDelayed()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_TDPhDelayed();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Recloser#getTDGrDelayed <em>TD Gr Delayed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>TD Gr Delayed</em>'.
	 * @see electrickery.control.Recloser#getTDGrDelayed()
	 * @see #getRecloser()
	 * @generated
	 */
	EAttribute getRecloser_TDGrDelayed();

	/**
	 * Returns the meta object for class '{@link electrickery.control.RegulatorControl <em>Regulator Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Regulator Control</em>'.
	 * @see electrickery.control.RegulatorControl
	 * @generated
	 */
	EClass getRegulatorControl();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getTransformer <em>Transformer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transformer</em>'.
	 * @see electrickery.control.RegulatorControl#getTransformer()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_Transformer();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getWinding <em>Winding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Winding</em>'.
	 * @see electrickery.control.RegulatorControl#getWinding()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_Winding();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getVReg <em>VReg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VReg</em>'.
	 * @see electrickery.control.RegulatorControl#getVReg()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_VReg();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getBand <em>Band</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Band</em>'.
	 * @see electrickery.control.RegulatorControl#getBand()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_Band();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getPTRatio <em>PT Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PT Ratio</em>'.
	 * @see electrickery.control.RegulatorControl#getPTRatio()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_PTRatio();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getCTPrim <em>CT Prim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CT Prim</em>'.
	 * @see electrickery.control.RegulatorControl#getCTPrim()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_CTPrim();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getR <em>R</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R</em>'.
	 * @see electrickery.control.RegulatorControl#getR()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_R();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see electrickery.control.RegulatorControl#getX()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_X();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getBus <em>Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus</em>'.
	 * @see electrickery.control.RegulatorControl#getBus()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_Bus();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see electrickery.control.RegulatorControl#getDelay()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_Delay();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#isReversible <em>Reversible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reversible</em>'.
	 * @see electrickery.control.RegulatorControl#isReversible()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_Reversible();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getRevVReg <em>Rev VReg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rev VReg</em>'.
	 * @see electrickery.control.RegulatorControl#getRevVReg()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_RevVReg();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getRevBand <em>Rev Band</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rev Band</em>'.
	 * @see electrickery.control.RegulatorControl#getRevBand()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_RevBand();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getRevR <em>Rev R</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rev R</em>'.
	 * @see electrickery.control.RegulatorControl#getRevR()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_RevR();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getRevX <em>Rev X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rev X</em>'.
	 * @see electrickery.control.RegulatorControl#getRevX()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_RevX();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getTapDelay <em>Tap Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tap Delay</em>'.
	 * @see electrickery.control.RegulatorControl#getTapDelay()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_TapDelay();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#isDebugTrace <em>Debug Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug Trace</em>'.
	 * @see electrickery.control.RegulatorControl#isDebugTrace()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_DebugTrace();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getMaxTapChange <em>Max Tap Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Tap Change</em>'.
	 * @see electrickery.control.RegulatorControl#getMaxTapChange()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_MaxTapChange();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#isInverseTime <em>Inverse Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inverse Time</em>'.
	 * @see electrickery.control.RegulatorControl#isInverseTime()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_InverseTime();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getTapWinding <em>Tap Winding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tap Winding</em>'.
	 * @see electrickery.control.RegulatorControl#getTapWinding()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_TapWinding();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.RegulatorControl#getVLimit <em>VLimit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VLimit</em>'.
	 * @see electrickery.control.RegulatorControl#getVLimit()
	 * @see #getRegulatorControl()
	 * @generated
	 */
	EAttribute getRegulatorControl_VLimit();

	/**
	 * Returns the meta object for class '{@link electrickery.control.Relay <em>Relay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relay</em>'.
	 * @see electrickery.control.Relay
	 * @generated
	 */
	EClass getRelay();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getMonitoredObj <em>Monitored Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Obj</em>'.
	 * @see electrickery.control.Relay#getMonitoredObj()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_MonitoredObj();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getMonitoredTerm <em>Monitored Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Term</em>'.
	 * @see electrickery.control.Relay#getMonitoredTerm()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_MonitoredTerm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getSwitchedObj <em>Switched Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switched Obj</em>'.
	 * @see electrickery.control.Relay#getSwitchedObj()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_SwitchedObj();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getSwitchedTerm <em>Switched Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switched Term</em>'.
	 * @see electrickery.control.Relay#getSwitchedTerm()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_SwitchedTerm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see electrickery.control.Relay#getType()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Type();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getPhaseCurve <em>Phase Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Curve</em>'.
	 * @see electrickery.control.Relay#getPhaseCurve()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_PhaseCurve();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getGroundCurve <em>Ground Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Curve</em>'.
	 * @see electrickery.control.Relay#getGroundCurve()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_GroundCurve();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getPhaseTrip <em>Phase Trip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Trip</em>'.
	 * @see electrickery.control.Relay#getPhaseTrip()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_PhaseTrip();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getGroundTrip <em>Ground Trip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Trip</em>'.
	 * @see electrickery.control.Relay#getGroundTrip()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_GroundTrip();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getTDPhase <em>TD Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>TD Phase</em>'.
	 * @see electrickery.control.Relay#getTDPhase()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_TDPhase();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getTDGround <em>TD Ground</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>TD Ground</em>'.
	 * @see electrickery.control.Relay#getTDGround()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_TDGround();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getPhaseInst <em>Phase Inst</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Inst</em>'.
	 * @see electrickery.control.Relay#getPhaseInst()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_PhaseInst();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getGroundInst <em>Ground Inst</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ground Inst</em>'.
	 * @see electrickery.control.Relay#getGroundInst()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_GroundInst();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getReset <em>Reset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reset</em>'.
	 * @see electrickery.control.Relay#getReset()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Reset();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getShots <em>Shots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shots</em>'.
	 * @see electrickery.control.Relay#getShots()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Shots();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.control.Relay#getRecloseIntervals <em>Reclose Intervals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Reclose Intervals</em>'.
	 * @see electrickery.control.Relay#getRecloseIntervals()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_RecloseIntervals();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see electrickery.control.Relay#getDelay()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Delay();

	/**
	 * Returns the meta object for the reference '{@link electrickery.control.Relay#getOvervoltCurve <em>Overvolt Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Overvolt Curve</em>'.
	 * @see electrickery.control.Relay#getOvervoltCurve()
	 * @see #getRelay()
	 * @generated
	 */
	EReference getRelay_OvervoltCurve();

	/**
	 * Returns the meta object for the reference '{@link electrickery.control.Relay#getUndervoltCurve <em>Undervolt Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Undervolt Curve</em>'.
	 * @see electrickery.control.Relay#getUndervoltCurve()
	 * @see #getRelay()
	 * @generated
	 */
	EReference getRelay_UndervoltCurve();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getKVBase <em>KV Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Base</em>'.
	 * @see electrickery.control.Relay#getKVBase()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_KVBase();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getPctPickup47 <em>Pct Pickup47</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Pickup47</em>'.
	 * @see electrickery.control.Relay#getPctPickup47()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_PctPickup47();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getPctAmps46 <em>Pct Amps46</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Amps46</em>'.
	 * @see electrickery.control.Relay#getPctAmps46()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_PctAmps46();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getPctPickup46 <em>Pct Pickup46</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Pickup46</em>'.
	 * @see electrickery.control.Relay#getPctPickup46()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_PctPickup46();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getISQT46 <em>ISQT46</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ISQT46</em>'.
	 * @see electrickery.control.Relay#getISQT46()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_ISQT46();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable</em>'.
	 * @see electrickery.control.Relay#getVariable()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Variable();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getOvertrip <em>Overtrip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtrip</em>'.
	 * @see electrickery.control.Relay#getOvertrip()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Overtrip();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getUndertrip <em>Undertrip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Undertrip</em>'.
	 * @see electrickery.control.Relay#getUndertrip()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Undertrip();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getBreakerTime <em>Breaker Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Breaker Time</em>'.
	 * @see electrickery.control.Relay#getBreakerTime()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_BreakerTime();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.control.Relay#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see electrickery.control.Relay#getAction()
	 * @see #getRelay()
	 * @generated
	 */
	EAttribute getRelay_Action();

	/**
	 * Returns the meta object for enum '{@link electrickery.control.controlType <em>control Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>control Type</em>'.
	 * @see electrickery.control.controlType
	 * @generated
	 */
	EEnum getcontrolType();

	/**
	 * Returns the meta object for enum '{@link electrickery.control.relayType <em>relay Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>relay Type</em>'.
	 * @see electrickery.control.relayType
	 * @generated
	 */
	EEnum getrelayType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ControlFactory getControlFactory();

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
		 * The meta object literal for the '{@link electrickery.control.impl.ControlElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.impl.ControlElementImpl
		 * @see electrickery.control.impl.ControlPackageImpl#getControlElement()
		 * @generated
		 */
		EClass CONTROL_ELEMENT = eINSTANCE.getControlElement();

		/**
		 * The meta object literal for the '<em><b>Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__ELEMENT_NAME = eINSTANCE.getControlElement_ElementName();

		/**
		 * The meta object literal for the '<em><b>Element Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__ELEMENT_TERMINAL = eINSTANCE.getControlElement_ElementTerminal();

		/**
		 * The meta object literal for the '<em><b>Controlled Bus Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__CONTROLLED_BUS_NAME = eINSTANCE.getControlElement_ControlledBusName();

		/**
		 * The meta object literal for the '<em><b>Controlled Bus</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_ELEMENT__CONTROLLED_BUS = eINSTANCE.getControlElement_ControlledBus();

		/**
		 * The meta object literal for the '<em><b>Monitored Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__MONITORED_VARIABLE = eINSTANCE.getControlElement_MonitoredVariable();

		/**
		 * The meta object literal for the '<em><b>Monitored Var Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__MONITORED_VAR_INDEX = eINSTANCE.getControlElement_MonitoredVarIndex();

		/**
		 * The meta object literal for the '<em><b>Time Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__TIME_DELAY = eINSTANCE.getControlElement_TimeDelay();

		/**
		 * The meta object literal for the '<em><b>Dbl Trace Param</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_ELEMENT__DBL_TRACE_PARAM = eINSTANCE.getControlElement_DblTraceParam();

		/**
		 * The meta object literal for the '{@link electrickery.control.impl.CapacitorControlImpl <em>Capacitor Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.impl.CapacitorControlImpl
		 * @see electrickery.control.impl.ControlPackageImpl#getCapacitorControl()
		 * @generated
		 */
		EClass CAPACITOR_CONTROL = eINSTANCE.getCapacitorControl();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__ELEMENT = eINSTANCE.getCapacitorControl_Element();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__TERMINAL = eINSTANCE.getCapacitorControl_Terminal();

		/**
		 * The meta object literal for the '<em><b>Capacitor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__CAPACITOR = eINSTANCE.getCapacitorControl_Capacitor();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__TYPE = eINSTANCE.getCapacitorControl_Type();

		/**
		 * The meta object literal for the '<em><b>PT Ratio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__PT_RATIO = eINSTANCE.getCapacitorControl_PTRatio();

		/**
		 * The meta object literal for the '<em><b>CT Ratio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__CT_RATIO = eINSTANCE.getCapacitorControl_CTRatio();

		/**
		 * The meta object literal for the '<em><b>On Setting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__ON_SETTING = eINSTANCE.getCapacitorControl_OnSetting();

		/**
		 * The meta object literal for the '<em><b>Off Setting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__OFF_SETTING = eINSTANCE.getCapacitorControl_OffSetting();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__DELAY = eINSTANCE.getCapacitorControl_Delay();

		/**
		 * The meta object literal for the '<em><b>Volt Override</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__VOLT_OVERRIDE = eINSTANCE.getCapacitorControl_VoltOverride();

		/**
		 * The meta object literal for the '<em><b>VMax</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__VMAX = eINSTANCE.getCapacitorControl_VMax();

		/**
		 * The meta object literal for the '<em><b>VMin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__VMIN = eINSTANCE.getCapacitorControl_VMin();

		/**
		 * The meta object literal for the '<em><b>Delay Off</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__DELAY_OFF = eINSTANCE.getCapacitorControl_DelayOff();

		/**
		 * The meta object literal for the '<em><b>Dead Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR_CONTROL__DEAD_TIME = eINSTANCE.getCapacitorControl_DeadTime();

		/**
		 * The meta object literal for the '{@link electrickery.control.impl.GeneratorDispatcherImpl <em>Generator Dispatcher</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.impl.GeneratorDispatcherImpl
		 * @see electrickery.control.impl.ControlPackageImpl#getGeneratorDispatcher()
		 * @generated
		 */
		EClass GENERATOR_DISPATCHER = eINSTANCE.getGeneratorDispatcher();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_DISPATCHER__ELEMENT = eINSTANCE.getGeneratorDispatcher_Element();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_DISPATCHER__TERMINAL = eINSTANCE.getGeneratorDispatcher_Terminal();

		/**
		 * The meta object literal for the '<em><b>KW Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_DISPATCHER__KW_LIMIT = eINSTANCE.getGeneratorDispatcher_KWLimit();

		/**
		 * The meta object literal for the '<em><b>KW Band</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_DISPATCHER__KW_BAND = eINSTANCE.getGeneratorDispatcher_KWBand();

		/**
		 * The meta object literal for the '<em><b>KVar Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_DISPATCHER__KVAR_LIMIT = eINSTANCE.getGeneratorDispatcher_KVarLimit();

		/**
		 * The meta object literal for the '<em><b>Gen List</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERATOR_DISPATCHER__GEN_LIST = eINSTANCE.getGeneratorDispatcher_GenList();

		/**
		 * The meta object literal for the '<em><b>Weights</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_DISPATCHER__WEIGHTS = eINSTANCE.getGeneratorDispatcher_Weights();

		/**
		 * The meta object literal for the '{@link electrickery.control.impl.RecloserImpl <em>Recloser</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.impl.RecloserImpl
		 * @see electrickery.control.impl.ControlPackageImpl#getRecloser()
		 * @generated
		 */
		EClass RECLOSER = eINSTANCE.getRecloser();

		/**
		 * The meta object literal for the '<em><b>Monitored Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__MONITORED_OBJ = eINSTANCE.getRecloser_MonitoredObj();

		/**
		 * The meta object literal for the '<em><b>Monitored Term</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__MONITORED_TERM = eINSTANCE.getRecloser_MonitoredTerm();

		/**
		 * The meta object literal for the '<em><b>Switched Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__SWITCHED_OBJ = eINSTANCE.getRecloser_SwitchedObj();

		/**
		 * The meta object literal for the '<em><b>Switched Term</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__SWITCHED_TERM = eINSTANCE.getRecloser_SwitchedTerm();

		/**
		 * The meta object literal for the '<em><b>NFast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__NFAST = eINSTANCE.getRecloser_NFast();

		/**
		 * The meta object literal for the '<em><b>Phase Fast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__PHASE_FAST = eINSTANCE.getRecloser_PhaseFast();

		/**
		 * The meta object literal for the '<em><b>Phase Delayed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__PHASE_DELAYED = eINSTANCE.getRecloser_PhaseDelayed();

		/**
		 * The meta object literal for the '<em><b>Ground Fast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__GROUND_FAST = eINSTANCE.getRecloser_GroundFast();

		/**
		 * The meta object literal for the '<em><b>Ground Delayed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__GROUND_DELAYED = eINSTANCE.getRecloser_GroundDelayed();

		/**
		 * The meta object literal for the '<em><b>Phase Trip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__PHASE_TRIP = eINSTANCE.getRecloser_PhaseTrip();

		/**
		 * The meta object literal for the '<em><b>Ground Trip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__GROUND_TRIP = eINSTANCE.getRecloser_GroundTrip();

		/**
		 * The meta object literal for the '<em><b>Phase Inst</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__PHASE_INST = eINSTANCE.getRecloser_PhaseInst();

		/**
		 * The meta object literal for the '<em><b>Ground Inst</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__GROUND_INST = eINSTANCE.getRecloser_GroundInst();

		/**
		 * The meta object literal for the '<em><b>Reset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__RESET = eINSTANCE.getRecloser_Reset();

		/**
		 * The meta object literal for the '<em><b>Shots</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__SHOTS = eINSTANCE.getRecloser_Shots();

		/**
		 * The meta object literal for the '<em><b>Reclose Intervals</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__RECLOSE_INTERVALS = eINSTANCE.getRecloser_RecloseIntervals();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__DELAY = eINSTANCE.getRecloser_Delay();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__ACTION = eINSTANCE.getRecloser_Action();

		/**
		 * The meta object literal for the '<em><b>TD Ph Fast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__TD_PH_FAST = eINSTANCE.getRecloser_TDPhFast();

		/**
		 * The meta object literal for the '<em><b>TD Gr Fast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__TD_GR_FAST = eINSTANCE.getRecloser_TDGrFast();

		/**
		 * The meta object literal for the '<em><b>TD Ph Delayed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__TD_PH_DELAYED = eINSTANCE.getRecloser_TDPhDelayed();

		/**
		 * The meta object literal for the '<em><b>TD Gr Delayed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECLOSER__TD_GR_DELAYED = eINSTANCE.getRecloser_TDGrDelayed();

		/**
		 * The meta object literal for the '{@link electrickery.control.impl.RegulatorControlImpl <em>Regulator Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.impl.RegulatorControlImpl
		 * @see electrickery.control.impl.ControlPackageImpl#getRegulatorControl()
		 * @generated
		 */
		EClass REGULATOR_CONTROL = eINSTANCE.getRegulatorControl();

		/**
		 * The meta object literal for the '<em><b>Transformer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__TRANSFORMER = eINSTANCE.getRegulatorControl_Transformer();

		/**
		 * The meta object literal for the '<em><b>Winding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__WINDING = eINSTANCE.getRegulatorControl_Winding();

		/**
		 * The meta object literal for the '<em><b>VReg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__VREG = eINSTANCE.getRegulatorControl_VReg();

		/**
		 * The meta object literal for the '<em><b>Band</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__BAND = eINSTANCE.getRegulatorControl_Band();

		/**
		 * The meta object literal for the '<em><b>PT Ratio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__PT_RATIO = eINSTANCE.getRegulatorControl_PTRatio();

		/**
		 * The meta object literal for the '<em><b>CT Prim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__CT_PRIM = eINSTANCE.getRegulatorControl_CTPrim();

		/**
		 * The meta object literal for the '<em><b>R</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__R = eINSTANCE.getRegulatorControl_R();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__X = eINSTANCE.getRegulatorControl_X();

		/**
		 * The meta object literal for the '<em><b>Bus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__BUS = eINSTANCE.getRegulatorControl_Bus();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__DELAY = eINSTANCE.getRegulatorControl_Delay();

		/**
		 * The meta object literal for the '<em><b>Reversible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__REVERSIBLE = eINSTANCE.getRegulatorControl_Reversible();

		/**
		 * The meta object literal for the '<em><b>Rev VReg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__REV_VREG = eINSTANCE.getRegulatorControl_RevVReg();

		/**
		 * The meta object literal for the '<em><b>Rev Band</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__REV_BAND = eINSTANCE.getRegulatorControl_RevBand();

		/**
		 * The meta object literal for the '<em><b>Rev R</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__REV_R = eINSTANCE.getRegulatorControl_RevR();

		/**
		 * The meta object literal for the '<em><b>Rev X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__REV_X = eINSTANCE.getRegulatorControl_RevX();

		/**
		 * The meta object literal for the '<em><b>Tap Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__TAP_DELAY = eINSTANCE.getRegulatorControl_TapDelay();

		/**
		 * The meta object literal for the '<em><b>Debug Trace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__DEBUG_TRACE = eINSTANCE.getRegulatorControl_DebugTrace();

		/**
		 * The meta object literal for the '<em><b>Max Tap Change</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__MAX_TAP_CHANGE = eINSTANCE.getRegulatorControl_MaxTapChange();

		/**
		 * The meta object literal for the '<em><b>Inverse Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__INVERSE_TIME = eINSTANCE.getRegulatorControl_InverseTime();

		/**
		 * The meta object literal for the '<em><b>Tap Winding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__TAP_WINDING = eINSTANCE.getRegulatorControl_TapWinding();

		/**
		 * The meta object literal for the '<em><b>VLimit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGULATOR_CONTROL__VLIMIT = eINSTANCE.getRegulatorControl_VLimit();

		/**
		 * The meta object literal for the '{@link electrickery.control.impl.RelayImpl <em>Relay</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.impl.RelayImpl
		 * @see electrickery.control.impl.ControlPackageImpl#getRelay()
		 * @generated
		 */
		EClass RELAY = eINSTANCE.getRelay();

		/**
		 * The meta object literal for the '<em><b>Monitored Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__MONITORED_OBJ = eINSTANCE.getRelay_MonitoredObj();

		/**
		 * The meta object literal for the '<em><b>Monitored Term</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__MONITORED_TERM = eINSTANCE.getRelay_MonitoredTerm();

		/**
		 * The meta object literal for the '<em><b>Switched Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__SWITCHED_OBJ = eINSTANCE.getRelay_SwitchedObj();

		/**
		 * The meta object literal for the '<em><b>Switched Term</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__SWITCHED_TERM = eINSTANCE.getRelay_SwitchedTerm();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__TYPE = eINSTANCE.getRelay_Type();

		/**
		 * The meta object literal for the '<em><b>Phase Curve</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__PHASE_CURVE = eINSTANCE.getRelay_PhaseCurve();

		/**
		 * The meta object literal for the '<em><b>Ground Curve</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__GROUND_CURVE = eINSTANCE.getRelay_GroundCurve();

		/**
		 * The meta object literal for the '<em><b>Phase Trip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__PHASE_TRIP = eINSTANCE.getRelay_PhaseTrip();

		/**
		 * The meta object literal for the '<em><b>Ground Trip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__GROUND_TRIP = eINSTANCE.getRelay_GroundTrip();

		/**
		 * The meta object literal for the '<em><b>TD Phase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__TD_PHASE = eINSTANCE.getRelay_TDPhase();

		/**
		 * The meta object literal for the '<em><b>TD Ground</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__TD_GROUND = eINSTANCE.getRelay_TDGround();

		/**
		 * The meta object literal for the '<em><b>Phase Inst</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__PHASE_INST = eINSTANCE.getRelay_PhaseInst();

		/**
		 * The meta object literal for the '<em><b>Ground Inst</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__GROUND_INST = eINSTANCE.getRelay_GroundInst();

		/**
		 * The meta object literal for the '<em><b>Reset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__RESET = eINSTANCE.getRelay_Reset();

		/**
		 * The meta object literal for the '<em><b>Shots</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__SHOTS = eINSTANCE.getRelay_Shots();

		/**
		 * The meta object literal for the '<em><b>Reclose Intervals</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__RECLOSE_INTERVALS = eINSTANCE.getRelay_RecloseIntervals();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__DELAY = eINSTANCE.getRelay_Delay();

		/**
		 * The meta object literal for the '<em><b>Overvolt Curve</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELAY__OVERVOLT_CURVE = eINSTANCE.getRelay_OvervoltCurve();

		/**
		 * The meta object literal for the '<em><b>Undervolt Curve</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELAY__UNDERVOLT_CURVE = eINSTANCE.getRelay_UndervoltCurve();

		/**
		 * The meta object literal for the '<em><b>KV Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__KV_BASE = eINSTANCE.getRelay_KVBase();

		/**
		 * The meta object literal for the '<em><b>Pct Pickup47</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__PCT_PICKUP47 = eINSTANCE.getRelay_PctPickup47();

		/**
		 * The meta object literal for the '<em><b>Pct Amps46</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__PCT_AMPS46 = eINSTANCE.getRelay_PctAmps46();

		/**
		 * The meta object literal for the '<em><b>Pct Pickup46</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__PCT_PICKUP46 = eINSTANCE.getRelay_PctPickup46();

		/**
		 * The meta object literal for the '<em><b>ISQT46</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__ISQT46 = eINSTANCE.getRelay_ISQT46();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__VARIABLE = eINSTANCE.getRelay_Variable();

		/**
		 * The meta object literal for the '<em><b>Overtrip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__OVERTRIP = eINSTANCE.getRelay_Overtrip();

		/**
		 * The meta object literal for the '<em><b>Undertrip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__UNDERTRIP = eINSTANCE.getRelay_Undertrip();

		/**
		 * The meta object literal for the '<em><b>Breaker Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__BREAKER_TIME = eINSTANCE.getRelay_BreakerTime();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELAY__ACTION = eINSTANCE.getRelay_Action();

		/**
		 * The meta object literal for the '{@link electrickery.control.controlType <em>control Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.controlType
		 * @see electrickery.control.impl.ControlPackageImpl#getcontrolType()
		 * @generated
		 */
		EEnum CONTROL_TYPE = eINSTANCE.getcontrolType();

		/**
		 * The meta object literal for the '{@link electrickery.control.relayType <em>relay Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.control.relayType
		 * @see electrickery.control.impl.ControlPackageImpl#getrelayType()
		 * @generated
		 */
		EEnum RELAY_TYPE = eINSTANCE.getrelayType();

	}

} //ControlPackage
