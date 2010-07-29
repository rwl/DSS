/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;

import cern.colt.matrix.tdcomplex.DComplexMatrix1D;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bus</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A bus is a circuit element having [1..N] nodes. Buses are the
 * connection point for all other circuit elements. The main electrical
 * property of a Bus is voltage.  Each node has a voltage with respect to the
 * zero voltage reference (remote ground).  There is a nodal admittance
 * equation written for every node (i.e., the current is summed at each node).
 * 
 * A bus may have any number of nodes (places to connect device terminal
 * conductors).  The nodes may be arbitrarily numbered, except that the first
 * N are reserved for the N phases.  Thus, if a bus has 3-phase devices
 * connected to it, connections would be expected to nodes 1, 2, and 3.  So
 * the DSS would use these voltages to compute the sequence voltages, for
 * example.   Phase 1 would nominally represent the same phase throughout the
 * circuit, although that would not be mandatory.  It is up to the user to
 * maintain a consistent definition.  If only the default connections are
 * used, the consistency is maintained automatically. Any other nodes would
 * simply be points of connection with no special meaning.
 * 
 * Each Bus object keeps track of the allocation and designation of its nodes.
 * 
 * Node 0 of a bus is always the voltage reference (a.k.a, ground, or earth).
 * That is, it always has a voltage of exactly zero volts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.common.Bus#getCircuit <em>Circuit</em>}</li>
 *   <li>{@link electrickery.common.Bus#getVBus <em>VBus</em>}</li>
 *   <li>{@link electrickery.common.Bus#getBusCurrent <em>Bus Current</em>}</li>
 *   <li>{@link electrickery.common.Bus#getNumNodesThisBus <em>Num Nodes This Bus</em>}</li>
 *   <li>{@link electrickery.common.Bus#getKVBase <em>KV Base</em>}</li>
 *   <li>{@link electrickery.common.Bus#getX <em>X</em>}</li>
 *   <li>{@link electrickery.common.Bus#getY <em>Y</em>}</li>
 *   <li>{@link electrickery.common.Bus#isCoordDefined <em>Coord Defined</em>}</li>
 *   <li>{@link electrickery.common.Bus#isKeep <em>Keep</em>}</li>
 *   <li>{@link electrickery.common.Bus#getBusRef <em>Bus Ref</em>}</li>
 *   <li>{@link electrickery.common.Bus#getNodeNum <em>Node Num</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.common.CommonPackage#getBus()
 * @model
 * @generated
 */
public interface Bus extends Named {
	/**
	 * Returns the value of the '<em><b>Circuit</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link electrickery.common.Circuit#getBuses <em>Buses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuit</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuit</em>' container reference.
	 * @see #setCircuit(Circuit)
	 * @see electrickery.common.CommonPackage#getBus_Circuit()
	 * @see electrickery.common.Circuit#getBuses
	 * @model opposite="buses" transient="false"
	 * @generated
	 */
	Circuit getCircuit();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getCircuit <em>Circuit</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Circuit</em>' container reference.
	 * @see #getCircuit()
	 * @generated
	 */
	void setCircuit(Circuit value);

	/**
	 * Returns the value of the '<em><b>VBus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>VBus</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>VBus</em>' reference.
	 * @see #setVBus(DComplexMatrix1D)
	 * @see electrickery.common.CommonPackage#getBus_VBus()
	 * @model type="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	DComplexMatrix1D getVBus();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getVBus <em>VBus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VBus</em>' reference.
	 * @see #getVBus()
	 * @generated
	 */
	void setVBus(DComplexMatrix1D value);

	/**
	 * Returns the value of the '<em><b>Bus Current</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Current</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Current</em>' reference.
	 * @see #setBusCurrent(DComplexMatrix1D)
	 * @see electrickery.common.CommonPackage#getBus_BusCurrent()
	 * @model type="electrickery.DComplexMatrix1D"
	 * @generated
	 */
	DComplexMatrix1D getBusCurrent();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getBusCurrent <em>Bus Current</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Current</em>' reference.
	 * @see #getBusCurrent()
	 * @generated
	 */
	void setBusCurrent(DComplexMatrix1D value);

	/**
	 * Returns the value of the '<em><b>Num Nodes This Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num Nodes This Bus</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num Nodes This Bus</em>' attribute.
	 * @see #isSetNumNodesThisBus()
	 * @see #unsetNumNodesThisBus()
	 * @see #setNumNodesThisBus(int)
	 * @see electrickery.common.CommonPackage#getBus_NumNodesThisBus()
	 * @model unsettable="true"
	 * @generated
	 */
	int getNumNodesThisBus();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getNumNodesThisBus <em>Num Nodes This Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Nodes This Bus</em>' attribute.
	 * @see #isSetNumNodesThisBus()
	 * @see #unsetNumNodesThisBus()
	 * @see #getNumNodesThisBus()
	 * @generated
	 */
	void setNumNodesThisBus(int value);

	/**
	 * Unsets the value of the '{@link electrickery.common.Bus#getNumNodesThisBus <em>Num Nodes This Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetNumNodesThisBus()
	 * @see #getNumNodesThisBus()
	 * @see #setNumNodesThisBus(int)
	 * @generated
	 */
	void unsetNumNodesThisBus();

	/**
	 * Returns whether the value of the '{@link electrickery.common.Bus#getNumNodesThisBus <em>Num Nodes This Bus</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Num Nodes This Bus</em>' attribute is set.
	 * @see #unsetNumNodesThisBus()
	 * @see #getNumNodesThisBus()
	 * @see #setNumNodesThisBus(int)
	 * @generated
	 */
	boolean isSetNumNodesThisBus();

	/**
	 * Returns the value of the '<em><b>KV Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base kV for each node to ground.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Base</em>' attribute.
	 * @see #setKVBase(double)
	 * @see electrickery.common.CommonPackage#getBus_KVBase()
	 * @model
	 * @generated
	 */
	double getKVBase();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getKVBase <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV Base</em>' attribute.
	 * @see #getKVBase()
	 * @generated
	 */
	void setKVBase(double value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * X coordinate.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(double)
	 * @see electrickery.common.CommonPackage#getBus_X()
	 * @model
	 * @generated
	 */
	double getX();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(double value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Y coordinate.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(double)
	 * @see electrickery.common.CommonPackage#getBus_Y()
	 * @model
	 * @generated
	 */
	double getY();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(double value);

	/**
	 * Returns the value of the '<em><b>Coord Defined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Are the coordinates defined?
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Coord Defined</em>' attribute.
	 * @see #setCoordDefined(boolean)
	 * @see electrickery.common.CommonPackage#getBus_CoordDefined()
	 * @model
	 * @generated
	 */
	boolean isCoordDefined();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#isCoordDefined <em>Coord Defined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coord Defined</em>' attribute.
	 * @see #isCoordDefined()
	 * @generated
	 */
	void setCoordDefined(boolean value);

	/**
	 * Returns the value of the '<em><b>Keep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keep</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keep</em>' attribute.
	 * @see #setKeep(boolean)
	 * @see electrickery.common.CommonPackage#getBus_Keep()
	 * @model
	 * @generated
	 */
	boolean isKeep();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#isKeep <em>Keep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keep</em>' attribute.
	 * @see #isKeep()
	 * @generated
	 */
	void setKeep(boolean value);

	/**
	 * Returns the value of the '<em><b>Bus Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to Bus in circuit's busList.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bus Ref</em>' attribute.
	 * @see #setBusRef(int)
	 * @see electrickery.common.CommonPackage#getBus_BusRef()
	 * @model
	 * @generated
	 */
	int getBusRef();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getBusRef <em>Bus Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Ref</em>' attribute.
	 * @see #getBusRef()
	 * @generated
	 */
	void setBusRef(int value);

	/**
	 * Returns the value of the '<em><b>Node Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Num</em>' attribute.
	 * @see #setNodeNum(int)
	 * @see electrickery.common.CommonPackage#getBus_NodeNum()
	 * @model
	 * @generated
	 */
	int getNodeNum();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getNodeNum <em>Node Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Num</em>' attribute.
	 * @see #getNodeNum()
	 * @generated
	 */
	void setNodeNum(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int add(int nodeNum);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns reference num for node by node number.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	int find(int nodeNum);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns index of node by node number.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	int findIdx(int nodeNum);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns reference Num for node by node index.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	int getRef(int nodeIndex);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns ith node number designation.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	int getNum(int nodeIndex);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void allocateBusVoltages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void allocateBusCurrents();

} // Bus
