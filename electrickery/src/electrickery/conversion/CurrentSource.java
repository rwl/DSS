/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Current Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Ideal current source.
 * 
 * ISource maintains a positive sequence for harmonic scans.  If you want zero
 * sequence, use three single-phase ISource.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.conversion.CurrentSource#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.CurrentSource#getAmps <em>Amps</em>}</li>
 *   <li>{@link electrickery.conversion.CurrentSource#getAngle <em>Angle</em>}</li>
 *   <li>{@link electrickery.conversion.CurrentSource#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.conversion.CurrentSource#getPhases <em>Phases</em>}</li>
 *   <li>{@link electrickery.conversion.CurrentSource#getScanType <em>Scan Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.conversion.ConversionPackage#getCurrentSource()
 * @model
 * @generated
 */
public interface CurrentSource extends PowerConversionElement {
	/**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of bus to which source is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.conversion.ConversionPackage#getCurrentSource_Bus1()
	 * @model
	 * @generated
	 */
	String getBus1();

	/**
	 * Sets the value of the '{@link electrickery.conversion.CurrentSource#getBus1 <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus1</em>' attribute.
	 * @see #getBus1()
	 * @generated
	 */
	void setBus1(String value);

	/**
	 * Returns the value of the '<em><b>Amps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Magnitude of current source, each phase, in Amps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Amps</em>' attribute.
	 * @see #setAmps(double)
	 * @see electrickery.conversion.ConversionPackage#getCurrentSource_Amps()
	 * @model
	 * @generated
	 */
	double getAmps();

	/**
	 * Sets the value of the '{@link electrickery.conversion.CurrentSource#getAmps <em>Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Amps</em>' attribute.
	 * @see #getAmps()
	 * @generated
	 */
	void setAmps(double value);

	/**
	 * Returns the value of the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Phase angle in degrees of first phase. Phase shift between phases is
	 * assumed 120 degrees when number of phases <= 3.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Angle</em>' attribute.
	 * @see #setAngle(double)
	 * @see electrickery.conversion.ConversionPackage#getCurrentSource_Angle()
	 * @model
	 * @generated
	 */
	double getAngle();

	/**
	 * Sets the value of the '{@link electrickery.conversion.CurrentSource#getAngle <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Angle</em>' attribute.
	 * @see #getAngle()
	 * @generated
	 */
	void setAngle(double value);

	/**
	 * Returns the value of the '<em><b>Frequency</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Source frequency.  Defaults to circuit fundamental frequency.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Frequency</em>' attribute.
	 * @see #setFrequency(double)
	 * @see electrickery.conversion.ConversionPackage#getCurrentSource_Frequency()
	 * @model default="60.0"
	 * @generated
	 */
	double getFrequency();

	/**
	 * Sets the value of the '{@link electrickery.conversion.CurrentSource#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
	void setFrequency(double value);

	/**
	 * Returns the value of the '<em><b>Phases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of phases. For 3 or less, phase shift is 120 degrees.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phases</em>' attribute.
	 * @see #setPhases(int)
	 * @see electrickery.conversion.ConversionPackage#getCurrentSource_Phases()
	 * @model
	 * @generated
	 */
	int getPhases();

	/**
	 * Sets the value of the '{@link electrickery.conversion.CurrentSource#getPhases <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phases</em>' attribute.
	 * @see #getPhases()
	 * @generated
	 */
	void setPhases(int value);

	/**
	 * Returns the value of the '<em><b>Scan Type</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.sequenceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maintain specified sequence for harmonic solution.  Otherwise, angle
	 * between phases rotates with harmonic.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scan Type</em>' attribute.
	 * @see electrickery.conversion.sequenceType
	 * @see #setScanType(sequenceType)
	 * @see electrickery.conversion.ConversionPackage#getCurrentSource_ScanType()
	 * @model
	 * @generated
	 */
	sequenceType getScanType();

	/**
	 * Sets the value of the '{@link electrickery.conversion.CurrentSource#getScanType <em>Scan Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scan Type</em>' attribute.
	 * @see electrickery.conversion.sequenceType
	 * @see #getScanType()
	 * @generated
	 */
	void setScanType(sequenceType value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" lower="2" upper="2"
	 * @generated
	 */
	EList<Double> getBaseCurrent();

} // CurrentSource
