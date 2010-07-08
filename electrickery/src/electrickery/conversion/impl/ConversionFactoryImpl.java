/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;

import electrickery.conversion.*;

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
public class ConversionFactoryImpl extends EFactoryImpl implements ConversionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConversionFactory init() {
		try {
			ConversionFactory theConversionFactory = (ConversionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openpowersystem.com/dss/conversion"); 
			if (theConversionFactory != null) {
				return theConversionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConversionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConversionFactoryImpl() {
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
			case ConversionPackage.CURRENT_SOURCE: return createCurrentSource();
			case ConversionPackage.EQUIVALENT: return createEquivalent();
			case ConversionPackage.GENERATOR: return createGenerator();
			case ConversionPackage.LOAD: return createLoad();
			case ConversionPackage.VOLTAGE_SOURCE: return createVoltageSource();
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
			case ConversionPackage.SEQUENCE_TYPE:
				return createsequenceTypeFromString(eDataType, initialValue);
			case ConversionPackage.GENERATOR_MODEL:
				return creategeneratorModelFromString(eDataType, initialValue);
			case ConversionPackage.DISPATCH_TYPE:
				return createdispatchTypeFromString(eDataType, initialValue);
			case ConversionPackage.GENERATOR_STATUS:
				return creategeneratorStatusFromString(eDataType, initialValue);
			case ConversionPackage.LOAD_MODEL:
				return createloadModelFromString(eDataType, initialValue);
			case ConversionPackage.LOAD_STATUS:
				return createloadStatusFromString(eDataType, initialValue);
			case ConversionPackage.LOAD_SPEC_TYPE:
				return createloadSpecTypeFromString(eDataType, initialValue);
			case ConversionPackage.SPEC_TYPE:
				return createspecTypeFromString(eDataType, initialValue);
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
			case ConversionPackage.SEQUENCE_TYPE:
				return convertsequenceTypeToString(eDataType, instanceValue);
			case ConversionPackage.GENERATOR_MODEL:
				return convertgeneratorModelToString(eDataType, instanceValue);
			case ConversionPackage.DISPATCH_TYPE:
				return convertdispatchTypeToString(eDataType, instanceValue);
			case ConversionPackage.GENERATOR_STATUS:
				return convertgeneratorStatusToString(eDataType, instanceValue);
			case ConversionPackage.LOAD_MODEL:
				return convertloadModelToString(eDataType, instanceValue);
			case ConversionPackage.LOAD_STATUS:
				return convertloadStatusToString(eDataType, instanceValue);
			case ConversionPackage.LOAD_SPEC_TYPE:
				return convertloadSpecTypeToString(eDataType, instanceValue);
			case ConversionPackage.SPEC_TYPE:
				return convertspecTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CurrentSource createCurrentSource() {
		CurrentSourceImpl currentSource = new CurrentSourceImpl();
		return currentSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Equivalent createEquivalent() {
		EquivalentImpl equivalent = new EquivalentImpl();
		return equivalent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Generator createGenerator() {
		GeneratorImpl generator = new GeneratorImpl();
		return generator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Load createLoad() {
		LoadImpl load = new LoadImpl();
		return load;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VoltageSource createVoltageSource() {
		VoltageSourceImpl voltageSource = new VoltageSourceImpl();
		return voltageSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public sequenceType createsequenceTypeFromString(EDataType eDataType, String initialValue) {
		sequenceType result = sequenceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertsequenceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public generatorModel creategeneratorModelFromString(EDataType eDataType, String initialValue) {
		generatorModel result = generatorModel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertgeneratorModelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public dispatchType createdispatchTypeFromString(EDataType eDataType, String initialValue) {
		dispatchType result = dispatchType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertdispatchTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public generatorStatus creategeneratorStatusFromString(EDataType eDataType, String initialValue) {
		generatorStatus result = generatorStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertgeneratorStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public loadModel createloadModelFromString(EDataType eDataType, String initialValue) {
		loadModel result = loadModel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertloadModelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public loadStatus createloadStatusFromString(EDataType eDataType, String initialValue) {
		loadStatus result = loadStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertloadStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public loadSpecType createloadSpecTypeFromString(EDataType eDataType, String initialValue) {
		loadSpecType result = loadSpecType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertloadSpecTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public specType createspecTypeFromString(EDataType eDataType, String initialValue) {
		specType result = specType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertspecTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConversionPackage getConversionPackage() {
		return (ConversionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ConversionPackage getPackage() {
		return ConversionPackage.eINSTANCE;
	}

} //ConversionFactoryImpl
