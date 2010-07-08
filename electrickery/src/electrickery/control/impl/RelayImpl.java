/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.control.impl;



import electrickery.common.tripAction;
import electrickery.control.ControlPackage;
import electrickery.control.Relay;
import electrickery.control.relayType;
import electrickery.general.TimeCurrentCurve;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relay</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.control.impl.RelayImpl#getMonitoredObj <em>Monitored Obj</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getMonitoredTerm <em>Monitored Term</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getSwitchedObj <em>Switched Obj</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getSwitchedTerm <em>Switched Term</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getType <em>Type</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getPhaseCurve <em>Phase Curve</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getGroundCurve <em>Ground Curve</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getPhaseTrip <em>Phase Trip</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getGroundTrip <em>Ground Trip</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getTDPhase <em>TD Phase</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getTDGround <em>TD Ground</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getPhaseInst <em>Phase Inst</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getGroundInst <em>Ground Inst</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getReset <em>Reset</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getShots <em>Shots</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getRecloseIntervals <em>Reclose Intervals</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getDelay <em>Delay</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getOvervoltCurve <em>Overvolt Curve</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getUndervoltCurve <em>Undervolt Curve</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getKVBase <em>KV Base</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getPctPickup47 <em>Pct Pickup47</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getPctAmps46 <em>Pct Amps46</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getPctPickup46 <em>Pct Pickup46</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getISQT46 <em>ISQT46</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getOvertrip <em>Overtrip</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getUndertrip <em>Undertrip</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getBreakerTime <em>Breaker Time</em>}</li>
 *   <li>{@link electrickery.control.impl.RelayImpl#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelayImpl extends ControlElementImpl implements Relay {
	/**
	 * The default value of the '{@link #getMonitoredObj() <em>Monitored Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredObj()
	 * @generated
	 * @ordered
	 */
	protected static final String MONITORED_OBJ_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMonitoredObj() <em>Monitored Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredObj()
	 * @generated
	 * @ordered
	 */
	protected String monitoredObj = MONITORED_OBJ_EDEFAULT;

	/**
	 * The default value of the '{@link #getMonitoredTerm() <em>Monitored Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredTerm()
	 * @generated
	 * @ordered
	 */
	protected static final int MONITORED_TERM_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getMonitoredTerm() <em>Monitored Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredTerm()
	 * @generated
	 * @ordered
	 */
	protected int monitoredTerm = MONITORED_TERM_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwitchedObj() <em>Switched Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedObj()
	 * @generated
	 * @ordered
	 */
	protected static final String SWITCHED_OBJ_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSwitchedObj() <em>Switched Obj</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedObj()
	 * @generated
	 * @ordered
	 */
	protected String switchedObj = SWITCHED_OBJ_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwitchedTerm() <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedTerm()
	 * @generated
	 * @ordered
	 */
	protected static final int SWITCHED_TERM_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getSwitchedTerm() <em>Switched Term</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchedTerm()
	 * @generated
	 * @ordered
	 */
	protected int switchedTerm = SWITCHED_TERM_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final relayType TYPE_EDEFAULT = relayType.CURRENT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected relayType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhaseCurve() <em>Phase Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseCurve()
	 * @generated
	 * @ordered
	 */
	protected static final String PHASE_CURVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhaseCurve() <em>Phase Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseCurve()
	 * @generated
	 * @ordered
	 */
	protected String phaseCurve = PHASE_CURVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroundCurve() <em>Ground Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundCurve()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUND_CURVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroundCurve() <em>Ground Curve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundCurve()
	 * @generated
	 * @ordered
	 */
	protected String groundCurve = GROUND_CURVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhaseTrip() <em>Phase Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseTrip()
	 * @generated
	 * @ordered
	 */
	protected static final double PHASE_TRIP_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getPhaseTrip() <em>Phase Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseTrip()
	 * @generated
	 * @ordered
	 */
	protected double phaseTrip = PHASE_TRIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroundTrip() <em>Ground Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundTrip()
	 * @generated
	 * @ordered
	 */
	protected static final double GROUND_TRIP_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getGroundTrip() <em>Ground Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundTrip()
	 * @generated
	 * @ordered
	 */
	protected double groundTrip = GROUND_TRIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getTDPhase() <em>TD Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDPhase()
	 * @generated
	 * @ordered
	 */
	protected static final double TD_PHASE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTDPhase() <em>TD Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDPhase()
	 * @generated
	 * @ordered
	 */
	protected double tDPhase = TD_PHASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTDGround() <em>TD Ground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDGround()
	 * @generated
	 * @ordered
	 */
	protected static final double TD_GROUND_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTDGround() <em>TD Ground</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDGround()
	 * @generated
	 * @ordered
	 */
	protected double tDGround = TD_GROUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhaseInst() <em>Phase Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseInst()
	 * @generated
	 * @ordered
	 */
	protected static final double PHASE_INST_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPhaseInst() <em>Phase Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseInst()
	 * @generated
	 * @ordered
	 */
	protected double phaseInst = PHASE_INST_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroundInst() <em>Ground Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundInst()
	 * @generated
	 * @ordered
	 */
	protected static final double GROUND_INST_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getGroundInst() <em>Ground Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundInst()
	 * @generated
	 * @ordered
	 */
	protected double groundInst = GROUND_INST_EDEFAULT;

	/**
	 * The default value of the '{@link #getReset() <em>Reset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReset()
	 * @generated
	 * @ordered
	 */
	protected static final double RESET_EDEFAULT = 15.0;

	/**
	 * The cached value of the '{@link #getReset() <em>Reset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReset()
	 * @generated
	 * @ordered
	 */
	protected double reset = RESET_EDEFAULT;

	/**
	 * The default value of the '{@link #getShots() <em>Shots</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShots()
	 * @generated
	 * @ordered
	 */
	protected static final int SHOTS_EDEFAULT = 4;

	/**
	 * The cached value of the '{@link #getShots() <em>Shots</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShots()
	 * @generated
	 * @ordered
	 */
	protected int shots = SHOTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRecloseIntervals() <em>Reclose Intervals</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecloseIntervals()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> recloseIntervals;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final double DELAY_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected double delay = DELAY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOvervoltCurve() <em>Overvolt Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvervoltCurve()
	 * @generated
	 * @ordered
	 */
	protected TimeCurrentCurve overvoltCurve;

	/**
	 * The cached value of the '{@link #getUndervoltCurve() <em>Undervolt Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUndervoltCurve()
	 * @generated
	 * @ordered
	 */
	protected TimeCurrentCurve undervoltCurve;

	/**
	 * The default value of the '{@link #getKVBase() <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVBase()
	 * @generated
	 * @ordered
	 */
	protected static final double KV_BASE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVBase() <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVBase()
	 * @generated
	 * @ordered
	 */
	protected double kVBase = KV_BASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctPickup47() <em>Pct Pickup47</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctPickup47()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_PICKUP47_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctPickup47() <em>Pct Pickup47</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctPickup47()
	 * @generated
	 * @ordered
	 */
	protected double pctPickup47 = PCT_PICKUP47_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctAmps46() <em>Pct Amps46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctAmps46()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_AMPS46_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPctAmps46() <em>Pct Amps46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctAmps46()
	 * @generated
	 * @ordered
	 */
	protected double pctAmps46 = PCT_AMPS46_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctPickup46() <em>Pct Pickup46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctPickup46()
	 * @generated
	 * @ordered
	 */
	protected static final double PCT_PICKUP46_EDEFAULT = 20.0;

	/**
	 * The cached value of the '{@link #getPctPickup46() <em>Pct Pickup46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctPickup46()
	 * @generated
	 * @ordered
	 */
	protected double pctPickup46 = PCT_PICKUP46_EDEFAULT;

	/**
	 * The default value of the '{@link #getISQT46() <em>ISQT46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getISQT46()
	 * @generated
	 * @ordered
	 */
	protected static final double ISQT46_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getISQT46() <em>ISQT46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getISQT46()
	 * @generated
	 * @ordered
	 */
	protected double iSQT46 = ISQT46_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariable() <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected String variable = VARIABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOvertrip() <em>Overtrip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertrip()
	 * @generated
	 * @ordered
	 */
	protected static final double OVERTRIP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOvertrip() <em>Overtrip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertrip()
	 * @generated
	 * @ordered
	 */
	protected double overtrip = OVERTRIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getUndertrip() <em>Undertrip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUndertrip()
	 * @generated
	 * @ordered
	 */
	protected static final double UNDERTRIP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUndertrip() <em>Undertrip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUndertrip()
	 * @generated
	 * @ordered
	 */
	protected double undertrip = UNDERTRIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getBreakerTime() <em>Breaker Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBreakerTime()
	 * @generated
	 * @ordered
	 */
	protected static final double BREAKER_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getBreakerTime() <em>Breaker Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBreakerTime()
	 * @generated
	 * @ordered
	 */
	protected double breakerTime = BREAKER_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final tripAction ACTION_EDEFAULT = tripAction.TRIP_OPEN;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected tripAction action = ACTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.RELAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMonitoredObj() {
		return monitoredObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoredObj(String newMonitoredObj) {
		String oldMonitoredObj = monitoredObj;
		monitoredObj = newMonitoredObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__MONITORED_OBJ, oldMonitoredObj, monitoredObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMonitoredTerm() {
		return monitoredTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoredTerm(int newMonitoredTerm) {
		int oldMonitoredTerm = monitoredTerm;
		monitoredTerm = newMonitoredTerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__MONITORED_TERM, oldMonitoredTerm, monitoredTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSwitchedObj() {
		return switchedObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchedObj(String newSwitchedObj) {
		String oldSwitchedObj = switchedObj;
		switchedObj = newSwitchedObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__SWITCHED_OBJ, oldSwitchedObj, switchedObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSwitchedTerm() {
		return switchedTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchedTerm(int newSwitchedTerm) {
		int oldSwitchedTerm = switchedTerm;
		switchedTerm = newSwitchedTerm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__SWITCHED_TERM, oldSwitchedTerm, switchedTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public relayType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(relayType newType) {
		relayType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhaseCurve() {
		return phaseCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseCurve(String newPhaseCurve) {
		String oldPhaseCurve = phaseCurve;
		phaseCurve = newPhaseCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__PHASE_CURVE, oldPhaseCurve, phaseCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroundCurve() {
		return groundCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundCurve(String newGroundCurve) {
		String oldGroundCurve = groundCurve;
		groundCurve = newGroundCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__GROUND_CURVE, oldGroundCurve, groundCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPhaseTrip() {
		return phaseTrip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseTrip(double newPhaseTrip) {
		double oldPhaseTrip = phaseTrip;
		phaseTrip = newPhaseTrip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__PHASE_TRIP, oldPhaseTrip, phaseTrip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getGroundTrip() {
		return groundTrip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundTrip(double newGroundTrip) {
		double oldGroundTrip = groundTrip;
		groundTrip = newGroundTrip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__GROUND_TRIP, oldGroundTrip, groundTrip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTDPhase() {
		return tDPhase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDPhase(double newTDPhase) {
		double oldTDPhase = tDPhase;
		tDPhase = newTDPhase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__TD_PHASE, oldTDPhase, tDPhase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTDGround() {
		return tDGround;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDGround(double newTDGround) {
		double oldTDGround = tDGround;
		tDGround = newTDGround;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__TD_GROUND, oldTDGround, tDGround));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPhaseInst() {
		return phaseInst;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseInst(double newPhaseInst) {
		double oldPhaseInst = phaseInst;
		phaseInst = newPhaseInst;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__PHASE_INST, oldPhaseInst, phaseInst));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getGroundInst() {
		return groundInst;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundInst(double newGroundInst) {
		double oldGroundInst = groundInst;
		groundInst = newGroundInst;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__GROUND_INST, oldGroundInst, groundInst));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getReset() {
		return reset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReset(double newReset) {
		double oldReset = reset;
		reset = newReset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__RESET, oldReset, reset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getShots() {
		return shots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShots(int newShots) {
		int oldShots = shots;
		shots = newShots;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__SHOTS, oldShots, shots));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getRecloseIntervals() {
		if (recloseIntervals == null) {
			recloseIntervals = new EDataTypeUniqueEList<Double>(Double.class, this, ControlPackage.RELAY__RECLOSE_INTERVALS);
		}
		return recloseIntervals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDelay() {
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelay(double newDelay) {
		double oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__DELAY, oldDelay, delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeCurrentCurve getOvervoltCurve() {
		if (overvoltCurve != null && overvoltCurve.eIsProxy()) {
			InternalEObject oldOvervoltCurve = (InternalEObject)overvoltCurve;
			overvoltCurve = (TimeCurrentCurve)eResolveProxy(oldOvervoltCurve);
			if (overvoltCurve != oldOvervoltCurve) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.RELAY__OVERVOLT_CURVE, oldOvervoltCurve, overvoltCurve));
			}
		}
		return overvoltCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeCurrentCurve basicGetOvervoltCurve() {
		return overvoltCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvervoltCurve(TimeCurrentCurve newOvervoltCurve) {
		TimeCurrentCurve oldOvervoltCurve = overvoltCurve;
		overvoltCurve = newOvervoltCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__OVERVOLT_CURVE, oldOvervoltCurve, overvoltCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeCurrentCurve getUndervoltCurve() {
		if (undervoltCurve != null && undervoltCurve.eIsProxy()) {
			InternalEObject oldUndervoltCurve = (InternalEObject)undervoltCurve;
			undervoltCurve = (TimeCurrentCurve)eResolveProxy(oldUndervoltCurve);
			if (undervoltCurve != oldUndervoltCurve) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ControlPackage.RELAY__UNDERVOLT_CURVE, oldUndervoltCurve, undervoltCurve));
			}
		}
		return undervoltCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeCurrentCurve basicGetUndervoltCurve() {
		return undervoltCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUndervoltCurve(TimeCurrentCurve newUndervoltCurve) {
		TimeCurrentCurve oldUndervoltCurve = undervoltCurve;
		undervoltCurve = newUndervoltCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__UNDERVOLT_CURVE, oldUndervoltCurve, undervoltCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVBase() {
		return kVBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVBase(double newKVBase) {
		double oldKVBase = kVBase;
		kVBase = newKVBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__KV_BASE, oldKVBase, kVBase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctPickup47() {
		return pctPickup47;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctPickup47(double newPctPickup47) {
		double oldPctPickup47 = pctPickup47;
		pctPickup47 = newPctPickup47;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__PCT_PICKUP47, oldPctPickup47, pctPickup47));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctAmps46() {
		return pctAmps46;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctAmps46(double newPctAmps46) {
		double oldPctAmps46 = pctAmps46;
		pctAmps46 = newPctAmps46;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__PCT_AMPS46, oldPctAmps46, pctAmps46));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPctPickup46() {
		return pctPickup46;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctPickup46(double newPctPickup46) {
		double oldPctPickup46 = pctPickup46;
		pctPickup46 = newPctPickup46;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__PCT_PICKUP46, oldPctPickup46, pctPickup46));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getISQT46() {
		return iSQT46;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setISQT46(double newISQT46) {
		double oldISQT46 = iSQT46;
		iSQT46 = newISQT46;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__ISQT46, oldISQT46, iSQT46));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariable(String newVariable) {
		String oldVariable = variable;
		variable = newVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__VARIABLE, oldVariable, variable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOvertrip() {
		return overtrip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertrip(double newOvertrip) {
		double oldOvertrip = overtrip;
		overtrip = newOvertrip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__OVERTRIP, oldOvertrip, overtrip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getUndertrip() {
		return undertrip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUndertrip(double newUndertrip) {
		double oldUndertrip = undertrip;
		undertrip = newUndertrip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__UNDERTRIP, oldUndertrip, undertrip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBreakerTime() {
		return breakerTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBreakerTime(double newBreakerTime) {
		double oldBreakerTime = breakerTime;
		breakerTime = newBreakerTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__BREAKER_TIME, oldBreakerTime, breakerTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tripAction getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(tripAction newAction) {
		tripAction oldAction = action;
		action = newAction == null ? ACTION_EDEFAULT : newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RELAY__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.RELAY__MONITORED_OBJ:
				return getMonitoredObj();
			case ControlPackage.RELAY__MONITORED_TERM:
				return getMonitoredTerm();
			case ControlPackage.RELAY__SWITCHED_OBJ:
				return getSwitchedObj();
			case ControlPackage.RELAY__SWITCHED_TERM:
				return getSwitchedTerm();
			case ControlPackage.RELAY__TYPE:
				return getType();
			case ControlPackage.RELAY__PHASE_CURVE:
				return getPhaseCurve();
			case ControlPackage.RELAY__GROUND_CURVE:
				return getGroundCurve();
			case ControlPackage.RELAY__PHASE_TRIP:
				return getPhaseTrip();
			case ControlPackage.RELAY__GROUND_TRIP:
				return getGroundTrip();
			case ControlPackage.RELAY__TD_PHASE:
				return getTDPhase();
			case ControlPackage.RELAY__TD_GROUND:
				return getTDGround();
			case ControlPackage.RELAY__PHASE_INST:
				return getPhaseInst();
			case ControlPackage.RELAY__GROUND_INST:
				return getGroundInst();
			case ControlPackage.RELAY__RESET:
				return getReset();
			case ControlPackage.RELAY__SHOTS:
				return getShots();
			case ControlPackage.RELAY__RECLOSE_INTERVALS:
				return getRecloseIntervals();
			case ControlPackage.RELAY__DELAY:
				return getDelay();
			case ControlPackage.RELAY__OVERVOLT_CURVE:
				if (resolve) return getOvervoltCurve();
				return basicGetOvervoltCurve();
			case ControlPackage.RELAY__UNDERVOLT_CURVE:
				if (resolve) return getUndervoltCurve();
				return basicGetUndervoltCurve();
			case ControlPackage.RELAY__KV_BASE:
				return getKVBase();
			case ControlPackage.RELAY__PCT_PICKUP47:
				return getPctPickup47();
			case ControlPackage.RELAY__PCT_AMPS46:
				return getPctAmps46();
			case ControlPackage.RELAY__PCT_PICKUP46:
				return getPctPickup46();
			case ControlPackage.RELAY__ISQT46:
				return getISQT46();
			case ControlPackage.RELAY__VARIABLE:
				return getVariable();
			case ControlPackage.RELAY__OVERTRIP:
				return getOvertrip();
			case ControlPackage.RELAY__UNDERTRIP:
				return getUndertrip();
			case ControlPackage.RELAY__BREAKER_TIME:
				return getBreakerTime();
			case ControlPackage.RELAY__ACTION:
				return getAction();
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
			case ControlPackage.RELAY__MONITORED_OBJ:
				setMonitoredObj((String)newValue);
				return;
			case ControlPackage.RELAY__MONITORED_TERM:
				setMonitoredTerm((Integer)newValue);
				return;
			case ControlPackage.RELAY__SWITCHED_OBJ:
				setSwitchedObj((String)newValue);
				return;
			case ControlPackage.RELAY__SWITCHED_TERM:
				setSwitchedTerm((Integer)newValue);
				return;
			case ControlPackage.RELAY__TYPE:
				setType((relayType)newValue);
				return;
			case ControlPackage.RELAY__PHASE_CURVE:
				setPhaseCurve((String)newValue);
				return;
			case ControlPackage.RELAY__GROUND_CURVE:
				setGroundCurve((String)newValue);
				return;
			case ControlPackage.RELAY__PHASE_TRIP:
				setPhaseTrip((Double)newValue);
				return;
			case ControlPackage.RELAY__GROUND_TRIP:
				setGroundTrip((Double)newValue);
				return;
			case ControlPackage.RELAY__TD_PHASE:
				setTDPhase((Double)newValue);
				return;
			case ControlPackage.RELAY__TD_GROUND:
				setTDGround((Double)newValue);
				return;
			case ControlPackage.RELAY__PHASE_INST:
				setPhaseInst((Double)newValue);
				return;
			case ControlPackage.RELAY__GROUND_INST:
				setGroundInst((Double)newValue);
				return;
			case ControlPackage.RELAY__RESET:
				setReset((Double)newValue);
				return;
			case ControlPackage.RELAY__SHOTS:
				setShots((Integer)newValue);
				return;
			case ControlPackage.RELAY__RECLOSE_INTERVALS:
				getRecloseIntervals().clear();
				getRecloseIntervals().addAll((Collection<? extends Double>)newValue);
				return;
			case ControlPackage.RELAY__DELAY:
				setDelay((Double)newValue);
				return;
			case ControlPackage.RELAY__OVERVOLT_CURVE:
				setOvervoltCurve((TimeCurrentCurve)newValue);
				return;
			case ControlPackage.RELAY__UNDERVOLT_CURVE:
				setUndervoltCurve((TimeCurrentCurve)newValue);
				return;
			case ControlPackage.RELAY__KV_BASE:
				setKVBase((Double)newValue);
				return;
			case ControlPackage.RELAY__PCT_PICKUP47:
				setPctPickup47((Double)newValue);
				return;
			case ControlPackage.RELAY__PCT_AMPS46:
				setPctAmps46((Double)newValue);
				return;
			case ControlPackage.RELAY__PCT_PICKUP46:
				setPctPickup46((Double)newValue);
				return;
			case ControlPackage.RELAY__ISQT46:
				setISQT46((Double)newValue);
				return;
			case ControlPackage.RELAY__VARIABLE:
				setVariable((String)newValue);
				return;
			case ControlPackage.RELAY__OVERTRIP:
				setOvertrip((Double)newValue);
				return;
			case ControlPackage.RELAY__UNDERTRIP:
				setUndertrip((Double)newValue);
				return;
			case ControlPackage.RELAY__BREAKER_TIME:
				setBreakerTime((Double)newValue);
				return;
			case ControlPackage.RELAY__ACTION:
				setAction((tripAction)newValue);
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
			case ControlPackage.RELAY__MONITORED_OBJ:
				setMonitoredObj(MONITORED_OBJ_EDEFAULT);
				return;
			case ControlPackage.RELAY__MONITORED_TERM:
				setMonitoredTerm(MONITORED_TERM_EDEFAULT);
				return;
			case ControlPackage.RELAY__SWITCHED_OBJ:
				setSwitchedObj(SWITCHED_OBJ_EDEFAULT);
				return;
			case ControlPackage.RELAY__SWITCHED_TERM:
				setSwitchedTerm(SWITCHED_TERM_EDEFAULT);
				return;
			case ControlPackage.RELAY__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ControlPackage.RELAY__PHASE_CURVE:
				setPhaseCurve(PHASE_CURVE_EDEFAULT);
				return;
			case ControlPackage.RELAY__GROUND_CURVE:
				setGroundCurve(GROUND_CURVE_EDEFAULT);
				return;
			case ControlPackage.RELAY__PHASE_TRIP:
				setPhaseTrip(PHASE_TRIP_EDEFAULT);
				return;
			case ControlPackage.RELAY__GROUND_TRIP:
				setGroundTrip(GROUND_TRIP_EDEFAULT);
				return;
			case ControlPackage.RELAY__TD_PHASE:
				setTDPhase(TD_PHASE_EDEFAULT);
				return;
			case ControlPackage.RELAY__TD_GROUND:
				setTDGround(TD_GROUND_EDEFAULT);
				return;
			case ControlPackage.RELAY__PHASE_INST:
				setPhaseInst(PHASE_INST_EDEFAULT);
				return;
			case ControlPackage.RELAY__GROUND_INST:
				setGroundInst(GROUND_INST_EDEFAULT);
				return;
			case ControlPackage.RELAY__RESET:
				setReset(RESET_EDEFAULT);
				return;
			case ControlPackage.RELAY__SHOTS:
				setShots(SHOTS_EDEFAULT);
				return;
			case ControlPackage.RELAY__RECLOSE_INTERVALS:
				getRecloseIntervals().clear();
				return;
			case ControlPackage.RELAY__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case ControlPackage.RELAY__OVERVOLT_CURVE:
				setOvervoltCurve((TimeCurrentCurve)null);
				return;
			case ControlPackage.RELAY__UNDERVOLT_CURVE:
				setUndervoltCurve((TimeCurrentCurve)null);
				return;
			case ControlPackage.RELAY__KV_BASE:
				setKVBase(KV_BASE_EDEFAULT);
				return;
			case ControlPackage.RELAY__PCT_PICKUP47:
				setPctPickup47(PCT_PICKUP47_EDEFAULT);
				return;
			case ControlPackage.RELAY__PCT_AMPS46:
				setPctAmps46(PCT_AMPS46_EDEFAULT);
				return;
			case ControlPackage.RELAY__PCT_PICKUP46:
				setPctPickup46(PCT_PICKUP46_EDEFAULT);
				return;
			case ControlPackage.RELAY__ISQT46:
				setISQT46(ISQT46_EDEFAULT);
				return;
			case ControlPackage.RELAY__VARIABLE:
				setVariable(VARIABLE_EDEFAULT);
				return;
			case ControlPackage.RELAY__OVERTRIP:
				setOvertrip(OVERTRIP_EDEFAULT);
				return;
			case ControlPackage.RELAY__UNDERTRIP:
				setUndertrip(UNDERTRIP_EDEFAULT);
				return;
			case ControlPackage.RELAY__BREAKER_TIME:
				setBreakerTime(BREAKER_TIME_EDEFAULT);
				return;
			case ControlPackage.RELAY__ACTION:
				setAction(ACTION_EDEFAULT);
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
			case ControlPackage.RELAY__MONITORED_OBJ:
				return MONITORED_OBJ_EDEFAULT == null ? monitoredObj != null : !MONITORED_OBJ_EDEFAULT.equals(monitoredObj);
			case ControlPackage.RELAY__MONITORED_TERM:
				return monitoredTerm != MONITORED_TERM_EDEFAULT;
			case ControlPackage.RELAY__SWITCHED_OBJ:
				return SWITCHED_OBJ_EDEFAULT == null ? switchedObj != null : !SWITCHED_OBJ_EDEFAULT.equals(switchedObj);
			case ControlPackage.RELAY__SWITCHED_TERM:
				return switchedTerm != SWITCHED_TERM_EDEFAULT;
			case ControlPackage.RELAY__TYPE:
				return type != TYPE_EDEFAULT;
			case ControlPackage.RELAY__PHASE_CURVE:
				return PHASE_CURVE_EDEFAULT == null ? phaseCurve != null : !PHASE_CURVE_EDEFAULT.equals(phaseCurve);
			case ControlPackage.RELAY__GROUND_CURVE:
				return GROUND_CURVE_EDEFAULT == null ? groundCurve != null : !GROUND_CURVE_EDEFAULT.equals(groundCurve);
			case ControlPackage.RELAY__PHASE_TRIP:
				return phaseTrip != PHASE_TRIP_EDEFAULT;
			case ControlPackage.RELAY__GROUND_TRIP:
				return groundTrip != GROUND_TRIP_EDEFAULT;
			case ControlPackage.RELAY__TD_PHASE:
				return tDPhase != TD_PHASE_EDEFAULT;
			case ControlPackage.RELAY__TD_GROUND:
				return tDGround != TD_GROUND_EDEFAULT;
			case ControlPackage.RELAY__PHASE_INST:
				return phaseInst != PHASE_INST_EDEFAULT;
			case ControlPackage.RELAY__GROUND_INST:
				return groundInst != GROUND_INST_EDEFAULT;
			case ControlPackage.RELAY__RESET:
				return reset != RESET_EDEFAULT;
			case ControlPackage.RELAY__SHOTS:
				return shots != SHOTS_EDEFAULT;
			case ControlPackage.RELAY__RECLOSE_INTERVALS:
				return recloseIntervals != null && !recloseIntervals.isEmpty();
			case ControlPackage.RELAY__DELAY:
				return delay != DELAY_EDEFAULT;
			case ControlPackage.RELAY__OVERVOLT_CURVE:
				return overvoltCurve != null;
			case ControlPackage.RELAY__UNDERVOLT_CURVE:
				return undervoltCurve != null;
			case ControlPackage.RELAY__KV_BASE:
				return kVBase != KV_BASE_EDEFAULT;
			case ControlPackage.RELAY__PCT_PICKUP47:
				return pctPickup47 != PCT_PICKUP47_EDEFAULT;
			case ControlPackage.RELAY__PCT_AMPS46:
				return pctAmps46 != PCT_AMPS46_EDEFAULT;
			case ControlPackage.RELAY__PCT_PICKUP46:
				return pctPickup46 != PCT_PICKUP46_EDEFAULT;
			case ControlPackage.RELAY__ISQT46:
				return iSQT46 != ISQT46_EDEFAULT;
			case ControlPackage.RELAY__VARIABLE:
				return VARIABLE_EDEFAULT == null ? variable != null : !VARIABLE_EDEFAULT.equals(variable);
			case ControlPackage.RELAY__OVERTRIP:
				return overtrip != OVERTRIP_EDEFAULT;
			case ControlPackage.RELAY__UNDERTRIP:
				return undertrip != UNDERTRIP_EDEFAULT;
			case ControlPackage.RELAY__BREAKER_TIME:
				return breakerTime != BREAKER_TIME_EDEFAULT;
			case ControlPackage.RELAY__ACTION:
				return action != ACTION_EDEFAULT;
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
		result.append(" (monitoredObj: ");
		result.append(monitoredObj);
		result.append(", monitoredTerm: ");
		result.append(monitoredTerm);
		result.append(", switchedObj: ");
		result.append(switchedObj);
		result.append(", switchedTerm: ");
		result.append(switchedTerm);
		result.append(", type: ");
		result.append(type);
		result.append(", phaseCurve: ");
		result.append(phaseCurve);
		result.append(", groundCurve: ");
		result.append(groundCurve);
		result.append(", phaseTrip: ");
		result.append(phaseTrip);
		result.append(", groundTrip: ");
		result.append(groundTrip);
		result.append(", tDPhase: ");
		result.append(tDPhase);
		result.append(", tDGround: ");
		result.append(tDGround);
		result.append(", phaseInst: ");
		result.append(phaseInst);
		result.append(", groundInst: ");
		result.append(groundInst);
		result.append(", reset: ");
		result.append(reset);
		result.append(", shots: ");
		result.append(shots);
		result.append(", recloseIntervals: ");
		result.append(recloseIntervals);
		result.append(", delay: ");
		result.append(delay);
		result.append(", kVBase: ");
		result.append(kVBase);
		result.append(", pctPickup47: ");
		result.append(pctPickup47);
		result.append(", pctAmps46: ");
		result.append(pctAmps46);
		result.append(", pctPickup46: ");
		result.append(pctPickup46);
		result.append(", iSQT46: ");
		result.append(iSQT46);
		result.append(", variable: ");
		result.append(variable);
		result.append(", overtrip: ");
		result.append(overtrip);
		result.append(", undertrip: ");
		result.append(undertrip);
		result.append(", breakerTime: ");
		result.append(breakerTime);
		result.append(", action: ");
		result.append(action);
		result.append(')');
		return result.toString();
	}

} //RelayImpl
