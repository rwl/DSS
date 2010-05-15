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
 * A representation of the model object '<em><b>Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base Class for all DSS collection classes.  Keeps track of objects of each class, dispatches edits, etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.common.Collection#getNProperties <em>NProperties</em>}</li>
 *   <li>{@link dss.common.Collection#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link dss.common.Collection#getPropertyHelp <em>Property Help</em>}</li>
 *   <li>{@link dss.common.Collection#getPropertyIdxMap <em>Property Idx Map</em>}</li>
 *   <li>{@link dss.common.Collection#getElementList <em>Element List</em>}</li>
 *   <li>{@link dss.common.Collection#isSaved <em>Saved</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.common.CommonPackage#getCollection()
 * @model abstract="true"
 * @generated
 */
public interface Collection extends EObject {
	/**
	 * Returns the value of the '<em><b>NProperties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NProperties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NProperties</em>' attribute.
	 * @see #setNProperties(int)
	 * @see dss.common.CommonPackage#getCollection_NProperties()
	 * @model
	 * @generated
	 */
	int getNProperties();

	/**
	 * Sets the value of the '{@link dss.common.Collection#getNProperties <em>NProperties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NProperties</em>' attribute.
	 * @see #getNProperties()
	 * @generated
	 */
	void setNProperties(int value);

	/**
	 * Returns the value of the '<em><b>Property Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Name</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Name</em>' attribute list.
	 * @see dss.common.CommonPackage#getCollection_PropertyName()
	 * @model
	 * @generated
	 */
	EList<String> getPropertyName();

	/**
	 * Returns the value of the '<em><b>Property Help</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Help</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Help</em>' attribute list.
	 * @see dss.common.CommonPackage#getCollection_PropertyHelp()
	 * @model
	 * @generated
	 */
	EList<String> getPropertyHelp();

	/**
	 * Returns the value of the '<em><b>Property Idx Map</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maps property to internal command number.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property Idx Map</em>' attribute list.
	 * @see dss.common.CommonPackage#getCollection_PropertyIdxMap()
	 * @model transient="true"
	 * @generated
	 */
	EList<String> getPropertyIdxMap();

	/**
	 * Returns the value of the '<em><b>Element List</b></em>' reference list.
	 * The list contents are of type {@link dss.common.CircuitElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element List</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element List</em>' reference list.
	 * @see dss.common.CommonPackage#getCollection_ElementList()
	 * @model
	 * @generated
	 */
	EList<CircuitElement> getElementList();

	/**
	 * Returns the value of the '<em><b>Saved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Saved</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Saved</em>' attribute.
	 * @see #setSaved(boolean)
	 * @see dss.common.CommonPackage#getCollection_Saved()
	 * @model
	 * @generated
	 */
	boolean isSaved();

	/**
	 * Sets the value of the '{@link dss.common.Collection#isSaved <em>Saved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Saved</em>' attribute.
	 * @see #isSaved()
	 * @generated
	 */
	void setSaved(boolean value);

} // Collection
