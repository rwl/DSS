/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter.impl;



import electrickery.common.CircuitElement;
import electrickery.common.impl.CircuitElementImpl;
import electrickery.meter.MeterElement;
import electrickery.meter.MeterPackage;

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
 *   <li>{@link electrickery.meter.impl.MeterElementImpl#getElementName <em>Element Name</em>}</li>
 *   <li>{@link electrickery.meter.impl.MeterElementImpl#getMeteredElement <em>Metered Element</em>}</li>
 *   <li>{@link electrickery.meter.impl.MeterElementImpl#getMeteredTerminal <em>Metered Terminal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MeterElementImpl extends CircuitElementImpl implements MeterElement {
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
	 * The cached value of the '{@link #getMeteredElement() <em>Metered Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeteredElement()
	 * @generated
	 * @ordered
	 */
	protected CircuitElement meteredElement;

	/**
	 * The default value of the '{@link #getMeteredTerminal() <em>Metered Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeteredTerminal()
	 * @generated
	 * @ordered
	 */
	protected static final int METERED_TERMINAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getMeteredTerminal() <em>Metered Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeteredTerminal()
	 * @generated
	 * @ordered
	 */
	protected int meteredTerminal = METERED_TERMINAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeterElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MeterPackage.Literals.METER_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.METER_ELEMENT__ELEMENT_NAME, oldElementName, elementName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CircuitElement getMeteredElement() {
		if (meteredElement != null && meteredElement.eIsProxy()) {
			InternalEObject oldMeteredElement = (InternalEObject)meteredElement;
			meteredElement = (CircuitElement)eResolveProxy(oldMeteredElement);
			if (meteredElement != oldMeteredElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MeterPackage.METER_ELEMENT__METERED_ELEMENT, oldMeteredElement, meteredElement));
			}
		}
		return meteredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CircuitElement basicGetMeteredElement() {
		return meteredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeteredElement(CircuitElement newMeteredElement) {
		CircuitElement oldMeteredElement = meteredElement;
		meteredElement = newMeteredElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.METER_ELEMENT__METERED_ELEMENT, oldMeteredElement, meteredElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMeteredTerminal() {
		return meteredTerminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeteredTerminal(int newMeteredTerminal) {
		int oldMeteredTerminal = meteredTerminal;
		meteredTerminal = newMeteredTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.METER_ELEMENT__METERED_TERMINAL, oldMeteredTerminal, meteredTerminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MeterPackage.METER_ELEMENT__ELEMENT_NAME:
				return getElementName();
			case MeterPackage.METER_ELEMENT__METERED_ELEMENT:
				if (resolve) return getMeteredElement();
				return basicGetMeteredElement();
			case MeterPackage.METER_ELEMENT__METERED_TERMINAL:
				return getMeteredTerminal();
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
			case MeterPackage.METER_ELEMENT__ELEMENT_NAME:
				setElementName((String)newValue);
				return;
			case MeterPackage.METER_ELEMENT__METERED_ELEMENT:
				setMeteredElement((CircuitElement)newValue);
				return;
			case MeterPackage.METER_ELEMENT__METERED_TERMINAL:
				setMeteredTerminal((Integer)newValue);
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
			case MeterPackage.METER_ELEMENT__ELEMENT_NAME:
				setElementName(ELEMENT_NAME_EDEFAULT);
				return;
			case MeterPackage.METER_ELEMENT__METERED_ELEMENT:
				setMeteredElement((CircuitElement)null);
				return;
			case MeterPackage.METER_ELEMENT__METERED_TERMINAL:
				setMeteredTerminal(METERED_TERMINAL_EDEFAULT);
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
			case MeterPackage.METER_ELEMENT__ELEMENT_NAME:
				return ELEMENT_NAME_EDEFAULT == null ? elementName != null : !ELEMENT_NAME_EDEFAULT.equals(elementName);
			case MeterPackage.METER_ELEMENT__METERED_ELEMENT:
				return meteredElement != null;
			case MeterPackage.METER_ELEMENT__METERED_TERMINAL:
				return meteredTerminal != METERED_TERMINAL_EDEFAULT;
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
		result.append(", meteredTerminal: ");
		result.append(meteredTerminal);
		result.append(')');
		return result.toString();
	}

} //MeterElementImpl
