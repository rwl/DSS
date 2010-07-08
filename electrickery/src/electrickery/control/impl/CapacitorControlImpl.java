/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.impl;

import electrickery.control.CapacitorControl;
import electrickery.control.ControlPackage;
import electrickery.control.controlType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capacitor Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getCapacitor <em>Capacitor</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getType <em>Type</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getPTRatio <em>PT Ratio</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getCTRatio <em>CT Ratio</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getOnSetting <em>On Setting</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getOffSetting <em>Off Setting</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getDelay <em>Delay</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#isVoltOverride <em>Volt Override</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getVMax <em>VMax</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getVMin <em>VMin</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getDelayOff <em>Delay Off</em>}</li>
 *   <li>{@link electrickery.control.impl.CapacitorControlImpl#getDeadTime <em>Dead Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CapacitorControlImpl extends ControlElementImpl implements CapacitorControl {
	/**
	 * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected String element = ELEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected static final int TERMINAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected int terminal = TERMINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCapacitor() <em>Capacitor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacitor()
	 * @generated
	 * @ordered
	 */
	protected static final String CAPACITOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCapacitor() <em>Capacitor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapacitor()
	 * @generated
	 * @ordered
	 */
	protected String capacitor = CAPACITOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final controlType TYPE_EDEFAULT = controlType.CURRENT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected controlType type = TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getCTRatio() <em>CT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCTRatio()
	 * @generated
	 * @ordered
	 */
	protected static final double CT_RATIO_EDEFAULT = 60.0;

	/**
	 * The cached value of the '{@link #getCTRatio() <em>CT Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCTRatio()
	 * @generated
	 * @ordered
	 */
	protected double cTRatio = CT_RATIO_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnSetting() <em>On Setting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnSetting()
	 * @generated
	 * @ordered
	 */
	protected static final double ON_SETTING_EDEFAULT = 300.0;

	/**
	 * The cached value of the '{@link #getOnSetting() <em>On Setting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnSetting()
	 * @generated
	 * @ordered
	 */
	protected double onSetting = ON_SETTING_EDEFAULT;

	/**
	 * The default value of the '{@link #getOffSetting() <em>Off Setting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffSetting()
	 * @generated
	 * @ordered
	 */
	protected static final double OFF_SETTING_EDEFAULT = 200.0;

	/**
	 * The cached value of the '{@link #getOffSetting() <em>Off Setting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffSetting()
	 * @generated
	 * @ordered
	 */
	protected double offSetting = OFF_SETTING_EDEFAULT;

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
	 * The default value of the '{@link #isVoltOverride() <em>Volt Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVoltOverride()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VOLT_OVERRIDE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVoltOverride() <em>Volt Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVoltOverride()
	 * @generated
	 * @ordered
	 */
	protected boolean voltOverride = VOLT_OVERRIDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVMax() <em>VMax</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMax()
	 * @generated
	 * @ordered
	 */
	protected static final double VMAX_EDEFAULT = 126.0;

	/**
	 * The cached value of the '{@link #getVMax() <em>VMax</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMax()
	 * @generated
	 * @ordered
	 */
	protected double vMax = VMAX_EDEFAULT;

	/**
	 * The default value of the '{@link #getVMin() <em>VMin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMin()
	 * @generated
	 * @ordered
	 */
	protected static final double VMIN_EDEFAULT = 115.0;

	/**
	 * The cached value of the '{@link #getVMin() <em>VMin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMin()
	 * @generated
	 * @ordered
	 */
	protected double vMin = VMIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getDelayOff() <em>Delay Off</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelayOff()
	 * @generated
	 * @ordered
	 */
	protected static final double DELAY_OFF_EDEFAULT = 15.0;

	/**
	 * The cached value of the '{@link #getDelayOff() <em>Delay Off</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelayOff()
	 * @generated
	 * @ordered
	 */
	protected double delayOff = DELAY_OFF_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeadTime() <em>Dead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadTime()
	 * @generated
	 * @ordered
	 */
	protected static final double DEAD_TIME_EDEFAULT = 300.0;

	/**
	 * The cached value of the '{@link #getDeadTime() <em>Dead Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadTime()
	 * @generated
	 * @ordered
	 */
	protected double deadTime = DEAD_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CapacitorControlImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.CAPACITOR_CONTROL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(String newElement) {
		String oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTerminal() {
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminal(int newTerminal) {
		int oldTerminal = terminal;
		terminal = newTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__TERMINAL, oldTerminal, terminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCapacitor() {
		return capacitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCapacitor(String newCapacitor) {
		String oldCapacitor = capacitor;
		capacitor = newCapacitor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__CAPACITOR, oldCapacitor, capacitor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public controlType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(controlType newType) {
		controlType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__PT_RATIO, oldPTRatio, pTRatio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCTRatio() {
		return cTRatio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCTRatio(double newCTRatio) {
		double oldCTRatio = cTRatio;
		cTRatio = newCTRatio;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__CT_RATIO, oldCTRatio, cTRatio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOnSetting() {
		return onSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnSetting(double newOnSetting) {
		double oldOnSetting = onSetting;
		onSetting = newOnSetting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__ON_SETTING, oldOnSetting, onSetting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOffSetting() {
		return offSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffSetting(double newOffSetting) {
		double oldOffSetting = offSetting;
		offSetting = newOffSetting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__OFF_SETTING, oldOffSetting, offSetting));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__DELAY, oldDelay, delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVoltOverride() {
		return voltOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVoltOverride(boolean newVoltOverride) {
		boolean oldVoltOverride = voltOverride;
		voltOverride = newVoltOverride;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__VOLT_OVERRIDE, oldVoltOverride, voltOverride));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVMax() {
		return vMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVMax(double newVMax) {
		double oldVMax = vMax;
		vMax = newVMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__VMAX, oldVMax, vMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVMin() {
		return vMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVMin(double newVMin) {
		double oldVMin = vMin;
		vMin = newVMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__VMIN, oldVMin, vMin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDelayOff() {
		return delayOff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelayOff(double newDelayOff) {
		double oldDelayOff = delayOff;
		delayOff = newDelayOff;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__DELAY_OFF, oldDelayOff, delayOff));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDeadTime() {
		return deadTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeadTime(double newDeadTime) {
		double oldDeadTime = deadTime;
		deadTime = newDeadTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CAPACITOR_CONTROL__DEAD_TIME, oldDeadTime, deadTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.CAPACITOR_CONTROL__ELEMENT:
				return getElement();
			case ControlPackage.CAPACITOR_CONTROL__TERMINAL:
				return getTerminal();
			case ControlPackage.CAPACITOR_CONTROL__CAPACITOR:
				return getCapacitor();
			case ControlPackage.CAPACITOR_CONTROL__TYPE:
				return getType();
			case ControlPackage.CAPACITOR_CONTROL__PT_RATIO:
				return getPTRatio();
			case ControlPackage.CAPACITOR_CONTROL__CT_RATIO:
				return getCTRatio();
			case ControlPackage.CAPACITOR_CONTROL__ON_SETTING:
				return getOnSetting();
			case ControlPackage.CAPACITOR_CONTROL__OFF_SETTING:
				return getOffSetting();
			case ControlPackage.CAPACITOR_CONTROL__DELAY:
				return getDelay();
			case ControlPackage.CAPACITOR_CONTROL__VOLT_OVERRIDE:
				return isVoltOverride();
			case ControlPackage.CAPACITOR_CONTROL__VMAX:
				return getVMax();
			case ControlPackage.CAPACITOR_CONTROL__VMIN:
				return getVMin();
			case ControlPackage.CAPACITOR_CONTROL__DELAY_OFF:
				return getDelayOff();
			case ControlPackage.CAPACITOR_CONTROL__DEAD_TIME:
				return getDeadTime();
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
			case ControlPackage.CAPACITOR_CONTROL__ELEMENT:
				setElement((String)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__TERMINAL:
				setTerminal((Integer)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__CAPACITOR:
				setCapacitor((String)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__TYPE:
				setType((controlType)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__PT_RATIO:
				setPTRatio((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__CT_RATIO:
				setCTRatio((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__ON_SETTING:
				setOnSetting((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__OFF_SETTING:
				setOffSetting((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__DELAY:
				setDelay((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__VOLT_OVERRIDE:
				setVoltOverride((Boolean)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__VMAX:
				setVMax((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__VMIN:
				setVMin((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__DELAY_OFF:
				setDelayOff((Double)newValue);
				return;
			case ControlPackage.CAPACITOR_CONTROL__DEAD_TIME:
				setDeadTime((Double)newValue);
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
			case ControlPackage.CAPACITOR_CONTROL__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__TERMINAL:
				setTerminal(TERMINAL_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__CAPACITOR:
				setCapacitor(CAPACITOR_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__PT_RATIO:
				setPTRatio(PT_RATIO_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__CT_RATIO:
				setCTRatio(CT_RATIO_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__ON_SETTING:
				setOnSetting(ON_SETTING_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__OFF_SETTING:
				setOffSetting(OFF_SETTING_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__VOLT_OVERRIDE:
				setVoltOverride(VOLT_OVERRIDE_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__VMAX:
				setVMax(VMAX_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__VMIN:
				setVMin(VMIN_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__DELAY_OFF:
				setDelayOff(DELAY_OFF_EDEFAULT);
				return;
			case ControlPackage.CAPACITOR_CONTROL__DEAD_TIME:
				setDeadTime(DEAD_TIME_EDEFAULT);
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
			case ControlPackage.CAPACITOR_CONTROL__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case ControlPackage.CAPACITOR_CONTROL__TERMINAL:
				return terminal != TERMINAL_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__CAPACITOR:
				return CAPACITOR_EDEFAULT == null ? capacitor != null : !CAPACITOR_EDEFAULT.equals(capacitor);
			case ControlPackage.CAPACITOR_CONTROL__TYPE:
				return type != TYPE_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__PT_RATIO:
				return pTRatio != PT_RATIO_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__CT_RATIO:
				return cTRatio != CT_RATIO_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__ON_SETTING:
				return onSetting != ON_SETTING_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__OFF_SETTING:
				return offSetting != OFF_SETTING_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__DELAY:
				return delay != DELAY_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__VOLT_OVERRIDE:
				return voltOverride != VOLT_OVERRIDE_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__VMAX:
				return vMax != VMAX_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__VMIN:
				return vMin != VMIN_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__DELAY_OFF:
				return delayOff != DELAY_OFF_EDEFAULT;
			case ControlPackage.CAPACITOR_CONTROL__DEAD_TIME:
				return deadTime != DEAD_TIME_EDEFAULT;
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
		result.append(" (element: ");
		result.append(element);
		result.append(", terminal: ");
		result.append(terminal);
		result.append(", capacitor: ");
		result.append(capacitor);
		result.append(", type: ");
		result.append(type);
		result.append(", pTRatio: ");
		result.append(pTRatio);
		result.append(", cTRatio: ");
		result.append(cTRatio);
		result.append(", onSetting: ");
		result.append(onSetting);
		result.append(", offSetting: ");
		result.append(offSetting);
		result.append(", delay: ");
		result.append(delay);
		result.append(", voltOverride: ");
		result.append(voltOverride);
		result.append(", vMax: ");
		result.append(vMax);
		result.append(", vMin: ");
		result.append(vMin);
		result.append(", delayOff: ");
		result.append(delayOff);
		result.append(", deadTime: ");
		result.append(deadTime);
		result.append(')');
		return result.toString();
	}

} //CapacitorControlImpl
