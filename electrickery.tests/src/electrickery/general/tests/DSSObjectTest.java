/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general.tests;

import electrickery.general.DSSObject;
import electrickery.general.GeneralFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>DSS Object</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class DSSObjectTest extends TestCase {

	/**
	 * The fixture for this DSS Object test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DSSObject fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DSSObjectTest.class);
	}

	/**
	 * Constructs a new DSS Object test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSSObjectTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this DSS Object test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(DSSObject fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this DSS Object test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DSSObject getFixture() {
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
		setFixture(GeneralFactory.eINSTANCE.createDSSObject());
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

} //DSSObjectTest
