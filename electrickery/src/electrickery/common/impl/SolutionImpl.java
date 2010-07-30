/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.common.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import cern.colt.function.tdcomplex.DComplexDComplexFunction;
import cern.colt.function.tdcomplex.DComplexRealFunction;
import cern.colt.matrix.tdcomplex.DComplexFactory1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import cern.colt.matrix.tdcomplex.DComplexMatrix2D;
import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.jet.math.tdcomplex.DComplexFunctions;
import electrickery.common.Bus;
import electrickery.common.Circuit;
import electrickery.common.CommonFactory;
import electrickery.common.CommonPackage;
import electrickery.common.Globals;
import electrickery.common.Solution;
import electrickery.common.SolutionAlgs;
import electrickery.common.YMatrix;
import electrickery.common.algorithmType;
import electrickery.common.controlModeType;
import electrickery.common.yBuildOption;
import electrickery.conversion.CurrentSource;
import electrickery.conversion.Generator;
import electrickery.conversion.Load;
import electrickery.conversion.VoltageSource;
import electrickery.conversion.generatorModel;
import electrickery.executive.loadModelType;
import electrickery.executive.solutionMode;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Solution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getY <em>Y</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getYSystem <em>YSystem</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getYSeries <em>YSeries</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getYear <em>Year</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isPreserveNodeVoltages <em>Preserve Node Voltages</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isFrequencyChanged <em>Frequency Changed</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isSolutionInitialised <em>Solution Initialised</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isSeriesYInvalid <em>Series YInvalid</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isSystemYChanged <em>System YChanged</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getLoadModel <em>Load Model</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isVoltageBaseChanged <em>Voltage Base Changed</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isHarmonicModel <em>Harmonic Model</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isDynamicModel <em>Dynamic Model</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isUseAuxillaryCurrents <em>Use Auxillary Currents</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isLoadsNeedUpdating <em>Loads Need Updating</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getIteration <em>Iteration</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getMaxIterations <em>Max Iterations</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getMaxError <em>Max Error</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getConvergenceTolerance <em>Convergence Tolerance</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isConverged <em>Converged</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getControlIteration <em>Control Iteration</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getMaxControlIterations <em>Max Control Iterations</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getControlMode <em>Control Mode</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isControlActionsDone <em>Control Actions Done</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getMostIterationsDone <em>Most Iterations Done</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#isLastSolutionWasDirect <em>Last Solution Was Direct</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getSolutionCount <em>Solution Count</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getNodeV <em>Node V</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getNodeVBase <em>Node VBase</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getErrorSaved <em>Error Saved</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getVMagSaved <em>VMag Saved</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getCurrents <em>Currents</em>}</li>
 *   <li>{@link electrickery.common.impl.SolutionImpl#getAlgorithms <em>Algorithms</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SolutionImpl extends EObjectImpl implements Solution {
    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    protected DComplexMatrix2D y;

    /**
     * The cached value of the '{@link #getYSystem() <em>YSystem</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYSystem()
     * @generated
     * @ordered
     */
    protected DComplexMatrix2D ySystem;

    /**
     * The cached value of the '{@link #getYSeries() <em>YSeries</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYSeries()
     * @generated
     * @ordered
     */
    protected DComplexMatrix2D ySeries;

                /**
     * The default value of the '{@link #getYear() <em>Year</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getYear()
     * @generated
     * @ordered
     */
    protected static final int YEAR_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getYear() <em>Year</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getYear()
     * @generated
     * @ordered
     */
    protected int year = YEAR_EDEFAULT;

    /**
     * The default value of the '{@link #isPreserveNodeVoltages() <em>Preserve Node Voltages</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isPreserveNodeVoltages()
     * @generated
     * @ordered
     */
    protected static final boolean PRESERVE_NODE_VOLTAGES_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPreserveNodeVoltages() <em>Preserve Node Voltages</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isPreserveNodeVoltages()
     * @generated
     * @ordered
     */
    protected boolean preserveNodeVoltages = PRESERVE_NODE_VOLTAGES_EDEFAULT;

    /**
     * The default value of the '{@link #isFrequencyChanged() <em>Frequency Changed</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isFrequencyChanged()
     * @generated
     * @ordered
     */
    protected static final boolean FREQUENCY_CHANGED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isFrequencyChanged() <em>Frequency Changed</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isFrequencyChanged()
     * @generated
     * @ordered
     */
    protected boolean frequencyChanged = FREQUENCY_CHANGED_EDEFAULT;

    /**
     * The default value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFrequency()
     * @generated
     * @ordered
     */
    protected static final double FREQUENCY_EDEFAULT = 60.0;

    /**
     * The cached value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFrequency()
     * @generated
     * @ordered
     */
    protected double frequency = FREQUENCY_EDEFAULT;

    /**
     * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMode()
     * @generated
     * @ordered
     */
    protected static final solutionMode MODE_EDEFAULT = solutionMode.SNAPSHOT;

    /**
     * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMode()
     * @generated
     * @ordered
     */
    protected solutionMode mode = MODE_EDEFAULT;

    /**
     * The cached value of the '{@link #getCircuit() <em>Circuit</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCircuit()
     * @generated
     * @ordered
     */
    protected Circuit circuit;

    /**
     * The default value of the '{@link #isSolutionInitialised() <em>Solution Initialised</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSolutionInitialised()
     * @generated
     * @ordered
     */
    protected static final boolean SOLUTION_INITIALISED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isSolutionInitialised() <em>Solution Initialised</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSolutionInitialised()
     * @generated
     * @ordered
     */
    protected boolean solutionInitialised = SOLUTION_INITIALISED_EDEFAULT;

    /**
     * The default value of the '{@link #isSeriesYInvalid() <em>Series YInvalid</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSeriesYInvalid()
     * @generated
     * @ordered
     */
    protected static final boolean SERIES_YINVALID_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isSeriesYInvalid() <em>Series YInvalid</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSeriesYInvalid()
     * @generated
     * @ordered
     */
    protected boolean seriesYInvalid = SERIES_YINVALID_EDEFAULT;

    /**
     * The default value of the '{@link #isSystemYChanged() <em>System YChanged</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSystemYChanged()
     * @generated
     * @ordered
     */
    protected static final boolean SYSTEM_YCHANGED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isSystemYChanged() <em>System YChanged</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSystemYChanged()
     * @generated
     * @ordered
     */
    protected boolean systemYChanged = SYSTEM_YCHANGED_EDEFAULT;

    /**
     * The default value of the '{@link #getLoadModel() <em>Load Model</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLoadModel()
     * @generated
     * @ordered
     */
    protected static final loadModelType LOAD_MODEL_EDEFAULT = loadModelType.POWERFLOW;

    /**
     * The cached value of the '{@link #getLoadModel() <em>Load Model</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLoadModel()
     * @generated
     * @ordered
     */
    protected loadModelType loadModel = LOAD_MODEL_EDEFAULT;

    /**
     * The default value of the '{@link #isVoltageBaseChanged() <em>Voltage Base Changed</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isVoltageBaseChanged()
     * @generated
     * @ordered
     */
    protected static final boolean VOLTAGE_BASE_CHANGED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isVoltageBaseChanged() <em>Voltage Base Changed</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isVoltageBaseChanged()
     * @generated
     * @ordered
     */
    protected boolean voltageBaseChanged = VOLTAGE_BASE_CHANGED_EDEFAULT;

    /**
     * The default value of the '{@link #isHarmonicModel() <em>Harmonic Model</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isHarmonicModel()
     * @generated
     * @ordered
     */
    protected static final boolean HARMONIC_MODEL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isHarmonicModel() <em>Harmonic Model</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isHarmonicModel()
     * @generated
     * @ordered
     */
    protected boolean harmonicModel = HARMONIC_MODEL_EDEFAULT;

    /**
     * The default value of the '{@link #isDynamicModel() <em>Dynamic Model</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isDynamicModel()
     * @generated
     * @ordered
     */
    protected static final boolean DYNAMIC_MODEL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDynamicModel() <em>Dynamic Model</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isDynamicModel()
     * @generated
     * @ordered
     */
    protected boolean dynamicModel = DYNAMIC_MODEL_EDEFAULT;

    /**
     * The default value of the '{@link #isUseAuxillaryCurrents() <em>Use Auxillary Currents</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isUseAuxillaryCurrents()
     * @generated
     * @ordered
     */
    protected static final boolean USE_AUXILLARY_CURRENTS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseAuxillaryCurrents() <em>Use Auxillary Currents</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isUseAuxillaryCurrents()
     * @generated
     * @ordered
     */
    protected boolean useAuxillaryCurrents = USE_AUXILLARY_CURRENTS_EDEFAULT;

    /**
     * The default value of the '{@link #isLoadsNeedUpdating() <em>Loads Need Updating</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isLoadsNeedUpdating()
     * @generated
     * @ordered
     */
    protected static final boolean LOADS_NEED_UPDATING_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isLoadsNeedUpdating() <em>Loads Need Updating</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isLoadsNeedUpdating()
     * @generated
     * @ordered
     */
    protected boolean loadsNeedUpdating = LOADS_NEED_UPDATING_EDEFAULT;

    /**
     * The default value of the '{@link #getIteration() <em>Iteration</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getIteration()
     * @generated
     * @ordered
     */
    protected static final int ITERATION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getIteration() <em>Iteration</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getIteration()
     * @generated
     * @ordered
     */
    protected int iteration = ITERATION_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxIterations() <em>Max Iterations</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMaxIterations()
     * @generated
     * @ordered
     */
    protected static final int MAX_ITERATIONS_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMaxIterations() <em>Max Iterations</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMaxIterations()
     * @generated
     * @ordered
     */
    protected int maxIterations = MAX_ITERATIONS_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxError() <em>Max Error</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMaxError()
     * @generated
     * @ordered
     */
    protected static final double MAX_ERROR_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMaxError() <em>Max Error</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMaxError()
     * @generated
     * @ordered
     */
    protected double maxError = MAX_ERROR_EDEFAULT;

    /**
     * The default value of the '{@link #getConvergenceTolerance() <em>Convergence Tolerance</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getConvergenceTolerance()
     * @generated
     * @ordered
     */
    protected static final double CONVERGENCE_TOLERANCE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getConvergenceTolerance() <em>Convergence Tolerance</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getConvergenceTolerance()
     * @generated
     * @ordered
     */
    protected double convergenceTolerance = CONVERGENCE_TOLERANCE_EDEFAULT;

    /**
     * The default value of the '{@link #isConverged() <em>Converged</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isConverged()
     * @generated
     * @ordered
     */
    protected static final boolean CONVERGED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isConverged() <em>Converged</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isConverged()
     * @generated
     * @ordered
     */
    protected boolean converged = CONVERGED_EDEFAULT;

    /**
     * This is true if the Converged attribute has been set.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean convergedESet;

    /**
     * The default value of the '{@link #getControlIteration() <em>Control Iteration</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getControlIteration()
     * @generated
     * @ordered
     */
    protected static final int CONTROL_ITERATION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getControlIteration() <em>Control Iteration</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getControlIteration()
     * @generated
     * @ordered
     */
    protected int controlIteration = CONTROL_ITERATION_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxControlIterations() <em>Max Control Iterations</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMaxControlIterations()
     * @generated
     * @ordered
     */
    protected static final int MAX_CONTROL_ITERATIONS_EDEFAULT = 10;

    /**
     * The cached value of the '{@link #getMaxControlIterations() <em>Max Control Iterations</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMaxControlIterations()
     * @generated
     * @ordered
     */
    protected int maxControlIterations = MAX_CONTROL_ITERATIONS_EDEFAULT;

    /**
     * The default value of the '{@link #getControlMode() <em>Control Mode</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getControlMode()
     * @generated
     * @ordered
     */
    protected static final controlModeType CONTROL_MODE_EDEFAULT = controlModeType.EVENT_DRIVEN;

    /**
     * The cached value of the '{@link #getControlMode() <em>Control Mode</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getControlMode()
     * @generated
     * @ordered
     */
    protected controlModeType controlMode = CONTROL_MODE_EDEFAULT;

    /**
     * The default value of the '{@link #isControlActionsDone() <em>Control Actions Done</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isControlActionsDone()
     * @generated
     * @ordered
     */
    protected static final boolean CONTROL_ACTIONS_DONE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isControlActionsDone() <em>Control Actions Done</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isControlActionsDone()
     * @generated
     * @ordered
     */
    protected boolean controlActionsDone = CONTROL_ACTIONS_DONE_EDEFAULT;

    /**
     * The default value of the '{@link #getMostIterationsDone() <em>Most Iterations Done</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMostIterationsDone()
     * @generated
     * @ordered
     */
    protected static final int MOST_ITERATIONS_DONE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMostIterationsDone() <em>Most Iterations Done</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMostIterationsDone()
     * @generated
     * @ordered
     */
    protected int mostIterationsDone = MOST_ITERATIONS_DONE_EDEFAULT;

    /**
     * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAlgorithm()
     * @generated
     * @ordered
     */
    protected static final algorithmType ALGORITHM_EDEFAULT = algorithmType.NORMAL_SOLVE;

    /**
     * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAlgorithm()
     * @generated
     * @ordered
     */
    protected algorithmType algorithm = ALGORITHM_EDEFAULT;

    /**
     * The default value of the '{@link #isLastSolutionWasDirect() <em>Last Solution Was Direct</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isLastSolutionWasDirect()
     * @generated
     * @ordered
     */
    protected static final boolean LAST_SOLUTION_WAS_DIRECT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isLastSolutionWasDirect() <em>Last Solution Was Direct</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isLastSolutionWasDirect()
     * @generated
     * @ordered
     */
    protected boolean lastSolutionWasDirect = LAST_SOLUTION_WAS_DIRECT_EDEFAULT;

    /**
     * The default value of the '{@link #getSolutionCount() <em>Solution Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSolutionCount()
     * @generated
     * @ordered
     */
    protected static final int SOLUTION_COUNT_EDEFAULT = 0;

                /**
     * The cached value of the '{@link #getSolutionCount() <em>Solution Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSolutionCount()
     * @generated
     * @ordered
     */
    protected int solutionCount = SOLUTION_COUNT_EDEFAULT;

                /**
     * The cached value of the '{@link #getNodeV() <em>Node V</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNodeV()
     * @generated
     * @ordered
     */
    protected DComplexMatrix1D nodeV;

    /**
     * The cached value of the '{@link #getNodeVBase() <em>Node VBase</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodeVBase()
     * @generated
     * @ordered
     */
    protected DoubleMatrix1D nodeVBase;

                                                                /**
     * The cached value of the '{@link #getErrorSaved() <em>Error Saved</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getErrorSaved()
     * @generated
     * @ordered
     */
    protected DoubleMatrix1D errorSaved;

                                                                /**
     * The cached value of the '{@link #getVMagSaved() <em>VMag Saved</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVMagSaved()
     * @generated
     * @ordered
     */
    protected DoubleMatrix1D vMagSaved;

                /**
     * The cached value of the '{@link #getCurrents() <em>Currents</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCurrents()
     * @generated
     * @ordered
     */
    protected DComplexMatrix1D currents;

    /**
     * The cached value of the '{@link #getAlgorithms() <em>Algorithms</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAlgorithms()
     * @generated
     * @ordered
     */
    protected SolutionAlgs algorithms;

    /*
     * Array of delta V for Newton iteration.
     */
    private DComplexMatrix1D dV;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SolutionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
    public DComplexMatrix2D getY() {
        if (y != null && ((EObject)y).eIsProxy()) {
            InternalEObject oldY = (InternalEObject)y;
            y = (DComplexMatrix2D)eResolveProxy(oldY);
            if (y != oldY) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__Y, oldY, y));
            }
        }
        return y;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix2D basicGetY() {
        return y;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setY(DComplexMatrix2D newY) {
        DComplexMatrix2D oldY = y;
        y = newY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__Y, oldY, y));
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix2D getYSystem() {
        if (ySystem != null && ((EObject)ySystem).eIsProxy()) {
            InternalEObject oldYSystem = (InternalEObject)ySystem;
            ySystem = (DComplexMatrix2D)eResolveProxy(oldYSystem);
            if (ySystem != oldYSystem) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__YSYSTEM, oldYSystem, ySystem));
            }
        }
        return ySystem;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix2D basicGetYSystem() {
        return ySystem;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setYSystem(DComplexMatrix2D newYSystem) {
        DComplexMatrix2D oldYSystem = ySystem;
        ySystem = newYSystem;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__YSYSTEM, oldYSystem, ySystem));
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix2D getYSeries() {
        if (ySeries != null && ((EObject)ySeries).eIsProxy()) {
            InternalEObject oldYSeries = (InternalEObject)ySeries;
            ySeries = (DComplexMatrix2D)eResolveProxy(oldYSeries);
            if (ySeries != oldYSeries) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__YSERIES, oldYSeries, ySeries));
            }
        }
        return ySeries;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix2D basicGetYSeries() {
        return ySeries;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setYSeries(DComplexMatrix2D newYSeries) {
        DComplexMatrix2D oldYSeries = ySeries;
        ySeries = newYSeries;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__YSERIES, oldYSeries, ySeries));
    }

                /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getYear() {
        return year;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setYear(int newYear) {
        int oldYear = year;
        year = newYear;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__YEAR, oldYear, year));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isPreserveNodeVoltages() {
        return preserveNodeVoltages;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setPreserveNodeVoltages(boolean newPreserveNodeVoltages) {
        boolean oldPreserveNodeVoltages = preserveNodeVoltages;
        preserveNodeVoltages = newPreserveNodeVoltages;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__PRESERVE_NODE_VOLTAGES, oldPreserveNodeVoltages, preserveNodeVoltages));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isFrequencyChanged() {
        return frequencyChanged;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFrequencyChanged(boolean newFrequencyChanged) {
        boolean oldFrequencyChanged = frequencyChanged;
        frequencyChanged = newFrequencyChanged;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__FREQUENCY_CHANGED, oldFrequencyChanged, frequencyChanged));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setFrequency(double newFrequency) {
        double oldFrequency = frequency;
        frequency = newFrequency;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__FREQUENCY, oldFrequency, frequency));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public solutionMode getMode() {
        return mode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setMode(solutionMode newMode) {
        solutionMode oldMode = mode;
        mode = newMode == null ? MODE_EDEFAULT : newMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MODE, oldMode, mode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Circuit basicGetCircuit() {
        return circuit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSolutionInitialised() {
        return solutionInitialised;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSolutionInitialised(boolean newSolutionInitialised) {
        boolean oldSolutionInitialised = solutionInitialised;
        solutionInitialised = newSolutionInitialised;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SOLUTION_INITIALISED, oldSolutionInitialised, solutionInitialised));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSeriesYInvalid() {
        return seriesYInvalid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSeriesYInvalid(boolean newSeriesYInvalid) {
        boolean oldSeriesYInvalid = seriesYInvalid;
        seriesYInvalid = newSeriesYInvalid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SERIES_YINVALID, oldSeriesYInvalid, seriesYInvalid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSystemYChanged() {
        return systemYChanged;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSystemYChanged(boolean newSystemYChanged) {
        boolean oldSystemYChanged = systemYChanged;
        systemYChanged = newSystemYChanged;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SYSTEM_YCHANGED, oldSystemYChanged, systemYChanged));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public loadModelType getLoadModel() {
        return loadModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLoadModel(loadModelType newLoadModel) {
        loadModelType oldLoadModel = loadModel;
        loadModel = newLoadModel == null ? LOAD_MODEL_EDEFAULT : newLoadModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__LOAD_MODEL, oldLoadModel, loadModel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isVoltageBaseChanged() {
        return voltageBaseChanged;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setVoltageBaseChanged(boolean newVoltageBaseChanged) {
        boolean oldVoltageBaseChanged = voltageBaseChanged;
        voltageBaseChanged = newVoltageBaseChanged;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__VOLTAGE_BASE_CHANGED, oldVoltageBaseChanged, voltageBaseChanged));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isHarmonicModel() {
        return harmonicModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setHarmonicModel(boolean newHarmonicModel) {
        boolean oldHarmonicModel = harmonicModel;
        harmonicModel = newHarmonicModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__HARMONIC_MODEL, oldHarmonicModel, harmonicModel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isDynamicModel() {
        return dynamicModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDynamicModel(boolean newDynamicModel) {
        boolean oldDynamicModel = dynamicModel;
        dynamicModel = newDynamicModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__DYNAMIC_MODEL, oldDynamicModel, dynamicModel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isUseAuxillaryCurrents() {
        return useAuxillaryCurrents;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setUseAuxillaryCurrents(boolean newUseAuxillaryCurrents) {
        boolean oldUseAuxillaryCurrents = useAuxillaryCurrents;
        useAuxillaryCurrents = newUseAuxillaryCurrents;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__USE_AUXILLARY_CURRENTS, oldUseAuxillaryCurrents, useAuxillaryCurrents));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isLoadsNeedUpdating() {
        return loadsNeedUpdating;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLoadsNeedUpdating(boolean newLoadsNeedUpdating) {
        boolean oldLoadsNeedUpdating = loadsNeedUpdating;
        loadsNeedUpdating = newLoadsNeedUpdating;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__LOADS_NEED_UPDATING, oldLoadsNeedUpdating, loadsNeedUpdating));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getIteration() {
        return iteration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setIteration(int newIteration) {
        int oldIteration = iteration;
        iteration = newIteration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__ITERATION, oldIteration, iteration));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setMaxIterations(int newMaxIterations) {
        int oldMaxIterations = maxIterations;
        maxIterations = newMaxIterations;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MAX_ITERATIONS, oldMaxIterations, maxIterations));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public double getMaxError() {
        return maxError;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setMaxError(double newMaxError) {
        double oldMaxError = maxError;
        maxError = newMaxError;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MAX_ERROR, oldMaxError, maxError));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public double getConvergenceTolerance() {
        return convergenceTolerance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setConvergenceTolerance(double newConvergenceTolerance) {
        double oldConvergenceTolerance = convergenceTolerance;
        convergenceTolerance = newConvergenceTolerance;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONVERGENCE_TOLERANCE, oldConvergenceTolerance, convergenceTolerance));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isConverged() {
        return converged;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetConverged() {
        return convergedESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getControlIteration() {
        return controlIteration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setControlIteration(int newControlIteration) {
        int oldControlIteration = controlIteration;
        controlIteration = newControlIteration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONTROL_ITERATION, oldControlIteration, controlIteration));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getMaxControlIterations() {
        return maxControlIterations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setMaxControlIterations(int newMaxControlIterations) {
        int oldMaxControlIterations = maxControlIterations;
        maxControlIterations = newMaxControlIterations;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MAX_CONTROL_ITERATIONS, oldMaxControlIterations, maxControlIterations));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public controlModeType getControlMode() {
        return controlMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setControlMode(controlModeType newControlMode) {
        controlModeType oldControlMode = controlMode;
        controlMode = newControlMode == null ? CONTROL_MODE_EDEFAULT : newControlMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONTROL_MODE, oldControlMode, controlMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isControlActionsDone() {
        return controlActionsDone;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setControlActionsDone(boolean newControlActionsDone) {
        boolean oldControlActionsDone = controlActionsDone;
        controlActionsDone = newControlActionsDone;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CONTROL_ACTIONS_DONE, oldControlActionsDone, controlActionsDone));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getMostIterationsDone() {
        return mostIterationsDone;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setMostIterationsDone(int newMostIterationsDone) {
        int oldMostIterationsDone = mostIterationsDone;
        mostIterationsDone = newMostIterationsDone;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__MOST_ITERATIONS_DONE, oldMostIterationsDone, mostIterationsDone));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public algorithmType getAlgorithm() {
        return algorithm;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setAlgorithm(algorithmType newAlgorithm) {
        algorithmType oldAlgorithm = algorithm;
        algorithm = newAlgorithm == null ? ALGORITHM_EDEFAULT : newAlgorithm;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__ALGORITHM, oldAlgorithm, algorithm));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isLastSolutionWasDirect() {
        return lastSolutionWasDirect;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
    public int getSolutionCount() {
        return solutionCount;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSolutionCount(int newSolutionCount) {
        int oldSolutionCount = solutionCount;
        solutionCount = newSolutionCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__SOLUTION_COUNT, oldSolutionCount, solutionCount));
    }

                /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix1D getNodeV() {
        if (nodeV != null && ((EObject)nodeV).eIsProxy()) {
            InternalEObject oldNodeV = (InternalEObject)nodeV;
            nodeV = (DComplexMatrix1D)eResolveProxy(oldNodeV);
            if (nodeV != oldNodeV) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__NODE_V, oldNodeV, nodeV));
            }
        }
        return nodeV;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix1D basicGetNodeV() {
        return nodeV;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setNodeV(DComplexMatrix1D newNodeV) {
        DComplexMatrix1D oldNodeV = nodeV;
        nodeV = newNodeV;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__NODE_V, oldNodeV, nodeV));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix1D getNodeVBase() {
        if (nodeVBase != null && ((EObject)nodeVBase).eIsProxy()) {
            InternalEObject oldNodeVBase = (InternalEObject)nodeVBase;
            nodeVBase = (DoubleMatrix1D)eResolveProxy(oldNodeVBase);
            if (nodeVBase != oldNodeVBase) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__NODE_VBASE, oldNodeVBase, nodeVBase));
            }
        }
        return nodeVBase;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix1D basicGetNodeVBase() {
        return nodeVBase;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodeVBase(DoubleMatrix1D newNodeVBase) {
        DoubleMatrix1D oldNodeVBase = nodeVBase;
        nodeVBase = newNodeVBase;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__NODE_VBASE, oldNodeVBase, nodeVBase));
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix1D getErrorSaved() {
        if (errorSaved != null && ((EObject)errorSaved).eIsProxy()) {
            InternalEObject oldErrorSaved = (InternalEObject)errorSaved;
            errorSaved = (DoubleMatrix1D)eResolveProxy(oldErrorSaved);
            if (errorSaved != oldErrorSaved) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__ERROR_SAVED, oldErrorSaved, errorSaved));
            }
        }
        return errorSaved;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix1D basicGetErrorSaved() {
        return errorSaved;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setErrorSaved(DoubleMatrix1D newErrorSaved) {
        DoubleMatrix1D oldErrorSaved = errorSaved;
        errorSaved = newErrorSaved;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__ERROR_SAVED, oldErrorSaved, errorSaved));
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix1D getVMagSaved() {
        if (vMagSaved != null && ((EObject)vMagSaved).eIsProxy()) {
            InternalEObject oldVMagSaved = (InternalEObject)vMagSaved;
            vMagSaved = (DoubleMatrix1D)eResolveProxy(oldVMagSaved);
            if (vMagSaved != oldVMagSaved) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__VMAG_SAVED, oldVMagSaved, vMagSaved));
            }
        }
        return vMagSaved;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMatrix1D basicGetVMagSaved() {
        return vMagSaved;
    }

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVMagSaved(DoubleMatrix1D newVMagSaved) {
        DoubleMatrix1D oldVMagSaved = vMagSaved;
        vMagSaved = newVMagSaved;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__VMAG_SAVED, oldVMagSaved, vMagSaved));
    }

                /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix1D getCurrents() {
        if (currents != null && ((EObject)currents).eIsProxy()) {
            InternalEObject oldCurrents = (InternalEObject)currents;
            currents = (DComplexMatrix1D)eResolveProxy(oldCurrents);
            if (currents != oldCurrents) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__CURRENTS, oldCurrents, currents));
            }
        }
        return currents;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DComplexMatrix1D basicGetCurrents() {
        return currents;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setCurrents(DComplexMatrix1D newCurrents) {
        DComplexMatrix1D oldCurrents = currents;
        currents = newCurrents;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__CURRENTS, oldCurrents, currents));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SolutionAlgs getAlgorithms() {
        if (algorithms != null && algorithms.eIsProxy()) {
            InternalEObject oldAlgorithms = (InternalEObject)algorithms;
            algorithms = (SolutionAlgs)eResolveProxy(oldAlgorithms);
            if (algorithms != oldAlgorithms) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.SOLUTION__ALGORITHMS, oldAlgorithms, algorithms));
            }
        }
        return algorithms;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SolutionAlgs basicGetAlgorithms() {
        return algorithms;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setAlgorithms(SolutionAlgs newAlgorithms) {
        SolutionAlgs oldAlgorithms = algorithms;
        algorithms = newAlgorithms;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SOLUTION__ALGORITHMS, oldAlgorithms, algorithms));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void solve() {
        Globals globals = getCircuit().getExecutive().getGlobals();

        getCircuit().setSolved(false);
        globals.setSolutionWasAttempted(true);

        // TODO: Initialise progress monitor.

        // Check of some special conditions that must be met before executing solutions.
        if (getCircuit().getEmergMinVolts() >= getCircuit().getNormalMinVolts()) {
            System.out.println("Emergency Min Voltage Must Be Less Than Normal Min Voltage!");
            return;
        }

         if (globals.isSolutionAbort()) {
//             globals.setGlobalResult("Solution aborted.");
//             globals.setCmdResult(SOLUTION_ABORT);
//             globals.setErrorNumber(globals.getCmdResult());
         }

        try {
            if (getYear() == 0) {
                getCircuit().setDefaultGrowthFactor(1.0);
            } else {
                getCircuit().setDefaultGrowthFactor(Math.pow(getCircuit().getDefaultGrowthRate(), getYear() - 1));
            }

            switch (getMode()) {
            case SNAPSHOT:
                solveSnap();
            case YEARLY:
                getAlgorithms().solveYearly();
            case DAILY:
                getAlgorithms().solveDaily();
            case DUTYCYCLE:
                getAlgorithms().solveDuty();
            case DYNAMIC:
                getAlgorithms().solveDynamic();
            case MONTE_CARLO1:
                getAlgorithms().solveMonte1();
            case MONTE_CARLO2:
                getAlgorithms().solveMonte2();
            case MONTE_CARLO3:
                getAlgorithms().solveMonte3();
            case PEAKDAY:
                getAlgorithms().solvePeakDay();
            case LOAD_DURATION1:
                getAlgorithms().solveLD1();
            case LOAD_DURATION2:
                getAlgorithms().solveLD2();
            case DIRECT:
                solveDirect();
            case MONTE_FAULT:
                getAlgorithms().solveMonteFault();
            case FAULT_STUDY:
                getAlgorithms().solveFaultStudy();
            case AUTO_ADD:
                // getCircuit().getAutoAddObj().solve();
            case HARMONIC:
                getAlgorithms().solveHarmonic();
            default:
                throw new UnsupportedOperationException();
            }
        } catch (Exception e) {
            System.out.println("Error Encountered in Solve: " + e.getMessage());
            globals.setSolutionAbort(true);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
            getCircuit().getExecutive().getGlobals().setSolutionAbort(true);
        }

        System.out.println("Solution done.");

        setIteration(totalIterations);

        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void snapShotInit() {
        setGeneratorDispRef();
        setControlIteration(0);
        setControlActionsDone(false);
        setMostIterationsDone(0);
        // Force the loads to update at least once.
        setLoadsNeedUpdating(true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
            // System.out.println("Unknown solution mode.");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public int solveCircuit() {
        if (getLoadModel() == loadModelType.ADMITTANCE) {
            solveDirect();
        } else {
            if (isSystemYChanged()) {
                YMatrix y = CommonFactory.eINSTANCE.createYMatrix();
                y.setCircuit(getCircuit());
                y.buildYMatrix(yBuildOption.WHOLE_MATRIX, true);
            }
            doPowerFlowSolution();
        }
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * Solve for now once, direct solution.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int solveDirect() {
        // Force possible update of loads and generators.
        setLoadsNeedUpdating(true);

        if (isSystemYChanged()) {
            YMatrix y = CommonFactory.eINSTANCE.createYMatrix();
            y.setCircuit(getCircuit());
            y.buildYMatrix(yBuildOption.WHOLE_MATRIX, true); // Side Effect: Allocates V
        }

        setSolutionCount(getSolutionCount() + 1); // Unique number for this solution.

        zeroInjectionCurrent(); // Side Effect: Allocates InjCurr.
        getSourceInjCurrents();
        getMachineInjCurrents(); // Need this in dynamics mode to pick up injections.

        if (solveSystem(getNodeV()) == 1) { // Solve with Zero injection current.
            getCircuit().setSolved(true);
            setConverged(true);
        }

        setIteration(1);
        setLastSolutionWasDirect(true);

        return 0;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void doPowerFlowSolution() {
        if (isVoltageBaseChanged()) {
            // For convergence test.
            getCircuit().initialiseNodeVBase();
        }

        if (!isSolutionInitialised()) {
            System.out.println("Initialising solution.");
            // solveZeroLoadSnapShot;
            solveYDirect();
             if (getCircuit().getExecutive().getGlobals().isSolutionAbort()) {
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
        }

        getCircuit().setSolved(isConverged());
        setLastSolutionWasDirect(false);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int solveSystem(DComplexMatrix1D v) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * Solve without load for initialization purposes.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int solveZeroLoadSnapShot() {
        if (isSystemYChanged() || isSeriesYInvalid()) {
            YMatrix y = CommonFactory.eINSTANCE.createYMatrix();
            y.setCircuit(getCircuit());
            y.buildYMatrix(yBuildOption.SERIES_ONLY, true); // Side Effect: Allocates V
        }

        setSolutionCount(getSolutionCount() + 1); // Unique number for this solution.

        zeroInjectionCurrent(); // Side Effect: Allocates InjCurr
        getSourceInjCurrents(); // Vsource and Isource only.

        // Make the series Y matrix the active matrix.
        if (getYSeries() == null)
            System.err.println("Series Y matrix not built yet in SolveZeroLoadSnapshot.");

        setY(getYSeries());

//        if (getCircuit().isLogEvents())
//            log("Solve Sparse Set ZeroLoadSnapshot");

        solveSystem(getNodeV()); // Also sets voltages in radial part of the circuit if radial solution.

        // Reset the main system Y as the solution matrix.
        if (getYSystem() != null && !getCircuit().getExecutive().getGlobals().isSolutionAbort())
            setY(getYSystem());

        return 0;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
            YMatrix y = CommonFactory.eINSTANCE.createYMatrix();
            y.setCircuit(getCircuit());
            y.buildYMatrix(yBuildOption.WHOLE_MATRIX, false);
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean convergenceCheck() {
        DComplexRealFunction abs = DComplexFunctions.abs;

        // Base convergence on voltage magnitude.
        setMaxError(0.0);

        for (int i = 0; i < getCircuit().getNumNodes(); i++) {
            double Vmag = abs.apply(getNodeV().get(i));

            // If base specified, use it; otherwise go on present magnitude.
            if (getNodeVBase().get(i) > 0.0) {
                getErrorSaved().set(i, Math.abs(Vmag - getVMagSaved().get(i)) / getNodeVBase().get(i));
            } else if (Vmag != 0.0) {
                getErrorSaved().set(i, Math.abs(1.0 - getVMagSaved().get(i)) / Vmag);
            }

            getVMagSaved().set(i, Vmag); // for next go-around

            setMaxError(Math.max(getMaxError(), getErrorSaved().get(i)));
        }

        if (getMaxError() < getConvergenceTolerance()) {
            setConverged(true);
        } else {
            setConverged(false);
        }

        return isConverged();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void sampleControlDevices() {
        // ControlElement controlDevice = null;
        // TODO: Sample all controls and set action times in control queue.
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void doControlActions() {
        // TODO: Execute the nearest set of control actions time-wise.
        switch (getControlMode()) {
        // case STATIC:
        case EVENT_DRIVEN:
        case TIME_DRIVEN:
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void checkFaultStatus() {
        // for (int i = 0; i < getFaults.size(); i++) {
        // Fault fault = getFaults.get(i);
        // fault.checkStatus();
        // }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public void updateVBus() {
        // Save present solution vector values to buses.
        for (int i = 0; i < getCircuit().getBuses().size(); i++) {
            Bus bus = getCircuit().getBuses().get(i);
            if (bus.getVBus() != null)
                for (int j = 0; j < bus.getNumNodesThisBus(); j++)
                    bus.getVBus().setQuick(j, nodeV.get(bus.getRef(j)));
        }
    }

    /**
     * Normal fixed point solution.
     *
     * Vn+1 = [Y]-1 Injcurr
     *
     * Where Injcurr includes only PC elements (loads, generators, etc.) i.e. the shunt elements.
     *
     * Injcurr are the current injected INTO the NODE (need to reverse current for loads).
     */
    private void doNormalSolution() {
        setIteration(0);

        while (!convergenceCheck() && (getIteration() > 1) || (getIteration() < getMaxIterations())) {
            setIteration(getIteration() + 1);

            System.out.println("Solution iteration " + getIteration() + ".");

            // Get injection currents for all PC devices.
            zeroInjectionCurrent();
            getSourceInjCurrents();
            // Get the injection currents from all the power conversion devices and feeders.
            getPCInjCurrents();

            // The above call could change the primitive Y matrix, so have to check.
            if (isSystemYChanged()) {
                // Do not realloc V, I.
                YMatrix y = CommonFactory.eINSTANCE.createYMatrix();
                y.setCircuit(getCircuit());
                y.buildYMatrix(yBuildOption.WHOLE_MATRIX, false);
            }

            if (isUseAuxillaryCurrents())
                addInAuxCurrents(algorithmType.NORMAL_SOLVE);

            // Solve for voltages. Note: nodeV[0] = 0 + 0j always.
            solveSystem(getNodeV());
            setLoadsNeedUpdating(false);
        }
    }

    /**
     * Newton Iteration
     *
     * Vn+1 =  Vn - [Y]-1 Termcurr
     *
     * Where Termcurr includes currents from all elements and we are
     * attempting to get the  currents to sum to zero at all nodes.
     *
     * Termcurr is the sum of all currents going INTO THE TERMINALS of
     * the elements.
     *
     * For PD Elements, Termcurr = Yprim*V
     *
     * For Loads, Termcurr = (Sload/V)*
     * For Generators, Termcurr = -(Sgen/V)*
     */
    private void doNewtonSolution() {
        // Make sure this is always big enough.
        dV = DComplexFactory1D.dense.make(getCircuit().getNumNodes());

        if (getControlIteration() == 1)
            // Update the load multipliers for this solution.
            getPCInjCurrents();

        setIteration(0);
        while (!convergenceCheck() && (getIteration() > 1) || (getIteration() < getMaxIterations())) {
            setIteration(getIteration() + 1);
            setSolutionCount(getSolutionCount() + 1); // SumAllCurrents Uses ITerminal  So must force a recalc.

            // Get sum of currents at all nodes for all  devices
            zeroInjectionCurrent();
            sumAllCurrents();

            // Call to current calc could change YPrim for some devices.
            if (isSystemYChanged()) {
                // Do not realloc V, I.
                YMatrix y = CommonFactory.eINSTANCE.createYMatrix();
                y.setCircuit(getCircuit());
                y.buildYMatrix(yBuildOption.WHOLE_MATRIX, false); // Does not realloc V, I.
            }

            if (isUseAuxillaryCurrents())
                addInAuxCurrents(algorithmType.NEWTON_SOLVE);

            // Solve for change in voltages.
            solveSystem(dV);

            setLoadsNeedUpdating(false);
            // Compute new guess at voltages.
            nodeV.assign(dV, DComplexFunctions.minus); // 0 node is always 0
        }
    }

    private void sumAllCurrents() {

    }

    private void addInAuxCurrents(algorithmType solveType) {

    }

    private void setGenerator_dQdV() {
        boolean didOne = false;

        // Save the generator dispatch level and set on high enough to turn all generators on.
        double genDispSave = getCircuit().getGeneratorDispatchReference();
        getCircuit().setGeneratorDispatchReference(1000.0);

        for (Generator generator : getCircuit().getGenerators()) {

            if (generator.isEnabled()) {
                // For PV generator models only.
                if (generator.getModel() == generatorModel.CONSTANT_KW_AND_KV) {
                    generator.initDQDVCalc();

                    // Solve at base var setting.
                    setIteration(0);
                    while (!convergenceCheck() || getIteration() <= getMaxIterations()) {
                        setIteration(getIteration() + 1);
                        zeroInjectionCurrent();
                        getSourceInjCurrents();
                        generator.injCurrents(); // Get generator currents with nominal vars.
                        solveSystem(getNodeV());
                    }

                    generator.rememberQV(); // Remember Q and V.
                    generator.bumpUpQ();

                    // Solve after changing vars.
                    setIteration(0);
                    while (!convergenceCheck() || getIteration() <= getMaxIterations()) {
                        setIteration(getIteration() + 1);
                        zeroInjectionCurrent();
                        getSourceInjCurrents();
                        generator.injCurrents(); // Get generator currents with nominal vars.
                        solveSystem(getNodeV());
                    }

                    generator.calc_dQdV(); // Based on remembered Q and V and present values of same.
                    generator.resetStartPoint();

                    didOne = true;
                }
            }
        }

        // Restore generator dispatch reference.
        getCircuit().setGeneratorDispatchReference(genDispSave);

        try {
            if (didOne) // Reset initial solution.
                solveZeroLoadSnapShot();
        } catch (Exception e) {
//            throw new SolveException("Aborting.");
        }
    }

    private void zeroInjectionCurrent() {

    }

    /**
     * Add in the contributions of all source type elements to the global solution vector InjCurr.
     */
    private void getSourceInjCurrents() {
        // Use NodeRef to add current into InjCurr array.
        for (VoltageSource vsource : getCircuit().getVoltageSources())
            vsource.injCurrents();
        for (CurrentSource isource : getCircuit().getCurrentSources())
            isource.injCurrents();

        if (isHarmonicModel()) { // Pick up generators and Loads, too.
            for (Generator generator : getCircuit().getGenerators())
                generator.injCurrents();
            for (Load load : getCircuit().getLoads())
                load.injCurrents();
        }
    }

    private void getMachineInjCurrents() {

    }

    private void getPCInjCurrents() {

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CommonPackage.SOLUTION__Y:
                if (resolve) return getY();
                return basicGetY();
            case CommonPackage.SOLUTION__YSYSTEM:
                if (resolve) return getYSystem();
                return basicGetYSystem();
            case CommonPackage.SOLUTION__YSERIES:
                if (resolve) return getYSeries();
                return basicGetYSeries();
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
            case CommonPackage.SOLUTION__SOLUTION_COUNT:
                return getSolutionCount();
            case CommonPackage.SOLUTION__NODE_V:
                if (resolve) return getNodeV();
                return basicGetNodeV();
            case CommonPackage.SOLUTION__NODE_VBASE:
                if (resolve) return getNodeVBase();
                return basicGetNodeVBase();
            case CommonPackage.SOLUTION__ERROR_SAVED:
                if (resolve) return getErrorSaved();
                return basicGetErrorSaved();
            case CommonPackage.SOLUTION__VMAG_SAVED:
                if (resolve) return getVMagSaved();
                return basicGetVMagSaved();
            case CommonPackage.SOLUTION__CURRENTS:
                if (resolve) return getCurrents();
                return basicGetCurrents();
            case CommonPackage.SOLUTION__ALGORITHMS:
                if (resolve) return getAlgorithms();
                return basicGetAlgorithms();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case CommonPackage.SOLUTION__Y:
                setY((DComplexMatrix2D)newValue);
                return;
            case CommonPackage.SOLUTION__YSYSTEM:
                setYSystem((DComplexMatrix2D)newValue);
                return;
            case CommonPackage.SOLUTION__YSERIES:
                setYSeries((DComplexMatrix2D)newValue);
                return;
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
            case CommonPackage.SOLUTION__SOLUTION_COUNT:
                setSolutionCount((Integer)newValue);
                return;
            case CommonPackage.SOLUTION__NODE_V:
                setNodeV((DComplexMatrix1D)newValue);
                return;
            case CommonPackage.SOLUTION__NODE_VBASE:
                setNodeVBase((DoubleMatrix1D)newValue);
                return;
            case CommonPackage.SOLUTION__ERROR_SAVED:
                setErrorSaved((DoubleMatrix1D)newValue);
                return;
            case CommonPackage.SOLUTION__VMAG_SAVED:
                setVMagSaved((DoubleMatrix1D)newValue);
                return;
            case CommonPackage.SOLUTION__CURRENTS:
                setCurrents((DComplexMatrix1D)newValue);
                return;
            case CommonPackage.SOLUTION__ALGORITHMS:
                setAlgorithms((SolutionAlgs)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case CommonPackage.SOLUTION__Y:
                setY((DComplexMatrix2D)null);
                return;
            case CommonPackage.SOLUTION__YSYSTEM:
                setYSystem((DComplexMatrix2D)null);
                return;
            case CommonPackage.SOLUTION__YSERIES:
                setYSeries((DComplexMatrix2D)null);
                return;
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
            case CommonPackage.SOLUTION__SOLUTION_COUNT:
                setSolutionCount(SOLUTION_COUNT_EDEFAULT);
                return;
            case CommonPackage.SOLUTION__NODE_V:
                setNodeV((DComplexMatrix1D)null);
                return;
            case CommonPackage.SOLUTION__NODE_VBASE:
                setNodeVBase((DoubleMatrix1D)null);
                return;
            case CommonPackage.SOLUTION__ERROR_SAVED:
                setErrorSaved((DoubleMatrix1D)null);
                return;
            case CommonPackage.SOLUTION__VMAG_SAVED:
                setVMagSaved((DoubleMatrix1D)null);
                return;
            case CommonPackage.SOLUTION__CURRENTS:
                setCurrents((DComplexMatrix1D)null);
                return;
            case CommonPackage.SOLUTION__ALGORITHMS:
                setAlgorithms((SolutionAlgs)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case CommonPackage.SOLUTION__Y:
                return y != null;
            case CommonPackage.SOLUTION__YSYSTEM:
                return ySystem != null;
            case CommonPackage.SOLUTION__YSERIES:
                return ySeries != null;
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
            case CommonPackage.SOLUTION__SOLUTION_COUNT:
                return solutionCount != SOLUTION_COUNT_EDEFAULT;
            case CommonPackage.SOLUTION__NODE_V:
                return nodeV != null;
            case CommonPackage.SOLUTION__NODE_VBASE:
                return nodeVBase != null;
            case CommonPackage.SOLUTION__ERROR_SAVED:
                return errorSaved != null;
            case CommonPackage.SOLUTION__VMAG_SAVED:
                return vMagSaved != null;
            case CommonPackage.SOLUTION__CURRENTS:
                return currents != null;
            case CommonPackage.SOLUTION__ALGORITHMS:
                return algorithms != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
        result.append(", solutionCount: ");
        result.append(solutionCount);
        result.append(')');
        return result.toString();
    }

} // SolutionImpl
