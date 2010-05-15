/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general.impl;

import dss.general.GeneralPackage;
import dss.general.LoadShape;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Load Shape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getNPts <em>NPts</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getInterval <em>Interval</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getMult <em>Mult</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getHour <em>Hour</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getMean <em>Mean</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getStdDev <em>Std Dev</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getCsvFile <em>Csv File</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getSngFile <em>Sng File</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getDblFile <em>Dbl File</em>}</li>
 *   <li>{@link dss.general.impl.LoadShapeImpl#getQMult <em>QMult</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoadShapeImpl extends EObjectImpl implements LoadShape {
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
	 * The default value of the '{@link #getInterval() <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterval()
	 * @generated
	 * @ordered
	 */
	protected static final int INTERVAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getInterval() <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterval()
	 * @generated
	 * @ordered
	 */
	protected int interval = INTERVAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMult() <em>Mult</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMult()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> mult;

	/**
	 * The cached value of the '{@link #getHour() <em>Hour</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHour()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> hour;

	/**
	 * The default value of the '{@link #getMean() <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMean()
	 * @generated
	 * @ordered
	 */
	protected static final double MEAN_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMean() <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMean()
	 * @generated
	 * @ordered
	 */
	protected double mean = MEAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getStdDev() <em>Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStdDev()
	 * @generated
	 * @ordered
	 */
	protected static final double STD_DEV_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getStdDev() <em>Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStdDev()
	 * @generated
	 * @ordered
	 */
	protected double stdDev = STD_DEV_EDEFAULT;

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
	 * The cached value of the '{@link #getQMult() <em>QMult</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQMult()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> qMult;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LoadShapeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.LOAD_SHAPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__NPTS, oldNPts, nPts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterval(int newInterval) {
		int oldInterval = interval;
		interval = newInterval;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__INTERVAL, oldInterval, interval));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getMult() {
		if (mult == null) {
			mult = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.LOAD_SHAPE__MULT);
		}
		return mult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getHour() {
		if (hour == null) {
			hour = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.LOAD_SHAPE__HOUR);
		}
		return hour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMean() {
		return mean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMean(double newMean) {
		double oldMean = mean;
		mean = newMean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__MEAN, oldMean, mean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getStdDev() {
		return stdDev;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStdDev(double newStdDev) {
		double oldStdDev = stdDev;
		stdDev = newStdDev;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__STD_DEV, oldStdDev, stdDev));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__CSV_FILE, oldCsvFile, csvFile));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__SNG_FILE, oldSngFile, sngFile));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LOAD_SHAPE__DBL_FILE, oldDblFile, dblFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getQMult() {
		if (qMult == null) {
			qMult = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.LOAD_SHAPE__QMULT);
		}
		return qMult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void normalise() {
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
			case GeneralPackage.LOAD_SHAPE__NPTS:
				return getNPts();
			case GeneralPackage.LOAD_SHAPE__INTERVAL:
				return getInterval();
			case GeneralPackage.LOAD_SHAPE__MULT:
				return getMult();
			case GeneralPackage.LOAD_SHAPE__HOUR:
				return getHour();
			case GeneralPackage.LOAD_SHAPE__MEAN:
				return getMean();
			case GeneralPackage.LOAD_SHAPE__STD_DEV:
				return getStdDev();
			case GeneralPackage.LOAD_SHAPE__CSV_FILE:
				return getCsvFile();
			case GeneralPackage.LOAD_SHAPE__SNG_FILE:
				return getSngFile();
			case GeneralPackage.LOAD_SHAPE__DBL_FILE:
				return getDblFile();
			case GeneralPackage.LOAD_SHAPE__QMULT:
				return getQMult();
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
			case GeneralPackage.LOAD_SHAPE__NPTS:
				setNPts((Integer)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__INTERVAL:
				setInterval((Integer)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__MULT:
				getMult().clear();
				getMult().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__HOUR:
				getHour().clear();
				getHour().addAll((Collection<? extends Double>)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__MEAN:
				setMean((Double)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__STD_DEV:
				setStdDev((Double)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__CSV_FILE:
				setCsvFile((String)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__SNG_FILE:
				setSngFile((String)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__DBL_FILE:
				setDblFile((String)newValue);
				return;
			case GeneralPackage.LOAD_SHAPE__QMULT:
				getQMult().clear();
				getQMult().addAll((Collection<? extends Double>)newValue);
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
			case GeneralPackage.LOAD_SHAPE__NPTS:
				setNPts(NPTS_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__INTERVAL:
				setInterval(INTERVAL_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__MULT:
				getMult().clear();
				return;
			case GeneralPackage.LOAD_SHAPE__HOUR:
				getHour().clear();
				return;
			case GeneralPackage.LOAD_SHAPE__MEAN:
				setMean(MEAN_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__STD_DEV:
				setStdDev(STD_DEV_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__CSV_FILE:
				setCsvFile(CSV_FILE_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__SNG_FILE:
				setSngFile(SNG_FILE_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__DBL_FILE:
				setDblFile(DBL_FILE_EDEFAULT);
				return;
			case GeneralPackage.LOAD_SHAPE__QMULT:
				getQMult().clear();
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
			case GeneralPackage.LOAD_SHAPE__NPTS:
				return nPts != NPTS_EDEFAULT;
			case GeneralPackage.LOAD_SHAPE__INTERVAL:
				return interval != INTERVAL_EDEFAULT;
			case GeneralPackage.LOAD_SHAPE__MULT:
				return mult != null && !mult.isEmpty();
			case GeneralPackage.LOAD_SHAPE__HOUR:
				return hour != null && !hour.isEmpty();
			case GeneralPackage.LOAD_SHAPE__MEAN:
				return mean != MEAN_EDEFAULT;
			case GeneralPackage.LOAD_SHAPE__STD_DEV:
				return stdDev != STD_DEV_EDEFAULT;
			case GeneralPackage.LOAD_SHAPE__CSV_FILE:
				return CSV_FILE_EDEFAULT == null ? csvFile != null : !CSV_FILE_EDEFAULT.equals(csvFile);
			case GeneralPackage.LOAD_SHAPE__SNG_FILE:
				return SNG_FILE_EDEFAULT == null ? sngFile != null : !SNG_FILE_EDEFAULT.equals(sngFile);
			case GeneralPackage.LOAD_SHAPE__DBL_FILE:
				return DBL_FILE_EDEFAULT == null ? dblFile != null : !DBL_FILE_EDEFAULT.equals(dblFile);
			case GeneralPackage.LOAD_SHAPE__QMULT:
				return qMult != null && !qMult.isEmpty();
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
		result.append(", interval: ");
		result.append(interval);
		result.append(", mult: ");
		result.append(mult);
		result.append(", hour: ");
		result.append(hour);
		result.append(", mean: ");
		result.append(mean);
		result.append(", stdDev: ");
		result.append(stdDev);
		result.append(", csvFile: ");
		result.append(csvFile);
		result.append(", sngFile: ");
		result.append(sngFile);
		result.append(", dblFile: ");
		result.append(dblFile);
		result.append(", qMult: ");
		result.append(qMult);
		result.append(')');
		return result.toString();
	}

} //LoadShapeImpl
