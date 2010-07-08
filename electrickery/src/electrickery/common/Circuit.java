/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;

import electrickery.control.CapacitorControl;
import electrickery.control.ControlElement;
import electrickery.control.RegulatorControl;
import electrickery.conversion.CurrentSource;
import electrickery.conversion.Generator;
import electrickery.conversion.Load;
import electrickery.conversion.PowerConversionElement;
import electrickery.conversion.VoltageSource;
import electrickery.delivery.Capacitor;
import electrickery.delivery.Fault;
import electrickery.delivery.Line;
import electrickery.delivery.PowerDeliveryElement;
import electrickery.delivery.Transformer;
import electrickery.meter.EnergyMeter;
import electrickery.meter.MeterElement;
import electrickery.meter.Monitor;
import electrickery.meter.Sensor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Circuit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines a container of circuit elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.Circuit#getSolution <em>Solution</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getControlQueue <em>Control Queue</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getBusList <em>Bus List</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getFaults <em>Faults</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getVoltageSources <em>Voltage Sources</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getCurrentSources <em>Current Sources</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getSensors <em>Sensors</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getMonitors <em>Monitors</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getEnergyMeters <em>Energy Meters</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getGenerators <em>Generators</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getTransformers <em>Transformers</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getCapControls <em>Cap Controls</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getRegControls <em>Reg Controls</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getLines <em>Lines</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getLoads <em>Loads</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getShuntCapacitors <em>Shunt Capacitors</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getFeeder <em>Feeder</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getName <em>Name</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getNumNodes <em>Num Nodes</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getGenMultiplier <em>Gen Multiplier</em>}</li>
 *   <li>{@link electrickery.common.Circuit#isSolved <em>Solved</em>}</li>
 *   <li>{@link electrickery.common.Circuit#isBusNameRedefined <em>Bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.Circuit#isControl_busNameRedefined <em>Control bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getLoadMultiplier <em>Load Multiplier</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getDefaultGrowthFactor <em>Default Growth Factor</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getDefaultHourMult <em>Default Hour Mult</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getPriceSignal <em>Price Signal</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getCircuit()
 * @model
 * @generated
 */
public interface Circuit extends EObject {

	/**
	 * Returns the value of the '<em><b>Solution</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link electrickery.common.Solution#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solution</em>' reference.
	 * @see #setSolution(Solution)
	 * @see electrickery.common.CommonPackage#getCircuit_Solution()
	 * @see electrickery.common.Solution#getCircuit
	 * @model opposite="circuit" required="true"
	 * @generated
	 */
	Solution getSolution();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getSolution <em>Solution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solution</em>' reference.
	 * @see #getSolution()
	 * @generated
	 */
	void setSolution(Solution value);

	/**
	 * Returns the value of the '<em><b>Control Queue</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Queue</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Queue</em>' reference.
	 * @see #setControlQueue(ControlQueue)
	 * @see electrickery.common.CommonPackage#getCircuit_ControlQueue()
	 * @model required="true"
	 * @generated
	 */
	ControlQueue getControlQueue();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getControlQueue <em>Control Queue</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Queue</em>' reference.
	 * @see #getControlQueue()
	 * @generated
	 */
	void setControlQueue(ControlQueue value);

	/**
	 * Returns the value of the '<em><b>Bus List</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.common.Bus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus List</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus List</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_BusList()
	 * @model containment="true"
	 * @generated
	 */
	EList<Bus> getBusList();

	/**
	 * Returns the value of the '<em><b>Faults</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.delivery.Fault}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Faults</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Faults</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Faults()
	 * @model containment="true"
	 * @generated
	 */
	EList<Fault> getFaults();

	/**
	 * Returns the value of the '<em><b>Bus Name Redefined</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Name Redefined</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Name Redefined</em>' attribute.
	 * @see #setBusNameRedefined(boolean)
	 * @see electrickery.common.CommonPackage#getCircuit_BusNameRedefined()
	 * @model default="true"
	 * @generated
	 */
	boolean isBusNameRedefined();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#isBusNameRedefined <em>Bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Name Redefined</em>' attribute.
	 * @see #isBusNameRedefined()
	 * @generated
	 */
	void setBusNameRedefined(boolean value);

	/**
	 * Returns the value of the '<em><b>Num Nodes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num Nodes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num Nodes</em>' attribute.
	 * @see #setNumNodes(int)
	 * @see electrickery.common.CommonPackage#getCircuit_NumNodes()
	 * @model
	 * @generated
	 */
	int getNumNodes();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getNumNodes <em>Num Nodes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Nodes</em>' attribute.
	 * @see #getNumNodes()
	 * @generated
	 */
	void setNumNodes(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuit Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<CircuitElement> getCircuitElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<PowerDeliveryElement> getPDElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<PowerConversionElement> getPCElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<ControlElement> getDSSControls();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<MeterElement> getMeterElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Transformer> getSubstations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void initialiseNodeVBase();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see electrickery.common.CommonPackage#getCircuit_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Voltage Sources</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.conversion.VoltageSource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Voltage Sources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Voltage Sources</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_VoltageSources()
	 * @model containment="true"
	 * @generated
	 */
	EList<VoltageSource> getVoltageSources();

	/**
	 * Returns the value of the '<em><b>Current Sources</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.conversion.CurrentSource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current Sources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Sources</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_CurrentSources()
	 * @model containment="true"
	 * @generated
	 */
	EList<CurrentSource> getCurrentSources();

	/**
	 * Returns the value of the '<em><b>Sensors</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.meter.Sensor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sensors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sensors</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Sensors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Sensor> getSensors();

	/**
	 * Returns the value of the '<em><b>Monitors</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.meter.Monitor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitors</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Monitors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Monitor> getMonitors();

	/**
	 * Returns the value of the '<em><b>Energy Meters</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.meter.EnergyMeter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Energy Meters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Energy Meters</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_EnergyMeters()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnergyMeter> getEnergyMeters();

	/**
	 * Returns the value of the '<em><b>Generators</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.conversion.Generator}.
	 * It is bidirectional and its opposite is '{@link electrickery.conversion.Generator#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generators</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Generators()
	 * @see electrickery.conversion.Generator#getCircuit
	 * @model opposite="circuit" containment="true"
	 * @generated
	 */
	EList<Generator> getGenerators();

	/**
	 * Returns the value of the '<em><b>Transformers</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.delivery.Transformer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformers</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Transformers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transformer> getTransformers();

	/**
	 * Returns the value of the '<em><b>Cap Controls</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.control.CapacitorControl}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cap Controls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cap Controls</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_CapControls()
	 * @model containment="true"
	 * @generated
	 */
	EList<CapacitorControl> getCapControls();

	/**
	 * Returns the value of the '<em><b>Reg Controls</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.control.RegulatorControl}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reg Controls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reg Controls</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_RegControls()
	 * @model containment="true"
	 * @generated
	 */
	EList<RegulatorControl> getRegControls();

	/**
	 * Returns the value of the '<em><b>Lines</b></em>' reference list.
	 * The list contents are of type {@link electrickery.delivery.Line}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lines</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lines</em>' reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Lines()
	 * @model
	 * @generated
	 */
	EList<Line> getLines();

	/**
	 * Returns the value of the '<em><b>Loads</b></em>' reference list.
	 * The list contents are of type {@link electrickery.conversion.Load}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loads</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loads</em>' reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Loads()
	 * @model
	 * @generated
	 */
	EList<Load> getLoads();

	/**
	 * Returns the value of the '<em><b>Shunt Capacitors</b></em>' reference list.
	 * The list contents are of type {@link electrickery.delivery.Capacitor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shunt Capacitors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shunt Capacitors</em>' reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_ShuntCapacitors()
	 * @model
	 * @generated
	 */
	EList<Capacitor> getShuntCapacitors();

	/**
	 * Returns the value of the '<em><b>Feeder</b></em>' reference list.
	 * The list contents are of type {@link electrickery.common.Feeder}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feeder</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feeder</em>' reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Feeder()
	 * @model
	 * @generated
	 */
	EList<Feeder> getFeeder();

	/**
	 * Returns the value of the '<em><b>Generator Dispatch Reference</b></em>' attribute.
	 * The default value is <code>"1000.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator Dispatch Reference</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator Dispatch Reference</em>' attribute.
	 * @see #setGeneratorDispatchReference(double)
	 * @see electrickery.common.CommonPackage#getCircuit_GeneratorDispatchReference()
	 * @model default="1000.0"
	 * @generated
	 */
	double getGeneratorDispatchReference();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generator Dispatch Reference</em>' attribute.
	 * @see #getGeneratorDispatchReference()
	 * @generated
	 */
	void setGeneratorDispatchReference(double value);

	/**
	 * Returns the value of the '<em><b>Gen Multiplier</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Global multiplier for every generator.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gen Multiplier</em>' attribute.
	 * @see #setGenMultiplier(double)
	 * @see electrickery.common.CommonPackage#getCircuit_GenMultiplier()
	 * @model default="1.0"
	 * @generated
	 */
	double getGenMultiplier();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getGenMultiplier <em>Gen Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen Multiplier</em>' attribute.
	 * @see #getGenMultiplier()
	 * @generated
	 */
	void setGenMultiplier(double value);

	/**
	 * Returns the value of the '<em><b>Solved</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Solved</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Solved</em>' attribute.
	 * @see #setSolved(boolean)
	 * @see electrickery.common.CommonPackage#getCircuit_Solved()
	 * @model default="false"
	 * @generated
	 */
	boolean isSolved();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#isSolved <em>Solved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Solved</em>' attribute.
	 * @see #isSolved()
	 * @generated
	 */
	void setSolved(boolean value);

	/**
	 * Returns the value of the '<em><b>Control bus Name Redefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control bus Name Redefined</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Lets control devices know the bus list has changed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Control bus Name Redefined</em>' attribute.
	 * @see #setControl_busNameRedefined(boolean)
	 * @see electrickery.common.CommonPackage#getCircuit_Control_busNameRedefined()
	 * @model
	 * @generated
	 */
	boolean isControl_busNameRedefined();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#isControl_busNameRedefined <em>Control bus Name Redefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control bus Name Redefined</em>' attribute.
	 * @see #isControl_busNameRedefined()
	 * @generated
	 */
	void setControl_busNameRedefined(boolean value);

	/**
	 * Returns the value of the '<em><b>Load Multiplier</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Global multiplier for every load.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Load Multiplier</em>' attribute.
	 * @see #setLoadMultiplier(double)
	 * @see electrickery.common.CommonPackage#getCircuit_LoadMultiplier()
	 * @model default="1.0"
	 * @generated
	 */
	double getLoadMultiplier();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getLoadMultiplier <em>Load Multiplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Multiplier</em>' attribute.
	 * @see #getLoadMultiplier()
	 * @generated
	 */
	void setLoadMultiplier(double value);

	/**
	 * Returns the value of the '<em><b>Default Growth Factor</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Growth Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Growth Factor</em>' attribute.
	 * @see #setDefaultGrowthFactor(double)
	 * @see electrickery.common.CommonPackage#getCircuit_DefaultGrowthFactor()
	 * @model default="1.0"
	 * @generated
	 */
	double getDefaultGrowthFactor();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getDefaultGrowthFactor <em>Default Growth Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Growth Factor</em>' attribute.
	 * @see #getDefaultGrowthFactor()
	 * @generated
	 */
	void setDefaultGrowthFactor(double value);

	/**
	 * Returns the value of the '<em><b>Default Hour Mult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Hour Mult</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Hour Mult</em>' attribute.
	 * @see #setDefaultHourMult(double)
	 * @see electrickery.common.CommonPackage#getCircuit_DefaultHourMult()
	 * @model
	 * @generated
	 */
	double getDefaultHourMult();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getDefaultHourMult <em>Default Hour Mult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Hour Mult</em>' attribute.
	 * @see #getDefaultHourMult()
	 * @generated
	 */
	void setDefaultHourMult(double value);

	/**
	 * Returns the value of the '<em><b>Price Signal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Price Signal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Price Signal</em>' attribute.
	 * @see #setPriceSignal(double)
	 * @see electrickery.common.CommonPackage#getCircuit_PriceSignal()
	 * @model
	 * @generated
	 */
	double getPriceSignal();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getPriceSignal <em>Price Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Price Signal</em>' attribute.
	 * @see #getPriceSignal()
	 * @generated
	 */
	void setPriceSignal(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Builds designated Y matrix for system and allocates solution arrays.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void buildYMatrix(yBuildOption buildOption, boolean allocateVI);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Redo all Buslists, nodelists.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void reProcessBusDefs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void reCalcAllYPrims();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void reCalcInvalidYPrims();
} // Circuit
