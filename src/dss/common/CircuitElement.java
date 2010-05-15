/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common;

import cern.colt.matrix.tdcomplex.impl.DenseDComplexMatrix1D;
import cern.colt.matrix.tdcomplex.impl.SparseDComplexMatrix2D;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Circuit Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for all elements of a circuit.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dss.common.CircuitElement#getName <em>Name</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getBaseFreq <em>Base Freq</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getNodeRef <em>Node Ref</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getYOrder <em>YOrder</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isYPrimInvalid <em>YPrim Invalid</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getLastTerminalChecked <em>Last Terminal Checked</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isChecked <em>Checked</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isHasMeter <em>Has Meter</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isIsolated <em>Isolated</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isHasControl <em>Has Control</em>}</li>
 *   <li>{@link dss.common.CircuitElement#isPartOfFeeder <em>Part Of Feeder</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getControlElement <em>Control Element</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getTerminals <em>Terminals</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getActiveTerminal <em>Active Terminal</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getNTerms <em>NTerms</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getNConds <em>NConds</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getBusIndex <em>Bus Index</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getYPrimSeries <em>YPrim Series</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getYPrimShunt <em>YPrim Shunt</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getYPrim <em>YPrim</em>}</li>
 *   <li>{@link dss.common.CircuitElement#getYPrimFreq <em>YPrim Freq</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.common.CommonPackage#getCircuitElement()
 * @model abstract="true"
 * @generated
 */
public interface CircuitElement extends EObject {
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
	 * @see dss.common.CommonPackage#getCircuitElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether this element is enabled.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Base Freq</b></em>' attribute.
	 * The default value is <code>"60.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base frequency for ratings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base Freq</em>' attribute.
	 * @see #setBaseFreq(double)
	 * @see dss.common.CommonPackage#getCircuitElement_BaseFreq()
	 * @model default="60.0"
	 * @generated
	 */
	double getBaseFreq();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getBaseFreq <em>Base Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Freq</em>' attribute.
	 * @see #getBaseFreq()
	 * @generated
	 */
	void setBaseFreq(double value);

	/**
	 * Returns the value of the '<em><b>Node Ref</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Ref</em>' attribute list.
	 * @see dss.common.CommonPackage#getCircuitElement_NodeRef()
	 * @model
	 * @generated
	 */
	EList<Integer> getNodeRef();

	/**
	 * Returns the value of the '<em><b>YOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YOrder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YOrder</em>' attribute.
	 * @see #setYOrder(int)
	 * @see dss.common.CommonPackage#getCircuitElement_YOrder()
	 * @model
	 * @generated
	 */
	int getYOrder();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getYOrder <em>YOrder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YOrder</em>' attribute.
	 * @see #getYOrder()
	 * @generated
	 */
	void setYOrder(int value);

	/**
	 * Returns the value of the '<em><b>YPrim Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YPrim Invalid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YPrim Invalid</em>' attribute.
	 * @see #setYPrimInvalid(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_YPrimInvalid()
	 * @model
	 * @generated
	 */
	boolean isYPrimInvalid();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isYPrimInvalid <em>YPrim Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim Invalid</em>' attribute.
	 * @see #isYPrimInvalid()
	 * @generated
	 */
	void setYPrimInvalid(boolean value);

	/**
	 * Returns the value of the '<em><b>Last Terminal Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag used in tree searches.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Last Terminal Checked</em>' attribute.
	 * @see #setLastTerminalChecked(int)
	 * @see dss.common.CommonPackage#getCircuitElement_LastTerminalChecked()
	 * @model
	 * @generated
	 */
	int getLastTerminalChecked();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getLastTerminalChecked <em>Last Terminal Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Terminal Checked</em>' attribute.
	 * @see #getLastTerminalChecked()
	 * @generated
	 */
	void setLastTerminalChecked(int value);

	/**
	 * Returns the value of the '<em><b>Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag used in tree searches etc.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Checked</em>' attribute.
	 * @see #setChecked(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_Checked()
	 * @model
	 * @generated
	 */
	boolean isChecked();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isChecked <em>Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checked</em>' attribute.
	 * @see #isChecked()
	 * @generated
	 */
	void setChecked(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Meter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Meter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Meter</em>' attribute.
	 * @see #setHasMeter(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_HasMeter()
	 * @model
	 * @generated
	 */
	boolean isHasMeter();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isHasMeter <em>Has Meter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Meter</em>' attribute.
	 * @see #isHasMeter()
	 * @generated
	 */
	void setHasMeter(boolean value);

	/**
	 * Returns the value of the '<em><b>Isolated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isolated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isolated</em>' attribute.
	 * @see #setIsolated(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_Isolated()
	 * @model
	 * @generated
	 */
	boolean isIsolated();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isIsolated <em>Isolated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isolated</em>' attribute.
	 * @see #isIsolated()
	 * @generated
	 */
	void setIsolated(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Control</em>' attribute.
	 * @see #setHasControl(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_HasControl()
	 * @model
	 * @generated
	 */
	boolean isHasControl();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isHasControl <em>Has Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Control</em>' attribute.
	 * @see #isHasControl()
	 * @generated
	 */
	void setHasControl(boolean value);

	/**
	 * Returns the value of the '<em><b>Part Of Feeder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part Of Feeder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part Of Feeder</em>' attribute.
	 * @see #setPartOfFeeder(boolean)
	 * @see dss.common.CommonPackage#getCircuitElement_PartOfFeeder()
	 * @model
	 * @generated
	 */
	boolean isPartOfFeeder();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#isPartOfFeeder <em>Part Of Feeder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part Of Feeder</em>' attribute.
	 * @see #isPartOfFeeder()
	 * @generated
	 */
	void setPartOfFeeder(boolean value);

	/**
	 * Returns the value of the '<em><b>Control Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Element</em>' reference.
	 * @see #setControlElement(EClass)
	 * @see dss.common.CommonPackage#getCircuitElement_ControlElement()
	 * @model
	 * @generated
	 */
	EClass getControlElement();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getControlElement <em>Control Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Element</em>' reference.
	 * @see #getControlElement()
	 * @generated
	 */
	void setControlElement(EClass value);

	/**
	 * Returns the value of the '<em><b>Terminals</b></em>' reference list.
	 * The list contents are of type {@link dss.common.Terminal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Terminals</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Terminals</em>' reference list.
	 * @see dss.common.CommonPackage#getCircuitElement_Terminals()
	 * @model
	 * @generated
	 */
	EList<Terminal> getTerminals();

	/**
	 * Returns the value of the '<em><b>Active Terminal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Terminal</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Terminal</em>' reference.
	 * @see #setActiveTerminal(EClass)
	 * @see dss.common.CommonPackage#getCircuitElement_ActiveTerminal()
	 * @model
	 * @generated
	 */
	EClass getActiveTerminal();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getActiveTerminal <em>Active Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Terminal</em>' reference.
	 * @see #getActiveTerminal()
	 * @generated
	 */
	void setActiveTerminal(EClass value);

	/**
	 * Returns the value of the '<em><b>NTerms</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NTerms</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NTerms</em>' attribute.
	 * @see #setNTerms(int)
	 * @see dss.common.CommonPackage#getCircuitElement_NTerms()
	 * @model
	 * @generated
	 */
	int getNTerms();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getNTerms <em>NTerms</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NTerms</em>' attribute.
	 * @see #getNTerms()
	 * @generated
	 */
	void setNTerms(int value);

	/**
	 * Returns the value of the '<em><b>NConds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No. conductors per terminal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NConds</em>' attribute.
	 * @see #setNConds(int)
	 * @see dss.common.CommonPackage#getCircuitElement_NConds()
	 * @model
	 * @generated
	 */
	int getNConds();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getNConds <em>NConds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NConds</em>' attribute.
	 * @see #getNConds()
	 * @generated
	 */
	void setNConds(int value);

	/**
	 * Returns the value of the '<em><b>NPhases</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NPhases</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NPhases</em>' attribute.
	 * @see #setNPhases(int)
	 * @see dss.common.CommonPackage#getCircuitElement_NPhases()
	 * @model
	 * @generated
	 */
	int getNPhases();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getNPhases <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPhases</em>' attribute.
	 * @see #getNPhases()
	 * @generated
	 */
	void setNPhases(int value);

	/**
	 * Returns the value of the '<em><b>Bus Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Index</em>' attribute.
	 * @see #setBusIndex(int)
	 * @see dss.common.CommonPackage#getCircuitElement_BusIndex()
	 * @model
	 * @generated
	 */
	int getBusIndex();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getBusIndex <em>Bus Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Index</em>' attribute.
	 * @see #getBusIndex()
	 * @generated
	 */
	void setBusIndex(int value);

	/**
	 * Returns the value of the '<em><b>YPrim Series</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YPrim Series</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YPrim Series</em>' attribute.
	 * @see #setYPrimSeries(SparseDComplexMatrix2D)
	 * @see dss.common.CommonPackage#getCircuitElement_YPrimSeries()
	 * @model dataType="dss.SparseDComplexMatrix2D"
	 * @generated
	 */
	SparseDComplexMatrix2D getYPrimSeries();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getYPrimSeries <em>YPrim Series</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim Series</em>' attribute.
	 * @see #getYPrimSeries()
	 * @generated
	 */
	void setYPrimSeries(SparseDComplexMatrix2D value);

	/**
	 * Returns the value of the '<em><b>YPrim Shunt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YPrim Shunt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YPrim Shunt</em>' attribute.
	 * @see #setYPrimShunt(SparseDComplexMatrix2D)
	 * @see dss.common.CommonPackage#getCircuitElement_YPrimShunt()
	 * @model dataType="dss.SparseDComplexMatrix2D"
	 * @generated
	 */
	SparseDComplexMatrix2D getYPrimShunt();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getYPrimShunt <em>YPrim Shunt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim Shunt</em>' attribute.
	 * @see #getYPrimShunt()
	 * @generated
	 */
	void setYPrimShunt(SparseDComplexMatrix2D value);

	/**
	 * Returns the value of the '<em><b>YPrim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Order will be nTerms * nConds
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YPrim</em>' attribute.
	 * @see #setYPrim(SparseDComplexMatrix2D)
	 * @see dss.common.CommonPackage#getCircuitElement_YPrim()
	 * @model dataType="dss.SparseDComplexMatrix2D"
	 * @generated
	 */
	SparseDComplexMatrix2D getYPrim();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getYPrim <em>YPrim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim</em>' attribute.
	 * @see #getYPrim()
	 * @generated
	 */
	void setYPrim(SparseDComplexMatrix2D value);

	/**
	 * Returns the value of the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Frequency at which YPrim has been computed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YPrim Freq</em>' attribute.
	 * @see #setYPrimFreq(double)
	 * @see dss.common.CommonPackage#getCircuitElement_YPrimFreq()
	 * @model
	 * @generated
	 */
	double getYPrimFreq();

	/**
	 * Sets the value of the '{@link dss.common.CircuitElement#getYPrimFreq <em>YPrim Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim Freq</em>' attribute.
	 * @see #getYPrimFreq()
	 * @generated
	 */
	void setYPrimFreq(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void calcYPrim(double yPrimFreq);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model yMatrixDataType="dss.SparseDComplexMatrix2D"
	 * @generated
	 */
	void doYPrimCalcs(SparseDComplexMatrix2D yMatrix);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="dss.SparseDComplexMatrix2D"
	 * @generated
	 */
	SparseDComplexMatrix2D getYPrimValues(yBuildOption opt);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void makePosSequence();

} // CircuitElement
