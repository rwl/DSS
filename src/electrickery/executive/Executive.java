/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.executive;

import electrickery.common.Circuit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Executive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link electrickery.executive.Executive#getCommand <em>Command</em>}</li>
 *   <li>{@link electrickery.executive.Executive#getExecCommands <em>Exec Commands</em>}</li>
 *   <li>{@link electrickery.executive.Executive#getExecOptions <em>Exec Options</em>}</li>
 *   <li>{@link electrickery.executive.Executive#getActiveCircuit <em>Active Circuit</em>}</li>
 *   <li>{@link electrickery.executive.Executive#getCircuits <em>Circuits</em>}</li>
 *   <li>{@link electrickery.executive.Executive#getMaxCircuits <em>Max Circuits</em>}</li>
 * </ul>
 * </p>
 *
 * @see electrickery.executive.ExecutivePackage#getExecutive()
 * @model
 * @generated
 */
public interface Executive extends EObject {
	/**
	 * Returns the value of the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command</em>' attribute.
	 * @see #setCommand(String)
	 * @see electrickery.executive.ExecutivePackage#getExecutive_Command()
	 * @model
	 * @generated
	 */
	String getCommand();

	/**
	 * Sets the value of the '{@link electrickery.executive.Executive#getCommand <em>Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command</em>' attribute.
	 * @see #getCommand()
	 * @generated
	 */
	void setCommand(String value);

	/**
	 * Returns the value of the '<em><b>Exec Commands</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Commands</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Commands</em>' reference.
	 * @see #setExecCommands(ExecCommands)
	 * @see electrickery.executive.ExecutivePackage#getExecutive_ExecCommands()
	 * @model required="true"
	 * @generated
	 */
	ExecCommands getExecCommands();

	/**
	 * Sets the value of the '{@link electrickery.executive.Executive#getExecCommands <em>Exec Commands</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exec Commands</em>' reference.
	 * @see #getExecCommands()
	 * @generated
	 */
	void setExecCommands(ExecCommands value);

	/**
	 * Returns the value of the '<em><b>Exec Options</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Options</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Options</em>' reference.
	 * @see #setExecOptions(ExecOptions)
	 * @see electrickery.executive.ExecutivePackage#getExecutive_ExecOptions()
	 * @model required="true"
	 * @generated
	 */
	ExecOptions getExecOptions();

	/**
	 * Sets the value of the '{@link electrickery.executive.Executive#getExecOptions <em>Exec Options</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exec Options</em>' reference.
	 * @see #getExecOptions()
	 * @generated
	 */
	void setExecOptions(ExecOptions value);

	/**
	 * Returns the value of the '<em><b>Active Circuit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Circuit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Circuit</em>' reference.
	 * @see #setActiveCircuit(Circuit)
	 * @see electrickery.executive.ExecutivePackage#getExecutive_ActiveCircuit()
	 * @model
	 * @generated
	 */
	Circuit getActiveCircuit();

	/**
	 * Sets the value of the '{@link electrickery.executive.Executive#getActiveCircuit <em>Active Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Circuit</em>' reference.
	 * @see #getActiveCircuit()
	 * @generated
	 */
	void setActiveCircuit(Circuit value);

	/**
	 * Returns the value of the '<em><b>Circuits</b></em>' reference list.
	 * The list contents are of type {@link electrickery.common.Circuit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Circuits</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circuits</em>' reference list.
	 * @see electrickery.executive.ExecutivePackage#getExecutive_Circuits()
	 * @model
	 * @generated
	 */
	EList<Circuit> getCircuits();

	/**
	 * Returns the value of the '<em><b>Max Circuits</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Circuits</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Circuits</em>' attribute.
	 * @see #setMaxCircuits(int)
	 * @see electrickery.executive.ExecutivePackage#getExecutive_MaxCircuits()
	 * @model default="1"
	 * @generated
	 */
	int getMaxCircuits();

	/**
	 * Sets the value of the '{@link electrickery.executive.Executive#getMaxCircuits <em>Max Circuits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Circuits</em>' attribute.
	 * @see #getMaxCircuits()
	 * @generated
	 */
	void setMaxCircuits(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void processCommand(String cmdLine);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int doSetCommand(int solveOption);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int doSolveCmd();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void makeNewCircuit(String name);

} // Executive
