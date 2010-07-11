/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.impl;

import electrickery.control.ControlPackage;
import electrickery.control.StorageController;
import electrickery.control.chargeMode;
import electrickery.control.dischargeMode;

import electrickery.conversion.Storage;

import electrickery.general.LoadShape;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Storage Controller</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getKWTarget <em>KW Target</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPctKWBand <em>Pct KW Band</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPFTarget <em>PF Target</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPFBand <em>PF Band</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getWeights <em>Weights</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getModeDischarge <em>Mode Discharge</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getModeCharge <em>Mode Charge</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getTimeDischargeTrigger <em>Time Discharge Trigger</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getTimeChargeTrigger <em>Time Charge Trigger</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPctRateKW <em>Pct Rate KW</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPctRateKVAr <em>Pct Rate KV Ar</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPctRateCharge <em>Pct Rate Charge</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPctReserve <em>Pct Reserve</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getKWhTotal <em>KWh Total</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getKWTotal <em>KW Total</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getKWhActual <em>KWh Actual</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getKWActual <em>KW Actual</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getKWNeed <em>KW Need</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getPctParticipation <em>Pct Participation</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getYearly <em>Yearly</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getDaily <em>Daily</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getDuty <em>Duty</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#isEventLog <em>Event Log</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#isVarDispatch <em>Var Dispatch</em>}</li>
 *   <li>{@link electrickery.control.impl.StorageControllerImpl#getInhibitTime <em>Inhibit Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StorageControllerImpl extends ControlElementImpl implements StorageController {
	/**
	 * The default value of the '{@link #getKWTarget() <em>KW Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWTarget()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_TARGET_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWTarget() <em>KW Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWTarget()
	 * @generated
	 * @ordered
	 */
	protected double kWTarget = KW_TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctKWBand() <em>Pct KW Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctKWBand()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_KW_BAND_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctKWBand() <em>Pct KW Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctKWBand()
	 * @generated
	 * @ordered
	 */
	protected double pctKWBand = PCT_KW_BAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getPFTarget() <em>PF Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPFTarget()
	 * @generated
	 * @ordered
	 */
	protected static final double PF_TARGET_EDEFAULT = 0.96;

	/**
	 * The cached value of the '{@link #getPFTarget() <em>PF Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPFTarget()
	 * @generated
	 * @ordered
	 */
	protected double pFTarget = PF_TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getPFBand() <em>PF Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPFBand()
	 * @generated
	 * @ordered
	 */
	protected static final double PF_BAND_EDEFAULT = 0.04;

	/**
	 * The cached value of the '{@link #getPFBand() <em>PF Band</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPFBand()
	 * @generated
	 * @ordered
	 */
	protected double pFBand = PF_BAND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Storage> elements;

	/**
	 * The cached value of the '{@link #getWeights() <em>Weights</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeights()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> weights;

	/**
	 * The default value of the '{@link #getModeDischarge() <em>Mode Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeDischarge()
	 * @generated
	 * @ordered
	 */
	protected static final dischargeMode MODE_DISCHARGE_EDEFAULT = dischargeMode.PEAK_SHAVE;

	/**
	 * The cached value of the '{@link #getModeDischarge() <em>Mode Discharge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeDischarge()
	 * @generated
	 * @ordered
	 */
	protected dischargeMode modeDischarge = MODE_DISCHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getModeCharge() <em>Mode Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeCharge()
	 * @generated
	 * @ordered
	 */
	protected static final chargeMode MODE_CHARGE_EDEFAULT = chargeMode.LOAD_SHAPE;

	/**
	 * The cached value of the '{@link #getModeCharge() <em>Mode Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeCharge()
	 * @generated
	 * @ordered
	 */
	protected chargeMode modeCharge = MODE_CHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeDischargeTrigger() <em>Time Discharge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeDischargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final double TIME_DISCHARGE_TRIGGER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTimeDischargeTrigger() <em>Time Discharge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeDischargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected double timeDischargeTrigger = TIME_DISCHARGE_TRIGGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeChargeTrigger() <em>Time Charge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeChargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final double TIME_CHARGE_TRIGGER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTimeChargeTrigger() <em>Time Charge Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeChargeTrigger()
	 * @generated
	 * @ordered
	 */
	protected double timeChargeTrigger = TIME_CHARGE_TRIGGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctRateKW() <em>Pct Rate KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRateKW()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_RATE_KW_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctRateKW() <em>Pct Rate KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRateKW()
	 * @generated
	 * @ordered
	 */
	protected double pctRateKW = PCT_RATE_KW_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctRateKVAr() <em>Pct Rate KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRateKVAr()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_RATE_KV_AR_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctRateKVAr() <em>Pct Rate KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRateKVAr()
	 * @generated
	 * @ordered
	 */
	protected double pctRateKVAr = PCT_RATE_KV_AR_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctRateCharge() <em>Pct Rate Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRateCharge()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_RATE_CHARGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctRateCharge() <em>Pct Rate Charge</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctRateCharge()
	 * @generated
	 * @ordered
	 */
	protected double pctRateCharge = PCT_RATE_CHARGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctReserve() <em>Pct Reserve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctReserve()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_RESERVE_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #getKWhTotal() <em>KWh Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhTotal()
	 * @generated
	 * @ordered
	 */
	protected static final double KWH_TOTAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWhTotal() <em>KWh Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhTotal()
	 * @generated
	 * @ordered
	 */
	protected double kWhTotal = KWH_TOTAL_EDEFAULT;

	/**
	 * This is true if the KWh Total attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean kWhTotalESet;

	/**
	 * The default value of the '{@link #getKWTotal() <em>KW Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWTotal()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_TOTAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWTotal() <em>KW Total</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWTotal()
	 * @generated
	 * @ordered
	 */
	protected double kWTotal = KW_TOTAL_EDEFAULT;

	/**
	 * This is true if the KW Total attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean kWTotalESet;

	/**
	 * The default value of the '{@link #getKWhActual() <em>KWh Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhActual()
	 * @generated
	 * @ordered
	 */
	protected static final double KWH_ACTUAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWhActual() <em>KWh Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWhActual()
	 * @generated
	 * @ordered
	 */
	protected double kWhActual = KWH_ACTUAL_EDEFAULT;

	/**
	 * This is true if the KWh Actual attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean kWhActualESet;

	/**
	 * The default value of the '{@link #getKWActual() <em>KW Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWActual()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_ACTUAL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWActual() <em>KW Actual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWActual()
	 * @generated
	 * @ordered
	 */
	protected double kWActual = KW_ACTUAL_EDEFAULT;

	/**
	 * This is true if the KW Actual attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean kWActualESet;

	/**
	 * The default value of the '{@link #getKWNeed() <em>KW Need</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWNeed()
	 * @generated
	 * @ordered
	 */
	protected static final double KW_NEED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKWNeed() <em>KW Need</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKWNeed()
	 * @generated
	 * @ordered
	 */
	protected double kWNeed = KW_NEED_EDEFAULT;

	/**
	 * This is true if the KW Need attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean kWNeedESet;

	/**
	 * The default value of the '{@link #getPctParticipation() <em>Pct Participation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctParticipation()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_PARTICIPATION_EDEFAULT = 100.0;

	/**
	 * The cached value of the '{@link #getPctParticipation() <em>Pct Participation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctParticipation()
	 * @generated
	 * @ordered
	 */
	protected double pctParticipation = PCT_PARTICIPATION_EDEFAULT;

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
	 * The default value of the '{@link #isEventLog() <em>Event Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEventLog()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EVENT_LOG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEventLog() <em>Event Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEventLog()
	 * @generated
	 * @ordered
	 */
	protected boolean eventLog = EVENT_LOG_EDEFAULT;

	/**
	 * The default value of the '{@link #isVarDispatch() <em>Var Dispatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVarDispatch()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VAR_DISPATCH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVarDispatch() <em>Var Dispatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVarDispatch()
	 * @generated
	 * @ordered
	 */
	protected boolean varDispatch = VAR_DISPATCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getInhibitTime() <em>Inhibit Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInhibitTime()
	 * @generated
	 * @ordered
	 */
	protected static final int INHIBIT_TIME_EDEFAULT = 5;

	/**
	 * The cached value of the '{@link #getInhibitTime() <em>Inhibit Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInhibitTime()
	 * @generated
	 * @ordered
	 */
	protected int inhibitTime = INHIBIT_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StorageControllerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.STORAGE_CONTROLLER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWTarget() {
		return kWTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWTarget(double newKWTarget) {
		double oldKWTarget = kWTarget;
		kWTarget = newKWTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__KW_TARGET, oldKWTarget, kWTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctKWBand() {
		return pctKWBand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctKWBand(double newPctKWBand) {
		double oldPctKWBand = pctKWBand;
		pctKWBand = newPctKWBand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PCT_KW_BAND, oldPctKWBand, pctKWBand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPFTarget() {
		return pFTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPFTarget(double newPFTarget) {
		double oldPFTarget = pFTarget;
		pFTarget = newPFTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PF_TARGET, oldPFTarget, pFTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPFBand() {
		return pFBand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPFBand(double newPFBand) {
		double oldPFBand = pFBand;
		pFBand = newPFBand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PF_BAND, oldPFBand, pFBand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Storage> getElements() {
		if (elements == null) {
			elements = new EObjectResolvingEList<Storage>(Storage.class, this, ControlPackage.STORAGE_CONTROLLER__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getWeights() {
		if (weights == null) {
			weights = new EDataTypeUniqueEList<Double>(Double.class, this, ControlPackage.STORAGE_CONTROLLER__WEIGHTS);
		}
		return weights;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public dischargeMode getModeDischarge() {
		return modeDischarge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModeDischarge(dischargeMode newModeDischarge) {
		dischargeMode oldModeDischarge = modeDischarge;
		modeDischarge = newModeDischarge == null ? MODE_DISCHARGE_EDEFAULT : newModeDischarge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__MODE_DISCHARGE, oldModeDischarge, modeDischarge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public chargeMode getModeCharge() {
		return modeCharge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModeCharge(chargeMode newModeCharge) {
		chargeMode oldModeCharge = modeCharge;
		modeCharge = newModeCharge == null ? MODE_CHARGE_EDEFAULT : newModeCharge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__MODE_CHARGE, oldModeCharge, modeCharge));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTimeDischargeTrigger() {
		return timeDischargeTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeDischargeTrigger(double newTimeDischargeTrigger) {
		double oldTimeDischargeTrigger = timeDischargeTrigger;
		timeDischargeTrigger = newTimeDischargeTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER, oldTimeDischargeTrigger, timeDischargeTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTimeChargeTrigger() {
		return timeChargeTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeChargeTrigger(double newTimeChargeTrigger) {
		double oldTimeChargeTrigger = timeChargeTrigger;
		timeChargeTrigger = newTimeChargeTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER, oldTimeChargeTrigger, timeChargeTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctRateKW() {
		return pctRateKW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctRateKW(double newPctRateKW) {
		double oldPctRateKW = pctRateKW;
		pctRateKW = newPctRateKW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KW, oldPctRateKW, pctRateKW));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctRateKVAr() {
		return pctRateKVAr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctRateKVAr(double newPctRateKVAr) {
		double oldPctRateKVAr = pctRateKVAr;
		pctRateKVAr = newPctRateKVAr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KV_AR, oldPctRateKVAr, pctRateKVAr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctRateCharge() {
		return pctRateCharge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctRateCharge(double newPctRateCharge) {
		double oldPctRateCharge = pctRateCharge;
		pctRateCharge = newPctRateCharge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PCT_RATE_CHARGE, oldPctRateCharge, pctRateCharge));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PCT_RESERVE, oldPctReserve, pctReserve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWhTotal() {
		return kWhTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWhTotal(double newKWhTotal) {
		double oldKWhTotal = kWhTotal;
		kWhTotal = newKWhTotal;
		boolean oldKWhTotalESet = kWhTotalESet;
		kWhTotalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__KWH_TOTAL, oldKWhTotal, kWhTotal, !oldKWhTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetKWhTotal() {
		double oldKWhTotal = kWhTotal;
		boolean oldKWhTotalESet = kWhTotalESet;
		kWhTotal = KWH_TOTAL_EDEFAULT;
		kWhTotalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ControlPackage.STORAGE_CONTROLLER__KWH_TOTAL, oldKWhTotal, KWH_TOTAL_EDEFAULT, oldKWhTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetKWhTotal() {
		return kWhTotalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWTotal() {
		return kWTotal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWTotal(double newKWTotal) {
		double oldKWTotal = kWTotal;
		kWTotal = newKWTotal;
		boolean oldKWTotalESet = kWTotalESet;
		kWTotalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__KW_TOTAL, oldKWTotal, kWTotal, !oldKWTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetKWTotal() {
		double oldKWTotal = kWTotal;
		boolean oldKWTotalESet = kWTotalESet;
		kWTotal = KW_TOTAL_EDEFAULT;
		kWTotalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ControlPackage.STORAGE_CONTROLLER__KW_TOTAL, oldKWTotal, KW_TOTAL_EDEFAULT, oldKWTotalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetKWTotal() {
		return kWTotalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWhActual() {
		return kWhActual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWhActual(double newKWhActual) {
		double oldKWhActual = kWhActual;
		kWhActual = newKWhActual;
		boolean oldKWhActualESet = kWhActualESet;
		kWhActualESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__KWH_ACTUAL, oldKWhActual, kWhActual, !oldKWhActualESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetKWhActual() {
		double oldKWhActual = kWhActual;
		boolean oldKWhActualESet = kWhActualESet;
		kWhActual = KWH_ACTUAL_EDEFAULT;
		kWhActualESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ControlPackage.STORAGE_CONTROLLER__KWH_ACTUAL, oldKWhActual, KWH_ACTUAL_EDEFAULT, oldKWhActualESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetKWhActual() {
		return kWhActualESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWActual() {
		return kWActual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWActual(double newKWActual) {
		double oldKWActual = kWActual;
		kWActual = newKWActual;
		boolean oldKWActualESet = kWActualESet;
		kWActualESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__KW_ACTUAL, oldKWActual, kWActual, !oldKWActualESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetKWActual() {
		double oldKWActual = kWActual;
		boolean oldKWActualESet = kWActualESet;
		kWActual = KW_ACTUAL_EDEFAULT;
		kWActualESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ControlPackage.STORAGE_CONTROLLER__KW_ACTUAL, oldKWActual, KW_ACTUAL_EDEFAULT, oldKWActualESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetKWActual() {
		return kWActualESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKWNeed() {
		return kWNeed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKWNeed(double newKWNeed) {
		double oldKWNeed = kWNeed;
		kWNeed = newKWNeed;
		boolean oldKWNeedESet = kWNeedESet;
		kWNeedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__KW_NEED, oldKWNeed, kWNeed, !oldKWNeedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetKWNeed() {
		double oldKWNeed = kWNeed;
		boolean oldKWNeedESet = kWNeedESet;
		kWNeed = KW_NEED_EDEFAULT;
		kWNeedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ControlPackage.STORAGE_CONTROLLER__KW_NEED, oldKWNeed, KW_NEED_EDEFAULT, oldKWNeedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetKWNeed() {
		return kWNeedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctParticipation() {
		return pctParticipation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctParticipation(double newPctParticipation) {
		double oldPctParticipation = pctParticipation;
		pctParticipation = newPctParticipation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__PCT_PARTICIPATION, oldPctParticipation, pctParticipation));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.STORAGE_CONTROLLER__YEARLY, oldYearly, yearly));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__YEARLY, oldYearly, yearly));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.STORAGE_CONTROLLER__DAILY, oldDaily, daily));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__DAILY, oldDaily, daily));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.STORAGE_CONTROLLER__DUTY, oldDuty, duty));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__DUTY, oldDuty, duty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEventLog() {
		return eventLog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventLog(boolean newEventLog) {
		boolean oldEventLog = eventLog;
		eventLog = newEventLog;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__EVENT_LOG, oldEventLog, eventLog));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVarDispatch() {
		return varDispatch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarDispatch(boolean newVarDispatch) {
		boolean oldVarDispatch = varDispatch;
		varDispatch = newVarDispatch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__VAR_DISPATCH, oldVarDispatch, varDispatch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInhibitTime() {
		return inhibitTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInhibitTime(int newInhibitTime) {
		int oldInhibitTime = inhibitTime;
		inhibitTime = newInhibitTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.STORAGE_CONTROLLER__INHIBIT_TIME, oldInhibitTime, inhibitTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.STORAGE_CONTROLLER__KW_TARGET:
				return getKWTarget();
			case ControlPackage.STORAGE_CONTROLLER__PCT_KW_BAND:
				return getPctKWBand();
			case ControlPackage.STORAGE_CONTROLLER__PF_TARGET:
				return getPFTarget();
			case ControlPackage.STORAGE_CONTROLLER__PF_BAND:
				return getPFBand();
			case ControlPackage.STORAGE_CONTROLLER__ELEMENTS:
				return getElements();
			case ControlPackage.STORAGE_CONTROLLER__WEIGHTS:
				return getWeights();
			case ControlPackage.STORAGE_CONTROLLER__MODE_DISCHARGE:
				return getModeDischarge();
			case ControlPackage.STORAGE_CONTROLLER__MODE_CHARGE:
				return getModeCharge();
			case ControlPackage.STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER:
				return getTimeDischargeTrigger();
			case ControlPackage.STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER:
				return getTimeChargeTrigger();
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KW:
				return getPctRateKW();
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KV_AR:
				return getPctRateKVAr();
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_CHARGE:
				return getPctRateCharge();
			case ControlPackage.STORAGE_CONTROLLER__PCT_RESERVE:
				return getPctReserve();
			case ControlPackage.STORAGE_CONTROLLER__KWH_TOTAL:
				return getKWhTotal();
			case ControlPackage.STORAGE_CONTROLLER__KW_TOTAL:
				return getKWTotal();
			case ControlPackage.STORAGE_CONTROLLER__KWH_ACTUAL:
				return getKWhActual();
			case ControlPackage.STORAGE_CONTROLLER__KW_ACTUAL:
				return getKWActual();
			case ControlPackage.STORAGE_CONTROLLER__KW_NEED:
				return getKWNeed();
			case ControlPackage.STORAGE_CONTROLLER__PCT_PARTICIPATION:
				return getPctParticipation();
			case ControlPackage.STORAGE_CONTROLLER__YEARLY:
				if (resolve) return getYearly();
				return basicGetYearly();
			case ControlPackage.STORAGE_CONTROLLER__DAILY:
				if (resolve) return getDaily();
				return basicGetDaily();
			case ControlPackage.STORAGE_CONTROLLER__DUTY:
				if (resolve) return getDuty();
				return basicGetDuty();
			case ControlPackage.STORAGE_CONTROLLER__EVENT_LOG:
				return isEventLog();
			case ControlPackage.STORAGE_CONTROLLER__VAR_DISPATCH:
				return isVarDispatch();
			case ControlPackage.STORAGE_CONTROLLER__INHIBIT_TIME:
				return getInhibitTime();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ControlPackage.STORAGE_CONTROLLER__KW_TARGET:
				setKWTarget((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_KW_BAND:
				setPctKWBand((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PF_TARGET:
				setPFTarget((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PF_BAND:
				setPFBand((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends Storage>)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__WEIGHTS:
				getWeights().clear();
				getWeights().addAll((Collection<? extends Double>)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__MODE_DISCHARGE:
				setModeDischarge((dischargeMode)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__MODE_CHARGE:
				setModeCharge((chargeMode)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER:
				setTimeDischargeTrigger((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER:
				setTimeChargeTrigger((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KW:
				setPctRateKW((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KV_AR:
				setPctRateKVAr((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_CHARGE:
				setPctRateCharge((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RESERVE:
				setPctReserve((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__KWH_TOTAL:
				setKWhTotal((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__KW_TOTAL:
				setKWTotal((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__KWH_ACTUAL:
				setKWhActual((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__KW_ACTUAL:
				setKWActual((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__KW_NEED:
				setKWNeed((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_PARTICIPATION:
				setPctParticipation((Double)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__YEARLY:
				setYearly((LoadShape)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__DAILY:
				setDaily((LoadShape)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__DUTY:
				setDuty((LoadShape)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__EVENT_LOG:
				setEventLog((Boolean)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__VAR_DISPATCH:
				setVarDispatch((Boolean)newValue);
				return;
			case ControlPackage.STORAGE_CONTROLLER__INHIBIT_TIME:
				setInhibitTime((Integer)newValue);
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
			case ControlPackage.STORAGE_CONTROLLER__KW_TARGET:
				setKWTarget(KW_TARGET_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_KW_BAND:
				setPctKWBand(PCT_KW_BAND_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PF_TARGET:
				setPFTarget(PF_TARGET_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PF_BAND:
				setPFBand(PF_BAND_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__ELEMENTS:
				getElements().clear();
				return;
			case ControlPackage.STORAGE_CONTROLLER__WEIGHTS:
				getWeights().clear();
				return;
			case ControlPackage.STORAGE_CONTROLLER__MODE_DISCHARGE:
				setModeDischarge(MODE_DISCHARGE_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__MODE_CHARGE:
				setModeCharge(MODE_CHARGE_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER:
				setTimeDischargeTrigger(TIME_DISCHARGE_TRIGGER_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER:
				setTimeChargeTrigger(TIME_CHARGE_TRIGGER_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KW:
				setPctRateKW(PCT_RATE_KW_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KV_AR:
				setPctRateKVAr(PCT_RATE_KV_AR_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_CHARGE:
				setPctRateCharge(PCT_RATE_CHARGE_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RESERVE:
				setPctReserve(PCT_RESERVE_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__KWH_TOTAL:
				unsetKWhTotal();
				return;
			case ControlPackage.STORAGE_CONTROLLER__KW_TOTAL:
				unsetKWTotal();
				return;
			case ControlPackage.STORAGE_CONTROLLER__KWH_ACTUAL:
				unsetKWhActual();
				return;
			case ControlPackage.STORAGE_CONTROLLER__KW_ACTUAL:
				unsetKWActual();
				return;
			case ControlPackage.STORAGE_CONTROLLER__KW_NEED:
				unsetKWNeed();
				return;
			case ControlPackage.STORAGE_CONTROLLER__PCT_PARTICIPATION:
				setPctParticipation(PCT_PARTICIPATION_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__YEARLY:
				setYearly((LoadShape)null);
				return;
			case ControlPackage.STORAGE_CONTROLLER__DAILY:
				setDaily((LoadShape)null);
				return;
			case ControlPackage.STORAGE_CONTROLLER__DUTY:
				setDuty((LoadShape)null);
				return;
			case ControlPackage.STORAGE_CONTROLLER__EVENT_LOG:
				setEventLog(EVENT_LOG_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__VAR_DISPATCH:
				setVarDispatch(VAR_DISPATCH_EDEFAULT);
				return;
			case ControlPackage.STORAGE_CONTROLLER__INHIBIT_TIME:
				setInhibitTime(INHIBIT_TIME_EDEFAULT);
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
			case ControlPackage.STORAGE_CONTROLLER__KW_TARGET:
				return kWTarget != KW_TARGET_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PCT_KW_BAND:
				return pctKWBand != PCT_KW_BAND_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PF_TARGET:
				return pFTarget != PF_TARGET_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PF_BAND:
				return pFBand != PF_BAND_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case ControlPackage.STORAGE_CONTROLLER__WEIGHTS:
				return weights != null && !weights.isEmpty();
			case ControlPackage.STORAGE_CONTROLLER__MODE_DISCHARGE:
				return modeDischarge != MODE_DISCHARGE_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__MODE_CHARGE:
				return modeCharge != MODE_CHARGE_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__TIME_DISCHARGE_TRIGGER:
				return timeDischargeTrigger != TIME_DISCHARGE_TRIGGER_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__TIME_CHARGE_TRIGGER:
				return timeChargeTrigger != TIME_CHARGE_TRIGGER_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KW:
				return pctRateKW != PCT_RATE_KW_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_KV_AR:
				return pctRateKVAr != PCT_RATE_KV_AR_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RATE_CHARGE:
				return pctRateCharge != PCT_RATE_CHARGE_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__PCT_RESERVE:
				return pctReserve != PCT_RESERVE_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__KWH_TOTAL:
				return isSetKWhTotal();
			case ControlPackage.STORAGE_CONTROLLER__KW_TOTAL:
				return isSetKWTotal();
			case ControlPackage.STORAGE_CONTROLLER__KWH_ACTUAL:
				return isSetKWhActual();
			case ControlPackage.STORAGE_CONTROLLER__KW_ACTUAL:
				return isSetKWActual();
			case ControlPackage.STORAGE_CONTROLLER__KW_NEED:
				return isSetKWNeed();
			case ControlPackage.STORAGE_CONTROLLER__PCT_PARTICIPATION:
				return pctParticipation != PCT_PARTICIPATION_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__YEARLY:
				return yearly != null;
			case ControlPackage.STORAGE_CONTROLLER__DAILY:
				return daily != null;
			case ControlPackage.STORAGE_CONTROLLER__DUTY:
				return duty != null;
			case ControlPackage.STORAGE_CONTROLLER__EVENT_LOG:
				return eventLog != EVENT_LOG_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__VAR_DISPATCH:
				return varDispatch != VAR_DISPATCH_EDEFAULT;
			case ControlPackage.STORAGE_CONTROLLER__INHIBIT_TIME:
				return inhibitTime != INHIBIT_TIME_EDEFAULT;
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
		result.append(" (kWTarget: ");
		result.append(kWTarget);
		result.append(", pctKWBand: ");
		result.append(pctKWBand);
		result.append(", pFTarget: ");
		result.append(pFTarget);
		result.append(", pFBand: ");
		result.append(pFBand);
		result.append(", weights: ");
		result.append(weights);
		result.append(", modeDischarge: ");
		result.append(modeDischarge);
		result.append(", modeCharge: ");
		result.append(modeCharge);
		result.append(", timeDischargeTrigger: ");
		result.append(timeDischargeTrigger);
		result.append(", timeChargeTrigger: ");
		result.append(timeChargeTrigger);
		result.append(", pctRateKW: ");
		result.append(pctRateKW);
		result.append(", pctRateKVAr: ");
		result.append(pctRateKVAr);
		result.append(", pctRateCharge: ");
		result.append(pctRateCharge);
		result.append(", pctReserve: ");
		result.append(pctReserve);
		result.append(", kWhTotal: ");
		if (kWhTotalESet) result.append(kWhTotal); else result.append("<unset>");
		result.append(", kWTotal: ");
		if (kWTotalESet) result.append(kWTotal); else result.append("<unset>");
		result.append(", kWhActual: ");
		if (kWhActualESet) result.append(kWhActual); else result.append("<unset>");
		result.append(", kWActual: ");
		if (kWActualESet) result.append(kWActual); else result.append("<unset>");
		result.append(", kWNeed: ");
		if (kWNeedESet) result.append(kWNeed); else result.append("<unset>");
		result.append(", pctParticipation: ");
		result.append(pctParticipation);
		result.append(", eventLog: ");
		result.append(eventLog);
		result.append(", varDispatch: ");
		result.append(varDispatch);
		result.append(", inhibitTime: ");
		result.append(inhibitTime);
		result.append(')');
		return result.toString();
	}

} //StorageControllerImpl
