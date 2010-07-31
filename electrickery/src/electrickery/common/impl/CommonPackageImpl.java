/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import electrickery.ElectrickeryPackage;
import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CircuitElement;
import electrickery.common.CommonFactory;
import electrickery.common.CommonPackage;
import electrickery.common.Conductor;
import electrickery.common.Globals;
import electrickery.common.Named;
import electrickery.common.Parser;
import electrickery.common.Solution;
import electrickery.common.SolutionAlgs;
import electrickery.common.Solver;
import electrickery.common.Terminal;
import electrickery.common.YMatrix;
import electrickery.common.algorithmType;
import electrickery.common.connectionType;
import electrickery.common.controlModeType;
import electrickery.common.lengthUnit;
import electrickery.common.tripAction;
import electrickery.common.yBuildOption;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.impl.ConversionPackageImpl;
import electrickery.executive.ExecutivePackage;
import electrickery.executive.impl.ExecutivePackageImpl;
import electrickery.impl.ElectrickeryPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommonPackageImpl extends EPackageImpl implements CommonPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass circuitEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass yMatrixEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass busEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass circuitElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass conductorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass solutionEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass solutionAlgsEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass terminalEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass globalsEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parserEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass solverEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum connectionTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum lengthUnitEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum tripActionEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum yBuildOptionEEnum = null;

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
    private EEnum algorithmTypeEEnum = null;

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
	 * @see electrickery.common.CommonPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private CommonPackageImpl() {
		super(eNS_URI, CommonFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CommonPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static CommonPackage init() {
		if (isInited) return (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Obtain or create and register package
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CommonPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ElectrickeryPackageImpl theElectrickeryPackage = (ElectrickeryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) instanceof ElectrickeryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) : ElectrickeryPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);

		// Create package meta-data objects
		theCommonPackage.createPackageContents();
		theElectrickeryPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theExecutivePackage.createPackageContents();

		// Initialize created meta-data
		theCommonPackage.initializePackageContents();
		theElectrickeryPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCommonPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CommonPackage.eNS_URI, theCommonPackage);
		return theCommonPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCircuit() {
		return circuitEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCircuit_Executive() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Solution() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCircuit_Buses() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_MapNodeToBus() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_BusList() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(4);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_BusNameRedefined() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(14);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_Solved() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(15);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_LoadMultiplier() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(16);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_DefaultGrowthFactor() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(17);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_DefaultGrowthRate() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(18);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_GeneratorDispatchReference() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(19);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_GenMultiplier() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(20);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_DefaultHourMult() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(21);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_Control_busNameRedefined() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(22);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_PriceSignal() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(23);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_EmergMinVolts() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(24);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_EmergMaxVolts() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(25);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_NormalMinVolts() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(26);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_NormalMaxVolts() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(27);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_LogEvents() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(28);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_MeterZonesComputed() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(29);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_ZonesLocked() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(30);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getYMatrix() {
		return yMatrixEClass;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getYMatrix_Circuit() {
		return (EReference)yMatrixEClass.getEStructuralFeatures().get(0);
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_NumNodes() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(11);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_NumBuses() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(12);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuit_Fundamental() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(13);
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_Name() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(10);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_VoltageSources() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(5);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCircuit_CurrentSources() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(6);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCircuit_Generators() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(7);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCircuit_Loads() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(8);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCircuit_ActiveCircuitElement() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(9);
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getBus() {
		return busEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBus_Circuit() {
		return (EReference)busEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getBus_VBus() {
		return (EReference)busEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getBus_BusCurrent() {
		return (EReference)busEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_NumNodesThisBus() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_KVBase() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_X() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_Y() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_CoordDefined() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_Keep() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(8);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_BusRef() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(9);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBus_NodeNum() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(10);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCircuitElement() {
		return circuitElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_Name() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_Enabled() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuitElement_BaseFreq() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_YOrder() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_YPrimInvalid() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(4);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_LastTerminalChecked() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_Terminals() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_ActiveTerminal() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuitElement_ActiveTerminalIndex() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(8);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_NTerms() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_NConds() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_NPhases() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_YPrimSeries() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_YPrimShunt() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_YPrim() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_YPrimFreq() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCircuitElement_NodeRef() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(16);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getConductor() {
		return conductorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConductor_Closed() {
		return (EAttribute)conductorEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConductor_FuseBlown() {
		return (EAttribute)conductorEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConductor_AccumISqT() {
		return (EAttribute)conductorEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConductor_TccName() {
		return (EAttribute)conductorEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSolution() {
		return solutionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_Y() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_YSystem() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_YSeries() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Year() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(3);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_PreserveNodeVoltages() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_FrequencyChanged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(5);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Frequency() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(6);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSolution_Harmonic() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(7);
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Mode() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(8);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSolution_Circuit() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(9);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SolutionInitialised() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(10);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SeriesYInvalid() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(11);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SystemYChanged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(12);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_LoadModel() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(13);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_VoltageBaseChanged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(14);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_HarmonicModel() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(15);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_DynamicModel() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(16);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_UseAuxillaryCurrents() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(17);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_LoadsNeedUpdating() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(18);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Iteration() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(19);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MaxIterations() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(20);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MaxError() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(21);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ConvergenceTolerance() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(22);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Converged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(23);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ControlIteration() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(24);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MaxControlIterations() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(25);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ControlMode() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(26);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ControlActionsDone() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(27);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MostIterationsDone() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(28);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Algorithm() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(29);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_LastSolutionWasDirect() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(30);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSolution_SolutionCount() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(31);
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSolution_NodeV() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(32);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_NodeVBase() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(33);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_ErrorSaved() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(34);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_VMagSaved() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(35);
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSolution_Currents() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(36);
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSolution_Algorithms() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(37);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSolutionAlgs() {
		return solutionAlgsEClass;
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getTerminal() {
		return terminalEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTerminal_BusRef() {
		return (EAttribute)terminalEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTerminal_TermNodeRef() {
		return (EAttribute)terminalEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getTerminal_Conductors() {
		return (EReference)terminalEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTerminal_Checked() {
		return (EAttribute)terminalEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTerminal_NCond() {
		return (EAttribute)terminalEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTerminal_ActiveConductor() {
		return (EAttribute)terminalEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamed() {
		return namedEClass;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamed_Name() {
		return (EAttribute)namedEClass.getEStructuralFeatures().get(0);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlobals() {
		return globalsEClass;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobals_SolutionAbort() {
		return (EAttribute)globalsEClass.getEStructuralFeatures().get(0);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGlobals_Executives() {
		return (EReference)globalsEClass.getEStructuralFeatures().get(1);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlobals_SolutionWasAttempted() {
		return (EAttribute)globalsEClass.getEStructuralFeatures().get(2);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParser() {
		return parserEClass;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParser_Token() {
		return (EAttribute)parserEClass.getEStructuralFeatures().get(0);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSolver() {
		return solverEClass;
	}

																/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getconnectionType() {
		return connectionTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getlengthUnit() {
		return lengthUnitEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum gettripAction() {
		return tripActionEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getyBuildOption() {
		return yBuildOptionEEnum;
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
    public EEnum getalgorithmType() {
		return algorithmTypeEEnum;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public CommonFactory getCommonFactory() {
		return (CommonFactory)getEFactoryInstance();
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
		circuitEClass = createEClass(CIRCUIT);
		createEReference(circuitEClass, CIRCUIT__EXECUTIVE);
		createEReference(circuitEClass, CIRCUIT__SOLUTION);
		createEReference(circuitEClass, CIRCUIT__BUSES);
		createEAttribute(circuitEClass, CIRCUIT__MAP_NODE_TO_BUS);
		createEAttribute(circuitEClass, CIRCUIT__BUS_LIST);
		createEReference(circuitEClass, CIRCUIT__VOLTAGE_SOURCES);
		createEReference(circuitEClass, CIRCUIT__CURRENT_SOURCES);
		createEReference(circuitEClass, CIRCUIT__GENERATORS);
		createEReference(circuitEClass, CIRCUIT__LOADS);
		createEReference(circuitEClass, CIRCUIT__ACTIVE_CIRCUIT_ELEMENT);
		createEAttribute(circuitEClass, CIRCUIT__NAME);
		createEAttribute(circuitEClass, CIRCUIT__NUM_NODES);
		createEAttribute(circuitEClass, CIRCUIT__NUM_BUSES);
		createEAttribute(circuitEClass, CIRCUIT__FUNDAMENTAL);
		createEAttribute(circuitEClass, CIRCUIT__BUS_NAME_REDEFINED);
		createEAttribute(circuitEClass, CIRCUIT__SOLVED);
		createEAttribute(circuitEClass, CIRCUIT__LOAD_MULTIPLIER);
		createEAttribute(circuitEClass, CIRCUIT__DEFAULT_GROWTH_FACTOR);
		createEAttribute(circuitEClass, CIRCUIT__DEFAULT_GROWTH_RATE);
		createEAttribute(circuitEClass, CIRCUIT__GENERATOR_DISPATCH_REFERENCE);
		createEAttribute(circuitEClass, CIRCUIT__GEN_MULTIPLIER);
		createEAttribute(circuitEClass, CIRCUIT__DEFAULT_HOUR_MULT);
		createEAttribute(circuitEClass, CIRCUIT__CONTROL_BUS_NAME_REDEFINED);
		createEAttribute(circuitEClass, CIRCUIT__PRICE_SIGNAL);
		createEAttribute(circuitEClass, CIRCUIT__EMERG_MIN_VOLTS);
		createEAttribute(circuitEClass, CIRCUIT__EMERG_MAX_VOLTS);
		createEAttribute(circuitEClass, CIRCUIT__NORMAL_MIN_VOLTS);
		createEAttribute(circuitEClass, CIRCUIT__NORMAL_MAX_VOLTS);
		createEAttribute(circuitEClass, CIRCUIT__LOG_EVENTS);
		createEAttribute(circuitEClass, CIRCUIT__METER_ZONES_COMPUTED);
		createEAttribute(circuitEClass, CIRCUIT__ZONES_LOCKED);

		yMatrixEClass = createEClass(YMATRIX);
		createEReference(yMatrixEClass, YMATRIX__CIRCUIT);

		busEClass = createEClass(BUS);
		createEReference(busEClass, BUS__CIRCUIT);
		createEReference(busEClass, BUS__VBUS);
		createEReference(busEClass, BUS__BUS_CURRENT);
		createEAttribute(busEClass, BUS__NUM_NODES_THIS_BUS);
		createEAttribute(busEClass, BUS__KV_BASE);
		createEAttribute(busEClass, BUS__X);
		createEAttribute(busEClass, BUS__Y);
		createEAttribute(busEClass, BUS__COORD_DEFINED);
		createEAttribute(busEClass, BUS__KEEP);
		createEAttribute(busEClass, BUS__BUS_REF);
		createEAttribute(busEClass, BUS__NODE_NUM);

		circuitElementEClass = createEClass(CIRCUIT_ELEMENT);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NAME);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__ENABLED);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__BASE_FREQ);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__YORDER);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_INVALID);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__TERMINALS);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__ACTIVE_TERMINAL);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__ACTIVE_TERMINAL_INDEX);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NTERMS);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NCONDS);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NPHASES);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_SERIES);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_SHUNT);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_FREQ);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NODE_REF);

		solutionEClass = createEClass(SOLUTION);
		createEReference(solutionEClass, SOLUTION__Y);
		createEReference(solutionEClass, SOLUTION__YSYSTEM);
		createEReference(solutionEClass, SOLUTION__YSERIES);
		createEAttribute(solutionEClass, SOLUTION__YEAR);
		createEAttribute(solutionEClass, SOLUTION__PRESERVE_NODE_VOLTAGES);
		createEAttribute(solutionEClass, SOLUTION__FREQUENCY_CHANGED);
		createEAttribute(solutionEClass, SOLUTION__FREQUENCY);
		createEAttribute(solutionEClass, SOLUTION__HARMONIC);
		createEAttribute(solutionEClass, SOLUTION__MODE);
		createEReference(solutionEClass, SOLUTION__CIRCUIT);
		createEAttribute(solutionEClass, SOLUTION__SOLUTION_INITIALISED);
		createEAttribute(solutionEClass, SOLUTION__SERIES_YINVALID);
		createEAttribute(solutionEClass, SOLUTION__SYSTEM_YCHANGED);
		createEAttribute(solutionEClass, SOLUTION__LOAD_MODEL);
		createEAttribute(solutionEClass, SOLUTION__VOLTAGE_BASE_CHANGED);
		createEAttribute(solutionEClass, SOLUTION__HARMONIC_MODEL);
		createEAttribute(solutionEClass, SOLUTION__DYNAMIC_MODEL);
		createEAttribute(solutionEClass, SOLUTION__USE_AUXILLARY_CURRENTS);
		createEAttribute(solutionEClass, SOLUTION__LOADS_NEED_UPDATING);
		createEAttribute(solutionEClass, SOLUTION__ITERATION);
		createEAttribute(solutionEClass, SOLUTION__MAX_ITERATIONS);
		createEAttribute(solutionEClass, SOLUTION__MAX_ERROR);
		createEAttribute(solutionEClass, SOLUTION__CONVERGENCE_TOLERANCE);
		createEAttribute(solutionEClass, SOLUTION__CONVERGED);
		createEAttribute(solutionEClass, SOLUTION__CONTROL_ITERATION);
		createEAttribute(solutionEClass, SOLUTION__MAX_CONTROL_ITERATIONS);
		createEAttribute(solutionEClass, SOLUTION__CONTROL_MODE);
		createEAttribute(solutionEClass, SOLUTION__CONTROL_ACTIONS_DONE);
		createEAttribute(solutionEClass, SOLUTION__MOST_ITERATIONS_DONE);
		createEAttribute(solutionEClass, SOLUTION__ALGORITHM);
		createEAttribute(solutionEClass, SOLUTION__LAST_SOLUTION_WAS_DIRECT);
		createEAttribute(solutionEClass, SOLUTION__SOLUTION_COUNT);
		createEReference(solutionEClass, SOLUTION__NODE_V);
		createEReference(solutionEClass, SOLUTION__NODE_VBASE);
		createEReference(solutionEClass, SOLUTION__ERROR_SAVED);
		createEReference(solutionEClass, SOLUTION__VMAG_SAVED);
		createEReference(solutionEClass, SOLUTION__CURRENTS);
		createEReference(solutionEClass, SOLUTION__ALGORITHMS);

		solutionAlgsEClass = createEClass(SOLUTION_ALGS);

		terminalEClass = createEClass(TERMINAL);
		createEAttribute(terminalEClass, TERMINAL__BUS_REF);
		createEAttribute(terminalEClass, TERMINAL__TERM_NODE_REF);
		createEReference(terminalEClass, TERMINAL__CONDUCTORS);
		createEAttribute(terminalEClass, TERMINAL__CHECKED);
		createEAttribute(terminalEClass, TERMINAL__NCOND);
		createEAttribute(terminalEClass, TERMINAL__ACTIVE_CONDUCTOR);

		conductorEClass = createEClass(CONDUCTOR);
		createEAttribute(conductorEClass, CONDUCTOR__CLOSED);
		createEAttribute(conductorEClass, CONDUCTOR__FUSE_BLOWN);
		createEAttribute(conductorEClass, CONDUCTOR__ACCUM_ISQ_T);
		createEAttribute(conductorEClass, CONDUCTOR__TCC_NAME);

		namedEClass = createEClass(NAMED);
		createEAttribute(namedEClass, NAMED__NAME);

		globalsEClass = createEClass(GLOBALS);
		createEAttribute(globalsEClass, GLOBALS__SOLUTION_ABORT);
		createEReference(globalsEClass, GLOBALS__EXECUTIVES);
		createEAttribute(globalsEClass, GLOBALS__SOLUTION_WAS_ATTEMPTED);

		parserEClass = createEClass(PARSER);
		createEAttribute(parserEClass, PARSER__TOKEN);

		solverEClass = createEClass(SOLVER);

		// Create enums
		connectionTypeEEnum = createEEnum(CONNECTION_TYPE);
		lengthUnitEEnum = createEEnum(LENGTH_UNIT);
		tripActionEEnum = createEEnum(TRIP_ACTION);
		yBuildOptionEEnum = createEEnum(YBUILD_OPTION);
		controlModeTypeEEnum = createEEnum(CONTROL_MODE_TYPE);
		algorithmTypeEEnum = createEEnum(ALGORITHM_TYPE);
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
		ExecutivePackage theExecutivePackage = (ExecutivePackage)EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI);
		ConversionPackage theConversionPackage = (ConversionPackage)EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI);
		ElectrickeryPackage theElectrickeryPackage = (ElectrickeryPackage)EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		busEClass.getESuperTypes().add(this.getNamed());

		// Initialize classes and features; add operations and parameters
		initEClass(circuitEClass, Circuit.class, "Circuit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCircuit_Executive(), theExecutivePackage.getExecutive(), theExecutivePackage.getExecutive_Circuits(), "executive", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Solution(), this.getSolution(), this.getSolution_Circuit(), "solution", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Buses(), this.getBus(), this.getBus_Circuit(), "buses", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEIntegerObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getBus());
		g1.getETypeArguments().add(g2);
		initEAttribute(getCircuit_MapNodeToBus(), g1, "mapNodeToBus", null, 0, 1, Circuit.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_BusList(), ecorePackage.getEString(), "busList", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_VoltageSources(), theConversionPackage.getVoltageSource(), null, "voltageSources", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_CurrentSources(), theConversionPackage.getCurrentSource(), null, "currentSources", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Generators(), theConversionPackage.getGenerator(), null, "generators", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Loads(), theConversionPackage.getLoad(), null, "loads", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_ActiveCircuitElement(), this.getCircuitElement(), null, "activeCircuitElement", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Name(), ecorePackage.getEString(), "name", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_NumNodes(), ecorePackage.getEInt(), "numNodes", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_NumBuses(), ecorePackage.getEInt(), "numBuses", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Fundamental(), ecorePackage.getEDouble(), "fundamental", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_BusNameRedefined(), ecorePackage.getEBoolean(), "busNameRedefined", "true", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Solved(), ecorePackage.getEBoolean(), "solved", "false", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_LoadMultiplier(), ecorePackage.getEDouble(), "loadMultiplier", "1.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_DefaultGrowthFactor(), ecorePackage.getEDouble(), "defaultGrowthFactor", "1.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_DefaultGrowthRate(), ecorePackage.getEDouble(), "defaultGrowthRate", "1.025", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_GeneratorDispatchReference(), ecorePackage.getEDouble(), "generatorDispatchReference", "1000.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_GenMultiplier(), ecorePackage.getEDouble(), "genMultiplier", "1.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_DefaultHourMult(), ecorePackage.getEDouble(), "defaultHourMult", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Control_busNameRedefined(), ecorePackage.getEBoolean(), "control_busNameRedefined", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_PriceSignal(), ecorePackage.getEDouble(), "priceSignal", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_EmergMinVolts(), ecorePackage.getEDouble(), "emergMinVolts", "0.90", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_EmergMaxVolts(), ecorePackage.getEDouble(), "emergMaxVolts", "1.08", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_NormalMinVolts(), ecorePackage.getEDouble(), "normalMinVolts", "0.95", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_NormalMaxVolts(), ecorePackage.getEDouble(), "normalMaxVolts", "1.05", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_LogEvents(), ecorePackage.getEBoolean(), "logEvents", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_MeterZonesComputed(), ecorePackage.getEBoolean(), "meterZonesComputed", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_ZonesLocked(), ecorePackage.getEBoolean(), "zonesLocked", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(circuitEClass, null, "reProcessBusDefs", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, null, "initialiseNodeVBase", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, this.getCircuitElement(), "getCircuitElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, null, "doResetMeterZones", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, theConversionPackage.getPowerConversionElement(), "getPCElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(yMatrixEClass, YMatrix.class, "YMatrix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getYMatrix_Circuit(), this.getCircuit(), null, "circuit", null, 0, 1, YMatrix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(yMatrixEClass, null, "buildYMatrix", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getyBuildOption(), "buildOption", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "allocateVI", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(yMatrixEClass, null, "reCalcAllYPrims", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(yMatrixEClass, null, "reCalcInvalidYPrims", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(yMatrixEClass, null, "initializeNodeVBase", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(yMatrixEClass, ecorePackage.getEString(), "checkYMatrixforZeroes", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(busEClass, Bus.class, "Bus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBus_Circuit(), this.getCircuit(), this.getCircuit_Buses(), "circuit", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBus_VBus(), theElectrickeryPackage.getDComplexMatrix1D(), null, "vBus", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBus_BusCurrent(), theElectrickeryPackage.getDComplexMatrix1D(), null, "busCurrent", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_NumNodesThisBus(), ecorePackage.getEInt(), "numNodesThisBus", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_KVBase(), ecorePackage.getEDouble(), "kVBase", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_X(), ecorePackage.getEDouble(), "x", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_Y(), ecorePackage.getEDouble(), "y", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_CoordDefined(), ecorePackage.getEBoolean(), "coordDefined", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_Keep(), ecorePackage.getEBoolean(), "keep", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_BusRef(), ecorePackage.getEInt(), "busRef", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_NodeNum(), ecorePackage.getEInt(), "nodeNum", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(busEClass, ecorePackage.getEInt(), "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nodeNum", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(busEClass, ecorePackage.getEInt(), "find", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nodeNum", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(busEClass, ecorePackage.getEInt(), "findIdx", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nodeNum", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(busEClass, ecorePackage.getEInt(), "getRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nodeIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(busEClass, ecorePackage.getEInt(), "getNum", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nodeIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(busEClass, null, "allocateBusVoltages", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(busEClass, null, "allocateBusCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(circuitElementEClass, CircuitElement.class, "CircuitElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCircuitElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_BaseFreq(), ecorePackage.getEDouble(), "baseFreq", "60.0", 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_YOrder(), ecorePackage.getEInt(), "yOrder", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_YPrimInvalid(), ecorePackage.getEBoolean(), "yPrimInvalid", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_LastTerminalChecked(), ecorePackage.getEInt(), "lastTerminalChecked", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_Terminals(), this.getTerminal(), null, "terminals", null, 0, -1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_ActiveTerminal(), this.getTerminal(), null, "activeTerminal", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_ActiveTerminalIndex(), ecorePackage.getEInt(), "activeTerminalIndex", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NTerms(), ecorePackage.getEInt(), "nTerms", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NConds(), ecorePackage.getEInt(), "nConds", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NPhases(), ecorePackage.getEInt(), "nPhases", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_YPrimSeries(), theElectrickeryPackage.getDComplexMatrix2D(), null, "yPrimSeries", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_YPrimShunt(), theElectrickeryPackage.getDComplexMatrix2D(), null, "yPrimShunt", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_YPrim(), theElectrickeryPackage.getDComplexMatrix2D(), null, "yPrim", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_YPrimFreq(), ecorePackage.getEDouble(), "yPrimFreq", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NodeRef(), theElectrickeryPackage.getEIntArray(), "nodeRef", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(circuitElementEClass, null, "calcYPrim", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "yPrimFreq", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(circuitElementEClass, null, "doYPrimCalcs", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix2D(), "yMatrix", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(circuitElementEClass, theElectrickeryPackage.getDComplexMatrix2D(), "getYPrimValues", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getyBuildOption(), "opt", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(circuitElementEClass, null, "setNodeRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "iTerm", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getEIntArray(), "nodeRefArray", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitElementEClass, ecorePackage.getEString(), "getFirstBus", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitElementEClass, ecorePackage.getEString(), "getNextBus", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitElementEClass, null, "sumCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(solutionEClass, Solution.class, "Solution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSolution_Y(), theElectrickeryPackage.getDComplexMatrix2D(), null, "y", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_YSystem(), theElectrickeryPackage.getDComplexMatrix2D(), null, "ySystem", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_YSeries(), theElectrickeryPackage.getDComplexMatrix2D(), null, "ySeries", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Year(), ecorePackage.getEInt(), "year", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_PreserveNodeVoltages(), ecorePackage.getEBoolean(), "preserveNodeVoltages", "false", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_FrequencyChanged(), ecorePackage.getEBoolean(), "frequencyChanged", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Frequency(), ecorePackage.getEDouble(), "frequency", "60.0", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Harmonic(), ecorePackage.getEDouble(), "harmonic", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Mode(), theExecutivePackage.getsolutionMode(), "mode", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_Circuit(), this.getCircuit(), this.getCircuit_Solution(), "circuit", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SolutionInitialised(), ecorePackage.getEBoolean(), "solutionInitialised", "false", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SeriesYInvalid(), ecorePackage.getEBoolean(), "seriesYInvalid", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SystemYChanged(), ecorePackage.getEBoolean(), "systemYChanged", "true", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_LoadModel(), theExecutivePackage.getloadModelType(), "loadModel", "Powerflow", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_VoltageBaseChanged(), ecorePackage.getEBoolean(), "voltageBaseChanged", "false", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_HarmonicModel(), ecorePackage.getEBoolean(), "harmonicModel", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_DynamicModel(), ecorePackage.getEBoolean(), "dynamicModel", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_UseAuxillaryCurrents(), ecorePackage.getEBoolean(), "useAuxillaryCurrents", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_LoadsNeedUpdating(), ecorePackage.getEBoolean(), "loadsNeedUpdating", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Iteration(), ecorePackage.getEInt(), "iteration", "0", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_MaxIterations(), ecorePackage.getEInt(), "maxIterations", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_MaxError(), ecorePackage.getEDouble(), "maxError", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_ConvergenceTolerance(), ecorePackage.getEDouble(), "convergenceTolerance", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Converged(), ecorePackage.getEBoolean(), "converged", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_ControlIteration(), ecorePackage.getEInt(), "controlIteration", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_MaxControlIterations(), ecorePackage.getEInt(), "maxControlIterations", "10", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_ControlMode(), this.getcontrolModeType(), "controlMode", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_ControlActionsDone(), ecorePackage.getEBoolean(), "controlActionsDone", "false", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_MostIterationsDone(), ecorePackage.getEInt(), "mostIterationsDone", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Algorithm(), this.getalgorithmType(), "algorithm", "NormalSolve", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_LastSolutionWasDirect(), ecorePackage.getEBoolean(), "lastSolutionWasDirect", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SolutionCount(), ecorePackage.getEInt(), "solutionCount", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_NodeV(), theElectrickeryPackage.getDComplexMatrix1D(), null, "nodeV", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_NodeVBase(), theElectrickeryPackage.getDoubleMatrix1D(), null, "nodeVBase", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_ErrorSaved(), theElectrickeryPackage.getDoubleMatrix1D(), null, "errorSaved", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_VMagSaved(), theElectrickeryPackage.getDoubleMatrix1D(), null, "vMagSaved", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_Currents(), theElectrickeryPackage.getDComplexMatrix1D(), null, "currents", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_Algorithms(), this.getSolutionAlgs(), null, "algorithms", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(solutionEClass, null, "solve", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveSnap", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveCircuit", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveDirect", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveYDirect", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(solutionEClass, ecorePackage.getEInt(), "solveSystem", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "v", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveZeroLoadSnapShot", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "doPowerFlowSolution", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "snapShotInit", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "setGeneratorDispRef", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "setVoltageBases", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "checkControls", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEBoolean(), "convergenceCheck", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "sample_doControlActions", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "sampleControlDevices", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "doControlActions", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "checkFaultStatus", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "updateVBus", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(solutionAlgsEClass, SolutionAlgs.class, "SolutionAlgs", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveMonte1", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveMonte2", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveMonte3", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveMonteFault", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveFaultStudy", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveDaily", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solvePeakDay", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveYearly", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveDuty", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveDynamic", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveLD1", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveLD2", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionAlgsEClass, ecorePackage.getEInt(), "solveHarmonic", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(terminalEClass, Terminal.class, "Terminal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTerminal_BusRef(), ecorePackage.getEInt(), "busRef", null, 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_TermNodeRef(), ecorePackage.getEInt(), "termNodeRef", null, 0, -1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTerminal_Conductors(), this.getConductor(), null, "conductors", null, 0, -1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_Checked(), ecorePackage.getEBoolean(), "checked", "false", 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_NCond(), ecorePackage.getEInt(), "nCond", null, 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_ActiveConductor(), ecorePackage.getEInt(), "activeConductor", null, 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conductorEClass, Conductor.class, "Conductor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConductor_Closed(), ecorePackage.getEBoolean(), "closed", "true", 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConductor_FuseBlown(), ecorePackage.getEBoolean(), "fuseBlown", "false", 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConductor_AccumISqT(), ecorePackage.getEDouble(), "accumISqT", null, 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConductor_TccName(), ecorePackage.getEString(), "tccName", null, 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedEClass, Named.class, "Named", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamed_Name(), ecorePackage.getEString(), "name", null, 0, 1, Named.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(globalsEClass, Globals.class, "Globals", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGlobals_SolutionAbort(), ecorePackage.getEBoolean(), "solutionAbort", "false", 0, 1, Globals.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGlobals_Executives(), theExecutivePackage.getExecutive(), theExecutivePackage.getExecutive_Globals(), "executives", null, 0, 1, Globals.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGlobals_SolutionWasAttempted(), ecorePackage.getEBoolean(), "solutionWasAttempted", null, 0, 1, Globals.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parserEClass, Parser.class, "Parser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParser_Token(), ecorePackage.getEString(), "token", null, 0, 1, Parser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(parserEClass, ecorePackage.getEString(), "parseAsBusName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "numNodes", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getEIntArray(), "nodeArray", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(solverEClass, Solver.class, "Solver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(solverEClass, ecorePackage.getEInt(), "newSparseSet", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nBus", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(solverEClass, ecorePackage.getEInt(), "solveSparseSet", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix2D(), "Y", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "V", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "I", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(solverEClass, ecorePackage.getEInt(), "addPrimitiveMatrix", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix2D(), "Y", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nOrder", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getEIntArray(), "pNodes", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "Yprim", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(solverEClass, ecorePackage.getEInt(), "findIslands", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix2D(), "Y", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "nOrder", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getEIntArray(), "pNodes", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(connectionTypeEEnum, connectionType.class, "connectionType");
		addEEnumLiteral(connectionTypeEEnum, connectionType.WYE);
		addEEnumLiteral(connectionTypeEEnum, connectionType.LN);
		addEEnumLiteral(connectionTypeEEnum, connectionType.DELTA);
		addEEnumLiteral(connectionTypeEEnum, connectionType.LL);

		initEEnum(lengthUnitEEnum, lengthUnit.class, "lengthUnit");
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.NONE);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.MI);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.KM);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.KFT);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.M);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.ME);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.FT);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.IN);
		addEEnumLiteral(lengthUnitEEnum, lengthUnit.CM);

		initEEnum(tripActionEEnum, tripAction.class, "tripAction");
		addEEnumLiteral(tripActionEEnum, tripAction.TRIP_OPEN);
		addEEnumLiteral(tripActionEEnum, tripAction.CLOSE);

		initEEnum(yBuildOptionEEnum, yBuildOption.class, "yBuildOption");
		addEEnumLiteral(yBuildOptionEEnum, yBuildOption.SERIES_ONLY);
		addEEnumLiteral(yBuildOptionEEnum, yBuildOption.WHOLE_MATRIX);

		initEEnum(controlModeTypeEEnum, controlModeType.class, "controlModeType");
		addEEnumLiteral(controlModeTypeEEnum, controlModeType.EVENT_DRIVEN);
		addEEnumLiteral(controlModeTypeEEnum, controlModeType.TIME_DRIVEN);
		addEEnumLiteral(controlModeTypeEEnum, controlModeType.STATIC);

		initEEnum(algorithmTypeEEnum, algorithmType.class, "algorithmType");
		addEEnumLiteral(algorithmTypeEEnum, algorithmType.NORMAL_SOLVE);
		addEEnumLiteral(algorithmTypeEEnum, algorithmType.NEWTON_SOLVE);
	}

} //CommonPackageImpl
