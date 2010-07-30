/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common.impl;

import electrickery.common.Circuit;
import electrickery.common.CircuitElement;
import electrickery.common.CommonPackage;
import electrickery.common.YMatrix;
import electrickery.common.yBuildOption;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>YMatrix</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.YMatrixImpl#getCircuit <em>Circuit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class YMatrixImpl extends EObjectImpl implements YMatrix {
    /**
	 * The cached value of the '{@link #getCircuit() <em>Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCircuit()
	 * @generated
	 * @ordered
	 */
    protected Circuit circuit;

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected YMatrixImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return CommonPackage.Literals.YMATRIX;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit getCircuit() {
		if (circuit != null && circuit.eIsProxy()) {
			InternalEObject oldCircuit = (InternalEObject)circuit;
			circuit = (Circuit)eResolveProxy(oldCircuit);
			if (circuit != oldCircuit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.YMATRIX__CIRCUIT, oldCircuit, circuit));
			}
		}
		return circuit;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit basicGetCircuit() {
		return circuit;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCircuit(Circuit newCircuit) {
		Circuit oldCircuit = circuit;
		circuit = newCircuit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.YMATRIX__CIRCUIT, oldCircuit, circuit));
	}

                /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void buildYMatrix(yBuildOption buildOption, boolean allocateVI) {
        Circuit ckt = getCircuit();

        if (ckt.getSolution().isPreserveNodeVoltages())
            ckt.getSolution().updateVBus();

        if (ckt.isBusNameRedefined())
            ckt.reProcessBusDefs(); // This changes the node references into the system Y matrix.

        if (ckt.getSolution().isFrequencyChanged()) {
            reCalcAllYPrims();
        } else {
            reCalcInvalidYPrims();
        }

        if (ckt.getExecutive().getGlobals().isSolutionAbort())
            System.out.println("Y matrix build aborted due to error in primitive Y calculations.");

        ckt.getSolution().setFrequencyChanged(false);

        if (buildOption == yBuildOption.WHOLE_MATRIX) {
            System.out.println("Building whole Y matrix.");
        } else if (buildOption == yBuildOption.SERIES_ONLY) {
            System.out.println("Building series Y matrix.");
        }

        // Add in all YPrims for all devices.
        for (int i = 0; i < ckt.getCircuitElements().size(); i++) {
            CircuitElement element = ckt.getCircuitElements().get(i);
            if (element.isEnabled()) {
                DComplexMatrix2D yPrimValues = element.getYPrimValues(buildOption);
                // Add primitive Y to Y matrix.
                // AddPrimitiveMatrix(element.getYOrder(), yPrimValues);
            }
        }

        // Allocate voltage and current vectors if requested.
        if (allocateVI) {
            // TODO: Allocate voltage and current vectors.
        }

        if (buildOption == yBuildOption.WHOLE_MATRIX) {
            // Indicate that the series matrix may not match.
            ckt.getSolution().setSeriesYInvalid(true);
            ckt.getSolution().setSystemYChanged(false);
        } else if (buildOption == yBuildOption.SERIES_ONLY) {
            ckt.getSolution().setSeriesYInvalid(false);
            // systemYChanged unchanged.
        }

        if (ckt.getSolution().isPreserveNodeVoltages()) {
            // getSolution().restoreNodeVFromVBus();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void reCalcAllYPrims() {
        Circuit ckt = getCircuit();

        System.out.println("Recalc All Yprims");

        for (int i = 0; i < ckt.getCircuitElements().size(); i++) {
            CircuitElement element = ckt.getCircuitElements().get(i);
            element.calcYPrim(ckt.getSolution().getFrequency());
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void reCalcInvalidYPrims() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.YMATRIX__CIRCUIT:
				if (resolve) return getCircuit();
				return basicGetCircuit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.YMATRIX__CIRCUIT:
				setCircuit((Circuit)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.YMATRIX__CIRCUIT:
				setCircuit((Circuit)null);
				return;
		}
		super.eUnset(featureID);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.YMATRIX__CIRCUIT:
				return circuit != null;
		}
		return super.eIsSet(featureID);
	}

} //YMatrixImpl
