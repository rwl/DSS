/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery.impl;

import cern.colt.matrix.tdouble.DoubleMatrix2D;


import electrickery.common.connectionType;
import electrickery.delivery.Capacitor;
import electrickery.delivery.DeliveryPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capacitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getCMatrix <em>CMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getCuf <em>Cuf</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getR <em>R</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getXl <em>Xl</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getHarm <em>Harm</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getNSteps <em>NSteps</em>}</li>
 *   <li>{@link electrickery.delivery.impl.CapacitorImpl#getStates <em>States</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CapacitorImpl extends PowerDeliveryElementImpl implements Capacitor {
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
	 * The cached value of the '{@link #getCMatrix() <em>CMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCMatrix()
	 * @generated
	 * @ordered
	 */
    protected DoubleMatrix2D cMatrix;

    /**
	 * The cached value of the '{@link #getCuf() <em>Cuf</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCuf()
	 * @generated
	 * @ordered
	 */
    protected EList<Double> cuf;

    /**
	 * The cached value of the '{@link #getR() <em>R</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getR()
	 * @generated
	 * @ordered
	 */
    protected EList<Double> r;

    /**
	 * The cached value of the '{@link #getXl() <em>Xl</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXl()
	 * @generated
	 * @ordered
	 */
    protected EList<Double> xl;

    /**
	 * The cached value of the '{@link #getHarm() <em>Harm</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getHarm()
	 * @generated
	 * @ordered
	 */
    protected EList<Double> harm;

    /**
	 * The default value of the '{@link #getNSteps() <em>NSteps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNSteps()
	 * @generated
	 * @ordered
	 */
    protected static final int NSTEPS_EDEFAULT = 1;

    /**
	 * The cached value of the '{@link #getNSteps() <em>NSteps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNSteps()
	 * @generated
	 * @ordered
	 */
    protected int nSteps = NSTEPS_EDEFAULT;

    /**
	 * The cached value of the '{@link #getStates() <em>States</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
    protected EList<Boolean> states;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected CapacitorImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return DeliveryPackage.Literals.CAPACITOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__BUS1, oldBus1, bus1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__BUS2, oldBus2, bus2));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__KV_AR, oldKVAr, kVAr));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__KV, oldKV, kV));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__CONN, oldConn, conn));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D getCMatrix() {
		if (cMatrix != null && ((EObject)cMatrix).eIsProxy()) {
			InternalEObject oldCMatrix = (InternalEObject)cMatrix;
			cMatrix = (DoubleMatrix2D)eResolveProxy(oldCMatrix);
			if (cMatrix != oldCMatrix) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.CAPACITOR__CMATRIX, oldCMatrix, cMatrix));
			}
		}
		return cMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D basicGetCMatrix() {
		return cMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCMatrix(DoubleMatrix2D newCMatrix) {
		DoubleMatrix2D oldCMatrix = cMatrix;
		cMatrix = newCMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__CMATRIX, oldCMatrix, cMatrix));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Double> getCuf() {
		if (cuf == null) {
			cuf = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.CAPACITOR__CUF);
		}
		return cuf;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Double> getR() {
		if (r == null) {
			r = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.CAPACITOR__R);
		}
		return r;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Double> getXl() {
		if (xl == null) {
			xl = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.CAPACITOR__XL);
		}
		return xl;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Double> getHarm() {
		if (harm == null) {
			harm = new EDataTypeUniqueEList<Double>(Double.class, this, DeliveryPackage.CAPACITOR__HARM);
		}
		return harm;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNSteps() {
		return nSteps;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNSteps(int newNSteps) {
		int oldNSteps = nSteps;
		nSteps = newNSteps;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.CAPACITOR__NSTEPS, oldNSteps, nSteps));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Boolean> getStates() {
		if (states == null) {
			states = new EDataTypeUniqueEList<Boolean>(Boolean.class, this, DeliveryPackage.CAPACITOR__STATES);
		}
		return states;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeliveryPackage.CAPACITOR__BUS1:
				return getBus1();
			case DeliveryPackage.CAPACITOR__BUS2:
				return getBus2();
			case DeliveryPackage.CAPACITOR__KV_AR:
				return getKVAr();
			case DeliveryPackage.CAPACITOR__KV:
				return getKV();
			case DeliveryPackage.CAPACITOR__CONN:
				return getConn();
			case DeliveryPackage.CAPACITOR__CMATRIX:
				if (resolve) return getCMatrix();
				return basicGetCMatrix();
			case DeliveryPackage.CAPACITOR__CUF:
				return getCuf();
			case DeliveryPackage.CAPACITOR__R:
				return getR();
			case DeliveryPackage.CAPACITOR__XL:
				return getXl();
			case DeliveryPackage.CAPACITOR__HARM:
				return getHarm();
			case DeliveryPackage.CAPACITOR__NSTEPS:
				return getNSteps();
			case DeliveryPackage.CAPACITOR__STATES:
				return getStates();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DeliveryPackage.CAPACITOR__BUS1:
				setBus1((String)newValue);
				return;
			case DeliveryPackage.CAPACITOR__BUS2:
				setBus2((String)newValue);
				return;
			case DeliveryPackage.CAPACITOR__KV_AR:
				setKVAr((Double)newValue);
				return;
			case DeliveryPackage.CAPACITOR__KV:
				setKV((Double)newValue);
				return;
			case DeliveryPackage.CAPACITOR__CONN:
				setConn((connectionType)newValue);
				return;
			case DeliveryPackage.CAPACITOR__CMATRIX:
				setCMatrix((DoubleMatrix2D)newValue);
				return;
			case DeliveryPackage.CAPACITOR__CUF:
				getCuf().clear();
				getCuf().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.CAPACITOR__R:
				getR().clear();
				getR().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.CAPACITOR__XL:
				getXl().clear();
				getXl().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.CAPACITOR__HARM:
				getHarm().clear();
				getHarm().addAll((Collection<? extends Double>)newValue);
				return;
			case DeliveryPackage.CAPACITOR__NSTEPS:
				setNSteps((Integer)newValue);
				return;
			case DeliveryPackage.CAPACITOR__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends Boolean>)newValue);
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
			case DeliveryPackage.CAPACITOR__BUS1:
				setBus1(BUS1_EDEFAULT);
				return;
			case DeliveryPackage.CAPACITOR__BUS2:
				setBus2(BUS2_EDEFAULT);
				return;
			case DeliveryPackage.CAPACITOR__KV_AR:
				setKVAr(KV_AR_EDEFAULT);
				return;
			case DeliveryPackage.CAPACITOR__KV:
				setKV(KV_EDEFAULT);
				return;
			case DeliveryPackage.CAPACITOR__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case DeliveryPackage.CAPACITOR__CMATRIX:
				setCMatrix((DoubleMatrix2D)null);
				return;
			case DeliveryPackage.CAPACITOR__CUF:
				getCuf().clear();
				return;
			case DeliveryPackage.CAPACITOR__R:
				getR().clear();
				return;
			case DeliveryPackage.CAPACITOR__XL:
				getXl().clear();
				return;
			case DeliveryPackage.CAPACITOR__HARM:
				getHarm().clear();
				return;
			case DeliveryPackage.CAPACITOR__NSTEPS:
				setNSteps(NSTEPS_EDEFAULT);
				return;
			case DeliveryPackage.CAPACITOR__STATES:
				getStates().clear();
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
			case DeliveryPackage.CAPACITOR__BUS1:
				return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
			case DeliveryPackage.CAPACITOR__BUS2:
				return BUS2_EDEFAULT == null ? bus2 != null : !BUS2_EDEFAULT.equals(bus2);
			case DeliveryPackage.CAPACITOR__KV_AR:
				return kVAr != KV_AR_EDEFAULT;
			case DeliveryPackage.CAPACITOR__KV:
				return kV != KV_EDEFAULT;
			case DeliveryPackage.CAPACITOR__CONN:
				return conn != CONN_EDEFAULT;
			case DeliveryPackage.CAPACITOR__CMATRIX:
				return cMatrix != null;
			case DeliveryPackage.CAPACITOR__CUF:
				return cuf != null && !cuf.isEmpty();
			case DeliveryPackage.CAPACITOR__R:
				return r != null && !r.isEmpty();
			case DeliveryPackage.CAPACITOR__XL:
				return xl != null && !xl.isEmpty();
			case DeliveryPackage.CAPACITOR__HARM:
				return harm != null && !harm.isEmpty();
			case DeliveryPackage.CAPACITOR__NSTEPS:
				return nSteps != NSTEPS_EDEFAULT;
			case DeliveryPackage.CAPACITOR__STATES:
				return states != null && !states.isEmpty();
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
		result.append(", cuf: ");
		result.append(cuf);
		result.append(", r: ");
		result.append(r);
		result.append(", xl: ");
		result.append(xl);
		result.append(", harm: ");
		result.append(harm);
		result.append(", nSteps: ");
		result.append(nSteps);
		result.append(", states: ");
		result.append(states);
		result.append(')');
		return result.toString();
	}

} //CapacitorImpl
