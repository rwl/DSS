/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.delivery.tests;

import electrickery.delivery.Capacitor;
import electrickery.delivery.DeliveryFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Capacitor</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CapacitorTest extends PowerDeliveryElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CapacitorTest.class);
	}

	/**
	 * Constructs a new Capacitor test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapacitorTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Capacitor test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Capacitor getFixture() {
		return (Capacitor)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(DeliveryFactory.eINSTANCE.createCapacitor());
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

} //CapacitorTest
