/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import electrickery.ElectrickeryPackage;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.impl.ConversionPackageImpl;
import electrickery.executive.Executive;
import electrickery.executive.ExecutiveFactory;
import electrickery.executive.ExecutivePackage;
import electrickery.executive.algorithmType;
import electrickery.executive.autoAddType;
import electrickery.executive.circuitModelType;
import electrickery.executive.controlModeType;
import electrickery.executive.distributionType;
import electrickery.executive.exportType;
import electrickery.executive.loadModelType;
import electrickery.executive.nextType;
import electrickery.executive.randomType;
import electrickery.executive.reduceType;
import electrickery.executive.reductionStrategy;
import electrickery.executive.resetType;
import electrickery.executive.solutionMode;
import electrickery.impl.ElectrickeryPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExecutivePackageImpl extends EPackageImpl implements ExecutivePackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass executiveEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum solutionModeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum randomTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum loadModelTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum autoAddTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum algorithmTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum controlModeTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum circuitModelTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum reductionStrategyEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum resetTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum nextTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum exportTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum reduceTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum distributionTypeEEnum = null;

    /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see electrickery.executive.ExecutivePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private ExecutivePackageImpl() {
		super(eNS_URI, ExecutiveFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExecutivePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static ExecutivePackage init() {
		if (isInited) return (ExecutivePackage)EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI);

		// Obtain or create and register package
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExecutivePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ElectrickeryPackageImpl theElectrickeryPackage = (ElectrickeryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) instanceof ElectrickeryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) : ElectrickeryPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);

		// Create package meta-data objects
		theExecutivePackage.createPackageContents();
		theElectrickeryPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theConversionPackage.createPackageContents();

		// Initialize created meta-data
		theExecutivePackage.initializePackageContents();
		theElectrickeryPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExecutivePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExecutivePackage.eNS_URI, theExecutivePackage);
		return theExecutivePackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getExecutive() {
		return executiveEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getExecutive_Command() {
		return (EAttribute)executiveEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getExecutive_ActiveCircuit() {
		return (EReference)executiveEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getExecutive_Circuits() {
		return (EReference)executiveEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getExecutive_MaxCircuits() {
		return (EAttribute)executiveEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getsolutionMode() {
		return solutionModeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getrandomType() {
		return randomTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getloadModelType() {
		return loadModelTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getautoAddType() {
		return autoAddTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getalgorithmType() {
		return algorithmTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getcontrolModeType() {
		return controlModeTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getcircuitModelType() {
		return circuitModelTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getreductionStrategy() {
		return reductionStrategyEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getresetType() {
		return resetTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getnextType() {
		return nextTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getexportType() {
		return exportTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getreduceType() {
		return reduceTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getdistributionType() {
		return distributionTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExecutiveFactory getExecutiveFactory() {
		return (ExecutiveFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		executiveEClass = createEClass(EXECUTIVE);
		createEAttribute(executiveEClass, EXECUTIVE__COMMAND);
		createEReference(executiveEClass, EXECUTIVE__ACTIVE_CIRCUIT);
		createEReference(executiveEClass, EXECUTIVE__CIRCUITS);
		createEAttribute(executiveEClass, EXECUTIVE__MAX_CIRCUITS);

		// Create enums
		solutionModeEEnum = createEEnum(SOLUTION_MODE);
		randomTypeEEnum = createEEnum(RANDOM_TYPE);
		loadModelTypeEEnum = createEEnum(LOAD_MODEL_TYPE);
		autoAddTypeEEnum = createEEnum(AUTO_ADD_TYPE);
		algorithmTypeEEnum = createEEnum(ALGORITHM_TYPE);
		controlModeTypeEEnum = createEEnum(CONTROL_MODE_TYPE);
		circuitModelTypeEEnum = createEEnum(CIRCUIT_MODEL_TYPE);
		reductionStrategyEEnum = createEEnum(REDUCTION_STRATEGY);
		resetTypeEEnum = createEEnum(RESET_TYPE);
		nextTypeEEnum = createEEnum(NEXT_TYPE);
		exportTypeEEnum = createEEnum(EXPORT_TYPE);
		reduceTypeEEnum = createEEnum(REDUCE_TYPE);
		distributionTypeEEnum = createEEnum(DISTRIBUTION_TYPE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(executiveEClass, Executive.class, "Executive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutive_Command(), ecorePackage.getEString(), "command", null, 0, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutive_ActiveCircuit(), theCommonPackage.getCircuit(), null, "activeCircuit", null, 0, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutive_Circuits(), theCommonPackage.getCircuit(), null, "circuits", null, 0, -1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutive_MaxCircuits(), ecorePackage.getEInt(), "maxCircuits", "1", 0, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(executiveEClass, null, "processCommand", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "cmdLine", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executiveEClass, ecorePackage.getEInt(), "doSetCommand", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "solveOption", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(executiveEClass, ecorePackage.getEInt(), "doSolveCmd", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(executiveEClass, null, "makeNewCircuit", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(solutionModeEEnum, solutionMode.class, "solutionMode");
		addEEnumLiteral(solutionModeEEnum, solutionMode.SNAPSHOT);
		addEEnumLiteral(solutionModeEEnum, solutionMode.DAILY);
		addEEnumLiteral(solutionModeEEnum, solutionMode.DIRECT);
		addEEnumLiteral(solutionModeEEnum, solutionMode.DUTYCYCLE);
		addEEnumLiteral(solutionModeEEnum, solutionMode.DYNAMIC);
		addEEnumLiteral(solutionModeEEnum, solutionMode.HARMONIC);
		addEEnumLiteral(solutionModeEEnum, solutionMode.MONTE_CARLO1);
		addEEnumLiteral(solutionModeEEnum, solutionMode.MONTE_CARLO2);
		addEEnumLiteral(solutionModeEEnum, solutionMode.MONTE_CARLO3);
		addEEnumLiteral(solutionModeEEnum, solutionMode.FAULT_STUDY);
		addEEnumLiteral(solutionModeEEnum, solutionMode.YEARLY);
		addEEnumLiteral(solutionModeEEnum, solutionMode.MONTE_FAULT);
		addEEnumLiteral(solutionModeEEnum, solutionMode.PEAKDAY);
		addEEnumLiteral(solutionModeEEnum, solutionMode.LOAD_DURATION1);
		addEEnumLiteral(solutionModeEEnum, solutionMode.LOAD_DURATION2);
		addEEnumLiteral(solutionModeEEnum, solutionMode.AUTO_ADD);

		initEEnum(randomTypeEEnum, randomType.class, "randomType");
		addEEnumLiteral(randomTypeEEnum, randomType.UNIFORM);
		addEEnumLiteral(randomTypeEEnum, randomType.GAUSSIAN);
		addEEnumLiteral(randomTypeEEnum, randomType.LOGNORMAL);
		addEEnumLiteral(randomTypeEEnum, randomType.NONE);

		initEEnum(loadModelTypeEEnum, loadModelType.class, "loadModelType");
		addEEnumLiteral(loadModelTypeEEnum, loadModelType.POWERFLOW);
		addEEnumLiteral(loadModelTypeEEnum, loadModelType.ADMITTANCE);

		initEEnum(autoAddTypeEEnum, autoAddType.class, "autoAddType");
		addEEnumLiteral(autoAddTypeEEnum, autoAddType.GENERATOR);
		addEEnumLiteral(autoAddTypeEEnum, autoAddType.CAPACITOR);

		initEEnum(algorithmTypeEEnum, algorithmType.class, "algorithmType");
		addEEnumLiteral(algorithmTypeEEnum, algorithmType.NORMAL);
		addEEnumLiteral(algorithmTypeEEnum, algorithmType.NEWTON);

		initEEnum(controlModeTypeEEnum, controlModeType.class, "controlModeType");
		addEEnumLiteral(controlModeTypeEEnum, controlModeType.STATIC);
		addEEnumLiteral(controlModeTypeEEnum, controlModeType.EVENT);
		addEEnumLiteral(controlModeTypeEEnum, controlModeType.TIME);

		initEEnum(circuitModelTypeEEnum, circuitModelType.class, "circuitModelType");
		addEEnumLiteral(circuitModelTypeEEnum, circuitModelType.MULTIPHASE);
		addEEnumLiteral(circuitModelTypeEEnum, circuitModelType.POSITIVE);

		initEEnum(reductionStrategyEEnum, reductionStrategy.class, "reductionStrategy");
		addEEnumLiteral(reductionStrategyEEnum, reductionStrategy.STUBS);
		addEEnumLiteral(reductionStrategyEEnum, reductionStrategy.MERGE_PARALLEL);
		addEEnumLiteral(reductionStrategyEEnum, reductionStrategy.BREAKLOOPS);
		addEEnumLiteral(reductionStrategyEEnum, reductionStrategy.TAPENDS);
		addEEnumLiteral(reductionStrategyEEnum, reductionStrategy.ENDS);
		addEEnumLiteral(reductionStrategyEEnum, reductionStrategy.SWITCHES);

		initEEnum(resetTypeEEnum, resetType.class, "resetType");
		addEEnumLiteral(resetTypeEEnum, resetType.MO);
		addEEnumLiteral(resetTypeEEnum, resetType.ME);
		addEEnumLiteral(resetTypeEEnum, resetType.FAULTS);
		addEEnumLiteral(resetTypeEEnum, resetType.CONTROLS);
		addEEnumLiteral(resetTypeEEnum, resetType.EVENT_LOG);
		addEEnumLiteral(resetTypeEEnum, resetType.KEEP_LIST);
		addEEnumLiteral(resetTypeEEnum, resetType.ALL);

		initEEnum(nextTypeEEnum, nextType.class, "nextType");
		addEEnumLiteral(nextTypeEEnum, nextType.YEAR);
		addEEnumLiteral(nextTypeEEnum, nextType.HOUR);
		addEEnumLiteral(nextTypeEEnum, nextType.TIME);

		initEEnum(exportTypeEEnum, exportType.class, "exportType");
		addEEnumLiteral(exportTypeEEnum, exportType.VOLTAGES);
		addEEnumLiteral(exportTypeEEnum, exportType.SEQ_VOLTAGES);
		addEEnumLiteral(exportTypeEEnum, exportType.CURRENTS);
		addEEnumLiteral(exportTypeEEnum, exportType.OVERLOADS);
		addEEnumLiteral(exportTypeEEnum, exportType.UNSERVED);
		addEEnumLiteral(exportTypeEEnum, exportType.SEQ_CURRENTS);
		addEEnumLiteral(exportTypeEEnum, exportType.POWERS);
		addEEnumLiteral(exportTypeEEnum, exportType.FAULT_STUDY);
		addEEnumLiteral(exportTypeEEnum, exportType.GENERATORS);
		addEEnumLiteral(exportTypeEEnum, exportType.LOADS);
		addEEnumLiteral(exportTypeEEnum, exportType.METERS);
		addEEnumLiteral(exportTypeEEnum, exportType.MONITORS);
		addEEnumLiteral(exportTypeEEnum, exportType.YPRIMS);
		addEEnumLiteral(exportTypeEEnum, exportType.Y);

		initEEnum(reduceTypeEEnum, reduceType.class, "reduceType");
		addEEnumLiteral(reduceTypeEEnum, reduceType.ALL);
		addEEnumLiteral(reduceTypeEEnum, reduceType.METER_NAME);

		initEEnum(distributionTypeEEnum, distributionType.class, "distributionType");
		addEEnumLiteral(distributionTypeEEnum, distributionType.PROPORTIONAL);
		addEEnumLiteral(distributionTypeEEnum, distributionType.UNIFORM);
		addEEnumLiteral(distributionTypeEEnum, distributionType.RANDOM);
		addEEnumLiteral(distributionTypeEEnum, distributionType.SKIP);
	}

} //ExecutivePackageImpl
