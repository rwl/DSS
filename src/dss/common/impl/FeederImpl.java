/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common.impl;

import dss.common.CommonPackage;
import dss.common.Feeder;

import dss.general.Spectrum;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feeder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.FeederImpl#getSpectrum <em>Spectrum</em>}</li>
 *   <li>{@link dss.common.impl.FeederImpl#getBaseFreq <em>Base Freq</em>}</li>
 *   <li>{@link dss.common.impl.FeederImpl#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeederImpl extends EObjectImpl implements Feeder {
	/**
	 * The cached value of the '{@link #getSpectrum() <em>Spectrum</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpectrum()
	 * @generated
	 * @ordered
	 */
	protected Spectrum spectrum;

	/**
	 * The default value of the '{@link #getBaseFreq() <em>Base Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseFreq()
	 * @generated
	 * @ordered
	 */
	protected static final double BASE_FREQ_EDEFAULT = 60.0;

	/**
	 * The cached value of the '{@link #getBaseFreq() <em>Base Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseFreq()
	 * @generated
	 * @ordered
	 */
	protected double baseFreq = BASE_FREQ_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeederImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.FEEDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Spectrum getSpectrum() {
		if (spectrum != null && spectrum.eIsProxy()) {
			InternalEObject oldSpectrum = (InternalEObject)spectrum;
			spectrum = (Spectrum)eResolveProxy(oldSpectrum);
			if (spectrum != oldSpectrum) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.FEEDER__SPECTRUM, oldSpectrum, spectrum));
			}
		}
		return spectrum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Spectrum basicGetSpectrum() {
		return spectrum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpectrum(Spectrum newSpectrum) {
		Spectrum oldSpectrum = spectrum;
		spectrum = newSpectrum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.FEEDER__SPECTRUM, oldSpectrum, spectrum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBaseFreq() {
		return baseFreq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseFreq(double newBaseFreq) {
		double oldBaseFreq = baseFreq;
		baseFreq = newBaseFreq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.FEEDER__BASE_FREQ, oldBaseFreq, baseFreq));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.FEEDER__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.FEEDER__SPECTRUM:
				if (resolve) return getSpectrum();
				return basicGetSpectrum();
			case CommonPackage.FEEDER__BASE_FREQ:
				return getBaseFreq();
			case CommonPackage.FEEDER__ENABLED:
				return isEnabled();
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
			case CommonPackage.FEEDER__SPECTRUM:
				setSpectrum((Spectrum)newValue);
				return;
			case CommonPackage.FEEDER__BASE_FREQ:
				setBaseFreq((Double)newValue);
				return;
			case CommonPackage.FEEDER__ENABLED:
				setEnabled((Boolean)newValue);
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
			case CommonPackage.FEEDER__SPECTRUM:
				setSpectrum((Spectrum)null);
				return;
			case CommonPackage.FEEDER__BASE_FREQ:
				setBaseFreq(BASE_FREQ_EDEFAULT);
				return;
			case CommonPackage.FEEDER__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
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
			case CommonPackage.FEEDER__SPECTRUM:
				return spectrum != null;
			case CommonPackage.FEEDER__BASE_FREQ:
				return baseFreq != BASE_FREQ_EDEFAULT;
			case CommonPackage.FEEDER__ENABLED:
				return enabled != ENABLED_EDEFAULT;
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
		result.append(" (baseFreq: ");
		result.append(baseFreq);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(')');
		return result.toString();
	}

} //FeederImpl
