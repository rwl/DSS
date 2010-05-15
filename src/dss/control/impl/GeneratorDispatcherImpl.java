/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control.impl;

import dss.control.ControlPackage;
import dss.control.GeneratorDispatcher;

import dss.conversion.Generator;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generator Dispatcher</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getElement <em>Element</em>}</li>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getKWLimit <em>KW Limit</em>}</li>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getKWBand <em>KW Band</em>}</li>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getKVarLimit <em>KVar Limit</em>}</li>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getGenList <em>Gen List</em>}</li>
 *   <li>{@link dss.control.impl.GeneratorDispatcherImpl#getWeights <em>Weights</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneratorDispatcherImpl extends ControlElementImpl implements GeneratorDispatcher {
	/**
	 * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected String element = ELEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected static final int TERMINAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected int terminal = TERMINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getKWLimit() <em>KW Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWLimit()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_LIMIT_EDEFAULT = 8000.0;

	/**
	 * The cached value of the '{@link #getKWLimit() <em>KW Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWLimit()
	 * @generated
	 * @ordered
	 */
	protected double kWLimit = KW_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getKWBand() <em>KW Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWBand()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_BAND_EDEFAULT = 100.0;

	/**
	 * The cached value of the '{@link #getKWBand() <em>KW Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWBand()
	 * @generated
	 * @ordered
	 */
	protected double kWBand = KW_BAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getKVarLimit() <em>KVar Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVarLimit()
	 * @generated
	 * @ordered
	 */
	protected static final double KVAR_LIMIT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVarLimit() <em>KVar Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVarLimit()
	 * @generated
	 * @ordered
	 */
	protected double kVarLimit = KVAR_LIMIT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGenList() <em>Gen List</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenList()
	 * @generated
	 * @ordered
	 */
	protected EList<Generator> genList;

	/**
	 * The cached value of the '{@link #getWeights() <em>Weights</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeights()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> weights;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneratorDispatcherImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.GENERATOR_DISPATCHER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(String newElement) {
		String oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.GENERATOR_DISPATCHER__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTerminal() {
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminal(int newTerminal) {
		int oldTerminal = terminal;
		terminal = newTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.GENERATOR_DISPATCHER__TERMINAL, oldTerminal, terminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWLimit() {
		return kWLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWLimit(double newKWLimit) {
		double oldKWLimit = kWLimit;
		kWLimit = newKWLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.GENERATOR_DISPATCHER__KW_LIMIT, oldKWLimit, kWLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWBand() {
		return kWBand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWBand(double newKWBand) {
		double oldKWBand = kWBand;
		kWBand = newKWBand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.GENERATOR_DISPATCHER__KW_BAND, oldKWBand, kWBand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVarLimit() {
		return kVarLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVarLimit(double newKVarLimit) {
		double oldKVarLimit = kVarLimit;
		kVarLimit = newKVarLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.GENERATOR_DISPATCHER__KVAR_LIMIT, oldKVarLimit, kVarLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Generator> getGenList() {
		if (genList == null) {
			genList = new EObjectResolvingEList<Generator>(Generator.class, this, ControlPackage.GENERATOR_DISPATCHER__GEN_LIST);
		}
		return genList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getWeights() {
		if (weights == null) {
			weights = new EDataTypeUniqueEList<Double>(Double.class, this, ControlPackage.GENERATOR_DISPATCHER__WEIGHTS);
		}
		return weights;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.GENERATOR_DISPATCHER__ELEMENT:
				return getElement();
			case ControlPackage.GENERATOR_DISPATCHER__TERMINAL:
				return getTerminal();
			case ControlPackage.GENERATOR_DISPATCHER__KW_LIMIT:
				return getKWLimit();
			case ControlPackage.GENERATOR_DISPATCHER__KW_BAND:
				return getKWBand();
			case ControlPackage.GENERATOR_DISPATCHER__KVAR_LIMIT:
				return getKVarLimit();
			case ControlPackage.GENERATOR_DISPATCHER__GEN_LIST:
				return getGenList();
			case ControlPackage.GENERATOR_DISPATCHER__WEIGHTS:
				return getWeights();
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
			case ControlPackage.GENERATOR_DISPATCHER__ELEMENT:
				setElement((String)newValue);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__TERMINAL:
				setTerminal((Integer)newValue);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__KW_LIMIT:
				setKWLimit((Double)newValue);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__KW_BAND:
				setKWBand((Double)newValue);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__KVAR_LIMIT:
				setKVarLimit((Double)newValue);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__GEN_LIST:
				getGenList().clear();
				getGenList().addAll((Collection<? extends Generator>)newValue);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__WEIGHTS:
				getWeights().clear();
				getWeights().addAll((Collection<? extends Double>)newValue);
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
			case ControlPackage.GENERATOR_DISPATCHER__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__TERMINAL:
				setTerminal(TERMINAL_EDEFAULT);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__KW_LIMIT:
				setKWLimit(KW_LIMIT_EDEFAULT);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__KW_BAND:
				setKWBand(KW_BAND_EDEFAULT);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__KVAR_LIMIT:
				setKVarLimit(KVAR_LIMIT_EDEFAULT);
				return;
			case ControlPackage.GENERATOR_DISPATCHER__GEN_LIST:
				getGenList().clear();
				return;
			case ControlPackage.GENERATOR_DISPATCHER__WEIGHTS:
				getWeights().clear();
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
			case ControlPackage.GENERATOR_DISPATCHER__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case ControlPackage.GENERATOR_DISPATCHER__TERMINAL:
				return terminal != TERMINAL_EDEFAULT;
			case ControlPackage.GENERATOR_DISPATCHER__KW_LIMIT:
				return kWLimit != KW_LIMIT_EDEFAULT;
			case ControlPackage.GENERATOR_DISPATCHER__KW_BAND:
				return kWBand != KW_BAND_EDEFAULT;
			case ControlPackage.GENERATOR_DISPATCHER__KVAR_LIMIT:
				return kVarLimit != KVAR_LIMIT_EDEFAULT;
			case ControlPackage.GENERATOR_DISPATCHER__GEN_LIST:
				return genList != null && !genList.isEmpty();
			case ControlPackage.GENERATOR_DISPATCHER__WEIGHTS:
				return weights != null && !weights.isEmpty();
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
		result.append(" (element: ");
		result.append(element);
		result.append(", terminal: ");
		result.append(terminal);
		result.append(", kWLimit: ");
		result.append(kWLimit);
		result.append(", kWBand: ");
		result.append(kWBand);
		result.append(", kVarLimit: ");
		result.append(kVarLimit);
		result.append(", weights: ");
		result.append(weights);
		result.append(')');
		return result.toString();
	}

} //GeneratorDispatcherImpl
