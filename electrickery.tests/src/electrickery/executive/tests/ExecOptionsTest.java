/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.executive.tests;

import electrickery.executive.ExecOptions;
import electrickery.executive.ExecutiveFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Exec Options</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExecOptionsTest extends TestCase {

	/**
	 * The fixture for this Exec Options test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecOptions fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExecOptionsTest.class);
	}

	/**
	 * Constructs a new Exec Options test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecOptionsTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Exec Options test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ExecOptions fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Exec Options test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecOptions getFixture() {
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
		setFixture(ExecutiveFactory.eINSTANCE.createExecOptions());
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

} //ExecOptionsTest
