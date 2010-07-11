/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery.impl;

import electrickery.ElectrickeryPackage;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.control.ControlPackage;
import electrickery.control.impl.ControlPackageImpl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.impl.ConversionPackageImpl;
import electrickery.delivery.Capacitor;
import electrickery.delivery.DeliveryFactory;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.Fault;
import electrickery.delivery.Fuse;
import electrickery.delivery.Line;
import electrickery.delivery.PowerDeliveryElement;
import electrickery.delivery.Reactor;
import electrickery.delivery.Transformer;
import electrickery.executive.ExecutivePackage;
import electrickery.executive.impl.ExecutivePackageImpl;
import electrickery.general.GeneralPackage;
import electrickery.general.impl.GeneralPackageImpl;
import electrickery.impl.ElectrickeryPackageImpl;
import electrickery.meter.MeterPackage;
import electrickery.meter.impl.MeterPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeliveryPackageImpl extends EPackageImpl implements DeliveryPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass powerDeliveryElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass capacitorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass faultEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass fuseEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass lineEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass reactorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass transformerEClass = null;

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
	 * @see electrickery.delivery.DeliveryPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private DeliveryPackageImpl() {
		super(eNS_URI, DeliveryFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DeliveryPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static DeliveryPackage init() {
		if (isInited) return (DeliveryPackage)EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI);

		// Obtain or create and register package
		DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DeliveryPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ElectrickeryPackageImpl theElectrickeryPackage = (ElectrickeryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) instanceof ElectrickeryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) : ElectrickeryPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

		// Create package meta-data objects
		theDeliveryPackage.createPackageContents();
		theElectrickeryPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theExecutivePackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theMeterPackage.createPackageContents();

		// Initialize created meta-data
		theDeliveryPackage.initializePackageContents();
		theElectrickeryPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theMeterPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDeliveryPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DeliveryPackage.eNS_URI, theDeliveryPackage);
		return theDeliveryPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPowerDeliveryElement() {
		return powerDeliveryElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPowerDeliveryElement_NormAmps() {
		return (EAttribute)powerDeliveryElementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPowerDeliveryElement_EmergAmps() {
		return (EAttribute)powerDeliveryElementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPowerDeliveryElement_FaultRate() {
		return (EAttribute)powerDeliveryElementEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPowerDeliveryElement_PctPerm() {
		return (EAttribute)powerDeliveryElementEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPowerDeliveryElement_Repair() {
		return (EAttribute)powerDeliveryElementEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCapacitor() {
		return capacitorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_Bus1() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_Bus2() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_KVAr() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_KV() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_Conn() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCapacitor_CMatrix() {
		return (EReference)capacitorEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_Cuf() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_R() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_Xl() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_Harm() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_NSteps() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCapacitor_States() {
		return (EAttribute)capacitorEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFault() {
		return faultEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_Bus1() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_Bus2() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_R() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_PctStdDev() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getFault_GMatrix() {
		return (EReference)faultEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_OnTime() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_Temporary() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFault_MinAmps() {
		return (EAttribute)faultEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFuse() {
		return fuseEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_MonitoredObj() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_MonitorTerm() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_SwitchedObj() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_SwitchedTerm() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_FuseCurve() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_RatedCurrent() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_Delay() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFuse_Action() {
		return (EAttribute)fuseEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLine() {
		return lineEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Bus1() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Bus2() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_LineCode() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Length() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_R1() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_X1() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_R0() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_X0() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_C1() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_C0() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLine_RMatrix() {
		return (EReference)lineEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLine_XMatrix() {
		return (EReference)lineEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLine_CMatrix() {
		return (EReference)lineEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Switch() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Rg() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Xg() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Rho() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Geometry() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLine_Units() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLine_Spacing() {
		return (EReference)lineEClass.getEStructuralFeatures().get(19);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLine_Wires() {
		return (EReference)lineEClass.getEStructuralFeatures().get(20);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getReactor() {
		return reactorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_Bus1() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_Bus2() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_KVAr() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_KV() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_Conn() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getReactor_RMatrix() {
		return (EReference)reactorEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getReactor_XMatrix() {
		return (EReference)reactorEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_Parallel() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_R() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_X() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getReactor_Rp() {
		return (EAttribute)reactorEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getTransformer() {
		return transformerEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Windings() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Wdg() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Bus() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Conn() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_KV() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_KVA() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Tap() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_RPct() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_RNeut() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_XNeut() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Buses() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Conns() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_KVs() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_KVAs() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Taps() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_XHL() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_XHT() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_XLT() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_XSCArray() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Thermal() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(19);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_N() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(20);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_M() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(21);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_FLRise() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(22);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_HSRise() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(23);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_PctLoadLoss() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(24);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_PctNoLoadLoss() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(25);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_NormHKVa() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(26);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_EmergHKVa() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(27);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_Substation() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(28);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_MaxTap() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(29);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_MinTap() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(30);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_NumTaps() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(31);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_SubName() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(32);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_PctImage() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(33);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTransformer_PpmAntiFloat() {
		return (EAttribute)transformerEClass.getEStructuralFeatures().get(34);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DeliveryFactory getDeliveryFactory() {
		return (DeliveryFactory)getEFactoryInstance();
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
		powerDeliveryElementEClass = createEClass(POWER_DELIVERY_ELEMENT);
		createEAttribute(powerDeliveryElementEClass, POWER_DELIVERY_ELEMENT__NORM_AMPS);
		createEAttribute(powerDeliveryElementEClass, POWER_DELIVERY_ELEMENT__EMERG_AMPS);
		createEAttribute(powerDeliveryElementEClass, POWER_DELIVERY_ELEMENT__FAULT_RATE);
		createEAttribute(powerDeliveryElementEClass, POWER_DELIVERY_ELEMENT__PCT_PERM);
		createEAttribute(powerDeliveryElementEClass, POWER_DELIVERY_ELEMENT__REPAIR);

		capacitorEClass = createEClass(CAPACITOR);
		createEAttribute(capacitorEClass, CAPACITOR__BUS1);
		createEAttribute(capacitorEClass, CAPACITOR__BUS2);
		createEAttribute(capacitorEClass, CAPACITOR__KV_AR);
		createEAttribute(capacitorEClass, CAPACITOR__KV);
		createEAttribute(capacitorEClass, CAPACITOR__CONN);
		createEReference(capacitorEClass, CAPACITOR__CMATRIX);
		createEAttribute(capacitorEClass, CAPACITOR__CUF);
		createEAttribute(capacitorEClass, CAPACITOR__R);
		createEAttribute(capacitorEClass, CAPACITOR__XL);
		createEAttribute(capacitorEClass, CAPACITOR__HARM);
		createEAttribute(capacitorEClass, CAPACITOR__NSTEPS);
		createEAttribute(capacitorEClass, CAPACITOR__STATES);

		faultEClass = createEClass(FAULT);
		createEAttribute(faultEClass, FAULT__BUS1);
		createEAttribute(faultEClass, FAULT__BUS2);
		createEAttribute(faultEClass, FAULT__R);
		createEAttribute(faultEClass, FAULT__PCT_STD_DEV);
		createEReference(faultEClass, FAULT__GMATRIX);
		createEAttribute(faultEClass, FAULT__ON_TIME);
		createEAttribute(faultEClass, FAULT__TEMPORARY);
		createEAttribute(faultEClass, FAULT__MIN_AMPS);

		fuseEClass = createEClass(FUSE);
		createEAttribute(fuseEClass, FUSE__MONITORED_OBJ);
		createEAttribute(fuseEClass, FUSE__MONITOR_TERM);
		createEAttribute(fuseEClass, FUSE__SWITCHED_OBJ);
		createEAttribute(fuseEClass, FUSE__SWITCHED_TERM);
		createEAttribute(fuseEClass, FUSE__FUSE_CURVE);
		createEAttribute(fuseEClass, FUSE__RATED_CURRENT);
		createEAttribute(fuseEClass, FUSE__DELAY);
		createEAttribute(fuseEClass, FUSE__ACTION);

		lineEClass = createEClass(LINE);
		createEAttribute(lineEClass, LINE__BUS1);
		createEAttribute(lineEClass, LINE__BUS2);
		createEAttribute(lineEClass, LINE__LINE_CODE);
		createEAttribute(lineEClass, LINE__LENGTH);
		createEAttribute(lineEClass, LINE__R1);
		createEAttribute(lineEClass, LINE__X1);
		createEAttribute(lineEClass, LINE__R0);
		createEAttribute(lineEClass, LINE__X0);
		createEAttribute(lineEClass, LINE__C1);
		createEAttribute(lineEClass, LINE__C0);
		createEReference(lineEClass, LINE__RMATRIX);
		createEReference(lineEClass, LINE__XMATRIX);
		createEReference(lineEClass, LINE__CMATRIX);
		createEAttribute(lineEClass, LINE__SWITCH);
		createEAttribute(lineEClass, LINE__RG);
		createEAttribute(lineEClass, LINE__XG);
		createEAttribute(lineEClass, LINE__RHO);
		createEAttribute(lineEClass, LINE__GEOMETRY);
		createEAttribute(lineEClass, LINE__UNITS);
		createEReference(lineEClass, LINE__SPACING);
		createEReference(lineEClass, LINE__WIRES);

		reactorEClass = createEClass(REACTOR);
		createEAttribute(reactorEClass, REACTOR__BUS1);
		createEAttribute(reactorEClass, REACTOR__BUS2);
		createEAttribute(reactorEClass, REACTOR__KV_AR);
		createEAttribute(reactorEClass, REACTOR__KV);
		createEAttribute(reactorEClass, REACTOR__CONN);
		createEReference(reactorEClass, REACTOR__RMATRIX);
		createEReference(reactorEClass, REACTOR__XMATRIX);
		createEAttribute(reactorEClass, REACTOR__PARALLEL);
		createEAttribute(reactorEClass, REACTOR__R);
		createEAttribute(reactorEClass, REACTOR__X);
		createEAttribute(reactorEClass, REACTOR__RP);

		transformerEClass = createEClass(TRANSFORMER);
		createEAttribute(transformerEClass, TRANSFORMER__WINDINGS);
		createEAttribute(transformerEClass, TRANSFORMER__WDG);
		createEAttribute(transformerEClass, TRANSFORMER__BUS);
		createEAttribute(transformerEClass, TRANSFORMER__CONN);
		createEAttribute(transformerEClass, TRANSFORMER__KV);
		createEAttribute(transformerEClass, TRANSFORMER__KVA);
		createEAttribute(transformerEClass, TRANSFORMER__TAP);
		createEAttribute(transformerEClass, TRANSFORMER__RPCT);
		createEAttribute(transformerEClass, TRANSFORMER__RNEUT);
		createEAttribute(transformerEClass, TRANSFORMER__XNEUT);
		createEAttribute(transformerEClass, TRANSFORMER__BUSES);
		createEAttribute(transformerEClass, TRANSFORMER__CONNS);
		createEAttribute(transformerEClass, TRANSFORMER__KVS);
		createEAttribute(transformerEClass, TRANSFORMER__KV_AS);
		createEAttribute(transformerEClass, TRANSFORMER__TAPS);
		createEAttribute(transformerEClass, TRANSFORMER__XHL);
		createEAttribute(transformerEClass, TRANSFORMER__XHT);
		createEAttribute(transformerEClass, TRANSFORMER__XLT);
		createEAttribute(transformerEClass, TRANSFORMER__XSC_ARRAY);
		createEAttribute(transformerEClass, TRANSFORMER__THERMAL);
		createEAttribute(transformerEClass, TRANSFORMER__N);
		createEAttribute(transformerEClass, TRANSFORMER__M);
		createEAttribute(transformerEClass, TRANSFORMER__FL_RISE);
		createEAttribute(transformerEClass, TRANSFORMER__HS_RISE);
		createEAttribute(transformerEClass, TRANSFORMER__PCT_LOAD_LOSS);
		createEAttribute(transformerEClass, TRANSFORMER__PCT_NO_LOAD_LOSS);
		createEAttribute(transformerEClass, TRANSFORMER__NORM_HK_VA);
		createEAttribute(transformerEClass, TRANSFORMER__EMERG_HK_VA);
		createEAttribute(transformerEClass, TRANSFORMER__SUBSTATION);
		createEAttribute(transformerEClass, TRANSFORMER__MAX_TAP);
		createEAttribute(transformerEClass, TRANSFORMER__MIN_TAP);
		createEAttribute(transformerEClass, TRANSFORMER__NUM_TAPS);
		createEAttribute(transformerEClass, TRANSFORMER__SUB_NAME);
		createEAttribute(transformerEClass, TRANSFORMER__PCT_IMAGE);
		createEAttribute(transformerEClass, TRANSFORMER__PPM_ANTI_FLOAT);
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
		ElectrickeryPackage theElectrickeryPackage = (ElectrickeryPackage)EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI);
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		powerDeliveryElementEClass.getESuperTypes().add(theCommonPackage.getCircuitElement());
		capacitorEClass.getESuperTypes().add(this.getPowerDeliveryElement());
		faultEClass.getESuperTypes().add(this.getPowerDeliveryElement());
		fuseEClass.getESuperTypes().add(this.getPowerDeliveryElement());
		lineEClass.getESuperTypes().add(this.getPowerDeliveryElement());
		reactorEClass.getESuperTypes().add(this.getPowerDeliveryElement());
		transformerEClass.getESuperTypes().add(this.getPowerDeliveryElement());

		// Initialize classes and features; add operations and parameters
		initEClass(powerDeliveryElementEClass, PowerDeliveryElement.class, "PowerDeliveryElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPowerDeliveryElement_NormAmps(), ecorePackage.getEDouble(), "normAmps", "400.0", 0, 1, PowerDeliveryElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowerDeliveryElement_EmergAmps(), ecorePackage.getEDouble(), "emergAmps", "600.0", 0, 1, PowerDeliveryElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowerDeliveryElement_FaultRate(), ecorePackage.getEDouble(), "faultRate", "0.1", 0, 1, PowerDeliveryElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowerDeliveryElement_PctPerm(), ecorePackage.getEDouble(), "pctPerm", "20.0", 0, 1, PowerDeliveryElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowerDeliveryElement_Repair(), ecorePackage.getEInt(), "repair", "3", 0, 1, PowerDeliveryElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(capacitorEClass, Capacitor.class, "Capacitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCapacitor_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_Bus2(), ecorePackage.getEString(), "bus2", null, 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_KVAr(), ecorePackage.getEDouble(), "kVAr", "1200.0", 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_KV(), ecorePackage.getEDouble(), "kV", "12.47", 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_Conn(), theCommonPackage.getconnectionType(), "conn", "Wye", 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCapacitor_CMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "cMatrix", null, 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_Cuf(), ecorePackage.getEDouble(), "cuf", null, 0, -1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_R(), ecorePackage.getEDouble(), "r", null, 0, -1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_Xl(), ecorePackage.getEDouble(), "xl", null, 0, -1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_Harm(), ecorePackage.getEDouble(), "harm", null, 0, -1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_NSteps(), ecorePackage.getEInt(), "nSteps", "1", 0, 1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCapacitor_States(), ecorePackage.getEBoolean(), "states", null, 0, -1, Capacitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(faultEClass, Fault.class, "Fault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFault_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFault_Bus2(), ecorePackage.getEString(), "bus2", null, 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFault_R(), ecorePackage.getEDouble(), "r", null, 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFault_PctStdDev(), ecorePackage.getEDouble(), "pctStdDev", null, 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFault_GMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "gMatrix", null, 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFault_OnTime(), ecorePackage.getEDouble(), "onTime", null, 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFault_Temporary(), ecorePackage.getEBoolean(), "temporary", "false", 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFault_MinAmps(), ecorePackage.getEDouble(), "minAmps", "5.0", 0, 1, Fault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fuseEClass, Fuse.class, "Fuse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFuse_MonitoredObj(), ecorePackage.getEString(), "monitoredObj", null, 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_MonitorTerm(), ecorePackage.getEInt(), "monitorTerm", "1", 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_SwitchedObj(), ecorePackage.getEString(), "switchedObj", null, 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_SwitchedTerm(), ecorePackage.getEInt(), "switchedTerm", "1", 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_FuseCurve(), ecorePackage.getEString(), "fuseCurve", null, 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_RatedCurrent(), ecorePackage.getEDouble(), "ratedCurrent", "1.0", 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_Delay(), ecorePackage.getEDouble(), "delay", null, 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFuse_Action(), theCommonPackage.gettripAction(), "action", null, 0, 1, Fuse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lineEClass, Line.class, "Line", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLine_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Bus2(), ecorePackage.getEString(), "bus2", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_LineCode(), ecorePackage.getEString(), "lineCode", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Length(), ecorePackage.getEDouble(), "length", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_R1(), ecorePackage.getEDouble(), "r1", "0.058", 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_X1(), ecorePackage.getEDouble(), "x1", "0.1206", 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_R0(), ecorePackage.getEDouble(), "r0", "0.1784", 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_X0(), ecorePackage.getEDouble(), "x0", "0.4047", 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_C1(), ecorePackage.getEDouble(), "c1", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_C0(), ecorePackage.getEDouble(), "c0", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLine_RMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "rMatrix", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLine_XMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "xMatrix", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLine_CMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "cMatrix", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Switch(), ecorePackage.getEBoolean(), "switch", "false", 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Rg(), ecorePackage.getEDouble(), "rg", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Xg(), ecorePackage.getEDouble(), "xg", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Rho(), ecorePackage.getEDouble(), "rho", "100.0", 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Geometry(), ecorePackage.getEString(), "geometry", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLine_Units(), theCommonPackage.getlengthUnit(), "units", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLine_Spacing(), theGeneralPackage.getLineSpacing(), null, "spacing", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLine_Wires(), theGeneralPackage.getWireData(), null, "wires", null, 0, -1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reactorEClass, Reactor.class, "Reactor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReactor_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_Bus2(), ecorePackage.getEString(), "bus2", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_KVAr(), ecorePackage.getEDouble(), "kVAr", "1200.0", 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_KV(), ecorePackage.getEDouble(), "kV", "12.47", 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_Conn(), theCommonPackage.getconnectionType(), "conn", "Wye", 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReactor_RMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "rMatrix", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReactor_XMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "xMatrix", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_Parallel(), ecorePackage.getEBoolean(), "parallel", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_R(), ecorePackage.getEDouble(), "r", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_X(), ecorePackage.getEDouble(), "x", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReactor_Rp(), ecorePackage.getEDouble(), "rp", null, 0, 1, Reactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformerEClass, Transformer.class, "Transformer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformer_Windings(), ecorePackage.getEInt(), "windings", "2", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Wdg(), ecorePackage.getEInt(), "wdg", "1", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Bus(), ecorePackage.getEString(), "bus", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Conn(), theCommonPackage.getconnectionType(), "conn", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_KV(), ecorePackage.getEDouble(), "kV", "12.47", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_KVA(), ecorePackage.getEDouble(), "kVA", "1000.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Tap(), ecorePackage.getEDouble(), "tap", "1.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_RPct(), ecorePackage.getEDouble(), "rPct", "0.2", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_RNeut(), ecorePackage.getEDouble(), "rNeut", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_XNeut(), ecorePackage.getEDouble(), "xNeut", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Buses(), ecorePackage.getEString(), "buses", null, 0, -1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Conns(), theCommonPackage.getconnectionType(), "conns", null, 0, -1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_KVs(), ecorePackage.getEDouble(), "kVs", null, 0, -1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_KVAs(), ecorePackage.getEDouble(), "kVAs", null, 0, -1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Taps(), ecorePackage.getEDouble(), "taps", null, 0, -1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_XHL(), ecorePackage.getEDouble(), "xHL", "7.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_XHT(), ecorePackage.getEDouble(), "xHT", "35.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_XLT(), ecorePackage.getEDouble(), "xLT", "30.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_XSCArray(), ecorePackage.getEDouble(), "xSCArray", null, 0, -1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Thermal(), ecorePackage.getEDouble(), "thermal", "2.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_N(), ecorePackage.getEDouble(), "n", "0.8", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_M(), ecorePackage.getEDouble(), "m", "0.8", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_FLRise(), ecorePackage.getEDouble(), "fLRise", "65.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_HSRise(), ecorePackage.getEDouble(), "hSRise", "15.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_PctLoadLoss(), ecorePackage.getEDouble(), "pctLoadLoss", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_PctNoLoadLoss(), ecorePackage.getEDouble(), "pctNoLoadLoss", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_NormHKVa(), ecorePackage.getEDouble(), "normHKVa", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_EmergHKVa(), ecorePackage.getEDouble(), "emergHKVa", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_Substation(), ecorePackage.getEBoolean(), "substation", "false", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_MaxTap(), ecorePackage.getEDouble(), "maxTap", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_MinTap(), ecorePackage.getEDouble(), "minTap", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_NumTaps(), ecorePackage.getEInt(), "numTaps", "32", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_SubName(), ecorePackage.getEString(), "subName", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_PctImage(), ecorePackage.getEDouble(), "pctImage", null, 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformer_PpmAntiFloat(), ecorePackage.getEDouble(), "ppmAntiFloat", "1.0", 0, 1, Transformer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //DeliveryPackageImpl
