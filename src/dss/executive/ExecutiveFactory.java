/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.executive;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see dss.executive.ExecutivePackage
 * @generated
 */
public interface ExecutiveFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExecutiveFactory eINSTANCE = dss.executive.impl.ExecutiveFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Exec Options</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exec Options</em>'.
	 * @generated
	 */
	ExecOptions createExecOptions();

	/**
	 * Returns a new object of class '<em>Exec Commands</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exec Commands</em>'.
	 * @generated
	 */
	ExecCommands createExecCommands();

	/**
	 * Returns a new object of class '<em>Executive</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Executive</em>'.
	 * @generated
	 */
	Executive createExecutive();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExecutivePackage getExecutivePackage();

} //ExecutiveFactory
