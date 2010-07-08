/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery;

import cern.colt.matrix.tdouble.DoubleMatrix2D;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fault</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One or more faults can be placed across any two buses in the circuit.
 * Like the capacitor, the second bus defaults to the ground node of the
 * same bus that bus1 is connected to.
 * 
 * The fault is basically an uncoupled, multiphase resistance branch. however,
 * you may also specify it as NODAL CONDUCTANCE (G) matrix, which will give
 * you complete control of a complex fault situation.
 * 
 * To eliminate a fault from the system after it has been defined, disable it.
 * 
 * In Monte Carlo Fault mode, the fault resistance is varied by the % std dev
 * specified If %Stddev is specified as zero (default), the resistance is
 * varied uniformly.
 * 
 * Fault may have its "ON" time specified (defaults to 0). When Time (t)
 * exceeds this value, the fault will be enabled.  Else it is disabled.
 * 
 * Fault may be designated as Temporary.  That is, after it is enabled, it
 * will disable itself if the fault current drops below the MinAmps value.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.delivery.Fault#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#getBus2 <em>Bus2</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#getR <em>R</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#getPctStdDev <em>Pct Std Dev</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#getGMatrix <em>GMatrix</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#getOnTime <em>On Time</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#isTemporary <em>Temporary</em>}</li>
 *   <li>{@link electrickery.delivery.Fault#getMinAmps <em>Min Amps</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.delivery.DeliveryPackage#getFault()
 * @model
 * @generated
 */
public interface Fault extends PowerDeliveryElement {
    /**
	 * Returns the value of the '<em><b>Bus1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of first bus.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' attribute.
	 * @see #setBus1(String)
	 * @see electrickery.delivery.DeliveryPackage#getFault_Bus1()
	 * @model
	 * @generated
	 */
    String getBus1();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getBus1 <em>Bus1</em>}' attribute.
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
	 * Name of 2nd bus.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus2</em>' attribute.
	 * @see #setBus2(String)
	 * @see electrickery.delivery.DeliveryPackage#getFault_Bus2()
	 * @model
	 * @generated
	 */
    String getBus2();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getBus2 <em>Bus2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus2</em>' attribute.
	 * @see #getBus2()
	 * @generated
	 */
    void setBus2(String value);

    /**
	 * Returns the value of the '<em><b>R</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resistance, each phase, ohms. Default is 0.0001. Assumed to be Mean value if gaussian random mode.Max value if uniform mode.  A Fault is actually a series resistance that defaults to a wye connection to ground on the second terminal.  You may reconnect the 2nd terminal to achieve whatever connection.  Use the Gmatrix property to specify an arbitrary conductance matrix.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>R</em>' attribute.
	 * @see #setR(double)
	 * @see electrickery.delivery.DeliveryPackage#getFault_R()
	 * @model
	 * @generated
	 */
    double getR();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getR <em>R</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>R</em>' attribute.
	 * @see #getR()
	 * @generated
	 */
    void setR(double value);

    /**
	 * Returns the value of the '<em><b>Pct Std Dev</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent standard deviation in resistance to assume for Monte Carlo fault (MF) solution mode for GAUSSIAN distribution. Default is 0 (no variation from mean).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Std Dev</em>' attribute.
	 * @see #setPctStdDev(double)
	 * @see electrickery.delivery.DeliveryPackage#getFault_PctStdDev()
	 * @model
	 * @generated
	 */
    double getPctStdDev();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getPctStdDev <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Std Dev</em>' attribute.
	 * @see #getPctStdDev()
	 * @generated
	 */
    void setPctStdDev(double value);

    /**
	 * Returns the value of the '<em><b>GMatrix</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this to specify a nodal conductance (G) matrix to represent some arbitrary resistance network. Specify in lower triangle form as usual for DSS matrices.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>GMatrix</em>' reference.
	 * @see #setGMatrix(DoubleMatrix2D)
	 * @see electrickery.delivery.DeliveryPackage#getFault_GMatrix()
	 * @model type="electrickery.DoubleMatrix2D"
	 * @generated
	 */
    DoubleMatrix2D getGMatrix();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getGMatrix <em>GMatrix</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>GMatrix</em>' reference.
	 * @see #getGMatrix()
	 * @generated
	 */
    void setGMatrix(DoubleMatrix2D value);

    /**
	 * Returns the value of the '<em><b>On Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time (sec) at which the fault is established for time varying simulations. Default is 0.0 (on at the beginning of the simulation)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>On Time</em>' attribute.
	 * @see #setOnTime(double)
	 * @see electrickery.delivery.DeliveryPackage#getFault_OnTime()
	 * @model
	 * @generated
	 */
    double getOnTime();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getOnTime <em>On Time</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Time</em>' attribute.
	 * @see #getOnTime()
	 * @generated
	 */
    void setOnTime(double value);

    /**
	 * Returns the value of the '<em><b>Temporary</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Designate whether the fault is temporary.  For Time-varying simulations, the fault will be removed if the current through the fault drops below the MINAMPS criteria.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temporary</em>' attribute.
	 * @see #setTemporary(boolean)
	 * @see electrickery.delivery.DeliveryPackage#getFault_Temporary()
	 * @model default="false"
	 * @generated
	 */
    boolean isTemporary();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#isTemporary <em>Temporary</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporary</em>' attribute.
	 * @see #isTemporary()
	 * @generated
	 */
    void setTemporary(boolean value);

    /**
	 * Returns the value of the '<em><b>Min Amps</b></em>' attribute.
	 * The default value is <code>"5.0"</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum amps that can sustain a temporary fault.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min Amps</em>' attribute.
	 * @see #setMinAmps(double)
	 * @see electrickery.delivery.DeliveryPackage#getFault_MinAmps()
	 * @model default="5.0"
	 * @generated
	 */
    double getMinAmps();

    /**
	 * Sets the value of the '{@link electrickery.delivery.Fault#getMinAmps <em>Min Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Amps</em>' attribute.
	 * @see #getMinAmps()
	 * @generated
	 */
    void setMinAmps(double value);

} // Fault
