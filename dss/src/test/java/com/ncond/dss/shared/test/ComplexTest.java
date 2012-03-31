package com.ncond.dss.shared.test;

import com.ncond.dss.shared.Complex;

import junit.framework.TestCase;


public class ComplexTest extends TestCase {

	private static final double delta = 1e-6;

	public void testToArray() {
		Complex c = new Complex(2, 3);

		double[] a = c.asArray();

		assertEquals(2, a.length);
		assertEquals(2, a[0], delta);
		assertEquals(3, a[1], delta);
	}

	public void testToArrayComplexArray() {
		Complex[] c = new Complex[] {
				new Complex(2, 3),
				new Complex(4, 5)
		};

		double[] a = Complex.toArray(c);

		assertEquals(4, a.length);
		assertEquals(2, a[0], delta);
		assertEquals(5, a[3], delta);
	}

	public void testToArrayComplexArrayOffset() {
		Complex[] c = new Complex[] {
				new Complex(2, 3),
				new Complex(4, 5),
				new Complex(6, 7)
		};

		double[] a = Complex.toArray(c, 1);

		assertEquals(4, a.length);
		assertEquals(4, a[0], delta);
		assertEquals(7, a[3], delta);
	}

	public void testFromArray() {
		double[] a = new double[] {
				2, 3,
				4, 5,
				6, 7
		};

		Complex[] c = new Complex[3];

		Complex.fromArray(a, c);

		assertEquals(2, c[0].real(), delta);
		assertEquals(7, c[2].imag(), delta);
	}

	public void testFromArrayOffset() {
		double[] a = new double[] {
				2, 3,
				4, 5,
				6, 7,
				8, 9
		};

		Complex[] c = new Complex[3];

		Complex.fromArray(a, 1, c, 0);

		assertEquals(4, c[0].real(), delta);
		assertEquals(9, c[2].imag(), delta);

		c = new Complex[3];

		Complex.fromArray(a, 2, c, 1);

		assertNull(c[0]);
		assertEquals(6, c[1].real(), delta);
		assertEquals(9, c[2].imag(), delta);
	}

}
