/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion;

import electrickery.common.Bus;
import electrickery.common.connectionType;

import electrickery.general.LoadShape;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Storage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The storage element is essentially a generator that can be dispatched
 * to either produce power or consume power commensurate with rating and
 * amount of stored energy.
 * 
 * The storage element can also produce or absorb vars within the kVA rating of the inverter.
 * That is, a StorageController object requests kvar and the storage element provides them if
 * it has any capacity left. The storage element can produce/absorb kvar while idling.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.conversion.Storage#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKW <em>KW</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPF <em>PF</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKVAR <em>KVAR</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKWRated <em>KW Rated</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKWhRated <em>KWh Rated</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getKWhStored <em>KWh Stored</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctStored <em>Pct Stored</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctReserve <em>Pct Reserve</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getState <em>State</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctDischarge <em>Pct Discharge</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctCharge <em>Pct Charge</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctEffCharge <em>Pct Eff Charge</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctEffDischarge <em>Pct Eff Discharge</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctIdlingKW <em>Pct Idling KW</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctIdlingKVAr <em>Pct Idling KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctR <em>Pct R</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getPctX <em>Pct X</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getModel <em>Model</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getVMinPU <em>VMin PU</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getVMaxPU <em>VMax PU</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getDispMode <em>Disp Mode</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getDischargeTrigger <em>Discharge Trigger</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getChargeTrigger <em>Charge Trigger</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getTimeChargeTrig <em>Time Charge Trig</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getClass_ <em>Class</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getUserModel <em>User Model</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#getUserData <em>User Data</em>}</li>
 *   <li>{@link electrickery.conversion.Storage#isDebugTrace <em>Debug Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.conversion.ConversionPackage#getStorage()
 * @model
 * @generated
 */
public interface Storage extends PowerConversionElement {
	/**
	 * Returns the value of the '<em><b>Bus1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bus to which the Storage element is connected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus1</em>' reference.
	 * @see #setBus1(Bus)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Bus1()
	 * @model
	 * @generated
	 */
	Bus getBus1();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getBus1 <em>Bus1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus1</em>' reference.
	 * @see #getBus1()
	 * @generated
	 */
	void setBus1(Bus value);

	/**
	 * Returns the value of the '<em><b>KV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nominal rated (1.0 per unit) voltage, kV, for Storage element. For 2- and 3-phase
	 * Storage elements, specify phase-phase kV. Otherwise, specify actual kV across
	 * each branch of the Storage element. If wye (star), specify phase-neutral kV. If
	 * delta or phase-phase connected, specify phase-phase kV.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV</em>' attribute.
	 * @see #setKV(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KV()
	 * @model
	 * @generated
	 */
	double getKV();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKV <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV</em>' attribute.
	 * @see #getKV()
	 * @generated
	 */
	void setKV(double value);

	/**
	 * Returns the value of the '<em><b>KW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The present kW value.  A positive value denotes power coming OUT of the
	 * element, which is the opposite of a Load element. A negative value indicates
	 * the Storage element is in Charging state. This value is modified internally
	 * depending on the dispatch mode.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW</em>' attribute.
	 * @see #setKW(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KW()
	 * @model
	 * @generated
	 */
	double getKW();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKW <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW</em>' attribute.
	 * @see #getKW()
	 * @generated
	 */
	void setKW(double value);

	/**
	 * Returns the value of the '<em><b>PF</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nominally, the power factor for discharging (acting as a generator). Default
	 * is 1.0. Setting this property will also set the kvar property. Enter negative
	 * for leading powerfactor (when kW and kvar have opposite signs.) A positive
	 * power factor for a generator signifies that the Storage element produces
	 * vars as is typical for a generator.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PF</em>' attribute.
	 * @see #setPF(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PF()
	 * @model default="1.0"
	 * @generated
	 */
	double getPF();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPF <em>PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PF</em>' attribute.
	 * @see #getPF()
	 * @generated
	 */
	void setPF(double value);

	/**
	 * Returns the value of the '<em><b>Conn</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.connectionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conn</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #setConn(connectionType)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Conn()
	 * @model
	 * @generated
	 */
	connectionType getConn();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getConn <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conn</em>' attribute.
	 * @see electrickery.common.connectionType
	 * @see #getConn()
	 * @generated
	 */
	void setConn(connectionType value);

	/**
	 * Returns the value of the '<em><b>KVAR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The present kVAr value.  Alternative to specifying the power factor.  Side effect: 
	 * the power factor value is altered to agree based on present value of kW.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVAR</em>' attribute.
	 * @see #setKVAR(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KVAR()
	 * @model
	 * @generated
	 */
	double getKVAR();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKVAR <em>KVAR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVAR</em>' attribute.
	 * @see #getKVAR()
	 * @generated
	 */
	void setKVAR(double value);

	/**
	 * Returns the value of the '<em><b>KVA</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kVA rating of power output. Defaults to rated kW. Used as the base for Dynamics
	 * mode and Harmonics mode values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KVA</em>' attribute.
	 * @see #setKVA(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KVA()
	 * @model
	 * @generated
	 */
	double getKVA();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKVA <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KVA</em>' attribute.
	 * @see #getKVA()
	 * @generated
	 */
	void setKVA(double value);

	/**
	 * Returns the value of the '<em><b>KW Rated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * kW rating of power output. Side effect: Sets the KVA property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KW Rated</em>' attribute.
	 * @see #setKWRated(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KWRated()
	 * @model
	 * @generated
	 */
	double getKWRated();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKWRated <em>KW Rated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KW Rated</em>' attribute.
	 * @see #getKWRated()
	 * @generated
	 */
	void setKWRated(double value);

	/**
	 * Returns the value of the '<em><b>KWh Rated</b></em>' attribute.
	 * The default value is <code>"50.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rated storage capacity in kWh. Default is 50.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KWh Rated</em>' attribute.
	 * @see #setKWhRated(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KWhRated()
	 * @model default="50.0"
	 * @generated
	 */
	double getKWhRated();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKWhRated <em>KWh Rated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KWh Rated</em>' attribute.
	 * @see #getKWhRated()
	 * @generated
	 */
	void setKWhRated(double value);

	/**
	 * Returns the value of the '<em><b>KWh Stored</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Present amount of energy stored, kWh. Default is same as kWh rated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KWh Stored</em>' attribute.
	 * @see #setKWhStored(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_KWhStored()
	 * @model
	 * @generated
	 */
	double getKWhStored();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getKWhStored <em>KWh Stored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KWh Stored</em>' attribute.
	 * @see #getKWhStored()
	 * @generated
	 */
	void setKWhStored(double value);

	/**
	 * Returns the value of the '<em><b>Pct Stored</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Present amount of energy stored, % of rated kWh. Default is 100%.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Stored</em>' attribute.
	 * @see #setPctStored(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctStored()
	 * @model default="100.0"
	 * @generated
	 */
	double getPctStored();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctStored <em>Pct Stored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Stored</em>' attribute.
	 * @see #getPctStored()
	 * @generated
	 */
	void setPctStored(double value);

	/**
	 * Returns the value of the '<em><b>Pct Reserve</b></em>' attribute.
	 * The default value is <code>"20.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent of rated kWh storage capacity to be held in reserve for normal operation.
	 * Default = 20. This is treated as the minimum energy discharge level unless there
	 * is an emergency. For emergency operation set this property lower. Cannot be less
	 * than zero.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Reserve</em>' attribute.
	 * @see #setPctReserve(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctReserve()
	 * @model default="20.0"
	 * @generated
	 */
	double getPctReserve();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctReserve <em>Pct Reserve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Reserve</em>' attribute.
	 * @see #getPctReserve()
	 * @generated
	 */
	void setPctReserve(double value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.storageState}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Present operational state. In DISCHARGING mode, the Storage element
	 * acts as a generator and the kW property is positive. The element continues
	 * discharging at the scheduled output power level until the storage reaches
	 * the reserve value. Then the state reverts to IDLING. In the CHARGING
	 * state, the Storage element behaves like a Load and the kW property is
	 * negative. The element continues to charge until the max storage kWh is
	 * reached and Then switches to IDLING state. In IDLING state, the kW
	 * property shows zero. However, the resistive and reactive loss elements
	 * remain in the circuit and the power flow report will show power being
	 * consumed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see electrickery.conversion.storageState
	 * @see #setState(storageState)
	 * @see electrickery.conversion.ConversionPackage#getStorage_State()
	 * @model
	 * @generated
	 */
	storageState getState();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see electrickery.conversion.storageState
	 * @see #getState()
	 * @generated
	 */
	void setState(storageState value);

	/**
	 * Returns the value of the '<em><b>Pct Discharge</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Discharge rate (output power) in Percent of rated kW. Default = 100.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Discharge</em>' attribute.
	 * @see #setPctDischarge(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctDischarge()
	 * @model default="100.0"
	 * @generated
	 */
	double getPctDischarge();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctDischarge <em>Pct Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Discharge</em>' attribute.
	 * @see #getPctDischarge()
	 * @generated
	 */
	void setPctDischarge(double value);

	/**
	 * Returns the value of the '<em><b>Pct Charge</b></em>' attribute.
	 * The default value is <code>"100.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Charging rate (input power) in Percent of rated kW. Default = 100.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Charge</em>' attribute.
	 * @see #setPctCharge(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctCharge()
	 * @model default="100.0"
	 * @generated
	 */
	double getPctCharge();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctCharge <em>Pct Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Charge</em>' attribute.
	 * @see #getPctCharge()
	 * @generated
	 */
	void setPctCharge(double value);

	/**
	 * Returns the value of the '<em><b>Pct Eff Charge</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent efficiency for CHARGING the storage element. Default = 90.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Eff Charge</em>' attribute.
	 * @see #setPctEffCharge(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctEffCharge()
	 * @model
	 * @generated
	 */
	double getPctEffCharge();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctEffCharge <em>Pct Eff Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Eff Charge</em>' attribute.
	 * @see #getPctEffCharge()
	 * @generated
	 */
	void setPctEffCharge(double value);

	/**
	 * Returns the value of the '<em><b>Pct Eff Discharge</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent efficiency for DISCHARGING the storage element. Default = 90. Idling
	 * losses are handled by %IdlingkW property and are in addition to the charging
	 * and discharging efficiency losses in the power conversion process inside the
	 * unit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Eff Discharge</em>' attribute.
	 * @see #setPctEffDischarge(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctEffDischarge()
	 * @model
	 * @generated
	 */
	double getPctEffDischarge();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctEffDischarge <em>Pct Eff Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Eff Discharge</em>' attribute.
	 * @see #getPctEffDischarge()
	 * @generated
	 */
	void setPctEffDischarge(double value);

	/**
	 * Returns the value of the '<em><b>Pct Idling KW</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent of rated kW consumed while idling. Default = 1.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Idling KW</em>' attribute.
	 * @see #setPctIdlingKW(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctIdlingKW()
	 * @model default="1.0"
	 * @generated
	 */
	double getPctIdlingKW();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctIdlingKW <em>Pct Idling KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Idling KW</em>' attribute.
	 * @see #getPctIdlingKW()
	 * @generated
	 */
	void setPctIdlingKW(double value);

	/**
	 * Returns the value of the '<em><b>Pct Idling KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent of rated kW consumed as reactive power (kvar) while idling. Default = 0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Idling KV Ar</em>' attribute.
	 * @see #setPctIdlingKVAr(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctIdlingKVAr()
	 * @model
	 * @generated
	 */
	double getPctIdlingKVAr();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctIdlingKVAr <em>Pct Idling KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Idling KV Ar</em>' attribute.
	 * @see #getPctIdlingKVAr()
	 * @generated
	 */
	void setPctIdlingKVAr(double value);

	/**
	 * Returns the value of the '<em><b>Pct R</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Equivalent percent internal resistance, ohms. Default is 0. Placed in series
	 * with internal voltage source for harmonics and dynamics modes. Use a
	 * combination of pctIdlekW and pctEffCharge and pctEffDischarge to account
	 * for losses in power flow modes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct R</em>' attribute.
	 * @see #setPctR(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctR()
	 * @model
	 * @generated
	 */
	double getPctR();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctR <em>Pct R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct R</em>' attribute.
	 * @see #getPctR()
	 * @generated
	 */
	void setPctR(double value);

	/**
	 * Returns the value of the '<em><b>Pct X</b></em>' attribute.
	 * The default value is <code>"50.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Equivalent percent internal reactance, ohms. Default is 50%. Placed in
	 * series with internal voltage source for harmonics and dynamics modes.
	 * (Limits fault current to 2 pu.) Use pctIdlekVAr and kvar properties to
	 * account for any reactive power during power flow solutions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct X</em>' attribute.
	 * @see #setPctX(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_PctX()
	 * @model default="50.0"
	 * @generated
	 */
	double getPctX();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getPctX <em>Pct X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct X</em>' attribute.
	 * @see #getPctX()
	 * @generated
	 */
	void setPctX(double value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Integer code for the model to use for powet output variation with voltage.
	 * Valid values are:
	 *   1: Storage element injects a CONSTANT kW at specified power factor.
	 *   2:Storage element is modeled as a CONSTANT ADMITTANCE.
	 *   3:Compute load injection from User-written Model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model</em>' attribute.
	 * @see #setModel(int)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Model()
	 * @model
	 * @generated
	 */
	int getModel();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getModel <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' attribute.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(int value);

	/**
	 * Returns the value of the '<em><b>VMin PU</b></em>' attribute.
	 * The default value is <code>"0.9"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum per unit voltage for which the Model is assumed to apply. Below
	 * this value, the load model reverts to a constant impedance model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMin PU</em>' attribute.
	 * @see #setVMinPU(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_VMinPU()
	 * @model default="0.9"
	 * @generated
	 */
	double getVMinPU();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getVMinPU <em>VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMin PU</em>' attribute.
	 * @see #getVMinPU()
	 * @generated
	 */
	void setVMinPU(double value);

	/**
	 * Returns the value of the '<em><b>VMax PU</b></em>' attribute.
	 * The default value is <code>"1.1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum per unit voltage for which the Model is assumed to apply. Above
	 * this value, the load model reverts to a constant impedance model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VMax PU</em>' attribute.
	 * @see #setVMaxPU(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_VMaxPU()
	 * @model default="1.1"
	 * @generated
	 */
	double getVMaxPU();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getVMaxPU <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VMax PU</em>' attribute.
	 * @see #getVMaxPU()
	 * @generated
	 */
	void setVMaxPU(double value);

	/**
	 * Returns the value of the '<em><b>Yearly</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dispatch shape to use for yearly simulations.  Must be previously defined as
	 * a Loadshape object.  If this is not specified, the Daily dispatch shape, If any,
	 * is repeated during Yearly solution modes. In the default dispatch mode, the
	 * Storage element uses this loadshape to trigger State changes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Yearly</em>' reference.
	 * @see #setYearly(LoadShape)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Yearly()
	 * @model
	 * @generated
	 */
	LoadShape getYearly();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getYearly <em>Yearly</em>}' reference.
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
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dispatch shape to use for daily simulations.  Must be previously defined as
	 * a Loadshape object of 24 hrs, typically.  In the default dispatch mode, the
	 * Storage element uses this loadshape to trigger State changes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Daily</em>' reference.
	 * @see #setDaily(LoadShape)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Daily()
	 * @model
	 * @generated
	 */
	LoadShape getDaily();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getDaily <em>Daily</em>}' reference.
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
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Load shape to use for duty cycle dispatch simulations such as for solar ramp
	 * rate studies. Must be previously defined as a Loadshape object. Typically would
	 * have time intervals of 1-5 seconds. If there are fewer points in the actual shape,
	 * the shape is assumed to repeat.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duty</em>' reference.
	 * @see #setDuty(LoadShape)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Duty()
	 * @model
	 * @generated
	 */
	LoadShape getDuty();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getDuty <em>Duty</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duty</em>' reference.
	 * @see #getDuty()
	 * @generated
	 */
	void setDuty(LoadShape value);

	/**
	 * Returns the value of the '<em><b>Disp Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.conversion.dispatchType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * In DEFAULT mode, Storage element state is triggered by the loadshape curve
	 * corresponding to the solution mode. In EXTERNAL mode, Storage element
	 * state is controlled by an external Storage controller. This mode is automatically
	 * set if this Storage element is included in the element list of a StorageController
	 * element. For the other two dispatch modes, the Storage element state is
	 * controlled by either the global default Loadlevel value or the price level.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Disp Mode</em>' attribute.
	 * @see electrickery.conversion.dispatchType
	 * @see #setDispMode(dispatchType)
	 * @see electrickery.conversion.ConversionPackage#getStorage_DispMode()
	 * @model
	 * @generated
	 */
	dispatchType getDispMode();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getDispMode <em>Disp Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disp Mode</em>' attribute.
	 * @see electrickery.conversion.dispatchType
	 * @see #getDispMode()
	 * @generated
	 */
	void setDispMode(dispatchType value);

	/**
	 * Returns the value of the '<em><b>Discharge Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dispatch trigger value for discharging the storage. If = 0.0 the Storage element
	 * state is changed by the State command or by a StorageController object. If <> 0
	 * the Storage element state is set to DISCHARGING when this trigger level is
	 * EXCEEDED by either the specified Loadshape curve value or the price signal or
	 * global Loadlevel value, depending on dispatch mode.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Discharge Trigger</em>' attribute.
	 * @see #setDischargeTrigger(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_DischargeTrigger()
	 * @model
	 * @generated
	 */
	double getDischargeTrigger();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getDischargeTrigger <em>Discharge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discharge Trigger</em>' attribute.
	 * @see #getDischargeTrigger()
	 * @generated
	 */
	void setDischargeTrigger(double value);

	/**
	 * Returns the value of the '<em><b>Charge Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dispatch trigger value for charging the storage. If = 0.0 the Storage element
	 * state is changed by the State command or StorageController object.  If <> 0
	 * the Storage element state is set to CHARGING when this trigger level is GREATER
	 * than either the specified Loadshape curve value or the price signal or global
	 * Loadlevel value, depending on dispatch mode. See State property.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Charge Trigger</em>' attribute.
	 * @see #setChargeTrigger(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_ChargeTrigger()
	 * @model
	 * @generated
	 */
	double getChargeTrigger();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getChargeTrigger <em>Charge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Charge Trigger</em>' attribute.
	 * @see #getChargeTrigger()
	 * @generated
	 */
	void setChargeTrigger(double value);

	/**
	 * Returns the value of the '<em><b>Time Charge Trig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time of day in fractional hours (0230 = 2.5) at which storage element will automatically
	 * go into charge state. Default is 2.0.  Enter a negative time value to disable this feature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time Charge Trig</em>' attribute.
	 * @see #setTimeChargeTrig(double)
	 * @see electrickery.conversion.ConversionPackage#getStorage_TimeChargeTrig()
	 * @model
	 * @generated
	 */
	double getTimeChargeTrig();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getTimeChargeTrig <em>Time Charge Trig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Charge Trig</em>' attribute.
	 * @see #getTimeChargeTrig()
	 * @generated
	 */
	void setTimeChargeTrig(double value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An arbitrary integer number representing the class of Storage element so that Storage
	 * values may be segregated by class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(int)
	 * @see electrickery.conversion.ConversionPackage#getStorage_Class()
	 * @model
	 * @generated
	 */
	int getClass_();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(int value);

	/**
	 * Returns the value of the '<em><b>User Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of DLL containing user-written model, which computes the terminal
	 * currents for Dynamics studies, overriding the default model.  Set to "none"
	 * to negate previous setting.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User Model</em>' attribute.
	 * @see #setUserModel(String)
	 * @see electrickery.conversion.ConversionPackage#getStorage_UserModel()
	 * @model
	 * @generated
	 */
	String getUserModel();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getUserModel <em>User Model</em>}' attribute.
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
	 * String (in quotes or parentheses) that gets passed to user-written model
	 * for defining the data required for that model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User Data</em>' attribute.
	 * @see #setUserData(String)
	 * @see electrickery.conversion.ConversionPackage#getStorage_UserData()
	 * @model
	 * @generated
	 */
	String getUserData();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#getUserData <em>User Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Data</em>' attribute.
	 * @see #getUserData()
	 * @generated
	 */
	void setUserData(String value);

	/**
	 * Returns the value of the '<em><b>Debug Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Turn this on to capture the progress of the Storage model for each iteration.  Creates
	 * a separate file for each Storage element named "STORAGE_name.CSV".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Debug Trace</em>' attribute.
	 * @see #setDebugTrace(boolean)
	 * @see electrickery.conversion.ConversionPackage#getStorage_DebugTrace()
	 * @model
	 * @generated
	 */
	boolean isDebugTrace();

	/**
	 * Sets the value of the '{@link electrickery.conversion.Storage#isDebugTrace <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug Trace</em>' attribute.
	 * @see #isDebugTrace()
	 * @generated
	 */
	void setDebugTrace(boolean value);

} // Storage
