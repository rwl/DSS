/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.delivery.tests;

import electrickery.delivery.DeliveryFactory;
import electrickery.delivery.Reactor;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Reactor</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReactorTest extends PowerDeliveryElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ReactorTest.class);
	}

	/**
	 * Constructs a new Reactor test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReactorTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Reactor test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Reactor getFixture() {
		return (Reactor)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(DeliveryFactory.eINSTANCE.createReactor());
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

} //ReactorTest
