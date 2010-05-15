/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general.impl;

import dss.general.GeneralPackage;
import dss.general.TimeCurrentCurve;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Current Curve</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.general.impl.TimeCurrentCurveImpl#getNPts <em>NPts</em>}</li>
 *   <li>{@link dss.general.impl.TimeCurrentCurveImpl#getCArray <em>CArray</em>}</li>
 *   <li>{@link dss.general.impl.TimeCurrentCurveImpl#getTArray <em>TArray</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeCurrentCurveImpl extends EObjectImpl implements TimeCurrentCurve {
	/**
	 * The default value of the '{@link #getNPts() <em>NPts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNPts()
	 * @generated
	 * @ordered
	 */
	protected static final int NPTS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNPts() <em>NPts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNPts()
	 * @generated
	 * @ordered
	 */
	protected int nPts = NPTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCArray() <em>CArray</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCArray()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> cArray;

	/**
	 * The cached value of the '{@link #getTArray() <em>TArray</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTArray()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> tArray;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeCurrentCurveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.TIME_CURRENT_CURVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNPts() {
		return nPts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNPts(int newNPts) {
		int oldNPts = nPts;
		nPts = newNPts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.TIME_CURRENT_CURVE__NPTS, oldNPts, nPts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getCArray() {
		if (cArray == null) {
			cArray = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.TIME_CURRENT_CURVE__CARRAY);
		}
		return cArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getTArray() {
		if (tArray == null) {
			tArray = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.TIME_CURRENT_CURVE__TARRAY);
		}
		return tArray;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.TIME_CURRENT_CURVE__NPTS:
				return getNPts();
			case GeneralPackage.TIME_CURRENT_CURVE__CARRAY:
				return getCArray();
			case GeneralPackage.TIME_CURRENT_CURVE__TARRAY:
				return getTArray();
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
			case GeneralPackage.TIME_CURRENT_CURVE__NPTS:
				setNPts((Integer)newValue);
				return;
			case GeneralPackage.TIME_CURRENT_CURVE__CARRAY:
				getCArray().clear();
				getCArray().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.TIME_CURRENT_CURVE__TARRAY:
				getTArray().clear();
				getTArray().addAll((Collection<? extends Double>)newValue);
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
			case GeneralPackage.TIME_CURRENT_CURVE__NPTS:
				setNPts(NPTS_EDEFAULT);
				return;
			case GeneralPackage.TIME_CURRENT_CURVE__CARRAY:
				getCArray().clear();
				return;
			case GeneralPackage.TIME_CURRENT_CURVE__TARRAY:
				getTArray().clear();
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
			case GeneralPackage.TIME_CURRENT_CURVE__NPTS:
				return nPts != NPTS_EDEFAULT;
			case GeneralPackage.TIME_CURRENT_CURVE__CARRAY:
				return cArray != null && !cArray.isEmpty();
			case GeneralPackage.TIME_CURRENT_CURVE__TARRAY:
				return tArray != null && !tArray.isEmpty();
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
		result.append(" (nPts: ");
		result.append(nPts);
		result.append(", cArray: ");
		result.append(cArray);
		result.append(", tArray: ");
		result.append(tArray);
		result.append(')');
		return result.toString();
	}

} //TimeCurrentCurveImpl
