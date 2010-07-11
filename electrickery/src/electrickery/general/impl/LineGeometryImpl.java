/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.impl;


import electrickery.common.impl.NamedImpl;
import electrickery.common.lengthUnit;
import electrickery.general.GeneralPackage;
import electrickery.general.LineGeometry;
import electrickery.general.LineSpacing;
import electrickery.general.WireData;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Line Geometry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getNConds <em>NConds</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getCond <em>Cond</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getWire <em>Wire</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getSpacing <em>Spacing</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getX <em>X</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getH <em>H</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#isReduce <em>Reduce</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getWires <em>Wires</em>}</li>
 *   <li>{@link electrickery.general.impl.LineGeometryImpl#getLike <em>Like</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LineGeometryImpl extends NamedImpl implements LineGeometry {
	/**
	 * The default value of the '{@link #getNConds() <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNConds()
	 * @generated
	 * @ordered
	 */
	protected static final int NCONDS_EDEFAULT = 3;

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
	protected static final int NPHASES_EDEFAULT = 3;

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
	 * The default value of the '{@link #getCond() <em>Cond</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCond()
	 * @generated
	 * @ordered
	 */
	protected static final int COND_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getCond() <em>Cond</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCond()
	 * @generated
	 * @ordered
	 */
	protected int cond = COND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWire() <em>Wire</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWire()
	 * @generated
	 * @ordered
	 */
	protected WireData wire;

	/**
	 * The cached value of the '{@link #getSpacing() <em>Spacing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpacing()
	 * @generated
	 * @ordered
	 */
	protected LineSpacing spacing;

	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final double X_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected double x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getH() <em>H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getH()
	 * @generated
	 * @ordered
	 */
	protected static final double H_EDEFAULT = 32.0;

	/**
	 * The cached value of the '{@link #getH() <em>H</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getH()
	 * @generated
	 * @ordered
	 */
	protected double h = H_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
	protected static final lengthUnit UNITS_EDEFAULT = lengthUnit.FT;

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
	 * The default value of the '{@link #getNormAmps() <em>Norm Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormAmps()
	 * @generated
	 * @ordered
	 */
	protected static final double NORM_AMPS_EDEFAULT = 0.0;

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
	protected static final double EMERG_AMPS_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #isReduce() <em>Reduce</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReduce()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REDUCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReduce() <em>Reduce</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReduce()
	 * @generated
	 * @ordered
	 */
	protected boolean reduce = REDUCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWires() <em>Wires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireData> wires;

	/**
	 * The cached value of the '{@link #getLike() <em>Like</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLike()
	 * @generated
	 * @ordered
	 */
	protected LineGeometry like;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LineGeometryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.LINE_GEOMETRY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__NCONDS, oldNConds, nConds));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__NPHASES, oldNPhases, nPhases));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCond() {
		return cond;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCond(int newCond) {
		int oldCond = cond;
		cond = newCond;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__COND, oldCond, cond));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WireData getWire() {
		if (wire != null && wire.eIsProxy()) {
			InternalEObject oldWire = (InternalEObject)wire;
			wire = (WireData)eResolveProxy(oldWire);
			if (wire != oldWire) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_GEOMETRY__WIRE, oldWire, wire));
			}
		}
		return wire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WireData basicGetWire() {
		return wire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWire(WireData newWire) {
		WireData oldWire = wire;
		wire = newWire;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__WIRE, oldWire, wire));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireData> getWires() {
		if (wires == null) {
			wires = new EObjectResolvingEList<WireData>(WireData.class, this, GeneralPackage.LINE_GEOMETRY__WIRES);
		}
		return wires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineGeometry getLike() {
		if (like != null && like.eIsProxy()) {
			InternalEObject oldLike = (InternalEObject)like;
			like = (LineGeometry)eResolveProxy(oldLike);
			if (like != oldLike) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_GEOMETRY__LIKE, oldLike, like));
			}
		}
		return like;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineGeometry basicGetLike() {
		return like;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLike(LineGeometry newLike) {
		LineGeometry oldLike = like;
		like = newLike;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__LIKE, oldLike, like));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineSpacing getSpacing() {
		if (spacing != null && spacing.eIsProxy()) {
			InternalEObject oldSpacing = (InternalEObject)spacing;
			spacing = (LineSpacing)eResolveProxy(oldSpacing);
			if (spacing != oldSpacing) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_GEOMETRY__SPACING, oldSpacing, spacing));
			}
		}
		return spacing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineSpacing basicGetSpacing() {
		return spacing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpacing(LineSpacing newSpacing) {
		LineSpacing oldSpacing = spacing;
		spacing = newSpacing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__SPACING, oldSpacing, spacing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(double newX) {
		double oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getH() {
		return h;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH(double newH) {
		double oldH = h;
		h = newH;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__H, oldH, h));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__UNITS, oldUnits, units));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__NORM_AMPS, oldNormAmps, normAmps));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__EMERG_AMPS, oldEmergAmps, emergAmps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReduce() {
		return reduce;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReduce(boolean newReduce) {
		boolean oldReduce = reduce;
		reduce = newReduce;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_GEOMETRY__REDUCE, oldReduce, reduce));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.LINE_GEOMETRY__NCONDS:
				return getNConds();
			case GeneralPackage.LINE_GEOMETRY__NPHASES:
				return getNPhases();
			case GeneralPackage.LINE_GEOMETRY__COND:
				return getCond();
			case GeneralPackage.LINE_GEOMETRY__WIRE:
				if (resolve) return getWire();
				return basicGetWire();
			case GeneralPackage.LINE_GEOMETRY__SPACING:
				if (resolve) return getSpacing();
				return basicGetSpacing();
			case GeneralPackage.LINE_GEOMETRY__X:
				return getX();
			case GeneralPackage.LINE_GEOMETRY__H:
				return getH();
			case GeneralPackage.LINE_GEOMETRY__UNITS:
				return getUnits();
			case GeneralPackage.LINE_GEOMETRY__NORM_AMPS:
				return getNormAmps();
			case GeneralPackage.LINE_GEOMETRY__EMERG_AMPS:
				return getEmergAmps();
			case GeneralPackage.LINE_GEOMETRY__REDUCE:
				return isReduce();
			case GeneralPackage.LINE_GEOMETRY__WIRES:
				return getWires();
			case GeneralPackage.LINE_GEOMETRY__LIKE:
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
			case GeneralPackage.LINE_GEOMETRY__NCONDS:
				setNConds((Integer)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__NPHASES:
				setNPhases((Integer)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__COND:
				setCond((Integer)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__WIRE:
				setWire((WireData)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__SPACING:
				setSpacing((LineSpacing)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__X:
				setX((Double)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__H:
				setH((Double)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__UNITS:
				setUnits((lengthUnit)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__NORM_AMPS:
				setNormAmps((Double)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__EMERG_AMPS:
				setEmergAmps((Double)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__REDUCE:
				setReduce((Boolean)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireData>)newValue);
				return;
			case GeneralPackage.LINE_GEOMETRY__LIKE:
				setLike((LineGeometry)newValue);
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
			case GeneralPackage.LINE_GEOMETRY__NCONDS:
				setNConds(NCONDS_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__NPHASES:
				setNPhases(NPHASES_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__COND:
				setCond(COND_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__WIRE:
				setWire((WireData)null);
				return;
			case GeneralPackage.LINE_GEOMETRY__SPACING:
				setSpacing((LineSpacing)null);
				return;
			case GeneralPackage.LINE_GEOMETRY__X:
				setX(X_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__H:
				setH(H_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__UNITS:
				setUnits(UNITS_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__NORM_AMPS:
				setNormAmps(NORM_AMPS_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__EMERG_AMPS:
				setEmergAmps(EMERG_AMPS_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__REDUCE:
				setReduce(REDUCE_EDEFAULT);
				return;
			case GeneralPackage.LINE_GEOMETRY__WIRES:
				getWires().clear();
				return;
			case GeneralPackage.LINE_GEOMETRY__LIKE:
				setLike((LineGeometry)null);
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
			case GeneralPackage.LINE_GEOMETRY__NCONDS:
				return nConds != NCONDS_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__NPHASES:
				return nPhases != NPHASES_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__COND:
				return cond != COND_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__WIRE:
				return wire != null;
			case GeneralPackage.LINE_GEOMETRY__SPACING:
				return spacing != null;
			case GeneralPackage.LINE_GEOMETRY__X:
				return x != X_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__H:
				return h != H_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__UNITS:
				return units != UNITS_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__NORM_AMPS:
				return normAmps != NORM_AMPS_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__EMERG_AMPS:
				return emergAmps != EMERG_AMPS_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__REDUCE:
				return reduce != REDUCE_EDEFAULT;
			case GeneralPackage.LINE_GEOMETRY__WIRES:
				return wires != null && !wires.isEmpty();
			case GeneralPackage.LINE_GEOMETRY__LIKE:
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
		result.append(", cond: ");
		result.append(cond);
		result.append(", x: ");
		result.append(x);
		result.append(", h: ");
		result.append(h);
		result.append(", units: ");
		result.append(units);
		result.append(", normAmps: ");
		result.append(normAmps);
		result.append(", emergAmps: ");
		result.append(emergAmps);
		result.append(", reduce: ");
		result.append(reduce);
		result.append(')');
		return result.toString();
	}

} //LineGeometryImpl
