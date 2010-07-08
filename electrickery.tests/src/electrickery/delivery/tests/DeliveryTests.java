/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.delivery.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>delivery</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeliveryTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new DeliveryTests("delivery Tests");
		suite.addTestSuite(CapacitorTest.class);
		suite.addTestSuite(FaultTest.class);
		suite.addTestSuite(FuseTest.class);
		suite.addTestSuite(LineTest.class);
		suite.addTestSuite(ReactorTest.class);
		suite.addTestSuite(TransformerTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeliveryTests(String name) {
		super(name);
	}

} //DeliveryTests
