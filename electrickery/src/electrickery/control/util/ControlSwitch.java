/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.util;


import electrickery.common.CircuitElement;
import electrickery.control.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see electrickery.control.ControlPackage
 * @generated
 */
public class ControlSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ControlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlSwitch() {
		if (modelPackage == null) {
			modelPackage = ControlPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ControlPackage.CONTROL_ELEMENT: {
				ControlElement controlElement = (ControlElement)theEObject;
				T result = caseControlElement(controlElement);
				if (result == null) result = caseCircuitElement(controlElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.CAPACITOR_CONTROL: {
				CapacitorControl capacitorControl = (CapacitorControl)theEObject;
				T result = caseCapacitorControl(capacitorControl);
				if (result == null) result = caseControlElement(capacitorControl);
				if (result == null) result = caseCircuitElement(capacitorControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.GENERATOR_DISPATCHER: {
				GeneratorDispatcher generatorDispatcher = (GeneratorDispatcher)theEObject;
				T result = caseGeneratorDispatcher(generatorDispatcher);
				if (result == null) result = caseControlElement(generatorDispatcher);
				if (result == null) result = caseCircuitElement(generatorDispatcher);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.RECLOSER: {
				Recloser recloser = (Recloser)theEObject;
				T result = caseRecloser(recloser);
				if (result == null) result = caseControlElement(recloser);
				if (result == null) result = caseCircuitElement(recloser);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.REGULATOR_CONTROL: {
				RegulatorControl regulatorControl = (RegulatorControl)theEObject;
				T result = caseRegulatorControl(regulatorControl);
				if (result == null) result = caseControlElement(regulatorControl);
				if (result == null) result = caseCircuitElement(regulatorControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.RELAY: {
				Relay relay = (Relay)theEObject;
				T result = caseRelay(relay);
				if (result == null) result = caseControlElement(relay);
				if (result == null) result = caseCircuitElement(relay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.STORAGE_CONTROLLER: {
				StorageController storageController = (StorageController)theEObject;
				T result = caseStorageController(storageController);
				if (result == null) result = caseControlElement(storageController);
				if (result == null) result = caseCircuitElement(storageController);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.SWITCH_CONTROL: {
				SwitchControl switchControl = (SwitchControl)theEObject;
				T result = caseSwitchControl(switchControl);
				if (result == null) result = caseControlElement(switchControl);
				if (result == null) result = caseCircuitElement(switchControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlElement(ControlElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capacitor Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capacitor Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapacitorControl(CapacitorControl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generator Dispatcher</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generator Dispatcher</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeneratorDispatcher(GeneratorDispatcher object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Recloser</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Recloser</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRecloser(Recloser object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Regulator Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Regulator Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRegulatorControl(RegulatorControl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relay</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relay</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelay(Relay object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Storage Controller</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Storage Controller</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStorageController(StorageController object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Switch Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Switch Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSwitchControl(SwitchControl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Circuit Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Circuit Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCircuitElement(CircuitElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ControlSwitch
