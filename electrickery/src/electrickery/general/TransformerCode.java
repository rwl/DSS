/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general;

import electrickery.common.connectionType;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformer Code</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.general.TransformerCode#getPhases <em>Phases</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getWindings <em>Windings</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getWdg <em>Wdg</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getTap <em>Tap</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getPctR <em>Pct R</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getConns <em>Conns</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getKVs <em>KVs</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getKVAs <em>KV As</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getTaps <em>Taps</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getXhl <em>Xhl</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getXht <em>Xht</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getXlt <em>Xlt</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getXscArray <em>Xsc Array</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getThermal <em>Thermal</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getN <em>N</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getM <em>M</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getFLRise <em>FL Rise</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getHSRise <em>HS Rise</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getPctLoadLoss <em>Pct Load Loss</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getPctNoLoadLoss <em>Pct No Load Loss</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getNormHKVA <em>Norm HKVA</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getEmergHKVA <em>Emerg HKVA</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getMaxTap <em>Max Tap</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getMinTap <em>Min Tap</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getNumTaps <em>Num Taps</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getPctIMag <em>Pct IMag</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getPpmAntiFloat <em>Ppm Anti Float</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getPctRS <em>Pct RS</em>}</li>
 *   <li>{@link electrickery.general.TransformerCode#getLike <em>Like</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.general.GeneralPackage#getTransformerCode()
 * @model
 * @generated
 */
public interface TransformerCode extends EObject {
	/**
	 * Returns the value of the '<em><b>Phases</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of phases for this transformer.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phases</em>' attribute.
	 * @see #setPhases(int)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Phases()
	 * @model default="3"
	 * @generated
	 */
	int getPhases();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPhases <em>Phases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phases</em>' attribute.
	 * @see #getPhases()
	 * @generated
	 */
	void setPhases(int value);

	/**
	 * Returns the value of the '<em><b>Windings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of windings, this transformers (Also is the number of terminals).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Windings</em>' attribute.
	 * @see #setWindings(int)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Windings()
	 * @model
	 * @generated
	 */
	int getWindings();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getWindings <em>Windings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Windings</em>' attribute.
	 * @see #getWindings()
	 * @generated
	 */
	void setWindings(int value);

	/**
	 * Returns the value of the '<em><b>Wdg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set this = to the number of the winding you wish to define.  Then set the
	 * values for this winding.  Repeat for each winding.  Alternatively, use the array
	 * collections (buses, kvas, etc.) to define the windings.  Note: reactances are
	 * BETWEEN pairs of windings; they are not the property of a single winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wdg</em>' attribute.
	 * @see #setWdg(int)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Wdg()
	 * @model
	 * @generated
	 */
	int getWdg();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getWdg <em>Wdg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wdg</em>' attribute.
	 * @see #getWdg()
	 * @generated
	 */
	void setWdg(int value);

	/**
	 * Returns the value of the '<em><b>Conn</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection of this winding. Default is "wye" with the neutral solidly grounded.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #setConn(connectionType)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Conn()
	 * @model
	 * @generated
	 */
	connectionType getConn();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
	void setConn(connectionType value);

	/**
	 * Returns the value of the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the
	 * actual winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_KV()
	 * @model
	 * @generated
	 */
	double getKV();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getKV <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV</em>' attribute.
	 * @see #getKV()
	 * @generated
	 */
	void setKV(double value);

	/**
	 * Returns the value of the '<em><b>KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base kVA rating of the winding. Side effect: forces change of max normal and
	 * emerg kva ratings.If 2-winding transformer, forces other winding to same value.
	 * When winding 1 is defined, all other windings are defaulted to the same rating
	 * and the first two winding resistances are defaulted to the pctLoadLoss value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA</em>' attribute.
	 * @see #setKVA(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_KVA()
	 * @model
	 * @generated
	 */
	double getKVA();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getKVA <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA</em>' attribute.
	 * @see #getKVA()
	 * @generated
	 */
	void setKVA(double value);

	/**
	 * Returns the value of the '<em><b>Tap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit tap that this winding is normally on.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tap</em>' attribute.
	 * @see #setTap(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Tap()
	 * @model
	 * @generated
	 */
	double getTap();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getTap <em>Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tap</em>' attribute.
	 * @see #getTap()
	 * @generated
	 */
	void setTap(double value);

	/**
	 * Returns the value of the '<em><b>Pct R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent resistance this winding.  (half of total for a 2-winding).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct R</em>' attribute.
	 * @see #setPctR(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_PctR()
	 * @model
	 * @generated
	 */
	double getPctR();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPctR <em>Pct R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct R</em>' attribute.
	 * @see #getPctR()
	 * @generated
	 */
	void setPctR(double value);

	/**
	 * Returns the value of the '<em><b>RNeut</b></em>' attribute.
	 * The default value is <code>"-1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Neutral resistance of wye (star)-connected winding in actual ohms. If entered
	 * as a negative value, the neutral is assumed to be open, or floating.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RNeut</em>' attribute.
	 * @see #setRNeut(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_RNeut()
	 * @model default="-1.0"
	 * @generated
	 */
	double getRNeut();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getRNeut <em>RNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RNeut</em>' attribute.
	 * @see #getRNeut()
	 * @generated
	 */
	void setRNeut(double value);

	/**
	 * Returns the value of the '<em><b>XNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Neutral reactance of wye(star)-connected winding in actual ohms.  May be + or -.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XNeut</em>' attribute.
	 * @see #setXNeut(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_XNeut()
	 * @model
	 * @generated
	 */
	double getXNeut();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getXNeut <em>XNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XNeut</em>' attribute.
	 * @see #getXNeut()
	 * @generated
	 */
	void setXNeut(double value);

	/**
	 * Returns the value of the '<em><b>Conns</b></em>' attribute list.
	 * The list contents are of type {@link electrickery.common.connectionType}.
	 * The literals are from the enumeration {@link electrickery.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify all the Winding connections at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conns</em>' attribute list.
	 * @see electrickery.common.connectionType
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Conns()
	 * @model
	 * @generated
	 */
	EList<connectionType> getConns();

	/**
	 * Returns the value of the '<em><b>KVs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the kV ratings of all windings at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVs</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getTransformerCode_KVs()
	 * @model
	 * @generated
	 */
	EList<Double> getKVs();

	/**
	 * Returns the value of the '<em><b>KV As</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the kVA ratings of all windings at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV As</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getTransformerCode_KVAs()
	 * @model
	 * @generated
	 */
	EList<Double> getKVAs();

	/**
	 * Returns the value of the '<em><b>Taps</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the normal p.u. tap of all windings at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Taps</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Taps()
	 * @model
	 * @generated
	 */
	EList<Double> getTaps();

	/**
	 * Returns the value of the '<em><b>Xhl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use
	 * for 2- or 3-winding transformers. On the kva base of winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xhl</em>' attribute.
	 * @see #setXhl(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Xhl()
	 * @model
	 * @generated
	 */
	double getXhl();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getXhl <em>Xhl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xhl</em>' attribute.
	 * @see #getXhl()
	 * @generated
	 */
	void setXhl(double value);

	/**
	 * Returns the value of the '<em><b>Xht</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use
	 * for 3-winding transformers only. On the kVA base of winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xht</em>' attribute.
	 * @see #setXht(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Xht()
	 * @model
	 * @generated
	 */
	double getXht();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getXht <em>Xht</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xht</em>' attribute.
	 * @see #getXht()
	 * @generated
	 */
	void setXht(double value);

	/**
	 * Returns the value of the '<em><b>Xlt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use
	 * for 3-winding transformers only. On the kVA base of winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xlt</em>' attribute.
	 * @see #setXlt(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Xlt()
	 * @model
	 * @generated
	 */
	double getXlt();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getXlt <em>Xlt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xlt</em>' attribute.
	 * @see #getXlt()
	 * @generated
	 */
	void setXlt(double value);

	/**
	 * Returns the value of the '<em><b>Xsc Array</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance between all pairs of windings as an
	 * array. All values are on the kVA base of winding 1.  The order of the values is as
	 * follows:(x12 13 14... 23 24.. 34 ..)  There will be n(n-1)/2 values, where n=number
	 * of windings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xsc Array</em>' attribute list.
	 * @see electrickery.general.GeneralPackage#getTransformerCode_XscArray()
	 * @model
	 * @generated
	 */
	EList<Double> getXscArray();

	/**
	 * Returns the value of the '<em><b>Thermal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thermal time constant of the transformer in hours.  Typically about 2.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Thermal</em>' attribute.
	 * @see #setThermal(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Thermal()
	 * @model
	 * @generated
	 */
	double getThermal();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getThermal <em>Thermal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thermal</em>' attribute.
	 * @see #getThermal()
	 * @generated
	 */
	void setThermal(double value);

	/**
	 * Returns the value of the '<em><b>N</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 'n' exponent for thermal properties in IEEE C57.  Typically 0.8.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>N</em>' attribute.
	 * @see #setN(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_N()
	 * @model
	 * @generated
	 */
	double getN();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getN <em>N</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>N</em>' attribute.
	 * @see #getN()
	 * @generated
	 */
	void setN(double value);

	/**
	 * Returns the value of the '<em><b>M</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 'm' exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>M</em>' attribute.
	 * @see #setM(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_M()
	 * @model
	 * @generated
	 */
	double getM();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getM <em>M</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>M</em>' attribute.
	 * @see #getM()
	 * @generated
	 */
	void setM(double value);

	/**
	 * Returns the value of the '<em><b>FL Rise</b></em>' attribute.
	 * The default value is <code>"65.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Temperature rise, deg C, for full load.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>FL Rise</em>' attribute.
	 * @see #setFLRise(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_FLRise()
	 * @model default="65.0"
	 * @generated
	 */
	double getFLRise();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getFLRise <em>FL Rise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FL Rise</em>' attribute.
	 * @see #getFLRise()
	 * @generated
	 */
	void setFLRise(double value);

	/**
	 * Returns the value of the '<em><b>HS Rise</b></em>' attribute.
	 * The default value is <code>"15.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hot spot temperature rise, deg C.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>HS Rise</em>' attribute.
	 * @see #setHSRise(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_HSRise()
	 * @model default="15.0"
	 * @generated
	 */
	double getHSRise();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getHSRise <em>HS Rise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>HS Rise</em>' attribute.
	 * @see #getHSRise()
	 * @generated
	 */
	void setHSRise(double value);

	/**
	 * Returns the value of the '<em><b>Pct Load Loss</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent load loss at full load. The %R of the High and Low windings (1 and 2)
	 * are adjusted to agree at rated kVA loading.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Load Loss</em>' attribute.
	 * @see #setPctLoadLoss(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_PctLoadLoss()
	 * @model
	 * @generated
	 */
	double getPctLoadLoss();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPctLoadLoss <em>Pct Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Load Loss</em>' attribute.
	 * @see #getPctLoadLoss()
	 * @generated
	 */
	void setPctLoadLoss(double value);

	/**
	 * Returns the value of the '<em><b>Pct No Load Loss</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent no load losses at rated excitatation voltage. Default is 0. Converts
	 * to a resistance in parallel with the magnetizing impedance in each winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct No Load Loss</em>' attribute.
	 * @see #setPctNoLoadLoss(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_PctNoLoadLoss()
	 * @model
	 * @generated
	 */
	double getPctNoLoadLoss();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPctNoLoadLoss <em>Pct No Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct No Load Loss</em>' attribute.
	 * @see #getPctNoLoadLoss()
	 * @generated
	 */
	void setPctNoLoadLoss(double value);

	/**
	 * Returns the value of the '<em><b>Norm HKVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of maximum
	 * nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm HKVA</em>' attribute.
	 * @see #setNormHKVA(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_NormHKVA()
	 * @model
	 * @generated
	 */
	double getNormHKVA();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getNormHKVA <em>Norm HKVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Norm HKVA</em>' attribute.
	 * @see #getNormHKVA()
	 * @generated
	 */
	void setNormHKVA(double value);

	/**
	 * Returns the value of the '<em><b>Emerg HKVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of
	 * maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating
	 * of Winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg HKVA</em>' attribute.
	 * @see #setEmergHKVA(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_EmergHKVA()
	 * @model
	 * @generated
	 */
	double getEmergHKVA();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getEmergHKVA <em>Emerg HKVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg HKVA</em>' attribute.
	 * @see #getEmergHKVA()
	 * @generated
	 */
	void setEmergHKVA(double value);

	/**
	 * Returns the value of the '<em><b>Max Tap</b></em>' attribute.
	 * The default value is <code>"1.1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Max per unit tap for the active winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Tap</em>' attribute.
	 * @see #setMaxTap(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_MaxTap()
	 * @model default="1.1"
	 * @generated
	 */
	double getMaxTap();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getMaxTap <em>Max Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Tap</em>' attribute.
	 * @see #getMaxTap()
	 * @generated
	 */
	void setMaxTap(double value);

	/**
	 * Returns the value of the '<em><b>Min Tap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Min per unit tap for the active winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min Tap</em>' attribute.
	 * @see #setMinTap(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_MinTap()
	 * @model
	 * @generated
	 */
	double getMinTap();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getMinTap <em>Min Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Tap</em>' attribute.
	 * @see #getMinTap()
	 * @generated
	 */
	void setMinTap(double value);

	/**
	 * Returns the value of the '<em><b>Num Taps</b></em>' attribute.
	 * The default value is <code>"32"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total number of taps between min and max tap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Num Taps</em>' attribute.
	 * @see #setNumTaps(int)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_NumTaps()
	 * @model default="32"
	 * @generated
	 */
	int getNumTaps();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getNumTaps <em>Num Taps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Taps</em>' attribute.
	 * @see #getNumTaps()
	 * @generated
	 */
	void setNumTaps(int value);

	/**
	 * Returns the value of the '<em><b>Pct IMag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel
	 * with windings in each phase. Also, see "ppm_antifloat".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct IMag</em>' attribute.
	 * @see #setPctIMag(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_PctIMag()
	 * @model
	 * @generated
	 */
	double getPctIMag();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPctIMag <em>Pct IMag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct IMag</em>' attribute.
	 * @see #getPctIMag()
	 * @generated
	 */
	void setPctIMag(double value);

	/**
	 * Returns the value of the '<em><b>Ppm Anti Float</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parts per million of transformer winding VA rating connected to ground to protect
	 * against accidentally floating a winding without a reference. If positive then the
	 * effect is adding a very large reactance to ground.  If negative, then a capacitor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ppm Anti Float</em>' attribute.
	 * @see #setPpmAntiFloat(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_PpmAntiFloat()
	 * @model default="1.0"
	 * @generated
	 */
	double getPpmAntiFloat();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPpmAntiFloat <em>Ppm Anti Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ppm Anti Float</em>' attribute.
	 * @see #getPpmAntiFloat()
	 * @generated
	 */
	void setPpmAntiFloat(double value);

	/**
	 * Returns the value of the '<em><b>Pct RS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this property to specify all the winding %resistances using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct RS</em>' attribute.
	 * @see #setPctRS(double)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_PctRS()
	 * @model
	 * @generated
	 */
	double getPctRS();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getPctRS <em>Pct RS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct RS</em>' attribute.
	 * @see #getPctRS()
	 * @generated
	 */
	void setPctRS(double value);

	/**
	 * Returns the value of the '<em><b>Like</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Like</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Like</em>' reference.
	 * @see #setLike(TransformerCode)
	 * @see electrickery.general.GeneralPackage#getTransformerCode_Like()
	 * @model
	 * @generated
	 */
	TransformerCode getLike();

	/**
	 * Sets the value of the '{@link electrickery.general.TransformerCode#getLike <em>Like</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Like</em>' reference.
	 * @see #getLike()
	 * @generated
	 */
	void setLike(TransformerCode value);

} // TransformerCode
