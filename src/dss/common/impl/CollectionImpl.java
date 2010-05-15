/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common.impl;

import dss.common.CircuitElement;
import dss.common.Collection;
import dss.common.CommonPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.CollectionImpl#getNProperties <em>NProperties</em>}</li>
 *   <li>{@link dss.common.impl.CollectionImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link dss.common.impl.CollectionImpl#getPropertyHelp <em>Property Help</em>}</li>
 *   <li>{@link dss.common.impl.CollectionImpl#getPropertyIdxMap <em>Property Idx Map</em>}</li>
 *   <li>{@link dss.common.impl.CollectionImpl#getElementList <em>Element List</em>}</li>
 *   <li>{@link dss.common.impl.CollectionImpl#isSaved <em>Saved</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CollectionImpl extends EObjectImpl implements Collection {
	/**
	 * The default value of the '{@link #getNProperties() <em>NProperties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNProperties()
	 * @generated
	 * @ordered
	 */
	protected static final int NPROPERTIES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNProperties() <em>NProperties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNProperties()
	 * @generated
	 * @ordered
	 */
	protected int nProperties = NPROPERTIES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyName()
	 * @generated
	 * @ordered
	 */
	protected EList<String> propertyName;

	/**
	 * The cached value of the '{@link #getPropertyHelp() <em>Property Help</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyHelp()
	 * @generated
	 * @ordered
	 */
	protected EList<String> propertyHelp;

	/**
	 * The cached value of the '{@link #getPropertyIdxMap() <em>Property Idx Map</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyIdxMap()
	 * @generated
	 * @ordered
	 */
	protected EList<String> propertyIdxMap;

	/**
	 * The cached value of the '{@link #getElementList() <em>Element List</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementList()
	 * @generated
	 * @ordered
	 */
	protected EList<CircuitElement> elementList;

	/**
	 * The default value of the '{@link #isSaved() <em>Saved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSaved()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SAVED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSaved() <em>Saved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSaved()
	 * @generated
	 * @ordered
	 */
	protected boolean saved = SAVED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNProperties() {
		return nProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNProperties(int newNProperties) {
		int oldNProperties = nProperties;
		nProperties = newNProperties;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COLLECTION__NPROPERTIES, oldNProperties, nProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPropertyName() {
		if (propertyName == null) {
			propertyName = new EDataTypeUniqueEList<String>(String.class, this, CommonPackage.COLLECTION__PROPERTY_NAME);
		}
		return propertyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPropertyHelp() {
		if (propertyHelp == null) {
			propertyHelp = new EDataTypeUniqueEList<String>(String.class, this, CommonPackage.COLLECTION__PROPERTY_HELP);
		}
		return propertyHelp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPropertyIdxMap() {
		if (propertyIdxMap == null) {
			propertyIdxMap = new EDataTypeUniqueEList<String>(String.class, this, CommonPackage.COLLECTION__PROPERTY_IDX_MAP);
		}
		return propertyIdxMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CircuitElement> getElementList() {
		if (elementList == null) {
			elementList = new EObjectResolvingEList<CircuitElement>(CircuitElement.class, this, CommonPackage.COLLECTION__ELEMENT_LIST);
		}
		return elementList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSaved(boolean newSaved) {
		boolean oldSaved = saved;
		saved = newSaved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COLLECTION__SAVED, oldSaved, saved));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.COLLECTION__NPROPERTIES:
				return getNProperties();
			case CommonPackage.COLLECTION__PROPERTY_NAME:
				return getPropertyName();
			case CommonPackage.COLLECTION__PROPERTY_HELP:
				return getPropertyHelp();
			case CommonPackage.COLLECTION__PROPERTY_IDX_MAP:
				return getPropertyIdxMap();
			case CommonPackage.COLLECTION__ELEMENT_LIST:
				return getElementList();
			case CommonPackage.COLLECTION__SAVED:
				return isSaved();
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
			case CommonPackage.COLLECTION__NPROPERTIES:
				setNProperties((Integer)newValue);
				return;
			case CommonPackage.COLLECTION__PROPERTY_NAME:
				getPropertyName().clear();
				getPropertyName().addAll((java.util.Collection<? extends String>)newValue);
				return;
			case CommonPackage.COLLECTION__PROPERTY_HELP:
				getPropertyHelp().clear();
				getPropertyHelp().addAll((java.util.Collection<? extends String>)newValue);
				return;
			case CommonPackage.COLLECTION__PROPERTY_IDX_MAP:
				getPropertyIdxMap().clear();
				getPropertyIdxMap().addAll((java.util.Collection<? extends String>)newValue);
				return;
			case CommonPackage.COLLECTION__ELEMENT_LIST:
				getElementList().clear();
				getElementList().addAll((java.util.Collection<? extends CircuitElement>)newValue);
				return;
			case CommonPackage.COLLECTION__SAVED:
				setSaved((Boolean)newValue);
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
			case CommonPackage.COLLECTION__NPROPERTIES:
				setNProperties(NPROPERTIES_EDEFAULT);
				return;
			case CommonPackage.COLLECTION__PROPERTY_NAME:
				getPropertyName().clear();
				return;
			case CommonPackage.COLLECTION__PROPERTY_HELP:
				getPropertyHelp().clear();
				return;
			case CommonPackage.COLLECTION__PROPERTY_IDX_MAP:
				getPropertyIdxMap().clear();
				return;
			case CommonPackage.COLLECTION__ELEMENT_LIST:
				getElementList().clear();
				return;
			case CommonPackage.COLLECTION__SAVED:
				setSaved(SAVED_EDEFAULT);
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
			case CommonPackage.COLLECTION__NPROPERTIES:
				return nProperties != NPROPERTIES_EDEFAULT;
			case CommonPackage.COLLECTION__PROPERTY_NAME:
				return propertyName != null && !propertyName.isEmpty();
			case CommonPackage.COLLECTION__PROPERTY_HELP:
				return propertyHelp != null && !propertyHelp.isEmpty();
			case CommonPackage.COLLECTION__PROPERTY_IDX_MAP:
				return propertyIdxMap != null && !propertyIdxMap.isEmpty();
			case CommonPackage.COLLECTION__ELEMENT_LIST:
				return elementList != null && !elementList.isEmpty();
			case CommonPackage.COLLECTION__SAVED:
				return saved != SAVED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (nProperties: ");
		result.append(nProperties);
		result.append(", propertyName: ");
		result.append(propertyName);
		result.append(", propertyHelp: ");
		result.append(propertyHelp);
		result.append(", propertyIdxMap: ");
		result.append(propertyIdxMap);
		result.append(", saved: ");
		result.append(saved);
		result.append(')');
		return result.toString();
	}

} //CollectionImpl
