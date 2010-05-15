/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general.impl;

import dss.general.GeneralPackage;
import dss.general.Spectrum;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Spectrum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.general.impl.SpectrumImpl#getNHarm <em>NHarm</em>}</li>
 *   <li>{@link dss.general.impl.SpectrumImpl#getHarmonic <em>Harmonic</em>}</li>
 *   <li>{@link dss.general.impl.SpectrumImpl#getPctMag <em>Pct Mag</em>}</li>
 *   <li>{@link dss.general.impl.SpectrumImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link dss.general.impl.SpectrumImpl#getCsvFile <em>Csv File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpectrumImpl extends EObjectImpl implements Spectrum {
	/**
	 * The default value of the '{@link #getNHarm() <em>NHarm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNHarm()
	 * @generated
	 * @ordered
	 */
	protected static final int NHARM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNHarm() <em>NHarm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNHarm()
	 * @generated
	 * @ordered
	 */
	protected int nHarm = NHARM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHarmonic() <em>Harmonic</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHarmonic()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> harmonic;

	/**
	 * The default value of the '{@link #getPctMag() <em>Pct Mag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctMag()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_MAG_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctMag() <em>Pct Mag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctMag()
	 * @generated
	 * @ordered
	 */
	protected double pctMag = PCT_MAG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAngle() <em>Angle</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> angle;

	/**
	 * The default value of the '{@link #getCsvFile() <em>Csv File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCsvFile()
	 * @generated
	 * @ordered
	 */
	protected static final String CSV_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCsvFile() <em>Csv File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCsvFile()
	 * @generated
	 * @ordered
	 */
	protected String csvFile = CSV_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpectrumImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.SPECTRUM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNHarm() {
		return nHarm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNHarm(int newNHarm) {
		int oldNHarm = nHarm;
		nHarm = newNHarm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.SPECTRUM__NHARM, oldNHarm, nHarm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getHarmonic() {
		if (harmonic == null) {
			harmonic = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.SPECTRUM__HARMONIC);
		}
		return harmonic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctMag() {
		return pctMag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctMag(double newPctMag) {
		double oldPctMag = pctMag;
		pctMag = newPctMag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.SPECTRUM__PCT_MAG, oldPctMag, pctMag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getAngle() {
		if (angle == null) {
			angle = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.SPECTRUM__ANGLE);
		}
		return angle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCsvFile() {
		return csvFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCsvFile(String newCsvFile) {
		String oldCsvFile = csvFile;
		csvFile = newCsvFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.SPECTRUM__CSV_FILE, oldCsvFile, csvFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.SPECTRUM__NHARM:
				return getNHarm();
			case GeneralPackage.SPECTRUM__HARMONIC:
				return getHarmonic();
			case GeneralPackage.SPECTRUM__PCT_MAG:
				return getPctMag();
			case GeneralPackage.SPECTRUM__ANGLE:
				return getAngle();
			case GeneralPackage.SPECTRUM__CSV_FILE:
				return getCsvFile();
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
			case GeneralPackage.SPECTRUM__NHARM:
				setNHarm((Integer)newValue);
				return;
			case GeneralPackage.SPECTRUM__HARMONIC:
				getHarmonic().clear();
				getHarmonic().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.SPECTRUM__PCT_MAG:
				setPctMag((Double)newValue);
				return;
			case GeneralPackage.SPECTRUM__ANGLE:
				getAngle().clear();
				getAngle().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.SPECTRUM__CSV_FILE:
				setCsvFile((String)newValue);
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
			case GeneralPackage.SPECTRUM__NHARM:
				setNHarm(NHARM_EDEFAULT);
				return;
			case GeneralPackage.SPECTRUM__HARMONIC:
				getHarmonic().clear();
				return;
			case GeneralPackage.SPECTRUM__PCT_MAG:
				setPctMag(PCT_MAG_EDEFAULT);
				return;
			case GeneralPackage.SPECTRUM__ANGLE:
				getAngle().clear();
				return;
			case GeneralPackage.SPECTRUM__CSV_FILE:
				setCsvFile(CSV_FILE_EDEFAULT);
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
			case GeneralPackage.SPECTRUM__NHARM:
				return nHarm != NHARM_EDEFAULT;
			case GeneralPackage.SPECTRUM__HARMONIC:
				return harmonic != null && !harmonic.isEmpty();
			case GeneralPackage.SPECTRUM__PCT_MAG:
				return pctMag != PCT_MAG_EDEFAULT;
			case GeneralPackage.SPECTRUM__ANGLE:
				return angle != null && !angle.isEmpty();
			case GeneralPackage.SPECTRUM__CSV_FILE:
				return CSV_FILE_EDEFAULT == null ? csvFile != null : !CSV_FILE_EDEFAULT.equals(csvFile);
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
		result.append(" (nHarm: ");
		result.append(nHarm);
		result.append(", harmonic: ");
		result.append(harmonic);
		result.append(", pctMag: ");
		result.append(pctMag);
		result.append(", angle: ");
		result.append(angle);
		result.append(", csvFile: ");
		result.append(csvFile);
		result.append(')');
		return result.toString();
	}

} //SpectrumImpl
