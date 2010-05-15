/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.general;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Spectrum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Spectrum specified as Harmonic, pct magnitude and angle
 * 
 * Spectrum is shifted by the fundamental angle and stored in MultArray
 * so that the fundamental is at zero degrees phase shift.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.general.Spectrum#getNHarm <em>NHarm</em>}</li>
 *   <li>{@link dss.general.Spectrum#getHarmonic <em>Harmonic</em>}</li>
 *   <li>{@link dss.general.Spectrum#getPctMag <em>Pct Mag</em>}</li>
 *   <li>{@link dss.general.Spectrum#getAngle <em>Angle</em>}</li>
 *   <li>{@link dss.general.Spectrum#getCsvFile <em>Csv File</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.general.GeneralPackage#getSpectrum()
 * @model
 * @generated
 */
public interface Spectrum extends EObject {
	/**
	 * Returns the value of the '<em><b>NHarm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of frequencies in this spectrum. (See csvFile)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NHarm</em>' attribute.
	 * @see #setNHarm(int)
	 * @see dss.general.GeneralPackage#getSpectrum_NHarm()
	 * @model
	 * @generated
	 */
	int getNHarm();

	/**
	 * Sets the value of the '{@link dss.general.Spectrum#getNHarm <em>NHarm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NHarm</em>' attribute.
	 * @see #getNHarm()
	 * @generated
	 */
	void setNHarm(int value);

	/**
	 * Returns the value of the '<em><b>Harmonic</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of harmonic values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Harmonic</em>' attribute list.
	 * @see dss.general.GeneralPackage#getSpectrum_Harmonic()
	 * @model
	 * @generated
	 */
	EList<Double> getHarmonic();

	/**
	 * Returns the value of the '<em><b>Pct Mag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of magnitude values, assumed to be in PERCENT.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Mag</em>' attribute.
	 * @see #setPctMag(double)
	 * @see dss.general.GeneralPackage#getSpectrum_PctMag()
	 * @model
	 * @generated
	 */
	double getPctMag();

	/**
	 * Sets the value of the '{@link dss.general.Spectrum#getPctMag <em>Pct Mag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Mag</em>' attribute.
	 * @see #getPctMag()
	 * @generated
	 */
	void setPctMag(double value);

	/**
	 * Returns the value of the '<em><b>Angle</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of phase angle values, degrees.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Angle</em>' attribute list.
	 * @see dss.general.GeneralPackage#getSpectrum_Angle()
	 * @model
	 * @generated
	 */
	EList<Double> getAngle();

	/**
	 * Returns the value of the '<em><b>Csv File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * File of spectrum points with (harmonic, magnitude-percent, angle-degrees) values, one set of 3 per line, in CSV format. If fewer than NUMHARM frequencies found in the file, NUMHARM is set to the smaller value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Csv File</em>' attribute.
	 * @see #setCsvFile(String)
	 * @see dss.general.GeneralPackage#getSpectrum_CsvFile()
	 * @model
	 * @generated
	 */
	String getCsvFile();

	/**
	 * Sets the value of the '{@link dss.general.Spectrum#getCsvFile <em>Csv File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Csv File</em>' attribute.
	 * @see #getCsvFile()
	 * @generated
	 */
	void setCsvFile(String value);

} // Spectrum
