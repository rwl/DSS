/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery.impl;

import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;

import dss.common.lengthUnit;

import dss.delivery.DeliveryPackage;
import dss.delivery.Line;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Line</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.delivery.impl.LineImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getLineCode <em>Line Code</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getLength <em>Length</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getR1 <em>R1</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getX1 <em>X1</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getR0 <em>R0</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getX0 <em>X0</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getC1 <em>C1</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getC0 <em>C0</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getRMatrix <em>RMatrix</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getXMatrix <em>XMatrix</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getCMatrix <em>CMatrix</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#isSwitch <em>Switch</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getRg <em>Rg</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getXg <em>Xg</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getRho <em>Rho</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link dss.delivery.impl.LineImpl#getUnits <em>Units</em>}</li>
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
	 * The default value of the '{@link #getRMatrix() <em>RMatrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRMatrix()
	 * @generated
	 * @ordered
	 */
	protected static final DenseDoubleMatrix2D RMATRIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRMatrix() <em>RMatrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRMatrix()
	 * @generated
	 * @ordered
	 */
	protected DenseDoubleMatrix2D rMatrix = RMATRIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getXMatrix() <em>XMatrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMatrix()
	 * @generated
	 * @ordered
	 */
	protected static final DenseDoubleMatrix2D XMATRIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXMatrix() <em>XMatrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMatrix()
	 * @generated
	 * @ordered
	 */
	protected DenseDoubleMatrix2D xMatrix = XMATRIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getCMatrix() <em>CMatrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCMatrix()
	 * @generated
	 * @ordered
	 */
	protected static final DenseDoubleMatrix2D CMATRIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCMatrix() <em>CMatrix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCMatrix()
	 * @generated
	 * @ordered
	 */
	protected DenseDoubleMatrix2D cMatrix = CMATRIX_EDEFAULT;

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
	public DenseDoubleMatrix2D getRMatrix() {
		return rMatrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRMatrix(DenseDoubleMatrix2D newRMatrix) {
		DenseDoubleMatrix2D oldRMatrix = rMatrix;
		rMatrix = newRMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__RMATRIX, oldRMatrix, rMatrix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DenseDoubleMatrix2D getXMatrix() {
		return xMatrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXMatrix(DenseDoubleMatrix2D newXMatrix) {
		DenseDoubleMatrix2D oldXMatrix = xMatrix;
		xMatrix = newXMatrix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeliveryPackage.LINE__XMATRIX, oldXMatrix, xMatrix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DenseDoubleMatrix2D getCMatrix() {
		return cMatrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCMatrix(DenseDoubleMatrix2D newCMatrix) {
		DenseDoubleMatrix2D oldCMatrix = cMatrix;
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
				return getRMatrix();
			case DeliveryPackage.LINE__XMATRIX:
				return getXMatrix();
			case DeliveryPackage.LINE__CMATRIX:
				return getCMatrix();
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
				setRMatrix((DenseDoubleMatrix2D)newValue);
				return;
			case DeliveryPackage.LINE__XMATRIX:
				setXMatrix((DenseDoubleMatrix2D)newValue);
				return;
			case DeliveryPackage.LINE__CMATRIX:
				setCMatrix((DenseDoubleMatrix2D)newValue);
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
				setRMatrix(RMATRIX_EDEFAULT);
				return;
			case DeliveryPackage.LINE__XMATRIX:
				setXMatrix(XMATRIX_EDEFAULT);
				return;
			case DeliveryPackage.LINE__CMATRIX:
				setCMatrix(CMATRIX_EDEFAULT);
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
				return RMATRIX_EDEFAULT == null ? rMatrix != null : !RMATRIX_EDEFAULT.equals(rMatrix);
			case DeliveryPackage.LINE__XMATRIX:
				return XMATRIX_EDEFAULT == null ? xMatrix != null : !XMATRIX_EDEFAULT.equals(xMatrix);
			case DeliveryPackage.LINE__CMATRIX:
				return CMATRIX_EDEFAULT == null ? cMatrix != null : !CMATRIX_EDEFAULT.equals(cMatrix);
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
		result.append(", rMatrix: ");
		result.append(rMatrix);
		result.append(", xMatrix: ");
		result.append(xMatrix);
		result.append(", cMatrix: ");
		result.append(cMatrix);
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
