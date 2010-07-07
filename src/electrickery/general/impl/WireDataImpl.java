/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.impl;


import electrickery.common.lengthUnit;
import electrickery.general.GeneralPackage;
import electrickery.general.WireData;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wire Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getRDC <em>RDC</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getRAC <em>RAC</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getRUnits <em>RUnits</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getGmrAC <em>Gmr AC</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getGmrUnits <em>Gmr Units</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getRadius <em>Radius</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getRadUnits <em>Rad Units</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link electrickery.general.impl.WireDataImpl#getDiameter <em>Diameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WireDataImpl extends EObjectImpl implements WireData {
	/**
	 * The default value of the '{@link #getRDC() <em>RDC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRDC()
	 * @generated
	 * @ordered
	 */
	protected static final double RDC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRDC() <em>RDC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRDC()
	 * @generated
	 * @ordered
	 */
	protected double rDC = RDC_EDEFAULT;

	/**
	 * The default value of the '{@link #getRAC() <em>RAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRAC()
	 * @generated
	 * @ordered
	 */
	protected static final double RAC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRAC() <em>RAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRAC()
	 * @generated
	 * @ordered
	 */
	protected double rAC = RAC_EDEFAULT;

	/**
	 * The default value of the '{@link #getRUnits() <em>RUnits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRUnits()
	 * @generated
	 * @ordered
	 */
	protected static final lengthUnit RUNITS_EDEFAULT = lengthUnit.NONE;

	/**
	 * The cached value of the '{@link #getRUnits() <em>RUnits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRUnits()
	 * @generated
	 * @ordered
	 */
	protected lengthUnit rUnits = RUNITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getGmrAC() <em>Gmr AC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGmrAC()
	 * @generated
	 * @ordered
	 */
	protected static final double GMR_AC_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getGmrAC() <em>Gmr AC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGmrAC()
	 * @generated
	 * @ordered
	 */
	protected double gmrAC = GMR_AC_EDEFAULT;

	/**
	 * The default value of the '{@link #getGmrUnits() <em>Gmr Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGmrUnits()
	 * @generated
	 * @ordered
	 */
	protected static final lengthUnit GMR_UNITS_EDEFAULT = lengthUnit.NONE;

	/**
	 * The cached value of the '{@link #getGmrUnits() <em>Gmr Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGmrUnits()
	 * @generated
	 * @ordered
	 */
	protected lengthUnit gmrUnits = GMR_UNITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRadius() <em>Radius</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRadius()
	 * @generated
	 * @ordered
	 */
	protected static final double RADIUS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRadius() <em>Radius</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRadius()
	 * @generated
	 * @ordered
	 */
	protected double radius = RADIUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRadUnits() <em>Rad Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRadUnits()
	 * @generated
	 * @ordered
	 */
	protected static final lengthUnit RAD_UNITS_EDEFAULT = lengthUnit.NONE;

	/**
	 * The cached value of the '{@link #getRadUnits() <em>Rad Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRadUnits()
	 * @generated
	 * @ordered
	 */
	protected lengthUnit radUnits = RAD_UNITS_EDEFAULT;

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
	 * The default value of the '{@link #getDiameter() <em>Diameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiameter()
	 * @generated
	 * @ordered
	 */
	protected static final double DIAMETER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDiameter() <em>Diameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiameter()
	 * @generated
	 * @ordered
	 */
	protected double diameter = DIAMETER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WireDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.WIRE_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRDC() {
		return rDC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRDC(double newRDC) {
		double oldRDC = rDC;
		rDC = newRDC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__RDC, oldRDC, rDC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRAC() {
		return rAC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRAC(double newRAC) {
		double oldRAC = rAC;
		rAC = newRAC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__RAC, oldRAC, rAC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public lengthUnit getRUnits() {
		return rUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRUnits(lengthUnit newRUnits) {
		lengthUnit oldRUnits = rUnits;
		rUnits = newRUnits == null ? RUNITS_EDEFAULT : newRUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__RUNITS, oldRUnits, rUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getGmrAC() {
		return gmrAC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGmrAC(double newGmrAC) {
		double oldGmrAC = gmrAC;
		gmrAC = newGmrAC;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__GMR_AC, oldGmrAC, gmrAC));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public lengthUnit getGmrUnits() {
		return gmrUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGmrUnits(lengthUnit newGmrUnits) {
		lengthUnit oldGmrUnits = gmrUnits;
		gmrUnits = newGmrUnits == null ? GMR_UNITS_EDEFAULT : newGmrUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__GMR_UNITS, oldGmrUnits, gmrUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRadius(double newRadius) {
		double oldRadius = radius;
		radius = newRadius;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__RADIUS, oldRadius, radius));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public lengthUnit getRadUnits() {
		return radUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRadUnits(lengthUnit newRadUnits) {
		lengthUnit oldRadUnits = radUnits;
		radUnits = newRadUnits == null ? RAD_UNITS_EDEFAULT : newRadUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__RAD_UNITS, oldRadUnits, radUnits));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__NORM_AMPS, oldNormAmps, normAmps));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__EMERG_AMPS, oldEmergAmps, emergAmps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDiameter() {
		return diameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiameter(double newDiameter) {
		double oldDiameter = diameter;
		diameter = newDiameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.WIRE_DATA__DIAMETER, oldDiameter, diameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.WIRE_DATA__RDC:
				return getRDC();
			case GeneralPackage.WIRE_DATA__RAC:
				return getRAC();
			case GeneralPackage.WIRE_DATA__RUNITS:
				return getRUnits();
			case GeneralPackage.WIRE_DATA__GMR_AC:
				return getGmrAC();
			case GeneralPackage.WIRE_DATA__GMR_UNITS:
				return getGmrUnits();
			case GeneralPackage.WIRE_DATA__RADIUS:
				return getRadius();
			case GeneralPackage.WIRE_DATA__RAD_UNITS:
				return getRadUnits();
			case GeneralPackage.WIRE_DATA__NORM_AMPS:
				return getNormAmps();
			case GeneralPackage.WIRE_DATA__EMERG_AMPS:
				return getEmergAmps();
			case GeneralPackage.WIRE_DATA__DIAMETER:
				return getDiameter();
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
			case GeneralPackage.WIRE_DATA__RDC:
				setRDC((Double)newValue);
				return;
			case GeneralPackage.WIRE_DATA__RAC:
				setRAC((Double)newValue);
				return;
			case GeneralPackage.WIRE_DATA__RUNITS:
				setRUnits((lengthUnit)newValue);
				return;
			case GeneralPackage.WIRE_DATA__GMR_AC:
				setGmrAC((Double)newValue);
				return;
			case GeneralPackage.WIRE_DATA__GMR_UNITS:
				setGmrUnits((lengthUnit)newValue);
				return;
			case GeneralPackage.WIRE_DATA__RADIUS:
				setRadius((Double)newValue);
				return;
			case GeneralPackage.WIRE_DATA__RAD_UNITS:
				setRadUnits((lengthUnit)newValue);
				return;
			case GeneralPackage.WIRE_DATA__NORM_AMPS:
				setNormAmps((Double)newValue);
				return;
			case GeneralPackage.WIRE_DATA__EMERG_AMPS:
				setEmergAmps((Double)newValue);
				return;
			case GeneralPackage.WIRE_DATA__DIAMETER:
				setDiameter((Double)newValue);
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
			case GeneralPackage.WIRE_DATA__RDC:
				setRDC(RDC_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__RAC:
				setRAC(RAC_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__RUNITS:
				setRUnits(RUNITS_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__GMR_AC:
				setGmrAC(GMR_AC_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__GMR_UNITS:
				setGmrUnits(GMR_UNITS_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__RADIUS:
				setRadius(RADIUS_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__RAD_UNITS:
				setRadUnits(RAD_UNITS_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__NORM_AMPS:
				setNormAmps(NORM_AMPS_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__EMERG_AMPS:
				setEmergAmps(EMERG_AMPS_EDEFAULT);
				return;
			case GeneralPackage.WIRE_DATA__DIAMETER:
				setDiameter(DIAMETER_EDEFAULT);
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
			case GeneralPackage.WIRE_DATA__RDC:
				return rDC != RDC_EDEFAULT;
			case GeneralPackage.WIRE_DATA__RAC:
				return rAC != RAC_EDEFAULT;
			case GeneralPackage.WIRE_DATA__RUNITS:
				return rUnits != RUNITS_EDEFAULT;
			case GeneralPackage.WIRE_DATA__GMR_AC:
				return gmrAC != GMR_AC_EDEFAULT;
			case GeneralPackage.WIRE_DATA__GMR_UNITS:
				return gmrUnits != GMR_UNITS_EDEFAULT;
			case GeneralPackage.WIRE_DATA__RADIUS:
				return radius != RADIUS_EDEFAULT;
			case GeneralPackage.WIRE_DATA__RAD_UNITS:
				return radUnits != RAD_UNITS_EDEFAULT;
			case GeneralPackage.WIRE_DATA__NORM_AMPS:
				return normAmps != NORM_AMPS_EDEFAULT;
			case GeneralPackage.WIRE_DATA__EMERG_AMPS:
				return emergAmps != EMERG_AMPS_EDEFAULT;
			case GeneralPackage.WIRE_DATA__DIAMETER:
				return diameter != DIAMETER_EDEFAULT;
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
		result.append(" (rDC: ");
		result.append(rDC);
		result.append(", rAC: ");
		result.append(rAC);
		result.append(", rUnits: ");
		result.append(rUnits);
		result.append(", gmrAC: ");
		result.append(gmrAC);
		result.append(", gmrUnits: ");
		result.append(gmrUnits);
		result.append(", radius: ");
		result.append(radius);
		result.append(", radUnits: ");
		result.append(radUnits);
		result.append(", normAmps: ");
		result.append(normAmps);
		result.append(", emergAmps: ");
		result.append(emergAmps);
		result.append(", diameter: ");
		result.append(diameter);
		result.append(')');
		return result.toString();
	}

} //WireDataImpl
