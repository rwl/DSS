/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.impl;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.common.lengthUnit;
import electrickery.general.GeneralPackage;
import electrickery.general.LineCode;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Line Code</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getR1 <em>R1</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getX1 <em>X1</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getR0 <em>R0</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getX0 <em>X0</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getC1 <em>C1</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getC0 <em>C0</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getRMatrix <em>RMatrix</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getXMatrix <em>XMatrix</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getCMatrix <em>CMatrix</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getBaseFreq <em>Base Freq</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getFaultRate <em>Fault Rate</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getPctPerm <em>Pct Perm</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getRepair <em>Repair</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#isKron <em>Kron</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getRg <em>Rg</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getXg <em>Xg</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getRho <em>Rho</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getNeutral <em>Neutral</em>}</li>
 *   <li>{@link electrickery.general.impl.LineCodeImpl#getLike <em>Like</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LineCodeImpl extends EObjectImpl implements LineCode {
    /**
	 * The default value of the '{@link #getNPhases() <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNPhases()
	 * @generated
	 * @ordered
	 */
    protected static final int NPHASES_EDEFAULT = 3;

    /**
	 * The cached value of the '{@link #getNPhases() <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNPhases()
	 * @generated
	 * @ordered
	 */
    protected int nPhases = NPHASES_EDEFAULT;

    /**
	 * The default value of the '{@link #getR1() <em>R1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getR1()
	 * @generated
	 * @ordered
	 */
    protected static final double R1_EDEFAULT = 0.058;

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
    protected static final double X1_EDEFAULT = 0.1206;

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
	protected static final double R0_EDEFAULT = 0.4047;

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
    protected static final double X0_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #getC1() <em>C1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getC1()
	 * @generated
	 * @ordered
	 */
    protected static final double C1_EDEFAULT = 3.4;

    /**
	 * The cached value of the '{@link #getC1() <em>C1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getC1()
	 * @generated
	 * @ordered
	 */
    protected double c1 = C1_EDEFAULT;

    /**
	 * The default value of the '{@link #getC0() <em>C0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getC0()
	 * @generated
	 * @ordered
	 */
    protected static final double C0_EDEFAULT = 1.6;

    /**
	 * The cached value of the '{@link #getC0() <em>C0</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getC0()
	 * @generated
	 * @ordered
	 */
    protected double c0 = C0_EDEFAULT;

    /**
	 * The default value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
    protected static final lengthUnit UNITS_EDEFAULT = lengthUnit.NONE;

    /**
	 * The cached value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
    protected lengthUnit units = UNITS_EDEFAULT;

    /**
	 * The cached value of the '{@link #getRMatrix() <em>RMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRMatrix()
	 * @generated
	 * @ordered
	 */
    protected DoubleMatrix2D rMatrix;

    /**
	 * The cached value of the '{@link #getXMatrix() <em>XMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXMatrix()
	 * @generated
	 * @ordered
	 */
    protected DoubleMatrix2D xMatrix;

    /**
	 * The cached value of the '{@link #getCMatrix() <em>CMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCMatrix()
	 * @generated
	 * @ordered
	 */
    protected DoubleMatrix2D cMatrix;

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
	 * The default value of the '{@link #getNormAmps() <em>Norm Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNormAmps()
	 * @generated
	 * @ordered
	 */
    protected static final double NORM_AMPS_EDEFAULT = 400.0;

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
    protected static final double EMERG_AMPS_EDEFAULT = 600.0;

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
	 * The default value of the '{@link #getFaultRate() <em>Fault Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFaultRate()
	 * @generated
	 * @ordered
	 */
    protected static final double FAULT_RATE_EDEFAULT = 0.1;

    /**
	 * The cached value of the '{@link #getFaultRate() <em>Fault Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFaultRate()
	 * @generated
	 * @ordered
	 */
    protected double faultRate = FAULT_RATE_EDEFAULT;

    /**
	 * The default value of the '{@link #getPctPerm() <em>Pct Perm</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPctPerm()
	 * @generated
	 * @ordered
	 */
    protected static final int PCT_PERM_EDEFAULT = 20;

    /**
	 * The cached value of the '{@link #getPctPerm() <em>Pct Perm</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPctPerm()
	 * @generated
	 * @ordered
	 */
    protected int pctPerm = PCT_PERM_EDEFAULT;

    /**
	 * The default value of the '{@link #getRepair() <em>Repair</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRepair()
	 * @generated
	 * @ordered
	 */
    protected static final int REPAIR_EDEFAULT = 3;

    /**
	 * The cached value of the '{@link #getRepair() <em>Repair</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRepair()
	 * @generated
	 * @ordered
	 */
    protected int repair = REPAIR_EDEFAULT;

    /**
	 * The default value of the '{@link #isKron() <em>Kron</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isKron()
	 * @generated
	 * @ordered
	 */
    protected static final boolean KRON_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isKron() <em>Kron</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isKron()
	 * @generated
	 * @ordered
	 */
    protected boolean kron = KRON_EDEFAULT;

    /**
	 * The default value of the '{@link #getRg() <em>Rg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRg()
	 * @generated
	 * @ordered
	 */
    protected static final double RG_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getRg() <em>Rg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRg()
	 * @generated
	 * @ordered
	 */
    protected double rg = RG_EDEFAULT;

    /**
	 * The default value of the '{@link #getXg() <em>Xg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXg()
	 * @generated
	 * @ordered
	 */
    protected static final double XG_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getXg() <em>Xg</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getXg()
	 * @generated
	 * @ordered
	 */
    protected double xg = XG_EDEFAULT;

    /**
	 * The default value of the '{@link #getRho() <em>Rho</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRho()
	 * @generated
	 * @ordered
	 */
    protected static final double RHO_EDEFAULT = 100.0;

    /**
	 * The cached value of the '{@link #getRho() <em>Rho</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRho()
	 * @generated
	 * @ordered
	 */
    protected double rho = RHO_EDEFAULT;

    /**
	 * The default value of the '{@link #getNeutral() <em>Neutral</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNeutral()
	 * @generated
	 * @ordered
	 */
	protected static final int NEUTRAL_EDEFAULT = 0;

				/**
	 * The cached value of the '{@link #getNeutral() <em>Neutral</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNeutral()
	 * @generated
	 * @ordered
	 */
	protected int neutral = NEUTRAL_EDEFAULT;

				/**
	 * The cached value of the '{@link #getLike() <em>Like</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLike()
	 * @generated
	 * @ordered
	 */
	protected LineCode like;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected LineCodeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return GeneralPackage.Literals.LINE_CODE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getNPhases() {
		return nPhases;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNPhases(int newNPhases) {
		int oldNPhases = nPhases;
		nPhases = newNPhases;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__NPHASES, oldNPhases, nPhases));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__R1, oldR1, r1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__X1, oldX1, x1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__R0, oldR0, r0));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__X0, oldX0, x0));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getC1() {
		return c1;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setC1(double newC1) {
		double oldC1 = c1;
		c1 = newC1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__C1, oldC1, c1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getC0() {
		return c0;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setC0(double newC0) {
		double oldC0 = c0;
		c0 = newC0;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__C0, oldC0, c0));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public lengthUnit getUnits() {
		return units;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUnits(lengthUnit newUnits) {
		lengthUnit oldUnits = units;
		units = newUnits == null ? UNITS_EDEFAULT : newUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__UNITS, oldUnits, units));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D getRMatrix() {
		if (rMatrix != null && ((EObject)rMatrix).eIsProxy()) {
			InternalEObject oldRMatrix = (InternalEObject)rMatrix;
			rMatrix = (DoubleMatrix2D)eResolveProxy(oldRMatrix);
			if (rMatrix != oldRMatrix) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_CODE__RMATRIX, oldRMatrix, rMatrix));
			}
		}
		return rMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D basicGetRMatrix() {
		return rMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRMatrix(DoubleMatrix2D newRMatrix) {
		DoubleMatrix2D oldRMatrix = rMatrix;
		rMatrix = newRMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__RMATRIX, oldRMatrix, rMatrix));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D getXMatrix() {
		if (xMatrix != null && ((EObject)xMatrix).eIsProxy()) {
			InternalEObject oldXMatrix = (InternalEObject)xMatrix;
			xMatrix = (DoubleMatrix2D)eResolveProxy(oldXMatrix);
			if (xMatrix != oldXMatrix) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_CODE__XMATRIX, oldXMatrix, xMatrix));
			}
		}
		return xMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D basicGetXMatrix() {
		return xMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXMatrix(DoubleMatrix2D newXMatrix) {
		DoubleMatrix2D oldXMatrix = xMatrix;
		xMatrix = newXMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__XMATRIX, oldXMatrix, xMatrix));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D getCMatrix() {
		if (cMatrix != null && ((EObject)cMatrix).eIsProxy()) {
			InternalEObject oldCMatrix = (InternalEObject)cMatrix;
			cMatrix = (DoubleMatrix2D)eResolveProxy(oldCMatrix);
			if (cMatrix != oldCMatrix) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_CODE__CMATRIX, oldCMatrix, cMatrix));
			}
		}
		return cMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DoubleMatrix2D basicGetCMatrix() {
		return cMatrix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCMatrix(DoubleMatrix2D newCMatrix) {
		DoubleMatrix2D oldCMatrix = cMatrix;
		cMatrix = newCMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__CMATRIX, oldCMatrix, cMatrix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__BASE_FREQ, oldBaseFreq, baseFreq));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__NORM_AMPS, oldNormAmps, normAmps));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__EMERG_AMPS, oldEmergAmps, emergAmps));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getFaultRate() {
		return faultRate;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFaultRate(double newFaultRate) {
		double oldFaultRate = faultRate;
		faultRate = newFaultRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__FAULT_RATE, oldFaultRate, faultRate));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getPctPerm() {
		return pctPerm;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPctPerm(int newPctPerm) {
		int oldPctPerm = pctPerm;
		pctPerm = newPctPerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__PCT_PERM, oldPctPerm, pctPerm));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getRepair() {
		return repair;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRepair(int newRepair) {
		int oldRepair = repair;
		repair = newRepair;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__REPAIR, oldRepair, repair));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isKron() {
		return kron;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKron(boolean newKron) {
		boolean oldKron = kron;
		kron = newKron;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__KRON, oldKron, kron));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getRg() {
		return rg;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRg(double newRg) {
		double oldRg = rg;
		rg = newRg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__RG, oldRg, rg));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getXg() {
		return xg;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setXg(double newXg) {
		double oldXg = xg;
		xg = newXg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__XG, oldXg, xg));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getRho() {
		return rho;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRho(double newRho) {
		double oldRho = rho;
		rho = newRho;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__RHO, oldRho, rho));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNeutral() {
		return neutral;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeutral(int newNeutral) {
		int oldNeutral = neutral;
		neutral = newNeutral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__NEUTRAL, oldNeutral, neutral));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineCode getLike() {
		if (like != null && like.eIsProxy()) {
			InternalEObject oldLike = (InternalEObject)like;
			like = (LineCode)eResolveProxy(oldLike);
			if (like != oldLike) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GeneralPackage.LINE_CODE__LIKE, oldLike, like));
			}
		}
		return like;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineCode basicGetLike() {
		return like;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLike(LineCode newLike) {
		LineCode oldLike = like;
		like = newLike;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.LINE_CODE__LIKE, oldLike, like));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.LINE_CODE__NPHASES:
				return getNPhases();
			case GeneralPackage.LINE_CODE__R1:
				return getR1();
			case GeneralPackage.LINE_CODE__X1:
				return getX1();
			case GeneralPackage.LINE_CODE__R0:
				return getR0();
			case GeneralPackage.LINE_CODE__X0:
				return getX0();
			case GeneralPackage.LINE_CODE__C1:
				return getC1();
			case GeneralPackage.LINE_CODE__C0:
				return getC0();
			case GeneralPackage.LINE_CODE__UNITS:
				return getUnits();
			case GeneralPackage.LINE_CODE__RMATRIX:
				if (resolve) return getRMatrix();
				return basicGetRMatrix();
			case GeneralPackage.LINE_CODE__XMATRIX:
				if (resolve) return getXMatrix();
				return basicGetXMatrix();
			case GeneralPackage.LINE_CODE__CMATRIX:
				if (resolve) return getCMatrix();
				return basicGetCMatrix();
			case GeneralPackage.LINE_CODE__BASE_FREQ:
				return getBaseFreq();
			case GeneralPackage.LINE_CODE__NORM_AMPS:
				return getNormAmps();
			case GeneralPackage.LINE_CODE__EMERG_AMPS:
				return getEmergAmps();
			case GeneralPackage.LINE_CODE__FAULT_RATE:
				return getFaultRate();
			case GeneralPackage.LINE_CODE__PCT_PERM:
				return getPctPerm();
			case GeneralPackage.LINE_CODE__REPAIR:
				return getRepair();
			case GeneralPackage.LINE_CODE__KRON:
				return isKron();
			case GeneralPackage.LINE_CODE__RG:
				return getRg();
			case GeneralPackage.LINE_CODE__XG:
				return getXg();
			case GeneralPackage.LINE_CODE__RHO:
				return getRho();
			case GeneralPackage.LINE_CODE__NEUTRAL:
				return getNeutral();
			case GeneralPackage.LINE_CODE__LIKE:
				if (resolve) return getLike();
				return basicGetLike();
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
			case GeneralPackage.LINE_CODE__NPHASES:
				setNPhases((Integer)newValue);
				return;
			case GeneralPackage.LINE_CODE__R1:
				setR1((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__X1:
				setX1((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__R0:
				setR0((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__X0:
				setX0((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__C1:
				setC1((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__C0:
				setC0((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__UNITS:
				setUnits((lengthUnit)newValue);
				return;
			case GeneralPackage.LINE_CODE__RMATRIX:
				setRMatrix((DoubleMatrix2D)newValue);
				return;
			case GeneralPackage.LINE_CODE__XMATRIX:
				setXMatrix((DoubleMatrix2D)newValue);
				return;
			case GeneralPackage.LINE_CODE__CMATRIX:
				setCMatrix((DoubleMatrix2D)newValue);
				return;
			case GeneralPackage.LINE_CODE__BASE_FREQ:
				setBaseFreq((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__NORM_AMPS:
				setNormAmps((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__EMERG_AMPS:
				setEmergAmps((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__FAULT_RATE:
				setFaultRate((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__PCT_PERM:
				setPctPerm((Integer)newValue);
				return;
			case GeneralPackage.LINE_CODE__REPAIR:
				setRepair((Integer)newValue);
				return;
			case GeneralPackage.LINE_CODE__KRON:
				setKron((Boolean)newValue);
				return;
			case GeneralPackage.LINE_CODE__RG:
				setRg((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__XG:
				setXg((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__RHO:
				setRho((Double)newValue);
				return;
			case GeneralPackage.LINE_CODE__NEUTRAL:
				setNeutral((Integer)newValue);
				return;
			case GeneralPackage.LINE_CODE__LIKE:
				setLike((LineCode)newValue);
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
			case GeneralPackage.LINE_CODE__NPHASES:
				setNPhases(NPHASES_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__R1:
				setR1(R1_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__X1:
				setX1(X1_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__R0:
				setR0(R0_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__X0:
				setX0(X0_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__C1:
				setC1(C1_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__C0:
				setC0(C0_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__UNITS:
				setUnits(UNITS_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__RMATRIX:
				setRMatrix((DoubleMatrix2D)null);
				return;
			case GeneralPackage.LINE_CODE__XMATRIX:
				setXMatrix((DoubleMatrix2D)null);
				return;
			case GeneralPackage.LINE_CODE__CMATRIX:
				setCMatrix((DoubleMatrix2D)null);
				return;
			case GeneralPackage.LINE_CODE__BASE_FREQ:
				setBaseFreq(BASE_FREQ_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__NORM_AMPS:
				setNormAmps(NORM_AMPS_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__EMERG_AMPS:
				setEmergAmps(EMERG_AMPS_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__FAULT_RATE:
				setFaultRate(FAULT_RATE_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__PCT_PERM:
				setPctPerm(PCT_PERM_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__REPAIR:
				setRepair(REPAIR_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__KRON:
				setKron(KRON_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__RG:
				setRg(RG_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__XG:
				setXg(XG_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__RHO:
				setRho(RHO_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__NEUTRAL:
				setNeutral(NEUTRAL_EDEFAULT);
				return;
			case GeneralPackage.LINE_CODE__LIKE:
				setLike((LineCode)null);
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
			case GeneralPackage.LINE_CODE__NPHASES:
				return nPhases != NPHASES_EDEFAULT;
			case GeneralPackage.LINE_CODE__R1:
				return r1 != R1_EDEFAULT;
			case GeneralPackage.LINE_CODE__X1:
				return x1 != X1_EDEFAULT;
			case GeneralPackage.LINE_CODE__R0:
				return r0 != R0_EDEFAULT;
			case GeneralPackage.LINE_CODE__X0:
				return x0 != X0_EDEFAULT;
			case GeneralPackage.LINE_CODE__C1:
				return c1 != C1_EDEFAULT;
			case GeneralPackage.LINE_CODE__C0:
				return c0 != C0_EDEFAULT;
			case GeneralPackage.LINE_CODE__UNITS:
				return units != UNITS_EDEFAULT;
			case GeneralPackage.LINE_CODE__RMATRIX:
				return rMatrix != null;
			case GeneralPackage.LINE_CODE__XMATRIX:
				return xMatrix != null;
			case GeneralPackage.LINE_CODE__CMATRIX:
				return cMatrix != null;
			case GeneralPackage.LINE_CODE__BASE_FREQ:
				return baseFreq != BASE_FREQ_EDEFAULT;
			case GeneralPackage.LINE_CODE__NORM_AMPS:
				return normAmps != NORM_AMPS_EDEFAULT;
			case GeneralPackage.LINE_CODE__EMERG_AMPS:
				return emergAmps != EMERG_AMPS_EDEFAULT;
			case GeneralPackage.LINE_CODE__FAULT_RATE:
				return faultRate != FAULT_RATE_EDEFAULT;
			case GeneralPackage.LINE_CODE__PCT_PERM:
				return pctPerm != PCT_PERM_EDEFAULT;
			case GeneralPackage.LINE_CODE__REPAIR:
				return repair != REPAIR_EDEFAULT;
			case GeneralPackage.LINE_CODE__KRON:
				return kron != KRON_EDEFAULT;
			case GeneralPackage.LINE_CODE__RG:
				return rg != RG_EDEFAULT;
			case GeneralPackage.LINE_CODE__XG:
				return xg != XG_EDEFAULT;
			case GeneralPackage.LINE_CODE__RHO:
				return rho != RHO_EDEFAULT;
			case GeneralPackage.LINE_CODE__NEUTRAL:
				return neutral != NEUTRAL_EDEFAULT;
			case GeneralPackage.LINE_CODE__LIKE:
				return like != null;
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
		result.append(" (nPhases: ");
		result.append(nPhases);
		result.append(", r1: ");
		result.append(r1);
		result.append(", x1: ");
		result.append(x1);
		result.append(", r0: ");
		result.append(r0);
		result.append(", x0: ");
		result.append(x0);
		result.append(", c1: ");
		result.append(c1);
		result.append(", c0: ");
		result.append(c0);
		result.append(", units: ");
		result.append(units);
		result.append(", baseFreq: ");
		result.append(baseFreq);
		result.append(", normAmps: ");
		result.append(normAmps);
		result.append(", emergAmps: ");
		result.append(emergAmps);
		result.append(", faultRate: ");
		result.append(faultRate);
		result.append(", pctPerm: ");
		result.append(pctPerm);
		result.append(", repair: ");
		result.append(repair);
		result.append(", kron: ");
		result.append(kron);
		result.append(", rg: ");
		result.append(rg);
		result.append(", xg: ");
		result.append(xg);
		result.append(", rho: ");
		result.append(rho);
		result.append(", neutral: ");
		result.append(neutral);
		result.append(')');
		return result.toString();
	}

} //LineCodeImpl
