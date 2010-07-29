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
	String eNS_URI = "http://www.electrickery.com/executive";

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
	 * The meta object id for the '{@link electrickery.executive.impl.ExecutiveImpl <em>Executive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.impl.ExecutiveImpl
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getExecutive()
	 * @generated
	 */
	int EXECUTIVE = 0;

	/**
	 * The feature id for the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__COMMAND = 0;

	/**
	 * The feature id for the '<em><b>Active Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__ACTIVE_CIRCUIT = 1;

	/**
	 * The feature id for the '<em><b>Circuits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__CIRCUITS = 2;

	/**
	 * The feature id for the '<em><b>Max Circuits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__MAX_CIRCUITS = 3;

	/**
	 * The feature id for the '<em><b>Globals</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE__GLOBALS = 4;

	/**
	 * The number of structural features of the '<em>Executive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTIVE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link electrickery.executive.solutionMode <em>solution Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.solutionMode
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getsolutionMode()
	 * @generated
	 */
	int SOLUTION_MODE = 1;

	/**
	 * The meta object id for the '{@link electrickery.executive.randomType <em>random Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.randomType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getrandomType()
	 * @generated
	 */
	int RANDOM_TYPE = 2;

	/**
	 * The meta object id for the '{@link electrickery.executive.loadModelType <em>load Model Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.loadModelType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getloadModelType()
	 * @generated
	 */
	int LOAD_MODEL_TYPE = 3;

	/**
	 * The meta object id for the '{@link electrickery.executive.autoAddType <em>auto Add Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.autoAddType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getautoAddType()
	 * @generated
	 */
	int AUTO_ADD_TYPE = 4;

	/**
	 * The meta object id for the '{@link electrickery.executive.algorithmType <em>algorithm Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.algorithmType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getalgorithmType()
	 * @generated
	 */
	int ALGORITHM_TYPE = 5;

	/**
	 * The meta object id for the '{@link electrickery.executive.controlModeType <em>control Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.controlModeType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getcontrolModeType()
	 * @generated
	 */
	int CONTROL_MODE_TYPE = 6;

	/**
	 * The meta object id for the '{@link electrickery.executive.circuitModelType <em>circuit Model Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.circuitModelType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getcircuitModelType()
	 * @generated
	 */
	int CIRCUIT_MODEL_TYPE = 7;

	/**
	 * The meta object id for the '{@link electrickery.executive.reductionStrategy <em>reduction Strategy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.reductionStrategy
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getreductionStrategy()
	 * @generated
	 */
	int REDUCTION_STRATEGY = 8;

	/**
	 * The meta object id for the '{@link electrickery.executive.resetType <em>reset Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.resetType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getresetType()
	 * @generated
	 */
	int RESET_TYPE = 9;

	/**
	 * The meta object id for the '{@link electrickery.executive.nextType <em>next Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.nextType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getnextType()
	 * @generated
	 */
	int NEXT_TYPE = 10;

	/**
	 * The meta object id for the '{@link electrickery.executive.exportType <em>export Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.exportType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getexportType()
	 * @generated
	 */
	int EXPORT_TYPE = 11;

	/**
	 * The meta object id for the '{@link electrickery.executive.reduceType <em>reduce Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.reduceType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getreduceType()
	 * @generated
	 */
	int REDUCE_TYPE = 12;

	/**
	 * The meta object id for the '{@link electrickery.executive.distributionType <em>distribution Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.executive.distributionType
	 * @see electrickery.executive.impl.ExecutivePackageImpl#getdistributionType()
	 * @generated
	 */
	int DISTRIBUTION_TYPE = 13;


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
	 * Returns the meta object for the containment reference list '{@link electrickery.executive.Executive#getCircuits <em>Circuits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Circuits</em>'.
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
	 * Returns the meta object for the reference '{@link electrickery.executive.Executive#getGlobals <em>Globals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Globals</em>'.
	 * @see electrickery.executive.Executive#getGlobals()
	 * @see #getExecutive()
	 * @generated
	 */
	EReference getExecutive_Globals();

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
		 * The meta object literal for the '<em><b>Active Circuit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTIVE__ACTIVE_CIRCUIT = eINSTANCE.getExecutive_ActiveCircuit();

		/**
		 * The meta object literal for the '<em><b>Circuits</b></em>' containment reference list feature.
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
		 * The meta object literal for the '<em><b>Globals</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTIVE__GLOBALS = eINSTANCE.getExecutive_Globals();

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
