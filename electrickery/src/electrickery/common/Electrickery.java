/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common;

import electrickery.executive.Executive;

import electrickery.general.GrowthShape;
import electrickery.general.LineCode;
import electrickery.general.LineGeometry;
import electrickery.general.LoadShape;
import electrickery.general.Spectrum;
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
 *   <li>{@link electrickery.common.Electrickery#getGrowthShapes <em>Growth Shapes</em>}</li>
 *   <li>{@link electrickery.common.Electrickery#getLineCodes <em>Line Codes</em>}</li>
 *   <li>{@link electrickery.common.Electrickery#getLoadShapes <em>Load Shapes</em>}</li>
 *   <li>{@link electrickery.common.Electrickery#getSpectrums <em>Spectrums</em>}</li>
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
	 * Returns the value of the '<em><b>Wire Data</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.general.WireData}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wire Data</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wire Data</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_WireData()
	 * @model containment="true"
	 * @generated
	 */
	EList<WireData> getWireData();

	/**
	 * Returns the value of the '<em><b>Line Geometries</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.general.LineGeometry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Geometries</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Geometries</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_LineGeometries()
	 * @model containment="true"
	 * @generated
	 */
	EList<LineGeometry> getLineGeometries();

	/**
	 * Returns the value of the '<em><b>Growth Shapes</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.general.GrowthShape}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Growth Shapes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Growth Shapes</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_GrowthShapes()
	 * @model containment="true"
	 * @generated
	 */
	EList<GrowthShape> getGrowthShapes();

	/**
	 * Returns the value of the '<em><b>Line Codes</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.general.LineCode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Codes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Codes</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_LineCodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<LineCode> getLineCodes();

	/**
	 * Returns the value of the '<em><b>Load Shapes</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.general.LoadShape}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Shapes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Shapes</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_LoadShapes()
	 * @model containment="true"
	 * @generated
	 */
	EList<LoadShape> getLoadShapes();

	/**
	 * Returns the value of the '<em><b>Spectrums</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.general.Spectrum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spectrums</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spectrums</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_Spectrums()
	 * @model containment="true"
	 * @generated
	 */
	EList<Spectrum> getSpectrums();

	/**
	 * Returns the value of the '<em><b>Executives</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.executive.Executive}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executives</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executives</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getElectrickery_Executives()
	 * @model containment="true"
	 * @generated
	 */
	EList<Executive> getExecutives();

} // Electrickery
