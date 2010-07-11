/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control;

import electrickery.conversion.Storage;

import electrickery.general.LoadShape;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Storage Controller</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A StorageController is a control element that is connected to a terminal of
 * another circuit element and sends dispatch  signals to a fleet of energy
 * storage elements it controls
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.control.StorageController#getKWTarget <em>KW Target</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPctKWBand <em>Pct KW Band</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPFTarget <em>PF Target</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPFBand <em>PF Band</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getElements <em>Elements</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getWeights <em>Weights</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getModeDischarge <em>Mode Discharge</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getModeCharge <em>Mode Charge</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getTimeDischargeTrigger <em>Time Discharge Trigger</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getTimeChargeTrigger <em>Time Charge Trigger</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPctRateKW <em>Pct Rate KW</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPctRateKVAr <em>Pct Rate KV Ar</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPctRateCharge <em>Pct Rate Charge</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPctReserve <em>Pct Reserve</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getKWhTotal <em>KWh Total</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getKWTotal <em>KW Total</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getKWhActual <em>KWh Actual</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getKWActual <em>KW Actual</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getKWNeed <em>KW Need</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getPctParticipation <em>Pct Participation</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.control.StorageController#isEventLog <em>Event Log</em>}</li>
 *   <li>{@link electrickery.control.StorageController#isVarDispatch <em>Var Dispatch</em>}</li>
 *   <li>{@link electrickery.control.StorageController#getInhibitTime <em>Inhibit Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.control.ControlPackage#getStorageController()
 * @model
 * @generated
 */
public interface StorageController extends ControlElement {
	/**
	 * Returns the value of the '<em><b>KW Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kW target for Discharging. The storage element fleet is dispatched to try to
	 * hold the power in band at least until the storage is depleted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Target</em>' attribute.
	 * @see #setKWTarget(double)
	 * @see electrickery.control.ControlPackage#getStorageController_KWTarget()
	 * @model
	 * @generated
	 */
	double getKWTarget();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getKWTarget <em>KW Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Target</em>' attribute.
	 * @see #getKWTarget()
	 * @generated
	 */
	void setKWTarget(double value);

	/**
	 * Returns the value of the '<em><b>Pct KW Band</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bandwidth (% of Target kW) of the dead band around the kW target value. Default
	 * is 2% (+/-1%).No dispatch changes are attempted If the power in the monitored
	 * terminal stays within this band.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct KW Band</em>' attribute.
	 * @see #setPctKWBand(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PctKWBand()
	 * @model
	 * @generated
	 */
	double getPctKWBand();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPctKWBand <em>Pct KW Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct KW Band</em>' attribute.
	 * @see #getPctKWBand()
	 * @generated
	 */
	void setPctKWBand(double value);

	/**
	 * Returns the value of the '<em><b>PF Target</b></em>' attribute.
	 * The default value is <code>"0.96"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Power Factor target for dispatching the reactive power. Default is 0.96. The
	 * reactive power of the storage element fleet is dispatched to try to hold the
	 * power factor in band. It is assumed that the storage element inverter can
	 * produce kvar up to its kVA limit regardless of storage level.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PF Target</em>' attribute.
	 * @see #setPFTarget(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PFTarget()
	 * @model default="0.96"
	 * @generated
	 */
	double getPFTarget();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPFTarget <em>PF Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PF Target</em>' attribute.
	 * @see #getPFTarget()
	 * @generated
	 */
	void setPFTarget(double value);

	/**
	 * Returns the value of the '<em><b>PF Band</b></em>' attribute.
	 * The default value is <code>"0.04"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bandwidth of the Target power factor of the monitored element. Of the dead band
	 * around the kvar target value. Default is 0.04 (+/- 0.02).No dispatch changes of the
	 * kvar are attempted If the power factor of the monitored terminal stays within this
	 * band.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PF Band</em>' attribute.
	 * @see #setPFBand(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PFBand()
	 * @model default="0.04"
	 * @generated
	 */
	double getPFBand();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPFBand <em>PF Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PF Band</em>' attribute.
	 * @see #getPFBand()
	 * @generated
	 */
	void setPFBand(double value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link electrickery.conversion.Storage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Storage elements to be controlled.  If not specified, all storage elements in
	 * the circuit are assumed dispatched by this controller.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see electrickery.control.ControlPackage#getStorageController_Elements()
	 * @model
	 * @generated
	 */
	EList<Storage> getElements();

	/**
	 * Returns the value of the '<em><b>Weights</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Proportional weights corresponding to each storage element in 'elements'.
	 * The needed kW or kvar to get back to center band is dispatched to each
	 * storage element according to these weights. Default is to set all weights
	 * to 1.0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Weights</em>' attribute list.
	 * @see electrickery.control.ControlPackage#getStorageController_Weights()
	 * @model default="1.0"
	 * @generated
	 */
	EList<Double> getWeights();

	/**
	 * Returns the value of the '<em><b>Mode Discharge</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.control.dischargeMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mode of operation for the DISCHARGE FUNCTION of this controller. In PeakShave
	 * mode (Default), the control attempts to discharge storage to keep power in the
	 * monitored element below the kWTarget. In Follow mode, the control is triggered
	 * by time and resets the kWTarget value to the present monitored element
	 * power. It then attempts to discharge storage to keep power in the monitored
	 * element below the new kWTarget. See TimeDischargeTrigger.In Support mode,
	 * the control operates oppositely of PeakShave mode: storage is discharged to
	 * keep kW power output up near the target. In Loadshape mode, both charging and
	 * discharging precisely follows the per unit loadshape. Storage is discharged when
	 * the loadshape value is positive. In Time mode, the storage discharge is turned on
	 * at the specified pctRatekW and pctRatekvar at the specified discharge trigger
	 * time in fractional hours.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mode Discharge</em>' attribute.
	 * @see electrickery.control.dischargeMode
	 * @see #setModeDischarge(dischargeMode)
	 * @see electrickery.control.ControlPackage#getStorageController_ModeDischarge()
	 * @model
	 * @generated
	 */
	dischargeMode getModeDischarge();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getModeDischarge <em>Mode Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode Discharge</em>' attribute.
	 * @see electrickery.control.dischargeMode
	 * @see #getModeDischarge()
	 * @generated
	 */
	void setModeDischarge(dischargeMode value);

	/**
	 * Returns the value of the '<em><b>Mode Charge</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.control.chargeMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mode of operation for the CHARGE FUNCTION of this controller. In Loadshape
	 * mode, both charging and discharging precisely follows the per unit loadshape.
	 * Storage is charged when the loadshape value is negative. In Time mode, the
	 * storage charging FUNCTION is triggered at the specified pctRateCharge at
	 * the specified sharge trigger time in fractional hours.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mode Charge</em>' attribute.
	 * @see electrickery.control.chargeMode
	 * @see #setModeCharge(chargeMode)
	 * @see electrickery.control.ControlPackage#getStorageController_ModeCharge()
	 * @model
	 * @generated
	 */
	chargeMode getModeCharge();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getModeCharge <em>Mode Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode Charge</em>' attribute.
	 * @see electrickery.control.chargeMode
	 * @see #getModeCharge()
	 * @generated
	 */
	void setModeCharge(chargeMode value);

	/**
	 * Returns the value of the '<em><b>Time Discharge Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default time of day (hr) for initiating Discharging of the fleet. During Follow
	 * or Time mode discharging is triggered at a fixed time each day at this hour.
	 * If Follow mode, storage will be discharged to attempt to hold the load at or
	 * below the power level at the time of triggering. In Time mode, the discharge
	 * is based on the pctRatekW property value. Set this to a negative value to
	 * ignore. Default is 12.0 for Follow mode; otherwise it is -1 (ignored).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Discharge Trigger</em>' attribute.
	 * @see #setTimeDischargeTrigger(double)
	 * @see electrickery.control.ControlPackage#getStorageController_TimeDischargeTrigger()
	 * @model
	 * @generated
	 */
	double getTimeDischargeTrigger();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getTimeDischargeTrigger <em>Time Discharge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Discharge Trigger</em>' attribute.
	 * @see #getTimeDischargeTrigger()
	 * @generated
	 */
	void setTimeDischargeTrigger(double value);

	/**
	 * Returns the value of the '<em><b>Time Charge Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default time of day (hr) for initiating charging in Time control mode. Set this
	 * to a negative value to ignore. Default is 2.0.  (0200).When this value is >0 the
	 * storage fleet is set to charging at this time regardless of other control criteria
	 * to make sure storage is topped off for the next discharge cycle.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Charge Trigger</em>' attribute.
	 * @see #setTimeChargeTrigger(double)
	 * @see electrickery.control.ControlPackage#getStorageController_TimeChargeTrigger()
	 * @model
	 * @generated
	 */
	double getTimeChargeTrigger();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getTimeChargeTrigger <em>Time Charge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Charge Trigger</em>' attribute.
	 * @see #getTimeChargeTrigger()
	 * @generated
	 */
	void setTimeChargeTrigger(double value);

	/**
	 * Returns the value of the '<em><b>Pct Rate KW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the kW discharge rate in % of rated capacity for each element of the
	 * fleet. Applies to TIME control mode or anytime discharging is triggered by
	 * time.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Rate KW</em>' attribute.
	 * @see #setPctRateKW(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PctRateKW()
	 * @model
	 * @generated
	 */
	double getPctRateKW();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPctRateKW <em>Pct Rate KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Rate KW</em>' attribute.
	 * @see #getPctRateKW()
	 * @generated
	 */
	void setPctRateKW(double value);

	/**
	 * Returns the value of the '<em><b>Pct Rate KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the kvar discharge rate in % of rated capacity for each element of the
	 * fleet. Applies to TIME control mode or anytime discharging is triggered by
	 * time.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Rate KV Ar</em>' attribute.
	 * @see #setPctRateKVAr(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PctRateKVAr()
	 * @model
	 * @generated
	 */
	double getPctRateKVAr();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPctRateKVAr <em>Pct Rate KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Rate KV Ar</em>' attribute.
	 * @see #getPctRateKVAr()
	 * @generated
	 */
	void setPctRateKVAr(double value);

	/**
	 * Returns the value of the '<em><b>Pct Rate Charge</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sets the kW charging rate in % of rated capacity for each element of the fleet.
	 * Applies to TIME control mode and anytime charging mode is entered due to a
	 * time trigger.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Rate Charge</em>' attribute.
	 * @see #setPctRateCharge(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PctRateCharge()
	 * @model
	 * @generated
	 */
	double getPctRateCharge();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPctRateCharge <em>Pct Rate Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Rate Charge</em>' attribute.
	 * @see #getPctRateCharge()
	 * @generated
	 */
	void setPctRateCharge(double value);

	/**
	 * Returns the value of the '<em><b>Pct Reserve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Use this property to change the % reserve for each storage element under
	 * control of this controller. This might be used, for example, to allow deeper
	 * discharges of storage or in case of emergency operation to use the remainder
	 * of the storage element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Reserve</em>' attribute.
	 * @see #setPctReserve(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PctReserve()
	 * @model
	 * @generated
	 */
	double getPctReserve();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPctReserve <em>Pct Reserve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Reserve</em>' attribute.
	 * @see #getPctReserve()
	 * @generated
	 */
	void setPctReserve(double value);

	/**
	 * Returns the value of the '<em><b>KWh Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total rated kWh energy storage capacity of storage elements controlled by
	 * this controller.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KWh Total</em>' attribute.
	 * @see #isSetKWhTotal()
	 * @see #unsetKWhTotal()
	 * @see #setKWhTotal(double)
	 * @see electrickery.control.ControlPackage#getStorageController_KWhTotal()
	 * @model unsettable="true"
	 * @generated
	 */
	double getKWhTotal();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getKWhTotal <em>KWh Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KWh Total</em>' attribute.
	 * @see #isSetKWhTotal()
	 * @see #unsetKWhTotal()
	 * @see #getKWhTotal()
	 * @generated
	 */
	void setKWhTotal(double value);

	/**
	 * Unsets the value of the '{@link electrickery.control.StorageController#getKWhTotal <em>KWh Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetKWhTotal()
	 * @see #getKWhTotal()
	 * @see #setKWhTotal(double)
	 * @generated
	 */
	void unsetKWhTotal();

	/**
	 * Returns whether the value of the '{@link electrickery.control.StorageController#getKWhTotal <em>KWh Total</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>KWh Total</em>' attribute is set.
	 * @see #unsetKWhTotal()
	 * @see #getKWhTotal()
	 * @see #setKWhTotal(double)
	 * @generated
	 */
	boolean isSetKWhTotal();

	/**
	 * Returns the value of the '<em><b>KW Total</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Total rated kW power capacity of storage elements controlled by this
	 * controller.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Total</em>' attribute.
	 * @see #isSetKWTotal()
	 * @see #unsetKWTotal()
	 * @see #setKWTotal(double)
	 * @see electrickery.control.ControlPackage#getStorageController_KWTotal()
	 * @model unsettable="true"
	 * @generated
	 */
	double getKWTotal();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getKWTotal <em>KW Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Total</em>' attribute.
	 * @see #isSetKWTotal()
	 * @see #unsetKWTotal()
	 * @see #getKWTotal()
	 * @generated
	 */
	void setKWTotal(double value);

	/**
	 * Unsets the value of the '{@link electrickery.control.StorageController#getKWTotal <em>KW Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetKWTotal()
	 * @see #getKWTotal()
	 * @see #setKWTotal(double)
	 * @generated
	 */
	void unsetKWTotal();

	/**
	 * Returns whether the value of the '{@link electrickery.control.StorageController#getKWTotal <em>KW Total</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>KW Total</em>' attribute is set.
	 * @see #unsetKWTotal()
	 * @see #getKWTotal()
	 * @see #setKWTotal(double)
	 * @generated
	 */
	boolean isSetKWTotal();

	/**
	 * Returns the value of the '<em><b>KWh Actual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Actual kWh output of all controlled storage elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KWh Actual</em>' attribute.
	 * @see #isSetKWhActual()
	 * @see #unsetKWhActual()
	 * @see #setKWhActual(double)
	 * @see electrickery.control.ControlPackage#getStorageController_KWhActual()
	 * @model unsettable="true"
	 * @generated
	 */
	double getKWhActual();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getKWhActual <em>KWh Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KWh Actual</em>' attribute.
	 * @see #isSetKWhActual()
	 * @see #unsetKWhActual()
	 * @see #getKWhActual()
	 * @generated
	 */
	void setKWhActual(double value);

	/**
	 * Unsets the value of the '{@link electrickery.control.StorageController#getKWhActual <em>KWh Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetKWhActual()
	 * @see #getKWhActual()
	 * @see #setKWhActual(double)
	 * @generated
	 */
	void unsetKWhActual();

	/**
	 * Returns whether the value of the '{@link electrickery.control.StorageController#getKWhActual <em>KWh Actual</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>KWh Actual</em>' attribute is set.
	 * @see #unsetKWhActual()
	 * @see #getKWhActual()
	 * @see #setKWhActual(double)
	 * @generated
	 */
	boolean isSetKWhActual();

	/**
	 * Returns the value of the '<em><b>KW Actual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Actual kW output of all controlled storage elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Actual</em>' attribute.
	 * @see #isSetKWActual()
	 * @see #unsetKWActual()
	 * @see #setKWActual(double)
	 * @see electrickery.control.ControlPackage#getStorageController_KWActual()
	 * @model unsettable="true"
	 * @generated
	 */
	double getKWActual();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getKWActual <em>KW Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Actual</em>' attribute.
	 * @see #isSetKWActual()
	 * @see #unsetKWActual()
	 * @see #getKWActual()
	 * @generated
	 */
	void setKWActual(double value);

	/**
	 * Unsets the value of the '{@link electrickery.control.StorageController#getKWActual <em>KW Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetKWActual()
	 * @see #getKWActual()
	 * @see #setKWActual(double)
	 * @generated
	 */
	void unsetKWActual();

	/**
	 * Returns whether the value of the '{@link electrickery.control.StorageController#getKWActual <em>KW Actual</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>KW Actual</em>' attribute is set.
	 * @see #unsetKWActual()
	 * @see #getKWActual()
	 * @see #setKWActual(double)
	 * @generated
	 */
	boolean isSetKWActual();

	/**
	 * Returns the value of the '<em><b>KW Need</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * KW needed to meet target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Need</em>' attribute.
	 * @see #isSetKWNeed()
	 * @see #unsetKWNeed()
	 * @see #setKWNeed(double)
	 * @see electrickery.control.ControlPackage#getStorageController_KWNeed()
	 * @model unsettable="true"
	 * @generated
	 */
	double getKWNeed();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getKWNeed <em>KW Need</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Need</em>' attribute.
	 * @see #isSetKWNeed()
	 * @see #unsetKWNeed()
	 * @see #getKWNeed()
	 * @generated
	 */
	void setKWNeed(double value);

	/**
	 * Unsets the value of the '{@link electrickery.control.StorageController#getKWNeed <em>KW Need</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetKWNeed()
	 * @see #getKWNeed()
	 * @see #setKWNeed(double)
	 * @generated
	 */
	void unsetKWNeed();

	/**
	 * Returns whether the value of the '{@link electrickery.control.StorageController#getKWNeed <em>KW Need</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>KW Need</em>' attribute is set.
	 * @see #unsetKWNeed()
	 * @see #getKWNeed()
	 * @see #setKWNeed(double)
	 * @generated
	 */
	boolean isSetKWNeed();

	/**
	 * Returns the value of the '<em><b>Pct Participation</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Participation factor, %.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Participation</em>' attribute.
	 * @see #setPctParticipation(double)
	 * @see electrickery.control.ControlPackage#getStorageController_PctParticipation()
	 * @model default="100.0"
	 * @generated
	 */
	double getPctParticipation();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getPctParticipation <em>Pct Participation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Participation</em>' attribute.
	 * @see #getPctParticipation()
	 * @generated
	 */
	void setPctParticipation(double value);

	/**
	 * Returns the value of the '<em><b>Yearly</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Yearly</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Yearly</em>' reference.
	 * @see #setYearly(LoadShape)
	 * @see electrickery.control.ControlPackage#getStorageController_Yearly()
	 * @model
	 * @generated
	 */
	LoadShape getYearly();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getYearly <em>Yearly</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Yearly</em>' reference.
	 * @see #getYearly()
	 * @generated
	 */
	void setYearly(LoadShape value);

	/**
	 * Returns the value of the '<em><b>Daily</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Daily</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Daily</em>' reference.
	 * @see #setDaily(LoadShape)
	 * @see electrickery.control.ControlPackage#getStorageController_Daily()
	 * @model
	 * @generated
	 */
	LoadShape getDaily();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getDaily <em>Daily</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Daily</em>' reference.
	 * @see #getDaily()
	 * @generated
	 */
	void setDaily(LoadShape value);

	/**
	 * Returns the value of the '<em><b>Duty</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duty</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duty</em>' reference.
	 * @see #setDuty(LoadShape)
	 * @see electrickery.control.ControlPackage#getStorageController_Duty()
	 * @model
	 * @generated
	 */
	LoadShape getDuty();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getDuty <em>Duty</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duty</em>' reference.
	 * @see #getDuty()
	 * @generated
	 */
	void setDuty(LoadShape value);

	/**
	 * Returns the value of the '<em><b>Event Log</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Log control actions to Eventlog.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Log</em>' attribute.
	 * @see #setEventLog(boolean)
	 * @see electrickery.control.ControlPackage#getStorageController_EventLog()
	 * @model
	 * @generated
	 */
	boolean isEventLog();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#isEventLog <em>Event Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Log</em>' attribute.
	 * @see #isEventLog()
	 * @generated
	 */
	void setEventLog(boolean value);

	/**
	 * Returns the value of the '<em><b>Var Dispatch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag to indicate whether or not to disatch vars as well as watts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Var Dispatch</em>' attribute.
	 * @see #setVarDispatch(boolean)
	 * @see electrickery.control.ControlPackage#getStorageController_VarDispatch()
	 * @model
	 * @generated
	 */
	boolean isVarDispatch();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#isVarDispatch <em>Var Dispatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Var Dispatch</em>' attribute.
	 * @see #isVarDispatch()
	 * @generated
	 */
	void setVarDispatch(boolean value);

	/**
	 * Returns the value of the '<em><b>Inhibit Time</b></em>' attribute.
	 * The default value is <code>"5"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hours (integer) to inhibit Discharging after going into Charge mode.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inhibit Time</em>' attribute.
	 * @see #setInhibitTime(int)
	 * @see electrickery.control.ControlPackage#getStorageController_InhibitTime()
	 * @model default="5"
	 * @generated
	 */
	int getInhibitTime();

	/**
	 * Sets the value of the '{@link electrickery.control.StorageController#getInhibitTime <em>Inhibit Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inhibit Time</em>' attribute.
	 * @see #getInhibitTime()
	 * @generated
	 */
	void setInhibitTime(int value);

} // StorageController
