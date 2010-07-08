/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive.impl;

import electrickery.executive.*;

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
public class ExecutiveFactoryImpl extends EFactoryImpl implements ExecutiveFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExecutiveFactory init() {
		try {
			ExecutiveFactory theExecutiveFactory = (ExecutiveFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openpowersystem.com/dss/executive"); 
			if (theExecutiveFactory != null) {
				return theExecutiveFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExecutiveFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutiveFactoryImpl() {
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
			case ExecutivePackage.EXEC_OPTIONS: return createExecOptions();
			case ExecutivePackage.EXEC_COMMANDS: return createExecCommands();
			case ExecutivePackage.EXECUTIVE: return createExecutive();
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
			case ExecutivePackage.SOLUTION_MODE:
				return createsolutionModeFromString(eDataType, initialValue);
			case ExecutivePackage.RANDOM_TYPE:
				return createrandomTypeFromString(eDataType, initialValue);
			case ExecutivePackage.LOAD_MODEL_TYPE:
				return createloadModelTypeFromString(eDataType, initialValue);
			case ExecutivePackage.AUTO_ADD_TYPE:
				return createautoAddTypeFromString(eDataType, initialValue);
			case ExecutivePackage.ALGORITHM_TYPE:
				return createalgorithmTypeFromString(eDataType, initialValue);
			case ExecutivePackage.CONTROL_MODE_TYPE:
				return createcontrolModeTypeFromString(eDataType, initialValue);
			case ExecutivePackage.CIRCUIT_MODEL_TYPE:
				return createcircuitModelTypeFromString(eDataType, initialValue);
			case ExecutivePackage.REDUCTION_STRATEGY:
				return createreductionStrategyFromString(eDataType, initialValue);
			case ExecutivePackage.RESET_TYPE:
				return createresetTypeFromString(eDataType, initialValue);
			case ExecutivePackage.NEXT_TYPE:
				return createnextTypeFromString(eDataType, initialValue);
			case ExecutivePackage.EXPORT_TYPE:
				return createexportTypeFromString(eDataType, initialValue);
			case ExecutivePackage.REDUCE_TYPE:
				return createreduceTypeFromString(eDataType, initialValue);
			case ExecutivePackage.DISTRIBUTION_TYPE:
				return createdistributionTypeFromString(eDataType, initialValue);
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
			case ExecutivePackage.SOLUTION_MODE:
				return convertsolutionModeToString(eDataType, instanceValue);
			case ExecutivePackage.RANDOM_TYPE:
				return convertrandomTypeToString(eDataType, instanceValue);
			case ExecutivePackage.LOAD_MODEL_TYPE:
				return convertloadModelTypeToString(eDataType, instanceValue);
			case ExecutivePackage.AUTO_ADD_TYPE:
				return convertautoAddTypeToString(eDataType, instanceValue);
			case ExecutivePackage.ALGORITHM_TYPE:
				return convertalgorithmTypeToString(eDataType, instanceValue);
			case ExecutivePackage.CONTROL_MODE_TYPE:
				return convertcontrolModeTypeToString(eDataType, instanceValue);
			case ExecutivePackage.CIRCUIT_MODEL_TYPE:
				return convertcircuitModelTypeToString(eDataType, instanceValue);
			case ExecutivePackage.REDUCTION_STRATEGY:
				return convertreductionStrategyToString(eDataType, instanceValue);
			case ExecutivePackage.RESET_TYPE:
				return convertresetTypeToString(eDataType, instanceValue);
			case ExecutivePackage.NEXT_TYPE:
				return convertnextTypeToString(eDataType, instanceValue);
			case ExecutivePackage.EXPORT_TYPE:
				return convertexportTypeToString(eDataType, instanceValue);
			case ExecutivePackage.REDUCE_TYPE:
				return convertreduceTypeToString(eDataType, instanceValue);
			case ExecutivePackage.DISTRIBUTION_TYPE:
				return convertdistributionTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecOptions createExecOptions() {
		ExecOptionsImpl execOptions = new ExecOptionsImpl();
		return execOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecCommands createExecCommands() {
		ExecCommandsImpl execCommands = new ExecCommandsImpl();
		return execCommands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executive createExecutive() {
		ExecutiveImpl executive = new ExecutiveImpl();
		return executive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public solutionMode createsolutionModeFromString(EDataType eDataType, String initialValue) {
		solutionMode result = solutionMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertsolutionModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public randomType createrandomTypeFromString(EDataType eDataType, String initialValue) {
		randomType result = randomType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertrandomTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public loadModelType createloadModelTypeFromString(EDataType eDataType, String initialValue) {
		loadModelType result = loadModelType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertloadModelTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public autoAddType createautoAddTypeFromString(EDataType eDataType, String initialValue) {
		autoAddType result = autoAddType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertautoAddTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public algorithmType createalgorithmTypeFromString(EDataType eDataType, String initialValue) {
		algorithmType result = algorithmType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertalgorithmTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public controlModeType createcontrolModeTypeFromString(EDataType eDataType, String initialValue) {
		controlModeType result = controlModeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertcontrolModeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public circuitModelType createcircuitModelTypeFromString(EDataType eDataType, String initialValue) {
		circuitModelType result = circuitModelType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertcircuitModelTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public reductionStrategy createreductionStrategyFromString(EDataType eDataType, String initialValue) {
		reductionStrategy result = reductionStrategy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertreductionStrategyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public resetType createresetTypeFromString(EDataType eDataType, String initialValue) {
		resetType result = resetType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertresetTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public nextType createnextTypeFromString(EDataType eDataType, String initialValue) {
		nextType result = nextType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertnextTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public exportType createexportTypeFromString(EDataType eDataType, String initialValue) {
		exportType result = exportType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertexportTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public reduceType createreduceTypeFromString(EDataType eDataType, String initialValue) {
		reduceType result = reduceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertreduceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public distributionType createdistributionTypeFromString(EDataType eDataType, String initialValue) {
		distributionType result = distributionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertdistributionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutivePackage getExecutivePackage() {
		return (ExecutivePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExecutivePackage getPackage() {
		return ExecutivePackage.eINSTANCE;
	}

} //ExecutiveFactoryImpl
