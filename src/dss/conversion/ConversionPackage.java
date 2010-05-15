/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.conversion;

import dss.common.CommonPackage;

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
 * @see dss.conversion.ConversionFactory
 * @model kind="package"
 * @generated
 */
public interface ConversionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "conversion";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.openpowersystem.com/dss/conversion";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "conv";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConversionPackage eINSTANCE = dss.conversion.impl.ConversionPackageImpl.init();

	/**
	 * The meta object id for the '{@link dss.conversion.impl.PowerConversionElementImpl <em>Power Conversion Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.impl.PowerConversionElementImpl
	 * @see dss.conversion.impl.ConversionPackageImpl#getPowerConversionElement()
	 * @generated
	 */
	int POWER_CONVERSION_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__NAME = CommonPackage.CIRCUIT_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__ENABLED = CommonPackage.CIRCUIT_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__BASE_FREQ = CommonPackage.CIRCUIT_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__NODE_REF = CommonPackage.CIRCUIT_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__YORDER = CommonPackage.CIRCUIT_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__YPRIM_INVALID = CommonPackage.CIRCUIT_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__LAST_TERMINAL_CHECKED = CommonPackage.CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__CHECKED = CommonPackage.CIRCUIT_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__HAS_METER = CommonPackage.CIRCUIT_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__ISOLATED = CommonPackage.CIRCUIT_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__HAS_CONTROL = CommonPackage.CIRCUIT_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__PART_OF_FEEDER = CommonPackage.CIRCUIT_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__CONTROL_ELEMENT = CommonPackage.CIRCUIT_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__TERMINALS = CommonPackage.CIRCUIT_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__ACTIVE_TERMINAL = CommonPackage.CIRCUIT_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__NTERMS = CommonPackage.CIRCUIT_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__NCONDS = CommonPackage.CIRCUIT_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__NPHASES = CommonPackage.CIRCUIT_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__BUS_INDEX = CommonPackage.CIRCUIT_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__YPRIM_SERIES = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__YPRIM_SHUNT = CommonPackage.CIRCUIT_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__YPRIM = CommonPackage.CIRCUIT_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__YPRIM_FREQ = CommonPackage.CIRCUIT_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__SPECTRUM = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT__INJ_CURRENT = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Power Conversion Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWER_CONVERSION_ELEMENT_FEATURE_COUNT = CommonPackage.CIRCUIT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link dss.conversion.impl.CurrentSourceImpl <em>Current Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.impl.CurrentSourceImpl
	 * @see dss.conversion.impl.ConversionPackageImpl#getCurrentSource()
	 * @generated
	 */
	int CURRENT_SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__NAME = POWER_CONVERSION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__ENABLED = POWER_CONVERSION_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__BASE_FREQ = POWER_CONVERSION_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__NODE_REF = POWER_CONVERSION_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__YORDER = POWER_CONVERSION_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__YPRIM_INVALID = POWER_CONVERSION_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__LAST_TERMINAL_CHECKED = POWER_CONVERSION_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__CHECKED = POWER_CONVERSION_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__HAS_METER = POWER_CONVERSION_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__ISOLATED = POWER_CONVERSION_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__HAS_CONTROL = POWER_CONVERSION_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__PART_OF_FEEDER = POWER_CONVERSION_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__CONTROL_ELEMENT = POWER_CONVERSION_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__TERMINALS = POWER_CONVERSION_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__ACTIVE_TERMINAL = POWER_CONVERSION_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__NTERMS = POWER_CONVERSION_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__NCONDS = POWER_CONVERSION_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__NPHASES = POWER_CONVERSION_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__BUS_INDEX = POWER_CONVERSION_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__YPRIM_SERIES = POWER_CONVERSION_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__YPRIM_SHUNT = POWER_CONVERSION_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__YPRIM = POWER_CONVERSION_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__YPRIM_FREQ = POWER_CONVERSION_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__SPECTRUM = POWER_CONVERSION_ELEMENT__SPECTRUM;

	/**
	 * The feature id for the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__SPECTRUM_OBJ = POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ;

	/**
	 * The feature id for the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__INJ_CURRENT = POWER_CONVERSION_ELEMENT__INJ_CURRENT;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__BUS1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__AMPS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__ANGLE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__FREQUENCY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Phases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__PHASES = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Scan Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE__SCAN_TYPE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Current Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_SOURCE_FEATURE_COUNT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link dss.conversion.impl.EquivalentImpl <em>Equivalent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.impl.EquivalentImpl
	 * @see dss.conversion.impl.ConversionPackageImpl#getEquivalent()
	 * @generated
	 */
	int EQUIVALENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__NAME = POWER_CONVERSION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__ENABLED = POWER_CONVERSION_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__BASE_FREQ = POWER_CONVERSION_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__NODE_REF = POWER_CONVERSION_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__YORDER = POWER_CONVERSION_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__YPRIM_INVALID = POWER_CONVERSION_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__LAST_TERMINAL_CHECKED = POWER_CONVERSION_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__CHECKED = POWER_CONVERSION_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__HAS_METER = POWER_CONVERSION_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__ISOLATED = POWER_CONVERSION_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__HAS_CONTROL = POWER_CONVERSION_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__PART_OF_FEEDER = POWER_CONVERSION_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__CONTROL_ELEMENT = POWER_CONVERSION_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__TERMINALS = POWER_CONVERSION_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__ACTIVE_TERMINAL = POWER_CONVERSION_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__NTERMS = POWER_CONVERSION_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__NCONDS = POWER_CONVERSION_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__NPHASES = POWER_CONVERSION_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__BUS_INDEX = POWER_CONVERSION_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__YPRIM_SERIES = POWER_CONVERSION_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__YPRIM_SHUNT = POWER_CONVERSION_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__YPRIM = POWER_CONVERSION_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__YPRIM_FREQ = POWER_CONVERSION_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__SPECTRUM = POWER_CONVERSION_ELEMENT__SPECTRUM;

	/**
	 * The feature id for the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__SPECTRUM_OBJ = POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ;

	/**
	 * The feature id for the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__INJ_CURRENT = POWER_CONVERSION_ELEMENT__INJ_CURRENT;

	/**
	 * The feature id for the '<em><b>Buses</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__BUSES = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__BASE_KV = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__PU = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__ANGLE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__FREQUENCY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Phases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__PHASES = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>R1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__R1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>X1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__X1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>R0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__R0 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>X0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT__X0 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Equivalent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUIVALENT_FEATURE_COUNT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link dss.conversion.impl.GeneratorImpl <em>Generator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.impl.GeneratorImpl
	 * @see dss.conversion.impl.ConversionPackageImpl#getGenerator()
	 * @generated
	 */
	int GENERATOR = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__NAME = POWER_CONVERSION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ENABLED = POWER_CONVERSION_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__BASE_FREQ = POWER_CONVERSION_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__NODE_REF = POWER_CONVERSION_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YORDER = POWER_CONVERSION_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YPRIM_INVALID = POWER_CONVERSION_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__LAST_TERMINAL_CHECKED = POWER_CONVERSION_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__CHECKED = POWER_CONVERSION_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__HAS_METER = POWER_CONVERSION_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ISOLATED = POWER_CONVERSION_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__HAS_CONTROL = POWER_CONVERSION_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__PART_OF_FEEDER = POWER_CONVERSION_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__CONTROL_ELEMENT = POWER_CONVERSION_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__TERMINALS = POWER_CONVERSION_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ACTIVE_TERMINAL = POWER_CONVERSION_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__NTERMS = POWER_CONVERSION_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__NCONDS = POWER_CONVERSION_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__NPHASES = POWER_CONVERSION_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__BUS_INDEX = POWER_CONVERSION_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YPRIM_SERIES = POWER_CONVERSION_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YPRIM_SHUNT = POWER_CONVERSION_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YPRIM = POWER_CONVERSION_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YPRIM_FREQ = POWER_CONVERSION_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__SPECTRUM = POWER_CONVERSION_ELEMENT__SPECTRUM;

	/**
	 * The feature id for the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__SPECTRUM_OBJ = POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ;

	/**
	 * The feature id for the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__INJ_CURRENT = POWER_CONVERSION_ELEMENT__INJ_CURRENT;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__CIRCUIT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__BUS1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__KV = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>KW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__KW = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Pf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__PF = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__KV_AR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__MODEL = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>VMin PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VMIN_PU = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>VMax PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VMAX_PU = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Yearly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YEARLY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Daily</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DAILY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Duty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DUTY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Disp Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DISP_MODE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Disp Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DISP_VALUE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__CONN = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>RNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__RNEUT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>XNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__XNEUT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__STATUS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__CLASS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>VPU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VPU = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>VTarget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VTARGET = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Max KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__MAX_KV_AR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Min KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__MIN_KV_AR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Pv Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__PV_FACTOR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Force On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__FORCE_ON = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__KVA = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>MVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__MVA = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>XD</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__XD = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>XDp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__XDP = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>XDpp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__XDPP = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>H</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__H = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 30;

	/**
	 * The feature id for the '<em><b>D</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__D = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 31;

	/**
	 * The feature id for the '<em><b>User Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__USER_MODEL = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 32;

	/**
	 * The feature id for the '<em><b>User Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__USER_DATA = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 33;

	/**
	 * The feature id for the '<em><b>Shaft Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__SHAFT_MODEL = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 34;

	/**
	 * The feature id for the '<em><b>Shaft Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__SHAFT_DATA = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 35;

	/**
	 * The feature id for the '<em><b>Debug Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DEBUG_TRACE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 36;

	/**
	 * The feature id for the '<em><b>Gen On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__GEN_ON = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 37;

	/**
	 * The feature id for the '<em><b>Shape Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__SHAPE_FACTOR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 38;

	/**
	 * The feature id for the '<em><b>Forced On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__FORCED_ON = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 39;

	/**
	 * The feature id for the '<em><b>Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__FIXED = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 40;

	/**
	 * The feature id for the '<em><b>YEq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YEQ = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 41;

	/**
	 * The feature id for the '<em><b>YEq95</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YEQ95 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 42;

	/**
	 * The feature id for the '<em><b>YEq105</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YEQ105 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 43;

	/**
	 * The feature id for the '<em><b>VBase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VBASE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 44;

	/**
	 * The feature id for the '<em><b>VBase95</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VBASE95 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 45;

	/**
	 * The feature id for the '<em><b>VBase105</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VBASE105 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 46;

	/**
	 * The feature id for the '<em><b>Var Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VAR_BASE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 47;

	/**
	 * The feature id for the '<em><b>Var Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VAR_MIN = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 48;

	/**
	 * The feature id for the '<em><b>Var Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__VAR_MAX = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 49;

	/**
	 * The feature id for the '<em><b>Delta QMax</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DELTA_QMAX = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 50;

	/**
	 * The feature id for the '<em><b>DQd V</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DQD_V = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 51;

	/**
	 * The feature id for the '<em><b>DQd VSaved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DQD_VSAVED = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 52;

	/**
	 * The feature id for the '<em><b>YQ Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__YQ_FIXED = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 53;

	/**
	 * The number of structural features of the '<em>Generator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_FEATURE_COUNT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 54;

	/**
	 * The meta object id for the '{@link dss.conversion.impl.LoadImpl <em>Load</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.impl.LoadImpl
	 * @see dss.conversion.impl.ConversionPackageImpl#getLoad()
	 * @generated
	 */
	int LOAD = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__NAME = POWER_CONVERSION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__ENABLED = POWER_CONVERSION_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__BASE_FREQ = POWER_CONVERSION_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__NODE_REF = POWER_CONVERSION_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YORDER = POWER_CONVERSION_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YPRIM_INVALID = POWER_CONVERSION_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__LAST_TERMINAL_CHECKED = POWER_CONVERSION_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CHECKED = POWER_CONVERSION_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__HAS_METER = POWER_CONVERSION_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__ISOLATED = POWER_CONVERSION_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__HAS_CONTROL = POWER_CONVERSION_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__PART_OF_FEEDER = POWER_CONVERSION_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CONTROL_ELEMENT = POWER_CONVERSION_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__TERMINALS = POWER_CONVERSION_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__ACTIVE_TERMINAL = POWER_CONVERSION_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__NTERMS = POWER_CONVERSION_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__NCONDS = POWER_CONVERSION_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__NPHASES = POWER_CONVERSION_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__BUS_INDEX = POWER_CONVERSION_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YPRIM_SERIES = POWER_CONVERSION_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YPRIM_SHUNT = POWER_CONVERSION_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YPRIM = POWER_CONVERSION_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YPRIM_FREQ = POWER_CONVERSION_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__SPECTRUM = POWER_CONVERSION_ELEMENT__SPECTRUM;

	/**
	 * The feature id for the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__SPECTRUM_OBJ = POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ;

	/**
	 * The feature id for the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__INJ_CURRENT = POWER_CONVERSION_ELEMENT__INJ_CURRENT;

	/**
	 * The feature id for the '<em><b>Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CIRCUIT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Growth Shape Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__GROWTH_SHAPE_OBJ = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__BUS1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__KV = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>KW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__KW = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__KV_AR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>PF</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__PF = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__MODEL = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Load Spec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__LOAD_SPEC = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Yearly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__YEARLY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Daily</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__DAILY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Duty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__DUTY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Growth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__GROWTH = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Conn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CONN = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>RNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__RNEUT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>XNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__XNEUT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__STATUS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CLASS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>VMin PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__VMIN_PU = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>VMax PU</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__VMAX_PU = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>VMin Norm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__VMIN_NORM = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>VMin Emerg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__VMIN_EMERG = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Xf KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__XF_KVA = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Allocation Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__ALLOCATION_FACTOR = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__KVA = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Pct Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__PCT_MEAN = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Pct Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__PCT_STD_DEV = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Cvr Watts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CVR_WATTS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Cvr Vars</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD__CVR_VARS = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 28;

	/**
	 * The number of structural features of the '<em>Load</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_FEATURE_COUNT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 29;

	/**
	 * The meta object id for the '{@link dss.conversion.impl.VoltageSourceImpl <em>Voltage Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.impl.VoltageSourceImpl
	 * @see dss.conversion.impl.ConversionPackageImpl#getVoltageSource()
	 * @generated
	 */
	int VOLTAGE_SOURCE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__NAME = POWER_CONVERSION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ENABLED = POWER_CONVERSION_ELEMENT__ENABLED;

	/**
	 * The feature id for the '<em><b>Base Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__BASE_FREQ = POWER_CONVERSION_ELEMENT__BASE_FREQ;

	/**
	 * The feature id for the '<em><b>Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__NODE_REF = POWER_CONVERSION_ELEMENT__NODE_REF;

	/**
	 * The feature id for the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__YORDER = POWER_CONVERSION_ELEMENT__YORDER;

	/**
	 * The feature id for the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__YPRIM_INVALID = POWER_CONVERSION_ELEMENT__YPRIM_INVALID;

	/**
	 * The feature id for the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__LAST_TERMINAL_CHECKED = POWER_CONVERSION_ELEMENT__LAST_TERMINAL_CHECKED;

	/**
	 * The feature id for the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__CHECKED = POWER_CONVERSION_ELEMENT__CHECKED;

	/**
	 * The feature id for the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__HAS_METER = POWER_CONVERSION_ELEMENT__HAS_METER;

	/**
	 * The feature id for the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ISOLATED = POWER_CONVERSION_ELEMENT__ISOLATED;

	/**
	 * The feature id for the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__HAS_CONTROL = POWER_CONVERSION_ELEMENT__HAS_CONTROL;

	/**
	 * The feature id for the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__PART_OF_FEEDER = POWER_CONVERSION_ELEMENT__PART_OF_FEEDER;

	/**
	 * The feature id for the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__CONTROL_ELEMENT = POWER_CONVERSION_ELEMENT__CONTROL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Terminals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__TERMINALS = POWER_CONVERSION_ELEMENT__TERMINALS;

	/**
	 * The feature id for the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ACTIVE_TERMINAL = POWER_CONVERSION_ELEMENT__ACTIVE_TERMINAL;

	/**
	 * The feature id for the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__NTERMS = POWER_CONVERSION_ELEMENT__NTERMS;

	/**
	 * The feature id for the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__NCONDS = POWER_CONVERSION_ELEMENT__NCONDS;

	/**
	 * The feature id for the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__NPHASES = POWER_CONVERSION_ELEMENT__NPHASES;

	/**
	 * The feature id for the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__BUS_INDEX = POWER_CONVERSION_ELEMENT__BUS_INDEX;

	/**
	 * The feature id for the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__YPRIM_SERIES = POWER_CONVERSION_ELEMENT__YPRIM_SERIES;

	/**
	 * The feature id for the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__YPRIM_SHUNT = POWER_CONVERSION_ELEMENT__YPRIM_SHUNT;

	/**
	 * The feature id for the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__YPRIM = POWER_CONVERSION_ELEMENT__YPRIM;

	/**
	 * The feature id for the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__YPRIM_FREQ = POWER_CONVERSION_ELEMENT__YPRIM_FREQ;

	/**
	 * The feature id for the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__SPECTRUM = POWER_CONVERSION_ELEMENT__SPECTRUM;

	/**
	 * The feature id for the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__SPECTRUM_OBJ = POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ;

	/**
	 * The feature id for the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__INJ_CURRENT = POWER_CONVERSION_ELEMENT__INJ_CURRENT;

	/**
	 * The feature id for the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__BUS1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__BASE_KV = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Per Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__PER_UNIT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ANGLE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__FREQUENCY = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Mva SC3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__MVA_SC3 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mva SC1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__MVA_SC1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>X1R1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__X1_R1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>X0R0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__X0_R0 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>ISC3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ISC3 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>ISC1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ISC1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>R1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__R1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>X1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__X1 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>R0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__R0 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>X0</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__X0 = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Scan Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__SCAN_TYPE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__Z = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>ZInv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ZINV = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>ZSpec Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__ZSPEC_TYPE = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>VMag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE__VMAG = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The number of structural features of the '<em>Voltage Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLTAGE_SOURCE_FEATURE_COUNT = POWER_CONVERSION_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The meta object id for the '{@link dss.conversion.sequenceType <em>sequence Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.sequenceType
	 * @see dss.conversion.impl.ConversionPackageImpl#getsequenceType()
	 * @generated
	 */
	int SEQUENCE_TYPE = 6;

	/**
	 * The meta object id for the '{@link dss.conversion.generatorModel <em>generator Model</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.generatorModel
	 * @see dss.conversion.impl.ConversionPackageImpl#getgeneratorModel()
	 * @generated
	 */
	int GENERATOR_MODEL = 7;

	/**
	 * The meta object id for the '{@link dss.conversion.dispatchType <em>dispatch Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.dispatchType
	 * @see dss.conversion.impl.ConversionPackageImpl#getdispatchType()
	 * @generated
	 */
	int DISPATCH_TYPE = 8;

	/**
	 * The meta object id for the '{@link dss.conversion.generatorStatus <em>generator Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.generatorStatus
	 * @see dss.conversion.impl.ConversionPackageImpl#getgeneratorStatus()
	 * @generated
	 */
	int GENERATOR_STATUS = 9;

	/**
	 * The meta object id for the '{@link dss.conversion.loadModel <em>load Model</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.loadModel
	 * @see dss.conversion.impl.ConversionPackageImpl#getloadModel()
	 * @generated
	 */
	int LOAD_MODEL = 10;

	/**
	 * The meta object id for the '{@link dss.conversion.loadStatus <em>load Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.loadStatus
	 * @see dss.conversion.impl.ConversionPackageImpl#getloadStatus()
	 * @generated
	 */
	int LOAD_STATUS = 11;


	/**
	 * The meta object id for the '{@link dss.conversion.loadSpecType <em>load Spec Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.loadSpecType
	 * @see dss.conversion.impl.ConversionPackageImpl#getloadSpecType()
	 * @generated
	 */
	int LOAD_SPEC_TYPE = 12;

	/**
	 * The meta object id for the '{@link dss.conversion.specType <em>spec Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.conversion.specType
	 * @see dss.conversion.impl.ConversionPackageImpl#getspecType()
	 * @generated
	 */
	int SPEC_TYPE = 13;


	/**
	 * Returns the meta object for class '{@link dss.conversion.CurrentSource <em>Current Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Current Source</em>'.
	 * @see dss.conversion.CurrentSource
	 * @generated
	 */
	EClass getCurrentSource();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.CurrentSource#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.conversion.CurrentSource#getBus1()
	 * @see #getCurrentSource()
	 * @generated
	 */
	EAttribute getCurrentSource_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.CurrentSource#getAmps <em>Amps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Amps</em>'.
	 * @see dss.conversion.CurrentSource#getAmps()
	 * @see #getCurrentSource()
	 * @generated
	 */
	EAttribute getCurrentSource_Amps();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.CurrentSource#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see dss.conversion.CurrentSource#getAngle()
	 * @see #getCurrentSource()
	 * @generated
	 */
	EAttribute getCurrentSource_Angle();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.CurrentSource#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see dss.conversion.CurrentSource#getFrequency()
	 * @see #getCurrentSource()
	 * @generated
	 */
	EAttribute getCurrentSource_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.CurrentSource#getPhases <em>Phases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phases</em>'.
	 * @see dss.conversion.CurrentSource#getPhases()
	 * @see #getCurrentSource()
	 * @generated
	 */
	EAttribute getCurrentSource_Phases();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.CurrentSource#getScanType <em>Scan Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scan Type</em>'.
	 * @see dss.conversion.CurrentSource#getScanType()
	 * @see #getCurrentSource()
	 * @generated
	 */
	EAttribute getCurrentSource_ScanType();

	/**
	 * Returns the meta object for class '{@link dss.conversion.Equivalent <em>Equivalent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equivalent</em>'.
	 * @see dss.conversion.Equivalent
	 * @generated
	 */
	EClass getEquivalent();

	/**
	 * Returns the meta object for the attribute list '{@link dss.conversion.Equivalent#getBuses <em>Buses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Buses</em>'.
	 * @see dss.conversion.Equivalent#getBuses()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_Buses();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getBaseKV <em>Base KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base KV</em>'.
	 * @see dss.conversion.Equivalent#getBaseKV()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_BaseKV();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getPu <em>Pu</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pu</em>'.
	 * @see dss.conversion.Equivalent#getPu()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_Pu();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see dss.conversion.Equivalent#getAngle()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_Angle();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see dss.conversion.Equivalent#getFrequency()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getPhases <em>Phases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phases</em>'.
	 * @see dss.conversion.Equivalent#getPhases()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_Phases();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getR1 <em>R1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R1</em>'.
	 * @see dss.conversion.Equivalent#getR1()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_R1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getX1 <em>X1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X1</em>'.
	 * @see dss.conversion.Equivalent#getX1()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_X1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getR0 <em>R0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R0</em>'.
	 * @see dss.conversion.Equivalent#getR0()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_R0();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Equivalent#getX0 <em>X0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X0</em>'.
	 * @see dss.conversion.Equivalent#getX0()
	 * @see #getEquivalent()
	 * @generated
	 */
	EAttribute getEquivalent_X0();

	/**
	 * Returns the meta object for class '{@link dss.conversion.Generator <em>Generator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator</em>'.
	 * @see dss.conversion.Generator
	 * @generated
	 */
	EClass getGenerator();

	/**
	 * Returns the meta object for the container reference '{@link dss.conversion.Generator#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Circuit</em>'.
	 * @see dss.conversion.Generator#getCircuit()
	 * @see #getGenerator()
	 * @generated
	 */
	EReference getGenerator_Circuit();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.conversion.Generator#getBus1()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getKV <em>KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV</em>'.
	 * @see dss.conversion.Generator#getKV()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_KV();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getKW <em>KW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KW</em>'.
	 * @see dss.conversion.Generator#getKW()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_KW();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getPf <em>Pf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pf</em>'.
	 * @see dss.conversion.Generator#getPf()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Pf();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getKVAr <em>KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Ar</em>'.
	 * @see dss.conversion.Generator#getKVAr()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_KVAr();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model</em>'.
	 * @see dss.conversion.Generator#getModel()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Model();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVMinPU <em>VMin PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMin PU</em>'.
	 * @see dss.conversion.Generator#getVMinPU()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VMinPU();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVMaxPU <em>VMax PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMax PU</em>'.
	 * @see dss.conversion.Generator#getVMaxPU()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VMaxPU();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getYearly <em>Yearly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Yearly</em>'.
	 * @see dss.conversion.Generator#getYearly()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Yearly();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDaily <em>Daily</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Daily</em>'.
	 * @see dss.conversion.Generator#getDaily()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Daily();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDuty <em>Duty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duty</em>'.
	 * @see dss.conversion.Generator#getDuty()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Duty();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDispMode <em>Disp Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disp Mode</em>'.
	 * @see dss.conversion.Generator#getDispMode()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_DispMode();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDispValue <em>Disp Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disp Value</em>'.
	 * @see dss.conversion.Generator#getDispValue()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_DispValue();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getConn <em>Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conn</em>'.
	 * @see dss.conversion.Generator#getConn()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Conn();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getRNeut <em>RNeut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RNeut</em>'.
	 * @see dss.conversion.Generator#getRNeut()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_RNeut();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getXNeut <em>XNeut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XNeut</em>'.
	 * @see dss.conversion.Generator#getXNeut()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_XNeut();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see dss.conversion.Generator#getStatus()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Status();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see dss.conversion.Generator#getClass_()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Class();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVPU <em>VPU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VPU</em>'.
	 * @see dss.conversion.Generator#getVPU()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VPU();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVTarget <em>VTarget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VTarget</em>'.
	 * @see dss.conversion.Generator#getVTarget()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VTarget();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getMaxKVAr <em>Max KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max KV Ar</em>'.
	 * @see dss.conversion.Generator#getMaxKVAr()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_MaxKVAr();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getMinKVAr <em>Min KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min KV Ar</em>'.
	 * @see dss.conversion.Generator#getMinKVAr()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_MinKVAr();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getPvFactor <em>Pv Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pv Factor</em>'.
	 * @see dss.conversion.Generator#getPvFactor()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_PvFactor();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#isForceOn <em>Force On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Force On</em>'.
	 * @see dss.conversion.Generator#isForceOn()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_ForceOn();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getKVA <em>KVA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KVA</em>'.
	 * @see dss.conversion.Generator#getKVA()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_KVA();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getMVA <em>MVA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>MVA</em>'.
	 * @see dss.conversion.Generator#getMVA()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_MVA();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getXD <em>XD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XD</em>'.
	 * @see dss.conversion.Generator#getXD()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_XD();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getXDp <em>XDp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XDp</em>'.
	 * @see dss.conversion.Generator#getXDp()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_XDp();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getXDpp <em>XDpp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XDpp</em>'.
	 * @see dss.conversion.Generator#getXDpp()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_XDpp();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getH <em>H</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>H</em>'.
	 * @see dss.conversion.Generator#getH()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_H();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getD <em>D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>D</em>'.
	 * @see dss.conversion.Generator#getD()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_D();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getUserModel <em>User Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Model</em>'.
	 * @see dss.conversion.Generator#getUserModel()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_UserModel();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getUserData <em>User Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Data</em>'.
	 * @see dss.conversion.Generator#getUserData()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_UserData();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getShaftModel <em>Shaft Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shaft Model</em>'.
	 * @see dss.conversion.Generator#getShaftModel()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_ShaftModel();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getShaftData <em>Shaft Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shaft Data</em>'.
	 * @see dss.conversion.Generator#getShaftData()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_ShaftData();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#isDebugTrace <em>Debug Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug Trace</em>'.
	 * @see dss.conversion.Generator#isDebugTrace()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_DebugTrace();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#isGenOn <em>Gen On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gen On</em>'.
	 * @see dss.conversion.Generator#isGenOn()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_GenOn();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getShapeFactor <em>Shape Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shape Factor</em>'.
	 * @see dss.conversion.Generator#getShapeFactor()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_ShapeFactor();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#isForcedOn <em>Forced On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forced On</em>'.
	 * @see dss.conversion.Generator#isForcedOn()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_ForcedOn();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#isFixed <em>Fixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed</em>'.
	 * @see dss.conversion.Generator#isFixed()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Fixed();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getYEq <em>YEq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YEq</em>'.
	 * @see dss.conversion.Generator#getYEq()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_YEq();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getYEq95 <em>YEq95</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YEq95</em>'.
	 * @see dss.conversion.Generator#getYEq95()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_YEq95();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getYEq105 <em>YEq105</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YEq105</em>'.
	 * @see dss.conversion.Generator#getYEq105()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_YEq105();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVBase <em>VBase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VBase</em>'.
	 * @see dss.conversion.Generator#getVBase()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VBase();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVBase95 <em>VBase95</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VBase95</em>'.
	 * @see dss.conversion.Generator#getVBase95()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VBase95();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVBase105 <em>VBase105</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VBase105</em>'.
	 * @see dss.conversion.Generator#getVBase105()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VBase105();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVarBase <em>Var Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Var Base</em>'.
	 * @see dss.conversion.Generator#getVarBase()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VarBase();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVarMin <em>Var Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Var Min</em>'.
	 * @see dss.conversion.Generator#getVarMin()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VarMin();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getVarMax <em>Var Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Var Max</em>'.
	 * @see dss.conversion.Generator#getVarMax()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_VarMax();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDeltaQMax <em>Delta QMax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delta QMax</em>'.
	 * @see dss.conversion.Generator#getDeltaQMax()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_DeltaQMax();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDQdV <em>DQd V</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>DQd V</em>'.
	 * @see dss.conversion.Generator#getDQdV()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_DQdV();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getDQdVSaved <em>DQd VSaved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>DQd VSaved</em>'.
	 * @see dss.conversion.Generator#getDQdVSaved()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_DQdVSaved();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Generator#getYQFixed <em>YQ Fixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YQ Fixed</em>'.
	 * @see dss.conversion.Generator#getYQFixed()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_YQFixed();

	/**
	 * Returns the meta object for class '{@link dss.conversion.Load <em>Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load</em>'.
	 * @see dss.conversion.Load
	 * @generated
	 */
	EClass getLoad();

	/**
	 * Returns the meta object for the reference '{@link dss.conversion.Load#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Circuit</em>'.
	 * @see dss.conversion.Load#getCircuit()
	 * @see #getLoad()
	 * @generated
	 */
	EReference getLoad_Circuit();

	/**
	 * Returns the meta object for the reference '{@link dss.conversion.Load#getGrowthShapeObj <em>Growth Shape Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Growth Shape Obj</em>'.
	 * @see dss.conversion.Load#getGrowthShapeObj()
	 * @see #getLoad()
	 * @generated
	 */
	EReference getLoad_GrowthShapeObj();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.conversion.Load#getBus1()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getKV <em>KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV</em>'.
	 * @see dss.conversion.Load#getKV()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_KV();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getKW <em>KW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KW</em>'.
	 * @see dss.conversion.Load#getKW()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_KW();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model</em>'.
	 * @see dss.conversion.Load#getModel()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Model();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getLoadSpec <em>Load Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Spec</em>'.
	 * @see dss.conversion.Load#getLoadSpec()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_LoadSpec();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getYearly <em>Yearly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Yearly</em>'.
	 * @see dss.conversion.Load#getYearly()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Yearly();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getDaily <em>Daily</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Daily</em>'.
	 * @see dss.conversion.Load#getDaily()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Daily();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getDuty <em>Duty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duty</em>'.
	 * @see dss.conversion.Load#getDuty()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Duty();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getGrowth <em>Growth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Growth</em>'.
	 * @see dss.conversion.Load#getGrowth()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Growth();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getConn <em>Conn</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Conn</em>'.
	 * @see dss.conversion.Load#getConn()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Conn();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getKVAr <em>KV Ar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KV Ar</em>'.
	 * @see dss.conversion.Load#getKVAr()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_KVAr();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getPF <em>PF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PF</em>'.
	 * @see dss.conversion.Load#getPF()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_PF();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getRNeut <em>RNeut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>RNeut</em>'.
	 * @see dss.conversion.Load#getRNeut()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_RNeut();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getXNeut <em>XNeut</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XNeut</em>'.
	 * @see dss.conversion.Load#getXNeut()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_XNeut();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see dss.conversion.Load#getStatus()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Status();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see dss.conversion.Load#getClass_()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_Class();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getVMinPU <em>VMin PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMin PU</em>'.
	 * @see dss.conversion.Load#getVMinPU()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_VMinPU();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getVMaxPU <em>VMax PU</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMax PU</em>'.
	 * @see dss.conversion.Load#getVMaxPU()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_VMaxPU();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getVMinNorm <em>VMin Norm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMin Norm</em>'.
	 * @see dss.conversion.Load#getVMinNorm()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_VMinNorm();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getVMinEmerg <em>VMin Emerg</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMin Emerg</em>'.
	 * @see dss.conversion.Load#getVMinEmerg()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_VMinEmerg();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getXfKVA <em>Xf KVA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xf KVA</em>'.
	 * @see dss.conversion.Load#getXfKVA()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_XfKVA();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getAllocationFactor <em>Allocation Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation Factor</em>'.
	 * @see dss.conversion.Load#getAllocationFactor()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_AllocationFactor();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getKVA <em>KVA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>KVA</em>'.
	 * @see dss.conversion.Load#getKVA()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_KVA();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getPctMean <em>Pct Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Mean</em>'.
	 * @see dss.conversion.Load#getPctMean()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_PctMean();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getPctStdDev <em>Pct Std Dev</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pct Std Dev</em>'.
	 * @see dss.conversion.Load#getPctStdDev()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_PctStdDev();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getCvrWatts <em>Cvr Watts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cvr Watts</em>'.
	 * @see dss.conversion.Load#getCvrWatts()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_CvrWatts();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.Load#getCvrVars <em>Cvr Vars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cvr Vars</em>'.
	 * @see dss.conversion.Load#getCvrVars()
	 * @see #getLoad()
	 * @generated
	 */
	EAttribute getLoad_CvrVars();

	/**
	 * Returns the meta object for class '{@link dss.conversion.PowerConversionElement <em>Power Conversion Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Power Conversion Element</em>'.
	 * @see dss.conversion.PowerConversionElement
	 * @generated
	 */
	EClass getPowerConversionElement();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.PowerConversionElement#getSpectrum <em>Spectrum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spectrum</em>'.
	 * @see dss.conversion.PowerConversionElement#getSpectrum()
	 * @see #getPowerConversionElement()
	 * @generated
	 */
	EAttribute getPowerConversionElement_Spectrum();

	/**
	 * Returns the meta object for the reference '{@link dss.conversion.PowerConversionElement#getSpectrumObj <em>Spectrum Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Spectrum Obj</em>'.
	 * @see dss.conversion.PowerConversionElement#getSpectrumObj()
	 * @see #getPowerConversionElement()
	 * @generated
	 */
	EReference getPowerConversionElement_SpectrumObj();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.PowerConversionElement#getInjCurrent <em>Inj Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inj Current</em>'.
	 * @see dss.conversion.PowerConversionElement#getInjCurrent()
	 * @see #getPowerConversionElement()
	 * @generated
	 */
	EAttribute getPowerConversionElement_InjCurrent();

	/**
	 * Returns the meta object for class '{@link dss.conversion.VoltageSource <em>Voltage Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Voltage Source</em>'.
	 * @see dss.conversion.VoltageSource
	 * @generated
	 */
	EClass getVoltageSource();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getBus1 <em>Bus1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus1</em>'.
	 * @see dss.conversion.VoltageSource#getBus1()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_Bus1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getBaseKV <em>Base KV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base KV</em>'.
	 * @see dss.conversion.VoltageSource#getBaseKV()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_BaseKV();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getPerUnit <em>Per Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Per Unit</em>'.
	 * @see dss.conversion.VoltageSource#getPerUnit()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_PerUnit();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see dss.conversion.VoltageSource#getAngle()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_Angle();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see dss.conversion.VoltageSource#getFrequency()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_Frequency();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getMvaSC3 <em>Mva SC3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mva SC3</em>'.
	 * @see dss.conversion.VoltageSource#getMvaSC3()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_MvaSC3();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getMvaSC1 <em>Mva SC1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mva SC1</em>'.
	 * @see dss.conversion.VoltageSource#getMvaSC1()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_MvaSC1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getX1R1 <em>X1R1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X1R1</em>'.
	 * @see dss.conversion.VoltageSource#getX1R1()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_X1R1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getX0R0 <em>X0R0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X0R0</em>'.
	 * @see dss.conversion.VoltageSource#getX0R0()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_X0R0();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getISC3 <em>ISC3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ISC3</em>'.
	 * @see dss.conversion.VoltageSource#getISC3()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_ISC3();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getISC1 <em>ISC1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ISC1</em>'.
	 * @see dss.conversion.VoltageSource#getISC1()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_ISC1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getR1 <em>R1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R1</em>'.
	 * @see dss.conversion.VoltageSource#getR1()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_R1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getX1 <em>X1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X1</em>'.
	 * @see dss.conversion.VoltageSource#getX1()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_X1();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getR0 <em>R0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>R0</em>'.
	 * @see dss.conversion.VoltageSource#getR0()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_R0();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getX0 <em>X0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X0</em>'.
	 * @see dss.conversion.VoltageSource#getX0()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_X0();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getScanType <em>Scan Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scan Type</em>'.
	 * @see dss.conversion.VoltageSource#getScanType()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_ScanType();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getZ <em>Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Z</em>'.
	 * @see dss.conversion.VoltageSource#getZ()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_Z();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getZInv <em>ZInv</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ZInv</em>'.
	 * @see dss.conversion.VoltageSource#getZInv()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_ZInv();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getZSpecType <em>ZSpec Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ZSpec Type</em>'.
	 * @see dss.conversion.VoltageSource#getZSpecType()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_ZSpecType();

	/**
	 * Returns the meta object for the attribute '{@link dss.conversion.VoltageSource#getVMag <em>VMag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VMag</em>'.
	 * @see dss.conversion.VoltageSource#getVMag()
	 * @see #getVoltageSource()
	 * @generated
	 */
	EAttribute getVoltageSource_VMag();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.sequenceType <em>sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>sequence Type</em>'.
	 * @see dss.conversion.sequenceType
	 * @generated
	 */
	EEnum getsequenceType();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.generatorModel <em>generator Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>generator Model</em>'.
	 * @see dss.conversion.generatorModel
	 * @generated
	 */
	EEnum getgeneratorModel();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.dispatchType <em>dispatch Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>dispatch Type</em>'.
	 * @see dss.conversion.dispatchType
	 * @generated
	 */
	EEnum getdispatchType();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.generatorStatus <em>generator Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>generator Status</em>'.
	 * @see dss.conversion.generatorStatus
	 * @generated
	 */
	EEnum getgeneratorStatus();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.loadModel <em>load Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>load Model</em>'.
	 * @see dss.conversion.loadModel
	 * @generated
	 */
	EEnum getloadModel();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.loadStatus <em>load Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>load Status</em>'.
	 * @see dss.conversion.loadStatus
	 * @generated
	 */
	EEnum getloadStatus();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.loadSpecType <em>load Spec Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>load Spec Type</em>'.
	 * @see dss.conversion.loadSpecType
	 * @generated
	 */
	EEnum getloadSpecType();

	/**
	 * Returns the meta object for enum '{@link dss.conversion.specType <em>spec Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>spec Type</em>'.
	 * @see dss.conversion.specType
	 * @generated
	 */
	EEnum getspecType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConversionFactory getConversionFactory();

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
		 * The meta object literal for the '{@link dss.conversion.impl.CurrentSourceImpl <em>Current Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.impl.CurrentSourceImpl
		 * @see dss.conversion.impl.ConversionPackageImpl#getCurrentSource()
		 * @generated
		 */
		EClass CURRENT_SOURCE = eINSTANCE.getCurrentSource();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRENT_SOURCE__BUS1 = eINSTANCE.getCurrentSource_Bus1();

		/**
		 * The meta object literal for the '<em><b>Amps</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRENT_SOURCE__AMPS = eINSTANCE.getCurrentSource_Amps();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRENT_SOURCE__ANGLE = eINSTANCE.getCurrentSource_Angle();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRENT_SOURCE__FREQUENCY = eINSTANCE.getCurrentSource_Frequency();

		/**
		 * The meta object literal for the '<em><b>Phases</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRENT_SOURCE__PHASES = eINSTANCE.getCurrentSource_Phases();

		/**
		 * The meta object literal for the '<em><b>Scan Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CURRENT_SOURCE__SCAN_TYPE = eINSTANCE.getCurrentSource_ScanType();

		/**
		 * The meta object literal for the '{@link dss.conversion.impl.EquivalentImpl <em>Equivalent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.impl.EquivalentImpl
		 * @see dss.conversion.impl.ConversionPackageImpl#getEquivalent()
		 * @generated
		 */
		EClass EQUIVALENT = eINSTANCE.getEquivalent();

		/**
		 * The meta object literal for the '<em><b>Buses</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__BUSES = eINSTANCE.getEquivalent_Buses();

		/**
		 * The meta object literal for the '<em><b>Base KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__BASE_KV = eINSTANCE.getEquivalent_BaseKV();

		/**
		 * The meta object literal for the '<em><b>Pu</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__PU = eINSTANCE.getEquivalent_Pu();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__ANGLE = eINSTANCE.getEquivalent_Angle();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__FREQUENCY = eINSTANCE.getEquivalent_Frequency();

		/**
		 * The meta object literal for the '<em><b>Phases</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__PHASES = eINSTANCE.getEquivalent_Phases();

		/**
		 * The meta object literal for the '<em><b>R1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__R1 = eINSTANCE.getEquivalent_R1();

		/**
		 * The meta object literal for the '<em><b>X1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__X1 = eINSTANCE.getEquivalent_X1();

		/**
		 * The meta object literal for the '<em><b>R0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__R0 = eINSTANCE.getEquivalent_R0();

		/**
		 * The meta object literal for the '<em><b>X0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUIVALENT__X0 = eINSTANCE.getEquivalent_X0();

		/**
		 * The meta object literal for the '{@link dss.conversion.impl.GeneratorImpl <em>Generator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.impl.GeneratorImpl
		 * @see dss.conversion.impl.ConversionPackageImpl#getGenerator()
		 * @generated
		 */
		EClass GENERATOR = eINSTANCE.getGenerator();

		/**
		 * The meta object literal for the '<em><b>Circuit</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERATOR__CIRCUIT = eINSTANCE.getGenerator_Circuit();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__BUS1 = eINSTANCE.getGenerator_Bus1();

		/**
		 * The meta object literal for the '<em><b>KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__KV = eINSTANCE.getGenerator_KV();

		/**
		 * The meta object literal for the '<em><b>KW</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__KW = eINSTANCE.getGenerator_KW();

		/**
		 * The meta object literal for the '<em><b>Pf</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__PF = eINSTANCE.getGenerator_Pf();

		/**
		 * The meta object literal for the '<em><b>KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__KV_AR = eINSTANCE.getGenerator_KVAr();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__MODEL = eINSTANCE.getGenerator_Model();

		/**
		 * The meta object literal for the '<em><b>VMin PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VMIN_PU = eINSTANCE.getGenerator_VMinPU();

		/**
		 * The meta object literal for the '<em><b>VMax PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VMAX_PU = eINSTANCE.getGenerator_VMaxPU();

		/**
		 * The meta object literal for the '<em><b>Yearly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__YEARLY = eINSTANCE.getGenerator_Yearly();

		/**
		 * The meta object literal for the '<em><b>Daily</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DAILY = eINSTANCE.getGenerator_Daily();

		/**
		 * The meta object literal for the '<em><b>Duty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DUTY = eINSTANCE.getGenerator_Duty();

		/**
		 * The meta object literal for the '<em><b>Disp Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DISP_MODE = eINSTANCE.getGenerator_DispMode();

		/**
		 * The meta object literal for the '<em><b>Disp Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DISP_VALUE = eINSTANCE.getGenerator_DispValue();

		/**
		 * The meta object literal for the '<em><b>Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__CONN = eINSTANCE.getGenerator_Conn();

		/**
		 * The meta object literal for the '<em><b>RNeut</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__RNEUT = eINSTANCE.getGenerator_RNeut();

		/**
		 * The meta object literal for the '<em><b>XNeut</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__XNEUT = eINSTANCE.getGenerator_XNeut();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__STATUS = eINSTANCE.getGenerator_Status();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__CLASS = eINSTANCE.getGenerator_Class();

		/**
		 * The meta object literal for the '<em><b>VPU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VPU = eINSTANCE.getGenerator_VPU();

		/**
		 * The meta object literal for the '<em><b>VTarget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VTARGET = eINSTANCE.getGenerator_VTarget();

		/**
		 * The meta object literal for the '<em><b>Max KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__MAX_KV_AR = eINSTANCE.getGenerator_MaxKVAr();

		/**
		 * The meta object literal for the '<em><b>Min KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__MIN_KV_AR = eINSTANCE.getGenerator_MinKVAr();

		/**
		 * The meta object literal for the '<em><b>Pv Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__PV_FACTOR = eINSTANCE.getGenerator_PvFactor();

		/**
		 * The meta object literal for the '<em><b>Force On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__FORCE_ON = eINSTANCE.getGenerator_ForceOn();

		/**
		 * The meta object literal for the '<em><b>KVA</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__KVA = eINSTANCE.getGenerator_KVA();

		/**
		 * The meta object literal for the '<em><b>MVA</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__MVA = eINSTANCE.getGenerator_MVA();

		/**
		 * The meta object literal for the '<em><b>XD</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__XD = eINSTANCE.getGenerator_XD();

		/**
		 * The meta object literal for the '<em><b>XDp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__XDP = eINSTANCE.getGenerator_XDp();

		/**
		 * The meta object literal for the '<em><b>XDpp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__XDPP = eINSTANCE.getGenerator_XDpp();

		/**
		 * The meta object literal for the '<em><b>H</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__H = eINSTANCE.getGenerator_H();

		/**
		 * The meta object literal for the '<em><b>D</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__D = eINSTANCE.getGenerator_D();

		/**
		 * The meta object literal for the '<em><b>User Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__USER_MODEL = eINSTANCE.getGenerator_UserModel();

		/**
		 * The meta object literal for the '<em><b>User Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__USER_DATA = eINSTANCE.getGenerator_UserData();

		/**
		 * The meta object literal for the '<em><b>Shaft Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__SHAFT_MODEL = eINSTANCE.getGenerator_ShaftModel();

		/**
		 * The meta object literal for the '<em><b>Shaft Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__SHAFT_DATA = eINSTANCE.getGenerator_ShaftData();

		/**
		 * The meta object literal for the '<em><b>Debug Trace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DEBUG_TRACE = eINSTANCE.getGenerator_DebugTrace();

		/**
		 * The meta object literal for the '<em><b>Gen On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__GEN_ON = eINSTANCE.getGenerator_GenOn();

		/**
		 * The meta object literal for the '<em><b>Shape Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__SHAPE_FACTOR = eINSTANCE.getGenerator_ShapeFactor();

		/**
		 * The meta object literal for the '<em><b>Forced On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__FORCED_ON = eINSTANCE.getGenerator_ForcedOn();

		/**
		 * The meta object literal for the '<em><b>Fixed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__FIXED = eINSTANCE.getGenerator_Fixed();

		/**
		 * The meta object literal for the '<em><b>YEq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__YEQ = eINSTANCE.getGenerator_YEq();

		/**
		 * The meta object literal for the '<em><b>YEq95</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__YEQ95 = eINSTANCE.getGenerator_YEq95();

		/**
		 * The meta object literal for the '<em><b>YEq105</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__YEQ105 = eINSTANCE.getGenerator_YEq105();

		/**
		 * The meta object literal for the '<em><b>VBase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VBASE = eINSTANCE.getGenerator_VBase();

		/**
		 * The meta object literal for the '<em><b>VBase95</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VBASE95 = eINSTANCE.getGenerator_VBase95();

		/**
		 * The meta object literal for the '<em><b>VBase105</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VBASE105 = eINSTANCE.getGenerator_VBase105();

		/**
		 * The meta object literal for the '<em><b>Var Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VAR_BASE = eINSTANCE.getGenerator_VarBase();

		/**
		 * The meta object literal for the '<em><b>Var Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VAR_MIN = eINSTANCE.getGenerator_VarMin();

		/**
		 * The meta object literal for the '<em><b>Var Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__VAR_MAX = eINSTANCE.getGenerator_VarMax();

		/**
		 * The meta object literal for the '<em><b>Delta QMax</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DELTA_QMAX = eINSTANCE.getGenerator_DeltaQMax();

		/**
		 * The meta object literal for the '<em><b>DQd V</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DQD_V = eINSTANCE.getGenerator_DQdV();

		/**
		 * The meta object literal for the '<em><b>DQd VSaved</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__DQD_VSAVED = eINSTANCE.getGenerator_DQdVSaved();

		/**
		 * The meta object literal for the '<em><b>YQ Fixed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR__YQ_FIXED = eINSTANCE.getGenerator_YQFixed();

		/**
		 * The meta object literal for the '{@link dss.conversion.impl.LoadImpl <em>Load</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.impl.LoadImpl
		 * @see dss.conversion.impl.ConversionPackageImpl#getLoad()
		 * @generated
		 */
		EClass LOAD = eINSTANCE.getLoad();

		/**
		 * The meta object literal for the '<em><b>Circuit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOAD__CIRCUIT = eINSTANCE.getLoad_Circuit();

		/**
		 * The meta object literal for the '<em><b>Growth Shape Obj</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOAD__GROWTH_SHAPE_OBJ = eINSTANCE.getLoad_GrowthShapeObj();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__BUS1 = eINSTANCE.getLoad_Bus1();

		/**
		 * The meta object literal for the '<em><b>KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__KV = eINSTANCE.getLoad_KV();

		/**
		 * The meta object literal for the '<em><b>KW</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__KW = eINSTANCE.getLoad_KW();

		/**
		 * The meta object literal for the '<em><b>PF</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__PF = eINSTANCE.getLoad_PF();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__MODEL = eINSTANCE.getLoad_Model();

		/**
		 * The meta object literal for the '<em><b>Load Spec</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__LOAD_SPEC = eINSTANCE.getLoad_LoadSpec();

		/**
		 * The meta object literal for the '<em><b>Yearly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__YEARLY = eINSTANCE.getLoad_Yearly();

		/**
		 * The meta object literal for the '<em><b>Daily</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__DAILY = eINSTANCE.getLoad_Daily();

		/**
		 * The meta object literal for the '<em><b>Duty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__DUTY = eINSTANCE.getLoad_Duty();

		/**
		 * The meta object literal for the '<em><b>Growth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__GROWTH = eINSTANCE.getLoad_Growth();

		/**
		 * The meta object literal for the '<em><b>Conn</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__CONN = eINSTANCE.getLoad_Conn();

		/**
		 * The meta object literal for the '<em><b>KV Ar</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__KV_AR = eINSTANCE.getLoad_KVAr();

		/**
		 * The meta object literal for the '<em><b>RNeut</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__RNEUT = eINSTANCE.getLoad_RNeut();

		/**
		 * The meta object literal for the '<em><b>XNeut</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__XNEUT = eINSTANCE.getLoad_XNeut();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__STATUS = eINSTANCE.getLoad_Status();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__CLASS = eINSTANCE.getLoad_Class();

		/**
		 * The meta object literal for the '<em><b>VMin PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__VMIN_PU = eINSTANCE.getLoad_VMinPU();

		/**
		 * The meta object literal for the '<em><b>VMax PU</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__VMAX_PU = eINSTANCE.getLoad_VMaxPU();

		/**
		 * The meta object literal for the '<em><b>VMin Norm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__VMIN_NORM = eINSTANCE.getLoad_VMinNorm();

		/**
		 * The meta object literal for the '<em><b>VMin Emerg</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__VMIN_EMERG = eINSTANCE.getLoad_VMinEmerg();

		/**
		 * The meta object literal for the '<em><b>Xf KVA</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__XF_KVA = eINSTANCE.getLoad_XfKVA();

		/**
		 * The meta object literal for the '<em><b>Allocation Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__ALLOCATION_FACTOR = eINSTANCE.getLoad_AllocationFactor();

		/**
		 * The meta object literal for the '<em><b>KVA</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__KVA = eINSTANCE.getLoad_KVA();

		/**
		 * The meta object literal for the '<em><b>Pct Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__PCT_MEAN = eINSTANCE.getLoad_PctMean();

		/**
		 * The meta object literal for the '<em><b>Pct Std Dev</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__PCT_STD_DEV = eINSTANCE.getLoad_PctStdDev();

		/**
		 * The meta object literal for the '<em><b>Cvr Watts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__CVR_WATTS = eINSTANCE.getLoad_CvrWatts();

		/**
		 * The meta object literal for the '<em><b>Cvr Vars</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD__CVR_VARS = eINSTANCE.getLoad_CvrVars();

		/**
		 * The meta object literal for the '{@link dss.conversion.impl.PowerConversionElementImpl <em>Power Conversion Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.impl.PowerConversionElementImpl
		 * @see dss.conversion.impl.ConversionPackageImpl#getPowerConversionElement()
		 * @generated
		 */
		EClass POWER_CONVERSION_ELEMENT = eINSTANCE.getPowerConversionElement();

		/**
		 * The meta object literal for the '<em><b>Spectrum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_CONVERSION_ELEMENT__SPECTRUM = eINSTANCE.getPowerConversionElement_Spectrum();

		/**
		 * The meta object literal for the '<em><b>Spectrum Obj</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ = eINSTANCE.getPowerConversionElement_SpectrumObj();

		/**
		 * The meta object literal for the '<em><b>Inj Current</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWER_CONVERSION_ELEMENT__INJ_CURRENT = eINSTANCE.getPowerConversionElement_InjCurrent();

		/**
		 * The meta object literal for the '{@link dss.conversion.impl.VoltageSourceImpl <em>Voltage Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.impl.VoltageSourceImpl
		 * @see dss.conversion.impl.ConversionPackageImpl#getVoltageSource()
		 * @generated
		 */
		EClass VOLTAGE_SOURCE = eINSTANCE.getVoltageSource();

		/**
		 * The meta object literal for the '<em><b>Bus1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__BUS1 = eINSTANCE.getVoltageSource_Bus1();

		/**
		 * The meta object literal for the '<em><b>Base KV</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__BASE_KV = eINSTANCE.getVoltageSource_BaseKV();

		/**
		 * The meta object literal for the '<em><b>Per Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__PER_UNIT = eINSTANCE.getVoltageSource_PerUnit();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__ANGLE = eINSTANCE.getVoltageSource_Angle();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__FREQUENCY = eINSTANCE.getVoltageSource_Frequency();

		/**
		 * The meta object literal for the '<em><b>Mva SC3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__MVA_SC3 = eINSTANCE.getVoltageSource_MvaSC3();

		/**
		 * The meta object literal for the '<em><b>Mva SC1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__MVA_SC1 = eINSTANCE.getVoltageSource_MvaSC1();

		/**
		 * The meta object literal for the '<em><b>X1R1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__X1_R1 = eINSTANCE.getVoltageSource_X1R1();

		/**
		 * The meta object literal for the '<em><b>X0R0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__X0_R0 = eINSTANCE.getVoltageSource_X0R0();

		/**
		 * The meta object literal for the '<em><b>ISC3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__ISC3 = eINSTANCE.getVoltageSource_ISC3();

		/**
		 * The meta object literal for the '<em><b>ISC1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__ISC1 = eINSTANCE.getVoltageSource_ISC1();

		/**
		 * The meta object literal for the '<em><b>R1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__R1 = eINSTANCE.getVoltageSource_R1();

		/**
		 * The meta object literal for the '<em><b>X1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__X1 = eINSTANCE.getVoltageSource_X1();

		/**
		 * The meta object literal for the '<em><b>R0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__R0 = eINSTANCE.getVoltageSource_R0();

		/**
		 * The meta object literal for the '<em><b>X0</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__X0 = eINSTANCE.getVoltageSource_X0();

		/**
		 * The meta object literal for the '<em><b>Scan Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__SCAN_TYPE = eINSTANCE.getVoltageSource_ScanType();

		/**
		 * The meta object literal for the '<em><b>Z</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__Z = eINSTANCE.getVoltageSource_Z();

		/**
		 * The meta object literal for the '<em><b>ZInv</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__ZINV = eINSTANCE.getVoltageSource_ZInv();

		/**
		 * The meta object literal for the '<em><b>ZSpec Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__ZSPEC_TYPE = eINSTANCE.getVoltageSource_ZSpecType();

		/**
		 * The meta object literal for the '<em><b>VMag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VOLTAGE_SOURCE__VMAG = eINSTANCE.getVoltageSource_VMag();

		/**
		 * The meta object literal for the '{@link dss.conversion.sequenceType <em>sequence Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.sequenceType
		 * @see dss.conversion.impl.ConversionPackageImpl#getsequenceType()
		 * @generated
		 */
		EEnum SEQUENCE_TYPE = eINSTANCE.getsequenceType();

		/**
		 * The meta object literal for the '{@link dss.conversion.generatorModel <em>generator Model</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.generatorModel
		 * @see dss.conversion.impl.ConversionPackageImpl#getgeneratorModel()
		 * @generated
		 */
		EEnum GENERATOR_MODEL = eINSTANCE.getgeneratorModel();

		/**
		 * The meta object literal for the '{@link dss.conversion.dispatchType <em>dispatch Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.dispatchType
		 * @see dss.conversion.impl.ConversionPackageImpl#getdispatchType()
		 * @generated
		 */
		EEnum DISPATCH_TYPE = eINSTANCE.getdispatchType();

		/**
		 * The meta object literal for the '{@link dss.conversion.generatorStatus <em>generator Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.generatorStatus
		 * @see dss.conversion.impl.ConversionPackageImpl#getgeneratorStatus()
		 * @generated
		 */
		EEnum GENERATOR_STATUS = eINSTANCE.getgeneratorStatus();

		/**
		 * The meta object literal for the '{@link dss.conversion.loadModel <em>load Model</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.loadModel
		 * @see dss.conversion.impl.ConversionPackageImpl#getloadModel()
		 * @generated
		 */
		EEnum LOAD_MODEL = eINSTANCE.getloadModel();

		/**
		 * The meta object literal for the '{@link dss.conversion.loadStatus <em>load Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.loadStatus
		 * @see dss.conversion.impl.ConversionPackageImpl#getloadStatus()
		 * @generated
		 */
		EEnum LOAD_STATUS = eINSTANCE.getloadStatus();

		/**
		 * The meta object literal for the '{@link dss.conversion.loadSpecType <em>load Spec Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.loadSpecType
		 * @see dss.conversion.impl.ConversionPackageImpl#getloadSpecType()
		 * @generated
		 */
		EEnum LOAD_SPEC_TYPE = eINSTANCE.getloadSpecType();

		/**
		 * The meta object literal for the '{@link dss.conversion.specType <em>spec Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.conversion.specType
		 * @see dss.conversion.impl.ConversionPackageImpl#getspecType()
		 * @generated
		 */
		EEnum SPEC_TYPE = eINSTANCE.getspecType();

	}

} //ConversionPackage
