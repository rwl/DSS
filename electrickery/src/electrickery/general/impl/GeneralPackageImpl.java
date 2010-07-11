/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.impl;

import electrickery.ElectrickeryPackage;
import electrickery.common.CommonPackage;
import electrickery.common.impl.CommonPackageImpl;
import electrickery.control.ControlPackage;
import electrickery.control.impl.ControlPackageImpl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.impl.ConversionPackageImpl;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.impl.DeliveryPackageImpl;
import electrickery.executive.ExecutivePackage;
import electrickery.executive.impl.ExecutivePackageImpl;
import electrickery.general.DSSObject;
import electrickery.general.GeneralFactory;
import electrickery.general.GeneralPackage;
import electrickery.general.GrowthShape;
import electrickery.general.LineCode;
import electrickery.general.LineGeometry;
import electrickery.general.LineSpacing;
import electrickery.general.LoadShape;
import electrickery.general.Spectrum;
import electrickery.general.TimeCurrentCurve;
import electrickery.general.WireData;
import electrickery.impl.ElectrickeryPackageImpl;
import electrickery.meter.MeterPackage;
import electrickery.meter.impl.MeterPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
public class GeneralPackageImpl extends EPackageImpl implements GeneralPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass dssObjectEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass growthShapeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass lineCodeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass lineGeometryEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lineSpacingEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass loadShapeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass spectrumEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass timeCurrentCurveEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass wireDataEClass = null;

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
	 * @see electrickery.general.GeneralPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private GeneralPackageImpl() {
		super(eNS_URI, GeneralFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GeneralPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static GeneralPackage init() {
		if (isInited) return (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);

		// Obtain or create and register package
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GeneralPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ElectrickeryPackageImpl theElectrickeryPackage = (ElectrickeryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) instanceof ElectrickeryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ElectrickeryPackage.eNS_URI) : ElectrickeryPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
		MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

		// Create package meta-data objects
		theGeneralPackage.createPackageContents();
		theElectrickeryPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theDeliveryPackage.createPackageContents();
		theExecutivePackage.createPackageContents();
		theMeterPackage.createPackageContents();

		// Initialize created meta-data
		theGeneralPackage.initializePackageContents();
		theElectrickeryPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theDeliveryPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();
		theMeterPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGeneralPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GeneralPackage.eNS_URI, theGeneralPackage);
		return theGeneralPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDSSObject() {
		return dssObjectEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDSSObject_DSSObjType() {
		return (EAttribute)dssObjectEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDSSObject_DSSClassName() {
		return (EAttribute)dssObjectEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDSSObject_ParentClass() {
		return (EReference)dssObjectEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDSSObject_ClassIndex() {
		return (EAttribute)dssObjectEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDSSObject_Dirty() {
		return (EAttribute)dssObjectEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDSSObject_Flag() {
		return (EAttribute)dssObjectEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDSSObject_PropSequence() {
		return (EAttribute)dssObjectEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGrowthShape() {
		return growthShapeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGrowthShape_NPts() {
		return (EAttribute)growthShapeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGrowthShape_Year() {
		return (EAttribute)growthShapeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGrowthShape_CsvFile() {
		return (EAttribute)growthShapeEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGrowthShape_SngFile() {
		return (EAttribute)growthShapeEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGrowthShape_DblFile() {
		return (EAttribute)growthShapeEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLineCode() {
		return lineCodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_NPhases() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_R1() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_X1() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineCode_R0() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_X0() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_C1() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_C0() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_Units() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLineCode_RMatrix() {
		return (EReference)lineCodeEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLineCode_XMatrix() {
		return (EReference)lineCodeEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLineCode_CMatrix() {
		return (EReference)lineCodeEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_BaseFreq() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_NormAmps() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_EmergAmps() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_FaultRate() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_PctPerm() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(15);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_Repair() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(16);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_Kron() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(17);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_Rg() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(18);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_Xg() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(19);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineCode_Rho() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(20);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineCode_Neutral() {
		return (EAttribute)lineCodeEClass.getEStructuralFeatures().get(21);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLineCode_Like() {
		return (EReference)lineCodeEClass.getEStructuralFeatures().get(22);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLineGeometry() {
		return lineGeometryEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_NConds() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_NPhases() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_Cond() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLineGeometry_Wire() {
		return (EReference)lineGeometryEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLineGeometry_Wires() {
		return (EReference)lineGeometryEClass.getEStructuralFeatures().get(11);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLineGeometry_Like() {
		return (EReference)lineGeometryEClass.getEStructuralFeatures().get(12);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLineGeometry_Spacing() {
		return (EReference)lineGeometryEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_X() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_H() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_Units() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_NormAmps() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_EmergAmps() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLineGeometry_Reduce() {
		return (EAttribute)lineGeometryEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLineSpacing() {
		return lineSpacingEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineSpacing_NConds() {
		return (EAttribute)lineSpacingEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineSpacing_NPhases() {
		return (EAttribute)lineSpacingEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineSpacing_X() {
		return (EAttribute)lineSpacingEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineSpacing_H() {
		return (EAttribute)lineSpacingEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLineSpacing_Units() {
		return (EAttribute)lineSpacingEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLineSpacing_Like() {
		return (EReference)lineSpacingEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLoadShape() {
		return loadShapeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_NPts() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_Interval() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_Mult() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_Hour() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_Mean() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_StdDev() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_CsvFile() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_SngFile() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_DblFile() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLoadShape_QMult() {
		return (EAttribute)loadShapeEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSpectrum() {
		return spectrumEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSpectrum_NHarm() {
		return (EAttribute)spectrumEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSpectrum_Harmonic() {
		return (EAttribute)spectrumEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSpectrum_PctMag() {
		return (EAttribute)spectrumEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSpectrum_Angle() {
		return (EAttribute)spectrumEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSpectrum_CsvFile() {
		return (EAttribute)spectrumEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getTimeCurrentCurve() {
		return timeCurrentCurveEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTimeCurrentCurve_NPts() {
		return (EAttribute)timeCurrentCurveEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTimeCurrentCurve_CArray() {
		return (EAttribute)timeCurrentCurveEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTimeCurrentCurve_TArray() {
		return (EAttribute)timeCurrentCurveEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getWireData() {
		return wireDataEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_RDC() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_RAC() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_RUnits() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_GmrAC() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_GmrUnits() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_Radius() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_RadUnits() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_NormAmps() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_EmergAmps() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWireData_Diameter() {
		return (EAttribute)wireDataEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GeneralFactory getGeneralFactory() {
		return (GeneralFactory)getEFactoryInstance();
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
		dssObjectEClass = createEClass(DSS_OBJECT);
		createEAttribute(dssObjectEClass, DSS_OBJECT__DSS_OBJ_TYPE);
		createEAttribute(dssObjectEClass, DSS_OBJECT__DSS_CLASS_NAME);
		createEReference(dssObjectEClass, DSS_OBJECT__PARENT_CLASS);
		createEAttribute(dssObjectEClass, DSS_OBJECT__CLASS_INDEX);
		createEAttribute(dssObjectEClass, DSS_OBJECT__DIRTY);
		createEAttribute(dssObjectEClass, DSS_OBJECT__FLAG);
		createEAttribute(dssObjectEClass, DSS_OBJECT__PROP_SEQUENCE);

		growthShapeEClass = createEClass(GROWTH_SHAPE);
		createEAttribute(growthShapeEClass, GROWTH_SHAPE__NPTS);
		createEAttribute(growthShapeEClass, GROWTH_SHAPE__YEAR);
		createEAttribute(growthShapeEClass, GROWTH_SHAPE__CSV_FILE);
		createEAttribute(growthShapeEClass, GROWTH_SHAPE__SNG_FILE);
		createEAttribute(growthShapeEClass, GROWTH_SHAPE__DBL_FILE);

		lineCodeEClass = createEClass(LINE_CODE);
		createEAttribute(lineCodeEClass, LINE_CODE__NPHASES);
		createEAttribute(lineCodeEClass, LINE_CODE__R1);
		createEAttribute(lineCodeEClass, LINE_CODE__X1);
		createEAttribute(lineCodeEClass, LINE_CODE__R0);
		createEAttribute(lineCodeEClass, LINE_CODE__X0);
		createEAttribute(lineCodeEClass, LINE_CODE__C1);
		createEAttribute(lineCodeEClass, LINE_CODE__C0);
		createEAttribute(lineCodeEClass, LINE_CODE__UNITS);
		createEReference(lineCodeEClass, LINE_CODE__RMATRIX);
		createEReference(lineCodeEClass, LINE_CODE__XMATRIX);
		createEReference(lineCodeEClass, LINE_CODE__CMATRIX);
		createEAttribute(lineCodeEClass, LINE_CODE__BASE_FREQ);
		createEAttribute(lineCodeEClass, LINE_CODE__NORM_AMPS);
		createEAttribute(lineCodeEClass, LINE_CODE__EMERG_AMPS);
		createEAttribute(lineCodeEClass, LINE_CODE__FAULT_RATE);
		createEAttribute(lineCodeEClass, LINE_CODE__PCT_PERM);
		createEAttribute(lineCodeEClass, LINE_CODE__REPAIR);
		createEAttribute(lineCodeEClass, LINE_CODE__KRON);
		createEAttribute(lineCodeEClass, LINE_CODE__RG);
		createEAttribute(lineCodeEClass, LINE_CODE__XG);
		createEAttribute(lineCodeEClass, LINE_CODE__RHO);
		createEAttribute(lineCodeEClass, LINE_CODE__NEUTRAL);
		createEReference(lineCodeEClass, LINE_CODE__LIKE);

		lineGeometryEClass = createEClass(LINE_GEOMETRY);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__NCONDS);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__NPHASES);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__COND);
		createEReference(lineGeometryEClass, LINE_GEOMETRY__WIRE);
		createEReference(lineGeometryEClass, LINE_GEOMETRY__SPACING);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__X);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__H);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__UNITS);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__NORM_AMPS);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__EMERG_AMPS);
		createEAttribute(lineGeometryEClass, LINE_GEOMETRY__REDUCE);
		createEReference(lineGeometryEClass, LINE_GEOMETRY__WIRES);
		createEReference(lineGeometryEClass, LINE_GEOMETRY__LIKE);

		lineSpacingEClass = createEClass(LINE_SPACING);
		createEAttribute(lineSpacingEClass, LINE_SPACING__NCONDS);
		createEAttribute(lineSpacingEClass, LINE_SPACING__NPHASES);
		createEAttribute(lineSpacingEClass, LINE_SPACING__X);
		createEAttribute(lineSpacingEClass, LINE_SPACING__H);
		createEAttribute(lineSpacingEClass, LINE_SPACING__UNITS);
		createEReference(lineSpacingEClass, LINE_SPACING__LIKE);

		loadShapeEClass = createEClass(LOAD_SHAPE);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__NPTS);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__INTERVAL);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__MULT);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__HOUR);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__MEAN);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__STD_DEV);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__CSV_FILE);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__SNG_FILE);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__DBL_FILE);
		createEAttribute(loadShapeEClass, LOAD_SHAPE__QMULT);

		spectrumEClass = createEClass(SPECTRUM);
		createEAttribute(spectrumEClass, SPECTRUM__NHARM);
		createEAttribute(spectrumEClass, SPECTRUM__HARMONIC);
		createEAttribute(spectrumEClass, SPECTRUM__PCT_MAG);
		createEAttribute(spectrumEClass, SPECTRUM__ANGLE);
		createEAttribute(spectrumEClass, SPECTRUM__CSV_FILE);

		timeCurrentCurveEClass = createEClass(TIME_CURRENT_CURVE);
		createEAttribute(timeCurrentCurveEClass, TIME_CURRENT_CURVE__NPTS);
		createEAttribute(timeCurrentCurveEClass, TIME_CURRENT_CURVE__CARRAY);
		createEAttribute(timeCurrentCurveEClass, TIME_CURRENT_CURVE__TARRAY);

		wireDataEClass = createEClass(WIRE_DATA);
		createEAttribute(wireDataEClass, WIRE_DATA__RDC);
		createEAttribute(wireDataEClass, WIRE_DATA__RAC);
		createEAttribute(wireDataEClass, WIRE_DATA__RUNITS);
		createEAttribute(wireDataEClass, WIRE_DATA__GMR_AC);
		createEAttribute(wireDataEClass, WIRE_DATA__GMR_UNITS);
		createEAttribute(wireDataEClass, WIRE_DATA__RADIUS);
		createEAttribute(wireDataEClass, WIRE_DATA__RAD_UNITS);
		createEAttribute(wireDataEClass, WIRE_DATA__NORM_AMPS);
		createEAttribute(wireDataEClass, WIRE_DATA__EMERG_AMPS);
		createEAttribute(wireDataEClass, WIRE_DATA__DIAMETER);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		lineGeometryEClass.getESuperTypes().add(theCommonPackage.getNamed());
		wireDataEClass.getESuperTypes().add(theCommonPackage.getNamed());

		// Initialize classes and features; add operations and parameters
		initEClass(dssObjectEClass, DSSObject.class, "DSSObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDSSObject_DSSObjType(), ecorePackage.getEInt(), "DSSObjType", null, 0, 1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDSSObject_DSSClassName(), ecorePackage.getEString(), "DSSClassName", null, 0, 1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDSSObject_ParentClass(), ecorePackage.getEClass(), null, "parentClass", null, 0, -1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDSSObject_ClassIndex(), ecorePackage.getEInt(), "classIndex", null, 0, 1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDSSObject_Dirty(), ecorePackage.getEBoolean(), "dirty", null, 0, 1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDSSObject_Flag(), ecorePackage.getEBoolean(), "flag", null, 0, 1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDSSObject_PropSequence(), ecorePackage.getEDouble(), "propSequence", null, 0, -1, DSSObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(growthShapeEClass, GrowthShape.class, "GrowthShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGrowthShape_NPts(), ecorePackage.getEInt(), "nPts", null, 0, 1, GrowthShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrowthShape_Year(), ecorePackage.getEDouble(), "year", null, 0, -1, GrowthShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrowthShape_CsvFile(), ecorePackage.getEString(), "csvFile", null, 0, 1, GrowthShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrowthShape_SngFile(), ecorePackage.getEString(), "sngFile", null, 0, 1, GrowthShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGrowthShape_DblFile(), ecorePackage.getEString(), "dblFile", null, 0, 1, GrowthShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(growthShapeEClass, ecorePackage.getEDouble(), "getMult", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "year", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(lineCodeEClass, LineCode.class, "LineCode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLineCode_NPhases(), ecorePackage.getEInt(), "nPhases", "3", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_R1(), ecorePackage.getEDouble(), "r1", "0.058", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_X1(), ecorePackage.getEDouble(), "x1", "0.1206", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_R0(), ecorePackage.getEDouble(), "r0", "0.4047", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_X0(), ecorePackage.getEDouble(), "x0", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_C1(), ecorePackage.getEDouble(), "c1", "3.4", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_C0(), ecorePackage.getEDouble(), "c0", "1.6", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Units(), theCommonPackage.getlengthUnit(), "units", "none", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineCode_RMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "rMatrix", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineCode_XMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "xMatrix", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineCode_CMatrix(), theElectrickeryPackage.getDoubleMatrix2D(), null, "cMatrix", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_BaseFreq(), ecorePackage.getEDouble(), "baseFreq", "60.0", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_NormAmps(), ecorePackage.getEDouble(), "normAmps", "400.0", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_EmergAmps(), ecorePackage.getEDouble(), "emergAmps", "600.0", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_FaultRate(), ecorePackage.getEDouble(), "faultRate", "0.1", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_PctPerm(), ecorePackage.getEInt(), "pctPerm", "20", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Repair(), ecorePackage.getEInt(), "repair", "3", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Kron(), ecorePackage.getEBoolean(), "kron", "false", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Rg(), ecorePackage.getEDouble(), "rg", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Xg(), ecorePackage.getEDouble(), "xg", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Rho(), ecorePackage.getEDouble(), "rho", "100.0", 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineCode_Neutral(), ecorePackage.getEInt(), "neutral", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineCode_Like(), this.getLineCode(), null, "like", null, 0, 1, LineCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lineGeometryEClass, LineGeometry.class, "LineGeometry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLineGeometry_NConds(), ecorePackage.getEInt(), "nConds", "3", 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_NPhases(), ecorePackage.getEInt(), "nPhases", "3", 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_Cond(), ecorePackage.getEInt(), "cond", "1", 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineGeometry_Wire(), this.getWireData(), null, "wire", null, 1, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineGeometry_Spacing(), this.getLineSpacing(), null, "spacing", null, 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_X(), ecorePackage.getEDouble(), "x", null, 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_H(), ecorePackage.getEDouble(), "h", "32.0", 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_Units(), theCommonPackage.getlengthUnit(), "units", "ft", 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_NormAmps(), ecorePackage.getEDouble(), "normAmps", null, 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_EmergAmps(), ecorePackage.getEDouble(), "emergAmps", null, 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineGeometry_Reduce(), ecorePackage.getEBoolean(), "reduce", "false", 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineGeometry_Wires(), this.getWireData(), null, "wires", null, 0, -1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineGeometry_Like(), this.getLineGeometry(), null, "like", null, 0, 1, LineGeometry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lineSpacingEClass, LineSpacing.class, "LineSpacing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLineSpacing_NConds(), ecorePackage.getEInt(), "nConds", null, 0, 1, LineSpacing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineSpacing_NPhases(), ecorePackage.getEInt(), "nPhases", null, 0, 1, LineSpacing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineSpacing_X(), ecorePackage.getEDouble(), "x", null, 0, -1, LineSpacing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineSpacing_H(), ecorePackage.getEDouble(), "h", null, 0, -1, LineSpacing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLineSpacing_Units(), theCommonPackage.getlengthUnit(), "units", null, 0, 1, LineSpacing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLineSpacing_Like(), this.getLineSpacing(), null, "like", null, 0, 1, LineSpacing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(loadShapeEClass, LoadShape.class, "LoadShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLoadShape_NPts(), ecorePackage.getEInt(), "nPts", null, 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_Interval(), ecorePackage.getEInt(), "interval", "1", 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_Mult(), ecorePackage.getEDouble(), "mult", null, 0, -1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_Hour(), ecorePackage.getEDouble(), "hour", null, 0, -1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_Mean(), ecorePackage.getEDouble(), "mean", null, 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_StdDev(), ecorePackage.getEDouble(), "stdDev", null, 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_CsvFile(), ecorePackage.getEString(), "csvFile", null, 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_SngFile(), ecorePackage.getEString(), "sngFile", null, 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_DblFile(), ecorePackage.getEString(), "dblFile", null, 0, 1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadShape_QMult(), ecorePackage.getEDouble(), "qMult", null, 0, -1, LoadShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(loadShapeEClass, null, "normalise", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(spectrumEClass, Spectrum.class, "Spectrum", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpectrum_NHarm(), ecorePackage.getEInt(), "nHarm", null, 0, 1, Spectrum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpectrum_Harmonic(), ecorePackage.getEDouble(), "harmonic", null, 0, -1, Spectrum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpectrum_PctMag(), ecorePackage.getEDouble(), "pctMag", null, 0, 1, Spectrum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpectrum_Angle(), ecorePackage.getEDouble(), "angle", null, 0, -1, Spectrum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpectrum_CsvFile(), ecorePackage.getEString(), "csvFile", null, 0, 1, Spectrum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timeCurrentCurveEClass, TimeCurrentCurve.class, "TimeCurrentCurve", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimeCurrentCurve_NPts(), ecorePackage.getEInt(), "nPts", null, 0, 1, TimeCurrentCurve.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeCurrentCurve_CArray(), ecorePackage.getEDouble(), "cArray", null, 0, -1, TimeCurrentCurve.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeCurrentCurve_TArray(), ecorePackage.getEDouble(), "tArray", null, 0, -1, TimeCurrentCurve.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wireDataEClass, WireData.class, "WireData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWireData_RDC(), ecorePackage.getEDouble(), "rDC", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_RAC(), ecorePackage.getEDouble(), "rAC", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_RUnits(), theCommonPackage.getlengthUnit(), "rUnits", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_GmrAC(), ecorePackage.getEDouble(), "gmrAC", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_GmrUnits(), theCommonPackage.getlengthUnit(), "gmrUnits", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_Radius(), ecorePackage.getEDouble(), "radius", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_RadUnits(), theCommonPackage.getlengthUnit(), "radUnits", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_NormAmps(), ecorePackage.getEDouble(), "normAmps", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_EmergAmps(), ecorePackage.getEDouble(), "emergAmps", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWireData_Diameter(), ecorePackage.getEDouble(), "diameter", null, 0, 1, WireData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create annotations
		// null
		createNullAnnotations();
	}

    /**
	 * Initializes the annotations for <b>null</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void createNullAnnotations() {
		String source = null;																																																																					
		addAnnotation
		  (getTimeCurrentCurve_NPts(), 
		   source, 
		   new String[] {
			 "documentation", "Number of points to expect in time-current arrays."
		   });		
		addAnnotation
		  (getTimeCurrentCurve_CArray(), 
		   source, 
		   new String[] {
			 "documentation", "Array of current (or voltage) values corresponding to time values."
		   });		
		addAnnotation
		  (getTimeCurrentCurve_TArray(), 
		   source, 
		   new String[] {
			 "documentation", "Array of time values in sec. Typical array syntax:\n    t_array = (1, 2, 3, 4, ...)\nCan also substitute a file designation:\n    t_array =  (file=filename)\nThe specified file has one value per line."
		   });											
	}

} //GeneralPackageImpl
