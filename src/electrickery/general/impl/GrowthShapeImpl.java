/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.impl;

import electrickery.general.GeneralPackage;
import electrickery.general.GrowthShape;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Growth Shape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.general.impl.GrowthShapeImpl#getNPts <em>NPts</em>}</li>
 *   <li>{@link electrickery.general.impl.GrowthShapeImpl#getYear <em>Year</em>}</li>
 *   <li>{@link electrickery.general.impl.GrowthShapeImpl#getCsvFile <em>Csv File</em>}</li>
 *   <li>{@link electrickery.general.impl.GrowthShapeImpl#getSngFile <em>Sng File</em>}</li>
 *   <li>{@link electrickery.general.impl.GrowthShapeImpl#getDblFile <em>Dbl File</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GrowthShapeImpl extends EObjectImpl implements GrowthShape {
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
	 * The cached value of the '{@link #getYear() <em>Year</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> year;

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
	 * The default value of the '{@link #getSngFile() <em>Sng File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSngFile()
	 * @generated
	 * @ordered
	 */
	protected static final String SNG_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSngFile() <em>Sng File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSngFile()
	 * @generated
	 * @ordered
	 */
	protected String sngFile = SNG_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDblFile() <em>Dbl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDblFile()
	 * @generated
	 * @ordered
	 */
	protected static final String DBL_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDblFile() <em>Dbl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDblFile()
	 * @generated
	 * @ordered
	 */
	protected String dblFile = DBL_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GrowthShapeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.GROWTH_SHAPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.GROWTH_SHAPE__NPTS, oldNPts, nPts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getYear() {
		if (year == null) {
			year = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.GROWTH_SHAPE__YEAR);
		}
		return year;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.GROWTH_SHAPE__CSV_FILE, oldCsvFile, csvFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSngFile() {
		return sngFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSngFile(String newSngFile) {
		String oldSngFile = sngFile;
		sngFile = newSngFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.GROWTH_SHAPE__SNG_FILE, oldSngFile, sngFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDblFile() {
		return dblFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDblFile(String newDblFile) {
		String oldDblFile = dblFile;
		dblFile = newDblFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.GROWTH_SHAPE__DBL_FILE, oldDblFile, dblFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMult(int year) {
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
			case GeneralPackage.GROWTH_SHAPE__NPTS:
				return getNPts();
			case GeneralPackage.GROWTH_SHAPE__YEAR:
				return getYear();
			case GeneralPackage.GROWTH_SHAPE__CSV_FILE:
				return getCsvFile();
			case GeneralPackage.GROWTH_SHAPE__SNG_FILE:
				return getSngFile();
			case GeneralPackage.GROWTH_SHAPE__DBL_FILE:
				return getDblFile();
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
			case GeneralPackage.GROWTH_SHAPE__NPTS:
				setNPts((Integer)newValue);
				return;
			case GeneralPackage.GROWTH_SHAPE__YEAR:
				getYear().clear();
				getYear().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.GROWTH_SHAPE__CSV_FILE:
				setCsvFile((String)newValue);
				return;
			case GeneralPackage.GROWTH_SHAPE__SNG_FILE:
				setSngFile((String)newValue);
				return;
			case GeneralPackage.GROWTH_SHAPE__DBL_FILE:
				setDblFile((String)newValue);
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
			case GeneralPackage.GROWTH_SHAPE__NPTS:
				setNPts(NPTS_EDEFAULT);
				return;
			case GeneralPackage.GROWTH_SHAPE__YEAR:
				getYear().clear();
				return;
			case GeneralPackage.GROWTH_SHAPE__CSV_FILE:
				setCsvFile(CSV_FILE_EDEFAULT);
				return;
			case GeneralPackage.GROWTH_SHAPE__SNG_FILE:
				setSngFile(SNG_FILE_EDEFAULT);
				return;
			case GeneralPackage.GROWTH_SHAPE__DBL_FILE:
				setDblFile(DBL_FILE_EDEFAULT);
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
			case GeneralPackage.GROWTH_SHAPE__NPTS:
				return nPts != NPTS_EDEFAULT;
			case GeneralPackage.GROWTH_SHAPE__YEAR:
				return year != null && !year.isEmpty();
			case GeneralPackage.GROWTH_SHAPE__CSV_FILE:
				return CSV_FILE_EDEFAULT == null ? csvFile != null : !CSV_FILE_EDEFAULT.equals(csvFile);
			case GeneralPackage.GROWTH_SHAPE__SNG_FILE:
				return SNG_FILE_EDEFAULT == null ? sngFile != null : !SNG_FILE_EDEFAULT.equals(sngFile);
			case GeneralPackage.GROWTH_SHAPE__DBL_FILE:
				return DBL_FILE_EDEFAULT == null ? dblFile != null : !DBL_FILE_EDEFAULT.equals(dblFile);
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
		result.append(", year: ");
		result.append(year);
		result.append(", csvFile: ");
		result.append(csvFile);
		result.append(", sngFile: ");
		result.append(sngFile);
		result.append(", dblFile: ");
		result.append(dblFile);
		result.append(')');
		return result.toString();
	}

} //GrowthShapeImpl
