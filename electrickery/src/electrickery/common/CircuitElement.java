/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import cern.colt.matrix.tdcomplex.DComplexMatrix2D;

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
 *   <li>{@link electrickery.common.CircuitElement#getName <em>Name</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getBaseFreq <em>Base Freq</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getYOrder <em>YOrder</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#isYPrimInvalid <em>YPrim Invalid</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getLastTerminalChecked <em>Last Terminal Checked</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getTerminals <em>Terminals</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getActiveTerminal <em>Active Terminal</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getActiveTerminalIndex <em>Active Terminal Index</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getNTerms <em>NTerms</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getNConds <em>NConds</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getNPhases <em>NPhases</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getYPrimSeries <em>YPrim Series</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getYPrimShunt <em>YPrim Shunt</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getYPrim <em>YPrim</em>}</li>
 *   <li>{@link electrickery.common.CircuitElement#getYPrimFreq <em>YPrim Freq</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getCircuitElement()
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_Name()
	 * @model
	 * @generated
	 */
    String getName();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getName <em>Name</em>}' attribute.
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_Enabled()
	 * @model
	 * @generated
	 */
    boolean isEnabled();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#isEnabled <em>Enabled</em>}' attribute.
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_BaseFreq()
	 * @model default="60.0"
	 * @generated
	 */
	double getBaseFreq();

				/**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getBaseFreq <em>Base Freq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Freq</em>' attribute.
	 * @see #getBaseFreq()
	 * @generated
	 */
	void setBaseFreq(double value);

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
	 * @see electrickery.common.CommonPackage#getCircuitElement_YOrder()
	 * @model
	 * @generated
	 */
    int getYOrder();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getYOrder <em>YOrder</em>}' attribute.
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_YPrimInvalid()
	 * @model
	 * @generated
	 */
    boolean isYPrimInvalid();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#isYPrimInvalid <em>YPrim Invalid</em>}' attribute.
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_LastTerminalChecked()
	 * @model
	 * @generated
	 */
    int getLastTerminalChecked();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getLastTerminalChecked <em>Last Terminal Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Terminal Checked</em>' attribute.
	 * @see #getLastTerminalChecked()
	 * @generated
	 */
    void setLastTerminalChecked(int value);

    /**
	 * Returns the value of the '<em><b>Terminals</b></em>' reference list.
	 * The list contents are of type {@link electrickery.common.Terminal}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Terminals</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Terminals</em>' reference list.
	 * @see electrickery.common.CommonPackage#getCircuitElement_Terminals()
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
	 * @see #setActiveTerminal(Terminal)
	 * @see electrickery.common.CommonPackage#getCircuitElement_ActiveTerminal()
	 * @model
	 * @generated
	 */
    Terminal getActiveTerminal();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getActiveTerminal <em>Active Terminal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Terminal</em>' reference.
	 * @see #getActiveTerminal()
	 * @generated
	 */
	void setActiveTerminal(Terminal value);

				/**
	 * Returns the value of the '<em><b>Active Terminal Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Terminal Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Terminal Index</em>' attribute.
	 * @see #setActiveTerminalIndex(int)
	 * @see electrickery.common.CommonPackage#getCircuitElement_ActiveTerminalIndex()
	 * @model
	 * @generated
	 */
	int getActiveTerminalIndex();

				/**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getActiveTerminalIndex <em>Active Terminal Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Terminal Index</em>' attribute.
	 * @see #getActiveTerminalIndex()
	 * @generated
	 */
	void setActiveTerminalIndex(int value);

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
	 * @see electrickery.common.CommonPackage#getCircuitElement_NTerms()
	 * @model
	 * @generated
	 */
    int getNTerms();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getNTerms <em>NTerms</em>}' attribute.
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_NConds()
	 * @model
	 * @generated
	 */
    int getNConds();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getNConds <em>NConds</em>}' attribute.
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
	 * @see electrickery.common.CommonPackage#getCircuitElement_NPhases()
	 * @model
	 * @generated
	 */
    int getNPhases();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getNPhases <em>NPhases</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NPhases</em>' attribute.
	 * @see #getNPhases()
	 * @generated
	 */
    void setNPhases(int value);

    /**
	 * Returns the value of the '<em><b>YPrim Series</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>YPrim Series</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>YPrim Series</em>' reference.
	 * @see #setYPrimSeries(DComplexMatrix2D)
	 * @see electrickery.common.CommonPackage#getCircuitElement_YPrimSeries()
	 * @model type="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    DComplexMatrix2D getYPrimSeries();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getYPrimSeries <em>YPrim Series</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim Series</em>' reference.
	 * @see #getYPrimSeries()
	 * @generated
	 */
    void setYPrimSeries(DComplexMatrix2D value);

    /**
	 * Returns the value of the '<em><b>YPrim Shunt</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>YPrim Shunt</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>YPrim Shunt</em>' reference.
	 * @see #setYPrimShunt(DComplexMatrix2D)
	 * @see electrickery.common.CommonPackage#getCircuitElement_YPrimShunt()
	 * @model type="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    DComplexMatrix2D getYPrimShunt();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getYPrimShunt <em>YPrim Shunt</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim Shunt</em>' reference.
	 * @see #getYPrimShunt()
	 * @generated
	 */
    void setYPrimShunt(DComplexMatrix2D value);

    /**
	 * Returns the value of the '<em><b>YPrim</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the value of the '<em>YPrim</em>' reference.
	 * @see #setYPrim(DComplexMatrix2D)
	 * @see electrickery.common.CommonPackage#getCircuitElement_YPrim()
	 * @model type="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    DComplexMatrix2D getYPrim();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getYPrim <em>YPrim</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPrim</em>' reference.
	 * @see #getYPrim()
	 * @generated
	 */
    void setYPrim(DComplexMatrix2D value);

    /**
	 * Returns the value of the '<em><b>YPrim Freq</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Frequency at which YPrim has been computed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>YPrim Freq</em>' attribute.
	 * @see #setYPrimFreq(double)
	 * @see electrickery.common.CommonPackage#getCircuitElement_YPrimFreq()
	 * @model
	 * @generated
	 */
    double getYPrimFreq();

    /**
	 * Sets the value of the '{@link electrickery.common.CircuitElement#getYPrimFreq <em>YPrim Freq</em>}' attribute.
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
	 * @model yMatrixType="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    void doYPrimCalcs(DComplexMatrix2D yMatrix);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model type="electrickery.DComplexMatrix2D"
	 * @generated
	 */
    DComplexMatrix2D getYPrimValues(yBuildOption opt);

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set NodeRef Array for fast solution with intrinsics.
	 * <!-- end-model-doc -->
	 * @model nodeRefArrayDataType="electrickery.EIntArray"
	 * @generated
	 */
	void setNodeRef(int iTerm, int[] nodeRefArray);

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getFirstBus();

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getNextBus();

} // CircuitElement
