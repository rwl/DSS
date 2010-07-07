/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive.impl;

import electrickery.ElectrickeryPackage;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.control.ControlPackage;
import electrickery.control.impl.ControlPackageImpl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.impl.ConversionPackageImpl;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.impl.DeliveryPackageImpl;
import electrickery.executive.ExecCommands;
import electrickery.executive.ExecOptions;
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
public class ExecutivePackageImpl extends EPackageImpl implements ExecutivePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass execOptionsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass execCommandsEClass = null;

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
        ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
        ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
        DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
        GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
        MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

        // Create package meta-data objects
        theExecutivePackage.createPackageContents();
        theElectrickeryPackage.createPackageContents();
        theCommonPackage.createPackageContents();
        theControlPackage.createPackageContents();
        theConversionPackage.createPackageContents();
        theDeliveryPackage.createPackageContents();
        theGeneralPackage.createPackageContents();
        theMeterPackage.createPackageContents();

        // Initialize created meta-data
        theExecutivePackage.initializePackageContents();
        theElectrickeryPackage.initializePackageContents();
        theCommonPackage.initializePackageContents();
        theControlPackage.initializePackageContents();
        theConversionPackage.initializePackageContents();
        theDeliveryPackage.initializePackageContents();
        theGeneralPackage.initializePackageContents();
        theMeterPackage.initializePackageContents();

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
    public EClass getExecOptions() {
        return execOptionsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Type() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Element() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Hour() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Sec() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Year() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Frequency() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_StepSize() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Mode() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Random() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Number() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Time() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Circuit() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Editor() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Tolerance() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_MaxIter() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_LoadModel() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_LoadMult() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_NormVMinPU() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_NormVMaxPU() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_EmergVMinPU() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_EmergVMaxPU() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_PctMean() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_PctStdDev() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_LDCurve() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_PctGrowth() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_GenKW() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_GenPF() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_CapKVAr() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_AddType() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_AllowDuplicates() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_ZoneLock() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_UEWeight() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_LossWeight() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_UERegs() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_LossRegs() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_VoltageBases() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Algorithm() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Trapezoidal() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(37);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_AutoBusList() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(38);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_ControlMode() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(39);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_TraceMode() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(40);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_GenMult() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(41);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_DefaultDaily() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(42);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_DefaultYearly() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(43);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_AllocationFactor() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(44);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_CktModel() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(45);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_PriceSignal() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(46);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_PriceCurve() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(47);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_Terminal() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(48);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_BaseFrequency() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(49);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Harmonics() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(50);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_MaxController() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(51);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_Bus() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(52);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_DataPath() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(53);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecOptions_KeepList() {
        return (EReference)execOptionsEClass.getEStructuralFeatures().get(54);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_ReduceOption() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(55);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_DemandInterval() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(56);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_PctNormal() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(57);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_DIVerbose() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(58);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_CaseName() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(59);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_MarkerCode() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(60);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_NodeWidth() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(61);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Log() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(62);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_Recorder() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(63);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_OverloadReport() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(64);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecOptions_VoltageExceptionReport() {
        return (EAttribute)execOptionsEClass.getEStructuralFeatures().get(65);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExecCommands() {
        return execCommandsEClass;
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
    public EReference getExecutive_ExecCommands() {
        return (EReference)executiveEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecutive_ExecOptions() {
        return (EReference)executiveEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecutive_ActiveCircuit() {
        return (EReference)executiveEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExecutive_Circuits() {
        return (EReference)executiveEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExecutive_MaxCircuits() {
        return (EAttribute)executiveEClass.getEStructuralFeatures().get(5);
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
        execOptionsEClass = createEClass(EXEC_OPTIONS);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__TYPE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__ELEMENT);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__HOUR);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__SEC);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__YEAR);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__FREQUENCY);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__STEP_SIZE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__MODE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__RANDOM);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__NUMBER);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__TIME);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__CIRCUIT);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__EDITOR);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__TOLERANCE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__MAX_ITER);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__LOAD_MODEL);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__LOAD_MULT);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__NORM_VMIN_PU);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__NORM_VMAX_PU);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__EMERG_VMIN_PU);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__EMERG_VMAX_PU);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__PCT_MEAN);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__PCT_STD_DEV);
        createEReference(execOptionsEClass, EXEC_OPTIONS__LD_CURVE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__PCT_GROWTH);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__GEN_KW);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__GEN_PF);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__CAP_KV_AR);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__ADD_TYPE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__ALLOW_DUPLICATES);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__ZONE_LOCK);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__UE_WEIGHT);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__LOSS_WEIGHT);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__UE_REGS);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__LOSS_REGS);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__VOLTAGE_BASES);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__ALGORITHM);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__TRAPEZOIDAL);
        createEReference(execOptionsEClass, EXEC_OPTIONS__AUTO_BUS_LIST);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__CONTROL_MODE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__TRACE_MODE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__GEN_MULT);
        createEReference(execOptionsEClass, EXEC_OPTIONS__DEFAULT_DAILY);
        createEReference(execOptionsEClass, EXEC_OPTIONS__DEFAULT_YEARLY);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__ALLOCATION_FACTOR);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__CKT_MODEL);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__PRICE_SIGNAL);
        createEReference(execOptionsEClass, EXEC_OPTIONS__PRICE_CURVE);
        createEReference(execOptionsEClass, EXEC_OPTIONS__TERMINAL);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__BASE_FREQUENCY);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__HARMONICS);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__MAX_CONTROLLER);
        createEReference(execOptionsEClass, EXEC_OPTIONS__BUS);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__DATA_PATH);
        createEReference(execOptionsEClass, EXEC_OPTIONS__KEEP_LIST);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__REDUCE_OPTION);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__DEMAND_INTERVAL);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__PCT_NORMAL);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__DI_VERBOSE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__CASE_NAME);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__MARKER_CODE);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__NODE_WIDTH);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__LOG);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__RECORDER);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__OVERLOAD_REPORT);
        createEAttribute(execOptionsEClass, EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT);

        execCommandsEClass = createEClass(EXEC_COMMANDS);

        executiveEClass = createEClass(EXECUTIVE);
        createEAttribute(executiveEClass, EXECUTIVE__COMMAND);
        createEReference(executiveEClass, EXECUTIVE__EXEC_COMMANDS);
        createEReference(executiveEClass, EXECUTIVE__EXEC_OPTIONS);
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
        initEClass(execOptionsEClass, ExecOptions.class, "ExecOptions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExecOptions_Type(), ecorePackage.getEString(), "type", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Element(), ecorePackage.getEString(), "element", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Hour(), ecorePackage.getEInt(), "hour", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Sec(), ecorePackage.getELong(), "sec", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Year(), ecorePackage.getEInt(), "year", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Frequency(), ecorePackage.getEFloat(), "frequency", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_StepSize(), ecorePackage.getEFloat(), "stepSize", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Mode(), this.getsolutionMode(), "mode", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Random(), this.getrandomType(), "random", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Number(), ecorePackage.getEInt(), "number", "2", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Time(), ecorePackage.getEInt(), "time", null, 2, 2, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Circuit(), ecorePackage.getEString(), "circuit", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Editor(), ecorePackage.getEString(), "editor", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Tolerance(), ecorePackage.getEFloat(), "tolerance", "0.0001", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_MaxIter(), ecorePackage.getEInt(), "maxIter", "15", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_LoadModel(), this.getloadModelType(), "loadModel", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_LoadMult(), ecorePackage.getEFloat(), "loadMult", "1.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_NormVMinPU(), ecorePackage.getEFloat(), "normVMinPU", "0.95", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_NormVMaxPU(), ecorePackage.getEFloat(), "normVMaxPU", "1.05", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_EmergVMinPU(), ecorePackage.getEFloat(), "emergVMinPU", "0.90", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_EmergVMaxPU(), ecorePackage.getEFloat(), "emergVMaxPU", "1.08", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_PctMean(), ecorePackage.getEFloat(), "pctMean", "65", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_PctStdDev(), ecorePackage.getEFloat(), "pctStdDev", "9", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_LDCurve(), ecorePackage.getEClass(), null, "LDCurve", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_PctGrowth(), ecorePackage.getEFloat(), "pctGrowth", "2.5", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_GenKW(), ecorePackage.getEFloat(), "genKW", "1000.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_GenPF(), ecorePackage.getEFloat(), "genPF", "1.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_CapKVAr(), ecorePackage.getEFloat(), "capKVAr", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_AddType(), this.getautoAddType(), "addType", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_AllowDuplicates(), ecorePackage.getEBoolean(), "allowDuplicates", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_ZoneLock(), ecorePackage.getEBoolean(), "zoneLock", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_UEWeight(), ecorePackage.getEFloat(), "UEWeight", "1.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_LossWeight(), ecorePackage.getEFloat(), "lossWeight", "1.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_UERegs(), ecorePackage.getEInt(), "UERegs", "11", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_LossRegs(), ecorePackage.getEInt(), "lossRegs", "13", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_VoltageBases(), ecorePackage.getEFloat(), "voltageBases", null, 0, -1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Algorithm(), this.getalgorithmType(), "algorithm", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Trapezoidal(), ecorePackage.getEBoolean(), "trapezoidal", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_AutoBusList(), ecorePackage.getEClass(), null, "autoBusList", null, 0, -1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_ControlMode(), this.getcontrolModeType(), "controlMode", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_TraceMode(), ecorePackage.getEBoolean(), "traceMode", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_GenMult(), ecorePackage.getEFloat(), "genMult", "1.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_DefaultDaily(), ecorePackage.getEClass(), null, "defaultDaily", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_DefaultYearly(), ecorePackage.getEClass(), null, "defaultYearly", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_AllocationFactor(), ecorePackage.getEFloat(), "allocationFactor", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_CktModel(), this.getcircuitModelType(), "cktModel", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_PriceSignal(), ecorePackage.getEFloat(), "priceSignal", "25.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_PriceCurve(), ecorePackage.getEClass(), null, "priceCurve", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_Terminal(), ecorePackage.getEClass(), null, "terminal", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_BaseFrequency(), ecorePackage.getEFloat(), "baseFrequency", "60.0", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Harmonics(), ecorePackage.getEInt(), "harmonics", null, 0, -1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_MaxController(), ecorePackage.getEInt(), "maxController", "10", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_Bus(), ecorePackage.getEClass(), null, "bus", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_DataPath(), ecorePackage.getEString(), "dataPath", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecOptions_KeepList(), ecorePackage.getEClass(), null, "keepList", null, 0, -1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_ReduceOption(), this.getreductionStrategy(), "reduceOption", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_DemandInterval(), ecorePackage.getEBoolean(), "demandInterval", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_PctNormal(), ecorePackage.getEFloat(), "pctNormal", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_DIVerbose(), ecorePackage.getEBoolean(), "DIVerbose", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_CaseName(), ecorePackage.getEString(), "caseName", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_MarkerCode(), ecorePackage.getEString(), "markerCode", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_NodeWidth(), ecorePackage.getEFloat(), "nodeWidth", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Log(), ecorePackage.getEBoolean(), "log", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_Recorder(), ecorePackage.getEBoolean(), "recorder", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_OverloadReport(), ecorePackage.getEBoolean(), "overloadReport", null, 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecOptions_VoltageExceptionReport(), ecorePackage.getEBoolean(), "voltageExceptionReport", "false", 0, 1, ExecOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(execCommandsEClass, ExecCommands.class, "ExecCommands", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        addEOperation(execCommandsEClass, null, "create", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "edit", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "more", 0, 1, IS_UNIQUE, IS_ORDERED);

        EOperation op = addEOperation(execCommandsEClass, null, "select", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "elementName", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEInt(), "terminalNumber", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "save", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "cls", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "dir", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "show", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "option", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "solve", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "enable", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "disable", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "plot", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "reset", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getresetType(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "compile", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "setValue", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "dump", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "cls", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEObject(), "obj", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEBoolean(), "debug", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "open", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "element", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "terminal", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEInt(), "conductor", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "close", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "element", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "terminal", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEInt(), "conductor", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "redirect", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "help", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "quit", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "what", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "element", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEStructuralFeature(), "property", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "next", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getnextType(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "panel", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "sample", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "clear", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "about", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "calcVoltageBases", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "setKvBase", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEClass(), "bus", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEFloat(), "kVLL", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEFloat(), "kVLN", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "buildY", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "initialise", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "export", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getexportType(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "fileEdit", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "voltages", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "currents", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "powers", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "seqVoltages", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "seqCurrents", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "seqPower", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "losses", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "phaseLosses", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "cktLosses", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "allocateLoads", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "formEdit", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEObject(), "obj", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "totals", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEDouble(), "capacity", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "classes", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "userClasses", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "Zsc", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "Zsc10", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "ZscRefresh", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "Ysc", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "puVoltages", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEDouble(), "varValues", 0, -1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, ecorePackage.getEString(), "varNames", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "busCoords", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "makeBusList", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "makePosSequence", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "reduce", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getreduceType(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "interpolate", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "alignFile", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "top", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "rotate", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "vDiff", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "summary", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(execCommandsEClass, null, "distribute", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getdistributionType(), "how", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "diPlot", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "compareCases", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "yearlyCurves", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "visualise", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "closeDI", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(execCommandsEClass, null, "estimate", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(executiveEClass, Executive.class, "Executive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExecutive_Command(), ecorePackage.getEString(), "command", null, 0, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecutive_ExecCommands(), this.getExecCommands(), null, "execCommands", null, 1, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecutive_ExecOptions(), this.getExecOptions(), null, "execOptions", null, 1, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecutive_ActiveCircuit(), theCommonPackage.getCircuit(), null, "activeCircuit", null, 0, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecutive_Circuits(), theCommonPackage.getCircuit(), null, "circuits", null, 0, -1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExecutive_MaxCircuits(), ecorePackage.getEInt(), "maxCircuits", "1", 0, 1, Executive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(executiveEClass, null, "processCommand", 0, 1, IS_UNIQUE, IS_ORDERED);
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
