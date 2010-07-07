/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery.impl;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.Fault;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fault</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getR <em>R</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getPctStdDev <em>Pct Std Dev</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getGMatrix <em>GMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getOnTime <em>On Time</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#isTemporary <em>Temporary</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FaultImpl#getMinAmps <em>Min Amps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaultImpl extends PowerDeliveryElementImpl implements Fault {
    /**
     * The default value of the '{@link #getBus1() <em>Bus1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBus1()
     * @generated
     * @ordered
     */
    protected static final String BUS1_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBus1() <em>Bus1</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBus1()
     * @generated
     * @ordered
     */
    protected String bus1 = BUS1_EDEFAULT;

    /**
     * The default value of the '{@link #getBus2() <em>Bus2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBus2()
     * @generated
     * @ordered
     */
    protected static final String BUS2_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBus2() <em>Bus2</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBus2()
     * @generated
     * @ordered
     */
    protected String bus2 = BUS2_EDEFAULT;

    /**
     * The default value of the '{@link #getR() <em>R</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getR()
     * @generated
     * @ordered
     */
    protected static final double R_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getR() <em>R</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getR()
     * @generated
     * @ordered
     */
    protected double r = R_EDEFAULT;

    /**
     * The default value of the '{@link #getPctStdDev() <em>Pct Std Dev</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPctStdDev()
     * @generated
     * @ordered
     */
    protected static final double PCT_STD_DEV_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getPctStdDev() <em>Pct Std Dev</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPctStdDev()
     * @generated
     * @ordered
     */
    protected double pctStdDev = PCT_STD_DEV_EDEFAULT;

    /**
     * The cached value of the '{@link #getGMatrix() <em>GMatrix</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGMatrix()
     * @generated
     * @ordered
     */
    protected DoubleMatrix2D gMatrix;

    /**
     * The default value of the '{@link #getOnTime() <em>On Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOnTime()
     * @generated
     * @ordered
     */
    protected static final double ON_TIME_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getOnTime() <em>On Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOnTime()
     * @generated
     * @ordered
     */
    protected double onTime = ON_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #isTemporary() <em>Temporary</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTemporary()
     * @generated
     * @ordered
     */
    protected static final boolean TEMPORARY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTemporary() <em>Temporary</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTemporary()
     * @generated
     * @ordered
     */
    protected boolean temporary = TEMPORARY_EDEFAULT;

    /**
     * The default value of the '{@link #getMinAmps() <em>Min Amps</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinAmps()
     * @generated
     * @ordered
     */
    protected static final double MIN_AMPS_EDEFAULT = 5.0;

    /**
     * The cached value of the '{@link #getMinAmps() <em>Min Amps</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinAmps()
     * @generated
     * @ordered
     */
    protected double minAmps = MIN_AMPS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaultImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DeliveryPackage.Literals.FAULT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBus1() {
        return bus1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBus1(String newBus1) {
        String oldBus1 = bus1;
        bus1 = newBus1;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__BUS1, oldBus1, bus1));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBus2() {
        return bus2;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBus2(String newBus2) {
        String oldBus2 = bus2;
        bus2 = newBus2;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__BUS2, oldBus2, bus2));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getR() {
        return r;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setR(double newR) {
        double oldR = r;
        r = newR;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__R, oldR, r));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getPctStdDev() {
        return pctStdDev;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPctStdDev(double newPctStdDev) {
        double oldPctStdDev = pctStdDev;
        pctStdDev = newPctStdDev;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__PCT_STD_DEV, oldPctStdDev, pctStdDev));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix2D getGMatrix() {
        if (gMatrix != null && ((EObject)gMatrix).eIsProxy()) {
            InternalEObject oldGMatrix = (InternalEObject)gMatrix;
            gMatrix = (DoubleMatrix2D)eResolveProxy(oldGMatrix);
            if (gMatrix != oldGMatrix) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.FAULT__GMATRIX, oldGMatrix, gMatrix));
            }
        }
        return gMatrix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix2D basicGetGMatrix() {
        return gMatrix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGMatrix(DoubleMatrix2D newGMatrix) {
        DoubleMatrix2D oldGMatrix = gMatrix;
        gMatrix = newGMatrix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__GMATRIX, oldGMatrix, gMatrix));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getOnTime() {
        return onTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOnTime(double newOnTime) {
        double oldOnTime = onTime;
        onTime = newOnTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__ON_TIME, oldOnTime, onTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTemporary() {
        return temporary;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTemporary(boolean newTemporary) {
        boolean oldTemporary = temporary;
        temporary = newTemporary;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__TEMPORARY, oldTemporary, temporary));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getMinAmps() {
        return minAmps;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinAmps(double newMinAmps) {
        double oldMinAmps = minAmps;
        minAmps = newMinAmps;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FAULT__MIN_AMPS, oldMinAmps, minAmps));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DeliveryPackage.FAULT__BUS1:
                return getBus1();
            case DeliveryPackage.FAULT__BUS2:
                return getBus2();
            case DeliveryPackage.FAULT__R:
                return getR();
            case DeliveryPackage.FAULT__PCT_STD_DEV:
                return getPctStdDev();
            case DeliveryPackage.FAULT__GMATRIX:
                if (resolve) return getGMatrix();
                return basicGetGMatrix();
            case DeliveryPackage.FAULT__ON_TIME:
                return getOnTime();
            case DeliveryPackage.FAULT__TEMPORARY:
                return isTemporary();
            case DeliveryPackage.FAULT__MIN_AMPS:
                return getMinAmps();
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
            case DeliveryPackage.FAULT__BUS1:
                setBus1((String)newValue);
                return;
            case DeliveryPackage.FAULT__BUS2:
                setBus2((String)newValue);
                return;
            case DeliveryPackage.FAULT__R:
                setR((Double)newValue);
                return;
            case DeliveryPackage.FAULT__PCT_STD_DEV:
                setPctStdDev((Double)newValue);
                return;
            case DeliveryPackage.FAULT__GMATRIX:
                setGMatrix((DoubleMatrix2D)newValue);
                return;
            case DeliveryPackage.FAULT__ON_TIME:
                setOnTime((Double)newValue);
                return;
            case DeliveryPackage.FAULT__TEMPORARY:
                setTemporary((Boolean)newValue);
                return;
            case DeliveryPackage.FAULT__MIN_AMPS:
                setMinAmps((Double)newValue);
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
            case DeliveryPackage.FAULT__BUS1:
                setBus1(BUS1_EDEFAULT);
                return;
            case DeliveryPackage.FAULT__BUS2:
                setBus2(BUS2_EDEFAULT);
                return;
            case DeliveryPackage.FAULT__R:
                setR(R_EDEFAULT);
                return;
            case DeliveryPackage.FAULT__PCT_STD_DEV:
                setPctStdDev(PCT_STD_DEV_EDEFAULT);
                return;
            case DeliveryPackage.FAULT__GMATRIX:
                setGMatrix((DoubleMatrix2D)null);
                return;
            case DeliveryPackage.FAULT__ON_TIME:
                setOnTime(ON_TIME_EDEFAULT);
                return;
            case DeliveryPackage.FAULT__TEMPORARY:
                setTemporary(TEMPORARY_EDEFAULT);
                return;
            case DeliveryPackage.FAULT__MIN_AMPS:
                setMinAmps(MIN_AMPS_EDEFAULT);
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
            case DeliveryPackage.FAULT__BUS1:
                return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
            case DeliveryPackage.FAULT__BUS2:
                return BUS2_EDEFAULT == null ? bus2 != null : !BUS2_EDEFAULT.equals(bus2);
            case DeliveryPackage.FAULT__R:
                return r != R_EDEFAULT;
            case DeliveryPackage.FAULT__PCT_STD_DEV:
                return pctStdDev != PCT_STD_DEV_EDEFAULT;
            case DeliveryPackage.FAULT__GMATRIX:
                return gMatrix != null;
            case DeliveryPackage.FAULT__ON_TIME:
                return onTime != ON_TIME_EDEFAULT;
            case DeliveryPackage.FAULT__TEMPORARY:
                return temporary != TEMPORARY_EDEFAULT;
            case DeliveryPackage.FAULT__MIN_AMPS:
                return minAmps != MIN_AMPS_EDEFAULT;
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
        result.append(" (bus1: ");
        result.append(bus1);
        result.append(", bus2: ");
        result.append(bus2);
        result.append(", r: ");
        result.append(r);
        result.append(", pctStdDev: ");
        result.append(pctStdDev);
        result.append(", onTime: ");
        result.append(onTime);
        result.append(", temporary: ");
        result.append(temporary);
        result.append(", minAmps: ");
        result.append(minAmps);
        result.append(')');
        return result.toString();
    }

} //FaultImpl
