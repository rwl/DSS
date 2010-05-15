/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.control.impl;

import dss.common.tripAction;

import dss.control.ControlPackage;
import dss.control.Recloser;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Recloser</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.control.impl.RecloserImpl#getMonitoredObj <em>Monitored Obj</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getMonitoredTerm <em>Monitored Term</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getSwitchedObj <em>Switched Obj</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getSwitchedTerm <em>Switched Term</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getNFast <em>NFast</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getPhaseFast <em>Phase Fast</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getPhaseDelayed <em>Phase Delayed</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getGroundFast <em>Ground Fast</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getGroundDelayed <em>Ground Delayed</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getPhaseTrip <em>Phase Trip</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getGroundTrip <em>Ground Trip</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getPhaseInst <em>Phase Inst</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getGroundInst <em>Ground Inst</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getReset <em>Reset</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getShots <em>Shots</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getRecloseIntervals <em>Reclose Intervals</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getDelay <em>Delay</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getAction <em>Action</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getTDPhFast <em>TD Ph Fast</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getTDGrFast <em>TD Gr Fast</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getTDPhDelayed <em>TD Ph Delayed</em>}</li>
 *   <li>{@link dss.control.impl.RecloserImpl#getTDGrDelayed <em>TD Gr Delayed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RecloserImpl extends ControlElementImpl implements Recloser {
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
	 * The default value of the '{@link #getNFast() <em>NFast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNFast()
	 * @generated
	 * @ordered
	 */
	protected static final int NFAST_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getNFast() <em>NFast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNFast()
	 * @generated
	 * @ordered
	 */
	protected int nFast = NFAST_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhaseFast() <em>Phase Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseFast()
	 * @generated
	 * @ordered
	 */
	protected static final String PHASE_FAST_EDEFAULT = "A";

	/**
	 * The cached value of the '{@link #getPhaseFast() <em>Phase Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseFast()
	 * @generated
	 * @ordered
	 */
	protected String phaseFast = PHASE_FAST_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhaseDelayed() <em>Phase Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseDelayed()
	 * @generated
	 * @ordered
	 */
	protected static final String PHASE_DELAYED_EDEFAULT = "D";

	/**
	 * The cached value of the '{@link #getPhaseDelayed() <em>Phase Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseDelayed()
	 * @generated
	 * @ordered
	 */
	protected String phaseDelayed = PHASE_DELAYED_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroundFast() <em>Ground Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundFast()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUND_FAST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroundFast() <em>Ground Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundFast()
	 * @generated
	 * @ordered
	 */
	protected String groundFast = GROUND_FAST_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroundDelayed() <em>Ground Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundDelayed()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUND_DELAYED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroundDelayed() <em>Ground Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroundDelayed()
	 * @generated
	 * @ordered
	 */
	protected String groundDelayed = GROUND_DELAYED_EDEFAULT;

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
	 * The default value of the '{@link #getPhaseInst() <em>Phase Inst</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseInst()
	 * @generated
	 * @ordered
	 */
	protected static final double PHASE_INST_EDEFAULT = 1.0;

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
	 * The default value of the '{@link #getTDPhFast() <em>TD Ph Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDPhFast()
	 * @generated
	 * @ordered
	 */
	protected static final double TD_PH_FAST_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getTDPhFast() <em>TD Ph Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDPhFast()
	 * @generated
	 * @ordered
	 */
	protected double tDPhFast = TD_PH_FAST_EDEFAULT;

	/**
	 * The default value of the '{@link #getTDGrFast() <em>TD Gr Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDGrFast()
	 * @generated
	 * @ordered
	 */
	protected static final double TD_GR_FAST_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getTDGrFast() <em>TD Gr Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDGrFast()
	 * @generated
	 * @ordered
	 */
	protected double tDGrFast = TD_GR_FAST_EDEFAULT;

	/**
	 * The default value of the '{@link #getTDPhDelayed() <em>TD Ph Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDPhDelayed()
	 * @generated
	 * @ordered
	 */
	protected static final double TD_PH_DELAYED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTDPhDelayed() <em>TD Ph Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDPhDelayed()
	 * @generated
	 * @ordered
	 */
	protected double tDPhDelayed = TD_PH_DELAYED_EDEFAULT;

	/**
	 * The default value of the '{@link #getTDGrDelayed() <em>TD Gr Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDGrDelayed()
	 * @generated
	 * @ordered
	 */
	protected static final double TD_GR_DELAYED_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getTDGrDelayed() <em>TD Gr Delayed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTDGrDelayed()
	 * @generated
	 * @ordered
	 */
	protected double tDGrDelayed = TD_GR_DELAYED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RecloserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.RECLOSER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__MONITORED_OBJ, oldMonitoredObj, monitoredObj));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__MONITORED_TERM, oldMonitoredTerm, monitoredTerm));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__SWITCHED_OBJ, oldSwitchedObj, switchedObj));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__SWITCHED_TERM, oldSwitchedTerm, switchedTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNFast() {
		return nFast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNFast(int newNFast) {
		int oldNFast = nFast;
		nFast = newNFast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__NFAST, oldNFast, nFast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhaseFast() {
		return phaseFast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseFast(String newPhaseFast) {
		String oldPhaseFast = phaseFast;
		phaseFast = newPhaseFast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__PHASE_FAST, oldPhaseFast, phaseFast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhaseDelayed() {
		return phaseDelayed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseDelayed(String newPhaseDelayed) {
		String oldPhaseDelayed = phaseDelayed;
		phaseDelayed = newPhaseDelayed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__PHASE_DELAYED, oldPhaseDelayed, phaseDelayed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroundFast() {
		return groundFast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundFast(String newGroundFast) {
		String oldGroundFast = groundFast;
		groundFast = newGroundFast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__GROUND_FAST, oldGroundFast, groundFast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroundDelayed() {
		return groundDelayed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroundDelayed(String newGroundDelayed) {
		String oldGroundDelayed = groundDelayed;
		groundDelayed = newGroundDelayed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__GROUND_DELAYED, oldGroundDelayed, groundDelayed));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__PHASE_TRIP, oldPhaseTrip, phaseTrip));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__GROUND_TRIP, oldGroundTrip, groundTrip));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__PHASE_INST, oldPhaseInst, phaseInst));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__GROUND_INST, oldGroundInst, groundInst));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__RESET, oldReset, reset));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__SHOTS, oldShots, shots));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getRecloseIntervals() {
		if (recloseIntervals == null) {
			recloseIntervals = new EDataTypeUniqueEList<Double>(Double.class, this, ControlPackage.RECLOSER__RECLOSE_INTERVALS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__DELAY, oldDelay, delay));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTDPhFast() {
		return tDPhFast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDPhFast(double newTDPhFast) {
		double oldTDPhFast = tDPhFast;
		tDPhFast = newTDPhFast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__TD_PH_FAST, oldTDPhFast, tDPhFast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTDGrFast() {
		return tDGrFast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDGrFast(double newTDGrFast) {
		double oldTDGrFast = tDGrFast;
		tDGrFast = newTDGrFast;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__TD_GR_FAST, oldTDGrFast, tDGrFast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTDPhDelayed() {
		return tDPhDelayed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDPhDelayed(double newTDPhDelayed) {
		double oldTDPhDelayed = tDPhDelayed;
		tDPhDelayed = newTDPhDelayed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__TD_PH_DELAYED, oldTDPhDelayed, tDPhDelayed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTDGrDelayed() {
		return tDGrDelayed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDGrDelayed(double newTDGrDelayed) {
		double oldTDGrDelayed = tDGrDelayed;
		tDGrDelayed = newTDGrDelayed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.RECLOSER__TD_GR_DELAYED, oldTDGrDelayed, tDGrDelayed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.RECLOSER__MONITORED_OBJ:
				return getMonitoredObj();
			case ControlPackage.RECLOSER__MONITORED_TERM:
				return getMonitoredTerm();
			case ControlPackage.RECLOSER__SWITCHED_OBJ:
				return getSwitchedObj();
			case ControlPackage.RECLOSER__SWITCHED_TERM:
				return getSwitchedTerm();
			case ControlPackage.RECLOSER__NFAST:
				return getNFast();
			case ControlPackage.RECLOSER__PHASE_FAST:
				return getPhaseFast();
			case ControlPackage.RECLOSER__PHASE_DELAYED:
				return getPhaseDelayed();
			case ControlPackage.RECLOSER__GROUND_FAST:
				return getGroundFast();
			case ControlPackage.RECLOSER__GROUND_DELAYED:
				return getGroundDelayed();
			case ControlPackage.RECLOSER__PHASE_TRIP:
				return getPhaseTrip();
			case ControlPackage.RECLOSER__GROUND_TRIP:
				return getGroundTrip();
			case ControlPackage.RECLOSER__PHASE_INST:
				return getPhaseInst();
			case ControlPackage.RECLOSER__GROUND_INST:
				return getGroundInst();
			case ControlPackage.RECLOSER__RESET:
				return getReset();
			case ControlPackage.RECLOSER__SHOTS:
				return getShots();
			case ControlPackage.RECLOSER__RECLOSE_INTERVALS:
				return getRecloseIntervals();
			case ControlPackage.RECLOSER__DELAY:
				return getDelay();
			case ControlPackage.RECLOSER__ACTION:
				return getAction();
			case ControlPackage.RECLOSER__TD_PH_FAST:
				return getTDPhFast();
			case ControlPackage.RECLOSER__TD_GR_FAST:
				return getTDGrFast();
			case ControlPackage.RECLOSER__TD_PH_DELAYED:
				return getTDPhDelayed();
			case ControlPackage.RECLOSER__TD_GR_DELAYED:
				return getTDGrDelayed();
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
			case ControlPackage.RECLOSER__MONITORED_OBJ:
				setMonitoredObj((String)newValue);
				return;
			case ControlPackage.RECLOSER__MONITORED_TERM:
				setMonitoredTerm((Integer)newValue);
				return;
			case ControlPackage.RECLOSER__SWITCHED_OBJ:
				setSwitchedObj((String)newValue);
				return;
			case ControlPackage.RECLOSER__SWITCHED_TERM:
				setSwitchedTerm((Integer)newValue);
				return;
			case ControlPackage.RECLOSER__NFAST:
				setNFast((Integer)newValue);
				return;
			case ControlPackage.RECLOSER__PHASE_FAST:
				setPhaseFast((String)newValue);
				return;
			case ControlPackage.RECLOSER__PHASE_DELAYED:
				setPhaseDelayed((String)newValue);
				return;
			case ControlPackage.RECLOSER__GROUND_FAST:
				setGroundFast((String)newValue);
				return;
			case ControlPackage.RECLOSER__GROUND_DELAYED:
				setGroundDelayed((String)newValue);
				return;
			case ControlPackage.RECLOSER__PHASE_TRIP:
				setPhaseTrip((Double)newValue);
				return;
			case ControlPackage.RECLOSER__GROUND_TRIP:
				setGroundTrip((Double)newValue);
				return;
			case ControlPackage.RECLOSER__PHASE_INST:
				setPhaseInst((Double)newValue);
				return;
			case ControlPackage.RECLOSER__GROUND_INST:
				setGroundInst((Double)newValue);
				return;
			case ControlPackage.RECLOSER__RESET:
				setReset((Double)newValue);
				return;
			case ControlPackage.RECLOSER__SHOTS:
				setShots((Integer)newValue);
				return;
			case ControlPackage.RECLOSER__RECLOSE_INTERVALS:
				getRecloseIntervals().clear();
				getRecloseIntervals().addAll((Collection<? extends Double>)newValue);
				return;
			case ControlPackage.RECLOSER__DELAY:
				setDelay((Double)newValue);
				return;
			case ControlPackage.RECLOSER__ACTION:
				setAction((tripAction)newValue);
				return;
			case ControlPackage.RECLOSER__TD_PH_FAST:
				setTDPhFast((Double)newValue);
				return;
			case ControlPackage.RECLOSER__TD_GR_FAST:
				setTDGrFast((Double)newValue);
				return;
			case ControlPackage.RECLOSER__TD_PH_DELAYED:
				setTDPhDelayed((Double)newValue);
				return;
			case ControlPackage.RECLOSER__TD_GR_DELAYED:
				setTDGrDelayed((Double)newValue);
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
			case ControlPackage.RECLOSER__MONITORED_OBJ:
				setMonitoredObj(MONITORED_OBJ_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__MONITORED_TERM:
				setMonitoredTerm(MONITORED_TERM_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__SWITCHED_OBJ:
				setSwitchedObj(SWITCHED_OBJ_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__SWITCHED_TERM:
				setSwitchedTerm(SWITCHED_TERM_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__NFAST:
				setNFast(NFAST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__PHASE_FAST:
				setPhaseFast(PHASE_FAST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__PHASE_DELAYED:
				setPhaseDelayed(PHASE_DELAYED_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__GROUND_FAST:
				setGroundFast(GROUND_FAST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__GROUND_DELAYED:
				setGroundDelayed(GROUND_DELAYED_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__PHASE_TRIP:
				setPhaseTrip(PHASE_TRIP_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__GROUND_TRIP:
				setGroundTrip(GROUND_TRIP_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__PHASE_INST:
				setPhaseInst(PHASE_INST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__GROUND_INST:
				setGroundInst(GROUND_INST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__RESET:
				setReset(RESET_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__SHOTS:
				setShots(SHOTS_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__RECLOSE_INTERVALS:
				getRecloseIntervals().clear();
				return;
			case ControlPackage.RECLOSER__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__TD_PH_FAST:
				setTDPhFast(TD_PH_FAST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__TD_GR_FAST:
				setTDGrFast(TD_GR_FAST_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__TD_PH_DELAYED:
				setTDPhDelayed(TD_PH_DELAYED_EDEFAULT);
				return;
			case ControlPackage.RECLOSER__TD_GR_DELAYED:
				setTDGrDelayed(TD_GR_DELAYED_EDEFAULT);
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
			case ControlPackage.RECLOSER__MONITORED_OBJ:
				return MONITORED_OBJ_EDEFAULT == null ? monitoredObj != null : !MONITORED_OBJ_EDEFAULT.equals(monitoredObj);
			case ControlPackage.RECLOSER__MONITORED_TERM:
				return monitoredTerm != MONITORED_TERM_EDEFAULT;
			case ControlPackage.RECLOSER__SWITCHED_OBJ:
				return SWITCHED_OBJ_EDEFAULT == null ? switchedObj != null : !SWITCHED_OBJ_EDEFAULT.equals(switchedObj);
			case ControlPackage.RECLOSER__SWITCHED_TERM:
				return switchedTerm != SWITCHED_TERM_EDEFAULT;
			case ControlPackage.RECLOSER__NFAST:
				return nFast != NFAST_EDEFAULT;
			case ControlPackage.RECLOSER__PHASE_FAST:
				return PHASE_FAST_EDEFAULT == null ? phaseFast != null : !PHASE_FAST_EDEFAULT.equals(phaseFast);
			case ControlPackage.RECLOSER__PHASE_DELAYED:
				return PHASE_DELAYED_EDEFAULT == null ? phaseDelayed != null : !PHASE_DELAYED_EDEFAULT.equals(phaseDelayed);
			case ControlPackage.RECLOSER__GROUND_FAST:
				return GROUND_FAST_EDEFAULT == null ? groundFast != null : !GROUND_FAST_EDEFAULT.equals(groundFast);
			case ControlPackage.RECLOSER__GROUND_DELAYED:
				return GROUND_DELAYED_EDEFAULT == null ? groundDelayed != null : !GROUND_DELAYED_EDEFAULT.equals(groundDelayed);
			case ControlPackage.RECLOSER__PHASE_TRIP:
				return phaseTrip != PHASE_TRIP_EDEFAULT;
			case ControlPackage.RECLOSER__GROUND_TRIP:
				return groundTrip != GROUND_TRIP_EDEFAULT;
			case ControlPackage.RECLOSER__PHASE_INST:
				return phaseInst != PHASE_INST_EDEFAULT;
			case ControlPackage.RECLOSER__GROUND_INST:
				return groundInst != GROUND_INST_EDEFAULT;
			case ControlPackage.RECLOSER__RESET:
				return reset != RESET_EDEFAULT;
			case ControlPackage.RECLOSER__SHOTS:
				return shots != SHOTS_EDEFAULT;
			case ControlPackage.RECLOSER__RECLOSE_INTERVALS:
				return recloseIntervals != null && !recloseIntervals.isEmpty();
			case ControlPackage.RECLOSER__DELAY:
				return delay != DELAY_EDEFAULT;
			case ControlPackage.RECLOSER__ACTION:
				return action != ACTION_EDEFAULT;
			case ControlPackage.RECLOSER__TD_PH_FAST:
				return tDPhFast != TD_PH_FAST_EDEFAULT;
			case ControlPackage.RECLOSER__TD_GR_FAST:
				return tDGrFast != TD_GR_FAST_EDEFAULT;
			case ControlPackage.RECLOSER__TD_PH_DELAYED:
				return tDPhDelayed != TD_PH_DELAYED_EDEFAULT;
			case ControlPackage.RECLOSER__TD_GR_DELAYED:
				return tDGrDelayed != TD_GR_DELAYED_EDEFAULT;
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
		result.append(", nFast: ");
		result.append(nFast);
		result.append(", phaseFast: ");
		result.append(phaseFast);
		result.append(", phaseDelayed: ");
		result.append(phaseDelayed);
		result.append(", groundFast: ");
		result.append(groundFast);
		result.append(", groundDelayed: ");
		result.append(groundDelayed);
		result.append(", phaseTrip: ");
		result.append(phaseTrip);
		result.append(", groundTrip: ");
		result.append(groundTrip);
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
		result.append(", action: ");
		result.append(action);
		result.append(", tDPhFast: ");
		result.append(tDPhFast);
		result.append(", tDGrFast: ");
		result.append(tDGrFast);
		result.append(", tDPhDelayed: ");
		result.append(tDPhDelayed);
		result.append(", tDGrDelayed: ");
		result.append(tDGrDelayed);
		result.append(')');
		return result.toString();
	}

} //RecloserImpl
