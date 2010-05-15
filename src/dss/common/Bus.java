/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dss.common;

import org.eclipse.emf.ecore.EObject;

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
 *   <li>{@link dss.common.Bus#getName <em>Name</em>}</li>
 *   <li>{@link dss.common.Bus#getVBus <em>VBus</em>}</li>
 *   <li>{@link dss.common.Bus#getBusCurrent <em>Bus Current</em>}</li>
 *   <li>{@link dss.common.Bus#getZSC <em>ZSC</em>}</li>
 *   <li>{@link dss.common.Bus#getYSC <em>YSC</em>}</li>
 *   <li>{@link dss.common.Bus#getX <em>X</em>}</li>
 *   <li>{@link dss.common.Bus#getY <em>Y</em>}</li>
 *   <li>{@link dss.common.Bus#getKVBase <em>KV Base</em>}</li>
 *   <li>{@link dss.common.Bus#isCoordsDefined <em>Coords Defined</em>}</li>
 *   <li>{@link dss.common.Bus#isBusChecked <em>Bus Checked</em>}</li>
 *   <li>{@link dss.common.Bus#isKeep <em>Keep</em>}</li>
 *   <li>{@link dss.common.Bus#isRadialBus <em>Radial Bus</em>}</li>
 * </ul>
 * </p>
 *
 * @see dss.common.CommonPackage#getBus()
 * @model
 * @generated
 */
public interface Bus extends EObject {
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
	 * @see dss.common.CommonPackage#getBus_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see dss.common.CommonPackage#getBus_VBus()
	 * @model default="115.0"
	 * @generated
	 */
	double getVBus();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getVBus <em>VBus</em>}' attribute.
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
	 * @see dss.common.CommonPackage#getBus_BusCurrent()
	 * @model
	 * @generated
	 */
	double getBusCurrent();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getBusCurrent <em>Bus Current</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Current</em>' attribute.
	 * @see #getBusCurrent()
	 * @generated
	 */
	void setBusCurrent(double value);

	/**
	 * Returns the value of the '<em><b>ZSC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ZSC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ZSC</em>' attribute.
	 * @see #setZSC(double)
	 * @see dss.common.CommonPackage#getBus_ZSC()
	 * @model
	 * @generated
	 */
	double getZSC();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getZSC <em>ZSC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ZSC</em>' attribute.
	 * @see #getZSC()
	 * @generated
	 */
	void setZSC(double value);

	/**
	 * Returns the value of the '<em><b>YSC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YSC</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YSC</em>' attribute.
	 * @see #setYSC(double)
	 * @see dss.common.CommonPackage#getBus_YSC()
	 * @model
	 * @generated
	 */
	double getYSC();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getYSC <em>YSC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YSC</em>' attribute.
	 * @see #getYSC()
	 * @generated
	 */
	void setYSC(double value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * X coordinate.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(double)
	 * @see dss.common.CommonPackage#getBus_X()
	 * @model
	 * @generated
	 */
	double getX();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getX <em>X</em>}' attribute.
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
	 * @see dss.common.CommonPackage#getBus_Y()
	 * @model
	 * @generated
	 */
	double getY();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(double value);

	/**
	 * Returns the value of the '<em><b>KV Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base kV for each node to ground.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>KV Base</em>' attribute.
	 * @see #setKVBase(double)
	 * @see dss.common.CommonPackage#getBus_KVBase()
	 * @model
	 * @generated
	 */
	double getKVBase();

	/**
	 * Sets the value of the '{@link dss.common.Bus#getKVBase <em>KV Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>KV Base</em>' attribute.
	 * @see #getKVBase()
	 * @generated
	 */
	void setKVBase(double value);

	/**
	 * Returns the value of the '<em><b>Coords Defined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Are the coordinates defined?
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Coords Defined</em>' attribute.
	 * @see #setCoordsDefined(boolean)
	 * @see dss.common.CommonPackage#getBus_CoordsDefined()
	 * @model
	 * @generated
	 */
	boolean isCoordsDefined();

	/**
	 * Sets the value of the '{@link dss.common.Bus#isCoordsDefined <em>Coords Defined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coords Defined</em>' attribute.
	 * @see #isCoordsDefined()
	 * @generated
	 */
	void setCoordsDefined(boolean value);

	/**
	 * Returns the value of the '<em><b>Bus Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bus Checked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bus Checked</em>' attribute.
	 * @see #setBusChecked(boolean)
	 * @see dss.common.CommonPackage#getBus_BusChecked()
	 * @model
	 * @generated
	 */
	boolean isBusChecked();

	/**
	 * Sets the value of the '{@link dss.common.Bus#isBusChecked <em>Bus Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bus Checked</em>' attribute.
	 * @see #isBusChecked()
	 * @generated
	 */
	void setBusChecked(boolean value);

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
	 * @see dss.common.CommonPackage#getBus_Keep()
	 * @model
	 * @generated
	 */
	boolean isKeep();

	/**
	 * Sets the value of the '{@link dss.common.Bus#isKeep <em>Keep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keep</em>' attribute.
	 * @see #isKeep()
	 * @generated
	 */
	void setKeep(boolean value);

	/**
	 * Returns the value of the '<em><b>Radial Bus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag for general use in bus searches.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Radial Bus</em>' attribute.
	 * @see #setRadialBus(boolean)
	 * @see dss.common.CommonPackage#getBus_RadialBus()
	 * @model
	 * @generated
	 */
	boolean isRadialBus();

	/**
	 * Sets the value of the '{@link dss.common.Bus#isRadialBus <em>Radial Bus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Radial Bus</em>' attribute.
	 * @see #isRadialBus()
	 * @generated
	 */
	void setRadialBus(boolean value);

} // Bus
