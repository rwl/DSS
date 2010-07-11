/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.impl;

import electrickery.ElectrickeryPackage;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.control.CapacitorControl;
import electrickery.control.ControlElement;
import electrickery.control.ControlFactory;
import electrickery.control.ControlPackage;
import electrickery.control.GeneratorDispatcher;
import electrickery.control.Recloser;
import electrickery.control.RegulatorControl;
import electrickery.control.Relay;
import electrickery.control.StorageController;
import electrickery.control.SwitchControl;
import electrickery.control.chargeMode;
import electrickery.control.controlType;
import electrickery.control.dischargeMode;
import electrickery.control.relayType;
import electrickery.control.switchAction;
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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlPackageImpl extends EPackageImpl implements ControlPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass controlElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass capacitorControlEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass generatorDispatcherEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass recloserEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass regulatorControlEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass relayEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass storageControllerEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass switchControlEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum controlTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum relayTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dischargeModeEEnum = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum chargeModeEEnum = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum switchActionEEnum = null;

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
	 * @see electrickery.control.ControlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private ControlPackageImpl() {
		super(eNS_URI, ControlFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ControlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static ControlPackage init() {
		if (isInited) return (ControlPackage)EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI);

		// Obtain or create and register package
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ControlPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ElectrickeryPackageImpl theElectrickeryPackage = (ElectrickeryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) instanceof ElectrickeryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) : ElectrickeryPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

		// Create package meta-data objects
		theControlPackage.createPackageContents();
		theElectrickeryPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theDeliveryPackage.createPackageContents();
		theExecutivePackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theMeterPackage.createPackageContents();

		// Initialize created meta-data
		theControlPackage.initializePackageContents();
		theElectrickeryPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theDeliveryPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theMeterPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theControlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ControlPackage.eNS_URI, theControlPackage);
		return theControlPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getControlElement() {
		return controlElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_ElementName() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_ElementTerminal() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_ControlledBusName() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getControlElement_ControlledBus() {
		return (EReference)controlElementEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_MonitoredVariable() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_MonitoredVarIndex() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_TimeDelay() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getControlElement_DblTraceParam() {
		return (EAttribute)controlElementEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCapacitorControl() {
		return capacitorControlEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_Element() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_Terminal() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_Capacitor() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_Type() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_PTRatio() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_CTRatio() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_OnSetting() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_OffSetting() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_Delay() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_VoltOverride() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_VMax() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_VMin() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_DelayOff() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitorControl_DeadTime() {
		return (EAttribute)capacitorControlEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGeneratorDispatcher() {
		return generatorDispatcherEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGeneratorDispatcher_Element() {
		return (EAttribute)generatorDispatcherEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGeneratorDispatcher_Terminal() {
		return (EAttribute)generatorDispatcherEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGeneratorDispatcher_KWLimit() {
		return (EAttribute)generatorDispatcherEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGeneratorDispatcher_KWBand() {
		return (EAttribute)generatorDispatcherEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGeneratorDispatcher_KVarLimit() {
		return (EAttribute)generatorDispatcherEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGeneratorDispatcher_GenList() {
		return (EReference)generatorDispatcherEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGeneratorDispatcher_Weights() {
		return (EAttribute)generatorDispatcherEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getRecloser() {
		return recloserEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_MonitoredObj() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_MonitoredTerm() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_SwitchedObj() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_SwitchedTerm() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_NFast() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_PhaseFast() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_PhaseDelayed() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_GroundFast() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_GroundDelayed() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_PhaseTrip() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_GroundTrip() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_PhaseInst() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_GroundInst() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_Reset() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_Shots() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_RecloseIntervals() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_Delay() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_Action() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_TDPhFast() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_TDGrFast() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(19);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_TDPhDelayed() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(20);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRecloser_TDGrDelayed() {
		return (EAttribute)recloserEClass.getEStructuralFeatures().get(21);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getRegulatorControl() {
		return regulatorControlEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_Transformer() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_Winding() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_VReg() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_Band() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_PTRatio() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_CTPrim() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_R() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_X() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_Bus() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_Delay() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_Reversible() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_RevVReg() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_RevBand() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_RevR() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_RevX() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_TapDelay() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_DebugTrace() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_MaxTapChange() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_InverseTime() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRegulatorControl_TapWinding() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(19);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRegulatorControl_VLimit() {
		return (EAttribute)regulatorControlEClass.getEStructuralFeatures().get(20);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getRelay() {
		return relayEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_MonitoredObj() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_MonitoredTerm() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_SwitchedObj() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_SwitchedTerm() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Type() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_PhaseCurve() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_GroundCurve() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_PhaseTrip() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_GroundTrip() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_TDPhase() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_TDGround() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_PhaseInst() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_GroundInst() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Reset() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Shots() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_RecloseIntervals() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Delay() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getRelay_OvervoltCurve() {
		return (EReference)relayEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getRelay_UndervoltCurve() {
		return (EReference)relayEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_KVBase() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(19);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_PctPickup47() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(20);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_PctAmps46() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(21);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_PctPickup46() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(22);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_ISQT46() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(23);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Variable() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(24);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Overtrip() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(25);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Undertrip() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(26);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_BreakerTime() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(27);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRelay_Action() {
		return (EAttribute)relayEClass.getEStructuralFeatures().get(28);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStorageController() {
		return storageControllerEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_KWTarget() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PctKWBand() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PFTarget() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PFBand() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStorageController_Elements() {
		return (EReference)storageControllerEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_Weights() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_ModeDischarge() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_ModeCharge() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_TimeDischargeTrigger() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(8);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_TimeChargeTrigger() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(9);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PctRateKW() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(10);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PctRateKVAr() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(11);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PctRateCharge() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(12);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PctReserve() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(13);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_KWhTotal() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(14);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_KWTotal() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(15);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_KWhActual() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(16);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_KWActual() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(17);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_KWNeed() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(18);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_PctParticipation() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(19);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStorageController_Yearly() {
		return (EReference)storageControllerEClass.getEStructuralFeatures().get(20);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStorageController_Daily() {
		return (EReference)storageControllerEClass.getEStructuralFeatures().get(21);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStorageController_Duty() {
		return (EReference)storageControllerEClass.getEStructuralFeatures().get(22);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_EventLog() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(23);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_VarDispatch() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(24);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStorageController_InhibitTime() {
		return (EAttribute)storageControllerEClass.getEStructuralFeatures().get(25);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchControl() {
		return switchControlEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchControl_Action() {
		return (EAttribute)switchControlEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchControl_Lock() {
		return (EAttribute)switchControlEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchControl_Delay() {
		return (EAttribute)switchControlEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getcontrolType() {
		return controlTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getrelayType() {
		return relayTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getdischargeMode() {
		return dischargeModeEEnum;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getchargeMode() {
		return chargeModeEEnum;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getswitchAction() {
		return switchActionEEnum;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ControlFactory getControlFactory() {
		return (ControlFactory)getEFactoryInstance();
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
		controlElementEClass = createEClass(CONTROL_ELEMENT);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__ELEMENT_NAME);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__ELEMENT_TERMINAL);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__CONTROLLED_BUS_NAME);
		createEReference(controlElementEClass, CONTROL_ELEMENT__CONTROLLED_BUS);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__MONITORED_VARIABLE);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__MONITORED_VAR_INDEX);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__TIME_DELAY);
		createEAttribute(controlElementEClass, CONTROL_ELEMENT__DBL_TRACE_PARAM);

		capacitorControlEClass = createEClass(CAPACITOR_CONTROL);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__ELEMENT);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__TERMINAL);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__CAPACITOR);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__TYPE);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__PT_RATIO);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__CT_RATIO);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__ON_SETTING);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__OFF_SETTING);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__DELAY);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__VOLT_OVERRIDE);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__VMAX);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__VMIN);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__DELAY_OFF);
		createEAttribute(capacitorControlEClass, CAPACITOR_CONTROL__DEAD_TIME);

		generatorDispatcherEClass = createEClass(GENERATOR_DISPATCHER);
		createEAttribute(generatorDispatcherEClass, GENERATOR_DISPATCHER__ELEMENT);
		createEAttribute(generatorDispatcherEClass, GENERATOR_DISPATCHER__TERMINAL);
		createEAttribute(generatorDispatcherEClass, GENERATOR_DISPATCHER__KW_LIMIT);
		createEAttribute(generatorDispatcherEClass, GENERATOR_DISPATCHER__KW_BAND);
		createEAttribute(generatorDispatcherEClass, GENERATOR_DISPATCHER__KVAR_LIMIT);
		createEReference(generatorDispatcherEClass, GENERATOR_DISPATCHER__GEN_LIST);
		createEAttribute(generatorDispatcherEClass, GENERATOR_DISPATCHER__WEIGHTS);

		recloserEClass = createEClass(RECLOSER);
		createEAttribute(recloserEClass, RECLOSER__MONITORED_OBJ);
		createEAttribute(recloserEClass, RECLOSER__MONITORED_TERM);
		createEAttribute(recloserEClass, RECLOSER__SWITCHED_OBJ);
		createEAttribute(recloserEClass, RECLOSER__SWITCHED_TERM);
		createEAttribute(recloserEClass, RECLOSER__NFAST);
		createEAttribute(recloserEClass, RECLOSER__PHASE_FAST);
		createEAttribute(recloserEClass, RECLOSER__PHASE_DELAYED);
		createEAttribute(recloserEClass, RECLOSER__GROUND_FAST);
		createEAttribute(recloserEClass, RECLOSER__GROUND_DELAYED);
		createEAttribute(recloserEClass, RECLOSER__PHASE_TRIP);
		createEAttribute(recloserEClass, RECLOSER__GROUND_TRIP);
		createEAttribute(recloserEClass, RECLOSER__PHASE_INST);
		createEAttribute(recloserEClass, RECLOSER__GROUND_INST);
		createEAttribute(recloserEClass, RECLOSER__RESET);
		createEAttribute(recloserEClass, RECLOSER__SHOTS);
		createEAttribute(recloserEClass, RECLOSER__RECLOSE_INTERVALS);
		createEAttribute(recloserEClass, RECLOSER__DELAY);
		createEAttribute(recloserEClass, RECLOSER__ACTION);
		createEAttribute(recloserEClass, RECLOSER__TD_PH_FAST);
		createEAttribute(recloserEClass, RECLOSER__TD_GR_FAST);
		createEAttribute(recloserEClass, RECLOSER__TD_PH_DELAYED);
		createEAttribute(recloserEClass, RECLOSER__TD_GR_DELAYED);

		regulatorControlEClass = createEClass(REGULATOR_CONTROL);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__TRANSFORMER);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__WINDING);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__VREG);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__BAND);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__PT_RATIO);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__CT_PRIM);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__R);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__X);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__BUS);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__DELAY);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__REVERSIBLE);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__REV_VREG);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__REV_BAND);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__REV_R);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__REV_X);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__TAP_DELAY);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__DEBUG_TRACE);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__MAX_TAP_CHANGE);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__INVERSE_TIME);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__TAP_WINDING);
		createEAttribute(regulatorControlEClass, REGULATOR_CONTROL__VLIMIT);

		relayEClass = createEClass(RELAY);
		createEAttribute(relayEClass, RELAY__MONITORED_OBJ);
		createEAttribute(relayEClass, RELAY__MONITORED_TERM);
		createEAttribute(relayEClass, RELAY__SWITCHED_OBJ);
		createEAttribute(relayEClass, RELAY__SWITCHED_TERM);
		createEAttribute(relayEClass, RELAY__TYPE);
		createEAttribute(relayEClass, RELAY__PHASE_CURVE);
		createEAttribute(relayEClass, RELAY__GROUND_CURVE);
		createEAttribute(relayEClass, RELAY__PHASE_TRIP);
		createEAttribute(relayEClass, RELAY__GROUND_TRIP);
		createEAttribute(relayEClass, RELAY__TD_PHASE);
		createEAttribute(relayEClass, RELAY__TD_GROUND);
		createEAttribute(relayEClass, RELAY__PHASE_INST);
		createEAttribute(relayEClass, RELAY__GROUND_INST);
		createEAttribute(relayEClass, RELAY__RESET);
		createEAttribute(relayEClass, RELAY__SHOTS);
		createEAttribute(relayEClass, RELAY__RECLOSE_INTERVALS);
		createEAttribute(relayEClass, RELAY__DELAY);
		createEReference(relayEClass, RELAY__OVERVOLT_CURVE);
		createEReference(relayEClass, RELAY__UNDERVOLT_CURVE);
		createEAttribute(relayEClass, RELAY__KV_BASE);
		createEAttribute(relayEClass, RELAY__PCT_PICKUP47);
		createEAttribute(relayEClass, RELAY__PCT_AMPS46);
		createEAttribute(relayEClass, RELAY__PCT_PICKUP46);
		createEAttribute(relayEClass, RELAY__ISQT46);
		createEAttribute(relayEClass, RELAY__VARIABLE);
		createEAttribute(relayEClass, RELAY__OVERTRIP);
		createEAttribute(relayEClass, RELAY__UNDERTRIP);
		createEAttribute(relayEClass, RELAY__BREAKER_TIME);
		createEAttribute(relayEClass, RELAY__ACTION);

		storageControllerEClass = createEClass(STORAGE_CONTROLLER);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__KW_TARGET);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PCT_KW_BAND);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PF_TARGET);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PF_BAND);
		createEReference(storageControllerEClass, STORAGE_CONTROLLER__ELEMENTS);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__WEIGHTS);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__MODE_DISCHARGE);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__MODE_CHARGE);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PCT_RATE_KW);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PCT_RATE_KV_AR);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PCT_RATE_CHARGE);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PCT_RESERVE);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__KWH_TOTAL);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__KW_TOTAL);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__KWH_ACTUAL);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__KW_ACTUAL);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__KW_NEED);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__PCT_PARTICIPATION);
		createEReference(storageControllerEClass, STORAGE_CONTROLLER__YEARLY);
		createEReference(storageControllerEClass, STORAGE_CONTROLLER__DAILY);
		createEReference(storageControllerEClass, STORAGE_CONTROLLER__DUTY);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__EVENT_LOG);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__VAR_DISPATCH);
		createEAttribute(storageControllerEClass, STORAGE_CONTROLLER__INHIBIT_TIME);

		switchControlEClass = createEClass(SWITCH_CONTROL);
		createEAttribute(switchControlEClass, SWITCH_CONTROL__ACTION);
		createEAttribute(switchControlEClass, SWITCH_CONTROL__LOCK);
		createEAttribute(switchControlEClass, SWITCH_CONTROL__DELAY);

		// Create enums
		controlTypeEEnum = createEEnum(CONTROL_TYPE);
		relayTypeEEnum = createEEnum(RELAY_TYPE);
		dischargeModeEEnum = createEEnum(DISCHARGE_MODE);
		chargeModeEEnum = createEEnum(CHARGE_MODE);
		switchActionEEnum = createEEnum(SWITCH_ACTION);
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
		ConversionPackage theConversionPackage = (ConversionPackage)EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI);
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		controlElementEClass.getESuperTypes().add(theCommonPackage.getCircuitElement());
		capacitorControlEClass.getESuperTypes().add(this.getControlElement());
		generatorDispatcherEClass.getESuperTypes().add(this.getControlElement());
		recloserEClass.getESuperTypes().add(this.getControlElement());
		regulatorControlEClass.getESuperTypes().add(this.getControlElement());
		relayEClass.getESuperTypes().add(this.getControlElement());
		storageControllerEClass.getESuperTypes().add(this.getControlElement());
		switchControlEClass.getESuperTypes().add(this.getControlElement());

		// Initialize classes and features; add operations and parameters
		initEClass(controlElementEClass, ControlElement.class, "ControlElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getControlElement_ElementName(), ecorePackage.getEString(), "elementName", null, 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlElement_ElementTerminal(), ecorePackage.getEInt(), "elementTerminal", "1", 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlElement_ControlledBusName(), ecorePackage.getEString(), "controlledBusName", null, 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getControlElement_ControlledBus(), theCommonPackage.getBus(), null, "controlledBus", null, 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlElement_MonitoredVariable(), ecorePackage.getEString(), "monitoredVariable", null, 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlElement_MonitoredVarIndex(), ecorePackage.getEInt(), "monitoredVarIndex", "1", 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlElement_TimeDelay(), ecorePackage.getEDouble(), "timeDelay", null, 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlElement_DblTraceParam(), ecorePackage.getEDouble(), "dblTraceParam", null, 0, 1, ControlElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(capacitorControlEClass, CapacitorControl.class, "CapacitorControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCapacitorControl_Element(), ecorePackage.getEString(), "element", null, 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_Terminal(), ecorePackage.getEInt(), "terminal", "1", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_Capacitor(), ecorePackage.getEString(), "capacitor", null, 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_Type(), this.getcontrolType(), "type", null, 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_PTRatio(), ecorePackage.getEDouble(), "pTRatio", "60.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_CTRatio(), ecorePackage.getEDouble(), "cTRatio", "60.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_OnSetting(), ecorePackage.getEDouble(), "onSetting", "300.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_OffSetting(), ecorePackage.getEDouble(), "offSetting", "200.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_Delay(), ecorePackage.getEDouble(), "delay", "15.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_VoltOverride(), ecorePackage.getEBoolean(), "voltOverride", "false", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_VMax(), ecorePackage.getEDouble(), "vMax", "126.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_VMin(), ecorePackage.getEDouble(), "vMin", "115.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_DelayOff(), ecorePackage.getEDouble(), "delayOff", "15.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitorControl_DeadTime(), ecorePackage.getEDouble(), "deadTime", "300.0", 0, 1, CapacitorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generatorDispatcherEClass, GeneratorDispatcher.class, "GeneratorDispatcher", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneratorDispatcher_Element(), ecorePackage.getEString(), "element", null, 0, 1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorDispatcher_Terminal(), ecorePackage.getEInt(), "terminal", "1", 0, 1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorDispatcher_KWLimit(), ecorePackage.getEDouble(), "kWLimit", "8000.0", 0, 1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorDispatcher_KWBand(), ecorePackage.getEDouble(), "kWBand", "100.0", 0, 1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorDispatcher_KVarLimit(), ecorePackage.getEDouble(), "kVarLimit", null, 0, 1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGeneratorDispatcher_GenList(), theConversionPackage.getGenerator(), null, "genList", null, 0, -1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorDispatcher_Weights(), ecorePackage.getEDouble(), "weights", null, 0, -1, GeneratorDispatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recloserEClass, Recloser.class, "Recloser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRecloser_MonitoredObj(), ecorePackage.getEString(), "monitoredObj", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_MonitoredTerm(), ecorePackage.getEInt(), "monitoredTerm", "1", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_SwitchedObj(), ecorePackage.getEString(), "switchedObj", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_SwitchedTerm(), ecorePackage.getEInt(), "switchedTerm", "1", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_NFast(), ecorePackage.getEInt(), "nFast", "1", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_PhaseFast(), ecorePackage.getEString(), "phaseFast", "A", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_PhaseDelayed(), ecorePackage.getEString(), "phaseDelayed", "D", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_GroundFast(), ecorePackage.getEString(), "groundFast", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_GroundDelayed(), ecorePackage.getEString(), "groundDelayed", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_PhaseTrip(), ecorePackage.getEDouble(), "phaseTrip", "1.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_GroundTrip(), ecorePackage.getEDouble(), "groundTrip", "1.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_PhaseInst(), ecorePackage.getEDouble(), "phaseInst", "1.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_GroundInst(), ecorePackage.getEDouble(), "groundInst", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_Reset(), ecorePackage.getEDouble(), "reset", "15.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_Shots(), ecorePackage.getEInt(), "shots", "4", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_RecloseIntervals(), ecorePackage.getEDouble(), "recloseIntervals", null, 0, -1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_Delay(), ecorePackage.getEDouble(), "delay", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_Action(), theCommonPackage.gettripAction(), "action", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_TDPhFast(), ecorePackage.getEDouble(), "tDPhFast", "1.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_TDGrFast(), ecorePackage.getEDouble(), "tDGrFast", "1.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_TDPhDelayed(), ecorePackage.getEDouble(), "tDPhDelayed", null, 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecloser_TDGrDelayed(), ecorePackage.getEDouble(), "tDGrDelayed", "1.0", 0, 1, Recloser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(regulatorControlEClass, RegulatorControl.class, "RegulatorControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRegulatorControl_Transformer(), ecorePackage.getEString(), "transformer", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_Winding(), ecorePackage.getEInt(), "winding", "1", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_VReg(), ecorePackage.getEDouble(), "vReg", "120.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_Band(), ecorePackage.getEDouble(), "band", "3.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_PTRatio(), ecorePackage.getEDouble(), "pTRatio", "60.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_CTPrim(), ecorePackage.getEDouble(), "cTPrim", "300.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_R(), ecorePackage.getEDouble(), "r", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_X(), ecorePackage.getEDouble(), "x", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_Bus(), ecorePackage.getEString(), "bus", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_Delay(), ecorePackage.getEDouble(), "delay", "15.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_Reversible(), ecorePackage.getEBoolean(), "reversible", "false", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_RevVReg(), ecorePackage.getEDouble(), "revVReg", "120.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_RevBand(), ecorePackage.getEDouble(), "revBand", "3.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_RevR(), ecorePackage.getEDouble(), "revR", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_RevX(), ecorePackage.getEDouble(), "revX", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_TapDelay(), ecorePackage.getEDouble(), "tapDelay", "2.0", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_DebugTrace(), ecorePackage.getEBoolean(), "debugTrace", "false", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_MaxTapChange(), ecorePackage.getEInt(), "maxTapChange", "16", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_InverseTime(), ecorePackage.getEBoolean(), "inverseTime", "false", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_TapWinding(), ecorePackage.getEInt(), "tapWinding", "1", 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRegulatorControl_VLimit(), ecorePackage.getEDouble(), "vLimit", null, 0, 1, RegulatorControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relayEClass, Relay.class, "Relay", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelay_MonitoredObj(), ecorePackage.getEString(), "monitoredObj", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_MonitoredTerm(), ecorePackage.getEInt(), "monitoredTerm", "1", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_SwitchedObj(), ecorePackage.getEString(), "switchedObj", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_SwitchedTerm(), ecorePackage.getEInt(), "switchedTerm", "1", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Type(), this.getrelayType(), "type", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_PhaseCurve(), ecorePackage.getEString(), "phaseCurve", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_GroundCurve(), ecorePackage.getEString(), "groundCurve", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_PhaseTrip(), ecorePackage.getEDouble(), "phaseTrip", "1.0", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_GroundTrip(), ecorePackage.getEDouble(), "groundTrip", "1.0", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_TDPhase(), ecorePackage.getEDouble(), "tDPhase", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_TDGround(), ecorePackage.getEDouble(), "tDGround", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_PhaseInst(), ecorePackage.getEDouble(), "phaseInst", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_GroundInst(), ecorePackage.getEDouble(), "groundInst", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Reset(), ecorePackage.getEDouble(), "reset", "15.0", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Shots(), ecorePackage.getEInt(), "shots", "4", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_RecloseIntervals(), ecorePackage.getEDouble(), "recloseIntervals", null, 0, -1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Delay(), ecorePackage.getEDouble(), "delay", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelay_OvervoltCurve(), theGeneralPackage.getTimeCurrentCurve(), null, "overvoltCurve", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelay_UndervoltCurve(), theGeneralPackage.getTimeCurrentCurve(), null, "undervoltCurve", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_KVBase(), ecorePackage.getEDouble(), "kVBase", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_PctPickup47(), ecorePackage.getEDouble(), "pctPickup47", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_PctAmps46(), ecorePackage.getEDouble(), "pctAmps46", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_PctPickup46(), ecorePackage.getEDouble(), "pctPickup46", "20.0", 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_ISQT46(), ecorePackage.getEDouble(), "iSQT46", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Variable(), ecorePackage.getEString(), "variable", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Overtrip(), ecorePackage.getEDouble(), "overtrip", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Undertrip(), ecorePackage.getEDouble(), "undertrip", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_BreakerTime(), ecorePackage.getEDouble(), "breakerTime", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelay_Action(), theCommonPackage.gettripAction(), "action", null, 0, 1, Relay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(storageControllerEClass, StorageController.class, "StorageController", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStorageController_KWTarget(), ecorePackage.getEDouble(), "kWTarget", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PctKWBand(), ecorePackage.getEDouble(), "pctKWBand", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PFTarget(), ecorePackage.getEDouble(), "pFTarget", "0.96", 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PFBand(), ecorePackage.getEDouble(), "pFBand", "0.04", 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStorageController_Elements(), theConversionPackage.getStorage(), null, "elements", null, 0, -1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_Weights(), ecorePackage.getEDouble(), "weights", "1.0", 0, -1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_ModeDischarge(), this.getdischargeMode(), "modeDischarge", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_ModeCharge(), this.getchargeMode(), "modeCharge", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_TimeDischargeTrigger(), ecorePackage.getEDouble(), "timeDischargeTrigger", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_TimeChargeTrigger(), ecorePackage.getEDouble(), "timeChargeTrigger", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PctRateKW(), ecorePackage.getEDouble(), "pctRateKW", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PctRateKVAr(), ecorePackage.getEDouble(), "pctRateKVAr", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PctRateCharge(), ecorePackage.getEDouble(), "pctRateCharge", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PctReserve(), ecorePackage.getEDouble(), "pctReserve", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_KWhTotal(), ecorePackage.getEDouble(), "kWhTotal", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_KWTotal(), ecorePackage.getEDouble(), "kWTotal", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_KWhActual(), ecorePackage.getEDouble(), "kWhActual", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_KWActual(), ecorePackage.getEDouble(), "kWActual", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_KWNeed(), ecorePackage.getEDouble(), "kWNeed", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_PctParticipation(), ecorePackage.getEDouble(), "pctParticipation", "100.0", 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStorageController_Yearly(), theGeneralPackage.getLoadShape(), null, "yearly", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStorageController_Daily(), theGeneralPackage.getLoadShape(), null, "daily", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStorageController_Duty(), theGeneralPackage.getLoadShape(), null, "duty", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_EventLog(), ecorePackage.getEBoolean(), "eventLog", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_VarDispatch(), ecorePackage.getEBoolean(), "varDispatch", null, 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStorageController_InhibitTime(), ecorePackage.getEInt(), "inhibitTime", "5", 0, 1, StorageController.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(switchControlEClass, SwitchControl.class, "SwitchControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwitchControl_Action(), this.getswitchAction(), "action", null, 0, 1, SwitchControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwitchControl_Lock(), ecorePackage.getEBoolean(), "lock", null, 0, 1, SwitchControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwitchControl_Delay(), ecorePackage.getEDouble(), "delay", "120.0", 0, 1, SwitchControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(controlTypeEEnum, controlType.class, "controlType");
		addEEnumLiteral(controlTypeEEnum, controlType.CURRENT);
		addEEnumLiteral(controlTypeEEnum, controlType.VOLTAGE);
		addEEnumLiteral(controlTypeEEnum, controlType.KV_AR);
		addEEnumLiteral(controlTypeEEnum, controlType.TIME);

		initEEnum(relayTypeEEnum, relayType.class, "relayType");
		addEEnumLiteral(relayTypeEEnum, relayType.CURRENT);
		addEEnumLiteral(relayTypeEEnum, relayType.FORTYSEVEN);
		addEEnumLiteral(relayTypeEEnum, relayType.GENERIC);

		initEEnum(dischargeModeEEnum, dischargeMode.class, "dischargeMode");
		addEEnumLiteral(dischargeModeEEnum, dischargeMode.PEAK_SHAVE);
		addEEnumLiteral(dischargeModeEEnum, dischargeMode.FOLLOW);
		addEEnumLiteral(dischargeModeEEnum, dischargeMode.SUPPORT);
		addEEnumLiteral(dischargeModeEEnum, dischargeMode.LOAD_SHAPE);
		addEEnumLiteral(dischargeModeEEnum, dischargeMode.TIME);

		initEEnum(chargeModeEEnum, chargeMode.class, "chargeMode");
		addEEnumLiteral(chargeModeEEnum, chargeMode.LOAD_SHAPE);
		addEEnumLiteral(chargeModeEEnum, chargeMode.TIME);

		initEEnum(switchActionEEnum, switchAction.class, "switchAction");
		addEEnumLiteral(switchActionEEnum, switchAction.OPEN);
		addEEnumLiteral(switchActionEEnum, switchAction.CLOSE);
	}

} //ControlPackageImpl
