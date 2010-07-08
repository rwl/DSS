/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.meter.tests;

import electrickery.meter.EnergyMeter;
import electrickery.meter.MeterFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Energy Meter</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnergyMeterTest extends MeterElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EnergyMeterTest.class);
	}

	/**
	 * Constructs a new Energy Meter test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnergyMeterTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Energy Meter test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EnergyMeter getFixture() {
		return (EnergyMeter)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(MeterFactory.eINSTANCE.createEnergyMeter());
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

} //EnergyMeterTest
