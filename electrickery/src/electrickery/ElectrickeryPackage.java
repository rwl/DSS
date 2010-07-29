/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see electrickery.ElectrickeryFactory
 * @model kind="package"
 * @generated
 */
public interface ElectrickeryPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "electrickery";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.electrickery.com/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "electrickery";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ElectrickeryPackage eINSTANCE = electrickery.impl.ElectrickeryPackageImpl.init();

	/**
	 * The meta object id for the '{@link cern.colt.matrix.tdouble.DoubleMatrix1D <em>Double Matrix1 D</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdouble.DoubleMatrix1D
	 * @see electrickery.impl.ElectrickeryPackageImpl#getDoubleMatrix1D()
	 * @generated
	 */
	int DOUBLE_MATRIX1_D = 0;

	/**
	 * The number of structural features of the '<em>Double Matrix1 D</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MATRIX1_D_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link cern.colt.matrix.tdcomplex.DComplexMatrix1D <em>DComplex Matrix1 D</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdcomplex.DComplexMatrix1D
	 * @see electrickery.impl.ElectrickeryPackageImpl#getDComplexMatrix1D()
	 * @generated
	 */
	int DCOMPLEX_MATRIX1_D = 1;

	/**
	 * The number of structural features of the '<em>DComplex Matrix1 D</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DCOMPLEX_MATRIX1_D_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link cern.colt.matrix.tdcomplex.DComplexMatrix2D <em>DComplex Matrix2 D</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdcomplex.DComplexMatrix2D
	 * @see electrickery.impl.ElectrickeryPackageImpl#getDComplexMatrix2D()
	 * @generated
	 */
	int DCOMPLEX_MATRIX2_D = 2;

	/**
	 * The number of structural features of the '<em>DComplex Matrix2 D</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DCOMPLEX_MATRIX2_D_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link cern.colt.matrix.tdouble.DoubleMatrix2D <em>Double Matrix2 D</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdouble.DoubleMatrix2D
	 * @see electrickery.impl.ElectrickeryPackageImpl#getDoubleMatrix2D()
	 * @generated
	 */
	int DOUBLE_MATRIX2_D = 3;

	/**
	 * The number of structural features of the '<em>Double Matrix2 D</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_MATRIX2_D_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '<em>Complex</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.impl.ElectrickeryPackageImpl#getComplex()
	 * @generated
	 */
	int COMPLEX = 4;


	/**
	 * The meta object id for the '<em>EInt Array</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.impl.ElectrickeryPackageImpl#getEIntArray()
	 * @generated
	 */
	int EINT_ARRAY = 5;


	/**
	 * Returns the meta object for class '{@link cern.colt.matrix.tdouble.DoubleMatrix1D <em>Double Matrix1 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Matrix1 D</em>'.
	 * @see cern.colt.matrix.tdouble.DoubleMatrix1D
	 * @model instanceClass="cern.colt.matrix.tdouble.DoubleMatrix1D"
	 * @generated
	 */
	EClass getDoubleMatrix1D();

	/**
	 * Returns the meta object for class '{@link cern.colt.matrix.tdcomplex.DComplexMatrix1D <em>DComplex Matrix1 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DComplex Matrix1 D</em>'.
	 * @see cern.colt.matrix.tdcomplex.DComplexMatrix1D
	 * @model instanceClass="cern.colt.matrix.tdcomplex.DComplexMatrix1D"
	 * @generated
	 */
	EClass getDComplexMatrix1D();

	/**
	 * Returns the meta object for class '{@link cern.colt.matrix.tdcomplex.DComplexMatrix2D <em>DComplex Matrix2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DComplex Matrix2 D</em>'.
	 * @see cern.colt.matrix.tdcomplex.DComplexMatrix2D
	 * @model instanceClass="cern.colt.matrix.tdcomplex.DComplexMatrix2D"
	 * @generated
	 */
	EClass getDComplexMatrix2D();

	/**
	 * Returns the meta object for class '{@link cern.colt.matrix.tdouble.DoubleMatrix2D <em>Double Matrix2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Matrix2 D</em>'.
	 * @see cern.colt.matrix.tdouble.DoubleMatrix2D
	 * @model instanceClass="cern.colt.matrix.tdouble.DoubleMatrix2D"
	 * @generated
	 */
	EClass getDoubleMatrix2D();

	/**
	 * Returns the meta object for data type '<em>Complex</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Complex</em>'.
	 * @model instanceClass="double[]"
	 * @generated
	 */
	EDataType getComplex();

	/**
	 * Returns the meta object for data type '<em>EInt Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EInt Array</em>'.
	 * @model instanceClass="int[]"
	 * @generated
	 */
	EDataType getEIntArray();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ElectrickeryFactory getElectrickeryFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link cern.colt.matrix.tdouble.DoubleMatrix1D <em>Double Matrix1 D</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdouble.DoubleMatrix1D
		 * @see electrickery.impl.ElectrickeryPackageImpl#getDoubleMatrix1D()
		 * @generated
		 */
		EClass DOUBLE_MATRIX1_D = eINSTANCE.getDoubleMatrix1D();

		/**
		 * The meta object literal for the '{@link cern.colt.matrix.tdcomplex.DComplexMatrix1D <em>DComplex Matrix1 D</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdcomplex.DComplexMatrix1D
		 * @see electrickery.impl.ElectrickeryPackageImpl#getDComplexMatrix1D()
		 * @generated
		 */
		EClass DCOMPLEX_MATRIX1_D = eINSTANCE.getDComplexMatrix1D();

		/**
		 * The meta object literal for the '{@link cern.colt.matrix.tdcomplex.DComplexMatrix2D <em>DComplex Matrix2 D</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdcomplex.DComplexMatrix2D
		 * @see electrickery.impl.ElectrickeryPackageImpl#getDComplexMatrix2D()
		 * @generated
		 */
		EClass DCOMPLEX_MATRIX2_D = eINSTANCE.getDComplexMatrix2D();

		/**
		 * The meta object literal for the '{@link cern.colt.matrix.tdouble.DoubleMatrix2D <em>Double Matrix2 D</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdouble.DoubleMatrix2D
		 * @see electrickery.impl.ElectrickeryPackageImpl#getDoubleMatrix2D()
		 * @generated
		 */
		EClass DOUBLE_MATRIX2_D = eINSTANCE.getDoubleMatrix2D();

		/**
		 * The meta object literal for the '<em>Complex</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.impl.ElectrickeryPackageImpl#getComplex()
		 * @generated
		 */
		EDataType COMPLEX = eINSTANCE.getComplex();

		/**
		 * The meta object literal for the '<em>EInt Array</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see electrickery.impl.ElectrickeryPackageImpl#getEIntArray()
		 * @generated
		 */
		EDataType EINT_ARRAY = eINSTANCE.getEIntArray();

	}

} //ElectrickeryPackage
