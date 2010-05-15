/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery;

import dss.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see dss.delivery.DeliveryFactory
 * @model kind="package"
 * @generated
 */
public interface DeliveryPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "delivery";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.openpowersystem.com/dss/delivery";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "delivery";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeliveryPackage eINSTANCE = dss.delivery.impl.DeliveryPackageImpl.init();

	/**
	 * The meta object id for the '{@link dss.delivery.impl.PowerDeliveryElementImpl <em>Power Delivery Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.PowerDeliveryElementImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getPowerDeliveryElement()
	 * @generated
	 */
	int POWER_DELIVERY_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__NAME = CommonPackage.CIRCUIT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__ENABLED = CommonPackage.CIRCUIT_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__BASE_FREQ = CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__NODE_REF = CommonPackage.CIRCUIT_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__YORDER = CommonPackage.CIRCUIT_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__YPRIM_INVALID = CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED = CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__CHECKED = CommonPackage.CIRCUIT_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__HAS_METER = CommonPackage.CIRCUIT_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__ISOLATED = CommonPackage.CIRCUIT_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__HAS_CONTROL = CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__PART_OF_FEEDER = CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT = CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__TERMINALS = CommonPackage.CIRCUIT_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL = CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__NTERMS = CommonPackage.CIRCUIT_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__NCONDS = CommonPackage.CIRCUIT_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__NPHASES = CommonPackage.CIRCUIT_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__BUS_INDEX = CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__YPRIM_SERIES = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__YPRIM_SHUNT = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__YPRIM = CommonPackage.CIRCUIT_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__YPRIM_FREQ = CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__NORM_AMPS = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__EMERG_AMPS = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__FAULT_RATE = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__PCT_PERM = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT__REPAIR = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Power Delivery Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_DELIVERY_ELEMENT_FEATURE_COUNT = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link dss.delivery.impl.CapacitorImpl <em>Capacitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.CapacitorImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getCapacitor()
	 * @generated
	 */
	int CAPACITOR = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NAME = POWER_DELIVERY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__ENABLED = POWER_DELIVERY_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__BASE_FREQ = POWER_DELIVERY_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NODE_REF = POWER_DELIVERY_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__YORDER = POWER_DELIVERY_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__YPRIM_INVALID = POWER_DELIVERY_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__LAST_TERMINAL_CHECKED = POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__CHECKED = POWER_DELIVERY_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__HAS_METER = POWER_DELIVERY_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__ISOLATED = POWER_DELIVERY_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__HAS_CONTROL = POWER_DELIVERY_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__PART_OF_FEEDER = POWER_DELIVERY_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__CONTROL_ELEMENT = POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__TERMINALS = POWER_DELIVERY_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__ACTIVE_TERMINAL = POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NTERMS = POWER_DELIVERY_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NCONDS = POWER_DELIVERY_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NPHASES = POWER_DELIVERY_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__BUS_INDEX = POWER_DELIVERY_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__YPRIM_SERIES = POWER_DELIVERY_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__YPRIM_SHUNT = POWER_DELIVERY_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__YPRIM = POWER_DELIVERY_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__YPRIM_FREQ = POWER_DELIVERY_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NORM_AMPS = POWER_DELIVERY_ELEMENT__NORM_AMPS;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__EMERG_AMPS = POWER_DELIVERY_ELEMENT__EMERG_AMPS;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__FAULT_RATE = POWER_DELIVERY_ELEMENT__FAULT_RATE;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__PCT_PERM = POWER_DELIVERY_ELEMENT__PCT_PERM;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__REPAIR = POWER_DELIVERY_ELEMENT__REPAIR;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__BUS1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__BUS2 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__KV_AR = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__KV = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__CONN = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>CMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__CMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Cuf</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__CUF = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>R</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__R = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Xl</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__XL = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Harm</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__HARM = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>NSteps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__NSTEPS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>States</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR__STATES = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Capacitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAPACITOR_FEATURE_COUNT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link dss.delivery.impl.FaultImpl <em>Fault</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.FaultImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getFault()
	 * @generated
	 */
	int FAULT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__NAME = POWER_DELIVERY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__ENABLED = POWER_DELIVERY_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__BASE_FREQ = POWER_DELIVERY_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__NODE_REF = POWER_DELIVERY_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__YORDER = POWER_DELIVERY_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__YPRIM_INVALID = POWER_DELIVERY_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__LAST_TERMINAL_CHECKED = POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__CHECKED = POWER_DELIVERY_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__HAS_METER = POWER_DELIVERY_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__ISOLATED = POWER_DELIVERY_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__HAS_CONTROL = POWER_DELIVERY_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__PART_OF_FEEDER = POWER_DELIVERY_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__CONTROL_ELEMENT = POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__TERMINALS = POWER_DELIVERY_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__ACTIVE_TERMINAL = POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__NTERMS = POWER_DELIVERY_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__NCONDS = POWER_DELIVERY_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__NPHASES = POWER_DELIVERY_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__BUS_INDEX = POWER_DELIVERY_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__YPRIM_SERIES = POWER_DELIVERY_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__YPRIM_SHUNT = POWER_DELIVERY_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__YPRIM = POWER_DELIVERY_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__YPRIM_FREQ = POWER_DELIVERY_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__NORM_AMPS = POWER_DELIVERY_ELEMENT__NORM_AMPS;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__EMERG_AMPS = POWER_DELIVERY_ELEMENT__EMERG_AMPS;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__FAULT_RATE = POWER_DELIVERY_ELEMENT__FAULT_RATE;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__PCT_PERM = POWER_DELIVERY_ELEMENT__PCT_PERM;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__REPAIR = POWER_DELIVERY_ELEMENT__REPAIR;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__BUS1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__BUS2 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__R = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Pct Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__PCT_STD_DEV = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>GMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__GMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>On Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__ON_TIME = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Temporary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__TEMPORARY = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Min Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT__MIN_AMPS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Fault</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_FEATURE_COUNT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link dss.delivery.impl.FuseImpl <em>Fuse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.FuseImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getFuse()
	 * @generated
	 */
	int FUSE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__NAME = POWER_DELIVERY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__ENABLED = POWER_DELIVERY_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__BASE_FREQ = POWER_DELIVERY_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__NODE_REF = POWER_DELIVERY_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__YORDER = POWER_DELIVERY_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__YPRIM_INVALID = POWER_DELIVERY_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__LAST_TERMINAL_CHECKED = POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__CHECKED = POWER_DELIVERY_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__HAS_METER = POWER_DELIVERY_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__ISOLATED = POWER_DELIVERY_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__HAS_CONTROL = POWER_DELIVERY_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__PART_OF_FEEDER = POWER_DELIVERY_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__CONTROL_ELEMENT = POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__TERMINALS = POWER_DELIVERY_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__ACTIVE_TERMINAL = POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__NTERMS = POWER_DELIVERY_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__NCONDS = POWER_DELIVERY_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__NPHASES = POWER_DELIVERY_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__BUS_INDEX = POWER_DELIVERY_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__YPRIM_SERIES = POWER_DELIVERY_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__YPRIM_SHUNT = POWER_DELIVERY_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__YPRIM = POWER_DELIVERY_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__YPRIM_FREQ = POWER_DELIVERY_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__NORM_AMPS = POWER_DELIVERY_ELEMENT__NORM_AMPS;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__EMERG_AMPS = POWER_DELIVERY_ELEMENT__EMERG_AMPS;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__FAULT_RATE = POWER_DELIVERY_ELEMENT__FAULT_RATE;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__PCT_PERM = POWER_DELIVERY_ELEMENT__PCT_PERM;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__REPAIR = POWER_DELIVERY_ELEMENT__REPAIR;

	/**
	 * The feature id for the '<em><b>Monitored Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__MONITORED_OBJ = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Monitor Term</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__MONITOR_TERM = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Switched Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__SWITCHED_OBJ = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Switched Term</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__SWITCHED_TERM = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fuse Curve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__FUSE_CURVE = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Rated Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__RATED_CURRENT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__DELAY = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE__ACTION = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Fuse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUSE_FEATURE_COUNT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link dss.delivery.impl.LineImpl <em>Line</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.LineImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getLine()
	 * @generated
	 */
	int LINE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__NAME = POWER_DELIVERY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__ENABLED = POWER_DELIVERY_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__BASE_FREQ = POWER_DELIVERY_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__NODE_REF = POWER_DELIVERY_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__YORDER = POWER_DELIVERY_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__YPRIM_INVALID = POWER_DELIVERY_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__LAST_TERMINAL_CHECKED = POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__CHECKED = POWER_DELIVERY_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__HAS_METER = POWER_DELIVERY_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__ISOLATED = POWER_DELIVERY_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__HAS_CONTROL = POWER_DELIVERY_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__PART_OF_FEEDER = POWER_DELIVERY_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__CONTROL_ELEMENT = POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__TERMINALS = POWER_DELIVERY_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__ACTIVE_TERMINAL = POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__NTERMS = POWER_DELIVERY_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__NCONDS = POWER_DELIVERY_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__NPHASES = POWER_DELIVERY_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__BUS_INDEX = POWER_DELIVERY_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__YPRIM_SERIES = POWER_DELIVERY_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__YPRIM_SHUNT = POWER_DELIVERY_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__YPRIM = POWER_DELIVERY_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__YPRIM_FREQ = POWER_DELIVERY_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__NORM_AMPS = POWER_DELIVERY_ELEMENT__NORM_AMPS;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__EMERG_AMPS = POWER_DELIVERY_ELEMENT__EMERG_AMPS;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__FAULT_RATE = POWER_DELIVERY_ELEMENT__FAULT_RATE;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__PCT_PERM = POWER_DELIVERY_ELEMENT__PCT_PERM;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__REPAIR = POWER_DELIVERY_ELEMENT__REPAIR;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__BUS1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__BUS2 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__LINE_CODE = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__LENGTH = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>R1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__R1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>X1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__X1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>R0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__R0 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>X0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__X0 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>C1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__C1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>C0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__C0 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>RMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__RMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>XMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__XMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>CMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__CMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Switch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__SWITCH = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Rg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__RG = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Xg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__XG = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Rho</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__RHO = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__GEOMETRY = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE__UNITS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The number of structural features of the '<em>Line</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_FEATURE_COUNT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The meta object id for the '{@link dss.delivery.impl.ReactorImpl <em>Reactor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.ReactorImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getReactor()
	 * @generated
	 */
	int REACTOR = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__NAME = POWER_DELIVERY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__ENABLED = POWER_DELIVERY_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__BASE_FREQ = POWER_DELIVERY_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__NODE_REF = POWER_DELIVERY_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__YORDER = POWER_DELIVERY_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__YPRIM_INVALID = POWER_DELIVERY_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__LAST_TERMINAL_CHECKED = POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__CHECKED = POWER_DELIVERY_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__HAS_METER = POWER_DELIVERY_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__ISOLATED = POWER_DELIVERY_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__HAS_CONTROL = POWER_DELIVERY_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__PART_OF_FEEDER = POWER_DELIVERY_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__CONTROL_ELEMENT = POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__TERMINALS = POWER_DELIVERY_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__ACTIVE_TERMINAL = POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__NTERMS = POWER_DELIVERY_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__NCONDS = POWER_DELIVERY_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__NPHASES = POWER_DELIVERY_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__BUS_INDEX = POWER_DELIVERY_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__YPRIM_SERIES = POWER_DELIVERY_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__YPRIM_SHUNT = POWER_DELIVERY_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__YPRIM = POWER_DELIVERY_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__YPRIM_FREQ = POWER_DELIVERY_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__NORM_AMPS = POWER_DELIVERY_ELEMENT__NORM_AMPS;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__EMERG_AMPS = POWER_DELIVERY_ELEMENT__EMERG_AMPS;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__FAULT_RATE = POWER_DELIVERY_ELEMENT__FAULT_RATE;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__PCT_PERM = POWER_DELIVERY_ELEMENT__PCT_PERM;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__REPAIR = POWER_DELIVERY_ELEMENT__REPAIR;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__BUS1 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__BUS2 = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__KV_AR = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__KV = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__CONN = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>RMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__RMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>XMatrix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__XMATRIX = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__PARALLEL = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__R = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__X = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Rp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR__RP = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Reactor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REACTOR_FEATURE_COUNT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link dss.delivery.impl.TransformerImpl <em>Transformer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.delivery.impl.TransformerImpl
	 * @see dss.delivery.impl.DeliveryPackageImpl#getTransformer()
	 * @generated
	 */
	int TRANSFORMER = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NAME = POWER_DELIVERY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__ENABLED = POWER_DELIVERY_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__BASE_FREQ = POWER_DELIVERY_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NODE_REF = POWER_DELIVERY_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__YORDER = POWER_DELIVERY_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__YPRIM_INVALID = POWER_DELIVERY_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__LAST_TERMINAL_CHECKED = POWER_DELIVERY_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__CHECKED = POWER_DELIVERY_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__HAS_METER = POWER_DELIVERY_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__ISOLATED = POWER_DELIVERY_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__HAS_CONTROL = POWER_DELIVERY_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__PART_OF_FEEDER = POWER_DELIVERY_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__CONTROL_ELEMENT = POWER_DELIVERY_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__TERMINALS = POWER_DELIVERY_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__ACTIVE_TERMINAL = POWER_DELIVERY_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NTERMS = POWER_DELIVERY_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NCONDS = POWER_DELIVERY_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NPHASES = POWER_DELIVERY_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__BUS_INDEX = POWER_DELIVERY_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__YPRIM_SERIES = POWER_DELIVERY_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__YPRIM_SHUNT = POWER_DELIVERY_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__YPRIM = POWER_DELIVERY_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__YPRIM_FREQ = POWER_DELIVERY_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Norm Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NORM_AMPS = POWER_DELIVERY_ELEMENT__NORM_AMPS;

	/**
	 * The feature id for the '<em><b>Emerg Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__EMERG_AMPS = POWER_DELIVERY_ELEMENT__EMERG_AMPS;

	/**
	 * The feature id for the '<em><b>Fault Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__FAULT_RATE = POWER_DELIVERY_ELEMENT__FAULT_RATE;

	/**
	 * The feature id for the '<em><b>Pct Perm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__PCT_PERM = POWER_DELIVERY_ELEMENT__PCT_PERM;

	/**
	 * The feature id for the '<em><b>Repair</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__REPAIR = POWER_DELIVERY_ELEMENT__REPAIR;

	/**
	 * The feature id for the '<em><b>Windings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__WINDINGS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Wdg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__WDG = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__BUS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__CONN = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__KV = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__KVA = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Tap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__TAP = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>RPct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__RPCT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>RNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__RNEUT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>XNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__XNEUT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Buses</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__BUSES = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Conns</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__CONNS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>KVs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__KVS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>KV As</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__KV_AS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Taps</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__TAPS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>XHL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__XHL = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>XHT</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__XHT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>XLT</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__XLT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>XSC Array</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__XSC_ARRAY = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Thermal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__THERMAL = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>N</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__N = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>M</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__M = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>FL Rise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__FL_RISE = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>HS Rise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__HS_RISE = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Pct Load Loss</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__PCT_LOAD_LOSS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Pct No Load Loss</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__PCT_NO_LOAD_LOSS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Norm HK Va</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NORM_HK_VA = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Emerg HK Va</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__EMERG_HK_VA = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Substation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__SUBSTATION = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>Max Tap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__MAX_TAP = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Min Tap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__MIN_TAP = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 30;

	/**
	 * The feature id for the '<em><b>Num Taps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__NUM_TAPS = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 31;

	/**
	 * The feature id for the '<em><b>Sub Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__SUB_NAME = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 32;

	/**
	 * The feature id for the '<em><b>Pct Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__PCT_IMAGE = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 33;

	/**
	 * The feature id for the '<em><b>Ppm Anti Float</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER__PPM_ANTI_FLOAT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 34;

	/**
	 * The number of structural features of the '<em>Transformer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMER_FEATURE_COUNT = POWER_DELIVERY_ELEMENT_FEATURE_COUNT + 35;


	/**
	 * Returns the meta object for class '{@link dss.delivery.PowerDeliveryElement <em>Power Delivery Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Power Delivery Element</em>'.
	 * @see dss.delivery.PowerDeliveryElement
	 * @generated
	 */
	EClass getPowerDeliveryElement();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.PowerDeliveryElement#getNormAmps <em>Norm Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm Amps</em>'.
	 * @see dss.delivery.PowerDeliveryElement#getNormAmps()
	 * @see #getPowerDeliveryElement()
	 * @generated
	 */
	EAttribute getPowerDeliveryElement_NormAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.PowerDeliveryElement#getEmergAmps <em>Emerg Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg Amps</em>'.
	 * @see dss.delivery.PowerDeliveryElement#getEmergAmps()
	 * @see #getPowerDeliveryElement()
	 * @generated
	 */
	EAttribute getPowerDeliveryElement_EmergAmps();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.PowerDeliveryElement#getFaultRate <em>Fault Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Rate</em>'.
	 * @see dss.delivery.PowerDeliveryElement#getFaultRate()
	 * @see #getPowerDeliveryElement()
	 * @generated
	 */
	EAttribute getPowerDeliveryElement_FaultRate();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.PowerDeliveryElement#getPctPerm <em>Pct Perm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Perm</em>'.
	 * @see dss.delivery.PowerDeliveryElement#getPctPerm()
	 * @see #getPowerDeliveryElement()
	 * @generated
	 */
	EAttribute getPowerDeliveryElement_PctPerm();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.PowerDeliveryElement#getRepair <em>Repair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repair</em>'.
	 * @see dss.delivery.PowerDeliveryElement#getRepair()
	 * @see #getPowerDeliveryElement()
	 * @generated
	 */
	EAttribute getPowerDeliveryElement_Repair();

	/**
	 * Returns the meta object for class '{@link dss.delivery.Capacitor <em>Capacitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Capacitor</em>'.
	 * @see dss.delivery.Capacitor
	 * @generated
	 */
	EClass getCapacitor();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.delivery.Capacitor#getBus1()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getBus2 <em>Bus2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus2</em>'.
	 * @see dss.delivery.Capacitor#getBus2()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_Bus2();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getKVAr <em>KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Ar</em>'.
	 * @see dss.delivery.Capacitor#getKVAr()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_KVAr();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getKV <em>KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV</em>'.
	 * @see dss.delivery.Capacitor#getKV()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_KV();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getConn <em>Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conn</em>'.
	 * @see dss.delivery.Capacitor#getConn()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_Conn();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getCMatrix <em>CMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CMatrix</em>'.
	 * @see dss.delivery.Capacitor#getCMatrix()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_CMatrix();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Capacitor#getCuf <em>Cuf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cuf</em>'.
	 * @see dss.delivery.Capacitor#getCuf()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_Cuf();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Capacitor#getR <em>R</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>R</em>'.
	 * @see dss.delivery.Capacitor#getR()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_R();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Capacitor#getXl <em>Xl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Xl</em>'.
	 * @see dss.delivery.Capacitor#getXl()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_Xl();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Capacitor#getHarm <em>Harm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Harm</em>'.
	 * @see dss.delivery.Capacitor#getHarm()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_Harm();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Capacitor#getNSteps <em>NSteps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NSteps</em>'.
	 * @see dss.delivery.Capacitor#getNSteps()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_NSteps();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Capacitor#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>States</em>'.
	 * @see dss.delivery.Capacitor#getStates()
	 * @see #getCapacitor()
	 * @generated
	 */
	EAttribute getCapacitor_States();

	/**
	 * Returns the meta object for class '{@link dss.delivery.Fault <em>Fault</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fault</em>'.
	 * @see dss.delivery.Fault
	 * @generated
	 */
	EClass getFault();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.delivery.Fault#getBus1()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getBus2 <em>Bus2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus2</em>'.
	 * @see dss.delivery.Fault#getBus2()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_Bus2();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getR <em>R</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R</em>'.
	 * @see dss.delivery.Fault#getR()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_R();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getPctStdDev <em>Pct Std Dev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Std Dev</em>'.
	 * @see dss.delivery.Fault#getPctStdDev()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_PctStdDev();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getGMatrix <em>GMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>GMatrix</em>'.
	 * @see dss.delivery.Fault#getGMatrix()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_GMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getOnTime <em>On Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Time</em>'.
	 * @see dss.delivery.Fault#getOnTime()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_OnTime();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#isTemporary <em>Temporary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temporary</em>'.
	 * @see dss.delivery.Fault#isTemporary()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_Temporary();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fault#getMinAmps <em>Min Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Amps</em>'.
	 * @see dss.delivery.Fault#getMinAmps()
	 * @see #getFault()
	 * @generated
	 */
	EAttribute getFault_MinAmps();

	/**
	 * Returns the meta object for class '{@link dss.delivery.Fuse <em>Fuse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fuse</em>'.
	 * @see dss.delivery.Fuse
	 * @generated
	 */
	EClass getFuse();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getMonitoredObj <em>Monitored Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitored Obj</em>'.
	 * @see dss.delivery.Fuse#getMonitoredObj()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_MonitoredObj();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getMonitorTerm <em>Monitor Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Monitor Term</em>'.
	 * @see dss.delivery.Fuse#getMonitorTerm()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_MonitorTerm();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getSwitchedObj <em>Switched Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switched Obj</em>'.
	 * @see dss.delivery.Fuse#getSwitchedObj()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_SwitchedObj();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getSwitchedTerm <em>Switched Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switched Term</em>'.
	 * @see dss.delivery.Fuse#getSwitchedTerm()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_SwitchedTerm();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getFuseCurve <em>Fuse Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fuse Curve</em>'.
	 * @see dss.delivery.Fuse#getFuseCurve()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_FuseCurve();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getRatedCurrent <em>Rated Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rated Current</em>'.
	 * @see dss.delivery.Fuse#getRatedCurrent()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_RatedCurrent();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see dss.delivery.Fuse#getDelay()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_Delay();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Fuse#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see dss.delivery.Fuse#getAction()
	 * @see #getFuse()
	 * @generated
	 */
	EAttribute getFuse_Action();

	/**
	 * Returns the meta object for class '{@link dss.delivery.Line <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line</em>'.
	 * @see dss.delivery.Line
	 * @generated
	 */
	EClass getLine();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.delivery.Line#getBus1()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getBus2 <em>Bus2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus2</em>'.
	 * @see dss.delivery.Line#getBus2()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Bus2();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getLineCode <em>Line Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Code</em>'.
	 * @see dss.delivery.Line#getLineCode()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_LineCode();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see dss.delivery.Line#getLength()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Length();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getR1 <em>R1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R1</em>'.
	 * @see dss.delivery.Line#getR1()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_R1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getX1 <em>X1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X1</em>'.
	 * @see dss.delivery.Line#getX1()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_X1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getR0 <em>R0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R0</em>'.
	 * @see dss.delivery.Line#getR0()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_R0();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getX0 <em>X0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X0</em>'.
	 * @see dss.delivery.Line#getX0()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_X0();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getC1 <em>C1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>C1</em>'.
	 * @see dss.delivery.Line#getC1()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_C1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getC0 <em>C0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>C0</em>'.
	 * @see dss.delivery.Line#getC0()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_C0();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getRMatrix <em>RMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RMatrix</em>'.
	 * @see dss.delivery.Line#getRMatrix()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_RMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getXMatrix <em>XMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XMatrix</em>'.
	 * @see dss.delivery.Line#getXMatrix()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_XMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getCMatrix <em>CMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CMatrix</em>'.
	 * @see dss.delivery.Line#getCMatrix()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_CMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#isSwitch <em>Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switch</em>'.
	 * @see dss.delivery.Line#isSwitch()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Switch();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getRg <em>Rg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rg</em>'.
	 * @see dss.delivery.Line#getRg()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Rg();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getXg <em>Xg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xg</em>'.
	 * @see dss.delivery.Line#getXg()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Xg();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getRho <em>Rho</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rho</em>'.
	 * @see dss.delivery.Line#getRho()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Rho();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getGeometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Geometry</em>'.
	 * @see dss.delivery.Line#getGeometry()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Geometry();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Line#getUnits <em>Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Units</em>'.
	 * @see dss.delivery.Line#getUnits()
	 * @see #getLine()
	 * @generated
	 */
	EAttribute getLine_Units();

	/**
	 * Returns the meta object for class '{@link dss.delivery.Reactor <em>Reactor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reactor</em>'.
	 * @see dss.delivery.Reactor
	 * @generated
	 */
	EClass getReactor();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.delivery.Reactor#getBus1()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getBus2 <em>Bus2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus2</em>'.
	 * @see dss.delivery.Reactor#getBus2()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_Bus2();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getKVAr <em>KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Ar</em>'.
	 * @see dss.delivery.Reactor#getKVAr()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_KVAr();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getKV <em>KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV</em>'.
	 * @see dss.delivery.Reactor#getKV()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_KV();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getConn <em>Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conn</em>'.
	 * @see dss.delivery.Reactor#getConn()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_Conn();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getRMatrix <em>RMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RMatrix</em>'.
	 * @see dss.delivery.Reactor#getRMatrix()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_RMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getXMatrix <em>XMatrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XMatrix</em>'.
	 * @see dss.delivery.Reactor#getXMatrix()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_XMatrix();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#isParallel <em>Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parallel</em>'.
	 * @see dss.delivery.Reactor#isParallel()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_Parallel();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getR <em>R</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R</em>'.
	 * @see dss.delivery.Reactor#getR()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_R();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see dss.delivery.Reactor#getX()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_X();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Reactor#getRp <em>Rp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rp</em>'.
	 * @see dss.delivery.Reactor#getRp()
	 * @see #getReactor()
	 * @generated
	 */
	EAttribute getReactor_Rp();

	/**
	 * Returns the meta object for class '{@link dss.delivery.Transformer <em>Transformer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformer</em>'.
	 * @see dss.delivery.Transformer
	 * @generated
	 */
	EClass getTransformer();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getWindings <em>Windings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Windings</em>'.
	 * @see dss.delivery.Transformer#getWindings()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Windings();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getWdg <em>Wdg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wdg</em>'.
	 * @see dss.delivery.Transformer#getWdg()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Wdg();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getBus <em>Bus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus</em>'.
	 * @see dss.delivery.Transformer#getBus()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Bus();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getConn <em>Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conn</em>'.
	 * @see dss.delivery.Transformer#getConn()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Conn();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getKV <em>KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV</em>'.
	 * @see dss.delivery.Transformer#getKV()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_KV();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getKVA <em>KVA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KVA</em>'.
	 * @see dss.delivery.Transformer#getKVA()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_KVA();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getTap <em>Tap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tap</em>'.
	 * @see dss.delivery.Transformer#getTap()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Tap();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getRPct <em>RPct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RPct</em>'.
	 * @see dss.delivery.Transformer#getRPct()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_RPct();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getRNeut <em>RNeut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RNeut</em>'.
	 * @see dss.delivery.Transformer#getRNeut()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_RNeut();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getXNeut <em>XNeut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XNeut</em>'.
	 * @see dss.delivery.Transformer#getXNeut()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_XNeut();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Transformer#getBuses <em>Buses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Buses</em>'.
	 * @see dss.delivery.Transformer#getBuses()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Buses();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Transformer#getConns <em>Conns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Conns</em>'.
	 * @see dss.delivery.Transformer#getConns()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Conns();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Transformer#getKVs <em>KVs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>KVs</em>'.
	 * @see dss.delivery.Transformer#getKVs()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_KVs();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Transformer#getKVAs <em>KV As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>KV As</em>'.
	 * @see dss.delivery.Transformer#getKVAs()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_KVAs();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Transformer#getTaps <em>Taps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Taps</em>'.
	 * @see dss.delivery.Transformer#getTaps()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Taps();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getXHL <em>XHL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XHL</em>'.
	 * @see dss.delivery.Transformer#getXHL()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_XHL();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getXHT <em>XHT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XHT</em>'.
	 * @see dss.delivery.Transformer#getXHT()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_XHT();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getXLT <em>XLT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XLT</em>'.
	 * @see dss.delivery.Transformer#getXLT()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_XLT();

	/**
	 * Returns the meta object for the attribute list '{@link dss.delivery.Transformer#getXSCArray <em>XSC Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>XSC Array</em>'.
	 * @see dss.delivery.Transformer#getXSCArray()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_XSCArray();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getThermal <em>Thermal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Thermal</em>'.
	 * @see dss.delivery.Transformer#getThermal()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Thermal();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getN <em>N</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>N</em>'.
	 * @see dss.delivery.Transformer#getN()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_N();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getM <em>M</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>M</em>'.
	 * @see dss.delivery.Transformer#getM()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_M();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getFLRise <em>FL Rise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>FL Rise</em>'.
	 * @see dss.delivery.Transformer#getFLRise()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_FLRise();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getHSRise <em>HS Rise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>HS Rise</em>'.
	 * @see dss.delivery.Transformer#getHSRise()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_HSRise();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getPctLoadLoss <em>Pct Load Loss</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Load Loss</em>'.
	 * @see dss.delivery.Transformer#getPctLoadLoss()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_PctLoadLoss();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getPctNoLoadLoss <em>Pct No Load Loss</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct No Load Loss</em>'.
	 * @see dss.delivery.Transformer#getPctNoLoadLoss()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_PctNoLoadLoss();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getNormHKVa <em>Norm HK Va</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Norm HK Va</em>'.
	 * @see dss.delivery.Transformer#getNormHKVa()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_NormHKVa();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getEmergHKVa <em>Emerg HK Va</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Emerg HK Va</em>'.
	 * @see dss.delivery.Transformer#getEmergHKVa()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_EmergHKVa();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#isSubstation <em>Substation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Substation</em>'.
	 * @see dss.delivery.Transformer#isSubstation()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_Substation();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getMaxTap <em>Max Tap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Tap</em>'.
	 * @see dss.delivery.Transformer#getMaxTap()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_MaxTap();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getMinTap <em>Min Tap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Tap</em>'.
	 * @see dss.delivery.Transformer#getMinTap()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_MinTap();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getNumTaps <em>Num Taps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Taps</em>'.
	 * @see dss.delivery.Transformer#getNumTaps()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_NumTaps();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getSubName <em>Sub Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Name</em>'.
	 * @see dss.delivery.Transformer#getSubName()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_SubName();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getPctImage <em>Pct Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Image</em>'.
	 * @see dss.delivery.Transformer#getPctImage()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_PctImage();

	/**
	 * Returns the meta object for the attribute '{@link dss.delivery.Transformer#getPpmAntiFloat <em>Ppm Anti Float</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ppm Anti Float</em>'.
	 * @see dss.delivery.Transformer#getPpmAntiFloat()
	 * @see #getTransformer()
	 * @generated
	 */
	EAttribute getTransformer_PpmAntiFloat();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DeliveryFactory getDeliveryFactory();

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
		 * The meta object literal for the '{@link dss.delivery.impl.PowerDeliveryElementImpl <em>Power Delivery Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.PowerDeliveryElementImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getPowerDeliveryElement()
		 * @generated
		 */
		EClass POWER_DELIVERY_ELEMENT = eINSTANCE.getPowerDeliveryElement();

		/**
		 * The meta object literal for the '<em><b>Norm Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_DELIVERY_ELEMENT__NORM_AMPS = eINSTANCE.getPowerDeliveryElement_NormAmps();

		/**
		 * The meta object literal for the '<em><b>Emerg Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_DELIVERY_ELEMENT__EMERG_AMPS = eINSTANCE.getPowerDeliveryElement_EmergAmps();

		/**
		 * The meta object literal for the '<em><b>Fault Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_DELIVERY_ELEMENT__FAULT_RATE = eINSTANCE.getPowerDeliveryElement_FaultRate();

		/**
		 * The meta object literal for the '<em><b>Pct Perm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_DELIVERY_ELEMENT__PCT_PERM = eINSTANCE.getPowerDeliveryElement_PctPerm();

		/**
		 * The meta object literal for the '<em><b>Repair</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_DELIVERY_ELEMENT__REPAIR = eINSTANCE.getPowerDeliveryElement_Repair();

		/**
		 * The meta object literal for the '{@link dss.delivery.impl.CapacitorImpl <em>Capacitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.CapacitorImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getCapacitor()
		 * @generated
		 */
		EClass CAPACITOR = eINSTANCE.getCapacitor();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__BUS1 = eINSTANCE.getCapacitor_Bus1();

		/**
		 * The meta object literal for the '<em><b>Bus2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__BUS2 = eINSTANCE.getCapacitor_Bus2();

		/**
		 * The meta object literal for the '<em><b>KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__KV_AR = eINSTANCE.getCapacitor_KVAr();

		/**
		 * The meta object literal for the '<em><b>KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__KV = eINSTANCE.getCapacitor_KV();

		/**
		 * The meta object literal for the '<em><b>Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__CONN = eINSTANCE.getCapacitor_Conn();

		/**
		 * The meta object literal for the '<em><b>CMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__CMATRIX = eINSTANCE.getCapacitor_CMatrix();

		/**
		 * The meta object literal for the '<em><b>Cuf</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__CUF = eINSTANCE.getCapacitor_Cuf();

		/**
		 * The meta object literal for the '<em><b>R</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__R = eINSTANCE.getCapacitor_R();

		/**
		 * The meta object literal for the '<em><b>Xl</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__XL = eINSTANCE.getCapacitor_Xl();

		/**
		 * The meta object literal for the '<em><b>Harm</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__HARM = eINSTANCE.getCapacitor_Harm();

		/**
		 * The meta object literal for the '<em><b>NSteps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__NSTEPS = eINSTANCE.getCapacitor_NSteps();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CAPACITOR__STATES = eINSTANCE.getCapacitor_States();

		/**
		 * The meta object literal for the '{@link dss.delivery.impl.FaultImpl <em>Fault</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.FaultImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getFault()
		 * @generated
		 */
		EClass FAULT = eINSTANCE.getFault();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__BUS1 = eINSTANCE.getFault_Bus1();

		/**
		 * The meta object literal for the '<em><b>Bus2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__BUS2 = eINSTANCE.getFault_Bus2();

		/**
		 * The meta object literal for the '<em><b>R</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__R = eINSTANCE.getFault_R();

		/**
		 * The meta object literal for the '<em><b>Pct Std Dev</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__PCT_STD_DEV = eINSTANCE.getFault_PctStdDev();

		/**
		 * The meta object literal for the '<em><b>GMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__GMATRIX = eINSTANCE.getFault_GMatrix();

		/**
		 * The meta object literal for the '<em><b>On Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__ON_TIME = eINSTANCE.getFault_OnTime();

		/**
		 * The meta object literal for the '<em><b>Temporary</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__TEMPORARY = eINSTANCE.getFault_Temporary();

		/**
		 * The meta object literal for the '<em><b>Min Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT__MIN_AMPS = eINSTANCE.getFault_MinAmps();

		/**
		 * The meta object literal for the '{@link dss.delivery.impl.FuseImpl <em>Fuse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.FuseImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getFuse()
		 * @generated
		 */
		EClass FUSE = eINSTANCE.getFuse();

		/**
		 * The meta object literal for the '<em><b>Monitored Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__MONITORED_OBJ = eINSTANCE.getFuse_MonitoredObj();

		/**
		 * The meta object literal for the '<em><b>Monitor Term</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__MONITOR_TERM = eINSTANCE.getFuse_MonitorTerm();

		/**
		 * The meta object literal for the '<em><b>Switched Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__SWITCHED_OBJ = eINSTANCE.getFuse_SwitchedObj();

		/**
		 * The meta object literal for the '<em><b>Switched Term</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__SWITCHED_TERM = eINSTANCE.getFuse_SwitchedTerm();

		/**
		 * The meta object literal for the '<em><b>Fuse Curve</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__FUSE_CURVE = eINSTANCE.getFuse_FuseCurve();

		/**
		 * The meta object literal for the '<em><b>Rated Current</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__RATED_CURRENT = eINSTANCE.getFuse_RatedCurrent();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__DELAY = eINSTANCE.getFuse_Delay();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUSE__ACTION = eINSTANCE.getFuse_Action();

		/**
		 * The meta object literal for the '{@link dss.delivery.impl.LineImpl <em>Line</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.LineImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getLine()
		 * @generated
		 */
		EClass LINE = eINSTANCE.getLine();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__BUS1 = eINSTANCE.getLine_Bus1();

		/**
		 * The meta object literal for the '<em><b>Bus2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__BUS2 = eINSTANCE.getLine_Bus2();

		/**
		 * The meta object literal for the '<em><b>Line Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__LINE_CODE = eINSTANCE.getLine_LineCode();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__LENGTH = eINSTANCE.getLine_Length();

		/**
		 * The meta object literal for the '<em><b>R1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__R1 = eINSTANCE.getLine_R1();

		/**
		 * The meta object literal for the '<em><b>X1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__X1 = eINSTANCE.getLine_X1();

		/**
		 * The meta object literal for the '<em><b>R0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__R0 = eINSTANCE.getLine_R0();

		/**
		 * The meta object literal for the '<em><b>X0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__X0 = eINSTANCE.getLine_X0();

		/**
		 * The meta object literal for the '<em><b>C1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__C1 = eINSTANCE.getLine_C1();

		/**
		 * The meta object literal for the '<em><b>C0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__C0 = eINSTANCE.getLine_C0();

		/**
		 * The meta object literal for the '<em><b>RMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__RMATRIX = eINSTANCE.getLine_RMatrix();

		/**
		 * The meta object literal for the '<em><b>XMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__XMATRIX = eINSTANCE.getLine_XMatrix();

		/**
		 * The meta object literal for the '<em><b>CMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__CMATRIX = eINSTANCE.getLine_CMatrix();

		/**
		 * The meta object literal for the '<em><b>Switch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__SWITCH = eINSTANCE.getLine_Switch();

		/**
		 * The meta object literal for the '<em><b>Rg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__RG = eINSTANCE.getLine_Rg();

		/**
		 * The meta object literal for the '<em><b>Xg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__XG = eINSTANCE.getLine_Xg();

		/**
		 * The meta object literal for the '<em><b>Rho</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__RHO = eINSTANCE.getLine_Rho();

		/**
		 * The meta object literal for the '<em><b>Geometry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__GEOMETRY = eINSTANCE.getLine_Geometry();

		/**
		 * The meta object literal for the '<em><b>Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINE__UNITS = eINSTANCE.getLine_Units();

		/**
		 * The meta object literal for the '{@link dss.delivery.impl.ReactorImpl <em>Reactor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.ReactorImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getReactor()
		 * @generated
		 */
		EClass REACTOR = eINSTANCE.getReactor();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__BUS1 = eINSTANCE.getReactor_Bus1();

		/**
		 * The meta object literal for the '<em><b>Bus2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__BUS2 = eINSTANCE.getReactor_Bus2();

		/**
		 * The meta object literal for the '<em><b>KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__KV_AR = eINSTANCE.getReactor_KVAr();

		/**
		 * The meta object literal for the '<em><b>KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__KV = eINSTANCE.getReactor_KV();

		/**
		 * The meta object literal for the '<em><b>Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__CONN = eINSTANCE.getReactor_Conn();

		/**
		 * The meta object literal for the '<em><b>RMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__RMATRIX = eINSTANCE.getReactor_RMatrix();

		/**
		 * The meta object literal for the '<em><b>XMatrix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__XMATRIX = eINSTANCE.getReactor_XMatrix();

		/**
		 * The meta object literal for the '<em><b>Parallel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__PARALLEL = eINSTANCE.getReactor_Parallel();

		/**
		 * The meta object literal for the '<em><b>R</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__R = eINSTANCE.getReactor_R();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__X = eINSTANCE.getReactor_X();

		/**
		 * The meta object literal for the '<em><b>Rp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REACTOR__RP = eINSTANCE.getReactor_Rp();

		/**
		 * The meta object literal for the '{@link dss.delivery.impl.TransformerImpl <em>Transformer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.delivery.impl.TransformerImpl
		 * @see dss.delivery.impl.DeliveryPackageImpl#getTransformer()
		 * @generated
		 */
		EClass TRANSFORMER = eINSTANCE.getTransformer();

		/**
		 * The meta object literal for the '<em><b>Windings</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__WINDINGS = eINSTANCE.getTransformer_Windings();

		/**
		 * The meta object literal for the '<em><b>Wdg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__WDG = eINSTANCE.getTransformer_Wdg();

		/**
		 * The meta object literal for the '<em><b>Bus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__BUS = eINSTANCE.getTransformer_Bus();

		/**
		 * The meta object literal for the '<em><b>Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__CONN = eINSTANCE.getTransformer_Conn();

		/**
		 * The meta object literal for the '<em><b>KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__KV = eINSTANCE.getTransformer_KV();

		/**
		 * The meta object literal for the '<em><b>KVA</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__KVA = eINSTANCE.getTransformer_KVA();

		/**
		 * The meta object literal for the '<em><b>Tap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__TAP = eINSTANCE.getTransformer_Tap();

		/**
		 * The meta object literal for the '<em><b>RPct</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__RPCT = eINSTANCE.getTransformer_RPct();

		/**
		 * The meta object literal for the '<em><b>RNeut</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__RNEUT = eINSTANCE.getTransformer_RNeut();

		/**
		 * The meta object literal for the '<em><b>XNeut</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__XNEUT = eINSTANCE.getTransformer_XNeut();

		/**
		 * The meta object literal for the '<em><b>Buses</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__BUSES = eINSTANCE.getTransformer_Buses();

		/**
		 * The meta object literal for the '<em><b>Conns</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__CONNS = eINSTANCE.getTransformer_Conns();

		/**
		 * The meta object literal for the '<em><b>KVs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__KVS = eINSTANCE.getTransformer_KVs();

		/**
		 * The meta object literal for the '<em><b>KV As</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__KV_AS = eINSTANCE.getTransformer_KVAs();

		/**
		 * The meta object literal for the '<em><b>Taps</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__TAPS = eINSTANCE.getTransformer_Taps();

		/**
		 * The meta object literal for the '<em><b>XHL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__XHL = eINSTANCE.getTransformer_XHL();

		/**
		 * The meta object literal for the '<em><b>XHT</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__XHT = eINSTANCE.getTransformer_XHT();

		/**
		 * The meta object literal for the '<em><b>XLT</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__XLT = eINSTANCE.getTransformer_XLT();

		/**
		 * The meta object literal for the '<em><b>XSC Array</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__XSC_ARRAY = eINSTANCE.getTransformer_XSCArray();

		/**
		 * The meta object literal for the '<em><b>Thermal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__THERMAL = eINSTANCE.getTransformer_Thermal();

		/**
		 * The meta object literal for the '<em><b>N</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__N = eINSTANCE.getTransformer_N();

		/**
		 * The meta object literal for the '<em><b>M</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__M = eINSTANCE.getTransformer_M();

		/**
		 * The meta object literal for the '<em><b>FL Rise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__FL_RISE = eINSTANCE.getTransformer_FLRise();

		/**
		 * The meta object literal for the '<em><b>HS Rise</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__HS_RISE = eINSTANCE.getTransformer_HSRise();

		/**
		 * The meta object literal for the '<em><b>Pct Load Loss</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__PCT_LOAD_LOSS = eINSTANCE.getTransformer_PctLoadLoss();

		/**
		 * The meta object literal for the '<em><b>Pct No Load Loss</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__PCT_NO_LOAD_LOSS = eINSTANCE.getTransformer_PctNoLoadLoss();

		/**
		 * The meta object literal for the '<em><b>Norm HK Va</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__NORM_HK_VA = eINSTANCE.getTransformer_NormHKVa();

		/**
		 * The meta object literal for the '<em><b>Emerg HK Va</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__EMERG_HK_VA = eINSTANCE.getTransformer_EmergHKVa();

		/**
		 * The meta object literal for the '<em><b>Substation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__SUBSTATION = eINSTANCE.getTransformer_Substation();

		/**
		 * The meta object literal for the '<em><b>Max Tap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__MAX_TAP = eINSTANCE.getTransformer_MaxTap();

		/**
		 * The meta object literal for the '<em><b>Min Tap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__MIN_TAP = eINSTANCE.getTransformer_MinTap();

		/**
		 * The meta object literal for the '<em><b>Num Taps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__NUM_TAPS = eINSTANCE.getTransformer_NumTaps();

		/**
		 * The meta object literal for the '<em><b>Sub Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__SUB_NAME = eINSTANCE.getTransformer_SubName();

		/**
		 * The meta object literal for the '<em><b>Pct Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__PCT_IMAGE = eINSTANCE.getTransformer_PctImage();

		/**
		 * The meta object literal for the '<em><b>Ppm Anti Float</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMER__PPM_ANTI_FLOAT = eINSTANCE.getTransformer_PpmAntiFloat();

	}

} //DeliveryPackage
