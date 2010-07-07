/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

import electrickery.common.connectionType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reactor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Uses same rules as Capacitor and Fault for connections
 * 
 * Implemented as a two-terminal constant impedance (Power Delivery Element)
 * Defaults to a Shunt Reactor but can be connected as a two-terminal series
 * reactor
 * 
 * If Parallel=Yes, then the R and X components are treated as being in
 * parallel.
 * 
 * Bus2 connection defaults to 0 node of Bus1 (if Bus2 has the default bus
 * connection at the time Bus1 is defined.  Therefore, if only Bus1 is
 * specified, a shunt Reactor results. If delta connected, Bus2 is set to node
 * zero of Bus1 and nothing is returned in the lower half of YPrim - all
 * zeroes.
 * 
 * If an ungrounded wye is desired, explicitly set Bus2= and set all nodes the
 * same, e.g.
 *     Bus1.4.4.4   (uses 4th node of Bus1 as neutral point)
 *     or BusNew.1.1.1  (makes a new bus for the neutral point)
 * You must specify the nodes or you will get a series Reactor!
 * 
 * A series Reactor is specified simply by setting bus2 and declaring the
 * connection to be Wye.  If the connection is specified as delta, nothing
 * will be connected to Bus2. In fact the number of terminals is set to 1.
 * 
 * Reactance may be specified as:
 * 
 *  1.  kvar and kv ratings at base frequency.  impedance.  Specify kvar as
 *      total for all phases. For 1-phase, kV = Reactor coil kV rating.
 *      For 2 or 3-phase, kV is line-line three phase. For more than 3 phases,
 *      specify kV as actual coil voltage.
 *  2.  Series Resistance and Reactance in ohns at base frequency to be used
 *      in each phase.  If specified in this manner, the given value is always
 *      used whether wye or delta.
 *  3.  A R and X  matrices. If conn=wye then 2-terminal through device
 *      If conn=delta then 1-terminal. Ohms at base frequency
 *      Note that Rmatix may be in parallel with Xmatric (set parallel = Yes)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.delivery.Reactor#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getKVAr <em>KV Ar</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getRMatrix <em>RMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getXMatrix <em>XMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#isParallel <em>Parallel</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getR <em>R</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getX <em>X</em>}</li>
 *   <li>{@link electrickery.delivery.Reactor#getRp <em>Rp</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.delivery.DeliveryPackage#getReactor()
 * @model
 * @generated
 */
public interface Reactor extends PowerDeliveryElement {
    /**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of first bus.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_Bus1()
	 * @model
	 * @generated
	 */
    String getBus1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getBus1 <em>Bus1</em>}' attribute.
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
	 * Name of 2nd bus. Defaults to all phases connected to first bus, node 0. (Shunt Wye Connection) Not necessary to specify for delta (LL) connection
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus2</em>' attribute.
	 * @see #setBus2(String)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_Bus2()
	 * @model
	 * @generated
	 */
    String getBus2();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getBus2 <em>Bus2</em>}' attribute.
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
	 * Total kvar, all phases.  Evenly divided among phases. Only determines X.  Specify R separately.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Ar</em>' attribute.
	 * @see #setKVAr(double)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_KVAr()
	 * @model default="1200.0"
	 * @generated
	 */
    double getKVAr();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getKVAr <em>KV Ar</em>}' attribute.
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
	 * For 2, 3-phase, kV phase-phase. Otherwise specify actual coil rating.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_KV()
	 * @model default="12.47"
	 * @generated
	 */
    double getKV();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getKV <em>KV</em>}' attribute.
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
	 * Default is wye, which is equivalent to LN. If Delta, then only one terminal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #setConn(connectionType)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_Conn()
	 * @model default="Wye"
	 * @generated
	 */
    connectionType getConn();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
    void setConn(connectionType value);

    /**
	 * Returns the value of the '<em><b>RMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resistance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>RMatrix</em>' reference.
	 * @see #setRMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_RMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getRMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getRMatrix <em>RMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RMatrix</em>' reference.
	 * @see #getRMatrix()
	 * @generated
	 */
    void setRMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>XMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reactance matrix, lower triangle, ohms at base frequency. Order of the matrix is the number of phases.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>XMatrix</em>' reference.
	 * @see #setXMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_XMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getXMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getXMatrix <em>XMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XMatrix</em>' reference.
	 * @see #getXMatrix()
	 * @generated
	 */
    void setXMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Signifies R and X are to be interpreted as being in parallel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parallel</em>' attribute.
	 * @see #setParallel(boolean)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_Parallel()
	 * @model
	 * @generated
	 */
    boolean isParallel();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#isParallel <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel</em>' attribute.
	 * @see #isParallel()
	 * @generated
	 */
    void setParallel(boolean value);

    /**
	 * Returns the value of the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resistance, each phase, ohms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R</em>' attribute.
	 * @see #setR(double)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_R()
	 * @model
	 * @generated
	 */
    double getR();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getR <em>R</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R</em>' attribute.
	 * @see #getR()
	 * @generated
	 */
    void setR(double value);

    /**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reactance, each phase, ohms at base frequency.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(double)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_X()
	 * @model
	 * @generated
	 */
    double getX();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
    void setX(double value);

    /**
	 * Returns the value of the '<em><b>Rp</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resistance in parallel with R and X (the entire branch). Assumed infinite if not specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rp</em>' attribute.
	 * @see #setRp(double)
	 * @see electrickery.delivery.DeliveryPackage#getReactor_Rp()
	 * @model
	 * @generated
	 */
    double getRp();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Reactor#getRp <em>Rp</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rp</em>' attribute.
	 * @see #getRp()
	 * @generated
	 */
    void setRp(double value);

} // Reactor
