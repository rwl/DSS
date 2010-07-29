/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>conversion</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConversionTests extends TestSuite {

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
		TestSuite suite = new ConversionTests("conversion Tests");
		suite.addTestSuite(CurrentSourceTest.class);
		suite.addTestSuite(GeneratorTest.class);
		suite.addTestSuite(LoadTest.class);
		suite.addTestSuite(VoltageSourceTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConversionTests(String name) {
		super(name);
	}

} //ConversionTests
