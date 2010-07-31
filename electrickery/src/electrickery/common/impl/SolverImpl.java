/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common.impl;

import cern.colt.matrix.tdcomplex.DComplexFactory1D;
import cern.colt.matrix.tdcomplex.DComplexFactory2D;
import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.algo.SparseDoubleAlgebra;

import electrickery.common.CommonPackage;
import electrickery.common.Solver;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Solver</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SolverImpl extends EObjectImpl implements Solver {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SolverImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CommonPackage.Literals.SOLVER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int newSparseSet(int nBus) {
        DComplexMatrix2D Y = DComplexFactory2D.sparse.make(nBus, nBus);
        DComplexMatrix1D x = DComplexFactory1D.dense.make(nBus);
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int solveSparseSet(DComplexMatrix2D Y, DComplexMatrix1D V, DComplexMatrix1D I) {
//        DoubleMatrix1D dx = SparseDoubleAlgebra.DEFAULT.solve(A, b);
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int addPrimitiveMatrix(DComplexMatrix2D Y, int nOrder, int[] pNodes, DComplexMatrix1D Yprim) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int findIslands(DComplexMatrix2D Y, int nOrder, int[] pNodes) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

} //SolverImpl
