/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Solver</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see electrickery.common.CommonPackage#getSolver()
 * @model
 * @generated
 */
public interface Solver extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int newSparseSet(int nBus);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model YType="electrickery.DComplexMatrix2D" VType="electrickery.DComplexMatrix1D" IType="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	int solveSparseSet(DComplexMatrix2D Y, DComplexMatrix1D V, DComplexMatrix1D I);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model YType="electrickery.DComplexMatrix2D" pNodesDataType="electrickery.EIntArray" YprimType="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	int addPrimitiveMatrix(DComplexMatrix2D Y, int nOrder, int[] pNodes, DComplexMatrix1D Yprim);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model YType="electrickery.DComplexMatrix2D" pNodesDataType="electrickery.EIntArray"
	 * @generated
	 */
	int findIslands(DComplexMatrix2D Y, int nOrder, int[] pNodes);

} // Solver
