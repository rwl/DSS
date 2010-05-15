/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.delivery;

import dss.common.tripAction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fuse</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Fuse is a control element that is connected to a terminal of a
 * circuit element and controls the switches in the same or another terminal.
 * 
 * The control is usually placed in the terminal of a line or transformer, but
 * it could be any element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.delivery.Fuse#getMonitoredObj <em>Monitored Obj</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getMonitorTerm <em>Monitor Term</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getSwitchedObj <em>Switched Obj</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getSwitchedTerm <em>Switched Term</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getFuseCurve <em>Fuse Curve</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getRatedCurrent <em>Rated Current</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getDelay <em>Delay</em>}</li>
 *   <li>{@link dss.delivery.Fuse#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.delivery.DeliveryPackage#getFuse()
 * @model
 * @generated
 */
public interface Fuse extends PowerDeliveryElement {
	/**
	 * Returns the value of the '<em><b>Monitored Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Full object name of the circuit element, typically a line, transformer, load, or generator, to which the Fuse is connected. This is the "monitored" element. There is no default; must be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Monitored Obj</em>' attribute.
	 * @see #setMonitoredObj(String)
	 * @see dss.delivery.DeliveryPackage#getFuse_MonitoredObj()
	 * @model
	 * @generated
	 */
	String getMonitoredObj();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getMonitoredObj <em>Monitored Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitored Obj</em>' attribute.
	 * @see #getMonitoredObj()
	 * @generated
	 */
	void setMonitoredObj(String value);

	/**
	 * Returns the value of the '<em><b>Monitor Term</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the circuit element to which the Fuse is connected.  1 or 2, typically.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Monitor Term</em>' attribute.
	 * @see #setMonitorTerm(int)
	 * @see dss.delivery.DeliveryPackage#getFuse_MonitorTerm()
	 * @model default="1"
	 * @generated
	 */
	int getMonitorTerm();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getMonitorTerm <em>Monitor Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitor Term</em>' attribute.
	 * @see #getMonitorTerm()
	 * @generated
	 */
	void setMonitorTerm(int value);

	/**
	 * Returns the value of the '<em><b>Switched Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of circuit element switch that the Fuse controls. Specify the full object name. Defaults to the same as the Monitored element. This is the "controlled" element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switched Obj</em>' attribute.
	 * @see #setSwitchedObj(String)
	 * @see dss.delivery.DeliveryPackage#getFuse_SwitchedObj()
	 * @model
	 * @generated
	 */
	String getSwitchedObj();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getSwitchedObj <em>Switched Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switched Obj</em>' attribute.
	 * @see #getSwitchedObj()
	 * @generated
	 */
	void setSwitchedObj(String value);

	/**
	 * Returns the value of the '<em><b>Switched Term</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of the terminal of the controlled element in which the switch is controlled by the Fuse. 1 or 2, typically.  Assumes all phases of the element have a fuse of this type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Switched Term</em>' attribute.
	 * @see #setSwitchedTerm(int)
	 * @see dss.delivery.DeliveryPackage#getFuse_SwitchedTerm()
	 * @model default="1"
	 * @generated
	 */
	int getSwitchedTerm();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getSwitchedTerm <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switched Term</em>' attribute.
	 * @see #getSwitchedTerm()
	 * @generated
	 */
	void setSwitchedTerm(int value);

	/**
	 * Returns the value of the '<em><b>Fuse Curve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of the TCC Curve object that determines the fuse blowing.  Must have been previously defined as a TCC_Curve object. Default is "Tlink".  Multiplying the current values in the curve by the "RatedCurrent" value gives the actual current.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fuse Curve</em>' attribute.
	 * @see #setFuseCurve(String)
	 * @see dss.delivery.DeliveryPackage#getFuse_FuseCurve()
	 * @model
	 * @generated
	 */
	String getFuseCurve();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getFuseCurve <em>Fuse Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fuse Curve</em>' attribute.
	 * @see #getFuseCurve()
	 * @generated
	 */
	void setFuseCurve(String value);

	/**
	 * Returns the value of the '<em><b>Rated Current</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Multiplier or actual phase amps for the phase TCC curve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rated Current</em>' attribute.
	 * @see #setRatedCurrent(double)
	 * @see dss.delivery.DeliveryPackage#getFuse_RatedCurrent()
	 * @model default="1.0"
	 * @generated
	 */
	double getRatedCurrent();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getRatedCurrent <em>Rated Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rated Current</em>' attribute.
	 * @see #getRatedCurrent()
	 * @generated
	 */
	void setRatedCurrent(double value);

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fixed delay time (sec) added to Fuse blowing time determined from the TCC curve. Used to represent fuse clearing time or any other delay.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see dss.delivery.DeliveryPackage#getFuse_Delay()
	 * @model
	 * @generated
	 */
	double getDelay();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(double value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link dss.common.tripAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action that overrides the Fuse control. Simulates manual control on Fuse "Trip" or "Open" causes the controlled element to open and lock out. "Close" causes the controlled element to close and the Fuse to reset.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see dss.common.tripAction
	 * @see #setAction(tripAction)
	 * @see dss.delivery.DeliveryPackage#getFuse_Action()
	 * @model
	 * @generated
	 */
	tripAction getAction();

	/**
	 * Sets the value of the '{@link dss.delivery.Fuse#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see dss.common.tripAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(tripAction value);

} // Fuse
