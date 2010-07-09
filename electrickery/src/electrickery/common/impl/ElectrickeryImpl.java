/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common.impl;

import electrickery.common.CommonPackage;
import electrickery.common.Electrickery;

import electrickery.executive.Executive;

import electrickery.general.LineGeometry;
import electrickery.general.WireData;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Electrickery</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getWireData <em>Wire Data</em>}</li>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getLineGeometries <em>Line Geometries</em>}</li>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getExecutives <em>Executives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElectrickeryImpl extends EObjectImpl implements Electrickery {
	/**
	 * The cached value of the '{@link #getWireData() <em>Wire Data</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWireData()
	 * @generated
	 * @ordered
	 */
	protected EList<WireData> wireData;

	/**
	 * The cached value of the '{@link #getLineGeometries() <em>Line Geometries</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineGeometries()
	 * @generated
	 * @ordered
	 */
	protected EList<LineGeometry> lineGeometries;

	/**
	 * The cached value of the '{@link #getExecutives() <em>Executives</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutives()
	 * @generated
	 * @ordered
	 */
	protected EList<Executive> executives;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElectrickeryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.ELECTRICKERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WireData> getWireData() {
		if (wireData == null) {
			wireData = new EObjectResolvingEList<WireData>(WireData.class, this, CommonPackage.ELECTRICKERY__WIRE_DATA);
		}
		return wireData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LineGeometry> getLineGeometries() {
		if (lineGeometries == null) {
			lineGeometries = new EObjectResolvingEList<LineGeometry>(LineGeometry.class, this, CommonPackage.ELECTRICKERY__LINE_GEOMETRIES);
		}
		return lineGeometries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Executive> getExecutives() {
		if (executives == null) {
			executives = new EObjectResolvingEList<Executive>(Executive.class, this, CommonPackage.ELECTRICKERY__EXECUTIVES);
		}
		return executives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.ELECTRICKERY__WIRE_DATA:
				return getWireData();
			case CommonPackage.ELECTRICKERY__LINE_GEOMETRIES:
				return getLineGeometries();
			case CommonPackage.ELECTRICKERY__EXECUTIVES:
				return getExecutives();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.ELECTRICKERY__WIRE_DATA:
				getWireData().clear();
				getWireData().addAll((Collection<? extends WireData>)newValue);
				return;
			case CommonPackage.ELECTRICKERY__LINE_GEOMETRIES:
				getLineGeometries().clear();
				getLineGeometries().addAll((Collection<? extends LineGeometry>)newValue);
				return;
			case CommonPackage.ELECTRICKERY__EXECUTIVES:
				getExecutives().clear();
				getExecutives().addAll((Collection<? extends Executive>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.ELECTRICKERY__WIRE_DATA:
				getWireData().clear();
				return;
			case CommonPackage.ELECTRICKERY__LINE_GEOMETRIES:
				getLineGeometries().clear();
				return;
			case CommonPackage.ELECTRICKERY__EXECUTIVES:
				getExecutives().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.ELECTRICKERY__WIRE_DATA:
				return wireData != null && !wireData.isEmpty();
			case CommonPackage.ELECTRICKERY__LINE_GEOMETRIES:
				return lineGeometries != null && !lineGeometries.isEmpty();
			case CommonPackage.ELECTRICKERY__EXECUTIVES:
				return executives != null && !executives.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ElectrickeryImpl
