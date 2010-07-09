/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common;

import electrickery.executive.Executive;

import electrickery.general.LineGeometry;
import electrickery.general.WireData;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Electrickery</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.Electrickery#getWireData <em>Wire Data</em>}</li>
 *   <li>{@link electrickery.common.Electrickery#getLineGeometries <em>Line Geometries</em>}</li>
 *   <li>{@link electrickery.common.Electrickery#getExecutives <em>Executives</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getElectrickery()
 * @model
 * @generated
 */
public interface Electrickery extends EObject {
	/**
	 * Returns the value of the '<em><b>Wire Data</b></em>' reference list.
	 * The list contents are of type {@link electrickery.general.WireData}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wire Data</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wire Data</em>' reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_WireData()
	 * @model
	 * @generated
	 */
	EList<WireData> getWireData();

	/**
	 * Returns the value of the '<em><b>Line Geometries</b></em>' reference list.
	 * The list contents are of type {@link electrickery.general.LineGeometry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Geometries</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Geometries</em>' reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_LineGeometries()
	 * @model
	 * @generated
	 */
	EList<LineGeometry> getLineGeometries();

	/**
	 * Returns the value of the '<em><b>Executives</b></em>' reference list.
	 * The list contents are of type {@link electrickery.executive.Executive}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executives</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executives</em>' reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_Executives()
	 * @model
	 * @generated
	 */
	EList<Executive> getExecutives();

} // Electrickery
