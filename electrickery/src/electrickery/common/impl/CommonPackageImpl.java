/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import electrickery.ElectrickeryPackage;
import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CircuitElement;
import electrickery.common.Collection;
import electrickery.common.CommonFactory;
import electrickery.common.CommonPackage;
import electrickery.common.Conductor;
import electrickery.common.ControlQueue;
import electrickery.common.Electrickery;
import electrickery.common.Feeder;
import electrickery.common.Named;
import electrickery.common.Solution;
import electrickery.common.Terminal;
import electrickery.common.algorithmType;
import electrickery.common.connectionType;
import electrickery.common.controlModeType;
import electrickery.common.lengthUnit;
import electrickery.common.tripAction;
import electrickery.common.yBuildOption;
import electrickery.control.ControlPackage;
import electrickery.control.impl.ControlPackageImpl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.impl.ConversionPackageImpl;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.impl.DeliveryPackageImpl;
import electrickery.executive.ExecutivePackage;
import electrickery.executive.impl.ExecutivePackageImpl;
import electrickery.general.GeneralPackage;
import electrickery.general.impl.GeneralPackageImpl;
import electrickery.impl.ElectrickeryPackageImpl;
import electrickery.meter.MeterPackage;
import electrickery.meter.impl.MeterPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

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
    private EClass collectionEClass = null;

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
    private EClass feederEClass = null;

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
    private EClass terminalEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass controlQueueEClass = null;

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
	private EClass electrickeryEClass = null;

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
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

		// Create package meta-data objects
		theCommonPackage.createPackageContents();
		theElectrickeryPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theDeliveryPackage.createPackageContents();
		theExecutivePackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theMeterPackage.createPackageContents();

		// Initialize created meta-data
		theCommonPackage.initializePackageContents();
		theElectrickeryPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theDeliveryPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theMeterPackage.initializePackageContents();

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
    public EReference getCircuit_Solution() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_ControlQueue() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(1);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_BusList() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(2);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Faults() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(3);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_BusNameRedefined() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(22);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_NumNodes() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(18);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_Name() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(17);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_VoltageSources() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(4);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_CurrentSources() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(5);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Sensors() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(6);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Monitors() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(7);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_EnergyMeters() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(8);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Generators() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(9);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Transformers() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(10);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_CapControls() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(11);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_RegControls() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(12);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Lines() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(13);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Loads() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(14);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_ShuntCapacitors() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(15);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuit_Feeder() {
		return (EReference)circuitEClass.getEStructuralFeatures().get(16);
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
    public EAttribute getCircuit_Solved() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(21);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_Control_busNameRedefined() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(23);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_LoadMultiplier() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(24);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_DefaultGrowthFactor() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(25);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_DefaultHourMult() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(26);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuit_PriceSignal() {
		return (EAttribute)circuitEClass.getEStructuralFeatures().get(27);
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
    public EAttribute getBus_Name() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_VBus() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_BusCurrent() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_ZSC() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_YSC() {
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
    public EAttribute getBus_KVBase() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_CoordsDefined() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_BusChecked() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_Keep() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBus_RadialBus() {
		return (EAttribute)busEClass.getEStructuralFeatures().get(11);
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
    public EAttribute getCircuitElement_NodeRef() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_YOrder() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_YPrimInvalid() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(5);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_LastTerminalChecked() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_Checked() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_HasMeter() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_Isolated() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_HasControl() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_PartOfFeeder() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_ControlElement() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_Terminals() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_ActiveTerminal() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_NTerms() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_NConds() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_NPhases() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_BusIndex() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_YPrimSeries() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(19);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_YPrimShunt() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(20);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCircuitElement_YPrim() {
		return (EReference)circuitElementEClass.getEStructuralFeatures().get(21);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCircuitElement_YPrimFreq() {
		return (EAttribute)circuitElementEClass.getEStructuralFeatures().get(22);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCollection() {
		return collectionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCollection_NProperties() {
		return (EAttribute)collectionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCollection_PropertyName() {
		return (EAttribute)collectionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCollection_PropertyHelp() {
		return (EAttribute)collectionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCollection_PropertyIdxMap() {
		return (EAttribute)collectionEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCollection_ElementList() {
		return (EReference)collectionEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCollection_Saved() {
		return (EAttribute)collectionEClass.getEStructuralFeatures().get(5);
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
    public EClass getFeeder() {
		return feederEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFeeder_Spectrum() {
		return (EReference)feederEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFeeder_BaseFreq() {
		return (EAttribute)feederEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFeeder_Enabled() {
		return (EAttribute)feederEClass.getEStructuralFeatures().get(2);
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
    public EAttribute getSolution_Year() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(0);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_PreserveNodeVoltages() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_FrequencyChanged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(2);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Frequency() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(3);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Mode() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(4);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSolution_Circuit() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(5);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SolutionAbort() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(6);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SolutionInitialised() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(7);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SeriesYInvalid() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(8);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_SystemYChanged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(9);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_LoadModel() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(10);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_VoltageBaseChanged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(11);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_HarmonicModel() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(12);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_DynamicModel() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(13);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_UseAuxillaryCurrents() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(14);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_LoadsNeedUpdating() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(15);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Iteration() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(16);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MaxIterations() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(17);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MaxError() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(18);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ConvergenceTolerance() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(19);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Converged() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(20);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ControlIteration() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(21);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MaxControlIterations() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(22);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ControlMode() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(23);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_ControlActionsDone() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(24);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_MostIterationsDone() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(25);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_Algorithm() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(26);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSolution_LastSolutionWasDirect() {
		return (EAttribute)solutionEClass.getEStructuralFeatures().get(27);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSolution_NodeV() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(28);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSolution_Currents() {
		return (EReference)solutionEClass.getEStructuralFeatures().get(29);
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
    public EClass getControlQueue() {
		return controlQueueEClass;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlQueue_Empty() {
		return (EAttribute)controlQueueEClass.getEStructuralFeatures().get(0);
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
	public EClass getElectrickery() {
		return electrickeryEClass;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_WireData() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(0);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_LineGeometries() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(1);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_GrowthShapes() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(2);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_LineCodes() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(3);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_LoadShapes() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(4);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_Spectrums() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(5);
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElectrickery_Executives() {
		return (EReference)electrickeryEClass.getEStructuralFeatures().get(6);
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
		createEReference(circuitEClass, CIRCUIT__SOLUTION);
		createEReference(circuitEClass, CIRCUIT__CONTROL_QUEUE);
		createEReference(circuitEClass, CIRCUIT__BUS_LIST);
		createEReference(circuitEClass, CIRCUIT__FAULTS);
		createEReference(circuitEClass, CIRCUIT__VOLTAGE_SOURCES);
		createEReference(circuitEClass, CIRCUIT__CURRENT_SOURCES);
		createEReference(circuitEClass, CIRCUIT__SENSORS);
		createEReference(circuitEClass, CIRCUIT__MONITORS);
		createEReference(circuitEClass, CIRCUIT__ENERGY_METERS);
		createEReference(circuitEClass, CIRCUIT__GENERATORS);
		createEReference(circuitEClass, CIRCUIT__TRANSFORMERS);
		createEReference(circuitEClass, CIRCUIT__CAP_CONTROLS);
		createEReference(circuitEClass, CIRCUIT__REG_CONTROLS);
		createEReference(circuitEClass, CIRCUIT__LINES);
		createEReference(circuitEClass, CIRCUIT__LOADS);
		createEReference(circuitEClass, CIRCUIT__SHUNT_CAPACITORS);
		createEReference(circuitEClass, CIRCUIT__FEEDER);
		createEAttribute(circuitEClass, CIRCUIT__NAME);
		createEAttribute(circuitEClass, CIRCUIT__NUM_NODES);
		createEAttribute(circuitEClass, CIRCUIT__GENERATOR_DISPATCH_REFERENCE);
		createEAttribute(circuitEClass, CIRCUIT__GEN_MULTIPLIER);
		createEAttribute(circuitEClass, CIRCUIT__SOLVED);
		createEAttribute(circuitEClass, CIRCUIT__BUS_NAME_REDEFINED);
		createEAttribute(circuitEClass, CIRCUIT__CONTROL_BUS_NAME_REDEFINED);
		createEAttribute(circuitEClass, CIRCUIT__LOAD_MULTIPLIER);
		createEAttribute(circuitEClass, CIRCUIT__DEFAULT_GROWTH_FACTOR);
		createEAttribute(circuitEClass, CIRCUIT__DEFAULT_HOUR_MULT);
		createEAttribute(circuitEClass, CIRCUIT__PRICE_SIGNAL);

		busEClass = createEClass(BUS);
		createEAttribute(busEClass, BUS__NAME);
		createEAttribute(busEClass, BUS__VBUS);
		createEAttribute(busEClass, BUS__BUS_CURRENT);
		createEAttribute(busEClass, BUS__ZSC);
		createEAttribute(busEClass, BUS__YSC);
		createEAttribute(busEClass, BUS__X);
		createEAttribute(busEClass, BUS__Y);
		createEAttribute(busEClass, BUS__KV_BASE);
		createEAttribute(busEClass, BUS__COORDS_DEFINED);
		createEAttribute(busEClass, BUS__BUS_CHECKED);
		createEAttribute(busEClass, BUS__KEEP);
		createEAttribute(busEClass, BUS__RADIAL_BUS);

		circuitElementEClass = createEClass(CIRCUIT_ELEMENT);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NAME);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__ENABLED);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__BASE_FREQ);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NODE_REF);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__YORDER);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_INVALID);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__LAST_TERMINAL_CHECKED);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__CHECKED);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__HAS_METER);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__ISOLATED);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__HAS_CONTROL);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__PART_OF_FEEDER);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__CONTROL_ELEMENT);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__TERMINALS);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__ACTIVE_TERMINAL);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NTERMS);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NCONDS);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__NPHASES);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__BUS_INDEX);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_SERIES);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_SHUNT);
		createEReference(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM);
		createEAttribute(circuitElementEClass, CIRCUIT_ELEMENT__YPRIM_FREQ);

		collectionEClass = createEClass(COLLECTION);
		createEAttribute(collectionEClass, COLLECTION__NPROPERTIES);
		createEAttribute(collectionEClass, COLLECTION__PROPERTY_NAME);
		createEAttribute(collectionEClass, COLLECTION__PROPERTY_HELP);
		createEAttribute(collectionEClass, COLLECTION__PROPERTY_IDX_MAP);
		createEReference(collectionEClass, COLLECTION__ELEMENT_LIST);
		createEAttribute(collectionEClass, COLLECTION__SAVED);

		conductorEClass = createEClass(CONDUCTOR);
		createEAttribute(conductorEClass, CONDUCTOR__CLOSED);
		createEAttribute(conductorEClass, CONDUCTOR__FUSE_BLOWN);
		createEAttribute(conductorEClass, CONDUCTOR__ACCUM_ISQ_T);
		createEAttribute(conductorEClass, CONDUCTOR__TCC_NAME);

		feederEClass = createEClass(FEEDER);
		createEReference(feederEClass, FEEDER__SPECTRUM);
		createEAttribute(feederEClass, FEEDER__BASE_FREQ);
		createEAttribute(feederEClass, FEEDER__ENABLED);

		solutionEClass = createEClass(SOLUTION);
		createEAttribute(solutionEClass, SOLUTION__YEAR);
		createEAttribute(solutionEClass, SOLUTION__PRESERVE_NODE_VOLTAGES);
		createEAttribute(solutionEClass, SOLUTION__FREQUENCY_CHANGED);
		createEAttribute(solutionEClass, SOLUTION__FREQUENCY);
		createEAttribute(solutionEClass, SOLUTION__MODE);
		createEReference(solutionEClass, SOLUTION__CIRCUIT);
		createEAttribute(solutionEClass, SOLUTION__SOLUTION_ABORT);
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
		createEReference(solutionEClass, SOLUTION__NODE_V);
		createEReference(solutionEClass, SOLUTION__CURRENTS);

		terminalEClass = createEClass(TERMINAL);
		createEAttribute(terminalEClass, TERMINAL__BUS_REF);
		createEAttribute(terminalEClass, TERMINAL__TERM_NODE_REF);
		createEReference(terminalEClass, TERMINAL__CONDUCTORS);
		createEAttribute(terminalEClass, TERMINAL__CHECKED);
		createEAttribute(terminalEClass, TERMINAL__NCOND);
		createEAttribute(terminalEClass, TERMINAL__ACTIVE_CONDUCTOR);

		controlQueueEClass = createEClass(CONTROL_QUEUE);
		createEAttribute(controlQueueEClass, CONTROL_QUEUE__EMPTY);

		namedEClass = createEClass(NAMED);
		createEAttribute(namedEClass, NAMED__NAME);

		electrickeryEClass = createEClass(ELECTRICKERY);
		createEReference(electrickeryEClass, ELECTRICKERY__WIRE_DATA);
		createEReference(electrickeryEClass, ELECTRICKERY__LINE_GEOMETRIES);
		createEReference(electrickeryEClass, ELECTRICKERY__GROWTH_SHAPES);
		createEReference(electrickeryEClass, ELECTRICKERY__LINE_CODES);
		createEReference(electrickeryEClass, ELECTRICKERY__LOAD_SHAPES);
		createEReference(electrickeryEClass, ELECTRICKERY__SPECTRUMS);
		createEReference(electrickeryEClass, ELECTRICKERY__EXECUTIVES);

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
		DeliveryPackage theDeliveryPackage = (DeliveryPackage)EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI);
		ConversionPackage theConversionPackage = (ConversionPackage)EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI);
		MeterPackage theMeterPackage = (MeterPackage)EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI);
		ControlPackage theControlPackage = (ControlPackage)EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI);
		ElectrickeryPackage theElectrickeryPackage = (ElectrickeryPackage)EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI);
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);
		ExecutivePackage theExecutivePackage = (ExecutivePackage)EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(circuitEClass, Circuit.class, "Circuit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCircuit_Solution(), this.getSolution(), this.getSolution_Circuit(), "solution", null, 1, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_ControlQueue(), this.getControlQueue(), null, "controlQueue", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_BusList(), this.getBus(), null, "busList", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Faults(), theDeliveryPackage.getFault(), null, "faults", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_VoltageSources(), theConversionPackage.getVoltageSource(), null, "voltageSources", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_CurrentSources(), theConversionPackage.getCurrentSource(), null, "currentSources", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Sensors(), theMeterPackage.getSensor(), null, "sensors", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Monitors(), theMeterPackage.getMonitor(), null, "monitors", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_EnergyMeters(), theMeterPackage.getEnergyMeter(), null, "energyMeters", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Generators(), theConversionPackage.getGenerator(), theConversionPackage.getGenerator_Circuit(), "generators", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Transformers(), theDeliveryPackage.getTransformer(), null, "transformers", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_CapControls(), theControlPackage.getCapacitorControl(), null, "capControls", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_RegControls(), theControlPackage.getRegulatorControl(), null, "regControls", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Lines(), theDeliveryPackage.getLine(), null, "lines", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Loads(), theConversionPackage.getLoad(), null, "loads", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_ShuntCapacitors(), theDeliveryPackage.getCapacitor(), null, "shuntCapacitors", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuit_Feeder(), this.getFeeder(), null, "feeder", null, 0, -1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Name(), ecorePackage.getEString(), "name", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_NumNodes(), ecorePackage.getEInt(), "numNodes", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_GeneratorDispatchReference(), ecorePackage.getEDouble(), "generatorDispatchReference", "1000.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_GenMultiplier(), ecorePackage.getEDouble(), "genMultiplier", "1.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Solved(), ecorePackage.getEBoolean(), "solved", "false", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_BusNameRedefined(), ecorePackage.getEBoolean(), "busNameRedefined", "true", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_Control_busNameRedefined(), ecorePackage.getEBoolean(), "control_busNameRedefined", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_LoadMultiplier(), ecorePackage.getEDouble(), "loadMultiplier", "1.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_DefaultGrowthFactor(), ecorePackage.getEDouble(), "defaultGrowthFactor", "1.0", 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_DefaultHourMult(), ecorePackage.getEDouble(), "defaultHourMult", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuit_PriceSignal(), ecorePackage.getEDouble(), "priceSignal", null, 0, 1, Circuit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(circuitEClass, null, "buildYMatrix", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getyBuildOption(), "buildOption", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "allocateVI", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, null, "reProcessBusDefs", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, null, "reCalcAllYPrims", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, null, "reCalcInvalidYPrims", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, null, "initialiseNodeVBase", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, this.getCircuitElement(), "getCircuitElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, theDeliveryPackage.getPowerDeliveryElement(), "getPDElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, theConversionPackage.getPowerConversionElement(), "getPCElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, theControlPackage.getControlElement(), "getDSSControls", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, theMeterPackage.getMeterElement(), "getMeterElements", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitEClass, theDeliveryPackage.getTransformer(), "getSubstations", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(busEClass, Bus.class, "Bus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBus_Name(), ecorePackage.getEString(), "name", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_VBus(), ecorePackage.getEDouble(), "vBus", "115.0", 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_BusCurrent(), ecorePackage.getEDouble(), "busCurrent", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_ZSC(), ecorePackage.getEDouble(), "zSC", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_YSC(), ecorePackage.getEDouble(), "ySC", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_X(), ecorePackage.getEDouble(), "x", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_Y(), ecorePackage.getEDouble(), "y", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_KVBase(), ecorePackage.getEDouble(), "kVBase", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_CoordsDefined(), ecorePackage.getEBoolean(), "coordsDefined", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_BusChecked(), ecorePackage.getEBoolean(), "busChecked", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_Keep(), ecorePackage.getEBoolean(), "keep", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBus_RadialBus(), ecorePackage.getEBoolean(), "radialBus", null, 0, 1, Bus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(circuitElementEClass, CircuitElement.class, "CircuitElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCircuitElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_BaseFreq(), ecorePackage.getEDouble(), "baseFreq", "60.0", 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NodeRef(), ecorePackage.getEInt(), "nodeRef", null, 0, -1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_YOrder(), ecorePackage.getEInt(), "yOrder", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_YPrimInvalid(), ecorePackage.getEBoolean(), "yPrimInvalid", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_LastTerminalChecked(), ecorePackage.getEInt(), "lastTerminalChecked", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_Checked(), ecorePackage.getEBoolean(), "checked", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_HasMeter(), ecorePackage.getEBoolean(), "hasMeter", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_Isolated(), ecorePackage.getEBoolean(), "isolated", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_HasControl(), ecorePackage.getEBoolean(), "hasControl", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_PartOfFeeder(), ecorePackage.getEBoolean(), "partOfFeeder", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_ControlElement(), ecorePackage.getEClass(), null, "controlElement", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_Terminals(), this.getTerminal(), null, "terminals", null, 0, -1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_ActiveTerminal(), ecorePackage.getEClass(), null, "activeTerminal", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NTerms(), ecorePackage.getEInt(), "nTerms", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NConds(), ecorePackage.getEInt(), "nConds", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_NPhases(), ecorePackage.getEInt(), "nPhases", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_BusIndex(), ecorePackage.getEInt(), "busIndex", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_YPrimSeries(), theElectrickeryPackage.getDComplexMatrix2D(), null, "yPrimSeries", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_YPrimShunt(), theElectrickeryPackage.getDComplexMatrix2D(), null, "yPrimShunt", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCircuitElement_YPrim(), theElectrickeryPackage.getDComplexMatrix2D(), null, "yPrim", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCircuitElement_YPrimFreq(), ecorePackage.getEDouble(), "yPrimFreq", null, 0, 1, CircuitElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(circuitElementEClass, null, "calcYPrim", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "yPrimFreq", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(circuitElementEClass, null, "doYPrimCalcs", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix2D(), "yMatrix", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(circuitElementEClass, theElectrickeryPackage.getDComplexMatrix2D(), "getYPrimValues", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getyBuildOption(), "opt", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(circuitElementEClass, null, "makePosSequence", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(collectionEClass, Collection.class, "Collection", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCollection_NProperties(), ecorePackage.getEInt(), "nProperties", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCollection_PropertyName(), ecorePackage.getEString(), "propertyName", null, 0, -1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCollection_PropertyHelp(), ecorePackage.getEString(), "propertyHelp", null, 0, -1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCollection_PropertyIdxMap(), ecorePackage.getEString(), "propertyIdxMap", null, 0, -1, Collection.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCollection_ElementList(), this.getCircuitElement(), null, "elementList", null, 0, -1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCollection_Saved(), ecorePackage.getEBoolean(), "saved", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conductorEClass, Conductor.class, "Conductor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConductor_Closed(), ecorePackage.getEBoolean(), "closed", "true", 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConductor_FuseBlown(), ecorePackage.getEBoolean(), "fuseBlown", "false", 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConductor_AccumISqT(), ecorePackage.getEDouble(), "accumISqT", null, 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConductor_TccName(), ecorePackage.getEString(), "tccName", null, 0, 1, Conductor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(feederEClass, Feeder.class, "Feeder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeeder_Spectrum(), theGeneralPackage.getSpectrum(), null, "spectrum", null, 0, 1, Feeder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeeder_BaseFreq(), ecorePackage.getEDouble(), "baseFreq", "60.0", 0, 1, Feeder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeeder_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1, Feeder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(solutionEClass, Solution.class, "Solution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSolution_Year(), ecorePackage.getEInt(), "year", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_PreserveNodeVoltages(), ecorePackage.getEBoolean(), "preserveNodeVoltages", "false", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_FrequencyChanged(), ecorePackage.getEBoolean(), "frequencyChanged", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Frequency(), ecorePackage.getEDouble(), "frequency", "60.0", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_Mode(), theExecutivePackage.getsolutionMode(), "mode", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_Circuit(), this.getCircuit(), this.getCircuit_Solution(), "circuit", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SolutionAbort(), ecorePackage.getEBoolean(), "solutionAbort", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SolutionInitialised(), ecorePackage.getEBoolean(), "solutionInitialised", "false", 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SeriesYInvalid(), ecorePackage.getEBoolean(), "seriesYInvalid", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSolution_SystemYChanged(), ecorePackage.getEBoolean(), "systemYChanged", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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
		initEReference(getSolution_NodeV(), theElectrickeryPackage.getDComplexMatrix1D(), null, "nodeV", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSolution_Currents(), theElectrickeryPackage.getDComplexMatrix1D(), null, "currents", null, 0, 1, Solution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(solutionEClass, null, "updateVBus", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "solve", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveSnap", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveCircuit", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "solveDirect", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEInt(), "solveYDirect", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(solutionEClass, ecorePackage.getEInt(), "solveSystem", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "v", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "doPowerFlowSolution", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "doNormalSolution", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "doNewtonSolution", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "snapShotInit", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "restoreNodeVFromVBus", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "zeroInjectionCurrent", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "getSourceInjCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "getMachineInjCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "getPCInjCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "setGenerator_dQdV", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "setGeneratorDispRef", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "checkControls", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, ecorePackage.getEBoolean(), "convergenceCheck", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "sample_doControlActions", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "sampleControlDevices", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "doControlActions", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(solutionEClass, null, "checkFaultStatus", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(solutionEClass, null, "addInAuxCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getalgorithmType(), "algorithm", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(terminalEClass, Terminal.class, "Terminal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTerminal_BusRef(), ecorePackage.getEInt(), "busRef", null, 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_TermNodeRef(), ecorePackage.getEInt(), "termNodeRef", null, 0, -1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTerminal_Conductors(), this.getConductor(), null, "conductors", null, 0, -1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_Checked(), ecorePackage.getEBoolean(), "checked", "false", 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_NCond(), ecorePackage.getEInt(), "nCond", null, 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTerminal_ActiveConductor(), ecorePackage.getEInt(), "activeConductor", null, 0, 1, Terminal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlQueueEClass, ControlQueue.class, "ControlQueue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getControlQueue_Empty(), ecorePackage.getEBoolean(), "empty", null, 0, 1, ControlQueue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedEClass, Named.class, "Named", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamed_Name(), ecorePackage.getEString(), "name", null, 0, 1, Named.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(electrickeryEClass, Electrickery.class, "Electrickery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElectrickery_WireData(), theGeneralPackage.getWireData(), null, "wireData", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElectrickery_LineGeometries(), theGeneralPackage.getLineGeometry(), null, "lineGeometries", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElectrickery_GrowthShapes(), theGeneralPackage.getGrowthShape(), null, "growthShapes", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElectrickery_LineCodes(), theGeneralPackage.getLineCode(), null, "lineCodes", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElectrickery_LoadShapes(), theGeneralPackage.getLoadShape(), null, "loadShapes", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElectrickery_Spectrums(), theGeneralPackage.getSpectrum(), null, "spectrums", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElectrickery_Executives(), theExecutivePackage.getExecutive(), null, "executives", null, 0, -1, Electrickery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
