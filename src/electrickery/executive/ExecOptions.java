/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.executive;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exec Options</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines options for an executive.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.executive.ExecOptions#getType <em>Type</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getElement <em>Element</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getHour <em>Hour</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getSec <em>Sec</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getYear <em>Year</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getStepSize <em>Step Size</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getMode <em>Mode</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getRandom <em>Random</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getNumber <em>Number</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getTime <em>Time</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getEditor <em>Editor</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getTolerance <em>Tolerance</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getMaxIter <em>Max Iter</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getLoadModel <em>Load Model</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getLoadMult <em>Load Mult</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getNormVMinPU <em>Norm VMin PU</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getNormVMaxPU <em>Norm VMax PU</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getEmergVMinPU <em>Emerg VMin PU</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getEmergVMaxPU <em>Emerg VMax PU</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getPctMean <em>Pct Mean</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getPctStdDev <em>Pct Std Dev</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getLDCurve <em>LD Curve</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getPctGrowth <em>Pct Growth</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getGenKW <em>Gen KW</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getGenPF <em>Gen PF</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getCapKVAr <em>Cap KV Ar</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getAddType <em>Add Type</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isAllowDuplicates <em>Allow Duplicates</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isZoneLock <em>Zone Lock</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getUEWeight <em>UE Weight</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getLossWeight <em>Loss Weight</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getUERegs <em>UE Regs</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getLossRegs <em>Loss Regs</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getVoltageBases <em>Voltage Bases</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isTrapezoidal <em>Trapezoidal</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getAutoBusList <em>Auto Bus List</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getControlMode <em>Control Mode</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isTraceMode <em>Trace Mode</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getGenMult <em>Gen Mult</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getDefaultDaily <em>Default Daily</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getDefaultYearly <em>Default Yearly</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getAllocationFactor <em>Allocation Factor</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getCktModel <em>Ckt Model</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getPriceSignal <em>Price Signal</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getPriceCurve <em>Price Curve</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getBaseFrequency <em>Base Frequency</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getHarmonics <em>Harmonics</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getMaxController <em>Max Controller</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getBus <em>Bus</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getKeepList <em>Keep List</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getReduceOption <em>Reduce Option</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isDemandInterval <em>Demand Interval</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getPctNormal <em>Pct Normal</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isDIVerbose <em>DI Verbose</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getCaseName <em>Case Name</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getMarkerCode <em>Marker Code</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#getNodeWidth <em>Node Width</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isLog <em>Log</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isRecorder <em>Recorder</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isOverloadReport <em>Overload Report</em>}</li>
 *   <li>{@link electrickery.executive.ExecOptions#isVoltageExceptionReport <em>Voltage Exception Report</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.executive.ExecutivePackage#getExecOptions()
 * @model
 * @generated
 */
public interface ExecOptions extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Active DSS class type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Active DSS element name. You can use the complete object spec (class.name) or just the name.  If full name is specifed, class becomes the active class, also.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Element()
	 * @model
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(String value);

	/**
	 * Returns the value of the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Start hour of the solution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hour</em>' attribute.
	 * @see #setHour(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Hour()
	 * @model
	 * @generated
	 */
	int getHour();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getHour <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hour</em>' attribute.
	 * @see #getHour()
	 * @generated
	 */
	void setHour(int value);

	/**
	 * Returns the value of the '<em><b>Sec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Seconds from the hour for the start time of the solution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sec</em>' attribute.
	 * @see #setSec(long)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Sec()
	 * @model
	 * @generated
	 */
	long getSec();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getSec <em>Sec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sec</em>' attribute.
	 * @see #getSec()
	 * @generated
	 */
	void setSec(long value);

	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Year to be used for the solution.  For certain solution types, this determines the growth multiplier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Year()
	 * @model
	 * @generated
	 */
	int getYear();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(int value);

	/**
	 * Returns the value of the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Frequency for the solution of the active circuit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Frequency</em>' attribute.
	 * @see #setFrequency(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Frequency()
	 * @model
	 * @generated
	 */
	float getFrequency();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
	void setFrequency(float value);

	/**
	 * Returns the value of the '<em><b>Step Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time step in sec for the active circuit.  Nominally for dynamics solution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Step Size</em>' attribute.
	 * @see #setStepSize(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_StepSize()
	 * @model
	 * @generated
	 */
	float getStepSize();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getStepSize <em>Step Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Step Size</em>' attribute.
	 * @see #getStepSize()
	 * @generated
	 */
	void setStepSize(float value);

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.solutionMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Setting the Mode property resets all monitors and energy meters. It also resets the time step, etc. to defaults for each mode.  After the initial reset, the user must explicitly reset the monitors and/or meters until another Set Mode= command.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see electrickery.executive.solutionMode
	 * @see #setMode(solutionMode)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Mode()
	 * @model
	 * @generated
	 */
	solutionMode getMode();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see electrickery.executive.solutionMode
	 * @see #getMode()
	 * @generated
	 */
	void setMode(solutionMode value);

	/**
	 * Returns the value of the '<em><b>Random</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.randomType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For Monte Carlo variables.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Random</em>' attribute.
	 * @see electrickery.executive.randomType
	 * @see #setRandom(randomType)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Random()
	 * @model
	 * @generated
	 */
	randomType getRandom();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getRandom <em>Random</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Random</em>' attribute.
	 * @see electrickery.executive.randomType
	 * @see #getRandom()
	 * @generated
	 */
	void setRandom(randomType value);

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * The default value is <code>"2"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number of solutions to perform for Monte Carlo or dutycycle solutions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Number()
	 * @model default="2"
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specify the solution start time as an array: {hour, secs}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time</em>' attribute list.
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Time()
	 * @model lower="2" upper="2"
	 * @generated
	 */
	EList<Integer> getTime();

	/**
	 * Returns the value of the '<em><b>Circuit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Active circuit name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Circuit</em>' attribute.
	 * @see #setCircuit(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Circuit()
	 * @model
	 * @generated
	 */
	String getCircuit();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getCircuit <em>Circuit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Circuit</em>' attribute.
	 * @see #getCircuit()
	 * @generated
	 */
	void setCircuit(String value);

	/**
	 * Returns the value of the '<em><b>Editor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Command string required to start up the editor preferred by the user.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Editor</em>' attribute.
	 * @see #setEditor(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Editor()
	 * @model
	 * @generated
	 */
	String getEditor();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getEditor <em>Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' attribute.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(String value);

	/**
	 * Returns the value of the '<em><b>Tolerance</b></em>' attribute.
	 * The default value is <code>"0.0001"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Solution tolerance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tolerance</em>' attribute.
	 * @see #setTolerance(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Tolerance()
	 * @model default="0.0001"
	 * @generated
	 */
	float getTolerance();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getTolerance <em>Tolerance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tolerance</em>' attribute.
	 * @see #getTolerance()
	 * @generated
	 */
	void setTolerance(float value);

	/**
	 * Returns the value of the '<em><b>Max Iter</b></em>' attribute.
	 * The default value is <code>"15"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum allowable iterations for power flow solutions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Iter</em>' attribute.
	 * @see #setMaxIter(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_MaxIter()
	 * @model default="15"
	 * @generated
	 */
	int getMaxIter();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getMaxIter <em>Max Iter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Iter</em>' attribute.
	 * @see #getMaxIter()
	 * @generated
	 */
	void setMaxIter(int value);

	/**
	 * Returns the value of the '<em><b>Load Model</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.loadModelType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If admittance, a non-iterative, direct solution is done with all loads and generators modeled by their equivalent admittance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Load Model</em>' attribute.
	 * @see electrickery.executive.loadModelType
	 * @see #setLoadModel(loadModelType)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_LoadModel()
	 * @model
	 * @generated
	 */
	loadModelType getLoadModel();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getLoadModel <em>Load Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Model</em>' attribute.
	 * @see electrickery.executive.loadModelType
	 * @see #getLoadModel()
	 * @generated
	 */
	void setLoadModel(loadModelType value);

	/**
	 * Returns the value of the '<em><b>Load Mult</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Global load multiplier for this circuit.  Does not affect loads designated to be "fixed".  All other base kW values are multiplied by this number. Defaults to 1.0 when the circuit is created. As with other values, it always stays at the last value to which it was set until changed again.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Load Mult</em>' attribute.
	 * @see #setLoadMult(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_LoadMult()
	 * @model default="1.0"
	 * @generated
	 */
	float getLoadMult();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getLoadMult <em>Load Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Mult</em>' attribute.
	 * @see #getLoadMult()
	 * @generated
	 */
	void setLoadMult(float value);

	/**
	 * Returns the value of the '<em><b>Norm VMin PU</b></em>' attribute.
	 * The default value is <code>"0.95"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum permissible per unit voltage for normal conditions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm VMin PU</em>' attribute.
	 * @see #setNormVMinPU(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_NormVMinPU()
	 * @model default="0.95"
	 * @generated
	 */
	float getNormVMinPU();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getNormVMinPU <em>Norm VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Norm VMin PU</em>' attribute.
	 * @see #getNormVMinPU()
	 * @generated
	 */
	void setNormVMinPU(float value);

	/**
	 * Returns the value of the '<em><b>Norm VMax PU</b></em>' attribute.
	 * The default value is <code>"1.05"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum permissible per unit voltage for normal conditions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Norm VMax PU</em>' attribute.
	 * @see #setNormVMaxPU(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_NormVMaxPU()
	 * @model default="1.05"
	 * @generated
	 */
	float getNormVMaxPU();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getNormVMaxPU <em>Norm VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Norm VMax PU</em>' attribute.
	 * @see #getNormVMaxPU()
	 * @generated
	 */
	void setNormVMaxPU(float value);

	/**
	 * Returns the value of the '<em><b>Emerg VMin PU</b></em>' attribute.
	 * The default value is <code>"0.90"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Minimum permissible per unit voltage for emergency (contingency) conditions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg VMin PU</em>' attribute.
	 * @see #setEmergVMinPU(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_EmergVMinPU()
	 * @model default="0.90"
	 * @generated
	 */
	float getEmergVMinPU();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getEmergVMinPU <em>Emerg VMin PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg VMin PU</em>' attribute.
	 * @see #getEmergVMinPU()
	 * @generated
	 */
	void setEmergVMinPU(float value);

	/**
	 * Returns the value of the '<em><b>Emerg VMax PU</b></em>' attribute.
	 * The default value is <code>"1.08"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximum permissible per unit voltage for emergency (contingency) conditions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Emerg VMax PU</em>' attribute.
	 * @see #setEmergVMaxPU(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_EmergVMaxPU()
	 * @model default="1.08"
	 * @generated
	 */
	float getEmergVMaxPU();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getEmergVMaxPU <em>Emerg VMax PU</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg VMax PU</em>' attribute.
	 * @see #getEmergVMaxPU()
	 * @generated
	 */
	void setEmergVMaxPU(float value);

	/**
	 * Returns the value of the '<em><b>Pct Mean</b></em>' attribute.
	 * The default value is <code>"65"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent mean to use for global load multiplier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Mean</em>' attribute.
	 * @see #setPctMean(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_PctMean()
	 * @model default="65"
	 * @generated
	 */
	float getPctMean();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getPctMean <em>Pct Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Mean</em>' attribute.
	 * @see #getPctMean()
	 * @generated
	 */
	void setPctMean(float value);

	/**
	 * Returns the value of the '<em><b>Pct Std Dev</b></em>' attribute.
	 * The default value is <code>"9"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Percent Standard deviation to use for global load multiplier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Std Dev</em>' attribute.
	 * @see #setPctStdDev(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_PctStdDev()
	 * @model default="9"
	 * @generated
	 */
	float getPctStdDev();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getPctStdDev <em>Pct Std Dev</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Std Dev</em>' attribute.
	 * @see #getPctStdDev()
	 * @generated
	 */
	void setPctStdDev(float value);

	/**
	 * Returns the value of the '<em><b>LD Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Global load multiplier is defined by this curve for LD1 and LD2 solution modes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>LD Curve</em>' reference.
	 * @see #setLDCurve(EClass)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_LDCurve()
	 * @model
	 * @generated
	 */
	EClass getLDCurve();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getLDCurve <em>LD Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LD Curve</em>' reference.
	 * @see #getLDCurve()
	 * @generated
	 */
	void setLDCurve(EClass value);

	/**
	 * Returns the value of the '<em><b>Pct Growth</b></em>' attribute.
	 * The default value is <code>"2.5"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default annual growth rate, percent, for loads with no growth curve specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Growth</em>' attribute.
	 * @see #setPctGrowth(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_PctGrowth()
	 * @model default="2.5"
	 * @generated
	 */
	float getPctGrowth();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getPctGrowth <em>Pct Growth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Growth</em>' attribute.
	 * @see #getPctGrowth()
	 * @generated
	 */
	void setPctGrowth(float value);

	/**
	 * Returns the value of the '<em><b>Gen KW</b></em>' attribute.
	 * The default value is <code>"1000.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Size of generator, kW, to automatically add to system.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gen KW</em>' attribute.
	 * @see #setGenKW(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_GenKW()
	 * @model default="1000.0"
	 * @generated
	 */
	float getGenKW();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getGenKW <em>Gen KW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen KW</em>' attribute.
	 * @see #getGenKW()
	 * @generated
	 */
	void setGenKW(float value);

	/**
	 * Returns the value of the '<em><b>Gen PF</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Power factor of generator to assume for automatic addition.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gen PF</em>' attribute.
	 * @see #setGenPF(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_GenPF()
	 * @model default="1.0"
	 * @generated
	 */
	float getGenPF();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getGenPF <em>Gen PF</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen PF</em>' attribute.
	 * @see #getGenPF()
	 * @generated
	 */
	void setGenPF(float value);

	/**
	 * Returns the value of the '<em><b>Cap KV Ar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Size of capacitor, kVAR, to automatically add to system.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cap KV Ar</em>' attribute.
	 * @see #setCapKVAr(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_CapKVAr()
	 * @model
	 * @generated
	 */
	float getCapKVAr();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getCapKVAr <em>Cap KV Ar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cap KV Ar</em>' attribute.
	 * @see #getCapKVAr()
	 * @generated
	 */
	void setCapKVAr(float value);

	/**
	 * Returns the value of the '<em><b>Add Type</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.autoAddType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Type of device for AutoAdd Mode.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Add Type</em>' attribute.
	 * @see electrickery.executive.autoAddType
	 * @see #setAddType(autoAddType)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_AddType()
	 * @model
	 * @generated
	 */
	autoAddType getAddType();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getAddType <em>Add Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Add Type</em>' attribute.
	 * @see electrickery.executive.autoAddType
	 * @see #getAddType()
	 * @generated
	 */
	void setAddType(autoAddType value);

	/**
	 * Returns the value of the '<em><b>Allow Duplicates</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag to indicate if it is OK to have devices of same name in the same class. If No, then a New command is treated as an Edit command.  If Yes, then a New command will always result in a device being added.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Allow Duplicates</em>' attribute.
	 * @see #setAllowDuplicates(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_AllowDuplicates()
	 * @model default="false"
	 * @generated
	 */
	boolean isAllowDuplicates();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isAllowDuplicates <em>Allow Duplicates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Duplicates</em>' attribute.
	 * @see #isAllowDuplicates()
	 * @generated
	 */
	void setAllowDuplicates(boolean value);

	/**
	 * Returns the value of the '<em><b>Zone Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If No, then meter zones are recomputed each time there is a change in the circuit. If Yes, then meter zones are not recomputed unless they have not yet been computed. Meter zones are normally recomputed on Solve command following a circuit change.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Zone Lock</em>' attribute.
	 * @see #setZoneLock(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_ZoneLock()
	 * @model
	 * @generated
	 */
	boolean isZoneLock();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isZoneLock <em>Zone Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zone Lock</em>' attribute.
	 * @see #isZoneLock()
	 * @generated
	 */
	void setZoneLock(boolean value);

	/**
	 * Returns the value of the '<em><b>UE Weight</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Weighting factor for UE/EEN in AutoAdd functions.  Autoadd mode minimizes (Lossweight * Losses + UEweight * UE).  If you wish to ignore UE, set to 0.  This applies only when there are EnergyMeter objects. Otherwise, AutoAdd mode minimizes total system losses.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UE Weight</em>' attribute.
	 * @see #setUEWeight(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_UEWeight()
	 * @model default="1.0"
	 * @generated
	 */
	float getUEWeight();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getUEWeight <em>UE Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UE Weight</em>' attribute.
	 * @see #getUEWeight()
	 * @generated
	 */
	void setUEWeight(float value);

	/**
	 * Returns the value of the '<em><b>Loss Weight</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Weighting factor for Losses in AutoAdd functions. Autoadd mode minimizes (Lossweight * Losses + UEweight * UE).  If you wish to ignore Losses, set to 0. This applies only when there are EnergyMeter objects. Otherwise, AutoAdd mode minimizes total system losses.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Loss Weight</em>' attribute.
	 * @see #setLossWeight(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_LossWeight()
	 * @model default="1.0"
	 * @generated
	 */
	float getLossWeight();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getLossWeight <em>Loss Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loss Weight</em>' attribute.
	 * @see #getLossWeight()
	 * @generated
	 */
	void setLossWeight(float value);

	/**
	 * Returns the value of the '<em><b>UE Regs</b></em>' attribute.
	 * The default value is <code>"11"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Which EnergyMeter register(s) to use for UE in AutoAdd Mode. May be one or more registers.  If more than one, register values are summed together. Array of integer values > 0.  Defaults to 11 (for Load EEN).  For a list of EnergyMeter register numbers, do the "Show Meters" command after defining a circuit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UE Regs</em>' attribute.
	 * @see #setUERegs(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_UERegs()
	 * @model default="11"
	 * @generated
	 */
	int getUERegs();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getUERegs <em>UE Regs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UE Regs</em>' attribute.
	 * @see #getUERegs()
	 * @generated
	 */
	void setUERegs(int value);

	/**
	 * Returns the value of the '<em><b>Loss Regs</b></em>' attribute.
	 * The default value is <code>"13"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Which EnergyMeter register(s) to use for Losses in AutoAdd Mode. May be one or more registers.  If more than one, register values are summed together.  Array of integer values > 0.  Defaults to 13 (for Zone kWh Losses).  For a list of EnergyMeter register numbers, do the "Show Meters" command after defining a circuit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Loss Regs</em>' attribute.
	 * @see #setLossRegs(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_LossRegs()
	 * @model default="13"
	 * @generated
	 */
	int getLossRegs();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getLossRegs <em>Loss Regs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loss Regs</em>' attribute.
	 * @see #getLossRegs()
	 * @generated
	 */
	void setLossRegs(int value);

	/**
	 * Returns the value of the '<em><b>Voltage Bases</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Float}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Legal bus voltage bases for this circuit.  Enter an array of the legal voltage bases, in phase-to-phase voltages, for example:  {.208, .480, 12.47, 24.9, 34.5, 115.0, 230.0}  When the CalcVoltageBases command is issued, a snapshot solution is performed with no load injections and the bus base voltage is set to the nearest legal voltage base. The defaults are as shown in the example above.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Voltage Bases</em>' attribute list.
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_VoltageBases()
	 * @model
	 * @generated
	 */
	EList<Float> getVoltageBases();

	/**
	 * Returns the value of the '<em><b>Algorithm</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.algorithmType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Solution algorithm type.  Normal is a fixed point iteration that is a little quicker than the Newton iteration.  Normal is adequate for most radial distribution circuits.  Newton is more robust for circuits that are difficult to solve.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Algorithm</em>' attribute.
	 * @see electrickery.executive.algorithmType
	 * @see #setAlgorithm(algorithmType)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Algorithm()
	 * @model
	 * @generated
	 */
	algorithmType getAlgorithm();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getAlgorithm <em>Algorithm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Algorithm</em>' attribute.
	 * @see electrickery.executive.algorithmType
	 * @see #getAlgorithm()
	 * @generated
	 */
	void setAlgorithm(algorithmType value);

	/**
	 * Returns the value of the '<em><b>Trapezoidal</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether to use trapezoidal integration for accumulating energy meter registers. Applies to EnergyMeter and Generator objects.  Default method simply multiplies the present value of the registers times the width of the interval. Trapezoidal is more accurate when there are sharp changes in a load shape or unequal intervals. Trapezoidal is automatically used for some load-duration curve simulations where the interval size varies considerably. Keep in mind that for Trapezoidal, you have to solve one more point than the number of intervals. That is, to do a Daily simulation on a 24-hr load shape, you would set Number=25 to force a solution at the first point again to establish the last (24th) interval.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trapezoidal</em>' attribute.
	 * @see #setTrapezoidal(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Trapezoidal()
	 * @model default="false"
	 * @generated
	 */
	boolean isTrapezoidal();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isTrapezoidal <em>Trapezoidal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trapezoidal</em>' attribute.
	 * @see #isTrapezoidal()
	 * @generated
	 */
	void setTrapezoidal(boolean value);

	/**
	 * Returns the value of the '<em><b>Auto Bus List</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Buses to include in AutoAdd searches.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Auto Bus List</em>' reference list.
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_AutoBusList()
	 * @model
	 * @generated
	 */
	EList<EClass> getAutoBusList();

	/**
	 * Returns the value of the '<em><b>Control Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.controlModeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Control mode for the solution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Control Mode</em>' attribute.
	 * @see electrickery.executive.controlModeType
	 * @see #setControlMode(controlModeType)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_ControlMode()
	 * @model
	 * @generated
	 */
	controlModeType getControlMode();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getControlMode <em>Control Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Mode</em>' attribute.
	 * @see electrickery.executive.controlModeType
	 * @see #getControlMode()
	 * @generated
	 */
	void setControlMode(controlModeType value);

	/**
	 * Returns the value of the '<em><b>Trace Mode</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set to YES to trace the actions taken in the control queue.  Creates a file named TRACE_CONTROLQUEUE.CSV in the default directory. The names of all circuit elements taking an action are logged.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trace Mode</em>' attribute.
	 * @see #setTraceMode(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_TraceMode()
	 * @model default="false"
	 * @generated
	 */
	boolean isTraceMode();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isTraceMode <em>Trace Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace Mode</em>' attribute.
	 * @see #isTraceMode()
	 * @generated
	 */
	void setTraceMode(boolean value);

	/**
	 * Returns the value of the '<em><b>Gen Mult</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Global multiplier for the kW output of every generator in the circuit.  Applies to all but Autoadd solution modes. Ignored for generators designated as Status=Fixed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gen Mult</em>' attribute.
	 * @see #setGenMult(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_GenMult()
	 * @model default="1.0"
	 * @generated
	 */
	float getGenMult();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getGenMult <em>Gen Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen Mult</em>' attribute.
	 * @see #getGenMult()
	 * @generated
	 */
	void setGenMult(float value);

	/**
	 * Returns the value of the '<em><b>Default Daily</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default daily load shape.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Daily</em>' reference.
	 * @see #setDefaultDaily(EClass)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_DefaultDaily()
	 * @model
	 * @generated
	 */
	EClass getDefaultDaily();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getDefaultDaily <em>Default Daily</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Daily</em>' reference.
	 * @see #getDefaultDaily()
	 * @generated
	 */
	void setDefaultDaily(EClass value);

	/**
	 * Returns the value of the '<em><b>Default Yearly</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default yearly load shape.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Yearly</em>' reference.
	 * @see #setDefaultYearly(EClass)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_DefaultYearly()
	 * @model
	 * @generated
	 */
	EClass getDefaultYearly();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getDefaultYearly <em>Default Yearly</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Yearly</em>' reference.
	 * @see #getDefaultYearly()
	 * @generated
	 */
	void setDefaultYearly(EClass value);

	/**
	 * Returns the value of the '<em><b>Allocation Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Allocation factor to which all loads in the active circuit are set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Allocation Factor</em>' attribute.
	 * @see #setAllocationFactor(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_AllocationFactor()
	 * @model
	 * @generated
	 */
	float getAllocationFactor();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getAllocationFactor <em>Allocation Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allocation Factor</em>' attribute.
	 * @see #getAllocationFactor()
	 * @generated
	 */
	void setAllocationFactor(float value);

	/**
	 * Returns the value of the '<em><b>Ckt Model</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.circuitModelType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Designates whether circuit model is to interpreted as a normal multi-phase model or a positive-sequence only model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ckt Model</em>' attribute.
	 * @see electrickery.executive.circuitModelType
	 * @see #setCktModel(circuitModelType)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_CktModel()
	 * @model
	 * @generated
	 */
	circuitModelType getCktModel();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getCktModel <em>Ckt Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ckt Model</em>' attribute.
	 * @see electrickery.executive.circuitModelType
	 * @see #getCktModel()
	 * @generated
	 */
	void setCktModel(circuitModelType value);

	/**
	 * Returns the value of the '<em><b>Price Signal</b></em>' attribute.
	 * The default value is <code>"25.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Price signal ($/MWh) for the circuit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Price Signal</em>' attribute.
	 * @see #setPriceSignal(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_PriceSignal()
	 * @model default="25.0"
	 * @generated
	 */
	float getPriceSignal();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getPriceSignal <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Signal</em>' attribute.
	 * @see #getPriceSignal()
	 * @generated
	 */
	void setPriceSignal(float value);

	/**
	 * Returns the value of the '<em><b>Price Curve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The curve to use to obtain for price signal. Default is none (null string).  If none, price signal either remains constant or is set by an external process.  Curve is defined as a loadshape (not normalized) and should correspond to the type of analysis being performed (daily, yearly, load-duration, etc.).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Price Curve</em>' reference.
	 * @see #setPriceCurve(EClass)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_PriceCurve()
	 * @model
	 * @generated
	 */
	EClass getPriceCurve();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getPriceCurve <em>Price Curve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Curve</em>' reference.
	 * @see #getPriceCurve()
	 * @generated
	 */
	void setPriceCurve(EClass value);

	/**
	 * Returns the value of the '<em><b>Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Active terminal of the active circuit element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Terminal</em>' reference.
	 * @see #setTerminal(EClass)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Terminal()
	 * @model
	 * @generated
	 */
	EClass getTerminal();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getTerminal <em>Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' reference.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(EClass value);

	/**
	 * Returns the value of the '<em><b>Base Frequency</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the fundamental frequency for harmonic solution and the default base frequency for all impedance quantities. Side effect: also changes the value of the solution frequency.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base Frequency</em>' attribute.
	 * @see #setBaseFrequency(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_BaseFrequency()
	 * @model default="60.0"
	 * @generated
	 */
	float getBaseFrequency();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getBaseFrequency <em>Base Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Frequency</em>' attribute.
	 * @see #getBaseFrequency()
	 * @generated
	 */
	void setBaseFrequency(float value);

	/**
	 * Returns the value of the '<em><b>Harmonics</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of harmonics for which to perform a solution in Harmonics mode. If ALL, then solution is performed for all harmonics defined in spectra currently being used.  Otherwise, specify a more limited list such as:  {1, 5, 7, 11, 13}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Harmonics</em>' attribute list.
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Harmonics()
	 * @model
	 * @generated
	 */
	EList<Integer> getHarmonics();

	/**
	 * Returns the value of the '<em><b>Max Controller</b></em>' attribute.
	 * The default value is <code>"10"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Max control iterations per solution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Controller</em>' attribute.
	 * @see #setMaxController(int)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_MaxController()
	 * @model default="10"
	 * @generated
	 */
	int getMaxController();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getMaxController <em>Max Controller</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Controller</em>' attribute.
	 * @see #getMaxController()
	 * @generated
	 */
	void setMaxController(int value);

	/**
	 * Returns the value of the '<em><b>Bus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Active bus.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus</em>' reference.
	 * @see #setBus(EClass)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Bus()
	 * @model
	 * @generated
	 */
	EClass getBus();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getBus <em>Bus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus</em>' reference.
	 * @see #getBus()
	 * @generated
	 */
	void setBus(EClass value);

	/**
	 * Returns the value of the '<em><b>Data Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the data path for files written or read by the DSS.  Defaults to the startup path. May be Null.  Executes a CHDIR to this path if non-null.  Does not require a circuit defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Path</em>' attribute.
	 * @see #setDataPath(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_DataPath()
	 * @model
	 * @generated
	 */
	String getDataPath();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getDataPath <em>Data Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Path</em>' attribute.
	 * @see #getDataPath()
	 * @generated
	 */
	void setDataPath(String value);

	/**
	 * Returns the value of the '<em><b>Keep List</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Array of buses to keep when performing circuit reductions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Keep List</em>' reference list.
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_KeepList()
	 * @model
	 * @generated
	 */
	EList<EClass> getKeepList();

	/**
	 * Returns the value of the '<em><b>Reduce Option</b></em>' attribute.
	 * The literals are from the enumeration {@link electrickery.executive.reductionStrategy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Strategy for reducing feeders.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reduce Option</em>' attribute.
	 * @see electrickery.executive.reductionStrategy
	 * @see #setReduceOption(reductionStrategy)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_ReduceOption()
	 * @model
	 * @generated
	 */
	reductionStrategy getReduceOption();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getReduceOption <em>Reduce Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reduce Option</em>' attribute.
	 * @see electrickery.executive.reductionStrategy
	 * @see #getReduceOption()
	 * @generated
	 */
	void setReduceOption(reductionStrategy value);

	/**
	 * Returns the value of the '<em><b>Demand Interval</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set for keeping demand interval data for daily, yearly, etc, simulations.  Side Effect:  Resets all meters!!!
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Demand Interval</em>' attribute.
	 * @see #setDemandInterval(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_DemandInterval()
	 * @model default="false"
	 * @generated
	 */
	boolean isDemandInterval();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isDemandInterval <em>Demand Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Demand Interval</em>' attribute.
	 * @see #isDemandInterval()
	 * @generated
	 */
	void setDemandInterval(boolean value);

	/**
	 * Returns the value of the '<em><b>Pct Normal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Normal rating of all lines to a specified percent of the emergency rating.  Note: This action takes place immediately. Only the in-memory value is changed for the duration of the run.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pct Normal</em>' attribute.
	 * @see #setPctNormal(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_PctNormal()
	 * @model
	 * @generated
	 */
	float getPctNormal();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getPctNormal <em>Pct Normal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pct Normal</em>' attribute.
	 * @see #getPctNormal()
	 * @generated
	 */
	void setPctNormal(float value);

	/**
	 * Returns the value of the '<em><b>DI Verbose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set to Yes/True if you wish a separate demand interval (DI) file written for each meter.  Otherwise, only the totalizing meters are written.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>DI Verbose</em>' attribute.
	 * @see #setDIVerbose(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_DIVerbose()
	 * @model
	 * @generated
	 */
	boolean isDIVerbose();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isDIVerbose <em>DI Verbose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>DI Verbose</em>' attribute.
	 * @see #isDIVerbose()
	 * @generated
	 */
	void setDIVerbose(boolean value);

	/**
	 * Returns the value of the '<em><b>Case Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Name of case for yearly simulations with demand interval data.  Becomes the name of the subdirectory under which all the year data are stored. Default = circuit name.  Side Effect: Sets the prefix for output files
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Case Name</em>' attribute.
	 * @see #setCaseName(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_CaseName()
	 * @model
	 * @generated
	 */
	String getCaseName();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getCaseName <em>Case Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Case Name</em>' attribute.
	 * @see #getCaseName()
	 * @generated
	 */
	void setCaseName(String value);

	/**
	 * Returns the value of the '<em><b>Marker Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Number code for node marker on circuit plots (SDL MarkAt options).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Marker Code</em>' attribute.
	 * @see #setMarkerCode(String)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_MarkerCode()
	 * @model
	 * @generated
	 */
	String getMarkerCode();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getMarkerCode <em>Marker Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Marker Code</em>' attribute.
	 * @see #getMarkerCode()
	 * @generated
	 */
	void setMarkerCode(String value);

	/**
	 * Returns the value of the '<em><b>Node Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Width of node marker.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Node Width</em>' attribute.
	 * @see #setNodeWidth(float)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_NodeWidth()
	 * @model
	 * @generated
	 */
	float getNodeWidth();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#getNodeWidth <em>Node Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Width</em>' attribute.
	 * @see #getNodeWidth()
	 * @generated
	 */
	void setNodeWidth(float value);

	/**
	 * Returns the value of the '<em><b>Log</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Significant solution events are added to the Event Log, primarily for debugging.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Log</em>' attribute.
	 * @see #setLog(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Log()
	 * @model default="false"
	 * @generated
	 */
	boolean isLog();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isLog <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log</em>' attribute.
	 * @see #isLog()
	 * @generated
	 */
	void setLog(boolean value);

	/**
	 * Returns the value of the '<em><b>Recorder</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opens DSSRecorder.DSS in DSS install folder and enables recording of all commands that come through the text command interface.  Closed by either setting to NO/FALSE or exiting the program.  When closed by this command, the file name can be found in the Result.  Does not require a circuit defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Recorder</em>' attribute.
	 * @see #setRecorder(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_Recorder()
	 * @model default="false"
	 * @generated
	 */
	boolean isRecorder();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isRecorder <em>Recorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recorder</em>' attribute.
	 * @see #isRecorder()
	 * @generated
	 */
	void setRecorder(boolean value);

	/**
	 * Returns the value of the '<em><b>Overload Report</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For yearly solution mode, sets overload reporting on/off.  DemandInterval must be set to true for this to have effect.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overload Report</em>' attribute.
	 * @see #setOverloadReport(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_OverloadReport()
	 * @model
	 * @generated
	 */
	boolean isOverloadReport();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isOverloadReport <em>Overload Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overload Report</em>' attribute.
	 * @see #isOverloadReport()
	 * @generated
	 */
	void setOverloadReport(boolean value);

	/**
	 * Returns the value of the '<em><b>Voltage Exception Report</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For yearly solution mode, sets voltage exception reporting on/off.  DemandInterval must be set to true for this to have effect.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Voltage Exception Report</em>' attribute.
	 * @see #setVoltageExceptionReport(boolean)
	 * @see electrickery.executive.ExecutivePackage#getExecOptions_VoltageExceptionReport()
	 * @model default="false"
	 * @generated
	 */
	boolean isVoltageExceptionReport();

	/**
	 * Sets the value of the '{@link electrickery.executive.ExecOptions#isVoltageExceptionReport <em>Voltage Exception Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Voltage Exception Report</em>' attribute.
	 * @see #isVoltageExceptionReport()
	 * @generated
	 */
	void setVoltageExceptionReport(boolean value);

} // ExecOptions
