/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery;

import dss.common.connectionType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Transfomer model is implemented as a multi-terminal (two or more)
 * power delivery element.
 * 
 * A transfomer consists of two or more Windings, connected in somewhat
 * arbitray fashion (with the standard Wye-Delta defaults, of course).  You
 * can specify the parameters of a winding one winding at a time or use arrays
 * to set all the values.  Use the "wdg=..." parameter to select a winding.
 * 
 * Transformers have one or more phases.  The number of conductors per
 * terminal is always one more than the number of phases.  For wye- or
 * star-connected windings, the extra conductor is the neutral point.  For
 * delta-connected windings, the extra terminal is open internally (you
 * normally leave this connected to node 0).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.delivery.Transformer#getWindings <em>Windings</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getWdg <em>Wdg</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getBus <em>Bus</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getConn <em>Conn</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getKV <em>KV</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getKVA <em>KVA</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getTap <em>Tap</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getRPct <em>RPct</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getBuses <em>Buses</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getConns <em>Conns</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getKVs <em>KVs</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getKVAs <em>KV As</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getTaps <em>Taps</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getXHL <em>XHL</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getXHT <em>XHT</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getXLT <em>XLT</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getXSCArray <em>XSC Array</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getThermal <em>Thermal</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getN <em>N</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getM <em>M</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getFLRise <em>FL Rise</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getHSRise <em>HS Rise</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getPctLoadLoss <em>Pct Load Loss</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getPctNoLoadLoss <em>Pct No Load Loss</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getNormHKVa <em>Norm HK Va</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getEmergHKVa <em>Emerg HK Va</em>}</li>
 *   <li>{@link dss.delivery.Transformer#isSubstation <em>Substation</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getMaxTap <em>Max Tap</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getMinTap <em>Min Tap</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getNumTaps <em>Num Taps</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getSubName <em>Sub Name</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getPctImage <em>Pct Image</em>}</li>
 *   <li>{@link dss.delivery.Transformer#getPpmAntiFloat <em>Ppm Anti Float</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.delivery.DeliveryPackage#getTransformer()
 * @model
 * @generated
 */
public interface Transformer extends PowerDeliveryElement {
	/**
	 * Returns the value of the '<em><b>Windings</b></em>' attribute.
	 * The default value is <code>"2"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of windings, this transformers. (Also is the number of terminals)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Windings</em>' attribute.
	 * @see #setWindings(int)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Windings()
	 * @model default="2"
	 * @generated
	 */
	int getWindings();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getWindings <em>Windings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Windings</em>' attribute.
	 * @see #getWindings()
	 * @generated
	 */
	void setWindings(int value);

	/**
	 * Returns the value of the '<em><b>Wdg</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set this = to the number of the winding you wish to define.  Then set the values for this winding.  Repeat for each winding.  Alternatively, use the array collections (buses, kvas, etc.) to define the windings. Note: impedances are BETWEEN pairs of windings; they are not the property of a single winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wdg</em>' attribute.
	 * @see #setWdg(int)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Wdg()
	 * @model default="1"
	 * @generated
	 */
	int getWdg();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getWdg <em>Wdg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wdg</em>' attribute.
	 * @see #getWdg()
	 * @generated
	 */
	void setWdg(int value);

	/**
	 * Returns the value of the '<em><b>Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bus to which this winding is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus</em>' attribute.
	 * @see #setBus(String)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Bus()
	 * @model
	 * @generated
	 */
	String getBus();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getBus <em>Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus</em>' attribute.
	 * @see #getBus()
	 * @generated
	 */
	void setBus(String value);

	/**
	 * Returns the value of the '<em><b>Conn</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection of this winding. Default is "wye" with the neutral solidly grounded.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see dss.common.connectionType
	 * @see #setConn(connectionType)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Conn()
	 * @model
	 * @generated
	 */
	connectionType getConn();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see dss.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
	void setConn(connectionType value);

	/**
	 * Returns the value of the '<em><b>KV</b></em>' attribute.
	 * The default value is <code>"12.47"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For 2-or 3-phase, enter phase-phase kV rating.  Otherwise, kV rating of the actual winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_KV()
	 * @model default="12.47"
	 * @generated
	 */
	double getKV();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getKV <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV</em>' attribute.
	 * @see #getKV()
	 * @generated
	 */
	void setKV(double value);

	/**
	 * Returns the value of the '<em><b>KVA</b></em>' attribute.
	 * The default value is <code>"1000.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base kVA rating of the winding. Side effect: forces change of max normal and emerg kva ratings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA</em>' attribute.
	 * @see #setKVA(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_KVA()
	 * @model default="1000.0"
	 * @generated
	 */
	double getKVA();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getKVA <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA</em>' attribute.
	 * @see #getKVA()
	 * @generated
	 */
	void setKVA(double value);

	/**
	 * Returns the value of the '<em><b>Tap</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit tap that this winding is on.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tap</em>' attribute.
	 * @see #setTap(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Tap()
	 * @model default="1.0"
	 * @generated
	 */
	double getTap();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getTap <em>Tap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tap</em>' attribute.
	 * @see #getTap()
	 * @generated
	 */
	void setTap(double value);

	/**
	 * Returns the value of the '<em><b>RPct</b></em>' attribute.
	 * The default value is <code>"0.2"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent resistance this winding.  (half of total for a 2-winding).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RPct</em>' attribute.
	 * @see #setRPct(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_RPct()
	 * @model default="0.2"
	 * @generated
	 */
	double getRPct();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getRPct <em>RPct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RPct</em>' attribute.
	 * @see #getRPct()
	 * @generated
	 */
	void setRPct(double value);

	/**
	 * Returns the value of the '<em><b>RNeut</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Neutral resistance of wye (star)-connected winding in actual ohms. If entered as a negative value, the neutral is assumed to be open, or floating.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RNeut</em>' attribute.
	 * @see #setRNeut(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_RNeut()
	 * @model
	 * @generated
	 */
	double getRNeut();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getRNeut <em>RNeut</em>}' attribute.
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
	 * Neutral reactance of wye(star)-connected winding in actual ohms. May be + or -.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XNeut</em>' attribute.
	 * @see #setXNeut(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_XNeut()
	 * @model
	 * @generated
	 */
	double getXNeut();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getXNeut <em>XNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XNeut</em>' attribute.
	 * @see #getXNeut()
	 * @generated
	 */
	void setXNeut(double value);

	/**
	 * Returns the value of the '<em><b>Buses</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify all the bus connections at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Buses</em>' attribute list.
	 * @see dss.delivery.DeliveryPackage#getTransformer_Buses()
	 * @model
	 * @generated
	 */
	EList<String> getBuses();

	/**
	 * Returns the value of the '<em><b>Conns</b></em>' attribute list.
	 * The list contents are of type {@link dss.common.connectionType}.
	 * The literals are from the enumeration {@link dss.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify all the Winding connections at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conns</em>' attribute list.
	 * @see dss.common.connectionType
	 * @see dss.delivery.DeliveryPackage#getTransformer_Conns()
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
	 * @see dss.delivery.DeliveryPackage#getTransformer_KVs()
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
	 * @see dss.delivery.DeliveryPackage#getTransformer_KVAs()
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
	 * Use this to specify the p.u. tap of all windings at once using an array.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Taps</em>' attribute list.
	 * @see dss.delivery.DeliveryPackage#getTransformer_Taps()
	 * @model
	 * @generated
	 */
	EList<Double> getTaps();

	/**
	 * Returns the value of the '<em><b>XHL</b></em>' attribute.
	 * The default value is <code>"7.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance, H-L (winding 1 to winding 2).  Use for 2- or 3-winding transformers. On the kva base of winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XHL</em>' attribute.
	 * @see #setXHL(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_XHL()
	 * @model default="7.0"
	 * @generated
	 */
	double getXHL();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getXHL <em>XHL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XHL</em>' attribute.
	 * @see #getXHL()
	 * @generated
	 */
	void setXHL(double value);

	/**
	 * Returns the value of the '<em><b>XHT</b></em>' attribute.
	 * The default value is <code>"35.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance, H-T (winding 1 to winding 3).  Use for 3-winding transformers only. On the kVA base of winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XHT</em>' attribute.
	 * @see #setXHT(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_XHT()
	 * @model default="35.0"
	 * @generated
	 */
	double getXHT();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getXHT <em>XHT</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XHT</em>' attribute.
	 * @see #getXHT()
	 * @generated
	 */
	void setXHT(double value);

	/**
	 * Returns the value of the '<em><b>XLT</b></em>' attribute.
	 * The default value is <code>"30.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance, L-T (winding 2 to winding 3).  Use for 3-winding transformers only. On the kVA base of winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XLT</em>' attribute.
	 * @see #setXLT(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_XLT()
	 * @model default="30.0"
	 * @generated
	 */
	double getXLT();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getXLT <em>XLT</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XLT</em>' attribute.
	 * @see #getXLT()
	 * @generated
	 */
	void setXLT(double value);

	/**
	 * Returns the value of the '<em><b>XSC Array</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify the percent reactance between all pairs of windings as an array.  All values are on the kVA base of winding 1.  The order of the values is as follows:
	 *   (x12 13 14... 23 24.. 34 ..)
	 * There will be n(n-1)/2 values, where n=number of windings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XSC Array</em>' attribute list.
	 * @see dss.delivery.DeliveryPackage#getTransformer_XSCArray()
	 * @model
	 * @generated
	 */
	EList<Double> getXSCArray();

	/**
	 * Returns the value of the '<em><b>Thermal</b></em>' attribute.
	 * The default value is <code>"2.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thermal time constant of the transformer in hours.  Typically about 2.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Thermal</em>' attribute.
	 * @see #setThermal(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Thermal()
	 * @model default="2.0"
	 * @generated
	 */
	double getThermal();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getThermal <em>Thermal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thermal</em>' attribute.
	 * @see #getThermal()
	 * @generated
	 */
	void setThermal(double value);

	/**
	 * Returns the value of the '<em><b>N</b></em>' attribute.
	 * The default value is <code>"0.8"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * n Exponent for thermal properties in IEEE C57.  Typically 0.8.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>N</em>' attribute.
	 * @see #setN(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_N()
	 * @model default="0.8"
	 * @generated
	 */
	double getN();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getN <em>N</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>N</em>' attribute.
	 * @see #getN()
	 * @generated
	 */
	void setN(double value);

	/**
	 * Returns the value of the '<em><b>M</b></em>' attribute.
	 * The default value is <code>"0.8"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * m Exponent for thermal properties in IEEE C57.  Typically 0.9 - 1.0
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>M</em>' attribute.
	 * @see #setM(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_M()
	 * @model default="0.8"
	 * @generated
	 */
	double getM();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getM <em>M</em>}' attribute.
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
	 * @see dss.delivery.DeliveryPackage#getTransformer_FLRise()
	 * @model default="65.0"
	 * @generated
	 */
	double getFLRise();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getFLRise <em>FL Rise</em>}' attribute.
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
	 * @see dss.delivery.DeliveryPackage#getTransformer_HSRise()
	 * @model default="15.0"
	 * @generated
	 */
	double getHSRise();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getHSRise <em>HS Rise</em>}' attribute.
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
	 * Percent load loss at full load. The %R of the High and Low windings (1 and 2) are adjusted to agree at rated kVA loading.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Load Loss</em>' attribute.
	 * @see #setPctLoadLoss(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_PctLoadLoss()
	 * @model
	 * @generated
	 */
	double getPctLoadLoss();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getPctLoadLoss <em>Pct Load Loss</em>}' attribute.
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
	 * Percent no load losses at rated excitation voltage. Converts to a resistance in parallel with the magnetising impedance in each winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct No Load Loss</em>' attribute.
	 * @see #setPctNoLoadLoss(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_PctNoLoadLoss()
	 * @model
	 * @generated
	 */
	double getPctNoLoadLoss();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getPctNoLoadLoss <em>Pct No Load Loss</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct No Load Loss</em>' attribute.
	 * @see #getPctNoLoadLoss()
	 * @generated
	 */
	void setPctNoLoadLoss(double value);

	/**
	 * Returns the value of the '<em><b>Norm HK Va</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Normal maximum kVA rating of H winding (winding 1).  Usually 100% - 110% of maximum nameplate rating, depending on load shape. Defaults to 110% of kVA rating of Winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm HK Va</em>' attribute.
	 * @see #setNormHKVa(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_NormHKVa()
	 * @model
	 * @generated
	 */
	double getNormHKVa();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getNormHKVa <em>Norm HK Va</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Norm HK Va</em>' attribute.
	 * @see #getNormHKVa()
	 * @generated
	 */
	void setNormHKVa(double value);

	/**
	 * Returns the value of the '<em><b>Emerg HK Va</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Emergency (contingency)  kVA rating of H winding (winding 1).  Usually 140% - 150% of maximum nameplate rating, depending on load shape. Defaults to 150% of kVA rating of Winding 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg HK Va</em>' attribute.
	 * @see #setEmergHKVa(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_EmergHKVa()
	 * @model
	 * @generated
	 */
	double getEmergHKVa();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getEmergHKVa <em>Emerg HK Va</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg HK Va</em>' attribute.
	 * @see #getEmergHKVa()
	 * @generated
	 */
	void setEmergHKVa(double value);

	/**
	 * Returns the value of the '<em><b>Substation</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Designates whether this transformer is to be considered a substation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Substation</em>' attribute.
	 * @see #setSubstation(boolean)
	 * @see dss.delivery.DeliveryPackage#getTransformer_Substation()
	 * @model default="false"
	 * @generated
	 */
	boolean isSubstation();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#isSubstation <em>Substation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Substation</em>' attribute.
	 * @see #isSubstation()
	 * @generated
	 */
	void setSubstation(boolean value);

	/**
	 * Returns the value of the '<em><b>Max Tap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Max per unit tap for the active winding.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Tap</em>' attribute.
	 * @see #setMaxTap(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_MaxTap()
	 * @model
	 * @generated
	 */
	double getMaxTap();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getMaxTap <em>Max Tap</em>}' attribute.
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
	 * @see dss.delivery.DeliveryPackage#getTransformer_MinTap()
	 * @model
	 * @generated
	 */
	double getMinTap();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getMinTap <em>Min Tap</em>}' attribute.
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
	 * @see dss.delivery.DeliveryPackage#getTransformer_NumTaps()
	 * @model default="32"
	 * @generated
	 */
	int getNumTaps();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getNumTaps <em>Num Taps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Taps</em>' attribute.
	 * @see #getNumTaps()
	 * @generated
	 */
	void setNumTaps(int value);

	/**
	 * Returns the value of the '<em><b>Sub Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Substation Name. Optional. If specified, printed on plots.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sub Name</em>' attribute.
	 * @see #setSubName(String)
	 * @see dss.delivery.DeliveryPackage#getTransformer_SubName()
	 * @model
	 * @generated
	 */
	String getSubName();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getSubName <em>Sub Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Name</em>' attribute.
	 * @see #getSubName()
	 * @generated
	 */
	void setSubName(String value);

	/**
	 * Returns the value of the '<em><b>Pct Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent magnetizing current. Default=0.0. Magnetizing branch is in parallel with windings in each phase. Also, see "ppm_antifloat".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Image</em>' attribute.
	 * @see #setPctImage(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_PctImage()
	 * @model
	 * @generated
	 */
	double getPctImage();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getPctImage <em>Pct Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Image</em>' attribute.
	 * @see #getPctImage()
	 * @generated
	 */
	void setPctImage(double value);

	/**
	 * Returns the value of the '<em><b>Ppm Anti Float</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parts per million by which the reactive term is increased to protect against accidentally floating a winding.  If positive then the effect is adding a small reactor to ground. If negative, then a capacitor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ppm Anti Float</em>' attribute.
	 * @see #setPpmAntiFloat(double)
	 * @see dss.delivery.DeliveryPackage#getTransformer_PpmAntiFloat()
	 * @model default="1.0"
	 * @generated
	 */
	double getPpmAntiFloat();

	/**
	 * Sets the value of the '{@link dss.delivery.Transformer#getPpmAntiFloat <em>Ppm Anti Float</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ppm Anti Float</em>' attribute.
	 * @see #getPpmAntiFloat()
	 * @generated
	 */
	void setPpmAntiFloat(double value);

} // Transformer
