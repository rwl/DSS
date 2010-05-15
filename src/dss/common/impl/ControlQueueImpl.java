/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package dss.common.impl;

import dss.common.CommonPackage;
import dss.common.ControlQueue;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control Queue</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.ControlQueueImpl#isEmpty <em>Empty</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ControlQueueImpl extends EObjectImpl implements ControlQueue {
	/**
	 * The default value of the '{@link #isEmpty() <em>Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EMPTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEmpty() <em>Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEmpty()
	 * @generated
	 * @ordered
	 */
	protected boolean empty = EMPTY_EDEFAULT;

	/**
	 * This is true if the Empty attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean emptyESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlQueueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.CONTROL_QUEUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmpty(boolean newEmpty) {
		boolean oldEmpty = empty;
		empty = newEmpty;
		boolean oldEmptyESet = emptyESet;
		emptyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CONTROL_QUEUE__EMPTY, oldEmpty, empty, !oldEmptyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEmpty() {
		boolean oldEmpty = empty;
		boolean oldEmptyESet = emptyESet;
		empty = EMPTY_EDEFAULT;
		emptyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.CONTROL_QUEUE__EMPTY, oldEmpty, EMPTY_EDEFAULT, oldEmptyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEmpty() {
		return emptyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.CONTROL_QUEUE__EMPTY:
				return isEmpty();
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
			case CommonPackage.CONTROL_QUEUE__EMPTY:
				setEmpty((Boolean)newValue);
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
			case CommonPackage.CONTROL_QUEUE__EMPTY:
				unsetEmpty();
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
			case CommonPackage.CONTROL_QUEUE__EMPTY:
				return isSetEmpty();
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
		result.append(" (empty: ");
		if (emptyESet) result.append(empty); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ControlQueueImpl
