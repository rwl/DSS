/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see electrickery.general.GeneralPackage
 * @generated
 */
public interface GeneralFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GeneralFactory eINSTANCE = electrickery.general.impl.GeneralFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>DSS Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DSS Object</em>'.
	 * @generated
	 */
	DSSObject createDSSObject();

	/**
	 * Returns a new object of class '<em>Growth Shape</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Growth Shape</em>'.
	 * @generated
	 */
	GrowthShape createGrowthShape();

	/**
	 * Returns a new object of class '<em>Line Code</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Line Code</em>'.
	 * @generated
	 */
	LineCode createLineCode();

	/**
	 * Returns a new object of class '<em>Line Geometry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Line Geometry</em>'.
	 * @generated
	 */
	LineGeometry createLineGeometry();

	/**
	 * Returns a new object of class '<em>Line Spacing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Line Spacing</em>'.
	 * @generated
	 */
	LineSpacing createLineSpacing();

	/**
	 * Returns a new object of class '<em>Load Shape</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Load Shape</em>'.
	 * @generated
	 */
	LoadShape createLoadShape();

	/**
	 * Returns a new object of class '<em>Spectrum</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Spectrum</em>'.
	 * @generated
	 */
	Spectrum createSpectrum();

	/**
	 * Returns a new object of class '<em>Time Current Curve</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Current Curve</em>'.
	 * @generated
	 */
	TimeCurrentCurve createTimeCurrentCurve();

	/**
	 * Returns a new object of class '<em>Wire Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wire Data</em>'.
	 * @generated
	 */
	WireData createWireData();

	/**
	 * Returns a new object of class '<em>Transformer Code</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformer Code</em>'.
	 * @generated
	 */
	TransformerCode createTransformerCode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	GeneralPackage getGeneralPackage();

} //GeneralFactory
