/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter;

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
 * @see electrickery.meter.MeterFactory
 * @model kind="package"
 * @generated
 */
public interface MeterPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "meter";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.electrickery.com/meter";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "meter";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MeterPackage eINSTANCE = electrickery.meter.impl.MeterPackageImpl.init();

	/**
	 * The meta object id for the '{@link electrickery.meter.impl.MeterElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.impl.MeterElementImpl
	 * @see electrickery.meter.impl.MeterPackageImpl#getMeterElement()
	 * @generated
	 */
	int METER_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__NAME = CommonPackage.CIRCUIT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__ENABLED = CommonPackage.CIRCUIT_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__BASE_FREQ = CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__NODE_REF = CommonPackage.CIRCUIT_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__YORDER = CommonPackage.CIRCUIT_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__YPRIM_INVALID = CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__LAST_TERMINAL_CHECKED = CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__CHECKED = CommonPackage.CIRCUIT_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__HAS_METER = CommonPackage.CIRCUIT_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__ISOLATED = CommonPackage.CIRCUIT_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__HAS_CONTROL = CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__PART_OF_FEEDER = CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__CONTROL_ELEMENT = CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__TERMINALS = CommonPackage.CIRCUIT_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__ACTIVE_TERMINAL = CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__NTERMS = CommonPackage.CIRCUIT_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__NCONDS = CommonPackage.CIRCUIT_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__NPHASES = CommonPackage.CIRCUIT_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__BUS_INDEX = CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__YPRIM_SERIES = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__YPRIM_SHUNT = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__YPRIM = CommonPackage.CIRCUIT_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__YPRIM_FREQ = CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__LIKE = CommonPackage.CIRCUIT_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__ELEMENT_NAME = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Metered Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__METERED_ELEMENT = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Metered Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT__METERED_TERMINAL = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METER_ELEMENT_FEATURE_COUNT = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link electrickery.meter.impl.EnergyMeterImpl <em>Energy Meter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.impl.EnergyMeterImpl
	 * @see electrickery.meter.impl.MeterPackageImpl#getEnergyMeter()
	 * @generated
	 */
	int ENERGY_METER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__NAME = METER_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ENABLED = METER_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__BASE_FREQ = METER_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__NODE_REF = METER_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__YORDER = METER_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__YPRIM_INVALID = METER_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__LAST_TERMINAL_CHECKED = METER_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__CHECKED = METER_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__HAS_METER = METER_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ISOLATED = METER_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__HAS_CONTROL = METER_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__PART_OF_FEEDER = METER_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__CONTROL_ELEMENT = METER_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__TERMINALS = METER_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ACTIVE_TERMINAL = METER_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__NTERMS = METER_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__NCONDS = METER_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__NPHASES = METER_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__BUS_INDEX = METER_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__YPRIM_SERIES = METER_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__YPRIM_SHUNT = METER_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__YPRIM = METER_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__YPRIM_FREQ = METER_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__LIKE = METER_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ELEMENT_NAME = METER_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Metered Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__METERED_ELEMENT = METER_ELEMENT__METERED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Metered Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__METERED_TERMINAL = METER_ELEMENT__METERED_TERMINAL;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ELEMENT = METER_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__TERMINAL = METER_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ACTION = METER_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Option</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__OPTION = METER_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>KVA Norm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__KVA_NORM = METER_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>KVA Emerg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__KVA_EMERG = METER_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Peak Current</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__PEAK_CURRENT = METER_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Zone List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__ZONE_LIST = METER_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Local Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__LOCAL_ONLY = METER_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Mask</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__MASK = METER_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__LOSSES = METER_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Line Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__LINE_LOSSES = METER_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Xfmr Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__XFMR_LOSSES = METER_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Seq Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__SEQ_LOSSES = METER_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Three Phase Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__THREE_PHASE_LOSSES = METER_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>VBase Losses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__VBASE_LOSSES = METER_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Overload Report</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER__OVERLOAD_REPORT = METER_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The number of structural features of the '<em>Energy Meter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENERGY_METER_FEATURE_COUNT = METER_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The meta object id for the '{@link electrickery.meter.impl.MonitorImpl <em>Monitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.impl.MonitorImpl
	 * @see electrickery.meter.impl.MeterPackageImpl#getMonitor()
	 * @generated
	 */
	int MONITOR = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__NAME = METER_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__ENABLED = METER_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__BASE_FREQ = METER_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__NODE_REF = METER_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__YORDER = METER_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__YPRIM_INVALID = METER_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__LAST_TERMINAL_CHECKED = METER_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__CHECKED = METER_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__HAS_METER = METER_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__ISOLATED = METER_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__HAS_CONTROL = METER_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__PART_OF_FEEDER = METER_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__CONTROL_ELEMENT = METER_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__TERMINALS = METER_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__ACTIVE_TERMINAL = METER_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__NTERMS = METER_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__NCONDS = METER_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__NPHASES = METER_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__BUS_INDEX = METER_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__YPRIM_SERIES = METER_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__YPRIM_SHUNT = METER_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__YPRIM = METER_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__YPRIM_FREQ = METER_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__LIKE = METER_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__ELEMENT_NAME = METER_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Metered Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__METERED_ELEMENT = METER_ELEMENT__METERED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Metered Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__METERED_TERMINAL = METER_ELEMENT__METERED_TERMINAL;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__ELEMENT = METER_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__TERMINAL = METER_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__MODE = METER_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__ACTION = METER_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Residual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__RESIDUAL = METER_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>VI Polar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__VI_POLAR = METER_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>PPolar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR__PPOLAR = METER_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR_FEATURE_COUNT = METER_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link electrickery.meter.impl.SensorImpl <em>Sensor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.impl.SensorImpl
	 * @see electrickery.meter.impl.MeterPackageImpl#getSensor()
	 * @generated
	 */
	int SENSOR = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__NAME = METER_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ENABLED = METER_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__BASE_FREQ = METER_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__NODE_REF = METER_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__YORDER = METER_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__YPRIM_INVALID = METER_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__LAST_TERMINAL_CHECKED = METER_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__CHECKED = METER_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__HAS_METER = METER_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ISOLATED = METER_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__HAS_CONTROL = METER_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__PART_OF_FEEDER = METER_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__CONTROL_ELEMENT = METER_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__TERMINALS = METER_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ACTIVE_TERMINAL = METER_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__NTERMS = METER_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__NCONDS = METER_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__NPHASES = METER_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__BUS_INDEX = METER_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__YPRIM_SERIES = METER_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__YPRIM_SHUNT = METER_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__YPRIM = METER_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__YPRIM_FREQ = METER_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__LIKE = METER_ELEMENT__LIKE;

	/**
	 * The feature id for the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ELEMENT_NAME = METER_ELEMENT__ELEMENT_NAME;

	/**
	 * The feature id for the '<em><b>Metered Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__METERED_ELEMENT = METER_ELEMENT__METERED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Metered Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__METERED_TERMINAL = METER_ELEMENT__METERED_TERMINAL;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ELEMENT = METER_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__TERMINAL = METER_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Modes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__MODES = METER_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>V</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__V = METER_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>I</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__I = METER_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>P</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__P = METER_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Q</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__Q = METER_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Phases</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__PHASES = METER_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__CONN = METER_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Pct Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__PCT_ERROR = METER_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ACTION = METER_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_FEATURE_COUNT = METER_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link electrickery.meter.meterAction <em>meter Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.meterAction
	 * @see electrickery.meter.impl.MeterPackageImpl#getmeterAction()
	 * @generated
	 */
	int METER_ACTION = 4;

	/**
	 * The meta object id for the '{@link electrickery.meter.monitorValues <em>monitor Values</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.monitorValues
	 * @see electrickery.meter.impl.MeterPackageImpl#getmonitorValues()
	 * @generated
	 */
	int MONITOR_VALUES = 5;

	/**
	 * The meta object id for the '{@link electrickery.meter.monitorAction <em>monitor Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.monitorAction
	 * @see electrickery.meter.impl.MeterPackageImpl#getmonitorAction()
	 * @generated
	 */
	int MONITOR_ACTION = 6;

	/**
	 * The meta object id for the '{@link electrickery.meter.sensorMode <em>sensor Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.sensorMode
	 * @see electrickery.meter.impl.MeterPackageImpl#getsensorMode()
	 * @generated
	 */
	int SENSOR_MODE = 7;

	/**
	 * The meta object id for the '{@link electrickery.meter.sensorAction <em>sensor Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.meter.sensorAction
	 * @see electrickery.meter.impl.MeterPackageImpl#getsensorAction()
	 * @generated
	 */
	int SENSOR_ACTION = 8;


	/**
	 * Returns the meta object for class '{@link electrickery.meter.MeterElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see electrickery.meter.MeterElement
	 * @generated
	 */
	EClass getMeterElement();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.MeterElement#getElementName <em>Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Name</em>'.
	 * @see electrickery.meter.MeterElement#getElementName()
	 * @see #getMeterElement()
	 * @generated
	 */
	EAttribute getMeterElement_ElementName();

	/**
	 * Returns the meta object for the reference '{@link electrickery.meter.MeterElement#getMeteredElement <em>Metered Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Metered Element</em>'.
	 * @see electrickery.meter.MeterElement#getMeteredElement()
	 * @see #getMeterElement()
	 * @generated
	 */
	EReference getMeterElement_MeteredElement();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.MeterElement#getMeteredTerminal <em>Metered Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metered Terminal</em>'.
	 * @see electrickery.meter.MeterElement#getMeteredTerminal()
	 * @see #getMeterElement()
	 * @generated
	 */
	EAttribute getMeterElement_MeteredTerminal();

	/**
	 * Returns the meta object for class '{@link electrickery.meter.EnergyMeter <em>Energy Meter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Energy Meter</em>'.
	 * @see electrickery.meter.EnergyMeter
	 * @generated
	 */
	EClass getEnergyMeter();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see electrickery.meter.EnergyMeter#getElement()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_Element();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminal</em>'.
	 * @see electrickery.meter.EnergyMeter#getTerminal()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_Terminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see electrickery.meter.EnergyMeter#getAction()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_Action();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.EnergyMeter#getOption <em>Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Option</em>'.
	 * @see electrickery.meter.EnergyMeter#getOption()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_Option();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#getKVANorm <em>KVA Norm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KVA Norm</em>'.
	 * @see electrickery.meter.EnergyMeter#getKVANorm()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_KVANorm();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#getKVAEmerg <em>KVA Emerg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KVA Emerg</em>'.
	 * @see electrickery.meter.EnergyMeter#getKVAEmerg()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_KVAEmerg();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.EnergyMeter#getPeakCurrent <em>Peak Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Peak Current</em>'.
	 * @see electrickery.meter.EnergyMeter#getPeakCurrent()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_PeakCurrent();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.EnergyMeter#getZoneList <em>Zone List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Zone List</em>'.
	 * @see electrickery.meter.EnergyMeter#getZoneList()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_ZoneList();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isLocalOnly <em>Local Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Only</em>'.
	 * @see electrickery.meter.EnergyMeter#isLocalOnly()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_LocalOnly();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.EnergyMeter#getMask <em>Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mask</em>'.
	 * @see electrickery.meter.EnergyMeter#getMask()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_Mask();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isLosses <em>Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Losses</em>'.
	 * @see electrickery.meter.EnergyMeter#isLosses()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_Losses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isLineLosses <em>Line Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Losses</em>'.
	 * @see electrickery.meter.EnergyMeter#isLineLosses()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_LineLosses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isXfmrLosses <em>Xfmr Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xfmr Losses</em>'.
	 * @see electrickery.meter.EnergyMeter#isXfmrLosses()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_XfmrLosses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isSeqLosses <em>Seq Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seq Losses</em>'.
	 * @see electrickery.meter.EnergyMeter#isSeqLosses()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_SeqLosses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isThreePhaseLosses <em>Three Phase Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Three Phase Losses</em>'.
	 * @see electrickery.meter.EnergyMeter#isThreePhaseLosses()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_ThreePhaseLosses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isVBaseLosses <em>VBase Losses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VBase Losses</em>'.
	 * @see electrickery.meter.EnergyMeter#isVBaseLosses()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_VBaseLosses();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.EnergyMeter#isOverloadReport <em>Overload Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overload Report</em>'.
	 * @see electrickery.meter.EnergyMeter#isOverloadReport()
	 * @see #getEnergyMeter()
	 * @generated
	 */
	EAttribute getEnergyMeter_OverloadReport();

	/**
	 * Returns the meta object for class '{@link electrickery.meter.Monitor <em>Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Monitor</em>'.
	 * @see electrickery.meter.Monitor
	 * @generated
	 */
	EClass getMonitor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see electrickery.meter.Monitor#getElement()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_Element();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminal</em>'.
	 * @see electrickery.meter.Monitor#getTerminal()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_Terminal();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see electrickery.meter.Monitor#getMode()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_Mode();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see electrickery.meter.Monitor#getAction()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_Action();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#isResidual <em>Residual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Residual</em>'.
	 * @see electrickery.meter.Monitor#isResidual()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_Residual();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#isVIPolar <em>VI Polar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VI Polar</em>'.
	 * @see electrickery.meter.Monitor#isVIPolar()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_VIPolar();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Monitor#isPPolar <em>PPolar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PPolar</em>'.
	 * @see electrickery.meter.Monitor#isPPolar()
	 * @see #getMonitor()
	 * @generated
	 */
	EAttribute getMonitor_PPolar();

	/**
	 * Returns the meta object for class '{@link electrickery.meter.Sensor <em>Sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sensor</em>'.
	 * @see electrickery.meter.Sensor
	 * @generated
	 */
	EClass getSensor();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Sensor#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see electrickery.meter.Sensor#getElement()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Element();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Sensor#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminal</em>'.
	 * @see electrickery.meter.Sensor#getTerminal()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Terminal();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.Sensor#getModes <em>Modes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Modes</em>'.
	 * @see electrickery.meter.Sensor#getModes()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Modes();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.Sensor#getV <em>V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>V</em>'.
	 * @see electrickery.meter.Sensor#getV()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_V();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.Sensor#getI <em>I</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>I</em>'.
	 * @see electrickery.meter.Sensor#getI()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_I();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.Sensor#getP <em>P</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>P</em>'.
	 * @see electrickery.meter.Sensor#getP()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_P();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.Sensor#getQ <em>Q</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Q</em>'.
	 * @see electrickery.meter.Sensor#getQ()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Q();

	/**
	 * Returns the meta object for the attribute list '{@link electrickery.meter.Sensor#getPhases <em>Phases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Phases</em>'.
	 * @see electrickery.meter.Sensor#getPhases()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Phases();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Sensor#getConn <em>Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conn</em>'.
	 * @see electrickery.meter.Sensor#getConn()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Conn();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Sensor#getPctError <em>Pct Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Error</em>'.
	 * @see electrickery.meter.Sensor#getPctError()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_PctError();

	/**
	 * Returns the meta object for the attribute '{@link electrickery.meter.Sensor#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see electrickery.meter.Sensor#getAction()
	 * @see #getSensor()
	 * @generated
	 */
	EAttribute getSensor_Action();

	/**
	 * Returns the meta object for enum '{@link electrickery.meter.meterAction <em>meter Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>meter Action</em>'.
	 * @see electrickery.meter.meterAction
	 * @generated
	 */
	EEnum getmeterAction();

	/**
	 * Returns the meta object for enum '{@link electrickery.meter.monitorValues <em>monitor Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>monitor Values</em>'.
	 * @see electrickery.meter.monitorValues
	 * @generated
	 */
	EEnum getmonitorValues();

	/**
	 * Returns the meta object for enum '{@link electrickery.meter.monitorAction <em>monitor Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>monitor Action</em>'.
	 * @see electrickery.meter.monitorAction
	 * @generated
	 */
	EEnum getmonitorAction();

	/**
	 * Returns the meta object for enum '{@link electrickery.meter.sensorMode <em>sensor Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>sensor Mode</em>'.
	 * @see electrickery.meter.sensorMode
	 * @generated
	 */
	EEnum getsensorMode();

	/**
	 * Returns the meta object for enum '{@link electrickery.meter.sensorAction <em>sensor Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>sensor Action</em>'.
	 * @see electrickery.meter.sensorAction
	 * @generated
	 */
	EEnum getsensorAction();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MeterFactory getMeterFactory();

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
		 * The meta object literal for the '{@link electrickery.meter.impl.MeterElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.impl.MeterElementImpl
		 * @see electrickery.meter.impl.MeterPackageImpl#getMeterElement()
		 * @generated
		 */
		EClass METER_ELEMENT = eINSTANCE.getMeterElement();

		/**
		 * The meta object literal for the '<em><b>Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METER_ELEMENT__ELEMENT_NAME = eINSTANCE.getMeterElement_ElementName();

		/**
		 * The meta object literal for the '<em><b>Metered Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METER_ELEMENT__METERED_ELEMENT = eINSTANCE.getMeterElement_MeteredElement();

		/**
		 * The meta object literal for the '<em><b>Metered Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METER_ELEMENT__METERED_TERMINAL = eINSTANCE.getMeterElement_MeteredTerminal();

		/**
		 * The meta object literal for the '{@link electrickery.meter.impl.EnergyMeterImpl <em>Energy Meter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.impl.EnergyMeterImpl
		 * @see electrickery.meter.impl.MeterPackageImpl#getEnergyMeter()
		 * @generated
		 */
		EClass ENERGY_METER = eINSTANCE.getEnergyMeter();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__ELEMENT = eINSTANCE.getEnergyMeter_Element();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__TERMINAL = eINSTANCE.getEnergyMeter_Terminal();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__ACTION = eINSTANCE.getEnergyMeter_Action();

		/**
		 * The meta object literal for the '<em><b>Option</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__OPTION = eINSTANCE.getEnergyMeter_Option();

		/**
		 * The meta object literal for the '<em><b>KVA Norm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__KVA_NORM = eINSTANCE.getEnergyMeter_KVANorm();

		/**
		 * The meta object literal for the '<em><b>KVA Emerg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__KVA_EMERG = eINSTANCE.getEnergyMeter_KVAEmerg();

		/**
		 * The meta object literal for the '<em><b>Peak Current</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__PEAK_CURRENT = eINSTANCE.getEnergyMeter_PeakCurrent();

		/**
		 * The meta object literal for the '<em><b>Zone List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__ZONE_LIST = eINSTANCE.getEnergyMeter_ZoneList();

		/**
		 * The meta object literal for the '<em><b>Local Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__LOCAL_ONLY = eINSTANCE.getEnergyMeter_LocalOnly();

		/**
		 * The meta object literal for the '<em><b>Mask</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__MASK = eINSTANCE.getEnergyMeter_Mask();

		/**
		 * The meta object literal for the '<em><b>Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__LOSSES = eINSTANCE.getEnergyMeter_Losses();

		/**
		 * The meta object literal for the '<em><b>Line Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__LINE_LOSSES = eINSTANCE.getEnergyMeter_LineLosses();

		/**
		 * The meta object literal for the '<em><b>Xfmr Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__XFMR_LOSSES = eINSTANCE.getEnergyMeter_XfmrLosses();

		/**
		 * The meta object literal for the '<em><b>Seq Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__SEQ_LOSSES = eINSTANCE.getEnergyMeter_SeqLosses();

		/**
		 * The meta object literal for the '<em><b>Three Phase Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__THREE_PHASE_LOSSES = eINSTANCE.getEnergyMeter_ThreePhaseLosses();

		/**
		 * The meta object literal for the '<em><b>VBase Losses</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__VBASE_LOSSES = eINSTANCE.getEnergyMeter_VBaseLosses();

		/**
		 * The meta object literal for the '<em><b>Overload Report</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENERGY_METER__OVERLOAD_REPORT = eINSTANCE.getEnergyMeter_OverloadReport();

		/**
		 * The meta object literal for the '{@link electrickery.meter.impl.MonitorImpl <em>Monitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.impl.MonitorImpl
		 * @see electrickery.meter.impl.MeterPackageImpl#getMonitor()
		 * @generated
		 */
		EClass MONITOR = eINSTANCE.getMonitor();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__ELEMENT = eINSTANCE.getMonitor_Element();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__TERMINAL = eINSTANCE.getMonitor_Terminal();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__MODE = eINSTANCE.getMonitor_Mode();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__ACTION = eINSTANCE.getMonitor_Action();

		/**
		 * The meta object literal for the '<em><b>Residual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__RESIDUAL = eINSTANCE.getMonitor_Residual();

		/**
		 * The meta object literal for the '<em><b>VI Polar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__VI_POLAR = eINSTANCE.getMonitor_VIPolar();

		/**
		 * The meta object literal for the '<em><b>PPolar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MONITOR__PPOLAR = eINSTANCE.getMonitor_PPolar();

		/**
		 * The meta object literal for the '{@link electrickery.meter.impl.SensorImpl <em>Sensor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.impl.SensorImpl
		 * @see electrickery.meter.impl.MeterPackageImpl#getSensor()
		 * @generated
		 */
		EClass SENSOR = eINSTANCE.getSensor();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__ELEMENT = eINSTANCE.getSensor_Element();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__TERMINAL = eINSTANCE.getSensor_Terminal();

		/**
		 * The meta object literal for the '<em><b>Modes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__MODES = eINSTANCE.getSensor_Modes();

		/**
		 * The meta object literal for the '<em><b>V</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__V = eINSTANCE.getSensor_V();

		/**
		 * The meta object literal for the '<em><b>I</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__I = eINSTANCE.getSensor_I();

		/**
		 * The meta object literal for the '<em><b>P</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__P = eINSTANCE.getSensor_P();

		/**
		 * The meta object literal for the '<em><b>Q</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__Q = eINSTANCE.getSensor_Q();

		/**
		 * The meta object literal for the '<em><b>Phases</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__PHASES = eINSTANCE.getSensor_Phases();

		/**
		 * The meta object literal for the '<em><b>Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__CONN = eINSTANCE.getSensor_Conn();

		/**
		 * The meta object literal for the '<em><b>Pct Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__PCT_ERROR = eINSTANCE.getSensor_PctError();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSOR__ACTION = eINSTANCE.getSensor_Action();

		/**
		 * The meta object literal for the '{@link electrickery.meter.meterAction <em>meter Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.meterAction
		 * @see electrickery.meter.impl.MeterPackageImpl#getmeterAction()
		 * @generated
		 */
		EEnum METER_ACTION = eINSTANCE.getmeterAction();

		/**
		 * The meta object literal for the '{@link electrickery.meter.monitorValues <em>monitor Values</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.monitorValues
		 * @see electrickery.meter.impl.MeterPackageImpl#getmonitorValues()
		 * @generated
		 */
		EEnum MONITOR_VALUES = eINSTANCE.getmonitorValues();

		/**
		 * The meta object literal for the '{@link electrickery.meter.monitorAction <em>monitor Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.monitorAction
		 * @see electrickery.meter.impl.MeterPackageImpl#getmonitorAction()
		 * @generated
		 */
		EEnum MONITOR_ACTION = eINSTANCE.getmonitorAction();

		/**
		 * The meta object literal for the '{@link electrickery.meter.sensorMode <em>sensor Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.sensorMode
		 * @see electrickery.meter.impl.MeterPackageImpl#getsensorMode()
		 * @generated
		 */
		EEnum SENSOR_MODE = eINSTANCE.getsensorMode();

		/**
		 * The meta object literal for the '{@link electrickery.meter.sensorAction <em>sensor Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.meter.sensorAction
		 * @see electrickery.meter.impl.MeterPackageImpl#getsensorAction()
		 * @generated
		 */
		EEnum SENSOR_ACTION = eINSTANCE.getsensorAction();

	}

} //MeterPackage
