/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion.impl;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import electrickery.conversion.ConversionPackage;
import electrickery.conversion.VoltageSource;
import electrickery.conversion.sequenceType;
import electrickery.conversion.specType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import cern.colt.matrix.tdcomplex.DComplexFactory2D;
import cern.jet.math.tdcomplex.DComplexFunctions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Voltage Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getBaseKV <em>Base KV</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getPerUnit <em>Per Unit</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getMvaSC3 <em>Mva SC3</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getMvaSC1 <em>Mva SC1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getX1R1 <em>X1R1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getX0R0 <em>X0R0</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getISC3 <em>ISC3</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getISC1 <em>ISC1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getR1 <em>R1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getX1 <em>X1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getR0 <em>R0</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getX0 <em>X0</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getScanType <em>Scan Type</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getZ <em>Z</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getZInv <em>ZInv</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getZSpecType <em>ZSpec Type</em>}</li>
 *   <li>{@link electrickery.conversion.impl.VoltageSourceImpl#getVMag <em>VMag</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VoltageSourceImpl extends PowerConversionElementImpl implements VoltageSource {
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
	 * The default value of the '{@link #getPerUnit() <em>Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPerUnit()
	 * @generated
	 * @ordered
	 */
    protected static final double PER_UNIT_EDEFAULT = 1.0;

                /**
	 * The cached value of the '{@link #getPerUnit() <em>Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPerUnit()
	 * @generated
	 * @ordered
	 */
    protected double perUnit = PER_UNIT_EDEFAULT;

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
	 * The default value of the '{@link #getMvaSC3() <em>Mva SC3</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMvaSC3()
	 * @generated
	 * @ordered
	 */
    protected static final double MVA_SC3_EDEFAULT = 2000.0;

    /**
	 * The cached value of the '{@link #getMvaSC3() <em>Mva SC3</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMvaSC3()
	 * @generated
	 * @ordered
	 */
    protected double mvaSC3 = MVA_SC3_EDEFAULT;

    /**
	 * The default value of the '{@link #getMvaSC1() <em>Mva SC1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMvaSC1()
	 * @generated
	 * @ordered
	 */
    protected static final double MVA_SC1_EDEFAULT = 2100.0;

    /**
	 * The cached value of the '{@link #getMvaSC1() <em>Mva SC1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMvaSC1()
	 * @generated
	 * @ordered
	 */
    protected double mvaSC1 = MVA_SC1_EDEFAULT;

    /**
	 * The default value of the '{@link #getX1R1() <em>X1R1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getX1R1()
	 * @generated
	 * @ordered
	 */
    protected static final double X1_R1_EDEFAULT = 4.0;

    /**
	 * The cached value of the '{@link #getX1R1() <em>X1R1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getX1R1()
	 * @generated
	 * @ordered
	 */
    protected double x1R1 = X1_R1_EDEFAULT;

    /**
	 * The default value of the '{@link #getX0R0() <em>X0R0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getX0R0()
	 * @generated
	 * @ordered
	 */
    protected static final double X0_R0_EDEFAULT = 3.0;

    /**
	 * The cached value of the '{@link #getX0R0() <em>X0R0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getX0R0()
	 * @generated
	 * @ordered
	 */
    protected double x0R0 = X0_R0_EDEFAULT;

    /**
	 * The default value of the '{@link #getISC3() <em>ISC3</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getISC3()
	 * @generated
	 * @ordered
	 */
    protected static final double ISC3_EDEFAULT = 10000.0;

    /**
	 * The cached value of the '{@link #getISC3() <em>ISC3</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getISC3()
	 * @generated
	 * @ordered
	 */
    protected double iSC3 = ISC3_EDEFAULT;

    /**
	 * The default value of the '{@link #getISC1() <em>ISC1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getISC1()
	 * @generated
	 * @ordered
	 */
    protected static final double ISC1_EDEFAULT = 10500.0;

    /**
	 * The cached value of the '{@link #getISC1() <em>ISC1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getISC1()
	 * @generated
	 * @ordered
	 */
    protected double iSC1 = ISC1_EDEFAULT;

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
	 * The cached value of the '{@link #getZ() <em>Z</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getZ()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix2D z;

                /**
	 * The cached value of the '{@link #getZInv() <em>ZInv</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getZInv()
	 * @generated
	 * @ordered
	 */
    protected DComplexMatrix2D zInv;

                /**
	 * The default value of the '{@link #getZSpecType() <em>ZSpec Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getZSpecType()
	 * @generated
	 * @ordered
	 */
    protected static final specType ZSPEC_TYPE_EDEFAULT = specType.MVA_SC;

                /**
	 * The cached value of the '{@link #getZSpecType() <em>ZSpec Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getZSpecType()
	 * @generated
	 * @ordered
	 */
    protected specType zSpecType = ZSPEC_TYPE_EDEFAULT;

                /**
	 * The default value of the '{@link #getVMag() <em>VMag</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMag()
	 * @generated
	 * @ordered
	 */
    protected static final double VMAG_EDEFAULT = 0.0;

                                                                /**
	 * The cached value of the '{@link #getVMag() <em>VMag</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVMag()
	 * @generated
	 * @ordered
	 */
    protected double vMag = VMAG_EDEFAULT;

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected VoltageSourceImpl() {
		super();
	}


    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConversionPackage.Literals.VOLTAGE_SOURCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__BUS1, oldBus1, bus1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__BASE_KV, oldBaseKV, baseKV));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getPerUnit() {
		return perUnit;
	}


                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPerUnit(double newPerUnit) {
		double oldPerUnit = perUnit;
		perUnit = newPerUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__PER_UNIT, oldPerUnit, perUnit));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__ANGLE, oldAngle, angle));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__FREQUENCY, oldFrequency, frequency));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getMvaSC3() {
		return mvaSC3;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMvaSC3(double newMvaSC3) {
		double oldMvaSC3 = mvaSC3;
		mvaSC3 = newMvaSC3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__MVA_SC3, oldMvaSC3, mvaSC3));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getMvaSC1() {
		return mvaSC1;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMvaSC1(double newMvaSC1) {
		double oldMvaSC1 = mvaSC1;
		mvaSC1 = newMvaSC1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__MVA_SC1, oldMvaSC1, mvaSC1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getX1R1() {
		return x1R1;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setX1R1(double newX1R1) {
		double oldX1R1 = x1R1;
		x1R1 = newX1R1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__X1_R1, oldX1R1, x1R1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getX0R0() {
		return x0R0;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setX0R0(double newX0R0) {
		double oldX0R0 = x0R0;
		x0R0 = newX0R0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__X0_R0, oldX0R0, x0R0));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getISC3() {
		return iSC3;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setISC3(double newISC3) {
		double oldISC3 = iSC3;
		iSC3 = newISC3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__ISC3, oldISC3, iSC3));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getISC1() {
		return iSC1;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setISC1(double newISC1) {
		double oldISC1 = iSC1;
		iSC1 = newISC1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__ISC1, oldISC1, iSC1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__R1, oldR1, r1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__X1, oldX1, x1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__R0, oldR0, r0));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__X0, oldX0, x0));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__SCAN_TYPE, oldScanType, scanType));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D getZ() {
		if (z != null && ((EObject)z).eIsProxy()) {
			InternalEObject oldZ = (InternalEObject)z;
			z = (DComplexMatrix2D)eResolveProxy(oldZ);
			if (z != oldZ) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.VOLTAGE_SOURCE__Z, oldZ, z));
			}
		}
		return z;
	}


                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D basicGetZ() {
		return z;
	}


                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setZ(DComplexMatrix2D newZ) {
		DComplexMatrix2D oldZ = z;
		z = newZ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__Z, oldZ, z));
	}


                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D getZInv() {
		if (zInv != null && ((EObject)zInv).eIsProxy()) {
			InternalEObject oldZInv = (InternalEObject)zInv;
			zInv = (DComplexMatrix2D)eResolveProxy(oldZInv);
			if (zInv != oldZInv) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.VOLTAGE_SOURCE__ZINV, oldZInv, zInv));
			}
		}
		return zInv;
	}


                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DComplexMatrix2D basicGetZInv() {
		return zInv;
	}


                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setZInv(DComplexMatrix2D newZInv) {
		DComplexMatrix2D oldZInv = zInv;
		zInv = newZInv;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__ZINV, oldZInv, zInv));
	}


                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public specType getZSpecType() {
		return zSpecType;
	}


                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setZSpecType(specType newZSpecType) {
		specType oldZSpecType = zSpecType;
		zSpecType = newZSpecType == null ? ZSPEC_TYPE_EDEFAULT : newZSpecType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__ZSPEC_TYPE, oldZSpecType, zSpecType));
	}


    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getVMag() {
		return vMag;
	}


    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVMag(double newVMag) {
		double oldVMag = vMag;
		vMag = newVMag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.VOLTAGE_SOURCE__VMAG, oldVMag, vMag));
	}


    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void recalcElementData() {
        setZ(DComplexFactory2D.sparse.make(getNPhases(), getNPhases()));
        setZInv(DComplexFactory2D.sparse.make(getNPhases(), getNPhases()));

        double factor;
        if (getNPhases() == 1) {
            factor = 1.0;
        } else {
            factor = Math.sqrt(3.0);
        }

        double rS = 0.0;
        double rM = 0.0;
        double xS = 0.1;
        double xM = 0.0;

        // Calculate the short circuit impedance and make all other spec types agree.
        if (getZSpecType() == specType.MVA_SC) {
            setX1(Math.pow(getBaseKV(), 2) / getMvaSC3() / Math.sqrt(1.0 + 1.0 / Math.pow(getX1R1(), 2)));
            xS = Math.pow(getBaseKV(), 2) / getMvaSC1() / Math.sqrt(1.0 + 1.0 / Math.pow(getX0R0(), 2));
            setR1(getX1() / getX1R1());
            xM = xS - getX1();
            setX0(xS + 2.0 * xM);
            setR0(getX0() / getX0R0());
            setISC3(getMvaSC3() * 1000.0 / (Math.sqrt(3.0) * getBaseKV()));
            setISC1(getMvaSC1() * 1000.0 / (factor * getBaseKV()));

            if (getNPhases() == 1) {
                rS = xS / getX0R0();
            } else {
                rS = (2.0 * getR1() + getR0()) / 3.0;
            }

            rM = (getR0() - getR1()) / 3.0;
        } else if (getZSpecType() == specType.ISC) {
            setMvaSC3(Math.sqrt(3.0) * getBaseKV() * getISC3() / 1000.0);
            setMvaSC1(factor * getBaseKV() * getISC1() / 1000.0);
            setX1(Math.pow(getBaseKV(), 2) / getMvaSC3() / Math.sqrt(1.0 + 1.0 / Math.pow(getX1R1(), 2)));
            xS = Math.pow(getBaseKV(), 2) / getMvaSC1() / Math.sqrt(1.0 + 1.0 / Math.pow(getX0R0(), 2));
            setR1(getX1() / getX1R1());
            xM = xS - getX1();
            setX0(xS + 2.0 * xM);
            setR0(getX0() / getX0R0());

            if (getNPhases() == 1) {
                rS = xS / getX0R0();
            } else {
                rS = (2.0 * getR1() + getR0()) / 3.0;
            }

            rM = (getR0() - getR1()) / 3.0;
        } else if (getZSpecType() == specType.Z1Z0) {
            double[] z1 = {r1, x1};
            setISC3(getBaseKV() * 1000.0 / Math.sqrt(3.0) / DComplexFunctions.abs.apply(z1));

            if (getNPhases() == 1) {
                // Force Z0 to be Z1 so Zs is same as Z1.
                setR0(getR1());
                setX0(getX1());
            }

            rS = (2.0 * getR1() + getR0()) / 3.0;
            xS = (2.0 * getX1() + getX0()) / 3.0;

            double[] zS = {rS, xS};
            setISC1(getBaseKV() * 1000.0 / factor / DComplexFunctions.abs.apply(zS));
            setMvaSC3(Math.sqrt(3.0) * getBaseKV() * getISC3() / 1000.0);
            setMvaSC1(factor * getBaseKV() * getISC1() / 1000.0);
            xM = xS - getX1();

            rS = (2.0 * getR1() + getR0()) / 3.0;
            rM = (getR0() - getR1() / 3.0);
        } else {
            // TODO: Handle unknown spec type.
        }

        for (int i = 0; i < getNPhases(); i++) {
            getZ().setQuick(i, i, rS, xS);
            for (int j = 0; j < i-1; j++) {
                getZ().setQuick(i, j, rM, xM);
                if (i != j)
                    getZ().setQuick(j, i, rM, xM);
            }
        }

        if (getNPhases() == 1) {
            setVMag(getBaseKV() * getPerUnit() * 1000.0);
        } else {
            setVMag(getBaseKV() * getPerUnit() * 1000.0 / 2.0 / Math.sin((180.0 / getNPhases()) * Math.PI / 180.0));
        }

        // TODO: Implement find spectrum or change to reference.
        if (getSpectrum() == "") {
            System.out.println("Spectrum '" + getSpectrum() + "' for VSource '" + getName() + "' not found.");
        }
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

        double freqMult = yPrimFreq / getBaseFreq();

        for (int i = 0; i < getNPhases(); i++) {
            for (int j = 0; j < getNPhases(); j++) {
                double[] value = getZ().getQuick(i, j);
                // Modify from base frequency.
                value[1] = value[1] * freqMult;
                getZInv().set(i, j, value);
            }
        }

        // Invert in place.
        getZInv().assign(DComplexFunctions.inv);
        // TODO: Catch matrix inversion exception.


        for (int i = 0; i < getNPhases(); i++) {
            for (int j = 0; j < getNPhases(); j++) {
                double[] value = getZInv().getQuick(i, j);
                getYPrimSeries().setQuick(i, j, value);
                getYPrimSeries().setQuick(i + getNPhases(), j + getNPhases(), value);
                getYPrimSeries().setQuick(i + getNPhases(), j, -value[0], -value[1]);
            }
        }

        setYPrim(getYPrimSeries().copy());

        // For any conductor that is open, zero out row and column.
        // FIXME: Call calcYPrim method from super class.

        setYPrimInvalid(false);
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.VOLTAGE_SOURCE__BUS1:
				return getBus1();
			case ConversionPackage.VOLTAGE_SOURCE__BASE_KV:
				return getBaseKV();
			case ConversionPackage.VOLTAGE_SOURCE__PER_UNIT:
				return getPerUnit();
			case ConversionPackage.VOLTAGE_SOURCE__ANGLE:
				return getAngle();
			case ConversionPackage.VOLTAGE_SOURCE__FREQUENCY:
				return getFrequency();
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC3:
				return getMvaSC3();
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC1:
				return getMvaSC1();
			case ConversionPackage.VOLTAGE_SOURCE__X1_R1:
				return getX1R1();
			case ConversionPackage.VOLTAGE_SOURCE__X0_R0:
				return getX0R0();
			case ConversionPackage.VOLTAGE_SOURCE__ISC3:
				return getISC3();
			case ConversionPackage.VOLTAGE_SOURCE__ISC1:
				return getISC1();
			case ConversionPackage.VOLTAGE_SOURCE__R1:
				return getR1();
			case ConversionPackage.VOLTAGE_SOURCE__X1:
				return getX1();
			case ConversionPackage.VOLTAGE_SOURCE__R0:
				return getR0();
			case ConversionPackage.VOLTAGE_SOURCE__X0:
				return getX0();
			case ConversionPackage.VOLTAGE_SOURCE__SCAN_TYPE:
				return getScanType();
			case ConversionPackage.VOLTAGE_SOURCE__Z:
				if (resolve) return getZ();
				return basicGetZ();
			case ConversionPackage.VOLTAGE_SOURCE__ZINV:
				if (resolve) return getZInv();
				return basicGetZInv();
			case ConversionPackage.VOLTAGE_SOURCE__ZSPEC_TYPE:
				return getZSpecType();
			case ConversionPackage.VOLTAGE_SOURCE__VMAG:
				return getVMag();
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
			case ConversionPackage.VOLTAGE_SOURCE__BUS1:
				setBus1((String)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__BASE_KV:
				setBaseKV((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__PER_UNIT:
				setPerUnit((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ANGLE:
				setAngle((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__FREQUENCY:
				setFrequency((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC3:
				setMvaSC3((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC1:
				setMvaSC1((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X1_R1:
				setX1R1((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X0_R0:
				setX0R0((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ISC3:
				setISC3((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ISC1:
				setISC1((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__R1:
				setR1((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X1:
				setX1((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__R0:
				setR0((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X0:
				setX0((Double)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__SCAN_TYPE:
				setScanType((sequenceType)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__Z:
				setZ((DComplexMatrix2D)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ZINV:
				setZInv((DComplexMatrix2D)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ZSPEC_TYPE:
				setZSpecType((specType)newValue);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__VMAG:
				setVMag((Double)newValue);
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
			case ConversionPackage.VOLTAGE_SOURCE__BUS1:
				setBus1(BUS1_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__BASE_KV:
				setBaseKV(BASE_KV_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__PER_UNIT:
				setPerUnit(PER_UNIT_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ANGLE:
				setAngle(ANGLE_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC3:
				setMvaSC3(MVA_SC3_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC1:
				setMvaSC1(MVA_SC1_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X1_R1:
				setX1R1(X1_R1_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X0_R0:
				setX0R0(X0_R0_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ISC3:
				setISC3(ISC3_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ISC1:
				setISC1(ISC1_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__R1:
				setR1(R1_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X1:
				setX1(X1_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__R0:
				setR0(R0_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__X0:
				setX0(X0_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__SCAN_TYPE:
				setScanType(SCAN_TYPE_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__Z:
				setZ((DComplexMatrix2D)null);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ZINV:
				setZInv((DComplexMatrix2D)null);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__ZSPEC_TYPE:
				setZSpecType(ZSPEC_TYPE_EDEFAULT);
				return;
			case ConversionPackage.VOLTAGE_SOURCE__VMAG:
				setVMag(VMAG_EDEFAULT);
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
			case ConversionPackage.VOLTAGE_SOURCE__BUS1:
				return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
			case ConversionPackage.VOLTAGE_SOURCE__BASE_KV:
				return baseKV != BASE_KV_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__PER_UNIT:
				return perUnit != PER_UNIT_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__ANGLE:
				return angle != ANGLE_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__FREQUENCY:
				return frequency != FREQUENCY_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC3:
				return mvaSC3 != MVA_SC3_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__MVA_SC1:
				return mvaSC1 != MVA_SC1_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__X1_R1:
				return x1R1 != X1_R1_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__X0_R0:
				return x0R0 != X0_R0_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__ISC3:
				return iSC3 != ISC3_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__ISC1:
				return iSC1 != ISC1_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__R1:
				return r1 != R1_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__X1:
				return x1 != X1_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__R0:
				return r0 != R0_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__X0:
				return x0 != X0_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__SCAN_TYPE:
				return scanType != SCAN_TYPE_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__Z:
				return z != null;
			case ConversionPackage.VOLTAGE_SOURCE__ZINV:
				return zInv != null;
			case ConversionPackage.VOLTAGE_SOURCE__ZSPEC_TYPE:
				return zSpecType != ZSPEC_TYPE_EDEFAULT;
			case ConversionPackage.VOLTAGE_SOURCE__VMAG:
				return vMag != VMAG_EDEFAULT;
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
		result.append(", baseKV: ");
		result.append(baseKV);
		result.append(", perUnit: ");
		result.append(perUnit);
		result.append(", angle: ");
		result.append(angle);
		result.append(", frequency: ");
		result.append(frequency);
		result.append(", mvaSC3: ");
		result.append(mvaSC3);
		result.append(", mvaSC1: ");
		result.append(mvaSC1);
		result.append(", x1R1: ");
		result.append(x1R1);
		result.append(", x0R0: ");
		result.append(x0R0);
		result.append(", iSC3: ");
		result.append(iSC3);
		result.append(", iSC1: ");
		result.append(iSC1);
		result.append(", r1: ");
		result.append(r1);
		result.append(", x1: ");
		result.append(x1);
		result.append(", r0: ");
		result.append(r0);
		result.append(", x0: ");
		result.append(x0);
		result.append(", scanType: ");
		result.append(scanType);
		result.append(", zSpecType: ");
		result.append(zSpecType);
		result.append(", vMag: ");
		result.append(vMag);
		result.append(')');
		return result.toString();
	}

} //VoltageSourceImpl
