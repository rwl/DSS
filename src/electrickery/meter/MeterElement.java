/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter;

import electrickery.common.CircuitElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base for all meters.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.meter.MeterElement#getElementName <em>Element Name</em>}</li>
 *   <li>{@link electrickery.meter.MeterElement#getMeteredElement <em>Metered Element</em>}</li>
 *   <li>{@link electrickery.meter.MeterElement#getMeteredTerminal <em>Metered Terminal</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.meter.MeterPackage#getMeterElement()
 * @model abstract="true"
 * @generated
 */
public interface MeterElement extends CircuitElement {
	/**
	 * Returns the value of the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Name</em>' attribute.
	 * @see #setElementName(String)
	 * @see electrickery.meter.MeterPackage#getMeterElement_ElementName()
	 * @model
	 * @generated
	 */
	String getElementName();

	/**
	 * Sets the value of the '{@link electrickery.meter.MeterElement#getElementName <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Name</em>' attribute.
	 * @see #getElementName()
	 * @generated
	 */
	void setElementName(String value);

	/**
	 * Returns the value of the '<em><b>Metered Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metered Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metered Element</em>' reference.
	 * @see #setMeteredElement(CircuitElement)
	 * @see electrickery.meter.MeterPackage#getMeterElement_MeteredElement()
	 * @model
	 * @generated
	 */
	CircuitElement getMeteredElement();

	/**
	 * Sets the value of the '{@link electrickery.meter.MeterElement#getMeteredElement <em>Metered Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metered Element</em>' reference.
	 * @see #getMeteredElement()
	 * @generated
	 */
	void setMeteredElement(CircuitElement value);

	/**
	 * Returns the value of the '<em><b>Metered Terminal</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metered Terminal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metered Terminal</em>' attribute.
	 * @see #setMeteredTerminal(int)
	 * @see electrickery.meter.MeterPackage#getMeterElement_MeteredTerminal()
	 * @model default="1"
	 * @generated
	 */
	int getMeteredTerminal();

	/**
	 * Sets the value of the '{@link electrickery.meter.MeterElement#getMeteredTerminal <em>Metered Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metered Terminal</em>' attribute.
	 * @see #getMeteredTerminal()
	 * @generated
	 */
	void setMeteredTerminal(int value);

} // MeterElement
