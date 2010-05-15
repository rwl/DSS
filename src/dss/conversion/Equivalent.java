/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.conversion;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equivalent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Multi terminal, multi-phase Short Circuit (Thevinen) Equivalent
 * 
 * Enter positive and zero short circuit impedance matrices and Voltage behind
 * the equivalent.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.conversion.Equivalent#getBuses <em>Buses</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getBaseKV <em>Base KV</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getPu <em>Pu</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getAngle <em>Angle</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getPhases <em>Phases</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getR1 <em>R1</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getX1 <em>X1</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getR0 <em>R0</em>}</li>
 *   <li>{@link dss.conversion.Equivalent#getX0 <em>X0</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.conversion.ConversionPackage#getEquivalent()
 * @model
 * @generated
 */
public interface Equivalent extends PowerConversionElement {
	/**
	 * Returns the value of the '<em><b>Buses</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of Bus Names to which equivalent source is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Buses</em>' attribute list.
	 * @see dss.conversion.ConversionPackage#getEquivalent_Buses()
	 * @model
	 * @generated
	 */
	EList<String> getBuses();

	/**
	 * Returns the value of the '<em><b>Base KV</b></em>' attribute.
	 * The default value is <code>"115.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base Source kV, usually L-L unless you are making a positive-sequence model in which case, it will be L-N.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base KV</em>' attribute.
	 * @see #setBaseKV(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_BaseKV()
	 * @model default="115.0"
	 * @generated
	 */
	double getBaseKV();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getBaseKV <em>Base KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base KV</em>' attribute.
	 * @see #getBaseKV()
	 * @generated
	 */
	void setBaseKV(double value);

	/**
	 * Returns the value of the '<em><b>Pu</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit of the base voltage that the source is actually operating at.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pu</em>' attribute.
	 * @see #setPu(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_Pu()
	 * @model default="1.0"
	 * @generated
	 */
	double getPu();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getPu <em>Pu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pu</em>' attribute.
	 * @see #getPu()
	 * @generated
	 */
	void setPu(double value);

	/**
	 * Returns the value of the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Phase angle in degrees of first phase.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Angle</em>' attribute.
	 * @see #setAngle(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_Angle()
	 * @model
	 * @generated
	 */
	double getAngle();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getAngle <em>Angle</em>}' attribute.
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
	 * Source frequency.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Frequency</em>' attribute.
	 * @see #setFrequency(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_Frequency()
	 * @model default="60.0"
	 * @generated
	 */
	double getFrequency();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
	void setFrequency(double value);

	/**
	 * Returns the value of the '<em><b>Phases</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of phases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phases</em>' attribute.
	 * @see #setPhases(int)
	 * @see dss.conversion.ConversionPackage#getEquivalent_Phases()
	 * @model default="3"
	 * @generated
	 */
	int getPhases();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getPhases <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phases</em>' attribute.
	 * @see #getPhases()
	 * @generated
	 */
	void setPhases(int value);

	/**
	 * Returns the value of the '<em><b>R1</b></em>' attribute.
	 * The default value is <code>"1.65"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive-sequence resistance matrix, lower triangle.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R1</em>' attribute.
	 * @see #setR1(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_R1()
	 * @model default="1.65"
	 * @generated
	 */
	double getR1();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getR1 <em>R1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R1</em>' attribute.
	 * @see #getR1()
	 * @generated
	 */
	void setR1(double value);

	/**
	 * Returns the value of the '<em><b>X1</b></em>' attribute.
	 * The default value is <code>"6.6"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive-sequence reactance matrix, lower triangle.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X1</em>' attribute.
	 * @see #setX1(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_X1()
	 * @model default="6.6"
	 * @generated
	 */
	double getX1();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getX1 <em>X1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X1</em>' attribute.
	 * @see #getX1()
	 * @generated
	 */
	void setX1(double value);

	/**
	 * Returns the value of the '<em><b>R0</b></em>' attribute.
	 * The default value is <code>"1.9"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Zero-sequence resistance matrix, lower triangle.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R0</em>' attribute.
	 * @see #setR0(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_R0()
	 * @model default="1.9"
	 * @generated
	 */
	double getR0();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getR0 <em>R0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R0</em>' attribute.
	 * @see #getR0()
	 * @generated
	 */
	void setR0(double value);

	/**
	 * Returns the value of the '<em><b>X0</b></em>' attribute.
	 * The default value is <code>"5.7"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Zero-sequence reactance matrix, lower triangle.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X0</em>' attribute.
	 * @see #setX0(double)
	 * @see dss.conversion.ConversionPackage#getEquivalent_X0()
	 * @model default="5.7"
	 * @generated
	 */
	double getX0();

	/**
	 * Sets the value of the '{@link dss.conversion.Equivalent#getX0 <em>X0</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X0</em>' attribute.
	 * @see #getX0()
	 * @generated
	 */
	void setX0(double value);

} // Equivalent
