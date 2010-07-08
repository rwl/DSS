/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.conversion.tests;

import electrickery.conversion.ConversionFactory;
import electrickery.conversion.Generator;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Generator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link electrickery.conversion.Generator#setNominalGeneration() <em>Set Nominal Generation</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class GeneratorTest extends PowerConversionElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(GeneratorTest.class);
	}

	/**
	 * Constructs a new Generator test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratorTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Generator test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Generator getFixture() {
		return (Generator)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ConversionFactory.eINSTANCE.createGenerator());
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
	 * Tests the '{@link electrickery.conversion.Generator#setNominalGeneration() <em>Set Nominal Generation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see electrickery.conversion.Generator#setNominalGeneration()
	 * @generated
	 */
	public void testSetNominalGeneration() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //GeneratorTest
