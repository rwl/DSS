/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>control</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlTests extends TestSuite {

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
		TestSuite suite = new ControlTests("control Tests");
		suite.addTestSuite(CapacitorControlTest.class);
		suite.addTestSuite(GeneratorDispatcherTest.class);
		suite.addTestSuite(RecloserTest.class);
		suite.addTestSuite(RegulatorControlTest.class);
		suite.addTestSuite(RelayTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlTests(String name) {
		super(name);
	}

} //ControlTests
