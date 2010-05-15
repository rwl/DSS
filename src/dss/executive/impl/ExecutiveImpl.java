/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package dss.executive.impl;

import dss.DSSFactory;
import dss.common.Circuit;
import dss.common.CommonFactory;
import dss.conversion.ConversionFactory;
import dss.conversion.VoltageSource;
import dss.executive.ExecCommands;
import dss.executive.ExecOptions;
import dss.executive.Executive;
import dss.executive.ExecutivePackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Executive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dss.executive.impl.ExecutiveImpl#getCommand <em>Command</em>}</li>
 *   <li>{@link dss.executive.impl.ExecutiveImpl#getExecCommands <em>Exec Commands</em>}</li>
 *   <li>{@link dss.executive.impl.ExecutiveImpl#getExecOptions <em>Exec Options</em>}</li>
 *   <li>{@link dss.executive.impl.ExecutiveImpl#getActiveCircuit <em>Active Circuit</em>}</li>
 *   <li>{@link dss.executive.impl.ExecutiveImpl#getCircuits <em>Circuits</em>}</li>
 *   <li>{@link dss.executive.impl.ExecutiveImpl#getMaxCircuits <em>Max Circuits</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutiveImpl extends EObjectImpl implements Executive {
    /**
	 * The default value of the '{@link #getCommand() <em>Command</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCommand()
	 * @generated
	 * @ordered
	 */
    protected static final String COMMAND_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCommand() <em>Command</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCommand()
	 * @generated
	 * @ordered
	 */
    protected String command = COMMAND_EDEFAULT;

    /**
	 * The cached value of the '{@link #getExecCommands() <em>Exec Commands</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExecCommands()
	 * @generated
	 * @ordered
	 */
    protected ExecCommands execCommands;

                /**
	 * The cached value of the '{@link #getExecOptions() <em>Exec Options</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExecOptions()
	 * @generated
	 * @ordered
	 */
    protected ExecOptions execOptions;

                                                                /**
	 * The cached value of the '{@link #getActiveCircuit() <em>Active Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getActiveCircuit()
	 * @generated
	 * @ordered
	 */
    protected Circuit activeCircuit;

                                                                /**
	 * The cached value of the '{@link #getCircuits() <em>Circuits</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCircuits()
	 * @generated
	 * @ordered
	 */
    protected EList<Circuit> circuits;

                                                                /**
	 * The default value of the '{@link #getMaxCircuits() <em>Max Circuits</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxCircuits()
	 * @generated
	 * @ordered
	 */
    protected static final int MAX_CIRCUITS_EDEFAULT = 1;

                                                                /**
	 * The cached value of the '{@link #getMaxCircuits() <em>Max Circuits</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMaxCircuits()
	 * @generated
	 * @ordered
	 */
    protected int maxCircuits = MAX_CIRCUITS_EDEFAULT;

                                                                                                                                                                                                                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ExecutiveImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ExecutivePackage.Literals.EXECUTIVE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getCommand() {
		return command;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setCommand(String newCommand) {
        String oldCommand = command;
        command = newCommand;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__COMMAND, oldCommand, command));

        processCommand(newCommand);
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExecCommands getExecCommands() {
		if (execCommands != null && execCommands.eIsProxy()) {
			InternalEObject oldExecCommands = (InternalEObject)execCommands;
			execCommands = (ExecCommands)eResolveProxy(oldExecCommands);
			if (execCommands != oldExecCommands) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXECUTIVE__EXEC_COMMANDS, oldExecCommands, execCommands));
			}
		}
		return execCommands;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExecCommands basicGetExecCommands() {
		return execCommands;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setExecCommands(ExecCommands newExecCommands) {
		ExecCommands oldExecCommands = execCommands;
		execCommands = newExecCommands;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__EXEC_COMMANDS, oldExecCommands, execCommands));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExecOptions getExecOptions() {
		if (execOptions != null && execOptions.eIsProxy()) {
			InternalEObject oldExecOptions = (InternalEObject)execOptions;
			execOptions = (ExecOptions)eResolveProxy(oldExecOptions);
			if (execOptions != oldExecOptions) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXECUTIVE__EXEC_OPTIONS, oldExecOptions, execOptions));
			}
		}
		return execOptions;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExecOptions basicGetExecOptions() {
		return execOptions;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setExecOptions(ExecOptions newExecOptions) {
		ExecOptions oldExecOptions = execOptions;
		execOptions = newExecOptions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__EXEC_OPTIONS, oldExecOptions, execOptions));
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit getActiveCircuit() {
		if (activeCircuit != null && activeCircuit.eIsProxy()) {
			InternalEObject oldActiveCircuit = (InternalEObject)activeCircuit;
			activeCircuit = (Circuit)eResolveProxy(oldActiveCircuit);
			if (activeCircuit != oldActiveCircuit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXECUTIVE__ACTIVE_CIRCUIT, oldActiveCircuit, activeCircuit));
			}
		}
		return activeCircuit;
	}

                                                                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Circuit basicGetActiveCircuit() {
		return activeCircuit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setActiveCircuit(Circuit newActiveCircuit) {
		Circuit oldActiveCircuit = activeCircuit;
		activeCircuit = newActiveCircuit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__ACTIVE_CIRCUIT, oldActiveCircuit, activeCircuit));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Circuit> getCircuits() {
		if (circuits == null) {
			circuits = new EObjectResolvingEList<Circuit>(Circuit.class, this, ExecutivePackage.EXECUTIVE__CIRCUITS);
		}
		return circuits;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getMaxCircuits() {
		return maxCircuits;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMaxCircuits(int newMaxCircuits) {
		int oldMaxCircuits = maxCircuits;
		maxCircuits = newMaxCircuits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__MAX_CIRCUITS, oldMaxCircuits, maxCircuits));
	}

                /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void processCommand(String cmdLine) {
        if (getActiveCircuit() == null) {
            System.out.println("No circuit defined.");
        } else if (cmdLine == "solve") {
            doSetCommand(1);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int doSetCommand(int solveOption) {
        if (solveOption == 1)
            doSolveCmd();
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int doSolveCmd() {
        getActiveCircuit().getSolution().solve();
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void makeNewCircuit(String name) {
        if (getCircuits().size() < getMaxCircuits() - 1) {
            setActiveCircuit(CommonFactory.eINSTANCE.createCircuit());
            getActiveCircuit().setName(name);
            getCircuits().add(getActiveCircuit());
            // Create a default circuit.
            getActiveCircuit().getSolution().setSolutionAbort(false);
            // Voltage source named "source" connected to SourceBus.
            VoltageSource source = ConversionFactory.eINSTANCE.createVoltageSource();
            source.setName("source");
            source.setBus1("SourceBus");
            getActiveCircuit().getVoltageSources().add(source);
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExecutivePackage.EXECUTIVE__COMMAND:
				return getCommand();
			case ExecutivePackage.EXECUTIVE__EXEC_COMMANDS:
				if (resolve) return getExecCommands();
				return basicGetExecCommands();
			case ExecutivePackage.EXECUTIVE__EXEC_OPTIONS:
				if (resolve) return getExecOptions();
				return basicGetExecOptions();
			case ExecutivePackage.EXECUTIVE__ACTIVE_CIRCUIT:
				if (resolve) return getActiveCircuit();
				return basicGetActiveCircuit();
			case ExecutivePackage.EXECUTIVE__CIRCUITS:
				return getCircuits();
			case ExecutivePackage.EXECUTIVE__MAX_CIRCUITS:
				return getMaxCircuits();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
                @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExecutivePackage.EXECUTIVE__COMMAND:
				setCommand((String)newValue);
				return;
			case ExecutivePackage.EXECUTIVE__EXEC_COMMANDS:
				setExecCommands((ExecCommands)newValue);
				return;
			case ExecutivePackage.EXECUTIVE__EXEC_OPTIONS:
				setExecOptions((ExecOptions)newValue);
				return;
			case ExecutivePackage.EXECUTIVE__ACTIVE_CIRCUIT:
				setActiveCircuit((Circuit)newValue);
				return;
			case ExecutivePackage.EXECUTIVE__CIRCUITS:
				getCircuits().clear();
				getCircuits().addAll((Collection<? extends Circuit>)newValue);
				return;
			case ExecutivePackage.EXECUTIVE__MAX_CIRCUITS:
				setMaxCircuits((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case ExecutivePackage.EXECUTIVE__COMMAND:
				setCommand(COMMAND_EDEFAULT);
				return;
			case ExecutivePackage.EXECUTIVE__EXEC_COMMANDS:
				setExecCommands((ExecCommands)null);
				return;
			case ExecutivePackage.EXECUTIVE__EXEC_OPTIONS:
				setExecOptions((ExecOptions)null);
				return;
			case ExecutivePackage.EXECUTIVE__ACTIVE_CIRCUIT:
				setActiveCircuit((Circuit)null);
				return;
			case ExecutivePackage.EXECUTIVE__CIRCUITS:
				getCircuits().clear();
				return;
			case ExecutivePackage.EXECUTIVE__MAX_CIRCUITS:
				setMaxCircuits(MAX_CIRCUITS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExecutivePackage.EXECUTIVE__COMMAND:
				return COMMAND_EDEFAULT == null ? command != null : !COMMAND_EDEFAULT.equals(command);
			case ExecutivePackage.EXECUTIVE__EXEC_COMMANDS:
				return execCommands != null;
			case ExecutivePackage.EXECUTIVE__EXEC_OPTIONS:
				return execOptions != null;
			case ExecutivePackage.EXECUTIVE__ACTIVE_CIRCUIT:
				return activeCircuit != null;
			case ExecutivePackage.EXECUTIVE__CIRCUITS:
				return circuits != null && !circuits.isEmpty();
			case ExecutivePackage.EXECUTIVE__MAX_CIRCUITS:
				return maxCircuits != MAX_CIRCUITS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (command: ");
		result.append(command);
		result.append(", maxCircuits: ");
		result.append(maxCircuits);
		result.append(')');
		return result.toString();
	}

} //ExecutiveImpl
