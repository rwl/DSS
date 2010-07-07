/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conductor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A power conductor.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.Conductor#isClosed <em>Closed</em>}</li>
 *   <li>{@link electrickery.common.Conductor#isFuseBlown <em>Fuse Blown</em>}</li>
 *   <li>{@link electrickery.common.Conductor#getAccumISqT <em>Accum ISq T</em>}</li>
 *   <li>{@link electrickery.common.Conductor#getTccName <em>Tcc Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getConductor()
 * @model abstract="true"
 * @generated
 */
public interface Conductor extends EObject {
	/**
	 * Returns the value of the '<em><b>Closed</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Closed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Closed</em>' attribute.
	 * @see #setClosed(boolean)
	 * @see electrickery.common.CommonPackage#getConductor_Closed()
	 * @model default="true"
	 * @generated
	 */
	boolean isClosed();

	/**
	 * Sets the value of the '{@link electrickery.common.Conductor#isClosed <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Closed</em>' attribute.
	 * @see #isClosed()
	 * @generated
	 */
	void setClosed(boolean value);

	/**
	 * Returns the value of the '<em><b>Fuse Blown</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fuse Blown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fuse Blown</em>' attribute.
	 * @see #setFuseBlown(boolean)
	 * @see electrickery.common.CommonPackage#getConductor_FuseBlown()
	 * @model default="false"
	 * @generated
	 */
	boolean isFuseBlown();

	/**
	 * Sets the value of the '{@link electrickery.common.Conductor#isFuseBlown <em>Fuse Blown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fuse Blown</em>' attribute.
	 * @see #isFuseBlown()
	 * @generated
	 */
	void setFuseBlown(boolean value);

	/**
	 * Returns the value of the '<em><b>Accum ISq T</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accum ISq T</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accum ISq T</em>' attribute.
	 * @see #setAccumISqT(double)
	 * @see electrickery.common.CommonPackage#getConductor_AccumISqT()
	 * @model
	 * @generated
	 */
	double getAccumISqT();

	/**
	 * Sets the value of the '{@link electrickery.common.Conductor#getAccumISqT <em>Accum ISq T</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accum ISq T</em>' attribute.
	 * @see #getAccumISqT()
	 * @generated
	 */
	void setAccumISqT(double value);

	/**
	 * Returns the value of the '<em><b>Tcc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tcc Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tcc Name</em>' attribute.
	 * @see #setTccName(String)
	 * @see electrickery.common.CommonPackage#getConductor_TccName()
	 * @model
	 * @generated
	 */
	String getTccName();

	/**
	 * Sets the value of the '{@link electrickery.common.Conductor#getTccName <em>Tcc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tcc Name</em>' attribute.
	 * @see #getTccName()
	 * @generated
	 */
	void setTccName(String value);

} // Conductor
