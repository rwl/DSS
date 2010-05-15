/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general.impl;

import dss.general.DSSObject;
import dss.general.GeneralPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DSS Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.general.impl.DSSObjectImpl#getDSSObjType <em>DSS Obj Type</em>}</li>
 *   <li>{@link dss.general.impl.DSSObjectImpl#getDSSClassName <em>DSS Class Name</em>}</li>
 *   <li>{@link dss.general.impl.DSSObjectImpl#getParentClass <em>Parent Class</em>}</li>
 *   <li>{@link dss.general.impl.DSSObjectImpl#getClassIndex <em>Class Index</em>}</li>
 *   <li>{@link dss.general.impl.DSSObjectImpl#isDirty <em>Dirty</em>}</li>
 *   <li>{@link dss.general.impl.DSSObjectImpl#isFlag <em>Flag</em>}</li>
 *   <li>{@link dss.general.impl.DSSObjectImpl#getPropSequence <em>Prop Sequence</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DSSObjectImpl extends EObjectImpl implements DSSObject {
	/**
	 * The default value of the '{@link #getDSSObjType() <em>DSS Obj Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDSSObjType()
	 * @generated
	 * @ordered
	 */
	protected static final int DSS_OBJ_TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDSSObjType() <em>DSS Obj Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDSSObjType()
	 * @generated
	 * @ordered
	 */
	protected int dssObjType = DSS_OBJ_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDSSClassName() <em>DSS Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDSSClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String DSS_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDSSClassName() <em>DSS Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDSSClassName()
	 * @generated
	 * @ordered
	 */
	protected String dssClassName = DSS_CLASS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParentClass() <em>Parent Class</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentClass()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> parentClass;

	/**
	 * The default value of the '{@link #getClassIndex() <em>Class Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int CLASS_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getClassIndex() <em>Class Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassIndex()
	 * @generated
	 * @ordered
	 */
	protected int classIndex = CLASS_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #isDirty() <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DIRTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDirty() <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected boolean dirty = DIRTY_EDEFAULT;

	/**
	 * The default value of the '{@link #isFlag() <em>Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFlag()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FLAG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFlag() <em>Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFlag()
	 * @generated
	 * @ordered
	 */
	protected boolean flag = FLAG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPropSequence() <em>Prop Sequence</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropSequence()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> propSequence;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DSSObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneralPackage.Literals.DSS_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDSSObjType() {
		return dssObjType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDSSObjType(int newDSSObjType) {
		int oldDSSObjType = dssObjType;
		dssObjType = newDSSObjType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.DSS_OBJECT__DSS_OBJ_TYPE, oldDSSObjType, dssObjType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDSSClassName() {
		return dssClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDSSClassName(String newDSSClassName) {
		String oldDSSClassName = dssClassName;
		dssClassName = newDSSClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.DSS_OBJECT__DSS_CLASS_NAME, oldDSSClassName, dssClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getParentClass() {
		if (parentClass == null) {
			parentClass = new EObjectResolvingEList<EClass>(EClass.class, this, GeneralPackage.DSS_OBJECT__PARENT_CLASS);
		}
		return parentClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getClassIndex() {
		return classIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassIndex(int newClassIndex) {
		int oldClassIndex = classIndex;
		classIndex = newClassIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.DSS_OBJECT__CLASS_INDEX, oldClassIndex, classIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirty(boolean newDirty) {
		boolean oldDirty = dirty;
		dirty = newDirty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.DSS_OBJECT__DIRTY, oldDirty, dirty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFlag(boolean newFlag) {
		boolean oldFlag = flag;
		flag = newFlag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneralPackage.DSS_OBJECT__FLAG, oldFlag, flag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getPropSequence() {
		if (propSequence == null) {
			propSequence = new EDataTypeUniqueEList<Double>(Double.class, this, GeneralPackage.DSS_OBJECT__PROP_SEQUENCE);
		}
		return propSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneralPackage.DSS_OBJECT__DSS_OBJ_TYPE:
				return getDSSObjType();
			case GeneralPackage.DSS_OBJECT__DSS_CLASS_NAME:
				return getDSSClassName();
			case GeneralPackage.DSS_OBJECT__PARENT_CLASS:
				return getParentClass();
			case GeneralPackage.DSS_OBJECT__CLASS_INDEX:
				return getClassIndex();
			case GeneralPackage.DSS_OBJECT__DIRTY:
				return isDirty();
			case GeneralPackage.DSS_OBJECT__FLAG:
				return isFlag();
			case GeneralPackage.DSS_OBJECT__PROP_SEQUENCE:
				return getPropSequence();
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
			case GeneralPackage.DSS_OBJECT__DSS_OBJ_TYPE:
				setDSSObjType((Integer)newValue);
				return;
			case GeneralPackage.DSS_OBJECT__DSS_CLASS_NAME:
				setDSSClassName((String)newValue);
				return;
			case GeneralPackage.DSS_OBJECT__PARENT_CLASS:
				getParentClass().clear();
				getParentClass().addAll((Collection<? extends EClass>)newValue);
				return;
			case GeneralPackage.DSS_OBJECT__CLASS_INDEX:
				setClassIndex((Integer)newValue);
				return;
			case GeneralPackage.DSS_OBJECT__DIRTY:
				setDirty((Boolean)newValue);
				return;
			case GeneralPackage.DSS_OBJECT__FLAG:
				setFlag((Boolean)newValue);
				return;
			case GeneralPackage.DSS_OBJECT__PROP_SEQUENCE:
				getPropSequence().clear();
				getPropSequence().addAll((Collection<? extends Double>)newValue);
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
			case GeneralPackage.DSS_OBJECT__DSS_OBJ_TYPE:
				setDSSObjType(DSS_OBJ_TYPE_EDEFAULT);
				return;
			case GeneralPackage.DSS_OBJECT__DSS_CLASS_NAME:
				setDSSClassName(DSS_CLASS_NAME_EDEFAULT);
				return;
			case GeneralPackage.DSS_OBJECT__PARENT_CLASS:
				getParentClass().clear();
				return;
			case GeneralPackage.DSS_OBJECT__CLASS_INDEX:
				setClassIndex(CLASS_INDEX_EDEFAULT);
				return;
			case GeneralPackage.DSS_OBJECT__DIRTY:
				setDirty(DIRTY_EDEFAULT);
				return;
			case GeneralPackage.DSS_OBJECT__FLAG:
				setFlag(FLAG_EDEFAULT);
				return;
			case GeneralPackage.DSS_OBJECT__PROP_SEQUENCE:
				getPropSequence().clear();
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
			case GeneralPackage.DSS_OBJECT__DSS_OBJ_TYPE:
				return dssObjType != DSS_OBJ_TYPE_EDEFAULT;
			case GeneralPackage.DSS_OBJECT__DSS_CLASS_NAME:
				return DSS_CLASS_NAME_EDEFAULT == null ? dssClassName != null : !DSS_CLASS_NAME_EDEFAULT.equals(dssClassName);
			case GeneralPackage.DSS_OBJECT__PARENT_CLASS:
				return parentClass != null && !parentClass.isEmpty();
			case GeneralPackage.DSS_OBJECT__CLASS_INDEX:
				return classIndex != CLASS_INDEX_EDEFAULT;
			case GeneralPackage.DSS_OBJECT__DIRTY:
				return dirty != DIRTY_EDEFAULT;
			case GeneralPackage.DSS_OBJECT__FLAG:
				return flag != FLAG_EDEFAULT;
			case GeneralPackage.DSS_OBJECT__PROP_SEQUENCE:
				return propSequence != null && !propSequence.isEmpty();
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
		result.append(" (DSSObjType: ");
		result.append(dssObjType);
		result.append(", DSSClassName: ");
		result.append(dssClassName);
		result.append(", classIndex: ");
		result.append(classIndex);
		result.append(", dirty: ");
		result.append(dirty);
		result.append(", flag: ");
		result.append(flag);
		result.append(", propSequence: ");
		result.append(propSequence);
		result.append(')');
		return result.toString();
	}

} //DSSObjectImpl
