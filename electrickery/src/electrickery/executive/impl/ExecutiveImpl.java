/**
 * Copyright (C) 2010 Richard Lincoln
 *
 * $Id$
 */
package electrickery.executive.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import electrickery.common.Circuit;
import electrickery.common.CommonFactory;
import electrickery.conversion.ConversionFactory;
import electrickery.conversion.VoltageSource;
import electrickery.executive.Executive;
import electrickery.executive.ExecutivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Executive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link electrickery.executive.impl.ExecutiveImpl#getCommand <em>Command</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecutiveImpl#getActiveCircuit <em>Active Circuit</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecutiveImpl#getCircuits <em>Circuits</em>}</li>
 *   <li>{@link electrickery.executive.impl.ExecutiveImpl#getMaxCircuits <em>Max Circuits</em>}</li>
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
	 * The cached value of the '{@link #getActiveCircuit() <em>Active Circuit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getActiveCircuit()
	 * @generated
	 * @ordered
	 */
    protected Circuit activeCircuit;

                                                                /**
	 * The cached value of the '{@link #getCircuits() <em>Circuits</em>}' containment reference list.
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
			circuits = new EObjectContainmentEList<Circuit>(Circuit.class, this, ExecutivePackage.EXECUTIVE__CIRCUITS);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecutivePackage.EXECUTIVE__CIRCUITS:
				return ((InternalEList<?>)getCircuits()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
