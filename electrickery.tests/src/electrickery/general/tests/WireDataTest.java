/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general.tests;

import electrickery.general.GeneralFactory;
import electrickery.general.WireData;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Wire Data</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class WireDataTest extends TestCase {

	/**
	 * The fixture for this Wire Data test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WireData fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(WireDataTest.class);
	}

	/**
	 * Constructs a new Wire Data test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WireDataTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Wire Data test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(WireData fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Wire Data test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WireData getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(GeneralFactory.eINSTANCE.createWireData());
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

} //WireDataTest
