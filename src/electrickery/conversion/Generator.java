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
 * A representation of the model object '<em><b>Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The generator is essentially a negative load that can be dispatched.
 * 
 * If the dispatch value (DispValue) is 0, the generator always follows the
 * appropriate dispatch curve, which are simply load curves. If DispValue>0
 * then the generator only comes on when the global circuit load multiplier
 * exceeds DispValue.  When the generator is on, it always follows the
 * dispatch curve appropriate for the type of solution being performed.
 * 
 * If you want to model a generator that is fully on whenever it is dispatched
 * on, simply designate "Status=Fixed".  The default is "Status=Variable"
 * (i.e., it follows a dispatch curve.  You could also define a dispatch curve
 * that is always 1.0.
 * 
 * Generators have their own energy meters that record:
 *     1. Total kwh
 *     2. Total kvarh
 *     3. Max kW
 *     4. Max kVA
 *     5. Hours in operation
 *     6. Price * kwH
 * 
 * Generator meters reset with the circuit energy meters and take a sample
 * with the circuit energy meters as well. The Energy meters also used
 * trapezoidal integration so that they are compatible with Load-Duration
 * simulations.
 * 
 * Generator models are:
 *     1. Constant P, Q  (* dispatch curve, if appropriate).
 *     2. Constant Z  (For simple solution)
 *     3. Constant P, |V|  like a standard power flow  [not implemented]
 *     4. Constant P, Fixed Q  (vars)
 *     5. Constant P, Fixed Q  (reactance)
 * 
 * Most of the time you will use #1 for planning studies.
 * 
 * The default is for the generator to be a current injection source.  Thus,
 * its primitive Y matrix contains only the impedance that might exist from
 * the neutral of a wye-connected generator to ground.  However, if the
 * generator model is switched to Admittance from PowerFlow (see Set Mode
 * command), the generator is converted to an admittance and included in the
 * system Y matrix.
 * 
 * Generators are assumed balanced for the number of phases specified.  If you
 * would like unbalanced generators, enter separate single-phase generators.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.conversion.Generator#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getKW <em>KW</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getPf <em>Pf</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getModel <em>Model</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVMinPU <em>VMin PU</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVMaxPU <em>VMax PU</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDispMode <em>Disp Mode</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDispValue <em>Disp Value</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getRNeut <em>RNeut</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getXNeut <em>XNeut</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getStatus <em>Status</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getClass_ <em>Class</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVPU <em>VPU</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVTarget <em>VTarget</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getMaxKVAr <em>Max KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getMinKVAr <em>Min KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getPvFactor <em>Pv Factor</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#isForceOn <em>Force On</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getMVA <em>MVA</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getXD <em>XD</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getXDp <em>XDp</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getXDpp <em>XDpp</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getH <em>H</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getD <em>D</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getUserModel <em>User Model</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getUserData <em>User Data</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getShaftModel <em>Shaft Model</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getShaftData <em>Shaft Data</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#isDebugTrace <em>Debug Trace</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#isGenOn <em>Gen On</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getShapeFactor <em>Shape Factor</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#isForcedOn <em>Forced On</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#isFixed <em>Fixed</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getYEq <em>YEq</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getYEq95 <em>YEq95</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getYEq105 <em>YEq105</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVBase <em>VBase</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVBase95 <em>VBase95</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVBase105 <em>VBase105</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVarBase <em>Var Base</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVarMin <em>Var Min</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getVarMax <em>Var Max</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDeltaQMax <em>Delta QMax</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDQdV <em>DQd V</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getDQdVSaved <em>DQd VSaved</em>}</li>
 *   <li>{@link electrickery.conversion.Generator#getYQFixed <em>YQ Fixed</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.conversion.ConversionPackage#getGenerator()
 * @model
 * @generated
 */
public interface Generator extends PowerConversionElement {
    /**
	 * Returns the value of the '<em><b>Circuit</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link electrickery.common.Circuit#getGenerators <em>Generators</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Circuit</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuit</em>' container reference.
	 * @see #setCircuit(Circuit)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Circuit()
	 * @see electrickery.common.Circuit#getGenerators
	 * @model opposite="generators" transient="false"
	 * @generated
	 */
    Circuit getCircuit();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getCircuit <em>Circuit</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Circuit</em>' container reference.
	 * @see #getCircuit()
	 * @generated
	 */
    void setCircuit(Circuit value);

    /**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bus to which the Generator is connected.  May include specific node specification.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Bus1()
	 * @model
	 * @generated
	 */
    String getBus1();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getBus1 <em>Bus1</em>}' attribute.
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
	 * Nominal rated (1.0 per unit) voltage, kV, for Generator. For 2- and 3-phase Generators, specify phase-phase kV. Otherwise, specify actual kV across each branch of the Generator. If wye (star), specify phase-neutral kV.  If delta or phase-phase connected, specify phase-phase kV.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_KV()
	 * @model default="12.47"
	 * @generated
	 */
    double getKV();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getKV <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV</em>' attribute.
	 * @see #getKV()
	 * @generated
	 */
    void setKV(double value);

    /**
	 * Returns the value of the '<em><b>KW</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total base kW for the Generator.  A positive value denotes power coming OUT of the element, which is the opposite of a load. This value is modified depending on the dispatch mode.  Unaffected by the global load multiplier and growth curves.  If you want there to be more generation, you must add more generators or change this value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW</em>' attribute.
	 * @see #setKW(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_KW()
	 * @model default="100.0"
	 * @generated
	 */
    double getKW();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getKW <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW</em>' attribute.
	 * @see #getKW()
	 * @generated
	 */
    void setKW(double value);

    /**
	 * Returns the value of the '<em><b>Pf</b></em>' attribute.
	 * The default value is <code>"0.8"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Generator power factor. Default is 0.80. Enter negative for leading powerfactor (when kW and kvar have opposite signs.) A positive power factor for a generator signifies that the generator produces vars as is typical for a synchronous generator.  Induction machines would be specified with a negative power factor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pf</em>' attribute.
	 * @see #setPf(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Pf()
	 * @model default="0.8"
	 * @generated
	 */
    double getPf();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getPf <em>Pf</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pf</em>' attribute.
	 * @see #getPf()
	 * @generated
	 */
    void setPf(double value);

    /**
	 * Returns the value of the '<em><b>KV Ar</b></em>' attribute.
	 * The default value is <code>"5.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specify the base kvar.  Alternative to specifying the power factor.  Side effect: the power factor value is altered to agree based on present value of kW.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Ar</em>' attribute.
	 * @see #setKVAr(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_KVAr()
	 * @model default="5.0"
	 * @generated
	 */
    double getKVAr();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getKVAr <em>KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV Ar</em>' attribute.
	 * @see #getKVAr()
	 * @generated
	 */
    void setKVAr(double value);

    /**
	 * Returns the value of the '<em><b>Model</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.generatorModel}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Integer code for the model to use for generation variation with voltage.
	 * Valid values are:
	 *     1:Generator injects a constant kW at specified power factor.
	 *     2:Generator is modeled as a constant admittance.
	 *     3:Const kW, constant kV.  Somewhat like a conventional transmission
	 *     power flow P-V generator.
	 *     4:Const kW, Fixed Q (Q never varies)
	 *     5:Const kW, Fixed Q(as a constant reactance)
	 *     6:Compute load injection from User-written Model.(see usage of Xd,Xdp)
	 *     7:Constant kW, kvar, but current limited below Vminpu
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model</em>' attribute.
	 * @see electrickery.conversion.generatorModel
	 * @see #setModel(generatorModel)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Model()
	 * @model
	 * @generated
	 */
    generatorModel getModel();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getModel <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' attribute.
	 * @see electrickery.conversion.generatorModel
	 * @see #getModel()
	 * @generated
	 */
    void setModel(generatorModel value);

    /**
	 * Returns the value of the '<em><b>VMin PU</b></em>' attribute.
	 * The default value is <code>"0.95"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum per unit voltage for which the Model is assumed to apply. Below this value, the load model reverts to a constant impedance model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMin PU</em>' attribute.
	 * @see #setVMinPU(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VMinPU()
	 * @model default="0.95"
	 * @generated
	 */
    double getVMinPU();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVMinPU <em>VMin PU</em>}' attribute.
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
	 * Maximum per unit voltage for which the Model is assumed to apply. Above this value, the load model reverts to a constant impedance model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMax PU</em>' attribute.
	 * @see #setVMaxPU(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VMaxPU()
	 * @model default="1.05"
	 * @generated
	 */
    double getVMaxPU();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVMaxPU <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMax PU</em>' attribute.
	 * @see #getVMaxPU()
	 * @generated
	 */
    void setVMaxPU(double value);

    /**
	 * Returns the value of the '<em><b>Yearly</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dispatch shape to use for yearly simulations.  Must be previously defined as a Loadshape object. If this is not specified, the daily dispatch shape is repeated. If the generator is assumed to be ON continuously, specify this value as FIXED, or designate a curve that is 1.0 per unit at all times. Nominally for 8760 simulations.  If there are fewer points in the designated shape than the number of points in the solution, the curve is repeated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Yearly</em>' attribute.
	 * @see #setYearly(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Yearly()
	 * @model
	 * @generated
	 */
    String getYearly();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getYearly <em>Yearly</em>}' attribute.
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
	 * Dispatch shape to use for daily simulations.  Must be previously defined as a Loadshape object of 24 hrs, typically.  If generator is assumed to be ON continuously, specify this value as FIXED, or designate a Loadshape object that is 1.0 perunit for all hours.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Daily</em>' attribute.
	 * @see #setDaily(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Daily()
	 * @model
	 * @generated
	 */
    String getDaily();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDaily <em>Daily</em>}' attribute.
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
	 * Load shape to use for duty cycle dispatch simulations such as for wind generation. Must be previously defined as a Loadshape object. Typically would have time intervals less than 1 hr -- perhaps, in seconds.  Designate the number of points to solve using the Set Number=xxxx command.  If there are fewer points in the actual shape, the shape is assumed to repeat.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duty</em>' attribute.
	 * @see #setDuty(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Duty()
	 * @model
	 * @generated
	 */
    String getDuty();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDuty <em>Duty</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duty</em>' attribute.
	 * @see #getDuty()
	 * @generated
	 */
    void setDuty(String value);

    /**
	 * Returns the value of the '<em><b>Disp Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.dispatchType}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * In default mode, gen is either always on or follows dispatch curve as specified.  Otherwise, the gen comes on when either the global default load level or the price level exceeds the dispatch value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Disp Mode</em>' attribute.
	 * @see electrickery.conversion.dispatchType
	 * @see #setDispMode(dispatchType)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_DispMode()
	 * @model
	 * @generated
	 */
    dispatchType getDispMode();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDispMode <em>Disp Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disp Mode</em>' attribute.
	 * @see electrickery.conversion.dispatchType
	 * @see #getDispMode()
	 * @generated
	 */
    void setDispMode(dispatchType value);

    /**
	 * Returns the value of the '<em><b>Disp Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If = 0.0 Then Generator follow dispatch curves, if any.  If > 0  Then Generator is ON only when either the price signal exceeds this value or the load multiplier (set loadmult=) times the default yearly growth factor exceeds this value.  Then the generator follows dispatch curves, if any (see also Status).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Disp Value</em>' attribute.
	 * @see #setDispValue(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_DispValue()
	 * @model
	 * @generated
	 */
    double getDispValue();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDispValue <em>Disp Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disp Value</em>' attribute.
	 * @see #getDispValue()
	 * @generated
	 */
    void setDispValue(double value);

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
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Conn()
	 * @model
	 * @generated
	 */
    connectionType getConn();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
    void setConn(connectionType value);

    /**
	 * Returns the value of the '<em><b>RNeut</b></em>' attribute.
	 * The default value is <code>"-1.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Removed due to causing confusion - Add neutral impedance externally.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RNeut</em>' attribute.
	 * @see #setRNeut(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_RNeut()
	 * @model default="-1.0"
	 * @generated
	 */
    double getRNeut();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getRNeut <em>RNeut</em>}' attribute.
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
	 * Removed due to causing confusion - Add neutral impedance externally.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XNeut</em>' attribute.
	 * @see #setXNeut(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_XNeut()
	 * @model
	 * @generated
	 */
    double getXNeut();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getXNeut <em>XNeut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XNeut</em>' attribute.
	 * @see #getXNeut()
	 * @generated
	 */
    void setXNeut(double value);

    /**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.generatorStatus}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If Fixed, then dispatch multipliers do not apply. The generator is alway at full power when it is ON. Default is Variable (follows curves).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see electrickery.conversion.generatorStatus
	 * @see #setStatus(generatorStatus)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Status()
	 * @model
	 * @generated
	 */
    generatorStatus getStatus();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see electrickery.conversion.generatorStatus
	 * @see #getStatus()
	 * @generated
	 */
    void setStatus(generatorStatus value);

    /**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An arbitrary integer number representing the class of Generator so that Generator values may be segregated by class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(int)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Class()
	 * @model default="1"
	 * @generated
	 */
    int getClass_();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
    void setClass(int value);

    /**
	 * Returns the value of the '<em><b>VPU</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per Unit voltage set point for Model = 3  (typical power flow model).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VPU</em>' attribute.
	 * @see #setVPU(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VPU()
	 * @model default="1.0"
	 * @generated
	 */
    double getVPU();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVPU <em>VPU</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VPU</em>' attribute.
	 * @see #getVPU()
	 * @generated
	 */
    void setVPU(double value);

    /**
	 * Returns the value of the '<em><b>VTarget</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Target voltage for generator with voltage control.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VTarget</em>' attribute.
	 * @see #setVTarget(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VTarget()
	 * @model
	 * @generated
	 */
    double getVTarget();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVTarget <em>VTarget</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VTarget</em>' attribute.
	 * @see #getVTarget()
	 * @generated
	 */
    void setVTarget(double value);

    /**
	 * Returns the value of the '<em><b>Max KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum kvar limit for Model = 3.  Defaults to twice the specified load kvar. Always reset this if you change PF or kvar properties.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max KV Ar</em>' attribute.
	 * @see #setMaxKVAr(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_MaxKVAr()
	 * @model
	 * @generated
	 */
    double getMaxKVAr();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getMaxKVAr <em>Max KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max KV Ar</em>' attribute.
	 * @see #getMaxKVAr()
	 * @generated
	 */
    void setMaxKVAr(double value);

    /**
	 * Returns the value of the '<em><b>Min KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum kvar limit for Model = 3. Enter a negative number if generator can absorb vars.  Defaults to negative of Maxkvar.  Always reset this if you change PF or kvar properties.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min KV Ar</em>' attribute.
	 * @see #setMinKVAr(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_MinKVAr()
	 * @model
	 * @generated
	 */
    double getMinKVAr();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getMinKVAr <em>Min KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min KV Ar</em>' attribute.
	 * @see #getMinKVAr()
	 * @generated
	 */
    void setMinKVAr(double value);

    /**
	 * Returns the value of the '<em><b>Pv Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Deceleration factor for P-V generator model (Model=3).  Default is 0.1.  If the circuit converges easily, you may want to use a higher number such as 1.0. Use a lower number if solution diverges. Use Debugtrace=yes to create a file that will trace the convergence of a generator model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pv Factor</em>' attribute.
	 * @see #setPvFactor(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_PvFactor()
	 * @model
	 * @generated
	 */
    double getPvFactor();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getPvFactor <em>Pv Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pv Factor</em>' attribute.
	 * @see #getPvFactor()
	 * @generated
	 */
    void setPvFactor(double value);

    /**
	 * Returns the value of the '<em><b>Force On</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Forces generator ON despite requirements of other dispatch modes.  Stays ON until this property is set to NO, or an internal algorithm cancels the forced ON state.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Force On</em>' attribute.
	 * @see #setForceOn(boolean)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_ForceOn()
	 * @model default="false"
	 * @generated
	 */
    boolean isForceOn();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#isForceOn <em>Force On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Force On</em>' attribute.
	 * @see #isForceOn()
	 * @generated
	 */
    void setForceOn(boolean value);

    /**
	 * Returns the value of the '<em><b>KVA</b></em>' attribute.
	 * The default value is <code>"1.2"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kVA rating of electrical machine. Defaults to 1.2* kW if not specified.  Applied to machine or inverter definition for Dynamics mode solutions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA</em>' attribute.
	 * @see #setKVA(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_KVA()
	 * @model default="1.2"
	 * @generated
	 */
    double getKVA();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getKVA <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA</em>' attribute.
	 * @see #getKVA()
	 * @generated
	 */
    void setKVA(double value);

    /**
	 * Returns the value of the '<em><b>MVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MVA rating of electrical machine.  Alternative to using kVA.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>MVA</em>' attribute.
	 * @see #setMVA(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_MVA()
	 * @model
	 * @generated
	 */
    double getMVA();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getMVA <em>MVA</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MVA</em>' attribute.
	 * @see #getMVA()
	 * @generated
	 */
    void setMVA(double value);

    /**
	 * Returns the value of the '<em><b>XD</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit synchronous reactance of machine. Presently used only for Thevinen impedance for power flow calcs of user models (model=6).  Typically use a value 0.4 to 1.0. Default is 1.0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XD</em>' attribute.
	 * @see #setXD(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_XD()
	 * @model default="1.0"
	 * @generated
	 */
    double getXD();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getXD <em>XD</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XD</em>' attribute.
	 * @see #getXD()
	 * @generated
	 */
    void setXD(double value);

    /**
	 * Returns the value of the '<em><b>XDp</b></em>' attribute.
	 * The default value is <code>"0.27"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit transient reactance of the machine.  Used for Dynamics mode and Fault studies.  Default is 0.27.  For user models, this value is used for the Thevinen/Norton impedance for Dynamics Mode.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XDp</em>' attribute.
	 * @see #setXDp(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_XDp()
	 * @model default="0.27"
	 * @generated
	 */
    double getXDp();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getXDp <em>XDp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XDp</em>' attribute.
	 * @see #getXDp()
	 * @generated
	 */
    void setXDp(double value);

    /**
	 * Returns the value of the '<em><b>XDpp</b></em>' attribute.
	 * The default value is <code>"0.2"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit subtransient reactance of the machine.  Used for Harmonics.  Default is 0.20.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XDpp</em>' attribute.
	 * @see #setXDpp(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_XDpp()
	 * @model default="0.2"
	 * @generated
	 */
    double getXDpp();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getXDpp <em>XDpp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XDpp</em>' attribute.
	 * @see #getXDpp()
	 * @generated
	 */
    void setXDpp(double value);

    /**
	 * Returns the value of the '<em><b>H</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Per unit mass constant of the machine.  MW-sec/MVA.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>H</em>' attribute.
	 * @see #setH(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_H()
	 * @model
	 * @generated
	 */
    double getH();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getH <em>H</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>H</em>' attribute.
	 * @see #getH()
	 * @generated
	 */
    void setH(double value);

    /**
	 * Returns the value of the '<em><b>D</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Damping constant.  Usual range is 0 to 4. Default is 1.0.  Adjust to get damping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>D</em>' attribute.
	 * @see #setD(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_D()
	 * @model default="1.0"
	 * @generated
	 */
    double getD();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getD <em>D</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>D</em>' attribute.
	 * @see #getD()
	 * @generated
	 */
    void setD(double value);

    /**
	 * Returns the value of the '<em><b>User Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of DLL containing user-written model, which computes the terminal currents for Dynamics studies, overriding the default model.  Set to "none" to negate previous setting.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User Model</em>' attribute.
	 * @see #setUserModel(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_UserModel()
	 * @model
	 * @generated
	 */
    String getUserModel();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getUserModel <em>User Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Model</em>' attribute.
	 * @see #getUserModel()
	 * @generated
	 */
    void setUserModel(String value);

    /**
	 * Returns the value of the '<em><b>User Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * String (in quotes or parentheses) that gets passed to user-written model for defining the data required for that model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User Data</em>' attribute.
	 * @see #setUserData(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_UserData()
	 * @model
	 * @generated
	 */
    String getUserData();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getUserData <em>User Data</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Data</em>' attribute.
	 * @see #getUserData()
	 * @generated
	 */
    void setUserData(String value);

    /**
	 * Returns the value of the '<em><b>Shaft Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of user-written DLL containing a Shaft model, which models the prime mover and determines the power on the shaft for Dynamics studies.  Models additional mass elements other than the single-mass model in the DSS default model. Set to "none" to negate previous setting.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shaft Model</em>' attribute.
	 * @see #setShaftModel(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_ShaftModel()
	 * @model
	 * @generated
	 */
    String getShaftModel();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getShaftModel <em>Shaft Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shaft Model</em>' attribute.
	 * @see #getShaftModel()
	 * @generated
	 */
    void setShaftModel(String value);

    /**
	 * Returns the value of the '<em><b>Shaft Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * String (in quotes or parentheses) that gets passed to user-written shaft dynamic model for defining the data for that model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Shaft Data</em>' attribute.
	 * @see #setShaftData(String)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_ShaftData()
	 * @model
	 * @generated
	 */
    String getShaftData();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getShaftData <em>Shaft Data</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shaft Data</em>' attribute.
	 * @see #getShaftData()
	 * @generated
	 */
    void setShaftData(String value);

    /**
	 * Returns the value of the '<em><b>Debug Trace</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Turn this on to capture the progress of the generator model for each iteration.  Creates a separate file for each generator named "GEN_name.CSV".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Debug Trace</em>' attribute.
	 * @see #setDebugTrace(boolean)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_DebugTrace()
	 * @model default="false"
	 * @generated
	 */
    boolean isDebugTrace();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#isDebugTrace <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug Trace</em>' attribute.
	 * @see #isDebugTrace()
	 * @generated
	 */
    void setDebugTrace(boolean value);

    /**
	 * Returns the value of the '<em><b>Gen On</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether generator is currently on.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gen On</em>' attribute.
	 * @see #setGenOn(boolean)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_GenOn()
	 * @model
	 * @generated
	 */
    boolean isGenOn();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#isGenOn <em>Gen On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen On</em>' attribute.
	 * @see #isGenOn()
	 * @generated
	 */
    void setGenOn(boolean value);

    /**
	 * Returns the value of the '<em><b>Shape Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Shape Factor</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Shape Factor</em>' attribute.
	 * @see #setShapeFactor(double[])
	 * @see electrickery.conversion.ConversionPackage#getGenerator_ShapeFactor()
	 * @model dataType="electrickery.Complex"
	 * @generated
	 */
    double[] getShapeFactor();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getShapeFactor <em>Shape Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shape Factor</em>' attribute.
	 * @see #getShapeFactor()
	 * @generated
	 */
    void setShapeFactor(double[] value);

    /**
	 * Returns the value of the '<em><b>Forced On</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Forced On</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Forced On</em>' attribute.
	 * @see #setForcedOn(boolean)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_ForcedOn()
	 * @model default="false"
	 * @generated
	 */
    boolean isForcedOn();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#isForcedOn <em>Forced On</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forced On</em>' attribute.
	 * @see #isForcedOn()
	 * @generated
	 */
    void setForcedOn(boolean value);

    /**
	 * Returns the value of the '<em><b>Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should generator always be at base value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fixed</em>' attribute.
	 * @see #setFixed(boolean)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_Fixed()
	 * @model
	 * @generated
	 */
    boolean isFixed();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#isFixed <em>Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed</em>' attribute.
	 * @see #isFixed()
	 * @generated
	 */
    void setFixed(boolean value);

    /**
	 * Returns the value of the '<em><b>YEq</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Y at nominal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YEq</em>' attribute.
	 * @see #setYEq(double[])
	 * @see electrickery.conversion.ConversionPackage#getGenerator_YEq()
	 * @model dataType="electrickery.Complex"
	 * @generated
	 */
    double[] getYEq();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getYEq <em>YEq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YEq</em>' attribute.
	 * @see #getYEq()
	 * @generated
	 */
    void setYEq(double[] value);

    /**
	 * Returns the value of the '<em><b>YEq95</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Y at 95%.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YEq95</em>' attribute.
	 * @see #setYEq95(double[])
	 * @see electrickery.conversion.ConversionPackage#getGenerator_YEq95()
	 * @model dataType="electrickery.Complex"
	 * @generated
	 */
    double[] getYEq95();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getYEq95 <em>YEq95</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YEq95</em>' attribute.
	 * @see #getYEq95()
	 * @generated
	 */
    void setYEq95(double[] value);

    /**
	 * Returns the value of the '<em><b>YEq105</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Y at 105%.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YEq105</em>' attribute.
	 * @see #setYEq105(double[])
	 * @see electrickery.conversion.ConversionPackage#getGenerator_YEq105()
	 * @model dataType="electrickery.Complex"
	 * @generated
	 */
    double[] getYEq105();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getYEq105 <em>YEq105</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YEq105</em>' attribute.
	 * @see #getYEq105()
	 * @generated
	 */
    void setYEq105(double[] value);

    /**
	 * Returns the value of the '<em><b>VBase</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base volts suitable for computing currents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VBase</em>' attribute.
	 * @see #setVBase(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VBase()
	 * @model
	 * @generated
	 */
    double getVBase();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVBase <em>VBase</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VBase</em>' attribute.
	 * @see #getVBase()
	 * @generated
	 */
    void setVBase(double value);

    /**
	 * Returns the value of the '<em><b>VBase95</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VBase95</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>VBase95</em>' attribute.
	 * @see #setVBase95(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VBase95()
	 * @model
	 * @generated
	 */
    double getVBase95();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVBase95 <em>VBase95</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VBase95</em>' attribute.
	 * @see #getVBase95()
	 * @generated
	 */
    void setVBase95(double value);

    /**
	 * Returns the value of the '<em><b>VBase105</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>VBase105</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>VBase105</em>' attribute.
	 * @see #setVBase105(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VBase105()
	 * @model
	 * @generated
	 */
    double getVBase105();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVBase105 <em>VBase105</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VBase105</em>' attribute.
	 * @see #getVBase105()
	 * @generated
	 */
    void setVBase105(double value);

    /**
	 * Returns the value of the '<em><b>Var Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var Base</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Var Base</em>' attribute.
	 * @see #setVarBase(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VarBase()
	 * @model
	 * @generated
	 */
    double getVarBase();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVarBase <em>Var Base</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var Base</em>' attribute.
	 * @see #getVarBase()
	 * @generated
	 */
    void setVarBase(double value);

    /**
	 * Returns the value of the '<em><b>Var Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var Min</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Var Min</em>' attribute.
	 * @see #setVarMin(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VarMin()
	 * @model
	 * @generated
	 */
    double getVarMin();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVarMin <em>Var Min</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var Min</em>' attribute.
	 * @see #getVarMin()
	 * @generated
	 */
    void setVarMin(double value);

    /**
	 * Returns the value of the '<em><b>Var Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var Max</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Var Max</em>' attribute.
	 * @see #setVarMax(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_VarMax()
	 * @model
	 * @generated
	 */
    double getVarMax();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getVarMax <em>Var Max</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var Max</em>' attribute.
	 * @see #getVarMax()
	 * @generated
	 */
    void setVarMax(double value);

    /**
	 * Returns the value of the '<em><b>Delta QMax</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum allowable var change on model=3 per iteration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delta QMax</em>' attribute.
	 * @see #setDeltaQMax(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_DeltaQMax()
	 * @model
	 * @generated
	 */
    double getDeltaQMax();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDeltaQMax <em>Delta QMax</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delta QMax</em>' attribute.
	 * @see #getDeltaQMax()
	 * @generated
	 */
    void setDeltaQMax(double value);

    /**
	 * Returns the value of the '<em><b>DQd V</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>DQd V</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>DQd V</em>' attribute.
	 * @see #setDQdV(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_DQdV()
	 * @model
	 * @generated
	 */
    double getDQdV();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDQdV <em>DQd V</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DQd V</em>' attribute.
	 * @see #getDQdV()
	 * @generated
	 */
    void setDQdV(double value);

    /**
	 * Returns the value of the '<em><b>DQd VSaved</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>DQd VSaved</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>DQd VSaved</em>' attribute.
	 * @see #setDQdVSaved(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_DQdVSaved()
	 * @model
	 * @generated
	 */
    double getDQdVSaved();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getDQdVSaved <em>DQd VSaved</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DQd VSaved</em>' attribute.
	 * @see #getDQdVSaved()
	 * @generated
	 */
    void setDQdVSaved(double value);

    /**
	 * Returns the value of the '<em><b>YQ Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fixed value of Y for type 7 load.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YQ Fixed</em>' attribute.
	 * @see #setYQFixed(double)
	 * @see electrickery.conversion.ConversionPackage#getGenerator_YQFixed()
	 * @model
	 * @generated
	 */
    double getYQFixed();

    /**
	 * Sets the value of the '{@link electrickery.conversion.Generator#getYQFixed <em>YQ Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YQ Fixed</em>' attribute.
	 * @see #getYQFixed()
	 * @generated
	 */
    void setYQFixed(double value);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
    void setNominalGeneration();

} // Generator
