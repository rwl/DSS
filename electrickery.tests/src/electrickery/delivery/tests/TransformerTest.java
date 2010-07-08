/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.delivery.tests;

import electrickery.delivery.DeliveryFactory;
import electrickery.delivery.Transformer;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Transformer</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransformerTest extends PowerDeliveryElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TransformerTest.class);
	}

	/**
	 * Constructs a new Transformer test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformerTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Transformer test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Transformer getFixture() {
		return (Transformer)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(DeliveryFactory.eINSTANCE.createTransformer());
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

} //TransformerTest
