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

import dss.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DSSFactoryImpl extends EFactoryImpl implements DSSFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DSSFactory init() {
		try {
			DSSFactory theDSSFactory = (DSSFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openpowersystem.com/dss"); 
			if (theDSSFactory != null) {
				return theDSSFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DSSFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSSFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DSSPackage.SPARSE_DCOMPLEX_MATRIX2_D:
				return createSparseDComplexMatrix2DFromString(eDataType, initialValue);
			case DSSPackage.DENSE_DCOMPLEX_MATRIX1_D:
				return createDenseDComplexMatrix1DFromString(eDataType, initialValue);
			case DSSPackage.DENSE_DOUBLE_MATRIX2_D:
				return createDenseDoubleMatrix2DFromString(eDataType, initialValue);
			case DSSPackage.COMPLEX:
				return createComplexFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DSSPackage.SPARSE_DCOMPLEX_MATRIX2_D:
				return convertSparseDComplexMatrix2DToString(eDataType, instanceValue);
			case DSSPackage.DENSE_DCOMPLEX_MATRIX1_D:
				return convertDenseDComplexMatrix1DToString(eDataType, instanceValue);
			case DSSPackage.DENSE_DOUBLE_MATRIX2_D:
				return convertDenseDoubleMatrix2DToString(eDataType, instanceValue);
			case DSSPackage.COMPLEX:
				return convertComplexToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SparseDComplexMatrix2D createSparseDComplexMatrix2DFromString(EDataType eDataType, String initialValue) {
		return (SparseDComplexMatrix2D)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSparseDComplexMatrix2DToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DenseDComplexMatrix1D createDenseDComplexMatrix1DFromString(EDataType eDataType, String initialValue) {
		return (DenseDComplexMatrix1D)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDenseDComplexMatrix1DToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DenseDoubleMatrix2D createDenseDoubleMatrix2DFromString(EDataType eDataType, String initialValue) {
		return (DenseDoubleMatrix2D)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDenseDoubleMatrix2DToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double[] createComplexFromString(EDataType eDataType, String initialValue) {
		return (double[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComplexToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSSPackage getDSSPackage() {
		return (DSSPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DSSPackage getPackage() {
		return DSSPackage.eINSTANCE;
	}

} //DSSFactoryImpl
