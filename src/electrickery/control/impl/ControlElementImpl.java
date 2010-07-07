/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.impl;



import electrickery.common.Bus;
import electrickery.common.impl.CircuitElementImpl;
import electrickery.control.ControlElement;
import electrickery.control.ControlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getElementName <em>Element Name</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getElementTerminal <em>Element Terminal</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getControlledBusName <em>Controlled Bus Name</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getControlledBus <em>Controlled Bus</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getMonitoredVariable <em>Monitored Variable</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getMonitoredVarIndex <em>Monitored Var Index</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getTimeDelay <em>Time Delay</em>}</li>
 *   <li>{@link electrickery.control.impl.ControlElementImpl#getDblTraceParam <em>Dbl Trace Param</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ControlElementImpl extends CircuitElementImpl implements ControlElement {
	/**
	 * The default value of the '{@link #getElementName() <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementName()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementName() <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementName()
	 * @generated
	 * @ordered
	 */
	protected String elementName = ELEMENT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementTerminal() <em>Element Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementTerminal()
	 * @generated
	 * @ordered
	 */
	protected static final int ELEMENT_TERMINAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getElementTerminal() <em>Element Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementTerminal()
	 * @generated
	 * @ordered
	 */
	protected int elementTerminal = ELEMENT_TERMINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getControlledBusName() <em>Controlled Bus Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlledBusName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTROLLED_BUS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getControlledBusName() <em>Controlled Bus Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlledBusName()
	 * @generated
	 * @ordered
	 */
	protected String controlledBusName = CONTROLLED_BUS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getControlledBus() <em>Controlled Bus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlledBus()
	 * @generated
	 * @ordered
	 */
	protected Bus controlledBus;

	/**
	 * The default value of the '{@link #getMonitoredVariable() <em>Monitored Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String MONITORED_VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMonitoredVariable() <em>Monitored Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredVariable()
	 * @generated
	 * @ordered
	 */
	protected String monitoredVariable = MONITORED_VARIABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMonitoredVarIndex() <em>Monitored Var Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredVarIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int MONITORED_VAR_INDEX_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getMonitoredVarIndex() <em>Monitored Var Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredVarIndex()
	 * @generated
	 * @ordered
	 */
	protected int monitoredVarIndex = MONITORED_VAR_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeDelay() <em>Time Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeDelay()
	 * @generated
	 * @ordered
	 */
	protected static final double TIME_DELAY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTimeDelay() <em>Time Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeDelay()
	 * @generated
	 * @ordered
	 */
	protected double timeDelay = TIME_DELAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getDblTraceParam() <em>Dbl Trace Param</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDblTraceParam()
	 * @generated
	 * @ordered
	 */
	protected static final double DBL_TRACE_PARAM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDblTraceParam() <em>Dbl Trace Param</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDblTraceParam()
	 * @generated
	 * @ordered
	 */
	protected double dblTraceParam = DBL_TRACE_PARAM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.CONTROL_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementName(String newElementName) {
		String oldElementName = elementName;
		elementName = newElementName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__ELEMENT_NAME, oldElementName, elementName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getElementTerminal() {
		return elementTerminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementTerminal(int newElementTerminal) {
		int oldElementTerminal = elementTerminal;
		elementTerminal = newElementTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__ELEMENT_TERMINAL, oldElementTerminal, elementTerminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getControlledBusName() {
		return controlledBusName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlledBusName(String newControlledBusName) {
		String oldControlledBusName = controlledBusName;
		controlledBusName = newControlledBusName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS_NAME, oldControlledBusName, controlledBusName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bus getControlledBus() {
		if (controlledBus != null && controlledBus.eIsProxy()) {
			InternalEObject oldControlledBus = (InternalEObject)controlledBus;
			controlledBus = (Bus)eResolveProxy(oldControlledBus);
			if (controlledBus != oldControlledBus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS, oldControlledBus, controlledBus));
			}
		}
		return controlledBus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bus basicGetControlledBus() {
		return controlledBus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlledBus(Bus newControlledBus) {
		Bus oldControlledBus = controlledBus;
		controlledBus = newControlledBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS, oldControlledBus, controlledBus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMonitoredVariable() {
		return monitoredVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoredVariable(String newMonitoredVariable) {
		String oldMonitoredVariable = monitoredVariable;
		monitoredVariable = newMonitoredVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__MONITORED_VARIABLE, oldMonitoredVariable, monitoredVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMonitoredVarIndex() {
		return monitoredVarIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoredVarIndex(int newMonitoredVarIndex) {
		int oldMonitoredVarIndex = monitoredVarIndex;
		monitoredVarIndex = newMonitoredVarIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__MONITORED_VAR_INDEX, oldMonitoredVarIndex, monitoredVarIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTimeDelay() {
		return timeDelay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeDelay(double newTimeDelay) {
		double oldTimeDelay = timeDelay;
		timeDelay = newTimeDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__TIME_DELAY, oldTimeDelay, timeDelay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDblTraceParam() {
		return dblTraceParam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDblTraceParam(double newDblTraceParam) {
		double oldDblTraceParam = dblTraceParam;
		dblTraceParam = newDblTraceParam;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.CONTROL_ELEMENT__DBL_TRACE_PARAM, oldDblTraceParam, dblTraceParam));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_NAME:
				return getElementName();
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_TERMINAL:
				return getElementTerminal();
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS_NAME:
				return getControlledBusName();
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS:
				if (resolve) return getControlledBus();
				return basicGetControlledBus();
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VARIABLE:
				return getMonitoredVariable();
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VAR_INDEX:
				return getMonitoredVarIndex();
			case ControlPackage.CONTROL_ELEMENT__TIME_DELAY:
				return getTimeDelay();
			case ControlPackage.CONTROL_ELEMENT__DBL_TRACE_PARAM:
				return getDblTraceParam();
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
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_NAME:
				setElementName((String)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_TERMINAL:
				setElementTerminal((Integer)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS_NAME:
				setControlledBusName((String)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS:
				setControlledBus((Bus)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VARIABLE:
				setMonitoredVariable((String)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VAR_INDEX:
				setMonitoredVarIndex((Integer)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__TIME_DELAY:
				setTimeDelay((Double)newValue);
				return;
			case ControlPackage.CONTROL_ELEMENT__DBL_TRACE_PARAM:
				setDblTraceParam((Double)newValue);
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
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_NAME:
				setElementName(ELEMENT_NAME_EDEFAULT);
				return;
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_TERMINAL:
				setElementTerminal(ELEMENT_TERMINAL_EDEFAULT);
				return;
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS_NAME:
				setControlledBusName(CONTROLLED_BUS_NAME_EDEFAULT);
				return;
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS:
				setControlledBus((Bus)null);
				return;
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VARIABLE:
				setMonitoredVariable(MONITORED_VARIABLE_EDEFAULT);
				return;
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VAR_INDEX:
				setMonitoredVarIndex(MONITORED_VAR_INDEX_EDEFAULT);
				return;
			case ControlPackage.CONTROL_ELEMENT__TIME_DELAY:
				setTimeDelay(TIME_DELAY_EDEFAULT);
				return;
			case ControlPackage.CONTROL_ELEMENT__DBL_TRACE_PARAM:
				setDblTraceParam(DBL_TRACE_PARAM_EDEFAULT);
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
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_NAME:
				return ELEMENT_NAME_EDEFAULT == null ? elementName != null : !ELEMENT_NAME_EDEFAULT.equals(elementName);
			case ControlPackage.CONTROL_ELEMENT__ELEMENT_TERMINAL:
				return elementTerminal != ELEMENT_TERMINAL_EDEFAULT;
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS_NAME:
				return CONTROLLED_BUS_NAME_EDEFAULT == null ? controlledBusName != null : !CONTROLLED_BUS_NAME_EDEFAULT.equals(controlledBusName);
			case ControlPackage.CONTROL_ELEMENT__CONTROLLED_BUS:
				return controlledBus != null;
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VARIABLE:
				return MONITORED_VARIABLE_EDEFAULT == null ? monitoredVariable != null : !MONITORED_VARIABLE_EDEFAULT.equals(monitoredVariable);
			case ControlPackage.CONTROL_ELEMENT__MONITORED_VAR_INDEX:
				return monitoredVarIndex != MONITORED_VAR_INDEX_EDEFAULT;
			case ControlPackage.CONTROL_ELEMENT__TIME_DELAY:
				return timeDelay != TIME_DELAY_EDEFAULT;
			case ControlPackage.CONTROL_ELEMENT__DBL_TRACE_PARAM:
				return dblTraceParam != DBL_TRACE_PARAM_EDEFAULT;
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
		result.append(" (elementName: ");
		result.append(elementName);
		result.append(", elementTerminal: ");
		result.append(elementTerminal);
		result.append(", controlledBusName: ");
		result.append(controlledBusName);
		result.append(", monitoredVariable: ");
		result.append(monitoredVariable);
		result.append(", monitoredVarIndex: ");
		result.append(monitoredVarIndex);
		result.append(", timeDelay: ");
		result.append(timeDelay);
		result.append(", dblTraceParam: ");
		result.append(dblTraceParam);
		result.append(')');
		return result.toString();
	}

} //ControlElementImpl
