/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common.impl;

import electrickery.common.CommonPackage;
import electrickery.common.Electrickery;

import electrickery.executive.Executive;

import electrickery.general.GrowthShape;
import electrickery.general.LineCode;
import electrickery.general.LineGeometry;
import electrickery.general.LoadShape;
import electrickery.general.Spectrum;
import electrickery.general.WireData;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
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
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getGrowthShapes <em>Growth Shapes</em>}</li>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getLineCodes <em>Line Codes</em>}</li>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getLoadShapes <em>Load Shapes</em>}</li>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getSpectrums <em>Spectrums</em>}</li>
 *   <li>{@link electrickery.common.impl.ElectrickeryImpl#getExecutives <em>Executives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElectrickeryImpl extends EObjectImpl implements Electrickery {
	/**
	 * The cached value of the '{@link #getWireData() <em>Wire Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWireData()
	 * @generated
	 * @ordered
	 */
	protected EList<WireData> wireData;

	/**
	 * The cached value of the '{@link #getLineGeometries() <em>Line Geometries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineGeometries()
	 * @generated
	 * @ordered
	 */
	protected EList<LineGeometry> lineGeometries;

	/**
	 * The cached value of the '{@link #getGrowthShapes() <em>Growth Shapes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrowthShapes()
	 * @generated
	 * @ordered
	 */
	protected EList<GrowthShape> growthShapes;

	/**
	 * The cached value of the '{@link #getLineCodes() <em>Line Codes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineCodes()
	 * @generated
	 * @ordered
	 */
	protected EList<LineCode> lineCodes;

	/**
	 * The cached value of the '{@link #getLoadShapes() <em>Load Shapes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadShapes()
	 * @generated
	 * @ordered
	 */
	protected EList<LoadShape> loadShapes;

	/**
	 * The cached value of the '{@link #getSpectrums() <em>Spectrums</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpectrums()
	 * @generated
	 * @ordered
	 */
	protected EList<Spectrum> spectrums;

	/**
	 * The cached value of the '{@link #getExecutives() <em>Executives</em>}' containment reference list.
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
			wireData = new EObjectContainmentEList<WireData>(WireData.class, this, CommonPackage.ELECTRICKERY__WIRE_DATA);
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
			lineGeometries = new EObjectContainmentEList<LineGeometry>(LineGeometry.class, this, CommonPackage.ELECTRICKERY__LINE_GEOMETRIES);
		}
		return lineGeometries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GrowthShape> getGrowthShapes() {
		if (growthShapes == null) {
			growthShapes = new EObjectContainmentEList<GrowthShape>(GrowthShape.class, this, CommonPackage.ELECTRICKERY__GROWTH_SHAPES);
		}
		return growthShapes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LineCode> getLineCodes() {
		if (lineCodes == null) {
			lineCodes = new EObjectContainmentEList<LineCode>(LineCode.class, this, CommonPackage.ELECTRICKERY__LINE_CODES);
		}
		return lineCodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LoadShape> getLoadShapes() {
		if (loadShapes == null) {
			loadShapes = new EObjectContainmentEList<LoadShape>(LoadShape.class, this, CommonPackage.ELECTRICKERY__LOAD_SHAPES);
		}
		return loadShapes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Spectrum> getSpectrums() {
		if (spectrums == null) {
			spectrums = new EObjectContainmentEList<Spectrum>(Spectrum.class, this, CommonPackage.ELECTRICKERY__SPECTRUMS);
		}
		return spectrums;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Executive> getExecutives() {
		if (executives == null) {
			executives = new EObjectContainmentEList<Executive>(Executive.class, this, CommonPackage.ELECTRICKERY__EXECUTIVES);
		}
		return executives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.ELECTRICKERY__WIRE_DATA:
				return ((InternalEList<?>)getWireData()).basicRemove(otherEnd, msgs);
			case CommonPackage.ELECTRICKERY__LINE_GEOMETRIES:
				return ((InternalEList<?>)getLineGeometries()).basicRemove(otherEnd, msgs);
			case CommonPackage.ELECTRICKERY__GROWTH_SHAPES:
				return ((InternalEList<?>)getGrowthShapes()).basicRemove(otherEnd, msgs);
			case CommonPackage.ELECTRICKERY__LINE_CODES:
				return ((InternalEList<?>)getLineCodes()).basicRemove(otherEnd, msgs);
			case CommonPackage.ELECTRICKERY__LOAD_SHAPES:
				return ((InternalEList<?>)getLoadShapes()).basicRemove(otherEnd, msgs);
			case CommonPackage.ELECTRICKERY__SPECTRUMS:
				return ((InternalEList<?>)getSpectrums()).basicRemove(otherEnd, msgs);
			case CommonPackage.ELECTRICKERY__EXECUTIVES:
				return ((InternalEList<?>)getExecutives()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case CommonPackage.ELECTRICKERY__GROWTH_SHAPES:
				return getGrowthShapes();
			case CommonPackage.ELECTRICKERY__LINE_CODES:
				return getLineCodes();
			case CommonPackage.ELECTRICKERY__LOAD_SHAPES:
				return getLoadShapes();
			case CommonPackage.ELECTRICKERY__SPECTRUMS:
				return getSpectrums();
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
			case CommonPackage.ELECTRICKERY__GROWTH_SHAPES:
				getGrowthShapes().clear();
				getGrowthShapes().addAll((Collection<? extends GrowthShape>)newValue);
				return;
			case CommonPackage.ELECTRICKERY__LINE_CODES:
				getLineCodes().clear();
				getLineCodes().addAll((Collection<? extends LineCode>)newValue);
				return;
			case CommonPackage.ELECTRICKERY__LOAD_SHAPES:
				getLoadShapes().clear();
				getLoadShapes().addAll((Collection<? extends LoadShape>)newValue);
				return;
			case CommonPackage.ELECTRICKERY__SPECTRUMS:
				getSpectrums().clear();
				getSpectrums().addAll((Collection<? extends Spectrum>)newValue);
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
			case CommonPackage.ELECTRICKERY__GROWTH_SHAPES:
				getGrowthShapes().clear();
				return;
			case CommonPackage.ELECTRICKERY__LINE_CODES:
				getLineCodes().clear();
				return;
			case CommonPackage.ELECTRICKERY__LOAD_SHAPES:
				getLoadShapes().clear();
				return;
			case CommonPackage.ELECTRICKERY__SPECTRUMS:
				getSpectrums().clear();
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
			case CommonPackage.ELECTRICKERY__GROWTH_SHAPES:
				return growthShapes != null && !growthShapes.isEmpty();
			case CommonPackage.ELECTRICKERY__LINE_CODES:
				return lineCodes != null && !lineCodes.isEmpty();
			case CommonPackage.ELECTRICKERY__LOAD_SHAPES:
				return loadShapes != null && !loadShapes.isEmpty();
			case CommonPackage.ELECTRICKERY__SPECTRUMS:
				return spectrums != null && !spectrums.isEmpty();
			case CommonPackage.ELECTRICKERY__EXECUTIVES:
				return executives != null && !executives.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ElectrickeryImpl
