/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Terminal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Each electrical element in the power system has one or more terminals.
 * Each terminal has one or more conductors.  Each conductor contains a
 * disconnect switch and a TCC (fuse) curve[Fuse has been disabled and is
 * being redesigned; a Relay object can be used if needed to control the
 * switches].  The conductors are numbered [1,2,3,...].
 * 
 * If the terminal is connected to an N-phase device, the first N conductors
 * are assumed to correspond to the phases, in order.  The remaining
 * conductors may be neutrals or whatever.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.common.Terminal#getBusRef <em>Bus Ref</em>}</li>
 *   <li>{@link dss.common.Terminal#getTermNodeRef <em>Term Node Ref</em>}</li>
 *   <li>{@link dss.common.Terminal#getConductors <em>Conductors</em>}</li>
 *   <li>{@link dss.common.Terminal#isChecked <em>Checked</em>}</li>
 *   <li>{@link dss.common.Terminal#getNCond <em>NCond</em>}</li>
 *   <li>{@link dss.common.Terminal#getActiveConductor <em>Active Conductor</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.common.CommonPackage#getTerminal()
 * @model
 * @generated
 */
public interface Terminal extends EObject {
	/**
	 * Returns the value of the '<em><b>Bus Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Ref</em>' attribute.
	 * @see #setBusRef(int)
	 * @see dss.common.CommonPackage#getTerminal_BusRef()
	 * @model
	 * @generated
	 */
	int getBusRef();

	/**
	 * Sets the value of the '{@link dss.common.Terminal#getBusRef <em>Bus Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Ref</em>' attribute.
	 * @see #getBusRef()
	 * @generated
	 */
	void setBusRef(int value);

	/**
	 * Returns the value of the '<em><b>Term Node Ref</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Term Node Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Term Node Ref</em>' attribute list.
	 * @see dss.common.CommonPackage#getTerminal_TermNodeRef()
	 * @model
	 * @generated
	 */
	EList<Integer> getTermNodeRef();

	/**
	 * Returns the value of the '<em><b>Conductors</b></em>' reference list.
	 * The list contents are of type {@link dss.common.Conductor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conductors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conductors</em>' reference list.
	 * @see dss.common.CommonPackage#getTerminal_Conductors()
	 * @model
	 * @generated
	 */
	EList<Conductor> getConductors();

	/**
	 * Returns the value of the '<em><b>Checked</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Checked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Checked</em>' attribute.
	 * @see #setChecked(boolean)
	 * @see dss.common.CommonPackage#getTerminal_Checked()
	 * @model default="false"
	 * @generated
	 */
	boolean isChecked();

	/**
	 * Sets the value of the '{@link dss.common.Terminal#isChecked <em>Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checked</em>' attribute.
	 * @see #isChecked()
	 * @generated
	 */
	void setChecked(boolean value);

	/**
	 * Returns the value of the '<em><b>NCond</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NCond</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NCond</em>' attribute.
	 * @see #setNCond(int)
	 * @see dss.common.CommonPackage#getTerminal_NCond()
	 * @model
	 * @generated
	 */
	int getNCond();

	/**
	 * Sets the value of the '{@link dss.common.Terminal#getNCond <em>NCond</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NCond</em>' attribute.
	 * @see #getNCond()
	 * @generated
	 */
	void setNCond(int value);

	/**
	 * Returns the value of the '<em><b>Active Conductor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Conductor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Conductor</em>' attribute.
	 * @see #setActiveConductor(int)
	 * @see dss.common.CommonPackage#getTerminal_ActiveConductor()
	 * @model
	 * @generated
	 */
	int getActiveConductor();

	/**
	 * Sets the value of the '{@link dss.common.Terminal#getActiveConductor <em>Active Conductor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Conductor</em>' attribute.
	 * @see #getActiveConductor()
	 * @generated
	 */
	void setActiveConductor(int value);

} // Terminal
