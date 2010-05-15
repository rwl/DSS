/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package dss.common.impl;

import cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D;
import dss.common.Circuit;
import java.security.KeyStore.Builder;

import dss.common.CommonPackage;
import dss.common.Solution;
import dss.common.algorithmType;
import dss.common.controlModeType;
import dss.executive.loadModelType;
import dss.common.yBuildOption;
import dss.control.ControlElement;

import dss.executive.solutionMode;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.common.impl.SolutionImpl#getYear <em>Year</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isPreserveNodeVoltages <em>Preserve Node Voltages</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isFrequencyChanged <em>Frequency Changed</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isSolutionAbort <em>Solution Abort</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isSolutionInitialised <em>Solution Initialised</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isSeriesYInvalid <em>Series YInvalid</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isSystemYChanged <em>System YChanged</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getLoadModel <em>Load Model</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isVoltageBaseChanged <em>Voltage Base Changed</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isHarmonicModel <em>Harmonic Model</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isDynamicModel <em>Dynamic Model</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isUseAuxillaryCurrents <em>Use Auxillary Currents</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isLoadsNeedUpdating <em>Loads Need Updating</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getIteration <em>Iteration</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getMaxIterations <em>Max Iterations</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getMaxError <em>Max Error</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getConvergenceTolerance <em>Convergence Tolerance</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isConverged <em>Converged</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getControlIteration <em>Control Iteration</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getMaxControlIterations <em>Max Control Iterations</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getControlMode <em>Control Mode</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isControlActionsDone <em>Control Actions Done</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getMostIterationsDone <em>Most Iterations Done</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#isLastSolutionWasDirect <em>Last Solution Was Direct</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getNodeV <em>Node V</em>}</li>
 *   <li>{@link dss.common.impl.SolutionImpl#getCurrents <em>Currents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SolutionImpl extends EObjectImpl implements Solution {
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
	 * The default value of the '{@link #isPreserveNodeVoltages() <em>Preserve Node Voltages</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isPreserveNodeVoltages()
	 * @generated
	 * @ordered
	 */
    protected static final boolean PRESERVE_NODE_VOLTAGES_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isPreserveNodeVoltages() <em>Preserve Node Voltages</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isPreserveNodeVoltages()
	 * @generated
	 * @ordered
	 */
    protected boolean preserveNodeVoltages = PRESERVE_NODE_VOLTAGES_EDEFAULT;

    /**
	 * The default value of the '{@link #isFrequencyChanged() <em>Frequency Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFrequencyChanged()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FREQUENCY_CHANGED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isFrequencyChanged() <em>Frequency Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFrequencyChanged()
	 * @generated
	 * @ordered
	 */
    protected boolean frequencyChanged = FREQUENCY_CHANGED_EDEFAULT;

    /**
	 * The default value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
    protected static final double FREQUENCY_EDEFAULT = 60.0;

    /**
	 * The cached value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
    protected double frequency = FREQUENCY_EDEFAULT;

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
	 * The cached value of the '{@link #getCircuit() <em>Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCircuit()
	 * @generated
	 * @ordered
	 */
    protected Circuit circuit;

                /**
	 * The default value of the '{@link #isSolutionAbort() <em>Solution Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSolutionAbort()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SOLUTION_ABORT_EDEFAULT = false;

                /**
	 * The cached value of the '{@link #isSolutionAbort() <em>Solution Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSolutionAbort()
	 * @generated
	 * @ordered
	 */
    protected boolean solutionAbort = SOLUTION_ABORT_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isSolutionInitialised() <em>Solution Initialised</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSolutionInitialised()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SOLUTION_INITIALISED_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isSolutionInitialised() <em>Solution Initialised</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSolutionInitialised()
	 * @generated
	 * @ordered
	 */
    protected boolean solutionInitialised = SOLUTION_INITIALISED_EDEFAULT;

                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #isSeriesYInvalid() <em>Series YInvalid</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSeriesYInvalid()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SERIES_YINVALID_EDEFAULT = false;

                /**
	 * The cached value of the '{@link #isSeriesYInvalid() <em>Series YInvalid</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSeriesYInvalid()
	 * @generated
	 * @ordered
	 */
    protected boolean seriesYInvalid = SERIES_YINVALID_EDEFAULT;

                /**
	 * The default value of the '{@link #isSystemYChanged() <em>System YChanged</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSystemYChanged()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SYSTEM_YCHANGED_EDEFAULT = false;

                /**
	 * The cached value of the '{@link #isSystemYChanged() <em>System YChanged</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSystemYChanged()
	 * @generated
	 * @ordered
	 */
    protected boolean systemYChanged = SYSTEM_YCHANGED_EDEFAULT;

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
	 * The default value of the '{@link #isVoltageBaseChanged() <em>Voltage Base Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isVoltageBaseChanged()
	 * @generated
	 * @ordered
	 */
    protected static final boolean VOLTAGE_BASE_CHANGED_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isVoltageBaseChanged() <em>Voltage Base Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isVoltageBaseChanged()
	 * @generated
	 * @ordered
	 */
    protected boolean voltageBaseChanged = VOLTAGE_BASE_CHANGED_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isHarmonicModel() <em>Harmonic Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHarmonicModel()
	 * @generated
	 * @ordered
	 */
    protected static final boolean HARMONIC_MODEL_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isHarmonicModel() <em>Harmonic Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isHarmonicModel()
	 * @generated
	 * @ordered
	 */
    protected boolean harmonicModel = HARMONIC_MODEL_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isDynamicModel() <em>Dynamic Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDynamicModel()
	 * @generated
	 * @ordered
	 */
    protected static final boolean DYNAMIC_MODEL_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isDynamicModel() <em>Dynamic Model</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDynamicModel()
	 * @generated
	 * @ordered
	 */
    protected boolean dynamicModel = DYNAMIC_MODEL_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isUseAuxillaryCurrents() <em>Use Auxillary Currents</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isUseAuxillaryCurrents()
	 * @generated
	 * @ordered
	 */
    protected static final boolean USE_AUXILLARY_CURRENTS_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isUseAuxillaryCurrents() <em>Use Auxillary Currents</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isUseAuxillaryCurrents()
	 * @generated
	 * @ordered
	 */
    protected boolean useAuxillaryCurrents = USE_AUXILLARY_CURRENTS_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isLoadsNeedUpdating() <em>Loads Need Updating</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLoadsNeedUpdating()
	 * @generated
	 * @ordered
	 */
    protected static final boolean LOADS_NEED_UPDATING_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isLoadsNeedUpdating() <em>Loads Need Updating</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLoadsNeedUpdating()
	 * @generated
	 * @ordered
	 */
    protected boolean loadsNeedUpdating = LOADS_NEED_UPDATING_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getIteration() <em>Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIteration()
	 * @generated
	 * @ordered
	 */
    protected static final int ITERATION_EDEFAULT = 0;

                                                                /**
	 * The cached value of the '{@link #getIteration() <em>Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIteration()
	 * @generated
	 * @ordered
	 */
    protected int iteration = ITERATION_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getMaxIterations() <em>Max Iterations</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxIterations()
	 * @generated
	 * @ordered
	 */
    protected static final int MAX_ITERATIONS_EDEFAULT = 0;

                                                                /**
	 * The cached value of the '{@link #getMaxIterations() <em>Max Iterations</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxIterations()
	 * @generated
	 * @ordered
	 */
    protected int maxIterations = MAX_ITERATIONS_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getMaxError() <em>Max Error</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxError()
	 * @generated
	 * @ordered
	 */
    protected static final double MAX_ERROR_EDEFAULT = 0.0;

                                                                /**
	 * The cached value of the '{@link #getMaxError() <em>Max Error</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxError()
	 * @generated
	 * @ordered
	 */
    protected double maxError = MAX_ERROR_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getConvergenceTolerance() <em>Convergence Tolerance</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getConvergenceTolerance()
	 * @generated
	 * @ordered
	 */
    protected static final double CONVERGENCE_TOLERANCE_EDEFAULT = 0.0;

                                                                /**
	 * The cached value of the '{@link #getConvergenceTolerance() <em>Convergence Tolerance</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getConvergenceTolerance()
	 * @generated
	 * @ordered
	 */
    protected double convergenceTolerance = CONVERGENCE_TOLERANCE_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #isConverged() <em>Converged</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isConverged()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CONVERGED_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isConverged() <em>Converged</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isConverged()
	 * @generated
	 * @ordered
	 */
    protected boolean converged = CONVERGED_EDEFAULT;

                                                                /**
	 * This is true if the Converged attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean convergedESet;

                                                                /**
	 * The default value of the '{@link #getControlIteration() <em>Control Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getControlIteration()
	 * @generated
	 * @ordered
	 */
    protected static final int CONTROL_ITERATION_EDEFAULT = 0;

                                                                /**
	 * The cached value of the '{@link #getControlIteration() <em>Control Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getControlIteration()
	 * @generated
	 * @ordered
	 */
    protected int controlIteration = CONTROL_ITERATION_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getMaxControlIterations() <em>Max Control Iterations</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxControlIterations()
	 * @generated
	 * @ordered
	 */
    protected static final int MAX_CONTROL_ITERATIONS_EDEFAULT = 10;

                                                                /**
	 * The cached value of the '{@link #getMaxControlIterations() <em>Max Control Iterations</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxControlIterations()
	 * @generated
	 * @ordered
	 */
    protected int maxControlIterations = MAX_CONTROL_ITERATIONS_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getControlMode() <em>Control Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getControlMode()
	 * @generated
	 * @ordered
	 */
    protected static final controlModeType CONTROL_MODE_EDEFAULT = controlModeType.EVENT_DRIVEN;

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
	 * The default value of the '{@link #isControlActionsDone() <em>Control Actions Done</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isControlActionsDone()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CONTROL_ACTIONS_DONE_EDEFAULT = false;

                                                                /**
	 * The cached value of the '{@link #isControlActionsDone() <em>Control Actions Done</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isControlActionsDone()
	 * @generated
	 * @ordered
	 */
    protected boolean controlActionsDone = CONTROL_ACTIONS_DONE_EDEFAULT;

                                                                /**
	 * The default value of the '{@link #getMostIterationsDone() <em>Most Iterations Done</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMostIterationsDone()
	 * @generated
	 * @ordered
	 */
    protected static final int MOST_ITERATIONS_DONE_EDEFAULT = 0;

                                                                /**
	 * The cached value of the '{@link #getMostIterationsDone() <em>Most Iterations Done</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMostIterationsDone()
	 * @generated
	 * @ordered
	 */
    protected int mostIterationsDone = MOST_ITERATIONS_DONE_EDEFAULT;

                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
    protected static final algorithmType ALGORITHM_EDEFAULT = algorithmType.NORMAL_SOLVE;

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
	 * The default value of the '{@link #isLastSolutionWasDirect() <em>Last Solution Was Direct</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLastSolutionWasDirect()
	 * @generated
	 * @ordered
	 */
    protected static final boolean LAST_SOLUTION_WAS_DIRECT_EDEFAULT = false;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #isLastSolutionWasDirect() <em>Last Solution Was Direct</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLastSolutionWasDirect()
	 * @generated
	 * @ordered
	 */
    protected boolean lastSolutionWasDirect = LAST_SOLUTION_WAS_DIRECT_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getNodeV() <em>Node V</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNodeV()
	 * @generated
	 * @ordered
	 */
    protected static final DenseDComplexMatrix1D NODE_V_EDEFAULT = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getNodeV() <em>Node V</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNodeV()
	 * @generated
	 * @ordered
	 */
    protected DenseDComplexMatrix1D nodeV = NODE_V_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The default value of the '{@link #getCurrents() <em>Currents</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCurrents()
	 * @generated
	 * @ordered
	 */
    protected static final DenseDComplexMatrix1D CURRENTS_EDEFAULT = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * The cached value of the '{@link #getCurrents() <em>Currents</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCurrents()
	 * @generated
	 * @ordered
	 */
    protected DenseDComplexMatrix1D currents = CURRENTS_EDEFAULT;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SolutionImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return CommonPackage.Literals.SOLUTION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__YEAR, oldYear, year));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isPreserveNodeVoltages() {
		return preserveNodeVoltages;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPreserveNodeVoltages(boolean newPreserveNodeVoltages) {
		boolean oldPreserveNodeVoltages = preserveNodeVoltages;
		preserveNodeVoltages = newPreserveNodeVoltages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__PRESERVE_NODE_VOLTAGES, oldPreserveNodeVoltages, preserveNodeVoltages));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isFrequencyChanged() {
		return frequencyChanged;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFrequencyChanged(boolean newFrequencyChanged) {
		boolean oldFrequencyChanged = frequencyChanged;
		frequencyChanged = newFrequencyChanged;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__FREQUENCY_CHANGED, oldFrequencyChanged, frequencyChanged));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getFrequency() {
		return frequency;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFrequency(double newFrequency) {
		double oldFrequency = frequency;
		frequency = newFrequency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__FREQUENCY, oldFrequency, frequency));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MODE, oldMode, mode));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit getCircuit() {
		if (circuit != null && circuit.eIsProxy()) {
			InternalEObject oldCircuit = (InternalEObject)circuit;
			circuit = (Circuit)eResolveProxy(oldCircuit);
			if (circuit != oldCircuit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__CIRCUIT, oldCircuit, circuit));
			}
		}
		return circuit;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit basicGetCircuit() {
		return circuit;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetCircuit(Circuit newCircuit, NotificationChain msgs) {
		Circuit oldCircuit = circuit;
		circuit = newCircuit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CIRCUIT, oldCircuit, newCircuit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCircuit(Circuit newCircuit) {
		if (newCircuit != circuit) {
			NotificationChain msgs = null;
			if (circuit != null)
				msgs = ((InternalEObject)circuit).eInverseRemove(this, CommonPackage.CIRCUIT__SOLUTION, Circuit.class, msgs);
			if (newCircuit != null)
				msgs = ((InternalEObject)newCircuit).eInverseAdd(this, CommonPackage.CIRCUIT__SOLUTION, Circuit.class, msgs);
			msgs = basicSetCircuit(newCircuit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CIRCUIT, newCircuit, newCircuit));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSolutionAbort() {
		return solutionAbort;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSolutionAbort(boolean newSolutionAbort) {
		boolean oldSolutionAbort = solutionAbort;
		solutionAbort = newSolutionAbort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SOLUTION_ABORT, oldSolutionAbort, solutionAbort));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSolutionInitialised() {
		return solutionInitialised;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSolutionInitialised(boolean newSolutionInitialised) {
		boolean oldSolutionInitialised = solutionInitialised;
		solutionInitialised = newSolutionInitialised;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SOLUTION_INITIALISED, oldSolutionInitialised, solutionInitialised));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSeriesYInvalid() {
		return seriesYInvalid;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSeriesYInvalid(boolean newSeriesYInvalid) {
		boolean oldSeriesYInvalid = seriesYInvalid;
		seriesYInvalid = newSeriesYInvalid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SERIES_YINVALID, oldSeriesYInvalid, seriesYInvalid));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSystemYChanged() {
		return systemYChanged;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSystemYChanged(boolean newSystemYChanged) {
		boolean oldSystemYChanged = systemYChanged;
		systemYChanged = newSystemYChanged;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SYSTEM_YCHANGED, oldSystemYChanged, systemYChanged));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__LOAD_MODEL, oldLoadModel, loadModel));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isVoltageBaseChanged() {
		return voltageBaseChanged;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVoltageBaseChanged(boolean newVoltageBaseChanged) {
		boolean oldVoltageBaseChanged = voltageBaseChanged;
		voltageBaseChanged = newVoltageBaseChanged;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__VOLTAGE_BASE_CHANGED, oldVoltageBaseChanged, voltageBaseChanged));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isHarmonicModel() {
		return harmonicModel;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setHarmonicModel(boolean newHarmonicModel) {
		boolean oldHarmonicModel = harmonicModel;
		harmonicModel = newHarmonicModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__HARMONIC_MODEL, oldHarmonicModel, harmonicModel));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isDynamicModel() {
		return dynamicModel;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDynamicModel(boolean newDynamicModel) {
		boolean oldDynamicModel = dynamicModel;
		dynamicModel = newDynamicModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__DYNAMIC_MODEL, oldDynamicModel, dynamicModel));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isUseAuxillaryCurrents() {
		return useAuxillaryCurrents;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUseAuxillaryCurrents(boolean newUseAuxillaryCurrents) {
		boolean oldUseAuxillaryCurrents = useAuxillaryCurrents;
		useAuxillaryCurrents = newUseAuxillaryCurrents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__USE_AUXILLARY_CURRENTS, oldUseAuxillaryCurrents, useAuxillaryCurrents));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isLoadsNeedUpdating() {
		return loadsNeedUpdating;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLoadsNeedUpdating(boolean newLoadsNeedUpdating) {
		boolean oldLoadsNeedUpdating = loadsNeedUpdating;
		loadsNeedUpdating = newLoadsNeedUpdating;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__LOADS_NEED_UPDATING, oldLoadsNeedUpdating, loadsNeedUpdating));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getIteration() {
		return iteration;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setIteration(int newIteration) {
		int oldIteration = iteration;
		iteration = newIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__ITERATION, oldIteration, iteration));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getMaxIterations() {
		return maxIterations;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMaxIterations(int newMaxIterations) {
		int oldMaxIterations = maxIterations;
		maxIterations = newMaxIterations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MAX_ITERATIONS, oldMaxIterations, maxIterations));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getMaxError() {
		return maxError;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMaxError(double newMaxError) {
		double oldMaxError = maxError;
		maxError = newMaxError;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MAX_ERROR, oldMaxError, maxError));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public double getConvergenceTolerance() {
		return convergenceTolerance;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setConvergenceTolerance(double newConvergenceTolerance) {
		double oldConvergenceTolerance = convergenceTolerance;
		convergenceTolerance = newConvergenceTolerance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONVERGENCE_TOLERANCE, oldConvergenceTolerance, convergenceTolerance));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isConverged() {
		return converged;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setConverged(boolean newConverged) {
		boolean oldConverged = converged;
		converged = newConverged;
		boolean oldConvergedESet = convergedESet;
		convergedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONVERGED, oldConverged, converged, !oldConvergedESet));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetConverged() {
		boolean oldConverged = converged;
		boolean oldConvergedESet = convergedESet;
		converged = CONVERGED_EDEFAULT;
		convergedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.SOLUTION__CONVERGED, oldConverged, CONVERGED_EDEFAULT, oldConvergedESet));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetConverged() {
		return convergedESet;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getControlIteration() {
		return controlIteration;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setControlIteration(int newControlIteration) {
		int oldControlIteration = controlIteration;
		controlIteration = newControlIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONTROL_ITERATION, oldControlIteration, controlIteration));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getMaxControlIterations() {
		return maxControlIterations;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMaxControlIterations(int newMaxControlIterations) {
		int oldMaxControlIterations = maxControlIterations;
		maxControlIterations = newMaxControlIterations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MAX_CONTROL_ITERATIONS, oldMaxControlIterations, maxControlIterations));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONTROL_MODE, oldControlMode, controlMode));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isControlActionsDone() {
		return controlActionsDone;
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setControlActionsDone(boolean newControlActionsDone) {
		boolean oldControlActionsDone = controlActionsDone;
		controlActionsDone = newControlActionsDone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONTROL_ACTIONS_DONE, oldControlActionsDone, controlActionsDone));
	}

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getMostIterationsDone() {
		return mostIterationsDone;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMostIterationsDone(int newMostIterationsDone) {
		int oldMostIterationsDone = mostIterationsDone;
		mostIterationsDone = newMostIterationsDone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MOST_ITERATIONS_DONE, oldMostIterationsDone, mostIterationsDone));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__ALGORITHM, oldAlgorithm, algorithm));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isLastSolutionWasDirect() {
		return lastSolutionWasDirect;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLastSolutionWasDirect(boolean newLastSolutionWasDirect) {
		boolean oldLastSolutionWasDirect = lastSolutionWasDirect;
		lastSolutionWasDirect = newLastSolutionWasDirect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__LAST_SOLUTION_WAS_DIRECT, oldLastSolutionWasDirect, lastSolutionWasDirect));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DenseDComplexMatrix1D getNodeV() {
		return nodeV;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNodeV(DenseDComplexMatrix1D newNodeV) {
		DenseDComplexMatrix1D oldNodeV = nodeV;
		nodeV = newNodeV;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__NODE_V, oldNodeV, nodeV));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DenseDComplexMatrix1D getCurrents() {
		return currents;
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCurrents(DenseDComplexMatrix1D newCurrents) {
		DenseDComplexMatrix1D oldCurrents = currents;
		currents = newCurrents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CURRENTS, oldCurrents, currents));
	}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void updateVBus() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int solveSnap() {
        snapShotInit();
        int totalIterations = 0;
        int result = 0;

        while (!isControlActionsDone() || (getControlIteration() < getMaxControlIterations())) {
            setControlIteration(getControlIteration() + 1);

            // Do circuit solution w/o checking controls.
            result = solveCircuit();

            // Now check controls.
            checkControls();

            // For reporting max iterations per control iteration.
            if (getIteration() > getMostIterationsDone())
                setMostIterationsDone(getIteration());

            totalIterations += getIteration();
        }

        if (!isControlActionsDone() || (getControlIteration() >= getMaxControlIterations())) {
            System.out.println("Warning maximum control iterations exceeded.");
            setSolutionAbort(true);
        }

        System.out.println("Solution done.");

        setIteration(totalIterations);

        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int solveCircuit() {
        if (getLoadModel() == loadModelType.ADMITTANCE) {
            solveDirect();
        } else {
            if (isSystemYChanged())
                getCircuit().buildYMatrix(yBuildOption.WHOLE_MATRIX, true);
            doPowerFlowSolution();
        }
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void solve() {
        if (getMode() == solutionMode.SNAPSHOT) {
            solveSnap();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void restoreNodeVFromVBus() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void solveDirect() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void doPowerFlowSolution() {
        if (isVoltageBaseChanged()) {
            // For convergence test.
            getCircuit().initialiseNodeVBase();
        }

        if (!isSolutionInitialised()) {
            System.out.println("Initialising solution.");
            //solveZeroLoadSnapShot;
            solveYDirect();
            if (isSolutionAbort()) {
                // Initialisation can result in abort.
                return;
            }
        }

        // Set dQdV for Model 3 generators.
        setGenerator_dQdV();

        setSolutionInitialised(true);

        switch (getAlgorithm()) {
            case NORMAL_SOLVE:
                doNormalSolution();
            case NEWTON_SOLVE:
                doNewtonSolution();
        };

        getCircuit().setSolved(isConverged());
        setLastSolutionWasDirect(false);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int solveYDirect() {
        zeroInjectionCurrent(); // Side effect: Allocates InjCurr
        getSourceInjCurrents();
        // Pick up injections in dynamics mode.
        getMachineInjCurrents();

        // Solve with zero injection current.
        solveSystem(getNodeV());

        return 0;
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void zeroInjectionCurrent() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void getSourceInjCurrents() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void getMachineInjCurrents() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void getPCInjCurrents() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int solveSystem(DenseDComplexMatrix1D v) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGenerator_dQdV() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setGeneratorDispRef() {
        Circuit circuit = getCircuit();
        switch (getMode()) {
            case SNAPSHOT:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor());
            case YEARLY:
                circuit.setGeneratorDispatchReference(circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case DAILY:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case DUTYCYCLE:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case DYNAMIC:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor());
            case HARMONIC:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor());
            case MONTE_CARLO1:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor());
            case MONTE_CARLO2:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case MONTE_CARLO3:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case PEAKDAY:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case LOAD_DURATION1:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case LOAD_DURATION2:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor() * circuit.getDefaultHourMult());
            case DIRECT:
                circuit.setGeneratorDispatchReference(circuit.getLoadMultiplier() * circuit.getDefaultGrowthFactor());
            case MONTE_FAULT:
                circuit.setGeneratorDispatchReference(1.0);
            case FAULT_STUDY:
                circuit.setGeneratorDispatchReference(1.0);
            case AUTO_ADD:
                circuit.setGeneratorDispatchReference(circuit.getDefaultGrowthFactor());
//            System.out.println("Unknown solution mode.");
        }
    }

    /**
     * <!-- begin-user-doc -->
     *
     * Normal fixed point solution.
     *
     * Vn+1 = [Y]-1 Injcurr
     *
     * Where Injcurr includes only PC elements (loads, generators, etc.)
     * i.e. the shunt elements.
     *
     * Injcurr are the current injected INTO the NODE (need to reverse current
     * for loads).
     *
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void doNormalSolution() {
        setIteration(0);

        while (!isConverged() && (getIteration() > 1) || (getIteration() < getMaxIterations())) {
            setIteration(getIteration() + 1);

            System.out.println("Solution iteration " + getIteration() + ".");

            // Get injection currents for all PC devices.
            zeroInjectionCurrent();
            getSourceInjCurrents();
            // Get the injection currents from all the power conversion devices and feeders.
            getPCInjCurrents();

            // The above call could change the primitive Y matrix, so have to check.
            if (isSystemYChanged())
                // Do not realloc V, I.
                getCircuit().buildYMatrix(yBuildOption.WHOLE_MATRIX, false);

            if (isUseAuxillaryCurrents())
                addInAuxCurrents(algorithmType.NORMAL_SOLVE);

            // Solve for voltages. Note: nodeV[0] = 0 + 0j always.
            solveSystem(getNodeV());
            setLoadsNeedUpdating(false);
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void doNewtonSolution() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void snapShotInit() {
        setGeneratorDispRef();
        setControlIteration(0);
        setControlActionsDone(false);
        setMostIterationsDone(0);
        setLoadsNeedUpdating(true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void checkControls() {
        if (getControlIteration() < getMaxControlIterations()) {
            if (isConverged()) {
                System.out.println("Control iteration " + getControlIteration() + ".");
                sample_doControlActions();
                checkFaultStatus();
            } else {
                setControlActionsDone(true);
            }
        }

        if (isSystemYChanged()) {
            // Rebuild Y matrix, but V stays the same.
            getCircuit().buildYMatrix(yBuildOption.WHOLE_MATRIX, false);
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean convergenceCheck() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void sample_doControlActions() {
        if (getControlMode() == null) {
            setControlActionsDone(true);
        } else {
            sampleControlDevices();
            doControlActions();

            // This variable lets control devices know the bus list has changed.
            getCircuit().setControl_busNameRedefined(false); // Reset until next change.
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void sampleControlDevices() {
        ControlElement controlDevice = null;
        // TODO: Sample all controls and set action times in control queue.
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void doControlActions() {
        // TODO: Execute the nearest set of control actions time-wise.
        switch (getControlMode()) {
//        	case STATIC:
            case EVENT_DRIVEN:
            case TIME_DRIVEN:
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void checkFaultStatus() {
//        for (int i = 0; i < getFaults.size(); i++) {
//			Fault fault = getFaults.get(i);
//			fault.checkStatus();
//		}
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void addInAuxCurrents(algorithmType algorithm) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.SOLUTION__CIRCUIT:
				if (circuit != null)
					msgs = ((InternalEObject)circuit).eInverseRemove(this, CommonPackage.CIRCUIT__SOLUTION, Circuit.class, msgs);
				return basicSetCircuit((Circuit)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.SOLUTION__CIRCUIT:
				return basicSetCircuit(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.SOLUTION__YEAR:
				return getYear();
			case CommonPackage.SOLUTION__PRESERVE_NODE_VOLTAGES:
				return isPreserveNodeVoltages();
			case CommonPackage.SOLUTION__FREQUENCY_CHANGED:
				return isFrequencyChanged();
			case CommonPackage.SOLUTION__FREQUENCY:
				return getFrequency();
			case CommonPackage.SOLUTION__MODE:
				return getMode();
			case CommonPackage.SOLUTION__CIRCUIT:
				if (resolve) return getCircuit();
				return basicGetCircuit();
			case CommonPackage.SOLUTION__SOLUTION_ABORT:
				return isSolutionAbort();
			case CommonPackage.SOLUTION__SOLUTION_INITIALISED:
				return isSolutionInitialised();
			case CommonPackage.SOLUTION__SERIES_YINVALID:
				return isSeriesYInvalid();
			case CommonPackage.SOLUTION__SYSTEM_YCHANGED:
				return isSystemYChanged();
			case CommonPackage.SOLUTION__LOAD_MODEL:
				return getLoadModel();
			case CommonPackage.SOLUTION__VOLTAGE_BASE_CHANGED:
				return isVoltageBaseChanged();
			case CommonPackage.SOLUTION__HARMONIC_MODEL:
				return isHarmonicModel();
			case CommonPackage.SOLUTION__DYNAMIC_MODEL:
				return isDynamicModel();
			case CommonPackage.SOLUTION__USE_AUXILLARY_CURRENTS:
				return isUseAuxillaryCurrents();
			case CommonPackage.SOLUTION__LOADS_NEED_UPDATING:
				return isLoadsNeedUpdating();
			case CommonPackage.SOLUTION__ITERATION:
				return getIteration();
			case CommonPackage.SOLUTION__MAX_ITERATIONS:
				return getMaxIterations();
			case CommonPackage.SOLUTION__MAX_ERROR:
				return getMaxError();
			case CommonPackage.SOLUTION__CONVERGENCE_TOLERANCE:
				return getConvergenceTolerance();
			case CommonPackage.SOLUTION__CONVERGED:
				return isConverged();
			case CommonPackage.SOLUTION__CONTROL_ITERATION:
				return getControlIteration();
			case CommonPackage.SOLUTION__MAX_CONTROL_ITERATIONS:
				return getMaxControlIterations();
			case CommonPackage.SOLUTION__CONTROL_MODE:
				return getControlMode();
			case CommonPackage.SOLUTION__CONTROL_ACTIONS_DONE:
				return isControlActionsDone();
			case CommonPackage.SOLUTION__MOST_ITERATIONS_DONE:
				return getMostIterationsDone();
			case CommonPackage.SOLUTION__ALGORITHM:
				return getAlgorithm();
			case CommonPackage.SOLUTION__LAST_SOLUTION_WAS_DIRECT:
				return isLastSolutionWasDirect();
			case CommonPackage.SOLUTION__NODE_V:
				return getNodeV();
			case CommonPackage.SOLUTION__CURRENTS:
				return getCurrents();
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
			case CommonPackage.SOLUTION__YEAR:
				setYear((Integer)newValue);
				return;
			case CommonPackage.SOLUTION__PRESERVE_NODE_VOLTAGES:
				setPreserveNodeVoltages((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__FREQUENCY_CHANGED:
				setFrequencyChanged((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__FREQUENCY:
				setFrequency((Double)newValue);
				return;
			case CommonPackage.SOLUTION__MODE:
				setMode((solutionMode)newValue);
				return;
			case CommonPackage.SOLUTION__CIRCUIT:
				setCircuit((Circuit)newValue);
				return;
			case CommonPackage.SOLUTION__SOLUTION_ABORT:
				setSolutionAbort((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__SOLUTION_INITIALISED:
				setSolutionInitialised((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__SERIES_YINVALID:
				setSeriesYInvalid((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__SYSTEM_YCHANGED:
				setSystemYChanged((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__LOAD_MODEL:
				setLoadModel((loadModelType)newValue);
				return;
			case CommonPackage.SOLUTION__VOLTAGE_BASE_CHANGED:
				setVoltageBaseChanged((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__HARMONIC_MODEL:
				setHarmonicModel((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__DYNAMIC_MODEL:
				setDynamicModel((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__USE_AUXILLARY_CURRENTS:
				setUseAuxillaryCurrents((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__LOADS_NEED_UPDATING:
				setLoadsNeedUpdating((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__ITERATION:
				setIteration((Integer)newValue);
				return;
			case CommonPackage.SOLUTION__MAX_ITERATIONS:
				setMaxIterations((Integer)newValue);
				return;
			case CommonPackage.SOLUTION__MAX_ERROR:
				setMaxError((Double)newValue);
				return;
			case CommonPackage.SOLUTION__CONVERGENCE_TOLERANCE:
				setConvergenceTolerance((Double)newValue);
				return;
			case CommonPackage.SOLUTION__CONVERGED:
				setConverged((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__CONTROL_ITERATION:
				setControlIteration((Integer)newValue);
				return;
			case CommonPackage.SOLUTION__MAX_CONTROL_ITERATIONS:
				setMaxControlIterations((Integer)newValue);
				return;
			case CommonPackage.SOLUTION__CONTROL_MODE:
				setControlMode((controlModeType)newValue);
				return;
			case CommonPackage.SOLUTION__CONTROL_ACTIONS_DONE:
				setControlActionsDone((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__MOST_ITERATIONS_DONE:
				setMostIterationsDone((Integer)newValue);
				return;
			case CommonPackage.SOLUTION__ALGORITHM:
				setAlgorithm((algorithmType)newValue);
				return;
			case CommonPackage.SOLUTION__LAST_SOLUTION_WAS_DIRECT:
				setLastSolutionWasDirect((Boolean)newValue);
				return;
			case CommonPackage.SOLUTION__NODE_V:
				setNodeV((DenseDComplexMatrix1D)newValue);
				return;
			case CommonPackage.SOLUTION__CURRENTS:
				setCurrents((DenseDComplexMatrix1D)newValue);
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
			case CommonPackage.SOLUTION__YEAR:
				setYear(YEAR_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__PRESERVE_NODE_VOLTAGES:
				setPreserveNodeVoltages(PRESERVE_NODE_VOLTAGES_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__FREQUENCY_CHANGED:
				setFrequencyChanged(FREQUENCY_CHANGED_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__CIRCUIT:
				setCircuit((Circuit)null);
				return;
			case CommonPackage.SOLUTION__SOLUTION_ABORT:
				setSolutionAbort(SOLUTION_ABORT_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__SOLUTION_INITIALISED:
				setSolutionInitialised(SOLUTION_INITIALISED_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__SERIES_YINVALID:
				setSeriesYInvalid(SERIES_YINVALID_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__SYSTEM_YCHANGED:
				setSystemYChanged(SYSTEM_YCHANGED_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__LOAD_MODEL:
				setLoadModel(LOAD_MODEL_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__VOLTAGE_BASE_CHANGED:
				setVoltageBaseChanged(VOLTAGE_BASE_CHANGED_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__HARMONIC_MODEL:
				setHarmonicModel(HARMONIC_MODEL_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__DYNAMIC_MODEL:
				setDynamicModel(DYNAMIC_MODEL_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__USE_AUXILLARY_CURRENTS:
				setUseAuxillaryCurrents(USE_AUXILLARY_CURRENTS_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__LOADS_NEED_UPDATING:
				setLoadsNeedUpdating(LOADS_NEED_UPDATING_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__ITERATION:
				setIteration(ITERATION_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__MAX_ITERATIONS:
				setMaxIterations(MAX_ITERATIONS_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__MAX_ERROR:
				setMaxError(MAX_ERROR_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__CONVERGENCE_TOLERANCE:
				setConvergenceTolerance(CONVERGENCE_TOLERANCE_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__CONVERGED:
				unsetConverged();
				return;
			case CommonPackage.SOLUTION__CONTROL_ITERATION:
				setControlIteration(CONTROL_ITERATION_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__MAX_CONTROL_ITERATIONS:
				setMaxControlIterations(MAX_CONTROL_ITERATIONS_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__CONTROL_MODE:
				setControlMode(CONTROL_MODE_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__CONTROL_ACTIONS_DONE:
				setControlActionsDone(CONTROL_ACTIONS_DONE_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__MOST_ITERATIONS_DONE:
				setMostIterationsDone(MOST_ITERATIONS_DONE_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__ALGORITHM:
				setAlgorithm(ALGORITHM_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__LAST_SOLUTION_WAS_DIRECT:
				setLastSolutionWasDirect(LAST_SOLUTION_WAS_DIRECT_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__NODE_V:
				setNodeV(NODE_V_EDEFAULT);
				return;
			case CommonPackage.SOLUTION__CURRENTS:
				setCurrents(CURRENTS_EDEFAULT);
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
			case CommonPackage.SOLUTION__YEAR:
				return year != YEAR_EDEFAULT;
			case CommonPackage.SOLUTION__PRESERVE_NODE_VOLTAGES:
				return preserveNodeVoltages != PRESERVE_NODE_VOLTAGES_EDEFAULT;
			case CommonPackage.SOLUTION__FREQUENCY_CHANGED:
				return frequencyChanged != FREQUENCY_CHANGED_EDEFAULT;
			case CommonPackage.SOLUTION__FREQUENCY:
				return frequency != FREQUENCY_EDEFAULT;
			case CommonPackage.SOLUTION__MODE:
				return mode != MODE_EDEFAULT;
			case CommonPackage.SOLUTION__CIRCUIT:
				return circuit != null;
			case CommonPackage.SOLUTION__SOLUTION_ABORT:
				return solutionAbort != SOLUTION_ABORT_EDEFAULT;
			case CommonPackage.SOLUTION__SOLUTION_INITIALISED:
				return solutionInitialised != SOLUTION_INITIALISED_EDEFAULT;
			case CommonPackage.SOLUTION__SERIES_YINVALID:
				return seriesYInvalid != SERIES_YINVALID_EDEFAULT;
			case CommonPackage.SOLUTION__SYSTEM_YCHANGED:
				return systemYChanged != SYSTEM_YCHANGED_EDEFAULT;
			case CommonPackage.SOLUTION__LOAD_MODEL:
				return loadModel != LOAD_MODEL_EDEFAULT;
			case CommonPackage.SOLUTION__VOLTAGE_BASE_CHANGED:
				return voltageBaseChanged != VOLTAGE_BASE_CHANGED_EDEFAULT;
			case CommonPackage.SOLUTION__HARMONIC_MODEL:
				return harmonicModel != HARMONIC_MODEL_EDEFAULT;
			case CommonPackage.SOLUTION__DYNAMIC_MODEL:
				return dynamicModel != DYNAMIC_MODEL_EDEFAULT;
			case CommonPackage.SOLUTION__USE_AUXILLARY_CURRENTS:
				return useAuxillaryCurrents != USE_AUXILLARY_CURRENTS_EDEFAULT;
			case CommonPackage.SOLUTION__LOADS_NEED_UPDATING:
				return loadsNeedUpdating != LOADS_NEED_UPDATING_EDEFAULT;
			case CommonPackage.SOLUTION__ITERATION:
				return iteration != ITERATION_EDEFAULT;
			case CommonPackage.SOLUTION__MAX_ITERATIONS:
				return maxIterations != MAX_ITERATIONS_EDEFAULT;
			case CommonPackage.SOLUTION__MAX_ERROR:
				return maxError != MAX_ERROR_EDEFAULT;
			case CommonPackage.SOLUTION__CONVERGENCE_TOLERANCE:
				return convergenceTolerance != CONVERGENCE_TOLERANCE_EDEFAULT;
			case CommonPackage.SOLUTION__CONVERGED:
				return isSetConverged();
			case CommonPackage.SOLUTION__CONTROL_ITERATION:
				return controlIteration != CONTROL_ITERATION_EDEFAULT;
			case CommonPackage.SOLUTION__MAX_CONTROL_ITERATIONS:
				return maxControlIterations != MAX_CONTROL_ITERATIONS_EDEFAULT;
			case CommonPackage.SOLUTION__CONTROL_MODE:
				return controlMode != CONTROL_MODE_EDEFAULT;
			case CommonPackage.SOLUTION__CONTROL_ACTIONS_DONE:
				return controlActionsDone != CONTROL_ACTIONS_DONE_EDEFAULT;
			case CommonPackage.SOLUTION__MOST_ITERATIONS_DONE:
				return mostIterationsDone != MOST_ITERATIONS_DONE_EDEFAULT;
			case CommonPackage.SOLUTION__ALGORITHM:
				return algorithm != ALGORITHM_EDEFAULT;
			case CommonPackage.SOLUTION__LAST_SOLUTION_WAS_DIRECT:
				return lastSolutionWasDirect != LAST_SOLUTION_WAS_DIRECT_EDEFAULT;
			case CommonPackage.SOLUTION__NODE_V:
				return NODE_V_EDEFAULT == null ? nodeV != null : !NODE_V_EDEFAULT.equals(nodeV);
			case CommonPackage.SOLUTION__CURRENTS:
				return CURRENTS_EDEFAULT == null ? currents != null : !CURRENTS_EDEFAULT.equals(currents);
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
		result.append(" (year: ");
		result.append(year);
		result.append(", preserveNodeVoltages: ");
		result.append(preserveNodeVoltages);
		result.append(", frequencyChanged: ");
		result.append(frequencyChanged);
		result.append(", frequency: ");
		result.append(frequency);
		result.append(", mode: ");
		result.append(mode);
		result.append(", solutionAbort: ");
		result.append(solutionAbort);
		result.append(", solutionInitialised: ");
		result.append(solutionInitialised);
		result.append(", seriesYInvalid: ");
		result.append(seriesYInvalid);
		result.append(", systemYChanged: ");
		result.append(systemYChanged);
		result.append(", loadModel: ");
		result.append(loadModel);
		result.append(", voltageBaseChanged: ");
		result.append(voltageBaseChanged);
		result.append(", harmonicModel: ");
		result.append(harmonicModel);
		result.append(", dynamicModel: ");
		result.append(dynamicModel);
		result.append(", useAuxillaryCurrents: ");
		result.append(useAuxillaryCurrents);
		result.append(", loadsNeedUpdating: ");
		result.append(loadsNeedUpdating);
		result.append(", iteration: ");
		result.append(iteration);
		result.append(", maxIterations: ");
		result.append(maxIterations);
		result.append(", maxError: ");
		result.append(maxError);
		result.append(", convergenceTolerance: ");
		result.append(convergenceTolerance);
		result.append(", converged: ");
		if (convergedESet) result.append(converged); else result.append("<unset>");
		result.append(", controlIteration: ");
		result.append(controlIteration);
		result.append(", maxControlIterations: ");
		result.append(maxControlIterations);
		result.append(", controlMode: ");
		result.append(controlMode);
		result.append(", controlActionsDone: ");
		result.append(controlActionsDone);
		result.append(", mostIterationsDone: ");
		result.append(mostIterationsDone);
		result.append(", algorithm: ");
		result.append(algorithm);
		result.append(", lastSolutionWasDirect: ");
		result.append(lastSolutionWasDirect);
		result.append(", nodeV: ");
		result.append(nodeV);
		result.append(", currents: ");
		result.append(currents);
		result.append(')');
		return result.toString();
	}

} //SolutionImpl
