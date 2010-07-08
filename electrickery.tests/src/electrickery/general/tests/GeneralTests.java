/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>general</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class GeneralTests extends TestSuite {

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
		TestSuite suite = new GeneralTests("general Tests");
		suite.addTestSuite(GrowthShapeTest.class);
		suite.addTestSuite(LoadShapeTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralTests(String name) {
		super(name);
	}

} //GeneralTests
