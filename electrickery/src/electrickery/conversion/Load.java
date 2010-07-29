/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.conversion;

import electrickery.common.Circuit;
import electrickery.common.connectionType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Load</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The load is assumed balanced over the no. of phases defined.  To model
 * unbalanced loads, define separate single-phase loads.
 * 
 * If you do not specify load shapes defaults are:
 *     Yearly:  Defaults to No variation or Daily when Daily is defined
 *     Daily:   Defaults to No variation  (i.e. multiplier = 1.0 always)
 *     Dutycycle: Defaults to Daily shape
 *     Growth: Circuit default growth factor
 * 
 * 
 * A Load is a complicated Power Conversion element that is at the heart of
 * many analyses.  It is basically defined by its nominal kW and PF or its kW
 * and kvar.  Then it may be modified by a number of multipliers, including
 * the global circuit load multiplier, yearly load shape, daily load shape,
 * and a dutycycle load shape.
 * 
 * The default is for the load to be a current injection source.  Thus, its
 * primitive Y matrix contains only the impedance that might exist from the
 * neutral of a wye-connected load to ground.  However, if the load model is
 * switched to Admittance from PowerFlow (see Set LoadModel command), the load
 * is converted to an admittance and included in the system Y matrix.  This
 * would be the model used for fault studies where convergence might not be
 * achieved because of low voltages.
 * 
 * Loads are assumed balanced for the number of phases specified.  If you
 * would like unbalanced loads, enter separate single-phase loads.
 * 
 * There are three legal ways to specify the base load:
 *     1.kW, PF
 *     2.kw, kvar
 *     3.kVA, PF
 * 
 * If you sent these properties in the order shown, the definition should
 * work. If you deviate from these procedures, the result may or may not be
 * what you want.  (To determine if it has accomplished the desired effect,
 * execute the Dump command for the desired load(s) and observe the settings.)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.conversion.Load#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getKW <em>KW</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getPF <em>PF</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getModel <em>Model</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getLoadSpec <em>Load Spec</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getGrowth <em>Growth</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getStatus <em>Status</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getClass_ <em>Class</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getVMinPU <em>VMin PU</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getVMaxPU <em>VMax PU</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getVMinNorm <em>VMin Norm</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getVMinEmerg <em>VMin Emerg</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getXfKVA <em>Xf KVA</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getAllocationFactor <em>Allocation Factor</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getPctMean <em>Pct Mean</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getPctStdDev <em>Pct Std Dev</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getCvrWatts <em>Cvr Watts</em>}</li>
 *   <li>{@link electrickery.conversion.Load#getCvrVars <em>Cvr Vars</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.conversion.ConversionPackage#getLoad()
 * @model
 * @generated
 */
public interface Load extends PowerConversionElement {
	/**
	 * Returns the value of the '<em><b>Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuit</em>' reference.
	 * @see #setCircuit(Circuit)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Circuit()
	 * @model
	 * @generated
	 */
	Circuit getCircuit();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getCircuit <em>Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Circuit</em>' reference.
	 * @see #getCircuit()
	 * @generated
	 */
	void setCircuit(Circuit value);

	/**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of bus to which the load is connected.  Include node definitions if the terminal conductors are connected abnormally.  3-phase Wye-connected loads have 4 conductors; Delta-connected have 3.  Wye-connected loads, in general, have one more conductor than phases.  1-phase Delta has 2 conductors; 2-phase has 3.  The remaining Delta, or line-line, connections have the same number of conductors as phases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Bus1()
	 * @model
	 * @generated
	 */
	String getBus1();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getBus1 <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus1</em>' attribute.
	 * @see #getBus1()
	 * @generated
	 */
	void setBus1(String value);

	/**
	 * Returns the value of the '<em><b>KV</b></em>' attribute.
	 * The default value is <code>"12.47"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nominal rated (1.0 per unit) voltage, kV, for load. For 2- and 3-phase loads, specify phase-phase kV.  Otherwise, specify actual kV across each branch of the load.  If wye (star), specify phase-neutral kV.  If delta or phase-phase connected, specify phase-phase kV.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_KV()
	 * @model default="12.47"
	 * @generated
	 */
	double getKV();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getKV <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV</em>' attribute.
	 * @see #getKV()
	 * @generated
	 */
	void setKV(double value);

	/**
	 * Returns the value of the '<em><b>KW</b></em>' attribute.
	 * The default value is <code>"10.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total base kW for the load.  Normally, you would enter the maximum kW for the load for the first year and allow it to be adjusted by the load shapes, growth shapes, and global load multiplier.  Legal ways to define base load:
	 *     kW, PF
	 *     kW, kvar
	 *     kVA, PF
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW</em>' attribute.
	 * @see #setKW(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_KW()
	 * @model default="10.0"
	 * @generated
	 */
	double getKW();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getKW <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW</em>' attribute.
	 * @see #getKW()
	 * @generated
	 */
	void setKW(double value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.loadModel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Integer code for the model to use for load variation with voltage.
	 * Valid values are:
	 *     1:Standard constant P+jQ load. (Default)
	 *     2:Constant impedance load.
	 *     3:Const P, Quadratic Q (like a motor).
	 *     4:Nominal Linear P, Quadratic Q (feeder mix). Use this with CVRfactor.
	 *     5:Constant Current Magnitude
	 *     6:Const P, Fixed Q
	 *     7:Const P, Fixed Impedance Q
	 *     For Types 6 and 7, only the P is modified by load multipliers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model</em>' attribute.
	 * @see electrickery.conversion.loadModel
	 * @see #setModel(loadModel)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Model()
	 * @model
	 * @generated
	 */
	loadModel getModel();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getModel <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' attribute.
	 * @see electrickery.conversion.loadModel
	 * @see #getModel()
	 * @generated
	 */
	void setModel(loadModel value);

	/**
	 * Returns the value of the '<em><b>Load Spec</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.loadSpecType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Spec</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Spec</em>' attribute.
	 * @see electrickery.conversion.loadSpecType
	 * @see #setLoadSpec(loadSpecType)
	 * @see electrickery.conversion.ConversionPackage#getLoad_LoadSpec()
	 * @model
	 * @generated
	 */
	loadSpecType getLoadSpec();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getLoadSpec <em>Load Spec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Spec</em>' attribute.
	 * @see electrickery.conversion.loadSpecType
	 * @see #getLoadSpec()
	 * @generated
	 */
	void setLoadSpec(loadSpecType value);

	/**
	 * Returns the value of the '<em><b>Yearly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Load shape to use for yearly simulations.  Must be previously defined as a Loadshape object. Defaults to Daily load shape when Daily is defined.  The daily load shape is repeated in this case. Otherwise, the default is no variation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Yearly</em>' attribute.
	 * @see #setYearly(String)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Yearly()
	 * @model
	 * @generated
	 */
	String getYearly();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getYearly <em>Yearly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Yearly</em>' attribute.
	 * @see #getYearly()
	 * @generated
	 */
	void setYearly(String value);

	/**
	 * Returns the value of the '<em><b>Daily</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Load shape to use for daily simulations.  Must be previously defined as a Loadshape object of 24 hrs, typically. Default is no variation (constant) if not defined. Side effect: Sets Yearly load shape if not already defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Daily</em>' attribute.
	 * @see #setDaily(String)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Daily()
	 * @model
	 * @generated
	 */
	String getDaily();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getDaily <em>Daily</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Daily</em>' attribute.
	 * @see #getDaily()
	 * @generated
	 */
	void setDaily(String value);

	/**
	 * Returns the value of the '<em><b>Duty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Load shape to use for duty cycle simulations.  Must be previously defined as a Loadshape object.  Typically would have time intervals less than 1 hr. Designate the number of points to solve using the Set Number=xxxx command. If there are fewer points in the actual shape, the shape is assumed to repeat. Defaults to Daily curve If not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duty</em>' attribute.
	 * @see #setDuty(String)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Duty()
	 * @model
	 * @generated
	 */
	String getDuty();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getDuty <em>Duty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duty</em>' attribute.
	 * @see #getDuty()
	 * @generated
	 */
	void setDuty(String value);

	/**
	 * Returns the value of the '<em><b>Growth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Characteristic  to use for growth factors by years.  Must be previously defined as a Growthshape object. Defaults to circuit default growth factor
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Growth</em>' attribute.
	 * @see #setGrowth(String)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Growth()
	 * @model
	 * @generated
	 */
	String getGrowth();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getGrowth <em>Growth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Growth</em>' attribute.
	 * @see #getGrowth()
	 * @generated
	 */
	void setGrowth(String value);

	/**
	 * Returns the value of the '<em><b>Conn</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Connection type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #setConn(connectionType)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Conn()
	 * @model
	 * @generated
	 */
	connectionType getConn();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
	void setConn(connectionType value);

	/**
	 * Returns the value of the '<em><b>KV Ar</b></em>' attribute.
	 * The default value is <code>"5.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specify the base kvar for specifying load as kW & kvar.  Assumes kW has been already defined.  Alternative to specifying the power factor.  Side effect: the power factor and kVA is altered to agree.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Ar</em>' attribute.
	 * @see #setKVAr(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_KVAr()
	 * @model default="5.0"
	 * @generated
	 */
	double getKVAr();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getKVAr <em>KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV Ar</em>' attribute.
	 * @see #getKVAr()
	 * @generated
	 */
	void setKVAr(double value);

	/**
	 * Returns the value of the '<em><b>PF</b></em>' attribute.
	 * The default value is <code>"0.88"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Load power factor.  Enter negative for leading powerfactor (when kW and kvar have opposite signs.)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PF</em>' attribute.
	 * @see #setPF(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_PF()
	 * @model default="0.88"
	 * @generated
	 */
	double getPF();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getPF <em>PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PF</em>' attribute.
	 * @see #getPF()
	 * @generated
	 */
	void setPF(double value);

	/**
	 * Returns the value of the '<em><b>RNeut</b></em>' attribute.
	 * The default value is <code>"-1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Neutral resistance of wye (star)-connected load in actual ohms. If entered as a negative value, the neutral is assumed to be open, or floating.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RNeut</em>' attribute.
	 * @see #setRNeut(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_RNeut()
	 * @model default="-1.0"
	 * @generated
	 */
	double getRNeut();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getRNeut <em>RNeut</em>}' attribute.
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
	 * Neutral reactance of wye(star)-connected load in actual ohms.  May be + or -.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XNeut</em>' attribute.
	 * @see #setXNeut(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_XNeut()
	 * @model
	 * @generated
	 */
	double getXNeut();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getXNeut <em>XNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XNeut</em>' attribute.
	 * @see #getXNeut()
	 * @generated
	 */
	void setXNeut(double value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The default value is <code>"Variable"</code>.
	 * The literals are from the enumeration {@link electrickery.conversion.loadStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If Fixed, no load multipliers apply;  however, growth multipliers do apply.  All multipliers apply to Variable loads.  Exempt loads are not modified by the global load multiplier, such as in load duration curves, etc.  Daily multipliers do apply, so this is a good way to represent industrial load that stays the same for the period study.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see electrickery.conversion.loadStatus
	 * @see #setStatus(loadStatus)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Status()
	 * @model default="Variable"
	 * @generated
	 */
	loadStatus getStatus();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see electrickery.conversion.loadStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(loadStatus value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An arbitrary integer number representing the class of load so that load values may be segregated by load value. Default is 1; not used internally.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(int)
	 * @see electrickery.conversion.ConversionPackage#getLoad_Class()
	 * @model
	 * @generated
	 */
	int getClass_();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(int value);

	/**
	 * Returns the value of the '<em><b>VMin PU</b></em>' attribute.
	 * The default value is <code>"0.95"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum per unit voltage for which the MODEL is assumed to apply.  Below this value, the load model reverts to a constant impedance model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMin PU</em>' attribute.
	 * @see #setVMinPU(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_VMinPU()
	 * @model default="0.95"
	 * @generated
	 */
	double getVMinPU();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getVMinPU <em>VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMin PU</em>' attribute.
	 * @see #getVMinPU()
	 * @generated
	 */
	void setVMinPU(double value);

	/**
	 * Returns the value of the '<em><b>VMax PU</b></em>' attribute.
	 * The default value is <code>"1.05"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum per unit voltage for which the MODEL is assumed to apply.  Above this value, the load model reverts to a constant impedance model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMax PU</em>' attribute.
	 * @see #setVMaxPU(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_VMaxPU()
	 * @model default="1.05"
	 * @generated
	 */
	double getVMaxPU();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getVMaxPU <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMax PU</em>' attribute.
	 * @see #getVMaxPU()
	 * @generated
	 */
	void setVMaxPU(double value);

	/**
	 * Returns the value of the '<em><b>VMin Norm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum per unit voltage for load EEN evaluations, Normal limit. Default = 0, which defaults to system "vminnorm" property (see Set Command under Executive).  If this property is specified, it ALWAYS overrides the system specification. This allows you to have different criteria for different loads. Set to zero to revert to the default system value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMin Norm</em>' attribute.
	 * @see #setVMinNorm(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_VMinNorm()
	 * @model
	 * @generated
	 */
	double getVMinNorm();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getVMinNorm <em>VMin Norm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMin Norm</em>' attribute.
	 * @see #getVMinNorm()
	 * @generated
	 */
	void setVMinNorm(double value);

	/**
	 * Returns the value of the '<em><b>VMin Emerg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum per unit voltage for load UE evaluations, Emergency limit.  Default = 0, which defaults to system "vminemerg" property (see Set Command under Executive).  If this property is specified, it ALWAYS overrides the system specification. This allows you to have different criteria for different loads.  Set to zero to revert to the default system value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMin Emerg</em>' attribute.
	 * @see #setVMinEmerg(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_VMinEmerg()
	 * @model
	 * @generated
	 */
	double getVMinEmerg();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getVMinEmerg <em>VMin Emerg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMin Emerg</em>' attribute.
	 * @see #getVMinEmerg()
	 * @generated
	 */
	void setVMinEmerg(double value);

	/**
	 * Returns the value of the '<em><b>Xf KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rated kVA of service transformer for allocating loads based on connected kVA at a bus. Side effect:  kW, PF, and kvar are modified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xf KVA</em>' attribute.
	 * @see #setXfKVA(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_XfKVA()
	 * @model
	 * @generated
	 */
	double getXfKVA();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getXfKVA <em>Xf KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xf KVA</em>' attribute.
	 * @see #getXfKVA()
	 * @generated
	 */
	void setXfKVA(double value);

	/**
	 * Returns the value of the '<em><b>Allocation Factor</b></em>' attribute.
	 * The default value is <code>"0.5"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Allocation factor for allocating loads based on connected kVA at a bus.  Side effect:  kW, PF, and kvar are modified by multiplying this factor times the XFKVA (if > 0).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Allocation Factor</em>' attribute.
	 * @see #setAllocationFactor(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_AllocationFactor()
	 * @model default="0.5"
	 * @generated
	 */
	double getAllocationFactor();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getAllocationFactor <em>Allocation Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allocation Factor</em>' attribute.
	 * @see #getAllocationFactor()
	 * @generated
	 */
	void setAllocationFactor(double value);

	/**
	 * Returns the value of the '<em><b>KVA</b></em>' attribute.
	 * The default value is <code>"11.3636"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specify base Load in kVA (and power factor).  This is intended to be used in combination with the power factor (PF) to determine the actual load.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA</em>' attribute.
	 * @see #setKVA(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_KVA()
	 * @model default="11.3636"
	 * @generated
	 */
	double getKVA();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getKVA <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA</em>' attribute.
	 * @see #getKVA()
	 * @generated
	 */
	void setKVA(double value);

	/**
	 * Returns the value of the '<em><b>Pct Mean</b></em>' attribute.
	 * The default value is <code>"50.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent mean value for load to use for monte carlo studies if no loadshape is assigned to this load.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Mean</em>' attribute.
	 * @see #setPctMean(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_PctMean()
	 * @model default="50.0"
	 * @generated
	 */
	double getPctMean();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getPctMean <em>Pct Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Mean</em>' attribute.
	 * @see #getPctMean()
	 * @generated
	 */
	void setPctMean(double value);

	/**
	 * Returns the value of the '<em><b>Pct Std Dev</b></em>' attribute.
	 * The default value is <code>"10.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent Std deviation value for load to use for monte carlo studies if no loadshape is assigned to this load.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Std Dev</em>' attribute.
	 * @see #setPctStdDev(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_PctStdDev()
	 * @model default="10.0"
	 * @generated
	 */
	double getPctStdDev();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getPctStdDev <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Std Dev</em>' attribute.
	 * @see #getPctStdDev()
	 * @generated
	 */
	void setPctStdDev(double value);

	/**
	 * Returns the value of the '<em><b>Cvr Watts</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent reduction in active power (watts) per 1% reduction in voltage from 100% rated. Typical values range from 0.4 to 0.8. Applies to Model=4 only. Intended to represent conservation voltage reduction or voltage optimization measures.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cvr Watts</em>' attribute.
	 * @see #setCvrWatts(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_CvrWatts()
	 * @model default="1.0"
	 * @generated
	 */
	double getCvrWatts();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getCvrWatts <em>Cvr Watts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cvr Watts</em>' attribute.
	 * @see #getCvrWatts()
	 * @generated
	 */
	void setCvrWatts(double value);

	/**
	 * Returns the value of the '<em><b>Cvr Vars</b></em>' attribute.
	 * The default value is <code>"2.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent reduction in reactive power (vars) per 1% reduction in voltage from 100% rated. Typical values range from 2 to 3. Applies to Model=4 only. Intended to represent conservation voltage reduction or voltage optimization measures.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cvr Vars</em>' attribute.
	 * @see #setCvrVars(double)
	 * @see electrickery.conversion.ConversionPackage#getLoad_CvrVars()
	 * @model default="2.0"
	 * @generated
	 */
	double getCvrVars();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Load#getCvrVars <em>Cvr Vars</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cvr Vars</em>' attribute.
	 * @see #getCvrVars()
	 * @generated
	 */
	void setCvrVars(double value);

} // Load
