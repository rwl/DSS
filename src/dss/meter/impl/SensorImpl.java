/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.meter.impl;

import dss.common.connectionType;

import dss.meter.MeterPackage;
import dss.meter.Sensor;
import dss.meter.sensorAction;
import dss.meter.sensorMode;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sensor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.meter.impl.SensorImpl#getElement <em>Element</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getModes <em>Modes</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getV <em>V</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getI <em>I</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getP <em>P</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getQ <em>Q</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getPhases <em>Phases</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getPctError <em>Pct Error</em>}</li>
 *   <li>{@link dss.meter.impl.SensorImpl#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SensorImpl extends MeterElementImpl implements Sensor {
	/**
	 * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected String element = ELEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected static final int TERMINAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected int terminal = TERMINAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModes() <em>Modes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModes()
	 * @generated
	 * @ordered
	 */
	protected EList<sensorMode> modes;

	/**
	 * The cached value of the '{@link #getV() <em>V</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getV()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> v;

	/**
	 * The cached value of the '{@link #getI() <em>I</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getI()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> i;

	/**
	 * The cached value of the '{@link #getP() <em>P</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getP()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> p;

	/**
	 * The cached value of the '{@link #getQ() <em>Q</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQ()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> q;

	/**
	 * The cached value of the '{@link #getPhases() <em>Phases</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhases()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> phases;

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
	 * The default value of the '{@link #getPctError() <em>Pct Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctError()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_ERROR_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getPctError() <em>Pct Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctError()
	 * @generated
	 * @ordered
	 */
	protected double pctError = PCT_ERROR_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final sensorAction ACTION_EDEFAULT = sensorAction.SQUARE_ERROR;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected sensorAction action = ACTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SensorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MeterPackage.Literals.SENSOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(String newElement) {
		String oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.SENSOR__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTerminal() {
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminal(int newTerminal) {
		int oldTerminal = terminal;
		terminal = newTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.SENSOR__TERMINAL, oldTerminal, terminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<sensorMode> getModes() {
		if (modes == null) {
			modes = new EDataTypeUniqueEList<sensorMode>(sensorMode.class, this, MeterPackage.SENSOR__MODES);
		}
		return modes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getV() {
		if (v == null) {
			v = new EDataTypeUniqueEList<Double>(Double.class, this, MeterPackage.SENSOR__V);
		}
		return v;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getI() {
		if (i == null) {
			i = new EDataTypeUniqueEList<Double>(Double.class, this, MeterPackage.SENSOR__I);
		}
		return i;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getP() {
		if (p == null) {
			p = new EDataTypeUniqueEList<Double>(Double.class, this, MeterPackage.SENSOR__P);
		}
		return p;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getQ() {
		if (q == null) {
			q = new EDataTypeUniqueEList<Double>(Double.class, this, MeterPackage.SENSOR__Q);
		}
		return q;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getPhases() {
		if (phases == null) {
			phases = new EDataTypeUniqueEList<Integer>(Integer.class, this, MeterPackage.SENSOR__PHASES);
		}
		return phases;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.SENSOR__CONN, oldConn, conn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctError() {
		return pctError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctError(double newPctError) {
		double oldPctError = pctError;
		pctError = newPctError;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.SENSOR__PCT_ERROR, oldPctError, pctError));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public sensorAction getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(sensorAction newAction) {
		sensorAction oldAction = action;
		action = newAction == null ? ACTION_EDEFAULT : newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.SENSOR__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MeterPackage.SENSOR__ELEMENT:
				return getElement();
			case MeterPackage.SENSOR__TERMINAL:
				return getTerminal();
			case MeterPackage.SENSOR__MODES:
				return getModes();
			case MeterPackage.SENSOR__V:
				return getV();
			case MeterPackage.SENSOR__I:
				return getI();
			case MeterPackage.SENSOR__P:
				return getP();
			case MeterPackage.SENSOR__Q:
				return getQ();
			case MeterPackage.SENSOR__PHASES:
				return getPhases();
			case MeterPackage.SENSOR__CONN:
				return getConn();
			case MeterPackage.SENSOR__PCT_ERROR:
				return getPctError();
			case MeterPackage.SENSOR__ACTION:
				return getAction();
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
			case MeterPackage.SENSOR__ELEMENT:
				setElement((String)newValue);
				return;
			case MeterPackage.SENSOR__TERMINAL:
				setTerminal((Integer)newValue);
				return;
			case MeterPackage.SENSOR__MODES:
				getModes().clear();
				getModes().addAll((Collection<? extends sensorMode>)newValue);
				return;
			case MeterPackage.SENSOR__V:
				getV().clear();
				getV().addAll((Collection<? extends Double>)newValue);
				return;
			case MeterPackage.SENSOR__I:
				getI().clear();
				getI().addAll((Collection<? extends Double>)newValue);
				return;
			case MeterPackage.SENSOR__P:
				getP().clear();
				getP().addAll((Collection<? extends Double>)newValue);
				return;
			case MeterPackage.SENSOR__Q:
				getQ().clear();
				getQ().addAll((Collection<? extends Double>)newValue);
				return;
			case MeterPackage.SENSOR__PHASES:
				getPhases().clear();
				getPhases().addAll((Collection<? extends Integer>)newValue);
				return;
			case MeterPackage.SENSOR__CONN:
				setConn((connectionType)newValue);
				return;
			case MeterPackage.SENSOR__PCT_ERROR:
				setPctError((Double)newValue);
				return;
			case MeterPackage.SENSOR__ACTION:
				setAction((sensorAction)newValue);
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
			case MeterPackage.SENSOR__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case MeterPackage.SENSOR__TERMINAL:
				setTerminal(TERMINAL_EDEFAULT);
				return;
			case MeterPackage.SENSOR__MODES:
				getModes().clear();
				return;
			case MeterPackage.SENSOR__V:
				getV().clear();
				return;
			case MeterPackage.SENSOR__I:
				getI().clear();
				return;
			case MeterPackage.SENSOR__P:
				getP().clear();
				return;
			case MeterPackage.SENSOR__Q:
				getQ().clear();
				return;
			case MeterPackage.SENSOR__PHASES:
				getPhases().clear();
				return;
			case MeterPackage.SENSOR__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case MeterPackage.SENSOR__PCT_ERROR:
				setPctError(PCT_ERROR_EDEFAULT);
				return;
			case MeterPackage.SENSOR__ACTION:
				setAction(ACTION_EDEFAULT);
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
			case MeterPackage.SENSOR__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case MeterPackage.SENSOR__TERMINAL:
				return terminal != TERMINAL_EDEFAULT;
			case MeterPackage.SENSOR__MODES:
				return modes != null && !modes.isEmpty();
			case MeterPackage.SENSOR__V:
				return v != null && !v.isEmpty();
			case MeterPackage.SENSOR__I:
				return i != null && !i.isEmpty();
			case MeterPackage.SENSOR__P:
				return p != null && !p.isEmpty();
			case MeterPackage.SENSOR__Q:
				return q != null && !q.isEmpty();
			case MeterPackage.SENSOR__PHASES:
				return phases != null && !phases.isEmpty();
			case MeterPackage.SENSOR__CONN:
				return conn != CONN_EDEFAULT;
			case MeterPackage.SENSOR__PCT_ERROR:
				return pctError != PCT_ERROR_EDEFAULT;
			case MeterPackage.SENSOR__ACTION:
				return action != ACTION_EDEFAULT;
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
		result.append(" (element: ");
		result.append(element);
		result.append(", terminal: ");
		result.append(terminal);
		result.append(", modes: ");
		result.append(modes);
		result.append(", v: ");
		result.append(v);
		result.append(", i: ");
		result.append(i);
		result.append(", p: ");
		result.append(p);
		result.append(", q: ");
		result.append(q);
		result.append(", phases: ");
		result.append(phases);
		result.append(", conn: ");
		result.append(conn);
		result.append(", pctError: ");
		result.append(pctError);
		result.append(", action: ");
		result.append(action);
		result.append(')');
		return result.toString();
	}

} //SensorImpl
