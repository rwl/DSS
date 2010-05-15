/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control.util;

import dss.common.CircuitElement;

import dss.control.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see dss.control.ControlPackage
 * @generated
 */
public class ControlAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ControlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ControlPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlSwitch<Adapter> modelSwitch =
		new ControlSwitch<Adapter>() {
			@Override
			public Adapter caseControlElement(ControlElement object) {
				return createControlElementAdapter();
			}
			@Override
			public Adapter caseCapacitorControl(CapacitorControl object) {
				return createCapacitorControlAdapter();
			}
			@Override
			public Adapter caseGeneratorDispatcher(GeneratorDispatcher object) {
				return createGeneratorDispatcherAdapter();
			}
			@Override
			public Adapter caseRecloser(Recloser object) {
				return createRecloserAdapter();
			}
			@Override
			public Adapter caseRegulatorControl(RegulatorControl object) {
				return createRegulatorControlAdapter();
			}
			@Override
			public Adapter caseRelay(Relay object) {
				return createRelayAdapter();
			}
			@Override
			public Adapter caseCircuitElement(CircuitElement object) {
				return createCircuitElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link dss.control.ControlElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.control.ControlElement
	 * @generated
	 */
	public Adapter createControlElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link dss.control.CapacitorControl <em>Capacitor Control</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.control.CapacitorControl
	 * @generated
	 */
	public Adapter createCapacitorControlAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link dss.control.GeneratorDispatcher <em>Generator Dispatcher</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.control.GeneratorDispatcher
	 * @generated
	 */
	public Adapter createGeneratorDispatcherAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link dss.control.Recloser <em>Recloser</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.control.Recloser
	 * @generated
	 */
	public Adapter createRecloserAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link dss.control.RegulatorControl <em>Regulator Control</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.control.RegulatorControl
	 * @generated
	 */
	public Adapter createRegulatorControlAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link dss.control.Relay <em>Relay</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.control.Relay
	 * @generated
	 */
	public Adapter createRelayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link dss.common.CircuitElement <em>Circuit Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see dss.common.CircuitElement
	 * @generated
	 */
	public Adapter createCircuitElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ControlAdapterFactory
