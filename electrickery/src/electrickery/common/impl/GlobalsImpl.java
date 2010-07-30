/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common.impl;

import electrickery.common.CommonPackage;
import electrickery.common.Globals;

import electrickery.executive.Executive;
import electrickery.executive.ExecutivePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Globals</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.GlobalsImpl#isSolutionAbort <em>Solution Abort</em>}</li>
 *   <li>{@link electrickery.common.impl.GlobalsImpl#getExecutives <em>Executives</em>}</li>
 *   <li>{@link electrickery.common.impl.GlobalsImpl#isSolutionWasAttempted <em>Solution Was Attempted</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GlobalsImpl extends EObjectImpl implements Globals {
	/**
	 * The default value of the '{@link #isSolutionAbort() <em>Solution Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSolutionAbort()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SOLUTION_ABORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSolutionAbort() <em>Solution Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSolutionAbort()
	 * @generated
	 * @ordered
	 */
	protected boolean solutionAbort = SOLUTION_ABORT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExecutives() <em>Executives</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutives()
	 * @generated
	 * @ordered
	 */
	protected Executive executives;

	/**
	 * The default value of the '{@link #isSolutionWasAttempted() <em>Solution Was Attempted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSolutionWasAttempted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SOLUTION_WAS_ATTEMPTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSolutionWasAttempted() <em>Solution Was Attempted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSolutionWasAttempted()
	 * @generated
	 * @ordered
	 */
	protected boolean solutionWasAttempted = SOLUTION_WAS_ATTEMPTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GlobalsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.GLOBALS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSolutionAbort() {
		return solutionAbort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSolutionAbort(boolean newSolutionAbort) {
		boolean oldSolutionAbort = solutionAbort;
		solutionAbort = newSolutionAbort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.GLOBALS__SOLUTION_ABORT, oldSolutionAbort, solutionAbort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executive getExecutives() {
		if (executives != null && executives.eIsProxy()) {
			InternalEObject oldExecutives = (InternalEObject)executives;
			executives = (Executive)eResolveProxy(oldExecutives);
			if (executives != oldExecutives) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.GLOBALS__EXECUTIVES, oldExecutives, executives));
			}
		}
		return executives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executive basicGetExecutives() {
		return executives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutives(Executive newExecutives, NotificationChain msgs) {
		Executive oldExecutives = executives;
		executives = newExecutives;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CommonPackage.GLOBALS__EXECUTIVES, oldExecutives, newExecutives);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutives(Executive newExecutives) {
		if (newExecutives != executives) {
			NotificationChain msgs = null;
			if (executives != null)
				msgs = ((InternalEObject)executives).eInverseRemove(this, ExecutivePackage.EXECUTIVE__GLOBALS, Executive.class, msgs);
			if (newExecutives != null)
				msgs = ((InternalEObject)newExecutives).eInverseAdd(this, ExecutivePackage.EXECUTIVE__GLOBALS, Executive.class, msgs);
			msgs = basicSetExecutives(newExecutives, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.GLOBALS__EXECUTIVES, newExecutives, newExecutives));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSolutionWasAttempted() {
		return solutionWasAttempted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSolutionWasAttempted(boolean newSolutionWasAttempted) {
		boolean oldSolutionWasAttempted = solutionWasAttempted;
		solutionWasAttempted = newSolutionWasAttempted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.GLOBALS__SOLUTION_WAS_ATTEMPTED, oldSolutionWasAttempted, solutionWasAttempted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.GLOBALS__EXECUTIVES:
				if (executives != null)
					msgs = ((InternalEObject)executives).eInverseRemove(this, ExecutivePackage.EXECUTIVE__GLOBALS, Executive.class, msgs);
				return basicSetExecutives((Executive)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.GLOBALS__EXECUTIVES:
				return basicSetExecutives(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.GLOBALS__SOLUTION_ABORT:
				return isSolutionAbort();
			case CommonPackage.GLOBALS__EXECUTIVES:
				if (resolve) return getExecutives();
				return basicGetExecutives();
			case CommonPackage.GLOBALS__SOLUTION_WAS_ATTEMPTED:
				return isSolutionWasAttempted();
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
			case CommonPackage.GLOBALS__SOLUTION_ABORT:
				setSolutionAbort((Boolean)newValue);
				return;
			case CommonPackage.GLOBALS__EXECUTIVES:
				setExecutives((Executive)newValue);
				return;
			case CommonPackage.GLOBALS__SOLUTION_WAS_ATTEMPTED:
				setSolutionWasAttempted((Boolean)newValue);
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
			case CommonPackage.GLOBALS__SOLUTION_ABORT:
				setSolutionAbort(SOLUTION_ABORT_EDEFAULT);
				return;
			case CommonPackage.GLOBALS__EXECUTIVES:
				setExecutives((Executive)null);
				return;
			case CommonPackage.GLOBALS__SOLUTION_WAS_ATTEMPTED:
				setSolutionWasAttempted(SOLUTION_WAS_ATTEMPTED_EDEFAULT);
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
			case CommonPackage.GLOBALS__SOLUTION_ABORT:
				return solutionAbort != SOLUTION_ABORT_EDEFAULT;
			case CommonPackage.GLOBALS__EXECUTIVES:
				return executives != null;
			case CommonPackage.GLOBALS__SOLUTION_WAS_ATTEMPTED:
				return solutionWasAttempted != SOLUTION_WAS_ATTEMPTED_EDEFAULT;
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
		result.append(" (solutionAbort: ");
		result.append(solutionAbort);
		result.append(", solutionWasAttempted: ");
		result.append(solutionWasAttempted);
		result.append(')');
		return result.toString();
	}

} //GlobalsImpl
