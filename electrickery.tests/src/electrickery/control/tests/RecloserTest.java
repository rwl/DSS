/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.tests;

import electrickery.control.ControlFactory;
import electrickery.control.Recloser;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Recloser</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RecloserTest extends ControlElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RecloserTest.class);
	}

	/**
	 * Constructs a new Recloser test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecloserTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Recloser test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Recloser getFixture() {
		return (Recloser)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ControlFactory.eINSTANCE.createRecloser());
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

} //RecloserTest
