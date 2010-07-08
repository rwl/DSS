/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common.impl;

import electrickery.common.CommonPackage;
import electrickery.common.Conductor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conductor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.ConductorImpl#isClosed <em>Closed</em>}</li>
 *   <li>{@link electrickery.common.impl.ConductorImpl#isFuseBlown <em>Fuse Blown</em>}</li>
 *   <li>{@link electrickery.common.impl.ConductorImpl#getAccumISqT <em>Accum ISq T</em>}</li>
 *   <li>{@link electrickery.common.impl.ConductorImpl#getTccName <em>Tcc Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ConductorImpl extends EObjectImpl implements Conductor {
	/**
	 * The default value of the '{@link #isClosed() <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClosed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLOSED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isClosed() <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClosed()
	 * @generated
	 * @ordered
	 */
	protected boolean closed = CLOSED_EDEFAULT;

	/**
	 * The default value of the '{@link #isFuseBlown() <em>Fuse Blown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFuseBlown()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FUSE_BLOWN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFuseBlown() <em>Fuse Blown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFuseBlown()
	 * @generated
	 * @ordered
	 */
	protected boolean fuseBlown = FUSE_BLOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getAccumISqT() <em>Accum ISq T</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccumISqT()
	 * @generated
	 * @ordered
	 */
	protected static final double ACCUM_ISQ_T_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAccumISqT() <em>Accum ISq T</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccumISqT()
	 * @generated
	 * @ordered
	 */
	protected double accumISqT = ACCUM_ISQ_T_EDEFAULT;

	/**
	 * The default value of the '{@link #getTccName() <em>Tcc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTccName()
	 * @generated
	 * @ordered
	 */
	protected static final String TCC_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTccName() <em>Tcc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTccName()
	 * @generated
	 * @ordered
	 */
	protected String tccName = TCC_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConductorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.CONDUCTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClosed() {
		return closed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClosed(boolean newClosed) {
		boolean oldClosed = closed;
		closed = newClosed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CONDUCTOR__CLOSED, oldClosed, closed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFuseBlown() {
		return fuseBlown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFuseBlown(boolean newFuseBlown) {
		boolean oldFuseBlown = fuseBlown;
		fuseBlown = newFuseBlown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CONDUCTOR__FUSE_BLOWN, oldFuseBlown, fuseBlown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getAccumISqT() {
		return accumISqT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccumISqT(double newAccumISqT) {
		double oldAccumISqT = accumISqT;
		accumISqT = newAccumISqT;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CONDUCTOR__ACCUM_ISQ_T, oldAccumISqT, accumISqT));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTccName() {
		return tccName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTccName(String newTccName) {
		String oldTccName = tccName;
		tccName = newTccName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.CONDUCTOR__TCC_NAME, oldTccName, tccName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.CONDUCTOR__CLOSED:
				return isClosed();
			case CommonPackage.CONDUCTOR__FUSE_BLOWN:
				return isFuseBlown();
			case CommonPackage.CONDUCTOR__ACCUM_ISQ_T:
				return getAccumISqT();
			case CommonPackage.CONDUCTOR__TCC_NAME:
				return getTccName();
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
			case CommonPackage.CONDUCTOR__CLOSED:
				setClosed((Boolean)newValue);
				return;
			case CommonPackage.CONDUCTOR__FUSE_BLOWN:
				setFuseBlown((Boolean)newValue);
				return;
			case CommonPackage.CONDUCTOR__ACCUM_ISQ_T:
				setAccumISqT((Double)newValue);
				return;
			case CommonPackage.CONDUCTOR__TCC_NAME:
				setTccName((String)newValue);
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
			case CommonPackage.CONDUCTOR__CLOSED:
				setClosed(CLOSED_EDEFAULT);
				return;
			case CommonPackage.CONDUCTOR__FUSE_BLOWN:
				setFuseBlown(FUSE_BLOWN_EDEFAULT);
				return;
			case CommonPackage.CONDUCTOR__ACCUM_ISQ_T:
				setAccumISqT(ACCUM_ISQ_T_EDEFAULT);
				return;
			case CommonPackage.CONDUCTOR__TCC_NAME:
				setTccName(TCC_NAME_EDEFAULT);
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
			case CommonPackage.CONDUCTOR__CLOSED:
				return closed != CLOSED_EDEFAULT;
			case CommonPackage.CONDUCTOR__FUSE_BLOWN:
				return fuseBlown != FUSE_BLOWN_EDEFAULT;
			case CommonPackage.CONDUCTOR__ACCUM_ISQ_T:
				return accumISqT != ACCUM_ISQ_T_EDEFAULT;
			case CommonPackage.CONDUCTOR__TCC_NAME:
				return TCC_NAME_EDEFAULT == null ? tccName != null : !TCC_NAME_EDEFAULT.equals(tccName);
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
		result.append(" (closed: ");
		result.append(closed);
		result.append(", fuseBlown: ");
		result.append(fuseBlown);
		result.append(", accumISqT: ");
		result.append(accumISqT);
		result.append(", tccName: ");
		result.append(tccName);
		result.append(')');
		return result.toString();
	}

} //ConductorImpl
