/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery.impl;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.common.connectionType;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.Reactor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reactor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getRMatrix <em>RMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getXMatrix <em>XMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#isParallel <em>Parallel</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getR <em>R</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getX <em>X</em>}</li>
 *   <li>{@link electrickery.delivery.impl.ReactorImpl#getRp <em>Rp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReactorImpl extends PowerDeliveryElementImpl implements Reactor {
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
     * The default value of the '{@link #getKVAr() <em>KV Ar</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKVAr()
     * @generated
     * @ordered
     */
    protected static final double KV_AR_EDEFAULT = 1200.0;

    /**
     * The cached value of the '{@link #getKVAr() <em>KV Ar</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKVAr()
     * @generated
     * @ordered
     */
    protected double kVAr = KV_AR_EDEFAULT;

    /**
     * The default value of the '{@link #getKV() <em>KV</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKV()
     * @generated
     * @ordered
     */
    protected static final double KV_EDEFAULT = 12.47;

    /**
     * The cached value of the '{@link #getKV() <em>KV</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKV()
     * @generated
     * @ordered
     */
    protected double kV = KV_EDEFAULT;

    /**
     * The default value of the '{@link #getConn() <em>Conn</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConn()
     * @generated
     * @ordered
     */
    protected static final connectionType CONN_EDEFAULT = connectionType.WYE;

    /**
     * The cached value of the '{@link #getConn() <em>Conn</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConn()
     * @generated
     * @ordered
     */
    protected connectionType conn = CONN_EDEFAULT;

    /**
     * The cached value of the '{@link #getRMatrix() <em>RMatrix</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRMatrix()
     * @generated
     * @ordered
     */
    protected DoubleMatrix2D rMatrix;

    /**
     * The cached value of the '{@link #getXMatrix() <em>XMatrix</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMatrix()
     * @generated
     * @ordered
     */
    protected DoubleMatrix2D xMatrix;

    /**
     * The default value of the '{@link #isParallel() <em>Parallel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isParallel()
     * @generated
     * @ordered
     */
    protected static final boolean PARALLEL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isParallel() <em>Parallel</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isParallel()
     * @generated
     * @ordered
     */
    protected boolean parallel = PARALLEL_EDEFAULT;

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
     * The default value of the '{@link #getX() <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final double X_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected double x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getRp() <em>Rp</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRp()
     * @generated
     * @ordered
     */
    protected static final double RP_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getRp() <em>Rp</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRp()
     * @generated
     * @ordered
     */
    protected double rp = RP_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ReactorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DeliveryPackage.Literals.REACTOR;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__BUS1, oldBus1, bus1));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__BUS2, oldBus2, bus2));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getKVAr() {
        return kVAr;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKVAr(double newKVAr) {
        double oldKVAr = kVAr;
        kVAr = newKVAr;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__KV_AR, oldKVAr, kVAr));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getKV() {
        return kV;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKV(double newKV) {
        double oldKV = kV;
        kV = newKV;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__KV, oldKV, kV));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public connectionType getConn() {
        return conn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConn(connectionType newConn) {
        connectionType oldConn = conn;
        conn = newConn == null ? CONN_EDEFAULT : newConn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__CONN, oldConn, conn));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix2D getRMatrix() {
        if (rMatrix != null && ((EObject)rMatrix).eIsProxy()) {
            InternalEObject oldRMatrix = (InternalEObject)rMatrix;
            rMatrix = (DoubleMatrix2D)eResolveProxy(oldRMatrix);
            if (rMatrix != oldRMatrix) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.REACTOR__RMATRIX, oldRMatrix, rMatrix));
            }
        }
        return rMatrix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix2D basicGetRMatrix() {
        return rMatrix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRMatrix(DoubleMatrix2D newRMatrix) {
        DoubleMatrix2D oldRMatrix = rMatrix;
        rMatrix = newRMatrix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__RMATRIX, oldRMatrix, rMatrix));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix2D getXMatrix() {
        if (xMatrix != null && ((EObject)xMatrix).eIsProxy()) {
            InternalEObject oldXMatrix = (InternalEObject)xMatrix;
            xMatrix = (DoubleMatrix2D)eResolveProxy(oldXMatrix);
            if (xMatrix != oldXMatrix) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.REACTOR__XMATRIX, oldXMatrix, xMatrix));
            }
        }
        return xMatrix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix2D basicGetXMatrix() {
        return xMatrix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXMatrix(DoubleMatrix2D newXMatrix) {
        DoubleMatrix2D oldXMatrix = xMatrix;
        xMatrix = newXMatrix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__XMATRIX, oldXMatrix, xMatrix));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isParallel() {
        return parallel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParallel(boolean newParallel) {
        boolean oldParallel = parallel;
        parallel = newParallel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__PARALLEL, oldParallel, parallel));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__R, oldR, r));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getX() {
        return x;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setX(double newX) {
        double oldX = x;
        x = newX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__X, oldX, x));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getRp() {
        return rp;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRp(double newRp) {
        double oldRp = rp;
        rp = newRp;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.REACTOR__RP, oldRp, rp));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DeliveryPackage.REACTOR__BUS1:
                return getBus1();
            case DeliveryPackage.REACTOR__BUS2:
                return getBus2();
            case DeliveryPackage.REACTOR__KV_AR:
                return getKVAr();
            case DeliveryPackage.REACTOR__KV:
                return getKV();
            case DeliveryPackage.REACTOR__CONN:
                return getConn();
            case DeliveryPackage.REACTOR__RMATRIX:
                if (resolve) return getRMatrix();
                return basicGetRMatrix();
            case DeliveryPackage.REACTOR__XMATRIX:
                if (resolve) return getXMatrix();
                return basicGetXMatrix();
            case DeliveryPackage.REACTOR__PARALLEL:
                return isParallel();
            case DeliveryPackage.REACTOR__R:
                return getR();
            case DeliveryPackage.REACTOR__X:
                return getX();
            case DeliveryPackage.REACTOR__RP:
                return getRp();
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
            case DeliveryPackage.REACTOR__BUS1:
                setBus1((String)newValue);
                return;
            case DeliveryPackage.REACTOR__BUS2:
                setBus2((String)newValue);
                return;
            case DeliveryPackage.REACTOR__KV_AR:
                setKVAr((Double)newValue);
                return;
            case DeliveryPackage.REACTOR__KV:
                setKV((Double)newValue);
                return;
            case DeliveryPackage.REACTOR__CONN:
                setConn((connectionType)newValue);
                return;
            case DeliveryPackage.REACTOR__RMATRIX:
                setRMatrix((DoubleMatrix2D)newValue);
                return;
            case DeliveryPackage.REACTOR__XMATRIX:
                setXMatrix((DoubleMatrix2D)newValue);
                return;
            case DeliveryPackage.REACTOR__PARALLEL:
                setParallel((Boolean)newValue);
                return;
            case DeliveryPackage.REACTOR__R:
                setR((Double)newValue);
                return;
            case DeliveryPackage.REACTOR__X:
                setX((Double)newValue);
                return;
            case DeliveryPackage.REACTOR__RP:
                setRp((Double)newValue);
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
            case DeliveryPackage.REACTOR__BUS1:
                setBus1(BUS1_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__BUS2:
                setBus2(BUS2_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__KV_AR:
                setKVAr(KV_AR_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__KV:
                setKV(KV_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__CONN:
                setConn(CONN_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__RMATRIX:
                setRMatrix((DoubleMatrix2D)null);
                return;
            case DeliveryPackage.REACTOR__XMATRIX:
                setXMatrix((DoubleMatrix2D)null);
                return;
            case DeliveryPackage.REACTOR__PARALLEL:
                setParallel(PARALLEL_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__R:
                setR(R_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__X:
                setX(X_EDEFAULT);
                return;
            case DeliveryPackage.REACTOR__RP:
                setRp(RP_EDEFAULT);
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
            case DeliveryPackage.REACTOR__BUS1:
                return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
            case DeliveryPackage.REACTOR__BUS2:
                return BUS2_EDEFAULT == null ? bus2 != null : !BUS2_EDEFAULT.equals(bus2);
            case DeliveryPackage.REACTOR__KV_AR:
                return kVAr != KV_AR_EDEFAULT;
            case DeliveryPackage.REACTOR__KV:
                return kV != KV_EDEFAULT;
            case DeliveryPackage.REACTOR__CONN:
                return conn != CONN_EDEFAULT;
            case DeliveryPackage.REACTOR__RMATRIX:
                return rMatrix != null;
            case DeliveryPackage.REACTOR__XMATRIX:
                return xMatrix != null;
            case DeliveryPackage.REACTOR__PARALLEL:
                return parallel != PARALLEL_EDEFAULT;
            case DeliveryPackage.REACTOR__R:
                return r != R_EDEFAULT;
            case DeliveryPackage.REACTOR__X:
                return x != X_EDEFAULT;
            case DeliveryPackage.REACTOR__RP:
                return rp != RP_EDEFAULT;
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
        result.append(", kVAr: ");
        result.append(kVAr);
        result.append(", kV: ");
        result.append(kV);
        result.append(", conn: ");
        result.append(conn);
        result.append(", parallel: ");
        result.append(parallel);
        result.append(", r: ");
        result.append(r);
        result.append(", x: ");
        result.append(x);
        result.append(", rp: ");
        result.append(rp);
        result.append(')');
        return result.toString();
    }

} //ReactorImpl
