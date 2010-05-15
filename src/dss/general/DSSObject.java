/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DSS Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.general.DSSObject#getDSSObjType <em>DSS Obj Type</em>}</li>
 *   <li>{@link dss.general.DSSObject#getDSSClassName <em>DSS Class Name</em>}</li>
 *   <li>{@link dss.general.DSSObject#getParentClass <em>Parent Class</em>}</li>
 *   <li>{@link dss.general.DSSObject#getClassIndex <em>Class Index</em>}</li>
 *   <li>{@link dss.general.DSSObject#isDirty <em>Dirty</em>}</li>
 *   <li>{@link dss.general.DSSObject#isFlag <em>Flag</em>}</li>
 *   <li>{@link dss.general.DSSObject#getPropSequence <em>Prop Sequence</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.general.GeneralPackage#getDSSObject()
 * @model
 * @generated
 */
public interface DSSObject extends EObject {
	/**
	 * Returns the value of the '<em><b>DSS Obj Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * PD, PC, Monitor, CondCode, etc.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>DSS Obj Type</em>' attribute.
	 * @see #setDSSObjType(int)
	 * @see dss.general.GeneralPackage#getDSSObject_DSSObjType()
	 * @model
	 * @generated
	 */
	int getDSSObjType();

	/**
	 * Sets the value of the '{@link dss.general.DSSObject#getDSSObjType <em>DSS Obj Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DSS Obj Type</em>' attribute.
	 * @see #getDSSObjType()
	 * @generated
	 */
	void setDSSObjType(int value);

	/**
	 * Returns the value of the '<em><b>DSS Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>DSS Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>DSS Class Name</em>' attribute.
	 * @see #setDSSClassName(String)
	 * @see dss.general.GeneralPackage#getDSSObject_DSSClassName()
	 * @model
	 * @generated
	 */
	String getDSSClassName();

	/**
	 * Sets the value of the '{@link dss.general.DSSObject#getDSSClassName <em>DSS Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DSS Class Name</em>' attribute.
	 * @see #getDSSClassName()
	 * @generated
	 */
	void setDSSClassName(String value);

	/**
	 * Returns the value of the '<em><b>Parent Class</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Class</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Class</em>' reference list.
	 * @see dss.general.GeneralPackage#getDSSObject_ParentClass()
	 * @model
	 * @generated
	 */
	EList<EClass> getParentClass();

	/**
	 * Returns the value of the '<em><b>Class Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Index into the class collection list.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Index</em>' attribute.
	 * @see #setClassIndex(int)
	 * @see dss.general.GeneralPackage#getDSSObject_ClassIndex()
	 * @model
	 * @generated
	 */
	int getClassIndex();

	/**
	 * Sets the value of the '{@link dss.general.DSSObject#getClassIndex <em>Class Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Index</em>' attribute.
	 * @see #getClassIndex()
	 * @generated
	 */
	void setClassIndex(int value);

	/**
	 * Returns the value of the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dirty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dirty</em>' attribute.
	 * @see #setDirty(boolean)
	 * @see dss.general.GeneralPackage#getDSSObject_Dirty()
	 * @model
	 * @generated
	 */
	boolean isDirty();

	/**
	 * Sets the value of the '{@link dss.general.DSSObject#isDirty <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dirty</em>' attribute.
	 * @see #isDirty()
	 * @generated
	 */
	void setDirty(boolean value);

	/**
	 * Returns the value of the '<em><b>Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * General purpose Flag for each object - don't assume inited.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Flag</em>' attribute.
	 * @see #setFlag(boolean)
	 * @see dss.general.GeneralPackage#getDSSObject_Flag()
	 * @model
	 * @generated
	 */
	boolean isFlag();

	/**
	 * Sets the value of the '{@link dss.general.DSSObject#isFlag <em>Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flag</em>' attribute.
	 * @see #isFlag()
	 * @generated
	 */
	void setFlag(boolean value);

	/**
	 * Returns the value of the '<em><b>Prop Sequence</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prop Sequence</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prop Sequence</em>' attribute list.
	 * @see dss.general.GeneralPackage#getDSSObject_PropSequence()
	 * @model
	 * @generated
	 */
	EList<Double> getPropSequence();

} // DSSObject
