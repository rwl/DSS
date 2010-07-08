/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive.impl;

import electrickery.executive.ExecOptions;
import electrickery.executive.ExecutivePackage;
import electrickery.executive.algorithmType;
import electrickery.executive.autoAddType;
import electrickery.executive.circuitModelType;
import electrickery.executive.controlModeType;
import electrickery.executive.loadModelType;
import electrickery.executive.randomType;
import electrickery.executive.reductionStrategy;
import electrickery.executive.solutionMode;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exec Options</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getType <em>Type</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getHour <em>Hour</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getSec <em>Sec</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getYear <em>Year</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getStepSize <em>Step Size</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getRandom <em>Random</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getTime <em>Time</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getEditor <em>Editor</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getTolerance <em>Tolerance</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getMaxIter <em>Max Iter</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getLoadModel <em>Load Model</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getLoadMult <em>Load Mult</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getNormVMinPU <em>Norm VMin PU</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getNormVMaxPU <em>Norm VMax PU</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getEmergVMinPU <em>Emerg VMin PU</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getEmergVMaxPU <em>Emerg VMax PU</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getPctMean <em>Pct Mean</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getPctStdDev <em>Pct Std Dev</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getLDCurve <em>LD Curve</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getPctGrowth <em>Pct Growth</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getGenKW <em>Gen KW</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getGenPF <em>Gen PF</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getCapKVAr <em>Cap KV Ar</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getAddType <em>Add Type</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isAllowDuplicates <em>Allow Duplicates</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isZoneLock <em>Zone Lock</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getUEWeight <em>UE Weight</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getLossWeight <em>Loss Weight</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getUERegs <em>UE Regs</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getLossRegs <em>Loss Regs</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getVoltageBases <em>Voltage Bases</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isTrapezoidal <em>Trapezoidal</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getAutoBusList <em>Auto Bus List</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getControlMode <em>Control Mode</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isTraceMode <em>Trace Mode</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getGenMult <em>Gen Mult</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getDefaultDaily <em>Default Daily</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getDefaultYearly <em>Default Yearly</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getAllocationFactor <em>Allocation Factor</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getCktModel <em>Ckt Model</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getPriceSignal <em>Price Signal</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getPriceCurve <em>Price Curve</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getBaseFrequency <em>Base Frequency</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getHarmonics <em>Harmonics</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getMaxController <em>Max Controller</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getBus <em>Bus</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getKeepList <em>Keep List</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getReduceOption <em>Reduce Option</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isDemandInterval <em>Demand Interval</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getPctNormal <em>Pct Normal</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isDIVerbose <em>DI Verbose</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getCaseName <em>Case Name</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getMarkerCode <em>Marker Code</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#getNodeWidth <em>Node Width</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isLog <em>Log</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isRecorder <em>Recorder</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isOverloadReport <em>Overload Report</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecOptionsImpl#isVoltageExceptionReport <em>Voltage Exception Report</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecOptionsImpl extends EObjectImpl implements ExecOptions {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getHour() <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHour()
	 * @generated
	 * @ordered
	 */
	protected static final int HOUR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHour() <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHour()
	 * @generated
	 * @ordered
	 */
	protected int hour = HOUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getSec() <em>Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSec()
	 * @generated
	 * @ordered
	 */
	protected static final long SEC_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSec() <em>Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSec()
	 * @generated
	 * @ordered
	 */
	protected long sec = SEC_EDEFAULT;

	/**
	 * The default value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected static final int YEAR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected int year = YEAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected static final float FREQUENCY_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected float frequency = FREQUENCY_EDEFAULT;

	/**
	 * The default value of the '{@link #getStepSize() <em>Step Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepSize()
	 * @generated
	 * @ordered
	 */
	protected static final float STEP_SIZE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getStepSize() <em>Step Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepSize()
	 * @generated
	 * @ordered
	 */
	protected float stepSize = STEP_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final solutionMode MODE_EDEFAULT = solutionMode.SNAPSHOT;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected solutionMode mode = MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRandom() <em>Random</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRandom()
	 * @generated
	 * @ordered
	 */
	protected static final randomType RANDOM_EDEFAULT = randomType.UNIFORM;

	/**
	 * The cached value of the '{@link #getRandom() <em>Random</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRandom()
	 * @generated
	 * @ordered
	 */
	protected randomType random = RANDOM_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 2;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> time;

	/**
	 * The default value of the '{@link #getCircuit() <em>Circuit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCircuit()
	 * @generated
	 * @ordered
	 */
	protected static final String CIRCUIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCircuit() <em>Circuit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCircuit()
	 * @generated
	 * @ordered
	 */
	protected String circuit = CIRCUIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditor() <em>Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditor()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditor() <em>Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditor()
	 * @generated
	 * @ordered
	 */
	protected String editor = EDITOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getTolerance() <em>Tolerance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTolerance()
	 * @generated
	 * @ordered
	 */
	protected static final float TOLERANCE_EDEFAULT = 1.0E-4F;

	/**
	 * The cached value of the '{@link #getTolerance() <em>Tolerance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTolerance()
	 * @generated
	 * @ordered
	 */
	protected float tolerance = TOLERANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxIter() <em>Max Iter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIter()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_ITER_EDEFAULT = 15;

	/**
	 * The cached value of the '{@link #getMaxIter() <em>Max Iter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIter()
	 * @generated
	 * @ordered
	 */
	protected int maxIter = MAX_ITER_EDEFAULT;

	/**
	 * The default value of the '{@link #getLoadModel() <em>Load Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadModel()
	 * @generated
	 * @ordered
	 */
	protected static final loadModelType LOAD_MODEL_EDEFAULT = loadModelType.POWERFLOW;

	/**
	 * The cached value of the '{@link #getLoadModel() <em>Load Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadModel()
	 * @generated
	 * @ordered
	 */
	protected loadModelType loadModel = LOAD_MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getLoadMult() <em>Load Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadMult()
	 * @generated
	 * @ordered
	 */
	protected static final float LOAD_MULT_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getLoadMult() <em>Load Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadMult()
	 * @generated
	 * @ordered
	 */
	protected float loadMult = LOAD_MULT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNormVMinPU() <em>Norm VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormVMinPU()
	 * @generated
	 * @ordered
	 */
	protected static final float NORM_VMIN_PU_EDEFAULT = 0.95F;

	/**
	 * The cached value of the '{@link #getNormVMinPU() <em>Norm VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormVMinPU()
	 * @generated
	 * @ordered
	 */
	protected float normVMinPU = NORM_VMIN_PU_EDEFAULT;

	/**
	 * The default value of the '{@link #getNormVMaxPU() <em>Norm VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormVMaxPU()
	 * @generated
	 * @ordered
	 */
	protected static final float NORM_VMAX_PU_EDEFAULT = 1.05F;

	/**
	 * The cached value of the '{@link #getNormVMaxPU() <em>Norm VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormVMaxPU()
	 * @generated
	 * @ordered
	 */
	protected float normVMaxPU = NORM_VMAX_PU_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmergVMinPU() <em>Emerg VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergVMinPU()
	 * @generated
	 * @ordered
	 */
	protected static final float EMERG_VMIN_PU_EDEFAULT = 0.9F;

	/**
	 * The cached value of the '{@link #getEmergVMinPU() <em>Emerg VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergVMinPU()
	 * @generated
	 * @ordered
	 */
	protected float emergVMinPU = EMERG_VMIN_PU_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmergVMaxPU() <em>Emerg VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergVMaxPU()
	 * @generated
	 * @ordered
	 */
	protected static final float EMERG_VMAX_PU_EDEFAULT = 1.08F;

	/**
	 * The cached value of the '{@link #getEmergVMaxPU() <em>Emerg VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmergVMaxPU()
	 * @generated
	 * @ordered
	 */
	protected float emergVMaxPU = EMERG_VMAX_PU_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctMean() <em>Pct Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctMean()
	 * @generated
	 * @ordered
	 */
	protected static final float PCT_MEAN_EDEFAULT = 65.0F;

	/**
	 * The cached value of the '{@link #getPctMean() <em>Pct Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctMean()
	 * @generated
	 * @ordered
	 */
	protected float pctMean = PCT_MEAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctStdDev() <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctStdDev()
	 * @generated
	 * @ordered
	 */
	protected static final float PCT_STD_DEV_EDEFAULT = 9.0F;

	/**
	 * The cached value of the '{@link #getPctStdDev() <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctStdDev()
	 * @generated
	 * @ordered
	 */
	protected float pctStdDev = PCT_STD_DEV_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLDCurve() <em>LD Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLDCurve()
	 * @generated
	 * @ordered
	 */
	protected EClass ldCurve;

	/**
	 * The default value of the '{@link #getPctGrowth() <em>Pct Growth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctGrowth()
	 * @generated
	 * @ordered
	 */
	protected static final float PCT_GROWTH_EDEFAULT = 2.5F;

	/**
	 * The cached value of the '{@link #getPctGrowth() <em>Pct Growth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctGrowth()
	 * @generated
	 * @ordered
	 */
	protected float pctGrowth = PCT_GROWTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenKW() <em>Gen KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenKW()
	 * @generated
	 * @ordered
	 */
	protected static final float GEN_KW_EDEFAULT = 1000.0F;

	/**
	 * The cached value of the '{@link #getGenKW() <em>Gen KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenKW()
	 * @generated
	 * @ordered
	 */
	protected float genKW = GEN_KW_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenPF() <em>Gen PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenPF()
	 * @generated
	 * @ordered
	 */
	protected static final float GEN_PF_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getGenPF() <em>Gen PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenPF()
	 * @generated
	 * @ordered
	 */
	protected float genPF = GEN_PF_EDEFAULT;

	/**
	 * The default value of the '{@link #getCapKVAr() <em>Cap KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapKVAr()
	 * @generated
	 * @ordered
	 */
	protected static final float CAP_KV_AR_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getCapKVAr() <em>Cap KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapKVAr()
	 * @generated
	 * @ordered
	 */
	protected float capKVAr = CAP_KV_AR_EDEFAULT;

	/**
	 * The default value of the '{@link #getAddType() <em>Add Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddType()
	 * @generated
	 * @ordered
	 */
	protected static final autoAddType ADD_TYPE_EDEFAULT = autoAddType.GENERATOR;

	/**
	 * The cached value of the '{@link #getAddType() <em>Add Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddType()
	 * @generated
	 * @ordered
	 */
	protected autoAddType addType = ADD_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllowDuplicates() <em>Allow Duplicates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowDuplicates()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_DUPLICATES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllowDuplicates() <em>Allow Duplicates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowDuplicates()
	 * @generated
	 * @ordered
	 */
	protected boolean allowDuplicates = ALLOW_DUPLICATES_EDEFAULT;

	/**
	 * The default value of the '{@link #isZoneLock() <em>Zone Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isZoneLock()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ZONE_LOCK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isZoneLock() <em>Zone Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isZoneLock()
	 * @generated
	 * @ordered
	 */
	protected boolean zoneLock = ZONE_LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #getUEWeight() <em>UE Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUEWeight()
	 * @generated
	 * @ordered
	 */
	protected static final float UE_WEIGHT_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getUEWeight() <em>UE Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUEWeight()
	 * @generated
	 * @ordered
	 */
	protected float ueWeight = UE_WEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getLossWeight() <em>Loss Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLossWeight()
	 * @generated
	 * @ordered
	 */
	protected static final float LOSS_WEIGHT_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getLossWeight() <em>Loss Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLossWeight()
	 * @generated
	 * @ordered
	 */
	protected float lossWeight = LOSS_WEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUERegs() <em>UE Regs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUERegs()
	 * @generated
	 * @ordered
	 */
	protected static final int UE_REGS_EDEFAULT = 11;

	/**
	 * The cached value of the '{@link #getUERegs() <em>UE Regs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUERegs()
	 * @generated
	 * @ordered
	 */
	protected int ueRegs = UE_REGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLossRegs() <em>Loss Regs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLossRegs()
	 * @generated
	 * @ordered
	 */
	protected static final int LOSS_REGS_EDEFAULT = 13;

	/**
	 * The cached value of the '{@link #getLossRegs() <em>Loss Regs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLossRegs()
	 * @generated
	 * @ordered
	 */
	protected int lossRegs = LOSS_REGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVoltageBases() <em>Voltage Bases</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVoltageBases()
	 * @generated
	 * @ordered
	 */
	protected EList<Float> voltageBases;

	/**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected static final algorithmType ALGORITHM_EDEFAULT = algorithmType.NORMAL;

	/**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected algorithmType algorithm = ALGORITHM_EDEFAULT;

	/**
	 * The default value of the '{@link #isTrapezoidal() <em>Trapezoidal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTrapezoidal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRAPEZOIDAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTrapezoidal() <em>Trapezoidal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTrapezoidal()
	 * @generated
	 * @ordered
	 */
	protected boolean trapezoidal = TRAPEZOIDAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAutoBusList() <em>Auto Bus List</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoBusList()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> autoBusList;

	/**
	 * The default value of the '{@link #getControlMode() <em>Control Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlMode()
	 * @generated
	 * @ordered
	 */
	protected static final controlModeType CONTROL_MODE_EDEFAULT = controlModeType.STATIC;

	/**
	 * The cached value of the '{@link #getControlMode() <em>Control Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlMode()
	 * @generated
	 * @ordered
	 */
	protected controlModeType controlMode = CONTROL_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #isTraceMode() <em>Trace Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTraceMode()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRACE_MODE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTraceMode() <em>Trace Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTraceMode()
	 * @generated
	 * @ordered
	 */
	protected boolean traceMode = TRACE_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenMult() <em>Gen Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenMult()
	 * @generated
	 * @ordered
	 */
	protected static final float GEN_MULT_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getGenMult() <em>Gen Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenMult()
	 * @generated
	 * @ordered
	 */
	protected float genMult = GEN_MULT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefaultDaily() <em>Default Daily</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDaily()
	 * @generated
	 * @ordered
	 */
	protected EClass defaultDaily;

	/**
	 * The cached value of the '{@link #getDefaultYearly() <em>Default Yearly</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultYearly()
	 * @generated
	 * @ordered
	 */
	protected EClass defaultYearly;

	/**
	 * The default value of the '{@link #getAllocationFactor() <em>Allocation Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocationFactor()
	 * @generated
	 * @ordered
	 */
	protected static final float ALLOCATION_FACTOR_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getAllocationFactor() <em>Allocation Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocationFactor()
	 * @generated
	 * @ordered
	 */
	protected float allocationFactor = ALLOCATION_FACTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCktModel() <em>Ckt Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCktModel()
	 * @generated
	 * @ordered
	 */
	protected static final circuitModelType CKT_MODEL_EDEFAULT = circuitModelType.MULTIPHASE;

	/**
	 * The cached value of the '{@link #getCktModel() <em>Ckt Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCktModel()
	 * @generated
	 * @ordered
	 */
	protected circuitModelType cktModel = CKT_MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriceSignal() <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriceSignal()
	 * @generated
	 * @ordered
	 */
	protected static final float PRICE_SIGNAL_EDEFAULT = 25.0F;

	/**
	 * The cached value of the '{@link #getPriceSignal() <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriceSignal()
	 * @generated
	 * @ordered
	 */
	protected float priceSignal = PRICE_SIGNAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPriceCurve() <em>Price Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriceCurve()
	 * @generated
	 * @ordered
	 */
	protected EClass priceCurve;

	/**
	 * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminal()
	 * @generated
	 * @ordered
	 */
	protected EClass terminal;

	/**
	 * The default value of the '{@link #getBaseFrequency() <em>Base Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseFrequency()
	 * @generated
	 * @ordered
	 */
	protected static final float BASE_FREQUENCY_EDEFAULT = 60.0F;

	/**
	 * The cached value of the '{@link #getBaseFrequency() <em>Base Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseFrequency()
	 * @generated
	 * @ordered
	 */
	protected float baseFrequency = BASE_FREQUENCY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHarmonics() <em>Harmonics</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHarmonics()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> harmonics;

	/**
	 * The default value of the '{@link #getMaxController() <em>Max Controller</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxController()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_CONTROLLER_EDEFAULT = 10;

	/**
	 * The cached value of the '{@link #getMaxController() <em>Max Controller</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxController()
	 * @generated
	 * @ordered
	 */
	protected int maxController = MAX_CONTROLLER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBus() <em>Bus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBus()
	 * @generated
	 * @ordered
	 */
	protected EClass bus;

	/**
	 * The default value of the '{@link #getDataPath() <em>Data Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPath()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataPath() <em>Data Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPath()
	 * @generated
	 * @ordered
	 */
	protected String dataPath = DATA_PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKeepList() <em>Keep List</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeepList()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> keepList;

	/**
	 * The default value of the '{@link #getReduceOption() <em>Reduce Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReduceOption()
	 * @generated
	 * @ordered
	 */
	protected static final reductionStrategy REDUCE_OPTION_EDEFAULT = reductionStrategy.STUBS;

	/**
	 * The cached value of the '{@link #getReduceOption() <em>Reduce Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReduceOption()
	 * @generated
	 * @ordered
	 */
	protected reductionStrategy reduceOption = REDUCE_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isDemandInterval() <em>Demand Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDemandInterval()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEMAND_INTERVAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDemandInterval() <em>Demand Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDemandInterval()
	 * @generated
	 * @ordered
	 */
	protected boolean demandInterval = DEMAND_INTERVAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPctNormal() <em>Pct Normal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctNormal()
	 * @generated
	 * @ordered
	 */
	protected static final float PCT_NORMAL_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getPctNormal() <em>Pct Normal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPctNormal()
	 * @generated
	 * @ordered
	 */
	protected float pctNormal = PCT_NORMAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isDIVerbose() <em>DI Verbose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDIVerbose()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DI_VERBOSE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDIVerbose() <em>DI Verbose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDIVerbose()
	 * @generated
	 * @ordered
	 */
	protected boolean diVerbose = DI_VERBOSE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCaseName() <em>Case Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCaseName()
	 * @generated
	 * @ordered
	 */
	protected static final String CASE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCaseName() <em>Case Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCaseName()
	 * @generated
	 * @ordered
	 */
	protected String caseName = CASE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMarkerCode() <em>Marker Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkerCode()
	 * @generated
	 * @ordered
	 */
	protected static final String MARKER_CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMarkerCode() <em>Marker Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkerCode()
	 * @generated
	 * @ordered
	 */
	protected String markerCode = MARKER_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNodeWidth() <em>Node Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeWidth()
	 * @generated
	 * @ordered
	 */
	protected static final float NODE_WIDTH_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getNodeWidth() <em>Node Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeWidth()
	 * @generated
	 * @ordered
	 */
	protected float nodeWidth = NODE_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isLog() <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLog()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLog() <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLog()
	 * @generated
	 * @ordered
	 */
	protected boolean log = LOG_EDEFAULT;

	/**
	 * The default value of the '{@link #isRecorder() <em>Recorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecorder()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECORDER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRecorder() <em>Recorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecorder()
	 * @generated
	 * @ordered
	 */
	protected boolean recorder = RECORDER_EDEFAULT;

	/**
	 * The default value of the '{@link #isOverloadReport() <em>Overload Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverloadReport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERLOAD_REPORT_EDEFAULT = false;

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
	 * The default value of the '{@link #isVoltageExceptionReport() <em>Voltage Exception Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVoltageExceptionReport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VOLTAGE_EXCEPTION_REPORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVoltageExceptionReport() <em>Voltage Exception Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVoltageExceptionReport()
	 * @generated
	 * @ordered
	 */
	protected boolean voltageExceptionReport = VOLTAGE_EXCEPTION_REPORT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecOptionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutivePackage.Literals.EXEC_OPTIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHour(int newHour) {
		int oldHour = hour;
		hour = newHour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__HOUR, oldHour, hour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSec() {
		return sec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSec(long newSec) {
		long oldSec = sec;
		sec = newSec;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__SEC, oldSec, sec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getYear() {
		return year;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYear(int newYear) {
		int oldYear = year;
		year = newYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__YEAR, oldYear, year));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFrequency() {
		return frequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrequency(float newFrequency) {
		float oldFrequency = frequency;
		frequency = newFrequency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__FREQUENCY, oldFrequency, frequency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getStepSize() {
		return stepSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStepSize(float newStepSize) {
		float oldStepSize = stepSize;
		stepSize = newStepSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__STEP_SIZE, oldStepSize, stepSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public solutionMode getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(solutionMode newMode) {
		solutionMode oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public randomType getRandom() {
		return random;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRandom(randomType newRandom) {
		randomType oldRandom = random;
		random = newRandom == null ? RANDOM_EDEFAULT : newRandom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__RANDOM, oldRandom, random));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getTime() {
		if (time == null) {
			time = new EDataTypeUniqueEList<Integer>(Integer.class, this, ExecutivePackage.EXEC_OPTIONS__TIME);
		}
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCircuit() {
		return circuit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCircuit(String newCircuit) {
		String oldCircuit = circuit;
		circuit = newCircuit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__CIRCUIT, oldCircuit, circuit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditor(String newEditor) {
		String oldEditor = editor;
		editor = newEditor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__EDITOR, oldEditor, editor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getTolerance() {
		return tolerance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTolerance(float newTolerance) {
		float oldTolerance = tolerance;
		tolerance = newTolerance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__TOLERANCE, oldTolerance, tolerance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxIter() {
		return maxIter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxIter(int newMaxIter) {
		int oldMaxIter = maxIter;
		maxIter = newMaxIter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__MAX_ITER, oldMaxIter, maxIter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public loadModelType getLoadModel() {
		return loadModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadModel(loadModelType newLoadModel) {
		loadModelType oldLoadModel = loadModel;
		loadModel = newLoadModel == null ? LOAD_MODEL_EDEFAULT : newLoadModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__LOAD_MODEL, oldLoadModel, loadModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getLoadMult() {
		return loadMult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadMult(float newLoadMult) {
		float oldLoadMult = loadMult;
		loadMult = newLoadMult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__LOAD_MULT, oldLoadMult, loadMult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getNormVMinPU() {
		return normVMinPU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormVMinPU(float newNormVMinPU) {
		float oldNormVMinPU = normVMinPU;
		normVMinPU = newNormVMinPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__NORM_VMIN_PU, oldNormVMinPU, normVMinPU));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getNormVMaxPU() {
		return normVMaxPU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormVMaxPU(float newNormVMaxPU) {
		float oldNormVMaxPU = normVMaxPU;
		normVMaxPU = newNormVMaxPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__NORM_VMAX_PU, oldNormVMaxPU, normVMaxPU));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getEmergVMinPU() {
		return emergVMinPU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmergVMinPU(float newEmergVMinPU) {
		float oldEmergVMinPU = emergVMinPU;
		emergVMinPU = newEmergVMinPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__EMERG_VMIN_PU, oldEmergVMinPU, emergVMinPU));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getEmergVMaxPU() {
		return emergVMaxPU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmergVMaxPU(float newEmergVMaxPU) {
		float oldEmergVMaxPU = emergVMaxPU;
		emergVMaxPU = newEmergVMaxPU;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__EMERG_VMAX_PU, oldEmergVMaxPU, emergVMaxPU));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPctMean() {
		return pctMean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctMean(float newPctMean) {
		float oldPctMean = pctMean;
		pctMean = newPctMean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__PCT_MEAN, oldPctMean, pctMean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPctStdDev() {
		return pctStdDev;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctStdDev(float newPctStdDev) {
		float oldPctStdDev = pctStdDev;
		pctStdDev = newPctStdDev;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__PCT_STD_DEV, oldPctStdDev, pctStdDev));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLDCurve() {
		if (ldCurve != null && ldCurve.eIsProxy()) {
			InternalEObject oldLDCurve = (InternalEObject)ldCurve;
			ldCurve = (EClass)eResolveProxy(oldLDCurve);
			if (ldCurve != oldLDCurve) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXEC_OPTIONS__LD_CURVE, oldLDCurve, ldCurve));
			}
		}
		return ldCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetLDCurve() {
		return ldCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLDCurve(EClass newLDCurve) {
		EClass oldLDCurve = ldCurve;
		ldCurve = newLDCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__LD_CURVE, oldLDCurve, ldCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPctGrowth() {
		return pctGrowth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctGrowth(float newPctGrowth) {
		float oldPctGrowth = pctGrowth;
		pctGrowth = newPctGrowth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__PCT_GROWTH, oldPctGrowth, pctGrowth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getGenKW() {
		return genKW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenKW(float newGenKW) {
		float oldGenKW = genKW;
		genKW = newGenKW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__GEN_KW, oldGenKW, genKW));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getGenPF() {
		return genPF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenPF(float newGenPF) {
		float oldGenPF = genPF;
		genPF = newGenPF;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__GEN_PF, oldGenPF, genPF));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getCapKVAr() {
		return capKVAr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCapKVAr(float newCapKVAr) {
		float oldCapKVAr = capKVAr;
		capKVAr = newCapKVAr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__CAP_KV_AR, oldCapKVAr, capKVAr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public autoAddType getAddType() {
		return addType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddType(autoAddType newAddType) {
		autoAddType oldAddType = addType;
		addType = newAddType == null ? ADD_TYPE_EDEFAULT : newAddType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__ADD_TYPE, oldAddType, addType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAllowDuplicates() {
		return allowDuplicates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllowDuplicates(boolean newAllowDuplicates) {
		boolean oldAllowDuplicates = allowDuplicates;
		allowDuplicates = newAllowDuplicates;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__ALLOW_DUPLICATES, oldAllowDuplicates, allowDuplicates));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isZoneLock() {
		return zoneLock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setZoneLock(boolean newZoneLock) {
		boolean oldZoneLock = zoneLock;
		zoneLock = newZoneLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__ZONE_LOCK, oldZoneLock, zoneLock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getUEWeight() {
		return ueWeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUEWeight(float newUEWeight) {
		float oldUEWeight = ueWeight;
		ueWeight = newUEWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__UE_WEIGHT, oldUEWeight, ueWeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getLossWeight() {
		return lossWeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLossWeight(float newLossWeight) {
		float oldLossWeight = lossWeight;
		lossWeight = newLossWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__LOSS_WEIGHT, oldLossWeight, lossWeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUERegs() {
		return ueRegs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUERegs(int newUERegs) {
		int oldUERegs = ueRegs;
		ueRegs = newUERegs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__UE_REGS, oldUERegs, ueRegs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLossRegs() {
		return lossRegs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLossRegs(int newLossRegs) {
		int oldLossRegs = lossRegs;
		lossRegs = newLossRegs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__LOSS_REGS, oldLossRegs, lossRegs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Float> getVoltageBases() {
		if (voltageBases == null) {
			voltageBases = new EDataTypeUniqueEList<Float>(Float.class, this, ExecutivePackage.EXEC_OPTIONS__VOLTAGE_BASES);
		}
		return voltageBases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public algorithmType getAlgorithm() {
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlgorithm(algorithmType newAlgorithm) {
		algorithmType oldAlgorithm = algorithm;
		algorithm = newAlgorithm == null ? ALGORITHM_EDEFAULT : newAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__ALGORITHM, oldAlgorithm, algorithm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTrapezoidal() {
		return trapezoidal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrapezoidal(boolean newTrapezoidal) {
		boolean oldTrapezoidal = trapezoidal;
		trapezoidal = newTrapezoidal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__TRAPEZOIDAL, oldTrapezoidal, trapezoidal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getAutoBusList() {
		if (autoBusList == null) {
			autoBusList = new EObjectResolvingEList<EClass>(EClass.class, this, ExecutivePackage.EXEC_OPTIONS__AUTO_BUS_LIST);
		}
		return autoBusList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public controlModeType getControlMode() {
		return controlMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlMode(controlModeType newControlMode) {
		controlModeType oldControlMode = controlMode;
		controlMode = newControlMode == null ? CONTROL_MODE_EDEFAULT : newControlMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__CONTROL_MODE, oldControlMode, controlMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTraceMode() {
		return traceMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTraceMode(boolean newTraceMode) {
		boolean oldTraceMode = traceMode;
		traceMode = newTraceMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__TRACE_MODE, oldTraceMode, traceMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getGenMult() {
		return genMult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenMult(float newGenMult) {
		float oldGenMult = genMult;
		genMult = newGenMult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__GEN_MULT, oldGenMult, genMult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefaultDaily() {
		if (defaultDaily != null && defaultDaily.eIsProxy()) {
			InternalEObject oldDefaultDaily = (InternalEObject)defaultDaily;
			defaultDaily = (EClass)eResolveProxy(oldDefaultDaily);
			if (defaultDaily != oldDefaultDaily) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXEC_OPTIONS__DEFAULT_DAILY, oldDefaultDaily, defaultDaily));
			}
		}
		return defaultDaily;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetDefaultDaily() {
		return defaultDaily;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultDaily(EClass newDefaultDaily) {
		EClass oldDefaultDaily = defaultDaily;
		defaultDaily = newDefaultDaily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__DEFAULT_DAILY, oldDefaultDaily, defaultDaily));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefaultYearly() {
		if (defaultYearly != null && defaultYearly.eIsProxy()) {
			InternalEObject oldDefaultYearly = (InternalEObject)defaultYearly;
			defaultYearly = (EClass)eResolveProxy(oldDefaultYearly);
			if (defaultYearly != oldDefaultYearly) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXEC_OPTIONS__DEFAULT_YEARLY, oldDefaultYearly, defaultYearly));
			}
		}
		return defaultYearly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetDefaultYearly() {
		return defaultYearly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultYearly(EClass newDefaultYearly) {
		EClass oldDefaultYearly = defaultYearly;
		defaultYearly = newDefaultYearly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__DEFAULT_YEARLY, oldDefaultYearly, defaultYearly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getAllocationFactor() {
		return allocationFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllocationFactor(float newAllocationFactor) {
		float oldAllocationFactor = allocationFactor;
		allocationFactor = newAllocationFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__ALLOCATION_FACTOR, oldAllocationFactor, allocationFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public circuitModelType getCktModel() {
		return cktModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCktModel(circuitModelType newCktModel) {
		circuitModelType oldCktModel = cktModel;
		cktModel = newCktModel == null ? CKT_MODEL_EDEFAULT : newCktModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__CKT_MODEL, oldCktModel, cktModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPriceSignal() {
		return priceSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriceSignal(float newPriceSignal) {
		float oldPriceSignal = priceSignal;
		priceSignal = newPriceSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__PRICE_SIGNAL, oldPriceSignal, priceSignal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPriceCurve() {
		if (priceCurve != null && priceCurve.eIsProxy()) {
			InternalEObject oldPriceCurve = (InternalEObject)priceCurve;
			priceCurve = (EClass)eResolveProxy(oldPriceCurve);
			if (priceCurve != oldPriceCurve) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXEC_OPTIONS__PRICE_CURVE, oldPriceCurve, priceCurve));
			}
		}
		return priceCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetPriceCurve() {
		return priceCurve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriceCurve(EClass newPriceCurve) {
		EClass oldPriceCurve = priceCurve;
		priceCurve = newPriceCurve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__PRICE_CURVE, oldPriceCurve, priceCurve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTerminal() {
		if (terminal != null && terminal.eIsProxy()) {
			InternalEObject oldTerminal = (InternalEObject)terminal;
			terminal = (EClass)eResolveProxy(oldTerminal);
			if (terminal != oldTerminal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXEC_OPTIONS__TERMINAL, oldTerminal, terminal));
			}
		}
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetTerminal() {
		return terminal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminal(EClass newTerminal) {
		EClass oldTerminal = terminal;
		terminal = newTerminal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__TERMINAL, oldTerminal, terminal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getBaseFrequency() {
		return baseFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseFrequency(float newBaseFrequency) {
		float oldBaseFrequency = baseFrequency;
		baseFrequency = newBaseFrequency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__BASE_FREQUENCY, oldBaseFrequency, baseFrequency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getHarmonics() {
		if (harmonics == null) {
			harmonics = new EDataTypeUniqueEList<Integer>(Integer.class, this, ExecutivePackage.EXEC_OPTIONS__HARMONICS);
		}
		return harmonics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxController() {
		return maxController;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxController(int newMaxController) {
		int oldMaxController = maxController;
		maxController = newMaxController;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__MAX_CONTROLLER, oldMaxController, maxController));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBus() {
		if (bus != null && bus.eIsProxy()) {
			InternalEObject oldBus = (InternalEObject)bus;
			bus = (EClass)eResolveProxy(oldBus);
			if (bus != oldBus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXEC_OPTIONS__BUS, oldBus, bus));
			}
		}
		return bus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetBus() {
		return bus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBus(EClass newBus) {
		EClass oldBus = bus;
		bus = newBus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__BUS, oldBus, bus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataPath() {
		return dataPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataPath(String newDataPath) {
		String oldDataPath = dataPath;
		dataPath = newDataPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__DATA_PATH, oldDataPath, dataPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getKeepList() {
		if (keepList == null) {
			keepList = new EObjectResolvingEList<EClass>(EClass.class, this, ExecutivePackage.EXEC_OPTIONS__KEEP_LIST);
		}
		return keepList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public reductionStrategy getReduceOption() {
		return reduceOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReduceOption(reductionStrategy newReduceOption) {
		reductionStrategy oldReduceOption = reduceOption;
		reduceOption = newReduceOption == null ? REDUCE_OPTION_EDEFAULT : newReduceOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__REDUCE_OPTION, oldReduceOption, reduceOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDemandInterval() {
		return demandInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDemandInterval(boolean newDemandInterval) {
		boolean oldDemandInterval = demandInterval;
		demandInterval = newDemandInterval;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__DEMAND_INTERVAL, oldDemandInterval, demandInterval));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPctNormal() {
		return pctNormal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPctNormal(float newPctNormal) {
		float oldPctNormal = pctNormal;
		pctNormal = newPctNormal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__PCT_NORMAL, oldPctNormal, pctNormal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDIVerbose() {
		return diVerbose;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDIVerbose(boolean newDIVerbose) {
		boolean oldDIVerbose = diVerbose;
		diVerbose = newDIVerbose;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__DI_VERBOSE, oldDIVerbose, diVerbose));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaseName(String newCaseName) {
		String oldCaseName = caseName;
		caseName = newCaseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__CASE_NAME, oldCaseName, caseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMarkerCode() {
		return markerCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMarkerCode(String newMarkerCode) {
		String oldMarkerCode = markerCode;
		markerCode = newMarkerCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__MARKER_CODE, oldMarkerCode, markerCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getNodeWidth() {
		return nodeWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeWidth(float newNodeWidth) {
		float oldNodeWidth = nodeWidth;
		nodeWidth = newNodeWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__NODE_WIDTH, oldNodeWidth, nodeWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLog() {
		return log;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLog(boolean newLog) {
		boolean oldLog = log;
		log = newLog;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__LOG, oldLog, log));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRecorder() {
		return recorder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecorder(boolean newRecorder) {
		boolean oldRecorder = recorder;
		recorder = newRecorder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__RECORDER, oldRecorder, recorder));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__OVERLOAD_REPORT, oldOverloadReport, overloadReport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVoltageExceptionReport() {
		return voltageExceptionReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVoltageExceptionReport(boolean newVoltageExceptionReport) {
		boolean oldVoltageExceptionReport = voltageExceptionReport;
		voltageExceptionReport = newVoltageExceptionReport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT, oldVoltageExceptionReport, voltageExceptionReport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExecutivePackage.EXEC_OPTIONS__TYPE:
				return getType();
			case ExecutivePackage.EXEC_OPTIONS__ELEMENT:
				return getElement();
			case ExecutivePackage.EXEC_OPTIONS__HOUR:
				return getHour();
			case ExecutivePackage.EXEC_OPTIONS__SEC:
				return getSec();
			case ExecutivePackage.EXEC_OPTIONS__YEAR:
				return getYear();
			case ExecutivePackage.EXEC_OPTIONS__FREQUENCY:
				return getFrequency();
			case ExecutivePackage.EXEC_OPTIONS__STEP_SIZE:
				return getStepSize();
			case ExecutivePackage.EXEC_OPTIONS__MODE:
				return getMode();
			case ExecutivePackage.EXEC_OPTIONS__RANDOM:
				return getRandom();
			case ExecutivePackage.EXEC_OPTIONS__NUMBER:
				return getNumber();
			case ExecutivePackage.EXEC_OPTIONS__TIME:
				return getTime();
			case ExecutivePackage.EXEC_OPTIONS__CIRCUIT:
				return getCircuit();
			case ExecutivePackage.EXEC_OPTIONS__EDITOR:
				return getEditor();
			case ExecutivePackage.EXEC_OPTIONS__TOLERANCE:
				return getTolerance();
			case ExecutivePackage.EXEC_OPTIONS__MAX_ITER:
				return getMaxIter();
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MODEL:
				return getLoadModel();
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MULT:
				return getLoadMult();
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMIN_PU:
				return getNormVMinPU();
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMAX_PU:
				return getNormVMaxPU();
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMIN_PU:
				return getEmergVMinPU();
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMAX_PU:
				return getEmergVMaxPU();
			case ExecutivePackage.EXEC_OPTIONS__PCT_MEAN:
				return getPctMean();
			case ExecutivePackage.EXEC_OPTIONS__PCT_STD_DEV:
				return getPctStdDev();
			case ExecutivePackage.EXEC_OPTIONS__LD_CURVE:
				if (resolve) return getLDCurve();
				return basicGetLDCurve();
			case ExecutivePackage.EXEC_OPTIONS__PCT_GROWTH:
				return getPctGrowth();
			case ExecutivePackage.EXEC_OPTIONS__GEN_KW:
				return getGenKW();
			case ExecutivePackage.EXEC_OPTIONS__GEN_PF:
				return getGenPF();
			case ExecutivePackage.EXEC_OPTIONS__CAP_KV_AR:
				return getCapKVAr();
			case ExecutivePackage.EXEC_OPTIONS__ADD_TYPE:
				return getAddType();
			case ExecutivePackage.EXEC_OPTIONS__ALLOW_DUPLICATES:
				return isAllowDuplicates();
			case ExecutivePackage.EXEC_OPTIONS__ZONE_LOCK:
				return isZoneLock();
			case ExecutivePackage.EXEC_OPTIONS__UE_WEIGHT:
				return getUEWeight();
			case ExecutivePackage.EXEC_OPTIONS__LOSS_WEIGHT:
				return getLossWeight();
			case ExecutivePackage.EXEC_OPTIONS__UE_REGS:
				return getUERegs();
			case ExecutivePackage.EXEC_OPTIONS__LOSS_REGS:
				return getLossRegs();
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_BASES:
				return getVoltageBases();
			case ExecutivePackage.EXEC_OPTIONS__ALGORITHM:
				return getAlgorithm();
			case ExecutivePackage.EXEC_OPTIONS__TRAPEZOIDAL:
				return isTrapezoidal();
			case ExecutivePackage.EXEC_OPTIONS__AUTO_BUS_LIST:
				return getAutoBusList();
			case ExecutivePackage.EXEC_OPTIONS__CONTROL_MODE:
				return getControlMode();
			case ExecutivePackage.EXEC_OPTIONS__TRACE_MODE:
				return isTraceMode();
			case ExecutivePackage.EXEC_OPTIONS__GEN_MULT:
				return getGenMult();
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_DAILY:
				if (resolve) return getDefaultDaily();
				return basicGetDefaultDaily();
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_YEARLY:
				if (resolve) return getDefaultYearly();
				return basicGetDefaultYearly();
			case ExecutivePackage.EXEC_OPTIONS__ALLOCATION_FACTOR:
				return getAllocationFactor();
			case ExecutivePackage.EXEC_OPTIONS__CKT_MODEL:
				return getCktModel();
			case ExecutivePackage.EXEC_OPTIONS__PRICE_SIGNAL:
				return getPriceSignal();
			case ExecutivePackage.EXEC_OPTIONS__PRICE_CURVE:
				if (resolve) return getPriceCurve();
				return basicGetPriceCurve();
			case ExecutivePackage.EXEC_OPTIONS__TERMINAL:
				if (resolve) return getTerminal();
				return basicGetTerminal();
			case ExecutivePackage.EXEC_OPTIONS__BASE_FREQUENCY:
				return getBaseFrequency();
			case ExecutivePackage.EXEC_OPTIONS__HARMONICS:
				return getHarmonics();
			case ExecutivePackage.EXEC_OPTIONS__MAX_CONTROLLER:
				return getMaxController();
			case ExecutivePackage.EXEC_OPTIONS__BUS:
				if (resolve) return getBus();
				return basicGetBus();
			case ExecutivePackage.EXEC_OPTIONS__DATA_PATH:
				return getDataPath();
			case ExecutivePackage.EXEC_OPTIONS__KEEP_LIST:
				return getKeepList();
			case ExecutivePackage.EXEC_OPTIONS__REDUCE_OPTION:
				return getReduceOption();
			case ExecutivePackage.EXEC_OPTIONS__DEMAND_INTERVAL:
				return isDemandInterval();
			case ExecutivePackage.EXEC_OPTIONS__PCT_NORMAL:
				return getPctNormal();
			case ExecutivePackage.EXEC_OPTIONS__DI_VERBOSE:
				return isDIVerbose();
			case ExecutivePackage.EXEC_OPTIONS__CASE_NAME:
				return getCaseName();
			case ExecutivePackage.EXEC_OPTIONS__MARKER_CODE:
				return getMarkerCode();
			case ExecutivePackage.EXEC_OPTIONS__NODE_WIDTH:
				return getNodeWidth();
			case ExecutivePackage.EXEC_OPTIONS__LOG:
				return isLog();
			case ExecutivePackage.EXEC_OPTIONS__RECORDER:
				return isRecorder();
			case ExecutivePackage.EXEC_OPTIONS__OVERLOAD_REPORT:
				return isOverloadReport();
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT:
				return isVoltageExceptionReport();
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
			case ExecutivePackage.EXEC_OPTIONS__TYPE:
				setType((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ELEMENT:
				setElement((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__HOUR:
				setHour((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__SEC:
				setSec((Long)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__YEAR:
				setYear((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__FREQUENCY:
				setFrequency((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__STEP_SIZE:
				setStepSize((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MODE:
				setMode((solutionMode)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__RANDOM:
				setRandom((randomType)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NUMBER:
				setNumber((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TIME:
				getTime().clear();
				getTime().addAll((Collection<? extends Integer>)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CIRCUIT:
				setCircuit((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__EDITOR:
				setEditor((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TOLERANCE:
				setTolerance((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MAX_ITER:
				setMaxIter((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MODEL:
				setLoadModel((loadModelType)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MULT:
				setLoadMult((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMIN_PU:
				setNormVMinPU((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMAX_PU:
				setNormVMaxPU((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMIN_PU:
				setEmergVMinPU((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMAX_PU:
				setEmergVMaxPU((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_MEAN:
				setPctMean((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_STD_DEV:
				setPctStdDev((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LD_CURVE:
				setLDCurve((EClass)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_GROWTH:
				setPctGrowth((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__GEN_KW:
				setGenKW((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__GEN_PF:
				setGenPF((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CAP_KV_AR:
				setCapKVAr((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ADD_TYPE:
				setAddType((autoAddType)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ALLOW_DUPLICATES:
				setAllowDuplicates((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ZONE_LOCK:
				setZoneLock((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__UE_WEIGHT:
				setUEWeight((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOSS_WEIGHT:
				setLossWeight((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__UE_REGS:
				setUERegs((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOSS_REGS:
				setLossRegs((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_BASES:
				getVoltageBases().clear();
				getVoltageBases().addAll((Collection<? extends Float>)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ALGORITHM:
				setAlgorithm((algorithmType)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TRAPEZOIDAL:
				setTrapezoidal((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__AUTO_BUS_LIST:
				getAutoBusList().clear();
				getAutoBusList().addAll((Collection<? extends EClass>)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CONTROL_MODE:
				setControlMode((controlModeType)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TRACE_MODE:
				setTraceMode((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__GEN_MULT:
				setGenMult((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_DAILY:
				setDefaultDaily((EClass)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_YEARLY:
				setDefaultYearly((EClass)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ALLOCATION_FACTOR:
				setAllocationFactor((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CKT_MODEL:
				setCktModel((circuitModelType)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PRICE_SIGNAL:
				setPriceSignal((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PRICE_CURVE:
				setPriceCurve((EClass)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TERMINAL:
				setTerminal((EClass)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__BASE_FREQUENCY:
				setBaseFrequency((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__HARMONICS:
				getHarmonics().clear();
				getHarmonics().addAll((Collection<? extends Integer>)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MAX_CONTROLLER:
				setMaxController((Integer)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__BUS:
				setBus((EClass)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DATA_PATH:
				setDataPath((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__KEEP_LIST:
				getKeepList().clear();
				getKeepList().addAll((Collection<? extends EClass>)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__REDUCE_OPTION:
				setReduceOption((reductionStrategy)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DEMAND_INTERVAL:
				setDemandInterval((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_NORMAL:
				setPctNormal((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DI_VERBOSE:
				setDIVerbose((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CASE_NAME:
				setCaseName((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MARKER_CODE:
				setMarkerCode((String)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NODE_WIDTH:
				setNodeWidth((Float)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOG:
				setLog((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__RECORDER:
				setRecorder((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__OVERLOAD_REPORT:
				setOverloadReport((Boolean)newValue);
				return;
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT:
				setVoltageExceptionReport((Boolean)newValue);
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
			case ExecutivePackage.EXEC_OPTIONS__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__HOUR:
				setHour(HOUR_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__SEC:
				setSec(SEC_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__YEAR:
				setYear(YEAR_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__STEP_SIZE:
				setStepSize(STEP_SIZE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__RANDOM:
				setRandom(RANDOM_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TIME:
				getTime().clear();
				return;
			case ExecutivePackage.EXEC_OPTIONS__CIRCUIT:
				setCircuit(CIRCUIT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__EDITOR:
				setEditor(EDITOR_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TOLERANCE:
				setTolerance(TOLERANCE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MAX_ITER:
				setMaxIter(MAX_ITER_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MODEL:
				setLoadModel(LOAD_MODEL_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MULT:
				setLoadMult(LOAD_MULT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMIN_PU:
				setNormVMinPU(NORM_VMIN_PU_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMAX_PU:
				setNormVMaxPU(NORM_VMAX_PU_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMIN_PU:
				setEmergVMinPU(EMERG_VMIN_PU_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMAX_PU:
				setEmergVMaxPU(EMERG_VMAX_PU_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_MEAN:
				setPctMean(PCT_MEAN_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_STD_DEV:
				setPctStdDev(PCT_STD_DEV_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LD_CURVE:
				setLDCurve((EClass)null);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_GROWTH:
				setPctGrowth(PCT_GROWTH_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__GEN_KW:
				setGenKW(GEN_KW_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__GEN_PF:
				setGenPF(GEN_PF_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CAP_KV_AR:
				setCapKVAr(CAP_KV_AR_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ADD_TYPE:
				setAddType(ADD_TYPE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ALLOW_DUPLICATES:
				setAllowDuplicates(ALLOW_DUPLICATES_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ZONE_LOCK:
				setZoneLock(ZONE_LOCK_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__UE_WEIGHT:
				setUEWeight(UE_WEIGHT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOSS_WEIGHT:
				setLossWeight(LOSS_WEIGHT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__UE_REGS:
				setUERegs(UE_REGS_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOSS_REGS:
				setLossRegs(LOSS_REGS_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_BASES:
				getVoltageBases().clear();
				return;
			case ExecutivePackage.EXEC_OPTIONS__ALGORITHM:
				setAlgorithm(ALGORITHM_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TRAPEZOIDAL:
				setTrapezoidal(TRAPEZOIDAL_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__AUTO_BUS_LIST:
				getAutoBusList().clear();
				return;
			case ExecutivePackage.EXEC_OPTIONS__CONTROL_MODE:
				setControlMode(CONTROL_MODE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TRACE_MODE:
				setTraceMode(TRACE_MODE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__GEN_MULT:
				setGenMult(GEN_MULT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_DAILY:
				setDefaultDaily((EClass)null);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_YEARLY:
				setDefaultYearly((EClass)null);
				return;
			case ExecutivePackage.EXEC_OPTIONS__ALLOCATION_FACTOR:
				setAllocationFactor(ALLOCATION_FACTOR_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CKT_MODEL:
				setCktModel(CKT_MODEL_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PRICE_SIGNAL:
				setPriceSignal(PRICE_SIGNAL_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PRICE_CURVE:
				setPriceCurve((EClass)null);
				return;
			case ExecutivePackage.EXEC_OPTIONS__TERMINAL:
				setTerminal((EClass)null);
				return;
			case ExecutivePackage.EXEC_OPTIONS__BASE_FREQUENCY:
				setBaseFrequency(BASE_FREQUENCY_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__HARMONICS:
				getHarmonics().clear();
				return;
			case ExecutivePackage.EXEC_OPTIONS__MAX_CONTROLLER:
				setMaxController(MAX_CONTROLLER_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__BUS:
				setBus((EClass)null);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DATA_PATH:
				setDataPath(DATA_PATH_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__KEEP_LIST:
				getKeepList().clear();
				return;
			case ExecutivePackage.EXEC_OPTIONS__REDUCE_OPTION:
				setReduceOption(REDUCE_OPTION_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DEMAND_INTERVAL:
				setDemandInterval(DEMAND_INTERVAL_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__PCT_NORMAL:
				setPctNormal(PCT_NORMAL_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__DI_VERBOSE:
				setDIVerbose(DI_VERBOSE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__CASE_NAME:
				setCaseName(CASE_NAME_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__MARKER_CODE:
				setMarkerCode(MARKER_CODE_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__NODE_WIDTH:
				setNodeWidth(NODE_WIDTH_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__LOG:
				setLog(LOG_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__RECORDER:
				setRecorder(RECORDER_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__OVERLOAD_REPORT:
				setOverloadReport(OVERLOAD_REPORT_EDEFAULT);
				return;
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT:
				setVoltageExceptionReport(VOLTAGE_EXCEPTION_REPORT_EDEFAULT);
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
			case ExecutivePackage.EXEC_OPTIONS__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ExecutivePackage.EXEC_OPTIONS__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case ExecutivePackage.EXEC_OPTIONS__HOUR:
				return hour != HOUR_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__SEC:
				return sec != SEC_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__YEAR:
				return year != YEAR_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__FREQUENCY:
				return frequency != FREQUENCY_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__STEP_SIZE:
				return stepSize != STEP_SIZE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__MODE:
				return mode != MODE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__RANDOM:
				return random != RANDOM_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__NUMBER:
				return number != NUMBER_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__TIME:
				return time != null && !time.isEmpty();
			case ExecutivePackage.EXEC_OPTIONS__CIRCUIT:
				return CIRCUIT_EDEFAULT == null ? circuit != null : !CIRCUIT_EDEFAULT.equals(circuit);
			case ExecutivePackage.EXEC_OPTIONS__EDITOR:
				return EDITOR_EDEFAULT == null ? editor != null : !EDITOR_EDEFAULT.equals(editor);
			case ExecutivePackage.EXEC_OPTIONS__TOLERANCE:
				return tolerance != TOLERANCE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__MAX_ITER:
				return maxIter != MAX_ITER_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MODEL:
				return loadModel != LOAD_MODEL_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__LOAD_MULT:
				return loadMult != LOAD_MULT_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMIN_PU:
				return normVMinPU != NORM_VMIN_PU_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__NORM_VMAX_PU:
				return normVMaxPU != NORM_VMAX_PU_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMIN_PU:
				return emergVMinPU != EMERG_VMIN_PU_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__EMERG_VMAX_PU:
				return emergVMaxPU != EMERG_VMAX_PU_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__PCT_MEAN:
				return pctMean != PCT_MEAN_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__PCT_STD_DEV:
				return pctStdDev != PCT_STD_DEV_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__LD_CURVE:
				return ldCurve != null;
			case ExecutivePackage.EXEC_OPTIONS__PCT_GROWTH:
				return pctGrowth != PCT_GROWTH_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__GEN_KW:
				return genKW != GEN_KW_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__GEN_PF:
				return genPF != GEN_PF_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__CAP_KV_AR:
				return capKVAr != CAP_KV_AR_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__ADD_TYPE:
				return addType != ADD_TYPE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__ALLOW_DUPLICATES:
				return allowDuplicates != ALLOW_DUPLICATES_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__ZONE_LOCK:
				return zoneLock != ZONE_LOCK_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__UE_WEIGHT:
				return ueWeight != UE_WEIGHT_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__LOSS_WEIGHT:
				return lossWeight != LOSS_WEIGHT_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__UE_REGS:
				return ueRegs != UE_REGS_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__LOSS_REGS:
				return lossRegs != LOSS_REGS_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_BASES:
				return voltageBases != null && !voltageBases.isEmpty();
			case ExecutivePackage.EXEC_OPTIONS__ALGORITHM:
				return algorithm != ALGORITHM_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__TRAPEZOIDAL:
				return trapezoidal != TRAPEZOIDAL_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__AUTO_BUS_LIST:
				return autoBusList != null && !autoBusList.isEmpty();
			case ExecutivePackage.EXEC_OPTIONS__CONTROL_MODE:
				return controlMode != CONTROL_MODE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__TRACE_MODE:
				return traceMode != TRACE_MODE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__GEN_MULT:
				return genMult != GEN_MULT_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_DAILY:
				return defaultDaily != null;
			case ExecutivePackage.EXEC_OPTIONS__DEFAULT_YEARLY:
				return defaultYearly != null;
			case ExecutivePackage.EXEC_OPTIONS__ALLOCATION_FACTOR:
				return allocationFactor != ALLOCATION_FACTOR_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__CKT_MODEL:
				return cktModel != CKT_MODEL_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__PRICE_SIGNAL:
				return priceSignal != PRICE_SIGNAL_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__PRICE_CURVE:
				return priceCurve != null;
			case ExecutivePackage.EXEC_OPTIONS__TERMINAL:
				return terminal != null;
			case ExecutivePackage.EXEC_OPTIONS__BASE_FREQUENCY:
				return baseFrequency != BASE_FREQUENCY_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__HARMONICS:
				return harmonics != null && !harmonics.isEmpty();
			case ExecutivePackage.EXEC_OPTIONS__MAX_CONTROLLER:
				return maxController != MAX_CONTROLLER_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__BUS:
				return bus != null;
			case ExecutivePackage.EXEC_OPTIONS__DATA_PATH:
				return DATA_PATH_EDEFAULT == null ? dataPath != null : !DATA_PATH_EDEFAULT.equals(dataPath);
			case ExecutivePackage.EXEC_OPTIONS__KEEP_LIST:
				return keepList != null && !keepList.isEmpty();
			case ExecutivePackage.EXEC_OPTIONS__REDUCE_OPTION:
				return reduceOption != REDUCE_OPTION_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__DEMAND_INTERVAL:
				return demandInterval != DEMAND_INTERVAL_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__PCT_NORMAL:
				return pctNormal != PCT_NORMAL_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__DI_VERBOSE:
				return diVerbose != DI_VERBOSE_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__CASE_NAME:
				return CASE_NAME_EDEFAULT == null ? caseName != null : !CASE_NAME_EDEFAULT.equals(caseName);
			case ExecutivePackage.EXEC_OPTIONS__MARKER_CODE:
				return MARKER_CODE_EDEFAULT == null ? markerCode != null : !MARKER_CODE_EDEFAULT.equals(markerCode);
			case ExecutivePackage.EXEC_OPTIONS__NODE_WIDTH:
				return nodeWidth != NODE_WIDTH_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__LOG:
				return log != LOG_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__RECORDER:
				return recorder != RECORDER_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__OVERLOAD_REPORT:
				return overloadReport != OVERLOAD_REPORT_EDEFAULT;
			case ExecutivePackage.EXEC_OPTIONS__VOLTAGE_EXCEPTION_REPORT:
				return voltageExceptionReport != VOLTAGE_EXCEPTION_REPORT_EDEFAULT;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", element: ");
		result.append(element);
		result.append(", hour: ");
		result.append(hour);
		result.append(", sec: ");
		result.append(sec);
		result.append(", year: ");
		result.append(year);
		result.append(", frequency: ");
		result.append(frequency);
		result.append(", stepSize: ");
		result.append(stepSize);
		result.append(", mode: ");
		result.append(mode);
		result.append(", random: ");
		result.append(random);
		result.append(", number: ");
		result.append(number);
		result.append(", time: ");
		result.append(time);
		result.append(", circuit: ");
		result.append(circuit);
		result.append(", editor: ");
		result.append(editor);
		result.append(", tolerance: ");
		result.append(tolerance);
		result.append(", maxIter: ");
		result.append(maxIter);
		result.append(", loadModel: ");
		result.append(loadModel);
		result.append(", loadMult: ");
		result.append(loadMult);
		result.append(", normVMinPU: ");
		result.append(normVMinPU);
		result.append(", normVMaxPU: ");
		result.append(normVMaxPU);
		result.append(", emergVMinPU: ");
		result.append(emergVMinPU);
		result.append(", emergVMaxPU: ");
		result.append(emergVMaxPU);
		result.append(", pctMean: ");
		result.append(pctMean);
		result.append(", pctStdDev: ");
		result.append(pctStdDev);
		result.append(", pctGrowth: ");
		result.append(pctGrowth);
		result.append(", genKW: ");
		result.append(genKW);
		result.append(", genPF: ");
		result.append(genPF);
		result.append(", capKVAr: ");
		result.append(capKVAr);
		result.append(", addType: ");
		result.append(addType);
		result.append(", allowDuplicates: ");
		result.append(allowDuplicates);
		result.append(", zoneLock: ");
		result.append(zoneLock);
		result.append(", UEWeight: ");
		result.append(ueWeight);
		result.append(", lossWeight: ");
		result.append(lossWeight);
		result.append(", UERegs: ");
		result.append(ueRegs);
		result.append(", lossRegs: ");
		result.append(lossRegs);
		result.append(", voltageBases: ");
		result.append(voltageBases);
		result.append(", algorithm: ");
		result.append(algorithm);
		result.append(", trapezoidal: ");
		result.append(trapezoidal);
		result.append(", controlMode: ");
		result.append(controlMode);
		result.append(", traceMode: ");
		result.append(traceMode);
		result.append(", genMult: ");
		result.append(genMult);
		result.append(", allocationFactor: ");
		result.append(allocationFactor);
		result.append(", cktModel: ");
		result.append(cktModel);
		result.append(", priceSignal: ");
		result.append(priceSignal);
		result.append(", baseFrequency: ");
		result.append(baseFrequency);
		result.append(", harmonics: ");
		result.append(harmonics);
		result.append(", maxController: ");
		result.append(maxController);
		result.append(", dataPath: ");
		result.append(dataPath);
		result.append(", reduceOption: ");
		result.append(reduceOption);
		result.append(", demandInterval: ");
		result.append(demandInterval);
		result.append(", pctNormal: ");
		result.append(pctNormal);
		result.append(", DIVerbose: ");
		result.append(diVerbose);
		result.append(", caseName: ");
		result.append(caseName);
		result.append(", markerCode: ");
		result.append(markerCode);
		result.append(", nodeWidth: ");
		result.append(nodeWidth);
		result.append(", log: ");
		result.append(log);
		result.append(", recorder: ");
		result.append(recorder);
		result.append(", overloadReport: ");
		result.append(overloadReport);
		result.append(", voltageExceptionReport: ");
		result.append(voltageExceptionReport);
		result.append(')');
		return result.toString();
	}

} //ExecOptionsImpl
