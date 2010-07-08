/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.executive.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>executive</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExecutiveTests extends TestSuite {

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
		TestSuite suite = new ExecutiveTests("executive Tests");
		suite.addTestSuite(ExecCommandsTest.class);
		suite.addTestSuite(ExecutiveTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutiveTests(String name) {
		super(name);
	}

} //ExecutiveTests
