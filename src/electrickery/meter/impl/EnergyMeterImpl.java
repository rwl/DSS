/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.meter.impl;

import electrickery.meter.EnergyMeter;
import electrickery.meter.MeterPackage;
import electrickery.meter.meterAction;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Energy Meter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getAction <em>Action</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getOption <em>Option</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getKVANorm <em>KVA Norm</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getKVAEmerg <em>KVA Emerg</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getPeakCurrent <em>Peak Current</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getZoneList <em>Zone List</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isLocalOnly <em>Local Only</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#getMask <em>Mask</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isLosses <em>Losses</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isLineLosses <em>Line Losses</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isXfmrLosses <em>Xfmr Losses</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isSeqLosses <em>Seq Losses</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isVBaseLosses <em>VBase Losses</em>}</li>
 *   <li>{@link electrickery.meter.impl.EnergyMeterImpl#isOverloadReport <em>Overload Report</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnergyMeterImpl extends MeterElementImpl implements EnergyMeter {
	/**
	 * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected String element = ELEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected static final int TERMINAL_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected int terminal = TERMINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final meterAction ACTION_EDEFAULT = meterAction.CLEAR;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected meterAction action = ACTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOption() <em>Option</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOption()
	 * @generated
	 * @ordered
	 */
	protected EList<String> option;

	/**
	 * The default value of the '{@link #getKVANorm() <em>KVA Norm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVANorm()
	 * @generated
	 * @ordered
	 */
	protected static final double KVA_NORM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVANorm() <em>KVA Norm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVANorm()
	 * @generated
	 * @ordered
	 */
	protected double kVANorm = KVA_NORM_EDEFAULT;

	/**
	 * The default value of the '{@link #getKVAEmerg() <em>KVA Emerg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVAEmerg()
	 * @generated
	 * @ordered
	 */
	protected static final double KVA_EMERG_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getKVAEmerg() <em>KVA Emerg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKVAEmerg()
	 * @generated
	 * @ordered
	 */
	protected double kVAEmerg = KVA_EMERG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPeakCurrent() <em>Peak Current</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakCurrent()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> peakCurrent;

	/**
	 * The cached value of the '{@link #getZoneList() <em>Zone List</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoneList()
	 * @generated
	 * @ordered
	 */
	protected EList<String> zoneList;

	/**
	 * The default value of the '{@link #isLocalOnly() <em>Local Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocalOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCAL_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLocalOnly() <em>Local Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocalOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean localOnly = LOCAL_ONLY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMask() <em>Mask</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMask()
	 * @generated
	 * @ordered
	 */
	protected EList<Double> mask;

	/**
	 * The default value of the '{@link #isLosses() <em>Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLosses()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOSSES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLosses() <em>Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLosses()
	 * @generated
	 * @ordered
	 */
	protected boolean losses = LOSSES_EDEFAULT;

	/**
	 * The default value of the '{@link #isLineLosses() <em>Line Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLineLosses()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LINE_LOSSES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLineLosses() <em>Line Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLineLosses()
	 * @generated
	 * @ordered
	 */
	protected boolean lineLosses = LINE_LOSSES_EDEFAULT;

	/**
	 * The default value of the '{@link #isXfmrLosses() <em>Xfmr Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isXfmrLosses()
	 * @generated
	 * @ordered
	 */
	protected static final boolean XFMR_LOSSES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isXfmrLosses() <em>Xfmr Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isXfmrLosses()
	 * @generated
	 * @ordered
	 */
	protected boolean xfmrLosses = XFMR_LOSSES_EDEFAULT;

	/**
	 * The default value of the '{@link #isSeqLosses() <em>Seq Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSeqLosses()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEQ_LOSSES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSeqLosses() <em>Seq Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSeqLosses()
	 * @generated
	 * @ordered
	 */
	protected boolean seqLosses = SEQ_LOSSES_EDEFAULT;

	/**
	 * The default value of the '{@link #isVBaseLosses() <em>VBase Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVBaseLosses()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VBASE_LOSSES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isVBaseLosses() <em>VBase Losses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVBaseLosses()
	 * @generated
	 * @ordered
	 */
	protected boolean vBaseLosses = VBASE_LOSSES_EDEFAULT;

	/**
	 * The default value of the '{@link #isOverloadReport() <em>Overload Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverloadReport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERLOAD_REPORT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOverloadReport() <em>Overload Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverloadReport()
	 * @generated
	 * @ordered
	 */
	protected boolean overloadReport = OVERLOAD_REPORT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnergyMeterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MeterPackage.Literals.ENERGY_METER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(String newElement) {
		String oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTerminal() {
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminal(int newTerminal) {
		int oldTerminal = terminal;
		terminal = newTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__TERMINAL, oldTerminal, terminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public meterAction getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(meterAction newAction) {
		meterAction oldAction = action;
		action = newAction == null ? ACTION_EDEFAULT : newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOption() {
		if (option == null) {
			option = new EDataTypeUniqueEList<String>(String.class, this, MeterPackage.ENERGY_METER__OPTION);
		}
		return option;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVANorm() {
		return kVANorm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVANorm(double newKVANorm) {
		double oldKVANorm = kVANorm;
		kVANorm = newKVANorm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__KVA_NORM, oldKVANorm, kVANorm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getKVAEmerg() {
		return kVAEmerg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKVAEmerg(double newKVAEmerg) {
		double oldKVAEmerg = kVAEmerg;
		kVAEmerg = newKVAEmerg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__KVA_EMERG, oldKVAEmerg, kVAEmerg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getPeakCurrent() {
		if (peakCurrent == null) {
			peakCurrent = new EDataTypeUniqueEList<Double>(Double.class, this, MeterPackage.ENERGY_METER__PEAK_CURRENT);
		}
		return peakCurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getZoneList() {
		if (zoneList == null) {
			zoneList = new EDataTypeUniqueEList<String>(String.class, this, MeterPackage.ENERGY_METER__ZONE_LIST);
		}
		return zoneList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocalOnly() {
		return localOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalOnly(boolean newLocalOnly) {
		boolean oldLocalOnly = localOnly;
		localOnly = newLocalOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__LOCAL_ONLY, oldLocalOnly, localOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Double> getMask() {
		if (mask == null) {
			mask = new EDataTypeUniqueEList<Double>(Double.class, this, MeterPackage.ENERGY_METER__MASK);
		}
		return mask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLosses() {
		return losses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLosses(boolean newLosses) {
		boolean oldLosses = losses;
		losses = newLosses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__LOSSES, oldLosses, losses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLineLosses() {
		return lineLosses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineLosses(boolean newLineLosses) {
		boolean oldLineLosses = lineLosses;
		lineLosses = newLineLosses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__LINE_LOSSES, oldLineLosses, lineLosses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isXfmrLosses() {
		return xfmrLosses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXfmrLosses(boolean newXfmrLosses) {
		boolean oldXfmrLosses = xfmrLosses;
		xfmrLosses = newXfmrLosses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__XFMR_LOSSES, oldXfmrLosses, xfmrLosses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSeqLosses() {
		return seqLosses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeqLosses(boolean newSeqLosses) {
		boolean oldSeqLosses = seqLosses;
		seqLosses = newSeqLosses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__SEQ_LOSSES, oldSeqLosses, seqLosses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVBaseLosses() {
		return vBaseLosses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVBaseLosses(boolean newVBaseLosses) {
		boolean oldVBaseLosses = vBaseLosses;
		vBaseLosses = newVBaseLosses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__VBASE_LOSSES, oldVBaseLosses, vBaseLosses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverloadReport() {
		return overloadReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverloadReport(boolean newOverloadReport) {
		boolean oldOverloadReport = overloadReport;
		overloadReport = newOverloadReport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeterPackage.ENERGY_METER__OVERLOAD_REPORT, oldOverloadReport, overloadReport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MeterPackage.ENERGY_METER__ELEMENT:
				return getElement();
			case MeterPackage.ENERGY_METER__TERMINAL:
				return getTerminal();
			case MeterPackage.ENERGY_METER__ACTION:
				return getAction();
			case MeterPackage.ENERGY_METER__OPTION:
				return getOption();
			case MeterPackage.ENERGY_METER__KVA_NORM:
				return getKVANorm();
			case MeterPackage.ENERGY_METER__KVA_EMERG:
				return getKVAEmerg();
			case MeterPackage.ENERGY_METER__PEAK_CURRENT:
				return getPeakCurrent();
			case MeterPackage.ENERGY_METER__ZONE_LIST:
				return getZoneList();
			case MeterPackage.ENERGY_METER__LOCAL_ONLY:
				return isLocalOnly();
			case MeterPackage.ENERGY_METER__MASK:
				return getMask();
			case MeterPackage.ENERGY_METER__LOSSES:
				return isLosses();
			case MeterPackage.ENERGY_METER__LINE_LOSSES:
				return isLineLosses();
			case MeterPackage.ENERGY_METER__XFMR_LOSSES:
				return isXfmrLosses();
			case MeterPackage.ENERGY_METER__SEQ_LOSSES:
				return isSeqLosses();
			case MeterPackage.ENERGY_METER__VBASE_LOSSES:
				return isVBaseLosses();
			case MeterPackage.ENERGY_METER__OVERLOAD_REPORT:
				return isOverloadReport();
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
			case MeterPackage.ENERGY_METER__ELEMENT:
				setElement((String)newValue);
				return;
			case MeterPackage.ENERGY_METER__TERMINAL:
				setTerminal((Integer)newValue);
				return;
			case MeterPackage.ENERGY_METER__ACTION:
				setAction((meterAction)newValue);
				return;
			case MeterPackage.ENERGY_METER__OPTION:
				getOption().clear();
				getOption().addAll((Collection<? extends String>)newValue);
				return;
			case MeterPackage.ENERGY_METER__KVA_NORM:
				setKVANorm((Double)newValue);
				return;
			case MeterPackage.ENERGY_METER__KVA_EMERG:
				setKVAEmerg((Double)newValue);
				return;
			case MeterPackage.ENERGY_METER__PEAK_CURRENT:
				getPeakCurrent().clear();
				getPeakCurrent().addAll((Collection<? extends Double>)newValue);
				return;
			case MeterPackage.ENERGY_METER__ZONE_LIST:
				getZoneList().clear();
				getZoneList().addAll((Collection<? extends String>)newValue);
				return;
			case MeterPackage.ENERGY_METER__LOCAL_ONLY:
				setLocalOnly((Boolean)newValue);
				return;
			case MeterPackage.ENERGY_METER__MASK:
				getMask().clear();
				getMask().addAll((Collection<? extends Double>)newValue);
				return;
			case MeterPackage.ENERGY_METER__LOSSES:
				setLosses((Boolean)newValue);
				return;
			case MeterPackage.ENERGY_METER__LINE_LOSSES:
				setLineLosses((Boolean)newValue);
				return;
			case MeterPackage.ENERGY_METER__XFMR_LOSSES:
				setXfmrLosses((Boolean)newValue);
				return;
			case MeterPackage.ENERGY_METER__SEQ_LOSSES:
				setSeqLosses((Boolean)newValue);
				return;
			case MeterPackage.ENERGY_METER__VBASE_LOSSES:
				setVBaseLosses((Boolean)newValue);
				return;
			case MeterPackage.ENERGY_METER__OVERLOAD_REPORT:
				setOverloadReport((Boolean)newValue);
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
			case MeterPackage.ENERGY_METER__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__TERMINAL:
				setTerminal(TERMINAL_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__OPTION:
				getOption().clear();
				return;
			case MeterPackage.ENERGY_METER__KVA_NORM:
				setKVANorm(KVA_NORM_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__KVA_EMERG:
				setKVAEmerg(KVA_EMERG_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__PEAK_CURRENT:
				getPeakCurrent().clear();
				return;
			case MeterPackage.ENERGY_METER__ZONE_LIST:
				getZoneList().clear();
				return;
			case MeterPackage.ENERGY_METER__LOCAL_ONLY:
				setLocalOnly(LOCAL_ONLY_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__MASK:
				getMask().clear();
				return;
			case MeterPackage.ENERGY_METER__LOSSES:
				setLosses(LOSSES_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__LINE_LOSSES:
				setLineLosses(LINE_LOSSES_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__XFMR_LOSSES:
				setXfmrLosses(XFMR_LOSSES_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__SEQ_LOSSES:
				setSeqLosses(SEQ_LOSSES_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__VBASE_LOSSES:
				setVBaseLosses(VBASE_LOSSES_EDEFAULT);
				return;
			case MeterPackage.ENERGY_METER__OVERLOAD_REPORT:
				setOverloadReport(OVERLOAD_REPORT_EDEFAULT);
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
			case MeterPackage.ENERGY_METER__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case MeterPackage.ENERGY_METER__TERMINAL:
				return terminal != TERMINAL_EDEFAULT;
			case MeterPackage.ENERGY_METER__ACTION:
				return action != ACTION_EDEFAULT;
			case MeterPackage.ENERGY_METER__OPTION:
				return option != null && !option.isEmpty();
			case MeterPackage.ENERGY_METER__KVA_NORM:
				return kVANorm != KVA_NORM_EDEFAULT;
			case MeterPackage.ENERGY_METER__KVA_EMERG:
				return kVAEmerg != KVA_EMERG_EDEFAULT;
			case MeterPackage.ENERGY_METER__PEAK_CURRENT:
				return peakCurrent != null && !peakCurrent.isEmpty();
			case MeterPackage.ENERGY_METER__ZONE_LIST:
				return zoneList != null && !zoneList.isEmpty();
			case MeterPackage.ENERGY_METER__LOCAL_ONLY:
				return localOnly != LOCAL_ONLY_EDEFAULT;
			case MeterPackage.ENERGY_METER__MASK:
				return mask != null && !mask.isEmpty();
			case MeterPackage.ENERGY_METER__LOSSES:
				return losses != LOSSES_EDEFAULT;
			case MeterPackage.ENERGY_METER__LINE_LOSSES:
				return lineLosses != LINE_LOSSES_EDEFAULT;
			case MeterPackage.ENERGY_METER__XFMR_LOSSES:
				return xfmrLosses != XFMR_LOSSES_EDEFAULT;
			case MeterPackage.ENERGY_METER__SEQ_LOSSES:
				return seqLosses != SEQ_LOSSES_EDEFAULT;
			case MeterPackage.ENERGY_METER__VBASE_LOSSES:
				return vBaseLosses != VBASE_LOSSES_EDEFAULT;
			case MeterPackage.ENERGY_METER__OVERLOAD_REPORT:
				return overloadReport != OVERLOAD_REPORT_EDEFAULT;
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
		result.append(" (element: ");
		result.append(element);
		result.append(", terminal: ");
		result.append(terminal);
		result.append(", action: ");
		result.append(action);
		result.append(", option: ");
		result.append(option);
		result.append(", kVANorm: ");
		result.append(kVANorm);
		result.append(", kVAEmerg: ");
		result.append(kVAEmerg);
		result.append(", peakCurrent: ");
		result.append(peakCurrent);
		result.append(", zoneList: ");
		result.append(zoneList);
		result.append(", localOnly: ");
		result.append(localOnly);
		result.append(", mask: ");
		result.append(mask);
		result.append(", losses: ");
		result.append(losses);
		result.append(", lineLosses: ");
		result.append(lineLosses);
		result.append(", xfmrLosses: ");
		result.append(xfmrLosses);
		result.append(", seqLosses: ");
		result.append(seqLosses);
		result.append(", vBaseLosses: ");
		result.append(vBaseLosses);
		result.append(", overloadReport: ");
		result.append(overloadReport);
		result.append(')');
		return result.toString();
	}

} //EnergyMeterImpl
