/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import electrickery.conversion.VoltageSource;
import electrickery.executive.Executive;

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
 *   <li>{@link electrickery.common.Circuit#getExecutive <em>Executive</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getSolution <em>Solution</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getBuses <em>Buses</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getBusList <em>Bus List</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getVoltageSources <em>Voltage Sources</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getActiveCircuitElement <em>Active Circuit Element</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getName <em>Name</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getNumNodes <em>Num Nodes</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getNumBuses <em>Num Buses</em>}</li>
 *   <li>{@link electrickery.common.Circuit#isBusNameRedefined <em>Bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.Circuit#isSolved <em>Solved</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getLoadMultiplier <em>Load Multiplier</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getDefaultGrowthFactor <em>Default Growth Factor</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getDefaultGrowthRate <em>Default Growth Rate</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getGeneratorDispatchReference <em>Generator Dispatch Reference</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getGenMultiplier <em>Gen Multiplier</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getDefaultHourMult <em>Default Hour Mult</em>}</li>
 *   <li>{@link electrickery.common.Circuit#isControl_busNameRedefined <em>Control bus Name Redefined</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getPriceSignal <em>Price Signal</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getEmergMinVolts <em>Emerg Min Volts</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getEmergMaxVolts <em>Emerg Max Volts</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getNormalMinVolts <em>Normal Min Volts</em>}</li>
 *   <li>{@link electrickery.common.Circuit#getNormalMaxVolts <em>Normal Max Volts</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getCircuit()
 * @model
 * @generated
 */
public interface Circuit extends EObject {

	/**
	 * Returns the value of the '<em><b>Executive</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link electrickery.executive.Executive#getCircuits <em>Circuits</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executive</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executive</em>' container reference.
	 * @see #setExecutive(Executive)
	 * @see electrickery.common.CommonPackage#getCircuit_Executive()
	 * @see electrickery.executive.Executive#getCircuits
	 * @model opposite="circuits" transient="false"
	 * @generated
	 */
	Executive getExecutive();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getExecutive <em>Executive</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executive</em>' container reference.
	 * @see #getExecutive()
	 * @generated
	 */
	void setExecutive(Executive value);

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
	 * @model opposite="circuit"
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
	 * Returns the value of the '<em><b>Buses</b></em>' containment reference list.
	 * The list contents are of type {@link electrickery.common.Bus}.
	 * It is bidirectional and its opposite is '{@link electrickery.common.Bus#getCircuit <em>Circuit</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buses</em>' containment reference list.
	 * @see electrickery.common.CommonPackage#getCircuit_Buses()
	 * @see electrickery.common.Bus#getCircuit
	 * @model opposite="circuit" containment="true"
	 * @generated
	 */
	EList<Bus> getBuses();

	/**
	 * Returns the value of the '<em><b>Bus List</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus List</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus List</em>' attribute list.
	 * @see electrickery.common.CommonPackage#getCircuit_BusList()
	 * @model
	 * @generated
	 */
	EList<String> getBusList();

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
	 * Returns the value of the '<em><b>Default Growth Rate</b></em>' attribute.
	 * The default value is <code>"1.025"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Growth Rate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Growth Rate</em>' attribute.
	 * @see #setDefaultGrowthRate(double)
	 * @see electrickery.common.CommonPackage#getCircuit_DefaultGrowthRate()
	 * @model default="1.025"
	 * @generated
	 */
	double getDefaultGrowthRate();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getDefaultGrowthRate <em>Default Growth Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Growth Rate</em>' attribute.
	 * @see #getDefaultGrowthRate()
	 * @generated
	 */
	void setDefaultGrowthRate(double value);

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
	 * Returns the value of the '<em><b>Control bus Name Redefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
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
	 * Returns the value of the '<em><b>Emerg Min Volts</b></em>' attribute.
	 * The default value is <code>"0.90"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emerg Min Volts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emerg Min Volts</em>' attribute.
	 * @see #setEmergMinVolts(double)
	 * @see electrickery.common.CommonPackage#getCircuit_EmergMinVolts()
	 * @model default="0.90"
	 * @generated
	 */
	double getEmergMinVolts();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getEmergMinVolts <em>Emerg Min Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg Min Volts</em>' attribute.
	 * @see #getEmergMinVolts()
	 * @generated
	 */
	void setEmergMinVolts(double value);

	/**
	 * Returns the value of the '<em><b>Emerg Max Volts</b></em>' attribute.
	 * The default value is <code>"1.08"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emerg Max Volts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emerg Max Volts</em>' attribute.
	 * @see #setEmergMaxVolts(double)
	 * @see electrickery.common.CommonPackage#getCircuit_EmergMaxVolts()
	 * @model default="1.08"
	 * @generated
	 */
	double getEmergMaxVolts();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getEmergMaxVolts <em>Emerg Max Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emerg Max Volts</em>' attribute.
	 * @see #getEmergMaxVolts()
	 * @generated
	 */
	void setEmergMaxVolts(double value);

	/**
	 * Returns the value of the '<em><b>Normal Min Volts</b></em>' attribute.
	 * The default value is <code>"0.95"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Normal Min Volts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Normal Min Volts</em>' attribute.
	 * @see #setNormalMinVolts(double)
	 * @see electrickery.common.CommonPackage#getCircuit_NormalMinVolts()
	 * @model default="0.95"
	 * @generated
	 */
	double getNormalMinVolts();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getNormalMinVolts <em>Normal Min Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Normal Min Volts</em>' attribute.
	 * @see #getNormalMinVolts()
	 * @generated
	 */
	void setNormalMinVolts(double value);

	/**
	 * Returns the value of the '<em><b>Normal Max Volts</b></em>' attribute.
	 * The default value is <code>"1.05"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Normal Max Volts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Normal Max Volts</em>' attribute.
	 * @see #setNormalMaxVolts(double)
	 * @see electrickery.common.CommonPackage#getCircuit_NormalMaxVolts()
	 * @model default="1.05"
	 * @generated
	 */
	double getNormalMaxVolts();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getNormalMaxVolts <em>Normal Max Volts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Normal Max Volts</em>' attribute.
	 * @see #getNormalMaxVolts()
	 * @generated
	 */
	void setNormalMaxVolts(double value);

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
	 * Returns the value of the '<em><b>Num Buses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num Buses</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num Buses</em>' attribute.
	 * @see #setNumBuses(int)
	 * @see electrickery.common.CommonPackage#getCircuit_NumBuses()
	 * @model
	 * @generated
	 */
	int getNumBuses();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getNumBuses <em>Num Buses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Buses</em>' attribute.
	 * @see #getNumBuses()
	 * @generated
	 */
	void setNumBuses(int value);

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
	 * Returns the value of the '<em><b>Active Circuit Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Circuit Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Circuit Element</em>' reference.
	 * @see #setActiveCircuitElement(CircuitElement)
	 * @see electrickery.common.CommonPackage#getCircuit_ActiveCircuitElement()
	 * @model
	 * @generated
	 */
	CircuitElement getActiveCircuitElement();

	/**
	 * Sets the value of the '{@link electrickery.common.Circuit#getActiveCircuitElement <em>Active Circuit Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Circuit Element</em>' reference.
	 * @see #getActiveCircuitElement()
	 * @generated
	 */
	void setActiveCircuitElement(CircuitElement value);

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
