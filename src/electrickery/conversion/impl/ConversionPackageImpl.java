/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;

import electrickery.ElectrickeryPackage;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.control.ControlPackage;
import electrickery.control.impl.ControlPackageImpl;
import electrickery.conversion.ConversionFactory;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.CurrentSource;
import electrickery.conversion.Equivalent;
import electrickery.conversion.Generator;
import electrickery.conversion.Load;
import electrickery.conversion.PowerConversionElement;
import electrickery.conversion.VoltageSource;
import electrickery.conversion.dispatchType;
import electrickery.conversion.generatorModel;
import electrickery.conversion.generatorStatus;
import electrickery.conversion.loadModel;
import electrickery.conversion.loadSpecType;
import electrickery.conversion.loadStatus;
import electrickery.conversion.sequenceType;
import electrickery.conversion.specType;
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
public class ConversionPackageImpl extends EPackageImpl implements ConversionPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass currentSourceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass equivalentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass generatorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass loadEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass powerConversionElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass voltageSourceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum sequenceTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum generatorModelEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum dispatchTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum generatorStatusEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum loadModelEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum loadStatusEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum loadSpecTypeEEnum = null;

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum specTypeEEnum = null;

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
     * @see electrickery.conversion.ConversionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConversionPackageImpl() {
        super(eNS_URI, ConversionFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ConversionPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConversionPackage init() {
        if (isInited) return (ConversionPackage)EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI);

        // Obtain or create and register package
        ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConversionPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        ElectrickeryPackageImpl theElectrickeryPackage = (ElectrickeryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) instanceof ElectrickeryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) : ElectrickeryPackage.eINSTANCE);
        CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
        ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
        DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
        ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
        GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
        MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

        // Create package meta-data objects
        theConversionPackage.createPackageContents();
        theElectrickeryPackage.createPackageContents();
        theCommonPackage.createPackageContents();
        theControlPackage.createPackageContents();
        theDeliveryPackage.createPackageContents();
        theExecutivePackage.createPackageContents();
        theGeneralPackage.createPackageContents();
        theMeterPackage.createPackageContents();

        // Initialize created meta-data
        theConversionPackage.initializePackageContents();
        theElectrickeryPackage.initializePackageContents();
        theCommonPackage.initializePackageContents();
        theControlPackage.initializePackageContents();
        theDeliveryPackage.initializePackageContents();
        theExecutivePackage.initializePackageContents();
        theGeneralPackage.initializePackageContents();
        theMeterPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConversionPackage.freeze();


        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ConversionPackage.eNS_URI, theConversionPackage);
        return theConversionPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCurrentSource() {
        return currentSourceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCurrentSource_Bus1() {
        return (EAttribute)currentSourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCurrentSource_Amps() {
        return (EAttribute)currentSourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCurrentSource_Angle() {
        return (EAttribute)currentSourceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCurrentSource_Frequency() {
        return (EAttribute)currentSourceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCurrentSource_Phases() {
        return (EAttribute)currentSourceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCurrentSource_ScanType() {
        return (EAttribute)currentSourceEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEquivalent() {
        return equivalentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_Buses() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_BaseKV() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_Pu() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_Angle() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_Frequency() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_Phases() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_R1() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_X1() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_R0() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEquivalent_X0() {
        return (EAttribute)equivalentEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGenerator() {
        return generatorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGenerator_Circuit() {
        return (EReference)generatorEClass.getEStructuralFeatures().get(0);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Bus1() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_KV() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_KW() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Pf() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_KVAr() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Model() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VMinPU() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VMaxPU() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Yearly() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Daily() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Duty() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_DispMode() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_DispValue() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Conn() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_RNeut() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_XNeut() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Status() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Class() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VPU() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VTarget() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(20);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_MaxKVAr() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_MinKVAr() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_PvFactor() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_ForceOn() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_KVA() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_MVA() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_XD() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_XDp() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_XDpp() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_H() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_D() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_UserModel() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_UserData() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_ShaftModel() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_ShaftData() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_DebugTrace() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_GenOn() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(37);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_ShapeFactor() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(38);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_ForcedOn() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(39);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_Fixed() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(40);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_YEq() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(41);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_YEq95() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(42);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_YEq105() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(43);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VBase() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(44);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VBase95() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(45);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VBase105() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(46);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VarBase() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(47);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VarMin() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(48);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_VarMax() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(49);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_DeltaQMax() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(50);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_DQdV() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(51);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_DQdVSaved() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(52);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGenerator_YQFixed() {
        return (EAttribute)generatorEClass.getEStructuralFeatures().get(53);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLoad() {
        return loadEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLoad_Circuit() {
        return (EReference)loadEClass.getEStructuralFeatures().get(0);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLoad_GrowthShapeObj() {
        return (EReference)loadEClass.getEStructuralFeatures().get(1);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Bus1() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_KV() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_KW() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Model() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_LoadSpec() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(8);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Yearly() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Daily() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Duty() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Growth() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Conn() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_KVAr() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_PF() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(6);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_RNeut() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_XNeut() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Status() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_Class() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_VMinPU() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_VMaxPU() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_VMinNorm() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_VMinEmerg() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_XfKVA() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_AllocationFactor() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_KVA() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_PctMean() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_PctStdDev() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_CvrWatts() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLoad_CvrVars() {
        return (EAttribute)loadEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPowerConversionElement() {
        return powerConversionElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPowerConversionElement_Spectrum() {
        return (EAttribute)powerConversionElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPowerConversionElement_SpectrumObj() {
        return (EReference)powerConversionElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPowerConversionElement_InjCurrent() {
        return (EAttribute)powerConversionElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVoltageSource() {
        return voltageSourceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_Bus1() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_BaseKV() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_PerUnit() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(2);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_Angle() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_Frequency() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_MvaSC3() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_MvaSC1() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_X1R1() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_X0R0() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_ISC3() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_ISC1() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_R1() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_X1() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_R0() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_X0() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_ScanType() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVoltageSource_Z() {
        return (EReference)voltageSourceEClass.getEStructuralFeatures().get(16);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getVoltageSource_ZInv() {
        return (EReference)voltageSourceEClass.getEStructuralFeatures().get(17);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_ZSpecType() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(18);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVoltageSource_VMag() {
        return (EAttribute)voltageSourceEClass.getEStructuralFeatures().get(19);
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getsequenceType() {
        return sequenceTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getgeneratorModel() {
        return generatorModelEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getdispatchType() {
        return dispatchTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getgeneratorStatus() {
        return generatorStatusEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getloadModel() {
        return loadModelEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getloadStatus() {
        return loadStatusEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getloadSpecType() {
        return loadSpecTypeEEnum;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getspecType() {
        return specTypeEEnum;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConversionFactory getConversionFactory() {
        return (ConversionFactory)getEFactoryInstance();
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
        powerConversionElementEClass = createEClass(POWER_CONVERSION_ELEMENT);
        createEAttribute(powerConversionElementEClass, POWER_CONVERSION_ELEMENT__SPECTRUM);
        createEReference(powerConversionElementEClass, POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ);
        createEAttribute(powerConversionElementEClass, POWER_CONVERSION_ELEMENT__INJ_CURRENT);

        currentSourceEClass = createEClass(CURRENT_SOURCE);
        createEAttribute(currentSourceEClass, CURRENT_SOURCE__BUS1);
        createEAttribute(currentSourceEClass, CURRENT_SOURCE__AMPS);
        createEAttribute(currentSourceEClass, CURRENT_SOURCE__ANGLE);
        createEAttribute(currentSourceEClass, CURRENT_SOURCE__FREQUENCY);
        createEAttribute(currentSourceEClass, CURRENT_SOURCE__PHASES);
        createEAttribute(currentSourceEClass, CURRENT_SOURCE__SCAN_TYPE);

        equivalentEClass = createEClass(EQUIVALENT);
        createEAttribute(equivalentEClass, EQUIVALENT__BUSES);
        createEAttribute(equivalentEClass, EQUIVALENT__BASE_KV);
        createEAttribute(equivalentEClass, EQUIVALENT__PU);
        createEAttribute(equivalentEClass, EQUIVALENT__ANGLE);
        createEAttribute(equivalentEClass, EQUIVALENT__FREQUENCY);
        createEAttribute(equivalentEClass, EQUIVALENT__PHASES);
        createEAttribute(equivalentEClass, EQUIVALENT__R1);
        createEAttribute(equivalentEClass, EQUIVALENT__X1);
        createEAttribute(equivalentEClass, EQUIVALENT__R0);
        createEAttribute(equivalentEClass, EQUIVALENT__X0);

        generatorEClass = createEClass(GENERATOR);
        createEReference(generatorEClass, GENERATOR__CIRCUIT);
        createEAttribute(generatorEClass, GENERATOR__BUS1);
        createEAttribute(generatorEClass, GENERATOR__KV);
        createEAttribute(generatorEClass, GENERATOR__KW);
        createEAttribute(generatorEClass, GENERATOR__PF);
        createEAttribute(generatorEClass, GENERATOR__KV_AR);
        createEAttribute(generatorEClass, GENERATOR__MODEL);
        createEAttribute(generatorEClass, GENERATOR__VMIN_PU);
        createEAttribute(generatorEClass, GENERATOR__VMAX_PU);
        createEAttribute(generatorEClass, GENERATOR__YEARLY);
        createEAttribute(generatorEClass, GENERATOR__DAILY);
        createEAttribute(generatorEClass, GENERATOR__DUTY);
        createEAttribute(generatorEClass, GENERATOR__DISP_MODE);
        createEAttribute(generatorEClass, GENERATOR__DISP_VALUE);
        createEAttribute(generatorEClass, GENERATOR__CONN);
        createEAttribute(generatorEClass, GENERATOR__RNEUT);
        createEAttribute(generatorEClass, GENERATOR__XNEUT);
        createEAttribute(generatorEClass, GENERATOR__STATUS);
        createEAttribute(generatorEClass, GENERATOR__CLASS);
        createEAttribute(generatorEClass, GENERATOR__VPU);
        createEAttribute(generatorEClass, GENERATOR__VTARGET);
        createEAttribute(generatorEClass, GENERATOR__MAX_KV_AR);
        createEAttribute(generatorEClass, GENERATOR__MIN_KV_AR);
        createEAttribute(generatorEClass, GENERATOR__PV_FACTOR);
        createEAttribute(generatorEClass, GENERATOR__FORCE_ON);
        createEAttribute(generatorEClass, GENERATOR__KVA);
        createEAttribute(generatorEClass, GENERATOR__MVA);
        createEAttribute(generatorEClass, GENERATOR__XD);
        createEAttribute(generatorEClass, GENERATOR__XDP);
        createEAttribute(generatorEClass, GENERATOR__XDPP);
        createEAttribute(generatorEClass, GENERATOR__H);
        createEAttribute(generatorEClass, GENERATOR__D);
        createEAttribute(generatorEClass, GENERATOR__USER_MODEL);
        createEAttribute(generatorEClass, GENERATOR__USER_DATA);
        createEAttribute(generatorEClass, GENERATOR__SHAFT_MODEL);
        createEAttribute(generatorEClass, GENERATOR__SHAFT_DATA);
        createEAttribute(generatorEClass, GENERATOR__DEBUG_TRACE);
        createEAttribute(generatorEClass, GENERATOR__GEN_ON);
        createEAttribute(generatorEClass, GENERATOR__SHAPE_FACTOR);
        createEAttribute(generatorEClass, GENERATOR__FORCED_ON);
        createEAttribute(generatorEClass, GENERATOR__FIXED);
        createEAttribute(generatorEClass, GENERATOR__YEQ);
        createEAttribute(generatorEClass, GENERATOR__YEQ95);
        createEAttribute(generatorEClass, GENERATOR__YEQ105);
        createEAttribute(generatorEClass, GENERATOR__VBASE);
        createEAttribute(generatorEClass, GENERATOR__VBASE95);
        createEAttribute(generatorEClass, GENERATOR__VBASE105);
        createEAttribute(generatorEClass, GENERATOR__VAR_BASE);
        createEAttribute(generatorEClass, GENERATOR__VAR_MIN);
        createEAttribute(generatorEClass, GENERATOR__VAR_MAX);
        createEAttribute(generatorEClass, GENERATOR__DELTA_QMAX);
        createEAttribute(generatorEClass, GENERATOR__DQD_V);
        createEAttribute(generatorEClass, GENERATOR__DQD_VSAVED);
        createEAttribute(generatorEClass, GENERATOR__YQ_FIXED);

        loadEClass = createEClass(LOAD);
        createEReference(loadEClass, LOAD__CIRCUIT);
        createEReference(loadEClass, LOAD__GROWTH_SHAPE_OBJ);
        createEAttribute(loadEClass, LOAD__BUS1);
        createEAttribute(loadEClass, LOAD__KV);
        createEAttribute(loadEClass, LOAD__KW);
        createEAttribute(loadEClass, LOAD__KV_AR);
        createEAttribute(loadEClass, LOAD__PF);
        createEAttribute(loadEClass, LOAD__MODEL);
        createEAttribute(loadEClass, LOAD__LOAD_SPEC);
        createEAttribute(loadEClass, LOAD__YEARLY);
        createEAttribute(loadEClass, LOAD__DAILY);
        createEAttribute(loadEClass, LOAD__DUTY);
        createEAttribute(loadEClass, LOAD__GROWTH);
        createEAttribute(loadEClass, LOAD__CONN);
        createEAttribute(loadEClass, LOAD__RNEUT);
        createEAttribute(loadEClass, LOAD__XNEUT);
        createEAttribute(loadEClass, LOAD__STATUS);
        createEAttribute(loadEClass, LOAD__CLASS);
        createEAttribute(loadEClass, LOAD__VMIN_PU);
        createEAttribute(loadEClass, LOAD__VMAX_PU);
        createEAttribute(loadEClass, LOAD__VMIN_NORM);
        createEAttribute(loadEClass, LOAD__VMIN_EMERG);
        createEAttribute(loadEClass, LOAD__XF_KVA);
        createEAttribute(loadEClass, LOAD__ALLOCATION_FACTOR);
        createEAttribute(loadEClass, LOAD__KVA);
        createEAttribute(loadEClass, LOAD__PCT_MEAN);
        createEAttribute(loadEClass, LOAD__PCT_STD_DEV);
        createEAttribute(loadEClass, LOAD__CVR_WATTS);
        createEAttribute(loadEClass, LOAD__CVR_VARS);

        voltageSourceEClass = createEClass(VOLTAGE_SOURCE);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__BUS1);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__BASE_KV);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__PER_UNIT);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__ANGLE);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__FREQUENCY);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__MVA_SC3);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__MVA_SC1);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__X1_R1);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__X0_R0);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__ISC3);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__ISC1);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__R1);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__X1);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__R0);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__X0);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__SCAN_TYPE);
        createEReference(voltageSourceEClass, VOLTAGE_SOURCE__Z);
        createEReference(voltageSourceEClass, VOLTAGE_SOURCE__ZINV);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__ZSPEC_TYPE);
        createEAttribute(voltageSourceEClass, VOLTAGE_SOURCE__VMAG);

        // Create enums
        sequenceTypeEEnum = createEEnum(SEQUENCE_TYPE);
        generatorModelEEnum = createEEnum(GENERATOR_MODEL);
        dispatchTypeEEnum = createEEnum(DISPATCH_TYPE);
        generatorStatusEEnum = createEEnum(GENERATOR_STATUS);
        loadModelEEnum = createEEnum(LOAD_MODEL);
        loadStatusEEnum = createEEnum(LOAD_STATUS);
        loadSpecTypeEEnum = createEEnum(LOAD_SPEC_TYPE);
        specTypeEEnum = createEEnum(SPEC_TYPE);
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
        GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);
        ElectrickeryPackage theElectrickeryPackage = (ElectrickeryPackage)EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        powerConversionElementEClass.getESuperTypes().add(theCommonPackage.getCircuitElement());
        currentSourceEClass.getESuperTypes().add(this.getPowerConversionElement());
        equivalentEClass.getESuperTypes().add(this.getPowerConversionElement());
        generatorEClass.getESuperTypes().add(this.getPowerConversionElement());
        loadEClass.getESuperTypes().add(this.getPowerConversionElement());
        voltageSourceEClass.getESuperTypes().add(this.getPowerConversionElement());

        // Initialize classes and features; add operations and parameters
        initEClass(powerConversionElementEClass, PowerConversionElement.class, "PowerConversionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPowerConversionElement_Spectrum(), ecorePackage.getEString(), "spectrum", null, 0, 1, PowerConversionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPowerConversionElement_SpectrumObj(), theGeneralPackage.getSpectrum(), null, "spectrumObj", null, 0, 1, PowerConversionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPowerConversionElement_InjCurrent(), ecorePackage.getEDouble(), "injCurrent", null, 0, 1, PowerConversionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(powerConversionElementEClass, null, "recalcElementData", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(powerConversionElementEClass, ecorePackage.getEInt(), "injCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

        EOperation op = addEOperation(powerConversionElementEClass, null, "getCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "curr", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(powerConversionElementEClass, null, "getInjCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theElectrickeryPackage.getDComplexMatrix1D(), "curr", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(currentSourceEClass, CurrentSource.class, "CurrentSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCurrentSource_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, CurrentSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCurrentSource_Amps(), ecorePackage.getEDouble(), "amps", null, 0, 1, CurrentSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCurrentSource_Angle(), ecorePackage.getEDouble(), "angle", null, 0, 1, CurrentSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCurrentSource_Frequency(), ecorePackage.getEDouble(), "frequency", "60.0", 0, 1, CurrentSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCurrentSource_Phases(), ecorePackage.getEInt(), "phases", null, 0, 1, CurrentSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCurrentSource_ScanType(), this.getsequenceType(), "scanType", null, 0, 1, CurrentSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(currentSourceEClass, ecorePackage.getEDouble(), "getBaseCurrent", 2, 2, IS_UNIQUE, IS_ORDERED);

        initEClass(equivalentEClass, Equivalent.class, "Equivalent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEquivalent_Buses(), ecorePackage.getEString(), "buses", null, 0, -1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_BaseKV(), ecorePackage.getEDouble(), "baseKV", "115.0", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_Pu(), ecorePackage.getEDouble(), "pu", "1.0", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_Angle(), ecorePackage.getEDouble(), "angle", null, 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_Frequency(), ecorePackage.getEDouble(), "frequency", "60.0", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_Phases(), ecorePackage.getEInt(), "phases", "3", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_R1(), ecorePackage.getEDouble(), "r1", "1.65", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_X1(), ecorePackage.getEDouble(), "x1", "6.6", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_R0(), ecorePackage.getEDouble(), "r0", "1.9", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEquivalent_X0(), ecorePackage.getEDouble(), "x0", "5.7", 0, 1, Equivalent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(generatorEClass, Generator.class, "Generator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGenerator_Circuit(), theCommonPackage.getCircuit(), theCommonPackage.getCircuit_Generators(), "circuit", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_KV(), ecorePackage.getEDouble(), "kV", "12.47", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_KW(), ecorePackage.getEDouble(), "kW", "100.0", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Pf(), ecorePackage.getEDouble(), "pf", "0.8", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_KVAr(), ecorePackage.getEDouble(), "kVAr", "5.0", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Model(), this.getgeneratorModel(), "model", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VMinPU(), ecorePackage.getEDouble(), "vMinPU", "0.95", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VMaxPU(), ecorePackage.getEDouble(), "vMaxPU", "1.05", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Yearly(), ecorePackage.getEString(), "yearly", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Daily(), ecorePackage.getEString(), "daily", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Duty(), ecorePackage.getEString(), "duty", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_DispMode(), this.getdispatchType(), "dispMode", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_DispValue(), ecorePackage.getEDouble(), "dispValue", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Conn(), theCommonPackage.getconnectionType(), "conn", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_RNeut(), ecorePackage.getEDouble(), "rNeut", "-1.0", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_XNeut(), ecorePackage.getEDouble(), "xNeut", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Status(), this.getgeneratorStatus(), "status", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Class(), ecorePackage.getEInt(), "class", "1", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VPU(), ecorePackage.getEDouble(), "vPU", "1.0", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VTarget(), ecorePackage.getEDouble(), "vTarget", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_MaxKVAr(), ecorePackage.getEDouble(), "maxKVAr", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_MinKVAr(), ecorePackage.getEDouble(), "minKVAr", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_PvFactor(), ecorePackage.getEDouble(), "pvFactor", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_ForceOn(), ecorePackage.getEBoolean(), "forceOn", "false", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_KVA(), ecorePackage.getEDouble(), "kVA", "1.2", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_MVA(), ecorePackage.getEDouble(), "MVA", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_XD(), ecorePackage.getEDouble(), "xD", "1.0", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_XDp(), ecorePackage.getEDouble(), "xDp", "0.27", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_XDpp(), ecorePackage.getEDouble(), "xDpp", "0.2", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_H(), ecorePackage.getEDouble(), "h", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_D(), ecorePackage.getEDouble(), "d", "1.0", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_UserModel(), ecorePackage.getEString(), "userModel", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_UserData(), ecorePackage.getEString(), "userData", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_ShaftModel(), ecorePackage.getEString(), "shaftModel", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_ShaftData(), ecorePackage.getEString(), "shaftData", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_DebugTrace(), ecorePackage.getEBoolean(), "debugTrace", "false", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_GenOn(), ecorePackage.getEBoolean(), "genOn", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_ShapeFactor(), theElectrickeryPackage.getComplex(), "shapeFactor", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_ForcedOn(), ecorePackage.getEBoolean(), "forcedOn", "false", 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_Fixed(), ecorePackage.getEBoolean(), "fixed", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_YEq(), theElectrickeryPackage.getComplex(), "yEq", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_YEq95(), theElectrickeryPackage.getComplex(), "yEq95", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_YEq105(), theElectrickeryPackage.getComplex(), "yEq105", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VBase(), ecorePackage.getEDouble(), "vBase", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VBase95(), ecorePackage.getEDouble(), "vBase95", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VBase105(), ecorePackage.getEDouble(), "vBase105", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VarBase(), ecorePackage.getEDouble(), "varBase", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VarMin(), ecorePackage.getEDouble(), "varMin", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_VarMax(), ecorePackage.getEDouble(), "varMax", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_DeltaQMax(), ecorePackage.getEDouble(), "deltaQMax", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_DQdV(), ecorePackage.getEDouble(), "dQdV", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_DQdVSaved(), ecorePackage.getEDouble(), "dQdVSaved", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGenerator_YQFixed(), ecorePackage.getEDouble(), "yQFixed", null, 0, 1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(generatorEClass, null, "setNominalGeneration", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(loadEClass, Load.class, "Load", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLoad_Circuit(), theCommonPackage.getCircuit(), null, "circuit", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLoad_GrowthShapeObj(), theGeneralPackage.getGrowthShape(), null, "growthShapeObj", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_KV(), ecorePackage.getEDouble(), "kV", "12.47", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_KW(), ecorePackage.getEDouble(), "kW", "10.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_KVAr(), ecorePackage.getEDouble(), "kVAr", "5.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_PF(), ecorePackage.getEDouble(), "pF", "0.88", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Model(), this.getloadModel(), "model", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_LoadSpec(), this.getloadSpecType(), "loadSpec", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Yearly(), ecorePackage.getEString(), "yearly", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Daily(), ecorePackage.getEString(), "daily", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Duty(), ecorePackage.getEString(), "duty", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Growth(), ecorePackage.getEString(), "growth", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Conn(), theCommonPackage.getconnectionType(), "conn", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_RNeut(), ecorePackage.getEDouble(), "rNeut", "-1.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_XNeut(), ecorePackage.getEDouble(), "xNeut", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Status(), this.getloadStatus(), "status", "Variable", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_Class(), ecorePackage.getEInt(), "class", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_VMinPU(), ecorePackage.getEDouble(), "vMinPU", "0.95", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_VMaxPU(), ecorePackage.getEDouble(), "vMaxPU", "1.05", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_VMinNorm(), ecorePackage.getEDouble(), "vMinNorm", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_VMinEmerg(), ecorePackage.getEDouble(), "vMinEmerg", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_XfKVA(), ecorePackage.getEDouble(), "xfKVA", null, 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_AllocationFactor(), ecorePackage.getEDouble(), "allocationFactor", "0.5", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_KVA(), ecorePackage.getEDouble(), "kVA", "11.3636", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_PctMean(), ecorePackage.getEDouble(), "pctMean", "50.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_PctStdDev(), ecorePackage.getEDouble(), "pctStdDev", "10.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_CvrWatts(), ecorePackage.getEDouble(), "cvrWatts", "1.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLoad_CvrVars(), ecorePackage.getEDouble(), "cvrVars", "2.0", 0, 1, Load.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(voltageSourceEClass, VoltageSource.class, "VoltageSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVoltageSource_Bus1(), ecorePackage.getEString(), "bus1", null, 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_BaseKV(), ecorePackage.getEDouble(), "baseKV", "115.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_PerUnit(), ecorePackage.getEDouble(), "perUnit", "1.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_Angle(), ecorePackage.getEDouble(), "angle", null, 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_Frequency(), ecorePackage.getEDouble(), "frequency", "60.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_MvaSC3(), ecorePackage.getEDouble(), "mvaSC3", "2000.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_MvaSC1(), ecorePackage.getEDouble(), "mvaSC1", "2100.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_X1R1(), ecorePackage.getEDouble(), "x1R1", "4.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_X0R0(), ecorePackage.getEDouble(), "x0R0", "3.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_ISC3(), ecorePackage.getEDouble(), "iSC3", "10000.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_ISC1(), ecorePackage.getEDouble(), "iSC1", "10500.0", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_R1(), ecorePackage.getEDouble(), "r1", "1.65", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_X1(), ecorePackage.getEDouble(), "x1", "6.6", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_R0(), ecorePackage.getEDouble(), "r0", "1.9", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_X0(), ecorePackage.getEDouble(), "x0", "5.7", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_ScanType(), this.getsequenceType(), "scanType", null, 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVoltageSource_Z(), theElectrickeryPackage.getDComplexMatrix2D(), null, "z", null, 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVoltageSource_ZInv(), theElectrickeryPackage.getDComplexMatrix2D(), null, "zInv", null, 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_ZSpecType(), this.getspecType(), "zSpecType", "MVAsc", 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVoltageSource_VMag(), ecorePackage.getEDouble(), "vMag", null, 0, 1, VoltageSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(sequenceTypeEEnum, sequenceType.class, "sequenceType");
        addEEnumLiteral(sequenceTypeEEnum, sequenceType.POSITIVE);
        addEEnumLiteral(sequenceTypeEEnum, sequenceType.ZERO);
        addEEnumLiteral(sequenceTypeEEnum, sequenceType.NONE);

        initEEnum(generatorModelEEnum, generatorModel.class, "generatorModel");
        addEEnumLiteral(generatorModelEEnum, generatorModel.CONSTANT_KW);
        addEEnumLiteral(generatorModelEEnum, generatorModel.CONSTANT_Y);
        addEEnumLiteral(generatorModelEEnum, generatorModel.CONSTANT_KW_AND_KV);
        addEEnumLiteral(generatorModelEEnum, generatorModel.CONST_KW_FIXED_Q);
        addEEnumLiteral(generatorModelEEnum, generatorModel.CONST_KW_FIXED_QCONST_X);
        addEEnumLiteral(generatorModelEEnum, generatorModel.USER_MODEL);
        addEEnumLiteral(generatorModelEEnum, generatorModel.CONST_KW_KV_AR_LIMITED_I);

        initEEnum(dispatchTypeEEnum, dispatchType.class, "dispatchType");
        addEEnumLiteral(dispatchTypeEEnum, dispatchType.LOAD_MODE);
        addEEnumLiteral(dispatchTypeEEnum, dispatchType.PRICE_MODE);

        initEEnum(generatorStatusEEnum, generatorStatus.class, "generatorStatus");
        addEEnumLiteral(generatorStatusEEnum, generatorStatus.VARIABLE);
        addEEnumLiteral(generatorStatusEEnum, generatorStatus.FIXED);

        initEEnum(loadModelEEnum, loadModel.class, "loadModel");
        addEEnumLiteral(loadModelEEnum, loadModel.PQ);
        addEEnumLiteral(loadModelEEnum, loadModel.CONST_Y);
        addEEnumLiteral(loadModelEEnum, loadModel.MOTOR);
        addEEnumLiteral(loadModelEEnum, loadModel.LINEAR_PQUAD_Q);
        addEEnumLiteral(loadModelEEnum, loadModel.CONST_I);
        addEEnumLiteral(loadModelEEnum, loadModel.CONST_PFIXED_Q);
        addEEnumLiteral(loadModelEEnum, loadModel.CONST_PFIXED_Z);

        initEEnum(loadStatusEEnum, loadStatus.class, "loadStatus");
        addEEnumLiteral(loadStatusEEnum, loadStatus.VARIABLE);
        addEEnumLiteral(loadStatusEEnum, loadStatus.FIXED);
        addEEnumLiteral(loadStatusEEnum, loadStatus.EXEMPT);

        initEEnum(loadSpecTypeEEnum, loadSpecType.class, "loadSpecType");
        addEEnumLiteral(loadSpecTypeEEnum, loadSpecType.KW_PF);
        addEEnumLiteral(loadSpecTypeEEnum, loadSpecType.KW_KVAR);
        addEEnumLiteral(loadSpecTypeEEnum, loadSpecType.KVA_PF);

        initEEnum(specTypeEEnum, specType.class, "specType");
        addEEnumLiteral(specTypeEEnum, specType.MVA_SC);
        addEEnumLiteral(specTypeEEnum, specType.ISC);
        addEEnumLiteral(specTypeEEnum, specType.Z1Z0);
    }

} //ConversionPackageImpl
