/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.executive.tests;

import electrickery.common.Circuit;
import electrickery.common.CommonFactory;
import electrickery.common.Solution;
import electrickery.conversion.ConversionFactory;
import electrickery.conversion.VoltageSource;
import electrickery.executive.Executive;
import electrickery.executive.ExecutiveFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Executive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link electrickery.executive.Executive#processCommand(java.lang.String) <em>Process Command</em>}</li>
 *   <li>{@link electrickery.executive.Executive#doSetCommand(int) <em>Do Set Command</em>}</li>
 *   <li>{@link electrickery.executive.Executive#doSolveCmd() <em>Do Solve Cmd</em>}</li>
 *   <li>{@link electrickery.executive.Executive#makeNewCircuit(java.lang.String) <em>Make New Circuit</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ExecutiveTest extends TestCase {

    /**
	 * The fixture for this Executive test case.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected Executive fixture = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static void main(String[] args) {
		TestRunner.run(ExecutiveTest.class);
	}

    /**
	 * Constructs a new Executive test case with the given name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExecutiveTest(String name) {
		super(name);
	}

    /**
	 * Sets the fixture for this Executive test case.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void setFixture(Executive fixture) {
		this.fixture = fixture;
	}

    /**
	 * Returns the fixture for this Executive test case.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected Executive getFixture() {
		return fixture;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated NOT
     */
    @Override
    protected void setUp() throws Exception {
        Circuit ckt = CommonFactory.eINSTANCE.createCircuit();
        ckt.setName("ckt1");
        Solution sol = CommonFactory.eINSTANCE.createSolution();
        ckt.setSolution(sol);
        // Voltage source named "source" connected to SourceBus.
        VoltageSource source = ConversionFactory.eINSTANCE.createVoltageSource();
        source.setName("source");
        source.setBus1("SourceBus");

        Executive exec = ExecutiveFactory.eINSTANCE.createExecutive();
        exec.getCircuits().add(ckt);
        exec.setActiveCircuit(ckt);
        setFixture(exec);
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
    @Override
    protected void tearDown() throws Exception {
		setFixture(null);
	}

    /**
     * Tests the '{@link electrickery.executive.Executive#processCommand(java.lang.String) <em>Process Command</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see electrickery.executive.Executive#processCommand(java.lang.String)
     * @generated NOT
     */
    public void testProcessCommand__String() {
        // TODO: implement this operation test method
    }

    /**
     * Tests the '{@link electrickery.executive.Executive#doSetCommand(int) <em>Do Set Command</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see electrickery.executive.Executive#doSetCommand(int)
     * @generated NOT
     */
    public void testDoSetCommand__int() {
        // TODO: implement this operation test method
    }

    /**
     * Tests the '{@link electrickery.executive.Executive#doSolveCmd() <em>Do Solve Cmd</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see electrickery.executive.Executive#doSolveCmd()
     * @generated NOT
     */
    public void testDoSolveCmd() {
        getFixture().getActiveCircuit().getSolution().solve();
    }

    /**
     * Tests the '{@link electrickery.executive.Executive#makeNewCircuit(java.lang.String) <em>Make New Circuit</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see electrickery.executive.Executive#makeNewCircuit(java.lang.String)
     * @generated NOT
     */
    public void testMakeNewCircuit__String() {
        // TODO: implement this operation test method
    }

} //ExecutiveTest
