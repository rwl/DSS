/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control;

import electrickery.conversion.Generator;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generator Dispatcher</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A GenDispatcher is a control element that is connected to a terminal of
 * another circuit element and sends dispatch kW signals to a set of
 * generators it controls.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getKWLimit <em>KW Limit</em>}</li>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getKWBand <em>KW Band</em>}</li>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getKVarLimit <em>KVar Limit</em>}</li>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getGenList <em>Gen List</em>}</li>
 *   <li>{@link electrickery.control.GeneratorDispatcher#getWeights <em>Weights</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.control.ControlPackage#getGeneratorDispatcher()
 * @model
 * @generated
 */
public interface GeneratorDispatcher extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Full object name of the circuit element, typically a line or transformer, which the control is monitoring. There is no default; must be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_Element()
	 * @model
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link electrickery.control.GeneratorDispatcher#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(String value);

	/**
	 * Returns the value of the '<em><b>Terminal</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the circuit element to which the GenDispatcher control is connected. 1 or 2, typically.  Default is 1. Make sure you have the direction on the power matching the sign of kWLimit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Terminal</em>' attribute.
	 * @see #setTerminal(int)
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_Terminal()
	 * @model default="1"
	 * @generated
	 */
	int getTerminal();

	/**
	 * Sets the value of the '{@link electrickery.control.GeneratorDispatcher#getTerminal <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' attribute.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(int value);

	/**
	 * Returns the value of the '<em><b>KW Limit</b></em>' attribute.
	 * The default value is <code>"8000.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kW Limit for the monitored element. The generators are dispatched to hold the power in band.the object class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Limit</em>' attribute.
	 * @see #setKWLimit(double)
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_KWLimit()
	 * @model default="8000.0"
	 * @generated
	 */
	double getKWLimit();

	/**
	 * Sets the value of the '{@link electrickery.control.GeneratorDispatcher#getKWLimit <em>KW Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Limit</em>' attribute.
	 * @see #getKWLimit()
	 * @generated
	 */
	void setKWLimit(double value);

	/**
	 * Returns the value of the '<em><b>KW Band</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bandwidth (kW) of the dead band around the target limit. No dispatch changes are attempted if the power in the monitored terminal stays within this band.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Band</em>' attribute.
	 * @see #setKWBand(double)
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_KWBand()
	 * @model default="100.0"
	 * @generated
	 */
	double getKWBand();

	/**
	 * Sets the value of the '{@link electrickery.control.GeneratorDispatcher#getKWBand <em>KW Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Band</em>' attribute.
	 * @see #getKWBand()
	 * @generated
	 */
	void setKWBand(double value);

	/**
	 * Returns the value of the '<em><b>KVar Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Max kvar to be delivered through the element.  Uses same dead band as kW.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVar Limit</em>' attribute.
	 * @see #setKVarLimit(double)
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_KVarLimit()
	 * @model
	 * @generated
	 */
	double getKVarLimit();

	/**
	 * Sets the value of the '{@link electrickery.control.GeneratorDispatcher#getKVarLimit <em>KVar Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVar Limit</em>' attribute.
	 * @see #getKVarLimit()
	 * @generated
	 */
	void setKVarLimit(double value);

	/**
	 * Returns the value of the '<em><b>Gen List</b></em>' reference list.
	 * The list contents are of type {@link electrickery.conversion.Generator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array list of generators to be dispatched.  If not specified, all generators in the circuit are assumed dispatchable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gen List</em>' reference list.
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_GenList()
	 * @model
	 * @generated
	 */
	EList<Generator> getGenList();

	/**
	 * Returns the value of the '<em><b>Weights</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of proportional weights corresponding to each generator in the gen_list. The needed kW to get back to center band is dispatched to each generator according to these weights. Default is to set all weights to 1.0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Weights</em>' attribute list.
	 * @see electrickery.control.ControlPackage#getGeneratorDispatcher_Weights()
	 * @model
	 * @generated
	 */
	EList<Double> getWeights();

} // GeneratorDispatcher
