/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common.impl;

import dss.common.Bus;
import dss.common.CommonPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bus</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.BusImpl#getName <em>Name</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getVBus <em>VBus</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getBusCurrent <em>Bus Current</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getZSC <em>ZSC</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getYSC <em>YSC</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getX <em>X</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getY <em>Y</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#getKVBase <em>KV Base</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#isCoordsDefined <em>Coords Defined</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#isBusChecked <em>Bus Checked</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#isKeep <em>Keep</em>}</li>
 *   <li>{@link dss.common.impl.BusImpl#isRadialBus <em>Radial Bus</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BusImpl extends EObjectImpl implements Bus {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVBus() <em>VBus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVBus()
	 * @generated
	 * @ordered
	 */
	protected static final double VBUS_EDEFAULT = 115.0;

	/**
	 * The cached value of the '{@link #getVBus() <em>VBus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVBus()
	 * @generated
	 * @ordered
	 */
	protected double vBus = VBUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getBusCurrent() <em>Bus Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusCurrent()
	 * @generated
	 * @ordered
	 */
	protected static final double BUS_CURRENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBusCurrent() <em>Bus Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBusCurrent()
	 * @generated
	 * @ordered
	 */
	protected double busCurrent = BUS_CURRENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getZSC() <em>ZSC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZSC()
	 * @generated
	 * @ordered
	 */
	protected static final double ZSC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getZSC() <em>ZSC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZSC()
	 * @generated
	 * @ordered
	 */
	protected double zSC = ZSC_EDEFAULT;

	/**
	 * The default value of the '{@link #getYSC() <em>YSC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYSC()
	 * @generated
	 * @ordered
	 */
	protected static final double YSC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYSC() <em>YSC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYSC()
	 * @generated
	 * @ordered
	 */
	protected double ySC = YSC_EDEFAULT;

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
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final double Y_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected double y = Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getKVBase() <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVBase()
	 * @generated
	 * @ordered
	 */
	protected static final double KV_BASE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVBase() <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVBase()
	 * @generated
	 * @ordered
	 */
	protected double kVBase = KV_BASE_EDEFAULT;

	/**
	 * The default value of the '{@link #isCoordsDefined() <em>Coords Defined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCoordsDefined()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COORDS_DEFINED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCoordsDefined() <em>Coords Defined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCoordsDefined()
	 * @generated
	 * @ordered
	 */
	protected boolean coordsDefined = COORDS_DEFINED_EDEFAULT;

	/**
	 * The default value of the '{@link #isBusChecked() <em>Bus Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBusChecked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BUS_CHECKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBusChecked() <em>Bus Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBusChecked()
	 * @generated
	 * @ordered
	 */
	protected boolean busChecked = BUS_CHECKED_EDEFAULT;

	/**
	 * The default value of the '{@link #isKeep() <em>Keep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isKeep()
	 * @generated
	 * @ordered
	 */
	protected static final boolean KEEP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isKeep() <em>Keep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isKeep()
	 * @generated
	 * @ordered
	 */
	protected boolean keep = KEEP_EDEFAULT;

	/**
	 * The default value of the '{@link #isRadialBus() <em>Radial Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRadialBus()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RADIAL_BUS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRadialBus() <em>Radial Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRadialBus()
	 * @generated
	 * @ordered
	 */
	protected boolean radialBus = RADIAL_BUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.BUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVBus() {
		return vBus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVBus(double newVBus) {
		double oldVBus = vBus;
		vBus = newVBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__VBUS, oldVBus, vBus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBusCurrent() {
		return busCurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBusCurrent(double newBusCurrent) {
		double oldBusCurrent = busCurrent;
		busCurrent = newBusCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__BUS_CURRENT, oldBusCurrent, busCurrent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getZSC() {
		return zSC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setZSC(double newZSC) {
		double oldZSC = zSC;
		zSC = newZSC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__ZSC, oldZSC, zSC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYSC() {
		return ySC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYSC(double newYSC) {
		double oldYSC = ySC;
		ySC = newYSC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__YSC, oldYSC, ySC));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getY() {
		return y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(double newY) {
		double oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVBase() {
		return kVBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVBase(double newKVBase) {
		double oldKVBase = kVBase;
		kVBase = newKVBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__KV_BASE, oldKVBase, kVBase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCoordsDefined() {
		return coordsDefined;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoordsDefined(boolean newCoordsDefined) {
		boolean oldCoordsDefined = coordsDefined;
		coordsDefined = newCoordsDefined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__COORDS_DEFINED, oldCoordsDefined, coordsDefined));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBusChecked() {
		return busChecked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBusChecked(boolean newBusChecked) {
		boolean oldBusChecked = busChecked;
		busChecked = newBusChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__BUS_CHECKED, oldBusChecked, busChecked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isKeep() {
		return keep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeep(boolean newKeep) {
		boolean oldKeep = keep;
		keep = newKeep;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__KEEP, oldKeep, keep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRadialBus() {
		return radialBus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRadialBus(boolean newRadialBus) {
		boolean oldRadialBus = radialBus;
		radialBus = newRadialBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.BUS__RADIAL_BUS, oldRadialBus, radialBus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.BUS__NAME:
				return getName();
			case CommonPackage.BUS__VBUS:
				return getVBus();
			case CommonPackage.BUS__BUS_CURRENT:
				return getBusCurrent();
			case CommonPackage.BUS__ZSC:
				return getZSC();
			case CommonPackage.BUS__YSC:
				return getYSC();
			case CommonPackage.BUS__X:
				return getX();
			case CommonPackage.BUS__Y:
				return getY();
			case CommonPackage.BUS__KV_BASE:
				return getKVBase();
			case CommonPackage.BUS__COORDS_DEFINED:
				return isCoordsDefined();
			case CommonPackage.BUS__BUS_CHECKED:
				return isBusChecked();
			case CommonPackage.BUS__KEEP:
				return isKeep();
			case CommonPackage.BUS__RADIAL_BUS:
				return isRadialBus();
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
			case CommonPackage.BUS__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.BUS__VBUS:
				setVBus((Double)newValue);
				return;
			case CommonPackage.BUS__BUS_CURRENT:
				setBusCurrent((Double)newValue);
				return;
			case CommonPackage.BUS__ZSC:
				setZSC((Double)newValue);
				return;
			case CommonPackage.BUS__YSC:
				setYSC((Double)newValue);
				return;
			case CommonPackage.BUS__X:
				setX((Double)newValue);
				return;
			case CommonPackage.BUS__Y:
				setY((Double)newValue);
				return;
			case CommonPackage.BUS__KV_BASE:
				setKVBase((Double)newValue);
				return;
			case CommonPackage.BUS__COORDS_DEFINED:
				setCoordsDefined((Boolean)newValue);
				return;
			case CommonPackage.BUS__BUS_CHECKED:
				setBusChecked((Boolean)newValue);
				return;
			case CommonPackage.BUS__KEEP:
				setKeep((Boolean)newValue);
				return;
			case CommonPackage.BUS__RADIAL_BUS:
				setRadialBus((Boolean)newValue);
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
			case CommonPackage.BUS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.BUS__VBUS:
				setVBus(VBUS_EDEFAULT);
				return;
			case CommonPackage.BUS__BUS_CURRENT:
				setBusCurrent(BUS_CURRENT_EDEFAULT);
				return;
			case CommonPackage.BUS__ZSC:
				setZSC(ZSC_EDEFAULT);
				return;
			case CommonPackage.BUS__YSC:
				setYSC(YSC_EDEFAULT);
				return;
			case CommonPackage.BUS__X:
				setX(X_EDEFAULT);
				return;
			case CommonPackage.BUS__Y:
				setY(Y_EDEFAULT);
				return;
			case CommonPackage.BUS__KV_BASE:
				setKVBase(KV_BASE_EDEFAULT);
				return;
			case CommonPackage.BUS__COORDS_DEFINED:
				setCoordsDefined(COORDS_DEFINED_EDEFAULT);
				return;
			case CommonPackage.BUS__BUS_CHECKED:
				setBusChecked(BUS_CHECKED_EDEFAULT);
				return;
			case CommonPackage.BUS__KEEP:
				setKeep(KEEP_EDEFAULT);
				return;
			case CommonPackage.BUS__RADIAL_BUS:
				setRadialBus(RADIAL_BUS_EDEFAULT);
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
			case CommonPackage.BUS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.BUS__VBUS:
				return vBus != VBUS_EDEFAULT;
			case CommonPackage.BUS__BUS_CURRENT:
				return busCurrent != BUS_CURRENT_EDEFAULT;
			case CommonPackage.BUS__ZSC:
				return zSC != ZSC_EDEFAULT;
			case CommonPackage.BUS__YSC:
				return ySC != YSC_EDEFAULT;
			case CommonPackage.BUS__X:
				return x != X_EDEFAULT;
			case CommonPackage.BUS__Y:
				return y != Y_EDEFAULT;
			case CommonPackage.BUS__KV_BASE:
				return kVBase != KV_BASE_EDEFAULT;
			case CommonPackage.BUS__COORDS_DEFINED:
				return coordsDefined != COORDS_DEFINED_EDEFAULT;
			case CommonPackage.BUS__BUS_CHECKED:
				return busChecked != BUS_CHECKED_EDEFAULT;
			case CommonPackage.BUS__KEEP:
				return keep != KEEP_EDEFAULT;
			case CommonPackage.BUS__RADIAL_BUS:
				return radialBus != RADIAL_BUS_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", vBus: ");
		result.append(vBus);
		result.append(", busCurrent: ");
		result.append(busCurrent);
		result.append(", zSC: ");
		result.append(zSC);
		result.append(", ySC: ");
		result.append(ySC);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(", kVBase: ");
		result.append(kVBase);
		result.append(", coordsDefined: ");
		result.append(coordsDefined);
		result.append(", busChecked: ");
		result.append(busChecked);
		result.append(", keep: ");
		result.append(keep);
		result.append(", radialBus: ");
		result.append(radialBus);
		result.append(')');
		return result.toString();
	}

} //BusImpl
