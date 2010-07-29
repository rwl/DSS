/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package electrickery.common;


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
	 * It is bidirectional and its opposite is '{@link electrickery.common.Circuit#getBusList <em>Bus List</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuit</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuit</em>' container reference.
	 * @see #setCircuit(Circuit)
	 * @see electrickery.common.CommonPackage#getBus_Circuit()
	 * @see electrickery.common.Circuit#getBusList
	 * @model opposite="busList" transient="false"
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
	 * Returns the value of the '<em><b>VBus</b></em>' attribute.
	 * The default value is <code>"115.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>VBus</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>VBus</em>' attribute.
	 * @see #setVBus(double)
	 * @see electrickery.common.CommonPackage#getBus_VBus()
	 * @model default="115.0"
	 * @generated
	 */
	double getVBus();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getVBus <em>VBus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VBus</em>' attribute.
	 * @see #getVBus()
	 * @generated
	 */
	void setVBus(double value);

	/**
	 * Returns the value of the '<em><b>Bus Current</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Current</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Current</em>' attribute.
	 * @see #setBusCurrent(double)
	 * @see electrickery.common.CommonPackage#getBus_BusCurrent()
	 * @model
	 * @generated
	 */
	double getBusCurrent();

	/**
	 * Sets the value of the '{@link electrickery.common.Bus#getBusCurrent <em>Bus Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Current</em>' attribute.
	 * @see #getBusCurrent()
	 * @generated
	 */
	void setBusCurrent(double value);

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

} // Bus
