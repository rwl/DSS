/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;

import electrickery.conversion.ConversionPackage;
import electrickery.conversion.Equivalent;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Equivalent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getBuses <em>Buses</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getBaseKV <em>Base KV</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getPu <em>Pu</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getPhases <em>Phases</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getR1 <em>R1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getX1 <em>X1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getR0 <em>R0</em>}</li>
 *   <li>{@link electrickery.conversion.impl.EquivalentImpl#getX0 <em>X0</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EquivalentImpl extends PowerConversionElementImpl implements Equivalent {
	/**
	 * The cached value of the '{@link #getBuses() <em>Buses</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuses()
	 * @generated
	 * @ordered
	 */
	protected EList<String> buses;

	/**
	 * The default value of the '{@link #getBaseKV() <em>Base KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseKV()
	 * @generated
	 * @ordered
	 */
	protected static final double BASE_KV_EDEFAULT = 115.0;

	/**
	 * The cached value of the '{@link #getBaseKV() <em>Base KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseKV()
	 * @generated
	 * @ordered
	 */
	protected double baseKV = BASE_KV_EDEFAULT;

	/**
	 * The default value of the '{@link #getPu() <em>Pu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPu()
	 * @generated
	 * @ordered
	 */
	protected static final double PU_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getPu() <em>Pu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPu()
	 * @generated
	 * @ordered
	 */
	protected double pu = PU_EDEFAULT;

	/**
	 * The default value of the '{@link #getAngle() <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected static final double ANGLE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAngle() <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected double angle = ANGLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected static final double FREQUENCY_EDEFAULT = 60.0;

	/**
	 * The cached value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected double frequency = FREQUENCY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhases() <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhases()
	 * @generated
	 * @ordered
	 */
	protected static final int PHASES_EDEFAULT = 3;

	/**
	 * The cached value of the '{@link #getPhases() <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhases()
	 * @generated
	 * @ordered
	 */
	protected int phases = PHASES_EDEFAULT;

	/**
	 * The default value of the '{@link #getR1() <em>R1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getR1()
	 * @generated
	 * @ordered
	 */
	protected static final double R1_EDEFAULT = 1.65;

	/**
	 * The cached value of the '{@link #getR1() <em>R1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getR1()
	 * @generated
	 * @ordered
	 */
	protected double r1 = R1_EDEFAULT;

	/**
	 * The default value of the '{@link #getX1() <em>X1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX1()
	 * @generated
	 * @ordered
	 */
	protected static final double X1_EDEFAULT = 6.6;

	/**
	 * The cached value of the '{@link #getX1() <em>X1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX1()
	 * @generated
	 * @ordered
	 */
	protected double x1 = X1_EDEFAULT;

	/**
	 * The default value of the '{@link #getR0() <em>R0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getR0()
	 * @generated
	 * @ordered
	 */
	protected static final double R0_EDEFAULT = 1.9;

	/**
	 * The cached value of the '{@link #getR0() <em>R0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getR0()
	 * @generated
	 * @ordered
	 */
	protected double r0 = R0_EDEFAULT;

	/**
	 * The default value of the '{@link #getX0() <em>X0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX0()
	 * @generated
	 * @ordered
	 */
	protected static final double X0_EDEFAULT = 5.7;

	/**
	 * The cached value of the '{@link #getX0() <em>X0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX0()
	 * @generated
	 * @ordered
	 */
	protected double x0 = X0_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EquivalentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConversionPackage.Literals.EQUIVALENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBuses() {
		if (buses == null) {
			buses = new EDataTypeUniqueEList<String>(String.class, this, ConversionPackage.EQUIVALENT__BUSES);
		}
		return buses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBaseKV() {
		return baseKV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseKV(double newBaseKV) {
		double oldBaseKV = baseKV;
		baseKV = newBaseKV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__BASE_KV, oldBaseKV, baseKV));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPu() {
		return pu;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPu(double newPu) {
		double oldPu = pu;
		pu = newPu;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__PU, oldPu, pu));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAngle(double newAngle) {
		double oldAngle = angle;
		angle = newAngle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__ANGLE, oldAngle, angle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrequency(double newFrequency) {
		double oldFrequency = frequency;
		frequency = newFrequency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__FREQUENCY, oldFrequency, frequency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPhases() {
		return phases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhases(int newPhases) {
		int oldPhases = phases;
		phases = newPhases;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__PHASES, oldPhases, phases));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getR1() {
		return r1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setR1(double newR1) {
		double oldR1 = r1;
		r1 = newR1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__R1, oldR1, r1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX1(double newX1) {
		double oldX1 = x1;
		x1 = newX1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__X1, oldX1, x1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getR0() {
		return r0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setR0(double newR0) {
		double oldR0 = r0;
		r0 = newR0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__R0, oldR0, r0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getX0() {
		return x0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX0(double newX0) {
		double oldX0 = x0;
		x0 = newX0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.EQUIVALENT__X0, oldX0, x0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.EQUIVALENT__BUSES:
				return getBuses();
			case ConversionPackage.EQUIVALENT__BASE_KV:
				return getBaseKV();
			case ConversionPackage.EQUIVALENT__PU:
				return getPu();
			case ConversionPackage.EQUIVALENT__ANGLE:
				return getAngle();
			case ConversionPackage.EQUIVALENT__FREQUENCY:
				return getFrequency();
			case ConversionPackage.EQUIVALENT__PHASES:
				return getPhases();
			case ConversionPackage.EQUIVALENT__R1:
				return getR1();
			case ConversionPackage.EQUIVALENT__X1:
				return getX1();
			case ConversionPackage.EQUIVALENT__R0:
				return getR0();
			case ConversionPackage.EQUIVALENT__X0:
				return getX0();
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
			case ConversionPackage.EQUIVALENT__BUSES:
				getBuses().clear();
				getBuses().addAll((Collection<? extends String>)newValue);
				return;
			case ConversionPackage.EQUIVALENT__BASE_KV:
				setBaseKV((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__PU:
				setPu((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__ANGLE:
				setAngle((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__FREQUENCY:
				setFrequency((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__PHASES:
				setPhases((Integer)newValue);
				return;
			case ConversionPackage.EQUIVALENT__R1:
				setR1((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__X1:
				setX1((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__R0:
				setR0((Double)newValue);
				return;
			case ConversionPackage.EQUIVALENT__X0:
				setX0((Double)newValue);
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
			case ConversionPackage.EQUIVALENT__BUSES:
				getBuses().clear();
				return;
			case ConversionPackage.EQUIVALENT__BASE_KV:
				setBaseKV(BASE_KV_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__PU:
				setPu(PU_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__ANGLE:
				setAngle(ANGLE_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__PHASES:
				setPhases(PHASES_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__R1:
				setR1(R1_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__X1:
				setX1(X1_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__R0:
				setR0(R0_EDEFAULT);
				return;
			case ConversionPackage.EQUIVALENT__X0:
				setX0(X0_EDEFAULT);
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
			case ConversionPackage.EQUIVALENT__BUSES:
				return buses != null && !buses.isEmpty();
			case ConversionPackage.EQUIVALENT__BASE_KV:
				return baseKV != BASE_KV_EDEFAULT;
			case ConversionPackage.EQUIVALENT__PU:
				return pu != PU_EDEFAULT;
			case ConversionPackage.EQUIVALENT__ANGLE:
				return angle != ANGLE_EDEFAULT;
			case ConversionPackage.EQUIVALENT__FREQUENCY:
				return frequency != FREQUENCY_EDEFAULT;
			case ConversionPackage.EQUIVALENT__PHASES:
				return phases != PHASES_EDEFAULT;
			case ConversionPackage.EQUIVALENT__R1:
				return r1 != R1_EDEFAULT;
			case ConversionPackage.EQUIVALENT__X1:
				return x1 != X1_EDEFAULT;
			case ConversionPackage.EQUIVALENT__R0:
				return r0 != R0_EDEFAULT;
			case ConversionPackage.EQUIVALENT__X0:
				return x0 != X0_EDEFAULT;
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
		result.append(" (buses: ");
		result.append(buses);
		result.append(", baseKV: ");
		result.append(baseKV);
		result.append(", pu: ");
		result.append(pu);
		result.append(", angle: ");
		result.append(angle);
		result.append(", frequency: ");
		result.append(frequency);
		result.append(", phases: ");
		result.append(phases);
		result.append(", r1: ");
		result.append(r1);
		result.append(", x1: ");
		result.append(x1);
		result.append(", r0: ");
		result.append(r0);
		result.append(", x0: ");
		result.append(x0);
		result.append(')');
		return result.toString();
	}

} //EquivalentImpl
