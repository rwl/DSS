/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.impl;

import electrickery.general.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GeneralFactoryImpl extends EFactoryImpl implements GeneralFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GeneralFactory init() {
		try {
			GeneralFactory theGeneralFactory = (GeneralFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openpowersystem.com/dss/general"); 
			if (theGeneralFactory != null) {
				return theGeneralFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GeneralFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case GeneralPackage.DSS_OBJECT: return createDSSObject();
			case GeneralPackage.GROWTH_SHAPE: return createGrowthShape();
			case GeneralPackage.LINE_CODE: return createLineCode();
			case GeneralPackage.LINE_GEOMETRY: return createLineGeometry();
			case GeneralPackage.LOAD_SHAPE: return createLoadShape();
			case GeneralPackage.SPECTRUM: return createSpectrum();
			case GeneralPackage.TIME_CURRENT_CURVE: return createTimeCurrentCurve();
			case GeneralPackage.WIRE_DATA: return createWireData();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSSObject createDSSObject() {
		DSSObjectImpl dssObject = new DSSObjectImpl();
		return dssObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GrowthShape createGrowthShape() {
		GrowthShapeImpl growthShape = new GrowthShapeImpl();
		return growthShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineCode createLineCode() {
		LineCodeImpl lineCode = new LineCodeImpl();
		return lineCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineGeometry createLineGeometry() {
		LineGeometryImpl lineGeometry = new LineGeometryImpl();
		return lineGeometry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape createLoadShape() {
		LoadShapeImpl loadShape = new LoadShapeImpl();
		return loadShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Spectrum createSpectrum() {
		SpectrumImpl spectrum = new SpectrumImpl();
		return spectrum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeCurrentCurve createTimeCurrentCurve() {
		TimeCurrentCurveImpl timeCurrentCurve = new TimeCurrentCurveImpl();
		return timeCurrentCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WireData createWireData() {
		WireDataImpl wireData = new WireDataImpl();
		return wireData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralPackage getGeneralPackage() {
		return (GeneralPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GeneralPackage getPackage() {
		return GeneralPackage.eINSTANCE;
	}

} //GeneralFactoryImpl
