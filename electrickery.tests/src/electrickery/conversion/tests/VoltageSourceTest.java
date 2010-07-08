/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion.tests;

import electrickery.conversion.ConversionFactory;
import electrickery.conversion.VoltageSource;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Voltage Source</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class VoltageSourceTest extends PowerConversionElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(VoltageSourceTest.class);
	}

	/**
	 * Constructs a new Voltage Source test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VoltageSourceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Voltage Source test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected VoltageSource getFixture() {
		return (VoltageSource)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ConversionFactory.eINSTANCE.createVoltageSource());
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

} //VoltageSourceTest
