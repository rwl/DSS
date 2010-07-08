/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.tests;

import electrickery.control.ControlFactory;
import electrickery.control.Relay;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Relay</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelayTest extends ControlElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RelayTest.class);
	}

	/**
	 * Constructs a new Relay test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelayTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Relay test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Relay getFixture() {
		return (Relay)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ControlFactory.eINSTANCE.createRelay());
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

} //RelayTest
