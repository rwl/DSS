/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter.impl;

import electrickery.meter.MeterPackage;
import electrickery.meter.Monitor;
import electrickery.meter.monitorAction;
import electrickery.meter.monitorValues;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Monitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#getAction <em>Action</em>}</li>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#isResidual <em>Residual</em>}</li>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#isVIPolar <em>VI Polar</em>}</li>
 *   <li>{@link electrickery.meter.impl.MonitorImpl#isPPolar <em>PPolar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MonitorImpl extends MeterElementImpl implements Monitor {
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
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final monitorValues MODE_EDEFAULT = monitorValues.VI;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected monitorValues mode = MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final monitorAction ACTION_EDEFAULT = monitorAction.CLEAR;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected monitorAction action = ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isResidual() <em>Residual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResidual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESIDUAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isResidual() <em>Residual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResidual()
	 * @generated
	 * @ordered
	 */
	protected boolean residual = RESIDUAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isVIPolar() <em>VI Polar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVIPolar()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VI_POLAR_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isVIPolar() <em>VI Polar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVIPolar()
	 * @generated
	 * @ordered
	 */
	protected boolean vIPolar = VI_POLAR_EDEFAULT;

	/**
	 * The default value of the '{@link #isPPolar() <em>PPolar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPPolar()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PPOLAR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPPolar() <em>PPolar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPPolar()
	 * @generated
	 * @ordered
	 */
	protected boolean pPolar = PPOLAR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MonitorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MeterPackage.Literals.MONITOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__ELEMENT, oldElement, element));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__TERMINAL, oldTerminal, terminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public monitorValues getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(monitorValues newMode) {
		monitorValues oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public monitorAction getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(monitorAction newAction) {
		monitorAction oldAction = action;
		action = newAction == null ? ACTION_EDEFAULT : newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isResidual() {
		return residual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResidual(boolean newResidual) {
		boolean oldResidual = residual;
		residual = newResidual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__RESIDUAL, oldResidual, residual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVIPolar() {
		return vIPolar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVIPolar(boolean newVIPolar) {
		boolean oldVIPolar = vIPolar;
		vIPolar = newVIPolar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__VI_POLAR, oldVIPolar, vIPolar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPPolar() {
		return pPolar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPPolar(boolean newPPolar) {
		boolean oldPPolar = pPolar;
		pPolar = newPPolar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.MONITOR__PPOLAR, oldPPolar, pPolar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MeterPackage.MONITOR__ELEMENT:
				return getElement();
			case MeterPackage.MONITOR__TERMINAL:
				return getTerminal();
			case MeterPackage.MONITOR__MODE:
				return getMode();
			case MeterPackage.MONITOR__ACTION:
				return getAction();
			case MeterPackage.MONITOR__RESIDUAL:
				return isResidual();
			case MeterPackage.MONITOR__VI_POLAR:
				return isVIPolar();
			case MeterPackage.MONITOR__PPOLAR:
				return isPPolar();
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
			case MeterPackage.MONITOR__ELEMENT:
				setElement((String)newValue);
				return;
			case MeterPackage.MONITOR__TERMINAL:
				setTerminal((Integer)newValue);
				return;
			case MeterPackage.MONITOR__MODE:
				setMode((monitorValues)newValue);
				return;
			case MeterPackage.MONITOR__ACTION:
				setAction((monitorAction)newValue);
				return;
			case MeterPackage.MONITOR__RESIDUAL:
				setResidual((Boolean)newValue);
				return;
			case MeterPackage.MONITOR__VI_POLAR:
				setVIPolar((Boolean)newValue);
				return;
			case MeterPackage.MONITOR__PPOLAR:
				setPPolar((Boolean)newValue);
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
			case MeterPackage.MONITOR__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case MeterPackage.MONITOR__TERMINAL:
				setTerminal(TERMINAL_EDEFAULT);
				return;
			case MeterPackage.MONITOR__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case MeterPackage.MONITOR__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case MeterPackage.MONITOR__RESIDUAL:
				setResidual(RESIDUAL_EDEFAULT);
				return;
			case MeterPackage.MONITOR__VI_POLAR:
				setVIPolar(VI_POLAR_EDEFAULT);
				return;
			case MeterPackage.MONITOR__PPOLAR:
				setPPolar(PPOLAR_EDEFAULT);
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
			case MeterPackage.MONITOR__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case MeterPackage.MONITOR__TERMINAL:
				return terminal != TERMINAL_EDEFAULT;
			case MeterPackage.MONITOR__MODE:
				return mode != MODE_EDEFAULT;
			case MeterPackage.MONITOR__ACTION:
				return action != ACTION_EDEFAULT;
			case MeterPackage.MONITOR__RESIDUAL:
				return residual != RESIDUAL_EDEFAULT;
			case MeterPackage.MONITOR__VI_POLAR:
				return vIPolar != VI_POLAR_EDEFAULT;
			case MeterPackage.MONITOR__PPOLAR:
				return pPolar != PPOLAR_EDEFAULT;
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
		result.append(", mode: ");
		result.append(mode);
		result.append(", action: ");
		result.append(action);
		result.append(", residual: ");
		result.append(residual);
		result.append(", vIPolar: ");
		result.append(vIPolar);
		result.append(", pPolar: ");
		result.append(pPolar);
		result.append(')');
		return result.toString();
	}

} //MonitorImpl
