/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see dss.delivery.DeliveryPackage
 * @generated
 */
public interface DeliveryFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeliveryFactory eINSTANCE = dss.delivery.impl.DeliveryFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Capacitor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Capacitor</em>'.
	 * @generated
	 */
	Capacitor createCapacitor();

	/**
	 * Returns a new object of class '<em>Fault</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fault</em>'.
	 * @generated
	 */
	Fault createFault();

	/**
	 * Returns a new object of class '<em>Fuse</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fuse</em>'.
	 * @generated
	 */
	Fuse createFuse();

	/**
	 * Returns a new object of class '<em>Line</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Line</em>'.
	 * @generated
	 */
	Line createLine();

	/**
	 * Returns a new object of class '<em>Reactor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reactor</em>'.
	 * @generated
	 */
	Reactor createReactor();

	/**
	 * Returns a new object of class '<em>Transformer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformer</em>'.
	 * @generated
	 */
	Transformer createTransformer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DeliveryPackage getDeliveryPackage();

} //DeliveryFactory
