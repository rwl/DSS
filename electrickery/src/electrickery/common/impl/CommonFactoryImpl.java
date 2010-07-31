/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import electrickery.common.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CommonFactory;
import electrickery.common.CommonPackage;
import electrickery.common.Solution;
import electrickery.common.Terminal;
import electrickery.common.algorithmType;
import electrickery.common.connectionType;
import electrickery.common.controlModeType;
import electrickery.common.lengthUnit;
import electrickery.common.tripAction;
import electrickery.common.yBuildOption;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommonFactoryImpl extends EFactoryImpl implements CommonFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CommonFactory init() {
		try {
			CommonFactory theCommonFactory = (CommonFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.electrickery.com/common"); 
			if (theCommonFactory != null) {
				return theCommonFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CommonFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommonFactoryImpl() {
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
			case CommonPackage.CIRCUIT: return createCircuit();
			case CommonPackage.YMATRIX: return createYMatrix();
			case CommonPackage.BUS: return createBus();
			case CommonPackage.SOLUTION: return createSolution();
			case CommonPackage.SOLUTION_ALGS: return createSolutionAlgs();
			case CommonPackage.TERMINAL: return createTerminal();
			case CommonPackage.GLOBALS: return createGlobals();
			case CommonPackage.PARSER: return createParser();
			case CommonPackage.SOLVER: return createSolver();
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
			case CommonPackage.CONNECTION_TYPE:
				return createconnectionTypeFromString(eDataType, initialValue);
			case CommonPackage.LENGTH_UNIT:
				return createlengthUnitFromString(eDataType, initialValue);
			case CommonPackage.TRIP_ACTION:
				return createtripActionFromString(eDataType, initialValue);
			case CommonPackage.YBUILD_OPTION:
				return createyBuildOptionFromString(eDataType, initialValue);
			case CommonPackage.CONTROL_MODE_TYPE:
				return createcontrolModeTypeFromString(eDataType, initialValue);
			case CommonPackage.ALGORITHM_TYPE:
				return createalgorithmTypeFromString(eDataType, initialValue);
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
			case CommonPackage.CONNECTION_TYPE:
				return convertconnectionTypeToString(eDataType, instanceValue);
			case CommonPackage.LENGTH_UNIT:
				return convertlengthUnitToString(eDataType, instanceValue);
			case CommonPackage.TRIP_ACTION:
				return converttripActionToString(eDataType, instanceValue);
			case CommonPackage.YBUILD_OPTION:
				return convertyBuildOptionToString(eDataType, instanceValue);
			case CommonPackage.CONTROL_MODE_TYPE:
				return convertcontrolModeTypeToString(eDataType, instanceValue);
			case CommonPackage.ALGORITHM_TYPE:
				return convertalgorithmTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Circuit createCircuit() {
		CircuitImpl circuit = new CircuitImpl();
		return circuit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public YMatrix createYMatrix() {
		YMatrixImpl yMatrix = new YMatrixImpl();
		return yMatrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bus createBus() {
		BusImpl bus = new BusImpl();
		return bus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Solution createSolution() {
		SolutionImpl solution = new SolutionImpl();
		return solution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SolutionAlgs createSolutionAlgs() {
		SolutionAlgsImpl solutionAlgs = new SolutionAlgsImpl();
		return solutionAlgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Terminal createTerminal() {
		TerminalImpl terminal = new TerminalImpl();
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Globals createGlobals() {
		GlobalsImpl globals = new GlobalsImpl();
		return globals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parser createParser() {
		ParserImpl parser = new ParserImpl();
		return parser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Solver createSolver() {
		SolverImpl solver = new SolverImpl();
		return solver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public connectionType createconnectionTypeFromString(EDataType eDataType, String initialValue) {
		connectionType result = connectionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertconnectionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public lengthUnit createlengthUnitFromString(EDataType eDataType, String initialValue) {
		lengthUnit result = lengthUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertlengthUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tripAction createtripActionFromString(EDataType eDataType, String initialValue) {
		tripAction result = tripAction.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String converttripActionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public yBuildOption createyBuildOptionFromString(EDataType eDataType, String initialValue) {
		yBuildOption result = yBuildOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertyBuildOptionToString(EDataType eDataType, Object instanceValue) {
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
	public CommonPackage getCommonPackage() {
		return (CommonPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CommonPackage getPackage() {
		return CommonPackage.eINSTANCE;
	}

} //CommonFactoryImpl
