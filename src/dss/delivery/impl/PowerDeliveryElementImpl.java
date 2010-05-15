/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery.impl;

import dss.common.impl.CircuitElementImpl;

import dss.delivery.DeliveryPackage;
import dss.delivery.PowerDeliveryElement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Power Delivery Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.delivery.impl.PowerDeliveryElementImpl#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link dss.delivery.impl.PowerDeliveryElementImpl#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link dss.delivery.impl.PowerDeliveryElementImpl#getFaultRate <em>Fault Rate</em>}</li>
 *   <li>{@link dss.delivery.impl.PowerDeliveryElementImpl#getPctPerm <em>Pct Perm</em>}</li>
 *   <li>{@link dss.delivery.impl.PowerDeliveryElementImpl#getRepair <em>Repair</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PowerDeliveryElementImpl extends CircuitElementImpl implements PowerDeliveryElement {
	/**
	 * The default value of the '{@link #getNormAmps() <em>Norm Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormAmps()
	 * @generated
	 * @ordered
	 */
	protected static final double NORM_AMPS_EDEFAULT = 400.0;

	/**
	 * The cached value of the '{@link #getNormAmps() <em>Norm Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormAmps()
	 * @generated
	 * @ordered
	 */
	protected double normAmps = NORM_AMPS_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmergAmps() <em>Emerg Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergAmps()
	 * @generated
	 * @ordered
	 */
	protected static final double EMERG_AMPS_EDEFAULT = 600.0;

	/**
	 * The cached value of the '{@link #getEmergAmps() <em>Emerg Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergAmps()
	 * @generated
	 * @ordered
	 */
	protected double emergAmps = EMERG_AMPS_EDEFAULT;

	/**
	 * The default value of the '{@link #getFaultRate() <em>Fault Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultRate()
	 * @generated
	 * @ordered
	 */
	protected static final double FAULT_RATE_EDEFAULT = 0.1;

	/**
	 * The cached value of the '{@link #getFaultRate() <em>Fault Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultRate()
	 * @generated
	 * @ordered
	 */
	protected double faultRate = FAULT_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctPerm() <em>Pct Perm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctPerm()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_PERM_EDEFAULT = 20.0;

	/**
	 * The cached value of the '{@link #getPctPerm() <em>Pct Perm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctPerm()
	 * @generated
	 * @ordered
	 */
	protected double pctPerm = PCT_PERM_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepair() <em>Repair</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepair()
	 * @generated
	 * @ordered
	 */
	protected static final int REPAIR_EDEFAULT = 3;

	/**
	 * The cached value of the '{@link #getRepair() <em>Repair</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepair()
	 * @generated
	 * @ordered
	 */
	protected int repair = REPAIR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PowerDeliveryElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeliveryPackage.Literals.POWER_DELIVERY_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getNormAmps() {
		return normAmps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormAmps(double newNormAmps) {
		double oldNormAmps = normAmps;
		normAmps = newNormAmps;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.POWER_DELIVERY_ELEMENT__NORM_AMPS, oldNormAmps, normAmps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getEmergAmps() {
		return emergAmps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmergAmps(double newEmergAmps) {
		double oldEmergAmps = emergAmps;
		emergAmps = newEmergAmps;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.POWER_DELIVERY_ELEMENT__EMERG_AMPS, oldEmergAmps, emergAmps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFaultRate() {
		return faultRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultRate(double newFaultRate) {
		double oldFaultRate = faultRate;
		faultRate = newFaultRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.POWER_DELIVERY_ELEMENT__FAULT_RATE, oldFaultRate, faultRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctPerm() {
		return pctPerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctPerm(double newPctPerm) {
		double oldPctPerm = pctPerm;
		pctPerm = newPctPerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.POWER_DELIVERY_ELEMENT__PCT_PERM, oldPctPerm, pctPerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRepair() {
		return repair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepair(int newRepair) {
		int oldRepair = repair;
		repair = newRepair;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.POWER_DELIVERY_ELEMENT__REPAIR, oldRepair, repair));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__NORM_AMPS:
				return getNormAmps();
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__EMERG_AMPS:
				return getEmergAmps();
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__FAULT_RATE:
				return getFaultRate();
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__PCT_PERM:
				return getPctPerm();
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__REPAIR:
				return getRepair();
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
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__NORM_AMPS:
				setNormAmps((Double)newValue);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__EMERG_AMPS:
				setEmergAmps((Double)newValue);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__FAULT_RATE:
				setFaultRate((Double)newValue);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__PCT_PERM:
				setPctPerm((Double)newValue);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__REPAIR:
				setRepair((Integer)newValue);
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
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__NORM_AMPS:
				setNormAmps(NORM_AMPS_EDEFAULT);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__EMERG_AMPS:
				setEmergAmps(EMERG_AMPS_EDEFAULT);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__FAULT_RATE:
				setFaultRate(FAULT_RATE_EDEFAULT);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__PCT_PERM:
				setPctPerm(PCT_PERM_EDEFAULT);
				return;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__REPAIR:
				setRepair(REPAIR_EDEFAULT);
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
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__NORM_AMPS:
				return normAmps != NORM_AMPS_EDEFAULT;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__EMERG_AMPS:
				return emergAmps != EMERG_AMPS_EDEFAULT;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__FAULT_RATE:
				return faultRate != FAULT_RATE_EDEFAULT;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__PCT_PERM:
				return pctPerm != PCT_PERM_EDEFAULT;
			case DeliveryPackage.POWER_DELIVERY_ELEMENT__REPAIR:
				return repair != REPAIR_EDEFAULT;
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
		result.append(" (normAmps: ");
		result.append(normAmps);
		result.append(", emergAmps: ");
		result.append(emergAmps);
		result.append(", faultRate: ");
		result.append(faultRate);
		result.append(", pctPerm: ");
		result.append(pctPerm);
		result.append(", repair: ");
		result.append(repair);
		result.append(')');
		return result.toString();
	}

} //PowerDeliveryElementImpl
