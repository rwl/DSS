/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.control.tests;

import electrickery.control.ControlFactory;
import electrickery.control.GeneratorDispatcher;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Generator Dispatcher</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class GeneratorDispatcherTest extends ControlElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(GeneratorDispatcherTest.class);
	}

	/**
	 * Constructs a new Generator Dispatcher test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratorDispatcherTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Generator Dispatcher test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected GeneratorDispatcher getFixture() {
		return (GeneratorDispatcher)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ControlFactory.eINSTANCE.createGeneratorDispatcher());
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

} //GeneratorDispatcherTest
