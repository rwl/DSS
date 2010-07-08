/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.meter.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>meter</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class MeterTests extends TestSuite {

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
		TestSuite suite = new MeterTests("meter Tests");
		suite.addTestSuite(EnergyMeterTest.class);
		suite.addTestSuite(MonitorTest.class);
		suite.addTestSuite(SensorTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeterTests(String name) {
		super(name);
	}

} //MeterTests
