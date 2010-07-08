/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.tests;

import electrickery.control.ControlFactory;
import electrickery.control.RegulatorControl;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Regulator Control</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RegulatorControlTest extends ControlElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RegulatorControlTest.class);
	}

	/**
	 * Constructs a new Regulator Control test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegulatorControlTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Regulator Control test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected RegulatorControl getFixture() {
		return (RegulatorControl)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ControlFactory.eINSTANCE.createRegulatorControl());
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

} //RegulatorControlTest
