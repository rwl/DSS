/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import electrickery.common.impl.CircuitElementImpl;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.PowerConversionElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Power Conversion Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.PowerConversionElementImpl#getInjCurrent <em>Inj Current</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PowerConversionElementImpl extends CircuitElementImpl implements PowerConversionElement {
    /**
	 * The default value of the '{@link #getInjCurrent() <em>Inj Current</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInjCurrent()
	 * @generated
	 * @ordered
	 */
    protected static final double INJ_CURRENT_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getInjCurrent() <em>Inj Current</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInjCurrent()
	 * @generated
	 * @ordered
	 */
    protected double injCurrent = INJ_CURRENT_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PowerConversionElementImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConversionPackage.Literals.POWER_CONVERSION_ELEMENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getInjCurrent() {
		return injCurrent;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInjCurrent(double newInjCurrent) {
		double oldInjCurrent = injCurrent;
		injCurrent = newInjCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT, oldInjCurrent, injCurrent));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void recalcElementData() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int injCurrents() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void getCurrents(DComplexMatrix1D curr) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void getInjCurrents(DComplexMatrix1D curr) {
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				return getInjCurrent();
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				setInjCurrent((Double)newValue);
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				setInjCurrent(INJ_CURRENT_EDEFAULT);
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				return injCurrent != INJ_CURRENT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (injCurrent: ");
		result.append(injCurrent);
		result.append(')');
		return result.toString();
	}

} //PowerConversionElementImpl
