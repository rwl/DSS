/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery.impl;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.common.lengthUnit;
import electrickery.delivery.DeliveryPackage;
import electrickery.delivery.Line;

import electrickery.general.LineSpacing;
import electrickery.general.WireData;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Line</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getLineCode <em>Line Code</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getLength <em>Length</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getR1 <em>R1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getX1 <em>X1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getR0 <em>R0</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getX0 <em>X0</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getC1 <em>C1</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getC0 <em>C0</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getRMatrix <em>RMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getXMatrix <em>XMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getCMatrix <em>CMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#isSwitch <em>Switch</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getRg <em>Rg</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getXg <em>Xg</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getRho <em>Rho</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getSpacing <em>Spacing</em>}</li>
 *   <li>{@link electrickery.delivery.impl.LineImpl#getWires <em>Wires</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LineImpl extends PowerDeliveryElementImpl implements Line {
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
	 * The default value of the '{@link #getBus2() <em>Bus2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBus2()
	 * @generated
	 * @ordered
	 */
    protected static final String BUS2_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getBus2() <em>Bus2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBus2()
	 * @generated
	 * @ordered
	 */
    protected String bus2 = BUS2_EDEFAULT;

    /**
	 * The default value of the '{@link #getLineCode() <em>Line Code</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLineCode()
	 * @generated
	 * @ordered
	 */
    protected static final String LINE_CODE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getLineCode() <em>Line Code</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLineCode()
	 * @generated
	 * @ordered
	 */
    protected String lineCode = LINE_CODE_EDEFAULT;

    /**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
    protected static final double LENGTH_EDEFAULT = 0.0;

    /**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
    protected double length = LENGTH_EDEFAULT;

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
    protected static final double R0_EDEFAULT = 0.1784;

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
    protected static final double X0_EDEFAULT = 0.4047;

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
    protected static final double C1_EDEFAULT = 0.0;

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
    protected static final double C0_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #isSwitch() <em>Switch</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSwitch()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SWITCH_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isSwitch() <em>Switch</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSwitch()
	 * @generated
	 * @ordered
	 */
    protected boolean switch_ = SWITCH_EDEFAULT;

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
	 * The default value of the '{@link #getGeometry() <em>Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGeometry()
	 * @generated
	 * @ordered
	 */
    protected static final String GEOMETRY_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getGeometry() <em>Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGeometry()
	 * @generated
	 * @ordered
	 */
    protected String geometry = GEOMETRY_EDEFAULT;

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
	 * The cached value of the '{@link #getSpacing() <em>Spacing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpacing()
	 * @generated
	 * @ordered
	 */
	protected LineSpacing spacing;

				/**
	 * The cached value of the '{@link #getWires() <em>Wires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWires()
	 * @generated
	 * @ordered
	 */
	protected EList<WireData> wires;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected LineImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return DeliveryPackage.Literals.LINE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__BUS1, oldBus1, bus1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getBus2() {
		return bus2;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBus2(String newBus2) {
		String oldBus2 = bus2;
		bus2 = newBus2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__BUS2, oldBus2, bus2));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getLineCode() {
		return lineCode;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLineCode(String newLineCode) {
		String oldLineCode = lineCode;
		lineCode = newLineCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__LINE_CODE, oldLineCode, lineCode));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getLength() {
		return length;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLength(double newLength) {
		double oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__LENGTH, oldLength, length));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__R1, oldR1, r1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__X1, oldX1, x1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__R0, oldR0, r0));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__X0, oldX0, x0));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__C1, oldC1, c1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__C0, oldC0, c0));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.LINE__RMATRIX, oldRMatrix, rMatrix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__RMATRIX, oldRMatrix, rMatrix));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.LINE__XMATRIX, oldXMatrix, xMatrix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__XMATRIX, oldXMatrix, xMatrix));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.LINE__CMATRIX, oldCMatrix, cMatrix));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__CMATRIX, oldCMatrix, cMatrix));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSwitch() {
		return switch_;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSwitch(boolean newSwitch) {
		boolean oldSwitch = switch_;
		switch_ = newSwitch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__SWITCH, oldSwitch, switch_));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__RG, oldRg, rg));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__XG, oldXg, xg));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__RHO, oldRho, rho));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getGeometry() {
		return geometry;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGeometry(String newGeometry) {
		String oldGeometry = geometry;
		geometry = newGeometry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__GEOMETRY, oldGeometry, geometry));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__UNITS, oldUnits, units));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineSpacing getSpacing() {
		if (spacing != null && spacing.eIsProxy()) {
			InternalEObject oldSpacing = (InternalEObject)spacing;
			spacing = (LineSpacing)eResolveProxy(oldSpacing);
			if (spacing != oldSpacing) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeliveryPackage.LINE__SPACING, oldSpacing, spacing));
			}
		}
		return spacing;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineSpacing basicGetSpacing() {
		return spacing;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpacing(LineSpacing newSpacing) {
		LineSpacing oldSpacing = spacing;
		spacing = newSpacing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__SPACING, oldSpacing, spacing));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireData> getWires() {
		if (wires == null) {
			wires = new EObjectResolvingEList<WireData>(WireData.class, this, DeliveryPackage.LINE__WIRES);
		}
		return wires;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeliveryPackage.LINE__BUS1:
				return getBus1();
			case DeliveryPackage.LINE__BUS2:
				return getBus2();
			case DeliveryPackage.LINE__LINE_CODE:
				return getLineCode();
			case DeliveryPackage.LINE__LENGTH:
				return getLength();
			case DeliveryPackage.LINE__R1:
				return getR1();
			case DeliveryPackage.LINE__X1:
				return getX1();
			case DeliveryPackage.LINE__R0:
				return getR0();
			case DeliveryPackage.LINE__X0:
				return getX0();
			case DeliveryPackage.LINE__C1:
				return getC1();
			case DeliveryPackage.LINE__C0:
				return getC0();
			case DeliveryPackage.LINE__RMATRIX:
				if (resolve) return getRMatrix();
				return basicGetRMatrix();
			case DeliveryPackage.LINE__XMATRIX:
				if (resolve) return getXMatrix();
				return basicGetXMatrix();
			case DeliveryPackage.LINE__CMATRIX:
				if (resolve) return getCMatrix();
				return basicGetCMatrix();
			case DeliveryPackage.LINE__SWITCH:
				return isSwitch();
			case DeliveryPackage.LINE__RG:
				return getRg();
			case DeliveryPackage.LINE__XG:
				return getXg();
			case DeliveryPackage.LINE__RHO:
				return getRho();
			case DeliveryPackage.LINE__GEOMETRY:
				return getGeometry();
			case DeliveryPackage.LINE__UNITS:
				return getUnits();
			case DeliveryPackage.LINE__SPACING:
				if (resolve) return getSpacing();
				return basicGetSpacing();
			case DeliveryPackage.LINE__WIRES:
				return getWires();
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
			case DeliveryPackage.LINE__BUS1:
				setBus1((String)newValue);
				return;
			case DeliveryPackage.LINE__BUS2:
				setBus2((String)newValue);
				return;
			case DeliveryPackage.LINE__LINE_CODE:
				setLineCode((String)newValue);
				return;
			case DeliveryPackage.LINE__LENGTH:
				setLength((Double)newValue);
				return;
			case DeliveryPackage.LINE__R1:
				setR1((Double)newValue);
				return;
			case DeliveryPackage.LINE__X1:
				setX1((Double)newValue);
				return;
			case DeliveryPackage.LINE__R0:
				setR0((Double)newValue);
				return;
			case DeliveryPackage.LINE__X0:
				setX0((Double)newValue);
				return;
			case DeliveryPackage.LINE__C1:
				setC1((Double)newValue);
				return;
			case DeliveryPackage.LINE__C0:
				setC0((Double)newValue);
				return;
			case DeliveryPackage.LINE__RMATRIX:
				setRMatrix((DoubleMatrix2D)newValue);
				return;
			case DeliveryPackage.LINE__XMATRIX:
				setXMatrix((DoubleMatrix2D)newValue);
				return;
			case DeliveryPackage.LINE__CMATRIX:
				setCMatrix((DoubleMatrix2D)newValue);
				return;
			case DeliveryPackage.LINE__SWITCH:
				setSwitch((Boolean)newValue);
				return;
			case DeliveryPackage.LINE__RG:
				setRg((Double)newValue);
				return;
			case DeliveryPackage.LINE__XG:
				setXg((Double)newValue);
				return;
			case DeliveryPackage.LINE__RHO:
				setRho((Double)newValue);
				return;
			case DeliveryPackage.LINE__GEOMETRY:
				setGeometry((String)newValue);
				return;
			case DeliveryPackage.LINE__UNITS:
				setUnits((lengthUnit)newValue);
				return;
			case DeliveryPackage.LINE__SPACING:
				setSpacing((LineSpacing)newValue);
				return;
			case DeliveryPackage.LINE__WIRES:
				getWires().clear();
				getWires().addAll((Collection<? extends WireData>)newValue);
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
			case DeliveryPackage.LINE__BUS1:
				setBus1(BUS1_EDEFAULT);
				return;
			case DeliveryPackage.LINE__BUS2:
				setBus2(BUS2_EDEFAULT);
				return;
			case DeliveryPackage.LINE__LINE_CODE:
				setLineCode(LINE_CODE_EDEFAULT);
				return;
			case DeliveryPackage.LINE__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case DeliveryPackage.LINE__R1:
				setR1(R1_EDEFAULT);
				return;
			case DeliveryPackage.LINE__X1:
				setX1(X1_EDEFAULT);
				return;
			case DeliveryPackage.LINE__R0:
				setR0(R0_EDEFAULT);
				return;
			case DeliveryPackage.LINE__X0:
				setX0(X0_EDEFAULT);
				return;
			case DeliveryPackage.LINE__C1:
				setC1(C1_EDEFAULT);
				return;
			case DeliveryPackage.LINE__C0:
				setC0(C0_EDEFAULT);
				return;
			case DeliveryPackage.LINE__RMATRIX:
				setRMatrix((DoubleMatrix2D)null);
				return;
			case DeliveryPackage.LINE__XMATRIX:
				setXMatrix((DoubleMatrix2D)null);
				return;
			case DeliveryPackage.LINE__CMATRIX:
				setCMatrix((DoubleMatrix2D)null);
				return;
			case DeliveryPackage.LINE__SWITCH:
				setSwitch(SWITCH_EDEFAULT);
				return;
			case DeliveryPackage.LINE__RG:
				setRg(RG_EDEFAULT);
				return;
			case DeliveryPackage.LINE__XG:
				setXg(XG_EDEFAULT);
				return;
			case DeliveryPackage.LINE__RHO:
				setRho(RHO_EDEFAULT);
				return;
			case DeliveryPackage.LINE__GEOMETRY:
				setGeometry(GEOMETRY_EDEFAULT);
				return;
			case DeliveryPackage.LINE__UNITS:
				setUnits(UNITS_EDEFAULT);
				return;
			case DeliveryPackage.LINE__SPACING:
				setSpacing((LineSpacing)null);
				return;
			case DeliveryPackage.LINE__WIRES:
				getWires().clear();
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
			case DeliveryPackage.LINE__BUS1:
				return BUS1_EDEFAULT == null ? bus1 != null : !BUS1_EDEFAULT.equals(bus1);
			case DeliveryPackage.LINE__BUS2:
				return BUS2_EDEFAULT == null ? bus2 != null : !BUS2_EDEFAULT.equals(bus2);
			case DeliveryPackage.LINE__LINE_CODE:
				return LINE_CODE_EDEFAULT == null ? lineCode != null : !LINE_CODE_EDEFAULT.equals(lineCode);
			case DeliveryPackage.LINE__LENGTH:
				return length != LENGTH_EDEFAULT;
			case DeliveryPackage.LINE__R1:
				return r1 != R1_EDEFAULT;
			case DeliveryPackage.LINE__X1:
				return x1 != X1_EDEFAULT;
			case DeliveryPackage.LINE__R0:
				return r0 != R0_EDEFAULT;
			case DeliveryPackage.LINE__X0:
				return x0 != X0_EDEFAULT;
			case DeliveryPackage.LINE__C1:
				return c1 != C1_EDEFAULT;
			case DeliveryPackage.LINE__C0:
				return c0 != C0_EDEFAULT;
			case DeliveryPackage.LINE__RMATRIX:
				return rMatrix != null;
			case DeliveryPackage.LINE__XMATRIX:
				return xMatrix != null;
			case DeliveryPackage.LINE__CMATRIX:
				return cMatrix != null;
			case DeliveryPackage.LINE__SWITCH:
				return switch_ != SWITCH_EDEFAULT;
			case DeliveryPackage.LINE__RG:
				return rg != RG_EDEFAULT;
			case DeliveryPackage.LINE__XG:
				return xg != XG_EDEFAULT;
			case DeliveryPackage.LINE__RHO:
				return rho != RHO_EDEFAULT;
			case DeliveryPackage.LINE__GEOMETRY:
				return GEOMETRY_EDEFAULT == null ? geometry != null : !GEOMETRY_EDEFAULT.equals(geometry);
			case DeliveryPackage.LINE__UNITS:
				return units != UNITS_EDEFAULT;
			case DeliveryPackage.LINE__SPACING:
				return spacing != null;
			case DeliveryPackage.LINE__WIRES:
				return wires != null && !wires.isEmpty();
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
		result.append(", bus2: ");
		result.append(bus2);
		result.append(", lineCode: ");
		result.append(lineCode);
		result.append(", length: ");
		result.append(length);
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
		result.append(", switch: ");
		result.append(switch_);
		result.append(", rg: ");
		result.append(rg);
		result.append(", xg: ");
		result.append(xg);
		result.append(", rho: ");
		result.append(rho);
		result.append(", geometry: ");
		result.append(geometry);
		result.append(", units: ");
		result.append(units);
		result.append(')');
		return result.toString();
	}

} //LineImpl
