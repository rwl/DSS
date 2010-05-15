/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss;

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
 * @see dss.DSSFactory
 * @model kind="package"
 * @generated
 */
public interface DSSPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dss";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.openpowersystem.com/dss";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dss";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DSSPackage eINSTANCE = dss.impl.DSSPackageImpl.init();

	/**
	 * The meta object id for the '<em>Sparse DComplex Matrix2 D</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D
	 * @see dss.impl.DSSPackageImpl#getSparseDComplexMatrix2D()
	 * @generated
	 */
	int SPARSE_DCOMPLEX_MATRIX2_D = 0;

	/**
	 * The meta object id for the '<em>Dense DComplex Matrix1 D</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D
	 * @see dss.impl.DSSPackageImpl#getDenseDComplexMatrix1D()
	 * @generated
	 */
	int DENSE_DCOMPLEX_MATRIX1_D = 1;

	/**
	 * The meta object id for the '<em>Dense Double Matrix2 D</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D
	 * @see dss.impl.DSSPackageImpl#getDenseDoubleMatrix2D()
	 * @generated
	 */
	int DENSE_DOUBLE_MATRIX2_D = 2;


	/**
	 * The meta object id for the '<em>Complex</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dss.impl.DSSPackageImpl#getComplex()
	 * @generated
	 */
	int COMPLEX = 3;


	/**
	 * Returns the meta object for data type '{@link cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D <em>Sparse DComplex Matrix2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sparse DComplex Matrix2 D</em>'.
	 * @see cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D
	 * @model instanceClass="cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D"
	 * @generated
	 */
	EDataType getSparseDComplexMatrix2D();

	/**
	 * Returns the meta object for data type '{@link cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D <em>Dense DComplex Matrix1 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Dense DComplex Matrix1 D</em>'.
	 * @see cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D
	 * @model instanceClass="cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D"
	 * @generated
	 */
	EDataType getDenseDComplexMatrix1D();

	/**
	 * Returns the meta object for data type '{@link cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D <em>Dense Double Matrix2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Dense Double Matrix2 D</em>'.
	 * @see cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D
	 * @model instanceClass="cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D"
	 * @generated
	 */
	EDataType getDenseDoubleMatrix2D();

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
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DSSFactory getDSSFactory();

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
		 * The meta object literal for the '<em>Sparse DComplex Matrix2 D</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D
		 * @see dss.impl.DSSPackageImpl#getSparseDComplexMatrix2D()
		 * @generated
		 */
		EDataType SPARSE_DCOMPLEX_MATRIX2_D = eINSTANCE.getSparseDComplexMatrix2D();

		/**
		 * The meta object literal for the '<em>Dense DComplex Matrix1 D</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D
		 * @see dss.impl.DSSPackageImpl#getDenseDComplexMatrix1D()
		 * @generated
		 */
		EDataType DENSE_DCOMPLEX_MATRIX1_D = eINSTANCE.getDenseDComplexMatrix1D();

		/**
		 * The meta object literal for the '<em>Dense Double Matrix2 D</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D
		 * @see dss.impl.DSSPackageImpl#getDenseDoubleMatrix2D()
		 * @generated
		 */
		EDataType DENSE_DOUBLE_MATRIX2_D = eINSTANCE.getDenseDoubleMatrix2D();

		/**
		 * The meta object literal for the '<em>Complex</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dss.impl.DSSPackageImpl#getComplex()
		 * @generated
		 */
		EDataType COMPLEX = eINSTANCE.getComplex();

	}

} //DSSPackage
