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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import electrickery.common.Circuit;
import electrickery.common.CommonPackage;
import electrickery.common.Globals;
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
 *   <li>{@link electrickery.executive.impl.ExecutiveImpl#getGlobals <em>Globals</em>}</li>
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
	 * The cached value of the '{@link #getGlobals() <em>Globals</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobals()
	 * @generated
	 * @ordered
	 */
	protected Globals globals;

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
			circuits = new EObjectContainmentWithInverseEList<Circuit>(Circuit.class, this, ExecutivePackage.EXECUTIVE__CIRCUITS, CommonPackage.CIRCUIT__EXECUTIVE);
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
	 * @generated
	 */
	public Globals getGlobals() {
		if (globals != null && globals.eIsProxy()) {
			InternalEObject oldGlobals = (InternalEObject)globals;
			globals = (Globals)eResolveProxy(oldGlobals);
			if (globals != oldGlobals) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutivePackage.EXECUTIVE__GLOBALS, oldGlobals, globals));
			}
		}
		return globals;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Globals basicGetGlobals() {
		return globals;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlobals(Globals newGlobals, NotificationChain msgs) {
		Globals oldGlobals = globals;
		globals = newGlobals;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__GLOBALS, oldGlobals, newGlobals);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

																/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlobals(Globals newGlobals) {
		if (newGlobals != globals) {
			NotificationChain msgs = null;
			if (globals != null)
				msgs = ((InternalEObject)globals).eInverseRemove(this, CommonPackage.GLOBALS__EXECUTIVES, Globals.class, msgs);
			if (newGlobals != null)
				msgs = ((InternalEObject)newGlobals).eInverseAdd(this, CommonPackage.GLOBALS__EXECUTIVES, Globals.class, msgs);
			msgs = basicSetGlobals(newGlobals, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutivePackage.EXECUTIVE__GLOBALS, newGlobals, newGlobals));
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecutivePackage.EXECUTIVE__CIRCUITS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCircuits()).basicAdd(otherEnd, msgs);
			case ExecutivePackage.EXECUTIVE__GLOBALS:
				if (globals != null)
					msgs = ((InternalEObject)globals).eInverseRemove(this, CommonPackage.GLOBALS__EXECUTIVES, Globals.class, msgs);
				return basicSetGlobals((Globals)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case ExecutivePackage.EXECUTIVE__GLOBALS:
				return basicSetGlobals(null, msgs);
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
			case ExecutivePackage.EXECUTIVE__GLOBALS:
				if (resolve) return getGlobals();
				return basicGetGlobals();
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
			case ExecutivePackage.EXECUTIVE__GLOBALS:
				setGlobals((Globals)newValue);
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
			case ExecutivePackage.EXECUTIVE__GLOBALS:
				setGlobals((Globals)null);
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
			case ExecutivePackage.EXECUTIVE__GLOBALS:
				return globals != null;
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
