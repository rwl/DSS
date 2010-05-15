/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.conversion;

import cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D;
import dss.common.CircuitElement;

import dss.general.Spectrum;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Power Conversion Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Power conversion elements convert power from electrical form to some
 * other form, or vice-versa.  Some may temporarily store energy and then give
 * it back, as is the case for reactive elements.  Most will have only one
 * connection to the power system and, therefore, only one multiphase
 * terminal.  The description of the mechanical or thermal side of the power
 * conversion is contained within the "Black box" model.  The description may
 * be a simple impedance or a complicated set of differential equations
 * yielding a current injection equation of the form:
 * 
 *         ITerm(t)  = F(VTerm, [State], t)
 * 
 * The function F will vary according to the type of simulation being
 * performed.  The power conversion element must also be capable of reporting
 * the partials matrix when necessary: dF/dV
 * 
 * In simple cases, this will simply be the primitive y (admittance) matrix;
 * that is, the y matrix for this element alone.
 * 
 * This concept may easily be extended to multi-terminal devices, which would
 * allow the representation of complex series elements such as fault current
 * limiters.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.conversion.PowerConversionElement#getSpectrum <em>Spectrum</em>}</li>
 *   <li>{@link dss.conversion.PowerConversionElement#getSpectrumObj <em>Spectrum Obj</em>}</li>
 *   <li>{@link dss.conversion.PowerConversionElement#getInjCurrent <em>Inj Current</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.conversion.ConversionPackage#getPowerConversionElement()
 * @model abstract="true"
 * @generated
 */
public interface PowerConversionElement extends CircuitElement {
	/**
	 * Returns the value of the '<em><b>Spectrum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of harmonic spectrum for this device.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Spectrum</em>' attribute.
	 * @see #setSpectrum(String)
	 * @see dss.conversion.ConversionPackage#getPowerConversionElement_Spectrum()
	 * @model
	 * @generated
	 */
	String getSpectrum();

	/**
	 * Sets the value of the '{@link dss.conversion.PowerConversionElement#getSpectrum <em>Spectrum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spectrum</em>' attribute.
	 * @see #getSpectrum()
	 * @generated
	 */
	void setSpectrum(String value);

	/**
	 * Returns the value of the '<em><b>Spectrum Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The harmonic spectrum for this device.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Spectrum Obj</em>' reference.
	 * @see #setSpectrumObj(Spectrum)
	 * @see dss.conversion.ConversionPackage#getPowerConversionElement_SpectrumObj()
	 * @model
	 * @generated
	 */
	Spectrum getSpectrumObj();

	/**
	 * Sets the value of the '{@link dss.conversion.PowerConversionElement#getSpectrumObj <em>Spectrum Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spectrum Obj</em>' reference.
	 * @see #getSpectrumObj()
	 * @generated
	 */
	void setSpectrumObj(Spectrum value);

	/**
	 * Returns the value of the '<em><b>Inj Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inj Current</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inj Current</em>' attribute.
	 * @see #setInjCurrent(double)
	 * @see dss.conversion.ConversionPackage#getPowerConversionElement_InjCurrent()
	 * @model
	 * @generated
	 */
	double getInjCurrent();

	/**
	 * Sets the value of the '{@link dss.conversion.PowerConversionElement#getInjCurrent <em>Inj Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inj Current</em>' attribute.
	 * @see #getInjCurrent()
	 * @generated
	 */
	void setInjCurrent(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void recalcElementData();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int injCurrents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model currDataType="dss.DenseDComplexMatrix1D"
	 * @generated
	 */
	void getCurrents(DenseDComplexMatrix1D curr);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model currDataType="dss.DenseDComplexMatrix1D"
	 * @generated
	 */
	void getInjCurrents(DenseDComplexMatrix1D curr);

} // PowerConversionElement
