/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.common;

import org.eclipse.emf.ecore.EObject;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;
import electrickery.executive.loadModelType;
import electrickery.executive.solutionMode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Solution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.Solution#getYear <em>Year</em>}</li>
 *   <li>{@link electrickery.common.Solution#isPreserveNodeVoltages <em>Preserve Node Voltages</em>}</li>
 *   <li>{@link electrickery.common.Solution#isFrequencyChanged <em>Frequency Changed</em>}</li>
 *   <li>{@link electrickery.common.Solution#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.common.Solution#getMode <em>Mode</em>}</li>
 *   <li>{@link electrickery.common.Solution#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.common.Solution#isSolutionAbort <em>Solution Abort</em>}</li>
 *   <li>{@link electrickery.common.Solution#isSolutionInitialised <em>Solution Initialised</em>}</li>
 *   <li>{@link electrickery.common.Solution#isSeriesYInvalid <em>Series YInvalid</em>}</li>
 *   <li>{@link electrickery.common.Solution#isSystemYChanged <em>System YChanged</em>}</li>
 *   <li>{@link electrickery.common.Solution#getLoadModel <em>Load Model</em>}</li>
 *   <li>{@link electrickery.common.Solution#isVoltageBaseChanged <em>Voltage Base Changed</em>}</li>
 *   <li>{@link electrickery.common.Solution#isHarmonicModel <em>Harmonic Model</em>}</li>
 *   <li>{@link electrickery.common.Solution#isDynamicModel <em>Dynamic Model</em>}</li>
 *   <li>{@link electrickery.common.Solution#isUseAuxillaryCurrents <em>Use Auxillary Currents</em>}</li>
 *   <li>{@link electrickery.common.Solution#isLoadsNeedUpdating <em>Loads Need Updating</em>}</li>
 *   <li>{@link electrickery.common.Solution#getIteration <em>Iteration</em>}</li>
 *   <li>{@link electrickery.common.Solution#getMaxIterations <em>Max Iterations</em>}</li>
 *   <li>{@link electrickery.common.Solution#getMaxError <em>Max Error</em>}</li>
 *   <li>{@link electrickery.common.Solution#getConvergenceTolerance <em>Convergence Tolerance</em>}</li>
 *   <li>{@link electrickery.common.Solution#isConverged <em>Converged</em>}</li>
 *   <li>{@link electrickery.common.Solution#getControlIteration <em>Control Iteration</em>}</li>
 *   <li>{@link electrickery.common.Solution#getMaxControlIterations <em>Max Control Iterations</em>}</li>
 *   <li>{@link electrickery.common.Solution#getControlMode <em>Control Mode</em>}</li>
 *   <li>{@link electrickery.common.Solution#isControlActionsDone <em>Control Actions Done</em>}</li>
 *   <li>{@link electrickery.common.Solution#getMostIterationsDone <em>Most Iterations Done</em>}</li>
 *   <li>{@link electrickery.common.Solution#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link electrickery.common.Solution#isLastSolutionWasDirect <em>Last Solution Was Direct</em>}</li>
 *   <li>{@link electrickery.common.Solution#getNodeV <em>Node V</em>}</li>
 *   <li>{@link electrickery.common.Solution#getCurrents <em>Currents</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getSolution()
 * @model
 * @generated
 */
public interface Solution extends EObject {
	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Year</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(int)
	 * @see electrickery.common.CommonPackage#getSolution_Year()
	 * @model
	 * @generated
	 */
	int getYear();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(int value);

	/**
	 * Returns the value of the '<em><b>Preserve Node Voltages</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preserve Node Voltages</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preserve Node Voltages</em>' attribute.
	 * @see #setPreserveNodeVoltages(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_PreserveNodeVoltages()
	 * @model default="false"
	 * @generated
	 */
	boolean isPreserveNodeVoltages();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isPreserveNodeVoltages <em>Preserve Node Voltages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preserve Node Voltages</em>' attribute.
	 * @see #isPreserveNodeVoltages()
	 * @generated
	 */
	void setPreserveNodeVoltages(boolean value);

	/**
	 * Returns the value of the '<em><b>Frequency Changed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frequency Changed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frequency Changed</em>' attribute.
	 * @see #setFrequencyChanged(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_FrequencyChanged()
	 * @model
	 * @generated
	 */
	boolean isFrequencyChanged();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isFrequencyChanged <em>Frequency Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency Changed</em>' attribute.
	 * @see #isFrequencyChanged()
	 * @generated
	 */
	void setFrequencyChanged(boolean value);

	/**
	 * Returns the value of the '<em><b>Frequency</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frequency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frequency</em>' attribute.
	 * @see #setFrequency(double)
	 * @see electrickery.common.CommonPackage#getSolution_Frequency()
	 * @model default="60.0"
	 * @generated
	 */
	double getFrequency();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
	void setFrequency(double value);

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.solutionMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see electrickery.executive.solutionMode
	 * @see #setMode(solutionMode)
	 * @see electrickery.common.CommonPackage#getSolution_Mode()
	 * @model
	 * @generated
	 */
	solutionMode getMode();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see electrickery.executive.solutionMode
	 * @see #getMode()
	 * @generated
	 */
	void setMode(solutionMode value);

	/**
	 * Returns the value of the '<em><b>Circuit</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link electrickery.common.Circuit#getSolution <em>Solution</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuit</em>' reference.
	 * @see #setCircuit(Circuit)
	 * @see electrickery.common.CommonPackage#getSolution_Circuit()
	 * @see electrickery.common.Circuit#getSolution
	 * @model opposite="solution"
	 * @generated
	 */
	Circuit getCircuit();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getCircuit <em>Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Circuit</em>' reference.
	 * @see #getCircuit()
	 * @generated
	 */
	void setCircuit(Circuit value);

	/**
	 * Returns the value of the '<em><b>Solution Abort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solution Abort</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solution Abort</em>' attribute.
	 * @see #setSolutionAbort(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_SolutionAbort()
	 * @model
	 * @generated
	 */
	boolean isSolutionAbort();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isSolutionAbort <em>Solution Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solution Abort</em>' attribute.
	 * @see #isSolutionAbort()
	 * @generated
	 */
	void setSolutionAbort(boolean value);

	/**
	 * Returns the value of the '<em><b>Solution Initialised</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solution Initialised</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solution Initialised</em>' attribute.
	 * @see #setSolutionInitialised(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_SolutionInitialised()
	 * @model default="false"
	 * @generated
	 */
	boolean isSolutionInitialised();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isSolutionInitialised <em>Solution Initialised</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solution Initialised</em>' attribute.
	 * @see #isSolutionInitialised()
	 * @generated
	 */
	void setSolutionInitialised(boolean value);

	/**
	 * Returns the value of the '<em><b>Series YInvalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Series YInvalid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Series YInvalid</em>' attribute.
	 * @see #setSeriesYInvalid(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_SeriesYInvalid()
	 * @model
	 * @generated
	 */
	boolean isSeriesYInvalid();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isSeriesYInvalid <em>Series YInvalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Series YInvalid</em>' attribute.
	 * @see #isSeriesYInvalid()
	 * @generated
	 */
	void setSeriesYInvalid(boolean value);

	/**
	 * Returns the value of the '<em><b>System YChanged</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System YChanged</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System YChanged</em>' attribute.
	 * @see #setSystemYChanged(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_SystemYChanged()
	 * @model default="true"
	 * @generated
	 */
	boolean isSystemYChanged();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isSystemYChanged <em>System YChanged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System YChanged</em>' attribute.
	 * @see #isSystemYChanged()
	 * @generated
	 */
	void setSystemYChanged(boolean value);

	/**
	 * Returns the value of the '<em><b>Load Model</b></em>' attribute.
	 * The default value is <code>"Powerflow"</code>.
	 * The literals are from the enumeration {@link electrickery.executive.loadModelType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Model</em>' attribute.
	 * @see electrickery.executive.loadModelType
	 * @see #setLoadModel(loadModelType)
	 * @see electrickery.common.CommonPackage#getSolution_LoadModel()
	 * @model default="Powerflow"
	 * @generated
	 */
	loadModelType getLoadModel();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getLoadModel <em>Load Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Model</em>' attribute.
	 * @see electrickery.executive.loadModelType
	 * @see #getLoadModel()
	 * @generated
	 */
	void setLoadModel(loadModelType value);

	/**
	 * Returns the value of the '<em><b>Voltage Base Changed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Voltage Base Changed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Voltage Base Changed</em>' attribute.
	 * @see #setVoltageBaseChanged(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_VoltageBaseChanged()
	 * @model default="false"
	 * @generated
	 */
	boolean isVoltageBaseChanged();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isVoltageBaseChanged <em>Voltage Base Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Voltage Base Changed</em>' attribute.
	 * @see #isVoltageBaseChanged()
	 * @generated
	 */
	void setVoltageBaseChanged(boolean value);

	/**
	 * Returns the value of the '<em><b>Harmonic Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Harmonic Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Harmonic Model</em>' attribute.
	 * @see #setHarmonicModel(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_HarmonicModel()
	 * @model
	 * @generated
	 */
	boolean isHarmonicModel();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isHarmonicModel <em>Harmonic Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Harmonic Model</em>' attribute.
	 * @see #isHarmonicModel()
	 * @generated
	 */
	void setHarmonicModel(boolean value);

	/**
	 * Returns the value of the '<em><b>Dynamic Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Model</em>' attribute.
	 * @see #setDynamicModel(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_DynamicModel()
	 * @model
	 * @generated
	 */
	boolean isDynamicModel();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isDynamicModel <em>Dynamic Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Model</em>' attribute.
	 * @see #isDynamicModel()
	 * @generated
	 */
	void setDynamicModel(boolean value);

	/**
	 * Returns the value of the '<em><b>Use Auxillary Currents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Auxillary Currents</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Auxillary Currents</em>' attribute.
	 * @see #setUseAuxillaryCurrents(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_UseAuxillaryCurrents()
	 * @model
	 * @generated
	 */
	boolean isUseAuxillaryCurrents();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isUseAuxillaryCurrents <em>Use Auxillary Currents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Auxillary Currents</em>' attribute.
	 * @see #isUseAuxillaryCurrents()
	 * @generated
	 */
	void setUseAuxillaryCurrents(boolean value);

	/**
	 * Returns the value of the '<em><b>Loads Need Updating</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loads Need Updating</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loads Need Updating</em>' attribute.
	 * @see #setLoadsNeedUpdating(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_LoadsNeedUpdating()
	 * @model
	 * @generated
	 */
	boolean isLoadsNeedUpdating();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isLoadsNeedUpdating <em>Loads Need Updating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loads Need Updating</em>' attribute.
	 * @see #isLoadsNeedUpdating()
	 * @generated
	 */
	void setLoadsNeedUpdating(boolean value);

	/**
	 * Returns the value of the '<em><b>Iteration</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iteration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iteration</em>' attribute.
	 * @see #setIteration(int)
	 * @see electrickery.common.CommonPackage#getSolution_Iteration()
	 * @model default="0"
	 * @generated
	 */
	int getIteration();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getIteration <em>Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iteration</em>' attribute.
	 * @see #getIteration()
	 * @generated
	 */
	void setIteration(int value);

	/**
	 * Returns the value of the '<em><b>Max Iterations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Iterations</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Iterations</em>' attribute.
	 * @see #setMaxIterations(int)
	 * @see electrickery.common.CommonPackage#getSolution_MaxIterations()
	 * @model
	 * @generated
	 */
	int getMaxIterations();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getMaxIterations <em>Max Iterations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Iterations</em>' attribute.
	 * @see #getMaxIterations()
	 * @generated
	 */
	void setMaxIterations(int value);

	/**
	 * Returns the value of the '<em><b>Max Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Error</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Error</em>' attribute.
	 * @see #setMaxError(double)
	 * @see electrickery.common.CommonPackage#getSolution_MaxError()
	 * @model
	 * @generated
	 */
	double getMaxError();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getMaxError <em>Max Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Error</em>' attribute.
	 * @see #getMaxError()
	 * @generated
	 */
	void setMaxError(double value);

	/**
	 * Returns the value of the '<em><b>Convergence Tolerance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Convergence Tolerance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Convergence Tolerance</em>' attribute.
	 * @see #setConvergenceTolerance(double)
	 * @see electrickery.common.CommonPackage#getSolution_ConvergenceTolerance()
	 * @model
	 * @generated
	 */
	double getConvergenceTolerance();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getConvergenceTolerance <em>Convergence Tolerance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Convergence Tolerance</em>' attribute.
	 * @see #getConvergenceTolerance()
	 * @generated
	 */
	void setConvergenceTolerance(double value);

	/**
	 * Returns the value of the '<em><b>Converged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converged</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Converged</em>' attribute.
	 * @see #isSetConverged()
	 * @see #unsetConverged()
	 * @see #setConverged(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_Converged()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isConverged();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isConverged <em>Converged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converged</em>' attribute.
	 * @see #isSetConverged()
	 * @see #unsetConverged()
	 * @see #isConverged()
	 * @generated
	 */
	void setConverged(boolean value);

	/**
	 * Unsets the value of the '{@link electrickery.common.Solution#isConverged <em>Converged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConverged()
	 * @see #isConverged()
	 * @see #setConverged(boolean)
	 * @generated
	 */
	void unsetConverged();

	/**
	 * Returns whether the value of the '{@link electrickery.common.Solution#isConverged <em>Converged</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Converged</em>' attribute is set.
	 * @see #unsetConverged()
	 * @see #isConverged()
	 * @see #setConverged(boolean)
	 * @generated
	 */
	boolean isSetConverged();

	/**
	 * Returns the value of the '<em><b>Control Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Iteration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Iteration</em>' attribute.
	 * @see #setControlIteration(int)
	 * @see electrickery.common.CommonPackage#getSolution_ControlIteration()
	 * @model
	 * @generated
	 */
	int getControlIteration();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getControlIteration <em>Control Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Iteration</em>' attribute.
	 * @see #getControlIteration()
	 * @generated
	 */
	void setControlIteration(int value);

	/**
	 * Returns the value of the '<em><b>Max Control Iterations</b></em>' attribute.
	 * The default value is <code>"10"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Control Iterations</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Control Iterations</em>' attribute.
	 * @see #setMaxControlIterations(int)
	 * @see electrickery.common.CommonPackage#getSolution_MaxControlIterations()
	 * @model default="10"
	 * @generated
	 */
	int getMaxControlIterations();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getMaxControlIterations <em>Max Control Iterations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Control Iterations</em>' attribute.
	 * @see #getMaxControlIterations()
	 * @generated
	 */
	void setMaxControlIterations(int value);

	/**
	 * Returns the value of the '<em><b>Control Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.common.controlModeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Mode</em>' attribute.
	 * @see electrickery.common.controlModeType
	 * @see #setControlMode(controlModeType)
	 * @see electrickery.common.CommonPackage#getSolution_ControlMode()
	 * @model
	 * @generated
	 */
	controlModeType getControlMode();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getControlMode <em>Control Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Mode</em>' attribute.
	 * @see electrickery.common.controlModeType
	 * @see #getControlMode()
	 * @generated
	 */
	void setControlMode(controlModeType value);

	/**
	 * Returns the value of the '<em><b>Control Actions Done</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Actions Done</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Actions Done</em>' attribute.
	 * @see #setControlActionsDone(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_ControlActionsDone()
	 * @model default="false"
	 * @generated
	 */
	boolean isControlActionsDone();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isControlActionsDone <em>Control Actions Done</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Actions Done</em>' attribute.
	 * @see #isControlActionsDone()
	 * @generated
	 */
	void setControlActionsDone(boolean value);

	/**
	 * Returns the value of the '<em><b>Most Iterations Done</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Most Iterations Done</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Most Iterations Done</em>' attribute.
	 * @see #setMostIterationsDone(int)
	 * @see electrickery.common.CommonPackage#getSolution_MostIterationsDone()
	 * @model
	 * @generated
	 */
	int getMostIterationsDone();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getMostIterationsDone <em>Most Iterations Done</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Most Iterations Done</em>' attribute.
	 * @see #getMostIterationsDone()
	 * @generated
	 */
	void setMostIterationsDone(int value);

	/**
	 * Returns the value of the '<em><b>Algorithm</b></em>' attribute.
	 * The default value is <code>"NormalSolve"</code>.
	 * The literals are from the enumeration {@link electrickery.common.algorithmType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Algorithm</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Algorithm</em>' attribute.
	 * @see electrickery.common.algorithmType
	 * @see #setAlgorithm(algorithmType)
	 * @see electrickery.common.CommonPackage#getSolution_Algorithm()
	 * @model default="NormalSolve"
	 * @generated
	 */
	algorithmType getAlgorithm();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getAlgorithm <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Algorithm</em>' attribute.
	 * @see electrickery.common.algorithmType
	 * @see #getAlgorithm()
	 * @generated
	 */
	void setAlgorithm(algorithmType value);

	/**
	 * Returns the value of the '<em><b>Last Solution Was Direct</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Solution Was Direct</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Solution Was Direct</em>' attribute.
	 * @see #setLastSolutionWasDirect(boolean)
	 * @see electrickery.common.CommonPackage#getSolution_LastSolutionWasDirect()
	 * @model
	 * @generated
	 */
	boolean isLastSolutionWasDirect();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#isLastSolutionWasDirect <em>Last Solution Was Direct</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Solution Was Direct</em>' attribute.
	 * @see #isLastSolutionWasDirect()
	 * @generated
	 */
	void setLastSolutionWasDirect(boolean value);

	/**
	 * Returns the value of the '<em><b>Node V</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node V</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node V</em>' reference.
	 * @see #setNodeV(DComplexMatrix1D)
	 * @see electrickery.common.CommonPackage#getSolution_NodeV()
	 * @model type="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	DComplexMatrix1D getNodeV();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getNodeV <em>Node V</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node V</em>' reference.
	 * @see #getNodeV()
	 * @generated
	 */
	void setNodeV(DComplexMatrix1D value);

	/**
	 * Returns the value of the '<em><b>Currents</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Currents</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Currents</em>' reference.
	 * @see #setCurrents(DComplexMatrix1D)
	 * @see electrickery.common.CommonPackage#getSolution_Currents()
	 * @model type="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	DComplexMatrix1D getCurrents();

	/**
	 * Sets the value of the '{@link electrickery.common.Solution#getCurrents <em>Currents</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Currents</em>' reference.
	 * @see #getCurrents()
	 * @generated
	 */
	void setCurrents(DComplexMatrix1D value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int solveSnap();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int solveCircuit();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void solve();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void solveDirect();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void doPowerFlowSolution();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int solveYDirect();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Main solver call.
	 * <!-- end-model-doc -->
	 * @model vType="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	int solveSystem(DComplexMatrix1D v);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setGeneratorDispRef();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void snapShotInit();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void checkControls();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean convergenceCheck();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void sample_doControlActions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void sampleControlDevices();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void doControlActions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void checkFaultStatus();

} // Solution
