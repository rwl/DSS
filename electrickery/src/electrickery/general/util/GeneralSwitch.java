/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.general.util;

import electrickery.common.Named;
import electrickery.general.*;

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
 * @see electrickery.general.GeneralPackage
 * @generated
 */
public class GeneralSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GeneralPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralSwitch() {
		if (modelPackage == null) {
			modelPackage = GeneralPackage.eINSTANCE;
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
			case GeneralPackage.DSS_OBJECT: {
				DSSObject dssObject = (DSSObject)theEObject;
				T result = caseDSSObject(dssObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.GROWTH_SHAPE: {
				GrowthShape growthShape = (GrowthShape)theEObject;
				T result = caseGrowthShape(growthShape);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.LINE_CODE: {
				LineCode lineCode = (LineCode)theEObject;
				T result = caseLineCode(lineCode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.LINE_GEOMETRY: {
				LineGeometry lineGeometry = (LineGeometry)theEObject;
				T result = caseLineGeometry(lineGeometry);
				if (result == null) result = caseNamed(lineGeometry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.LINE_SPACING: {
				LineSpacing lineSpacing = (LineSpacing)theEObject;
				T result = caseLineSpacing(lineSpacing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.LOAD_SHAPE: {
				LoadShape loadShape = (LoadShape)theEObject;
				T result = caseLoadShape(loadShape);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.SPECTRUM: {
				Spectrum spectrum = (Spectrum)theEObject;
				T result = caseSpectrum(spectrum);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.TIME_CURRENT_CURVE: {
				TimeCurrentCurve timeCurrentCurve = (TimeCurrentCurve)theEObject;
				T result = caseTimeCurrentCurve(timeCurrentCurve);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.WIRE_DATA: {
				WireData wireData = (WireData)theEObject;
				T result = caseWireData(wireData);
				if (result == null) result = caseNamed(wireData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeneralPackage.TRANSFORMER_CODE: {
				TransformerCode transformerCode = (TransformerCode)theEObject;
				T result = caseTransformerCode(transformerCode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DSS Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DSS Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDSSObject(DSSObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Growth Shape</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Growth Shape</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGrowthShape(GrowthShape object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Line Code</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Line Code</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLineCode(LineCode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Line Geometry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Line Geometry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLineGeometry(LineGeometry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Line Spacing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Line Spacing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLineSpacing(LineSpacing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Load Shape</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Load Shape</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoadShape(LoadShape object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Spectrum</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Spectrum</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpectrum(Spectrum object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Current Curve</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Current Curve</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeCurrentCurve(TimeCurrentCurve object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireData(WireData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transformer Code</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transformer Code</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransformerCode(TransformerCode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamed(Named object) {
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

} //GeneralSwitch
