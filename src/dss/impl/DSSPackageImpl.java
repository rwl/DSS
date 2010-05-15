/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.impl;

import cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D;
import cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D;

import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;

import dss.DSSFactory;
import dss.DSSPackage;

import dss.common.CommonPackage;

import dss.common.impl.CommonPackageImpl;

import dss.control.ControlPackage;

import dss.control.impl.ControlPackageImpl;

import dss.conversion.ConversionPackage;

import dss.conversion.impl.ConversionPackageImpl;

import dss.delivery.DeliveryPackage;

import dss.delivery.impl.DeliveryPackageImpl;

import dss.executive.ExecutivePackage;

import dss.executive.impl.ExecutivePackageImpl;

import dss.general.GeneralPackage;

import dss.general.impl.GeneralPackageImpl;

import dss.meter.MeterPackage;

import dss.meter.impl.MeterPackageImpl;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DSSPackageImpl extends EPackageImpl implements DSSPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sparseDComplexMatrix2DEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType denseDComplexMatrix1DEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType denseDoubleMatrix2DEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType complexEDataType = null;

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
	 * @see dss.DSSPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DSSPackageImpl() {
		super(eNS_URI, DSSFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DSSPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DSSPackage init() {
		if (isInited) return (DSSPackage)EPackage.Registry.INSTANCE.getEPackage(DSSPackage.eNS_URI);

		// Obtain or create and register package
		DSSPackageImpl theDSSPackage = (DSSPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DSSPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DSSPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		ConversionPackageImpl theConversionPackage = (ConversionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) instanceof ConversionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI) : ConversionPackage.eINSTANCE);
		DeliveryPackageImpl theDeliveryPackage = (DeliveryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) instanceof DeliveryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI) : DeliveryPackage.eINSTANCE);
		ExecutivePackageImpl theExecutivePackage = (ExecutivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) instanceof ExecutivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI) : ExecutivePackage.eINSTANCE);
		GeneralPackageImpl theGeneralPackage = (GeneralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) instanceof GeneralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI) : GeneralPackage.eINSTANCE);
		MeterPackageImpl theMeterPackage = (MeterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) instanceof MeterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI) : MeterPackage.eINSTANCE);

		// Create package meta-data objects
		theDSSPackage.createPackageContents();
		theCommonPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theConversionPackage.createPackageContents();
		theDeliveryPackage.createPackageContents();
		theExecutivePackage.createPackageContents();
		theGeneralPackage.createPackageContents();
		theMeterPackage.createPackageContents();

		// Initialize created meta-data
		theDSSPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theConversionPackage.initializePackageContents();
		theDeliveryPackage.initializePackageContents();
		theExecutivePackage.initializePackageContents();
		theGeneralPackage.initializePackageContents();
		theMeterPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDSSPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DSSPackage.eNS_URI, theDSSPackage);
		return theDSSPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSparseDComplexMatrix2D() {
		return sparseDComplexMatrix2DEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDenseDComplexMatrix1D() {
		return denseDComplexMatrix1DEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDenseDoubleMatrix2D() {
		return denseDoubleMatrix2DEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getComplex() {
		return complexEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSSFactory getDSSFactory() {
		return (DSSFactory)getEFactoryInstance();
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

		// Create data types
		sparseDComplexMatrix2DEDataType = createEDataType(SPARSE_DCOMPLEX_MATRIX2_D);
		denseDComplexMatrix1DEDataType = createEDataType(DENSE_DCOMPLEX_MATRIX1_D);
		denseDoubleMatrix2DEDataType = createEDataType(DENSE_DOUBLE_MATRIX2_D);
		complexEDataType = createEDataType(COMPLEX);
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
		ControlPackage theControlPackage = (ControlPackage)EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI);
		ConversionPackage theConversionPackage = (ConversionPackage)EPackage.Registry.INSTANCE.getEPackage(ConversionPackage.eNS_URI);
		DeliveryPackage theDeliveryPackage = (DeliveryPackage)EPackage.Registry.INSTANCE.getEPackage(DeliveryPackage.eNS_URI);
		ExecutivePackage theExecutivePackage = (ExecutivePackage)EPackage.Registry.INSTANCE.getEPackage(ExecutivePackage.eNS_URI);
		GeneralPackage theGeneralPackage = (GeneralPackage)EPackage.Registry.INSTANCE.getEPackage(GeneralPackage.eNS_URI);
		MeterPackage theMeterPackage = (MeterPackage)EPackage.Registry.INSTANCE.getEPackage(MeterPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theCommonPackage);
		getESubpackages().add(theControlPackage);
		getESubpackages().add(theConversionPackage);
		getESubpackages().add(theDeliveryPackage);
		getESubpackages().add(theExecutivePackage);
		getESubpackages().add(theGeneralPackage);
		getESubpackages().add(theMeterPackage);

		// Initialize data types
		initEDataType(sparseDComplexMatrix2DEDataType, SparseDComplexMatrix2D.class, "SparseDComplexMatrix2D", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(denseDComplexMatrix1DEDataType, DenseDComplexMatrix1D.class, "DenseDComplexMatrix1D", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(denseDoubleMatrix2DEDataType, DenseDoubleMatrix2D.class, "DenseDoubleMatrix2D", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(complexEDataType, double[].class, "Complex", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DSSPackageImpl
