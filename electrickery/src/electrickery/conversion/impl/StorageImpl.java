/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion.impl;

import electrickery.common.Bus;
import electrickery.common.connectionType;

import electrickery.conversion.ConversionPackage;
import electrickery.conversion.Storage;
import electrickery.conversion.dispatchType;
import electrickery.conversion.storageState;

import electrickery.general.LoadShape;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Storage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getBus1 <em>Bus1</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKV <em>KV</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKW <em>KW</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPF <em>PF</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getConn <em>Conn</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKVAR <em>KVAR</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKVA <em>KVA</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKWRated <em>KW Rated</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKWhRated <em>KWh Rated</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getKWhStored <em>KWh Stored</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctStored <em>Pct Stored</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctReserve <em>Pct Reserve</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getState <em>State</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctDischarge <em>Pct Discharge</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctCharge <em>Pct Charge</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctEffCharge <em>Pct Eff Charge</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctEffDischarge <em>Pct Eff Discharge</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctIdlingKW <em>Pct Idling KW</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctIdlingKVAr <em>Pct Idling KV Ar</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctR <em>Pct R</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getPctX <em>Pct X</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getModel <em>Model</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getVMinPU <em>VMin PU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getVMaxPU <em>VMax PU</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getDispMode <em>Disp Mode</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getDischargeTrigger <em>Discharge Trigger</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getChargeTrigger <em>Charge Trigger</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getTimeChargeTrig <em>Time Charge Trig</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getUserModel <em>User Model</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#getUserData <em>User Data</em>}</li>
 *   <li>{@link electrickery.conversion.impl.StorageImpl#isDebugTrace <em>Debug Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StorageImpl extends PowerConversionElementImpl implements Storage {
	/**
	 * The cached value of the '{@link #getBus1() <em>Bus1</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBus1()
	 * @generated
	 * @ordered
	 */
	protected Bus bus1;

	/**
	 * The default value of the '{@link #getKV() <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKV()
	 * @generated
	 * @ordered
	 */
	protected static final double KV_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKV() <em>KV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKV()
	 * @generated
	 * @ordered
	 */
	protected double kV = KV_EDEFAULT;

	/**
	 * The default value of the '{@link #getKW() <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKW()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKW() <em>KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKW()
	 * @generated
	 * @ordered
	 */
	protected double kW = KW_EDEFAULT;

	/**
	 * The default value of the '{@link #getPF() <em>PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPF()
	 * @generated
	 * @ordered
	 */
	protected static final double PF_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getPF() <em>PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPF()
	 * @generated
	 * @ordered
	 */
	protected double pF = PF_EDEFAULT;

	/**
	 * The default value of the '{@link #getConn() <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConn()
	 * @generated
	 * @ordered
	 */
	protected static final connectionType CONN_EDEFAULT = connectionType.WYE;

	/**
	 * The cached value of the '{@link #getConn() <em>Conn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConn()
	 * @generated
	 * @ordered
	 */
	protected connectionType conn = CONN_EDEFAULT;

	/**
	 * The default value of the '{@link #getKVAR() <em>KVAR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVAR()
	 * @generated
	 * @ordered
	 */
	protected static final double KVAR_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVAR() <em>KVAR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVAR()
	 * @generated
	 * @ordered
	 */
	protected double kVAR = KVAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
	protected static final double KVA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVA() <em>KVA</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVA()
	 * @generated
	 * @ordered
	 */
	protected double kVA = KVA_EDEFAULT;

	/**
	 * The default value of the '{@link #getKWRated() <em>KW Rated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWRated()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_RATED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWRated() <em>KW Rated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWRated()
	 * @generated
	 * @ordered
	 */
	protected double kWRated = KW_RATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getKWhRated() <em>KWh Rated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhRated()
	 * @generated
	 * @ordered
	 */
	protected static final double KWH_RATED_EDEFAULT = 50.0;

	/**
	 * The cached value of the '{@link #getKWhRated() <em>KWh Rated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhRated()
	 * @generated
	 * @ordered
	 */
	protected double kWhRated = KWH_RATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getKWhStored() <em>KWh Stored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhStored()
	 * @generated
	 * @ordered
	 */
	protected static final double KWH_STORED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWhStored() <em>KWh Stored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhStored()
	 * @generated
	 * @ordered
	 */
	protected double kWhStored = KWH_STORED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctStored() <em>Pct Stored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctStored()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_STORED_EDEFAULT = 100.0;

	/**
	 * The cached value of the '{@link #getPctStored() <em>Pct Stored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctStored()
	 * @generated
	 * @ordered
	 */
	protected double pctStored = PCT_STORED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctReserve() <em>Pct Reserve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctReserve()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_RESERVE_EDEFAULT = 20.0;

	/**
	 * The cached value of the '{@link #getPctReserve() <em>Pct Reserve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctReserve()
	 * @generated
	 * @ordered
	 */
	protected double pctReserve = PCT_RESERVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final storageState STATE_EDEFAULT = storageState.IDLING;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected storageState state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctDischarge() <em>Pct Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctDischarge()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_DISCHARGE_EDEFAULT = 100.0;

	/**
	 * The cached value of the '{@link #getPctDischarge() <em>Pct Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctDischarge()
	 * @generated
	 * @ordered
	 */
	protected double pctDischarge = PCT_DISCHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctCharge() <em>Pct Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctCharge()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_CHARGE_EDEFAULT = 100.0;

	/**
	 * The cached value of the '{@link #getPctCharge() <em>Pct Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctCharge()
	 * @generated
	 * @ordered
	 */
	protected double pctCharge = PCT_CHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctEffCharge() <em>Pct Eff Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctEffCharge()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_EFF_CHARGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctEffCharge() <em>Pct Eff Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctEffCharge()
	 * @generated
	 * @ordered
	 */
	protected double pctEffCharge = PCT_EFF_CHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctEffDischarge() <em>Pct Eff Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctEffDischarge()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_EFF_DISCHARGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctEffDischarge() <em>Pct Eff Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctEffDischarge()
	 * @generated
	 * @ordered
	 */
	protected double pctEffDischarge = PCT_EFF_DISCHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctIdlingKW() <em>Pct Idling KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctIdlingKW()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_IDLING_KW_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getPctIdlingKW() <em>Pct Idling KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctIdlingKW()
	 * @generated
	 * @ordered
	 */
	protected double pctIdlingKW = PCT_IDLING_KW_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctIdlingKVAr() <em>Pct Idling KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctIdlingKVAr()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_IDLING_KV_AR_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctIdlingKVAr() <em>Pct Idling KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctIdlingKVAr()
	 * @generated
	 * @ordered
	 */
	protected double pctIdlingKVAr = PCT_IDLING_KV_AR_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctR() <em>Pct R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctR()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_R_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctR() <em>Pct R</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctR()
	 * @generated
	 * @ordered
	 */
	protected double pctR = PCT_R_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctX() <em>Pct X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctX()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_X_EDEFAULT = 50.0;

	/**
	 * The cached value of the '{@link #getPctX() <em>Pct X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctX()
	 * @generated
	 * @ordered
	 */
	protected double pctX = PCT_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected static final int MODEL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected int model = MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getVMinPU() <em>VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMinPU()
	 * @generated
	 * @ordered
	 */
	protected static final double VMIN_PU_EDEFAULT = 0.9;

	/**
	 * The cached value of the '{@link #getVMinPU() <em>VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMinPU()
	 * @generated
	 * @ordered
	 */
	protected double vMinPU = VMIN_PU_EDEFAULT;

	/**
	 * The default value of the '{@link #getVMaxPU() <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMaxPU()
	 * @generated
	 * @ordered
	 */
	protected static final double VMAX_PU_EDEFAULT = 1.1;

	/**
	 * The cached value of the '{@link #getVMaxPU() <em>VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVMaxPU()
	 * @generated
	 * @ordered
	 */
	protected double vMaxPU = VMAX_PU_EDEFAULT;

	/**
	 * The cached value of the '{@link #getYearly() <em>Yearly</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYearly()
	 * @generated
	 * @ordered
	 */
	protected LoadShape yearly;

	/**
	 * The cached value of the '{@link #getDaily() <em>Daily</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaily()
	 * @generated
	 * @ordered
	 */
	protected LoadShape daily;

	/**
	 * The cached value of the '{@link #getDuty() <em>Duty</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuty()
	 * @generated
	 * @ordered
	 */
	protected LoadShape duty;

	/**
	 * The default value of the '{@link #getDispMode() <em>Disp Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDispMode()
	 * @generated
	 * @ordered
	 */
	protected static final dispatchType DISP_MODE_EDEFAULT = dispatchType.LOAD_MODE;

	/**
	 * The cached value of the '{@link #getDispMode() <em>Disp Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDispMode()
	 * @generated
	 * @ordered
	 */
	protected dispatchType dispMode = DISP_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDischargeTrigger() <em>Discharge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDischargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final double DISCHARGE_TRIGGER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDischargeTrigger() <em>Discharge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDischargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected double dischargeTrigger = DISCHARGE_TRIGGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getChargeTrigger() <em>Charge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final double CHARGE_TRIGGER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getChargeTrigger() <em>Charge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected double chargeTrigger = CHARGE_TRIGGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeChargeTrig() <em>Time Charge Trig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeChargeTrig()
	 * @generated
	 * @ordered
	 */
	protected static final double TIME_CHARGE_TRIG_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTimeChargeTrig() <em>Time Charge Trig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeChargeTrig()
	 * @generated
	 * @ordered
	 */
	protected double timeChargeTrig = TIME_CHARGE_TRIG_EDEFAULT;

	/**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final int CLASS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected int class_ = CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserModel() <em>User Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserModel()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserModel() <em>User Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserModel()
	 * @generated
	 * @ordered
	 */
	protected String userModel = USER_MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserData() <em>User Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserData()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserData() <em>User Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserData()
	 * @generated
	 * @ordered
	 */
	protected String userData = USER_DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #isDebugTrace() <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDebugTrace()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEBUG_TRACE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDebugTrace() <em>Debug Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDebugTrace()
	 * @generated
	 * @ordered
	 */
	protected boolean debugTrace = DEBUG_TRACE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StorageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConversionPackage.Literals.STORAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bus getBus1() {
		if (bus1 != null && bus1.eIsProxy()) {
			InternalEObject oldBus1 = (InternalEObject)bus1;
			bus1 = (Bus)eResolveProxy(oldBus1);
			if (bus1 != oldBus1) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.STORAGE__BUS1, oldBus1, bus1));
			}
		}
		return bus1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bus basicGetBus1() {
		return bus1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBus1(Bus newBus1) {
		Bus oldBus1 = bus1;
		bus1 = newBus1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__BUS1, oldBus1, bus1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKV() {
		return kV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKV(double newKV) {
		double oldKV = kV;
		kV = newKV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KV, oldKV, kV));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKW() {
		return kW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKW(double newKW) {
		double oldKW = kW;
		kW = newKW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KW, oldKW, kW));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPF() {
		return pF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPF(double newPF) {
		double oldPF = pF;
		pF = newPF;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PF, oldPF, pF));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public connectionType getConn() {
		return conn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConn(connectionType newConn) {
		connectionType oldConn = conn;
		conn = newConn == null ? CONN_EDEFAULT : newConn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__CONN, oldConn, conn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVAR() {
		return kVAR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVAR(double newKVAR) {
		double oldKVAR = kVAR;
		kVAR = newKVAR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KVAR, oldKVAR, kVAR));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVA() {
		return kVA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVA(double newKVA) {
		double oldKVA = kVA;
		kVA = newKVA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KVA, oldKVA, kVA));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWRated() {
		return kWRated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWRated(double newKWRated) {
		double oldKWRated = kWRated;
		kWRated = newKWRated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KW_RATED, oldKWRated, kWRated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWhRated() {
		return kWhRated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWhRated(double newKWhRated) {
		double oldKWhRated = kWhRated;
		kWhRated = newKWhRated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KWH_RATED, oldKWhRated, kWhRated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWhStored() {
		return kWhStored;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWhStored(double newKWhStored) {
		double oldKWhStored = kWhStored;
		kWhStored = newKWhStored;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__KWH_STORED, oldKWhStored, kWhStored));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctStored() {
		return pctStored;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctStored(double newPctStored) {
		double oldPctStored = pctStored;
		pctStored = newPctStored;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_STORED, oldPctStored, pctStored));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctReserve() {
		return pctReserve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctReserve(double newPctReserve) {
		double oldPctReserve = pctReserve;
		pctReserve = newPctReserve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_RESERVE, oldPctReserve, pctReserve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public storageState getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(storageState newState) {
		storageState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctDischarge() {
		return pctDischarge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctDischarge(double newPctDischarge) {
		double oldPctDischarge = pctDischarge;
		pctDischarge = newPctDischarge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_DISCHARGE, oldPctDischarge, pctDischarge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctCharge() {
		return pctCharge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctCharge(double newPctCharge) {
		double oldPctCharge = pctCharge;
		pctCharge = newPctCharge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_CHARGE, oldPctCharge, pctCharge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctEffCharge() {
		return pctEffCharge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctEffCharge(double newPctEffCharge) {
		double oldPctEffCharge = pctEffCharge;
		pctEffCharge = newPctEffCharge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_EFF_CHARGE, oldPctEffCharge, pctEffCharge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctEffDischarge() {
		return pctEffDischarge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctEffDischarge(double newPctEffDischarge) {
		double oldPctEffDischarge = pctEffDischarge;
		pctEffDischarge = newPctEffDischarge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_EFF_DISCHARGE, oldPctEffDischarge, pctEffDischarge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctIdlingKW() {
		return pctIdlingKW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctIdlingKW(double newPctIdlingKW) {
		double oldPctIdlingKW = pctIdlingKW;
		pctIdlingKW = newPctIdlingKW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_IDLING_KW, oldPctIdlingKW, pctIdlingKW));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctIdlingKVAr() {
		return pctIdlingKVAr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctIdlingKVAr(double newPctIdlingKVAr) {
		double oldPctIdlingKVAr = pctIdlingKVAr;
		pctIdlingKVAr = newPctIdlingKVAr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_IDLING_KV_AR, oldPctIdlingKVAr, pctIdlingKVAr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctR() {
		return pctR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctR(double newPctR) {
		double oldPctR = pctR;
		pctR = newPctR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_R, oldPctR, pctR));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctX() {
		return pctX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctX(double newPctX) {
		double oldPctX = pctX;
		pctX = newPctX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__PCT_X, oldPctX, pctX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getModel() {
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(int newModel) {
		int oldModel = model;
		model = newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__MODEL, oldModel, model));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVMinPU() {
		return vMinPU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVMinPU(double newVMinPU) {
		double oldVMinPU = vMinPU;
		vMinPU = newVMinPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__VMIN_PU, oldVMinPU, vMinPU));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVMaxPU() {
		return vMaxPU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVMaxPU(double newVMaxPU) {
		double oldVMaxPU = vMaxPU;
		vMaxPU = newVMaxPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__VMAX_PU, oldVMaxPU, vMaxPU));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape getYearly() {
		if (yearly != null && yearly.eIsProxy()) {
			InternalEObject oldYearly = (InternalEObject)yearly;
			yearly = (LoadShape)eResolveProxy(oldYearly);
			if (yearly != oldYearly) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.STORAGE__YEARLY, oldYearly, yearly));
			}
		}
		return yearly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape basicGetYearly() {
		return yearly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYearly(LoadShape newYearly) {
		LoadShape oldYearly = yearly;
		yearly = newYearly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__YEARLY, oldYearly, yearly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape getDaily() {
		if (daily != null && daily.eIsProxy()) {
			InternalEObject oldDaily = (InternalEObject)daily;
			daily = (LoadShape)eResolveProxy(oldDaily);
			if (daily != oldDaily) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.STORAGE__DAILY, oldDaily, daily));
			}
		}
		return daily;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape basicGetDaily() {
		return daily;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDaily(LoadShape newDaily) {
		LoadShape oldDaily = daily;
		daily = newDaily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__DAILY, oldDaily, daily));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape getDuty() {
		if (duty != null && duty.eIsProxy()) {
			InternalEObject oldDuty = (InternalEObject)duty;
			duty = (LoadShape)eResolveProxy(oldDuty);
			if (duty != oldDuty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConversionPackage.STORAGE__DUTY, oldDuty, duty));
			}
		}
		return duty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadShape basicGetDuty() {
		return duty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuty(LoadShape newDuty) {
		LoadShape oldDuty = duty;
		duty = newDuty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__DUTY, oldDuty, duty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public dispatchType getDispMode() {
		return dispMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDispMode(dispatchType newDispMode) {
		dispatchType oldDispMode = dispMode;
		dispMode = newDispMode == null ? DISP_MODE_EDEFAULT : newDispMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__DISP_MODE, oldDispMode, dispMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDischargeTrigger() {
		return dischargeTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDischargeTrigger(double newDischargeTrigger) {
		double oldDischargeTrigger = dischargeTrigger;
		dischargeTrigger = newDischargeTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__DISCHARGE_TRIGGER, oldDischargeTrigger, dischargeTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getChargeTrigger() {
		return chargeTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChargeTrigger(double newChargeTrigger) {
		double oldChargeTrigger = chargeTrigger;
		chargeTrigger = newChargeTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__CHARGE_TRIGGER, oldChargeTrigger, chargeTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTimeChargeTrig() {
		return timeChargeTrig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeChargeTrig(double newTimeChargeTrig) {
		double oldTimeChargeTrig = timeChargeTrig;
		timeChargeTrig = newTimeChargeTrig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__TIME_CHARGE_TRIG, oldTimeChargeTrig, timeChargeTrig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass(int newClass) {
		int oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__CLASS, oldClass, class_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserModel() {
		return userModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserModel(String newUserModel) {
		String oldUserModel = userModel;
		userModel = newUserModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__USER_MODEL, oldUserModel, userModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserData() {
		return userData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserData(String newUserData) {
		String oldUserData = userData;
		userData = newUserData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__USER_DATA, oldUserData, userData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDebugTrace() {
		return debugTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDebugTrace(boolean newDebugTrace) {
		boolean oldDebugTrace = debugTrace;
		debugTrace = newDebugTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConversionPackage.STORAGE__DEBUG_TRACE, oldDebugTrace, debugTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConversionPackage.STORAGE__BUS1:
				if (resolve) return getBus1();
				return basicGetBus1();
			case ConversionPackage.STORAGE__KV:
				return getKV();
			case ConversionPackage.STORAGE__KW:
				return getKW();
			case ConversionPackage.STORAGE__PF:
				return getPF();
			case ConversionPackage.STORAGE__CONN:
				return getConn();
			case ConversionPackage.STORAGE__KVAR:
				return getKVAR();
			case ConversionPackage.STORAGE__KVA:
				return getKVA();
			case ConversionPackage.STORAGE__KW_RATED:
				return getKWRated();
			case ConversionPackage.STORAGE__KWH_RATED:
				return getKWhRated();
			case ConversionPackage.STORAGE__KWH_STORED:
				return getKWhStored();
			case ConversionPackage.STORAGE__PCT_STORED:
				return getPctStored();
			case ConversionPackage.STORAGE__PCT_RESERVE:
				return getPctReserve();
			case ConversionPackage.STORAGE__STATE:
				return getState();
			case ConversionPackage.STORAGE__PCT_DISCHARGE:
				return getPctDischarge();
			case ConversionPackage.STORAGE__PCT_CHARGE:
				return getPctCharge();
			case ConversionPackage.STORAGE__PCT_EFF_CHARGE:
				return getPctEffCharge();
			case ConversionPackage.STORAGE__PCT_EFF_DISCHARGE:
				return getPctEffDischarge();
			case ConversionPackage.STORAGE__PCT_IDLING_KW:
				return getPctIdlingKW();
			case ConversionPackage.STORAGE__PCT_IDLING_KV_AR:
				return getPctIdlingKVAr();
			case ConversionPackage.STORAGE__PCT_R:
				return getPctR();
			case ConversionPackage.STORAGE__PCT_X:
				return getPctX();
			case ConversionPackage.STORAGE__MODEL:
				return getModel();
			case ConversionPackage.STORAGE__VMIN_PU:
				return getVMinPU();
			case ConversionPackage.STORAGE__VMAX_PU:
				return getVMaxPU();
			case ConversionPackage.STORAGE__YEARLY:
				if (resolve) return getYearly();
				return basicGetYearly();
			case ConversionPackage.STORAGE__DAILY:
				if (resolve) return getDaily();
				return basicGetDaily();
			case ConversionPackage.STORAGE__DUTY:
				if (resolve) return getDuty();
				return basicGetDuty();
			case ConversionPackage.STORAGE__DISP_MODE:
				return getDispMode();
			case ConversionPackage.STORAGE__DISCHARGE_TRIGGER:
				return getDischargeTrigger();
			case ConversionPackage.STORAGE__CHARGE_TRIGGER:
				return getChargeTrigger();
			case ConversionPackage.STORAGE__TIME_CHARGE_TRIG:
				return getTimeChargeTrig();
			case ConversionPackage.STORAGE__CLASS:
				return getClass_();
			case ConversionPackage.STORAGE__USER_MODEL:
				return getUserModel();
			case ConversionPackage.STORAGE__USER_DATA:
				return getUserData();
			case ConversionPackage.STORAGE__DEBUG_TRACE:
				return isDebugTrace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConversionPackage.STORAGE__BUS1:
				setBus1((Bus)newValue);
				return;
			case ConversionPackage.STORAGE__KV:
				setKV((Double)newValue);
				return;
			case ConversionPackage.STORAGE__KW:
				setKW((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PF:
				setPF((Double)newValue);
				return;
			case ConversionPackage.STORAGE__CONN:
				setConn((connectionType)newValue);
				return;
			case ConversionPackage.STORAGE__KVAR:
				setKVAR((Double)newValue);
				return;
			case ConversionPackage.STORAGE__KVA:
				setKVA((Double)newValue);
				return;
			case ConversionPackage.STORAGE__KW_RATED:
				setKWRated((Double)newValue);
				return;
			case ConversionPackage.STORAGE__KWH_RATED:
				setKWhRated((Double)newValue);
				return;
			case ConversionPackage.STORAGE__KWH_STORED:
				setKWhStored((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_STORED:
				setPctStored((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_RESERVE:
				setPctReserve((Double)newValue);
				return;
			case ConversionPackage.STORAGE__STATE:
				setState((storageState)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_DISCHARGE:
				setPctDischarge((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_CHARGE:
				setPctCharge((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_EFF_CHARGE:
				setPctEffCharge((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_EFF_DISCHARGE:
				setPctEffDischarge((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_IDLING_KW:
				setPctIdlingKW((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_IDLING_KV_AR:
				setPctIdlingKVAr((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_R:
				setPctR((Double)newValue);
				return;
			case ConversionPackage.STORAGE__PCT_X:
				setPctX((Double)newValue);
				return;
			case ConversionPackage.STORAGE__MODEL:
				setModel((Integer)newValue);
				return;
			case ConversionPackage.STORAGE__VMIN_PU:
				setVMinPU((Double)newValue);
				return;
			case ConversionPackage.STORAGE__VMAX_PU:
				setVMaxPU((Double)newValue);
				return;
			case ConversionPackage.STORAGE__YEARLY:
				setYearly((LoadShape)newValue);
				return;
			case ConversionPackage.STORAGE__DAILY:
				setDaily((LoadShape)newValue);
				return;
			case ConversionPackage.STORAGE__DUTY:
				setDuty((LoadShape)newValue);
				return;
			case ConversionPackage.STORAGE__DISP_MODE:
				setDispMode((dispatchType)newValue);
				return;
			case ConversionPackage.STORAGE__DISCHARGE_TRIGGER:
				setDischargeTrigger((Double)newValue);
				return;
			case ConversionPackage.STORAGE__CHARGE_TRIGGER:
				setChargeTrigger((Double)newValue);
				return;
			case ConversionPackage.STORAGE__TIME_CHARGE_TRIG:
				setTimeChargeTrig((Double)newValue);
				return;
			case ConversionPackage.STORAGE__CLASS:
				setClass((Integer)newValue);
				return;
			case ConversionPackage.STORAGE__USER_MODEL:
				setUserModel((String)newValue);
				return;
			case ConversionPackage.STORAGE__USER_DATA:
				setUserData((String)newValue);
				return;
			case ConversionPackage.STORAGE__DEBUG_TRACE:
				setDebugTrace((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConversionPackage.STORAGE__BUS1:
				setBus1((Bus)null);
				return;
			case ConversionPackage.STORAGE__KV:
				setKV(KV_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__KW:
				setKW(KW_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PF:
				setPF(PF_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__CONN:
				setConn(CONN_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__KVAR:
				setKVAR(KVAR_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__KVA:
				setKVA(KVA_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__KW_RATED:
				setKWRated(KW_RATED_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__KWH_RATED:
				setKWhRated(KWH_RATED_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__KWH_STORED:
				setKWhStored(KWH_STORED_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_STORED:
				setPctStored(PCT_STORED_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_RESERVE:
				setPctReserve(PCT_RESERVE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__STATE:
				setState(STATE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_DISCHARGE:
				setPctDischarge(PCT_DISCHARGE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_CHARGE:
				setPctCharge(PCT_CHARGE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_EFF_CHARGE:
				setPctEffCharge(PCT_EFF_CHARGE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_EFF_DISCHARGE:
				setPctEffDischarge(PCT_EFF_DISCHARGE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_IDLING_KW:
				setPctIdlingKW(PCT_IDLING_KW_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_IDLING_KV_AR:
				setPctIdlingKVAr(PCT_IDLING_KV_AR_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_R:
				setPctR(PCT_R_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__PCT_X:
				setPctX(PCT_X_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__MODEL:
				setModel(MODEL_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__VMIN_PU:
				setVMinPU(VMIN_PU_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__VMAX_PU:
				setVMaxPU(VMAX_PU_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__YEARLY:
				setYearly((LoadShape)null);
				return;
			case ConversionPackage.STORAGE__DAILY:
				setDaily((LoadShape)null);
				return;
			case ConversionPackage.STORAGE__DUTY:
				setDuty((LoadShape)null);
				return;
			case ConversionPackage.STORAGE__DISP_MODE:
				setDispMode(DISP_MODE_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__DISCHARGE_TRIGGER:
				setDischargeTrigger(DISCHARGE_TRIGGER_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__CHARGE_TRIGGER:
				setChargeTrigger(CHARGE_TRIGGER_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__TIME_CHARGE_TRIG:
				setTimeChargeTrig(TIME_CHARGE_TRIG_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__USER_MODEL:
				setUserModel(USER_MODEL_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__USER_DATA:
				setUserData(USER_DATA_EDEFAULT);
				return;
			case ConversionPackage.STORAGE__DEBUG_TRACE:
				setDebugTrace(DEBUG_TRACE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConversionPackage.STORAGE__BUS1:
				return bus1 != null;
			case ConversionPackage.STORAGE__KV:
				return kV != KV_EDEFAULT;
			case ConversionPackage.STORAGE__KW:
				return kW != KW_EDEFAULT;
			case ConversionPackage.STORAGE__PF:
				return pF != PF_EDEFAULT;
			case ConversionPackage.STORAGE__CONN:
				return conn != CONN_EDEFAULT;
			case ConversionPackage.STORAGE__KVAR:
				return kVAR != KVAR_EDEFAULT;
			case ConversionPackage.STORAGE__KVA:
				return kVA != KVA_EDEFAULT;
			case ConversionPackage.STORAGE__KW_RATED:
				return kWRated != KW_RATED_EDEFAULT;
			case ConversionPackage.STORAGE__KWH_RATED:
				return kWhRated != KWH_RATED_EDEFAULT;
			case ConversionPackage.STORAGE__KWH_STORED:
				return kWhStored != KWH_STORED_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_STORED:
				return pctStored != PCT_STORED_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_RESERVE:
				return pctReserve != PCT_RESERVE_EDEFAULT;
			case ConversionPackage.STORAGE__STATE:
				return state != STATE_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_DISCHARGE:
				return pctDischarge != PCT_DISCHARGE_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_CHARGE:
				return pctCharge != PCT_CHARGE_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_EFF_CHARGE:
				return pctEffCharge != PCT_EFF_CHARGE_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_EFF_DISCHARGE:
				return pctEffDischarge != PCT_EFF_DISCHARGE_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_IDLING_KW:
				return pctIdlingKW != PCT_IDLING_KW_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_IDLING_KV_AR:
				return pctIdlingKVAr != PCT_IDLING_KV_AR_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_R:
				return pctR != PCT_R_EDEFAULT;
			case ConversionPackage.STORAGE__PCT_X:
				return pctX != PCT_X_EDEFAULT;
			case ConversionPackage.STORAGE__MODEL:
				return model != MODEL_EDEFAULT;
			case ConversionPackage.STORAGE__VMIN_PU:
				return vMinPU != VMIN_PU_EDEFAULT;
			case ConversionPackage.STORAGE__VMAX_PU:
				return vMaxPU != VMAX_PU_EDEFAULT;
			case ConversionPackage.STORAGE__YEARLY:
				return yearly != null;
			case ConversionPackage.STORAGE__DAILY:
				return daily != null;
			case ConversionPackage.STORAGE__DUTY:
				return duty != null;
			case ConversionPackage.STORAGE__DISP_MODE:
				return dispMode != DISP_MODE_EDEFAULT;
			case ConversionPackage.STORAGE__DISCHARGE_TRIGGER:
				return dischargeTrigger != DISCHARGE_TRIGGER_EDEFAULT;
			case ConversionPackage.STORAGE__CHARGE_TRIGGER:
				return chargeTrigger != CHARGE_TRIGGER_EDEFAULT;
			case ConversionPackage.STORAGE__TIME_CHARGE_TRIG:
				return timeChargeTrig != TIME_CHARGE_TRIG_EDEFAULT;
			case ConversionPackage.STORAGE__CLASS:
				return class_ != CLASS_EDEFAULT;
			case ConversionPackage.STORAGE__USER_MODEL:
				return USER_MODEL_EDEFAULT == null ? userModel != null : !USER_MODEL_EDEFAULT.equals(userModel);
			case ConversionPackage.STORAGE__USER_DATA:
				return USER_DATA_EDEFAULT == null ? userData != null : !USER_DATA_EDEFAULT.equals(userData);
			case ConversionPackage.STORAGE__DEBUG_TRACE:
				return debugTrace != DEBUG_TRACE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (kV: ");
		result.append(kV);
		result.append(", kW: ");
		result.append(kW);
		result.append(", pF: ");
		result.append(pF);
		result.append(", conn: ");
		result.append(conn);
		result.append(", kVAR: ");
		result.append(kVAR);
		result.append(", kVA: ");
		result.append(kVA);
		result.append(", kWRated: ");
		result.append(kWRated);
		result.append(", kWhRated: ");
		result.append(kWhRated);
		result.append(", kWhStored: ");
		result.append(kWhStored);
		result.append(", pctStored: ");
		result.append(pctStored);
		result.append(", pctReserve: ");
		result.append(pctReserve);
		result.append(", state: ");
		result.append(state);
		result.append(", pctDischarge: ");
		result.append(pctDischarge);
		result.append(", pctCharge: ");
		result.append(pctCharge);
		result.append(", pctEffCharge: ");
		result.append(pctEffCharge);
		result.append(", pctEffDischarge: ");
		result.append(pctEffDischarge);
		result.append(", pctIdlingKW: ");
		result.append(pctIdlingKW);
		result.append(", pctIdlingKVAr: ");
		result.append(pctIdlingKVAr);
		result.append(", pctR: ");
		result.append(pctR);
		result.append(", pctX: ");
		result.append(pctX);
		result.append(", model: ");
		result.append(model);
		result.append(", vMinPU: ");
		result.append(vMinPU);
		result.append(", vMaxPU: ");
		result.append(vMaxPU);
		result.append(", dispMode: ");
		result.append(dispMode);
		result.append(", dischargeTrigger: ");
		result.append(dischargeTrigger);
		result.append(", chargeTrigger: ");
		result.append(chargeTrigger);
		result.append(", timeChargeTrig: ");
		result.append(timeChargeTrig);
		result.append(", class: ");
		result.append(class_);
		result.append(", userModel: ");
		result.append(userModel);
		result.append(", userData: ");
		result.append(userData);
		result.append(", debugTrace: ");
		result.append(debugTrace);
		result.append(')');
		return result.toString();
	}

} //StorageImpl
