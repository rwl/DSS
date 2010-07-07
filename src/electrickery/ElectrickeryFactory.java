/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see electrickery.ElectrickeryPackage
 * @generated
 */
public interface ElectrickeryFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ElectrickeryFactory eINSTANCE = electrickery.impl.ElectrickeryFactoryImpl.init();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ElectrickeryPackage getElectrickeryPackage();

} //ElectrickeryFactory
