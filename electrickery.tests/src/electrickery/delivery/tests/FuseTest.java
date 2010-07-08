/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.delivery.tests;

import electrickery.delivery.DeliveryFactory;
import electrickery.delivery.Fuse;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Fuse</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class FuseTest extends PowerDeliveryElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FuseTest.class);
	}

	/**
	 * Constructs a new Fuse test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FuseTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Fuse test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Fuse getFixture() {
		return (Fuse)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(DeliveryFactory.eINSTANCE.createFuse());
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

} //FuseTest
