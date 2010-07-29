/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import electrickery.*;
import electrickery.ElectrickeryPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see electrickery.ElectrickeryPackage
 * @generated
 */
public class ElectrickeryAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ElectrickeryPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElectrickeryAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ElectrickeryPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElectrickerySwitch<Adapter> modelSwitch =
		new ElectrickerySwitch<Adapter>() {
			@Override
			public Adapter caseDoubleMatrix1D(DoubleMatrix1D object) {
				return createDoubleMatrix1DAdapter();
			}
			@Override
			public Adapter caseDComplexMatrix1D(DComplexMatrix1D object) {
				return createDComplexMatrix1DAdapter();
			}
			@Override
			public Adapter caseDComplexMatrix2D(DComplexMatrix2D object) {
				return createDComplexMatrix2DAdapter();
			}
			@Override
			public Adapter caseDoubleMatrix2D(DoubleMatrix2D object) {
				return createDoubleMatrix2DAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link cern.colt.matrix.tdouble.DoubleMatrix1D <em>Double Matrix1 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.colt.matrix.tdouble.DoubleMatrix1D
	 * @generated
	 */
	public Adapter createDoubleMatrix1DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.colt.matrix.tdcomplex.DComplexMatrix1D <em>DComplex Matrix1 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.colt.matrix.tdcomplex.DComplexMatrix1D
	 * @generated
	 */
	public Adapter createDComplexMatrix1DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.colt.matrix.tdcomplex.DComplexMatrix2D <em>DComplex Matrix2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.colt.matrix.tdcomplex.DComplexMatrix2D
	 * @generated
	 */
	public Adapter createDComplexMatrix2DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.colt.matrix.tdouble.DoubleMatrix2D <em>Double Matrix2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.colt.matrix.tdouble.DoubleMatrix2D
	 * @generated
	 */
	public Adapter createDoubleMatrix2DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ElectrickeryAdapterFactory
