/**
 * Copyright (C) 2010 Richard Lincoln
 */
package electrickery.general.tests;

import electrickery.common.tests.NamedTest;
import electrickery.general.GeneralFactory;
import electrickery.general.LineGeometry;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Line Geometry</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class LineGeometryTest extends NamedTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LineGeometryTest.class);
	}

	/**
	 * Constructs a new Line Geometry test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineGeometryTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Line Geometry test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected LineGeometry getFixture() {
		return (LineGeometry)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(GeneralFactory.eINSTANCE.createLineGeometry());
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

} //LineGeometryTest
