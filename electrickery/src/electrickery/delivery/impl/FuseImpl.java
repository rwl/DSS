/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery.impl;


import electrickery.common.tripAction;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.Fuse;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fuse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getMonitoredObj <em>Monitored Obj</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getMonitorTerm <em>Monitor Term</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getSwitchedObj <em>Switched Obj</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getSwitchedTerm <em>Switched Term</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getFuseCurve <em>Fuse Curve</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getRatedCurrent <em>Rated Current</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getDelay <em>Delay</em>}</li>
 *   <li>{@link electrickery.delivery.impl.FuseImpl#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FuseImpl extends PowerDeliveryElementImpl implements Fuse {
	/**
	 * The default value of the '{@link #getMonitoredObj() <em>Monitored Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredObj()
	 * @generated
	 * @ordered
	 */
	protected static final String MONITORED_OBJ_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMonitoredObj() <em>Monitored Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredObj()
	 * @generated
	 * @ordered
	 */
	protected String monitoredObj = MONITORED_OBJ_EDEFAULT;

	/**
	 * The default value of the '{@link #getMonitorTerm() <em>Monitor Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitorTerm()
	 * @generated
	 * @ordered
	 */
	protected static final int MONITOR_TERM_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getMonitorTerm() <em>Monitor Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitorTerm()
	 * @generated
	 * @ordered
	 */
	protected int monitorTerm = MONITOR_TERM_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwitchedObj() <em>Switched Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedObj()
	 * @generated
	 * @ordered
	 */
	protected static final String SWITCHED_OBJ_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSwitchedObj() <em>Switched Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedObj()
	 * @generated
	 * @ordered
	 */
	protected String switchedObj = SWITCHED_OBJ_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwitchedTerm() <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedTerm()
	 * @generated
	 * @ordered
	 */
	protected static final int SWITCHED_TERM_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getSwitchedTerm() <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedTerm()
	 * @generated
	 * @ordered
	 */
	protected int switchedTerm = SWITCHED_TERM_EDEFAULT;

	/**
	 * The default value of the '{@link #getFuseCurve() <em>Fuse Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFuseCurve()
	 * @generated
	 * @ordered
	 */
	protected static final String FUSE_CURVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFuseCurve() <em>Fuse Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFuseCurve()
	 * @generated
	 * @ordered
	 */
	protected String fuseCurve = FUSE_CURVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRatedCurrent() <em>Rated Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatedCurrent()
	 * @generated
	 * @ordered
	 */
	protected static final double RATED_CURRENT_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getRatedCurrent() <em>Rated Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatedCurrent()
	 * @generated
	 * @ordered
	 */
	protected double ratedCurrent = RATED_CURRENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final double DELAY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected double delay = DELAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final tripAction ACTION_EDEFAULT = tripAction.TRIP_OPEN;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected tripAction action = ACTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FuseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeliveryPackage.Literals.FUSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMonitoredObj() {
		return monitoredObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoredObj(String newMonitoredObj) {
		String oldMonitoredObj = monitoredObj;
		monitoredObj = newMonitoredObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__MONITORED_OBJ, oldMonitoredObj, monitoredObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMonitorTerm() {
		return monitorTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitorTerm(int newMonitorTerm) {
		int oldMonitorTerm = monitorTerm;
		monitorTerm = newMonitorTerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__MONITOR_TERM, oldMonitorTerm, monitorTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSwitchedObj() {
		return switchedObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchedObj(String newSwitchedObj) {
		String oldSwitchedObj = switchedObj;
		switchedObj = newSwitchedObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__SWITCHED_OBJ, oldSwitchedObj, switchedObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSwitchedTerm() {
		return switchedTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchedTerm(int newSwitchedTerm) {
		int oldSwitchedTerm = switchedTerm;
		switchedTerm = newSwitchedTerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__SWITCHED_TERM, oldSwitchedTerm, switchedTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFuseCurve() {
		return fuseCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFuseCurve(String newFuseCurve) {
		String oldFuseCurve = fuseCurve;
		fuseCurve = newFuseCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__FUSE_CURVE, oldFuseCurve, fuseCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRatedCurrent() {
		return ratedCurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRatedCurrent(double newRatedCurrent) {
		double oldRatedCurrent = ratedCurrent;
		ratedCurrent = newRatedCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__RATED_CURRENT, oldRatedCurrent, ratedCurrent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDelay() {
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelay(double newDelay) {
		double oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__DELAY, oldDelay, delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tripAction getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(tripAction newAction) {
		tripAction oldAction = action;
		action = newAction == null ? ACTION_EDEFAULT : newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.FUSE__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeliveryPackage.FUSE__MONITORED_OBJ:
				return getMonitoredObj();
			case DeliveryPackage.FUSE__MONITOR_TERM:
				return getMonitorTerm();
			case DeliveryPackage.FUSE__SWITCHED_OBJ:
				return getSwitchedObj();
			case DeliveryPackage.FUSE__SWITCHED_TERM:
				return getSwitchedTerm();
			case DeliveryPackage.FUSE__FUSE_CURVE:
				return getFuseCurve();
			case DeliveryPackage.FUSE__RATED_CURRENT:
				return getRatedCurrent();
			case DeliveryPackage.FUSE__DELAY:
				return getDelay();
			case DeliveryPackage.FUSE__ACTION:
				return getAction();
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
			case DeliveryPackage.FUSE__MONITORED_OBJ:
				setMonitoredObj((String)newValue);
				return;
			case DeliveryPackage.FUSE__MONITOR_TERM:
				setMonitorTerm((Integer)newValue);
				return;
			case DeliveryPackage.FUSE__SWITCHED_OBJ:
				setSwitchedObj((String)newValue);
				return;
			case DeliveryPackage.FUSE__SWITCHED_TERM:
				setSwitchedTerm((Integer)newValue);
				return;
			case DeliveryPackage.FUSE__FUSE_CURVE:
				setFuseCurve((String)newValue);
				return;
			case DeliveryPackage.FUSE__RATED_CURRENT:
				setRatedCurrent((Double)newValue);
				return;
			case DeliveryPackage.FUSE__DELAY:
				setDelay((Double)newValue);
				return;
			case DeliveryPackage.FUSE__ACTION:
				setAction((tripAction)newValue);
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
			case DeliveryPackage.FUSE__MONITORED_OBJ:
				setMonitoredObj(MONITORED_OBJ_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__MONITOR_TERM:
				setMonitorTerm(MONITOR_TERM_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__SWITCHED_OBJ:
				setSwitchedObj(SWITCHED_OBJ_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__SWITCHED_TERM:
				setSwitchedTerm(SWITCHED_TERM_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__FUSE_CURVE:
				setFuseCurve(FUSE_CURVE_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__RATED_CURRENT:
				setRatedCurrent(RATED_CURRENT_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case DeliveryPackage.FUSE__ACTION:
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
			case DeliveryPackage.FUSE__MONITORED_OBJ:
				return MONITORED_OBJ_EDEFAULT == null ? monitoredObj != null : !MONITORED_OBJ_EDEFAULT.equals(monitoredObj);
			case DeliveryPackage.FUSE__MONITOR_TERM:
				return monitorTerm != MONITOR_TERM_EDEFAULT;
			case DeliveryPackage.FUSE__SWITCHED_OBJ:
				return SWITCHED_OBJ_EDEFAULT == null ? switchedObj != null : !SWITCHED_OBJ_EDEFAULT.equals(switchedObj);
			case DeliveryPackage.FUSE__SWITCHED_TERM:
				return switchedTerm != SWITCHED_TERM_EDEFAULT;
			case DeliveryPackage.FUSE__FUSE_CURVE:
				return FUSE_CURVE_EDEFAULT == null ? fuseCurve != null : !FUSE_CURVE_EDEFAULT.equals(fuseCurve);
			case DeliveryPackage.FUSE__RATED_CURRENT:
				return ratedCurrent != RATED_CURRENT_EDEFAULT;
			case DeliveryPackage.FUSE__DELAY:
				return delay != DELAY_EDEFAULT;
			case DeliveryPackage.FUSE__ACTION:
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
		result.append(" (monitoredObj: ");
		result.append(monitoredObj);
		result.append(", monitorTerm: ");
		result.append(monitorTerm);
		result.append(", switchedObj: ");
		result.append(switchedObj);
		result.append(", switchedTerm: ");
		result.append(switchedTerm);
		result.append(", fuseCurve: ");
		result.append(fuseCurve);
		result.append(", ratedCurrent: ");
		result.append(ratedCurrent);
		result.append(", delay: ");
		result.append(delay);
		result.append(", action: ");
		result.append(action);
		result.append(')');
		return result.toString();
	}

} //FuseImpl
