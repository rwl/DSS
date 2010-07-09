/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.impl;

import electrickery.control.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlFactoryImpl extends EFactoryImpl implements ControlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ControlFactory init() {
		try {
			ControlFactory theControlFactory = (ControlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.electrickery.com/control"); 
			if (theControlFactory != null) {
				return theControlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ControlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ControlPackage.CAPACITOR_CONTROL: return createCapacitorControl();
			case ControlPackage.GENERATOR_DISPATCHER: return createGeneratorDispatcher();
			case ControlPackage.RECLOSER: return createRecloser();
			case ControlPackage.REGULATOR_CONTROL: return createRegulatorControl();
			case ControlPackage.RELAY: return createRelay();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ControlPackage.CONTROL_TYPE:
				return createcontrolTypeFromString(eDataType, initialValue);
			case ControlPackage.RELAY_TYPE:
				return createrelayTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ControlPackage.CONTROL_TYPE:
				return convertcontrolTypeToString(eDataType, instanceValue);
			case ControlPackage.RELAY_TYPE:
				return convertrelayTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapacitorControl createCapacitorControl() {
		CapacitorControlImpl capacitorControl = new CapacitorControlImpl();
		return capacitorControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratorDispatcher createGeneratorDispatcher() {
		GeneratorDispatcherImpl generatorDispatcher = new GeneratorDispatcherImpl();
		return generatorDispatcher;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Recloser createRecloser() {
		RecloserImpl recloser = new RecloserImpl();
		return recloser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegulatorControl createRegulatorControl() {
		RegulatorControlImpl regulatorControl = new RegulatorControlImpl();
		return regulatorControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relay createRelay() {
		RelayImpl relay = new RelayImpl();
		return relay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public controlType createcontrolTypeFromString(EDataType eDataType, String initialValue) {
		controlType result = controlType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertcontrolTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public relayType createrelayTypeFromString(EDataType eDataType, String initialValue) {
		relayType result = relayType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertrelayTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlPackage getControlPackage() {
		return (ControlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ControlPackage getPackage() {
		return ControlPackage.eINSTANCE;
	}

} //ControlFactoryImpl
