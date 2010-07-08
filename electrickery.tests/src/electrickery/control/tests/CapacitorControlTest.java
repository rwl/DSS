/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.tests;

import electrickery.control.CapacitorControl;
import electrickery.control.ControlFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Capacitor Control</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CapacitorControlTest extends ControlElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CapacitorControlTest.class);
	}

	/**
	 * Constructs a new Capacitor Control test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapacitorControlTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Capacitor Control test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CapacitorControl getFixture() {
		return (CapacitorControl)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ControlFactory.eINSTANCE.createCapacitorControl());
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

} //CapacitorControlTest
