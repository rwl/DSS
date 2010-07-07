/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Current Curve</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Nominally, a time-current curve, but also used for volt-time curves.
 * 
 * Collections of time points.  Return values can be interpolated either
 * Log-Log as traditional TCC or as over- or under-voltage definite time.
 * 
 * A TCC_Curve object is defined similarly to Loadshape and Growthshape
 * objects in that they all are defined by curves consisting of arrays of
 * points.  Intended to model time-current characteristics for overcurrent
 * relays, TCC_Curve objects are also used for other relay types requiring
 * time curves.  Both the time array and the C array must be entered.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.general.TimeCurrentCurve#getNPts <em>NPts</em>}</li>
 *   <li>{@link electrickery.general.TimeCurrentCurve#getCArray <em>CArray</em>}</li>
 *   <li>{@link electrickery.general.TimeCurrentCurve#getTArray <em>TArray</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.general.GeneralPackage#getTimeCurrentCurve()
 * @model
 * @generated
 */
public interface TimeCurrentCurve extends EObject {
	/**
	 * Returns the value of the '<em><b>NPts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NPts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NPts</em>' attribute.
	 * @see #setNPts(int)
	 * @see electrickery.general.GeneralPackage#getTimeCurrentCurve_NPts()
	 * @model
	 * @generated
	 */
	int getNPts();

	/**
	 * Sets the value of the '{@link electrickery.general.TimeCurrentCurve#getNPts <em>NPts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPts</em>' attribute.
	 * @see #getNPts()
	 * @generated
	 */
	void setNPts(int value);

	/**
	 * Returns the value of the '<em><b>CArray</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CArray</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CArray</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getTimeCurrentCurve_CArray()
	 * @model
	 * @generated
	 */
	EList<Double> getCArray();

	/**
	 * Returns the value of the '<em><b>TArray</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TArray</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TArray</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getTimeCurrentCurve_TArray()
	 * @model
	 * @generated
	 */
	EList<Double> getTArray();

} // TimeCurrentCurve
