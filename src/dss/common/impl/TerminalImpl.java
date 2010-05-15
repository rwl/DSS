/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common.impl;

import dss.common.CommonPackage;
import dss.common.Conductor;
import dss.common.Terminal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Terminal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.TerminalImpl#getBusRef <em>Bus Ref</em>}</li>
 *   <li>{@link dss.common.impl.TerminalImpl#getTermNodeRef <em>Term Node Ref</em>}</li>
 *   <li>{@link dss.common.impl.TerminalImpl#getConductors <em>Conductors</em>}</li>
 *   <li>{@link dss.common.impl.TerminalImpl#isChecked <em>Checked</em>}</li>
 *   <li>{@link dss.common.impl.TerminalImpl#getNCond <em>NCond</em>}</li>
 *   <li>{@link dss.common.impl.TerminalImpl#getActiveConductor <em>Active Conductor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TerminalImpl extends EObjectImpl implements Terminal {
	/**
	 * The default value of the '{@link #getBusRef() <em>Bus Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusRef()
	 * @generated
	 * @ordered
	 */
	protected static final int BUS_REF_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getBusRef() <em>Bus Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusRef()
	 * @generated
	 * @ordered
	 */
	protected int busRef = BUS_REF_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTermNodeRef() <em>Term Node Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTermNodeRef()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> termNodeRef;

	/**
	 * The cached value of the '{@link #getConductors() <em>Conductors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConductors()
	 * @generated
	 * @ordered
	 */
	protected EList<Conductor> conductors;

	/**
	 * The default value of the '{@link #isChecked() <em>Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isChecked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHECKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isChecked() <em>Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isChecked()
	 * @generated
	 * @ordered
	 */
	protected boolean checked = CHECKED_EDEFAULT;

	/**
	 * The default value of the '{@link #getNCond() <em>NCond</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNCond()
	 * @generated
	 * @ordered
	 */
	protected static final int NCOND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNCond() <em>NCond</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNCond()
	 * @generated
	 * @ordered
	 */
	protected int nCond = NCOND_EDEFAULT;

	/**
	 * The default value of the '{@link #getActiveConductor() <em>Active Conductor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveConductor()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTIVE_CONDUCTOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getActiveConductor() <em>Active Conductor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveConductor()
	 * @generated
	 * @ordered
	 */
	protected int activeConductor = ACTIVE_CONDUCTOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TerminalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.TERMINAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getBusRef() {
		return busRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBusRef(int newBusRef) {
		int oldBusRef = busRef;
		busRef = newBusRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.TERMINAL__BUS_REF, oldBusRef, busRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getTermNodeRef() {
		if (termNodeRef == null) {
			termNodeRef = new EDataTypeUniqueEList<Integer>(Integer.class, this, CommonPackage.TERMINAL__TERM_NODE_REF);
		}
		return termNodeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Conductor> getConductors() {
		if (conductors == null) {
			conductors = new EObjectResolvingEList<Conductor>(Conductor.class, this, CommonPackage.TERMINAL__CONDUCTORS);
		}
		return conductors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChecked(boolean newChecked) {
		boolean oldChecked = checked;
		checked = newChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.TERMINAL__CHECKED, oldChecked, checked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNCond() {
		return nCond;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNCond(int newNCond) {
		int oldNCond = nCond;
		nCond = newNCond;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.TERMINAL__NCOND, oldNCond, nCond));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getActiveConductor() {
		return activeConductor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveConductor(int newActiveConductor) {
		int oldActiveConductor = activeConductor;
		activeConductor = newActiveConductor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.TERMINAL__ACTIVE_CONDUCTOR, oldActiveConductor, activeConductor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.TERMINAL__BUS_REF:
				return getBusRef();
			case CommonPackage.TERMINAL__TERM_NODE_REF:
				return getTermNodeRef();
			case CommonPackage.TERMINAL__CONDUCTORS:
				return getConductors();
			case CommonPackage.TERMINAL__CHECKED:
				return isChecked();
			case CommonPackage.TERMINAL__NCOND:
				return getNCond();
			case CommonPackage.TERMINAL__ACTIVE_CONDUCTOR:
				return getActiveConductor();
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
			case CommonPackage.TERMINAL__BUS_REF:
				setBusRef((Integer)newValue);
				return;
			case CommonPackage.TERMINAL__TERM_NODE_REF:
				getTermNodeRef().clear();
				getTermNodeRef().addAll((Collection<? extends Integer>)newValue);
				return;
			case CommonPackage.TERMINAL__CONDUCTORS:
				getConductors().clear();
				getConductors().addAll((Collection<? extends Conductor>)newValue);
				return;
			case CommonPackage.TERMINAL__CHECKED:
				setChecked((Boolean)newValue);
				return;
			case CommonPackage.TERMINAL__NCOND:
				setNCond((Integer)newValue);
				return;
			case CommonPackage.TERMINAL__ACTIVE_CONDUCTOR:
				setActiveConductor((Integer)newValue);
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
			case CommonPackage.TERMINAL__BUS_REF:
				setBusRef(BUS_REF_EDEFAULT);
				return;
			case CommonPackage.TERMINAL__TERM_NODE_REF:
				getTermNodeRef().clear();
				return;
			case CommonPackage.TERMINAL__CONDUCTORS:
				getConductors().clear();
				return;
			case CommonPackage.TERMINAL__CHECKED:
				setChecked(CHECKED_EDEFAULT);
				return;
			case CommonPackage.TERMINAL__NCOND:
				setNCond(NCOND_EDEFAULT);
				return;
			case CommonPackage.TERMINAL__ACTIVE_CONDUCTOR:
				setActiveConductor(ACTIVE_CONDUCTOR_EDEFAULT);
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
			case CommonPackage.TERMINAL__BUS_REF:
				return busRef != BUS_REF_EDEFAULT;
			case CommonPackage.TERMINAL__TERM_NODE_REF:
				return termNodeRef != null && !termNodeRef.isEmpty();
			case CommonPackage.TERMINAL__CONDUCTORS:
				return conductors != null && !conductors.isEmpty();
			case CommonPackage.TERMINAL__CHECKED:
				return checked != CHECKED_EDEFAULT;
			case CommonPackage.TERMINAL__NCOND:
				return nCond != NCOND_EDEFAULT;
			case CommonPackage.TERMINAL__ACTIVE_CONDUCTOR:
				return activeConductor != ACTIVE_CONDUCTOR_EDEFAULT;
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
		result.append(" (busRef: ");
		result.append(busRef);
		result.append(", termNodeRef: ");
		result.append(termNodeRef);
		result.append(", checked: ");
		result.append(checked);
		result.append(", nCond: ");
		result.append(nCond);
		result.append(", activeConductor: ");
		result.append(activeConductor);
		result.append(')');
		return result.toString();
	}

} //TerminalImpl
