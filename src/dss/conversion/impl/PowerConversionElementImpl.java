/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.conversion.impl;

import cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D;
import dss.common.impl.CircuitElementImpl;

import dss.conversion.ConversionPackage;
import dss.conversion.PowerConversionElement;

import dss.general.Spectrum;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Power Conversion Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.conversion.impl.PowerConversionElementImpl#getSpectrum <em>Spectrum</em>}</li>
 *   <li>{@link dss.conversion.impl.PowerConversionElementImpl#getSpectrumObj <em>Spectrum Obj</em>}</li>
 *   <li>{@link dss.conversion.impl.PowerConversionElementImpl#getInjCurrent <em>Inj Current</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PowerConversionElementImpl extends CircuitElementImpl implements PowerConversionElement {
	/**
	 * The default value of the '{@link #getSpectrum() <em>Spectrum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpectrum()
	 * @generated
	 * @ordered
	 */
	protected static final String SPECTRUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpectrum() <em>Spectrum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpectrum()
	 * @generated
	 * @ordered
	 */
	protected String spectrum = SPECTRUM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSpectrumObj() <em>Spectrum Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpectrumObj()
	 * @generated
	 * @ordered
	 */
	protected Spectrum spectrumObj;

	/**
	 * The default value of the '{@link #getInjCurrent() <em>Inj Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjCurrent()
	 * @generated
	 * @ordered
	 */
	protected static final double INJ_CURRENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getInjCurrent() <em>Inj Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjCurrent()
	 * @generated
	 * @ordered
	 */
	protected double injCurrent = INJ_CURRENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PowerConversionElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConversionPackage.Literals.POWER_CONVERSION_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpectrum() {
		return spectrum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpectrum(String newSpectrum) {
		String oldSpectrum = spectrum;
		spectrum = newSpectrum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM, oldSpectrum, spectrum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Spectrum getSpectrumObj() {
		if (spectrumObj != null && spectrumObj.eIsProxy()) {
			InternalEObject oldSpectrumObj = (InternalEObject)spectrumObj;
			spectrumObj = (Spectrum)eResolveProxy(oldSpectrumObj);
			if (spectrumObj != oldSpectrumObj) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ, oldSpectrumObj, spectrumObj));
			}
		}
		return spectrumObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Spectrum basicGetSpectrumObj() {
		return spectrumObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpectrumObj(Spectrum newSpectrumObj) {
		Spectrum oldSpectrumObj = spectrumObj;
		spectrumObj = newSpectrumObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ, oldSpectrumObj, spectrumObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getInjCurrent() {
		return injCurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInjCurrent(double newInjCurrent) {
		double oldInjCurrent = injCurrent;
		injCurrent = newInjCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT, oldInjCurrent, injCurrent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void recalcElementData() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int injCurrents() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void getCurrents(DenseDComplexMatrix1D curr) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void getInjCurrents(DenseDComplexMatrix1D curr) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM:
				return getSpectrum();
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ:
				if (resolve) return getSpectrumObj();
				return basicGetSpectrumObj();
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				return getInjCurrent();
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM:
				setSpectrum((String)newValue);
				return;
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ:
				setSpectrumObj((Spectrum)newValue);
				return;
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				setInjCurrent((Double)newValue);
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM:
				setSpectrum(SPECTRUM_EDEFAULT);
				return;
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ:
				setSpectrumObj((Spectrum)null);
				return;
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				setInjCurrent(INJ_CURRENT_EDEFAULT);
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
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM:
				return SPECTRUM_EDEFAULT == null ? spectrum != null : !SPECTRUM_EDEFAULT.equals(spectrum);
			case ConversionPackage.POWER_CONVERSION_ELEMENT__SPECTRUM_OBJ:
				return spectrumObj != null;
			case ConversionPackage.POWER_CONVERSION_ELEMENT__INJ_CURRENT:
				return injCurrent != INJ_CURRENT_EDEFAULT;
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
		result.append(" (spectrum: ");
		result.append(spectrum);
		result.append(", injCurrent: ");
		result.append(injCurrent);
		result.append(')');
		return result.toString();
	}

} //PowerConversionElementImpl
