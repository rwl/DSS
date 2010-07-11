/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.impl;

import electrickery.control.ControlPackage;
import electrickery.control.RegulatorControl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Regulator Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getTransformer <em>Transformer</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getWinding <em>Winding</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getVReg <em>VReg</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getBand <em>Band</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getPTRatio <em>PT Ratio</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getCTPrim <em>CT Prim</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getR <em>R</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getX <em>X</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getBus <em>Bus</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getDelay <em>Delay</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#isReversible <em>Reversible</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getRevVReg <em>Rev VReg</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getRevBand <em>Rev Band</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getRevR <em>Rev R</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getRevX <em>Rev X</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getTapDelay <em>Tap Delay</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#isDebugTrace <em>Debug Trace</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getMaxTapChange <em>Max Tap Change</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#isInverseTime <em>Inverse Time</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getTapWinding <em>Tap Winding</em>}</li>
 *   <li>{@link electrickery.control.impl.RegulatorControlImpl#getVLimit <em>VLimit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RegulatorControlImpl extends ControlElementImpl implements RegulatorControl {
	/**
	 * The default value of the '{@link #getTransformer() <em>Transformer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformer()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSFORMER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransformer() <em>Transformer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformer()
	 * @generated
	 * @ordered
	 */
	protected String transformer = TRANSFORMER_EDEFAULT;

	/**
	 * The default value of the '{@link #getWinding() <em>Winding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWinding()
	 * @generated
	 * @ordered
	 */
	protected static final int WINDING_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getWinding() <em>Winding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWinding()
	 * @generated
	 * @ordered
	 */
	protected int winding = WINDING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVReg() <em>VReg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVReg()
	 * @generated
	 * @ordered
	 */
	protected static final double VREG_EDEFAULT = 120.0;

	/**
	 * The cached value of the '{@link #getVReg() <em>VReg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVReg()
	 * @generated
	 * @ordered
	 */
	protected double vReg = VREG_EDEFAULT;

	/**
	 * The default value of the '{@link #getBand() <em>Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBand()
	 * @generated
	 * @ordered
	 */
	protected static final double BAND_EDEFAULT = 3.0;

	/**
	 * The cached value of the '{@link #getBand() <em>Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBand()
	 * @generated
	 * @ordered
	 */
	protected double band = BAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getPTRatio() <em>PT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPTRatio()
	 * @generated
	 * @ordered
	 */
	protected static final double PT_RATIO_EDEFAULT = 60.0;

	/**
	 * The cached value of the '{@link #getPTRatio() <em>PT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPTRatio()
	 * @generated
	 * @ordered
	 */
	protected double pTRatio = PT_RATIO_EDEFAULT;

	/**
	 * The default value of the '{@link #getCTPrim() <em>CT Prim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCTPrim()
	 * @generated
	 * @ordered
	 */
	protected static final double CT_PRIM_EDEFAULT = 300.0;

	/**
	 * The cached value of the '{@link #getCTPrim() <em>CT Prim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCTPrim()
	 * @generated
	 * @ordered
	 */
	protected double cTPrim = CT_PRIM_EDEFAULT;

	/**
	 * The default value of the '{@link #getR() <em>R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getR()
	 * @generated
	 * @ordered
	 */
	protected static final double R_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getR() <em>R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getR()
	 * @generated
	 * @ordered
	 */
	protected double r = R_EDEFAULT;

	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final double X_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected double x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getBus() <em>Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBus()
	 * @generated
	 * @ordered
	 */
	protected static final String BUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBus() <em>Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBus()
	 * @generated
	 * @ordered
	 */
	protected String bus = BUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final double DELAY_EDEFAULT = 15.0;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected double delay = DELAY_EDEFAULT;

	/**
	 * The default value of the '{@link #isReversible() <em>Reversible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReversible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REVERSIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReversible() <em>Reversible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReversible()
	 * @generated
	 * @ordered
	 */
	protected boolean reversible = REVERSIBLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevVReg() <em>Rev VReg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevVReg()
	 * @generated
	 * @ordered
	 */
	protected static final double REV_VREG_EDEFAULT = 120.0;

	/**
	 * The cached value of the '{@link #getRevVReg() <em>Rev VReg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevVReg()
	 * @generated
	 * @ordered
	 */
	protected double revVReg = REV_VREG_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevBand() <em>Rev Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevBand()
	 * @generated
	 * @ordered
	 */
	protected static final double REV_BAND_EDEFAULT = 3.0;

	/**
	 * The cached value of the '{@link #getRevBand() <em>Rev Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevBand()
	 * @generated
	 * @ordered
	 */
	protected double revBand = REV_BAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevR() <em>Rev R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevR()
	 * @generated
	 * @ordered
	 */
	protected static final double REV_R_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRevR() <em>Rev R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevR()
	 * @generated
	 * @ordered
	 */
	protected double revR = REV_R_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevX() <em>Rev X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevX()
	 * @generated
	 * @ordered
	 */
	protected static final double REV_X_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRevX() <em>Rev X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevX()
	 * @generated
	 * @ordered
	 */
	protected double revX = REV_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getTapDelay() <em>Tap Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTapDelay()
	 * @generated
	 * @ordered
	 */
	protected static final double TAP_DELAY_EDEFAULT = 2.0;

	/**
	 * The cached value of the '{@link #getTapDelay() <em>Tap Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTapDelay()
	 * @generated
	 * @ordered
	 */
	protected double tapDelay = TAP_DELAY_EDEFAULT;

	/**
	 * The default value of the '{@link #isDebugTrace() <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDebugTrace()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEBUG_TRACE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDebugTrace() <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDebugTrace()
	 * @generated
	 * @ordered
	 */
	protected boolean debugTrace = DEBUG_TRACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxTapChange() <em>Max Tap Change</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTapChange()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_TAP_CHANGE_EDEFAULT = 16;

	/**
	 * The cached value of the '{@link #getMaxTapChange() <em>Max Tap Change</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxTapChange()
	 * @generated
	 * @ordered
	 */
	protected int maxTapChange = MAX_TAP_CHANGE_EDEFAULT;

	/**
	 * The default value of the '{@link #isInverseTime() <em>Inverse Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInverseTime()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INVERSE_TIME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInverseTime() <em>Inverse Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInverseTime()
	 * @generated
	 * @ordered
	 */
	protected boolean inverseTime = INVERSE_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTapWinding() <em>Tap Winding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTapWinding()
	 * @generated
	 * @ordered
	 */
	protected static final int TAP_WINDING_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getTapWinding() <em>Tap Winding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTapWinding()
	 * @generated
	 * @ordered
	 */
	protected int tapWinding = TAP_WINDING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVLimit() <em>VLimit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLimit()
	 * @generated
	 * @ordered
	 */
	protected static final double VLIMIT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getVLimit() <em>VLimit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVLimit()
	 * @generated
	 * @ordered
	 */
	protected double vLimit = VLIMIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RegulatorControlImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.REGULATOR_CONTROL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransformer() {
		return transformer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformer(String newTransformer) {
		String oldTransformer = transformer;
		transformer = newTransformer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__TRANSFORMER, oldTransformer, transformer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWinding() {
		return winding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWinding(int newWinding) {
		int oldWinding = winding;
		winding = newWinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__WINDING, oldWinding, winding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVReg() {
		return vReg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVReg(double newVReg) {
		double oldVReg = vReg;
		vReg = newVReg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__VREG, oldVReg, vReg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBand() {
		return band;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBand(double newBand) {
		double oldBand = band;
		band = newBand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__BAND, oldBand, band));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPTRatio() {
		return pTRatio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPTRatio(double newPTRatio) {
		double oldPTRatio = pTRatio;
		pTRatio = newPTRatio;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__PT_RATIO, oldPTRatio, pTRatio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCTPrim() {
		return cTPrim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCTPrim(double newCTPrim) {
		double oldCTPrim = cTPrim;
		cTPrim = newCTPrim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__CT_PRIM, oldCTPrim, cTPrim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getR() {
		return r;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setR(double newR) {
		double oldR = r;
		r = newR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__R, oldR, r));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(double newX) {
		double oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBus() {
		return bus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBus(String newBus) {
		String oldBus = bus;
		bus = newBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__BUS, oldBus, bus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDelay() {
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelay(double newDelay) {
		double oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__DELAY, oldDelay, delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReversible() {
		return reversible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReversible(boolean newReversible) {
		boolean oldReversible = reversible;
		reversible = newReversible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__REVERSIBLE, oldReversible, reversible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRevVReg() {
		return revVReg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevVReg(double newRevVReg) {
		double oldRevVReg = revVReg;
		revVReg = newRevVReg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__REV_VREG, oldRevVReg, revVReg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRevBand() {
		return revBand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevBand(double newRevBand) {
		double oldRevBand = revBand;
		revBand = newRevBand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__REV_BAND, oldRevBand, revBand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRevR() {
		return revR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevR(double newRevR) {
		double oldRevR = revR;
		revR = newRevR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__REV_R, oldRevR, revR));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRevX() {
		return revX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevX(double newRevX) {
		double oldRevX = revX;
		revX = newRevX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__REV_X, oldRevX, revX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTapDelay() {
		return tapDelay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTapDelay(double newTapDelay) {
		double oldTapDelay = tapDelay;
		tapDelay = newTapDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__TAP_DELAY, oldTapDelay, tapDelay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDebugTrace() {
		return debugTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDebugTrace(boolean newDebugTrace) {
		boolean oldDebugTrace = debugTrace;
		debugTrace = newDebugTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__DEBUG_TRACE, oldDebugTrace, debugTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxTapChange() {
		return maxTapChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxTapChange(int newMaxTapChange) {
		int oldMaxTapChange = maxTapChange;
		maxTapChange = newMaxTapChange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__MAX_TAP_CHANGE, oldMaxTapChange, maxTapChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInverseTime() {
		return inverseTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInverseTime(boolean newInverseTime) {
		boolean oldInverseTime = inverseTime;
		inverseTime = newInverseTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__INVERSE_TIME, oldInverseTime, inverseTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTapWinding() {
		return tapWinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTapWinding(int newTapWinding) {
		int oldTapWinding = tapWinding;
		tapWinding = newTapWinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__TAP_WINDING, oldTapWinding, tapWinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVLimit() {
		return vLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVLimit(double newVLimit) {
		double oldVLimit = vLimit;
		vLimit = newVLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.REGULATOR_CONTROL__VLIMIT, oldVLimit, vLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.REGULATOR_CONTROL__TRANSFORMER:
				return getTransformer();
			case ControlPackage.REGULATOR_CONTROL__WINDING:
				return getWinding();
			case ControlPackage.REGULATOR_CONTROL__VREG:
				return getVReg();
			case ControlPackage.REGULATOR_CONTROL__BAND:
				return getBand();
			case ControlPackage.REGULATOR_CONTROL__PT_RATIO:
				return getPTRatio();
			case ControlPackage.REGULATOR_CONTROL__CT_PRIM:
				return getCTPrim();
			case ControlPackage.REGULATOR_CONTROL__R:
				return getR();
			case ControlPackage.REGULATOR_CONTROL__X:
				return getX();
			case ControlPackage.REGULATOR_CONTROL__BUS:
				return getBus();
			case ControlPackage.REGULATOR_CONTROL__DELAY:
				return getDelay();
			case ControlPackage.REGULATOR_CONTROL__REVERSIBLE:
				return isReversible();
			case ControlPackage.REGULATOR_CONTROL__REV_VREG:
				return getRevVReg();
			case ControlPackage.REGULATOR_CONTROL__REV_BAND:
				return getRevBand();
			case ControlPackage.REGULATOR_CONTROL__REV_R:
				return getRevR();
			case ControlPackage.REGULATOR_CONTROL__REV_X:
				return getRevX();
			case ControlPackage.REGULATOR_CONTROL__TAP_DELAY:
				return getTapDelay();
			case ControlPackage.REGULATOR_CONTROL__DEBUG_TRACE:
				return isDebugTrace();
			case ControlPackage.REGULATOR_CONTROL__MAX_TAP_CHANGE:
				return getMaxTapChange();
			case ControlPackage.REGULATOR_CONTROL__INVERSE_TIME:
				return isInverseTime();
			case ControlPackage.REGULATOR_CONTROL__TAP_WINDING:
				return getTapWinding();
			case ControlPackage.REGULATOR_CONTROL__VLIMIT:
				return getVLimit();
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
			case ControlPackage.REGULATOR_CONTROL__TRANSFORMER:
				setTransformer((String)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__WINDING:
				setWinding((Integer)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__VREG:
				setVReg((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__BAND:
				setBand((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__PT_RATIO:
				setPTRatio((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__CT_PRIM:
				setCTPrim((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__R:
				setR((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__X:
				setX((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__BUS:
				setBus((String)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__DELAY:
				setDelay((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__REVERSIBLE:
				setReversible((Boolean)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_VREG:
				setRevVReg((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_BAND:
				setRevBand((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_R:
				setRevR((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_X:
				setRevX((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__TAP_DELAY:
				setTapDelay((Double)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__DEBUG_TRACE:
				setDebugTrace((Boolean)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__MAX_TAP_CHANGE:
				setMaxTapChange((Integer)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__INVERSE_TIME:
				setInverseTime((Boolean)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__TAP_WINDING:
				setTapWinding((Integer)newValue);
				return;
			case ControlPackage.REGULATOR_CONTROL__VLIMIT:
				setVLimit((Double)newValue);
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
			case ControlPackage.REGULATOR_CONTROL__TRANSFORMER:
				setTransformer(TRANSFORMER_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__WINDING:
				setWinding(WINDING_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__VREG:
				setVReg(VREG_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__BAND:
				setBand(BAND_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__PT_RATIO:
				setPTRatio(PT_RATIO_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__CT_PRIM:
				setCTPrim(CT_PRIM_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__R:
				setR(R_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__X:
				setX(X_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__BUS:
				setBus(BUS_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__REVERSIBLE:
				setReversible(REVERSIBLE_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_VREG:
				setRevVReg(REV_VREG_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_BAND:
				setRevBand(REV_BAND_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_R:
				setRevR(REV_R_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__REV_X:
				setRevX(REV_X_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__TAP_DELAY:
				setTapDelay(TAP_DELAY_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__DEBUG_TRACE:
				setDebugTrace(DEBUG_TRACE_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__MAX_TAP_CHANGE:
				setMaxTapChange(MAX_TAP_CHANGE_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__INVERSE_TIME:
				setInverseTime(INVERSE_TIME_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__TAP_WINDING:
				setTapWinding(TAP_WINDING_EDEFAULT);
				return;
			case ControlPackage.REGULATOR_CONTROL__VLIMIT:
				setVLimit(VLIMIT_EDEFAULT);
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
			case ControlPackage.REGULATOR_CONTROL__TRANSFORMER:
				return TRANSFORMER_EDEFAULT == null ? transformer != null : !TRANSFORMER_EDEFAULT.equals(transformer);
			case ControlPackage.REGULATOR_CONTROL__WINDING:
				return winding != WINDING_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__VREG:
				return vReg != VREG_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__BAND:
				return band != BAND_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__PT_RATIO:
				return pTRatio != PT_RATIO_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__CT_PRIM:
				return cTPrim != CT_PRIM_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__R:
				return r != R_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__X:
				return x != X_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__BUS:
				return BUS_EDEFAULT == null ? bus != null : !BUS_EDEFAULT.equals(bus);
			case ControlPackage.REGULATOR_CONTROL__DELAY:
				return delay != DELAY_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__REVERSIBLE:
				return reversible != REVERSIBLE_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__REV_VREG:
				return revVReg != REV_VREG_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__REV_BAND:
				return revBand != REV_BAND_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__REV_R:
				return revR != REV_R_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__REV_X:
				return revX != REV_X_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__TAP_DELAY:
				return tapDelay != TAP_DELAY_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__DEBUG_TRACE:
				return debugTrace != DEBUG_TRACE_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__MAX_TAP_CHANGE:
				return maxTapChange != MAX_TAP_CHANGE_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__INVERSE_TIME:
				return inverseTime != INVERSE_TIME_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__TAP_WINDING:
				return tapWinding != TAP_WINDING_EDEFAULT;
			case ControlPackage.REGULATOR_CONTROL__VLIMIT:
				return vLimit != VLIMIT_EDEFAULT;
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
		result.append(" (transformer: ");
		result.append(transformer);
		result.append(", winding: ");
		result.append(winding);
		result.append(", vReg: ");
		result.append(vReg);
		result.append(", band: ");
		result.append(band);
		result.append(", pTRatio: ");
		result.append(pTRatio);
		result.append(", cTPrim: ");
		result.append(cTPrim);
		result.append(", r: ");
		result.append(r);
		result.append(", x: ");
		result.append(x);
		result.append(", bus: ");
		result.append(bus);
		result.append(", delay: ");
		result.append(delay);
		result.append(", reversible: ");
		result.append(reversible);
		result.append(", revVReg: ");
		result.append(revVReg);
		result.append(", revBand: ");
		result.append(revBand);
		result.append(", revR: ");
		result.append(revR);
		result.append(", revX: ");
		result.append(revX);
		result.append(", tapDelay: ");
		result.append(tapDelay);
		result.append(", debugTrace: ");
		result.append(debugTrace);
		result.append(", maxTapChange: ");
		result.append(maxTapChange);
		result.append(", inverseTime: ");
		result.append(inverseTime);
		result.append(", tapWinding: ");
		result.append(tapWinding);
		result.append(", vLimit: ");
		result.append(vLimit);
		result.append(')');
		return result.toString();
	}

} //RegulatorControlImpl
