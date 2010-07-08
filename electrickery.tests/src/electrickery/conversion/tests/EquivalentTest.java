/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion.tests;

import electrickery.conversion.ConversionFactory;
import electrickery.conversion.Equivalent;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Equivalent</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EquivalentTest extends PowerConversionElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EquivalentTest.class);
	}

	/**
	 * Constructs a new Equivalent test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EquivalentTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Equivalent test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Equivalent getFixture() {
		return (Equivalent)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ConversionFactory.eINSTANCE.createEquivalent());
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

} //EquivalentTest
