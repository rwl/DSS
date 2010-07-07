/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see electrickery.control.ControlPackage
 * @generated
 */
public interface ControlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ControlFactory eINSTANCE = electrickery.control.impl.ControlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Capacitor Control</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Capacitor Control</em>'.
	 * @generated
	 */
	CapacitorControl createCapacitorControl();

	/**
	 * Returns a new object of class '<em>Generator Dispatcher</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generator Dispatcher</em>'.
	 * @generated
	 */
	GeneratorDispatcher createGeneratorDispatcher();

	/**
	 * Returns a new object of class '<em>Recloser</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Recloser</em>'.
	 * @generated
	 */
	Recloser createRecloser();

	/**
	 * Returns a new object of class '<em>Regulator Control</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Regulator Control</em>'.
	 * @generated
	 */
	RegulatorControl createRegulatorControl();

	/**
	 * Returns a new object of class '<em>Relay</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relay</em>'.
	 * @generated
	 */
	Relay createRelay();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ControlPackage getControlPackage();

} //ControlFactory
