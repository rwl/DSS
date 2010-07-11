/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general;

import electrickery.common.lengthUnit;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Line Spacing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.general.LineSpacing#getNConds <em>NConds</em>}</li>
 *   <li>{@link electrickery.general.LineSpacing#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.general.LineSpacing#getX <em>X</em>}</li>
 *   <li>{@link electrickery.general.LineSpacing#getH <em>H</em>}</li>
 *   <li>{@link electrickery.general.LineSpacing#getUnits <em>Units</em>}</li>
 *   <li>{@link electrickery.general.LineSpacing#getLike <em>Like</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.general.GeneralPackage#getLineSpacing()
 * @model
 * @generated
 */
public interface LineSpacing extends EObject {
	/**
	 * Returns the value of the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of wires in this geometry. Default is 3.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NConds</em>' attribute.
	 * @see #setNConds(int)
	 * @see electrickery.general.GeneralPackage#getLineSpacing_NConds()
	 * @model
	 * @generated
	 */
	int getNConds();

	/**
	 * Sets the value of the '{@link electrickery.general.LineSpacing#getNConds <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NConds</em>' attribute.
	 * @see #getNConds()
	 * @generated
	 */
	void setNConds(int value);

	/**
	 * Returns the value of the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of retained phase conductors. If less than the number of wires, list the retained phase coordinates first.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NPhases</em>' attribute.
	 * @see #setNPhases(int)
	 * @see electrickery.general.GeneralPackage#getLineSpacing_NPhases()
	 * @model
	 * @generated
	 */
	int getNPhases();

	/**
	 * Sets the value of the '{@link electrickery.general.LineSpacing#getNPhases <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPhases</em>' attribute.
	 * @see #getNPhases()
	 * @generated
	 */
	void setNPhases(int value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of wire X coordinates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getLineSpacing_X()
	 * @model
	 * @generated
	 */
	EList<Double> getX();

	/**
	 * Returns the value of the '<em><b>H</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of wire Heights.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>H</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getLineSpacing_H()
	 * @model
	 * @generated
	 */
	EList<Double> getH();

	/**
	 * Returns the value of the '<em><b>Units</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.lengthUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Units for x and h.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #setUnits(lengthUnit)
	 * @see electrickery.general.GeneralPackage#getLineSpacing_Units()
	 * @model
	 * @generated
	 */
	lengthUnit getUnits();

	/**
	 * Sets the value of the '{@link electrickery.general.LineSpacing#getUnits <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Units</em>' attribute.
	 * @see electrickery.common.lengthUnit
	 * @see #getUnits()
	 * @generated
	 */
	void setUnits(lengthUnit value);

	/**
	 * Returns the value of the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Like</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Like</em>' reference.
	 * @see #setLike(LineSpacing)
	 * @see electrickery.general.GeneralPackage#getLineSpacing_Like()
	 * @model
	 * @generated
	 */
	LineSpacing getLike();

	/**
	 * Sets the value of the '{@link electrickery.general.LineSpacing#getLike <em>Like</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Like</em>' reference.
	 * @see #getLike()
	 * @generated
	 */
	void setLike(LineSpacing value);

} // LineSpacing
