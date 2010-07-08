/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.util;

import electrickery.general.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see electrickery.general.GeneralPackage
 * @generated
 */
public class GeneralAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GeneralPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = GeneralPackage.eINSTANCE;
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
	protected GeneralSwitch<Adapter> modelSwitch =
		new GeneralSwitch<Adapter>() {
			@Override
			public Adapter caseDSSObject(DSSObject object) {
				return createDSSObjectAdapter();
			}
			@Override
			public Adapter caseGrowthShape(GrowthShape object) {
				return createGrowthShapeAdapter();
			}
			@Override
			public Adapter caseLineCode(LineCode object) {
				return createLineCodeAdapter();
			}
			@Override
			public Adapter caseLineGeometry(LineGeometry object) {
				return createLineGeometryAdapter();
			}
			@Override
			public Adapter caseLoadShape(LoadShape object) {
				return createLoadShapeAdapter();
			}
			@Override
			public Adapter caseSpectrum(Spectrum object) {
				return createSpectrumAdapter();
			}
			@Override
			public Adapter caseTimeCurrentCurve(TimeCurrentCurve object) {
				return createTimeCurrentCurveAdapter();
			}
			@Override
			public Adapter caseWireData(WireData object) {
				return createWireDataAdapter();
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
	 * Creates a new adapter for an object of class '{@link electrickery.general.DSSObject <em>DSS Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.DSSObject
	 * @generated
	 */
	public Adapter createDSSObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.GrowthShape <em>Growth Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.GrowthShape
	 * @generated
	 */
	public Adapter createGrowthShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.LineCode <em>Line Code</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.LineCode
	 * @generated
	 */
	public Adapter createLineCodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.LineGeometry <em>Line Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.LineGeometry
	 * @generated
	 */
	public Adapter createLineGeometryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.LoadShape <em>Load Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.LoadShape
	 * @generated
	 */
	public Adapter createLoadShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.Spectrum <em>Spectrum</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.Spectrum
	 * @generated
	 */
	public Adapter createSpectrumAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.TimeCurrentCurve <em>Time Current Curve</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.TimeCurrentCurve
	 * @generated
	 */
	public Adapter createTimeCurrentCurveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link electrickery.general.WireData <em>Wire Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see electrickery.general.WireData
	 * @generated
	 */
	public Adapter createWireDataAdapter() {
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

} //GeneralAdapterFactory
