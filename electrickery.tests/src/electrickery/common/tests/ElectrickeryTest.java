/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.common.tests;

import electrickery.common.CommonFactory;
import electrickery.common.Electrickery;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Electrickery</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ElectrickeryTest extends TestCase {

	/**
	 * The fixture for this Electrickery test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Electrickery fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ElectrickeryTest.class);
	}

	/**
	 * Constructs a new Electrickery test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElectrickeryTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Electrickery test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Electrickery fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Electrickery test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Electrickery getFixture() {
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
		setFixture(CommonFactory.eINSTANCE.createElectrickery());
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

} //ElectrickeryTest
