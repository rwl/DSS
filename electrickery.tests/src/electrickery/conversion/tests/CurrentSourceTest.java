/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion.tests;

import electrickery.conversion.ConversionFactory;
import electrickery.conversion.CurrentSource;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Current Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link electrickery.conversion.CurrentSource#getBaseCurrent() <em>Get Base Current</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class CurrentSourceTest extends PowerConversionElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CurrentSourceTest.class);
	}

	/**
	 * Constructs a new Current Source test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CurrentSourceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Current Source test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CurrentSource getFixture() {
		return (CurrentSource)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ConversionFactory.eINSTANCE.createCurrentSource());
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

	/**
	 * Tests the '{@link electrickery.conversion.CurrentSource#getBaseCurrent() <em>Get Base Current</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.conversion.CurrentSource#getBaseCurrent()
	 * @generated
	 */
	public void testGetBaseCurrent() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //CurrentSourceTest
