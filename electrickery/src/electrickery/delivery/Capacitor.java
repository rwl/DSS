/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.common.connectionType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capacitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Basic capacitor
 * 
 * Implemented as a two-terminal constant impedance (Power Delivery Element)
 * 
 * Bus2 connection defaults to 0 node of Bus1 (if Bus2 has the default bus
 * connection at the time Bus1 is defined.  Therefore, if only Bus1 is
 * specified, a shunt capacitor results.
 * If delta connected, Bus2 is set to node zero of Bus1 and nothing is
 * returned in the lower half of YPrim - all zeroes.
 * 
 * If an ungrounded wye is desired, explicitly set Bus2= and set all nodes the
 * same, e.g. Bus1.4.4.4   (uses 4th node of Bus1 as neutral point)
 * or BusNew.1.1.1  (makes a new bus for the neutral point)
 * You must specify the nodes or you will get a series capacitor!
 * 
 * A series capacitor is specified simply by setting bus2 and declaring the
 * connection to be Wye.  If the connection is specified as delta, nothing
 * will be connected to Bus2. In fact the number of terminals is set to 1.
 * 
 * Capacitance may be specified as:
 * 
 *  1.  kvar and kv ratings at base frequency.  impedance.  Specify kvar as
 *      total for
 *      all phases (all cans assumed equal). For 1-phase, kV = capacitor can
 *      kV rating. For 2 or 3-phase, kV is line-line three phase. For more
 *      than 3 phases, specify kV as actual can voltage.
 *  2.  Capacitance in uF to be used in each phase.  If specified in this
 *      manner, the given value is always used whether wye or delta.
 *  3.  A nodal C matrix (like a nodal admittance matrix).
 *      If conn=wye then 2-terminal through device
 *      If conn=delta then 1-terminal.
 *      Microfarads.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.delivery.Capacitor#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getCMatrix <em>CMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getCuf <em>Cuf</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getR <em>R</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getXl <em>Xl</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getHarm <em>Harm</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getNSteps <em>NSteps</em>}</li>
 *   <li>{@link electrickery.delivery.Capacitor#getStates <em>States</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.delivery.DeliveryPackage#getCapacitor()
 * @model
 * @generated
 */
public interface Capacitor extends PowerDeliveryElement {
    /**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of first bus.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_Bus1()
	 * @model
	 * @generated
	 */
    String getBus1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getBus1 <em>Bus1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus1</em>' attribute.
	 * @see #getBus1()
	 * @generated
	 */
    void setBus1(String value);

    /**
	 * Returns the value of the '<em><b>Bus2</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of 2nd bus. Defaults to all phases connected to first bus, node 0.  (Shunt Wye Connection) Not necessary to specify for delta (LL) connection
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus2</em>' attribute.
	 * @see #setBus2(String)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_Bus2()
	 * @model
	 * @generated
	 */
    String getBus2();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getBus2 <em>Bus2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus2</em>' attribute.
	 * @see #getBus2()
	 * @generated
	 */
    void setBus2(String value);

    /**
	 * Returns the value of the '<em><b>KV Ar</b></em>' attribute.
	 * The default value is <code>"1200.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total kvar, if one step, or ARRAY of kvar ratings for each step. Evenly divided among phases. See rules for NUMSTEPS.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Ar</em>' attribute.
	 * @see #setKVAr(double)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_KVAr()
	 * @model default="1200.0"
	 * @generated
	 */
    double getKVAr();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getKVAr <em>KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV Ar</em>' attribute.
	 * @see #getKVAr()
	 * @generated
	 */
    void setKVAr(double value);

    /**
	 * Returns the value of the '<em><b>KV</b></em>' attribute.
	 * The default value is <code>"12.47"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For 2, 3-phase, kV phase-phase. Otherwise specify actual can rating.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_KV()
	 * @model default="12.47"
	 * @generated
	 */
    double getKV();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getKV <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV</em>' attribute.
	 * @see #getKV()
	 * @generated
	 */
    void setKV(double value);

    /**
	 * Returns the value of the '<em><b>Conn</b></em>' attribute.
	 * The default value is <code>"Wye"</code>.
	 * The literals are from the enumeration {@link electrickery.common.connectionType}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default is wye, which is equivalent to LN.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #setConn(connectionType)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_Conn()
	 * @model default="Wye"
	 * @generated
	 */
    connectionType getConn();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
    void setConn(connectionType value);

    /**
	 * Returns the value of the '<em><b>CMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nodal cap. matrix, lower triangle, microfarads, of the following form:
	 *      cmatrix="c11 | -c21 c22 | -c31 -c32 c33"
	 * All steps are assumed the same if this property is used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>CMatrix</em>' reference.
	 * @see #setCMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_CMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getCMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getCMatrix <em>CMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CMatrix</em>' reference.
	 * @see #getCMatrix()
	 * @generated
	 */
    void setCMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>Cuf</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of Capacitance, each phase, for each step, microfarads.  See Rules for NumSteps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cuf</em>' attribute list.
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_Cuf()
	 * @model
	 * @generated
	 */
    EList<Double> getCuf();

    /**
	 * Returns the value of the '<em><b>R</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of series resistance in each phase (line), ohms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R</em>' attribute list.
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_R()
	 * @model
	 * @generated
	 */
    EList<Double> getR();

    /**
	 * Returns the value of the '<em><b>Xl</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of series inductive reactance(s) in each phase (line) for filter,
	 *     # ohms at base frequency. Use this OR "h" property to define filter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xl</em>' attribute list.
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_Xl()
	 * @model
	 * @generated
	 */
    EList<Double> getXl();

    /**
	 * Returns the value of the '<em><b>Harm</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of harmonics to which each step is tuned. Zero is interpreted as meaning zero reactance (no filter).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Harm</em>' attribute list.
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_Harm()
	 * @model
	 * @generated
	 */
    EList<Double> getHarm();

    /**
	 * Returns the value of the '<em><b>NSteps</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of steps in this capacitor bank. Default = 1. Forces reallocation of the capacitance, reactor, and states array.  Rules:  If this property was previously =1, the value in the kvar property is divided equally among the steps. The kvar property does not need to be reset if that is accurate.  If the Cuf or Cmatrix property was used previously, all steps are set to the value of the first step.  The states property is set to all steps on. All filter steps are set to the same harmonic.  If this property was previously >1, the arrays are reallocated, but no values are altered. You must SUBSEQUENTLY assign all array properties.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NSteps</em>' attribute.
	 * @see #setNSteps(int)
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_NSteps()
	 * @model default="1"
	 * @generated
	 */
    int getNSteps();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Capacitor#getNSteps <em>NSteps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NSteps</em>' attribute.
	 * @see #getNSteps()
	 * @generated
	 */
    void setNSteps(int value);

    /**
	 * Returns the value of the '<em><b>States</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Boolean}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of integers {1|0} states representing the state of each step (on|off). Defaults to 1 when reallocated (on).  Capcontrol will modify this array as it turns steps on or off.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>States</em>' attribute list.
	 * @see electrickery.delivery.DeliveryPackage#getCapacitor_States()
	 * @model
	 * @generated
	 */
    EList<Boolean> getStates();

} // Capacitor
