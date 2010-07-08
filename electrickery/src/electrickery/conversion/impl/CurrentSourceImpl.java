/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;

import electrickery.conversion.ConversionPackage;
import electrickery.conversion.CurrentSource;
import electrickery.conversion.sequenceType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import cern.colt.matrix.tdcomplex.DComplexFactory2D;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Current Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.CurrentSourceImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.CurrentSourceImpl#getAmps <em>Amps</em>}</li>
 *   <li>{@link electrickery.conversion.impl.CurrentSourceImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link electrickery.conversion.impl.CurrentSourceImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.conversion.impl.CurrentSourceImpl#getPhases <em>Phases</em>}</li>
 *   <li>{@link electrickery.conversion.impl.CurrentSourceImpl#getScanType <em>Scan Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CurrentSourceImpl extends PowerConversionElementImpl implements CurrentSource {
    /**
	 * The default value of the '{@link #getBus1() <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBus1()
	 * @generated
	 * @ordered
	 */
    protected static final String BUS1_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getBus1() <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBus1()
	 * @generated
	 * @ordered
	 */
    protected String bus1 = BUS1_EDEFAULT;

    /**
	 * The default value of the '{@link #getAmps() <em>Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAmps()
	 * @generated
	 * @ordered
	 */
    protected static final double AMPS_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getAmps() <em>Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAmps()
	 * @generated
	 * @ordered
	 */
    protected double amps = AMPS_EDEFAULT;

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
    protected static final int PHASES_EDEFAULT = 0;

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
	 * The default value of the '{@link #getScanType() <em>Scan Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getScanType()
	 * @generated
	 * @ordered
	 */
    protected static final sequenceType SCAN_TYPE_EDEFAULT = sequenceType.POSITIVE;

    /**
	 * The cached value of the '{@link #getScanType() <em>Scan Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getScanType()
	 * @generated
	 * @ordered
	 */
    protected sequenceType scanType = SCAN_TYPE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected CurrentSourceImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConversionPackage.Literals.CURRENT_SOURCE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getBus1() {
		return bus1;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBus1(String newBus1) {
		String oldBus1 = bus1;
		bus1 = newBus1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.CURRENT_SOURCE__BUS1, oldBus1, bus1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getAmps() {
		return amps;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAmps(double newAmps) {
		double oldAmps = amps;
		amps = newAmps;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.CURRENT_SOURCE__AMPS, oldAmps, amps));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.CURRENT_SOURCE__ANGLE, oldAngle, angle));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.CURRENT_SOURCE__FREQUENCY, oldFrequency, frequency));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.CURRENT_SOURCE__PHASES, oldPhases, phases));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public sequenceType getScanType() {
		return scanType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setScanType(sequenceType newScanType) {
		sequenceType oldScanType = scanType;
		scanType = newScanType == null ? SCAN_TYPE_EDEFAULT : newScanType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.CURRENT_SOURCE__SCAN_TYPE, oldScanType, scanType));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Double> getBaseCurrent() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
     *
     */
    @Override
    public void recalcElementData() {
        // TODO: Locate spectrum object using name.
    }

    /**
     *
     */
    @Override
    public void calcYPrim(double yPrimFreq) {
        if (isYPrimInvalid()) {
            if (getYPrimSeries() == null) {
                setYPrimSeries(DComplexFactory2D.sparse.make(yOrder, yOrder));
            } else {
                getYPrimSeries().assign(0.0, 0.0);
            }
            if (getYPrim() == null) {
                setYPrim(DComplexFactory2D.sparse.make(getNTerms(), getNConds()));
            } else {
                getYPrim().assign(0.0, 0.0);
            }
        }

        // YPrim = 0 for ideal current source; just leave it zeroed.

        // For any conductor that is open, zero out row and column.
        // FIXME: Call calcYPrim method from super class.

        setYPrimInvalid(false);
    }

    /**
     *
     */
    public void makePosSequence() {
        if (getNPhases() > 1)
            setNPhases(1);
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.CURRENT_SOURCE__BUS1:
				return getBus1();
			case ConversionPackage.CURRENT_SOURCE__AMPS:
				return getAmps();
			case ConversionPackage.CURRENT_SOURCE__ANGLE:
				return getAngle();
			case ConversionPackage.CURRENT_SOURCE__FREQUENCY:
				return getFrequency();
			case ConversionPackage.CURRENT_SOURCE__PHASES:
				return getPhases();
			case ConversionPackage.CURRENT_SOURCE__SCAN_TYPE:
				return getScanType();
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
			case ConversionPackage.CURRENT_SOURCE__BUS1:
				setBus1((String)newValue);
				return;
			case ConversionPackage.CURRENT_SOURCE__AMPS:
				setAmps((Double)newValue);
				return;
			case ConversionPackage.CURRENT_SOURCE__ANGLE:
				setAngle((Double)newValue);
				return;
			case ConversionPackage.CURRENT_SOURCE__FREQUENCY:
				setFrequency((Double)newValue);
				return;
			case ConversionPackage.CURRENT_SOURCE__PHASES:
				setPhases((Integer)newValue);
				return;
			case ConversionPackage.CURRENT_SOURCE__SCAN_TYPE:
				setScanType((sequenceType)newValue);
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
			case ConversionPackage.CURRENT_SOURCE__BUS1:
				setBus1(BUS1_EDEFAULT);
				return;
			case ConversionPackage.CURRENT_SOURCE__AMPS:
				setAmps(AMPS_EDEFAULT);
				return;
			case ConversionPackage.CURRENT_SOURCE__ANGLE:
				setAngle(ANGLE_EDEFAULT);
				return;
			case ConversionPackage.CURRENT_SOURCE__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case ConversionPackage.CURRENT_SOURCE__PHASES:
				setPhases(PHASES_EDEFAULT);
				return;
			case ConversionPackage.CURRENT_SOURCE__SCAN_TYPE:
				setScanType(SCAN_TYPE_EDEFAULT);
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
			case ConversionPackage.CURRENT_SOURCE__BUS1:
				return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
			case ConversionPackage.CURRENT_SOURCE__AMPS:
				return amps != AMPS_EDEFAULT;
			case ConversionPackage.CURRENT_SOURCE__ANGLE:
				return angle != ANGLE_EDEFAULT;
			case ConversionPackage.CURRENT_SOURCE__FREQUENCY:
				return frequency != FREQUENCY_EDEFAULT;
			case ConversionPackage.CURRENT_SOURCE__PHASES:
				return phases != PHASES_EDEFAULT;
			case ConversionPackage.CURRENT_SOURCE__SCAN_TYPE:
				return scanType != SCAN_TYPE_EDEFAULT;
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
		result.append(" (bus1: ");
		result.append(bus1);
		result.append(", amps: ");
		result.append(amps);
		result.append(", angle: ");
		result.append(angle);
		result.append(", frequency: ");
		result.append(frequency);
		result.append(", phases: ");
		result.append(phases);
		result.append(", scanType: ");
		result.append(scanType);
		result.append(')');
		return result.toString();
	}

} //CurrentSourceImpl
