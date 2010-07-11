/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general.impl;

import electrickery.common.lengthUnit;

import electrickery.general.GeneralPackage;
import electrickery.general.LineSpacing;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Line Spacing</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.general.impl.LineSpacingImpl#getNConds <em>NConds</em>}</li>
 *   <li>{@link electrickery.general.impl.LineSpacingImpl#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.general.impl.LineSpacingImpl#getX <em>X</em>}</li>
 *   <li>{@link electrickery.general.impl.LineSpacingImpl#getH <em>H</em>}</li>
 *   <li>{@link electrickery.general.impl.LineSpacingImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link electrickery.general.impl.LineSpacingImpl#getLike <em>Like</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LineSpacingImpl extends EObjectImpl implements LineSpacing {
	/**
	 * The default value of the '{@link #getNConds() <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNConds()
	 * @generated
	 * @ordered
	 */
	protected static final int NCONDS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNConds() <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNConds()
	 * @generated
	 * @ordered
	 */
	protected int nConds = NCONDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getNPhases() <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNPhases()
	 * @generated
	 * @ordered
	 */
	protected static final int NPHASES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNPhases() <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNPhases()
	 * @generated
	 * @ordered
	 */
	protected int nPhases = NPHASES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> x;

	/**
	 * The cached value of the '{@link #getH() <em>H</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getH()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> h;

	/**
	 * The default value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
	protected static final lengthUnit UNITS_EDEFAULT = lengthUnit.NONE;

	/**
	 * The cached value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
	protected lengthUnit units = UNITS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLike() <em>Like</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLike()
	 * @generated
	 * @ordered
	 */
	protected LineSpacing like;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LineSpacingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.LINE_SPACING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNConds() {
		return nConds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNConds(int newNConds) {
		int oldNConds = nConds;
		nConds = newNConds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_SPACING__NCONDS, oldNConds, nConds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNPhases() {
		return nPhases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNPhases(int newNPhases) {
		int oldNPhases = nPhases;
		nPhases = newNPhases;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_SPACING__NPHASES, oldNPhases, nPhases));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getX() {
		if (x == null) {
			x = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.LINE_SPACING__X);
		}
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getH() {
		if (h == null) {
			h = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.LINE_SPACING__H);
		}
		return h;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public lengthUnit getUnits() {
		return units;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnits(lengthUnit newUnits) {
		lengthUnit oldUnits = units;
		units = newUnits == null ? UNITS_EDEFAULT : newUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_SPACING__UNITS, oldUnits, units));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineSpacing getLike() {
		if (like != null && like.eIsProxy()) {
			InternalEObject oldLike = (InternalEObject)like;
			like = (LineSpacing)eResolveProxy(oldLike);
			if (like != oldLike) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_SPACING__LIKE, oldLike, like));
			}
		}
		return like;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineSpacing basicGetLike() {
		return like;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLike(LineSpacing newLike) {
		LineSpacing oldLike = like;
		like = newLike;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_SPACING__LIKE, oldLike, like));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.LINE_SPACING__NCONDS:
				return getNConds();
			case GeneralPackage.LINE_SPACING__NPHASES:
				return getNPhases();
			case GeneralPackage.LINE_SPACING__X:
				return getX();
			case GeneralPackage.LINE_SPACING__H:
				return getH();
			case GeneralPackage.LINE_SPACING__UNITS:
				return getUnits();
			case GeneralPackage.LINE_SPACING__LIKE:
				if (resolve) return getLike();
				return basicGetLike();
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
			case GeneralPackage.LINE_SPACING__NCONDS:
				setNConds((Integer)newValue);
				return;
			case GeneralPackage.LINE_SPACING__NPHASES:
				setNPhases((Integer)newValue);
				return;
			case GeneralPackage.LINE_SPACING__X:
				getX().clear();
				getX().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.LINE_SPACING__H:
				getH().clear();
				getH().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.LINE_SPACING__UNITS:
				setUnits((lengthUnit)newValue);
				return;
			case GeneralPackage.LINE_SPACING__LIKE:
				setLike((LineSpacing)newValue);
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
			case GeneralPackage.LINE_SPACING__NCONDS:
				setNConds(NCONDS_EDEFAULT);
				return;
			case GeneralPackage.LINE_SPACING__NPHASES:
				setNPhases(NPHASES_EDEFAULT);
				return;
			case GeneralPackage.LINE_SPACING__X:
				getX().clear();
				return;
			case GeneralPackage.LINE_SPACING__H:
				getH().clear();
				return;
			case GeneralPackage.LINE_SPACING__UNITS:
				setUnits(UNITS_EDEFAULT);
				return;
			case GeneralPackage.LINE_SPACING__LIKE:
				setLike((LineSpacing)null);
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
			case GeneralPackage.LINE_SPACING__NCONDS:
				return nConds != NCONDS_EDEFAULT;
			case GeneralPackage.LINE_SPACING__NPHASES:
				return nPhases != NPHASES_EDEFAULT;
			case GeneralPackage.LINE_SPACING__X:
				return x != null && !x.isEmpty();
			case GeneralPackage.LINE_SPACING__H:
				return h != null && !h.isEmpty();
			case GeneralPackage.LINE_SPACING__UNITS:
				return units != UNITS_EDEFAULT;
			case GeneralPackage.LINE_SPACING__LIKE:
				return like != null;
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
		result.append(" (nConds: ");
		result.append(nConds);
		result.append(", nPhases: ");
		result.append(nPhases);
		result.append(", x: ");
		result.append(x);
		result.append(", h: ");
		result.append(h);
		result.append(", units: ");
		result.append(units);
		result.append(')');
		return result.toString();
	}

} //LineSpacingImpl
