/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.delivery;

import electrickery.common.CircuitElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Power Delivery Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Power delivery elements usually consist of two or more multiphase
 * terminals.  Their basic function is to transport energy from one point to
 * another.  On the power system, the most common power delivery elements are
 * lines and transformers.  Thus, they generally have more than one terminal
 * (capacitors and reactors can be an exception when shunt-connected rather
 * than series-connected).  Power delivery elements are standard electrical
 * elements generally completely defined in the rms steady state by their
 * impedances.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.delivery.PowerDeliveryElement#getNormAmps <em>Norm Amps</em>}</li>
 *   <li>{@link electrickery.delivery.PowerDeliveryElement#getEmergAmps <em>Emerg Amps</em>}</li>
 *   <li>{@link electrickery.delivery.PowerDeliveryElement#getFaultRate <em>Fault Rate</em>}</li>
 *   <li>{@link electrickery.delivery.PowerDeliveryElement#getPctPerm <em>Pct Perm</em>}</li>
 *   <li>{@link electrickery.delivery.PowerDeliveryElement#getRepair <em>Repair</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.delivery.DeliveryPackage#getPowerDeliveryElement()
 * @model abstract="true"
 * @generated
 */
public interface PowerDeliveryElement extends CircuitElement {
	/**
	 * Returns the value of the '<em><b>Norm Amps</b></em>' attribute.
	 * The default value is <code>"400.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Normal rated current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm Amps</em>' attribute.
	 * @see #setNormAmps(double)
	 * @see electrickery.delivery.DeliveryPackage#getPowerDeliveryElement_NormAmps()
	 * @model default="400.0"
	 * @generated
	 */
	double getNormAmps();

	/**
	 * Sets the value of the '{@link electrickery.delivery.PowerDeliveryElement#getNormAmps <em>Norm Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Norm Amps</em>' attribute.
	 * @see #getNormAmps()
	 * @generated
	 */
	void setNormAmps(double value);

	/**
	 * Returns the value of the '<em><b>Emerg Amps</b></em>' attribute.
	 * The default value is <code>"600.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg Amps</em>' attribute.
	 * @see #setEmergAmps(double)
	 * @see electrickery.delivery.DeliveryPackage#getPowerDeliveryElement_EmergAmps()
	 * @model default="600.0"
	 * @generated
	 */
	double getEmergAmps();

	/**
	 * Sets the value of the '{@link electrickery.delivery.PowerDeliveryElement#getEmergAmps <em>Emerg Amps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg Amps</em>' attribute.
	 * @see #getEmergAmps()
	 * @generated
	 */
	void setEmergAmps(double value);

	/**
	 * Returns the value of the '<em><b>Fault Rate</b></em>' attribute.
	 * The default value is <code>"0.1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No. of failures per year.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fault Rate</em>' attribute.
	 * @see #setFaultRate(double)
	 * @see electrickery.delivery.DeliveryPackage#getPowerDeliveryElement_FaultRate()
	 * @model default="0.1"
	 * @generated
	 */
	double getFaultRate();

	/**
	 * Sets the value of the '{@link electrickery.delivery.PowerDeliveryElement#getFaultRate <em>Fault Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fault Rate</em>' attribute.
	 * @see #getFaultRate()
	 * @generated
	 */
	void setFaultRate(double value);

	/**
	 * Returns the value of the '<em><b>Pct Perm</b></em>' attribute.
	 * The default value is <code>"20.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent of failures that become permanent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Perm</em>' attribute.
	 * @see #setPctPerm(double)
	 * @see electrickery.delivery.DeliveryPackage#getPowerDeliveryElement_PctPerm()
	 * @model default="20.0"
	 * @generated
	 */
	double getPctPerm();

	/**
	 * Sets the value of the '{@link electrickery.delivery.PowerDeliveryElement#getPctPerm <em>Pct Perm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Perm</em>' attribute.
	 * @see #getPctPerm()
	 * @generated
	 */
	void setPctPerm(double value);

	/**
	 * Returns the value of the '<em><b>Repair</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hours to repair.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Repair</em>' attribute.
	 * @see #setRepair(int)
	 * @see electrickery.delivery.DeliveryPackage#getPowerDeliveryElement_Repair()
	 * @model default="3"
	 * @generated
	 */
	int getRepair();

	/**
	 * Sets the value of the '{@link electrickery.delivery.PowerDeliveryElement#getRepair <em>Repair</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repair</em>' attribute.
	 * @see #getRepair()
	 * @generated
	 */
	void setRepair(int value);

} // PowerDeliveryElement
